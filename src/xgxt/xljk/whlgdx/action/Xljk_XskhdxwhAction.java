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
 * 学生困惑对象维护    武汉理工大学 liangchao
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
		Base.chkSessionTimeOut(request.getSession());  //检查session是否过期
		if(!StringUtils.isNull(doType)){
			if(doType.equalsIgnoreCase("add")){  //增加学生信息
				myActFwd =  addStuInfo(mapping,form,request,response);
			}else if(doType.equalsIgnoreCase("modi")){     //修改学生信息
				myActFwd =  modifyStuInfo(mapping,form,request,response);
			}else if(doType.equalsIgnoreCase("del")){    //删除学生信息
				myActFwd =  delStuInfo(mapping,form,request,response);	
			}else if(doType.equalsIgnoreCase("view")){   //双击查看单个学生信息
				myActFwd =  getStuInfo(mapping,form,request,response);	
			}else if(doType.equalsIgnoreCase("search")){   //index界面【主界面】上显示查找到的信息
				myActFwd =  getStusInfoByCondi(mapping,form,request,response);	
			}else if(doType.equalsIgnoreCase("stu_info")){   //得到学生的信息【在对话框中选择】
				myActFwd =  getChoiceStusInfoByCondi(mapping,form,request,response);	
			}else if(doType.equalsIgnoreCase("per_stu")){   //双击后,得到学生的信息
				myActFwd =  getChoicePerStuInfo(mapping,form,request,response);	
			}
		}else{
			request.setAttribute("rs", myForm);
			//request.setAttribute("tableName", "VIEW_XLJK_WHLGDX_KHXX"); //视图名称
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
		//TITLE路径获取
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
			myForm.dealResult(service.modiStuInfo(myForm));  //修改学生信息
			request.setAttribute("rs", myForm);
		}else{
			getStuInfo(mapping,myForm,request,response); //得到学生信息
			//request have data from form
		}
		//TITLE路径获取
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
		request.setAttribute("tip", "当前所在位置：心理健康 - 学生困惑对象维护 - 增加学生名单");
		Xljk_XskhdxwhForm myForm = (Xljk_XskhdxwhForm)form;
		myForm.setDoType("add");  //跳转到add的界面
		String xh = request.getParameter("xh").trim();
		myForm.setXh(xh);
		request.setAttribute("rs",service.getChoicePerStuInfo(myForm));
		request.setAttribute("closeWindow", "yes");
		return mapping.findForward("add");
	}
	
}
