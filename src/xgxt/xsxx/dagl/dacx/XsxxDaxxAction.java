/**
 * @部门:学工产品事业部
 * @日期：2014-4-22 上午10:53:31 
 */  
package xgxt.xsxx.dagl.dacx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.zfsoft.basic.BasicAction;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案查询页面
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-22 上午10:53:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxDaxxAction extends BasicAction{

	/**
	 * 档案查询（跳转）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryXsDaxx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("daxxcx");
	}
	
	/**
	 * 档案查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryXsDaxxAction(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xm = request.getParameter("xm");
		String sfzh = request.getParameter("sfzh");
		XsxxDaxxService xsxxDaxxService = new XsxxDaxxService();
		HashMap<String, String> xsxxDaxx = xsxxDaxxService.getXsxxDaxx(StringUtils.trim(xm), StringUtils.trim(sfzh));
		
		JSONObject dataObject = JSONObject.fromObject(xsxxDaxx);
		response.getWriter().print(dataObject);
		return null;
	}
}
