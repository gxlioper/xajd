/**
 * @部门:学工产品事业部
 * @日期：2013-6-17 上午09:09:24 
 */
package com.zfsoft.xgxt.qgzx.xsgw;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.gwgl.QgzxGwglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学管理模块
 * @类功能描述: 学生岗位-我的岗位申请
 * @作者： zhangjw
 * @时间： 2013-6-17 上午09:09:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WdgwsqAction extends SuperAction {

	private static final String CJFF = "cjff";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;

	
	
	/**
	 * @描述:TODO我的岗位申请 高级查询
	 * @作者：zhangjw
	 * @日期：2013-6-17 上午11:49:19
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	
	public ActionForward wdgwsqCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		if(Base.xxdm.equals("10704") && getUser(request).getUserStatus().equals("stu")){//西安科技大学个性化且学生用户登录
			request.setAttribute("isTg", service.isTgInOneYear(getUser(request).getUserName())== true?"1":"0");//判断学生用户是否在一年内是否在一年内
		}
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", csszService.setZjmrCs(request));

		String path = "qgzx_wdgwsq.do?method=wdgwsqCx";
		request.setAttribute("path", path);
		QgCommUtilf.setCzpath(request, null);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wdgwsqCx");
	}

	public ActionForward wdgwsqjlCx(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getWdgwsqjlList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_wdgwsq.do?method=wdgwsqjlCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		HashMap<String,String> map = new QgzxCsszService().getKfsqMap();
		request.setAttribute("sfkfsq",map.get("sfkfxndwgwsq"));
		return mapping.findForward("gwsqjlCxStu");
	}

	public ActionForward wdqggwCx(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getWdGwList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qgzx_wdgwsq.do?method=wdqggwCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wdqggwCx");
	}
	public ActionForward lzsq(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		if(SAVE.equals(model.getType())){
			User user = getUser(request);
			model.setXh(user.getUserName());
			boolean flag = service.saveLzSq(model);
			String msg = flag ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(msg));
			return null;
		}
		request.setAttribute("model",service.getGwxxByGwdm(model.getGwdm()));
		return mapping.findForward("lzsq");
	}
	public ActionForward gwmx(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		User user = getUser(request);
		WdgwsqService service = new WdgwsqService();
		request.setAttribute("model",service.getGwxxByXhAndGwdm(model.getGwdm(),user.getUserName()));
		return mapping.findForward("gwmx");
	}
	public ActionForward gzmx(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		User user = getUser(request);
		WdgwsqService service = new WdgwsqService();
		request.setAttribute("model",service.getGwxxByXhAndGwdm(model.getGwdm(),user.getUserName()));
		request.setAttribute("gzmxList",service.getGzmxList(model.getGwdm(),user.getUserName()));
		return mapping.findForward("gzmx");
	}
	/**
	 * 
	 * @描述:查询勤工助学学生表
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-30 上午08:42:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public ActionForward showQgzxStudents(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getQgzxStuList(
				model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}

	// =============================学生申请=========================//
	/**
	 * 
	 * @描述:学生岗位申请 学生页面
	 * @作者：张小彬[工号：1036]
	 */
	
	public ActionForward wdgwsqCxStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		User user = getUser(request); // 当前登录学生

		String userType = user.getUserType();// 该模块只允许学生访问
		if (!"stu".equalsIgnoreCase(userType)) {
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		/*if ("yes".equals(new QgzxCsszService().getCssz().get("sfxyzgsc"))
				&& !service.isQgzxStu(user.getUserName())) {
			request.setAttribute("isQgzxStu", "no");
		}*/
		request.setAttribute("curXn", Base.currXn);
		request.setAttribute("curXq", Base.getXqmcForXqdm(Base.currXq));
		String path = "qgzx_wdgwsq.do?method=wdgwsqCxStu";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", csszService.setZjmrCs(request));
		HashMap<String,String> map = new QgzxCsszService().getKfsqMap();
		request.setAttribute("sfkfsq",map.get("sfkfxsgwsq"));
		return mapping.findForward("wdgwsqCxStu");
	}

	/**
	 * 
	 * @描述:数据查询
	 * @作者：张小彬[工号：1036]
	 */
	
	public ActionForward wdgwxzCxAjaxStu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		User user = getUser(request); // 当前登录学生

		String type = model.getType();

		if (org.apache.commons.lang.StringUtils.equals(type, "wsq")) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> data = service.getGwsqPageListStu(
					model, user);
			JSONArray dataList = JSONArray.fromObject(data);
			response.getWriter().print(dataList);
			return null;
		} else if (org.apache.commons.lang.StringUtils.equals(type, "ysq")) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> data = service.getGwsqPageListStuYsq(
					model, user);
			JSONArray dataList = JSONArray.fromObject(data);
			response.getWriter().print(dataList);
			return null;
		}

		return null;
	}
	public ActionForward getGwList(ActionMapping mapping,
										 ActionForm form, HttpServletRequest request,
										 HttpServletResponse response) throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		User user = getUser(request); // 当前登录学生
		// ==================高级查询相关========================
		CommService cs = new CommService();
		SearchModel searchModel = cs.getSearchModel(request);
		model.setSearchModel(searchModel);
		// ==================高级查询相关 end========================
		List<HashMap<String, String>> data = service.getGwList(
				model, user);
		JSONArray dataList = JSONArray.fromObject(data);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * 
	 * @描述: 学生申请
	 * @作者：张小彬[工号：1036]
	 */
	
	public ActionForward wdgwSqStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		User user = getUser(request);
		szXsxx(request, user.getUserName());
		String path = "qgzx_wdgwsq.do?method=wdgwSqStu";
		request.setAttribute("path", path);
		model.setXh(user.getUserName());
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("xxdm", Base.xxdm);
		
		WdgwsqService service = new WdgwsqService();
		List<HashMap<String, String>> qgxmList= service.getQgmxList(model.getXh());
		request.setAttribute("qgxmList", new QjjgService().getQjxmList()); //和日常事务当中的课程时间一直
		request.setAttribute("qgmxList", qgxmList);
		request.setAttribute("qgxmSize", qgxmList.size());
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", StringUtils.formatData(csszService.setZjmrCs(request)));
		this.saveToken(request);
		return mapping.findForward("wdgwSqStu");

	}
	public ActionForward xsgwxxCk(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String gwdm = request.getParameter("gwdm");
		WdgwsqService service = new WdgwsqService();
		request.setAttribute("model",service.getGwxxByGwdm(gwdm));
		return mapping.findForward("xsgwxxCk");
	}
	// =============================学生申请=========================//
	/**
	 * @描述: 我的岗位申请（TODO表单传值需与↓方法【QgzxStuSq】↓同步）
	 * @作者：zhangjw
	 * @日期：2013-6-17 下午02:30:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-学生岗位申请-增加:XH:{xh},GWDM:{gwdm},SQLY:{sqly}")
	public ActionForward wdgwSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		String xh = request.getParameter("xh");
		User user = getUser(request);
		
		if ("stu".equals(user.getUserType())) {
			xh = user.getUserName();
			model.setXh(user.getUserName());
		}
		if(SAVE.equalsIgnoreCase(model.getType()) || "submit".equalsIgnoreCase(model.getType())){
//			if (!isTokenValid(request)){
//				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//				return null;
//			} else {
//				super.resetToken(request);
//			}
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.saveSq(model);
			/*String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();

			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			if(mxrq != null &&mxrq.length!=0){
				service.saveQgmx(model, mxrq, mxxmList);
			}*/
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if ("submit".equalsIgnoreCase(model.getType())) {
			String result = service.saveSq(model, "submit");
			
			/*String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();
			
			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			if(null!=mxrq&&mxrq.length!=0){
				service.saveQgmx(model, mxrq, mxxmList);
			}*/
			String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
					: "true".equals(result) ? MessageKey.SYS_SUBMIT_SUCCESS
							: MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// 设置学生基本信息
		if (user.getUserStatus().equalsIgnoreCase("stu")) {
			szXsxx(request, user.getUserName());
		} else {
			szXsxx(request, xh);
		}
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", csszService.setZjmrCs(request));
		request.setAttribute("xxdm", Base.xxdm);
		String path = "qgzx_wdgwsq.do?method=wdgwSq";
		if ( "yes".equals(new QgzxCsszService().getCssz().get("sfxyzgsc"))) {
			String qgzxPath = "qgzx_wdgwsq.do?method=QgzxStuSq";
			request.setAttribute("path", qgzxPath);
		} else {
			request.setAttribute("path", path);
		}
		List<HashMap<String, String>> qgxmList= service.getQgmxList(xh);
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());//和日常事务当中的课程时间一致
		request.setAttribute("qgmxList", qgxmList);
		request.setAttribute("qgxmSize", qgxmList.size());
		request.setAttribute("model", StringUtils.formatData(model));
		/*if("10344".equals(Base.xxdm)){
			if(StringUtils.isNotNull(xh)){
				KnsjgService knsjgService = new KnsjgService();
				List<HashMap<String, String>> knsInfoList = knsjgService
						.getKnsInfoList(xh);
				request.setAttribute("knsInfoList", knsInfoList);
			}
		}*/
		/**
		 * 以下代码是为了防止表单重复提交而做的处理
		 */
		/*********************start**********************************/
		//用户请求访问该申请页面时，向页面置一个时间戳标记
		this.saveToken(request);
		/*********************start**********************************/
		return mapping.findForward("wdgwSq");
	}

	/**
	 * 
	 * @描述: 需要勤工资格审查学生岗位申请选择学生专用（TODO表单传值需与↑方法【wdgwSq】↑一致）
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-30 上午11:48:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	
	public ActionForward QgzxStuSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		String xh = request.getParameter("xh");
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			xh = user.getUserName();
			model.setXh(user.getUserName());
		}
//		if (SAVE.equalsIgnoreCase(model.getType())) {
//			boolean result = service.saveSq(model);
//			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
//					: MessageKey.SYS_SAVE_FAIL;
//			response.getWriter().print(getJsonMessageByKey(messageKey));
//			return null;
//		} else if ("submit".equalsIgnoreCase(model.getType())) {
//			String result = service.saveSq(model, "submit");
//			String messageKey = MessageKey.SYS_SELECT_SHLC_FAIL.equals(result) ? MessageKey.SYS_SELECT_SHLC_FAIL
//					: "true".equals(result) ? MessageKey.SYS_SUBMIT_SUCCESS
//							: MessageKey.SYS_SUBMIT_FAIL;
//			response.getWriter().print(getJsonMessageByKey(messageKey));
//			return null;
//		}

		// 设置学生基本信息
		if (user.getUserStatus().equalsIgnoreCase("stu")) {
			szXsxx(request, user.getUserName());
		} else {
			if (service.isQgzxStu(xh)) {
				szXsxx(request, xh);
			}
			else{
				szXsxx(request, "");
			}
		}
		QgzxGwglService csszService = new QgzxGwglService();
		request.setAttribute("cssz", csszService.setZjmrCs(request));
		request.setAttribute("xxdm", Base.xxdm);

		List<HashMap<String, String>> qgxmList= service.getQgmxList(xh);
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());//和日常事务当中的课程时间一致
		request.setAttribute("qgmxList", qgxmList);
		request.setAttribute("qgxmSize", qgxmList.size());
		
		String qgzxPath = "qgzx_wdgwsq.do?method=QgzxStuSq";
		request.setAttribute("path", qgzxPath);
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("wdgwSq");
	}

	/**
	 * 
	 * @描述:修改
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-11 下午02:35:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemLog(description = "访问勤工助学-岗位人员管理-学生岗位申请-增加:XH:{xh},GWDM:{gwdm},SQLY:{sqly}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		User user = getUser(request);
		String xh = request.getParameter("xh");
		String xssqkg = request.getParameter("xssqkg");

		if ("stu".equals(user.getUserType())) {
			xh = user.getUserName();
			model.setXh(user.getUserName());
		}
		if (UPDATE.equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_WTJ);
			boolean result = service.update(model);
			/*String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();

			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}

			if(null!=mxrq&&mxrq.length!=0){
				service.saveQgmx(model, mxrq, mxxmList);
			}*/
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		} else if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);
			boolean result = service.update(model);
			/*String[] mxrq = request.getParameterValues("mxrq");
			List<String[]> mxxmList = new ArrayList<String[]>();

			if (mxrq != null && mxrq.length > 0){
				for (int i = 0 ; i < mxrq.length ; i++){
					String[] mxxm = request.getParameterValues("mxxm"+i);
					mxxmList.add(mxxm);
				}
			}
			if(null!=mxrq&&mxrq.length!=0){
				service.saveQgmx(model, mxrq, mxxmList);
			}*/
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// 设置学生基本信息
		if (user.getUserStatus().equalsIgnoreCase("stu")) {
			szXsxx(request, user.getUserName());
		} else {
			szXsxx(request, xh);
		}
		WdgwsqForm modelN = service.getModel(model);
		if("10344".equals(Base.xxdm) || "11488".equals(Base.xxdm)){
			WdgwsqForm tempmodel = new WdgwsqDAO().getModel(model.getSqbh());
			request.setAttribute("tempmodel", StringUtils.formatData(tempmodel));
		}
		
		// 982
		// 酬金上限
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
//		HashMap<String, String> map = qgzxCsszService.getCssz();
		String isshow = "false";
