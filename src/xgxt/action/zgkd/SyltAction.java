package xgxt.action.zgkd;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.zgkd.bkManage.BkWeiHuModel;
import xgxt.action.zgkd.bkManage.BkglyPpModel;
import xgxt.action.zgkd.bkManage.bkManageService;
import xgxt.action.zgkd.userReg.UserRegModel;
import xgxt.action.zgkd.userReg.UserRegService;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:大学生生涯论坛Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author:
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2008-7-4 下午04:15:28
 * </p>
 */
public class SyltAction extends DispatchAction {
	// 论坛首页打开
	public ActionForward wjdcresult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> wjdclist = new ArrayList<HashMap<String, String>>();
		sql = "select distinct(bt) from jygl_wjdcb";
		String[] btinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "bt" });
		String bt = "";
		if (null != btinfo) {
			bt = btinfo[0];
		}
		map.put("bt", bt);
		sql = "select sum(times) from jygl_wjdcb";
		String[] judgeinfo = dao.getOneRs(sql, new String[] {},
				new String[] { "sum(times)" });
		if (null != judgeinfo) {
			String sum = judgeinfo[0];
			String sql1 = "";
			if ("0".equals(sum)) {
				sql1 = "1";
			} else {
				sql1 = "select sum(times) from jygl_wjdcb";
			}
			sql = "select a.choice,a.times,round(to_number((a.times/( " + sql1
					+ " )) * 100),2) bili from jygl_wjdcb a";
			wjdclist = dao.getArrayList(sql, new String[] {}, new String[] {
					"choice", "times", "bili" });
		}

		request.setAttribute("bt", map);
		request.setAttribute("wjdclist", wjdclist);
		return mapping.findForward("success");
	}

	// 论坛各版块列表
	public ActionForward syltbklb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		syltForm.getPages().setPageSize(20);
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		
		SyltService service = SyltService.getSyltService();
		// 检验操作权限
		String bk = DealString.toGBK(request.getParameter("bk")).trim(); // 得到板块的名字;
		boolean power = service.checkUserOperatePower(userName, bk);
		String userType = (String) session.getAttribute("userType");
		String powerNum = service.checkUserPower(userName);
		if (power || userType.equalsIgnoreCase("admin")
				|| powerNum.equalsIgnoreCase("2")) {
			// 表示分配给该管理员的权限或是超级管理员，即admin
			request.setAttribute("power", "yes"); // 有权限
		} else {
			request.setAttribute("power", "no"); // 没有权限
		}
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		HashMap<String, String> rs2 = new HashMap<String, String>();
		// String dlm = request.getParameter("userName"); //得到登陆名
		StringBuffer sqlBf = new StringBuffer();
		/*
		 * 一个板块下有很多的帖子，对于每块帖子下有很多的评论， 根据顶贴的最后时间来显示帖子在页面上的顺序
		 */
		
        /*
         * 搜索 searchtype:1、标题 2、作者 searchtype2:1、人气 2、精华 3、全部
         */
		String querry = " ";
		String search = DealString.toGBK(request.getParameter("search"));
		String searchtype = DealString.toGBK(request.getParameter("searchtype"));
		String searchtype2 = DealString.toGBK(request.getParameter("searchtype2"));
		
		if(null!=search&&!"".equalsIgnoreCase(search)&&"1".equalsIgnoreCase(searchtype)){
			querry += " and bt like '%";
			querry +=search;
			querry +="%' ";
			
		}
		if(null!=search&&!"".equalsIgnoreCase(search)&&"2".equalsIgnoreCase(searchtype)){
			querry += " and fbrnc like '%";
			querry +=search;
			querry +="%' ";
		}
		if(null!=searchtype2&&!"".equalsIgnoreCase(searchtype2)&&"1".equalsIgnoreCase(searchtype2)){
			querry += " and lll > ";
			querry +="50";
			querry +=" ";
		}
		if(null!=searchtype2&&!"".equalsIgnoreCase(searchtype2)&&"2".equalsIgnoreCase(searchtype2)){
			querry += " and dj ='";
			querry +="10";
			querry +="' ";
		}
		rs2.put("search", search);
		rs2.put("searchtype", searchtype);
		rs2.put("searchtype2", searchtype2);
		
		
		//
		/*
		 */
		String zdsj = dao
				.getOneRs(
						"select count(*) num from view_sylt_tzpx where zdsj is not null and bk=?",
						new String[] {bk}, "num");
		if (!StringUtils.isNull(zdsj) && !"0".equalsIgnoreCase(zdsj)) {//有置顶时间
			sqlBf.append("select rid,bt,fbrnc,fbsj,hfs,lll,is_zd,dj from (");
			sqlBf.append("select a.*,rownum r from view_sylt_tzpx a where bk=? ");
			sqlBf.append(querry);
			sqlBf.append("order by a.is_zd desc nulls last,zdsj desc nulls last,fbsj desc nulls last,maxfbsj desc nulls last) where r<=");
			sqlBf.append(syltForm.getPages().getStart()
					+ syltForm.getPages().getPageSize());
			sqlBf.append(" and r> ");
			sqlBf.append(syltForm.getPages().getStart());
			sqlBf.append(" ");
		} else {//无置顶时间,按发布时间排序
			sqlBf.append("select rid,bt,fbrnc,fbsj,hfs,lll,is_zd,dj from (");
			sqlBf.append("select a.*,rownum r from view_sylt_tzpx a where bk=? ");
			sqlBf.append(querry);
			sqlBf.append("order by fbsj desc nulls last,a.is_zd desc nulls last,zdsj desc nulls last,maxfbsj desc nulls last) where r<=");
			sqlBf.append(syltForm.getPages().getStart()
					+ syltForm.getPages().getPageSize());
			sqlBf.append(" and r> ");
			sqlBf.append(syltForm.getPages().getStart());
			sqlBf.append(" ");
		}
		String[] outputValue = new String[] { "rid", "bt", "fbrnc", "fbsj",
				"hfs", "lll", "is_zd", "dj" };
		rs = dao.getList(sqlBf.toString(), new String[] { bk }, outputValue);
		// 判断是否是当天发的帖子
		String sysSql = "select to_char(sysdate,'yyyymmdd') curdate from dual";
		String sysDate = dao.getOneRs(sysSql, new String[] {},
				new String[] { "curdate" })[0];
		try {
			for (int i = 0; i < rs.size(); i++) {
				if (rs.get(i).get("fbsj").substring(0, 8).equals(sysDate)) {
					rs.get(i).put("isnew", "1");
				} else {
					rs.get(i).put("isnew", "0");
				}
			}
			for (int i = 0; i < rs.size(); i++) {
				if (rs.get(i).get("is_zd").equals("1")) {
					rs.get(i).put("color", "#FDF1DF");
				} else {
					rs.get(i).put("color", "");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		for (HashMap<String, String> map : rs) {
			if (map.get("fbsj") != null && !"".equals(map.get("fbsj"))) {
				map.put("fbsj", dao.doForTime2(map.get("fbsj")));
			}
		}
		String tempSql = "select count(*) num from syltb where type='主贴' and bk=? and pass='1' "+querry;
		String[] tempArray = dao.getOneRs(tempSql, new String[] { bk },
				new String[] { "num" });
		request.setAttribute("rsNum", (tempArray == null) ? "0" : tempArray[0]);
		syltForm.getPages().setMaxRecord(
				Integer.parseInt(request.getAttribute("rsNum").toString()));
		rs1.put("bk", bk);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs2", rs2);
		return mapping.findForward("success");
	}

	// 发表新帖

	public ActionForward syltaddnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String bk = "";
		String bt = request.getParameter("doType");
		String go = "";
		HttpSession session = request.getSession();
		// String mac = dao.getMac();
		// String ip = dao.getIp();
		String ip = request.getRemoteAddr();
		String nr = "";
		String dlm = session.getAttribute("userName").toString();

		String yhnc = dao.getOneRs("select nc from sylt_yhb where dlm=?",
				new String[] { dlm }, "nc");

		if (null == yhnc || "".equals(yhnc)) {
			request.setAttribute("zhuce", "no");
			go = "no";
			// 表示还没有注册过
			return mapping.findForward("false");
		}

		bk = DealString.toGBK(request.getParameter("bk")).trim();
		bt = DealString.toGBK(request.getParameter("bt"));
		nr = DealString.toGBK(request.getParameter("content1"));

		// check power
		SyltService service = SyltService.getSyltService();
		boolean power = service.checkUserOperatePower(dlm, bk);
		String userType = (String) session.getAttribute("userType");
		String powerNum = service.checkUserPower(dlm);
		if (power || userType.equalsIgnoreCase("admin")
				|| powerNum.equalsIgnoreCase("2")) {
			// 表示分配给该管理员的权限或是超级管理员，即admin
			request.setAttribute("power", "yes"); // 有权限
		} else {
			request.setAttribute("power", "no"); // 没有权限
		}

		request.setAttribute("bk", bk);
		request.setAttribute("bt", bt);
		request.setAttribute("content1", nr);
		request.setAttribute("yhnc", yhnc);

		String sql = "";

		// 判断有没有封ip了
		sql = "select fip from sylt_fip where ip=?";
		String fip = dao.getOneRs(sql, new String[] { ip }, "fip");
		if ("fip".equals(fip)) {
			request.setAttribute("add", "fip");
			go = "no";
		}

		if ("save".equals(doType) && !("no".equals(go))) {
			// 检验验证码
			String yzm = request.getParameter("yzm");
			String standardYzm = request.getSession().getAttribute("yzm")
					.toString();
			if (!yzm.equalsIgnoreCase(standardYzm)) {
				request.setAttribute("checkYzm", "no");
				return mapping.findForward("success");
			}

			String fbr = "";
			String fbrnc = "";

			String[] fbrinfo = dao.getOneRs(
					"select dlm,nc from sylt_yhb where dlm=?",
					new String[] { dlm }, new String[] { "dlm", "nc" });
			if (fbrinfo != null) {
				fbr = fbrinfo[0];
				fbrnc = fbrinfo[1];
			}

			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			// 判断是不是修改后的保存，如果是那么只要更新信息即可
			String view = request.getParameter("view");
			if (!StringUtils.isNull(view)) {
				// update data
				judge = StandardOperation.update("syltb", new String[] { "bk",
						"bt", "nr", "fbr", "fbrnc", "fbsj", "ip", "fbrtype",
						"tplj", "type" }, new String[] { bk, bt, nr, fbr,
						fbrnc, fbsj, ip, "", "jyweb/images/sylt.gif", "主贴" },
						"fbr||bt", fbr + bt, request);
			} else {
				judge = StandardOperation.insert("syltb", new String[] { "bk",
						"bt", "nr", "fbr", "fbrnc", "fbsj", "ip", "fbrtype",
						"tplj", "type" }, new String[] { bk, bt, nr, fbr,
						fbrnc, fbsj, ip, "", "jyweb/images/sylt.gif", "主贴" },
						request);
			}
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
		}

		request.setAttribute("yhnc", yhnc);
		// 看新闻的详细信息
		if ("view".equals(doType) && !("no".equals(go))) {
			String rid = request.getParameter("rid").replaceAll(" ", "+")
					.trim();
			sql = "select * from syltb where rowid = ?";
			String[] newMoreInfo = dao.getOneRs(sql, new String[] { rid },
					new String[] { "fbrnc", "bt", "nr" });
			request.setAttribute("yhnc", newMoreInfo[0]);
			request.setAttribute("bt", newMoreInfo[1]);
			// CLOB clob = dao.getOneClob(sql, new String[]{}, "nr");
			// request.setAttribute("content1",clob);
			request.setAttribute("content1", newMoreInfo[2]);
			// request.setAttribute("content1",newMoreInfo[2]);
			request.setAttribute("view", "ok");
		}
		request.setAttribute("go", go);
		return mapping.findForward("success");
	}

	// 帖子具体内容查看

	public ActionForward syltmoreinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SyltService syltService = SyltService.getSyltService();
		DAO dao = DAO.getInstance();
		SyltDao syltDao = SyltDao.getSyltDao();
		String sql = "";
		String[] colList = null;
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> rs1 = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		pkValue = pkValue.replaceAll(" ", "+");
		String btValue = DealString.toGBK(request.getParameter("btValue"));
		HttpSession session = request.getSession();
		// String
		String dlm = session.getAttribute("userName").toString();
		String ip = request.getRemoteAddr();
		String go = "";
		String fbr = "";
		String fbrnc = "";
		// lll add 1 有标志位判断
		if (StringUtils.isNull(request.getParameter("noadd"))) {
			syltService.addLllOrPll("syltb", "lll", "rowid", pkValue);
			// 刷新屏幕时不能让浏览量加1
			request.setAttribute("noadd", "yes");
		}
		String bk = DealString.toGBK(request.getParameter("bk"));
		String bt = DealString.toGBK(request.getParameter("bt"));
		String nr = DealString.toGBK(request.getParameter("content1"));

		SyltService service = SyltService.getSyltService();
		String userName = session.getAttribute("userName").toString();
		boolean power = service.checkUserOperatePower(userName, bk);
		String userType = (String) session.getAttribute("userType");
		String powerNum = service.checkUserPower(userName);
		if (power || userType.equalsIgnoreCase("admin")
				|| powerNum.equalsIgnoreCase("2")) {
			// 表示分配给该管理员的权限或是超级管理员，即admin
			request.setAttribute("power", "yes"); // 有权限
		} else {
			request.setAttribute("power", "no"); // 没有权限
		}
		String[] bkinfo = dao.getOneRs("select bk,bt from syltb where rowid=?",
				new String[] { pkValue }, new String[] { "bk", "bt" });
		if (bkinfo != null) {
			bk = bkinfo[0];
			bt = bkinfo[1];
			rs1.put("bk", bk);
			rs1.put("bt", bt);
			rs1.put("pkValue", pkValue);
			rs1.put("btValue", btValue);
		}

		// 单个删除 [超级管理员权限]
		if ("del".equals(doType)) {
			String gtpkValue = request.getParameter("gtpkValue").replaceAll(
					" ", "+"); // 跟帖的主键
			boolean judge = false;
			judge = StandardOperation.delete("syltb", "rowid", gtpkValue, // 这个rowid跟帖的
					request);
			/*
			 * if(judge){ syltDao.minusLll("syltb", "hfs", "rowid", pkValue);
			 * //将评论量减1 }
			 */
			request.setAttribute("noadd", "yes");
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		// 全部清空 [超级管理员权限]
		if ("delall".equals(doType)) {
			boolean judge = false;
			// sql = "delete from syltb where rowid=?";
			// judge = dao.runUpdate(sql, new String[]{pkValue});
			sql = "delete from syltb where BTROWID=?";
			judge = dao.runUpdate(sql, new String[] { pkValue });
			// 将这个帖子的评论量设为0
			sql = "update syltb set hfs='0' where rowid=?";
			dao.runUpdate(sql, new String[] { pkValue });
			// judge = dao.runUpdateTab(sql);
			// judge = StandardOperation.delete("syltb", new String[]{"bt"}, new
			// String[]{bt}, request);
			if (judge) {
				request.setAttribute("delall", "ok");
				// rs1 = null;
			} else {
				request.setAttribute("delall", "no");
			}
			request.setAttribute("noadd", "yes");
		}

		// 保存判断及用户验证
		// if ("save".equals(doType)) {
		//			
		// String yzm = session.getAttribute("yzm").toString().toLowerCase();
		// String yzm2 = request.getParameter("yzm").toLowerCase();
		// sql = "select fip from jygl_lyb_fip where ip=?";
		// String fip = dao.getOneRs(sql, new String[] { ip }, "fip");
		// if ("fip".equals(fip)) {
		// request.setAttribute("add", "fip");
		// go = "no";
		// }
		// if (yzm2 == null || !(yzm2.equals(yzm)) || "".equals(yzm2)) {
		// request.setAttribute("add", "no");
		// session.setAttribute("yzm", "");
		// // 留言板内容
		//
		// CommanForm dataSearchForm = (CommanForm) form;
		// sql = "select count(*) count from view_jygl_lyb ";
		// dataSearchForm.getPages().setMaxRecord(
		// Integer.parseInt(dao.getOneRs(sql, new String[] {},
		// "count")));
		//
		// sql = "select * from (select a.*,rownum r from (select distinct
		// a.rowid rid,a.id,a.tplj,a.yhm,a.fbsj,a.ly,a.ip from jygl_lyb a order
		// by a.fbsj ) a ) a where a.r>"
		// + dataSearchForm.getPages().getStart()
		// + " and a.r<="
		// + (dataSearchForm.getPages().getStart() + dataSearchForm
		// .getPages().getPageSize())
		// + " order by a.fbsj ";
		// colList = new String[] { "rid", "id", "tplj", "yhm", "fbsj",
		// "ly", "ip" };
		// rs1 = dao.rsToVator(sql, new String[] {}, colList);
		// rs.addAll(rs1);
		// request.setAttribute("rs", rs);
		// return mapping.findForward("success");
		// }
		// session.setAttribute("yzm", "");
		// }

		String[] fbrinfo = dao.getOneRs(
				"select dlm,nc from sylt_yhb where dlm=?",
				new String[] { dlm }, new String[] { "dlm", "nc" });
		if (fbrinfo != null) {
			fbr = fbrinfo[0];
			fbrnc = fbrinfo[1];
		}

		// 封IP
		if ("fip".equals(doType)) {
			sql = "select ip from syltb where rowid=?";
			ip = dao.getOneRs(sql, new String[] { pkValue }, "ip");
			if (null != ip && !("".equals(ip))) {
				boolean judge = false;
				judge = StandardOperation.insert("sylt_fip",
						new String[] { "ip" }, new String[] { ip }, request);
				if (judge) {
					request.setAttribute("fip", "ok");
				} else {
					request.setAttribute("fip", "no");
				}
			}
		}

		// 留言提交
		if ("save".equals(doType)) {
			sql = "select fip from sylt_fip where ip=?";
			String fip = dao.getOneRs(sql, new String[] { ip }, "fip");
			if ("fip".equals(fip)) {
				request.setAttribute("add", "fip");
				go = "no";
			}
			String standardYzm = request.getSession().getAttribute("yzm")
					.toString(); // 标准的验证码
			String yzm = request.getParameter("yzm"); // 用户输入的验证码
			if (!yzm.equalsIgnoreCase(standardYzm)) {
				request.setAttribute("checkYzm", "no");
			} else if (!("no".equals(go))) {
				// 表明验证码正确
				String fbsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
								new String[] {}, new String[] { "sdate" }))[0];
				boolean judge = false;
				judge = StandardOperation.insert("syltb", new String[] { "bk",
						"bt", "nr", "fbr", "fbrnc", "fbsj", "ip", "fbrtype",
						"tplj", "btrowid" }, // 用于识别是哪个主贴的
						new String[] { bk, bt, nr, fbr, fbrnc, fbsj, ip, "",
								"jyweb/images/1.png", pkValue }, request);
				// 给评论量加1
				/*
				 * if(judge){ syltService.addLllOrPll("syltb", "hfs", "rowid",
				 * pkValue); }
				 */
				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
		}

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from syltb where rowid=?";
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] { pkValue },
						"count")) - 1);

		colList = new String[] { "rid", "tplj", "fbrnc", "fbsj", "nr", "ip",
				"bt","fbr", "type" };
		// 得到主贴的信息
		sql = "select rowid rid,a.* from syltb a where a.rowid = ?";
		String[] zhutieInfo = dao.getOneRs(sql, new String[] { pkValue },
				colList);
		HashMap<String, String> zhutieInfoMap = new HashMap<String, String>();
		if (zhutieInfo != null) {
			for (int i = 0; i < zhutieInfo.length; i++) {
				zhutieInfoMap.put(colList[i], zhutieInfo[i]);
			}
			// 判断是否是本人帖子并具有编辑功能
			if (userName.equalsIgnoreCase(zhutieInfoMap.get("fbr"))) {
				zhutieInfoMap.put("update", "yes");
			}
		}
		if (!StringUtils.isNull(zhutieInfoMap.get("fbsj"))) {
			zhutieInfoMap.put("fbsj", dao.doForTime(zhutieInfoMap.get("fbsj")));
		}
		// 贴子具体内容[留言版信息]
		sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.tplj,a.fbrnc,a.fbsj,a.nr,a.ip,a.bt,a.fbr,a.type from syltb a  where btrowid='"
				+ pkValue
				+ "' order by a.fbsj) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<= "
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by a.fbsj";
		rs = syltDao.rsToVator(sql, new String[] {}, colList);
		dataSearchForm.getPages().setMaxRecord(rs.size());
		rs.add(0, zhutieInfoMap);
		request.setAttribute("bk", bk);
		request.setAttribute("bt", bt);
		// request.setAttribute("content1", nr);
		request.setAttribute("fbrnc", fbrnc);
		request.setAttribute("fbr", dlm);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", rs1);
		return mapping.findForward("success");
	}

	//修改
	public ActionForward syltupdateinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String dlm = session.getAttribute("userName").toString();
		String nc = dao.getOneRs("select nc from sylt_yhb where dlm=?", new String[]{dlm}, "nc");
