package xsgzgl.wjcf.general.cfjcgl;

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

import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Pages;
import xsgzgl.wjcf.cfssgl.WjcfCfssglServices;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.inter.WjcfCfjcInterface;
import xsgzgl.wjcf.general.inter.WjcfCfjcshInterface;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class WjcfCfjcAction extends BasicAction {
	
	/**
	 * Υ�ʹ��ִ��ֽ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cfjcCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfGeneralService service = new WjcfGeneralService();
		WjcfCfjcInit init = new WjcfCfjcInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initCfjc(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/wjcf/" + xxpymc + "/cfjcgl/cfjcCx.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * Υ�ʹ���  ���ֽ����ѯ
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
		
		WjcfCfjcInit init = new WjcfCfjcInit();
		WjcfCfjcModel model = new WjcfCfjcModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initCfjc(rForm, myForm, user, request);
		WjcfCfjcInterface service = myService.getWjcfCfjcService(myForm);

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
		List<HashMap<String, String>> topTr = service.getWjcfCfjcTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getWjcfCfjcList(myForm, model,
				user);
		// ���������
		String spHtml = service.createWjcfCfjcHTML(rsModel, model, rsArrList,
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
	 * ����Զ��嵼�������ֽ��ά����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cfjcwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		WjcfGeneralForm model = (WjcfGeneralForm) form;
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfjcModel model1 = new WjcfCfjcModel();
		WjcfCfjcInterface service = myService.getWjcfCfjcService(model);
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getWjcfCfjcExportList(model, model1,user);		
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
	 *���ӽ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjJcSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String cfId = request.getParameter("cfId");
		request.setAttribute("cfId", cfId);
		return mapping.findForward("jcsqZj");
	}

	/**
	 * ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveJcSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfssglServices service=new WjcfCfssglServices();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfjcInterface wjcfcfjcService =  myService.getWjcfCfjcService(myForm);
		String cfId = request.getParameter("cfId");
		String lx = request.getParameter("lx");
		boolean flag = false;
		if("zj".equals(lx)){
			flag = wjcfcfjcService.saveJcSq(myForm);
			request.setAttribute("result", flag);//�������ӽ��
			if (flag){
				//====���칤��==2013-1-16= qph ==begin====
				HashMap<String,String> map = service.cfssglCk(cfId);
				String xh = map.get("xh");
				String[] spgw = service.getSsjcSpgw();
				if (null != spgw && spgw.length > 0){
					Job job = Job.getJobInstance(cfId, xh, spgw[0], 
							"wjcf_general_cfjcsh.do?spgw="+spgw[0], 
							"wjcf_general_cfjc.do","���ֽ��", "���ֽ��");
					MyJobsManager manager = new MyJobsImpl();
					manager.pushJob(job);
				}
				//====���칤��==2013-1-16= qph ==end======
			}
			return mapping.findForward("jcsqZj");
		}else{
			flag = wjcfcfjcService.updateJcSq(myForm);
			request.setAttribute("result", flag);//�������ӽ��
			return mapping.findForward("jcsqXg");
		}
		
	}
	
	/**
	 * Υ�ʹ���  ���ֽ���޸Ľ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xgJcSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfjcInterface wjcfcfjcService =  myService.getWjcfCfjcService(myForm);
		
		String cfId = request.getParameter("cfId");
		HashMap<String, String> jcsq = wjcfcfjcService.getJcSq(cfId);
		request.setAttribute("jcsq", jcsq);
		return mapping.findForward("jcsqXg");
		
		
	}
	
	
	/**
	 * ɾ�����ֽ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxJcSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfjcInterface wjcfcfjcService =  myService.getWjcfCfjcService(myForm);
		String cfId = myForm.getCfId();
		boolean flag = wjcfcfjcService.delJcSq(cfId);
		
		if (flag){
			//====���칤��===2013-1-16==qph===begin===
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(new String[]{cfId}, "���ֽ��");
			//====���칤��===2013-1-16==qph===end=====
		}
		
		String message =flag?"�����ɹ���":"����ʧ�ܣ�";
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * �鿴���ֽ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ckCfjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService stuService = new XsxxglService();

		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		String cfId = request.getParameter("cfId");
		WjcfCfjcshInterface wjcfcfjcshService = myService.getWjcfCfjcshService(myForm);
		myForm.setCfId(cfId);

		List<HashMap<String, String>> cfsh = wjcfcfjcshService.getCfjcshxxList(cfId);
		request.setAttribute("cfshList", cfsh);

		HashMap<String, String> cfsbMap = wjcfcfjcshService.getCfxx(cfId);// ��������

		HashMap<String, String> cfjcMap = wjcfcfjcshService.getOnesCfjc(cfId);// ���ֽ������
		
		HashMap<String, String> cfssMap = wjcfcfjcshService.getOnesCfss(cfId);// ������������
		
		if (null != cfsbMap) {
			request.setAttribute("rs", stuService.selectStuinfo(cfsbMap
					.get("xh")));
		}
		request.setAttribute("wjsb", cfsbMap);
		request.setAttribute("cfjc", cfjcMap);
		request.setAttribute("cfss", cfssMap);
		
		return mapping.findForward("ckCfjc");
	}
	
	/**
	 * �������
	 */
	public ActionForward expCfjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfjcInterface wjcfcfsbService =  myService.getWjcfCfjcService(myForm);
		WjcfCfjcModel model = new WjcfCfjcModel();
		User user = getUser(request);// �û�����
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		String[] title = new String[]{"PK","ѧ��", "ѧ��","ѧ��", "����", "�������","����ԭ��","����ĺ�","���ʱ��","��˽��","������"};
		List<String[]> rs = wjcfcfsbService.getWjcfCfjcList(myForm, model, user);
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
		WjcfCfjcInterface wjcfcfjcService =  myService.getWjcfCfjcService(myForm);
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(myForm.getFjmc()));
		InputStream in = wjcfcfjcService.fjCx(myForm);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}
	/**
	 * ���߸�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssFjxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfjcInterface wjcfcfjcService =  myService.getWjcfCfjcService(myForm);
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(myForm.getFjmc()));
		InputStream in = wjcfcfjcService.ssFjxz(myForm);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}
}
