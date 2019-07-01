/**
 * @部门:学工产品事业部
 * @日期：2016-7-22 上午10:41:42 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.sq;

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
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszService;
import xsgzgl.gygl.xyzsgl.jg.XyzsglDao;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

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
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.tttzxm.comm.CommTtxmService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-22 上午10:41:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TttzxmAction extends SuperAction<TttzxmForm, TttzxmService> {
	TttzxmService service = new TttzxmService();
	/**
	 * 
	 * @描述: 查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-25 上午10:53:30
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
	public ActionForward getTtxmsqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmForm model = (TttzxmForm) form;
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
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_ttxm_sq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 *
	 * @描述: 团体拓展项目申请增加
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-25 上午10:54:07
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
		User user = getUser(request);
		request.setAttribute("usertype", user.getUserType());
		if("stu".equals(user.getUserType())){
			String xh = user.getUserName();
			CommTtxmService service = new CommTtxmService();
			//置回队长信息
			request.setAttribute("xsmap", service.getDzxx(xh));
		}
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述: 保存申请
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午03:01:44
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
	public ActionForward saveTtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmForm model = (TttzxmForm) form;
		CommTtxmService commService = new CommTtxmService();
		boolean result = false;
	    User user = getUser(request);
	    String[] xhs = request.getParameterValues("xh");
	    model.setXhArr(xhs);
	    model.setSqr(user.getUserName());
		if(model.getType().equals("save")||model.getType().equals("submit")){
			//判断团队名称是否重复
			boolean isNotExist = commService.checkNameIsNotExists(model.getTdmc(),  model.getXmdm(), null, "qb");
			//重复返回提示
			if(!isNotExist){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SZTZ_NEW_TTXM_MCREPEAT));
				return null;
			}
			//判断团队人员是否已经申请过该项目
			String xhRepeatStr =  commService.checkIsNotExists(xhs,model.getXmdm(), null);
			String[] xhRepeatStrArr = new String[0];
			if(StringUtils.isNotNull(xhRepeatStr)){
				xhRepeatStrArr = xhRepeatStr.split(";");
			}
			//如果返回的学号字符串数组大于0，直接返回
			if (xhRepeatStrArr.length > 0) {
				StringBuilder message = new StringBuilder();
				for (int i = 0; i < xhRepeatStrArr.length; i++) {
					message.append("["+xhRepeatStrArr[i]+"]");
					if(i != xhRepeatStrArr.length-1){
						message.append(",");
					}
				}
				message.append("已申请过该项目!");
				response.getWriter().print(getJsonMessage(message.toString()));
				return null;
			}
			result = service.saveTtsq(model, user);
		}else if(model.getType().equals("update")||model.getType().equals("updatesubmit")){
			//判断团队名称是否重复
			boolean isNotExist = commService.checkNameIsNotExists(model.getTdmc(),  model.getXmdm(), model.getTtsqid(), "qb");
			//重复返回提示
			if(!isNotExist){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SZTZ_NEW_TTXM_MCREPEAT));
				return null;
			}
			//判断团队人员是否已经申请过该项目
			String xhRepeatStr =  commService.checkIsNotExists(xhs,model.getXmdm(), model.getTtsqid());
			String[] xhRepeatStrArr = new String[0];
			if(StringUtils.isNotNull(xhRepeatStr)){
				xhRepeatStrArr = xhRepeatStr.split(";");
			}
			//如果返回的学号字符串数组大于0，直接返回
			if (xhRepeatStrArr.length > 0) {
				StringBuilder message = new StringBuilder();
				for (int i = 0; i < xhRepeatStrArr.length; i++) {
					message.append("["+xhRepeatStrArr[i]+"]");
					if(i != xhRepeatStrArr.length-1){
						message.append(",");
					}
				}
				message.append("已申请过该项目!");
				response.getWriter().print(getJsonMessage(message.toString()));
				return null;
			}
			result = service.saveTtsqUpdate(model, user);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:提交
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午04:30:10
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
		TttzxmForm myForm = (TttzxmForm) form;
		String value = request.getParameter("values");
		myForm.setTtsqid(value);
		TttzxmForm model = service.getModel(myForm);
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
     * @日期：2016-7-26 下午04:39:59
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
	public ActionForward cancelZssq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ttsqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(ttsqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			TttzxmForm model = new TttzxmForm();
			model.setTtsqid(ttsqid);
			model.setSplc(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(ttsqid)) > 0) {
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
	 * @描述:导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午04:40:18
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
		
		TttzxmForm model = (TttzxmForm) form;

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
	 * @描述: 查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午04:41:09
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
	public ActionForward TtsqView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmForm myForm = (TttzxmForm) form;
		TttzxmForm model = service.getModel(myForm);
		User user = getUser(request);
		CommTtxmService commService = new CommTtxmService();
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
		}
		request.setAttribute("usertype", user.getUserType());
		//队长信息map
		request.setAttribute("dzxxmap", commService.getDzxx(model.getDzxh()));
		//队员信息list
		request.setAttribute("dyzzlist", commService.getDyxxNotDz(model.getTtsqid(), model.getDzxh()));
		//项目信息
		request.setAttribute("xmxxmap", commService.getXmxxMap(model.getXmdm()));
		return mapping.findForward("view");
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午04:41:31
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
	public ActionForward editTtsq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TttzxmForm myForm = (TttzxmForm) form;
		TttzxmForm model = service.getModel(myForm);
		CommTtxmService commService = new CommTtxmService();
		User user = getUser(request);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		request.setAttribute("usertype", user.getUserType());
		//队长信息map
		request.setAttribute("dzxxmap", commService.getDzxx(model.getDzxh()));
		//队员信息list
		request.setAttribute("dyzzlist", commService.getDyxxNotDz(model.getTtsqid(), model.getDzxh()));
		//项目信息
		request.setAttribute("xmxxmap", commService.getXmxxMap(model.getXmdm()));
		return mapping.findForward("editTtsq");
	}
	
	/**
	 * 
	 * @描述:删除团体申请
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-27 下午04:10:12
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
	public ActionForward delTtsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		CommTtxmService commService = new CommTtxmService();
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			XyzsSqForm myForm = new XyzsSqForm();
			int num = service.runDelete(ids);
			boolean result = num > 0;
			result = commService.delTtcy(ids);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	

}
