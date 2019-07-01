package xsgzgl.customForm.demo;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.form.User;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 系統維護_自定義表單_DEMO_Init类
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

public class DemoFormInit {
		
	/**
	 * 初始化数据【自定義表單管理】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initDemoFormManage(RequestForm rForm, DemoFormForm myForm,
			User user, HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "customForm.do";

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
	 * 初始化数据【顯示字段設置】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initXszdSetup(RequestForm rForm, DemoFormForm myForm,
			User user, HttpServletRequest request) {

		DemoFormService service = new DemoFormService();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "customForm.do";
		// FormID
		String form_id = request.getParameter("form_id");
		// 所屬表
		String ssb = request.getParameter("ssb");
		// 顯示順序
		String xssx = "1";
		// 其他字段
		String[] qtzd = new String[] { "ssb", "form_id", "xssx" };
		// 其他字段值
		String[] qtzdz = new String[] { ssb, form_id, xssx };

		myForm.setForm_id(form_id);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// =================初始化其他信息==============================
		// 所屬表字段列表
		List<HashMap<String, String>> zdList = service.getZdList(ssb);
		request.setAttribute("zdList", zdList);
	}
	
	/**
	 * 初始化数据【結果查詢設置】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initJgcxSetup(RequestForm rForm, DemoFormForm myForm,
			User user, HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "customForm.do";

		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
}
