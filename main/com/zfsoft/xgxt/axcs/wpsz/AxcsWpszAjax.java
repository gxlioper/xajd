/**
 * @部门:学工产品事业部
 * @日期：2014-12-3 下午06:17:35 
 */  
package com.zfsoft.xgxt.axcs.wpsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-3 下午06:17:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class AxcsWpszAjax extends BasicAction{
	/**
	 * 
	 * @描述:物品图片上传
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-3 下午06:18:10
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
	public ActionForward uploadWpPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		User user = getUser(request);// 用户对象
		String flag = service.saveWpPic(model, user);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag);
	
		return null;
	}
	
	public ActionForward getWpTjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		List<HashMap<String, String>> resultList = service.getWpTjList(model.getXmdm());
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	public ActionForward getWpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		HashMap<String, String> wpxx = service.getWpxxByXmdm(model.getXmdm());
		JSONObject data = JSONObject.fromMap(wpxx);
		response.getWriter().print(data);
	
		return null;
	}
	
}
