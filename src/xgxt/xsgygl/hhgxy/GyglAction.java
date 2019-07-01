package xgxt.xsgygl.hhgxy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDropDownList;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.xsgygl.action.XsgyglForm;
import xgxt.xsgygl.dao.gyglDao;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 淮海工学院学生信息管理公寓管理-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */
public class GyglAction extends DispatchAction {

	/**
	 * @describe 值日生管理
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward zrsgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_hh_gygl_zrsgl";
		String realTable = "hhgxy_zrsgl";
		String title = "公寓管理 - 值班记录 - 值日生管理";

		ArrayList<String[]> rs = null;
		XsgyglForm myForm = (XsgyglForm) form;
		GyglService service = new GyglService();

		// 获得查询条件
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		// 查询是否点击查询按钮
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 获得表头
			topTr = service.getZrsglTopTr();
			// 获得查询结果列表
			rs = service.getZrsglList(myForm);
			// 防止返回页面姓名乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("zrsgl");
	}

	/**
	 * @describe 值日生管理
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward zrsglEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("type");
		String pk = "xn||xq||xh";
		String title = "公寓管理 - 值班记录 - 值日生管理维护";
		String lddm = request.getParameter("lddm");
		String pkValue = request.getParameter("pkValue");
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String zs = dao.getOneRs("  select xqzs from xtszb", new String[]{}, "xqzs");
		String userType = dao.getUserType(userDep);
		// String userName =
		// request.getSession().getAttribute("userName").toString();
		String xxdm = dao.getXxdm();
		//String tableName = "view_gygl_jqlxxx";
		String realTable = "gygl_jqlxxxb";
		String[] colList = { "xn", "xq", "xqmc", "xh", "xb", "xm", "nj",
				"xymc", "zymc", "bjmc", "lddm", "ldmc", "qsh", "zskssj",
				"zsjssj", "lxdh", "lxyy", "bz" };
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		boolean del = false;

		// XsgyglForm dataSearchForm = (XsgyglForm)form;
		GetDropDownList getList = new GetDropDownList();
		String lc = null;
		String ssbh = "";
		List<HashMap<String, String>> ldList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> lcList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> qshList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> cwhList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> xhInfo = new HashMap<String, String>();
		HashMap<String, String> zrInfo = new HashMap<String, String>();
		List<HashMap<String, String>> xhList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> zrList = new ArrayList<HashMap<String, String>>();

		sql = "select lddm,ldmc from sslddmb ";
		sql += " order by  lddm ";
		ldList = dao.getList(sql, new String[] {}, new String[] { "lddm",
				"ldmc" });
		lcList = getList.GetLcList(lddm,"");
		qshList = getList.GetQshList2(lddm, lc,"");
		cwhList = getList.GetCwhList(ssbh);

		if ("add".equalsIgnoreCase(doType) || "".equalsIgnoreCase(doType)
				|| doType == null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], "");
			}
			map.put("xn", Base.currXn);// 添加时默认系统设置年月
			map.put("xq", Base.currXq);
		} else if ("modi".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {

			String pkV = request.getParameter("pkValue").replace(" ", "");

			sql = "select * from (select distinct (xh), xn, xq, (select t.xm"
					+ " from view_xsxxb t where t.xh = a.xh) xm, (select t.bjmc from view_xsxxb t where t.xh = a.xh) bjmc,"
					+ " ssbh, max(ltrim(sys_connect_by_path('第' || (zs) || '周', '、'),  '、')) zs from (select t.xn,"
					+ " t.xq, t.xh, t.SSBH,t.ZS, row_number() over(partition by b.xh order by t.xh) pno,"
					+ " row_number() over(partition by b.xh order by t.xh) - 1 sno from hhgxy_zrsgl t, hhgxy_zrsgl b"
					+ " where t.zs = b.zs and t.xh = b.xh and t.xn || t.xq || t.ssbh ='"
					+ pkV
					+ "' group by t.xn, t.xq, t.XH, t.SSBH, t.ZS, b.xh) a"
					+ " start with pno = 1 connect by prior pno = sno and prior a.xh = a.xh group by xh, xn, xq, ssbh)"
					+ "  order by zs";

			colList = new String[] { "xh", "xq", "ssbh" };
			String[] outputSQL = new String[] { "xh", "xm" };
			zrList = dao.getList(sql, new String[] {}, outputSQL);
			List<HashMap<String, String>> rsList = dao.getList(sql,
					new String[] {}, colList);
			HashMap<String, String> tempMap = new HashMap<String, String>();
			if (rsList.size() != 0) {
				tempMap = rsList.get(0);
			}
			ssbh = tempMap.get("ssbh");

			String xqmc = CommonQueryDAO.getXqMc(Base.currXq);
			
			
			sql = "select xh, xm from view_xszsxx where ssbh = '"
					+ ssbh
					+ "' and xh not in(select xh from (select distinct (xh), xn, xq, (select t.xm"
					+ " from view_xsxxb t where t.xh = a.xh) xm, (select t.bjmc from view_xsxxb t where t.xh = a.xh) bjmc,"
					+ " ssbh, max(ltrim(sys_connect_by_path('第' || (zs) || '周', '、'),  '、')) zs from (select t.xn,"
					+ " t.xq, t.xh, t.SSBH,t.ZS, row_number() over(partition by b.xh order by t.xh) pno,"
					+ " row_number() over(partition by b.xh order by t.xh) - 1 sno from hhgxy_zrsgl t, hhgxy_zrsgl b"
					+ " where t.zs = b.zs and t.xh = b.xh and t.xn || t.xq || t.ssbh ='"
					+ pkV
					+ "' group by t.xn, t.xq, t.XH, t.SSBH, t.ZS, b.xh) a"
					+ " start with pno = 1 connect by prior pno = sno and prior a.xh = a.xh group by xh, xn, xq, ssbh))";
			xhList = dao.getList(sql, new String[] {}, outputSQL);
			
			map.put("xn", Base.currXn);// 添加时默认系统设置年月
			map.put("xqmc", xqmc);
			map.put("xq", Base.currXq);
			map.put("ssbh", ssbh);
			map.put("zs", zs);
		} else {
			del = StandardOperation.delete(realTable, pk, pkValue, request);
			if (del) {
				request.setAttribute("result", "ok");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/xgxt/holidayPutUpInfo.do", false);
		}
		String xh = request.getParameter("xh");// 住宿纪律信息添加时，获得所选学号
		String[] colListV = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc" };
		String[] rsV = dao
				.getOneRs(
						"select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?",
						new String[] { xh }, colListV);
		if (rsV != null) {
			for (int i = 0; i < colListV.length; i++) {
				map.put(colListV[i], rsV[i]);
			}
			// 默认学生所住寝室
			String[] ldQshTem = dao.getOneRs(
					"select lddm, qsh from view_xszsxx where xh=?",
					new String[] { xh }, new String[] { "lddm", "qsh" });
			if (ldQshTem != null) {
				map.put("lddm", (Base.isNull(ldQshTem[0]) ? "" : ldQshTem[0]));
				map.put("qsh", (Base.isNull(ldQshTem[1]) ? "" : ldQshTem[1]));

			}
		}
		if (!Base.isNull(map.get("lddm"))) {
			lddm = map.get("lddm");
		}
		request.setAttribute("ldList", ldList);
		request.setAttribute("lcList", lcList);
		request.setAttribute("qshList", qshList);
		request.setAttribute("cwhList", cwhList);
		request.setAttribute("doType", doType);
		request.setAttribute("xhInfo", xhInfo);
		request.setAttribute("xhList", xhList);
		request.setAttribute("zrInfo", zrInfo);
		request.setAttribute("zrList", zrList);
		request.setAttribute("zs", zs);
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		gyglDao.getLdLcQshList(request);
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("title", title);
		return mapping.findForward("zrsglEdit");
	}

	/**
	 * @describe 值日生管理
	 * @author luo
	 * @throws Exception
	 */
	public static ActionForward zrsglSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();

