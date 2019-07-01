package xsgzgl.xtwh.wdgz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

import common.Globals;


/**
 * <p>我的工作Action<p>
 * 
 * @author Penghui.Qu 2013-1-7
 */
public class MyJobsAction extends BasicAction{

	
	/**
	 * 我的工作查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryWdgz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		MyJobsForm model = (MyJobsForm) form;
		MyJobsService service = new MyJobsService();
		
		List<HashMap<String,String>> wdgzList = service.getWdgzList(model,user);
		JSONArray result = JSONArray.fromObject(wdgzList); //转为json格式
		response.setContentType("text/html;charset=gbk"); //ajax请求返回数据转码，否则会中文乱码
		response.getWriter().print(result);
		
		return null;
	}
	
	public ActionForward  queryWdgzForSystem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName=request.getParameter("userName");
		String userType=request.getParameter("userType");
		session.setAttribute("userName", userName);
		String[] userMsg=FormModleCommon.getUserType(userName,userType);
		session.setAttribute("userType", userMsg[1]);
		session.setAttribute("fdyQx", Fdypd.fdybjck(userName));
		session.setAttribute("bzrQx", Fdypd.bzrbjck(userName));
		User user =getUser(request);
		user.setUserDep(userMsg[0]);
		MyJobsForm model = (MyJobsForm) form;
		MyJobsService service = new MyJobsService();
		model.getPages().setPageSize(5);
		List<HashMap<String,String>> wdgzList = service.getWdgzList(model,user);
		JSONArray result = JSONArray.fromObject(wdgzList); //转为json格式
		response.setContentType("text/html;charset=gbk"); //ajax请求返回数据转码，否则会中文乱码
		response.getWriter().print(result);
	
		return null;
	}
	
	
	
	
	/**
	 * 我的工作申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryWdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		MyJobsForm model = (MyJobsForm) form;
		MyJobsService service = new MyJobsService();
		
		List<HashMap<String,String>> wdsqList = service.getWdsqList(model,user);
		JSONArray result = JSONArray.fromObject(wdsqList); //转为json格式
		response.setContentType("text/html;charset=gbk"); //ajax请求返回数据转码，否则会中文乱码
		response.getWriter().print(result);
		
		return null;
	}
	
	
	
	/**
	 * 我的工作之更多
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMoreJobs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		MyJobsForm model = (MyJobsForm) form;
		MyJobsService service = new MyJobsService();
		
		List<HashMap<String,String>> jobsList = null;
		
		if ("stu".equals(user.getUserType())){
			jobsList = service.getWdsqList(model,user);
		} else {
			jobsList = service.getWdgzList(model,user);
		}
		
		request.setAttribute("jobsList", jobsList);
		
		return mapping.findForward("jobsMore");
	}
	
}
