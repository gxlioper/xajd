package xgxt.rcgl.nbcs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.utils.String.StringUtils;

public class XfxxglAction  extends DispatchAction {
	
	/***
	 * 加载学籍信息列表
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": (xydm==null ? "" : xydm); 
		zy = zy==null ? "": (zydm==null ? "" : zydm); 
		njLocal = nj==null ? "": nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		
		request.setAttribute("writeAble", "yes");//判断用户读写权		
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
	}
	
	/***
	 * 加载时间信息列表
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendTimeList(HttpServletRequest request){
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		request.setAttribute("yfList", Base.getYfList());//月份列表
	}
	
	/**
	 * 显示学生学费信息管理页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xfxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XfxxglForm model = (XfxxglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("stu".equalsIgnoreCase(userType)){
			return xsxfxxcx(mapping, form, request, response);
		}
		
		request.setAttribute("realTable", "nbcs_xsxfxxb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		appendProperties(request, "", "", "");
		return mapping.findForward("xfxxgl");
	}
	
	/**
	 * 查询学生学费信息
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xsxfxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("stu".equalsIgnoreCase(userType)){
			model.setXh(session.getAttribute("userName").toString());
		}
		
		List<String[]> rs = service.queryXsxfxxb(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getXsxfxxbTopTr());		
		request.setAttribute("realTable", "nbcs_xsxfxxb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		return mapping.findForward("xfxxgl");
	}
	
	/**
	 * 显示学生学费信息增加页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xsxfxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglForm model = (XfxxglForm)form;
		StudentInfoService xsxx = new StudentInfoService();
		request.setAttribute("rs", xsxx.getStuInfo(request.getParameter("xh")));
		
		model.setNd(Base.currNd);
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward("xsxfxxAdd");
	}
	
	/**
	 * 显示学生学费信息修改页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xsxfxxModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryXsxfxxbOne(model));//按主键查询信息
		return mapping.findForward("xsxfxxModi");
	}
	
	/**
	 * 学生学费信息保存
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveXsxfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		String page = "xsxfxxModi";
		String doType = request.getParameter("doType");
		
		
		model.setPkValue(model.getNd()+model.getXh());//设置主键值		
		page = StringUtils.isNotNull(doType) && "add".equalsIgnoreCase(doType) ? "xsxfxxAdd" : page;
		
		request.setAttribute("result", service.saveXsxfxxb(model,request));//信息保存
		request.setAttribute("rs", service.queryXsxfxxbOne(model));
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward(page);
	}
	
	/**
	 * 学生学费信息删除
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward delXsxfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		
		request.setAttribute("result", service.delXsxfxxb(model));//删除信息
		return xsxfxxcx(mapping, form, request, response);
	}
	
	/**
	 * 学生学费信息查询导出
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expXsxfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		model.setNd(request.getParameter("nd"));
		model.setXydm(request.getParameter("xh"));
		
		String[] colList = {"nd","xh","xm","xb","xz","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","xxxs","pycc","zkzh","qjxf","qtf","qjdgf","欠缴合计","jfzt"};
		String[] colListCN = service.getColCN("view_nbcs_xsxfxxb", colList);
		List<String[]> rs = service.queryXsxfxxbForExp(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * 显示学生教材费结算管理页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward jcfjsgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XfxxglForm model = (XfxxglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("stu".equalsIgnoreCase(userType)){
			return jcfjsxxcx(mapping, form, request, response);
		}
		
		request.setAttribute("realTable", "jcfjsb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		appendProperties(request, "", "", "");
		return mapping.findForward("jcfjsgl");
	}
	
	/**
	 * 查询教材费结算信息
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward jcfjsxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("stu".equalsIgnoreCase(userType)){
			model.setXh(session.getAttribute("userName").toString());
		}
		
		List<String[]> rs = service.queryJcfjsb(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getJcfjsbTopTr());		
		request.setAttribute("realTable", "jcfjsb");
		request.setAttribute("writeAble", "yes");
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		return mapping.findForward("jcfjsgl");
	}
	
	/**
	 * 显示教材费结算增加页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward jcfjsxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglForm model = (XfxxglForm)form;
		StudentInfoService xsxx = new StudentInfoService();
		request.setAttribute("rs", xsxx.getStuInfo(request.getParameter("xh")));
		
		model.setNd(Base.currNd);
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward("jcfjsxxAdd");
	}
	
	/**
	 * 显示教材费结算修改页面
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward jcfjsxxModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryJcfjsbOne(model));//按主键查询信息
		return mapping.findForward("jcfjsxxModi");
	}
	
	/**
	 * 教材费结算信息保存
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveJcfjsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		String page = "jcfjsxxModi";
		String doType = request.getParameter("doType");
		
		
		model.setPkValue(model.getNd()+model.getXh());//设置主键值		
		page = StringUtils.isNotNull(doType) && "add".equalsIgnoreCase(doType) ? "jcfjsxxAdd" : page;
		
		request.setAttribute("result", service.saveJcfjsb(model,request));//信息保存
		request.setAttribute("rs", service.queryJcfjsbOne(model));
		request.setAttribute("ndList", Base.getXnndList());//学年列表
		return mapping.findForward(page);
	}
	
	/**
	 * 教材费结算信息删除
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward delJcfjsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		
		request.setAttribute("result", service.delJcfjsb(model));//删除信息
		return jcfjsxxcx(mapping, form, request, response);
	}
	
	/**
	 * 教材费结算信息查询导出
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expJcfjsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XfxxglService service = new XfxxglService();
		XfxxglForm model = (XfxxglForm)form;
		model.setNd(request.getParameter("nd"));
		model.setXydm(request.getParameter("xh"));
		
		String[] colList = {"nd","xh","xm","xb","xz","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","jyje"};
		String[] colListCN = service.getColCN("view_jcfjsb", colList);
		List<String[]> rs = service.queryJcfjsbForExp(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
}
