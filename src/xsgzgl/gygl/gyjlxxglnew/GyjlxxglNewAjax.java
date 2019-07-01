package xsgzgl.gygl.gyjlxxglnew;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.date.DateUtils;
import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class GyjlxxglNewAjax extends BasicAction{
	
	/**
	 * ��Ԣ������Ϣά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm myForm = (GyjlxxglNewForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gygl_gyjlglnew_gyjlxxwh.do");
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxwh.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gyjlxxwh");
		// �����
		ArrayList<String[]> rsArrList = service.gyjlxxwhCx(myForm,request);
		// ���������
		String spHtml = service.createSearchHTMLgyjlxxwh(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 * ��Ԣ������Ϣά�� �Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward gyjlxxwhexportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		//����path�ж��Ƿ���Ҫ��Ԣ����Ա���ݷ�Χ
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxwh.do");
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList  = service.gyjlxxwhExportCx(model,request);
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
	 * ��Ԣ����ѧ����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxscx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm myForm = (GyjlxxglNewForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyjl_gyjlglnew_gyjlxscx.do");
		request.setAttribute("path", "gyjl_gyjlglnew_gyjlxscx.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gyjlxscx");
		// �����
		ArrayList<String[]> rsArrList = service.gyjlxscx(myForm,request);
		// ���������ѧ����Ϣ
		String spHtml = service.createSearchHTMLXscx(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ѧ��������Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjlxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm myForm = (GyjlxxglNewForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		myForm.setXh(user.getUserName());
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyjl_gyjlglnew_xsjlxxcx.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("xsjlxxcx");
		// �����
		ArrayList<String[]> rsArrList = service.xsjlxxcx(myForm);
		// ���������ѧ����Ϣ
		String spHtml = service.createSearchHTMLXsjl(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ���ѧ����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		HashMap<String,String> rs = service.getStuInfo(model);
		JSONObject jsonObj = JSONObject.fromObject(rs);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	/**
	 * ��Ԣ������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm myForm = (GyjlxxglNewForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gygl_gyjlglnew_gyjlxxcl.do");
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxcl.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gyjlxxcl");
		// �����
		ArrayList<String[]> rsArrList = service.gyjlxxclCx(myForm,request);
		// ���������
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	/**
	 * ��Ԣ������Ϣ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm myForm = (GyjlxxglNewForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gygl_gyjlglnew_gyjlxxsh.do");
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxsh.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gyjlxxsh");
		// �����
		ArrayList<String[]> rsArrList = service.gyjlxxshCx(myForm,request);
		// ���������
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}
	
	
	/**
	 * ���ѧ����Ϣ    getWjxxList
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getWjxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		ArrayList<String[]> rs = service.getWjxxList(model);
		JSONObject jsonObj = JSONObject.fromObject(rs);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonObj.toString());
		return null;
	}
	
	
	
	/**
	 * ��Ԣ������Ϣ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxPlcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		//������������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		model.setCljg(service.unicode2Gbk(model.getCljg()));
		model.setDcqk(service.unicode2Gbk(model.getDcqk()));
		String message = service.gyjlxxPlcl(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * ��Ԣ������Ϣ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		User user = getUser(request);// �û�����
		//������������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		model.setShyj(service.unicode2Gbk(model.getShyj()));
		model.setShzt(model.getShzt());
		model.setShr(user.getUserName());
		model.setShsj(DateUtils.getCurrTime());
		String message = service.gyjlxxPlsh(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 
	 * @����:��Ԣ������Ϣ���  �Զ��嵼��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-12-30 ����03:28:05
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
	public ActionForward gyjlxxshexportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyjlxxglNewService service = new GyjlxxglNewService();
		GyjlxxglNewForm model = (GyjlxxglNewForm) form;
		//����path�ж��Ƿ���Ҫ��Ԣ����Ա���ݷ�Χ
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxsh.do");
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList  = service.gyjlxxshExportCx(model,request);
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
	
	
	
}
