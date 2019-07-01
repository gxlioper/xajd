/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-8 下午01:29:58</p>
 */
package xgxt.xsgygl.jhzyjsxy;

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
import xgxt.DAO.GetDropDownList;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.xsgygl.dao.gyglDao;
import xgxt.xsgygl.jhzyjsxy.yxlcqsz.YxlcqszModel;


public class GyglJhzyAction extends DispatchAction {
	public static String writeAble ="";
	private static GyglJhzyService service = new GyglJhzyService();
	/**
	 * 公用条件方法
	 */
	public void setNjXyZyBjList(HttpServletRequest request,GyglJhzyForm myForm) throws IllegalArgumentException,
	SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//	    在request保存年级学院专业班级List的方法
//		HttpSession session =request.getSession();
//		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;		
		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList",(Base.getZyMap()).get(xy));
		request.setAttribute("bjList",(Base.getBjMap()).get(bjKey));
//		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
//			//辅导员登录
//			request.setAttribute("bjList", Fdypd.getFdybjList (userName));// 发送班级列表
//			request.setAttribute("zyList", Fdypd.getFdyZyList (userName));// 发送班级列表
//		}
	}
	/**
	 * @descrip 根据校区名刷楼栋名
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryLdmForXqm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		return mapping.findForward("");
	}
	/**
	 * @descrip 公寓辅导员信息管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward gyfdyInfoManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		GyglGyfdyModel model = new GyglGyfdyModel();
		List<String[]> rs = new ArrayList<String[]>();
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String yhm = request.getParameter("pkValue");
		String tableName = "jhzy_gyfdyb";
		String rsNum = "0";
		String realTable = "jhzy_gyfdyb";
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		StringBuffer querry = new StringBuffer();
		if(Base.isNull(xq)){
			myForm.setXq(Base.currXq);
		}
		if(Base.isNull(xn)){
			myForm.setXn(Base.currXn);
		}
		querry.append(" where 1=1 ");
		if("del".equals(doType)){
			boolean result = service.ser_DelGyfdyInfo(yhm,xn,xq,request);
			if(result){
				request.setAttribute("done", "ok");
			}else{
				request.setAttribute("done", "no");
			}
		}
		String[] colList ={"yhm","xn","xq","zmc","bmmc","dwmc","gzs","lxdh"};
		List<HashMap<String, String>> topTr = service.getTopTr(tableName, colList);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			BeanUtils.copyProperties(model, myForm);
			rs = service.ser_QueryGyfdyInfo(model,myForm);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		request.setAttribute("yhList", service.getUserInfoList());
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		writeAble = (Base.chkUPower(userName,"gyfdyInfoManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no";
		request.setAttribute("writeAble",writeAble);
		return mapping.findForward("gyfdyInfoQuery");
	}
	/**
	 * @descrip 行政公寓辅导员信息管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward xzgyfdyManager(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		GyglGyfdyModel model = new GyglGyfdyModel();
		List<String[]> rs = new ArrayList<String[]>();
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String yhm = request.getParameter("pkValue");
		String tableName = "jhzy_gyfdyb";
		String rsNum = "0";
		String realTable = "jhzy_gyfdyb";
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		StringBuffer querry = new StringBuffer();
		String lddm = myForm.getLddm();
		String lc = myForm.getLcdm();
//		if(Base.isNull(xq)){
//			myForm.setXq(Base.currXq);
//		}
//		if(Base.isNull(xn)){
//			myForm.setXn(Base.currXn);
//		}
		querry.append(" where 1=1 ");
		if("del".equals(doType)){
			boolean result = service.ser_DelGyfdyInfo(yhm,xn,xq,request);
			if(result){
				request.setAttribute("done", "ok");
			}else{
				request.setAttribute("done", "no");
			}
		}
		String[] colList ={"ldmc","cs","qsh","zgh","xm","xymc"};
		List<HashMap<String, String>> topTr = service.getTopTr("view_xzgyfdy", colList);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			BeanUtils.copyProperties(model, myForm);
			rs = service.ser_QueryXzGyfdyInfo(model,myForm);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		GetDropDownList getList = new GetDropDownList();
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm) ? " " : lddm,userName));
		request.setAttribute("qshList", getList.GetQshList2(Base.isNull(lddm) ? " " : lddm, Base.isNull(lc) ? " " : lc,""));
		request.setAttribute("yhList", service.getXzGyfdyInfoList());
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		writeAble = (Base.chkUPower(userName,"gyfdyInfoManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no";
		request.setAttribute("writeAble",writeAble);
		return mapping.findForward("xzgyfdy");
	}
	/**
	 * @descrip 公寓辅导员信息管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward gyfdyInfoModify(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		GyglGyfdyModel model = new GyglGyfdyModel();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String lddm = myForm.getLddm();
		String lch = myForm.getLcdm();
		String xq = StringUtils.isNull(myForm.getXq())?request.getParameter("xq"):myForm.getXq();
		String xn = StringUtils.isNull(myForm.getXn())?request.getParameter("xn"):myForm.getXn();
		String yhm = request.getParameter("pkValue");
		String isView = DealString.toString(request.getParameter("isView"));
		
		HashMap<String, String> map = new HashMap<String, String>();
		if(StringUtils.isNotNull(yhm)){
			model.setYhm(yhm);
			model.setXn(xn);
			model.setXq(xq);
			map = service.ser_GyfdyInfo(model);
		}

		if(Base.isNull(xq)){
			if (Base.isNull(yhm)) {
				map.put("xq", Base.currXq);
			}
			xq = map.get("xq");
			myForm.setXq(map.get("xq"));
		}
		if(Base.isNull(xn)){
			if (Base.isNull(yhm)) {
				map.put("xn", Base.currXn);
			}
			xn = map.get("xn");
			myForm.setXn(map.get("xn"));
		}
		request.setAttribute("isView", isView);
		request.setAttribute("lcList", service.getSsLcList(lddm));
		request.setAttribute("extssxxList", service.getSsmList(lddm, lch,"yes",yhm,xn,xq));
		request.setAttribute("ssxxList", service.getSsmList(lddm, lch,"no",yhm,xn,xq));
		request.setAttribute("ldList", service.getSsLdList());
		request.setAttribute("yhList", service.getUserInfoList());
		request.setAttribute("rs", map);
		request.setAttribute("njList", Base.getNjList());
		setNjXyZyBjList(request, myForm);
		writeAble = (Base.chkUPower(userName,"gydykp.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no";
		return mapping.findForward("gyfdyInfoModify");
	}
	/**
	 * @descrip 公寓辅导员信息保存
	 * roomCompartition
	 */
	public ActionForward gyfdyOperation(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		GyglGyfdyModel model = new GyglGyfdyModel();
		HashMap<String, String> map = new HashMap<String, String>();
		String lddm = myForm.getLddm();
		String lch = myForm.getLcdm();
		String xq = myForm.getXq();
		String xn = myForm.getXn();
		String yhm = myForm.getYhm();
		String isView = DealString.toString(request.getParameter("isView"));
		if(Base.isNull(xq)){
			myForm.setNj(Base.currXq);
		}
		if(Base.isNull(xn)){
			myForm.setXn(Base.currXn);
		}
		String doType = request.getParameter("doType");
		boolean doFlag = false;
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("save")){
			BeanUtils.copyProperties(model, myForm);
			FormModleCommon.formToGBK(model);
			doFlag = service.serv_GyfdyIntoSave(model);
			if(doFlag){
				request.setAttribute("done", "ok");
			}else{
				request.setAttribute("done", "no");
			}
		}
		
		if(StringUtils.isNotNull(yhm)){
			model.setYhm(yhm);
			map = service.ser_GyfdyInfo(model);
		}
		setNjXyZyBjList(request, myForm);
		request.setAttribute("isView", isView);
		request.setAttribute("lcList", service.getSsLcList(lddm));
		request.setAttribute("extssxxList", service.getSsmList(lddm, lch,"yes",yhm,xn,xq));
		request.setAttribute("ssxxList", service.getSsmList(lddm, lch,"no",yhm,xn,xq));
		request.setAttribute("ldList", service.getSsLdList());
		request.setAttribute("yhList", service.getUserInfoList());
		request.setAttribute("rs", map);
		request.setAttribute("njList", Base.getNjList());
		return mapping.findForward("gyfdyInfoModify");
	}
	/**
	 * 公寓辅导员信息(把一个学年学期的辅导员信息同步到另一个学年学期)
	 * roomCompartition
	 */
	public ActionForward selectIntoFdy(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		String xq = myForm.getXq();
		String xn = myForm.getXn();
		String dqxn = myForm.getDqxn();
		String dqxq = myForm.getDqxq();
		if(Base.isNull(xn)){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(xq)){
			myForm.setXq(Base.currXq);
		}
		String doType = request.getParameter("doType");
		boolean doFlag = false;
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("save")){//分配结果保存
			doFlag = service.serv_selectIntoFdy(dqxn, dqxq, xn, xq,request);
			if(doFlag){
				request.setAttribute("done", "ok");
			}else{
				request.setAttribute("done", "no");
			}
		}
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("njList", Base.getNjList());
		return mapping.findForward("synFdy");
	}
	/**
	 * @descrip 文明公寓楼申请
	 * roomCompartition
	 */
	public ActionForward wmgylSq(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		GyglGyfdyModel model = new GyglGyfdyModel();
		String xn = myForm.getXn();
		String userName = request.getSession().getAttribute("userName").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep  = request.getSession().getAttribute("userDep").toString();
		if(Base.isNull(xn)){
			myForm.setXn(Base.currXn);
		}
		if("stu".equalsIgnoreCase(userType)){//楼长判断
			HashMap<String,String>mp= new HashMap<String, String>();
			mp = service.serv_getLzInfo(userName);
			if(mp.size()>0){
				myForm.setXydm(userDep);
				myForm.setLzm(mp.get("lddm"));
				myForm.setNj(mp.get("nj"));
				request.setAttribute("ldmc",mp.get("ldmc"));
				request.setAttribute("xymc",mp.get("xymc"));
				request.setAttribute("nj",mp.get("nj"));
				request.setAttribute("isLz","yes");
			}else{
				request.setAttribute("isLz","no");
			}
		}
		
		String doType = request.getParameter("doType");
		boolean doFlag = false;
		BeanUtils.copyProperties(model, myForm);
		FormModleCommon.formToGBK(myForm);
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("save")){
			boolean isexis = service.serv_isExists(model);
			if(isexis){
				doFlag = service.serv_saveWmgyl(model,request);
				if(doFlag){
					request.setAttribute("done", "ok");
				}else{
					request.setAttribute("done", "no");
				}
			}else{
				request.setAttribute("isexists", "yes");
			}
		} 
		request.setAttribute("ldList", service.getSsLdList());
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		return mapping.findForward("wmgylsq");
	}
	/**
	 *文明公寓楼查询
	 */
	public ActionForward wmgylManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO  dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		String go = request.getParameter("go");
		String xn   = myForm.getXn();
		String doType = request.getParameter("doType");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if (xn == null) {
			xn = Base.currXn;
			myForm.setXn(xn);
		}
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("del")){
			String pkStr = request.getParameter("pkVStr");
			@SuppressWarnings("unused")
			String whichpk = service.serv_WmgylDel(pkStr, request);
			go = "go";
		}
		if ("go".equalsIgnoreCase(go)) {
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));
			String yesNo = myForm.getYesNo();
			GyglGyfdyModel model = new GyglGyfdyModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getWmgyCxTopTr();
			rs = service.serv_getWmgylCxList(yesNo, userType, userName, model);
		}	
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", rs);
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ldList", getList.GetLdList());
//		request.setAttribute("qshList", getList.GetQshList2(lddm, lc));		
		request.setAttribute("chkList",dao.getChkList(3));			
		request.setAttribute("tableName", "jhzy_wmgylsqb");
		request.setAttribute("realTable", "jhzy_wmgylsqb");
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);
//		读写权判断
		String writeAble = (Base.chkUPower(userName, "wmgylManage.do", userOnLine.equalsIgnoreCase("student")) == 1) ? "yes" : "no";
		HashMap<String, String> map = service.serv_isLz(userName);
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			if (map != null && map.size()>0) {
//				writeAble = "no";
				request.setAttribute("isstu", "yes");
			}else {
				request.setAttribute("errMsg", "学生用户无权访问该页面！");
				return new ActionForward("/errMsg.do", false);
			}
		}
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble",writeAble);	
		return mapping.findForward("wmgylQuery");
	}
	/**
	 *文明公寓楼审核查询
	 */
	public ActionForward wmgylSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO  dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		String go = request.getParameter("go");
		String xn   = myForm.getXn();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if (xn == null) {
			xn = Base.currXn;
			myForm.setXn(xn);
		}
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if ("chk".equalsIgnoreCase(request.getParameter("chk"))) {
			String pkStr = request.getParameter("pkVStr");
			String str = DealString.toGBK(request.getParameter("str"));
			String whichpk = service.serv_WmgylSh(pkStr,str,request);
			if ("".equals(whichpk)) {
				request.setAttribute("done", "ok");
			}else {
				request.setAttribute("done", "no");
			}
		}
		if ("go".equalsIgnoreCase(go)) {
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));
			String yesNo = myForm.getYesNo();
			GyglGyfdyModel model = new GyglGyfdyModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getWmgyCxTopTr();;
			rs = service.serv_getWmgylCxList(yesNo, userType, userName, model);
		}	
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", rs);
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ldList", getList.GetLdList());
//		request.setAttribute("qshList", getList.GetQshList2(lddm, lc));		
		request.setAttribute("chkList",dao.getChkList(3));			
		request.setAttribute("tableName", "jhzy_wmgylsqb");
		request.setAttribute("realTable", "jhzy_wmgylsqb");
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);
//		读写权判断
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "wmgylManage.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
		return mapping.findForward("wmgylSh");
	}
	/**
	 * @descrip 公寓辅导员信息修改
	 * roomCompartition
	 */
	public ActionForward gyfdyupdate(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm) form;
