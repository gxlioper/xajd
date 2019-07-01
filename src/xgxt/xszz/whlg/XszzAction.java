
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
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �人����ѧѧ������Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-09</p>
 */
public class XszzAction extends BaseAction {

	/**
	 * ѧ����ͥ���������Ϣҳ��
	 * data_jtqkdc ----- ѧ����ͥ�������
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
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, whlgForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", whlgForm);
		request.setAttribute("realTable", "xszz_whlg_jtqkdcb");
		request.setAttribute("tableName", "view_xszz_whlg_jtqkdcb");
		return mapping.findForward("data_jtqkdc");
	}
	
	/**
	 * ��ͥ���������ϸ��Ϣҳ��
	 * jtqkdc_queryOne ----- ��ͥ���������ϸ��Ϣ
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
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String xn = Base.currXn;
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getJtqkdcxx(pkVal);// �õ�������ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);//��ǰѧ��
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jtqkdc_queryOne");
	}
	
	/**
	 * �����ͥ���������Ϣ
	 * jtqkdc_save ---- �����ͥ���������Ϣ 
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
		boolean bJg = service.saveJtqkdcxx(jtqkdcModel, request);// ���������Ϣ
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
			stuMap = service.getJtqkdcxx(pkVal);// �õ�������ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);//��ǰѧ��
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jtqkdc_save");
	}
	
	/**
	 * ��ͥ���������Ϣ����<font color='red'>�����Ѿ�����</font>
	 * jtqkdc_exp ----- ��ͥ���������Ϣ����
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
	
	/**################################      ��ͥ����������      ###########################################*/
	/**��ͥ��������������-��ʾ-�޸�*/
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

	/**��ͥ����������ά��*/
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
	 * ����������ѧ����Ϣ   <font color='red'>��������<br>
	 * ���е���������Ϊȫ���ֶΣ���ѯ����Ϊ/ѧ��/�꼶/ѧԺ����/רҵ����/�༶����/ѧ��/����</font>
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
	
	/**###################################    ������ѧ����     ###########################################*/
	/** ������ѧ��������/�޸�/��ʾ   �ṩ����������ѯ����*/
	public ActionForward gjzxdksq(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String tableName = "view_xszz_whlgdx_gjzxdk";   //��ͼ
		String realTable = "xszz_whlgdx_gjzxdkb";       //��
		String pk =  "xh||xn";   //����
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		myForm.do_Gjzxdk_GBK();
		this.doTypeRequest_SQ(myForm, request, service, realTable, tableName, userOnLine, userName, pk);
		return mapping.findForward("gjzxdk_queryOne");
	}
	
	/** ������ѧ����ά�� */
	public ActionForward gjzxdkwh(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String tableName = "view_xszz_whlgdx_gjzxdk";
		String realTable = "xszz_whlgdx_gjzxdkb";
		String pkColumn = "xh||xn";   //������ɾ����ʱ����
		myForm.do_Gjzxdk_GBK();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		rs = this.doTypeRequest_WH(myForm, rs, pkColumn, realTable, tableName, request, service);
		String writeAble = this.setXyUserWritAbleDisabled(myForm, userType, userDep);  //writAble
		this.setRequestAttribute(request, writeAble, realTable, tableName, userType, rs, myForm);
		return mapping.findForward("data_gjzxdk");
	}
	
	/** <font color='red'>������ѧ��������</font> ����/�޸�/��ʾ   �ṩ����������ѯ���� */
	public ActionForward gjzxdksp(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String tableName = "view_xszz_whlgdx_gjzxdk_sp";   //�й����й�����ѧ��������������ͼ
		String realTable = "xszz_whlgdx_gjzxdk_spb";       //�й����й�����ѧ��������������
		String pk =  "xh||xn";   //����[ѧ�ź�ѧ��]
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		myForm.do_Gjdk_Sp_GBK();
		this.doTypeRequest_SQ(myForm, request, service, realTable, tableName, userOnLine, userName, pk);
		return mapping.findForward("gjzxdk_sp_queryOne");
	}	
	
