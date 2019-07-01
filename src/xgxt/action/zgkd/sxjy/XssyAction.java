package xgxt.action.zgkd.sxjy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class XssyAction extends DispatchAction{
	
	/**
	* <p>Title: 学工管理系统</p>
	* <p>Description: 学生信息管理思想教育-中国矿大-学生生涯action类</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author 鲁宁
	* @version 1.0
	*/
	

	public ActionForward setXssyxgsj(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    时间设置显示
		XssyService service          =    new XssyService();
		
		HashMap<String, String> rs = service.getSjszList();
	
		request.setAttribute("rs", rs);
		return mapping.findForward("setXssyxgsj");		
	}
	
	public ActionForward saveSyxgsj(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    时间设置保存
		XssyService service          =    new XssyService();
		XssyForm myForm              =    (XssyForm) form;
		XssyModel myModel            =    new XssyModel();
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted             =    service.saveSyxgsj(myModel,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return setXssyxgsj(mapping, form,request,response);
	}
	
	//学生生涯设计首页
	public ActionForward xssysj(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    学生身涯设计
		HttpSession session        = request.getSession();
		String userType            = (String) session.getAttribute("userType");
		String xh                  = (String) session.getAttribute("userName");
		String xn                  = (String) session.getAttribute("LoginXn");
		String pk                  = DealString.toGBK(request.getParameter("pk"));
		XssyService service        =    new XssyService();
	//	  判断是否是学生
		
		String view =  DealString.toGBK(request.getParameter("view"));
		
		if(!userType.equalsIgnoreCase("stu")&&pk.equalsIgnoreCase("")) {
			request.setAttribute("errMassage", "该模块只能由学生填写");
			return mapping.findForward("xssysj");		
		}else if (!pk.equalsIgnoreCase("")){
			xh = pk;
		}
		
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = service.getXssysj(xh, xn,view);
		
		//查看学生生涯设计的表里有没有学生的数据
		if(service.isStuAlreadyWriteSj(xh)){
			request.setAttribute("alreadyWrite", "yes");
		}else{
			request.setAttribute("alreadyWrite", "no");
		}
		if(!userType.equalsIgnoreCase("stu")) {
			if (!pk.equalsIgnoreCase("")){
				rs.put("jnjs", "js"+((rs.get("jnjs") == null) ? "" : rs.get("jnjs")));  //辅导员
			}
		}else if (userType.equalsIgnoreCase("stu")){
			rs.put("jnjs", ((rs.get("jnjs") == null) ? "" : rs.get("jnjs")));
		}
		if (!pk.equalsIgnoreCase("")){
			
		}
		if(null!=rs.get("errMassage")) {
			request.setAttribute("errMassage", rs.get("errMassage"));
			return mapping.findForward("xssysj");		
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("xssysj");		
	}
	
	public ActionForward xssysjSave(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    学生身涯设计保存
		HttpSession session        = request.getSession();
		String userName            = (String) session.getAttribute("userNameReal");
		
		XssyForm myForm            =    (XssyForm) form;
		XssyService service        =    new XssyService();
		XssyModel myModel          =    new XssyModel();
		BeanUtils.copyProperties(myModel,myForm);
		String xh = request.getParameter("pk");
		String jnjs = request.getParameter("jnjs");
		boolean inserted = service.saveXssysj(xh,jnjs,myModel,userName,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return xssysj(mapping, form,request,response);		
	}
	
	public ActionForward fdyhf(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //辅导员回复
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_jskd_xssyxxb";
		String realTable     = "sxjy_jskd_xssyxxb";
		//String title     = "思想教育-学生生涯-辅导员查看回复";
		String title     = "思想教育-学生生涯-学生生涯查询";
		ArrayList<String[]> rs = null;
		XssyForm myForm              =    (XssyForm) form;
		XssyService service          =    new XssyService();
		XssyModel myModel            =    new XssyModel();
		
		String nj = myForm.getNj();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		
		xy = (xy==null) ? "" : xy;
		zy = (zy==null) ? "" : zy;
		bj = (bj==null) ? "" : bj;
		nj = (nj==null) ? "" : nj;
		
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		String bjKey = xy+"!!"+zy+"!!"+nj;
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				//rs = service.getXssyList(myModel);
			rs = service.getFilterList(myModel);
		}
		
		List topTr = service.getXsxyTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		
		
		request.setAttribute("filterList", service.getFilterCondi());
		
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("xssycx");		
}
	private void commonRequestSet(HttpServletRequest request, String tableName, String realTable,
			ArrayList<String[]> rs, List topTr,String title) {
    //Request存值的通用方法
		String writeAble    = request.getParameter("writeAble");
		if(writeAble==null){
			   writeAble    = Base.getWriteAble(request);
		}
		
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
	
	public ActionForward xssysjReqort(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    学生身涯设计报表
		String page                = DealString.toGBK(request.getParameter("page"));
		String pk                  = DealString.toGBK(request.getParameter("pk"));
		XssyService service        =    new XssyService();
		HashMap<String, String> rs = service.getXssysjAll(pk);
		request.setAttribute("rs", rs);
		return mapping.findForward("xssyReport"+page);		
	}

	//中国矿大党员信息
	public ActionForward dyxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //辅导员回复
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_zgkd_dyxx";
		String realTable     = "zgkd_dyxxb";
		//String title     = "思想教育-学生生涯-辅导员查看回复";
		String title     = "思想教育-信息维护-党员信息";
		ArrayList<String[]> rs = null;
		XssyForm myForm              =    (XssyForm) form;
		XssyService service          =    new XssyService();
		XssyModel myModel            =    new XssyModel();
		
		String nj = myForm.getNj();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		
		xy = (xy==null) ? "" : xy;
		zy = (zy==null) ? "" : zy;
		bj = (bj==null) ? "" : bj;
		nj = (nj==null) ? "" : nj;
		
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		String bjKey = xy+"!!"+zy+"!!"+nj;
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			String isBy = DealString.toGBK(request.getParameter("by"));
				// rs = service.getXssyList(myModel);
			if ("noBy".equals(isBy)) {
				rs = service.getDyxxList(myModel,myForm);
			}
			if ("isBy".equals(isBy)) {
				rs = service.getByDyxxList(myModel,myForm);
			}
		}
		
		List topTr = service.getDyxxTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		
		
		request.setAttribute("xzztList", service.getXzztList());
		
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("dyxx");		
}
	
	public ActionForward dyxxOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		// 党员信息单个查看
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		String xh                      =    DealString.toGBK(request.getParameter("xh"));
		String db                      =    DealString.toGBK(request.getParameter("db"));
		DAO dao=new DAO();
		if(pk!=null&&!"".equals(pk)){
			request.setAttribute("add", "add");
		}
		if(db!=null&&!"".equals(db)){
			request.setAttribute("db", "db");
		}
		XssyService service          =    new XssyService();
		HashMap<String, String> rs     =    service.getDyxxOne(pk,xh);
		String rdsqsj = dao.getOneRs(
				"select t.djsqsj from zgkd_rdsqb t where t.xh = ?",
				new String[] { xh }, "djsqsj");
		String qdwjjfzsj = dao.getOneRs(
				"select t.rdjjfzsj from zgkd_rdsqb t where t.xh = ?",
				new String[] { xh }, "rdjjfzsj");
		String fzdxsj = dao.getOneRs(
				"select t.fzdxsj from zgkd_rdsqb t where t.xh = ?",
				new String[] { xh }, "fzdxsj");
		rs.put("rdsqsj", rdsqsj);
		rs.put("qdwjjfzsj", qdwjjfzsj);
		rs.put("fzdxsj", fzdxsj);
		request.setAttribute("rs", rs);
		request.setAttribute("xzztList", service.getXzztList());

		return mapping.findForward("dyxxOne");	
	}
	
	public ActionForward saveDyxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // 军训新生体检维护单个查看保存
		XssyForm myForm              =    (XssyForm)  form;
		XssyService service          =    new XssyService();
		XssyModel myModel            =    new XssyModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataDyxx(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("dyxxOne");	
	}
	
	public ActionForward delRtjjfzOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 入团积极分子单个删除
		XssyService service          =    new XssyService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteDyxxOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return dyxx(mapping, form,request, response);		
	}
	
	public ActionForward ybdyzz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
			XssyService service          =    new XssyService();
			
			List topTr = service.getYbdyzzTorList();
			ArrayList<String[]> rs = null;
			rs = service.getYbdyzzList();
			if(rs!=null){
				request.setAttribute("rsNum", rs.size());
			}
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			return mapping.findForward("ybdyzz");	
	}
	
	public ActionForward ybdyzzOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
