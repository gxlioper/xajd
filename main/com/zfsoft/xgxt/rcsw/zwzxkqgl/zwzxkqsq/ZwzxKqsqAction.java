/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:37:19 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsq;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.cclxwh.CclxwhService;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.jcsz.JcszService;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.qqlxwh.QqlxwhService;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-26 下午02:37:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwzxKqsqAction extends SuperAction<ZwzxKqsqForm,ZwzxKqsqService>{
	private ZwzxKqsqService service = new ZwzxKqsqService();
	private ZwzxKqjgService kqjgService = new ZwzxKqjgService();
	private CclxwhService cclxwhService = new CclxwhService();
	private QqlxwhService qqlxwhService = new QqlxwhService();
	
	private static final String url = "rcsw_zwzxkq_kqsq.do";
	
	/**
	 * 
	 * @描述:考勤申请列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午03:19:24
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
	public ActionForward getKqsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
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
		JcszService  jcszService = new JcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		String path = "rcsw_zwzxkq_kqsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		// =========== 根据菜单自动更新字段名称 begin =============
		String gnmkmc = (String) request.getAttribute("gnmkmc");
		String ccrqTitle = "填报日期";
		String cclxTitle = "填报事项";
		String jlrTitle = "填报人";
		if(gnmkmc.contains("考勤")){
			ccrqTitle = "抽查日期";
			cclxTitle = "抽查类型";
			jlrTitle = "填写人";
		}
		request.setAttribute("ccrqTitle", ccrqTitle);
		request.setAttribute("cclxTitle", cclxTitle);
		request.setAttribute("jlrTitle", jlrTitle);
		// =========== 根据菜单自动更新字段名称 end =============
		return mapping.findForward("kqsqList");
	}
	/**
	 * 
	 * @描述:辅导员反馈
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-3 下午01:50:28
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
	public ActionForward kqfkList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
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
		JcszService  jcszService = new JcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		String path = "rcsw_zwzxkq_fdyfk.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kqfkList");
	}
	
	
	/**
	 * 
	 * @描述:考勤填写申请
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午06:33:18
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
	public ActionForward addKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc", kqjgService.getXqmc(Base.currXq));
		request.setAttribute("cclxList", cclxwhService.getCclxList());
		request.setAttribute("qqlxList", qqlxwhService.getQqlxList());
		request.setAttribute("path", "zwzxKqsq.do?method=addKqsq");
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("addKqsq");
	}
	/**
	 * 
	 * @描述:考勤填写申请修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午06:48:05
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
	public ActionForward editKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm myForm = (ZwzxKqsqForm) form;
		ZwzxKqsqForm model = service.getKqsq(myForm);
		if("2297".equals(Base.xxdm)){
			model.setYdrs(service.getYdrsSzly(model.getBjdm()));
		}
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			request.setAttribute("kqsqForm", model);
		}
		//查询缺勤学生信息
		List<HashMap<String,String>> qqxsList = kqjgService.getQqxsList(myForm.getSqid());
		request.setAttribute("cclxList", cclxwhService.getCclxList());
		request.setAttribute("qqxsList", qqxsList);
		request.setAttribute("qqlxList", qqlxwhService.getQqlxList());
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("path", "zwzxkqKqsq.do?method=editKqsq");
		return mapping.findForward("editKqsq");
	}
	/**
	 * 
	 * @描述:辅导员反馈
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-3 下午02:39:54
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
	public ActionForward fdyfk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm myForm = (ZwzxKqsqForm) form;
		ZwzxKqsqForm model = service.getKqsq(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			request.setAttribute("kqsqForm", model);
		}
		//查询缺勤学生信息
		List<HashMap<String,String>> qqxsList = kqjgService.getQqxsList(myForm.getSqid());
		request.setAttribute("cclxList", cclxwhService.getCclxList());
		request.setAttribute("qqxsList", qqxsList);
		request.setAttribute("qqlxList", qqlxwhService.getQqlxList());
		request.setAttribute("path", "zwzxkqKqsq.do?method=fdyfk");
		return mapping.findForward("fdyfk");
	}
	/**
	 * 
	 * @描述:申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午04:16:09
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
	@SystemLog(description = "日常事务-零报告管理-零报告填写-增加CCRQ:{ccrq},CCLXDM:{cclxdm},BJMC:{bjmc},YDRS:{ydrs},SDRS:{sdrs}")
	@SuppressWarnings("unchecked")
	public ActionForward saveKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		}

		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
		String objStr = request.getParameter("objStr");
		if (service.isHaveSqJl(model,"add")) {// 验证
			model.setCclxmc(cclxwhService.getCclxById(model.getCclxdm()).get("lxmc"));
			String messageKey = MessageUtil.getText(MessageKey.RCSW_ZWZXKQ_KQSJ_EXIST, new String[] { model.getBjmc(), model.getCcrq(), model.getCclxmc() });
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		super.resetToken(request);
		List<ZwzxKqsqForm> qqxxList = JsonUtil.jsonArrToList(objStr,ZwzxKqsqForm.class);
		User user = getUser(request);
		model.setJlr(user.getUserName());
		boolean result = service.saveKqsq(model,qqxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:申请修改保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午07:06:20
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
	@SuppressWarnings("unchecked")
	@SystemLog(description = "日常事务-零报告管理-零报告填写-修改SQID:{sqid},CCRQ:{ccrq},CCLXDM:{cclxdm},BJMC:{bjmc},YDRS:{ydrs},SDRS:{sdrs}")
	public ActionForward saveEditKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
		String objStr = request.getParameter("objStr");
		if (service.isHaveSqJl(model,"update")) {// 关联性
			model.setCclxmc(cclxwhService.getCclxById(model.getCclxdm()).get("lxmc"));
			String messageKey = MessageUtil.getText(MessageKey.RCSW_ZWZXKQ_KQSJ_EXIST, new String[] { model.getBjmc(), model.getCcrq(), model.getCclxmc() });
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		List<ZwzxKqsqForm> qqxxList = JsonUtil.jsonArrToList(objStr,ZwzxKqsqForm.class);
		boolean result = service.saveEditKqsq(model,qqxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		if (!"save".equals(model.getType())) {
			messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:申请提交
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-26 下午08:01:16
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
	public ActionForward submitKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
		boolean result = service.submitKqsq(request,model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:考勤申请删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 上午08:41:18
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
	@SystemLog(description = "日常事务-零报告管理-零报告填写-删除VALUES:{values}")
	public ActionForward delKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// 删除
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			if(result){
				result = kqjgService.delQqxs(values.split(","));
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @描述:申请撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 上午08:41:44
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
	public ActionForward cancelKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			ZwzxKqsqForm model = new ZwzxKqsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:考勤申请查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-28 上午10:45:23
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
	public ActionForward viewKqsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqsqForm myForm = (ZwzxKqsqForm) form;
		ZwzxKqsqForm kqsqForm = service.getKqsq(myForm);
		/**
		 * 苏州旅游职业个性化
		 */
		if("2297".equals(Base.xxdm)){
			String num = service.getYdrsSzly(kqsqForm.getBjdm());
			kqsqForm.setYdrs(num);
		}
		if(null!=kqsqForm){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(kqsqForm));
			request.setAttribute("kqsqForm", StringUtils.formatData(kqsqForm));
		}
		//查询缺勤学生信息
		List<HashMap<String,String>> qqxsList = kqjgService.getQqxsList(myForm.getSqid());
		request.setAttribute("qqxsList", qqxsList);
		request.setAttribute("path", "zwzxkqKqsq.do?method=viewKqsq");
		return mapping.findForward("viewKqsq");
	}
	/**
	 * 
	 * @描述:考勤申请导出
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-28 上午10:45:04
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
		
		ZwzxKqsqForm model = (ZwzxKqsqForm) form;
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
	

}
