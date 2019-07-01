package xsgzgl.pjpy.general.tjcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;
import xsgzgl.pjpy.general.inter.PjpyTjcxInterface;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_通用_Init类
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

public class PjpyTjcxInit extends BasicInit {

	/**
	 * 综测班级名单_初始化数据【有等级考试】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initZcbjmdDjks(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// 访问路径
		String path = "pjpy_tjcx_zcbjmd_djks.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// 智育排名
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// 综测排名
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 样式路径
		String stylePath = request.getParameter("stylePath");
		// 参评组
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * 综测班级名单_初始化数据【无等级考试】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initZcbjmdNoDjks(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// 访问路径
		String path = "pjpy_tjcx_zcbjmd_nodjks.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// 智育排名
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// 综测排名
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 样式路径
		String stylePath = request.getParameter("stylePath");
		// 参评组
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 *获奖名单汇总_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initHjmdhz(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// 访问路径
		String path = "pjpy_tjcx_hjmdhz.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// 智育排名
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// 综测排名
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 样式路径
		String stylePath = request.getParameter("stylePath");
		// 参评组
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = new String[] { pjxn };
		searchModel.setSearch_tj_xn(xn);

		request.setAttribute("searchTj", searchModel);
	}

	/**
	 *获奖金额汇总_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initHjjehz(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// 访问路径
		String path = "pjpy_tjcx_hjjehz.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// 智育排名
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// 综测排名
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 样式路径
		String stylePath = request.getParameter("stylePath");
		// 参评组
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = new String[] { pjxn };
		searchModel.setSearch_tj_xn(xn);

		request.setAttribute("searchTj", searchModel);
	}
	
	/**
	 * 学院奖学金推荐名单汇总
	 * @param request
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initTjmdhz(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// 访问路径
		String path = "pjpy_tjcx_tjmdhz.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// 智育排名
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// 综测排名
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 样式路径
		String stylePath = request.getParameter("stylePath");
		// 参评组
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = new String[] { pjxn };
		searchModel.setSearch_tj_xn(xn);

		request.setAttribute("searchTj", searchModel);
	}
	
	/**
	 * 困难生奖学金推荐名单汇总
	 * @param request
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initKnsTjmdhz(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// 访问路径
		String path = "pjpy_tjcx_knstjmdhz.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// 智育排名
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// 综测排名
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 样式路径
		String stylePath = request.getParameter("stylePath");
		// 参评组
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = new String[] { pjxn };
		searchModel.setSearch_tj_xn(xn);

		request.setAttribute("searchTj", searchModel);
	}
	
	/**
	 *获奖金额汇总_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initCmhjmdhz(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// 访问路径
		String path = "pjpy_tjcx_cmhjmdhz.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// 智育排名
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// 综测排名
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 样式路径
		String stylePath = request.getParameter("stylePath");
		// 参评组
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = new String[] { pjxn };
		searchModel.setSearch_tj_xn(xn);

		request.setAttribute("searchTj", searchModel);
	}
}
