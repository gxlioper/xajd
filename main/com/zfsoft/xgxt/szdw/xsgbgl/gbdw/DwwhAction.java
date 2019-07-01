/**
 * @部门:学工产品事业部
 * @日期：2013-8-12 上午10:05:35 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.gbdw;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.szdw.jfxxwh.JfxxForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhService;
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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxDAO;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhDAO;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部管理 队伍维护
 * @作者： zhangjw
 * @时间： 2013-8-12 上午10:05:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DwwhAction  extends SuperAction{

	private static final String  CJFF = "cjff";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "szdw_xsgb_zwlx.do?method=zwlxList";

	private DwwhService service = new DwwhService();
	/**
	 * @描述:队伍结果维护
	 * @作者：zhangjw
	 * @日期：2013-8-12 上午10:13:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward dwwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DwwhForm myForm = (DwwhForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("path", "szdw_xsgb_dwwh.do?method=dwwhList");
		return mapping.findForward("list");
	}
	/**
	 * @描述:
	 * @作者：zhangjw
	 * @日期：2013-8-12 下午2:08:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-学生干部管理-学生干部队伍维护-增加XH:{xh},LXDM:{lxdm},ZWID:{zwid},ZZZT:{zzzt},RZSJ:{rzsj},LZSJ:{lzsj}")
	public ActionForward dwwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DwwhForm myForm = (DwwhForm) form;
//		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			//同一个岗位不允许有两条在岗记录
			/*if("1".equals(myForm.getZzzt())&&service.getZfmcExits(myForm)){
				Map<String, String> map = new HashMap<String, String>();
				map.put("message", "当前职务已有一条在职记录");
				response.getWriter().print(JSONObject.fromMap(map));
				return null;
			}
			myForm.setSqr(user.getUserName());*/

			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//设置学生基本信息
		/*if(user.getUserStatus().equalsIgnoreCase("stu")){
			szXsxx(request,user.getUserName());
		}else{
			szXsxx(request,myForm.getXh());
		}*/
		String path = "szdw_xsgb_dwwh.do?method=dwwhAdd";
		request.setAttribute("path", path);
		request.setAttribute("model", myForm);
		request.setAttribute("xn",Base.currXn);
		return mapping.findForward("add");
	}

	public ActionForward getZwxx(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZwwhService zwwhService = new ZwwhService();
		List<HashMap<String,String>> data = zwwhService.getAllList(new ZwwhForm());
		response.getWriter().print(JSONArray.fromObject(data));
		return null;
	}
	public ActionForward getBgbData(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String bjdm = request.getParameter("bjdm");
		List<HashMap<String,String>> data = service.getBgbData(bjdm);
		response.getWriter().print(JSONArray.fromObject(data));
		return null;
	}

	public ActionForward getXsxxList(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DwwhForm myForm = (DwwhForm) form;
//		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.getXsxxList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_xsgb_dwwh.do?method=getXsxxList");
		request.setAttribute("id",request.getParameter("id"));
		request.setAttribute("bjdm",myForm.getBjdm());
		return mapping.findForward("xsxxList");
	}
	/**
	 * @描述:学生干部队伍维护修改
	 * @作者：zhangjw
	 * @日期：2013-8-12 下午4:24:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-学生干部管理-学生干部队伍维护-修改DWID:{dwid},XH:{xh},LXDM:{lxdm},ZWID:{zwid},ZZZT:{zzzt},RZSJ:{rzsj},LZSJ:{lzsj}")
	public ActionForward dwwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwwhForm myForm = (DwwhForm) form;
//		DwwhForm model = service.getModel(myForm);
//		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			//同一个岗位不允许有两条在岗记录
			boolean result = service.update(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		HashMap<String,String> bjxx = service.getBjxx(myForm.getBjdm());
		request.setAttribute("bjxx", bjxx);
		request.setAttribute("xn",Base.currXn);
		return mapping.findForward("update");
	}
	/**
	 * @描述:学生干部维护队伍查看
	 * @作者：zhangjw
	 * @日期：2013-8-12 下午4:24:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward dwwhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwwhForm myForm = (DwwhForm) form;
		HashMap<String,String> bjxx = service.getBjxx(myForm.getBjdm());
		request.setAttribute("bjxx", bjxx);
		request.setAttribute("xn",Base.currXn);
		return mapping.findForward("view");
	}
	/**
	 * @描述:队伍维护导出
	 * @作者：zhangjw
	 * @日期：2013-8-12 下午4:25:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward dwwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwwhForm myForm=(DwwhForm)form;
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(myForm,user);
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
	/**
	 * @描述:队伍维护删除
	 * @作者：zhangjw
	 * @日期：2013-8-12 下午4:52:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问思政队伍-学生干部管理-学生干部队伍维护-删除DWID:{values}")
	public ActionForward dwwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String values = request.getParameter("values");
		String message = null;
		if (!StringUtil.isNull(values)){
			boolean result = service.batchDelete(values.split(","));
			message = result ? MessageUtil.getText(MessageKey.SYS_DEL_SUCCESS)
					: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
		
	}
	
	@SystemAuth(url = url)
	private void szXsxx(HttpServletRequest request,String xh){
		//查询学生信息
		if(xh != null && !"".equals(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
			request.setAttribute("xh", xh);
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
	}
	public ActionForward dwwhxsList(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DwwhForm myForm = (DwwhForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//查询
			List<HashMap<String,String>> resultList = service.export(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("path", "szdw_xsgb_dwwh.do?method=dwwhxsList");
		return mapping.findForward("dwwhxsList");
	}
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DwwhForm model = (DwwhForm) form;

		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setMaxPage(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.export(model,user);

		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
