package xsgzgl.gygl.zsxxgl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import common.Globals;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.gygl.cwgl.CwglService;

public class ZsxxglAction extends SuperAction {

	private static final String url = "gyglnew_zsxxgl_zsxxgl.do";
	
	/**
	 * 
	 * @描述:批量离校
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-16 上午08:54:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-住宿管理-住宿信息管理-{doType}批量离校LDDM:{lddm}")
	public ActionForward plLx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZsxxglService service = new ZsxxglService();
		User user = getUser(request);
		ZsxxglForm myForm = (ZsxxglForm) form;
		String doType = request.getParameter("doType");

		if ("pllx".equals(doType)) {
			String mess = service.pllx(myForm, user);
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", mess);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
		}
		if (QUERY.equals(doType)) {
			List<HashMap<String, String>> list = service.getKtsList(myForm,
					user);
			JSONArray dataList = JSONArray.fromObject(list);
			response.getWriter().print(dataList);
			return null;
		}

		request.setAttribute("hjrs", service.getHjrs(myForm, user));
		// request.setAttribute("data", list);

		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("tsyyList", service.getTsyyList());

		return mapping.findForward("pllx");
	}

	/**
	 * 住宿信息管理
	 */
	@SystemAuth(url = url)
	@SystemLog(description="访问公寓管理-住宿管理-住宿信息管理-床位对调PK:{primarykey_checkVal}")
	public ActionForward zsxxglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");
		ZsxxglService service = new ZsxxglService();
		User user = getUser(request);

		String doType = request.getParameter("doType");
		ZsxxglForm myForm = (ZsxxglForm) form;

		if ("cwdd".equals(doType)) {// 床位对调
			HttpSession session = request.getSession();
			myForm.setCzr(session.getAttribute("userName").toString());// 设置操作人
			String[] pk = request.getParameterValues("primarykey_checkVal");
			String message = service.cwdd(pk, myForm) ? "对调成功！" : "对调失败！";

			request.setAttribute("message", message);
		}

		// 如果是辅导员，判断床位是否在可操作时间范围
		request.setAttribute("rzyylist", service.getRzyyList());
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		if (fdyqx || bzrqx) {
			boolean czkz = service.getCzkz();
			request.setAttribute("czkz", czkz);
		} else {
			request.setAttribute("czkz", true);
		}

		// 学校用户显示[批量离校退宿]
		if (!fdyqx && !bzrqx && ("xx".equals(user.getUserType()) || "admin".equals(user.getUserType()))) {

			request.setAttribute("pllxtx", "true");
		}

		request.setAttribute("rs", service.queryCw(myForm, request));
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("num", service.getYrzrs(myForm, request));
		request.setAttribute("searchTjstr", service.getSearchTjstr(myForm,
				request));

		// write和titile
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");
		FormModleCommon.commonRequestSet(request);

		request.setAttribute("topTr", service.getTopTr("zsgl"));
		request.setAttribute("tableName", "xg_gygl_new_cwxxb"); // 导出视图

		request.setAttribute("pageSize", myForm.getPages().getPageSize());

		FormModleCommon.commonRequestSet(request);
		if (Globals.XXDM_NTZYDX.equals(Base.xxdm)) {
			request.setAttribute("path_dc", "gyglnew_zsxxgl_zsxxgl_ntzydx.do");
		}
		if (Globals.XXDM_zjgmzyjsxy.equals(Base.xxdm)) {
			request.setAttribute("path_dc", "gyglnew_zsxxgl_zsxxgl_zjgmzy.do");
		} else {
			request.setAttribute("path_dc", "gyglnew_zsxxgl_zsxxgl.do");
		}

