package xsgzgl.xsxx.general.xxxg.xgjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgjgInterface;
import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 學生信息_信息修改_修改結果_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XgjgAction extends BasicAction {

	/**
	 * 查询修改結果
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public ActionForward searchXgjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XgjgModel model = new XgjgModel();
		XgjgInit init = new XgjgInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initXgjgSearch(rForm, myForm, user, request);
		XxxgXgjgInterface service = myService.getXxxgXgjgService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getXgjgTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service
				.getXgjgList(myForm, model, user);
		// 构建结果集
		String spHtml = service.createXgjgHTML(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
}