		String title = "公寓管理 - 值班记录 - 值日生管理维护";
		// String realTable = "hhgxy_zrsgl";
		// HashMap<String, String> map = new HashMap<String, String>();

		String pk = request.getParameter("pk");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String ssbh = request.getParameter("ssbh");
		String zs = dao.getOneRs("  select xqzs from xtszb", new String[] {},
		"xqzs");
		String zsV = DealString.toGBK(request.getParameter("zsV"));
		String[] xh = pk.split("!!SplitSignOne!!");
		String[] zsT = zsV.split("!!SplitSignOne!!");

		StringBuffer sbdel = new StringBuffer();
		String[] pksql = new String[] {};
		String sqldel = "";
		for (int i = 0; i < xh.length; i++) {
			String pkV = xn + xq + ssbh;// 得到主键
			sqldel = "delete from hhgxy_zrsgl where xn||xq||ssbh = '" + pkV
					+ "'";
			// 把主键组合成sql语句
			sbdel.append(sqldel);
			sbdel.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sbdel.toString().split("!!#!!");
		dao.runBatch(pksql);

		if (xh[0] != null &&!"".equals(xh[0])) {
			StringBuffer sbins = new StringBuffer();
			String sqlins = "";
			if (zs != null && !"".equals(zs)) {
				for (int i = 0; i < xh.length; i++) {
					String xhV = DealString.toGBK(xh[i]);// 得到主键
					String[] zsU = zsT[i].split(" ：");
					String[] zsW = zsU[1].split("周,");
					for (int j = 0; j < zsW.length; j++) {
						sqlins = "insert into hhgxy_zrsgl (xn,xq,xh,ssbh,zs)"
								+ "values('" + xn + "','" + xq + "','" + xhV
								+ "','" + ssbh + "','"
								+ zsW[j].replace("周", "") + "')";
						sbins.append(sqlins);
						sbins.append("!!#!!");
					}
				}
			}
			pksql = sbins.toString().split("!!#!!");
			dao.runBatch(pksql);
		}

		request.setAttribute("result", "ok");
		request.setAttribute("title", title);

		return mapping.findForward("success");
	}

