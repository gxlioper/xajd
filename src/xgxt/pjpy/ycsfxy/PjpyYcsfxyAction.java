package xgxt.pjpy.ycsfxy;

import java.lang.reflect.InvocationTargetException;
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

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.zjlg.ZjlgPjpyUnit;
import xgxt.utils.Fdypd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * deprecated 盐城师范评奖ACTION
 * author litao
 * time 2009.9.24
 */
public class PjpyYcsfxyAction extends CommonAction {

	private PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
	
	private String act;//操作类刑
	
//	private String xydm;
	/**
	 * 综合素质-平时，阶段考核成绩维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjjdkhcjWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String userName = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString()
				: "";
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		if ("xy".equalsIgnoreCase(userType)) {
			ycsfForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
			userType = "fdy";
		}
		
		//默认选择为评奖学年，学期
		if (StringUtils.isNull(ycsfForm.getXn())) {
			ycsfForm.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(ycsfForm.getXq())) {
			ycsfForm.setXq(Base.getJxjsqxq());
		}
		
		act = request.getParameter("go");
		//数据查询
		if ("go".equalsIgnoreCase(act)) {
			PjpyYcsfxyModel model = new PjpyYcsfxyModel();
			BeanUtils.copyProperties(model, ycsfForm);
			topTr = service.getPsjdkhQueryTitle();
			int count = service.getPsjdkhQueryResultCount(model, userType, userName);
			rs = service.getPsjdkhQueryResult(model, userType, userName, ycsfForm);//带分页查询
			ycsfForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		}
		
		ZjlgPjpyUnit unit = new ZjlgPjpyUnit();
		//对用户读写权进行判断
		request.setAttribute("path", "pjpy_ycsf_pjjdkhcjwh.do");
		unit.commonRequestSet(request, "pjpy_ycsf_zhszcpb", "pjpy_ycsf_zhszcpb",  rs, topTr);
		//加载学院，专业，班级下拉列表
		setNjXyZyBjList(request, ycsfForm);
		
		request.setAttribute("userType", userType);
		ycsfForm.setXm(DealString.toGBK(ycsfForm.getXm()));
		ycsfForm.setXh(DealString.toGBK(ycsfForm.getXh()));
		return mapping.findForward("pjjdkhcjWh");
	}
	
	/**
	 * 保存平时，阶段考核成绩信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjjdkhcjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] pkList = request.getParameterValues("pkvalue");//主键值
		String[] pjkhcj = request.getParameterValues("pjkhcj");//平时考核成绩值
		String[] jdkhcj = request.getParameterValues("jdkhcj");//阶段考核成绩值
		String[] dis = request.getParameterValues("dis");//学校审核标志,如果这条数据学校已审核则不能进行操作
		//执行数据保存操作
		boolean result = service.savePjjdkhcjxx(pkList, pjkhcj, jdkhcj, dis);
		request.setAttribute("result", result);
		
		//加载相关列表信息
		pjjdkhcjWh(mapping, form, request, response);
		return mapping.findForward("pjjdkhcjWh");
	}
	
	/**
	 * 删除平时，阶段考核成绩信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjjdkhcjDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] pkList = request.getParameterValues("cbv");
		String failinfo = "";
		boolean result = true;
		if (pkList != null && pkList.length > 0) {
			failinfo = service.deletePjjdkhcjxx(pkList);
		}
		if (!StringUtils.isNull(failinfo)) {
			result = false;
			request.setAttribute("failinfo", "操作完成,其中第" + failinfo + "条数据操作失败!");
		}
		
		//加载相关列表信息
		pjjdkhcjWh(mapping, form, request, response);
		request.setAttribute("result", result);
		return mapping.findForward("pjjdkhcjWh");
	}
	
	/**
	 * 加载学院，年级，专业，班级下拉列表值
	 * @param request
	 * @param myForm
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void setNjXyZyBjList(HttpServletRequest request,
			PjpyYcsfxyActionForm myForm) throws Exception,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		//
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx").toString() != null
				&& "true".equalsIgnoreCase(session.getAttribute("fdyQx")
						.toString())) {
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// 发送班级列表
		}

	}
	
	/**
	 * 综合素质测评比例设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward setZhszcpBl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		boolean flag = false;
		String xxdm = Base.xxdm;
		if(Globals.XXDM_XMLGXY.equalsIgnoreCase(Base.xxdm)||
				Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(Base.xxdm)){
			map.put("pskhcjbl", "德育表现分比例");
			map.put("xykhcjbl", "智育表现分比例");
			map.put("jdkhcjbl", "文体表现分比例");
			request.setAttribute("showXmlgxy","");
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			map.put("pskhcjbl", "学业测评所占比例");
			map.put("jdkhcjbl", "行为表现分所占比例");
			map.put("zhbxfbl", "突出表现分所占比例");
			map.put("xykhcjbl", "综合表现分所占比例");
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
			map.put("pskhcjbl", "德育成绩");
			map.put("xykhcjbl", "智育成绩");
			map.put("jdkhcjbl", "体育成绩");
		} else{
			map.put("pskhcjbl", "平时考核成绩");
			map.put("xykhcjbl", "学业考核成绩");
			map.put("jdkhcjbl", "阶段考核成绩");
		}
		try{
			if("save".equals(myForm.getAct())){
				BeanUtils.copyProperties(model,myForm);
				flag = service.setZhszcpBl_ser(model);
				if(flag){
					request.setAttribute("result", "yes");
				}else{
					request.setAttribute("result", "no");
				}
			}else{
				model = service.getZhszcpBl_ser();
				BeanUtils.copyProperties(myForm,model);
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("result", " no");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("setzhszcpbl");
	}
	
	/**
	 * 综合素质测评维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zhszcpwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = (String)session.getAttribute("userName");
		String[] chkValue = request.getParameterValues("cbv");
		String xydm = "";
		if("xy".equals(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}else if("stu".equals(userType)){
			return new ActionForward("/pjpy_ycsf_stuZhszcpQuery.do");
		}
		if(session.getAttribute("fdyQx") != null
				&& "true".equals((String)session.getAttribute("fdyQx")
						.toString())){
			userType = "fdy";
			request.setAttribute("userType", "fdy");
		}
		xydm = myForm.getXydm() == null ? "":myForm.getXydm();
		String zydm = myForm.getZydm() == null ? "":myForm.getZydm();
		String nj = myForm.getNj() == null ? "":myForm.getNj();
		String bjKey = xydm + "!!" + zydm + "!!" + nj;	
		
		try{
			BeanUtils.copyProperties(model, myForm);
			if ("query".equals(myForm.getAct())) {
				String[] colEn = new String[] { "pk", "xh", "xm", "xn", "xqmc",
						"nj", "bjmc", "pjkhcj", "xykhcj", "jdkhcj", "zhszcpzf",
						"pm","xxsh" };
				String[] colCn = new String[] { "pk", "学号", "姓名", "学年", "学期",
						"年级", "班级名称", "平时考核成绩", "阶段考核成绩", "学业考核成绩","学业班级排名", "综测成绩",
						"综测班级排名", "学校审核" };
				List topTr = service.getTabName_ser(colEn, colCn);
				String isFdy = session.getAttribute("fdyQx") == null ? ""
						: session.getAttribute("fdyQx").toString();
				int count = service.zhszcpQueryCount_ser(model, userName, isFdy);
				myForm.getPages().setMaxRecord(count);// 设置最大的记录数
				List<String[]> rs = service.zhszcpQuery_ser(myForm, model,
						userName, isFdy);
				request.setAttribute("rs", rs);
				request.setAttribute("topTr", topTr);
			} else if ("compute".equals(myForm.getAct())) {
				request.setAttribute("result", service.computeZhszcp_ser(userType,xydm,userName));
			} else if("sh_all".equals(myForm.getAct())){
				request.setAttribute("result", service.zhszcpShAll_ser());
			} else if("sh_part".equals(myForm.getAct()) || "sh_btg".equals(myForm.getAct())
					||  "sh_cx".equals(myForm.getAct())){
				request.setAttribute("result", service.zhszcpShPart_ser(chkValue,myForm.getAct()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(myForm.getXn() == null){
			myForm.setXn(Base.getJxjsqxn());
		}
		if(myForm.getXq() == null){
			myForm.setXq(Base.getJxjsqxq());
		}
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		List<HashMap<String,String>> bjList = (Base.getBjMap()).get(bjKey) ==null ? 
				new ArrayList<HashMap<String,String>>() :(Base.getBjMap()).get(bjKey);
		request.setAttribute("tableName", "view_ycsf_zhszcp");
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));
		request.setAttribute("bjList", bjList);
		if (session.getAttribute("fdyQx") != null
				&& "true".equals((String)session.getAttribute("fdyQx")
						.toString())) {
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// 发送班级列表
		}
		return mapping.findForward("zhszcpwh");
	}
	
	/**
	 * 教师查看学生成绩
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward viewxskccj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String pk = request.getParameter("pk");
		String view = request.getParameter("view");
		String psjd = request.getParameter("psjd");
		String zhszcp = request.getParameter("zhszcp");
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		CommonAction comm = new CommonAction();
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		HashMap<String, String> shMap = null;
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
		}
		String pkVal = "";
		try {
			BeanUtils.copyProperties(model, myForm);
			if ("save".equals(myForm.getAct())){
				request.setAttribute("result", service.jxjOrRychDgsh_ser(pk,
						model,userType));
			}
			if (Base.isNull(myForm.getDm())) {
				pkVal = pk;

			} else {
				pkVal = pk.substring(0, pk.length() - myForm.getDm().length());
				shMap = service.getJxjOrRychShxx_ser(myForm.getLb(), pk);

				comm.appendJxjList(request);
				comm.appendRychList(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("yes".equals(psjd)){
			request.setAttribute("xsxxmap", service.getXsxxAndPsjdcj_ser(pkVal));
			request.setAttribute("psjd", psjd);
		}else{
			request.setAttribute("xsxxmap", service.getXsxxAndCjhz_ser(pkVal));
			if("yes".equals(zhszcp)){
				request.setAttribute("psjd", zhszcp);
			}
		}
		request.setAttribute("view", view);
		request.setAttribute("lb", myForm.getLb());
		request.setAttribute("pk", pk);
		request.setAttribute("rs", service.getXscj_ser(pkVal));
		request.setAttribute("shMap", shMap);
		return mapping.findForward("viewxskccj");
	}
	
	/**
	 * 学生综合素质测评成绩查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward stuZhszcpQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String xh = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString()
				: ""; 
		List<String[]> rs = service.stuQueryZhszcpxx(xh);
		request.setAttribute("rs", rs);
		return mapping.findForward("stuZhszcpQuery");
	}
	
	/**
	 * 学生综合素质测评显示详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward stuZhszcpView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pk = request.getParameter("pk");
		HashMap<String, String> rs = service.stuZhszcpView(pk);
		List<String[]> cjList = service.stuCjInfo(pk);
		request.setAttribute("rs", rs);
		request.setAttribute("cjList", cjList);
	
		return mapping.findForward("stuZhszcpView");
	}
	
	/**
	 * 奖学金荣誉称号审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward jxjRychSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		CommonAction comm = new CommonAction();
		String[] chkValue = request.getParameterValues("cbv");
		String xydm = myForm.getXydm() == null ? "":myForm.getXydm();
		String zydm = myForm.getZydm() == null ? "":myForm.getZydm();
		String nj = myForm.getNj() == null ? "":myForm.getNj();;
		String bjKey = xydm + "!!" + zydm + "!!" + nj;	
		HttpSession session = request.getSession();
		List<String[]> rs = null;
		String userType = session.getAttribute("userType") != null ? session.getAttribute("userType").toString():"";
		String userDep = session.getAttribute("userDep") != null ? session.getAttribute("userDep").toString():"";
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
		}
		if("xy".equals(userType)){
			myForm.setXydm(userDep);
			request.setAttribute("xydm", userDep);
		}
		try{
			BeanUtils.copyProperties(model, myForm);
			if ("query".equals(myForm.getAct())) {
				
				List topTr = service.getJxjOrRychTabName(model.getLb());
				int count = service.getJxjOrRychCount_db(model);
				myForm.getPages().setMaxRecord(count);// 设置最大的记录数
				if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
					rs = service.getJxjOrRychList_ser(myForm, model,userType);
				}else{
					rs = service.getJxjOrRychQueryList_ser(myForm, model);
				}
				request.setAttribute("rs", rs);
				request.setAttribute("topTr", topTr);
			} else if("sh_all".equals(myForm.getAct())){
				request.setAttribute("result", service.jxjOrRychShAll_ser(userType,userDep));
			} else if("sh_part".equals(myForm.getAct()) || "sh_btg".equals(myForm.getAct()) 
					|| "sh_cx".equals(myForm.getAct())){
				request.setAttribute("result", service.jxjOrRychShPart_ser(chkValue,myForm.getAct(),model.getLb(),userType,userDep));
			}
			comm.appendJxjList(request);
			comm.appendRychList(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		myForm.setXn(Base.getJxjsqxn());
		myForm.setXq(Base.getJxjsqxq());
		if(myForm.getLb() == null){
			myForm.setLb("jxj");
		}
		request.setAttribute("form", myForm);
		request.setAttribute("lb", myForm.getLb());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		return mapping.findForward("jxjrychsh");
	}
	
	/**
	 * 学院获奖名单上报默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xyhjmdSb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		String tableName = "";
		String realTable = "";
		if (StringUtils.isNull(ycsfForm.getXn())) {
			ycsfForm.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(ycsfForm.getXq())) {
			ycsfForm.setXq(Base.getJxjsqxq());
		}
		
		String lb = ycsfForm.getLb();
		if (StringUtils.isNull(lb)) {
			lb = "jxj";
		}
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
			realTable = "view_ahzyjs_xsjxjb";
			if ("rych".equalsIgnoreCase(lb)) {
				realTable = "view_ahzyjs_xsrychb";
			}
		}else{
			realTable = "view_ycsf_xsjxjb";
			if ("rych".equalsIgnoreCase(lb)) {
				realTable = "view_ycsf_xsrychb";
			}
		}
		tableName = "xsjxjb";
		if ("rych".equalsIgnoreCase(lb)) {
			tableName = "xsrychb";
		}
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String userName = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString()
				: "";
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		if ("xy".equalsIgnoreCase(userType)) {
			ycsfForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
			userType = "fdy";
		}
		
		act = request.getParameter("act");
		//数据查询
		if ("query".equalsIgnoreCase(act)) {
			PjpyYcsfxyModel model = new PjpyYcsfxyModel();
			BeanUtils.copyProperties(model, ycsfForm);
			topTr = service.queryHjmdxxTitle();
			rs = service.queryHjmdxxResult(model, userType, userName, ycsfForm);
			int count = service.queryHjmdxxResultCount(model, userType, userName, ycsfForm);
			ycsfForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		}
		
		ZjlgPjpyUnit unit = new ZjlgPjpyUnit();
		//对用户读写权进行判断
		request.setAttribute("path", "pjpy_ycsf_xyhjmdsb.do");
		unit.commonRequestSet(request, realTable, tableName,  rs, topTr);
		//加载学院，专业，班级下拉列表
		setNjXyZyBjList(request, ycsfForm);
		request.setAttribute("userType", userType);
		ycsfForm.setXm(DealString.toGBK(ycsfForm.getXm()));
		ycsfForm.setXh(DealString.toGBK(ycsfForm.getXh()));
		appendJxjList(request);
		appendRychList(request);
		request.setAttribute("lb", lb);
		return mapping.findForward("xyhjmdSb");
	}
	
	/**
	 * 增加获奖名单信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addHjmdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		String lb = request.getParameter("lb");//保存的项目类别
		String xh = request.getParameter("xh");
		String act = request.getParameter("act");//保存数据标志
		if (StringUtils.isNull(lb)) {
			lb = "jxj";
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		List<String[]> cjList = new ArrayList<String[]>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh,Base.xxdm);//查询学生信息及综测信息
			cjList = service.stuCjInfo(xh, Base.getJxjsqxn(), Base.getJxjsqxq());//查询学生成绩信息
			
		}
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
			if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
				userType = "fdy";
			}
			request.setAttribute("userType", userType);
		}
		if ("save".equalsIgnoreCase(act)) {//保存数据,
			PjpyYcsfxyModel model = new PjpyYcsfxyModel();
			BeanUtils.copyProperties(model, ycsfForm);
			model.setXn(Base.getJxjsqxn());
			model.setXq(Base.getJxjsqxq());
			boolean result = service.saveHjmdxx(model, userType, lb);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			
		}
		rs.put("xn", Base.getJxjsqxn());
		rs.put("xq", Base.getJxjsqxqmc());
		request.setAttribute("rs", rs);
		request.setAttribute("cjList", cjList);
		appendJxjList(request);
		appendRychList(request);
		request.setAttribute("lb", lb);
		return mapping.findForward("addHjmdxx");
	}
	
	/**
	 * 删除获奖名单信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delHjmdxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String type = request.getParameter("type");
		String[] pkList = request.getParameterValues("cbv");
		boolean result = service.deleteHjmdxx(pkList, type);//删除数据操作
		request.setAttribute("result", result);
		xyhjmdSb(mapping, form, request, response);//加载列表信息
		return mapping.findForward("xyhjmdSb");
	}
	
	/**
	 * 修改获奖名单信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hjmdxxModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		String lb = request.getParameter("lb");//奖项类别
		lb = StringUtils.isNull(lb) ? "jxj" : lb;
		String pkValue = request.getParameter("pk");//主键
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		if (Globals.XXDM_AHZYJSXY.equals(Base.xxdm)) {
			request.setAttribute("ahzyjsxy", "yes");
			if (Fdypd.isFdy(request.getSession().getAttribute("userName")
					.toString())) {
				userType = "fdy";
				request.setAttribute("userType", userType);
			}
		} 
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {//保存修改操作
			PjpyYcsfxyModel model = new PjpyYcsfxyModel();
			BeanUtils.copyProperties(model, ycsfForm);
			boolean result = service.modiHjmdxx(userType, lb, pkValue, model);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		
		HashMap<String, String> rs = service.viewHjmdxx(lb, userType, pkValue);//显示修改信息
		if ("jxj".equalsIgnoreCase(lb)) {
			rs.put("lb", "奖学金");
		} else {
			rs.put("lb", "荣誉称号");
		}
		if("fdy".equals(userType)){
			if ("未审核".equalsIgnoreCase(rs.get("xysh"))) {
				request.setAttribute("modi", "true");
			}
		}else if("xy".equals(userType)){
			if ("未审核".equalsIgnoreCase(rs.get("xxsh"))) {
				request.setAttribute("modi", "true");
			}
		}else{
			request.setAttribute("modi", "true");
		}
		
		//ycsfForm.setDm(rs.get("dm"));
		//ycsfForm.setXyshyj(rs.get("xyshyj"));
		List<String[]> cjList = new ArrayList<String[]>();//学生成绩列表
		cjList = service.stuCjInfo(rs.get("xh"), rs.get("xn"), rs.get("xq"));
		request.setAttribute("cjList", cjList);
		
		request.setAttribute("lb", lb);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		appendJxjList(request);
		appendRychList(request);
		return mapping.findForward("hjmdxxModi");
	}
	
	/**
	 * 学院获奖名单量上报保存页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hjmdSb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		String xhList = request.getParameter("xhList");
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
				
		String lb = request.getParameter("lb");//奖项类别
		lb = StringUtils.isNull(lb) ? "jxj" : lb;
		if ("jxj".equalsIgnoreCase(lb)) {
			request.setAttribute("lbmc", "奖学金");
		} else {
			request.setAttribute("lbmc", "荣誉称号");
		}
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
				userType = "fdy";
				request.setAttribute("userType", userType);
			}
		}
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			PjpyYcsfxyModel model = new PjpyYcsfxyModel();
			BeanUtils.copyProperties(model, ycsfForm);
			model.setXn(Base.getJxjsqxn());
			model.setXq(Base.getJxjsqxq());
			String[] xhArray = !StringUtils.isNull(xhList) ? xhList.split("!@") : new String[]{};
			boolean result = service.hjmdPlsb(userType, model, xhArray);
			if (result) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		
		request.setAttribute("lb", lb);
		request.setAttribute("xn", Base.getJxjsqxn());
		request.setAttribute("xq", Base.getJxjsqxqmc());
		appendJxjList(request);
		appendRychList(request);
		request.setAttribute("xhList", xhList);
		return mapping.findForward("hjmdSb");
	}
	
	/**
	 * 获奖名单显示详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hjmdxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
//		PjpyYcsfxyActionForm ycsfForm = (PjpyYcsfxyActionForm) form;
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		String pkValue = request.getParameter("pk");
		String lb = request.getParameter("lb");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
			if (Fdypd.isFdy(request.getSession().getAttribute("userName")
					.toString())) {
				userType = "fdy";
				request.setAttribute("userType", userType);
			}
		}
		model.setXn(xn);
		model.setXq(xq);
		model.setXh(xh);
		HashMap<String, String> rs = service.viewHjmdxx(model, pkValue, userType, lb);
		if ("jxj".equalsIgnoreCase(lb)) {
			rs.put("lb", "奖学金");
		} else {
			rs.put("lb", "荣誉称号");
		}
		//ycsfForm.setDm(rs.get("dm"));
		//ycsfForm.setXyshyj(rs.get("xyshyj"));
		List<String[]> cjList = service.stuCjInfo(xh, xn, xq);
		request.setAttribute("rs", rs);
		appendJxjList(request);
		appendRychList(request);
		request.setAttribute("lb", lb);
		request.setAttribute("cjList", cjList);
		return mapping.findForward("hjmdxxView");
	}
	
	/**
	 * 奖学金荣誉称号审核结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryShResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = (String)session.getAttribute("userName");
		CommonAction comm = new CommonAction();
		String xydm = "";
		String tableName = "";
		String realTable = "";
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
			tableName = "view_ahzyjs_xsjxjb";
			if ("rych".equalsIgnoreCase(myForm.getLb())) {
				tableName = "view_ahzyjs_xsrychb";
			}
		}else{
			tableName = "view_ycsf_xsjxjb";
			if ("rych".equalsIgnoreCase(myForm.getLb())) {
				tableName = "view_ycsf_xsrychb";
			}
		}
		realTable = "xsjxjb";
		if ("rych".equalsIgnoreCase(myForm.getLb())) {
			realTable = "xsrychb";
		}
		if("xy".equals(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}else if("stu".equals(userType)){
			request.setAttribute("jxjrs", service.queryXsJxj_ser(userName));
			request.setAttribute("rychrs", service.queryXsRych_ser(userName));
			return mapping.findForward("studentQueryResult");
		}
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			request.setAttribute("ahzyjsxy", "yes");
		}
		xydm = myForm.getXydm() == null ? "":myForm.getXydm();
		String zydm = myForm.getZydm() == null ? "":myForm.getZydm();
		String nj = myForm.getNj() == null ? "":myForm.getNj();
		String bjKey = xydm + "!!" + zydm + "!!" + nj;	
		try{
			BeanUtils.copyProperties(model, myForm);
			if ("query".equals(myForm.getAct())) {
				List topTr = service.getJxjOrRychTabName(model.getLb());
				String isFdy = session.getAttribute("fdyQx") == null ? ""
						: session.getAttribute("fdyQx").toString();
				int count = service.queryShResultCount_ser(model, userName, isFdy);
				myForm.getPages().setMaxRecord(count);// 设置最大的记录数
				List<String[]> rs = service.queryShResult_ser(myForm, model,
						userName, isFdy);
				request.setAttribute("rs", rs);
				request.setAttribute("topTr", topTr);
			} 
			comm.appendJxjList(request);
			comm.appendRychList(request);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(myForm.getXn() == null){
			myForm.setXn(Base.getJxjsqxn());
		}
		if(myForm.getXq() == null){
			myForm.setXq(Base.getJxjsqxq());
		}
		if(myForm.getLb() == null){
			myForm.setLb("jxj");
		}
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		request.setAttribute("form", myForm);
		request.setAttribute("lb", myForm.getLb());
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx") != null
				&& "true".equals((String)session.getAttribute("fdyQx")
						.toString())) {
			// 辅导员登录
			request.setAttribute("userType", "fdy");
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// 发送班级列表
		}
		return mapping.findForward("teacherQueryResult");
	}
	
	/**
	 * 奖学金荣誉称号审核结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward expBjcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PjpyYcsfxyActionForm myForm = (PjpyYcsfxyActionForm) form;
		PjpyYcsfxyModel model = new PjpyYcsfxyModel();
		PjpyYcsfxyService service = PjpyYcsfxyService.getInstance();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		try {
			BeanUtils.copyProperties(model, myForm);
			service.expBjcj_ser(model, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
