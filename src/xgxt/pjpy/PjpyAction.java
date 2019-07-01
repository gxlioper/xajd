package xgxt.pjpy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.CommanForm;
import xgxt.utils.CheckPower;

public class PjpyAction extends Action{
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) 
									throws Exception{
		HttpSession session = request.getSession();
		ActionForward af = new ActionForward();
		if( (af = Base.chkSessionTimeOut(session)) != null){
			return af;
		};
		CommanForm tempForm = new CommanForm();
		String parameter = mapping.getParameter();
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		try{
			if("dyszjfwh".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "dyszjfwh.do")){
					af = PjpyLogicDAO.zyszjfwh(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("dyszwh".equalsIgnoreCase(parameter)){
				af = PjpyLogicDAO.dyszwh(mapping,form,request,response);
			} else if("jdsd".equalsIgnoreCase(parameter)){
				af = PjpyLogicDAO.jdsd(mapping, form, request, response);
			} else if("applyForPrise".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//只要拥有奖学金申请的权限就可以进入上海工程的申请界面
					//这是优秀、自强奖学金申请
					af = PjpyLogicDAO.applyForPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("bgPriseApplication".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//宝钢奖学金申请
					af = PjpyLogicDAO.applyForBaoSteelPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("dtPriseApplication".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//上 海 地 铁 教 育 奖 申请
					af = PjpyLogicDAO.applyForDtPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("sonyPriseApplication".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//sony奖学金申请
					af = PjpyLogicDAO.applyForSonyPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("jiaoyunPriseApplication".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//交运奖学金
					af = PjpyLogicDAO.applyForJiaoyunPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("shanglianPriseApplication".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//上联奖学金
					af = PjpyLogicDAO.applyForShanglianPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			}  else if("stu_cj_details".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "data_search.do?act=zhsz")){
					//SQ 综合素质测评时查询学生成绩
					af = PjpyLogicDAO.showStuCjInfo(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("exportPrint".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//SQ 数据导出
					af = PjpyLogicDAO.exportPrint(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("pjpy_zbdx_sssppdsjsz".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "pjpy_zbdx_sssppdsjsz.do")){
					//中北大学师生思品评定时间设置
					af = PjpyLogicDAO.pjpyZbdxSssppdsjsz(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("pjpy_zbdx_autocalc".equalsIgnoreCase(parameter)){
				if(PjpyLogicDAO.pjpyZbdxZhcpAutoCalc(session.getAttribute("userDep").toString())){
					request.setAttribute("autoCj", "ok");
				} else {
					request.setAttribute("autoCj", "no");
				}
				mapping.findForward("success");	
			} else if("zbdxPjpyApply".equalsIgnoreCase(parameter)){ //SQ　中北大学评奖评优奖学金申请
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyApply(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
				mapping.findForward("success");	
			} else if("zbdxPjpyJxjdjb".equalsIgnoreCase(parameter)){ //SQ 中北大学奖学金评定表
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyJxjdjb(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("jsxxPjpyApply".equalsIgnoreCase(parameter)){ //SQ 江苏信息奖学金申请
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyApply(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("jsxxPjpyJxjdjb".equalsIgnoreCase(parameter)){ //SQ 江苏信息奖学金评定表
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyJxjdjb(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("jsxxRychApply".equalsIgnoreCase(parameter)){ //SQ 江苏信息荣誉称号申请
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyRychApply(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("pjpy_zbdx_py".equalsIgnoreCase(parameter)){
				if("student".equalsIgnoreCase(userOnLine)){
					if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "pjpy_zbdx_xspy.do")){
						PjpyLogicDAO pjpyLogicDAO = new PjpyLogicDAO();
						af = pjpyLogicDAO.pjpyZbdxXspy(mapping,form,request,response);
					} else{
						tempForm.setErrMsg("权限不足！");
						return new ActionForward("/login.jsp", false);
					}
				} else if("teacher".equalsIgnoreCase(userOnLine)){
					if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "pjpy_zbdx_jspy.do")){
						PjpyLogicDAO pjpyLogicDAO = new PjpyLogicDAO();
						af = pjpyLogicDAO.pjpyZbdxJspy(mapping,form,request,response);
					} else{
						tempForm.setErrMsg("权限不足！");
						return new ActionForward("/login.jsp", false);
					}
				}
			} else if("zbdxPjpyJxjdjb".equalsIgnoreCase(parameter)){ //SQ 中北大学奖学金评定表
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyJxjdjb(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("dyszjf_print".equalsIgnoreCase(parameter)){ //SQ 打印德育测评
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "dyszjf_print.do")){
					af = PjpyLogicDAO.PrintDyszjf(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("zhszcp_print".equalsIgnoreCase(parameter)){ //SQ 打印综合素质测评
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "zhszcp_print.do")){
					af = PjpyLogicDAO.PrintZhszcp(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("hzzy_cjimp".equalsIgnoreCase(parameter)){ //sq 杭州职业技术学院成绩导入
				return mapping.findForward("success");
			} else if("hzzy_cjimpcommit".equalsIgnoreCase(parameter)){ //sq 杭州职业技术学院成绩导入提交
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "hzzy_cjimpcommit.do")){
					af = PjpyLogicDAO.HzzyCjImpCommit(mapping, form, request, response, servlet);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			} else if("hzzy_cjQlcommit".equalsIgnoreCase(parameter)){ //sq 杭州职业技术学院为空成绩清理
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "hzzy_cjQlcommit.do")){
					af = PjpyLogicDAO.Hzzy_cjQlcommit(mapping, form, request, response, servlet);
				} else{
					tempForm.setErrMsg("权限不足！");
					return new ActionForward("/login.jsp", false);
				}
			}	
		} catch(Exception e){
			e.printStackTrace();
			tempForm.setErrMsg("出现异常，请重新登陆！");
			return new ActionForward("/login.jsp",false);
		}
		
		return af;
	}
}
