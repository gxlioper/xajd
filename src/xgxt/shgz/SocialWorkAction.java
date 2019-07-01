
package xgxt.shgz;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
import xgxt.DAO.SxjyDAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.shgz.form.StglForm;
import xgxt.utils.CheckPower;
import xgxt.utils.SearchUtils;

public class SocialWorkAction extends DispatchAction{
	SxjyDAO dao = new SxjyDAO();
	String[] colList = null;
	String[] colListCN = null;
	String sql =null;
	String writeAble = null;
	
	public ActionForward zhsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String act = DealString.toGBK(request.getParameter("act"));
		String xh="";
		HashMap<String, String> map = new HashMap<String, String>();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		HttpSession session = request.getSession();
		String userName = request.getSession().getAttribute("userName").toString();
		String pk = "";
		StglForm sxjyForm = (StglForm) form;
		String zgh = DealString.toGBK(sxjyForm.getZgh());
		if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
			userDep="";
		}
		pk = DealString.toGBK(request.getParameter("pk"));
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
			writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stgl.do?method=zhsq")?"yes":"no";
			request.setAttribute("ddxg", "yes");
		}else{
		xh = request.getParameter("xh");
		xh = (xh==null) ? "" : xh;
		}
		// ---------- 2010/5/4 luojw begin -----------
		String doType = DealString.toGBK(request.getParameter("doType"));
		String stdm = request.getParameter("stdm");
		act = Base.isNull(act) && !Base.isNull(doType) ? doType : act;
		if (!Base.isNull(stdm)) {
			map.put("stdm", stdm);
			pk = stdm;
		}
		
		//获得申请信息
		String tableName = "view_stsqdj";
		String[] colList = new String[] { "stdm", "stmc", "yhm", "clsj",
				"zsn1st", "zsm1st", "zsn2st", "zsm2st", "zsn3st", "zsm3st",
				"xh", "lxfs", "grjl", "lbdm", "lbmc", "hdnr", "zgh", "xshyj",
				"ytwyj", "shzt", "xymc", "lsxm", "zzmmmc", "xm", "xb", "bmmc",
				"bjmc", "zwmc", "zydm", "zymc" };
		map = dao.sxjyQueryOne(tableName, colList, "stdm", pk);

		//获得负责人信息
		xh = Base.isNull(xh) ? map.get("xh") : xh;
		colList = new String[] { "xh", "xm", "xymc", "zydm", "zymc", "bjmc" };
		map = dao.sxjyQueryOne3("view_xsjbxx", colList, "xh", xh, map, "");
		colList = new String[] { "zzmmmc" };
		map = dao.sxjyQueryOne3("view_stu_details", colList, "xh", xh, map, "");
		
		// 获得指导老师信息
		zgh = Base.isNull(zgh) ? map.get("zgh") : zgh;
		sql = "select xm lsxm,xb,zwmc from view_fdyxx where zgh = ?";
		colList = new String[] { "lsxm", "xb", "zwmc" };
		map = dao.sxjyQueryOne3("view_fdyxx", colList, "zgh", zgh, map, sql);
		map.put("zgh", zgh);

		// -------------- end -----------
		request.setAttribute("doType", act);
		request.setAttribute("pkValue", pk);
		request.setAttribute("stlbList", dao.getAsnnSortList());
		request.setAttribute("szdwList", dao.getTutorshippersonList(userDep));
		request.setAttribute("rs", map);
		request.setAttribute("shztList", dao.getChkList(3));
		request.setAttribute("userType", userType);
		return mapping.findForward("stsq");
}
	public ActionForward saveStSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	String stmc = DealString.toGBK(request.getParameter("stmc"));
	String clsj = DealString.toGBK(request.getParameter("clsj"));
	String xh = DealString.toGBK(request.getParameter("xh"));
	String lxfs = DealString.toGBK(request.getParameter("lxfs"));
	String grjl = DealString.toGBK(request.getParameter("grjl"));
	String zgh = DealString.toGBK(request.getParameter("zgh"));
	String lbdm = DealString.toGBK(request.getParameter("lbdm"));
	String zsn1st = DealString.toGBK(request.getParameter("zsn1st"));
	String zsm1st = DealString.toGBK(request.getParameter("zsm1st"));
	String zsn2st = DealString.toGBK(request.getParameter("zsn2st"));
	String zsm2st = DealString.toGBK(request.getParameter("zsm2st"));
	String zsn3st = DealString.toGBK(request.getParameter("zsn3st"));
	String zsm3st = DealString.toGBK(request.getParameter("zsm3st"));
	String shzt = DealString.toGBK(request.getParameter("shzt"));
	// ---------- 2010/5/5 luojw begin -----------
	shzt = Base.isNull(shzt) ? "未审核" : shzt;
	// -------------- end -----------
	String yhm = DealString.toGBK(request.getParameter("yhm"));
	String xshyj = DealString.toGBK(request.getParameter("xshyj"));
	String ytwyj = DealString.toGBK(request.getParameter("ytwyj"));
	String doType = DealString.toGBK(request.getParameter("doType"));
	String hdnr = DealString.toGBK(request.getParameter("hdnr"));
	String tableName="";
	String stdm = DealString.toGBK(request.getParameter("pkValue"));
	boolean inserted = false;
	if(doType.equalsIgnoreCase("sh")){
		inserted = StandardOperation.delete("stsqdjb", "stdm", stdm, request);
		request.setAttribute("act", "sh");
	}else{
	String [] tmp = dao.sxjyQueryOne2("stdmb",new String []{"stdm"},"stmc",stmc);
	if(tmp!=null){
		request.setAttribute("errMessage", "该社团名称已被申请或使用");
		request.setAttribute("msg", "该社团名称已被申请或使用");
		request.setAttribute("inserted", "no");
	}else{
	tableName = "stdmb";
	String sql = "select max(to_number(stdm))+1 stdm from stdmb";
	String[] stdmTmp  = dao.getOneRs(sql, new String []{ }, new String []{"stdm"});
	if(stdmTmp!=null){
		stdm=stdmTmp[0];
	}else{
		stdm="001";
	}
	inserted = StandardOperation.insert(tableName, new String []{"stdm","stmc"}, new String []{stdm,stmc}, request);
	}
	}
	if(inserted){
		tableName = "stsqdjb";
		colList = new String []{"stdm","clsj","fzrxh","lxfs","grjl","zdzgh","lbdm","zsn1st","zsm1st","zsn2st","zsm2st","zsn3st","zsm3st","xshyj","ytwyj","shzt","hdnr"};
		String  [] inputList = new String []{stdm,clsj,xh,lxfs,grjl,zgh,lbdm,zsn1st,zsm1st,zsn2st,zsm2st,zsn3st,zsm3st,xshyj,ytwyj,shzt,hdnr};
		inserted = StandardOperation.insert(tableName, colList, inputList, request);
	}
	if(shzt.equalsIgnoreCase("通过")){
		StandardOperation.update("stdmb", new String []{"yhm"}, new String []{ yhm }, "stdm", stdm, request);
	}
	if(inserted){
		request.setAttribute("inserted", "ok");
	}else{
		request.setAttribute("inserted", "no");
		request.setAttribute("errMessage", "发生错误");
	}
	return mapping.findForward("stsq");
	}
	
	public ActionForward stsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		SxjyDAO dao = new SxjyDAO();
		HttpSession session = request.getSession();
		String userType =  session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stgl.do?method=stsh")?"yes":"no";
		String query = "";
		StglForm sxjyForm = (StglForm) form;
		String tableName = "view_stsqdj";
		String xy = sxjyForm.getXydm();
		xy = (xy==null) ? "" : xy;
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			sxjyForm.setXydm(xy);
		}
		query = SearchUtils.makeQueryCondition(xy,"","","","","","","","","");
		colList = new String[] { "stdm","stmc","clsj","xymc","lsxm","xm","shzt"};
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = dao.sxjyQuery(tableName,query,new String []{},colList,"");
		}
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("xydm", xy);
		request.setAttribute("tableName", tableName);
		return mapping.findForward("stsh");
	}
	
	public ActionForward hdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String act = DealString.toGBK(request.getParameter("act"));
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String tableName ="view_gdnz_sthd";
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String stdm = DealString.toGBK(request.getParameter("stdm"));
		String pk = DealString.toGBK(request.getParameter("pk"));
