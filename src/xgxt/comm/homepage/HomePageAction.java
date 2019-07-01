package xgxt.comm.homepage;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

public class HomePageAction extends BasicAction{

	/**
	 * 获得学生家庭情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getPjgw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session=request.getSession();
		
		String userName=session.getAttribute("userName").toString();
		User user=new User();
		user.setUserName(userName);
		
		String xmdm=request.getParameter("xmdm");
		
		HomePageService service = new HomePageService();

		List<HashMap<String, String>> list = service.getShlcJb(user,xmdm);
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return null;
	}

}
