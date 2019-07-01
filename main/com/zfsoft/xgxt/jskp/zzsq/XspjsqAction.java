/**
 * @部门:学工产品(1)部
 * @日期：2018-4-11 上午09:09:20 
 */  
package com.zfsoft.xgxt.jskp.zzsq;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
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
import com.zfsoft.xgxt.jskp.dmwh.DmwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学习评价管理模块
 * @类功能描述: 自主申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-11 上午09:09:20 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XspjsqAction extends SuperAction<XspjsqForm,XspjsqService>{
	private final String url = "xspj_xspj_xspjsq.do";
	private final String XSPJ = "xspj";
	private XspjsqService service = new XspjsqService();
	public static final String DRCGBZ = "导入成功！";
	public static final String DRSBBZ = "导入失败,请仔细核对【错误数据.xls】！";
	public static final String KBG = "空excel表格！";
	public static final String KFILE = "未上传文件！";
	public static final String EXCELREPEAT = "Excle中存在重复数据(参与人(学号)、项目名称、参与时间重复)，请仔细核对！";
	
	/**
	 * @描述: 查询页面
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-11 上午10:30:46
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
	@SystemLog(description = "访问学习评价-自主申请")
	public ActionForward getXspjsqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		User user = getUser(request);
		//只有2017级及以后的学生才可以申请
		boolean checkStuNj = service.getCheckStuNj(user);
		if(!checkStuNj){
			String msg = "该模块仅允许2017级及以后的学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//返回URL
		request.setAttribute("path", url);
		//取审批流程,用于控制没设置审核流程时会显示错误信息
		String splc = service.getSplcForParam("sb").get("splc");
		request.setAttribute("splc", splc);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xspjsqList");
	}
	
	/**
	 * @描述: 返回json数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-11 上午11:58:00
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
	@SystemLog(description = "访问学习评价-查询自主申请数据")
	public ActionForward seachForXspjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		XspjsqForm model = (XspjsqForm)form;
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
	 * @描述: 申请
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-12 上午10:48:51
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
	@SystemLog(description = "访问学习评价-操作申请按钮")
	public ActionForward xspjsqApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*取系统当前时间，格式：2018-01-01*/
		String DATE_FORMAT = "yyyy-MM-dd";
		String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
		request.setAttribute("maxtime", maxtime);
		
		/*获取用户信息*/
		User user = getUser(request);
		request.setAttribute("userName", user.getUserName());
		
		/*获取项目类别信息List(只限于能力素养)*/
		DmwhService dmwhService = new DmwhService();
		List<HashMap<String,String>> xmlbList = dmwhService.getXmlbListByNlsy();
		request.setAttribute("xmlbList", xmlbList);
		
		/*获取短学期信息List*/
		List<HashMap<String,String>> dxqList = service.getDxqInfoList();
		request.setAttribute("dxqList", dxqList);
		
		/*学年list*/
		List<HashMap<String,String>> xnList = Base.getXnndList();
		request.setAttribute("xnList", xnList);
		return mapping.findForward("xspjsqApply");
	}
	
	/**
	 * @描述: 申请保存(保存草稿、提交申请)
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-12 下午05:26:25
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
	@SystemLog(description="访问学习评价-自主申请-保存:学号:{xh},项目名称:{xmmc},参与时间:{cysj}")
	public ActionForward xspjsqApplySave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjsqForm model = (XspjsqForm)form;
		/**获取用户*/
		User user = getUser(request);
		
		boolean rs = true;
		try{
			rs = service.saveFormXspjsq(model,user);
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
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-13 上午09:41:36
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
	@SystemLog(description="访问学习评价-自主申请-删除VALUES：{values}")
	public ActionForward xspjsqDelete(ActionMapping mapping, ActionForm form,
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
	 * @描述: 修改
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-13 下午04:19:46
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
	@SystemLog(description = "访问学习评价-操作修改按钮")
	public ActionForward xspjsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjsqForm xspjsqForm = (XspjsqForm)form;
		XspjsqForm model = service.getModel(xspjsqForm);
		
		if(model != null){
			BeanUtils.copyProperties(xspjsqForm, StringUtils.formatData(model));
			/*取系统当前时间，格式：2018-01-01*/
			String DATE_FORMAT = "yyyy-MM-dd";
			String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
			request.setAttribute("maxtime", maxtime);
			
			/*获取用户信息*/
			User user = getUser(request);
			request.setAttribute("userName", user.getUserName());
			
			/*获取项目类别信息List(只限于能力素养)*/
			DmwhService dmwhService = new DmwhService();
			List<HashMap<String,String>> xmlbList = dmwhService.getXmlbListByNlsy();
			request.setAttribute("xmlbList", xmlbList);
			
			/*获取短学期信息List*/
			List<HashMap<String,String>> dxqList = service.getDxqInfoList();
			request.setAttribute("dxqList", dxqList);
			
			/*学年list*/
			List<HashMap<String,String>> xnList = Base.getXnndList();
			request.setAttribute("xnList", xnList);
		}
		
		/*根据指导部门代码获得指导部门名称*/
		String bmmc = service.getBmmcByZdbmdm(model.getZdbmdm());
		request.setAttribute("bmmc", bmmc);
		
		return mapping.findForward("xspjsqUpdate");
	}
	
	/**
	 * @描述: 查看
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-16 下午02:26:21
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
	@SystemLog(description = "访问学习评价-查看")
	public ActionForward xspjsqView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjsqForm myForm = (XspjsqForm)form;
		XspjsqForm model = service.getModel(myForm);
		
		if(null != model){
			/*加载学生基本信息*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		/*学生基本信息显示配置*/
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XSPJ);
		request.setAttribute("jbxxList", jbxxList);
		
		/*根据申请ID获得学生申请信息*/
		HashMap<String,String> xxckList = service.getInfoBySqid(model.getSqid());
		request.setAttribute("rs", xxckList);
		return mapping.findForward("xspjsqView");
	}
	
	
	/**
	 * @描述: 批量提交
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-13 上午11:58:20
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
	@SystemLog(description="访问学习评价-自主申请-提交 VALUES：{values}")
	public ActionForward xspjsqSubmit (ActionMapping mapping, ActionForm form,
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
				String fzr = dataMap.get("xh");
				result = service.plSubmit(sqid,splcid,fzr);
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
	 * @日期：2018-4-13 上午11:12:53
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
	@SystemLog(description="访问学习评价-自主申请-撤销 VALUES：{values}")
	public ActionForward xspjsqRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		/*获取勾选值【申请id、流程id】*/
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		
		boolean result = true;
		
		/*只有第一级未审核的未提交状态数据，申请人可以撤销*/
		result = service.cancelRecord(sqid, lcid);
		if(result){
			XspjsqForm model = new XspjsqForm();
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
	 * @日期： 2018-4-16 上午10:23:23
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
	@SystemLog(description="访问学习评价-自主申请-导出")
	public ActionForward xspjsqExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		XspjsqForm model = new XspjsqForm();
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
	
	/**
	 * @描述: 学生评价申请信息导入
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-21 上午11:10:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生评价-学生评价-自主申请-导入页面")
	public ActionForward xspjsqImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("xspjsqImport");
	}
	
	/**
	 * @描述: 下载导入模板
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-22 上午08:49:44
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
	@SystemLog(description = "访问学生评价-学生评价-自主申请-下载导入模板")
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""+ new String("xspjsq_dataImport.xls".getBytes(), "GBK") + "\"");
		service.createWwb(response.getOutputStream());
		return null;
	}
	
	/**
	 * @描述: 数据导入
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-22 上午09:35:48
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
	@SystemLog(description = "访问学生评价-学生评价-自主申请-Excle数据导入")
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		XspjsqForm model = (XspjsqForm)form;
		/**request里获取用户*/
		User user = getUser(request);
		/*操作人工号*/
		model.setSjlrr(user.getUserName());
		/*数据录入时间*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
		FormFile file = model.getDrmb();
		if(file != null){
			try{
				model.setFilepath(servlet.getServletContext().getRealPath("/temp/importTemp/")+"/");
				HashMap<String,Object> resultMap = service.saveDrExcelInfo(file.getInputStream(),model,user);
				String message = DRCGBZ;
				if(resultMap.get("result").equals("true")){
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if(resultMap.get("result").equals("null")){
					 message = KBG;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if("excelrepeat".equals(resultMap.get("result"))){
					 message = EXCELREPEAT;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else{
				    message = DRSBBZ;
				    Map<String,String> map = new HashMap<String, String>();
					map.put("message", message);
					map.put("gid", (String)resultMap.get("gid"));
					JSONObject json = JSONObject.fromObject(map); 
				    response.getWriter().print(json);
					return null;
				}
			}catch (FileNotFoundException e) {
				// TODO 自动生成 catch 块
				logger.info("导入文件未找到！");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				logger.info("IO异常！");
				e.printStackTrace();
			}
		}else{
			String message = KFILE;
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}
		return null;
	}
	
	/**
	 * @描述: 错误数据下载
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-22 下午01:58:33
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
	@SystemLog(description = "访问学生评价-学生评价-自主申请-错误数据下载")
	public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//得到tomcat/webapp/temp/importTemp下错误文件的路径
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/")+"/"+filename;
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("错误数据.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
}
