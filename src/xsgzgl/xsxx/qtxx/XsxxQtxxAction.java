package xsgzgl.xsxx.qtxx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

public class XsxxQtxxAction extends BasicAction{
	
	XsxxQtxxService service =new XsxxQtxxService();
	
	XsxxQtxxInit init =new XsxxQtxxInit();
	
	/**
	 * 查询模型 以高级查询为查询条件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsqtxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		XsxxQtxxForm myForm=(XsxxQtxxForm)form;
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		String xmdm=request.getParameter("xmdm");
		
		//将form值拷贝到model中
		/** 此操作的用户在于查询时只传入 BasicModel 在查询时需要分页所以将
		 * Pages对象拷贝到BasicModel
		 * 此demo的查询是以高级查询为例，所以也需要拷贝 SearchModel
		 * 处了BasicModel 和 SearchModel 外还提供了 USER对象的拷贝 用于权限控制
		 */
		BeanUtils.copyProperties(model, myForm);
		
		//功能模块
		model.setGnmk("xsxx");
		//模块路径
		model.setPath("xsxx_qtxx.do?method=xsqtxxManage&xmdm=001");/** 高级查询用到此属性 以及jsp页面上得title和writeAble*/

		init.initXsqtxx(rForm, myForm,user, request);
		
		//高级查询
		SearchModel searchModel=model.getSearchModel();
//		searchModel.setPath(model.getPath());
		request.setAttribute("searchTj", searchModel);/** 高级查询值回填 */
		
		service.setRequestValue(rForm, user, request);
		
		request.setAttribute("path", model.getPath());
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("xmdm", xmdm);
		
		request.setAttribute("tableName", "xg_view_xsxx_xsqtxx");
		
		request.setAttribute("realTable", "xg_xsxx_qtxxb");
		// =================== end ===================
	
		return mapping.findForward("xsqtxxManage");
	}
	
	/**
	 * 查询模型 以高级查询为查询条件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsqtxxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		XsxxQtxxForm myForm=(XsxxQtxxForm)form;
		
		User user=getUser(request);
		
		BasicModel model=new BasicModel();
		
		String doType=request.getParameter("doType");
		
		String xh=request.getParameter("xh");
		
		String xmdm=request.getParameter("xmdm");
		
		if(Base.isNull(xmdm)){
			
			xmdm=request.getParameter("va");
		}
		
		myForm.setXh(xh);
		
		myForm.setXmdm(xmdm);
		
		HashMap<String,String>rs=service.getQtxxDetail(myForm);
		
		//将form值拷贝到model中
		/** 此操作的用户在于查询时只传入 BasicModel 在查询时需要分页所以将
		 * Pages对象拷贝到BasicModel
		 * 此demo的查询是以高级查询为例，所以也需要拷贝 SearchModel
		 * 处了BasicModel 和 SearchModel 外还提供了 USER对象的拷贝 用于权限控制
		 */
		BeanUtils.copyProperties(model, myForm);
		
		//功能模块
		model.setGnmk("xsxx");
		//模块路径
		model.setPath("xsxx_qtxx.do?method=xsqtxxManage&xmdm=001");

		//init.initBzrpy(rForm, model, request);
		
		//高级查询
		request.setAttribute("searchTj", model.getSearchModel());/** 高级查询值回填 */
		
		service.setRequestValue(rForm, user, request);
		
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("rs", rs);
		request.setAttribute("xmdm", xmdm);
		
		request.setAttribute("doType", doType);
		
		request.setAttribute("path", model.getPath());
		
		FormModleCommon.commonRequestSet(request);
		// =================== end ===================
	
		return mapping.findForward("xsqtxxDetail");
	}
	
	
}
