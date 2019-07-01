/**
 * @部门:学工产品事业部
 * @日期：2016-11-24 上午11:24:40 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;
import net.sf.json.JSONArray;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-24 上午11:24:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxmdwhAction extends SuperAction<LxmdwhForm, LxmdwhService> {
	LxmdwhService service = new LxmdwhService();
	private String CQSXJQLX = "cqsxjqlx";
	/**
	 * 
	 * @描述:查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 下午02:09:17
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
	public ActionForward getMdwhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LxmdwhForm model = (LxmdwhForm)form;
		if(QUERY.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xg_rcsw_cqsx_jqlx_lxmdwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述: 删除名单维护结果，此处不做任何控制
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午10:04:35
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
	public ActionForward delWhjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			String czr = getUser(request).getUserName();
			int num = service.deleteLxmd(ids,czr);
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
	 * @描述: 留校名单维护查看导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午10:05:48
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
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		LxmdwhForm myForm = (LxmdwhForm)form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(myForm, user);
		

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
	 * @描述:查看维护结果
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午10:20:35
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
	public ActionForward ckMdwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxmdwhForm myForm = (LxmdwhForm) form;
		LxmdwhForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CQSXJQLX);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zsjgxx", service.getXmmcMap(model.getXmid()));
		request.setAttribute("rs", model);
		return mapping.findForward("ck");
	}
	
	/**
	 * 
	 * @描述:修改维护结果
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午10:20:35
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
	public ActionForward xgMdwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxmdwhForm myForm = (LxmdwhForm) form;
		if("save".equals(myForm.getType())){
			String czr = getUser(request).getUserName();
			boolean rs = service.updateLxmd(myForm,czr);
			String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		LxmdwhForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CQSXJQLX);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("zsjgxx", service.getXmmcMap(myForm.getXmid()));
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @描述: 批量名单维护
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午10:23:25
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
	public ActionForward plMdwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 学生基本信息显示配置gzkhKhjgXx
		String xmid = request.getParameter("xmid");
		//判断xmid是否为空
		List<HashMap<String, String>> xmmcList = service.getXmmcList();
		HashMap<String, String> xmmcMap = null;
		if(StringUtils.isNotNull(xmid)){
			xmmcMap = service.getXmmcMap(xmid);
		}
		request.setAttribute("xmmcMap", xmmcMap);
		request.setAttribute("xmmcList", xmmcList);
		request.setAttribute("xmid", xmid);
		return mapping.findForward("plwh");
	}
	
	/**
	 * 
	 * @描述: 保存批量名单维护
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-25 上午11:08:30
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
	public ActionForward savePlMdwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] xhs = request.getParameterValues("xh");
		LxmdwhForm myForm = (LxmdwhForm) form;
		String message = "";
		if(null == xhs || xhs.length == 0 || StringUtils.isNull(xhs[0])){
			message = "无可维护的学生，请确认！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		//验证数据是否有重复
		boolean rs = service.checkIfCanSave(xhs, myForm.getXmid());
		if(!rs){
			message = "该项目存在已维护的学生，请确认！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		String czr = getUser(request).getUserName();
		rs = service.savePlwh(myForm, xhs,czr); 
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:获取可添加学号List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-28 下午01:42:35
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
	public ActionForward getCanAddUserList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LxmdwhForm model = (LxmdwhForm)form;
		if(QUERY.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			String xhs = request.getParameter("xhs");
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getCanAddStuList(model, user, xhs);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "jqlx_lxmdwh.do?method=getCanAddUserList";
		request.setAttribute("path", path);
		request.setAttribute("xhs", request.getParameter("xhs"));
		request.setAttribute("xmid", request.getParameter("xmid"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getstu");
	}
}
