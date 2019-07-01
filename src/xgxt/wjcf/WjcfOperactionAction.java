package xgxt.wjcf;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.ShgcForm;
import xgxt.pjpy.cqdzgc.PjpyCqdzgcForm;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.ToolClass;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.fjgc.WjcfFjgcService;

import common.Globals;

public class WjcfOperactionAction {
	
	public static ActionForward stuPunishScoutTeach(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/wjcf_zgkd_gzjy.do", false);
		}
		DAO dao = DAO.getInstance();
		
		ShgcForm shgcForm = (ShgcForm) form;
		String xydm1 = shgcForm.getXydm();
		String zydm = shgcForm.getZydm();
		String bjdm = shgcForm.getBjdm();
		String nj = shgcForm.getNj();
		String nd = shgcForm.getNd();
		String xn = shgcForm.getXn();
		String xh = shgcForm.getXh();
		String cflb = shgcForm.getCflb();
		DealString deal = new DealString();
		String xydm = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(xydm);
		if ("xy".equalsIgnoreCase(userType)) {
			xydm1=xydm;
			shgcForm.setXydm(xydm);
		}
		List<String[]> rs = new ArrayList<String[]>();
		String rsNum = "";
		String pk = "xh||xn||sbsj";
		String tips = "违纪处分 - 跟踪教育 - 跟踪教育";
		String tableName = "wjcfb";
		String realName = "view_wjcf";
		String [] colList = new String[] { "主键", "xh", "xm", "xn", "nd", "xq",
				"cflbmc", "cfsj", "cfwh", "cfyymc" };
		@SuppressWarnings("unused")
		String [] setpara = getQueryCond(request, deal);                //////////////////获取查询字段
		List topTr = WjcfDataAccessAction.getTheadList(colList, realName);    ////////////获取表头
		StringBuffer whereSql = new StringBuffer();
		List<String> values = new ArrayList<String>();
		if (!xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			if (!StringUtils.isNull(nd)) {
				whereSql.append(" and nd = ?");
				values.add(nd);
			}// end if
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}// end if
		if (!StringUtils.isNull(xydm1)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm1);
		}// end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}// end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(DealString.toGBK(xh));
		}// end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(DealString.toGBK(bjdm));
		}// end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn=?");
			values.add(xn);
		}
		if (!StringUtils.isNull(cflb)) {
			whereSql.append(" and cflb=?");
			values.add(cflb);
		}
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("setxy", "setxy");
			request.setAttribute("xydm", xydm);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {//淮阴工学院
			return new ActionForward("/wjcf_hygxy_gzjyjl.do", false);
		}
		if (xxdm != null && xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){//上海工程
			topTr = WjcfOperactionAction.getSearchTitle();
			request.setAttribute("isSHGC", "yes");
		}
		//查询数据集
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			if (xxdm != null && xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				String sql = "select "+pk+" 主键, a.xh, a.xm, a.xn, a.nd, a.xq,a.cflbmc, a.cfsj, a.cfwh, a.cfyymc,(case when(months_between(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd'),to_date(cfsj,'yyyymmdd')))>=12 then 'pass' else 'nopass' end) ishg "+"from " + realName +  " a where 1=1 and cfwh is not null and cfsj is not null ";
							//+ " where nj like ? and cfsj > ? and xydm like ? and zydm like ? and bjdm like ? and xh like ? and nd like ? ";
				rs.addAll(dao.rsToVator(sql+whereSql, values!=null ? values.toArray(new String[0]) : new String[]{}, new String[]{"主键","xh", "xm", "xn", "nd", "xq",
				"cflbmc", "cfsj", "cfwh", "cfyymc","ishg"}));
			}
			else{
				String[] enCol = new String[] { "主键", "xh", "xm", "xn", "nd", "xq",
						"cflbmc", "cfsj", "cfwh", "cfyymc" };
				String sql = "select "+pk+" 主键,xh,xm,xn,nd,(select xqmc from xqdzb where xqdm=a.xq) xq,cflbmc,cfsj,cfwh,cfyymc,'' ishg from view_wjcf a where xxsh='通过' and cfwh is not null and cfsj is not null ";
				rs = dao.rsToVator(sql + whereSql.toString(), values != null ? values.toArray(new String[0]):new String[]{}, enCol); // ////获取查询记录
			}
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realName);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("tips", tips);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("rsNum", rsNum);
		List<HashMap<String, String>> cflbList = dao.getList(
				"select cflbdm,cflbmc from cflbdmb", new String[] {},
				new String[] { "cflbdm", "cflbmc" });
		request.setAttribute("cflbList", cflbList);
		appendProperties(request, shgcForm.getXydm(), "", "", "");
		return mapping.findForward("success");
	}

	// TODO 待定 学校审核处理信息
	public static ActionForward stuPunishCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return mapping.findForward("success");
	}

	public static ActionForward stuNotDeclareInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		WjcfForm checkForm = (WjcfForm) form;
		String xxdm = dao.getXxdm();
		StringBuilder sql = new StringBuilder();
		String tableName = "";
		StringBuffer querry = new StringBuffer();
		querry.append("");
		String rsNum = "";
		String pk = "";
		String []colList = null;
		String []colListCN = null;
		Vector<Object> rs = new Vector<Object>();

		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String nj = checkForm.getNj();
		String xn = checkForm.getXn();
		String nd = checkForm.getNd();
		String bmdm = request.getParameter("xydm");
		String zydm = "";
		String cflb = request.getParameter("cflb");
		String xh = request.getParameter("xh");
		String tips = "当前所在位置: 违纪处分 - "+Base.YXPZXY_KEY+"申报 - 未通过申报信息";
		if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
			tips = "当前所在位置: 违纪处理 - 系院申报 - 未通过结果查询";
		} else if (!Globals.XXDM_SHGC.equalsIgnoreCase(xxdm) && !Globals.XXDM_XBEMY.equalsIgnoreCase(xxdm)) {
			//厦门理工跳转到处分申报信息修改页面
			request.setAttribute("path", "not_declare_info.do");
			FormModleCommon.commonRequestSet(request);
			return new ActionForward("/wjcf_xmlg_cfsbxxModi.do", false);
		}
		pk = "xh||cfrq||cfwh";
		pk = "xh||sbsj";
		tableName = "view_wjcf";
		String userType = dao.getUserType(userDep);
		
		String rest = request.getParameter("resed");
		if("xy".equalsIgnoreCase(userType)){
			bmdm = userDep;
		}
//		if(Base.isNull(xn)){
//			xn = Base.currXn;
//		}
//		if(Base.isNull(nd)){
//			nd = Base.currNd;
//		}
		if (!StringUtils.isNull(xn)) {
			querry.append("and xn = '" + xn + "' ");
		}
		if (!xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX) && !StringUtils.isNull(nd)) {
			querry.append("and nd = '" + nd + "' ");
		}
		if (Base.isNull(nj)) {
			nj = "%";
		}
		if (Base.isNull(cflb)) {
			cflb = "%";
		}
		if (Base.isNull(bmdm)) {
			bmdm = "%";
		}
		if (!StringUtils.isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("'");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			//pk = "xh||sbsj";
			return new ActionForward("/wtgwjcfdefault.do",false);
		}
		List topTr=null;
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd", "xh", "xm",
					"bjmc", "cflbmc", "cfyymc", "sbsj", "xxsh" ,"xdwsh","xzsh"};
			
			sql.append("select rownum 行号,(case nvl(a.xxsh,'未审核') when '通过' ");
			sql.append("then '#FFFFFF' when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor,");
			sql.append(" a.* from(select ");
			sql.append(pk);
			sql.append(" 主键,a.* from view_wjcfsb a where 1=1 ");
			sql.append(querry);
			sql.append(" and nj like ? and cflb like ? and xydm like ? ");
			sql.append("and sbsj is not null and (xxsh='不通过' or xdwsh='不通过'");
			sql.append(" or xzsh='不通过') order by xxsh desc) a");
			
			colListCN = dao.getColumnNameCN(colList, "view_wjcfsb");
			topTr = dao.arrayToList(colList, colListCN);
		}else{
			colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd", "xh", "xm",
					"bjmc", "cflbmc", "cfyymc", "sbsj", "xxsh" };
			
			sql.append("select rownum 行号,(case nvl(a.xxsh,'未审核') when '通过' ");
			sql.append("then '#FFFFFF' when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor,");
			sql.append(" a.* from(select ");
			sql.append(pk);
			sql.append(" 主键,a.* from ");
			sql.append(tableName);
			sql.append(" a where 1=1 ");
			sql.append(querry);
			sql.append(" and nj like ? and cflb like ? and xydm like ? ");
			sql.append("and sbsj is not null and xxsh='不通过' order by xxsh desc) a");
			
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql.toString(), new String[] { nj, cflb, bmdm },
					colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}/*if ((request.getParameter("result") != null) && request.getParameter("result").equalsIgnoreCase("view")){
			request.setAttribute("result", "view");
		}*/
		if (!StringUtils.isNull(rest) && StringUtils.isEqual(rest, "wtgdel")) {
			request.setAttribute("result", "view");
		}
		//request.setAttribute("bjList", dao.getBjList("", "", nj));
		//request.setAttribute("zyList", dao.getZyList(userDep));// 发送专业列表
		String sqls = "select cflbdm,cflbmc from cflbdmb";
		List<HashMap<String, String>> cflbList = dao.getList(sqls, new String[] {}, new String[] {
				"cflbdm", "cflbmc" });
		request.setAttribute("cflbList", cflbList);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("tips", tips);
		request.setAttribute("writeAble", "yes");
		getListxx(request, dao, bmdm, zydm, nj);
		//appendProperties(request, bmdm, zydm, nj, "");
		return mapping.findForward("success");
	}

	//获取列表信息
	private static void getListxx(HttpServletRequest request, DAO dao,
			String xydm, String zydm, String nj) {
		String sql;
		List cflbList=null;
		String xxdm = dao.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			sql = "select * from cflbdmb where cflbmc=? or cflbmc=? or cflbmc=? or cflbmc=? or cflbmc=?";
			cflbList = dao.getList(sql, new String[]{"警告", "严重警告", "记过", "留校察看", "开除学籍"}, new String[]{"cflbdm", "cflbmc"});
		}else{
			sql = "select * from cflbdmb";
			cflbList = dao.getList(sql, new String[] {}, new String[] {
					"cflbdm", "cflbmc" });
		}
		xydm = xydm==null ? "":xydm;
		zydm = zydm==null ? "":zydm;
		nj = nj==null ? "":nj;
		String bjKey = xydm+"!!"+zydm+"!!"+nj;
		
		request.setAttribute("cflbList", cflbList);
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// 发送专业列表
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// 发送班级列表		
	}

	//跟踪教育维护
	public static ActionForward wjcfStuTrackTeach(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		DAO dao=DAO.getInstance();
		String pk = "xh||xn||sbsj";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String realName = "view_wjcf";
		String [] setpara = {pkValue};
		String[] colList = { "xh", "nd", "xm", "xn", "xb", "xq", "nj", "cflb",
				"xymc", "cfyy", "zymc", "cfsj", "bjmc", "cfwh", "xsbx1",
				"xsbx2", "xsbx3", "xsbx4", "xyyj" };
		HashMap<String, String> map = new HashMap<String, String>();
		map = WjcfDataAccessAction.getStuMap(pk, setpara, colList, realName); // 获取单个学生处分信息
		String xxdm=dao.getXxdm();
		//上海工程技术大学按季度显示受教育情况记录
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			String cfsj=map.get("cfsj");
			String year=cfsj.substring(0, 4);
			String month=cfsj.substring(4,6);
			String dt=cfsj.substring(6,8);
			int monthtmp=Integer.parseInt(month);
			int yeartmp=Integer.parseInt(year);
			String[] dttemp=new String[4];
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			int temp=0;
			if (sdf.parse(sdf.format(new Date())).after((sdf.parse(yeartmp+1+""+month+dt)))){
				temp=4;
			}else{
				for (int i=0;i<4;i++){
					monthtmp+=3;
					if (monthtmp>12){
						yeartmp++;
						monthtmp-=12;
					}
					if (monthtmp<10){
						dttemp[i]=yeartmp+"0"+monthtmp+""+dt;
					}else{
						dttemp[i]=yeartmp+""+monthtmp+""+dt;
					}
				}
				for (int i=1;i<=dttemp.length;i++){
					if (sdf.parse(sdf.format(new Date())).after(sdf.parse(dttemp[i-1]))){
						temp=i;
						if (temp==3)
							break;
					}
				}
			}
			request.setAttribute("isSHGC", temp);
			request.setAttribute("rs", map);
			request.setAttribute("pkValue", pkValue);
			return new ActionForward("/wjcf/zzsf/wjcf_shgc_gzjyb.jsp",false);
		}
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	}

	//保存跟踪教育信息
	public static ActionForward SaveStuTrackTeach(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableName = "wjcfb";
		String pk = "xh||xn||sbsj";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String xsbx1 = DealString.toGBK(request.getParameter("xsbx1"));
		String xsbx2 = DealString.toGBK(request.getParameter("xsbx2"));
		String xsbx3 = DealString.toGBK(request.getParameter("xsbx3"));
		String xsbx4 = DealString.toGBK(request.getParameter("xsbx4"));
		String xyyj = DealString.toGBK(request.getParameter("xyyj"));
		String [] colList = {"xsbx1","xsbx2","xsbx3","xsbx4","xyyj"};
		StandardOperation.update(tableName, colList, new String[] { xsbx1,
				xsbx2, xsbx3, xsbx4, xyyj }, pk, pkValue, request);
		return mapping.findForward("success");
	}

	//TODO 待定 学校审核
	public static ActionForward StuWjcfSchoolCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("success");
	}

	//获取查询条件
	@SuppressWarnings("unused")
	private static String[] getQueryCond(HttpServletRequest request,
			DealString deal) {
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = dao.getUserType(userDep);
		String xydm = "";
		String nj = DealString.toGBK(request.getParameter("nj"));
		if("xy".equalsIgnoreCase(userType)){
			xydm = request.getSession().getAttribute("userDep").toString();
			;
		}else{
			xydm = DealString.toGBK(request.getParameter("xyV"));
		}
		String nd = DealString.toGBK(request.getParameter("nd"));
		String zydm = DealString.toGBK(request.getParameter("zy"));
		String bjdm = DealString.toGBK(request.getParameter("bj"));
		String xh = DealString.toGBK(request.getParameter("xh"));
		

		dealDate dd = new dealDate();
		String CurrDate = dd.getToday();
		String MinCfsj = dd.getDate2(-365, CurrDate);

		if (nj == null || "".equalsIgnoreCase(nj)){
			nj = "%";
		}else{
			nj = "%" + nj + "%";
		}
		if (xydm == null || "".equalsIgnoreCase(xydm)){
			xydm = "%";
		}else{
			xydm = "%" + xydm + "%";
		}
		if (zydm == null || "".equalsIgnoreCase(zydm)){
			zydm = "%";
		}else{
			zydm = "%" + zydm + "%";
		}
		if (bjdm == null || "".equalsIgnoreCase(bjdm)){
			bjdm = "%";
		}else{
			bjdm = "%" + bjdm + "%";
		}
		if (xh == null || "".equalsIgnoreCase(xh)){
			xh = "%";
		}else{
			xh = "%" + xh + "%";
		}
		
        if (nd == null || "".equalsIgnoreCase(nd)){
        	nd = "%";
        }else{
        	nd = "%" + nd + "%";
        }
        String[] setpara = null;
        if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
        	setpara = new String[]{nj,xydm,zydm,bjdm,xh};
        } else {
        	 setpara = new String[]{nj,xydm,zydm,bjdm,xh,nd};
        }
		//String []setpara = {nj,MinCfsj,xydm,zydm,bjdm,xh,nd};
		return setpara;
	}

	public static ActionForward stuPunishApp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		String xxdm=dao.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			return new ActionForward("/shgcxysbdefault.do",false);
		}
		//南宁职业技术学院 裘力俊 2010-8-3
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)) {
			return new ActionForward("/shgcxysbdefault.do",false);
		}
		
		String cfpk = request.getParameter("cfpk");
		String sql = "";
		ShgcForm applyForm = (ShgcForm) form;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();
		String cflb = request.getParameter("cflb");
		String cfyy = request.getParameter("cfyy");
//		String query = "";
		String userDep = session.getAttribute("userDep").toString();
		String currXn = Base.currXn;
