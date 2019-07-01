package xsgzgl.jygl.general.sxjy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.jygl.general.JyglGeneralForm;
import xsgzgl.jygl.general.JyglGeneralService;
import xsgzgl.jygl.general.inter.JyglSxjyInterface;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjJgcxInterface;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 就业管理_实习就业_通用_Init类
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

public class JyglSxjyInit extends BasicInit {

	/**
	 * 【实习就业 - 管理】
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initSxjyManage(RequestForm rForm, JyglGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		JyglGeneralService myService = new JyglGeneralService();
		JyglSxjyInterface service = myService.getJyglSxjyService(myForm);
		JyglSxjyModel model = new JyglSxjyModel();

		// 访问路径
		String path = "jygl_general_sxjy.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 视图
		String tableName = model.getSearch_table();
		// 表
		String realTable = model.getSave_table();
		// 用户身份
		String userStatus = user.getUserStatus();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] { "userStatus" };
		// 其他字段值
		String[] qtzdz = new String[] { userStatus };

		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * 【实习就业 - 维护】
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initSxjyUpdate(RequestForm rForm, JyglGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		JyglGeneralService myService = new JyglGeneralService();
		JyglSxjyInterface service = myService.getJyglSxjyService(myForm);
		JyglSxjyModel model = new JyglSxjyModel();
		
		// 访问路径
		String path = "jygl_general_sxjy.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] { "ryfw" };
		// 其他字段值
		String[] qtzdz = new String[] { "view_xsjbxx" };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================初始化页面相关数据=====================

		// 详细信息
		HashMap<String, String> map = new HashMap<String, String>();
		
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			// 主键
			String pkValue = request.getParameter("pkValue");
			model.setPkValue(new String[] { pkValue });
			map = service.getSxjyMap(model);
		}

		request.setAttribute("rs", map);
	}
}
