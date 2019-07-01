/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jswh;

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
import xsgzgl.szdw.general.dwwh.DwwhService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class JswhAction extends SuperAction{
	private static final String url = "xg_dekt_jswhgl.do";
	private JswhService service = new JswhService();
	
	/**
	 * @description	： 查询
	 * @author 		： CP（1352）
	 * @date 		：2018-4-2 下午01:42:19
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jswhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JswhForm model = (JswhForm) form;
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
		return mapping.findForward("jswhList");
	}	
	
	/**
	 * @description	： 分配
	 * @author 		： CP（1352）
	 * @date 		：2018-4-3 下午02:24:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String zghs = request.getParameter("zghs");
		String[] zgharr = zghs.split(",");
		boolean result = service.jsFp(zgharr);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * @description	： 取消分配
	 * @author 		： CP（1352）
	 * @date 		：2018-4-3 下午04:14:28
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String zghs = request.getParameter("zghs");
		String[] zgharr = zghs.split(",");
		boolean result = service.jsQxfp(zgharr);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//西安交通大学
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveSfkyy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String zghs = request.getParameter("zghs");
		String[] zgharr = zghs.split(",");
		boolean result = service.jsSfkyy(zgharr);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dekt_10698(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		request.setAttribute("zgh", request.getParameter("zgh"));
		return mapping.findForward("dekt_10698");
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dektSave_10698(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zgh = request.getParameter("zgh");
		String zzshen = request.getParameter("zzshen"); // 是否思政队伍
		JswhService service = new JswhService();
		
		boolean flag = service.dektSave(zgh, zzshen);
		String message = flag ? "保存成功！":"保存失败！";
		request.setAttribute("message", message);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * @description	： 导出
	 * @author 		： CP（1352）
	 * @date 		：2018-4-4 上午10:24:41
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
		JswhForm model = (JswhForm) form;
		//根据不同的审核类型 去自定义导出
		String jslx = request.getParameter("jslx");
		model.setJslx(jslx);
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
