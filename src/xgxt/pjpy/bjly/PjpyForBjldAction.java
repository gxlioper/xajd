package xgxt.pjpy.bjly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.CheckPower;

public class PjpyForBjldAction extends Action{
	
	 private boolean isNull(String str) {
			return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
		    }
	 
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) 
									throws Exception{
		HttpSession session = request.getSession();
		ActionForward af = new ActionForward();
		if( (af = Base.chkSessionTimeOut(session)) != null){
			return af;
		};
		CommanForm tempForm = new CommanForm();
		String parameter = mapping.getParameter();
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		try{
			if("pjpy_bjly_jxjsz".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "pjpy_jxjsz.do")){
					af = priseConf(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			}else if("pjpy_jxjsz_delComman".equalsIgnoreCase(parameter)){
					af = pjpy_jxjsz_delComman(mapping,form,request,response);
			}else if("pjpy_bjly_jxjszOne".equalsIgnoreCase(parameter)){
					af = pjpy_jxjsz_one(mapping,form,request,response);
			}else if("pjpy_jxjsz_save".equalsIgnoreCase(parameter)){
					af = pjpy_jxjsz_save(mapping,form,request,response);
			}else if("pjpy_jxjsjsz".equalsIgnoreCase(parameter)){
					af = pjpy_jxjsjsz(mapping,form,request,response);
			}else if("pjpy_jxjsjsz_save".equalsIgnoreCase(parameter)){
					af = pjpy_jxjsjsz_save(mapping,form,request,response);
			}else if("pjpy_jxjsjsz_one".equalsIgnoreCase(parameter)){
					af = pjpy_jxjsjsz_one(mapping,form,request,response);
			}else if("bjlyPjpyApply".equalsIgnoreCase(parameter)){
					af = bjlyPjpyApply(mapping,form,request,response);
			}else if("pjpy_bjly_tj".equalsIgnoreCase(parameter)){
					af = pjpy_bjly_tj(mapping,form,request,response);
			}
		} catch(Exception e){
			e.printStackTrace();
			tempForm.setErrMsg("出现异常，请重新登陆！");
			return new ActionForward("/login.jsp",false);
		}
		
		return af;
	}

private ActionForward pjpy_bjly_tj(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
    //设置奖学金获得条件
	DAO dao = DAO.getInstance();
	String jxj = request.getParameter("xmdm");
	String zdm = request.getParameter("zdm");
	String ysf = request.getParameter("ysf");
	String val = request.getParameter("val");
	String go = request.getParameter("go");
	String sql = "";// sql语句
	sql = "select jxjdm,jxjmc from jxjdmb_tmp_bjly";
	List jxjList = dao.getList(sql, new String[] {}, new String[] {
		"jxjdm", "jxjmc" });
	sql = "select zdmc||'!!'||lyb zd,zdsm from jxjtjzdb";
	List zdList = dao.getList(sql, new String[] {}, new String[] { "zd",
		"zdsm" });
	if ((go != null) && go.equalsIgnoreCase("go")) {
	    if (isNull(zdm)) {
		return mapping.findForward("false");
	    }
	    String[] tmp = zdm.split("!!");
	    sql = "insert into jxjhdtj(jxjdm,tjzdm,tjzdlyb,tj) values(?,?,?,?)";
	    dao.runUpdate(sql, new String[] { jxj, tmp[0], tmp[1],
		    ysf + "'" + val + "'"});
	} else if ((go != null) && go.equalsIgnoreCase("del")) {
	    String pkVal = request.getParameter("pkVal");
	    sql = "delete from jxjhdtj where replace(replace((tjzdlyb||'.'||tjzdm||tj),' ',''),chr(39),'') = ?";
	    dao.runUpdate(sql, new String[] { pkVal.replaceAll(" ", "")
		    .replaceAll("'", "") });
	} else if ((go != null) && go.equalsIgnoreCase("delAll")){
		sql = "delete from jxjhdtj where jxjdm = ?";
	    dao.runUpdate(sql, new String[] {jxj});
	}
	if (!isNull(jxj)) {
	    sql = "select rownum,(tjzdlyb||'.'||tjzdm) tjzdm,tj,tjzdlyb from jxjhdtj where jxjdm=?";
	    List rs = dao.getList(sql, new String[] { jxj }, new String[] {
		    "rownum", "tjzdm", "tj"});
	    request.setAttribute("rs", rs);
	}
	request.setAttribute("jxjList", jxjList);
	request.setAttribute("zdList", zdList);
	return mapping.findForward("success");
	}

