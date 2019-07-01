/**
 * @部门:学工产品事业部
 * @日期：2017年2月4日 下午2:27:36 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.bysdzbwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xszz.zzkff.ZzkffForm;
import com.zfsoft.xgxt.xszz.zzkff.ZzkffService;

import net.sf.json.JSONArray;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-组织关系转出管理模块
 * @类功能描述: 毕业生党支部代码维护Action
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月4日 下午2:27:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BysdzbwhAction extends SuperAction<BysdzbwhForm,BysdzbwhService>{
	
	private static final String url = "dtjs_bysdzbwh.do?method=dzbwhList";
	
	/**
	 * @描述:毕业生党支部维护查询列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月4日 下午5:06:37
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
	public ActionForward dzbwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BysdzbwhForm bysdzbwhForm = (BysdzbwhForm) form;
		BysdzbwhService service = new BysdzbwhService();
		
		if (QUERY.equals(bysdzbwhForm.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(bysdzbwhForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("bysdzbwhList");
	}
	
	/**
	 * @描述:增加
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月4日 下午5:28:28
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
	@SystemLog(description="党团建设-组织关系转出-毕业生党支部维护-增加党支部DZBDM:{dzbdm},DZBMC:{dzbmc}")
	public ActionForward dzbwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BysdzbwhForm bysdzbwhForm = (BysdzbwhForm) form;
		BysdzbwhService service = new BysdzbwhService();
		
		if (SAVE.equalsIgnoreCase(bysdzbwhForm.getType())){
			//判断党支部代码或名称是否存在
			bysdzbwhForm.setDzbdm(bysdzbwhForm.getDzbdm().trim());
			bysdzbwhForm.setDzbmc(bysdzbwhForm.getDzbmc().trim());
			boolean isExist=service.isExist(bysdzbwhForm);
			
			if(!isExist){
				boolean result = service.runInsert(bysdzbwhForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				//党支部代码或名称已经存在
				response.getWriter().print(getJsonMessageByKey(MessageKey.DTJS_ZZGXZC_DZBWH_EXIST));
				return null;
				
			}
		}
		return mapping.findForward("bysdzbwhAdd");
	}
	
	/**
	 * @描述:更新
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月4日 下午5:29:24
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
	@SystemLog(description="党团建设-组织关系转出-毕业生党支部维护-修改党支部DZBDM:{dzbdm},DZBMC:{dzbmc}")
	public ActionForward dzbwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BysdzbwhForm bysdzbwhForm = (BysdzbwhForm) form;
		BysdzbwhService service = new BysdzbwhService();
		
		if (UPDATE.equalsIgnoreCase(bysdzbwhForm.getType())){
			
			bysdzbwhForm.setDzbdm(bysdzbwhForm.getDzbdm().trim());
			bysdzbwhForm.setDzbmc(bysdzbwhForm.getDzbmc().trim());
			
			boolean isExist = service.isExist(bysdzbwhForm);
			
			if(!isExist){
				boolean result = service.runUpdate(bysdzbwhForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				//党支部代码或名称已经存在
				response.getWriter().print(getJsonMessageByKey(MessageKey.DTJS_ZZGXZC_DZBWH_EXIST));
				return null;
				
			}
		}
		
		BysdzbwhForm model = service.getModel(bysdzbwhForm);
		BeanUtils.copyProperties(bysdzbwhForm, StringUtils.formatData(model));
		return mapping.findForward("bysdzbwhUpdate");
	}
	
	/**
	 * @描述:删除
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月4日 下午5:29:39
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
	@SystemLog(description="党团建设-组织关系转出-毕业生党支部维护-删除党支部VALUES:{values}")
	public ActionForward dzbwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BysdzbwhForm bysdzbwhForm = (BysdzbwhForm) form;
		BysdzbwhService service = new BysdzbwhService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			String [] ids = values.split(",");
			//判断党支部代码或名称是否已经被使用，如果被使用则不允许删除
			HashMap<String,Object> r = service.isUsed(ids);
			boolean isUsed = (Boolean)r.get("isUsed");
			if(isUsed){
				//党支部代码已经被使用
				response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.DTJS_ZZGXZC_DZBWH_USED,r.get("dzbmc"))));
				return null;
			}
			
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @描述:导出
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月4日 下午5:29:39
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
	public ActionForward dzbwhExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BysdzbwhForm bysdzbwhForm = (BysdzbwhForm) form;
		BysdzbwhService service = new BysdzbwhService();
		
		//生成高级查询对象(这里没有使用高级查询)		
//		CommService comService = new CommService();
//		SearchModel searchModel = comService.getSearchModel(request);
//		bysdzbwhForm.setSearchModel(searchModel);
		User user = getUser(request);
//		List<HashMap<String,String>> resultList = service.getAllList(bysdzbwhForm,user);//查询出所有记录，不分页
		List<HashMap<String,String>> resultList = service.getAllList(bysdzbwhForm);//查询出所有记录，不分页
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = bysdzbwhForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
