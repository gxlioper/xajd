package xgxt.xsgygl.action;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import oracle.sql.CLOB;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDataInfo;
import xgxt.DAO.GetDropDownList;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.ShgcForm;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.ToolClass;
import xgxt.xsgygl.GyglTyDAO;
import xgxt.xsgygl.GyglTyForm;
import xgxt.xsgygl.GyglTyService;
import xgxt.xsgygl.dao.GyglShareDAO;
import xgxt.xsgygl.dao.gyglDao;
import xgxt.utils.Fdypd;
import xgxt.utils.String.StringUtils;
import common.Globals;

public class XsgyglOperationAction {
	public static String writeAble ="";
	private static boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str.equalsIgnoreCase("all"));
	}
	// 初始化主查询页面
	public static ActionForward initMainPageInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		ShgcForm shgcform = (ShgcForm) form;
		String doType = request.getParameter("doType");
		String rsNum = "";
		String nj = shgcform.getNj(); // 获取年级
		String xy = shgcform.getXydm(); // 获取学院代码
		String zy = shgcform.getZydm(); // 获取专业代码
		String bj = shgcform.getBjdm(); // 获取班级代码
		String xh = shgcform.getXh(); // 获取学号
		Vector<Object> vector = new Vector<Object>();
		List rs = null;
		StringBuffer query = new StringBuffer(); // 查询条件
		query.append(" where 1=1");

		gyglDao.getXyZyBjxx(request); // 获取年级、学院、专业、班级列表
		query = ToolClass.getQuery(nj, "", "", xh, xy, zy, bj, "", "", "", "",
				query);

		// sql = "select distinct lddm, ldmc from sslddmb";
		// List ldList = dao.getList(sql, new String []{}, new String
		// []{"lddm","ldmc"});
		// request.setAttribute("ldList", ldList);

		if ("ssfb".equalsIgnoreCase(doType)) {
			// 宿舍分布信息情况
			if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){//西北二民院
				String zzmm = shgcform.getZzmm();
				String mz = shgcform.getMz();
				String syd = shgcform.getSyd();
				query.append(Base.isNull(zzmm)?"":" zzmm='"+zzmm+"' ");
				query.append(Base.isNull(mz)?"":" mz='"+mz+"' ");
				query.append(Base.isNull(syd)?"":" syd like '%"+syd+"%' ");
				gyglDao gydao = new gyglDao();
				gydao.getOrthList(dao, request);
				request.setAttribute("showXBEMY", "showXBEMY");
			}
			dromDistribut(request, dao, rsNum, rs, query);
			return new ActionForward("/shgc/xsgygl_xsssfbqkb.jsp", false);
		} else if ("whhdjl".equalsIgnoreCase(doType)) {
			// 活动记录维护
			whActiveInfo(request, dao, rsNum, vector, query);
			return new ActionForward("/shgc/xsgygl_hdjlwh.jsp", false);
		} else if ("jxkh".equalsIgnoreCase(doType)) {
			// 学生活动评优绩效考核
			stuActivePyCheck(request, dao, rsNum, vector, query);
			return new ActionForward("/gygl/gygl_xshdpykh.jsp", false);
		} else if ("lczxx".equalsIgnoreCase(doType)) {
			// 楼层长信息管理
			getLczxx(request, dao, rsNum, vector, query);
			return new ActionForward("/gygl/gygl_lczxx.jsp", false);
		} else {
			return mapping.findForward("false");
		}
	}
	private static void getLczxx(HttpServletRequest request, DAO dao,
			String rsNum, Vector<Object> vector, StringBuffer query) {
		String sql;
		String[] colList;
		String[] colListCN;
		String tabName;
		String realtable;
		String tips;
		tips = "公寓管理 - 工作里弄 - 楼层长信息 ";
		request.setAttribute("tips", tips);
		String pk = "xh||rmsj";

		tabName = "hzjy_lczxx"; // 数据表
		realtable = "view_hzjy_lczxx"; // 视图
		sql = "select "
			+ pk
			+ " 主键,rownum 行号,xh,xm,xymc,zymc,bjmc,ssbh,ghhxm,ghhbjmc,ghhxb,ghhssbh from "
			+ realtable + query.toString();
		colList = new String[] { "主键", "行号", "xh", "xm", "xymc", "zymc",
				"bjmc", "ssbh", "ghhxm", "ghhbjmc", "ghhxb", "ghhssbh" };
		colListCN = dao.getColumnNameCN(colList, realtable);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			vector.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (vector == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(vector.size());
			}
		}

		request.setAttribute("rsNum", rsNum);
		request.setAttribute("realTable", tabName);
		request.setAttribute("tableName", realtable);
		request.setAttribute("rs", vector);
		request.setAttribute("topTr", topTr);
	}

	private static void stuActivePyCheck(HttpServletRequest request, DAO dao,
			String rsNum, Vector<Object> vector, StringBuffer query) {
		String sql;
		String[] colList;
		String[] colListCN;
		String tabName;
		String realtable;
		String tips;
		String pk = "xh||xn||xq";
		tips = " 公寓管理 - 工作里弄 - 绩效考核 ";
		request.setAttribute("tips", tips);

		tabName = "hzjy_yxlczsqb"; // 数据表
		realtable = "view_hzjy_yxlczsqb"; // 视图
		sql = "select "
			+ pk
			+ " 主键,rownum 行号,xh,xn,xq,xm,xymc,zymc,bjmc,ssbh,decode(sqzl,'A','优秀楼长','B','优秀层长') sqzl,sfsh from "
			+ realtable + query.toString();
		colList = new String[] { "主键", "行号", "xh", "xn", "xq", "xm", "xymc",
				"zymc", "bjmc", "ssbh", "sqzl", "sfsh" };
		colListCN = dao.getColumnNameCN(colList, realtable);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			vector.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (vector == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(vector.size());
			}
		}

		request.setAttribute("rsNum", rsNum);
		request.setAttribute("realTable", tabName);
		request.setAttribute("tableName", realtable);
		request.setAttribute("rs", vector);
		request.setAttribute("topTr", topTr);
	}

	private static void dromDistribut(HttpServletRequest request, DAO dao,
			String rsNum, List rs, StringBuffer query) {
		String sql;
		String[] colList;
		String tabName;
		String realtable;
		String tips;
		tips = "公寓管理 - 宿舍精神文明建设 - 学生宿舍分布 ";
		request.setAttribute("tips", tips);

		tabName = "xsgygl_xsssfbqkb"; // 数据表
		realtable = "view_xsgygl_xsssfbqkb"; // 视图
		sql = "select * from " + realtable + query.toString();
		colList = new String[] { "xh", "xm", "xymc", "zymc", "bjmc", "ssbh",
				"fdyxm", "szxm", "czxm", "lzxm", "cwh" }; // 显示字段
		// 查询结果
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = dao.getList(sql, new String[] {}, colList);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rsNum", rsNum);
		request.setAttribute("realTable", tabName);
		request.setAttribute("tableName", realtable);
		request.setAttribute("rs", rs);
	}

	// 删除操作
	public static ActionForward delDromDistribut(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tabName = "xsgygl_xsssfbqkb";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		StandardOperation.delete(tabName, pk, pkValue, request);
		return mapping.findForward("success");
	}

	public static ActionForward stuActivePerforCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userOnLine")
		.toString();
		String pk = "xh||xn||xq";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_hzjy_yxlczsqb";
		String realTable = "hzjy_yxlczsqb";
		String sql = "";
		String xh = request.getSession().getAttribute("userName").toString();
		String[] colList = { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
				"sjh", "qsdh", "lddm", "qsh", "sqzl", "sqrzw", "zysj",
				"lztjyj", "xxyj", "sfsh" };
		HashMap<String, String> map = new HashMap<String, String>();
		sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,sjh,qsdh,lddm,qsh,sqzl,sqrzw,zysj,lztjyj,xxyj,sfsh from "
			+ tableName + " where " + pk + "=?";
		String[] setpara = dao.getOneRs(sql, new String[] { pkValue }, colList);

		if ("student".equalsIgnoreCase(userType)) {
			sql = "select * from view_xsgygl_xsssfbqkb where lz=?";
			String tag = dao.returntag(sql, new String[] { xh });
			if ("notempty".equalsIgnoreCase(tag)) {
				request.setAttribute("lz", "lz");
			} else {
				request.setAttribute("ptxs", "ptxs");
			}
		}
		if (setpara == null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], "");
			}
		} else {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], setpara[i]);
			}
		}
		request.setAttribute("pk", pk);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	public static ActionForward stuActivePerforCheckSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableName = "hzjy_yxlczsqb";
		String userType = request.getSession().getAttribute("userOnLine").toString();
		String pk = request.getParameter("pk");
		String pkValue = request.getParameter("pkValue");
		String lztjyj = DealString.toGBK(request.getParameter("lztjyj"));
		String sfsh = DealString.toGBK(request.getParameter("sfsh"));
		String xxyj = DealString.toGBK(request.getParameter("xxyj"));

		if ("teacher".equalsIgnoreCase(userType)) {
			StandardOperation.update(tableName,
					new String[] { "sfsh", "xxyj" },
					new String[] { sfsh, xxyj }, pk, pkValue, request);
			request.setAttribute("result", "view");
		} else {
			StandardOperation.update(tableName, new String[] { "lztjyj" },
					new String[] { lztjyj }, pk, pkValue, request);
			request.setAttribute("result", "view");
		}
		return mapping.findForward("success");
	}

	public static ActionForward delStuActiveInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tabName = "xsgygl_gcln_xshdxxb";
		String pk = "hd_zt||hd_sj";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		StandardOperation.delete(tabName, pk, pkValue, request);
		return mapping.findForward("success");
	}

	public static ActionForward putActivePlan(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		DAO dao = DAO.getInstance();
		ShgcForm msgForm = (ShgcForm) form;
		String tagId = request.getParameter("tagId");
		String sql = "select hd_id,hd_title,hd_part,hd_pubtime,hd_puber from "
			+ "xsgygl_hdjhb where hd_part=? order by hd_id";
		List rs = dao.getList(sql, new String[] { tagId }, new String[] {
				"hd_Id", "hd_title", "hd_part", "hd_pubtime", "hd_puber" });
		request.setAttribute("rs", rs);

		String hd_id = request.getParameter("hd_Id");
		if (!isNull(hd_id)) {
			sql = "select hd_title,hd_cont from "
				+ "xsgygl_hdjhb where hd_id=?";
			CLOB clob = dao.getOneClob(sql, new String[] { hd_id }, "hd_cont");
			String hd_Tit = (dao.getOneRs(sql, new String[] { hd_id },
					new String[] { "hd_title" }))[0];
			request.setAttribute("hd_cont", clob.getSubString(1L, (int) clob
					.length()));
			request.setAttribute("hd_tit", hd_Tit);
			request.setAttribute("isModi", "yes");
			request.setAttribute("hd_Id", hd_id);
		} else {
			request.setAttribute("hd_cont", "");
			request.setAttribute("hd_tit", "");
			request.setAttribute("isModi", "no");
			request.setAttribute("hd_Id", "");
		}

		msgForm.setXmdm(tagId);
		request.setAttribute("modList", dao.getModelList());
		return mapping.findForward("success");
	}

	public static ActionForward SaveHdjh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		ShgcForm msgForm = (ShgcForm) form;
		HttpSession session = request.getSession();
		String ssmk = msgForm.getXmdm();
		String hd_Title = DealString.toGBK(request.getParameter("hd_Title"));
		String sContent = DealString.toGBK(request.getParameter("content1"));
		String tagId = request.getParameter("tagId");
		String hd_puber = session.getAttribute("userName").toString();
		String isModi = request.getParameter("isModi");
		String hd_Id = request.getParameter("hd_Id");
		hd_Title = isNull(hd_Title) ? "无标题" : hd_Title;
		String act = request.getParameter("act");
		if (!isNull(act) && act.equalsIgnoreCase("del") && !isNull(hd_puber)) {
			StandardOperation.delete("xsgygl_hdjhb", "hd_id", hd_Id, request);
		} else {
			if (!isNull(isModi) && isModi.equalsIgnoreCase("yes")) {
				dao.updateNews(hd_Title, ssmk, sContent, hd_puber, hd_Id);
			} else {
				String id = dao.getOneRs("select s_hd_id.nextval ID from dual",
						new String[] {}, "ID");
				String[] columns = { "HD_ID", "HD_TITLE", "HD_PART", "HD_CONT",
				"HD_PUBER" };
				String[] values = { id, hd_Title, ssmk, sContent, hd_puber };
				StandardOperation.insert("xsgygl_hdjhb", columns, values,
						request);
			}
		}
		return new ActionForward("active_plan_manage.do?tagId=" + tagId, true);
	}

	public static ActionForward showPlanContent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String tagId = request.getParameter("hd_Id");
		String[] tit = new String[] { "hd_Id", "hd_title", "hd_part",
				"hd_pubtime", "hd_puber", "hd_cont" };
		String sql = "select hd_id,hd_title,hd_part,hd_pubtime,hd_puber,hd_cont from "
			+ "xsgygl_hdjhb where hd_id=?";
		String[] rs = dao.getOneRs(sql, new String[] { tagId }, tit);
		rs = (rs == null) ? new String[tit.length] : rs;
		for (int i = 0; i < tit.length; i++) {
			request.setAttribute(tit[i], isNull(rs[i]) ? " " : rs[i]);
		}
		CLOB clob = dao.getOneClob(sql, new String[] { tagId }, "hd_cont");
		request.setAttribute("hd_cont", clob.getSubString(1L, (int) clob
				.length()));
		return mapping.findForward("success");
	}

	public static ActionForward showActivePlanList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String tagId = request.getParameter("tagId");
		String sql = "select hd_id,hd_title,hd_part,hd_pubtime,hd_puber from "
			+ "xsgygl_hdjhb where hd_part=? order by hd_id";
		List rs = dao.getList(sql, new String[] { tagId }, new String[] {
				"hd_Id", "hd_title", "hd_part", "hd_pubtime", "hd_puber" });
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}

	public static ActionForward whDromInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();		
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
//		String lddm = request.getParameter("lddm");
		String pk = "xh";
		// String sql = "";
		String pkValue = request.getParameter("pkValue");
		String realName = "view_xsgygl_xsssfbqkb";
		String[] colList = { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
				"lddm", "qsh", "fdyxm", "sz", "cz", "lz", "cwh", "sshjqk", "bz" };
		map = getMapxx(dao, map, doType, pk, pkValue, realName, colList);

		gyglDao.getLdLcQshList(request);
		getLCZxx(request, dao, pkValue, pk, realName);
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	public static ActionForward whStuActiveInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String pk = "hd_zt||hd_sj";
		String pkValue = request.getParameter("pkValue");
		String[] colList = null;
		String realName = "xsgygl_gcln_xshdxxb";
		if ("modi".equalsIgnoreCase(doType)) {
			request.setAttribute("modi", "modi");
			colList = new String[] { "hd_zt", "hd_xz", "hd_nr", "hd_sj",
					"hd_dd", "hd_dx" };
		} else if ("xshdjl".equalsIgnoreCase(doType)) {
			request.setAttribute("modi", "modi");
			request.setAttribute("xshdjl", "xshdjl");
			colList = new String[] { "hd_zt", "hd_xz", "hd_nr", "hd_sj",
					"hd_dd", "hd_dx", "hd_cyrs", "hd_lczcyqk", "hd_xcqf",
			"hd_xgpg" };
		} else {
			colList = new String[] { "hd_zt", "hd_xz", "hd_nr", "hd_sj",
					"hd_dd", "hd_dx" };
		}

		map = getMapxx(dao, map, doType, pk, pkValue, realName, colList);
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 增加修改操作
	public static ActionForward stuDromDataModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		String lddm = DealString.toGBK(request.getParameter("lddm"));
		String qsh = DealString.toGBK(request.getParameter("qsh"));
		String fdy = DealString.toGBK(request.getParameter("fdyxm"));
		String sz = DealString.toGBK(request.getParameter("sz"));
		String cz = DealString.toGBK(request.getParameter("cz"));
		String lz = DealString.toGBK(request.getParameter("lz"));
		String cwh = DealString.toGBK(request.getParameter("cwh"));
		String hjqk = DealString.toGBK(request.getParameter("sshjqk"));
		StringBuffer ssbh = new StringBuffer();
		ssbh.append(lddm);
		ssbh.append("-");
		ssbh.append(qsh);
		String bz = DealString.toGBK(request.getParameter("bz"));
		boolean del = false;
		del = StandardOperation.delete("xsgygl_xsssfbqkb", "xh", xh, request);
		if (del) {
			StandardOperation.insert("xsgygl_xsssfbqkb", new String[] { "xh",
					"ssbh", "ch", "fdyxm", "sz", "cz", "lz", "sshjqk", "bz" },
					new String[] { xh, ssbh.toString(), cwh, fdy, sz, cz, lz,
					hjqk, bz }, request);
		}
		return mapping.findForward("success");
	}

	public static ActionForward stuActiveDataModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String hdzt = DealString.toGBK(request.getParameter("hd_zt"));
		String hdxz = DealString.toGBK(request.getParameter("hd_xz"));
		String hdnr = DealString.toGBK(request.getParameter("hd_nr"));
		String sj_rq = DealString.toGBK(request.getParameter("hd_sj"));
		String sj_hour = request.getParameter("sj_hour");
		String sj_minute = request.getParameter("sj_minute");
		String sj_second = request.getParameter("sj_second");
		String hddd = DealString.toGBK(request.getParameter("hd_dd"));
		String hddx = DealString.toGBK(request.getParameter("hd_dx"));
		String hdcyrs = DealString.toGBK(request.getParameter("hd_cyrs"));
		String hdlczcyqk = DealString.toGBK(request.getParameter("hd_lczcyqk"));
		String hdxcqf = DealString.toGBK(request.getParameter("hd_xcqf"));
		String hdxgpg = DealString.toGBK(request.getParameter("hd_xgpg"));
		StringBuffer hdsj = new StringBuffer();
		hdsj.append(sj_rq);
		if (!hdsj.toString().contains(":")) {
			hdsj.append("-");
			hdsj.append(sj_hour);
			hdsj.append(":");
			hdsj.append(sj_minute);
			hdsj.append(":");
			hdsj.append(sj_second);
		}
		boolean del = false;
		del = StandardOperation.delete("xsgygl_gcln_xshdxxb", "hd_zt||hd_sj",
				hdzt + hdsj.toString(), request);
		if (del) {
			StandardOperation.insert("xsgygl_gcln_xshdxxb", new String[] { "hd_zt",
					"hd_xz", "hd_nr", "hd_dd", "hd_dx", "hd_sj",
					"hd_cyrs", "hd_lczcyqk", "hd_xcqf", "hd_xgpg" },
					new String[] { hdzt, hdxz, hdnr, hddd, hddx,
					hdsj.toString(), hdcyrs, hdlczcyqk, hdxcqf,
					hdxgpg }, request);
		}
		return mapping.findForward("success");
	}

	public static ActionForward stuActiveInfoManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String sql = "";
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");
		String tabName = "";
		String realtable = "";
		String[] colList = null;
		List rs = null;
		String rsNum = "";
		String Kshdsj = request.getParameter("KShdsj");
		String Jshdsj = request.getParameter("JShdsj");

		if ("stu_active_thing".equalsIgnoreCase(mapping.getParameter())) {
			String tips = "公寓管理 - 工程里弄 - 学生活动记录";
			request.setAttribute("tips", tips);
		} else {
			String tips = "公寓管理 - 工程里弄 - 学生活动信息";
			request.setAttribute("tips", tips);
		}

		tabName = "xsgygl_gcln_xshdxxb";
		realtable = "xsgygl_gcln_xshdxxb";

		if (Kshdsj == null || "".equalsIgnoreCase(Kshdsj)) {
			query.append("and 1=1 ");
		} else {
			query.append("and substr(hd_sj,1,10)>='");
			query.append(Kshdsj);
			query.append("' ");
		}
		if (Jshdsj == null || "".equalsIgnoreCase(Jshdsj)) {
			query.append("and 1=1 ");
		} else {
			query.append("and substr(hd_sj,1,10)<='");
			query.append(Jshdsj);
			query.append("' ");
		}

		sql = "select a.hd_zt||a.hd_sj pkValue,rownum xh,a.* from " + tabName
		+ " a" + query.toString();
		colList = new String[] { "pkValue", "xh", "hd_zt", "hd_xz", "hd_nr",
				"hd_sj", "hd_dd", "hd_dx" };
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = dao.getList(sql, new String[] {}, colList);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("realTable", tabName);
		request.setAttribute("tableName", realtable);
		return mapping.findForward("success");
	}

	/**楼层长分布*/	
	public static ActionForward dromFloorFugDis(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		DAO dao = DAO.getInstance();
//		XsgyglForm myForm = (XsgyglForm) form;
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
//		String userName = session.getAttribute("userName").toString();
		String tableName = "view_gygl_lczfpb";
		String realTable = "gygl_lczfpb";
		String tips = "公寓管理 - 宿舍分配 - 楼层长分配";
		String go = request.getParameter("go");
		String sql = "";
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		String pk = "xn||xq||ssbh";
		String[] colList = { "主键", "行号", "xn", "xq", "nd", "ssbh", "lz", "cz",
		"qsz" };
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nd = request.getParameter("nd");
//		if(Base.isNull(xn)||Base.isNull(xq)){
//		xn = Base.currXn;
//		xq = Base.currXq;
//		nd = Base.currNd;
//		myForm.setXn(xn);
//		myForm.setXq(xq);
//		myForm.setNd(nd);
//		}
		String lddm = request.getParameter("lddm");
		String qsh = DealString.toGBK(request.getParameter("qsh"));
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
				||xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)) {// 杭州职业技术学院,南昌大学科学技术学院
//			lddm = gyglDao.getLddmxXx(userName);
//			myForm.setLddm(lddm);
			
			tableName = "hzy_view_gygl_lczfpb";
			colList = new String[] { "主键", "行号", "xn", "xq", "nd", "ssbh",
					"cz", "qsz" };
		}

		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		if (xn == null || "".equalsIgnoreCase(xn)) {
			querry.append(" and 1=1 ");
		} else {
			querry.append(" and xn='");
			querry.append(xn);
			querry.append("'");
		}
		if (xq == null || "".equalsIgnoreCase(xq)) {
			querry.append(" and 1=1 ");
		} else {
			querry.append(" and xq='");
			querry.append(xq);
			querry.append("'");
		}
		if (nd == null || "".equalsIgnoreCase(nd)) {
			querry.append(" and 1=1 ");
		} else {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("'");
		}
		if (lddm == null || "".equalsIgnoreCase(lddm)) {
			querry.append(" and 1=1 ");
		} else {
			querry.append(" and lddm='");
			querry.append(lddm);
			querry.append("'");
		}
		if (qsh == null || "".equalsIgnoreCase(qsh)) {
			querry.append(" and 1=1 ");
		} else {
			if (Check_Input_Value.check2(qsh)) {
				querry.append(" and qsh='");
				querry.append(qsh);
				querry.append("'");
			}
		}
		String[] colCNList = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colCNList);
		if (go != null && "go".equalsIgnoreCase(go)) {
			sql = "select "
				+ pk
				+ " 主键,rownum 行号,a.xn,a.xq,a.nd,a.ssbh,a.cz,a.qsz,a.lz from "
				+ tableName + " a " + querry.toString();
			vector.addAll(dao.rsToVator(sql, new String[] {}, colList));
			request.setAttribute("rs", vector);
			if (vector == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(vector.size());
			}
		}

		gyglDao.getLdLcQshList(request);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tips", tips);
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("success");
	}

	public static ActionForward addDromFugle(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		// HttpSession session = request.getSession();
		// String xxdm = dao.getXxdm();
		XsgyglForm myForm = (XsgyglForm) form;
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String pkValue = request.getParameter("pkValue");
//		String lddm = request.getParameter("lddm");
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String pk = "xn||xq||ssbh";

		String tableName = "view_gygl_lczfpb";
		// String realTable = "gygl_lczfpb";
		String[] colList = { "xn", "xq", "nd", "ssbh", "ldmc", "lz", "cz",
				"qsz", "qsh" };
		sql = "select xn,xq,nd,ssbh,ldmc,lz,cz,qsz,qsh from " + tableName
		+ " where " + pk + "= ?";
		map = dao.getMap(sql, new String[] { pkValue }, colList);

		getLCZxx(request, dao, pkValue, pk, tableName);
		gyglDao.getLdLcQshList(request);

		if (!Base.isNull(xn)) {
			map.put("xn", xn);
		}
		if (!Base.isNull(xq)) {
			map.put("xq", xq);
		}

		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	}

	public static ActionForward SaveLczxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String realTable = "gygl_lczfpb";
		String pk = "xn||xq||ssbh";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String xn = DealString.toGBK(request.getParameter("xn"));
		String xq = DealString.toGBK(request.getParameter("xq"));
		String nd = request.getParameter("nd");
		String lz = DealString.toGBK(request.getParameter("lz"));
		String cz = DealString.toGBK(request.getParameter("cz"));
		String qsz = DealString.toGBK(request.getParameter("qsz"));
		String ssbh = DealString.toGBK(request.getParameter("ssbh"));
		String[] colList = { "xn", "xq", "nd", "ssbh", "lz", "cz", "qsz" };
		boolean flag = false;

		String tag = dao.returntag("select * from " + realTable + " where "
				+ pk + "= ?", new String[] { pkValue });
		if ("empty".equalsIgnoreCase(tag)) {
			flag = StandardOperation.insert(realTable, colList, new String[] {
					xn, xq, nd, ssbh, lz, cz, qsz }, request);
		} else {
			flag = StandardOperation.update(realTable, colList, new String[] {
					xn, xq, nd, ssbh, lz, cz, qsz }, pk, pkValue, request);
		}
		if (flag) {
			request.setAttribute("result", "ok");
		} else {
			request.setAttribute("result", "no");
		}
		return mapping.findForward("success");
	}

	public static ActionForward StuActiveActorApply(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userOnLine")
		.toString();
//		String tableName = "view_hzjy_yxlczsqb";
		// String realTable = "hzjy_yxlczsqb";
		String sql = "";
		String xh = "";
//		String xn = Base.currXn;
//		String xq = Base.currXq;
//		String lddm = request.getParameter("lddm");
//		String[] colList = { "sjh", "qsdh", "lddm", "qsh", "sqzl", "sqrzw",
//				"zysj", "lztjyj", "xxyj" };
		HashMap<String, String> map = new HashMap<String, String>();
		if ("student".equalsIgnoreCase(userType)) {
			xh = request.getSession().getAttribute("userName").toString();
		} else {
			xh = request.getParameter("xh");
		}
		sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,lddm,qsh,qsdh from view_xszsxx where xh=?";
		map = dao.getMap(sql, new String[] { xh }, new String[] { "xh", "xm",
				"xb", "nj", "xymc", "zymc", "bjmc","lddm","qsh","qsdh" });
//		sql = "select sjh,qsdh,lddm,qsh,sqzl,sqrzw,zysj,lztjyj,xxyj from "
//		+ tableName + " where " + "xh=? and xn=? and xq=?";
//		String[] qtxx = dao.getOneRs(sql, new String[] { xh, xn, xq }, colList); // 活动评优其它信息
//		if (qtxx == null) {
//		for (int i = 0; i < colList.length; i++) {
//		map.put(colList[i], "");
//		}
//		} else {
//		for (int i = 0; i < colList.length; i++) {
//		map.put(colList[i], qtxx[i]);
//		}
//		}

		gyglDao.getLdLcQshList(request);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	public static ActionForward dataSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String tableName = "hzjy_yxlczsqb";
		String curr_xn = Base.currXn;
		String curr_xq = Base.currXq;
		String xh = request.getParameter("xh");
		String sjh = request.getParameter("sjh");
		String qsdh = request.getParameter("qsdh");
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		String sqzl = request.getParameter("sqzl");
		String sqrzw = DealString.toGBK(request.getParameter("sqrzw"));
		String zysj = DealString.toGBK(request.getParameter("zysj"));
		String lztjyj = DealString.toGBK(request.getParameter("lztjyj"));
		String xxyj = DealString.toGBK(request.getParameter("xxyj"));
		String ssbh =dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");
		String[] colList = { "xh", "sjh", "qsdh", "ssbh", "sqzl", "sqrzw",
				"zysj", "lztjyj", "xxyj", "xn", "xq" };
		String[] valList = { xh, sjh, qsdh, ssbh, sqzl, sqrzw, zysj, lztjyj,
				xxyj, curr_xn, curr_xq };
		boolean del = false;
		del = StandardOperation.delete(tableName, "xn||xq||xh", curr_xn
				+ curr_xq + xh, request);
		if (del) {
			StandardOperation.insert(tableName, colList, valList, request);
		}
		return mapping.findForward("success");
	}

	public static ActionForward floorFugBasicInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = "xh||rmsj";
		String tableName = "hzjy_lczxx";
		String realName = "view_hzjy_lczxx";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String[] colList = { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
				"lddm", "qsh", "sjh", "qsdh", "drzw", "rmsj", "lzsj", "lzyy" };
		if ("del".equalsIgnoreCase(doType) || doType == null) {
			StandardOperation.delete(tableName, pk, pkValue, request);
		}

		map = getMapxx(dao, map, doType, pk, pkValue, realName, colList);

		gyglDao.getLdLcQshList(request);
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	public static ActionForward floorFugBasicInfoSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String tableName = "hzjy_lczxx";
		String pk = "xh||rmsj";
		String pkValue = request.getParameter("pkValue");
		String xh = request.getParameter("xh");
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		String sjh = request.getParameter("sjh");
		String qsdh = request.getParameter("qsdh");
		String drzw = DealString.toGBK(request.getParameter("drzw"));
		String rmsj = request.getParameter("rmsj");
		String lzsj = request.getParameter("lzsj");
		String lzyy = DealString.toGBK(request.getParameter("lzyy"));
		String ghhxh = request.getParameter("ghhxh");
		String ghhlddm = request.getParameter("ghhlddm");
		String ghhqsh = request.getParameter("ghhqsh");
		String ghhsjh = request.getParameter("ghhsjh");
		String ghhqsdh = request.getParameter("ghhqsdh");
		String ghhssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{ghhlddm,ghhqsh},"ssbh");
		String ssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");
		String[] inCloumn = { "xh", "ssbh", "sjh", "qsdh", "drzw", "rmsj",
				"lzsj", "lzyy", "ghhxh", "ghhssbh", "ghhsjh", "ghhqsdh" };
		String[] inValue = { xh, ssbh, sjh, qsdh, drzw, rmsj, lzsj, lzyy,
				ghhxh, ghhssbh, ghhsjh, ghhqsdh };
		boolean del = false;
		if ("".equalsIgnoreCase(pkValue) || pkValue == "") {
			del = StandardOperation.delete(tableName, "xh||rmsj", xh + rmsj,
					request);
		} else {
			del = StandardOperation.delete(tableName, pk, pkValue, request);
		}
		if (del) {
			StandardOperation.insert(tableName, inCloumn, inValue, request);
		}
		request.setAttribute("result", "view");
		return mapping.findForward("success");
	}

	public static ActionForward holidayPutUpInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String tableName = "view_gygl_jqlxxx";
		String realTable = "gygl_jqlxxxb";
		XsgyglForm gyglform = (XsgyglForm) form;
		HttpSession session  = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String pk = "xn||xq||xh";
		String go = request.getParameter("go");
		String tips = "公寓管理 - 信息维护 - 假期留校信息维护";
		String sql = "";
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String xh = request.getParameter("xh");
		String xsxb = DealString.toGBK(gyglform.getXb());
		String mz = gyglform.getMz();
		String jg = DealString.toGBK(gyglform.getJg());
		String userOnLine=session.getAttribute("userOnLine").toString();
		gyglform.setJg(jg);
		gyglform.setXb(xsxb);
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1");
		String xxdm = dao.getXxdm();
		String userDep = request.getSession().getAttribute("userDep")
		.toString();
		String userType = dao.getUserType(userDep);
		if("student".equalsIgnoreCase(userOnLine)){
			request.setAttribute("textxh", userName);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		}
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			xydm = Fdypd.fdybjck(userName,xydm);
		}
		gyglform.setXydm(xydm);
		querry = ToolClass.getQuery("", xn, xq, xh, xydm, zydm, bjdm, "", "",
				"", "", querry);
		
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			//辅导员登录
			querry.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
		}	
		String[] colList = { "主键", "行号", "xn", "xqmc", "xh", "xm", "bjmc",
				"ldmc","qsh", "lxdh", "zskssj", "zsjssj", "lxyy" };
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){						
			gyglDao gydao = new gyglDao();
			request.setAttribute("showxbemy","showxbemy");				
			gydao.getOrthList(dao, request);
			querry.append(Base.isNull(xsxb)?"":" and xb ='"+xsxb+"' ");
			querry.append(Base.isNull(mz)?"":" and mz ='"+mz+"' ");
			querry.append(Base.isNull(jg)?"":" and jg ='"+jg+"' ");
			colList = new String[]{ "主键", "行号", "xn", "xqmc", "xh", "xm","xb","jg","mz", "bjmc",
					"ldmc","qsh", "lxdh", "zskssj", "zsjssj", "lxyy" };
		}	

		String[] CNcolList = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, CNcolList);
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		if ("go".equalsIgnoreCase(go)) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
				StringBuffer query = new StringBuffer();
				query.append(" where 1=1");
				query.append(Base.isNull(xh)?"":" and a.xh like '%"+xh+"%' ");
				query.append(Base.isNull(xn)?"":" and a.xn ='"+xn+"' ");
				query.append(Base.isNull(xq)?"":" and a.xq ='"+xq+"' ");
				query.append(Base.isNull(xydm)?"":" and a.xydm ='"+xydm+"' ");
				query.append(Base.isNull(zydm)?"":" and a.zydm ='"+zydm+"' ");
				query.append(Base.isNull(bjdm)?"":" and a.bjdm ='"+bjdm+"' ");
				
				tableName="view_hh_gygl_jqlxsq";
				colList = new String[] { "主键", "行号", "xn","xq", "xh", "xm", "xb",
						"bjmc", "ssbh","cwh", "lxdh", "zskssj", "zsjssj", "lxyy" };
				sql = "select "
						+ "a.xn || a.xq || a.xh"
						+ " 主键,rownum 行号,a.xn, (case when a.xq = '01' then '春' when a.xq = '02' then '秋' end) xq,a.xh,a.xm,a.xb,a.bjmc,b.ssbh,b.cwh,a.lxdh,a.zskssj,a.zsjssj,(case when a.lxyy is null then '' else substr(a.lxyy,1,10)||'...' end )lxyy from "
						+ tableName + " a ,gygl_jqlxxxb b " + query.toString() +" and xxsh='通过' and b.ssbh <>'-' and a.xh||a.xn||a.xq=b.xh||b.xn||b.xq";
				HashMap<String, String> map=new HashMap<String, String>();		
				map.put("en", "cwh");
				map.put("cn", "床位号");
				topTr.add(8, map);
			}else{
			sql = "select " + pk + " 主键,rownum 行号,xn,xqmc,xh,xm,xb,jg,mz,bjmc,ldmc,qsh,lxdh,zskssj,zsjssj,(case when lxyy is null then '' else substr(lxyy,1,10)||'...' end )lxyy from " + tableName
			+ " a " + querry.toString();
			}
			vector.addAll(dao.rsToVator(sql, new String[] {}, colList));
			rsNum = vector == null ? "0" : String.valueOf(vector.size());
		}
		gyglDao.getXyZyBjxx(request);
		gyglDao.getLdLcQshList(request);
		
		request.setAttribute("userType", userType);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", vector);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		request.setAttribute("pk", pk);
		request.setAttribute("path", "holidayPutUpInfo.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("tips", tips);
		return mapping.findForward("success");
	}

	public static ActionForward MainHolidayLsxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String pk = "xn||xq||xh";
		String lddm = request.getParameter("lddm");
		String pkValue = request.getParameter("pkValue");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName=session.getAttribute("userName").toString();
		String userOnLine=session.getAttribute("userOnLine").toString();
