package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhklx;

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

/**
 * 火车优惠卡类型维护
 */
public class HcyhklxAction extends SuperAction {
	
	private static final String url = "rcsw_hcyhkgl_hcyhklx.do";

	/**
	 * 火车优惠卡类型查询
	 */
	@SystemAuth(url = url)
	public ActionForward hcyhklxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcyhklxForm model = (HcyhklxForm) form;
		HcyhklxService service = new HcyhklxService();
		if (QUERY.equals(model.getType())){
			User user = getUser(request);
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_hcyhkgl_hcyhklx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hcyhklxManage");
	}
	
	/**
	 * 增加 火车优惠卡类型 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车优惠卡类型-增加LXMC:{lxmc}")
	public ActionForward addHcyhklx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcyhklxForm model = (HcyhklxForm) form;
		HcyhklxService service = new HcyhklxService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			boolean isExist = service.isExist(model, "add");
			if (!isExist) {
				boolean result = service.saveInfo(model, "add");
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_HCYHK_HCYHKLX_EXIST));
				return null;
			}
		}
		
		String lxdm = service.changeHcyhkgllxdm();
		model.setLxdm(lxdm);
		request.setAttribute("model", model);
		return mapping.findForward("addHcyhklx");
	}
	
	/**
	 * 修改火车优惠卡类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车优惠卡类型-修改LXDM:{lxdm},LXMC:{lxmc}")
	public ActionForward updateHcyhklx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcyhklxService service = new HcyhklxService();
		HcyhklxForm myForm = (HcyhklxForm) form;
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){	
			boolean isExist = service.isExist(myForm, "update");
			if (!isExist) {
				boolean result = service.saveInfo(myForm, "update");
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_HCYHK_HCYHKLX_EXIST));
				return null;
			}
		}
		HcyhklxForm model = service.getHcyhklxForm(myForm);
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("updateHcyhklx");
	}
	
	/**
	 * 删除火车优惠卡类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车优惠卡类型-删除VALUES:{values}")
	public ActionForward delHcyhklx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcyhklxService service = new HcyhklxService();	
		String values = request.getParameter("values");
	
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deleteHcyhklxInfo(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
}