//		String currXq = Base.currXq;
		String currNd = Base.currNd;
		String result = "";
		String rsNum = "";
		String tmp[] = null;
		List cflbList=null;
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		sql = "select a.*,(select b.zzmmmc from view_xsxxb b where a.xh=b.xh) zzmmmc from view_xsjbxx a where xh=?";
		String[] colList = dao
		.getColumnName("select a.*,'' zzmmmc from view_xsjbxx a where 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
			map.remove("bz");
		}
		sql = "select count(*) rsNum from view_wjcf where xn=? and nd=? and xydm=? and xxsh='不通过'";
		rsNum = dao.getOneRs(sql, new String[] { currXn, currNd, userDep },
				"rsNum");
		if(!"0".equalsIgnoreCase(rsNum)){
			result = "view";
		}
		if(!Base.isNull(cflb))
			map.put("cflb", cflb);
		if(!Base.isNull(cfyy))
			map.put("cfyy", cfyy);
		sql = "select cflbdm,cflbmc from cflbdmb";
		cflbList = dao.getList(sql, new String[] {}, new String[] {
				"cflbdm", "cflbmc" });
		sql = "select cfyydm,cfyymc from cfyydmb";
		List cfyyList = dao.getList(sql, new String[] {}, new String[] {
				"cfyydm", "cfyymc" });
		request.setAttribute("cfyyList", cfyyList);
		sql = "select dqxn,dqnd from xtszb where rownum=1";
		tmp = dao.getOneRs(sql, new String[] {},
				new String[] { "dqxn", "dqnd" });
		map.put("xn", tmp[0]);
		map.put("nd", tmp[1]);
		map.put("stuExists", "yes");
		map.put("userType", userType);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			sql = "select cflbdm,cflbmc from cflbdmb where cflbmc=? or cflbmc=? or cflbmc=? or cflbmc=? or cflbmc=?";
			cflbList = dao.getList(sql, new String[]{"警告","严重警告","记过","留校察看","开除学籍"}, new String[]{"cflbdm", "cflbmc"});
			request.setAttribute("isXBEMY", "yes");
		}
		if (!StringUtils.isNull(cfpk)) {
			map = dao.getMapNotOut("select * from (select xh||cflb||cfyy||xn||nd pk,xh,xb,xn,nd,xm,nj,xymc,zymc,bjmc,cflb,cfyy,bz,xyclyj,lswjjl,wjsj,fjsclj,zzmmmc,xsqr from view_wjcf where xh||cflb||cfyy||xn||nd=? order by sbsj desc) where rownum=1", new String[]{cfpk});
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_FJGCXY)){
			String sbrSql = "select xm from yhb where yhm=? ";
			String userName=session.getAttribute("userName").toString();
			HashMap<String,String>hashMap=dao.getMap(sbrSql, new String[]{userName}, new String[]{"xm"});
			String sbr=hashMap.get("xm");
			map.put("sbr", sbr);
		}
		request.setAttribute("cflbList", cflbList);
		request.setAttribute("result", result);
		request.setAttribute("rs", map);
		request.setAttribute("cfpk", cfpk);
		request.setAttribute("path", "stuPunishApp.do");
		FormModleCommon.commonRequestSet(request);
		
		//以下判断要进行处分报表的打印
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CDTYXY)) {
			request.setAttribute("print", "true");
		}
		//判断学生确认状态位是否由教师确认
		if (xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)) {
			request.setAttribute("jsqr", "true");
		}
		return mapping.findForward("success");
	}

	//TODO 待定 学生网上点名
	public static ActionForward StuwebCall(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("success");
	}

	public static ActionForward StuCheckWorkInfoWh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)) {
			return new ActionForward("/szzykqxxwhdefault.do",false);
		}
		String realTable = "wjcf_xskqccb";
		String tableName = "view_xskqxx";
		String pk = "xn||xq||bjdm||kcdm";
		String go = request.getParameter("go");
		WjcfForm wjcfform = (WjcfForm) form;
		String xydm = wjcfform.getXydm();
		String zydm = wjcfform.getZydm();
		String bjdm = wjcfform.getBjdm();
		String nj = wjcfform.getNj();
		String xn = wjcfform.getXn();
		String xq = wjcfform.getXq();
		String[] colList = { "主键", "行号", "skdd", "xymc" ,"bjmc", "bzr", "rkjs", "kcmc",
				"rs", "sdrs", "cql" };
		StringBuffer querry = getQuerry(xydm, zydm, bjdm, nj, xn, xq);
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		List topTr = WjcfDataAccessAction.getTheadList(colList, tableName);

		if("go".equalsIgnoreCase(go) || go != null){
			vector.addAll(WjcfDataAccessAction.getKqRs(pk, colList, tableName,
					querry));
			rsNum = String.valueOf(vector.size());
			request.setAttribute("rs", vector);
		}

		getListxx(request, dao, xydm, zydm, nj);
		setListxx(wjcfform, xydm, zydm, nj);
		/*String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_NCDXKJXY.equalsIgnoreCase(xxdm)) {
			request.setAttribute("isNCDXKJXY", "yes");
		}*/
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("success");
	}
	
	public static ActionForward StuCheckWorkInfoWh_print(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String realTable = "wjcf_xskqccb";
		String tableName = "view_xskqxx";
		String pk = "xn||xq||bjdm||kcdm";
//		String go = request.getParameter("go");
		WjcfForm wjcfform = (WjcfForm) form;
		String xydm = wjcfform.getXydm();
		String zydm = wjcfform.getZydm();
		String bjdm = wjcfform.getBjdm();
		String nj = wjcfform.getNj();
		String xn = wjcfform.getXn();
		String xq = wjcfform.getXq();
		String[] colList = { "主键", "行号", "skdd", "bjmc", "bzr", "rkjs", "kcmc",
				"rs", "sdrs", "cql" };
		StringBuffer querry = getQuerry(xydm, zydm, bjdm, nj, xn, xq);
		Vector<Object> vector = new Vector<Object>();
		String rsNum = "";
		List topTr = WjcfDataAccessAction.getTheadList(colList, tableName);

		vector.addAll(WjcfDataAccessAction.getKqRs(pk, colList, tableName,
				querry));
			rsNum = String.valueOf(vector.size());
			request.setAttribute("rs", vector);
		

		getListxx(request, dao, xydm, zydm, nj);
		setListxx(wjcfform, xydm, zydm, nj);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("success");
	}
	
	public static ActionForward StuCheckWordStatReportForms(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String tableName = "view_xskqxx";
//		WjcfForm wjcfform = (WjcfForm) form;
		String cxtj = request.getParameter("querry");
		String [] tj = cxtj.split("@@");
		StringBuffer querry = getQuerry(tj[0], tj[1], tj[2], tj[3], tj[4],
				tj[5]);
		String sql = "";
		Calendar calendar = Calendar.getInstance();
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String[] colList = { "skdd", "xymc", "bjmc", "bzr", "rkjs", "kcmc",
				"rs", "sdrs", "cql", "qq", "qjrs", "bz" };
		sql = "select skdd,xymc,bjmc,bzr,rkjs,kcmc,rs,sdrs,cql,qq,qjrs,bz from "
				+ tableName + " where 1=1 " + querry.toString();
		List rs = dao.getList(sql, new String[]{}, colList);
		request.setAttribute("rs", rs);
		request.setAttribute("xxmc", ToolClass.getXxmc());
		request.setAttribute("day", day);
		
		request.setAttribute("month", month);
		return mapping.findForward("success");
	}

	public static ActionForward AddCheckWorkInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		WjcfForm myform=(WjcfForm)form;
		myform.deal_gbk();
		DAO dao = DAO.getInstance();
//		lrh_commen_util commen_util = new lrh_commen_util();
		String pk = "xn||xq||bjdm||kcdm";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_xskqxx";
		String sql = "";
