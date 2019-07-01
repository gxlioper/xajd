/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.dtjs.tsdzb;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * @className	： TsdzbShAction
 * @description	： 特色党支部action(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2017-11-1 上午10:38:19
 * @version 	V1.0 
 */

public class TsdzbShAction extends SuperAction<TsdzbForm, TsdzbService>{
	private static final String url = "dtjs_tsdzbgl_tsdzbsh.do";
	
	private TsdzbService service = new TsdzbService();
	
	/**
	 * @description	： 党支部审核列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-1 上午10:55:56
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getTsdzbShList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageListForSh(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "dtjs_tsdzbgl_tsdzb.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tsdzbshList");
	} 
	
	/**
	 * @description	： 单个审核
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-1 上午11:58:27
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward dgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		TsdzbForm editModel = service.getTsdzbxx(model);
		BeanUtils.copyProperties(model, editModel);
		return mapping.findForward("dgsh");
	} 
	
	/**
	 * @description	： 保存审核
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-1 下午02:49:42
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward saveSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		boolean result = service.updateTsdzb(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
	
	/**
	 * @description	： 撤销
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-1 下午03:21:33
	 * @return
	 */
	public ActionForward cx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		String values = request.getParameter("values");
		boolean result = service.plCx(values.split(","));
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	： 导出
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-2 上午10:14:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TsdzbForm model = (TsdzbForm)form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllListForTsdzsh(model, user);//查询出所有记录，不分页
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
