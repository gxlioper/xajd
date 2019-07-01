package xsgzgl.wjcf.general.cfsbgl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Pages;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.inter.WjcfCfsbInterface;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * 
* 
* �����ƣ�WjcfCfsbAction 
* ��������Υ�ʹ��ִ����ϱ�Action
* �����ˣ�xucy 
* ����ʱ�䣺2012-6-20 ����01:18:00 
* �޸ı�ע��  
* @version 
*
 */
public class WjcfCfsbAction extends BasicAction {


	/**
	 * Υ�ʹ���  �����ϱ���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchWjcfResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfGeneralService myService = new WjcfGeneralService();
		
		WjcfCfsbInit init = new WjcfCfsbInit();
		WjcfCfsbModel model = new WjcfCfsbModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initCfsb(rForm, myForm, user, request);
		WjcfCfsbInterface service = myService.getWjcfCfsbService(myForm);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE�汾
		String ie = otherValue[0];
		String stylePath = otherValue[1];
		rsModel.setIe(ie);
		rsModel.setStylePath(stylePath);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		// ����
		List<HashMap<String, String>> topTr = service.getWjcfCfsbTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getWjcfCfsbfList(myForm, model,
				user);
		// ���������
		String spHtml = service.createWjcfCfsbHTML(rsModel, model, rsArrList,
					user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 * �Զ��嵼������������ά������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cfsbExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralForm model = (WjcfGeneralForm) form;
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfsbInterface service = myService.getWjcfCfsbService(model);
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList = service.getWjcfCfsbfExportList(model,user);
		
		//List<String[]> rsList = service.cflbdmCx(model);
		
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * ���Ӵ����ϱ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjWjcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh=request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		request.setAttribute("rs", stuService.selectStuinfo(xh));
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//Υ�ʹ������
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//Υ�ʹ���ԭ��
		request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(xh));//����Υ�ʹ���
		return mapping.findForward("cfsbZj");
		
	}
	
	/**
	 * ���洦���ϱ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh=request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		User user = getUser(request);// �û�����
		String cfid = wjcfcfsbService.saveCfsb(myForm,user);
		
		if (!StringUtil.isNull(cfid)){
			//=======���칤������===2013-1-15==qph===begin============
			String cflbdm = myForm.getCflbdm();
			String[] spgw = wjcfcfsbService.getSpgwByCflb(cflbdm);
			
			if (null != spgw && spgw.length > 0){
				HashMap<String, String> map = wjcfcfsbService.getCfsb(cfid);
				
				Job job = Job.getJobInstance(cfid, xh, spgw[0], 
						"general_wjcf.do?method=cfshCx&xmdm="+cflbdm+"&spgw="+spgw[0], 
						"xscfCx.do","�����ϱ�", map.get("cflbmc"));
				MyJobsManager manager = new MyJobsImpl();
				manager.pushJob(job);
			}
			//=======���칤������===2013-1-15==qph===end==============
		}
		
		request.setAttribute("isSuccess", !StringUtil.isNull(cfid) ? "true":"false");
		request.setAttribute("rs", stuService.selectStuinfo(xh));
		request.setAttribute("dqxn", Base.currXn);
		request.setAttribute("dqxq", Base.getDqxqmc());
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//Υ�ʹ������
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//Υ�ʹ���ԭ��
		
		request.setAttribute("message",!StringUtil.isNull(cfid) ? "�����ɹ���":"����ʧ�ܣ�");
		return mapping.findForward("cfsbZj");
	}
	
	/**
	 * �޸Ĵ����ϱ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xgCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService stuService = new XsxxglService();
		
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		String cfId = request.getParameter("cfId");
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		HashMap<String, String> map = wjcfcfsbService.getCfsb(cfId);
		request.setAttribute("wjsb", map);
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//Υ�ʹ������
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//Υ�ʹ���ԭ��
		if(null!=map){
			request.setAttribute("rs", stuService.selectStuinfo(map.get("xh")));
			request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(map.get("xh")));//����Υ�ʹ���
		}
		return mapping.findForward("cfsbXg");
	}
	
	/**
	 * �޸ı��洦���ϱ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService stuService = new XsxxglService();
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		User user = getUser(request);// �û�����
		String cflbdmXt = request.getParameter("flag");
		boolean flag = wjcfcfsbService.updateCfsb(myForm,user,cflbdmXt);
		HashMap<String, String> map = wjcfcfsbService.getCfsb(myForm.getCfId());
		
		if (flag){
			//========���칤��==2013-1-15==qph====begin===============
			String cflbdm = myForm.getCflbdm();
			String[] spgw = wjcfcfsbService.getSpgwByCflb(cflbdm);
			
			if (null != spgw && spgw.length > 0){
				Job job = Job.getJobInstance(myForm.getCfId(), map.get("xh"), spgw[0], 
						"general_wjcf.do?method=cfshCx&xmdm="+cflbdm+"&spgw="+spgw[0], 
						"xscfCx.do","�����ϱ�", map.get("cflbmc"));
				MyJobsManager manager = new MyJobsImpl();
				
				manager.removeJob(new String[]{myForm.getCfId()}, "�����ϱ�");
				manager.pushJob(job);
			}
			//========���칤��==2013-1-15==qph====end=================
		}
		
		request.setAttribute("wjsb", map);
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());//Υ�ʹ������
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());//Υ�ʹ���ԭ��
		if(null!=map){
			request.setAttribute("rs", stuService.selectStuinfo(map.get("xh")));
			request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(map.get("xh")));//����Υ�ʹ���
		}
		request.setAttribute("message",flag?"�����ɹ���":"����ʧ�ܣ�");
		return mapping.findForward("cfsbXg");
	}
	
	/**
	 * �鿴�����ϱ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ckCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService stuService = new XsxxglService();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		String cfId = request.getParameter("cfId");
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		HashMap<String, String> map = wjcfcfsbService.getCfsb(cfId);
		List<HashMap<String, String>> cfsh = wjcfcfsbService.getCfshxxList(cfId);
		request.setAttribute("wjsb", map);
		request.setAttribute("cfshList", cfsh);
		if(null!=map){
			request.setAttribute("rs", stuService.selectStuinfo(map.get("xh")));
			request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(map.get("xh")));//����Υ�ʹ���
		}
		return mapping.findForward("cfsbCk");
	}
	
	/**
	 * ɾ�������ϱ���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward scCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		boolean flag = wjcfcfsbService.delWjsb(myForm.getPrimarykey_checkVal());
			
		if (flag){
			//===���칤�� ===2013-1-15=====qph=====begin========
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(myForm.getPrimarykey_checkVal(), "�����ϱ�");
			//===���칤�� ===2013-1-15=====qph=====end==========
		}
		
		
		String message =flag?"�����ɹ���":"����ʧ�ܣ�";
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * �޸ģ�ɾ��ʱ��⴦���ϱ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		String message = wjcfcfsbService.checkCfsb(myForm);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * �������
	 */
	public ActionForward expCfsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		WjcfCfsbModel model = new WjcfCfsbModel();
		User user = getUser(request);// �û�����
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		String[] title = new String[]{"PK","ѧ��", "ѧ��","ѧ��", "����", "�������","����ԭ��","�����ĺ�","����ʱ��","�ϱ���","��˽��"};
		List<String[]> rs = wjcfcfsbService.getWjcfCfsbfList(myForm, model, user);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, title, title, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fjxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfsbInterface wjcfcfsbService =  myService.getWjcfCfsbService(myForm);
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(myForm.getFjmc()));
		InputStream in = wjcfcfsbService.fjCx(myForm);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}
		
}
