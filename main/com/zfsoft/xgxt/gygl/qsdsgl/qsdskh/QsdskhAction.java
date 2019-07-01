/**
 * @部门:学工产品事业部
 * @日期：2014-11-26 下午04:34:40 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl.qsdskh;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @类功能描述: 寝室导师考核
 * @作者： 江水才[工号：1150]
 * @时间： 2014-11-26 下午04:34:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QsdskhAction extends SuperAction {
	
	private static final String url = "gygl_qsdskhgl.do";

	/**
	 * @描述:寝室导师考核查询列表
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
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
	public ActionForward qsdskhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdskhForm model = (QsdskhForm) form;
		QsdskhService service  = new QsdskhService();
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equalsIgnoreCase(model.getType())){
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
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		if(!StringUtil.isNull(Base.currXq)){
			searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		}
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_qsdskhgl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qsdskhManage");
	}
	
	/**
	 * 寝室导师考核增加页面
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
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
	public ActionForward qsdskhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		return mapping.findForward("qsdskhAdd");
	}
	
	/**
	 * 寝室导师考核修改页面
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
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
	public ActionForward qsdskhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdskhForm model = (QsdskhForm) form;
		QsdskhService service  = new QsdskhService();
		HashMap<String ,String> data = service.getQsdskh(model);
		request.setAttribute("qsdskh", xgxt.utils.String.StringUtils.formatData(data));
		return mapping.findForward("qsdskhUpdate");
	}
	
	/**
	 * 寝室导师考核保存（检查）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
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
	public ActionForward qsdskhAddCheckAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdskhForm model = (QsdskhForm) form;
		QsdskhService service  = new QsdskhService();
		
		boolean isSuccess = service.qsdskhAddCheck(model);
		String messageKey = isSuccess ? "该学年学期重复考核！" : "";
		JSONObject message = getJsonMessage(messageKey);
		response.getWriter().print(message);	
		return null;
	}
	
	/**
	 * 寝室导师考核保存
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
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
	@SystemLog(description="访问公寓管理-寝室导师管理-寝室导师考核-增加ZGH:{zgh},ND:{nd},XN:{xn},XQ:{xq},:CJ:{cj}")
	public ActionForward qsdskhAddAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QsdskhForm model = (QsdskhForm) form;
		QsdskhService service  = new QsdskhService();
		
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);	
		return null;
	}
	
	/**
	 * 寝室导师考核修改保存
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
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
	@SystemLog(description="访问公寓管理-寝室导师管理-寝室导师考核-修改ZGH:{zgh},,ND:{nd},XN:{xn},XQ:{xq},:CJ:{cj}")
	public ActionForward qsdskhUpdateAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdskhForm model = (QsdskhForm) form;
		QsdskhService service  = new QsdskhService();
		
		boolean isSuccess = service.updateQsdskh(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		JSONObject message = getJsonMessageByKey(messageKey);
		response.getWriter().print(message);	
		return null;
	}
	
	/**
	 * @描述:删除
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
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
	@SystemLog(description="访问公寓管理-寝室导师管理-寝室导师考核-删除PK:{pks}")
	public ActionForward qsdskhDeleteAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdskhService service  = new QsdskhService();
		
		String pks = request.getParameter("pks");
		
		if(StringUtils.isNotBlank(pks)){
			String[] pkArr = pks.split(",");
			List<String[]> pkList = new ArrayList<String[]>();
			for (String string : pkArr) {
				pkList.add(string.split("@@"));
			}
			service.deleteDsdsxxPl(pkList);
			String messageKey =MessageKey.SYS_DEL_SUCCESS;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);	
		}
		return null;
	}
	
	
	/**
	 * 
	 * @描述:导出
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 下午04:34:40 
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
		QsdskhForm model = (QsdskhForm) form;
		QsdskhService service  = new QsdskhService();
		
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