//		String userName = request.getSession().getAttribute("userName").toString();
		String xxdm = dao.getXxdm();
		String tableName = "view_gygl_jqlxxx";
		String realTable = "gygl_jqlxxxb";
		String[] colList = { "xn", "xq","xqmc", "xh", "xb", "xm", "nj", "xymc",
				"zymc", "bjmc", "lddm", "ldmc", "qsh", "zskssj", "zsjssj",
				"lxdh", "lxyy", "bz" };		
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		boolean del = false;
		
		//XsgyglForm dataSearchForm = (XsgyglForm)form;
		GetDropDownList getList = new GetDropDownList();
		String lc = null;
		String ssbh = "";
		List<HashMap<String, String>> ldList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> lcList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> qshList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> cwhList = new ArrayList<HashMap<String, String>>();
		sql = "select lddm,ldmc from sslddmb ";
		sql +=" order by  lddm ";
		ldList = dao.getList(sql, new String[] {}, new String[] {"lddm", "ldmc" });		
		lcList =getList.GetLcList(lddm,"");
		qshList = getList.GetQshList2(lddm,lc,"");	
		cwhList = getList.GetCwhList(ssbh);	
		
		if ("add".equalsIgnoreCase(doType) || "".equalsIgnoreCase(doType)
				|| doType == null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], "");
			}
			map.put("xn",Base.currXn);//添加时默认系统设置年月
			map.put("xq",Base.currXq);
		} else if ("modi".equalsIgnoreCase(doType)||"view".equalsIgnoreCase(doType)) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
				sql = "select a.xn, a.xq,(case when a.xq='01' then '春' else '秋' end) xqmc,a.xh,a.xb,a.xm,a.nj,a.xymc,a.zymc,a.bjmc,b.ssbh,a.zskssj,a.zsjssj,"
						+ " a.lxdh,a.lxyy,b.bz,b.cwh from view_hh_gygl_jqlxsq a,gygl_jqlxxxb b where a.xn||a.xq||a.xh =? "
						+ " and a.xn||a.xq||a.xh = b.xn||b.xq||b.xh";
				colList = new String[] { "xn", "xq","xqmc", "xh", "xb", "xm", "nj",
						"xymc", "zymc", "bjmc", "ssbh", "zskssj", "zsjssj",
						"lxdh", "lxyy", "bz","cwh" };	
				map = dao.getMap(sql, new String[] { pkValue }, colList);
				ssbh = map.get("ssbh");
				if(ssbh!=null&&!"".equals(ssbh)){
				String[] bh = ssbh.split("-");
				String cwh=map.get("cwh");
				ssbh = bh[0] + "号楼" + bh[1]+ "/" + cwh + "床";;
				}
				map.put("ssbh", ssbh);
				
				String xh=map.get("xh");
				HashMap<String, String> tempmap1 = dao.getMap(
						"select * from xszsxxb where xh=?", new String[] { xh },
						new String[] { "ssbh", "cwh" });
				ssbh = tempmap1.get("ssbh");
				String yssbh="";
				if(ssbh!=null&&!"".equals(ssbh)){
					String cwh = tempmap1.get("cwh");
					String bh[] = ssbh.split("-");
					yssbh = bh[0] + "号楼" + bh[1] + "/" + cwh + "床";
				}
				map.put("yssbh", yssbh);
				
			}else{
			sql = "select * from " + tableName + " where " + pk + "=?";	
			map = dao.getMap(sql, new String[] { pkValue }, colList);
			}
		} else {
			del = StandardOperation.delete(realTable, pk, pkValue, request);
			if (del) {
				request.setAttribute("result", "ok");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/holidayPutUpInfo.do", false);
		}
		
		String xh = request.getParameter("xh");// 住宿纪律信息添加时，获得所选学号
		if("student".equalsIgnoreCase(userOnLine)){
			xh=userName;
		}
		String[] colListV = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc" };
		String[] rsV = dao.getOneRs("select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?",
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
		if(!Base.isNull(map.get("lddm"))){
			lddm = map.get("lddm");
		}
		
		request.setAttribute("ldList", ldList);
		request.setAttribute("lcList",lcList);
		request.setAttribute("qshList",qshList);
		request.setAttribute("cwhList",cwhList);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		gyglDao.getLdLcQshList(request);
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("success");
	}

	public static ActionForward SaveHolidayLsxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
//		HttpSession session = request.getSession();
		String realTable = "gygl_jqlxxxb";
		String pk = "xn||xq||xh";
		String pkValue = request.getParameter("pkValue");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		String lxdh = request.getParameter("lxdh");
		String zskssj = request.getParameter("zskssj");
		String zsjssj = request.getParameter("zsjssj");
		String lxyy = DealString.toGBK(request.getParameter("lxyy"));
		String bz = DealString.toGBK(request.getParameter("bz"));
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {// 杭州职业技术学院，根据登录用户（即楼长）查找其管理楼栋代码
//			String userName = session.getAttribute("userName").toString();
//			String fzld = gyglDao.getLddmxXx(userName);
//			if (!("".equalsIgnoreCase(fzld) || fzld == null)) {
//				lddm = fzld;
//			}
		}
		String ssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");
		String cwh = "";
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
			ssbh = request.getParameter("ssbh");
			String bh[] = ssbh.split("-");
			ssbh = bh[0] + "-" + bh[1];
			cwh = bh[2];
		}
		String[] colList = { "xn", "xq", "xh", "ssbh", "zskssj", "zsjssj",
				"lxdh", "lxyy", "bz" };
		boolean del = false;
		boolean insert = false;

		if ("".equalsIgnoreCase(pkValue) || pkValue == null) {
			del = StandardOperation.delete(realTable, pk, xn + xq + xh, request);
		} else {
			del = StandardOperation.delete(realTable, pk, pkValue, request);
		}
		if (del) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
				insert = StandardOperation.insert(realTable, new String[] {
						"xn", "xq", "xh", "ssbh", "zskssj", "zsjssj", "lxdh",
						"lxyy", "bz", "cwh" }, new String[] { xn, xq, xh, ssbh,
						zskssj, zsjssj, lxdh, lxyy, bz,cwh }, request);
			}else{
			insert = StandardOperation.insert(realTable, colList, new String[] { xn, xq, xh,
					ssbh, zskssj, zsjssj, lxdh, lxyy, bz }, request);
			}
		}

		if (insert) {
			request.setAttribute("result", "ok");
		} else {
			request.setAttribute("result", "no");
		}
		return mapping.findForward("success");
	}

	public static ActionForward ManagerEmptyDormInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		DAO dao = DAO.getInstance();
		XsgyglForm myForm = (XsgyglForm) form;
		HttpSession session = request.getSession();
//		String sql = "";
		String kxqk = request.getParameter("kxqk");
		String tips = "公寓管理 - 信息维护 - 空闲宿舍管理";
		String go = request.getParameter("go");
		String tableName = "";
		// String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
//		String userName = session.getAttribute("userName").toString();
		String[] colList = null;
		String[] CNcolList = null;
		String rsNum = "";
		List topTr = null;
		StringBuffer sqlV = new StringBuffer();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		String xxdm = StandardOperation.getXxdm();
		String lc = request.getParameter("cs");
		String xqdm = request.getParameter("xqdm");
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {			
			request.setAttribute("showhzy", "showhzy");
		}

		StringBuffer querry = new StringBuffer(" where 1=1 ") ;
		//===========2010/8/4 edit by luojw =====================
		querry.append((Base.isNull(lddm)) ? " and  1=1": " and lddm='" + lddm + "'");
		querry.append((Base.isNull(xqdm)) ? " and  1=1": " and xqdm='" + xqdm + "'");
		querry.append((Base.isNull(qsh)||"qsh".equalsIgnoreCase(qsh)) ? " and 1=1": " and qsh='" + qsh + "'");
		querry.append((Base.isNull(lc)||"lc".equalsIgnoreCase(lc) ? " and 1=1" : " and cs ='"+ lc + "' "));

		//===========2010/8/4 end=====================
		if ("kxssinfo".equalsIgnoreCase(kxqk)) {
			tableName = "view_hzy_skxxx";
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				colList = new String[] {"r", "xqmc", "ldmc", "qsh","cws", "fpqk" };
				CNcolList = new String[]{"行号","校区","楼栋名称","寝室号","总床位数","班级(划分床位数)"};
				topTr = dao.arrayToList(colList, CNcolList);
				sqlV.append("select rownum r,cws,ldmc,qsh,(case when fpqk='()'then replace(fpqk,'()','') else fpqk end)fpqk,xqmc from (select ssbh,cws,ldmc,qsh,fpqk,xqmc from "+tableName+"  "+querry+" order by lddm ,qsh ) ");
			}else{
				tableName = "view_skxxx";
				if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
					colList = new String[] {"r", "xqmc", "yqmc","ldmc", "qsh","cws", "fpqk"};
					CNcolList = new String[]{"行号","校区","园区","楼栋","寝室号","总床位数","年级 院系 划分床位数"};
					topTr = dao.arrayToList(colList, CNcolList);
					sqlV.append("select rownum r,cws,ldmc,qsh,fpqk,xqmc,yqmc from (select ssbh,cws,ldmc,qsh,fpqk,xqmc,yqmc from "+tableName+"  "+querry+" order by lddm ,qsh ) ");
				}else{
					colList = new String[] {"r", "xqmc", "ldmc", "qsh","cws", "fpqk" };
					CNcolList = new String[]{"行号","校区","楼栋","寝室号","总床位数","年级院系床位数划分情况"};
					topTr = dao.arrayToList(colList, CNcolList);
					sqlV.append("select rownum r,cws,ldmc,qsh,fpqk,xqmc from (select ssbh,cws,ldmc,qsh,fpqk,xqmc from "+tableName+"  "+querry+" order by lddm ,qsh ) ");
				}
			}
				String zcwshj = dao.getOneRs(" select sum(nvl(cws,0))zcws from  "+tableName+" "+querry, new String[]{},"zcws");
		        request.setAttribute("zcwshj",zcwshj);
		} else if ("kxcwinfo".equalsIgnoreCase(kxqk)) {
			tableName = "view_ckxxx";
			if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
				colList = new String[] {"r","xqmc","yqmc","ldmc", "qsh","cws","sycw"};
				CNcolList = new String[]{"行号","校区","园区","楼栋","寝室号","总床位数","剩余床位"};		
				String zcwshj = dao.getOneRs(" select sum(nvl(cws,0))zcws from  "+tableName+" "+querry, new String[]{},"zcws");
		        request.setAttribute("zcwshj",zcwshj);
		        
			}else{
				colList = new String[] {"r","xqmc","ldmc", "qsh","cws","sycw"};
				CNcolList = new String[]{"行号","校区","楼栋","寝室号","总床位数","剩余床位"};				
			}
			StringBuilder kxcwSql=new StringBuilder();
			kxcwSql.append(" select (cws-yzry)kxcw from ( ");
			kxcwSql.append(" select (select sum(cws) from view_ssxx ");
			kxcwSql.append(querry);
			kxcwSql.append("  )cws,(select count(1) from view_xszsxx ");
			kxcwSql.append(querry);
			kxcwSql.append(")yzry from dual) ");
			String kxcwhj = dao.getOneRs(kxcwSql.toString(), new String[]{},"kxcw");
	        request.setAttribute("kxcwhj",kxcwhj);
			topTr = dao.arrayToList(colList, CNcolList);
			sqlV.append(" select rownum r ,ssbh,lddm,ldmc,qsh,qsdh,yqmc,xqmc,cws,sycw from (select ssbh,lddm,ldmc,qsh,qsdh,yqmc,xqmc,cws,sycw from view_ckxxx  "+querry+" order by lddm,qsh )");
		}

		if ("go".equalsIgnoreCase(go)) {
			
			GyglTyForm model = new GyglTyForm();
			
			try {
				BeanUtils.copyProperties(model, myForm);
			} catch (IllegalAccessException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			
			GyglTyService tyService = new GyglTyService();
			
			rs = tyService.getResultList(dao.rsToVator(sqlV.toString(),
					new String[] {}, colList), model);
			
			request.setAttribute("kxqsNum", model.getPages().getMaxRecord());
		}        
		
		GyglTyDAO gydao = new GyglTyDAO();
		
		String dm = "";
		String value = "";
		String zd = Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm) ? "yqmc||'/'||ldmc"
				: "ldmc";
		if (!Base.isNull(xqdm)) {
			dm = "xqdm";
			value = xqdm;
		}
		List<HashMap<String, String>> xqdmList = gydao.getGyglList("dm_zju_xq", "dm", "xqmc", "", "", "");// 校区
		List<HashMap<String, String>> ldList = gydao.getGyglList("view_bjlh_ldxx", "lddm", zd, "", dm, value);// 楼栋
		List<HashMap<String, String>> csList = gydao.getCsList(lddm);	//层数
		List<HashMap<String, String>> qsList = gydao.getQsList(lddm, lc, "");// 寝室
		
		gyglDao.getLdLcQshList(request);
		
		request.setAttribute("xqdmList", xqdmList);
		request.setAttribute("ldList", ldList);
		request.setAttribute("csList", csList);
		request.setAttribute("qsList", qsList);
			
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("tips", tips);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", tableName);
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("success");
	}

	public static ActionForward ManagerOutPutStuInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
 		DAO dao = DAO.getInstance();
		XsgyglForm gyglform = (XsgyglForm) form;
		HttpSession session = request.getSession();
		
		String userName = (String)session.getAttribute("userName");
		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		Boolean fdyqx = (Boolean)session.getAttribute("fdyQx");
		
		if("xy".equalsIgnoreCase(userType) && !fdyqx){
			gyglform.setXydm(userDep);
			request.setAttribute("isxy", true);
		}
		
		String tips = "公寓管理 - 信息维护 - 外住学生管理";
		String realTable = "gygl_xswzxxb";
		String tableName = "view_gygl_xswzxx";
		String pk = "xh||xn||xq||wzksrq";
		String sql = "";
		
		String[] colList = { "主键","xh", "xn","xqmc","xm", "xb", "bjmc", "lxdh", "wzlxmc", "wzdz" };
		String[] CNcolList = null;
		List topTr = null;
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		String xxdm = dao.getXxdm();
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		String nj = request.getParameter("nj");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String xh = request.getParameter("xh");
		String xm = request.getParameter("xm");
		String wzlxdm = request.getParameter("wzlxdm");
		String xsxb = DealString.toGBK(gyglform.getXb());
		String mz = gyglform.getMz();
		String jg = DealString.toGBK(gyglform.getJg());
		gyglform.setXb(xsxb);
		gyglform.setJg(jg);
		
		if (request.getParameter("print") != null// 南昌大学科技学院外宿证明打印
				&& request.getParameter("print").equalsIgnoreCase("wszm")) {
			HashMap<String, String> map = new HashMap<String, String>();
			colList = new String[] { "xh", "xymc", "zymc", "bjmc", "xm","sjhm","lxdh","lxdzxx"};
			String[] stuInfo = dao.getOneRs("select xh,xymc,zymc,bjmc,xm,sjhm,lxdh,lxdzxx from view_xsjbxx where xh=?",
					new String[] { xh }, colList);
			if (stuInfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i], Base.isNull(stuInfo[i]) ? ""
							: stuInfo[i]);
				}
			}
			map.put("wzyy", dao.getOneRs(
					"select wzyy from gygl_xswzxxb where xh=?",
					new String[] { xh }, "wzyy"));
			map.put("xn", Base.currXn);
			request.setAttribute("rsm", map);
			return new ActionForward("/gygl/ncdxkjxy/wszmPrint.jsp", false);
		}

		StringBuffer querry = new StringBuffer();
		List wzlxList = null;
		querry.append(" where 1=1 ");
		querry = ToolClass.getQuery(nj, xn, xq, xh, xydm, zydm, bjdm, "", "","", wzlxdm, querry);
		if(fdyqx){
			//辅导员登录
			querry.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
		}
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			gyglDao gydao = new gyglDao();
			request.setAttribute("showxbemy","showxbemy");				
			gydao.getOrthList(dao, request);
			querry.append(Base.isNull(xsxb)?"":" and xb ='"+xsxb+"' ");
			querry.append(Base.isNull(mz)?"":" and mz ='"+mz+"' ");
			querry.append(Base.isNull(jg)?"":" and jg ='"+jg+"' ");
			colList = new String[] {"主键","xh","xn","xqmc", "xm", "xb","mz","jg", "bjmc", "lxdh", "wzlxmc", "wzdz" };
		}

		// -----------2010/6/21 edit by luojw ----------
			//中国地大
			String kssj = request.getParameter("kssj");
			String jssj = request.getParameter("jssj");
			
			querry.append(Base.isNull(xm) ? "" : " and xm like '%" + xm + "%' ");
			querry.append(Base.isNull(kssj) ? "" : " and wzksrq >= '" + kssj + "' ");
			querry.append(Base.isNull(jssj) ? "" : " and wzksrq <= '" + jssj + "' ");
	
		// -----------end ----------
		CNcolList = dao.getColumnNameCN(colList, tableName);
		topTr = dao.arrayToList(colList, CNcolList);
		sql = "select distinct wzlxdm,wzlxmc from wzlxdmb";
		wzlxList = dao.getList(sql, new String[] {}, new String[] { "wzlxdm",
		"wzlxmc" });
		
		if ("del".equalsIgnoreCase(doType)) {
			String pkValue = request.getParameter("pkValue");
			boolean del = dao.runUpdate("delete from "+realTable+" where "+pk+"=? ", new String[]{pkValue});
			
			if (del) {
				request.setAttribute("result", "ok");
			} else {
				request.setAttribute("result", "no");
			}
			
			go = "go";
		}
		
		if ("go".equalsIgnoreCase(go)) {

			sql = "select * from (select * from (select * from (select rownum r,"+pk+" 主键, a.* from view_gygl_xswzxx a "
			+ querry.toString()
			+" )"					 
			+ ")where rownum<= "
			+ (gyglform.getPages().getStart() + gyglform.getPages().getPageSize())
			+ ") where r>"
			+ gyglform.getPages().getStart();
			// 分页
			gyglform.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(
					" select count(*) count from  view_gygl_xswzxx a"
					+ querry.toString(),
					new String[] {}, "count" )));
			vector.addAll(dao.rsToVator(sql, new String[] {}, colList));
			rsNum = String.valueOf(vector == null ? "" : vector.size());
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)){
			request.setAttribute("isNCDXKJXY", "XXDM_NCDXKJXY");
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("wzlxList", wzlxList);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", vector);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("path", "outPutstuManager.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("tips", tips);
		FormModleCommon.setNdXnXqList(request);

		return mapping.findForward("success");
	}

	public static ActionForward WhStuOutPutInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
