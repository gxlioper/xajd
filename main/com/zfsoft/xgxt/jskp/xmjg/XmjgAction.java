package com.zfsoft.xgxt.jskp.xmjg;

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
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.jskp.dmwh.DmwhService;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;
import com.zfsoft.xgxt.jskp.lxsq.LxsqService;
import common.newp.StringUtil;

public class XmjgAction extends SuperAction<XmjgForm,XmjgService> {
	private XmjgService service = new XmjgService();
	private final String url = "pjpy_jskp_lxjg.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-14 下午02:03:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getLxjgCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-14 下午02:11:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward searchForJgCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XmjgForm model = (XmjgForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// 查询
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加立项结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-14 下午02:42:03
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
	public ActionForward addLxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//立项时间最大值，取系统当前日期
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		//取联系方式
		String lxfs = "";
		if("stu".equals(user.getUserType())){
			lxfs = new LxsqService().getFzrxxStu(user.getUserName()).get("sjhm");
		}else{
			lxfs = new LxsqService().getFzrxxTea(user.getUserName()).get("lxdh");
		}
		request.setAttribute("lxfs", lxfs);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述: 修改
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-14 下午03:18:01
	 * @修改记录: 修改者名字-修改日期-修改内容A
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward updateLxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XmjgForm model = (XmjgForm)form;
		XmjgForm lxsq = service.getModel(model);
		BeanUtils.copyProperties(model, lxsq);
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//立项时间最大值，取系统当前日期
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		StringBuilder xhforTextarea = new StringBuilder();
	    request.setAttribute("xhs", xhforTextarea.toString());
	    request.setAttribute("bmmc", new LxsqService().getBmmc(lxsq.getZdbm()).get("bmmc"));
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-14 下午03:44:28
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
	public ActionForward saveForLxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XmjgForm lxjg = (XmjgForm)form;
		boolean rs = true;
		try {
			rs = service.saveForLxjg(lxjg);
		} catch (SystemException e) {
			// TODO 自动生成 catch 块
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-14 下午03:49:46
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			//验证是否
			if(!service.checkXmIsNotUserd(ids)){
				response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.DTJS_ZZGXZC_DZBWH_USED,"该项目")));
				return null;
			}
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
	 * 
	 * @描述:查看立项结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-14 下午04:02:02
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
	public ActionForward ckLxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XmjgForm model = (XmjgForm)form;
		XmjgForm lxsq = service.getModel(model);
		BeanUtils.copyProperties(model, lxsq);
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		//立项时间最大值，取系统当前日期
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		List<HashMap<String, String>> xhList = new LxsqService().getXmcyryXhs(lxsq.getXmid());
		request.setAttribute("xhList", xhList);
	    request.setAttribute("bmmc", new LxsqService().getBmmc(lxsq.getZdbm()).get("bmmc"));
	    request.setAttribute("xmlbmc",new DmwhService().getModel(lxsq.getXmlb()).getXmlbmc());
		return mapping.findForward("cklxjg");
	}
	
	/**
	 * 
	 * @描述: 导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-18 上午10:25:23
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
		
		XmjgForm model = (XmjgForm) form;

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
	 * 
	 * @描述: 基本设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-20 下午05:25:42
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
	public ActionForward jbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XmjgForm model = (XmjgForm) form;
		XmjgForm lxjg = service.getModel(model.getXmid());
		BeanUtils.copyProperties(model, lxjg);
		return mapping.findForward("jbsz");
	}
	
	/**
	 * 
	 * @描述: 保存基本设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-20 下午05:31:11
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
	public ActionForward saveJbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XmjgForm model = (XmjgForm) form;
		boolean rs = service.runUpdate(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
