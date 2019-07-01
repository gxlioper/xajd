/**
 * @部门:学工产品事业部
 * @日期：2015-2-11 上午09:13:40 
 */  
package com.zfsoft.xgxt.zdxljk.ecmm;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.Encrypt;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.szdw.jtff.FdyjtffService;

/** 
 * @类功能描述: 浙大心理健康--特别关心学生 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-2-11 上午09:11:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class EcmmAction extends SuperAction<EcmmModel, EcmmService> {

	private static final String url = "zdxljk_ecmm.do";
	
	/**查询列表**/
	@SystemAuth(url = url)
	public ActionForward ecmmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "zdxljk_ecmm.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ecmmList");
	}
	
	
	/**ajax查询**/
	@SystemAuth(url = url)
	public ActionForward getEcmmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		EcmmModel model = (EcmmModel) form;
		EcmmService service = new EcmmService();
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel); 
		
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;
	}
	
	
	
	/**二次密码维护**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addEcmm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		EcmmModel ecmmForm = (EcmmModel) form;
		EcmmService service = new EcmmService();
		FdyjtffService fdyService = new FdyjtffService();
		
		if (!StringUtil.isNull(ecmmForm.getZgh())){
			//加载f辅导员基本信息
			HashMap<String,String> jbxx = fdyService.getFdyjbxx(ecmmForm.getZgh());
			request.setAttribute("jbxx", jbxx);
		}
		
		EcmmModel model = service.getModel(ecmmForm);
		if (model != null){
			request.setAttribute("update", true);
			BeanUtils.copyProperties(ecmmForm, model);
		}
		String path = "zdxljkEcmm.do?method=addEcmm";
		request.setAttribute("path", path);
		return mapping.findForward("addEcmm");
	}
	
	
	/**新增保存***/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		EcmmModel model = (EcmmModel) form;
		EcmmService service = new EcmmService();
		
		Encrypt e = new Encrypt();
		model.setEcmm(e.encrypt(model.getEcmm()));
		
		boolean result = service.runInsert(model);
		
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/****修改****/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editEcmm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		EcmmModel ecmmForm = (EcmmModel) form;
		EcmmService service = new EcmmService();
		
		FdyjtffService fdyService = new FdyjtffService();
		
		if (!StringUtil.isNull(ecmmForm.getZgh())){
			//加载f辅导员基本信息
			HashMap<String,String> jbxx = fdyService.getFdyjbxx(ecmmForm.getZgh());
			request.setAttribute("jbxx", jbxx);
		}
		
		EcmmModel model = service.getModel(ecmmForm);
		BeanUtils.copyProperties(ecmmForm, model);
		return mapping.findForward("editEcmm");
	}
	
	
	/**修改保存***/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		EcmmModel model = (EcmmModel) form;
		EcmmService service = new EcmmService();
		
		Encrypt e = new Encrypt();
		model.setEcmm(e.encrypt(model.getEcmm()));
		
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
		EcmmService service = new EcmmService();
		
		boolean result = service.runDelete(ids.split(",")) > 0;
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveInitEcmm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String ids = request.getParameter("ids");
		
		EcmmModel model = (EcmmModel) form;
		EcmmService service = new EcmmService();
		
		boolean result = service.initEcmm(ids.split(","), model.getEcmm());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	public ActionForward initEcmm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		EcmmModel model = (EcmmModel) form;
		EcmmService service = new EcmmService();
		return mapping.findForward("initEcmm");
	}
}
