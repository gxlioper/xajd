package com.zfsoft.xgxt.dtjs.dxbmgl.bmsh;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @功能描述：党校报名审核action
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午03:39:52 
 */
public class DxbmshAction extends SuperAction<DxbmshForm, DxbmshService> {
	private static final String RCSWRCXW = "dtxxXsxxpz";
	/** 
	 * @功能描述：跳转查询页面
	 * @author：杨珩 【1346】
	 * @date：2017-11-7 下午07:16:03 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxbmshCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxbmshService service = new DxbmshService();
		CommService cs = new CommService();
		DxbmshForm myForm = (DxbmshForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================高级查询相关========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("dtjs_dxbmgl_dxbmshCx.do");
			myForm.setSearchModel(searchModel);
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "dtjs_dxbmgl_dxbmshCx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dxbmshCx");
	}
	/** 
	 * @功能描述：跳转审核页面
	 * @author：杨珩 【1346】
	 * @date：2017-11-7 下午07:26:18 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxbmshSh(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxbmshService service = new DxbmshService();
		DxbmshForm myForm = (DxbmshForm) form;
		// 学生信息
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		HashMap<String, String> sqxx=service.getXspxBySqid(myForm.getSqid());
		DxbmshForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// 学生基本信息
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("sqxx", sqxx);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("dxbmshSh");
	}
	/** 
	 * @功能描述：保存审核信息
	 * @author：杨珩 【1346】
	 * @date：2017-11-7 下午07:28:32 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxbmshBc(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxbmshService service = new DxbmshService();
		User user = getUser(request);
		DxbmshForm myForm = (DxbmshForm) form;
		boolean result = service.saveSh(myForm, user);
		response.getWriter().print(result);
		return null;
	}
	/** 
	 * @功能描述：查看审核信息
	 * @author：杨珩 【1346】
	 * @date：2017-11-8 上午09:31:53 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	@SuppressWarnings("deprecation")
	public ActionForward dxbmshCk(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxbmshService service = new DxbmshService();
		DxbmshForm myForm = (DxbmshForm) form;
		DxbmshForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// 学生信息
		if (!StringUtil.isNull(myForm.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMoreForDtgl(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息

		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("dxbmshCk");
	}
	
	/** 
	 * @功能描述：导出
	 * @author：杨珩 【1346】
	 * @date：2017-11-8 上午11:22:54 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxbmshDc(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		DxbmshForm model = (DxbmshForm) form;
		// 根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
		DxbmshService service = new DxbmshService();
		model.setShzt(shlx);
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
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
