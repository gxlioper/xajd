package com.zfsoft.xgxt.rcsw.xsxwkh.pddj;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.rcsw.xsxwkh.cssz.XsxwCsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

public class PddjAction extends SuperAction<PddjForm, PddjService> {
	private final String RCSW="rcswrcxw";
	private PddjService service = new PddjService();
	private static final String url = "xsxwkh_djpd.do";

	/**
	 * 等级评定列表
	 */
	@SystemAuth(url = url)
	public ActionForward getPddjList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PddjForm model = (PddjForm) form;
		XsxwCsszService csszService = new XsxwCsszService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//处罚分提示（学生）
		if("stu".equals(user.getUserType())){
			RcxwjgService rcxwjgService = new RcxwjgService();
			boolean flag = rcxwjgService.getCffWarnStudent(user.getUserName());
			request.setAttribute("flag", flag);
		}
		// 默认高级查询条件(当前学年)
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("csszForm", csszService.getModel());
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getPddjList");
	}
	/**
	 * 
	 * @描述:评定等级（单个）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-5 下午04:56:32
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生行为考核-等级评定-等级评定-评定XH:{xh}")
	public ActionForward pddjSingle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PddjForm model = (PddjForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// 保存单个审核
			boolean result = service.savePddj(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HashMap<String,String> XsxwKhxxMap = service.getXsxwKhxx(model.getJbfid(), model.getXn());
		if (!StringUtil.isNull(XsxwKhxxMap.get("xh"))) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(XsxwKhxxMap.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rs", service.getXsxwKhxx(model.getJbfid(), model.getXn()));
		request.setAttribute("jlfList", service.getJlfList(XsxwKhxxMap.get("xh"), model.getXn()));
		request.setAttribute("cffList", service.getCffList(XsxwKhxxMap.get("xh"), model.getXn()));
		request.setAttribute("fjfList", service.getFjfList(XsxwKhxxMap.get("xh"), model.getXn()));
		return mapping.findForward("pddjSingle");
	}
	public ActionForward pddjView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PddjForm model = (PddjForm) form;
		HashMap<String,String> XsxwKhxxMap = service.getXsxwKhxx(model.getJbfid(), model.getXn());
		if (!StringUtil.isNull(XsxwKhxxMap.get("xh"))) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(XsxwKhxxMap.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rs", service.getXsxwKhxx(model.getJbfid(), model.getXn()));
		request.setAttribute("jlfList", service.getJlfList(XsxwKhxxMap.get("xh"), model.getXn()));
		request.setAttribute("cffList", service.getCffList(XsxwKhxxMap.get("xh"), model.getXn()));
		request.setAttribute("fjfList", service.getFjfList(XsxwKhxxMap.get("xh"), model.getXn()));
		return mapping.findForward("pddjView");
	}
	/**
	 * 
	 * @描述:批量评定等级
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-5 下午05:00:40
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
	@SystemLog(description = "访问学生行为考核-等级评定-等级评定批量-评定XH:{xh}，XN:{xn}")
	public ActionForward pddjPl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PddjForm model = (PddjForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePddjPl(model);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		request.setAttribute("num", request.getParameter("num"));
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("pddjPl");
	}
	
	/**
	 * 等级评定导出
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PddjForm model = (PddjForm) form;
		PddjService service = new PddjService();

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
