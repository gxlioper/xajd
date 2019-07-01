
package xgxt.xszz.whlg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WriteException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 武汉理工大学学生资助Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-09</p>
 */
public class XszzAction extends BaseAction {

	/**
	 * 学生家庭情况调查信息页面
	 * data_jtqkdc ----- 学生家庭情况调查
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward data_jtqkdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzWhlgService service = new XszzWhlgService();
		XszzWhlgdxActionForm whlgForm = (XszzWhlgdxActionForm) form;
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(request.getParameter("go"));
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delJtqkxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}	
		if (userType.equalsIgnoreCase("xy")) {
			whlgForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, whlgForm);
		List<HashMap<String, String>> topList = service.getJtqkdcTit();
		List<String[]> resList = service.getJtqkdcRes(queryModel,request);
		String xh = DealString.toGBK(whlgForm.getXh());
		whlgForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, whlgForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", whlgForm);
		request.setAttribute("realTable", "xszz_whlg_jtqkdcb");
		request.setAttribute("tableName", "view_xszz_whlg_jtqkdcb");
		return mapping.findForward("data_jtqkdc");
	}
	
	/**
	 * 家庭情况调查详细信息页面
	 * jtqkdc_queryOne ----- 家庭情况调查详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdc_queryOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzWhlgService service = new XszzWhlgService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//用户类型是学生则直接获取用户名
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String xn = Base.currXn;
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getJtqkdcxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("xn", Base.currXn);//当前学年
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jtqkdc_queryOne");
	}
	
	/**
	 * 保存家庭情况调查信息
	 * jtqkdc_save ---- 保存家庭情况调查信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdc_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzWhlgdxActionForm whlgdxActionForm = (XszzWhlgdxActionForm)form;
		JtqkdcModel jtqkdcModel = new JtqkdcModel();
		BeanUtils.copyProperties(jtqkdcModel, whlgdxActionForm);
		XszzWhlgService service = new XszzWhlgService();
		boolean bJg = service.saveJtqkdcxx(jtqkdcModel, request);// 保存贷款信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		String xh = jtqkdcModel.getXh();
		String xn = jtqkdcModel.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getJtqkdcxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("xn", Base.currXn);//当前学年
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jtqkdc_save");
	}
	
	/**
	 * 家庭情况调查信息导出<font color='red'>现在已经公用</font>
	 * jtqkdc_exp ----- 家庭情况调查信息导出
	 * @throws Exception
	 */
	public ActionForward jtqkdc_exp(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)throws Exception {		
		XszzWhlgService service = new XszzWhlgService();
		XszzWhlgdxActionForm whlgForm = (XszzWhlgdxActionForm) form;
		QueryModel queryModel = new QueryModel();		
		BeanUtils.copyProperties(queryModel, whlgForm);
		service.getJtqkdcExp(queryModel, response,request);
		return mapping.findForward("jtqkdc_exp");
	}
	
	/**################################      家庭经济困难生      ###########################################*/
	/**家庭经济困难生申请-显示-修改*/
	public ActionForward jtjjknssq(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		HttpSession session = request.getSession();
		String tableName = "view_whlgdx_sxzz_knsrdsq";
		String realTable = "whlgdx_sxzz_knsrdsqb";
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		String pk = "xh||xn";
		myForm.do_JtjjKns_GBK();
		this.doTypeRequest_SQ(myForm, request, service, realTable, tableName, userOnLine, userName, pk);
		return mapping.findForward("jtjj_knssqOne");
	}

	/**家庭经济困难生维护*/
	public ActionForward jtjjknswh(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String tableName = "view_whlgdx_sxzz_knsrdsq";
		String realTable = "whlgdx_sxzz_knsrdsqb";
		String pkColumn = "xh||xn";
		myForm.do_JtjjKns_GBK();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		rs = this.doTypeRequest_WH(myForm, rs, pkColumn, realTable, tableName, request, service);
		String writeAble = this.setXyUserWritAbleDisabled(myForm, userType, userDep);  //writAble
		this.setRequestAttribute(request, writeAble, realTable, tableName, userType, rs, myForm);
		return mapping.findForward("data_jtjjkns_sq");
	}	
	
