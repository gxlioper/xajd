package xsgzgl.qgzx.jcdmwh;

import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xsgzgl.qgzx.gwglnew.QgzxGwglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class QgzxJcdmwhAjax extends BasicExtendAction{
	
	
	/**
	 * 岗位性质维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxzWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxGwglService service = new QgzxGwglService();
		List<HashMap<String,String>> list = service.getGwList();
		JSONArray dataList = JSONArray.fromObject(list);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * 岗位信息管理保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-基础设置-岗位性质维护-增加或修改GWXZMC:{gwxzmc}")
	public ActionForward gwxzBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		String doType = request.getParameter("doType");
		String message = service.gwxzBc(model,doType);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述:性质代码弹出层更改
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-24 下午12:08:22
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
	public ActionForward gwxzZjxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		if(UPDATE.equalsIgnoreCase(doType)){
			QgzxJcdmwhService service = new QgzxJcdmwhService();
			request.setAttribute("model",service.getGwlbData(model.getGwxzdm()));
		}
		return mapping.findForward("gwxzZj");
	}
	
	
	/**
	 * 岗位信息管理删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-基础设置-岗位性质维护-删除VALUES:{pkValue}")
	public ActionForward gwxzSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		String message  = service.gwxzSc(model);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	
	/**
	 * 验证修改删除信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkScInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.checkScInfo(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 岗位性质维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yrdwWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		// ==================构建前台页面 end========================
	}
	
	
	/**
	 * 岗位信息管理保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-基础设置-用人单位维护增加或修改YRDWMC：{yrdwmc}")
	public ActionForward yrdwBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		String xydm = model.getXydm();
		if(StringUtil.isNull(xydm)){
			xydm = UUID.randomUUID().toString().substring(26);
			model.setXydm(xydm);
		}
		boolean saveFlag = service.runInsert(model);
		/*if("02".equals(model.getDwlb())){//校外单位增加用户
			*//*boolean isExist = service.yhIsExist(model);
			if(isExist){
				response.getWriter().print(getJsonMessage("用户名已存在！！！"));
				return null;
			}*//*
			saveFlag = service.insertYhb(model);
		}*/
		String messageKey = saveFlag ? MessageKey.SYS_SAVE_SUCCESS :MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	public ActionForward yrdwZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxJcdmwhService service = new QgzxJcdmwhService();
		request.setAttribute("xyList",service.getBmList() );
		return mapping.findForward("yrdwZj");
	}

	public ActionForward dwxx(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {

		String type = request.getParameter("type");
		if("01".equals(type)){
			return mapping.findForward("xnyrdwxx");
		} else {
			return mapping.findForward("xwyrdwxx");
		}
	}

	public ActionForward selectTeacher(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response) throws Exception {

		String type = request.getParameter("type");
		if(QUERY.equals(type)){
			QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			QgzxJcdmwhService service = new QgzxJcdmwhService();
			List<HashMap<String,String>> resultList = service.getAllTeacher(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path","qgzx_jcdmwh_ajax.do?method=selectTeacher");
		return mapping.findForward("selectTeacher");
	}

	public ActionForward yrdwXg(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {

		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		HashMap<String,String> rs = service.getData(model.getId());
		String type = request.getParameter("type");
		if(UPDATE.equals(type)){
			boolean flag = service.runUpdate(model);
			String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS :MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("model",rs);
		request.setAttribute("xyList", Base.getXyList());
		return mapping.findForward("yrdwXg");
	}
	public ActionForward yrdwCk(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {

		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		HashMap<String,String> rs = service.getData(model.getId());
		request.setAttribute("model",rs);
		request.setAttribute("xyList", Base.getXyList());
		return mapping.findForward("yrdwCk");
	}
	public ActionForward mmcsh(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {

		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		String type = request.getParameter("type");
		if(UPDATE.equals(type)){
			boolean flag = service.mmcsh(model);
			String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS :MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("id",model.getId());
		return mapping.findForward("mmcsh");
	}
	public ActionForward blsc(ActionMapping mapping, ActionForm form,
							   HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		if(!service.checkDwIsUsed(model.getId().split(","))){
			boolean flag = service.blsc(model);
			String messageKey = flag ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} else {
			response.getWriter().print(getJsonMessage("数据被使用！"));
		}

		return null;
	}
	/**
	 * 岗位信息管理删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description = "访问勤工助学-基础设置-用人单位维护删除VALUES：{pkValue}")
	public ActionForward yrdwSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.yrdwSc(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 验证修改删除信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkyrdwScInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		String pkValue = request.getParameter("pkValue");
		model.setPkValue(pkValue);
		String message  = service.checkyrdwScInfo(model);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 岗位性质维护自定义导出 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward gwxzwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = (List<HashMap<String,String>>) service.getGwxzExportDataList(model);
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
	
	/**
	 * 用人单位维护自定义导出 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward yrdwwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxJcdmwhService service = new QgzxJcdmwhService();
		QgzxJcdmwhForm model = (QgzxJcdmwhForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = (List<HashMap<String,String>>) service.getYrdwExportDataList(model);
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
