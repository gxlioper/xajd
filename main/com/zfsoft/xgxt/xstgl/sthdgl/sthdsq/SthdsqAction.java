/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午05:02:12 
 */
package com.zfsoft.xgxt.xstgl.sthdgl.sthdsq;

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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xstgl.sthdgl.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

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

public class SthdsqAction extends SuperAction<SthdsqForm, SthdsqService> {
	private final String RTSQ = "zyfw";
	private SthdsqService service = new SthdsqService();
	private CsszService csszService = new CsszService();
	
	private static final String url = "stgl_sthdgl_sthdsq.do";

	/**
	 * 
	 * @描述:社团活动申请列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-27 下午04:23:38
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
	public ActionForward getSthdsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SthdsqForm model = (SthdsqForm) form;
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
		String[] sqshkg = csszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
		String path = "stgl_sthdgl_sthdsq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getSthdsqList");
	}

	/**
	 * 
	 * @描述:社团活动申请增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 下午04:06:41
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
	public ActionForward addSthdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SthdsqForm model = (SthdsqForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		String path = "sthdglSthdsq.do?method=addSthdsq";
		request.setAttribute("path", path);
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RTSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("nowDate", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm"));
		//service.initParam(request, user);
		return mapping.findForward("addSthdsq");
	}

	/**
	 * 
	 * @描述:社团活动申请修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 下午04:07:06
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
	public ActionForward editSthdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SthdsqForm myForm = (SthdsqForm) form;
		User user = getUser(request);
		SthdsqForm model = service.getModel(myForm);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RTSQ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "sthdglSthdsq.do?method=editSthdsq";
		request.setAttribute("path", path);
		request.setAttribute("nowDate", GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm"));
		//service.initParam(request, user);
		return mapping.findForward("editSthdsq");
	}

	/**
	 * 
	 * @描述:社团活动申请查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 上午11:32:02
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
	public ActionForward viewSthdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SthdsqForm myForm = (SthdsqForm) form;
		SthdsqForm model = service.getSqxx(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RTSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", StringUtils.formatData(model));
		return mapping.findForward("viewSthdsq");

	}

	/**
	 * 
	 * @描述:社团活动申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 上午10:18:49
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
	public ActionForward saveSthdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 SthdsqForm model = (SthdsqForm) form;
		 //判断当前时间是否有申请记录
			boolean isExist = service.isHaveSqJl(model);
			if (isExist) {
				String message = MessageUtil.getText(MessageKey.STGL_STHDDJ_REPEAT,new String[]{model.getFwsj()});
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		 User user = getUser(request);
		 model.setLrr(user.getUserName());
		 model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
		 boolean result = service.saveSthdsq(model);
		 String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS :
		 MessageKey.SYS_SAVE_FAIL;
		 response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:社团活动申请修改保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 下午02:33:27
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
	public ActionForward saveEditSthdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 SthdsqForm model = (SthdsqForm) form;
		 //判断当前时间是否有申请记录
			boolean isExist = service.isHaveSqJl(model);
			if (isExist) {
				String message = MessageUtil.getText(MessageKey.STGL_STHDDJ_REPEAT,new String[]{model.getFwsj()});
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		 boolean result = service.saveEditSthdsq(model);
		 String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS :
		 MessageKey.SYS_SAVE_FAIL;
		 response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 下午02:35:38
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
	public ActionForward delSthdsq(ActionMapping mapping, ActionForm form,
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
	 * @日期：2015-7-27 下午03:03:21
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
	public ActionForward cancelSthdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String hdid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(hdid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			SthdsqForm model = new SthdsqForm();
			model.setHdid(hdid);
			model.setSplc(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(hdid)) > 0) {
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
	 * @日期：2015-7-27 上午10:26:05
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
	public ActionForward submitSthdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SthdsqForm model = (SthdsqForm) form;
		String values = request.getParameter("values");
		model.setHdid(values);
		boolean result = service.submitSthdsq(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:数据导出
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-27 上午11:18:26
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
		SthdsqForm model = (SthdsqForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
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