	/**
	 * @describe 值日生成绩
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward zrscj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "公寓管理 - 值班记录 - 值日生值周成绩维护";
		DAO dao = new DAO();
		// DtjszjcmService service = new DtjszjcmService();
		HashMap<String, String> rs = new HashMap<String, String>();

		String pk = DealString.toGBK(request.getParameter("pk"));
		String type = DealString.toGBK(request.getParameter("type"));
		String realTable = "hhgxy_zrsgl";
		
		String[] colList = new String[] { "xn", "xqmc","xq", "xh", "xm", "ssbh", "zs",
				"dj", "cj" };

		String sql = "select a.xn, (select xqmc from xqdzb where xqdm = a.xq) xqmc,a.xq, a.xh, "
				+ " a.xm, a.ssbh, a.zs, b.dj, a.cj from view_hh_gygl_zrsgl a, gywsjcb b where "
				+ " a.SSBH||a.zs  = b.ssbh||b.zs and a.xn=b.xn and a.xq=b.xq and b.xn || b.xq ||b.ssbh || b.jcsj=?";
		 if (pk != null && !"".equals(pk)) {
			rs = dao.getMap(sql, new String[] { pk }, colList);
			String ssbh = rs.get("ssbh");

			if (rs.get("zs") != null && !"".equals(rs.get("zs"))) {
				String zs = "第" + rs.get("zs") + "周";
				rs.put("zs1", zs);
			}

			if (ssbh != null && !"".equals(ssbh)) {
				String[] ss = ssbh.split("-");
				ssbh = ss[0] + "号楼" + ss[1] + "寝室";
				rs.put("ssbh1", ssbh);
			}

		}
		if (type.equals("save")) {
			String xn = request.getParameter("xn");
			String xq = request.getParameter("xq");
			String xh = request.getParameter("xh");
			String ssbh = request.getParameter("ssbh");
			String zs = request.getParameter("zs");
			String cj = request.getParameter("cj");
			String pkValue = xn + xq + xh + ssbh + zs;
			boolean inserted = StandardOperation.update(realTable,
					new String[] { "cj" }, new String[] { cj },
					"xn||xq||xh||ssbh||zs", pkValue, request);
			if(inserted){
				request.setAttribute("result", "ok");
				rs.put("xh", " ");
			}else{
				request.setAttribute("result", "no");
			}
		}
		// // 判断是否按增加按钮
		// if (pk != null && !"".equals(pk)) {
		// // 获得发展对象信息
		// rs = service.getFzdxOne(pk);
		// String db = DealString.toGBK(request.getParameter("db"));
		// // 判断是否鼠标双击
		// if (db != null && !"".equals(db)) {
		// request.setAttribute("db", db);
		// }
		// request.setAttribute("doType", "modi");
		// request.setAttribute("type", "update");
		// } else {
		// request.setAttribute("doType", "add");
		// rs.put("xh", "");
		// request.setAttribute("rsZjcm", rsZjcm);
		// }

		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("zrscj");
	}
	
	/**
	 * @describe 公寓德育考评
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward gydykp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_hh_gygl_zrsgl";
		String realTable = "hhgxy_zrsgl";
		String title = "公寓管理 - 值班记录 - 淮海公寓德育管理";
		
		String lddm = request.getParameter("lddm");
		String lc = null;
		String sql="";

		ArrayList<String[]> rs = null;
		XsgyglForm myForm = (XsgyglForm) form;
		GyglService service = new GyglService();
		DAO dao= new DAO();

		List<HashMap<String, String>> ldList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> lcList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> qshList = new ArrayList<HashMap<String, String>>();
		
		GetDropDownList getList = new GetDropDownList();

		sql = "select lddm,ldmc from sslddmb ";
		sql += " order by  lddm ";
		ldList = dao.getList(sql, new String[] {}, new String[] { "lddm",
				"ldmc" });
		lcList = getList.GetLcList(lddm,"");
		qshList = getList.GetQshList2(lddm, lc,"");
		
		// 获得查询条件
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		// 查询是否点击查询按钮
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 获得表头
			topTr = service.getGydykpTopTr();
			// 获得查询结果列表
			rs = service.getGydykpList(myForm);
			// 防止返回页面姓名乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("ldList", ldList);
		request.setAttribute("lcList", lcList);
		request.setAttribute("qshList", qshList);
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("gydykp");
	}
	
	/**
	 * @describe 公寓德育考评打印
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward gydykpPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglService service = new GyglService();
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/gydykp.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		
		String pk = DealString.toGBK(request.getParameter("pk"));
		service.printPayReport(wwb,pk);
		
		return mapping.findForward("");
	}
	
	/**
	 * @describe 查询符合条件的寝室
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward gywmqs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "hhgxy_wmqs";
		String realTable = "hhgxy_wmqs";
		String title = "公寓管理 - 信息维护 - 文明寝室评比";
		
		String lddm = request.getParameter("lddm");
		String lc = null;
		String sql="";

		ArrayList<String[]> rs = null;
		XsgyglForm myForm = (XsgyglForm) form;
		GyglService service = new GyglService();

		List<HashMap<String, String>> ldList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> lcList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> qshList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		
		GetDropDownList getList = new GetDropDownList();

		sql = "select lddm,ldmc from sslddmb ";
		sql += " order by  lddm ";
		DAO dao=new DAO();
		ldList = dao.getList(sql, new String[] {}, new String[] { "lddm",
				"ldmc" });
		lcList = getList.GetLcList(lddm,"");
		qshList = getList.GetQshList2(lddm, lc,"");
		
		// 获得查询条件
		String xy = myForm.getXydm();
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		List<HashMap<String, String>> topTr = null;

		// 查询是否点击查询按钮
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			
			String zs = dao.getOneRs("  select xqzs from xtszb", new String[] {},
			"xqzs");
			if (zs == null || "".equalsIgnoreCase(zs)
					|| "0".equalsIgnoreCase(zs)) {
				String msg = "请在系统设置处设置本学期的周时数";
				request.setAttribute("msg", msg);
				rs1.put("lddm", " ");
				rs1.put("lc", " ");
				rs1.put("qsh", " ");
				request.setAttribute("rs1", rs1);
				request.setAttribute("ldList", ldList);
				request.setAttribute("lcList", lcList);
				request.setAttribute("qshList", qshList);
				request.setAttribute("xydm", xy);			
				request.setAttribute("xnList", Base.getXnndList());
				request.setAttribute("xqList", Base.getXqList());
				commonRequestSet(request, tableName, realTable, rs, topTr, title);
				return mapping.findForward("gywmqs");
			}
			// 获得表头
			topTr = service.getGywmqsTopTr();
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", "iswmqs");
			map.put("cn", "是否文明寝室");
			topTr.add(map);
			// 获得查询结果列表
			rs = service.getWmqsZgList(myForm);
			// 防止返回页面姓名乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			rs1.put("lddm", myForm.getLddm());
			rs1.put("lc", myForm.getLc());
			rs1.put("qsh", myForm.getQsh());
			rs1.put("xn", myForm.getXn());
			rs1.put("xq", myForm.getXq());
		}else{
			rs1.put("xn", Base.currXn);
			rs1.put("xq", Base.currXq);
		}
		
		request.setAttribute("rs1", rs1);
		request.setAttribute("ldList", ldList);
		request.setAttribute("lcList", lcList);
		request.setAttribute("qshList", qshList);
		request.setAttribute("xydm", xy);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("gywmqs");
	}
	
	/**
	 * @describe 文明寝室评选
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward gywmqspx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, Object> mapList = new HashMap<String, Object>();
		HashMap<String, String> rs = new HashMap<String, String>();
		Vector<Object> rsList = new Vector<Object>();
		GyglService service = new GyglService();
		
		String tableName = "hhgxy_wmqs";
		String title = "公寓管理 - 信息维护 - 文明寝室评比";

		String pk = DealString.toGBK(request.getParameter("pk"));
		String pkall = DealString.toGBK(request.getParameter("pkall"));
		String type = DealString.toGBK(request.getParameter("type"));
		String xn = "";
		String xq = "";
		String ssbh = "";
		String avgfs = "";
		String pbzq = "";
		String pbdj = "";
		
		if (pk != null && !"".equals(pk)) {

			String[] tempPk = pk.split("!!SplitSignOne!!");
			xn = tempPk[0].trim();
			xq = tempPk[1].trim();
			ssbh = tempPk[2].trim();
			avgfs = tempPk[3].trim();
			pbzq = service.getPbzq(pk);
			pbdj = service.getPbdj(pk);

			if (service.isWmqs(tableName, pk, request)) {
				request.setAttribute("wmsq", "yes");
			} else {
				request.setAttribute("wmsq", "no");
			}
		}
		
		if ("save".equalsIgnoreCase(type)) {
			String msg = service.maxWmqs(pk);
			if (msg == null || "".equals(msg)) {
				pbdj = DealString.toGBK(request.getParameter("pbdj"));
				boolean inserted = service.saveWmqs(tableName, pk,pbdj, request);
				if (inserted) {
					request.setAttribute("inserted", "ok");
				} else {
					request.setAttribute("inserted", "no");
				}
			} else {
				request.setAttribute("msg", msg);
			}
		} else if ("del".equalsIgnoreCase(type)) {
			boolean inserted = service.delWmqs(tableName, pk, request);
			if (inserted) {
				request.setAttribute("inserted", "ok");
				
			} else {
				request.setAttribute("inserted", "no");
			}

		} else if ("allsave".equalsIgnoreCase(type)) {
			String msg = "设置成功";
			String errorMsg = service.addAllWmqs(pkall);
			request.setAttribute("msg", "".equals(errorMsg) ? msg : errorMsg);
			return new ActionForward("/XsgyglHhDispatch.do?method=gywmqs&go=go&writeAble=yes");
		} else if ("alldel".equalsIgnoreCase(type)) {
			String msg="设置成功";
			service.delAllWmqs(pkall);
			request.setAttribute("msg", msg);
			return new ActionForward("/XsgyglHhDispatch.do?method=gywmqs&go=go&writeAble=yes");
		}

		List<HashMap<String, String>> wsjcList = service.getWsjcList(pk);
		List<HashMap<String, String>> wjList = service.getWjList(pk);
		rs.put("xn", xn);
		rs.put("xq", xq);
		rs.put("ssbh", ssbh);
		rs.put("avgfs", avgfs);
		rs.put("pbzq", pbzq);
		rs.put("pbdj", pbdj);
		mapList.put("wsjcList", wsjcList);
		mapList.put("wjList", wjList);
		rsList.add(mapList);

		request.setAttribute("rsList", rsList);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);

		return mapping.findForward("gywmqspx");
	}
	
	/**
	 * @describe 文明寝室打印
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward gywmqsPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglService service = new GyglService();
		String pk = request.getParameter("pk");
		String modelPath = servlet.getServletContext().getRealPath("")
				+ "/print/hhgxy_wmqs.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.printWmqsReport(wwb,pk);

		return mapping.findForward("");
	}
	
	private void commonRequestSet(HttpServletRequest request, String tableName,
			String realTable, ArrayList<String[]> rs, List topTr, String title) {
		// Request存值的通用方法
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
}