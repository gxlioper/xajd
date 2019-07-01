/**
 * @部门:学工产品事业部
 * @日期：2017-2-8 上午10:08:12 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsq;

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
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.dtjs.zzgxzc.jcsz.JcszDao;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-2-8 上午10:08:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcsqDO extends SuperAction<ZcsqForm, ZcsqSERVICE> {
	ZcsqSERVICE service = new ZcsqSERVICE();
	private final String ZCSQ ="zcsq";
	
	private static final String url_js = "dtjs_dzzgxsq_js.do";//dtjs_dzzgxsq_js.do 教师申请列表
	private static final String url_xs = "dtjs_dzzgxsq_xs.do";//dtjs_dzzgxsq_xs.do 学生申请列表

	/**
	 * 
	 * @描述: 申请查询
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-8 上午11:23:18
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
	@SystemAuth(url = url_js)
	public ActionForward dzzGxJsSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZcsqForm model = (ZcsqForm)form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "dtjs_dzzgxsq_js.do";
		request.setAttribute("path", path);
		JcszDao jcsz = new JcszDao();
		String sqkg = StringUtils.isNotNull(jcsz.getSqKg()) ? jcsz.getSqKg():"0";
		request.setAttribute("sqkg", sqkg);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jscx");
	}
	
	/**
	 * 
	 * @描述: 党组织关系转出申请
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-8 下午04:37:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward zcSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZcsqForm model = (ZcsqForm)form;
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dzbList",service.getDzbList());
		String path = "dzzgxsq.do?method=zcSq";
		request.setAttribute("path", path);
		return mapping.findForward("sq");
	}
	
	/**
	 * 保存[增加(提交)，修改(提交)]
	 * @作者：yxy[工号：1206]
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZcsqForm myForm = (ZcsqForm)form;
		String type = myForm.getType();
		boolean rs = true;
		User user = getUser(request);
		/**
		 * 如果是增加，需要判断重复性
		 */
		if("save".equals(type) || "savesubmit".equals(type)){
			rs = service.IsNotExist(myForm);
			if(!rs){
				String message = "该学生已有填写记录，请确认！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		rs = service.saveSq(myForm, user);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		if (type.indexOf("submit") != -1) {
			messageKey = rs ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		}
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 查看申请
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-9 下午02:24:02
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
	public ActionForward cksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZcsqForm myForm = (ZcsqForm)form;
		ZcsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String, String> rs = service.ckZcsq(myForm.getXh());
		request.setAttribute("rs", rs);
		return mapping.findForward("cksq");
	}
	
	/**
	 * 
	 * @描述: 修改申请
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-9 下午02:26:35
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
	public ActionForward xgsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZcsqForm myForm = (ZcsqForm)form;
		ZcsqForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("dzbList",service.getDzbList());
		String path = "dzzgxsq.do?method=xgsq";
		request.setAttribute("path", path);
		return mapping.findForward("xgsq");
	}
	
	/**
	 * 
	 * @描述: 学生端申请
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-9 下午02:28:52
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
	@SystemAuth(url = url_xs)
	public ActionForward xssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		User user = getUser(request);
		if(!"stu".equals(user.getUserType())){
			request.setAttribute("message", "该页面只能学生党员用户访问。");
			return mapping.findForward("error");
		}
		String xh = user.getUserName();
		boolean isdy = service.IsDy(xh);
		if(!isdy){
			request.setAttribute("message", "该页面只能学生党员用户访问。");
			return mapping.findForward("error");
		}else{
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			// 学生基本信息显示配置gzkhKhjgXx
			BdpzService bdpzService = new BdpzService();
			List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
			request.setAttribute("jbxxList", jbxxList);
			JcszDao jcsz = new JcszDao();
			String sqkg = StringUtils.isNotNull(jcsz.getSqKg()) ? jcsz.getSqKg():"0";
			request.setAttribute("sqkg", sqkg);
			HashMap<String, String> rs = service.ckZcsq(xh);
			request.setAttribute("rs", rs);
			request.setAttribute("xh", xh);
			request.setAttribute("dzbList",service.getDzbList());
			if(rs == null || rs.isEmpty()){
				request.setAttribute("saveType", "save");
				request.setAttribute("submitType", "savesubmit");
			}else{
				request.setAttribute("saveType", "update");
				request.setAttribute("submitType", "updatesubmit");
			}
			
		}
		return mapping.findForward("xssq");
	}
	
	/**
	 * 
	 * @描述: 删除申请
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-9 下午02:31:02
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
	@SystemLog(description="访问党团建设-组织关系转出-信息登记-删除VALUES:{values}")
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
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
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
	@SystemLog(description="访问党团建设-组织关系转出-信息登记-提交VALUES:{values}")
	public ActionForward submitBusi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZcsqForm myForm = (ZcsqForm)form;
		String value = request.getParameter("values");
		myForm.setSqid(value);
		ZcsqForm model = service.getModel(myForm);
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
	@SystemLog(description="访问党团建设-组织关系转出-信息登记-撤销VALUES:{values}")
	public ActionForward cancelSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			ZcsqForm model = new ZcsqForm();
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
		ZcsqForm myForm = (ZcsqForm)form;
		
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
	
}
