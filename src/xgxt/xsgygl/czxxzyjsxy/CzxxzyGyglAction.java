/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2010-1-20 上午10:42:45</p>
 */
package xgxt.xsgygl.czxxzyjsxy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.xsgygl.dao.gyglDao;

public class CzxxzyGyglAction extends DispatchAction {
	public ActionForward  dormcheckManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
		CzxxzyGyglService service = new CzxxzyGyglService();
		CzxxzyGyglForm myform = (CzxxzyGyglForm)form;
		String xn = myform.getXn();
		String xq = myform.getXq();
		if(xn==null){
			myform.setXn(Base.currXn);
		}
		if(xq==null){
			myform.setXq(Base.currXq);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){

			WsjcModel model = new WsjcModel();
			BeanUtils.copyProperties(model,myform);
			List<HashMap<String,String>> rs = service.serv_dormcheckQery(model);
			List<HashMap<String, String>> topTr = service.getQswsjcTopTr(model.getJcsj());
			request.setAttribute("rsNum", (rs != null && !"".equals(rs))?rs.size():"0");   		 
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
		}

		gyglDao.getLdLcQshList(request);
		gyglDao.getXnxqList(request);
		return mapping.findForward("dormcheckManage");
	}
	public ActionForward dormcheckSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		CzxxzyGyglService service = new CzxxzyGyglService();
		CzxxzyGyglForm myform = (CzxxzyGyglForm)form;
		WsjcModel model = new WsjcModel();
		BeanUtils.copyProperties(model,myform);
		if("save".equalsIgnoreCase(request.getParameter("doType"))){
			service.serv_dormCheckSave(model);
		}
		return dormcheckManage(mapping, form, request, response);
	}
	public ActionForward dormcheckResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		CzxxzyGyglService service = new CzxxzyGyglService();
		CzxxzyGyglForm myform = (CzxxzyGyglForm)form;
		String xn = myform.getXn();
		String xq = myform.getXq();
		if(xn==null){
			myform.setXn(Base.currXn);
		}
		if(xq==null){
			myform.setXq(Base.currXq);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			WsjcModel model = new WsjcModel();
			BeanUtils.copyProperties(model,myform);
			List<HashMap<String,String>> rs = service.serv_dormcheckResult(model);
			List<HashMap<String, String>> topTr = service.getjcResultTopTr();
			request.setAttribute("rsNum", (rs != null && !"".equals(rs))?rs.size():"0");   		 
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
		}

		gyglDao.getLdLcQshList(request);
		gyglDao.getXnxqList(request);
		return mapping.findForward("dormcheckResult");
	}	
	public ActionForward dormpointManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		CzxxzyGyglService service = new CzxxzyGyglService();
		CzxxzyGyglForm myform = (CzxxzyGyglForm)form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep  = session.getAttribute("userDep").toString();
		if("xy".equalsIgnoreCase(userType)){
			myform.setXydm(userDep);
		}
		SsgffModel model = new SsgffModel();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = myform.getXn();
		String xq = myform.getXq();
		if(xn==null){
			myform.setXn(Base.getJxjsqxn());
		}
		if(xq==null){
			myform.setXq(Base.getJxjsqxq());
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model,myform);
			rs = service.serv_dpointResearch(model);
			topTr = service.getDormpointTopTr();
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", rs);
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xnList", CommonQueryDAO.getFullXnlist());
		gyglDao.getXyZyBjxx(request);
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);
		return mapping.findForward("dormpointManage");
	}
	public ActionForward gffAutoCount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		CzxxzyGyglForm myform = (CzxxzyGyglForm)form;
		CzxxzyGyglService service = new CzxxzyGyglService();
		HttpSession session = request.getSession();
		//String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep  = session.getAttribute("userDep").toString();
		if("xy".equalsIgnoreCase(userType)){
			myform.setXydm(userDep);
		}
		String xn = myform.getXn();
		String xq = myform.getXq();
		if(xn==null){
			myform.setXn(Base.getJxjsqxn());
		}
		if(xq==null){
			myform.setXq(Base.getJxjsqxq());
		}
		if("doCout".equalsIgnoreCase(request.getParameter("doType"))){
			SsgffModel model = new SsgffModel();
			BeanUtils.copyProperties(model,myform);
			boolean done = service.serv_autocount(model);
			request.setAttribute("done",done);
		}
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xnList", CommonQueryDAO.getFullXnlist());	
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("userType",userType);
		return mapping.findForward("gffAutoCount");
	}
	public ActionForward expShGFF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, WriteException{
		CzxxzyGyglService service = new CzxxzyGyglService();
		CzxxzyGyglForm myform = (CzxxzyGyglForm)form;
		SsgffModel model = new SsgffModel();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		try {
			BeanUtils.copyProperties(model,myform);
			service.serv_expShGFF(wwb, model);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}	
		return mapping.findForward("");
	}
}
