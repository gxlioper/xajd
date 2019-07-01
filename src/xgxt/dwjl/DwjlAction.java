package xgxt.dwjl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 对外交流模块Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-01-14</p>
 */
public class DwjlAction extends BasicAction {
	
	/**
	 * 公用方法:在REQUEST中存放页面所要加载的属性
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		String writeAble = "yes";
		xy = xy==null ? "": (xydm==null ? "" : xydm); 
		zy = zy==null ? "": (zydm==null ? "" : zydm); 
		njLocal = nj==null ? "": nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();

		writeAble = userType != null && "stu".equalsIgnoreCase(userType) ? "no" : writeAble;
		request.setAttribute("writeAble", writeAble);//判断用户读写权
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		request.setAttribute("userType", userType);//用户类型		
	}
	
	/**
	 * 宁波理工对外交流申请表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward printDwjlsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DwjlForm model = new DwjlForm();
		DwjlService service = new DwjlService(); 
		String xh = DealString.toGBK(request.getParameter("xh"));
		String bjdypm = DealString.toGBK(request.getParameter("bjdypm"));
		String njzypm = DealString.toGBK(request.getParameter("njzypm"));
		String tcah = DealString.toGBK(request.getParameter("tcah"));
		String shgzqk = DealString.toGBK(request.getParameter("shgzqk"));
		String xmdm = DealString.toGBK(request.getParameter("xmdm"));
		String sqly = DealString.toGBK(request.getParameter("sqly"));
		
		model.setXh(xh);
		model.setBjdypm(bjdypm);
		model.setNjzypm(njzypm);
		model.setTcah(tcah);
		model.setShgzqk(shgzqk);
		model.setXmdm(xmdm);
		model.setSqly(sqly);
		
		request.setAttribute("rs", service.getDwjlsqInfo(model));
		return mapping.findForward("success");
	}	
	
	/**
	 * 宁波理工对外交流奖学金申请表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward printDwjljxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DwjlForm model = new DwjlForm();
		DwjlService service = new DwjlService();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xmdm = DealString.toGBK(request.getParameter("xmdm"));
		String sqje = DealString.toGBK(request.getParameter("sqje"));
		String jlbx = DealString.toGBK(request.getParameter("jlbx"));
		
		model.setXh(xh);
		model.setXmdm(xmdm);
		model.setSqje(sqje);
		model.setJlbx(jlbx);
		
		request.setAttribute("rs", service.getDwjljxjsqInfo(model));
		return mapping.findForward("dwjljxjsqb");
	}
	
	/**
	 * 出国留学申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward cglxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		DwjlService service = new DwjlService();
		String xh = request.getParameter("xh");
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
//		String writeAble = Base.getWriteAble(request);
		
		xh = userOnLine != null && "student".equalsIgnoreCase(userOnLine) ? userName : xh;
		
		request.setAttribute("rs",service.getStuInfo(xh));
		request.setAttribute("writeAble", "yes");//writeAble
		return mapping.findForward("cglxsq");
	}
	
	/**
	 * 出国留学申请信息保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveCglxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		DwjlService service = new DwjlService();
		DwjlForm model = (DwjlForm)form;
		
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
//		String writeAble = Base.getWriteAble(request);
		
		String xh = userOnLine != null && "student".equalsIgnoreCase(userOnLine) ? userName : model.getXh();
		model.setXh(xh);
		
		request.setAttribute("result", service.saveCglxsq(model, request));
		request.setAttribute("rs",service.getStuCglxsq(model));
		request.setAttribute("writeAble", "yes");//writeAble
		return mapping.findForward("cglxsq");
	}
	
	/**
	 * 出国留学申请修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward cglxck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DwjlService service = new DwjlService();
		DwjlForm model = (DwjlForm)form;
		String xh = request.getParameter("xh");
		String sqrq = request.getParameter("sqrq");
		String doType = request.getParameter("doType");
//		String writeAble = Base.getWriteAble(request);	
		
		xh = xh != null && !"".equalsIgnoreCase(xh) ? DealString.toGBK(xh).trim() : xh;
		sqrq = sqrq != null && !"".equalsIgnoreCase(sqrq) ? DealString.toGBK(sqrq).trim() : sqrq;
		
		model.setXh(xh);
		model.setSqrq(sqrq);
		
		request.setAttribute("rs",service.getStuCglxsq(model));
		request.setAttribute("writeAble", "yes");//writeAble
		request.setAttribute("doType", doType);
		return mapping.findForward("cglxsq");
	}
	
	/**
	 * 出国留学申请记录删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward cglxsqsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//DwjlForm model = (DwjlForm)form;
		DwjlService service = new DwjlService();
		String pk = DealString.toGBK(request.getParameter("delPk"));
		
		request.setAttribute("result", service.cglxsqDel(pk,request));
		return cglxsqcx(mapping, form, request, response);		
	}
	
	/**
	 * 打印出国留学申请表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward printCglxsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HashMap<String, String> map = new HashMap<String, String>();
		DwjlService service = new DwjlService();
		
		String xh = DealString.toGBK(request.getParameter("xh"));
		map.putAll(service.getStuInfo(xh));//学生基本信息
		
		//页面字段
		String[] column = {"xl","jdxx","cet","tem","toeft","jzxm","jzgzdw","jtdz","gj","xx","qqh","dzyx","lxdh","sfzzlxxx"};
		
		for(int i=0; i<column.length; i++){
			map.put(column[i], DealString.toGBK(request.getParameter(column[i])));
		}
		map.put("toeft", map.get("toeft") == null || "".equalsIgnoreCase(map.get("toeft")) ? "无" : map.get("toeft"));
		request.setAttribute("rs", map);
		request.setAttribute("year", GetTime.getNowYear());
		return mapping.findForward("printCglxsqb");
	}
	
	/**
	 * 出国留学申请查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward cglxsqcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		DwjlService service = new DwjlService();
		DwjlForm model = (DwjlForm)form;
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String nj = model.getNj();
		String go = request.getParameter("go");
		
		String userType = session.getAttribute("userType").toString();
		if(userType != null && "xy".equalsIgnoreCase(userType)){//学院用户
			xydm = session.getAttribute("userDep").toString();
			model.setXydm(xydm);
		}else if(userType != null && "stu".equalsIgnoreCase(userType)){//学生用户
			model.setXh(session.getAttribute("userName").toString());
		}		
		if("stu".equalsIgnoreCase(userType)){//学生用户查询
			List rs = service.selectCglxsq(model);
			request.setAttribute("topTr", service.getCglxsqToptr());
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		if(go != null && !"".equalsIgnoreCase(go)){//查询
			List rs = service.selectCglxsq(model);
			request.setAttribute("topTr", service.getCglxsqToptr());
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("realTable", "xscgsqb");
		request.setAttribute("tableName", "view_cgsqxx");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("cglxcx");
	}
	
	
	/**
	 * 出国留学审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward cglxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		DwjlService service = new DwjlService();
		DwjlForm model = (DwjlForm)form;
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String nj = model.getNj();
		String go = request.getParameter("go");
		
		
		String userType = session.getAttribute("userType").toString();
		if(userType != null && "xy".equalsIgnoreCase(userType)){//学院用户
			xydm = session.getAttribute("userDep").toString();
			model.setXydm(xydm);
		}else if(userType != null && "stu".equalsIgnoreCase(userType)){//学生用户
			model.setXh(session.getAttribute("userName").toString());
		}
		model.setUserType(userType);
		
		if(go != null && !"".equalsIgnoreCase(go)){//查询
			List rs = service.selectCglxsqsh(model);
			request.setAttribute("topTr", service.getCglxsqToptr());
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("cglxsh");
	}
	
	/**
	 * 出国留学审核查看详情
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward cglxshck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		DwjlService service = new DwjlService();
		DwjlForm model = (DwjlForm)form;
		String xh = request.getParameter("xh");
		String sqrq = request.getParameter("sqrq");
		String doType = request.getParameter("doType");
//		String writeAble = Base.getWriteAble(request);	
		String userType = session.getAttribute("userType").toString();
		
		xh = xh != null && !"".equalsIgnoreCase(xh) ? DealString.toGBK(xh).trim() : xh;
		sqrq = sqrq != null && !"".equalsIgnoreCase(sqrq) ? DealString.toGBK(sqrq).trim() : sqrq;
		
		model.setXh(xh);
		model.setSqrq(sqrq);
		
		HashMap<String, String> map = service.getStuCglxsq(model);
		map.put("yesNo", userType != null && "xy".equalsIgnoreCase(userType) ? map.get("xysh") : map.get("xxsh"));
		
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("rs",map);
		request.setAttribute("writeAble", "yes");//writeAble
		request.setAttribute("doType", doType);
		return mapping.findForward("cglxshck");
	}
	
	/**
	 * 保存出国留学审核信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveCglxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		DwjlService service = new DwjlService();
		DwjlForm model = (DwjlForm)form;
		String userType = session.getAttribute("userType").toString();
		model.setUserType(userType);
		
		request.setAttribute("result", service.saveCglxsh(model,request));
		return cglxshck(mapping, form, request, response);
	}
	
	/**
	 * 出国留学申请批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward cglxsqplsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DwjlForm model = (DwjlForm)form;
		DwjlService service = new DwjlService();
		String pk = DealString.toGBK(request.getParameter("delPk"));
		String yesNo = DealString.toGBK(request.getParameter("yesNo"));
		
		model.setPk(pk);
		model.setYesNo(yesNo);
		
		request.setAttribute("result", service.cglxsqplsh(model,request));
		return cglxsh(mapping, form, request, response);		
	}
	
	/**
	 * 对外交流批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward dwjlsqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		DwjlService service = new DwjlService();
		DwjlForm model = new DwjlForm();
		String type = request.getParameter("type");
		String userType = session.getAttribute("userType").toString();
		String pkStr = request.getParameter("pkString");
		
		model.setUserType(userType);
		model.setYesNo(type);
		model.setPk(pkStr);
		
		request.setAttribute("result", service.dwjlsqsh(model,request));
		request.setAttribute("tips", "对外交流 - 审核 - 对外交流审核");
		request.setAttribute("tableName", request.getParameter("tableName"));
		request.setAttribute("realTable", request.getParameter("realTable"));
		request.setAttribute("pk", request.getParameter("pk"));
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		request.setAttribute("jlxmList", service.getDwjlxmList());
		return mapping.findForward("dwjlsh");
	}
	
	/**
	 * 对外交流批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward dwjljxjsqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		DwjlService service = new DwjlService();
		DwjlForm model = new DwjlForm();
		String type = request.getParameter("type");
		String userType = session.getAttribute("userType").toString();
		String pkStr = request.getParameter("pkString");
		
		model.setUserType(userType);
		model.setYesNo(type);
		model.setPk(pkStr);
		
		request.setAttribute("result", service.dwjljxjsqsh(model,request));
		request.setAttribute("tips", "对外交流 - 审核 - 对外交流奖学金审核");
		request.setAttribute("tableName", request.getParameter("tableName"));
		request.setAttribute("realTable", request.getParameter("realTable"));
		request.setAttribute("pk", request.getParameter("pk"));
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		request.setAttribute("jlxmList", service.getDwjlxmList());
		return mapping.findForward("dwjljxjsh");
	}
	
	/**
	 * 对外交流奖学金维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward dwjljxjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		DwjlService service = new DwjlService();
		String go = request.getParameter("go");
		String type = request.getParameter("type");
		DwjlForm model = (DwjlForm)form;
		
		if("student".equalsIgnoreCase(session.getAttribute("userOnLine").toString())){
			model.setXh(session.getAttribute("userName").toString());
			model.setQuerylike_xh(model.getXh());
		}
		if("del".equalsIgnoreCase(type)){
			this.deleteOperation(request, "dwjljxjsqb");
			go = "go";
		}
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = {"pkValue", "xn", "nd", "xqmc", "xh", "xm", "bjmc", 
					                 "dwjlxmmc", "sqje", "xxsh", "zzpzje"};
			this.selectPageDataByPagination(request, model, "dwjljxjsqb", "view_dwjljxjsq", outputColumn);
		}else{
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			model.setNd(Base.currNd);
			model.setQueryequals_xn(Base.currXn);
			model.setQueryequals_nd(Base.currNd);
			model.setQueryequals_xq(Base.currXq);
		}
		
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("jlxmdmList", service.queryDwjlxmdmList(model.getXn(),
				                                                       model.getNd(),
				                                                       model.getXq()));
		if("del".equalsIgnoreCase(type)){
			request.setAttribute("writeAble", "yes");
		}
		return mapping.findForward("dwjljxjwh");
	}
	
	/**
	 * 对外交流奖学金信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expDdwjljxjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String[] outputColumn = {"xn","nd","xq","xqmc","xh","xm","xb","nj","xydm",
				                 "xymc","zydm","zymc","bjdm","bjmc","jlxmdm",
				                 "bxmsxje","sqje","hjqk","yhtc","yqsfyg","sqsy",
				                 "xysh","xypzje","xxsh","zzpzje","sqsj","jxjffsj",
				                 "bz","dwjlxmmc","dwjlfsmc","dwjllbmc","jlbx"};
		this.expPageData(request, response,"dwjljxjsqb", "view_dwjljxjsq", outputColumn);
		return mapping.findForward("");
	}
	
	/**
	 * 对外交流奖学金信息修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward dwjljxjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String type = request.getParameter("type");
		DwjlService service = new DwjlService();
		DwjlForm model = (DwjlForm)form;
		String pkValue = request.getParameter("pkValue");		
		this.selectPageDataByOne(request, "dwjljxjsqb", "view_dwjljxjsq", pkValue);
		
		HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("rs");		
		model.setXh(map.get("xh"));
		model.setXmdm(map.get("xn")+map.get("nd")+map.get("xq")+map.get("jlxmdm"));
		
		map.put("xmdm",model.getXmdm());
		map.putAll(service.getDwjljxjsqInfo(model.getXh(),model.getXmdm()));
		map.putAll(service.queryDwjlxx(model.getXmdm()));
		request.setAttribute("rs", map);
		request.getAttribute("rs");
		request.setAttribute("dwjlList", service.queryXsdwjlxm(model.getXh()));
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("writeAble", "view".equalsIgnoreCase(type) ? "no" :"yes");
		
		return mapping.findForward("dwjljxjModi");
	}
	
}
