package xgxt.comm.xginfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.SearchUtils;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 相关信息通用-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class CommXgInfoAction extends BasicAction {
	
	/**
	 * 学生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommXgInfoService service = new CommXgInfoService();
		CommForm myForm = (CommForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 用户名
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// 用户所在部门
		String userDep = (String) session.getAttribute("userDep");
		// 视图
		String tableName = request.getParameter("tableName");
		// 项目类型
		String lx = request.getParameter("lx");
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 提示信息
		String message = "";
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 是否学院
		boolean isxy = false;
		// 表头
		List<HashMap<String, String>> topTr = null;
		// 结果列表
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// =================end==================

		// ==================登陆用户检测 ==================

		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// 学院用户
			myForm.setXydm(userDep);
			isxy = true;
		}

		// 登陆用户类型
		String userLx = "";

		if (bzrQx && fdyQx) {// 班主任兼辅导员
			userLx = "jd";
		} else if (fdyQx) {// 辅导员
			userLx = "fdy";
		} else if (bzrQx) {// 班主任
			userLx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// 学院
			userLx = "xy";
			myForm.setXydm(userDep);
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// 学校用户（管理员）
			userLx = "xx";
		}

		myForm.setUserType(userLx);

		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {

			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc" };
			
			myForm.setTableName(tableName);
			
			// 表头
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			// 结果
			rsArrList = service.getXsxxList(myForm, colList);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "isxy","lx" };
		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(isxy),lx };

		rForm.setTableName(tableName);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setMklx(mklx);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		rForm.setGnmk("gygl");
		service.setList(myForm, rForm, request);
		// =================end ===================

		return mapping.findForward("commImp");
	}
	
	/**
	 * 选择学院
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward choiceXy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommForm commonForm = (CommForm)form;
		CommXgInfoModel model = new CommXgInfoModel();
		
		BeanUtils.copyProperties(model, commonForm);
		
		CommXgInfoService service = new CommXgInfoService();
		
		List<String[]> rs = service.getXyList(model);

		// 查询所有满足条件的学院
		request.setAttribute("rs", rs);
		
		// 重新设置页面分页,主要是总共条目数
		commonForm.setPages(model.getPages());
		
		return mapping.findForward("choiceXy");
	}
	
	/**
	 * 选择专业
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward choiceZy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommForm commonForm = (CommForm)form;
		CommXgInfoModel model = new CommXgInfoModel();
		
		BeanUtils.copyProperties(model, commonForm);
		
		CommXgInfoService service = new CommXgInfoService();
		List<String[]>  rs = service.getZyList(model);
		
		request.setAttribute("xymc", commonForm.getXymc());
		request.setAttribute("xydm", commonForm.getXydm());
		request.setAttribute("rs", rs);
		return mapping.findForward("choiceZy");
	}
	
	/**
	 * 选择班级
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward choiceBj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommForm commonForm = (CommForm)form;
		CommXgInfoModel model = new CommXgInfoModel();
		
		BeanUtils.copyProperties(model, commonForm);
		
		CommXgInfoService service = new CommXgInfoService();
		model.setUser(getUser(request));
		List<String[]>  rs = service.getBjList(model);
		
		request.setAttribute("xymc", request.getParameter("xymc"));
		request.setAttribute("zymc", request.getParameter("zymc"));
		request.setAttribute("zydm", request.getParameter("zydm"));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("rs", rs);
		return mapping.findForward("choiceBj");
	}
	
	/**
	 * 选择班级（根据年级，学院，专业查询）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	public ActionForward xzBj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommForm commonForm = (CommForm)form;
		User user = getUser(request);// 用户对象
		CommXgInfoModel model = new CommXgInfoModel();
		if ("xy".equalsIgnoreCase(user.getUserType())) {
			commonForm.setXydm(user.getUserDep());
		}
		model.setUser(user);
		RequestForm rForm = new RequestForm();
		BeanUtils.copyProperties(model, commonForm);
		CommXgInfoService service = new CommXgInfoService();
		String zdpzstr = request.getParameter("zdpzstr");
		service.setRequestValue(rForm, user, request);
		List<String[]>  rs = service.getBjList(model);
		request.setAttribute("rs", rs);
		request.setAttribute("zdpzstr", zdpzstr);
		String isZybjXz= request.getParameter("isZybjXz");
		request.setAttribute("isZybjXz", isZybjXz);
		service.setAllList(commonForm, rForm, request);
		return mapping.findForward("xzBj");
	}
	
	/**
	 * 选择流程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward choiceLc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommForm commonForm = (CommForm)form;
		CommXgInfoModel model = new CommXgInfoModel();
		//模块信息
		String mkid = request.getParameter("mkid");
		
		BeanUtils.copyProperties(model, commonForm);
		
		CommXgInfoService service = new CommXgInfoService();
		List<String[]>  rs = service.getLcList(model,mkid);
		
		request.setAttribute("rs", rs);
		request.setAttribute("cyfsList", service.getCyfsList());
		return mapping.findForward("choiceLc");
	}
	
}