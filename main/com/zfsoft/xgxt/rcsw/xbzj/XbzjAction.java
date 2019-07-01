/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 下午05:15:46 
 */  
package com.zfsoft.xgxt.rcsw.xbzj;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(学生系部支教管理--潍坊学院) 
 * @作者： cmj [工号：913]
 * @时间： 2013-8-5 下午05:15:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XbzjAction extends SuperAction {
	
	private static final String url = "rcsw_xbzj.do?method=cxXbzjList";
	
	@SystemAuth(url = url)
	public ActionForward cxXbzjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XbzjService service=new XbzjService();
		XbzjForm model=(XbzjForm) form;
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("realTable", "rcsw_xbzj");
		String path = "rcsw_xbzj.do?method=cxXbzjList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxXbzjList");
	}
	
	
	/**
	 * 
	 * @描述:(增加支教信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-8 上午09:03:31
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
	@SystemLog(description="访问日常事务-系部支教-系部支教-增加")
	public ActionForward addXbzjxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XbzjService service=new XbzjService();
		XbzjForm model=(XbzjForm) form;
		
		HashMap<String, String> map=new HashMap<String, String>();
		String currxn=Base.currXn;
		String currxq=Base.currXq;
		map.put("xn", currxn);
		map.put("xq", currxq);
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		request.setAttribute("map", map);
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		
		}
		if (SAVE.equals(model.getType())){
			boolean isExist=service.isExist(model);
			if(!isExist){
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_REPEAT_ERROR));
				return null;
			}
		}
		String path = "rcsw_xbzj.do?method=addXbzjxs";
		request.setAttribute("path", path);
		return mapping.findForward("addXbzjxs");
	}
	/**
	 * 
	 * @描述:(修改支教信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-8 上午09:02:44
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
	@SystemLog(description="访问日常事务-系部支教-系部支教-修改ID:{id}")
	public ActionForward updateXbzjxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XbzjService service=new XbzjService();
		XbzjForm model=(XbzjForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())){

			boolean result = service.runUpdate(model);
			String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
		XbzjForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		
		}
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateXbzjxs");
	}
	/**
	 * 
	 * @描述:(查看学生支教信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-8 上午09:02:09
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
	public ActionForward viewXbzjxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XbzjService service=new XbzjService();
		XbzjForm model=(XbzjForm) form;
		
		XbzjForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("viewXbzjxs");
		
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-系部支教-系部支教-删除VALUES:{values}")
	public ActionForward delXbzjxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XbzjService service=new XbzjService();
		String values = request.getParameter("values");
		
		int num = service.runDelete(values.split(","));
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
								: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述:导出功能
	 * @作者：cmj
	 * @日期：2013-5-22 上午10:44:47
	 * @修改记录: 
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
		XbzjService service=new XbzjService();
		XbzjForm model=(XbzjForm) form;

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
