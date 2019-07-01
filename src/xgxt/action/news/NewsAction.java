package xgxt.action.news;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.CommanForm;

import com.zfsoft.basic.BasicAction;

public class NewsAction extends BasicAction {

	
	/**
	 * 新闻查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward newsQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		NewsService service = new NewsService();
		NewsModel model = new NewsModel();
		CommanForm commanForm = (CommanForm) form;

		BeanUtils.copyProperties(model, commanForm);

		List<HashMap<String, String>> news = service.getNews(model,getUser(request));
		request.setAttribute("news", news);

		request.setAttribute("tagId", model.getTagId());
		request.setAttribute("maxNum", model.getPages().getPageSize());
		return mapping.findForward("newsQuery");
	}
	
	
	/**
	 * 北京联合外网可见新闻
	 */
	public ActionForward getWwNews(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		NewsService service = new NewsService();
		
		List<HashMap<String,String>> news = service.getWwkjNews();
		
		request.setAttribute("news", news);
		return mapping.findForward("getWwNews");
	}
	
}