	/**������ѧ��������ά��*/
	public ActionForward gjzxdkspwh(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String tableName = "view_xszz_whlgdx_gjzxdk_sp";   //�й����й�����ѧ��������������ͼ
		String realTable = "xszz_whlgdx_gjzxdk_spb";       //�й����й�����ѧ��������������
		String pkColumn = "xh||xn";   //������ɾ����ʱ����
		myForm.do_Gjdk_Sp_GBK();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		rs = this.doTypeRequest_WH(myForm, rs, pkColumn, realTable, tableName, request, service);
		String writeAble = this.setXyUserWritAbleDisabled(myForm, userType, userDep);  //writAble
		this.setRequestAttribute(request, writeAble, realTable, tableName, userType, rs, myForm);
		return mapping.findForward("data_gjzxdksp");
	}
	
	/** <font color='red'>������ѧ�����Э��</font> ����/�޸�/��ʾ   �ṩ����������ѯ����*/
	public ActionForward gjzxdkhkxysq(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String tableName = "view_xszz_whlgdx_gjzxdk_hkxy";   //�й����й�����ѧ��������������ͼ
		String realTable = "xszz_whlgdx_gjzxdk_hkxyb";       //�й����й�����ѧ��������������
		String pk =  "xh||xn";   //����[ѧ�ź�ѧ��]
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		myForm.do_Gjdk_Fkxy_GBK();
		this.doTypeRequest_SQ(myForm, request, service, realTable, tableName, userOnLine, userName, pk);
		return mapping.findForward("gjzxdk_fkxy_queryOne");
	}	
	
	/** <font color='red'>������ѧ�����Э��ά��</font> */
	public ActionForward gjzxdkhkxywh(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String tableName = "view_xszz_whlgdx_gjzxdk_hkxy";   
		String realTable = "xszz_whlgdx_gjzxdk_hkxyb";       
		String pkColumn = "xh||xn";   //������ɾ����ʱ����
		myForm.do_Gjdk_Fkxy_GBK();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		rs = this.doTypeRequest_WH(myForm, rs, pkColumn, realTable, tableName, request, service);
		String writeAble = this.setXyUserWritAbleDisabled(myForm, userType, userDep);
		this.setRequestAttribute(request, writeAble, realTable, tableName, userType, rs, myForm);
		return mapping.findForward("data_gjzxdkhkxywh");
	}
	
	/**<font color='red'>������Ŀ��������</font> ���� �޸�  �ṩ����������ѯ����**/
	public ActionForward xszzsq(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String tableName = "view_xszz_common_new_zzbbxssqb";  
		String realTable = "xszz_common_new_zzbbxssqb";      
		String pk =  "xh||nd";   //����[ѧ�ź�ѧ��]
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		myForm.do_Zzxm_GBK();
		this.doTypeRequest_SQ(myForm, request, service, realTable, tableName, userOnLine, userName, pk);
		XszzDao xszzDao = new XszzDao();
		request.setAttribute("zzxmList", xszzDao.getXszzZzxmList());  //������Ŀ���б�
		request.setAttribute("zdyzdXxxx",xszzDao.getXszzZdyzdXxxxList(myForm.getXmdm()));
		return mapping.findForward("xszzsq_queryOne");
	}	
	