//		StglForm myForm = (StglForm) form;
		String xn = Base.currXn;
		String nd = Base.currNd;
		String xq = Base.currXq;
		if(!userType.equalsIgnoreCase("admin")&&!userType.equalsIgnoreCase("xx")){
			tableName = "stdmb";
			rs = dao.sxjyQuery(tableName, " where yhm = ? ",new String []{userName} , new String []{"stdm"}, "");
			if(rs!=null&&rs.size()!=0){
				stdm = rs.get(0)[0];
				request.setAttribute("sfstzh", "on");
			}else{
				request.setAttribute("errMessage", "只有社团帐户或着管理员或学校帐户才能登入");
				return mapping.findForward("sthdsq");
			}
		}
		if(act.equalsIgnoreCase("sh")){
			tableName = "view_gdnz_sthd";
			String[] colList = new String[] { "pk","yhm","lxfs","shzt","stmc","lbmc","xymc","bjmc","xm","lsxm","stdm","hdmc","hdsj","hdnr","hdjfys","ddlsyj","xtzjyj","lrsj","cjrs","shzt","hddd","xn","nd","xqdm"};
			map = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		}else{
			tableName = "view_stsqdj";
			String[] colList = new String[] { "stdm","stmc","clsj","xh","lxfs","lbdm","lbmc","zgh","xymc","lsxm","zzmmmc","xm","xb","bmmc","bjmc","zwmc","zydm","zymc"};
			map = dao.sxjyQueryOne3(tableName, colList, "stdm", stdm,map,"");
			map.put("xn", xn);
			map.put("nd",nd);
			map.put("xqdm",xq);
		}
		request.setAttribute("doType", act);
		request.setAttribute("stList", dao.getComboList());
		request.setAttribute("pkValue", pk);
		request.setAttribute("rs", map);
		request.setAttribute("shztList", dao.getChkList(3));
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("userType", userType);
		return mapping.findForward("sthdsq");
}
	public ActionForward saveHdSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	String stdm = DealString.toGBK(request.getParameter("stdm"));
	String hdsj = DealString.toGBK(request.getParameter("hdsj"));
	String hdmc = DealString.toGBK(request.getParameter("hdmc"));
	String hddd = DealString.toGBK(request.getParameter("hddd"));
	String cjrs = DealString.toGBK(request.getParameter("cjrs"));
	String hdnr = DealString.toGBK(request.getParameter("hdnr"));
	String hdjfys = DealString.toGBK(request.getParameter("hdjfys"));
	String shzt = DealString.toGBK(request.getParameter("shzt"));
	// ---------- 2010/5/5 luojw begin -----------
	shzt = Base.isNull(shzt) ? "未审核" : shzt;
	// ------end------
	String ddlsyj = DealString.toGBK(request.getParameter("ddlsyj"));
	String xtzjyj = DealString.toGBK(request.getParameter("xtzjyj"));
	String doType = DealString.toGBK(request.getParameter("doType"));
	String xqdm = DealString.toGBK(request.getParameter("xqdm"));
	String nd = DealString.toGBK(request.getParameter("nd"));
	String xn = DealString.toGBK(request.getParameter("xn"));
	String tableName="gdnz_sthdsqb";
	String pk = DealString.toGBK(request.getParameter("pkValue"));
	boolean inserted = true;
	if(doType.equalsIgnoreCase("sh")){
		request.setAttribute("act", "sh");
		inserted = StandardOperation.delete(tableName, "stdm||hdmc||hdsj", pk, request);
	}
	if(inserted){
	colList = new String []{"stdm","hdsj","hdmc","hddd","cjrs","hdnr","hdjfys","shzt","ddlsyj","xtzjyj","xn","nd","xqdm"};
	String  [] inputList = new String []{stdm,hdsj,hdmc,hddd,cjrs,hdnr,hdjfys,shzt,ddlsyj,xtzjyj,xn,nd,xqdm};
	inserted = StandardOperation.insert(tableName, colList, inputList, request);
	}
	if(inserted){
		request.setAttribute("inserted", "ok");
	}else{
		request.setAttribute("inserted", "no");
		request.setAttribute("errMessage", "发生错误");
	}
	return mapping.findForward("sthdsq");
	}
	
	public ActionForward hdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		SxjyDAO dao = new SxjyDAO();
		HttpSession session = request.getSession();
		String userType =  session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stgl.do?method=hdsh")?"yes":"no";
		String query = "";
		StglForm sxjyForm = (StglForm) form;
		String tableName = "view_gdnz_sthd";
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String xqdm = request.getParameter("xqdm");
		String xy = sxjyForm.getXydm();
		xy = (xy==null) ? "" : xy;
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			sxjyForm.setXydm(xy);
		}
		query = SearchUtils.makeQueryCondition(xy,"","","","","","",nd,"",xn);
		if(xqdm!= null && !("".equalsIgnoreCase(xqdm.trim()))){
			query+=" and xqdm='";
			query+=xqdm;
			query+="' ";
		}
		colList = new String[] { "pk","stmc","hdmc","hdsj","xm","shzt"};
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = dao.sxjyQuery(tableName,query,new String []{},colList,"");
		}
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("xydm", xy);
		request.setAttribute("tableName", tableName);
		return mapping.findForward("sthdsh");
	}
	
	public ActionForward hdjfsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String act = DealString.toGBK(request.getParameter("act"));
		HashMap<String, String> map = new HashMap<String, String>();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String tableName ="view_gdnz_sthdjf";
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String stdm = DealString.toGBK(request.getParameter("stdm"));
		String pk = DealString.toGBK(request.getParameter("pk"));