private ActionForward bjlyPjpyApply(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {
	DAO dao = DAO.getInstance();
	String tips = "评奖评优 - 奖学金申请 - 奖学金评审登记表";
	request.setAttribute("tips", tips);
	HttpSession session = request.getSession();
	String userType = session.getAttribute("userType").toString();
	String xh = request.getParameter("xh");
//	String jxj = request.getParameter("jxjdm");
//	Vector<String[]> rs = new Vector<String[]>();
	ArrayList<String[]> rstmp = new ArrayList<String[]>();
	String xydm = session.getAttribute("userDep").toString();
//	String sfpks = "否";
	if (userType.equalsIgnoreCase("stu")) {
	    xh = session.getAttribute("userName").toString();
	}else{
		return mapping.findForward("success");
	}
	
	String sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
	String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
		"jxjsqxn", "jxjsqnd" });
//	String xn = tmp[0];
	String nd = tmp[1];
	//初始化jxjsqrs
	HashMap<String, HashMap<String,String>> jxjsqrs = new HashMap<String, HashMap<String,String>>();
	sql="select jxjdm,jxjmc,jxjsm,lbmc,jxjje from view_bjly_jxjsz where nd = ?";
	rstmp = dao.rsToVator(sql, new String[] {nd},  new String[] {"jxjdm","jxjmc","jxjsm","lbmc","jxjje"});
	for(int i=0;i<rstmp.size();i++){
		HashMap<String,String> tmpMap = new HashMap<String,String>();
		tmpMap.put("jxjdm", ((String[])rstmp.get(i))[0]);
		tmpMap.put("jxjmc", ((String[])rstmp.get(i))[1]);
		tmpMap.put("jxjsm", ((String[])rstmp.get(i))[2]);
		tmpMap.put("lbmc", ((String[])rstmp.get(i))[3]);
		tmpMap.put("jxjje", ((String[])rstmp.get(i))[4]);
		tmpMap.put("sfksq", "1");
		jxjsqrs.put(((String[])rstmp.get(i))[0],tmpMap);
	}
	//判断是否是贫困生
	sql="select jxjdm from view_bjlydx_knssqb where xh = ?";
	String query = "";
	tmp = dao.getOneRs(sql, new String[] {xh}, new String[] {
		"jxjdm" });
	if(tmp!=null){
		if(tmp[0].equalsIgnoreCase("通过")){
		query = "and sfxdpks='是'";
		}
	}
	//判断是否能够参评
	sql="select jxjdm from view_bjly_jxjsz where nd = ?";
	sql+=query;
	rstmp = dao.rsToVator(sql, new String[] {nd},  new String[] {"jxjdm"});
	String [] sqsfknsjxjdm=new String[rstmp.size()+1];
	for(int i=0;i<rstmp.size();i++){
		sqsfknsjxjdm[i]=((String[])rstmp.get(i))[0];
	}
	//判断是否在申请时间内
	sql="select jxjdm from view_bjly_jxjsjsz where nd = ? and xydm = ? and to_char(sysdate,'yyyymmdd')<=pjjsrq and to_char(sysdate,'yyyymmdd')>=pjksrq order by jxjdm";
	rstmp = dao.rsToVator(sql, new String[] {nd,xydm},  new String[] {"jxjdm"});
	String [] sqsjnjxjdm=new String[rstmp.size()+1];
	for(int i=0;i<rstmp.size();i++){
		sqsjnjxjdm[i]=((String[])rstmp.get(i))[0];
	}
	//得到已申请的奖学金代码
	sql="select jxjdm from jxjbjlysqb where xh = ? and nd =?";
	rstmp = dao.rsToVator(sql, new String[] {nd,xh},  new String[] {"jxjdm"});
	String [] sqjxjdm=new String[rstmp.size()+1];
	for(int i=0;i<rstmp.size();i++){
		sqjxjdm[i]=((String[])rstmp.get(i))[0];
	}
	//得到学生的总学分和挂课数
	sql = "select ndzxf,xfsum,ndgks,gkssum from jxjbjlyxfb where xh = ?";
	tmp = dao.getOneRs(sql, new String[] {xh}, new String[] {
		"ndzxf","xfsum","ndgks","gkssum" });
	Integer ndzxf = Integer.parseInt(tmp[0]);
	Integer xfsum = Integer.parseInt(tmp[1]);
	Integer ndgks = Integer.parseInt(tmp[2]);
	Integer gkssum = Integer.parseInt(tmp[3]);
	//得到不符合申请标准的奖学金代码
	sql="select jxjdm,tjzdm,tj from jxjhdtj";
	rstmp = dao.rsToVator(sql, new String[] {},  new String[] {"jxjdm","tjzdm","tj"});
	//创建HashMap里面存放的是未通过的奖学金代码
	HashMap<String, String> map = new HashMap<String, String>();
	for(int i=0;i<rstmp.size();i++){
		Integer bjzd = 0;
		String jxjdmTmp=((String[])rstmp.get(i))[0];
		String tjzdmTmp=((String[])rstmp.get(i))[1];
		String tjTmp=((String[])rstmp.get(i))[2];
		String [] temp = tjTmp.split("'");
		String bjfh = temp[0];
		Integer bjfz = Integer.parseInt(temp[1]);
		if(tjzdmTmp.equalsIgnoreCase("ndzxf")){
			bjzd=ndzxf;
		}else if(tjzdmTmp.equalsIgnoreCase("xfsum")){
			bjzd=xfsum;
		}else if(tjzdmTmp.equalsIgnoreCase("ndgks")){
			bjzd=ndgks;
		}else if(tjzdmTmp.equalsIgnoreCase("gkssum")){
			bjzd=gkssum;
		}
		if(bjfh.equalsIgnoreCase(">")){
			if(bjzd>bjfz){	
			}else{
				map.put(jxjdmTmp, "false");
			}
		}else if(bjfh.equalsIgnoreCase(">=")){
			if(bjzd>=bjfz){
			}else{
				map.put(jxjdmTmp, "false");
			}
		}else if(bjfh.equalsIgnoreCase("=")){
			if(bjzd.equals(bjfz)){
			}else{
				map.put(jxjdmTmp, "false");
			}
		}else if(bjfh.equalsIgnoreCase("<")){
			if(bjzd<bjfz){
			}else{
				map.put(jxjdmTmp, "false");
			}
		}else if(bjfh.equalsIgnoreCase("<=")){
			if(bjzd<=bjfz){
			}else{
				map.put(jxjdmTmp, "false");
			}
		}
	}
	//得到审核的奖学金代码
	sql="select c.jxjdm,nvl(b.gscs,'0') gscs,nvl(b.gsjg,'未审核') gsjg from jxjdmb_tmp_bjly c left join (select jxjdm,max(gscs) gscs from jxjbjlygsb where xh = ? and nd =? group by jxjdm) a  on a.jxjdm = c.jxjdm left join (select jxjdm,gscs,gsjg from jxjbjlygsb where xh = ? and nd =?) b on a.jxjdm=b.jxjdm and a.gscs = b.gscs ";
	rstmp = dao.rsToVator(sql, new String[] {nd,xh},  new String[] {"jxjdm","gscs","gsjg"});
	String [] jxjgsdm=new String[rstmp.size()+1];
	String [] gscs=new String[rstmp.size()+1];
	String [] gsjg=new String[rstmp.size()+1];
	for(int i=0;i<rstmp.size();i++){
		jxjgsdm[i]=((String[])rstmp.get(i))[0];
		gscs[i]=((String[])rstmp.get(i))[1];
		gsjg[i]=((String[])rstmp.get(i))[2];
	}
	//综合起来放入map中
	return mapping.findForward("success");
}


