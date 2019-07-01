package xgxt.studentInfo.xscj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchForm;
import xgxt.comm.search.SearchTjByRoles;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

import common.Globals;
import common.GlobalsVariable;


/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 池州学院学生成绩维护Action
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: honglin
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2012-03-14
 * </p>
 */
public class XscjAction extends DispatchAction{
	private String xydm = "";//学院代码
	private String zydm = "";//专业代码
	private String nj = "";//年级
	private CommonAction comAct = null;
	/**
	 * 信息维护学生成绩查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XscjService service = new XscjService();
		XscjActionForm xscjForm = (XscjActionForm) form;
		//学生用户只能查询自己的学生成绩
		String uType = request.getSession().getAttribute("userType").toString();
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(uType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(uType)) {
			//TODO 暂时先用无权访问期该页面，有时间再把学生成绩查询做进去
			request.setAttribute("message", "您无权访问该页面!");
			return new ActionForward("/prompt.do",false);
		}
		if (StringUtils.isNull(xscjForm.getXn())) {
			xscjForm.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(xscjForm.getXq())) {
			xscjForm.setXq(Base.getJxjsqxq());
		}
		String xxdm = StandardOperation.getXxdm();
		String jwflag = service.getJwFlag();//查询教务版本,1为学年,0为过渡,否则为其他公司教务
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {//教务是过渡,通过WEBSERVICE同步学生成绩绩点
			return new ActionForward("/pjpy_xnmz_xscjwh.do", false);
		}
		if ("1".equalsIgnoreCase(jwflag)) {// 教务是学年
			return new ActionForward("/pjpy_zgms_cjwh.do", false);
		}
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
				if (Fdypd.isBzr(request.getSession().getAttribute("userName").toString(), "")) {
					userType = "bzr";
				}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm=request.getSession().getAttribute("userDep") != null ? request
					.getSession().getAttribute("userDep").toString()
					: "";
			xscjForm.setXydm(xydm);
		} else {
			xydm = xscjForm.getXydm();
		}
		zydm = xscjForm.getZydm();
		nj = xscjForm.getNj();
		String tableName = "";
		String realTable = "";
		if (!StringUtils.isNull(xscjForm.getCjlx())) {
			if ("cjb".equalsIgnoreCase(xscjForm.getCjlx())) {
				tableName = "view_cjb";
				realTable = "cjb";
			} else if ("djksb".equalsIgnoreCase(xscjForm.getCjlx())) {
				tableName = "view_xsdjksb";
				realTable = "xsdjksb";
				request.setAttribute("djksmcList", service.getDjksmc());
			}
		}
		
		if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {
			List<HashMap<String, String>> kcxzList = service.getKsxzList();
			if (kcxzList != null && kcxzList.size() > 0) {
				request.setAttribute("kcxzList", kcxzList);
			}
		}
		request.setAttribute("path", "xsxx_xscj.do");
		FormModleCommon.commonRequestSet(request);
		String title = "学生信息 - 学生信息 - 学生成绩维护";
		request.setAttribute("title", title);
		appendProperties(request, xydm, zydm, nj);//加载页面列表
		request.setAttribute("tableName", tableName);//查询多为视图名
		request.setAttribute("realTable", realTable);//表名
		return mapping.findForward("xscj");
	}
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
		xy = StringUtils.isNull(xy) ? "" : xy;
		zy = StringUtils.isNull(zy) ? "" : zy;
		nj = StringUtils.isNull(nj) ? "" : nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
//		String userType = request.getSession().getAttribute("userType").toString();
//		if (Fdypd.isBzr(request.getSession().getAttribute("userName").toString(), "")) {
//			userType = "bzr";
//		}
		request.setAttribute("writeAble", "yes");//判断用户读写权
		
		FormModleCommon.setNdXnXqList(request);
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
//		request.setAttribute("xqList", Base.getXqList());//学期列表
//		request.setAttribute("xnList", Base.getXnndList());//学年列表
//		request.setAttribute("njList", Base.getNjList());//年级列表
//		request.setAttribute("xyList", Base.getXyList());//学院列表
//		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//专业列表
//		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//班级列表
////		request.setAttribute("userType", userType);//用户类型
//		if (request.getSession().getAttribute("fdyQx").toString() != null
//				&& "true".equalsIgnoreCase(request.getSession().getAttribute("fdyQx")
//						.toString())) {
//			// 辅导员登录
//			request.setAttribute("bjList", Fdypd.getFdybjList(request.getSession().getAttribute("userName").toString()));// 发送班级列表
//			request.setAttribute("zyList", Fdypd.getFdyZyList(request.getSession().getAttribute("userName").toString()));// 发送班级列表
//		}
	}
	
	/**
	 * 信息维护学生成绩查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscjQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XscjActionForm xscjForm = (XscjActionForm) form;
		String xh = DealString.toGBK(xscjForm.getXh());
		String xm = DealString.toGBK(xscjForm.getXm());
		String kcmc=request.getParameter("kcmc");
		if(StringUtils.isNull(xscjForm.getXn())){
			xscjForm.setXn(Base.currXn);
		}
		if(StringUtils.isNull(xscjForm.getXq())){
			xscjForm.setXq(Base.currXq);
		}
		request.setAttribute("path", "xsxx_xscjQuery.do");
		XscjModel xscjModel = new XscjModel();//学生成绩查询MODEL
		List<String[]> listRs = new ArrayList<String[]>();//学生成绩查询结果
		List<HashMap<String, String>> listTopTr = new ArrayList<HashMap<String,String>>();//学生成绩查询表头
		XscjService service = new XscjService();
		User user=getUser(request);
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		String userName = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString(): "";
//		
//		if (Fdypd.isBzr(request.getSession().getAttribute("userName").toString(), "")) {
//				userType = "bzr";
//		}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm=request.getSession().getAttribute("userDep") != null ? request
					.getSession().getAttribute("userDep").toString()
					: "";
					xscjForm.setXydm(xydm);
		} else {
			xydm = xscjForm.getXydm();
		}
		BeanUtils.copyProperties(xscjModel, xscjForm);
		if (!StringUtils.isNull(xscjForm.getCjlx()) && StringUtils.isEqual(xscjForm.getCjlx(), "cjb")) {
			if (Globals.XXDM_YCSFXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(7);//传入7则输出学生成绩查询表头
			}else if (Globals.XXDM_AHZYJSXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(8);//传入7则输出学生成绩查询表头
			}else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(9);//传入7则输出学生成绩查询表头
			} else if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {// 广州大学
				listTopTr = service.getSearchTitle(10);
			} else {
				//listTopTr = service.getSearchTitle(1);// 传入1则输出学生成绩查询表头
				if(!Base.isNull(kcmc)){
					
					xscjForm.setKcmc(kcmc);
				}
				listTopTr = service.getToptr(xscjForm, user,"1");
			}
			
			//广东建设增加排名
			if(Globals.XXDM_GDJSZYJSXY.equalsIgnoreCase(Base.xxdm) && !Base.isNull(kcmc)){
				listRs = service.getXscjList(xscjModel, xscjForm, user,"1");
			}else{
				listRs = service.getXscjResult(xscjModel, xscjForm,user,"1");
			}	
				
			int count = service.getXscjResultNum(xscjModel,userType,userName);
			xscjForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		} else {
			//listTopTr = service.getSearchTitle(6);//传入6则输出学生等级考试查询表头
			listTopTr = service.getToptr(xscjForm, user,"2");
			listRs = service.getKscjResult(xscjModel, xscjForm,user,"2");
			int count = service.getKscjResultNum(xscjModel);
			xscjForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		}
		
		zydm = xscjForm.getZydm();
		nj = xscjForm.getNj();
		String tableName = "";
		String realTable = "";
		if (!StringUtils.isNull(xscjForm.getCjlx())) {
			if ("cjb".equalsIgnoreCase(xscjForm.getCjlx())) {
				tableName = "view_cjb";
				realTable = "cjb";
				if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {// 广州大学
					tableName = "view_gzdx_cjb";
				}
			} else if ("djksb".equalsIgnoreCase(xscjForm.getCjlx())) {
				tableName = "view_xsdjksb";
				realTable = "xsdjksb";
				request.setAttribute("djksmcList", service.getDjksmc());
			}
		}else{
			
			realTable="cjb";
		}
		
		if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {
			List<HashMap<String, String>> kcxzList = service.getKsxzList();
			if (kcxzList != null && kcxzList.size() > 0) {
				String[] kcxz = xscjModel.getKcxz();
				String checked_Kcxz = "";
				if (kcxz != null && kcxz.length > 0) {
					for (int i = 0; i < kcxz.length; i++) {
						checked_Kcxz += kcxz[i] + "!!@@!!";
					}
				}
				request.setAttribute("checked_Kcxz", checked_Kcxz);
				request.setAttribute("kcxzList", kcxzList);
			}
		}
		// -------------end --------------
		
		// ==================执行导出操作 ==================
		String doType = request.getParameter("doType");
		if ("exp".equalsIgnoreCase(doType)) {
			
			String[] outputColumn = new String[listTopTr.size()];
			String[] colListCN = new String[listTopTr.size()];
			List<String[]> list = new ArrayList<String[]>();
			// 配置好需要导出的字段
			for(int i=0; i<listTopTr.size(); i++){
				HashMap<String, String> map = listTopTr.get(i);
				outputColumn[i] = map.get("en");
				colListCN[i] = map.get("cn");
			}
			list = listRs;//导出结果
			
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
			//return mapping.findForward("");
		}
		// =================end ===================
		
		request.setAttribute("tableName", tableName);//查询多为视图名
		request.setAttribute("realTable", realTable);//表名
		appendProperties(request, xydm, zydm, nj);
		xscjForm.setXh(xh);
		xscjForm.setXm(xm);
		String title = "学生信息 - 学生信息 - 学生成绩维护";
		request.setAttribute("title", title);
		request.setAttribute("topTr", listTopTr);
		request.setAttribute("rs", listRs);
		request.setAttribute("rsNum", listRs != null ? listRs.size() : 0);
		request.setAttribute("kcmc", kcmc);
		return mapping.findForward("xscj");
	}
	
	/**
	 * 信息维护学生成绩导出页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscjExp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XscjActionForm xscjForm = (XscjActionForm) form;
		String xh = DealString.toGBK(xscjForm.getXh());
		String xm = DealString.toGBK(xscjForm.getXm());
		String kcmc=request.getParameter("kcmc");
		if(StringUtils.isNull(xscjForm.getXn())){
			xscjForm.setXn(Base.currXn);
		}
		if(StringUtils.isNull(xscjForm.getXq())){
			xscjForm.setXq(Base.currXq);
		}
		request.setAttribute("path", "xsxx_xscjQuery.do");
		XscjModel xscjModel = new XscjModel();//学生成绩查询MODEL
		List<String[]> listRs = new ArrayList<String[]>();//学生成绩查询结果
		List<HashMap<String, String>> listTopTr = new ArrayList<HashMap<String,String>>();//学生成绩查询表头
		XscjService service = new XscjService();
		User user=getUser(request);
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		String userName = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString(): "";
//		
//		if (Fdypd.isBzr(request.getSession().getAttribute("userName").toString(), "")) {
//				userType = "bzr";
//		}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm=request.getSession().getAttribute("userDep") != null ? request
					.getSession().getAttribute("userDep").toString()
					: "";
					xscjForm.setXydm(xydm);
		} else {
			xydm = xscjForm.getXydm();
		}
		BeanUtils.copyProperties(xscjModel, xscjForm);
		if (!StringUtils.isNull(xscjForm.getCjlx()) && StringUtils.isEqual(xscjForm.getCjlx(), "cjb")) {
			if (Globals.XXDM_YCSFXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(7);//传入7则输出学生成绩查询表头
			}else if (Globals.XXDM_AHZYJSXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(8);//传入7则输出学生成绩查询表头
			}else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(9);//传入7则输出学生成绩查询表头
			} else if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {// 广州大学
				listTopTr = service.getSearchTitle(10);
			} else {
				//listTopTr = service.getSearchTitle(1);// 传入1则输出学生成绩查询表头
				if(!Base.isNull(kcmc)){
					
					xscjForm.setKcmc(kcmc);
				}
				listTopTr = service.getToptr(xscjForm, user,"1");
			}
			
			//广东建设增加排名
			if(Globals.XXDM_GDJSZYJSXY.equalsIgnoreCase(Base.xxdm) && !Base.isNull(kcmc)){
				listRs = service.getXscjList(xscjModel, xscjForm, user,"1");
			}else{
				listRs = service.getXscjResult(xscjModel, xscjForm,user,"1");
			}	
				
			int count = service.getXscjResultNum(xscjModel,userType,userName);
			xscjForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		} else {
			//listTopTr = service.getSearchTitle(6);//传入6则输出学生等级考试查询表头
			listTopTr = service.getToptr(xscjForm, user,"2");
			listRs = service.getKscjResult(xscjModel, xscjForm,user,"2");
			int count = service.getKscjResultNum(xscjModel);
			xscjForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		}
		
		zydm = xscjForm.getZydm();
		nj = xscjForm.getNj();
		String tableName = "";
		String realTable = "";
		if (!StringUtils.isNull(xscjForm.getCjlx())) {
			if ("cjb".equalsIgnoreCase(xscjForm.getCjlx())) {
				tableName = "view_cjb";
				realTable = "cjb";
				if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {// 广州大学
					tableName = "view_gzdx_cjb";
				}
			} else if ("djksb".equalsIgnoreCase(xscjForm.getCjlx())) {
				tableName = "view_xsdjksb";
				realTable = "xsdjksb";
				request.setAttribute("djksmcList", service.getDjksmc());
			}
		}else{
			
			realTable="cjb";
		}
		
		if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {
			List<HashMap<String, String>> kcxzList = service.getKsxzList();
			if (kcxzList != null && kcxzList.size() > 0) {
				String[] kcxz = xscjModel.getKcxz();
				String checked_Kcxz = "";
				if (kcxz != null && kcxz.length > 0) {
					for (int i = 0; i < kcxz.length; i++) {
						checked_Kcxz += kcxz[i] + "!!@@!!";
					}
				}
				request.setAttribute("checked_Kcxz", checked_Kcxz);
				request.setAttribute("kcxzList", kcxzList);
			}
		}
		// -------------end --------------
		
		// ==================执行导出操作 ==================
		String doType = request.getParameter("doType");
			
			String[] outputColumn = new String[listTopTr.size()];
			String[] colListCN = new String[listTopTr.size()];
			List<String[]> list = new ArrayList<String[]>();
			// 配置好需要导出的字段
			for(int i=0; i<listTopTr.size(); i++){
				HashMap<String, String> map = listTopTr.get(i);
				outputColumn[i] = map.get("en");
				colListCN[i] = map.get("cn");
			}
			list = listRs;//导出结果
			
			
		// =================end ===================
		
		request.setAttribute("tableName", tableName);//查询多为视图名
		request.setAttribute("realTable", realTable);//表名
		appendProperties(request, xydm, zydm, nj);
		xscjForm.setXh(xh);
		xscjForm.setXm(xm);
		String title = "学生信息 - 学生信息 - 学生成绩维护";
		request.setAttribute("title", title);
		request.setAttribute("topTr", listTopTr);
		request.setAttribute("rs", listRs);
		request.setAttribute("rsNum", listRs != null ? listRs.size() : 0);
		request.setAttribute("kcmc", kcmc);
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * 列选
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kindChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XscjService service=new XscjService();
		XscjActionForm xscjForm=(XscjActionForm)form;
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		
		String doType=request.getParameter("doType");
		xscjForm.setPath("xsxx_xscjQuery.do");
		//学生成绩查询表头
		String cjlx = request.getParameter("cjlx");
		if (!StringUtils.isNull(cjlx) && StringUtils.isEqual(cjlx, "cjb")) {
			request.setAttribute("topTr", service.getKindChoose(xscjForm, user,"1"));
			String message = "";// 提示信息
			if("save".equalsIgnoreCase(doType)){
				boolean flag=service.saveKindChoose(xscjForm, user,"1");
				message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
						: MessageInfo.MESSAGE_SAVE_FALSE;
				rForm.setMessage(message);
			}
			//是否已经有列选设置
			request.setAttribute("cjlx",xscjForm.getCjlx());
			request.setAttribute("checkKind", service.getCheckKind(xscjForm, user,"1"));
			service.setRequestValue(rForm, request);
		}else{
			//学生等级考试查询表头
			request.setAttribute("topTr", service.getKindChoose(xscjForm, user,"2"));
			String message = "";// 提示信息
			if("save".equalsIgnoreCase(doType)){
				boolean flag=service.saveKindChoose(xscjForm, user,"2");
				message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
						: MessageInfo.MESSAGE_SAVE_FALSE;
				rForm.setMessage(message);
			}
			//是否已经有列选设置
			request.setAttribute("cjlx",xscjForm.getCjlx());
			request.setAttribute("checkKind", service.getCheckKind(xscjForm, user,"2"));
			service.setRequestValue(rForm, request);
		}
		
		return mapping.findForward("kindChoose");
	}
	/**
	 * 获取用户相关信息
	 * @param request
	 * @return User
	 * @throws Exception 
	 * */
	public User getUser(HttpServletRequest request){
		User user = new User();
		HttpSession session = request.getSession();
		String jyweb = session.getAttribute("jyweb") != null ? session.getAttribute("jyweb").toString() : "";
		
		if (!Base.isNull(jyweb) && "yes".equals(jyweb)) {
			user.setUserName((String) session.getAttribute("jyweb_userName"));
			user.setUserType((String) session.getAttribute("jyweb_userType"));
		} else {
			user.setUserName(session.getAttribute("userName").toString());
			user.setUserType(session.getAttribute("userType").toString());
			user.setIsFdy(session.getAttribute("isFdy").toString());
			user.setUserDep(session.getAttribute("userDep").toString());
			user.setUserMac((String) (session.getAttribute("userMac")) != null ? session.getAttribute("userMac").toString() : "");
			user.setRealName((String)(session.getAttribute("userNameReal") != null ? session.getAttribute("userNameReal").toString() :""));
			
			// ===========2011.3.16 edit by luojw===========
			// 用户类型
			String userType = session.getAttribute("userType") != null ? session.getAttribute("userType").toString() : "";
			// 辅导员权限
			String fdyQx = session.getAttribute("fdyQx") != null ? session.getAttribute("fdyQx").toString() : "";
			// 班主任权限
			String bzrQx = session.getAttribute("bzrQx") != null ? session.getAttribute("bzrQx").toString() : "";
			// 用户身份
			String userStatus = "";
			
			 // 使用用户角色
			String userRolesApply = user.getUserRolesApply();
			String userRoles = (String)(session.getAttribute("userRoles") != null ? session.getAttribute("userRoles").toString() : "");
			if(!Base.isNull(userRoles)){
				user.setUserRoles(userRoles.split("!!"));
			}
			
			if ("yes".equalsIgnoreCase(userRolesApply)) {
							
				String path = session.getAttribute("clickPath") != null ? session.getAttribute("clickPath").toString() : "";
				
				SearchTjByRoles rolesService = new SearchTjByRoles();
				
				SearchForm searchForm = new SearchForm();
				searchForm.setPath(path);
				
				// 用户角色
				List<HashMap<String, String>> userRolesList = null;
				
				try {
					userRolesList = rolesService.getUserGnmkRoles(searchForm, user);
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				// 用户身份
				userStatus = rolesService.getUserStatus(userRolesList, user);
				
			} else {
				if (Boolean.parseBoolean(bzrQx) && Boolean.parseBoolean(fdyQx)) {
					userStatus = "jd";// 班主任兼辅导员
				} else if (Boolean.parseBoolean(fdyQx)) {
					userStatus = "fdy";// 辅导员
				} else if (Boolean.parseBoolean(bzrQx)) {
					userStatus = "bzr";// 班主任
				} else if ("xy".equalsIgnoreCase(userType)) {
					userStatus = "xy";// 学院
				} else if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					userStatus = "xx";// 学校用户（管理员）
				} else {
					userStatus = "stu";// 学生
				}
			}

			String gyglyQx = session.getAttribute("gyglyQx") != null ? session.getAttribute("gyglyQx").toString() : "";
			
			user.setFdyQx(fdyQx);
			user.setBzrQx(bzrQx);
			user.setGyglyQx(gyglyQx);
			user.setUserStatus(userStatus);

			session.setAttribute("userStatus", userStatus);
		}
		
		user.setHost(request.getRemoteHost());
		user.setIp(request.getRemoteAddr());
				
		return user;
	}
	
	
	// ----------------------2012.4.9 qlj begin-------------------------
	/**
	 * 获取某学年、某学期xx班级课程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBjkcInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		XscjService service =new XscjService();
		XscjActionForm myForm=new XscjActionForm();
		
		// ------------------查询参数 begin-----------------------
		String xn=request.getParameter("xn");
		String xq=request.getParameter("xq");
		String bjdm=request.getParameter("bjdm");
		
		myForm.setXn(xn);
		myForm.setXq(xq);
		myForm.setBjdm(bjdm);
		// ------------------查询参数 end-----------------------
		
		response.setContentType("text/html;charset=gbk");

		List<HashMap<String,String>>bjkcInfo=service.getBjkcInfo(myForm);
		response.getWriter().print(JSONArray.fromObject(bjkcInfo));

		return null;
	}
	//----------------------2012.4.9 qlj end -------------------------
}