//		XsgyglForm xgyForm = (XsgyglForm)form;
		String sql = "";
		String dest = "success";
		String realTable = "gygl_xswzxxb";
		String tableName = "view_gygl_xswzxx";
		String pk = "xh||xn||xq||wzksrq";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String pkTemp = (String)request.getAttribute("pkTemp");
		
		if(pkTemp != null && !pkTemp.equalsIgnoreCase(pkValue)){
			pkValue = pkTemp;
		}
		
		String xh = request.getParameter("xh");
		String[] colList = { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
				"wzlxdm", "wzyy", "wzdz","xn","xq","wzksrq","jhwzsj","sjhm","lxdh","lxdzxx","jzsfty"};
		HashMap<String, String> map = new HashMap<String, String>();

		if("add".equalsIgnoreCase(doType)){
			if(xh!=null&&!xh.equalsIgnoreCase("")){//获取学生基本信息
				map = dao.getMap("select xh,xm,xb,nj,zymc,bjmc,sjhm,lxdh,lxdzxx from view_xsjbxx where xh=? ", new String[]{xh},
						new String[]{"xh","xm","xb","nj","zymc","bjmc","sjhm","lxdh","lxdzxx"});
			}
			map.put("xn",Base.currXn);//默认学年
			map.put("xq",Base.currXq);//默认学期
		}
		if ("modi".equalsIgnoreCase(doType)||"view".equalsIgnoreCase(doType)) {
			sql = "select * from " + tableName + " where "+pk+"=? ";
			map = dao.getMap(sql, new String[] { pkValue }, colList);
		} 
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("wzlxList", gyglDao.getWzlxList());//外住类型列表
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", map);
		return mapping.findForward(dest);
	}

	public static ActionForward SaveOutPutStuInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();		
		String realTable = "gygl_xswzxxb";
		String xh = request.getParameter("xh");
		String wzlxdm = request.getParameter("wzlxdm");
		String wzyy = DealString.toGBK(request.getParameter("wzyy"));
		String wzdz = DealString.toGBK(request.getParameter("wzdz"));
		String wzksxn = request.getParameter("xn");
		String wzksxq = request.getParameter("xq");
		String wzksrq = request.getParameter("wzksrq");
		String jhwzsj = DealString.toGBK(request.getParameter("jhwzsj"));
		String jzsfty = request.getParameter("jzsfty");
		String sjhm = request.getParameter("sjhm");
		String pkValue = StringUtils.isNull(request.getParameter("pkValue")) ? xh+wzksxn+wzksxq+wzksrq : request.getParameter("pkValue"); 
		
		boolean del = false;
		boolean insert = false;
		del = StandardOperation.delete(realTable, "xh||xn||xq||wzksrq", pkValue, request);
		if (del) {
			String pkTemp = xh+wzksxn+wzksxq+wzksrq;
			insert = StandardOperation.insert(realTable, new String[] { "xh",
					"wzlxdm", "wzyy", "wzdz","xn","xq","wzksrq","jhwzsj","jzsfty","sjhm" }, new String[] { xh, wzlxdm,
					wzyy, wzdz,wzksxn,wzksxq,wzksrq,jhwzsj,jzsfty,sjhm}, request);
			if(insert){
				dao.runUpdate("delete from xszsxxb where xh=? ",new String[]{xh});
			}
			
			request.setAttribute("pkTemp", pkTemp);
		}
		if (insert) {
			request.setAttribute("result", "ok");
		} else {
			request.setAttribute("result", "no");
		}
		return mapping.findForward("success");
	}

	public static ActionForward StuGydykpInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String xxdm = dao.getXxdm();
		StringBuffer querry = new StringBuffer();
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String xh = request.getParameter("Pkxh");
		String xm = dao.getOneRs("select xm from view_xsjbxx where xh=?",new String[] { xh }, "xm");
		//String tableName = "view_xsgydykp";		
		querry.append(Base.isNull(xn)?" and 1=1 ":" and xn= '"+xn+"' ");
		querry.append(Base.isNull(xq)?" and 1=1 ":" and xq='"+xq+"' " );
		String[] colListA = {"pk","l","r","xn","xq","sj","ssbh","jlnr","ryjf" };
		String sqlA ="";
		String sqlB ="";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			sqlA = "select rownum r,a.* from (select xh||xn||xq||sj pk,'xsjlb' l,xn,(select xqmc from xqdzb c where a.xq=c.xqdm) xq,substr(sj,1,8)sj,(select b.ldmc||b.qsh from view_ssxx b where a.ssbh=b.ssbh  )ssbh,(select jlmc from xsjldmb where jldm = jlnr)jlnr,ryjf  from xsjlb a where xh=? "+querry+" order by sj desc ) a ";			
		}else{
			sqlA = "select rownum r,a.* from (select xh||xn||xq||sj pk,'xsjlb' l,xn,(select xqmc from xqdzb c where a.xq=c.xqdm) xq,substr(sj,1,8)sj,(select b.ldmc||b.qsh from view_ssxx b where a.ssbh=b.ssbh  )ssbh ,jlnr,ryjf  from xsjlb a where xh=? "+querry+" order by sj desc ) a ";			
		}

		List rsA = dao.getList(sqlA, new String[] { xh }, colListA);
		String[] colListB = {"pk","l","r","xn","xq","sj","ssbh","cfnr","rykf" };
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			sqlB = " select rownum r,a.* from (select xh||xn||xq||sj pk,'xscfb'l,xn,(select xqmc from xqdzb c where a.xq=c.xqdm)xq,substr(sj,1,8)sj,(select b.ldmc||b.qsh from view_ssxx b where a.ssbh=b.ssbh  )ssbh,(select cfmc from xscfdmb where cfdm = cfnr )cfnr,rykf  from xscfb a where xh=? "+querry+" order by sj desc ) a ";
		}else{
			sqlB = " select rownum r,a.* from (select xh||xn||xq||sj pk,'xscfb'l,xn,(select xqmc from xqdzb c where a.xq=c.xqdm)xq,substr(sj,1,8)sj,(select b.ldmc||b.qsh from view_ssxx b where a.ssbh=b.ssbh  )ssbh,cfnr,rykf  from xscfb a where xh=? "+querry+" order by sj desc ) a ";
		}

		List rsB = dao.getList(sqlB,new String[] { xh }, colListB);
		request.setAttribute("rsa", rsA);
		request.setAttribute("rsb", rsB);
		request.setAttribute("xm", xm);
		request.setAttribute("xh", xh);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());		
		writeAble = (Base.chkUPower(userName,"gydykp.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no";
		return mapping.findForward("success");
	}

	public static ActionForward StuWgRollCall(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] colList = null;
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String pk = "xh||wjsj";
		String pkValue = request.getParameter("pkValue");
		String userName = request.getSession().getAttribute("userName")
		.toString();
		HashMap<String, String> map = new HashMap<String, String>();
		if ("add".equalsIgnoreCase(doType) || "".equalsIgnoreCase(doType)) {
			if (!"".equalsIgnoreCase(xh) && xh != null) {
				colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "sfzh", "csrq", "ldmc", "qsh", "cwh",
				"ssbh" };
				sql = "select distinct a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.sfzh,(select csrq from bks_xsqtxx b where a.xh=b.xh) csrq,c.ldmc,c.qsh,c.cwh,c.ssbh from view_xsjbxx a,view_xszsxx c where a.xh=c.xh and a.xh=?";
				map = dao.getMap(sql, new String[] { xh }, colList);
				if (map.isEmpty()) {
					request.setAttribute("result", "view");
					map.put("xh", xh);
				}
			} else {
				map.put("xh", "");
			}
			map.put("jlr", userName);
		} else if ("modi".equalsIgnoreCase(doType)) {
			colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sfzh", "csrq", "ldmc", "qsh", "cwh", "ssbh",
					"jlr", "wjsj", "wjlbdm", "sysm", "wjsy", "cljg", "bz" };
			sql = "select a.*,a.wjsm sysm from view_zsjlxx a where " + pk
			+ "=?";
			map = dao.getMap(sql, new String[] { pkValue }, colList);
		}
		sql = "select wjlbdm,wjlbmc from gygl_zswjlbdmb";
		List wjlbList = dao.getList(sql, new String[] {}, new String[] {
				"wjlbdm", "wjlbmc" });
		sql = "select distinct wjsydm,wjsymc from gygl_wjsydmb";
		List wjsyList = dao.getList(sql, new String[] {}, new String[] {
				"wjsydm", "wjsymc" });
		List wjxxList = null;
		if ("".equalsIgnoreCase(xh) || xh == null) {
			xh = dao.getOneRs("select distinct xh from zsjlb where " + pk
					+ "=?", new String[] { pkValue }, "xh");
		}
		sql = "select a.wjsj,b.wjlbmc,c.wjsymc,a.cljg,a.jlr from zsjlb a,gygl_zswjlbdmb b,gygl_wjsydmb c where a.wjsy=c.wjsydm and a.wjlbdm=b.wjlbdm and a.xh=?";
		wjxxList = dao.getList(sql, new String[] { xh }, new String[] { "wjsj",
				"wjlbmc", "wjsymc", "cljg", "jlr" });

		request.setAttribute("wjxxList", wjxxList);
		request.setAttribute("wjlbList", wjlbList);
		request.setAttribute("wjsyList", wjsyList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", map);
		request.setAttribute("doType", doType);
		return mapping.findForward("success");
	}

	public static ActionForward SaveZsjlxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String doType = request.getParameter("doType");
		String realTable = "zsjlb";
		String xn = Base.currXn;
		String xq = Base.currXq;
		String xh = request.getParameter("xh");
		String wjsj = request.getParameter("wjsj");
		String jlr = DealString.toGBK(request.getParameter("jlr"));
		String wjlbdm = request.getParameter("wjlbdm");
		String wjsydm = request.getParameter("wjsy");
		String sysm = DealString.toGBK(request.getParameter("sysm"));
		String cljg = DealString.toGBK(request.getParameter("cljg"));
		String bz = DealString.toGBK(request.getParameter("bz"));
		String ssbh = request.getParameter("ssbh");
		String pk = "xh||wjsj";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String[] colList = { "xn", "xq", "xh", "wjsj", "jlr", "wjlbdm", "wjsy",
				"wjsm", "cljg", "bz", "ssbh" };
		boolean inser = false;
		if ("add".equalsIgnoreCase(doType)) {
			inser = StandardOperation.insert(realTable, colList,
					new String[] { xn, xq, xh, wjsj, jlr, wjlbdm, wjsydm, sysm,
					cljg, bz, ssbh }, request);
		} else if ("modi".equalsIgnoreCase(doType)) {
			inser = StandardOperation.update(realTable, colList,
					new String[] { xn, xq, xh, wjsj, jlr, wjlbdm, wjsydm, sysm,
					cljg, bz, ssbh }, pk, pkValue, request);
		}
		if (inser) {
			request.setAttribute("result", "ok");
		} else {
			request.setAttribute("result", "no");
		}
		return mapping.findForward("success");
	}

	// //////获取楼层长信息列表 TODO SQ 楼层长在增加住宿信息时不能用。。换成Ajax
	private static void getLCZxx(HttpServletRequest request, DAO dao,
			String pkValue, String pk, String tableName) {
		String sql;
		String lddm = dao.getOneRs("select distinct lddm from " + tableName
				+ " where " + pk + "= ?", new String[] { pkValue }, "lddm");
		String ssbh = dao.getOneRs("select distinct ssbh from " + tableName
				+ " where " + pk + "= ?", new String[] { pkValue }, "ssbh");
		if (dao.getXxdm().equalsIgnoreCase(Globals.XXDM_HZZY)
				||dao.getXxdm().equalsIgnoreCase(Globals.XXDM_NCDXKJXY)) {// 杭州职业技术学院
			String xn1 = dao.getOneRs("select distinct xn from " + tableName
					+ " where " + pk + "= ?", new String[] { pkValue }, "xn");
			String xq1 = dao.getOneRs("select distinct xq from " + tableName
					+ " where " + pk + "= ?", new String[] { pkValue }, "xq");
			String xn2 = request.getParameter("xn");
			String xq2 = request.getParameter("xq");
			String xn = Base.isNull(xn2) ? xn1 : DealString.toString(xn2);
			String xq = Base.isNull(xq2) ? xq1 : DealString.toString(xq2);
			sql = "select yhm ,xm from view_gyfdyxx where lddm=? and xn=? and xq=?";// 老师或辅导员担任楼长
			List lzxxList = dao.getList(sql, new String[] { lddm, xn, xq },
					new String[] { "yhm", "xm" });
			request.setAttribute("lzxxList", lzxxList);
		}
		sql = "select distinct xh,xm from view_xszsxx where lddm=?";// 学生担任楼长
		List lzList = dao.getList(sql, new String[] { lddm }, new String[] {
				"xh", "xm"});
		sql = "select distinct xh,xm from view_xszsxx where ssbh=?";
		List qszList = dao.getList(sql, new String[] { ssbh }, new String[] {
				"xh", "xm"});
		request.setAttribute("lzList", lzList);
		request.setAttribute("qszList", qszList);
	}

	private static void whActiveInfo(HttpServletRequest request, DAO dao,
			String rsNum, Vector<Object> vector, StringBuffer query) {
		String sql;
		String[] colList;
		String[] colListCN;
		String tabName;
		String realtable;
		String tips;
		tips = "公寓管理 - 宿舍精神文明建设 - 活动记录维护 ";
		request.setAttribute("tips", tips);

		tabName = "xsgygl_xsssfbqkb"; // 数据表
		realtable = "view_xsgygl_xsssfbqkb"; // 视图
		sql = "select * from " + realtable + query.toString();
		colList = new String[] { "xh", "xm", "xymc", "zymc", "bjmc", "ssbh",
				"fdyxm", "szxm", "czxm", "lzxm", "cwh" };
		colListCN = dao.getColumnNameCN(colList, realtable);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			vector.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (vector == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(vector.size());
			}
		}
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("realTable", tabName);
		request.setAttribute("tableName", realtable);
		request.setAttribute("rs", vector);
		request.setAttribute("topTr", topTr);
	}

	public  static ActionForward dormDistribute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		boolean res = dao.createCwxx();//验证床位信息是否正确
		if (res == false){
			request.setAttribute("errINFO","更新床位数据时出现错误！");
		}

		GetDropDownList getData = new GetDropDownList();
		GetDataInfo getDataIF   = new GetDataInfo();
		String xxdm = StandardOperation.getXxdm();
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)
//				||xxdm.equalsIgnoreCase(Globals.XXDM_HENANGYDX)
//				||Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)
//				||Globals.XXDM_CSDLZYJSXY.equalsIgnoreCase(xxdm)){//长沙民政职业技术学院,河南工业大学,江苏信息职业技术学院,长沙电力职业技术学院
		if(gyglDao.xsfpMsXxPd(xxdm)){	
			//获得是否新生报到住宿信息维护模式Begin（长沙民政职业技术学院）“1”表示新生报到住宿信息维护模式，“0”表示学生住宿信息维护维护模式
			String sfbdbz = dao.getOneRs(" select xszsbdms from gygl_xsbdbz where rownum=1 ",new String[]{},"xszsbdms");
			if(Base.isNull(sfbdbz)){
				sfbdbz="0";
			}
			if(sfbdbz.equalsIgnoreCase("1")){
				return new ActionForward("/csmz_gygl.do?method=xsDorm_Distribute",false);
			}
			//获得是否新生报到住宿信息维护模式End
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){//中国地质大学
			return new ActionForward("/zgdzdx_Gygl.do?method=roomCompartition",false);
		}
		CommanForm dataSearchForm = (CommanForm) form;
//		String sql = "";
		String query = "";
		String tracePage    = "success";
		String oldMappingItems = DealString.toGBK(dataSearchForm.getOldCondiSqlValue());
		String newMappingItems = DealString.toGBK(dataSearchForm.getConditionSqlValue());
		boolean doFlag = false;
		String userName = request.getSession().getAttribute("userName").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		String doType = request.getParameter("doType");
//		String delete = request.getParameter("delete");
		String xqdm = dataSearchForm.getXqdm();
		String lddm = dataSearchForm.getLddm();
		String cs = dataSearchForm.getCs();
		String xb = DealString.toGBK(dataSearchForm.getXb());
		dataSearchForm.setXb(xb);
		String fpfs = dataSearchForm.getFpfs();
		//dataSearchForm.setConditionSqlText(DealString.toGBK(dataSearchForm.getConditionSqlText()));
		dataSearchForm.setConditionSqlValue(DealString.toGBK(dataSearchForm.getConditionSqlValue()));					
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)) {// 南昌大学科学技术学院
//			lddm = gyglDao.getLddmxXx(userName);// 获得楼长用户操作楼栋代码
			String[] ldxxTem = dao.getOneRs(
					"select xqdm ,xbxd from  view_ssxx where lddm=? ",
					new String[] { lddm }, new String[] { "xqdm", "xbxd" });
			if (ldxxTem != null) {
				xqdm = Base.isNull(ldxxTem[0]) ? "" : ldxxTem[0];
				xb = Base.isNull(ldxxTem[1]) ? "" : ldxxTem[1];
				dataSearchForm.setLddm(lddm);
				dataSearchForm.setXqdm(xqdm);
				dataSearchForm.setXb(xb);
			}
		}
		//校区List
