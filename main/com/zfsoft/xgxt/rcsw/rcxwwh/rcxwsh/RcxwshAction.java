/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午08:41:14 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwsh;

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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.gygl.ssyd.ydsh.YdshService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh.RcxwxxwhService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为管理模块
 * @类功能描述: 日常行为审核 
 * @作者： Dlq [工号：995]
 * @时间： 2013-8-5 上午08:41:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwshAction extends SuperAction {
	//定义日常事务中日常行为常量可以从基本信息表中获取学生信息
	private List<HashMap<String,String>> jbxxList = null;
	private BdpzService bdpzService = new BdpzService();
	private static final String RCSWRCXW = "rcswrcxw";
	
	private static final String url = "rcsw_rcxwwh_rcxwsh.do";
	
	/**
	 * 
	 * 日常行为审核查询
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-6 下午06:56:22
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
	public ActionForward rcxwshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwshForm model = (RcxwshForm) form;
		RcxwshService service = new RcxwshService();
		
		
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询获取日常行为审核数据
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_rcxwwh_rcxwsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("rcxwshManage");
	}
	
	
	/**
	 * 
	 * 日常行为单个审核
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-6 下午06:56:51
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为审核-审核ID:{id}")
	public ActionForward rcxwDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwshForm model = (RcxwshForm) form;
		RcxwshService service = new RcxwshService();
		
		if (!StringUtil.isNull(model.getXh())){
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//审核流程信息
			HashMap<String,String> infoList = service.getSplcInfo(model);
			
			// ========== 审核分值设置 begin ============
			ShlcDao shlcDao = new ShlcDao();
			List<HashMap<String, String>> shyjList = shlcDao.getShyjList(model.getId(), "desc");
			String shfz = infoList.get("sqfz");
			if(shyjList.size() > 0){
				HashMap<String, String> shyj = shyjList.get(0);
				shfz = shyj.get("zd2");
			}
			infoList.put("shfz", shfz);
			// ========== 审核分值设置 end ============

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
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		model=service.getModel(model);
		//日常行为大类代码
		model.setRcxwlbdldm(request.getParameter("rcxwlbdldm"));
		model.setShid(request.getParameter("shid"));
		//model.setShid(shid);
		request.setAttribute("model", StringUtils.formatData(model));
		RcxwxxwhService rcxwxxwhService = new RcxwxxwhService();
		List<HashMap<String,String>> xwlbxx = rcxwxxwhService.getXwlbxx(request,model.getRcxwlbdm());
		request.setAttribute("xwlbxx", xwlbxx);
		
		return mapping.findForward("rcxwDgsh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward toPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("toPlsh");
	}
	
	/**
	 * 
	 * 撤销日常行为审核
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
	@SystemLog(description="访问日常事务-日常行为维护-日常行为审核-撤销XXWHID:{xxwhid}")
	public ActionForward cancelRcxwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwshForm model = (RcxwshForm) form;
		String id = request.getParameter("xxwhid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setId(id);
		RcxwshService service = new RcxwshService();
		//User user = getUser(request);
		//撤销日常行为审核
		// ShlcInterface shlc = new CommShlcImpl();
		//boolean isSuccess = service.cancelSh(model, user);
		//boolean isSuccess = shlc.runCancel(user.getUserName(),model.getId(),model.getSplc(),model.getGwid());
		//撤销日常行为审核，最后一级。
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward viewXwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcxwshForm model = (RcxwshForm) form;
		
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (model != null){
			
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			
			RcxwxxwhService service = new RcxwxxwhService();
			//查询单个行为信息结果
			request.setAttribute("rs", service.getOneXwxxList(model.getId()));
			//学生基本信息显示配置
			jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
			request.setAttribute("jbxxList", jbxxList);
			request.setAttribute("model", StringUtils.formatData(model));
			request.setAttribute("shzt", model.getShzt());
			return mapping.findForward("viewXwxx");
		} else {
			return rcxwDgsh(mapping, form, request, response);
		}
		
	}
	
	/**
	 * 自定义导出设置
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-12 下午01:43:26
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
		RcxwshForm model = (RcxwshForm) form;
		//根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
		RcxwshService service = new RcxwshService();
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
	
	/**批量审核**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问日常事务-日常行为维护-日常行为审核-批量审核INFO:{info}")
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String shzt = request.getParameter("shzt");
		String shyj = request.getParameter("shyj");
		String info = request.getParameter("info");
		User user = getUser(request);
		RcxwshService service = new RcxwshService();
		boolean isSuccess = service.plsh(shzt, shyj, info, user);
		
		String messageKey = isSuccess ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
