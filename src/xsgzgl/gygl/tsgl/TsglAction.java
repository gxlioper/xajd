package xsgzgl.gygl.tsgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.Globals;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

public class TsglAction extends BasicAction {
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SystemLog(description = "���ʹ�Ԣ����-ͳ�Ʋ�ѯ-ѧ��������Ϣ��ѯ-ɾ��XH:{xh}")
	public ActionForward tsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_tsgl_tsgl.do");
		TsglForm myForm = (TsglForm) form;
		TsglService service = new TsglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		String doType=request.getParameter("doType");
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// �������ʾ�ֶ�
		String[] colList = new String[]{"ѧ��","����","�꼶",Base.YXPZXY_KEY,"�Ա�","��������","��סʱ��","����ʱ��","����ԭ��","���ޱ�ע"};

		String message="";
		if("delete".equalsIgnoreCase(doType)){
			
			boolean flag=service.deleteTsglInfo(myForm);
			
		    message = flag ? "�����ɹ�" : "����ʧ��";
			
		}
		
		// =============== ִ�в�ѯ���� ===========
		rsArrList = service.getTsglInfoList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("topTr", service.getToplist(colList));
		request.setAttribute("message", message);
		service.setRequestValue(rForm, user,request);
		
		request.setAttribute("tableName", "xg_gygl_new_tsxxb");	// ������
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================

		return mapping.findForward("success");
	}
	
	/**
	 *  ������Ϣ��ѯ �Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward tsxxcxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		TsglForm model = (TsglForm) form;
		TsglService service = new TsglService();
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);		
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList = service.getTsglInfoExportList(model,request);
		
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
	
	
	
	public ActionForward tsglView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = request.getParameter("pkValue");
		
		TsglService service = new TsglService();	
		HashMap<String,String> map = service.viewTsxx(pk);
		request.setAttribute("map", map);
		return mapping.findForward("tsglView");
	}
	

}
