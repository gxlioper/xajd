/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午05:02:12 
 */
package com.zfsoft.xgxt.xstgl.stgl.stsq;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.stlbgl.StlbglService;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.xmlbgl.XmlbglService;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;

import common.newp.StringUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-9 下午05:02:12
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class StsqAction extends SuperAction<StsqForm, StsqService> {

	private StsqService service = new StsqService();
	private StlbglService stlbService = new StlbglService();
	private XmlbglService xmlbService = new XmlbglService();
	
	private static final String url = "stgl_stgl_stsq.do";

	/**
	 * 
	 * @描述:社团申请列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-8-3 下午04:23:38
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
	public ActionForward getStsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
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
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		// String[] sqshkg = csszService.getSqShKg();
		// request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
		String path = "stgl_stgl_stsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getStsqList");
	}

	/**
	 * 
	 * @描述:社团申请增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 下午04:06:41
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
	public ActionForward addStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;
		User user = getUser(request);
		// 学年 学期
		model.setXn(Base.currXn);
		List<HashMap<String, String>> stlbList = stlbService.getStlbList();
		request.setAttribute("stlbList", stlbList);
		String stlbdm = stlbList.get(0).get("stlbdm");
		request.setAttribute("xmlbList", xmlbService.getXmlbList(stlbdm));
		if("12872".equals(Base.xxdm)) {
			request.setAttribute("gkdwList", service.getBbdmlist());
			List<HashMap<String, String>> stxjList = service.getstxjList();
			request.setAttribute("stxjList",stxjList);
		}
		initParam(request, user);
		return mapping.findForward("addStsq");
	}

	/**
	 * 
	 * @描述:社团申请修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 下午04:07:06
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
	public ActionForward editStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm myForm = (StsqForm) form;
		User user = getUser(request);
		StsqForm model = service.getModel(myForm);
		HashMap<String, String> stsqxx = service.getSqxx(model);
		request.setAttribute("stfzrxm", stsqxx.get("stfzrxm"));
		request.setAttribute("zdlsxm", stsqxx.get("zdlsxm"));
		request.setAttribute("fzrlb", stsqxx.get("fzrlb"));
		request.setAttribute("fzrbj", stsqxx.get("fzrbj"));
		request.setAttribute("fzrxy", stsqxx.get("fzrxy"));
		List<HashMap<String, String>> stlbList = stlbService.getStlbList();
		request.setAttribute("stlbList", stlbList);
		request.setAttribute("xmlbList", xmlbService.getXmlbList(model.getStlbdm()));
		//指导老师信息
		List<HashMap<String,String>> ZdlsInfoList=service.getZdlsInfo(model);
		request.setAttribute("ZdlsInfoList",ZdlsInfoList);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		if("12872".equals(Base.xxdm)) {
			request.setAttribute("gkdwList", service.getBbdmlist());
			List<HashMap<String, String>> stxjList = service.getstxjList();
			request.setAttribute("stxjList",stxjList);
		}
		String path = "stglStsq.do?method=editStsq";
		request.setAttribute("path", path);
		initParam(request, user);
		return mapping.findForward("editStsq");
	}

	/**
	 * 
	 * @描述:社团申请查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 上午11:32:02
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
	
	public ActionForward viewStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm myForm = (StsqForm) form;
		StsqForm model = service.getModel(myForm);
		HashMap<String,String> sqxxMap = service.getSqxx(model);
		//指导老师信息
		List<HashMap<String,String>> ZdlsInfoList=service.getZdlsInfo(model);
		request.setAttribute("ZdlsInfoList",ZdlsInfoList);
		request.setAttribute("rs", StringUtils.formatData(sqxxMap));
		return mapping.findForward("viewStsq");
	}

	/**
	 * 
	 * @描述:社团申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 上午10:18:49
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
	public ActionForward saveStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;
		// 判断当前时间是否有申请记录
		boolean isExist = service.isHaveSqJl(model);
		if (isExist) {
			String message = MessageUtil.getText(MessageKey.STGL_STGL_ST_REPEAT, new String[] {
					model.getKssj(), model.getJssj() });
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.saveStsq(model);
		String messageKey = "";
		if("submit".equals(model.getType())){
			 messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
		}
		else{
			 messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:社团申请修改保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 下午02:33:27
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
	public ActionForward saveEditStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;
		// 判断当前时间是否有申请记录
		boolean isExist = service.isHaveSqJl(model);
		if (isExist) {
			String message = MessageUtil.getText(MessageKey.STGL_STGL_ST_REPEAT, new String[] {
					model.getJssj(), model.getKssj() });
			;
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		String messageKey = service.saveEditStsq(model);
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 下午02:35:38
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
	public ActionForward delStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
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
	 * 
	 * @描述:撤销申请
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 下午03:03:21
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
	public ActionForward cancelStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			StsqForm model = new StsqForm();
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
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:提交
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-8-3 上午10:26:05
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
	public ActionForward submitStsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;
		String values = request.getParameter("values");
		String xmlbdm = request.getParameter("xmlbdm");
		model.setSqid(values);
		model.setXmlbdm(xmlbdm);
		boolean result = service.submitStsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:数据导出
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-3 上午11:18:26
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StsqForm model = (StsqForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getsqAll(model,
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
     * @描述:初始化下拉列表
     * @作者：夏夏[工号：1104]
     * @日期：2015-8-3 上午10:59:11
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param request
     * @param user
     * @throws Exception
     * void 返回类型 
     * @throws
     */
	private void initParam(HttpServletRequest request,User user) throws Exception{
		List<HashMap<String, String>> bmList = new OptionUtil().getOptions("gkdw");
		request.setAttribute("jtrxm", user.getRealName());
		request.setAttribute("jtr", user.getUserName());
		/*升级版本将默认申请时间改为yyyy-MM-dd，原来为yyyy-MM-dd hh24:mm:ss*/
		request.setAttribute("sqsj", GetTime.getTimeByFormat("yyyy-MM-dd"));
		request.setAttribute("bmList", bmList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("zclist", service.getZclblist());
		/*默认有效学年*/
		request.setAttribute("mryxxn", Base.currXn);
		/*升级版本增加所属部门*/
		request.setAttribute("ssbmlist", service.getBbdmlist());
	}
	
	//社团申请增加获取学生列表
	@SystemAuth(url = url)
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		StsqForm myForm = (StsqForm) form;
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getXsxxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "stglStsq.do?method=getStu";
		request.setAttribute("path", path);
		return mapping.findForward("getStu");
	}
	
	//社团申请增加获取老师列表
	
	public ActionForward getTea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		StsqForm myForm = (StsqForm) form;
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);

			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getTeaxxList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		if(request.getParameter("flag")!=null && request.getParameter("flag").equals("selzdls")){
			request.setAttribute("flag", request.getParameter("flag"));
		}else{
			request.setAttribute("flag", "selstfzr");
		}
		String path = "stglStsq.do?method=getTea";
		request.setAttribute("path", path);
		return mapping.findForward("getTea");
	}

}
