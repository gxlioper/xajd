package xsgzgl.xsxx.grxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjForm;
import xsgzgl.xsxx.cssz.XsxxCsszForm;
import xsgzgl.xsxx.model.CsszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_个人信息_Init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxGrxxInit {

	/**
	 * 个人信息_修改申请_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initGrxxSq(RequestForm rForm, XsxxGrxxForm model, User user,
			HttpServletRequest request) {

		XsxxGrxxService service = new XsxxGrxxService();
		CsszModel csszModel = model.getCsszModel();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = user.getUserType();
		// 访问路径
		String path = "xsxx_grxx_sq.do";
		// 是否审核
		String sfsh = csszModel.getSfsh();
		// 流程ID
		String lcid = csszModel.getLcid();
		// 学号
		String xh = "";
		if ("stu".equalsIgnoreCase(userType)) {
			xh = user.getUserName();
		} else {
			xh = request.getParameter("xh");
		}
		model.setXh(xh);
		// 申请ID
		String sqid = service.getSqid(model, user);
		model.setSqid(sqid);
		
		// 申请结果
		String sqjg = service.getOneValue("xg_xsxx_grxx_xgsqb", "sqjg", "id", sqid);
		model.setSqjg(sqjg);
		
		// 其他字段
		String[] qtzd = new String[] { "sfsh", "lcid", "sqid" };
		// 其他字段值
		String[] qtzdz = new String[] { sfsh, lcid, sqid };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);

		if (!Base.isNull(xh)) {
			try {
				XsxxGrxxService.initZdxgb(xh);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 个人信息_修改审核_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initGrxxSh(RequestForm rForm, XsxxGrxxForm model, User user,
			HttpServletRequest request) {

		CsszModel csszModel = model.getCsszModel();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = user.getUserType();
		// 访问路径
		String path = "xsxx_grxx_sh.do";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// 学号
		String xh = "";
		if ("stu".equalsIgnoreCase(userType)) {
			xh = user.getUserName();
		} else {
			xh = request.getParameter("xh");
		}
		model.setXh(xh);
		
		// 判断用户岗位
		int gwnum = 1;

		List<HashMap<String, String>> yhgwList = XtwhShlcService.getSpgwList(
				csszModel.getLcid(), user.getUserName());

		request.setAttribute("yhgwList", yhgwList);

		if (yhgwList != null && yhgwList.size() > 1) {
			gwnum = yhgwList.size();
		}
		

		// 其他字段
		String[] qtzd = new String[] { "gwnum" };
		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(gwnum) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	
	/**
	 * 个人信息_修改审核_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initGrxxJg(RequestForm rForm, XsxxGrxxForm model, User user,
			HttpServletRequest request) {

		XsxxGrxxService service = new XsxxGrxxService();
		
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = user.getUserType();
		// 访问路径
		String path = "xsxx_grxx_jg.do";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// 学号
		String xh = "";
		// 姓名
		String xm = "";
		if ("stu".equalsIgnoreCase(userType)) {
			xh = user.getUserName();
			xm = service.getOneValue("view_xsjbxx", "xm", "xh", xh);
		} else {
			xh = request.getParameter("xh");
		}
		model.setXh(xh);

		// 其他字段
		String[] qtzd = new String[] { "xh", "xm" };
		// 其他字段值
		String[] qtzdz = new String[] { xh, xm };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	
	/**
	 * 个人信息_修改申请（详细）_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initGrxxShDetail(RequestForm rForm, XsxxGrxxForm model,
			User user, HttpServletRequest request) {

		XsxxGrxxService service = new XsxxGrxxService();
		CsszModel csszModel = model.getCsszModel();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = user.getUserType();
		// 访问路径
		String path = "xsxx_grxx_jg.do";
		// 申请ID
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		// 审核岗位
		String shgw = request.getParameter("shgw");
		model.setShgw(shgw);
		// 学号
		String xh = service.getOneValue("xg_xsxx_grxx_xgsqb", "xh", "id", sqid);
		model.setXh(xh);

		// 其他字段
		String[] qtzd = new String[] { "sqid", "shgw", "xh" };
		// 其他字段值
		String[] qtzdz = new String[] { sqid, shgw, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
	}
	
	private List<HashMap<String, String>> getDefaultValue(XsxxGrxxForm model,
			String path) {

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		if ("xsxx_grxx_sh.do".equalsIgnoreCase(path)) {// 个人信息修改审核

			String[] en = new String[] { "xh", "xm", "bjmc", "sqsj", "shzt" };
			String[] cn = new String[] { "学号", "姓名", "班级名称", "申请时间", "审核状态" };

			topTr = dao.arrayToList(en, cn);
		} else if ("xsxx_grxx_jg.do".equalsIgnoreCase(path)) {// 个人信息修改结果

			String[] en = new String[] { "xh", "xm", "bjmc", "sqsj", "sqjg" };
			String[] cn = new String[] { "学号", "姓名", "班级名称", "申请时间", "申请结果" };

			topTr = dao.arrayToList(en, cn);
		}

		return topTr;
	}
}
