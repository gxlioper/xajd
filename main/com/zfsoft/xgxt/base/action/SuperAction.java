package com.zfsoft.xgxt.base.action;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.LogInfo;
import com.zfsoft.xgxt.base.log.LogService;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.IPRequest;

import eu.bitwalker.useragentutils.UserAgent;
import xgxt.utils.String.StringUtils;

/**
 * 基础Action
 * @author qph
 * 日期：2013-4-12
 */
public abstract class SuperAction<T extends ActionForm, S extends SuperServiceImpl<T,?>> extends BasicAction {
	protected static final int RAM_MAX_SIZE =5000;//内存中记录缓存数量
	protected Log logger = LogFactory.getLog(SuperAction.class);
	private static final String writeable = "1";
	
	
	/**
	 * 覆盖基类execute，处理系统业务逻辑
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String parameter = mapping.getParameter();
		String methodName = super.getMethodName(mapping, form, request, response, parameter);
		
		if (methodName == null){
			return unspecified(mapping, form, request, response);
		}
		
		Method method = null;
		
		try{
			method = getMethod(methodName);
		} catch (NoSuchMethodException e){
			String message = messages.getMessage("dispatch.method", mapping.getPath(), methodName);
			log.error(message, e);
			String userMsg = messages.getMessage("dispatch.method.user", mapping.getPath());
			throw new NoSuchMethodException(userMsg);
		}
		
//		SystemAuth sysAuth = method.getAnnotation(SystemAuth.class);
//		
//		if (sysAuth != null){
//			User user = getUser(request);
//			Map<String,String> authMap = user.getAuthMap();
//			
//			/*
//			 * 权限不足的判断逻辑
//			 * 1、用户权限不包含注解url;
//			 * 或者
//			 * 2、注解要求可写权限并且用户权限非可写
//			 */
//			// 根据request中sindex的选择url
//			String [] urls = sysAuth.url();
//			String sindex = request.getParameter("sindex");
//			String url = null;
//
//			if(StringUtils.isNotNull(sindex)){
//				try{
//					url = urls[Integer.parseInt(sindex)];
//				}catch (NumberFormatException e){
//					url = urls[0];
//				}
//			}else {
//				url = urls[0];
//			}
//			if (!authMap.containsKey(url) || (sysAuth.rewritable().equals(ReadWrite.WRITEABLE) && !writeable.equals(authMap.get(url)))){
//				String message = MessageUtil.getText(MessageKey.SYS_AUTH_FAIL);;
//				//根据header信息判断是否异步提交
//				if (!StringUtil.isNull(request.getHeader("x-requested-with"))){
//					//ajax请求方式
//					response.getWriter().print(getJsonMessage(message));
//					return null;
//				} else {
//					//刷新方式
//					request.setAttribute("message", message);
//					return mapping.findForward("error");
//				}
//			}
//		}
		
		LogInfo logInfo = null;
		SystemLog sysLog = method.getAnnotation(SystemLog.class);
		
		if (sysLog != null){
			User user = getUser(request);
			String description = sysLog.description();
			UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));
			
			logInfo = new LogInfo();
			logInfo.setOsName(userAgent.getOperatingSystem().getName());
			logInfo.setBrowserName(userAgent.getBrowser().getName());
			logInfo.setBrowserVersion(userAgent.getBrowserVersion().getVersion());
			logInfo.setIp(IPRequest.getIpAddress(request));
			logInfo.setDescription(parseParams(request,description));
			logInfo.setClassName(mapping.getType());
			logInfo.setMethodName(methodName);
			
			if (user != null){
				logInfo.setUsername(user.getUserName());
				logInfo.setUserType(user.getUserType());
			}
		}
		
		ActionForward acf = null;
		
		try {
			acf = super.execute(mapping, form, request, response);
			if (logInfo != null){
				logInfo.setStatus("success");
			}
		} catch (Exception e) {
			
			logger.error(this,e);
			String message = null;
			
			if (e instanceof SystemException){
				message = e.getMessage();
			} else {
				message = MessageUtil.getText(MessageKey.EXP_SYS_ERROR);
			}
			
			if (logInfo != null){
				logInfo.setStatus("fail");
				logInfo.setMessage(message);
			}
			
			//根据header信息判断是否异步提交
			if (!StringUtil.isNull(request.getHeader("x-requested-with"))){
				//ajax请求方式
				response.getWriter().print(getJsonMessage(message));
			} else {
				//刷新方式
				request.setAttribute("message", message);
				return mapping.findForward("error");
			}
		} finally{
			
			if (logInfo != null){
				new LogService().runInsert(logInfo);
			}
		}
		return acf;
	}
	
	
	
	/*处理参数*/
	private String parseParams(HttpServletRequest request, String description) {

		String result = description.trim();
		Pattern p = Pattern.compile("\\{([^\\}]*)");//
		Matcher m = p.matcher(description);// 开始编译
		while (m.find()) {
			String key = m.group(1);
			String value = request.getParameter(key);
			value = value == null ? "" : value;
			result = result.replace(key, value);
		}
		// 避免超出字段长度
		result = result.length() > 2000 ? result.substring(0, 1999) : result;
		return result;
	}
	
	
	
	/**
	 * 把message封装为json对象
	 * @param message
	 * @return
	 */
	protected JSONObject getJsonMessage(String message){
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map); 
		return json;
	}
	
	
	/**
	 * 把message封装为json对象,传入key
	 * @param key
	 * @return
	 */
	protected JSONObject getJsonMessageByKey(String key){

		String message =  MessageUtil.getText(key);

		return getJsonMessage(message);
	}
	
	
	/**
	 * 把message封装为json对象,传入key
	 * @param key
	 * @return
	 */
	protected JSONObject getJsonResult(String key,boolean success){
		 
		String message =  MessageUtil.getText(key);
		Map<String,String> map = new HashMap<String,String>();
		map.put("message", message);
		map.put("success", String.valueOf(success));
		JSONObject json = JSONObject.fromObject(map); 
		return json;
	}
	
	
	/**
	 * 把message封装为json对象,传入key
	 * @param message，提示消息
	 * @return
	 */
	protected JSONObject getJsonMessageResult(String message,boolean success){
		Map<String,String> map = new HashMap<String,String>();
		map.put("message", message);
		map.put("success", String.valueOf(success));
		JSONObject json = JSONObject.fromObject(map); 
		return json;
	}

	
	/**
	 * 
	 * @描述: ajax 新增操作
	 * @作者： 屈朋辉[工号:445]
	 * @日期：2014年6月9日 上午9:51:00
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
	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T model = (T) form;
		S service = getService();
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @描述: ajax修改操作
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月9日 上午11:08:07
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
	@SuppressWarnings("unchecked")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		T model = (T) form;
		S service = getService();
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);
		return null;
	}
	
	/*
	 * 获取Service
	 */
	@SuppressWarnings("unchecked")
	protected S getService() {
		Type genType = getClass().getGenericSuperclass();  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
        try {
			return (S) ((Class<?>) params[1]).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
        return null;
	}
}
