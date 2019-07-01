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
	 * ��ѯ��Χ
	 */
	public ActionForward selectQx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String toWho = request.getParameter("toWho");
		String toWhoIndex = request.getParameter("toWhoIndex");
		String path = "newselectxy.do"; // ��ѯ��Χ��ѧԺ��
		if("some_stu".equals(toWho)){
			path = "newselectbj.do"; // ��ѯ��Χ���༶��
		}
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		CommService cs = new CommService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath(path);
			myForm.setSearchModel(searchModel);
			//SearchUtil.saveSearch(searchModel, user);
			// ==================�߼���ѯ��� end========================
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
	 * ����Ԥ��
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
	 * ��ҳ�������Ÿ���
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
	 * @����:���Ķ���Ա�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-25 ����12:01:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
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
