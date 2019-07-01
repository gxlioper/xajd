/**
 * @部门:学工产品事业部
 * @日期：2015-5-29 上午11:40:29 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zdzc;

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
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import eu.bitwalker.useragentutils.UserAgent;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2015-5-29 上午11:40:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzcAction extends SuperAction {
	
	private static final String url = "pj_zdzcwh.do";
	
	/**
	 * 
	 * @描述:综测分数批量录入
	 * @作者：cq [工号：785]
	 * @日期：2015-5-29 上午11:56:47
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
	public ActionForward viewZdzcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		User user = getUser(request);
		
		if (QUERY.equals(zdzcForm.getDoType())){
		
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			zdzcForm.setSearchModel(searchModel);
			
			//查询
			List<HashMap<String,String>> resultList = service.getZcwhList(zdzcForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		
		//加载没有子项目的综测项目
		List<HashMap<String,String>> zcxmList = service.getAllowEditZcfxm();
		
		ZcfsService zcfsService = new ZcfsService();
		List<HashMap<String, String>> djList = zcfsService.getDj();// 等级
		
		request.setAttribute("djList", JSONArray.fromObject(djList));
		request.setAttribute("zcxmList", zcxmList);
		request.setAttribute("cssz", csszModel);
		request.setAttribute("userStatus", user.getUserStatus());
		request.setAttribute("path", "pj_zdzcwh.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewZdzcList");
	}
	
	
	/**
	 * 
	 * @描述:取消参评人员状态
	 * @作者：cq [工号：785]
	 * @日期：2015-6-1 上午11:23:25
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
	public ActionForward delCpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdzcService service = new ZdzcService();
		
		String values = request.getParameter("values");
		User user = getUser(request);
		

		boolean result = service.qxcp(values,user);
		String messageKey = result ? MessageKey.SYS_QXCP_SUCCESS
				: MessageKey.SYS_QXCP_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;

	}
	
	
	/**
	 * 
	 * @描述:验证下载条数
	 * @作者：cq [工号：785]
	 * @日期：2015-6-5 下午05:32:53
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
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		User user = getUser(request);
		
		String num = request.getParameter("num");
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zdzcForm.setSearchModel(searchModel);
		
		//这样写压力太大，需要更改
		zdzcForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.getZcwhList(zdzcForm,user);
		
		boolean isCanDownload = resultList.size()<=Integer.parseInt(num)? true:false;
		
		response.getWriter().print(isCanDownload);
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述:综测分导入
	 * @作者：cq [工号：785]
	 * @日期：2015-6-8 上午10:29:08
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
	public ActionForward toImportZdzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String jsonStr = request.getParameter("jsonStr");
		request.setAttribute("jsonStr", jsonStr);
		
		return mapping.findForward("toImportZdzc");
	}
	
	
	/**
	 * 
	 * @描述:下载导入模版
	 * @作者：cq [工号：785]
	 * @日期：2015-6-8 下午05:24:41
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
	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zdzcForm.setSearchModel(searchModel);
		
		File file = service.createImportTemplate(zdzcForm, user);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述:综测分数导入
	 * @作者：cq [工号：785]
	 * @日期：2015-6-9 下午02:36:48
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
	@SystemLog(description="访问评奖评优-综合测评-综测维护-录入-导入ID：{id}")
	public ActionForward importZcfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zdzcForm.setSearchModel(searchModel);
		
		try {
			File file = service.importZcfs(zdzcForm,user);
			
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
		
		return toImportZdzc(mapping, zdzcForm, request, response);
		
	}
	
	/**
	 * 
	 * @描述:提交参评人员状态
	 * @作者：cq [工号：785]
	 * @日期：2015-6-9 下午04:19:51
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
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		
		String values = request.getParameter("values");
		String tjzt = request.getParameter("tjzt");
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zdzcForm.setSearchModel(searchModel);
		
		boolean result = service.tjCpxs(values,tjzt,zdzcForm,user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		//手动书写system log	
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
	 * 
	 * @描述:检测是否可提交综测分
	 * @作者：cq [工号：785]
	 * @日期：2015-6-10 下午05:15:45
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
		
		ZdzcService service = new ZdzcService();
		ZdzcForm zdzcForm = (ZdzcForm) form;
		User user = getUser(request);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zdzcForm.setSearchModel(searchModel);
		
		String values = request.getParameter("values");
		
		boolean isCanSubmit = service.isCanSubmit(values,zdzcForm,user);
		
		response.getWriter().print(isCanSubmit);
		
		return null;
	}

}
