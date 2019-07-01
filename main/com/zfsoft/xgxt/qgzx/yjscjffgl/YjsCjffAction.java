package com.zfsoft.xgxt.qgzx.yjscjffgl;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 经费酬金管理
 * @类功能描述: 研究生酬金发放action
 * @作者： xiaxia
 * @时间： 2016-05-04 下午03:33:37
 * @版本： V5.7.15
 * @修改记录:
 */
public class YjsCjffAction extends SuperAction {
	private static final String url = "qgzx_jfgl_yjscjff.do";

	/**
	 * @描述: 酬金发放高级模式查询
	 * @作者：xiaxia
	 * @日期：2016-05-04 下午03:34:24
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward yjsCjffList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YjsCjffForm model = (YjsCjffForm) form;
		YjsCjffService service = new YjsCjffService();
		// 获取登录用户
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);

			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		return mapping.findForward("yjsCjffList");
	}
	

	/**
	 * @描述:增加酬金发放
	 * @作者：xiaxia
	 * @日期：2016-5-04 下午03:35:01
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问勤工助学-经费酬金管理-研究生酬金发放-增加XH:{xh}")
	public ActionForward zjyjsCjff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YjsCjffForm myForm = (YjsCjffForm) form;
		YjsCjffService service = new YjsCjffService();
		User user = getUser(request);
		String disQg = request.getParameter("disQg");
		if (!StringUtils.isNull(disQg) || StringUtils.isNull(myForm.getYrbm())) {// 如果为空则是勤工管理员
			myForm.setYrbm(user.getUserDep());
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("path", url);
		request.setAttribute("model", myForm);
		request.setAttribute("rs", service.getFfmrCs(request, myForm));

		return mapping.findForward("zjyjsCjff");
	}
	/**
	 * 
	 * @描述: 检查是否发放过
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-05-04 下午02:42:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward checkFfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YjsCjffService service = new YjsCjffService();
		String guid=request.getParameter("guid");
		String xh=request.getParameter("xh");
		String xn=request.getParameter("xn");
		String yrdwdm=request.getParameter("yrdwdm");
		String gwmc=request.getParameter("gwmc");
		String ffny=request.getParameter("ffny");
		Boolean ish=service.isHaveFfxx(guid, xh, xn, yrdwdm, gwmc,ffny);
		response.getWriter().print(ish);
		return null;
	}

	/**
	 * @描述:修改酬金发放
	 * @作者：xiaxia
	 * @日期：2016-05-04 下午03:35:25
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问勤工助学-经费酬金管理-研究生酬金发放-修改guid:{guid},XH:{xh},XN:{xn},GWMC:{gwmc}")
	public ActionForward xgyjsCjff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YjsCjffForm myForm = (YjsCjffForm) form;
		YjsCjffService service = new YjsCjffService();
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			if(result){
				result=service.runUpdate(myForm);
			}
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		YjsCjffForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("path", url);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("rs", StringUtils.formatData(service.getFfmrCs(request, myForm)));
		return mapping.findForward("xgyjsCjff");
	}

	/**
	 * @描述:删除酬金发放信息
	 * @作者：xiaxia
	 * @日期：2016-05-04 下午03:35:57
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问勤工助学-经费酬金管理-研究生酬金发放-删除ids:{ids}")
	public ActionForward scCjff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		YjsCjffService service = new YjsCjffService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;

	}

	

	/**
	 * 勤工结果维护自定义导出
	 * 
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
	public ActionForward qgjgwhExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		YjsCjffForm model = (YjsCjffForm) form;
		YjsCjffService service = new YjsCjffService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);
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
