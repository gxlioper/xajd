/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.xsh.stgl.dekthdqh;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.rcsw.kqgl.xskq.RcswKqglXskqService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.GetTime;

/** 
 * MyEclipse Struts
 * Creation date: 07-24-2010
 * author 裘力俊
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SjxyDektqhAction extends BasicAction {
	/*
	 * Generated Methods
	 */
	protected static final String QUERY = "qry";//查询操作判断标志
	
	protected static final String DELETE = "del";//页面删除操作判断标志
	
	protected static final String VIEW = "view";//页面单个显示详细操作判断标志
	
	protected static final String MODIFY = "modi";//页面修改操作判断标志

	protected static final String SAVE = "save";//页面保存操作判断标志
	
	protected static final String TABNAME = "sjxy_dekthdqh";//页面保存操作判断标志
	
	protected static final String VIEWNAME = "view_sjxy_dekthdqh";//页面保存操作判断标志
	/** 
	 * Method sqDektqh
	 * 申请第二课堂企划
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward sqDektqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		SjxyDektqhService service=new SjxyDektqhService();
		RcswKqglXskqService rcswKqglXskqService=new RcswKqglXskqService();
		
		String userType=session.getAttribute("userType").toString();
		String userDep=session.getAttribute("userDep").toString();
		
		
		String userName=session.getAttribute("userName").toString();
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("save_hdmc")+request.getParameter("save_jbbm")+request.getParameter("save_jbsj");
		
		//不是社团负责人，判断是否是学院用户
		service.setQhInfo(request, userName, userType,userDep);
		
		if(SAVE.equalsIgnoreCase(doType)){
									//插入后，再查询一遍
			this.insertOperation(request, TABNAME);								//保存荣誉称号信息
		}
		
		//读写权
		rcswKqglXskqService.writeAbled(request, "sjxyDektqh.do?method=sqDektqh");
		//用于打印的pkValue
		request.setAttribute("pkValue", pkValue);
		//申请时间
		request.setAttribute("nowTime", GetTime.getNowTime2());
		return mapping.findForward("sqDektqh");
	}
	
	/**
	 * method resultDektqh
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward resultDektqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		SjxyDektqhForm SjxyDektqhForm=(SjxyDektqhForm)form;
		SjxyDektqhService service=new SjxyDektqhService();
		RcswKqglXskqService rcswKqglXskqService=new RcswKqglXskqService();
		
		String userName=session.getAttribute("userName").toString();
		String userDep=session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String doType=request.getParameter("doType");
	
		
		if(DELETE.equalsIgnoreCase(doType)){
			this.deleteOperation(request, TABNAME);
			doType="qry";
		}
		
		if(QUERY.equalsIgnoreCase(doType)){
			//设置结果查询页面disabled属性
			service.disabled(request,userType,userDep,userName);
			String []outputColumn={"pkValue","disabled","hdmc","jbbm","jbsj","hddd","hdfzr","zdls","lxfs","xxsh"};
			this.selectPageDataByPagination(request, SjxyDektqhForm, TABNAME, VIEWNAME, outputColumn);
		}
		
		
		
		//读写权
		rcswKqglXskqService.writeAbled(request, "sjxyDektqh.do?method=resultDektqh");
		//申请时间
		request.setAttribute("nowTime", GetTime.getNowTime2());
		return mapping.findForward("resultDektqh");
	}
	
	/**
	 * method shDektqh
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward shDektqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		SjxyDektqhForm SjxyDektqhForm=(SjxyDektqhForm)form;
		SjxyDektqhService service=new SjxyDektqhService();
		RcswKqglXskqService rcswKqglXskqService=new RcswKqglXskqService();
		
		String userName=session.getAttribute("userName").toString();
		String userDep=session.getAttribute("userDep").toString();
		String userType=session.getAttribute("userType").toString();
		String doType=request.getParameter("doType");
		String shjg=request.getParameter("shjg");
		String shsj=GetTime.getNowTime2();
		
		if(SAVE.equalsIgnoreCase(doType)){
			HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
			HashMap<String,String>valueMap =new HashMap<String,String>();
			valueMap.put("xxsh", shjg);
			valueMap.put("xxshsj", shsj);
			this.auditingBatchOperation(request, primaryMap, valueMap, TABNAME);
			doType="qry";
		}
		
		//查询
		if(QUERY.equalsIgnoreCase(doType)){
			String []outputColumn={"pk","disabled","hdmc","jbbm","jbsj","hddd","hdfzr","zdls","lxfs","xxsh"};
			service.disabled(request,userType,userDep,userName);
			this.selectPageDataByPagination(request, SjxyDektqhForm, TABNAME, VIEWNAME, outputColumn);
		}

		//读写权
		rcswKqglXskqService.writeAbled(request, "sjxyDektqh.do?method=shDektqh");
		//申请时间
		request.setAttribute("nowTime", GetTime.getNowTime2());
		return mapping.findForward("shDektqh");
	}
	
	/**
	 * method fkDektqh
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward fkDektqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		SjxyDektqhForm SjxyDektqhForm=(SjxyDektqhForm)form;
		SjxyDektqhService service=new SjxyDektqhService();
		RcswKqglXskqService rcswKqglXskqService=new RcswKqglXskqService();
		
		String userType=session.getAttribute("userType").toString();
		String userName=session.getAttribute("userName").toString();
		String userDep =session.getAttribute("userDep").toString();
		String doType=request.getParameter("doType");
		String shjg=request.getParameter("shjg");
		String shsj=GetTime.getNowTime2();
		
		
		if(SAVE.equalsIgnoreCase(doType)){
			HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
			HashMap<String,String>valueMap =new HashMap<String,String>();
			valueMap.put("xxsh", shjg);
			valueMap.put("xxshsj", shsj);
			this.auditingBatchOperation(request, primaryMap, valueMap, TABNAME);
			doType="qry";
		}
		
		//查询
		if(QUERY.equalsIgnoreCase(doType)){
			service.disabledFk(request,userType,userDep,userName);
			String []outputColumn={"pkValue","hdmc","jbbm","jbsj","hddd","hdfzr","zdls","lxfs","xxsh"};
			this.selectPageDataByPagination(request, SjxyDektqhForm, TABNAME, VIEWNAME, outputColumn);
		}

		//读写权
		rcswKqglXskqService.writeAbled(request, "sjxyDektqh.do?method=fkDektqh");
		//申请时间
		request.setAttribute("nowTime", GetTime.getNowTime2());
		return mapping.findForward("fkDektqh");
	}

	/**
	 * method oneDektqh
	 * 查看单条记录（修改，查询）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward oneDektqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		SjxyDektqhService service=new SjxyDektqhService();
		
		String userType=session.getAttribute("userType").toString();
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");
		
		//只要不为空，就进行查询操作
		if(!Base.isNull(doType)){
			this.selectPageDataByOne(request, TABNAME, VIEWNAME, pkValue);
		}
		
		service.getWrite(request,userType);
		
		if(MODIFY.equalsIgnoreCase(doType)){
			this.updateOperation(request, TABNAME);
		}

		//申请时间
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("nowTime", GetTime.getNowTime2());
		return mapping.findForward("oneDektqh");
	}
	

	/**
	 * method oneFkDektqh
	 * 查看单条反馈记录（修改，查询）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward oneFkDektqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");
		
		
		//只要不为空，就进行查询操作
		if(!Base.isNull(doType)){
			this.selectPageDataByOne(request, TABNAME, VIEWNAME, pkValue);
		}
		

		//申请时间
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("nowTime", GetTime.getNowTime2());
		return mapping.findForward("oneFkDektqh");
	}
	
	
	/**
	 * method shOneDektqh
	 * 单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward shOneDektqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		SjxyDektqhForm sjxyDektqhForm=(SjxyDektqhForm)form;
		SjxyDektqhService service=new SjxyDektqhService();
		
		String userType=session.getAttribute("userType").toString();
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");
		
		//只要不为空，就进行查询操作
		if(!Base.isNull(doType)){
			this.selectPageDataByOne(request, TABNAME, VIEWNAME, pkValue);
			service.setShZt(sjxyDektqhForm, request);
		}
		
		if(MODIFY.equalsIgnoreCase(doType)){
			this.updateOperation(request, TABNAME);
		}
		
		service.getWrite(request,userType);
		
		//申请时间
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("nowTime", GetTime.getNowTime2());
		return mapping.findForward("shOneDektqh");
	}
	
	/**
	 * method fkOneDektqh
	 * 单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward fkOneDektqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		
		String userType=session.getAttribute("userType").toString();
		SjxyDektqhForm sjxyDektqhForm=(SjxyDektqhForm)form;
		SjxyDektqhService service=new SjxyDektqhService();
		
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");
		
		//只要不为空，就进行查询操作
		if(!Base.isNull(doType)){
			this.selectPageDataByOne(request, TABNAME, VIEWNAME, pkValue);
			service.setHdxg(sjxyDektqhForm, request);
		}
		
		service.getFkWrite(request,userType);
		
		if(MODIFY.equalsIgnoreCase(doType)){
			this.updateOperation(request, TABNAME);
		}
		
		//活动效果List
		service.setHdxg(request);
		service.setTrueFalse(sjxyDektqhForm,request);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		//申请时间
		request.setAttribute("nowTime", GetTime.getNowTime2());
		return mapping.findForward("fkOneDektqh");
	}
	

	/**
	 * 申请表打印
	 * method jtjjknsPrint
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward printSqDektqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			
		    String pkValue = request.getParameter("pkValue");
			this.selectPageDataByOne(request,TABNAME, VIEWNAME,  pkValue);
			return mapping.findForward("printSqDektqh");
	}
	
	/**
	 * 申请表打印
	 * method jtjjknsPrint
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward printFkDektqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			
		    String pkValue = request.getParameter("pkValue");
			this.selectPageDataByOne(request,TABNAME, VIEWNAME,  pkValue);
			return mapping.findForward("printFkDektqh");
	}
	
	/**
	 * 数据导出
	 * method expDate
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	
	public ActionForward expDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getParameter("querylike_jbbm"));
		try {
			this.expPageData(request, response, TABNAME, VIEWNAME, null);
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
		return mapping.findForward("success");
	}
	
}