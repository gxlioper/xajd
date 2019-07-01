/**
 * @部门:学工产品事业部
 * @日期：2017年2月14日 下午3:33:38 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.gprz;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import net.sf.json.JSONArray;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-组织关系转出-改派日志模块
 * @类功能描述: Action
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月14日 下午3:33:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GprzAction extends SuperAction<GprzForm,GprzService>{
	
	private static final String url = "dtjs_gprz.do?method=gprzList";
	private final String ZCSQ ="zcsq";
	
	/**
	 * @描述:查询
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月13日 上午10:39:21
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
	public ActionForward gprzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GprzForm gprzForm = (GprzForm) form;
		GprzService gprzService = new GprzService();
		
		if (QUERY.equals(gprzForm.getType())){
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			gprzForm.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String,String>> resultList = gprzService.getPageList(gprzForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gprzList");
	}
	
	/**
	 * 
	 * @描述:改派日志详情
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月13日 下午4:53:47
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
	public ActionForward gprzShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GprzForm gprzForm = (GprzForm) form;
		GprzService gprzService = new GprzService();
		GprzForm gf = gprzService.getModel(gprzForm);
		BeanUtils.copyProperties(gprzForm,gf);
		String xh = request.getParameter("xh");
		
		if (!StringUtil.isNull(xh)){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//学生基本信息显示配置
		String path = "dtjs_gprz.do?method=xxjgShow";
		request.setAttribute("path", path);
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZCSQ);
		request.setAttribute("jbxxList", jbxxList);
		
		return mapping.findForward("gprzShow");
	}
	
	/**
	 * @描述:导出
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月14日 下午3:59:24
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GprzForm gprzForm = (GprzForm) form;
		GprzService gprzService = new GprzService();
		
		
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		gprzForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = gprzService.getAllList(gprzForm,user);//查询出所有记录，不分页
		
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = gprzForm.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcglbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
}
