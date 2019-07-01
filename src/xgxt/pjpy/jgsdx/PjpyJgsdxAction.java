
package xgxt.pjpy.jgsdx;

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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学评奖评优Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-026</p>
 */
public class PjpyJgsdxAction extends DispatchAction {
	
	String xydm = "";
	String zydm = "";
	String nj = "";
	
	CommonAction commonAction = new CommonAction();
	
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
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm; 
		njLocal = nj==null ? "": nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> zyjxjList = service.getZyjxjList();//专业奖学金列表
		request.setAttribute("zyjxjList", zyjxjList);
		request.setAttribute("writeAble", "yes");//判断用户读写权
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());//学年列表
		request.setAttribute("njList", Base.getNjList());//年级列表
		request.setAttribute("xyList", Base.getXyList());//学院列表
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
		request.setAttribute("userType", userType);//用户类型
	}
	
	public void appentProperties1(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String zyKey = "";
		String bjKey = "!!!!";
		if("xy".equalsIgnoreCase(userType)){
			zyKey = userDep;
			bjKey = zyKey+"!!"+""+"!!"+"";
		}
		request.setAttribute("userType", userType);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
	}
	
	/**
	 * 专业奖学金上报默认页面
	 * zyjxjSbDefault ---- 专业奖学金上报默认页面 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjSbDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session    = request.getSession();
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		String userName		   = session.getAttribute("userName").toString();//用户名
		String isFdy 		   = session.getAttribute("isFdy").toString();//是否是辅导员
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		//辅导员用户不具备自动审核功能
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "false")) {
			request.setAttribute("showxyxx", "true");
		}
		request.setAttribute("tableName", "view_xszyjxj");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * 刷新页面奖励金额
	 * refreshJlje ---- 刷新页面奖励金额 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward refreshJlje(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		String userName		   = session.getAttribute("userName").toString();//用户名
		String isFdy 		   = session.getAttribute("isFdy").toString();//是否是辅导员
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		
		String jxjdm = jgsdxForm.getJxjdm();
		PjpyJgsdxService service = new PjpyJgsdxService();
		String jlje = service.getJxjJeByJxjdm(jxjdm);//通过奖学金代码获取奖学金额
		jgsdxForm.setJlje(jlje);
//		辅导员用户不具备自动审核功能
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "false")) {
			request.setAttribute("showxyxx", "true");
		}
		request.setAttribute("tableName", "view_xszyjxj");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * 专业奖学金上报查询
	 * zyjxjsbSearch ---- 专业奖学金上报查询 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjsbSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		ZyjxjQryModel zyjxjModel = new ZyjxjQryModel();//专业奖学金上报查询MODEL
		
		HttpSession session    		= request.getSession();
		String bmdm            		= session.getAttribute("userDep").toString();//用户所在部门
//		String userName             = session.getAttribute("userName").toString();
		String userType        		= session.getAttribute("userType").toString();//用户类型
		String isFdy 			    = session.getAttribute("isFdy").toString();//是否是辅导员
//		用户是学院或管理员，没有审核单位的分配
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		BeanUtils.copyProperties(zyjxjModel, jgsdxForm);
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "true")) {
			request.setAttribute("fdyxx", "双击一行可以进行综合素质，学习成绩排名设置，单击表头可以进行排序;");
			request.setAttribute("isFdy", "yes");
		}else {
			request.setAttribute("fdyxx", "单击表头可以进行排序;");
		}
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> topList = service.getZyjxjSbTitle(isFdy, userType);//专业奖学金查询表头
		List<String[]> resList = service.getZyjxjSbResult(isFdy, userType, zyjxjModel);//专业奖学金查询结果
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		request.setAttribute("topTr", topList);
//		辅导员用户不具备自动审核功能
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "false")) {
			request.setAttribute("showxyxx", "true");
		}
		request.setAttribute("tableName", "view_xszyjxj");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * 综合素质和学习成绩设置
	 * setXhszAndXxcj ---- 综合素质和学习成绩设置 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setXhszAndXxcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String isFdy = request.getSession().getAttribute("isFdy").toString();
		if (StringUtils.isEqual(isFdy, "true")) {
			request.setAttribute("fdywritable", "yes");
		}
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String jxjdm = request.getParameter("jxjdm");
		pkValue = xh + xn + jxjdm;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HashMap<String, String> resMap = service.getZhszandXxcjByPk(pkValue, xh);//通过主键获取综合素质和学习成绩
		List shztList = service.getChkList(3);//审核状态
		jgsdxForm.setXh(xh);
		jgsdxForm.setBz(resMap.get("bz"));
		jgsdxForm.setZhszpm(resMap.get("zhszpm"));
		jgsdxForm.setXm(resMap.get("xm"));
		jgsdxForm.setXxcjpm(resMap.get("xxcjpm"));
		jgsdxForm.setSfsf(resMap.get("sfsf"));
		jgsdxForm.setXn(xn);
		jgsdxForm.setXq(resMap.get("xq"));
		jgsdxForm.setShzt(resMap.get("fdysh"));
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("jxjdm", jxjdm);
		request.setAttribute("xn", xn);
		request.setAttribute("shztList", shztList);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszandxxcj");
	}
	
	/**
	 * 综合素质和学习成绩保存
	 * zhszandxxcjSave ---- 综合素质和学习成绩保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszandxxcjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		ZhszandXxcjSaveModel zxsaveModel = new ZhszandXxcjSaveModel();//综合素质和学习成绩保存MODEL
		String isFdy = request.getSession().getAttribute("isFdy").toString();
		if (StringUtils.isEqual(isFdy, "true")) {
			request.setAttribute("fdywritable", "yes");
		}
		BeanUtils.copyProperties(zxsaveModel, jgsdxForm);
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String pkValue = xh + request.getParameter("xn")+request.getParameter("jxjdm");
		zxsaveModel.setXn(xn);
		zxsaveModel.setPkValue(xh+xn+request.getParameter("jxjdm"));
		zxsaveModel.setJxjdm(request.getParameter("jxjdm"));
		PjpyJgsdxService service = new PjpyJgsdxService();
		boolean bFlag = service.zhszandxxcjSave(zxsaveModel, request);
		if (bFlag) {
			request.setAttribute("result", "view");
		} else {
			request.setAttribute("result", "noview");
		}
		HashMap<String, String> resMap = service.getZhszandXxcjByPk(pkValue, xh);//通过主键获取综合素质和学习成绩
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		jgsdxForm.setBz("");
		List shztList = service.getChkList(3);//审核状态
		request.setAttribute("shztList", shztList);
		return mapping.findForward("zhszandxxcj");
	}
	
	/**
	 * 专业奖学金自动审核
	 * zyjxjautosh ---- 专业奖学金自动审核 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjAutoSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session    = request.getSession();
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		String userName		   = session.getAttribute("userName").toString();//用户名
		String isFdy           = session.getAttribute("isFdy").toString();//辅导员
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		}
		ZyjxjAutoshModel zyjxjModel = new ZyjxjAutoshModel();
		BeanUtils.copyProperties(zyjxjModel, jgsdxForm);
		service.zyjxjAutoSh(zyjxjModel, request);//自动审核
		
//		辅导员用户不具备自动审核功能
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "false")) {
			request.setAttribute("showxyxx", "true");
		}
		request.setAttribute("tableName", "view_xszyjxj");
		request.setAttribute("result", "noview");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * 专业奖学金打印
	 * zyjxjPrint ---- 专业奖学金打印 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		ZyjxjQryModel zyjxjModel = new ZyjxjQryModel();
		BeanUtils.copyProperties(zyjxjModel, jgsdxForm);
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<String[]> resList = service.zyjxjPrint(zyjxjModel);//奖学金打印
		request.setAttribute("rs", resList);
		request.setAttribute("xn", request.getParameter("xn"));
		return mapping.findForward("zyjxjprint");
	}
	
	/**
	 * 专业奖学金审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session    = request.getSession();
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		ZyjxjShModel zyjxjModel = new ZyjxjShModel();
		BeanUtils.copyProperties(zyjxjModel, jgsdxForm);
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		String userName		   = session.getAttribute("userName").toString();//用户名
		String isFdy           = session.getAttribute("isFdy").toString();//辅导员
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "true")) {
			request.setAttribute("fdyxx", "双击一行可以进行综合素质，学习成绩排名设置，单击表头可以进行排序;");
			request.setAttribute("isFdy", "yes");
		}else {
			request.setAttribute("fdyxx", "单击表头可以进行排序;");
		}
		PjpyJgsdxService service = new PjpyJgsdxService();
		String[] cbv = jgsdxForm.getCbv();
		String res = request.getParameter("param1");
		zyjxjModel.setCbv(cbv);//主键列表
		zyjxjModel.setRes(res);//审核结果
		service.zyjxjSh(isFdy, userType, zyjxjModel, request);//奖学金审核
//		辅导员用户不具备自动审核功能
		if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "false")) {
			request.setAttribute("showxyxx", "true");
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("tableName", "view_xszyjxj");
		request.setAttribute("result", "view");
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * 专业奖学金批量删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjxjblDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session    = request.getSession();
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		ZyjxjShModel zyjxjModel = new ZyjxjShModel();
		BeanUtils.copyProperties(zyjxjModel, jgsdxForm);
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		String userName		   = session.getAttribute("userName").toString();//用户名
//		String isFdy           = session.getAttribute("isFdy").toString();//辅导员
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		PjpyJgsdxService service = new PjpyJgsdxService();
		String[] keys = jgsdxForm.getCbv();
		String result = service.zyjxjblDel(keys);//批量删除
		if (!StringUtils.isNull(result)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", result);
		}
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyjxjsbdefault");
	}
	
	/**
	 * 荣誉称号申请默认页面
	 * rysqdefault ---- 荣誉申请默认
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rysqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> list = service.getRysqList(1);//申请列表
		request.setAttribute("list", list);
		return mapping.findForward("rysqdefault");
	}
	
	/**
	 * 文明班级申请默认页面
	 * wmbjsqdefault ---- 文明班级申请默认 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjsqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> titleList = service.getRysqList(2);//文明班级列表
		String defaultTitle = request.getParameter("titName");
		if(defaultTitle == null || defaultTitle.trim().equals("")){
			defaultTitle = "xjbj";
		}
		appentProperties1(request, xydm, zydm, nj);
		
		request.setAttribute("defaultTitle", defaultTitle);
		request.setAttribute("titleList", titleList);
		return mapping.findForward("wmbjsqdefault");
	}
	
	/**
	 * 文明班级申请结果,先验证数据是否重复
	 * wmbjsqjg ---- 文明班级申请结果 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjsqJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		WmbjSqModel wmbjModel = new WmbjSqModel();
		BeanUtils.copyProperties(wmbjModel, jgsdxForm);
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> titleList = service.getRysqList(2);//文明班级列表
		String defaultTitle = request.getParameter("titName");
		if(defaultTitle == null || defaultTitle.trim().equals("")){
			defaultTitle = "xjbj";
		}
		boolean bChk = service.chkDataByWmbj(wmbjModel);
		if (bChk) {//存在
			request.setAttribute("result", "haved");
		}else {//不存在
			boolean bFlag = service.saveWmBjInfo(wmbjModel, request);//保存数据
			if (bFlag) {
				request.setAttribute("result", "true");
			}else{
				request.setAttribute("result", "false");
			}//end if
		}//end if
		
		jgsdxForm.setBzr(DealString.toGBK(jgsdxForm.getBzr()));
		jgsdxForm.setBzxm(DealString.toGBK(jgsdxForm.getBzxm()));
		jgsdxForm.setZysj(DealString.toGBK(jgsdxForm.getZysj()));
		appentProperties1(request, xydm, zydm, nj);
		request.setAttribute("defaultTitle", defaultTitle);
		request.setAttribute("titleList", titleList);
		return mapping.findForward("wmbjsqdefault");
	}

	/**
	 * 荣誉称号查询默认页面
	 * rychcxDefault ---- 荣誉称号查询默认 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychcxDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> list = service.getRysqList(3);//申请列表
		request.setAttribute("list", list);
		return mapping.findForward("rychcxdefault");
	}
	
	/**
	 * 文明班级查询默认
	 * wmbjcxdefault ---- 文明班级查询默认
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjcxDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
		//用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		List<HashMap<String, String>> shxmList = service.getRysqList(2);
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");
		request.setAttribute("tableName", "view_pjpy_xjbjandwmsq");
		return mapping.findForward("wmbjcxdefault");
	}
	
	/**
	 * 文明班级查询结果
	 * wmbjcxjg ---- 文明班级查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjcxJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
		//用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		WmbjSqModel wmbjModel = new WmbjSqModel();
		BeanUtils.copyProperties(wmbjModel, jgsdxForm);
		wmbjModel.setShxm("xjbj");
		wmbjModel.setXydm(xydm);
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> shxmList = service.getRysqList(2);//查询项目列表
		List<HashMap<String, String>> topList = service.wmbjcxTitle();//查询表头
		List<String[]> resList = service.wmbjsqCxJg(wmbjModel);//查询结果
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");
		request.setAttribute("tableName", "view_pjpy_xjbjandwmsq");
		return mapping.findForward("wmbjcxdefault");
	}
	
	//文明班级的修改同安徽建筑工业学院的先进班级修改
	
	/**
	 * 文明班级批量删除
	 * wmbjsqdel ---- 文明班级批量删除 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjsqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		String[] keys = jgsdxForm.getCbv();
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> shxmList = service.getRysqList(2);
		String sJg = service.delWmbjXx(keys);//批量删除
		if (!StringUtils.isNull(sJg)) {
			request.setAttribute("result", "view");
		} else {
			request.setAttribute("result", sJg);
		}
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");
		request.setAttribute("tableName", "view_pjpy_xjbjandwmsq");
		return mapping.findForward("wmbjcxdefault");
	}
	
	/**
	 * 文明班级打印报表
	 * wmbjprint ---- 文明班级打印报表 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> resMap = service.getWmbjByPk(pkValue);
		request.setAttribute("rs", resMap);
		return mapping.findForward("wmbjprint");
	}
	
	/**
	 * 荣誉称号审核默认页面
	 * rychshDefault ---- 荣誉称号审核默认 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychshDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> list = service.getRysqList(1);//申请列表
		request.setAttribute("list", list);
		return mapping.findForward("rychshdefault");
	}
	
	/**
	 * 文明班级审核
	 * wmbjsh ---- 文明班级审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> shxmList = service.getRysqList(2);
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		String userName		   = session.getAttribute("userName").toString();//用户名
//		String isFdy           = session.getAttribute("isFdy").toString();//辅导员
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("wmbjsh");
	}
	
	/**
	 * 文明班级审核查询结果
	 * wmbjshqry ---- 文明班级审核查询结果 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjshQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		WmbjSqModel wmbjModel = new WmbjSqModel();
		BeanUtils.copyProperties(wmbjModel, jgsdxForm);
		wmbjModel.setShxm("xjbj");
		List<HashMap<String, String>> shxmList = service.getRysqList(2);
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		String userName		   = session.getAttribute("userName").toString();//用户名
		String isFdy           = session.getAttribute("isFdy").toString();//辅导员
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		wmbjModel.setXydm(xydm);
		List<HashMap<String, String>> topList = service.wmbjShTitle(isFdy, userType, request);//查询表头
		List<String[]> resList = service.wmbjShResult(isFdy, userType, wmbjModel, request);//查询结果
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		return mapping.findForward("wmbjsh");
	}
	
	/**
	 * 文明班级审核详细信息显示
	 * wmbjshview ---- 文明班级审核显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward wmbjshView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		String userName		   = session.getAttribute("userName").toString();//用户名
		String isFdy           = session.getAttribute("isFdy").toString();//辅导员
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");//主键
		HashMap<String, String> resMap = service.getWmbjShView(isFdy, userType, pkValue);//单个审核信息
		List<HashMap<String, String>> shxmList = service.getChkList(3);//审核列表
		jgsdxForm.setShxm(resMap.get("sh"));
		jgsdxForm.setShyj(resMap.get("shyj"));
		request.setAttribute("rs", resMap);
		request.setAttribute("shxmList", shxmList);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("wmbjshview");
	}
	
	/**
	 * 文明班级单个审核
	 * wmbjshbyone ---- 文明班级单个审核 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward wmbjShByone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");//主键
		WmbjShModel wmbjModel = new WmbjShModel();//文明班级单个审核MODEL
		BeanUtils.copyProperties(wmbjModel, jgsdxForm);
		wmbjModel.setPkValue(pkValue);
		HashMap<String, String> resMap = new HashMap<String, String>();
		List<HashMap<String, String>> shxmList = service.getChkList(3);//审核列表
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		String userName		   = session.getAttribute("userName").toString();//用户名
		String isFdy           = session.getAttribute("isFdy").toString();//辅导员
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		boolean bFlag = service.wmbjShByOne(isFdy, userType, wmbjModel, request);//单个审核
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", resMap);
		request.setAttribute("shxmList", shxmList);
		return mapping.findForward("wmbjshview");
	}
	
	/**
	 * 文明班级集体审核
	 * wmbjjtsh ---- 文明班级集体审核 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wmbjjtSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		List<HashMap<String, String>> shxmList = service.getRysqList(2);
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
//		String userName		   = session.getAttribute("userName").toString();//用户名
		String isFdy           = session.getAttribute("isFdy").toString();//辅导员
//		用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		String[] keys = jgsdxForm.getCbv();//主键列表
		String res = request.getParameter("param1");//审核结果
		service.wmbjJtSh(isFdy, userType, keys, res, request);
		request.setAttribute("shxmList", shxmList);
		jgsdxForm.setShxm("xjbj");
		request.setAttribute("ag", jgsdxForm);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("result", "view");
		return mapping.findForward("wmbjsh");
	}
	
	/**
	 * 个人荣誉称号审核默认页面
	 * grrychshDefault ---- 个人荣誉称号审核默认
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychshDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
		//用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		List<HashMap<String, String>> rychList = service.getRychList();//荣誉称号列表
		String[] jxjsqnxndList = service.getJxjsqXnNd();//奖学金申请学年年度
		if (jxjsqnxndList != null && jxjsqnxndList.length == 2) {
			jgsdxForm.setXn(jxjsqnxndList[0]);
			jgsdxForm.setNd(jxjsqnxndList[1]);
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rychList", rychList);
		return mapping.findForward("grrychshdefault");
	}
	
	/**
	 * 个人荣誉称号审核查询（辅导员，学院，学校）
	 * grrychshQry ---- 个人荣誉称号审核查询 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychshQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		RychQryModel rychModel = new RychQryModel();//荣誉称号查询MODEL
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
		String isFdy 		   = session.getAttribute("isFdy").toString();//是否辅导员
		//用户是辅导员,学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		}
		String[] jxjsqnxndList = service.getJxjsqXnNd();//奖学金申请学年年度
		if (jxjsqnxndList != null && jxjsqnxndList.length == 2) {
			jgsdxForm.setXn(jxjsqnxndList[0]);
			jgsdxForm.setNd(jxjsqnxndList[1]);
		}
		BeanUtils.copyProperties(rychModel, jgsdxForm);
		rychModel.setXydm(xydm);
		List<HashMap<String, String>> topList = service.getRychQryTitle(isFdy, userType, jgsdxForm.getXmdm());//查询表头
		List<String[]> resList = service.getRychQryResult(isFdy, userType, rychModel, jgsdxForm.getXmdm());//查询结果
		List<HashMap<String, String>> rychList = service.getRychList();//荣誉称号列表
		
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rychList", rychList);
		return mapping.findForward("grrychshdefault");
	}
	
	/**
	 * 个人荣誉称号审核（辅导员，学院，学校）
	 * grrychsh ---- 个人荣誉称号审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
		String isFdy 		   = session.getAttribute("isFdy").toString();//是否辅导员
		//用户是辅导员,学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		}
		String[] jxjsqnxndList = service.getJxjsqXnNd();//奖学金申请学年年度
		if (jxjsqnxndList != null && jxjsqnxndList.length == 2) {
			jgsdxForm.setXn(jxjsqnxndList[0]);
			jgsdxForm.setNd(jxjsqnxndList[1]);
		}
		String[] cbv = jgsdxForm.getCbv();//主键
		String shjg = request.getParameter("param1");//审核结果
		GrrychShModel grrychModel = new GrrychShModel();
		grrychModel.setCbv(cbv);
		grrychModel.setRychdm(jgsdxForm.getXmdm());
		grrychModel.setShjg(shjg);
		List<HashMap<String, String>> rychList = service.getRychList();//荣誉称号列表
		service.grrychSh(isFdy, userType, grrychModel, request);//用户审核
		request.setAttribute("updated", "yes");
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rychList", rychList);
		return mapping.findForward("grrychshdefault");
	}
	
	/**
	 * 个人荣誉称号报表打印
	 * grrychPrint ---- 个人荣誉称号报表打印 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");
		String rychmc = service.getRychMc(request.getParameter("rychdm"));
		HashMap<String, String>  resMap = new HashMap<String, String>();
		resMap = service.getRychInfo(pkValue);//获取荣誉称号信息
		request.setAttribute("rs", resMap);
		if (!StringUtils.isNull(rychmc) && StringUtils.isEqual(rychmc, "优秀毕业生")) {//优秀毕业生报表打印
			return mapping.findForward("yxdxsprint");
		}
		//三好学生,文明大学生,三好学生标兵,优秀学生干部报表打印
		if (!StringUtils.isNull(rychmc)
				&& (StringUtils.isEqual(rychmc, "三好学生")
						|| StringUtils.isEqual(rychmc, "文明大学生")
						|| StringUtils.isEqual(rychmc, "三好学生标兵") || StringUtils
						.isEqual(rychmc, "优秀学生干部"))) {
			request.setAttribute("rychmc", rychmc);
			return mapping.findForward("wmshxsprint");
		}
		//其它报表打印
		request.setAttribute("rychmc", rychmc);
		return mapping.findForward("grrychprint");
	}
	
	/**
	 * 个人荣誉称号显示详细信息
	 * grrychview ---- 个人荣誉称号显示 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward grrychView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String userType        = session.getAttribute("userType").toString();//用户类型
		String isFdy 		   = session.getAttribute("isFdy").toString();//是否辅导员
		String pkValue = request.getParameter("pkValue");//主键
		String rychdm = request.getParameter("rychdm");
		HashMap<String, String> resMap = service.getRychshView(isFdy, userType, rychdm, pkValue);//获取详细信息
		List<HashMap<String, String>> shList = service.getChkList(3);//审核列表
		jgsdxForm.setYesNo(resMap.get("yesno"));
		request.setAttribute("rs", resMap);
		request.setAttribute("chkList", shList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rychdm", rychdm);
		return mapping.findForward("grrychview");
	}
	
	/**
	 * 个人荣誉称号审核审核
	 * grrychmodi ---- 个人荣誉称号审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward grrychModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String userType        = session.getAttribute("userType").toString();//用户类型
		String isFdy 		   = session.getAttribute("isFdy").toString();//是否辅导员
		String pkValue = request.getParameter("pkValue");
		String rychdm = request.getParameter("rychdm");
		String sh = DealString.toGBK(request.getParameter("yesNo"));
		String shyj = DealString.toGBK(request.getParameter("shyj"));
		GrrychModiModel grrychModel = new GrrychModiModel();//荣誉称号单个审核MODEL
		grrychModel.setPkValue(pkValue);
		grrychModel.setRychdm(rychdm);
		grrychModel.setSh(sh);
		grrychModel.setShyj(shyj);
		boolean bFlag = service.grrychModi(isFdy, userType, grrychModel, request);//单个审核
		List<HashMap<String, String>> shList = service.getChkList(3);//审核列表
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());//返回页面的空结果集
		request.setAttribute("chkList", shList);
		return mapping.findForward("grrychview");
	}

	/**
	 * 个人荣誉称号三级审核查询默认页面
	 * grrychsjDefault ---- 个人荣誉称号三级审核查询默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychsjDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
		//用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		} 
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		return mapping.findForward("grrychsjdefault");
	}
	
	/**
	 * 个人荣誉称号三级审核查询结果
	 * grrychsjQry ---- 个人荣誉称号三级审核查询结果 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychsjQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		HttpSession session = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//用户所在部门
		String userType        = session.getAttribute("userType").toString();//用户类型
		//用户是学院时
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			jgsdxForm.setXydm(xydm);
		}
		RychSjQryModel rychModel = new RychSjQryModel();
		BeanUtils.copyProperties(rychModel, jgsdxForm);
		List<HashMap<String, String>> topList = service.qryRychTitle();//查询表头
		List<String[]> resList = service.qryRychResult(rychModel);//查询结果
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//记录数
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		return mapping.findForward("grrychsjdefault");
	}
	
	/**
	 * 个人荣誉称号修改页面
	 * grrychModiView ---- 个人荣誉称号修改页面 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychModiView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");
		String type = request.getParameter("type");
		HashMap<String, String> resMap = service.rychInfoByPk(pkValue);//荣誉称号信息
		List<HashMap<String, String>> rychList = service.getRychList();//荣誉称号列表
		jgsdxForm.setXmdm(resMap.get("rychdm"));
		if (!StringUtils.isNull(type) && StringUtils.isEqual(type, "modi")) {
			request.setAttribute("isupdate", "yes");
		}
		request.setAttribute("rs", resMap);
		request.setAttribute("rychList", rychList);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("grrychmodiview");
	}
	
	/**
	 * 个人荣誉称号修改
	 * grrychModi ---- 个人荣誉称号修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychModijg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");
		String xh = request.getParameter("xh");
		String rychdm = request.getParameter("xmdm");
		String drzw = DealString.toGBK(request.getParameter("drzw"));
		List<HashMap<String, String>> rychList = service.getRychList();
		boolean bFlag = service.rychInfoModi(pkValue, rychdm, drzw, xh, request);//申请信息修改
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rychList", rychList);
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("grrychmodiview");
	}
	
	/**
	 * 个人荣誉称号删除
	 * grrychsqDel ---- 个人荣誉称号删除 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grrychsqDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String[] keys = jgsdxForm.getCbv();
		String sJg = service.rychInfoDel(keys, request);//批量删除
		request.setAttribute("result", sJg);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("tableName", "view_xsrychb");
		return mapping.findForward("grrychsjdefault");
	}
	
	/**
	 * 条件设置首页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjszDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String jxjdm = jgsdxForm.getJxjdm();
		if (!StringUtils.isNull(jxjdm)) {
			List<HashMap<String, String>> topList = service.getJxjsztjTitle();
			List<String[]> jxjsztjList = service.getJxjsztj(jgsdxForm.getXn(), jxjdm);
			String wjtj = service.getJxjTjsz(jgsdxForm.getXn(), jxjdm);//是否违纪
			request.setAttribute("rs", jxjsztjList);
			request.setAttribute("topTr", topList);
			jgsdxForm.setSfwj(wjtj);
		}
		List<HashMap<String, String>> zdList = service.getZdList();//字段列表
		request.setAttribute("zdList", zdList);
		jgsdxForm.setZdm("");
		request.setAttribute("jxjList", service.getJxjList1());//加载奖学金列表
		//commonAction.appendJxjList(request);//加载奖学金列表
		commonAction.appendProperties(request, xydm, zydm, nj);//加载LIST列表
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * 奖学金条件设置保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjtjszSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		String ysf = request.getParameter("ysf");
		if (StringUtils.isNull(ysf)) {
			ysf = "<=";
		}
		jgsdxForm.setYsf(ysf);
		PjpyJgsdxService service = new PjpyJgsdxService();
		JxjtjszSaveModel tjszModel = new JxjtjszSaveModel();
		BeanUtils.copyProperties(tjszModel, jgsdxForm);
		boolean bFlag = service.jxjsztjSave(tjszModel, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> zdList = service.getZdList();//字段列表
		request.setAttribute("zdList", zdList);
		//commonAction.appendJxjList(request);//加载奖学金列表
		request.setAttribute("jxjList", service.getJxjList1());//加载奖学金列表
		jgsdxForm.setZdm("");
		commonAction.appendProperties(request, xydm, zydm, nj);//加载LIST列表
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * 奖学金条件设置修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjszcjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");
		String jxjdm = request.getParameter("jxjdm");
		HashMap<String, String> tjMap = service.getTjInfo(pkValue, jxjdm);//获取单个信息
		if (tjMap != null) {
			String zdm = tjMap.get("tjzdm");
			String zdcz = tjMap.get("zdcz");
			String sfwj = tjMap.get("sfwj");
			String tj = tjMap.get("tj");
			zdm = service.getZdm(!StringUtils.isNull(zdm) ? zdm.trim() : "");
			//zdcz = service.getZdcz(!StringUtils.isNull(zdcz) ? zdcz.trim() : "");
			String[] tjValue = service.getTjValue(!StringUtils.isNull(tj) ? tj.trim() : "");
			tjMap.put("zdm", zdm);
			jgsdxForm.setZdcz(zdcz);
			jgsdxForm.setSfwj(sfwj);
			if (tjValue != null && tjValue.length == 2) {
				jgsdxForm.setYsf(service.getTj(tjValue[0]));
				jgsdxForm.setVal(tjValue[1]);
			}
		}
		request.setAttribute("rs", tjMap);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jxjsztjmodi");
	}
	
	/**
	 * 奖学金条件设置删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjszcjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		PjpyJgsdxService service = new PjpyJgsdxService();
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.jxjsztjDel(pkValue, request);//数据删除
		if (bFlag) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
		}
		List<HashMap<String, String>> zdList = service.getZdList();//字段列表
		request.setAttribute("zdList", zdList);
		//commonAction.appendJxjList(request);//加载奖学金列表
		request.setAttribute("jxjList", service.getJxjList1());//加载奖学金列表
		jgsdxForm.setZdm("");
		commonAction.appendProperties(request, xydm, zydm, nj);//加载LIST列表
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * 奖学金条件设置修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsztjEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyJgsdxActionForm jgsdxForm = (PjpyJgsdxActionForm) form;
		String ysf = request.getParameter("ysf");
		if (StringUtils.isNull(ysf)) {
			ysf = "<=";
		}
		jgsdxForm.setYsf(ysf);
		PjpyJgsdxService service = new PjpyJgsdxService();
		JxjtjszSaveModel tjszModel = new JxjtjszSaveModel();
		BeanUtils.copyProperties(tjszModel, jgsdxForm);
		String pkValue = request.getParameter("pkValue");
		boolean bFlag = service.tjEditResult(pkValue, tjszModel, request);//单个修改
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("jxjsztjmodi");
	}
}

