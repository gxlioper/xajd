package xgxt.pjpy.comm.zhcp.sjdr;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.FileManage;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.rar.CompressFile;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_数据导入_Action类
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

public class ZhcpSjdrAction extends BasicAction {

	/**
	 * 综合测评_数据导入_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjdrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrInit init = new ZhcpSjdrInit();
		User user = getUser(request);// 用户对象
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;

		// ============= 初始化各变量的值 ============
		List<HashMap<String, String>> xmList = service.getXmList(myForm);// 项目列表
		myForm.setXmList(xmList);

		RequestForm rForm = new RequestForm();
		init.getSjdrRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String userType = user.getUserType();// 用户类型
		String userStatus = user.getUserStatus();// 用户身份
		String message = "";// 提示信息
		String[] xn = new String[] { jbszModel.getPjxn() };// 学年
		// 初始化项目列表
		List<HashMap<String, String>> cshXmList = service.getCshXmList(myForm);
		request.setAttribute("cshXmList", cshXmList);
		// =================== end ===================

		// ============= 设置request的值 =============
		myForm.getSearchModel().setSearch_tj_xn(xn);
		request.setAttribute("searchTj", myForm.getSearchModel());
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("sjdrManage");
	}

	/**
	 * 获得综测分相关查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZhcpInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrInit init = new ZhcpSjdrInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		// 操作项目
		String czxm = otherValue[0];
		myForm.setCzxm(czxm);
		// IE版本
		String ie = otherValue[1];
		rsModel.setIe(ie);
		// 来源表
		String lyb = otherValue[2];
		myForm.setLyb(lyb);

		RequestForm rForm = new RequestForm();
		init.getSjdrRForm(rForm, myForm, request);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		ArrayList<String[]> rsArrList = service.getZhfwhList(myForm, user);
		String spHtml = service.getSpHtml(rsModel, myForm, rsArrList);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 保存综合测评分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZhcpf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		User user = getUser(request);// 用户对象

		String[] pk = request.getParameter("pk").split("!!@@!!");
		String[] fs = request.getParameter("fs").split("!!@@!!");

		myForm.setPk(pk);
		myForm.setFs(fs);

		// 执行保存操作
		boolean flag = service.saveZhcpfXgInfo(myForm, user);
		// 提示信息
		String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 同步综合测评分
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tbZhcpf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		User user = getUser(request);// 用户对象

		// 来源表
		String lyb = request.getParameter("lyb");
		// 操作项目
		String czxm = request.getParameter("czxm");

		myForm.setCzxm(czxm);
		myForm.setLyb(lyb);

		// 同步操作
		boolean flag = service.tbZhcpfXgInfo(myForm, user);
		// 提示信息
		String message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
				: MessageInfo.MESSAGE_DO_FALSE + ",可能由于配置数据源有误造成，请联系相关负责人解决";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 显示导出信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrInit init = new ZhcpSjdrInit();

		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();
		init.getSjdrRForm(rForm, myForm, request);

		// 导出形式
		String dcxs = request.getParameter("dcxs");
		myForm.setDcxs(dcxs);

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// 同部门列表
		List<HashMap<String, String>> bmList = service.getExpBmInfo(myForm);
		// 部门数
		int bmNum = (bmList != null && bmList.size() > 0) ? bmList.size() : 0;

		// 提示信息
		String message = "";

		if (bmNum > 0) {
			message += "将要导出" + bmList.size() + "个部门数据";
			message += "(";
			for (int i = 0; i < bmList.size(); i++) {
				if (i == 2) {
					break;
				} else if (i != 0) {
					message += ",";
				}
				message += bmList.get(i).get("bmmc");
			}
			if (bmNum != 0) {
				message += "等";
			}
			message += ")";
		} else {
			message = "false";
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 导出excel
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expToExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrInit init = new ZhcpSjdrInit();

		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();
		init.getSjdrRForm(rForm, myForm, request);

		// 导出形式
		String dcxs = request.getParameter("dcxs");
		myForm.setDcxs(dcxs);

		// 保存路径
		String filePath = request.getParameter("filePath");
		myForm.setFilePath(filePath);

		// 操作项目
		String czxm = request.getParameter("czxm");
		myForm.setCzxm(czxm);
		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// 同部门列表
		List<HashMap<String, String>> bmList = service.getExpBmInfo(myForm);
		myForm.setBmList(bmList);
		myForm.setFilePath(FileManage.getRootPath(request, "zhcp"));

		boolean result = service.expZcfmbToExcel(myForm, user);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(result ? "模版已经生成,请下载您需要的模版!" : "模版生成失败!");

		return null;
	}

	/**
	 * 综合测评_数据导入_文件上传
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjdrImp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrInit init = new ZhcpSjdrInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		init.getSjdrRForm(rForm, myForm, request);
		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String userType = user.getUserType();// 用户类型
		String userStatus = user.getUserStatus();// 用户身份
		String showMessage = "";// 提示信息

		init.getSjdrRForm(rForm, myForm, request);
		// =================== end ===================

		// ============= 执行上传操作 ============
		if ("imp".equalsIgnoreCase(doType)) {

			String filePath = service.upLoadFile(request, myForm
					.getImpFilePath(), "comm");

			showMessage = (String) request.getAttribute("message");

			if (Base.isNull(showMessage)) {

				showMessage = service.impZcfInfo(myForm, filePath, user);
				showMessage = Base.isNull(showMessage) ? "导入数据成功,可以导入下一份"
						: showMessage;
			}
		}
		// ============= 执行上传操作 end ============

		request.setAttribute("showMessage", showMessage);

		return mapping.findForward("sjdrImp");
	}

	/**
	 * 模版管理页面
	 */
	public ActionForward sjdrExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String czxm = request.getParameter("czxm");
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrForm model = (ZhcpSjdrForm) form;
		User user = getUser(request);// 用户对象
		String[] topTr = new String[] { "模版名称", "模版生成时间" };

		if (StringUtils.isNull(model.getBmlx())){
			model.setBmlx("bj");
		}
		
		List<String[]> result = service.getStencilLog(user, model);
		request.setAttribute("rs", result);

		request.setAttribute("topTr", topTr);
		request.setAttribute("czxm", czxm);
		return mapping.findForward("sjdrExp");
	}

	/**
	 * 下载模版
	 */
	public ActionForward downloadStencil(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String[] fileNames = request.getParameterValues("primarykey_cbv");

		if (null != fileNames && fileNames.length > 0) {

			String filePath = FileManage.getRootPath(request, "zhcp");
			String path = "\\";
			if (System.getProperty("os.name").equalsIgnoreCase("Linux")){
				path = "/";
			}
			for (int i = 0; i < fileNames.length; i++) {
				fileNames[i] = filePath + path + fileNames[i]+".xls";
			}
			
			//创建压缩文件
			CompressFile.getInstance().zip(fileNames, filePath+path+"temp.zip");
			//下载
			byte b[] = new byte[500];
			File file = new File(filePath + "/temp.zip");
			if (null != file && file.exists()) {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=xgxt_zhcp.zip");
				InputStream in = new FileInputStream(file);
				in = new BufferedInputStream(in);
				while ((in.read(b)) != -1) {
					response.getOutputStream().write(b);
				}
				in.close();
				return  null;
			} else {
				request.setAttribute("message", "文件不存在或被删除，请联系管理员");
				return new ActionForward("/prompt.do",false);
			}
		}
		return null;
	}
}
