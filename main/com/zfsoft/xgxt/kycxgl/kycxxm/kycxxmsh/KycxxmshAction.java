package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmsh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.gygl.ssyd.ydsh.YdshService;
import com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmjg.KycxxmjgService;

public class KycxxmshAction extends SuperAction {
	
	private static final String url = "kycxgl_kycxxm_kycxxmsh.do";
	
	/**
	 * 科研创新项目审核
	 */
	@SystemAuth(url = url)
	public ActionForward kycxxmshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmshForm model = (KycxxmshForm) form;
		KycxxmshService service = new KycxxmshService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询审核数据
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		request.setAttribute("searchTj", searchModel);
		String path = "kycxgl_kycxxm_kycxxmsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("kycxxmshManage");
	}
	/**
	 * 科研创新项目单个审核//TODO
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-项目审核-单个审核SHID:{shid}")
	public ActionForward kycxxmDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmshForm model = (KycxxmshForm) form;
		KycxxmshService service = new KycxxmshService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			boolean result = service.saveSh(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		model=service.getModel(model);
		model.setShid(request.getParameter("shid"));
		// ========== 审核下拨经费设置 begin ============
		ShlcDao shlcDao = new ShlcDao();
		List<HashMap<String, String>> shyjList = shlcDao.getShyjList(model.getSqid(), "desc");
		String shXbjf = model.getXbjf();
		if(shyjList.size() > 0){
			HashMap<String, String> shyj = shyjList.get(0);
			shXbjf = shyj.get("zd3");
			model.setXbjf(shXbjf);
		}
		// ========== 审核下拨经费设置 end ============
		request.setAttribute("rs", model);
		User user = getUser(request);
		request.setAttribute("kycxxmcyList", new KycxxmjgService().getKycxxmcyList(model.getSqid(), user));
		return mapping.findForward("kycxxmDgsh");
	}
	/**
	 * 撤销//TODO
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-项目审核-撤销SQID:{sqid}")
	public ActionForward cancelKycxxmsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmshForm model = (KycxxmshForm) form;
		User user = getUser(request);
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		KycxxmshService service = new KycxxmshService();
		boolean isSuccess = service.newCancelSh(model, user);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 批量审核保存//TODO
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问科研创新管理-科研创新管理-项目审核-批量审核VALUES:{values}")
	public ActionForward kycxxmPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmshForm model = (KycxxmshForm) form;
		KycxxmshService service = new KycxxmshService();
		User user = getUser(request);
		if("SAVE".equalsIgnoreCase(model.getType())){
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("kycxxmPlsh");
	}
	/**
	 * 自定义导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KycxxmshForm model = (KycxxmshForm) form;
		//根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
		KycxxmshService service = new KycxxmshService();
		model.setShzt(shlx);
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
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
