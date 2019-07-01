package com.zfsoft.xgxt.dtjs.dxbmgl.pxgl;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @功能描述：党校培训管理action
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午02:56:34 
 */
public class DxpxglAction extends SuperAction<DxpxglForm, DxpxglService> {
	/** 
	 * @功能描述：跳转查询页面
	 * @author：杨珩 【1346】
	 * @date：2017-11-3 下午04:20:04 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxpxglCx(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxglService service = new DxpxglService();
		CommService cs = new CommService();
		DxpxglForm myForm = (DxpxglForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("dtjs_dxbmgl_dxpxglCx.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "dtjs_dxbmgl_dxpxglCx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dxpxglCx");
	}
	
	/** 
	 * @功能描述：跳转和保存培训信息
	 * @author：杨珩 【1346】
	 * @date：2017-11-4 下午01:52:28 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxpxglZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxpxglService service = new DxpxglService();
		DxpxglForm myForm = (DxpxglForm)form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			myForm.setFbr(user.getUserName());
			boolean result = service.save(myForm);
			response.getWriter().print(result);
			return null;
		}
		request.setAttribute("username", user.getRealName());
		return mapping.findForward("dxpxglZj");
	}
	/** 
	 * @功能描述：修改培训信息
	 * @author：杨珩 【1346】
	 * @date：2017-11-4 下午02:59:01 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxpxglXg(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxglService service = new DxpxglService();
		DxpxglForm myForm = (DxpxglForm)form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			myForm.setFbr(user.getRealName());
			boolean result = service.update(myForm);
			response.getWriter().print(result);
			return null;
		}
		DxpxglForm model = service.getModel(myForm);
		model.setFbrxm(user.getUserName());
		request.setAttribute("dxpxglModel", model);
		return mapping.findForward("dxpxglXg");
	}
	/** 
	 * @功能描述：设置百分比
	 * @author：杨珩 【1346】
	 * @date：2017-11-4 下午02:59:57 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxpxglSz(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxglService service = new DxpxglService();
		DxpxglForm myForm = (DxpxglForm)form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.updatebfb(myForm);
			response.getWriter().print(result);
			return null;
		}
		DxpxglForm model = service.getModel(myForm);
		request.setAttribute("dxpxglModel", model);
		return mapping.findForward("dxpxglSz");
	}
	
	/** 
	 * @功能描述：删除培训信息
	 * @author：杨珩 【1346】
	 * @date：2017-11-6 上午09:23:02 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxpxglSc(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		DxpxglService service = new DxpxglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int mess = service.del(values.split(","));
			response.getWriter().print(mess);
		} else {
			response.getWriter().print("0");
		}
		return null;
	}
}
