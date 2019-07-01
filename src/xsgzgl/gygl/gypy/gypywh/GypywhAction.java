package xsgzgl.gygl.gypy.gypywh;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;
/**
 * 公寓管理-公寓评优-公寓评优管理
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class GypywhAction extends BasicExtendAction{

	/**
	 * 公寓评优查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gypyCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GypywhService service = new GypywhService();
		HashMap<String,String> rs = new HashMap<String,String>();
		User user = getUser(request);
		String userType=user.getUserType();
		
		rs.put("xn", Base.currXn);
		rs.put("xqdm", Base.currXq);
		
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("pylbList", service.getPylbList());
		request.setAttribute("rs", rs);
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xqmc(new String[]{service.getXqmc(Base.currXq)});
		// write和titile
		if("stu".equalsIgnoreCase(userType)){
			setWriteAbleAndTitle(request, "gygl_gypygl_gypycx.do");
		}else{
			setWriteAbleAndTitle(request, "gygl_gypygl_gypywh.do");
		}
		searchModel.setPath("gygl_gypygl_gypywh.do");
		request.setAttribute("searchTj", searchModel);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_gygl_pyxxwhb");
		request.setAttribute("userType", userType);
		return mapping.findForward("gypyCx");
	}
	
	
	/**
	 * 公寓评优增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pyqsCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GypywhService service = new GypywhService();
		HashMap<String,String> rs = new HashMap<String,String>();
		String xn = request.getParameter("xn");
		String xqdm = request.getParameter("xqdm");
		String pylbdm = request.getParameter("pylbdm");
		String pydx = request.getParameter("pydx");
		String pysj = request.getParameter("pysj");
		rs.put("xn", xn);
		rs.put("xqdm", xqdm);
		rs.put("pylbdm", pylbdm);
		rs.put("pydx", pydx);
		rs.put("pysj", pysj);
		rs.put("xqmc", service.getXqmc(xqdm));
		rs.put("pylbmc", service.getPylbmc(pylbdm));
		request.setAttribute("rs", rs);
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		if(pydx.equals("0")) {
			rForm.setPath("gygl_gypywh.do?method=pyldCx");
		}else {
			rForm.setPath("gygl_gypywh.do?method=pyqsCx");
		}
		// write和titile
		setWriteAbleAndTitle(request, "gygl_gypygl_gypywh.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("pyqsCx");
	}
}
