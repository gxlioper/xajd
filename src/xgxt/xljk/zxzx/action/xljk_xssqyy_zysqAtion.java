
package xgxt.xljk.zxzx.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import java.io.File;
import java.util.*;

import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.ExcelMethods;
import xgxt.xljk.zxzx.form.xljk_xssqyy_Form;
import xgxt.xljk.zxzx.util.XLJK_ZXZX_Util;

/** 
 * Creation date: 07-06-2007
 */
public class xljk_xssqyy_zysqAtion extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xssqyy_Form myform = new xljk_xssqyy_Form();
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				myform.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}

			String act = request.getParameter("act");
			String doType = request.getParameter("doType");
			ActionForward myActFwd = null;
			if (act.equals("xljk_xssqyy_zysq")) {
				if (doType != null && !doType.equalsIgnoreCase("")) {
					if (doType.equals("student_check")) {
						myActFwd = Student_Check(mapping, form, request,
								response);
					}
					if (doType.equals("Apply_Table")) {  //进入预约申请表
						myActFwd = Apply_Table(mapping, form, request, response);
					}
					if (doType.equals("Table_Record")) { //保存预约信息
						myActFwd = table_record(mapping, form, request,
								response);
					}
					if (doType.equals("Search_Table")) {
						myActFwd = Table_Search(mapping, form, request,
								response);
					}
					if (doType.equals("Cancle_Table")) { //取消预约
						myActFwd = Cancle_Table(mapping, form, request,
								response);
					}
					//闽江学院心理咨询师预约报表
					if (doType.equals("xljk_zxsyy")) {  //进入预约申请表
						myActFwd = xljk_zxxyybb(mapping, form, request, response);
					}
					//闽江学院学生预约查询
					if (doType.equals("xljk_xsyycx")) {
						myActFwd = xsyyCxTj(mapping, form, request,
								response);
					}
				} 
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			myform.setErrMsg("数据连接中断，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}
	}

	private ActionForward Student_Check(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//String zxszy_xnid = request.getParameter("zxszy_xnid");
		return Apply_Table(mapping,form,request,response);
/**
		xljk_xssqyy_Form myform = new xljk_xssqyy_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		//myutil.XLJK_ZXZX_Util(request);
		String yanzheng_flag = request.getParameter("yanzheng_flag");
		String zxszy_xnid = request.getParameter("zxszy_xnid");
		request.setAttribute("zxszy_xnid", zxszy_xnid);
		if (yanzheng_flag != null && !yanzheng_flag.equalsIgnoreCase("")) {
			myform.setXh(request.getParameter("xh"));
			myform.setXssrmm(request.getParameter("xsmm"));
			String message = myutil.xssqyy_check_student(myform);
			request.setAttribute("zxszy_xnid", zxszy_xnid);
			if (message.equals("true")) {
				request.setAttribute("message", message);
				request.setAttribute("xh", myform.getXh());
				request.setAttribute("xsmm", myform.getXssrmm());
				return mapping.findForward("check_index");
			} else {
				request.setAttribute("message", "log fail");
				request.setAttribute("xh", myform.getXh());
				request.setAttribute("xsmm", myform.getXssrmm());
				return mapping.findForward("check_index");
			}
		} else {
			request.setAttribute("xh", myform.getXh());
			request.setAttribute("xsmm", myform.getXssrmm());
			return mapping.findForward("check_index");
		}
	*/	
	}

	private ActionForward Apply_Table(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xssqyy_Form myform = new xljk_xssqyy_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		xljk_xssqyy_Form stuform = new xljk_xssqyy_Form();
		xljk_xssqyy_Form yqform = new xljk_xssqyy_Form();
		xljk_xssqyy_Form zylsform = new xljk_xssqyy_Form();
		xljk_xssqyy_Form jtcyform = new xljk_xssqyy_Form();
		String zxszy_xnid = request.getParameter("zxszy_xnid");
		String userType = (String) request.getSession().getAttribute("userType");
		request.setAttribute("zxszy_xnid", zxszy_xnid);
		myform.setXh(request.getSession().getAttribute("userName").toString());
		stuform = myutil.xssqyy_findStu_byId(myform.getXh());
		yqform = myutil.xssqyy_findYq_byXnid(zxszy_xnid);
		
		if (!"stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起，本页面只有学生用户可以访问!");
			return new ActionForward("/errMsg.do",false);
		}
		
		
		if(StandardOperation.getXxdm().equals(Globals.XXDM_ZGMSXY)){
			//查看是否可以申请，在三小时之外
			//String sql = "select to_char(sysdate,'hh24:mi:ss') from dual";
			DAO dao = DAO.getInstance();
			String nowtime = (dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
					// 发布时间25
					// 取至数据库临时表
					new String[] {}, new String[] { "sdate" }))[0];
//			int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			int minute = Calendar.getInstance().get(Calendar.MINUTE);
			//由于显示的时候就是当天或之后的信息，那么天就不用在判断了
			String sjd = yqform.getSjd();
			String csrq = yqform.getRq();
			boolean bool = false;
			if(!Base.isNull(csrq)){
				String[] csrq1 = csrq.split("-");
				String[] csrq2 = nowtime.split("-");
				if((Integer.valueOf(csrq1[1]))>(Integer.valueOf(csrq2[1]))){
					bool = true;
				}
				if(!bool){
					if((Integer.valueOf(csrq1[2]))>(Integer.valueOf(csrq2[2]))){
						bool = true;
					}
				}
			}
			if(!bool){
				String[] sjdSplit = sjd.split("-");
				String sjdBeginH = sjdSplit[0].split(":")[0].trim();
				String sjdBeginM = sjdSplit[0].split(":")[1].trim();
				if(Integer.parseInt(sjdBeginH)>= hour + 3
						&& Integer.parseInt(sjdBeginM)>= minute + 3){
					
					bool = true;
				}	
			}
			if(bool){
				request.setAttribute("xxdm", StandardOperation.getXxdm());
				request.setAttribute("yyfsList", myutil.getYyfsList());
			}else{
				request.setAttribute("noSq", "yes");//表示不能申请
			}
		}
		List mycdList = myutil.get_mycdList();
		request.setAttribute("rs", jtcyform);
		request.setAttribute("zxls_rs", zylsform);
		request.setAttribute("mycdList", mycdList);
		request.setAttribute("xs_rs", stuform);
		request.setAttribute("yq_rs", yqform);
		return mapping.findForward("check_sucess");

	}

	private ActionForward table_record(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xssqyy_Form myform = new xljk_xssqyy_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		myform.validate(mapping, request);
		xljk_xssqyy_Form zxsform = new xljk_xssqyy_Form();
		zxsform = myutil.xssqyy_findYq_byXnid(myform.getZxszy_xnid());
		myform.setZXXBH(zxsform.getZXXBH());
		xljk_xssqyy_Form rsform = new xljk_xssqyy_Form();
		boolean falg = myutil.xssqyy_tableDetail_Rec(myform);
		String message = (falg == true) ? "rec_suc" : "rec_fail";
		rsform = myutil.xssqyy_find_rec(myform.getZxszy_xnid()); 
		rsform.setZymycd_dm(myform.getZymycd());
		List mycdList = myutil.get_mycdList();
		request.setAttribute("zxszy_xnid", myform.getZxszy_xnid());
		request.setAttribute("message", message);
		request.setAttribute("mycdList", mycdList);
		request.setAttribute("rs", rsform);
		return mapping.findForward("table_detail");
	}

	private ActionForward Table_Search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		xljk_xssqyy_Form myform = new xljk_xssqyy_Form();
		xljk_xssqyy_Form rsform = new xljk_xssqyy_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		String zxszy_xnid = request.getParameter("zxszy_xnid");
		myform.setZxszy_xnid(zxszy_xnid);
		rsform = myutil.xssqyy_find_rec(zxszy_xnid);
		List mycdList = myutil.get_mycdList();
		rsform.setZymycd_dm(myform.getZymycd());
		request.setAttribute("mycdList", mycdList);
		request.setAttribute("rs", rsform);
		if(StandardOperation.getXxdm().equals(Globals.XXDM_ZGMSXY)){
			request.setAttribute("xxdm", StandardOperation.getXxdm());
			request.setAttribute("yyfsList", myutil.getYyfsList());
		}
		request.setAttribute("zxszy_xnid", zxszy_xnid);
		return mapping.findForward("table_detail");
	}

	private ActionForward Cancle_Table(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zxszy_xnid = request.getParameter("zxszy_xnid");
		@SuppressWarnings("unused")
		xljk_xssqyy_Form myform = new xljk_xssqyy_Form();
		@SuppressWarnings("unused")
		xljk_xssqyy_Form rsform = new xljk_xssqyy_Form();
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		@SuppressWarnings("unused")
		String[] id = zxszy_xnid.split(",");
		boolean flag = myutil.xssqyy_cancle_table(zxszy_xnid);
		@SuppressWarnings("unused")
		String message = "";
		if (flag == true) {
			message = "cacel_suc";
		} else {
			message = "cacel_fail";
		}

		return mapping.findForward("cacel_success");
	}

	/**
	 * xljk_zxxyybb
	 * 咨询师预约报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward xljk_zxxyybb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		xljk_xssqyy_Form myform = (xljk_xssqyy_Form)form;
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		xljk_xssqyy_Form xljkform = new xljk_xssqyy_Form();
		xljk_xssqyy_Form stuform = new xljk_xssqyy_Form();
		String zxszy_xnid = request.getParameter("zxszy_xnid");
		request.setAttribute("zxszy_xnid", zxszy_xnid);
	
		xljkform = myutil.xssqyy_find_rec(zxszy_xnid);
		stuform = myutil.xssqyy_findStu_byId(xljkform.getXh());
		List mycdList = myutil.get_mycdList();
	
		request.setAttribute("mycdList", mycdList);
		request.setAttribute("xs_rs", xljkform);
		request.setAttribute("stu",stuform );
		return mapping.findForward("xljk_zxxyybb");

	}
	
	/**
	 * 学生预约查询统计
	 * method xsyyCxTj
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward xsyyCxTj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		xljk_xssqyy_Form myform = (xljk_xssqyy_Form) form;
		XLJK_ZXZX_Util myutil = new XLJK_ZXZX_Util(request);
		HttpSession session=request.getSession();
		
		//将用户权限相关信息存入FORM中
		myform.setUserName(session.getAttribute("userName").toString());
		myform.setUserDep(session.getAttribute("userDep").toString());
		myform.setUserType(session.getAttribute("userType").toString());
		myform.setIsBzr(session.getAttribute("isBzr").toString());
		myform.setIsFdy(session.getAttribute("isFdy").toString());
		
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/xsxx/cslg_xxjl.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		myutil.expData(myform, request, wwb);
		return mapping.findForward("");
	}

}