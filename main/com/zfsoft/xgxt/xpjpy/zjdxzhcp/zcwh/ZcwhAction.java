/**
 * @部门:学工产品(1)部
 * @日期：2017-6-22 上午09:41:07 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.zcwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.LogInfo;
import com.zfsoft.xgxt.base.log.LogService;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.IPRequest;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;

import eu.bitwalker.useragentutils.UserAgent;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测分维护
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-6-22 上午09:41:52 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcwhAction extends SuperAction{
	private static final String url = "xpjpy_zhcp_zcwh.do";
	private static final String num = "5000";
	private ZcwhService service = new ZcwhService();
	
	/**
	 * @描述: 学院综测情况列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-22 上午11:23:47
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
	@SystemLog(description = "访问新评奖评优-综合评价-评价维护-查询页面")
	public ActionForward getZcwhList (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	 	throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		User user = getUser(request);
		if (QUERY.equals(model.getDoType())){
			/*生成高级查询对象*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			/*查询*/
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*参数设置信息*/
		request.setAttribute("cssz", new CsszService().getCszzInfo());
		/*返回path*/
		String path = "xpjpy_zhcp_zcwh.do";
		request.setAttribute("path", path);
		/*加载页面控制权限及表头*/
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zcwhList");
	}
	
	/**
	 * @描述: 查看是否还有未提交记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-23 上午11:25:38
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
	public ActionForward checkSubmitInfo (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	 	throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		/*用户*/
		User user = getUser(request);
		/*判断是否有未提交的记录*/
		boolean isCanSubmit = service.isSubmitInfo(model,user);
		response.getWriter().print(isCanSubmit);
		return null;
	}
	
	/**
	 * @描述: 综测分数批量录入
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-23 下午02:40:36
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
	public ActionForward zcwhEdit (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	 	throws Exception {
		
		ZcwhForm zcwhForm = (ZcwhForm)form;
		/*用户*/
		User user = getUser(request);
		
		/*获得每条记录的综测项目父级代码*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		request.setAttribute("zcxmdmForTop", zcxmdmForTop);
		
		if(QUERY.equals(zcwhForm.getDoType())){
			/*生成高级查询对象*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcwhForm.setSearchModel(searchModel);
			
			/*查询数据*/
			List<HashMap<String,String>> resultList = service.getZcwhList(zcwhForm,user,zcxmdmForTop);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*综测fjdm为top的项目代码获取相关的综测子项*/
		List<HashMap<String,String>> zcxmList = service.getZcxmListByFjdmisTop(zcxmdmForTop);
		request.setAttribute("zcxmList", zcxmList);
		/*参数设置信息*/
		CsszService csszService = new CsszService();
		HashMap<String,String> getCsszInfo = csszService.getCszzInfo();
		request.setAttribute("cssz", getCsszInfo);
		/*获得等级*/
		List<HashMap<String,String>> djList = service.getDj();
		request.setAttribute("djList", JSONArray.fromObject(djList));
		/*返回path*/
		String path = "xpjpy_zhcp_zcwh.do";
		request.setAttribute("path", path);
		/*返回操作path（高级查询用）*/
		String czpath = "xpjpy_zhcp_zcwh.do?method=zcwhEdit";
		request.setAttribute("czpath", czpath);
		/*用户身份*/
		request.setAttribute("userStatus", user.getUserStatus());
		
		/*加载页面控制权限及表头*/
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("zcwhEdit");
	}
	
	/**
	 * @描述: 实时保存录入的综测分
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-28 下午03:46:52
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
	@SystemLog(description="访问评奖评优-综合测评-综测维护-录入-更新学生XH：{xh}")
	public ActionForward saveZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcwhForm zcwhForm = (ZcwhForm)form;
		User user = getUser(request);
		boolean result = service.saveZcfs(zcwhForm, user);
		if (!result){
			/*如果失败，则提示*/
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		return null;
	}
	
	/**
	 * @描述: 调整参评学生名单
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-6-30 下午05:51:37
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
	public ActionForward cpxsAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		
		if (QUERY.equalsIgnoreCase(model.getDoType())) {
			/*生成高级查询对象*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			/*查询*/
			List<HashMap<String, String>> resultList = service.getAddCpxsList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		/*年级、学院、专业、班级*/
		FormModleCommon.setAllNjXyZyBjList(request);
		/*传回Path*/
		String path = "xpjpy_zhcp_zcwh.do?method=cpxsAdd";
		request.setAttribute("path", path);
		return mapping.findForward("cpxsAdd");
	}
	
	
	/**
	 * @描述: 综测分导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-10 上午11:46:10
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
	public ActionForward cpxsExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		/*参数设置信息*/
		request.setAttribute("cssz", new CsszService().getCszzInfo());
		/*用户*/
		User user = getUser(request);
		
		/*获得每条记录的综测项目父级代码*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		
		/*生成高级查询*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		/*综测项目列表*/
		List<HashMap<String,String>> zcxmList = service.getZcxmListByFjdmisTop(zcxmdmForTop);
		/*不分页*/
		model.getPages().setPageSize(Integer.MAX_VALUE);
		/*查询数据*/
		ZcwhDao zcwhDao = new ZcwhDao();
		List<HashMap<String,String>> resultList = zcwhDao.getCpxsExportList(model,zcxmList,user,zcxmdmForTop);
		/*导出功能代码*/
		File file = service.createImportTemplateDc(resultList, zcxmList,user);
		FileUtil.outputExcel(response, file);
		
		return null;
	}
	
	/**
	 * @描述: 综测分导入
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-10 下午05:57:17
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
	public ActionForward checkDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		/*用户*/
		User user = getUser(request);
		
		/*获得每条记录的综测项目父级代码*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		/*检查*/
		boolean isCanDownload = service.isCanDownload(model,user, num,zcxmdmForTop);
		response.getWriter().print(isCanDownload);
		return null;
	}
	
	/**
	 * @描述: 综测分导入
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-7-11 上午11:34:46
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
	public ActionForward toImportZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String jsonStr = request.getParameter("jsonStr");
		request.setAttribute("jsonStr", jsonStr);
		
		/*获得每条记录的综测项目父级代码*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		request.setAttribute("zcxmdmForTop", zcxmdmForTop);
		
		return mapping.findForward("importZcf");
	}
	
	/**
	 * @描述: 创建综测分导入模板
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-11 上午11:53:21
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
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		/*用户*/
		User user = getUser(request);
		
		/*获得每条记录的综测项目父级代码*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		/*创建导入表格*/
		File file = service.createImportTemplate(model, user,zcxmdmForTop);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述: 综测分导入，附件上传
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-11 下午03:49:54
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
	@SystemLog(description="访问评奖评优-综合测评-综测维护-录入-导入ID：{id}")
	public ActionForward importZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm model = (ZcwhForm)form;
		User user = getUser(request);
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		
		try {
			File file = service.importZcfs(model,user,zcxmdmForTop);
			
			if (file != null){
				FileUtil.outputExcel(response, file);
				return null;
			}
		
			request.setAttribute("result", true);
			request.setAttribute("message",MessageUtil.getText(MessageKey.SYS_IMPORT_SUCCESS));
		} catch (SystemException e) {
			request.setAttribute("result", false);
			request.setAttribute("message", e.getMessage());
		}
		
		return toImportZcfs(mapping, model, request, response);
	}
	
	
	/**
	 * @描述: 取消参评人员资格
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-6-28 下午05:29:54
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
	@SystemLog(description="访问评奖评优-综合测评-综测维护-录入-删除学生VALUES：{values}")
	public ActionForward cpxsDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		/*接收前台传过来的数据*/
		String values = request.getParameter("values");
		/*用户*/
		User user = getUser(request);
		boolean result = service.qxcp(values,user);
		String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS : MessageKey.SYS_QXCP_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 将参评人员从一个班级调整到另一个班级
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 下午02:35:25
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
	@SystemLog(description="访问评奖评优-综合测评-增加参评学生-调整BJDM：{bjdm},IDS:{ids}")
	public ActionForward updateCpbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String bjdm = request.getParameter("bjdm");
		String ids = request.getParameter("ids");
		User user = getUser(request);
		
		 if (!StringUtil.isNull(bjdm)){
			boolean result = service.bjtzs(bjdm,user,ids);
			String messageKey = result ? MessageKey.SYS_TZ_SUCCESS
					: MessageKey.SYS_TZ_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
						
		}
		return null;
	}
	
	/**
	 * @描述: 检测是否可提交综测分
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-5 下午05:02:13
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
	public ActionForward checkIsCanSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm zcwhForm = (ZcwhForm)form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcwhForm.setSearchModel(searchModel);
		
		String values = request.getParameter("values");
		boolean isCanSubmit = service.isCanSubmit(values,zcwhForm,user);
		response.getWriter().print(isCanSubmit);
		return null;
	}
	
	/**
	 * @描述: 综测分提交
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-6 上午09:11:34
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
	public ActionForward tjCpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm zcwhForm = (ZcwhForm)form;
		String values = request.getParameter("values");
		String tjzt = request.getParameter("tjzt");
		User user = getUser(request);
		
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zcwhForm.setSearchModel(searchModel);
		
		boolean result = service.tjCpxs(values,tjzt,zcwhForm,user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		/*手动写LOGO*/
		LogInfo logInfo = null;
		UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));
		
		String tjztmc = new String();
		if(tjzt.equalsIgnoreCase("tj")) tjztmc="提交";
		else if(tjzt.equalsIgnoreCase("qxtj")) tjztmc="取消提交";
		
		String[] xhList = service.getXhArray(values);
		String xh = "{";
		for(int i=0;i<xhList.length-1;i++){
			xh+=xhList[i];
			xh+=",";
		}
		xh+=xhList[xhList.length-1];
		xh+="}";
		String description = "访问评奖评优-综合测评-综测维护-" + tjztmc + "-改动学生学号：" + xh;

		logInfo = new LogInfo();
		logInfo.setOsName(userAgent.getOperatingSystem().getName());
		logInfo.setBrowserName(userAgent.getBrowser().getName());
		logInfo.setBrowserVersion(userAgent.getBrowserVersion().getVersion());
		logInfo.setIp(IPRequest.getIpAddress(request));
		logInfo.setDescription(description);
		logInfo.setClassName(mapping.getType());
		logInfo.setMethodName("tjCpxs");
		
		if (user != null){
			logInfo.setUsername(user.getUserName());
			logInfo.setUserType(user.getUserType());
		}
		
		if(result) logInfo.setStatus("success");
		else logInfo.setStatus("fail");
		new LogService().runInsert(logInfo);
		return null;
	}
	
	/**
	 * @描述: 取消提交综测分查询列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-7 下午04:28:20
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
	public ActionForward getQxtjzcfList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm model = (ZcwhForm)form;
		
		if (QUERY.equals(model.getDoType())){
			/*生成高级查询对象*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			/*查询*/
			List<HashMap<String,String>> resultList = service.getZcfqxList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*参数设置信息*/
		request.setAttribute("cssz", new CsszService().getCszzInfo());
		/*返回path*/
		String path = "xpjpy_zhcp_qxtjzcf.do";
		request.setAttribute("path", path);
		/*控制表头*/
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qxtjzcfList");
	}
	
	/**
	 * @描述: 取消提交综测分
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-7 下午05:58:58
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
	@SystemLog(description="访问评奖评优-综合测评-取消提交综测分-取消提交ID：{id}")
	public ActionForward cancelTj(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ZcwhForm model = (ZcwhForm)form;
		
		if (UPDATE.equals(model.getDoType())){
			User user = getUser(request);
			boolean result = service.cancelTj(user,model);
			String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS : MessageKey.SYS_QXCP_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
			return null;
		}
		return mapping.findForward("cancelTj");
	}
	
	/**
	 * @描述: 综测分提交检测
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-7 下午05:58:58
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
	public ActionForward checkZcfSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm model = (ZcwhForm)form;
		boolean isCanSubmit = service.checkZcfSubmit(model);
		response.getWriter().print(isCanSubmit);
		return null;
	}
	
	/**
	 * @描述: 提交综测分计算排名
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-13 上午08:59:07
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
	@SystemLog(description="访问评奖评优-综合测评-综测维护-提交综测分ID：{id}")
	public ActionForward submitXyzcf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm model = (ZcwhForm)form;
		/*用户*/
		User user = getUser(request);
		boolean result = service.submitZcfs(model, user);
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL));
		return null;
	}
	
	/**
	 * @描述: 取消提交
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-3 上午10:44:25
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
	@SystemLog(description="访问评奖评优-综合测评-id：{id}")
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward zcwhCancelSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		ZcwhForm model = (ZcwhForm)form;
		boolean result = service.cancelSubmit(model);
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL));
		return null;
	}
	
	/**
	 * @描述: 综测分查看
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-7-13 下午03:31:10
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
	public ActionForward zcwhView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	 	throws Exception {
		
		ZcwhForm zcwhForm = (ZcwhForm)form;
		/*用户*/
		User user = getUser(request);
		
		/*获得每条记录的综测项目父级代码*/
		String zcxmdmForTop = request.getParameter("zcxmdmForTop");
		request.setAttribute("zcxmdmForTop", zcxmdmForTop);
		
		/*综测fjdm为top的项目代码获取相关的综测子项*/
		List<HashMap<String,String>> zcxmList = service.getZcxmListByFjdmisTop(zcxmdmForTop);
		
		if(QUERY.equals(zcwhForm.getDoType())){
			/*生成高级查询对象*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zcwhForm.setSearchModel(searchModel);
			/*查询数据*/
			List<HashMap<String,String>> resultList = service.getZcwhView(zcwhForm,zcxmList,user,zcxmdmForTop);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/*学院信息*/
		HashMap<String,String> xyMap = service.getXyxxById(zcwhForm.getId());
		request.setAttribute("xyxxMap", xyMap);
		
		request.setAttribute("zcxmList", zcxmList);
		/*参数设置信息*/
		request.setAttribute("cssz", new CsszService().getCszzInfo());
		return mapping.findForward("zcwhView");
	}
	
	/**
	 * @描述: 一键同步综测分(五个单项)
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-12-7 上午10:52:39
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
	public ActionForward dataSynchronization (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
 		throws Exception {
		boolean result = false;
		result = service.getIntefaceData();
		response.getWriter().print(getJsonMessageByKey(result ? MessageKey.SYS_TB_SUCCESS : MessageKey.SYS_TB_FAIL));
		return null;
	}
}
