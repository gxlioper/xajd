/**
 * @部门:学工产品事业部
 * @日期：2016-12-21 上午11:51:34 
 */  
package com.zfsoft.xgxt.xlzx.xlsc;

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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 心理筛查方法类
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2016-12-21 上午11:51:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlscjgAction extends SuperAction<XlscjgForm,XlscjgService>{
	private static final String url = "xg_xlzx_xlscjg.do";
	private XlscjgService service = new XlscjgService();
	private static final String XLZXXLSCJG = "xlzxxlscjg";
	private static List<HashMap<String, String>> jbxxList = null;
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XLZXXLSCJG);
	}
	
	/**
	 * @描述: 查询列表，页面跳转
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-12-21 下午03:21:33
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
	public ActionForward xlscjgManage(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		XlscjgForm model = (XlscjgForm) form;
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
		String path = "xg_xlzx_xlscjg.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xlscjgList");
	}
	
	/**
	 * @描述: 增加、保存方法
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2016-12-21 下午03:22:02
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
	@SystemLog(description="访问心理咨询-心理筛查结果-增加XH:{xh},SCRQ:{scrq},SCL:{scl},SDS:{sds},SAS:{sas},BKYY:{bkyy},BKJL:{bkjl},SFXYYT:{sfxyyt},SFYYT:{sfyyt}")
	public ActionForward xlscjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlscjgForm model = (XlscjgForm) form;
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
		if (SAVE.equalsIgnoreCase(model.getType())) {
			// 唯一性判断
			boolean isExist = service.uniqueness(model);
			if (!isExist) {
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.XLZX_XLSCJG_EXIST, false));
			}
			return null;
		}
		//设置Path
		String path = "xlzx_xlscjg.do?method=xlscjgAdd";
		request.setAttribute("path", path);
		//学生基本信息显示配置
		request.setAttribute("jbxxList", jbxxList);
		//做换行处理，不会出现换行出现<br/>
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("xlscjgAdd");
	}
	
	/**
	 * @描述: 修改方法、保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-12-21 下午05:34:15
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
	@SystemLog(description="访问心理咨询-心理筛查结果-修改id:{id},XH:{xh},SCRQ:{scrq}")
	public ActionForward xlscjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlscjgForm model = (XlscjgForm) form;
		if (UPDATE.equalsIgnoreCase(model.getType())){
			boolean isExist = service.uniqueness(model);
			if (!isExist) {
				boolean result = service.runUpdate(model);
				String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(message));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.XLZX_XLSCJG_EXIST, false));
			}
			return null;
		}
		XlscjgForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//学生基本信息显示配置
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("sfxyyt", myForm.getSfxyyt());
		request.setAttribute("sfyyt", myForm.getSfyyt());
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("xlscjgUpdate");
	}
	
	/**
	 * @描述: 删除方法
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-12-21 下午05:21:10
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
	@SystemLog(description="访问心理咨询-心理筛查结果-删除VALUES:{values}")
	public ActionForward xlscjgDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-12-21 下午08:11:42
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
	public ActionForward xlscjgView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		XlscjgForm model = (XlscjgForm) form;
		XlscjgForm viewForm = service.getModel(model);
		BeanUtils.copyProperties(model, viewForm);
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("model", model);
		//是否需要参加约谈
		String sfxyyt = "";
		if("0".equals(model.getSfxyyt())){
			 sfxyyt = "否";
		}else{
			sfxyyt = "是";
		}
		request.setAttribute("sfxyyt", sfxyyt);
		//是否已参加约谈
		String sfyyt = "";
		if("0".equals(model.getSfyyt())){
			sfyyt = "否";
		}else{
			sfyyt = "是";
		}
		request.setAttribute("sfyyt", sfyyt);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("xlscjgView");
	}
	
	/**
	 * @描述: 导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-12-21 下午08:16:23
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
		XlscjgForm model = (XlscjgForm) form;
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