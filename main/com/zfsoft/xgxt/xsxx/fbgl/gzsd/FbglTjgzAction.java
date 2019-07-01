/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午09:43:04 
 */  
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午09:43:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FbglTjgzAction extends SuperAction {
	/**
	 * 
	 * @描述: 条件配置详细信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-27 下午05:06:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjgzid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public ActionForward getTjpzXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglTjgzService fs=new FbglTjgzService();
		String tjgzid=request.getParameter("tjgzid");
		List<HashMap<String, String>> list=fs.getTjpzXx(tjgzid);
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}
	/**
	 * 
	 * @描述:条件具体配置内容
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-10 下午02:01:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward getTjNrpzXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglTjgzService fs=new FbglTjgzService();
		String tjgzid=request.getParameter("tjgzid");
		String tjszzd=request.getParameter("tjszzd");
		HashMap<String, String> tjnrMap=fs.getTjNrpzXx(tjgzid,tjszzd);
		if(!StringUtils.isNull(tjnrMap.get("ylz"))){
			tjnrMap.put("mrylz", fs.getMrylz(tjnrMap.get("ylz")));//获取默认的预览值
		}
		response.getWriter().print(JSONObject.fromObject(tjnrMap));
		return null;
	}
	/**
	 * 
	 * @描述: 保存规则配置
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-19 上午08:54:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward saveGzpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglTjgzService fs=new FbglTjgzService();
		String json=request.getParameter("json");
		String jsonStr=URLDecoder.decode(json,"utf-8");
		JSONObject jo=new JSONObject(jsonStr);
		if(fs.isHave(null, jo.get("pzgzmc").toString())){
			response.getWriter().print(getJsonMessageByKey(MessageKey.FBGL_FBGZMC_USED));
			return null;
		}
		boolean isok=fs.saveGzpz(jo,null);
		String messageKey = isok ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
	/**
	 * 
	 * @描述: 修改规则配置
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-19 上午08:54:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward updateGzpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglTjgzService fs=new FbglTjgzService();
		String json=request.getParameter("json");
		String jsonStr=URLDecoder.decode(json,"utf-8");
		JSONObject jo=new JSONObject(jsonStr);
		String type=request.getParameter("type");
		if(fs.isHave(jo.get("gzpzid").toString(), jo.get("pzgzmc").toString())){
			response.getWriter().print(getJsonMessageByKey(MessageKey.FBGL_FBGZMC_USED));
			return null;
		}
		boolean isok=fs.updataGzpz(jo);
		String messageKey = isok ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
}
