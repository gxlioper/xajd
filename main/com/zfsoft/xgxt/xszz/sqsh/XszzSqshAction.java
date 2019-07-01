/**
 * @部门:学工产品事业部
 * @日期：2013-4-25 下午02:27:41 
 */
package com.zfsoft.xgxt.xszz.sqsh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.rcsw.ylbx.ylbxjg.YlbxjgService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgAction;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgModel;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.cxpy.CxpyService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.bbwh.BbwhService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcDao;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.xmwh.jdsz.XmwhJdszService;
import com.zfsoft.xgxt.xszz.xmwh.rssz.XmwhRsszService;
import com.zfsoft.xgxt.xszz.xmwh.shsz.XmwhShszService;
import com.zfsoft.xgxt.xszz.xmwh.tjsz.XmwhTjszService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhDao;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhForm;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;
import com.zfsoft.xgxt.xszz.xszzbjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xszz.xszzbjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xszz.xszzbjpy.jglr.JglrService;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgDao;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生资助2013版--项目申请审核
 * @作者： Penghui.Qu
 * @时间： 2013-4-25 下午02:27:41
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XszzSqshAction extends SuperAction {
	// 是否班级评议.1：是，0：否
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xszz.sfbjpy"));
	private static final String ZZXM = "zzxm";
	private static List<HashMap<String, String>> mkxxList = null;
	private static List<HashMap<String, String>> jbxxList = null;
	private static int CYSIZE = 5;// 家庭成员最大支持数量

	static {
		BdpzService bdpzService = new BdpzService();
		// 资助项目显示配置
		mkxxList = bdpzService.getBdpz(ZZXM);
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(ZZXM);
	}

	private static final String url = "xszz_sqsh_xmsq.do";

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:项目申请
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午04:48:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward xszzXmsq(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
//		 XszzSqshService service = new XszzSqshService();

		User user = getUser(request);

		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}

		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

			// 加载学生的困难生
			KnsjgService knsjgService = new KnsjgService();
			List<HashMap<String, String>> knsInfoList = knsjgService
					.getKnsInfoList(myForm.getXh());
			request.setAttribute("knsInfoList", knsInfoList);
		}

		request.setAttribute("mkxxForm", myForm);

		request.setAttribute("currXn", Base.currXn);
		request.setAttribute("currXq", Base.getDqxqmc());
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		String path = "xszz_sqsh.do?method=xszzXmsq";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("mkxxForm", StringUtils.formatData(myForm));
		this.saveToken(request);
		return mapping.findForward("xszzXmsq");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:保存项目申请
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午09:59:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemLog(description = "访问学生资助-资助管理-资助申请-保存-XH:{xh},SQLY:{sqly},XMDMARRAY:{xmdmArray}")
	public ActionForward saveXmsq(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)) {
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		XszzSqshForm myForm = (XszzSqshForm) StringUtils.formatBean(form);
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}

		if (SFBJPY_Y && "submit".equals(myForm.getType())) {
			// 评议小组人员不能申请！
			JglrService jglrService = new JglrService();
			HashMap<String, String> bjpyxzcyMap = jglrService.queryBjpyxzcy(myForm.getXh());
			if (bjpyxzcyMap.get("xh") != null) {
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
				return null;
			}
		}
		super.resetToken(request);
		XszzSqshService service = new XszzSqshService();

		String[] xmdmArray = request.getParameterValues("xmdmArray");
		//学生可申请金额资助项目，设置学生申请金额
		String[] ylzd1 = request.getParameterValues("ylzd1");
		// 保存项目申请
		boolean result = service.saveXmsq(myForm, xmdmArray, ylzd1);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(myForm.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:申请记录提交
	 * @作者：cmj[工号：913]
	 * @日期：2013-12-4 下午05:19:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemLog(description = "访问学生资助-资助管理-资助申请-提交-VALUES:{values}")
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshService service = new XszzSqshService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			xh = user.getUserName();
		}
		if (SFBJPY_Y) {
			// 评议小组人员不能申请！
			JglrService jglrService = new JglrService();
			HashMap<String, String> bjpyxzcyMap = jglrService.queryBjpyxzcy(xh);
			if (bjpyxzcyMap.get("xh") != null) {
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
				return null;
			}
		}

		XmwhTjszService tjszService = new XmwhTjszService();
		XszzSqshForm myForm = service.getModel(values);

		// ========== 验证评奖条件 begin ============
		List<HashMap<String, String>> conditions = tjszService.getTjszGl(myForm.getXmdm(), myForm.getXh());
		// 校验评奖条件，返回不符合条件的项目名称。
		CheckCondition checkCondition = new CheckStudentCondition();
		// 是否满足全部评奖条件
		boolean conditionsAllOk = checkCondition.checkConditionBoolean(myForm.getXh(), conditions);

		if (!conditionsAllOk) {
			response.getWriter().print(getJsonMessage("不符合条件！请确认！"));
			return null;
		}
		// ========== 验证评奖条件 end ============

		boolean result = true;

		if (!SFBJPY_Y) {
			HashMap<String, String> falseXmdm = service.getFalseXmdm(values);
			if (falseXmdm != null && StringUtils.isNotNull(falseXmdm.get("xmdm"))) {
				response.getWriter().print(getJsonMessage(falseXmdm.get("jgsqzq")));
				return null;
			}
			result = service.submitRecord(values, lcid, xh);
		}
		if (result) {
			//更新业务状态为'审核中'
			XszzSqshForm model = new XszzSqshForm();
			model.setGuid(values);
			if (SFBJPY_Y) {
				model.setShzt(Constants.YW_BJPYZ);
			} else {
				model.setShzt(Constants.YW_SHZ);
			}
			//更新当前时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setSqsj(dateFormat.format(new Date()));
			//根据values 查询 jgsqzq；
			String jgsqzq = service.getJgsqzq(values);
			model.setJgsqzq(jgsqzq);
			service.updateModel(model);
		}
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}


	@SystemLog(description = "访问学生资助-资助管理-资助申请-撤销-VALUES:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshService service = new XszzSqshService();
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		boolean result = true;
		if (!SFBJPY_Y) {
			result = service.cancleRecord(values, lcid);
		}
		if (result) {

			//根据guid 查询jgsqzq 
			String jgsqzq = service.getJgsqzq(values);

			//更新业务状态为'未提交'
			XszzSqshForm model = new XszzSqshForm();
			model.setGuid(values);
			model.setShzt(Constants.YW_WTJ);
			model.setJgsqzq(jgsqzq);

			service.updateModel(model);

			if (SFBJPY_Y) {
				XszzSqshForm modelNew = service.getModel(model);
				JgcxDao jgcxDao = new JgcxDao();
				String guid = modelNew.getGuid();
				String xn = modelNew.getXn();
				String xq = modelNew.getXq();
				String xmdm = modelNew.getXmdm();
				String sqr = modelNew.getXh();
				// 更新班级评议表
				boolean rs = jgcxDao.cxBjpyxzpy(xn, xq, xmdm, sqr);
				if (rs) {
					// 更新结果查询表
					JgcxForm jgcxForm = new JgcxForm();
					jgcxForm.setSqid(guid);
					jgcxForm.setTjzt("0");
					jgcxForm.setTjsj(" ");
					jgcxForm.setShzt(" ");
					jgcxDao.runUpdate(jgcxForm);
				}
			}
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:申请项目选择页
	 * @作者：Penghui.Qu
	 * @日期：2013-4-27 上午09:50:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward getXmsqInfo(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		XszzSqshService service = new XszzSqshService();

		// 已申请项目信息
		List<HashMap<String, String>> xmsqInfoList = service.getXmsqInfoList(myForm.getXh());
		request.setAttribute("xmsqInfoList", xmsqInfoList);

		// 未申请项目信息
		List<HashMap<String, String>> wsqList = service.getWsqList(myForm.getXh());
		request.setAttribute("wsqList", wsqList);

		return mapping.findForward("getXmsqInfo");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:申请管理界面
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午04:49:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq.do")
	public ActionForward xmsqManage(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);

		String path = "xszz_sqsh_xmsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		return mapping.findForward("xmsqManage");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @描述:资助项目审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午10:32:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsh.do")
	public ActionForward xmshManage(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getShjlList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);

		String path = "xszz_sqsh_xmsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("xmshManage");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:资助项目单个审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午11:24:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsh.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward zzxmDgsh(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		XszzSqshService service = new XszzSqshService();

		String xtgwid = myForm.getXtgwid();
		XszzSqshForm model = service.getModel(myForm);
		model.setShid(myForm.getShid());

		if (model != null) {
			// 学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
			// 加载学生的困难生————Num：1529 name:JZ
			if (Base.xxdm.equalsIgnoreCase("11799")) {
				KnsjgService knsjgService = new KnsjgService();
				List<HashMap<String, String>> knsInfoList = knsjgService.getKnsInfoList(model.getXh());
				request.setAttribute("knsInfoList", knsInfoList);
			}


			//获取申请项目信息
			XmwhService xmService = new XmwhService();
			XmwhForm xmwhForm = xmService.getModel(model.getDqxmdm());
			//金额可调整项目显示申请金额
			if ("1".equalsIgnoreCase(xmwhForm.getJesfxssq())) {
				model.setJe(model.getYlzd1());
			}
			request.setAttribute("xmwhForm", StringUtils.formatData(xmwhForm));
			//判断申请奖项与调整奖项是否相同
			if (model.getXmdm().equals(xmwhForm.getXmdm())) {
				request.setAttribute("isSame", true);
			} else {
				request.setAttribute("isSame", false);
			}
			// 审核流程列表
			//List<HashMap<String, String>> infoList = service.getSplcInfo(model);
			//request.setAttribute("infoList", infoList);
			BeanUtils.copyProperties(myForm, model);

			// 审核时可调整项目列表
			XmwhShszService xmwhService = new XmwhShszService();
			List<HashMap<String, String>> tzxmList = xmwhService.getKshxm(model.getDqxmdm());
			request.setAttribute("tzxmList", tzxmList);

			// 通过学号查询资助项目结果
			List<HashMap<String, String>> zzxmjgInfoList = new ZzxmjgService().getZzxmjgInfoList(model.getXh());
			request.setAttribute("zzxmjgInfoList", zzxmjgInfoList);
		}

		myForm.setXtgwid(xtgwid);
		request.setAttribute("mkxxForm", myForm);

		// 排版配置
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		return mapping.findForward("zzxmDgsh");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:验证资助项目金额是否可以修改
	 * @作者：cmj[工号：913]
	 * @日期：2013-12-5 下午02:09:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward jeSfkt(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm myForm = (XszzSqshForm) form;
		XmwhService xmwhService = new XmwhService();
		XmwhForm model = xmwhService.getModel(myForm.getXmdm());
		response.getWriter().print(JSONObject.fromObject(model));
		return null;
	}


	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:保存项目审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午02:02:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsh.do", rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生资助-资助管理-资助审核-保存-GUID：{guid}")
	public ActionForward saveXmsh(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) StringUtils.formatBean(form);
		XszzSqshService service = new XszzSqshService();

		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		// 保存项目审核
		boolean isSuccess = service.saveXmsh(myForm, user);
		String messageKey = isSuccess ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL_XSZZ;
		response.getWriter().print(getJsonMessageByKey(messageKey));

		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:最后一级撤消审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午02:09:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsh.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward cancelXmsh(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();
		// 撤消审核
		boolean isSuccess = service.cancelXmsh(myForm);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:删除项目申请
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午03:41:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemLog(description = "访问学生资助-资助管理-资助申请-删除-VALUES：{values}")
	public ActionForward delXmsq(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshService service = new XszzSqshService();
		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// 删除
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:流程跟踪
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午03:42:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@Deprecated
	public ActionForward getSplcInfo(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();
		// 获取各级审核信息
		List<HashMap<String, String>> infoList = service.getSplcInfo(myForm);

		request.setAttribute("infoList", infoList);
		return mapping.findForward("xmsqLcgz");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:资助项目批量审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午05:07:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsh.do", rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生资助-资助管理-资助审核-批量审核-ID:{id}")
	public ActionForward zzxmPlsh(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;

		if (SAVE.equals(myForm.getType())) {
			XszzSqshService service = new XszzSqshService();

			User user = getUser(request);
			// 批量审核
			String message = service.savePlsh(myForm, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		request.setAttribute("xxdm", Base.xxdm);

		return mapping.findForward("zzxmPlsh");

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:修改项目申请
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午06:54:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward updateXmsq(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		XszzSqshService service = new XszzSqshService();

		XszzSqshForm model = service.getModel(myForm);

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

			// 加载学生的困难生
			KnsjgService knsjgService = new KnsjgService();
			List<HashMap<String, String>> knsInfoList = knsjgService
					.getKnsInfoList(myForm.getXh());
			request.setAttribute("knsInfoList", knsInfoList);
		}
		//获取申请项目信息
		XmwhService xmService = new XmwhService();
		XmwhForm xmwhForm = xmService.getModel(model.getXmdm());
		xmwhForm.setPdxqmc(Base.getXqmcForXqdm(xmwhForm.getPdxq()));

		boolean isopen = false;
		//项目时间开关判断
		if ("1".equals(xmwhForm.getSqkg())) {

			//去除时间必填项，开始时间为空初始为零
			String kssj = null == xmwhForm.getSqkssj() ? "0000-00-00" : xmwhForm.getSqkssj();
			String jssj = null == xmwhForm.getSqjssj() ? "9999-99-99" : xmwhForm.getSqjssj();
			int ks = Integer.parseInt(kssj.replace("-", ""));
			int js = Integer.parseInt(jssj.replace("-", ""));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			int dq = Integer.parseInt(sdf.format(new Date()));
			if (dq >= ks && dq <= js) {
				isopen = true;
			}
		}

		request.setAttribute("isopen", isopen);
		request.setAttribute("xmwhForm", StringUtils.formatData(xmwhForm));
		request.setAttribute("type", UPDATE);
		request.setAttribute("mkxxForm", myForm);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("updateXmsq");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:保存申请修改
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午06:55:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemLog(description = "访问学生资助-资助管理-资助申请-保存GUID:{guid},XH:{xh},SQLY:{sqly}")
	public ActionForward saveSqxg(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XmwhTjszService tjszService = new XmwhTjszService();

		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		if ("submit".equals(myForm.getType())) {
			if (SFBJPY_Y) {
				// 评议小组人员不能申请！
				JglrService jglrService = new JglrService();
				HashMap<String, String> bjpyxzcyMap = jglrService.queryBjpyxzcy(myForm.getXh());
				if (bjpyxzcyMap.get("xh") != null) {
					response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_BJPY_XZRY_FAIL));
					return null;
				}
			}

			// ========== 验证评奖条件 begin ============
			List<HashMap<String, String>> conditions = tjszService.getTjszGl(myForm.getXmdm(), myForm.getXh());
			// 校验评奖条件，返回不符合条件的项目名称。
			CheckCondition checkCondition = new CheckStudentCondition();
			// 是否满足全部评奖条件
			boolean conditionsAllOk = checkCondition.checkConditionBoolean(myForm.getXh(), conditions);

			if (!conditionsAllOk) {
				response.getWriter().print(getJsonMessage("不符合条件！请确认！"));
				return null;
			}
			// ========== 验证评奖条件 end ============
		}
		XszzSqshService service = new XszzSqshService();
		// 执行修改操作
		boolean result = service.bcxgXmsq(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(myForm.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:查看申请信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-27 下午02:09:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward viewXmsq(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		XszzSqshForm model = service.getModel(myForm);

		if (model != null) {
			BeanUtils.copyProperties(myForm, model);

			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

			// 加载学生的困难生
			KnsjgService knsjgService = new KnsjgService();
			List<HashMap<String, String>> knsInfoList = knsjgService
					.getKnsInfoList(myForm.getXh());
			request.setAttribute("knsInfoList", knsInfoList);

			//List<HashMap<String, String>> infoList = service
			//		.getSplcInfo(myForm);
			//request.setAttribute("infoList", infoList);
		}
		if (Base.xxdm.equalsIgnoreCase("10718")) {
			KnsjgService knsjgservice = new KnsjgService();
			HashMap<String, String> knsrd = knsjgservice.getKnspd(model.getXh(), model.getXn());
			request.setAttribute("knsrd", knsrd);
			request.setAttribute("usertype", getUser(request).getUserType());
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("mkxxForm", myForm);
		request.setAttribute("mkxxList", mkxxList);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		return mapping.findForward("viewXmsq");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述: 检测条件
	 * @作者：Penghui.Qu
	 * @日期：2013-5-2 下午01:39:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward checkCondition(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XmwhTjszService tjszService = new XmwhTjszService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		List<HashMap<String, String>> conditions = tjszService.getTjszGl(myForm.getXmdm(), myForm.getXh());
		// 校验条件，返回不符合条件的项目名称。
		CheckCondition check = new CheckStudentCondition();
		List<HashMap<String, String>> faileds = check.checkCondition(myForm
				.getXh(), conditions);

		JSONArray jsonArray = JSONArray.fromObject(faileds);
		response.getWriter().print(jsonArray);

		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述: 学生资助审核情况统计
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-23 上午10:56:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward zzxmShqk(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);
		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		XmwhRsszService xmwhrsszservice = new XmwhRsszService();
		request.setAttribute("rsfpfs", xmwhrsszservice.getRsfpfs(myForm.getXmdm()));

		if (StringUtils.isNotNull(myForm.getXmdm())) {
			Map<String, Object> shqkInfo = service.getShqkInfo(user, myForm.getXmdm());

			request.setAttribute("shqkInfo", shqkInfo);
		}
		return mapping.findForward("zzxmShqk");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述: 具体资助项目审核统计
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-23 下午05:11:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward zzxmShtj(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XmwhService xmwhService = new XmwhService();
		// xmwhService.get
		List<HashMap<String, String>> zzxmList = xmwhService.getOthers(" ");

		request.setAttribute("zzxmList", zzxmList);
		return mapping.findForward("zzxmShtj");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:打印困难生申请信息
	 * @作者：honglin
	 * @日期：2013-5-3
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward printJsp(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		XszzSqshForm model = service.getModel(myForm);
		myForm.setXn(model.getXn());
		myForm.setXq(model.getXq());
		// 根据项目名称获取代码信息,以及项目信息
		if (!StringUtil.isNull(myForm.getXmmc())) {
			XmwhService xmwhService = new XmwhService();
			String xsxmdm = xmwhService.getXszzdm(myForm.getXmmc(), myForm.getXn(), myForm.getXq());
			myForm.setXmdm(xsxmdm);
		}
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			// 分解身份证号begin
			String xssfzh = xsjbxx.get("sfzh") == null || "".equals("sfzh") ? ""
					: xsjbxx.get("sfzh");
			int sylen = 18 - xssfzh.length();
			for (int i = 0; i < xssfzh.length(); i++) {
				xsjbxx.put("sfzh" + i, xssfzh.charAt(i) + "");
			}
			for (int i = 0; i < sylen; i++) {
				xsjbxx.put("sfzh" + (xssfzh.length() + i), "");
			}
			// 分解身份证号end
			request.setAttribute("jbxx", xsjbxx);

			// 加载学生家庭基本信息
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkdcForm = new JtqkdcForm();
			jtqkdcForm.setXh(myForm.getXh());
			JtqkdcForm jtqkmodel = jtqkService.getModel(jtqkdcForm);
			if (null == jtqkmodel) {
				jtqkmodel = new JtqkdcForm();
			}
			request.setAttribute("jtqk", jtqkmodel);
			
			// 按学号加载成员列表
			JtqkdcService jtqkdcService = new JtqkdcService();
			List<HashMap<String, String>> cyList = jtqkdcService
					.getJtcyList(myForm.getXh());
			request.setAttribute("blankList",
					CYSIZE > cyList.size() ? jtqkdcService.getBlankList(CYSIZE
							- cyList.size()) : jtqkdcService.getBlankList(0));
			request.setAttribute("cyList", cyList);
			request.setAttribute("cysize", CYSIZE > cyList.size() ? CYSIZE
					: cyList.size());

			// 加载学生困难认定情况
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(myForm
					.getXh(), myForm.getXn(), myForm.getXq());
			request.setAttribute("rddc", knsjg.get("rddc") == null ? "" : knsjg
					.get("rddc"));
		}
		// 学校名称
		request.setAttribute("xxmc", Base.xxmc);
		KnsdcService knsdcService = new KnsdcService();
		// 困难生档次list
		request.setAttribute("knsdc", knsdcService.getKnsdcList());
		// 跳转
		String forward = "";
		if ("gjjxjb".equalsIgnoreCase(myForm.getXmdm())) {// 国家奖学金申请审批表
			forward = "/xsgzgl/xszz/print/gjjxjbDefault.jsp";
		} else if ("gjlzjxjb".equalsIgnoreCase(myForm.getXmdm())) {// 国家励志奖学金申请审批表
			forward = "/xsgzgl/xszz/print/gjlzjxjbDefault.jsp";
		} else if ("gjzxjb".equalsIgnoreCase(myForm.getXmdm())) {// 国家助学金申请审批表
			forward = "/xsgzgl/xszz/print/gjzxjbDefault.jsp";
		} else {
			throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}

		BeanUtils.copyProperties(myForm, model);
		request.setAttribute("xmxx", model);// 加载学生项目信息
		return new ActionForward(forward, false);
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:导出功能
	 * @作者：ligl
	 * @日期：2013-5-22 上午10:44:47
	 * @修改记录:
	 */

	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getSqExportList(myForm, user);

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述: 审核导出
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-10-16 下午02:32:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url)
	public ActionForward exportDataSh(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getShExportList(myForm, user);

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}


	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述: 审核情况统计查询具体学生
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-4 上午10:17:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward getStudentsByShqk(ActionMapping mapping, ActionForm form,
										   HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm model = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		if (QUERY.equalsIgnoreCase(model.getType())) {
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			User user = getUser(request);
			//查询
			model.setShid(request.getParameter("xtgwid"));
			List<HashMap<String, String>> resultList = service.getStudentsList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xszz_sqsh_shtj.do");
		return mapping.findForward("studentsList");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:打印word
	 * @作者：ligl
	 * @日期：2013-5-31 上午11:32:52
	 * @修改记录:
	 */

	public ActionForward downloadWord(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm myForm = (XszzSqshForm) form;
		String guid = myForm.getGuid();
		File wordFile = getWord(guid);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:下载ZIP
	 * @作者：ligl
	 * @日期：2013-5-31 上午11:32:26
	 * @修改记录:
	 */

	public ActionForward downloadZip(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				File file = getWord(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}

	// 填充模版数据生成word文件
	private File getWord(String guid) throws Exception {
		XszzSqshService service = new XszzSqshService();
		ZzxmjgService zzxmjgService = new ZzxmjgService();
		XszzSqshForm myForm = new XszzSqshForm();
		myForm.setGuid(guid);
		XszzSqshForm model = service.getModel(myForm);//
		HashMap<String, String> bbMap = null;// 报表

		// 根据项目获取代码信息
		XmwhService xmwhService = new XmwhService();
		XmwhForm xmwhModel = xmwhService.getModel(model.getXmdm());
		BbwhService bbwhService = new BbwhService();
		bbMap = bbwhService.getDataById(xmwhModel.getDybb());
		model.setXmmc(xmwhModel.getXmmc());

		// 查询不到相关联报表信息
		if (bbMap == null || bbMap.isEmpty()) {
			throw new SystemException(MessageKey.XSZZ_BBDY_FAIL);
		}

		String xh = model.getXh();

		if (!StringUtil.isNull(xh)) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			//转换为中文日期格式
			xsjbxx.put("rxrq", DateTranCnDate.fomartDateToCn(xsjbxx.get("rxrq"), FomartDateType.month));
			// 分解身份证号begin
			String xssfzh = StringUtil.isNull(xsjbxx.get("sfzh")) ? "" : xsjbxx.get("sfzh");
			for (int i = 0, j = xssfzh.length(); i < j; i++) {
				xsjbxx.put("sfzh" + i, String.valueOf(xssfzh.charAt(i)));
			}

			//学生照片
			InputStream is = xsxxService.getPhotoStream(xh);
			File photoFile = FileUtil.inputstreamToFile(is, "doc");
			String photo = FileUtil.getBASE64(photoFile);

			// 加载学生家庭基本信息
			JtqkdcService jtqkService = new JtqkdcService();
			JtqkdcForm jtqkmodel = jtqkService.getModel(xh);

			// 按学号加载成员列表
			JtqkdcService jtqkdcService = new JtqkdcService();
			List<HashMap<String, String>> cyList = jtqkdcService
					.getJtcyList(model.getXh());
			if (cyList != null && cyList.size() > 0) {
				for (HashMap<String, String> cyMap : cyList) {
					cyMap.put("cyxm", HtmlUtil.xmlZy(cyMap.get("cyxm")));
					cyMap.put("cygxmc", HtmlUtil.xmlZy(cyMap.get("cygxmc")));
					cyMap.put("cyxxdw", HtmlUtil.xmlZy(cyMap.get("cyxxdw")));
				}
			}
			List<HashMap<String, String>> cyList4line = jtqkdcService.getJtcyList(xh, 4); //取4条家庭成员列表，不足4条填空
			List<HashMap<String, String>> cyList5line = jtqkdcService.getJtcyList(xh, 5); //取4条家庭成员列表，不足4条填空
			// 加载学生困难认定情况
			KnsjgService knsjgService = new KnsjgService();
			HashMap<String, String> knsjg = knsjgService.getXsknsjg(model
					.getXh(), model.getXn(), model.getXq());
			KnsdcService knsdcService = new KnsdcService();
			Map<String, Object> data = new HashMap<String, Object>();
			
			
			//闽南师范学院个性化
			if ("10402".equals(Base.xxdm)) {
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> hjqkList_10402 = xsxxglService.getHjqkNewList(xh);

				data.put("hjqkList_10402", hjqkList_10402);
				int hjqkSize = (4 - hjqkList_10402.size()) < 0 ? 0 : (4 - hjqkList_10402.size());
				data.put("hjqkBlankList_10402", jtqkdcService.getBlankList(hjqkSize));
			}
			//加载当前学年学期的智育分
			ZcfsService zcfsServcie = new ZcfsService();
			List<HashMap<String, String>> zyfsInfo = zcfsServcie.getZyfListByXh(model.getXh(), model.getPdxn(), model.getPdxq());
			if (zyfsInfo != null && zyfsInfo.size() > 0) {
				data.put("zyf", zyfsInfo.get(0).get("fs"));
			}
			//加载项目名称
			data.put("xmmc", model.getXmmc());

			String theSameXmmcNum = service.getTheSameZzxmNumber(bbMap.get("bbmc"), xh);  //以前申请相同项目的数量
			//已申请且批准通过的相同项目的数量
			data.put("theSameXmmcNum", theSameXmmcNum);

			List<HashMap<String, String>> ywqtjxj = service.getYwqtjxList(bbMap.get("bbmc"), xh);
			//大学期间同时享受其他奖(助)学金
			data.put("ywqtjxj", ywqtjxj == null ? new ArrayList<HashMap<String, String>>() : ywqtjxj);

			//加载评奖结果信息
			PjjgService pjjgService = new PjjgService();
			List<HashMap<String, String>> pjjg = pjjgService.getPjpyInfoMapForDjb(xh);
			List<HashMap<String, String>> pjjgList4line = pjjgService.getHjqkByXhMap(xh, 4);  //获取最新4条评奖结果
			String xmmcs = pjjgService.getXmmcAllByPjjg(xh);

			//加载资助信息
			List<HashMap<String, String>> zzjg = zzxmjgService.getZzxmjgInfoList(xh);

			data.put("pjjg", pjjg);
			data.put("pjjgList4line", pjjgList4line);
			data.put("xmmcs", xmmcs);
			data.put("zzjgList", zzjg);
			// 家庭成员列表空行
			data.put("pjjgblankList", CYSIZE > pjjg.size() ? jtqkdcService
					.getBlankList(CYSIZE - pjjg.size()) : jtqkdcService
					.getBlankList(0));
			data.put("sqzzje", model.getYlzd1()); // 申请资助金额
			data.put("zzjg", model);
			data.putAll(xsjbxx);// 学生基本信息
			data.put("jtqk", jtqkmodel == null ? new JtqkdcForm() : jtqkmodel);// 家庭情况
			if (jtqkmodel != null && jtqkmodel.getJtnzsr() != null) {
				data.put("jtnsr",jtqkmodel.getJtnzsr());//家庭年收入
				data.put("jtyzsr", (Double.parseDouble(jtqkmodel.getJtnzsr()) / 12));//家庭月总收入
				data.put("jtrjysr", jtqkmodel.getJtrs() == null ? "" : (Double.parseDouble(jtqkmodel.getJtnzsr()) / 12 / Integer.parseInt(jtqkmodel.getJtrs())));//家庭人均月收入
			} else {
				data.put("jtyzsr", "");
				data.put("jtrjysr", "");
			}
			
			/*家庭情况--家庭人均年收入(通用)*/
			if (jtqkmodel != null) {
				String jtrjsr = jtqkmodel.getJtrjsr();
				if (jtrjsr != null) {
					data.put("jtrjsr", jtrjsr);
				} else {
					data.put("jtrjsr", "");
				}
			} else {
				data.put("jtrjsr", "");
			}

			data.put("cyList", cyList);// 家庭成员列表
			data.put("cyList4line", cyList4line);
			data.put("cyList5line", cyList5line);
			// 家庭成员列表空行
			data.put("blankList", CYSIZE > cyList.size() ? jtqkdcService
					.getBlankList(CYSIZE - cyList.size()) : jtqkdcService
					.getBlankList(0));
			//危机处分数据查询
			WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
			List<HashMap<String, String>> wjList = wjcfcfsbService.getYscfqk(xh);
			data.put("wjList", wjList);

			//山西财经大学个性化
			if ("10125".equals(Base.xxdm)) {
				List<HashMap<String, String>> pjjgList = new ArrayList<HashMap<String, String>>();
				HashMap<String, String> map = null;
				int pjjgsize = 4;
				if (pjjg.size() > pjjgsize) {
					for (int i = 0; i < pjjgsize; i++) {
						map = new HashMap<String, String>();
						map.put("sqsjs", pjjg.get(i).get("sqsjs"));
						map.put("xmmc", pjjg.get(i).get("xmmc"));
						map.put("bjdw", pjjg.get(i).get("bjdw"));
						pjjgList.add(map);
					}
					data.put("pjjgblankList", pjjgList);
				} else {
					data.put("pjjgblankList", pjjg);
				}
				int size2 = (4 - pjjg.size()) <= 0 ? 0 : (4 - pjjg.size());
				data.put("blankList2", jtqkdcService.getBlankList(size2));
				data.put("cyList", cyList);
				int size1 = (4 - cyList.size()) <= 0 ? 0 : (4 - cyList.size());
				data.put("blankList1", jtqkdcService.getBlankList(size1));
			}
			//浙江警官学院 个性化
			if ("12869".equals(Base.xxdm)) {
				List<HashMap<String, String>> pjjgList = new ArrayList<HashMap<String, String>>();
				HashMap<String, String> map = null;
				int pjjgsize = 4;
				if (pjjg.size() > pjjgsize) {
					for (int i = 0; i < pjjgsize; i++) {
						map = new HashMap<String, String>();
						map.put("sqsjs", pjjg.get(i).get("sqsjs"));
						map.put("xmmc", pjjg.get(i).get("xmmc"));
						map.put("bjdw", pjjg.get(i).get("bjdw"));
						pjjgList.add(map);
					}
					data.put("pjjgblankList", pjjgList);
				} else {
					data.put("pjjgblankList", pjjg);
				}
				int size2 = (4 - pjjg.size()) <= 0 ? 0 : (4 - pjjg.size());
				data.put("blankList2", jtqkdcService.getBlankList(size2));
				data.put("cyList", cyList);
				int size1 = (4 - cyList.size()) <= 0 ? 0 : (4 - cyList.size());
				data.put("blankList1", jtqkdcService.getBlankList(size1));
				HashMap<String, String> sfkns = zzxmjgService.getSfkns(xh, model.getXn());
				data.put("jgxyrddc", sfkns.get("dcmc"));
				data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
			}
			//河北建材
			if ("12389".equals(Base.xxdm)) {
				data.put("xn",model.getXn());
				int bxks = zcfsServcie.getCjListByXhXn(xh,model.getXn(),"必修").size();
				String yxkcs = service.getYxksByXh_12389(model.getXh(),model.getXn(),"必修");//优秀课程数
				String lkcs = service.getLkcsByXh_12389(model.getXh(),model.getXn(),"必修");//良课程数
				String cjpm = service.getCjpmXy(model.getXn(), xh, xsjbxx.get("zydm"));//成绩专业排名
				String zyzrs = service.getZyzrs(xsjbxx.get("zydm"));//专业总人数
				HashMap<String, String> zcf = zcfsServcie.getZczfByXh(xh, model.getXn(), model.getXq());
				String bjrs = zcfsServcie.getBjrs(xh);
				int sfzcf = 1;
				if(StringUtils.isNull( zcf.get("fs"))){
					sfzcf = 0;
				}
				data.put("sfzcf", sfzcf);
				data.put("zcfs", zcf.get("fs"));
				data.put("zcbjpm", zcf.get("bjpm"));
				data.put("bxks",bxks);
				data.put("yxkcs",yxkcs);
				data.put("lkcs",lkcs);
				data.put("cjpm",cjpm);
				data.put("bjrs", bjrs);
				data.put("zyzrs", zyzrs);

			}
			//黑龙江农垦职业学院个性化
			if ("12727".equals(Base.xxdm)) {
				// ======== 曾获何种奖励 begin============
				StringBuffer chhzjlBuffer = new StringBuffer();
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pj = pjjg.get(i);
					String xmmc = pj.get("xmmc");
					if (StringUtils.isNotNull(xmmc)) {
						chhzjlBuffer.append(pj.get("xn")).append(" ").append(pj.get("xqmc")).append(" ").append(xmmc).append("；");
					}
				}
				String chhzjlmc = chhzjlBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("chhzjlmc", chhzjlmc);
				// ======== 曾获何种奖励 end============
				// 家庭情况
				JtqkdcForm jtqkdcForm = new JtqkdcForm();
				jtqkdcForm.setXh(xh);
				JtqkdcForm jtqkmodel_12727 = jtqkService.getModel(jtqkdcForm);
				if (jtqkmodel_12727 == null) {
					jtqkmodel_12727 = new JtqkdcForm();
				}
				data.put("jtqkmodel_12727", jtqkmodel_12727);
				String jthk = jtqkmodel.getJthk();
				boolean jthkCzFlag = false;
				if (jthk != null && jthk.contains("城镇")) {
					jthkCzFlag = true;
				}
				data.put("jthkCzFlag", String.valueOf(jthkCzFlag));
				// 家庭成员
				List<HashMap<String, String>> jtcyxxList_12727 = jtqkService.getJtcyList(xh);
				pjjgService.addBlankList(jtcyxxList_12727, 4 - jtcyxxList_12727.size());
				data.put("jtcyxxList_12727", jtcyxxList_12727.subList(0, 4));
				// ========= 学习成绩 begin ============
				ZcfsService zcfService = new ZcfsService();
				List<HashMap<String, String>> cjXnxqList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "");
				StringBuffer bxnxxcjBuffer = new StringBuffer();
				for (int i = 0; i < cjXnxqList.size(); i++) {
					HashMap<String, String> cjXnxqMap = cjXnxqList.get(i);
					bxnxxcjBuffer.append(cjXnxqMap.get("kcmc")).append("：").append(cjXnxqMap.get("cj")).append("，");
				}
				String bxnxxcjmc = bxnxxcjBuffer.reverse().replace(0, 1, "").reverse().toString();
				data.put("bxnxxcjmc", bxnxxcjmc);

				List<HashMap<String, String>> bxn_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "");
				String bxn_cjSxqPjf = pjjgService.getPjf(bxn_cjSxqBxList, 2); // 本学年上学期平均成绩
				data.put("bxn_cjSxqPjf", bxn_cjSxqPjf);
				List<HashMap<String, String>> bxn_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "");
				String bxn_cjXxqPjf = pjjgService.getPjf(bxn_cjXxqBxList, 2); // 本学年下学期平均成绩
				data.put("bxn_cjXxqPjf", bxn_cjXxqPjf);

				String bxn_bjgcjsSxq = pjjgService.getBjgcjNum(xh, model.getXn(), "01"); //本学年上学期不及格成绩数量
				data.put("bxn_bjgcjsSxq", "".equals(bxn_bjgcjsSxq) ? "0" : bxn_bjgcjsSxq);
				String bxn_bjgcjsXxq = pjjgService.getBjgcjNum(xh, model.getXn(), "02"); //本学年下学期不及格成绩数量
				data.put("bxn_bjgcjsXxq", "".equals(bxn_bjgcjsXxq) ? "0" : bxn_bjgcjsXxq);
				// ========= 学习成绩 end ============
			}
			//广西职业学院个性化
			if("11773".equals(Base.xxdm)){
				// ======== 曾获何种奖励 begin============
				List<HashMap<String, String>> pjjgList3line = pjjgService.getHjqkByXhMap(xh,3);
				data.put("pjjgList3line", pjjgList3line);
				List<HashMap<String, String>> zzjgList4line = zzxmjgService.getZzjgList(xh,4);
				data.put("zzjgList4line", zzjgList4line);
				// ======== 曾获何种奖励 end============
				// ========= 上学年各科学习成绩 begin ============
				ZcfsService zcfService = new ZcfsService();
				String sT = model.getXn().substring(0,4);
				Base.beforXn = String.valueOf(Integer.parseInt(sT)-1) + "-" + sT;
				List<HashMap<String, String>> cjXnxqList = zcfService.getCjListByXhXn(xh,Base.beforXn, "");
				data.put("cjXnxqList", cjXnxqList);
				// ========= 学习成绩 end ============
			}
			
			//杭州师范个性化
			if("10346".equals(Base.xxdm)){
				ZcfsService zcfService = new ZcfsService();
				String bjrs = zcfService.getBjrs(xh);
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bjrs", "".equals(cjpm) ? "" : bjrs);// 班级人数
				data.put("cjpm", cjpm);// 总成绩排名
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// 必修课数
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// 必修课及格数

				//加载评奖结果信息
				PjjgService pjjgservice = new PjjgService();
				List<HashMap<String, String>> pjjg4line = pjjgservice.getHjqkByXhMap(xh, 4);  //获取最新4条评奖结果
				for (HashMap<String, String> hashMap : pjjg4line) {
					String xmmc = hashMap.get("xmmc");
					if(xmmc!=null && !"".equals(xmmc)){
						hashMap.put("bjdw","杭州师范大学");
					}
				}
				data.put("pjjg4_hzsf", pjjg4line);
				
				//加载资助信息（3条）
				List<HashMap<String, String>> zzdwlist = zzxmjgService.getZzxmjgInfoList(xh);
				int m=3-zzdwlist.size();
				for (int i = 0; i <m; i++) {
					zzdwlist.add(new HashMap<String, String>());
				}
				List<HashMap<String, String>> pjjg3line = pjjgservice.getHjqkByXhMap(xh, 3);  //获取最新3条评奖结果
				//将资助信息放入评奖结果中，方便xml文件读取
				for (int i = 0; i < pjjg3line.size(); i++) {
					pjjg3line.get(i).put("zzxmmc", zzdwlist.get(i).get("xmmc"));
					pjjg3line.get(i).put("zzje", zzdwlist.get(i).get("je"));
				}
				data.put("pjjg3_hzsf", pjjg3line);
				//助学贷款信息
				XszzSqshService xszzSqshService = new XszzSqshService();
				String dkxx = xszzSqshService.getXsDkxx(Base.currXn, xh);
				data.put("zxdkxx", dkxx);
				
			}
			
			data.put("knsdcList", knsdcService.getKnsdcList());// 困难生档次list
			data.put("rddc", knsjg.get("rddc")==null?"":knsjg.get("rddc"));//认定档次
			data.put("rddcmc", knsjg.get("dcmc") == null ? "" : knsjg.get("dcmc"));//认定档次名称
			data.put("xxmc", Base.xxmc);// 学校名称
			data.put("sqly", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
			data.put("currXnNow", Base.currXn);// 当前学年
			data.put("currXn", model.getPdxn());// 当前项目评定学年
			//data.put("xn", HtmlUtil.xmlZy(model.getSqly()));// 申请理由
			//转换为中文日期格式
			data.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.month));// 出生日期
			data.put("photo", photo);// 学生照片

			String jtsfyqk = "";

			if (jtqkmodel != null
					&& jtqkmodel.getJtqzqk() != null
					&& jtqkmodel.getJtqzqk().indexOf("无") == -1
					&& jtqkmodel.getJtqzqk().indexOf("没有") == -1) {
				jtsfyqk = "有";
			} else {
				jtsfyqk = "无";
			}
			data.put("jtsfyqk", jtsfyqk);// 家庭是否有欠款
			//学生年龄
			String xsnl = " ";
			try {
				String csn = DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.year).replaceAll("年", "");
				Calendar cal = Calendar.getInstance();
				int currn = cal.get(Calendar.YEAR); //当前年
				xsnl = String.valueOf(currn - Integer.valueOf(csn));
			} catch (Exception e) {
				//e.printStackTrace();
			}
			data.put("xsnl", xsnl);// 学生年龄

			//东北石油
			if ("10220".equals(Base.xxdm)) {
				String zwmc = zzxmjgService.getZwForXh(xh);
				data.put("zwmc", zwmc);
				if (zwmc == "") {
					data.put("sfyzw", "0");
				}
				String csrq = xsjbxx.get("csrq");
				data.put("csrq", csrq);
				if (jtqkmodel != null) {
					data.put("sfpkx", jtqkmodel.getSfpkx());// 是否贫困县
					data.put("pkxjb", jtqkmodel.getPkxjb());// 贫困县级别
					data.put("fmjk", jtqkmodel.getFmjk());// 父母是否有病或残疾
					data.put("fmjz", jtqkmodel.getFmjz());// 父母健在情况
				} else {
					data.put("sfpkx", "");// 是否贫困县
					data.put("pkxjb", "");// 贫困县级别
					data.put("fmjk", "");// 父母是否有病或残疾
					data.put("fmjz", "");// 父母健在情况
				}
				HashMap<String, String> fqxxInfo = jtqkdcService.getfqInfo(xh);
				HashMap<String, String> mqxxInfo = jtqkdcService.getmqInfo(xh);
				data.put("fqzy", fqxxInfo.get("cyzy"));
				data.put("mqzy", mqxxInfo.get("cyzy"));
				data.put("blankList", 5 > cyList.size() ? jtqkdcService
						.getBlankList(5 - cyList.size()) : jtqkdcService
						.getBlankList(0));
			}

			//重庆三峡高等医药专科学校
			if ("14008".equals(Base.xxdm)) {
				YlbxjgService ylbxjgService = new YlbxjgService();
				HashMap<String, String> jfdcInfo = ylbxjgService.getJfdcRylbInfo(xh);
				data.put("yljfdc", jfdcInfo.get("dcmc")); // 医疗缴费档次	
				data.put("rylb", jfdcInfo.get("rylb")); // 人员类别
			}

			//上海戏剧
			if ("10279".equals(Base.xxdm)) {
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.month));// 出生日期
				String csny = xsjbxx.get("csny") == null ? "" : xsjbxx.get("csny");
				String rxrq = xsjbxx.get("rxrq") == null ? "" : xsjbxx.get("rxrq");
				data.put("csny_n", csny.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				data.put("rxny_n", rxrq.replaceAll("年", ".").replaceAll("月", ""));// 1988.9
				data.put("blankList", 5 > cyList.size() ? jtqkdcService
						.getBlankList(5 - cyList.size()) : jtqkdcService
						.getBlankList(0));
			}

			//河北工业大学
			if("10080".equals(Base.xxdm)){
				List<HashMap<String,String>> zzxmList = zzxmjgService.getZzxmInfoByXhXn(model.getXh(),model.getXn(),model.getXq());//本学年已享受的资助项目
				String yxszzxm = "";
				if(zzxmList.size()>0) {
					for (int i = 0; i < zzxmList.size(); i++) {
						HashMap<String, String> zzxmmap = zzxmList.get(i);
						yxszzxm += zzxmmap.get("xmmc");
						if (i<zzxmList.size()-1)
						{
							yxszzxm +=",";
						}
					}
					data.put("yxszzxm",yxszzxm);
				}

			}
			data.put("lxdh",xsjbxx.get("sjhm"));
			//温州大学
			if ("10351".equals(Base.xxdm)) {
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> hjqkList = xsxxglService.getHjqkNewFourList(xh);
				data.put("pjjg", hjqkList);
				int hjqkSize = (4 - hjqkList.size()) < 0 ? 0 : (4 - hjqkList.size());
				data.put("pjjgblankList", jtqkdcService.getBlankList(hjqkSize));
				String csny = DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.month);
				String rxrq = xsjbxx.get("rxrq");
				data.put("csny", csny);
				data.put("rxrq", rxrq);
				String xz = "";
				if (StringUtils.isNotNull(xsjbxx.get("xz"))) {
					xz = DateUtils.numToZh(xsjbxx.get("xz")) + "年";
				}
				data.put("xz", xz);
				HashMap<String, String> bxk = pjjgService.getBxk(model.getXh(), model.getPdxn());
				HashMap<String, String> pm = pjjgService.getCjPm(model.getXh(), model.getPdxn());
				data.put("bxk", bxk);
				data.put("pm", pm);

			}

			if ("12867".equals(Base.xxdm)) {
				ZzxmjgService zzService = new ZzxmjgService();
				XsxxtyglService service1 = new XsxxtyglService();
				XsxxglService xsxxglService = new XsxxglService();
				List cyList1 = xsxxglService.getJtcyxxXsList(xh);
				data.put("jtcyxxList", cyList1);
				int size1 = 5 - cyList1.size() <= 0 ? 0 : 5 - cyList1.size();
				data.put("cyblankList", service1.getBlankList(size1));

				Map jtqkmap = jtqkdcService.getJtqkInfo(xh);
				data.put("jtqkmap", jtqkmap);

				List zzxx = zzService.getZzxmjgInfoList(xh);
				HashMap zxjxxmap = new HashMap();
				for (int i = 0; i < zzxx.size(); i++) {
					if (((String) ((HashMap) zzxx.get(i)).get("xmmc")).equals(model.getXmmc())) {
						zxjxxmap = (HashMap) zzxx.get(i);
					}
				}
				data.put("zxjxxmap", zxjxxmap);
				HashMap<String, String> axjjmap = zzService.getZjlyAxjjMap(xh, model.getXn());
				data.put("axjjmap", axjjmap);
				if (model.getXmmc().indexOf("爱心基金") != -1) {
					//爱心基金
					HashMap<String, String> axjjmap1 = zzService.getZjlyAxjjMap(xh, model.getXn());
					data.put("axjjmap", axjjmap1);
				} else if (model.getXmmc().indexOf("孤儿爱心补助") != -1) {
					//孤儿爱心补助
					HashMap<String, String> geaxmap = zzService.getZjlyGeaxMap(xh, model.getXn());
					data.put("geaxmap", geaxmap);
				} else if (model.getXmmc().indexOf("校服费用减免") != -1) {
					//新生校服费用减免
					HashMap<String, String> xfjmmap = zzService.getZjlyXfjmMap(xh, model.getXn());
					data.put("xfjmmap", xfjmmap);
				} else if (model.getXmmc().indexOf("生活用品费用减免") != -1) {
					//生活用品费用减免
					HashMap<String, String> shfjmmap = zzService.getZjlyShfjmMap(xh, model.getXn());
					data.put("shfjmmap", shfjmmap);
				} else if (model.getXmmc().indexOf("商业保险补助") != -1) {
					//生活用品费用减免
					HashMap<String, String> sybxbzmmap = zzService.getZjlySybxbzMap(xh, model.getXn());
					data.put("sybxbzmmap", sybxbzmmap);
				} else if (model.getXmmc().indexOf("交通补助") != -1) {
					//交通补助
					HashMap<String, String> jtbzmmap = zzService.getZjlyJtbzMap(xh, model.getXn());
					data.put("jtbzmmap", jtbzmmap);
				} else if (model.getXmmc().indexOf("技能考证") != -1) {
					//技能考证费补助
					HashMap<String, String> jnkzfbzmap = zzService.getZjlyJnkzfbzmapMap(xh, model.getXn());
					data.put("jnkzfbzmap", jnkzfbzmap);
				} else if (model.getXmmc().indexOf("学费减免") != -1) {
					//学费减免
					HashMap<String, String> xfjmmap = zzService.getZjlyXfjmmapMap(xh, model.getXn());
					data.put("xfjmmap", xfjmmap);
				}
			}
			//广西师范大学 个性化
			if (StringUtils.isEqual(Base.xxdm, "10602")) {
				//学生家庭成员情况
				XsxxglService xsxxglService = new XsxxglService();
				List<HashMap<String, String>> jtcyxxList = xsxxglService.getJtcyxxXsList(xh);
				data.put("dqxmmc", model.getXmmc());
				data.put("photo", photo);
				data.put("jtcyxxList", jtcyxxList);
				int size1 = (3 - jtcyxxList.size()) <= 0 ? 0 : (3 - jtcyxxList.size());
				data.put("cyblankList", jtqkdcService.getBlankList(size1));
				//加载评奖结果信息
				PjjgService pjjgservice = new PjjgService();
				List<HashMap<String, String>> pjjglist = pjjgservice.getPjpyInfoList(xh);
				data.put("pjjg1", pjjglist);
				//加载资助信息
				List<HashMap<String, String>> zzjglist = zzxmjgService.getZzxmjgInfoList(xh);
				data.put("zzjglist", zzjglist);
			}
			//西北民族大学
			if ("10742".equals(Base.xxdm)) {
				data.put("xn1", model.getXn().trim().substring(0, 4));
				data.put("xn2", model.getXn().trim().substring(5, 9));
				String tbrq = model.getSqsj();// 申请时间
				data.put("y", tbrq.trim().substring(0, 4));
				data.put("m", tbrq.trim().substring(5, 7));
				data.put("d", tbrq.trim().substring(8, 10));
				CxpyService cxpyService = new CxpyService();
				XsxxglService xsxxglService = new XsxxglService();
				HashMap<String, String> cxpyDj = cxpyService.getCxpyByXhXnXq(xh, model.getXn(), model.getXq());
				data.put("cxpyDj", cxpyDj.get("cxdjmc"));//操行评定

				// ========== 本学年成绩相关 begin ============
				List<HashMap<String, String>> kcxxlist = xsxxglService.getStuCjOfXnList(xh, model.getXn());

				pjjgService.addBlankList(kcxxlist, 30 - kcxxlist.size());
				data.put("kcxxlist", kcxxlist); // 本学年成绩（课程信息）

				List<HashMap<String, String>> xfjdlist = xsxxglService.getStuXFJDOfXnList(xh, model.getXn());
				data.put("xfjdlist", xfjdlist); // 总学分绩点、平均学分绩点
				// ========== 本学年成绩相关 end ============
			}

			//中央民族大学
			if ("10052".equals(Base.xxdm)) {
				String sqly = HtmlUtil.xmlZy(model.getSqly());
				data.put("sqly", sqly);// 申请理由
				// 寝室
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx = wdgwsqService.getQsxx(xh);
				String qsbh = null;
				if (null == qsxx.get("ldmc") || null == qsxx.get("qsh")) {
					qsbh = "";
				} else {
					qsbh = qsxx.get("ldmc") + "-" + qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				data.put("qsxx", qsxx);
				xsjbxx.put("csny", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.month));// 出生日期
				xsjbxx.put("csnyr", DateTranCnDate.fomartDateToCn(xsjbxx.get("csrq"), FomartDateType.day));// 出生日期
				xsjbxx.put("rxny", xsjbxx.get("rxrq"));// 入学日期
				// 家庭成员
				PjjgService servicePjPy = new PjjgService();
				List<HashMap<String, String>> jtcyxxList_zymzdx = jtqkService.getJtcyList(xh);
				servicePjPy.addBlankList(jtcyxxList_zymzdx, 5 - jtcyxxList_zymzdx.size());
				data.put("jtcyxxList_zymzdx", jtcyxxList_zymzdx);
				// 项目学年学期必修课成绩
				ZcfsService zcfService = new ZcfsService();
				HashMap<String, String> zcf = zcfService.getZczfByXh(xh, model.getXn(), model.getXq());
				data.put("zcf", zcf);
				List<HashMap<String, String>> xmXnxqBxCjList = zcfService.getCjListByXhXnXq(xh, model.getXn(), model.getXq(), "必修");
				servicePjPy.addBlankList(xmXnxqBxCjList, 8 - xmXnxqBxCjList.size());
				data.put("xmXnxqBxCjList", xmXnxqBxCjList);
				String xmXnxqBxCjPjf = servicePjPy.getPjf(xmXnxqBxCjList, 2); // 平均成绩
				data.put("xmXnxqBxCjPjf", xmXnxqBxCjPjf);
				// 党团建设
				DtxxjgService dtxxjgService = new DtxxjgService();
				List<HashMap<String, String>> jdlist = dtxxjgService.getGrJdxx(xh);
				String rdrtsj = "";
				String zzmmmc = xsjbxx.get("zzmmmc");
				if (StringUtils.isNotNull(zzmmmc)) {
					for (HashMap<String, String> jdMap : jdlist) {
						String jddm = jdMap.get("jddm");
						String kssj = jdMap.get("kssj");
						if ((zzmmmc.contains("团员") && "02".equals(jddm))
								|| (zzmmmc.contains("预备党员") && "09".equals(jddm))
								|| (zzmmmc.contains("党员") && !zzmmmc.contains("预备党员") && "11".equals(jddm))) {
							rdrtsj = kssj;
						}
					}
				}
				data.put("rdrtnyr", DateTranCnDate.fomartDateToCn(rdrtsj, FomartDateType.day));
				// 已获奖项
				StringBuffer yhjxBuffer = new StringBuffer();
				List<HashMap<String, String>> pjjg1 = servicePjPy.getPjpyInfoMapForDjb(xh);
				for (int i = 0; i < pjjg1.size(); i++) {
					HashMap<String, String> pj = pjjg1.get(i);
					String xmmc = pj.get("xmmc");
					if (StringUtils.isNotNull(xmmc)) {
						yhjxBuffer.append(xmmc).append("、");
					}
				}
				String yhjxStr = yhjxBuffer.toString();
				if (yhjxStr.length() > 1) {
					yhjxStr = yhjxStr.substring(0, yhjxStr.length() - 1);
				}
				data.put("yhjxxmmc", yhjxStr);
			}

			//广州铁路职业技术学院个性化
			if ("13943".equals(Base.xxdm)) {
				String sqly_13943 = HtmlUtil.xmlZy(model.getSqly());
				String sqly_13943_1 = sqly_13943;
				String sqly_13943_2 = "";
				if (sqly_13943.length() > 363) {
					sqly_13943_1 = sqly_13943.substring(0, 363);
					sqly_13943_2 = sqly_13943.substring(363);
				}
				data.put("sqly_13943_1", sqly_13943_1);// 申请理由(第一部分)
				data.put("sqly_13943_2", sqly_13943_2);// 申请理由(第二部分)
				String xymc_13943 = xsjbxx.get("xymc");
				if (xymc_13943.length() < 39) {
					int max = 39 - xymc_13943.length();
					for (int i = 0; i < max; i++) {
						xymc_13943 += " ";
					}
				}
				data.put("xymc_13943", xymc_13943);
				// 寝室
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx = wdgwsqService.getQsxx(xh);
				String qsbh = null;
				if (null == qsxx.get("ldmc") || null == qsxx.get("qsh")) {
					qsbh = "";
				} else {
					qsbh = qsxx.get("ldmc") + "-" + qsxx.get("qsh");
				}
				data.put("qsbh_13943", qsbh);
				// 担任职务
				DwwhService dwwhService = new DwwhService();
				data.put("zwmc", dwwhService.getZwForXh(xh));
				ZcfsService zcfService = new ZcfsService();
				// 本学年上学期成绩
				PjjgService servicePjPy = new PjjgService();
				List<HashMap<String, String>> bxn_cjSxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "01", "");
				servicePjPy.addBlankList(bxn_cjSxqBxList, 12 - bxn_cjSxqBxList.size());
				data.put("bxn_cjSxqBxList", bxn_cjSxqBxList);
				HashMap<String, String> bxn_zcfSxq = zcfService.getZczfByXh(xh, model.getXn(), "01");
				data.put("bxn_zcfSxq", bxn_zcfSxq);
				// 本学年下学期成绩
				List<HashMap<String, String>> bxn_cjXxqBxList = zcfService.getCjListByXhXnXq(xh, model.getXn(), "02", "");
				servicePjPy.addBlankList(bxn_cjXxqBxList, 12 - bxn_cjXxqBxList.size());
				data.put("bxn_cjXxqBxList", bxn_cjXxqBxList);
				HashMap<String, String> bxn_zcfXxq = zcfService.getZczfByXh(xh, model.getXn(), "02");
				data.put("bxn_zcfXxq", bxn_zcfXxq);
				// 评定学期
				String pdxq = model.getPdxq();
				if (StringUtils.isNull(pdxq)) {
					pdxq = CsszService.XQKG;
				}
				// 项目学年学期成绩
				HashMap<String, String> xmZcf = zcfService.getZczfByXh(xh, model.getPdxn(), pdxq);
				data.put("xmZcf", xmZcf);
				// 班级人数
				CpmdService cpmdService = new CpmdService();
				String bjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getPdxn(), pdxq);
				data.put("bjbjrs", bjrs);
				int yjNum = 0; // 院级
				int xjNum = 0; // 校级
				int xjysNum = 0; // 校级以上
				for (int i = 0; i < pjjg.size(); i++) {
					HashMap<String, String> pjjgMap = pjjg.get(i);
					String xmxzmc = pjjgMap.get("xmxzmc");
					if (StringUtils.isNotNull(xmxzmc)) {
						if (xmxzmc.contains("院级")) {
							yjNum++;
						} else if (xmxzmc.contains("校级以上")) {
							xjysNum++;
						} else if (xmxzmc.contains("校级")) {
							xjNum++;
						}
					}
				}
				data.put("yjNum", String.valueOf(yjNum));
				data.put("xjNum", String.valueOf(xjNum));
				data.put("xjysNum", String.valueOf(xjysNum));
				// 本学年上学期班级人数
				String bxn_cjSxqBjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), "01");
				data.put("bxn_cjSxqBjrs", bxn_cjSxqBjrs);
				// 本学年下学期班级人数
				String bxn_cjXxqBjrs = cpmdService.getBjrs(xsjbxx.get("bjdm"), model.getXn(), "02");
				data.put("bxn_cjXxqBjrs", bxn_cjXxqBjrs);
			}

			//陕西师大
			if ("10718".equals(Base.xxdm)) {
				data.put("rxrq", (String) xsjbxx.get("rxrq").subSequence(0, 4) + xsjbxx.get("rxrq").subSequence(5, 7));
				data.put("csny", (String) xsjbxx.get("csrq").subSequence(0, 4) + xsjbxx.get("csrq").subSequence(5, 7));
				PjjgModel form = new PjjgModel();
				form.setXn(model.getXn());
				form.setXh(model.getXh());
				form.setCpbjdm(xsjbxx.get("bjdm"));
				form.setCpzydm(xsjbxx.get("zydm"));
				data.put("xn1", model.getXn().trim().substring(0, 4));
				data.put("xn2", model.getXn().trim().substring(5, 9));
				String sqsj = model.getSqsj();
				data.put("year", sqsj.trim().substring(0, 4));
				data.put("mon", sqsj.trim().substring(5, 7));
				List<HashMap<String, String>> qgzxlist = zzxmjgService.getQgzxList(model.getXh());
				data.put("qgzxlist", qgzxlist);
				data.put("mdjlist", zzxmjgService.getMjxList(xh, "明德奖学金"));
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = format.format(new java.util.Date());
				data.put("y", time.trim().substring(0, 4));
				data.put("m", time.trim().substring(5, 7));
				data.put("d", time.trim().substring(8, 10));
				if (zzxmjgService.getSfzxDk(xh).get("cs").equals("0")) {
					data.put("zxdk", "否");
				} else {
					data.put("zxdk", "是");
				}
				String sxn = (Integer.parseInt(model.getXn().trim().substring(0, 4)) - 1) + "-" + (Integer.parseInt(model.getXn().trim().substring(5, 9)) - 1);
				HashMap<String, String> sfkns = zzxmjgService.getSfkns(xh, model.getXn());
				if (sfkns.get("dcmc") == null) {
					sfkns.put("dcmc", "家庭经济不困难");
				}
				HashMap<String, String> sfxs = zzxmjgService.getSfxs();
				data.putAll(sfkns);
				if ((sfxs.get("dqnd").trim()).equals(xsjbxx.get("rxrq").trim().substring(0, 4))) {
					sfkns.put("isxs", "是");
				} else {
					sfkns.put("isxs", "否");
				}
				List<HashMap<String, String>> pjjgList = pjjgService.getPjjgGroupByXn(xh);
				if (pjjgList.size() == 0) {
					pjjgList.add(new HashMap<String, String>());
				}
				data.put("pjjgList", pjjgList);
				JtqkdcDao jtqkDao = new JtqkdcDao();
				List<HashMap<String, String>> cyListSXSD = jtqkDao.getJtcyListFour(model.getXh());
				data.put("cyListSXSD", cyListSXSD);//家庭成员列表
				data.put("blankList", 4 > cyList.size() ? jtqkdcService
						.getBlankList(4 - cyList.size()) : jtqkdcService
						.getBlankList(0));

				data.put("jejg", model.getYlzd1());

				ZzxmjgDao zzjgDao = new ZzxmjgDao();
				data.put("zypm", zzjgDao.getXsZyBxPm(sxn, xsjbxx.get("zydm"), xh));
				data.put("zyrs", zzjgDao.getZyrs(xsjbxx.get("zydm")));
				data.put("pjcj", zzjgDao.getBxPjcj(xh, sxn));
				data.put("zdf", zzjgDao.getZdf(xh, sxn));
				data.put("zdfkmmc", zzjgDao.getZdfkmmc(xh, sxn));
			}
			//华中农业大学
			if ("10504".equals(Base.xxdm)) {
				int size6 = (4 - pjjg.size()) <= 0 ? 0 : (4 - pjjg.size());
				data.put("blankList1", jtqkdcService.getBlankList(size6));
				int size7 = (4 - cyList.size()) <= 0 ? 0 : (4 - cyList.size());
				data.put("blankList2", jtqkdcService.getBlankList(size7));

			}

			//家庭人均月收入
			String jtrjysrtemp = " ";
			try {
				jtqkmodel = jtqkmodel == null ? new JtqkdcForm() : jtqkmodel;
				String jtnzsr = jtqkmodel.getJtnzsr() != null ? jtqkmodel.getJtnzsr() : "0";
				String jtrs = jtqkmodel.getJtrs() != null ? jtqkmodel.getJtrs() : "0";
				if (!jtrs.equals("0")) {
					jtrjysrtemp = String.valueOf((int) (Double.valueOf(jtnzsr) / Double.valueOf(jtrs) / 12));
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
			//江西陶瓷
			if("12930".equals(Base.xxdm)) {
				HashMap<String, String> cjpm = pjjgService.getCjPm(model.getXh(),model.getXn());
				data.put("cjpm", cjpm);
				HashMap<String, String> bxkms_12930 = pjjgService.getXakjdxylzjbxkms(model.getXh(),model.getXn());
				//必修课及格门数
				data.put("bxkjgms", bxkms_12930.get("bxkjgms"));
				data.put("bxkms", bxkms_12930.get("bxkms"));
				PjjgService servicePjPy = new PjjgService();
				PjjgAction pjjgAction = new PjjgAction();
				List<HashMap<String, String>> pjjgListhjqk =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgListhjqk, 4 - pjjgListhjqk.size());
				data.put("pjjgListhjqk", pjjgListhjqk);
				int size1=(4 - pjjgListhjqk.size())<0?0:(4 - pjjgListhjqk.size());
				data.put("blankListhjqk", pjjgAction.getBlankList(size1));
				//励志用的评奖
				List<HashMap<String, String>> pjjgList =  pjjgService.getHznydxPjpyMap(xh);
				servicePjPy.addBlankList(pjjgList, 3 - pjjgList.size());
				data.put("pjjgList1", pjjgList);
				int size=(3 - pjjgList.size())<0?0:(3 - pjjgList.size());
				data.put("blankList1", pjjgAction.getBlankList(size));
				ZzxmjgService zzxmjgservice_12930=new ZzxmjgService();
				List<HashMap<String, String>> shyjList = zzxmjgservice_12930.getShyjList(xh, model.getXn(), model.getXq(), model.getXmdm());
				for (int i = 0; i < shyjList.size(); i++) {
					data.put("shyj"+i, shyjList.get(i).get("shyj"));
				}
			}
			data.put("jtrjysrtemp", jtrjysrtemp);// 家庭人均月收入

			String szbbjrs = service.countBjRs(xh);

			data.put("szbbjrs", szbbjrs);  //所在班班级人数

			data.put("je", xmwhModel.getJe());// 申请金额

			data.put("zzsqsj", model.getSqsj() == null ? "" : model.getSqsj().substring(0, model.getSqsj().indexOf(" ")));// 申请时间 yyyy-MM-dd

			if ("10335".equals(Base.xxdm)) {
				//学生申请困难生时间（申请时间取XXXX年XX月）
				HashMap<String, String> knsxx = knsjgService.getKnsxx(model.getXh());
				data.put("knssqsj", DateTranCnDate.fomartDateToCn(knsxx.get("sqsj"), FomartDateType.month));
				//取学生申请奖项时间
				xsjbxx.put("zzxmsqsj", DateTranCnDate.fomartDateToCn(model.getSqsj(), FomartDateType.day));
				//老师审核通过的最终金额
				String zzxmshje = service.zzxmshtgJe(xh, model.getXn(), model.getXq());
				data.put("zzxmshje", zzxmshje);
			}
			//重庆信息职业技术学院
			if ("12755".equals(Base.xxdm)) {
				List<HashMap<String, String>> cyList3line = jtqkdcService.getJtcyList(xh, 3); //取3条家庭成员列表，不足3条填空
				data.put("cyList3line", cyList3line);
				data.put("hkxian", xsjbxx.get("hkxian"));//户口县
				String tbrq = model.getSqsj();// 申请时间
				data.put("y", tbrq.trim().substring(0, 4));
				data.put("m", tbrq.trim().substring(5, 7));
				data.put("d", tbrq.trim().substring(8, 10));
				ZcfsService zcfService = new ZcfsService();
				String bjrs = zcfService.getBjrs(xh);
				String cjpm = zcfService.getCjpm(model.getXn(), xh, xsjbxx.get("bjmc"));
				String bxks = zcfService.getBxks(model.getXn(), xh);
				String bxkjgs = zcfService.getBxkjgs(model.getXn(), xh);
				data.put("bjrs", "".equals(cjpm) ? "" : bjrs);// 班级人数
				data.put("cjpm", cjpm);// 总成绩排名
				data.put("bxks", "0".equals(bxkjgs) ? "" : bxks);// 必修课数
				data.put("bxkjgs", "0".equals(bxkjgs) ? "" : bxkjgs );// 必修课及格数
				String mzmc = xsjbxx.get("mzmc").trim();
				String ssmz = "";
				if("汉族".equals(mzmc)){
					ssmz="否";
				}else if(!"汉族".equals(mzmc) && !"".equals(mzmc) && mzmc != null){
					ssmz="是";
				}
				data.put("ssmz", ssmz);
				PjjgService pjjgservice = new PjjgService();
				List<HashMap<String, String>> pjjg4_12755 = pjjgservice.getHjqkByXhMap(xh, 4);  //获取最新4条评奖结果
				for (HashMap<String, String> hashMap : pjjg4_12755) {
					String xmmc = hashMap.get("xmmc");
					if(xmmc!=null && !"".equals(xmmc)){
						hashMap.put("bjdw","重庆信息技术职业学院 ");
					}
				}
				data.put("pjjg4_12755", pjjg4_12755);
				if (jtqkmodel != null && jtqkmodel.getJtnzsr() != null) {
					data.put("jtrjysr12755", jtqkmodel.getJtrs() == null ? "" :  String.format("%.2f",Double.parseDouble(jtqkmodel.getJtnzsr()) / 12 / Integer.parseInt(jtqkmodel.getJtrs())));//家庭人均月收入
					data.put("jtyzsr12755", String.format("%.2f", Double.parseDouble(jtqkmodel.getJtnzsr()) / 12));//家庭月总收入
				} else {
					data.put("jtyzsr12755", "");
					data.put("jtrjysr","");
				}
				String rxrq = xsjbxx.get("rxrq");
				data.put("rxrq", rxrq);
			}
			//浙江交通职业技术学院
			if("12036".equals(Base.xxdm)){
				List<HashMap<String,String>> shyjjtzyList = new CommShlcImpl().getShyjListByYwid(model.getGuid());
				for (int i = 0; i < shyjjtzyList.size(); i++) {
					data.put("shyj"+i, shyjjtzyList.get(i).get("shyj"));
				}
				String qsbh = null;
				WdgwsqService wdgwsqService = new WdgwsqService();
				HashMap<String, String> qsxx = wdgwsqService.getQsxx(xh);
				if (null == qsxx.get("ldmc") || null == qsxx.get("qsh")) {
					qsbh = "";
				} else {
					qsbh = qsxx.get("ldmc") + "-" + qsxx.get("qsh");
				}
				data.put("qsbh", qsbh);
				data.put("qsxx", qsxx);
			}
			//通用审核意见1-7级
			List<HashMap<String,String>> shyjtyList = new CommShlcImpl().getShyjListByYwid(myForm.getGuid());
			for (int i = 0; i < shyjtyList.size(); i++) {
				data.put("shyjty"+i,shyjtyList.get(i).get("shyj"));
			}
			
			// 按学号加载学生住宿信息
			HashMap<String, String> gyxx = service.getGyxx(xh);
			data.putAll(gyxx);
			File wordFile = null;
			if ("12867".equals(Base.xxdm)) {
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), FreeMarkerUtil.getFileName(xsjbxx.get("bjmc") + "" + xsjbxx.get("xh") + "[" + xsjbxx.get("xm") + "]"));
			} else {
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + bbMap.get("mblj"), bbMap.get("mbmc"), FreeMarkerUtil.getFileName(model.getXh() + "[" + xsjbxx.get("xm") + "]"));// "classpath://templates//" +  "xszz"// +"gjjxjb.xml"
			}
			return wordFile;
		}
		
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:审核撤销
	 * @作者：cmj
	 * @日期：2013-12-16 下午02:49:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生资助-资助管理-资助审核-撤销SHID:{shid}")
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm model = new XszzSqshForm();
		XszzSqshService service = new XszzSqshService();

		String shid = request.getParameter("shid");
		String shlc = request.getParameter("shlc");
		String xh = request.getParameter("xh");
		String xtgwid = request.getParameter("xtgwid");
		model.setShlc(shlc);
		model.setShid(shid);
		model.setXh(xh);
		model.setXtgwid(xtgwid);
		User user = getUser(request);
		HashMap<String, String> shxx = ShlcUtil.getShxx(shid);

		String cancelFlg = service.cxshnew(shxx.get("ywid"), model, user);


		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}


	//=========================学生申请部分开始==============================//

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:学生申请资助页面
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-6 上午10:33:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq_stu.do")
	public ActionForward xmsqStuManage(ActionMapping mapping, ActionForm form,
									   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm model = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		User user = getUser(request); //当前登录学生

		String userType = user.getUserType();//该模块只允许学生访问
		if (!"stu".equalsIgnoreCase(userType)) {
			String msg = "该模块仅允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		String actionType = model.getType(); //是否查询
		if (StringUtils.isEqual(QUERY, actionType)) {
			String xh = user.getUserName();
			model.setXh(xh);
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			List<HashMap<String, Object>> data = service.getXmsqInfoList(model);
			JSONArray dataList = JSONArray.fromObject(data);
			response.getWriter().print(dataList);
			return null;
		}

		List<HashMap<String, String>> xmlbList = service.getXmlb();// 项目类别
		request.setAttribute("xmlbList", xmlbList);

		request.setAttribute("xnxqmc", Base.currXn + " " + DAO.getInstance().getXqmcForXqdm(Base.currXq));
		String path = "xszz_sqsh_xmsq_stu.do";
		request.setAttribute("path", path);
		String currDate = DateUtils.getYear() + DateUtils.getMonth()
				+ DateUtils.getDayOfMonth();
		request.setAttribute("currDate", currDate);
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("SFBJPY_Y", SFBJPY_Y);
		return mapping.findForward("xmsqStuManage");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:学生申请资助页面
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-6 下午02:34:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq_stu.do")
	public ActionForward xszzXmsqStu(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();
		User user = getUser(request);
		myForm.setXh(user.getUserName());

		List<HashMap<String, String>> xmlbList = service.getXmlb();// 项目类别
		request.setAttribute("xmlbList", xmlbList);

		request.setAttribute("currXn", Base.currXn);
		request.setAttribute("currXq", Base.getDqxqmc());
		request.setAttribute("xh", getUser(request).getUserName());
		request.setAttribute("mkxxList", mkxxList);
		//request.setAttribute("jbxxList", jbxxList);
		String path = "xszz_sqsh.do?method=xszzXmsqStu";
		request.setAttribute("path", path);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("mkxxForm", StringUtils.formatData(myForm));
		request.setAttribute("xmdm", myForm.getXmdm());
		this.saveToken(request);
		return mapping.findForward("xszzXmsqStu");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:项目兼得设置检查
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-7 上午09:22:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */

	public ActionForward xszzXmsqChkJdStu(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhJdszService service = new XmwhJdszService();
		XszzSqshService xszzXmsqService = new XszzSqshService();
		XszzSqshForm model = (XszzSqshForm) form;
		model.setXh(getUser(request).getUserName());
		model.setSqType("ysq");

		StringBuffer msg = new StringBuffer();

		boolean isSuccess = true;

		String[] xmdmArr = request.getParameter("xmdmids").split(",");                    //======请求项目代码

		for (String xmdm : xmdmArr) {
			List<HashMap<String, String>> ysqList = xszzXmsqService.getAllYsqXmList(model); //=====已申请项目列表
			List<HashMap<String, String>> resultList = service.getKjdxm(xmdm);                //=====兼得项目列表

			XmwhDao xmwhDao = new XmwhDao();
			HashMap<String, String> data = xmwhDao.getDataById(xmdm);
			String jdkg = "";
			if (data != null) {
				jdkg = data.get("jdkg");
			}

			if (StringUtils.isEqual("1", jdkg)) {
				if (resultList != null && resultList.size() != 0) {
					for (HashMap<String, String> bkjdxmdm : resultList) {
						for (HashMap<String, String> ysqxm : ysqList) {
							if (StringUtils.isEqual(ysqxm.get("xmdm"), bkjdxmdm.get("dm"))) {
								isSuccess = false;
								msg.append(bkjdxmdm.get("mc")).append("已申请,").append(bkjdxmdm.get("sqxmmc")).append("不能申请,奖项不能兼得;");
								break;
							}
						}
					}
					if (isSuccess) {
						for (HashMap<String, String> bkjdxmdm : resultList) {
							for (String sqxmdm : xmdmArr) {
								if (StringUtils.isEqual(sqxmdm, bkjdxmdm.get("dm"))) {
									isSuccess = false;
									msg.append(bkjdxmdm.get("mc")).append(" 已申请 ， ").append(bkjdxmdm.get("sqxmmc")).append(" 不能申请,不能兼得;");
									break;
								}
							}
						}
					}
				}
			}

		}

		response.getWriter().print(getJsonByMsg(msg.toString(), isSuccess));
		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:更新项目申请
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-7 下午01:20:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	@SystemAuth(url = "xszz_sqsh_xmsq_stu.do", rewritable = ReadWrite.WRITEABLE)
	public ActionForward updateXmsqStu(ActionMapping mapping, ActionForm form,
									   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzSqshForm myForm = (XszzSqshForm) form;
		XszzSqshService service = new XszzSqshService();

		XszzSqshForm model = service.getModel(myForm);

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		}
		//获取申请项目信息
		XmwhService xmService = new XmwhService();
		XmwhForm xmwhForm = xmService.getModel(model.getXmdm());
		request.setAttribute("xmwhForm", xmwhForm);
		request.setAttribute("type", UPDATE);
		request.setAttribute("mkxxForm", myForm);
		request.setAttribute("mkxxList", mkxxList);
		return mapping.findForward("updateXmsqStu");
	}

	//=========================学生申请部分结束==============================//

	protected JSONObject getJsonByMsg(String msg, boolean success) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("message", msg);
		map.put("success", String.valueOf(success));
		JSONObject json = JSONObject.fromObject(map);
		return json;
	}

	public ActionForward showXmxx_10335(ActionMapping mapping, ActionForm form,
										HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xmdm = request.getParameter("xmdm");
		XmwhService service = new XmwhService();
		HashMap<String, String> xmxxmap = service.showXmxx_10335(xmdm);
		request.setAttribute("xmxx", xmxxmap);
		return mapping.findForward("showxmxx");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception ActionForward 返回类型
	 * @throws
	 * @描述:困难生申请流程图
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-1-19 上午10:41:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public ActionForward printLct(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		File file = print(request);
		FileUtil.outputWord(response, file);

		return null;
	}

	private synchronized File print(HttpServletRequest request) throws Exception {
		return FreeMarkerUtil.getWordFile(null, Constants.TEP_DIR + "xszz", "xszzsqlct.xml", FreeMarkerUtil.getFileName("申请流程图"));
	}

	/**
	 *  学生资助申请提交异常数据处理.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-17 17:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throw Exception
	 */
	public ActionForward exceptionDataResolve(ActionMapping mapping, ActionForm form,
											  HttpServletRequest request, HttpServletResponse response) throws Exception {

		XszzSqshService service = new XszzSqshService();
		Map<String, String> map = service.exceptionDataResolve();

		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
}
