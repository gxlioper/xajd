/**
 * @部门:学工产品事业部
 * @日期：2016-4-19 上午10:03:50 
 */  
package com.zfsoft.xgxt.zjly.ylbx;

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
import xgxt.utils.String.StringUtils;

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
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 医疗保险管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-19 上午10:03:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YlbxAction extends SuperAction<YlbxForm,YlbxService> {
	private YlbxService service = new  YlbxService();
	private static final String url = "xg_zjly_ylbx.do";
	public static final String DRCGBZ = "导入成功！";
	public static final String DRSBBZ = "导入失败,请仔细核对【出错记录.xls】！";
	public static final String KBG = "空excel表格！";
	public static final String KFILE = "没有文件！";
	public ActionForward getYlbxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxForm model = (YlbxForm) form;
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
		if ("stu".equals(user.getUserType())){
			// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
			return mapping.findForward("xscx");
		}else {
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
			return mapping.findForward("cx");
		}
	}
	
	
	
	/**
	 * @描述：增加
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年9月12日 上午9:29:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问浙江旅游-医疗保险-医疗保险-增加XH:{xh}")
	public ActionForward Ylbxadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxForm myForm = (YlbxForm) form;
		YlbxService service=new YlbxService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			// 唯一性判断
			boolean isExist = service.isExistSame(myForm);
			if (!isExist) {
				boolean result = service.runInsert(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.RCSW_XSXWKH_JBFGL_ADD_EXIST, false));
			}
			return null;
		}
		HashMap<String, String> map=new HashMap<String, String>();
		String currxn=Base.currXn;
		map.put("xn", currxn);
		List<HashMap<String, String>> xnList=Base.getXnndList();
		request.setAttribute("map", map);
		request.setAttribute("xnList", xnList);
		//学生基本信息列表
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("ylbx");
		request.setAttribute("jbxxList", jbxxList);
		//学生信息选择后重新加载
		String path = "zjly_ylbx.do?method=Ylbxadd";
		request.setAttribute("path", path);
		
		return mapping.findForward("add");
	}
	
	/**
	 * @描述：编辑
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年9月12日 下午6:00:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问浙江旅游-医疗保险-医疗保险-编辑XH:{xh}")
	public ActionForward Ylbxedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxForm myForm = (YlbxForm) form;
		YlbxService service=new YlbxService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myForm.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(myForm.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			// 唯一性判断
			boolean isExist = service.isExistSame(myForm);
			if (!isExist) {
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			} else {
				response.getWriter().print(
						getJsonResult(MessageKey.RCSW_XSXWKH_JBFGL_ADD_EXIST, false));
			}
			return null;
		}
		//查询
		YlbxForm model = service.getModel(myForm);
		//载入数据
		BeanUtils.copyProperties(myForm,StringUtils.formatData(model));
		//学生基本信息列表
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList =bdpzService.getJbxxpz("ylbx");
		request.setAttribute("jbxxList", jbxxList);
		
		List<HashMap<String, String>> xnList=Base.getXnndList();
		request.setAttribute("xnList", xnList);
		
		return mapping.findForward("edit");
	}
	
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxForm model = (YlbxForm) form;

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
	
	
	
	
	
	
	public ActionForward del(ActionMapping mapping, ActionForm form,
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
					.getText(MessageKey.SYS_SAVE_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	public ActionForward ylbxck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxForm myform = (YlbxForm) form;
		if(null!=myform){
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myform.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz("ylbx");
		request.setAttribute("jbxxList", jbxxList);
		//查询
		YlbxForm model = service.getModel(myform);
		BeanUtils.copyProperties(myform,model);
		
		return mapping.findForward("ck");
	}
	
	/**
	 * @描述：获取该学生最新参续保信息
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月17日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward loadXbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String xh=request.getParameter("xh");
		//按学号搜索医保信息倒序排列
		List<HashMap<String, String>> resultList=service.getXbxxList(xh);
		if(null==resultList||0==resultList.size()){
			return null;
		}
		HashMap<String, String> xbxx=resultList.get(0);
		response.getWriter().print(JSONObject.fromObject(StringUtils.formatData(xbxx)));
		
		return null;
	}
	
	/**
	 * 
	 * @描述:导入
	 * @作者：CP[工号：1352]
	 * @日期：2017-5-3 上午09:16:25
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
	public ActionForward importYlbx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		request.setAttribute("jsxx",service.getFdyxx(user.getUserName()));
		return mapping.findForward("drjsp");
	}
	/**
	 * 
	 * @描述:下载导入模板
	 * @作者：CP[工号：1352]
	 * @日期：2017-5-3 上午09:37:39
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
				"/temp/mb/")+"/zjlyylbxmb.xls";
        logger.info("path = "+path);
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("zjlyylbxmb.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
/**
 * 
 * @描述:导入保存
 * @作者：CP[工号：1352]
 * @日期：2017-5-3 上午10:33:05
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
	YlbxForm model = (YlbxForm) form;
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
					map.put("message", "成功导入【"+resultMap.get("zqts")+"】条!");
					map.put("cw","none");
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
			}else if (resultMap.get("result").equals("sjcf")) {//表里数据有重复，列出重复学生
			    Map<String,String> map = new HashMap<String, String>();
				map.put("message", "导入表中有重复数据【"+resultMap.get("cwts")+"】条，请对比【错误数据.xls】更改后重新导入!");
				map.put("gid", (String)resultMap.get("gid"));
				map.put("cw","yes");
				JSONObject json = JSONObject.fromObject(map); 
			    response.getWriter().print(json);
				return null;
			}
			else{
			    Map<String,String> map = new HashMap<String, String>();
				map.put("message", "成功导入【"+resultMap.get("zqts")+"】条，错误【"+resultMap.get("cwts")+"】条，请仔细核对【错误数据.xls】!");
				map.put("gid", (String)resultMap.get("gid"));
				map.put("cw","yes");
				JSONObject json = JSONObject.fromObject(map); 
			    response.getWriter().print(json);
				return null;
			}
			
		} catch (FileNotFoundException e) {
			logger.info("导入文件未找到！");
			e.printStackTrace();
		} catch (IOException e) {
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
 * @描述:下载错误数据
 */
public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
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


}