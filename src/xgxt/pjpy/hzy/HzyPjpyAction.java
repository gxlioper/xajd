
package xgxt.pjpy.hzy;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import common.Globals;
import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Arrays2;
import xgxt.utils.FormModleCommon;
import xgxt.utils.RowidToPk;
import xgxt.utils.String.StringUtils;
import xgxt.DAO.SxjyDAO;
import xgxt.wjcf.wjcfutils.CommonAction;
public class HzyPjpyAction extends DispatchAction{
	public ActionForward dxjxjsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO daoo = DAO.getInstance();
		SxjyDAO dao = new SxjyDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<String[]> cjrs = new ArrayList<String[]>();
		String pkValue = request.getParameter("pk");
		String [] colList = new String []{"xh","bjmc","xm","xymc","xn","xq","xm","drzw","jxjmc","cjmc","zhpfmc","jfqk","fdyyj","xyshyj","xxshyj","jxjdm","xyshsj","fdyqm","xyqm", "xxshsj"};
		map = dao.sxjyQueryOne("view_xsjxjb", colList, "xn||nd||xh||jxjdm", pkValue);
		if (map == null || StringUtils.isNull(map.get("xh")))  {
			map = dao.sxjyQueryOne("view_xsjxjb", colList, "xn||nd||jxjdm||xh", pkValue);
		}
		String xh = map.get("xh");
		String[] jxjsqxnnd = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
		if (jxjsqxnnd == null) {
			jxjsqxnnd[0] = "";
			jxjsqxnnd[1] = "";
			jxjsqxnnd[2] = "";
		}
		String query = " where xh = ? and xn='" + jxjsqxnnd[0] +"' and xq='" +jxjsqxnnd[2]+ "' ";
		colList = new String []{"kcsbm","cj","kcxz"};
		cjrs = dao.sxjyQuery("cjb", query, new String []{xh}, colList, "");
		String [] cjList = null;
		int cjrsLength = cjrs.size()+1;
		for(int i=1;i<21;i++){
			if(i<cjrsLength){
				cjList = cjrs.get(i-1);
				map.put("kcmc"+i, cjList[0].replace("(选)", "")+"("+cjList[2]+")");
				map.put("kccj"+i, cjList[1]);
			}else{
				map.put("kcmc"+i, "");
				map.put("kccj"+i, "");
			}
		}
		String sql = "select b.xm from view_xsjbxx a left join view_fdybjdz b on a.bjdm = b.bjdm where a.xh = ?";
		map.put("bzrxm", daoo.getOneRs(sql,new String []{xh},"xm"));
		String jxjdm = map.get("jxjdm");
		if(jxjdm.equalsIgnoreCase("024")||jxjdm.equalsIgnoreCase("025")||jxjdm.equalsIgnoreCase("026")||jxjdm.equalsIgnoreCase("027")){
			map.put("title", "优秀学生奖学金");
		}else{
			map.put("title", "单项奖学金审批表");
		}
		if (!StringUtils.isNull(map.get("xq")) && StringUtils.isEqual(map.get("xq"), "03")) {
			map.put("xq", "第一学期");
		} else {
			map.put("xq", "第二学期");
		}
		String xyshsj = map.get("xyshsj");
		String xxshsj = map.get("xxshsj");
		map.put("xyshyear", StringUtils.isNull(xyshsj) ? "" : xyshsj.substring(0, 4));//学院审核年份
		map.put("xyshmon", StringUtils.isNull(xyshsj) ? "" : xyshsj.substring(4, 6));//学院审核月份
		map.put("xyshdate", StringUtils.isNull(xyshsj) ? "" : xyshsj.substring(6, 8));//学院审核日

		map.put("xxshyear", StringUtils.isNull(xxshsj) ? "" : xxshsj.substring(0, 4));//学校审核年份
		map.put("xxshmon", StringUtils.isNull(xxshsj) ? "" : xxshsj.substring(4, 6));//学校审核月份
		map.put("xxshdate", StringUtils.isNull(xxshsj) ? "" : xxshsj.substring(6, 8));//学校审核日
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	/**
	 * 杭职院集体项目特别奖励申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param responses
	 * @return
	 */
	public ActionForward jtxmtbjlsq(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		// TODO 包括申请页面的显示和保存功能
		String doType = request.getParameter("doType");
//		HzyPjpyForm myForm = (HzyPjpyForm) form;
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		String bmmc = session.getAttribute("bmmc").toString();
		DAO utilDao = DAO.getInstance();
		String xn = utilDao.getConf(2);
		String xq = utilDao.getConf(6);
		HzyPjpyDAO dao = new HzyPjpyDAO();
		List<HashMap<String, String>> rs = null;
		HashMap<String, String> rs1 = null;
		if(doType==null || "".equals(doType)){
			//不保存时，包括第一次登录和申请之后的查看信息
			rs = dao.getCjryList(utilDao, xn, xq, bmdm,null);//取出参加人员
			rs1 = dao.getHdxx(utilDao, xn, xq, bmdm,null);
		}
		request.setAttribute("bmmc", bmmc);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", rs1);
		return mapping.findForward("success");
	}

	public ActionForward saveJtxmtbjlsq(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		//保存操作
		//HttpServletRequest request, HzyPjpyForm myForm, String bmdm, DAO utilDao, String xn, String xq, HzyPjpyDAO dao
		HzyPjpyForm myForm = (HzyPjpyForm) form;
		DAO utilDao = DAO.getInstance();
		String xn = utilDao.getConf(2);
		String xq = utilDao.getConf(6);
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		HzyPjpyDAO dao = new HzyPjpyDAO();
		String hdsj = myForm.getHdsj();
		String hdmc = myForm.getHdmc();
		String hjmc = myForm.getHjmc();
		String sqje = myForm.getSqje();
		String xyyj = myForm.getXyyj();
		String xxyj = myForm.getXxyj();
		String xscyj = myForm.getXscyj();

		String[] xmArr = myForm.getXm();
		String[] bjArr = myForm.getBjmc();
		StringBuffer bjlb = new StringBuffer("");
		StringBuffer cjry = new StringBuffer("");
		for(int i=0;i<xmArr.length;i++){
			bjlb.append(i==xmArr.length-1?bjArr[i]:bjArr[i]+"!!");
			cjry.append(i==xmArr.length-1?xmArr[i]:xmArr[i]+"!!");
		}
		String[] cols = {"xn","xq","bmdm","hdmc","hdsj","hjmc","sqje","bjlb","cjry","xyyj","xxyj","xscyj"};
		String[] vals = {xn,xq,bmdm,hdmc,hdsj,hjmc,sqje,bjlb.toString(),cjry.toString(),xyyj,xxyj,xscyj};
		Arrays2.toGBKArray(vals);
		String primarykey = "xn||xq||bmdm||hdmc";
		String pkVal = xn+xq+bmdm+hdmc;
		boolean saveFlag = dao.saveJtxmtbjlsqDML(utilDao, cols, vals,primarykey,pkVal,request);
		List<HashMap<String, String>> rs = dao.getCjryList(utilDao, xn, xq, bmdm,hdmc);
		HashMap<String, String> rs1 = dao.getHdxx(utilDao, xn, xq, bmdm,hdmc);
		//TODO 处理保存结果到页面
		request.setAttribute("saveFlag", saveFlag);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", rs1);
		return mapping.findForward("success");
	}