	/**
	 * 导出困难生学生信息   <font color='red'>尽量公用<br>
	 * 其中导出的数据为全部字段，查询条件为/学年/年级/学院代码/专业代码/班级代码/学号/姓名</font>
	 * @throws Exception
	 */
	public ActionForward jtjj_kns_exp(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)throws Exception {
		XszzWhlgdxActionForm whlgForm = (XszzWhlgdxActionForm) form;
		String tableName = whlgForm.getTableName();
		XszzWhlgService service = new XszzWhlgService();
		QueryModel queryModel = new QueryModel();		
		BeanUtils.copyProperties(queryModel, whlgForm);
		service.getCommonExp(queryModel, response, request, tableName);
		return mapping.findForward("jtqkdc_exp");
	}
	
	/**###################################    国家助学贷款     ###########################################*/
	/** 国家助学贷款申请/修改/显示   提供主键列来查询数据*/
	public ActionForward gjzxdksq(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String tableName = "view_xszz_whlgdx_gjzxdk";   //视图
		String realTable = "xszz_whlgdx_gjzxdkb";       //表
		String pk =  "xh||xn";   //主键
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		myForm.do_Gjzxdk_GBK();
		this.doTypeRequest_SQ(myForm, request, service, realTable, tableName, userOnLine, userName, pk);
		return mapping.findForward("gjzxdk_queryOne");
	}
	
	/** 国家助学贷款维护 */
	public ActionForward gjzxdkwh(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String tableName = "view_xszz_whlgdx_gjzxdk";
		String realTable = "xszz_whlgdx_gjzxdkb";
		String pkColumn = "xh||xn";   //用于在删除的时候用
		myForm.do_Gjzxdk_GBK();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		rs = this.doTypeRequest_WH(myForm, rs, pkColumn, realTable, tableName, request, service);
		String writeAble = this.setXyUserWritAbleDisabled(myForm, userType, userDep);  //writAble
		this.setRequestAttribute(request, writeAble, realTable, tableName, userType, rs, myForm);
		return mapping.findForward("data_gjzxdk");
	}
	
	/** <font color='red'>国家助学贷款审批</font> 申请/修改/显示   提供主键列来查询数据 */
	public ActionForward gjzxdksp(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String tableName = "view_xszz_whlgdx_gjzxdk_sp";   //中国银行国家助学贷款申请审批视图
		String realTable = "xszz_whlgdx_gjzxdk_spb";       //中国银行国家助学贷款申请审批表
		String pk =  "xh||xn";   //主键[学号和学年]
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		myForm.do_Gjdk_Sp_GBK();
		this.doTypeRequest_SQ(myForm, request, service, realTable, tableName, userOnLine, userName, pk);
		return mapping.findForward("gjzxdk_sp_queryOne");
	}	
	
	/**国家助学贷款审批维护*/
	public ActionForward gjzxdkspwh(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String tableName = "view_xszz_whlgdx_gjzxdk_sp";   //中国银行国家助学贷款申请审批视图
		String realTable = "xszz_whlgdx_gjzxdk_spb";       //中国银行国家助学贷款申请审批表
		String pkColumn = "xh||xn";   //用于在删除的时候用
		myForm.do_Gjdk_Sp_GBK();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		rs = this.doTypeRequest_WH(myForm, rs, pkColumn, realTable, tableName, request, service);
		String writeAble = this.setXyUserWritAbleDisabled(myForm, userType, userDep);  //writAble
		this.setRequestAttribute(request, writeAble, realTable, tableName, userType, rs, myForm);
		return mapping.findForward("data_gjzxdksp");
	}
	
