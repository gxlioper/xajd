/**
 * @部门:学工产品事业部
 * @日期：2015-12-12 下午03:39:31 
 */  
package com.zfsoft.xgxt.comm.provicecitylocal;

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
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-12-12 下午03:39:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SsxAction extends SuperAction<SsxModel, SsxService> {
	SsxService ssx = new SsxService();
		
	/**
	 * 
	 * @描述:获取城市map
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-12 下午04:18:29
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
	public ActionForward getCtiyMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SsxModel model = (SsxModel) form;
		List<HashMap<String, String>> citylist = ssx.getCtiyMap(model.getProvicedm());
		JSONArray jsoncitylist = JSONArray.fromObject(citylist);
		response.getWriter().print(jsoncitylist);
		return null;
	}
	
	/**
	 * 
	 * @描述:获取县区信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-12 下午05:13:55
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
	public ActionForward getLocalMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SsxModel model = (SsxModel)form;
		List<HashMap<String, String>> locallist = ssx.getLocalMap(model.getProvicedm(), model.getCitydm());
		JSONArray jsonlocallist = JSONArray.fromObject(locallist);
		response.getWriter().print(jsonlocallist);
		return null;
	}
	
	/**
	 * 
	 * @描述:获取省信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-12 下午05:14:14
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
	public ActionForward getProviceMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SsxModel model = (SsxModel)form;
		List<HashMap<String, String>> provicelist = ssx.getProviceMap();		
		JSONArray jsonprovicelist = JSONArray.fromObject(provicelist);
		response.getWriter().print(jsonprovicelist);
		return null;
	}
	
	/**
	 * 
	 * @描述:获取总信息（字段有数据情况下，修改模式下用）
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-12 下午05:14:14
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
	public ActionForward getTotalMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SsxModel model = (SsxModel)form;
		//省信息
		List<HashMap<String, String>> provicelist = ssx.getProviceMap();		
		JSONArray jsonprovicelist = JSONArray.fromObject(provicelist);
		//城市信息
		List<HashMap<String, String>> citylist = ssx.getCtiyMap(model.getProvicedm());
		JSONArray jsoncitylist = JSONArray.fromObject(citylist);
		//县区信息
		List<HashMap<String, String>> locallist = ssx.getLocalMap(model.getProvicedm(), model.getCitydm());
		JSONArray jsonlocallist = JSONArray.fromObject(locallist);
		JSONObject json = new JSONObject();
		json.put("jsonprovicelist", jsonprovicelist);
		json.put("jsonprovicelist", jsoncitylist);
		json.put("jsonlocallist", jsonlocallist);
		response.getWriter().print(json);
		return null;
	}
	
	public ActionForward getSsxQcName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SsxModel model = (SsxModel)form;
		//省信息
		String qcname = ssx.getSsxQcName(model.getProvicedm());
		HashMap<String, String> datamap = new HashMap<String, String>();
		datamap.put("qcname", qcname);
		JSONObject json = JSONObject.fromObject(datamap);
		response.getWriter().print(json);
		return null;
	}

}
