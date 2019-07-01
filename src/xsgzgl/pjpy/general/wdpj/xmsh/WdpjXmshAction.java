package xsgzgl.pjpy.general.wdpj.xmsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_��Ŀ���_ͨ��_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class WdpjXmshAction extends BasicAction {
	
	/**
	 * ��ѯ��Ŀ��˽��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchWdpjXmsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInit init = new PjpyWdpjInit();
		WdpjXmshModel model = new WdpjXmshModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initXmsh(rForm, myForm, user, request);
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		
		String xmdm = otherValue[2];
		
		String spgw = otherValue[3];
		
		model.setXmdm(xmdm);
		model.setSpgw(spgw);
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
		List<HashMap<String, String>> topTr = service.getWdpjXmshTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getWdpjXmshList(myForm, model,
				user);
		// ���������
		String spHtml = service.createWdpjXmshHTML(rsModel, model, rsArrList,
				user);
		// ����ѧУ���Ի���Ϣ������
		spHtml+=service.createKidneyDiv(rsModel,rForm, model, rsArrList, user);
		
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");
		
		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	//====================old=================================	
	
	/**
	 * ��ѯ��Ŀ��˽��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPjpyXmsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInit init = new PjpyWdpjInit();
		WdpjXmshModel model = new WdpjXmshModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initXmsh(rForm, myForm, user, request);
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		// ��Ŀ����
		String xmdm = otherValue[1];
		model.setXmdm(xmdm);
		myForm.setXmdm(xmdm);
		
		// ��Ŀ����
		String xmmc = otherValue[2];
		
		// stylePath
		String stylePath=otherValue[3];
		rsModel.setStylePath(stylePath);
		
		if(!Base.isNull(xmmc)){
			model.setXmmc(myService.unicode2Gbk(xmmc));
		}
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
		List<HashMap<String, String>> topTr = service.getWdpjXmshTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getWdpjXmshList(myForm, model,
				user);
		// ���������
		String spHtml = service.createWdpjXmshHTML(rsModel, model, rsArrList,
				user);
		// ����ѧУ���Ի���Ϣ������
		spHtml+=service.createKidneyDiv(rsModel,rForm, model, rsArrList, user);
		
		
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");

		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ������˱���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePlShzt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommService commService=new CommService();
		WdpjXmshModel model=new WdpjXmshModel();
		PjpyWdpjModel pjtjModel=new PjpyWdpjModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchModel searchModel = new SearchModel();
		
		// ============= ��ʼ����������ֵ ============
		init.initZhcpMaintain(rForm, myForm, user, request);
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		WdpjPjtjInterface pjtjService = myService.getWdpjPjtjService(myForm);
		

		myForm.setSearchModel(searchModel);
		// ============= end ============

		String message="";
		
		commService.getModelValue(model, request);
		
		pjtjModel.setXmdm(model.getXmdm());
		pjtjModel.setSqr(model.getXh());
		pjtjModel.setGwid(model.getSpgw());
	
		if("tg".equalsIgnoreCase(model.getShzt())){
			message=pjtjService.checkShzg(pjtjModel, user);
		}
		
		boolean flag=false;
		
		if(Base.isNull(message)){
			flag = service.updateShzt(model,request, user);
			message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
			
			if (flag){
				//===���칤��=====2013-1-15 qph==beign======
				PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
				String[] id = pjtjModel.getSqr();
				String[] spgw = pjxmService.getSpgwByXmdm(model.getXmdm());
				int index = StringUtils.getStrIndexInArray(model.getSpgw(), spgw);
				
				for (String str : id){
					Job job = null;
					String pkValue = str+"!!@@!!"+model.getXmdm()+"!!@@!!"+jbszModel.getPjxn()+"!!@@!!"+jbszModel.getPjxq()+"!!@@!!"+jbszModel.getPjnd();
					if ("tg".equals(model.getShzt())) {
						String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
						job = Job.getJobInstance(pkValue,nextShgw,
								"general_pjpy.do?method=xmshManage&xmdm="+model.getXmdm()+"&spgw="+nextShgw,"��������");
					} else if ("th".equals(model.getShzt())){
						String nextShgw = index!=0 ? spgw[index-1] : null;
						job = Job.getJobInstance(pkValue,nextShgw,
								"general_pjpy.do?method=xmshManage&xmdm="+model.getXmdm()+"&spgw="+nextShgw,"��������");
					} else {
						job = Job.getJobInstance(pkValue,"��������");
					}
					MyJobsManager manager = new MyJobsImpl();
					manager.updateJob(job);
				}
				
				//===���칤��=====2013-1-15 qph==end=========
			}
			
			
		}
		
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
//
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ������˸�λѡ��DIV
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showShgwDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		WdpjXmshModel model=new WdpjXmshModel();
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		//init.initSaveXssq(rForm, myForm, user, request);
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		
		String xmdm = request.getParameter("xmdm");
		
		model.setXmdm(xmdm);

		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		
		if(Base.isNull(message)){
			
			service.showShgwDiv(model, user, response);
			
		}

		return null;
	}
	
	/**
	 * ������˱���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveShzt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommService commService=new CommService();
		WdpjXmshModel model=new WdpjXmshModel();
		PjpyWdpjModel pjtjModel=new PjpyWdpjModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjpyZhcpInit init = new PjpyZhcpInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchModel searchModel = new SearchModel();
		
		// ============= ��ʼ����������ֵ ============
		init.initZhcpMaintain(rForm, myForm, user, request);
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		WdpjPjtjInterface pjtjService = myService.getWdpjPjtjService(myForm);

		myForm.setSearchModel(searchModel);
		// ============= end ============

		// ============= �������С�����ã�δ��ѡ�� ============
		String message="";
		
		commService.getModelValue(model, request);
		
		pjtjModel.setXmdm(model.getXmdm());
		pjtjModel.setSqr(model.getXh());
		pjtjModel.setGwid(model.getSpgw());
	
		boolean flag = false;
		if("tg".equalsIgnoreCase(model.getShzt())){
			message=pjtjService.checkShzg(pjtjModel, user);
		}
		
		if(Base.isNull(message)){
			flag = service.updateShzt(model,request, user);
			message = flag ? "��˳ɹ�" : "���ʧ�ܣ�����ϵ�����Ա";
			
			if (flag){
				//===���칤��=====2013-1-15 qph==beign======
				PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
				String[] id = model.getXh();
				String[] spgw = pjxmService.getSpgwByXmdm(model.getXmdm());
				int index = StringUtils.getStrIndexInArray(model.getSpgw(), spgw);
				
				for (String str : id){
					Job job = null;
					String pkValue = str+"!!@@!!"+model.getXmdm()+"!!@@!!"+jbszModel.getPjxn()+"!!@@!!"+jbszModel.getPjxq()+"!!@@!!"+jbszModel.getPjnd();
					if ("tg".equals(model.getShzt())) {
						String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
						job = Job.getJobInstance(pkValue,nextShgw,
								"general_pjpy.do?method=xmshManage&xmdm="+model.getXmdm()+"&spgw="+nextShgw,"��������");
					} else if ("th".equals(model.getShzt())){
						String nextShgw = index!=0 ? spgw[index-1] : null;
						job = Job.getJobInstance(pkValue,nextShgw,
								"general_pjpy.do?method=xmshManage&xmdm="+model.getXmdm()+"&spgw="+nextShgw,"��������");
					} else {
						job = Job.getJobInstance(pkValue,"��������");
					}
					MyJobsManager manager = new MyJobsImpl();
					manager.updateJob(job);
				}
				//===���칤��=====2013-1-15 qph==end=========
			}
			
			
		}
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
//
		response.getWriter().print(message);

		return null;
	}
	
}
