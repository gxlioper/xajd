package xgxt.xtwh.xtwhCriterion.yhgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_用户管理_action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class YhglAction extends BasicExtendAction{
	
	/**
	 * 用户维护管理
	 * @param mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhglForm yhglForm = (YhglForm)form;
		
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		
		YhglService service = new YhglService();
		
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			
			String message = service.delYh(pkValues) ? "删除成功！" : "删除失败！";
			request.setAttribute("message", message);
			go = "go";
		}
		
		// 初始密码
		if("init".equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("primarykey_cbv");
			
			// 密码初始化操作
			String message = service.initMm(pkValues) ? "初始化成功！" : "初始化失败！";
			request.setAttribute("message", message);
			go = "go";
		}
		
		// 学生用户初始化
		if("initXs".equalsIgnoreCase(doType)){
			
		}
		
		// 查询操作
		if("go".equalsIgnoreCase(go)){
			request.setAttribute("rs", service.queryYhList(yhglForm));
		}
		
		// 获取jsList
		request.setAttribute("topTr", service.getTopTr("yhgl"));
		request.setAttribute("jsList", service.getRsList("xg_xtwh_jswhb", new HashMap<String, String>(), new String[]{"jsdm", "jsmc"}));
	
		FormModleCommon.requestSetList(new String[] {"bm" }, request);// 自定义(目前：部门代码)
		
		return mapping.findForward("yhwhManage");
	}
	
	/**
	 * 用户增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		// 登陆用户
		String userName = (String)session.getAttribute("userName");
		String doType = request.getParameter("doType");

		YhglForm yhglForm = (YhglForm)form;	
		
		YhglService service = new YhglService();
		
		// 保存用户
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveYh(yhglForm) ? "保存成功！" : "保存失败！";
			
			request.setAttribute("message", message);
		}
		
		// 默认权限List
		request.setAttribute("mrqxList", service.getJsczfwList());
		// 管理角色List
		request.setAttribute("jsList", service.getJsListForUserName(userName));
		
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		return mapping.findForward("yhwhUpdate");
	}
	
	/**
	 * 用户维护查看页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhwhModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		// 操作
		String doType = request.getParameter("doType");
		// 登陆用户名 
		String userName = (String)session.getAttribute("userName");
		// 用户名主键
		String pkValue = request.getParameter("pkValue");
		
		YhglForm myForm = (YhglForm)form;
		
		YhglService service = new YhglService();
		
		// 修改操作
		if("save".equalsIgnoreCase(doType)){
			String message = service.modiYh(myForm) ? "修改成功！" : "修改失败！";
			request.setAttribute("message", message);
		}
		
		// 获得角色列表
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("yhm", pkValue);
		
		List<HashMap<String, String>> jsList = service.getRsList("xg_view_xtwh_yhjs", queryMap, new String[]{"jsdm", "jsmc", "jsqx"});
		// 用户信息 
		Map<String, String> rs = service.getYh(pkValue);
		// 开启状态
		myForm.setKqzt(rs.get("kqzt"));
		
		// 操作
		request.setAttribute("doType", doType);
		// 已有角色列表
		request.setAttribute("yyjsList", jsList);
		// 登陆用户拥有角色
		request.setAttribute("jsList", service.getJsListForUserName(userName));
		// 用户信息
		request.setAttribute("rs", rs);
		// 默认权限List
		request.setAttribute("mrqxList", service.getJsczfwList());
		
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		
		return mapping.findForward("yhwhModi");
	}
	
	/**
	 * 用户角色分配操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhjsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		// 用户名
		String userName = (String)session.getAttribute("userName");
		// 需要角色分配的用户
		
		YhglService service = new YhglService();
		
		// 用户角色List
		// request.setAttribute("yhjsMap", service.getYhJsList(pkValues));
		// 管理角色List
		request.setAttribute("jsList", service.getJsListForUserName(userName));

		return mapping.findForward("yhjsUpdate");
	}
	
	/**
	 * 用户权限分配管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhqxfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		// 登陆用户
		String userName = (String)session.getAttribute("userName");
		// 操作类型
		String doType = request.getParameter("doType");
		YhglService service = new YhglService();
		
		// 获取用户对象
		String yhm = request.getParameter("pkValue");
		
		// 获取用户通过用户名
		if(StringUtils.isNotNull(yhm)){
			request.setAttribute("rs", service.getYh(yhm));
			
			// 获得角色列表
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("yhm", yhm);
			
			List<HashMap<String, String>> jsList = service.getRsList("xg_view_xtwh_yhjs", queryMap, new String[]{"jsdm", "jsmc"});
			request.setAttribute("jsList", jsList);
			request.setAttribute("size", jsList.size());
		}
		
		// 保存添加的权限
		if("save".equalsIgnoreCase(doType)){
			String[] btns = request.getParameterValues("btns");
			String message = service.saveYhqx(userName, yhm, btns) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		// 所有功能模块
		List<GnmkModel> list = service.getAllGnmkList(userName, yhm);
		request.setAttribute("allGnmkList", list);
		
		// 界面高度
		int height = (list.size() * 29 + 36) < 800 ? 800 : (list.size() * 29 + 36); 
		request.setAttribute("height", height);
		
		// 角色性质，角色操作范围List
		request.setAttribute("jslxList", service.getJslxList());
		request.setAttribute("jsczfwList", service.getJsczfwList());
		
		return mapping.findForward("yhqxfpManage");
	}
	
	/**
	 * 用户选择页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getYhInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String go = request.getParameter("go");
		
		// 查询操作
		if ("go".equalsIgnoreCase(go)) {
			YhglForm yhglForm = (YhglForm) form;
			YhglService service = new YhglService();
			
			request.setAttribute("topTr", service.getTopTr("yhqxfp"));
			request.setAttribute("rs", service.queryYhList(yhglForm));
		}
	
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		
		return mapping.findForward("userInfo");
	}
	
	/**
	 * 角色批量授予用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jsBatchManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session  = request.getSession();
		
		// 登陆用户名
		String userName = (String)session.getAttribute("userName");
		
		String go = request.getParameter("go");
		
		YhglService service = new YhglService();
		
		YhglForm myForm = (YhglForm) form;
		
		// 查询操作
		if("go".equalsIgnoreCase(go)){
			request.setAttribute("rs", service.queryJsList(myForm, userName));
		}
		
		request.setAttribute("topTr", service.getTopTr("js"));
		request.setAttribute("jslxList", service.getJslxList());		// 角色类型List
		request.setAttribute("jsczfwList", service.getJsczfwList());	// 角色操作范围List
		
		return mapping.findForward("jsBatchManage");
	}
	
	/**
	 * 角色批量授予用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yhBatchManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhglService service = new YhglService();
		
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		
		// 角色主键数组
		String[] jsIds = request.getParameterValues("jsIds");

		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			String[] yhs = request.getParameterValues("yhs");
			String message = service.saveJsForYh(yhs, jsIds) ? "操作成功！" : "操作失败！";
			
			request.setAttribute("message", message);
			go = "go";
		}
		
		// 查询操作
		if("go".equalsIgnoreCase(go)){	
			YhglForm yhglForm = (YhglForm)form;
			request.setAttribute("rs", service.queryYhList(yhglForm));
		}
		
		request.setAttribute("jsList", service.getJsListForPk(jsIds, new String[]{"jsdm","jsmc","jslxmc","jscmmc","jssm"}));
		FormModleCommon.requestSetList(new String[]{"bm"}, request);
		
		request.setAttribute("topTr", service.getTopTr("yhjsfp"));
		return mapping.findForward("yhBatchManage");
	}
	
	/**
	 * 学生批量授权
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsBatchManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		// 登陆用户名 
		String userName = (String)session.getAttribute("userName");
		String go = request.getParameter("go");
		
		YhglService service = new YhglService();
		
		YhglForm myForm = (YhglForm) form;
		
		// 查询操作
		if("go".equalsIgnoreCase(go)){
			request.setAttribute("rs", service.queryJsList(myForm, userName));
		}
		
		request.setAttribute("topTr", service.getTopTr("js"));
		request.setAttribute("jslxList", service.getJslxList());		// 角色类型List
		request.setAttribute("jsczfwList", service.getJsczfwList());	// 角色操作范围List
		
		return mapping.findForward("xsBatchManage");
	}
	
	/**
	 * 学生批量授权操作页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsBatchUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YhglService service = new YhglService();
		// 操作类型
		String doType = request.getParameter("doType");
		// 角色主键列表
		String[] jsIds = request.getParameterValues("jsIds");
		String go = request.getParameter("go");
		
		// 保存操作
		if("save".equalsIgnoreCase(doType)){
			YhglForm model = (YhglForm) form; 
			// 需要保存的学生类型
			String[] xss = request.getParameterValues("xss");
			String message = service.saveJsForXs(xss, jsIds, model.getKssj(), model.getJssj()) ? "操作成功！" : "操作失败！";
			
			request.setAttribute("message", message);
			go = "go";
		}
		
		// 查询操作
		if("go".equalsIgnoreCase(go)){	
			YhglForm yhglForm = (YhglForm)form;
			
			request.setAttribute("rs", service.queryXslxList(yhglForm));
		}
		
		// 获取角色说明
		request.setAttribute("jsList", service.getJsListForPk(jsIds, new String[]{"jsdm","jsmc","jslxmc","jscmmc","jssm"}));
		request.setAttribute("xslxlbList", service.getRsList("xg_xtwh_xslxlbb", new HashMap<String, String>(), new String[]{"xslxlbdm", "xslxlbmc"}));
		
		// 获取学生批量授权topTr
		request.setAttribute("topTr", service.getTopTr("xsBatch"));
		return mapping.findForward("xsBatchUpdate");
	}
	
	/**
	 * 学生单个用户授权
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsqxfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 操作类型
		String doType = request.getParameter("doType");
		// 用户管类service
		YhglService service = new YhglService();
		// 获得登陆用户对象
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		// 用户管理对象
//		YhglForm model = (YhglForm) form;
		// 学号
		String xh = request.getParameter("xh");
		
		if("save".equalsIgnoreCase(doType)){
//			String[] btns = request.getParameterValues("btns");
		}
		
		// 学号部位null获取学生对象
		if(StringUtils.isNotNull(xh)){
		}
		
		request.setAttribute("gnmkList", service.getOneGnmkList(userName, 1));
		
		return mapping.findForward("xsqxfpManage");
	}
}
