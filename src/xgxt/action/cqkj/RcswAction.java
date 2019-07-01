package xgxt.action.cqkj;

/*
 * 创建日期 2006-9-16
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.ApplyAction;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.String.StringUtils;

/**
 * @author bat_zzj
 */

public class RcswAction extends ApplyAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm chkUser = (CommanForm) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}

			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();

			if (myAct.equalsIgnoreCase("xszbbQuerryCqkj")) {
				myActFwd = xszbbQuerryCqkj(mapping, form, request, response); // 学生证补办-查询-重庆科技
			} else if (myAct.equalsIgnoreCase("xszbbViewmoreCqkj")) {
				myActFwd = xszbbViewmoreCqkj(mapping, form, request, response); // 学生证补办-查看详细-重庆科技
			} else if (myAct.equalsIgnoreCase("xszbbInputCqkj")) {
				myActFwd = xszbbInputCqkj(mapping, form, request, response); // 学生证补办-添加-重庆科技
			}else if (myAct.equalsIgnoreCase("xszbbUpdateCqkj")) {
				myActFwd = xszbbUpdateCqkj(mapping, form, request, response); // 学生证补办-修改-重庆科技
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("数据连接中断，请重新登陆！");
		}
		return new ActionForward("/login.jsp", false);
	}

	// 学生证补办查询及删除-重庆科技
	private ActionForward xszbbQuerryCqkj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DAO dao = DAO.getInstance();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