	public ActionForward xjbjAndWmbjSq(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HzyPjpyForm myForm = (HzyPjpyForm) form;
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		//学生用户无权进入该页面
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(userType)) {
			String msg = "您无权访问该页面，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		String userDep = (String) session.getAttribute("userDep");
		String mkType  = request.getParameter("mkType");
		String doType  = request.getParameter("doType");	
		myForm.setZysj(DealString.toGBK(request.getParameter("zysj")));
		myForm.setBzxm(DealString.toGBK(request.getParameter("bzxm")));
		myForm.setBzr(DealString.toGBK(request.getParameter("bzr")));
		myForm.setXyyj(DealString.toGBK(request.getParameter("xyyj")));
		myForm.setTzs(DealString.toGBK(request.getParameter("tzs")));
		myForm.setBjry(DealString.toGBK(request.getParameter("bjry")));
		myForm.setJtch(DealString.toGBK(request.getParameter("jtch")));
		myForm.setBhgqs(DealString.toGBK(request.getParameter("bhgqs")));
		myForm.setYwcf(DealString.toGBK(request.getParameter("ywcf")));
		if(request.getParameter("xn")==null){
			myForm.setXn(Base.getJxjsqxn());
		}
		if(request.getParameter("xq")==null){
			myForm.setXq(Base.getJxjsqxq());
		}		
		
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
			return new ActionForward("/ynys_xjbjsq.do", false);
		}
		
