/**
 * @部门:学工产品事业部
 * @日期：2015-8-14 下午05:59:40 
 */
package com.zfsoft.xgxt.ystgl.stzhwh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.ystgl.jtgl.ystwh.YstwhForm;
import com.zfsoft.xgxt.ystgl.jtgl.ystwh.YstwhService;
import com.zfsoft.xgxt.ystgl.rtgl.rtjg.YstRtjgForm;
import com.zfsoft.xgxt.ystgl.rtgl.rtjg.YstRtjgService;

import common.newp.StringUtil;

/**
 * @系统名称: 学生工作管理系统
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-8-14 下午05:59:40
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class StzhwhAction extends SuperAction<StzhwhForm, StzhwhService> {
	DAO dao = DAO.getInstance();
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	private final String RTSQ = "rtsq";
	private YstRtjgService ystRtjgService = new YstRtjgService();
	StzhwhService service = new StzhwhService();
	
	private static final String url = "ystgl_ystgl_stzhwh.do";

	/**
	 * 社团综合维护查询
	 */
	@SystemAuth(url = url)
	public ActionForward getStzhwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StzhwhForm model = (StzhwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getStzhwhList(
					model, user);

			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		// searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "ystgl_ystgl_stzhwh.do";
		request.setAttribute("path", path);
		User user = getUser(request);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getStzhwhList");
	}

	// 社团综合维护信息删除
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delStzhwhxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			YstRtjgForm myForm = new YstRtjgForm();
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 社团综合维护信息导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		StzhwhForm model = (StzhwhForm) form;

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

	/**
	 * 社团综合信息查看
	 */
	@SystemAuth(url = url)
	public ActionForward StzhwhCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StzhwhForm myForm = (StzhwhForm) form;
		YstRtjgForm YstRtjg = new YstRtjgForm();
		YstRtjg.setYstid(myForm.getYstid());
		YstwhForm myForm1 = new YstwhForm();
		myForm1.setYstid(myForm.getYstid());
		YstwhService stjg = new YstwhService();
		YstwhForm model = stjg.getModel(myForm1);
		HashMap<String,String> stjgMap = stjg.getYstwh(model);
		request.setAttribute("rs", StringUtils.formatData(stjgMap));
		List<HashMap<String, String>> stcyzhpdList = service.getzhwhList(
				Base.currXn, YstRtjg.getYstid(), "");
		request.setAttribute("zhpdList", stcyzhpdList);
		// 学生基本信息显示配置gzkhKhjgXx
		return mapping.findForward("viewstzhwhck");
	}

	/**
	 * 成员状态维护
	 */
	public ActionForward StCyZtwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StzhwhForm myForm = (StzhwhForm) form;
		YstRtjgForm YstRtjg = new YstRtjgForm();
		YstRtjg.setYstid(myForm.getYstid());
		OptionUtil op = new OptionUtil();
		request.setAttribute("ztwh", op.getOptions("ztwh"));
		YstwhForm myForm1 = new YstwhForm();
		myForm1.setYstid(myForm.getYstid());
		YstwhService stjg = new YstwhService();
		YstwhForm model = stjg.getModel(myForm1);
		HashMap<String,String> stjgMap = stjg.getYstwh(model);
		request.setAttribute("rs", StringUtils.formatData(stjgMap));
		List<HashMap<String, String>> stcyztwhlist = service
				.getStCyZtWhList(myForm.getYstid());
		request.setAttribute("ztwhList", stcyztwhlist);
		// 学生基本信息显示配置gzkhKhjgXx
		return mapping.findForward("viewztwh");
	}

	/**
	 * 保存社团成员状态维护
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveStCyZtwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StzhwhForm myForm = (StzhwhForm) form;
		String ystid = myForm.getYstid();
		String[] rtids = request.getParameterValues("rtid");
		String[] tnzts = request.getParameterValues("tnzt");
		boolean result = true;
		for (int i = 0; i < rtids.length; i++) {
			YstRtjgForm YstRtjg = new YstRtjgForm();
			YstRtjg.setRtid(rtids[i]);
			YstRtjg.setTnzt(tnzts[i]);
			result = ystRtjgService.runUpdate(YstRtjg);
		}
		
		String message = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;

		response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}

	/**
	 * 成员成绩评定
	 */
	public ActionForward StCyCjpd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StzhwhForm myForm = (StzhwhForm) form;
		YstRtjgForm YstRtjg = new YstRtjgForm();
		YstRtjg.setYstid(myForm.getYstid());
		OptionUtil op = new OptionUtil();
		request.setAttribute("cjpdList", op.getOptions("cjpd"));
		YstwhForm myForm1 = new YstwhForm();
		myForm1.setYstid(myForm.getYstid());
		YstwhService stjg = new YstwhService();
		YstwhForm model = stjg.getModel(myForm1);
		HashMap<String,String> stjgMap = stjg.getYstwh(model);
		request.setAttribute("rs", StringUtils.formatData(stjgMap));
		request.setAttribute("currxn", Base.currXn);
		List<HashMap<String, String>> stcyzhpdList = service.getzhwhList(
				Base.currXn, YstRtjg.getYstid(), "正常");
		request.setAttribute("zhpdList", stcyzhpdList);
		// 学生基本信息显示配置gzkhKhjgXx
		return mapping.findForward("viewcjpd");
	}

	/**
	 * 保存社团成员成绩评定
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveStCyCjpd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StzhwhForm myForm = (StzhwhForm) form;
		String stid = myForm.getYstid();
		String[] ids = request.getParameterValues("id");
		String[] cjpds = request.getParameterValues("cjpd");
		String[] xhs = request.getParameterValues("xh");
		String[] rtids = request.getParameterValues("rtid");
		boolean result = true;
		for (int i = 0; i < xhs.length; i++) {
			StzhwhForm stzhwh = new StzhwhForm();
			if (ids != null && !ids[i].equals("") && ids[i] != null) {
				stzhwh.setId(ids[i]);
				stzhwh.setCjpd(cjpds[i]);
				result = service.runUpdate(stzhwh);
			} else {
				stzhwh.setCjpd(cjpds[i]);
				stzhwh.setXh(xhs[i]);
				stzhwh.setXn(Base.currXn);
				stzhwh.setYstid(myForm.getYstid());
				stzhwh.setRtid(rtids[i]);
				result = service.runInsert(stzhwh);
			}
		}
		// 学生基本信息显示配置gzkhKhjgXx
		String message = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;

		response.getWriter().print(getJsonMessageByKey(message));
		return null;
	}

	/**
	 * 社团成员评定成绩查询
	 */
	@SystemAuth(url = "ystglStzhwh.do?method=getStcycjList")
	public ActionForward getStcycjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StzhwhForm model = (StzhwhForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);

			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "ystglStzhwh.do?method=getStcycjList";
		request.setAttribute("path", path);
		User user = getUser(request);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getStcycjList");
	}

	/**
	 * 成绩查询点击学号具体信息查看
	 */
	@SystemAuth(url = url)
	public ActionForward Cycjck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StzhwhForm myForm = (StzhwhForm) form;
		YstRtjgForm ystRtjgForm = new YstRtjgForm();
		ystRtjgForm.setYstid(myForm.getYstid());
		HashMap<String, String> ystxx = ystRtjgService.getYstxxMap(ystRtjgForm);
		request.setAttribute("ystxx", StringUtils.formatData(ystxx));
		List<HashMap<String, String>> cjpdList = service.getCycjlist(myForm
				.getYstid(), myForm.getXh());
		request.setAttribute("cjpdlist", cjpdList);
		// 学生基本信息显示配置gzkhKhjgXx
		return mapping.findForward("viewscjpd");
	}
}
