package xsgzgl.qgzx.xsgw;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cssz.QgzxCsszService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-23 下午14:19:22
 * </p>
 */

public class QgzxXsgwcxAction extends BasicAction{
	
	/**
	 * 查询我的勤工岗位首页面数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wdqggwCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxXsgwcxService service = new QgzxXsgwcxService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_xsqg_xsqggw.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		if(!"stu".equalsIgnoreCase(userType)){
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("xsgwCx");
		}
	}
	
	/**
	 * 查看我的勤工岗位首页面一条数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wdqggwCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxXsgwcxService service = new QgzxXsgwcxService();
		QgzxXsgwcxForm myForm = (QgzxXsgwcxForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String pkValue = request.getParameter("pkValue");
		myForm.setGuid(pkValue);
		// 单条记录信息
		HashMap<String, String> rs = service.getXsgwCkmxMap(myForm,userName);
		ArrayList<String[]> cjrs = (ArrayList<String[]>) service.getXsgwCkcjmxMap(myForm,userName);
		HashMap<String, String> cssz = new QgzxCsszService().getCssz();
		
		request.setAttribute("cjbz", cssz.get("cjbz"));
		request.setAttribute("rs", rs);
		request.setAttribute("cjrs", cjrs);
		request.setAttribute("path", "qgzx_xsqg_xsqggw.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsgwCkmx");
	}
	
	/**
	 * 查询我的勤工岗位首页面数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxXsgwcxService service = new QgzxXsgwcxService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("qgzx_xsqg_xsgwcx.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		String userType=user.getUserType();
		if(!"stu".equalsIgnoreCase(userType)){
		String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else{
			return mapping.findForward("gwxxCx");
		}
	}
	
	/**
	 * 岗位信息查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxXsgwcxService service = new QgzxXsgwcxService();
		QgzxXsgwcxForm model = (QgzxXsgwcxForm) form;
		RequestForm rForm = new RequestForm(); 
		HashMap<String,String> rs = service.gwxxCk(model);
		
		//982
		//酬金上限
		QgzxCsszService qgzxCsszService = new QgzxCsszService(); 
		HashMap<String, String> map=qgzxCsszService.getCssz();
		String isshow="false";
		String sfsdgwcjsx=map.get("sfsdgwcjsx");
		//如果设置了酬金上限
		if("yes".equals(sfsdgwcjsx)){
			isshow="true";
			//如果当前岗位未设置酬金上限，则显示基本配置的酬金上限
			if(StringUtils.isNull(rs.get("gwcjsx"))){
				rs.put("gwcjsx",map.get("gwzgcjsx"));
			}
		}
		request.setAttribute("isshow", isshow);
		
		request.setAttribute("rs", rs);
		User user = getUser(request);
		rForm.setPath("qgzx_xsqg_xsgwcx.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("gwxxCk");
	}
}