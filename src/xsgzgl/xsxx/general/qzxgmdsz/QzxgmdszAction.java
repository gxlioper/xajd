package xsgzgl.xsxx.general.qzxgmdsz;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class QzxgmdszAction extends BasicAction {
	/**
	 * 强制修改名单设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward qzxgmdCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszService service = new QzxgmdszService();
		QzxgmdszInit init = new QzxgmdszInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initQzxgmd(rForm, myForm, user, request);

		// 跳转路径
		String url = "/xsgzgl/xsxx/general/qzxgmdsz/qzxgmdCx.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
		
	}
	
	
	/**
	 * 查询强制修改名单修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchQzxgmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszInit init = new QzxgmdszInit();
		QzxgmdszForm model = new QzxgmdszForm();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initQzxgmd(rForm, myForm, user, request);
		QzxgmdszService service = new QzxgmdszService();

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE版本
		String ie = otherValue[0];
		String stylePath = otherValue[1];
		rsModel.setIe(ie);
		rsModel.setStylePath(stylePath);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getQzxgmdTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getQzxgmdList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createQzxgmdHTML(rsModel, model, rsArrList,
					user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 检测强制修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkQzxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszInit init = new QzxgmdszInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initQzxgmd(rForm, myForm, user, request);
		QzxgmdszService service = new QzxgmdszService();
		
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		
		String message = service.checkQzxg(myForm,user);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 强制修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问学生信息-基础设置-强制修改名单设置-保存-pk:{primarykey_checkVal}")
	public ActionForward szQzxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszInit init = new QzxgmdszInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initQzxgmd(rForm, myForm, user, request);
		QzxgmdszService service = new QzxgmdszService();
		
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		
		boolean flag = service.szQzxg(myForm,user);
		String message =flag?"操作成功！":"操作失败！";
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 取消强制修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问学生信息-基础设置-强制修改名单设置-取消-pk:{primarykey_checkVal}")
	public ActionForward qxQzxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszInit init = new QzxgmdszInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initQzxgmd(rForm, myForm, user, request);
		QzxgmdszService service = new QzxgmdszService();
		
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		
		boolean flag = service.qxQzxg(myForm,user);
		String message =flag?"操作成功！":"操作失败！";
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	/**
	 * 
	 * @描述:强制修改名单导出查询列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-25 下午02:24:26
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
	public ActionForward qzmdExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QzxgmdszForm myForm = (QzxgmdszForm) form;
		QzxgmdszService service = new QzxgmdszService();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.getQzxgmdListForDc(myForm,user);
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

}
