package xsgzgl.wjcf.general.cfsbgl;

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
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.WjcfGeneralService;
import xsgzgl.wjcf.general.inter.WjcfCfsbInterface;
import xsgzgl.wjcf.general.inter.WjcfCfshInterface;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;

/**
 * 
 * 
 * �����ƣ�WjcfCfsbAction 
 * ��������Υ�ʹ��ִ������Action 
 * �����ˣ�xucy 
 * ����ʱ�䣺2012-7-11 ����01:18:00
 * �޸ı�ע��
 * 
 * @version
 * 
 */
public class WjcfCfshAction extends BasicAction {

	/**
	 * Υ�ʹ��� ������˲�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchWjcfResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfsbInit init = new WjcfCfsbInit();
		WjcfCfshModel model = new WjcfCfshModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initCfsh(rForm, myForm, user, request);
		WjcfCfshInterface service = myService.getWjcfCfshService(myForm);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);

		String cflbdm = otherValue[1];

		String spgw = otherValue[2];

		String stylePath = otherValue[3];
		myForm.setCflbdm(cflbdm);

		myForm.setSpgwId(spgw);
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
		List<HashMap<String, String>> topTr = service.getWjcfCfshTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getWjcfCfshList(myForm, model,
				user);
		// ���������
		String spHtml = service.createWjcfCfshHTML(rsModel, model, rsArrList,
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

	// ���
	public ActionForward cfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxglService stuService = new XsxxglService();

		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		String cfId = request.getParameter("cfId");
		WjcfCfsbInterface wjcfcfsbService = myService
				.getWjcfCfsbService(myForm);
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);
		User user = getUser(request);// �û�����
		String cflbdm = request.getParameter("cflbdm");
		String spgw = request.getParameter("spgw");
		myForm.setCflbdm(cflbdm);
		myForm.setSpgwId(spgw);
		myForm.setCfId(cfId);

		HashMap<String, String> upSpgwmap = wjcfcfshService.getHigherUpSpMap(
				myForm, user);// �ϼ�������λ
		boolean sfDyj = false;
		if (null == upSpgwmap || upSpgwmap.size() == 0) {// ���û���ϼ�������λ˵��Ϊ��һ�����������˻ز���
			sfDyj = true;
		}

		HashMap<String, String> maxSpgwmap = wjcfcfshService.getMaxSpgw(myForm,
				user);// ��߼�������λ
		boolean sfZgj = false;
		if (spgw.equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}

		List<HashMap<String, String>> cfsh = wjcfcfshService.getCfshxx(cfId,spgw);
		request.setAttribute("cfshList", cfsh);

		HashMap<String, String> cfsbMap = wjcfcfsbService.getCfsb(cfId);// �����ϱ�����
		HashMap<String, String> cfshMap = wjcfcfshService.getOnesCfsh(cfId,spgw);// �����������

		request.setAttribute("sfDyj", sfDyj);// �Ƿ��һ��
		request.setAttribute("sfZgj", sfZgj);// �Ƿ���߼�
		request.setAttribute("spgw", spgw);
		request.setAttribute("cflbdm", cflbdm);
		request.setAttribute("wjsb", cfsbMap);
		request.setAttribute("cfsh", cfshMap);

		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());// Υ�ʹ������
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());// Υ�ʹ���ԭ��
		if (null != cfsbMap) {
			request.setAttribute("rs", stuService.selectStuinfo(cfsbMap
					.get("xh")));
			request.setAttribute("yscfqkList", wjcfcfsbService
					.getYscfqk(cfsbMap.get("xh")));// ����Υ�ʹ���
		}
		return mapping.findForward("cfshZj");
	}

	// ������λ��
	public ActionForward showShgwDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		WjcfCfshInterface service = myService.getWjcfCfshService(myForm);

		String cflbdm = request.getParameter("cflbdm");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���

		if (Base.isNull(message)) {

			service.showShgwDiv(cflbdm, user, response);

		}

		return null;
	}

	// �������
	public ActionForward saveCfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfsbService cfsbService = new WjcfCfsbService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);
		User user = getUser(request);// �û�����
		String doType = request.getParameter("doType");
		boolean tgcz = false;
		if ("tg".equals(doType)) {
			myForm.setShzt("tg");
			tgcz=true;
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
		if(null!=myForm.getCfwh()){
			myForm.setCfwh(myService.unicode2Gbk(myForm.getCfwh()));
		}
		boolean flag = wjcfcfshService.saveCfsh(myForm, user);
		
		if (flag){
			//===���칤��=====2013-1-15 qph==beign======
			String[] id = new String[]{myForm.getCfId()};
			String curShgw = myForm.getSpgwId();
			String[] spgw = cfsbService.getSpgwByCflb(myForm.getCflbdm());
			int index = StringUtils.getStrIndexInArray(curShgw, spgw);
			
			for (String str : id){
				Job job = null;
				if ("tg".equals(myForm.getShzt())) {
					String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"general_wjcf.do?method=cfshCx&xmdm="+myForm.getCflbdm()+"&spgw="+nextShgw,"�����ϱ�");
				} else if ("th".equals(myForm.getShzt())){
					String nextShgw = index!=0 ? spgw[index-1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"general_wjcf.do?method=cfshCx&xmdm="+myForm.getCflbdm()+"&spgw="+nextShgw,"�����ϱ�");
				} else {
					job = Job.getJobInstance(str,"�����ϱ�");
				}
				MyJobsManager manager = new MyJobsImpl();
				manager.updateJob(job);
			}
			//===���칤��=====2013-1-15 qph==end=========
		}
		
		HashMap<String, String> maxSpgwmap = wjcfcfshService.getMaxSpgw(myForm,
				user);// ��߼�������λ
		boolean sfZgj = false;
		if (myForm.getSpgwId().equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}
		request.setAttribute("message", flag);
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(tgcz&&sfZgj);
		
		return null;
	}

	/**
	 * �������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plCfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);
		User user = getUser(request);// �û�����
		String cflbdm = request.getParameter("cflbdm");
		String spgw = request.getParameter("spgw");
		String cfId = request.getParameter("cfId");
		myForm.setCflbdm(cflbdm);
		myForm.setSpgwId(spgw);

		HashMap<String, String> upSpgwmap = wjcfcfshService.getHigherUpSpMap(
				myForm, user);// �ϼ�������λ
		boolean sfDyj = false;
		if (null == upSpgwmap || upSpgwmap.size() == 0) {// ���û���ϼ�������λ˵��Ϊ��һ�����������˻ز���
			sfDyj = true;
		}

		HashMap<String, String> maxSpgwmap = wjcfcfshService.getMaxSpgw(myForm,
				user);// ��߼�������λ
		boolean sfZgj = false;
		if (spgw.equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}

		request.setAttribute("sfDyj", sfDyj);// �Ƿ��һ��
		request.setAttribute("sfZgj", sfZgj);// �Ƿ���߼�
		request.setAttribute("spgw", spgw);
		request.setAttribute("cfId", cfId);
		request.setAttribute("cflbdm", cflbdm);

		return mapping.findForward("plCfsh");
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
	public ActionForward plSaveCfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfGeneralService myService = new WjcfGeneralService();
		WjcfCfsbService cfsbService = new WjcfCfsbService();
		WjcfGeneralForm myForm = (WjcfGeneralForm) form;
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);
		User user = getUser(request);// �û�����
		String doType = request.getParameter("doType");
		boolean tgcz = false;
		if ("tg".equals(doType)) {
			myForm.setShzt("tg");
			tgcz= true;
		}
		if ("btg".equals(doType)) {
			myForm.setShzt("btg");
		}
		if ("th".equals(doType)) {
			myForm.setShzt("th");
		}

		String cflbdm = request.getParameter("cflbdm");
		String spgw = myForm.getSpgwId();
		String cfId = myForm.getCfId();
		myForm.setCflbdm(cflbdm);
		myForm.setSpgwId(spgw);

		HashMap<String, String> upSpgwmap = wjcfcfshService.getHigherUpSpMap(
				myForm, user);// �ϼ�������λ
		boolean sfDyj = false;
		if (null == upSpgwmap || upSpgwmap.size() == 0) {// ���û���ϼ�������λ˵��Ϊ��һ�����������˻ز���
			sfDyj = true;
		}

		HashMap<String, String> maxSpgwmap = wjcfcfshService.getMaxSpgw(myForm,
				user);// ��߼�������λ
		boolean sfZgj = false;
		if (spgw.equals(maxSpgwmap.get("spgw"))) {
			sfZgj = true;
		}
		//ת��
		if(null!=myForm.getShyj()){
			myForm.setShyj(myService.unicode2Gbk(myForm.getShyj()));
		}
		if(null!=myForm.getCfwh()){
			myForm.setCfwh(myService.unicode2Gbk(myForm.getCfwh()));
		}
		boolean flag = wjcfcfshService.saveCfsh(myForm, user);
		
		if (flag){
			//===���칤��=====2013-1-15 qph==beign======
			String[] id = myForm.getCfId().split(",");
			String curShgw = myForm.getSpgwId();
			String[] spgwArr = cfsbService.getSpgwByCflb(myForm.getCflbdm());
			int index = StringUtils.getStrIndexInArray(curShgw, spgwArr);
			
			for (String str : id){
				Job job = null;
				if ("tg".equals(myForm.getShzt())) {
					String nextShgw = index!=spgwArr.length-1 && spgwArr[index+1] != null ? spgwArr[index+1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"general_wjcf.do?method=cfshCx&xmdm="+myForm.getCflbdm()+"&spgw="+nextShgw,"�����ϱ�");
				} else if ("th".equals(myForm.getShzt())){
					String nextShgw = index!=0 ? spgwArr[index-1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"general_wjcf.do?method=cfshCx&xmdm="+myForm.getCflbdm()+"&spgw="+nextShgw,"�����ϱ�");
				}  else {
					job = Job.getJobInstance(str,"�����ϱ�");
				}
				MyJobsManager manager = new MyJobsImpl();
				manager.updateJob(job);
			}
			//===���칤��=====2013-1-15 qph==end=========
		}
		
		request.setAttribute("message", flag == true ? "�����ɹ�" : "����ʧ��");

		request.setAttribute("sfDyj", sfDyj);// �Ƿ��һ��
		request.setAttribute("sfZgj", sfZgj);// �Ƿ���߼�
		request.setAttribute("spgw", spgw);
		request.setAttribute("cfId", cfId);
		request.setAttribute("cflbdm", myForm.getCflbdm());
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
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);

		request.setAttribute("tongjiList", wjcfcfshService.tongjiList());
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
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);

		boolean flag = wjcfcfshService.tjSh();
		request.setAttribute("message", flag == true ? "�����ɹ�" : "����ʧ��");
		request.setAttribute("tongjiList", wjcfcfshService.tongjiList());
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
		WjcfCfshInterface wjcfcfshService = myService
				.getWjcfCfshService(myForm);

		boolean flag = wjcfcfshService.zjtjSh(myForm);
		request.setAttribute("message", flag == true ? "�����ɹ�" : "����ʧ��");
		return null;
	}
	
}
