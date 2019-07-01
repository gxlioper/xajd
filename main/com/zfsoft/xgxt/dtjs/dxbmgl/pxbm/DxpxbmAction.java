package com.zfsoft.xgxt.dtjs.dxbmgl.pxbm;

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
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.dtjs.dxbmgl.pxgl.DxpxglForm;
import com.zfsoft.xgxt.dtjs.dxbmgl.pxgl.DxpxglService;

/** 
 * @功能描述：党校培训报名action
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午03:21:51 
 */
public class DxpxbmAction extends SuperAction<DxpxbmForm, DxpxbmService> {
	/** 
	 * @功能描述：跳转查询页面
	 * @author：杨珩 【1346】
	 * @date：2017-11-6 上午11:52:52 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxpxbmCx(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxbmService service = new DxpxbmService();
		CommService cs = new CommService();
		DxpxbmForm myForm = (DxpxbmForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("dtjs_dxbmgl_dxpxbmCx.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			myForm.setXh(user.getUserName());
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "dtjs_dxbmgl_dxpxbmCx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dxpxbmCx");
	}
	
	/** 
	 * @功能描述：查看页面
	 * @author：杨珩 【1346】
	 * @date：2017-11-10 下午03:00:48 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxpxbmCk(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxglService service = new DxpxglService();
		DxpxbmForm myForm = (DxpxbmForm) form;
		User user = getUser(request);
		DxpxglForm dxpxglForm=new DxpxglForm();
		dxpxglForm.setId(myForm.getId());
		DxpxglForm model = service.getModel(dxpxglForm);
		model.setFbrxm(user.getUserName());
		request.setAttribute("dxpxglModel", model);
		return mapping.findForward("dxpxbmCk");
	}
	/** 
	 * @功能描述：报名  排除已经报名的
	 * @author：杨珩 【1346】
	 * @date：2017-11-6 上午11:53:48 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxpxbmBm(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxbmService service = new DxpxbmService();
		DxpxbmForm myForm = (DxpxbmForm) form;
		User user = getUser(request);
		myForm.setXh(user.getUserName());
		boolean flag=service.updateBm(myForm);
		response.getWriter().print(flag);
		return null;
	}
}
