/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:24:25 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:24:25
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglGzdmAction extends SuperAction {
	/**
	 * 
	 * @描述: 获取条件规则
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-11 下午04:38:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward getTjgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzdmService service=new FbglGzdmService();
		String gzdm=request.getParameter("gzdm");
		List<HashMap<String, String>> list=service.getTjGzForGzdm(gzdm);
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}
}
