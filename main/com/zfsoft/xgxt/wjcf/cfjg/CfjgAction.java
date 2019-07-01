/**
 * @部门:学工产品事业部
 * @日期：2014-8-5 上午11:40:39 
 */
package com.zfsoft.xgxt.wjcf.cfjg;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jcp.xml.dsig.internal.dom.Utils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.bbdmpz.utils.BbdmUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbService;
import xsgzgl.wjcf.jcsz.WjcfJcszServices;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 处分结果库
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-8-5 上午11:40:39
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class CfjgAction extends SuperAction {
	BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	private final static String CFSBZDPZ="cfsbzdpz";
	
	private static final String url = "wjcf_cfjg_new.do";
	CfjgService service = new CfjgService();
	/**
	 * 
	 * @描述:处分结果查询
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-5 下午12:51:01
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
	public ActionForward cxCfjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		CfjgForm myForm = (CfjgForm) form;
		CommService cs = new CommService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("wjcf_cfjg_new.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "wjcf_cfjg_new.do";
		request.setAttribute("path", path);
		request.setAttribute("cflbsList", wjcfjcszService.cflbmcCx());// 违纪处分类别
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxCfjgList");
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
	public ActionForward cfjgZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		CfjgForm myForm = (CfjgForm) form;
		
		//青岛酒店管理职业技术学院，生成处分文号
		if("13011".equals(Base.xxdm)){
			String cfwh = service.getDefaultCfwhFor13011();
			myForm.setCfwh(cfwh);
		}
		
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
		//学生基本信息显示配置
		if (!StringUtil.isNull(xh)){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		String path = "wjcf_cfjg.do?method=cfjgZj";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());// 违纪处分类别
		request.setAttribute("cfyyList", wjcfjcszService.cfyydmCx());// 违纪处分原因
		request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(xh));// 已受违纪处分
		// 设置学年学期列表
		FormModleCommon.setNdXnXqList(request);
		
		User user = getUser(request);
		request.setAttribute("sbbxm", user.getRealName());
		request.setAttribute("xnndList", service.getXnndList());
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("cfjgZj");

	}

	/**
	 * 
	 * @描述:处分增加保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-6 下午02:13:55
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
	@SystemLog(description = "访问违纪处分-处分正式库-处分结果-增加XH:{xh},XN:{xn},XQ:{xq},CFYYDM:{cfyydm},CFLBDM:{cflbdm},WJSJ:{wjsj},CFWH:{cfwh},CFSJ:{cfsj}")
	public ActionForward saveCfjgZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		CfjgForm myForm = (CfjgForm) form;
		User user = getUser(request);// 用户对象
		myForm.setSbb(user.getUserName());
		myForm.setSbsj(GetTime.getNowTime4());
		if("12915".equals(Base.xxdm)){
			myForm.setCflsh(service.getLsh(myForm));
		}
		else{
			myForm.setCflsh("");
		}
		if(!service.checkIsNotRepeat(myForm)){
			String message = "本学年本学期已有处分记录，请勿重复填写记录！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean flag = service.saveCfsb(myForm);
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
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
	public ActionForward cfjgXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		String sjly = request.getParameter("sjly");//数据来源  1走审核流0不走审核流
		CfjgForm myForm = (CfjgForm) form;
		myForm.setCfid(request.getParameter("cfid"));
		WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
		WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
		// 查询处分情况
		HashMap<String, String> rs = service.cfsjwhCk(myForm.getCfid());
		//学生基本信息显示配置
		if (!StringUtil.isNull(xh)){
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		jbxxList = bdpzService.getJbxxpz(CFSBZDPZ);
		String path = "wjcf_cfjg.do?method=cfjgXg";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", StringUtils.formatData(rs));
		request.setAttribute("cflbList", wjcfjcszService.cflbdmCx());// 违纪处分类别
		request.setAttribute("cfyyList", wjcfjcszService.cfyymcCx());// 违纪处分原因
		request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(rs.get("xh")));// 已受违纪处分
		request.setAttribute("type", UPDATE);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("xnndList", service.getXnndList());
		// 设置学年学期列表
		FormModleCommon.setNdXnXqList(request);
		if ("1".equals(sjly)) {//走审核流的和不走审核流的页面不同。
			return mapping.findForward("cfjgshlcXg");
		}else {
			return mapping.findForward("cfjgXg");
		}

	}

	/**
	 * 
	 * @描述:不走流程的处分修改保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-6 下午02:15:22
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
	@SystemLog(description = "访问违纪处分-处分正式库-处分结果-修改CFID:{cfid},XH:{xh},XN:{xn},XQ:{xq},CFYYDM:{cfyydm},CFLBDM:{cflbdm},WJSJ:{wjsj},CFWH:{cfwh},CFSJ:{cfsj}")
	public ActionForward saveCfjgXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		User user = getUser(request);// 用户对象
		myForm.setSbb(user.getUserName());
		myForm.setSbsj(DealString.getDateTime());
		if(!service.checkIsNotRepeat(myForm)){
			String message = "本学年本学期已有处分记录，请勿重复填写记录！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean flag = service.cfsjwhXg(myForm);
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:走流程的处分修改保存
	 * @作者：CP[工号：1352]
	 * @日期：2017-2-16 下午11:21:34
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问违纪处分-处分正式库-处分结果-修改CFID:{cfid},XH:{xh},CFWH:{cfwh},CFSJ:{cfsj}")
	public ActionForward saveCfjgshlcXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		User user = getUser(request);// 用户对象
		myForm.setSbb(user.getUserName());
		myForm.setSbsj(DealString.getDateTime());
		boolean flag = service.cfsjshlcXg(myForm);
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 查看处分信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward cfjgCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
		myForm.setCfid(request.getParameter("cfid"));

		// 查询处分情况
		HashMap<String, String> rs = service.cfsjwhCk(myForm.getCfid());
		request.setAttribute("yscfqkList", wjcfcfsbService.getYscfqk(rs.get("xh")));// 已受违纪处分
		request.setAttribute("rs", StringUtils.formatData(rs));
		CflbdmwhService cflbdmwhSv = new CflbdmwhService();
		String cflbmc = rs.get("cflbmc");
		String cfqx = cflbdmwhSv.getCfqxByMc(cflbmc);
		request.setAttribute("cfqx", cfqx);
		return mapping.findForward("cfjgCk");
	}

	/**
	 * 
	 * @描述:删除处分信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-6 下午03:51:00
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问违纪处分-处分正式库-处分结果-删除VALUES:{values}")
	public ActionForward cfjgSc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgService service = new CfjgService();
		String values = request.getParameter("values");
		String[] valueList = values.split(",");
		String messageKey = service.cfsjwhSc(valueList) ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
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
	public ActionForward saveWjcfssjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		boolean flag = service.cfssjgBc(myForm);
		request.setAttribute("isSuccess", flag == true ? "true" : "false");
		request.setAttribute("message", flag ? "操作成功！" : "操作失败！");
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
	public ActionForward saveWjcfss(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		boolean flag = service.cfssjgBc(myForm);

		request.setAttribute("isSuccess", flag == true ? "true" : "false");
		String message = flag ? "操作成功！" : "操作失败！";
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}

	/**
	 * 
	 * @描述:处分申诉
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-6 下午02:03:19
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
	public ActionForward cfjgSs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgService service = new CfjgService();
		HashMap<String, String> result = service.cfsjwhCk(request.getParameter("cfid"));
		if (result != null && result.size() > 0) {
			CflbdmwhService cflbdmwhSv = new CflbdmwhService();
			WjcfJcszServices wjcfjcszService = new WjcfJcszServices();
			String cfqx = "";
			if (!StringUtils.isNull(result.get("cflbmc"))) {
				cfqx = cflbdmwhSv.getCfqxByMc(result.get("cflbmc"));
			}
			request.setAttribute("cfqx", cfqx);
			request.setAttribute("wjcfss", result);
			request.setAttribute("cflbsList", wjcfjcszService.cflbmcCx());
		}
		return mapping.findForward("cfjgSs");
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
	public ActionForward cfsssjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgService service = new CfjgService();
		HashMap<String, String> result = service.cfsjwhCk(request.getParameter("cfid"));
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(StringUtils.formatData(result)));
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
	public ActionForward saveWjcfjcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		boolean flag = service.cfjcjgBc(myForm);
		request.setAttribute("message", flag ? "操作成功！" : "操作失败！");
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @描述:处分解除
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-13 上午10:49:18
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cfjgJc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		HashMap<String, String> result = service.cfsjwhCk(request.getParameter("cfid"));
		if (result != null && result.size() > 0) {
			myForm.setJcsj(result.get("jcsj"));
			myForm.setJcwh(result.get("jcwh"));
			myForm.setJcyj(result.get("jcyj"));
		}
		return mapping.findForward("cfjgJc");
	}

	/**
	 * 
	 * @描述:处分终止
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-13 上午10:49:01
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cfjgZz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;

		CfjgService service = new CfjgService();
		HashMap<String, String> result = service.cfsjwhCk(request.getParameter("cfid"));
		if (result != null && result.size() > 0) {
			myForm.setZzsj(result.get("zzsj"));
			myForm.setZzwh(result.get("zzwh"));
			myForm.setZzyj(result.get("zzyj"));
		}
		request.setAttribute("cfid", request.getParameter("cfid"));
		return mapping.findForward("cfjgZz");
	}

	public ActionForward getKzzFlag(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CflbdmwhService cflbdmwhSv = new CflbdmwhService();
		String message = "";
		if (StringUtils.isNotNull(myForm.getCflbmc())) {
			boolean flag = cflbdmwhSv.zzwjcfFlag(myForm.getCflbmc(), myForm.getCfsj());
			if (!flag) {
				message = MessageUtil.getText(MessageKey.WJCF_CFLBMC_BKZZ, myForm.getCflbmc());
			}
		}
		response.getWriter().print(getJsonMessage(message));
		return null;
	}

	/**
	 * 
	 * @描述:处分解除保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-13 上午10:48:39
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWjcfjc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		boolean flag = service.cfjcjgBc(myForm);
		String message = flag ? "操作成功！" : "操作失败！";
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		
		return null;

	}

	/**
	 * 
	 * @描述:处分终止保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-13 上午10:48:07
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveWjcfzz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		boolean flag = service.cfzzjgBc(myForm);
		String message = flag ? "操作成功！" : "操作失败！";
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward fjxz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
		byte b[] = new byte[500];
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + DealString.toUtf8String(myForm.getFjmc()));
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward doPrint(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		CfjgService service = new CfjgService();
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward doPrintWjcfWord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		File wordFile = getWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	private File getWord(CfjgForm myForm, HttpServletRequest request) throws Exception {
		CfjgService service = new CfjgService();
		// 查询处分情况
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> cfData = service.cfsjwhCk(myForm.getCfid());
		String cfsjFormat = cfData.get("cfsj");
		cfsjFormat = cfsjFormat.replace("-", "/");
		data.putAll(cfData);
		String formatTime = DateTranCnDate.fomartDateToCn(cfData.get("cfsj"), FomartDateType.day);
		String cfbh=Base.currNd+cfData.get("xq")+cfData.get("cflsh");
		data.put("cfsjFormatCN", formatTime);
		data.put("cfsjFormat", cfsjFormat);
		data.put("tzsbh", cfbh);
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", "wjcftzs_12915.xml", cfData.get("xh") + "-" + cfData.get("xm"));
		return file;

	}

	/**
	 * 
	 * @描述:处分决定书批量打印
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-6 上午10:20:37
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward doPrintCfjdWordZip(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		String value = request.getParameter("cfid");
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				myForm.setCfid(values[i]);
				File file = getcfjdWord(myForm, request);
				if(null!=file){
				files.add(file);
				}
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}

		return null;
		
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
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward doPrintCfjdWord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgForm myForm = (CfjgForm) form;
		File wordFile = getcfjdWord(myForm, request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}

	private File getcfjdWord(CfjgForm myForm, HttpServletRequest request) throws Exception {
		CfjgService service = new CfjgService();
		// 查询处分情况
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, String> cfData = service.cfsjwhCk(myForm.getCfid());
		data.putAll(cfData);
		// 查询是否党团员和职务寝室
		HashMap<String, String> zwAndZzmm = service.getZwAndZzmm(cfData.get("xh"));
		data.put("sfdty", zwAndZzmm.get("sfdty"));
		data.put("zw", zwAndZzmm.get("zwmc"));
		if(null==zwAndZzmm.get("ldmc")||null==zwAndZzmm.get("qsh")){
			data.put("qsbh", "");
		}else{
			data.put("qsbh", zwAndZzmm.get("ldmc") + "-" + zwAndZzmm.get("qsh"));
		}
		
		File file = null;
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", "cfjds_12865.xml", cfData.get("xh") + "-" + cfData.get("xm"));
		return file;

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
	@SystemAuth(url = url)
	public ActionForward sjkwhExportData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgService service = new CfjgService();
		CfjgForm model = (CfjgForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String, String>> resultList = (List<HashMap<String, String>>) service.getCfjgExportList(model, user);
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
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-5 上午09:22:12
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
	public ActionForward initCfwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfjgService service = new CfjgService();
		CfjgForm model = (CfjgForm) form;
		String cflsh = service.getLsh2(model);
		String cfwh =  MessageUtil.getText(MessageKey.WJCF_CFWH_FORMAT, new String[] {model.getNd(),cflsh});
		response.getWriter().print(getJsonMessage(cfwh));
		return null;
	}
	
	/**
	 * @描述:处分决定书下载（乌海职业技术学院：同类别同文号多条记录合并为单个文件）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月15日 上午9:45:36
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cfjdsDownload(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		//获取cfids
		String cfidsStr = request.getParameter("cfids");
		String [] cfids = null;
		
		if(StringUtils.isNotNull(cfidsStr)){
			cfids = cfidsStr.split(",");
		}
		
		//查询处分结果信息（列表），再传入获取文件的方法
		List<HashMap<String,String>> cfjgList = service.getCfjgList(cfids);
		//根据处分类别和处分文号进行记录合并操作
		Map<String,List<HashMap<String,String>>> cfjgListMap = service.getCfjgListMap(cfjgList);
		//生成word文件数组
		File[] files = service.getCfjdsFiles(cfjgListMap);
		
		if(files.length>1){
			File zipFile = ZipUtil.zip(files);
			FileUtil.outputZip(response, zipFile);
		}else{
			FileUtil.outputWord(response, files[0]);
		}
		
		return null;
	}

	/**
	 * @描述:处分审批表下载（乌海职业技术学院：需要取审核流程信息）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月15日 上午9:45:36
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cfspbDownload(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		//获取cfids
		String cfidsStr = request.getParameter("cfids");
		String [] cfids = null;
		
		if(StringUtils.isNotNull(cfidsStr)){
			cfids = cfidsStr.split(",");
		}
		
		//查询处分结果信息（列表），再传入获取文件的方法
		List<HashMap<String,String>> cfjgList = service.getCfjgMoreList(cfids);
		//生成word文件数组
		File[] files = service.getCfspbFiles(cfjgList);
		
		if(files.length>1){
			File zipFile = ZipUtil.zip(files);
			FileUtil.outputZip(response, zipFile);
		}else{
			FileUtil.outputWord(response, files[0]);
		}
		
		return null;
	}
	
	/**
	 * @描述: 石家庄铁路职业技术学院，学生处分决定书
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-8-2 上午10:08:26
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
	public ActionForward getCfjdsDjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		CfjgForm model = (CfjgForm)form;
		String cfid = model.getCfid();
		File wordFile = getCfsWord(cfid);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @描述: 学生处分决定书批量打印
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-8-2 上午10:19:49
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
	public ActionForward getCfjdsDjbZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String value = request.getParameter("value");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getCfsWord(values[i]);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @描述: 学生处分决定书打印
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-8-2 上午10:22:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getCfsWord (String cfid) throws Exception {
		
		CfjgService service = new CfjgService();
		/*获得处分信息*/
		//HashMap<String, String> cfxx = service.cfsjwhCk(cfid);
		HashMap<String, String> cfxx = service.getCfxxByCfid(cfid);
		String xh = cfxx.get("xh");
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		XsxxService xsxxService = new XsxxService();
		XsxxglService xsxxglService = new XsxxglService();
		
		/*加载学生基本信息*/
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
		data.putAll(xsxxMap);
		
		/*获得打印日期*/
		String dyrq = xsxxglService.getDqrq(xh);
		data.put("dyrq", DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
	
		/*处分原因、依据、类别*/
		data.put("wjssjg", HtmlUtil.xmlZy(cfxx.get("wjssjg")));
		data.put("cfyj", HtmlUtil.xmlZy(cfxx.get("cfyj")));
		data.put("cflbmc", cfxx.get("cflbmc"));
		
		/*根据学号查询已受违纪处分*/
		WjcfCfsbService wjcfcfsbService = new WjcfCfsbService();
		List<HashMap<String,String>> yscfqkList = wjcfcfsbService.getYscfqkNotId(xh, cfid);
		data.put("yscfqkList", yscfqkList);
		
		/*根据学号查询已获奖信息*/
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String,String>> pjjgList = pjjgService.getPjpyInfoList(xh);
		data.put("pjjgList", pjjgList);
		
		File file = null;
		file = FreeMarkerUtil.getWordFile(data,"classpath://templates//wjcf","cfjds_12424.xml",FreeMarkerUtil.getFileName(xh+"-"+xsxxMap.get("xm")));
		return file;
	}
	
	/**
	 * @描述: 浙江警官职业学院个性化——单个打印违纪处理单
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-13 下午05:14:52
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
	public ActionForward getWjcldOne (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CfjgForm model = (CfjgForm)form;
		/*获取url带过来的请假申请id*/
		String cfid = request.getParameter("id");
		/*获取文件信息*/
		File wordFile = getWordForWjcld(cfid);
		/*输出文件*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @描述: 浙江警官职业学院个性化——批量打印违纪处理单
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-13 下午05:23:09
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
	public ActionForward getWjcldZip (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*获取url带过来的Value*/
		String value = request.getParameter("value");
		/*判断value是否为空*/
		if(StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for(int i = 0, n = values.length; i < n; i++){
				File file = getWordForWjcld(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputWord(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @描述: 违纪处理单数据输出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-13 下午05:45:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfid
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForWjcld (String id) throws Exception{
		
		CfjgService service = new CfjgService();
		
		Map<String,Object> data = new HashMap<String,Object>();
		/*定义一个空file*/
		File file = null;
		
		/*根据所选id获取学生违纪信息*/
		RcxwjgService rcxwjgService = new RcxwjgService();
		HashMap<String, String> rs = rcxwjgService.getKptzsForId(id);
		data.put("rs", rs);
		
		/*加载学生基本信息*/
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(rs.get("xh"));
		data.putAll(xsxxMap);
		
		/*分割处分时间年、月、日、时*/
		String cfsjYear = rs.get("fssj").substring(0, 4);
		String cfsjMonth = rs.get("fssj").substring(5, 7);
		String cfsjDay = rs.get("fssj").substring(8, 10);
		data.put("cfsjYear", cfsjYear);
		data.put("cfsjMonth", cfsjMonth);
		data.put("cfsjDay", cfsjDay);
		
		/*取发生时间的后两天，2月份也处理过了*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date nextTwoDay = com.zfsoft.xgxt.base.export.util.DateUtils.addDays(format.parse(rs.get("fssj")), 2);
		String nextTwoDayForString = format.format(nextTwoDay);
		
		/*取后两天的月、日*/
		String nextMonth = nextTwoDayForString.substring(5, 7);
		String nextDay = nextTwoDayForString.substring(8, 10);
		data.put("nextMonth", nextMonth);
		data.put("nextDay", nextDay);
		
		/*取分值的绝对值*/
		Float fz = Float.parseFloat(rs.get("fz"));
		data.put("fz", Math.abs(fz));
		
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf","wjcld_12869.xml", FreeMarkerUtil.getFileName(rs.get("xh")+"-"+xsxxMap.get("xm")));
		return file;
	}
	
	/**
	 * @描述: 浙江警官职业学院个性化——单个打印学生处分审批表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-2 下午07:56:34
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
	public ActionForward getXscfspbOne (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		CfjgForm model = (CfjgForm)form;
		/*获取url带过来的处分id*/
		String id = request.getParameter("id");
		/*获取文件信息*/
		File wordFile = getWordForXscfspb(id);
		/*输出文件*/
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @描述: 浙江警官职业学院个性化——批量打印学生处分审批表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-2 下午07:57:01
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
	public ActionForward getXscfspbZip (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*获取url带过来的Value*/
		String value = request.getParameter("value");
		/*判断value是否为空*/
		if(StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for(int i = 0, n = values.length; i < n; i++){
				File file = getWordForXscfspb(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputWord(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @描述: 学生处分审批表数据输出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-2 下午06:58:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public File getWordForXscfspb (String id) throws Exception{
		
		Map<String,Object> data = new HashMap<String,Object>();
		/*定义一个空file*/
		File file = null;
		
		CfjgService service = new CfjgService();
		/*获得处分信息*/
		HashMap<String, String> cfxx = service.getCfxxByCfid(id);
		data.put("cfxx", cfxx);
		
		/*加载学生基本信息*/
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(cfxx.get("xh"));
		data.putAll(xsxxMap);
		
		/*取学号和姓名*/
		String xh = xsxxMap.get("xh");
		String xm = xsxxMap.get("xm");
		/*出生日期年月,例如：2017年11月*/
		data.put("csny",DateTranCnDate.fomartDateToCn(xsxxMap.get("csrq"),FomartDateType.month));
		
		file = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf","xscfspb_12869.xml", FreeMarkerUtil.getFileName(xh+"-"+xm));
		return file;
	}
	
	/**
	 * @描述: 上海戏剧学院，学生处分决定书，处分送达书
	 * @作者：lgx[工号：1553]
	 * @日期： 2018-2-5 上午9:18:03
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
	public ActionForward getCfForShxj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		CfjgForm model = (CfjgForm)form;
		File wordFile = getCfWord(model.getCfid(),model.getType());
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * @描述: 上海戏剧学院批量打印
	 * @作者：lgx[工号：1553]
	 * @日期： 2018-2-5 上午9:20:37
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
	public ActionForward getCfForShxjZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String value = request.getParameter("value");
		String type = request.getParameter("type");
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = getCfWord(values[i],type);
				files.add(file);
			}
			
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}
	
	/**
	 * @描述: 学生处分决定书打印
	 * @作者： lgx[工号：1553]
	 * @日期：2018-2-5 上午9:23:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	private File getCfWord (String cfid,String type) throws Exception {
		
		CfjgService service = new CfjgService();
		/*获得处分信息*/
		HashMap<String, String> cfxx = service.getCfxxByCfid(cfid);
		String xh = cfxx.get("xh");
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		XsxxService xsxxService = new XsxxService();
		XsxxglService xsxxglService = new XsxxglService();
		
		/*加载学生基本信息*/
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
		data.putAll(xsxxMap);
		
		/*获得打印日期*/
		String dyrq = xsxxglService.getDqrq(xh);
		data.put("dysj", DateTranCnDate.fomartDateToCn(dyrq,FomartDateType.day));
		String xq = "";
		if("01".equals(cfxx.get("xq"))){
			xq="第一学期";
		}else{
			xq="第二学期";
		}
		data.put("xq", xq);
		data.put("cfyymc", cfxx.get("cfyymc"));
		data.put("cfyj", HtmlUtil.xmlZy(cfxx.get("cfyj")));
		data.put("cflbmc", cfxx.get("cflbmc"));
		data.put("xn", cfxx.get("xn"));
		data.put("cfwh", cfxx.get("cfwh"));
		data.put("bz", HtmlUtil.xmlZy(cfxx.get("bz")));
		
		File file = null;
		if("cfjds".equals(type)){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//wjcf","cfjds_10279.xml",FreeMarkerUtil.getFileName(xh+"-"+xsxxMap.get("xm")));
		}
		if("cfsds".equals(type)){
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//wjcf","cfsds_10279.xml",FreeMarkerUtil.getFileName(xh+"-"+xsxxMap.get("xm")));
		}
		
		
		return file;
	}
}
