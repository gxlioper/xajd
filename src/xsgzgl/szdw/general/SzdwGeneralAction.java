package xsgzgl.szdw.general;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.szdw.general.dwwh.DwwhInit;
import xsgzgl.szdw.general.szbb.SzbbInit;
import xsgzgl.szdw.general.szbb.SzbbService;
import xsgzgl.szdw.general.tjcx.bmqk.BmqkInit;
import xsgzgl.szdw.general.tjcx.bmqk.BmqkService;
import xsgzgl.szdw.general.tjcx.szry.SzryInit;
import xsgzgl.szdw.general.tjcx.szry.SzryService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.szdw.cssz.CsszUtil;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszForm;
import com.zfsoft.xgxt.szdw.cssz.SzdwCsszService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SzdwGeneralAction extends BasicAction {

	// ================== 思政队伍维护 begin ==============================

	/**
	 * 页面跳转【思政队伍维护】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward szdwDwwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		DwwhInit init = new DwwhInit();
		User user = getUser(request);// 用户对象
		
		String userStatus=user.getUserStatus();
		
		if (!("xy".equalsIgnoreCase(userStatus) || "xx".equalsIgnoreCase(userStatus) || "sy".equalsIgnoreCase(userStatus))) {
			String msg = "本模块只能由"+Base.YXPZXY_KEY+"、书院或者学校用户进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ============= 初始化各变量的值 begin ============
		init.initDwwhSearch(rForm, myForm, user, request);
		init.initParameter(myForm);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		request.setAttribute("path", "szdw_general_dwwh.do");
		String url = "/xsgzgl/szdw/" + xxpymc + "/dwwh/dwwhSearch.jsp";
		request.setAttribute("tableName", "view_fdybbqk");
		request.setAttribute("realTable", "fdyxxb");
		
		//获取编班时间参数
		SzdwCsszService szdwCsszService = new SzdwCsszService();
		SzdwCsszForm bbsjModel = szdwCsszService.getModel(CsszUtil.SZDW_BBSJ);
		request.setAttribute("bbsjModel", bbsjModel);
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	public ActionForward fdyxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();

		User user = getUser(request);// 用户对象
		
		String userStatus=user.getUserStatus();
		
		if (!("xy".equalsIgnoreCase(userStatus) || "xx".equalsIgnoreCase(userStatus))) {
			String msg = "本模块只能由"+Base.YXPZXY_KEY+"或者学校用户进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		if(QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> list = service.getJsxxList(myForm,user);
			response.getWriter().print(JSONArray.fromObject(list));
			return null;
		}
		request.setAttribute("path", "szdw_general_fdyxxwh.do");
		return mapping.findForward("jsxxList");
	}
	
	/**
	 * 页面跳转【思政人员编班】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward szdwRybb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		DwwhInit init = new DwwhInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initRybbSetting(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		//String url = "/xsgzgl/szdw/" + xxpymc + "/dwwh/rybbSetting.jsp";
		String url = "/xsgzgl/szdw/" + xxpymc + "/dwwh/rybbSetup.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	// ================== 思政队伍维护 end ================================

	// ==================思政队伍编班 begin================================
	
	/**
	 * 查询【思政队伍编班】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward szdwSzBzrbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		SzbbInit init = new SzbbInit();
		User user = getUser(request);// 用户对象

		String userStatus=user.getUserStatus();
		
		if (!("xy".equalsIgnoreCase(userStatus) || "xx".equalsIgnoreCase(userStatus) || "sy".equalsIgnoreCase(userStatus))) {
			String msg = "本模块只能由"+Base.YXPZXY_KEY+"、书院或者学校用户进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ============= 初始化各变量的值 begin ============
		/*init.initSzbbSearch(rForm, myForm, user, request);
		
		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/szdw/" + xxpymc + "/szbb/szBzrbbSearch.jsp";
		//获取编班时间参数
		SzdwCsszService szdwCsszService = new SzdwCsszService();
		SzdwCsszForm bbsjModel = szdwCsszService.getModel(CsszUtil.SZDW_BBSJ);
		request.setAttribute("bbsjModel", bbsjModel);
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);*/
		// =================== end ===================
		if(QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> list = service.getSzBzrbbList(myForm,user);
			response.getWriter().print(JSONArray.fromObject(list));
			return null;
		}
		request.setAttribute("path", "szdw_general_szbzrbb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bzrBbList");
	}

	public ActionForward xsxxList(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();

		if(QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			List<HashMap<String,String>> list = service.getXsxxList(myForm);
			response.getWriter().print(JSONArray.fromObject(list));
			return null;
		}
		request.setAttribute("bbType",myForm.getBbType());
		request.setAttribute("bjdm",myForm.getBjdm());
		return mapping.findForward("xsxxList");
	}
	/**
	 * 查询【思政队伍编班】
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward szdwSzbb(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		User user = getUser(request);// 用户对象

		String userStatus=user.getUserStatus();

		if (!("xy".equalsIgnoreCase(userStatus) || "xx".equalsIgnoreCase(userStatus) || "sy".equalsIgnoreCase(userStatus))) {
			String msg = "本模块只能由"+Base.YXPZXY_KEY+"、书院或者学校用户进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ============= 初始化各变量的值 begin ============
//		init.initSzbbSearch(rForm, myForm, user, request);

		// 学校拼音名称
//		String xxpymc = myForm.getXxpymc();
		// 跳转路径
//		String url = "/xsgzgl/szdw/" + xxpymc + "/szbb/szbbSearch.jsp";
		//获取编班时间参数
		/*SzdwCsszService szdwCsszService = new SzdwCsszService();
		SzdwCsszForm bbsjModel = szdwCsszService.getModel(CsszUtil.SZDW_BBSJ);
		request.setAttribute("bbsjModel", bbsjModel);
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);*/
		// =================== end ===================
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			// 查询
			List<HashMap<String, String>> resultList = service.getSzbbList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "szdw_general_szbb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fdyBbList");
	}

	/**
	 * 思政队伍编办（辅导员、班主任）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward szbbSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		SzbbInit init = new SzbbInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initSzbbSearch(rForm, myForm, user, request);

		// 学校拼音名称
//		String xxpymc = myForm.getXxpymc();
		// 跳转路径
//		String url = "/xsgzgl/szdw/" + xxpymc + "/szbb/szbbSetting.jsp";
		
		String fplx=request.getParameter("fplx");
		String sydm = request.getParameter("sydm");
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// 编班类型
		request.setAttribute("fplx", fplx);
		request.setAttribute("sydm",sydm);
		FormModleCommon.setAllNjXyZyBjListForFdyBzr(request);
		// =================== end ===================

		return mapping.findForward("szbbSetting");
	}
	
	// ==================思政队伍编班 end=======================================
	
	// ================== 统计查询 begin =================================

	/**
	 * 页面跳转【思政人员统计】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward tjcxSzry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		SzryInit init = new SzryInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initSzrySearch(rForm, myForm, user, request);
	
		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/szdw/" + xxpymc + "/tjcx/szry/szryStat.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		//searchModel.setSearch_tj_bbzt(new String[]{"1"});
		request.setAttribute("searchTj", searchModel);
		
		// =================== end ===================

		return new ActionForward(url, false);
	}
	

	/**
	 * 页面跳转【部门情况统计】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward tjcxBmqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService service = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		BmqkInit init = new BmqkInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initBmqkSearch(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/szdw/" + xxpymc + "/tjcx/bmqk/bmqkStat.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	// ================== 统计查询 end ===================================
	
	
	
	/**
	 * 
	 * 部门情况统计（详细信息）
	 * by : quph
	 */
	public ActionForward getBmtjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
