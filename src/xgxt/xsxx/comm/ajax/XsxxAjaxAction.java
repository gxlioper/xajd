package xgxt.xsxx.comm.ajax;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_Ajax_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxAjaxAction extends BasicAction{

	/**
	 * 获得学生家庭情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQtxxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xh = request.getParameter("xh");
		String mklx = request.getParameter("mklx");
		
		XsxxAjaxService service = new XsxxAjaxService();

		List<HashMap<String, String>> list = service.getQtxxInfo(mklx,xh);
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return null;
	}
	
	/**
	 * 设置session的值
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setSessionValue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = request.getParameter("path");
		
		HttpSession session = request.getSession();
		session.setAttribute("clickPath", path);
		
		return null;
	}
	
	/**
	 * 加载部门下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setBmOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String bmjb = request.getParameter("bmjb");
		String bmmc = request.getParameter(bmjb);
			
		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		
		if (!Base.isNull(bmmc)){
			bmmc = URLDecoder.decode(bmmc, "UTF-8");
		}
		
		XsxxAjaxService service = new XsxxAjaxService();
			
		XsxxAjaxModel model = new XsxxAjaxModel();
		model.setBmjb(bmjb);
		model.setBmmc(bmmc);
		model.setNj(nj);
		model.setXydm(xydm);
		model.setZydm(zydm);
		model.setBjdm(bjdm);
		
		User user = getUser(request);// 用户对象
		
		List<HashMap<String, String>> map = service.getBmOption(model,user);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * 判断是否存在
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkIsExists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxAjaxModel model = new XsxxAjaxModel();
		XsxxAjaxService service = new XsxxAjaxService();
		// 过滤代码
		String dm = request.getParameter("dm");
		// 主键
		String[] pk = request.getParameter("pk").split("!!@@!!");
		// 主键值
		String pkValue = service.unicode2Gbk(request.getParameter("pkValue"));
		// 表名
		String tableName = request.getParameter("tableName");
		// 出错信息
		String errMsg = service.unicode2Gbk(request.getParameter("errMsg"));

		model.setDm(dm);
		model.setPk(pk);
		model.setPkValue(pkValue);
		model.setTableName(tableName);
		model.setErrMsg(errMsg);

		// 判断是否存在
		boolean flag = service.checkIsExists(model);

		// 提示信息
		String message = "";

		if (!flag) {
			message = errMsg;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 检测用户名
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkUserName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxAjaxService service = new XsxxAjaxService();
		// 用户名
		String userName = request.getParameter("userName");

		// 用户类型
		String userType = service.checkUserName(userName);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(userType);

		return null;
	}
	

	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jdqkInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxAjaxService service = new XsxxAjaxService();
		// 用户名
		String xh = request.getParameter("xh");

		// 用户类型
		HashMap<String,String>jdqkMap = service.getJdqkInfo(xh);

		response.setContentType("text/html;charset=gbk"); 
		
		response.getWriter().print(JSONArray.fromObject(jdqkMap));

		return null;
	}

	/**
	 * 创建学生成绩HTML
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createXscjHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxAjaxService service = new XsxxAjaxService();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		String xh = request.getParameter("xh");
		// =================== end ===================

		// ==================构建前台页面====================
		service.createXscjHtml(xh, user, response);
		// ==================构建前台页面 end================

		return null;
	}

}
