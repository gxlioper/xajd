package xsgzgl.pjpy.general.tjcx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_通用_Action类
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

public class PjpyTjcxAction extends BasicAction {

	/**
	 * 综测班级名单【有等级考试】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="访问评奖评优-统计管理-综测统计-查询（有等级考试）")
	public ActionForward zcbjmdDjks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initZcbjmdDjks(rForm, myForm, user, request);
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/zcbjmd/djks.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		request.setAttribute("searchTj", searchModel);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 综测班级名单【无等级考试】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="访问评奖评优-统计管理-综测统计-查询（无等级考试）")
	public ActionForward zcbjmdNoDjks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initZcbjmdNoDjks(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});
		
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/zcbjmd/zcmd.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		request.setAttribute("searchTj", searchModel);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 获奖名单汇总
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="访问评奖评优-统计管理-综测统计-获奖名单汇总")
	public ActionForward hjmdhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initHjmdhz(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/hjmdhz/hjmdhz.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 获奖金额汇总
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="访问评奖评优-统计管理-综测统计-获奖金额汇总")
	public ActionForward hjjehz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initHjjehz(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/hjjehz/hjjehz.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 推荐名单汇总表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author qlj
	 * @return
	 */
	@SystemLog(description="访问评奖评优-统计管理-综测统计-推荐名单汇总")
	public ActionForward tjmdhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initTjmdhz(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/tjmdhz/tjmdhz.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 困难生奖学金推荐名单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author qlj
	 * @return
	 */
	@SystemLog(description="访问评奖评优-统计管理-综测统计-困难生奖学金推荐名单汇总")
	public ActionForward knstjmdhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initKnsTjmdhz(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/tjmdhz/knstjmdhz.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 传媒获奖名单汇总
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SystemLog(description="访问评奖评优-统计管理-综测统计-传媒获奖名单汇总")
	public ActionForward cmhjmdhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyTjcxInit init = new PjpyTjcxInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initCmhjmdhz(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/tjcx/hjjehz/cmhjmdhz.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
}