	/**<font color='red'>������Ŀά��</font> */
	public ActionForward xszzwh(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) {
		XszzWhlgdxActionForm myForm = (XszzWhlgdxActionForm)form;
		XszzWhlgService service = XszzWhlgService.getInstance();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String tableName = "view_xszz_common_new_zzbbxssqb";  
		String realTable = "xszz_common_new_zzbbxssqb";      
		String pkColumn =  "xh||nd";   //����[ѧ�ź�ѧ��]  //������ɾ����ʱ����
		String[] outputValues = new String[]{"nd","nj","xh","xm","xb","xymc","zymc","bjmc","sqsj"};
		myForm.do_Zzxm_GBK();
		String doType = myForm.getDoType();
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		try{
		if(StringUtils.isNotNull(doType)){	
		if("del".equals(doType)){
			String pk = request.getParameter("cbVal");
			request.setAttribute("ok",myForm.setResult(service.batchDelRecord(pk, realTable, pkColumn)));//��ʾ��Ϣ
		}
		rs = service.getCommonStuList(myForm, tableName, outputValues);      //���ؽ����
		request.setAttribute("topTr", myForm.getColumnCN());   //���ر�ͷ��Ϣ
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		//this.doTypeRequest_WH(myForm, rs, pkColumn, realTable, tableName, request, service);
		String writeAble = this.setXyUserWritAbleDisabled(myForm, userType, userDep);
		this.setRequestAttribute(request, writeAble, realTable, tableName, userType, rs, myForm);
		return mapping.findForward("data_xszzwh");
	}	
	
	/**������Ŀά��*/
/**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/	
	/**
	 * ���ݵ�������
	 * @param myForm
	 * @param request  
	 * @param service    <font color='red'>servicce ����</font>
	 * @param realTable  <font color='red'>����</font>  
	 * @param tableName  <font color='red'>��ͼ��</font>
	 * @param userOnLine  <font color='red'>check��student/teacher��</font>
	 * @param userName    <font color='red'>servicce �û���</font>
	 * @param pk
	 */
	private void doTypeRequest_SQ(XszzWhlgdxActionForm myForm,HttpServletRequest request,XszzWhlgService service,
	String realTable,String tableName,String userOnLine,String userName,String pk){
		String doType = myForm.getDoType();
	try{
	if(StringUtils.isNull(request.getParameter("getXh"))){ //�õ�ѧ������Ϣ���ж��Ƿ���Ӵ�������ת�������ʾ����	
		if("add".equals(doType)){
			request.setAttribute("ok",myForm.setResult(service.saveCommonStuInfo(myForm,realTable,pk)));
		}else if("view".equals(doType) || "modi".equals(doType)){
			service.getCommon_PreStuAllInfo(myForm, tableName, pk);
		}else if(StringUtils.isNull(doType)){
			service.getSfTsxx(userName, userOnLine, myForm, tableName, pk); //������xh||xn
		}
		request.setAttribute("rs", myForm);
	}
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	/**
	 * ����ά�����÷���
	 * @param myForm  <font color='red'>XszzWhlgdxActionForm</font>
	 * @param rs      <font color='red'>�����</font>
	 * @param pkColumn <font color='red'>������</font>
	 * @param realTable <font color='red'>����</font>
	 * @param tableName <font color='red'>��ͼ��</font>
	 * @param request <font color='red'>request</font>
	 * @param service <font color='red'>servicce ����</font>
	 */
	private List<HashMap<String,String>> doTypeRequest_WH(XszzWhlgdxActionForm myForm,List<HashMap<String,String>> rs,
	String pkColumn,String realTable,String tableName,HttpServletRequest request,XszzWhlgService service){
		String doType = myForm.getDoType();
	try{
	if(StringUtils.isNotNull(doType)){	
		if("del".equals(doType)){
			String pk = request.getParameter("cbVal");
			request.setAttribute("ok",myForm.setResult(service.batchDelRecord(pk, realTable, pkColumn)));//��ʾ��Ϣ
		}
		rs = service.getCommonStuList(myForm, tableName);      //���ؽ����
		request.setAttribute("topTr", myForm.getColumnCN());   //���ر�ͷ��Ϣ
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
	
	/**set ѧԺ�û� û�� ���� ��ɾ�Ĳ� �͵��뵼�� ��Ȩ��*/
	private String setXyUserWritAbleDisabled(XszzWhlgdxActionForm myForm,String userType,String userDep){
		String writeAble = "yes";// дȨ��   �ٶ�ѧԺû�в���Ȩ��
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
	 * ָ��������ѧ�����Test��
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
