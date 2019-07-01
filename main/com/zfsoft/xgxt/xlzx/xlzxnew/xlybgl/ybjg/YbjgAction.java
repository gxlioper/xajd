package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg;

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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.newp.StringUtil;

public class YbjgAction extends SuperAction<YbjgForm, YbjgService> {
	private final String url = "xg_xlzxnew_ybjg.do";
	private YbjgService service = new YbjgService();
	/**
	 * 
	 * @����: ��ȡ�±������ѯList
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����06:37:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getYbjgCxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ybjgcx");
	}
	
	/**
	 * @description	�� �±�����
	 * @author 		�� CP��1352��
	 * @date 		��2017-12-27 ����07:15:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getYbhzCxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbjgForm model = (YbjgForm)form;
		if (QUERY.equals(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getYbhzList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xg_xlzxnew_ybhz.do");
		request.setAttribute("userType", getUser(request).getUserType());
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ybhzcx");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ѯ�±�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����06:42:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward seachYbjgCxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbjgForm myForm = (YbjgForm)form;
		User user = getUser(request);
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: �����±����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-5 ����11:25:22
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
	public ActionForward addYbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbjgForm myForm = (YbjgForm)form;
		User user = getUser(request);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("txr",user.getRealName());
		myForm.setXn(Base.currXn);
		myForm.setTxr(user.getUserName());
		request.setAttribute("xnList",Base.getXnndList());
		return mapping.findForward("addybjg");
	}
	
	/**
	 * 
	 * @����: �޸��±����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-5 ����11:36:58
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
	public ActionForward editYbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbjgForm myForm = (YbjgForm)form;
		YbjgForm model = service.getModel(myForm);
		if(model != null){
			BeanUtils.copyProperties(myForm, model);
			request.setAttribute("yf", Integer.valueOf(model.getYf())+"��");
			request.setAttribute("sfywt", model.getSfywt());
			request.setAttribute("xymc",service.getXymc(model.getXydm()));
		}
		User user = getUser(request);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("txr",user.getRealName());
		myForm.setTxr(user.getUserName());
		request.setAttribute("wtryInfo", service.getYbWtryInfo(model.getJgid()));
		return mapping.findForward("editybjg");
	}
	
	/**
	 * 
	 * @����: �鿴�±����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-5 ����11:36:58
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
	public ActionForward ckYbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbjgForm myForm = (YbjgForm)form;
		YbjgForm model = service.getModel(myForm);
		if(model != null){
			BeanUtils.copyProperties(myForm, model);
			request.setAttribute("yf", Integer.valueOf(model.getYf())+"��");
			request.setAttribute("xymc",service.getXymc(model.getXydm()));
		}
		User user = getUser(request);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("txr",user.getRealName());
		request.setAttribute("wtryInfo", service.getYbWtryInfo(model.getJgid()));
		return mapping.findForward("ckybjg");
	}
	
	/**
	 * 
	 * @����: ����ѧ����תҳ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����11:23:08
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
	public ActionForward addStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setAttribute("xhs",request.getParameter("xhs"));
		request.setAttribute("xydm",request.getParameter("xydm"));
		request.setAttribute("path","xg_xlzxnew_addxystu.do");
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("addstu");
	}
	
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����11:43:58
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
	public ActionForward searchForStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		YbjgForm myForm = (YbjgForm)form;
		String xhs = request.getParameter("xhs");
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getStuCx(myForm, user, xhs);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: ����ѧ����תҳ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����11:23:08
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
	public ActionForward addXy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		return mapping.findForward("addxy");
	}
	
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����11:43:58
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
	public ActionForward searchForXy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getXyList(request.getParameter("xymc"));
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ���ϱ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-2 ����03:01:55
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				service.delSbjg(ids);
			}
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����10:33:20
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YbjgForm model = (YbjgForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
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
	/**
	 * @description	�� �����Ƽ���  ���ܵ���
	 * @author 		�� CP��1352��
	 * @date 		��2017-12-28 ����11:03:15
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportHzData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YbjgForm model = (YbjgForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllHzList(model,
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
	/**
	 * 
	 * @����: �����ϱ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����04:33:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public ActionForward saveYbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		YbjgForm myForm = (YbjgForm)form;
		String[] xhArray = request.getParameterValues("xh");
		String[] ybwtmsArray = request.getParameterValues("ybwtms");
		String[] ybgycsArray = request.getParameterValues("ybgycs");
		String[] ybgyhjgArray = request.getParameterValues("ybgyhjg");
		String[] wtfsrqArray = request.getParameterValues("wtfsrq");
		myForm.setXhArray(xhArray);
		myForm.setYbwtmsArray(ybwtmsArray);
		myForm.setYbgycsArray(ybgycsArray);
		myForm.setYbgyhjgArray(ybgyhjgArray);
		myForm.setTxr(getUser(request).getUserName());
		myForm.setWtfsrqArray(wtfsrqArray);
		YbjgService tranService = new YbjgService();
		boolean rs = true;
		if ("10704".equals(Base.xxdm)) {
			if ("��".equals(myForm.getSfywt())&&myForm.getJgid()!=null) {//��� ԭ�������⣬��Ϊû���⣬��������������ԭ�еĸ������
				rs = tranService.update(myForm);
			}else {
				rs = tranService.saveYbjg(myForm);
			}
		}else {
			rs = tranService.saveYbjg(myForm);
		}
		
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
	    return null;
	}
	
	
}
