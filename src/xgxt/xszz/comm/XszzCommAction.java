package xgxt.xszz.comm;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommForm;
import xgxt.comm.FileManage;
import xgxt.comm.xml.XMLReader;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.exp.XszzExpService;
import xgxt.xszz.comm.xgsz.XszzXgszService;
import xgxt.xszz.comm.xmtj.XszzXmtjService;
import xgxt.xszz.tjgy.XszzPrintService;
import xgxt.xszz.zgdzdx.synData.SynDataService;

import com.zfsoft.basic.BasicAction;

import common.Globals;
import common.XszzXmdm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 通用版本学生资助-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class XszzCommAction extends BasicAction {

	/**
	 * 学生资助_信息_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward msgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("msgManage");
	}

	/**
	 * 学生资助_文件上传
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fileUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		String zd = request.getParameter("zd");
		String filePath = "";
		String doType = request.getParameter("doType");
		String message = "";

		if ("save".equalsIgnoreCase(doType)) {
			filePath = service.upLoadFile(request, myForm.getUploadFile(),
					"xszz");
			if (!Base.isNull(filePath)) {

				message = "上传成功";
			}
		}

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "zd" };
		// 其他字段值
		String[] qtzdz = new String[] { zd };

		rForm.setDoType(doType);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		return mapping.findForward("fileUpload");
	}

	public ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzTyForm myForm = (XszzTyForm) form;

		// 处理文件上传
		FormFile file = myForm.getUploadFile();
		String filePath = request.getParameter("scdz");
		String fName = "";
		if (file != null) {
			String dir = "/upload/dtjs";
			File f = new File(dir);
			if (!f.exists()) {
				f.mkdirs();
			}
			Timestamp date = new Timestamp(System.currentTimeMillis());
			String dateStr = date.toString().substring(0, 19);
			dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
					.replaceAll(":", "");
			int size = file.getFileSize();
			if (size < 10485760 && size != 0) {
				fName = dateStr + file.getFileName();
				InputStream in = file.getInputStream();
				filePath = dir + "/" + fName;
				OutputStream out = new FileOutputStream(filePath);
				int bytesRead = 0;
				byte[] buffer = new byte[size];
				while ((bytesRead = in.read(buffer, 0, size)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.close();
				in.close();
			} else {
				request.setAttribute("alert", "cannot");
			}
			request.setAttribute("filePath", filePath);
			request.setAttribute("message", "上传成功");
		}
		return mapping.findForward("uploadFile");
	}

	/**
	 * @describe 下载所选择文件
	 * @author luojw
	 * @throws Exception
	 */
	public ActionForward downLoad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		byte b[] = new byte[500];
		String dir = DealString.toGBK(request.getParameter("dir"));
		String filename = request.getParameter("fileName");

		if (!Base.isNull(filename)) {
			dir = servlet.getServletContext().getRealPath("WEB-INF/upLoad")
					+ "/" + filename;
			;
		} else {
			filename = dir.substring(27, dir.length());
		}

		File fileload = new File(dir);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(filename));
		InputStream in = new FileInputStream(fileload);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}

	/**
	 * @describe 删除所上传文件
	 * @author luojw
	 * @throws Exception
	 */
	public void fileDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = null;
		XszzTyForm myForm = (XszzTyForm) form;
		XszzCommService service = new XszzCommService();

		String doType = request.getParameter("doType");
		String pk = "xh||sqsj";
		String pkValue = myForm.getSave_xh() + myForm.getSave_sqsj();
		String realTable = "xszz_knsb";

		if (!Base.isNull(pkValue)) {
			service.fileDel(realTable, "scdz", pk, pkValue);
		}

		request.setAttribute("doType", doType);
		JSONObject obj = new JSONObject();
		obj.put("msg", "删除成功");
		out.flush();
		out.close();
	}

	/**
	 * 学生资助_项目维护_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 辅导员
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 视图名
		String tableName = "view_xszz_comm_xmwh";
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xszz_comm_xmwh");
		// 表名
		String realTable = "xszz_zzxmb";
		// 访问路径
		String path = "yes".equals(myForm.getIskns()) ? "kns_jbsz.do"
				: "xszz_xgsz_xmwh.do";
		// 当前学年
		String xn = Base.currXn;
		myForm.setQueryequals_xn(xn);
		// 当前学期
		String xq = Base.currXq;
		myForm.setQueryequals_xq(xq);
		// 当前年度
		String nd = Base.currNd;
		myForm.setQueryequals_nd(nd);
		// 提示信息
		String message = "";
		// 模块类型
		String mklx = (String) session.getAttribute("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			tableName = "pj".equalsIgnoreCase(mklx) ? "xg_view_xszz_pjpy_xmwh"
					: "xg_view_xszz_xmwh";
			path += "?mklx=" + mklx;
		}
		// =================end==================

		// ==================登陆用户检测 ==================
		if (bzrQx || fdyQx || "stu".equalsIgnoreCase(userType)) {
			String msg = "本模块只能由"+Base.YXPZXY_KEY+"或者学校用户进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================

		// ==================执行删除操作 ==================
		if ("xmDel".equalsIgnoreCase(doType)) {
			boolean result = service.delXmxgInfo(myForm);
			if (result) {
				this.deleteOperation(request, realTable);
			}
			message = result ? "操作成功" : "操作失败";
		}
		// =================end ===================

		// ==================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			// 项目代码
			String[] zzxmdm = myForm.getZzxmdm();
			if (zzxmdm != null && zzxmdm.length > 0) {
				boolean result = service.updateKgzt(myForm);
				message = result ? "操作成功" : "操作失败";
			} else {
				message = "请先确定各个项目的开关状态!";
			}
		}
		// =================end ===================

		// ==================执行查询操作 ==================
		// if (search) {
		String[] outputColumn = { "pk", "mrxm", "xmdm", "xmmc", "xmlb", "sqzq",
				"pdsj", "sfje", "sffj", "jelx", "shjb", "rskz", "kgzt" };

		// 根据流程设置查询范围
		request.setAttribute("annexTerm", new XszzService()
				.getFlowControlSql(myForm));
		this.selectPageDataByPagination(request, myForm, "", tableName,
				outputColumn);
		// }
		// =================end ===================

		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			outputColumn = null;
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "message" };
		// 其他字段值
		String[] qtzdz = new String[] { message };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getXqMc(Base.currXq));
		request.setAttribute("nd", Base.currNd);
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "xmwh");
		// =================end ===================

		return mapping.findForward("xmwhManage");
	}

	/**
	 * 学生资助_项目维护_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xmwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = "view_xszz_comm_xmwh";
		// 表名
		String realTable = "xszz_zzxmb";
		// 访问路径
		String path = "xszz_xgsz_xmwh.do";
		// 主键值
		String pkValue = request.getParameter("pk");
		myForm.setPkValue(pkValue);
		// 项目代码
		String xmdm = myForm.getXmdm();
		xmdm = Base.isNull(xmdm) ? service.getXmbh(realTable, "xmdm") : xmdm;
		myForm.setXmdm(xmdm);
		// 提示信息
		String message = "";
		// 项目信息
		HashMap<String, String> rs = service.getXmwhfo(myForm, tableName);
		// 模块类型
		String mklx = (String) session.getAttribute("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "?mklx=" + mklx;
		}
		// 人数控制
		String savedRskz = "";
		// 项目代码
		String savedXmdm = "";
		// 项目代码
		String savedKzjb = "";
		// =================end==================

		// ===================相关项目的特殊处理 ======================
		String xmb = rs.get("xmb");
		String knsTj = "yes";
		// /家庭情况调查或者困难生
		if ("jtqkdcb".equalsIgnoreCase(xmb)
				|| "xszz_knsb".equalsIgnoreCase(xmb)) {
			knsTj = "no";
		}
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			// 获得条件信息
			HashMap<String, String> tjMap = this.getValueMap(request,
					PRIFIX_SAVE);
			boolean result = service.saveXmwh(myForm, tjMap, request);
			message = result ? "操作成功" : "操作失败";

			savedXmdm = myForm.getXmdm();
			savedRskz = myForm.getRskz();
			savedKzjb = myForm.getKzjb();
		} else {
			savedXmdm = rs.get("xmdm");
			savedRskz = rs.get("rskz");
			savedKzjb = rs.get("kzjb");
			
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "message", "xmdm", "knsTj", "savedXmdm",
				"savedRskz", "savedKzjb" };
		// 其他字段值
		String[] qtzdz = new String[] { message, xmdm, knsTj, savedXmdm,
				savedRskz, savedKzjb };

		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "xmwh");
		// =================end ===================

		return mapping.findForward("xmwhUpdate");
	}

	/**
	 * 学生资助_人数设置_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;
		User user = getUser(request);

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = user.getUserType();
		// 用户所在部门
		String userDep = user.getUserDep();
		// 视图名
		String tableName = "";
		// 表名
		String realTable = "xszz_zzxmb";
		// 访问路径
		String path = "yes".equals(myForm.getIskns()) ? "kns_rssz.do"
				: "xszz_xgsz_rssz.do";
		// 当前学年
		String xn = Base.currXn;
		myForm.setQueryequals_xn(xn);
		// 当前学期
		String xq = Base.currXq;
		myForm.setQueryequals_xq(xq);
		// 当前年度
		String nd = Base.currNd;
		myForm.setQueryequals_nd(nd);
		// 项目代码
		String xmdm = myForm.getQueryequals_xmdm();

		String cz = request.getParameter("cz");

		request.setAttribute("cz", cz);

		if (Base.isNull(xmdm)) {
			xmdm = request.getParameter("xmdm");
		}
		myForm.setXmdm(xmdm);
		// 控制级别
		String kzjb = myForm.getKzjb();

		if (Base.isNull(kzjb)) {
			kzjb = request.getParameter("kzjb");
		}
		myForm.setKzjb(kzjb);

		// 人数设置ID
		String rsszId = "";
		// 提示信息
		String message = "";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 模块类型
		String mklx = request.getParameter("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "?mklx=" + mklx;
		}
		// =================end==================

		// ================= 用户类型检测 ==================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		// =================end==================

		// ==================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			// 判断保存人数是否超过上限
			message = service.isCgRssx(myForm);
			if (Base.isNull(message)) {
				boolean result = service.updateRssz(myForm);
				message = result ? "操作成功" : "操作失败";
			}
		}
		// =================end ===================

		// =================初始化 ===================
		if (("".equalsIgnoreCase(doType) || null == doType) && !search) {
			myForm = service.initXmdm(myForm, "rssz");
			// 根据控制级别区分表头
			kzjb = myForm.getKzjb();
			if (myForm.getXmdm() == null
					|| "".equalsIgnoreCase(myForm.getXmdm())) {
				request.setAttribute("yhInfo", "对不起，暂时没有需要设置人数的相关项目，请确认!");
				return new ActionForward("/yhInfo.do", false);
			}
			search = true;
		}

		request.setAttribute("xmdm", myForm.getXmdm());
		// =================查询 ===================
		if (search) {

			// 根据控制级别区分表头
			if ("学院".equalsIgnoreCase(kzjb)) {
				rsszId = "rssz_xy";
			} else if ("专业".equalsIgnoreCase(kzjb)) {
				rsszId = "rssz_zy";
			} else if ("班级".equalsIgnoreCase(kzjb)) {
				rsszId = "rssz_bj";
			}

			// 表头
			List<HashMap<String, String>> topTr = service.getTopTr(rsszId);
			// 内容
			ArrayList<String[]> rs = service.getRsszList(myForm);
			// 项目情况（人数上限等）
			HashMap<String, String> xmInfo = service.getXmrsInfo(myForm);
			request.setAttribute("xmInfo", xmInfo);
			request.setAttribute("rsNum", rs.size());
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "message" };
		// 其他字段值
		String[] qtzdz = new String[] { message };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ================当前学年、学期、年度==================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getXqMc(Base.currXq));
		request.setAttribute("nd", Base.currNd);
		// =======================end========================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "rssz");
		// =================end ===================

		// 宽度样式设置
		String widthType = request.getAttribute("widthType") != null ? request
				.getAttribute("widthType").toString() : myForm.getWidthType();
		if ("dbsx".equalsIgnoreCase(widthType)) {
			// 从待办事项进入
			request.setAttribute("widthType", "dbsx");
		} else if ("kjfs".equalsIgnoreCase(widthType)) {
			// 从快捷方式进入
			request.setAttribute("widthType", "kjfs");
		} else {
			// 从项目审核进入
			request.setAttribute("widthType", "xmsh");
		}

		// 页面显示记录数
		request.setAttribute("pageSize", myForm.getPages().getPageSize());

		request.setAttribute("kzjb", kzjb);

		return mapping.findForward("rsszManage");
	}

	/**
	 * 学生资助_人数设置_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward rsszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xszz_xgsz_rssz.do";
		// /视图
		String tableName = "view_xszz_comm_xmwh";
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		// 当前学年
		String xn = Base.currXn;
		// 当前学期
		String xq = Base.currXq;
		// 当前年度
		String nd = Base.currNd;
		// 提示信息
		String message = "";
		// 模块类型
		String mklx = (String) session.getAttribute("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "?mklx=" + mklx;
		}
		// =================end==================

		// ===================执行查看操作操作 ======================
		String pk = "xmdm";
		String pkValue = xmdm;
		String[] colList = new String[] { "xmdm", "kzjb", "xmsm", "rssx" };
		HashMap<String, String> rs = service.getXszzInfo(tableName, pk,
				pkValue, colList);

		rs.put("xn", xn);
		rs.put("xq", xq);
		rs.put("nd", nd);
		rs.put("fpfs", "比例");
		// =================end ===================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveRssz(myForm);
			message = resault ? "分配成功" : "分配失败,人数超过项目上限，请确认！";
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "message" };
		String[] qtzdz = new String[] { message };

		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "rssz");
		// =================end ===================

		return mapping.findForward("rsszUpdate");
	}

	/**
	 * 学生资助_项目申请_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 用户名
		String userName = (String) session.getAttribute("userName");
		myForm.setXh(userName);
		// 用户所在部门
		String userDep = (String) session.getAttribute("userDep");
		// 视图名
		String tableName = "";
		// 表名
		String realTable = "xszz_zzxmb";
		// 访问路径
		String path = "yes".equals(myForm.getIskns()) ? "kns_xmsq.do"
				: "xszz_xscz_xmsq.do";
		// 当前学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		// 当前学期
		String xq = Base.currXq;
		myForm.setXq(xq);
		// 当前年度
		String nd = Base.currNd;
		myForm.setNd(nd);
		// 当前时间
		String sqsjCn = service.getNowTime("YYYY年MM月DD日");
		String sqsj = service.getNowTime("YYYYMMDD");
		myForm.setSqsj(sqsj);
		myForm.setSqsjCn(sqsjCn);
		// 提示信息
		String message = "";
		// 错误信息
		String msg = "";
		// 项目列表
		List<HashMap<String, String>> rsList = null;
		// =================end==================

		// ==================登陆用户检测 ==================
		if (!"stu".equalsIgnoreCase(userType)) {
			msg = "本模块只能由学生用户进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================

		// ==================默认展现所有登陆者有资格申请的项目 ==================
		if ("stu".equalsIgnoreCase(userType)) {
			myForm.setXmdm(null);
			// 表头
			List<HashMap<String, String>> topTr = service.getTopTr("xmsq");
			rsList = service.getXmsqList(myForm);

			request.setAttribute("topTr", topTr);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "message", "msg" };
		// 其他字段值
		String[] qtzdz = new String[] { message, msg };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setUserName(userName);
		rForm.setRsList(rsList);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "sqsh");
		// =================end ===================

		return mapping.findForward("xmsqManage");
	}

	/**
	 * 学生资助_项目申请_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xmsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户名
		String userName = (String) session.getAttribute("userName");
		myForm.setXh(userName);
		// 表名
		String realTable = "xszz_zzxmb";
		// 访问路径
		String path = "xszz_xscz_xmsq.do";
		// 当前学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		// 当前学期
		String xq = Base.currXq;
		String xqmc = Base.getDqxqmc();
		myForm.setXq(xq);
		// 当前年度
		String nd = Base.currNd;
		myForm.setNd(nd);
		// 当前时间
		String sqsj = request.getParameter("sqsj");
		sqsj = Base.isNull(sqsj) ? service.getNowTime("YYYYMMDD") : sqsj;
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		xmdm = Base.isNull(xmdm) ? request.getParameter("save_xmdm") : xmdm;
		myForm.setPkValue(xmdm);
		myForm.setXmdm(xmdm);
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 是否已申请
		String ysq = request.getParameter("ysq");
		// =================end==================

		// ===================获得项目相关信息 ======================
		HashMap<String, String> xmInfo = service.getXmxgInfo(myForm);

		// 申请周期
		String sqzq = xmInfo.get("sqzq");
		// 评定时间
		String pdsj = xmInfo.get("pdsj");
		// 评定时间为前次
		if ("前次".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = service.getBeforeXnXqNd(sqzq,
					pdsj, myForm);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);
			nd = befInfo.get("nd");
		}
		xmInfo.put("xn", xn);
		xmInfo.put("xq", xq);
		xmInfo.put("xqmc", xqmc);
		xmInfo.put("nd", nd);
		xmInfo.put("sqsj", sqsj);
		xmInfo.put("xh", userName);
		xmInfo.put("ysq", ysq);

		realTable = xmInfo.get("xmb");
		String mrxm = xmInfo.get("mrxm");
		// ------2010.9.27 lr------
		myForm.setXn(xn);
		myForm.setXq(xq);
		myForm.setNd(nd);
		// --------end------
		myForm.setMrxm(mrxm);
		myForm.setXmb(realTable);

		// 项目内容列表
		List<HashMap<String, Object>> xmnrList = service.getXmSqNrList(myForm);
		// 项目级别列表
		List<HashMap<String, String>> xmfjqkList = service
				.getXmfjqkList(myForm);
		request.setAttribute("xmnrList", xmnrList);
		request.setAttribute("xmfjqkList", xmfjqkList);

		boolean xspjpy = ("5004".equals(xmdm) || "1001".equalsIgnoreCase(xmdm))
				&& Boolean.valueOf(XMLReader.getFlowControl("xszz", "xspjxx"));
		// =================end ===================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {

			// 执行申请操作
			if ("sq".equalsIgnoreCase(mklx)) {

				this.insertOperation(request, realTable);

				// ---------2011.7.6 qph----保存成功了再保存审核状态------------------------
				boolean result = (Boolean) request.getAttribute("result");

				// 保存信息去状态表
				if (result) {
					HashMap<String, String> map = this.getValueMap(request,
							PRIFIX_SAVE);
					service.addZtInfo(map);
				}

			} else if ("xg".equalsIgnoreCase(mklx)) {
				// 执行修改操作
				this.updateOperation(request, realTable);

			}

			boolean knsdl = "yes".equals(myForm.getIskns())
					&& "xszz_knsb".equals(realTable)
					&& Boolean.valueOf(XMLReader
							.getFlowControl("xszz", "knsdl"))
					&& Boolean.valueOf(XMLReader.getFlowControl("xszz",
							"jtqkdc"));
			// 困难生认定作为独立模块，并且困难生认定项目里包含了家庭情况调查
			if (knsdl) {
				service.saveJtqkdcFromKns(myForm.getSave_xh(), myForm
						.getSave_sqsj());
			}

			// 家庭情况
			if ("jtqkdcb".equalsIgnoreCase(realTable) || knsdl) {
				String message = service.saveJtcy(myForm);
				if (!Base.isNull(message)) {
					request.setAttribute("message", message);
				}
			}
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] {};
		String[] qtzdz = new String[] {};

		rForm.setMklx(mklx);
		rForm.setDoType(doType);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		request.setAttribute("xmInfo", xmInfo);
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		request.setAttribute("xspjpy", xspjpy);
		request.setAttribute("jxjList", service.getPjpyInfo("01", userName));
		request.setAttribute("rychList", service.getPjpyInfo("02", userName));
		service.setList(myForm, request, "sqsh");
		// =================end ===================

		return mapping.findForward("xmsqUpdate");
	}


	/**
	 * 学生资助_项目统计_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 用户名
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 用户所在部门
		String userDep = (String) session.getAttribute("userDep");
		myForm.setUserDep(userDep);

		if ("stu".equals(userType)) {
			request.setAttribute("yhInfo", "对不起，您无权访问此页!");
			return new ActionForward("/yhInfo.do", false);
		}

		// 视图名
		String tableName = "";
		// 表名
		String realTable = "xszz_zzxmb";
		// 访问路径
		String path = "yes".equals(myForm.getIskns()) ? "kns_xmsh.do"
				: "xszz_jscz_xmsh.do";
		// 当前学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		// 当前学期
		String xq = Base.currXq;
		myForm.setXq(xq);
		// 当前年度
		String nd = Base.currNd;
		myForm.setNd(nd);
		// 当前时间
		String sqsjCn = service.getNowTime("YYYY年MM月DD日");
		String sqsj = service.getNowTime("YYYYMMDD");
		myForm.setSqsj(sqsj);
		myForm.setSqsjCn(sqsjCn);
		// 提示信息
		String message = "";
		// 错误信息
		String msg = "";
		// 项目列表
		List<HashMap<String, String>> rsList = null;
		// 模块类型
		String mklx = (String) session.getAttribute("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "?mklx=" + mklx;
		}
		// =================end==================

		// ==================登陆用户检测 ==================
		if ("stu".equalsIgnoreCase(userType)) {
			msg = "本模块不能由学生用户进行操作，请确认！";
		} else {
			// 登陆用户类型
			String lx = "";

			if (bzrQx && fdyQx) {// 班主任兼辅导员
				lx = "jd";
			} else if (fdyQx) {// 辅导员
				lx = "fdy";
			} else if (bzrQx) {// 班主任
				lx = "bzr";
			} else if ("xy".equalsIgnoreCase(userType)) {// 学院
				lx = "xy";
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {// 学校用户（管理员）
				lx = "xx";
			}

			myForm.setLx(lx);
		}
		// =================end ===================

		// ==================默认展现所有登陆者有资格申请的项目 ==================
		if (!"stu".equalsIgnoreCase(userType)) {
			// 表头
			List<HashMap<String, String>> topTr = service.getTopTr("xmsh");
			rsList = service.getXmshList(myForm);
			myForm.getPages().setMaxRecord(rsList.size());//
			request.setAttribute("topTr", topTr);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "message", "msg" };
		// 其他字段值
		String[] qtzdz = new String[] { message, msg };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setUserName(userName);
		rForm.setRsList(rsList);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "sqsh");
		// =================end ===================

		return mapping.findForward("xmtjManage");
	}

	/**
	 * 学生资助_项目审核_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 页面宽度控制
		String widthType = request.getAttribute("widthType") != null ? request
				.getAttribute("widthType").toString() : myForm.getWidthType();
		// 用户类型
		String userType = (String) session.getAttribute("userType");

		if ("stu".equalsIgnoreCase(userType)) {
			request.setAttribute("yhInfo", "您没有访问该模块的权限！");
			return new ActionForward("/yhInfo.do", false);
		}

		// 用户名
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 用户所在部门
		String userDep = (String) session.getAttribute("userDep");

		myForm.setUserDep(userDep);
		// 视图名
		String tableName = "view_xszz_comm_xmwh";
		// 表名
		String realTable = "xszz_zzxmb";
		// 访问路径
		String path = "yes".equals(myForm.getIskns()) ? "kns_xmsh.do"
				: "commXszz.do?method=xmshManage";
		// 当前学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		// 当前学期
		String xq = Base.currXq;
		myForm.setXq(xq);
		// 当前年度
		String nd = Base.currNd;
		myForm.setNd(nd);
		// 当前时间
		String sqsjCn = service.getNowTime("YYYY年MM月DD日");
		String sqsj = service.getNowTime("YYYYMMDD");
		myForm.setSqsj(sqsj);
		myForm.setSqsjCn(sqsjCn);
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		xmdm = Base.isNull(xmdm) ? myForm.getXmdm() : xmdm;
		myForm.setXmdm(xmdm);
		myForm.setPkValue(xmdm);
		// 项目信息
		HashMap<String, String> xmInfo = service.getXmwhfo(myForm, tableName);
		// 项目名称
		String xmmc = xmInfo.get("xmmc");
		myForm.setXmmc(xmmc);
		// 项目表
		String xmb = xmInfo.get("xmb");
		myForm.setXmb(xmb);
		// 是否分级
		String sffj = xmInfo.get("sffj");
		// 提示信息
		String message = "";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 是否学院
		boolean isxy = false;
		// 项目列表
		List<HashMap<String, String>> rsList = null;
		// 模块类型
		String mklx = request.getParameter("mklx");
		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "&mklx=" + mklx;
		}
		// =================end==================

		// ==================登陆用户检测 ==================

		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// 学院用户
			myForm.setXydm(userDep);
			isxy = true;
		}

		// 登陆用户类型
		String lx = "";

		if (bzrQx && fdyQx) {// 班主任兼辅导员
			lx = "jd";
		} else if (fdyQx) {// 辅导员
			lx = "fdy";
		} else if (bzrQx) {// 班主任
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// 学院
			lx = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// 学校用户（管理员）
			lx = "xx";
		}

		myForm.setLx(lx);
		// =================end ===================

		// =================初始化 ===================
		if (("".equalsIgnoreCase(doType) || null == doType) && !search
				&& ("".equalsIgnoreCase(xmdm) || null == xmdm)) {
			myForm = service.initXmdm(myForm, "xmsh_xs");
			xmdm = myForm.getXmdm();
			if (xmdm != null && !"".equalsIgnoreCase(xmdm)) {
				myForm.setPkValue(xmdm);
				// 申请周期
				xmInfo = service.getXmwhfo(myForm, tableName);
				sffj = xmInfo.get("sffj");
			} else {
				request.setAttribute("yhInfo", "对不起，暂时没有需要审核的相关项目，请确认!");
				return new ActionForward("/yhInfo.do", false);
			}
			search = true;
		}
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {

			// 表头
			List<HashMap<String, String>> topTr = service.getTopTr("xmsh_xs");
			request.setAttribute("topTr", topTr);

			// 内容
			xmInfo.put("yhzgh", userName);
			rsList = service.getXsShList(myForm, xmInfo);
			// 记录数
			request.setAttribute("rsNum", rsList.size());
		}
		// =================end ===================

		// =================资助分级情况 ===================
		// 项目级别列表
		myForm.setXmdm(xmdm);
		List<HashMap<String, String>> fjList = service.getXmfjqkList(myForm);

		if (fjList != null && fjList.size() > 0) {
			HashMap<String, String> jbMap = new HashMap<String, String>();
			jbMap.put("fjmc", "未指定");
			jbMap.put("fjxxje", "未指定");
			jbMap.put("fjsxje", "未指定");
			jbMap.put("fjqdje", "未指定");
			fjList.add(jbMap);
			request.setAttribute("sffj", "yes");
			request.setAttribute("fjList", fjList);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "message", "xmdm", "xmmc", "isxy",
				"sffj" };
		// 其他字段值
		String[] qtzdz = new String[] { message, xmdm, xmmc,
				String.valueOf(isxy), sffj };

		rForm.setRsList(rsList);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setUserName(userName);
		rForm.setRsList(rsList);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ================当前学年、学期、年度==================
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", service.getXqMc(Base.currXq));
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("sqsjCn", sqsjCn);
		// =======================end========================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "xmsh");
		// =================end ===================

		// 页面显示记录数
		request.setAttribute("mklx", mklx);
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		request.setAttribute("xmshList", service.getXmshList(myForm));

		// 宽度样式设置
		if ("dbsx".equalsIgnoreCase(widthType)) {
			// 从待办事项进入
			request.setAttribute("widthType", "dbsx");
		} else if ("kjfs".equalsIgnoreCase(widthType)) {
			// 从快捷方式进入
			request.setAttribute("widthType", "kjfs");
		} else {
			// 从项目审核进入
			request.setAttribute("widthType", "xmsh");
		}
		return mapping.findForward("xmshManage");
	}

	/**
	 * 学生资助_项目审核_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xmshUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 表名
		String realTable = "xszz_zzxmb";
		// 访问路径
		String path = "xszz_jscz_xmsh.do";
		// 当前学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		// 当前学期
		String xq = Base.currXq;
		String xqmc = Base.getDqxqmc();
		myForm.setXq(xq);
		// 当前年度
		String nd = Base.currNd;
		myForm.setNd(nd);
		// 当前时间
		// String sqsjCn = service.getNowTime("YYYY年MM月DD日");
		String sqsj = request.getParameter("sqsj");
		sqsj = Base.isNull(sqsj) ? service.getNowTime("YYYYMMDD") : sqsj;
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		xmdm = Base.isNull(xmdm) ? request.getParameter("save_xmdm") : xmdm;
		myForm.setPkValue(xmdm);
		myForm.setXmdm(xmdm);
		// 学号
		String xh = request.getParameter("xh");
		xh = Base.isNull(xh) ? xh : xh.trim();
		myForm.setXh(xh);
		// 模块类型
		String mklx = request.getParameter("mklx");
		mklx = Base.isNull(mklx) ? "sh" : mklx;
		// 审核主键
		String shpk = request.getParameter("shpk");
		myForm.setShpk(shpk);
		// 审核状态
		String shzt = request.getParameter("shzt");
		// 模块类型
		String mk = (String) session.getAttribute("mklx");
		myForm.setMklx(mk);
		if (!Base.isNull(mk)) {
			path += "?mklx=" + mk;
		}
		// =================end==================

		// ==================登陆用户检测 ==================
		// 登陆用户类型
		String lx = "";

		if (bzrQx && fdyQx) {// 班主任兼辅导员
			lx = "jd";
		} else if (fdyQx) {// 辅导员
			lx = "fdy";
		} else if (bzrQx) {// 班主任
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// 学院
			lx = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// 学校用户（管理员）
			lx = "xx";
		}

		myForm.setLx(lx);
		// =================end ===================

		// ===================获得项目相关信息 ======================
		HashMap<String, String> xmInfo = service.getXmxgInfo(myForm);
		// 申请周期
		String sqzq = xmInfo.get("sqzq");
		// 评定时间
		String pdsj = xmInfo.get("pdsj");
		// 评定时间为前次
		if ("前次".equalsIgnoreCase(pdsj)) {
			HashMap<String, String> befInfo = service.getBeforeXnXqNd(sqzq,
					pdsj, myForm);
			xn = befInfo.get("xn");
			xq = befInfo.get("xq");
			xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);
			nd = befInfo.get("nd");
		}
		xmInfo.put("xn", xn);
		xmInfo.put("xq", xq);
		xmInfo.put("xqmc", xqmc);
		xmInfo.put("nd", nd);
		xmInfo.put("sqsj", sqsj);
		xmInfo.put("xh", xh);

		realTable = xmInfo.get("xmb");
		myForm.setXmb(realTable);

		// 项目内容列表
		List<HashMap<String, Object>> xmnrList = service.getXmShNrList(myForm,
				xmInfo);
		// 项目级别列表
		myForm.setXmdm(xmdm);
		List<HashMap<String, String>> xmfjqkList = service
				.getXmfjqkList(myForm);

		request.setAttribute("xmnrList", xmnrList);
		request.setAttribute("xmfjqkList", xmfjqkList);

		if (xmfjqkList != null && xmfjqkList.size() > 0) {
			request.setAttribute("fjNum", xmfjqkList.size());

			if (xmfjqkList.size() == 1) {

				HashMap<String, String> map = xmfjqkList.get(0);

				String fjqdje = map.get("fjqdje");
				String fjsxje = map.get("fjsxje");
				String fjxxje = map.get("fjxxje");

				if ("无".equalsIgnoreCase(fjqdje)
						&& "无".equalsIgnoreCase(fjsxje)
						&& "无".equalsIgnoreCase(fjxxje)) {
					request.setAttribute("noJe", "yes");
				}
			}
		}
		// =================end ===================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {

			String message = "";

			if ("tg".equalsIgnoreCase(shzt)) {

				// 判断是否超过人数上限
				if (("是".equals(xmInfo.get("bzrkz")) && bzrQx)
						|| ("是".equals(xmInfo.get("fdykz")) && fdyQx)
						|| ("是".equals(xmInfo.get("xykz"))
								&& "xy".equals(userType) && !fdyQx || ("是"
								.equals(xmInfo.get("xxkz")))
								&& "xx".equals(lx))) {
					message = service.isCgrssx(xmInfo, myForm);
				}

				// 判断是否超过资助上限
				if (Base.isNull(message)) {
					XszzXmtjService xmtjService = new XszzXmtjService();
					message = xmtjService.isCgZzsx(xmInfo, myForm);
				}
			}

			if (Base.isNull(message)) {

				this.updateOperation(request, realTable);

				// 保存信息去状态表
				HashMap<String, String> map = this.getValueMap(request,
						PRIFIX_SAVE);
				service.updateZtInfo(map);
			} else {
				request.setAttribute("message", message);
			}

		} else if ("plsave".equalsIgnoreCase(doType)) {

			realTable = request.getParameter("xmb");

			String message = "";

			if ("tg".equalsIgnoreCase(shzt)) {
				if (("是".equals(xmInfo.get("bzrkz")) && bzrQx)
						|| ("是".equals(xmInfo.get("fdykz")) && fdyQx)
						|| ("是".equals(xmInfo.get("xykz"))
								&& "xy".equals(userType) && !fdyQx || ("是"
								.equals(xmInfo.get("xxkz")))
								&& "xx".equals(lx))) {
					message = service.isCgrssxPl(xmInfo, myForm);
				}

				// 判断是否超过资助上限
				if (Base.isNull(message)) {
					XszzXmtjService xmtjService = new XszzXmtjService();
					message = xmtjService.isCgZzsxPl(xmInfo, myForm);
				}
			}

			// 获得条件信息
			HashMap<String, String> map = this
					.getValueMap(request, PRIFIX_SAVE);

			if (Base.isNull(message)) {
				boolean result = service.savePlsh(myForm, realTable, map);
				message = result ? "操作成功" : "操作失败";

				if (result) {
					service.updateZtInfoPl(map, myForm);
				}
			}

			request.setAttribute("message", message);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "lx", "shpk" };
		String[] qtzdz = new String[] { lx, shpk };

		rForm.setMklx(mklx);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		request.setAttribute("xmInfo", xmInfo);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "sqsh");
		// =================end ===================
		
		String jb=request.getParameter("jb");
		myForm.setSave_xmzzjb(jb);
		if ("plsh".equalsIgnoreCase(doType)
				|| "plsave".equalsIgnoreCase(doType)) {

			return mapping.findForward("xmshPl");
		}

		request.setAttribute("path", "commXszz.do?method=xmshManage");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmshUpdate");
	}

	/**
	 * 学生资助_结果查询_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 用户名
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 用户所在部门
		String userDep = (String) session.getAttribute("userDep");
		myForm.setUserDep(userDep);
		// 视图名
		String tableName = "view_xszz_comm_xmwh";
		// 表名
		String realTable = "xszz_zzxmb";
		// 访问路径
		String path = "yes".equals(myForm.getIskns()) ? "kns_jgcx.do"
				: "xszz_xmxg_jgcx.do";
		// 项目代码
		String xmdm = myForm.getXmdm();
		// 提示信息
		String message = "";
		// 是否学院
		boolean isxy = false;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 项目列表
		List<HashMap<String, String>> rsList = null;
		// 模块类型
		String mklx = (String) session.getAttribute("mklx");
		// 页面宽度
		String widthType = request.getAttribute("widthType") != null ? request
				.getAttribute("widthType").toString() : myForm.getWidthType();

		myForm.setMklx(mklx);
		if (!Base.isNull(mklx)) {
			path += "?mklx=" + mklx;
		}
		// =================end==================

		// ==================登陆用户检测 ==================
		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// 学院用户
			myForm.setXydm(userDep);
			isxy = true;
		} else if ("stu".equalsIgnoreCase(userType)) {
			// 学生用户
			myForm.setXh(userName);
		}

		// 登陆用户类型
		String lx = "";

		if (bzrQx && fdyQx) {// 班主任兼辅导员
			lx = "jd";
		} else if (fdyQx) {// 辅导员
			lx = "fdy";
		} else if (bzrQx) {// 班主任
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// 学院
			lx = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// 学校用户（管理员）
			lx = "xx";
		}

		myForm.setLx(lx);
		// =================end ===================

		// ==================执行删除操作 ==================
		if ("del".equalsIgnoreCase(doType)) {

			boolean result = service.delXmsqInfo(myForm);

			// 如果是家庭情况调查的话 删除相关的学生家属信息 2011.3.28 QLJ
			if ("5001".equalsIgnoreCase(xmdm) && result) {
				result = service.delXsJtcy();
			}
			message = result ? "操作成功" : "操作失败";
		}
		// =================end ===================

		// =================初始化 ===================
		if (("".equalsIgnoreCase(doType) || null == doType) && !search) {
			myForm = service.initXmdm(myForm, "jgcx");
			xmdm = myForm.getXmdm();
			if (xmdm == null || "".equalsIgnoreCase(xmdm)) {
				request.setAttribute("yhInfo", "对不起，暂时没有可以查看的相关项目，请确认!！");
				return new ActionForward("/yhInfo.do", false);
			}

			search = true;

		}
		request.setAttribute("xmdm", xmdm);

		// ==================执行查询操作 ==================
		if (search) {

			xmdm = Base.isNull(xmdm) ? "no" : xmdm;
			// 表头
			List<HashMap<String, String>> topTr = service.getTopTr("jgcx!!@@!!"
					+ xmdm);
			request.setAttribute("topTr", topTr);
			// 内容
			rsList = service.getJgcxList(myForm);

			request.setAttribute("rsNum",
					(rsList != null && rsList.size() > 0) ? rsList.size() : 0);

		}
		// =================end ===================

		// ==================执行同步操作 ==================
		if ("tb".equalsIgnoreCase(doType)) {
			boolean result = service.tbZtInfo();
			message = result ? "同步成功" : "同步失败";

			SynDataService synService = new SynDataService();
			synService.synImpData();
		}

		// =================end ===================
		// 困难生认定表里家庭情况调查数据同步到家庭情况调查表
		if ("jtqkdcTb".equalsIgnoreCase(doType)) {
			boolean result = service.saveJtqkdcFromKns(null, null);
			message = result ? "同步成功" : "同步失败";
		}
		// ------------------end------------------

		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {

			String xxdm = Base.xxdm;

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// 中国地大
				XszzExpService expService = new XszzExpService();
				expService.expInfo(myForm, response.getOutputStream());
			} else if (Globals.XXDM_WHSYFWXY.equalsIgnoreCase(xxdm)) {//武汉商业服务
				XszzExpService expService = new XszzExpService();
				expService.expInfo(myForm, response.getOutputStream());
			} else if (Globals.XXDM_HZNYDX.equalsIgnoreCase(xxdm)) {//华中农业大学
				XszzExpService expService = new XszzExpService();
				expService.expInfo(myForm, response.getOutputStream());
			}else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {//湖州师范学院
				XszzExpService expService = new XszzExpService();
				expService.expInfo(myForm, response.getOutputStream());
			} else {
				service.expInfo(myForm, response.getOutputStream());
			}

			return mapping.findForward("");
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "message", "isxy", "xmjb" };
		// 其他字段值
		String[] qtzdz = new String[] { message, String.valueOf(isxy),
				myForm.getXmzzjb() };

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setUserName(userName);
		rForm.setRsList(rsList);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "jgcx");
		// =================end ===================

		// 宽度样式设置
		if ("dbsx".equalsIgnoreCase(widthType)) {
			// 从待办事项进入
			request.setAttribute("widthType", "dbsx");
		} else if ("kjfs".equalsIgnoreCase(widthType)) {
			// 从快捷方式进入
			request.setAttribute("widthType", "kjfs");
		} else {
			// 从项目审核进入
			request.setAttribute("widthType", "xmsh");
		}

		// 页面显示记录数
		request.setAttribute("pageSize", myForm.getPages().getPageSize());

		return mapping.findForward("jgcxManage");
	}

	/**
	 * 学生资助_结果查询_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jgcxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;
		// 学校代码
		String xxdm = Base.xxdm;
		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// 表名
		String realTable = request.getParameter("xmb");
		realTable = Base.isNull(realTable) ? "xszz_zzxmb" : realTable;
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 用户名
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 访问路径
		String path = "xszz_xmxg_jgcx.do";
		// 当前学年
		String xn = Base.currXn;
		// myForm.setXn(xn);
		// 当前学期
		String xq = Base.currXq;
		String xqmc = Base.getDqxqmc();
		// myForm.setXq(xq);
		// 当前年度
		String nd = Base.currNd;
		// myForm.setNd(nd);
		// 模块类型
		String mklx = "jg";
		myForm.setMklx(mklx);
		// 可以修改
		String canEdit = "no";
		// 主键
		String pk = request.getParameter("pk");
		String[] arr_pk = !Base.isNull(pk) ? pk.split("!!@@!!") : null;
		// 模块类型
		String mk = (String) session.getAttribute("mklx");
		if (!Base.isNull(mk)) {
			path += "?mklx=" + mk;
		}
		// =================end==================

		// ==================登陆用户检测 ==================

		// 登陆用户类型
		String lx = "";

		if (bzrQx && fdyQx) {// 班主任兼辅导员
			lx = "jd";
		} else if (fdyQx) {// 辅导员
			lx = "fdy";
		} else if (bzrQx) {// 班主任
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// 学院
			lx = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// 学校用户（管理员）
			lx = "xx";
		}
		// =================end==================
		if ("fileDel".equalsIgnoreCase(doType)) {
			String pkV = "xh||sqsj";
			String pkValue = myForm.getSave_xh() + myForm.getSave_sqsj();
			if (!Base.isNull(pkValue)) {
				service.fileDel("xszz_knsb", "scdz", pkV, pkValue);
			}
		}
		String filePath = request.getParameter("scdz");
		if ("upload".equalsIgnoreCase(doType)) {
			// 处理文件上传
			FormFile file = myForm.getUploadFile();

			String fName = "";
			if (file != null) {
				String dir = "/upload/dtjs";
				File f = new File(dir);
				if (!f.exists()) {
					f.mkdirs();
				}
				Timestamp date = new Timestamp(System.currentTimeMillis());
				String dateStr = date.toString().substring(0, 19);
				dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
						.replaceAll(":", "");
				int size = file.getFileSize();
				if (size < 10485760 && size != 0) {
					fName = dateStr + file.getFileName();
					InputStream in = file.getInputStream();
					filePath = dir + "/" + fName;
					OutputStream out = new FileOutputStream(filePath);
					int bytesRead = 0;
					byte[] buffer = new byte[size];
					while ((bytesRead = in.read(buffer, 0, size)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
					out.close();
					in.close();
				} else {
					request.setAttribute("alert", "cannot");
				}
				request.setAttribute("message", "上传成功");
			}
		}
		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("save_xh");
			String sqsj = request.getParameter("save_sqsj");
			String xmdm = request.getParameter("save_xmdm");
			myForm.setXh(xh);
			arr_pk = new String[3];
			arr_pk[0] = xh;
			arr_pk[1] = sqsj;
			arr_pk[2] = xmdm;

			String bclx = request.getParameter("bclx");

			if ("add".equalsIgnoreCase(bclx)) {
				if ("xszz_knsb".equals(myForm.getXmb())
						&& Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {
					request.setAttribute("save_scdz", myForm.getScdz());
					request.getParameterMap();
				}
				this.insertOperation(request, realTable);
				// 保存信息去状态表
				HashMap<String, String> map = this.getValueMap(request,
						PRIFIX_SAVE);
				service.addZtInfo(map);
			} else {
				this.updateOperation(request, realTable);
			}

			boolean knsdl = "yes".equals(myForm.getIskns())
					&& "xszz_knsb".equals(realTable)
					&& Boolean.valueOf(XMLReader
							.getFlowControl("xszz", "knsdl"))
					&& Boolean.valueOf(XMLReader.getFlowControl("xszz",
							"jtqkdc"));
			// 困难生认定作为独立模块，并且困难生认定项目里包含了家庭情况调查
			if (knsdl) {
				service.saveJtqkdcFromKns(myForm.getSave_xh(), myForm
						.getSave_sqsj());
			}

			// 家庭情况
			if ("jtqkdcb".equalsIgnoreCase(realTable) || knsdl) {
				String message = service.saveJtcy(myForm);
				if (!Base.isNull(message)) {
					request.setAttribute("message", message);
				}
			}
		}
		// =================end ===================

		// ===================执行添加操作 ======================
		if ("add".equalsIgnoreCase(doType)
				|| "fileDel".equalsIgnoreCase(doType)) {
			String xh = request.getParameter("xh");
			String xmdm = request.getParameter("xmdm");
			String sqsj = service.getNowTime("yyyymmdd");
			
			boolean xspjpy = ("5004".equals(xmdm) || "1001".equalsIgnoreCase(xmdm))
			&& Boolean.valueOf(XMLReader.getFlowControl("xszz", "xspjxx"));
			request.setAttribute("jxjList", service.getPjpyInfo("01", xh));
			request.setAttribute("rychList", service.getPjpyInfo("02", xh));
			request.setAttribute("xspjpy", xspjpy);
			
			myForm.setPkValue(xmdm);

			HashMap<String, String> xmInfo = service.getXmxgInfo(myForm);
			// ------2010.9.27 lr------
			String pdsj = xmInfo.get("pdsj");
			String sqzq = xmInfo.get("sqzq");
			if ("前次".equalsIgnoreCase(pdsj)) {
				myForm.setXn(xn);
				myForm.setXq(xq);
				myForm.setNd(nd);
				HashMap<String, String> befInfo = service.getBeforeXnXqNd(sqzq,
						pdsj, myForm);
				xn = befInfo.get("xn");
				xq = befInfo.get("xq");
				xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);
				nd = befInfo.get("nd");
			}
			// ------end 2010.9.27 lr------

			xmInfo.put("xn", xn);
			xmInfo.put("xq", xq);
			xmInfo.put("xqmc", xqmc);
			xmInfo.put("nd", nd);
			xmInfo.put("sqsj", sqsj);
			xmInfo.put("xh", xh);

			realTable = xmInfo.get("xmb");
			canEdit = "yes";
			myForm.setXn(xn);
			myForm.setXq(xq);
			myForm.setNd(nd);
			myForm.setXmb(realTable);

			// 项目内容列表
			List<HashMap<String, Object>> xmnrList = service
					.getXmSqNrList(myForm);
			// 项目级别列表
			List<HashMap<String, String>> xmfjqkList = service
					.getXmfjqkList(myForm);

			request.setAttribute("xmInfo", xmInfo);
			request.setAttribute("xmnrList", xmnrList);
			request.setAttribute("xmfjqkList", xmfjqkList);
			request.setAttribute("xmdm", xmdm);
		}
		// =================end ===================

		// ===================获得项目相关信息 ======================

		if (arr_pk != null && arr_pk.length >= 3
				&& !"add".equalsIgnoreCase(doType)) {
			String xh = arr_pk[0];
			String sqsj = arr_pk[1];
			String xmdm = arr_pk[2];
			
			boolean xspjpy = ("5004".equals(xmdm) || "1001".equalsIgnoreCase(xmdm))
			&& Boolean.valueOf(XMLReader.getFlowControl("xszz", "xspjxx"));
			request.setAttribute("jxjList", service.getPjpyInfo("01", xh));
			request.setAttribute("rychList", service.getPjpyInfo("02", xh));
			request.setAttribute("xspjpy", xspjpy);
			
			myForm.setXh(xh);
			myForm.setXmdm(xmdm);
			myForm.setPkValue(xmdm);

			HashMap<String, String> xmInfo = service.getXmxgInfo(myForm);
			realTable = xmInfo.get("xmb");
			String shjb = xmInfo.get("shjb");

			myForm.setSqsj(sqsj);
			myForm.setShjb(shjb);
			myForm.setXmb(realTable);
			myForm.setPkValue(xh + sqsj);

			HashMap<String, String> sqInfo = service.getXsSqInfo(myForm,
					realTable);

			myForm.setXn(sqInfo.get("xn"));
			myForm.setXq(sqInfo.get("xq"));
			myForm.setNd(sqInfo.get("nd"));
			xmInfo.putAll(sqInfo);

			// 项目内容列表
			List<HashMap<String, Object>> xmnrList = service.getXmShNrList(
					myForm, xmInfo);
			// 项目级别列表
			List<HashMap<String, String>> xmfjqkList = service
					.getXmfjqkList(myForm);

			// 上传附件
			String file = service.getFile(myForm);

			request.setAttribute("file", file);
			request.setAttribute("xmdm", xmdm);
			request.setAttribute("xmInfo", xmInfo);
			request.setAttribute("xmnrList", xmnrList);
			request.setAttribute("xmfjqkList", xmfjqkList);

			if (xmfjqkList != null && xmfjqkList.size() > 0) {
				request.setAttribute("fjNum", xmfjqkList.size());

				if (xmfjqkList.size() == 1) {

					HashMap<String, String> map = xmfjqkList.get(0);

					String fjqdje = map.get("fjqdje");
					String fjsxje = map.get("fjsxje");
					String fjxxje = map.get("fjxxje");

					if ("无".equalsIgnoreCase(fjqdje)
							&& "无".equalsIgnoreCase(fjsxje)
							&& "无".equalsIgnoreCase(fjxxje)) {
						request.setAttribute("noJe", "yes");
					}
				} else {
					for (int i = 0; i < xmfjqkList.size(); i++) {
						HashMap<String, String> map = xmfjqkList.get(i);
						String fjqdje = map.get("fjqdje");
						String fjsxje = map.get("fjsxje");
						String fjxxje = map.get("fjxxje");

						if ("无".equalsIgnoreCase(fjqdje)
								&& "无".equalsIgnoreCase(fjsxje)
								&& "无".equalsIgnoreCase(fjxxje)) {
							request.setAttribute("noJe", "yes");
							break;
						}
					}
				}
			}

			// 管理员用户或者未审核的数据有修改的功能按钮
			if ("xx".equalsIgnoreCase(lx)
					|| ("update".equalsIgnoreCase(doType) && service
							.canEdit(myForm))) {
				canEdit = "yes";
			}
		}

		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "canEdit" };
		String[] qtzdz = new String[] { canEdit };

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		myForm.setMklx(mk);
		service.setList(myForm, request, "sqsh");
		
		
		// =================end ===================
		return mapping.findForward("jgcxUpdate");
	}

	/**
	 * 学生基本信息管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 用户名
		String userName = (String) session.getAttribute("userName");
		myForm.setZgh(userName);
		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 用户所在部门
		String userDep = (String) session.getAttribute("userDep");
		myForm.setUserDep(userDep);
		// 视图名
		String tableName = "view_xsjbxx";
		// 表名
		String realTable = "";
		// 访问路径
		String path = "";
		// 提示信息
		String message = "";
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 查询结果
		List<HashMap<String, String>> rsList = null;
		List<HashMap<String, String>> topTr = null;
		// =================end==================

		// ==================登陆用户检测 ==================
		// 登陆用户类型
		String lx = "";

		if (bzrQx && fdyQx) {// 班主任兼辅导员
			lx = "jd";
		} else if (fdyQx) {// 辅导员
			lx = "fdy";
		} else if (bzrQx) {// 班主任
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// 学院
			lx = "xy";
			myForm.setXydm(userDep);
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// 学校用户（管理员）
			lx = "xx";
		}

		myForm.setLx(lx);
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {

			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc" };
			// 表头
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			// 内容
			rsList = service.getXsxxList(myForm);

			request.setAttribute("topTr", topTr);
			request.setAttribute("rsNum",
					(rsList != null && rsList.size() > 0) ? rsList.size() : 0);

		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "message", "xmdm" };
		// 其他字段值
		String[] qtzdz = new String[] { message, xmdm };

		rForm.setRsList(rsList);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setUserName(userName);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "xsxx");
		// =================end ===================

		return mapping.findForward("xsxxManage");
	}

	/**
	 * 老师信息管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward lsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 用户所在部门
		String userDep = (String) session.getAttribute("userDep");
		myForm.setUserDep(userDep);
		// 视图名
		String tableName = "view_fdyxx";
		// 表名
		String realTable = "";
		// 访问路径
		String path = "";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 查询结果
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		// =================end==================

		// ==================执行查询操作 ==================
		if (search) {

			// 内容
			String[] colList = new String[] { "zgh", "xm", "bmmc", "zwmc",
					"zzmm", "xl", "yddh", "bgdh" };
			// 表头

			topTr = SearchUtils.getTopTr(tableName, colList, null);

			rs = service.getXszzList(tableName, myForm, colList, "");

			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);

		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserDep(userDep);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "lsxx");
		// =================end ===================

		return mapping.findForward("lsxxManage");
	}

	/**
	 * 学生资助_统计报表
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward tjbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		// ================= 赋初值 ==================
		// 项目代码
		String xmdm = myForm.getXmdm();
		myForm.setXmdm(xmdm);
		// 申请时间
		String sqsj = myForm.getSave_sqsj();
		myForm.setSqsj(sqsj);
		// ================= end ==================

		// ================= 执行统计输出EXCEL ==================
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {// 困难生
			service.printKns(myForm, response.getOutputStream());
		} else if (XszzXmdm.XSZZ_LSTD.equals(xmdm)) {// 绿色通道
			service.printLstd(myForm, response.getOutputStream());
		} else if (XszzXmdm.XSZZ_GJLZJXJ.equals(xmdm)) {// 国家励志奖学
			service.printLzjxj(myForm, response.getOutputStream());
		} else if (XszzXmdm.XSZZ_XFHJ.equals(xmdm)) {// 学生缓交学费
			service.printHjxf(myForm, response.getOutputStream());
		} else if (XszzXmdm.XSZZ_GJZXJ.equals(xmdm)) {
			service.printGjzxj(myForm, response.getOutputStream());
		} else {
			throw new Exception("不好意思，这个统计还没做呢！");
		}
		// ================= end ==================

		return mapping.findForward("");
	}

	/**
	 * 学生资助_统计报表
	 * 
	 * @author lr
	 * @return ActionForward
	 */
	public ActionForward tjbbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;
		if ("xy".equalsIgnoreCase((String)request.getSession().getAttribute("userType")
				)) {
			myForm.setXydm((String)request.getSession().getAttribute("userDep")
					);
		}

		// 报表列表
		request.setAttribute("tjbbList", service.getTjbbList());
		// 年级学院专业班级
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		// 年度学年学期
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("path", "xszz_jscz_tjbb.do");// 路径
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tjbbManage");
	}

	/**
	 * 学生资助_打印统计报表
	 * 
	 * @author lr
	 * @return ActionForward
	 */
	public ActionForward printTjbbManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		response.reset();
		response.setContentType("application/vnd.ms-excel");

		// 打印统计表报
		service.printZztjbb(myForm, response.getOutputStream());

		return mapping.findForward("");
	}

	/**
	 * 学生资助_资助汇总_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzhzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXmtjService service = new XszzXmtjService();
		XszzTyForm myForm = (XszzTyForm) form;
		User user = getUser(request);

		// ================= 赋初值 ==================
		// 用户类型
		String userType = user.getUserType();
		// 用户名
		String userName = user.getUserName();
		// 所在部门
		String userDep = user.getUserDep();
		// 访问路径
		String path = "xszz_xmtj_zzhz.do";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 学校代码
		String xxdm = Base.xxdm;
		// 功能模块
		String gnmk = "xszz";
		// 菜单
		String menu = "xmtj";
		// 当前时间
		String nowTime = service.getNowTime("YYYYMMDD");
		// 开始时间
		String kssj = myForm.getKssj();
		myForm.setKssj(Base.isNull(kssj) ? Base.currNd + "0101" : kssj);
		// 结束时间
		String jssj = myForm.getJssj();
		myForm.setJssj(Base.isNull(jssj) ? nowTime : jssj);
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

		// ==================登陆用户检测 ==================
		if (isxy) {
			// 学院用户
			myForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {
			// 学生用户
			myForm.setXh(userName);
		}

		// 登陆用户类型
		String lx = service.getUserLx(user);
		myForm.setLx(lx);
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {
			topTr = service.getTopTr(xxdm, "zzhz");
			rsArrList = service.getZzhzList(myForm);
		}
		// =================end ===================

		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expZzhzInfo(myForm, response.getOutputStream());

			return mapping.findForward("");
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "isxy" };
		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
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

		return mapping.findForward("zzhzManage");
	}

	/**
	 * 学生资助_资助汇总_详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzhzUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXmtjService service = new XszzXmtjService();
		XsxxglService stuService = new XsxxglService();
		XszzTyForm myForm = (XszzTyForm) form;
		User user = getUser(request);

		// ================= 赋初值 ==================
		// 学号
		String xh = request.getParameter("xh");
		myForm.setXh(xh);
		// 开始时间
		String kssj = request.getParameter("kssj");
		myForm.setKssj(kssj);
		// 结束时间
		String jssj = request.getParameter("jssj");
		myForm.setJssj(jssj);
		// 访问路径
		String path = "xszz_xmtj_zzhz.do";
		// 功能模块
		String gnmk = "xszz";
		// 菜单
		String menu = "xmtj";
		// 提示信息
		String message = "";
		// =================end==================

		// ==================相关信息展示 ==================
		// 学生基本信息
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		// 学生获得资助情况
		List<HashMap<String, String>> rsList = service.getXsZzhzList(myForm);
		int rsNum = (rsList != null && rsList.size() > 0) ? rsList.size() : 0;

		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "kssj", "jssj", "rsNum" };
		// 其他字段值
		String[] qtzdz = new String[] { kssj, jssj, String.valueOf(rsNum) };

		rForm.setRsList(rsList);
		rForm.setRs(stuInfo);
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

		return mapping.findForward("zzhzUpdate");
	}

	/**
	 * 学生资助_项目维护_字段设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zdszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXgszService service = new XszzXgszService();
		XszzTyForm myForm = (XszzTyForm) form;
		User user = getUser(request);

		// ================= 赋初值 ==================
		// 访问路径
		String path = "xszz_xgsz_zdsz.do";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 功能模块
		String gnmk = "xszz";
		// 菜单
		String menu = "zdsz";
		// 提示信息
		String message = "";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;

		// =================end==================

		// ==================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {

		}
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {

		}
		// =================end ===================
		RequestForm rForm = new RequestForm();
		rForm.setMessage(message);
		rForm.setPath(path);

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("zdszManage");
	}

	/**
	 * 附件下载
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downLoadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String pkKey = request.getParameter("pk");
		String pkValue = request.getParameter("pkValue");
		String tableName = request.getParameter("tableName");
		String filePath = DealString.toGBK(request.getParameter("filePath"));
		String fileName = DealString.toGBK(request.getParameter("fileName"));

		if (FileManage.downLoadFile(filePath, fileName, response)) {
			return null;
		} else {
			request.setAttribute("errMsg", "文件不存在或者已删除!");
			FileManage.delFileInfo(tableName, "fileName", "filePath", pkKey,
					pkValue);
			return new ActionForward("/errMsg.do", false);
		}

	}

	/**
	 * 删除附件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String filePath = request.getParameter("filepath");
		String tableName = request.getParameter("tableName");
		String url = request.getParameter("url");
		String pk = "xmdm||xh||sqsj";
		String xmdm = request.getParameter("xmdm");
		String sqsj = request.getParameter("sqsj");
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");

		boolean result = false;

		File f = new File(filePath);
		// 判断附件是否存在，若存在则删除
		if (f.exists()) {
			f.delete();
		}

		result = FileManage.delFileInfo(tableName, "fileName", "filePath", pk,
				pkValue);
		request.setAttribute("message", result ? "操作成功!" : "操作失败!");

		return new ActionForward(new StringBuilder().append(
				"/commXszz.do?method=").append(url).append("&pk=").append(
				pkValue).append("&xdmd=").append(xmdm).append("&sqsj=").append(
				sqsj).append("&xmb=").append(tableName).append("&doType=")
				.append(doType).toString(), false);
	}

	/**
	 * 资助续办管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzxbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzTyForm myForm = (XszzTyForm) form;
		XszzXgszService service = new XszzXgszService();

		// 视图名
		String tableName = "view_xszz_comm_xmwh";
		// 表名
		String realTable = "xszz_zzxmb";
		String xmdm = request.getParameter("xmdm");
		String doType = request.getParameter("doType");

		List<HashMap<String, String>> rsList = null;

		if (!Base.isNull(xmdm)) {
			HashMap<String, String> xmInfo = service.getXmIfno(xmdm);
			request.setAttribute("xmInfo", xmInfo);
		}

		if ("zzxb".equals(doType)) {

			String[] pkValue = request
					.getParameterValues("primarykey_checkVal");
			boolean result = service.saveZzxb(pkValue, myForm);
			request.setAttribute("message", result ? "操作成功!"
					: "操作失败,请检查是否有重复数据!");

			doType = "query";
		}

		if ("del".equalsIgnoreCase(doType)) {

			boolean result = service.delXmsqInfo(myForm);
			request.setAttribute("message", result ? "操作成功" : "操作失败");

			doType = "query";
		}

		if ("query".equals(doType)) {

			xmdm = Base.isNull(xmdm) ? "no" : xmdm;
			// 表头
			List<HashMap<String, String>> topTr = service.getTopTr("zzxb!!@@!!"
					+ xmdm);
			request.setAttribute("topTr", topTr);
			// 内容
			rsList = service.getZzxbList(myForm);

			request.setAttribute("rsNum",
					(rsList != null && rsList.size() > 0) ? rsList.size() : 0);
			request.setAttribute("rsList", rsList);
		}

		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("path", "xszz_zzxb.do");
		FormModleCommon.commonRequestSet(request);
		service.setList(myForm, request, "jgcx");
		return mapping.findForward("zzxbManage");
	}

	/**
	 * 资助续办维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzxbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommService service = new XszzCommService();
		XszzTyForm myForm = (XszzTyForm) form;

		String doType = request.getParameter("doType");
		String pk = request.getParameter("pk");
		String[] tempArr = Base.isNull(pk) ? null : pk.split("!!@@!!");

		if (null != tempArr && tempArr.length == 4) {

			String xh = tempArr[0];
			String sqsj = tempArr[1];
			String xmdm = tempArr[2];
			String xmb = tempArr[3];

			myForm.setXh(xh);
			myForm.setSqsj(sqsj);
			myForm.setXmdm(xmdm);
			myForm.setXmb(xmb);
			myForm.setPkValue(xmdm);

			// 项目相关信息
			HashMap<String, String> xmInfo = service.getXmxgInfo(myForm);
			// 字段
			List<HashMap<String, Object>> xmnrList = service.getXmShNrList(
					myForm, xmInfo);

			myForm.setPkValue(new StringBuilder().append(xh).append(sqsj)
					.toString());

			// 记录详细信息
			HashMap<String, String> zzxbInfo = service.getXsSqInfo(myForm, xmb);

			xmInfo.putAll(zzxbInfo);
			xmInfo.put("xqmc", service.getOneValue("xqdzb", "xqmc", "xqdm",
					xmInfo.get("xq")));
			// 项目级别列表
			myForm.setXmdm(xmdm);

			request.setAttribute("xmnrList", xmnrList);
			request.setAttribute("xmInfo", xmInfo);

			request.setAttribute("xh", xh);
			request.setAttribute("sqsj", sqsj);
			request.setAttribute("xmdm", xmdm);
			request.setAttribute("xmb", xmb);

			request.setAttribute("pkValue", new StringBuilder().append(sqsj)
					.append(xh));
		}

		if ("save".equals(doType)) {
			updateOperation(request, myForm.getXmb());
		}

		// ===================初始化列表值 ======================
		request.setAttribute("pk", pk);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "xszz_zzxb.do");
		FormModleCommon.commonRequestSet(request);
		service.setList(myForm, request, "sqsh");
		// =================end ===================

		return mapping.findForward("zzxbUpdate");
	}

	/**
	 * 导入页面
	 */
	public ActionForward dowDqfbMb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		return mapping.findForward("dowDqfbMb");
	}

	
	/**
	 * 下载地区分布模版
	 */
	@SuppressWarnings("deprecation")
	public ActionForward downloadMb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String doType = request.getParameter("doType");

		if ("dow".equalsIgnoreCase(doType)) {
			String filePath = request.getRealPath("") + "\\xlsDown\\地区分布表.xls";
			String fileName = "地区分布.xls";

			FileManage.downLoadFile(filePath, fileName, response);
		}

		return null;
	}
	
	/**
	 * 地区分布导入
	 */
	@SuppressWarnings("deprecation")
	public ActionForward impDqfbMb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		XszzTyForm model = (XszzTyForm) form;
		XszzPrintService service = new XszzPrintService();
		
		HashMap<String,String> map = FileManage.fileUpload(model.getUploadFile(), request.getRealPath("")+".upload", 10);
		boolean result = service.impZcfInfo(map.get("filePath"), user);
		
		request.setAttribute("message", result ? "导入成功!" : "导入失败!");
		return mapping.findForward("dowDqfbMb");
	}
}