	/** <font color='red'>国家助学贷款还款协议</font> 申请/修改/显示   提供主键列来查询数据*/
	public ActionForward gjzxdkhkxysq(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String tableName = "view_xszz_whlgdx_gjzxdk_hkxy";   //中国银行国家助学贷款申请审批视图
		String realTable = "xszz_whlgdx_gjzxdk_hkxyb";       //中国银行国家助学贷款申请审批表
		String pk =  "xh||xn";   //主键[学号和学年]
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		myForm.do_Gjdk_Fkxy_GBK();
		this.doTypeRequest_SQ(myForm, request, service, realTable, tableName, userOnLine, userName, pk);
		return mapping.findForward("gjzxdk_fkxy_queryOne");
	}	
	
	/** <font color='red'>国家助学贷款还款协议维护</font> */
	public ActionForward gjzxdkhkxywh(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String tableName = "view_xszz_whlgdx_gjzxdk_hkxy";   
		String realTable = "xszz_whlgdx_gjzxdk_hkxyb";       
		String pkColumn = "xh||xn";   //用于在删除的时候用
		myForm.do_Gjdk_Fkxy_GBK();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		rs = this.doTypeRequest_WH(myForm, rs, pkColumn, realTable, tableName, request, service);
		String writeAble = this.setXyUserWritAbleDisabled(myForm, userType, userDep);
		this.setRequestAttribute(request, writeAble, realTable, tableName, userType, rs, myForm);
		return mapping.findForward("data_gjzxdkhkxywh");
	}
	
	/**<font color='red'>资助项目单个操作</font> 申请 修改  提供主键列来查询数据**/
	public ActionForward xszzsq(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String tableName = "view_xszz_common_new_zzbbxssqb";  
		String realTable = "xszz_common_new_zzbbxssqb";      
		String pk =  "xh||nd";   //主键[学号和学年]
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		myForm.do_Zzxm_GBK();
		this.doTypeRequest_SQ(myForm, request, service, realTable, tableName, userOnLine, userName, pk);
		XszzDao xszzDao = new XszzDao();
		request.setAttribute("zzxmList", xszzDao.getXszzZzxmList());  //资助项目的列表
		request.setAttribute("zdyzdXxxx",xszzDao.getXszzZdyzdXxxxList(myForm.getXmdm()));
		return mapping.findForward("xszzsq_queryOne");
	}	
	
