package xsgzgl.gygl.wsjc.fscx;

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

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 ����11:29:22
 * </p>
 */
public class FscxAjax extends BasicAction {

	/**
	 * ������飬��������Ϣ�Ĳ�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fscxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm myForm = (FscxForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyglnew_wsjc_fscx.do");
		request.setAttribute("path", "gyglnew_wsjc_fscx.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// =================== end ===================
		List<HashMap<String, String>> topTr = service.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getFscxCx(myForm,request);

		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);

		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 * �����ֲ�ѯ �Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward wsfcxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FscxService service = new FscxService();
		FscxForm model = (FscxForm) form;		
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);		
		User user = getUser(request);	
		List<HashMap<String,String>> resultList =  service.getFscxExportCx(model,request,user); 		
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
	 * ������飬����������Ϣ�ı���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "���ʹ�Ԣ����-�������-�����ֲ�ѯ-�޸�PK:{guid},FZ:{fz},JCRQ:{jcrq},JCBM:{jcbm},JCRY:{jcry}")
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FscxService service = new FscxService();
		String message = "";
		boolean flag = false;
		FscxForm myForm = (FscxForm) form;
		User user = getUser(request);

		String pkValue = request.getParameter("pkValue");
		String lddm = request.getParameter("lddm");
		String qsh = service.unicode2Gbk(request.getParameter("qsh"));
		// ��ȡ��ҳ�洫�����ķ�ֵ
		String fz = request.getParameter("fz");

		// ��ȡ��ҳ�洫�����ĵȼ�
		String dj = service.unicode2Gbk(request.getParameter("dj"));
		String jcrq = request.getParameter("jcrq");
		String jcbm = service.unicode2Gbk(request.getParameter("jcbm"));
		String jcry = service.unicode2Gbk(request.getParameter("jcry"));
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String username = user.getUserName();

		myForm.setLddm(lddm);
		myForm.setQsh(qsh);
		myForm.setDj(fz);
		myForm.setDj(dj);
		myForm.setJcrq(jcrq);
		myForm.setJcbm(jcbm);
		myForm.setJcry(jcry);
		myForm.setBz(bz);

		flag = service.fscxXg(myForm, pkValue, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����: ��֤����ճ��Ƿ��ύ
	 * @���ߣ��׽���[���ţ�781]
	 * @���ڣ�2013-9-2 ����09:31:20
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
	public ActionForward checkJcrcSftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String message = "";
		boolean flag = false;
		FscxForm myForm = (FscxForm) form;
		User user = getUser(request);
		FscxService service = new FscxService();
		
		flag = service.checkJcrcSftj(myForm, request, user);
		
		if(!flag){
			message = "����������,����δ�ύ���ճ̡�";
		}
		
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @����: ���������ȼ�
	 * @���ߣ��׽���[���ţ�781]
	 * @���ڣ�2013-9-9 ����08:43:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward pdFsDj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		FscxService service = new FscxService();
		FscxForm model = (FscxForm) form;		
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);		
		User user = getUser(request);	
		
		String message = "";
		boolean flag = false;
		
		flag = service.pdQsDjXg(model, request, user);
		if (Base.isNull(message)) {
			message = flag ? "�����������" : "û�����ύ���ճ�";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		
		return null;
	}
	
	
	/**
	 * �����ֲ�ѯ ������ѧ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward bjgmdExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FscxService service = new FscxService();
		FscxForm model = (FscxForm) form;		
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);		
		User user = getUser(request);	
		request.setAttribute("path", "gyglnew_wsjc_fscx.do");
		List<HashMap<String,String>> resultList =  service.getbjgmdExportCx(model,request,user); 		
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
	 * 
	 * @����:�´����������ָ��Ի�����
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-29 ����10:28:10
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
	public ActionForward wsfDcOfWd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FscxService service = new FscxService();
		FscxForm exporModel = (FscxForm) form;	
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("gyglnew_wsjc_fscx.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.wsfDcOfWd(exporModel, request,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	
	/** 
	 * @����:��ͳ�Ƶ���(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-7 ����11:04:37
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
	public ActionForward zTjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FscxService service = new FscxService();
		FscxForm exporModel = (FscxForm) form;	
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		
		searchModel.setPath("gyglnew_wsjc_fscx.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.zTjdc(exporModel, request,response.getOutputStream(),user);
		
		// ============= end ============

		return null;
	}
	
}