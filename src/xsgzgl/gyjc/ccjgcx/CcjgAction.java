package xsgzgl.gyjc.ccjgcx;

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
import xsgzgl.gyjc.jcjglr.JcjglrService;
import xsgzgl.gyjc.jcsd.PfbzForm;
import xsgzgl.gyjc.jcsd.PfbzService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.QueryDataService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.newp.StringUtil;

public class CcjgAction extends SuperAction<CcjgForm, CcjgService> {
	private CcjgService service = new  CcjgService();
	private PfbzService pfbzServie = new PfbzService();
	private static final String url = "gyjc_ccjgcx.do";
	private static final String JCLX_XX = "xx";//检查类型：学校
	
	/**
	 * 
	 * @描述:抽查结果查询
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-17 上午10:42:14
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
	public ActionForward ccjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcjgForm model = (CcjgForm) form;
		User user = getUser(request);
		request.getRequestURL();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		if("cx".equals(model.getFlag())){
			request.setAttribute("path", "xg_gyjc_ccjgcx.do?flag=cx");
		}else{
			request.setAttribute("path", "xg_gyjc_ccjgcx.do?flag=cc");
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", url);
		request.setAttribute("flag", model.getFlag());
		return mapping.findForward("ccjgList");
	}
	/**
	 * 
	 * @描述:抽查结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-17 下午05:31:27
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
	public ActionForward addCcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception { 
		this.saveToken(request);
		return mapping.findForward("addCcjg");
	}
	
	/**
	 * 
	 * @描述:寝室列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-17 下午06:08:25
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
	public ActionForward showQsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CcjgForm model = (CcjgForm) form;
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getQsxxPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "gyjc_ccjgcx.do?method=showQsxx";
		request.setAttribute("path", path);
        return mapping.findForward("showQsxx");	
	}
	/**
	 * 
	 * @描述:获取检查类型
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-18 下午04:21:12
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
	public ActionForward getJclxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CcjgForm model = (CcjgForm) form;
		PfbzForm bzForm = new PfbzForm();
		bzForm.setJjlx(model.getJjlx());
		bzForm.setJs(model.getJs());
		bzForm.setXydm(model.getXydm());
		List<HashMap<String,String>> jclxList = pfbzServie.getXmSelectList(bzForm);
		JSONArray dataList = JSONArray.fromObject(jclxList);
		response.getWriter().print(dataList);
		return null;
	}
	@SystemLog(description = "文明寝室-抽查结果-抽查结果保存-:学院{xydm},lddm:{lddm},qsh:{qsh}")
	public ActionForward saveCcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcjgForm model = (CcjgForm) form;
		if (service.isHaveCcjg(model)) {
			String messageKey = MessageUtil.getText(MessageKey.XG_GYJC_CCJG_REPEAT, new String[] {});
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		User user =getUser(request);
		model.setTjr(user.getUserName());
		model.setFids(request.getParameterValues("fid"));
		model.setIndexs(request.getParameterValues("index"));
		boolean result = service.editCcjg(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:修改抽查结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-20 下午04:59:28
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
	public ActionForward editCcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcjgForm myForm = (CcjgForm) form;
		CcjgForm model = service.getModel(myForm);
		request.setAttribute("model", model);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		//查询抽查结果信息
		List<HashMap<String,String>> ccjgList = service.getCcjgList(model);
		request.setAttribute("ccjgList", ccjgList);
		request.setAttribute("yscwjList", new JcjglrService().getYscfjxx(model.getFjid()));
		getJcxxList(model,request);//设置检查项列表信息
		return mapping.findForward("editCcjg");
	}
	/**
	 * 
	 * @描述:抽查结果查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-21 上午10:12:58
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
	public ActionForward viewCcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CcjgForm myForm = (CcjgForm) form;
		CcjgForm model = service.getModel(myForm);
		request.setAttribute("model", model);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		//查询抽查结果信息
		List<HashMap<String,String>> ccjgList = service.getCcjgList(model);
		request.setAttribute("ccjgList", ccjgList);
		request.setAttribute("djList", new JcjglrService().getFxdjcxForView(null, model.getXydm(), model.getQsh(), model.getLddm(), "cc", model.getJcrq()));
		getJcxxList(model,request);//设置检查项列表信息
		return mapping.findForward("viewCcjg");
	}
	private void getJcxxList(CcjgForm model,HttpServletRequest request){
		PfbzForm t = new PfbzForm();
		t.setJs(JCLX_XX);
		t.setJjlx(OptionUtil.JCLX_WSJC);
		t.setXydm(model.getXydm());
		List<HashMap<String,String>> wsjcList = pfbzServie.getXmSelectList(t);//卫生检查大项
		t.setJjlx(OptionUtil.JCLX_AQJC);
		List<HashMap<String,String>> aqjcList = pfbzServie.getXmSelectList(t);//安全检查大项
		t.setJjlx(OptionUtil.JCLX_JLJC);
		List<HashMap<String,String>> jljcList = pfbzServie.getXmSelectList(t);//纪律检查大项
		request.setAttribute("wsjcList", wsjcList);
		request.setAttribute("aqjcList", aqjcList);
		request.setAttribute("jljcList", jljcList);
		
	}
	/**
	 * 
	 * @描述:删除抽查结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-4-20 下午04:44:57
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
	@SystemLog(description = "文明寝室-抽查结果-抽查结果保存-删除VALUES:{values}")
	public ActionForward delCcjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			service.delCcmxbz(ids);
			int num = service.runDelete(ids);
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
	public ActionForward exportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CcjgForm myForm = (CcjgForm) form;
		exportData(myForm, request, response);
		return null;
	}
	/*
	 * 自定义导出功能实现
	 */
	private void exportData(CcjgForm model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		exportModel.setRowConut(model.getRowConut());
		model.getPages().setPageSize(RAM_MAX_SIZE);
		File file = exportService.getExportExcelFile(exportModel,new QueryDataService(model,user){
			@Override
			public List queryData(Object model, User user) throws Exception {
				CcjgForm fmtModel = (CcjgForm)model;
				fmtModel.getPages().setCurrentPage(OptionUtil.page);
				return service.getPageList(fmtModel, user);	
			}});
		FileUtil.outputExcel(response, file);
	}
	
	
}
