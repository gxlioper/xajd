/**
 * 
 * @部门:学工产品事业部
 * @日期： 2013-12-18 上午08:51:40 
 * 
 */  
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjsh;

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
 * @模块名称: 火车乘车区间审核管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-24 下午12:55:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 * 
 */
public class HcccqjshAction extends SuperAction {
	//定义火车乘车区间常量可以从基本信息表中获取学生信息
	private List<HashMap<String,String>> jbxxList = null;
	private BdpzService bdpzService = new BdpzService();
	private static final String RCSWXSZBB = "rcswxszbb";
	
	private static final String url = "rcsw_hcyhk_hcccqjsh.do";
	
	/**
	 * 
	 * @描述:TODO(火车乘车区间审核管理模块)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午01:03:12
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
	public ActionForward hcccqjshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjshForm model = (HcccqjshForm) form;
		HcccqjshService service = new HcccqjshService();
		
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
		
		String path = "rcsw_hcyhk_hcccqjsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("hcccqjshManage");
		
	}
	
	/**
	 * 
	 * @描述:TODO(火车乘车区间审核管理模块)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午01:03:48
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
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车乘车区间审核-审核CCQJTXID:{ccqjtxid}")
	public ActionForward hcccqjDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjshForm model = (HcccqjshForm) form;
		HcccqjshService service = TransactionControlProxy.newProxy(new HcccqjshService());
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//审核流程信息
			HashMap<String,String> infoList = service.getHcccqjshInfo(model);
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
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		model=service.getModel(model);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("hcccqjDgsh");
		
	}
	
	/**
	 * 
	 * @描述:TODO(撤销火车乘车区间审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午02:18:47
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
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车乘车区间审核-撤销CCQJTXID:{ccqjtxid}")
	public ActionForward cancelHcccqjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjshForm model = (HcccqjshForm) form;
		String ccqjtxid = request.getParameter("ccqjtxid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setCcqjtxid(ccqjtxid);
		HcccqjshService service = new HcccqjshService();
		//撤销火车乘车区间审核，最后一级。
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午12:52:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 * 
	 */
	@SystemAuth(url = url)
	public ActionForward viewHcccqjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjshForm model = (HcccqjshForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		//加载学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
		request.setAttribute("jbxx", xsjbxx);
		
		HcccqjshService service = new HcccqjshService();
		//查询单个行为信息结果
		request.setAttribute("rs", StringUtils.formatData(service.getHcccqjshInfo(model)));
		//学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(RCSWXSZBB);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("model", StringUtils.formatData(model));
		request.setAttribute("shzt", model.getShzt());
		return mapping.findForward("viewHcccqjsh");
		
	}
	
	/**
	 * 
	 * @描述:TODO(自定义导出设置)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:36:09
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
		HcccqjshForm model = (HcccqjshForm) form;
		//根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
		HcccqjshService service = new HcccqjshService();
		model.setShzt(shlx);
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		
		
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
	 * @日期：2014-4-25 下午02:52:03
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
	@SystemLog(description="访问日常事务-火车优惠卡管理-火车乘车区间审核-批量审核ID:{id}")
	public ActionForward hcccPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HcccqjshForm model = (HcccqjshForm) form;
		HcccqjshService service = TransactionControlProxy.newProxy(new HcccqjshService());
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("hcccPlsh");
	}
	
	
}