		if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){
			return new ActionForward("/zjlgPjpy.do?method=xjbjSq",false);
		}	
		
		//TODO 宁波天一职业技术学院   文明班级申请
		if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){
			return new ActionForward("/nbtyWmbj.do?method=nbtySqWmbj",false);
		}

		String[] titleEn = getTitleEn();
		String[] titleCn = getTitleCn();
		ArrayList<HashMap<String, String>> titleList = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<titleEn.length;i++){
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", titleEn[i]);
			tempMap.put("cn", titleCn[i]);
			titleList.add(tempMap);
		}
		String defaultTitle = "";
		if(mkType!=null){
			defaultTitle = mkType;
		}else{
			defaultTitle = titleEn[0];
		}				
		String zyKey = "";
		String bjKey = "!!!!";
		if("xy".equalsIgnoreCase(userType)){
			zyKey = userDep;
			bjKey = zyKey+"!!"+""+"!!"+"";
		}

		if("save".equalsIgnoreCase(doType)){			
			boolean  result = false;
			String titName = request.getParameter("titName");
			if("xjbj".equalsIgnoreCase(titName)){					
				result = xjbjWmbjSqSave(myForm, request, defaultTitle);
			}else if("wmbj".equalsIgnoreCase(titName)){							
				result = xjbjWmbjSqSave(myForm, request, defaultTitle);
			}else if("xjtzb".equalsIgnoreCase(titName)){							
				result = xjtzbSqSave(myForm, request);
				String pkValue = request.getParameter("xn")+request.getParameter("xq")+request.getParameter("tzbmc");
				request.setAttribute("rswj",GetXjtzbFileList(pkValue,titName));
			}else if("xjtzz".equalsIgnoreCase(titName)){								
				result = xjtzzSqSave(myForm, request);
				String pkValue = request.getParameter("xn")+request.getParameter("xq")+request.getParameter("tzzmc");
				request.setAttribute("rswj",GetXjtzbFileList(pkValue,titName));
			}
//			myForm.setBzr(DealString.toGBK(myForm.getBzr()));
//			myForm.setBzxm(DealString.toGBK(myForm.getBzxm()));
//			myForm.setZysj(DealString.toGBK(myForm.getZysj()));
			request.setAttribute("result",result);
			defaultTitle = titName;
		}
		
		String bjdm = request.getParameter("bjdm");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (StringUtils.isNotNull(bjdm)) {
			DAO dao = DAO.getInstance();
			rs = dao.getMapNotOut("select bjrs,bjdm,(select xm from view_bjgbxx b where a.bjdm=b.bjdm and rownum=1) bzxm from (select count(*) bjrs,bjdm from view_xsjbxx a where bjdm=? group by bjdm) a", new String[]{bjdm});
			rs.putAll(dao.getMapNotOut("select b.xm bzrxm from bzrbbb a,yhb b where b.yhm=a.zgh and a.bjdm=? and rownum=1", new String[]{bjdm}));
			request.setAttribute("bjrs", rs);
		}

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("defaultTitle", defaultTitle);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("titleList", titleList);		
		if("xjtzb".equalsIgnoreCase(defaultTitle)){//先进团支部
			return mapping.findForward("hzy_xjtzb");
		}else if("xjtzz".equalsIgnoreCase(defaultTitle)){//先进团总支
			return mapping.findForward("hzy_xjtzz");
		}
		return mapping.findForward("success");
	}
	public boolean xjbjWmbjSqSave(HzyPjpyForm myForm,HttpServletRequest request,String defaultTitle){
		Boolean result = false;
		HzyPjpyDAO dao = new HzyPjpyDAO(); 
		String bjdm = myForm.getBjdm();
		String bzxm = myForm.getBzxm();
		String xsrs = myForm.getXsrs();		
		String bzr =myForm.getBzr();
		String zysj = myForm.getZysj();//主要事迹
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		String xyyj = myForm.getXyyj();
		String tzs = myForm.getTzs();
		String bhgqs = myForm.getBhgqs();
		String ywcf = myForm.getYwcf();
		String bjry = myForm.getBjry();
		String jtch = myForm.getJtch();

		String rychdm = (defaultTitle.equalsIgnoreCase("xjbj"))?"00001":(defaultTitle.equalsIgnoreCase("wmbj")?"00002":"");
		String[] columns = {"xn","xq","rychdm","bjdm","bzxm","xsrs","bzr","zysj", "xyyj", "jtch"};
		String[] values = {xn,xq,rychdm,bjdm,bzxm,xsrs,bzr,zysj,xyyj,jtch};
		//宁波职业先进班级保存
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(Base.xxdm)) {
			xn = myForm.getXn();
			columns = new String[]{"xn","xq","rychdm","bjdm","bzxm","xsrs","bzr","tzs","bhgqs","ywcf","bjry","zysj"};
			values = new String[]{xn,xq,rychdm,bjdm,bzxm,xsrs,bzr,tzs,bhgqs,ywcf,bjry,zysj};
		}
		String primaryKey = "xn||xq||rychdm||bjdm";
		String primaryKeyValue = xn+xq+rychdm+bjdm;
		result = dao.saveXjbjAndWmbjSq("pjpy_xjbjandwmsqb", primaryKey, primaryKeyValue, columns, values, request);//数据保存		
		return result ;
	}
	/**
	 *先进团支部推荐保存
	 */
	public boolean xjtzbSqSave(HzyPjpyForm myForm,HttpServletRequest request) throws Exception{
//		保存数据
		DAO dao = DAO.getInstance();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String ftys = myForm.getFtys();
		String zysj =  myForm.getTzbzysj();
		String tzbmc =  myForm.getTzbmc();
		String tzbsj = myForm.getTzbsj();
		String tys    = myForm.getTys();
		String xydm   = myForm.getXydm();

		String[] fileInfo = upFile(request, myForm, xn, xq);
		String filePath = fileInfo[0];
		String wjm      = fileInfo[1];   	   
		//提交数据保存
		boolean done = false;

		String pkValue = xn+xq+tzbmc;
		String pk = "xn||xq||xydm||tzbmc";
		String flag = dao.returntag("select * from xjtzbb where  "+pk+"=?", new String[]{pkValue});

		String sql = " insert into xjtzbb(xn,xq,tzbmc,xydm,tzbsj,tys,ftys,zysj,wjlj,wjm)values(?,?,?,?,?,?,?,?,?,?)";
		if("empty".equalsIgnoreCase(flag)){//插入
			done = dao.runUpdate(sql,new String[]{xn,xq,tzbmc,xydm,tzbsj,tys,ftys,zysj,filePath,wjm});
		}else{//修改
			if(Base.isNull(wjm)){
				sql = " update xjtzbb set xydm,tzbsj=?,tys=?,ftys=?,zysj=?  where  "+pk+"=?";
				done = dao.runUpdate(sql,new String[]{xydm,tzbsj,tys,ftys,zysj,pkValue});
			}else{
				sql = " update xjtzbb set xydm,tzbsj=?,tys=?,ftys=?,zysj=?,wjlj=?,wjm=? where  "+pk+"=?";
				done = dao.runUpdate(sql,new String[]{xydm,tzbsj,tys,ftys,zysj,filePath,wjm,pkValue});
			}			
		}
		return done;
	}
	/**
	 *先进团总支推荐保存
	 */
	public boolean xjtzzSqSave(HzyPjpyForm myForm,HttpServletRequest request) throws Exception{
//		保存数据

		DAO dao = DAO.getInstance();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String tys = myForm.getTys();
		String zysj =myForm.getTzzzysj();
		String tzzmc = myForm.getTzzmc();
		String tzzsj = myForm.getTzzsj();
		String tzbs    = myForm.getTzbs();
		String xydm   = myForm.getXydm();
		String[] fileInfo = upFile(request, myForm, xn, xq);
		String filePath = fileInfo[0];
		String wjm      = fileInfo[1];
		//提交数据保存
		boolean done = false;

		String pkValue = xn+xq+tzzmc;
		String pk = "xn||xq||xydm||tzzmc";
		String flag = dao.returntag("select * from XJTZZB where  "+pk+"=?", new String[]{pkValue});

		String sql = " insert into xjtzzb(xn,xq,tzzmc,xydm,tzzsj,tzbs,tys,zysj,wjlj,wjm)values(?,?,?,?,?,?,?,?,?,?)";
		if("empty".equalsIgnoreCase(flag)){//插入
			done = dao.runUpdate(sql,new String[]{xn,xq,tzzmc,xydm,tzzsj,tzbs,tys,zysj,filePath,wjm});
		}else{//修改
			if(Base.isNull(wjm)){
				sql = " update xjtzzb set xydm=?,tzzsj=?,tzbs=?,tys=?,zysj=?  where  "+pk+"=?";
				done = dao.runUpdate(sql,new String[]{tzzsj,tzbs,tys,zysj,pkValue});
			}else{
				sql = " update xjtzzb set xydm=?,tzzsj=?,tzbs=?,tys=?,zysj=? ,wjlj=?,wjm=? where  "+pk+"=?";
				done = dao.runUpdate(sql,new String[]{xydm,tzzsj,tzbs,tys,zysj,filePath,wjm,pkValue});
			}

		}
		return done;
	}
	public  String[] getTitleEn(){
		String[] titleEn = {"xjbj","wmbj"};
		if(Globals.XXDM_HZZY.equalsIgnoreCase(Base.xxdm)){
			titleEn = new String[]{"xjbj","wmbj","xjtzb","xjtzz"};			
		}
		return titleEn;
	}
	public  String[] getTitleCn(){		
		String[] titleCn = {"先进班级","文明班级"};
		if(Globals.XXDM_HZZY.equalsIgnoreCase(Base.xxdm)){			
			titleCn = new String[]{"先进班级","文明班级","先进团支部","先进团总支"};
		}
		return titleCn;
	}    
	/**
	public ActionForward xjbjAndWmbjSqSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HzyPjpyForm myForm = (HzyPjpyForm) form;
		HzyPjpyDAO dao = new HzyPjpyDAO();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String mkType  = request.getParameter("mkType");
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		String xxdm = StandardOperation.getXxdm();
		String[] titleEn = getTitleEn();
		String[] titleCn = getTitleCn();
		ArrayList<HashMap<String, String>> titleList = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<titleEn.length;i++){
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", titleEn[i]);
			tempMap.put("cn", titleCn[i]);
			titleList.add(tempMap);
		}
		String defaultTitle = "";
		if(mkType!=null){
			defaultTitle = mkType;
		}else{
			defaultTitle = titleEn[0];
		}		
		String bjdm = myForm.getBjdm();
		String bzxm = DealString.toGBK(myForm.getBzxm());
		String xsrs = myForm.getXsrs();
		String bzr = DealString.toGBK(myForm.getBzr());
		String zysj = DealString.toGBK(myForm.getZysj());//主要事迹
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		String xyyj = DealString.toGBK(myForm.getXyyj());
		String tzs = DealString.toGBK(myForm.getTzs());
		String bhgqs = myForm.getBhgqs();
		String ywcf = myForm.getYwcf();
		String bjry = DealString.toGBK(myForm.getBjry());
		String jtch = DealString.toGBK(myForm.getJtch());
		//String dzzyj = myForm.getXxyj();
		//String tableName = "";
		//String nd
		String rychdm = (defaultTitle.equalsIgnoreCase("xjbj"))?"00001":(defaultTitle.equalsIgnoreCase("wmbj")?"00002":"");
		String[] columns = {"xn","xq","rychdm","bjdm","bzxm","xsrs","bzr","zysj", "xyyj", "jtch"};
		String[] values = {xn,xq,rychdm,bjdm,bzxm,xsrs,bzr,zysj,xyyj,jtch};
		//宁波职业先进班级保存
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBZYJSXY)) {
			xn = myForm.getXn();
			columns = new String[]{"xn","xq","rychdm","bjdm","bzxm","xsrs","bzr","tzs","bhgqs","ywcf","bjry","zysj"};
			values = new String[]{xn,xq,rychdm,bjdm,bzxm,xsrs,bzr,tzs,bhgqs,ywcf,bjry,zysj};
		}
		String primaryKey = "xn||xq||rychdm||bjdm";
		String primaryKeyValue = xn+xq+rychdm+bjdm;
		request.setAttribute("result", dao.saveXjbjAndWmbjSq("pjpy_xjbjandwmsqb", primaryKey, primaryKeyValue, columns, values, request));
		myForm.setBjdm(bjdm);
		myForm.setBzr(bzr);
		myForm.setBzxm(bzxm);
		myForm.setZysj(zysj);
		myForm.setXsrs(xsrs);
		myForm.setTzs(tzs);
		myForm.setBhgqs(bhgqs);
		myForm.setYwcf(ywcf);
		myForm.setBjry(bjry);
		myForm.setJtch(jtch);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(""));
		request.setAttribute("bjList", Base.getBjMap().get("!!!!"));
		request.setAttribute("defaultTitle", defaultTitle);
		request.setAttribute("titleList", titleList);
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("success");
	}
	 */
	public ActionForward xjbjAndWmbjSqPrint(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HzyPjpyForm myForm = (HzyPjpyForm) form;
		HttpSession session = request.getSession();
		String xxmc = session.getAttribute("xxmc").toString();
		HzyPjpyDAO dao = new HzyPjpyDAO();
		DAO daoo = DAO.getInstance();
		String defaultTitle = request.getParameter("titName");		
		String actionFwd = "success";
		if("xjtzb".equalsIgnoreCase(defaultTitle)){
			String pkValue = request.getParameter("pkValue");
			String xn = myForm.getXn();
			String tzbmc = myForm.getTzbmc();
			String tzbsj = myForm.getTzbsj();
			String  tys  = myForm.getTys();
			String ftys  = myForm.getFtys();
			String zysj  = myForm.getZysj();
			if(!Base.isNull(pkValue)){
				HashMap<String,String> map = daoo.getMap("select xn,xq,tzbmc,xydm,tzbsj,tys,ftys,zysj from xjtzbb where rowid=?  ",
						new String[]{RowidToPk.rowidToPK(pkValue)},new String[]{ "xn","xq","tzbmc","xydm","tzbsj","tys","ftys","zysj"});
				xn = map.get("xn");
				tzbmc = map.get("tzbmc");
				tzbsj = map.get("tzbsj");
				 tys  = map.get("tys");
				ftys  = map.get("ftys");
				zysj  = map.get("zysj");
			}
			request.setAttribute("xn",xn);
			request.setAttribute("tzbmc",tzbmc);
			request.setAttribute("tzbsj",tzbsj);
			request.setAttribute("tys",tys);
			request.setAttribute("ftys",ftys);
			request.setAttribute("zysj",zysj);			
			actionFwd = "xjtzbPrint";		
		}else if("xjtzz".equalsIgnoreCase(defaultTitle)){
			String pkValue = request.getParameter("pkValue");
			String xn = myForm.getXn();
			String tzzmc = myForm.getTzzmc();
			String tzbs = myForm.getTzbs();
			String  tzzsj  = myForm.getTzzsj();
			String tys  = myForm.getTys();
			String zysj  = myForm.getZysj();
			if(!Base.isNull(pkValue)){
				HashMap<String,String> map = daoo.getMap("select xn,xq,tzzmc,xydm,tzzsj,tys,tzbs,zysj from xjtzzb where rowid=?  ",
						new String[]{RowidToPk.rowidToPK(pkValue)},new String[]{ "xn","xq","tzzmc","xydm","tzzsj","tys","tzbs","zysj"});
				xn = map.get("xn");
				tzzmc = map.get("tzzmc");
				tzbs = map.get("tzbs");
				tzzsj  = map.get("tzzsj");
				tys  = map.get("tys");
				zysj  = map.get("zysj");
			}
			request.setAttribute("xn",xn);
			request.setAttribute("tzzmc",tzzmc);
			request.setAttribute("tzbs",tzbs);
			request.setAttribute("tzzsj",tzzsj);
			request.setAttribute("tys",tys);
			request.setAttribute("zysj",zysj);
			actionFwd ="xjtzzPrint";
		}else{
			String pkValue = request.getParameter("pkValue");
			String title = "";
			String xn    = Base.getJxjsqxn();
			String bjdm = myForm.getBjdm();
			String bzxm = DealString.toGBK(myForm.getBzxm());
			String xsrs = DealString.toGBK(myForm.getXsrs());
			String bzr = DealString.toGBK(myForm.getBzr());
			String zysj = DealString.toGBK(myForm.getZysj());//主要事迹			
			String jtch = DealString.toGBK(myForm.getJtch());

			if(!Base.isNull(pkValue)){
				String sql = "select * from pjpy_xjbjandwmsqb where xn||xq||rychdm||bjdm=?  ";
				if (!Globals.XXDM_HZZY.equalsIgnoreCase(Base.xxdm)) {
					sql = "select * from pjpy_xjbjandwmsqb where xn||bjdm||rychdm=?  ";
				}
				HashMap<String,String> map = daoo.getMap(sql,
						new String[]{RowidToPk.rowidToPK(pkValue)},new String[]{ "xn","xq","rychdm","bzxm","xsrs","bzr","zysj","bjdm","jtch"});
				bjdm = map.get("bjdm");
				bzxm = map.get("bzxm");
				xsrs = map.get("xsrs");
				bzr  = map.get("bzr");
				zysj  = map.get("zysj");
				jtch  = map.get("jtch");
				xn    = StringUtils.isNull(map.get("xn")) ? "" : map.get("xn"); 
			}
			if("xjbj".equalsIgnoreCase(defaultTitle)){
				title =xn + "学年" +xxmc+"先进班级推荐表";
			}else{
				title =xn + "学年" +xxmc+"文明班级推荐表";
			}
			request.setAttribute("bjmc",  dao.getBjmc(bjdm));
			request.setAttribute("bzxm", bzxm);
			request.setAttribute("xsrs", xsrs);
			request.setAttribute("bzr", bzr);
			request.setAttribute("zysj", zysj);
			request.setAttribute("title", title);
			request.setAttribute("jtch", jtch);
			
//			String xn = Base.getJxjsqxn();
//			String xq = Base.getJxjsqxq();
//			String rychdm = (defaultTitle.equalsIgnoreCase("xjbj"))?"00001":(defaultTitle.equalsIgnoreCase("wmbj")?"00002":"");
//			String[] columns = {"xn","xq","rychdm","bzxm","xsrs","bzr","zysj","bjdm","jtch"};
//			String primaryKey = "xn||xq||rychdm||bjdm";
//			String primaryKeyValue = xn+xq+rychdm+bjdm;
//			String pks = request.getParameter("pks");
//			String[] values = dao.getMes(primaryKey, primaryKeyValue, columns);
//			if (!StringUtils.isNull(pks)) {
//				primaryKey="xn||bjdm";
//				values = dao.getMes(primaryKey, pks, columns);
//			}
//			String bjmc = dao.getBjmc(values != null && values.length == 9 ? values[7] : "");
//			String bzxm = values != null && values.length == 9 ? values[3] : "";
//			String xsrs = values != null && values.length == 9 ? values[4] : "";
//			String bzr = values != null && values.length == 9 ? values[5] : "";
//			String zysj = values != null && values.length == 9 ? values[6] : "";//主要事迹
//			String rychdms = values != null && values.length == 9 ? values[2] : "";
//			String jtch = values != null && values.length == 9 ? values[8] : "";
//			//杭职院的先进班级和文明班级推荐表
//			//String modelPath = servlet.getServletContext().getRealPath("")+"/print/pjpy_hzy_xjbjandwmbjsq.xls";
//			String title = rychdm.equals("00001")? xxmc + "先进班级推荐表":(rychdm.equals("00002")? xxmc + "文明班级推荐表": xxmc + "先进班级推荐表");
//			if (!StringUtils.isNull(pks)) {
//				title = rychdms.equals("00001")? xxmc + "先进班级推荐表":(rychdms.equals("00002")? xxmc + "文明班级推荐表": xxmc + "先进班级推荐表");
//			}
//			request.setAttribute("bjmc", bjmc);
//			request.setAttribute("bzxm", bzxm);
//			request.setAttribute("xsrs", xsrs);
//			request.setAttribute("bzr", bzr);
//			request.setAttribute("zysj", zysj);
//			request.setAttribute("title", title);
//			request.setAttribute("jtch", jtch);
//			String[] xyxxshsj = daoo.getOneRs("select xyyj,xxyj,xyshsj,xxshsj,xyqm,jtch from pjpy_xjbjandwmsqb where xn||xq||rychdm||bjdm=?", new String[]{primaryKeyValue}, new String[]{"xyyj", "xxyj", "xyshsj", "xxshsj", "xyqm","jtch"});
//			if (xyxxshsj != null && xyxxshsj.length == 6) {
//				request.setAttribute("xyyj", xyxxshsj[0]);
//				request.setAttribute("xyshyear", StringUtils.isNull(xyxxshsj[2]) ? "" : xyxxshsj[2].substring(0, 4));
//				request.setAttribute("xyshmon", StringUtils.isNull(xyxxshsj[2]) ? "" : xyxxshsj[2].substring(4, 6));
//				request.setAttribute("xyshdate", StringUtils.isNull(xyxxshsj[2]) ? "" : xyxxshsj[2].substring(6, 8));
//				request.setAttribute("xxyj", xyxxshsj[1]);
//				request.setAttribute("xxshyear", StringUtils.isNull(xyxxshsj[3]) ? "" : xyxxshsj[3].substring(0, 4));
//				request.setAttribute("xxshmon", StringUtils.isNull(xyxxshsj[3]) ? "" : xyxxshsj[3].substring(4, 6));
//				request.setAttribute("xxshdate", StringUtils.isNull(xyxxshsj[3]) ? "" : xyxxshsj[3].substring(6, 8));
//				request.setAttribute("xyqm", xyxxshsj[4]);
//				request.setAttribute("jtch", xyxxshsj[5]);
//			}
		}
		
		//信阳师范先进集体班级打印报表
		if(Globals.XXDM_XYSFXY.equalsIgnoreCase(Base.xxdm)){
			return new ActionForward("/pjpy/xysf/xjbjtprint.jsp", false);
		}
		return mapping.findForward(actionFwd);
	}
	/**
	 * 获取先进团支部文件下载列表
	 * @param pkValue
	 * @return
	 */	   
	public  List GetXjtzbFileList(String pkValue,String upType){
		DAO dao = DAO.getInstance();
		List rs = null;
		if("xjtzb".equalsIgnoreCase(upType)){
			String pk = "xn||xq||tzbmc";
			String sql = "select "+pk+" pk,wjm,sqsj,wjlj from XJTZBB where "+pk+"=? and wjlj is not null";
			rs = dao.getList(sql, new String[] { pkValue }, new String[] {
					"pk", "wjm", "sqsj", "wjlj"});
		}else{
			String pk = "xn||xq||tzzmc";
			String sql = "select "+pk+" pk,wjm,sqsj,wjlj from XJTZZB where "+pk+"=? and wjlj is not null";
			rs = dao.getList(sql, new String[] { pkValue }, new String[] {
					"pk", "wjm", "sqsj", "wjlj"});			
		}
		return rs;
	}	
	/**
	 *文件下载
	 * @throws Exception
	 */
	public  ActionForward DownLoadFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		byte b[]= new byte[500];	
		String downType = request.getParameter("downType");		
		String pkValue = DealString.toGBK(request.getParameter("param"));
		String dir = "";
		String filename = "";
		if("xjtzb".equalsIgnoreCase(downType)){
			HashMap<String,String>  map= dao.getMap("select wjlj,wjm from XJTZBB where xn||xq||tzbmc=? ", new String[]{pkValue}, new String[]{"wjlj","wjm"});		
			dir =map.get("wjlj");
			filename = map.get("wjm");
		}else{
			HashMap<String,String>  map= dao.getMap("select wjlj,wjm from XJTZZB where xn||xq||tzzmc=? ", new String[]{pkValue}, new String[]{"wjlj","wjm"});		
			dir =map.get("wjlj");
			filename = map.get("wjm");		
		}		
		try{
			File fileload=new File(dir);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ DealString.toUtf8String(filename));
			InputStream in=new FileInputStream(fileload);
			in = new BufferedInputStream(in);
			while ((in.read(b))!=-1) {
				response.getOutputStream().write(b);
			}
		}catch (Exception e) {
			request.setAttribute("errMsg", "找不到指定文件！");
			return new ActionForward("/errMsg.do", false);
		}		
		return null;
	}
	/**
	 * 先进班级申请结果查询页面
	 */
	public ActionForward xjbjsqQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		HzyPjpyForm hzyPjpyForm = (HzyPjpyForm) form;
		HttpSession session = request.getSession();
