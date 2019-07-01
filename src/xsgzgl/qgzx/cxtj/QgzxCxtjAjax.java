package xsgzgl.qgzx.cxtj;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
/**
 * �ڹ���ѧ-��ѯͳ��-���ͳ�Ƶ���
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxCxtjAjax extends BasicAction{
	
	/**
	 * ��λ��Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		String qgzq = QgCommUtilf.getQgzq();
		model.getSearchModel().setPath("qgzx_cxtj_gwxx.do");
        if("xq".equals(qgzq)){
        	model.getSearchModel().setPath("qgzx_cxtj_gwxx_xq.do");
		}
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gwxx");
		// �����
//		ArrayList<String[]> rsArrList = service.gwxxCx(model);
		// ���������
//		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
//		rsModel.setRsArrList(rsArrList);
//		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 *��λ��Ϣ��ѯ�Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward gwxxcxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = service.getExportList(model,user);
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
	 * ѧ����λ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgwCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("qgzx_cxtj_xsgw.do");
		String qgzq = QgCommUtilf.getQgzq();
		if("xq".equals(qgzq)){
			model.getSearchModel().setPath("qgzx_cxtj_xsgw_xq.do");
		}
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("xsgw");
		// �����
//		ArrayList<String[]> rsArrList = service.xsgwCx(model);
		// ���������
//		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
//		// ==================����ǰ̨ҳ��========================
//		rsModel.setTopTr(topTr);
//		rsModel.setRsArrList(rsArrList);
//		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 *ѧ����λ��ѯ�Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward xsgwcxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;

		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.xsgwCx(model,user);

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
	 * ���ѻ�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfhbCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("qgzx_cxtj_jfhb.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("jfhb");
		// �����
		ArrayList<String[]> rsArrList = service.jfhbCx(model);
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ���ѻ�����ѯ�Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward jfhbCxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = service.jfhbCxExport(model,user);
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
	 * ��𷢷Ų�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjffCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("qgzx_cxtj_cjff.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("cjff");
		// �����
		ArrayList<String[]> rsArrList = service.cjffCx(model);
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ��𷢷Ų�ѯ�Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cjffCxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// �����
		List<HashMap<String,String>> resultList = service.cjffCxExport(model,user);
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
	 * @����:������ҵ��ѧ�����걨��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-19 ����09:30:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward cjffCxExportData_10022(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+new String("�걨��".getBytes("gb2312"),"iso-8859-1")+".xls");
		
		service.cjffCxExportData_10022(model, response.getOutputStream(), user);
		return null;
	}
	
	/**
	 * ���ų�𷢷�ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bmcjffTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		model.setBmdm(request.getParameter("bmdm"));
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		request.setAttribute("path", "qgzx_cxtj_cjtj.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.bmcjffTj(model);
		// ���������
		String spHtml = service.createSearchHTMLByBm(model,rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	/**
	 * @����:��λ��𷢷�ͳ��
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-18 ����10:05:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward gwcjffTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		model.setBmdm(request.getParameter("bmdm"));
		model.setGwmc(URLDecoder.decode(request.getParameter("gwmc"),"UTF-8"));

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		request.setAttribute("path", "qgzx_cxtj_cjtj.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.gwcjffTj(model);
		// ���������
		String spHtml = service.createSearchHTMLByGw(model,rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ���˳�𷢷�ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grcjffTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		model.setBmdm(request.getParameter("bmdm"));
		model.setGwmc(URLDecoder.decode(request.getParameter("gwmc"),"UTF-8"));
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		request.setAttribute("path", "qgzx_cxtj_cjtj.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.grcjffTj(model);
		// ���������
		String spHtml = service.createSearchHTMLByGr(model,rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	

	/**
	 * ���ų�𷢷ŵ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bmcjffDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		// ============= ��ʼ����������ֵ ============
		response.setContentType("application/vnd.ms-excel");
		//�����ļ�������ֹ��Ϊapi����ϵͳ�����Բ��ö������excel��׺���ĳ�.do
		 response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("qgzx_bmcjtj.xls".getBytes(), "GBK") + "\"");
		service.bmcjffDc(response.getOutputStream(),model);
		return null;
	}
	
	/**
	 * ���˳�𷢷ŵ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grcjffDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		// ============= ��ʼ����������ֵ ============
		response.setContentType("application/vnd.ms-excel");
		//�����ļ�������ֹ��Ϊapi����ϵͳ�����Բ��ö������excel��׺���ĳ�.do
		 response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("qgzx_grcjtj.xls".getBytes(), "GBK") + "\"");
		service.grcjffDc(response.getOutputStream(),model);
		
		return null;
	}
	/**
	 * @����:��λ��𷢷ŵ���
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-18 ����10:00:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward gwcjffDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		// ============= ��ʼ����������ֵ ============
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("qgzx_gwcjtj.xls".getBytes(), "GBK") + "\"");
		service.gwcjffDc(response.getOutputStream(),model);
		
		return null;
	}
	/**
	 * @���������˳�𷢷��·ݵ���
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��5��23�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * ActionForward ��������
	 */
	public ActionForward grcjffDcyf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		File wordFile = service.getjffTjyf(model);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @����:��ý���Ի�����
	 * @���ߣ�cq
	 * @���ڣ�2013-12-26 ����04:30:54
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
	public ActionForward cjffCxExportDataCm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm exporModel = new QgzxCxtjForm();
		CommService comService = new CommService();
		
		comService.getModelValue(exporModel, request);
		
		User user = getUser(request);
		

		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.cjffExpCm(exporModel, response.getOutputStream(),user);
		// ============= end ============

		return null;
	}
	/**
	 * 
	 * @����:���Ϲ���ְҵ����ѧԺ�ڹ���ѧ���˸��Ի�����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-6-30 ����05:34:57
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
	public ActionForward cjffCxExportDataJn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm exporModel = new QgzxCxtjForm();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("qgzx_cxtj_cjff.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		
		comService.getModelValue(exporModel, request);
		
		

		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.cjffExpJn(exporModel, response.getOutputStream(),user);
		// ============= end ============

		return null;
	}
	/**
	 * 
	 * @����:����ְҵ����ѧԺ�ڹ���ѧ����ͳ�Ƽ����������嵥
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-23 ����07:08:28
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
	public ActionForward cjffCxExportDataJzy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm exporModel = (QgzxCxtjForm)form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("qgzx_cxtj_cjff.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		List<File> files=service.cjffExpJzy(exporModel,user);
		File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
		FileUtil.outputZip(response, zipFile);
		// ============= end ============

		return null;
	}
	
	/**
	 * ���ų�𷢷Ų�ѯ
	 */
	public ActionForward bmcjffCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		model.setBmdm(request.getParameter("bmdm"));
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		request.setAttribute("path", "qgzx_cxtj_bmcjffcx.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.bmcjffCx(model);
		// ���������
		String spHtml = service.createBmcjffcxSearchHTML(model,rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ���ų�𷢷Ų�ѯ����
	 */
	public ActionForward bmcjffCxDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		// ============= ��ʼ����������ֵ ============
		response.setContentType("application/vnd.ms-excel");
		service.bmcjffCxDc(response.getOutputStream(),model);
		return null;
	}
	
	/**
	 * 
	 * @����: ���˳�𷢷��·ݵ���(�㽭��ְͨҵ����ѧԺ)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-4-19 ����02:46:49
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
	public ActionForward grcjffyfdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		File file = service.getExcelFile(model);
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("qgzxcjffdcqc.xls".getBytes(), "GBK") + "\"");
		response.setContentType("application/vnd.ms-excel");
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
