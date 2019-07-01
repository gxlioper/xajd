
package xgxt.sxjy.action.szdw;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.sql.CLOB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.SxjyDAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SxjyForm;
import xgxt.utils.CheckPower;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class XsgbAction extends DispatchAction{
	SxjyDAO dao = new SxjyDAO();
	String[] colList = null;
	String[] colListCN = null;
	String sql =null;
	String writeAble = null;
	
	public ActionForward msjglr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//显示面试页面
	HttpSession session = request.getSession();
	ArrayList<String[]> rs = new ArrayList<String[]>();
	writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stuCadre.do?method=msjglr")?"yes":"no";
	SxjyDAO dao = new SxjyDAO();
	String nd = Base.currNd;
	String xn = Base.currXn;
	String xq = Base.currXq;
	String xh= DealString.toGBK(request.getParameter("xh"));
	String[] colList = new String[]{"xmdm","xmmc","qz","fz"};
	String tableName = "view_xsgbmsqz";
	if(!xh.equalsIgnoreCase("")){
		tableName = "view_xsgbmsjg";
		rs = dao.sxjyQuery(tableName,"",new String []{xh,xn,nd,xq},colList,"select d.xh,d.xn,d.xq,d.nd,d.bjdm,d.bjmc,d.zydm,d.zymc,d.xydm,d.xymc,d.nj,d.xm,d.xb,d.sfzh,d.fz,c.qz,(d.fz*c.qz) jsfz,c.xmdm,c.xmmc from  view_xsgbmsqz c  left join (select a.xh,a.xn,a.xq,a.nd,b.bjdm,b.bjmc,b.zydm,b.zymc,b.xydm,b.xymc,a.xmdm,b.nj,b.xm,b.xb,b.sfzh,a.fz from xsgbmsjgb a left join view_xsjbxx b on a.xh =b.xh where a.xh =? and a.xn =? and a.nd =? and a.xq =?)  d on c.xmdm = d.xmdm");
	}
	if(xh.equalsIgnoreCase("")||rs.size()==0){
	rs = dao.sxjyQuery(tableName,"",new String []{},colList,"select xmdm,xmmc,qz,'' fz from view_xsgbmsqz order by xmdm");
	}
	//取得到的学号信息
	HashMap<String, String> map = new HashMap<String, String>();
	tableName  = "view_xsjbxx";
	map = dao.sxjyQueryOne(tableName,new String []{"xh","xm","nj","xymc","zymc","bjmc","xb"},"xh",xh);
	request.setAttribute("rs2", map);
	request.setAttribute("rs", rs);
	request.setAttribute("xh", xh);
    request.setAttribute("pk", xh+xn+xq+nd);
    request.setAttribute("writeAble", writeAble);
    return mapping.findForward("msjglr");
}
	public ActionForward saveMsJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 保存面试结果
	SxjyDAO dao = new SxjyDAO();
	SxjyForm sxjyForm = (SxjyForm) form;
	String [] mkdm = sxjyForm.getMkdm();
	String [] fz = sxjyForm.getFz();
	String nd = Base.currNd;
	String xn = Base.currXn;
	String xq = Base.currXq;
	String xh= DealString.toGBK(request.getParameter("xh"));
	String tableName = "xsgbmsjgb";
	String pkValue= DealString.toGBK(request.getParameter("pk"));
	boolean inserted = StandardOperation.delete(tableName, "xh||xn||xq||nd",pkValue, request);
	colList = new String []{"xh","xn","xq","nd","xmdm","fz"};
	String[] insertSql = new String[mkdm.length];
	if(inserted){
		for(int i=0;i<mkdm.length;i++){
			insertSql[i] = StandardOperation.insertSql(tableName, colList, new String []{xh,xn,xq,nd,mkdm[i],fz[i]}, request);
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
	return msjglr(mapping,form,request,response);
}
	public ActionForward qzConf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String[] colList = new String[]{"xmdm","xmmc","qz"};
		String tableName = "view_xsgbmsqz";
		rs = dao.sxjyQuery(tableName," order by xmdm",new String []{},colList,"");
		request.setAttribute("rs", rs);
		return mapping.findForward("qzConf");
	}
	
	public ActionForward saveXmQz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 保存权重设置
	SxjyDAO dao = new SxjyDAO();
	SxjyForm sxjyForm = (SxjyForm) form;
	String [] mkdm = sxjyForm.getMkdm();
	String [] qz = sxjyForm.getQz();
	String tableName = "xsgbmsqzb";
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
	return qzConf(mapping,form,request,response);
}
	
	public ActionForward msjgcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 数据查询
	HttpSession session = request.getSession();
	ArrayList<String[]> rs = new ArrayList<String[]>();
	SxjyDAO dao = new SxjyDAO();
	writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stuCadre.do?method=msjgcx")?"yes":"no";
	String userType = session.getAttribute("userType").toString();
	String userDep = session.getAttribute("userDep").toString();
	String query = "";
	SxjyForm sxjyForm = (SxjyForm) form;
	String tableName = "view_xsgbmszfb";
	String nj = sxjyForm.getNj();
	String xy = sxjyForm.getXydm();
	String zy = sxjyForm.getZydm();
	String bj = sxjyForm.getBjdm();
	String xh = DealString.toGBK(sxjyForm.getXh());
	String xm = DealString.toGBK(sxjyForm.getXm());
	String xb = DealString.toGBK(sxjyForm.getXb());
	xy = (xy==null) ? "" : xy;
	zy = (zy==null) ? "" : zy;
	bj = (bj==null) ? "" : bj;
	nj = (nj==null) ? "" : nj;
	if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
		xy = userDep;
		sxjyForm.setXydm(xy);
	}
	query = SearchUtils.makeQueryCondition(xy,zy,bj,xb,xh,xm,nj,"","","");
	if(null!=request.getParameter("ffjebj")&&null!=request.getParameter("bjfz")&&("")!=request.getParameter("ffjebj")&&("")!=request.getParameter("bjfz")){
		query += " and fz"+DealString.toGBK(request.getParameter("ffjebj"))+request.getParameter("bjfz");	    		
	}
	
	colList = new String[] { "xh","xm","xymc","zymc","bjmc","fz"};
	colListCN = dao.getColumnNameCN(colList, tableName);
	List topTr = dao.arrayToList(colList, colListCN);//表头 
	if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
		rs = dao.sxjyQuery(tableName,query,new String []{},colList,"");
	}
	String bjKey = xy+"!!"+zy+"!!"+nj;
	request.setAttribute("writeAble", writeAble);
	request.setAttribute("rs", rs);// 发送数据集
	request.setAttribute("xyList", Base.getXyList());
	request.setAttribute("zyList", (Base.getZyMap()).get(xy));
	request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
	request.setAttribute("njList", Base.getNjList());
	request.setAttribute("topTr", topTr);// 发送表头
	request.setAttribute("rsNum", rs.size());
	request.setAttribute("userType", userType);
	request.setAttribute("xydm", xy);
	request.setAttribute("zydm", zy);
	request.setAttribute("bjdm", bj);
	request.setAttribute("tableName", "view_xsgbmszfb");
	return mapping.findForward("msjgcx");
}
	public ActionForward msjgzfjs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String tableName = "xsgbmszfb";
		String nd = Base.currNd;
		String xn = Base.currXn;
		String xq = Base.currXq;
		boolean inserted = StandardOperation.delete(tableName, "1","1", request);
		if(inserted){
			String sql = "insert into xsgbmszfb select xh,sum(jsfz) fz from view_xsgbmsjg where nd = ? and xn = ? and xq = ? group by xh order by xh";
			dao.runUpdate(sql, new String []{nd,xn,xq});
		}
		return msjgcx(mapping,form,request,response);
}
	public ActionForward xsgbgzjh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		SxjyDAO dao = new SxjyDAO();
		writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stuCadre.do?method=msjgcx")?"yes":"no";
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		SxjyForm myForm = (SxjyForm) form;
		List pagesList = dao.getJhzlList();
		request.setAttribute("pages", pagesList);
		if(userType.equalsIgnoreCase("stu")){
			String xh = userName;
			String jhzldm = request.getParameter("type");
			if(jhzldm==null){
				jhzldm = (String)request.getAttribute("type");
			}
			jhzldm = (jhzldm==null) ? "001" : jhzldm;
			rs = dao.sxjyQuery("view_bjgbxx", " where xh = ?", new String []{userName}, new String []{"xh"}, "");
			if(rs.size()!=0){
				List<HashMap<String, String>> rs2 = getPlanList(userName,jhzldm);
				String plansId = DealString.toGBK(request.getParameter("plansId"));
				if(null!=plansId&&!("").equalsIgnoreCase(plansId)){
					sql = "select jhbt,jhbgnr,jhzldm from xsgbgzjhb where xh||jhzldm||lrrq||jhbt = ?";
					CLOB clob = dao.getOneClob(sql, new String[] { plansId }, "jhbgnr");
					String [] tempStrings = dao.getOneRs(sql, new String[] { plansId },new String[] { "jhbt","jhzldm" });
					String plansTit = tempStrings[0];
					jhzldm = tempStrings[1];
					String temp = clob.getSubString(1L, (int) clob.length());
					request.setAttribute("planscont", temp);
					request.setAttribute("planstit", plansTit);
					request.setAttribute("isModi", "yes");
					request.setAttribute("plansId", plansId);
				}else {
					request.setAttribute("planscont","");
					request.setAttribute("planstit", "");
					request.setAttribute("isModi", "no");
					request.setAttribute("plansId", "");
				}	
				request.setAttribute("type",jhzldm);
				request.setAttribute("xh", xh);
				request.setAttribute("rs", rs2);
			}else{
				request.setAttribute("type","");
				request.setAttribute("errMassage", "该模块只能由学生干部登入");
			}
			return mapping.findForward("xsgbgzjhForStu");
		}else{
			String xy = DealString.toGBK(myForm.getXydm());
			String zy = DealString.toGBK(myForm.getZydm());
			String bj = DealString.toGBK(myForm.getBjdm());
			String nj = DealString.toGBK(myForm.getNj());
			String nd = DealString.toGBK(myForm.getNd());
			String xh = DealString.toGBK(myForm.getXh());
			String jhzldm = DealString.toGBK(myForm.getJhzl());
			String xm = DealString.toGBK(myForm.getXm());
			String userDep = session.getAttribute("userDep").toString();
			if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
				if( request.getParameter("xydm")!=null){
					userDep =  request.getParameter("xydm");
					myForm.setXydm(xy);
				}else{
					userDep	= " ";
				}
			}
			if(userType.equalsIgnoreCase("xy") && (xy == null || xy.equalsIgnoreCase(""))){
				xy = userDep;
				myForm.setXydm(xy);
			}
			String[] colList = {"pk","jhbt","bjmc","xymc","jhzlmc","xm","lrrq"};
			String tableName = "view_xsgbjhzj";
			String realTable = "xsgbgzjhb";
			String[] colListCN = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);//表头 
			String query = SearchUtils.makeQueryCondition(xy, zy, bj, "", xh, xm, nj, nd,"","");
			if(jhzldm!=null&&!jhzldm.equalsIgnoreCase("")){
			query += " and jhzldm =";
			query += jhzldm;
			}
			String go = request.getParameter("go");
			if("go".equalsIgnoreCase(go)){
				rs = dao.sxjyQuery(tableName, query, new String[]{} , colList, "");
				request.setAttribute("rsNum", rs.size());
				request.setAttribute("topTr", topTr);
				request.setAttribute("rs", rs);
			}
			String writeAble = "";
			writeAble = (CheckPower.checkUsrPower(userName, "stuCadre.do?method=xsgbgzjh"))?"yes" : "no";
			request.setAttribute("realTable", realTable);
			request.setAttribute("tableName", tableName);
			request.setAttribute("writeAble", writeAble);
			String bjKey = xy+"!!"+zy+"!!"+nj;
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("rs", rs);// 发送数据集
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", (Base.getZyMap()).get(xy));
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("ndList", dao.getNdList());
			request.setAttribute("bjgbjhzj", pagesList);
			request.setAttribute("xydm", xy);
			request.setAttribute("zydm", zy);
			request.setAttribute("bjdm", bj);
			return mapping.findForward("xsgbgzjhForTea");
		}	
	}
	
	public ActionForward deleteOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("pk"));
		StandardOperation.delete("fdydjjlb", "zgh||bdjzgh",pk, request);
		return msjglr(mapping,form,request,response);
	}
	
	public ActionForward xsgbgzjhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("pk"));
		sql = "select jhzldm from xsgbgzjhb where xh||jhzldm||lrrq||jhbt = ?";
		String jhzldm = (dao.sxjyQueryOne2("xsgbgzjhb",new String [] {"jhzldm"},"xh||jhzldm||lrrq||jhbt",pk))[0];
		request.setAttribute("type", jhzldm);
		StandardOperation.delete("xsgbgzjhb", "xh||jhzldm||lrrq||jhbt",pk, request);
		return xsgbgzjh(mapping,form,request,response);
	}
	
	public ActionForward postilOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("pk"));
		String tableName = "view_xsgbjhzj";
		String [] colList = new String []{"pk","xh","xm","xb","nj","jhzlmc","xymc","zymc","jhbt","bjmc","lrrq","pz"};
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		request.setAttribute("rs", rs);
		return mapping.findForward("postilOne");
	}
	
	public ActionForward postilOneUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("pk"));
		String pz = DealString.toGBK(request.getParameter("pz"));
		String tableName = "xsgbgzjhb";
		boolean updata = StandardOperation.update(tableName, new String []{"pz"}, new String []{pz},  "xh||jhzldm||lrrq||jhbt",pk, request);
		if(updata){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return postilOne(mapping,form,request,response);
	}
	
	public ActionForward xsgbpb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//显示面试页面
	HttpSession session = request.getSession();
	ArrayList<String[]> rs = new ArrayList<String[]>();
	writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stuCadre.do?method=xsgbpb")?"yes":"no";
	String pjyh = DealString.toGBK(session.getAttribute("userName").toString());
	SxjyDAO dao = new SxjyDAO();
	String nd = Base.currNd;
	String xn = Base.currXn;
	String xq = Base.currXq;
	String xh= DealString.toGBK(request.getParameter("xh"));
	String[] colList = new String[]{"xmdm","xmmc","qz","fz"};
	String tableName = "view_xsgbpbqz";
	if(!xh.equalsIgnoreCase("")){
		tableName = "view_xsgbpbjg";
		rs = dao.sxjyQuery(tableName," where xh = ? and xn = ? and nd = ? and xq = ? and pjyh = ? order by xmdm",new String []{xh,xn,nd,xq,pjyh},colList,"select d.xh,d.xn,d.xq,d.nd,d.bjdm,d.bjmc,d.zydm,d.zymc,d.xydm,d.xymc,d.nj,d.xm,d.xb,d.sfzh,d.fz,d.pjyh,c.xmdm,c.xmmc,(d.fz*c.qz) jsfz,c.qz from view_xsgbpbqz c  left join (select a.xh,a.xn,a.xq,a.nd,b.bjdm,b.bjmc,b.zydm,b.zymc,b.xydm,b.xymc,b.nj,b.xm,b.xb,b.sfzh,a.fz,a.pjyh,a.xmdm from xsgbpbjgb a left join view_xsjbxx b on a.xh =b.xh where a.xh = ? and a.xn = ? and a.nd = ? and a.xq = ? and pjyh = ? ) d on d.xmdm = c.xmdm");
	}
	if(xh.equalsIgnoreCase("")||rs.size()==0){
	rs = dao.sxjyQuery(tableName,"",new String []{},colList,"select xmdm,xmmc,qz,'' fz from view_xsgbpbqz order by xmdm");
	}
	//取得到的学号信息
	HashMap<String, String> map = new HashMap<String, String>();
	tableName  = "view_bjgbxx";
	map = dao.sxjyQueryOne(tableName,new String []{"xh","xm","nj","xymc","lrrq","bjmc","xb","bjgbmc"},"xh",xh);
	request.setAttribute("rs2", map);
	request.setAttribute("rs", rs);
	request.setAttribute("xh", xh);
    request.setAttribute("pk", xh+xn+xq+nd+pjyh);
    request.setAttribute("writeAble", writeAble);
    return mapping.findForward("pbjglr");
}
	
	public ActionForward pjqzConf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String[] colList = new String[]{"xmdm","xmmc","qz"};
		String tableName = "view_xsgbpbqz";
		rs = dao.sxjyQuery(tableName," order by xmdm",new String []{},colList,"");
		request.setAttribute("rs", rs);
		return mapping.findForward("pjqzConf");
	}
	
	public ActionForward savePjXmQz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		// 保存权重设置
		SxjyDAO dao = new SxjyDAO();
		SxjyForm sxjyForm = (SxjyForm) form;
		String [] mkdm = sxjyForm.getMkdm();
		String [] qz = sxjyForm.getQz();
		String tableName = "xsgbpbqzb";
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
		return pjqzConf(mapping,form,request,response);
	}
	
	public ActionForward getStuCadreOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tableName  = "view_bjgbxx";
		String rsNum = "0";
		SxjyForm myForm = (SxjyForm) form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xy = DealString.toGBK(myForm.getXydm());
		String zy = DealString.toGBK(myForm.getZydm());
		String bj = DealString.toGBK(myForm.getBjdm());
		String nj = DealString.toGBK(myForm.getNj());
		String xh = DealString.toGBK(myForm.getXh());
		String xm = DealString.toGBK(myForm.getXm());
		String query = SearchUtils.makeQueryCondition(xy,zy,bj,"",xh,xm,nj,"","","");
		colList = new String[] { "xh","xm","nj","xymc","bjmc","bjgbmc"};
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
		 rs = dao.sxjyQuery(tableName,query,new String []{},colList,"");
		}
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}	
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);// 发送表头
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("xydm", xy);
		request.setAttribute("zydm", zy);
		request.setAttribute("bjdm", bj);
		return mapping.findForward("getBjgb");
	}
	
	public List<HashMap<String, String>> getPlanList(String xh,String jhzldm){
		String sql = "select pk,jhbt,lrrq,sfhfpz from view_xsgbjhzj where xh = ? and jhzldm = ? order by lrrq desc";
		List<HashMap<String, String>> planList = dao.getList(sql, new String[]{xh,jhzldm}, new String[]{"pk","jhbt","lrrq","sfhfpz"});
		return planList;
	}
	
	public ActionForward savePbJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 保存评比结果
	HttpSession session = request.getSession();
	String pjyh = DealString.toGBK(session.getAttribute("userName").toString());
	SxjyDAO dao = new SxjyDAO();
	SxjyForm sxjyForm = (SxjyForm) form;
	String [] mkdm = sxjyForm.getMkdm();
	String [] fz = sxjyForm.getFz();
	String nd = Base.currNd;
	String xn = Base.currXn;
	String xq = Base.currXq;
	String xh= DealString.toGBK(request.getParameter("xh"));
	String tableName = "xsgbpbjgb";
	String pkValue= DealString.toGBK(request.getParameter("pk"));
	boolean inserted = StandardOperation.delete(tableName, "xh||xn||xq||nd||pjyh",pkValue, request);
	colList = new String []{"xh","xn","xq","nd","xmdm","fz","pjyh"};
	String[] insertSql = new String[mkdm.length];
	if(inserted){
		for(int i=0;i<mkdm.length;i++){
			insertSql[i] = StandardOperation.insertSql(tableName, colList, new String []{xh,xn,xq,nd,mkdm[i],fz[i],pjyh}, request);
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
	return xsgbpb(mapping,form,request,response);
}
	public ActionForward gbpbjgcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// 数据查询
	HttpSession session = request.getSession();
	ArrayList<String[]> rs = new ArrayList<String[]>();
	SxjyDAO dao = new SxjyDAO();
	writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stuCadre.do?method=msjgcx")?"yes":"no";
	String userType = session.getAttribute("userType").toString();
	String userDep = session.getAttribute("userDep").toString();
	String query = "";
	SxjyForm sxjyForm = (SxjyForm) form;
	String tableName = "view_xsgbpbzfb";
	String nj = sxjyForm.getNj();
	String xy = sxjyForm.getXydm();
	String zy = sxjyForm.getZydm();
	String bj = sxjyForm.getBjdm();
	String xh = DealString.toGBK(sxjyForm.getXh());
	String xm = DealString.toGBK(sxjyForm.getXm());
	String xb = DealString.toGBK(sxjyForm.getXb());
	String xmdm = DealString.toGBK(sxjyForm.getXmdm());
	xy = (xy==null) ? "" : xy;
	zy = (zy==null) ? "" : zy;
	bj = (bj==null) ? "" : bj;
	nj = (nj==null) ? "" : nj;
	if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
		xy = userDep;
		sxjyForm.setXydm(xy);
	}
	query = SearchUtils.makeQueryCondition(xy,zy,bj,xb,xh,xm,nj,"","","");
	if(null!=request.getParameter("ffjebj")&&null!=request.getParameter("bjfz")&&("")!=request.getParameter("ffjebj")&&("")!=request.getParameter("bjfz")){
		query += " and fz"+DealString.toGBK(request.getParameter("ffjebj"))+request.getParameter("bjfz");	    		
	}
		query += " and xmdm = '"+xmdm+"'";
	colList = new String[] { "xh","xm","xymc","zymc","bjmc","fz"};
	colListCN = dao.getColumnNameCN(colList, tableName);
	List topTr = dao.arrayToList(colList, colListCN);//表头 
	if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
		rs = dao.sxjyQuery(tableName,query,new String []{},colList,"");
	}
	String bjKey = xy+"!!"+zy+"!!"+nj;
	request.setAttribute("writeAble", writeAble);
	request.setAttribute("rs", rs);// 发送数据集
	request.setAttribute("xyList", Base.getXyList());
	request.setAttribute("zyList", (Base.getZyMap()).get(xy));
	request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
	request.setAttribute("njList", Base.getNjList());
	request.setAttribute("pbxmList", dao.getAppraiseItemList());
	request.setAttribute("topTr", topTr);// 发送表头
	request.setAttribute("rsNum", rs.size());
	request.setAttribute("userType", userType);
	request.setAttribute("xydm", xy);
	request.setAttribute("zydm", zy);
	request.setAttribute("bjdm", bj);
	request.setAttribute("tableName", "view_xsgbmszfb");
	return mapping.findForward("pbjgcx");
}
	public ActionForward pbjgjs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String tableName = "xsgbpbzfb";
		String nd = Base.currNd;
		String xn = Base.currXn;
		String xq = Base.currXq;
		boolean inserted = StandardOperation.delete(tableName, "1","1", request);
		if(inserted){
			String sql = "insert into xsgbpbzfb select xh,avg(jsfz),xmdm fz from view_xsgbpbjg  where nd = ? and xn = ? and xq = ? group by xh,xmdm order by xh,xmdm";
			dao.runUpdate(sql, new String []{nd,xn,xq});
			//算出总分插入临时表内
			 sql = "insert into xsgbpbzfb select xh,sum(jsfz),'sum' fz from view_xsgbpbjg  where nd = ? and xn = ? and xq = ? group by xh order by xh";
				dao.runUpdate(sql, new String []{nd,xn,xq});
		}
		
		return gbpbjgcx(mapping,form,request,response);
}
	public ActionForward xsgbgrzh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		SxjyDAO dao = new SxjyDAO();
		writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stuCadre.do?method=xsgbgrzh")?"yes":"no";
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String query = "";
		SxjyForm sxjyForm = (SxjyForm) form;
		String tableName = "view_bjgbgrxx";
		String nj = sxjyForm.getNj();
		String xy = sxjyForm.getXydm();
		String zy = sxjyForm.getZydm();
		String bj = sxjyForm.getBjdm();
		String xh = DealString.toGBK(sxjyForm.getXh());
		String xm = DealString.toGBK(sxjyForm.getXm());
		String xb = DealString.toGBK(sxjyForm.getXb());
		xy = (xy==null) ? "" : xy;
		zy = (zy==null) ? "" : zy;
		bj = (bj==null) ? "" : bj;
		nj = (nj==null) ? "" : nj;
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			sxjyForm.setXydm(xy);
		}
		query = SearchUtils.makeQueryCondition(xy,zy,bj,xb,xh,xm,nj,"","","");
		colList = new String[] { "pk","xh","xm","xymc","zymc","bjmc","bjgbmc","sfdj"};
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = dao.sxjyQuery(tableName,query,new String []{},colList,"");
		}
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("xydm", xy);
		request.setAttribute("zydm", zy);
		request.setAttribute("bjdm", bj);
		request.setAttribute("tableName", "view_xsgbmszfb");
		return mapping.findForward("xsgbgrzhforTea");
}
	public ActionForward xsgbgrzhOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		ArrayList<String[]> rsTmp = new ArrayList<String[]>();
		HashMap<String, String> rs =new HashMap<String, String>();
		ArrayList<String[]> dzdxm = new ArrayList<String[]>();
		ArrayList<String[]> rqxm = new ArrayList<String[]>();
		ArrayList<String[]> wbxm = new ArrayList<String[]>();
		ArrayList<String[]> wbyxm = new ArrayList<String[]>();
		SxjyDAO dao = new SxjyDAO();
		writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stuCadre.do?method=xsgbgrzh")?"yes":"no";
		String userType = session.getAttribute("userType").toString();
		String tableName = "view_bjgbgrxx";
		String pk = DealString.toGBK(request.getParameter("pk"));
		rsTmp = dao.sxjyQuery("xsgbzhwhbb","",new String []{},new String []{"xmdm","xmmc","sszldm"},"");
		String [] xmList;
		String xmzl;
		for(int i = 0;i<rsTmp.size();i++){
			xmList = rsTmp.get(i);
			xmzl = xmList[2];
			if(xmzl.equalsIgnoreCase("001")){
			dzdxm.add(xmList);
			}else if(xmzl.equalsIgnoreCase("002")){
			rqxm.add(xmList);
			}else if(xmzl.equalsIgnoreCase("003")){
			wbxm.add(xmList);
			}else if(xmzl.equalsIgnoreCase("004")){
			wbyxm.add(xmList);
			}
		}
		request.setAttribute("dzdxmList", dzdxm);
		request.setAttribute("rqxmList", rqxm);
		request.setAttribute("wbxmList", wbxm);
		request.setAttribute("wbyxmList", wbyxm);
		colList = new String[(9+rsTmp.size())];
		colList[0]="pk";
		colList[1]="xh";
		colList[2]="xm";
		colList[3]="xymc";
		colList[4]="zymc";
		colList[5]="bjmc";
		colList[6]="bjgbmc";
		colList[7]="nj";
		colList[8]="xb";
		for(int i = 0;i<rsTmp.size();i++){
			colList[9+i]=rsTmp.get(i)[0];
		}
		rs = dao.sxjyQueryOne(tableName,colList,"pk",pk);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", "view_bjgbgrxx");
		return mapping.findForward("xsgbgrzhOne");
}
	public ActionForward delXsgbgrzh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("delPk"));
		StandardOperation.delete("xsgbzhb", "xh",pk, request);
		return xsgbgrzh(mapping,form,request,response);
	}
	
	public ActionForward xsgbgrzhConf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tableName = "xsgbzhwhbb";
		colList = new String[]{"xmdm","xmmc","sszldm"};
		ArrayList<String[]> rs = new ArrayList<String[]>();
		rs = dao.sxjyQuery("xsgbzhwhbb","",new String []{},colList,"");
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);//表头 
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		return mapping.findForward("xsgbgrzhConf");
	}
	
	public ActionForward bdpzUpdata(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("pk"));
		colList = new String[]{"xmdm","xmmc","sszldm"};
		HashMap<String, String> rs =new HashMap<String, String>();
		rs = dao.sxjyQueryOne("xsgbzhwhbb",colList,"xmdm",pk);
		request.setAttribute("rs", rs);// 发送数据集
		if(pk.equalsIgnoreCase("")){
			request.setAttribute("act", "add");
		}else{
			request.setAttribute("act", "modi");
		}
		return mapping.findForward("bdxg");
	}
	
	public ActionForward bjgbxbOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String act = DealString.toGBK(request.getParameter("act"));
		String nd = Base.currNd;
		String xn = Base.currXn;
		String xq = Base.currXq;
		String xh="";
		HashMap<String, String> map = new HashMap<String, String>();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		HttpSession session = request.getSession();
		String userName = request.getSession().getAttribute("userName").toString();
		if(!userType.equalsIgnoreCase("stu")&&act.equalsIgnoreCase("cx")){
			ArrayList<String[]> rs = new ArrayList<String[]>();
			SxjyDAO dao = new SxjyDAO();
			writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stuCadre.do?method=msjgcx")?"yes":"no";
			String query = "";
			SxjyForm sxjyForm = (SxjyForm) form;
			String tableName = "view_xsgbxbxx";
			String nj = sxjyForm.getNj();
			String xy = sxjyForm.getXydm();
			String zy = sxjyForm.getZydm();
			String bj = sxjyForm.getBjdm();
			xh = DealString.toGBK(sxjyForm.getXh());
			String xm = DealString.toGBK(sxjyForm.getXm());
			nd = DealString.toGBK(sxjyForm.getNd());
			xn = DealString.toGBK(sxjyForm.getXn());
			xq = DealString.toGBK(sxjyForm.getXq());
			xy = (xy==null) ? "" : xy;
			zy = (zy==null) ? "" : zy;
			bj = (bj==null) ? "" : bj;
			nj = (nj==null) ? "" : nj;
			if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
				xy = userDep;
				sxjyForm.setXydm(xy);
			}
			query = SearchUtils.makeQueryCondition(xy,zy,bj,"",xh,xm,nj,nd,xq,xn);
			String bjgbdm =DealString.toGBK(sxjyForm.getJpzw());
			if(!bjgbdm.equalsIgnoreCase("")){
				query += " and jpzw = ";
				query += bjgbdm;
			}
			colList = new String[] { "xh||xn||nd||xq","xh","xm","xymc","zymc","bjmc","bjgbmc"};
			colListCN = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);//表头 
			if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = dao.sxjyQuery(tableName,query,new String []{},colList,"");
			}
			String bjKey = xy+"!!"+zy+"!!"+nj;
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("rs", rs);// 发送数据集
			FormModleCommon.formToGBK(sxjyForm);
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", (Base.getZyMap()).get(xy));
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
			request.setAttribute("njList", Base.getNjList());
			request.setAttribute("xqList", Base.getXqList());
			request.setAttribute("xnList", Base.getXnndList());
			request.setAttribute("pbxmList", dao.getAppraiseItemList());
			request.setAttribute("topTr", topTr);// 发送表头
			request.setAttribute("rsNum", rs.size());
			request.setAttribute("userType", userType);
			request.setAttribute("xydm", xy);
			request.setAttribute("zydm", zy);
			request.setAttribute("bjdm", bj);
			request.setAttribute("tableName", "view_xsgbmszfb");
			request.setAttribute("zwList", dao.getClassDutyList());
			return mapping.findForward("bjgbxbCx");
		}
		if (act.equalsIgnoreCase("save")){
			String ddxg = request.getParameter("ddxg");
			ddxg = (ddxg==null) ? "" : ddxg;
			boolean res = false;
			if(ddxg.equalsIgnoreCase("yes")){
				request.setAttribute("ddxg", "yes");
			}
			String pkValue =DealString.toGBK(request.getParameter("pkValue"));
			xh =DealString.toGBK(request.getParameter("xh"));
			String tc = DealString.toGBK(request.getParameter("tc"));
			String jpzz = DealString.toGBK(request.getParameter("jpzz"));
			String jpbm = DealString.toGBK(request.getParameter("jpbm"));
			String jpzw = DealString.toGBK(request.getParameter("jpzw"));
			String rzjl = DealString.toGBK(request.getParameter("rzjl"));
			String jljl = DealString.toGBK(request.getParameter("jljl"));
			String zwtj = DealString.toGBK(request.getParameter("zwtj"));
			boolean del = StandardOperation.delete("xsgbxbxxb", "xh||xn||nd||xq",pkValue, request);
			if(del){
				res = StandardOperation.insert("xsgbxbxxb", new String []{"xh","xn","nd","xq","jpzz","jpbm","jpzw","tc","rzjl","jljl","zwtj"}, new String []{xh,xn,nd,xq,jpzz,jpbm,jpzw,tc,rzjl,jljl,zwtj}, request);
			}
			if (res) {
				request.setAttribute("inserted", "ok");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		String pk = "";
		
		if(userType.equalsIgnoreCase("stu")){
			pk = userName;
			writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "stuCadre.do?method=bjgbxbOne")?"yes":"no";
			request.setAttribute("ddxg", "yes");
		}else{
		xh = request.getParameter("xh");
		xh = (xh==null) ? "" : xh;
		   pk=xh;
		}
		if(request.getParameter("pk")==null){
			   pk = xh+xn+nd+xq;
		   }else{
			   pk = request.getParameter("pk");
		   }
		String tableName = "view_xsgbxbxx";
		String[] colList = new String[] { "xh","xm","nj","xb","bjdm","bjmc","xq","xn","nd","jpzz","jpbm","jpzw","xymc","xydm","tc","rzjl","zydm","zymc","jljl","zwtj","zzmmmc","lxdh"};
		map = dao.sxjyQueryOne(tableName, colList, "xh||xn||nd||xq", pk);
		if(map.get("xh").equalsIgnoreCase("")){
			colList = new String[] { "xh","xm","nj","xb","bjdm","bjmc","zydm","zymc","xydm","xymc","lxdh"};
			map=dao.sxjyQueryOne("view_xsjbxx", colList, "xh", xh);
			map.put("xn", xn);
			map.put("xq", xq);
			map.put("nd", nd);
		}
		request.setAttribute("doType", act);
		request.setAttribute("pkValue", pk);
		request.setAttribute("zwList", dao.getClassDutyList());
		request.setAttribute("rs", map);
		request.setAttribute("userType", userType);
		return mapping.findForward("bjgbxbOne");
	}
	
	public ActionForward bgbxbDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("delPk"));
		StandardOperation.delete("xsgbxbxxb", "xh||xn||nd||xq",pk, request);
		return new ActionForward("/stuCadre.do?method=bjgbxbOne&act=cx");
	}
	
	public ActionForward saveOneXm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		boolean insert = false;
		String xmdm = DealString.toGBK(request.getParameter("xmdm"));
		String xmmc = DealString.toGBK( request.getParameter("xmmc"));
		String sszldm = DealString.toGBK(request.getParameter("sszldm"));
		String tableName = "xsgbzhwhbb";
		String act = request.getParameter("act");
		colList = new String[]{"xmdm","xmmc","sszldm"};
		String zdtype="";
		if(sszldm.equalsIgnoreCase("001")){
		zdtype=" varchar2(50)";
		}else if (sszldm.equalsIgnoreCase("002")){
		zdtype=" varchar2(12)";
		}else if (sszldm.equalsIgnoreCase("003")){
		zdtype=" varchar2(120)";
		}else{
		zdtype=" varchar2(600)";
		}
		if(act.equalsIgnoreCase("modi")){
			insert = StandardOperation.update(tableName, new String []{"xmmc"}, new String []{xmmc}, "xmdm", xmdm, request);
		}else{
		boolean del = StandardOperation.delete(tableName, "xmdm",xmdm, request);
		if(del){
			insert = StandardOperation.insert(tableName, colList, new String []{xmdm,xmmc,sszldm}, request);
			if(insert){
			insert = dao.runUpdateTab("alter table xsgbzhb add "+xmdm+zdtype);
			}
		}
		if(insert){	
			dao.runUpdateTab("comment on column xsgbzhb."+xmdm+" is '"+xmmc+"'");
			String [] outComment = dao.getViewComm("view_bjgbgrxx");
			String sqlTemp = "create or replace view view_bjgbgrxx as select a.xh pk,a.xm,a.nj,a.xb,a.bjdm,a.bjmc,a.bjgbmc,a.bjgbdm,a.xydm,a.xymc,a.lxfs,a.lrrq,a.bz,a.zydm,a.zymc,b.*,(case when b.xh is null then '否' else '是' end) sfdj from view_bjgbxx a left join xsgbzhb b on a.xh = b.xh";
			dao.creatView(sqlTemp, outComment);
			dao.runUpdateTab("comment on column view_bjgbgrxx."+xmdm+" is '"+xmmc+"'");
		}
		}
		if(insert){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("bdxg");
	}
	
	public ActionForward delxsgbzhXm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		boolean insert = false;
		String pk = DealString.toGBK(request.getParameter("delPk"));
		boolean del = StandardOperation.delete("xsgbzhwhbb", "xmdm",pk, request);
		if(del){
			insert = StandardOperation.update("xsgbzhb","update xsgbzhb set "+pk+"=''", request);
			if(insert){
				insert = dao.runUpdateTab("alter table xsgbzhb drop column "+pk);
				if(insert){
				String [] outComment = dao.getViewComm("view_bjgbgrxx");
				String sqlTemp = "create or replace view view_bjgbgrxx as select a.xh pk,a.xm,a.nj,a.xb,a.bjdm,a.bjmc,a.bjgbmc,a.bjgbdm,a.xydm,a.xymc,a.lxfs,a.lrrq,a.bz,a.zydm,a.zymc,b.*,(case when b.xh is null then '否' else '是' end) sfdj from view_bjgbxx a left join xsgbzhb b on a.xh = b.xh";
				dao.creatView(sqlTemp, outComment);
				}
			}
		}
		return xsgbgrzhOne(mapping,form,request,response);
	}
	
	public ActionForward bjgbzhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pk = DealString.toGBK(request.getParameter("pk"));
		String tableName = "xsgbzhb";
		ArrayList<String[]> rsTmp = new ArrayList<String[]>();
		boolean inserted = false;
		boolean del = StandardOperation.delete(tableName, "xh",pk, request);
		if(del){
		rsTmp = dao.sxjyQuery("xsgbzhwhbb","",new String []{},new String []{"xmdm","xmmc","sszldm"},"");
		colList = new String[(1+rsTmp.size())];
		colList[0]="xh";
		String inputList [] = new String[(1+rsTmp.size())];
		inputList[0]=pk;
		for(int i = 0;i<rsTmp.size();i++){
			colList[1+i]=rsTmp.get(i)[0];
			inputList[1+i]=request.getParameter(rsTmp.get(i)[0]);
		}
		inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return xsgbgrzhOne(mapping,form,request,response);
	}
}