		return mapping.findForward("zsxxglManage");
	}

	@SystemAuth(url = url)
	public ActionForward geExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZsxxglService service = new ZsxxglService();
		ZsxxglForm myForm = (ZsxxglForm) form;
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");//公寓管理员数据范围过滤
		File file = service.getExportData(myForm, request);
		//为空判断，如果获取的file为空，则进行重定向，回到主页面，防止页面报错或者空白
		if(file != null){
			FileUtil.outputExcel(response, file);
			return null;
		}else{
		   response.sendRedirect("gyglnew_zsxxgl_zsxxgl.do");
		}
		return null;
	}

	/**
	 * 
	 * @描述:床位住宿管理床位对调
	 * @作者：dlq
	 * @日期：2013-8-22 下午05:54:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-住宿管理-住宿信息管理-床位对调PK:{idList}")
	public ActionForward zsxxCwdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZsxxglService service = new ZsxxglService();

		String doType = request.getParameter("doType");
		ZsxxglForm myForm = (ZsxxglForm) form;

		String idList = request.getParameter("idList");
		idList.substring(0, idList.length() - 1);
		request.setAttribute("idList", idList);
		request.setAttribute("rzyylist", service.getRzyyList());
		if ("cwdd".equals(doType)) {// 床位对调
			HttpSession session = request.getSession();
			myForm.setCzr(session.getAttribute("userName").toString());// 设置操作人
			String[] pk = idList.split(",");
			String message = service.cwdd(pk, myForm) ? "对调成功！" : "对调失败！";
			Map<String, String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
			return null;
			// request.setAttribute("message", message);
		}
		return mapping.findForward("zsxxCwdd");
	}

	/**
	 *住宿信息管理自定义导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward zsxxglExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ZsxxglService service = new ZsxxglService();
		ZsxxglForm model = (ZsxxglForm) form;
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String, String>> resultList = service.queryExportCw(model,
				request);
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

	/**
	 * 导出床头卡
	 */
	@SystemAuth(url = url)
	public ActionForward expCtk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");
		ZsxxglService service = new ZsxxglService();
		String path = servlet.getServletContext().getRealPath(
				"/print/gygl/gygl_exp_ctk.xls");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expCtk((ZsxxglForm) form, request, response, path);
		return null;
	}

	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward rzyyZjxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 操作
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		if (UPDATE.equalsIgnoreCase(doType)) {
			String jldldm = request.getParameter("rzyydm");
			String jldlmc = request.getParameter("rzyymc");
			request.setAttribute("rzyydm", jldldm);
			request.setAttribute("rzyymc", jldlmc);
		}

		return mapping.findForward("rzyyZj");
	}

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：xucy[工号：754]
	 * @日期：2013-8-29 上午09:22:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-住宿管理-住宿信息管理-{doType}维护LDDM:{lddm};访问公寓管理-住宿管理-住宿管理代码维护-维护{doType}-RZYYDM：{rzyydm},RZYYMC:{rzyymc}")
	public ActionForward zsxxgldmManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 操作
		request.setAttribute("path", "gyglnew_zsxxgl_zsgldmgl.do");
		String doType = request.getParameter("doType");
		ZsxxglForm myForm = (ZsxxglForm) form;
		ZsxxglService service = new ZsxxglService();

		if (!Base.isNull(doType)) {
			String message = "参数错误！";
			if ("add".equals(doType)) {// 增加
				message = service.saveRzyy(myForm, "add");
				// String message = flag ? "修改成功!":"修改失败!";
				Map<String, String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map);
				response.getWriter().print(json);
				return null;
			} else if ("update".equals(doType)) {// 修改
				message = service.saveRzyy(myForm, "update");
				Map<String, String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map);
				response.getWriter().print(json);
				return null;
			} else if ("del".equalsIgnoreCase(doType)) {// 删除
				message = service.saveRzyy(myForm, "delete");
			}
			request.setAttribute("message", message);
		}

		List<String[]> rs = service.getRzyyList(myForm);

		request.setAttribute("rs", rs);
		request.setAttribute("topTr", service.getTopTr("rzyy"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zsxxgldmManage");
	}
	/**
	 * @描述: 离校待退宿学生信息
	 * @作者：孟威[工号：1186]
	 * @日期：2015-12-29 上午09:17:05
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
	public ActionForward pllxview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZsxxglForm model = (ZsxxglForm) form;
		ZsxxglService service = new ZsxxglService();
		String nj = request.getParameter("nj");
		request.setAttribute("nj", nj);
		String xydm = request.getParameter("xydm");
		request.setAttribute("xymc", service.getXymc(xydm));
		String zydm = request.getParameter("zydm");
		request.setAttribute("zymc", service.getZymc(zydm));
		User user = getUser(request);
		if (QUERY.equals(model.getType())){
			//查询
			List<HashMap<String,String>> resultList = service.getLxxsList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("pllxview");
	}
	
	/**
	 * 
	 * @描述: 学生违纪详细信息
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-2-25 上午10:16:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public ActionForward wjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZsxxglForm model = (ZsxxglForm) form;
		ZsxxglService service = new ZsxxglService();
		String xh = request.getParameter("xh");
		request.setAttribute("xh", xh);
		User user = getUser(request);
		if (QUERY.equals(model.getType())){
			//查询
			List<HashMap<String,String>> resultList = service.getXswjxx(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return mapping.findForward("xswjxxView");
	}
	
	/**
	 * @描述:修改备注（单个）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月29日 上午11:59:55
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
	public ActionForward updateBz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//主键
		String pkValue = request.getParameter("pkValue");
		CwglService service = new CwglService();
		// 寝室详细信息
		request.setAttribute("rs", service.getCwForPk(pkValue));
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("updateBz");
	}
	
	/**
	 * @描述:修改备注（批量）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月29日 下午12:00:17
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
	public ActionForward updateBzBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValues = request.getParameter("pkValues");
		request.setAttribute("pkValues", pkValues);
		return mapping.findForward("updateBzBatch");
	}
	
	/**
	 * @描述:批量保存备注的修改
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月29日 下午1:57:09
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
	public ActionForward saveBzBatchForUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValues = request.getParameter("pkValues");
		String bz = request.getParameter("bz");
		ZsxxglService service = new ZsxxglService();
		boolean result = service.saveBzBatchForUpdate(pkValues,bz);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-10-23 下午02:07:12
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
	public ActionForward qshr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZsxxglForm model = (ZsxxglForm) form;
		ZsxxglService service = new ZsxxglService();
		//寝室换人
		boolean result = service.qshr(model.getLddm(),model.getQsh(),model.getCwh(),model.getXh());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 *住宿信息管理自定义导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward zsxxglExportDataForGsjt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ZsxxglService service = new ZsxxglService();
		ZsxxglForm model = (ZsxxglForm) form;
		request.setAttribute("path", "gyglnew_zsxxgl_zsxxgl.do");
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String, String>> resultList = service.queryExportCwForGsjt(model,
				request);
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