//		HttpSession session = request.getSession(false);
//		String userName	= session.getAttribute("userName").toString();
		GyglGyfdyModel model = new GyglGyfdyModel();
		HashMap<String, String> map = new HashMap<String, String>();
		String lddm = myForm.getLddm();
		String lch = myForm.getLcdm();
		String xq = myForm.getXq();
		String xn = myForm.getXn();
		String yhm = request.getParameter("pkValue");
//		String xydm = myForm.getXydm();
		String isView = DealString.toString(request.getParameter("isView"));
		
		String doType = request.getParameter("doType");
		boolean doFlag = false;
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("save")){
			BeanUtils.copyProperties(model, myForm);
			FormModleCommon.formToGBK(myForm);
			model.setYhm(yhm);
			doFlag = service.serv_WmgylUpdate(model,request);
			if(doFlag){
				request.setAttribute("done", "ok");
			}else{
				request.setAttribute("done", "no");
			}
		}
		if(Base.isNull(xq)){
			myForm.setNj(Base.currXq);
		}
		if(Base.isNull(xn)){
			myForm.setXn(Base.currXn);
		}
		if(StringUtils.isNotNull(yhm)){
			model.setYhm(yhm);
			map = service.ser_wmgylInfo(model);
		}
		setNjXyZyBjList(request, myForm);
		request.setAttribute("isView", isView);
		request.setAttribute("lcList", service.getSsLcList(lddm));
		request.setAttribute("extssxxList", service.getSsmList(lddm, lch,"yes","",xn,xq));
		request.setAttribute("ssxxList", service.getSsmList(lddm, lch,"no","",xn,xq));
		request.setAttribute("ldList", service.getSsLdList());
		request.setAttribute("yhList", service.getUserInfoList());
		request.setAttribute("rs", map);
		request.setAttribute("njList", Base.getNjList());
		return mapping.findForward("gyfdyupdate");
	}
	/**
	 *文明公寓楼单个审核查询
	 */
	public ActionForward wmgyldgSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO  dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