//		String xxmc = dao.getOneRs("select xxmc from xtszb where rownum=1",
//				new String[] {}, "xxmc");
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = { "xn", "xq", "bjdm", "bjmc", "kcdm", "kcmc",
				"ydrs", "sdrs", "qjrs", "bz", "rq", "sjd", "xn_id" };
		sql = "select xn,xq,bjdm,bjmc,kcdm,kcmc,rs ydrs,sdrs,qjrs,bz,rq,sjd,xn_id from "
				+ tableName + " where " + pk + "=?";
	    map = dao.getMap(sql, new String[]{pkValue}, colList);
	    myform.setXn_id(map.get("xn_id"));
		if ("" != myform.getXn_id() && null != myform.getXn_id()) {
	    	myform.setSdrs(map.get("sdrs"));
			myform.setQjrs(map.get("qjrs"));
			myform.setRq(map.get("rq"));
			myform.setXn_id(map.get("xn_id"));			
			String []temp=map.get("sjd").split("-");
			myform.setKssj(temp[0]);
			myform.setJssj(temp[1]);			
			myform.setBz(map.get("bz"));
			
	    }
	    String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)) {				
			request.setAttribute("isSZXXZYJSXY", "isSZXXZYJSXY");
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	public static ActionForward SaveCheckWordInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjcfForm myform=(WjcfForm)form;
		myform.deal_gbk();
//		WjcfUtil myutil = new WjcfUtil();
//		lrh_commen_util commen_util = new lrh_commen_util();
//		String message=myutil.wjcf_xskqccb_add2(myform);
//		DAO dao = DAO.getInstance();
//		DealString deal = new DealString();
//		String tableName = "wjcf_xskqccb";
//		String pk = "xn||xq||bjdm||kcdm";
//		String pkValue = request.getParameter("pkValue");
//		String xn = request.getParameter("xn");
//		String xq = request.getParameter("xq");
//		String bjdm = request.getParameter("bjdm");
//		String kcdm = request.getParameter("kcdm");
//		String sdrs = request.getParameter("sdrs");
//		String qjrs = request.getParameter("qjrs");
//		String bz = deal.toGBK(request.getParameter("bz"));
//		String []colList = {"xn","xq","bjdm","kcdm","sdrs","qjrs","bz"};
//		String []valList = {xn,xq,bjdm,kcdm,sdrs,qjrs,bz};
//		boolean del = false;
//		if("".equalsIgnoreCase(pkValue) || pkValue == null){
//			del = StandardOperation.delete(tableName, pk, xn+xq+bjdm+kcdm);
//		}else{
//			del = StandardOperation.delete(tableName, pk, pkValue);
//		}
//		if(del){
//			StandardOperation.insert(tableName, colList, valList);
//		}
		request.setAttribute("result", "view");
		return mapping.findForward("success");
	}
	
	public static ActionForward JwStuWorkInfoQuerry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String tableName = "view_kqb";
		Vector<Object> vector = new Vector<Object>();
		WjcfForm wjcfform = (WjcfForm) form;
		String nj = wjcfform.getNj();
		String xydm = wjcfform.getXydm();
		String zydm = wjcfform.getZydm();
		String sql ="";
		String rsNum = "";
		String go = request.getParameter("go");
		String []colList = {"xh","xn","xq","kqsj","kqqk"};
		List topTr = WjcfDataAccessAction.getTheadList(colList, tableName);
		if("go".equalsIgnoreCase(go)){
			sql ="select * from " + tableName;
			vector.addAll(dao.rsToVator(sql, new String[]{}, colList));
			request.setAttribute("rs", vector);
			rsNum = vector == null ? "0" : String.valueOf(vector.size());
			request.setAttribute("rsNum", rsNum);
		}
		
		getListxx(request, dao, xydm, zydm, nj);
		setListxx(wjcfform, xydm, zydm, nj);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", tableName);
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("success");
	}

	public static ActionForward JwStuWorkInfoPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String tableName = "view_kqb";
		Vector<Object> vector = new Vector<Object>();
		WjcfForm wjcfform = (WjcfForm) form;
		String nj = wjcfform.getNj();
		String xydm = wjcfform.getXydm();
		String zydm = wjcfform.getZydm();
		String sql ="";
		String rsNum = "";
		String []colList = {"xh","xn","xq","kqsj","kqqk"};
		List topTr = WjcfDataAccessAction.getTheadList(colList, tableName);
	
		sql ="select * from " + tableName;
		vector.addAll(dao.rsToVator(sql, new String[]{}, colList));
		request.setAttribute("rs", vector);
		rsNum = vector == null ? "0" : String.valueOf(vector.size());
		request.setAttribute("rsNum", rsNum);
		
		getListxx(request, dao, xydm, zydm, nj);
		setListxx(wjcfform, xydm, zydm, nj);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", tableName);
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("success");
	}
	
	public static ActionForward PrintXswjcfqkb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String[]colList = null;
		String xh = request.getParameter("xh");
		String bz = DealString.toGBK(request.getParameter("bz"));
		String sql = "";
		String xxmc = ToolClass.getXxmc();
		String xxdm = Base.xxdm;
		String cflb = request.getParameter("cflb");
		String cfyy = request.getParameter("cfyy");
		//厦门理工单独报表
		if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/wjcf_xmlg_cfbprint.do?xh=" + xh, false);
		} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/wjcf_zjlg_cfbprint.do?xh=" + xh + "&cflb=" + cflb + "&cfyy=" + cfyy, false);
		}
		HashMap<String, String> map = new HashMap<String, String>();
		colList = new String[] { "xh", "xm", "xb", "bjmc", "nj", "xymc",
				"zymc","bjmc", "njzy", "sfzh", "lxdh", "jg", "zzmm", "csrq", "xslb" ,"rxrq"};
		sql = "select a.xh,a.xm,a.xb,a.bjmc,a.nj,a.xymc,a.zymc,a.bjmc,a.nj||a.zymc njzy,a.sfzh,a.lxdh,(select b.jgm from bks_xsjbxx b where a.xh=b.xh) jg,s.zzmmmc zzmm,s.csrq,(select b.xslbm from bks_xsjbxx b where a.xh=b.xh) xslb,(select b.rxny from bks_xsjbxx b where a.xh=b.xh) rxrq from view_xsjbxx a,view_stu_details s where a.xh=s.xh and a.xh=?";
		map = dao.getMap(sql, new String[]{xh}, colList);
		map.put("xxmc", xxmc);
		request.setAttribute("rs", map);
		request.setAttribute("bz", bz);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			String tips = "学生违纪处分预审表";
			/*String csrq = map.get("csrq") != null ?map.get("csrq"): "";
			String rxrq = map.get("rxrq") != null ?map.get("rxrq"): "";
			if (csrq != "" && csrq != null){
				if (csrq.split("-").length > 0){
					request.setAttribute("year", csrq.split("-")[0]);
					request.setAttribute("mon", csrq.split("-")[1]);
					request.setAttribute("dt", csrq.split("-")[2]);
				}else{
					request.setAttribute("year", csrq.substring(0, 4));
					request.setAttribute("mon", csrq.substring(4, 6));
					request.setAttribute("dt", csrq.substring(6, 8));
				}
			}else{
				request.setAttribute("year", "");
				request.setAttribute("mon", "");
				request.setAttribute("dt", "");
			}
			if (rxrq != "" && rxrq != null){
				if (rxrq.split("-").length > 0){
					request.setAttribute("rxy", rxrq.split("-")[0]);
					request.setAttribute("rxm", rxrq.split("-")[1]);
					request.setAttribute("rxd", rxrq.split("-")[2]);
				}else{
					request.setAttribute("rxy", rxrq.substring(0, 4));
					request.setAttribute("rxm", rxrq.substring(4, 6));
					request.setAttribute("rxd", rxrq.substring(6, 8));
				}
			}else{
				request.setAttribute("rxy", "");
				request.setAttribute("rxm", "");
				request.setAttribute("rxd", "");
			}*/
			request.setAttribute("tips", tips);
			return new ActionForward("/wjcf/zzsf/wjcf_shgc_xswjcfqkb.jsp",
					false);
		}
		
		return mapping.findForward("success");
	}
	
	private static StringBuffer getQuerry(String xydm, String zydm,
			String bjdm, String nj, String xn, String xq) {
		StringBuffer querry = new StringBuffer();
		if(nj == null || "".equalsIgnoreCase(nj)){
			querry.append("and 1=1 ");
		}else{
			if(Check_Input_Value.check2(nj)){
				querry.append("and nj= '");
				querry.append(nj);
				querry.append("' ");
			}
		}
		if(xydm == null || "".equalsIgnoreCase(xydm)){
			querry.append("and 1=1 ");
		}else{
			if(Check_Input_Value.check2(xydm)){
				querry.append("and xydm= '");
				querry.append(xydm);
				querry.append("' ");
			}
		}
		if(zydm == null || "".equalsIgnoreCase(zydm)){
			querry.append("and 1=1 ");
		}else{
			if(Check_Input_Value.check2(zydm)){
				querry.append("and zydm= '");
				querry.append(zydm);
				querry.append("' ");
			}
		}
		if(bjdm == null || "".equalsIgnoreCase(bjdm)){
			querry.append("and 1=1 ");
		}else{
			if(Check_Input_Value.check2(bjdm)){
				querry.append("and bjdm= '");
				querry.append(bjdm);
				querry.append("' ");
			}
		}
		if(xn == null || "".equalsIgnoreCase(xn)){
			querry.append("and 1=1 ");
		}else{
			if(Check_Input_Value.check2(xn)){
				querry.append("and xn= '");
				querry.append(xn);
				querry.append("' ");
			}
		}
		if(xq == null || "".equalsIgnoreCase(xq)){
			querry.append("and 1=1 ");
		}else{
			if(Check_Input_Value.check2(xq)){
				querry.append("and xq= '");
				querry.append(xq);
				querry.append("' ");
			}
		}
		return querry;
	}

	//设置列表信息
	private static void setListxx(WjcfForm wjcfform, String xydm, String zydm,
			String nj) {
		wjcfform.setNj(nj);
		wjcfform.setXydm(xydm);
		wjcfform.setZydm(zydm);
	}
	//学生申诉申请	
	@SuppressWarnings("deprecation")
	public static ActionForward ShsSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, IOException {
	    DAO dao = DAO.getInstance();
	    WjcfForm wjcfForm = (WjcfForm) form;
	    HashMap<String,String> map = new HashMap<String,String>();
		String userType = request.getSession().getAttribute("userOnLine")
				.toString();
		String yhlb = request.getSession().getAttribute("userType").toString();
	    String xxdm = dao.getXxdm();
		String pk = " xh||cfwh||cfsj";
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));//学生申诉申请纪录ID
		String cfid = DealString.toGBK(request.getParameter("cfid"));//学生违纪处分纪录ID
		String doType = DealString.toGBK(request.getParameter("doType"));
		String from = request.getParameter("from");//
		String sql = "";
		String realTable = "wjcf_xsssb";
		String[] colList = null;
		String tit = "填写申请表";
		String xh = request.getParameter("xh");
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){//浙江机电职业技术学院
		   request.setAttribute("isZJJDZYJSXY", "ok");
		}
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			return new ActionForward("/shsldefault.do", false);
		}*/
		if(userType.equalsIgnoreCase("student")){
		   xh = request.getSession().getAttribute("userName").toString();	   
		}
		if(xh!=null){
			xh=xh.trim();
		    sql = "select * from view_xsjbxx where xh = ? ";
		    colList = dao.getColumnName("select * from view_xsjbxx where 1=2");
		    String[] stuInfo = dao.getOneRs(sql,new String[]{xh},colList);
		    if(stuInfo != null){
			   for(int i=0;i<colList.length;i++){
				map.put(colList[i].toLowerCase(), stuInfo[i]);//个人信息
			   }
		    }
		}
		
		sql = "select * from view_wjcf where xh||trim(cfwh)||cfsj = ?";
		colList = dao.getColumnName("select * from view_wjcf where 1=2");
		String[] wjcfInfo = dao.getOneRs(sql, new String[]{cfid}, colList);
		if(wjcfInfo != null){
			for(int i=0;i<colList.length;i++){
				map.put(colList[i].toLowerCase(), wjcfInfo[i]);//违纪信息
			}
		}
		dealDate dd = new dealDate();
		
		String CurrDate = GetTime.getNowTime4();	
		map.put("sssj", CurrDate);
		request.setAttribute("rs", map);//获取当前日期
        if("save".equalsIgnoreCase(doType)&&from==null){//保存信息
        	
        	String cfwh = request.getParameter("cfwh");
        	String xn = request.getParameter("xn");
    		String nd = request.getParameter("nd");
    		String xq = request.getParameter("xq");
    		String cflbmc = request.getParameter("cflbmc");
    		String cfyymc = request.getParameter("cfyymc");
    		String cfyy = request.getParameter("cfyy");
    		String cfsj = request.getParameter("cfsj");
        	String lxdh = request.getParameter("lxdh");
        	String dz = request.getParameter("dz");
        	String yb = request.getParameter("yb");
        	String sssj = request.getParameter("sssj");
        	String yq = request.getParameter("yq");
        	String cfxg = request.getParameter("cfxg");
         	String filePath = null;
         	String flag = request.getParameter("flag");
         	String lj = request.getParameter("lj");
    		String message = "";
    		String dir = request.getRealPath("/") + "upload";
    		String dateStr = xh+cfwh+cfsj;   
    		request.setAttribute("pk", xh+cfwh+cfsj+cflbmc+cfyymc);
    	    File f = new File(dir);
    	    if (!f.exists()) {
    		   f.mkdir();
    	    }
    	    Timestamp date = new Timestamp(System.currentTimeMillis());
    	    dateStr += date.toString().substring(0, 19);
    	    dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(":","");
    	    FormFile file = wjcfForm.getUploadFile();
    	    if(file==null){
    		   message = "附件上传失败！";
    		   request.setAttribute("message", message);
    		   request.setAttribute("inserted","no");
    		   return mapping.findForward("false");
    	   }
    	   else{
    		   int size = file.getFileSize();
    		   if(size>0){//有文件上传
    			   String fName = dateStr+file.getFileName();
    			   InputStream in = file.getInputStream();
    			   filePath = dir + "/" + fName;
    			   OutputStream out = new FileOutputStream(filePath);
    			   int bytesRead = 0;
    			   byte[] buffer = new byte[size];
    			   while ((bytesRead = in.read(buffer, 0, size)) != -1) {
    				   out.write(buffer, 0, bytesRead);
    			   }
    			   out.close();
    			   in.close();
    		   }
    	   }
        	boolean res = false;
        	String[] oldVals = null;
        	try{
        		if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
         			sql = "delete from "+realTable+" where xh=? and sssj=? and cfwh=?";
    				//res = dao.runUpdate(sql, new String[] { xh,sssj,cfwh });  
    				res = StandardOperation.delete(realTable, "xh||sssj||cfwh", xh+sssj+cfwh, request);
    			} else {//“pkValue”存在时删除其标志的处分纪录
    				oldVals = dao.getOneRs("select xh,lxdh,dz,yb,sssj,yq,xn,nd,xq,cflbmc,cfyymc,cfsj,cfwh,wjsclj,cfxg from "+realTable+" where "+pk+" =?", new String[]{pkValue}, 
    						new String[]{"xh","lxdh","dz","yb","sssj","yq","xn","nd","xq","cflbmc","cfyymc","cfsj","cfwh","wjsclj","cfxg"});
    				sql = "delete from "+realTable+" where " + pk + "='" + pkValue
    				+ "'";
    				//res = dao.runUpdate(sql, new String[] {});
    				res = StandardOperation.delete(realTable, pk, pkValue, request);
    			} 
                 if(res){
                	 if (!StringUtils.isNull(flag) && StringUtils.isEqual("modi", flag)) {
                		 if (StringUtils.isNull(filePath)) {
                			 if (!StringUtils.isNull(lj)) {
                				 filePath = lj;
                			 }
                		 }
                	 }
            	      if (Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)) {
            	    	  res = StandardOperation.insert(realTable, new String[]{"xh","lxdh","dz","yb","sssj",
                  	    		"yq","xn","nd","xq","cflbmc","cfyymc","cfsj","cfwh","wjsclj","cfxg"}, 
                  	    		new String[]{xh,lxdh,dz,yb,sssj,yq,xn,nd,xq,cflbmc,cfyy,cfsj,cfwh,filePath,cfxg}, request);
            	      } else {
            	    	  res = StandardOperation.insert(realTable, new String[]{"xh","lxdh","dz","yb","sssj",
                  	    		"yq","xn","nd","xq","cflbmc","cfyymc","cfsj","cfwh","wjsclj","cfxg"}, 
                  	    		new String[]{xh,lxdh,dz,yb,sssj,yq,xn,nd,xq,cflbmc,cfyymc,cfsj,cfwh,filePath,cfxg}, request);
            	      }
            	      if(res){
          				dao.writeLog(sql, new String[] {xh,lxdh,dz,yb,sssj,yq,xn,nd,xq,cflbmc,cfyymc,cfsj,cfwh,filePath,cfxg }, oldVals, "增加记录:", request);
            	      }
                 }          
        	}catch(Exception e){
        		 res = false;
        	}
            if(res){
            	request.setAttribute("inserted", "ok");
            }else{
            	request.setAttribute("inserted", "no");
            }

        }
        
        //只有学校和管理员才可以修改。
		  if ("xx".equalsIgnoreCase(yhlb) || "admin".equalsIgnoreCase(yhlb)) {
			  request.setAttribute("xgzt", "yes");
		  }
        
        if(pkValue!=null){//当学生申诉申请纪录ID“pkValue”存在时查找学生申诉表中该条记录"LXDH","DZ","YB","SSSJ","YQ"信息
        	sql = "select LXDH,DZ,YB,SSSJ,YQ,CFXG  from "+realTable+" where "+pk+" = ?";
        	colList = new String[]{ "LXDH","DZ","YB","SSSJ","YQ","CFXG"};
         	if("modi".equalsIgnoreCase(doType)){
        		  request.setAttribute("modi", "domodi");	
        		  tit = "修改申请表";
        		  if(from==null){
        		     sql = "select LXDH,DZ,YB,SSSJ,YQ,SH,CFWH,XN,ND,XQ,CFLBMC,CFYYMC cfyy,(select b.cfyymc from cfyydmb b where b.cfyymc=wjcf_xsssb.cfyymc ) CFYYMC,CFSJ,CFXG,JD from "+realTable+" where "+pk+" = ?";
            	     colList = new String[]{ "LXDH","cfyy","DZ","YB","SSSJ","YQ","SH","CFWH","XN","ND","XQ","CFLBMC","CFYYMC","CFSJ","CFXG","JD"};            	
            	     request.setAttribute("modi", "domodi");
        		  }   
        		  
        		 
        		  
        	}
        	
        	String[] stemInfo = dao.getOneRs(sql, new String[]{pkValue}, colList);
        	if(stemInfo != null){
        		for(int i=0;i<stemInfo.length;i++){
        			map.put(colList[i].toLowerCase(), stemInfo[i]);
        		}
        		
        	}
        	request.setAttribute("flag", "modi");//修改状态
        }
        String ssId = (pkValue.equalsIgnoreCase("")||pkValue==null) ? xh+request.getParameter("cfwh")+request.getParameter("cfsj") : pkValue;
        List  rswj = WjcfOperactionAction.GetFileList(ssId);//发送上传材料证明数据
		request.setAttribute("rswj", rswj);	
		if (StringUtils.isNull(map.get("dz"))) {
			map.put("dz", request.getParameter("dz"));
		}
		if (StringUtils.isNull(map.get("yb"))) {
			map.put("yb", request.getParameter("yb"));
		}
		if (StringUtils.isNull(map.get("yq"))) {
			map.put("yq", request.getParameter("yq"));
		}
		request.setAttribute("rs", map);
        request.setAttribute("tit", tit);		
		request.setAttribute("cfid", cfid);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("xqList", Base.getXqList());
		
		
		
		return mapping.findForward("success");
		
	}
	//个人违纪处分信息显示
	public static ActionForward WjcfInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
//		WjcfForm wjcfForm = (WjcfForm) form;
		Vector<Object> rs = new Vector<Object>();
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String sql = "";
		String tabName = "view_wjcf";
		String[] colList = null;
		String[] colListCN = null;
		String tabType = request.getParameter("tabType");
		String tips = "违纪处分 - 学生申诉申请 - 申请 - 个人违纪处分信息";
		String xxdm = dao.getXxdm();
		colList = new String[] { "CFID", "CFWH", "ND", "XN", "XQ", "CFLBMC",
				"CFYYMC", "CFSJ" };
		colListCN = dao.getColumnNameCN(colList, tabName);
		List topTr = dao.arrayToList(colList,colListCN);
		sql = "select (xh||cfwh||cfsj) cfid,cfwh,nd,xn,(select xqmc from xqdzb where xqdm=a.xq) xq,cflbmc,cfyymc,cfsj from "
				+ tabName + " a where xh = ? and cfsj is not null and cfwh is not null and xxsh='通过' ";
		
		//撤消处分时选择学生
		if ("cx".equalsIgnoreCase(request.getParameter("lx"))) {
			sql += " and not exists (select 1 from wjcf_cxcfb b where a.xh=b.xh and a.cfwh=b.cfwh and a.cfsj=b.cfsj)";
		} else {
			sql += " and not exists (select 1 from wjcf_xsssb b where a.xh=b.xh and a.cfwh=b.cfwh and a.cfsj=b.cfsj)";
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY) && (tabType != null) &&(tabType!="")) {
			request.setAttribute("isCSMZ", "yes");
			sql = "select (xh||cfwh||cfsj) cfid,cfwh,nd,xn,xq,cflbmc,cfyymc,cfsj from "
				+ tabName + " where xh = ? and cfsj is not null and cfwh is not null and xxsh='通过'";
		}
