/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:03:38 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团信息管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间：2013-10-22 下午12:03:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ShlcpzAction extends SuperAction {
	
	public static String _DTXX_SPLC_NAME="dtjs"; 
	
	private static final String url = "shlcpzbase.do";
	
	/**
	 * 
	 * @描述:党团信息管理列表查询显示
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-10-22 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShlcpzService service = new ShlcpzService();
		CommService cs = new CommService();
		ShlcpzForm myForm = (ShlcpzForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("shlcpzbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "shlcpzbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("shlcpzlb");
	}

	/**
	 * 
	 * @描述:批量删除
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-10-22 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShlcpzService service = new ShlcpzService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
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

	/**
	 * 
	 * @描述: 党团信息管理模块
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-10-22 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShlcpzService service = new ShlcpzService();
		ShlcpzForm myForm = (ShlcpzForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ShlcpzForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		//获取日常事务审核流
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx(_DTXX_SPLC_NAME);
		request.setAttribute("shlcList", shlc);
		//获取可使用阶段名称
		List<HashMap<String,String>> jdlist = service.getJdList(myForm.getJddm(),myForm.getJdmc());
		request.setAttribute("jdList", jdlist);
		return mapping.findForward("shlcpzxg");
	}
	/**
	 * 
	 * @描述:增加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-25 上午10:36:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShlcpzService service = new ShlcpzService();
		ShlcpzForm myForm = (ShlcpzForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		//获取日常事务审核流
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx(_DTXX_SPLC_NAME);
		request.setAttribute("shlcList", shlc);
		//获取可使用阶段名称
		List<HashMap<String,String>> jdlist = service.getJdList();
		request.setAttribute("jdList", jdlist);
		return mapping.findForward("shlcpzzj");
	}
	
}
