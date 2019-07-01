package xsgzgl.xsxx.general.xjyd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.dtjs.general.DtjsGeneralForm;
import xsgzgl.dtjs.general.DtjsGeneralService;
import xsgzgl.dtjs.general.inter.DtjsTyjfInterface;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.XsxxXjydInterface;
import xsgzgl.xsxx.general.inter.XsxxZxxsInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_学籍异动_通用_Init类
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

public class XsxxXjydInit {

	/**
	 * 初始化数据【学籍异动】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initXjyd(RequestForm rForm, XsxxGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxXjydInterface service = myService.getXsxxXjydService(myForm);

		// 访问路径
		String path = "xsxx_xjyd.do";
		String tableName = "view_xsxx_xjyd";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = (Base.isNull(myForm.getXxpymc()))?GlobalsValue.getXxpymc(xxdm):myForm.getXxpymc();
		myForm.setXxpymc(xxpymc);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};
		rForm.setTableName(tableName);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
}