//		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		if (null != pkValue && "".equalsIgnoreCase(pkValue)) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("update".equalsIgnoreCase(doType)) {
			boolean judge = false;
			String xgsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') sdate from dual", // 发布时间
							new String[] {}, new String[] { "sdate" }))[0];

			String nr = DealString.toGBK(request.getParameter("content1"));
			String bjtag = "";
			if (nr.contains(" ---------]")) {
				bjtag = "再次";
			}
			nr += "<P></P>" + "[--------- 该主题于：" + xgsj + "被" + nc + bjtag
					+ "编辑过 ---------]";
			judge = StandardOperation.update("syltb", new String[] { "nr" },
					new String[] { nr }, "rowid", pkValue, request);

			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}

		String[] colList = new String[] { "rid", "tplj", "fbrnc", "fbsj", "nr",
				"ip", "bt", "fbr", "type" };
		// 得到主贴的信息
		String sql = "select rowid rid,a.* from syltb a where a.rowid = ?";
		String[] zhutieInfo = dao.getOneRs(sql, new String[] { pkValue },
				colList);
		HashMap<String, String> rs = new HashMap<String, String>();
		if (zhutieInfo != null) {
			for (int i = 0; i < zhutieInfo.length; i++) {
				rs.put(colList[i], zhutieInfo[i]);
			}
			if (null != zhutieInfo[4]) {
				request.setAttribute("content1", zhutieInfo[4]);
			}
		}

		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	/** 论坛首页 */
	public ActionForward syltDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		// 得到各版主的信息
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		SyltService service = SyltService.getSyltService();
		// 检验权限
		String power = service.checkUserPower(userName);
		String userType = (String) session.getAttribute("userType");
		if ("admin".equalsIgnoreCase(userType) || power.equalsIgnoreCase("2")) { // 超级管理员
			request.setAttribute("power", "yes");
		} else {
			request.setAttribute("power", "no");
		}
		// List topTr = service.getTalksCN();
		List<HashMap<String, String>> talksList = service.getTalksInfo(); // 得到论坛结果集
		request.setAttribute("talksList", talksList);
		// request.setAttribute("topTr", topTr);
		return mapping.findForward("deFault");
	}

	/**
	 * 用户注册页面
	 */
	public ActionForward userReg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		SyltForm ltForm = (SyltForm) form;
		// UserRegModel model = new UserRegModel();//用户注册数据Model
		// UserRegService service = new UserRegService();//用户注册业务Model
		// BeanUtils.copyProperties(model,
		// ltForm);//UserRegModel复制SyltForm中相关变量值
		// String userType = session.getAttribute("userType").toString();//用户类型
		String userName = session.getAttribute("userName").toString();// 用户登陆名
		ltForm.setYhm(userName);
		// request.setAttribute("gotoPage", "userReg");
		// return mapping.findForward("deFault");
		return mapping.findForward("userReg");
	}

	/**
	 * 注册信息保存
	 * 
	 * @throws Exception
	 */
	public ActionForward regInFoSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		boolean done = false;
		SyltForm ltForm = (SyltForm) form;
		UserRegModel model = new UserRegModel();// 用户注册数据Model
		UserRegService service = new UserRegService();// 用户注册业务Model
		String userType = session.getAttribute("userType").toString();// 用户类型
		String userName = session.getAttribute("userName").toString();// 用户登陆名
		BeanUtils.copyProperties(model, ltForm);// UserRegModel复制SyltForm中相关变量值
		done = service.regToSave(userType, userName, model);
		ltForm.setYhm(userName);
		request.setAttribute("done", done);
		// request.setAttribute("gotoPage", "userReg");
		// return mapping.findForward("deFault");
		return mapping.findForward("userReg");
	}

	/** 板块分配管理页面 */
	public ActionForward bkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		bkManageService service = new bkManageService();
		request.setAttribute("yhList", service.getCommonList(1));// 用户列表
		request.setAttribute("bkList", service.getCommonList(2));// 论坛版块列表
		// request.setAttribute("gotoPage","bkManage");
		// return mapping.findForward("deFault");
		return mapping.findForward("bkMag");
	}

	/**
	 * 管理员对应版块查询结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward glyandbkQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		// BkglyPpModel bkglModel = new BkglyPpModel();//版块管理员匹配MODEL
		// BeanUtils.copyProperties(bkglModel, syltForm);
		syltForm.getPages().setPageSize(5);
		bkManageService service = new bkManageService();
		SyltService syltService = SyltService.getSyltService();
		List<HashMap<String, String>> topList = service.getBkGlyppTitle();// 查询表头
		List<String[]> resList = service.getBkGlyppResult(syltForm);// 查询结果,分页显示
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		String[] columns = new String[] { "GLYM", "BKDM" };
		String[] values = { syltForm.getYhm(), syltForm.getBkdm() };
		String rsNum = syltService.getTotalRsNumByEqual("view_sylt_bkglypp",
				columns, values);
		request.setAttribute("rsNum", rsNum);// 记录数
		syltForm.getPages().setMaxRecord(Integer.parseInt(rsNum));
		request.setAttribute("yhList", service.getCommonList(1));// 用户列表
		request.setAttribute("bkList", service.getCommonList(2));// 论坛版块列表
		String xm = syltForm.getXm();
		request.setAttribute("xm", xm);
		//syltForm.setXm(syltForm.getXm());
		// request.setAttribute("gotoPage", "bkManage");
		// return mapping.findForward("deFault");
		return mapping.findForward("bkMag");
	}

	/**
	 * 管理员版块信息增加页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyandbkAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		bkManageService service = new bkManageService();
		request.setAttribute("yhList", service.getCommonList(1));// 用户列表
		request.setAttribute("bkList", service.getCommonList(2));// 论坛版块列表
		return mapping.findForward("glyandbkadd");
	}

	/**
	 * 先检查用户是否注册再查询该用户其它信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward refreshglyInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		bkManageService service = new bkManageService();
		String yhm = DealString.toGBK(request.getParameter("yhm"));
		boolean bFlag = service.chkYhmisReg(yhm);// 检查用户是否有注册
		if (bFlag) {
			String[] yhList = service.getYhInfo(yhm);// 获取用户其它信息
			if (yhList != null && yhList.length == 2) {
				request.setAttribute("nc", yhList[0]);// 昵称
				request.setAttribute("grqm", yhList[1]);// 个人签名
			}
		} else {
			String[] yhList = service.getYhxm(yhm);
			if (yhList != null && yhList.length == 1) {
				request.setAttribute("nc", yhList[0]);// 姓名
			}
		}
		request.setAttribute("yhList", service.getCommonList(1));// 用户列表
		request.setAttribute("bkList", service.getCommonList(2));// 论坛版块列表
		return mapping.findForward("glyandbkadd");
	}

	/**
	 * 管理员版块信息保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyandbkSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		BkglyPpModel bkglModel = new BkglyPpModel();// 版块管理MODEL
		BeanUtils.copyProperties(bkglModel, syltForm);
		bkManageService service = new bkManageService();
		boolean bFlag = service.bkglInfoSave(bkglModel, request);// 信息保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("yhList", service.getCommonList(1));// 用户列表
		request.setAttribute("bkList", service.getCommonList(2));// 论坛版块列表
		return mapping.findForward("glyandbkadd");
	}

	/**
	 * 管理员对应版式块信息修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyandbkModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		String type = request.getParameter("type");// 信息显示/信息修改
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		bkManageService service = new bkManageService();
		HashMap<String, String> resMap = service.getBkglyPpInfo(pkValue);// 获取相关信息
		syltForm.setBkdm(resMap.get("bkdm"));
		syltForm.setYhm(resMap.get("glym"));
		request.setAttribute("nc", resMap.get("nc"));
		request.setAttribute("grqm", resMap.get("grqm"));
		if (!StringUtils.isNull(type) && StringUtils.isEqual(type, "modi")) {
			request.setAttribute("isModi", "yes");
		}
		request.setAttribute("yhList", service.getCommonList(1));// 用户列表
		request.setAttribute("bkList", service.getCommonList(2));// 论坛版块列表
		request.setAttribute("glym", resMap.get("glym"));
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("glyandbkmodi");
	}

	/**
	 * 管理员对应版式信息批量删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyandbkDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		bkManageService service = new bkManageService();
		String[] keys = syltForm.getCbv();
		String sJg = service.delBkglyPpInfo(keys, request);// 批量删除
		request.setAttribute("isDel", sJg);
		request.setAttribute("yhList", service.getCommonList(1));// 用户列表
		request.setAttribute("bkList", service.getCommonList(2));// 论坛版块列表
		return mapping.findForward("bkMag");
	}

	/**
	 * 管理员对应版式信息修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward glyandbkModifly(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		bkManageService service = new bkManageService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String yhm = DealString.toGBK(request.getParameter("yhm"));
		String bkdm = request.getParameter("bkdm");
		boolean bFlag = service.bkglInfoModi(pkValue, yhm, bkdm, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("yhList", service.getCommonList(1));// 用户列表
		request.setAttribute("bkList", service.getCommonList(2));// 论坛版块列表
		return mapping.findForward("glyandbkmodi");
	}

	/** 板块维护管理 */
	public ActionForward bkWeiHu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		bkManageService service = new bkManageService();
		SyltForm syltForm = (SyltForm) form;
		ArrayList<HashMap<String, String>> topTr = service.getBKWHSearchTitle();
		ArrayList<String[]> rs = service.getBKResult(syltForm);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		// request.setAttribute("gotoPage", "bkWeiHu");
		// return mapping.findForward("deFault");
		return mapping.findForward("bkWH");
	}

	/** 板块添加页面 */
	public ActionForward bkAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("bkadd");
	}

	/**
	 * 板块维护保存
	 * 
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	public ActionForward bkAddSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {
		SyltForm syltForm = (SyltForm) form;
		BkWeiHuModel model = new BkWeiHuModel();// 版块管理MODEL
		BeanUtils.copyProperties(model, syltForm);
		bkManageService service = new bkManageService();
		boolean bFlag = service.bkInfoSave(model, request);// 信息保存
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("yhList", service.getCommonList(1));// 用户列表
		request.setAttribute("bkList", service.getCommonList(2));// 论坛版块列表
		return mapping.findForward("bkadd");
	}

	/** 板块信息修改 */
	public ActionForward bkWeiHuModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		bkManageService service = new bkManageService();
		map = service.viewBKInfo(pkValue);
		request.setAttribute("rs", map);
		return mapping.findForward("bkmodi");
	}

	/** 板块信息查看 */
	public ActionForward bkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		bkManageService service = new bkManageService();
		map = service.viewBKInfo(pkValue);
		request.setAttribute("rs", map);
		return mapping.findForward("bkview");
	}

	/** 板块删除 */
	public ActionForward bkwhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {

		SyltForm syltForm = (SyltForm) form;
		String[] keys = syltForm.getCbv();
		bkManageService service = new bkManageService();
		int del = service.bkInFoDel(keys, request);// 批量删除
		
		
		
		for (int i = 0; i < keys.length; i++) {
			String whichbkmc = DealString.toGBK(keys[i]).trim();// 得到主键
			System.out.print("a" + whichbkmc + "b");
			StandardOperation.delete("syltb", "bk",
					whichbkmc, request);
		}// end for
		
		
		
		request.setAttribute("del", "yes");
		request.setAttribute("delcout", del);
		return new ActionForward("/sylt.do?method=bkWeiHu", false);
	}

	/** 注册用户管理默认页面 */
	public ActionForward regUserManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// request.setAttribute("gotoPage","regUserManage");
		// return mapping.findForward("deFault");
		return mapping.findForward("regUserManage");
	}

	public ActionForward regUserQer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {
		SyltForm syltForm = (SyltForm) form;
//		UserRegModel model = new UserRegModel();// 用户注册管理数据Model
		UserRegService service = new UserRegService();// 用户注册管理业务Model
		// BeanUtils.copyProperties(model,
		// syltForm);//UserRegModel复制SyltForm中相关变量值
		List<HashMap<String, String>> topList = service.getregUserQerTitle();// 查询表头
		List<String[]> resList = service.getRegUserResult(syltForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);// 记录数
		// request.setAttribute("gotoPage","regUserManage");
		// return mapping.findForward("deFault");
		return mapping.findForward("regUserManage");
	}

	public ActionForward regUserDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		String[] keys = syltForm.getCbv();
		UserRegService service = new UserRegService();
		int del = service.regUserDel(keys, request);// 批量删除
		request.setAttribute("del", "yes");
		request.setAttribute("delcout", del);
		return new ActionForward("/sylt.do?method=regUserQer", false);
	}

	public ActionForward delNewByRID(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String newsid = request.getParameter("newsid");
		// System.out.print(newsid);
		if(null!=newsid){
		  newsid=newsid.replace(" ", "+");
		}
		boolean deleteOk = StandardOperation.delete("syltb",
				new String[] { "rowid" }, new String[] { newsid }, request);
		StandardOperation.delete("syltb", new String[]{ "btrowid" }, new String[]{newsid}, request);
		if (deleteOk == true) {
			request.setAttribute("delete", "ok");
		} else {
			request.setAttribute("delete", "no");
		}
		syltbklb(mapping, form, request, response);
		return mapping.findForward("delNewByRID");
	}

	// public ActionForward delCommentByRID(ActionMapping mapping,ActionForm
	// form,HttpServletRequest request,HttpServletResponse response) throws
	// Exception{
	// String pkValue = request.getParameter("pkValue");
	// boolean deleteOk = StandardOperation.delete("syltb", new
	// String[]{"rowid"}, new String[]{pkValue}, request);
	// if(deleteOk == true){
	// request.setAttribute("delete","ok");
	// }else{
	// request.setAttribute("delete","no");
	// }
	// BeanUtils.copyProperties(new CommonForm(),form);
	// syltmoreinfo(mapping, (CommonForm)form, request, response);
	// return mapping.findForward("delCommentByRID");
	// }

	public ActionForward noPassNoteList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String doType = request.getParameter("doType");
		String bkmc = DealString.toGBK(request.getParameter("bk")).trim();
		SyltService service = SyltService.getSyltService();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		boolean power = service.checkUserOperatePower(userName, bkmc);
		String userType = (String) session.getAttribute("userType");
		String powerNum = service.checkUserPower(userName);
		SyltDao syltDao = SyltDao.getSyltDao();
		if (power || userType.equalsIgnoreCase("admin")
				|| powerNum.equalsIgnoreCase("2")) {
			// 表示分配给该管理员的权限或是超级管理员，即admin
			request.setAttribute("power", "yes"); // 有权限
		} else {
			request.setAttribute("power", "no"); // 没有权限
		}
		boolean result = false;
		if (!StringUtils.isNull(doType)) {
			String rid = request.getParameter("rid").trim()
					.replaceAll(" ", "+");
			if (doType.equalsIgnoreCase("pass")) { // 审核通过
				result = syltDao.setNotePass(rid);
			} else if (doType.equalsIgnoreCase("nopass")) { // 审核不通过
				result = syltDao.setNoteNoPass(rid);
			} else if (doType.equalsIgnoreCase("del")) { // 删除帖子
				result = syltDao.delNoPassNote(rid);
			}
			request.setAttribute("result", result == true ? "ok" : "no");
		}
		// 显示没有通过和没有审核的帖子信息
		List<HashMap<String, String>> rs = syltDao.getNoPassNoteList(bkmc);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("bk", bkmc);
		return mapping.findForward("noPassNoteList");
	}

	/** 将板块置顶 */
	public ActionForward noteZd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String newsid = request.getParameter("newsid");
		String sql = "update syltb set is_zd =?,zdsj = to_char(sysdate,'yyyymmddhh24miss') where rowid ='"
				+ newsid + "'";
		String[] input = new String[] { "1" };
		boolean zdOk = dao.runUpdate(sql, input);
		// StandardOperation.update("syltb", sql, request);
		if (zdOk == true) {
			request.setAttribute("notezd", "ok");
		} else {
			request.setAttribute("notezd", "no");
		}
		syltbklb(mapping, form, request, response);
		return mapping.findForward("delNewByRID");
	}

	/** 解除置顶 */
	public ActionForward cancleZd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String newsid = request.getParameter("newsid");
		String sql = "update syltb set is_zd = ? , zdsj=? where rowid ='"
				+ newsid + "'";
		String[] input = new String[] { "0", "0" };
		boolean zdOk = dao.runUpdate(sql, input);
		// StandardOperation.update("syltb", sql, request);
		if (zdOk == true) {
			request.setAttribute("canclezd", "ok");
		} else {
			request.setAttribute("canclezd", "no");
		}
		syltbklb(mapping, form, request, response);
		return mapping.findForward("delNewByRID");
	}

	/** 给帖子评等级 */
	public ActionForward noteDj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String newsid = request.getParameter("newsid");
		String dj = request.getParameter("dj");
		String sql = "update syltb set dj = " + dj + " where rowid ='" + newsid
				+ "'";
		boolean zdOk = dao.runUpdate(sql, new String[] {});
		if (zdOk == true) {
			request.setAttribute("notedj", "ok");
		} else {
			request.setAttribute("notedj", "no");
		}
		syltbklb(mapping, form, request, response);
		return mapping.findForward("delNewByRID");
	}

	/** 将板块置顶 */
	public ActionForward bkSort(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SyltForm syltForm = (SyltForm) form;
		bkManageService service = new bkManageService();
		request.setAttribute("bkList", service.getBkDmMcList());
		request.setAttribute("rs", syltForm);
		return mapping.findForward("bksort");
	}

}