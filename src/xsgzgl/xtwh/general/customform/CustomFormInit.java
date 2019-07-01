package xsgzgl.xtwh.general.customform;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.RequestForm;
import xgxt.form.User;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 系統維護_自定義表單_Init类
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

public class CustomFormInit {

	/**
	 * 初始化数据【自定義表單管理】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initManage(RequestForm rForm, CustomFormForm myForm, User user,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xtwh_general_customform.do";
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
	 * 初始化数据【自定義表單参数】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initParameter(RequestForm rForm, CustomFormForm myForm,
			User user, HttpServletRequest request) {

		DAO dao = DAO.getInstance();
		
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xtwh_general_customform.do";
		// 创建时间
		String cjsj = dao.getNowTime("yyyy-mm-dd hh24:mi:ss");
		// 其他字段
		String[] qtzd = new String[] { "cjsj" };
		// 其他字段值
		String[] qtzdz = new String[] { cjsj };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		// 所属模块列表
		List<HashMap<String, String>> ssmkList = dao.getWhList(
				"xg_xtwh_splcmkdzb", "mkdm", "mkmc", "", "", "", true);
		request.setAttribute("ssmkList", ssmkList);
	}
	
	/**
	 * 初始化数据【自定義表單设置】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initSetting(RequestForm rForm, CustomFormForm myForm,
			User user, HttpServletRequest request) {

		CustomFormService service = new CustomFormService();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xtwh_general_customform.do";
		// 表单ID
		String form_id = request.getParameter("form_id");
		// 显示顺序
		String xssx = service.getMaxXssx(form_id);
		// 其他字段
		String[] qtzd = new String[] { "xssx" };
		// 其他字段值
		String[] qtzdz = new String[] { xssx };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// 表单内容
		HashMap<String, String> map = service.getCustomFormInfo(form_id);
		request.setAttribute("formInfo", map);
	}
	
	/**
	 * 初始化数据【自定義表單查詢】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initSearch(RequestForm rForm, CustomFormForm myForm,
			User user, HttpServletRequest request) {

		CustomFormService service = new CustomFormService();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xtwh_general_customform.do";
		// 表单ID
		String form_id = request.getParameter("form_id");
		// 显示顺序
		String xssx = service.getMaxXssx(form_id);
		// 其他字段
		String[] qtzd = new String[] { "xssx" };
		// 其他字段值
		String[] qtzdz = new String[] { xssx };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// 表单内容
		HashMap<String, String> map = service.getCustomFormInfo(form_id);
		request.setAttribute("formInfo", map);
	}
}
