package xsgzgl.xszz.jhzy.gjlzjxj;

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
 * Description: 学生资助_国家励志奖学金_金华职业_Init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */

public class GjlzjxjInit {

	/**
	 * 国家励志奖学金申请【查询】
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initSqSearch(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xszz_jhzy_gjlzjxjSq.do";
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
	 * 国家励志奖学金申请【增加】
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initSqInsert(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		GjlzjxjService service = new GjlzjxjService();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		String tableName = "view_xsjbxx";
		// 访问路径
		String path = "xszz_jhzy_gjlzjxjSq.do";
		// 学号
		String xh = request.getParameter("xh");
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			xh = user.getUserName();
		}

		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("flag", "true");
		if (!Base.isNull(xh)) {
			XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();
			// 学年
			String xn = csszModel.getXn();
			rs = service.getGjlzjxjInfo(xh, xn);
		} 

		// 其他字段
		String[] qtzd = new String[] { "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { xh };

		rForm.setTableName(tableName);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);
	}

	/**
	 * 国家励志奖学金申请【修改】
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initSqUpdate(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		GjlzjxjService service = new GjlzjxjService();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xszz_jhzy_knsrd_sq.do";
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};
		// 主键
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!Base.isNull(pkValue)) {
			String xn = pkValue.split("!!luojw!!")[0];
			String xh = pkValue.split("!!luojw!!")[1];
			rs = service.getGjlzjxjInfo(xh, xn);
		}

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);
	}

	/**
	 * 国家励志奖学金审核【查询】
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initShSearch(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = request.getParameter("userType");
		// 访问路径
		String path = "xszz_jhzy_gjlzjxjSh.do";
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
	 * 国家奖学金认定结果【查询】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initjgSearch(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = request.getParameter("userType");
		// 访问路径
		String path = "xszz_jhzy_gjlzjxjCx.do";
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
	 * 国家励志奖学金审核【修改】
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initShUpdate(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		GjlzjxjService service = new GjlzjxjService();

		String pkValue = request.getParameter("pkValue");
		String xn = pkValue.split("!!luojw!!")[0];
		String xh = pkValue.split("!!luojw!!")[1];

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xszz_jhzy_Gjlzjxj_sh.do";
		// 其他字段
		String[] qtzd = new String[] { "xn", "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HashMap<String, String> map = service.getGjlzjxjInfo(xh, xn);
		request.setAttribute("map", map);


		if ("bzr".equalsIgnoreCase(user.getUserStatus())) {
			request.setAttribute("shyj", map.get("bzrshyj"));
		} else if ("fdy".equalsIgnoreCase(user.getUserStatus())) {
			request.setAttribute("shyj", map.get("fdyshyj"));
		}
		if ("xy".equalsIgnoreCase(user.getUserStatus())) {
			request.setAttribute("shyj", map.get("xyshyj"));
		}
		if ("xx".equalsIgnoreCase(user.getUserStatus())) {
			request.setAttribute("shyj", map.get("xxshyj"));
		}
		request.setAttribute("xh", xh);
		request.setAttribute("xn", xn);
		request.setAttribute("map", map);
	}

	/**
	 * 国家励志奖学金结果【查询】
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initJgSearch(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xszz_jhzy_knsrd_jg.do";
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
	 *国家励志奖学金结果【详细】
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initJgDetail(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		GjlzjxjService service = new GjlzjxjService();

		String pkValue = request.getParameter("pkValue");
		String xn = pkValue.split("luojw")[0];
		String xh = pkValue.split("luojw")[1];

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xszz_jhzy_Gjlzjxj_jg.do";
		// 其他字段
		String[] qtzd = new String[] { "xn", "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { xn, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HashMap<String, String> map = service.getGjlzjxjInfo(xh, xn);
		request.setAttribute("map", map);

	
		request.setAttribute("xn", xn);
		request.setAttribute("xh", xh);
		request.setAttribute("map", map);
	}
}
