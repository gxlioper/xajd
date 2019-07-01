/**
 * @部门:学工产品事业部
 * @日期： 2013-12-18 上午08:51:40 
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsh;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办管理模块
 * @类功能描述: TODO(学生证补办-补办审核) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-18 上午08:51:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxshAction extends SuperAction {
	//定义学生证补办中学生证补办常量可以从基本信息表中获取学生信息
	private List<HashMap<String,String>> jbxxList = null;
	private BdpzService bdpzService = new BdpzService();
	private static final String DXSYLBX = "dxsylbx";
	
	private static final String url = "rcsw_dxsylbx_ylbxsh.do";
	
	/**
	 * 
	 * @描述:TODO(医疗保险审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-9 下午02:20:24
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
	public ActionForward ylbxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxshForm model = (YlbxshForm) form;
		YlbxshService service = new YlbxshService();
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询获取学生证补办审核数据
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_dxsylbx_ylbxsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ylbxshManage");
	}
	
	/**
	 * 
	 * @描述:TODO(医疗保险单个审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-9 下午04:04:49
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险审核-审核YLSQID:{ylsqid}")
	public ActionForward ylbxDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxshForm model = (YlbxshForm) form;
		YlbxshService service = TransactionControlProxy.newProxy(new YlbxshService());
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			//审核流程信息
			HashMap<String,String> infoList = service.getYlbxshInfo(model);
			request.setAttribute("rs", StringUtils.formatData(infoList));
		}
		if (SAVE.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			//保存单个审核
			boolean result = service.saveSh(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(DXSYLBX);
		request.setAttribute("jbxxList", jbxxList);
		model=service.getModel(model);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", StringUtils.formatData(model));
		if(!"".equals(model.getCzqebzdm())&& model.getCzqebzdm() != null){
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", service.getCzqebzrymcList(czqebzdms));
		}
		if(!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null){
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", service.getCbzkdmcsList(cbzkdms));
		}
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		return mapping.findForward("ylbxDgsh");
		
	}
	
	/**
	 * 
	 * @撤销医疗保险单个审核
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-6 下午06:57:12
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险审核-撤销YLSQID:{ylsqid}")
	public ActionForward cancelYlbxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxshForm model = (YlbxshForm) form;
		String ylsqid = request.getParameter("ylsqid");
		model.setYlsqid(ylsqid);
		YlbxshService service = new YlbxshService();
		//撤销日常行为审核，最后一级。
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward viewYlbxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxshForm model = (YlbxshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		YlbxshService service = new YlbxshService();
		
		//查询单个行为信息结果
		request.setAttribute("rs", StringUtils.formatData(service.getYlbxshInfo(model)));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(DXSYLBX);
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		request.setAttribute("model", model);
		request.setAttribute("shzt", model.getShzt());
		
		YlbxshService ylbxshService = new YlbxshService();
		if(!"".equals(model.getCzqebzdm())&& model.getCzqebzdm() != null){
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", ylbxshService.getCzqebzrymcList(czqebzdms));
		}
		if(!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null){
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", ylbxshService.getCbzkdmcsList(cbzkdms));
		}
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("viewYlbxsh");
		
	}
	
	/**
	 * 
	 * @描述:TODO(查看医疗保险单个审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-14 下午04:13:21
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
	public ActionForward viewCbzk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String xm = request.getParameter("xm");
		if("null".equals(xm)){
			xm = "";
		}
		YlbxshForm model = (YlbxshForm) form;
		YlbxshService service = new YlbxshService();
		
		model=service.getModel(model);
		request.setAttribute("model", StringUtils.formatData(model));
		
		if(!"".equals(model.getCzqebzdm())&& model.getCzqebzdm() != null){
			String[] czqebzdms = model.getCzqebzdm().split(",");
			request.setAttribute("czqebzrymcsList", service.getCzqebzrymcList(czqebzdms));
		}
		if(!"".equals(model.getCbzkdm()) && model.getCbzkdm() != null){
			String[] cbzkdms = model.getCbzkdm().split(",");
			request.setAttribute("cbzkmcsList", service.getCbzkdmcsList(cbzkdms));
		}
		
		request.setAttribute("qtcbzkval",model.getQtcbzkval());
		request.setAttribute("xm",xm);
		return mapping.findForward("viewCbzk");
		
	}
	
	/**
	 * 
	 * @描述:TODO(自定义导出设置)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:27:45
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YlbxshForm model = (YlbxshForm) form;
		//根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
		YlbxshService service = new YlbxshService();
		model.setShzt(shlx);
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getPageList(model,user);//查询出所有记录，不分页
		
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-28 上午09:30:50
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
	@SystemLog(description="访问日常事务-大学生医疗保险-医疗保险审核-批量审核ID:{id}")
	public ActionForward ylbxPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		YlbxshForm model = (YlbxshForm) form;
		YlbxshService service = TransactionControlProxy.newProxy(new YlbxshService());
		
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("ylbxPlsh");
	}
	
	
}
