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
 * 团队建设Action
 * @author qph
 * 日期 2013-4-10
 */
public class TdjsAction extends SuperAction {

	private static final String url = "tdjs.do?method=tdglManage";
	
	/**
	 * 团队管理 
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
	 * 增加团队
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
			
			//重新加载年级、学院、专业、班级列表
			new Thread(new Base.initialBj()).start();
			return null;
		}
		
		
		return mapping.findForward("addTdinfo");
	}
	
	
	
	/**
	 * 修改团队
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
			
			//重新加载年级、学院、专业、班级列表
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
	 * 删除团队
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
			
			//重新加载年级、学院、专业、班级列表
			new Thread(new Base.initialBj()).start();
		}
		return null;
		
	}




	/**
	 * 加载指导老师信息
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
	 * 创建团队
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
	 * 生成团队
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
	 * 加载专业列表
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
	 * 保存团队
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
	 * 分配学生
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
	 * 保存学生分配 
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
	 * @描述:加载学生列表
	 * @作者：Penghui.Qu
	 * @日期：2013-5-10 下午02:00:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
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
