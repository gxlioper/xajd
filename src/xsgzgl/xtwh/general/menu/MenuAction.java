package xsgzgl.xtwh.general.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

public class MenuAction extends BasicAction {

	
	/**
	 * 增加快捷方式
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addKjfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MenuModel model = (MenuModel) form;
		MenuService service = new MenuService();
		User user = getUser(request);
		
		boolean result = service.addKjfs(user, model);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(result);
		
		return null;
	}
	
	
	
	/**
	 * 删除快捷方式
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward removeKjfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		MenuModel model = (MenuModel) form;
		MenuService service = new MenuService();
		User user = getUser(request);
		
		boolean result = service.removeKjfs(user, model);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(result);
		
		return null;
	}
	
	
	/**
	 * 查找用户快捷方式
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryKjfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MenuModel model = (MenuModel) form;
		MenuService service = new MenuService();
		User user = getUser(request);
		
		String[] kjfs = service.getKjfs(user, model);
		
		JSONArray result = JSONArray.fromArray(kjfs);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(result);
		
		return null;
	}
}
