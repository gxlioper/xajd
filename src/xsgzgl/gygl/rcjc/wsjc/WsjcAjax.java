package xsgzgl.gygl.rcjc.wsjc;

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

public class WsjcAjax extends BasicAction {
	
	/**
	 * 卫生检查信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wsjcCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsjcService service = new WsjcService();
		WsjcForm myForm = (WsjcForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyglnew_rcjc_wsjc.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getWsjcCx(myForm);
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
	 * 级联查询-获取所有楼栋号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAllLd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsjcService service = new WsjcService();
		List<HashMap<String, String>> list = service.getAllLd();
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
	public ActionForward getQsForLd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lddm = request.getParameter("ld");
		WsjcService service = new WsjcService();
		List<HashMap<String, String>> list = service.getQsForLd(lddm);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}

	/**
	 * 级联查询-有楼栋号和寝室号获取其他信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lddm = request.getParameter("ld");
		String qsh = request.getParameter("qsh");
		WsjcService service = new WsjcService();
		List<HashMap<String, String>> list = service.getInfo(lddm, qsh);
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		response.setContentType("text/html;charset=gbk");
		response.getWriter().write(jsonArr.toString());
		return null;
	}

	/**
	 * 卫生检查信息保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsjcService service = new WsjcService();
		String message = "";
		boolean flag = false;
		WsjcForm myForm = (WsjcForm) form;
		User user = getUser(request);
		String wsdjbz = service.unicode2Gbk(request.getParameter("wsdjbz"));
		String dgldqbz = service.unicode2Gbk(request.getParameter("dgldqbz"));
		String jcry = service.unicode2Gbk(request.getParameter("jcry"));
		String jcsj = request.getParameter("jcsj");
		String ld = request.getParameter("ld");
		String qs = request.getParameter("qs");
		String dgldq = service.unicode2Gbk(request.getParameter("dgldq"));
		String wsdj = service.unicode2Gbk(request.getParameter("wsdj"));

		String username = user.getUserName();
		myForm.setWsdjbz(wsdjbz);
		myForm.setDgldqbz(dgldqbz);
		myForm.setJcry(jcry);
		myForm.setJcsj(jcsj);
		myForm.setLd(ld);
		myForm.setQs(qs);
		myForm.setDgldq(dgldq);
		myForm.setWsdj(wsdj);

		flag = service.wsjcBc(myForm, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * 卫生检查信息修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wsjcXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WsjcService service = new WsjcService();
		String message = "";
		boolean flag = false;
		WsjcForm myForm = (WsjcForm) form;
		User user = getUser(request);

		String wsdjbz = service.unicode2Gbk(request.getParameter("wsdjbz"));
		String dgldqbz = service.unicode2Gbk(request.getParameter("dgldqbz"));
		String jcry = service.unicode2Gbk(request.getParameter("jcry"));
		String jcsj = request.getParameter("jcsj");
		String ld = request.getParameter("ld");
		String qs = request.getParameter("qs");
		String dgldq = service.unicode2Gbk(request.getParameter("dgldq"));
		String wsdj = service.unicode2Gbk(request.getParameter("wsdj"));

		String username = user.getUserName();
		myForm.setWsdjbz(wsdjbz);
		myForm.setDgldqbz(dgldqbz);
		myForm.setJcry(jcry);
		myForm.setJcsj(jcsj);
		myForm.setLd(ld);
		myForm.setQs(qs);
		myForm.setDgldq(dgldq);
		myForm.setWsdj(wsdj);

		flag = service.wsjcXg(myForm, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}