//		RequestForm rForm = new RequestForm();
//		
//		// ==================高级查询相关========================
//		rForm.setPath("szdw_general_tjcx_bmqk.do");
//		SearchModel searchModel = service.setSearchModel(rForm, request);
//		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================
		
		List<HashMap<String, String>> bjqkList = service.getBmtjInfo(myForm);
		
		request.setAttribute("bjqkList", bjqkList);
		return mapping.findForward("getBjqkInfo");
	}
	
	/**
	 * 
	 * 带班辅导员情况统计（详细信息）
	 * by : Huang Chenji
	 */
	public ActionForward getFdyInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		List<HashMap<String, String>> fdyList = service.getFdyInfo(myForm);
		
		request.setAttribute("fdyList", fdyList);
		return mapping.findForward("getFdyInfo");
	}
	
	/**
	 * 
	 * 带班班主任情况统计（详细信息）
	 * by : Huang Chenji
	 */
	public ActionForward getBzrInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		List<HashMap<String, String>> bzrList = service.getBzrInfo(myForm);
		
		request.setAttribute("bzrList", bzrList);
		return mapping.findForward("getBzrInfo");
	}

	/**
	 * 
	 * 部门情况统计（详细信息）导出
	 * by : quph
	 */
	public ActionForward exportBmtjInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exportBmtjInfo(response.getOutputStream(),myForm);
		
		return null;
	}
	
	/**
	 * 
	 * 编班辅导员情况统计（详细信息）导出
	 * by : Huang chenji
	 */
	public ActionForward exportFdyInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exportFdyInfo(response.getOutputStream(),myForm);
		
		return null;
	}
	
	/**
	 * 
	 * 编班班主任情况统计（详细信息）导出
	 * by : Huang chenji
	 */
	public ActionForward exportBzrInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BmqkService service = new BmqkService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exportBzrInfo(response.getOutputStream(),myForm);
		
		return null;
	}
	
	/**
	 * 思政人员统计--详细数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSzryInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzryService service = new SzryService();
		RequestForm rForm = new RequestForm();
		
		if (!StringUtil.isNull(myForm.getXb())){
			myForm.setXb(new String(myForm.getXb().getBytes("ISO-8859-1"),"GBK"));
		}
		
		// ==================高级查询相关========================
		rForm.setPath("szdw_general_tjcx_szry.do");
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		List<HashMap<String, String>> szryList = service.getSzryInfo(myForm);
		
		request.setAttribute("szryList", szryList);
		return mapping.findForward("getSzryInfo");
	}
	
	
	/**
	 * 
	 * 思政人员统计（详细信息）导出
	 * by : quph
	 */
	public ActionForward exportSzryInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzryService service = new SzryService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exportSzrytjInfo(response.getOutputStream(),myForm);
		
		return null;
	}
	public ActionForward setQQqh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwGeneralForm model = (SzdwGeneralForm)form;
		SzbbService service = new SzbbService();
		String bjdm = request.getParameter("bjdm");
		String nj1 =  request.getParameter("nj");
		String xymc1 =  request.getParameter("xymc");
		String zymc1 =  request.getParameter("zymc");
		String rs1 =  request.getParameter("rs");
		/*String fdyxm1 = request.getParameter("fdyxm");
		String bzrxm1 = request.getParameter("bzrxm");*/
		String bjmc = service.getBjmcForBjdm(bjdm);
		String qqqh = service.getCxQQqh(bjdm);
		request.setAttribute("bjdm", bjdm);
		request.setAttribute("bjmc", bjmc);
		request.setAttribute("qqqh", qqqh);
		request.setAttribute("nj", nj1);
		request.setAttribute("xymc", xymc1);
		request.setAttribute("zymc", zymc1);
		request.setAttribute("rs", rs1);
		/*request.setAttribute("fdyxm", fdyxm1);
		request.setAttribute("bzrxm", bzrxm1);	*/	
		if (SAVE.equals(model.getType())) {
			boolean result = service.setQQqh(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		
		return mapping.findForward("setQQqh");
	}
}
