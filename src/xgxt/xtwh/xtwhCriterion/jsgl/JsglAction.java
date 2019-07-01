package xgxt.xtwh.xtwhCriterion.jsgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.xtwhCriterion.QxwhForm;
import xgxt.xtwh.xtwhCriterion.RequestModel;
import xgxt.xtwh.xtwhCriterion.RsModel;
import xgxt.xtwh.xtwhCriterion.entities.JsEntity;
import xgxt.xtwh.xtwhCriterion.yhgl.GnmkModel;

/**
 * @author luning 
 * @describe 因为系统维护的特殊性，没有继承通用的baseAction
 */
public class JsglAction extends DispatchAction{
	
/**
 * @author luning
 * @describe 角色维护
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward jswh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JsglService jsglservice = new JsglService();
		//对request的封装
		QxwhForm myForm = (QxwhForm) form;
		JsglRequestModel model = new JsglRequestModel();
		JsglQueryModel queryModel = new JsglQueryModel();
		BeanUtils.copyProperties(queryModel, myForm);
		model.setJsglQueryModel(queryModel);
		
		model = jsglservice.setJsModel(model);
		
		//获取查询结果
		RsModel rsModel = jsglservice.getJsList(model);
		
		//结果集处理及加载
		setRequset(model,rsModel,request);
		
		//转跳路径
		return mapping.findForward("jswhManage");
	}
		
/**
 * @author luning
 * @describe 单个角色维护保存
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward jswhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JsglService jsglservice = new JsglService();
		//对request的封装
		QxwhForm myForm = (QxwhForm) form;
		
		JsglRequestModel model = new JsglRequestModel();
		
		BeanUtils.copyProperties(model, myForm);
		
		model = jsglservice.setJsModel(model);
		
		jsglservice.jswhUpdate(model,request);
		
		
		//================= 页面显示信息==================
		HashMap<String, String> rs = jsglservice.getJswh(model);
		// =================end ===================
		//结果集处理及加载
		request.setAttribute("rs",rs);
		FormModleCommon.requestSetList(new String[]{"jslx","jsczfw"}, request);
		
		//转跳路径
		return mapping.findForward("jswhUpdate");
	}
	
/**
 * @author luning
 * @describe 单个角色删除
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public ActionForward jswhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JsglService jsglservice = new JsglService();
		//对request的封装
		QxwhForm myForm = (QxwhForm) form;
		
		JsglRequestModel model = new JsglRequestModel();
		
		BeanUtils.copyProperties(model, myForm);
		
		model = jsglservice.setJsModel(model);
		
		Boolean yesNo = jsglservice.delJswh(model,request);
		
		jsglservice.setMessage(request, yesNo,"del");
		
		return jswh(mapping,form,request,response);
	}



/**
 * @author luning
 * @describe 角色维护页面加载通用方法
 * @param model
 * @param rsModel
 * @param request
 */
	private void setRequset(RequestModel model, RsModel rsModel, HttpServletRequest request) {
		request.setAttribute("tableName", model.getTableName());
		request.setAttribute("realTable", model.getRealTable());
		
		request.setAttribute("rs",rsModel.getRsList());
		List topTr = SearchUtils.getTopTr(model.getTableName(),model.getColList(), null);
		request.setAttribute("topTr",topTr);
		try {
			FormModleCommon.requestSetList(new String[]{"jslx","jsczfw","yesNo"}, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/**
 * @author luning
 * @describe 角色授权页面
 * @param model
 * @param rsModel
 * @param request
 */
	public ActionForward jssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//操作类型
		String doType = request.getParameter("doType");
		JsglService jsglservice = new JsglService();
		
		String jsmc = request.getParameter("jsmc");
		
		QxwhForm myForm = (QxwhForm) form;
		request.setAttribute("rs", jsglservice.getEntitry(JsEntity.class, jsmc));
		
		// 保存添加的权限
//		if("save".equalsIgnoreCase(doType)){
//			String[] btns = request.getParameterValues("btns");
//			String message = service.saveYhqx(user.getUserName(), jsmc, btns) ? "保存成功！" : "保存失败！";
//			request.setAttribute("message", message);
//		}
		request.setAttribute("fpdxList", jsglservice.getFpdxList());
		request.setAttribute("gnmkList", jsglservice.getOneGnmkList());
		
		return mapping.findForward("jssqManage");
	}
	
	public ActionForward getJsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JsglService jsglservice = new JsglService();
		//对request的封装
		QxwhForm myForm = (QxwhForm) form;
		JsglRequestModel model = new JsglRequestModel();
		JsglQueryModel queryModel = new JsglQueryModel();
		BeanUtils.copyProperties(queryModel, myForm);
		//查询出的角色在该模块必须是启用的角色
		queryModel.setSfqy("是");
		
		model.setJsglQueryModel(queryModel);
		
		model = jsglservice.setJsQueryModel(model);

		//获取查询结果
		RsModel rsModel = jsglservice.getJsList(model);
		
		//结果集处理及加载
		setRequset(model,rsModel,request);
		
		//转跳路径
		return mapping.findForward("roleInfo");
	}
	
	/**
	 * 角色授权页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jssqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");

		JsglService service = new JsglService();
		
		// 保存角色权限
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveJsqx(pkValue, request.getParameterValues("btns")) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		// 所有功能模块
		List<GnmkModel> list = service.getAllGnmkList(pkValue);
		request.setAttribute("allGnmkList", list);
		
		// 角色信息
		request.setAttribute("rs",service.getJsInfo(pkValue));
		// 界面高度
		int height = (list.size() * 29 + 36) < 800 ? 800 : (list.size() * 29 + 36); 
		request.setAttribute("height", height);
		
		return mapping.findForward("jssqManage");
	}
	
}
