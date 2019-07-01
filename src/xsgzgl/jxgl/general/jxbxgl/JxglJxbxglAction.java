package xsgzgl.jxgl.general.jxbxgl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class JxglJxbxglAction extends BasicAction{

	
	/**
	 * ��ѵ���ֹ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxbxCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxbxglService JxglJxbxglService = new JxglJxbxglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("jxgl_jxkhgl_jxbxgl.do");
		// ----------------����PATH end-----------------------
		JxglJxbxglService.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			String msg = "��ģ�鲻����ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("jxbxCx");
		}
		
	}
	
	/**
	 * ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bxmdCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxbxglService JxglJxbxglService = new JxglJxbxglService();
		JxglJxbxglForm model = (JxglJxbxglForm)form;
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		String pkValue = request.getParameter("pkValue");
		String sfhd = request.getParameter("sfhd");
		if("yes".equalsIgnoreCase(sfhd)){
			SearchModel searchModel=new SearchModel();
			searchModel.setSearch_tj_sfhd(new String[]{"��"});
			searchModel.setPath("jxgl_jxbxgl.do?method=bxmdCx");
			request.setAttribute("searchTj", searchModel);
		}
		model.setPkValue(pkValue);
		request.setAttribute("pkValue", pkValue);
		HashMap<String,String> rs = JxglJxbxglService.getJxbx(model);
		request.setAttribute("rs", rs);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		request.setAttribute("path", "jxgl_jxkhgl_jxbxgl.do");
		FormModleCommon.commonRequestSet(request);
		rForm.setPath("jxgl_jxbxgl.do?method=bxmdCx");
		// ----------------����PATH end-----------------------
		JxglJxbxglService.setRequestValue(rForm, user, request);
		return mapping.findForward("bxmdCx");
		
	}
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxbxglForm model = (JxglJxbxglForm) form;
		JxglJxbxglService service = new JxglJxbxglService();
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllbxmd(model, request);

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}
