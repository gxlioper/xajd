/**
 * @部门:学工产品事业部
 * @日期：2014-3-19 上午09:42:28 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl;

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
import xsgzgl.gygl.gyglry.GyglryService;
import xsgzgl.gygl.qsgl.QsglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.Globals;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述: 寝室导师维护
 * @作者： 张小彬[工号:1036]
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-19 上午09:42:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QsdswhAction extends SuperAction {
	
	private static final String url = "gygl_qsdswhgl.do";
	
	/**
	 * 
	 * @描述:公寓管理查询列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-19 上午10:41:15
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
	public ActionForward qsdswhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
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
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_qsdswhgl.do");
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qsdswhManage");
	}
	
	/**
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-19 下午04:15:05
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
	public ActionForward qsdswhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsglService qsglService = new QsglService();
		//寝室信息
		request.setAttribute("ldList", qsglService.getLdList());
		return mapping.findForward("qsdswhAdd");
	}
	
	/**
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-19 下午04:15:05
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
	public ActionForward qsdswhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
		String lddm = model.getLddm();
		String qsh = model.getQsh();
		
		if(StringUtils.isNotBlank(lddm) && StringUtils.isNotBlank(qsh)){

			HashMap<String ,String> data = service.getQsdsxx(model);
			request.setAttribute("qsdsxx", data);
			GyglryService gyglryService = new GyglryService();
			HashMap<String ,String> qszInfo = gyglryService.getQszInfo(lddm, qsh);
			request.setAttribute("qszInfo", xgxt.utils.String.StringUtils.formatData(qszInfo));
		}
		return mapping.findForward("qsdswhUpdate");
	}
	
	
	/**
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-19 下午04:15:05
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
	@SystemLog(description="访问公寓管理-寝室导师管理-寝室导师维护-增加LDDM:{lddm},QSH:{qsh},ZGH:{zgh}")
	public ActionForward qsdswhAddAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
		String lddm = model.getLddm();
		String qsh = model.getQsh();
		String zgh = model.getZgh();
		
		if(StringUtils.isNotBlank(lddm) && StringUtils.isNotBlank(qsh) && StringUtils.isNotBlank(zgh)){
			boolean isSuccess = service.saveQsdsxx(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);	
		}
		return null;
	}
		
	
	/**
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-19 下午04:15:05
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
	public ActionForward qsdsIsExist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
		boolean isExist = service.isExistLddm(model);
		if(isExist) {
			String messageKey = MessageKey.GYGL_QSDSWH_FALL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
		}
		return null;
	}
	
	/**
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-19 下午04:15:05
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
	@SystemLog(description="访问公寓管理-寝室导师管理-寝室导师维护-修改LDDM:{lddm},QSH:{qsh},ZGH:{zgh}")
	public ActionForward qsdswhUpdateAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
		String lddm = model.getLddm();
		String qsh = model.getQsh();
		String zgh = model.getZgh();
		
		if(StringUtils.isNotBlank(lddm) && StringUtils.isNotBlank(qsh) && StringUtils.isNotBlank(zgh)){
			boolean isSuccess = service.saveQsdsxx(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);	
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:删除
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-21 下午05:08:29
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
	@SystemLog(description="访问公寓管理-寝室导师管理-寝室导师维护-删除VALUES:{pks}")
	public ActionForward qsdswhDeleteAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QsdswhService service  = new QsdswhService();
		
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
	 * 查询寝室信息
	 */
	@SystemAuth(url = url)
	public ActionForward qsdswhQsxxView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		throws Exception{
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		QsdswhService service  = new QsdswhService();
		if(Globals.XXDM_WZDX.equalsIgnoreCase(Base.xxdm)){
			List<HashMap<String, String>> rsList = service.getxsxx(lddm, qsh);
			request.setAttribute("rsList", rsList);
			request.setAttribute("qsh", qsh);
			return mapping.findForward("qsdswhQsxxViewForWzdx");
		}else {
			List<String[]> rsList=service.getQsxxList(lddm, qsh);
			request.setAttribute("rsList", rsList);
			request.setAttribute("topTr", service.getQsxxTopTr());
			
			FormModleCommon.commonRequestSet(request);
					
			return mapping.findForward("qsdswhQsxxView");
		}
		
	}
	
	
	/**
	 * 
	 * @描述:导出
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-21 下午05:13:15
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
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
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
	
	/**
	 * @描述:导师考核导出
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
	public ActionForward exportDskh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QsdswhForm model = (QsdswhForm) form;
		QsdswhService service  = new QsdswhService();
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+new String("导师考核".getBytes("gb2312"),"iso-8859-1")+".xls");
		
		service.exportDskh(model, user, response.getOutputStream());
		return null;
	}
	
}