//		sql = "select dm,xqmc from dm_zju_xq";
		List xiaoqquList = GyglShareDAO.getXiaoQuList(userName);//dao.getList(sql, new String[] {}, new String[] {"dm", "xqmc" });
		//楼栋List
		request.setAttribute("xiaoqquList", xiaoqquList);
		String xydm = DealString.toGBK(dataSearchForm.getXydm());
		String nj = DealString.toGBK(dataSearchForm.getNj());
		String bjdm =  DealString.toString(dataSearchForm.getBjdm());
		String zydm =  DealString.toGBK(dataSearchForm.getZydm());
		String[] tmp = getDataIF.getCwFpZsData(xydm, nj, bjdm);//未划分总数(人)
		if(tmp!=null){
			request.setAttribute("total", tmp[0]);
			request.setAttribute("boy", tmp[1]);
			request.setAttribute("girl", tmp[2]);
		}else{
			request.setAttribute("total", "0");
			request.setAttribute("boy", "0");
			request.setAttribute("girl", "0");
		}
		String[] tmpT = getDataIF.getSsFpYhfcws(nj, xydm,bjdm);//未划分总数(人)
		if(tmpT!=null){
			request.setAttribute("totalBed", tmpT[0]);
			request.setAttribute("boyBed", tmpT[1]);
			request.setAttribute("girlBed", tmpT[2]);
			request.setAttribute("bgBed", tmpT[3]);			
		}else{
			request.setAttribute("totalBed", "0");
			request.setAttribute("boyBed", "0");
			request.setAttribute("girlBed", "0");
			request.setAttribute("bgBed", "0");
		}
		if (doType != null && doType.equals("save")) {
			String[] delSql    = null;
			String[] inserSql  = null;
//			String  querry     = " where 1=1 ";
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){//杭州职业技术学院
				if(((oldMappingItems != null) && !oldMappingItems.equals(""))){
					//删除语句拼接					
//					if(Base.isNull(bjdm)){//删除整个年级，学院
					String delItems1[] = oldMappingItems.split(",");
					int num = delItems1.length;
					String delItems2[][] = new String[num][];
					delSql     = new String[num];
					for(int i=0;i<num;i++){
						delItems2[i] = delItems1[i].split("/");
						if(delItems2[i].length == 5){//按宿舍//其中值格式:年级/学院代码/班级代码/宿舍编号/剩余床位数				
							delSql[i]  = "delete from ssfpb where xydm||bjdm||ssbh||cwh in (select xydm||bjdm||ssbh||cwh from " +
							"(select a.bjdm,a.cwh,a.xydm,a.ssbh,b.lddm,c.xqdm from ssfpb a,ssxxb b,sslddmb c where a.ssbh=b.ssbh and b.lddm=c.lddm and bjdm='"+bjdm+"') )";
//							    delSql[i] = "delete from ssfpb where  bjdm='"+delItems2[i][2]+"'"+" and ssbh= "+"'"+delItems2[i][3]+"' and cwh='"+delItems2[i][4]+"' ";
						}else{//按床位//其中值格式:年级/学院代码/班级代码/宿舍编号/该宿舍总(床)位数/床位号	
//								delSql[i] = "delete from ssfpb where ssbh='"+delItems2[i][3]+"'and cwh='"+delItems2[i][5]+"'";//宿舍编号、床位号
							delSql[i] = "delete from ssfpb where  bjdm='"+delItems2[i][2]+"'"+" and ssbh= "+"'"+delItems2[i][3]+"' and cwh='"+delItems2[i][5]+"' ";							
						}
					}
//					}else{//删除整个班级
//						delSql     = new String[1];	
//						delSql[0]  = "delete from ssfpb where bjdm ='"+bjdm+"' and xydm||bjdm||ssbh||cwh in  (select xydm||bjdm||ssbh||cwh from "
//						+"(select a.bjdm,a.cwh,a.xydm,a.ssbh,b.lddm,c.xqdm from ssfpb a,ssxxb b,sslddmb c where a.ssbh=b.ssbh and b.lddm=c.lddm) ) ";
//					} 
//			    
				}else{
					delSql     = new String[1];
					delSql[0]  = " delete from ssfpb where 1=2 ";
				}
				
				if ((newMappingItems != null) && !newMappingItems.equals("")){	
					//插入语句拼接
					String inserItems1[] = newMappingItems.split(",");
					int num = inserItems1.length;
					String inserItems2[][]   = new String[num][];
					inserSql                 = new String[num];
					for(int i=0;i<num;i++){
						inserItems2[i] = inserItems1[i].split("/");	
						if(inserItems2[i].length == 5){//按宿舍//其中值格式:年级/学院代码/班级代码/宿舍编号/该宿舍总(床)位数
							inserSql[i]      = " insert into ssfpb(xydm,ssbh,cws,bjdm,cwh,nj) select '"
								+inserItems2[i][1]
								                +"','"
								                +inserItems2[i][3]
								                                +"','1','"
								                                +inserItems2[i][2]
								                                                +"',cwh,'"
								                                                +inserItems2[i][0]
								                                                                +"' from cwxxb a where a.cwh not in "
								                                                                +
								                                                                " (select cwh from xszsxxb b where a.ssbh=b.ssbh ) and a.cwh not in (select cwh from ssfpb b where a.ssbh=b.ssbh) and a.ssbh='"+inserItems2[i][3]+"'";
						}else{//按床位//其中值格式:年级/学院代码/班级代码/宿舍编号/该宿舍总(床)位数/床位号	
							inserSql[i]      = " insert into ssfpb(xydm,ssbh,cws,bjdm,cwh,nj) values('"+inserItems2[i][1]+"','"+inserItems2[i][3]+"','1','"+inserItems2[i][2]+"','"+inserItems2[i][5]+"','"+inserItems2[i][0]+"') ";
						}
					}
				}else{
					inserSql = new String[1];
					inserSql[0] =  "delete from ssfpb where 1=2"; 
				}
			}else{
				if(StringUtils.isNotNull(oldMappingItems)){
//					删除语句拼接
					String delItems1[]     = oldMappingItems.split(",");
					int delNum             =  delItems1.length;
					String delItems2[][]   = new String[delNum][];
					delSql                 = new String[delNum];
					for (int i = 0; i < delNum; i++) {
						delItems2[i] = delItems1[i].split("/");
						delSql[i]    = "delete from ssfpb where  xydm='"+delItems2[i][0]+"'"+" and ssbh= "+"'"+delItems2[i][1]+"' and nj='"+delItems2[i][3]+"' ";
					}
				}else{
					if (StringUtils.isNotNull(newMappingItems)) {
						//newMappingItems        = dormSimilarStrUnion(newMappingItems);//并宿舍划分中重复值
						String inserItems1[]   = newMappingItems.split(",");
						int  inNum            = inserItems1.length;
						delSql     = new String[inNum];
						String inserItems2[][] = new String[inNum][];		   
						inserSql               = new String[inNum];
						for(int i=0;i<inNum;i++){
							inserItems2[i] = inserItems1[i].split("/");
							delSql[i] = "delete from ssfpb where xydm='"+inserItems2[i][0]+"' and ssbh='"+inserItems2[i][1]+"' and nj='"+inserItems2[i][3]+"'";
						}			
					}
				}
				if (StringUtils.isNotNull(newMappingItems)) {
//					插入语句拼接
					
					newMappingItems        = dormSimilarStrUnion(newMappingItems);//并宿舍划分中重复值
					String inserItems1[]   = newMappingItems.split(",");
					int  inNum            = inserItems1.length;
					String inserItems2[][] = new String[inNum][];		   
					inserSql               = new String[inNum];
					String sycws=null;//剩余床位数
					for(int i=0;i<inNum;i++){
						inserItems2[i] = inserItems1[i].split("/");
						sycws=dao.getOneRs("select a.cws-nvl(b.cws,'0') sycws from ssxxb a,ssfpb b where a.ssbh=b.ssbh(+) and a.ssbh=?",
								new String[]{inserItems2[i][1]}, "sycws");
						if((Integer.parseInt(sycws)-Integer.parseInt(inserItems2[i][2]))>0){//分配床位数必须小于等于宿舍剩余床位数
							inserSql[i]    = "insert into ssfpb(xydm,ssbh,cws,bjdm,cwh,nj) values('"+inserItems2[i][0]+"','"+inserItems2[i][1]+"','"+inserItems2[i][2]+"','0','0','"+inserItems2[i][3]+"') ";
						}else{
							//分配的床位数已经大于剩余床位数，请重新分配
						}
					}					   
				}else{
					inserSql = new String[1];
					inserSql[0] =  "delete from ssfpb where 1=2 "; 
				}
				
			}//TODO
			String[] exesql = dao.unionArray(delSql, inserSql);
			int[] array = null;
			array = dao.runBatch(exesql);
			doFlag = dao.checkBatch(array);
			if (doFlag == true) {
				oldMappingItems=newMappingItems;//更改旧值
				request.setAttribute("doFlag", "ok");
			} else {
				request.setAttribute("doFlag", "no");
			}		
			//############################################################################## gaobb 宿舍划分bug 20110906 start
		}

		query=(Base.isNull(xydm))?"%":"%"+xydm+"%";
		query+="!!-!!";
		query+=(Base.isNull(zydm))?"%":"%"+zydm+"%";
		query+="!!-!!";
		query+=(Base.isNull(nj))?"%":"%"+nj+"%";
		query+="!!-!!";	
		request.setAttribute("bjList", getData.getBjList(query, userName,Fdypd.isFdy(userName)+"",""));//班级列表
		request.setAttribute("njList",Base.getNjList());// 年级列表
		request.setAttribute("xyList",Base.getXyList());//学院列表
		request.setAttribute("ldList", getDataIF.getSsFpLdList(xqdm,xb));//楼栋列表
		request.setAttribute("lcList", getDataIF.getSsFpCsList(lddm));//楼层列表		
		request.setAttribute("ssfpList", getDataIF.getSsFpFpSsXxList(lddm, cs, nj, xydm, bjdm));//已划分宿舍信息列表
		request.setAttribute("ssxxList", getDataIF.getSsFpSsXxList(xqdm, xb, lddm, cs, "ass"));
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("userType",userType);
		request.setAttribute("oldMappingItems", oldMappingItems);
		writeAble=(Base.chkUPower(userName,"dorm_distribute.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no";
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {//杭州职业技术学院
			tracePage = "hzzy_success";
			request.setAttribute("ssxxList",getDataIF.getSsFpSsXxList(xqdm, xb, lddm, cs, fpfs));//未划分宿舍信息列表
		}
		
		return mapping.findForward(tracePage);
	}

	public static ActionForward bedDistribute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		
		
		boolean res = dao.createCwxx();//验证床位信息是否正确
		if (res == false){
			request.setAttribute("errINFO","更新床位数据时出现错误！");
		}
		
		CommanForm dataSearchForm = (CommanForm) form;
		GetDataInfo getDataIF   = new GetDataInfo();
		GetDropDownList getData = new GetDropDownList();
		String xxdm =dao.getXxdm();
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)//长沙民政职业技术学院
//				||xxdm.equalsIgnoreCase(Globals.XXDM_HENANGYDX)//河南工业大学
//				||Globals.XXDM_CSDLZYJSXY.equalsIgnoreCase(xxdm)){//长沙民政职业技术学院,河南工业大学,江苏信息职业技术学院,长沙电力职业技术学院
		if(gyglDao.xsfpMsXxPd(xxdm)){			
			//获得是否新生报到住宿信息维护模式Begin（长沙民政职业技术学院）“1”表示新生报到住宿信息维护模式，“0”表示学生住宿信息维护维护模式
			String sfbdbz = dao.getOneRs(" select xszsbdms from gygl_xsbdbz where rownum=1 ",new String[]{},"xszsbdms");
			if(Base.isNull(sfbdbz)){
				sfbdbz="0";
			}
			if(sfbdbz.equalsIgnoreCase("1")){
				return new ActionForward("/csmz_gygl.do?method=xsBed_Distribute",false);
			}
			//获得是否新生报到住宿信息维护模式End
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){//中国地质大学
			return new ActionForward("/zgdzdx_Gygl.do?method=bedCompartition",false);
		}

		String query = "";		
		String oldMappingItems = DealString.toGBK(dataSearchForm.getOldCondiSqlValue());
		String newMappingItems = DealString.toGBK(dataSearchForm.getConditionSqlValue());
		String doType = request.getParameter("doType");
		String xydm = dataSearchForm.getXydm();
		String zydm = dataSearchForm.getZydm();
		String bjdm = dataSearchForm.getBjdm();
		String xqdm = dataSearchForm.getXqdm();
		String lddm = dataSearchForm.getLddm();		
		String nj = dataSearchForm.getNj();
		String cs = dataSearchForm.getCs();
		String xb = DealString.toGBK(dataSearchForm.getXb());
		dataSearchForm.setXb(xb);
		String cwfp = DealString.toGBK(request.getParameter("cwfp"));
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String userName = request.getSession().getAttribute("userName").toString();
		String ksh      = dataSearchForm.getKsh();//学号
		String[] xsInfo   = dao.getOneRs("select a.nj,a.xydm,a.bjdm,a.xb,b.lddm,b.cs,b.xqdm from view_xszsxx a,view_ssxx b where a.ssbh=b.ssbh and xh=?  ",
				new String[]{ksh},new String[]{"nj","xydm","bjdm","xb","lddm","cs","xqdm"});
		if(xsInfo!=null){
			nj   = xsInfo[0];
			xydm = xsInfo[1];
			bjdm = xsInfo[2];
			xb   = xsInfo[3];
			lddm   = xsInfo[4];
			cs   = xsInfo[5];
			xqdm   = xsInfo[6];
			dataSearchForm.setNj(nj);
			dataSearchForm.setXydm(xydm);
			dataSearchForm.setBjdm(bjdm);
			dataSearchForm.setXb(xb);
			dataSearchForm.setLddm(lddm);
			dataSearchForm.setCs(cs);
			dataSearchForm.setXqdm(xqdm);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			dataSearchForm.setXydm(xydm);
		}
		if(Base.isNull(nj)){
			nj = Base.currNd;
			dataSearchForm.setNj(Base.currNd);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {//杭州职业技术学院
			request.setAttribute("showhzy", "showhzy");	
		}
			

		// ==================传媒开关控制 ==================
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)) {// 浙江传媒
			String cwfpkg = dao.getOneValue("xgxtgy_gnkgb", "kgzt", "gndm",
					"gygl_cwfp");
			if ("关".equalsIgnoreCase(cwfpkg)) {
				
				String msg = "本次分配未开放，请确认！";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);

			}
		}
		// =================end ===================
		
		if (doType != null && doType.equals("save")) {
//			boolean doFlag    = false;
//			String[] delSql    = null;
//			String[] inserSql  = null;
//			if(((oldMappingItems != null) && !oldMappingItems.equals(""))){
//				//删除语句拼接
//				String delItems1[]     = oldMappingItems.split(",");
//				int delNum             =  delItems1.length;
//				String delItems2[][]   = new String[delNum][];
//				delSql                 = new String[delNum];
//				for (int i = 0; i < delNum; i++) {
//					delItems2[i] = delItems1[i].split("/");
//					delSql[i]    = " delete from xszsxxb where xh = '"+delItems2[i][0]+"' "; 
//				}
//			}else{
//				delSql     = new String[1];
//				delSql[0]  = " delete from xszsxxb where 1=2 ";
//			}
//			if ((newMappingItems != null) && !newMappingItems.equals("")) {	
//				//插入语句拼接
//				String inserItems1[] = newMappingItems.split(",");
//				int num = inserItems1.length;
//				String inserItems2[][] = new String[num][];
//				inserSql                 = new String[num];
//				for(int i=0;i<num;i++){
//					inserItems2[i] = inserItems1[i].split("/");
//					inserSql[i]      = " insert into xszsxxb(xh,ssbh,cwh,rzrq) values('"+inserItems2[i][0]+"','"+inserItems2[i][1]+"','"+inserItems2[i][2]+"','"+inserItems2[i][3]+"') ";
//				}
//			}else{
//				inserSql = new String[1];
//				inserSql[0] =  "delete from xszsxxb where 1=2"; 
//			}
//			String[] exesql = dao.unionArray(delSql, inserSql);
//			int[] array = dao.runBatch(exesql);
//			doFlag = dao.checkBatch(array);
//			if (doFlag == true) {
//				oldMappingItems=newMappingItems;//更改旧值
//				request.setAttribute("doFlag", "ok");
//			} else {
//				request.setAttribute("doFlag", "no");
//			}
			//################################################# gaobb 20110905 防止多个操作人员并发操作   start
			Object lock=new Object();
			synchronized(lock){//防止并发执行
				boolean doFlag    = false;
				StringBuffer back=new StringBuffer();//反馈提示信息
				String sql="select count(1) rs_num from xszsxxb where xh=? and ssbh=? and cwh=?";
				String sql_wh="select count(1) rs_num from xszsxxb where ssbh=? and cwh=?";
				String rs_num=null;
				String rs_num_cw=null; 
//				StringBuffer oldXh=new StringBuffer();
				ArrayList<String> batchSql=new ArrayList<String>(); 
				
				if(((oldMappingItems != null) && !oldMappingItems.equals(""))){
					String inserItems1[] = newMappingItems.split(",");
					
					//删除语句拼接
					String delItems1[]     = oldMappingItems.split(",");
					
					int delNum             =  delItems1.length;
					String delItems2[][]   = new String[delNum][];
					for (int i = 0; i < delNum; i++) {
						if(newMappingItems==null||newMappingItems.indexOf(delItems1[i])<0){//判断本地是否移除
							delItems2[i] = delItems1[i].split("/");
							rs_num=dao.getOneRs(sql, new String[]{delItems2[i][0],delItems2[i][1],delItems2[i][2]}, "rs_num");
							if("0".equals(rs_num)){//验证该对象是否被其他人员删除或异动
								back.append(delItems2[i][0]+"已被其他人员删除或异动！<br/>");
							}else if(newMappingItems!=null&&newMappingItems.indexOf(delItems2[i][0])>-1){//该对象本地异动
								for(int j=0;j<inserItems1.length;j++){
									if(delItems2[i][0].equals(inserItems1[j].split("/")[0])){
										rs_num_cw=dao.getOneRs(sql_wh, new String[]{inserItems1[j].split("/")[1],inserItems1[j].split("/")[2]}, "rs_num");
										if("0".equals(rs_num_cw)){//判断异动后的床位是否已被占用，若占用这不进行删除
											batchSql.add(" delete from xszsxxb where xh = '"+delItems2[i][0]
											              +"' and ssbh= '"+delItems2[i][1]+"' and cwh= '"+delItems2[i][2]+"' ");
											break;
										}
									}
								}
							}else{
								batchSql.add(" delete from xszsxxb where xh = '"+delItems2[i][0]
								             +"' and ssbh= '"+delItems2[i][1]+"' and cwh= '"+delItems2[i][2]+"' ");
							}
//							oldXh.append(delItems2[i][0]);
//							oldXh.append(",");
						}
					}
				}
				
				if ((newMappingItems != null) && !newMappingItems.equals("")) {	
					//插入语句拼接
					String inserItems1[] = newMappingItems.split(",");
					int num = inserItems1.length;
					String inserItems2[][] = new String[num][];
					sql="select count(1) rs_num from xszsxxb where xh=?";
					for(int i=0;i<num;i++){
						if(oldMappingItems==null||oldMappingItems.indexOf(inserItems1[i])<0){//判断本地新添加的和异动的
							inserItems2[i] = inserItems1[i].split("/");
							rs_num=dao.getOneRs(sql, new String[]{inserItems2[i][0]}, "rs_num");
							rs_num_cw=dao.getOneRs(sql_wh, new String[]{inserItems2[i][1],inserItems2[i][2]}, "rs_num");
							if("0".equals(rs_num)&&"0".equals(rs_num_cw)){//学生和宿舍床位都未安排的情况下，才可进行插入数据
								batchSql.add(" insert into xszsxxb(xh,ssbh,cwh,rzrq) values('"+inserItems2[i][0]+"','"+inserItems2[i][1]+"','"+inserItems2[i][2]+"','"+inserItems2[i][3]+"') ");
							}else{
								back.append(inserItems2[i][0]+"已经被分配床位或分配的床位已被占用！<br/>");
							}
						}
					}
				}
				
				if(batchSql.size()==0){
					batchSql.add("delete from xszsxxb where 1=2");
				}
				
				int[] array = dao.runBatch(batchSql.toArray(new String[]{}));
				doFlag = dao.checkBatch(array);
				if (doFlag == true) {
					oldMappingItems=newMappingItems;//更改旧值
					request.setAttribute("doFlag", "ok");
				} else {
					request.setAttribute("doFlag", "no");
				}
				
				if(back.length()>0){//返回异常信息
					request.setAttribute("back", back.toString());
				}
			}
			//################################################# gaobb 20110905 防止多个操作人员并发操作   end
		}
		
		String[] tmp = getDataIF.getCwFpZsData(xydm, nj, bjdm);//未分配总数(人)
		if(tmp!=null){
			request.setAttribute("total", tmp[0]);
			request.setAttribute("boy", tmp[1]);
			request.setAttribute("girl", tmp[2]);
		}else{
			request.setAttribute("total", "0");
			request.setAttribute("boy", "0");
			request.setAttribute("girl", "0");
		}
		//String[] tmpT = getDataIF.getCwYfpZsData(nj, xydm,bjdm);//已分配总数(人)
		String[] tmpT = getDataIF.getCwYfpZsData(xydm, nj,bjdm);//已分配总数(人)
		if(tmpT!=null){
			request.setAttribute("totalBed", tmpT[0]);
			request.setAttribute("boyBed", tmpT[1]);
			request.setAttribute("girlBed", tmpT[2]);					
		}else{
			request.setAttribute("totalBed", "0");
			request.setAttribute("boyBed", "0");
			request.setAttribute("girlBed", "0");			
		}
		
		request.setAttribute("oldMappingItems", oldMappingItems);
		query=(Base.isNull(xydm))?"%":"%"+xydm+"%";
		query+="!!-!!";
		query+=(Base.isNull(zydm))?"%":"%"+zydm+"%";
		query+="!!-!!";
		query+=(Base.isNull(nj))?"%":"%"+nj+"%";
		query+="!!-!!";		
		request.setAttribute("bjList", getData.getBjList(query, userName,Fdypd.isFdy(userName)+"",""));//班级列表
		request.setAttribute("njList",Base.getNjList());// 年级列表
		request.setAttribute("xyList",Base.getXyList());//学院列表       
		request.setAttribute("xiaoqquList", getDataIF.getCwFpXqList(xydm,cwfp,nj,bjdm));//校区列表
		request.setAttribute("ldList", getDataIF.getCwFpLdList(xqdm, xydm, xb, cwfp, nj));//楼栋列表
		request.setAttribute("csList", getDataIF.getcwFpCsList(lddm, nj, xydm, cwfp));//层数列表
		//request.setAttribute("ssxxList",getDataIF.getCwFpSsCwXxList(xqdm, xydm, lddm, xb, cs, cwfp, nj, bjdm));//宿舍床位列表
		request.setAttribute("yfpCwList",getDataIF.getCwFpFpCwList(xydm, nj, bjdm,xb,cwfp,lddm));//已分配学生床位列表
		request.setAttribute("xsList",getDataIF.getCwFpSsXsList(xydm, nj, bjdm, xb));//未分配学生列表
		request.setAttribute("xxdm",xxdm);
		request.setAttribute("userType", userType);	
		if(!Base.isNull(ksh)){//单个学号查询
			String sql = "select distinct (xh||'/'||ssbh||'/'||cwh) dm , xh||'/'||xm||'/'||xb||'/'||ldmc||qsh||'/'||cws||'/'||cwh mc from view_xszsxx where xh=?";
			request.setAttribute("yfpCwList",dao.getList(sql,new String[]{ksh},new String[]{"dm","mc"}));
			request.setAttribute("xsList", "");//
		}
		request.setAttribute("writeAble", (Base.chkUPower(userName,"bed_distribute.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		
		GyglTyDAO gyglDAO = new GyglTyDAO();
		List<HashMap<String,String>> ssxxList = gyglDAO.getCwFpSsCwXxList(xqdm, xydm, lddm, xb, cs, cwfp, nj, bjdm, null, null);
		request.setAttribute("ssxxList", ssxxList);
		
		return mapping.findForward("success");
	}

	public static ActionForward dormRepair(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		CommanForm comForm = (CommanForm) form;
		Vector<Object> rs = new Vector<Object>();
		String rsNum = "0";
		String sql = "";
		StringBuffer query = new StringBuffer();
		String userType = session.getAttribute("userType").toString(); //直接获得用户类型，此方式获取的类型为两类：1、"stu"学生2、"teacher"教师
//		String userName = session.getAttribute("userName").toString();
		query.append(" where 1=1 ");
		String tips = "公寓管理 - 信息维护 - 公寓维修";
		String pk = "ssbh||bxsj";
		String tableName = "view_gywxglxx";
		String act = request.getParameter("go");
		String realTable = "gywxglb";
		String xn = comForm.getXn();
		String xq = comForm.getXq();
		String lddm = comForm.getLddm();
		String lc = comForm.getLc();
		String qsh = comForm.getQsh();
		String bxsj = comForm.getBxsj();
		String wxsj = comForm.getWxsj();
		String wxzt = DealString.toGBK(comForm.getWxzt());
		String wxnr = comForm.getWxnr();
		comForm.setWxzt(wxzt);
		String[] colList = null;
		String xxdm = StandardOperation.getXxdm();
		
		//公寓辅导员判断begin
		//String lddmStr = gyglDao.getLddmxXx(userName);
//		String isGyFdy = "no";
//		if(!Base.isNull(lddmStr)){
//			lddm = lddmStr; 
//			comForm.setLddm(lddm);
//			isGyFdy = "yes";
//		}
//		request.setAttribute("isGyFdy",isGyFdy);
        //公寓辅导员判断end
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)) {			
			request.setAttribute("showhzy", "showhzy");
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){
			request.setAttribute("isGDBYXY", "yes");
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){
			request.setAttribute("isCSMZZYJSXY", "isCSMZZYJSXY");			
		}else if(Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)){
			tableName = "view_nbzyjsxy_gywxglxx";
			request.setAttribute("isNBZYJSXY", "isNBZYJSXY");	
		}

		if (xn != null && !xn.equalsIgnoreCase(""))
			query.append(" and xn = '" + xn + "'");
		if (xq != null && !xq.equalsIgnoreCase(""))
			query.append(" and xq = '" + xq + "'");
		if (lddm != null && !lddm.equalsIgnoreCase(""))
			query.append(" and lddm = '" + lddm + "'");
		if (!Base.isNull(qsh)&& !qsh.equalsIgnoreCase("qsh"))
			query.append(" and qsh = '" + qsh + "'");
		if (bxsj != null && !bxsj.equalsIgnoreCase(""))
			query.append(" and bxsj = '" + bxsj + "'");
		if (wxsj != null && !wxsj.equalsIgnoreCase(""))
			query.append(" and wxsj = '" + wxsj + "'");
		if (!Base.isNull(lc) && !lc.equalsIgnoreCase("lc"))
			query.append(" and (substr(qsh,1,1) ='" + lc + "' or cs='"+lc+"') ");
		if (!Base.isNull(wxzt)){
			if(wxzt.equalsIgnoreCase("未维修")){
				query.append(" and wxsj = '未维修' ");
			}else{
				query.append(" and wxsj <> '未维修' ");
			}
		}
		query.append(Base.isNull(wxnr)?"":" and wxnr='"+wxnr+"' ");
		sql = " select (case when v.wxsj != '未维修' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
			+ pk
			+ " 主键,rownum 行号,v.* from "
			+ tableName
			+ " v"
			+ query
			+ " order by v.wxsj desc,v.bxsj desc ";

		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			colList = new String[] { "bgcolor", "主键", "行号", "xn", "xqmc", "ldmc",
					"qsh", "shq", "bxsj", "wxsj", "rymc", "bmmc" };
		} else if(Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)){
			colList = new String[] { "bgcolor", "主键", "行号", "xn", "xqmc", "ldmc",
					"qsh", "wxnrmc","bxsj", "wxsj" };		
		}else {
			colList = new String[] { "bgcolor", "主键", "行号", "xn", "xqmc", "ldmc",
					"qsh", "bxsj", "wxsj","rymc" };
		}
		String[] colListCN = dao.getColumnNameCN(colList, "view_gywxglxx");
		List topTr = dao.arrayToList(colList, colListCN);

		if (act != null && act.equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		gyglDao.getLdLcQshList(request);
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", "view");// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);
		sql = "select dm,mc from wxnrdmb order by dm ";
		List wxnrList = dao.getList(sql, new String[] {}, new String[] {
				"dm", "mc" });
		request.setAttribute("wxnrList", wxnrList);
		
		if (Base.isNull(lddm)) {
			
			List<HashMap<String, String>> qshList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "----请选择----");
			qshList.add(map);
			request.setAttribute("qshList", qshList);
		}
		return mapping.findForward("success");
	}

	public static ActionForward sanitationCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm comForm = (CommanForm) form;
		HttpSession session = request.getSession();
		Vector<Object> rs = new Vector<Object>();
		String rsNum = "0";
		String sql = "";
		String xxdm = Base.xxdm;
		StringBuffer query = new StringBuffer();
		//String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
//		String userName = session.getAttribute("userName").toString();
		query.append(" where 1=1 ");
		String tips = "公寓管理 - 信息维护 - 卫生检查";
		if(Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)){
			tips = "公寓管理 - 信息维护 - 内务卫生";
		}
		String pk = "xn||xq||jcsj||ssbh";
		String tableName = "view_gywsjcxx";
		String act = request.getParameter("go");
		String realTable = "gywsjcb";
		String xn = comForm.getXn();
		String xq = comForm.getXq();
		String lddm = comForm.getLddm();
		String lc = comForm.getLc();
		String qsh = comForm.getQsh();
		String jcsj = comForm.getJcsj();
		String jcbm = comForm.getJcbm();
		String gzkssj = comForm.getGzkssj();
		String gzjssj = comForm.getGzjssj();
		String[] colList = null;
		
		if(xn==null){
			xn = Base.currXn;
			comForm.setXn(xn);
		}
		if(xq==null){
			xq = Base.currXq;
			comForm.setXq(xq);
		}
        //公寓辅导员判断begin
//		String lddmStr = gyglDao.getLddmxXx(userName);
//		String isGyFdy = "no";
//		if(!Base.isNull(lddmStr)){
//			lddm = lddmStr; 
//			comForm.setLddm(lddm);
//			isGyFdy = "yes";
//		}
//		request.setAttribute("isGyFdy",isGyFdy);
        //公寓辅导员判断end
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)) {			
			request.setAttribute("showhzy", "showhzy");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)
				||xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){
				tips = "公寓管理 - 信息维护 - 卫生寝室信息";				
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				tips = "公寓管理 - 精神文明建设 - 卫生寝室";				
			}
			request.setAttribute("noshowbm","noshowbm");
		}
		
		// ------------2010/6/23 edit by luojw----------------
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) { // 中国地质大学
			String dj = comForm.getDj();
			
			if (!Base.isNull(dj))
				query.append(" and dj = '" + dj + "'");
			
			List<HashMap<String, String>> djList = dao.getWhList("zgdd_wsjcdj",
					"dm", "mc", "", "", "");//卫生检查等级
			djList.remove(0);
			request.setAttribute("djList", djList);// 发送记录数
		}
		// ------------end------------------------
		
		if (xn != null && !xn.equalsIgnoreCase(""))
			query.append(" and xn = '" + xn + "'");
		if (xq != null && !xq.equalsIgnoreCase(""))
			query.append(" and xq = '" + xq + "'");
		if (lddm != null && !lddm.equalsIgnoreCase(""))
			query.append(" and lddm = '" + lddm + "'");
		if (!Base.isNull(qsh)&& !qsh.equalsIgnoreCase("qsh"))
			query.append(" and qsh = '" + qsh + "'");
		if (jcsj != null && !jcsj.equalsIgnoreCase("")) {
			if (Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {
				query.append(" and jcsj = '第" + jcsj + "周'");
			} else {
				query.append(" and jcsj = '" + jcsj + "'");
			}
		}
		if(!Base.isNull(gzkssj)&&!Base.isNull(gzjssj)){
			query.append(" and jcsj between '"+gzkssj+"' and '"+gzjssj+"' ");
		}else if(!Base.isNull(gzkssj)){
			query.append(" and jcsj>='"+gzkssj+"' ");
		}else if(!Base.isNull(gzjssj)){
			query.append(" and jcsj<='"+gzjssj+"' ");
		}
		if (jcbm != null && !jcbm.equalsIgnoreCase(""))
			query.append(" and jcbm = '" + jcbm + "'");
		if (!Base.isNull(lc)&& !lc.equalsIgnoreCase("lc"))
			query.append(" and  cs='"+lc+"' ");

		if (Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {
			pk = "xn||xq||ssbh||jcsj";
		}
		sql = " select " + pk + " 主键,rownum 行号,v.* from " + tableName + " v"
		+ query;

		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			colList = new String[] { "主键", "行号", "xn", "xqmc", "ldmc", "qsh",
					"shq", "jcsj", "bmmc", "fs", "dj","cy" };
		} else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)
				||xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
			colList = new String[] { "主键", "行号", "xn", "xqmc", "ldmc", "qsh",
					"jcsj", "jcbm", "fs", "dj","cy" };
		}else if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			colList = new String[] { "主键", "行号", "xn", "xqmc", "ldmc", "qsh",
					"jcsj", "jcbm", "dj","cy" };		
		} else if (Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {
			// ==================begin 2009/5/26 骆嘉伟 =================
			colList = new String[] { "主键", "行号", "xn", "xqmc", "ldmc", "qsh", "jcsj", "bmmc", "fs", "dj","cy","主键" };
			sql = " select "
					+ pk
					+ " 主键,rownum 行号,v.xn,v.xqmc,v.ldmc,v.qsh,'第'||v.zs||'周' jcsj,v.bmmc,v.fs,v.dj,v.cy from "
					+ tableName + " v" + query;
			request.setAttribute("zsList", gyglDao.dao_zsList());
			// ==================end 2009/5/26 骆嘉伟 =================
		}
		else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
			colList = new String[] { "主键", "行号", "xn", "xqmc", "ldmc", "qsh",
					"jcsj", "fs","cy" };
		}else if (Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)){//武汉理工大学华夏学院
			colList = new String[] { "主键", "行号", "xn", "xqmc", "ldmc", "qsh",
					"jcsj", "bmmc", "fs", "cy" };
		}
		else{
			colList = new String[] { "主键", "行号", "xn", "xqmc", "ldmc", "qsh",
					"jcsj", "bmmc", "fs", "dj","cy" };
		}

		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);

		if (act != null && act.equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		gyglDao.getLdLcQshList(request);
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", "view");// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("success");
	}

	public static ActionForward dormDiscipline(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		CommanForm comForm = (CommanForm) form;
		List<String[]>rs=new ArrayList<String[]>();
		String rsNum = "0";
		String sql = "";
		String userDep = session.getAttribute("userDep").toString();		
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");
		String tips = "公寓管理 - 信息维护 - 住宿纪律";
		String pk = "xh||wjsj";
		String tableName = "view_zsjlxx";
		String act = request.getParameter("go");
		String realTable = "zsjlb";
		String xn = comForm.getXn();
		String xq = comForm.getXq();
		String nj = comForm.getNj();
		String xh = comForm.getXh();
		String lddm = comForm.getLddm();
		@SuppressWarnings("unused")
		String lc = comForm.getLc();
		String qsh = comForm.getQsh();
		String gzkssj = comForm.getGzkssj();
		String gzjssj = comForm.getGzjssj();
		String xydm = comForm.getXydm();
		String zydm = comForm.getZydm();
		String bjdm = comForm.getBjdm();
		String xm   = DealString.toGBK(comForm.getXm());
		String wjlbdm = comForm.getWjlbdm();
		String xsxb = DealString.toGBK(comForm.getXb1());
		String mz = comForm.getMz();
		String jg = DealString.toGBK(comForm.getJg());
		comForm.setJg(jg);
		comForm.setXb1(xsxb);
		comForm.setXm(xm);
		String[] colList = null;
		String xxdm = StandardOperation.getXxdm();
		if(xn == null){			
		   comForm.setXn(Base.currXn);
		}
		if(xq == null){
		   comForm.setXq(Base.currXq);
		}
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;			
		}
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			xydm = Fdypd.fdybjck(userName,xydm);
		}
		comForm.setXydm(xydm);
		
		
	    if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {			
			request.setAttribute("showhzy", "showhzy");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			gyglDao gydao = new gyglDao();
			request.setAttribute("showxbemy","showxbemy");				
			gydao.getOrthList(dao, request);
			query.append(Base.isNull(xsxb)?"":" and xb ='"+xsxb+"' ");
			query.append(Base.isNull(mz)?"":" and mz ='"+mz+"' ");
			query.append(Base.isNull(jg)?"":" and jg ='"+jg+"' ");
		}
		if (xn != null && !xn.equalsIgnoreCase(""))
			query.append(" and xn = '" + xn + "'");
		if (nj != null && !nj.equalsIgnoreCase(""))
			query.append(" and nj = '" + nj + "'");
		if (xq != null && !xq.equalsIgnoreCase(""))
			query.append(" and xq = '" + xq + "'");
		if (xh != null && !xh.equalsIgnoreCase(""))
			query.append(" and xh like '%'||?||'%'");
		if (lddm != null && !lddm.equalsIgnoreCase(""))
			query.append(" and lddm = '" + lddm + "'");
		if (!Base.isNull(qsh) && !qsh.equalsIgnoreCase("qsh"))
			query.append(" and qsh = '" + qsh + "'");		
		if (xydm != null && !xydm.equalsIgnoreCase(""))
			query.append(" and xydm = '" + xydm + "'");
		if (zydm != null && !zydm.equalsIgnoreCase(""))
			query.append(" and zydm = '" + zydm + "'");
		if (bjdm != null && !bjdm.equalsIgnoreCase(""))
			query.append(" and bjdm = '" + bjdm + "'");
		if (!Base.isNull(gzkssj)&&Base.isNull(gzjssj)){
			query.append(" and wjsj = '" + gzkssj + "'");
		}
		if(!Base.isNull(gzjssj)&&Base.isNull(gzkssj)){
			query.append(" and wjsj = '"+gzjssj+"' ");
		}
		if(!Base.isNull(gzkssj)&&!Base.isNull(gzjssj)){
			query.append(" and (wjsj >= '"+gzkssj+"' and wjsj<='"+gzjssj+"' ) ");
		}
		if (!Base.isNull(lc)&& !lc.equalsIgnoreCase("lc")){
			query.append(" and  cs='"+lc+"' ");
		}
		query.append(Base.isNull(wjlbdm)?"":" and wjlbdm='"+wjlbdm+"' ");
		query.append(Base.isNull(xm)?"":" and xm like '%'||?||'%' ");
		sql = "select * from(select  "+ pk +" 主键,rownum r,rownum 行号,v.* from (select * from " + tableName 
		+ query +" order by xn,xq,wjlbdm)v)";

		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			colList = new String[] { "主键", "行号", "xn", "xqmc", "xh", "xm", "xb",
					"ldmc", "qsh", "cwh", "wjlbmc","wjsj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			colList = new String[] { "主键", "行号", "xn", "xqmc", "xh", "xm", "xb",
					"jg","mz","ldmc", "qsh","wjlbmc", "wjsj" };
		}else {
			colList = new String[] { "主键", "行号", "xn", "xqmc", "xh", "xm", "xb",
					"ldmc", "qsh","wjlbmc", "wjsj" };
		}
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);

		if (act != null && act.equalsIgnoreCase("go")) {
			String[] input = new String[] {};
			if (!Base.isNull(xh) && !Base.isNull(xm)) {
				input = new String[] { xh, xm };
			} else if (!Base.isNull(xh)) {
				input = new String[] { xh };
			} else if (!Base.isNull(xm)) {
				input = new String[] { xm };
			}
			rs=CommonQueryDAO.commonQuery(sql, "", input, colList, comForm);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		sql = "select wjlbdm,wjlbmc from gygl_zswjlbdmb";
		List wjlbList = dao.getList(sql, new String[] {}, new String[] {
				"wjlbdm", "wjlbmc" });
		request.setAttribute("wjlbList", wjlbList);
		gyglDao.getLdLcQshList(request);
		gyglDao.getXyZyBjxx(request);
		request.setAttribute("path", "zsjl.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", "view");// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);
		
		if (Base.isNull(lddm)) {

			List<HashMap<String, String>> qshList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "----请选择----");
			qshList.add(map);
			request.setAttribute("qshList", qshList);
		}
		return mapping.findForward("success");
	}

	public static ActionForward dormDifferent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm comForm = (CommanForm) form;	
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		Vector<Object> rs = new Vector<Object>();
		String rsNum = "0";
		String sql = "";
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");
		String tips = "公寓管理 - 宿舍分配 - 宿舍异动";
		String pk = "xh||ydsj";
		String tableName = "view_ssydxx";
		String act = request.getParameter("go");
		String realTable = "ssydxxb";
		String xn = comForm.getXn();
		String xq = comForm.getXq();
		String xh = comForm.getXh();
		String xm = comForm.getXm();