//		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xydm = hzyPjpyForm.getXydm();
		String zydm = hzyPjpyForm.getXydm();
		String nj   = hzyPjpyForm.getNj();
		String xn = hzyPjpyForm.getXn();
		String xq = hzyPjpyForm.getXq();
		String bjdm = hzyPjpyForm.getBjdm();
		String tzbmc = hzyPjpyForm.getTzbmc();
		String sqsj  = hzyPjpyForm.getSqsj();
		String tzbsj = hzyPjpyForm.getTzbs();
		String tzzmc  = hzyPjpyForm.getTzzmc();
		String tzzsj = hzyPjpyForm.getTzzsj();
		String xjType = request.getParameter("xjType");
		String tableName = "";
		String realTable = "";
		if(request.getParameter("xn")==null){
			hzyPjpyForm.setXn(Base.getJxjsqxn());
		}
		if(request.getParameter("xq")==null){
			hzyPjpyForm.setXq(Base.getJxjsqxq());
		}	
		if (StringUtils.isEqual("xy", userType)) {//学院用户
			xydm = userDep;
			hzyPjpyForm.setXydm(xydm);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			StringBuffer querry = new StringBuffer();
			List<HashMap<String, String>> titList = null;//service.getXjbjQryTitle(xxdm);//查询表头
			List<String[]> rsList = null;//service.getXjbjQryResult(model, xxdm);			
			String[] enList = null;
			String[] cnList = null;
			String sql      = "";
			if("xjtzb".equalsIgnoreCase(xjType)){//先进团支部
				enList = new String[] { "pk", "rownum","xn","xqmc","tzbmc","tzbsj","tys","ftys","sqsj","xysh","xxsh"};				
				cnList = new String[] { "pk", "行号", "学年", "学期", "团支部","支部书记", "团员数", "非团员数", "申请时间", "院系审核", "学生处审核" };				
				sql = "select rowid pk,rownum,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc,a.* from xjtzbb a where 1=1 ";
				querry.append(Base.isNull(tzbmc)?"":" and tzbmc like '%"+tzbmc+"%' ");
				querry.append(Base.isNull(sqsj)?"":" and sqsj='"+sqsj+"' ");
				querry.append(Base.isNull(tzbsj)?"":" and tzbsj='"+tzbsj+"' ");
				querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");	
				querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
				querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
			}else if("xjtzz".equalsIgnoreCase(xjType)){//先进团支部
				enList = new String[] { "pk", "rownum","xn","xqmc","tzzmc","tzzsj","tzbs","tys","sqsj","xysh","xxsh"};				
				cnList = new String[] { "pk", "行号", "学年", "学期", "团总支","书记", "团支部数", "团员数", "申请时间", "院系审核", "学生处审核" };				
				sql = "select rowid pk,rownum,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc,a.* from xjtzzb a where 1=1 ";
				querry.append(Base.isNull(tzzmc)?"":" and tzzmc like '%"+tzzmc+"%' ");
				querry.append(Base.isNull(sqsj)?"":" and sqsj='"+sqsj+"' ");
				querry.append(Base.isNull(tzzsj)?"":" and tzzsj='"+tzzsj+"' ");
				querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
				querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
				querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
			}else{
				enList = new String[] { "pk", "rownum", "xn","xqmc", "nj", "xymc","zymc", "bjmc", "rychmc", "bzr", "xysh", "xxsh" };
				cnList = new String[] { "pk", "行号", "学年","学期" ,"年级", "学院名称","专业名称", "班级名称", "班级荣誉", "班主任", "院系审核", "学生处审核" };
				sql = "select a.xn||a.bjdm||a.rychdm pk,rownum,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc,a.* from view_pjpy_xjbjandwmsq a where 1=1 ";
				querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
				querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
				querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
				querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
				querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
				querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
				if("xjbj".equalsIgnoreCase(xjType)){//先进班级
					querry.append(" and rychdm='00001' ");
				}else{//文明班级
					querry.append(" and rychdm='00002' ");
				}
			}
			titList = dao.arrayToList(enList, cnList);
			rsList  = dao.rsToVator(sql+querry,new String[] {},enList);
			request.setAttribute("topTr", titList);//表头
			request.setAttribute("rs", rsList);//结果
			request.setAttribute("rsNum", rsList != null ? rsList.size() : 0);//记录数
		}
		String actFd = "xjwmbjqry";
		if("xjtzb".equalsIgnoreCase(xjType)){
			tableName = "xjtzbb";
			realTable = "xjtzbb";
			actFd = "xjtzbSqQuer";
			request.setAttribute("xnList", Base.getXnndList());//学年列表
			request.setAttribute("xqList", Base.getXqList());//学期列表
			request.setAttribute("xyList", Base.getXyList());//学院列表
		}else if("xjtzz".equalsIgnoreCase(xjType)){
			tableName = "xjtzzb";
			realTable = "xjtzzb";
			actFd = "xjtzzSqQuer";
			request.setAttribute("xnList", Base.getXnndList());//学年列表
			request.setAttribute("xqList", Base.getXqList());//学期列表
			request.setAttribute("xyList", Base.getXyList());//学院列表
		}else{
			tableName = "view_pjpy_xjbjandwmsq";
			realTable = "pjpy_xjbjandwmsqb";
			CommonAction cA = new CommonAction();
			cA.appendProperties(request, xydm, zydm, nj);//加载页面列表	
		}
		request.setAttribute("writeAble", "yes");//判断用户读写权
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		return mapping.findForward(actFd);
	}	
	public  ActionForward xjchDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HzyPjpyForm hzyPjpyForm = (HzyPjpyForm) form;
		String[] pkValueA = hzyPjpyForm.getCbv();
		String xjType = request.getParameter("xjType");
		String pk = "";
		DAO dao = DAO.getInstance();
		String[] delPkSql  = new String[pkValueA.length];
		if("xjtzb".equalsIgnoreCase(xjType)){			
			for (int i = 0; i < pkValueA.length; i++) {
				delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete xjtzbb  where rowid= '"+RowidToPk.rowidToPK(pkValueA[i])+"'";							
			}			
		}else if("xjtzz".equalsIgnoreCase(xjType)){			
			for (int i = 0; i < pkValueA.length; i++) {
				delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete xjtzzb  where rowid= '"+RowidToPk.rowidToPK(pkValueA[i])+"'";							
			}			
		}else{			
			pk = "xn||bjdm||rychdm";
			for (int i = 0; i < pkValueA.length; i++) {
				delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete pjpy_xjbjandwmsqb  where "+pk+"= '"+DealString.toGBK(pkValueA[i])+"'";							
			}
		}				              
		int[] array = dao.runBatch(delPkSql);
		dao.checkBatch(array);   
		return xjbjsqQry(mapping, form, request, response);
	}
	public ActionForward xjchModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		HzyPjpyForm hzyPjpyForm = (HzyPjpyForm) form;
		String pkValue = RowidToPk.rowidToPK(request.getParameter("pkValue"));
		String act  = request.getParameter("act");
		String xjType = request.getParameter("xjType");
		String doType = request.getParameter("doType");
		HashMap<String,String> map = new HashMap<String, String>();
		String actionFor = "";
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();		
		if (StringUtils.isEqual("xy", userType)) {//学院用户
			hzyPjpyForm.setXydm(userDep);
		}
		String realTable = "";
		if("xjtzb".equalsIgnoreCase(xjType)){	
			realTable = "xjtzbb";
		}else if("xjtzz".equalsIgnoreCase(xjType)){	
			realTable = "xjtzzb";
		}
		String[] fileInfo = upFile(request, hzyPjpyForm,dao.getOneRs("select xn from "+realTable+" where rowid=?", new String[]{pkValue}, "xn"),dao.getOneRs("select xq from xjtzbb where rowid=?", new String[]{pkValue}, "xq"));
		String filePath = fileInfo[0];
		String wjm      = fileInfo[1];  
		
		if("save".equalsIgnoreCase(doType)){
			String sql = "";
			boolean done = false;
			if("xjtzb".equalsIgnoreCase(xjType)){
				String ftys = hzyPjpyForm.getFtys();
				String zysj = hzyPjpyForm.getZysj();
				String tzbmc = hzyPjpyForm.getTzbmc();
				String tzbsj = hzyPjpyForm.getTzbsj();
				String tys    = hzyPjpyForm.getTys(); 
				String xydm    = hzyPjpyForm.getXydm(); 	
				if(Base.isNull(wjm)){
					sql = " update xjtzbb set tzbmc=?,xydm=?,tzbsj=?,tys=?,ftys=?,zysj=?  where  rowid=?";
					done = dao.runUpdate(sql,new String[]{tzbmc,xydm,tzbsj,tys,ftys,zysj,pkValue});
				}else{
					sql = " update xjtzbb set tzbmc=?,xydm=?,tzbsj=?,tys=?,ftys=?,zysj=?,wjlj=?,wjm=? where  rowid=?";
					done = dao.runUpdate(sql,new String[]{tzbmc,xydm,tzbsj,tys,ftys,zysj,filePath,wjm,pkValue});
				}
			}else{
				String tys = hzyPjpyForm.getTys();
				String zysj = hzyPjpyForm.getZysj();
				String tzzmc = hzyPjpyForm.getTzzmc();
				String tzzsj = hzyPjpyForm.getTzzsj();
				String tzbs    = hzyPjpyForm.getTzbs();
				String xydm  = hzyPjpyForm.getXydm();
				if(Base.isNull(wjm)){
					sql = " update xjtzzb set tzzmc=?,xydm=?,tzzsj=?,tzbs=?,tys=?,zysj=?  where  rowid=?";
					done = dao.runUpdate(sql,new String[]{tzzmc,xydm,tzzsj,tzbs,tys,zysj,pkValue});
				}else{
					sql = " update xjtzzb set tzzmc=?,xydm=?,tzzsj=?,tzbs=?,tys=?,zysj=? ,wjlj=?,wjm=? where  rowid=?";
					done = dao.runUpdate(sql,new String[]{tzzmc,xydm,tzzsj,tzbs,tys,zysj,filePath,wjm,pkValue});
				}
			}
			request.setAttribute("done",done);
		}	
		if("xjtzb".equalsIgnoreCase(xjType)){			
			map = dao.getMap("select * from xjtzbb where rowid=?", new String[]{pkValue},new String[]{"xn","xq","tzbmc","tzbsj","tys","ftys","zysj","xydm","xysh","xxsh"});
			actionFor = "xjtzbModi";
			request.setAttribute("rswj",dao.getList("select xn||xq||tzbmc pk,wjm,sqsj,wjlj from XJTZBB where rowid=? and wjlj is not null", new String[] { pkValue }, new String[] {"pk", "wjm", "sqsj", "wjlj"}));
		}else if("xjtzz".equalsIgnoreCase(xjType)){			
			map = dao.getMap("select * from xjtzzb where rowid=?", new String[]{pkValue},new String[]{"xn","xq","tzzmc","tzzsj","tzbs","tys","zysj","xydm","xysh","xxsh"});
			actionFor = "xjtzzModi";
			request.setAttribute("rswj",dao.getList("select xn||xq||tzzmc pk,wjm,sqsj,wjlj from XJTZZB where rowid=? and wjlj is not null", new String[] { pkValue }, new String[] {"pk", "wjm", "sqsj", "wjlj"}));
		}
		request.setAttribute("rs",map);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("xjType",xjType);
		request.setAttribute("act",act);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("userType", userType);
		return mapping.findForward(actionFor);
	}
	@SuppressWarnings("deprecation")
	public String[] upFile(HttpServletRequest request,HzyPjpyForm myForm,String xn,String xq) throws Exception{
//		文件上传
		String[] retValue= new String[]{"",""}; 
		String filePath = null;
		String dir = request.getRealPath("/") + "upload";
		String dateStr = xn+xq;  
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
//		Timestamp date = new Timestamp(System.currentTimeMillis());
//		dateStr += date.toString().substring(0, 19);
//		dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(":","");
		FormFile file = myForm.getUploadFile();
		String message = "";
		String wjm ="" ;//文件名
		if(file==null){
			message = "附件上传失败！";
			request.setAttribute("message", message);
			request.setAttribute("inserted","no");
			//return mapping.findForward("false");
		}else{
			int size = file.getFileSize();
			if(size>0){//有文件上传
				String fName = dateStr+file.getFileName();
				wjm = file.getFileName();
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
		retValue = new String[]{filePath,wjm};
		return retValue;
	}
	public ActionForward xjchChkQuerry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HzyPjpyForm hzyPjpyForm = (HzyPjpyForm) form;
		HttpSession session = request.getSession();
//		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if(request.getParameter("xn")==null){
			hzyPjpyForm.setXn(Base.getJxjsqxn());
		}
		if(request.getParameter("xq")==null){
			hzyPjpyForm.setXq(Base.getJxjsqxq());
		}
		String xydm = hzyPjpyForm.getXydm();
		String zydm = hzyPjpyForm.getXydm();
		String nj   = hzyPjpyForm.getNj();
		String xn = hzyPjpyForm.getXn();
		String xq = DealString.toGBK(hzyPjpyForm.getXq());
		hzyPjpyForm.setXq(xq);
		String bjdm = hzyPjpyForm.getBjdm();
//		String tzbmc = hzyPjpyForm.getTzbmc();
//		String sqsj  = hzyPjpyForm.getSqsj();
//		String tzbsj = hzyPjpyForm.getTzbs();
//		String tzzmc  = hzyPjpyForm.getTzzmc();
//		String tzzsj = hzyPjpyForm.getTzzsj();
		String yesNo = DealString.toGBK(hzyPjpyForm.getYesNo());
		hzyPjpyForm.setYesNo(yesNo);
		String xjType = request.getParameter("xjType");
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {//学院用户
			xydm = userDep;
			hzyPjpyForm.setXydm(xydm);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			StringBuffer querry = new StringBuffer();
			List<HashMap<String, String>> titList = null;//查询表头
			List<String[]> rsList = null;			
			String[] enList = null;
			String[] cnList = null;
			String sql      = "";			
			if("xy".equalsIgnoreCase(userType)){				
				querry.append(" and xysh='"+yesNo+"'");				
			}else{
				querry.append(" and xysh='通过'");
				querry.append(" and xxsh='"+yesNo+"'");				
			}
			if("xjtzb".equalsIgnoreCase(xjType)){//先进团支部
				enList = new String[] { "pk", "rownum","xn","xqmc","tzbmc","tzbsj","tys","ftys","sqsj","xysh","xxsh"};				
				cnList = new String[] { "pk", "行号", "学年", "学期", "团支部","支部书记", "团员数", "非团员数", "申请时间", "院系审核", "学校审核" };				
				sql = "select rowid pk,rownum,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc,a.* from xjtzbb a where 1=1 ";
//				querry.append(Base.isNull(tzbmc)?"":" and tzbmc like '%"+tzbmc+"%' ");
//				querry.append(Base.isNull(sqsj)?"":" and sqsj='"+sqsj+"' ");
//				querry.append(Base.isNull(tzbsj)?"":" and tzbsj='"+tzbsj+"' ");
				querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");	
				querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
				querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");				
			}else if("xjtzz".equalsIgnoreCase(xjType)){//先进团支部
				enList = new String[] { "pk", "rownum","xn","xqmc","tzzmc","tzzsj","tzbs","tys","sqsj","xysh","xxsh"};				
				cnList = new String[] { "pk", "行号", "学年", "学期", "团总支","书记", "团支部数", "团员数", "申请时间", "院系审核", "学校审核" };				
				sql = "select rowid pk,rownum,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc,a.* from xjtzzb a where 1=1 ";
//				querry.append(Base.isNull(tzzmc)?"":" and tzzmc like '%"+tzzmc+"%' ");
//				querry.append(Base.isNull(sqsj)?"":" and sqsj='"+sqsj+"' ");
//				querry.append(Base.isNull(tzzsj)?"":" and tzzsj='"+tzzsj+"' ");
				querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
				querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
				querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
			}else{
				enList = new String[] { "pk", "rownum", "xn","xqmc", "nj", "xymc","zymc", "bjmc", "rychmc", "bzr", "xysh", "xxsh" };
				cnList = new String[] { "pk", "行号", "学年","学期" ,"年级", "学院名称","专业名称", "班级名称", "班级荣誉", "班主任", "院系审核", "学校审核" };
				sql = "select xn||xq||rychdm||bjdm pk,rownum,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc,a.* from view_pjpy_xjbjandwmsq a where 1=1 ";
				querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
				querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
				querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
				querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
				querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
				querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
				if("xjbj".equalsIgnoreCase(xjType)){//先进班级
					querry.append(" and rychdm='00001' ");
				}else{//文明班级
					querry.append(" and rychdm='00002' ");
				}
			}
			titList = dao.arrayToList(enList, cnList);
			rsList  = dao.rsToVator(sql+querry,new String[] {},enList);
			request.setAttribute("topTr", titList);//表头
			request.setAttribute("rs", rsList);//结果
			request.setAttribute("rsNum", rsList != null ? rsList.size() : 0);//记录数
		}
		String actFd = "xjwmbjChkQry";
		if("xjtzb".equalsIgnoreCase(xjType)){
			tableName = "xjtzbb";
			realTable = "xjtzbb";
			actFd = "xjtzbChkQry";
			request.setAttribute("xnList", Base.getXnndList());//学年列表
			request.setAttribute("xqList", Base.getXqList());//学期列表
			request.setAttribute("xyList", Base.getXyList());//学院列表
		}else if("xjtzz".equalsIgnoreCase(xjType)){
			tableName = "xjtzzb";
			realTable = "xjtzzb";
			actFd = "xjtzzChkQry";
			request.setAttribute("xnList", Base.getXnndList());//学年列表
			request.setAttribute("xqList", Base.getXqList());//学期列表
			request.setAttribute("xyList", Base.getXyList());//学院列表
		}else{
			tableName = "view_pjpy_xjbjandwmsq";
			realTable = "pjpy_xjbjandwmsqb";
			CommonAction cA = new CommonAction();
			cA.appendProperties(request, xydm, zydm, nj);//加载页面列表	
		}
		request.setAttribute("chkList",dao.getChkList(3));//审核列表
		request.setAttribute("writeAble", "yes");//判断用户读写权
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		return mapping.findForward(actFd);
	}
	public ActionForward xjchChk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
