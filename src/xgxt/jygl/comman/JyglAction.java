package xgxt.jygl.comman;

import java.io.File;
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
import xgxt.bdsz.BdszService;
import xgxt.comm.FileManage;
import xgxt.comm.xml.XMLReader;
import xgxt.form.User;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import common.Globals;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 就业管理Action</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 屈朋辉</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-13</p>
 */
public class JyglAction extends BasicAction {
	
	
	/**
	 * 双向选择就业推荐表补办申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjbBb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		JyglService service = new JyglService();
		
		String tabName = "jygl_xysbb";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		
		HashMap<String , String> rs = new HashMap<String, String>();
		
		if ("stu".equals(userType)) {
			rs = service.getJyxyStuInfo(userName);
			
			if (rs.isEmpty()) {
				rs = service.getStuInfo(userName);
				rs.put("xsxh", rs.get("xh"));
				rs.put("id", rs.get("sfzh"));
				rs.put("name", rs.get("xm"));
			}
			rs.putAll(service.getBysqx(userName));
			rs.put("fdy", service.getFdy(userName));
			
		} else {
			
			rs = service.getJyxyStuInfo(xh);
			
			if (rs.isEmpty()) {
				rs = service.getStuInfo(xh);
				rs.put("xsxh", rs.get("xh"));
				rs.put("id", rs.get("sfzh"));
				rs.put("name", rs.get("xm"));
			}
			rs.putAll(service.getBysqx(xh));
			rs.put("fdy", service.getFdy(xh));
			
			
		}
		
		
		request.setAttribute("xh", xh);
		
		//保存
		if ("save".equals(doType)) {
			rs.putAll((HashMap<String, String>)this.insertOperation(request, tabName));
		}
		
		service.setList(request, "tjb");
		request.setAttribute("path", "jygl_sqbb.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("rs", rs);
		return mapping.findForward("tjbBb");
	}
	
	
	/**
	 * 双向选择就业推荐表审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjbSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglService service = new JyglService();
		JyglForm myForm = new JyglForm();
		
		String tableName = "jygl_xysbb";
		String viewName="view_jygl_xysbb";
		String[] outputColumn = new String[] { "pkValue","xh", "xm", "nj", "xymc",
				"zymc", "bjmc", "sqsj", "bbyy", "sxcl", "xysbh","xysh" };
		String doType = request.getParameter("doType");
		
		/*权限控制*/
		if ("xy".equals(userType)) {
			request.setAttribute("shzd", "xysh");
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		} else if ("stu".equals(userType)) {
			request.setAttribute("annexTerm", " and xh= '"+userName+"'");
		}
		
		//查询
		
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		
		
		//批量审核
		if (!Base.isNull(doType) && "sh".equalsIgnoreCase(doType)) {
			this.auditingBatchOperation(request, tableName);
		}
		
