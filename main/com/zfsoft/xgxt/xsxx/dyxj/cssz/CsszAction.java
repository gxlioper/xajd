/**
 * @部门:学工产品事业部
 * @日期：2015-6-23 上午08:52:56 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.cssz;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import common.newp.StringUtil;

/** 
 * @类功能描述: 德育小结-参数设置
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-23 上午08:52:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszAction extends SuperAction<CsszModel, CsszService> {

	
	private static final String KGZT_CLOSE = "0";
	
	private static final String url = "xsxx_dyxj_cssz.do";
	
	
	/***德育小结-参数设置***/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		CsszModel csszForm = (CsszModel) form;
		CsszService service = getService();
		if (QUERY.equalsIgnoreCase(csszForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			csszForm.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(csszForm, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszModel model = service.getModel();
	
		if (model != null){
			
			if (StringUtil.isNull(csszForm.getSqkg())){
				csszForm.setSqkg(KGZT_CLOSE);
			}
			
			BeanUtils.copyProperties(csszForm, model);
		} else {
			csszForm.setSqkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// 基本设置中审核流程列表的取值通用方法
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xsxx");
		request.setAttribute("shlcList", shlc);
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);
		
		request.setAttribute("path", "xsxx_dyxj_cssz.do");
		request.setAttribute("sqxnList", Base.getXnndList());
		request.setAttribute("sqxqList", Base.getXqList());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 上午11:25:07
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CsszModel model = (CsszModel) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		CsszService service = getService();

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
