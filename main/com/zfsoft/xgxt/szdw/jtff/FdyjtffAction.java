/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:07:43 
 */  
package com.zfsoft.xgxt.szdw.jtff;

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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(辅导员津贴发放--山东潍坊) 
 * @作者： cmj [工号：913]
 * @时间： 2013-8-5 上午11:07:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdyjtffAction extends SuperAction {

	private static final String url = "szdw_fdyjtff.do?method=cxFdyjtffList";
	
	@SystemAuth(url = url)
	public ActionForward cxFdyjtffList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
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
		
		String path = "szdw_fdyjtff.do?method=cxFdyjtffList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxFdyjtffList");
	}
	/**
	 * 
	 * @描述:TODO(增加辅导员津贴发放)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-5 下午01:51:46
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
	@SystemLog(description = "访问思政队伍-津贴发放-津贴发放-增加ZGH:{zgh},BZLX:{bzlx},XN:{xn},XQ:{xq},BZJE:{bzje}")
	public ActionForward addFdyjtff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
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
		if (!StringUtil.isNull(model.getZgh())){
			//加载f辅导员基本信息
			HashMap<String,String> jbxx = service.getFdyjbxx(model.getZgh());
			request.setAttribute("jbxx", jbxx);
		
		}
		if (SAVE.equalsIgnoreCase(model.getType())){
			//等级代码是否存在
			boolean isExist=service.isExist(model);
			if(!isExist){
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.SZDW_REPEAT_ERROR));
				return null;
				
			}
			
		}
		String path = "szdw_fdyjtff.do?method=addFdyjtff";
		request.setAttribute("path", path);
		return mapping.findForward("addFdyjtff");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-津贴发放-津贴发放-修改ID:{id},ZGH:{zgh},BZLX:{bzlx},XN:{xn},XQ:{xq},BZJE:{bzje}")
	public ActionForward updateFdyjtff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyjtffForm myForm = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			// 潍坊学院 则不清空
			if(!"11067".equals(Base.xxdm) && "0".equalsIgnoreCase(myForm.getBzlx())){
				myForm.setKpdj("");
			}
			boolean result = service.runUpdate(myForm);
			String message = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
			return null;
		}
		FdyjtffForm model=service.getModel(myForm);
		if (!StringUtil.isNull(model.getZgh())){
			//加载f辅导员基本信息
			HashMap<String,String> jbxx = service.getFdyjbxx(model.getZgh());
			request.setAttribute("jbxx", jbxx);
			
		}
		BeanUtils.copyProperties(myForm, model);
		List<HashMap<String, String>> xnList=Base.getXnndList();
		List<HashMap<String, String>> xqList=Base.getXqList();
		request.setAttribute("xnList", xnList);
		request.setAttribute("xqList", xqList);
		return mapping.findForward("updateFdyjtff");
		
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-津贴发放-津贴发放-删除ID:{values}")
	public ActionForward delFdyjtff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyjtffService service = new FdyjtffService();
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
	
	@SystemAuth(url = url)
	public ActionForward viewFdyjtff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyjtffForm myForm = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		FdyjtffForm model=service.getModel(myForm);
		if (!StringUtil.isNull(model.getZgh())){
			//加载f辅导员基本信息
			HashMap<String,String> jbxx = service.getFdyjbxx(model.getZgh());
			request.setAttribute("jbxx", jbxx);
			
		}
		BeanUtils.copyProperties(myForm, model);
		
		return mapping.findForward("viewFdyjtff");
		
	}
	
	
	/**
	 * 
	 * @描述:TODO(获取辅导员list)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-5 下午02:44:45
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
	
	public ActionForward showFdys(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getFdyPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String gotoPath = request.getParameter("goto");
		
		String path = "szdw_fdyjtff.do?method=showFdys";
		request.setAttribute("path", path);
		request.setAttribute("gotoPath", gotoPath);
		request.setAttribute("fdyjtfflx", model.getFdyjtfflx());
		return mapping.findForward("showFdys");
	}
	
	/**
	 * 
	 * @描述:获取辅导员（不刷新父页面）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 上午08:46:34
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
	
	public ActionForward showFdysNotF5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getFdyPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "szdw_fdyjtff.do?method=showFdys";
		request.setAttribute("path", path);
		return mapping.findForward("showFdysNotF5");
	}
	
	public ActionForward showFdysAnother(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getFdyPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "szdw_fdyjtff.do?method=showFdys";
		request.setAttribute("path", path);
		return mapping.findForward("showFdysAnother");
	}
	
	/**
	 * 
	 * @描述:获取辅导员【寝室导师】（不刷新父页面）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 上午08:46:34
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
	
	public ActionForward showFdysQsdsNotF5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getFdyQsdsPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "szdw_fdyjtff.do?method=showFdys";
		request.setAttribute("path", path);
		return mapping.findForward("showFdysQsdsNotF5");
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
		FdyjtffForm model = (FdyjtffForm) form;
		FdyjtffService service = new FdyjtffService();

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
