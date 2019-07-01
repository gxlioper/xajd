package com.zfsoft.xgxt.gygl.gypynew.gypyjg;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.gygl.gypynew.gypysq.GypySqForm;
import com.zfsoft.xgxt.gygl.gypynew.gypysq.GypySqService;
import common.newp.StringUtil;

public class GypyJgAction extends SuperAction<GypyJgForm,GypyJgService> {
	private GypyJgService service = new GypyJgService();
	private final String url = "gygl_gypynew_gypyjg.do";
	/**
	 * 
	 * @描述: 公寓评优
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-25 上午11:24:44
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
	public ActionForward getGypyJgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gypyjgcx");
	}
	
	/**
	 * 
	 * @描述: 查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-25 上午11:40:58
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
	public ActionForward searchForJgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypyJgForm myForm = (GypyJgForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:公寓评优申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-25 下午04:33:46
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
	public ActionForward addJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String nowYearMonth = GetTime.getTimeByFormat("yyyy-mm");
		request.setAttribute("month",nowYearMonth);
		return mapping.findForward("addjg");
	}
	
	/**
	 * 
	 * @描述: 
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午02:22:04
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
	public ActionForward editJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypyJgForm myForm = (GypyJgForm)form;
		GypyJgForm model = service.getModel(myForm);
		GypySqForm sqForm = new GypySqForm();
		if(model != null){
			BeanUtils.copyProperties(myForm, model);
			BeanUtils.copyProperties(sqForm, model);
		}
		String nowYearMonth = GetTime.getTimeByFormat("yyyy-mm");
		request.setAttribute("month",nowYearMonth);
		request.setAttribute("qsxx",new GypySqService().getQshxx(sqForm));
		return mapping.findForward("editjg");
	}
	/**
	 * 
	 * @描述: 保存公寓评优申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午02:28:43
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
	public ActionForward saveJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypyJgForm myForm = (GypyJgForm)form;
		User user = getUser(request);
		boolean rs = service.saveSq(myForm, user);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午02:30:08
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
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
	 * @描述: 导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-27 下午02:31:18
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
		GypyJgForm model = (GypyJgForm) form;

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
	 * 
	 * @描述: 结果查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-28 下午02:35:41
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
	public ActionForward viewJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypyJgForm myForm = (GypyJgForm)form;
		GypyJgForm model = service.getModel(myForm);
		GypySqForm sqForm = new GypySqForm();
		if(model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatDataView(model) );
			BeanUtils.copyProperties(sqForm, model );
		}
		request.setAttribute("qsxx",new GypySqService().getQshxx(sqForm));
		return mapping.findForward("viewjg");
	}
	
	/**
	 * 
	 * @描述: 
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-28 下午04:48:26
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
	public ActionForward saveCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		GypyJgForm myForm = (GypyJgForm)form;
		boolean rs = service.saveCx(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销星级
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-1 下午02:35:45
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
	public ActionForward cancelXj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		@SuppressWarnings("unused")
		GypyJgForm myForm = (GypyJgForm)form;
		return mapping.findForward("cx");
	}
}
