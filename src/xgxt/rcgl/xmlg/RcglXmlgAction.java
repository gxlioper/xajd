package xgxt.rcgl.xmlg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;


public class RcglXmlgAction  extends DispatchAction {
	
	//系、班集体外出活动管理申请
	public ActionForward bxjthdglsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglForm  myForm = (RcglForm) form;
		RcglXmlgService service = new RcglXmlgService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		if("stu".equals(userType)){
			if("save".equals(doType)){
				request.setAttribute("result", service.saveBxjthdsq_ser(myForm,"bx"));
			}
			
		}else{
			request.setAttribute("sfksq", "no");
		}
		return mapping.findForward("bxjthdsq");
	}
	
	//系、班集体外出活动管理审核
	public ActionForward bxjthdglsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//undo
		RcglForm  myForm = (RcglForm) form;
		RcglXmlgService service = new RcglXmlgService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
//		String userName = session.getAttribute("userName") == null ? ""
//				:session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		if("stu".equals(userType)){
			if("save".equals(doType)){
				request.setAttribute("result", service.saveBxjthdsq_ser(myForm,"bx"));
			}
			
		}else{
			request.setAttribute("sfksq", "no");
		}
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	
		return mapping.findForward("bxjthdsh");
	}
	
	//系、班集体外出活动管理审核查询
	public ActionForward bxjthdglshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//undo
//		RcglForm  myForm = (RcglForm) form;
//		RcglXmlgService service = new RcglXmlgService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
//		String userName = session.getAttribute("userName") == null ? ""
//				:session.getAttribute("userName").toString();
//		String doType = request.getParameter("doType");
		if("stu".equals(userType)){
			
			return mapping.findForward("bxjthdsh");
		}else{
			request.setAttribute("sfksq", "no");
		}
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	
		return mapping.findForward("bxjthdsh");
	}
	
	
	//系、班,跨系集体外出活动管理申请打印
	public ActionForward bxjthdPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglForm  myForm = (RcglForm) form;
		String cxsj = myForm.getCxsj();
		if(!Base.isNull(cxsj)){
			cxsj = cxsj.substring(0, 4)+"-"+cxsj.substring(4, 6)+"-"+cxsj.substring(6);
			myForm.setCxsj(cxsj);
		}
		request.setAttribute("form", myForm);
		request.setAttribute("lb", request.getParameter("lb"));
		return mapping.findForward("bxjthdsqb");
	}
	//跨系集体外出活动管理申请
	public ActionForward kxjthdglsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglForm  myForm = (RcglForm) form;
		RcglXmlgService service = new RcglXmlgService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		if("stu".equals(userType)){
			if("save".equals(doType)){
				request.setAttribute("result", service.saveBxjthdsq_ser(myForm,"kx"));
			}
			
		}else{
			request.setAttribute("sfksq", "no");
		}
		return mapping.findForward("kxjthdsq");
	}
	//跨系集体外出活动管理审核
	public ActionForward kxjthdglsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//undo
		RcglForm  myForm = (RcglForm) form;
		RcglXmlgService service = new RcglXmlgService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		if("stu".equals(userType)){
			if("save".equals(doType)){
				request.setAttribute("result", service.saveBxjthdsq_ser(myForm,"kx"));
			}
			
		}else{
			request.setAttribute("sfksq", "no");
		}
		return mapping.findForward("kxjthdsq");
	}
	
	//出勤统计管理
	public ActionForward cqtjgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglForm  myForm = (RcglForm) form;
		RcglXmlgService service = new RcglXmlgService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				:session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		if("xy".equals(userType)){
			myForm.setXydm(userDep);
		}
		if(myForm.getXn()==null){
			myForm.setXn(Base.currXn);
		}
		if(myForm.getXq()==null){
			myForm.setXq(Base.currXq);
		}
		if("query".equals(doType)){
			request.setAttribute("topTr",service.getTableTop("cqtj"));
			request.setAttribute("rs",service.queryCqtj_ser(myForm));
		}
		request.setAttribute("realTable", "rcsw_cqtjb");
		request.setAttribute("tableName", "view_cqtjb");
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	request.setAttribute("xyList",Base.getXyList());
		return mapping.findForward("cqtjgl");
	}
	
	//出勤统计管理增加
	public ActionForward cqtjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglForm  myForm = (RcglForm) form;
		RcglXmlgService service = new RcglXmlgService();
		String doType = request.getParameter("doType");
		if("save".equals(doType)){
			request.setAttribute("result",service.addCqtj_ser(myForm));
		}
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.getDqxqmc());
		request.setAttribute("xyList",Base.getXyList());
		return mapping.findForward("cqtjadd");
	}
	
	//出勤统计管理修改
	public ActionForward cqtjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglForm  myForm = (RcglForm) form;
		RcglXmlgService service = new RcglXmlgService();
		String doType = request.getParameter("doType");
		String pk = request.getParameter("pk");
		if("save".equals(doType)){
			request.setAttribute("result",service.saveCqtjUpdateXx_ser(pk, myForm));
		}
		request.setAttribute("rs",service.getCqtjUpdateXx_ser(pk));
		request.setAttribute("pk",pk);
		return mapping.findForward("cqtjupdate");
	}
	
	//出勤统计管理删除
	public ActionForward cqtjDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		RcglForm  myForm = (RcglForm) form;
		RcglXmlgService service = new RcglXmlgService();
		String pkVStr = request.getParameter("pkVStr");
		request.setAttribute("result",service.deleteCqtjXx_ser(pkVStr));
		return cqtjgl(mapping, form, request, response);
	}
	
	//出勤统计报表
	public ActionForward cqtjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglForm  myForm = (RcglForm) form;
		RcglXmlgService service = new RcglXmlgService();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.cjtjPrint_ser(myForm.getJcsj(), response.getOutputStream());
		return mapping.findForward("");
	}
	
	//旷课情况报送表
	public ActionForward kkqkbsgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcglForm  myForm = (RcglForm) form;
		DAO dao = DAO.getInstance();
		RcglXmlgService service = new RcglXmlgService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				:session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		if("xy".equals(userType)){
			myForm.setXydm(userDep);
		}
		if(myForm.getXn()==null){
			myForm.setXn(Base.currXn);
		}
		if(myForm.getXq()==null){
			myForm.setXq(Base.currXq);
		}
		if("query".equals(doType)){
			request.setAttribute("topTr",service.getTableTop("kkqk"));
			request.setAttribute("rs",service.queryCqtj_ser(myForm));
		}
		request.setAttribute("realTable", "rcsw_kkqkbsb");
		request.setAttribute("tableName", "view_kkqkbsb");
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	request.setAttribute("xyList",dao.getXyList());
   	 	request.setAttribute("njList",dao.getNjList());
   	 	request.setAttribute("zyList", dao.getZyList(myForm.getXydm()));
		request.setAttribute("bjList", dao.getBjList(myForm.getXydm(), myForm.getZydm(), myForm.getNj()));
		return mapping.findForward("kkqkbsgl");
	}
}