//		HzyPjpyForm hzyPjpyForm = (HzyPjpyForm) form;
		String pkValue = RowidToPk.rowidToPK(request.getParameter("pkValue"));
		String xjType = request.getParameter("xjType");
		String doType = request.getParameter("doType");
		HashMap<String,String> map = new HashMap<String, String>();
		String actionFor = "";
		String userType = request.getSession().getAttribute("userType").toString();
		String shType =  ("xy".equalsIgnoreCase(userType))?"xysh":"xxsh";
		if("save".equalsIgnoreCase(doType)){
			String sql = "";
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			boolean done = false;
				
			if("xjtzb".equalsIgnoreCase(xjType)){
				sql = " update xjtzbb set "+shType+"=? where rowid=?";
				done = dao.runUpdate(sql,new String[]{yesNo,pkValue});
			}else if("xjtzz".equalsIgnoreCase(xjType)){
				sql = " update xjtzzb set "+shType+"=? where rowid=?";
				done = dao.runUpdate(sql,new String[]{yesNo,pkValue});				
			}else{
				String xxyj = DealString.toGBK(request.getParameter("xxyj"));
				String xyyj = DealString.toGBK(request.getParameter("xyyj"));
				if("xy".equalsIgnoreCase(userType)){
					sql = " update pjpy_xjbjandwmsqb set "+shType+"=?, xyyj=? where xn||xq||rychdm||bjdm=?";
					done = dao.runUpdate(sql,new String[]{yesNo,Base.isNull(xyyj)?"":xyyj.trim(),pkValue});
				}else{
					sql = " update pjpy_xjbjandwmsqb set "+shType+"=?, xxyj=? where xn||xq||rychdm||bjdm=?";
					done = dao.runUpdate(sql,new String[]{yesNo,Base.isNull(xxyj)?"":xxyj.trim(),pkValue});
				}																
			}
			request.setAttribute("done",done);
		}	
		if("xjtzb".equalsIgnoreCase(xjType)){			
			map = dao.getMap("select "+shType+" yesNo ,a.* from xjtzbb a where rowid=?", new String[]{pkValue},new String[]{"yesNo","xn","xq","tzbmc","tzbsj","tys","ftys","zysj","xydm","xysh","xxsh"});
			actionFor = "xjtzbViewChk";
			request.setAttribute("rswj",dao.getList("select xn||xq||tzbmc pk,wjm,sqsj,wjlj from XJTZBB where rowid=? and wjlj is not null", new String[] { pkValue }, new String[] {"pk", "wjm", "sqsj", "wjlj"}));
		}else if("xjtzz".equalsIgnoreCase(xjType)){			
			map = dao.getMap("select  "+shType+" yesNo ,a.* from xjtzzb a where rowid=?", new String[]{pkValue},new String[]{"yesNo","xn","xq","tzzmc","tzzsj","tzbs","tys","zysj","xydm","xysh","xxsh"});
			actionFor = "xjtzzViewChk";
			request.setAttribute("rswj",dao.getList("select xn||xq||tzzmc pk,wjm,sqsj,wjlj from XJTZZB where rowid=? and wjlj is not null", new String[] { pkValue }, new String[] {"pk", "wjm", "sqsj", "wjlj"}));
		}else{
			if("xjbj".equalsIgnoreCase(xjType)){
				request.setAttribute("clin","先进班级");
			}else{
				request.setAttribute("clin","文明班级");
			}
			map = dao.getMap("select "+shType+" yesNo,a.* from view_pjpy_xjbjandwmsq a where xn||xq||rychdm||bjdm=?", new String[] { pkValue }, new String[]{"yesNo","bjmc","tzs","bhgqs","ywcf","bjry","yxdzbyj", "xymc","zymc","bzxm", "bzr", "xsrs", "zysj","rychmc","xyyj", "xn", "xq","xxyj","xxsh" ,"xyqm", "xysh", "xyyj","bz", "jtch"});
			actionFor = "xjwmbjViewChk";
		}
		if(!"xy".equalsIgnoreCase(userType)){
			if(!"通过".equalsIgnoreCase(map.get("xysh"))){
				request.setAttribute("xxSubmit","no");
			}
		}
		request.setAttribute("chkList",dao.getChkList(3));//审核列表
		request.setAttribute("rs",map);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("xjType",xjType);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("userType", userType);
		return mapping.findForward(actionFor);
	}
	//先进称号批量审核
	public ActionForward xjchPlChk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HzyPjpyForm hzyPjpyForm = (HzyPjpyForm) form;
		String[] pkValueA = hzyPjpyForm.getCbv();
		String xjType = request.getParameter("xjType");
		String userType = request.getSession().getAttribute("userType").toString();
		String chekValue = request.getParameter("chek");
		chekValue = ("yes".equalsIgnoreCase(chekValue)?"通过":"不通过");		
		DAO dao = DAO.getInstance();
		String[] delPkSql  = new String[pkValueA.length];
		String shType =  ("xy".equalsIgnoreCase(userType))?"xysh":"xxsh";
		if("xjtzb".equalsIgnoreCase(xjType)){			
			for (int i = 0; i < pkValueA.length; i++) {
				delPkSql[i] = Base.isNull(pkValueA[i])?"":"update xjtzbb set "+shType+"='"+chekValue+"' where rowid= '"+RowidToPk.rowidToPK(pkValueA[i])+"'";							
			}			
		}else if("xjtzz".equalsIgnoreCase(xjType)){			
			for (int i = 0; i < pkValueA.length; i++) {
				delPkSql[i] = Base.isNull(pkValueA[i])?"":"update xjtzzb  set "+shType+"='"+chekValue+"' where rowid= '"+RowidToPk.rowidToPK(pkValueA[i])+"'";							
			}			
		}else{			
			String pk = "xn||xq||rychdm||bjdm";
			for (int i = 0; i < pkValueA.length; i++) {
				delPkSql[i] = Base.isNull(pkValueA[i])?"":"update pjpy_xjbjandwmsqb set "+shType+"='"+chekValue+"'  where "+pk+"= '"+DealString.toGBK(pkValueA[i])+"'";							
			}
		}				              
		int[] array = dao.runBatch(delPkSql);
		dao.checkBatch(array);   
		return xjchChkQuerry(mapping, form, request, response);
	}
}
