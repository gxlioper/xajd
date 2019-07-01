package xsgzgl.pjpy.general.xmsz.pjtj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.xmsz.XmszPjtjInterface;
import xsgzgl.pjpy.general.inter.xmsz.XmszXmjdInterface;
import xsgzgl.pjpy.general.xmsz.xmjd.XmszXmjdInit;
import xsgzgl.pjpy.general.xmsz.xmjd.XmszXmjdModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_评奖条件_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XmszPjtjAction extends BasicAction {

	/**
	 * 初始化评奖条件设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultPjtjSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszPjtjInit init = new XmszPjtjInit();
		XmszPjtjModel model = new XmszPjtjModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============	
		//项目代码
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		
		init.initPjtj(rForm, myForm, user, request);
		XmszPjtjInterface service = myService.getXmszPjtjService(myForm);
		
		// 评奖条件列表
		List<HashMap<String, String>> pjtjList = (List<HashMap<String, String>>) request
				.getAttribute("pjtjList");
		model.setPjtjList(pjtjList);
		
		//班级大类列表
		List<HashMap<String, String>> bjdlList = (List<HashMap<String, String>>) request
		.getAttribute("bjdlList");
		model.setBjdlList(bjdlList);
		
		// =================== end ===================

		// ==================构建前台页面====================
		service.defaultPjtjSetting(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 初始化评奖项目设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultPjtjInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszPjtjInit init = new XmszPjtjInit();
		XmszPjtjModel model = new XmszPjtjModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjtj(rForm, myForm, user, request);
		XmszPjtjInterface service = myService.getXmszPjtjService(myForm);
		
		//条件代码
		String tjdm = request.getParameter("tjdm");
		model.setTjdm(tjdm);
		// =================== end ===================

		// ==================获得条件信息====================
		HashMap<String, String> map = service.getPjtjInfo(model, user);
		// 特殊格式
		String tsgs = map.get("tsgs");
		tsgs = Base.isNull(tsgs) ? "" : tsgs;
		// 条件值
		String tjz = map.get("tjz");
		tjz = Base.isNull(tjz) ? "" : tjz;
		
		String message = tsgs + "!!luojw!!" + tjz + "!!luojw!!";
		// ==================获得条件信息 end================
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * 检验评奖条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkPjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmszPjtjService service = new XmszPjtjService();
		XmszPjtjModel model = new XmszPjtjModel();

		User user = getUser(request);

		// 将request中的值封装成model
		service.getModelValue(model, request);

		// 判断评奖条件
		service.getPjtjMessage(model, user);

		return null;
	}

	/**
	 * 保存评奖条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePjtj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszPjtjInit init = new XmszPjtjInit();
		XmszPjtjModel model = new XmszPjtjModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initPjtj(rForm, myForm, user, request);
		XmszPjtjInterface service = myService.getXmszPjtjService(myForm);
		myService.getModelValue(model, request);		
		// ============= end ============

		// ============= 保存评奖条件 ============
		boolean flag = service.savePjtj(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 清空评奖条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deletePjtj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszPjtjInit init = new XmszPjtjInit();
		XmszPjtjModel model = new XmszPjtjModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initPjtj(rForm, myForm, user, request);
		XmszPjtjInterface service = myService.getXmszPjtjService(myForm);
		myService.getModelValue(model, request);		
		// ============= end ============

		// ============= 保存评奖条件 ============
		boolean flag = service.deletePjtj(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
