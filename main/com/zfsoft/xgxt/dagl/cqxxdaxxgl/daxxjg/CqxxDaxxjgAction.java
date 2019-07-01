/**
 * @部门:学工产品事业部
 * @日期：2016-8-19 下午05:15:41 
 */
package com.zfsoft.xgxt.dagl.cqxxdaxxgl.daxxjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 重庆信息技术职业学院档案管理模块
 * @类功能描述: 档案结果查询
 * @作者： linguoxia[工号:1553]
 * @时间： 2017-12-29
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CqxxDaxxjgAction extends SuperAction<CqxxDaxxjgForm, CqxxDaxxjgService> {
	private final String RCSW = "rcswrcxw";
	private CqxxDaxxjgService service = new CqxxDaxxjgService();
	private static final String url = "cqxxdaxxjg.do";

	public ActionForward getjglist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CqxxDaxxjgForm model = (CqxxDaxxjgForm) form;
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
		// 默认高级查询条件(当前学年)
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getjglist");
	}

	/**
	 * @描述:导出
	 * @日期：2016-8-25 下午06:51:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CqxxDaxxjgService service = new CqxxDaxxjgService();
		CqxxDaxxjgForm model = (CqxxDaxxjgForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页
		for (HashMap<String, String> hashMap : resultList) {
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(hashMap.get("xh"));
			hashMap.put("xb", xsjbxx.get("xb"));
			hashMap.put("sfzh", xsjbxx.get("sfzh"));
			hashMap.put("zzmmmc", xsjbxx.get("zzmmmc"));
			hashMap.put("lxdh", xsjbxx.get("sjhm"));
		}
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
	 * @描述: 点击学号查看
	 */
	@SystemAuth(url = url)
	public ActionForward dajgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CqxxDaxxjgService service = new CqxxDaxxjgService();
		CqxxDaxxjgForm model = (CqxxDaxxjgForm) form;

		CqxxDaxxjgForm myForm = service.getModel(model);
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSW);
		request.setAttribute("jbxxList", jbxxList);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("dajgView");
	}

}
