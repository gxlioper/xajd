package xgxt.xtwh.zfoa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

public class ReturnAction extends BasicAction {
	public ActionForward xzzqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userName = (String)request.getSession().getAttribute("userName");
		String returl = "missionManager.do?theAction="+request.getParameter("OAForward");
		GetUrl getUrl=new GetUrl();
		String url = getUrl.getUrl(userName, returl);
		response.getWriter().write("<script>window.location.href('"+url+"')</script>"); 

		return null;
	}
}
