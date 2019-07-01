/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.pjpy.mjxy.jtrych;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.pjpy.nbty.wmbj.NbtyWmbjService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

/** 
 * MyEclipse Struts
 * Creation date: 08-23-2010
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class MjxyJtrychAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * 集体荣誉称号申请
	 * Method jtrychSq
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward jtrychSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		MjxyJtrychService service=new MjxyJtrychService();
		MjxyJtrychForm jtrychForm=(MjxyJtrychForm)form;
		HttpSession session=request.getSession();
		NbtyWmbjService wmbjService=new NbtyWmbjService();
		
		String bjdm="";
		String userDep = session.getAttribute("userDep").toString();
		String doType=request.getParameter("doType");
		String userType=session.getAttribute("userType").toString();
		String userOnLine=session.getAttribute("userOnLine").toString();
		String userName=session.getAttribute("userName").toString();
		if("save".equalsIgnoreCase(doType)){
			this.insertOperation(request, "mjxy_jtrych");
		}
		
		
		if("student".equalsIgnoreCase(userOnLine)){
			String xh=userName;
			if(wmbjService.isBgb(xh)){
				bjdm=service.getBjdm(xh);
				service.getBjxx(request, xh);
				float bjrs=Float.parseFloat(service.Bjrs(bjdm));
				float dyrs=Float.parseFloat(service.bjdyrs(bjdm));
				float bjcfrs=Float.parseFloat(service.bjcfrs(bjdm));
				float cfrsbl=0.0f;
				float dyrsbl=0.0f;
				if(bjrs>0.0f){
					dyrsbl=dyrs/bjrs*100;
					cfrsbl=bjcfrs/bjrs*100;
				}
				request.setAttribute("bjgb", service.bjgbxx(bjdm));
				request.setAttribute("bjrs", service.Bjrs(bjdm));
				request.setAttribute("dyrsbl",dyrsbl);
				request.setAttribute("cfrsbl", cfrsbl);
			}else{
				request.setAttribute("notXsgb", "yes");
			}
		}else if("teacher".equalsIgnoreCase(userOnLine)){
			bjdm=request.getParameter("save_bjdm");
			String xydm=request.getParameter("save_xydm");
			String zydm=request.getParameter("save_zydm");
			float bjrs=Float.parseFloat(service.Bjrs(bjdm));
			float dyrs=Float.parseFloat(service.bjdyrs(bjdm));
			float bjcfrs=Float.parseFloat(service.bjcfrs(bjdm));
			float cfrsbl=0.0f;
			float dyrsbl=0.0f;
			if(bjrs>0.0f){
				dyrsbl=dyrs/bjrs*100;
				cfrsbl=bjcfrs/bjrs*100;
			}
			jtrychForm.setSave_bjdm(bjdm);
			jtrychForm.setSave_xydm(xydm);
			jtrychForm.setSave_zydm(zydm);
			request.setAttribute("nj",service.getNjByBjdm(bjdm) );
			request.setAttribute("bjgb", service.bjgbxx(bjdm));
			request.setAttribute("bjrs", service.Bjrs(bjdm));
			request.setAttribute("dyrsbl",dyrsbl);
			request.setAttribute("cfrsbl", cfrsbl);
		}
		
		if("xy".equalsIgnoreCase(userType)){
			jtrychForm.setXydm(userDep);
		}
		request.setAttribute("nowTime", GetTime.getNowTime2());
		request.setAttribute("jtrychList", service.jtrychList());
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("path", "mjxyJtrych.do?method=jtrychSq");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xn", Base.currXn);
		return mapping.findForward("jtrychSq");
	}
	
	/** 
	 * 集体荣誉称号申请查询
	 * Method jtrychSq
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward jtrychCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session=request.getSession();
		MjxyJtrychService service=new MjxyJtrychService();
		MjxyJtrychForm jtrychForm=(MjxyJtrychForm)form;
		
		String userDep=session.getAttribute("userDep").toString();
		String userType=session.getAttribute("userType").toString();
		String userOnLine=session.getAttribute("userOnLine").toString();
		String userName=session.getAttribute("userName").toString();
		String doType=request.getParameter("doType");
		String tabName="mjxy_jtrych";
		String viewName="view_mjxy_jtrych";
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tabName);
			doType="qry";
		}
		
		if("qry".equalsIgnoreCase(doType)){
			service.disabled(request, userType);
			String[]outputColumn={"pkValue","disabled","xn","nj","bjmc","zymc","xymc","jtrychmc","xysh","xxsh"};
			this.selectPageDataByPagination(request, form,tabName, viewName, outputColumn);
		}
		
		if("xy".equalsIgnoreCase(userType)){
			jtrychForm.setXydm(userDep);
		}
		
		if("student".equalsIgnoreCase(userOnLine)){
			String bjdm=service.getBjdm(userName);
			jtrychForm.setBjdm(bjdm);
			request.setAttribute("bjdms", bjdm);
		}
		request.setAttribute("jtrychList", service.jtrychList());
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("path", "mjxyJtrych.do?method=jtrychCx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jtrychCx");
	}
	
	/** 
	 * 集体荣誉称号审核
	 * Method jtrychSh
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward jtrychSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		MjxyJtrychService service=new MjxyJtrychService();
		MjxyJtrychForm jtrychForm=(MjxyJtrychForm)form;
		
		String userDep= session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String doType=request.getParameter("doType");
		String tabName="mjxy_jtrych";
		String viewName="view_mjxy_jtrych";
		String shjg=request.getParameter("shjg");
		if("save".equalsIgnoreCase(doType)){			
			//审核操作
			HashMap<String, String> valueMap =  service.setShzd(userType, shjg);
			
			
			//批量审核的primaryKey
			HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
			//通用批量审核
			this.auditingBatchOperation(request, primaryMap,valueMap, tabName);
			doType="qry";
		}
		
		
		if("qry".equalsIgnoreCase(doType)){
			service.setDisabled(request, userType);
			String[]outputColumn={"pkValue","disabled","xn","nj","bjmc","zymc","xymc","jtrychmc","xysh","xxsh"};
			this.selectPageDataByPagination(request, form,tabName, viewName, outputColumn);
		}
		
		if("xy".equalsIgnoreCase(userType)){
			jtrychForm.setXydm(userDep);
		}
		
		request.setAttribute("jtrychList", service.jtrychList());
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("path", "mjxyJtrych.do?method=jtrychSh");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jtrychSh");
	}
	
	
	/** 
	 * 单条信息查询
	 * Method jtrychSq
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward jtrychOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		MjxyJtrychService service=new MjxyJtrychService();
		MjxyJtrychForm jtrychForm=(MjxyJtrychForm)form;
		HttpSession session=request.getSession();
		
		String tabName="mjxy_jtrych";
		String viewName="view_mjxy_jtrych";
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");
		String userType=session.getAttribute("userType").toString();
		
		this.selectPageDataByOne(request, tabName, viewName, pkValue);
		
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		String bjdm=hashMap.get("bjdm");
		if(!Base.isNull(bjdm)){
			float bjrs=Float.parseFloat(service.Bjrs(bjdm));
			float dyrs=Float.parseFloat(service.bjdyrs(bjdm));
			float bjcfrs=Float.parseFloat(service.bjcfrs(bjdm));
			float cfrsbl=0.0f;
			float dyrsbl=0.0f;
			if(bjrs>0.0f){
				dyrsbl=dyrs/bjrs*100;
				cfrsbl=bjcfrs/bjrs*100;
			}
			jtrychForm.setSave_jtrychdm(hashMap.get("jtrychdm"));
			request.setAttribute("bjgb", service.bjgbxx(bjdm));
			request.setAttribute("bjrs", service.Bjrs(bjdm));
			request.setAttribute("dyrsbl",dyrsbl);
			request.setAttribute("cfrsbl", cfrsbl);
		}
		

		request.setAttribute("nowTime", GetTime.getNowTime2());
		request.setAttribute("jtrychList", service.jtrychList());
		request.setAttribute("doType", doType);
//		单个审核的查看控制
		service.writeReadCx(request, userType,doType);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("path", "mjxyJtrych.do?method=jtrychSq");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xn", Base.currXn);
		return mapping.findForward("jtrychSq");
	}
	
	public ActionForward jtrychModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		MjxyJtrychService service=new MjxyJtrychService();
	
		String tabName="mjxy_jtrych";
		this.updateOperation(request, tabName);
		request.setAttribute("jtrychList", service.jtrychList());
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("path", "mjxyJtrych.do?method=jtrychSq");
		request.setAttribute("xn", Base.currXn);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jtrychSq");
	}
	
	public ActionForward jtrychShone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		MjxyJtrychForm jtrychForm=(MjxyJtrychForm)form;
		MjxyJtrychService service=new MjxyJtrychService();
		
		String userType=session.getAttribute("userType").toString();
		String tabName="mjxy_jtrych";
		String viewName="view_mjxy_jtrych";
		String pkValue=request.getParameter("pkValue");
		String doType=request.getParameter("doType");
		
		if("modi".equalsIgnoreCase(doType)){
			 this.updateOperation(request, tabName);			//获取request中要保存的数据，进行修改操作
			 this.selectPageDataByOne(request, tabName, viewName, pkValue);
				
		}
		
		if("view".equalsIgnoreCase(doType)){
			this.selectPageDataByOne(request, tabName, viewName, pkValue);
		
			HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
			String bjdm=hashMap.get("bjdm");
			float bjrs=Float.parseFloat(service.Bjrs(bjdm));
			float dyrs=Float.parseFloat(service.bjdyrs(bjdm));
			float bjcfrs=Float.parseFloat(service.bjcfrs(bjdm));
			float cfrsbl=0.0f;
			float dyrsbl=0.0f;
			if(bjrs>0.0f){
				dyrsbl=dyrs/bjrs*100;
				cfrsbl=bjcfrs/bjrs*100;
			}
			jtrychForm.setSave_jtrychdm(hashMap.get("jtrychdm"));
			request.setAttribute("bjgb", service.bjgbxx(bjdm));
			request.setAttribute("bjrs", service.Bjrs(bjdm));
			request.setAttribute("dyrsbl",dyrsbl);
			request.setAttribute("cfrsbl", cfrsbl);
			
			if("xy".equalsIgnoreCase(userType)){
				jtrychForm.setSave_xysh(hashMap.get("xysh"));
			}else if("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)){
				jtrychForm.setSave_xxsh(hashMap.get("xxsh"));
			}
		}
		
		//单个审核的查看控制
		service.writeRead(request, userType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "mjxyJtrych.do?method=jtrychSh");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xn", Base.currXn);
		return mapping.findForward("jtrychShone");
	}
	
	/**
	 * 荣誉称号信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward expDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			this.expPageData(request, response, "mjxy_jtrych", "view_mjxy_jtrych", null);
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}			
		
		return mapping.findForward("success");
	}
}