//		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
//		String userDep   = request.getSession().getAttribute("userDep").toString();
		String pk = request.getParameter("pkValue");
		String xn   = myForm.getXn();
		HashMap<String, String> map = new HashMap<String, String>();
		if (xn == null) {
			xn = Base.currXn;
			myForm.setXn(xn);
		}
		if ("chk".equalsIgnoreCase(request.getParameter("chk"))) {
			String pkStr = request.getParameter("pkValue");
			String str = DealString.toGBK(myForm.getXxsh());
			Boolean whichpk = service.serv_WmgyldgSh(pkStr,str,request);
			if (whichpk) {
				request.setAttribute("done", "ok");
			}else {
				request.setAttribute("done", "no");
			}
		}
		if(StringUtils.isNotNull(pk)){
			map = service.ser_wmgylInfosh(pk);
		}
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("rs", map);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("chkList",dao.getChkList(3));			
		request.setAttribute("tableName", "jhzy_wmgylsqb");
		request.setAttribute("realTable", "jhzy_wmgylsqb");
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);	
		return mapping.findForward("gyfdydgsh");
	}
	/**
	 *文明公寓楼申请表打印
	 */
	public ActionForward wmgylprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
//		GyglJhzyForm myForm = (GyglJhzyForm) form;
		String pk = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		if(StringUtils.isNotNull(pk)){
			map = service.ser_wmgylInfosh(pk);
		}
		request.setAttribute("rs", map);
		return mapping.findForward("wmgylsqb");
	}
	/**
	 * 辅导员入住信息管理
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ActionForward fdyrzInfoManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, IllegalArgumentException, SecurityException, NoSuchMethodException{
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();		
		GetDropDownList getList = new GetDropDownList();
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			myForm.setZgh(DealString.toGBK(myForm.getZgh()).trim());
			myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
			FdyRzxxModel model = new FdyRzxxModel();
			BeanUtils.copyProperties(model,myForm);
			topTr = service.getFdyRzXxTopTr();
			rs = service.serv_getFdyRzxxList(model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm)?" ":lddm,userName));
		request.setAttribute("qshList", getList.GetBlQshList(Base.isNull(lddm)?" ":lddm, Base.isNull(lc)?" ":lc));
		request.setAttribute("tableName","view_fdyrzxx");
		request.setAttribute("realTable","fdyrzxxb");
//		读写权判断
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "fdyrzInfoManage.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
		return mapping.findForward("fdyrzInfoManage");
	}
	/**
	 *  辅导员入住信息添加
	 * @throws Exception 
	 */
	public ActionForward fdyRzInfoAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		GetDropDownList getList = new GetDropDownList();
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		String zgh  =request.getParameter("zgh");
		String act  = DealString.toGBK(request.getParameter("act"));
		String doType = request.getParameter("doType");
	    String pkValue = request.getParameter("pkValue");
	    if(!Base.isNull(pkValue)){
	    	zgh = pkValue;
	    }
		if("save".equalsIgnoreCase(doType)){
			myForm.setZgh(DealString.toGBK(zgh));
			myForm.setXm(DealString.toGBK(request.getParameter("xm")).trim());
			FdyRzxxModel model = new FdyRzxxModel();
			BeanUtils.copyProperties(model,myForm);
			boolean done=service.serv_fdyRzXxSave(model);
			request.setAttribute("done",done);
		}
		HashMap<String,String> map = service.getFdyRzXx(DealString.toGBK(zgh));
		request.setAttribute("rs", map);
		if(map!=null){
			if(!Base.isNull(map.get("lddm"))){
				lddm=map.get("lddm");
			}
			if(!Base.isNull(map.get("lc"))){
				lc=map.get("lc");
			}			
		}		
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm)?" ":lddm,""));
		request.setAttribute("qshList", getList.GetBlQshList(Base.isNull(lddm)?" ":lddm, Base.isNull(lc)?" ":lc));
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("act",act);
		request.setAttribute("tableName","view_fdyrzxx");
		request.setAttribute("realTable","fdyrzxxb");
		return mapping.findForward("fdyRzInfoAdd");
	}
    /**
     *辅导员入住信批量删除
     */
	public ActionForward fdyRzInfoDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String pkVStr = DealString.toGBK(request.getParameter("pkVStr"));
		service.serv_fdyRzxxDel(pkVStr);
		return fdyrzInfoManage(mapping, form, request, response);
	}
	/**
	 * 寝室长管理
	 * @throws Exception 
	 */
	public ActionForward qszManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		GetDropDownList getList = new GetDropDownList();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();				
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
//		公寓辅导员负判断
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}				
	        
		if("xy".equalsIgnoreCase(userType)){
        	myForm.setXydm(userDep);
        }
        if("go".equalsIgnoreCase(request.getParameter("go"))){
        	myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
        	myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
        	myForm.setSfzz(DealString.toGBK(myForm.getSfzz()).trim());
        	
        	QszXxModel model = new QszXxModel();
			BeanUtils.copyProperties(model,myForm);
			topTr = service.getQszXxTopTr();
			rs = service.getQszXxCxList(model,userType,userName);
        } 
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm)?" ":lddm,userName));
		request.setAttribute("qshList", getList.GetBlQshList(Base.isNull(lddm)?" ":lddm, Base.isNull(lc)?" ":lc));
		FormModleCommon.setNjXyZyBjList(request);	
		request.setAttribute("tableName","view_qszxx");
		request.setAttribute("realTable","qszxxb");
		
		//公寓辅导员负责楼层寝室列表限制
		gyglDao.setGyfdy(request, userName, lddm, lc, Base.currXn, Base.currXq);		
		//		读写权判断
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "qszManage.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");		
		return mapping.findForward("qszManage");
	}
	/**
	 * 寝室长添加
	 * @throws Exception 
	 */
	public ActionForward qszAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String xh     = DealString.toGBK(request.getParameter("xh"));
	    String ldmc="";
	    String qsh ="";
	    String lc = "";
	    String ssbh=request.getParameter("ssbh");
		String doType = request.getParameter("doType");
		myForm.setLxdh(DealString.toGBK(request.getParameter("lxdh")));
		myForm.setBz(DealString.toGBK(request.getParameter("bz")));
		myForm.setDzyx(DealString.toGBK(request.getParameter("dzyx")));
		myForm.setQsz(xh);
		if("save".equalsIgnoreCase(doType)){
			QszXxModel model = new QszXxModel();
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_QszXxSave(model,"");
			request.setAttribute("done",done);
		}	
		HashMap<String,String> map    = CommonQueryDAO.getStuInfo(xh);
		HashMap<String,String> mapZs    = CommonQueryDAO.getStuZsInfo(xh);
		ldmc = mapZs.get("ldmc");
		lc = mapZs.get("lc");
		qsh = mapZs.get("qsh");
		ssbh = mapZs.get("ssbh");		
		request.setAttribute("ldmc",ldmc);
		request.setAttribute("lc",lc);
		request.setAttribute("qsh",qsh);
		request.setAttribute("ssbh",ssbh);		
		request.setAttribute("rs", map);
		return mapping.findForward("qszAdd");
	}
	/**
	 * 寝室长修改
	 * @throws Exception 
	 */
	public ActionForward qszModi(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String pkValue     = DealString.toGBK(request.getParameter("pkValue"));
	    String ldmc="";
	    String qsh ="";
	    String lc = "";
	    String ssbh=request.getParameter("ssbh");
		String doType = request.getParameter("doType");
		String act    = request.getParameter("act");
		myForm.setLxdh(DealString.toGBK(request.getParameter("lxdh")));
		myForm.setBz(DealString.toGBK(request.getParameter("bz")));
		myForm.setDzyx(DealString.toGBK(request.getParameter("dzyx")));
		//myForm.setQsh(DealString.toGBK("xh"));
		if("save".equalsIgnoreCase(doType)){
			QszXxModel model = new QszXxModel();
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_QszXxSave(model,pkValue);
			if(done){
				pkValue =model.getXh()+ model.getSsbh()+model.getRzrq();
			}
			request.setAttribute("done",done);
		}	
		HashMap<String,String> map    = service.getQszInfo(pkValue);
		map.put("xh",map.get("qsz"));
		ldmc = map.get("ldmc");
		lc = map.get("lc");
		qsh = map.get("qsh");
		ssbh = map.get("ssbh");		
		request.setAttribute("ldmc",ldmc);
		request.setAttribute("lc",lc);
		request.setAttribute("qsh",qsh);
		request.setAttribute("ssbh",ssbh);		
		request.setAttribute("rs", map);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("act",act);
		return mapping.findForward("qszModi");
	}
	/**
     * 寝室长信息批量删除
     */
	public ActionForward qszInfoDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkVStr = request.getParameter("pkVStr");
		service.serv_qszDel(pkVStr);
		return qszManage(mapping, form, request, response);
	}	
	/**
	 * 公寓党建信息管理
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ActionForward gydjInfoManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, InvocationTargetException,
			IllegalArgumentException, SecurityException, NoSuchMethodException {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userDep = request.getSession().getAttribute("userDep").toString();
		if (userType.equalsIgnoreCase("xy")) {
			myForm.setXydm(userDep);
		}
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		GetDropDownList getList = new GetDropDownList();
		String lddm = request.getParameter("lddm");
		String cs   = request.getParameter("cs");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			GydjglxxbModel model = new GydjglxxbModel();
			BeanUtils.copyProperties(model,myForm);
			topTr = service.getGydjXxTopTr();
			rs = service.serv_getGydjxxList(model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList
				.GetLcList(Base.isNull(lddm) ? " " : lddm,userName));
		request.setAttribute("qshList", getList.GetBlQshList(
				Base.isNull(lddm) ? " " : lddm, Base.isNull(cs) ? " " : cs));
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("tableName","view_gydjglxxb");
		request.setAttribute("realTable","gydjglxxb");
		request.setAttribute("userType", userType);
//		读写权判断
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "gydjInfoManage.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");
		return mapping.findForward("gydjInfoManage");
	}
	/**
	 *  公寓党建信息添加
	 * @throws Exception 
	 */
	public ActionForward gydjInfoAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String guid  = "";
		String act  = DealString.toGBK(request.getParameter("act"));
		String doType = request.getParameter("doType");
	    String pkValue = request.getParameter("pkValue");
