/**
 * @部门:学工产品事业部
 * @日期：2017-1-3 下午02:46:01 
 */  
package com.zfsoft.xgxt.rcsw.rwdjsq;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.rwgl.rwtw.RwglRwtwService;

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
import com.zfsoft.xgxt.rcsw.rwdjsqcssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-3 下午02:46:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwdjsqAction extends SuperAction<RwdjsqForm, RwdjsqService> {
	private static final String RCSWRCXW = "rcswqjgl";
	private List<HashMap<String,String>> jbxxList = null;
	private RwdjsqService service = new RwdjsqService();
	/**
	 * 
	 * @描述: 查询
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-3 下午03:14:40
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjsqForm myForm = (RwdjsqForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("rwdjbase.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "rwdjsqbase.do");
		FormModleCommon.commonRequestSet(request);
		// 默认当前学年学期
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		//申请开关：
		CsszService jcszService = new CsszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg==null?"0":sqshkg[0]);
		boolean notcfbz = service.checkIsNotExist(user.getUserName());
		request.setAttribute("notcfbz", notcfbz+"");
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 删除申请记录
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-3 下午05:39:36
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
	public ActionForward delSqjl(ActionMapping mapping, ActionForm form,
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
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-3 下午05:41:43
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
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjsqForm myForm = (RwdjsqForm) form;
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType()) || "savesubmit".equals(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			/**
			 * 验证重复性
			 */
			if(!service.checkIsNotExist(myForm.getXh())){
				String  message = "该学生已有入伍登记记录，请确认！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			super.resetToken(request);
			boolean result = service.saveJg(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			if (!SAVE.equalsIgnoreCase(myForm.getType())) {
				messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//学生基本信息显示配置
		String path = "rwdjsq.do?method=add";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//入伍途径
		RwglRwtwService rwglRwtwService = new RwglRwtwService();
		List<HashMap<String, String>> rwtjList = rwglRwtwService.rwtjList();
		request.setAttribute("rwtjList", rwtjList);
		this.saveToken(request);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-4 上午08:48:59
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjsqForm myForm = (RwdjsqForm) form;
		myForm = service.getModel(myForm);
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		String path = "rwdjsq.do?method=showView";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data",myForm);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-4 上午08:51:10
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
		RwdjsqForm model = (RwdjsqForm) form;
		//根据不同的审核类型 去自定义导出
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @描述:提交
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-4 上午09:14:39
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
		RwdjsqForm myForm = (RwdjsqForm) form;
		String value = request.getParameter("values");
		myForm.setRwdjid(value);
		RwdjsqForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-4 上午09:15:11
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
		String sqbh = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqbh, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			RwdjsqForm model = new RwdjsqForm();
			model.setRwdjid(sqbh);
			model.setSplc(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqbh)) > 0) {
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
     * 保存修改结果
     */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RwdjsqForm myForm = (RwdjsqForm) form;
		User user = getUser(request);
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType()) || "savesubmit".equalsIgnoreCase(myForm.getType())) {
			boolean result = service.saveUpdateJg(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			if (!SAVE.equalsIgnoreCase(myForm.getType())) {
				messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		RwdjsqForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		//学生基本信息显示配置
		String path = "rwdjsq.do?method=update";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		//入伍途径
		RwglRwtwService rwglRwtwService = new RwglRwtwService();
		List<HashMap<String, String>> rwtjList = rwglRwtwService.rwtjList();
		request.setAttribute("rwtjList", rwtjList);
		return mapping.findForward("edit");
	}
}
