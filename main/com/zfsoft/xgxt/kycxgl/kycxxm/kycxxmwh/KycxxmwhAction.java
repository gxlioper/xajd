package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmwh;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;

/**
 * 科研创新项目类别
 */
public class KycxxmwhAction extends SuperAction {
	
	private static final String url = "kycxgl_kycxxm_kycxxmwh.do";
	
	/**
	 * 科研创新项目类别
	 */
	@SystemAuth(url = url)
	public ActionForward kycxxmwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmwhForm model = (KycxxmwhForm) form;
		KycxxmwhService service = new KycxxmwhService();
		User user = getUser(request);
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "kycxgl_kycxxm_kycxxmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		request.setAttribute("currDate", df.format(new Date()));
		return mapping.findForward("kycxxmwhManage");
	}
	
	/**
	 * 增加 科研创新项目类别 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-类别维护-增加")
	public ActionForward addKycxxmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmwhForm myForm = (KycxxmwhForm) form;
		KycxxmwhService service = new KycxxmwhService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.checkKycxxmwhSave(myForm);
			String messageKey = "";
			if(result){
				result = service.runInsert(myForm);
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			}else{
				messageKey = MessageKey.KYCXGL_KYCXXM_KYCXXMWH_SAVE;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlcList = shlcService.getShlcByDjlx("kycxgl");
		request.setAttribute("shlcList", shlcList);
		return mapping.findForward("addKycxxmwh");
	}
	
	/**
	 * 修改科研创新项目类别 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-类别维护-修改LBDM:{lbdm}")
	public ActionForward updateKycxxmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmwhService service = new KycxxmwhService();
		KycxxmwhForm myForm = (KycxxmwhForm) form;
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.checkKycxxmwhSave(myForm);
			String messageKey = "";
			if(result){
				result = service.updateKycxxmwh(myForm);
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			}else{
				messageKey = MessageKey.KYCXGL_KYCXXM_KYCXXMWH_SAVE;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlcList = shlcService.getShlcByDjlx("kycxgl");
		request.setAttribute("shlcList", shlcList);
		KycxxmwhForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateKycxxmwh");
	}
	
	/**
	 * 删除 科研创新项目类别 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-类别维护-删除VALUES:{values}")
	public ActionForward delKycxxmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmwhService service = new KycxxmwhService();	
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			String checkMsg = service.checkKycxxmwhDel(values);
			if(!checkMsg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.KYCXGL_KYCXXM_KYCXXMWH_DEL,checkMsg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 时间开关
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-类别维护-申请开关-LBDM：{lbdm}")
	public ActionForward kycxxmwhSjkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmwhForm myForm = (KycxxmwhForm) form;
		KycxxmwhService service = new KycxxmwhService();
		KycxxmwhForm model = service.getModel(myForm);
		String lbmc = model.getLbmc();
		request.setAttribute("lbmc", lbmc);
		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.updateKycxxmwhSqkg(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		String path = "kycxgl_kycxxm_kycxxmwhgl.do?method=kycxxmwhManage";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("kycxxmwhSjkg");
	}
	
}
