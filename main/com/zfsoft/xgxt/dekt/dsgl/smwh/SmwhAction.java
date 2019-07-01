/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.smwh;

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

public class SmwhAction extends SuperAction{
	private static final String url = "xg_dekt_smwhgl.do";
	private SmwhService service = new SmwhService();
	/**
	 * @description	： 查询列表
	 * @author 		： CP（1352）
	 * @date 		：2018-3-12 下午01:59:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward smwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmwhForm model = (SmwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
	
		return mapping.findForward("smwhList");
		
	}	
	
	/**
	 * @description	： 增加
	 * @author 		： CP（1352）
	 * @date 		：2018-3-13 上午09:27:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SmwhForm model = (SmwhForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExist(model);
        	if(!isExist){
        		boolean result = service.runInsert(model);
        		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
        	}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		String path = "dekt_smwhgl.do?method=add";
		request.setAttribute("path", path);
		return mapping.findForward("smzj");
	}

	
	/**
	 * @description	： 修改
	 * @author 		：CP（1352）
	 * @date 		：2018-3-13 上午09:27:34
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			SmwhForm model = (SmwhForm) form;
		if (UPDATE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExist(model);
        	if(!isExist){
				boolean result = service.runUpdate(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			}else{
        		response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
        	}
			return null;
		}
		SmwhForm updateForm = service.getModel(model);
		request.setAttribute("smid", updateForm.getSmid());
		request.setAttribute("nj", updateForm.getNj());
		request.setAttribute("lx", updateForm.getLx());
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		String path = "dekt_smwhgl.do?method=update";
		request.setAttribute("path", path);
		return mapping.findForward("smxg");
	}
	
	
	/**
	 * @description	： 删除
	 * @author 		： CP（1352）
	 * @date 		：2018-3-13 上午09:27:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	 * @description	： 查看
	 * @author 		： CP（1352）
	 * @date 		：2018-3-13 下午04:40:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmwhForm myForm = (SmwhForm) form;
		SmwhForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("smck");
	}
	
	
	/**
	 * @description	： 导出
	 * @author 		： CP（1352）
	 * @date 		：2018-3-13 下午04:40:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmwhForm model=(SmwhForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model);//查询出所有记录，不分页
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
