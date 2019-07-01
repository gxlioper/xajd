package xsgzgl.xsxx.general.qzxgmdsz;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class QzxgmdszAction extends BasicAction {
	/**
	 * ǿ���޸���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward qzxgmdCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszService service = new QzxgmdszService();
		QzxgmdszInit init = new QzxgmdszInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initQzxgmd(rForm, myForm, user, request);

		// ��ת·��
		String url = "/xsgzgl/xsxx/general/qzxgmdsz/qzxgmdCx.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
		
	}
	
	
	/**
	 * ��ѯǿ���޸������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchQzxgmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszInit init = new QzxgmdszInit();
		QzxgmdszForm model = new QzxgmdszForm();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initQzxgmd(rForm, myForm, user, request);
		QzxgmdszService service = new QzxgmdszService();

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
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		// ����
		List<HashMap<String, String>> topTr = service.getQzxgmdTop(model,
				user);
		// �����
		ArrayList<String[]> rsArrList = service.getQzxgmdList(myForm, model,
				user);
		// ���������
		String spHtml = service.createQzxgmdHTML(rsModel, model, rsArrList,
					user);
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
	 * ���ǿ���޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkQzxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszInit init = new QzxgmdszInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initQzxgmd(rForm, myForm, user, request);
		QzxgmdszService service = new QzxgmdszService();
		
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		
		String message = service.checkQzxg(myForm,user);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * ǿ���޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="����ѧ����Ϣ-��������-ǿ���޸���������-����-pk:{primarykey_checkVal}")
	public ActionForward szQzxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszInit init = new QzxgmdszInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initQzxgmd(rForm, myForm, user, request);
		QzxgmdszService service = new QzxgmdszService();
		
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		
		boolean flag = service.szQzxg(myForm,user);
		String message =flag?"�����ɹ���":"����ʧ�ܣ�";
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * ȡ��ǿ���޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="����ѧ����Ϣ-��������-ǿ���޸���������-ȡ��-pk:{primarykey_checkVal}")
	public ActionForward qxQzxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszInit init = new QzxgmdszInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initQzxgmd(rForm, myForm, user, request);
		QzxgmdszService service = new QzxgmdszService();
		
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		
		boolean flag = service.qxQzxg(myForm,user);
		String message =flag?"�����ɹ���":"����ʧ�ܣ�";
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 
	 * @����:ǿ���޸�����������ѯ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-25 ����02:24:26
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
	public ActionForward qzmdExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszService service = new QzxgmdszService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = service.getQzxgmdListForDc(myForm,user);
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}

}
