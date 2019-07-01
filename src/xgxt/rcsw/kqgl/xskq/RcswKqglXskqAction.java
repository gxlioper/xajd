/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.rcsw.kqgl.xskq;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.pjpy.nbty.wmbj.NbtyWmbjForm;
import xgxt.pjpy.nbty.wmbj.NbtyWmbjService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.xsgygl.dao.gyglDao;
import xgxt.xszz.commonN05.QueryModel;
import xgxt.xszz.commonN05.XszzCommonN05ActionForm;
import xgxt.xszz.commonN05.XszzCommonN05Service;

/** 
 * MyEclipse Struts
 * Creation date: 07-19-2010
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class RcswKqglXskqAction extends BaseAction {
	/*
	 * Generated Methods
	 */
	protected static final String QUERY = "qry";//页面查询操作判断标志
	
	protected static final String DELETE = "del";//页面删除操作判断标志
	
	protected static final String VIEW = "view";//页面单个显示详细操作判断标志
	
	protected static final String MODIFY = "modi";//页面修改操作判断标志

	protected static final String SAVE = "save";//页面保存操作判断标志
	
	protected static final String TABNAME = "RCSW_KQGL_XSKQ";//页面保存操作判断标志
	
	protected static final String VIEWNAME = "view_rcsw_kqgl_xskq";//页面保存操作判断标志
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward rcswKqglXskqSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		RcswKqglXskqService service=new RcswKqglXskqService();
		XsxxglService xsxxglService=new XsxxglService();
		String xqmc=service.getXqMc(Base.currXq);
		HashMap<String,String> map = new HashMap<String, String>();
		String userOnLine=session.getAttribute("userOnLine").toString();

		String userName=session.getAttribute("userName").toString();
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("save_xn")+request.getParameter("save_xq")+request.getParameter("save_xh");
		
		String xh=request.getParameter("xh");
		
		if("student".equalsIgnoreCase(userOnLine)){
			   xh=userName;														//如果登陆的是学生将xh设置成
		}
		
		if(SAVE.equalsIgnoreCase(doType)){
									//插入后，再查询一遍
			this.insertOperation(request, TABNAME);								//保存荣誉称号信息		
			request.setAttribute("pkValue", pkValue);					//用于打印的pkValue
		}
		
		request.setAttribute("nowTime", GetTime.getNowTime2());
		map=xsxxglService.selectStuinfo(xh);					//获取学生表信息存入Map
		request.setAttribute("rs", map);
		
		service.getGygly(request,xh);
		service.writeAbled(request, "rcswKqglXskq.do?method=rcswKqglXskqSq");
		//当前年度，学年，学期
		request.setAttribute("xn", Base.currXn);							
		request.setAttribute("nd", Base.currNd);								
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", xqmc);
		//请假列表加载
		request.setAttribute("qjlxList",service.getQjlxList());	
		return mapping.findForward("rcswKqglXskqSq");
	}
	
	/**
	 * method rcswKqglXskqSh
	 * 学生考勤审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward rcswKqglXskqSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		RcswKqglXskqForm rcswKqglXskqForm=(RcswKqglXskqForm)form;
		RcswKqglXskqService service=new RcswKqglXskqService();
		HttpSession session=request.getSession();
		String userType =session.getAttribute("userType").toString();
		String doType=request.getParameter("doType");
		String userDep=session.getAttribute("userDep").toString();
		String shjg=request.getParameter("shjg");
		String userName=session.getAttribute("userName").toString();
		
		if(MODIFY.equalsIgnoreCase(doType)){		

			
			//获取要修改值的valueMap
			HashMap<String, String> valueMap = (HashMap)service.getValueMap(request, 
					rcswKqglXskqForm, userName, shjg, userType);
			//获取pkValue
			HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
			this.auditingBatchOperation(request, primaryMap, valueMap, TABNAME);
			//如果是学院用户登陆
			if("xy".equalsIgnoreCase(userType)){
				//将请假天数小于3的学生的shzt设置成审核结果
				service.updateShzt(shjg);
			}
			doType="qry";
		}
		
		//条件查询  审核信息
		if(QUERY.equalsIgnoreCase(doType)){
			//获取输出的字段
			String[]outputColumn=service.getStuQjxx(request,rcswKqglXskqForm, userName, userType);
			if(null!=outputColumn){
				this.selectPageDataByPagination(request, rcswKqglXskqForm, TABNAME, VIEWNAME, outputColumn);
			}
		}
		
		if("xy".equalsIgnoreCase(userType) ){
			rcswKqglXskqForm.setXydm(userDep);
		}
		
		request.setAttribute("isGygly", service.isGyGly(userName));
		rcswKqglXskqForm.setXn(Base.currXn);
		rcswKqglXskqForm.setXq(Base.currXq);
		service.writeAbled(request, "rcswKqglXskq.do?method=rcswKqglXskqSh");
		request.setAttribute("qjlxList",service.getQjlxList());	
		gyglDao.getLdLcQshList(request);
		FormModleCommon.getWriteAbleAndTitle(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("xnList", Base.getXnndList());//学年列表	
		return mapping.findForward("rcswKqglXskqSh");
	}
	
	/**
	 * method rcswKqglXskqResult
	 * 学生考勤 结果查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward rcswKqglXskqResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RcswKqglXskqForm rcswKqglXskqForm=(RcswKqglXskqForm)form;
		HttpSession session=request.getSession();
		RcswKqglXskqService service=new RcswKqglXskqService();
		
		String userName=session.getAttribute("userName").toString();
		String userType=session.getAttribute("userType").toString();
		String userDep=session.getAttribute("userDep").toString();
		String userOnLine=session.getAttribute("userOnLine").toString();
		String doType=request.getParameter("doType");
		
		
		if(DELETE.equalsIgnoreCase(doType)){
			this.deleteOperation(request, TABNAME);
			doType="qry";
		}
		
		if("student".equalsIgnoreCase(userOnLine)){
			rcswKqglXskqForm.setXh(userName);
		}
		
		if(QUERY.equalsIgnoreCase(doType)){
			
			service.disabled(request, service, rcswKqglXskqForm, userOnLine, userName, userType);
			if(("xy".equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType)
					||"admin".equalsIgnoreCase(userType)) && !service.isGyGly(userName)){
				String[]outputColumn=new String[] {"pk","disabled","xn","xqmc","xh",
					"xm","xymc","zymc","bjmc","qjlxmc","qjsj","shzt"};
				this.selectPageDataByPagination(request, rcswKqglXskqForm, TABNAME, VIEWNAME, outputColumn);
			}else if("student".equalsIgnoreCase(userOnLine)){
				String[]outputColumn=new String[] {"pk","disabled","xn","xqmc","xh",
						"xm","xymc","zymc","bjmc","qjlxmc","qjsj","shzt"};
				this.selectPageDataByPagination(request, rcswKqglXskqForm, TABNAME, VIEWNAME, outputColumn);
			}
		}
		if("xy".equalsIgnoreCase(userType)){
			rcswKqglXskqForm.setXydm(userDep);
		}
		
		service.writeAbled(request,"rcswKqglXskq.do?method=rcswKqglXskqResult");
		request.setAttribute("qjlxList",service.getQjlxList());	
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);						
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("rcswKqglXskqResult");
	}

	
	//	单条记录显示及修改
	public ActionForward rcswKqglXskqShOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RcswKqglXskqForm rcswKqglXskqForm=(RcswKqglXskqForm)form;
		HttpSession session=request.getSession();
		RcswKqglXskqService rcswKqglXskqService=new RcswKqglXskqService();
	
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		String userName=session.getAttribute("userName").toString();
		String userType=session.getAttribute("userType").toString();
		String nowTime=GetTime.getNowTime2();
		String savedd=request.getParameter("save_xxsh");
		//修改操作
		if(MODIFY.equalsIgnoreCase(doType)){				
			this.updateOperation(request, TABNAME);
		}
		
		//单条记录显示
		this.selectPageDataByOne(request, TABNAME, VIEWNAME, pkValue);
		//判断学院用户在二级审核时的读写权
		rcswKqglXskqService.getWrite(request, userType, userName);
		//在FORM中设置单个审核时的SHJG
		rcswKqglXskqService.setShjg(request, rcswKqglXskqForm,userName, userType);
		
		request.setAttribute("nowTime", nowTime);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("rcswKqglXskqShOne");
	}
	
	//学生考勤统计
	public ActionForward rcswKqglXskqTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RcswKqglXskqForm rcswKqglXskqForm=(RcswKqglXskqForm)form;
		HttpSession session=request.getSession();
		RcswKqglXskqService service=new RcswKqglXskqService();

		String userType=session.getAttribute("userType").toString();
		String userOnLine=session.getAttribute("userOnLine").toString();
		String userDep=session.getAttribute("userDep").toString();
		String userName=session.getAttribute("userName").toString();
		String doType=request.getParameter("doType");
		
		if("student".equals(userOnLine)){
			rcswKqglXskqForm.setXh(userName);
		}
		
		//判断用户是否是寝室管理员 true(是)
		if(QUERY.equalsIgnoreCase(doType)){
			service.getStuTj(request, rcswKqglXskqForm);
		}
		
		if("xy".equalsIgnoreCase(userType)){
			rcswKqglXskqForm.setQueryequals_xydm(userDep);
		}
		
		request.setAttribute("path","rcswKqglXskq.do?method=rcswKqglXskqTj");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);						
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("rcswKqglXskqTj");
	}
	
	//单条记录显示及修改
	public ActionForward rcswKqglXskqOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		RcswKqglXskqService rcswKqglXskqService=new RcswKqglXskqService();
		
		String doType =request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");
		String userType=session.getAttribute("userType").toString();
		String userName=session.getAttribute("userName").toString();
		String userOnLine=session.getAttribute("userOnLine").toString();
		//修改操作
		if(MODIFY.equalsIgnoreCase(doType)){
			
			this.updateOperation(request, TABNAME);
		}
		
		this.selectPageDataByOne(request, TABNAME, VIEWNAME, pkValue);
		
		if("student".equals(userOnLine)){
			//判断学生的读写权
			rcswKqglXskqService.getWriteByStu(request, userType, userName);
		}else{
			//判断学院用户在二级审核时的读写权
			rcswKqglXskqService.getWrite(request, userType, userName);
		}
		HashMap<String,String> map=(HashMap<String,String>)request.getAttribute("rs");				//获取学生表信息存入Map
		rcswKqglXskqService.getGygly(request,map.get("xh"));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("rcswKqglXskqOne");
	}
	
	/**
	 * 统计
	 * knsrd2Print
	 * author 裘力俊
	 * data 2010-7-15
	 * @throws IOException 
	 */
	public ActionForward printQjsjbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			RcswKqglXskqService rcswKqglXskqService=new RcswKqglXskqService();
			RcswKqglXskqForm rcswKqglXskqForm=(RcswKqglXskqForm)form;
		
			RcswKqglXskqModel rcswKqglXskqModel = new RcswKqglXskqModel();
			String modelPath =servlet.getServletContext().getRealPath("") + "/print/rcsw/nbwl_xskq.xls";
			response.setContentType("application/vnd.ms-excel");
			BeanUtils.copyProperties(rcswKqglXskqModel, rcswKqglXskqForm);
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			rcswKqglXskqService.printQjsjbb(rcswKqglXskqForm,rcswKqglXskqModel,request, wwb);
		    return mapping.findForward("");
	}
	
	
	public ActionForward allTjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取统计信息中的学号
		RcswKqglXskqService rcswKqglXskqService=new RcswKqglXskqService();
		RcswKqglXskqForm rcswKqglXskqForm=(RcswKqglXskqForm)form;
		
		String xh=request.getParameter("xh");
		rcswKqglXskqService.getAllStuInfo(request,rcswKqglXskqForm, xh);
		return mapping.findForward("allTjxx");
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
		
		try {
			
			this.expPageData(request, response, TABNAME, VIEWNAME, null);
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}			
		
		return mapping.findForward("success");
	}
	
	public ActionForward expDateTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			this.expPageData(request, response, "", "view_rcsw_kqgl_xsqjtj", null);
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}			
		
		return mapping.findForward("success");
	}
	
}