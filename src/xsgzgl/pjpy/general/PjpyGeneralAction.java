package xsgzgl.pjpy.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.index.PjpyIndexInit;
import xsgzgl.pjpy.general.pjsz.bjdl.PjszBjdlInit;
import xsgzgl.pjpy.general.pjsz.cpxz.PjszCpxzInit;
import xsgzgl.pjpy.general.pjsz.pjry.PjszPjryInit;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmInit;
import xsgzgl.pjpy.general.pjsz.zcxm.PjszZcxmInit;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.xmsz.pjtj.XmszPjtjInit;
import xsgzgl.pjpy.general.xmsz.rssz.XmszRsszInit;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_通用_Action类
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

public class PjpyGeneralAction extends BasicAction {
	
	// =================评奖首页==================================
	
	/**
	 * 评奖评优_首页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjpyIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyIndexInit init = new PjpyIndexInit();
		User user = getUser(request);// 用户对象

		String userType=user.getUserType();
		
		if(!"xx".equalsIgnoreCase(userType) && !"admin".equalsIgnoreCase(userType)){
			
			String msg = "本模块只能由管理员或者学校用户进行操作，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// ============= 初始化各变量的值 begin ============
		init.initIndex(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/init/pjpyIndex.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 评奖评优_首页_评奖流程设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjlcSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyIndexInit init = new PjpyIndexInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initIndex(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/init/pjlcSetting.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 评奖评优_首页_开始新评奖
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjpyStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyIndexInit init = new PjpyIndexInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initIndex(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/init/pjpyStart.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 评奖评优_首页_结束本次评奖
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjpyEnd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyIndexInit init = new PjpyIndexInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjpyEnd(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/init/pjpyEnd.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================评奖首页 end================================

	// =================评奖设置 begin==================================

	/**
	 * 评奖评优_评奖设置_评奖人员设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjszPjry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszPjryInit init = new PjszPjryInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjry(rForm, myForm, user, request);
		
		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/pjry/pjryManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 评奖评优_评奖设置_参评小组设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjszCpxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszCpxzInit init = new PjszCpxzInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initCpxz(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/cpxz/cpxzManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 评奖评优_评奖设置_综测项目设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjszZcxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszZcxmInit init = new PjszZcxmInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initZcxm(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/zcxm/zcxmManage.jsp";
		// ============= 初始化各变量的值 end ==============
		
		// 综测项目以横向展示
		if("2".equalsIgnoreCase(myForm.getZcxmzs())){
			url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/zcxm/zcxmByHx.jsp";
		}

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_评奖设置_评奖项目设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjszPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszPjxmInit init = new PjszPjxmInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjxm(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/pjxm/pjxmManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_评奖设置_班级大类设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjszBjdl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszBjdlInit init = new PjszBjdlInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initBjdl(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/bjdl/bjdlManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_评奖设置_评奖项目设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjxmSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszPjxmInit init = new PjszPjxmInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjxm(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/pjxm/pjxmSetting.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_评奖设置_评奖项目设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward pjxmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjszPjxmInit init = new PjszPjxmInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjxm(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/pjsz/pjxm/pjxmUpdate.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================评奖设置 end==================================

	// =================项目设置 begin================================
	
	/**
	 * 评奖评优_项目设置_评奖条件设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmszPjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		XmszPjtjInit init = new XmszPjtjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjtj(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/xmsz/pjtj/pjtjSetting.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_项目设置_评奖人数设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmszRssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		XmszRsszInit init = new XmszRsszInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initRssz(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/xmsz/rssz/rsszManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================项目设置 end==================================
	
	// =================综合测评==================================
	
	/**
	 * 评奖评优_综合测评_维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zhcpMaintain(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return zhcpZcxx(mapping, form, request, response);
	}
	
	/**
	 * 评奖评优_综合测评_综测分维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zhcpZcxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyZhcpInit init = new PjpyZhcpInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initZhcp(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/zhcp/zhcpManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		
		return new ActionForward(url, false);
	}
	
	/**
	 * 综合测评_基本设置_综测分结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhcpResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyZhcpInit init = new PjpyZhcpInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initZhcpResult(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/zhcp/zhcpResult.jsp";
		
		// ============= 设置高级查询 =============
		// 评奖学年
		String pjxn = myForm.getPjxn();
		// 评奖学期
		String pjxq = myForm.getPjxq();
		// 评奖年度
		String pjnd = myForm.getPjnd();
		// 高级查询
		SearchModel searchModel = myForm.getSearchModel();
		searchModel.setSearch_tj_zczq(new String[] { pjxn+"luojw"+pjxq+"luojw"+pjnd});

		request.setAttribute("searchTj", searchModel);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		
		
		String tableName="";
		
		if("ss".equalsIgnoreCase(myForm.getRyk())){		
			tableName="xg_view_pjpy_zhcpresult_ss";
		}else{
			tableName="xg_view_pjpy_zhcpresult_ryk";
		}
		request.setAttribute("tableName", tableName);
		
		request.setAttribute("realTable", "xg_pjpy_zhcpb");
		// =================== end ===================

		return new ActionForward(url, false);
	
	}
	
	/**
	 * 综合测评_基本设置_综测基本设置
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

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyZhcpInit init = new PjpyZhcpInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		myForm.getSearchModel().setPath("pjpy_general_zhcp_result.do");

		init.initKindChoose(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/zhcp/kindChoose.jsp";
		// ============= 初始化各变量的值 end ============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================综合测评 end===============================
	
	// =================历史评奖 begin ==============================
	
	/**
	 * 评奖评优_我的评奖_历史评奖【查询】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward wdpjLspj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initLspj(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/jgcx/lspjManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_我的评奖_历史评奖【维护】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward lspjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initLspjUpdate(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/jgcx/lspjUpdate.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================历史评奖 end ================================
	
	// =================我的评奖 begin ================================
	
	/**
	 * 评奖评优_我的评奖_我的评奖管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward wdpjMangage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initWdpj(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/wdpjManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_我的评奖_学生申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward wdpjXssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// 用户对象
		String userType=user.getUserType();

		// ============= 初始化各变量的值 begin ============
		init.initXssq(rForm, myForm, user, request);
		
		if(!"stu".equalsIgnoreCase(userType)){
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else if(!Base.isNull(rForm.getMessage())){
			String msg = rForm.getMessage();
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} 

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/xssq/xssqManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_我的评奖_学生申请结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward wdpjXssqJgcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initXssqJgcx(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/xssq/xssqResult.jsp";	
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		
		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_我的评奖_老师上报
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmsbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initLssb(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/lssb/xmsbManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		
		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_我的评奖_项目审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initXmsh(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/xmsh/xmshManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * 评奖评优_我的评奖_项目审核【详细】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initXmshDetail(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/xmsh/xmshDetail.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_我的评奖_本次评奖
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward wdpjBcpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		request.setAttribute("tableName", "view_xg_pjpy_bcpjjg");
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initBcpj(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/jgcx/bcpjManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 评奖评优_我的评奖_本次评奖【详细】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward bcpjDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService service = new PjpyGeneralService();
		RequestForm rForm = new RequestForm();
		PjpyWdpjInit init = new PjpyWdpjInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initBcpjDetail(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/pjpy/" + xxpymc + "/wdpj/jgcx/bcpjDetail.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================我的评奖 end ==================================
	

}
