/**
 * @部门:学工产品事业部
 * @日期：2016-6-20 上午10:07:42 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfdr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zjly.zhf.comm.CommUtil;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-6-20 上午10:07:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfDrAction extends SuperAction<ZhfDrForm, ZhfDrService> {
	private ZhfDrService service = new ZhfDrService();
//	private ZhfCsszService csszService = new ZhfCsszService();
	private static List<HashMap<String, String>> jbxxList = null;
	public static final String ZJLY_ZHF = "zjly_zhf";
	public static final String DRCGBZ = "导入成功！";
	public static final String DRSBBZ = "导入失败,请仔细核对【出错记录.xls】！";
	public static final String KBG = "空excel表格！";
	public static final String KFILE = "没有文件！";
	
	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(ZJLY_ZHF);
	}
	
	private static final String url = "xg_zjly_zhfdr.do";
	
	/**
	 * 
	 * @描述:查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-20 上午11:28:03
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
	public ActionForward getZhfdrCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfDrForm model = (ZhfDrForm) form;
		String lb = request.getParameter("lb");
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
			// 默认高级查询条件
			SearchModel searchModel = new SearchModel();
			request.setAttribute("searchTj", searchModel);
			if ("dr".equals(lb)) {
				request.setAttribute("path", url);
				FormModleCommon.commonRequestSet(request);
				return mapping.findForward("getZhfdrCx");
			}else if ("hzdc".equals(lb)) {
				request.setAttribute("path", "xg_zjly_hzbdc.do");
				FormModleCommon.commonRequestSet(request);
				return mapping.findForward("getHzxxdc");
			}else {
				request.setAttribute("path", "xg_zjly_xxsxdc.do");
				FormModleCommon.commonRequestSet(request);
				return mapping.findForward("getXxsxdc");
			}
	}
	
	/**
	 * 
	 * @描述:导入页面跳转
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-20 下午01:36:23
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
	public ActionForward importJfxmPrepare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		request.setAttribute("jsxx",service.getFdyxx(user.getUserName()));
		//计分大项
		request.setAttribute("dxlist", new CommUtil().getDxList(user.getUserDep(),user.getUserName()));
		//计分小项
		request.setAttribute("xxlist", new CommUtil().getXxList(user.getUserDep(),user.getUserName()));
		return mapping.findForward("drprepare");
	}
	
	/**
	 * 
	 * @描述:导出
	 * @作者：CP[工号：1352]
	 * @日期：2017-2-27 下午05:28:53
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
	public ActionForward exportJfxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			ZhfDrForm model = (ZhfDrForm) form;
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			File file = service.getZhfFile(model,user);
			//导出文件
			FileUtil.outputExcel(response, file);
		return null;
}
	/**
	 * 详细事项 合并分类导出
	 */
	public ActionForward exportXxsx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfDrForm model = (ZhfDrForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getXXsxList(model, user);
		File file = service.getZhfXxsx(resultList,model,user);
		//导出文件
		FileUtil.outputExcel(response, file);
	return null;
}	
	/**
	 * 
	 * @描述:汇总表 合并分类导出
	 * @作者：CP[工号：1352]
	 * @日期：2017-3-16 下午02:53:42
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
	public ActionForward exportHZsx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfDrForm model = (ZhfDrForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		List<HashMap<String,String>> resultList = service.getXXsxList(model, user);
		File file = service.getZhfHzsx(resultList,model,user);
		//导出文件
		FileUtil.outputExcel(response, file);
	return null;
}	
	/**
	 * 
	 * @描述:导入模板下载
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-20 下午02:11:25
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
			throws Exception {
		String path = request.getSession().getServletContext().getRealPath(
				"/temp/mb/")+"/zhfdrmb.xls";
        logger.info("path = "+path);
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("zhfdrmb.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @描述:导入保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-22 上午11:15:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		ZhfDrForm model = (ZhfDrForm) form;
		FormFile file = model.getDrmb();
		model.setUser(getUser(request));
		if(file != null){
			try {
				model.setFilepath(servlet.getServletContext().getRealPath(
				"/temp/importTemp/")+"/");
				HashMap<String,Object> resultMap = service.saveDrExcelInfo(file.getInputStream(),model);
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
				}
				else{
				    message = DRSBBZ;
				    Map<String,String> map = new HashMap<String, String>();
					map.put("message", message);
					map.put("gid", (String)resultMap.get("gid"));
					JSONObject json = JSONObject.fromObject(map); 
				    response.getWriter().print(json);
					return null;
				}
				
			} catch (FileNotFoundException e) {
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
	 * 
	 * @描述:下载错误数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-20 下午02:11:25
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
		String path = servlet.getServletContext().getRealPath(
		"/temp/importTemp/")+"/"+filename;
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
	 * 
	 * @描述: 删除
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-23 上午11:21:43
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
	public ActionForward delRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		User user  = getUser(request);
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			//添加删除日志
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			service.addlog(time,user,ids);
			int num = service.runDelete(ids);
			String message;
			boolean result = num > 0;
			if (result) {
				message = MessageUtil.getText(MessageKey.SYS_DEL_NUM, num);
			}else {
				message = MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				service.dellog(ids);
			}
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}  
	
	/**
	 * 
	 * @描述:修改导入记录，未审定的不能修改
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-23 下午02:01:41
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
	public ActionForward updateJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfDrForm myForm = (ZhfDrForm) form;
		ZhfDrForm model = service.getModel(myForm);
		User user = getUser(request);
		if(null!=model && !"save".equals(myForm.getType())){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if("save".equals(myForm.getType())){
//			myForm
			if(service.checkNotExists(myForm)){
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String message = "学号，计分项目代码，事项说明，参与时间不能重复！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		}
		request.setAttribute("jbxxList", jbxxList);
		String path = "zjly_zhfdr.do?method=updateJg";
		request.setAttribute("path", path);
		request.setAttribute("lrr", user.getUserName());
		request.setAttribute("lrsj", GetTime.getTimeByFormat("yyyy-mm-dd"));
		request.setAttribute("lrrname", user.getRealName());
		//计分大项
		request.setAttribute("dxlist", new CommUtil().getDxList(user.getUserDep(),user.getUserName()));
		//计分小项
		request.setAttribute("xxlist", new CommUtil().getXxList(user.getUserDep(),user.getUserName()));
		request.setAttribute("jfxmdm", model.getJfxmdm());
		request.setAttribute("xmmkdm", model.getXmmkdm());
		return mapping.findForward("updateJg");
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-23 下午04:27:31
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
	public ActionForward viewJg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfDrForm myForm = (ZhfDrForm) form;
		ZhfDrForm model = service.getModel(myForm);
		if(null!=model && !"save".equals(myForm.getType())){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("jg", service.viewJg(model.getId()));
		String path = "zjly_zhfdr.do?method=viewJg";
		request.setAttribute("path", path);
		return mapping.findForward("viewJg");

	}
	
}
