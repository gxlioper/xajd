package xsgzgl.gygl.rcjc.qszf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-9 下午14:19:22
 * </p>
 */

public class QszfAjax extends BasicAction {
	/**
	 * 寝室走访首页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszfCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QszfService service = new QszfService();
		QszfForm myForm = (QszfForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyglnew_rcjc_qszf.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getQszfCx(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}

	/**
	 * 获取所有走访老师
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZfls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QszfService service = new QszfService();
		QszfForm myForm = (QszfForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyglnew_rcjc_qszf.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr2();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getZfls(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTML2(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}

	/**
	 * 级联查询-由走访老师职工号获取负责班级
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getFzbjForZflsgh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String zflsgh = request.getParameter("zflsgh");
		QszfService service = new QszfService();
		List<HashMap<String, String>> list = service.getFzbjForZflsgh(zflsgh);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}

	/**
	 * 级联查询-由走访老师职工号获取走访老师姓名
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZflsxmForZflsgh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		QszfForm qszfForm = (QszfForm) form;
		String zflsgh = request.getParameter("zflsgh");
		QszfService service = new QszfService();
		HashMap<String, String> zflsxmRs = service.getZflsxmForZflsgh(qszfForm,zflsgh);
		String zflsxm = zflsxmRs.get("xm");
		if (zflsxm == null || zflsxm == "") {
			zflsxm = "";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(zflsxm);
		return null;
	}

	/**
	 * 级联查询-由走访老师负责班级获取楼栋
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getLdForFzbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String fzbj = request.getParameter("fzbj");
		QszfService service = new QszfService();
		List<HashMap<String, String>> list = service.getLdForFzbj(fzbj);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}

	/**
	 * 级联查询-由楼栋号获取寝室号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQshForLd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		String fzbj = request.getParameter("fzbj");
		String xsszld = request.getParameter("xsszld");
		QszfService service = new QszfService();
		List<HashMap<String, String>> list = service.getQshForLd(fzbj, xsszld);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.getWriter().write(jsonArr.toString());
		return null;
	}

	/**
	 * 寝室走访信息保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		QszfService service = new QszfService();
		String message = "";
		boolean flag = false;
		QszfForm myForm = (QszfForm) form;
		User user = getUser(request);
		String zflsgh = request.getParameter("zflsgh");
		String fzbj = request.getParameter("fzbj");
		String xsszld = request.getParameter("xsszld");
		String xsszqsh = request.getParameter("xsszqsh");
		String xqsj = request.getParameter("xqsj");
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String username = user.getUserName();
		myForm.setZflsgh(zflsgh);
		myForm.setFzbj(fzbj);
		myForm.setXsszld(xsszld);
		myForm.setXsszqsh(xsszqsh);
		myForm.setXqsj(xqsj);
		myForm.setBz(bz);
		flag = service.qszfBc(myForm, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * 寝室走访信息修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszfXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		QszfService service = new QszfService();
		String message = "";
		boolean flag = false;
		QszfForm myForm = (QszfForm) form;
		User user = getUser(request);
		String zflsgh = request.getParameter("zflsgh");
		String fzbj = request.getParameter("fzbj");
		String xsszld = request.getParameter("xsszld");
		String xsszqsh = request.getParameter("xsszqsh");
		String fzbjBefore = request.getParameter("fzbjxgq");
		String xsszldBefore = request.getParameter("xsszldxgq");
		String xsszqshBefore = request.getParameter("xsszqshxgq");
		String xqsj = request.getParameter("xqsj");
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String username = user.getUserName();
		myForm.setZflsgh(zflsgh);
		myForm.setFzbj(fzbj);
		myForm.setXsszld(xsszld);
		myForm.setXsszqsh(xsszqsh);
		myForm.setBjBefore(fzbjBefore);
		myForm.setXsszldBefore(xsszldBefore);
		myForm.setXsszqsBefore(xsszqshBefore);
		myForm.setXqsj(xqsj);
		myForm.setBz(bz);

		flag = service.qszfXg(myForm, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * 下寝次数统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xqcsTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QszfForm myForm = (QszfForm) form;
		QszfService service = new QszfService();
		User user = getUser(request);// 用户对象
		myForm.setUserName(user.getUserName());
		String xn = request.getParameter("xn");
		if(!StringUtil.isNull(xn)){
			xn = xn.substring(0, 4);
		}
		String yf = request.getParameter("yf");
		String xymc = request.getParameter("xymc");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expXqtjb(myForm, response.getOutputStream(), xn, yf, xymc);
		return null;
	}
}