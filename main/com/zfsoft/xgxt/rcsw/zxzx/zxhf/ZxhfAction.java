package com.zfsoft.xgxt.rcsw.zxzx.zxhf;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class ZxhfAction extends SuperAction {
	
	private static final String url = "rcsw_zxzx_zxhf.do";

	/**
	 * 查询咨询回复
	 */
	@SystemAuth(url = url)
	public ActionForward zxhfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxhfForm model = (ZxhfForm) form;
		ZxhfService service = new ZxhfService();
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if("stu".equalsIgnoreCase(userStatus)){
			String msg = "该模块不允许学生访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_zxzx_zxhf.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zxhfManage");
	}
	/**
	 * 咨询回复
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-学生咨询回复-回复HFNR:{hfnr}")
	public ActionForward addZxhf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxhfForm model = (ZxhfForm) form;
		ZxhfService service = new ZxhfService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			model.setZxid(model.getZxid());
			model.setHfnr(model.getHfnr());
			model.setHfr(user.getUserName());
			model.setHfsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			boolean result = service.insertZxhf(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ZxhfForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs", model);
		return mapping.findForward("addZxhf");
	}
	/**
	 * 修改咨询回复
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-学生咨询回复-修改HFID:{hfid},HFNR:{hfnr}")
	public ActionForward updateZxhf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxhfForm model = (ZxhfForm) form;
		ZxhfService service = new ZxhfService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			model.setHfnr(model.getHfnr());
			model.setHfr(user.getUserName());
			model.setHfsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			boolean result = service.updateZxhf(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ZxhfForm updateForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs", model);
		return mapping.findForward("updateZxhf");
	}
	/**
	 * 删除咨询回复
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-在线咨询-学生咨询回复-删除VALUES:{values}")
	public ActionForward delZxhf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxhfForm model = (ZxhfForm) form;
		ZxhfService service = new ZxhfService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			boolean result =  service.deleteZxhf(values);
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,values.split(",").length) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 查看咨询回复
	 */
	@SystemAuth(url = url)
	public ActionForward viewZxhf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxhfForm model = (ZxhfForm) form;
		ZxhfService service = new ZxhfService();
		User user = getUser(request);
		model = service.getModel(model);
		request.setAttribute("rs", StringUtils.formatData(model));
		return mapping.findForward("viewZxhf");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxhfForm model = (ZxhfForm) form;
		ZxhfService service = new ZxhfService();

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
