package xgxt.pjpy.whlghxxy;

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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.jgsdx.PjpyJgsdxService;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyHxxyAction extends CommonAction {

	private String xydm = "";
	
	private String zydm = "";
	
	private String nj = "";
	
	private PjpyHxxyService service = PjpyHxxyService.getInstance();
	

	/**
	 * 综合素质测评默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成方法存根
		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			hxxyForm.setXydm(xydm);
		} else {
			xydm = hxxyForm.getXydm();
		}
		nj = hxxyForm.getNj();
		zydm = hxxyForm.getZydm();
		String act = request.getParameter("act");//数据操作标志
		List<String[]> rsList = new ArrayList<String[]>();// 数据查询结果
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String,String>>();//数据查询表头
		if ("qry".equalsIgnoreCase(act)) {//数据查询操作
			PjpyHxxyModel model = new PjpyHxxyModel();
			BeanUtils.copyProperties(model, hxxyForm);
			rsList = service.queryZhszcpList(model, hxxyForm);
			int count = service.queryZhszcpListNum(model);
			titList = service.queryZhszcpTitle();
			hxxyForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		}
		if ("del".equalsIgnoreCase(act)) {//数据删除操作
			String flag = service.deleteZhszf(hxxyForm.getCbv());
			if (StringUtils.isNull(flag)) {
				request.setAttribute("deleted", "yes");//返回页面提示
			} else {
				request.setAttribute("deleted", "no");//返回页面提示
				request.setAttribute("failinfo", "操作完成，其中第" + flag + "条数据操作失败！");//返回页面提示错误信息
			}
		}
		String xn = Base.getJxjsqxn();//奖学金申请学年
		String nd = Base.getJxjsqnd();//奖学金申请年度
		@SuppressWarnings("unused")
		String xq = Base.getJxjsqxq();//奖学金申请学期
		if ("auto".equalsIgnoreCase(act)) {//自动计算分
			boolean flag = service.autoCount(xn, nd, xydm);
			if (flag) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
			}
		}
		appendProperties(request, xydm, zydm, nj);// 在页面加载学年，年度，年级，学院,专业,班级列表
		//appendJxjList(request);// 在页面加载奖学金列表
		//appendRychList(request);// 在页面加载荣誉称号列表
		appendTableXx(request, "view_hxxy_zhszcp", "zhszcp");//在页面加载视图名，表名
		appendQryResult(request, titList, rsList);//在页面加载查询结果集，表头，记录数
		hxxyForm.setXm(DealString.toGBK(hxxyForm.getXm()));
		return mapping.findForward("zhszcpdefault");
	}
	
	/**
	 * 奖学金上报（辅导员）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		PjpyHxxyService service = new PjpyHxxyService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		//学院用户
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			hxxyForm.setXydm(xydm);
		}
		
		PjpyHxxyModel model = new PjpyHxxyModel();
		BeanUtils.copyProperties(model, hxxyForm);
		HashMap<String, String> resMap = new HashMap<String, String>();
		
		model.setXn(Base.getJxjsqxn());
		model.setXq(Base.getJxjsqxq());
		model.setNd(Base.getJxjsqnd());
		
		String xh = "";
		if (StringUtils.isEqual(userType, "student") || StringUtils.isEqual(userType, "stu")) {
			xh = session.getAttribute("userName").toString();
			request.setAttribute("showstu", "yes");
		} else {
			xh = request.getParameter("xh");
		}
		model.setXh(xh);
		if (!StringUtils.isNull(xh)) {
			resMap = service.getJxjpdxx(model);//获取学生评定信息
			if (resMap != null) {//该生信息存在
				resMap.put("stuExists", "yes");
			} else {//该生信息不存在
				resMap.put("stuExists", "no");
			}
			boolean tj = service.chksqtj(xh);
			if (!tj) {
				request.setAttribute("tj", "disabled");
			}
			
		}
		resMap.put("xn", Base.getJxjsqxn());
		resMap.put("xq", Base.getJxjsqxq());
		resMap.put("nd", Base.getJxjsqnd());
		appendJxjList(request);//奖学金列表
		request.setAttribute("rs", resMap);
		hxxyForm.setWysp(DealString.toGBK(hxxyForm.getWysp()));
		hxxyForm.setDrzw(DealString.toGBK(hxxyForm.getDrzw()));
		hxxyForm.setTzjkbzdj(DealString.toGBK(hxxyForm.getTzjkbzdj()));
		hxxyForm.setSqly(DealString.toGBK(hxxyForm.getSqly()));
		return mapping.findForward("jxjsqDefault");
	}
	
	/**
	 * 奖学金申请保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		PjpyHxxyService service = new PjpyHxxyService();
		HttpSession session = request.getSession();
		String bmdm = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		//学院用户
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			hxxyForm.setXydm(xydm);
		}
		PjpyHxxyModel model = new PjpyHxxyModel();
		BeanUtils.copyProperties(model, hxxyForm);
		model.setXn(request.getParameter("xn"));
		model.setNd(request.getParameter("nd"));
		model.setXq(request.getParameter("xq"));
		HashMap<String, String> resMap = new HashMap<String, String>();

		boolean bFlag = service.jxjsqSave(model, request);// 申请保存
		if (bFlag) {// 成功
			request.setAttribute("inserted", "yes");
		} else {// 失败
			request.setAttribute("inserted", "no");
		}
		resMap = service.getJxjpdxx(model);// 获取学生评定信息
		resMap.put("stuExists", "yes");
		resMap.put("xn", Base.getJxjsqxn());
		resMap.put("xq", Base.getJxjsqxq());
		resMap.put("nd", Base.getJxjsqnd());
		appendJxjList(request);//奖学金列表
		request.setAttribute("rs", resMap);
		hxxyForm.setWysp(DealString.toGBK(hxxyForm.getWysp()));
		hxxyForm.setDrzw(DealString.toGBK(hxxyForm.getDrzw()));
		hxxyForm.setTzjkbzdj(DealString.toGBK(hxxyForm.getTzjkbzdj()));
		hxxyForm.setSqly(DealString.toGBK(hxxyForm.getSqly()));
		return mapping.findForward("jxjsqDefault");
	}
	
	/**
	 * 奖学金申请结果查询首页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqqryDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		PjpyHxxyService service = new PjpyHxxyService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {
			xydm = bmdm;
			hxxyForm.setXydm(xydm);
		}else {
			xydm = request.getParameter("xydm");
		}
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		if (StringUtils.isEqual(userType, "student") || StringUtils.isEqual(userType, "stu")) {
			String xh = session.getAttribute("userName").toString();
			List<HashMap<String, String>> topList = service.getQryTitle("stusqxx");//查询表头
			List<String[]>  resList = service.stuJxjSqxx(xh);//学生奖学金申请信息
			request.setAttribute("rs", resList);
			request.setAttribute("num", resList != null ? resList.size() : 0);//申请次数
			request.setAttribute("topTr", topList);
			return mapping.findForward("stujxjqry");
		}
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		List<String[]>  resList = new ArrayList<String[]>();
		String act = request.getParameter("act");
		if ("qry".equalsIgnoreCase(act)) {
			PjpyHxxyModel model = new PjpyHxxyModel();
			BeanUtils.copyProperties(model, hxxyForm);
			topList = service.jxjsqTitlequery(userType);
			resList = service.jxjsqjgQuery(model, hxxyForm);
			int count = service.jxjsqjgQueryNum(model, hxxyForm);
			hxxyForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		}
		if ("del".equalsIgnoreCase(act)) {
			String flag = service.jxjsqDelete(hxxyForm.getCbv());
			if (StringUtils.isNull(flag)) {
				request.setAttribute("deleted", "yes");//返回页面提示
			} else {
				request.setAttribute("deleted", "no");//返回页面提示
				request.setAttribute("failinfo", "操作完成，其中第" + flag + "条数据操作失败！");//返回页面提示错误信息
			}
		}
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		appendQryResult(request, topList, resList);
		request.setAttribute("realTable", "xsjxjb");
		request.setAttribute("tableName", "view_xsjxjb");
		hxxyForm.setZt(DealString.toGBK(hxxyForm.getZt()));
		hxxyForm.setXm(DealString.toGBK(hxxyForm.getXm()));
		return mapping.findForward("jxjsqqrydefault");
	}
	
	/**
	 * 奖学金申请学生查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stujxjQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHxxyService service = new PjpyHxxyService();
		HttpSession session = request.getSession();
		String xh = session.getAttribute("userName").toString();
		List<HashMap<String, String>> topList = service.getQryTitle("stusqxx");//查询表头
		List<String[]>  resList = service.stuJxjSqxx(xh);//学生奖学金申请信息
		request.setAttribute("rs", resList);
		request.setAttribute("num", resList != null ? resList.size() : 0);//申请次数
		request.setAttribute("topTr", topList);
		return mapping.findForward("stujxjqrys");
	}
	
	/**
	 * 综合素质分修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");//数据操作标志
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("view".equalsIgnoreCase(act)) {
			request.setAttribute("flag", "view");
		}
		if ("save".equalsIgnoreCase(act)) {
			PjpyHxxyModel model = new PjpyHxxyModel();
			BeanUtils.copyProperties(model, hxxyForm);
			boolean flag = service.updateZhszcp(pkValue, model, request);
			if (flag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		if (!StringUtils.isNull(pkValue)) {
			rs = service.viewZhszcp(pkValue);
			hxxyForm.setDcj(rs.get("dcj"));
			hxxyForm.setMcj(rs.get("mcj"));
			hxxyForm.setXn(rs.get("xn"));
			hxxyForm.setZcj(rs.get("zcj"));
			hxxyForm.setTcj(rs.get("tcj"));
			hxxyForm.setXf(rs.get("xf"));
			hxxyForm.setJlf(rs.get("jlf"));
			hxxyForm.setCff(rs.get("cff"));
		}
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpmodi");
	}
	
	/**
	 * 奖学金审核默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjshWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成方法存根
		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		hxxyForm.setXn(Base.getJxjsqxn());
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			hxxyForm.setXydm(xydm);
		} else {
			xydm = hxxyForm.getXydm();
			
		}
		nj = hxxyForm.getNj();
		zydm = hxxyForm.getZydm();
		String act = request.getParameter("act");//数据操作标志
		List<String[]> rsList = new ArrayList<String[]>();// 数据查询结果
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String,String>>();//数据查询表头
		if ("qry".equalsIgnoreCase(act)) {//数据查询操作
			PjpyHxxyModel model = new PjpyHxxyModel();
			BeanUtils.copyProperties(model, hxxyForm);
			rsList = service.queryJxjshResult(model, userType);
			titList = service.queryJxjshTitle(userType);
		}
		if ("sh".equalsIgnoreCase(act)) {
			String flag = service.jxjplsh(hxxyForm.getCbv(), request.getParameter("param1"), userType, request);
			if (StringUtils.isNull(flag)) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("failinfo", "操作完成，其中第" + flag + "条数据操作失败！");
			}
		}
		
		String xn = Base.getJxjsqxn();//奖学金申请学年
		@SuppressWarnings("unused")
		String nd = Base.getJxjsqnd();//奖学金申请年度
		@SuppressWarnings("unused")
		String xq = Base.getJxjsqxq();//奖学金申请学期
		appendProperties(request, xydm, zydm, nj);// 在页面加载学年，年度，年级，学院,专业,班级列表
		appendJxjList(request);// 在页面加载奖学金列表
		appendTableXx(request, "view_xsjxjb", "xsjxjb");//在页面加载视图名，表名
		appendQryResult(request, titList, rsList);//在页面加载查询结果集，表头，记录数
		hxxyForm.setXm(DealString.toGBK(hxxyForm.getXm()));
		hxxyForm.setXn(xn);
		hxxyForm.setZt(DealString.toGBK(hxxyForm.getZt()));
		return mapping.findForward("jxjshpage");
	}
	
	public ActionForward jxjshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = service.viewJxjshOne(pkValue, userType);
		hxxyForm.setYesNo(rs.get("sh"));
		hxxyForm.setYj(rs.get("yj"));
		hxxyForm.setFdyyj(rs.get("fdyyj"));
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			boolean flag = service.jxjshOne(pkValue, userType, DealString.toGBK(request.getParameter("yesNo")), DealString.toGBK(request.getParameter("yj")), DealString.toGBK(request.getParameter("fdyyj")));;
			if (flag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		appendChkList(request);
		return mapping.findForward("jxjshone");
	}
	
	/**
	 * 荣誉称号申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		 TODO 自动生成方法存根
//		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
//		DAO dao=new DAO();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		rs.put("xh", " ");
		
		appendRychList(request);// 在页面加载荣誉称号列表
		
		request.setAttribute("userType", userType);
		request.setAttribute("userDep", userDep);
		request.setAttribute("rs", rs);
		return mapping.findForward("whlghxxy_rychsq");
	}
	
	/**
	 * 荣誉称号申请保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychSqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// TODO 自动生成方法存根
		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		PjpyHxxyModel myModel = new PjpyHxxyModel();

		HttpSession session = request.getSession();
		PjpyHxxyService service = new PjpyHxxyService();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String oldpk = request.getParameter("oldpk");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String rychdm = request.getParameter("rychdm");
		//String wydj = DealString.toGBK(request.getParameter("wydj"));
		String type = request.getParameter("rychEdit");
		if ("student".equalsIgnoreCase(userType) || "stu".equalsIgnoreCase(userType)) {
			type = "";
		}
		//type='rychOne' modi
		HashMap<String, String> rs = new HashMap<String, String>();
		BeanUtils.copyProperties(myModel, hxxyForm);
		boolean inserted = service.updataRych(myModel, xh + xn + rychdm,
				request, type, oldpk);
		if (inserted) {
			if ("yes".equals(type)) {
				request.setAttribute("inserted", "ok");
			} else {
				request.setAttribute("inserted", "okk");
				
				
			}
			
		}else{
			request.setAttribute("inserted", "no");
		}
		rs = service.getRych(hxxyForm.getXh() + xn + rychdm, request);
		rs.put("xh", hxxyForm.getXh());
		rs.put("nd", hxxyForm.getNd());
		rs.put("xn", hxxyForm.getXn());
		rs.put("wydj", DealString.toGBK(hxxyForm.getWydj()));
		rs.put("zysj", DealString.toGBK(hxxyForm.getZysj()));
		rs.put("drshgzqk", DealString.toGBK(hxxyForm.getDrshgzqk()));
		rs.put("xh", hxxyForm.getXh());

		appendRychList(request);// 在页面加载荣誉称号列表

		request.setAttribute("userType", userType);
		request.setAttribute("userDep", userDep);
		rs.put("pk", xn + hxxyForm.getXh() + rychdm);
		request.setAttribute("rs", rs);
//		hxxyForm.setWysp(DealString.toGBK(hxxyForm.getWysp()));
//		hxxyForm.setZysj(DealString.toGBK(hxxyForm.getZysj()));
//		hxxyForm.setDrshgzqk(DealString.toGBK(hxxyForm.getDrshgzqk()));
		return mapping.findForward("whlghxxy_rychsq");
	}
	
	/**
	 * @describe 根据条件查询目前所有的荣誉称号申请
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward rychall(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_xsrychb";
		String realTable = "xsrychb";
		String title = "评奖评优 - 荣誉称号申请 - 申请结果查询";

		ArrayList<String[]> rs = null;
		PjpyHxxyActionForm myForm = (PjpyHxxyActionForm) form;
		PjpyHxxyModel myModel = new PjpyHxxyModel();
		PjpyHxxyService service = new PjpyHxxyService();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		// 获得查询条件
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// 查询是否点击查询按钮
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 获得表头
			topTr = service.getRychTopTr();
			// 获得查询结果列表
			rs = service.getRychList(myModel, myForm);
			// 防止返回页面姓名乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		
		PjpyJgsdxService jservice = new PjpyJgsdxService();
		List<HashMap<String, String>> rychList = jservice.getRychList();
		request.setAttribute("rychList", rychList);
		rs1.put("rychdm", " ");
		rs1.put("xxsh", " ");
		rs1.put("xysh", " ");
		request.setAttribute("rs1", rs1);
		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);

		return mapping.findForward("whlghxxy_rychall");
	}
	
	/**
	 * @describe 荣誉称号修改
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward rychOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		PjpyHxxyActionForm myForm = (PjpyHxxyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
//		String userDep = session.getAttribute("userDep").toString();
		String xh = session.getAttribute("userName").toString();
		
		String xn = Base.getJxjsqxn();//奖学金申请学年
		String nd = Base.getJxjsqnd();//奖学金申请年度
//		String xq = Base.getJxjsqxq();//奖学金申请学期
		
		PjpyHxxyService service = new PjpyHxxyService();
		HashMap<String, String> rs = new HashMap<String, String>();

		String pk = DealString.toGBK(request.getParameter("pk"));
		String xy = "";
		String bjKey = "!!" + "!!";

		if ("student".equals(userType)) {
			rs = service.getRychStu(xh);
			CommonAction com = new CommonAction();
			com.appendRychList(request);// 在页面加载荣誉称号列表
			rs.put("nd", nd);
			rs.put("xn", xn);
		}
		
		else {
			if (pk != null && !"".equals(pk)) {
				// 获得发展对象信息
				rs = service.getRychOne(pk);
				// 判断是否鼠标双击

				request.setAttribute("doType", "modi");
				request.setAttribute("type", "update");
			}
		}

		request.setAttribute("rychEdit", "rychOne");
		request.setAttribute("xydm", xy);
		request.setAttribute("rs", rs);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());

		appendProperties(request, xydm, zydm, nj);// 在页面加载学年，年度，年级，学院,专业,班级列表
		appendJxjList(request);// 在页面加载奖学金列表
		appendRychList(request);// 在页面加载荣誉称号列表

		request.setAttribute("oldpk", rs.get("xh") + rs.get("xn") + rs.get("rychdm"));
		return mapping.findForward("whlghxxy_rychOne");
	}
	
	/**
	 * @describe 荣誉称号数据库中删除
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward rychDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyHxxyActionForm myForm = (PjpyHxxyActionForm) form;
		PjpyHxxyService service = new PjpyHxxyService();
		HttpSession session = request.getSession();
		String pk = DealString.toGBK(request.getParameter("pk"));

		// 删除发展对象
		// boolean del = service.delFzdx(pk, request);
		String[] keys = pk.split("!!SplitSignOne!!");
		HashMap<String, String> rs1 = new HashMap<String, String>();
		// 获得查询条件
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		rs1.put("rychdm", " ");
		rs1.put("xxsh", " ");
		rs1.put("xysh", " ");
		request.setAttribute("rs1", rs1);
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		String userType = session.getAttribute("userOnLine").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		@SuppressWarnings("unused")
		String delFzdx = service.rychDel(keys);
		request.setAttribute("rs1", rs1);
		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}



		request.setAttribute("writeAble", "yes");
		request.setAttribute("tableName", "view_xsrychb");
		request.setAttribute("realTable", "xsrychb");
		appendRychList(request);
		return mapping.findForward("whlghxxy_rychdel");
	}
	
	/**
	 * @describe 荣誉称号打印
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward rychPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
//		PjpyHxxyActionForm myForm = (PjpyHxxyActionForm) form;
		PjpyHxxyService service = new PjpyHxxyService();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		String pk = request.getParameter("pk");

		 // 获得发展对象信息
		rs = service.getRychOne(pk);
		// 判断是否鼠标双击

		request.setAttribute("rs", rs);

		return mapping.findForward("whlghxxy_rychPrint");
	}
	
	public ActionForward jxjmodi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");//数据操作标志
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = service.jxjsqView(pkValue);
		if ("view".equalsIgnoreCase(act)) {
			request.setAttribute("flag", "view");
		}
		if ("save".equalsIgnoreCase(act)) {
			PjpyHxxyModel model = new PjpyHxxyModel();
			BeanUtils.copyProperties(model, hxxyForm);
			boolean flag = service.jxjsqUpdate(pkValue, model, request);
			if (flag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			hxxyForm.setWysp(DealString.toGBK(hxxyForm.getWysp()));
			hxxyForm.setDrzw(DealString.toGBK(hxxyForm.getDrzw()));
			hxxyForm.setTzjkbzdj(DealString.toGBK(hxxyForm.getTzjkbzdj()));
			hxxyForm.setSqly(DealString.toGBK(hxxyForm.getSqly()));
		}
		if (rs != null) {
			hxxyForm.setJxjdm(rs.get("jxjdm"));
			hxxyForm.setWysp(rs.get("wysp"));
			hxxyForm.setDrzw(rs.get("drzw"));
			hxxyForm.setTzjkbzdj(rs.get("tzjkbzdj"));
			hxxyForm.setSqly(rs.get("sqly"));
		}
//		rs.put("xn", Base.getJxjsqxn());
//		rs.put("nd", Base.getJxjsqnd());
//		rs.put("xq", Base.getJxjsqxq());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		return mapping.findForward("jxjmodipage");
	}
	
	public ActionForward jxjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xh = request.getParameter("xh");
		String jxjdm = request.getParameter("jxjdm");
		String xn = Base.getJxjsqxn();
		String pkValue = xh+xn+jxjdm;
		HashMap<String, String> rs = service.jxjsqView(pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("jxjprint");
	}
	
	/**
	 * 荣誉称号审核默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychshWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成方法存根
		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		hxxyForm.setXn(Base.getJxjsqxn());
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			hxxyForm.setXydm(xydm);
		} else {
			xydm = hxxyForm.getXydm();
		}
		nj = hxxyForm.getNj();
		zydm = hxxyForm.getZydm();
		String act = request.getParameter("act");//数据操作标志
		List<String[]> rsList = new ArrayList<String[]>();// 数据查询结果
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String,String>>();//数据查询表头
		if ("qry".equalsIgnoreCase(act)) {//数据查询操作
			PjpyHxxyModel model = new PjpyHxxyModel();
			BeanUtils.copyProperties(model, hxxyForm);
			rsList = service.queryrychshResult(model, userType);
			titList = service.queryrychshTitle(userType);
		}
		if ("sh".equalsIgnoreCase(act)) {
			String flag = service.ruchplsh(hxxyForm.getCbv(), request.getParameter("param1"), userType, request);
			if (StringUtils.isNull(flag)) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("failinfo", "操作完成，其中第" + flag + "条数据操作失败！");
			}
		}
		
		String xn = Base.getJxjsqxn();//奖学金申请学年
//		String nd = Base.getJxjsqnd();//奖学金申请年度
//		String xq = Base.getJxjsqxq();//奖学金申请学期
		appendProperties(request, xydm, zydm, nj);// 在页面加载学年，年度，年级，学院,专业,班级列表
		appendRychList(request);// 在页面加载奖学金列表
		appendTableXx(request, "view_xsrychb", "Xsrychb");//在页面加载视图名，表名
		appendQryResult(request, titList, rsList);//在页面加载查询结果集，表头，记录数
		hxxyForm.setXm(DealString.toGBK(hxxyForm.getXm()));
		hxxyForm.setXn(xn);
		hxxyForm.setZt(DealString.toGBK(hxxyForm.getZt()));
		return mapping.findForward("rychshtz");
	}
	
	public ActionForward rychshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHxxyActionForm hxxyForm = (PjpyHxxyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = service.viewrychshOne(pkValue, userType);
		hxxyForm.setYesNo(rs.get("sh"));
		hxxyForm.setYj(rs.get("yj"));
		hxxyForm.setFdyyj(rs.get("fdyyj"));
		String act = request.getParameter("act");
		String xyyj = DealString.toGBK(request.getParameter("yj"));
		String fdyyj = DealString.toGBK(request.getParameter("fdyyj"));
		String  yesNo = DealString.toGBK(request.getParameter("yesNo"));
		if ("save".equalsIgnoreCase(act)) {
			boolean flag = service.rychshOne(pkValue,userType,yesNo,xyyj,fdyyj);;
			if (flag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		appendChkList(request);
		return mapping.findForward("rychshone");
	}
}
