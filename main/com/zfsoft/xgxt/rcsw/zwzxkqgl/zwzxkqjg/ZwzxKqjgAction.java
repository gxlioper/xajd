/**
 * @部门:学工产品事业部
 * @日期：2015-1-20 上午11:36:27 
 */
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.cclxwh.CclxwhService;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsq.ZwzxKqsqService;

import common.newp.StringUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 早晚自习考勤管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-20 上午11:36:27
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZwzxKqjgAction extends SuperAction<ZwzxKqjgForm, ZwzxKqjgService> {
	private ZwzxKqjgService service = new ZwzxKqjgService();
	private CclxwhService cclxwhService = new CclxwhService();

	private static final String url = "rcsw_zwzxkq_kqjg.do";
	
	/**
	 * 
	 * @描述:查询考勤结果列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-20 下午01:54:11
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
	public ActionForward getKqjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm model = (ZwzxKqjgForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_zwzxkq_kqjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		// =========== 根据菜单自动更新字段名称 begin =============
		String gnmkmc = (String) request.getAttribute("gnmkmc");
		String ccrqTitle = "填报日期";
		String cclxTitle = "填报事项";
		String jlrTitle = "填报人";
		boolean gnmkmcKq = gnmkmc.contains("考勤");
		if(gnmkmcKq){
			ccrqTitle = "抽查日期";
			cclxTitle = "抽查类型";
			jlrTitle = "填写人";
		}
		request.setAttribute("ccrqTitle", ccrqTitle);
		request.setAttribute("cclxTitle", cclxTitle);
		request.setAttribute("jlrTitle", jlrTitle);
		request.setAttribute("gnmkmcKq", gnmkmcKq);
		// =========== 根据菜单自动更新字段名称 end =============
		return mapping.findForward("kqjgList");
	}
	
	@SystemAuth(url = url)
	public ActionForward getQqxsList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm model = (ZwzxKqjgForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getQqxsList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_zwzxkq_xskqxx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qqxsList");
	}
	/**
	 * 
	 * @描述:考勤结果增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-22 下午05:27:51
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
	public ActionForward addKqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", service.getXqmc(Base.currXq));
		request.setAttribute("cclxList", cclxwhService.getCclxList());
		request.setAttribute("qqlxList", service.getQqlxList());
		request.setAttribute("path", "zwzxkqKqjg.do?method=addKqjg");
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addKqjg");
	}
	/**
	 * 
	 * @描述:修改考勤结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午01:55:20
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
	public ActionForward editKqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;
		ZwzxKqjgForm kqjgForm = service.getKqjg(myForm);
		if("2297".equals(Base.xxdm)){
			ZwzxKqsqService sqService = new ZwzxKqsqService();
			kqjgForm.setYdrs(sqService.getYdrsSzly(kqjgForm.getBjdm()));
		}
		if(null!=kqjgForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(kqjgForm));
			request.setAttribute("kqjgForm", kqjgForm);
		}
		//查询缺勤学生信息
		List<HashMap<String,String>> qqxsList = service.getQqxsList(myForm.getJgid());
		request.setAttribute("cclxList", cclxwhService.getCclxList());
		request.setAttribute("qqxsList", qqxsList);
		request.setAttribute("qqlxList", service.getQqlxList());
		request.setAttribute("path", "zwzxkqKqjg.do?method=editKqjg");
		return mapping.findForward("editKqjg");
	}
	/**
	 * 
	 * @描述:查看考勤结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午01:55:20
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
	public ActionForward viewKqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;
		ZwzxKqjgForm kqjgForm = service.getKqjg(myForm);
		/**
		 * 
		 * 苏州旅游职业个性化
		 */
		if("2297".equals(Base.xxdm)){
			ZwzxKqsqService sqService = new ZwzxKqsqService();
			String num = sqService.getYdrsSzly(kqjgForm.getBjdm());
			kqjgForm.setYdrs(num);
		}
		if(null!=kqjgForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(kqjgForm));
			request.setAttribute("kqjgForm", kqjgForm);
		}
		//查询缺勤学生信息
		List<HashMap<String,String>> qqxsList = service.getQqxsList(myForm.getJgid());
		request.setAttribute("qqxsList", qqxsList);
		request.setAttribute("path", "zwzxkqKqjg.do?method=viewKqjg");
		return mapping.findForward("viewKqjg");
	}
	/**
	 * 
	 * @描述:缺勤学生信息查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 下午05:06:22
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
	public ActionForward qqxsView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;
		//查询缺勤学生信息
		HashMap<String,String> result = service.getQqxsxx(myForm);
		request.setAttribute("rs", StringUtils.formatData(result));
		request.setAttribute("path", "zwzxkqKqjg.do?method=qqxsView");
		return mapping.findForward("qqxsView");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "日常事务-零报告管理-零报告结果-删除VALUES:{values}")
	public ActionForward delKqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				result = service.delQqxs(ids);
			}
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
	 * 
	 * @描述:考勤结果保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-22 下午05:28:12
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
	@SystemLog(description = "日常事务-零报告管理-零报告结果-增加或修改保存CCRQ:{ccrq},JGID:{jgid},CCLXDM:{cclxdm},BJMC:{bjmc},YDRS:{ydrs},SDRS:{sdrs}")
	@SuppressWarnings("unchecked")
	public ActionForward saveKqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;
		String objStr = request.getParameter("objStr");
		User user =getUser(request);
		if (service.isHaveKgjg(myForm)) {
			myForm.setCclxmc(cclxwhService.getCclxById(myForm.getCclxdm()).get("lxmc"));
			String messageKey = MessageUtil.getText(MessageKey.RCSW_ZWZXKQ_KQSJ_EXIST, new String[] { myForm.getBjmc(), myForm.getCcrq(), myForm.getCclxmc() });
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		super.resetToken(request);
		List<ZwzxKqjgForm> qqxxList = JsonUtil.jsonArrToList(objStr,ZwzxKqjgForm.class);
		myForm.setJlr(user.getUserName());
		boolean result = service.editKqjg(myForm,qqxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:显示班级列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-23 上午08:43:25
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
	public ActionForward showBjList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;

		User user = getUser(request);// 用户对象
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			// 查询
			List<HashMap<String, String>> resultList = service.getBjListNew(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String gotoPath = request.getParameter("goto");
		request.setAttribute("gotoPath", gotoPath);
		String path = "zwzxkqKqjg.do?method=showBjList";
		request.setAttribute("path", path);
		return mapping.findForward("bjSelect");
	}
	
	@SystemAuth(url = url)
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ZwzxKqjgForm myForm = (ZwzxKqjgForm) form;
		String xhArr= request.getParameter("xhArr");
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			String bjdm = request.getParameter("bjdm");
			
			myForm.setBjdm(bjdm);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user,request);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("xhArr", xhArr);
		String path = "zwzxkqKqjg.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}
	
	@SystemAuth(url = url)
	public ActionForward getQqlxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<HashMap<String,String>> sbflList = cclxwhService.getQqlxList();
		JSONArray dataList = JSONArray.fromObject(sbflList);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * 
	 * @描述:考勤结果导出
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-28 上午10:28:18
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwzxKqjgForm model = (ZwzxKqjgForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页
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
	 * @描述:学生考勤信息导出
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-28 下午01:55:01
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
	public ActionForward qqxsExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwzxKqjgForm model = (ZwzxKqjgForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllQqxsList(model,
				user);// 查询出所有记录，不分页
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
	 * @描述:缺勤学生人数数据同步
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-30 下午04:20:04
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
	public ActionForward qqxsxxTb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		Boolean flag = service.qqxsxxTb();
		String messageKey = flag ? MessageKey.SYS_SYNC_SUCCESS : MessageKey.SYS_SYNC_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
}
