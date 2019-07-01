package com.zfsoft.xgxt.xlzx.xlwjga.xlwjgasb;


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
import xgxt.utils.GetTime;
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
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;

/**
 * 心理危机个案
 */
public class XlwjgasbAction extends SuperAction {
	
	private static List<HashMap<String, String>> jbxxList = null;
	private static final String XLWJGASB = "xlwjgasb";
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XLWJGASB);
	}
	
	private static final String url = "xlzx_xlwjga_xlwjgasb.do";
	
	/**
	 * 心理危机个案
	 */
	@SystemAuth(url = url)
	public ActionForward xlwjgasbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjgasbForm model = (XlwjgasbForm) form;
		XlwjgasbService service = new XlwjgasbService();
		User user = getUser(request);
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_wjgabz(new String[]{ "1" });
		request.setAttribute("searchTj", searchModel);
		String path = "xlzx_xlwjga_xlwjgasb.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xlwjgasbManage");
	}
	/**
	 * 增加 心理危机个案 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问心理健康咨询-心理危机个案管理-心理危机个案上报-增加")
	public ActionForward addXlwjgasb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjgasbForm myForm = (XlwjgasbForm) form;
		XlwjgasbService service = new XlwjgasbService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			myForm.setSbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			myForm.setSbr(user.getUserName());
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String xh = myForm.getXh();
		request.setAttribute("jbxxList", jbxxList);
		if (!StringUtil.isNull(xh)) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			
			XlwjgasbForm model = service.getModelByXh(xh);
			if(model != null){
				BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			}
		}
		List<HashMap<String,String>> wjcdList = service.getWjcdList();
		request.setAttribute("wjcdList", wjcdList);
		List<HashMap<String,String>> haveList = new OptionUtil().getOptions(OptionUtil.HAVENOT);
		request.setAttribute("haveList", haveList);
		// 默认值
		if(StringUtils.isNull(myForm.getYwzyls())){ 
			myForm.setYwzyls("1");
		}
		String path = "xlzx_xlwjga_xlwjgasbgl.do?method=addXlwjgasb";
		request.setAttribute("path", path);
		return mapping.findForward("addXlwjgasb");
	}
	
	/**
	 * 修改心理危机个案 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问心理健康咨询-心理危机个案管理-心理危机个案上报-修改XH:{xh}")
	public ActionForward updateXlwjgasb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjgasbForm myForm = (XlwjgasbForm) form;
		XlwjgasbService service = new XlwjgasbService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		String xh = myForm.getXh();
		request.setAttribute("jbxxList", jbxxList);
		if (!StringUtil.isNull(xh)) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			
			XlwjgasbForm model = service.getModelByXh(xh);
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		List<HashMap<String,String>> wjcdList = service.getWjcdList();
		request.setAttribute("wjcdList", wjcdList);
		List<HashMap<String,String>> haveList = new OptionUtil().getOptions(OptionUtil.HAVENOT);
		request.setAttribute("haveList", haveList);
		return mapping.findForward("updateXlwjgasb");
	}
	/**
	 * 查看心理危机个案 
	 */
	@SystemAuth(url = url)
	public ActionForward viewXlwjgasb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjgasbForm myForm = (XlwjgasbForm) form;
		XlwjgasbService service = new XlwjgasbService();
		String xh = myForm.getXh();
		request.setAttribute("jbxxList", jbxxList);
		// 加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		request.setAttribute("jbxx", xsjbxx);
		List<HashMap<String,String>> list = service.getModelListByXh(xh);
		request.setAttribute("rs", list.remove(0));
		request.setAttribute("rsList", list);
		return mapping.findForward("viewXlwjgasb");
	}
	
	/**
	 * 删除 心理危机个案 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问心理健康咨询-心理危机个案管理-心理危机个案上报-删除VALUES:{values}")
	public ActionForward delXlwjgasb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjgasbService service = new XlwjgasbService();	
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
	 * 解除 心理危机个案 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问心理健康咨询-心理危机个案管理-心理危机个案上报-解除VALUES:{values}")
	public ActionForward cancelXlwjgasb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XlwjgasbService service = new XlwjgasbService();	
		String values = request.getParameter("values");
		boolean result = service.cancelXlwjgasb(values.split(","));
		String message = result ? MessageUtil.getText(MessageKey.SYS_JC_NUM,values.split(",").length) 
				: MessageUtil.getText(MessageKey.SYS_JC_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	/**
	 * 自定义导出设置
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwjgasbForm model = (XlwjgasbForm) form;
		XlwjgasbService service = new XlwjgasbService();
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
