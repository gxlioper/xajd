package com.zfsoft.xgxt.jyglnew.jygl.cypx;

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
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

public class CypxAction extends SuperAction {
	
	private static final String JYGLNEWCYJYYZD = "jyglnewcyjyyzd";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "jyglnew_jygl_cypx.do";

	/**
	 * 查询创业培训
	 */
	@SystemAuth(url = url)
	public ActionForward cypxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CypxForm model = (CypxForm) form;
		CypxService service = new CypxService();
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if("stu".equalsIgnoreCase(userStatus)){
			String msg = "该模块不允许学生访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "jyglnew_jygl_cypx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cypxManage");
	}
	/**
	 * 增加创业培训
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-创业培训-增加")
	public ActionForward addCypx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CypxForm model = (CypxForm) form;
		CypxService service = new CypxService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean flag = service.checkExistSave(model, user);
			if (flag) {
				boolean result = service.insertCypx(model, user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String message = MessageUtil.getText(MessageKey.JYGLNEW_JYGL_CYPX_EXISTS) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			model.setSjhm(xsjbxx.get("sjhm"));
			model.setQqhm(xsjbxx.get("qqhm"));
		}
		jbxxList = bdpzService.getJbxxpz(JYGLNEWCYJYYZD);
		request.setAttribute("jbxxList", jbxxList);
		List<HashMap<String, String>> pxlxList = service.getPxlxList();
		request.setAttribute("pxlxList", pxlxList);
		List<HashMap<String, String>> jyxsList = service.getJyxsList();
		request.setAttribute("jyxsList", jyxsList);
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);
		request.setAttribute("isnotList", isnotList);
		String path = "jyglnew_jygl_cypxgl.do?method=addCypx";
		request.setAttribute("path", path);
		return mapping.findForward("addCypx");
	}
	/**
	 * 修改创业培训
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-创业培训-修改XH:{xh}")
	public ActionForward updateCypx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CypxForm model = (CypxForm) form;
		CypxService service = new CypxService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean flag = service.checkExistSave(model, user);
			if (flag) {
				boolean result = service.updateCypx(model, user);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String message = MessageUtil.getText(MessageKey.JYGLNEW_JYGL_CYPX_EXISTS) ;
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			HashMap<String, String> updateForm = service.getModelMap(model);
			BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		}
		jbxxList = bdpzService.getJbxxpz(JYGLNEWCYJYYZD);
		request.setAttribute("jbxxList", jbxxList);
		List<HashMap<String, String>> pxlxList = service.getPxlxList();
		request.setAttribute("pxlxList", pxlxList);
		List<HashMap<String, String>> jyxsList = service.getJyxsList();
		request.setAttribute("jyxsList", jyxsList);
		List<HashMap<String, String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);
		request.setAttribute("isnotList", isnotList);
		request.setAttribute("rs", StringUtils.formatData(model));
		return mapping.findForward("updateCypx");
	}
	/**
	 * 删除创业培训
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问就业管理-就业管理-创业培训-删除VALUES:{values}")
	public ActionForward delCypx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CypxForm model = (CypxForm) form;
		CypxService service = new CypxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 查看创业培训
	 */
	@SystemAuth(url = url)
	public ActionForward viewCypx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CypxForm model = (CypxForm) form;
		CypxService service = new CypxService();
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		jbxxList = bdpzService.getJbxxpz(JYGLNEWCYJYYZD);
		request.setAttribute("jbxxList", jbxxList);
		HashMap<String,String> rs = service.getModelMap(model);
		request.setAttribute("rs", StringUtils.formatData(rs));
		return mapping.findForward("viewCypx");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CypxForm model = (CypxForm) form;
		CypxService service = new CypxService();
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
