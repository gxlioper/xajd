package xsgzgl.gygl.gypy.gypywh;

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
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
/**
 * 公寓管理-公寓评优-公寓评优管理
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class GypywhAjax  extends BasicExtendAction{
	
	
	/**
	 * 公寓评优查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gypyCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GypywhService service = new GypywhService();
		GypywhForm model = (GypywhForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("gygl_gypygl_gypywh.do");
		request.setAttribute("path", "gygl_gypygl_gypywh.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gypy","99");
		// 结果集
		ArrayList<String[]> rsArrList = service.gypyCx(model,request);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 公寓评优增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pyqsCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GypywhService service = new GypywhService();
		GypywhForm model = (GypywhForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("gygl_gypywh.do?method=pyqsCx");
		request.setAttribute("path", "gygl_gypywh.do?method=pyqsCx");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		String xn = request.getParameter("xn");
		String xqdm = request.getParameter("xqdm");
		String pydx = request.getParameter("pydx");
		String pylbdm = request.getParameter("pylbdm");
		String pysj = request.getParameter("pysj");
		model.setXn(xn);
		model.setXqdm(xqdm);
		model.setPylbdm(pylbdm);
		model.setPysj(pysj);
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("pyqs",pydx);
		// 结果集
		ArrayList<String[]> rsArrList = null;
		if("1".equalsIgnoreCase(pydx)) {
			rsArrList = service.pyqsCx(model,request);
		}else if("0".equalsIgnoreCase(pydx)) {
			model.getSearchModel().setPath("gygl_gypywh.do?method=pyldCx");
			request.setAttribute("path", "gygl_gypywh.do?method=pyldCx");
			rsArrList = service.pyldCx(model,request);
		}else if("2".equalsIgnoreCase(pydx) || "3".equalsIgnoreCase(pydx) || "4".equalsIgnoreCase(pydx)) {
			rsArrList = service.pyzzCx(model,request);
		}
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 * 公寓评优增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓评优管理-公寓评优维护-增加XN:{xn},XQDM:{xqdm},PYLBDM:{pylbdm},PYSJ:{pysj}")
	public ActionForward gypyZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GypywhService service = new GypywhService();
		GypywhForm model = (GypywhForm) form;
		//传输乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		model.setXn(service.unicode2Gbk(model.getXn()));
		model.setXqdm(service.unicode2Gbk(model.getXqdm()));
		model.setPylbdm(service.unicode2Gbk(model.getPylbdm()));
		model.setPysj(service.unicode2Gbk(model.getPysj()));
		String message = service.gypyZj(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 公寓评优删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓评优管理-公寓评优维护-删除PK:{pkValue}")
	public ActionForward gypySc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GypywhService service = new GypywhService();
		GypywhForm model = (GypywhForm) form;
		//传输乱码问题
		model.setPkValue(service.unicode2Gbk(model.getPkValue()));
		String message = service.gypySc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 日常事务结果查询自定义导出
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
		GypywhService service = new GypywhService();
		GypywhForm model = (GypywhForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.setUser(user);
		// 结果集
		List<HashMap<String,String>> resultList = service.gypyCxForDc(model, request);
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
