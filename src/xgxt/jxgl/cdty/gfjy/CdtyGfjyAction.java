package xgxt.jxgl.cdty.gfjy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.bdsz.BdszModel;
import xgxt.bdsz.BdszService;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.gygl.jbsz.GyglJbszForm;
import xgxt.gygl.jbsz.GyglJbszService;
import xgxt.gygl.tjfx.GyglTjfxForm;
import xgxt.gygl.tjfx.GyglTjfxService;
import xgxt.sjdrdc.ExportAndImportModel;
import xgxt.sjdrdc.ImportDataService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.xsgygl.bjlh.sjwh.SjwhDAO;
import xgxt.xszz.zgmsxy.XszzZgmsxyActionForm;
import xgxt.xszz.zgmsxy.XszzZgmsxyService;

import com.zfsoft.basic.BasicAction;
import common.Globals;

public class CdtyGfjyAction extends BasicAction{
	
	
	/**
	 * ���������γ�ά������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kcwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		CdtyGfjyForm myForm = (CdtyGfjyForm) form;
		CdtyGfjyService service = new CdtyGfjyService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		
		String message="";
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.delGfkc(myForm);
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============ͨ������ ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// ��ʾ����
		List<HashMap<String,String>>topTr=service.getTopTr("kfkc",rForm,myForm);
		String showNum = String.valueOf(topTr.size()-1);
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);

		myForm.setUser(user);
		rForm.setRsArrList((ArrayList<String[]>)service.getGfkcList(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============ͨ������ end ============
		
		
		request.setAttribute("path", "cdtyGfjy.do?method=kcwhManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		//��ͷ
		request.setAttribute("topTr", service.getTopTr("kfkc",rForm,myForm));
		return mapping.findForward("kcwhManage");
	}
	
	/**
	 * ���������γ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kcwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CdtyGfjyForm myForm = (CdtyGfjyForm) form;
		CdtyGfjyService service = new CdtyGfjyService();
		String doType = request.getParameter("doType");
		
		// ��Դ�ش����
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveKcxx(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}

		HashMap<String,String>rs=new HashMap<String,String>();
		request.setAttribute("rs", rs);
		request.setAttribute("path", "cdtyGfjy.do?method=kcwhManage");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("tableName", "xg_jxgl_gfjygl");
		request.setAttribute("doType", "add");
		return mapping.findForward("kcwhUpdate");
	}
	
	/**
	 * ���������γ��޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kcwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdtyGfjyForm myForm = (CdtyGfjyForm) form;
		CdtyGfjyService service = new CdtyGfjyService();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		myForm.setCheckVal(new String[]{pkValue});
		
		// ��Դ�ش����
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.updateKcxx(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
			doType="update";
		}

		request.setAttribute("path", "cdtyGfjy.do?method=kcwhManage");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("rs", service.getOneKcmc(myForm));
		request.setAttribute("tableName", "view_xsjbxx");
		request.setAttribute("doType", doType);
		return mapping.findForward("kcwhUpdate");
	}
	
	/**
	 * ���������γ�ά������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kcfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		CdtyGfjyForm myForm = (CdtyGfjyForm) form;
		CdtyGfjyService service = new CdtyGfjyService();
		CommService commService=new CommService();
		String nd=myForm.getNj();

		String doType=request.getParameter("doType");
		
		//�жϵ�ǰ���ʱ���пγ�����
		if(!(service.getKcList().size()>0)){
			String msg = "�������δά�����������γ�,����ά�����������γ̣�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		String message="";
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.delGfkc(myForm);
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}
		
		if("save".equalsIgnoreCase(doType)){
			boolean flag=service.saveGfjyf(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============ͨ������ ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// ��ʾ����
		List<HashMap<String,String>>topTr=service.getTopTr("kcf",rForm,myForm);
		String showNum = String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);

		myForm.setUser(user);
		rForm.setRsArrList((ArrayList<String[]>)service.getGfcjList(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============ͨ������ end ============
		
		
		request.setAttribute("path", "cdtyGfjy.do?method=kcfManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		if(Base.isNull(nd)){
			nd=Base.currNd;
			myForm.setNj(Base.currNd);
		}
		if(!Base.isNull(myForm.getXydm())){
			request.setAttribute("xydm", myForm.getXydm());
		}
		if(!Base.isNull(myForm.getZydm())){
			request.setAttribute("zydm", myForm.getZydm());
		}
		if(!Base.isNull(myForm.getBjdm())){
			request.setAttribute("bjdm", myForm.getBjdm());
		}
		if(!Base.isNull(myForm.getNj())){
			request.setAttribute("nj", myForm.getNj());
		}
		
		request.setAttribute("nd", nd);
		//��ͷ
		request.setAttribute("topTr", service.getTopTr("kcf",rForm,myForm));
		return mapping.findForward("kcfManage");
	}
	
	/**
	 * ���������γ�ά������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gfjycxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		CdtyGfjyForm myForm = (CdtyGfjyForm) form;
		CdtyGfjyService service = new CdtyGfjyService();
		CommService commService=new CommService();
		String nd=myForm.getNj();
		
		

//		 ===============ͨ������ ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// ��ʾ����
		List<HashMap<String,String>>topTr=service.getTopTr("zf",rForm,myForm);
		String showNum = String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);

		myForm.setUser(user);
	
		String go=request.getParameter("go");
		if("go".equalsIgnoreCase(go)){
			rForm.setRsArrList((ArrayList<String[]>)service.getGfcj(myForm,"cx"));
		}
		commService.setRequestValue(rForm, user, request);
		// ===============ͨ������ end ============
		
		
		request.setAttribute("path", "cdtyGfjy.do?method=gfjycxManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		//��ͷ
		request.setAttribute("topTr", topTr);
		if(Base.isNull(nd)){
			nd=Base.currNd;
		}
		if(!Base.isNull(myForm.getXydm())){
			request.setAttribute("xydm", myForm.getXydm());
		}
		if(!Base.isNull(myForm.getZydm())){
			request.setAttribute("zydm", myForm.getZydm());
		}
		if(!Base.isNull(myForm.getBjdm())){
			request.setAttribute("bjdm", myForm.getBjdm());
		}
		
		request.setAttribute("nd", nd);
		return mapping.findForward("gfjycxManage");
	}
	

	/** 
	 * Method printGfjyf
	 * ��������ͳ�Ƶ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * 
	 */
	public ActionForward printGfjyf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		CdtyGfjyForm myForm = (CdtyGfjyForm) form;
		CdtyGfjyService service = new CdtyGfjyService();
	
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response);
		
		myForm.setUser(user);
		service.printGfjyf(myForm, request, wwb);
		return null;
	}
	
	// ----------------------------------�����γ̷ֵ��� begin-----------------------------------
	
	/** 
	 * ���ݵ�����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward importData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
		String tableName = request.getParameter("tableName");//��ͼ��
		String realTable = request.getParameter("realTable");//����
		String drnj = request.getParameter("drnj");// �꼶
		
		request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("drnj",drnj);
		return mapping.findForward("checkorimport");
	}
	
	/**
	 * ���ص���ģ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stencil(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CdtyGfjyService service = new CdtyGfjyService();
		
		String drnj=request.getParameter("drnj");// �꼶
		
		response.setContentType("application/vnd.ms-excel");
		service.exportDataIn(response.getOutputStream(),drnj);
					
		return mapping.findForward("");
	}
	
	/** 
	 * �������ݼ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward showCheckData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CdtyGfjyService service = new CdtyGfjyService();
		ImportDataService importDataservice = new ImportDataService();
		uploadFile(mapping, form, request, response);
		String filePath = request.getAttribute("filepath") != null ?request.getAttribute("filepath").toString() : request.getParameter("filepath").toString();
		String realTable = request.getParameter("realTable");//�������ݶ�Ӧ�ı�
		String tableName = request.getParameter("tableName");//��ͼ
		String drnj =request.getParameter("drnj");//�꼶
		
		List<HashMap<String, String>> orcleItemList = service.getImportZd(drnj);//���ݿ�����
		request.setAttribute("excelListCollection", importDataservice.getExcelCols(filePath));//Excel���е���
		request.setAttribute("orcleItemList", orcleItemList);
		request.setAttribute("tabName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("drnj", drnj);
		request.setAttribute("filepath", filePath);
		request.setAttribute("flag", "1");//���ڼ��ı���� ��1��
		return mapping.findForward("dataMatching");
	}
	
	
	/** 
	 * ���ݵ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException 
	 */
	public ActionForward checkAndImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
 		String flag = request.getParameter("flag");
		String filePath =  DealString.toGBK(request.getParameter("filepath").toString()); 
		String realTable = request.getParameter("realTable");
		String drnj =request.getParameter("drnj");
   		String mappingItems = request.getParameter("mappingItems");
		
		String drms = request.getParameter("drms");
		//excel�����Ҫ�����ݿ�����ƥ����ֶ�
		String[] yzsjzd = request.getParameterValues("yzsjzd");
		
		CdtyGfjyService service = new CdtyGfjyService();
		List<String[]> list = new ArrayList<String[]>();
		
		ActionForward af = null;
		
		ExportAndImportModel model = new ExportAndImportModel();
		model.setMappingItems(mappingItems);
		model.setRealTable(realTable);
		model.setFilePath(filePath);
		