//		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String sql = "";
		String querry = "";
		String rsNum = "0";

		if (userType.equalsIgnoreCase("xx")
				|| userType.equalsIgnoreCase("admin")) {
			request.setAttribute("who", "xx");

		} else if (userType.equalsIgnoreCase("xy")) {
			request.setAttribute("who", "xy");
		}

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		String zydm = DealString.toGBK(request.getParameter("zydm"));
		String bjdm = DealString.toGBK(request.getParameter("bjdm"));
		String xn = DealString.toGBK(request.getParameter("xn"));
		String xq = DealString.toGBK(request.getParameter("xqmc"));

		if ("del".equals(doType)) {
			boolean judge = false;
			sql = "delete from xszbbb where xh=?";
			judge = StandardOperation.delete("xszbbb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		map.put("xh", xh);
		map.put("xm", xm);
		map.put("xb", xb);
		map.put("nj", nj);
		map.put("xydm", xydm);
		map.put("zydm", zydm);
		map.put("bjdm", bjdm);
		map.put("xn", xn);
		map.put("xqmc", xq);

		if (userType.equalsIgnoreCase("xy")) {
			map.put("xydm", userType);
		}

		if (!"".equalsIgnoreCase(xh) && null != xh) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%' ");
		}
		if (!"".equalsIgnoreCase(xm) && null != xm) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%' ");
		}
		if (!"".equalsIgnoreCase(xb) && null != xb) {
			query.append(" and xb like '%");
			query.append(xb);
			query.append("%' ");
		}

		if (!"".equalsIgnoreCase(xydm) && null != xydm) {
			query.append(" and xydm='");
			query.append(xydm);
			query.append("' ");
		}

		if (!"".equalsIgnoreCase(zydm) && null != zydm) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("' ");
		}

		if (!"".equalsIgnoreCase(bjdm) && null != bjdm) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("' ");
		}
		if (!"".equalsIgnoreCase(xn) && null != xn) {
			query.append(" and xn='");
			query.append(xn);
			query.append("' ");
		}
		if (!"".equalsIgnoreCase(xq) && null != xq) {
			query.append(" and xq='");
			query.append(xq);
			query.append("' ");
		}

		querry = query.toString();

		sql = "select  rownum 行号,a.* from view_xszbb a " + querry;
		String[] colList = new String[] { "rid", "行号", "xh", "xm", "xb",
				"sqsj", "bbsj", "sflq" };
		String[] colListCN = dao.getColumnNameCN(colList, "view_xszbb");
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("njList", dao.getNjList()); // 发送年级列表
		request.setAttribute("xnList", dao.getXnndList()); // 发送学年列表
		request.setAttribute("xqList", dao.getXqList()); // 发送学期列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送班级列表
		request.setAttribute("rs", rs); // 发送数据集
		request.setAttribute("topTr", topTr); // 发送表头
		request.setAttribute("rsNum", rsNum); // 发送记录数
		request.setAttribute("rs1", map); // 把查询条件再次封装后发送回去

		return mapping.findForward("success");
	}

	// 学生证补办查看详细信息-重庆科技
	private ActionForward xszbbViewmoreCqkj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String pk = "rid";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String tableName = "view_xszbb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view") || doType.equalsIgnoreCase("update")) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from view_xszbb where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				if (!"".equalsIgnoreCase(map.get("sqsj"))
						&& null != map.get("sqsj")) {
					map.put("sqsj", dao.doForTime2(map.get("sqsj")));
				}
				if (!"".equalsIgnoreCase(map.get("bbsj"))
						&& null != map.get("bbsj")) {
					map.put("bbsj", dao.doForTime2(map.get("bbsj")));
				}
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		//将经办人改为他的真名
		if(map.get("jbr") != null){
			sql = "select jbrxm from jbrxxb where jbrgh = ?";
			String[] sJbrArray = dao.getOneRs(sql, new String[]{map.get("jbr")},new String[]{"jbrxm"});
			if((sJbrArray != null) && !StringUtils.isNull(sJbrArray[0])){
				map.remove("jbr");
				map.put("jbr", sJbrArray[0]);
			}
		}	
		if(doType.equalsIgnoreCase("update")){
			request.setAttribute("ndList", dao.getNdList()); // 发送年度列表
			request.setAttribute("xnList", dao.getXnndList()); // 发送学年列表
			request.setAttribute("xqList", dao.getXqList()); // 发送学期列表
			request.setAttribute("isModi", "1");
			sql = "select jbrgh,jbrxm from jbrxxb order by jbrgh";  //经办人信息表
			List jbrList = dao.getList(sql, new String[] {}, new String[] {
					"jbrgh", "jbrxm" });
			request.setAttribute("jbrList", jbrList);
			return new ActionForward("/rcsw/cqkj/xszbb_input.jsp");
		}
		return mapping.findForward("success");
	}

	// 学生证补办增加信息-重庆科技
	private ActionForward xszbbInputCqkj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String doType = request.getParameter("doType");
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = "xh";
//		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String xh = DealString.toGBK(request.getParameter("xh"));
		
		//获取当前时间
		String[] dqndq = dao.getOneRs("select dqxn,dqxq,dqnd from xtszb",
				new String[] {}, new String[] { "dqxn", "dqxq", "dqnd" });
		if(null!=pk&&!"".equalsIgnoreCase(pk)){
			pk.replace(" ", "+");
		}

		if ("save".equalsIgnoreCase(doType)) {
			String xn = DealString.toGBK(request.getParameter("xn")); // 学年
			String xq = DealString.toGBK(request.getParameter("xq"));// 学期
                   xh = DealString.toGBK(request.getParameter("xh")); // 学号
			String bz = DealString.toGBK(request.getParameter("bz"));// 备注
			String nd = DealString.toGBK(request.getParameter("nd")); // 年度
			String bbsj = DealString.toGBK(request.getParameter("bbsj"));// 补办时间
			String jbr = DealString.toGBK(request.getParameter("jbr")); // 经办人
			String bbyy = DealString.toGBK(request.getParameter("bbyy"));// 补办原因
			String sflq = DealString.toGBK(request.getParameter("sflq")); // 是否领取
			String sqsj = DealString.toGBK(request.getParameter("sqsj")); // 申请时间
			String sfzh = DealString.toGBK(request.getParameter("sfzh")); // 身份证号
			String syd = DealString.toGBK(request.getParameter("syd")); // 生源地
			String xz = DealString.toGBK(request.getParameter("xz"));// 学制
			String csrq = DealString.toGBK(request.getParameter("csrq")); // 出生日期
			String hczm = DealString.toGBK(request.getParameter("hczm")); // 火车站名
			
			//需要将申请时间和补办时间字符串的'-'去掉
			bbsj = bbsj.replace("-", "");
			sqsj = sqsj.replace("-", "");
			
			boolean judge = false;

			judge = StandardOperation.insert("xszbbb", new String[] { "xn",
					"xq", "xh", "bz", "nd", "bbsj", "jbr", "bbyy", "sflq",
					"sqsj", "sfzh", "syd", "xz", "csrq", "hczm" },
					new String[] { xn, xq, xh, bz, nd, bbsj, jbr, bbyy, sflq,
							sqsj, sfzh, syd, xz, csrq, hczm }, request);

			if (judge) {
				request.setAttribute("result", "ok");
	
			} else {
				request.setAttribute("result", "no");
			}
		}

		// 查询数据
		sql = "select * from view_xsxxb where " + pk + "='" + xh + "'";
		String[] colList = dao
				.getColumnName("select * from view_xsxxb where 1=2"); // 返回列名数组
		String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
			}
		}
		map.put("xn", dqndq[0]);// xn
		map.put("xq", dqndq[1]);// xq
		map.put("nd", dqndq[2]);// nd
		request.setAttribute("ndList", dao.getNdList()); // 发送年度列表
		request.setAttribute("xnList", dao.getXnndList()); // 发送学年列表
		request.setAttribute("xqList", dao.getXqList()); // 发送学期列表
		
		sql = "select jbrgh,jbrxm from jbrxxb order by jbrgh";  //经办人信息表
		List jbrList = dao.getList(sql, new String[] {}, new String[] {
				"jbrgh", "jbrxm" });
		request.setAttribute("jbrList", jbrList);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}
	//学生证补办-修改-重庆科技
	private ActionForward xszbbUpdateCqkj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
