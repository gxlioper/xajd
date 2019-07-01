package com.zfsoft.xgxt.xsxx.tdjs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;


/**
 * �Ŷӽ���Action
 * @author qph
 * ���� 2013-4-10
 */
public class TdjsAction extends SuperAction {

	private static final String url = "tdjs.do?method=tdglManage";
	
	/**
	 * �Ŷӹ��� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward tdglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TdjsForm model = (TdjsForm) form;
		TdjsService service = new TdjsService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "tdjs.do?method=tdglManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xyList", service.getXyList());
		request.setAttribute("zyList", service.getZyList());
		request.setAttribute("bjList", service.getBjList());
		request.setAttribute("njList", service.getNjList());
		return mapping.findForward("tdglManage");
	}
	
	
	
	
	/**
	 * �����Ŷ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addTdinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TdjsForm model = (TdjsForm) form;
		TdjsService service = new TdjsService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
			//���¼����꼶��ѧԺ��רҵ���༶�б�
			new Thread(new Base.initialBj()).start();
			return null;
		}
		
		
		return mapping.findForward("addTdinfo");
	}
	
	
	
	/**
	 * �޸��Ŷ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateTdinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TdjsForm myForm = (TdjsForm) form;
		TdjsService service = new TdjsService();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
			//���¼����꼶��ѧԺ��רҵ���༶�б�
			new Thread(new Base.initialBj()).start();
			return null;
		}
		
		TdjsForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("xyList", service.getXyList());
		request.setAttribute("zyList", service.getZyList());
		request.setAttribute("njList", service.getNjList());
		return mapping.findForward("updateTdinfo");
	}
	
	
	
	
	/**
	 * ɾ���Ŷ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delTdinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TdjsService service = new TdjsService();
		
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.XSXX_TDJS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
			
			//���¼����꼶��ѧԺ��רҵ���༶�б�
			new Thread(new Base.initialBj()).start();
		}
		return null;
		
	}




	/**
	 * ����ָ����ʦ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadZdls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TdjsForm model = (TdjsForm) form;
		TdjsService service = new TdjsService();
		
		Map<String,String> zdlsInfo = service.getZdlsInfo(model);
		
		JSONObject map = JSONObject.fromObject(zdlsInfo); 
		response.getWriter().print(map);
		return null;
	}


	/**
	 * �����Ŷ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cjtd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TdjsForm model = (TdjsForm) form;
		TdjsService service = new TdjsService();
		
		model.setNj(Base.currNd);
		
		request.setAttribute("njList", service.getNjList());
		return mapping.findForward("cjtd");
	}
	

	/**
	 * �����Ŷ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sctd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TdjsForm model = (TdjsForm) form;
		TdjsService service = new TdjsService();
		
		List<HashMap<String,String>> tdinfoList = service.sctdInfo(model);
		
		request.setAttribute("tdinfoList", tdinfoList);
		request.setAttribute("xyList", service.getXyList());
		request.setAttribute("zyList", service.getZyList());
		request.setAttribute("njList", service.getNjList());
		return mapping.findForward("sctd");
	}


	
	/**
	 * ����רҵ�б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TdjsForm model = (TdjsForm) form;
		TdjsService service = new TdjsService();
		
		List<HashMap<String,String>> zyList = service.getZyList(model);
		JSONArray dataList = JSONArray.fromObject(zyList);
		response.getWriter().print(dataList);
		return null;
	}

	
	/**
	 * �����Ŷ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveTdinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TdjsForm model = (TdjsForm) form;
		TdjsService service = new TdjsService();
		
		boolean result = service.saveTdinfo(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	
	
	/**
	 * ����ѧ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward fpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TdjsService service = new TdjsService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			
			List<HashMap<String,String>> tdInfoList = service.getTdinfoList(values.split(","));
			
			request.setAttribute("tdInfoList", tdInfoList);
		}
		
		return mapping.findForward("fpxs");
	}
	
	
	
	/**
	 * ����ѧ������ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveFpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TdjsForm model = (TdjsForm) form;
		TdjsService service = new TdjsService();
		
		boolean result = service.saveFpxs(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	
	/**
	 * 
	 * @����:����ѧ���б�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-10 ����02:00:34
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
	@SystemAuth(url = url)
	public ActionForward showStudents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TdjsForm model = (TdjsForm) form;
		TdjsService service = new TdjsService();
		
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getStudents(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		return mapping.findForward("showStudents");
	}
}
