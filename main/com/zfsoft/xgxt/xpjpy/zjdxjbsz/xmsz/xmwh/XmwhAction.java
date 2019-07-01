/**
 * @部门:学工产品1部
 * @日期：2017-4-7 上午10:57:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq.XmsqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优_项目设置_项目维护
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-7 上午10:57:56 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmwhAction extends SuperAction {
	private static final String url = "xpjpy_jbsz_xmsz.do";
	
	/**
	 * @描述: 返回项目设置_项目维护列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-7 下午01:50:50
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
	@SystemLog(description = "访问新评奖评优-基本设置-项目设置-查询页面")
	public ActionForward getXmwhList (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	 	throws Exception {
		
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		User user = getUser(request);
		/*查询所有数据*/
		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*获取参数设置信息*/
		HashMap<String, String> csszMap = service.getCsszMap();
		request.setAttribute("pjzq", csszMap.get("xn"));
		/*获取项目类型*/
		List<HashMap<String, String>> xmlxList = service.getXmlx();
		request.setAttribute("xmlxList", xmlxList);
		/*获取项目性质*/
		List<HashMap<String, String>> xmxzList = service.getXmxz();
		request.setAttribute("xmxzList", xmxzList);
		/*返回path*/
		String path = "xpjpy_jbsz_xmsz.do";
		request.setAttribute("path", path);
		/*返回系统当前时间*/
		String dateFormat = "yyyy-MM-dd HH:mm";
		request.setAttribute("currDate", service.getCurrTime(dateFormat));
		/*加载页面控制权限及表头*/
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmwhList");
	}
	
	/**
	 * @描述: 项目增加
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 上午11:06:34
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
	public ActionForward xmwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		XtwhShlcService shlcService = new XtwhShlcService();
		/*基本设置中审核流程列表的取值通用方法*/
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("pjpy");
		request.setAttribute("shlcList", shlc);

		/*获取项目类型*/
		List<HashMap<String, String>> xmlxList = service.getXmlx();
		request.setAttribute("xmlxList", xmlxList);
		/*获取项目性质*/
		List<HashMap<String, String>> xmxzList = service.getXmxz();
		request.setAttribute("xmxzList", xmxzList);
		/*返回path*/
		String path = "xpjpy_xmwh.do?method=xmwhAdd";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if (model != null) {
			BeanUtils.copyProperties(model, StringUtils.formatData(model));
		}
		return mapping.findForward("xmwhAdd");
	}
	
	/**
	 * @描述: 项目增加保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 下午02:07:25
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
	@SystemLog(description="访问新评奖评优-基本设置-项目设置-增加-XZDM:{xzdm},LXDM:{lxdm},XMMC:{xmmc},YWMC:{ywmc},XMJE:{xmje},XSSX:{xssx},SHLC:{shlc},XMSM:{xmsm}")
	public ActionForward saveFormAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		String messageKey = null;
		/*验证同一个学年是否有显示顺序重复*/
		if(service.isExistXssx(model)) {
			messageKey = MessageKey.PJPY_XSXH_EXIST;
			response.getWriter().print(getJsonResult(messageKey, false));
			return null;
		}
		/*验证同一个学年是否有项目名称重复*/
		if (service.isExistXmmc(model)) {
			messageKey = MessageKey.PJPY_XMMC_EXIST;
			response.getWriter().print(getJsonResult(messageKey, false));
			return null;
		}
		/*每个项目默认增加当年学年*/
		model.setXn(service.getCsszMap().get("xn"));
		/*插入表 */
		boolean result = service.runInsert(model);
		messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 项目修改
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-11 下午05:05:36
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
	public ActionForward xmwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		XmwhForm updateForm = service.getModel(model);
		XtwhShlcService shlcService = new XtwhShlcService();
		/*基本设置中审核流程列表的取值通用方法*/
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("pjpy");
		request.setAttribute("shlcList", shlc);
		/*获取项目类型*/
		List<HashMap<String, String>> xmlxList = service.getXmlx();
		request.setAttribute("xmlxList", xmlxList);
		/*获取项目性质*/
		List<HashMap<String, String>> xmxzList = service.getXmxz();
		request.setAttribute("xmxzList", xmxzList);
		/*检验是否有学生申请此项目*/
		if(model.getXmdm() != null && !model.getXmdm().equals("")){
			XmsqService xmsqService = new XmsqService();
			boolean flag = xmsqService.isExistsFlowData(model.getXmdm());
			request.setAttribute("spzt", flag);
		}
		/*返回path*/
		String path = "xpjpy_xmwh.do?method=xmwhUpdate";
		request.setAttribute("path", path);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		return mapping.findForward("xmwhUpdate");
	}
	
	/**
	 * @描述: 项目修改保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-11 下午05:23:50
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
	@SystemLog(description="访问新评奖评优-基本设置-项目设置-修改-XZDM:{xzdm},LXDM:{lxdm},XMMC:{xmmc},YWMC:{ywmc},XMJE:{xmje},XSSX:{xssx},SHLC:{shlc},XMSM:{xmsm}")
	public ActionForward saveFormUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		String messageKey = null;
		/*验证同一个学年是否有显示顺序重复*/
		if(service.isExistXssx(model)) {
			messageKey = MessageKey.PJPY_XSXH_EXIST;
			response.getWriter().print(getJsonResult(messageKey, false));
			return null;
		}
		/*验证同一个学年是否有项目名称重复*/
		if (service.isExistXmmc(model)) {
			messageKey = MessageKey.PJPY_XMMC_EXIST;
			response.getWriter().print(getJsonResult(messageKey, false));
			return null;
		}
		/*更新表 */
		boolean result = service.runUpdate(model);
		messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 删除操作
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-10 下午05:40:39
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
	@SystemLog(description="访问评奖评优-基本设置-项目设置-删除-VALUES：{values}")
	public ActionForward xmwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmwhService service = new XmwhService();
		String messageKey = null;
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			/*判断所选删除的项目 是否在项目申请时被使用*/
			if (service.checkForXmsq(values)) {
				messageKey = MessageKey.PJPY_XM_NOTDEL;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			if (result) {
				/*清理需要删除项目 的关联表*/
				service.deleteRelationalTable(values);
			}
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;
	}
	
	/**
	 * @描述: 申请、审核开关页面返回
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-12 上午11:25:52
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
	public ActionForward xmwhSjkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		XmwhForm sjkgForm = service.getModel(model);
		/*从页面获得项目代码*/
		String xmdm = request.getParameter("xmdm");
		/*返回项目名称*/
		String xmmc = service.getNameById(xmdm);
		request.setAttribute("xmmc", xmmc);
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);// 是否list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		/*返回path*/
		String path = "xpjpy_xmwh.do?method=getXmwhList";
		request.setAttribute("path", path);
		BeanUtils.copyProperties(model, StringUtils.formatData(sjkgForm));
		return mapping.findForward("xmwhSjkg");
	}
	
	/**
	 * @描述: 时间开关设置保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-12 下午01:47:09
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
	@SystemLog(description="访问评奖评优-基本设置-项目设置-申请、审核开关-XMDM：{xmdm},SQKG:{sqkg},SQKSSJ:{sqkssj},SQJSSJ:{sqjssj},SHKG:{shkg},SHKSSJ:{shkssj},SHJSSJ:{shjssj},KGBZ:{kgbz}")
	public ActionForward saveFormSjkg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 奖项复制
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-12 下午03:57:05
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
	public ActionForward xmwhJxfz(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		XmwhForm model = (XmwhForm)form;
		XmwhService service = new XmwhService();
		if (QUERY.equals(model.getType())) {
			/*复制来源年度下拉列表数据*/
			List<HashMap<String, String>> jxfzList = service.getJxfz();
			JSONArray dataList = JSONArray.fromObject(jxfzList);
			response.getWriter().print(dataList);
			return null;
		}
		/*当前评奖周期(xn)*/
		String pjzq = service.getCsszMap().get("xn");
		request.setAttribute("pjzq", pjzq);
		/*返回path*/
		String path = "xpjpy_xmwh.do?method=getXmwhList";
		request.setAttribute("path", path);
		return mapping.findForward("xmwhJxfz");
	}
	
	/**
	 * @描述: 奖项复制保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-4-12 下午03:57:05
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
	public ActionForward saveFormCopy(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		XmwhService service = new XmwhService();
		String jxfznd = request.getParameter("jxfznd");
		boolean result = service.runJxfz(jxfznd);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
