package xsgzgl.rcsw.zjbb;

import java.io.File;
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
import xgxt.utils.String.StringUtils;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.jqlx.GyglJqlxForm;
import xsgzgl.gygl.jqlx.GyglJqlxInit;
import xsgzgl.gygl.jqlx.GyglJqlxService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class RcswZjbbAjax extends BasicAction{

	RcswZjbbService service = new RcswZjbbService();
	
	RcswZjbbInit init =new RcswZjbbInit();
	
	// -------------------------�������ù��� begin ---------------------------------
	/**
	 * �������ù����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbszSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZjbbForm myForm = (RcswZjbbForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		init.initBbszSearch(rForm, model, request, user);

		// �������ʾ�ֶ�

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});
		
		service.getModelValue(myForm, request);

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================


		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getBbszList(myForm,model);

		// ���������
		String spHtml = service
				.createBbszSearchHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ֤���������Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward zjbbjgExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BasicModel model = new BasicModel();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		model.setColList(new String[] { "id", "xh", "xm", "nj", "xydm",
				"xymc", "zydm","zymc","bjdm","bjmc","sqsj","zjmc","sqjg" });
		
		//init.initBbjgSearch(rForm, model, request, user);
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//User user = getUser(request);// �û�����
		
		
		List<HashMap<String,String>> resultList =  service.getBbjgExportList(model,user);
		//�������ܴ���
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
	 * �������ù����ѯ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initBbszSave(model, request);
		
		
		flag = service.saveInfo(model);
		
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);


		return null;
	}
	
	/**
	 * �������ù����ѯ�޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiBbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initBbszModi(model, request);
		
		flag = service.updateInfo(model);
		
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);


		return null;
	}
	
	/**
	 * ��ʾ֤������ģʽ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZjbb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		RcswZjbbForm myForm = (RcswZjbbForm) form;
		BasicModel model=new BasicModel();
		
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		String option=request.getParameter("option");
		
		String id = request.getParameter("id");
		
		String[]pkValue=new String[]{id};
		String[]pk=new String[]{"id"};
		
		model.setPk(pk);
		model.setPkValue(pkValue);
		model.setTableName("xg_rcsw_zjbbszb");
		
		//��Ϣ��Ϣ
		String message="";
		
		//�������ݷ���
		
		if(Base.isNull(message)){
			
			String html=service.showZjbbDiv(user,response,option,model);
			request.setAttribute("html",html);
			request.setAttribute("zjmc",request.getParameter("zjmc"));
			request.setAttribute("lcid",request.getParameter("lcid"));
			request.setAttribute("xmid",request.getParameter("xmid"));
			request.setAttribute("id", id);
			return mapping.findForward("bbszzj");
		}

		return null;
	}
	
	/**
	 * ����ɾ��ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteBbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] {"id"});

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		// --------------����------------
		model.setTableName("xg_rcsw_zjbbszb");

		// ����
		model.setPkValue(pkValue);

		flag = service.batchDelete(model);
		
		message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	// -------------------------�������ù��� end ---------------------------------
	
	/**
	 * ����ɾ��ѯʦ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BasicModel model = new BasicModel();

		model.setPk(new String[] {"id"});

		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));

		String[] pkValue = pkStr.split("!!array!!");

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		// --------------����------------
		model.setTableName("xg_rcsw_zjbbsqb");

		// ����
		model.setPkValue(pkValue);

		// ����ɾ��

		flag = service.batchDelete(model);
		
		if(flag){
			model.setPk(new String[] {"sqid"});
			model.setTableName("xg_gygl_jqlxshb");
			service.batchDelete(model);
			
			//=======ɾ������==2013-1-15==qph==begin============
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(pkValue, "֤������");
			//=======ɾ������==2013-1-15==qph==end============
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ������У�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbsqSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZjbbForm myForm = (RcswZjbbForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		init.initBbsqSearch(rForm, model, request, user);

		// �������ʾ�ֶ�

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, model.getPath());

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getBbsqList(model);

		// ���������
		String spHtml = service
				.createBbsqSearchHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	

	/**
	 * ������У��˲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbshSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZjbbForm myForm=(RcswZjbbForm)form;
		
		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		model.setColList(new String[] { "id","xh","xm","nj","bjmc","sqsj","shzt" });
		
		init.initBbshSearch(rForm, model, request, user);

		// �������ʾ�ֶ�

		String[]otherValue=request.getParameter("otherValue").split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// IE�汾
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		// 
		String spgw = otherValue[3];
		model.getValueMap().put("spgw", spgw);
		
		String xmdm = otherValue[2];
		model.getValueMap().put("xmdm", xmdm);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user, "rcsw_zjbb_bbsh.do");

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getBbshList(model);

		// ���������
		String spHtml = service
				.createBbshSearchHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ������У�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bbjgSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZjbbForm myForm = (RcswZjbbForm) form;

		BasicModel model = new BasicModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		model.setColList(new String[] { "id", "xh", "xm", "nj", "yjzxsj",
				"sqsj", "sqjg" });
		
		init.initBbjgSearch(rForm, model, request, user);

		// �������ʾ�ֶ�

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// IE�汾
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		model.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);

		model.setSearchModel(searchModel);

		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================

		// ����
		model.setUser(user);
		List<HashMap<String, String>> topTr = init.getTopTr(model.getColList(),
				user,model.getPath());

		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getBbjgList(model);

		// ���������
		String spHtml = service
				.createSearchHTML(rsModel, model, rsArrList, user);

		// ����ѧУ���Ի���Ϣ������
		// spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ������У�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswZjbbForm myForm = (RcswZjbbForm) form;
		
		User user=getUser(request);

		BasicModel model = new BasicModel();
		
		model.setUser(user);

		// ��Ϣ��Ϣ
		String message = "";

		// �������ݷ���
		boolean flag = false;

		init.initBbsqSave(model, request);
		
		
		
		flag = service.saveInfo(model);
		
		if(flag){
			
			HashMap<String,String>valueMap=model.getValueMap();
			
			myForm.setXmid(valueMap.get("xmid"));
			
			HashMap<String,String>zjbbMap=service.getBbszInfo(myForm);
			
			String id=model.getValueMap().get("id");
			if(!"no".equalsIgnoreCase(zjbbMap.get("lcid"))){
				
				flag = service.saveZjbbShb(id,valueMap.get("xmid"));
			
			}
			
			
			/*
			* ���칤����ҵ��ID��������,������λ���������תURL����������תURL��ҵ���ܣ���Ŀ���ƣ�
			* 2013-1-10 qph
			*/
			String xh = valueMap.get("xh");
			String xmid = valueMap.get("xmid");
			
			try {
				String[] spgw = service.getSpgwByXmid(xmid);
				
				if (null != spgw && spgw.length > 0){
					RcswZjbbDAO zjbbDao = new RcswZjbbDAO();
					myForm.setXmid(xmid);
					HashMap<String,String> zjszInfo = zjbbDao.getBbszInfo(myForm);
					
					Job job = Job.getJobInstance(id, xh, spgw[0], 
							"rcsw_zjbb.do?method=bbshManage&xmid="+xmid+"&spgw="+spgw[0], 
							"rcsw_zjbb.do?method=zjbbCk&sqid="+id,"֤������", zjszInfo.get("zjmc"));
					MyJobsManager manager = new MyJobsImpl();
					manager.pushJob(job);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);


		return null;
	}
	
	/**
	 * ������У�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BasicModel basicModel=new BasicModel();
		
		RcswZjbbForm myForm = (RcswZjbbForm) form;
		
		init.initZjbbSh(basicModel, request);

		User user = getUser(request);// �û�����
	
		// ============= end ============

		String message="";
		
		boolean flag = false;
	
		flag = service.updateShzt(myForm,basicModel,request, user);
		message = flag ? "��˳ɹ�" : "���ʧ�ܣ�����ϵ�����Ա";
	
		if(flag){
			/*
			 * ���ʹ���
			 * 2013-1-10 qph
			 */
			String[] id = myForm.getSqid();
			String curShgw = myForm.getSpgw();
			String[] spgw = service.getSpgwByXmid(myForm.getXmid());
			int index = StringUtils.getStrIndexInArray(curShgw, spgw);
			
			for (String str : id){
				Job job = null;
				if ("tg".equals(myForm.getShzt())) {
					String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"rcsw_zjbb.do?method=bbshManage&xmid="+myForm.getXmid()+"&spgw="+nextShgw,"֤������");
				} else if ("th".equals(myForm.getShzt())){
					String nextShgw = index!=0 ? spgw[index-1] : null;
					job = Job.getJobInstance(str,nextShgw,
							"rcsw_zjbb.do?method=bbshManage&xmid="+myForm.getXmid()+"&spgw="+nextShgw,"֤������");
				} else {
					job = Job.getJobInstance(str,"֤������");
				}
				MyJobsManager manager = new MyJobsImpl();
				manager.updateJob(job);
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
		
		RcswZjbbForm myForm=(RcswZjbbForm)form;
		
		User user = getUser(request);// �û�����
		
		String xmdm=request.getParameter("xmdm");
		
		myForm.setXmid(xmdm);
		// ============= ��ʼ����������ֵ ============
		
		service.showShgwDiv(myForm, user ,response);
			
		return null;
	}
	/**
	 * 
	 * @����:(ѧ�����벹��)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-8-27 ����11:38:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward bbsqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("zjbbList", service.getZjbbList());
		return mapping.findForward("bbsqAdd");
	
	}
	
}
