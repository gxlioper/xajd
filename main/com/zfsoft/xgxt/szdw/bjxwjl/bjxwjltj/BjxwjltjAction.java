/**
 * @部门:学工产品事业部
 * @日期：2014-5-16 下午01:40:39 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjltj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-16 下午01:40:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjxwjltjAction extends SuperAction {

	/**
	 * service
	 */
	private static BjxwjltjService service = new BjxwjltjService();
	
	/**
	 * path 路径
	 */
	private static final String PATH = "szdw_bjxwjltj.do?method=bjxwjltjManage";
	
	private static final String url = "szdw_bjxwjltj.do?method=bjxwjltjManage";
	
	/**
	 * 班级信息记录维护管理
	 */
	@SystemAuth(url = url)
	public ActionForward bjxwjltjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjxwjltjManage");
	}
	
	/**
	 * 
	 * @描述:检索数据列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-4-23 上午11:43:04
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
	@SystemAuth(url = url)
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjltjForm model = (BjxwjltjForm) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述 : 查看详情
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-19 下午04:29:09
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
	@SystemAuth(url = url)
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjltjForm model = (BjxwjltjForm) form;
		HashMap<String , String> bjxx = service.getbjxx(model.getBjdm());
		List<HashMap<String , String>> gbxx = new DwwhService().getZwListByBjdm(model.getBjdm());
		request.setAttribute("fdyjlxx", service.getXwxx(model.getBjdm(), "1")); //'1' 代表辅导员记录信息
		request.setAttribute("bzrjlxx", service.getXwxx(model.getBjdm(), "2"));	//'2' 代表班主任记录信息 
		request.setAttribute("bjxx", bjxx);
		request.setAttribute("gbxx", gbxx);
		return mapping.findForward("tjck");
	}
	
}