//	    String ssgyzb = request.getParameter("ssgyzb");
	    String xh = request.getParameter("xh");
	    if(!Base.isNull(pkValue)){
	    	guid = pkValue;
	    }
		if("save".equalsIgnoreCase(doType)){
			GydjglxxbModel model = new GydjglxxbModel();
			BeanUtils.copyProperties(model,myForm);
			String[] sT = service.serv_gydjXxSave(model,request);
			guid = sT[1];
			request.setAttribute("done","1".equalsIgnoreCase(sT[0]));
		}
		HashMap<String,String> map = service.getGydjXx(guid);
		if (map.size()==0 && xh != null) {
			map.putAll(CommonQueryDAO.getStuInfo(xh));
			map.putAll(CommonQueryDAO.getStuZsInfo(xh));
		}
//		request.setAttribute("gyzbxx",CommonQueryDAO.dao_getInfo("gyzbb", null," where dm='"+ssgyzb+"'"));
//		if(!Base.isNull(ssgyzb)){
//			map.put("ssgyzb",ssgyzb);
//		}
		request.setAttribute("rs", map);
		request.setAttribute("gyzbList",service.ser_getGyzbList());
		request.setAttribute("khdxList",service.ser_getKhdxList());
		request.setAttribute("act",act);
		request.setAttribute("tableName","view_gydjglxxb");
		request.setAttribute("realTable","gydjglxxb");
		return mapping.findForward("gydjInfoAdd");
	}
	/**
     * 公寓党建信息批量删除
     */
	public ActionForward gydjInfoDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkVStr = request.getParameter("pkVStr");
		service.serv_gydjDel(pkVStr);
		return gydjInfoManage(mapping, form, request, response);
	}
	
	/*
	 * 申请项目下拉框
	 */
	public List<HashMap<String, String>> getCdList() {
		DAO dao = DAO.getInstance();
		String[] listEn = new String[] { "001", "002"};
		String[] listCn = new String[] { "星级寝室", "文明寝室"};
		return dao.arrayToList(listEn, listCn);
	}
	/**
	 *申请菜单选择
	 */
	public ActionForward xjwmqsSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		String xmdmV = request.getParameter("xmdm");