//		党员信息单个查看
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		String xh                      =    DealString.toGBK(request.getParameter("xh"));
		XssyService service          =    new XssyService();
		HashMap<String, String> rs     =    service.getDyxxOne(pk,xh);
		request.setAttribute("rs", rs);
		request.setAttribute("xzztList", service.getXzztList());
		return mapping.findForward("ybdyzzOne");	
	}
	
	public ActionForward saveYbdyzzOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		XssyForm myForm              =    (XssyForm)  form;
		XssyService service          =    new XssyService();
		XssyModel myModel            =    new XssyModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataYbdyzz(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("ybdyzzOne");
	}
	
//	中国矿大入党申请
	public ActionForward rdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String realTable = "zgkd_rdsqb";
		String tableName = "view_zgkd_rdsq";
		String title = "党团建设-数据维护-入党申请";

		ArrayList<String[]> rs = null;
		XssyForm myForm = (XssyForm) form;
		XssyService service = new XssyService();
		XssyModel myModel = new XssyModel();

		// 取得查询条件
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		String pk = "xh";
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			topTr = service.getRdsqTopTr();
			// 取得查询结果
			rs = service.getRdsqList(myModel, myForm);
			// 页面返回保证姓名不乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表		
		request.setAttribute("ndList", Base.getXnndList());// 获得5年内年度列表
		commonRequestSet(request, tableName, realTable, rs, topTr, title);
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", "partyApply");// 发送数据查询类型

		return mapping.findForward("rdsq");
	}
	
	public ActionForward delRdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String realTable = "zgkd_rdsqb";
		String tableName = "view_zgkd_rdsq";
		String title = "党团建设-数据维护-入党申请";

		ArrayList<String[]> rs = null;
		XssyForm myForm = (XssyForm) form;
		XssyService service = new XssyService();
		XssyModel myModel = new XssyModel();

		// 取得查询条件
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		String pk = "xh";

		// 获得主键
		String xh = DealString.toGBK(request.getParameter("pk"));

		// 删除发展对象
		String[] keys = xh.split("!!SplitSignOne!!");
		// 删除相关数据
		String del = service.delRdsq(keys);

		// boolean del = service.delSwcl(xh, request);
		// 判断删除是否成功
		if (del == null || "".equals(del)) {
			request.setAttribute("del", "ok");
		} else {
			request.setAttribute("del", "no");
			request.setAttribute("delRdsq", del);
		}
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			topTr = service.getRdsqTopTr();
			// 取得查询结果
			rs = service.getRdsqList(myModel, myForm);
			// 页面返回保证姓名不乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表		
		request.setAttribute("ndList", Base.getXnndList());// 获得5年内年度列表
		commonRequestSet(request, tableName, realTable, rs, topTr, title);
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", "partyApply");// 发送数据查询类型
		
		return mapping.findForward("rdsq");
	}
}