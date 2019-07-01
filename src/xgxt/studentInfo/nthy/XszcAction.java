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
 * ѧ��ע��
 */
public class XszcAction extends BasicAction {
	
	/**
	 * ѧ��ע��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszc(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		
		XszcActionForm myForm = (XszcActionForm) form;
		XszcService service = new XszcService();
		
		String tableName = "xg_xsxx_nthy_xszcb";
		String viewName = "";
		
		String doType = request.getParameter("doType");
		
		/*Ȩ�޿���*/
		if ("xy".equals(userType)) {
			myForm.setXydm(userDep);
		} 
		if(StringUtils.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(StringUtils.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		//ע��
		if (!Base.isNull(doType) && "zc".equalsIgnoreCase(doType)) {
			myForm.setUserName(userName);
			boolean res = service.updateSave(myForm);
			request.setAttribute("result", res);
		}
		//ȡ��ע��
		if(!Base.isNull(doType) && "qxzc".equalsIgnoreCase(doType)){
			myForm.setUserName(userName);
			boolean res = service.deleteZcxx(myForm);
			request.setAttribute("result", res);
		}
		//����
		if (!Base.isNull(doType) && "exp".equalsIgnoreCase(doType)) {
			service.expPageData(request, response, myForm, session);
			return mapping.findForward("");
		}
		
		//��ѯ
		request.setAttribute("rs", service.getList(myForm,session));
		request.setAttribute("topTr", service.getTitle());
		
		service.setList(request, "zczt");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "xsxx_xszc.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszc");
	}
	
	/**
	 * ѧ��ע��δע��ԭ����д
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NullStringException
	 * @throws SQLException
	 */
	public ActionForward wzcyydetail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws NullStringException, SQLException{
		XszcActionForm myForm = (XszcActionForm) form;
		XszcService service = new XszcService();
		request.setAttribute("xhstr", myForm.getCb());
		String doType = request.getParameter("doType");
		if(StringUtils.isEqual("save", doType)){
			HttpSession session = request.getSession();
			String userName = session.getAttribute("userName").toString();
			myForm.setUserName(userName);
			boolean res = service.updateSave(myForm);
			request.setAttribute("result", res);
		}
		return mapping.findForward("wzcyydetail");
	}
	
	/**
	 * �鿴ѧ��ע����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NullStringException
	 * @throws SQLException
	 */
	public ActionForward xszcdetail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		XszcService service = new XszcService();
		XszcActionForm myForm = (XszcActionForm) form;
		request.setAttribute("rs", service.viewZcxx(myForm));
		return mapping.findForward("xszcdetail");
	}
	
	/**
	 * ѧ��ע��ͳ�Ƽ��䵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public ActionForward xszctj(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, Exception{
		XszcActionForm myForm = (XszcActionForm) form;
		XszcService service = new XszcService();
		String doType = request.getParameter("doType");
		
		if(StringUtils.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(StringUtils.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		List<String[]> rs = service.getTjList(myForm);
		
		//����
		if (!Base.isNull(doType) && "exp".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(rs,new String[]{"xymc","zrs","yzcrs","zcbl","wzcrs","wclrs"},new String[]{"ѧԺ","������","�ѱ�������","��������","δ��������","δ��������"}, response.getOutputStream());
			return mapping.findForward("");
		}
		
		//ͳ��
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTjTitle());
		
		service.setList(request, "zczt");
		request.setAttribute("path", "xsxx_xszctj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszctj");
	}
	
}

