package xsgzgl.wjcf.jcsz;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * 
* 
* �����ƣ�WjcfJcszAction 
* ��������Υ�ʹ��ֻ�������Action
* �����ˣ�yijd 
* ����ʱ�䣺2012-6-19 ����09:20:00 
* �޸ı�ע��  
* @version 
*
 */
public class WjcfJcszAction extends BasicAction {

	private WjcfJcszServices service = new WjcfJcszServices();
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	/**
	 * Υ�ʹ���  �������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		List<String[]> rsList = service.cflbdmCx(model);

		request.setAttribute("rsList", rsList);
		request.setAttribute("rs", form);
		// ����Ϊ����������
		request.setAttribute("topTr", service.getTopTr(model,"wjcflb"));
		request.setAttribute("colnum", service.getColnumName(model,"wjcflb"));
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		// ���뵼��������
		request.setAttribute("tableName", "view_cflbdm");
		request.setAttribute("realTable", "xg_wjcf_cflbdmb");
		return mapping.findForward("success");
	}
	/**
	 * �Զ��嵼������������ά������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cflbExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList = service.cflbdmExportCx(model);
		
		//List<String[]> rsList = service.cflbdmCx(model);
		
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
	 * Υ�ʹ���  ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		WjcfJcszForm model = (WjcfJcszForm) form;

		if ("save".equals(doType)) {
			boolean result=service.cflbdmZj(model);
			request.setAttribute("result", result);//�������ӽ��
		}
		// ����Ϊ����������
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("wjcf"));
		request.setAttribute("doType", doType);
		
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("add");
	}
	
	/**
	 * Υ�ʹ���   �����������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		WjcfJcszForm model = (WjcfJcszForm) form;
		
		if ("update".equals(doType)) {
			boolean result=service.cflbdmXg(model);
			request.setAttribute("result", result);//�������ӽ��
		}
		HashMap<String, String> rs=service.cflbdmCk(pkValue);
		// ����Ϊ����������
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("wjcf"));
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		
		//�����ϱ��д���δ�����ɵ��������ܽ����޸������
		request.setAttribute("iskxg", service.cxCfsbBycflbdm(rs.get("cflbdm")));
		return mapping.findForward("update");
	}
	
	/**
	 * ��鴦���������Ƿ��ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmKfsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(service.cxCflbdmsfksc(pkValue));
		return null;
	}
	
	/**
	 * Υ�ʹ���   ����������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		//ִ��ɾ��
		boolean result=service.cflbdmSc(pkValue);
		
		request.setAttribute("doType", doType);
		request.setAttribute("result", result);
		cflbdmCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * Υ�ʹ���  ����������鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cflbdmCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		
		HashMap<String, String> rs=service.cflbdmCk(pkValue);
		// ����Ϊ����������
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("wjcf"));
		request.setAttribute("rs", rs);
		if(rs.get("spl").equals("wxsh")){
			request.setAttribute("wxsh", "yes");
		}else{
			request.setAttribute("wxsh", "no");
		}
		// ��ȡ�û����Ƿ��д��Ȩ�� ��title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("view");
	}
	
	
	/**
	 * Υ�ʹ��� ԭ������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfyydmCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		List<String[]> rsList = service.cfyydmCx(model);

		request.setAttribute("rsList", rsList);
		request.setAttribute("rs", form);
		// ����Ϊ����������
		request.setAttribute("topTr", service.getTopTr(model,"wjcfyy"));
		request.setAttribute("colnum", service.getColnumName(model,"wjcfyy"));
		// ��ȡ�û����Ƿ��д��Ȩ�� title
		request.setAttribute("path", "wjcfJcsz_cfyydm.do?method=cfyydmCx");
		FormModleCommon.commonRequestSet(request);
		// ���뵼��������
		request.setAttribute("tableName", "xg_wjcf_cfyydmb");
		request.setAttribute("realTable", "xg_wjcf_cfyydmb");
		return mapping.findForward("success");
	}
	
	/**
	 * �Զ��嵼��Υ�ʹ��� ԭ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward cfyydmExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList = service.cfyydmExportCx(model);
		
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
	 * Υ�ʹ���  ԭ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfyydmZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		WjcfJcszForm model = (WjcfJcszForm) form;
		if ("save".equals(doType)) {
			boolean result=service.cfyydmZj(model);
			request.setAttribute("result", result);//�������ӽ��
		}
		// ����Ϊ����������
		request.setAttribute("doType", doType);
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("add");
	}
	
	/**
	 * ���洦��ԭ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCfyy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		String lx = request.getParameter("lx");
		boolean flag = false;
		if("zj".equals(lx)){
			flag = service.cfyydmZj(model);
		}else{
			flag = service.cfyydmXg(model);
		}
		request.setAttribute("message",flag?"�����ɹ���":"����ʧ�ܣ�");
		List<String[]> rsList = service.cfyydmCx(model);

		request.setAttribute("rsList", rsList);
		request.setAttribute("rs", form);
		// ����Ϊ����������
		request.setAttribute("topTr", service.getTopTr(model,"wjcfyy"));
		request.setAttribute("colnum", service.getColnumName(model,"wjcfyy"));
		// ��ȡ�û����Ƿ��д��Ȩ�� title
		request.setAttribute("path", "wjcfJcsz_cfyydm.do?method=cfyydmCx");
		FormModleCommon.commonRequestSet(request);
		// ���뵼��������
		request.setAttribute("tableName", "xg_wjcf_cfyydmb");
		request.setAttribute("realTable", "xg_wjcf_cfyydmb");
		return mapping.findForward("success");
	}
	
	
	/**
	 * Υ�ʹ���  ����ԭ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xgCfyydm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		HashMap<String, String> rs=service.cfyydmCk(pkValue);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(JSONArray.fromObject(rs));
		return null;
	}
	
	/**
	 * Υ�ʹ���   ����ԭ������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfyydmXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		WjcfJcszForm model = (WjcfJcszForm) form;
		
		if ("update".equals(doType)) {
			boolean result=service.cfyydmXg(model);
			request.setAttribute("result", result);//�������ӽ��
		}
		HashMap<String, String> rs=service.cfyydmCk(pkValue);
		// ����Ϊ����������
		request.setAttribute("rs", rs);
		request.setAttribute("doType", doType);
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("update");
	}
	
	/**
	 * Υ�ʹ���   ����ԭ�����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfyydmSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		//ִ��ɾ��
		boolean result=service.cfyydmSc(pkValue);
		
		request.setAttribute("doType", doType);
		request.setAttribute("result", result);
		cfyydmCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
	/**
	 * Υ�ʹ���  ����ԭ�����鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfyydmCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = Base.chgNull(request.getParameter("pkValue"), "", 0);
		HashMap<String, String> rs=service.cfyydmCk(pkValue);
		// ����Ϊ����������
		request.setAttribute("rs", rs);
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "wjcfJcsz_cflbdm.do?method=cflbdmCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("view");
	}
	
	/**
	 * ���߽�������� ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssjcsplCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		HashMap<String, String> rs=service.ssjcsplCx(model);
		if(rs!=null && !rs.isEmpty()){
			request.setAttribute("rs", rs);
		}
		//����Ϊ����������
		List<HashMap<String, String>> shlcList = shlcService.getShlcByDjlx("wjcf");
		request.setAttribute("shlcList", shlcList);
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "ssjcsplCx.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("success");
	}
	
	/**
	 * ���߽�������� ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ssjcsplBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfJcszForm model = (WjcfJcszForm) form;
		boolean result=service.ssjcsplBc(model);
		//����Ϊ����������
		request.setAttribute("result", result);
		ssjcsplCx(mapping, form, request, response);
		return mapping.findForward("success");
	}
	
}
