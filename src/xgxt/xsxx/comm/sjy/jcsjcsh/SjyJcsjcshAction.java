package xgxt.xsxx.comm.sjy.jcsjcsh;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.jygl.comman.JyglService;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;
import xgxt.xsxx.comm.jbsz.XsxxJbszInit;
import xgxt.xsxx.comm.jbsz.XsxxJbszService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_数据源_基础数据初始化action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SjyJcsjcshAction extends BasicAction {

	/**
	 * 学生信息_数据源_基础数据初始化
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcsjcshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjcshForm myForm = (SjyJcsjcshForm) form;
		SjyJcsjcshService service = new SjyJcsjcshService();
		SjyJcsjcshInit init = new SjyJcsjcshInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 操作项目
		String czxm = request.getParameter("czxm");
		czxm = Base.isNull(czxm) ? "xy" : czxm;
		myForm.setCzxm(czxm);

		RequestForm rForm = new RequestForm();
		init.getJcsjcshRForm(rForm, myForm, request);

		// 页面跳转
		String forward = "";
		if ("xy".equalsIgnoreCase(czxm)) {
			forward = "jcsjcshForXy";
		} else if ("zy".equalsIgnoreCase(czxm)) {
			forward = "jcsjcshForZy";
		} else if ("bj".equalsIgnoreCase(czxm)) {
			forward = "jcsjcshForBj";
		}else if ("xsjbxx".equalsIgnoreCase(czxm)) {
			forward = "jcsjcshForStu";
		}

		// 是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		//		 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 操作类型（保存，删除等）
		String importResult = request.getParameter("importResult");
		// 结果集显示字段
		String[] colList = rForm.getColList();
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// 初始化项目
		List<HashMap<String, String>> cshXmList = service.getCshXmList(myForm);
		request.setAttribute("cshXmList", cshXmList);

		// 结果集显示字段
		String editPageSize = request.getParameter("editPageSize");
		if (!"yes".equalsIgnoreCase(editPageSize)) {
			myForm.getPages().setPageSize(16);
			request.setAttribute("autoPageSize", "16");
		}
		// ================= end =====================

		// =============== 执行同步操作 ===============
		if ("tb".equalsIgnoreCase(doType)) {
		
			boolean flag = service.tbInfo(myForm, user);
			String message = flag ? "同步成功" : "同步失败";
			
			//同步失败
			if(!flag){
				//存储过程名称
				String pro = "";
				
				if ("xy".equalsIgnoreCase(czxm)) {
					pro = "pro_xg_jcsj_bmtb";
				} else if ("zy".equalsIgnoreCase(czxm)) {
					pro = "pro_xg_jcsj_zytb";
				} else if ("bj".equalsIgnoreCase(czxm)) {
					pro = "pro_xg_jcsj_bjtb";
				}else if ("xsjbxx".equalsIgnoreCase(czxm)) {
					pro = "pro_xg_jcsj_stutb";
				}
				
				message += "\n注：失败原因可在plsql中参考存储过程";
				message += pro;
				message += "，可根据实际情况进行修改";
			}
			
			rForm.setMessage(message);

			search = true;
		}
		// ================= end =====================

		// =============== 勾选提交操作 ===============
		if ("submit".equalsIgnoreCase(doType)) {
			boolean flag = service.submitCheckInfo(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
			rForm.setMessage(message);

			search = true;
		}
		// ================= end =====================
		
		// =============== 整体提交操作 ===============
		if ("allSubmit".equalsIgnoreCase(doType)) {
			boolean flag = service.allSubmit(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
			rForm.setMessage(message);

			search = true;
		}
		// ================= end =====================
		
		// =============== 数据导入成功，执行规则制定 ===============
		if ("yes".equalsIgnoreCase(importResult)) {
			service.doRule(myForm, user);
		}
		// ================= end =====================
		
		// =============== 执行查询操作 ===========

		// 结果集
		rsArrList = service.getCshInfoList(myForm, user, colList);
		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward(forward);
	}

	/**
	 * 学生信息_数据源_规则制定
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ruleManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjcshForm myForm = (SjyJcsjcshForm) form;
		SjyJcsjcshService service = new SjyJcsjcshService();
		SjyJcsjcshInit init = new SjyJcsjcshInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getJcsjcshRForm(rForm, myForm, request);

		// 操作项目
		String czxm = myForm.getCzxm();

		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// ================= end =====================

		// =============== 初始化规则项 ===========
		// 规则列表
		List<HashMap<String, Object>> ruleList = service.getRuleList(myForm);
		request.setAttribute("ruleList", ruleList);
		// ================= end =====================

		// =============== 执行同步操作 ===============
		if ("tb".equalsIgnoreCase(doType)) {
			boolean flag = service.doRule(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =================== 初始化列表值 ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("ruleManage");
	}

	/**
	 * 学生信息_数据源_规则制定
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ruleUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjcshForm myForm = (SjyJcsjcshForm) form;
		SjyJcsjcshService service = new SjyJcsjcshService();
		SjyJcsjcshInit init = new SjyJcsjcshInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getJcsjcshRForm(rForm, myForm, request);

		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// ================= end =====================

		// =============== 执行保存操作 ===============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveRule(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== 初始化规则项 ===========
		// 规则列表
		List<HashMap<String, String>> ruleList = service.getRuleZdList(myForm);
		request.setAttribute("ruleList", ruleList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("ruleUpdate");
	}
	
	/**
	 * 加载部门下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setBmOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		id = Base.isNull(id) ? "bmdm" : id;
		String bmmc = request.getParameter(id);
		
		if (!Base.isNull(bmmc)){
			bmmc = URLDecoder.decode(bmmc, "UTF-8");
		}
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		
		List<HashMap<String, String>> map = service.getBmOption(bmmc);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * 加载年级下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setNjOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		id = Base.isNull(id) ? "nj" : id;
		String bmmc = request.getParameter(id);
		
		if (!Base.isNull(bmmc)){
			bmmc = URLDecoder.decode(bmmc, "UTF-8");
		}
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		
		List<HashMap<String, String>> map = service.getNjOption(bmmc);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * 加载专业下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setZyOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		id = Base.isNull(id) ? "zydm" : id;
		
		String zymc = request.getParameter(id);
		
		if (!Base.isNull(zymc)){
			zymc = URLDecoder.decode(zymc, "UTF-8");
		}
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		// 年级
		String nj = request.getParameter("search_nj");
		// 学院
		String xy = request.getParameter("search_xy");

		String[] searchTj = new String[] { nj, xy };
		       
		List<HashMap<String, String>> map = service.getZyOption(zymc,searchTj);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * 加载班级下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setBjOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		id = Base.isNull(id) ? "bjdm" : id;
		
		String bjmc = request.getParameter(id);
		// 年级
		String nj = request.getParameter("search_nj");
		// 学院
		String xy = request.getParameter("search_xy");
		// 专业
		String zy = request.getParameter("search_zy");
		
		String[] searchTj = new String[] { nj, xy, zy };
		
		if (!Base.isNull(bjmc)){
			bjmc = URLDecoder.decode(bjmc, "UTF-8");
		}
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		
		List<HashMap<String, String>> map = service.getBjOption(bjmc,searchTj);
		
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * 加载学籍下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setXjOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		id = Base.isNull(id) ? "xjztm" : id;
		String bmmc = request.getParameter(id);
		
		if (!Base.isNull(bmmc)){
			bmmc = URLDecoder.decode(bmmc, "UTF-8");
		}
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		
		List<HashMap<String, String>> map = service.getXjOption(bmmc);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * 加载行政区块下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setXzqkOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		String lx = request.getParameter("lx");
		String xzqmc = request.getParameter(id);
		
		if (!Base.isNull(xzqmc)){
			xzqmc = URLDecoder.decode(xzqmc, "UTF-8");
		}
		
		// 省
		String sheng = request.getParameter("sheng");
		// 市
		String shi = request.getParameter("shi");
		// 县
		String xian = request.getParameter("xian");

		String[] searchTj = new String[] { sheng, shi, xian };
		
		SjyJcsjcshService service = new SjyJcsjcshService();
		
		
		List<HashMap<String, String>> map = service.getXzqkOption(xzqmc,lx,searchTj);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
}
