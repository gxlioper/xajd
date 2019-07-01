package xgxt.studentInfo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.daoActionLogic.StuInfoActionLogic;


/**
 * @author Administrator
 * @category 学生信息模块action
 */
public class StudentInfoAction extends Action{

	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{

		ActionForward af = new ActionForward();
		String para = mapping.getParameter();
		String act=request.getParameter("act");
		HttpSession session = request.getSession(); 
		af = Base.chkSessionTimeOut(session);
		if(af == null){
			try{
				if("stuStatusDifferent".equalsIgnoreCase(para)){ //学籍异动					
					af = StuInfoActionLogic.stuStatusDifferentInfo(mapping,form,request,response);					
				}else if("stu_cgcj".equalsIgnoreCase(para)){
					if("stu_cgcj_sq".equalsIgnoreCase(act)){	//出国出境申请						
						af=StuInfoActionLogic.stuCgcjInfo(mapping, form, request, response);
					}else if("stu_tx_sq".equalsIgnoreCase(act)){//退学申请
						af=StuInfoActionLogic.stuTxInfo(mapping, form, request, response);
					}
				}else if("query".equalsIgnoreCase(para)){//出国退学信息查询
					af=StuInfoActionLogic.ManagerInfo(mapping, form, request, response);					
				}else if("studentMessage".equalsIgnoreCase(para)){//参数设置
					af=StuInfoActionLogic.StuMessageConf(mapping, form, request, response);
				}else if("addressBook".equalsIgnoreCase(para)){//通讯录
					af=StuInfoActionLogic.AddressBookInfo(mapping, form, request, response);
				}else if("attend_school_prove".equalsIgnoreCase(para)){//在读证明申请
					af=StuInfoActionLogic.AttendSchoolApply(mapping, form, request, response);
				}else if("prove_query".equalsIgnoreCase(para)){//在读证明申请查询
					af=StuInfoActionLogic.ProveManage(mapping, form, request, response);
				}else if("certificatePrint".equalsIgnoreCase(para)){//在读证明打印
					af=StuInfoActionLogic.CertificatePrint(mapping, form, request, response);
				}else if("stuSchoolingInfo".equalsIgnoreCase(para)){//学费信息
					af=StuInfoActionLogic.stuSchoolingInfo(mapping, form, request, response);
				}else if("stuSchoolingInfoOne".equalsIgnoreCase(para)){//单个学生学费维护
					af=StuInfoActionLogic.stuSchoolingInfoOne(mapping, form, request, response);
				}else if("stuSchoolingInfoSave".equalsIgnoreCase(para)){//单个学生学费保存
					af=StuInfoActionLogic.stuSchoolingInfoSave(mapping, form, request, response);
				}else if("stuinfoPrint".equalsIgnoreCase(para)){//学生报表信息打印
					af=StuInfoActionLogic.StuinfoPrint(mapping, form, request, response);
				}else if("stuGroupInfo".equalsIgnoreCase(para)){//学生集体信息报表打印					 
					af=StuInfoActionLogic.stuGroupInfo(mapping, form, request, response);
				}else if("certificatePrintAll".equalsIgnoreCase(para)){//证书打印
					af=StuInfoActionLogic.CertificatePrintAll(mapping, form, request, response);
				}
			}catch(Exception e){
				e.printStackTrace();
				return mapping.findForward("failure");
			}
		}	
	return af;
}
}
