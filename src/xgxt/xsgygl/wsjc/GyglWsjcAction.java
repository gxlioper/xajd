package xgxt.xsgygl.wsjc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.xsgygl.GyglTyForm;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 通用版本公寓管理-卫生检查-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * 12 Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class GyglWsjcAction extends BasicAction {

	/**
	 * 参数设置
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward csszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// 用户对象

		// ================= 赋初值 ==================
		// 访问路径
		String path = "gygl_wsjc_cssz.do";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 功能模块
		String gnmk = "gygl";
		// 菜单
		String menu = "wsjc_cssz";
		// 提示信息
		String message = "";
		// =================end==================

		// =================执行保存操作==================;
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveCssz(myForm, user, request);
			message = flag ? "操作成功" : "操作失败";
		}
		// =================end==================

		// =================获得参数设置内容==================
		HashMap<String, String> rs = service.getCsszInfo();
		// =================end==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setRs(rs);
		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("csszManage");
	}

	/**
	 * 空白报表
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward kbbbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// 用户对象
		GyglWsjcModel wsjcModel = service.getWsjc();// 卫生检查对象

		// ================= 赋初值 ==================
		// 访问路径
		String path = "gygl_wsjc_kbbb.do";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 功能模块
		String gnmk = "gygl";
		// 菜单
		String menu = "wsjc_kbbb";
		// 提示信息
		String message = "";
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 公寓版本
		String edition = myForm.getEdition();
		// 是否学院
		boolean isxy = service.isXy(user);
		// =================end==================

		// ================= 未设置参数设置 ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= 判断是否学院 ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// =================执行导出操作==================;
		if ("exp".equalsIgnoreCase(doType)) {

			myForm.setUserName(user.getUserName());
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expWsjcKbbb(myForm, wsjcModel, response.getOutputStream());

			return null;
		}
		// =================end==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "jczq", "isxy", "gyglEdition" };
		// 其他字段值
		String[] qtzdz = new String[] { jczq, String.valueOf(isxy), edition };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("kbbbManage");
	}

	/**
	 * 卫生分录入
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fslrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// 用户对象
		GyglWsjcModel wsjcModel = service.getWsjc();// 卫生检查对象

		// ================= 赋初值 ==================
		// 访问路径
		String path = "gygl_wsjc_fslr.do";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 功能模块
		String gnmk = "gygl";
		// 菜单
		String menu = "wsjc_fs";
		// 提示信息
		String message = "";
		// 公寓版本
		String edition = myForm.getEdition();
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 是否关联等级
		String gldj = wsjcModel.getGldj();
		// 是否关联分数
		String glfs = wsjcModel.getGlfs();
		// 检查周次
		String jczc = myForm.getJczc();
		// 检查时间
		String jcsj = myForm.getJcsj();
		if (Base.isNull(jcsj)) {
			jcsj = service.getNowTime("YYYYMMDD");
			myForm.setJcsj(jcsj);
		}
		// 当前周次
		String dqzc = "";
		if (Base.isNull(jczc) && "周".equalsIgnoreCase(jczq)) {
			dqzc = service.getNowZc(jcsj, wsjcModel);
			myForm.setJczc(dqzc);
		}
		// 当年学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		// 当年学期
		String xq = Base.currXq;
		myForm.setXq(xq);
		// 当年年度
		String nd = Base.currNd;
		myForm.setNd(nd);
		// 等级列表
		List<HashMap<String, String>> wsfdjList = wsjcModel.getWsdjList();
		int djNum = (wsfdjList != null && wsfdjList.size() > 0) ? wsfdjList
				.size() : 0;
		// 是否学院
		boolean isxy = service.isXy(user);
		// 表头
		List<HashMap<String, String>> topTr = null;
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= 未设置参数设置 ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= 判断是否学院 ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// =================执行保存操作==================;
		if ("save".equalsIgnoreCase(doType)) {
			// 检查楼栋
			String[] jcld = myForm.getJcld();

			if (jcld != null && jcld.length > 0) {
				boolean flag = service.saveWsf(myForm, wsjcModel, user);
				message = flag ? "操作成功" : "操作失败";
			} 
		} else if ("update".equalsIgnoreCase(doType)) {
			boolean flag = service.updateWsf(myForm, wsjcModel, user);
			message = flag ? "操作成功" : "操作失败";
		}
		// =================end==================

		// =================执行查询操作==================;
		if (search) {
			// 表头
			topTr = service.getWsflrTop(wsjcModel);
			// 结果集
			rsArrList = service.getWsflrList(myForm, wsjcModel);
		}
		// =================end==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "isxy", "jczq", "jczc", "lrxs", "gldj",
				"glfs", "djNum","gyglEdition" };
		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(isxy), jczq, jczc, lrxs,
				gldj, glfs, String.valueOf(djNum), edition };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		request.setAttribute("wsfdjList", wsfdjList);
		// ===================end ====================

		// ===================初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("fslrManage");
	}

	/**
	 * 显示寝室信息
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward showQsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// 用户对象
		GyglWsjcModel wsjcModel = service.getWsjc();// 卫生检查对象

		// ================= 赋初值 ==================
		// 功能模块
		String gnmk = "gygl";
		// 菜单
		String menu = "wsjc_kbbb";
		// 提示信息
		String message = "";
		// 公寓版本
		String edition = myForm.getEdition();
		// 主键
		String pk = request.getParameter("pk");
		myForm.setPkValue(pk);
		// 检查周次
		String jczc = request.getParameter("jczc");
		myForm.setJczc(jczc);
		// 检查时间
		String jcsj = request.getParameter("jcsj");
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		myForm.setJcsj(jcsj);
		// 寝室卫生分情况
		HashMap<String, String> rs = service.getQsWsfInfo(myForm, wsjcModel);
		ArrayList<String[]> rsArrList = service.getQsrzInfo(myForm);
		// ================= end ==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "jczq", "lrxs", "gyglEdition" };
		// 其他字段值
		String[] qtzdz = new String[] { jczq, lrxs, edition };

		rForm.setRs(rs);
		rForm.setRsArrList(rsArrList);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("qsInfo");

	}

	/**
	 * 卫生分查询
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fscxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// 用户对象
		GyglWsjcModel wsjcModel = service.getWsjc();// 卫生检查对象

		// ================= 赋初值 ==================
		// 访问路径
		String path = "gygl_wsjc_fscx.do";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 操作表
		String realTable = "gygl_wsjc_wsfwhb";
		// 功能模块
		String gnmk = "gygl";
		// 菜单
		String menu = "wsjc_fs";
		// 提示信息
		String message = "";
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 是否关联等级
		String gldj = wsjcModel.getGldj();
		// 是否关联分数
		String glfs = wsjcModel.getGlfs();
		// 公寓版本
		String edition = myForm.getEdition();
		// 类型
		String lx = Base.isNull(myForm.getLx()) ? "qs" : myForm.getLx();
		myForm.setLx(lx);
		// 检查时间
		String jcsj = myForm.getJcsj();
		if (Base.isNull(jcsj)) {
			jcsj = service.getNowTime("YYYYMMDD");
			myForm.setJcsj(jcsj);
		}
		// 结束周次
		String jszc = myForm.getJszc();
		// 当前周次
		String dqzc = "";
		if (Base.isNull(jszc) && "周".equalsIgnoreCase(jczq)) {
			dqzc = service.getNowZc(jcsj, wsjcModel);
			myForm.setJszc(dqzc);
		}
		// 等级列表
		List<HashMap<String, String>> wsfdjList = wsjcModel.getWsdjList();
		int djNum = (wsfdjList != null && wsfdjList.size() > 0) ? wsfdjList
				.size() : 0;
		// 是否学院
		boolean isxy = service.isXy(user);
		// 表头
		List<HashMap<String, String>> topTr = null;
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= 未设置参数设置 ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= 判断是否学院 ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// =================执行删除操作==================;
		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.delWsf(myForm, user);
			message = flag ? "操作成功" : "操作失败";
		}
		// =================end==================
		
		// =================执行导出操作==================;
		if ("exp".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.wsjcQsExp(myForm, wsjcModel, response.getOutputStream());

			return null;
		}
		// =================end==================
		
		// =================执行查询操作==================;
		if (search) {
			// 表头
			topTr = service.getWsflrTop(wsjcModel);
			// 结果集
			rsArrList = service.getWsjcJgList(myForm, wsjcModel);
			
			int size = myForm.getPages().getMaxRecord();

			if (rsArrList != null && rsArrList.size() > 0
					&& size < rsArrList.size()) {
				rsArrList = service.getResultList(rsArrList, myForm);
			}
		}
		// =================end==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "isxy", "jczq", "lrxs", "gldj", "glfs",
				"djNum", "lx", "gyglEdition" };
		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(isxy), jczq, lrxs, gldj,
				glfs, String.valueOf(djNum), lx, edition };

		rForm.setMessage(message);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		request.setAttribute("wsfdjList", wsfdjList);
		// ===================end ====================

		// ===================初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("fscxManage");
	}
	
	/**
	 * 卫生分修改
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fscxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// 用户对象
		GyglWsjcModel wsjcModel = service.getWsjc();// 卫生检查对象

		// ================= 赋初值 ==================
		// 访问路径
		String path = "gygl_wsjc_fscx.do";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 主键
		String pk = request.getParameter("pk");
		myForm.setPkValue(pk);
		// 功能模块
		String gnmk = "gygl";
		// 菜单
		String menu = "wsjc_fs";
		// 提示信息
		String message = "";
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 是否关联等级
		String gldj = wsjcModel.getGldj();
		// 是否关联分数
		String glfs = wsjcModel.getGlfs();
		// 类型
		String lx = request.getParameter("lx");
		// 寝室卫生分情况
		HashMap<String, String> rs = service.getWsfjgInfo(myForm, wsjcModel);
		// 住宿列表
		String pkValue = rs.get("lddm") + rs.get("cs") + rs.get("qsh");
		myForm.setPkValue(pkValue);
		ArrayList<String[]> rsArrList = service.getQsrzInfo(myForm);
		//页面跳转
		String forward = "qs".equalsIgnoreCase(lx) ? "qsInfo" : "xsInfo";
		// =================end==================

		// =================执行保存操作==================;
		if ("save".equalsIgnoreCase(doType)) {

			myForm.setPkValue(pk);

			boolean flag = service.editWsf(myForm, request);
			message = flag ? "操作成功" : "操作失败";
			
			myForm.setLx("qs");
			rs = service.getWsfjgInfo(myForm, wsjcModel);
		}
		// =================end==================
		
		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "jczq", "lrxs", "gldj", "glfs", "lx",
				"pk" };
		// 其他字段值
		String[] qtzdz = new String[] { jczq, lrxs, gldj, glfs, lx, pk };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setRs(rs);
		rForm.setRsArrList(rsArrList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward(forward);
	}

	/**
	 * 卫生分统计
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fstjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// 用户对象
		GyglWsjcModel wsjcModel = service.getWsjc();// 卫生检查对象

		// ================= 赋初值 ==================
		// 访问路径
		String path = "gygl_wsjc_fstj.do";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 功能模块
		String gnmk = "gygl";
		// 菜单
		String menu = "wsjc_fs";
		// 公寓版本
		String edition = myForm.getEdition();
		// 提示信息
		String message = "";
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 是否关联等级
		String gldj = wsjcModel.getGldj();
		// 是否关联分数
		String glfs = wsjcModel.getGlfs();
		// 检查时间
		String jcsj = myForm.getJcsj();
		if (Base.isNull(jcsj)) {
			jcsj = service.getNowTime("YYYYMMDD");
			myForm.setJcsj(jcsj);
		}
		// 结束周次
		String jszc = myForm.getJszc();
		// 当前周次
		String dqzc = "";
		if (Base.isNull(jszc) && "周".equalsIgnoreCase(jczq)) {
			dqzc = service.getNowZc(jcsj, wsjcModel);
			myForm.setJszc(dqzc);
		}
		// 等级列表
		List<HashMap<String, String>> wsfdjList = wsjcModel.getWsdjList();
		int djNum = (wsfdjList != null && wsfdjList.size() > 0) ? wsfdjList
				.size() : 0;
		// 是否学院
		boolean isxy = service.isXy(user);
		// 表头
		List<HashMap<String, String>> topTr = null;
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= 未设置参数设置 ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= 判断是否学院 ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// ================= 未设置参数设置 ==================
		String dj = service.getOneValue("gygl_wsjc_wsfdjb", "wsfdj", "1", "1");
		if(Base.isNull(dj)){
			String msg = "本模块统计基于卫生分等级进行操作，本次分数录入无等级，本模块无法操作，请确认！";

			request.setAttribute("yhInfo", msg);

			return new ActionForward("/yhInfo.do", false);
		}
		// ================= end ==================
		
		// =================执行导出操作==================;
		if ("exp".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.wsjcTjExp(myForm, wsjcModel, response.getOutputStream(),
					topTr, request);

			return null;
		}
		// =================end==================
		
		// =================执行查询操作==================;
		if (search) {
			
			service.tbWsfAndDj(myForm, wsjcModel);
			// 表头
			topTr = service.getWsfTjTop(myForm, wsjcModel);
			// 结果集
			rsArrList = service.getResultList(service.getWsfTjList(myForm,
					wsjcModel, topTr, request), myForm);
		}
		// =================end==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "isxy", "jczq", "lrxs", "gldj", "glfs",
				"djNum","gyglEdition" };
		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(isxy), jczq, lrxs, gldj,
				glfs, String.valueOf(djNum),edition };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		request.setAttribute("wsfdjList", wsfdjList);
		
		if (wsfdjList != null && wsfdjList.size() > 0) {
			String wsfdj = wsfdjList.get(0).get("wsfdj");
			request.setAttribute("wsfdj", wsfdj);
		}
		// ===================end ====================

		// =================== 初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("fstjManage");
	}
	
	/**
	 * 未设置参数设置的跳转
	 * 
	 * @param request
	 * @return
	 */
	private ActionForward errForward(HttpServletRequest request) {

		String msg = "卫生检查的参数设置尚未被设置，请确认！";

		request.setAttribute("yhInfo", msg);

		return new ActionForward("/yhInfo.do", false);
	}
	
	/**
	 * @param myForm
	 * @param user
	 * @param isxy
	 */
	private void isXy(GyglTyForm myForm, User user, boolean isxy) {
		if (isxy) {
			String userDep = user.getUserDep();
			myForm.setXydm(userDep);
		}
	}
		
	/**
	 * 学生分数录入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author sjf
	 */
	public ActionForward xsfslrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		User user = getUser(request);// 用户对象
		GyglWsjcModel wsjcModel = service.getWsjc();// 卫生检查对象

		// ================= 赋初值 ==================
		// 访问路径
		String path = "gygl_wsjc_xsfslr.do";
		// 功能模块
		String gnmk = "gygl";
		// 菜单
		String menu = "wsjc_fs";
		// 提示信息
		String message = "";
		// 检查周期
		String jczq = wsjcModel.getJczq();
		myForm.setJczq(jczq);
		// 检查周次
		String jczc = myForm.getJczc();
		// 检查时间
		String jcsj = myForm.getJcsj();
		
		if (Base.isNull(jcsj)) {
			jcsj = service.getNowTime("YYYYMMDD");
			myForm.setJcsj(jcsj);
		}
		// 当前周次
		String dqzc = "";
		if (Base.isNull(jczc) && "周".equalsIgnoreCase(jczq)) {
			dqzc = service.getNowZc(jcsj, wsjcModel);
			myForm.setJczc(dqzc);
		}
		// 当年学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		// 当年学期
		String xq = Base.currXq;
		myForm.setXq(xq);
		// 当年年度
		String nd = Base.currNd;
		myForm.setNd(nd);
		// 等级列表
		List<HashMap<String, String>> wsfdjList = wsjcModel.getWsdjList();
		int djNum = (wsfdjList != null && wsfdjList.size() > 0) ? wsfdjList
				.size() : 0;
		// 是否学院
		boolean isxy = service.isXy(user);
		// 表头
		List<HashMap<String, String>> topTr = null;
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= 未设置参数设置 ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= 判断是否学院 ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// =================执行查询操作==================;
		if(search){
			topTr = service.getXslrfTop(wsjcModel, "lr");
			rsArrList = service.getXslrfInfo(myForm);
		
		}
		// =================end==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "isxy", "jczq", "jczc", "djNum" };
		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(isxy), jczq, jczc,String.valueOf(djNum) };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setUserName(user.getUserName());

		service.setRequestValue(rForm, user, request);
		request.setAttribute("wsfdjList", wsfdjList);
		// ===================end ====================

		// ===================初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("xsfslrManage");
	}
	
	/**
	 * 具体录入学生加分减分情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author sjf
	 */
	public ActionForward xsfslrUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		// User user = getUser(request);
	
		GyglWsjcModel wsjcModel = service.getWsjc();// 卫生检查对象
		GyglWsjcXslrfModel lrfModel = new GyglWsjcXslrfModel();// 学生录入分对象
		
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		
		// 保存学生寝室加减分信息
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(lrfModel, myForm);
			String message = service.saveXslrf(lrfModel, wsjcModel.getJczq()) ? "保存成功！" : "保存失败！";
			request.setAttribute("message", message);
		}
		
		// 获取学生住宿信息
		if(StringUtils.isNotNull(xh)){
			selectPageDataByOne(request, "xszsxxb", "view_xszsxx", xh);
		}
	
		// ================= 赋初值 ==================
		// 检查周期
		String jczq = wsjcModel.getJczq();
	
		// 检查周次
		String jczc = myForm.getJczc();
		// 检查时间
		String jcsj = myForm.getJcsj();
	
		String title = "公寓管理-卫生检查-学生个人卫生分维护";
			
		request.setAttribute("title", title);
		request.setAttribute("jczq", jczq);
		request.setAttribute("jcsj", jcsj);
		request.setAttribute("jczc", jczc);
		request.setAttribute("doType", doType);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("xqmc", BasicExtendService.xqMap.get(xq));
		request.setAttribute("nd", nd);
		request.setAttribute("lrsj", DateUtils.getTime());
		request.setAttribute("addXmList", service.getWsjcInfo("加分"));
		request.setAttribute("reduceXmList", service.getWsjcInfo("减分"));
		return mapping.findForward("xsfslrUpdate");
	}
	
	/**
	 * 学生分数查看情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsfsckManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		
		User user = getUser(request);// 用户对象
		GyglWsjcModel wsjcModel = service.getWsjc();// 卫生检查对象
		
		// 是否需要统计，当为参数设置为周时需要统计
		boolean sftj = "周".equalsIgnoreCase(wsjcModel.getJczq()) ? true : false;
		
		myForm.setJczq(wsjcModel.getJczq());

		// ================= 赋初值 ==================
		// 访问路径
		String path = "gygl_wsjc_xsfsck.do";
		// 功能模块
		String gnmk = "gygl";
		// 菜单
		String menu = "wsjc_fs";
		// 提示信息
		String message = "";

		// 是否学院
		boolean isxy = service.isXy(user);
		// 表头
		List<HashMap<String, String>> topTr = null;
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= 未设置参数设置 ==================
		if (!service.isSz()) {
			return errForward(request);
		}
		// ================= end ==================

		// ================= 判断是否学院 ==================
		isXy(myForm, user, isxy);
		// ================= end ==================
		
		// =================执行查询操作==================;
		if(search){
			topTr = service.getXslrfTop(wsjcModel, "ck");
			
			rsArrList = service.getXsxqlrfInfo(myForm);
		
		}
		// =================end==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();
		
		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setUserName(user.getUserName());
		
		service.setRequestValue(rForm, user, request);
		request.setAttribute("isxy", isxy);
		request.setAttribute("sftj", sftj);
		// ===================end ====================

		// ===================初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
	
		// =================end ===================
		return mapping.findForward("xsfsckManage");
	}
	
	/**
	 * 具体录入学生没学期加分减分情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author sjf
	 */
	public ActionForward xsfsckUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyglWsjcService service = new GyglWsjcService();
		// User user = getUser(request);
	
		GyglWsjcModel wsjcModel = service.getWsjc();// 卫生检查对象
		
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String xqmc = request.getParameter("xqmc");
		String jczq = wsjcModel.getJczq();
		
		// 获取学生住宿信息
		if(StringUtils.isNotNull(xh)){
			selectPageDataByOne(request, "xszsxxb", "view_xszsxx", xh);
		}
		
		// 存放该学期学生所有加减分项目
		
		List<HashMap<String, String>> xsfsInfo = service.getXsfsInfo(xh, xn, xq, jczq);
	 
		// ================= 赋初值 ==================
	
		String title = "公寓管理 - 卫生检查 - 学生具体卫生分查看";
			
		request.setAttribute("title", title);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("jczq", jczq);
		request.setAttribute("xsfsInfo", xsfsInfo);
		request.setAttribute("xqmc", xqmc);
		return mapping.findForward("xsfsckUpdate");
	}
	
	/**
	 * 学生分数统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsfsTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		GyglTyForm myForm = (GyglTyForm) form;
		GyglWsjcService service = new GyglWsjcService();
		
		if("print".equalsIgnoreCase(doType)){
			response.setContentType("application/vnd.ms-excel");
			service.xswsfTj(myForm, response.getOutputStream());
			return mapping.findForward("");
		}
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		return mapping.findForward("xsfsTj");
	}
}
