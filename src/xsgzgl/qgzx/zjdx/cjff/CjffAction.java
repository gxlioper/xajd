/**
 * @部门:学工产品事业部
 * @日期：2016-12-20 下午03:13:22 
 */  
package xsgzgl.qgzx.zjdx.cjff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.qgzx.jfgl.QgzxJfglService;
import xsgzgl.qgzx.zjdx.tjcx.TjcxDAO;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zjly.zhf.zhfdr.ZhfDrForm;

import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 浙江大学酬金发放
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-20 下午03:13:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CjffAction extends SuperAction<CjffForm, CjffService> {
	CjffService service = new CjffService();
	public static final String DRCGBZ = "导入成功！";
	public static final String DRSBBZ = "导入失败,请仔细核对【错误数据.xls】！";
	public static final String KBG = "空excel表格！";
	public static final String KFILE = "没有文件！";
	public static final String EXCELREPEAT = "excel中存在重复数据(学号，发放年度月份，用人单位主键重复)，请仔细核对！";
	public ActionForward cjffCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjffForm model = (CjffForm) form;
		TjcxDAO tjcxDAO = new TjcxDAO();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_nd(new String[] {Base.currNd});
		//默认高级查询条件-月份
		String Yf = tjcxDAO.getCsszYf();
		searchModel.setSearch_tj_yf(new String[]{Yf});
		
		HashMap<String,String> dataMap = service.cjffCxTitleXx(Base.currNd,user);
		request.setAttribute("tsxx", dataMap);
		request.setAttribute("dqnd", Base.currNd);
		request.setAttribute("searchTj", searchModel);
		String path = "qgzx_jfcjgl_cjff_zjdx.do";
		request.setAttribute("path", path);
		//验证是否在酬金发放开放时间段内，从而得到开发开关
		HashMap<String, String> csszMap = service.getCsszMap();
		int kssj = Integer.parseInt(csszMap.get("kssj"));
		int jssj = Integer.parseInt(csszMap.get("jssj"));
		request.setAttribute("jssj", jssj);
		boolean sfkq = service.checkIsInKfsjd(kssj, jssj);
		String sqkg = sfkq ? "open" :"close";
		/**
		 * 为防止oracel job定时器非正常执行，特在此点菜单时候更新数据，如后续更新数据量大，
		 * 需要修改，可以采用配置web.xml listerner监听的方式,写time定时器去实现
		 */
//		if(!sfkq){
//			service.updateWtjsj();
//		}
		request.setAttribute("sqkg", sqkg);
		//验证是否是勤工管理员
		QgzxJfglService qgzxJfglService = new QgzxJfglService();
		boolean sfqggly = qgzxJfglService.sfQggly(user.getUserName());
		request.setAttribute("sfqggly", sfqggly);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cjffcx");
	}
	
	/**
	 * 
	 * @描述: 酬金发放增加
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 上午10:01:19
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
	public ActionForward cjffAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjffForm myForm = (CjffForm)form;
		if(StringUtils.isNotNull(myForm.getXh())){
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
		}
		User user = getUser(request);
		//用人单位
		List<HashMap<String, String>> yrdwList = service.getYrdwyList(user);
		//岗位性质
		List<HashMap<String, String>> gwxzList = service.getGwxzList();
		//岗位类别
		List<HashMap<String, String>> gwlbList = service.getGwlbList();
		//校区
		List<HashMap<String, String>> xqList = service.getXqList();
		//参数设置
		HashMap<String, String> csszMap = service.getCsszMap();
		//月份下拉框
		List<HashMap<String, String>> yfList = service.createList(csszMap.get("ksyf"), csszMap.get("jsyf"));
		request.setAttribute("yrdwList", yrdwList);
		request.setAttribute("gwxzList", gwxzList);
		request.setAttribute("gwlbList", gwlbList);
		request.setAttribute("xqList", xqList);
		request.setAttribute("yfList", yfList);
		//取出酬金上限
		request.setAttribute("sxsz", csszMap.get("sxsz"));
		//取出参数设置的是否允许超过酬金上限
		String sfyxcgcjsx = csszMap.get("sfyxcgcjsx");
		request.setAttribute("sfyxcgcjsx", sfyxcgcjsx);
		//取出酬金标准
		request.setAttribute("cjbz", csszMap.get("cjbz"));
		request.setAttribute("type", "add");
		request.setAttribute("path", "cjff_zjdx.do?method=cjffAdd");
		//取出用户部门代码
		request.setAttribute("yrdwdm", user.getUserDep());
		return mapping.findForward("cjffadd");
	}
	
	/**
	 * 
	 * @描述: 酬金发放修改
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 下午05:11:14
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
	public ActionForward cjffEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjffForm myForm = (CjffForm)form;
		CjffForm model = service.getModel(myForm);
		if(StringUtils.isNotNull(model.getId())){
			// 加载学生基本信息
			HashMap<String, String> xsjbxx = service.getXsxxck(myForm.getId());
			//复制
			BeanUtils.copyProperties(myForm, model);
			request.setAttribute("jbxx", xsjbxx);
		} 
		 
		User user = getUser(request);
		//用人单位
		List<HashMap<String, String>> yrdwList = service.getYrdwyList(user);
		//岗位性质
		List<HashMap<String, String>> gwxzList = service.getGwxzList();
		//岗位类别
		List<HashMap<String, String>> gwlbList = service.getGwlbList();
		//校区
		List<HashMap<String, String>> xqList = service.getXqList();
		//参数设置
		HashMap<String, String> csszMap = service.getCsszMap();
		request.setAttribute("yrdwList", yrdwList);
		request.setAttribute("gwxzList", gwxzList);
		request.setAttribute("gwlbList", gwlbList);
		request.setAttribute("xqList", xqList);
		//发放年度月份，查看用
		request.setAttribute("ffndyf", model.getFfndyf());
		//取出酬金上限
		request.setAttribute("sxsz", csszMap.get("sxsz"));
		//取出参数设置的是否允许超过酬金上限
		String sfyxcgcjsx = csszMap.get("sfyxcgcjsx");
		request.setAttribute("sfyxcgcjsx", sfyxcgcjsx);
		//取出酬金标准
		request.setAttribute("cjbz", csszMap.get("cjbz"));
		request.setAttribute("type", "update");
		return mapping.findForward("cjffedit");
	}
	
	/**
	 * 
	 * @描述:酬金发放查看
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-21 下午05:32:40
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
	public ActionForward cjffck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CjffForm myForm = (CjffForm)form;
		if(StringUtils.isNotNull(myForm.getId())){
			// 加载学生基本信息
			HashMap<String, String> xsjbxx = service.getXsxxck(myForm.getId());
			//复制
			BeanUtils.copyProperties(myForm, myForm);
			request.setAttribute("jbxx", xsjbxx);
			HashMap<String, String> bdxxMap = service.getYwbdxxCk(myForm.getId());
			request.setAttribute("bdxxMap", bdxxMap);
		}
		request.setAttribute("type", "view");
		return mapping.findForward("cjffck");
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-22 上午08:45:29
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
	public ActionForward deljg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-22 上午08:47:01
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CjffForm model = (CjffForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页
		

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 *
	 * @描述: 保存表单
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-22 上午08:51:07
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
	public ActionForward saveForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CjffForm model = (CjffForm) form;
		String message = "";
		//先验证学号，姓名准确性
		if(!service.checkXhXmIsTrue(model.getXh(), model.getXm())){
			message = "学号，姓名没有对应一致，请确认！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		//验证数据是否重复
		if(!service.checkIsNotExists(model.getXh(), model.getFfndyf(), model.getYrdwdm(), model.getId())){
			message = "存在相同学号相同用人单位相同发放年月的记录，请确认！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		//验证是否符合经费划拨
		HashMap<String, String> dataMap = service.checkIsFhJfhb(model.getFfndyf(), model.getYrdwdm(), model.getBcje(),model.getId());
		if(("false").equals(dataMap.get("rs"))){
			message = "【酬金】不能超过部门经费余额，当前部门经费余额"+dataMap.get("syje")+"元，有效发放金额不能超过"+dataMap.get("syje")+"元！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		User user = getUser(request);
		model.setLrr(user.getUserName());
		boolean result = service.saveForm(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 提交
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-22 上午09:23:45
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
	public ActionForward tj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String values = request.getParameter("values");
		String message = "";
		boolean result = true;
		if (!StringUtil.isNull(values)) {
			 result = service.submit(values.split(","));
		}
		
		message = result ? "提交成功！"
				: "提交失败！";
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述: 导入
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-22 下午05:30:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward dr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("dr");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 下载导入模板
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-23 上午09:16:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("zjdx_qgzx_cjff_xzmb.xls".getBytes(), "GBK") + "\"");
		service.createWwb(response.getOutputStream());
		return null;
	}
	
	/**
	 * 
	 * @描述: 导入保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-23 上午11:03:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		CjffForm model = (CjffForm) form;
		FormFile file = model.getDrmb();
		User user = getUser(request);
		model.setLrr(user.getUserName());
		model.setUser(user);
		//参数设置
		HashMap<String, String> csszMap = service.getCsszMap();
		//月份下拉框
		List<HashMap<String, String>> yfList = service.createList(csszMap.get("ksyf"), csszMap.get("jsyf"));
		model.setYfList(yfList);
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
				}else if("excelrepeat".equals(resultMap.get("result"))){
					 message = EXCELREPEAT;
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
	 * @描述: 错误文件下载
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-23 上午11:11:19
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
	 * @throws Exception 
	 * 
	 * @描述: 取消提交
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-6 下午04:52:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward CancelTjRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			boolean result = service.cancelTjjl(ids);
			String message = result ? "取消提交成功！" : "取消提交失败！";
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
}
