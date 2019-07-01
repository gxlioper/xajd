package xgxt.pjpy.comm.pjpy;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优初始化类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * 
 * @version 1.0
 */

public class PjpyInit {

	/**
	 * 基本设置_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getPjjbszRForm(RequestForm rForm, HttpServletRequest request) {

		// 功能模块
		String gnmk = "pjpy";
		// 评奖基本设置
		String menu = "pjjbsz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "pjpy_jbsz_jbsz.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}
	
	/**
	 * 流程设置_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void getPjlcszRForm(RequestForm rForm, HttpServletRequest request) {

		// 功能模块
		String gnmk = "pjpy";
		// 评奖基本设置
		String menu = "pjlcsz";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "pjpy_jbsz_lcsz.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

	}
}
