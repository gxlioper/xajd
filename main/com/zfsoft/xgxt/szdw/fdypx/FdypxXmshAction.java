/**
 * @部门:学工产品事业部
 * @日期：2013-7-25 下午4:13:59 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.fdyrz.FdyrzsqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:辅导员培训审核
 * @作者： zhangjw
 * @时间： 2013-7-25 下午4:14:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdypxXmshAction extends SuperAction {
	
	private static final String url = "szdw_fdypxxmsh.do?method=fdypxxmList";

	/**
	 * @描述:辅导员培训审核
	 * @作者：zhangjw
	 * @日期：2013-7-26 下午2:34:33
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
	public ActionForward fdypxxmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdypxXmshForm myForm = (FdypxXmshForm) form;
		FdypxXmshService service = new FdypxXmshService();
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_fdypxxmsh.do?method=fdypxxmList");
		return mapping.findForward("list");
	}
	/**
	 * @描述:辅导员培训项目审核
	 * @作者：zhangjw
	 * @日期：2013-7-26 下午2:09:53
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
	public ActionForward fdypxxmsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmshForm myForm = (FdypxXmshForm) form;
		FdypxXmsqService sqservice = new FdypxXmsqService();
		FdypxXmshService service = new FdypxXmshService();
		//获取辅导员培训申请信息
		FdypxXmsqForm model = sqservice.getModel(myForm);
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			//保存审核结果
			myForm.setSplc(model.getSplc());
			boolean result = service.fdypxsh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		FdyrzsqService fdyservice = new FdyrzsqService();
		//获取辅导员信息
		HashMap<String,String> map = fdyservice.getFdyjbxx(model.getSqr());
		request.setAttribute("rs", StringUtils.formatData(map));
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("myForm", myForm);
		if("ck".equalsIgnoreCase(myForm.getType())){
			return mapping.findForward("fdypxck");
		}
		return mapping.findForward("fdypxsh");
	}
	/**
	 * @描述:辅导员培训结果
	 * @作者：zhangjw
	 * @日期：2013-7-26 下午2:43:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = "szdw_fdypxxmsh.do?method=fdypxjgList")
	public ActionForward fdypxjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdypxXmshForm myForm = (FdypxXmshForm) form;
		FdypxXmshService service = new FdypxXmshService();
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_fdypxxmsh.do?method=fdypxjgList");
		return mapping.findForward("jglist");
	}
	/**
	 * @描述:辅导员培训结果导出
	 * @作者：zhangjw
	 * @日期：2013-7-26 下午4:09:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = "szdw_fdypxxmsh.do?method=fdypxjgList")
	public ActionForward fdypxjgExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdypxXmshForm myForm=(FdypxXmshForm)form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		FdypxXmshService service = new FdypxXmshService();
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
	 * 
	 * @描述:TODO(撤销流程)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-10 下午04:22:49
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
	public ActionForward cancelFdyxmsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdypxXmshForm model = (FdypxXmshForm) form;
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		FdypxXmshService service = new FdypxXmshService();
		//撤销日常行为审核，最后一级。
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @描述:批量保存审核
	 * @作者：cq [工号：785]
	 * @日期：2014-4-29 下午04:05:16
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
	public ActionForward fdypxxmPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmshForm model = (FdypxXmshForm) form;
		FdypxXmshService service = new FdypxXmshService();
		User user = getUser(request);
		
		if("save".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model,user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}

		return mapping.findForward("fdypxxmPlsh");
	}
	
	/**
	 * 
	 * @描述:已获得结果查看
	 * @作者：cq [工号：785]
	 * @日期：2015-4-24 下午05:18:02
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
	@SystemAuth(url = "szdw_fdypxxmsh.do?method=fdypxjgList")
	public ActionForward yhdpxxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdypxXmshForm myForm = (FdypxXmshForm) form;
		FdypxXmshService service = new FdypxXmshService();
		List<HashMap<String, String>> sqjgList = service.getSqjg(myForm.getSqid());
		request.setAttribute("sqjgList", sqjgList);
		return mapping.findForward("yhdpxxm");
	}
	
}