		service.setList(request, "tjb");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "jygl_bbsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tjbSh");
	}
	
	
	/**
	 * 双向选择就业推荐表查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjbcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglService service = new JyglService();
		JyglForm myForm = (JyglForm) form;
		
		String tableName = "jygl_xysbb";
		String viewName="view_jygl_xysbb";
		String[] outputColumn = new String[] { "pkValue","xh", "xm", "nj", "xymc",
				"zymc", "bjmc", "sqsj", "bbyy", "sxcl", "xysbh","xysh" };
		String doType = request.getParameter("doType");
		
		/*权限控制*/
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		}  else if ("stu".equals(userType)) {
			myForm.setQuerylike_xh(userName);
			request.setAttribute("annexTerm", " and xh= '"+userName+"'");
		}
		
		//查询
		if (!Base.isNull(doType) && "query".equalsIgnoreCase(doType)) {

			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		//删除
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {

			this.deleteOperation(request, tableName);
		}
		
		//导出
		if (!Base.isNull(doType) && "expPageData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "tjb");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "jygl_bbcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tjbcx");
	}
	
	
	/**
	 * 双向选择就业推荐表单条维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward showTjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String tableName = "jygl_xysbb";
		String viewName="view_jygl_xysbb";
		String pkValue = request.getParameter("pk");
		
		JyglService service = new JyglService();
		String doType = request.getParameter("doType");
		
		//加载数据
		this.selectPageDataByOne(request, tableName, viewName, pkValue);
		
		HashMap<String , String> tjb = (HashMap<String, String>) request.getAttribute("rs");
		
		HashMap<String , String> rs = service.getJyxyStuInfo(tjb.get("xh"));
		
		rs.putAll(service.getBysqx(tjb.get("xh")));
		
		rs.put("xysbh", tjb.get("xysbh"));
		rs.put("bbyy", tjb.get("bbyy"));
		rs.put("sxcl", tjb.get("sxcl"));
		rs.put("xyyj", tjb.get("xyyj"));
		rs.put("fdy", service.getFdy(tjb.get("xh")));
		
		
		//单个审核
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			this.updateOperation(request, tableName);
		}
		
		
		service.setList(request, "tjb");
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "jygl_bbsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("showTjb");
	}

	
	/**
	 * 就业协议数据源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyglData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String tableName = "jygl_xsjbxxb";
		String doType = request.getParameter("doType");
		String[] outputColumn = new String[] { "xsxh", "nd", "bynd", "name",
				"xslb", "zymc", "sbsj" };
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, tableName, outputColumn);
		}
		
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("jyglData");
	}

	
	
	
	/**
	 * 毕业生上报管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward byssbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jy_bysxxb";
		String viewName = "view_jy_bysxxb";
		String[] colList = new String[] {"xh", "xm", "xb",
				"xz", "nj", "xymc","bjmc","sbr","sbsj"};
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		} else if ("stu".equals(userType)) {
			request.setAttribute("message", "对不起,您无权访问此页！");
			return new ActionForward("/prompt.do", false);
		}
		
		if ("query".equalsIgnoreCase(doType)) {
			request.setAttribute("rs", service.getByssbList(myForm));
			//selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("ysbrs", service.getYsbrsByNd(userName, Base.currNd));
		request.setAttribute("flg", service.getKgzt());
		request.setAttribute("path", "jygl_byssb.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		service.setList(request, "");
		return mapping.findForward("byssbManage");
	}
	
	
	

	/**
	 * 毕业生上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward byssb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		
		JyglService service = new JyglService();
		JyglForm myForm = (JyglForm) form;
		
		myForm.setUser(user);
		String tableName = "xsxxb";
		String viewName = "view_xsjbxx";
		String[] outputColumn = new String[] { "pkValue", "xh", "xm", "xb",
				"xz", "nj", "xymc", "zymc", "bjmc" };
		String doType = request.getParameter("doType");
		boolean result = false;
		
		
		
		//批量上报
		if (!Base.isNull(doType) && "save".equals(doType)) {
			String[] xh =request.getParameterValues("primarykey_cbv");
			
			if (null != xh && xh.length>0) {
				result = service.byssb(xh,user.getUserName(),Base.currNd);
			}
			request.setAttribute("message", result ? "上报成功" : "上报失败");
		}
		
		
		if ("query".equalsIgnoreCase(doType)) {
//			 -------学生信息视图修改后无法查询到数据 2012.1.10 qlj------------
			//selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
			request.setAttribute("rs", service.getXsxxList(myForm));
		}
		
		
		request.setAttribute("topTr", service.getColumn(viewName, outputColumn));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("byssb");
	}
	
	
	/**
	 * 毕业生维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bysxc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user = getUser(request);
		JyglForm model = (JyglForm) form;
		JyglService service = new JyglService();
		
		if ("stu".equals(user.getUserType())) {
			
			boolean isQrsj = service.getIsBysqrsj();
			request.setAttribute("isQrsj", isQrsj);
			request.setAttribute("cssz", service.getCssz());
			return new ActionForward("/jygl.do?method=bysUpdate&doType=update&pk="+user.getUserName(),false);
		}
		
		String tableName = "jy_bysxxb";
		String viewName = "view_jy_bysxxb";
		String[] outputColumn = null;
		String doType = request.getParameter("doType");
		
		if (Globals.XXDM_FJGCXY.equals(Base.xxdm)) {
			outputColumn = new String[] {  "pkValue","disabled", "xh", "xm",
					"xymc", "bjmc", "nj", "bynf", "xl", "sflxdk", "xsgb",
					"isjys", "shzt" };
		} else {
			outputColumn = new String[] {  "pkValue","disabled", "xh", 
					"xm","je","bjmc","sydq","syds","sydx","zzmmmc","sjhm",
					"sfqr","sfxg","xyshzt","shzt"};
		}
		
		//权限控制
		if ("false".equalsIgnoreCase(user.getIsFdy())
			     && "xy".equalsIgnoreCase(user.getUserType())){
			model.setQueryequals_xydm(user.getUserDep());
		}
		
		//删除
		if ("del".equalsIgnoreCase(doType)) {
			deleteOperation(request, tableName);
			deleteOperation(request, "jy_jyxy");//级联删除就业协议
			
			doType = "query";
		}
		
		if ("query".equalsIgnoreCase(doType)) {
			service.setCustomAudiColumn(request, "shzt");
			String clientColumns=(String)request.getAttribute("clientColumns");
			model.setClientColumns(clientColumns);
			request.setAttribute("rs",service.getByqrList(model, outputColumn));
			//selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		
		//导出指定字段数据
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			request.setAttribute("clientColumns"," (select qxmc||(select qxmc " +
					"from dmk_qx where qxdm=a.sydxian) from dmk_qx where qxdm = a.sydshi) 生源地,");
			String[] colList = new String[] { "xxmc", "xxdm", "xymc", "zymc",
					"zydm", "bjmc", "xm", "xh", "sfzh", "xbdm", "csrq", "mzdm",
					"zzmm", "生源地", "sydxian", "xlccdm", "xz", "sfzz", "sfsf",
					"sfdl", "zslbdm", "pyfsdm", "dxhwp", "rxnf", "bynf",
					"lxdh", "sjhm", "qq", "lxdz", "dzyx", "yzbh" };

			if (Globals.XXDM_FJGCXY.equals(Base.xxdm)) {
				colList = service.getColumn(viewName);
			}
			
			this.expPageData(request, response, tableName, viewName, colList);
			return mapping.findForward("");
		}
		
		request.setAttribute("topTr", service.getColumn(viewName, outputColumn));
		request.setAttribute("maxNum", model.getPages().getPageSize());
		service.setList(request, "jysb");
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "jygl_bysxc.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bysxc");
	}
	
	
	/**
	 * 毕业生单条维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward bysUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JyglService service = new JyglService();
		
		String tableName = "jy_bysxxb";
		String viewName = "view_jy_bysxxb";
		String pkValue = Base.isNull(request.getParameter("pk")) 
							? request.getParameter("pkValue") 
							: request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		//修改毕业生信息
		if (!Base.isNull(doType) && "save".equals(doType)) {
			updateOperation(request, tableName);
		}
		
		//加载数据
		selectPageDataByOne(request, tableName,viewName, pkValue);
		
		service.setList(request, "jysb");
		
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		
		//rs.put("ysyd", new CommService().getSydmc(rs.get("ysyd"), "/", "/"));
		
		String shzt = rs.get("shzt");
		String xyshzt = rs.get("xyshzt");
		//审核状态为通过或不通过的，学生用户不必再确认
		if ("通过".equals(shzt) || "不通过".equals(shzt)
		     || "通过".equals(xyshzt) || "不通过".equals(xyshzt)) {
			request.setAttribute("flg", "true");
		}
		
		request.setAttribute("path", "jygl_bysxc.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("sydsList", service.getShiList(rs.get("sydshen")));
		request.setAttribute("sydxList", service.getXianList(rs.get("sydshen")));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("bysUpdate");
	}
	
	
	
	
	/**
	 * 毕业生审核查询
	 */
	public ActionForward bysshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "jy_bysxxb";
		String viewName = "view_jy_bysxxb";
		
		User user = getUser(request);
		JyglForm model = (JyglForm) form;
		JyglService service = new JyglService();
		String[] outputColumn = null;
		
		if (Globals.XXDM_FJGCXY.equals(Base.xxdm)) {
			outputColumn = new String[] { "pkValue","disabled", "xh", "xm", "xymc",
					"bjmc", "nj", "bynf", "xl", "sflxdk", "xsgb","isjys", "shzt" };
		} else if (Globals.XXDM_ZJXY.equals(Base.xxdm)){
			outputColumn = new String[] { "sybdzt","pkValue","disabled", "xh", "xm","je","bjmc",
					"sydq","syds","sydx","zzmmmc","sjhm","sybdzt","sfxg","xyshzt","shzt"};
		} else {
			outputColumn = new String[] { "pkValue","disabled", "xh", "xm","je","bjmc",
					"sydq","syds","sydx","zzmmmc","sjhm","sfqr","sfxg","xyshzt","shzt"};
		}
		
		if ("false".equalsIgnoreCase(user.getIsFdy())
		     && "xy".equalsIgnoreCase(user.getUserType())){
			model.setQueryequals_xydm(user.getUserDep());
		}
		
		
		//查询 
//		if (QUERY.equalsIgnoreCase(model.getDoType())) {
//			service.setCustomAudiColumn(request, "byssh");
//			selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
//		}
		if (QUERY.equalsIgnoreCase(model.getDoType())) {
			service.setCustomAudiColumn(request, "byssh");
			String clientColumns=(String)request.getAttribute("clientColumns");
			model.setClientColumns(clientColumns);
			request.setAttribute("rs",service.getByqrList(model, outputColumn));
			//selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		service.setList(request, "jysb");
		request.setAttribute("isByssh", service.getIsBysshsj());//是否毕业生审核时间
		request.setAttribute("cssz", service.getCssz());//参数设置相关信息
		request.setAttribute("path", "jygl_byssh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("maxNum", model.getPages().getPageSize());
		request.setAttribute("topTr", service.getColumn(viewName, outputColumn));
		return mapping.findForward("byssh");
	}
	
	
	
	
	/**
	 * 毕业生源审核
	 */
	public ActionForward byssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user = getUser(request);
		String tableName = "jy_bysxxb";
		JyglForm model = (JyglForm) form;
		JyglService service = new JyglService();
		
		HashMap<String,String> valueMap = new HashMap<String, String>();
		HashMap<String,String> xqshMap = new HashMap<String, String>();
		
		//权限控制
		if ("xy".equals(user.getUserType())) {
			//model.setQueryequals_xydm(userDep);
			//学院审核的值 
			valueMap.put("xyshzt", model.getSave_shzt());
			valueMap.put("xyshr", user.getUserName());
			valueMap.put("xyshsj", service.getNow());
			valueMap.put("xyshyj", model.getSave_shyj());
			
			//学院取消审核的值
			xqshMap.put("xyshzt", "未审核");
			xqshMap.put("xyshr", "");
			xqshMap.put("xyshsj", "");
			xqshMap.put("xyshyj", "");
		} else if ("xx".equals(user.getUserType()) || "admin".equals(user.getUserType())) {
			//学校审核的值
			valueMap.put("shzt", model.getSave_shzt());
			valueMap.put("shr", user.getUserName());
			valueMap.put("shsj", service.getNow());
			valueMap.put("shyj", model.getSave_shyj());
			
			//学校取消审核的值
			xqshMap.put("shzt", "未审核");
			xqshMap.put("shr", "");
			xqshMap.put("shsj", "");
			xqshMap.put("shyj", "");
		} else {
			request.setAttribute("message","对不起，您无权访问此页!" );
			return new ActionForward("/prompt.do",false);
		}
		
		//批量审核
		if ("sh".equals(model.getDoType())) {
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
		}
		
		//取消审核
		if ("qxsh".equals(model.getDoType())) {
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), xqshMap, tableName);
		}
		
		model.setDoType(QUERY);
		return bysshcx(mapping, form, request, response);
	}
	

	/**
	 * 毕业生数据源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syxxData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String url = request.getParameter("url");
		String xh = request.getParameter("xh");
		String tableName = "jy_bysxxb";
		String[] colList = new String[] {"xh","nj","xm","xymc","zymc","bjmc"};
		String doType = request.getParameter("doType");
		
		//就业管理参数设置信息
		service.initCssz();
		
		HashMap<String, String> csszInfo = service.getCssz();
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		// 查询审核通过的毕业生信息
		if ("query".equalsIgnoreCase(doType)) {
			if (2 == Integer.valueOf(csszInfo.get("lcbh"))) {
				request.setAttribute("annexTerm"," and jyxybh is not null and lqqk='已领取' ");
				selectPageDataByPagination(request, form, "jygl_jyxyslqdjb",
											"view_jygl_jyxyslqdjb", colList);
			} else {
				request.setAttribute("annexTerm", " and shzt='通过' ");
				selectPageDataByPagination(request, form, tableName,tableName, colList);
			}
		}
		
		
		//返回指定的URL
		if (!Base.isNull(url)) {
			return new ActionForward(url+"&xh="+xh,false);
		}
		
		request.setAttribute("topTr", service.getColumn(tableName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("syxxData");
	}
	
	
	/**
	 * 就业协议维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyxywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		XsxxglService stuService = new XsxxglService();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		JyglForm model = (JyglForm) form;
		JyglService service = new JyglService();
		
		String pkValue = request.getParameter("xh");
		String tableName = "jy_jyxy";
		String viewName="view_jy_jyxy";
		String doType = request.getParameter("doType");
		
		//默认报到开始、结束时间
		String mrbdKssj = XMLReader.getFlowControl("jygl", "jyxy_mrbdkssj");
		String mrbdJssj = XMLReader.getFlowControl("jygl", "jyxy_mrbdjssj");
		model.setSave_bdkssj(Base.currNd+mrbdKssj);
		model.setSave_bdjssj(Base.currNd+mrbdJssj);
		
		//如果为学生用户加载自己的基本信息
		if ("stu".equals(userType)) {
			pkValue = userName;
			request.setAttribute("isBys", service.getIsBys(userName));
		}
		
		if (!Base.isNull(pkValue)) {
			//加载毕业生基本信息
			HashMap<String, String> jtInfo = stuService.getStuJtxx(pkValue);
			selectPageDataByOne(request, "jy_bysxxb", "view_jy_bysxxb", pkValue);
			HashMap<String,String>rs=(HashMap<String,String>)request.getAttribute("rs");
			rs.putAll(service.getJyxyKzx(pkValue));
			request.setAttribute("rs", rs);
			request.setAttribute("jtInfo", jtInfo);
		}
		
		//保存就业协议
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.insertOperation(request, tableName);
			this.selectPageDataByOne(request, tableName, viewName, request.getParameter("save_xh"));
		}
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("yrdwssList", stuDao.getSsList());	
		request.setAttribute("yrdwshiList", stuDao.getShiList("410000").get("shiList"));
		request.setAttribute("yrdwxianList",  stuDao.getXianList("410100"));
		
		request.setAttribute("bddqssList", stuDao.getSsList());	
		request.setAttribute("bddqshiList", stuDao.getShiList("410000").get("shiList"));
		request.setAttribute("bddqxianList",  stuDao.getXianList("410100"));
		
		service.setList(request, "jyxy");
		request.setAttribute("path", "jygl_jyxycx.do");
		FormModleCommon.commonRequestSet(request);	
		return mapping.findForward("jyxywh");
	}


	/**
	 * 就业协议审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyxysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User user=getUser(request);
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		String userName=user.getUserName();
		
		if (Globals.XXDM_FJGCXY.equals(Base.xxdm)) {
			return fjgcJyxysh(mapping, form, request, response);
		}
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		
		String tableName = "jy_jyxy";
		String viewName="view_jy_jyxy";
		String[] colList = new String[] { "disabled", "isdis", "pkValue", "xh",
				"xm", "xymc", "bjmc", "xz", "mzmc", "zzmmmc", "jybzmc",
				"dwxzmc", "jyhymc", "xysh","xyshr","xxsh","xxshr" };
		String doType = request.getParameter("doType");
		String shzt = request.getParameter("shzt");
		HashMap<String,String> valueMap = new HashMap<String, String>();
		HashMap<String,String> xqshMap = new HashMap<String, String>();
		
		
		//权限控制
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			//学院审核的值 
			valueMap.put("xysh", shzt);
			valueMap.put("xyshr", userName);
			valueMap.put("xyshsj", service.getNow());
			valueMap.put("xyshyj", myForm.getSave_shyj());
			
			//学院取消审核的值
			xqshMap.put("xysh", "未审核");
			xqshMap.put("xyshr", "");
			xqshMap.put("xyshsj", "");
			xqshMap.put("xyshyj", "");
			
			request.setAttribute("annexTerm", " and xydm='"+userDep+"'");
			
		} else if ("xx".equals(userType) || "admin".equals(userType)) {
			//学校审核的值
			valueMap.put("xxsh", shzt);
			valueMap.put("xxshr", userName);
			valueMap.put("xxshsj", service.getNow());
			valueMap.put("xxshyj", myForm.getSave_shyj());
			
			//学校取消审核的值
			xqshMap.put("xxsh", "未审核");
			xqshMap.put("xxshr", "");
			xqshMap.put("xxshsj", "");
			xqshMap.put("xxshyj", "");
			
			if ("1".equals(XMLReader.getFlowControl("jygl","jyxy"))) {
				request.setAttribute("annexTerm"," and xysh='通过' ");
			}
			
		} else {
			request.setAttribute("message","对不起，您无权访问此页!" );
			return new ActionForward("/prompt.do",false);
		}
		
		// 批量审核
		if ("sh".equals(doType)) {
			auditingBatchOperation(request, getValueArrayMap(request,PRIFIX_PRIMARY_KEY), valueMap, tableName);
			
			if ("xy".equals(userType) && "通过".equals(shzt)) {
				boolean result = service.resetXxsh(tableName, request.getParameterValues("primarykey_cbv"));
				request.setAttribute("message", result ? "操作成功" : "操作失败");
			}
			
			doType="query";
		}
		
		//取消审核
		if ("qxsh".equals(doType)) {
			auditingBatchOperation(request, getValueArrayMap(request,PRIFIX_PRIMARY_KEY), xqshMap, tableName);
			
			doType="query";
		}
		
		//查询
		if ("query".equals(doType)) {
			//service.setCustomAudiColumn(request, userType+"sh");
			//selectPageDataByPagination(request, myForm, tableName, viewName, colList);
			myForm.setUser(user);
			request.setAttribute("rs", service.getJyxyShList(myForm, colList));
		}
		
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		service.setList(request, "jyxy");
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("path", "jygl_jyxysh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jyxysh");
	}
	
	
	/**
	 * 就业协议管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyxycx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jy_jyxy";
		String viewName="view_jy_jyxy";
		String[] colList = null;
		String doType = request.getParameter("doType");
		
		if ("stu".equals(userType)) {
			selectPageDataByOne(request, tableName, viewName, userName);
			
			HashMap<String,String> rs = (HashMap<String, String>) request.getAttribute("rs");
			
			if (null == rs || rs.isEmpty()) {
				return new ActionForward("/jygl.do?method=jyxywh&pk="+userName,false);
			} else {
				return new ActionForward("/jygl.do?method=jyxyShow&pkValue="+userName,false);
			}
			
		}
		
		if (Globals.XXDM_FJGCXY.equals(Base.xxdm)) {
			colList = new String[] { "disabled", "pkValue", "xh",
					"xm", "xymc", "zymc", "bjmc", "xz", "mzmc", "zzmmmc",
					"jybzmc", "dwxzmc", "jyhymc", "sfdk", "xysh", "xxsh" };
		} else {
			colList = new String[] { "disabled", "pkValue", "xh",
					"xm", "xymc", "bjmc", "xz", "mzmc", "zzmmmc", "jybzmc",
					"dwxzmc", "jyhymc", "xysh","xyshr","xxsh","xxshr" };
		}
		
		//权限控制
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		}
		
		//删除
		if (!Base.isNull(doType) && "del".equals(doType)) {
			deleteOperation(request, tableName);
			
			doType="query";
		}
		
		//查询
		if ("query".equals(doType)) {
			//service.setCustomAudiColumn(request, "jgcx");
			//selectPageDataByPagination(request, form, tableName, viewName, colList);
			request.setAttribute("rs", service.getJyxycxList(myForm, colList));
		}
		
		
		//导出
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return new ActionForward("");
		}
		
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		service.setList(request, "jyxy");
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("path", "jygl_jyxycx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jyxycx");
	}

	
	/**
	 * 就业协议单条维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyxyShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JyglService service = new JyglService();
		
		String tableName = "jy_jyxy";
		String viewName="view_jy_jyxy";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		//修改、单个审核
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		
		//加载数据
		selectPageDataByOne(request, tableName, viewName, pkValue);
		
		
		HashMap<String,String> rs = (HashMap<String, String>) request.getAttribute("rs");
		
		//任何一级审核状态为通过或不通过的，学生用户不能再修改
		String shzt = rs.get("xysh");
		if ("通过".equals(shzt) || "不通过".equals(shzt)) {
			request.setAttribute("flg", "true");
		}
		
		service.setList(request, "jyxy");
		request.setAttribute("isBys", service.getIsBys(pkValue));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("now", service.getNow());
		
		if (!Base.isNull(doType) && ("view".equals(doType) || "sh".equals(doType))) {
			return new ActionForward("/jygl/jygl/ty_jyxyView.jsp",false);
		}
		
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("yrdwssList", stuDao.getSsList());	
		request.setAttribute("yrdwshiList", stuDao.getShiList("410000").get("shiList"));
		request.setAttribute("yrdwxianList",  stuDao.getXianList("410100"));
		
		request.setAttribute("bddqssList", stuDao.getSsList());	
		request.setAttribute("bddqshiList", stuDao.getShiList("410000").get("shiList"));
		request.setAttribute("bddqxianList",  stuDao.getXianList("410100"));
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jyxyShow");
	}


	/**
	 * 生源地分布情况统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sydtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JyglService service = new JyglService();
		
		String nj = request.getParameter("nj");
		
		if (Base.isNull(nj)) {
			nj = Base.currNd;
		}
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		service.printPayReport(response.getOutputStream(), nj);
		
		return mapping.findForward("");
	} 
	
	/**
	 * 按班级就业统计
	 * 
	 * @author honglin
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjjytj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JyglService service = new JyglService();
		
		String[] selNj = request.getParameterValues("selNj");
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		service.printBjjytj(response.getOutputStream(), selNj);
		
		return mapping.findForward("");
	} 
	/**
	 * 按专业就业统计
	 * 
	 * @author honglin
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zyjytj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JyglService service = new JyglService();
		
		String[] selNj = request.getParameterValues("selNj");
		String[] selJyXy = request.getParameterValues("selJyXy");
//		for (int i = 0 ; i < selNj.length ; i++){
//			System.out.println("selNj["+i+"]："+selNj[i]);
//		}
//		for (int i = 0 ; i < selJyXy.length ; i++){
//			System.out.println("selJyXy["+i+"]："+selJyXy[i]);
//		}
		
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		service.printZyjytj(response.getOutputStream(), selNj,selJyXy);
		
		return mapping.findForward("");
	} 
	/**
	 * 就业率统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyltj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		JyglService service = new JyglService();
		
		String xxdm = (String) session.getAttribute("xxdm");
		String nj = request.getParameter("nj");
		String[] selNj = request.getParameterValues("selNj");
		
		if (Base.isNull(nj)) {
			nj = Base.currNd;
		}
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
			service.zgddPrintJyltj(response.getOutputStream(),nj);
		} else {
			service.printJyltj(response.getOutputStream(),selNj);
		}
		
		
		return mapping.findForward("");
	} 

	
	/**
	 * 就业代码维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String tableName = "";
		String[] colList = null;
		String doType = request.getParameter("doType");
		String tname = request.getParameter("tname");
		
		if (Base.isNull(tname) || "zgdw".equals(tname)) {
			tname="zgdw";
			tableName = "dmk_zgdw";
			colList = new String[] {"pkValue","dm","mc","bddq"}; 
		} else {
			tname="yrdw";
			tableName = "dmk_yrdw";
			colList = new String[] {"pkValue","dm","mc","dwszd"}; 
		}
		
		if (!Base.isNull(doType) &&"query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, tableName, colList);
		}
		
		if (!Base.isNull(doType) &&"del".equals(doType)) {
			this.deleteOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) &&"expData".equals(doType)) {
			this.expPageData(request, response, tableName, tableName, colList);
			return mapping.findForward("");
		}
		
		request.setAttribute("path", "jygl_dmwh.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("realTable", tableName);
		request.setAttribute("tname", tname);
		return mapping.findForward("dmwh");
	}
	
	
	/**
	 * 主管单位维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zgdwUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String tableName = "dmk_zgdw";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		this.selectPageDataByOne(request, tableName, tableName, pkValue);
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.insertOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "modi".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("zgdwUpdate");
	}
	
	
	/**
	 * 用人单位维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yrdwUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JyglService service = new JyglService();
		
		String tableName = "dmk_yrdw";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		this.selectPageDataByOne(request, tableName, tableName, pkValue);
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.insertOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "modi".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		service.setList(request, "yrdwwh");
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("yrdwUpdate");
	}

	
	
	/**
	 * 地质大学毕业生查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zgddByscx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		String userDep = (String) session.getAttribute("userDep");
		
		if ("stu".equals(userType)) {
			return new ActionForward("/jygl.do?method=zgddBysUpdate&doType=update&pkValue="+userName,false);
		}
		
		String tableName = "jy_bysxxb";
		String viewName = "view_jy_zgdd_bysxxb";
		String[] outputColumn = new String[] { "disabled", "pkValue", "xh",
				"xm", "nj", "xymc", "bynf", "sfqr", "shzt", "shr" };
		String doType = request.getParameter("doType");
		
		//权限控制
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm='"+userDep+"'");
		} 
		
		//删除
		if (!Base.isNull(doType) && "del".equals(doType)) {
			deleteOperation(request, tableName);
			deleteOperation(request, "jy_jyxy");//级联删除就业协议
			
			doType = "query";
		}
		
		//查询
		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, "shzt");
			selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		//导出指定字段数据
		if (!Base.isNull(doType) && "expData".equals(doType)) {

			this.expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "zgdd");
		request.setAttribute("topTr", service.getColumn(viewName, outputColumn));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_zgdd_bysxc.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zgddByscx");
	}
	
	
	/**
	 * 地质大学毕业生审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zgddByssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");

		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jy_bysxxb";
		String viewName = "view_jy_zgdd_bysxxb";
		String[] outputColumn = new String[] { "disabled", "pkValue", "xh",
				"xm", "nj", "xymc", "bynf", "xl", "sfqr", "shzt","shr" };
		String doType = request.getParameter("doType");
		
		//只有学校用户可以审核毕业生信息
		if (!"xx".equals(userType) && !"admin".equals(userType)) {
			request.setAttribute("message", "对不起,您无权访问此页！");
			return new ActionForward("/prompt.do",false);
		}
		
		//批量审核
		if ("sh".equals(doType)) {
			
			HashMap<String, String> valueMap = new HashMap<String, String>();
			
			valueMap.put("shzt", myForm.getSave_shzt());
			valueMap.put("shr", userName);
			valueMap.put("shsj", service.getNow());
			valueMap.put("shyj", myForm.getSave_shyj());
			
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
			
			doType = "query";
		}
		
		//取消审核
		if ("qxsh".equals(doType)) {
			
			HashMap<String, String> valueMap = new HashMap<String, String>();
			
			valueMap.put("shzt", "未审核");
			valueMap.put("shr", "");
			valueMap.put("shsj", "");
			valueMap.put("shyj", "");
			
			auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
			
			doType = "query";
		}
		
		//查询 
		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, "shzt");
			selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		service.setList(request, "zgdd");
		request.setAttribute("topTr", service.getColumn(viewName, outputColumn));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("path", "jygl_zgdd_byssh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zgddByssh");
	}


	/**
	 * 地质大学毕业生生源信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward zgddBysUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JyglService service = new JyglService();
		
		String tableName = "jy_bysxxb";
		String viewName = "view_jy_zgdd_bysxxb";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		//修改毕业生信息
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.updateOperation(request, tableName);
		}
		
		//加载数据
		this.selectPageDataByOne(request, tableName,viewName, pkValue);
		
		service.setList(request, "zgdd");
		
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		request.setAttribute("sydsList", service.getShiList(rs.get("sydshen")));
		request.setAttribute("sydxList", service.getXianList(rs.get("sydshen")));
		request.setAttribute("now",service.getNow());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		return mapping.findForward("zgddBysUpdate");
	}


	/**
	 * 地大就业协议维护
	 * @return
	 * @throws Exception
	 */
	public ActionForward zgddJyxywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		JyglService service = new JyglService();
		
		String pkValue = request.getParameter("xh");
		String tableName = "jy_jyxy";
		String viewName="view_jy_zgdd_jyxy";
		String doType = request.getParameter("doType");
		
		//如果为学生用户加载自己的基本信息
		if ("stu".equals(userType)) {
			pkValue = userName;
			request.setAttribute("isBys", service.getIsBys(userName));
		}
		
		//加载毕业生基本信息
		if (!Base.isNull(pkValue)) {
			this.selectPageDataByOne(request, "jy_bysxxb", "view_jy_zgdd_bysxxb", pkValue);
			HashMap<String, String> map = new XsxxglService().getStuJtxx(pkValue);
			
			request.setAttribute("map", map);
		}
		
		//保存就业协议
		if (!Base.isNull(doType) && "save".equals(doType)) {
			this.insertOperation(request, tableName);
			this.selectPageDataByOne(request, tableName, viewName, request.getParameter("save_xh"));
		}
		
		
		service.setList(request, "zgddJyxy");
		request.setAttribute("path", "jygl_zgdd_jyxycx.do");
		FormModleCommon.commonRequestSet(request);	
		return mapping.findForward("zgddJyxywh");
	}

	
	/**
	 * 地大就业协议管理
	 * @return
	 * @throws Exception
	 */
	public ActionForward zgddJyxycx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jy_jyxy";
		String viewName="view_jy_zgdd_jyxy";
		String[] colList = new String[] { "disabled", "pkValue", "xh", "xm",
				"nj", "xymc", "zymc", "bjmc", "jybzmc", "xysh", "xyshr",
				"xxsh", "xxshr" };
		String doType = request.getParameter("doType");
		
		if ("stu".equals(userType)) {
			selectPageDataByOne(request, tableName, viewName, userName);
			
			if (((HashMap<String,String>)request.getAttribute("rs")).isEmpty()) {
				return new ActionForward("/jygl.do?method=zgddJyxywh&xh="+userName,false);
			} else {
				return new ActionForward("/jygl.do?method=zgddJyxyShow&pkValue="+userName,false);
			}
			
		}
		
		
		//权限控制
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		} 
		
		//删除
		if (!Base.isNull(doType) && "del".equals(doType)) {
			this.deleteOperation(request, tableName);
			
			doType = "query";
		}
		
		//查询
		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, "jgcx");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		//导出
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			this.expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return new ActionForward("");
		}
		
		service.setList(request, "zgddJyxy");
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_zgdd_jyxycx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zgddJyxycx");
	}
	
	
	/**
	 * 地大就业协议审核
	 * @return
	 * @throws Exception
	 */
	public ActionForward zgddJyxysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jy_jyxy";
		String viewName="view_jy_zgdd_jyxy";
		String[] colList = new String[] { "disabled", "isdis", "pkValue", "xh",
				"xm", "nj", "xymc", "zymc", "bjmc", "jybzmc", "xysh",
				"xyshr", "xxsh", "xxshr" };
		String doType = request.getParameter("doType");
		String shzt = request.getParameter("shzt");
		HashMap<String,String> valueMap = new HashMap<String, String>();
		HashMap<String,String> xqshMap = new HashMap<String, String>();
		
		//权限控制
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			//学院审核的值 
			valueMap.put("xysh", shzt);
			valueMap.put("xyshr", userName);
			valueMap.put("xyshsj", service.getNow());
			valueMap.put("xyshyj", myForm.getSave_shyj());
			
			//学院取消审核的值
			xqshMap.put("xysh", "未审核");
			xqshMap.put("xyshr", "");
			xqshMap.put("xyshsj", "");
			xqshMap.put("xyshyj", "");
			
			request.setAttribute("annexTerm", " and xydm='"+userDep+"'");
			
		} else if ("xx".equals(userType) || "admin".equals(userType)) {
			
			//学校审核的值
			valueMap.put("xxsh", shzt);
			valueMap.put("xxshr", userName);
			valueMap.put("xxshsj", service.getNow());
			valueMap.put("xxshyj", myForm.getSave_shyj());
			
			//学校取消审核的值
			xqshMap.put("xxsh", "未审核");
			xqshMap.put("xxshr", "");
			xqshMap.put("xxshsj", "");
			xqshMap.put("xxshyj", "");
			
			if ("1".equals(XMLReader.getFlowControl("jygl","jyxy"))) {
				request.setAttribute("annexTerm"," and xysh='通过' ");
			}
			
		} else {
			request.setAttribute("message","对不起，您无权访问此页!" );
			return new ActionForward("/prompt.do",false);
		}
		
		
		// 批量审核
		if (!Base.isNull(doType) && "sh".equals(doType)) {
			auditingBatchOperation(request, getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY), valueMap, tableName);
			
			if ("xy".equals(userType) && "通过".equals(shzt)) {
				boolean result = service.resetXxsh(tableName, request.getParameterValues("primarykey_cbv"));
				request.setAttribute("message", result ? "操作成功" : "操作失败");
			}
			
			doType = "query";
		}
		
		//取消审核
		if ("qxsh".equals(doType)) {
			auditingBatchOperation(request, getValueArrayMap(request,PRIFIX_PRIMARY_KEY), xqshMap, tableName);
			
			doType = "query";
		}
		
		//查询
		if ("query".equals(doType)) {
			service.setCustomAudiColumn(request, userType+"sh");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		service.setList(request, "zgddJyxy");
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("path", "jygl_zgdd_jyxysh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zgddJyxysh");
	}


	/**
	 * 地大就业协议单条维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zgddJyxyShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//学生登录进method
		JyglService service = new JyglService();
		
		String tableName = "jy_jyxy";
		String viewName="view_jy_zgdd_jyxy";
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		//修改、单个审核
		if (!Base.isNull(doType) && "save".equals(doType)) {
			updateOperation(request, tableName);
		}
		
		//加载数据
		selectPageDataByOne(request, tableName, viewName, pkValue);
		HashMap<String, String> rs = (HashMap<String, String>) request.getAttribute("rs");
		String shzt = rs.get("xysh");
		if ("通过".equals(shzt) || "不通过".equals(shzt)) {
			request.setAttribute("flg", "true");
		}
		
		HashMap<String, String> map = new XsxxglService().getStuJtxx(pkValue);
		
		request.setAttribute("map", map);
		request.setAttribute("isBys", service.getIsBys(pkValue));
		service.setList(request, "zgddJyxy");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("now", service.getNow());
		
		if (!Base.isNull(doType) && ("view".equals(doType) || "sh".equals(doType))) {
			return new ActionForward("/jygl/zgdd/zgddJyxyView.jsp",false);
		}
		
		return mapping.findForward("zgddJyxyShow");
	}
	
	
	
	/**
	 * 福建工程就业协议审核
	 * @return
	 * @throws Exception
	 */
	public ActionForward fjgcJyxysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		boolean idFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jy_jyxy";
		String viewName="view_jy_jyxy";
		String[] colList = null;
		String doType = request.getParameter("doType");
		String shjg = request.getParameter("shjg");
		HashMap<String,String> valueMap = new HashMap<String, String>();
		
		//权限控制
		if (idFdy) {
			myForm.setQueryequals_xydm(userDep);
			
			colList = new String[] { "disabled", "bgcolor", "pkValue", "xh",
					"xm", "xymc", "zymc", "bjmc", "xz", "mzmc", "zzmmmc",
					"jybzmc", "dwxzmc", "jyhymc", "sfdk", "xysh","xxsh"};
			valueMap.put("xysh", shjg);
			valueMap.put("xyshr", userName);
			valueMap.put("xyshsj", service.getNow());
		} else if ("xx".equals(userType) || "admin".equals(userType)) {
			colList = new String[] { "disabled", "bgcolor", "pkValue", "xh",
					"xm", "xymc", "zymc", "bjmc", "xz", "mzmc", "zzmmmc",
					"jybzmc", "dwxzmc", "jyhymc", "sfdk", "xxsh"};
			valueMap.put("xxsh", shjg);
			valueMap.put("xxshr", userName);
			valueMap.put("xxshsj", service.getNow());
		} else {
			request.setAttribute("errMsg","对不起，您无权访问此页!" );
			return new ActionForward("/errMsg.do",false);
		}
		
		//查询
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request, userType);
			this.selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		// 批量审核
		if (!Base.isNull(doType) && "sh".equals(doType)) {
			this.auditingBatchOperation(request, getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY), valueMap, tableName);
		}

		service.setList(request, "jyxy");
		request.setAttribute("path", "jygl_jyxysh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jyxysh");
	}


	
	/**
	 * 福建工程就业率统计
	 * @return
	 * @throws Exception
	 */
	public ActionForward printJyl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JyglService service = new JyglService();
		JyglForm model = (JyglForm) form;
		String flg = request.getParameter("flg");
		
		if (Base.isNull(model.getNf())) {
			model.setNf( Base.currNd);
		}
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		service.updateXmbz(model.getTjxmdm(), model.getXmbz());
		
		if ("xy".equals(flg)) {//分院就业率统计表
			service.printJylForXy(model, response.getOutputStream());
		} else if("zy".equals(flg)) {//专业就业率统计表
			service.printJylForZy(model, response.getOutputStream());
		} else if ("bj".equals(flg)) {//班级就业率统计表
			service.printJylForBj(model, response.getOutputStream());
		} else if ("hz".equals(flg)) {//就业汇总统计表
			service.printJylHz(model, response.getOutputStream());
		} else if ("bks".equals(flg)) {//本科生就业统计表
			service.printJylForBks(model,response.getOutputStream());
		} else if ("zks".equals(flg)) {//专科生就业统计表
			service.printJylForZks(model, response.getOutputStream());
		} else if ("fdymx".equals(flg)) {//辅导员带班就业明细表
			service.printFdyJymx(model, response.getOutputStream());
		} else if ("dwlb".equals(flg)) {//单位类别统计表
			service.printDwlbTj(model, response.getOutputStream());
		} else if ("hyfl".equals(flg)) {//行业分类分布情况表
			service.printHyflTj(model, response.getOutputStream());
		} else {
			throw new Exception("别急，这个还没做！");
		}
		
		return mapping.findForward("");
	}
	

	/**
	 * 就业协议书管理
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyslqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		JyglService service = new JyglService();
		JyglForm myForm = (JyglForm) form;
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String tableName = "jygl_jyxysgl";
		String viewName = "view_jygl_jyxysgl";
		String doType = request.getParameter("doType");
		String[] colList = new String[] { "pkValue", "xh", "xm", "nj", "xymc",
				"zymc", "bjmc", "xysbh", "sflq", "lqsj" };
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			deleteOperation(request, tableName);
		}
		
		selectPageDataByPagination(request, form, tableName, viewName, colList);
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_xyslq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xyslqManage");
	}
	
	
	/**
	 * 就业协议书领取单条维护
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyslqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		JyglService service = new JyglService();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String tableName = "jygl_jyxysgl";
		String viewName = "view_jygl_jyxysgl";
		String xh = request.getParameter("xh");
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		if ("stu".equals(userType)) {
			xh = userName;
		}
		
		if (!Base.isNull(xh)) {
			selectPageDataByOne(request, "jy_jyxy", "view_jy_jyxy", xh);
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			insertOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			updateOperation(request, tableName);
		}
		
		service.setList(request, "");
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "jygl_xyslq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xyslqUpdate");
	}
	
	
	/**
	 * 已签订就业协议学生
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyxyData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "jy_jyxy";
		String viewName = "view_jy_jyxy";
		String url = request.getParameter("url");
		String xh = request.getParameter("xh");
		String[] colList = new String[] {"xh","nj","xm","xymc","zymc","bjmc"};
		String doType = request.getParameter("doType");
		JyglForm myForm = (JyglForm) form;
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		//查询审核通过的毕业生信息
		if (!Base.isNull(doType) && "query".equals(doType)) {
			this.selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		//返回指定的URL
		if (!Base.isNull(url)) {
			return new ActionForward(url+"&xh="+xh,false);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("jyxyData");
	}

	
	/**
	 * 就业协议书补办申请
	 * @return
	 * @throws Exception
	 */
	public ActionForward xysbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		JyglService service = new JyglService();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String tableName = "jygl_jyxysbb";
		String viewName ="view_jygl_jyxysbb";
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		
		if ("stu".equals(userType)) {
			xh = userName;
		}
		
		if (!Base.isNull(xh)) {
			selectPageDataByOne(request, "jy_jyxy", "view_jy_jyxy", xh);
		}
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			insertOperation(request, tableName);
			pkValue = request.getParameter("save_xh")+request.getParameter("save_sqsj");
		}
		
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			updateOperation(request, tableName);
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		service.setList(request, "xysgl");
		request.setAttribute("now", service.getNow());
		request.setAttribute("doType", doType);
		request.setAttribute("path", "jygl_xysbb.do");
		FormModleCommon.commonRequestSet(request);
		
		if ("sh".equals(doType)) {
			return new ActionForward("/jygl/jygl/xysbbView.jsp",false);
		}
		return mapping.findForward("xysbbsq");
	}
	
	
	/**
	 * 协议书补办审核
	 * @return
	 * @throws Exception
	 */
	public ActionForward xysbbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		JyglService service = new JyglService();
		JyglForm myForm = (JyglForm) form;
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "jygl_jyxysbb";
		String viewName = "view_jygl_jyxysbb";
		String[] colList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc","xysbh","xxysbh","" };
		String doType = request.getParameter("doType");
		String shjg = request.getParameter("shjg");
		HashMap<String,String> valueMap = new HashMap<String, String>();
		
		if ("xy".equals(userType)) {
			colList[colList.length-1] = "xysh";
			valueMap.put("xysh", shjg);
			valueMap.put("xyshsj", service.getNow());
			myForm.setQueryequals_xydm(userDep);
		} else if ("xx".equals(userType) || "admin".equals(userType)) {
			colList[colList.length-1] = "xxsh";
			valueMap.put("xxsh", shjg);
			valueMap.put("xxshsj", service.getNow());
		} else {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/errMsg.do",false);
		}
		
		if (!Base.isNull(doType) && "sh".equals(doType)) {
			this.auditingBatchOperation(request, getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY), valueMap, tableName);
			doType = "query";
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request, userType);
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "");
		request.setAttribute("path", "jygl_xysbbsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xysbbsh");
	}
	
	
	
	/**
	 * 协议书补办结果查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward xysbbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		JyglService service = new JyglService();
		JyglForm myForm = (JyglForm) form;
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String tableName = "jygl_jyxysbb";
		String viewName = "view_jygl_jyxysbb";
		String[] colList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc","xysbh","xxysbh","xysh","xxsh" };
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			deleteOperation(request, tableName);
			doType = "query";
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request, userType);
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_xysbbjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xysbbjg");
	}

	
	/**
	 * 改派管理
	 * @return
	 * @throws Exception
	 */
	public ActionForward jygp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm model = (JyglForm) form;
		JyglService service = new JyglService();
		BdszService bdszService = new BdszService();
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		boolean result = false;
		List<HashMap<String, String>> topTr = null;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String realTable = "jygl_jygp";
		String tableName = "view_jygl_jygp";
		String pk = "xh||gpsj";
		String[] colList = new String[] { "pk", "xh", "xm", "nj", "xymc",
				"zymc", "bjmc", "ydwmc", "gpdwmc" };
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			model.setXydm(userDep);
		} else if ("stu".equals(userType)) {
			model.setXh(userName);
		}
		
		//批量删除所选数据
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = request.getParameterValues("primarykey_cbv");

			if (null != checkVal && checkVal.length > 0) {

				result = bdszService.delData(realTable, checkVal);

				if(result) {
					request.setAttribute("message", "操作成功！");
				} else {
					request.setAttribute("message", "操作失败！");
				}
			}
		}
		
		
		//查询
		if (!Base.isNull(doType) &&"query".equals(doType)) {
			
			/*List<HashMap<String, String>> zdyColList = bdszService.zdyColList(realTable);
			String[] zdyCol = new String[zdyColList.size()];
			String[] zdyColCN = new String[zdyColList.size()];
			
			for (int i = 0; i < zdyColList.size(); i++) {
				zdyCol[i] = zdyColList.get(i).get("zdid");
				zdyColCN[i] = zdyColList.get(i).get("zdmc");
			}*/
			
			HashMap<String,String[]> zdyCol = new BdszService().getZdyCol(realTable);
			
			String[] zdyZdEn = zdyCol.get("zdyZdEn");
			String[] zdyZdCn = zdyCol.get("zdyZdCn");
			
			topTr = bdszService.getZdyTopTr(tableName, colList, zdyZdEn, zdyZdCn);
			rs = service.getDataList(tableName, model, colList, zdyZdEn,
					realTable, new String[] { pk});
			
		}
		
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		service.setList(request, "gp");
		request.setAttribute("path", "jygl_gp.do");
		FormModleCommon.commonRequestSet(request, tableName,realTable , rs, topTr);
		return mapping.findForward("jygp");
	}
	
	
	/**
	 * 改派维护
	 * @return
	 * @throws Exception
	 */
	public ActionForward jygpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		BdszService bdszService = new BdszService();
		
		boolean result = false;
		String realTable = "jygl_jygp";
		String tableName = "view_jygl_jygp";
		String pk = "xh||gpsj";
		
		String xh = request.getParameter("xh");
		String pkV = request.getParameter("pk");
		String doType = request.getParameter("doType");
		String[] colList = new String[] { "xh", "ydw", "ydwszd", "gpdw",
				"dwszd", "gpsj", "gpr", "gpyy" };

		if (!Base.isNull(xh)) {
			request.setAttribute("rs", service.getJyxyStuInfo(xh));
		}
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			String pkValue = myForm.getXh()+myForm.getGpsj();
			result = bdszService.saveData(realTable, pkValue, myForm, request);
			if(result) {
				request.setAttribute("message", "操作成功！");
			} else {
				request.setAttribute("message", "操作失败！");
			}
		}
		
		//修改
		if (!Base.isNull(doType) && "modify".equalsIgnoreCase(doType)) {
			String pkValue = myForm.getXh()+myForm.getGpsj();
			result = service.updateData(realTable,pk,myForm, pkV,pkValue,colList, request);
			
			if(result) {
				request.setAttribute("message", "操作成功！");
			} else {
				request.setAttribute("message", "操作失败！");
			}
		}
		
		
		//加载数据
		if (!Base.isNull(doType) && "view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {
			
			HashMap<String, String> rs = bdszService.getOneData(tableName, realTable, pkV);
			request.setAttribute("rs", rs);
		}
		
		bdszService.getBdZd(realTable, "jygl", myForm);
		service.setList(request, "gp");
		request.setAttribute("pk", pkV);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "jygl_gp.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jygpUpdate");
	}
	
	
	/**
	 * 二次分配管理
	 * @return
	 * @throws Exception
	 */
	public ActionForward ecfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm model = (JyglForm) form;
		JyglService service = new JyglService();
		BdszService bdszService = new BdszService();
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		boolean result = false;
		List<HashMap<String, String>> topTr = null;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String realTable = "jygl_ecfp";
		String tableName = "view_jygl_ecfp";
		String pk = "xh||ecfpsj";
		String[] colList = new String[] { "pk", "xh", "xm", "nj", "xymc",
				"zymc", "bjmc", "ydwmc", "ecfpdwmc" };
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			model.setXydm(userDep);
		} else if ("stu".equals(userType)) {
			model.setXh(userName);
		}
		
		//批量删除所选数据
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = request.getParameterValues("primarykey_cbv");

			if (null != checkVal && checkVal.length > 0) {

				result = bdszService.delData(realTable, checkVal);

				if(result) {
					request.setAttribute("message", "操作成功！");
				} else {
					request.setAttribute("message", "操作失败！");
				}
			}
		}
		
		
		//查询
		if (!Base.isNull(doType) &&"query".equals(doType)) {
			
			/*List<HashMap<String, String>> zdyColList = bdszService.zdyColList(realTable);
			String[] zdyCol = new String[zdyColList.size()];
			String[] zdyColCN = new String[zdyColList.size()];
			
			for (int i = 0; i < zdyColList.size(); i++) {
				zdyCol[i] = zdyColList.get(i).get("zdid");
				zdyColCN[i] = zdyColList.get(i).get("zdmc");
			}*/
			HashMap<String,String[]> zdyCol = new BdszService().getZdyCol(realTable);
			
			String[] zdyZdEn = zdyCol.get("zdyZdEn");
			String[] zdyZdCn = zdyCol.get("zdyZdCn");
			
			topTr = bdszService.getZdyTopTr(tableName, colList, zdyZdEn, zdyZdCn);
			rs = service.getDataList(tableName, model, colList, zdyZdEn,
					realTable, new String[] { pk});
			
		}
		
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		service.setList(request, "gp");
		request.setAttribute("path", "jygl_ecfp.do");
		FormModleCommon.commonRequestSet(request, tableName,realTable , rs, topTr);
		return mapping.findForward("ecfpManage");
	}
	
	
	/**
	 * 二次分配维护
	 * @return
	 * @throws Exception
	 */
	public ActionForward ecfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		BdszService bdszService = new BdszService();
		
		boolean result = false;
		String realTable = "jygl_ecfp";
		String tableName = "view_jygl_ecfp";
		String pk = "xh||ecfpsj";
		
		String xh = request.getParameter("xh");
		String pkV = request.getParameter("pk");
		String doType = request.getParameter("doType");
		String[] colList = new String[] { "xh", "ydw", "ydwszd", "ecfpdw",
				"ecfpdwszd", "ecfpsj", "ecfpr", "ecfpyy" };

		if (!Base.isNull(xh)) {
			request.setAttribute("rs", service.getJyxyStuInfo(xh));
		}
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			String pkValue = myForm.getXh()+myForm.getEcfpsj();
			result = bdszService.saveData(realTable, pkValue, myForm, request);
			if(result) {
				request.setAttribute("message", "操作成功！");
			} else {
				request.setAttribute("message", "操作失败！");
			}
		}
		
		//修改
		if (!Base.isNull(doType) && "modify".equalsIgnoreCase(doType)) {
			String pkValue = myForm.getXh()+myForm.getEcfpsj();
			result = service.updateData(realTable,pk,myForm, pkV,pkValue,colList, request);
			
			if(result) {
				request.setAttribute("message", "操作成功！");
			} else {
				request.setAttribute("message", "操作失败！");
			}
		}
		
		
		//加载数据
		if (!Base.isNull(doType) && "view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {
			
			HashMap<String, String> rs = bdszService.getOneData(tableName, realTable,  pkV);
			request.setAttribute("rs", rs);
		}
		
		bdszService.getBdZd(realTable, "jygl", myForm);
		service.setList(request, "gp");
		request.setAttribute("pk", pkV);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "jygl_gp.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ecfpUpdate");
	}


	/**
	 * 就业推荐表登记
	 * @return
	 * @throws Exception
	 */
	public ActionForward jytjbdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		String tableName = "jygl_jytjb";
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		
		if ("stu".equals(userType)) {
			xh = userName;
		}
		
		if (!Base.isNull(xh)) {
			selectPageDataByOne(request, "jy_bysxxb", "view_jy_bysxxb", xh);
		}
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			insertOperation(request, tableName);
			pkValue = request.getParameter("save_xh");
		}
		
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			updateOperation(request, tableName);
			pkValue = request.getParameter("save_xh");
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, tableName,pkValue );
		}
		
		service.setList(request, "");
		request.setAttribute("doType", doType);
		request.setAttribute("path", "jygl_jytjbdj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jytjbdj");
	}
	
	
	/**
	 * 就业推荐表审核
	 * @return
	 * @throws Exception
	 */
	public ActionForward jytjbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String tableName = "jygl_jytjb";
		String viewName = "view_jygl_jytjb";
		String doType = request.getParameter("doType");
		String[] colList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "nj", "xymc", "zymc", "xysh"};
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("shzd", "xysh");
		} else if ("xx".equals(userType) || "admin".equals(userType)){
			colList[colList.length-1] = "xxsh";
			request.setAttribute("shzd", "xxsh");
		} else {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/errMsg.do",false);
		}
		
		
		if (!Base.isNull(doType) && "sh".equals(doType)) {
			auditingBatchOperation(request, tableName);
			doType = "query";
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request, userType);
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "");
		request.setAttribute("path", "jygl_jytjbsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jytjbsh");
	}
	
	
	/**
	 * 就业推荐表结果查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward jytjbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String tableName = "jygl_jytjb";
		String viewName = "view_jygl_jytjb";
		String doType = request.getParameter("doType");
		String[] colList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "nj", "xymc", "zymc", "xysh", "xxsh" };
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request, userType);
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			deleteOperation(request, tableName);
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_jytjbjg.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jytjbjg");
	}
	
	
	/**
	 * 就业推荐表打印
	 * @return
	 * @throws Exception
	 */
	public ActionForward pritnJytjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "jygl_jytjb";
		String viewName = "view_jygl_jytjb";
		String pkValue = request.getParameter("pkValue");
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		request.setAttribute("nd", Base.currNd);
		return mapping.findForward("pritnJytjb");
	}


	/**
	 * 档案户口上报
	 * @return
	 * @throws Exception
	 */
	public ActionForward dawksb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String tableName = "jy_bysxxb";
		String viewName = "view_jy_bysxxb";
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String[] colList = new String[] { "pkValue", "xh", "xm", "nj", "xymc",
				"zymc", "bjmc" };
		String[] pkValues = request.getParameterValues("primarykey_cbv");
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		//档案户口批量上报
		if (!Base.isNull(doType) && "save".equals(doType)) {
			boolean result = service.dahkPlsb(pkValues);
			request.setAttribute("result", result);
			doType = "query";
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			request.setAttribute("annexTerm", " and not exists (select 1 from jygl_dahkb where xh = a.xh) ");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "");	
		request.setAttribute("path", "jygl_dawksb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dawksb");
	}

	
	/**
	 * 档案户口审核
	 * @return
	 * @throws Exception
	 */
	public ActionForward dawksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_dahkb";
		String viewName ="view_jygl_dahkb";
		String userType = (String) session.getAttribute("userType");
		String[] colList = new String[] { "pkValue", "xh", "xm", "nj", "xymc",
				"zymc", "bjmc", "sbsj", "xxsh" };
		String doType = request.getParameter("doType");
		
		if (!"xx".equals(userType) && !"admin".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/errMsg.do",false);
		}
		
		if (!Base.isNull(doType) && "sh".equals(doType)) {
			request.setAttribute("shzd", "xxsh");
			auditingBatchOperation(request, tableName);
			doType = "query";
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "");
		request.setAttribute("path", "jygl_dawksh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dawksh");
	}
	
	
	/**
	 * 档案户口查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward dawkcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_dahkb";
		String viewName ="view_jygl_dahkb";
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String[] colList = new String[] { "pkValue", "xh", "xm", "nj", "xymc",
				"zymc", "bjmc", "sbsj", "xxsh" };
		String doType = request.getParameter("doType");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			deleteOperation(request, tableName);
			doType = "query";
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		request.setAttribute("realTable", tableName);
		service.setList(request,"");
		request.setAttribute("path", "jygl_dawkcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dawkcx");
	}
	
	
	/**
	 * 档案户口维护
	 * @return
	 * @throws Exception
	 */
	public ActionForward dahkUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_dahkb";
		String viewName ="view_jygl_dahkb";
		String pkValue = request.getParameter("pk");
		String doType = request.getParameter("doType");
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			updateOperation(request, tableName);
		}
		
		service.setList(request, "");
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("dahkUpdate");
	}
	
	
	
	/**
	 * 毕业生风采录入
	 * @return
	 * @throws Exception
	 */
	public ActionForward fclr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_bysfc";
		String viewName = "view_jygl_bysfc";
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		if ("stu".equals(userType)) {
			xh = userName;
		}
		
		if (!Base.isNull(xh)) {
			selectPageDataByOne(request, "jy_bysxxb", "view_jy_bysxxb", xh);
		}
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			insertOperation(request, tableName);
			pkValue = request.getParameter("save_xh");
			//文件上传
			HashMap<String,String> map = FileManage.fileUpload(myForm.getFile(), "/upload", 10);
			
			if (!Base.isNull(map.get("errMsg"))) {
				request.setAttribute("errMsg", map.get("errMsg"));
			} else if(!map.isEmpty()){
				FileManage.saveFileInfo(tableName, "fileName", "filePath", "xh", pkValue, map);
			}
		}
		
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			updateOperation(request, tableName);
			pkValue = request.getParameter("save_xh");
			//文件上传
			HashMap<String,String> map = FileManage.fileUpload(myForm.getFile(), "/upload", 10);
			
			if (!Base.isNull(map.get("errMsg"))) {
				request.setAttribute("errMsg", map.get("errMsg"));
			} else if(!map.isEmpty()){
				FileManage.saveFileInfo(tableName, "fileName", "filePath", "xh", pkValue,pkValue, map);
			}
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName,pkValue );
		}
		
		service.setList(request, "fc");
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "jygl_fclr.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fclr");
	}
	
	
	/**
	 * 毕业生风采审核
	 * @return
	 * @throws Exception
	 */
	public ActionForward fcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_bysfc";
		String viewName = "view_jygl_bysfc";
		String[] colList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "nj", "xymc", "zymc", "bjmc", "fclxmc", "xysh" };
		String doType = request.getParameter("doType");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("shzd", "xysh");
		} else if ("xx".equals(userType) || "admin".equals(userType)){
			colList[colList.length-1] = "xxsh";
			request.setAttribute("shzd", "xxsh");
		} else {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/errMsg.do",false);
		}
		
		if (!Base.isNull(doType) && "sh".equals(doType)) {
			auditingBatchOperation(request, tableName);
			doType = "query";
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request, userType);
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "fc");
		request.setAttribute("path", "jygl_fcsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fcsh");
	}
	
	
	/**
	 * 毕业生风采查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward fccx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_bysfc";
		String viewName = "view_jygl_bysfc";
		String[] colList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "nj", "xymc", "zymc", "bjmc", "fclxmc", "xysh","xxsh" };
		String doType = request.getParameter("doType");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			
			String[] pkValue = request.getParameterValues("primarykey_cbv");
			boolean result = FileManage.bathDelFiles(tableName, "xh", pkValue, "filepath");
			request.setAttribute("message", result ? "操作成功!" : "操作失败!");
			doType = "query";
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request, userType);
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
		}
		
		service.setList(request, "fc");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_fccx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fccx");
	}
	
	
	/**
	 * 风采录打印
	 * @return
	 * @throws Exception
	 */
	public ActionForward printFcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "jygl_bysfc";
		String viewName = "view_jygl_bysfc";
		String pkValue = request.getParameter("pkValue");
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		request.setAttribute("nd", Base.currNd);
		return mapping.findForward("printFcl");
	}
	

	
	/**
	 * 优秀毕业生申报
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbyssb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_yxbys";
		String viewName = "view_jygl_yxbys";
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		if ("stu".equals(userType)) {
			xh = userName;
		}
		
		if (!Base.isNull(xh)) {
			request.setAttribute("zcfMap",service.getZcfPm(xh, Base.currXn, "") );
			selectPageDataByOne(request, "jy_bysxxb", "view_jy_bysxxb", xh);
		}
		
		if (!Base.isNull(doType) && "save".equals(doType)) {
			insertOperation(request, tableName);
			pkValue = request.getParameter("save_xh");
		}
		
		if (!Base.isNull(doType) && "modify".equals(doType)) {
			updateOperation(request, tableName);
			pkValue = request.getParameter("save_xh");
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		service.setList(request, "yxbys");	
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "jygl_yxbyssb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yxbyssb");
	}
	
	
	
	/**
	 * 优秀毕业生审核
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbyssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_yxbys";
		String viewName = "view_jygl_yxbys";
		String[] colList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "nj", "xymc", "zymc", "bjmc", "sqlb","xysh" };
		String doType = request.getParameter("doType");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("shzd", "xysh");
		} else if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("shzd", "xxsh");
			colList[colList.length-1] = "xxsh";
		} else {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/errMsg.do",false);
		}
		
		if (!Base.isNull(doType) && "sh".equals(doType)) {
			auditingBatchOperation(request, tableName);
			doType = "query";
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request, userType);
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "yxbys");
		request.setAttribute("path", "jygl_yxbyssh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yxbyssh");
	}
	
	
	
	/**
	 * 优秀毕业生查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxbyscx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		HttpSession session = request.getSession();
		
		String tableName = "jygl_yxbys";
		String viewName = "view_jygl_yxbys";
		String[] colList = new String[] { "disabled", "bgcolor", "pkValue",
				"xh", "xm", "nj", "xymc", "zymc", "bjmc","sqlb", "xysh", "xxsh" };
		String doType = request.getParameter("doType");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		if (!Base.isNull(doType) && "del".equals(doType)) {
			deleteOperation(request, tableName);
			doType = "query";
		}
		
		if (!Base.isNull(doType) && "query".equals(doType)) {
			service.getCustomAudiColumn(request, userType);
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if (!Base.isNull(doType) && "expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "yxbys");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_yxbyscx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yxbyscx");
	}


	
	public ActionForward printYxbys(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JyglForm model = (JyglForm) form;
		JyglService service = new JyglService();
		
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		//TODO 未完成
		service.printYxbys(model, response.getOutputStream());
		return mapping.findForward("");
	}
	
	
	
	
	/**
	 * 删除附件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String filePath = request.getParameter("filepath");
		String tableName = request.getParameter("tableName");
		String url = request.getParameter("url");
		String doType = request.getParameter("doType");
		String pk = request.getParameter("pk");
		String pkValue = request.getParameter("pkValue");

		boolean result = false;
		
		File f = new File(filePath);
		// 判断附件是否存在，若存在则删除
		if (f.exists()) {
			f.delete();
		} 
		
		result = FileManage.delFileInfo(tableName, "fileName", "filePath", pk, pkValue);
		request.setAttribute("message", result ? "操作成功!" : "操作失败!");
		
		return new ActionForward("/jygl.do?method=" + url + "&doType="
				+ doType+"&pk="+pkValue, false);
	}
	
	
	/**
	 * 毕业生风采附件下载 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downLoadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String tableName = "jygl_bysfc";
		String pkKey = "xh";
		String pkValue = request.getParameter("pkValue");
		String filePath = DealString.toGBK(request.getParameter("filePath"));
		String fileName = DealString.toGBK(request.getParameter("fileName"));
		
		if (FileManage.downLoadFile(filePath, fileName, response)) {
			return null;
		} else {
			request.setAttribute("errMsg", "文件不存在或者已删除!");
			FileManage.delFileInfo(tableName, "fileName", "filePath", pkKey, pkValue);
			return new ActionForward("/errMsg.do",false);
		}
		
	}
	
	
	/**
	 * 毕业生数据源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward compayData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String url = request.getParameter("url");
		String dm = request.getParameter("dm");
		String tableName = "dmk_yrdw";
		String viewName = "view_dmk_yrdw";
		String[] colList = new String[] {"dm","mc","dwszdmc","dwdh","dwxz","hyfl","dwlxr","dwyb"};
		String flg = request.getParameter("flg");
		String doType = request.getParameter("doType");
		
		//查询用人单位
		if ("query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		//返回指定的URL
		if (!Base.isNull(url)) {
			return new ActionForward(url+"&dm="+dm,false);
		}
		
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		service.setList(request, "jyxy");
		request.setAttribute("flg", flg);
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		return mapping.findForward("compayData");
	}
	
	
	/**
	 * 弹出窗口的单位维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward companyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglService service = new JyglService();
		
		String tableName = "dmk_yrdw";
		String doType = request.getParameter("doType");
		
		if ("save".equals(doType)) {
			insertOperation(request, tableName);
		}
		
		service.setList(request, "jyxy");
		return mapping.findForward("companyUpdate");
	}
	

	/**
	 * 就业相关统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyxgtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglService service = new JyglService();
		
		service.setList(request, "jytj");
		request.setAttribute("jyxyList",service.setXyList(Base.xxdm));
		request.setAttribute("path", "jyxgtj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jyxgtj");
	}
	
	
	
	/**
	 * 就业实践基地上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jysjjdsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_jysjjd";
		String viewName = "view_jygl_jysjjd";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		
		if ("save".equals(doType)) {
			insertOperation(request, tableName);
			
			pkValue = request.getParameter("save_bmdm")+request.getParameter("save_jtmc");
		}
		
		if ("modify".equals(doType)) {
			updateOperation(request, tableName);
			
			pkValue = request.getParameter("save_bmdm")+request.getParameter("save_jtmc");
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		service.setList(request, "");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", "save".equals(doType) ? null : doType);
		request.setAttribute("path", "jygl_jysjjdsb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jysjjdsb");
	}
	
	
	
	/**
	 * 就业实践基地审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jysjjdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_jysjjd";
		String viewName = "view_jygl_jysjjd";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","bmmc","jtmc",
									"qysj","qtr","sfba","qyrs","shzt"};
		
		if (!"xx".equals(userType) && !"admin".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		if ("sh".equals(doType)) {
			request.setAttribute("shzd", "shzt");
			auditingBatchOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request, "");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "");
		request.setAttribute("path", "jygl_jysjjdsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jysjjdsh");
	}
	
	
	
	/**
	 * 就业实践基地查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jysjjdcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jygl_jysjjd";
		String viewName = "view_jygl_jysjjd";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","bmmc","jtmc",
				"qysj","qtr","sfba","qyrs","shzt"};
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_bmdm(userDep);
		}
		
		
		if ("del".equals(doType)) {
			deleteOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request, "");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if ("expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_jysjjdcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jysjjdcx");
	}



	/**
	 * 就业工作招聘会上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jygzzphsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_jygzzph";
		String viewName = "view_jygl_jygzzph";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		
		if ("save".equals(doType)) {
			insertOperation(request, tableName);
			pkValue = new StringBuilder()
					.append(request.getParameter("save_bmdm"))
					.append(request.getParameter("save_sj"))
					.append(request.getParameter("save_dd"))
					.append(request.getParameter("save_chdw"))
					.append(request.getParameter("save_xqgw"))
					.toString();
		}
		
		if ("modify".equals(doType)) {
			updateOperation(request, tableName);
			pkValue = new StringBuilder()
					.append(request.getParameter("save_bmdm"))
					.append(request.getParameter("save_sj"))
					.append(request.getParameter("save_dd"))
					.append(request.getParameter("save_chdw"))
					.append(request.getParameter("save_xqgw"))
					.toString();
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		service.setList(request, "jygzzph"); 
		request.setAttribute("doType", "save".equals(doType) ? null : doType);
		request.setAttribute("path", "jygl_jygzzphsb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jygzzphsb");
	}


	
	
	/**
	 * 就业工作招聘会审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jygzzphsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_jygzzph";
		String viewName = "view_jygl_jygzzph";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","bmmc","sj",
						"chdw","xqgw","qyfl","zphlx","shzt"};
		
		if (!"xx".equals(userType) && !"admin".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		if ("sh".equals(doType)) {
			request.setAttribute("shzd", "shzt");
			auditingBatchOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request, "");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "jygzzph");
		request.setAttribute("path", "jygl_jygzzphsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jygzzphsh");
	}
	
	
	
	/**
	 * 就业招聘会结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jygzzphcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jygl_jygzzph";
		String viewName = "view_jygl_jygzzph";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","bmmc","sj",
						"chdw","xqgw","qyfl","zphlx","shzt"};
		

		if ("xy".equals(userType)) {
			myForm.setQueryequals_bmdm(userDep);
		}
		
		if ("del".equals(doType)) {
			deleteOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request, "");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if ("expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "jygzzph");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_jygzzphcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jygzzphcx");
	}
	
	
	
	/**
	 * 就业工作会议上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jygzhysb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_jygzhy";
		String viewName = "view_jygl_jygzhy";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		
		if ("save".equals(doType)) {
			insertOperation(request, tableName);
			pkValue = new StringBuilder()
					.append(request.getParameter("save_bmdm"))
					.append(request.getParameter("save_sj"))
					.append(request.getParameter("save_dd"))
					.append(request.getParameter("save_hylxdm"))
					.toString();
		}
		
		if ("modify".equals(doType)) {
			updateOperation(request, tableName);
			pkValue = new StringBuilder()
					.append(request.getParameter("save_bmdm"))
					.append(request.getParameter("save_sj"))
					.append(request.getParameter("save_dd"))
					.append(request.getParameter("save_hylxdm"))
					.toString();
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		service.setList(request, "jygzhy"); 
		request.setAttribute("doType", "save".equals(doType) ? null : doType);
		request.setAttribute("path", "jygl_jygzhysb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jygzhysb");
	}


	
	
	/**
	 * 就业工作会议审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jygzhysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_jygzhy";
		String viewName = "view_jygl_jygzhy";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","bmmc","sj",
						"hylx","hyzt","zcr","shzt"};
		
		if (!"xx".equals(userType) && !"admin".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		if ("sh".equals(doType)) {
			request.setAttribute("shzd", "shzt");
			auditingBatchOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request,"");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "jygzhy");
		request.setAttribute("path", "jygl_jygzhysh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jygzhysh");
	}
	
	
	
	/**
	 * 就业工作会议结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jygzhycx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jygl_jygzhy";
		String viewName = "view_jygl_jygzhy";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","bmmc","sj",
						"hylx","hyzt","zcr","shzt"};
		

		if ("xy".equals(userType)) {
			myForm.setQueryequals_bmdm(userDep);
		}
		
		if ("del".equals(doType)) {
			deleteOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request, "");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if ("expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "jygzhy");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_jygzhycx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jygzhycx");
	}
	
	
	
	/**
	 * 就业指导讲座上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyzdjzsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_jyzdjz";
		String viewName = "view_jygl_jyzdjz";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		
		if ("save".equals(doType)) {
			insertOperation(request, tableName);
			pkValue = new StringBuilder()
					.append(request.getParameter("save_bmdm"))
					.append(request.getParameter("save_sj"))
					.append(request.getParameter("save_dd"))
					.append(request.getParameter("save_jzzd"))
					.toString();
		}
		
		if ("modify".equals(doType)) {
			updateOperation(request, tableName);
			pkValue = new StringBuilder()
					.append(request.getParameter("save_bmdm"))
					.append(request.getParameter("save_sj"))
					.append(request.getParameter("save_dd"))
					.append(request.getParameter("save_jzzd"))
					.toString();
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		service.setList(request, ""); 
		request.setAttribute("doType", "save".equals(doType) ? null : doType);
		request.setAttribute("path", "jygl_jyzdjzsb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jyzdjzsb");
	}

	
	
	/**
	 * 就业指导讲座审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyzdjzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_jyzdjz";
		String viewName = "view_jygl_jyzdjz";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","bmmc","sj",
						"jzzd","zcr","mxdx","shzt"};
		
		if (!"xx".equals(userType) && !"admin".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		if ("sh".equals(doType)) {
			request.setAttribute("shzd", "shzt");
			auditingBatchOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request,"");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "");
		request.setAttribute("path", "jygl_jyzdjzsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jyzdjzsh");
	}
	
	
	
	/**
	 * 就业指导讲座结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyzdjzcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jygl_jyzdjz";
		String viewName = "view_jygl_jyzdjz";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","bmmc","sj",
						"jzzd","zcr","mxdx","shzt"};
		

		if ("xy".equals(userType)) {
			myForm.setQueryequals_bmdm(userDep);
		}
		
		if ("del".equals(doType)) {
			deleteOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request, "");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if ("expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_jyzdjzcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jyzdjzcx");
	}
	
	
	
	/**
	 * 就业双师制上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jysszsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_jygzssz";
		String viewName = "view_jygl_jygzssz";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		String now = service.getNow();
		
		if ("save".equals(doType)) {
			insertOperation(request, tableName);
			pkValue = new StringBuilder()
					.append(request.getParameter("save_xydm"))
					.append(request.getParameter("save_btjxs"))
					.append(request.getParameter("save_tjdwmc"))
					.append(now)
					.toString();
		}
		
		if ("modify".equals(doType)) {
			updateOperation(request, tableName);
			pkValue = new StringBuilder()
					.append(request.getParameter("save_xydm"))
					.append(request.getParameter("save_btjxs"))
					.append(request.getParameter("save_tjdwmc"))
					.append(now)
					.toString();
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		service.setList(request, "jyssz"); 
		request.setAttribute("doType", "save".equals(doType) ? null : doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "jygl_jysszsb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jysszsb");
	}

	
	
	/**
	 * 就业双师制审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jysszsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_jygzssz";
		String viewName = "view_jygl_jygzssz";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","xymc","cyjs",
						"jsfl","btjxs","tjdwmc","tjjg","shzt"};
		
		if (!"xx".equals(userType) && !"admin".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		if ("sh".equals(doType)) {
			request.setAttribute("shzd", "shzt");
			auditingBatchOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request,"");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "jyssz");
		request.setAttribute("path", "jygl_jysszsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jysszsh");
	}
	
	
	
	/**
	 * 就业双师制结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jysszcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jygl_jygzssz";
		String viewName = "view_jygl_jygzssz";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","xymc","cyjs",
				"jsfl","btjxs","tjdwmc","tjjg","shzt"};
		

		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		if ("del".equals(doType)) {
			deleteOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request, "");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if ("expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "jyssz");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_jysszcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jysszcx");
	}
	
	
	
	/**
	 * 企业信息上报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qyxxsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		if ("stu".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_qyxxb";
		String viewName = "view_jygl_qyxxb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pk");
		
		if ("save".equals(doType)) {
			insertOperation(request, tableName);
			pkValue = new StringBuilder()
					.append(request.getParameter("save_bmdm"))
					.append(request.getParameter("save_dwmc"))
					.toString();
		}
		
		if ("modify".equals(doType)) {
			updateOperation(request, tableName);
			pkValue = new StringBuilder()
					.append(request.getParameter("save_bmdm"))
					.append(request.getParameter("save_dwmc"))
					.toString();
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		service.setList(request, "jygzzph"); 
		request.setAttribute("doType", "save".equals(doType) ? null : doType);
		request.setAttribute("path", "jygl_qyxxsb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qyxxsb");
	}

	
	
	/**
	 * 企业信息审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qyxxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		
		JyglService service = new JyglService();
		
		String tableName = "jygl_qyxxb";
		String viewName = "view_jygl_qyxxb";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","bmmc","dwmc",
						"xqzy","qyfl","shzt"};
		
		if (!"xx".equals(userType) && !"admin".equals(userType)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/yhtj.do",false);
		}
		
		if ("sh".equals(doType)) {
			request.setAttribute("shzd", "shzt");
			auditingBatchOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request,"");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		service.setList(request, "jygzzph");
		request.setAttribute("path", "jygl_qyxxsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qyxxsh");
	}
	
	
	
	/**
	 * 企业信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qyxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jygl_qyxxb";
		String viewName = "view_jygl_qyxxb";
		String doType = request.getParameter("doType");
		String[] colList = new String[] {"bgcolor","pkValue","bmmc",
				"dwmc","xqzy","qyfl","shzt"};
		

		if ("xy".equals(userType)) {
			myForm.setQueryequals_bmdm(userDep);
		}
		
		if ("del".equals(doType)) {
			deleteOperation(request, tableName);
			
			doType = "query";
		}
		
		if ("query".equals(doType)) {
			service.getCustomAudiColumn(request, "");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if ("expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "jygzzph");
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_qyxxcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qyxxcx");
	}
	
	/**
	 * 选择考研，考公页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kykgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		
		if("stu".equalsIgnoreCase(userType)){
			String msg = "对不起，您无权访问此页！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		JyglService service = new JyglService();
		
		request.setAttribute("xmsqList", service.getKsList("sq"));
		
		setWriteAbleAndTitle(request, "jygl_kykgsq.do");
		return mapping.findForward("kykgsq");
	}
	
	/**
	 * 具体考研，考公申请页面。由学院用户申报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward byskssb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		String mk = request.getParameter("mk"); 
		String xh = StringUtils.isNull(request.getParameter("xh")) ?
				request.getParameter("save_xh") : request.getParameter("xh");
		
		String tableName = "jygl_zjkj_bysksb";
		String title = "ky".equalsIgnoreCase(mk) ? "毕业生考研申报" : "毕业生考公务员申报";
		
		if(StringUtils.isNotNull(xh)){
			selectPageDataByOne(request, "jy_bysxxb", "jy_bysxxb", xh);
		}
		
		if("save".equalsIgnoreCase(doType)){
			this.insertOperation(request, tableName);
		}
		
		request.setAttribute("title", title);
		request.setAttribute("mk", mk);
		return mapping.findForward("bysksUpdate");
	}
	
	/**
	 * 毕业生管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bysksManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
	
		String userType = (String)session.getAttribute("userType");
		String doType = request.getParameter("doType");
		String path = "sh".equalsIgnoreCase(doType) ? "jygl_kykgsh.do" : "jygl_kykgcx.do";
		String go = request.getParameter("go");
		String tableName = "jygl_zjkj_bysksb";
		String viewName = "view_jygl_zjkj_bysks";
		
		JyglService service = new JyglService();
		
		if("xy".equalsIgnoreCase(userType)){
			JyglForm myForm = (JyglForm)form;
			myForm.setQueryequals_xydm((String)session.getAttribute("userDep"));
		}
		
		if("sh".equalsIgnoreCase(doType) && !("admin".equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType))){
			String msg = "对不起，您无权访问此页！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		setWriteAbleAndTitle(request, path);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("doType", doType);
		request.setAttribute("xnList", Base.getXnndList());
		
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[] { "pkValue", "disabled",
					"bgcolor", "xh", "xm", "xymc", "zymc", "bjmc", "mkmc",
					"bkmb", "fs", "sfsx", "xxsh" };

			service.getCustomAudiColumn(request, userType);
			selectPageDataByPagination(request, form, tableName, viewName,outputColumn);
		}
		
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		return mapping.findForward("bysksManage");
	}
	
	/**
	 * 毕业生考试审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward byskssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "jygl_zjkj_bysksb";
		String shzd = "xxsh";	
		
		// 审核结果
		String shjg = request.getParameter("shjg");
		
		if (!StringUtils.isNull(shjg)) {
			shjg = "tg".equalsIgnoreCase(shjg) ? "通过" : "不通过";
		}
		
		// 获取页面中以primarykey_为开始的数据
		HashMap<String, String[]> primaryMap = getValueArrayMap(request,
				PRIFIX_PRIMARY_KEY);
		HashMap<String, String> valueMap = new HashMap<String, String>();
		valueMap.put(shzd, shjg);

		// 通用审核方法
		auditingBatchOperation(request, primaryMap, valueMap, tableName);
		
		return bysksManage(mapping, form, request, response);
	}
	
	/**
	 * 毕业生考试删除
	 */
	public ActionForward bysksdel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "jygl_zjkj_bysksb";
		this.deleteOperation(request, tableName);
		return bysksManage(mapping, form, request, response);
	}
	
	/**
	 * 毕业生考试导出
	 */
	public ActionForward bysksexp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "jygl_zjkj_bysksb";
		String viewName = "view_jygl_zjkj_bysks";
		
		JyglService service = new JyglService();
		expPageData(request, response, tableName, viewName, service.getColumn(viewName));
		return bysksManage(mapping, form, request, response);
	}
	
	/**
	 * 毕业生考试查看和修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bysksmodi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
	
		if("save".equalsIgnoreCase(doType)){
			String tableName = "jygl_zjkj_bysksb";
			this.updateOperation(request, tableName);
		}
		
		if(StringUtils.isNotNull(pkValue)){
			String tableName = "jygl_zjkj_bysksb";
			String viewName = "view_jygl_zjkj_bysks";
			this.selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("title", "毕业生考研考公信息查看");
		request.setAttribute("mk", ((HashMap<String, String>)request.getAttribute("rs")).get("mk"));
		return mapping.findForward("bysksView");
	}
	
	/**
	 * 具体就业援助申报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyyzUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String userType = (String)session.getAttribute("userType");
		String doType = request.getParameter("doType");
		String xh = request.getParameter("save_xh");

		if("stu".equalsIgnoreCase(userType)){
			String msg = "对不起，您无权访问此页！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		String tableName = "jygl_zjkj_jyyzb";
		JyglService service = new JyglService();
		
		if(StringUtils.isNotNull(xh)){
			selectPageDataByOne(request, "xsxxb", "view_xsbfxx", xh);
		}
		
		if("save".equalsIgnoreCase(doType)){
			this.insertOperation(request, tableName);
		}
		
		setWriteAbleAndTitle(request, "jygl_jyyzsq.do");
		
		request.setAttribute("yzjgList", service.getYzjgList());
		return mapping.findForward("jyyzUpdate");
	}
	
	/**
	 * 就业援助管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyyzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String userType = (String)session.getAttribute("userType");
		String doType = request.getParameter("doType");
		String mk = request.getParameter("mk");
		String path = "sh".equalsIgnoreCase(mk) ? "jygl_jyyzsh.do" : "jygl_jyyzcx.do";
		String go = request.getParameter("go");
		String tableName = "jygl_zjkj_jyyzb";
		String viewName = "view_jygl_zjkj_jyyz";
		
		JyglService service = new JyglService();
		
		if("xy".equalsIgnoreCase(userType)){
			JyglForm myForm = (JyglForm)form;
			myForm.setQueryequals_xydm((String)session.getAttribute("userDep"));
		}
		
		if("sh".equalsIgnoreCase(mk) && !("admin".equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType))){
			String msg = "对不起，您无权访问此页！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		setWriteAbleAndTitle(request, path);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("mk", mk);
		
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, tableName);
		}
		
		if ("expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
		}
		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = new String[]{"pkValue", "disabled", "bgcolor", 
						"xh", "xm", "xymc", "zymc", "bjmc", "tjdw", "yzjgmc","xxsh"};		
			service.getCustomAudiColumn(request, userType);
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		request.setAttribute("yzjgList", service.getYzjgList());
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		return mapping.findForward("jyyzManage");
	}
	
	/**
	 * 就业援助审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyyzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "jygl_zjkj_jyyzb";
		String shzd = "xxsh";	
		
		// 审核结果
		String shjg = request.getParameter("shjg");
		
		if (!StringUtils.isNull(shjg)) {
			shjg = "tg".equalsIgnoreCase(shjg) ? "通过" : "不通过";
		}
		
		// 获取页面中以primarykey_为开始的数据
		HashMap<String, String[]> primaryMap = getValueArrayMap(request,
				PRIFIX_PRIMARY_KEY);
		HashMap<String, String> valueMap = new HashMap<String, String>();
		valueMap.put(shzd, shjg);

		// 通用审核方法
		auditingBatchOperation(request, primaryMap, valueMap, tableName);
		
		return jyyzManage(mapping, form, request, response);
	}
	
	/**
	 * 就业援助修改 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyyzmodi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String tableName = "jygl_zjkj_jyyzb";
		String viewName = "view_jygl_zjkj_jyyz";
		
		JyglService service = new JyglService();
		
		if("save".equalsIgnoreCase(doType)){
			updateOperation(request, tableName);
		}
		
		if(StringUtils.isNotNull(pkValue)){
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		request.setAttribute("yzjgList", service.getYzjgList());
		request.setAttribute("doType", doType);
		request.setAttribute("title", "就业援助信息查看");
		return mapping.findForward("jyyzView");
	}
	
	/**
	 * 设置读写权和title
	 * @param request
	 * @param path
	 */
	private void setWriteAbleAndTitle(HttpServletRequest request, String path){
		request.setAttribute("path", path);
		
		/*String[] writeAbleAndTitle = FormModleCommon.getWriteAbleAndTitle(request);
		
		request.setAttribute("writeAble", writeAbleAndTitle[0]);
		request.setAttribute("title", writeAbleAndTitle[1]);*/
		FormModleCommon.commonRequestSet(request);
	}
	
	
	
	
	/**
	 * 就业协议编号维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jyxybhwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "xg_jygl_jyxybhb";
		String viewName = "xg_view_jygl_jyxybhb";
		String[] colList = new String[] {"disabled","pkValue","jyxybh","sfsy"};
		String doType = request.getParameter("doType");
		
		if ("del".equals(doType)) {
			deleteOperation(request, tableName);
			
			doType="query";
		}
		
		if ("query".equals(doType)) {
			request.setAttribute("clientColumns", "(case when sfsy='是' then 'disabled' else '' end) disabled,");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		if ("expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "");
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("realTable", tableName);
		request.setAttribute("path", "jygl_jyxybhwh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jyxybhwh");
	}
	
	
	
	/**
	 * 就业协议书领取登记
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xyslqdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "jygl_jyxyslqdjb";
		String viewName = "view_jygl_jyxyslqdjb";
		String[] colList = new String[] {"pkValue","xh","xm","nj","xymc",
						"zymc","bjmc","lqqk","jyxybh"};
		String doType = request.getParameter("doType");
		
		if ("stu".equals(userType)) {
			request.setAttribute("message","对不起，您无权访问此页!" );
			return new ActionForward("/prompt.do",false);
		}
		
		//批量保存就业协议书领取情况
		if ("save".equals(doType)) {
			String[] xh = request.getParameterValues("save_xh");
			String[] lqqk = new String[xh.length];
			String[] jyxybh = request.getParameterValues("save_jyxybh");
			
			for (int i = 0 ; i < lqqk.length ; i++) {
				lqqk[i] = request.getParameter("save_lqqk"+i);
			}
			
			boolean result = service.saveJyxylq(xh, lqqk, jyxybh,userName);
			request.setAttribute("message", result ? "保存成功" : "保存失败");
			
			doType = "query";
		}
		
		if ("del".equals(doType)) {
			deleteOperation(request, tableName);
			
			doType="query";
		}
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		}
		
		if ("query".equals(doType)) {
			selectPageDataByPagination(request, myForm, tableName, viewName, colList);
		}
		
			
		if ("expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("sybh", service.getSybh());
		service.setList(request, "jyxys");
		request.setAttribute("path", "jygl_xyslqdj.do");
		FormModleCommon.commonRequestSet(request);
		service.setList(request, "");
		return mapping.findForward("xyslqdj");
	}
	
	
	
	/**
	 * 协议书补领申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xysblsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglService service = new JyglService();
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		
		String tableName = "jygl_jyxyblsqb";
		String viewName = "view_jygl_jyxyblsq";
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String now = service.getNow();
		String pkValue = request.getParameter("pkValue");
		String xxsh = request.getParameter("save_xxsh");
		String flg = request.getParameter("flg");
		
		if ("stu".equals(userType)) {
			xh = userName;
		}

		if ("save".equals(doType)) {
			insertOperation(request, tableName);
			pkValue = request.getParameter("save_xh")+now;
		}
		
		if ("modify".equals(doType)) {
			updateOperation(request, tableName);
			pkValue = request.getParameter("pkValue");

			if ("通过".equals(xxsh)
					&& Boolean.valueOf(request.getAttribute("result")
							.toString())) {
				boolean result = service.updateXysbhOne(request
						.getParameter("save_xh"), request
						.getParameter("save_newjyxybh"));
				request.setAttribute("message", result ? "操作成功!" : "操作成败!");
			}
		}
		
		if (!Base.isNull(pkValue)) {
			selectPageDataByOne(request, tableName, viewName, pkValue);
		}
		
		if ((!Base.isNull(xh) && Base.isNull(doType)) 
				|| (Base.isNull(doType) && "stu".equals(userType))) {
			selectPageDataByOne(request, "jygl_jyxyslqdjb", "view_jygl_jyxyslqdjb", xh);
		}
		
		service.setList(request, "xysbl");
		request.setAttribute("flg", flg);
		request.setAttribute("doType", "save".equals(doType) ? null : doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("cssz", service.getCssz());
		request.setAttribute("path", "jygl_xysblsq.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xysblsq");
	}
	
	
	
	
	/**
	 * 协议书补领审核
	 * @author Penghui Qu
	 * @return
	 * @throws Exception
	 */
	public ActionForward xysblsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		HttpSession session = request.getSession();

		String userType = (String) session.getAttribute("userType");
		String userName = (String) session.getAttribute("userName");
		String userDep = (String) session.getAttribute("userDep");
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		
		String tableName = "jygl_jyxyblsqb";
		String viewName = "view_jygl_jyxyblsq";
		String doType = request.getParameter("doType");
		String shjg = request.getParameter("shjg");
		HashMap<String, String> valueMap = new HashMap<String, String>();
		HashMap<String, String> csszInfo = service.getCssz();

		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		} else if ("stu".equals(userType)) {
			request.setAttribute("message", "对不起，您无权访问此页!");
			return new ActionForward("/prompt.do", false);
		}
		
		//如果参数设置信息为空就初始化参数
		if (null == csszInfo) {
			service.initCssz();
		}

		String[] colList = null;
		int shjb = Integer.valueOf(csszInfo.get("xysbbshjb"));
		String shr = csszInfo.get("xysbbshr");

		if ("admin".equals(userType)) {
			userType="xx";
		}
		
		// 根据审核级别确定查询字段/审核字段
		if (1 != shjb) {
			if (!isFdy && shr.indexOf("fdy") > -1 && !"xx".equals(userType)
					&& !"admin".equals(userType)) {
				request.setAttribute("message", "对不起，您无权访问此页!");
				return new ActionForward("/prompt.do", false);
			}
			
			if ((!"xy".equals(userType)) && "1".equals(XMLReader.getFlowControl("jygl", "jyxysbl"))) {
				request.setAttribute("annexTerm", " and xysh='通过' ");
			}
			
			colList = new String[] { "disabled", "isdis", "pkValue", "xh",
					"xm", "nj", "xymc", "zymc", "bjmc","bblb","xysh","xxsh" };

			valueMap.put("xy".equals(userType) ? "xysh" : "xxsh", shjg);
			service.setCustomAudiColumn(request, userType+"sh");
		} else {
			
			colList = new String[] { "disabled", "isdis", "pkValue", "xh",
					"xm", "nj", "xymc", "zymc", "bjmc", "bblb",
					 "xxsh" };

			if ((shr.indexOf(userType)==-1 && (!isFdy && (shr.indexOf("fdy") > -1)))
					|| ((shr.indexOf("xx")==-1) && "xx".equals(userType))
					|| (shr.replace(",", "").equals("xx") && !"xx".equals(userType))) {
				request.setAttribute("message", "对不起，您无权访问此页!");
				return new ActionForward("/prompt.do", false);
			}
			
			valueMap.put("xxsh", shjg);
			service.setCustomAudiColumn(request, "xxsh");
		}

		//审核
		if ("sh".equals(doType)) {

			if ("xy".equals(userType) && 2==Integer.valueOf(csszInfo.get("xysbbshjb"))) {
				valueMap.put("xyshr", userName);
				valueMap.put("xyshsj", service.getNow());
				valueMap.put("xyshyj", myForm.getSave_shyj());
				
				auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
				
				if ("通过".equals(shjg)) {
					boolean result = service.resetXxsh(tableName, request.getParameterValues("primarykey_cbv"));
					request.setAttribute("message", result ? "操作成功" : "操作失败");
				}
				
			} else {
				valueMap.put("xxsh", shjg);
				valueMap.put("xxshr", userName);
				valueMap.put("xxshsj", service.getNow());
				valueMap.put("xxshyj", myForm.getSave_shyj());
				
				//把新就业协议书编号同步到协议书领取登记表
				if ("通过".equals(shjg)) {
					boolean result = service.xysblsh(request.getParameterValues("primarykey_cbv"),valueMap);
					request.setAttribute("message", result ? "操作成功!" : "操作失败!");
				} else {
					auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
				}
			}
			
			doType="query";
			
		}
		
		//取消审核
		if ("qxsh".equals(doType)) {

			if ("xy".equals(userType) && 2==Integer.valueOf(csszInfo.get("xysbbshjb"))) {
				valueMap.put("xysh", "未审核");
				valueMap.put("xyshr", "");
				valueMap.put("xyshsj", "");
				valueMap.put("xyshyj", "");
				
				auditingBatchOperation(request, getValueArrayMap(request, PRIFIX_PRIMARY_KEY), valueMap, tableName);
			} else {
				valueMap.put("xxsh", "未审核");
				valueMap.put("xxshr", "");
				valueMap.put("xxshsj", "");
				valueMap.put("xxshyj", "");
				valueMap.put("newjyxybh", "");
				
				//取消同步到协议书领取登记表的协议书编号
				boolean result = service.xysblsh(request.getParameterValues("primarykey_cbv"),valueMap);
				request.setAttribute("message", result ? "操作成功!" : "操作失败!");
			}
			
			doType="query";
		}
		
		//查询
		if ("query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, viewName,colList);
		}
	
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		service.setList(request, "xysbl");
		request.setAttribute("sybh", service.getSybh());
		request.setAttribute("cssz", csszInfo);
		request.setAttribute("path", "jygl_xysblsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xysblsh");
	}
	
	
	
	
	/**
	 * 协议书补领结果查询
	 * @return
	 * @throws Exception
	 */
	public ActionForward xysblcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		HttpSession session = request.getSession();

		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		String tableName = "jygl_jyxyblsqb";
		String viewName = "view_jygl_jyxyblsq";
		String doType = request.getParameter("doType");
		HashMap<String, String> csszInfo = service.getCssz();

		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
			request.setAttribute("annexTerm", " and xydm= '"+userDep+"'");
		} else if ("stu".equals(userType)) {
			request.setAttribute("annexTerm", " and xh= '"+userName+"'");
		}
		
		//如果参数设置信息为空就初始化参数
		if (null == csszInfo) {
			service.initCssz();
		}

		String[] colList = null;
		int shjb = Integer.valueOf(csszInfo.get("xysbbshjb"));

		// 根据审核级别确定查询字段
		if (1 != shjb) {
			colList = new String[] {  "isdis", "pkValue", "xh",
					"xm","nj", "xymc", "zymc", "bjmc", 
					"bblb", "xysh","xyshr", "xxsh","xxshr" };
			
			service.setCustomAudiColumn(request, "xxsh");
		} else {
			colList = new String[] {  "isdis", "pkValue", "xh",
					"xm","nj", "xymc", "zymc", "bjmc","bblb", "xxsh","xxshr" };
			
			service.setCustomAudiColumn(request, userType+"sh");
		}

		if ("del".equals(doType)) {
			deleteOperation(request, tableName);
			
			doType = "query";
		}

		if ("query".equals(doType)) {
			selectPageDataByPagination(request, form, tableName, viewName,colList);
		}

		if ("expData".equals(doType)) {
			expPageData(request, response, tableName, viewName, service.getColumn(viewName));
			return mapping.findForward("");
		}
		
		service.setList(request, "xysbl");
		request.setAttribute("topTr", service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		request.setAttribute("realTable", tableName);
		request.setAttribute("tableName", viewName);
		request.setAttribute("cssz", csszInfo);
		request.setAttribute("path", "jygl_xysblcx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xysblcx");
	}
	
	
	
	/**
	 * 领取过就业协议书的数据源
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xysData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		String url = request.getParameter("url");
		String xh = request.getParameter("xh");
		String tableName = "jygl_jyxyslqdjb";
		String viewName = "view_jygl_jyxyslqdjb";
		String doType = request.getParameter("doType");
			
		String[] colList = new String[] {"xh","nj","xm","xymc","zymc","bjmc","jyxybh"};
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		if ("xy".equals(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		//查询领取过就业协议书的学生
		if ("query".equals(doType)) {
			request.setAttribute("annexTerm", " and jyxybh is not null ");
			selectPageDataByPagination(request, form, tableName, viewName, colList);
		}
		
		
		//返回指定的URL
		if (!Base.isNull(url)) {
			return new ActionForward(url+"&xh="+xh,false);
		}
		
		request.setAttribute("topTr",service.getColumn(viewName, colList));
		request.setAttribute("maxNum", myForm.getPages().getPageSize());
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("xysData");
	}
	
	
	
	
	/**
	 * 参数设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JyglForm myForm = (JyglForm) form;
		JyglService service = new JyglService();
		
		String tableName = "jygl_csszb";
		String doType = request.getParameter("doType");
		
		if ("save".equals(doType)) {
			updateOperation(request, tableName);
		}
		
		//检测是否有配置，没有则初始化
		service.initCssz();
		
		selectPageDataByOne(request, tableName, tableName, Base.xxdm);
		
		HashMap<String,String> rs = (HashMap<String, String>) request.getAttribute("rs");
		
		String shr = rs.get("xysbbshr");
		
		if (!Base.isNull(shr) && shr.length()>0) {
			myForm.setShr(shr.split(","));
		}
		
		request.setAttribute("myForm", myForm);
		request.setAttribute("path", "jygl_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
}




