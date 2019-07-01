/**
 * @部门:学工产品事业部
 * @日期：2015-07-31 下午02:24:28 
 */
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.xmlbgl;

import java.net.URLDecoder;
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

import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl.StlbglService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-07-31 下午02:24:28
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XmlbglAction extends SuperAction {
	private StlbglService stlbService = new StlbglService();
	
	private static final String url = "stgl_jcsz_dmwh.do";
	
	/**
	 * 
	 * @描述:项目类别列表
	 * @作者：xiaixa [工号：1104]
	 * @日期：2015-07-31 下午02:38:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getXmlbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmlbglForm model = (XmlbglForm) form;
		XmlbglService XmlbService = new XmlbglService();
		if (QUERY.equals(model.getType())) {
			String xmlbmc = request.getParameter("xmlbmc"); 
			xmlbmc = URLDecoder.decode(URLDecoder.decode(xmlbmc,"UTF-8"),"UTF-8"); 
			model.setXmlbmc(xmlbmc);
			String stlbmc = request.getParameter("stlbmc"); 
			stlbmc = URLDecoder.decode(URLDecoder.decode(stlbmc,"UTF-8"),"UTF-8");
			model.setStlbmc(stlbmc);
			List<HashMap<String, String>> resultList = XmlbService.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
			request.setAttribute("tabType", "xmlb");
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "stgl_jcsz_dmwh.do");
		request.setAttribute("currDate", GetTime.getTimeByFormat("yyyyMMdd"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXmlbList");

	}

	/**
	 * 
	 * @描述:增加项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午03:44:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXmlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmlbglForm model = (XmlbglForm) form;
		XmlbglService XmlbService = new XmlbglService();
		if (SAVE.equals(model.getType())) {
			boolean result = XmlbService.addXmlb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("stlbList", stlbService.getStlbList());
		return mapping.findForward("addXmlb");
	}

	/**
	 * 
	 * @描述:修改项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午03:50:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateXmlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmlbglForm myForm = (XmlbglForm) form;
		XmlbglService XmlbService = new XmlbglService();
		XmlbglForm model = XmlbService.getModel(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = XmlbService.updateXmlb(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("stlbList", stlbService.getStlbList());
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateXmlb");
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午04:03:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delXmlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmlbglService XmlbService = new XmlbglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//判断项目类别是否被占用
			if (XmlbService.isUsed(values)) {
				String messageKey = MessageKey.STGL_JCSZ_XMLB_USED;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			String[] value=values.split(",");
			int num = XmlbService.runDelete(value);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xmsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmlbglForm myForm = (XmlbglForm) form;
		XmlbglService XmlbService = new XmlbglService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = XmlbService.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XmlbglForm model = XmlbService.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xstgl");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		request.setAttribute("xmmc", model.getXmlbmc());
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		//FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsz");
	}
	
	//stlbdm和xmlbdm联动
	public ActionForward getXmlblist(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String stlbdm = request.getParameter("stlbdm");
		XmlbglService service = new XmlbglService();
		List<HashMap<String, String>> xmlblist = service.getXmlbList(stlbdm);
		StringBuilder html = new StringBuilder();
		if(xmlblist != null){
			for (HashMap<String, String> hashMap : xmlblist) {
				html.append("<option value='"+hashMap.get("xmlbdm")+"'>"+hashMap.get("xmlbmc")+"</option>");
			}
		}
		Map<String,String> map = new HashMap<String, String>();
		map.put("html", html.toString());
		JSONObject json = JSONObject.fromObject(map); 
		response.getWriter().print(json);
		return null;
	}
}
