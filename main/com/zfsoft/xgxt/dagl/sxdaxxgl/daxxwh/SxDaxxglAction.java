/**
 * @部门:学工产品事业部
 * @日期：2016-8-17 下午02:05:59 
 */  
package com.zfsoft.xgxt.dagl.sxdaxxgl.daxxwh;

import java.io.File;
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

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 三峡档案管理模块
 * @类功能描述: 维护档案信息
 * @作者： caopei[工号:1352]
 * @时间： 2016-8-25 下午06:41:48 
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class SxDaxxglAction extends SuperAction<SxDaxxglForm, SxDaxxglService>{
	private static final String url = "sxdaxxgl.do";//数据库里的
	SxDaxxglService service = new SxDaxxglService();
	
	/**
	 * 
	 * @描述:查询操作
	 * @日期：2016-8-25 下午06:44:55
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward getdalist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SxDaxxglForm model = (SxDaxxglForm)form;
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
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String, String>> xqlist = Base.getXqList();
		String xqmc = "";
		for (HashMap<String, String> hashMap : xqlist) {
			if(Base.currXq.equalsIgnoreCase(hashMap.get("xqdm"))){
				xqmc = hashMap.get("xqmc");
			}
		}
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getdalist");
	}
	
	/**
	 * @描述:维护
	 * @日期：2016-8-25 下午06:46:45
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxdaxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SxDaxxglDao dao = new SxDaxxglDao();
		SxDaxxglForm model = (SxDaxxglForm)form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			model.setFlag("gx");
			List<HashMap<String,String>> resultList = service.getXsPageList(model, user);
			JSONArray dataList  = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		HashMap<String, String> bjxxMap = service.getBjxx(model.getBjdms());
		request.setAttribute("xymc",bjxxMap.get("xymc"));
		request.setAttribute("zymc",bjxxMap.get("zymc"));
		request.setAttribute("nj",bjxxMap.get("nj"));
		request.setAttribute("bjmc",bjxxMap.get("bjmc"));
		String count = dao.getCount(model.getBjdms());
		String ywh = dao.getywhCount(model.getBjdms());
		int wwh = Integer.parseInt(count)-Integer.parseInt(ywh);
		request.setAttribute("ywh",ywh);
		request.setAttribute("wwh",wwh);
		request.setAttribute("bjdms",model.getBjdms());
		request.setAttribute("bjid", model.getBjid());
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String, String>> xqlist = Base.getXqList();
		String xqmc = "";
		for (HashMap<String, String> hashMap : xqlist) {
			if(Base.currXq.equalsIgnoreCase(hashMap.get("xqdm"))){
				xqmc = hashMap.get("xqmc");
			}
		}
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sxdaxxwh");
	}
	
	/**
	 * 
	 * @描述:插入或更新
	 * @日期：2016-8-25 下午06:47:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveData(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SxDaxxglForm myForm = (SxDaxxglForm) form;
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		boolean flag;  
        boolean isExist = service.isExistQysj(myForm);
    	if (!isExist) {
        	flag = service.saveDataXs(myForm, "insert");
        }else{
        	flag = service.saveDataXs(myForm, "update");
        }
		String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述:实时获取维护人数
	 * @日期：2016-8-25 下午06:47:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getwhrs(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SxDaxxglForm myForm = (SxDaxxglForm) form;
		SxDaxxglDao dao = new SxDaxxglDao();
		
		String count = dao.getCount(myForm.getBjdm());
		String ywh = dao.getywhCount(myForm.getBjdm());
		String wwh = String.valueOf(Integer.parseInt(count)-Integer.parseInt(ywh));
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("ywh", ywh);
		map.put("wwh", wwh);
		JSONObject json = JSONObject.fromObject(map); 
		
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * @描述:查看班级学生档案信息
	 * @日期：2016-8-25 下午06:48:04
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SxDaxxglDao dao = new SxDaxxglDao();
		SxDaxxglForm model = (SxDaxxglForm)form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			model.setFlag("gx");
			model.setFlag1("ck");
			List<HashMap<String, String>> resultList = service.getXsPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		request.setAttribute("xn", Base.currXn);
		List<HashMap<String, String>> xqlist = Base.getXqList();
		String xqmc = "";
		for (HashMap<String, String> hashMap : xqlist) {
			if(Base.currXq.equalsIgnoreCase(hashMap.get("xqdm"))){
				xqmc = hashMap.get("xqmc");
			}
		}
		HashMap<String, String> bjxxMap = service.getBjxx(model.getBjdms());
		request.setAttribute("xymc",bjxxMap.get("xymc"));
		request.setAttribute("zymc",bjxxMap.get("zymc"));
		request.setAttribute("nj",bjxxMap.get("nj"));
		request.setAttribute("bjmc",bjxxMap.get("bjmc"));
		String count = dao.getCount(model.getBjdms());
		String ywh = dao.getywhCount(model.getBjdms());
		int wwh = Integer.parseInt(count)-Integer.parseInt(ywh);
		request.setAttribute("ywh",ywh);
		request.setAttribute("wwh",wwh);
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("bjdms",model.getBjdms());
		request.setAttribute("bjid", model.getBjid());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sxdaview");
	}

	/**
	 * @描述:导出
	 * @日期：2016-8-25 下午06:48:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward exportDataWH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SxDaxxglService service=new SxDaxxglService();
		SxDaxxglForm model=(SxDaxxglForm) form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
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
