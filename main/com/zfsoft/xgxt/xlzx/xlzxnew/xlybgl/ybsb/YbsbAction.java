package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsb;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg.YbjgService;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz.XlzxSbService;
import common.newp.StringUtil;

public class YbsbAction extends SuperAction<YbsbForm,YbsbService> {
	private final String url = "xg_xlzxnew_ybsb.do";
	private YbsbService service = new YbsbService();
	/**
	 * @throws Exception 
	 * 
	 * @描述: 获取月报结果查询List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午06:37:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getYbsbCxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("cssz", new XlzxSbService().getModel("yb"));
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ybsbcx");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 查询月报
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午06:42:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward seachYbsqCxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		User user = getUser(request);
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加月报结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-5 上午11:25:22
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
	public ActionForward addYbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		User user = getUser(request);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("txr",user.getRealName());
		request.setAttribute("xymc",new YbjgService().getXymc(user.getUserDep()));
		myForm.setXn(Base.currXn);
		myForm.setXydm(user.getUserDep());
		request.setAttribute("xnList",Base.getXnndList());
		return mapping.findForward("addybsb");
	}
	
	/**
	 * 
	 * @描述: 修改月报结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-5 上午11:36:58
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
	public ActionForward editYbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		YbsbForm model = service.getModel(myForm);
		if(model != null){
			BeanUtils.copyProperties(myForm, model);
			request.setAttribute("yf", Integer.valueOf(model.getYf())+"月");
			request.setAttribute("sfywt", model.getSfywt());
			request.setAttribute("xymc",new YbjgService().getXymc(model.getXydm()));
		}
		User user = getUser(request);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("txr",user.getRealName());
		myForm.setTxr(user.getUserName());
		request.setAttribute("wtryInfo", service.getYbWtryInfo(model.getSbid()));
		return mapping.findForward("editybsb");
	}
	
	/**
	 * 
	 * @描述: 查看月报结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-5 上午11:36:58
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
	public ActionForward ckYbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		YbsbForm model = service.getModel(myForm);
		if(model != null){
			BeanUtils.copyProperties(myForm, model);
			request.setAttribute("yf", Integer.valueOf(model.getYf())+"月");
			request.setAttribute("xymc",new YbjgService().getXymc(model.getXydm()));
		}
		User user = getUser(request);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("txr",user.getRealName());
		request.setAttribute("wtryInfo", service.getYbWtryInfo(model.getSbid()));
		return mapping.findForward("ckybsb");
	}
	
	/**
	 * 
	 * @描述: 删除上报结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-2 下午03:01:55
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
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				service.delSbjg(ids);
			}
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
	 * @描述: 导出
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 上午10:33:20
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
		
		YbsbForm model = (YbsbForm) form;

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
	 * @描述: 保存上报数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午04:33:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public ActionForward saveYbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		String[] xhArray = request.getParameterValues("xh");
		String[] ybwtmsArray = request.getParameterValues("ybwtms");
		String[] ybgycsArray = request.getParameterValues("ybgycs");
		String[] ybgyhjgArray = request.getParameterValues("ybgyhjg");
		String[] wtfsrqArray = request.getParameterValues("wtfsrq");
		myForm.setXhArray(xhArray);
		myForm.setYbwtmsArray(ybwtmsArray);
		myForm.setYbgycsArray(ybgycsArray);
		myForm.setYbgyhjgArray(ybgyhjgArray);
		myForm.setTxr(getUser(request).getUserName());
		myForm.setWtfsrqArray(wtfsrqArray);
		YbsbService tranService = new YbsbService();
		boolean rs = true;
		try {
			if ("10704".equals(Base.xxdm)) {
				if ("否".equals(myForm.getSfywt())&&myForm.getSbid()!=null) {//如果 原本有问题，改为没问题，清空总体情况极其原有的个人情况
					rs = tranService.update(myForm);
				}else {
					rs = tranService.saveYbsb(myForm);
				}
			}else {
				rs = tranService.saveYbsb(myForm);
			}
		} catch (SystemException e) {
			// TODO 自动生成 catch 块
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
	    return null;
	}
	
	
	/**
	 * @描述: 提交
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 上午10:10:49
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		String value = request.getParameter("sbsqid");
		myForm.setSbid(value);
		YbsbForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 上午10:28:11
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sqid = request.getParameter("sbsqid");
		String lcid = request.getParameter("splcid");
		// 只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// 更新业务状态为'未提交'
			YbsbForm model = new YbsbForm();
			model.setSbid(sqid);
			model.setSplcid(lcid);
			// 查看是否有退回记录,有：审核状态就为退回
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
