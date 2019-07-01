package xsgzgl.jxgl.general.jxcjgl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;


/**
 * 军训管理-军训考核管理-军训成绩管理
 * @author yeyipin
 * @since 2012.10.13
 */
public class JxglJxcjglAjax extends BasicAction{
	
	/**
	 * 军训成绩查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxcjCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JxglJxcjglService jxglJxcjglService = new JxglJxcjglService();
		JxglJxcjglForm model = (JxglJxcjglForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		jxglJxcjglService.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("jxgl_jxkhgl_jxcjgl.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		String jxid = request.getParameter("otherValue").split("!!@@!!")[1];
		model.setJzid(jxid);
		List<HashMap<String, String>> topTr = jxglJxcjglService.getTopTr(jxid);
		// 结果集
		ArrayList<String[]> rsArrList = jxglJxcjglService.jxcjCx(model,request);
		// 构建结果集
		String spHtml = jxglJxcjglService.createSearchHTML(model,rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		jxglJxcjglService.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
		
	}
	
	/**
	 * 军训成绩保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxcjBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglJxcjglService jxglJxcjglService = new JxglJxcjglService();
		JxglJxcjglForm model = (JxglJxcjglForm) form;
		//传输乱码问题
		model.setPkValue(jxglJxcjglService.unicode2Gbk(model.getPkValue()));
		String message = jxglJxcjglService.jxcjBc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 军训成绩导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxcjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JxglJxcjglService jxglJxcjglService = new JxglJxcjglService();
		JxglJxcjglForm model = (JxglJxcjglForm) form;
		// ============= 初始化各变量的值 ============
		model.getSearchModel().setPath("jxgl_jxkhgl_jxcjgl.do");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		jxglJxcjglService.jxcjDc(response.getOutputStream(),model,request);
		return null;
	}
	
	/**
	 * 军训管理成绩自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward jxcjglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JxglJxcjglService jxglJxcjglService = new JxglJxcjglService();
		JxglJxcjglForm model = (JxglJxcjglForm) form;
		
		String jxid = request.getParameter("jxid");
		model.setJzid(jxid);
		User user = getUser(request);// 用户对象
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		List<HashMap<String,String>> resultList = jxglJxcjglService.jxcjExportDataCx(model,request);
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
