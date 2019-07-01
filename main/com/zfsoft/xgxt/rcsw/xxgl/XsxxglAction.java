/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午03:25:29 
 */  
package com.zfsoft.xgxt.rcsw.xxgl;
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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
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
 * @模块名称: 日常事务管理模块
 * @类功能描述: 学生献血管理action 
 * @作者： zhangjw 
 * @时间： 2013-4-18 下午03:26:39 
 * @版本： V5.1.75
 */
public class XsxxglAction extends SuperAction {
	
	private static final String url = "rcsw_xsxxgl.do?method=gjcxXxgl";

	private static final String  XSXXGL = "xsxxgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	/**
	 * @描述:学生献血信息高级模式查询
	 * @作者：zhangjw
	 * @日期：2013-4-18 下午04:22:47
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型 
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward gjcxXxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxglForm myForm = (XsxxglForm) form;
		XsxxglService service = new XsxxglService();
		
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_xsxxgl.do?method=gjcxXxgl";
		request.setAttribute("path", path);
		request.setAttribute("tableName", "XG_RCSW_XSXXGL");
		request.setAttribute("realTable", "XG_RCSW_XSXXGL");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gjcxXxgl");
	}
	/**
	 * @描述:增加学生献血信息
	 * @作者：zhangjw
	 * @日期：2013-4-18 下午04:26:34
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型 
	 * @throws Exception
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-学生献血管理-学生献血信息维护-增加XH:{xh},XN:{xn},XXSJ:{xxsj}")
	public ActionForward zjXxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglForm myForm = (XsxxglForm) form;
		XsxxglService service = new XsxxglService();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//设置学生基本信息
		szXsxx(request,myForm.getXh());
		myForm.setXn(Base.currXn);
		String path = "rcsw_xsxxgl.do?method=zjXxgl";
		request.setAttribute("path", path);
		request.setAttribute("model", StringUtils.formatData(myForm));
		request.setAttribute("rs", StringUtils.formatData(service.getFfmrCs(request,myForm)));
		return mapping.findForward("zjXxgl");
	}
	/**
	 * @描述:设置学生基本信息
	 * @作者：zhangjw
	 * @日期：2013-5-3 上午10:59:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param xh 学号
	 * void 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	private void szXsxx(HttpServletRequest request,String xh){
		//查询学生信息
		if(xh != null && !"".equals(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(XSXXGL);
		request.setAttribute("jbxxList", jbxxList);
	}
	/**
	 * @描述:修改学生献血信息
	 * @作者：zhangjw
	 * @日期：2013-4-18 下午04:26:01
	 * @修改记录:  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型 
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-学生献血管理-学生献血信息维护-修改XXGLDM:{xxgldm},XH:{xh},XN:{xn},XXSJ:{xxsj}")
	public ActionForward xgXxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglForm myForm = (XsxxglForm) form;
		XsxxglService service = new XsxxglService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//设置学生基本信息
		szXsxx(request,myForm.getXh());
		String path = "rcsw_xsxxgl.do?method=xgXxgl";
		request.setAttribute("path", path);
		request.setAttribute("type", "update");
		XsxxglForm model = (XsxxglForm)StringUtils.formatData(service.getModel(myForm));
		request.setAttribute("model", model);
		request.setAttribute("rs", StringUtils.formatData(service.getFfmrCs(request,myForm)));
		return mapping.findForward("xgXxgl");
	}
	/**
	 * @描述:查看学生献血信息
	 * @作者：zhangjw
	 * @日期：2013-4-18 下午04:26:01
	 * @修改记录:  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型 
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward ckXxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglForm myForm = (XsxxglForm) form;
		XsxxglService service = new XsxxglService();
		//设置学生基本信息
		szXsxx(request,myForm.getXh());
		String path = "rcsw_xsxxgl.do?method=ckXxgl";
		request.setAttribute("path", path);
		request.setAttribute("type", "update");
		XsxxglForm model = service.getModel(myForm);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("rs", StringUtils.formatData(service.getFfmrCs(request,myForm)));
		return mapping.findForward("ckXxgl");
	}
	/**
	 * @描述:删除学生献血信息
	 * @作者：zhangjw
	 * @日期：2013-4-18 下午04:27:15
	 * @修改记录:
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型 
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-学生献血管理-学生献血信息维护-删除VALUES:{values}")
	public ActionForward scXxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XsxxglService service = new XsxxglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
					: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
		
	}
	
	/**
	 * 学生献血信息维护自定义导出
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
	public ActionForward xsxxxxwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		XsxxglForm model = (XsxxglForm) form;
		XsxxglService service = new XsxxglService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
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
