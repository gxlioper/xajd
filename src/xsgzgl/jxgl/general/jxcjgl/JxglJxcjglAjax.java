package xsgzgl.jxgl.general.jxcjgl;

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
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;


/**
 * ��ѵ����-��ѵ���˹���-��ѵ�ɼ�����
 * @author yeyipin
 * @since 2012.10.13
 */
public class JxglJxcjglAjax extends BasicAction{
	
	/**
	 * ��ѵ�ɼ���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxcjCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxcjglService jxglJxcjglService = new JxglJxcjglService();
		JxglJxcjglForm model = (JxglJxcjglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		jxglJxcjglService.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxkhgl_jxcjgl.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		String jxid = request.getParameter("otherValue").split("!!@@!!")[1];
		model.setJzid(jxid);
		List<HashMap<String, String>> topTr = jxglJxcjglService.getTopTr(jxid);
		// �����
		ArrayList<String[]> rsArrList = jxglJxcjglService.jxcjCx(model,request);
		// ���������
		String spHtml = jxglJxcjglService.createSearchHTML(model,rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		jxglJxcjglService.createRs(rsModel, model.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
		
	}
	
	/**
	 * ��ѵ�ɼ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxcjBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxcjglService jxglJxcjglService = new JxglJxcjglService();
		JxglJxcjglForm model = (JxglJxcjglForm) form;
		//������������
		model.setPkValue(jxglJxcjglService.unicode2Gbk(model.getPkValue()));
		String message = jxglJxcjglService.jxcjBc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * ��ѵ�ɼ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxcjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JxglJxcjglService jxglJxcjglService = new JxglJxcjglService();
		JxglJxcjglForm model = (JxglJxcjglForm) form;
		// ============= ��ʼ����������ֵ ============
		model.getSearchModel().setPath("jxgl_jxkhgl_jxcjgl.do");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		jxglJxcjglService.jxcjDc(response.getOutputStream(),model,request);
		return null;
	}
	
	/**
	 * ��ѵ����ɼ��Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward jxcjglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxcjglService jxglJxcjglService = new JxglJxcjglService();
		JxglJxcjglForm model = (JxglJxcjglForm) form;
		
		String jxid = request.getParameter("jxid");
		model.setJzid(jxid);
		User user = getUser(request);// �û�����
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		List<HashMap<String,String>> resultList = jxglJxcjglService.jxcjExportDataCx(model,request);
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
