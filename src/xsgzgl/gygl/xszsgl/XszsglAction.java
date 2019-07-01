package xsgzgl.gygl.xszsgl;

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

public class XszsglAction extends BasicAction {
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward xszsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_xszsgl_xszsgl.do");
		XszsglForm myForm = (XszsglForm) form;
		XszsglService service = new XszsglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		
		// �������ʾ�ֶ�
		String[] colList = new String[]{"ѧ��","����","�꼶",Base.YXPZXY_KEY,"�༶","�Ա�","¥������","���Һ�","��λ��","����Ա","������"};
		if(Globals.XXDM_NTZYDX.equals(Base.xxdm)){//��ְͨҵ��ѧ
			colList = new String[]{"ѧ��","����","�꼶",Base.YXPZXY_KEY,"�Ա�","¥������","���Һ�","��λ��","��ѧ��ʽ","������"};
			request.setAttribute("tableName", "xg_view_gygl_new_xszsgl_ntzydx");	// ������
		}else{
			request.setAttribute("tableName", "xg_view_gygl_new_xszsgl");	// ������
		}

		// =============== ִ�в�ѯ���� ===========
		rsArrList = service.getTsglZsxxList(myForm,request);
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("rsArrList", rsArrList);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("num", myForm.getPages().getMaxRecord());
		request.setAttribute("topTr", service.getToplist(colList));
		//ѧУ����
		request.setAttribute("xxdm", Base.xxdm);
		service.setRequestValue(rForm, user,request);
		
		FormModleCommon.commonRequestSet(request);
		// ================= end =====================

		return mapping.findForward("success");
	}
	
	
	/**
	 * ѧ��ס����Ϣ �Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward xszsxxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		XszsglForm model = (XszsglForm) form;
		XszsglService service = new XszsglService();
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		User user = getUser(request);
		
		//��ѯ
		List<HashMap<String,String>> resultList = service.getExportResultList(model,user,request);//��ѯ�����м�¼������ҳ
		
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
	
	
	
	public ActionForward bzxbzsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		XszsglForm myForm = (XszsglForm) form;
		XszsglService service = new XszsglService();
		String xhs=request.getParameter("xhs");
		request.setAttribute("xhs", xhs);
		String[] xhstr=xhs.split("-");
		if(xhstr.length==1){
			String bz=service.getBzxbz(xhstr[0]);
			request.setAttribute("bz", bz);
		}
		if("save".equalsIgnoreCase(doType)){
			
			boolean flag=service.setBz(myForm,xhstr);
			String msg=flag?"����ɹ�":"����ʧ��";
			request.setAttribute("message", msg);
		}
		return mapping.findForward("bzxbzsz");
	}
	/**
	 * ѧ��ס����Ϣ�鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszsxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh=request.getParameter("pkValue");
		XszsglService service = new XszsglService();
		HashMap<String, String> rs=new HashMap<String, String>();
		rs=service.getXszsxx(xh);
		request.setAttribute("rs", rs);
		return mapping.findForward("xszsxxView");
	}
	
	
	
}