private ActionForward pjpy_jxjsjsz_one(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {
//	String currNd = Base.currNd;
	DAO basicDao = DAO.getInstance();  
	PjpyForBjlyDAO dao = new PjpyForBjlyDAO();
	String pk = request.getParameter("pk");
	HashMap<String, String> map = new HashMap<String, String>();
	String sql = "select jxjdm,jxjmc,nd,xydm,pjksrq,pjjsrq,replace(xffzbfb,'%','') xffzbfb,replace(ckfz1bfb,'%','') ckfz1bfb,replace(ckfz2bfb,'%','') ckfz2bfb,replace(ckfz3bfb,'%','') ckfz3bfb from view_bjly_jxjsjsz where pk=?";
	String[] cols = {"jxjdm","jxjmc","nd","xydm","pjksrq","pjjsrq","xffzbfb","ckfz1bfb","ckfz2bfb","ckfz3bfb"};
	String[] vals = basicDao.getOneRs(sql, new String[]{pk},cols);
	for(int i=0;i<cols.length;i++){
		map.put(cols[i], (vals!=null)? vals[i]:"");
	}
	String jxjdm = map.get("jxjdm");
	String nd = map.get("nd");
	sql = "select lbdm,pjksrq,pjjsrq  from view_bjly_jxjsz where jxjdm=? and nd = ?";
	cols = new String[]{"lbdm","pjksrq","pjjsrq"};
	vals = basicDao.getOneRs(sql, new String[]{jxjdm,nd},cols);
	map.put("lbdm", vals[0]);
	map.put("xxpjksrq", vals[1]);
	map.put("xxpjjsrq", vals[2]);
	request.setAttribute("rs", map);
	request.setAttribute("pk", pk);
	request.setAttribute("jxjlbList", dao.getjxjlbList() );// 发送奖学金类别列表
	request.setAttribute("jxjList", dao.getjxjList());// 发送奖学金列表
	request.setAttribute("njList", basicDao.getXnndList());// 发送年级列表
	return new ActionForward("/pjpy/bjly/pjpy_jxjsjsz_one.jsp",false);
	}

private ActionForward pjpy_jxjsjsz_save(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) throws Exception {
//	PjpyForBjlyDAO dao = new PjpyForBjlyDAO();
	String pk = DealString.toGBK(request.getParameter("pk"));
	String pjksrq = DealString.toGBK(request.getParameter("pjksrq"));
	String pjjsrq = DealString.toGBK(request.getParameter("pjjsrq"));
	String xffzbfb = DealString.toGBK(request.getParameter("xffzbfb"));
	String ckfz1bfb = DealString.toGBK(request.getParameter("ckfz1bfb"));
	String ckfz2bfb = DealString.toGBK(request.getParameter("ckfz2bfb"));
	String ckfz3bfb = DealString.toGBK(request.getParameter("ckfz3bfb"));
	String[] tablecol = {"pjksrq","pjjsrq","xffzbfb","ckfz1bfb","ckfz2bfb","ckfz3bfb"};
	String[] values = {pjksrq,pjjsrq,xffzbfb+"%",ckfz1bfb+"%",ckfz2bfb+"%",ckfz3bfb+"%"};
	StandardOperation.update("jxjsjxyszb",tablecol,values,"jxjdm||nd||xydm",pk,request);
	return  null;
	}

private ActionForward pjpy_jxjsjsz(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
	//初始化页面，返回查询信息
	DAO basicDao = DAO.getInstance();
 	String currXn = Base.currXn;
	String currNd = Base.currNd;
	CommanForm priseForm = (CommanForm) form;
	PjpyForBjlyDAO dao = new PjpyForBjlyDAO();
	Vector<Object> rs = new Vector<Object>();
	String[] colList = null;
	String[] colListCN = null;
	String xy = priseForm.getXydm();
	HttpSession session = request.getSession();
	String userType = session.getAttribute("userType").toString();
	String userDep = session.getAttribute("userDep").toString();
	if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("xx")){
		if( request.getParameter("xydm")!=null){
			userDep =  request.getParameter("xydm");
		}else{
			userDep	= "";
		}
	}
	if(userType.equalsIgnoreCase("xy") && (xy == null || xy.equalsIgnoreCase(""))){
		xy = userDep;
		priseForm.setXydm(xy);
	}
	String sql = "";// sql语句
	String querry = " where 1=1 ";// sql条件
	String tips = "";// 位置提示信息
	String tableName = "";// 查询信息源（多为视图名）
	String rsNum = "0";// 返回的记录数
	String realTable = "";// 数据源表
	String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
	String writeAble = "yes";// 写权限
	String dataType = request.getParameter("act");
	String lbdm = priseForm.getJllb();
	lbdm = (lbdm == null) ? "" : lbdm;
	String xydm = priseForm.getXydm();
	if (isNull(xydm)) {
	    querry += "and 1=1 ";
	} else {
	    querry += "and xydm = '" + xydm + "' ";
	}
	
	xydm = isNull(xydm) ? "all" : xydm;
	String jxjdm = priseForm.getXmdm();
	if (dataType == null) {
	    dataType = "";
	}
	querry += " and (nd = '" + currNd + "' or nd is null )";
	if (isNull(jxjdm)) {
	    querry += "and 1=1 ";
	} else {
	    querry += "and jxjdm = '" + jxjdm + "' ";
	}
	sql = "select pk,jxjdm,jxjmc,nd,pjksrq,pjjsrq,xymc,xffzbfb,ckfz1bfb ckfz1bfb,ckfz2bfb ckfz2bfb,ckfz3bfb ckfz3bfb from view_bjly_jxjsjsz";
	sql+=querry;
	realTable = "view_bjly_jxjsjsz";
	pk = "jxjdm||nd||xydm";
	tips = "评奖评优 - 参数设置 - 奖学金"+ Base.YXPZXY_KEY+"设置";
	tableName = "view_bjly_jxjsjsz";
	colList = new String[] {"pk","jxjdm","jxjmc","nd","pjksrq","pjjsrq","xymc","xffzbfb","ckfz1bfb","ckfz2bfb","ckfz3bfb"};
	colListCN = basicDao.getColumnNameCN(colList, tableName);
	List topTr = basicDao.arrayToList(colList, colListCN);
	if ((request.getParameter("go") != null)
		&& request.getParameter("go").equalsIgnoreCase("go")) {
	    rs.addAll(basicDao.rsToVator(sql, new String[] {}, colList));
	    if (rs == null) {
		rsNum = "0";
	    } else {
		rsNum = String.valueOf(rs.size());
	    }
	}
	priseForm.setXn(currXn);
	priseForm.setNd(currNd);
	request.setAttribute("writeAble", writeAble);// 发送读写权限
	request.setAttribute("tableName", tableName);// 发送视图名
	request.setAttribute("realTable", realTable);// 发送数据源表名
	request.setAttribute("pk", pk);// 发送数据源表主键
	request.setAttribute("act", dataType);// 发送数据查询类型
	request.setAttribute("tips", tips);// 发送位置提示栏信息
	request.setAttribute("jxjList", dao.getjxjList());//发送奖学金列表
	request.setAttribute("njList", basicDao.getNjList());//发送年级列表
	request.setAttribute("xnList", basicDao.getXnndList());//发送学年列表
	request.setAttribute("rs", rs);//发送数据集
	request.setAttribute("topTr", topTr);//发送表头
	request.setAttribute("rsNum", rsNum);//发送记录数
	request.setAttribute("xyList", basicDao.getXyList());
	return mapping.findForward("success");
	}

