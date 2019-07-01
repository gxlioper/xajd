package com.zfsoft.xgxt.base.export.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;

import xgxt.action.BaseAction;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.model.ImportModel;
import com.zfsoft.xgxt.base.export.service.IImportService;
import com.zfsoft.xgxt.base.export.service.imp.ImportServiceImpl;
import com.zfsoft.xgxt.base.export.util.ImportConfig;

/**
 * 导入数据
 * 
 * @author Jiangdong.Yi
 * 
 */
public class ImportAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ValueStack vs = null;
	private ImportModel model = new ImportModel();
	private IImportService importService = new ImportServiceImpl();
	private File file;
	protected Log logger = LogFactory.getLog(ImportAction.class);

	public void setImportService(IImportService importService) {
		this.importService = importService;
	}

	public ImportModel getModel() {
		return model;
	}

	private ValueStack getValueStack(HttpServletRequest request,
			HttpServletResponse response) {
		vs = new ValueStack(request, response);
		return vs;
	}

	private ValueStack getValueStack(HttpServletRequest request,
			HttpServletResponse response, ActionServlet servlet) {
		//这里使用了静态变量存储了当前ActionServlet对象
		//因为新系统此对象是从service获取而非action
		//旧版系统（也就是servletContext对象）只能在aciton才能获取。
		//这样使用可能会存在并发问题，改成传递（增加方法参数）可以解决，但是暂时工作量比较大。
		//延迟
		vs = getValueStack(request, response);
		vs.servlet = servlet;
		return vs;
	}

	/**
	 * 进入导入数据页面
	 * 
	 * @return
	 */
	public ActionForward toImportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ImportModel myForm = (ImportModel) form;
		try {
			ValueStack vs = getValueStack(request, response);
			// 设置导入表列
			List<ImportModel> impTableList = importService
					.getImportTableListByDrmkdm(myForm.getDrmkdm());
			vs.set("impTableList", impTableList);
			vs.set("drmkdm", model.getDrmkdm());
		} catch (Exception e) {
			throw new RuntimeException("设置导入列错误！",e);
		}
		return mapping.findForward("toImportData");
	}
	/**
	 * 
	 * @描述: 自动配置导入
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-26 上午11:34:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * ActionForward 返回类型 
	 */
	public ActionForward autoset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tableName=request.getParameter("tableName");
		String key=request.getParameter("key");
		String type=request.getParameter("type");
		String name=request.getParameter("name");
		if(SAVE.equals(type)){
			ImportConfig ic=new ImportConfig();
			ic.config(key,name,tableName);
			Map<String,String> data=new HashMap<String, String>();
			data.put("message", "自动配置成功！");
			response.getWriter().print(JSONObject.fromObject(data));
			return null;
		}
		return mapping.findForward("autoset");
	}
	public ActionForward autosetExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tableName=request.getParameter("tableName");
		String bh=request.getParameter("bh");
		String mc=request.getParameter("mc");
		String type=request.getParameter("type");
		if(SAVE.equals(type)){
			ImportConfig ic=new ImportConfig();
			ic.configExport(bh, mc, tableName);
			Map<String,String> data=new HashMap<String, String>();
			data.put("message", "自动配置成功！");
			response.getWriter().print(JSONObject.fromObject(data));
			return null;
		}
		return mapping.findForward("autosetexport");
	}
	/**
	 * 导入数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward importData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ValueStack vs = getValueStack(request, response,servlet);
		User user = getUser(request);
		ImportModel myForm = (ImportModel) form;
		try {
			if(myForm.getDrms() != null && myForm.getDrms().equals("1")){
				// 获取错误临时目录
				String path = servlet.getServletContext().getRealPath(
						IImportService.IMPORT_TEMP_DIRPATH);

				StringBuffer filePath = new StringBuffer(path);
				filePath.append("\\");
				filePath.append(IImportService.IMPORT_ERROR);
				filePath.append("_");
				filePath.append(myForm.getDrmkdm());
				// 导入错误数据文件名称加入用户
				if (!StringUtils.isNull(user.getUserName())) {
					filePath.append("_");
					filePath.append(user.getUserName());
				}
				// 暂时值支持xls文件导入
				filePath.append(".");
				filePath.append(IImportService.IMPORT_SUFFLX);
				// 导出文件存放 的临时目
				file = new File(filePath.toString());
				myForm.setUpdateFile(file);
			}

			HashMap<String, Object> importReslult = importService
					.importData(myForm, user);

			// 导入成功数据
			if (importReslult != null
					&& importReslult.get("succeedList") != null) {
				/*
				 * logger.update(user, getText("log.message.ywmc", new String[]
				 * { "通用导入", model.getDrbm().toUpperCase() }), "通用导入",
				 * getText("log.message.czms", new String[] { "通用导入", "导入模块代码",
				 * model.getDrmkdm() }));
				 */
			}
			// 生成错误文件
			if (importReslult != null && importReslult.get("errorList") != null) {
				importService.getImportErrortData(myForm.getDrmkdm(), myForm
						.getDrbm(), (List<String[]>) importReslult
						.get("errorList"), user);
			}
			vs.set(ValueStack.JSON, importReslult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载模板
	 * 
	 * @return
	 */
	public ActionForward downloadTemplate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ValueStack vs = getValueStack(request, response,servlet);
		ImportModel myForm = (ImportModel) form;
		try {
			file = importService.getImportTemplate(myForm);
			vs.attachmentStream(file);
		} catch (Exception e) {
			throw new RuntimeException("生成模板错误!", e);
		}
		return null;
	}

	/**
	 * 下载错误数据
	 * 
	 * @return
	 */
	public ActionForward downloadErrorData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ValueStack vs = getValueStack(request, response);
		ImportModel myForm = (ImportModel) form;
		try {
			// 获取错误临时目录
			String path = servlet.getServletContext().getRealPath(
					IImportService.IMPORT_TEMP_DIRPATH);
			// 获取当前操作用户
			User user = getUser(request);

			StringBuffer filePath = new StringBuffer(path);
			filePath.append("\\");
			filePath.append(IImportService.IMPORT_ERROR);
			filePath.append("_");
			filePath.append(myForm.getDrmkdm());
			// 导入错误数据文件名称加入用户
			if (!StringUtils.isNull(user.getUserName())) {
				filePath.append("_");
				filePath.append(user.getUserName());
			}
			// 暂时值支持xls文件导入
			filePath.append(".");
			filePath.append(IImportService.IMPORT_SUFFLX);
			// 导出文件存放 的临时目
			file = new File(filePath.toString());
			vs.attachmentStream(file);
		} catch (Exception e) {

		}
		return null;
	}

}