//		else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
//			sql = "select (xh||cfwh||cfsj) cfid,cfwh,nd,xn,xq,cflbmc,cfyymc,cfsj from "
//				+ tabName + " where xh = ? and cfsj is not null and cfwh is not null and xxsh='通过' and xsqr<>'已确认'";
//		}
		rs.addAll(dao.rsToVator(sql, new String[]{xh}, colList));
		String rsNum="";
		if(rs == null){
			rsNum = "0";
		}else{
			rsNum = String.valueOf(rs.size());
		}
		request.setAttribute("xm", xm);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("tips", tips);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("tabType", tabType);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)
				&& (tabType != null)
				&& (tabType.equalsIgnoreCase("wjcf_cxcfb")) || Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/wjcf/csmz/csmz_wjcf_info.jsp",false);
		}
		return mapping.findForward("success");
	}
    //dwr客户端判断编号是否已经存在(有条件)
	public boolean getInfoEx(String tableName, String pk, String pkValue,
			String andCondition) {
		DAO dao=DAO.getInstance();
		boolean flag=false;
		int result=0;
		String sql = "select count(*) num from " + tableName + " where " + pk
				+ "=? ";
		if(andCondition != ""){
			sql += " and ";
			sql += andCondition;
		}
		result = Integer.parseInt(dao.getOneRs(sql, new String[] { pkValue },
				"num"));
		if(result>0){
			flag=true;
		}else{
			flag=false;
		}
		return flag;
	}
  
   //学生申诉申请查询
	public static ActionForward WjcfShsSqCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
	   DAO dao = DAO.getInstance();
	   WjcfForm wjcfForm = (WjcfForm)form;
		String userType = request.getSession().getAttribute("userOnLine")
				.toString();
	   String xh = wjcfForm.getXh();
	   String sql = "";
	   String tabName = "view_wjcf_xsssxx";
	   String[] colList = null;
	   String[] colListCN = null;
	   List topTr = null;
	   String xxdm = dao.getXxdm();
	   Vector<Object> rs = new Vector<Object>();
	   if("student".equalsIgnoreCase(userType)){
		   xh = request.getSession().getAttribute("userName").toString();
		   if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			   sql = "select xh,cfwh,cflbmc,cfyymc,cfsj,sssj,slqk,mqzt from "
					+ tabName + " where xh =?";
			colList = new String[] { "xh", "cfwh", "cflbmc", "cfyymc", "cfsj",
					"sssj", "slqk", "mqzt" };
		   }else if (Globals.XXDM_GZDX.equals(xxdm)) {
			   sql = "select xh,cfwh,ggcflbmc,cfyymc,cfsj,sssj,sh,jd,cflbmc from "
					+ tabName + " where xh =?";
			   colList = new String[] { "xh", "cfwh","cfsj", "ggcflbmc", "cfyymc", 
					"sssj", "jd","cflbmc" };
		   }else{
			   sql = "select xh,xn,(select xqmc from xqdzb where xqdm=a.xq) xq,cfwh,cfsj,cflbmc,cfyymc,sssj,sh,jd from "
					+ tabName + " a where xh =?";
			   colList = new String[] { "xh","xn","xq", "cfwh", "cflbmc", "cfyymc", "cfsj",
					"sssj", "jd"};
		   }
		   if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			   colListCN = new String[]{ "xh", "处分文号","处分时间", "处分类别", "处分原因", 
						"申诉时间", "委员会决定","原处分类别" };
		   } else {
			   colListCN = dao.getColumnNameCN(colList, tabName);
		   }
		   
		   topTr = dao.arrayToList(colList, colListCN);
	       rs.addAll(dao.rsToVator(sql, new String[]{xh}, colList));
	       request.setAttribute("topTr", topTr);
		   request.setAttribute("rs", rs);		 
		   request.setAttribute("num", String.valueOf(rs.size()));
	   }else{		   
		   return new ActionForward("/wjcf_shsdatasearch.do?act=cx",false);
	   }
	   return mapping.findForward("success");
   }

	public static ActionForward WjcfShsDataSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
        //数据查询
		WjcfForm  dataSearchForm = (WjcfForm)form;
		HttpSession session = request.getSession();
		String tableName = "view_wjcf_xsssxx";
		String realTable = "wjcf_xsssb";
		String act = request.getParameter("act");
		String go = request.getParameter("go");
		go = "go";
		DAO dao = DAO.getInstance();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy="";
		String tmp = "";
		if(userType.equalsIgnoreCase("xy")){
			tmp = " and xydm=";
			xy = userDep;
			dataSearchForm.setXydm(xy);
			tmp += "'" + userDep + "'";
		} else {
			xy = request.getParameter("xydm");
		}
		boolean disabled = true;
		String tips = "违纪处分 - 学生申诉申请 - 查询";
		
		//申诉审核时默认查询当前学年，年度
		if (act.equalsIgnoreCase("decide")){
			if (StringUtils.isNull(dataSearchForm.getXn())) {
				dataSearchForm.setXn(Base.currXn);
			}
			if (StringUtils.isNull(dataSearchForm.getNd())) {
				dataSearchForm.setNd(Base.currNd);
			}
			
		}
		
		String cues = "双击一行可以查看详细信息;单击表头可以排序";
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");// sql条件
		String rsNum = "0";// 返回的记录数
		String nj = dataSearchForm.getNj();
		
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xh = dataSearchForm.getXh();
		String xm = dataSearchForm.getXm();
		String xn = dataSearchForm.getXn();
		String nd = dataSearchForm.getNd();
		String sh = dataSearchForm.getSh();
		String xxdm = dao.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
			tips = "违纪处理 - 学生申诉 - 查询";
		}
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("shList", dao.getChkList(3));//审核类型列表
		
		if (xy == null) {
		    xy = "";
		}
		if (zy == null) {
		    zy = "";
		}
		querry.append(((nj == null) || nj.equalsIgnoreCase("")) ? "and 1=1 "
				: "and nj = '" + nj + "' ");
		querry.append(((xy == null) || xy.equalsIgnoreCase("")) ? "and 1=1 "
				: "and xydm = '" + xy + "' ");
		querry.append(((zy == null) || zy.equalsIgnoreCase("")) ? "and 1=1 "
				: "and zydm = '" + zy + "' ");
		querry.append(((bj == null) || bj.equalsIgnoreCase("")) ? "and 1=1 "
				: "and bjdm = '" + bj + "' ");
		querry.append(((xh == null) || xh.equalsIgnoreCase("")) ? "and 1=1 "
				: "and xh = '" + xh.trim() + "' ");
		querry.append(((xm == null) || xm.equalsIgnoreCase("")) ? "and 1=1 "
				: "and xm like '%" + DealString.toGBK(xm.trim()) + "%' ");
		querry.append(((xn == null) || xn.equalsIgnoreCase("")) ? "and 1=1 "
				: "and xn = '" + xn + "' ");
		querry.append(((nd == null) || nd.equalsIgnoreCase("")) ? "and 1=1 "
				: "and nd = '" + nd + "' ");
		querry.append(((sh == null) || sh.equalsIgnoreCase("")) ? "and 1=1 "
				: "and sh = '" + sh + "' ");
		
		dataSearchForm.setXm(DealString.toGBK(xm));
		
		if(act.equalsIgnoreCase("cx")){
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				sql = "select rownum 行号,xn,xh||cfwh||cfsj pk,xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,cfyymc,sssj,slqk,mqzt from "
					+ tableName + querry;
				colList = new String[] { "行号", "pk","xn", "xh", "xm",
					"bjmc", "cfwh", "cflbmc", "cfyymc", "sssj", "slqk", "mqzt" };
			}/*else if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				sql = "select rownum 行号,xh||cfwh||cfsj pk,xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,cfyymc,sssj,fdysh,sh,jd from "
					+ tableName + querry;
				colList = new String[] { "行号", "pk", "xh", "xm", "xymc", "zymc",
					"bjmc", "cfwh", "cflbmc", "cfyymc", "sssj","fdysh", "sh", "jd" };
			}*/else if (Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)) {
				sql = "select rownum 行号,xn,xh||cfwh||cfsj pk,xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,(select b.cfyymc from cfyydmb b where b.cfyydm=view_wjcf_xsssxx.cfyymc) cfyymc,sssj,sh,jd from "
					+ tableName + querry;
				colList = new String[] { "行号", "pk","xn", "xh", "xm", "xymc", "zymc",
					"bjmc", "cfwh", "cflbmc", "cfyymc", "sssj", "sh", "jd" };
			} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				sql = "select rownum 行号,xn,xh||cfwh||cfsj pk,xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,cfyymc,sssj,jd from "
					+ tableName + querry;
				colList = new String[] { "行号", "pk","xn", "xh", "xm",
					"bjmc", "cfwh", "cflbmc", "cfyymc", "sssj"};
			} else {
				sql = "select rownum 行号,xn,xh||cfwh||cfsj pk,xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,cfyymc,sssj,sh,jd from "
					+ tableName + querry;
				colList = new String[] { "行号", "pk","xn", "xh", "xm",
					"bjmc", "cfwh", "cflbmc", "cfyymc", "sssj","jd" };
			}	
		}else if(act.equalsIgnoreCase("discuss")){
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				tips = "违纪处分 - 申诉申请审核 - "+Base.YXPZXY_KEY+"受理";
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
				tips = "违纪处理 - 申诉处理 - 委员会讨论";
			} else{
			 tips = "违纪处分 - 申诉申请审核 - 委员会讨论";
			 }
			 if(go!=null&&go.equalsIgnoreCase("go")) {//点击查询按钮时
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
					sql = "select(case when slqk='等待受理' then '#CCCCCC' else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk,"
						+ " xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,"
						+ "cfyymc,sssj,slqk from " + tableName + querry;
					
				}else if (Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)) {
					sql = "select(case when sh='未讨论' then '#CCCCCC' else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk,"
						+ " xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,"
						+ "(select b.cfyymc from cfyydmb b where b.cfyydm=view_wjcf_xsssxx.cfyymc) cfyymc,sssj,sh from " + tableName + querry;
				} else if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
					sql = "select(case when sh='未讨论' then '#CCCCCC' else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk,"
						+ " xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,"
						+ "cfyymc,sssj,sh from " + tableName + querry + " and wyhsl='受理'";
				}else {
					sql = "select(case when sh='未讨论' then '#CCCCCC' else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk,"
						+ " xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,"
						+ "cfyymc,sssj,sh from " + tableName + querry;
				}
				cues = "双击一行可以查看详细信息、并进行相关操作;单击表头可以排序";
			 }else{//点击链接时
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
					sql = "select  (case when slqk='等待受理' then '#CCCCCC' else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk, "
						+ "xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,"
						+ "cfyymc,sssj,slqk from "
						+ tableName 
						+ " where slqk='等待受理' "+tmp+" and rownum <=200  order by sssj";
				} else if (Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)) {
					sql = "select  (case when sh='未讨论' then '#CCCCCC' else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk, "
						+ "xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,"
						+ "(select b.cfyymc from cfyydmb b where b.cfyydm=view_wjcf_xsssxx.cfyymc) cfyymc,sssj,sh from "
						+ tableName
						+ " where sh='未讨论' "+tmp+" and rownum <=200  order by sssj";
				} else if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
					sql = "select  (case when sh='未讨论' then '#CCCCCC' else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk, "
						+ "xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,"
						+ "cfyymc,sssj,sh from "
						+ tableName
						+ " where wyhsl='受理' and sh='未讨论' "+tmp+" and rownum <=200  order by sssj";
				}else {
					sql = "select  (case when sh='未讨论' then '#CCCCCC' else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk, "
						+ "xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,"
						+ "cfyymc,sssj,sh from "
						+ tableName
						+ " where sh='未讨论' "+tmp+" and rownum <=200  order by sssj";
				}
				 cues = "双击一行可以查看详细信息、并进行相关操作;单击表头可以排序;(最近部分未受理的学生处分申诉申请)" ;
		     } 
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				colList = new String[] { "bgcolor", "pk", "xh", "xm", "xymc",
						 "bjmc", "cfwh", "cflbmc", "cfyymc", "sssj", "slqk" };
			}else{
				colList = new String[] { "bgcolor", "pk", "xh", "xm",
						"bjmc", "cfwh", "cflbmc", "cfyymc", "sssj", "sh" };
			}
		}else if(act.equalsIgnoreCase("decide")){
			if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				return new ActionForward("/wjcf_nblg_wyhjdwh.do", false);
			}
				 tips = "违纪处分 - 学生申诉管理 - 申诉审核";
				 if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
						tips = "违纪处理 - 申诉处理 - 学校处理决定";
					}
				 else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
					 tips = "违纪处理 - 申诉处理 - 委员会处理";
				 }
			 if(go!=null&&go.equalsIgnoreCase("go")) {//点击查询按钮时
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
					sql = "select  (case when mqzt='等待复查' then '#CCCCCC'  else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk,"
						+ " xn,xh,xm,xymc,zymc,bjmc,cfwh,"
						+ "cflbmc,cfyymc,sssj,mqzt from "
						+ tableName
						+ querry
						+ " and slqk='已受理'";
					
				}else if (Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)) {
					sql = "select  (case when jd='未决定' then '#CCCCCC'  else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk,"
						+ " xn,xh,xm,xymc,zymc,bjmc,cfwh,"
						+ "cflbmc,(select b.cfyymc from cfyydmb b where b.cfyydm=view_wjcf_xsssxx.cfyymc) cfyymc,sssj,jd from "
						+ tableName
						+ querry
						+ " and sh='成立'";
				} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
					sql = "select  (case when jd='未决定' then '#CCCCCC'  else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk,"
						+ " xn,xh,xm,xymc,zymc,bjmc,cfwh,"
						+ "cflbmc,cfyymc,sssj,jd from "
						+ tableName
						+ querry;
				}else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
					sql = "select  (case when jd='未决定' then '#CCCCCC'  else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk,"
						+ " xn,xh,xm,xymc,zymc,bjmc,cfwh,"
						+ "cflbmc,cfyymc,sssj,jd from "
						+ tableName
						+ querry;
				} else {
					sql = "select  (case when jd='未决定' then '#CCCCCC'  else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk,"
						+ " xn,xh,xm,xymc,zymc,bjmc,cfwh,"
						+ "cflbmc,cfyymc,sssj,jd from "
						+ tableName
						+ querry;
						//+ " and sh='成立'";
				}
				 cues = "双击一行可以查看详细信息、并进行相关操作;单击表头可以排序"; 
			 }else{//点击链接时
				if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
					sql = "select  (case when mqzt='等待复查' then '#CCCCCC'  else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk, "
						+ "xh,xm,xymc,zymc,bjmc,cfwh,"
						+ "cflbmc,cfyymc,sssj,mqzt from "
						+ tableName
						+ " where slqk='已受理' and mqzt='等待复查' and rownum <=200  order by sssj";
				}else if (Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)) {
					sql = "select  (case when jd='未决定' then '#CCCCCC'  else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk, "
						+ "xh,xm,xymc,zymc,bjmc,cfwh,"
						+ "cflbmc,(select b.cfyymc from cfyydmb b where b.cfyydm=view_wjcf_xsssxx.cfyymc) cfyymc,sssj,jd from "
						+ tableName
						+ " where sh='成立' and jd='未决定' and rownum <=200  order by sssj";
				} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
					sql = "select  (case when jd='未决定' then '#CCCCCC'  else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk, "
						+ "xh,xm,xymc,zymc,bjmc,cfwh,"
						+ "cflbmc,cfyymc,sssj,jd from "
						+ tableName
						+ " where jd='未决定' and rownum <=200  order by sssj";
				} else {
//					sql = "select  (case when jd='未决定' then '#CCCCCC'  else '#FFFFFF' end) bgcolor,xh||cfwh||cfsj pk, "
//						+ "xh,xm,xymc,zymc,bjmc,cfwh,"
//						+ "cflbmc,cfyymc,sssj,jd from "
//						+ tableName
//						+ " where jd='未决定' and rownum <=200  order by sssj";
				}
				 cues = "双击一行可以查看详细信息、并进行相关操作;单击表头可以排序;" ;
		     }  
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				colList = new String[] { "bgcolor", "pk", "xn","xh", "xm", "xymc",
						 "bjmc", "cfwh", "cflbmc", "cfyymc", "sssj", "mqzt" };
			}else{
				colList = new String[] { "bgcolor", "pk","xn", "xh", "xm",
						 "bjmc", "cfwh", "cflbmc", "cfyymc", "sssj", "jd" };
			}
	    }else{
			 return new ActionForward("/errMsg.do",false);
		}
		
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
				
		if (go != null
				&& go.equalsIgnoreCase("go") && "cx".equalsIgnoreCase(act)) {
		     rs.addAll(dao.rsToVator(sql, new String[] {}, colList));		     
		}
		
		if ((act.equalsIgnoreCase("discuss")
				|| act.equalsIgnoreCase("decide")) && StringUtils.isNotNull(sql)) {
			 rs.addAll(dao.rsToVator(sql, new String[] {}, colList));			     
		 }
		
		if (rs == null) {
		     rsNum = "0";
	    } else {
		     rsNum = String.valueOf(rs.size());
	    }
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			request.setAttribute("isSHGC", "yes");
		}
		
		getListxx(request, dao, xy, zy, nj);
		request.setAttribute("disabled", disabled);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("tableName", tableName);
		request.setAttribute("tips",tips);
		request.setAttribute("realTable", realTable);
		request.setAttribute("act", act);
		request.setAttribute("cues", cues);
		return mapping.findForward("success");
   }
   //学生申诉详细信息显示、删除公共模块
	public static ActionForward WjcfViewMoreInfoAndDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	   DAO dao = DAO.getInstance();
	   ActionForward newFwd = new ActionForward();
	   String sql = "";
	   String pkValue = DealString.toGBK(request.getParameter("pkValue"));	  
	   String pk = "xh||cfwh||cfsj";    
	   String tips = "";
	   String[] colList = null;
	   String xxdm = dao.getXxdm(); 
	   
	   if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){//浙江机电职业技术学院
		   request.setAttribute("isZJJDZYJSXY", "ok");
		}
	   HashMap<String,String> map = new HashMap<String,String>();
		String userType = request.getSession().getAttribute("userOnLine")
				.toString();
	   if("student".equalsIgnoreCase(userType)){
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				sql = "select XH, XM, XB, NJ, XYMC, ZYMC, BJMC, SSSJ, SH, JD, CFLBMC, CFYYMC, CFSJ, CFWH, JDWH, "
					+ "JDSJ, SLTZS, FCTZS, SLQK, MQZT from view_wjcf_xsssxx where "
					+ pk
					+ "=?";
			colList = new String[] { "XH", "XM", "XB", "NJ", "XYMC", "ZYMC",
					"BJMC", "SSSJ", "SH", "JD", "CFLBMC", "CFYYMC", "CFSJ",
					"CFWH", "JDWH", "JDSJ", "SLTZS", "FCTZS", "SLQK", "MQZT" };
			request.setAttribute("isSHGC", "yes");
			}/* else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				sql = "select XH, XM, XB, NJ, XYMC, ZYMC, BJMC, SSSJ,FDYSH, SH, JD, CFLBMC, CFYYMC, CFSJ, CFWH, JDWH, "
					+ "JDSJ, TLLY, JDLY,FDYYJ from view_wjcf_xsssxx where "
					+ pk
					+ "=?";
			colList = new String[] { "XH", "XM", "XB", "NJ", "XYMC", "ZYMC",
					"BJMC", "SSSJ","FDYSH", "SH", "JD", "CFLBMC", "CFYYMC", "CFSJ",
					"CFWH", "JDWH", "JDSJ", "TLLY", "JDLY" ,"FDYYJ"};
			}*/else{
				sql = "select XH, XM, XB, NJ, XYMC, ZYMC, BJMC, SSSJ, SH, JD, CFLBMC, CFYYMC,DZ, CFSJ, CFWH, JDWH, "
					+ "JDSJ, TLLY, JDLY,XN,ND,(select xqmc from xqdzb where xqdm=a.xq) XQ,YQ,ggcflbmc from view_wjcf_xsssxx a where "
					+ pk
					+ "=?";
			colList = new String[] { "XH", "XM", "XB", "NJ", "XYMC", "ZYMC",
					"BJMC", "SSSJ", "SH", "JD", "CFLBMC", "CFYYMC", "CFSJ",
					"CFWH", "JDWH", "JDSJ", "TLLY", "JDLY" ,"XN","ND","XQ","DZ", "YQ", "GGCFLBMC"};
			}
			String[] teminfo = dao.getOneRs(sql, new String[] { pkValue },
					colList);
		   if(teminfo != null){
			   for(int i=0;i<colList.length;i++){
               map.put(colList[i].toLowerCase(),teminfo[i]);
			   }
		   }
		   List  rswj = WjcfOperactionAction.GetFileList(pkValue);//发送上传材料证明数据
		   request.setAttribute("rswj", rswj);
		   request.setAttribute("rs", map);
		   /*if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			   return new ActionForward("/wjcf/csmz/wjcf_csmz_stussview.jsp",false);
		   }*/
		   newFwd = new ActionForward("/wjcf/wjcf_stu_shsxx.jsp",false);
	   }else{
		   String doType = request.getParameter("doType");	
		   String act = request.getParameter("act");
		   String realTable = request.getParameter("realTable");	   		  
		   String xh = request.getParameter("xh").trim();
		   String from = request.getParameter("from");	
		   if ((doType == null) || doType.equalsIgnoreCase("")) {
			   // 参数异常
			   newFwd=mapping.findForward("false");
		   } else if (doType.equalsIgnoreCase("view")) {
			   // 查询数据 
			   if(realTable.equalsIgnoreCase("wjcf_xsssb")){
				   sql = "select * from view_xsjbxx where xh = ? ";
					colList = dao
							.getColumnName("select * from view_xsjbxx where 1=2");
					String[] stuInfo = dao.getOneRs(sql, new String[] { xh },
							colList);
				   if(stuInfo != null){
					   for(int i=0;i<colList.length;i++){
						   map.put(colList[i].toLowerCase(), stuInfo[i]);//个人信息
					   }
				   }
				  
				   sql = "select xh,lxdh,dz,yb,sssj,yq,sh,jd,jdwh,jdsj,tlly,jdly,cfwh,xn,nd,(select xqmc from xqdzb where xqdm=a.xq) xq,cflbmc,cfyymc," +
				   		"cfsj,wjsclj,cfxg,bz,slqk,slrq,sltzs,fcrq,csqk,fcqk,fctzs,mqzt,fdysh,fdyyj,ggcflbdm,wyhsl,wyhsllr from wjcf_xsssb a"
				   		+" where "+pk+" = ?";
				   
				   
				   
					colList = dao.getColumnName("select * from " + realTable + " where 1=2");
					String[] wjcfInfo = dao.getOneRs(sql,
							new String[] { pkValue }, colList);
				   if(wjcfInfo != null){
					   for(int i=0;i<colList.length;i++){
						   map.put(colList[i].toLowerCase(), wjcfInfo[i]);//违纪信息
					   }
				   }
				   map.put("cfyy", map.get("cfyymc"));
				   if (Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)) {
					   map.put("cfyymc", dao.getOneRs("select cfyymc from cfyydmb  where cfyydm=?", new String[]{map.get("cfyymc")}, "cfyymc"));
				   } else {
					   map.put("cfyymc", map.get("cfyymc"));
				   }
				   if("cx".equalsIgnoreCase(act)){
					   tips = "学生申诉详细信息";	
					   List  rswj = WjcfOperactionAction.GetFileList(pkValue);//发送上传材料证明数据
					   request.setAttribute("rswj", rswj);				  	    		
					   request.setAttribute("rs", map);
					   request.setAttribute("tips", tips);
						newFwd = new ActionForward(
								"/wjcf/wjcf_shssqmoreinfo.jsp", false);
				   }
			   }
		   } else if(doType.equalsIgnoreCase("del")){
			   //删除数据
				sql = "delete from " + realTable + " where " + pk + " = ?";
			   boolean del = false;
			   try{
				   del = dao.runUpdate(sql, new String[] {pkValue});
			   }catch(Exception e){
				   newFwd=mapping.findForward("false");
			   }
			   if(del){
					dao.writeLog(sql, new String[] {}, new String[] {}, "删除"
							+ realTable + "中" + pk + "=" + pkValue + "的记录:",
							request);
				   request.setAttribute("Done", "ok");
			   } else {
				   request.setAttribute("Done", "no");
			   }
			   if(realTable.equalsIgnoreCase("wjcf_xsssb")){
					newFwd = new ActionForward(
							"/wjcf_shsdatasearch.do?go=go&act=" + from, false);
			   }
		   }	
	   }
	     return newFwd;
   }
   //申诉申请审核 (委员会讨论、委员会决定)
	public static ActionForward WjcfShsCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
	   DAO dao = DAO.getInstance();
	   ActionForward newFwd = new ActionForward();
	   String act = request.getParameter("act");
	   String pkValue = DealString.toGBK(request.getParameter("pkValue"));
	   String doType = request.getParameter("doType");
	   String xh = request.getParameter("xh").trim();
	   String realTable = request.getParameter("realTable");
	   String pk = " xh||cfwh||cfsj ";
	   String xxdm = dao.getXxdm();
	   HashMap<String,String> map = new HashMap<String,String>();
	   String sql = "";
	   String[] colList = null;
	   String pkVal = request.getParameter("pkValue");
	   if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){//浙江机电职业技术学院
		   request.setAttribute("isZJJDZYJSXY", "ok");
		}
	   if("save".equalsIgnoreCase(doType)){
		   boolean res = false;
		   String[] valuetmp = null;
		   if(act == null){
			   newFwd=mapping.findForward("false");  
		   } else if("discuss".equalsIgnoreCase(act)){
			   String sh = DealString.toGBK(request.getParameter("sh")).trim();
			   String tlly = DealString.toGBK(request.getParameter("tlly"));
			   sql = "update wjcf_xsssb set sh=?,tlly=? where xh||cfwh||cfsj=?";
			   valuetmp = new String[]{sh,tlly,pkValue};
			   try{
				   res = dao.runUpdate(sql, valuetmp);	
				   }catch(Exception e){
					 res = false;   
				   }
		   }else if("decide".equalsIgnoreCase(act)){
			   String jd = DealString.toGBK(request.getParameter("jd").trim());
			   String jdwh = DealString.toGBK(request.getParameter("jdwh"));
			   String jdly = DealString.toGBK(request.getParameter("jdly"));
			   String ggcflbdm = request.getParameter("ycflb");
			   String jdsj = request.getParameter("jdsj");
			   sql = "update wjcf_xsssb set jd=?,jdwh=?,jdsj=?,jdly=?,ggcflbdm=? where xh||cfwh||cfsj=?";
			   valuetmp = new String[]{jd,jdwh,jdsj,jdly,ggcflbdm,pkValue};
			   try{
				   res = dao.runUpdate(sql, valuetmp);	   
				   }catch(Exception e){
					 res = false;   
				   }
			   if(res){//委员会决定通过后更改处分表申诉结果
				   
				   String[] sqlArr = new String[3];
				   if(!jdwh.equalsIgnoreCase("")||jdwh!=null){
					   sql = "update wjcfb set cxwh='" + jdwh + "',cxsj='"
								+ jdsj + "',ssjg='" + jd
								+ "' where xh||cfwh||cfsj='" + pkValue + "'";
					   sqlArr[0] = sql;
					   String cflb = request.getParameter("ycflb");
					   
					   String wjsql = "";
					   //决定为更改处分时，修改处分类别，和原处分类别
					   if ("更改处分".equalsIgnoreCase(jd)) {
						   String ycflb = dao.getOneRs("select cflbdm from cflbdmb where cflbmc = (select cflbmc from wjcf_xsssb where xh||cfwh||cfsj=? and rownum <2)", new String[]{pkValue}, "cflbdm");
						   //String ycflb = dao.getOneRs("select cflb from view_wjcf where xh||cfwh||cfsj=? and rownum=1", new String[]{pkValue}, "cflb");
						   //String cflbmc = request.getParameter("cflbmc");
						   wjsql = "update wjcfb set cflb='" + cflb
									+ "',ycflb='" + ycflb
									+ "' where xh||cfwh||cfsj='" + pkValue
									+ "'";
						   sqlArr[1] = wjsql;
						   //sqlArr[2] = "update wjcf_xsssb set cflbmc='"+cflbmc+"' where xh||cfwh||cfsj='" + pkValue + "'";
						   
					   } else if ("解除处分".equalsIgnoreCase(jd)) {
						   sqlArr[1] = "update wjcfb set xxsh='解除处分(处分申诉)' where xh||cfwh||cfsj='" + pkValue + "'";;
					   }
					   
					   //valuetmp = new String[]{jdwh,jdsj,jd,pkValue};
					   try{
						   dao.runBatch(sqlArr);
					   }catch(Exception e){
						   
					   }
				   }
			   }
			   
		   }
		   if(res){
			   request.setAttribute("done","yes");
		   }else{
			   request.setAttribute("done","no");
		   }
	   }
	   
	   
	    sql = "select * from view_xsjbxx where xh = ? ";
		colList = dao.getColumnName("select * from view_xsjbxx where 1=2");
		String[] stuInfo = dao.getOneRs(sql,new String[]{xh},colList);
		if(stuInfo != null){
			for(int i=0;i<colList.length;i++){
				map.put(colList[i].toLowerCase(), stuInfo[i]);//个人信息
			}
		}

		sql = "select a.*,ggcflbdm ycflb from "+realTable+" a where "+pk+" = ?";
		colList = dao
				.getColumnName("select a.*,'' ycflb from " + realTable + " a where 1=2");
		String[] wjcfInfo = dao
				.getOneRs(sql, new String[] { pkValue }, colList);
		if(wjcfInfo != null){
			for(int i=0;i<colList.length;i++){
				map.put(colList[i].toLowerCase(), wjcfInfo[i]);//违纪信息
			}
		}
		map.put("cfyy", map.get("cfyymc"));
		if (Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)) {
			map.put("cfyymc", dao.getOneRs("select cfyymc from cfyydmb where cfyydm=?", new String[]{map.get("cfyymc")}, "cfyymc"));
		}
	   if("discuss".equalsIgnoreCase(act)){	//申诉申请审核讨论	  
		   if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {//转到上海工程
			   return new ActionForward("/shsldefault.do?pkVal="+pkVal,false);
		   }
		   String[] shtmp = {"成立","不成立","未讨论"};
		   ArrayList<HashMap<String, String>> shList = new ArrayList<HashMap<String, String>>();
		   for(int i=0;i<shtmp.length;i++){
			   HashMap<String,String> maps = new HashMap<String,String>();
			   maps.put("sh", shtmp[i].toString());
				shList.add(maps);
				Base.getXnndList();

		   }
		   List  rswj = WjcfOperactionAction.GetFileList(pkValue);//发送上传材料证明数据
		   request.setAttribute("rswj", rswj);	
		   request.setAttribute("shList", shList);   		
		   newFwd = new ActionForward("/wjcf/wjcf_shstl.jsp",false);
	   }else if("decide".equalsIgnoreCase(act)){//申诉申请审核决定
		   if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {//转到上海工程
			   return new ActionForward("/shjddefault.do?pkVal="+pkVal,false);
		   }
		   String[] jdtmp = {"解除处分","更改处分","维持原处分","未决定"};
		   if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			   jdtmp = new String[]{"取消处分","更改处分","维持原处分","未决定"};
		   }
		   ArrayList<HashMap<String,String>> jdList = new ArrayList<HashMap<String,String>>();
		   for(int i=0;i<jdtmp.length;i++){
			   HashMap<String,String> maps = new HashMap<String,String>();
			   maps.put("jd", jdtmp[i].toString());
			   jdList.add(maps);
		   }
		   List  rswj = WjcfOperactionAction.GetFileList(pkValue);//发送上传材料证明数据
		   request.setAttribute("rswj", rswj);	
		   request.setAttribute("jdList", jdList);
		   sql = "select * from cflbdmb";
		   List<HashMap<String, String>> cflbList = dao.getList(sql, new String[] {}, new String[] {
					"cflbdm", "cflbmc" });
		   request.setAttribute("cflbList", cflbList);
		   newFwd = new ActionForward("/wjcf/wjcf_shsjd.jsp",false);
	   }	    
	    
  		request.setAttribute("rs", map);
  		request.setAttribute("pkValue",pkValue);
  		request.setAttribute("xh", xh);
  		request.setAttribute("doType", doType);
  		request.setAttribute("realTable", realTable);
  		request.setAttribute("act",act);
	   return newFwd;   
  
   }
   //获取文件上传列表
   public static List GetFileList(String pkValue){
		DAO dao = DAO.getInstance();
		pkValue = "%"+pkValue+"%";
		String sql = "select length(xh||cfwh||cfsj)len, cfwh,cflbmc,cfyymc,sssj,wjsclj from wjcf_xsssb where xh||cfwh||cfsj like ? and wjsclj is not null";
		List rs = dao.getList(sql, new String[] { pkValue }, new String[] {
				"len", "cfwh", "cflbmc", "cfyymc", "sssj", "wjsclj" });
		return rs;
	}
  
   //获取文件上传列表
   public static List GetFileList1(String pkValue){
		DAO dao = DAO.getInstance();
		pkValue = "%"+pkValue+"%";
		String sql = "select length(xh||cfwh||cfsj)len, cfwh,cflbmc,cfyymc,sssj,wjsclj from wjcf_xsssb where xh||xn||sssj like ? and wjsclj is not null";
		List rs = dao.getList(sql, new String[] { pkValue }, new String[] {
				"len", "cfwh", "cflbmc", "cfyymc", "sssj", "wjsclj" });
		return rs;
	}
   
   //文件下载
	public static ActionForward DownLoadFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		byte b[]= new byte[500];
		String dir = DealString.toGBK(request.getParameter("wjsclj"));
		int len = Integer.parseInt(request.getParameter("len"))+22;
		String filename = dir.substring(len, dir.length());
		File fileload=new File(dir);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(filename));
		InputStream in=new FileInputStream(fileload);
		in = new BufferedInputStream(in);
		while ((in.read(b))!=-1) {
		response.getOutputStream().write(b);
		}
		return null;
	}
	
	/**
	 * 南昌大学科学技术学生考勤旷课预警
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author litao
	 * @time 08-4-3
	 */
	public static ActionForward WjcfStuPreWarn(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String color = request.getParameter("color");
		WjcfForm wjcfForm = (WjcfForm) form;
		String xn = wjcfForm.getXn();
		String xq = wjcfForm.getXq();
		String tableName = "view_kkqkxx";
		String sql = "";
		String tips = null;
		String cs = null;
		String tops = xn + " 学年第 " + xq + " 学期旷课次数在";
		String act=request.getParameter("act");
		StringBuffer query=new StringBuffer();
		if (xn!=null&&xn.length()>0){
			query.append("and xn='");
			query.append(xn);
			query.append("' ");
		}
		if (xq!=null&&xq.length()>0){
			query.append("and xq='");
			query.append(xq);
			query.append("' ");
		}
		if (!"".equalsIgnoreCase(xn) && null != xn && !"".equalsIgnoreCase(xq)
				&& null != xq) {
			if ("orange".equalsIgnoreCase(color) && color != null) {
				sql = "select xh,xm,xb,xn,xq,xymc,zymc,bjmc,sum(to_number(kkjs)) kkjs from "
						+ tableName
						+" where 1=1 "
						+ query
						+ "group by xh,xm,xb,xn,xq,xymc,zymc,bjmc having (sum(to_number(kkjs))>7 and sum(to_number(kkjs))<9)";
				tips = "橙色预警--旷课节数在7-9节";
				cs = "#ffdead";
				tops += "7-9节的学生名单";
			} else if ("red".equalsIgnoreCase(color) && color != null) {
				sql = "select xh,xm,xb,xn,xq,xymc,zymc,bjmc,sum(to_number(kkjs)) kkjs from "
					+ tableName
					+" where 1=1 "
					+ query
					+ "group by xh,xm,xb,xn,xq,xymc,zymc,bjmc having sum(to_number(kkjs))=9";
				tips = "红色预警--旷课节数为9节";
				cs = "#FF9999";
				tops += "9节的学生名单";
			} else if ("blank".equalsIgnoreCase(color) && color != null) {
				sql = "select xh,xm,xb,xn,xq,xymc,zymc,bjmc,sum(to_number(kkjs)) kkjs from "
					+ tableName
					+" where 1=1 "
					+ query
					+ "group by xh,xm,xb,xn,xq,xymc,zymc,bjmc having sum(to_number(kkjs))>=10";
				tips = "黑名单--旷课节数在10节及以上";
				cs = "#CCCCCC";
				tops += "10节及以上的学生名单";
			}
		}
		String[] colList = { "xh", "xm", "xb", "xn", "xq", "xymc", "zymc","bjmc","kkjs" };
		List topTr = WjcfDataAccessAction.getTheadList(colList, tableName);
		int rsNum = 0;
		Vector<Object> vector = new Vector<Object>();		
		if (act!=null&&act.equalsIgnoreCase("go")){
			vector.addAll(dao.rsToVator(sql, new String[]{}, colList));
			if (vector != null && vector.size() > 0) {
				rsNum=vector.size();
			}
		}
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", vector);
		request.setAttribute("tips", tips);
		request.setAttribute("cs", cs);
		request.setAttribute("tops", tops);
		return mapping.findForward("success");
	}

	/**
	 * 上海工程技术大学违纪处分主题班会管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author litao
	 * @time 08-4-8
	 */
	public static ActionForward WjcfZtBhGl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
