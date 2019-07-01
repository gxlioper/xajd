/**
 * @部门:学工产品事业部
 * @日期：2017-1-11 上午08:57:03 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxdj;

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
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
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
import com.zfsoft.xgxt.rcsw.qmlxdj.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-11 上午08:57:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxdjAction extends SuperAction<LxdjForm, LxdjService> {
	LxdjService service = new LxdjService();
	private final String QMLXDJ="qmlxdj";
	/**
	 * 
	 * @描述: 查询
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午01:55:42
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
	public ActionForward getLxdjsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		LxdjForm lxfjform = (LxdjForm)form;
		User user = getUser(request);
		if(QUERY.equals(lxfjform.getType())){
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			lxfjform.setSearchModel(searchModel);

			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(lxfjform, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[]{ Base.currXq});
		request.setAttribute("searchTj", searchModel);
		CsszService csszService = new CsszService();
		String sqkg = csszService.getSqShKg();
		/**
		 * 参数设置
		 */
		request.setAttribute("sqkg", StringUtils.isNull(sqkg)?"0":sqkg);
		String path = "rcsw_qmlxdj.do";
		request.setAttribute("path", path);
		
		/**
		 * 重复标志,该判断只针对申请时限定死当前学年学期有效
		 */
		String notcfbz = service.checkNotExist(user.getUserName(), Base.currXn, Base.currXq, "sq") ? "true" : "false";
		request.setAttribute("notcfbz", notcfbz);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午02:59:52
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxdjForm lxfjform = (LxdjForm)form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			lxfjform.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(lxfjform.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(lxfjform.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(QMLXDJ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "qmlxdj.do?method=add";
		request.setAttribute("path", path);
		/**
		 * 申请默认当前学年，学期，且写死不可改
		 */
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(Base.currXq));
		request.setAttribute("dmList", service.getDmList());
		request.setAttribute("lxlxList", service.getLxlxList());
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午03:26:00
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
	public ActionForward delSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午03:29:37
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
	public ActionForward editSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxdjForm myForm = (LxdjForm)form;
		LxdjForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("sflxdm",myForm.getSflxdm());
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(QMLXDJ);
		request.setAttribute("jbxxList", jbxxList);
		String path = "qmlxdj.do?method=editSq";
		request.setAttribute("path", path);
		request.setAttribute("xn", model.getXn());
		request.setAttribute("xq", model.getXq());
		request.setAttribute("xqmc",Base.getXqmcForXqdm(model.getXq()));
		request.setAttribute("dmList", service.getDmList());
		request.setAttribute("lxlxList", service.getLxlxList());
		return mapping.findForward("edit");
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午03:38:52
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
	public ActionForward ckSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		LxdjForm myForm = (LxdjForm)form;
		if(null!=myForm){
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(QMLXDJ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("rs", service.getCkxx(myForm.getSqid()));
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @描述: 导出申请
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午04:01:20
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LxdjForm myForm = (LxdjForm)form;
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(myForm,
				user);// 查询出所有记录，不分页
		
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
	 * 
	 * @描述:提交
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午04:42:55
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
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxdjForm myForm = (LxdjForm)form;
		String value = request.getParameter("values");
		myForm.setSqid(value);
		LxdjForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午04:48:57
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
	public ActionForward cancelSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			LxdjForm model = new LxdjForm();
			model.setSqid(sqid);
			model.setSplcid(lcid);
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
	 * @描述: 保存
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午04:50:52
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
	public ActionForward saveSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxdjForm myForm = (LxdjForm)form;
		String type = myForm.getType();
		boolean rs = true;
		User user = getUser(request);
		/**
		 * 如果是增加，需要判断重复性
		 */
		if("save".equals(type) || "savesubmit".equals(type)){
			rs = service.checkNotExist(myForm.getXh(), myForm.getXn(), myForm.getXq(), "sq");
			if(!rs){
				String message = "本学期已有填写记录，请确认！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		rs = service.saveSq(myForm, user);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
