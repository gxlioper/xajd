package xsgzgl.xsxx.general.dljc;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.XsxxDljcInterface;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 学生信息_登录检测_通用_Init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 偉大的駱
 * @version 1.0
 */

public class XsxxDljcInit {

	/**
	 * 登录配置查询初始化
	 * 
	 * @date 2012-12-17
	 * @author 伟大的骆
	 * 
	 */
	public void initDljcSearch(RequestForm rForm, XsxxGeneralForm myForm,
			User user, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		HttpSession session = request.getSession();

		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);

		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);
		// 访问路径
		String path = "xsxx_dljc.do";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * 检测信息配置初始化
	 * 
	 * @date 2012-12-17
	 * @author 伟大的骆
	 * 
	 */
	public void initDljcSetting(RequestForm rForm, XsxxGeneralForm myForm,
			User user, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		HttpSession session = request.getSession();

		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);

		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);
		// 访问路径
		String path = "xsxx_dljc.do";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * 登录检测信息完善初始化
	 * 
	 * @date 2012-12-19
	 * @author 伟大的骆
	 * 
	 */
	public void initDljcInput(RequestForm rForm, XsxxGeneralForm myForm,
			XsxxDljcModel model, User user, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		HttpSession session = request.getSession();

		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);

		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		myForm.setXxpymc(xxpymc);
		// 访问路径
		String path = "xsxx_xsdl.do";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 是否信息完善
		boolean isXxws = service.isXxws(user.getUserName());
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		model.setXxws(isXxws);
	}
}