//		int xmdm = Integer.parseInt(Base.isNull(xmdmV) ? "0" : xmdmV);
//		switch (xmdm) {
//		case 1: {
			return new ActionForward("/jhzy_gygl.do?method=xjqsSq",false);
//		}
//		case 2: {
//			return new ActionForward("/jhzy_gygl.do?method=xjqsSq",false);
//		}		
//		}
//		request.setAttribute("list", getCdList());
//		return mapping.findForward("xjwmqsSq");
	}
	
	/**
	 * 星级寝室申请
	 * @throws Exception 
	 */
	public ActionForward xjqsSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GetDropDownList getList = new GetDropDownList();
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		HttpSession session = request.getSession();
		HashMap<String,String> map = new HashMap<String,String>();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String pkone = request.getParameter("pkone");
		String lddm = request.getParameter("lddm");
		String cs   = request.getParameter("lc");
		String qsh   = request.getParameter("qsh");
		String xjqswh   = request.getParameter("xjqswh");
		String xn = "";
		String xq = "";
		String yf = "";		;		
//		公寓辅导员负判断
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}

		if ("save".equalsIgnoreCase(doType)) {
			YxlcqszModel model = new YxlcqszModel();
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.saveXjqs_ser(model);
			request.setAttribute("done",done);			
		}

		if ("stu".equals(userType)) {
			String exists = service.isChecking_ser(userName);
			if ("yes".equals(exists)) {
				request.setAttribute("exists", exists);
			}
			map = service.getXszsqk_ser(userName);
			if (map == null) {
				request.setAttribute("rzxx", "no");
			}else{
				lddm=map.get("lddm");
				cs = map.get("lc");
				request.setAttribute("ssbh", map.get("ssbh"));
			}
		} else {			
			if ("xjqswh".equalsIgnoreCase(doType)) {
				if ("modi".equalsIgnoreCase(act)) {
					HashMap<String,String> temmap = service.getSsxx_ser(pkone.substring(14,pkone.length()));
					lddm = temmap.get("lddm");
					cs = temmap.get("cs");
					qsh = temmap.get("qsh");
				}
				xjqswh = "yes";
			}
			if(!Base.isNull(lddm) && !Base.isNull(cs) && !Base.isNull(qsh)){
				map = service.getXjqsqk_ser(lddm, cs, qsh);
				if(map != null){
					request.setAttribute("ssbh", map.get("ssbh"));
				}
				yf = map.get("yf");
				xn = map.get("xn");
				xq = map.get("xq");
			}		
		}	
		xn = Base.isNull(xn)?Base.currXn:xn;
		xq = Base.isNull(xq)?Base.currXq:xn;
		yf = Base.isNull(yf)?DateUtils.getMonth():yf;;
		request.setAttribute("xjqswh", xjqswh);
		request.setAttribute("rs",map != null ? map : new HashMap<String,String>());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm)?" ":lddm,userName));
		request.setAttribute("qshList", getList.GetQshList2(Base.isNull(lddm)?" ":lddm, Base.isNull(cs)?" ":cs,userName));
//		request.setAttribute("yfList",DAO.getInstance().getYfList());
		request.setAttribute("wsJc",service.serv_wsjcDbInfo(xn, xq, yf, lddm, cs, qsh));
		request.setAttribute("xn",xn);
		request.setAttribute("xqmc",CommonQueryDAO.getXqMc(xq));
		request.setAttribute("yf",yf);
		//公寓辅导员负责楼层寝室列表限制
		gyglDao.setGyfdy(request, userName, lddm, cs, Base.currXn, Base.currXq);

		return mapping.findForward("xjqsSq");
	}
	/**
	 * 星级寝室申请表打印
	 */
	public ActionForward sqbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String forwardPage = "";
		String xmk = request.getParameter("xmk");
//		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String lddm = request.getParameter("lddm");
		String cs   = request.getParameter("lc");
		String qsh   = request.getParameter("qsh");
		String qsjsqk   = DealString.toGBK(request.getParameter("qsjsqk"));
		String dj   = DealString.toGBK(request.getParameter("dj"));
		HashMap<String,String> map = new HashMap<String,String>();
		if ("xjqs".equalsIgnoreCase(xmk)) {
			map = service.getXjqsqk_ser(lddm, cs, qsh);
			String ssrs = service.getSsrs_ser(map.get("ssbh"));
			String ssbj = service.getSsbj_ser(map.get("ssbh"));
			map.put("ssrs", ssrs);
			map.put("ssbj", ssbj);
			map.put("xn", Base.currXn);
			map.put("qsjsqk", qsjsqk);
			map.put("dj", dj);
			map.put("month", GetTime.getSystemTime().substring(5,7));
			forwardPage = "xjqsSqb";
		}
		String xn = "";
		String xq = "";
		String yf = "";
		if(map.size()>0){
			 xn = map.get("xn");
			 xq = map.get("xq");
			 yf = map.get("yf");
		}
		request.setAttribute("wsJc",service.serv_wsjcDbInfo(xn, xq, yf, lddm, cs, qsh));
		request.setAttribute("rs", map);
		return mapping.findForward(forwardPage);
	}
	/**
	 * 审核菜单选择
	 */
	public ActionForward xjwmqsSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String userType = request.getSession().getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
//		String xmdmV = request.getParameter("xmdm");
//		int xmdm = Integer.parseInt(Base.isNull(xmdmV) ? "0" : xmdmV);
//		switch (xmdm) {
//		case 1: {
			return new ActionForward("/jhzy_gygl.do?method=xjqsShManage",false);
//		}
//		case 2: {
//			return new ActionForward("/jhzy_gygl.do?method=wmqsShManage",false);
//		}		
//		}
//		request.setAttribute("list", getCdList());
//		return mapping.findForward("xjwmqsSh");
	}
	/**
	 * 星级寝室审核
	 * @throws Exception 
	 */
	public ActionForward xjqsShManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		DAO  dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep   = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String xn = request.getParameter("xn");
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		String pkString = request.getParameter("pkVStr");
		YxlcqszModel model = new YxlcqszModel();
		List<HashMap<String,String>> rs = null;