private ActionForward pjpy_jxjsz_save(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
	DAO basicDao = DAO.getInstance();
	PjpyForBjlyDAO dao = new PjpyForBjlyDAO();
	String jxjdm = DealString.toGBK(request.getParameter("pk"));
	String nd = DealString.toGBK(request.getParameter("jxjNd"));
	String lbdm = DealString.toGBK(request.getParameter("lbdm"));
	String sfxdpks = DealString.toGBK(request.getParameter("sfxdpks"));
	String pjksrq = DealString.toGBK(request.getParameter("pjksrq"));
	String pjjsrq = DealString.toGBK(request.getParameter("pjjsrq"));
	String jxjje = DealString.toGBK(request.getParameter("jxjje"));
	String jxjxxsm = DealString.toGBK(request.getParameter("jxjxxsm"));
	boolean del = false;
	del = dao.doDelete("jxjndglsdb"," where jxjdm = ? and nd = ?",new String[]{jxjdm,nd});
	if(del){
		String[] tablecol = {"jxjdm","nd","lbdm","sfxdpks","pjksrq","pjjsrq","jxjje","jxjxxsm"};
		String[] values = {jxjdm,nd,lbdm,sfxdpks,pjksrq,pjjsrq,jxjje,jxjxxsm};
		StandardOperation.insert("jxjndglsdb",tablecol,values,request);
		//初始化学院奖学金设置
		del = dao.doDelete("jxjsjxyszb"," where jxjdm = ? and nd = ?",new String[]{jxjdm,nd});
		if(del){
			String sql = "select distinct xydm from view_njxyzybj order by xydm";
			ArrayList<String[]> xydms = basicDao.rsToVator(sql, new String[]{}, new String[]{"xydm"});
			tablecol = new String[]{"jxjdm","xydm","nd","pjksrq","pjjsrq"};
			for(int i=0;i<xydms.size();i++){
			values =  new String[] {jxjdm,(xydms.get(i))[0],nd,pjksrq,pjjsrq};
			StandardOperation.insert("jxjsjxyszb",tablecol,values,request);
			}
		}
	}
	return  null;
}

