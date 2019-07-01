/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:10:21 
 */  
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:10:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FbglGzpzTjXxAction extends SuperAction {
	/**
	 * 
	 * @描述: 
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-19 上午11:03:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward getGzpzTjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjXxServer fgts=new FbglGzpzTjXxServer();
		String tjgzid=request.getParameter("tjgzid");
		String pzgzid=request.getParameter("pzgzid");
		List<HashMap<String, String>> list=fgts.getGzpzTjxxForLx(tjgzid, pzgzid, false);
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}
	/**
	 * 
	 * @描述: 获取配置规则选中类型
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-19 下午02:54:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward getGzpzTjSelect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjXxServer fgts=new FbglGzpzTjXxServer();
		String pzgzid=request.getParameter("pzgzid");
		String tjgzid=request.getParameter("tjgzid");
		String sx=request.getParameter("sx");
		String tjszzd=request.getParameter("tjszzd");
		HashMap<String, String> list=fgts.getGzpzTjgz(pzgzid, tjgzid,tjszzd,sx);
		response.getWriter().print(JSONObject.fromObject(list));
		return null;
	}
}