//		String sfsdgwcjsx = map.get("sfsdgwcjsx");
		// 如果设置了酬金上限
//		if ("yes".equals(sfsdgwcjsx)) {
//			isshow = "true";
//			// 如果当前岗位未设置酬金上限，则显示基本配置的酬金上限
//			if (StringUtils.isNull(modelN.getGwcjsx())) {
//				modelN.setGwcjsx(map.get("gwzgcjsx"));
//			}
//		}
		QgzxCsszService cssz = new QgzxCsszService();
		request.setAttribute("cssz", cssz.getCssz());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("isshow", isshow);
		request.setAttribute("model", StringUtils.formatData(modelN));
		request.setAttribute("xssqkg", xssqkg);
		request.setAttribute("qgxmList", new QjjgService().getQjxmList());
		request.setAttribute("qgmxList", service.getQgmxList(xh));
		if("10344".equals(Base.xxdm)){
			if(StringUtils.isNotNull(xh)){
				KnsjgService knsjgService = new KnsjgService();
				List<HashMap<String, String>> knsInfoList = knsjgService
						.getKnsInfoList(xh);
				request.setAttribute("knsInfoList", knsInfoList);
			}
		}
		QgCommUtilf.setCssz(request);
		return mapping.findForward("wdgwxg");
	}

	public ActionForward qglsjl(ActionMapping mapping, ActionForm form,
							 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		WdgwsqService service = new WdgwsqService();
		List<HashMap<String,String>> list = service.getQglsjl(xh);
		request.setAttribute("qglsjlList",list);
		return mapping.findForward("qglsjl");
	}
	@SystemLog(description = "访问勤工助学-岗位人员管理-学生岗位申请-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqService service = new WdgwsqService();
		String values = request.getParameter("values");
		int result = service.runDelete(values.split(","));
		String messageKey = null;
		if (result > 0) {
			messageKey = ShlcUtil.deleteSpxx(values.split(",")) ? MessageKey.SYS_DEL_SUCCESS
					: MessageKey.SYS_DEL_FAIL;
		} else {
			messageKey = MessageKey.SYS_DEL_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @描述:TODO学生岗位申请 参数验证
	 * @作者：zhangjw
	 * @日期：2013-6-19 下午04:11:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward yzgwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		response.getWriter().print(service.yzcssz(model));
		return null;
	}

	/**
	 * @描述:TODO获取学生基本信息
	 * @作者：zhangjw
	 * @日期：2013-6-17 下午02:30:28
	 * @param request
	 * @param xh
	 *            void 返回类型
	 */
	
	private void szXsxx(HttpServletRequest request, String xh) {
		// 查询学生信息
		if (xh != null && !"".equals(xh)) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
	}

	/**
	 * @描述:TODO选择岗位高级查询
	 * @作者：zhangjw
	 * @日期：2013-6-18 上午08:56:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	
	public ActionForward wdgwxzCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String tzlx = "tz";
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		String lx = request.getParameter("lx");
		String path = "qgzx_wdgwsq.do?method=wdgwxzCx";
		// 标志为调整类型
		if (StringUtils.isNotNull(lx) && lx.equals(tzlx)) {
			path += "&lx=" + tzlx;
			request.setAttribute("lx", tzlx);
			WdgwsqForm wf = service.getModel(model);
			model.setYrdwdm(wf.getYrdwdm());
		}
		if (QUERY.equals(model.getType())) {

			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getGwPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
//		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);

		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("wdgwxzCx");
	}

	/**
	 * @描述:TODO岗位信息展示
	 * @作者：zhangjw
	 * @日期：2013-6-18 上午08:55:53
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	
	public ActionForward gwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		int xszggwsl = service.getXszggwsl(model);
		int xssqsl = service.getXssqsl(model);
		String message = "";
		if (xszggwsl > 0) {
			message = "此岗位该学生已经在岗";
		}
		if (xssqsl > 0) {
			message = "此岗位该学生已经申请";
		}
		// 982
		// 酬金上限
//		QgzxCsszService qgzxCsszService = new QgzxCsszService();
//		HashMap<String, String> map = qgzxCsszService.getCssz();
//		request.setAttribute("cssz", map);
//		String isshow = "false";
//		String sfsdgwcjsx = map.get("sfsdgwcjsx");
		xsgzgl.qgzx.gwglnew.QgzxGwglService qgzxGwglService = new xsgzgl.qgzx.gwglnew.QgzxGwglService();
		HashMap<String,String> gwxx = qgzxGwglService.getGwxxByGwdm(model.getGwdm());
		/*if("10351".equals(Base.xxdm)){//温州大学个性化
			model.setSqxyms(service.getXyms(model));
		}*/
		/*// 如果设置了酬金上限
		if ("yes".equals(sfsdgwcjsx)) {
			isshow = "true";
			// 如果当前岗位未设置酬金上限，则显示基本配置的酬金上限
			if (StringUtils.isNull(model.getGwcjsx())) {
				model.setGwcjsx(map.get("gwzgcjsx"));
			}
		}*/
//		request.setAttribute("isshow", isshow);
		request.setAttribute("message", message);
		request.setAttribute("model", gwxx);
//		request.setAttribute("lx", request.getParameter("lx"));
	//	request.setAttribute("xxdm", Base.xxdm);
//		if (StringUtils.equals(request.getParameter("type"), "stu")) {
//			request.setAttribute("userlx", "stu");
//		}
		QgCommUtilf.setCssz(request);
		return mapping.findForward("gwxx");
	}

	/**
	 * @描述:TODO取消审核
	 * @作者：zhangjw
	 * @日期：2013-6-18 上午08:59:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	
	public ActionForward qxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqbhs = request.getParameter("sqbhs");
		String[] sqbh = sqbhs.split(",");
		if (sqbhs == null || "".equals(sqbhs) || sqbh.length < 1) {
			response.getWriter().print(
					getJsonMessageByKey(MessageKey.SYS_CANCEL_NULL));
		} else {
			WdgwsqService service = new WdgwsqService();
			WdgwsqForm model = (WdgwsqForm) form;
			boolean result = false;
			for (String sqid : sqbh) {
				model.setShzt("9");
				model.setSqbh(sqid);
				ShlcInterface shlc = new CommShlcImpl();
				result = shlc.deleteShjl(sqid);
				int i = service.runDelete(new String[] { model.getSqbh() });
				if (i > 0) {
					result = true;
				}
			}
			String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
					: MessageKey.SYS_CANCEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		return null;
	}

	
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqService service = new WdgwsqService();

		String values = request.getParameter("values");
		//String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		String gwdm = request.getParameter("gwdm");
		// 获取审批流程
		/*String splc = service.getShlcID();
		String bmlb = service.getYrdwlb(gwdm);
		if("5".equals(bmlb))
			splc = service.getYjShlcID();*/
		String splc = "";
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if(service.isXndw(gwdm)){
			splc = splcMap.get("xsgwsqsplc");
		} else {
			splc = splcMap.get("xwxsgwsqsplc");
		}
		boolean result = service.submitRecord(values, splc, xh);
		if (result) {
			// 更新业务状态为'审核中'
			WdgwsqForm model = new WdgwsqForm();
			model.setSqbh(values);
			model.setShzt(Constants.YW_SHZ);
			model.setSplc(splc);
			// 暂时去掉
			// ShlcDao shlcDao = new ShlcDao();
			// model.setShgw(shlcDao.getFirstGwid(lcid));
			// 清空审核岗位
			model.setShgw("");
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		WdgwsqService service = new WdgwsqService();
		boolean result = false;
		result = service.cancleRecord(values, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			WdgwsqForm model = new WdgwsqForm();
			model.setSqbh(values);
			model.setShzt(Constants.YW_WTJ);
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	public ActionForward sqlyModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("sqlyModel");
	}
	

	/**
	 * 
	 * @描述:勤工助学学生申请结果导出
	 * @作者：cq [工号：785]
	 * @日期：2013-10-16 下午04:36:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	
	public ActionForward qgsqExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm model = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// 结果集
		List<HashMap<String, String>> resultList = service.getPageList(model,
				user);
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 * 
	 * @描述:勤工助学岗位报名表下载
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-6-20 上午09:50:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	
	public ActionForward printGwbmb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqService service = new WdgwsqService();
		WdgwsqForm myForm = (WdgwsqForm) form;

		XsxxService xsxxService = new XsxxService();
		if (StringUtils.isNotNull(myForm.getSqbh())) {
			List<File> files = new ArrayList<File>();
			String[] sqbhs = myForm.getSqbh().split(",");
			for (String sqbhid : sqbhs) {
				myForm.setSqbh(sqbhid);
				WdgwsqForm model = service.getModel(myForm);
				Map<String, Object> data = new HashMap<String, Object>();
				// 加载学生基本信息
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				data.putAll(xsjbxx);
				HashMap<String, String> qsxx= service.getQsxx(model.getXh());
				String qsbh=null;
				if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
					qsbh="";
				}else{
					qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
				}
				//查询贫困生
				String pkxdj=service.getPkxjb(model.getXh());
				data.put("qsbh", qsbh);
				data.put("gwmc", model.getGwmc());
				data.put("pkxdj", pkxdj);
				
				//潍坊学院个性化
				if("11067".equals(Base.xxdm)){
					data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
					data.put("sqsj", DateTranCnDate.fomartDateToCn(model.getSqsj(),FomartDateType.day));// 申请时间
					//转换为中文日期格式
					data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"),FomartDateType.month));// 出生日期
					// 加载学生的困难生
					KnsjgService knsjgService = new KnsjgService();
					List<HashMap<String, String>> knsInfoList = knsjgService.getKnsInfoList(xsjbxx.get("xh"));
					if(knsInfoList.size() > 0){
						data.put("knsdcmc", knsInfoList.get(0).get("dcmc"));
					}else{
						data.put("knsdcmc", "");
					}
				}
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("wdgwsq_sqly", sqly);// 申请理由
				//学生照片
				InputStream is = xsxxService.getPhotoStream(model.getXh());
				File photoFile = FileUtil.inputstreamToFile(is, "doc");
				String photo = FileUtil.getBASE64(photoFile);
				if (StringUtil.isNull(photo)){
					//取默认照片
					photo = xsxxService.getDefaultPhotoBase64(request);
				}
				if(photo == null){
					photo = "";
				}
				data.put("xsxx_photo", photo);
				// 担任职务
				DwwhService dwwhService = new DwwhService();
				data.put("zwmc", dwwhService.getZwForXh(model.getXh()));
				// ========== 可参加勤工助学时间 begin ========
				WdgwsqService wdgwsqService = new WdgwsqService();
				String[] qgrqArr = new String[]{ "一", "二", "三", "四", "五" }; // 星期一, 二, 三, 四, 五
				String[] qgmxArr = new String[]{ "2", "3", "4", "5" }; // 1-2节、3-4节、5-6节、7-8节
				HashMap<String, String> qgzxsjMap = wdgwsqService.getQgmxByQgrqQgmx(qgrqArr, qgmxArr, model.getXh());
				data.putAll(qgzxsjMap);
				// ========== 可参加勤工助学时间 end ========
				// ========= 家庭情况信息 begin ========
				JtqkdcService jtqkService = new JtqkdcService();
				JtqkdcForm jtqkdcForm = new JtqkdcForm();
				jtqkdcForm.setXh(model.getXh());
				JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
				String jtqk_xx = "";
				if (jtqkmodel != null) {
					jtqk_xx = "家庭人均月收入: " + jtqkmodel.getJtrjysr()
								+ " 元， 家庭月总收入: " + jtqkmodel.getSnjtsr()
								+ " 元， 家庭人均年收入: " + jtqkmodel.getJtrjsr()
								+ " 元， 家庭年总收入: " + jtqkmodel.getJtnzsr()
								+ " 元， 家庭收入来源: " + jtqkmodel.getJtsrly();
					data.put("jtqk", jtqkmodel);
				}
				data.put("jtqk_xx", jtqk_xx); 
				
				List<HashMap<String, String>> cyList4line = jtqkService.getJtcyList(model.getXh(),4);
				data.put("cyList4line", cyList4line);
				
				// ========= 家庭情况信息 end ========
				File file = service.printForWord(data);
				files.add(file);
			}
			if (files != null && files.size() > 1) {
				File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
				FileUtil.outputZip(response, zipFile);
			} else {
				FileUtil.outputWord(response, files.get(0));
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:浙江中医药勤工报名表打印
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-19 上午11:54:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getQgbmb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm myForm = (WdgwsqForm) form;
		File wordFile = getQgbmWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述:勤工报名表打印获取word
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-19 上午11:54:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getQgbmWord(WdgwsqForm myForm,HttpServletRequest request) throws Exception{

		//String xh = myForm.getXh();
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
	    WdgwsqService service = new WdgwsqService();
	    String xh = service.getModel(myForm.getSqbh()).getXh();
		List<HashMap<String, String>> qgmxList = new ArrayList<HashMap<String,String>>();;//service.getKxsjMx(xh);
		if(null == qgmxList || qgmxList.size()<7){
			
//			int kswz = 0;
//			if(null ==qgmxList){
//				kswz = 1;
//			}else{
//				 kswz = qgmxList.size()+1;
//			}
			for (int i = 1; i <= 7; i++) {
				HashMap<String, String> lsmnap = new HashMap<String, String>();
				lsmnap.put("qgrq", StringUtils.numberToChinese(i));
				lsmnap.put("sw", "0");
				lsmnap.put("xw", "0");
				lsmnap.put("ws", "0");
				qgmxList.add(lsmnap);
			}
			
			
		}
		HashMap<String, String> bmbxx = service.getBmbxx(myForm.getSqbh());
		data.put("qgmxList", qgmxList);
		
		bmbxx.put("yhtc", HtmlUtil.xmlZy(bmbxx.get("yhtc")));
		bmbxx.put("jtqk", HtmlUtil.xmlZy(bmbxx.get("jtqk")));
		bmbxx.put("qgzxrs", HtmlUtil.xmlZy(bmbxx.get("qgzxrs")));
		bmbxx.put("year", GetTime.getTimeByFormat("yyyy"));
		bmbxx.put("mon", GetTime.getTimeByFormat("mm"));
		bmbxx.put("day", GetTime.getTimeByFormat("dd"));
		data.putAll(bmbxx);
//		data.put("xyzsjg", myForm);
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//qgzx","qgzxgwbmb.xml",xh+bmbxx.get("xm"));
		
		return file;
	}
	
	/**
	 * 
	 * @描述:浙江中医药批量打印勤工报名
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-19 上午11:55:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getQgbmbTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WdgwsqForm myForm = (WdgwsqForm) form;
		String value = request.getParameter("value");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i=i+1){
				myForm.setSqbh(values[i]);
				File file = getQgbmWord(myForm,request);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		
		return null;
	}
	
	/** 
	 * @描述:检测该学生是否退岗满一年(西安科技大学专用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-20 下午05:37:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward checkSfTg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		WdgwsqForm myForm = (WdgwsqForm) form;
		WdgwsqService service = new WdgwsqService();
		String result = service.isTgInOneYear(myForm.getXh())==true?"1":"0";//1：未退岗满一年  0：退岗满一年
		response.getWriter().print(getJsonMessage(result));
		return null;
	}

}
