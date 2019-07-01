package xgxt.xljk.whlgdx.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
//import xgxt.form.CommanForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.whlgdx.form.Xljk_XskhdxwhForm;
import xgxt.xljk.whlgdx.service.Xljk_XskhdxwhService;
/**
 * ѧ���������ά��    �人����ѧ liangchao
 */
public class Xljk_XskhdxwhAction extends Action {

	private Xljk_XskhdxwhService service = Xljk_XskhdxwhService.getInstance();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,  //execute method
	HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm)form;
		myForm.deal_GBK();
		String doType = request.getParameter("doType");
		String action = request.getParameter("action");
		myForm.setDoType(doType);     //set doType
		myForm.setAction(action);     //set action
		ActionForward myActFwd = null;
		try{
		Base.chkSessionTimeOut(request.getSession());  //���session�Ƿ����
		if(!StringUtils.isNull(doType)){
			if(doType.equalsIgnoreCase("add")){  //����ѧ����Ϣ
				myActFwd =  addStuInfo(mapping,form,request,response);
			}else if(doType.equalsIgnoreCase("modi")){     //�޸�ѧ����Ϣ
				myActFwd =  modifyStuInfo(mapping,form,request,response);
			}else if(doType.equalsIgnoreCase("del")){    //ɾ��ѧ����Ϣ
				myActFwd =  delStuInfo(mapping,form,request,response);	
			}else if(doType.equalsIgnoreCase("view")){   //˫���鿴����ѧ����Ϣ
				myActFwd =  getStuInfo(mapping,form,request,response);	
			}else if(doType.equalsIgnoreCase("search")){   //index���桾�����桿����ʾ���ҵ�����Ϣ
				myActFwd =  getStusInfoByCondi(mapping,form,request,response);	
			}else if(doType.equalsIgnoreCase("stu_info")){   //�õ�ѧ������Ϣ���ڶԻ�����ѡ��
				myActFwd =  getChoiceStusInfoByCondi(mapping,form,request,response);	
			}else if(doType.equalsIgnoreCase("per_stu")){   //˫����,�õ�ѧ������Ϣ
				myActFwd =  getChoicePerStuInfo(mapping,form,request,response);	
			}
		}else{
			request.setAttribute("rs", myForm);
			//request.setAttribute("tableName", "VIEW_XLJK_WHLGDX_KHXX"); //��ͼ����
			return mapping.findForward("index");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return myActFwd;
	}
	
	/** get students's info by condition*/     //MAIN
	public ActionForward getStusInfoByCondi (ActionMapping mapping, ActionForm form,  
	HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm)form;
		List<HashMap<String,String>> stuList = service.getStusByCondi(myForm);
		request.setAttribute("list", stuList);
		request.setAttribute("rsNum", stuList.size());
		request.setAttribute("topTr", service.getColumnCN());
		request.setAttribute("rs", myForm);
		request.setAttribute("path", "xljk_whlgdx_xskhdxwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("index");
	}
	
	/** get student's info*/
	public ActionForward getStuInfo (ActionMapping mapping, ActionForm form,  
	HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		String xn_id = request.getParameter("xn_id");//.replaceAll(" ", "+");
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm)form;
		myForm.setXn_id(xn_id);
		HashMap<String,String> rs = service.getStuInfo(myForm);
		rs.put("doType", myForm.getDoType());
		rs.put("action", myForm.getAction());
		request.setAttribute("rs", rs);  //student's info
		request.setAttribute("path", "xljk_whlgdx_xskhdxwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("view");
	}
	
	/** add student's info*/
	public ActionForward addStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm) form;
		if (StringUtils.isNull(request.getParameter("getXh"))) {
			if (!StringUtils.isNull(request.getParameter("action"))) {
				if (!service.isStuExists(myForm.getXh())) { // could add
															// student's info
					myForm.dealResult(service.saveStuInfo(myForm));
				} else {
					request.setAttribute("stuExist", "true");
				}
			}
			request.setAttribute("rs", myForm);
		}
		//TITLE·����ȡ
		request.setAttribute("path", "xljk_whlgdx_xskhdxwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("add");
	}
	
	/**modify student's info*/
	public ActionForward modifyStuInfo (ActionMapping mapping, ActionForm form,  
	HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm)form;
		if(!StringUtils.isNull(request.getParameter("action"))){
			myForm.dealResult(service.modiStuInfo(myForm));  //�޸�ѧ����Ϣ
			request.setAttribute("rs", myForm);
		}else{
			getStuInfo(mapping,myForm,request,response); //�õ�ѧ����Ϣ
			//request have data from form
		}
		//TITLE·����ȡ
		request.setAttribute("path", "xljk_whlgdx_xskhdxwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("modi");
	}
	
	/**delete student's info*/
	public ActionForward delStuInfo (ActionMapping mapping, ActionForm form,  
	HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm)form;
		myForm.dealResult(service.delStuInfo(myForm));
		request.setAttribute("rs", myForm);
		return mapping.findForward("del");    // to index.jsp
	}
	
	/**choice student's info by xh in dialogue window*/
	public ActionForward choiceStu (ActionMapping mapping, ActionForm form,  
	HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm)form;
		myForm.dealResult(service.delStuInfo(myForm));
		request.setAttribute("rs", myForm);
		return mapping.findForward("add");
	}
	
	//==========================choice===================================
	/** get students's info by condition   no pages*/
	public ActionForward getChoiceStusInfoByCondi (ActionMapping mapping, ActionForm form,  
	HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm)form;
		//CommanForm commanForm = new CommanForm();
		if(!StringUtils.isNull(request.getParameter("action"))){
			//myForm.getPages().setPageSize(10);   //set page size 
			List<HashMap<String,String>> stuList = service.getChoiceStusByCondi(myForm);
			request.setAttribute("rs", stuList);
			request.setAttribute("topTr", service.getChoice_ColumnCN());
			request.setAttribute("rsNum", service.getChoiceNumStusByCondi(myForm));
			myForm.getPages().setMaxRecord(Integer.parseInt(request.getAttribute("rsNum").toString()));
			//BeanUtils.copyProperties(commanForm,myForm);
		}
		//request.setAttribute("myForm",myForm);
		return mapping.findForward("stu_info");
	}
	
	/** get student's info by xh*/
	public ActionForward getChoicePerStuInfo (ActionMapping mapping, ActionForm form,  
	HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		request.setAttribute("tip", "��ǰ����λ�ã������� - ѧ���������ά�� - ����ѧ������");
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm)form;
		myForm.setDoType("add");  //��ת��add�Ľ���
		String xh = request.getParameter("xh").trim();
		myForm.setXh(xh);
		request.setAttribute("rs",service.getChoicePerStuInfo(myForm));
		request.setAttribute("closeWindow", "yes");
		return mapping.findForward("add");
	}
	
}
