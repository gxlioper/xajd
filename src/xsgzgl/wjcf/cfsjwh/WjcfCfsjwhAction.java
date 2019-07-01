package xsgzgl.wjcf.cfsjwh;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

import com.ctc.wstx.util.DataUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhService;
/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ltt
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 下午12:36:30
 * </p>
 */
public class WjcfCfsjwhAction extends SuperAction {
	
	private static final String url = "wjcfsjCx.do";

	/**
	 * 正式数据维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward wjcfsjCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("wjcfsjCx.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "xg_view_wjcf_wjcfb");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "xg_wjcf_wjcfb");
		// 学校,管理员按钮操作权限控制
		request.setAttribute("czqx", "admin".equalsIgnoreCase(user
				.getUserType())
				|| "xx".equalsIgnoreCase(user.getUserType()) ? "yes" : "no");
		request.setAttribute("cflbsList", wjcfjcszService.cflbmcCx());// 违纪处分类别
		return mapping.findForward("success");
	}

	/**
	 * 处分数据查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward wjcfsjCxAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		service.initCfcxManage(rForm, request);
		myForm.getSearchModel().setPath("wjcfsjCx.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// =================== end ===================
		List<HashMap<String, String>> topTr = service.getTopTr();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service
				.getCfjgList(myForm);
		// 构建结果集
		String spHtml = service.createSearchSylyHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}

	/**
	 * 数据维护自定义导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward sjkwhExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		WjcfCfsjwhActionForm model = (WjcfCfsjwhActionForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String, String>> resultList = (List<HashMap<String, String>>) service
				.getCfjgExportList(model, user);
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
	 * 增加处分信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward wjcfsjZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
		request.setAttribute("rs", stuService.selectStuinfo(xh));
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());// 违纪处分类别
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());// 违纪处分原因
		request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(xh));// 已受违纪处分
		// 设置学年学期列表
		FormModleCommon.setNdXnXqList(request);
		this.saveToken(request);
		return mapping.findForward("wjcfsjZj");

	}

	/**
	 * 保存处分上报
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问违纪处分-处分正式库-数据维护-增加XH:{xh},XN:{xn},XQ:{xq},CFYYDM:{cfyydm},CFLBDM:{cflbdm},WJSJ:{wjsj},CFWH:{cfwh},CFSJ:{cfsj}")
	public ActionForward savewjcfsjZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		User user = getUser(request);// 用户对象
		myForm.setSbb(user.getUserName());
		myForm.setSbsj(GetTime.getNowTime2());
		boolean flag = service.saveCfsb(myForm);
		// request.setAttribute("isSuccess", flag==true?"true":"false");
		//		
		// request.setAttribute("message",flag?"操作成功！":"操作失败！");

		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;

		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		//		
		// wjcfsjZj(mapping, form, request, response);
		// return mapping.findForward("wjcfsjZj");
	}

	/**
	 * 修改处分信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward wjcfsjXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		myForm.setCfid(request.getParameter("cfid"));
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();

		// 查询处分情况
		HashMap<String, String> rs = service.cfsjwhCk(myForm.getCfid());

		request.setAttribute("rs", xgxt.utils.String.StringUtils.formatData(rs));
		request.setAttribute("cflbList", wjcfjcszService.cflbmcCx());// 违纪处分类别
		request.setAttribute("cfyyList", wjcfjcszService.cfyymcCx());// 违纪处分原因
		request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(rs
				.get("xh")));// 已受违纪处分
		// 设置学年学期列表
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("wjcfsjXg");

	}

	/**
	 * 修改处分信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问违纪处分-处分正式库-数据维护-修改CFID:{cfid},XH:{xh},XN:{xn},XQ:{xq},CFYYDM:{cfyydm},CFLBDM:{cflbdm},WJSJ:{wjsj},CFWH:{cfwh},CFSJ:{cfsj}")
	public ActionForward saveWjcfsjXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		User user = getUser(request);// 用户对象
		myForm.setSbb(user.getUserName());
		myForm.setSbsj(DealString.getDateTime());
		boolean flag = service.cfsjwhXg(myForm);
		// request.setAttribute("isSuccess", flag==true?"true":"false");
		// request.setAttribute("message",flag?"操作成功！":"操作失败！");
		// wjcfsjXg(mapping, form, request, response);
		// return mapping.findForward("wjcfsjXg");
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 修改处分信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward wjcfsjCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
		myForm.setCfid(request.getParameter("cfid"));

		// 查询处分情况
		HashMap<String, String> rs = service.cfsjwhCk(myForm.getCfid());
		request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(rs
				.get("xh")));// 已受违纪处分
		request.setAttribute("rs", rs);
		CflbdmwhService cflbdmwhSv = new CflbdmwhService();
		String cflbmc = rs.get("cflbmc");
		String cfqx = cflbdmwhSv.getCfqxByMc(cflbmc);
		request.setAttribute("cfqx", xgxt.utils.String.StringUtils.formatData(cfqx));
		return mapping.findForward("wjcfsjCk");
	}

	/**
	 * 删除处分信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问违纪处分-处分正式库-数据维护-删除PK：{pkValue}")
	public ActionForward wjcfsjSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		String message = service.cfsjwhSc(service.unicode2Gbk(
				request.getParameter("pkValue")).split("!!array!!")) ? "删除成功！"
				: "删除失败！";
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * 保存处分申诉结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWjcfssjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		boolean flag = service.cfssjgBc(myForm);

		request.setAttribute("isSuccess", flag == true ? "true" : "false");

		request.setAttribute("message", flag ? "操作成功！" : "操作失败！");

		wjcfsjCx(mapping, form, request, response);
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @描述:处分申述弹出层保存
	 * @作者：Dlq[工号：995]
	 * @日期：2013-8-30 下午03:23:39
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWjcfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		boolean flag = service.cfssjgBc(myForm);

		request.setAttribute("isSuccess", flag == true ? "true" : "false");
		String message = flag ? "操作成功！" : "操作失败！";
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		wjcfsjCx(mapping, form, request, response);

		return null;
	}

	/**
	 * 
	 * @描述处分申述弹出层
	 * @作者：Dlq[工号：995]
	 * @日期：2013-8-30 下午03:24:18
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
	@SystemAuth(url = url)
	public ActionForward wjcfCfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		HashMap<String, String> result = service.cfsjwhCk(request
				.getParameter("cfid"));
		if (result != null && result.size() > 0) {
			CflbdmwhService cflbdmwhSv = new CflbdmwhService();
			WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
			String cfqx = "";
			if (!StringUtils.isEmpty(result.get("cflbmc"))) {
				cfqx = cflbdmwhSv.getCfqxByMc(result.get("cflbmc"));
			}
			request.setAttribute("cfqx", xgxt.utils.String.StringUtils.formatData(cfqx));
			request.setAttribute("wjcfss", result);
			request.setAttribute("cflbsList", wjcfjcszService.cflbmcCx());
		}
		return mapping.findForward("wjcfsjCfss");
	}

	/**
	 * 查看处分申诉详情
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward cfsssjCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		HashMap<String, String> result = service.cfsjwhCk(request
				.getParameter("pkValue"));
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(result));
		return null;

	}

	/**
	 * 保存处分解除结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWjcfjcjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		boolean flag = service.cfjcjgBc(myForm);

		request.setAttribute("message", flag ? "操作成功！" : "操作失败！");

		wjcfsjCx(mapping, form, request, response);
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @描述:处分解除弹出层
	 * @作者：Dlq[工号：995]
	 * @日期：2013-8-30 下午03:24:43
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
	@SystemAuth(url = url)
	public ActionForward wjcfCfjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		HashMap<String, String> result = service.cfsjwhCk(request
				.getParameter("cfid"));
		if (result != null && result.size() > 0) {
			myForm.setJcsj(result.get("jcsj"));
			myForm.setJcwh(result.get("jcwh"));
			myForm.setJcyj(result.get("jcyj"));
		}
		return mapping.findForward("wjcfsjCfjc");
	}

	/**
	 * 
	 * @描述:处分终止弹出层
	 * @作者：Dlq[工号：995]
	 * @日期：2013-8-30 下午03:24:43
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
	@SystemAuth(url = url)
	public ActionForward wjcfCfzz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;

		WjcfCfsjwhService service = new WjcfCfsjwhService();
		HashMap<String, String> result = service.cfsjwhCk(request
				.getParameter("cfid"));
		if (result != null && result.size() > 0) {
			myForm.setZzsj(result.get("zzsj"));
			myForm.setZzwh(result.get("zzwh"));
			myForm.setZzyj(result.get("zzyj"));
		}
		return mapping.findForward("wjcfsjCfzz");
	}

	public ActionForward getKzzFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		CflbdmwhService cflbdmwhSv = new CflbdmwhService();
		String message = "";
		if (StringUtils.isNotEmpty(myForm.getCflbmc())) {
			boolean flag = cflbdmwhSv.zzwjcfFlag(myForm.getCflbmc(), myForm
					.getCfsj());
			if (!flag) {
				message = MessageUtil.getText(MessageKey.WJCF_CFLBMC_BKZZ,
						myForm.getCflbmc());
			}
		}
		response.getWriter().print(getJsonMessage(message));
		return null;
	}

	/**
	 * 
	 * @描述:处分解除弹出层保存
	 * @作者：Dlq[工号：995]
	 * @日期：2013-8-30 下午03:25:03
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWjcfjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		boolean flag = service.cfjcjgBc(myForm);
		wjcfsjCx(mapping, form, request, response);
		String message = flag ? "操作成功！" : "操作失败！";
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		wjcfsjCx(mapping, form, request, response);
		return null;

	}

	/**
	 * 
	 * @描述:处分终止弹出层保存
	 * @作者：wanghj[工号：1004]
	 * @日期：2013-11-25 下午04:55:17
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWjcfzz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		boolean flag = service.cfzzjgBc(myForm);
		wjcfsjCx(mapping, form, request, response);
		String message = flag ? "操作成功！" : "操作失败！";
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		wjcfsjCx(mapping, form, request, response);
		return null;

	}

	/**
	 * 附件下载
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward fjxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ DealString.toUtf8String(myForm.getFjmc()));
		InputStream in = service.fjCx(myForm);
		in = new BufferedInputStream(in);
		while ((in.read(b)) != -1) {
			response.getOutputStream().write(b);
		}
		return null;
	}

	/**
	 * 
	 * @描述:下载申请表格
	 * @作者：zhangxiaobin[工号：1036]
	 * @日期：2014-3-28 下午02:11:52
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
	@SystemAuth(url = url)
	public ActionForward doPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		// 查询处分情况
		HashMap<String, String> data = service.cfsjwhCk(myForm.getCfid());
		HashMap<String, Object> objectData = new HashMap<String, Object>();
		objectData.putAll(data);
		File file = null;
		String guid = "wjcf_jccfsq_10351"; // 文件配置id
		objectData.put("xxmc", Base.xxmc);
		file = BbdmUtils.getSigleFile(guid, objectData);
		FileUtil.outputWord(response, file);
		return null;
	}

	/**
	 * 
	 * @描述:打印违纪处分通知书(上海中侨职业技术学院)
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-21 上午11:51:05
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
	@SystemAuth(url = url)
	public ActionForward doPrintWjcfWord(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		File wordFile = getWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	private File getWord(WjcfCfsjwhActionForm myForm, HttpServletRequest request)
			throws Exception {
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		// 查询处分情况
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> cfData = service.cfsjwhCk(myForm.getCfid());
		String cfsjFormat = cfData.get("cfsj");
		cfsjFormat=cfsjFormat.replace("-", "/");
		data.putAll(cfData);
		String formatTime = DateTranCnDate.fomartDateToCn(cfData.get("cfsj"), FomartDateType.day);
		data.put("cfsjFormatCN", formatTime);
		data.put("cfsjFormat", cfsjFormat);
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf",
				"wjcftzs_12915.xml", cfData.get("xh") + "-" + cfData.get("xm"));
		return file;

	}
	/**
	 * 
	 * @描述:处分决定书下载(浙江商业职业技术学院)
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-1 上午11:00:03
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
	@SystemAuth(url = url)
	public ActionForward doPrintCfjdWord(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		File wordFile = getcfjdWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	private File getcfjdWord(WjcfCfsjwhActionForm myForm, HttpServletRequest request)
			throws Exception {
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		// 查询处分情况
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> cfData = service.cfsjwhCk(myForm.getCfid());
		data.putAll(cfData);
		//查询是否党团员和职务寝室
		HashMap<String, String>  zwAndZzmm = service.getZwAndZzmm(cfData.get("xh"));
		data.put("sfdty", zwAndZzmm.get("sfdty"));
		data.put("zw", zwAndZzmm.get("zwmc"));
		System.out.println("ldmc:"+zwAndZzmm.get("ldmc")+"-"+zwAndZzmm.get("qsh"));
		data.put("qsbh", zwAndZzmm.get("ldmc")+"-"+zwAndZzmm.get("qsh"));
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf",
				"cfjds_12865.xml", cfData.get("xh") + "-" + cfData.get("xm"));
		return file;

	}
	
	/**
	 * 
	 * @描述:处分决定书下载(玉林师范学院)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-14 下午02:04:27
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
	@SystemAuth(url = url)
	public ActionForward getCfjdsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		
		File wordFile = getCfjdsbWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	
	private File getCfjdsbWord(WjcfCfsjwhActionForm myForm,HttpServletRequest request) throws Exception{
		
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		
		//查询处分决定书信息
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> cfData = service.cfjdsxx(myForm.getCfid());
		
		data.put("xh",cfData.get("xh"));
		data.put("dysj",DateTranCnDate.fomartDateToCn(cfData.get("dysj"),FomartDateType.day));
		data.put("wjsj",DateTranCnDate.fomartDateToCn(cfData.get("wjsj"),FomartDateType.day));
		data.put("cflbmc",cfData.get("cflbmc"));
		data.put("cfsj",DateTranCnDate.fomartDateToCn(cfData.get("cfsj"),FomartDateType.day));
		data.put("csrq",DateTranCnDate.fomartDateToCn(cfData.get("csrq"),FomartDateType.month));
		data.put("wjssjg",cfData.get("wjssjg"));
		data.put("xm",cfData.get("xm"));
		data.put("xb",cfData.get("xb"));
		data.put("xymc",cfData.get("xymc"));
		data.put("bjmc",cfData.get("bjmc"));
		data.put("zzmmmc",cfData.get("zzmmmc"));
		data.put("cfyj",cfData.get("cfyj"));
			
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf",
				"cfjds_10606.xml", cfData.get("xh") + "-" + cfData.get("xm"));
		return file;
	}
	
	
	/**
	 * 
	 * @描述:拟处分告知书下载(玉林师范学院)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-14 下午03:15:03
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
	@SystemAuth(url = url)
	public ActionForward getNcfgzsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		
		File wordFile = getNcfgzsbWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getNcfgzsbWord(WjcfCfsjwhActionForm myForm,HttpServletRequest request) throws Exception{
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		
		//查询拟处分告知书信息
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> cfData = service.cfjdsxx(myForm.getCfid());
		
		data.put("xh",cfData.get("xh"));
		data.put("wjsj",DateTranCnDate.fomartDateToCn(cfData.get("wjsj"),FomartDateType.day));
		data.put("cflbmc",cfData.get("cflbmc"));
		data.put("cfsj",DateTranCnDate.fomartDateToCn(cfData.get("cfsj"),FomartDateType.day));
		data.put("csrq",DateTranCnDate.fomartDateToCn(cfData.get("csrq"),FomartDateType.month));
		data.put("wjssjg",cfData.get("wjssjg"));
		data.put("xm",cfData.get("xm"));
		data.put("xb",cfData.get("xb"));
		data.put("xymc",cfData.get("xymc"));
		data.put("bjmc",cfData.get("bjmc"));
		data.put("zymc",cfData.get("zymc"));
		data.put("cfyj",cfData.get("cfyj"));
			
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf",
				"ncfgzs_10606.xml", cfData.get("xh") + "-" + cfData.get("xm"));
		return file;
	}
	
	/**
	 * 
	 * @描述:处分意见书下载(河北工业大学)
	 * @作者：姜舟[工号：1529]
	 * @日期：2018-3-2 
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
	public ActionForward getCftzswjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WjcfCfsjwhActionForm myForm = (WjcfCfsjwhActionForm) form;
		
		File wordFile = getCftzswjbWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	private File getCftzswjbWord(WjcfCfsjwhActionForm myForm,HttpServletRequest request) throws Exception{
		WjcfCfsjwhService service = new WjcfCfsjwhService();
		
		//查询拟处分告知书信息
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> cfData = service.cfjdsxx(myForm.getCfid());
		
		data.put("xh",cfData.get("xh"));
		data.put("wjsj",DateTranCnDate.fomartDateToCn(cfData.get("wjsj"),FomartDateType.day));
		data.put("cflbmc",cfData.get("cflbmc"));
		data.put("cfsj",DateTranCnDate.fomartDateToCn(cfData.get("cfsj"),FomartDateType.day));
		data.put("wjssjg",cfData.get("wjssjg"));
		data.put("xm",cfData.get("xm"));
		data.put("xb",cfData.get("xb"));
		data.put("xymc",cfData.get("xymc"));
		data.put("bjmc",cfData.get("bjmc"));
		data.put("zymc",cfData.get("zymc"));
		data.put("cfyj",cfData.get("cfyj"));
		data.put("sfzh",cfData.get("sfzh"));
		data.put("jgmc",cfData.get("jgmc"));
		data.put("zzmmmc",cfData.get("zzmmmc"));
		data.put("mzmc",cfData.get("mzmc"));
		String rq = GetTime.getTimeByFormat("yyyy-mm-dd");
		String[] csrqArr = cfData.get("csrq").split("-");
		String[] xnArr = rq.split("-");
		data.put("csrq",csrqArr[0]+"年"+csrqArr[1]+"月"+csrqArr[2]+"日");
		data.put("nowTime", DateUtils.numToZh(xnArr[0])+"年"+DateUtils.numToZh(xnArr[1])+"月"+DateUtils.numToZh(xnArr[2])+"日");
			
		File file = null;
		String cflbmc = cfData.get("cflbmc");
		if("警告".equals(cflbmc)||"严重警告".equals(cflbmc)||"记过处分".equals(cflbmc)){
			file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf",
					"wjcfyj_10080.xml", cfData.get("xh") + "-" + cfData.get("xm"));
		}else{
			file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf",
					"wjcfyj2_10080.xml", cfData.get("xh") + "-" + cfData.get("xm"));
		}
		return file;
	}
}
