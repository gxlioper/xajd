/**
 * @部门:学工产品(1)部
 * @日期：2018-4-18 上午11:03:39 
 */  
package com.zfsoft.xgxt.jskp.pjjg;

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
import com.zfsoft.xgxt.jskp.dmwh.DmwhService;
import com.zfsoft.xgxt.jskp.zzsq.XspjsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生评价管理模块
 * @类功能描述: 学生评价结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-18 上午11:03:39 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XspjjgAction extends SuperAction<XspjjgForm,XspjjgService>{
	private final String url = "xspj_xspj_xspjjg.do";
	private final String XSPJ = "xspj";
	private XspjjgService service = new XspjjgService();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DRCGBZ = "导入成功！";
	public static final String DRSBBZ = "导入失败,请仔细核对【错误数据.xls】！";
	public static final String KBG = "空excel表格！";
	public static final String KFILE = "未上传文件！";
	public static final String EXCELREPEAT = "Excle中存在重复数据(参与人(学号)、项目名称、参与时间重复)，请仔细核对！";
	
	/**
	 * @描述: 查询页面
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-18 上午11:41:53
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
	@SystemLog(description = "访问学习评价-评价结果")
	public ActionForward getXspjjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		User user = getUser(request);
		//只有2017级及以后的学生才可以申请
		XspjsqService xspjsqService = new XspjsqService();
		boolean checkStuNj = xspjsqService.getCheckStuNj(user);
		if(!checkStuNj){
			String msg = "该模块仅允许2017级及以后的学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		//返回URL
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xspjjgList");
	}
	
	/**
	 * @描述: 返回json数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-19 上午10:49:33
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
	public ActionForward seachForXspjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		XspjjgForm model = (XspjjgForm)form;
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
	 * @描述: 个性化导入页面
	 * @作者：  Meng.Wei[工号：1186]
	 * @日期：  2018-4-19 下午05:13:09
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
	public ActionForward xspjjgImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("xspjjgImport");
	}
	
	/**
	 * @描述: 模板下载
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-20 上午08:54:55
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
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""+ new String("xspj_dataImport.xls".getBytes(), "GBK") + "\"");
		service.createWwb(response.getOutputStream());
		return null;
	}
	
	/**
	 * @描述: 导入保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-20 下午02:03:01
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
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		XspjjgForm model = (XspjjgForm)form;
		/**request里获取用户*/
		User user = getUser(request);
		/*操作人工号*/
		model.setSjlrr(user.getUserName());
		/*数据录入时间*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		/*数据来源【1:申请审核、2:结果增加、3:导入】*/
		model.setSjly("3");
		
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
	 * @日期： 2018-4-20 下午05:34:19
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
	
	/**
	 * @描述: 增加返回页面
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-23 下午07:42:08
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
	public ActionForward xspjjgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjsqService xspjsqService = new XspjsqService();
		
		/*取系统当前时间，格式：2018-01-01*/
		String DATE_FORMAT = "yyyy-MM-dd";
		String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
		request.setAttribute("maxtime", maxtime);
		
		/*获取用户信息*/
		User user = getUser(request);
		request.setAttribute("userName", user.getUserName());
		
		/*获取项目类别信息List(思想政治素质)*/
		DmwhService dmwhService = new DmwhService();
		List<HashMap<String,String>> xmlbList = dmwhService.getXmlbListBySzsz();
		request.setAttribute("xmlbList", xmlbList);
		
		/*获取短学期信息List*/
		List<HashMap<String,String>> dxqList = xspjsqService.getDxqInfoList();
		request.setAttribute("dxqList", dxqList);
		
		/*学年list*/
		List<HashMap<String,String>> xnList = Base.getXnndList();
		request.setAttribute("xnList", xnList);
		
		return mapping.findForward("xspjjgAdd");
	}
	
	/**
	 * @描述: 增加修改保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-24 上午09:44:03
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
	@SystemLog(description="访问学习评价-评价结果-保存:学号:{xh},项目名称:{xmmc},参与时间:{cysj}")
	public ActionForward xspjjgSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjjgForm model = (XspjjgForm)form;
		/**获取用户*/
		User user = getUser(request);
		
		boolean rs = true;
		try{
			rs = service.saveFormXspjjg(model,user);
		}catch(SystemException e){
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述: 修改
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-24 上午11:18:53
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
	@SystemLog(description = "访问评价结果-操作修改按钮")
	public ActionForward xspjjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjjgForm xspjjgForm = (XspjjgForm)form;
		XspjjgForm model = service.getModel(xspjjgForm);
		XspjsqService xspjsqService = new XspjsqService();
		
		if(model != null){
			BeanUtils.copyProperties(xspjjgForm, StringUtils.formatData(model));
			
			/*取系统当前时间，格式：2018-01-01*/
			String DATE_FORMAT = "yyyy-MM-dd";
			String maxtime = GetTime.getTimeByFormat(DATE_FORMAT);
			request.setAttribute("maxtime", maxtime);
			
			/*获取用户信息*/
			User user = getUser(request);
			request.setAttribute("userName", user.getUserName());
			
			/*获取项目类别信息List(只限思想政治素质)*/
			DmwhService dmwhService = new DmwhService();
			List<HashMap<String,String>> xmlbList = dmwhService.getXmlbListBySzsz();
			request.setAttribute("xmlbList", xmlbList);
			
			/*获取短学期信息List*/
			List<HashMap<String,String>> dxqList = xspjsqService.getDxqInfoList();
			request.setAttribute("dxqList", dxqList);
			
			/*学年list*/
			List<HashMap<String,String>> xnList = Base.getXnndList();
			request.setAttribute("xnList", xnList);
		}
		/*根据指导部门代码获得指导部门名称*/
		String bmmc = xspjsqService.getBmmcByZdbmdm(model.getZdbmdm());
		request.setAttribute("bmmc", bmmc);
		
		return mapping.findForward("xspjjgUpdate");
	}
	
	/**
	 * @描述: 删除
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-24 下午02:16:54
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
	@SystemLog(description="访问学生评价-评价结果-删除-VALUES:{values}")
	public ActionForward xspjjgDelete(ActionMapping mapping, ActionForm form,
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
	 * @描述: 导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-24 下午03:01:30
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
	@SystemLog(description="访问学习评价-评价结果-导出")
	public ActionForward xspjjgExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjjgForm model = (XspjjgForm)form;
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
	 * @描述: 查看
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-24 下午08:12:10
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
	@SystemLog(description = "访问评价结果-查看")
	public ActionForward xspjjgView (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		XspjjgForm myForm = (XspjjgForm)form;
		XspjjgForm model = service.getModel(myForm);
		
		if(null != model){
			/*加载学生基本信息*/
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			List<HashMap<String, String>> singleSummary = service.getSingleSummary(model.getXh());
			request.setAttribute("singleSummary", singleSummary);
		}
		
		/*学生基本信息显示配置*/
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XSPJ);
		request.setAttribute("jbxxList", jbxxList);
		
		/*根据申请ID获得学生申请信息*/
		HashMap<String,String> xxckList = service.getInfoByGuid(model.getGuid());
		request.setAttribute("rs", xxckList);
		return mapping.findForward("xspjjgView");
	}
	
	/**
	 * @描述: 评价结果评分统计
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-25 下午02:17:02
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
	public ActionForward dataStatistics(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XspjjgForm model = new XspjjgForm();
		
		/*生成高级查询对象	*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		User user = getUser(request);
		/*查询出所有记录，不分页*/
		List<HashMap<String,String>> resultList = service.getDateForSearchXn(model,user, xn);
		/*生成导出文件*/
		File file = service.getDataStatisticsFile(resultList,xn);
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
