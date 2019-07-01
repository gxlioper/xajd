package com.zfsoft.xgxt.gygl.gyjlxxwh;

import java.io.File;
import java.util.ArrayList;
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
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.gyjlxxgl.GyjlxxglService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

public class GyjlxxwhAction extends SuperAction{
	private GyjlxxwhService service = new GyjlxxwhService();
	private static final String url = "gygl_gyjlglnew_gyjlxxwh_new.do";
	/**
	 * 公寓纪律信息维护
	 * @param mapping
	 * @param from
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjlxxwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		GyjlxxwhForm model = (GyjlxxwhForm) form;
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		searchModel.setPath("gygl_gyjlglnew_gyjlxxwh.do");
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "gygl_gyjlglnew_gyjlxxwh_new.do");
		FormModleCommon.commonRequestSet(request);		
		return mapping.findForward("gyjlxxwhList");
		
	}
	

	
	/**
	 * 增加公寓纪律信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息维护-增加PK:{xh},JLDLDM:{jldldm},JLLBDM:{jllbdm},WJSJ:{wjsj}")
	public ActionForward gyjlZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		GyjlxxglService jlxxService = new GyjlxxglService();
		List<HashMap<String, String>> jldlList= service.getJldlList(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", Base.getDqxqmc());
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("jldlList", jldlList);
		request.setAttribute("jllbList", jlxxService.getJllbList(jldlList.get(0).get("jldldm"), request));
		request.setAttribute("username", user.getRealName());
		return mapping.findForward("gyjlxxwhZj");
	}
	/**
	 * 修改公寓纪律信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息维护-修改PK:{xh},JLDLDM:{jldldm},JLLBDM:{jllbdm},WJSJ:{wjsj}")
	public ActionForward gyjlXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyjlxxwhForm myForm = (GyjlxxwhForm) form;
		if("update".equals(myForm.getType())){
			if(service.isExists(myForm)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.GYGL_WJXX_EXIST));
				return null;
			}
			myForm.setGyjllbdldm(myForm.getJldldm());//model字段和数据库表字段不一致，很坑~~~~~
			myForm.setGyjllbdm(myForm.getJllbdm());
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HashMap<String,String> wjxxMap = service.getOneRsWjxx(myForm);
		request.setAttribute("rs", wjxxMap);
		
		request.setAttribute("jldlList", service.getJldlList(request));
		request.setAttribute("jllbList", new ArrayList<HashMap<String,String>>());
		//设置学年学期列表
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("gyjlxxwhXg");
	}
	
	/**
	 * 删除公寓纪律信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-公寓纪律-公寓纪律信息维护-删除PK:{values}")
	public ActionForward gyjlSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			// 删除
			int num = service.runDelete(values.split(","));
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", num + "");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	/**
	 * 
	 * @描述:查看公寓纪律信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-12-3 下午04:06:10
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
	public ActionForward gyjlxxwhCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxwhForm myForm = (GyjlxxwhForm) form;
		HashMap<String,String> wjxxMap = service.getOneRsWjxx(myForm);
		request.setAttribute("rs", wjxxMap);
		request.setAttribute("rsArrList", service.getWjxxList(myForm));//历史违纪信息
		return mapping.findForward("gyjlxxwhCk");
	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问公寓管理-公寓纪律-公寓纪律信息维护:XH:{xh},XN:{xn},XQ:{xq},JLDLDM:{jldldm},JLLBDM:{jllbdm},WJSJ:{wjsj}")
	public ActionForward saveWjxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyjlxxwhForm myForm = (GyjlxxwhForm) form;
		User user =getUser(request);
		myForm.setCzr(user.getUserName());
		String objStr = request.getParameter("objStr");
		List<GyjlxxwhForm> wjxxList = JsonUtil.jsonArrToList(objStr,	GyjlxxwhForm.class);
		boolean result = service.saveWjxx(myForm,wjxxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:查询违纪学生List
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-12-3 上午11:48:38
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
	@SystemAuth(url = url)
	public ActionForward getWjxsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyjlxxwhForm model = (GyjlxxwhForm) form;
		String cxzd = request.getParameter("cxzd");//查询字段
		String sftq = request.getParameter("sftq");//是否同寝室
		String qsxx = request.getParameter("qsxx");
		String xhArr = request.getParameter("xhArr");//已添加的学生
		List<HashMap<String,String>> wjxsList = service.getWjxsList(model,cxzd,qsxx,sftq,xhArr);
		JSONArray dataList = JSONArray.fromObject(wjxsList);
		response.getWriter().print(dataList);
		return null;
	}
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyjlxxwhForm model = (GyjlxxwhForm) form;
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

	
	
}
