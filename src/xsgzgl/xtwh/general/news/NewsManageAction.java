package xsgzgl.xtwh.general.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.SearchUtil;

public class NewsManageAction extends BasicAction {

	public ActionForward newsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageService service = new NewsManageService();
		NewsManageForm myForm = (NewsManageForm) form;
		User user = getUser(request);

		NewsManageInit init = new NewsManageInit();
		RequestForm rForm = new RequestForm();
		init.initSearch(rForm, myForm, user, request);
		service.setRequestValue(rForm, request);
		List<HashMap<String, String>> typeList = service.getNewsListType();
		request.setAttribute("typeList", typeList);
		return mapping.findForward("newsManage");
	}

	/** 
	 * 查询范围
	 */
	public ActionForward selectQx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String toWho = request.getParameter("toWho");
		String toWhoIndex = request.getParameter("toWhoIndex");
		String path = "newselectxy.do"; // 查询范围（学院）
		if("some_stu".equals(toWho)){
			path = "newselectbj.do"; // 查询范围（班级）
		}
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		CommService cs = new CommService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath(path);
			myForm.setSearchModel(searchModel);
			//SearchUtil.saveSearch(searchModel, user);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("searchTj", SearchUtil.getSearchForRequest(request));
		request.setAttribute("path", path);
		request.setAttribute("toWho", toWho);
		request.setAttribute("toWhoIndex", toWhoIndex);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("selectqx");
	}

	/**
	 * 新闻预览
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageService service = new NewsManageService();
		service.newsInfo(request);

		return mapping.findForward("newsInfo");
	}

	/**
	 * 首页加载新闻更多
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newsMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageService service = new NewsManageService();
		NewsManageForm model = (NewsManageForm) form;
		User user = getUser(request);

		List<HashMap<String, String>> newsList = service.getNewsPageList(model,
				user);
		List<HashMap<String, String>> typeList = service.getNewsListType();
		request.setAttribute("typeList", typeList);
		request.setAttribute("newsList", newsList);
		return mapping.findForward("newsMore");
	}
	/**
	 * 
	 * @描述:已阅读人员列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-25 下午12:01:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward yydmdView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageService service = new NewsManageService();
		NewsManageForm model = (NewsManageForm) form;
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getYydryList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("yydryView");
	}
}
