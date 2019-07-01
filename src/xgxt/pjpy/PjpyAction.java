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
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("dyszwh".equalsIgnoreCase(parameter)){
				af = PjpyLogicDAO.dyszwh(mapping,form,request,response);
			} else if("jdsd".equalsIgnoreCase(parameter)){
				af = PjpyLogicDAO.jdsd(mapping, form, request, response);
			} else if("applyForPrise".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//ֻҪӵ�н�ѧ�������Ȩ�޾Ϳ��Խ����Ϻ����̵��������
					//�������㡢��ǿ��ѧ������
					af = PjpyLogicDAO.applyForPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("bgPriseApplication".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//���ֽ�ѧ������
					af = PjpyLogicDAO.applyForBaoSteelPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("dtPriseApplication".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//�� �� �� �� �� �� �� ����
					af = PjpyLogicDAO.applyForDtPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("sonyPriseApplication".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//sony��ѧ������
					af = PjpyLogicDAO.applyForSonyPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("jiaoyunPriseApplication".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//���˽�ѧ��
					af = PjpyLogicDAO.applyForJiaoyunPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("shanglianPriseApplication".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//������ѧ��
					af = PjpyLogicDAO.applyForShanglianPrise(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			}  else if("stu_cj_details".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "data_search.do?act=zhsz")){
					//SQ �ۺ����ʲ���ʱ��ѯѧ���ɼ�
					af = PjpyLogicDAO.showStuCjInfo(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("exportPrint".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					//SQ ���ݵ���
					af = PjpyLogicDAO.exportPrint(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("pjpy_zbdx_sssppdsjsz".equalsIgnoreCase(parameter)){
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "pjpy_zbdx_sssppdsjsz.do")){
					//�б���ѧʦ��˼Ʒ����ʱ������
					af = PjpyLogicDAO.pjpyZbdxSssppdsjsz(mapping,form,request,response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("pjpy_zbdx_autocalc".equalsIgnoreCase(parameter)){
				if(PjpyLogicDAO.pjpyZbdxZhcpAutoCalc(session.getAttribute("userDep").toString())){
					request.setAttribute("autoCj", "ok");
				} else {
					request.setAttribute("autoCj", "no");
				}
				mapping.findForward("success");	
			} else if("zbdxPjpyApply".equalsIgnoreCase(parameter)){ //SQ���б���ѧ�������Ž�ѧ������
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyApply(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
				mapping.findForward("success");	
			} else if("zbdxPjpyJxjdjb".equalsIgnoreCase(parameter)){ //SQ �б���ѧ��ѧ��������
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyJxjdjb(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("jsxxPjpyApply".equalsIgnoreCase(parameter)){ //SQ ������Ϣ��ѧ������
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyApply(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("jsxxPjpyJxjdjb".equalsIgnoreCase(parameter)){ //SQ ������Ϣ��ѧ��������
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyJxjdjb(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("jsxxRychApply".equalsIgnoreCase(parameter)){ //SQ ������Ϣ�����ƺ�����
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyRychApply(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("pjpy_zbdx_py".equalsIgnoreCase(parameter)){
				if("student".equalsIgnoreCase(userOnLine)){
					if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "pjpy_zbdx_xspy.do")){
						PjpyLogicDAO pjpyLogicDAO = new PjpyLogicDAO();
						af = pjpyLogicDAO.pjpyZbdxXspy(mapping,form,request,response);
					} else{
						tempForm.setErrMsg("Ȩ�޲��㣡");
						return new ActionForward("/login.jsp", false);
					}
				} else if("teacher".equalsIgnoreCase(userOnLine)){
					if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "pjpy_zbdx_jspy.do")){
						PjpyLogicDAO pjpyLogicDAO = new PjpyLogicDAO();
						af = pjpyLogicDAO.pjpyZbdxJspy(mapping,form,request,response);
					} else{
						tempForm.setErrMsg("Ȩ�޲��㣡");
						return new ActionForward("/login.jsp", false);
					}
				}
			} else if("zbdxPjpyJxjdjb".equalsIgnoreCase(parameter)){ //SQ �б���ѧ��ѧ��������
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "prise_apply.do")){
					af = PjpyLogicDAO.zbdxPjpyJxjdjb(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("dyszjf_print".equalsIgnoreCase(parameter)){ //SQ ��ӡ��������
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "dyszjf_print.do")){
					af = PjpyLogicDAO.PrintDyszjf(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("zhszcp_print".equalsIgnoreCase(parameter)){ //SQ ��ӡ�ۺ����ʲ���
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "zhszcp_print.do")){
					af = PjpyLogicDAO.PrintZhszcp(mapping, form, request, response);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("hzzy_cjimp".equalsIgnoreCase(parameter)){ //sq ����ְҵ����ѧԺ�ɼ�����
				return mapping.findForward("success");
			} else if("hzzy_cjimpcommit".equalsIgnoreCase(parameter)){ //sq ����ְҵ����ѧԺ�ɼ������ύ
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "hzzy_cjimpcommit.do")){
					af = PjpyLogicDAO.HzzyCjImpCommit(mapping, form, request, response, servlet);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			} else if("hzzy_cjQlcommit".equalsIgnoreCase(parameter)){ //sq ����ְҵ����ѧԺΪ�ճɼ�����
				if(CheckPower.checkUsrPageAccessPower(userOnLine,userName, "hzzy_cjQlcommit.do")){
					af = PjpyLogicDAO.Hzzy_cjQlcommit(mapping, form, request, response, servlet);
				} else{
					tempForm.setErrMsg("Ȩ�޲��㣡");
					return new ActionForward("/login.jsp", false);
				}
			}	
		} catch(Exception e){
			e.printStackTrace();
			tempForm.setErrMsg("�����쳣�������µ�½��");
			return new ActionForward("/login.jsp",false);
		}
		
		return af;
	}
}
