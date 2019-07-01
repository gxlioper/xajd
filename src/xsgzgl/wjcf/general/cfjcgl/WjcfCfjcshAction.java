package xsgzgl.wjcf.general.cfjcgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.cfssgl.WjcfCfssglServices;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.inter.WjcfCfjcshInterface;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;

public class WjcfCfjcshAction extends BasicAction  {
	
	/**
	 * Υ�ʹ���  ���ֽ����˲�ѯ
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
		WjcfCfjcshModel model = new WjcfCfjcshModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initCfjcsh(rForm, myForm, user, request);
		WjcfCfjcshInterface service = myService.getWjcfCfjcshService(myForm);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE�汾
		String ie = otherValue[0];
		String stylePath = otherValue[1];
		String spgw = otherValue[2];
		rsModel.setIe(ie);
		rsModel.setStylePath(stylePath);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		myForm.setSpgwId(spgw);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		// ����
		List<HashMap<String, String>> topTr = service.getWjcfCfjcshTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getWjcfCfjcshList(myForm, model,
				user);
		// ���������
		String spHtml = service.createWjcfCfjcshHTML(rsModel, model, rsArrList,
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
	
	// ������λ��
	public ActionForward showShgwDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		WjcfCfjcshInterface service = myService.getWjcfCfjcshService(myForm);

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���

		if (Base.isNull(message)) {

			service.showShgwDiv(user, response);

		}

		return null;
	}
	
	/**
	 * Υ�ʹ���  ���ֽ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfjcSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService stuService = new XsxxglService();

		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		String cfId = request.getParameter("cfId");
		WjcfCfjcshInterface wjcfcfjcshService = myService.getWjcfCfjcshService(myForm);
		User user = getUser(request);// �û�����
		String spgw = request.getParameter("spgw");
		myForm.setSpgwId(spgw);
		myForm.setCfId(cfId);

		HashMap<String, String> upSpgwmap = wjcfcfjcshService.getHigherUpSpMap(
				myForm, user);// �ϼ�������λ
		boolean sfDyj = false;
		if (null == upSpgwmap || upSpgwmap.size() == 0) {// ���û���ϼ�������λ˵��Ϊ��һ�����������˻ز���
			sfDyj = true;
		}

		HashMap<String, String> maxSpgwmap = wjcfcfjcshService.getMaxSpgw(myForm,
				user);// ��߼�������λ
		boolean sfZgj = false;
		if (spgw.equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}

		List<HashMap<String, String>> cfsh = wjcfcfjcshService.getCfjcshxx(cfId,spgw);
		request.setAttribute("cfshList", cfsh);

		HashMap<String, String> cfsbMap = wjcfcfjcshService.getCfxx(cfId);// ��������
		
		HashMap<String, String> cfshMap = wjcfcfjcshService.getOnesCfjcsh(cfId,spgw);// �����������

		HashMap<String, String> cfjcMap = wjcfcfjcshService.getOnesCfjc(cfId);// ���ֽ������
		
		HashMap<String, String> cfssMap = wjcfcfjcshService.getOnesCfss(cfId);// ������������
		
		if (null != cfsbMap) {
			request.setAttribute("rs", stuService.selectStuinfo(cfsbMap
					.get("xh")));
		}
		request.setAttribute("wjsb", cfsbMap);
		request.setAttribute("sfDyj", sfDyj);// �Ƿ��һ��
		request.setAttribute("sfZgj", sfZgj);// �Ƿ���߼�
		request.setAttribute("spgw", spgw);
		request.setAttribute("cfsh", cfshMap);
		request.setAttribute("cfjc", cfjcMap);
		request.setAttribute("cfss", cfssMap);

		return mapping.findForward("cfjcshZj");
	}
	
	// ���������
	public ActionForward saveCfjcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfssglServices cfssService = new WjcfCfssglServices();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfjcshInterface wjcfcfjcshService = myService
				.getWjcfCfjcshService(myForm);
		User user = getUser(request);// �û�����
		String doType = request.getParameter("doType");
		boolean tgcz = false;
		if ("tg".equals(doType)) {
			myForm.setShzt("tg");
			tgcz = true;
		}
		if ("btg".equals(doType)) {
			myForm.setShzt("btg");
		}
		if ("th".equals(doType)) {
			myForm.setShzt("th");
		}
		//ת��
		if(null!=myForm.getShyj()){
			myForm.setShyj(myService.unicode2Gbk(myForm.getShyj()));
		}
		if(null!=myForm.getJcwh()){
			myForm.setJcwh(myService.unicode2Gbk(myForm.getJcwh()));
		}
		boolean flag = wjcfcfjcshService.saveCfjcsh(myForm, user);
		
		if (flag){
			//=======���칤��===2013-1-16====qph====begin========
			String[] id = new String[]{myForm.getCfId()};
			String curShgw = myForm.getSpgwId();
			String[] spgw = cfssService.getSsjcSpgw();
			int index = StringUtils.getStrIndexInArray(curShgw, spgw);
			
			for (String str : id){
				Job job = null;
				if ("tg".equals(doType)) {
					String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"wjcf_general_cfjcsh.do?spgw="+nextShgw,"���ֽ��");
				} else if ("th".equals(doType)){
					String nextShgw = index!=0 ? spgw[index-1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"wjcf_general_cfjcsh.do?spgw="+nextShgw,"���ֽ��");
				} else {
					job = Job.getJobInstance(str,"���ֽ��");
				}
				MyJobsManager manager = new MyJobsImpl();
				manager.updateJob(job);
			}
			//=======���칤��===2013-1-16====qph====end==========
		}
		
		
		
		HashMap<String, String> maxSpgwmap = wjcfcfjcshService.getMaxSpgw(myForm,
				user);// ��߼�������λ
		boolean sfZgj = false;
		if (myForm.getSpgwId().equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(tgcz&&sfZgj);
		
		return null;
	}

	/**
	 * ����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plJcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfjcshInterface wjcfcfjcshService = myService
				.getWjcfCfjcshService(myForm);
		User user = getUser(request);// �û�����
		String spgw = request.getParameter("spgw");
		String cfId = request.getParameter("cfId");
		myForm.setSpgwId(spgw);

		HashMap<String, String> upSpgwmap = wjcfcfjcshService.getHigherUpSpMap(
				myForm, user);// �ϼ�������λ
		boolean sfDyj = false;
		if (null == upSpgwmap || upSpgwmap.size() == 0) {// ���û���ϼ�������λ˵��Ϊ��һ�����������˻ز���
			sfDyj = true;
		}

		HashMap<String, String> maxSpgwmap = wjcfcfjcshService.getMaxSpgw(myForm,
				user);// ��߼�������λ
		boolean sfZgj = false;
		if (spgw.equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}

		request.setAttribute("sfDyj", sfDyj);// �Ƿ��һ��
		request.setAttribute("sfZgj", sfZgj);// �Ƿ���߼�
		request.setAttribute("spgw", spgw);
		request.setAttribute("cfId", cfId);

		return mapping.findForward("plJcsh");
	}
	
	/**
	 * �����������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plSaveJcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfjcshInterface wjcfcfjcshService = myService
				.getWjcfCfjcshService(myForm);
		WjcfCfssglServices cfssService = new WjcfCfssglServices();
		User user = getUser(request);// �û�����
		String doType = request.getParameter("doType");
		boolean tgcz = false;
		if ("tg".equals(doType)) {
			myForm.setShzt("tg");
			tgcz = true;
		}
		if ("btg".equals(doType)) {
			myForm.setShzt("btg");
		}
		if ("th".equals(doType)) {
			myForm.setShzt("th");
		}
		String spgw = myForm.getSpgwId();
		myForm.setSpgwId(spgw);
		HashMap<String, String> maxSpgwmap = wjcfcfjcshService.getMaxSpgw(myForm,
				user);// ��߼�������λ
		boolean sfZgj = false;
		if (spgw.equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}
		//ת��
		if(null!=myForm.getShyj()){
			myForm.setShyj(myService.unicode2Gbk(myForm.getShyj()));
		}
		if(null!=myForm.getJcwh()){
			myForm.setJcwh(myService.unicode2Gbk(myForm.getJcwh()));
		}
		boolean flag = wjcfcfjcshService.saveCfjcsh(myForm, user);
		
		if (flag){
			//=====���칤��===2013-1-16===qph===begin=====
			String[] id = myForm.getCfId().split(",");
			String curShgw = myForm.getSpgwId();
			String[] spgwArray = cfssService.getSsjcSpgw();
			int index = StringUtils.getStrIndexInArray(curShgw, spgwArray);
			
			for (String str : id){
				Job job = null;
				if ("tg".equals(doType)) {
					String nextShgw = index!=spgwArray.length-1 && spgwArray[index+1] != null ? spgwArray[index+1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"wjcf_general_cfjcsh.do?spgw="+nextShgw,"���ֽ��");
				} else if ("th".equals(doType)){
					String nextShgw = index!=0 ? spgwArray[index-1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"wjcf_general_cfjcsh.do?spgw="+nextShgw,"���ֽ��");
				} else {
					job = Job.getJobInstance(str,"���ֽ��");
				}
				MyJobsManager manager = new MyJobsImpl();
				manager.updateJob(job);
			}
			//=====���칤��===2013-1-16===qph===end=======
		}

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(tgcz&&sfZgj);
		
		return null;
	}
	
	/**
	 * �ύʱͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tongJi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfjcshInterface wjcfcfjcshService = myService
				.getWjcfCfjcshService(myForm);

		request.setAttribute("tongjiList", wjcfcfjcshService.tongjiList());
		return mapping.findForward("tongJi");
	}

	/**
	 * �ύ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfjcshInterface wjcfcfjcshService = myService
				.getWjcfCfjcshService(myForm);

		boolean flag = wjcfcfjcshService.tjSh();
		request.setAttribute("message", flag == true ? "�����ɹ�" : "����ʧ��");
		request.setAttribute("tongjiList", wjcfcfjcshService.tongjiList());
		return mapping.findForward("tongJi");
	}
	
	/**
	 * �ύ(ֱ���ύ)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjtjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfjcshInterface wjcfcfjcshService = myService
				.getWjcfCfjcshService(myForm);

		boolean flag = wjcfcfjcshService.zjtjSh(myForm);
		request.setAttribute("message", flag == true ? "�����ɹ�" : "����ʧ��");
		return null;
	}
}
