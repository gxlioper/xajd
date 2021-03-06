/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.xljk.mjxy.xlwj;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.BaseAction;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.xljk.mjxy.XljkMjxyService;
import xgxt.xljk.mjxy.XljkMjxyXlztbsForm;

/** 
 * MyEclipse Struts
 * Creation date: 12-06-2010
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class XljkMjxyXlwjAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * 心理状态报送维护
	 * Method xlztbsWh
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward xlwjSb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
 		XljkMjxyService service=new XljkMjxyService();
 		XsxxglService xsxxglService=new XsxxglService();
 		XljkMjxyXlwjForm myForm=(XljkMjxyXlwjForm)form;
 		
		String tabName="xljk_mjxy_xlwjyjb";
		String doType=request.getParameter("doType");
		
		if("save".equalsIgnoreCase(doType)){
			this.insertOperation(request, tabName);
			String pkValue=myForm.getSave_xh()+myForm.getSave_sbsj();
			request.setAttribute("pkValue", pkValue);
			this.selectPageDataByOne(request, tabName, tabName, pkValue);
			doType="add";
		}
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		if(hashMap!=null){
			hashMap.putAll(xsxxglService.selectStuinfo(hashMap.get("xh")));
			hashMap.putAll(xsxxglService.getStuJtxx(hashMap.get("xh")));
			request.setAttribute("rs", hashMap);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("bsbmList", service.getBsbmList());
		request.setAttribute("xlztList", service.getXlztList());
		request.setAttribute("nowTime", GetTime.getNowTime2());
		request.setAttribute("path", "xljkMjxyXlwj.do?method=xlwjSb&doType=add");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("xlwjyjWh");
	}
	
	/**
	 * 心理状态报送查询
	 * method xlztbsCx
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xlwjCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		XljkMjxyXlwjForm myForm=(XljkMjxyXlwjForm)form;
		
		String doType=request.getParameter("doType");
		String tabName="xljk_mjxy_xlwjyjb";
		String viewName="view_xljk_mjxy_xlwjyj";
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tabName);
			doType="qry";
		}
		
		if("qry".equalsIgnoreCase(doType)){
			String []outputColumn={"pkValue","xh","xm","sbsj","nj","xymc","zymc","bjmc"}; 
			String qry="";
			if(!"".equalsIgnoreCase(myForm.getKssj())){
				qry=" and sbsj >= "+myForm.getKssj();
			}
			if(!"".equalsIgnoreCase(myForm.getJssj())){
				
				qry+=" and sbsj <= "+myForm.getJssj();
			}
			request.setAttribute("annexTerm", qry);
			this.selectPageDataByPagination(request, form,tabName, viewName, outputColumn);
		}
		
		request.setAttribute("path", "xljkMjxyXlwj.do?method=xlwjCx");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("xlwjCx");
	}
	
	/** 
	 * 心理状态报送单条记录操作
	 * Method xlztbsWh
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward xlwjOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		XljkMjxyService service=new XljkMjxyService();
		XsxxglService xsxxglService=new XsxxglService();
		
		String pkValue=request.getParameter("pkValue");
		String tabName="xljk_mjxy_xlwjyjb";
		String viewName="view_xljk_mjxy_xlwjyj";
		String doType=request.getParameter("doType");
		if("view".equalsIgnoreCase(doType) 
				|| "modi".equalsIgnoreCase(doType)){
			this.selectPageDataByOne(request, tabName, viewName, pkValue);
		}
		
		if("update".equalsIgnoreCase(doType)){
			this.updateOperation(request, tabName);
		}
		
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		if(hashMap!=null){
			hashMap.putAll(xsxxglService.selectStuinfo(hashMap.get("xh")));
			hashMap.putAll(xsxxglService.getStuJtxx(hashMap.get("xh")));
			request.setAttribute("rs", hashMap);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("bsbmList", service.getBsbmList());
		request.setAttribute("xlztList", service.getXlztList());
		request.setAttribute("nowTime", GetTime.getNowTime2());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "xljkMjxyXlwj.do?method=xlwjSb&doType=add");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("xlwjyjWh");
	}
	
	public ActionForward xlwjyjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		XljkMjxyService service=new XljkMjxyService();
		XsxxglService xsxxglService=new XsxxglService();

		String pkValue=request.getParameter("pkValue");
		if(!"".equalsIgnoreCase(pkValue)&& null!=pkValue){
			String tabName="xljk_mjxy_xlwjyjb";
			String viewName="view_xljk_mjxy_xlwjyj";
			this.selectPageDataByOne(request, tabName, viewName, pkValue);
			HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
			String xh=service.getXh(pkValue);
			hashMap.putAll(xsxxglService.selectStuinfo(xh));
			hashMap.putAll(xsxxglService.getStuJtxx(xh));
		}
		
		return mapping.findForward("xlwjyjb");
	}
	
	/**
	 * 信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward expDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String tabName="xljk_mjxy_xlwjyjb";
		String viewName="view_xljk_mjxy_xlwjyj";
		
		try {
			this.expPageData(request, response, tabName, viewName, null);
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
		return mapping.findForward("success");
	}
}