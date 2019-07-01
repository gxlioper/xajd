package xsgzgl.gygl.gypy.gypywh;

import java.io.File;
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
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
/**
 * ��Ԣ����-��Ԣ����-��Ԣ���Ź���
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class GypywhAjax  extends BasicExtendAction{
	
	
	/**
	 * ��Ԣ���Ų�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gypyCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GypywhService service = new GypywhService();
		GypywhForm model = (GypywhForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("gygl_gypygl_gypywh.do");
		request.setAttribute("path", "gygl_gypygl_gypywh.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gypy","99");
		// �����
		ArrayList<String[]> rsArrList = service.gypyCx(model,request);
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
	 * ��Ԣ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pyqsCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GypywhService service = new GypywhService();
		GypywhForm model = (GypywhForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("gygl_gypywh.do?method=pyqsCx");
		request.setAttribute("path", "gygl_gypywh.do?method=pyqsCx");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		String xn = request.getParameter("xn");
		String xqdm = request.getParameter("xqdm");
		String pydx = request.getParameter("pydx");
		String pylbdm = request.getParameter("pylbdm");
		String pysj = request.getParameter("pysj");
		model.setXn(xn);
		model.setXqdm(xqdm);
		model.setPylbdm(pylbdm);
		model.setPysj(pysj);
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("pyqs",pydx);
		// �����
		ArrayList<String[]> rsArrList = null;
		if("1".equalsIgnoreCase(pydx)) {
			rsArrList = service.pyqsCx(model,request);
		}else if("0".equalsIgnoreCase(pydx)) {
			model.getSearchModel().setPath("gygl_gypywh.do?method=pyldCx");
			request.setAttribute("path", "gygl_gypywh.do?method=pyldCx");
			rsArrList = service.pyldCx(model,request);
		}else if("2".equalsIgnoreCase(pydx) || "3".equalsIgnoreCase(pydx) || "4".equalsIgnoreCase(pydx)) {
			rsArrList = service.pyzzCx(model,request);
		}
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
	 * ��Ԣ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ���Ź���-��Ԣ����ά��-����XN:{xn},XQDM:{xqdm},PYLBDM:{pylbdm},PYSJ:{pysj}")
	public ActionForward gypyZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GypywhService service = new GypywhService();
		GypywhForm model = (GypywhForm) form;
		//������������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		model.setXn(service.unicode2Gbk(model.getXn()));
		model.setXqdm(service.unicode2Gbk(model.getXqdm()));
		model.setPylbdm(service.unicode2Gbk(model.getPylbdm()));
		model.setPysj(service.unicode2Gbk(model.getPysj()));
		String message = service.gypyZj(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * ��Ԣ����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="���ʹ�Ԣ����-��Ԣ���Ź���-��Ԣ����ά��-ɾ��PK:{pkValue}")
	public ActionForward gypySc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GypywhService service = new GypywhService();
		GypywhForm model = (GypywhForm) form;
		//������������
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.gypySc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * �ճ���������ѯ�Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypywhService service = new GypywhService();
		GypywhForm model = (GypywhForm) form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.setUser(user);
		// �����
		List<HashMap<String,String>> resultList = service.gypyCxForDc(model, request);
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
