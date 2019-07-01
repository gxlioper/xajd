package xgxt.studentInfo.xszc;

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
	 * ѧ��ע�������ѯ
	 * @throws SQLException 
	 */
	public ActionForward xszcsq(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException{
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		XszcActionForm xszcForm = (XszcActionForm)form;
		if (!"stu".equals(userType)) {
			request.setAttribute("message", "��ҳ��ֻ��ѧ���û����Է���");
			return new ActionForward("/prompt.do",false);
		}
		String doType = request.getParameter("doType");
		XszcService service = new XszcService();
		if("zc".equals(doType)){
			if(!Base.currXn.equals(xszcForm.getXn().split(",")[0])){
				request.setAttribute("result", false);
				request.setAttribute("message", "ֻ�ܶԵ�ǰѧ�ڽ��в�����");
			}else{
				boolean res = service.zc(xszcForm);
				request.setAttribute("result", res);
				request.setAttribute("message", res? " �����ɹ���" : "����ʧ�ܣ�");
			}
		}
		
		request.setAttribute("rs", service.getRs(userName));
		request.setAttribute("currXn", Base.currXn);
		request.setAttribute("currXq", Base.currXq.equals("01")?"��ѧ��":"��ѧ��");
		request.setAttribute("topTr", service.getTopTr());
		request.setAttribute("path", "xsxx_xszcsq.do");
		request.setAttribute("xh", userName);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszcsq");
	}
	
	/**
	 * ѧ��ע��ԭ����д
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszcsqyydetail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userName = request.getSession().getAttribute("userName").toString();
		XszcService service = new XszcService();
		XszcActionForm xszcForm = (XszcActionForm)form;
		String doType = request.getParameter("doType");
		String tableName = "xg_xsxx_zcqkb";
		if("save".equals(doType)){
			boolean res = service.del(xszcForm.getPkValue());
			if(res){
				insertOperation(request, tableName);
			}
		}
		xszcForm.setXh(userName);
		request.setAttribute("oper", request.getParameter("oper"));
		request.setAttribute("rs", service.getXszcInfo(xszcForm));
		return mapping.findForward("xszcsqyydetail");
	}
	
	/**
	 * ѧ��ע����ˣ���ѯ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward xszcsh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy").toString();//�ж��Ƿ��Ǹ���Ա
		
		XszcActionForm myForm = (XszcActionForm) form;
		XszcService service = new XszcService();
		
		String tableName = "xg_xsxx_zcqkb";
		String viewName = "xg_view_xsxx_zcqkb";
		
		String doType = request.getParameter("doType");
		String[] outputColumn = new String[] {"pkValue","bagcolor","dis", "xn", "xq","xh","xm","zczt","fdysh"};
		request.setAttribute("shzd", "fdysh");
		request.setAttribute("clientColumns", " (case fdysh when 'ͨ��' then '#99CCFF' when '��ͨ��' then '#FF9999' else '#FFFFFF' end) bagcolor,(case fdysh when 'δ���' then '' else 'disabled' end) dis,");
		
		/*Ȩ�޿���*/
		if(StringUtils.isEqual(isFdy, "true")){
			request.setAttribute("annexTerm", " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
//			request.setAttribute("clientColumns", " (case fdysh when 'ͨ��' then '#99CCFF' when '��ͨ��' then '#FF9999' else '#FFFFFF' end) bagcolor,(case fdysh when 'δ���' then '' else 'disabled' end) dis,");
		} else if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
//			request.setAttribute("clientColumns", " (case fdysh when 'ͨ��' then '#99CCFF' when '��ͨ��' then '#FF9999' else '#FFFFFF' end) bagcolor,(case fdysh when 'δ���' then '' else 'disabled' end) dis,");
		} else if("xx".equals(userType) || "admin".equals(userType)){
			
		} else {
			request.setAttribute("errMsg","�Բ�������Ȩ���ʴ�ҳ��" );
			return new ActionForward("/errMsg.do",false);
		}
		
		//��ѯ
		this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		
		//�������
		if (!Base.isNull(doType) && "sh".equalsIgnoreCase(doType)) {
			this.auditingBatchOperation(request, tableName);
		}
		if("del".equals(doType)){
			deleteOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, new String[] {"xn", "xq","xh","xm","zczt","fdysh"});
			return mapping.findForward("");
		}
		
		service.setList(request, "sh");
		service.setList(request, "zc");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "xsxx_xszcsh.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("xszcsh");
	}
	
	/**
	 * ѧ��ע�������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NullStringException
	 */
	public ActionForward xszcshDetial(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws NullStringException{
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy").toString();//�ж��Ƿ��Ǹ���Ա
		
		XszcService service = new XszcService();
		XszcActionForm myForm = (XszcActionForm)form;
		String tableName = "xg_xsxx_zcqkb";
		String viewName = "xg_view_xsxx_zcqkb";
		
//		if(!StringUtils.isEqual(isFdy, "true")&&!"xy".equals(userType)){
//			request.setAttribute("errMsg","�Բ�������Ȩ���ʴ�ҳ��" );
//			return new ActionForward("/errMsg.do",false);
//		}
		
		this.selectPageDataByOne(request, tableName, viewName, myForm.getPk());
		service.setList(request, "sh");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		
		//����
		String doType = request.getParameter("doType");
		if ("save".equals(doType)) {
			this.updateOperation(request, tableName);
			this.selectPageDataByOne(request, tableName, viewName, myForm.getPkValue());
		}
		return mapping.findForward("xszcshDetail");
	}

	/**
	 * ѧ��ע������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws NullStringException
	 */
	public ActionForward xszcjgcx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws NullStringException{
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy").toString();//�ж��Ƿ��Ǹ���Ա
		
		XszcActionForm myForm = (XszcActionForm) form;
		String tableName = "xsxxb";
		String viewName = "view_xg_xsxx_zcqkbonecode";
		
		String[] outputColumn = new String[] {"xh","xm","xb","xymc","zymc","bjmc"};
		
		if(StringUtils.isEqual("true", isFdy)){
			request.setAttribute("annexTerm", " and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='"+userName+"') ");
		} else if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		} else if("stu".equals(userType)){
			myForm.setQuerylike_xh(userName);
			request.setAttribute("annexTerm", " and xh= '"+userName+"'");
		}
		
		//��ѯ
		this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "xsxx_xszcjgcx.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("xszcjgcx");
	}
	
	/**
	 * ѧ����ϸ����ѧ��ע�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 */
	public ActionForward xszcjgcxDetail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException{
		String userName = request.getParameter("pk");
		XszcService service = new XszcService();
		request.setAttribute("rs", service.getRs(userName));
		request.setAttribute("topTr", service.getTopTr());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszcjgcxDetail");
	}
	
	/**
	 * ����ѧ��ע����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dcxszcjg(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		XszcActionForm myForm = (XszcActionForm) form;
		
		String tableName = "xg_xsxx_zcqkb";
		String viewName = "xg_view_xsxx_zcqkb";
		String[] outputColumn = new String[] { "xn", "xq","xh","xm","xb","nj","xymc","zymc","bjmc","zczt","fdysh"};
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		} else if("stu".equals(userType)){
			myForm.setQuerylike_xh(userName);
			request.setAttribute("annexTerm", " and xh= '"+userName+"'");
		}
		//��ѯ
		this.selectPageData(request, tableName, viewName, outputColumn);
		
		List<String[]> rs = (List<String[]>)request.getAttribute("rs");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, outputColumn,new String[]{"ѧ��","ѧ��","ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","ע��״̬","���״̬"}, response.getOutputStream());
		return null;
	}
	
}

