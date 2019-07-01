/**
 * @部门:学工产品事业部
 * @日期：2016-7-25 上午08:51:27 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.comm;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.jyglnew.jygl.cyyqgl.CyyqglForm;
import com.zfsoft.xgxt.jyglnew.jygl.cyyqgl.CyyqglService;
import com.zfsoft.xgxt.xsztz.tttzxm.sq.TttzxmForm;
import com.zfsoft.xgxt.xsztz.tttzxm.sq.TttzxmService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-25 上午08:51:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CommTtxmAction extends SuperAction<TttzxmForm, CommTtxmService> {
	 CommTtxmService service = new CommTtxmService();
	/**
	 * @throws Exception 
	 * 
	 * @描述:项目选择查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-25 上午09:09:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getXmSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		TttzxmForm model = (TttzxmForm) form;
			if (QUERY.equalsIgnoreCase(model.getType())) {
				// 生成高级查询对象
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				model.setSearchModel(searchModel);
				User user = getUser(request);
				// 查询
				List<HashMap<String, String>> resultList = service.getPageList(model, user);
				
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			// 默认高级查询条件
			SearchModel searchModel = new SearchModel();
			request.setAttribute("searchTj", searchModel);
			String path = "ttxm_comm.do?method=getXmSelect";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("ttxmxz");
		
	}
	
	/**
	 * 
	 * @描述: 学生申请输入学号录入成员
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-25 下午05:41:56
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
	public ActionForward EnterXh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh = request.getParameter("xh");
		String xmdm = request.getParameter("xmdm");
		String xhstr = request.getParameter("xhs");
		String[] xhs = null;
		if(StringUtils.isNotNull("xhstr")){
			xhs = xhstr.split(",");
		}
		//获取文本框内学号的学生信息
		HashMap<String, String> xsmap = service.getXsxx(xh,xmdm,xhs);
		JSONObject jsonObj = JSONObject.fromObject(xsmap);
		response.getWriter().print(jsonObj);
		return null;
	}
	
	/**
	 * 
	 * @描述: 教师录入成员信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-25 下午05:42:49
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
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmForm myForm = (TttzxmForm) form;
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "ttxm_comm.do?method=getStu";
		request.setAttribute("path", path);
		request.setAttribute("xmdm", myForm.getXmdm());
		request.setAttribute("xhs", myForm.getXhs());
		return mapping.findForward("getStuSelect");
	}
	
}