	/**<font color='red'>资助项目维护</font> */
	public ActionForward xszzwh(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String tableName = "view_xszz_common_new_zzbbxssqb";  
		String realTable = "xszz_common_new_zzbbxssqb";      
		String pkColumn =  "xh||nd";   //主键[学号和学年]  //用于在删除的时候用
		String[] outputValues = new String[]{"nd","nj","xh","xm","xb","xymc","zymc","bjmc","sqsj"};
		myForm.do_Zzxm_GBK();
		String doType = myForm.getDoType();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		try{
		if(StringUtils.isNotNull(doType)){	
		if("del".equals(doType)){
			String pk = request.getParameter("cbVal");
			request.setAttribute("ok",myForm.setResult(service.batchDelRecord(pk, realTable, pkColumn)));//提示信息
		}
		rs = service.getCommonStuList(myForm, tableName, outputValues);      //返回结果集
		request.setAttribute("topTr", myForm.getColumnCN());   //返回表头信息
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		//this.doTypeRequest_WH(myForm, rs, pkColumn, realTable, tableName, request, service);
		String writeAble = this.setXyUserWritAbleDisabled(myForm, userType, userDep);
		this.setRequestAttribute(request, writeAble, realTable, tableName, userType, rs, myForm);
		return mapping.findForward("data_xszzwh");
	}	
	
	/**资助项目维护*/
/**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/	
	/**
	 * 数据单个保存
	 * @param myForm
	 * @param request  
	 * @param service    <font color='red'>servicce 对象</font>
	 * @param realTable  <font color='red'>表名</font>  
	 * @param tableName  <font color='red'>视图名</font>
	 * @param userOnLine  <font color='red'>check（student/teacher）</font>
	 * @param userName    <font color='red'>servicce 用户名</font>
	 * @param pk
	 */
	private void doTypeRequest_SQ(XszzWhlgdxActionForm myForm,HttpServletRequest request,XszzWhlgService service,
	String realTable,String tableName,String userOnLine,String userName,String pk){
		String doType = myForm.getDoType();
	try{
	if(StringUtils.isNull(request.getParameter("getXh"))){ //得到学生的信息（判断是否从子窗口中跳转）这里表示不是	
		if("add".equals(doType)){
			request.setAttribute("ok",myForm.setResult(service.saveCommonStuInfo(myForm,realTable,pk)));
		}else if("view".equals(doType) || "modi".equals(doType)){
			service.getCommon_PreStuAllInfo(myForm, tableName, pk);
		}else if(StringUtils.isNull(doType)){
			service.getSfTsxx(userName, userOnLine, myForm, tableName, pk); //主键是xh||xn
		}
		request.setAttribute("rs", myForm);
	}
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	/**
	 * 数据维护公用方法
	 * @param myForm  <font color='red'>XszzWhlgdxActionForm</font>
	 * @param rs      <font color='red'>结果集</font>
	 * @param pkColumn <font color='red'>主键列</font>
	 * @param realTable <font color='red'>表名</font>
	 * @param tableName <font color='red'>视图名</font>
	 * @param request <font color='red'>request</font>
	 * @param service <font color='red'>servicce 对象</font>
	 */
	private List<HashMap<String,String>> doTypeRequest_WH(XszzWhlgdxActionForm myForm,List<HashMap<String,String>> rs,
	String pkColumn,String realTable,String tableName,HttpServletRequest request,XszzWhlgService service){
		String doType = myForm.getDoType();
	try{
	if(StringUtils.isNotNull(doType)){	
		if("del".equals(doType)){
			String pk = request.getParameter("cbVal");
			request.setAttribute("ok",myForm.setResult(service.batchDelRecord(pk, realTable, pkColumn)));//提示信息
		}
		rs = service.getCommonStuList(myForm, tableName);      //返回结果集
		request.setAttribute("topTr", myForm.getColumnCN());   //返回表头信息
	}
	}catch(Exception e){
		e.printStackTrace();
	}
	return rs;
	}
	
	/** request setAttributes*/
	private void setRequestAttribute(HttpServletRequest request,String writeAble,String realTable,
	String tableName,String userType,List<HashMap<String,String>> rs,XszzWhlgdxActionForm myForm){
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName",tableName);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", (rs == null) == true  ? "0" : rs.size());
		request.setAttribute("form", myForm);
	}
	
	/**set 学院用户 没有 操作 增删改查 和导入导出 的权限*/
	private String setXyUserWritAbleDisabled(XszzWhlgdxActionForm myForm,String userType,String userDep){
		String writeAble = "yes";// 写权限   假定学院没有操作权限
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			myForm.setXydm(userDep);
		}
		return writeAble;
	}
	
//	/**just test,get sql language for calc*/
//	public ActionForward getColumn(ActionMapping mapping, ActionForm form, 
//	HttpServletRequest request, HttpServletResponse response) {
//		String tableName = "view_whlgdx_sxzz_knsrdsq";   
//		tableName = tableName.toUpperCase();
//		DAO comDao = DAO.getInstance();		
//		String[] columns = comDao.getColumnName(SQL_Util.getNoResultSql(tableName));
//		String publicStr = "insert into cxb(ssmk,cxbm,sxxm,sxbj,xxmc)values('assisNew', '" + tableName + "', '";
//		String endStr = "','0','10497');";
//		for(int i=0;i<columns.length;i++){
//			System.err.println(publicStr + columns[i] + endStr);
//		}
//		return null;
//	}
	
	/**
	 * 指定国家助学贷款报表（Test）
	 */
	public ActionForward xkxybbSpecialExp(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws IOException, WriteException{
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();     
		myForm.do_Gjdk_Fkxy_GBK();
		service.xkxybbSpecialExp(request, myForm, response);
		return mapping.findForward("jtqkdc_exp");
	}
	
}