//		WjcfForm wjcfForm=new WjcfForm();
		ShgcForm shgcForm=(ShgcForm) form;
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String xydms = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(xydms);
		String xydm=request.getParameter("xy");
		String zydm=request.getParameter("zy");
		String bjdm=request.getParameter("bj");
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xh = DealString.toGBK(shgcForm.getXh());
		String xm = DealString.toGBK(shgcForm.getXm());
		Vector<Object> rs = new Vector<Object>();
		String rsNum = null;
		String tips = "违纪处分-主题班会-处分后表现主题班会记录";
		String realName = "wjcfztbhjlb";
		String tableName = "view_wjcfztbhjl";
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		String[] colList = new String[] { "xh","xm","nj","xymc","zymc","bjmc","cxrs","zc","jlr","rq" };
		List topTr = WjcfDataAccessAction.getTheadList(colList, realName); // ///获取表头
		StringBuffer query = new StringBuffer();
		String act = DealString.toGBK(request.getParameter("go"));
		String sql = "";
		query = getQuerry(xydm, zydm, bjdm, nj, "", "");
		if (xh != null && xh.length() > 0) {
			query.append("and xh= '");
			query.append(xh);
			query.append("' ");
		}
		if (xm!=null && xm.length()>0){
			query.append("and xm= '");
			query.append(xm);
			query.append("' ");
		}
		if (act != null && "go".equalsIgnoreCase(act)) {
			sql="select * from "+tableName+" where 1=1 "+query.toString();
			/*sql="select * from (select * from (select rownum r,a.* from "
				+ tableName
				+ " a where 1=1 "
				+ query.toString()
				+ ") where rownum<="
				+ (shgcForm.getPages().getStart() + shgcForm
						.getPages().getPageSize()) + ") where r>"
						+ shgcForm.getPages().getStart();*/
			 // 分页
			/*shgcForm.getPages().setMaxRecord(
					Integer.parseInt(dao.getOneRs("select count(*) count from "
							+ realName + " a where 1=1 " + query.toString(),
							new String[] {}, "count")));*/
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs != null) {
				rsNum = String.valueOf(rs.size());
			} else {
				rsNum = "0";
			}
		}
		if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("setxy", "setxy");
			request.setAttribute("xydm", xydm);
		}
		
		//getListxx(request, dao, xydm, zydm, nj);
		//setListxx(wjcfForm, xydm, zydm, nj);
		appendProperties(request, xydm, zydm, nj, "");
		shgcForm.setXm(xm);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("realTable", realName);
		request.setAttribute("tableName", tableName);
		request.setAttribute("tips", tips);
		request.setAttribute("userType", userType);
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("success");
	}
	
	/**
	 * 上海工程技术大学违纪处分主题班会管理-打印报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author litao
	 * @time 08-4-8
	 */
	public static ActionForward WjcfZtBhGlPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao=DAO.getInstance();	
		String xh=DealString.toGBK(request.getParameter("xh"));
		String rq=DealString.toGBK(request.getParameter("rq"));
		String tableName="view_wjcfztbhjl";
		String sql="select * from "+tableName+" where xh=? and rq=?";
		//String[] colList={"xm","rq","cxrs","zc","cxryqd","hyjl","jlr"};
		Map<String,String> rs=new HashMap<String,String>();
		if (xh!=null&&xh.length()>0){
			rs=dao.getMapNotOut(sql, new String[]{xh,rq});
			request.setAttribute("rs", rs);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 上海工程技术大学违纪处分主题班会管理-信息加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author litao
	 * @time 08-4-8
	 */
	public static ActionForward WjcfZtBhGlLoad(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xh=DealString.toGBK(request.getParameter("xh"));
		String rq=DealString.toGBK(request.getParameter("rq"));
		String doType=DealString.toGBK(request.getParameter("doType"));
		request.setAttribute("xqList", Base.getXqList());
		String sql="select * from view_wjcfztbhjl where xh=? and rq=?";
		if (doType.equalsIgnoreCase("modi")||doType.equalsIgnoreCase("load")){
			DAO dao=DAO.getInstance();
			Map<String,String> rs=new HashMap<String,String>();
			rs=dao.getMapNotOut(sql, new String[]{xh,rq});
			request.setAttribute("rs", rs);
			return new ActionForward("/wjcf/zzsf/wjcf_ztbhgl_view.jsp",false);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 上海工程技术大学违纪处分主题班会管理-信息添加,删除，更改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author litao
	 * @time 08-4-9
	 */
	public static ActionForward WjcfZtBhGlOper(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao=DAO.getInstance();
		ShgcForm shgcForm=(ShgcForm) form;
		String xh=DealString.toGBK(request.getParameter("xh"));
		String xm=DealString.toGBK(request.getParameter("xm"));
		String nj=DealString.toGBK(request.getParameter("nj"));
		String xymc=DealString.toGBK(request.getParameter("xymc"));
		String zymc=DealString.toGBK(request.getParameter("zymc"));
		String bjmc=DealString.toGBK(request.getParameter("bjmc"));
		String zc=DealString.toGBK(request.getParameter("zc"));
		String cxrs=DealString.toGBK(request.getParameter("cxrs"));
		String cxryqd=DealString.toGBK(request.getParameter("cxryqd"));
		String hyjl=DealString.toGBK(request.getParameter("hyjl"));
		String jlr=DealString.toGBK(request.getParameter("jlr"));
		String rq=DealString.toGBK(request.getParameter("rq"));
		String realName = "wjcfztbhjlb";
		String tableName = "view_wjcfztbhjl";
		String xydms = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(xydms);
//		String sql="";
		String xydm = "";
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userDep)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		boolean flag=false;
		String doType=request.getParameter("doType");
		if (!"".equalsIgnoreCase(doType)&&doType.equalsIgnoreCase("save")){
			String[] xzbList=dao.getOneRs("select xydm,zydm,bjdm from view_njxyzybj where xymc=? and zymc=? and bjmc=?", new String[]{xymc,zymc,bjmc}, new String[]{"xydm","zydm","bjdm"});
			String[] input=new String[]{xh,xm,nj,xzbList[0],xzbList[1],xzbList[2],xymc,zymc,bjmc,zc,cxryqd,cxrs,hyjl,jlr,rq};
			//sql="insert into wjcfztbhjlb (xh,xm,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,zc,cxryqd,cxrs,hyjl,jlr,rq) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//flag=dao.runUpdate(sql, input);
			String[] columns = new String[]{"xh", "xm", "nj", "xydm", "zydm", "bjdm", "xymc", "zymc", "bjmc", "zc", "cxryqd", "cxrs", "hyjl", "jlr", "rq"};
			flag = StandardOperation.insert(realName, columns, input, request);
		}else if(!"".equalsIgnoreCase(doType)&&doType.equalsIgnoreCase("modi")){
			//sql="update wjcfztbhjlb set zc=?,jlr=?,cxryqd=?,cxrs=?,hyjl=? where xh=? and rq=?";
			//flag=dao.runUpdate(sql, new String[]{zc,jlr,cxryqd,cxrs,hyjl,xh,rq});
			flag = StandardOperation.update(realName, new String[]{"zc", "jlr", "cxryqd", "cxrs", "hyjl"}, new String[]{zc, jlr, cxryqd, cxrs, hyjl}, "xh||rq", xh+rq, request);
		}else if(!"".equalsIgnoreCase(doType)&&doType.equalsIgnoreCase("del")){
			//sql="delete from wjcfztbhjlb where xh=? and rq=?";
			//flag=dao.runUpdate(sql, new String[]{xh,rq});
			flag = StandardOperation.delete(realName, "xh||rq", xh+rq, request);
			if (flag){
				request.setAttribute("done", "ok");
			}else{
				request.setAttribute("done", "no");
			}
			request.setAttribute("realTable", realName);
			request.setAttribute("tableName", tableName);
			request.setAttribute("userType", userType);
			request.setAttribute("writeAble", "yes");
			appendProperties(request, xydm, "", nj, "");
			return new ActionForward("/wjcf/zzsf/wjcf_ztbhgl.jsp",false);
		}
		if (flag){
			request.setAttribute("done", "ok");
		}else{
			request.setAttribute("done", "no");
		}
		request.setAttribute("realTable", realName);
		request.setAttribute("tableName", tableName);
		request.setAttribute("userType", userType);
		return mapping.findForward("success");
	}
	
	/**
	 * 上海工程技术大学违纪学生学号信息加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author litao
	 * @time 08-4-10
	 */
	public static ActionForward WjcfStuInfoLoad(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		ShgcForm dataSearchForm =(ShgcForm) form;
		String xydms = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(xydms);
		//String userDep = request.getSession().getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			dataSearchForm.setXydm(xydms);
		}
		String realTable = request.getParameter("realtable");
		String xh = DealString.toGBK(dataSearchForm.getXh());
		String nj = DealString.toGBK(dataSearchForm.getNj());
		String bjdm = DealString.toGBK(dataSearchForm.getBjdm());
		String xydm = DealString.toGBK(dataSearchForm.getXydm());
		String zydm = DealString.toGBK(dataSearchForm.getZydm());
		String xm = DealString.toGBK(dataSearchForm.getXm());
		Vector<Object> rs = new Vector<Object>();
		String rsNum = null;
		String realName = "view_wjcf";
		if (!StringUtils.isNull(realTable) && StringUtils.isEqual("kswjcfb", realTable)) {
			realName = "view_kswjcf";
		} else {
			realName = request.getParameter("tableName");
			//realName = "view_wjcf";
			if (StringUtils.isNull(realName)) {
				realName = "view_wjcf";
			}
		}
		String[] colList = new String[] { "pk","xh","xm","nj","nd","xymc","zymc","bjmc","cfyymc" };
		List topTr = WjcfDataAccessAction.getTheadList(colList, realName); // ///获取表头
		StringBuffer query = new StringBuffer(" where 1=1 ");
		String act = DealString.toGBK(request.getParameter("go"));
		String sql = "";
	
		query.append(getQuerry(xydm, zydm, bjdm, nj, "", ""));
		if (xh != null && xh.length() > 0) {
			query.append("and xh= '");
			query.append(xh);
			query.append("' ");
		}
		if (xm != null && xm.length()>0){
			query.append("and xm= '");
			query.append(xm);
			query.append("' ");
		}
		if (act != null && "go".equalsIgnoreCase(act)) {
			sql="select * from (select * from (select xh||xn||nd||sbsj pk,rownum r,a.* from "
				+ realName+" a "
				+ query.toString()
				+ ") where rownum<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + ") where r>"
				+ dataSearchForm.getPages().getStart();
			 // 分页
			dataSearchForm.getPages().setMaxRecord(
					Integer.parseInt(dao.getOneRs("select count(*) count from "
							+ realName + " a " + query.toString(),
							new String[] {}, "count")));
			if (!StringUtils.isNull(realTable) && StringUtils.isEqual("kswjcfb", realTable)) {
				sql="select * from (select * from (select xh||xn||xq||sbsj pk,rownum r,a.* from "
					+ "view_kswjcf" +" a "
					+ query.toString()
					+ ") where rownum<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize()) + ") where r>"
					+ dataSearchForm.getPages().getStart();
				 // 分页
				dataSearchForm.getPages().setMaxRecord(
						Integer.parseInt(dao.getOneRs("select count(*) count from "
								+ realName + " a " + query.toString(),
								new String[] {}, "count")));
			}
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs != null) {
				rsNum = String.valueOf(rs.size());
			} else {
				rsNum = "0";
			}
		}
		if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("setxy", "setxy");
			request.setAttribute("xydm", xydm);
			
		}
		dataSearchForm.setXm(xm);
		dataSearchForm.setXh(xh);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xydm));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("realTable", realName);
		request.setAttribute("tableName", realName);
		request.setAttribute("userType", userType);
		request.setAttribute("act", act);
		return mapping.findForward("success");
	}
	
	/**
	 * 上海工程技术大学违纪处分信息流程图
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author litao
	 * @time 04-4-10
	 */
	public static ActionForward WjcfXxlct(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	
	/**
	 * 南昌大学科学技术学生考勤旷课情况统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author litao
	 * @time 08-4-16
	 */
	public static ActionForward WjcfKkQkTj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao=DAO.getInstance();
		WjcfForm wjcfForm=(WjcfForm) form;
		String act=request.getParameter("go");
		String qssj=request.getParameter("qssj");
		String zzsj=request.getParameter("zzsj");
		String tableName="view_kkqkxx";
		String userType = request.getSession().getAttribute("userType").toString();
		String zydm = DealString.toGBK(request.getParameter("zy"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String bjdm = DealString.toGBK(request.getParameter("bj"));
		String xydm=request.getParameter("xy");
		String xq=request.getParameter("xq");
		String tips="日常事务 - 考勤管理 - 旷课情况统计";
		String sql="";
		StringBuffer isPrint=new StringBuffer();
		int rsNum=0;
		String[] colList = { "xh", "xm", "xb", "xn", "xq", "xymc", "zymc", "bjmc" ,"kkjs"};
		StringBuffer query=new StringBuffer();
		query=getQuerry(xydm, zydm, bjdm, nj, "", xq);
		List topTr = WjcfDataAccessAction.getTheadList(colList, tableName);
		Vector<Object> rs=new Vector<Object>();
		getListxx(request, dao, xydm, zydm, nj);
		setListxx(wjcfForm, xydm, zydm, nj);
		if (act!=null&&act.equalsIgnoreCase("go")){
			isPrint.append(qssj.substring(0,4));
			isPrint.append(" 年 ");
			isPrint.append(qssj.substring(4,6));
			isPrint.append(" 月 ");
			isPrint.append(qssj.substring(6,8));
			isPrint.append(" 日 -- ");
			isPrint.append(zzsj.substring(0,4));
			isPrint.append(" 年 ");
			isPrint.append(zzsj.substring(4,6));
			isPrint.append(" 月 ");
			isPrint.append(zzsj.substring(6,8));
			isPrint.append(" 日旷课学生名单");
			sql="select xh,xm,xb,xn,xq,xymc,zymc,bjmc,sum(to_number(kkjs)) kkjs from "
			+ tableName
			+ " where months_between(to_date('"
			+ qssj
			+ "','yyyymmdd'),to_date(kkrq,'yyyymmdd'))<0 and months_between(to_date('"
			+ zzsj
			+ "','yyyymmdd'),to_date(kkrq,'yyyymmdd'))>0 "
			+ query
			+ "group by xh,xm,xb,xn,xq,xymc,zymc,bjmc";
			rs.addAll(dao.rsToVator(sql, new String[]{}, colList));
			if (rs.size()>0){
				rsNum=rs.size();
			}
		}
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("tableName", tableName);
		request.setAttribute("tips", tips);
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("userType", userType);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("topTr", topTr);
		request.setAttribute("isPrint", isPrint);
		return mapping.findForward("success");
	}
	
	/**
	 * 上海工程技术大学学生行政处分不归档管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author litao
	 * @time 08-4-11
	 */
	public static ActionForward WjcfClBgd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ShgcForm shgcForm= (ShgcForm) form;
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String xh=DealString.toGBK(shgcForm.getXh());
		String xydm=request.getParameter("xy");
		String zydm=request.getParameter("zy");
		String bjdm=request.getParameter("bj");
		String cflb=request.getParameter("cflb");
		String cfyy=request.getParameter("cfyy");
		String xm=DealString.toGBK(shgcForm.getXm());
		String nj=shgcForm.getNj();
		DAO dao=DAO.getInstance();
		String xydms = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(xydms);
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		String tableName="view_wjcfbgd";
		String realTable="wjcfbgd";
		String act=request.getParameter("go");
		String sql="";
		String[] colList={"xh","xm","nj","xymc","zymc","bjmc","zzmm","mz","jg","cfyymc"};
		StringBuffer query=new StringBuffer(" where 1=1 ");
		query.append(getQuerry(xydm, zydm, bjdm, nj, "", ""));
		int rsNum=0;
		List topTr = WjcfDataAccessAction.getTheadList(colList, realTable);
		HashMap<String, String> map = new HashMap<String, String>();
		if (!Base.isNull(cflb))
			map.put("cflb", cflb);
		if (!Base.isNull(cfyy))
			map.put("cfyy", cfyy);
		List cflbList = dao.getList("select cflbdm,cflbmc from cflbdmb", new String[] {}, new String[] {
				"cflbdm", "cflbmc" });
		List cfyyList = dao.getList("select cfyydm,cfyymc from cfyydmb", new String[] {}, new String[] {
				"cfyydm", "cfyymc" });
		if (xh!=null&&xh.length()>0){
			query.append("and xh= '");
			query.append(xh);
			query.append("' ");
		}
		if (cflb!=null&&cflb.length()>0){
			query.append("and cflb= '");
			query.append(cflb);
			query.append("' ");
		}
		if (cfyy!=null&&cfyy.length()>0){
			query.append("and cfyy= '");
			query.append(cfyy);
			query.append("' ");
		}
		if (xm!=null&&xm.length()>0){
			query.append("and xm= '");
			query.append(xm.trim());
			query.append("' ");
		}
		Vector<Object> rs=new Vector<Object>();
		if (act != null && act.equalsIgnoreCase("go")){
			sql="select * from "+tableName+query.toString();
			/*sql="select * from (select * from (select rownum r,a.* from "
				+ tableName+" a "
				+ query.toString()
				+ ") where rownum<="
				+ (shgcForm.getPages().getStart() + shgcForm
						.getPages().getPageSize()) + ") where r>"
				+ shgcForm.getPages().getStart();*/
				/*shgcForm.getPages().setMaxRecord(
					Integer.parseInt(dao.getOneRs("select count(*) count from "
							+ tableName + " a " + query.toString(),
							new String[] {}, "count")));*/
			rs.addAll(dao.rsToVator(sql, new String[]{}, colList));
			if (rs!=null&&rs.size()>0){
				rsNum=rs.size();
			}else
				rsNum=0;
		}
		shgcForm.setXm(xm);
		request.setAttribute("cflbList", cflbList);
		request.setAttribute("cfyyList", cfyyList);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);
		request.setAttribute("rs",map);
		request.setAttribute("rss", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("writeAble", "yes");
		appendProperties(request, xydm, zydm, nj, "");
		return mapping.findForward("success");
	}
	
	/**
	 * 上海工程技术大学学生行政处分不归档页面加载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward WjcfClBgdLoad(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xh=DealString.toGBK(request.getParameter("xh"));
		String doType=DealString.toGBK(request.getParameter("doType"));
		String tz = request.getParameter("tz");
		String pk = request.getParameter("pk");
//		String cflb = request.getParameter("cflb");
//		String tzxh = request.getParameter("tzxh");
//		tzxh = StringUtils.isNull(tzxh) ? "" : tzxh.trim();
		DAO dao=DAO.getInstance();
		if (xh!=null&&xh.length()>0){
			String sql="select a.csrq,a.mzmc,a.jg,a.zzmmmc,b.cflbmc,b.cfyymc from view_xsxxb a,view_wjcf b where a.xh=? and a.xh=b.xh";
			Map<String,String> rss=new HashMap<String,String>();
			rss=dao.getMapNotOut(sql, new String[]{xh});
			request.setAttribute("rss", rss);
			if (StandardOperation.isBys(xh)){
				if (doType.equalsIgnoreCase("modi")){
					sql="select * from view_wjcfbgd where xh=?";
					Map<String,String> rs=new HashMap<String,String>();
					rs=dao.getMapNotOut(sql, new String[]{xh});
					request.setAttribute("rs", rs);
					return new ActionForward("/wjcf/zzsf/wjcf_bgdgl_view.jsp",false);
				}
			}else{
				request.setAttribute("isBgd", "no");
			}
		} 
		if (!StringUtils.isNull(pk)) {
			HashMap<String,String> rs=new HashMap<String,String>();
			String sql="select a.csrq,a.mz,a.jg,a.zzmm,a.zzmmmc,a.mzmc,b.* from view_wjcf b left join view_xsxxb a on a.xh=b.xh where b.xh||b.xn||b.nd||b.sbsj=?";
			Map<String,String> rss=new HashMap<String,String>();
			rss=dao.getMapNotOut(sql, new String[]{pk});
			rs = dao.getMapNotOut(sql, new String[]{pk});
			request.setAttribute("rs", rs);
			request.setAttribute("rss", rss);
			request.setAttribute("tz", tz);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 上海工程技术大学学生行政处分不归档信息增，删，改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward WjcfClBgdOper(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao=DAO.getInstance();
		String tz = request.getParameter("tz");
		ShgcForm shgcForm=(ShgcForm) form;
		String xh=request.getParameter("xh").trim();
		String xm=DealString.toGBK(request.getParameter("xm"));
		String nj=DealString.toGBK(request.getParameter("nj"));
		String xymc=DealString.toGBK(request.getParameter("xymc"));
		String zymc=DealString.toGBK(request.getParameter("zymc"));
		String bjmc=DealString.toGBK(request.getParameter("bjmc"));
		String xb=DealString.toGBK(request.getParameter("xb")).trim();
		String csrq=DealString.toGBK(request.getParameter("csrq"));
		String mz=DealString.toGBK(request.getParameter("mz"));
		String jg=DealString.toGBK(request.getParameter("jg"));
		String cflbmc=DealString.toGBK(request.getParameter("cflbmc"));
		String cfyymc=DealString.toGBK(request.getParameter("cfyymc"));
		String cfhbx=DealString.toGBK(request.getParameter("cfhbx"));
		String jcjl=DealString.toGBK(request.getParameter("jcjl"));
		String bzryj=DealString.toGBK(request.getParameter("bzryj"));
		String xyyj=DealString.toGBK(request.getParameter("xyyj"));
		String kbbmyj=DealString.toGBK(request.getParameter("kbbmyj"));
		String xscyj=DealString.toGBK(request.getParameter("xscyj"));
		String xxyj=DealString.toGBK(request.getParameter("xxyj"));
		String zzmm=DealString.toGBK(request.getParameter("zzmm"));
		String bz=DealString.toGBK(request.getParameter("bz"));
		String act=DealString.toGBK(request.getParameter("doType"));
		String sql="";
		String realName="wjcfbgd";
		String xydms = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(xydms);
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String xydm = "";
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			shgcForm.setXydm(xydm);
		}
		request.setAttribute("tz", tz);
		boolean flag=false;
		if (act.equalsIgnoreCase("save")){
			sql="select a.xydm,a.zydm,a.bjdm,b.cflbdm,c.cfyydm from view_njxyzybj a,cflbdmb b,cfyydmb c where a.xymc=? and a.zymc=? and a.bjmc=? and b.cflbmc=? and c.cfyymc=? and rownum=1";
			String[] xzbccList=dao.getOneRs(sql, new String[]{xymc,zymc,bjmc,cflbmc,cfyymc}, new String[]{"xydm","zydm","bjdm","cflbdm","cfyydm"});
			String[] input={xh,xm,nj,xzbccList[0],xzbccList[1],xzbccList[2],
					xymc,zymc,bjmc,zzmm,xb,csrq,jg,mz,xzbccList[3],xzbccList[4],
					cflbmc,cfyymc,cfhbx.trim(),jcjl,bz,bzryj,xyyj,kbbmyj,xscyj,xxyj};
			/*sql="insert into "+realName
				+ " (xh,xm,nj,xydm,zydm,bjdm,xymc," +
						"zymc,bjmc,zzmm,xb,csrq,jg,mz,cflb,cfyy,cflbmc," +
						"cfyymc,cfhbx,jcjl,bz,bzryj,xyyj,kbbmyj,xscyj,xxyj) " +
						"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			flag=dao.runUpdate(sql, input);*/
			flag = StandardOperation.insert(realName, new String[]{"xh", "xm", "nj", "xydm", "zydm", "bjdm", "xymc", "zymc", "bjmc", "zzmm", "xb", "csrq", "jg", "mz", "cflb", "cfyy", "cflbmc", "cfyymc", "cfhbx", "jcjl", "bz", "bzryj", "xyyj", "kbbmyj", "xscyj", "xxyj"}, input, request);
		}else if(act.equalsIgnoreCase("modi")){
			/*sql="update "+realName+" set cfhbx=?,jcjl=?,bzryj=?,xyyj=?,kbbmyj=?,xscyj=?,xxyj=?,bz=? where xh=?";
			flag=dao.runUpdate(sql, new String[]{cfhbx,jcjl,bzryj,xyyj,kbbmyj,xscyj,xxyj,bz,xh});*/
			flag = StandardOperation.update(realName, new String[]{"cfhbx", "jcjl", "bzryj", "xyyj", "kbbmyj", "xscyj", "xxyj", "bz"}, new String[]{cfhbx,jcjl,bzryj,xyyj,kbbmyj,xscyj,xxyj,bz}, "xh", xh, request);
		}else if(act.equalsIgnoreCase("del")){
			/*sql="delete from wjcfbgd where xh=?";
			flag=dao.runUpdate(sql, new String[]{xh});*/
			flag = StandardOperation.delete(realName, "xh", xh, request);
			if (flag){
				request.setAttribute("done", "ok");
			}else{
				request.setAttribute("done", "no");
			}
			request.setAttribute("realTable", realName);
			request.setAttribute("tableName", "view_wjcfbgd");
			request.setAttribute("userType", userType);
			appendProperties(request, xydm, "", nj, "");
			return new ActionForward("/wjcf_BgdGl.do?go=go",false);
		}
		if (flag){
			request.setAttribute("done", "ok");
		}else{
			request.setAttribute("done", "no");
		}
		request.setAttribute("realTable", realName);
		request.setAttribute("tableName", "view_wjcfbgd");
		request.setAttribute("userType", userType);
		return mapping.findForward("success");
	}
	
	/**
	 * 违纪处分不归档条件筛选
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward WjcfBgdTjSx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		ShgcForm dataSearchForm =(ShgcForm) form;
		String xydms = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(xydms);
		if ("xy".equalsIgnoreCase(userType)) {
			dataSearchForm.setXydm(xydms);
		}
		String xh = DealString.toGBK(dataSearchForm.getXh());
		String nj = DealString.toGBK(dataSearchForm.getNj());
		String bjdm = DealString.toGBK(dataSearchForm.getBjdm());
		String xydm = DealString.toGBK(dataSearchForm.getXydm());
		String zydm = DealString.toGBK(dataSearchForm.getZydm());
		String xm = DealString.toGBK(dataSearchForm.getXm());
		Vector<Object> rs = new Vector<Object>();
		String rsNum = null;
		String realName = "view_wjcf";
		String[] colList = new String[] { "xh","xm","nj","nd","xymc","zymc","bjmc","cfyymc" };
		List topTr = WjcfDataAccessAction.getTheadList(colList, realName); // ///获取表头
		StringBuffer query = new StringBuffer(" where 1=1 ");
		String act = DealString.toGBK(request.getParameter("go"));
		String sql = "";
		query.append(getQuerry(xydm, zydm, bjdm, nj, "", ""));
		if (xh != null && xh.length() > 0) {
			query.append("and xh= '");
			query.append(xh);
			query.append("' ");
		}
		if (xh != null && xm.length() > 0){
			query.append("and xm= '");
			query.append(xm);
			query.append("' ");
		}
		if (act != null && "go".equalsIgnoreCase(act)) {
			sql="select * from (select * from (select rownum r,a.* from "
				+ realName+" a "
				+ query.toString()
				+ ") where rownum<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + ") where r>"
				+ dataSearchForm.getPages().getStart();
			 // 分页
			dataSearchForm.getPages().setMaxRecord(
					Integer.parseInt(dao.getOneRs("select count(*) count from "
							+ realName + " a " + query.toString(),
							new String[] {}, "count")));
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs != null) {
				rsNum = String.valueOf(rs.size());
			} else {
				rsNum = "0";
			}
		}
		if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("setxy", "setxy");
			request.setAttribute("xydm", xydm);
		}
		dataSearchForm.setXm(xm);
		dataSearchForm.setXh(xh);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xydm));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("realTable", realName);
		request.setAttribute("tableName", "");
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("userType", userType);
		return mapping.findForward("success");
	}
	
	/**
	 * 违纪处分不归档打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward WjcfBgdPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xh=DealString.toGBK(request.getParameter("xh"));
		if (xh!=null&&xh.length()>0){
			DAO dao=DAO.getInstance();
			String sql="select * from view_wjcfbgd where xh=?";
			Map<String,String> rs=new HashMap<String,String>();
			rs=dao.getMapNotOut(sql, new String[]{xh});
			request.setAttribute("rs", rs);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 西北二民院学生版个人违纪信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward StuWjcfxxSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xh=DealString.toGBK(request.getSession().getAttribute("userName").toString());
		String userType = request.getSession().getAttribute("userOnLine").toString();
		String xxdm = StandardOperation.getXxdm();
		if (userType.equalsIgnoreCase("student")){
			//保存学生确认数据操作
			DAO dao=DAO.getInstance();
			if ("save".equalsIgnoreCase(request.getParameter("act"))) {
				String pk = request.getParameter("pk");
				//pk = StringUtils.isNotNull(pk) ? pk.replace(" ", "") : "";
				boolean result=false;
				if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
					result = dao.runUpdate("update wjcfb set xsqr = ?,qrsj=(to_char(sysdate,'yyyymmdd')) where xh||xn||nd||sbsj = ?", new String[]{"已确认", pk});
				}else{
					result = dao.runUpdate("update wjcfb set xsqr = ?,qrsj=(to_char(sysdate,'yyyymmdd')) where xh||xn||nd||sbsj = ?", new String[]{"已确认", pk});
				}
				request.setAttribute("result", result);
			}
			
			String tableName="view_wjcf";
			String sql="";
			Vector<Object> rs = new Vector<Object>();
			String[] colList = new String[]{"pk","xn","xq","cflbmc","cfyymc","cfwh","cfsj","xxsh","fjsclj", "bz","xsqr", "qrsj", "dis"};
			if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				colList = new String[]{"pk","xn","xq","cflbmc","cfyymc","cfwh","cfsj","ssjg","cxjg","fjsclj","bz"};
			} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				colList = new String[]{"pk","xn","xq","cflbmc","cfyymc","cfwh","cfsj", "xsqr","qrsj", "dis"};
			} 
			List topTr=WjcfDataAccessAction.getTheadList(colList, tableName); // ///获取表头
			
			String countSql="select xh||xn||nd||sbsj pk,(case when xsqr='已确认' then 'disabled=true' else '' end) dis,xn,(select xqmc from xqdzb where xqdm=a.xq) xq,cflbmc,cfyymc,cfwh,cfsj,xxsh,fjsclj,xsqr,qrsj,(case when fjsclj is null then '无' else '附件下载' end) bz,(case when ssjg is null then '无' else ssjg end) ssjg,(case when cxjg is null then '无' else cxjg end) cxjg from "
				+ tableName
				+ " a where xh = ? and xxsh='通过' and not exists (select 1 from wjcf_cxcfb b where a.xh=b.xh and a.cfwh=b.cfwh and a.cfsj=b.cfsj and b.xxsh='通过')";
			
			if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
				sql="select xh||xn||nd||sbsj pk,(case when xsqr='已确认' then 'disabled=true' else '' end) dis,xn,xq,cflbmc,cfyymc,cfwh,cfsj,fjsclj,xsqr,qrsj,(case when fjsclj is null then '无' else '附件下载' end) bz,(case when ssjg is null then '无' else ssjg end) ssjg,(case when cxjg is null then '无' else cxjg end) cxjg from "
					+ tableName
					+ " where xh = ? ";	
			}else{
				sql="select xh||xn||nd||sbsj pk,(case when xsqr='已确认' then 'disabled=true' else '' end) dis,xn,(select xqmc from xqdzb where xqdm=a.xq) xq,cflbmc,cfyymc,cfwh,cfsj,xxsh,fjsclj,xsqr,qrsj,(case when fjsclj is null then '无' else '附件下载' end) bz,(case when ssjg is null then '无' else ssjg end) ssjg,(case when cxjg is null then '无' else cxjg end) cxjg from "
					+ tableName
					+ " a where xh = ?  and not exists (select 1 from wjcf_cxcfb b where a.xh=b.xh and a.cfwh=b.cfwh and a.cfsj=b.cfsj and b.xxsh='通过')";
			}
			rs.addAll(dao.rsToVator(sql, new String[]{xh}, colList));
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			
			ArrayList<String[]>countRs=dao.rsToVator(countSql, new String[]{xh}, colList);
			request.setAttribute("num", countRs != null ? countRs.size() : 0);//历史处分记录总数
			
			//查询学生解除留校察看信息
			List<String[]> lxckData = dao
					.rsToVator(
							"select xh||cfxn||cfsbsj pk,xn,nd,cflbmc,cfyymc,cfsj,cfwh,jcsj,jcwh,jcjg from view_wjcf_zjlg_lxck where xh=? and xxsh='通过' and jcwh is not null and jcsj is not null",
							new String[] { xh }, new String[] { "pk", "xn", "nd", "cflbmc",
									"cfyymc", "cfsj", "cfwh", "jcsj","jcwh", "jcjg" });
			
			request.setAttribute("rsData", lxckData);
			return new ActionForward("/wjcf/xbemy/grwjcfxxsearch.jsp",false);
		} else {
			request.setAttribute("message", "对不起，本页面仅供学生用户访问!");
			return new ActionForward("/prompt.do",false);
		}
		//return mapping.findForward("success");
	}
	
	/**
	 * 西北二民院学生登陆个人违纪详细信息显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward StuWjcfxxView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pkValue=DealString.toGBK(request.getParameter("pkValue"));
		DAO dao=DAO.getInstance();
		//String cfwh=DealString.toGBK(request.getParameter("cfwh"));
//		if (cfwh!=null&&cfwh.length()>0){
//			cfwh=cfwh.trim();
//		}
//		String cfsj=DealString.toGBK(request.getParameter("cfsj").trim());
//		if (cfsj!=null&&cfsj.length()>0){
//			cfsj=cfsj.trim();
//		}
		HttpSession session=request.getSession();
		String userType=(String)session.getAttribute("userType");
		String xxdm = Base.xxdm;
		request.setAttribute("pkValue", pkValue);
		if ("save".equalsIgnoreCase(request.getParameter("act"))) {
			String pk = request.getParameter("pk");
			//pk = StringUtils.isNotNull(pk) ? pk.replace(" ", "") : "";
			boolean result=false;
			if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
				result = dao.runUpdate("update wjcfb set xsqr = ?,qrsj=(to_char(sysdate,'yyyymmdd')) where xh||xn||nd||sbsj = ?", new String[]{"已确认", pk});
			}else{
				result = dao.runUpdate("update wjcfb set xsqr = ?,qrsj=(to_char(sysdate,'yyyymmdd')) where xh||xn||nd||sbsj = ?", new String[]{"已确认", pk});
			}
			request.setAttribute("result", result);
		}
		if("stu".equalsIgnoreCase(userType)){
			
			StringBuilder sql=new StringBuilder();
			List<String>inputV=new ArrayList<String>();
			inputV.add(pkValue);
			sql.append(" update  wjcfb set sfqs='是'  where xh||xn||nd||sbsj=? ");
			dao.runUpdate(sql.toString(), inputV.toArray(new String[]{}));
		}
		Map<String,String> rs=new HashMap<String,String>();
		if (pkValue!=null){
			String sql="select * from view_wjcf where xh||xn||nd||sbsj=?";
			
			rs=dao.getMapNotOut(sql, new String[]{pkValue});
			if (StringUtils.isNull(rs.get("qrsj"))) {
				rs.put("qrsj", GetTime.getSystemTime().replaceAll("-", ""));
			}
			List<HashMap<String, String>> fjList = dao
					.getList(
							"select cfwh,cfsj,cfyymc,cflbmc,fjsclj from view_wjcf where xh||xn||nd||cfsj=?",
							new String[] { pkValue }, new String[] {"cfwh", "cfsj", "cfyymc", "cflbmc", "fjsclj"});
			if (fjList != null && fjList.size() > 0) {
				if (StringUtils.isNull(fjList.get(0).get("fjsclj"))) {
					fjList = new ArrayList<HashMap<String,String>>();
				}
			}
			request.setAttribute("fjList", fjList);
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("success");
	}
	
	public static ArrayList<HashMap<String, String>> getSearchTitle() throws Exception {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String [] enCol = new String[] { "xh||cfsj||cfwh", "xh", "xm", "xn", "nd", "xq",
				"cflbmc", "cfsj", "cfwh", "cfyymc","ishg" };
		String[] cnCol = { "主键", "学号", "姓名", "学年", "年度", "学期",
				"处分类别", "处分时间","处分文号","处分原因" ,"教育跟踪状态"};
		for (int i = 0; i < enCol.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", enCol[i]);
			map.put("cn", cnCol[i]);
			result.add(map);
			map = null;
		}// end for
		return result;
	}
	
	/**
	 * 未通过申报信息批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*public static ActionForward deletewtgwjxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfForm wjcfForm = (WjcfForm) form;
		String[] cbv = wjcfForm.getCbv();
		String res = delxxshInfo(cbv);
		String result = "view";
		if (!StringUtils.isNull(res)) {
			request.setAttribute("result", result);
		}else {
			result+=res;
			request.setAttribute("result", result);
		}//end if
		return new ActionForward("/not_declare_info.do?go=go&result="+result,false);
	}
	*/
	/*public static String delxxshInfo(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from wjcfb where xh||sbsj = '" + whichxh
					+ "'";
			// 把主键组合成sql语句
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = pksql1.toString().split("!!#!!");
		DAO dao = DAO.getInstance();
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}*/
	
	public static void appendProperties(HttpServletRequest request, String xydm, String zydm, String nj, String xq) throws Exception {
		String xy = "";
		String zy = "";
//		String xqLocal = xq;
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm; 
		njLocal = nj==null ? "": nj;
//		xqLocal = xq==null ? "": xq;
		xy = StringUtils.isNull(xy) ? "" :xy;
		zy = StringUtils.isNull(zy) ? "" : zy;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();
		request.setAttribute("userType", userType);
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
	}
	
