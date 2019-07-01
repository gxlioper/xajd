/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhqjdr;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.rcsw.gjgl.gjjg.GjjgForm;
import com.zfsoft.xgxt.rcsw.gjgl.gjjg.GjjgService;

public class HcqjAction extends SuperAction {
	
	private static final String url = "rcsw_hcyhk_hcyhqjdr.do";//数据库里的
	public static final String DRCGBZ = "导入成功！";
	public static final String DRSBBZ = "导入失败,请仔细核对【出错记录.xls】！";
	public static final String KBG = "空excel表格！";
	public static final String KFILE = "没有文件！";
	HcqjService service = new HcqjService();
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date 		：2017-11-9 上午11:52:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward hcqjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HcqjForm model = (HcqjForm)form;
		request.setAttribute("path", url);
		model.setPath(url);
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
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hcqjManage");
	}
	
	/**
	 * 
	 */
	public ActionForward importhcyhk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		GjjgService  service =new GjjgService();
		request.setAttribute("jsxx",service.getFdyxx(user.getUserName()));
		return mapping.findForward("drjsp");
	}
	
	/**
	 * @描述: 下载导入模板
	 */	
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = request.getSession().getServletContext().getRealPath(
				"/temp/mb/")+"/hcqjdrmb.xls";
        logger.info("path = "+path);
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("hcqjdrmb.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
	
	/**
	 * @描述: 导入保存
	 */	
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		HcqjForm model = (HcqjForm) form;
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
	
	/**
	 * @描述: 删除
	 */
	@SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
	public ActionForward HcqjDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String xhs = request.getParameter("xhs");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
			boolean result =(boolean) false;
			result = num > 0;
			if (result) {
				result = service.clearBpmx(xhs.split(","));
			}
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	

	
	
}
