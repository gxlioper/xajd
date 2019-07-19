/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stglsh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.ttgl.stgl.stgljg.StglService;
import net.sf.json.JSONArray;

import net.sf.json.JSONObject;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.ttgl.stgl.stglsq.StglsqService;

public class StglshAction extends SuperAction {
	private static final String url = "xg_ttgl_stglsh.do";
	StglshService  service = new StglshService();
	
	/**
	 * @description	： 审核列表
	 * @author 		： CP（1352）
	 * @date 		：2018-2-2 下午03:42:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward stglShList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglshForm model = (StglshForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			
			//查询获取学生证补办审核数据
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("stglSh");
		
	}
	
	
	@SystemAuth(url = url)
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglshForm model = (StglshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		
		HashMap<String,String> stxxMap = service.getStxxInfo(model);
		//学生组织经费来源
        StglService stglService = new StglService();
        List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
        request.setAttribute("xszzjflyList",xszzjflyList);
		request.setAttribute("rs", StringUtils.formatData(stxxMap));
		request.setAttribute("filepath", stxxMap.get("filepath"));
		StglsqService stglsqService = new StglsqService();
		request.setAttribute("fzrxxInfo", stglsqService.getFzrxx(model.getSqid()));
        request.setAttribute("tzsxxInfo",stglsqService.getTzsxx(model.getSqid()));
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("stglck");
		
	}
	
	
/**
 * @description	： 社团审核
 * @author 		： CP（1352）
 * @date 		：2018-2-8 下午05:17:36
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward stglDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglshForm model = (StglshForm) form;
		if (!StringUtil.isNull(model.getSqid())){
			HashMap<String,String> stxxMap = service.getStxxInfo(model);
			request.setAttribute("rs", StringUtils.formatData(stxxMap));
			StglsqService stglsqService = new StglsqService();
			request.setAttribute("fzrxxInfo", stglsqService.getFzrxx(model.getSqid()));
			request.setAttribute("filepath", stxxMap.get("filepath"));
			request.setAttribute("tzsxxInfo",stglsqService.getTzsxx(model.getSqid()));
		}
		if (SAVE.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			//保存单个审核
			boolean result = service.saveSh(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		model=service.getModel(model);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		//学生组织经费来源
		StglService stglService = new StglService();
		List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
		request.setAttribute("xszzjflyList",xszzjflyList);
		return mapping.findForward("stglDgsh");
		
	}
	
	/**
	 * @description	： 社团转正审核
	 * @author 		： CP（1352）
	 * @date 		：2018-2-8 下午05:18:26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward stzzDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglshForm model = (StglshForm) form;
		if (!StringUtil.isNull(model.getSqid())){
			HashMap<String,String> stxxMap = service.getStxxInfo(model);
			request.setAttribute("rs", StringUtils.formatData(stxxMap));
			StglsqService stglsqService = new StglsqService();
			request.setAttribute("fzrxxInfo", stglsqService.getFzrxx(model.getSqid()));	
			request.setAttribute("filepath", stxxMap.get("filepath"));
			request.setAttribute("tzsxxInfo",stglsqService.getTzsxx(model.getSqid()));
		}
		if (SAVE.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			//保存单个审核
			boolean result = service.saveZzSh(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		model=service.getModel(model);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		//学生组织经费来源
		StglService stglService = new StglService();
		List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
		request.setAttribute("xszzjflyList",xszzjflyList);

		return mapping.findForward("stzzDgsh");
		
	}
/**
 * @description	： 社团申请取消
 * @author 		： CP（1352）
 * @date 		：2018-2-8 下午05:17:54
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglshForm model = (StglshForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		//撤销审核，最后一级。
		boolean isSuccess = service.CancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward checkisCancel(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		StglshForm model = (StglshForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		if (service.checkisCancel(model)){//已申请转正
			response.getWriter().print("true");
		}else{
			response.getWriter().print("false");
		}
		return null;
	}
	/**
	 * @description	： 社团转正审核撤销
	 * @author 		： CP（1352）
	 * @date 		：2018-2-9 上午09:49:50
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelZzSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglshForm model = (StglshForm) form;
		String sqid = request.getParameter("sqid");
		String zzywid = request.getParameter("zzywid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setZzywid(zzywid);
		model.setSqid(sqid);
		//撤销审核，最后一级。
		boolean isSuccess = service.CancelZzSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	/**
	 * @description	： 批量审核
	 * @author 		： CP（1352）
	 * @date 		：2018-2-9 上午10:12:31
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward stglPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglshForm model = (StglshForm) form;
		User user = getUser(request);
		if("SAVE".equalsIgnoreCase(model.getType())){
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("stglPlsh");
	}
	
/**
 * @description	： 导出
 * @author 		： CP（1352）
 * @date 		：2018-2-9 下午04:48:18
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StglshForm model = (StglshForm) form;
		//根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
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