//	个人违纪处分信息显示（留校查看）
	public static ActionForward WjcfInfoLxck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
//		WjcfForm wjcfForm = (WjcfForm) form;
		Vector<Object> rs = new Vector<Object>();
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String sql = "";
		String tabName = "view_wjcf";
		String[] colList = null;
		String[] colListCN = null;
//		String tabType = request.getParameter("tabType");
		String tips = "违纪处分 - 学生申诉申请 - 申请 - 个人违纪处分信息";
//		String xxdm = dao.getXxdm();
		colList = new String[] { "CFID", "CFWH", "ND", "XN", "XQ", "CFLBMC",
				"CFYYMC", "CFSJ" };
		colListCN = dao.getColumnNameCN(colList, tabName);
		List topTr = dao.arrayToList(colList,colListCN);
		sql = "select (xh||cfwh||cfsj) cfid,cfwh,nd,xn,xq,cflbmc,cfyymc,cfsj from "
				+ tabName + " where xh = ? and cflbmc='留校察看'";
		rs.addAll(dao.rsToVator(sql, new String[]{xh}, colList));
		String rsNum="";
		if(rs == null){
			rsNum = "0";
		}else{
			rsNum = String.valueOf(rs.size());
		}
		String param = request.getParameter("param");
		System.out.println(param);
		request.setAttribute("xm", xm);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("tips", tips);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("param1", param);
		return mapping.findForward("success");
	}
	
	/**
	 * 违纪处分未通过信息批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public static ActionForward wjcfWtgxxDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		WjcfForm wjcfForm = (WjcfForm) form;
		String[] keys = wjcfForm.getCbv();
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from wjcfb where xh||sbsj = '" + whichxh
					+ "'";
			// 把主键组合成sql语句
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		if (StringUtils.isNull(whichpk)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", whichpk);
		}
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		request.setAttribute("userType", userType);
		String tips = "违纪处分 - "+Base.YXPZXY_KEY+"申报 - 未通过申报信息";
		request.setAttribute("tips", tips);
		request.setAttribute("writeAble", "yes");
		getListxx(request, dao, "", "", "");
		return mapping.findForward("success");
	}
	
	/**
	 * 中国矿业大学留校察看提醒功能
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static ActionForward wjcfLxckTj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String nd = Base.currNd;
		//String sql = "";
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[] { "pk","color", "xn",
				"xm", "bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh", "bz" ,"sq"};
		String[] cnList = new String[] { "pk", "color", "学年",
				"姓名", "班级", "处分类别", "处分原因", "处分时间", "处分文号", "说明" ,"解除操作"};
		topTr = dao.arrayToList(enList, cnList);
		List<String[]> rs = new ArrayList<String[]>();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String uesrDep = session.getAttribute("userDep").toString();
		String xydm = "";
		
		String xxdm = Base.xxdm;
		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/wjcf_zjlg_lxckcx.do", false);
		}
		
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = uesrDep;
			xydm = " and a.xydm='" + xydm + "'";
			
		} 
		rs = dao
				.rsToVator(
						"select xh||xn||nd||sbsj pk,'#FFFFFF' color,rownum,xn,xq,xh,xm,bjmc,cflbmc,cfyymc,cfsj,cfwh,('处分已满一年') " +
						"bz from view_wjcf a where cflbmc like '%留校%' and cfwh is not null and " +
						"(months_between(add_months(to_date(cfsj, 'yyyymmdd'), 12), to_date" +
						"(to_char(sysdate, 'yyyymmdd'), 'yyyymmdd')) <= 0) and not exists(select " +
						"1 from wjcf_cxcfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and " +
						"a.cfsj=b.cfsj and a.cfwh=b.cfwh and a.cflbmc=b.cflbmc and a.cfyymc=b.cfyymc) " + xydm,
						new String[] {}, new String[] { "pk","color", "xn",
								"xm", "bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh", "bz"});//处分已满一年但是未进行撤消处分申请操作的数据
		List<String[]> rs1 = new ArrayList<String[]>();
		rs1 = dao.rsToVator("select xh||xn||nd||sbsj pk,'#DDDDDD' color,rownum,xn,xq,xh,xm,bjmc,cflbmc,cfyymc,cfsj,cfwh,('处分将满一年') " +
				"bz from view_wjcf a where cflbmc like '%留校%' and cfwh is not null and (months_between" +
				"(add_months(to_date(cfsj, 'yyyymmdd'), 12), to_date(to_char(sysdate, 'yyyymmdd')," +
				"'yyyymmdd')) <= 1) and (months_between(add_months(to_date(cfsj, 'yyyymmdd'), 12)," +
				" to_date(to_char(sysdate, 'yyyymmdd'),'yyyymmdd')) > 0) and not exists(select " +
				"1 from wjcf_cxcfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.cfsj=b.cfsj " +
				"and a.cfwh=b.cfwh and a.cflbmc=b.cflbmc and b.cfyymc=a.cfyymc)" + xydm, new String[]{}, new String[] { "pk","color", "xn",
						"xm", "bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh", "bz"});//还有1-30天满一年
		List<String[]> bysList = new ArrayList<String[]>();
		bysList = dao.rsToVator("select xh||xn||nd||sbsj pk,'#BBBBBB' color,rownum,xn,xq,xh,xm,bjmc,cflbmc,cfyymc,cfsj,cfwh,('毕业班学生') " +
				"bz from view_wjcf a where cflbmc like '%留校%' and cfwh is not null and xh in " +
				"(select distinct xh from view_xsjbxx where to_number(trim(nj))+to_number(trim(xz)) " +
				"='"+nd+"') and not exists (select 1 from wjcf_cxcfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.cfsj=b.cfsj " +
						"and a.cfwh=b.cfwh and a.cflbmc=b.cflbmc and b.cfyymc=a.cfyymc)" + xydm, new String[]{}, new String[] { "pk","color", "xn",
						"xm", "bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh", "bz"});//还有1-30天满一年
		rs.addAll(rs1);
		rs.addAll(bysList);
		//TODO 明天接着做
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		return mapping.findForward("success");
	}
	
	public static ActionForward downloadfileWj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		byte b[] = new byte[500];
		String dir = DealString.toGBK(request.getParameter("wjsclj"));
		//dir = dir.substring(dir.lastIndexOf("/") + 1, dir.length());
		String filename = dir.substring(dir.lastIndexOf("/")+1, dir.length());
		//String filename = dir.substring(dir.lastIndexOf("/")+1+14, dir.length());
		File fileload = new File(dir);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(filename));
		InputStream in = new FileInputStream(fileload);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}
	
	/**
	 * 在项目中载下文件/xlsDown
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static ActionForward downloadfileTemplate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		byte b[] = new byte[500];
		String dir = URLDecoder.decode(request.getRealPath("/") + "/xlsDown/" +(request.getParameter("filename")), "gbk");
		//String dir = request.getRealPath("/") + "/xlsDown/" +DealString.toGBK(request.getParameter("filename"));
		//dir = dir.substring(dir.lastIndexOf("/") + 1, dir.length());
		String filename = DealString.toGBK(request.getParameter("filename"));
		//String filename = dir.substring(dir.lastIndexOf("/")+1+14, dir.length());
		File fileload = new File(dir);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(filename));
		InputStream in = new FileInputStream(fileload);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}
	
	public static ActionForward wjcfKfTj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, Exception{
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String sheetTitle = "";
		
		StringBuffer sb = new StringBuffer("where 1=1 ");
		if(xn != null && !"".equalsIgnoreCase(xn)){
			sb.append(" and xn='");
			sb.append(xn);
			sb.append("'");
			sheetTitle += xn + "学年";
		}
		if(xq != null && !"".equalsIgnoreCase(xq)){
			sb.append(" and xq='");
			sb.append(xq);
			sb.append("'");
			sheetTitle += xq + "学期";
		}
		
		String sql = "select xh,xn,xq,max(xm)xm,max(xb)xb,max(xydm)xydm,max(xymc)xymc,max(zydm)zydm,max(zymc)zymc,max(bjdm)bjdm,max(bjmc)bjmc,max(nj)nj,decode(sum(kf),'','0',sum(kf))扣分总数 from view_wjcf "+sb.toString()+" and xxsh='通过' group by xh,xn,xq";
		sheetTitle += "违纪扣分统计";
		
		if((xn == null || "".equalsIgnoreCase(xn)) && (xq == null || "".equalsIgnoreCase(xq))){
			sql = "select xh,max(xm)xm,max(xb)xb,max(xydm)xydm,max(xymc)xymc,max(zydm)zydm,max(zymc)zymc,max(bjdm)bjdm,max(bjmc)bjmc,max(nj)nj,decode(sum(kf),'','0',sum(kf))扣分总数 from view_wjcf "+sb.toString()+" and xxsh='通过' group by xh";
			sheetTitle = "学生违纪扣分统计";
		}
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(sql, "view_wjcf", response.getOutputStream(),sheetTitle);//将数据写到Excel文件
		
		return null;
	}


	public static ActionForward wjcfLxck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xh=request.getSession().getAttribute("userName").toString();
		String userType = request.getSession().getAttribute("userOnLine").toString();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		if (userType.equalsIgnoreCase("student")){
			//保存学生确认数据操作
			DAO dao=DAO.getInstance();
			
			
			if (!Base.isNull(doType) && "save".equals(doType)) {
				StringBuilder sb = new StringBuilder();
				sb.append("insert into wjcf_zjlg_lxckb(xh,xn,nd,cfxn,cfnd,cflb,cfyy,cfsbsj,tqjcly,jcsj)");
				sb.append("select xh,(select dqxn from xtszb),(select dqnd from xtszb),xn,nd,cflb,cfyy,sbsj,");
				sb.append("'留校查看到期',CXCLSJ from view_wjcf where xh||xn||nd||cfsj = ?");
				
				request.setAttribute("result", dao.runUpdate(sb.toString(), new String[] {pkValue}));
			}
			
			String tableName="view_wjcf";
			Vector<Object> rs = new Vector<Object>();
			String[] colList = new String[] { "pk", "xn", "xq", "cflbmc",
					"cfyymc", "cfwh", "cfsj", "fjsclj", "bz", "xsqr", "qrsj" };
			
			List topTr=WjcfDataAccessAction.getTheadList(colList, tableName); // ///获取表头
			
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("select xh||xn||nd||cfsj pk,xn,xq,cflbmc,cfyymc,cfwh,");
			sql.append("cfsj,fjsclj,xsqr,qrsj,(case when fjsclj is null then '无'");
			sql.append(" else '附件下载' end) bz,(case when ssjg is null then '无'");
			sql.append(" else ssjg end) ssjg,(case when cxjg is null then '无'");
			sql.append(" else cxjg end) cxjg from ");
			sql.append(tableName);
			sql.append(" where xh = ? and cflb='04'");
			
			rs.addAll(dao.rsToVator(sql.toString(), new String[]{xh}, colList));
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			request.setAttribute("num", rs != null ? rs.size() : 0);//历史处分记录总数
			
			return new ActionForward("/wjcf/xbemy/wjcfLxck.jsp",false);
		}
		
		
		return mapping.findForward("success");
	}
}