//		StglForm myForm = (StglForm) form;
		if(!userType.equalsIgnoreCase("admin")&&!userType.equalsIgnoreCase("xx")){
			tableName = "stdmb";
			rs = dao.sxjyQuery(tableName, " where yhm = ? ",new String []{userName} , new String []{"stdm"}, "");
			if(rs!=null&&rs.size()!=0){
				stdm = rs.get(0)[0];
				request.setAttribute("sfstzh", "on");
			}else{
				request.setAttribute("errMessage", "只有社团帐户或着管理员或学校帐户才能登入");
				return mapping.findForward("sthdjfsq");
			}
		}
		if(act.equalsIgnoreCase("sh")){
			String[] colList = new String[] { "pk","hdmc","xn","xqdm","xqmc","nd","stdm","lxfs","stmc","xm","xymc","jfyt1","je1","jfyt2","je2","jfyt3","je3","jfyt4","je4","jfyt5","je5","jfyt6","je6","lrsj","shzt","yfje","jsr","bxqk","ytwyj","stdm"};
			map = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		}else{
			tableName = "view_gdnz_sthd";
			String[] colList = new String[] { "pk","xn","xqdm","xqmc","nd","stdm","stmc","hdmc","xymc","lsxm","xm","lxfs"};
			map = dao.sxjyQueryOne3(tableName, colList, "pk", pk,map,"");
			map.put("stdm",stdm);
		}
		request.setAttribute("doType", act);
		request.setAttribute("stList", dao.getComboList());
		request.setAttribute("sthdList", dao.getComboPloyList(stdm));
		request.setAttribute("pkValue", pk);
		request.setAttribute("rs", map);
		request.setAttribute("shztList", dao.getChkList(3));
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("userType", userType);
		return mapping.findForward("sthdjfsq");
}
	public ActionForward saveHdJfSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	String pk = DealString.toGBK(request.getParameter("pk"));
	String jfyt1 = DealString.toGBK(request.getParameter("jfyt1"));
	String je1 = DealString.toGBK(request.getParameter("je1"));
	String jfyt2 = DealString.toGBK(request.getParameter("jfyt2"));
	String je2 = DealString.toGBK(request.getParameter("je2"));
	String jfyt3 = DealString.toGBK(request.getParameter("jfyt3"));
	String je3 = DealString.toGBK(request.getParameter("je3"));
	String jfyt4 = DealString.toGBK(request.getParameter("jfyt4"));
	String je4 = DealString.toGBK(request.getParameter("je4"));
	String jfyt5 = DealString.toGBK(request.getParameter("jfyt5"));
	String je5 = DealString.toGBK(request.getParameter("je5"));
	String jfyt6 = DealString.toGBK(request.getParameter("jfyt6"));
	String je6 = DealString.toGBK(request.getParameter("je6"));
	String yfje = DealString.toGBK(request.getParameter("yfje"));
	String jsr = DealString.toGBK(request.getParameter("jsr"));
	String bxqk = DealString.toGBK(request.getParameter("bxqk"));
	String ytwyj = DealString.toGBK(request.getParameter("ytwyj"));
	String shzt = DealString.toGBK(request.getParameter("shzt"));
	// ---------- 2010/5/5 luojw begin -----------
	shzt = Base.isNull(shzt) ? "未审核" : shzt;
	// ------end------
	String doType = DealString.toGBK(request.getParameter("doType"));
	String tableName="gdnz_sthdjfsqb";
	boolean inserted = true;
	if(doType.equalsIgnoreCase("sh")){
		request.setAttribute("act", "sh");
		inserted = StandardOperation.delete(tableName, "pk", pk, request);
	}
	if(inserted){
	colList = new String []{"pk","jfyt1","je1","jfyt2","je2","jfyt3","je3","jfyt4","je4","jfyt5","je5","jfyt6","je6","shzt","yfje","jsr","bxqk","ytwyj"};
	String  [] inputList = new String []{pk,jfyt1,je1,jfyt2,je2,jfyt3,je3,jfyt4,je4,jfyt5,je5,jfyt6,je6,shzt,yfje,jsr,bxqk,ytwyj};
	inserted = StandardOperation.insert(tableName, colList, inputList, request);
	}
	if(inserted){
		request.setAttribute("inserted", "ok");
	}else{
		request.setAttribute("inserted", "no");
		request.setAttribute("errMessage", "发生错误");
	}
	return mapping.findForward("sthdsq");
	}
	
	public ActionForward hdjfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		SxjyDAO dao = new SxjyDAO();
		HttpSession session = request.getSession();
		String userType =  session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stgl.do?method=hdjfsh")?"yes":"no";
		String query = "";
		StglForm sxjyForm = (StglForm) form;
		String tableName = "view_gdnz_sthdjf";
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String xqdm = request.getParameter("xqdm");
		String xy = sxjyForm.getXydm();
		xy = (xy==null) ? "" : xy;
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			sxjyForm.setXydm(xy);
		}
		query = SearchUtils.makeQueryCondition(xy,"","","","","","",nd,"",xn);
		if(xqdm!= null && !("".equalsIgnoreCase(xqdm.trim()))){
			query+=" and xqdm='";
			query+=xqdm;
			query+="' ";
		}
		colList = new String[] { "pk","stmc","hdmc","lsxm","xm","shzt"};
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = dao.sxjyQuery(tableName,query,new String []{},colList,"");
		}
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("xydm", xy);
		request.setAttribute("tableName", tableName);
		return mapping.findForward("sthdjfsh");
	}
	public ActionForward sthp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stgl.do?method=sthp")?"yes":"no";
		String pjyh = DealString.toGBK(session.getAttribute("userName").toString());
		SxjyDAO dao = new SxjyDAO();
		String nd = Base.currNd;
		String xn = Base.currXn;
		String xq = Base.currXq;
		String stdm= DealString.toGBK(request.getParameter("stdm"));
		String[] colList = new String[]{"xmdm","xmmc","qz","fz"};
		String tableName = "view_stpyqz";
		if(!stdm.equalsIgnoreCase("")){
			tableName = "view_stpyjg";
			rs = dao.sxjyQuery(tableName,"",new String []{xn,nd,xq,stdm,pjyh},colList,"select d.stdm,a.xn,a.xq,a.nd,d.stmc,d.xydm,d.xymc,d.xm,d.lsxm,d.xb,d.zwmc,d.lbmc,d.xmdm,d.xmmc,(a.fz*d.qz) jsfz,a.fz,a.pjyh,d.qz from (select b.stdm,b.stmc,b.xydm,b.xymc,b.xm,b.lsxm,b.xb,b.zwmc,b.lbmc,c.xmdm,c.xmmc,c.qz from view_stsqdj b, view_stpyqz c) d left join (select stdm,xmdm,xn,xq,nd,fz,pjyh from stpyjgb where xn=? and nd = ? and xq = ? and pjyh = ?) a on a.xmdm=d.xmdm and a.stdm=d.stdm where d.stdm = ?");
		}
		if(stdm.equalsIgnoreCase("")||rs.size()==0){
		rs = dao.sxjyQuery(tableName,"",new String []{},colList,"select xmdm,xmmc,qz,'' fz from view_stpyqz order by xmdm");
		}
		//取得到的社团信息
		HashMap<String, String> map = new HashMap<String, String>();
		tableName  = "view_stsqdj";
		map = dao.sxjyQueryOne(tableName,new String []{"xh","xm","lsxm","xymc","zwmc","xb","lbmc"},"stdm",stdm);
		map.put("stdm",stdm);
		request.setAttribute("rs2", map);
		request.setAttribute("rs", rs);
		request.setAttribute("stList", dao.getComboList());
		request.setAttribute("stdm", stdm);
	    request.setAttribute("pk", stdm+xn+nd+xq);
	    request.setAttribute("writeAble", writeAble);
	    return mapping.findForward("stpylr");
	}
	
	public ActionForward saveStPyJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 保存面试结果
	HttpSession session = request.getSession();
	SxjyDAO dao = new SxjyDAO();
	StglForm myForm = (StglForm) form;
	String [] mkdm = myForm.getMkdm();
	String [] fz = myForm.getFz();
	String pjyh = DealString.toGBK(session.getAttribute("userName").toString());
	String nd = Base.currNd;
	String xn = Base.currXn;
	String xq = Base.currXq;
	String stdm= DealString.toGBK(request.getParameter("stdm"));
	String tableName = "stpyjgb";
	String pkValue= DealString.toGBK(request.getParameter("pk"));
	boolean inserted = StandardOperation.delete(tableName, "stdm||xn||xq||nd",pkValue, request);
	colList = new String []{"stdm","xn","xq","nd","xmdm","fz","pjyh"};
	String[] insertSql = new String[mkdm.length];
	if(inserted){
		for(int i=0;i<mkdm.length;i++){
			insertSql[i] = StandardOperation.insertSql(tableName, colList, new String []{stdm,xn,xq,nd,mkdm[i],fz[i],pjyh}, request);
		}
		int[] res = dao.runBatch(insertSql);
		for(int i=0;i<res.length;i++){
			inserted = (res[i]==Statement.EXECUTE_FAILED)?false:true;
			if(!inserted) break;
		}
	}
	if(inserted){
		request.setAttribute("inserted", "ok");
	}else{
		request.setAttribute("inserted", "no");
	}
	return sthp(mapping,form,request,response);
}
	
	public ActionForward stpyqzConf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String[] colList = new String[]{"xmdm","xmmc","qz"};
		String tableName = "view_stpyqz";
		rs = dao.sxjyQuery(tableName," order by xmdm",new String []{},colList,"");
		request.setAttribute("rs", rs);
		return mapping.findForward("stpyqzConf");
	}
	
	public ActionForward saveStPyQz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 保存权重设置
	SxjyDAO dao = new SxjyDAO();
	StglForm myForm = (StglForm) form;
	String [] mkdm = myForm.getMkdm();
	String [] qz = myForm.getQz();
	String tableName = "stpyqzb";
	boolean inserted = StandardOperation.delete(tableName, "1","1", request);
	colList = new String []{"xmdm","qz"};
	String[] insertSql = new String[mkdm.length];
	if(inserted){
		for(int i=0;i<mkdm.length;i++){
			insertSql[i] = StandardOperation.insertSql(tableName, colList, new String []{mkdm[i],qz[i]}, request);
		}
		int[] res = dao.runBatch(insertSql);
		for(int i=0;i<res.length;i++){
			inserted = (res[i]==Statement.EXECUTE_FAILED)?false:true;
			if(!inserted) break;
		}
	}
	if(inserted){
		request.setAttribute("inserted", "ok");
	}else{
		request.setAttribute("inserted", "no");
	}
	return stpyqzConf(mapping,form,request,response);
}
	public ActionForward sthpjgcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 数据查询
	HttpSession session = request.getSession();
	ArrayList<String[]> rs = new ArrayList<String[]>();
	SxjyDAO dao = new SxjyDAO();
	writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stgl.do?method=sthpjgcx")?"yes":"no";
	String userType = session.getAttribute("userType").toString();
	String userDep = session.getAttribute("userDep").toString();
	String query = "";
	StglForm myForm = (StglForm) form;
	String tableName = "view_stpyjgzfb";
	String xy = myForm.getXydm();
	String xmdm = DealString.toGBK(myForm.getXmdm());
	xy = (xy==null) ? "" : xy;
	if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
		xy = userDep;
		myForm.setXydm(xy);
	}
	query = SearchUtils.makeQueryCondition(xy,"","","","","","","","","");
	if(null!=request.getParameter("ffjebj")&&null!=request.getParameter("bjfz")&&("")!=request.getParameter("ffjebj")&&("")!=request.getParameter("bjfz")){
		query += " and fz"+DealString.toGBK(request.getParameter("ffjebj"))+request.getParameter("bjfz");	    		
	}
		query += " and xmdm = '"+xmdm+"'";
	colList = new String[] { "stmc","xymc","xmmc","fz"};
	colListCN = dao.getColumnNameCN(colList, tableName);
	List topTr = dao.arrayToList(colList, colListCN);//表头 
	if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
		rs = dao.sxjyQuery(tableName,query,new String []{},colList,"");
	}
	request.setAttribute("writeAble", writeAble);
	request.setAttribute("rs", rs);// 发送数据集
	request.setAttribute("xyList", Base.getXyList());
	request.setAttribute("pbxmList", dao.getStAppraiseItemList());
	request.setAttribute("topTr", topTr);// 发送表头
	request.setAttribute("rsNum", rs.size());
	request.setAttribute("userType", userType);
	request.setAttribute("xydm", xy);
	request.setAttribute("tableName", "view_stpyjgzfb");
	return mapping.findForward("stpbjgcx");
}
	public ActionForward stpbjgjs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String tableName = "stpylsdjb";
		boolean inserted = StandardOperation.delete(tableName, "1","1", request);
		String nd = Base.currNd;
		String xn = Base.currXn;
		String xq = Base.currXq;
		if(inserted){
			String sql = "insert into stpylsdjb select stdm,avg(jsfz),xmdm fz from view_stpyjg where nd = ? and xn = ? and xq = ? group by stdm,xmdm order by stdm,xmdm";
			dao.runUpdate(sql, new String []{nd,xn,xq});
			//算出总分插入临时表内
			 sql = "insert into stpylsdjb select stdm,sum(jsfz),'sum' fz from view_stpyjg  where nd = ? and xn = ? and xq = ? group by stdm order by stdm";
				dao.runUpdate(sql, new String []{nd,xn,xq});
		}
		
		return sthpjgcx(mapping,form,request,response);
}
	public ActionForward sqjgcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		StglForm myForm = (StglForm) form;
		String xy = myForm.getXydm();
		String realTable = request.getParameter("realTable");
		String title = "";
		String [] inputList = new String []{};
		String pk = DealString.toGBK(request.getParameter("pk"));
		if(!pk.equalsIgnoreCase("")) {
			String temp = "";
			if(realTable.equalsIgnoreCase("stsqdjb")) {
				temp = "stdm";
			}else if(realTable.equalsIgnoreCase("gdnz_sthdsqb")){
				temp = "stdm||hdmc||hdsj";
			}else {
				temp = "pk";
			}
			boolean del = StandardOperation.delete(realTable, temp, pk, request);
			if(del) {
				request.setAttribute("delete", "ok");
			}else {
				request.setAttribute("delete", "no");
			}
		}
		writeAble = request.getParameter("writeAble");
		if(writeAble==null){
			writeAble = Base.getWriteAble(request);
		}
		if(realTable==null){
			realTable = "stsqdjb";
		}
		String tableName = request.getParameter("tableName");
		String query = "";
		xy = (xy==null) ? "" : xy;
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		query = SearchUtils.makeQueryCondition(xy,"","","","","","","","","");
		String shzt = DealString.toGBK(request.getParameter("shzt"));
		if(shzt!=null&&!shzt.equalsIgnoreCase("")){
			query += " and shzt = ? ";
			inputList = new String []{shzt};
		}
		if(realTable.equalsIgnoreCase("stsqdjb")){
			tableName = "view_stsqdj";
			colList = new String[] { "stdm","stmc","clsj","xymc","lsxm","xm","shzt"};
			title = "当前位置：社会工作 - 社团管理 - 社团申请结果查询";
		}else if(realTable.equalsIgnoreCase("gdnz_sthdsqb")){
			tableName = "view_gdnz_sthd";
			colList = new String[] { "pk","stmc","hdmc","hdsj","xm","shzt"};
			title = "当前位置：社会工作 - 社团管理 - 社团活动申请结果查询";
		}else if(realTable.equalsIgnoreCase("gdnz_sthdjfsqb")){
			tableName = "view_gdnz_sthdjf";
			colList = new String[] { "pk","stmc","hdmc","lsxm","xm","shzt"};
			title = "当前位置：社会工作 - 社团管理 - 社团活动经费申请结果查询";
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = dao.sxjyQuery(tableName,query,inputList,colList,"");
		}
		request.setAttribute("rs", rs);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("shzt", shzt);
		request.setAttribute("xydm", xy);
		request.setAttribute("shztList", dao.getChkList(3));
		return mapping.findForward("sqjgcx");
}
}