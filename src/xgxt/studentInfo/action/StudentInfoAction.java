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
 * @category ѧ����Ϣģ��action
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
				if("stuStatusDifferent".equalsIgnoreCase(para)){ //ѧ���춯					
					af = StuInfoActionLogic.stuStatusDifferentInfo(mapping,form,request,response);					
				}else if("stu_cgcj".equalsIgnoreCase(para)){
					if("stu_cgcj_sq".equalsIgnoreCase(act)){	//������������						
						af=StuInfoActionLogic.stuCgcjInfo(mapping, form, request, response);
					}else if("stu_tx_sq".equalsIgnoreCase(act)){//��ѧ����
						af=StuInfoActionLogic.stuTxInfo(mapping, form, request, response);
					}
				}else if("query".equalsIgnoreCase(para)){//������ѧ��Ϣ��ѯ
					af=StuInfoActionLogic.ManagerInfo(mapping, form, request, response);					
				}else if("studentMessage".equalsIgnoreCase(para)){//��������
					af=StuInfoActionLogic.StuMessageConf(mapping, form, request, response);
				}else if("addressBook".equalsIgnoreCase(para)){//ͨѶ¼
					af=StuInfoActionLogic.AddressBookInfo(mapping, form, request, response);
				}else if("attend_school_prove".equalsIgnoreCase(para)){//�ڶ�֤������
					af=StuInfoActionLogic.AttendSchoolApply(mapping, form, request, response);
				}else if("prove_query".equalsIgnoreCase(para)){//�ڶ�֤�������ѯ
					af=StuInfoActionLogic.ProveManage(mapping, form, request, response);
				}else if("certificatePrint".equalsIgnoreCase(para)){//�ڶ�֤����ӡ
					af=StuInfoActionLogic.CertificatePrint(mapping, form, request, response);
				}else if("stuSchoolingInfo".equalsIgnoreCase(para)){//ѧ����Ϣ
					af=StuInfoActionLogic.stuSchoolingInfo(mapping, form, request, response);
				}else if("stuSchoolingInfoOne".equalsIgnoreCase(para)){//����ѧ��ѧ��ά��
					af=StuInfoActionLogic.stuSchoolingInfoOne(mapping, form, request, response);
				}else if("stuSchoolingInfoSave".equalsIgnoreCase(para)){//����ѧ��ѧ�ѱ���
					af=StuInfoActionLogic.stuSchoolingInfoSave(mapping, form, request, response);
				}else if("stuinfoPrint".equalsIgnoreCase(para)){//ѧ��������Ϣ��ӡ
					af=StuInfoActionLogic.StuinfoPrint(mapping, form, request, response);
				}else if("stuGroupInfo".equalsIgnoreCase(para)){//ѧ��������Ϣ�����ӡ					 
					af=StuInfoActionLogic.stuGroupInfo(mapping, form, request, response);
				}else if("certificatePrintAll".equalsIgnoreCase(para)){//֤���ӡ
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