//		公寓辅导员负判断
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}
		if("xy".equals(userType)){
			myForm.setXydm(userDep);
		}
		if(xn==null){
			myForm.setXn(Base.currXn);
		}	
		if(myForm.getYf()==null){
			myForm.setYf(GetTime.getSystemTime().substring(5,7));
		}		
		BeanUtils.copyProperties(model, myForm);
		if ("shtg".equalsIgnoreCase(doType)||"shbtg".equalsIgnoreCase(doType)) {	
			request.setAttribute("result", service.xjqsPlsh_ser(doType, pkString, userType));		
		}
		if("query".equalsIgnoreCase(doType)){
			rs = service.queryXjqs_ser(model, userType,userName);
		}
		String[] colEn ={"pk","xn","qsh","ldmc","cs","lxdh","dj","shzt"};
		String[] colCn ={"pk","学年","寝室号","楼栋名称","楼层","联系电话","申报等级","审核状态"};
		List<HashMap<String, String>> topTr = dao.arrayToList(colEn, colCn);
		
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs == null ? 0 :rs.size());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm)?" ":lddm,userName));
		request.setAttribute("qshList", getList.GetQshList2(Base.isNull(lddm)?" ":lddm, Base.isNull(lc)?" ":lc,userName));			
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("chkList",dao.getChkList(3));
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);
		//公寓辅导员负责楼层寝室列表限制
		gyglDao.setGyfdy(request, userName, lddm, lc, Base.currXn, Base.currXq);

		return mapping.findForward("xjqsShManage");
	}
	
	/**
	 * 星级寝室单个审核
	 * @throws Exception 
	 */
	public ActionForward xjqsChek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String view = request.getParameter("view");
		String dj = DealString.toGBK(request.getParameter("dj"));
		String shzt = DealString.toGBK(request.getParameter("shzt"));
		String xn = "";
		String xq = "";
		String yf = "";		;
        String lddm = "";
        String cs = "";
        String qsh="";
		DAO  dao = DAO.getInstance();
//		公寓辅导员负判断
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}
		
		if("save".equals(doType)){
			request.setAttribute("result", service.xjqsDgsh_ser(pkValue, dj, shzt, userType));
		}
		HashMap<String,String> rs = service.xjqsDgshPage_ser(pkValue,userType,view);
		if(rs.size()>0){
			 xn = rs.get("xn");
			 xq = rs.get("xq");
			 yf = rs.get("sqsj").substring(4, 6);
	         lddm =rs.get("lddm");
	         cs = rs.get("cs");
	         qsh=rs.get("qsh");
		}
		request.setAttribute("wsJc",service.serv_wsjcDbInfo(xn, xq, yf, lddm, cs, qsh));
		request.setAttribute("xn",xn);
		request.setAttribute("xqmc",CommonQueryDAO.getXqMc(xq));
		request.setAttribute("yf",yf);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		request.setAttribute("view", view);
		request.setAttribute("chkList",dao.getChkList(3));
		request.setAttribute("userType",userType);
		return mapping.findForward("xjqsChek");
	}
	/**
	 * 星级寝室审核菜单选择
	 * @throws Exception 
	 */
	public ActionForward xjwmqsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		if("student".equalsIgnoreCase(userOnline)){
			return new ActionForward("/jhzy_gygl.do?method=xjwmqsXsSqJg",false);
		}
//		String xmdmV = request.getParameter("xmdm");
//		int xmdm = Integer.parseInt(Base.isNull(xmdmV) ? "0" : xmdmV);
//		switch (xmdm) {
//		case 1: {
			return new ActionForward("/jhzy_gygl.do?method=xjqsManange",false);