private ActionForward pjpy_jxjsz_one(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
	    DAO basicDao = DAO.getInstance();
		String currNd = Base.currNd;
		String pk = request.getParameter("pk");
		PjpyForBjlyDAO dao = new PjpyForBjlyDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select jxjdm,jxjmc,nd,pjksrq,pjjsrq,lbmc,lbdm,jxjje,sfxdpks,jxjxxsm from view_bjly_jxjsz where jxjdm=? and nd = ?";
		String[] cols = {"jxjdm","jxjmc","nd","pjksrq","pjjsrq","lbmc","lbdm","jxjje","sfxdpks","jxjxxsm"};
		String[] vals = basicDao.getOneRs(sql, new String[]{pk,currNd},cols);
		if(vals==null){
			vals = basicDao.getOneRs(sql, new String[]{pk,((Integer)(Integer.parseInt(currNd)-1)).toString()},cols);
		}
		for(int i=0;i<cols.length;i++){
			map.put(cols[i], (vals!=null)? vals[i]:"");
		}
		map.put("jxjdm", pk);
		map.put("nd", currNd);
		request.setAttribute("rs", map);
		request.setAttribute("jxjlbList", dao.getjxjlbList() );// 发送奖学金类别列表
		request.setAttribute("jxjList", dao.getjxjList());// 发送奖学金列表
		request.setAttribute("njList", basicDao.getXnndList());// 发送年级列表
		request.setAttribute("sfpksList", basicDao.getChkList(2));// 发送年级列表
		return new ActionForward("/pjpy/bjly/pjpy_jxjsz_one.jsp",false);
	}

