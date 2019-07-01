package xsgzgl.pjpy.general.wdpj.xssq;

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
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_ѧ������_ͨ��_Action��
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

public class WdpjXssqAction extends BasicAction {
	
	/**
	 * ��ѯ��Ŀ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchWdpjXssq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInit init = new PjpyWdpjInit();
		WdpjXssqModel model = new WdpjXssqModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initXssq(rForm, myForm, user, request);
		WdpjXssqInterface service = myService.getWdpjXssqService(myForm);
		
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// ��Ŀ����
		String xmlx = otherValue[1];
		model.setXmlx(xmlx.trim());
		// ��Ŀ����
		String xmxz = otherValue[2];
		model.setXmxz(xmxz.trim());
		// ��Ŀ����
		String xmmc = otherValue[3];
		if (!Base.isNull(xmmc.trim())) {
			model.setXmmc(myService.unicode2Gbk(xmmc.trim()));
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
		List<HashMap<String, String>> topTr = service.getWdpjXssqTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getWdpjXssqList(myForm, model,
				user);
		// ���������
		String spHtml = service.createWdpjXssqHTML(rsModel, model, rsArrList,
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

	//================old============================
	/**
	 * ��ѯѧ�����빦�ܵ���Ŀ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchXssqXmxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInit init = new PjpyWdpjInit();
		WdpjXssqModel model = new WdpjXssqModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initXssq(rForm, myForm, user, request);
		WdpjXssqInterface service = myService.getWdpjXssqService(myForm);
		
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// ��Ŀ����
		String xmlx = otherValue[1];
		model.setXmlx(xmlx.trim());
		// ��Ŀ����
		String xmxz = otherValue[2];
		model.setXmxz(xmxz.trim());
		// ��Ŀ����
		String xmmc = otherValue[3];
		if (!Base.isNull(xmmc.trim())) {
			model.setXmmc(myService.unicode2Gbk(xmmc.trim()));
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
		List<HashMap<String, String>> topTr = service.getWdpjXssqTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getWdpjXssqList(myForm, model,
				user);
		// ���������
		String spHtml = service.createWdpjXssqHTML(rsModel, model, rsArrList,
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
	 * ����ѧ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXssqInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BasicModel basicModel=new BasicModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjpyWdpjInit init = new PjpyWdpjInit();
	
		CommService commService=new CommService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initSaveXssq(rForm, myForm, basicModel, user,request);
		WdpjXssqInterface service = myService.getWdpjXssqService(myForm);
		// ��������
		String opera=request.getParameter("opera");

		//��Ϣ��Ϣ
		String message="";
		
		String xmdm= request.getParameter("xmdm");
		//�������ݷ���
		boolean flag=false;
		if("add".equalsIgnoreCase(opera)){ // ����
			// �������������ӿ�
			WdpjPjtjInterface pjtjService = myService.getWdpjPjtjService(myForm);
			
			PjpyWdpjModel model=new PjpyWdpjModel();
			// ��ȡrequest�������ֵ
			HashMap<String,String>valueMap=basicModel.getValueMap();
			// ѧ��
			model.setXh(valueMap.get("xh"));
			// ��Ŀ����
			model.setXmdm(valueMap.get("xmdm"));
			// �ж�ѧ���Ƿ������뽱ѧ����ʸ�
			message=pjtjService.checkSqzg(model, user);
			
			// ������������������Ϊ��
			if(Base.isNull(message)){
				
				// ���������¼
				flag=service.saveXssqInfo(basicModel,commService.getValueMap(request, "xg_pjpy_pjxmsqb"), user);
				
				if(flag){
					// ������˼�¼
					flag=service.saveWdpjShInfo(xmdm, user);
				}
				
				if (flag){
					//===���칤��=====2013-1-18===qph=======begin=========
					String xh = model.getXh();
					String[] spgw = pjxmService.getSpgwByXmdm(xmdm);
					HashMap<String,String> pjxmInfo = pjxmService.getPjxmInfo(xmdm);
					PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
					
					if (null != spgw && spgw.length > 0){
						Job job = Job.getJobInstance(xh+"!!@@!!"+xmdm+"!!@@!!"+jbszModel.getPjxn()+"!!@@!!"+jbszModel.getPjxq()+"!!@@!!"+jbszModel.getPjnd(), xh, spgw[0], 
								"general_pjpy.do?method=xmshManage&xmdm="+xmdm+"&spgw="+spgw[0], 
								"pjpy_general_wdpj.do","��������", pjxmInfo.get("xmmc"));
						MyJobsManager manager = new MyJobsImpl();
						manager.pushJob(job);
					}
					//===���칤��=====2013-1-18===qph=======end===========
				}
				
				message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
						: MessageInfo.MESSAGE_SAVE_FALSE;
		
			}
			
		}else { // �޸�
			
			// ִ���޸ķ���
			flag=service.updateXssqInfo(basicModel,commService.getValueMap(request, "xg_pjpy_pjxmsqb"), user);
			
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		
		}
		
		
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * �ж�ѧ�������ʸ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXssqZg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// �û�����

		//��Ϣ��Ϣ
		String message="";
		
		// �������������ӿ�
		WdpjPjtjInterface pjtjService = myService.getWdpjPjtjService(myForm);
		
		PjpyWdpjModel model=new PjpyWdpjModel();
		// ��ȡrequest�������ֵ
		String xmdm=request.getParameter("xmdm");
		// ѧ��
		model.setXh(user.getUserName());
		// ��Ŀ����
		model.setXmdm(xmdm);
		// �ж�ѧ���Ƿ������뽱ѧ����ʸ�
		message=pjtjService.checkSqzg(model, user);
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
		
	/**
	 * ����ѧ������DIV(���롢�޸ĸ��������ж�)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showXssqDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		WdpjXssqInterface service = myService.getWdpjXssqService(myForm);
		// ��������
		String opera=request.getParameter("opera");
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");

		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		String xxdm = Base.xxdm;
		if(Base.isNull(message)){
			if(xxdm.equals(Globals.XXDM_BJLHDX)){
				service.showXssqDivForBJLH(opera,xmdm, user, response);
			}else{
				service.showXssqDiv(opera,xmdm, user, response);
			}
		}
		return null;
	}
	
	/**
	 * ����ѧ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward disfrockXssqInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BasicModel model = new BasicModel();
		
		BasicService service = new BasicService();

		model.setPk(new String[] { "xh","xmdm","pjxn","pjxq","pjnd" });

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		// --------------����------------
		model.setTableName("xg_pjpy_pjxmsqb");

		// ����
		model.setPkValue(pkValue);

		flag = service.batchDelete(model);
		
		if(flag){
			
			model.setTableName("xg_pjpy_pjxmshb");
			
			flag = service.batchDelete(model);
			
			
			if (flag){
				//===���칤��==2013-1-18==qph===begin=======
				MyJobsManager manager = new MyJobsImpl();
				manager.removeJob(pkValue, "��������");
				//===���칤��==2013-1-18==qph===end=========
			}
			
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
}