//		}
//		case 2: {
//			return new ActionForward("/jhzy_gygl.do?method=wmqsManange",false);
//		}		
//		}
//		request.setAttribute("list", getCdList());
//		return mapping.findForward("xjwmqsManage");
	}
	/**
	 * 星级寝室审核结果查询
	 * @throws Exception 
	 */
	public ActionForward xjqsManange(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		DAO  dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep   = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String doType = request.getParameter("doType");
		String xn = request.getParameter("xn");
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		String pkVStr = request.getParameter("pkVStr");
		YxlcqszModel model = new YxlcqszModel();
		List<HashMap<String,String>> rs = null;
//		公寓辅导员负判断
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}
		
		if("xy".equals(userType)){
			myForm.setXydm(userDep);
			request.setAttribute("xydm", userDep);
		}
		if(xn==null){
			myForm.setXn(Base.currXn);
		}	
		if(myForm.getYf()==null){
			myForm.setYf(GetTime.getSystemTime().substring(5,7));
		}		
		BeanUtils.copyProperties(model, myForm);
		if("del".equalsIgnoreCase(doType)){
			request.setAttribute("result", service.delXjqs_ser(pkVStr));
		}
		if("query".equalsIgnoreCase(doType)){
			rs = service.queryXjqsShjg_ser(model, userType, userName);
		}
		String[] colEn ={"pk","xn","qsh","ldmc","cs","lxdh","dj","fdysh","xysh","xxsh"};
		String[] colCn ={"pk","学年","寝室号","楼栋名称","楼层","联系电话","申报等级","辅导员审核",Base.YXPZXY_KEY+"审核","学校审核"};
		List<HashMap<String, String>> topTr = dao.arrayToList(colEn, colCn);
		if("xx".equals(userType)||"admin".equals(userType)){
			request.setAttribute("writeAble", "yes");
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs == null ? 0 :rs.size());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm)?" ":lddm,userName));
		request.setAttribute("qshList", getList.GetQshList2(Base.isNull(lddm)?" ":lddm, Base.isNull(lc)?" ":lc,userName));			
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("chkList",dao.getChkList(3));
		request.setAttribute("tableName","view_xjqsxx");
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);
		//公寓辅导员负责楼层寝室列表限制
		gyglDao.setGyfdy(request, userName, lddm, lc, Base.currXn, Base.currXq);

		//		读写权判断
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "xjwmqsManage.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
		return mapping.findForward("xjqsManange");
	}
	
	/**
     * 寝室卫生检查
     */
	public ActionForward dormCheckManage (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userName = (String)session.getAttribute("userName");
		String lddm = myForm.getLddm();
		String lc = myForm.getLcdm();
		String checkedValue = "";
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		if("stu".equalsIgnoreCase(userType)){//学生无操作权限
			request.setAttribute("errMsg", "学生无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
        if("go".equalsIgnoreCase(request.getParameter("go"))){
        	GyglGyfdyModel model = new GyglGyfdyModel();      	
        	myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
        	myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
    		BeanUtils.copyProperties(model,myForm);
    		ArrayList<List> rs = service.serv_qswsjcQuery(model);
        	List<HashMap<String, String>> topTr = service.getQswsjcTopTr();
    	    request.setAttribute("rsNum", (rs != null && !"".equals(rs))?rs.size():"0");   		 
    		request.setAttribute("rs", rs);
    		request.setAttribute("topTr", topTr);
    		checkedValue = service.serv_getqswsjcChecked(model);
        }
        if(Base.isNull(xn)){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(xq)){
			myForm.setXq(Base.currXq);
		}
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		String jgwh = request.getParameter("jgwh");
		if("yes".equals(jgwh)){
			myForm.setJcsj("");
		}
		request.setAttribute("iswsdj",StringUtils.isArrayNotNull(myForm.getWsdj())?myForm.getWsdj()[0]:"");
		GetDropDownList getList = new GetDropDownList();
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm)?" ":lddm,userName));
		request.setAttribute("qshList", getList.GetQshList2(Base.isNull(lddm)?" ":lddm, Base.isNull(lc)?" ":lc,userName));
		request.setAttribute("checkedValue",checkedValue);
		request.setAttribute("userType",userType);
		return mapping.findForward("qswsjcManage");
	}
	/**
	 * 寝室卫生检查信息保存
	 * @throws Exception 
	 */
	public ActionForward qswsjcSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglGyfdyModel model = new GyglGyfdyModel();
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		BeanUtils.copyProperties(model,myForm);
		Boolean result = service.serv_qswsjcSave(model);
		if(result){
			request.setAttribute("done", "yes");
		}else {
			request.setAttribute("done", "no");
		}
		return dormCheckManage(mapping, form, request, response);
	}
	/**
     * 寝室卫生检查
     */
	public ActionForward resultQuManage (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		HttpSession session = request.getSession(false);
		String userName	= session.getAttribute("userName").toString();
		String userType = (String)session.getAttribute("userType");
		String checkedValue = "";
		String lddm = myForm.getLddm();
		String lc = myForm.getLcdm();
		if("stu".equalsIgnoreCase(userType)){//学生无操作权限
			request.setAttribute("errMsg", "学生无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
        if("go".equalsIgnoreCase(request.getParameter("go"))){
        	GyglGyfdyModel model = new GyglGyfdyModel();      	
        	myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
        	myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
    		BeanUtils.copyProperties(model,myForm);
    		List<String[]> rs = service.serv_qswsjcjgQuery(model);
        	List<HashMap<String, String>> topTr = service.getQswsjccxTopTr();
    	    request.setAttribute("rsNum", (rs != null && !"".equals(rs))?rs.size():"0");   		 
    		request.setAttribute("rs", rs);
    		request.setAttribute("topTr", topTr);
        }
		FormModleCommon.setNjXyZyBjList(request);
		String jgwh = request.getParameter("jgwh");
		if("yes".equals(jgwh)){
			myForm.setJcsj("");
		}
		GetDropDownList getList = new GetDropDownList();
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm)?" ":lddm,userName));
		request.setAttribute("qshList", getList.GetQshList2(Base.isNull(lddm)?" ":lddm, Base.isNull(lc)?" ":lc,userName));
		request.setAttribute("checkedValue",checkedValue);
		request.setAttribute("userType",userType);
		return mapping.findForward("qswsjcManagecx");
	}
	/**
	 * 自管会工作人员管理
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ActionForward zghryInfoManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, InvocationTargetException,
			IllegalArgumentException, SecurityException, NoSuchMethodException {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userDep = request.getSession().getAttribute("userDep").toString();
		if (userType.equalsIgnoreCase("xy")) {
			myForm.setXydm(userDep);
		}
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();		
		GetDropDownList getList = new GetDropDownList();
		String lddm = request.getParameter("lddm");
		String cs   = request.getParameter("cs");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
			myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
			ZghryModel model = new ZghryModel();
			BeanUtils.copyProperties(model,myForm);
			topTr = service.getZghryTopTr();
			rs = service.serv_getZghryList(model);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("zghbmList", service.GetZghbmList());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList
				.GetLcList(Base.isNull(lddm) ? " " : lddm,userName));
		request.setAttribute("qshList", getList.GetBlQshList(
				Base.isNull(lddm) ? " " : lddm, Base.isNull(cs) ? " " : cs));
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("tableName","view_zghryxxb");
		request.setAttribute("realTable","zghryxxb");
		request.setAttribute("userType", userType);
//		读写权判断
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "zghryInfoManage.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");
		return mapping.findForward("zghryInfoManage");
	}
	/**
	 *  自管会工作人员添加
	 * @throws Exception 
	 */
	public ActionForward zghryInfoAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String act  = DealString.toGBK(request.getParameter("act"));
		String doType = request.getParameter("doType");
	    String pkValue = request.getParameter("pkValue");
	    String xh = request.getParameter("xh");
	    if(!Base.isNull(pkValue)){
	    	xh = pkValue;
	    }
		if("save".equalsIgnoreCase(doType)){
			ZghryModel model = new ZghryModel();
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_zghrySave(model,request);
			request.setAttribute("done",done);
		}
		HashMap<String,String> map = service.getZghryXx(xh);
		if (map.size()==0 && xh != null) {
			map.putAll(CommonQueryDAO.getStuInfo(xh));
			map.putAll(CommonQueryDAO.getStuZsInfo(xh));
		}
		request.setAttribute("rs", map);
		
		request.setAttribute("act",act);
		request.setAttribute("zghbmList", service.GetZghbmList());
		request.setAttribute("tableName","view_zghryxxb");
		request.setAttribute("realTable","zghryxxb");
		return mapping.findForward("zghryInfoAdd");
	}
	/**
     * 自管会工作人员批量删除
     */
	public ActionForward zghryInfoDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkVStr = request.getParameter("pkVStr");
		service.serv_zghryDel(pkVStr);
		return zghryInfoManage(mapping, form, request, response);
	}
	/**
	 * 公寓辅导员考核查询
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ActionForward gyfdyCheckManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, IllegalArgumentException, SecurityException, NoSuchMethodException{
        HttpSession session = request.getSession();
        String userType = session.getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
        GyglJhzyForm myForm = (GyglJhzyForm)form;
		GetDropDownList getList = new GetDropDownList();
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		if(xn==null){
			xn = Base.currXn;
			myForm.setXn(xn);
		}
		if(xq==null){
			xq = Base.currXq;
			myForm.setXq(xq);
		}
		String lddm = myForm.getLddm();
		String lc   = myForm.getLc();
		
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			myForm.setZgh(DealString.toGBK(myForm.getZgh()).trim());
			myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
			GyfdyKhModel model = new GyfdyKhModel();
			BeanUtils.copyProperties(model,myForm);
			topTr = service.getGyfdyKhTopTr();
			rs = service.getgyfdyCheckList(model, userType, userName); 
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm) ? " " : lddm,userName));
		request.setAttribute("qshList", getList.GetBlQshList(Base.isNull(lddm) ? " " : lddm, Base.isNull(lc) ? " " : lc));
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("tableName","view_gyfdykhxx");
		request.setAttribute("realTable","gyfdykhxxb");
//		读写权判断
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "gyfdyCheckManage.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");
		return mapping.findForward("gyfdyCheckManage");
	}
	/**
	 * 公寓辅导员考核添加
	 * @throws Exception 
	 */
	public ActionForward gyfdyCheckAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO  dao = DAO.getInstance();
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String yf = request.getParameter("yf");
		String user = request.getParameter("zgh");
		String pkValue = request.getParameter("pkValue");
		String act  = request.getParameter("act");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		if(xn==null){
			xn = Base.currXn;
		}
		if(xq==null){
		    xq = Base.currXq;
		}
        if(Base.isNull(pkValue)){
        	pkValue=xn+xq+yf+user;
        }
		if("save".equalsIgnoreCase(request.getParameter("doType"))){			
			GyfdyKhModel model = new GyfdyKhModel();
			BeanUtils.copyProperties(model,myForm);
			boolean done = service.serv_gyfdyCheckSave(model, "");
			if(done){
				pkValue = model.getXn()+model.getXq()+model.getYf()+model.getZgh();
			}
			request.setAttribute("done",done);
		}		
		HashMap<String,String>map = new HashMap<String, String>();
		map = service.getGyfdyKhXx(pkValue);
		if(map.size()>0&&!Base.isNull(act)){
			user = map.get("zgh");
			xn = map.get("xn");
			xq = map.get("xq");
			yf = map.get("yf");
		}
		request.setAttribute("rs",map);		
		request.setAttribute("gyFdyList",gyglDao.gyGyFdyXxLlist(xn, xq));
		request.setAttribute("yfList", dao.getYfList());
		request.setAttribute("rsGyfdy",gyglDao.gyGyFdyInfo(user, xn, xq));
		request.setAttribute("rsWsjcf",service.serv_getWsjcXx(user, xn, xq, yf));
		request.setAttribute("xn",xn);
		request.setAttribute("xq",xq);
		request.setAttribute("xqmc",CommonQueryDAO.getXqMc(xq));
		request.setAttribute("act",act);
		return mapping.findForward("gyfdyCheckAdd");
	}
	/**
     *  公寓辅导员考核信息批量删除
     */
	public ActionForward gyfdyCheckDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkVStr = request.getParameter("pkVStr");
		service.serv_gyfdyCheckDel(pkVStr);
		return gyfdyCheckManage(mapping, form, request, response);
	}
	/**
	 * 自主息灯检查管理
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ActionForward zzxdjcInfoManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IllegalArgumentException,
			SecurityException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		GetDropDownList getList = new GetDropDownList();
		String lddm = request.getParameter("lddm");
		String cs = request.getParameter("cs");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = null;
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			ZzxdglModel model = new ZzxdglModel();
			BeanUtils.copyProperties(model, myForm);
			if ("save".equalsIgnoreCase(request.getParameter("act"))) {
				service.ser_saveZzxdjc(model);
				request.setAttribute("done", true);
			}
			topTr = service.getZzxdjcTopTr();
			rs = service.serv_getZzxdjcList(model);
		}
		rs1.put("jcrq", myForm.getJcrq());
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(
				Base.isNull(lddm) ? " " : lddm, userName));
		request.setAttribute("qshList", getList.GetBlQshList(
				Base.isNull(lddm) ? " " : lddm, Base.isNull(cs) ? " " : cs));
		request.setAttribute("yfList", dao.getYfList());
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("talbeName", "view_qswsjc");
		request.setAttribute("realTable", "qswsjc");
		request.setAttribute("userType", userType);
		// 读写权判断
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "zzxdjcInfoManage.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");
		return mapping.findForward("zzxdjcInfoManage");
	}
	
	/**
	 * 自主息灯检查信息查询
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ActionForward zzxdjcQueryDate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IllegalArgumentException,
			SecurityException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		String userName = session.getAttribute("userName").toString();
		GetDropDownList getList = new GetDropDownList();
		String lddm = request.getParameter("lddm");
		String cs = request.getParameter("cs");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = null;
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			ZzxdglModel model = new ZzxdglModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getZzxdjcQueryTopTr();
			rs = service.serv_getZzxdjcQueryList(model);
		}
		rs1.put("jcrq", myForm.getJcrq());
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(
				Base.isNull(lddm) ? " " : lddm, userName));
		request.setAttribute("qshList", getList.GetBlQshList(
				Base.isNull(lddm) ? " " : lddm, Base.isNull(cs) ? " " : cs));
		request.setAttribute("yfList", dao.getYfList());
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("tableName", "view_zghryxxb");
		request.setAttribute("realTable", "zghryxxb");

		return mapping.findForward("zzxdjcQueryDate");
	}
	/**
	 *星级文明寝室申请结果学生查询
	 */
	public ActionForward  xjwmqsXsSqJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String userName = request.getSession().getAttribute("userName").toString();
		ArrayList<String[]> rsBjqsz = new ArrayList<String[]>();
		rsBjqsz = service.serv_getXjwmqsList_stu(userName);
		ArrayList<String[]> rsEsjcz = new ArrayList<String[]>();
