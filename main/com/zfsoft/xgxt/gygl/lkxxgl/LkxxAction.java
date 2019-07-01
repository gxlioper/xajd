/**
 * @部门:学工产品事业部
 * @日期：2016-4-18 下午02:16:19 
 */  
package com.zfsoft.xgxt.gygl.lkxxgl;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-4-18 下午02:16:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LkxxAction extends SuperAction<LkxxForm, LkxxService>{
	private LkxxService service = new LkxxService();
	private static final String url = "xgygl_lkxxgl_zsdj.do";
	
	/** 
	 * @描述:查询
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-18 下午02:23:57
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
	public ActionForward getLkxxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LkxxForm model = (LkxxForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		//searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		//searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);		
		String path = "xgygl_lkxxgl_zsdj.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getLkxxList");
	}
	
	/** 
	 * @描述:增加
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-18 下午03:43:31
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addLkxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LkxxForm model = (LkxxForm) form;
		if(SAVE.equalsIgnoreCase(model.getType())){
			boolean result = false;
			if(service.isHaveRecord(model)){
				String messageKey = MessageKey.GYGL_LKXX_REPEAT;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
			}else{
				result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}			
		}
		String path = "xgygl_lkxxdj.do?method=addLkxx";
		request.setAttribute("path", path);
		return mapping.findForward("addLkxx");
	}
	
	/** 
	 * @描述:修改
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-19 上午08:51:31
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editLkxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LkxxForm model = (LkxxForm) form;
		if(UPDATE.equalsIgnoreCase(model.getType())){
			boolean result = false;
			if(service.isHaveRecord(model)){
				String messageKey = MessageKey.GYGL_LKXX_REPEAT;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
			}else{
				result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}			
		}
		LkxxForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(viewForm));
		String path = "xgygl_lkxxdj.do?method=editLkxx";
		request.setAttribute("path", path);
		return mapping.findForward("editLkxx");
	}
	
	/** 
	 * @描述:删除
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-19 上午09:16:14
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delLkxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * @描述:查看
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-19 上午09:28:01
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
	public ActionForward viewLkxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LkxxForm model = (LkxxForm) form;
		LkxxForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(viewForm));
		request.setAttribute("rs", model);		
		return mapping.findForward("viewLkxx");
	}
	
	/** 
	 * @描述:导出
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-19 上午09:52:16
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LkxxForm model = (LkxxForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
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
	
	
}