private ActionForward pjpy_jxjsz_delComman(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) throws Exception {
	DAO basicDao = DAO.getInstance();
	String realTable = request.getParameter("realTable");
	String currNd = Base.currNd;
	String pk = request.getParameter("pk");
	String sql = "delete from "+realTable;
	String query = "";
	boolean rs = false;
	
//	ActionForward actionForward = null;
	if(realTable.equalsIgnoreCase("jxjndglsdb")){
		query = " where jxjdm = ? and nd = ?";
		sql+=query;
		rs = basicDao.runUpdate(sql, new String[]{pk,currNd});
		if(rs){
		    priseConf(mapping,form,request,response);
		}else{
			return new ActionForward("/login.jsp",false);
	    }
    }
	return priseConf(mapping,form,request,response);
	}

private ActionForward priseConf(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	// 初始化页面，返回查询信息
     	String currXn = Base.currXn;
    	String currNd = Base.currNd;
	CommanForm priseForm = (CommanForm) form;
	PjpyForBjlyDAO dao = new PjpyForBjlyDAO();
	DAO basicDao = DAO.getInstance();
	Vector<Object> rs = new Vector<Object>();
	String[] colList = null;
	String[] colListCN = null;
//	HttpSession session = request.getSession();
	String sql = "";// sql语句
	String querry = " where 1=1 ";// sql条件
	String tips = "";// 位置提示信息
	String tableName = "";// 查询信息源（多为视图名）
	String rsNum = "0";// 返回的记录数
	String realTable = "";// 数据源表
	String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
	String writeAble = "yes";// 写权限
	String dataType = request.getParameter("act");
	String lbdm = priseForm.getJllb();
	lbdm = (lbdm == null) ? "" : lbdm;
	String xydm = priseForm.getXydm();

	xydm = isNull(xydm) ? "all" : xydm;
	String jxjdm = priseForm.getXmdm();
	if (dataType == null) {
	    dataType = "";
	}
	querry += " and (nd = '" + currNd + "' or nd is null )";
	if (isNull(jxjdm)) {
	    querry += "and 1=1 ";
	} else {
	    querry += "and pk = '" + jxjdm + "' ";
	}
	sql = "select pk,jxjdm,jxjmc,nd,pjksrq,pjjsrq,lbmc,jxjje,sfxdpks from view_bjly_jxjsz";
	sql+=querry;
	realTable = "jxjndglsdb";
	pk = "jxjdm||nd";
	tips = "评奖评优 - 参数设置 - 奖学金设置";
	tableName = "view_bjly_jxjsz";
	colList = new String[] { "pk", "jxjdm","jxjmc", "nd", "pjksrq", "pjjsrq", "lbmc","jxjje",
		"sfxdpks" };
	colListCN = basicDao.getColumnNameCN(colList, tableName);
	List topTr = basicDao.arrayToList(colList, colListCN);
	if ((request.getParameter("go") != null)
		&& request.getParameter("go").equalsIgnoreCase("go")) {
	    rs.addAll(basicDao.rsToVator(sql, new String[] {}, colList));
	    if (rs == null) {
		rsNum = "0";
	    } else {
		rsNum = String.valueOf(rs.size());
	    }
	}
	priseForm.setXn(currXn);
	priseForm.setNd(currNd);
	request.setAttribute("writeAble", writeAble);// 发送读写权限
	request.setAttribute("tableName", tableName);// 发送视图名
	request.setAttribute("realTable", realTable);// 发送数据源表名
	request.setAttribute("pk", pk);// 发送数据源表主键
	request.setAttribute("act", dataType);// 发送数据查询类型
	request.setAttribute("tips", tips);// 发送位置提示栏信息
	request.setAttribute("jxjList", dao.getjxjList());// 发送奖学金列表
	request.setAttribute("njList", basicDao.getNjList());// 发送年级列表
	request.setAttribute("xnList", basicDao.getXnndList());// 发送学年列表
	request.setAttribute("rs", rs);// 发送数据集
	request.setAttribute("topTr", topTr);// 发送表头
	request.setAttribute("rsNum", rsNum);// 发送记录数
	return mapping.findForward("success");
    }
}
