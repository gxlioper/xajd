/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午02:53:25 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcjg;

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
 * @类功能描述: 档案转出-结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午02:54:21 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazcjgAction extends SuperAction<DazcjgForm,DazcjgService>{
	private final String url = "xsxx_dagl_dazcjg.do";
	private DazcjgService service = new DazcjgService();
	private final static String DAZC = "dazc";
	private static List<HashMap<String, String>> jbxxList = null;
	static{
		BdpzService bdpzService = new BdpzService();
		/*学生基本信息显示配置*/
		jbxxList = bdpzService.getJbxxpz(DAZC);
	}
	
	/**
	 * @描述: 返回查询列表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午02:30:25
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
	@SystemLog(description = "访问学生信息-档案管理-档案转出结果-查询页面")
	public ActionForward getDazcjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*参数设置信息*/
		DazccsszService dazccsszService = new DazccsszService();
		DazccsszForm dazccsszForm = dazccsszService.getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		/*返回path*/
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dazcjgList");
	}
	
	/**
	 * @描述: 档案转出结果返回Json数据
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午02:38:33
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
	@SystemLog(description = "访问学生信息-档案转出管理-档案转出结果-查询数据")
	public ActionForward dazcjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm model = (DazcjgForm)form;
		
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
	 * @描述: 增加
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午02:53:43
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
	@SystemLog(description = "访问学生信息-档案转出管理-档案转出结果-增加")
	public ActionForward dazcjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm model = (DazcjgForm)form;
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
		
		String path = "dagl_dazcjg.do?method=dazcjgAdd";
		request.setAttribute("path", path);
		
		BeanUtils.copyProperties(model, StringUtils.formatData(model));
		return mapping.findForward("dazcjgAdd");
	}
	
	/**
	 * @描述: 修改
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午05:06:26
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
	@SystemLog(description = "访问学生信息-档案转出管理-档案转出结果-修改")
	public ActionForward dazcjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm dazcjgForm = (DazcjgForm)form;
		DazcjgForm model = service.getModel(dazcjgForm);
		if(model != null){
			BeanUtils.copyProperties(dazcjgForm, StringUtils.formatData(model));
			
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
			request.setAttribute("zcfs", dazcjgForm.getZcfs());
			
			/*返回邮寄状态*/
			String yjzt = null;
			if(StringUtils.isNull(dazcjgForm.getYjzt())){
				/*这个5其实没什么作用
				 *当转出方式为自带时，jsp上的var yjzt = ${yjzt};取值会为空，会导致修改出问题，所以写了个值*/
				yjzt = "5";
			}else{
				yjzt = dazcjgForm.getYjzt();
			}
			request.setAttribute("yjzt", yjzt);
		}
		return mapping.findForward("dazcjgUpdate");
	}
	
	/**
	 * @描述: 档案转出结果保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午04:00:03
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
	@SystemLog(description="访问学生信息-档案转出管理-档案转出结果-保存:学号:{xh},转出方式:{zcfs}")
	public ActionForward dazcjgSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm model = (DazcjgForm)form;
		/**获取用户*/
		User user = getUser(request);
		
		boolean rs = true;
		try{
			rs = service.saveFormDazcjg(model,user);
		}catch(SystemException e){
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 删除
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午05:05:07
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
	@SystemLog(description="访问学生信息-档案转出管理-档案转出结果-删除-VALUES:{values}")
	public ActionForward dazcjgDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");

		if(!StringUtil.isNull(values)){
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * @描述: 查看
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午05:19:05
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
	@SystemLog(description = "访问学生信息-档案转出管理-档案转出结果-查看")
	public ActionForward dazcjgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm myForm = (DazcjgForm)form;
		
		/*加载学生基本信息*/
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		/*根据结果ID获得学生申请信息*/
		HashMap<String,String> xxckList = service.getInfoByGuid(myForm.getXh());
		request.setAttribute("rs", xxckList);
		
		/*根据学号查看结果表是否有数据*/
		String countRs = service.getDazcjgRs(myForm.getXh());
		request.setAttribute("countRs", countRs);
		
		/*参数设置信息*/
		DazccsszForm dazccsszForm = new DazccsszService().getModel();
		request.setAttribute("dazccsszForm", dazccsszForm);
		
		return mapping.findForward("dazcjgView");
	}
	
	/**
	 * @描述: 导出
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-10 下午05:08:55
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
	@SystemLog(description="访问学生信息-档案转出管理-档案转出结果-导出")
	public ActionForward dazcjgExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		DazcjgForm model = (DazcjgForm)form;
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
