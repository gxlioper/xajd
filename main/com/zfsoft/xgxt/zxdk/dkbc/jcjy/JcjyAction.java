/**
 * @部门:学工产品事业部
 * @日期：2016-12-6 下午03:16:41 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.jcjy;

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
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgModel;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgService;
import com.zfsoft.xgxt.zxdk.xnwxjkhk.XnwxjkhkForm;
import com.zfsoft.xgxt.zxdk.ysjxj.ysjxj.YsjxjForm;
import com.zfsoft.xgxt.zxdk.ysjxj.ysjxj.YsjxjService;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 助学贷款--基层就业
 * @类功能描述: 基层就业补偿代偿
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2016-12-6 下午03:16:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcjyAction extends SuperAction<JcjyModel, JcjyService>{
	private static final String url = "zxdk_jcjy_bcdc.do";
	private JcjyService service = new JcjyService();
	private static final String ZXDKJCJY = "zxdkjcjy";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(ZXDKJCJY);
	}
	
	/**
	 * @描述: 查询页面
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2016-12-6 下午08:33:46
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
	public ActionForward bcdcList(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		JcjyModel model = (JcjyModel) form;
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
		String path = "zxdk_jcjy_bcdc.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bcdcList");
	}
	
	/**
	 * @描述: 增加信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-8 上午09:03:56
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
	public ActionForward jcjyAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcjyModel model = (JcjyModel) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		request.setAttribute("jbxxList", jbxxList);
		//返回行业类别代码、行业类别名称
		request.setAttribute("hylbList",service.getHylbList());
		request.setAttribute("path", "jcjy_bcdc.do?method=jcjyAdd");
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("jcjyAdd");
	}
	
	/**
	 * @描述: 修改
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2016-12-8 下午05:07:14
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
	public ActionForward jcjyUpdate(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		JcjyModel myForm = (JcjyModel) form;
		JcjyModel model = service.getModel(myForm);
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
		//返回行业类别代码、行业类别名称
		request.setAttribute("hylbList",service.getHylbList());
		String path = "jcjy_bcdc.do?method=jcjyUpdate";
		request.setAttribute("path", path);
		request.setAttribute("dclb", model.getDclb());
		request.setAttribute("clsfqq", model.getClsfqq());
		request.setAttribute("sfwxzfsfz", model.getSfwxzfsfz());
		request.setAttribute("sfzzzg", model.getSfzzzg());
		request.setAttribute("lzsfzc", model.getLzsfzc());
		request.setAttribute("dksfwqch", model.getDksfwqch());
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("jcjyUpdate");
	}
	
	/**
	 * @描述: 增加、修改保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-8 上午09:04:38
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
	public ActionForward saveJcjy(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		JcjyModel model = (JcjyModel) form;
		boolean result = false;	
		if(model.getType().equals("save")){
 			if(service.isHaveRecord(model.getXh(),model.getDclb())){
 				String messageKey = MessageKey.JYGLNEW_JYGL_BYQX_EXISTS;
 				response.getWriter().print(getJsonMessageByKey(messageKey));
 				return null;
 			}
			result = service.runInsert(model);
		}else if(model.getType().equals("update")){
 			result = service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	/**
	 * @描述: 删除方法
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-8 上午09:05:54
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
	public ActionForward delJcjy(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
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
	 * @描述: 查看
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2016-12-8 下午04:36:38
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
	public ActionForward jcjyView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcjyModel myForm = (JcjyModel) form;
		JcjyModel model = service.getModel(myForm.getJuid());
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", model);
		request.setAttribute("hylbmc", service.getHylb(model.getXh()));
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("jcjyView");
	}
	
	/**
	 * @描述: 导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-12-8 下午04:41:53
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcjyModel model = (JcjyModel) form;
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
