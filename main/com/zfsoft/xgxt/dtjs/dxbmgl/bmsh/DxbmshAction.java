package com.zfsoft.xgxt.dtjs.dxbmgl.bmsh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @������������У�������action
 * @author������ ��1346��
 * @date��2017-11-1 ����03:39:52 
 */
public class DxbmshAction extends SuperAction<DxbmshForm, DxbmshService> {
	private static final String RCSWRCXW = "dtxxXsxxpz";
	/** 
	 * @������������ת��ѯҳ��
	 * @author������ ��1346��
	 * @date��2017-11-7 ����07:16:03 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxbmshCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxbmshService service = new DxbmshService();
		CommService cs = new CommService();
		DxbmshForm myForm = (DxbmshForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("dtjs_dxbmgl_dxbmshCx.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "dtjs_dxbmgl_dxbmshCx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dxbmshCx");
	}
	/** 
	 * @������������ת���ҳ��
	 * @author������ ��1346��
	 * @date��2017-11-7 ����07:26:18 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxbmshSh(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxbmshService service = new DxbmshService();
		DxbmshForm myForm = (DxbmshForm) form;
		// ѧ����Ϣ
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		HashMap<String, String> sqxx=service.getXspxBySqid(myForm.getSqid());
		DxbmshForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// ѧ��������Ϣ
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("sqxx", sqxx);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("dxbmshSh");
	}
	/** 
	 * @�������������������Ϣ
	 * @author������ ��1346��
	 * @date��2017-11-7 ����07:28:32 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxbmshBc(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxbmshService service = new DxbmshService();
		User user = getUser(request);
		DxbmshForm myForm = (DxbmshForm) form;
		boolean result = service.saveSh(myForm, user);
		response.getWriter().print(result);
		return null;
	}
	/** 
	 * @�����������鿴�����Ϣ
	 * @author������ ��1346��
	 * @date��2017-11-8 ����09:31:53 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	@SuppressWarnings("deprecation")
	public ActionForward dxbmshCk(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxbmshService service = new DxbmshService();
		DxbmshForm myForm = (DxbmshForm) form;
		DxbmshForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// ѧ����Ϣ
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMoreForDtgl(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ

		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("dxbmshCk");
	}
	
	/** 
	 * @��������������
	 * @author������ ��1346��
	 * @date��2017-11-8 ����11:22:54 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxbmshDc(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxbmshForm model = (DxbmshForm) form;
		// ���ݲ�ͬ��������� ȥ�Զ��嵼��
		String shlx = request.getParameter("shlx");
		DxbmshService service = new DxbmshService();
		model.setShzt(shlx);
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ

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
