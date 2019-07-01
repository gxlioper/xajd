package xsgzgl.xszz.jhzy.gjzxj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 学生资助_国家助学金_金华职业_Init类
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

public class GjzxjInit {

	/**
	 * 国家助学金申请【查询】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initSqSearch(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xszz_jhzy_gjzxj_sq.do";
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
	 * 国家助学金申请【增加】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initSqInsert(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();
		GjzxjService service = new GjzxjService();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		String tableName = "view_xsjbxx";
		// 访问路径
		String path = "xszz_jhzy_gjzxj_sq.do";
		// 学号
		String xh = request.getParameter("xh");
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			xh = user.getUserName();
		}
		// 资助学年
		String xn = csszModel.getXn();
		// 是否困难生
		boolean isKns = csszModel.getIsKns(xh, xn);
		// 国家助学金申请状态
		String gjzxjsqzt = csszModel.getGjzxjsqzt();

		HashMap<String, String> rs = new HashMap<String, String>();
		if (!Base.isNull(xh)) {
			rs = service.getGjzxjInfo(xh, xn);
		}

		// 其他字段
		String[] qtzd = new String[] { "xh", "isKns", "gjzxjsqzt" };
		// 其他字段值
		String[] qtzdz = new String[] { xh, String.valueOf(isKns), gjzxjsqzt };

		rForm.setTableName(tableName);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);
	}

	/**
	 * 国家助学金申请【修改】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initSqUpdate(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();
		GjzxjService service = new GjzxjService();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xszz_jhzy_gjzxj_sq.do";
		// 国家助学金申请状态
		String gjzxjsqzt = csszModel.getGjzxjsqzt();
		// 其他字段
		String[] qtzd = new String[] { "gjzxjsqzt" };
		// 其他字段值
		String[] qtzdz = new String[] { gjzxjsqzt };
		// 主键
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!Base.isNull(pkValue)) {
			String xn = pkValue.split("luojw")[0];
			String xh = pkValue.split("luojw")[1];
			rs = service.getGjzxjInfo(xh, xn);
		}

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);
	}

	/**
	 * 国家助学金审核【查询】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initShSearch(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = request.getParameter("userType");
		// 访问路径
		String path = "xszz_jhzy_gjzxj_sh.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HttpSession session = request.getSession();

		if ("fdy".equalsIgnoreCase(userType)) {
			session.setAttribute("fdyQx", "true");
			session.setAttribute("bzrQx", "false");
		} else if ("bzr".equalsIgnoreCase(userType)) {
			session.setAttribute("fdyQx", "false");
			session.setAttribute("bzrQx", "true");
		}
	}

	/**
	 * 国家助学金审核【修改】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initShUpdate(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		GjzxjService service = new GjzxjService();

		String pkValue = request.getParameter("pkValue");
		String xn = pkValue.split("luojw")[0];
		String xh = pkValue.split("luojw")[1];

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xszz_jhzy_gjzxj_sh.do";
		// 其他字段
		String[] qtzd = new String[] { "xn", "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HashMap<String, String> map = service.getGjzxjInfo(xh, xn);
		request.setAttribute("map", map);

		String tjdc = "一等";

		if ("bzr".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("bzrtjdc")) ? tjdc : map.get("bzrtjdc");
			request.setAttribute("shyj", map.get("bzrshyj"));
		} else if ("fdy".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("fdytjdc")) ? map.get("bzrtjdc") : map
					.get("fdytjdc");
			request.setAttribute("shyj", map.get("fdyshyj"));
		}
		if ("xy".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("xytjdc")) ? map.get("fdytjdc") : map
					.get("xytjdc");
			request.setAttribute("shyj", map.get("xyshyj"));
		}
		if ("xx".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("xxtjdc")) ? map.get("xytjdc") : map
					.get("xxtjdc");
			request.setAttribute("shyj", map.get("xxshyj"));
		}

		request.setAttribute("tjdj", tjdc);
		request.setAttribute("map", map);
	}

	/**
	 * 国家助学金结果【查询】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initJgSearch(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xszz_jhzy_gjzxj_jg.do";
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
	 * 国家助学金结果【详细】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initJgDetail(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		GjzxjService service = new GjzxjService();

		String pkValue = request.getParameter("pkValue");
		String xn = pkValue.split("luojw")[0];
		String xh = pkValue.split("luojw")[1];

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xszz_jhzy_gjzxj_jg.do";
		// 其他字段
		String[] qtzd = new String[] { "xn", "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HashMap<String, String> map = service.getGjzxjInfo(xh, xn);
		request.setAttribute("map", map);
	}
}
