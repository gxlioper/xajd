/**
 * @部门:学工产品事业部
 * @日期：2015-9-8 上午11:16:24 
 */  
package com.zfsoft.xgxt.xsxx.xygl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

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
import com.zfsoft.xgxt.comm.zdybd.service.FlszService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息new
 * @类功能描述: 校友管理
 * @作者： 沈晓波[工号:1123]
 * @时间： 2015-9-8 上午11:16:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyglAction extends SuperAction {
	
	private static final String url = "xsxx_xygl.do";
	
	/**
	 * 校友管理信息查询（非在校生集合）
	 */
	@SystemAuth(url = url)
	public ActionForward xyglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyglService service = new XyglService();
		XyglForm model = (XyglForm) form;
		model.setSfzx("0");// 非在校
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getXyglList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_xygl.do";
		request.setAttribute("path", path);
		request.setAttribute("method", request.getParameter("method"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xyglList");
	}
	
	/**
	 * 查看页面
	 */
	@SystemAuth(url = url)
	public ActionForward xyglCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XyglForm myForm = (XyglForm) form;
		XsxxglService xsxxService = new XsxxglService();
//		XyglService service = new XyglService();
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			myForm.setXh(user.getUserName());
		}
		HashMap xsxxMap = xsxxService.getXsxxByXh(myForm.getXh());
		if (QUERY.equalsIgnoreCase(myForm.getType())) {
			String gndm = "xygl_query";
			List<String> srcList = new FlszService().getSrcListByGndm(gndm);
			if (srcList.contains("xsgbxxList")) {
				xsxxMap.put("xsgbxxList", xsxxService.getXsgbxxList(myForm.getXh()));
			}
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		request.setAttribute("xh", myForm.getXh());
		return mapping.findForward("xyglCk");
	}
	
	/**
	 * 删除
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-校友管理-校友管理-删除VALUES:{values}")
	public ActionForward xyglSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyglService service = new XyglService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = values.split(",").length;
			boolean result = service.delData(values);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_SUCCESS);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.EXP_SYS_ERROR);
		}
		return null;
	}
	
	/**
	 * 修改
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-校友管理-校友管理-修改XH:{xh}")
	public ActionForward xyglXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService xsxxService = new XsxxglService();
		XyglService service = new XyglService();
		XyglForm model = (XyglForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		HashMap xsxxMap = service.getXsxxByXhForUpdate(model.getXh());

		String gndm = "xygl_update";
		List<String> srcList = new FlszService().getSrcListByGndm(gndm);	
		if (QUERY.equalsIgnoreCase(model.getType())) {
			
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
			
		} else if (UPDATE.equals(model.getType())) {
			model.setUser(user);
			CommService commService = new CommService();
			HashMap<String, String> valueMap = commService.getValueMap(request,
					xsxxService.getColumnByTable("xsxxb"));
			HashMap<String, String> xsfzxxValueMap = commService.getValueMap(
					request, xsxxService.getColumnByTable("xsfzxxb"));
			// 家庭地址:取xsxxb的jtdz（旧版：取xsfzxxb的jtszd）
//			valueMap.put("jtdz", xsfzxxValueMap.get("jtszd"));
			xsfzxxValueMap.put("jtszd", valueMap.get("jtdz"));

			boolean result = service.updateRecord(model, valueMap,
					xsfzxxValueMap);

			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		String zpsfcz = new XsxxtyglService().checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("xh", model.getXh());
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xyglXg");
	}
	
	
	/**
	 * 导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xyglExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XyglForm model = (XyglForm) form;
		model.setSfzx("0");// 非在校
		exportData(model, request, response);
		return null;
	}
	
	private void exportData(XyglForm model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XyglService service = new XyglService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
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
	}
	
	/**
	 * 增加
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-校友管理-校友管理-增加XH:{xh}")
	public ActionForward xyglZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XyglForm model = (XyglForm) form;
		XsxxglService xsxxService = new XsxxglService();
		XyglService service = new XyglService();

		if (SAVE.equalsIgnoreCase(model.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			User user = getUser(request);
			model.setUser(user);
			CommService commService = new CommService();
			HashMap<String, String> valueMap = commService.getValueMap(request,
					xsxxService.getColumnByTable("xsxxb"));
			boolean result = service.saveRecord(model, valueMap);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		this.saveToken(request);
		return mapping.findForward("xyglZj");
	}
	
	
	/**
	 * 判断学号是否在校友管理中
	 */
	public String jcXhsfcz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XyglForm myForm = (XyglForm) form;
		XyglService service = new XyglService();
		// ============= end ============

		// ==================构建前台页面====================
		String message = service.chkStuIsExistsXYGL(myForm.getXh());

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
}