//		rsEsjcz = service.serv_getEsjczList_stu(userName);
		ArrayList<String[]> rsWjlz = new ArrayList<String[]>();
//		rsWjlz = service.getWjlzList_stu(userName);		
		request.setAttribute("rsBjqsz",rsBjqsz);//星级文明寝室申请结果集
		request.setAttribute("rsBjqszNum",rsBjqsz.size());
//		request.setAttribute("rsEsjcz",rsEsjcz);//二十佳层长申请结果集
//		request.setAttribute("rsEsjczNum",rsEsjcz.size());
//		request.setAttribute("rsWjlz",rsWjlz);// 五佳楼长申请结果集
//		request.setAttribute("rsWjlzNum",rsWjlz.size());			
		request.setAttribute("numCout", rsWjlz.size()+rsBjqsz.size()+rsEsjcz.size());
		return mapping.findForward("xjwmqsXsSqJg");
	}
	
	/**
	 * 自主息灯检查统计
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ActionForward zzxdjcTj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException,
			InvocationTargetException, IllegalArgumentException,
			SecurityException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
		GyglJhzyForm myForm = (GyglJhzyForm) form;
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		List<HashMap<String, String>> topTr = null;
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			ZzxdglModel model = new ZzxdglModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getZzxdjcTjTopTr(model.getTjlx());
			rs = service.serv_getZzxdjcTjList(model);
		}
		rs1.put("jcrq", myForm.getJcrq());
		rs1.put("tjlx", myForm.getTjlx());
		rs1.put("tjdw", myForm.getTjdw());
		request.setAttribute("rs1", rs1);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("yfList", dao.getYfList());
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("zzxdjcTj");
	}
}
