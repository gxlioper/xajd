package xsgzgl.pjpy.general.wdpj.lssb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import xgxt.pjpy.comm.pjpy.pjlc.xmsb.PjpyXmsbInit;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyWdpjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjLssbInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-7-11 ����10:36:33</p>
 */
public class WdpjLssbAction extends BasicAction{
	
	// -------------------------�������� begin--------------------------------
	/**
	 * ��ѯ�ϱ�ѧ���б�
	 * (ָ����Ŀ���༶)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward searchWdpjLssb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		RequestForm rForm = new RequestForm();
		
		PjpyWdpjModel model = new PjpyWdpjModel();
		SearchRsModel rsModel = new SearchRsModel();
		
		PjpyGeneralService myService = new PjpyGeneralService();
		
		PjpyWdpjInit init = new PjpyWdpjInit();
		
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initLssb(rForm, myForm, user, request);
		WdpjLssbInterface service = myService.getWdpjLssbService(myForm);
		
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// ��Ŀ����
		String xmdm=otherValue[1];
		model.setXmdm(xmdm);
		// �༶����
		String bjdm=otherValue[2];
		model.setBjdm(bjdm);
		
		// ==================��ҳ���========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		HashMap<String,String>qdrsMap=service.getQdrsByBj(xmdm, bjdm);
		// ����
		List<HashMap<String, String>> topTr = service.getWdpjLssbTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getWdpjLssbList(myForm, model,
				user);
		// ���������
		String spHtml = service.createWdpjLssbHTML(rsModel,model,qdrsMap, rsArrList,
				user);
		
		spHtml+=service.createKidneyDiv(rsModel, model,qdrsMap, rsArrList, user);
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
	// -------------------------�������� end--------------------------------

	// -------------------------�ϱ���������� begin--------------------------------	
	/**
	 * ������Ŀ�ϱ�
	 * author qlj
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXmsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BasicModel basicModel=new BasicModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjpyWdpjInit init = new PjpyWdpjInit();
	
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initSaveLssb(rForm, myForm,basicModel, user, request);
		WdpjLssbInterface service = myService.getWdpjLssbService(myForm);
		// ��������
		String opera=request.getParameter("opera");

		//��Ϣ��Ϣ
		String message="";
		
		String xmdm= request.getParameter("xmdm");
		
		String xh=request.getParameter("xh");
		
		//�������ݷ���
		boolean flag=false;
		if("add".equalsIgnoreCase(opera)){
			
			flag=service.saveXmsb(basicModel, user);
			
			if(flag){
				flag=service.saveLssbShInfo(xmdm, xh);
			}
			
			if (flag){
				
				//===���칤��=====2013-1-18===qph=======begin=========
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
			
	
		}else{
//			 ִ���޸ķ���
			flag=service.updateLssbInfo(basicModel, user);
			
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		
		}
		
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}

	/**
	 * ������ʦ�ϱ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward disfrockLssbInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BasicModel model = new BasicModel();
		
		BasicService service = new BasicService();
		// ���������ֶ�
		model.setPk(new String[] { "xh","xmdm","pjxn","pjxq","pjnd" });
		// ��unic��תgbk
		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));
		// ��ȡ����ֵ
		String[] pkValue = pkStr.split("!!array!!");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		// --------------����------------
		model.setTableName("xg_pjpy_pjxmsqb");

		// ����
		model.setPkValue(pkValue);
		
		// ɾ���������Ϣ
		flag = service.batchDelete(model);
		
		if(flag){
			// ɾ����˱���Ϣ
			model.setTableName("xg_pjpy_pjxmshb");
			
			flag = service.batchDelete(model);
			
			if (flag){
				//===���칤��==2013-1-18==qph===begin=======
				MyJobsManager manager = new MyJobsImpl();
				manager.removeJob(pkValue, "��������");
				//===���칤��==2013-1-18==qph===end=========
			}
			
		}
		
		// ��ʾ��Ϣ
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ��ʦ�ϱ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showLssbDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		//init.initSaveXssq(rForm, myForm, user, request);
		WdpjLssbInterface service = myService.getWdpjLssbService(myForm);
		// ��������
		String xh=request.getParameter("xh");
		
		String xmdm = request.getParameter("xmdm");
		
		String opera = request.getParameter("opera");
		
		//��ʦ�ϱ�
		service.showLssbDiv( user,opera,xmdm,xh, response);
		
		return null;
	}
	
	/**
	 * �ж�ѧ���Ƿ����ϱ��ʸ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXssbZg(ActionMapping mapping,
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
		// ��Ŀ����
		String xmdm=request.getParameter("xmdm");
		// ѧ��
		String xh=request.getParameter("xh");
		model.setXh(xh);
		// ��Ŀ����
		model.setXmdm(xmdm);
		// �ж�ѧ���Ƿ������뽱ѧ����ʸ�
		message=pjtjService.checkSqzg(model, user);
		
		response.setContentType("text/html;charset=gbk");
		
		response.getWriter().print(message);

		return null;
	}
	// -------------------------�ϱ���������� end--------------------------------		
	
	// -------------------------ѧ����Ϣ��� begin------------------------
	/**
	 * ��ʾѧ�������Ϣ(�γ̳ɼ����۲�ɼ�)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showXsxxDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		WdpjLssbInterface service = myService.getWdpjLssbService(myForm);
		// ��������
		String xh=request.getParameter("xh");
		
		String xmdm = request.getParameter("xmdm");
		
		service.showXsxxDiv( user,xmdm,xh, response);
		
		return null;
	}
	// -------------------------ѧ����Ϣ��� end------------------------
}
