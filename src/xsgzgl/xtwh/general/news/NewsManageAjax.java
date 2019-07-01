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

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.SearchUtil;

/**
 * 新闻管理异步类
 */
public class NewsManageAjax extends SuperAction {
	// =========== 初始化 begin ============
	private List<HashMap<String, String>> radioList = new ArrayList<HashMap<String, String>>();
	{
		addRadioList("全校", "师生", "teastu", radioList);
		addRadioList("教师", "教师", "tea", radioList);
		addRadioList("学生", "学生", "stu", radioList);
	}

	private void addRadioList(String pName, String cName, String toWho, List<HashMap<String, String>> radioList){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pName", pName);
		map.put("cName", cName);
		map.put("toWho", toWho);
		radioList.add(map);
	}
	// =========== 初始化 end ============
	/**
	 * 新闻管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newsSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成方法存根
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		SearchRsModel rsModel = new SearchRsModel();

		HashMap<String, String> valueMap = service.getValueMap(request,
				new String[] {});

		// 传递参数
		service.getModelValue(myForm, request);

		// IE版本
		String ie = valueMap.get("ie");
		rsModel.setIe(ie);

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// 表头
		List<HashMap<String, String>> topTr = service.getNewsTop();
		// 结果集
		ArrayList<String[]> rs = (ArrayList<String[]>) service
				.getNewsInfo(myForm);

		// 构建结果集
		String spHtml = "";
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rs);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.myCreateRs(rsModel, myForm.getPages(), response);

		return null;
	}

	/**
	 * 新闻删除
	 * 
	 * @date 2013-01-09
	 * @author MJ
	 */
	@SystemLog(description="访问系统维护-系统设置-通知公告-删除VALUES:{array_primarykey_checkVal}")
	public ActionForward newsDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		// 参数赋值
		service.getModelValue(myForm, request);

		// 消息信息
		String message = "";

		boolean flag = false;

		flag = service.delNews(myForm);

		message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 新闻发布
	 * 
	 * @date 2013-01-09
	 * @author MJ
	 */
	public ActionForward newsFb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		// 参数赋值
		service.getModelValue(myForm, request);

		// 消息信息
		String message = "";

		boolean flag = false;

		flag = service.newsFb(myForm);

		message = flag ? "发布成功" : "发布失败";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;

	}

	/**
	 * 新闻取消发布
	 * 
	 * @date 2013-01-09
	 * @author MJ
	 */
	public ActionForward newsQxfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		// 参数赋值
		service.getModelValue(myForm, request);

		// 消息信息
		String message = "";

		boolean flag = false;

		flag = service.newsQxfb(myForm);

		message = flag ? "取消发布成功" : "取消发布失败";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;

	}

	/**
	 * 新闻置顶
	 * 
	 * @date 2013-01-09
	 * @author MJ
	 */
	public ActionForward newsZd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		// 参数赋值
		service.getModelValue(myForm, request);

		// 消息信息
		String message = "";

		boolean flag = false;

		flag = service.newsZd(myForm);

		message = flag ? "置顶成功" : "置顶失败";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;

	}

	/**
	 * 新闻取消置顶
	 * 
	 * @date 2013-01-09
	 * @author MJ
	 */
	public ActionForward newsQxzd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();
		// 参数赋值
		service.getModelValue(myForm, request);

		// 消息信息
		String message = "";

		boolean flag = false;

		flag = service.newsQxzd(myForm);

		message = flag ? "取消置顶成功" : "取消置顶失败";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;

	}

	/**
	 * 新闻修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageService service = new NewsManageService();
		NewsManageForm myForm = (NewsManageForm) form;
		//myForm.setTypeid(service.getTypeId(myForm));
		List<HashMap<String, String>> typeList = service.getNewsListType();
		RequestForm rForm = new RequestForm();
		service.setRequestValue(rForm, request);
		NewQxfwForm nqf = SearchUtil.getInstance().getNewQxfwForm(
				request.getParameter("newsId"));
		request.setAttribute("search", nqf);
		request.setAttribute("searchTj", nqf.getSearchModel());
		request.setAttribute("selectTj", SearchUtil.getInstance().getSelectTj(
				nqf)); 
		request.setAttribute("typeList", typeList);
		request.setAttribute("radioList", radioList);
		service.newsUpdate(request);
		myForm.setTypeid(request.getAttribute("typeid").toString());
		return mapping.findForward("newsUpdate");
	}

	/**
	 * 添加新闻
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward newsAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageService service = new NewsManageService();
		NewsManageForm myForm = (NewsManageForm) form;
		List<HashMap<String, String>> typeList = service.getNewsListType();
		RequestForm rForm = new RequestForm();
		service.setRequestValue(rForm, request);
		request.setAttribute("typeList", typeList);
		request.setAttribute("radioList", radioList);
		return mapping.findForward("newsAdd");
	}

	/**
	 * 保存新闻
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问系统维护-系统设置-通知公告-增加或修改NEWSTITLE:{newsTitle},EDITORID:{editorid},NEWSID:{newsId}")
	public ActionForward saveNews(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NewsManageService service = new NewsManageService();
		String doType = request.getParameter("dotype");
		List<HashMap<String, String>> typeList = service.getNewsListType();
		request.setAttribute("typeList", typeList);
		request.setAttribute("radioList", radioList);
		boolean b = true;
		if (doType.equalsIgnoreCase("update")) {
			b = service.updateNews(request);

//			return mapping.findForward("newsUpdate");
		} else {
			b = service.saveNews(request);
//			return mapping.findForward("newsAdd");
		}
		String messageKey = b ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 首页加载新闻列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getNewsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);
		NewsManageForm myForm = (NewsManageForm) form;
		NewsManageService service = new NewsManageService();

		List<HashMap<String, String>> result = service
				.getNewsPageList(myForm, user);
		JSONArray resultList = JSONArray.fromObject(result); // 转为json格式
		response.setContentType("text/html;charset=gbk"); // ajax请求返回数据转码，否则会中文乱码
		response.getWriter().print(resultList);

		return null;
	}

	public ActionForward getNewsListType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		NewsManageService service = new NewsManageService();

		List<HashMap<String, String>> result = service.getNewsListType();
		JSONArray resultList = JSONArray.fromObject(result); // 转为json格式
		response.setContentType("text/html;charset=gbk"); // ajax请求返回数据转码，否则会中文乱码
		response.getWriter().print(resultList);

		return null;
	}
}
