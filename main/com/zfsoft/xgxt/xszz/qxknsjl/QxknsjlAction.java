/**
 * @部门:学工产品事业部
 * @日期：2016-4-20 下午06:31:00 
 */  
package com.zfsoft.xgxt.xszz.qxknsjl;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.bdpz.BdpzService;



/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-4-21 上午08:35:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class QxknsjlAction extends SuperAction<QxknsjlForm, QxknsjlService> {
	
	
	//private static final String QXKNSZG = "qxknszg";
	private static final String KNSRD = "knsrd";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String,String>> jbxxList = null;
	
	private static final String url = "xszz_qxknsjl_cx.do";
	
	public ActionForward qxKnsjlManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QxknsjlForm model = (QxknsjlForm) form;
		QxknsjlService service = new QxknsjlService();		
		if (QUERY.equals(model.getType())){
			
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
			
		}		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});						
		request.setAttribute("searchTj", searchModel);				
		String path = "xszz_qxknsjl_cx.do";		
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qxKnsjlManage");		
		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-25 下午03:34:13
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
	public ActionForward viewQxKnszgjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		
			QxknsjlForm model = (QxknsjlForm) form;
			QxknsjlService service = new QxknsjlService();
			@SuppressWarnings("unused")
			User user = getUser(request);			
			if ("stu".equals(user.getUserType())){
				model.setXh(user.getUserName());
			}
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//查询单个困难生结果
			request.setAttribute("knsjgrs", service.getOneKnsjgList(model.getJgguid()));
			//查询单个行为信息结果
			//request.setAttribute("rs", StringUtils.formatData(service.getHcccqjtxInfo(model)));
			request.setAttribute("knsqxjlrs", service.getOneKnsqxjlList(model.getJgguid()));
			//学生基本信息显示配置
			jbxxList = bdpzService.getJbxxpz(KNSRD);
			request.setAttribute("jbxxList", jbxxList);							
			return mapping.findForward("viewQxKnszgjl");
			
	}
	
	/**
	 * 
	 * 导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QxknsjlForm model = (QxknsjlForm) form;
		QxknsjlService service = new QxknsjlService();

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
