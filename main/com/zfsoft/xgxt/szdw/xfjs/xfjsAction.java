/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-8-9 ����03:40:57 
 */  
package com.zfsoft.xgxt.szdw.xfjs;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ�罨��ά��ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� CP[����:1352]
 * @ʱ�䣺 2017-8-9 ����03:40:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class xfjsAction extends SuperAction{
	private xfjsService service = new xfjsService();
	private static final String url = "szdw_xfjswh.do";
	@SystemAuth(url = url)
	public ActionForward xfjsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xfjsForm model = (xfjsForm) form;
		if(QUERY.equals(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����(��ǰѧ��)
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xfjsList");
	}
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-10 ����09:46:22
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
	public ActionForward  addXfjs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		xfjsForm myForm = (xfjsForm) form;
		if(StringUtils.isNotNull(myForm.getBz())){
			   myForm.setBz(URLDecoder.decode((URLDecoder.decode(myForm.getBz(),"UTF-8")),"UTF-8"));
			}
		User user = getUser(request);
		myForm.setLrr(user.getUserName());
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean isExist = service.isExist(myForm);
	    	if (!isExist) {
	    		boolean flag = service.saveDataXf(myForm);
	        	String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
	    	}else {
	    		response.getWriter().print(getJsonMessageByKey(MessageKey.SZDW_XFJS_ADD_EXIST));
	    	}
				return null;
		}
		// ȡ��ǰѧ��
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("lrr", user.getRealName());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		//�õ���½�û�Ȩ���ڵ�¥���б�
		request.setAttribute("path", url);
		return mapping.findForward("addXfjs");
	}
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-10 ����02:55:41
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateXf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xfjsForm model = (xfjsForm) form;
		if(StringUtils.isNotNull(model.getBz())){
			model.setBz(URLDecoder.decode((URLDecoder.decode(model.getBz(),"UTF-8")),"UTF-8"));
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String messageKey;
			boolean flag = service.updateData(model);
			messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SZDW_XFJS_ADD_EXIST;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		xfjsForm myForm = service.getModel(model);
		BeanUtils.copyProperties(myForm,model);
		User user = getUser(request);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("lrr", user.getRealName());
		request.setAttribute("bjdm", myForm.getBjdm());
		request.setAttribute("id", myForm.getId());
		request.setAttribute("path", url);
		return mapping.findForward("updateXf");
		
	}

	/**
	 * 
	 * @����:�鿴
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-11 ���� 10:44:07
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
	public ActionForward viewXf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		xfjsForm myForm = (xfjsForm) form;
		xfjsForm model = service.getModel(myForm);;
		BeanUtils.copyProperties(model, myForm);
		request.setAttribute("rs", model);
		return mapping.findForward("viewXf");
	}
	
	
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-11 ����02:44:11
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delXf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
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
	 * @����:����
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-11����02:44:16
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		xfjsForm model = (xfjsForm) form;
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//��ѯ�����м�¼������ҳ
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
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-11 ����03:59:33
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
	public ActionForward bjManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		xfjsForm model = (xfjsForm) form;		
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getBjList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "szdw_xfjswh.do?method=bjManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjManage");
	}
	
}
