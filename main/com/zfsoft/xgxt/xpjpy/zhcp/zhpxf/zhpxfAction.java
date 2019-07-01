/**
 * @部门:学工产品事业部
 * @日期：2017-6-27 下午02:56:41 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zhpxf;

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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.zjly.ylbx.YlbxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： CP[工号:1352]
 * @时间： 2017-6-27 下午02:56:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class zhpxfAction extends SuperAction<zhpxfForm, zhpxfService>{
	private zhpxfService service = new  zhpxfService();
	private static final String url = "pj_zcqkcx.do";
	public static final String DRCGBZ = "导入成功！";
	public static final String DRSBBZ = "导入失败,请仔细核对【出错记录.xls】！";
	public static final String KBG = "空excel表格！";
	public static final String KFILE = "没有文件！";
	public ActionForward getZhpxf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		zhpxfForm model = (zhpxfForm) form;
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
//			searchModel.setSearch_tj_xn(new String[] { Base.beforXn });
			request.setAttribute("searchTj", searchModel);
			request.setAttribute("path", url);
			request.setAttribute("userType", user.getUserType());
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("cx");
		}
	}
	
	public ActionForward importYlbx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		YlbxService service = new YlbxService();
		request.setAttribute("jsxx",service.getFdyxx(user.getUserName()));
		return mapping.findForward("drjsp");
	}
	
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = request.getSession().getServletContext().getRealPath(
				"/temp/mb/")+"/zjlyzhpxfmb.xls";
        logger.info("path = "+path);
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("zjlyzhpxfmb.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
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
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		zhpxfForm model = (zhpxfForm) form;
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
				    if ("0".equals(String.valueOf(resultMap.get("cwts")))) {
				    	map.put("message", "成功导入【"+resultMap.get("zqts")+"】条，更新【"+resultMap.get("gxts")+"】条");
				    	map.put("gid", (String)resultMap.get("gid"));
				    	map.put("cw","none");
					}else {
						map.put("message", "成功导入【"+resultMap.get("zqts")+"】条，更新【"+resultMap.get("gxts")+"】条，错误【"+resultMap.get("cwts")+"】条，请仔细核对【错误数据.xls】!");
				    	map.put("gid", (String)resultMap.get("gid"));
				    	map.put("cw","yes");
					}
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
	
	
	
	public ActionForward zhpxfck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		zhpxfForm myform = (zhpxfForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			myform.setXh(user.getUserName());
		}
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
		HashMap<String, String> zhpxfmap = service.getAllData(myform);
		request.setAttribute("zhpxfmap", zhpxfmap);
		return mapping.findForward("ck");
	}

	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		zhpxfForm model = (zhpxfForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);
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