//		if(flag.equals("1")){//���ݼ��
//			list = importDataService.checkExcelDataComm(model);//�������ݼ��
//			if(list.size()>0){//���������������
//				response.reset();
//				response.setContentType("application/vnd.ms-excel");
//				ExcelMethods.write2Sheet(filePath, response.getOutputStream(), "����������", list);
//				response.flushBuffer();
//				af = mapping.findForward("showExcelData");
//			}else{           //���ݼ��ͨ��
//				request.setAttribute("checkResult", "true");
//				af = showCheckData(mapping,form,request,response);
//			}
//		}else 
		
		if(flag.equals("2")){//���ݵ���
			
			if(drms!=null && drms.equalsIgnoreCase("1")){//����������

				list = service.saveExcel2Db(model, response, yzsjzd,drnj);

			}else if(drms!=null && drms.equalsIgnoreCase("0")){//���ݸ���
				
				list = service.updateExcel2Db(model, response, yzsjzd,drnj);				
			}
			
			if(list != null && list.size()>0){
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				service.write2SheetImport(filePath, response.getOutputStream(), "����������", list);
				response.flushBuffer();
			}else{
				
				request.setAttribute("importResult", true);
			}
			af = showImportData(mapping,form,request,response);				
		}
		
		request.setAttribute("filepath", filePath);
		return af;
	}
	
	/** 
	 * �������ݵ���ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward showImportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ImportDataService importDataService = new ImportDataService();
		CdtyGfjyService service = new CdtyGfjyService();
		//���ϴ�
		uploadFile(mapping, form, request, response);
		String filePath = request.getAttribute("filepath") != null ?request.getAttribute("filepath").toString() : request.getParameter("filepath").toString();
		String realTable = request.getParameter("realTable");//�������ݶ�Ӧ�ı�
		String tableName = request.getParameter("tableName");//��ͼ
		String drnj =request.getParameter("drnj");//�꼶
		String drms = request.getParameter("drms");//---1:���������� ----0:���ݸ���
		
		String[] pkList = importDataService.getTablePk(realTable);
		
		List<HashMap<String, String>> orcleItemList = service.getImportZd(drnj);//���ݿ�����
		
		request.setAttribute("excelListCollection", importDataService.getExcelCols(filePath));//Excel���е���
		request.setAttribute("orcleItemList", orcleItemList);
		request.setAttribute("tabName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("drnj", drnj);
		request.setAttribute("filepath", filePath);
		request.setAttribute("drms", drms);
		request.setAttribute("pkList", pkList);
		request.setAttribute("flag", "2");//���ڵ���ı���� ��2��
		return mapping.findForward("dataMatching");
	}
	
	/**
	 * �ϴ��赼���ļ�
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String dir = servlet.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = (String) session.getAttribute("userName");
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		CdtyGfjyForm myForm = (CdtyGfjyForm) form;
		FormFile file = myForm.getImpFilePath();	
		if (file == null || (file.getFileName() == null || file.getFileName().trim().equals(""))) {
			file = myForm.getCheckFilePath();
			if(file == null){
				return mapping.findForward("false");
			}
		}
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		request.setAttribute("tabName", tabName);
		request.setAttribute("filepath", filePath);
		request.setAttribute("moditag", request.getParameter("moditag"));
		return mapping.findForward("success");
	}
	
	
	// ---------------------------�����γ̷ֵ��� end-----------------------------------
}
