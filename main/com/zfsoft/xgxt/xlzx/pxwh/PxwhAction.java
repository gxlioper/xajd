/**
 * @部门：学工产品事业部
 * @日期：2016年11月17日
 */  
package com.zfsoft.xgxt.xlzx.pxwh;

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

import common.newp.StringUtil;

/**
 * @系统名称：学生工作管理系统
 * @模块名称：心理健康培训维护 管理模块
 * @类功能描述：心理健康培训维护Action
 * @作者：卓耐[工号:1391]
 * @时间：2016年11月17日
 * @版本：V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class PxwhAction extends SuperAction<PxwhForm,PxwhService> {
	private PxwhService service = new  PxwhService();
	private static final String url = "xg_xlzx_pxwh.do";
	private static final String urlstu = "xg_xlzx_pxbm.do";
	
	/**
	 * @描述：心里健康培训维护列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward pxwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm model = (PxwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pxwhList");
	}
	
	/**
	 * @描述：增加
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月17日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问心理健康咨询-心理健康培训-心理健康培训维护-增加PXID:{pxid}")
	public ActionForward pxwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm myForm = (PxwhForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			// 唯一性判断
			if (service.checkExist(myForm)) {
				boolean result = service.runInsert(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(getJsonResult(MessageKey.XLZX_PXWH_REPEAT, false));
			}
			return null;
		}
		return mapping.findForward("pxwhAdd");
	}
	
	/**
	 * @描述：编辑
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月18日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问心理健康咨询-心理健康培训-心理健康培训维护-编辑PXID:{pxid}")
	public ActionForward pxwhEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm myForm = (PxwhForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			// 唯一性判断
			if (service.checkExist(myForm)) {
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(getJsonResult(MessageKey.XLZX_PXWH_REPEAT, false));
			}
			return null;
		}
		PxwhForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		return mapping.findForward("pxwhEdit");
	}
	
	/**
	 * @描述：详情
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月17日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward pxwhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm myform = (PxwhForm) form;
		PxwhForm model = service.getModel(myform);
		BeanUtils.copyProperties(myform,model);
		return mapping.findForward("pxwhView");
	}
	
	/**
	 * @描述：导出
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月17日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PxwhForm model = (PxwhForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getBmxsdcList(model);// 查询出所有记录，不分页
		
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
	 * @描述：删除
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月18日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问心理健康咨询-心理健康培训-心理健康培训维护-删除:PXID{values}")
	public ActionForward pxwhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @描述：未报名/已报名列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = urlstu)
	public ActionForward pxbmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm model = (PxwhForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPxbmList(model,user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xg_xlzx_pxbm.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pxbmList");
	}
	
	/**
	 * @描述：报名/取消报名 操作
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = urlstu,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问心理健康咨询-心理健康培训-培训维护-报名:PXID{pxid},XH{xh}")
	public ActionForward bmcz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh=request.getParameter("xh");
		String bmtype=request.getParameter("bmtype");
		String pxid=request.getParameter("pxid");
		if(StringUtils.isNull(xh)||StringUtils.isNull(bmtype)||StringUtils.isNull(pxid)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.XLZX_PXWH_CZFAIL));
			return null;
		}
		boolean result = service.bmcz(pxid, xh, bmtype);
		String messageKey = result ? MessageKey.XLZX_PXWH_CZSUCCESS:MessageKey.XLZX_PXWH_CZFAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述：已报名学生列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月21日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward ybmxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PxwhForm model = (PxwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getYbmxsList(model);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "xg_xlzx_ybmxslist.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ybmxsList");
	}
	
}
