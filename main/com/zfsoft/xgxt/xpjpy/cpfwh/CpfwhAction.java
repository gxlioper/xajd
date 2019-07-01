/**
 * @部门:学工产品事业部
 * @日期：2016-2-22 下午01:54:10 
 */  
package com.zfsoft.xgxt.xpjpy.cpfwh;

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

import xgxt.action.Base;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsModel;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.xnwxjkhk.XnwxjkhkForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-22 下午01:54:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CpfwhAction extends SuperAction<CpfwhForm, CpfwhService>{
	private static final String url = "pjpy_cpfwh.do";
	private static final String CPFWH = "cpfwh";
	CpfwhService service = new CpfwhService();
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CPFWH);
	}
	
	/** 
	 * @描述:高级查询
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-22 下午01:57:22
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
	public ActionForward getCpfwhList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CpfwhForm model = (CpfwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		//searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		//searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "pjpy_cpfwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getCpfwhList");
	}
	
	/** 
	 * @描述:增加
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-22 下午02:49:35
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
	public ActionForward addCpfWh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CpfwhForm model = (CpfwhForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学年 学期list
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		String path = "cpfwh_sq.do?method=addCpfWh";
		request.setAttribute("path", path);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnsr", Base.currXn);
		request.setAttribute("xqsr", service.getXqmc(Base.currXq));
		return mapping.findForward("addCpfwh");		
	}
	
	/** 
	 * @描述:保存结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-22 下午02:52:27
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
	public ActionForward saveSqjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CpfwhForm model = (CpfwhForm) form;
		boolean result = false;
		if("add".equalsIgnoreCase(model.getType())){
			if(service.isHaveRecord(model)){
				String messageKey = MessageKey.PJPY_CPFWH_REPEAT;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			result = service.runInsert(model);
		}else if("update".equalsIgnoreCase(model.getType())){
			result = service.runUpdate(model);
		} 
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/** 
	 * @描述:修改
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-22 下午03:36:22
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
	public ActionForward editCpfwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CpfwhForm myForm = (CpfwhForm) form;
		CpfwhForm model = service.getModel(myForm);
		if (null != model) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		String path = "cpfwh_sq.do?method=editCpfwh";
		request.setAttribute("path", path);
		request.setAttribute("xnsr", model.getXn());
		request.setAttribute("xqsr", service.getXqmc(model.getXq()));
		return mapping.findForward("editCpfwh");
	}
	
	/** 
	 * @描述:删除
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-22 下午04:08:28
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
	public ActionForward delCpfwh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
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
	
	/** 
	 * @描述:查看
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-22 下午04:22:27
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
	public ActionForward viewCpfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CpfwhForm model = (CpfwhForm) form;
		request.setAttribute("jbxxList", jbxxList);
		CpfwhForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(viewForm));
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("rs", StringUtils.formatData(model));
		request.setAttribute("xnsr", model.getXn());
		request.setAttribute("xqsr", service.getXqmc(model.getXq()));
		return mapping.findForward("viewCpfwh");
	}
	
	/** 
	 * @描述:数据导出
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-22 下午04:24:00
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CpfwhForm model = (CpfwhForm) form;
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
	
	/** 
	 * @描述:导入
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-22 下午05:28:07
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
	public ActionForward toImportCpfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("toImportCpfwh");
	}
	
	
//	/** 
//	 * @描述:TODO(这里用一句话描述这个方法的作用)
//	 * @作者：柳俊[工号：1282]
//	 * @日期：2016-2-22 下午05:28:32
//	 * @修改记录: 修改者名字-修改日期-修改内容
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 * ActionForward 返回类型 
//	 * @throws 
//	 */
//	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
//	public ActionForward downloadTemplate(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		CpfwhForm cpfwhForm = (CpfwhForm) form;
//		User user = getUser(request);
//		
//		//生成高级查询对象
//		CommService comService = new CommService();
//		SearchModel searchModel = comService.getSearchModel(request);
//		cpfwhForm.setSearchModel(searchModel);
//		
//		File file = service.createImportTemplate(cpfwhForm, user);
//		FileUtil.outputExcel(response, file);
//		return null;
//	}
//	
//	/** 
//	 * @描述:导入
//	 * @作者：柳俊[工号：1282]
//	 * @日期：2016-2-23 上午08:36:16
//	 * @修改记录: 修改者名字-修改日期-修改内容
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 * ActionForward 返回类型 
//	 * @throws 
//	 */
//	public ActionForward importCpfwh(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		CpfwhForm cpfwhForm = (CpfwhForm) form;
//		User user = getUser(request);
//		
//		//生成高级查询对象
//		CommService comService = new CommService();
//		SearchModel searchModel = comService.getSearchModel(request);
//		cpfwhForm.setSearchModel(searchModel);
//		
//		try {
//			File file = service.importCpfwh(cpfwhForm,user);
//			
//			if (file != null){
//				FileUtil.outputExcel(response, file);
//				return null;
//			}
//		
//			request.setAttribute("result", true);
//			request.setAttribute("message",MessageUtil.getText(MessageKey.SYS_IMPORT_SUCCESS));
//		} catch (SystemException e) {
//			request.setAttribute("result", false);
//			request.setAttribute("message", e.getMessage());
//		}
//		
//		return toImportCpfwh(mapping, cpfwhForm, request, response);
//	}
	
	
	
	
	
	
	
}
