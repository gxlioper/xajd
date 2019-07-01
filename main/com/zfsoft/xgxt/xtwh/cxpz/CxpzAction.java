/**
 * @部门:学工产品事业部
 * @日期：2013-5-27 下午02:23:49 
 */  
package com.zfsoft.xgxt.xtwh.cxpz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;


import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 自定义查询列 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-5-27 下午02:23:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxpzAction extends SuperAction {

	
	/**
	 * 
	 * @描述: 查询配置初始化页面
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-27 下午03:42:51
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
	public ActionForward cxpzInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxpzForm model = (CxpzForm) form;
		CxpzService service = new CxpzService();
		
		List<HashMap<String,String>> cxgnList = service.getCxgnList(model.getGnmc());
		
		if (StringUtils.isNull(model.getGnbz()) && cxgnList.size() > 0){
			model.setGnbz(cxgnList.get(0).get("gnbz"));
		}
		
		
		request.setAttribute("cxgnList", cxgnList);
		return mapping.findForward("cxpzInit");
	}
	
	
	
	/**
	 * 
	 * @描述: 获取查询列相关配置
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-27 下午05:20:33
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
	public ActionForward getColList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxpzForm model = (CxpzForm) form;
		CxpzService service = new CxpzService();
		
		List<HashMap<String,String>> colList = service.getColList(model.getGnbz());
		
		request.setAttribute("colList", colList);
		return mapping.findForward("colList");
	}
	
	
	/**
	 * 
	 * @描述: 更新列信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-29 下午05:13:26
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
	public ActionForward updateColInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpzForm model = (CxpzForm) form;
		CxpzService service = new CxpzService();
		
		service.updateColInfo(model);
		
		return null;
	}
	


	/**
	 * 
	 * @描述: 获取查询配置信息
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-31 下午02:19:32
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
	public ActionForward getCxpzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CxpzForm model = (CxpzForm) form;
		CxpzService service = new CxpzService();
		
		String cxpz = request.getParameter("cxpz");
		
		JSONObject json = service.getCxbzForJson(model.getGnbz(),cxpz);
		response.getWriter().print(json);
		
		return null;
	}
	
	
	public ActionForward ylxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("ylxg");
	}

}
