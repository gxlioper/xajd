/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.pjpy.zjsyzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江商业职业技术学院评奖评优Action
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-16</p>
 */
public class PjpyZjsyzyAction extends CommonAction {
	/**
	 * 公用方法:在REQUEST中存放页面所要加载的LIST属性
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @param xq
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xydm==null ? "": xydm; 
		zy = zydm==null ? "": zydm; 
		njLocal = nj==null ? "": nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();
		request.setAttribute("writeAble", "yes");//判断用户读写权
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		request.setAttribute("userType", userType);//用户类型
	}
	
	/** 
	 * 综合素质测评成绩增加获取学生基本信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getZhszcpStuinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		String xh = request.getParameter("xh");
		String from = request.getParameter("from");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");		
		
		request.setAttribute("rs", service.getZhszcpStuinfo(xh));
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xnList", service.getXnndList());
		request.setAttribute("xqList", service.getXqList());
		return new ActionForward(from);
	}
	
	/** 
	 * 显示单个学生奖学金审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showCheckPrise(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		String pkVal = request.getParameter("pkVal");
		String pk = "xn||nd||xh||jxjdm";
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = service.getJxjshInfo(pk,pkVal,userType);
		boolean nfhdjxj = service.nfhdtdjxj(rs.get("xh"), rs.get("xn"),rs.get("nd"), rs.get("xq"));
		String pm = service.getCpcppm(rs.get("xh"), rs.get("xn"),rs.get("nd"), rs.get("xq"));
		if (nfhdjxj) {
			pm = "无须参评";
			request.setAttribute("nfhdjxj", "是");
		} else {
			request.setAttribute("nfhdjxj", "否");
		}
		request.setAttribute("pm", pm);
		List<String[]> cjList = service.getStuKccjxx(false, rs.get("xh"), rs.get("xn"), rs.get("xq"));
		List<String[]> wjList = service.getStuWjcfxx(rs.get("xh"), rs.get("xn"), rs.get("xq"));
		request.setAttribute("rs", rs);
		request.setAttribute("cjList", cjList);
		request.setAttribute("wjList", wjList);
		request.setAttribute("chkList", service.getChkList(3));
		return mapping.findForward("checkOne");
	}
	
	/** 
	 * 单个学生奖学金审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward priseCheckOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		HttpSession session = request.getSession();
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;
		boolean flag = false;
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String xq = request.getParameter("xq");
		String jxjdm = request.getParameter("jxjdm");
		String xxshyj = DealString.toGBK(request.getParameter("xxyj"));
		String xyshyj = DealString.toGBK(request.getParameter("xyyj"));
		String fdyyj = DealString.toGBK(request.getParameter("fdyyj"));
		String yesNo = DealString.toGBK(request.getParameter("yesNo"));
		String pk = "xh||xn||nd||jxjdm";
		String pkValue = xh+xn+nd+jxjdm;
		String userType = session.getAttribute("userType").toString();
		
		model.setXh(xh);
		model.setXn(xn);
		model.setNd(nd);
		model.setJxjdm(jxjdm);
		model.setPk(pk);
		model.setPkValue(pkValue);
		model.setYesNo(yesNo);
		model.setUserType(userType);
		model.setXxshyj(xxshyj);
		model.setXyshyj(xyshyj);
		model.setFdyyj(fdyyj);
		model.setXydm(request.getParameter("bmdm"));
		model.setNj(request.getParameter("nj"));
		model.setXq(xq);
		model.setSh(DealString.toGBK(request.getParameter("sh")));
		String jxjmc = DealString.toGBK(request.getParameter("jxjmc"));
		String mes = service.saveCheck(model,request, jxjmc);
		flag = mes == null || "".equalsIgnoreCase(mes) ? true : false;
		request.setAttribute("mes", mes);
		request.setAttribute("result", flag);
		request.setAttribute("pkVal", pkValue);
		return showCheckPrise(mapping, form, request, response);
	}
	
	/** 
	 * 显示单个学生荣誉称号审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward showCheckRych(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		HttpSession session = request.getSession();
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;
		String userType = session.getAttribute("userType").toString();
		
		String pk ="xn||nd||xh||rychdm";
		String pkValue = request.getParameter("pkVal");
		
		model.setPk(pk);
		model.setPkValue(pkValue);
		model.setUserType(userType);
		
		request.setAttribute("rs", service.getRychshInfo(model));
		request.setAttribute("chkList", service.getChkList(3));
		return mapping.findForward("rychCheckOne");
	}
	
	/** 
	 * 保存荣誉称号审核结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward saveCheckCredit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		HttpSession session = request.getSession();
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;
		String userType = session.getAttribute("userType").toString();
		
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String xq = request.getParameter("xq");
		String rychdm = request.getParameter("rychdm");
		String rychmc = DealString.toGBK(request.getParameter("rychmc"));
		String yesNo = DealString.toGBK(request.getParameter("yesNo"));
		
		model.setXh(xh);
		model.setXn(xn);
		model.setNd(nd);
		model.setXq(xq);
		model.setRychdm(rychdm);
		model.setUserType(userType);
		model.setYesNo(yesNo);
		model.setRychmc(rychmc);
		
		request.setAttribute("result", service.saveCheckCredit(model,request));
		return showCheckRych(mapping, form, request, response);
	}
	
	/** 
	 * 比赛项目维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward bsxmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		HttpSession session = request.getSession();
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;
		model.setSsjfxm(DealString.toGBK(model.getSsjfxm()));
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String go = request.getParameter("go");
		String page = "bsxmwh";
		String realTable = "bsxmb";
		String tableName = "bsxmb";
		String writeAble = Base.getWriteAble(request);	
		
		if(userType != null && userType.equalsIgnoreCase("xy")){
			realTable = "xybsxmjfxxb";		
			tableName = "view_xybsxmjfxxb";
		}	
		
		model.setUserDep(userDep);
		model.setUserType(userType);
		model.setTableName(tableName);
		if(go!=null && !"".equalsIgnoreCase(go)){
			//查询
			List rs = service.querryBsxm(model);
			List topTr = service.getBsxmToptr(userType);			
			
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			request.setAttribute("rsNum", rs.size());
		}
		
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", realTable);
		request.setAttribute("ssjfxmList", service.getSsjfxmList());//所属项目
		request.setAttribute("czlxList", service.getCzlxList());//操作类型
		return mapping.findForward(page);
	}
	
	/** 
	 * Method showAddBsxm 显示比赛项目增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward showAddBsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String page = "addBsxm";
		String ssjfxm = DealString.toGBK(model.getSsjfxm());
		
		model.setXmdm(DealString.toGBK(model.getXmdm()));
		model.setXmmc(DealString.toGBK(model.getXmmc()));
		model.setSsjfxm(DealString.toGBK(model.getSsjfxm()));
		model.setUserDep(userDep);
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			page = "addBsxmJf";
			model.setCzlx(service.showXyBsxmInfo("xmdm", ssjfxm).get("czlx"));
		}
		
		request.setAttribute("ssjfxmList", service.getSsjfxmList());
		request.setAttribute("bsxmList", service.getBsxmList("bsxmb",new String[]{"xmdm","xmmc"},ssjfxm));
		request.setAttribute("czlxList", service.getCzlxList());
		request.setAttribute("rs", model);
		return mapping.findForward(page);
	}
	
	/** 
	 * 显示比赛项目信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward showBsxmInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {	
		HttpSession session = request.getSession();
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		String pk = "xmdm";		
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String page = "addBsxm";
		
		HashMap<String, String> rs = new HashMap<String, String>();
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			rs = service.showXyBsxmInfo(pk, pkValue);
			page = "addBsxmJf";
			pk = "xmdm||xydm";
			pkValue = pkValue + userDep;
		}else{
			rs = service.showBsxmInfo(pk, pkValue);
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("ssjfxmList", service.getSsjfxmList());
		request.setAttribute("czlxList", service.getCzlxList());
		request.setAttribute("bsxmList", service.getBsxmList("bsxmb",new String[]{"xmdm","xmmc"},""));
		return mapping.findForward(page);
	}
	
	/** 
	 *保存比赛项目
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward saveBsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		model.setUserDep(userDep);
		model.setUserType(userType);
		
		request.setAttribute("result", service.saveBsxm(model, request));		
		return showAddBsxm(mapping, form, request, response);
	}
	
	
	
	/** 
	 * 删除比赛项目
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward delBsxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyService service = new PjpyZjsyzyService();		
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;
		String[] pk = model.getCbv();
		@SuppressWarnings("unused")
		String writeAble = Base.getWriteAble(request);
		
		request.setAttribute("result", service.delBsxm(pk, request));
		request.setAttribute("writeAble", "yes");//writeAble
		return bsxmwh(mapping, form, request, response);
	}
	
	/** 
	 * 初始化分数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward initMark(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		
		request.setAttribute("result", service.initMark(userDep, request));
		return bsxmwh(mapping, form, request, response);
	}
	
	///////////////////////////////////////////////////////////////////
	/** 
	 * 素质积分维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward zyszjfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String nj = model.getNj();
		String realTable = "xsszjfb";
		String tableName = "view_xsszjf";
		String go = request.getParameter("go");
		
		model.setRealTable(realTable);
		model.setTableName(tableName);		
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			model.setXydm(xydm);
		}
		
		if(go != null && "go".equalsIgnoreCase(go)){//数据查询
			List rs = service.querrySzjf(model);//获取数据集合
			request.setAttribute("topTr", service.getSzjfToptr());//获取表头
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		request.setAttribute("ssxmList", service.getSsjfxmList());
		request.setAttribute("czlxList", service.getCzlxList());
		appendProperties(request, xydm, zydm, nj);		
		return mapping.findForward("zyszjfwh");
	}
	
	/** 
	 * 显示素质积分增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward addSzjf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		PjpyZjsyzyForm model = (PjpyZjsyzyForm)form;
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		String tableName = "bsxmb";
		String[] columns = {"xmdm","xmmc"};
		String xh = request.getParameter("xh");
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		List bxsmList = new ArrayList();
		
		if(xh != null && !"".equalsIgnoreCase(xh)){
			HashMap<String, String> map = service.getStuDetails(xh);
			model.setXymc(map.get("xymc"));
			model.setZymc(map.get("zymc"));
			model.setXm(map.get("xm"));
			model.setBjmc(map.get("bjmc"));
			model.setNj(map.get("nj"));
			model.setXm(map.get("xm"));
			model.setXb(map.get("xb"));
			model.setXh(map.get("xh"));
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			userDep = map.get("xydm");
		}
		
		if((userType != null && !"xx".equalsIgnoreCase(userType) && !"admin".equalsIgnoreCase(userType)) || (xh != null && !"".equalsIgnoreCase(xh))){//非学校用户 或 选择了学生
			bxsmList = service.getBxsmListByXy(userDep);
		}else{//学校用户 且 没有选择学生
			bxsmList = service.getBsxmList(tableName, columns, "");
		}
		
		request.setAttribute("rs", model);
		request.setAttribute("bsxmList",bxsmList);
		return mapping.findForward("szjf");
	}
	
	/** 
	 * 显示素质积分修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward modiSzjf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		String pkValue = request.getParameter("pkValue");
		String tableName = "bsxmb";
		String[] columns = {"xmdm","xmmc"};
		
		request.setAttribute("type", "modi");
		request.setAttribute("rs", service.getSzjfInfo(pkValue));
		request.setAttribute("bsxmList",service.getBsxmList(tableName, columns, ""));
		return mapping.findForward("szjf");
	}
	
	/** 
	 * 删除素质积分
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward delSzjf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		String pk = request.getParameter("pk");
		
		request.setAttribute("result", service.delSzjf(pk,request));
		return zyszjfwh(mapping, form, request, response);
	}
	
	/** 
	 * 保存素质积分信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward saveSzjf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;
		String doType = request.getParameter("doType");
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		String tableName = "bsxmb";
		
		if(doType != null && "modi".equalsIgnoreCase(doType)){//修改操作
			model.setXhO(request.getParameter("xhO"));
			model.setXmlbdmO(request.getParameter("xmlbdmO"));
			model.setSjO(request.getParameter("sjO"));
			model.setXn(request.getParameter("xn"));
			model.setXq(request.getParameter("xq"));
		}
		
		request.setAttribute("result", service.saveSzjf(model,doType,request));
		request.setAttribute("bsxmList",service.getBsxmList(tableName, new String[]{"xmdm","xmmc"}, ""));
		request.setAttribute("rs", new PjpyZjsyzyForm());
		return mapping.findForward("szjf");
	}
	
	/** 
	 * 评奖评优黑名单维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward pjpyhmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;
		
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String nj = model.getNj();
		String realTable = "pjpyhmd";
		String tableName = "view_pjpyhmd";
		String go = request.getParameter("go");
		String xm = DealString.toGBK(model.getXm());
		String xh = DealString.toGBK(model.getXh());
		
		model.setRealTable(realTable);
		model.setTableName(tableName);	
		model.setXm(xm);
		model.setXh(xh);
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			model.setXydm(xydm);
		}
		
		if(go != null && "go".equalsIgnoreCase(go)){//数据查询			
			List rs = service.querryPjpyhmd(model);//获取数据集合			
			request.setAttribute("topTr", service.getPjpyhmdToptr());//获取表头
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		appendProperties(request, xydm, zydm, nj);		
		return mapping.findForward("pjpyhmd");
	}
	
	/** 
	 * 显示评奖评优黑名单增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward addPjpyhmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyForm model = (PjpyZjsyzyForm)form;
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		String xh = request.getParameter("xh");
		
		if(xh != null && !"".equalsIgnoreCase(xh)){
			HashMap<String, String> map = service.getStuDetails(xh);
			model.setXymc(map.get("xymc"));
			model.setZymc(map.get("zymc"));
			model.setXm(map.get("xm"));
			model.setBjmc(map.get("bjmc"));
			model.setNj(map.get("nj"));
			model.setXm(map.get("xm"));
			model.setXb(map.get("xb"));
			model.setXh(map.get("xh"));
		}
		
		request.setAttribute("rs", model);
		request.setAttribute("xmList", service.getXmList());
		return mapping.findForward("hmd");
	}
	
	/** 
	 * 显示评奖评优黑名单修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward modiPjpyhmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		String pkValue = request.getParameter("pkValue");
		
		request.setAttribute("type", "modi");
		request.setAttribute("rs", service.getPjpyhmdInfo(pkValue));
		request.setAttribute("xmList", service.getXmList());
		return mapping.findForward("hmd");
	}
	
	/** 
	 * 删除评奖评优黑名单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward delPjpyhmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		String pk = request.getParameter("pk");
		
		request.setAttribute("result", service.delPjpyhmd(pk,request));
		return pjpyhmd(mapping, form, request, response);
	}
	
	/** 
	 * 保存评奖评优黑名单信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward savePjpyhmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;
		String doType = request.getParameter("doType");
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		String xmmcO = request.getParameter("xmmcO");
		model.setXmmcO(xmmcO);
		
		request.setAttribute("result", service.savePjpyhmd(model,doType,request));
		request.setAttribute("rs", new PjpyZjsyzyForm());
		request.setAttribute("xmList", service.getXmList());
		return mapping.findForward("hmd");
	}
	
	/** 
	 * 自动计算综合素质测评分数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward autoAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyZjsyzyForm model = (PjpyZjsyzyForm) form;	
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		request.setAttribute("result", service.autoAccount(model));
		return zyszjfwh(mapping, form, request, response);
	}
	
	/**
	 * 奖学金申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward jxjApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String pk = "xn||nd||jxjdm||xh";//主键
		String xh = DealString.toGBK(request.getParameter("xh"));		
		boolean jwbb = false;//学年
		
		if("student".equalsIgnoreCase(userOnLine)){
			xh = userName;
		}
		
		map = service.selectJxjsqOne(pk,pkValue);
		if(map == null || map.size()<1){
			//查询学生基本信息和当前学年学期信息
			map = service.selectStuBase(xh);
		}
		request.setAttribute("pkValue", map.get("xh")+map.get("xn")+map.get("jxjdm"));
		//查询出的数据列表
		request.setAttribute("rs", map);
		//奖学金列表
		appendJxjList(request);
		//违纪处分信息
		request.setAttribute("wjcf", service.getStuWjcfxx(map.get("xh"),map.get("xn"),map.get("xq")));
		//学习成绩信息
		request.setAttribute("kccj", service.getStuKccjxx(jwbb,map.get("xh"),map.get("xn"),map.get("xq")));
		return mapping.findForward("jxjApply");
	}
	
	/**
	 * 班级综合素质测评信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjzhszcpInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xn = DealString.toGBK(request.getParameter("xn"));
		String xq = DealString.toGBK(request.getParameter("xq"));
		
		PjpyZjsyzyService service = new PjpyZjsyzyService();
		List<String[]> rs = service.getBjZhszcpinfo(xh,xn,xq);
		request.setAttribute("rs", rs);
		request.setAttribute("bjmc", service.getStubjmc(xh));
		return mapping.findForward("bjzhszcpinfo");
	}
}