package xgxt.xljk.hzny;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.xszz.whtl.ybbx.XszzYbbxForm;
import xgxt.xszz.whtl.ybbx.XszzYbbxInit;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-4 下午04:34:29</p>
 */
public class HznyXljkZxzxAction extends BasicAction{
	
	HznyXljkZxzxService service =new HznyXljkZxzxService();
	
	HznyXljkZxzxInit init =new HznyXljkZxzxInit();
	
	// -----------------------咨询师管理 begin---------------------------
	/**
	 * 咨询师管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		User user=getUser(request);
		
		HznyXljkZxzxForm  myForm=(HznyXljkZxzxForm )form;
		
		BasicModel model=new BasicModel();
		//将form值拷贝到model中
		BeanUtils.copyProperties(model, myForm);
		
		//查询的结果集
		model.setColList(new String[]{"pkValue","zgh","xm", "xb",  "bmmc","zxszg_info"});
		//排序字段
		model.setOrderBy(new String[]{"bmdm","zgh"});
		//功能模块
		model.setGnmk("xljk");
		//模块路径
		model.setPath("hn_xljk_zxsgl.do");
		
		init.initZxsglManage(rForm, model, request,user);
		//结果列表
	
		//高级查询
		request.setAttribute("searchTj", model.getSearchModel());
		
		service.setRequestValue(rForm, user, request);
		
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("tableName", "xg_view_xljk_zxsxx");
		
		request.setAttribute("realTable", "xg_xljk_zxsxxb");
		
		//request.setAttribute("tableName", TABLENAME);
		// =================== end ===================

		return mapping.findForward("zxsglManage");
	
	}

	/**
	 * 咨询师详细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxsglDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String doType=request.getParameter("doType");
		
		String zgh=request.getParameter("zgh");
		
		String pkValue=request.getParameter("pkValue");
		
		HashMap<String, String> teaInfo=new HashMap<String,String>();

		if(!Base.isNull(zgh)){
			//辅导员信息
			teaInfo.putAll(service.getTeaInfo(zgh));
			
		}
		
		if(!Base.isNull(pkValue)){
			//获取咨询师信息
			teaInfo.putAll(service.getTeaInfo(pkValue));
		}
	
		request.setAttribute("rs", teaInfo);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "hn_xljk_zxsgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zxsglDetail");
	}

	// -----------------------咨询师管理 end---------------------------
	
	
	// -----------------------特殊学生管理 begin---------------------------
	/**
	 * 特殊学生管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsxsglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		User user=getUser(request);
		
		HznyXljkZxzxForm myForm=(HznyXljkZxzxForm)form;
		
		BasicModel model=new BasicModel();
		//将form值拷贝到model中
		BeanUtils.copyProperties(model, myForm);
		
		//查询的结果集
		model.setColList(new String[]{"pkValue","xh","xm", "nj",  "bjmc","xysh","xxsh"});
		//排序字段
		model.setOrderBy(new String[]{"bjdm","xh"});
		//功能模块
		model.setGnmk("xszz");
		//模块路径
		model.setPath("hn_xljk_tsxsgl.do");
		
		init.initTsxsglManage(rForm, model, request,user);
		//结果列表
	
		//高级查询
		request.setAttribute("searchTj", model.getSearchModel());
		
		service.setRequestValue(rForm, user, request);
		
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("tableName", "xg_view_xljk_tsxsxx");
		
		request.setAttribute("realTable", "xg_xljk_tsxsxxb");
		// =================== end ===================

		return mapping.findForward("tsxsglManage");
	
	}

	/**
	 * 心理特殊学生详细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsxsglDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService stuService = new XsxxglService();
		
		String doType=request.getParameter("doType");
		
		String xh=request.getParameter("xh");
		
		String pkValue=request.getParameter("pkValue");
		
		HashMap<String, String> stuInfo=new HashMap<String,String>();
		
		// 设置默认的RADIO值
		stuInfo.put("sfzy", "否");
		
		if(!Base.isNull(xh)){
			// 学生基本信息
			stuInfo.putAll(stuService.selectStuinfo(xh));

		}
		
		if(!Base.isNull(pkValue)){
			// 获取学生信息
			stuInfo.putAll(stuService.selectStuinfo(pkValue));
			// 特殊学生信息
			stuInfo.putAll(service.getTsxsInfo(pkValue));
			// 设置默认的RADIO值
			if(Base.isNull(stuInfo.get("sfzy"))){
				stuInfo.put("sfzy", "否");
			}
		}
		
		request.setAttribute("rs", stuInfo);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "hn_xljk_tsxsgl.do");
		// 特别关心学生类别信息列表
		request.setAttribute("gxxslbList", service.getTbgxxslb());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tsxsglDetail");
	}
	
	// -----------------------特殊学生管理 end---------------------------
	
	// -----------------------在线咨询管理 begin---------------------------
	/**
	 * 在线咨询管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		User user=getUser(request);
		
		String userType=user.getUserType();
		
		String userName=user.getUserName();
		
		if(!("stu".equalsIgnoreCase(userType)
				|| service.checkZxs(userName)
				|| "admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType))){
			String msg = "本模块只能由学生、管理员、心理咨询师进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		HznyXljkZxzxForm myForm=(HznyXljkZxzxForm)form;
		
		BasicModel model=new BasicModel();
		//将form值拷贝到model中
		BeanUtils.copyProperties(model, myForm);
		
		//查询的结果集
		
		if("stu".equalsIgnoreCase(userType)){
			
			model.setColList(new String[]{"pkValue","xh","xm", "zxsj", "zxwt","sfhf"});
		}else {
			
			model.setColList(new String[]{"pkValue","xh","xm","nj","bjmc", "zxsj","zxwt","sfhf","sftsxs"});
		}
		
		//排序字段
		model.setOrderBy(new String[]{"bjdm","xh"});
		//功能模块
		model.setGnmk("xszz");
		//模块路径
		model.setPath("hn_xljk_zxzx.do");
		
		init.initZxzxglManage(rForm, model, request,user);
		//结果列表
	
		//高级查询
		request.setAttribute("searchTj", model.getSearchModel());
		
		service.setRequestValue(rForm, user, request);
		
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("tableName", "xg_view_xszz_ybbxxx");
		request.setAttribute("zxs", service.checkZxs(user.getUserName()));
		//request.setAttribute("tableName", TABLENAME);
		// =================== end ===================

		return mapping.findForward("zxzxManage");
	
	}

	/**
	 * 在线咨询详细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user=getUser(request);
		
		HznyXljkZxzxForm myForm=(HznyXljkZxzxForm)form;
		
		XsxxglService stuService = new XsxxglService();
		
		String doType=request.getParameter("doType");
		
		String xh="";
		
		String pkValue=request.getParameter("pkValue");
		
		HashMap<String, String> stuInfo=new HashMap<String,String>();
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			
			xh=user.getUserName();
			stuInfo.putAll(stuService.selectStuinfo(xh));
		}
	
		if(!Base.isNull(pkValue)){

			stuInfo.putAll(service.getZxzxInfo(pkValue));
		}
		

		if(Base.isNull(doType)){
		
			doType="add";
		}

		request.setAttribute("rs", stuInfo);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "hn_xljk_zxzx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zxzxDetail");
	}

	// -----------------------在线咨询管理 end---------------------------
	
	// -----------------------生咨询管理 begin---------------------------
	/**
	 * 学生咨询管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		User user=getUser(request);
		
		HznyXljkZxzxForm myForm=(HznyXljkZxzxForm)form;
		
		BasicModel model=new BasicModel();
		//将form值拷贝到model中
		BeanUtils.copyProperties(model, myForm);
		
		String userType =user.getUserType();
		
		String userName = user.getUserName();
		
		String path="hn_xljk_xszx.do";
		
		if(!("stu".equalsIgnoreCase(userType)
				|| service.checkZxs(userName)
				|| "admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType))){
			String msg = "本模块只能由学生、管理员、心理咨询师进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		
		//查询的结果集
		if("stu".equalsIgnoreCase(userType)){
			
			model.setColList(new String[]{"pkValue","zxsxm","zxsj", "zxnrjyj", "sfhf"});
		
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			path="hn_xljk_xszx.do?searchType=admin";
			model.setColList(new String[]{"pkValue","zgh","zxsxm","xh","xm","zxsj", "sffk"});
		
		}else {
			path="hn_xljk_xszx.do?searchType=zxs";
			model.setColList(new String[]{"pkValue","zxsxm","zxsj", "zxnrjyj", "sfhf","sfzdbr"});
		}
		//排序字段
		model.setOrderBy(new String[]{"bjdm","xh"});
		//功能模块
		model.setGnmk("xljk");
		//模块路径
		model.setPath(path);
		
		init.initXszxManage(rForm, model, request, user);
		//结果列表
	
		//高级查询
		request.setAttribute("searchTj", model.getSearchModel());
		
		service.setRequestValue(rForm, user, request);
		
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("zxs", service.checkZxs(user.getUserName()));
		request.setAttribute("tableName", "xg_view_xszz_ybbxxx");
		
		//request.setAttribute("tableName", TABLENAME);
		// =================== end ===================

		return mapping.findForward("xszxManage");
	
	}
	
	/**
	 * 学生咨询详细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType=request.getParameter("doType");
		
		String zgh=request.getParameter("zgh");
		
		String pkValue=request.getParameter("pkValue");
		
		HashMap<String, String> teaInfo=new HashMap<String,String>();
	

		if(!Base.isNull(zgh)){
			
			// 学生基本信息
			teaInfo.putAll(service.getXlzxsInfo(zgh));
			
		}
		
		if(!Base.isNull(pkValue)){
		
			teaInfo.putAll(service.getXszxInfo(pkValue));
		}
		

		if(Base.isNull(doType)){
		
			doType="add";
		}
		
		request.setAttribute("rs", teaInfo);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "hn_xljk_xszx.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xszxDetail");
	}
	
	// -----------------------生咨询管理 end---------------------------
	
	// -----------------------咨询记录管理 begin---------------------------
	/**
	 * 咨询记录管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RequestForm rForm = new RequestForm();
		
		User user=getUser(request);
		
		HznyXljkZxzxForm myForm=(HznyXljkZxzxForm)form;
		
		BasicModel model=new BasicModel();
		//将form值拷贝到model中
		BeanUtils.copyProperties(model, myForm);
		
		String userType=user.getUserType();
		
		//查询的结果集
		if("stu".equalsIgnoreCase(userType)){
			
			model.setColList(new String[] { "pkValue", "xh", "xm", "nj", "bjmc","zxsj" });
		
		}else {
			
			model.setColList(new String[] { "pkValue", "xh", "xsxm", "nj", "bjmc",
					"zxsj","sftsxs" });
		}
		//排序字段
		model.setOrderBy(new String[]{"bjdm","xh"});
		//功能模块
		model.setGnmk("xljk");
		//模块路径
		model.setPath("hn_xljk_zxjl.do");
		
		init.initZxjlManage(rForm, model, request,user);
		//结果列表
	
		//高级查询
		request.setAttribute("searchTj", model.getSearchModel());
		request.setAttribute("zxs", service.checkZxs(user.getUserName()));
		service.setRequestValue(rForm, user, request);
		
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("tableName", "xg_view_xljk_xlzxjl");
		
		//request.setAttribute("tableName", TABLENAME);
		// =================== end ===================

		return mapping.findForward("zxjlManage");
	
	}
   
	/**
	 * 咨询记录详细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjlDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user=getUser(request);
		
		XsxxglService stuService = new XsxxglService();
		
		String doType=request.getParameter("doType");
		
		String xh=request.getParameter("xh");
		
		String pkValue=request.getParameter("pkValue");
		
		HashMap<String, String> stuInfo=new HashMap<String,String>();
	
		if(!Base.isNull(xh)){
			
			// 学生基本信息
			stuInfo.putAll(stuService.selectStuinfo(xh));
			
		}
		
		if(!Base.isNull(pkValue)){
	
			stuInfo.putAll(service.getZxjlInfo(pkValue));
			
		}
		
		if(Base.isNull(doType)){
		
			doType="add";
		}
		
		request.setAttribute("rs", stuInfo);
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("doType", doType);
		request.setAttribute("path", "hn_xljk_zxjl.do");
		request.setAttribute("gxxslbList", service.getTbgxxslb());
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("zxjlDetail");
	}

	// -----------------------咨询记录管理 end---------------------------
}