//		String ydsj = comForm.getYdsj();
		String gzkssj = comForm.getGzkssj();
		String gzjssj = comForm.getGzjssj();
		String xydm = comForm.getXydm();
		String zydm = comForm.getZydm();
		String bjdm = comForm.getBjdm();
		String xqdm = comForm.getXqdm();
		String lddm = comForm.getLddm();
		String nj= comForm.getNj();
		String qsh = request.getParameter("qsh");
		String lc = request.getParameter("lc");
		String[] colList = null;
		if (dao.getXxdm().equalsIgnoreCase(Globals.XXDM_HZZY)) {// 杭州职业技术学院
			request.setAttribute("showhzy", "showhzy");
		}
		if (nj != null && !nj.equalsIgnoreCase(""))
			query.append(" and nj = '" + nj + "'");
		if (xn != null && !xn.equalsIgnoreCase(""))
			query.append(" and xn = '" + xn + "'");
		if (xq != null && !xq.equalsIgnoreCase(""))
			query.append(" and xq = '" + xq + "'");
		if (xh != null && !xh.equalsIgnoreCase(""))
			query.append(" and xh like  '%" + xh + "%'");
		if (xydm != null && !xydm.equalsIgnoreCase(""))
			query.append(" and xydm = '" + xydm + "'");
		if (zydm != null && !zydm.equalsIgnoreCase(""))
			query.append(" and zydm = '" + zydm + "'");
		if (bjdm != null && !bjdm.equalsIgnoreCase(""))
			query.append(" and bjdm = '" + bjdm + "'");
		if ((xqdm != null) && !xqdm.equalsIgnoreCase(""))
			query.append(" and xqdm = '" + xqdm + "'");
		if ((lddm != null) && !lddm.equalsIgnoreCase(""))
			query.append(" and lddm = '" + lddm + "' ");
		if (!Base.isNull(qsh) && !qsh.equalsIgnoreCase("qsh")) // 异动后宿舍号
			query.append(" and qsh='"+qsh+"'   ");
		if (!Base.isNull(lc) && !lc.equalsIgnoreCase("lc"))// 异动后楼层
			query.append("and cs ='" + lc + "'");
        if(!Base.isNull(gzkssj)){
        	query.append(" and ydsj >= '" + gzkssj + "'");
        }
        if(!Base.isNull(gzjssj)){
        	query.append(" and ydsj <= '" + gzjssj + "'");
        }
        if(!Base.isNull(xm)){
        	query.append(" and xm like '%" + xm + "%'");
        }
        
		sql = " select rownum 行号,a.* from (select " + pk + " 主键,v.* from " + tableName + " v"
		+ query +" order by xh ,ydsj asc)a ";
		colList = new String[] { "主键", "行号", "xn", "xqmc", "xh", "xm", "xb",
				"ldmc", "ydqzs","ydqsfbz", "ydhzs","ydhsfbz","ydsj" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if (act != null && act.equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		sql = "select dm,xqmc from dm_zju_xq";
		List xiaoqquList = dao.getList(sql, new String[] {}, new String[] {
				"dm", "xqmc" });
		request.setAttribute("xiaoqquList", xiaoqquList);
		sql = "select lddm,ldmc from sslddmb ";
		if ((xqdm != null) && !xqdm.equals("")) {
			sql += " where xqdm='" + xqdm + "'";
		}
		sql += " order by lddm ";
		List ldList = dao.getList(sql, new String[] {}, new String[] { "lddm",
		"ldmc" });
		request.setAttribute("ldList", ldList);
		gyglDao.getLdLcQshList(request);
		gyglDao.getXyZyBjxx(request);
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", "view");// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("xxdm", dao.getXxdm());
		//读写权判断		
		String canWrite =  (Base.chkUPower(userName,"ssyd.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no";
		request.setAttribute("writeAble", canWrite);
		request.setAttribute("canWrite", canWrite);
		return mapping.findForward("success");
	}

	public static ActionForward GdnzXqStuRsTj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, Exception,
			WriteException {
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] xq = { "上", "下" };
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response
				.getOutputStream());
		WritableSheet ws = wwb.createSheet("学期学生人数统计", 0);

		WritableFont wf = new WritableFont(WritableFont.TIMES); // 构造字体格式
		wf.setPointSize(20);
		WritableCellFormat wcf = new WritableCellFormat(wf); // 构造指定字体格式的单元格格式
		wcf.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中
		wcf.setWrap(true); // 指定格式设置自动换行
		ws.mergeCells(0, 0, 6, 0); // 设置指定列-行合并单元格
		ws.setRowView(0, 550); // 设置指定行高
		ws.setRowView(1, 300);
		ws.setRowView(2, 300);
		ws.addCell(new Label(0, 0, Base.currXn + "学年度"
				+ xq[Integer.parseInt(Base.currXq) - 1] + "学期大专学生人数统计", wcf)); // 添加有指定格式的单元格值

		// 表头
		WritableFont wf1 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		wf1.setPointSize(12);
		wf1.setBoldStyle(WritableFont.BOLD);
		WritableCellFormat wcf1 = new WritableCellFormat(wf1); // 构造指定字体格式的单元格格式
		wcf1.setAlignment(Alignment.CENTRE);
		wcf1.setWrap(true);
		wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf1.setBorder(Border.ALL, BorderLineStyle.MEDIUM);

		// 内容
		WritableFont wf2 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		wf2.setPointSize(12);
		WritableCellFormat wcf2 = new WritableCellFormat(wf2); // 构造指定字体格式的单元格格式
		wcf2.setAlignment(Alignment.CENTRE);
		wcf2.setWrap(true);
		wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf2.setBorder(Border.ALL, BorderLineStyle.THIN);

		// 学院内容
		WritableFont wf3 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		wf3.setPointSize(12);
		wf3.setBoldStyle(WritableFont.BOLD);
		WritableCellFormat wcf3 = new WritableCellFormat(wf3); // 构造指定字体格式的单元格格式
		wcf3.setAlignment(Alignment.CENTRE);
		wcf3.setWrap(true);
		wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf3.setBorder(Border.TOP, BorderLineStyle.THIN);
		wcf3.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		wcf3.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);

		// 合计内容
		WritableFont wf4 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		wf4.setPointSize(12);
		wf4.setBoldStyle(WritableFont.BOLD);
		WritableCellFormat wcf4 = new WritableCellFormat(wf4); // 构造指定字体格式的单元格格式
		wcf4.setAlignment(Alignment.CENTRE);
		wcf4.setWrap(true);
		wcf4.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf4.setBorder(Border.ALL, BorderLineStyle.THIN);

		ws.setRowView(1, 200);
		ws.setRowView(1, 200);
		ws.setColumnView(0, 6);
		ws.setColumnView(1, 18);
		ws.setColumnView(2, 19);
		ws.setColumnView(3, 12);
		ws.setColumnView(4, 12);
		ws.setColumnView(5, 12);
		ws.setColumnView(6, 12);
		ws.addCell(new Label(0, 2, "系别", wcf1));
		ws.addCell(new Label(1, 2, "专业", wcf1));
		ws.addCell(new Label(2, 2, "班级", wcf1));
		ws.addCell(new Label(3, 2, "班级数", wcf1));
		ws.addCell(new Label(4, 2, "总人数", wcf1));
		ws.addCell(new Label(5, 2, "休学人数", wcf1));
		ws.addCell(new Label(6, 2, "实际在校", wcf1));

		List<HashMap<String, String>> xyList = Base.getXyList(); // 获取学院列表
		int xybjs = 0;
		int zbjs = 0;
		int zrs = 0;
		int zxxrs = 0;
		int zsjrs = 0;
		for (int i = 0; i < xyList.size(); i++) {
			List<HashMap<String, String>> zyList = dao.getZyList(xyList.get(i).get("xydm")); // 获取专业列表
			int zybjs = xybjs;
			int xyrs = 0;
			int xyxxrs = 0;
			int xysjrs = 0;
			int tmp_xybjs = 0;
			if (zyList.size() > 0) {
				for (int j = 0; j < zyList.size(); j++) {
					sql = "select count(*) zynjs from (select distinct nj from view_njxyzybj where xydm=? and zydm=?)";
					String tmp_zynjs = dao.getOneRs(sql, new String[] {
							xyList.get(i).get("xydm"),
							zyList.get(j).get("zydm") }, "zynjs"); // 获取专业年级数
					sql = "select count(*) zybjs from view_njxyzybj where zydm=?";
					String tmp_zybjs = dao.getOneRs(sql, new String[] { zyList
							.get(j).get("zydm") }, "zybjs"); // 获取专业班级数

					// 添加班级
					List<HashMap<String, String>> bjList = dao.getBjList(xyList
							.get(i).get("xydm"), zyList.get(j).get("zydm")); // 获取班级列表
					int zyrs = 0;
					int zyxxrs = 0;
					int zysjrs = 0;
					if (bjList.size() > 0) {
						for (int b = 0; b < bjList.size(); b++) {
							sql = "select rs,xxrs,sjrs from view_gdnz_bj_dormstuinfo where bjdm=?"; // 获取班级人数信息
							String[] bjrsList = dao.getOneRs(sql,
									new String[] { bjList.get(b).get("bjdm") },
									new String[] { "rs", "xxrs", "sjrs" });
							ws.addCell(new Label(2, 3 + zybjs + b, bjList
									.get(b).get("bjmc"), wcf2));
							ws.addCell(new Label(3, 3 + zybjs + b, "1", wcf2));
							if (bjrsList != null) {
								ws.addCell(new Label(4, 3 + zybjs + b,
										bjrsList[0], wcf2));
								ws.addCell(new Label(5, 3 + zybjs + b,
										bjrsList[1], wcf2));
								ws.addCell(new Label(6, 3 + zybjs + b,
										bjrsList[2], wcf2));
								zyrs = zyrs + Integer.parseInt(bjrsList[0]); // 获取专业人数
								zyxxrs = zyxxrs + Integer.parseInt(bjrsList[1]); // 获取专业休学人数
								zysjrs = zysjrs + Integer.parseInt(bjrsList[2]); // 获取专业实际人数
							} else {
								ws.addCell(new Label(4, 3 + zybjs + b, "0",
										wcf2));
								ws.addCell(new Label(5, 3 + zybjs + b, "0",
										wcf2));
								ws.addCell(new Label(6, 3 + zybjs + b, "0",
										wcf2));
								zyrs = zyrs + 0; // 获取专业人数
								zyxxrs = zyxxrs + 0; // 获取专业休学人数
								zysjrs = zysjrs + 0; // 获取专业实际人数
							}
						}
						tmp_xybjs = tmp_xybjs + bjList.size();
					} else {
						break;
					}

					// 添加专业
					ws.mergeCells(1, zybjs + 3, 1, zybjs
							+ Integer.parseInt(tmp_zybjs) + 2);
					ws.addCell(new Label(1, zybjs + 3, zyList.get(j)
							.get("zymc"), wcf2));
					zybjs = zybjs + Integer.parseInt(tmp_zybjs);

					// 添加合计
					sql = "select distinct substr(nj,3,2) nj from view_njxyzybj where xydm=? and zydm=? order by nj";
					String[] njArrary = dao.getRs(sql, new String[] {
							xyList.get(i).get("xydm"),
							zyList.get(j).get("zydm") }, "nj"); // 获取专业年级
					for (int n = 0; n < njArrary.length; n++) {
						ws.addCell(new Label(2, zybjs + 3 + n, njArrary[n]
						                                                + "级", wcf2));
						sql = "select count(*) zynjbjs from view_njxyzybj where zydm=? and nj=?";
						String zynjbjs = dao.getOneRs(sql,
								new String[] { zyList.get(j).get("zydm"),
								"20" + njArrary[n] }, "zynjbjs"); // 获取专业年级班级数
						sql = "select rs,xxrs,sjrs from view_gdnz_zy_dormstuinfo where zydm=? and nj=?";
						String[] zynjrsList = dao.getOneRs(sql,
								new String[] { zyList.get(j).get("zydm"),
								"20" + njArrary[n] }, new String[] {
								"rs", "xxrs", "sjrs" }); // 获取专业年级人数
						ws.addCell(new Label(3, zybjs + 3 + n, zynjbjs, wcf2));
						if (zynjrsList == null) {
							ws.addCell(new Label(4, zybjs + 3 + n, "0", wcf2));
							ws.addCell(new Label(5, zybjs + 3 + n, "0", wcf2));
							ws.addCell(new Label(6, zybjs + 3 + n, "0", wcf2));
						} else {
							ws.addCell(new Label(4, zybjs + 3 + n,
									zynjrsList[0], wcf2));
							ws.addCell(new Label(5, zybjs + 3 + n,
									zynjrsList[1], wcf2));
							ws.addCell(new Label(6, zybjs + 3 + n,
									zynjrsList[2], wcf2));
						}
					}
					ws.addCell(new Label(2, zybjs + 3 + njArrary.length, "合计",
							wcf2));
					ws.addCell(new Label(3, zybjs + 3 + njArrary.length,
							tmp_zybjs, wcf2));
					ws.addCell(new Label(4, zybjs + 3 + njArrary.length, String
							.valueOf(zyrs), wcf2));
					ws.addCell(new Label(5, zybjs + 3 + njArrary.length, String
							.valueOf(zyxxrs), wcf2));
					ws.addCell(new Label(6, zybjs + 3 + njArrary.length, String
							.valueOf(zysjrs), wcf2));
					ws.mergeCells(1, zybjs + 3, 1, zybjs
							+ Integer.parseInt(tmp_zynjs) + 3);
					ws.addCell(new Label(1, zybjs + 3, "专业合计", wcf2));
					zybjs = zybjs + Integer.parseInt(tmp_zynjs) + 1;
					xyrs = xyrs + zyrs;
					xyxxrs = xyxxrs + zyxxrs;
					xysjrs = xysjrs + zysjrs;
				}
				sql = "select count(*) xynjs from (select distinct nj from view_njxyzybj where xydm=?)";
				String tmp_xynjs = dao.getOneRs(sql, new String[] { xyList.get(
						i).get("xydm") }, "xynjs"); // 获取学院年级数
				ws.mergeCells(0, xybjs + 3, 0, zybjs
						+ Integer.parseInt(tmp_xynjs) + 3);
				ws.addCell(new Label(0, xybjs + 3, xyList.get(i).get("xymc"),
						wcf3));
				ws.mergeCells(1, zybjs + 3, 1, zybjs
						+ Integer.parseInt(tmp_xynjs) + 3);
				ws.addCell(new Label(1, zybjs + 3, "系人数合计", wcf3));

				// 添加学院合计
				sql = "select distinct substr(nj,3,2) nj from view_njxyzybj where xydm=? order by nj";
				String[] njArrary = dao.getRs(sql, new String[] { xyList.get(i)
						.get("xydm") }, "nj"); // 获取学院年级
				for (int n = 0; n < njArrary.length; n++) {
					ws.addCell(new Label(2, zybjs + 3 + n, njArrary[n] + "级",
							wcf4));
					sql = "select count(*) xynjbjs from view_njxyzybj where xydm=? and nj=?";
					String xynjbjs = dao.getOneRs(sql, new String[] {
							xyList.get(i).get("xydm"), "20" + njArrary[n] },
					"xynjbjs"); // 获取学院年级班级数
					sql = "select rs,xxrs,sjrs from view_gdnz_xy_dormstuinfo where xydm=? and nj=?";
					String[] xynjrsList = dao.getOneRs(sql, new String[] {
							xyList.get(i).get("xydm"), "20" + njArrary[n] },
							new String[] { "rs", "xxrs", "sjrs" }); // 获取学院年级人数
					ws.addCell(new Label(3, zybjs + 3 + n, xynjbjs, wcf4));
					if (xynjrsList == null) {
						ws.addCell(new Label(4, zybjs + 3 + n, "0", wcf4));
						ws.addCell(new Label(5, zybjs + 3 + n, "0", wcf4));
						ws.addCell(new Label(6, zybjs + 3 + n, "0", wcf4));
					} else {
						ws.addCell(new Label(4, zybjs + 3 + n, xynjrsList[0],
								wcf4));
						ws.addCell(new Label(5, zybjs + 3 + n, xynjrsList[1],
								wcf4));
						ws.addCell(new Label(6, zybjs + 3 + n, xynjrsList[2],
								wcf4));
					}
				}
				ws
				.addCell(new Label(2, zybjs + 3 + njArrary.length,
						"合计", wcf3));
				ws.addCell(new Label(3, zybjs + 3 + njArrary.length, String
						.valueOf(tmp_xybjs), wcf3));
				ws.addCell(new Label(4, zybjs + 3 + njArrary.length, String
						.valueOf(xyrs), wcf3));
				ws.addCell(new Label(5, zybjs + 3 + njArrary.length, String
						.valueOf(xyxxrs), wcf3));
				ws.addCell(new Label(6, zybjs + 3 + njArrary.length, String
						.valueOf(xysjrs), wcf3));

				xybjs = zybjs + Integer.parseInt(tmp_xynjs) + 1; // 获取学院班级数
				zbjs = zbjs + tmp_xybjs;
				zrs = zrs + xyrs;
				zxxrs = zxxrs + xyxxrs;
				zsjrs = zsjrs + xysjrs;
			}
		}
		// 添加全校统计
		// sql = "select distinct nj from view_njxyzybj";
		// String njs = dao.getOneRs(sql, new String[]{}, "nj"); //获取学年级数
		sql = "select distinct substr(nj,3,2) nj from view_njxyzybj order by nj";
		String[] njArrary = dao.getRs(sql, new String[] {}, "nj"); // 获取学年级
		for (int n = 0; n < njArrary.length; n++) {
			ws.addCell(new Label(2, xybjs + 3 + n, njArrary[n] + "级", wcf4));
			sql = "select count(*) njbjs from view_njxyzybj where nj=?";
			String njbjs = dao.getOneRs(sql,
					new String[] { "20" + njArrary[n] }, "njbjs"); // 获取年级班级数
			sql = "select rs,xxrs,sjrs from view_gdnz_nj_dormstuinfo where nj=?";
			String[] njrsList = dao.getOneRs(sql, new String[] { "20"
					+ njArrary[n] }, new String[] { "rs", "xxrs", "sjrs" }); // 获取年级人数
			ws.addCell(new Label(3, xybjs + 3 + n, njbjs, wcf4));
			if (njrsList == null) {
				ws.addCell(new Label(4, xybjs + 3 + n, "0", wcf4));
				ws.addCell(new Label(5, xybjs + 3 + n, "0", wcf4));
				ws.addCell(new Label(6, xybjs + 3 + n, "0", wcf4));
			} else {
				ws.addCell(new Label(4, xybjs + 3 + n, njrsList[0], wcf4));
				ws.addCell(new Label(5, xybjs + 3 + n, njrsList[1], wcf4));
				ws.addCell(new Label(6, xybjs + 3 + n, njrsList[2], wcf4));
			}
		}
		ws.mergeCells(0, xybjs + 3, 1, xybjs + 3 + njArrary.length);
		ws.addCell(new Label(0, xybjs + 3, "全校人数统计", wcf3));
		ws.addCell(new Label(2, xybjs + 3 + njArrary.length, "合计", wcf3));
		ws.addCell(new Label(3, xybjs + 3 + njArrary.length, String
				.valueOf(zbjs), wcf3));
		ws.addCell(new Label(4, xybjs + 3 + njArrary.length, String
				.valueOf(zrs), wcf3));
		ws.addCell(new Label(5, xybjs + 3 + njArrary.length, String
				.valueOf(zxxrs), wcf3));
		ws.addCell(new Label(6, xybjs + 3 + njArrary.length, String
				.valueOf(zsjrs), wcf3));

		wwb.write();
		wwb.close();
		return mapping.findForward("success");
	}

	public static ActionForward GdnzNdZxStuTj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response
				.getOutputStream());
		WritableSheet ws = wwb.createSheet("年度在校学生统计表", 0);

		// sql = "select distinct cws from view_ssxx order by to_number(cws)";
		// String []cws = dao.getRs(sql, new String[]{}, "cws"); //获取宿舍床位信息

		WritableFont wf = new WritableFont(WritableFont.TIMES); // 构造字体格式
		wf.setPointSize(20);
		WritableCellFormat wcf = new WritableCellFormat(wf); // 构造指定字体格式的单元格格式
		wcf.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中
		wcf.setWrap(true); // 指定格式设置自动换行
		// ws.mergeCells(0, 0, 7 + cws.length, 0); //设置指定列-行合并单元格
		ws.mergeCells(0, 0, 9, 0);
		ws.setRowView(0, 550); // 设置指定行高
		ws.setRowView(1, 300);
		ws.setRowView(2, 300);
		ws.addCell(new Label(0, 0, Base.currXn + "学年度在校学生统计表计", wcf)); // 添加有指定格式的单元格值

		// 表头
		WritableFont wf1 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		wf1.setPointSize(12);
		wf1.setBoldStyle(WritableFont.BOLD);
		WritableCellFormat wcf1 = new WritableCellFormat(wf1); // 构造指定字体格式的单元格格式
		wcf1.setAlignment(Alignment.CENTRE);
		wcf1.setWrap(true);
		wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf1.setBorder(Border.ALL, BorderLineStyle.MEDIUM);

		// 内容
		WritableFont wf2 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		wf2.setPointSize(12);
		WritableCellFormat wcf2 = new WritableCellFormat(wf2); // 构造指定字体格式的单元格格式
		wcf2.setAlignment(Alignment.CENTRE);
		wcf2.setWrap(true);
		wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf2.setBorder(Border.ALL, BorderLineStyle.THIN);

		// 学院内容
		WritableFont wf3 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		wf3.setPointSize(12);
		wf3.setBoldStyle(WritableFont.BOLD);
		WritableCellFormat wcf3 = new WritableCellFormat(wf3); // 构造指定字体格式的单元格格式
		wcf3.setAlignment(Alignment.CENTRE);
		wcf3.setWrap(true);
		wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf3.setBorder(Border.TOP, BorderLineStyle.THIN);
		wcf3.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		wcf3.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);

		// 合计内容
		WritableFont wf4 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		wf4.setPointSize(12);
		wf4.setBoldStyle(WritableFont.BOLD);
		WritableCellFormat wcf4 = new WritableCellFormat(wf4); // 构造指定字体格式的单元格格式
		wcf4.setAlignment(Alignment.CENTRE);
		wcf4.setWrap(true);
		wcf4.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf4.setBorder(Border.ALL, BorderLineStyle.THIN);

		ws.setRowView(1, 200);
		ws.setRowView(1, 200);
		ws.setColumnView(0, 6);
		ws.setColumnView(1, 18);
		ws.setColumnView(2, 19);
		ws.setColumnView(3, 12);
		ws.setColumnView(4, 12);
		ws.setColumnView(5, 12);
		ws.setColumnView(6, 12);
		// for(int c=0; c<cws.length; c++){
		// ws.setColumnView(7+c, 10);
		// }
		// ws.setColumnView(7 + cws.length, 10);
		ws.setColumnView(7, 12);
		ws.setColumnView(8, 12);
		ws.setColumnView(9, 10);
		ws.addCell(new Label(0, 2, "系别", wcf1));
		ws.addCell(new Label(1, 2, "专业", wcf1));
		ws.addCell(new Label(2, 2, "班级", wcf1));
		ws.addCell(new Label(3, 2, "班级数", wcf1));
		ws.addCell(new Label(4, 2, "总人数", wcf1));
		ws.addCell(new Label(5, 2, "休学人数", wcf1));
		ws.addCell(new Label(6, 2, "实际在校", wcf1));
		// for(int c=0; c<cws.length; c++){
		// ws.addCell(new Label(7 + c,2,cws[c]+"人间",wcf1));
		// }
		// ws.addCell(new Label(7 + cws.length,2,"男生",wcf1));
		ws.addCell(new Label(7, 2, "六人间", wcf1));
		ws.addCell(new Label(8, 2, "七人间", wcf1));
		ws.addCell(new Label(9, 2, "男生", wcf1));
		List<HashMap<String, String>> xyList = Base.getXyList(); // 获取学院列表
		int xybjs = 0;
		int zbjs = 0;
		int zrs = 0;
		int zxxrs = 0;
		int zsjrs = 0;
		int zrs6 = 0;
		int zrs10 = 0;
		int znsrs = 0;
		for (int i = 0; i < xyList.size(); i++) {
			List<HashMap<String, String>> zyList = dao.getZyList(xyList.get(i)
					.get("xydm")); // 获取专业列表
			int zybjs = xybjs;
			int xyrs = 0;
			int xyxxrs = 0;
			int xysjrs = 0;
			int xyrs6 = 0;
			int xyrs10 = 0;
			int xynsrs = 0;
			int tmp_xybjs = 0;
			if (zyList.size() > 0) {
				for (int j = 0; j < zyList.size(); j++) {
					sql = "select count(*) zynjs from (select distinct nj from view_njxyzybj where xydm=? and zydm=?)";
					String tmp_zynjs = dao.getOneRs(sql, new String[] {
							xyList.get(i).get("xydm"),
							zyList.get(j).get("zydm") }, "zynjs"); // 获取专业年级数
					sql = "select count(*) zybjs from view_njxyzybj where zydm=?";
					String tmp_zybjs = dao.getOneRs(sql, new String[] { zyList
							.get(j).get("zydm") }, "zybjs"); // 获取专业班级数

					// 添加班级
					List<HashMap<String, String>> bjList = dao.getBjList(xyList
							.get(i).get("xydm"), zyList.get(j).get("zydm")); // 获取班级列表
					int zyrs = 0;
					int zyxxrs = 0;
					int zysjrs = 0;
					int zyrs6 = 0;
					int zyrs10 = 0;
					int zynsrs = 0;
					if (bjList.size() > 0) {
						for (int b = 0; b < bjList.size(); b++) {
							sql = "select rs,nsrs,xxrs,sjrs,rs6,rs10 from view_gdnz_bj_dormstuinfo where bjdm=?"; // 获取班级人数信息
							String[] bjrsList = dao.getOneRs(sql,
									new String[] { bjList.get(b).get("bjdm") },
									new String[] { "rs", "nsrs", "xxrs",
									"sjrs", "rs6", "rs10" });
							ws.addCell(new Label(2, 3 + zybjs + b, bjList
									.get(b).get("bjmc"), wcf2));
							ws.addCell(new Label(3, 3 + zybjs + b, "1", wcf2));
							if (bjrsList != null) {
								ws.addCell(new Label(4, 3 + zybjs + b,
										bjrsList[0], wcf2));
								ws.addCell(new Label(5, 3 + zybjs + b,
										bjrsList[2], wcf2));
								ws.addCell(new Label(6, 3 + zybjs + b,
										bjrsList[3], wcf2));
								ws.addCell(new Label(7, 3 + zybjs + b,
										bjrsList[4], wcf2));
								ws.addCell(new Label(8, 3 + zybjs + b,
										bjrsList[5], wcf2));
								ws.addCell(new Label(9, 3 + zybjs + b,
										bjrsList[1], wcf2));
								zyrs = zyrs + Integer.parseInt(bjrsList[0]); // 获取专业人数
								zyxxrs = zyxxrs + Integer.parseInt(bjrsList[2]); // 获取专业休学人数
								zysjrs = zysjrs + Integer.parseInt(bjrsList[3]); // 获取专业实际人数
								zyrs6 = zyrs6 + Integer.parseInt(bjrsList[4]); // 获取专业６人间人数
								zyrs10 = zyrs10 + Integer.parseInt(bjrsList[5]); // 获取专业10人间人数
								zynsrs = zynsrs + Integer.parseInt(bjrsList[1]); // 获取专业男生人数
							} else {
								ws.addCell(new Label(4, 3 + zybjs + b, "0",
										wcf2));
								ws.addCell(new Label(5, 3 + zybjs + b, "0",
										wcf2));
								ws.addCell(new Label(6, 3 + zybjs + b, "0",
										wcf2));
								ws.addCell(new Label(7, 3 + zybjs + b, "0",
										wcf2));
								ws.addCell(new Label(8, 3 + zybjs + b, "0",
										wcf2));
								ws.addCell(new Label(9, 3 + zybjs + b, "0",
										wcf2));
								zyrs = zyrs + 0; // 获取专业人数
								zyxxrs = zyxxrs + 0; // 获取专业休学人数
								zysjrs = zysjrs + 0; // 获取专业实际人数
								zyrs6 = zyrs6 + 0; // 获取专业６人间人数
								zyrs10 = zyrs10 + 0; // 获取专业10人间人数
								zynsrs = zynsrs + 0; // 获取专业男生人数
							}
						}
						tmp_xybjs = tmp_xybjs + bjList.size();
					} else {
						break;
					}

					// 添加专业
					ws.mergeCells(1, zybjs + 3, 1, zybjs
							+ Integer.parseInt(tmp_zybjs) + 2);
					ws.addCell(new Label(1, zybjs + 3, zyList.get(j)
							.get("zymc"), wcf2));
					zybjs = zybjs + Integer.parseInt(tmp_zybjs);

					// 添加合计
					sql = "select distinct substr(nj,3,2) nj from view_njxyzybj where xydm=? and zydm=? order by nj";
					String[] njArrary = dao.getRs(sql, new String[] {
							xyList.get(i).get("xydm"),
							zyList.get(j).get("zydm") }, "nj"); // 获取专业年级
					for (int n = 0; n < njArrary.length; n++) {
						ws.addCell(new Label(2, zybjs + 3 + n, njArrary[n]
						                                                + "级", wcf2));
						sql = "select count(*) zynjbjs from view_njxyzybj where zydm=? and nj=?";
						String zynjbjs = dao.getOneRs(sql,
								new String[] { zyList.get(j).get("zydm"),
								"20" + njArrary[n] }, "zynjbjs"); // 获取专业年级班级数
						sql = "select rs,nsrs,xxrs,sjrs,rs6,rs10 from view_gdnz_zy_dormstuinfo where zydm=? and nj=?";
						String[] zynjrsList = dao.getOneRs(sql,
								new String[] { zyList.get(j).get("zydm"),
								"20" + njArrary[n] }, new String[] {
								"rs", "nsrs", "xxrs", "sjrs", "rs6",
						"rs10" }); // 获取专业年级人数
						ws.addCell(new Label(3, zybjs + 3 + n, zynjbjs, wcf2));
						if (zynjrsList == null) {
							ws.addCell(new Label(4, zybjs + 3 + n, "0", wcf2));
							ws.addCell(new Label(5, zybjs + 3 + n, "0", wcf2));
							ws.addCell(new Label(6, zybjs + 3 + n, "0", wcf2));
							ws.addCell(new Label(7, zybjs + 3 + n, "0", wcf2));
							ws.addCell(new Label(8, zybjs + 3 + n, "0", wcf2));
							ws.addCell(new Label(9, zybjs + 3 + n, "0", wcf2));
						} else {
							ws.addCell(new Label(4, zybjs + 3 + n,
									zynjrsList[0], wcf2));
							ws.addCell(new Label(5, zybjs + 3 + n,
									zynjrsList[2], wcf2));
							ws.addCell(new Label(6, zybjs + 3 + n,
									zynjrsList[3], wcf2));
							ws.addCell(new Label(7, zybjs + 3 + n,
									zynjrsList[4], wcf2));
							ws.addCell(new Label(8, zybjs + 3 + n,
									zynjrsList[5], wcf2));
							ws.addCell(new Label(9, zybjs + 3 + n,
									zynjrsList[1], wcf2));
						}
					}
					ws.addCell(new Label(2, zybjs + 3 + njArrary.length, "合计",
							wcf2));
					ws.addCell(new Label(3, zybjs + 3 + njArrary.length,
							tmp_zybjs, wcf2));
					ws.addCell(new Label(4, zybjs + 3 + njArrary.length, String
							.valueOf(zyrs), wcf2));
					ws.addCell(new Label(5, zybjs + 3 + njArrary.length, String
							.valueOf(zyxxrs), wcf2));
					ws.addCell(new Label(6, zybjs + 3 + njArrary.length, String
							.valueOf(zysjrs), wcf2));
					ws.addCell(new Label(7, zybjs + 3 + njArrary.length, String
							.valueOf(zyrs6), wcf2));
					ws.addCell(new Label(8, zybjs + 3 + njArrary.length, String
							.valueOf(zyrs10), wcf2));
					ws.addCell(new Label(9, zybjs + 3 + njArrary.length, String
							.valueOf(zynsrs), wcf2));
					ws.mergeCells(1, zybjs + 3, 1, zybjs
							+ Integer.parseInt(tmp_zynjs) + 3);
					ws.addCell(new Label(1, zybjs + 3, "专业合计", wcf2));
					zybjs = zybjs + Integer.parseInt(tmp_zynjs) + 1;
					xyrs = xyrs + zyrs;
					xyxxrs = xyxxrs + zyxxrs; // 获取学院休学人数
					xysjrs = xysjrs + zysjrs; // 获取学院实际人数
					xyrs6 = xyrs6 + zyrs6; // 获取学院６人间人数
					xyrs10 = xyrs10 + zyrs10; // 获取学院10人间人数
					xynsrs = xynsrs + zynsrs; // 获取学院男生人数
				}
				sql = "select count(*) xynjs from (select distinct nj from view_njxyzybj where xydm=?)";
				String tmp_xynjs = dao.getOneRs(sql, new String[] { xyList.get(
						i).get("xydm") }, "xynjs"); // 获取学院年级数
				ws.mergeCells(0, xybjs + 3, 0, zybjs
						+ Integer.parseInt(tmp_xynjs) + 3);
				ws.addCell(new Label(0, xybjs + 3, xyList.get(i).get("xymc"),
						wcf3));
				ws.mergeCells(1, zybjs + 3, 1, zybjs
						+ Integer.parseInt(tmp_xynjs) + 3);
				ws.addCell(new Label(1, zybjs + 3, "系人数合计", wcf3));

				// 添加学院合计
				sql = "select distinct substr(nj,3,2) nj from view_njxyzybj where xydm=? order by nj";
				String[] njArrary = dao.getRs(sql, new String[] { xyList.get(i)
						.get("xydm") }, "nj"); // 获取学院年级
				for (int n = 0; n < njArrary.length; n++) {
					ws.addCell(new Label(2, zybjs + 3 + n, njArrary[n] + "级",
							wcf4));
					sql = "select count(*) xynjbjs from view_njxyzybj where xydm=? and nj=?";
					String xynjbjs = dao.getOneRs(sql, new String[] {
							xyList.get(i).get("xydm"), "20" + njArrary[n] },
					"xynjbjs"); // 获取学院年级班级数
					sql = "select rs,nsrs,xxrs,sjrs,rs6,rs10 from view_gdnz_xy_dormstuinfo where xydm=? and nj=?";
					String[] xynjrsList = dao.getOneRs(sql, new String[] {
							xyList.get(i).get("xydm"), "20" + njArrary[n] },
							new String[] { "rs", "nsrs", "xxrs", "sjrs", "rs6",
					"rs10" }); // 获取学院年级人数
					ws.addCell(new Label(3, zybjs + 3 + n, xynjbjs, wcf4));
					if (xynjrsList == null) {
						ws.addCell(new Label(4, zybjs + 3 + n, "0", wcf4));
						ws.addCell(new Label(5, zybjs + 3 + n, "0", wcf4));
						ws.addCell(new Label(6, zybjs + 3 + n, "0", wcf4));
						ws.addCell(new Label(7, zybjs + 3 + n, "0", wcf4));
						ws.addCell(new Label(8, zybjs + 3 + n, "0", wcf4));
						ws.addCell(new Label(9, zybjs + 3 + n, "0", wcf4));
					} else {
						ws.addCell(new Label(4, zybjs + 3 + n, xynjrsList[0],
								wcf4));
						ws.addCell(new Label(5, zybjs + 3 + n, xynjrsList[2],
								wcf4));
						ws.addCell(new Label(6, zybjs + 3 + n, xynjrsList[3],
								wcf4));
						ws.addCell(new Label(7, zybjs + 3 + n, xynjrsList[4],
								wcf4));
						ws.addCell(new Label(8, zybjs + 3 + n, xynjrsList[5],
								wcf4));
						ws.addCell(new Label(9, zybjs + 3 + n, xynjrsList[1],
								wcf4));
					}
				}
				ws
				.addCell(new Label(2, zybjs + 3 + njArrary.length,
						"合计", wcf3));
				ws.addCell(new Label(3, zybjs + 3 + njArrary.length, String
						.valueOf(tmp_xybjs), wcf3));
				ws.addCell(new Label(4, zybjs + 3 + njArrary.length, String
						.valueOf(xyrs), wcf3));
				ws.addCell(new Label(5, zybjs + 3 + njArrary.length, String
						.valueOf(xyxxrs), wcf3));
				ws.addCell(new Label(6, zybjs + 3 + njArrary.length, String
						.valueOf(xysjrs), wcf3));
				ws.addCell(new Label(7, zybjs + 3 + njArrary.length, String
						.valueOf(xyrs6), wcf3));
				ws.addCell(new Label(8, zybjs + 3 + njArrary.length, String
						.valueOf(xyrs10), wcf3));
				ws.addCell(new Label(9, zybjs + 3 + njArrary.length, String
						.valueOf(xynsrs), wcf3));

				xybjs = zybjs + Integer.parseInt(tmp_xynjs) + 1; // 获取学院班级数
				zbjs = zbjs + tmp_xybjs;
				zrs = zrs + xyrs;
				zxxrs = xyxxrs + zxxrs; // 获取总休学人数
				zsjrs = xysjrs + zsjrs; // 获取总实际人数
				zrs6 = xyrs6 + zrs6; // 获取总６人间人数
				zrs10 = xyrs10 + zrs10; // 获取总10人间人数
				znsrs = xynsrs + znsrs; // 获取总男生人数
			}
		}
		// 添加全校统计
		// sql = "select distinct nj from view_njxyzybj";
		// String njs = dao.getOneRs(sql, new String[]{}, "nj"); //获取学年级数
		sql = "select distinct substr(nj,3,2) nj from view_njxyzybj order by nj";
		String[] njArrary = dao.getRs(sql, new String[] {}, "nj"); // 获取学年级
		for (int n = 0; n < njArrary.length; n++) {
			ws.addCell(new Label(2, xybjs + 3 + n, njArrary[n] + "级", wcf4));
			sql = "select count(*) njbjs from view_njxyzybj where nj=?";
			String njbjs = dao.getOneRs(sql,
					new String[] { "20" + njArrary[n] }, "njbjs"); // 获取年级班级数
			sql = "select rs,nsrs,xxrs,sjrs,rs6,rs10 from view_gdnz_nj_dormstuinfo where nj=?";
			String[] njrsList = dao.getOneRs(sql, new String[] { "20"
					+ njArrary[n] }, new String[] { "rs", "nsrs", "xxrs",
					"sjrs", "rs6", "rs10" }); // 获取年级人数
			ws.addCell(new Label(3, xybjs + 3 + n, njbjs, wcf4));
			if (njrsList == null) {
				ws.addCell(new Label(4, xybjs + 3 + n, "0", wcf4));
				ws.addCell(new Label(5, xybjs + 3 + n, "0", wcf4));
				ws.addCell(new Label(6, xybjs + 3 + n, "0", wcf4));
				ws.addCell(new Label(7, xybjs + 3 + n, "0", wcf4));
				ws.addCell(new Label(8, xybjs + 3 + n, "0", wcf4));
				ws.addCell(new Label(9, xybjs + 3 + n, "0", wcf4));
			} else {
				ws.addCell(new Label(4, xybjs + 3 + n, njrsList[0], wcf4));
				ws.addCell(new Label(5, xybjs + 3 + n, njrsList[2], wcf4));
				ws.addCell(new Label(6, xybjs + 3 + n, njrsList[3], wcf4));
				ws.addCell(new Label(7, xybjs + 3 + n, njrsList[4], wcf4));
				ws.addCell(new Label(8, xybjs + 3 + n, njrsList[5], wcf4));
				ws.addCell(new Label(9, xybjs + 3 + n, njrsList[1], wcf4));
			}
		}
		ws.mergeCells(0, xybjs + 3, 1, xybjs + 3 + njArrary.length);
		ws.addCell(new Label(0, xybjs + 3, "全校人数统计", wcf3));
		ws.addCell(new Label(2, xybjs + 3 + njArrary.length, "合计", wcf3));
		ws.addCell(new Label(3, xybjs + 3 + njArrary.length, String
				.valueOf(zbjs), wcf3));
		ws.addCell(new Label(4, xybjs + 3 + njArrary.length, String
				.valueOf(zrs), wcf3));
		ws.addCell(new Label(5, xybjs + 3 + njArrary.length, String
				.valueOf(zxxrs), wcf3));
		ws.addCell(new Label(6, xybjs + 3 + njArrary.length, String
				.valueOf(zsjrs), wcf3));
		ws.addCell(new Label(7, xybjs + 3 + njArrary.length, String
				.valueOf(zrs6), wcf3));
		ws.addCell(new Label(8, xybjs + 3 + njArrary.length, String
				.valueOf(zrs10), wcf3));
		ws.addCell(new Label(9, xybjs + 3 + njArrary.length, String
				.valueOf(znsrs), wcf3));

		wwb.write();
		wwb.close();
		return mapping.findForward("success");
	}

	public static ActionForward GdnzDormFbSlt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream()); // 创建工作簿
		WritableSheet ws = wwb.createSheet("缩略图", 0); // 创建工作表
		//WritableCellFeatures wcfeat = new WritableCellFeatures(); // 构造单元格特性
		WritableFont wf = new WritableFont(WritableFont.TIMES); // 构造字体格式
		WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
		wf.setBoldStyle(WritableFont.NO_BOLD); // 设置字体格式(非粗体)
		wf.setColour(Colour.getInternalColour(20)); // 设置字体格式(红色)
		wf.setUnderlineStyle(UnderlineStyle.NO_UNDERLINE); // 设置字体格式(无下划线)
		wf.setPointSize(13); // 设置字体格式(大小)
		wcf.setFont(wf); // 设置指定字体格式的单元格格式
		wcf.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中
		wcf.setWrap(true); // 指定格式设置自动换行
		ws.mergeCells(1, 0, 2, 0); // 设置指定列-行合并单元格
		ws.setColumnView(0, 4); // 设置指定列的列宽
		ws.addCell(new Label(1, 0, "（备注）：", wcf)); // 添加有指定格式的单元格值

		WritableFont wf1 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		WritableCellFormat wcf1 = new WritableCellFormat(); // 构造单元格格式
		wf1.setPointSize(10);
		wcf1.setFont(wf1);
		wcf1.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.getInternalColour(20)); // 设置单元格外边线
		wcf1.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
		wcf1.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中
		wcf1.setBackground(Colour.getInternalColour(43));
		wcf1.setWrap(true);
		ws.mergeCells(1, 1, 4, 1);
		ws.setRowView(1, 650); // 设置指定行高
		ws.addCell(new Label(1, 1, "填充黄色表示该宿舍有空床位（其中宿舍号的数为其所空床位数）", wcf1));

		WritableFont wf2 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wf2.setPointSize(10);
		wcf2.setFont(wf2);
		wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.getInternalColour(20)); // 设置单元格外边线;
		wcf2.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
		wcf2.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中
		wcf2.setBackground(Colour.getInternalColour(42));
		wcf2.setWrap(true);
		ws.mergeCells(6, 1, 7, 1);
		ws.addCell(new Label(6, 1, "填充绿色的表示该宿舍为空空宿舍", wcf2));

		WritableFont wf3 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		WritableCellFormat wcf3 = new WritableCellFormat(); // 构造单元格格式
		wf3.setPointSize(10);
		wcf3.setFont(wf3);
		wcf3.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.getInternalColour(20)); // 设置单元格外边线
		wcf3.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
		wcf3.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中
		wcf3.setWrap(true);
		ws.mergeCells(9, 1, 10, 1);
		ws.addCell(new Label(9, 1, "无填充颜色的表示该已住满", wcf3));
		ws.mergeCells(12, 1, 23, 1);
		ws.addCell(new Label(12,1,"每个宿舍的详细资料均可在批注中查看（包括：班级、宿舍电话、舍长、舍员和该宿舍总住人数）如果个别宿舍的详细资料看不清楚或需要对其资料进行修改，则可用以下方法：右击所要查看\\修改的宿舍，选择编辑批注，通过拉大编辑框进一步查看或进行修改",wcf3));

		WritableFont wf4 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		WritableCellFormat wcf4 = new WritableCellFormat(); // 构造单元格格式
		wf4.setPointSize(15);
		wf4.setColour(Colour.getInternalColour(20));
		wf4.setBoldStyle(WritableFont.BOLD);
		wcf4.setFont(wf4);
		wcf4.setAlignment(Alignment.CENTRE);
		wcf4.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf4.setBorder(Border.TOP, BorderLineStyle.THICK, Colour.getInternalColour(20));
		wcf4.setBorder(Border.LEFT, BorderLineStyle.THICK, Colour.getInternalColour(20));
		wcf4.setBorder(Border.RIGHT, BorderLineStyle.THICK, Colour.getInternalColour(20));

		WritableFont wf5 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		WritableCellFormat wcf5 = new WritableCellFormat(); // 构造单元格格式
		wf5.setBoldStyle(WritableFont.BOLD);
		wcf5.setFont(wf5);
		wcf5.setAlignment(Alignment.CENTRE);
		wcf5.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf5.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

		WritableFont wf6 = new WritableFont(WritableFont.TIMES); // 构造字体格式
		WritableCellFormat wcf6 = new WritableCellFormat(); // 构造单元格格式
		wf6.setPointSize(12);
		wcf6.setFont(wf6);
		wcf6.setAlignment(Alignment.CENTRE);
		wcf6.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf6.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		wcf6.setWrap(true);

		// 空宿舍格式
		WritableCellFormat wcf7 = new WritableCellFormat(); // 构造单元格格式
		wcf7.setAlignment(Alignment.CENTRE);
		wcf7.setVerticalAlignment(VerticalAlignment.TOP);
		wcf7.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		wcf7.setWrap(true);
		wcf7.setBackground(Colour.getInternalColour(42));

		// 部份空宿舍格式
		WritableCellFormat wcf8 = new WritableCellFormat(); // 构造单元格格式
		wcf8.setAlignment(Alignment.CENTRE);
		wcf8.setVerticalAlignment(VerticalAlignment.TOP);
		wcf8.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		wcf8.setWrap(true);
		wcf8.setBackground(Colour.getInternalColour(43));

		// 已满宿舍格式
		WritableCellFormat wcf9 = new WritableCellFormat(); // 构造单元格格式
		wcf9.setAlignment(Alignment.CENTRE);
		wcf9.setVerticalAlignment(VerticalAlignment.TOP);
		wcf9.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		wcf9.setWrap(true);

		sql = "select distinct lddm,ldmc from view_ssxx order by lddm";
		List<HashMap<String, String>> ldList = dao.getList(sql,
				new String[] {}, new String[] { "lddm", "ldmc" });
		String[] szArrary = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十","十一", "十二" };
		if (!ldList.isEmpty()) {
			int maxCs = 0;
			
			for (int i = 0; i < ldList.size(); i++) {
				String lddm = ldList.get(i).get("lddm");
				String ldmc = ldList.get(i).get("ldmc");
				ws.mergeCells(0, 4 + maxCs, 23, 4 + maxCs);
				sql = "select max(nvl(cws,0)) cws from view_ssxx where lddm=?";
				String cws = dao.getOneRs(sql, new String[] { lddm }, "cws"); // 取床位数
				ws.addCell(new Label(0, 4 + maxCs, ldmc + "宿舍成员名单（"
						+ szArrary[Integer.parseInt(cws) - 1] + "人间）", wcf4));

				ws.setRowView(5 + maxCs, 500);
				ws.mergeCells(0, 5 + maxCs, 3, 5 + maxCs);
				ws.mergeCells(4, 5 + maxCs, 6, 5 + maxCs);
				ws.mergeCells(7, 5 + maxCs, 9, 5 + maxCs);
				ws.mergeCells(10, 5 + maxCs, 12, 5 + maxCs);
				ws.mergeCells(13, 5 + maxCs, 15, 5 + maxCs);
				ws.mergeCells(16, 5 + maxCs, 18, 5 + maxCs);
				sql = "select max(count(*)) maxsss from view_ssxx group by lddm,substr(qsh,1,1)";
				String maxSss = dao.getOneRs(sql, new String[] {}, "maxsss");
				if (Integer.parseInt(maxSss) <= 21) {
					ws.mergeCells(19, 5 + maxCs, 21, 5 + maxCs);
				}
				sql = "select count(*) zsss From view_ssxx where lddm=?";
				String zsss = dao.getOneRs(sql, new String[] { lddm }, "zsss"); // 取总宿舍数
				sql = "select sum(cws) zcws from view_ssxx where lddm=?";
				String zcws = dao.getOneRs(sql, new String[] { lddm }, "zcws"); // 取总床位数
				sql = "select count(*) ysyss from (select distinct qsh from view_xszsxx where lddm=? and jzrq is null)";
				String ysyss = dao.getOneRs(sql, new String[] { lddm }, "ysyss"); // 取已使用宿舍数
				sql = "select count(*) yzrs from view_xszsxx where lddm=? and jzrq is null";
				String yzrs = dao.getOneRs(sql, new String[] { lddm }, "yzrs"); // 取已入住学生数
				sql = "select count(*) ckss from view_ssxx where qsh not in (select qsh from view_xszsxx) and lddm=?";
				String ckss = dao.getOneRs(sql, new String[] { lddm }, "ckss"); // 纯空宿舍数
				ws.addCell(new Label(0, 5 + maxCs, "总宿舍数" + zsss, wcf5));
				ws.addCell(new Label(4, 5 + maxCs, "总床位数" + zcws, wcf5));
				ws.addCell(new Label(7, 5 + maxCs, "已用宿舍" + ysyss, wcf5));
				ws.addCell(new Label(10, 5 + maxCs, "已住人数" + yzrs, wcf5));
				ws.addCell(new Label(13, 5 + maxCs, "纯空宿舍" + ckss + "间", wcf5));
				ws.addCell(new Label(16, 5 + maxCs, "混空宿舍8间", wcf5));
				ws.addCell(new Label(19, 5 + maxCs, "混空床位11张", wcf5));

				sql = "select count(*) maxcs from (select distinct substr(qsh,1,1) cs from view_ssxx where lddm=?)";
				String tmp_maxCs = dao.getOneRs(sql, new String[] { lddm },
				"maxcs"); // 取最大层数

				sql = "select distinct substr(qsh,1,1) c from view_ssxx where lddm=? order by substr(qsh,1,1) desc";
				String[] c = dao.getRs(sql, new String[] { lddm }, "c"); // 取层
				for (int j = 0; j < Integer.parseInt(tmp_maxCs); j++) {
					ws.setRowView(6 + j + maxCs, 1000);
					ws.addCell(new Label(0, 6 + j + maxCs, c[j] + "楼", wcf6));

					sql = "select qsh from view_ssxx where lddm=? and substr(qsh,1,1) = ?";
					String[] qsh = dao.getRs(sql, new String[] { lddm, c[j] },"qsh"); // 取寝室
					for (int q = 0; q < qsh.length; q++) {
						sql = "select count(*) rs from view_xszsxx where lddm=? and qsh=? and jzrq is null";
						String rs = dao.getOneRs(sql, new String[] { lddm,qsh[q] }, "rs"); // 取寝室人数

						sql = "select bjmc,xm,qsdh from view_xszsxx where lddm=? and qsh=? and jzrq is null";
						List<HashMap<String, String>> xszsxx = dao.getList(sql,
								new String[] { lddm, qsh[q] }, new String[] {"bjmc", "xm", "qsdh" }); // 取寝室学生信息

						sql = "select (select xm from view_xsjbxx a where  a.xh=qsz) qsz from view_gygl_lczfpb where lddm=? and qsh=? and xn=? and xq=? and nd=?";
						String czxm = dao.getOneRs(sql,new String[] { lddm, qsh[q], Base.currXn,
								Base.currXq, Base.currNd }, "qsz"); // 获取寝室长信息

						if ("0".equalsIgnoreCase(rs)) {
							ws.addCell(new Label(1 + q, 6 + j + maxCs, ""+qsh[q],wcf7));
						} else if (!"0".equalsIgnoreCase(rs)
								&& Integer.parseInt(rs) < Integer.parseInt(cws)) {
							ws.addCell(new Label(1 + q, 6 + j + maxCs, xszsxx.get(0).get("bjmc")
									+ "\n" + qsh[q], wcf8));

						} else if (!"0".equalsIgnoreCase(rs)
								&& Integer.parseInt(rs) == Integer.parseInt(cws)) {
							ws.addCell(new Label(1 + q, 6 + j + maxCs, xszsxx.get(0).get("bjmc")
									+ "\n" + qsh[q], wcf9));
						}
						//读入批注内容
						String xm = "";
						String bjmc = "";
						String qsdh = "";
						if (!"0".equalsIgnoreCase(rs)
								&& Integer.parseInt(rs) <= Integer.parseInt(cws)) {
							bjmc = DealString.toString(xszsxx.get(0).get("bjmc"));
							qsdh = DealString.toString(xszsxx.get(0).get("qsdh"));
							for (int x = 0; x < xszsxx.size(); x++) {
								xm +=xszsxx.get(x).get("xm")+"\n";								
							}							
						}
						StringBuffer cell_Comment = new StringBuffer();
						cell_Comment.append(bjmc);
						cell_Comment.append("\n");
						cell_Comment.append("电话:");
						cell_Comment.append(qsdh);
						cell_Comment.append("\n");
						cell_Comment.append("舍长:");
						cell_Comment.append(czxm);
						cell_Comment.append("\n");
						cell_Comment.append("舍员:");
						cell_Comment.append(xm);
						cell_Comment.append("(共");
						cell_Comment.append(rs);
						cell_Comment.append("人)");
						WritableCellFeatures wcfeat = new WritableCellFeatures(); // 构造单元格特性
						WritableCell wc = ws.getWritableCell(1+q,6 + j + maxCs);//获取第1 + q列，6 + j + maxCs行单元格对象						
						wcfeat.setComment(cell_Comment.toString());
						wc.setCellFeatures(wcfeat);	
					}
				}
				maxCs = maxCs + Integer.parseInt(tmp_maxCs) + 3;
			}
		}
		wwb.write();
		wwb.close();
		return mapping.findForward("success");
	}

	public static ActionForward GdnzDormFpb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		List fpList = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String tableName = "view_gdnzdormfpb";
		sql = "select substr(max(nj),3,2) newstu from view_xsjbxx order by newstu";
		String newstu = dao.getOneRs(sql, new String[] {}, "newstu");
		sql = "select distinct substr(nj,3,2) oldstu from view_xsjbxx where nj<>? order by oldstu";
		String[] oldstuArrary = dao.getRs(sql, new String[] { "20" + newstu },
		"oldstu");
		String oldstu = "";
		for (int i = 0; i < oldstuArrary.length; i++) {
			oldstu += oldstuArrary[i];
			if (i != oldstuArrary.length - 1) {
				oldstu += "、";
			}
		}
		map.put("newstu", newstu);
		map.put("oldstu", oldstu);
		sql = "select xymc,newsturs,oldsturs,zsturs,xyyzrs6,xyyzrs10,yzrs6,yzrs10,ybccws6,ybccws10,ybcss6,ybcss10,ylcws6 from "
			+ tableName;
		fpList = dao.getList(sql, new String[] {}, new String[] { "xymc",
				"newsturs", "oldsturs", "zsturs", "xyyzrs6", "xyyzrs10",
				"yzrs6", "yzrs10", "ybccws6", "ybccws10", "ybcss6", "ybcss10",
		"ylcws6" });
		request.setAttribute("fpList", fpList);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	/**
	 * @param dao
	 * @param map
	 * @param doType
	 * @param pk
	 * @param pkValue
	 * @param realName
	 * @param colList
	 * @return 返回一个HashMap<String, String>变量
	 */
	private static HashMap<String, String> getMapxx(DAO dao,
			HashMap<String, String> map, String doType, String pk,
			String pkValue, String realName, String[] colList) {
		String sql;
		if ("add".equalsIgnoreCase(doType)) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], "");
			}
		} else {
			sql = "select * from " + realName + " where " + pk + "=?";
			map = dao.getMap(sql, new String[] { pkValue }, colList);
		}
		return map;
	}

	public static List getssfpInfo(DAO dao, String xqdm, String lddm,
			String xydm, String bjdm) {
		String sql;		
		String xxdm = StandardOperation.getXxdm();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append((Base.isNull(xqdm)) ? " and 1=1": " and xqdm='" + xqdm + "'");
		query.append((Base.isNull(xydm)) ? " and 1=2": " and xydm='" + xydm + "'");
		query.append((Base.isNull(lddm)) ? " and 1=1": " and lddm='" + lddm + "'");
		query.append((Base.isNull(bjdm)) ? " and 1=1": " and bjdm='" + bjdm + "'");
		//query = getdromquery(xqdm, lddm, xydm, "", bjdm);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			sql = "select  bjdm||'/'||ssbh||'/'||cws||'/'||sycws||'/'||cwh en,bjmc||'/'||ssbh||'/'||cws||'/'||cwh cn from "
				+ "(select a.bjdm,e.bjmc,a.cwh,a.xydm,d.bmmc,a.ssbh,nvl(b.cws,0)- nvl(a.cws,0) sycws,b.cws,b.lddm,c.xqdm,c.xbxd from ssfpb a,ssxxb b,sslddmb c,zxbz_xxbmdm d,bks_bjdm e where a.ssbh=b.ssbh and "
				+ "b.lddm=c.lddm and a.xydm=d.bmdm and a.bjdm=e.bjdm)"
				+ query;
		} else {
			sql = " select  distinct(xydm||'/'||ssbh||'/'||cws||'/'||fpcws) en,bmmc||'/'||ssbh||'/'||fpcws cn from "
				+ "(select a.xydm,d.bmmc,a.ssbh,a.cws fpcws,b.cws,b.lddm,c.xqdm,c.xbxd from ssfpb a,ssxxb b,sslddmb c,zxbz_xxbmdm d where a.ssbh=b.ssbh and "
				+ "b.lddm=c.lddm and a.xydm=d.bmdm)" + query;
		}
		List ssfpList = dao.getList(sql, new String[] {}, new String[] { "en",
		"cn" });
		return ssfpList;
	}

	/**
	 * @param xqdm
	 *            校区代码
	 * @param lddm
	 *            楼栋代码
	 * @param xydm
	 *            学院代码
	 * @param qsh
	 *            寝室号
	 * @param bjdm
	 *            班级代码
	 * @return 返回查询条件
	 */
	public static String getdromquery(String xqdm, String lddm, String xydm,
			String qsh, String bjdm) {
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(("".equalsIgnoreCase(xqdm) || xqdm == null) ? " and 1=1"
				: " and xqdm='" + xqdm + "'");
		query.append(("".equalsIgnoreCase(xydm) || xydm == null) ? " and 1=1"
				: " and xydm='" + xydm + "'");
		query.append(("".equalsIgnoreCase(lddm) || lddm == null) ? " and 1=1"
				: " and lddm='" + lddm + "'");
		query.append(("".equalsIgnoreCase(qsh) || qsh == null) ? " and 1=1"
				: " and qsh='" + qsh + "'");
		query.append(("".equalsIgnoreCase(bjdm) || bjdm == null) ? " and 1=1"
				: " and bjdm='" + bjdm + "'");
		return query.toString();
	}

	/**
	 * 
	 * @descrip 获得已经分配宿舍数目
	 * @param xydm
	 *            学院代码
	 * @param bjdm
	 *            班级代码
	 * @return 返回数组
	 */
	public static String[] getSsFpNum(String xydm, String bjdm) {
		DAO dao = DAO.getInstance();
		String[] str = null;
		StringBuffer querry = new StringBuffer();
		querry.append(" and 1=1 ");

		querry.append((Base.isNull(xydm)) ? " and 1=1 " : " and xydm='" + xydm
				+ "' ");
		querry.append((Base.isNull(bjdm)) ? " and 1=1 " : " and bjdm='" + bjdm
				+ "'");

		String sql = "select a.yfss_boy+a.yfss_girl yfss_total,yfss_boy, yfss_girl from (select (select (case count(cws) when 0 then 0 else sum(to_number(cws))end) sumb from ssfpb where exists "
			+ "(select a.ssbh from ssfpb a,sslddmb b where substr(a.ssbh,1,instr(a.ssbh,'-')-1)=b.lddm and b.xbxd='男') "
			+ querry
			+ " )yfss_boy,"
			+ "(select (case count(cws) when 0 then 0 else sum(to_number(cws)) end ) sumg from ssfpb where exists (select a.ssbh from ssfpb a,sslddmb b where substr(a.ssbh,1,instr(a.ssbh,'-')-1)=b.lddm and b.xbxd='女') "
			+ querry + " )yfss_girl" + " from dual)a ";
		str = dao.getOneRs(sql, new String[] {}, new String[] { "yfss_total",
				"yfss_boy", "yfss_girl" });
		return str;
	}

	/**
	 * @descrip 获得已经入住学生数目
	 * @param xydm
	 * @param bjdm
	 * @return 返回数组
	 */
	public static String[] getSsRzXxNum(String xydm, String bjdm) {
//		DAO dao = DAO.getInstance();
		String[] str = null;
		StringBuffer querry = new StringBuffer();
		querry.append("");
		querry.append((Base.isNull(xydm)) ? " and 1=1 " : " and xydm='" + xydm
				+ "' ");
		querry.append((Base.isNull(bjdm)) ? " and 1=1 " : " and bjdm='" + bjdm
				+ "'");

//		String sql = "select a.yrz_boy+a.yrz_girl yrz_total,yrz_boy,yrz_girl from "
//			+ "(select (select count(a.xh) from xszsxxb a,sslddmb b where substr(a.ssbh,1,instr(a.ssbh,'-')-1)=b.lddm and b.xbxd='男' and a.jzrq is null )yrz_boy,"
//			+ "(select count(a.xh) from xszsxxb a,sslddmb b where substr(a.ssbh,1,instr(a.ssbh,'-')-1)=b.lddm and b.xbxd='女' and a.jzrq is null )yrz_girl"
//			+ "from dual)a";
		return str;
	}

	/**
	 * @descrip 楼长信息管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public static ActionForward lzxx_manage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		CommanForm myForm = (CommanForm) form;
//		String tips = "";// 位置提示信息
		String tableName = "view_gyfdyxx";// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "gygl_lzxxb";// 数据源表
		StringBuffer querry = new StringBuffer();
		String pk = "xn||xq||xqdm||lddm||yhm";
		querry.append(" where 1=1 ");// sql条件
		String sql = "";
		String xqdm = myForm.getXqdm();
		String lddm = myForm.getLddm();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		Vector<Object> rs = new Vector<Object>();
        if(xn==null){
        	xn = Base.currXn;
        	myForm.setXn(xn);
        }
        if(xq==null){
        	xq = Base.currXq;
        	myForm.setXq(xq);
        }
		sql = "select dm,xqmc from dm_zju_xq";
		List xiaoqquList = dao.getList(sql, new String[] {}, new String[] {
				"dm", "xqmc" });

		sql = "select lddm,ldmc from sslddmb  order by lddm";
		List ldList = dao.getList(sql, new String[] {}, new String[] { "lddm",
		"ldmc" });

		querry.append(Base.isNull(xqdm) ? " and 1=1 " : " and xqdm= '" + xqdm+ "' ");
		querry.append(Base.isNull(lddm) ? " and 1=1 " : " and lddm= '" + lddm+ "'");
		querry.append(Base.isNull(xn) ? " and 1=1 " : " and xn='" + xn + "'");
		querry.append(Base.isNull(xq) ? " and 1=1 " : " and xq='" + xq + "'");

		String[] colList = new String[] { "主键", "xn", "xueqmc", "xqmc", "ldmc",
				"xm","gzs","lxdh","dzyx","zmc", "bmmc", "dwmc" };
		sql = "select * from (select * from( select "
			+ pk
			+ " 主键,rownum r,a.* from "
			+ tableName
			+ " a "
			+ querry.toString()
			+ " order by xqdm,lddm) where rownum<="
			+ (myForm.getPages().getStart() + myForm.getPages()
					.getPageSize()) + ") where r>"
					+ myForm.getPages().getStart();

		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		// 分页
		myForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName+ " a" + querry, new String[] {},"count")));

		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("xiaoqquList", xiaoqquList);
		request.setAttribute("ldList", ldList);
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("success");
	}

	public static ActionForward lzxx_add(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm myForm = (CommanForm) form;
		String sql = "";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String yhm = myForm.getYhm();
		String xqdm = myForm.getXqdm();
		String lddm = myForm.getLddm();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String lxdh = DealString.toGBK(myForm.getLxdh());
		String bz  = DealString.toGBK(myForm.getBz());
		String yx  = DealString.toGBK(myForm.getDzyx());
		String gzs = DealString.toGBK(myForm.getGzs());
		String isView = DealString.toString(request.getParameter("isView"));
		boolean done = false;
		HashMap<String, String> map = new HashMap<String, String>();
		String xm = dao.getOneRs("select xm from yhb where yhm=?",
				new String[] { yhm }, "xm");// 获得楼长名
		if (Base.isNull(xm)) {
			xm = "";
		}
		if(doType.equalsIgnoreCase("add")){//添加时默认系统设置年月
			myForm.setXq(Base.currXq);
			map.put("xn",Base.currXn);
			map.put("xq",Base.currXq);
		}
		if (doType.equalsIgnoreCase("save")) {// 保存数据
			if (Base.isNull(pkValue)) {// 添加
				done = dao.runUpdate(
						"delete from gygl_lzxxb where xn||xq||xqdm||lddm||yhm =?",
						new String[] { pkValue });
				if (done) {
					done = dao.runUpdate(" insert into gygl_lzxxb(xn,xq,xqdm,lddm,yhm,xm,gzs,lxdh,dzyx,bz) values(?,?,?,?,?,?,?,?,?,?)",
							new String[] { xn, xq, xqdm, lddm, yhm, xm,gzs,lxdh,yx,bz });
				}
			} else {// 修改
				done = dao.runUpdate(" update gygl_lzxxb set yhm=?,xm=?,gzs=?,lxdh=?,dzyx=?,bz=? where  xn||xq||xqdm||lddm||yhm=?",
						new String[] { yhm, xm, gzs,lxdh,yx,bz,pkValue });
			}
			if (done) {
				request.setAttribute("done", "ok");
			} else {
				request.setAttribute("done", "no");
			}
		}		
		
		if (doType.equalsIgnoreCase("del")) {// 删除数据
			done = dao.runUpdate("delete from gygl_lzxxb where xn||xq||xqdm||lddm||yhm=?",
					new String[] { pkValue });
			if (done) {
				request.setAttribute("done", "ok");
			} else {
				request.setAttribute("done", "no");
			}
			return new ActionForward("/lzxx_manage.do?go=go", false);
		}

		if (!Base.isNull(yhm)) {// 选择用户时获得用户相关信息
			sql = "select c.yhm,(select zmc from yhzb where zdm in (select zdm from yhb b where b.yhm=c.yhm)) zmc,"
				+ "(select bmmc from ZXBZ_XXBMDM where bmdm in (select szbm from yhb b where b.yhm=c.yhm)) bmmc, "
				+ "(select dwmc from bks_dwdmb where dwdm in (select dwdm from yhb b where b.yhm=c.yhm)) dwmc "
				+ " from yhb c where c.yhm = ? ";
			map = dao.getMapNotOut(sql, new String[] { yhm });
		}
		if (!Base.isNull(xqdm)) {
			myForm.setXqdm(xqdm);
			map.put("xqdm", xqdm);
		}
		if (!Base.isNull(lddm)) {
			myForm.setLddm(lddm);
			map.put("lddm", lddm);
		}
		if (!Base.isNull(xn)) {
			myForm.setXn(xn);
			map.put("xn", xn);
		}
		if (!Base.isNull("xq")) {
			myForm.setXq(Base.currXq);
			map.put("xq", xq);
		}

//		 修改、查看记录时获得相关信息
		sql = "select xn,xq,xqdm,lddm,yhm,zmc,bmmc,dwmc,gzs,lxdh,dzyx,bz from view_gyfdyxx where xn||xq||xqdm||lddm||yhm=?";
		String[] colList = new String[] { "xn", "xq", "xqdm", "lddm",
				"yhm", "zmc", "bmmc", "dwmc","gzs","lxdh","dzyx","bz" };
		String[] temV = dao.getOneRs(sql, new String[] { pkValue }, colList);
		if (temV != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), temV[i]);
			}
		}
		sql = "select a.yhm,a.xm||'('||a.yhm||')' xm,b.zmc from yhb a,yhzb b,ZXBZ_XXBMDM c,bks_dwdmb d where a.zdm<>'0001' and a.zdm=b.zdm and a.szbm=c.bmdm and a.dwdm=d.dwdm "
			+ " order by  xm";

		List yhList = dao.getList(sql, new String[] {}, new String[] { "yhm",
		"xm" });
		sql = "select dm,xqmc from dm_zju_xq";
		List xiaoqquList = dao.getList(sql, new String[] {}, new String[] {
				"dm", "xqmc" });
		request.setAttribute("xiaoqquList", xiaoqquList);
		sql = "select lddm,ldmc from sslddmb  order by lddm";
		List ldList = dao.getList(sql, new String[] {}, new String[] { "lddm",
		"ldmc" });
		request.setAttribute("xiaoqquList", xiaoqquList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("ldList", ldList);
		request.setAttribute("yhList", yhList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", map);
		request.setAttribute("isView",isView);
		return mapping.findForward("success");
	}

	@SuppressWarnings("unchecked")
	public static ActionForward wmqspb_result(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm myForm = (CommanForm) form;
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		String sql = "";
		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		String rsNum = "";
		String nd = myForm.getNd();
		String yf = myForm.getYf();
		String pysj = "";
		float allHj = 0;
		String jjzhjdx = "";
		String xxdm = dao.getXxdm();
		String[] colList = null;
		String czType = request.getParameter("czType");
		Vector<Object> rs = new Vector<Object>();
//		Vector<Object> rs2 = new Vector<Object>();
//		Vector<Object> rs3 = new Vector<Object>();
		Vector<Object> rs4 = new Vector<Object>();
		ArrayList<Object> rsa = new ArrayList<Object>();
		ArrayList<Object> rsb = new ArrayList<Object>();
		ArrayList<Object> rsc = new ArrayList<Object>();
		String tableName = "view_wmqspbxx";
		if (nd == null || nd.equalsIgnoreCase("")) {
			nd = Base.currNd;
			myForm.setNd(nd);
		}
		if (!(isNull(nd) && isNull(yf))) {
			pysj = nd + yf;
			querry.append(" and pysj like '" + pysj + "%' ");
		} else {
			querry.append(" and 1=2 ");
		}

		if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){//广东白云学院
			request.setAttribute("isGDBYXY", "ok");
		}

		if (czType != null && czType.equalsIgnoreCase("pbjg")) {// 学生公寓文明寝室评比结果（报表）
			sql = "select distinct xydm from view_xszsxx a,wmqspbb b  where a.ssbh=b.ssbh and b.pysj like'"
				+ pysj + "%' ";
			String[] xyTem = dao.getRs(sql, new String[] {}, "xydm");
			if (xyTem != null) {
				for (int i = 0; i < xyTem.length; i++) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("xyList",dao.getList("select distinct bmmc xymc from zxbz_xxbmdm where bmdm=?",
							new String[] { xyTem[i] },new String[] { "xymc" }));

					// 文明寝室List
					sql1.append("select ldmc,qsh,cy,bjmc,lbmc from ( ");
					sql1.append(" select ldmc,qsh,max(ltrim(sys_connect_by_path(xm,'、'),'、')) cy,bjmc,lbmc from ( ");
					sql1.append(" select distinct a.xh,b.lbmc,a.ldmc,a.qsh,a.bjdm,a.bjmc,a.xm,row_number() over (partition by a.qsh,a.bjmc order by a.xh) pno,");
					sql1.append(" row_number() over (partition by a.qsh,a.bjmc order by a.xh)-1 sno from view_xszsxx a, view_wmqspbxx b where a.ssbh=b.ssbh and xydm='"+ xyTem[i] + "' and b.pysj like '"+ pysj+"%' ) a ");
					sql1.append(" start with pno='1' connect by prior pno=sno and prior bjdm=bjdm and prior qsh=qsh group by  bjmc,ldmc,qsh,lbmc");
					sql1.append(" ) order by lbmc,ldmc ");
					colList = new String[]{ "ldmc", "qsh", "cy", "bjmc", "lbmc" };
					List myList = dao.getList(sql1.toString(), new String[] {},
							colList);
					map.put("wmpsList", myList);

					rsa.add(map);
					request.setAttribute("rs", rsa);
					sql1 = new StringBuffer();
				}
			}
			request.setAttribute("sysData", DealString.getDateTime().substring(
					0, 10));
			return new ActionForward("/gygl/hzzy/wmqs_lb.jsp", false);
		} else if (czType != null && czType.equalsIgnoreCase("pbjj")) {// 公寓文明寝室评比奖金发放清单报
			// 获得满足条件宿舍编号（有人入住的）
			sql = " select distinct(a.ssbh) ssbh from wmqspbb a,view_xszsxx b  where a.ssbh=b.ssbh and pysj like '"
				+ pysj + "%' order by ssbh";
			String[] ssbhTem = dao.getRs(sql, new String[] {}, "ssbh");
			sql = "select distinct(lbdm)lbdm from qslbdmb order by lbdm";
			String[] qslbTem = dao.getRs(sql, new String[] {}, "lbdm");
			if (ssbhTem != null) {
				if (qslbTem != null) {
					for (int i = 0; i < qslbTem.length; i++) {// 按宿舍类别循环
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("lbList", dao.getList(
								"select lbmc from qslbdmb where lbdm=?",
								new String[] { qslbTem[i] },
								new String[] { "lbmc" }));

						// 文明寝室List
						sql2.append(" select ldmc,max(ltrim(sys_connect_by_path(qsh,'、'),'、')) qs from ( ");
						sql2.append(" select b.lddm,b.ldmc,b.qsh,row_number() over (partition by b.lddm order by b.qsh) pno, ");
						sql2.append(" row_number() over (partition by b.lddm order by b.qsh)-1 sno from ( ");
						sql2.append(" select distinct a.qsh,b.lddm,a.ldmc from view_wmqspbxx b,view_xszsxx a where a.ssbh=b.ssbh and b.qslb='"+ qslbTem[i]+ "'and  b.pysj like '"+ pysj + "%' )b ");
						sql2.append(" ) a start with pno='1'  connect by prior pno=sno and prior lddm=lddm  group by ldmc ");
						colList = new String[]{ "ldmc", "qs" };
						List myList = dao.getList(sql2.toString(),
								new String[] {}, colList);
						map.put("qsList", myList);
						// 经费合计List
						sql = " select cout,qsjje,cout*qsjje hj from qslbdmb a, (select count( distinct b.ssbh) cout from wmqspbb a,view_xszsxx b where a.ssbh=b.ssbh and pysj like '"
							+ pysj
							+ "%' and qslb = '"
							+ qslbTem[i]
							          + "' )b where a.lbdm = '" + qslbTem[i] + "' ";
						List hjList = dao.getList(sql, new String[] {},
								new String[] { "cout", "qsjje", "hj" });
						map.put("hjList", hjList);
						rsb.add(map);
						request.setAttribute("rs", rsb);
						sql2 = new StringBuffer();
					}
				}
				for (int i = 0; i < ssbhTem.length; i++) {// 按宿舍编号循环
					HashMap<String, Object> mapa = new HashMap<String, Object>();
					// 逐条获得宿舍对应楼名、寝室号
					mapa.put("ssbhList",dao.getList("select ldmc,qsh,sum(qsjje)jjhj from view_wmqspbxx where ssbh=? group by ldmc,qsh ",
							new String[] { ssbhTem[i] },new String[] { "ldmc","qsh", "jjhj" }));
					// 逐条获得宿舍对应奖项奖金额
					List lbcjList = dao.getList("select (case when qsjje is null then ' ' else qsjje end)qsjje from (select (select a.qsjje from wmqspbb b where a.lbdm=b.qslb and b.ssbh = ?)qsjje from qslbdmb a order by a.lbdm)",
							new String[] { ssbhTem[i] },new String[] { "qsjje" });
					mapa.put("lbcjList", lbcjList);
					rsc.add(mapa);
				}
				request.setAttribute("rs2", rsc);

				HashMap<String, Object> mapb = new HashMap<String, Object>();
				// 获得宿舍类别List
				mapb.put("qslbList", dao.getList(
						"select lbmc from qslbdmb order by lbdm",
						new String[] {}, new String[] { "lbmc" }));
				// 获得单项类别奖金合计List
				List dxjjhjList = dao.getList("select sum(c.qsjje) dxjjhj from (select distinct a.ssbh,a.qsjje,qslb from view_wmqspbxx a,xszsxxb b where a.ssbh=b.ssbh and a.pysj like '"
						+ pysj
						+ "%' ) c right join  qslbdmb d  on c.qslb=d.lbdm group by d.lbdm order by d.lbdm ",
						new String[] {}, new String[] { "dxjjhj" });
				mapb.put("dxjjhjList", dxjjhjList);
				rsa.add(mapb);
				request.setAttribute("rs3", rsa);

				HashMap<String, Object> hmap = new HashMap<String, Object>();
				for (int i = 0; i < dxjjhjList.size(); i++) {// 总奖金合计
					hmap = (HashMap<String, Object>) dxjjhjList.get(i);
					String hjTem = "0";
					if (hmap.get("dxjjhj") != null) {
						hjTem = hmap.get("dxjjhj").toString();
					}
					allHj += Float.parseFloat(hjTem);
				}
				String sqlT = "{call pro_Disp_dxje(?,?)}";
				String[] temp = dao.getProVal(sqlT, new String[] { String
						.valueOf(allHj) }, new int[] { 2 });
				jjzhjdx = temp[0];// 大写转换
			}
			request.setAttribute("allHj", new java.text.DecimalFormat("#,###,##0.00").format(allHj));
			request.setAttribute("jjzhjdx", jjzhjdx);
			request.setAttribute("qslbSize", qslbTem.length);
			request.setAttribute("rowSize", qslbTem.length + 2);
			request.setAttribute("sysData", DealString.getDateTime().substring(0, 10));
			return new ActionForward("/gygl/hzzy/wmqs_pbqd.jsp", false);
		}else if(czType != null && czType.equalsIgnoreCase("gdby_pbjg")){//广东白云学院文明寝室评比结果报表
			sql = "select distinct xydm from view_gdbywmqsppxxbb"+querry;
			String[] xyTem = dao.getRs(sql,new String[]{},"xydm");
			if(xyTem!=null){
				for(int i=0;i<xyTem.length;i++){
					HashMap<String, Object> map = new HashMap<String, Object>();
					sql = "select distinct xymc from view_gdbywmqsppxxbb where xydm=? ";
					List xyList = dao.getList(sql,new String[]{xyTem[i]},new String[]{"xymc"});
					map.put("xyList",xyList);
					sql = "select bjmc,max(ltrim(sys_connect_by_path(ss,'、'),'、'))cy from" +
					"(select bjmc, ss,row_number() over (partition by bjmc order by ss) pno, " +
					"row_number() over (partition by bjmc order by ss)-1 sno from view_gdbywmqsppxxbb " +
					querry+" and xydm =? )a connect by prior sno=pno  and prior bjmc=bjmc group by bjmc";
					List qsList = dao.getList(sql,new String[]{xyTem[i]},new String[]{"bjmc","cy"});
					map.put("qsList",qsList);
					rs4.add(map);
				}
				request.setAttribute("rs",rs4);
			}
			request.setAttribute("nd",nd);
			request.setAttribute("yf",yf);
			return new ActionForward("/gygl/gdby/gdby_wmqspbbb.jsp",false);
		}

		sql = "select a.xn,a.xq,a.ldmc,a.qsh,a.lbmc,a.pysj,(select count(b.ssbh) from xszsxxb b where a.ssbh=b.ssbh) 入住人数 from "
			+ tableName + " a " + querry.toString() + " order by lbmc";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){
			tableName = "view_gdbywmqsppxxbb";
			sql = "select xn,xymc,bjmc,ldmc,qsh,pysj from view_gdbywmqsppxxbb "+querry.toString();
			colList= new String[] { "xn", "xymc","bjmc","ldmc", "qsh", "pysj" };
		}else{
			colList= new String[] { "xn", "xq", "ldmc", "qsh", "lbmc","pysj", "入住人数" };
		}
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs", rs);
		request.setAttribute("ndList", dao.getNdList());
		return mapping.findForward("success");
	}

	public static ActionForward stu_LdQsInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		CommanForm dataSearchForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		String rsNum = "";
		//String pk = "xh";
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String tips = "公寓管理 - 信息维护 - 住宿信息维护";
		String tableName = "view_xszsxx";
		String lddm = dataSearchForm.getLddm();
		String lc = dataSearchForm.getLc();
		String qsh = dataSearchForm.getQsh();
		String nj = dataSearchForm.getNj();
		String xn = dataSearchForm.getXn();
		String xq = dataSearchForm.getXq();
		String xydm = dataSearchForm.getXydm();
		String zydm = dataSearchForm.getZydm();
		String bjdm = dataSearchForm.getBjdm();
		String xh =  DealString.toGBK(dataSearchForm.getXh()).trim();
		String xm = DealString.toGBK(dataSearchForm.getXm()).trim();
		String xqdm = dataSearchForm.getXqdm();
		dataSearchForm.setXh(xh);
		dataSearchForm.setXm(xm);
		// String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xxdm = StandardOperation.getXxdm();
		if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){//金华职业技术学院
//			公寓辅导员负判断
			if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
				userType = "isGyFdy";
			}				
		}
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			dataSearchForm.setXydm(xydm);
		}
		colList = new String[] { "xh", "xm", "xb", "ssbh","xqmc", "ldmc","cs", "qsh","cwh",
				 "rzrq", "jzrq" };
		sql = "select dm,xqmc from dm_zju_xq";
		List xiaoqquList = dao.getList(sql, new String[] {}, new String[] {
				"dm", "xqmc" });
		request.setAttribute("xiaoqquList", xiaoqquList);
		sql = "select lddm,ldmc from sslddmb";
		if (Base.isNull(xqdm)) {
			sql += " where 1=1 ";
		}else{
			sql += " where xqdm='" + xqdm + "' ";
		}
		sql += " order by lddm ";
		List ldList = dao.getList(sql, new String[] {}, new String[] { "lddm",
		"ldmc" });
		request.setAttribute("ldList", ldList);
		querry.append((Base.isNull(nj)) ? "" : " and nj = '" + nj+ "'");
		querry.append((Base.isNull(xn)) ? "" : " and xn = '" + xn+ "'");
		querry.append((Base.isNull(xq)) ? "" : " and xq = '" + xq+ "'");
		querry.append((Base.isNull(xh)) ? "" : " and xh = '" + xh+ "'");
		querry.append((Base.isNull(xm)) ? "" : " and xm like '%" + xm+ "%'");
		querry.append((Base.isNull(xydm)) ? "" : " and xydm = '"+ xydm + "'");
		querry.append((Base.isNull(zydm)) ? "" : " and zydm = '"+ zydm + "'");
		querry.append((Base.isNull(bjdm)) ? "" : " and bjdm = '"+ bjdm + "'");
		querry.append(Base.isNull(xqdm) ? "" : " and xqdm = '" + xqdm+ "' ");
		querry.append(Base.isNull(lddm) ? "" : " and lddm = '" + lddm+ "' ");
		querry.append(Base.isNull(qsh) ? "" : " and qsh = '" + qsh+ "' ");
		querry.append(Base.isNull(lc) ? "" : " and cs = '" + lc+ "' ");
		String fdyQuerry = "isGyFdy".equalsIgnoreCase(userType) ? " and ssbh in (select ssbh from jhzy_gyfdyb  where yhm ='"+ userName + "' and xn='"+Base.currXn+"' and xq='"+Base.currXq+"' )":"";
		querry.append(fdyQuerry);
		sql = "select * from (select * from( select rownum r,a.* from "
			+ tableName
			+ " a"
			+ querry.toString()
			+ ") where rownum<="
			+ (dataSearchForm.getPages().getStart() + dataSearchForm
					.getPages().getPageSize()) + ") where r>"
					+ dataSearchForm.getPages().getStart();
		// 分页
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs("select count(*) count from "
						+ tableName + " a" + querry.toString(),
						new String[] {}, "count")));
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		gyglDao.getLdLcQshList(request);
		gyglDao.getXyZyBjxx(request);
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("userName", userName);
		if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){//金华职业技术学院
			//公寓辅导员负责楼层寝室列表限制
			gyglDao.setGyfdy(request, userName, lddm, lc, Base.currXn, Base.currXq);
		}
		return mapping.findForward("success");
	}
	/**公寓管理参数设置
	 * @throws Exception */
	public static ActionForward gygl_ParaSet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommanForm cfForm = (CommanForm)form;
		HashMap<String,String> map = new HashMap<String,String>();
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String sql = "";
		//String realTable = "";
		String xn = cfForm.getXn();
		String xq = cfForm.getXq();
		String wmqsbz = cfForm.getWmqsbz();
		String wsjc = cfForm.getWsjc();
		String sfbd = cfForm.getSfbd();
		String sjsz = request.getParameter("sjsz");
		String wmqsbl = request.getParameter("wmqsbl");
		String kssj   = request.getParameter("kssqsj");
		String jssj   = request.getParameter("jssqsj");
		boolean done = false;
		String xxdm = StandardOperation.getXxdm();
		if(Base.isNull(xn)){
			xn = Base.currXn;
			cfForm.setXn(xn);
		}
		if(Base.isNull(xq)){
			xq = Base.currXq;
			cfForm.setXq(xq);
		}
		
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)
//				||xxdm.equalsIgnoreCase(Globals.XXDM_HENANGYDX)
//				||Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)
//				||Globals.XXDM_CSDLZYJSXY.equalsIgnoreCase(xxdm)){//长沙民政职业技术学院,河南工业大学,江苏信息职业技术学院,长沙电力职业技术学院
//			request.setAttribute("SSFPMSSZ", "SSFPMSSZ");//宿舍分配模式设置		
//		}
//		if(gyglDao.xsfpMsXxPd(xxdm)){
//			request.setAttribute("SSFPMSSZ", "SSFPMSSZ");//宿舍分配模式设置
//		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){//宁波理工学院
			request.setAttribute("isNBLGXY", "NBLGXY");
		}else{
			request.setAttribute("SSFPMSSZ", "SSFPMSSZ");//宿舍分配模式设置
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {// 淮海工学院
			HashMap<String, Object> mapList = new HashMap<String, Object>();
			Vector<Object> rsList = new Vector<Object>();
			sql = "select a.wsjcdm, a.wsjcdj, b.wsjcdj ws, b.wsjcdjbfb from hhgxy_wsjcdj a"
					+ " left join gygl_csszb b on a.wsjcdj = b.wsjcdj order by a.wsjcdj";
			List<HashMap<String, String>> wsjcList = dao.getList(sql,
					new String[] {}, new String[] { "wsjcdm", "wsjcdj", "ws",
							"wsjcdjbfb" });
			sql = "select cfdjdm,cfdjmc from hhgxy_cfdj order by cfdjdm";
			List<HashMap<String, String>> cfdjList = dao.getList(sql,
					new String[] {}, new String[] { "cfdjdm", "cfdjmc" });
			sql = "select * from gygl_csszb where xn=? and xq=? ";
			map = dao.getMap(sql, new String[] { xn, xq }, new String[] { "xn",
					"xq", "wmqsbz", "pbzq" });
			sql = "select a.wsjcdm, a.wsjcdj, c.ws, c.wsjcdjbfb from hhgxy_wsjcdj a"
					+ " left join (select b.wsjcdj ws, b.wsjcdjbfb from gygl_csszb b"
					+ " where b.xn = ? and b.xq = ?) c on a.wsjcdj = c.ws order by a.wsjcdj";
			List<HashMap<String, String>> wsjcbfbList = dao.getList(sql,
					new String[] { xn, xq }, new String[] { "wsjcdm", "wsjcdj",
							"ws", "wsjcdjbfb" });
			sql = "select a.cfdjdm, a.cfdjmc, c.cf, c.cfdjcs from hhgxy_cfdj a"
					+ " left join (select b.cfdj cf, b.cfdjcs from gygl_csszb b where b.xn = ?"
					+ " and b.xq = ?) c on a.cfdjmc = c.cf order by a.cfdjdm";
			List<HashMap<String, String>> cfdjsList = dao.getList(sql,
					new String[] { xn, xq }, new String[] { "cfdjdm", "cfdjmc",
							"cf", "cfdjcs" });
			List<HashMap<String, String>> pbzqList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> mapPb = new HashMap<String, String>();
			mapPb.put("pbzqdm", "1");
			mapPb.put("pbzqmc", "学年");
			pbzqList.add(mapPb);
			mapPb = new HashMap<String, String>();
			mapPb.put("pbzqdm", "2");
			mapPb.put("pbzqmc", "学期");
			pbzqList.add(mapPb);

			mapList.put("wsjcbfbList", wsjcbfbList);
			mapList.put("cfdjsList", cfdjsList);
			rsList.add(mapList);
			request.setAttribute("rsList", rsList);
			
			request.setAttribute("wsjcList", wsjcList);
			request.setAttribute("wsjcbfbList", wsjcbfbList);
			request.setAttribute("wsjcdjs", wsjcList.size());
			request.setAttribute("cfdjList", cfdjList);
			request.setAttribute("cfdjs", cfdjList.size());
			request.setAttribute("pbzqList", pbzqList);
			request.setAttribute("isHHGXY", "HHGXY");
		}
		
		if(doType!=null&&doType.equalsIgnoreCase("save")){//保存			
			if(!Base.isNull(wmqsbl)){//文明寝室参数设置
				String IorN = dao.returntag("select * from gygl_csszb where xn=? and xq=? ", new String[]{xn,xq});
				if("notempty".equalsIgnoreCase(IorN)){
					if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {// 淮海工学院
						String pbzq = request.getParameter("pbzq");
						// String wsjc = request.getParameter("wsjc");
						// String cfdj = request.getParameter("cfdj");
						String bfb = request.getParameter("bfb");
						String wsjcmc = request.getParameter("wsjcmc");
						String[] bfbs = bfb.split("!!SplitSignOne!!");
						String[] wsjcmcs = wsjcmc.split("!!SplitSignTwo!!");

						String cfcs = request.getParameter("cfcs");
						String cfmc = request.getParameter("cfmc");
						String[] cfcss = cfcs.split("!!SplitSignOne!!");
						String[] cfmcs = cfmc.split("!!SplitSignTwo!!");
						int bf = 0;
						StringBuffer sb = new StringBuffer();
						String[] pksql = new String[] {};
						sql = "";

						for (int i = 0; i < bfbs.length; i++) {
							bf += Integer.parseInt(bfbs[i]);
							if (bf > 100) {
								String msg = "各百分比相加超过100%，请确认";
								request.setAttribute("msg", msg);
								request.setAttribute("rs", map);
								request.setAttribute("xnList", Base.getXnndList());
								request.setAttribute("xqList", Base.getXqList());
								return mapping.findForward("success");
							}
						}
						done = dao
								.runUpdate(
										" delete from gygl_csszb where xn = ? and xq= ? and cfdj is null and cfdjcs is null ",
										new String[] { xn, xq });
						if (done) {
							for (int i = 0; i < bfbs.length; i++) {
								sql = "insert into gygl_csszb (xn,xq,wmqsbz,pbzq,wsjcdj,wsjcdjbfb)"
										+ "values('"
										+ xn
										+ "','"
										+ xq
										+ "','"
										+ wmqsbz
										+ "','"
										+ pbzq
										+ "','"
										+ DealString.toGBK(wsjcmcs[i])
										+ "','"
										+ ("0".equals(bfbs[i]) ? "" : bfbs[i])
										+ "')";
								sb.append(sql);
								sb.append("!!#!!");
							}
						}
						pksql = sb.toString().split("!!#!!");
						dao.runBatch(pksql);

						sb = new StringBuffer();
						
						done = dao
								.runUpdate(
										" delete from gygl_csszb where xn = ? and xq= ? and wsjcdj is null and wsjcdjbfb is null ",
										new String[] { xn, xq });
						if (done) {
							for (int i = 0; i < cfcss.length; i++) {
								sql = "insert into gygl_csszb (xn,xq,wmqsbz,pbzq,cfdj,cfdjcs)"
										+ "values('"
										+ xn
										+ "','"
										+ xq
										+ "','"
										+ wmqsbz
										+ "','"
										+ pbzq
										+ "','"
										+ DealString.toGBK(cfmcs[i])
										+ "','"
										+ ("0".equals(cfcss[i]) ? "" : cfcss[i])
										+ "')";
								sb.append(sql);
								sb.append("!!#!!");
							}
						}
						pksql = sb.toString().split("!!#!!");
						dao.runBatch(pksql);

						request.setAttribute("pbzq", pbzq);
					} else {
						done = dao.runUpdate(" update gygl_csszb set wmqsbz=?,wsjc=? ",new String[] { wmqsbz, wsjc});
					}
//					done = StandardOperation.update("gygl_csszb", new String[]{"wmqsbz"}, new String[]{wmqsbz}, new String[]{"xn","xq"}, new String[]{xn,xq}, request);
				} else {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {// 淮海工学院
						String pbzq = request.getParameter("pbzq");
						// String wsjc = request.getParameter("wsjc");
						// String cfdj = request.getParameter("cfdj");
						String bfb = request.getParameter("bfb");
						String wsjcmc = request.getParameter("wsjcmc");
						String[] bfbs = bfb.split("!!SplitSignOne!!");
						String[] wsjcmcs = wsjcmc.split("!!SplitSignTwo!!");

						String cfcs = request.getParameter("cfcs");
						String cfmc = request.getParameter("cfmc");
						String[] cfcss = cfcs.split("!!SplitSignOne!!");
						String[] cfmcs = cfmc.split("!!SplitSignTwo!!");
						int bf = 0;
						
						StringBuffer sb = new StringBuffer();
						String[] pksql = new String[] {};
						sql = "";

						for (int i = 0; i < bfbs.length; i++) {
							bf += Integer.parseInt(bfbs[i]);
							if (bf > 100) {
								String msg = "各百分比相加超过100%，请确认";
								request.setAttribute("msg", msg);
								request.setAttribute("rs", map);
								request.setAttribute("xnList", Base.getXnndList());
								request.setAttribute("xqList", Base.getXqList());
								return mapping.findForward("success");
							}
						}
						
						done = dao
								.runUpdate(
										" delete from gygl_csszb where xn = ? and xq= ? and cfdj is null and cfdjcs is null ",
										new String[] { xn, xq });
						if (done) {
							for (int i = 0; i < bfbs.length; i++) {
								sql = "insert into gygl_csszb (xn,xq,wmqsbz,pbzq,wsjcdj,wsjcdjbfb)"
										+ "values('"
										+ xn
										+ "','"
										+ xq
										+ "','"
										+ wmqsbz
										+ "','"
										+ pbzq
										+ "','"
										+ DealString.toGBK(wsjcmcs[i])
										+ "','"
										+ ("0".equals(bfbs[i]) ? "" : bfbs[i])
										+ "')";
								sb.append(sql);
								sb.append("!!#!!");
							}
						}
						pksql = sb.toString().split("!!#!!");
						dao.runBatch(pksql);

						sb = new StringBuffer();
						done = dao
								.runUpdate(
										" delete from gygl_csszb where xn = ? and xq= ? and wsjcdj is null and wsjcdjbfb is null ",
										new String[] { xn, xq });
						if (done) {
							for (int i = 0; i < cfcss.length; i++) {
								sql = "insert into gygl_csszb (xn,xq,wmqsbz,pbzq,cfdj,cfdjcs)"
										+ "values('"
										+ xn
										+ "','"
										+ xq
										+ "','"
										+ wmqsbz
										+ "','"
										+ pbzq
										+ "','"
										+ DealString.toGBK(cfmcs[i])
										+ "','"
										+ ("0".equals(cfcss[i]) ? "" : cfcss[i])
										+ "')";
								sb.append(sql);
								sb.append("!!#!!");
							}
						}
						pksql = sb.toString().split("!!#!!");
						dao.runBatch(pksql);

						request.setAttribute("pbzq", pbzq);
					} else {
						done = dao
								.runUpdate(
										" insert into gygl_csszb(xn,xq,wmqsbz,wsjc) values(?,?,?,?) ",
										new String[] { xn, xq, wmqsbz, wsjc});
						// done = StandardOperation.insert("gygl_csszb", new
						// String[]{"xn","xq","wmqsbz"}, new
						// String[]{xn,xq,wmqsbz},request);
					}
				}
			} 
			if(!Base.isNull(sfbd)){//是否新生报到设置
				done = dao.runUpdate("delete from gygl_xsbdbz ",new String []{});
				if(done){
					dao.runUpdate("insert into gygl_xsbdbz(xszsbdms) values(?)", new String[]{sfbd});
					//done = StandardOperation.insert("gygl_xsbdbz", new String[]{"xszsbdms"}, new String[]{sfbd},request);		    			
				}
			}
			if(!Base.isNull(sjsz)){//时间设置
				done = dao.runUpdate("delete from gygl_sjszb ",new String []{});
				if(done){
					done  = dao.runUpdate("insert into gygl_sjszb(kssj,jssj)values(?,?)", new String[]{kssj,jssj});
					//done = StandardOperation.insert("gygl_sjszb", new String[]{"kssj","jssj"}, new String[]{kssj,jssj},request);		    			
				}
			}
			request.setAttribute("done",done);   
		}

		//获得是否新生报到住宿信息维护标志值Begin（长沙民政职业技术学院）
		String sfbdbz = dao.getOneRs(" select xszsbdms from gygl_xsbdbz where rownum=1 ",new String[]{},"xszsbdms");
		if(Base.isNull(sfbdbz)){
			sfbdbz="0";
		}
		request.setAttribute("sfbd",sfbdbz);				
		//获得是否新生报到住宿信息维护标志值End

//		文明寝室参数设置值begin
		sql = " select wmqsbz,wsjc from gygl_csszb where  xn=? and xq=? ";
		HashMap<String, String> szxx = dao.getMap(sql,new String[]{xn,xq},
				                      new String[]{"wmqsbz", "wsjc"});
		cfForm.setWmqsbz(szxx.get("wmqsbz"));
		cfForm.setWsjc(szxx.get("wsjc"));
//		文明寝室参数设置值End

		//获取时间设置值begin
		sql = "select strtodatetime(substr(kssj,1,8)) kssj1,"
			+ "substr(kssj,9,2) kssj2," + "substr(kssj,11,2) kssj3,"
			+ "substr(kssj,13,2) kssj4,"
			+ "strtodatetime(substr(jssj,1,8)) jssj1,"
			+ "substr(jssj,9,2) jssj2," + "substr(jssj,11,2) jssj3,"
			+ "substr(jssj,13,2) jssj4 from gygl_sjszb "
			+ "where rownum=1";
		String[] colList = { "kssj1", "kssj2", "kssj3", "kssj4", "jssj1",
				"jssj2", "jssj3", "jssj4" };
		String[] sqsj = dao.getOneRs(sql, new String[] {}, colList);
		if (sqsj == null) {
			sqsj = new String[colList.length];
			for (int i = 0; i < sqsj.length; i++) {
				sqsj[i] = "";
			}
		}	
		for (int i = 0; i < sqsj.length; i++) {
			request.setAttribute(colList[i], Base.isNull(sqsj[i])?" ":sqsj[i]);
		}		
//		获取时间设置值end

		request.setAttribute("rs",map);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		return mapping.findForward("success");
	}
	//按床位数分配（公用版本）<整改后>
	public static ActionForward acws_DormDis(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String sycws = request.getParameter("qssycws");
		String nj = request.getParameter("nj");
		String addordel = request.getParameter("addordel");
		List<HashMap<String, String>> cwsList = new ArrayList<HashMap<String,String>>();
		if(sycws!=null&&!sycws.equalsIgnoreCase("")){
			for (int i = 1; i <= Integer.parseInt(sycws); i++) {
				HashMap<String, String> resTemp = new HashMap<String, String>();
				resTemp.put("en", String.valueOf(i));
				resTemp.put("cn", String.valueOf(i));
				cwsList.add(resTemp);
			}//end for
		}
		request.setAttribute("nj",nj);
		request.setAttribute("addordel",addordel);
		request.setAttribute("cwsList",cwsList);
		return mapping.findForward("success");
	}

	/**
	 * 合并宿舍划分中重复的年级、院系、寝室编号、分配床位数
	 * @param strValue（合并前有字符串）
	 * @return
	 */
	public static String dormSimilarStrUnion(String strValue){
		String temValue = "";
		StringBuffer retStr   = new StringBuffer();
		String[] strArr       = strValue.split(",");//strArr值排列方式：学院代码/宿舍编号/分配床位数/年级  
		StringBuffer comparer = new StringBuffer();
		for(int i=0;i<strArr.length;i++){
			String[] arrTem     = strArr[i].split("/");
			String strCompValue = arrTem[0]+"/"+arrTem[1];
			for(int j=0;j<strArr.length;j++){
				String[] arrTem2     = strArr[j].split("/");
				String strCompValue2 = arrTem2[0]+"/"+arrTem2[1];
				if(comparer.toString().indexOf(strCompValue)!=-1){
					continue;
				}else{
					if((strCompValue).equalsIgnoreCase(strCompValue2)){
						int m=0;
						for(int n=0;n<strArr.length;n++){
							String[] ArrTem3     = strArr[n].split("/");
							String strCompValue4 = ArrTem3[0]+"/"+ArrTem3[1];
							if(strCompValue.equalsIgnoreCase(strCompValue4)){
								m+=Integer.parseInt(ArrTem3[2]);
							}  	
						}
						comparer.append(strCompValue);
						comparer.append("!-!");
						retStr.append(strCompValue+"/"+m+"/"+arrTem[3]);
						retStr.append(",");
						continue;
					}else{
						int m=0;
						for(int n=0;n<strArr.length;n++){
							String[] ArrTem3     = strArr[n].split("/");
							String strCompValue4 = ArrTem3[0]+"/"+ArrTem3[1];
							if(strCompValue.equalsIgnoreCase(strCompValue4)){
								m+=Integer.parseInt(ArrTem3[2]);
							}  	
						}
						comparer.append(strCompValue);
						comparer.append("!-!");
						retStr.append(strCompValue+"/"+m+"/"+arrTem[3]);
						retStr.append(",");
					}
				}
			}
		}	   
		temValue =retStr.toString().substring(0,retStr.toString().lastIndexOf(","));//去掉最后一个字符“,”
		return temValue;
	}
	
	//按床位数分配（公用版本）<整改后>
	public static ActionForward wsjctj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		return mapping.findForward("success");
	}
}
