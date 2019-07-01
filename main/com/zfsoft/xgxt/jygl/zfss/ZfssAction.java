/**
 * @部门:学工产品事业部
 * @日期：2013-5-27 下午02:06:21 
 */  
package com.zfsoft.xgxt.jygl.zfss;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学工管理系统
 * @模块名称: 公寓管理
 * @类功能描述:  
 * @作者： huj
 * @时间： 2013-5-27 下午02:06:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZfssAction extends SuperAction{
	
	private static final String url = "jygl_zfss_zfssMg.do";
	
	/**
	 * 
	 * @描述:走访登记列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-30 下午02:18:44
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
	public ActionForward zfssManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
		if (QUERY.equals(myForm.getType())){
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "jygl_zfss_zfssMg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zfssList");
	}
	
	/**
	 * 
	 * @描述:新增走访登记
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-30 下午02:19:33
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
	@SystemLog(description="访问公寓管理-辅导员走访管理-辅导员走访登记-增加ZGH:{zgh},ZBR:{zbr},JRSJ:{jrsj},FWLY:{fwly}")
	public ActionForward addZfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("zfssAdd");
	}
	
	/**
	 * 
	 * @描述:保存走访登记
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-30 下午02:19:55
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
	@SystemLog(description="访问公寓管理-辅导员走访管理-辅导员走访登记-修改PK:{djid},ZGH:{zgh},ZBR:{zbr},JRSJ:{jrsj},LKSJ:{lksj},FWLY:{fwly}")
	public ActionForward updateZfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
//		RclfdjService rclfsjervice = new RclfdjService();
//		LdglServices lds=new LdglServices();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		ZfssForm model = service.getModel(myForm);
		
//		request.setAttribute("yqlist", lds.getYqList(""));//园区列表
//		request.setAttribute("ldlist", rclfsjervice.getLdList(model.getBfwyq()));//楼栋列表
//		request.setAttribute("cslist", rclfsjervice.getCsList(model.getBfwld()));//层数列表
//		request.setAttribute("qslist", rclfsjervice.getQsList(model.getBfwld(),model.getBfwcs()));//寝室列表
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("zfssUpdate");
	}
	
	/**
	 * 
	 * @描述:删除走访登记
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-30 下午02:20:08
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
	@SystemLog(description="访问公寓管理-辅导员走访管理-辅导员走访登记-删除VALUES:{values}")
	public ActionForward delZfss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssService service = new ZfssService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:走访登记汇总
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-8-30 下午02:20:19
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
	public ActionForward zfssCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
		if (QUERY.equals(myForm.getType())){
			List<HashMap<String,String>> resultList = service.getZfssCountList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "jygl_zfss_zfssCt.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zfssCountList");
	}

	/**
	 * @描述:辅导员走访登记列表导出
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-11-27下午5:25:25
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
	public ActionForward exportZfxxData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm);
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * @描述:辅导员走访登记统计导出
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-10-27 下午7:11:41
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
	public ActionForward exportZfxxCountData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZfssForm myForm = (ZfssForm) form;
		ZfssService service = new ZfssService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllZfssCountList(myForm);
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
