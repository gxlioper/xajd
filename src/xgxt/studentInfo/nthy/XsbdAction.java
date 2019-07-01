package xgxt.studentInfo.nthy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.NullStringException;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
/**
 * 学生报到
 */
public class XsbdAction extends BasicAction {
	
	/**
	 * 学生报到
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsbd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		
		XsbdActionForm myForm = (XsbdActionForm) form;
		XsbdService service = new XsbdService();
		
		String tableName = "xg_xsxx_nthy_xsbdb";
		String viewName = "";
		
		String doType = request.getParameter("doType");
		
		/*权限控制*/
		if ("xy".equals(userType)) {
			myForm.setXydm(userDep);
		} 
		if(StringUtils.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(StringUtils.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		//报到
		if (!Base.isNull(doType) && "bd".equalsIgnoreCase(doType)) {
			myForm.setUserName(userName);
			boolean res = service.updateSave(myForm);
			request.setAttribute("result", res);
		}
		//取消报到
		if(!Base.isNull(doType) && "qxbd".equalsIgnoreCase(doType)){
			myForm.setUserName(userName);
			boolean res = service.deleteBdxx(myForm);
			request.setAttribute("result", res);
		}
		//导出
		if (!Base.isNull(doType) && "exp".equalsIgnoreCase(doType)) {
			service.expPageData(request, response, myForm, session);
			return mapping.findForward("");
		}
		
		service.setList(request, "bdzt");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "xsxx_xsbd.do");
		FormModleCommon.commonRequestSet(request);
		
		//查询
		request.setAttribute("rs", service.getList(myForm,session));
		request.setAttribute("topTr", service.getTitle());
		return mapping.findForward("xsbd");
	}
	
	/**
	 * 学生报到未报到原因填写
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NullStringException
	 * @throws SQLException
	 */
	public ActionForward wbdyydetail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws NullStringException, SQLException{
		XsbdActionForm myForm = (XsbdActionForm) form;
		XsbdService service = new XsbdService();
		request.setAttribute("xhstr", myForm.getCb());
		String doType = request.getParameter("doType");
		if(StringUtils.isEqual("save", doType)){
			HttpSession session = request.getSession();
			String userName = session.getAttribute("userName").toString();
			myForm.setUserName(userName);
			boolean res = service.updateSave(myForm);
			request.setAttribute("result", res);
		}
		return mapping.findForward("wbdyydetail");
	}
	
	/**
	 * 查看学生报到信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NullStringException
	 * @throws SQLException
	 */
	public ActionForward xsbddetail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		XsbdService service = new XsbdService();
		XsbdActionForm myForm = (XsbdActionForm) form;
		request.setAttribute("rs", service.viewBdxx(myForm));
		return mapping.findForward("xsbddetail");
	}
	
	/**
	 * 学生报到统计及其导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public ActionForward xsbdtj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, Exception{
		XsbdActionForm myForm = (XsbdActionForm) form;
		XsbdService service = new XsbdService();
		String doType = request.getParameter("doType");
		
		if(StringUtils.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(StringUtils.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		List<String[]> rs = service.getTjList(myForm);
		
		//导出
		if (!Base.isNull(doType) && "exp".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(rs,new String[]{"xymc","zrs","ybdrs","bdbl","wbdrs","wclrs"},new String[]{"学院","总人数","已报到人数","报到比率","未报到人数","未处理人数"}, response.getOutputStream());
			return mapping.findForward("");
		}
		
		//统计
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTjTitle());
		
		service.setList(request, "bdzt");
		request.setAttribute("path", "xsxx_xsbdtj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsbdtj");
	}
	
}