//		String sql = "";
//		String doType = request.getParameter("doType");
//		HashMap<String, String> map = new HashMap<String, String>();
//		String pk = "xh";
//		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xn = DealString.toGBK(request.getParameter("xn")); // 学年
		String xq = DealString.toGBK(request.getParameter("xq"));// 学期
               xh = DealString.toGBK(request.getParameter("xh")); // 学号
		String bz = DealString.toGBK(request.getParameter("bz"));// 备注
		String nd = DealString.toGBK(request.getParameter("nd")); // 年度
		String bbsj = DealString.toGBK(request.getParameter("bbsj"));// 补办时间
		String jbr = DealString.toGBK(request.getParameter("jbr")); // 经办人
		String bbyy = DealString.toGBK(request.getParameter("bbyy"));// 补办原因
		String sflq = DealString.toGBK(request.getParameter("sflq")); // 是否领取
		String sqsj = DealString.toGBK(request.getParameter("sqsj")); // 申请时间
		String sfzh = DealString.toGBK(request.getParameter("sfzh")); // 身份证号
		String syd = DealString.toGBK(request.getParameter("syd")); // 生源地
		String xz = DealString.toGBK(request.getParameter("xz"));// 学制
		String csrq = DealString.toGBK(request.getParameter("csrq")); // 出生日期
		String hczm = DealString.toGBK(request.getParameter("hczm")); // 火车站名
		
//		需要将申请时间和补办时间字符串的'-'去掉
		bbsj = bbsj.replaceAll("-", "").replace("年", "").replace("月", "").replace("日", "").trim();
		sqsj = sqsj.replaceAll("-", "").replace("年", "").replace("月", "").replace("日", "").trim();
		
		boolean judge = false;

		judge = StandardOperation.update("xszbbb", 
				new String[] { "xn","xq", "xh", "bz", "nd", "bbsj", "jbr", "bbyy", "sflq",
						"sqsj", "sfzh", "syd", "xz", "csrq", "hczm" },
				new String[] { xn, xq, xh, bz, nd, bbsj, jbr, bbyy, sflq,
						sqsj, sfzh, syd, xz, csrq, hczm },
				"xh",
				xh, 
				request);

		if (judge) {
			request.setAttribute("result", "ok");

		} else {
			request.setAttribute("result", "no");
		}
		request.setAttribute("rs", map);
		request.setAttribute("ndList", dao.getNdList()); // 发送年度列表
		request.setAttribute("xnList", dao.getXnndList()); // 发送学年列表
		request.setAttribute("xqList", dao.getXqList()); // 发送学期列表
		
		
		String sql = "select jbrgh,jbrxm from jbrxxb order by jbrgh";  //经办人信息表
		List jbrList = dao.getList(sql, new String[] {}, new String[] {
				"jbrgh", "jbrxm" });
		request.setAttribute("jbrList", jbrList);
		return mapping.findForward("success");
	}

}
