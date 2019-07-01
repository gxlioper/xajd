/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午03:10:51 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsq;

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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.dagl.xzmzdx.cssz.DazccsszForm;
import com.zfsoft.xgxt.dagl.xzmzdx.cssz.DazccsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 档案转出-申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午03:13:09 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazcsqAction extends SuperAction<DazcsqForm,DazcsqService>{
	private final String url = "xsxx_dagl_dazcsq.do";
	private DazcsqService service = new DazcsqService();
	
	private final static String DAZC = "dazc";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*学生基本信息显示配置*/
		jbxxList = bdpzService.getJbxxpz(DAZC);
	}
	
	/**
	 * @描述: 返回查询页面
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-8 上午11:47:49
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
	@SystemLog(description = "访问学生信息-档案管理-档案转出申请-查询页面")
	public ActionForward getDazcsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*参数设置信息*/
		DazccsszService dazccsszService = new DazccsszService();
		DazccsszForm dazccsszForm = dazccsszService.getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		/*取审核流程*/
		String splc = service.getSplc().get("splc");
		request.setAttribute("splc", splc);
		
		/*返回path*/
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dazcsqList");
	}
	
	/**
	 * @描述: 档案转出申请返回Json数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-8 下午02:09:35
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
	@SystemLog(description = "访问学生信息-档案转出管理-档案转出申请-查询数据")
	public ActionForward dazcsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcsqForm model = (DazcsqForm)form;
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//查询
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述: 申请页面
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-8 下午03:52:29
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
	@SystemLog(description = "访问学生信息-档案转出管理-档案转出申请-申请")
	public ActionForward dazcsqApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcsqForm model = (DazcsqForm)form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			/*加载学生基本信息*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		/*取系统当前时间，格式：2018-01-01*/
		String DATE_FORMAT = "yyyy-MM-dd";
		String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
		request.setAttribute("maxtime", maxtime);
		
		/*参数设置信息*/
		DazccsszForm dazccsszForm = new DazccsszService().getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		/*学生基本信息显示配置*/
		request.setAttribute("jbxxList", jbxxList);
		
		String path = "dagl_dazcsq.do?method=dazcsqApply";
		request.setAttribute("path", path);
		
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("dazcsqApply");
	}
	
	/**
	 * @描述: 修改
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午03:36:49
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
	@SystemLog(description = "访问学生信息-档案转出管理-档案转出申请-修改")
	public ActionForward dazcsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcsqForm dazcsqForm = (DazcsqForm)form;
		DazcsqForm model = service.getModel(dazcsqForm);
		
		if(model != null){
			BeanUtils.copyProperties(dazcsqForm, StringUtils.formatData(model));
			
			if (!StringUtil.isNull(model.getXh())){
				/*加载学生基本信息*/
				XsxxService xsxxService = new XsxxService();
				HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
				request.setAttribute("jbxx", xsjbxx);
			}
			
			/*取系统当前时间，格式：2018-01-01*/
			String DATE_FORMAT = "yyyy-MM-dd";
			String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
			request.setAttribute("maxtime", maxtime);
			
			/*参数设置信息*/
			DazccsszForm dazccsszForm = new DazccsszService().getModel();
			request.setAttribute("dazccsszForm", dazccsszForm);
			
			/*学生基本信息显示配置*/
			request.setAttribute("jbxxList", jbxxList);
			
			String path = "dagl_dazcsq.do?method=dazcsqUpdate";
			request.setAttribute("path", path);
			
			/*返回转出方式*/
			request.setAttribute("zcfs", dazcsqForm.getZcfs());
		}
		return mapping.findForward("dazcsqUpdate");
	}
	
	/**
	 * @描述: 保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午01:49:06
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
	@SystemLog(description="访问学生信息-档案转出管理-档案转出申请-保存:学号:{xh}")
	public ActionForward dazcsqApplySave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcsqForm  model = (DazcsqForm)form;
		/**获取用户*/
		User user = getUser(request);
		boolean rs = true;
		try{
			rs = service.saveFormDazcsq(model,user);
		}catch(SystemException e){
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 查看
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午05:42:34
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
	@SystemLog(description = "访问学生信息-档案转出管理-档案转出申请-查看")
	public ActionForward dazcsqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcsqForm myForm = (DazcsqForm)form;
		DazcsqForm model = service.getModel(myForm);
		
		if(null != model){
			/*加载学生基本信息*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		/*学生基本信息显示配置*/
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(DAZC);
		request.setAttribute("jbxxList", jbxxList);
		
		/*根据申请ID获得学生申请信息*/
		HashMap<String,String> xxckList = service.getInfoBySqid(model.getSqid());
		request.setAttribute("rs", xxckList);
		
		/*参数设置信息*/
		DazccsszForm dazccsszForm = new DazccsszService().getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		return mapping.findForward("dazcsqView");
	}
	
	/**
	 * @描述: 删除
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2018-5-9 下午02:24:55
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
	@SystemLog(description="访问学生信息-档案转出管理-档案转出申请-删除VALUES：{values}")
	public ActionForward dazcsqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		String values = request.getParameter("values");
		if(!StringUtil.isNull(values)){
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				/*存在这种情况，当数据多级退回时，用户修改 已退回数据并做保存草稿操作，然后进行删除，这样xg_xtwh_shztb中就产生了垃圾数据*/
				service.delShztbData(ids);
			}
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @描述: 提交
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午02:33:50
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
	@SystemLog(description="访问学生信息-档案转出管理-档案转出申请-提交 VALUES：{values}")
	public ActionForward dazcsqSubmit (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String values = request.getParameter("values");
		if(!StringUtil.isNull(values)){
			boolean result = false;
			int okNum = 0;
			//根据ID查询学生申报项目的信息
			List<HashMap<String,String>> dataList = service.getInfoBySqid(values.split(","));
			for(int i = 0; i < dataList.size(); i++){
				HashMap<String,String> dataMap = dataList.get(i);
				String sqid = dataMap.get("sqid");
				String splcid = dataMap.get("splcid");
				String xh = dataMap.get("xh");
				result = service.plSubmit(sqid,splcid,xh);
				if (result) {
					okNum++;
				}
			}
			String resultMsg = "提交成功"+okNum+"条！";
			response.getWriter().print(getJsonMessage(resultMsg));
		}
		return null;
	}
	
	/**
	 * @描述: 撤销
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午02:43:43
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
	@SystemLog(description="访问学生信息-档案转出管理-档案转出申请-撤销 VALUES：{values}")
	public ActionForward dazcsqRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*获取勾选值【申请id、流程id】*/
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		
		boolean result = true;
		
		/*只有第一级未审核的未提交状态数据，申请人可以撤销*/
		result = service.cancelRecord(sqid, lcid);
		if(result){
			DazcsqForm model = new DazcsqForm();
			model.setSqid(sqid);
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-9 下午02:49:07
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
	@SystemLog(description="访问学生信息-档案转出管理-档案转出申请-导出")
	public ActionForward dazcsqExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		DazcsqForm model = new DazcsqForm();
		/*生成高级查询对象*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		/*查询出所有记录，不分页*/
		List<HashMap<String, String>> resultList = service.getAllList(model,user);

		/*导出功能代码*/
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		/*当前操作员*/
		exportModel.setZgh(user.getUserName());
		/*设置数据*/
		exportModel.setDataList(resultList);
		/*设置当前导出功能编号*/
		exportModel.setDcclbh(request.getParameter("dcclbh"));
		/*生成导出文件*/
		File file = exportService.getExportFile(exportModel);
		FileUtil.outputExcel(response, file);
		return null;
	}

}
