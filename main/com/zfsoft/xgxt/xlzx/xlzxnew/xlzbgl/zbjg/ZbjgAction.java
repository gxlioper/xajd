package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbjggl.XxsbjgForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb.ZbsbForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb.ZbsbService;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz.ZqszForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz.ZqszService;
import common.newp.StringUtil;

public class ZbjgAction extends SuperAction<ZbjgForm, ZbjgService> {
	private ZbjgService service = new ZbjgService();
	private final String url = "xg_xlzxnew_zbjg.do";
	/**
	 * 
	 * @描述: 周报上报查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-28 上午09:05:27
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
	public ActionForward getZbjgListCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		User user = getUser(request);
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块仅允许老师访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if(!service.checkIsFdy(user.getUserName())){
			String message = "该模块仅允许带班辅导员访问，请确认！";
			request.setAttribute("message", message);
			return mapping.findForward("error");
		}
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zbjgcx");
	}
	
	/**
	 * 
	 * @描述: 周报上报查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-28 上午10:18:18
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
	public ActionForward searchZbjgCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbjgForm myForm = (ZbjgForm)form;
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
	 * @描述: 周报上报
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午01:49:04
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
	public ActionForward addZbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbjgForm myForm = (ZbjgForm)form;
		User user = getUser(request);
		request.setAttribute("bjList",service.getBjList(user.getUserName()));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		return mapping.findForward("zbjg");
	}
	
	/**
	 * 
	 * @描述: 周报上报修改
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午04:00:29
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
	public ActionForward editZbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbjgForm myForm = (ZbjgForm)form;
		ZbjgForm model = service.getModel(myForm.getSbjgid());
		BeanUtils.copyProperties(myForm, model);
		ZqszForm zbrc = new ZqszService().getModel(model.getSbzbid());
		request.setAttribute("zbrc",zbrc);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zbrc.getXq()));
		request.setAttribute("bjxx", service.getTeaBjxx(model.getBjdm()));
		request.setAttribute("wtryInfo", service.getZbWtryInfo(myForm.getSbjgid()));
		return mapping.findForward("zbjgedit");
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
	public ActionForward saveSbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbjgForm myForm = (ZbjgForm)form;
		String[] xhArray = request.getParameterValues("xh");
		String[] zbwtmsArray = request.getParameterValues("zbwtms");
		myForm.setXhArray(xhArray);
		myForm.setZbwtmsArray(zbwtmsArray);
		myForm.setXh(getUser(request).getUserName());
		ZbjgService tranService = TransactionControlProxy.newProxy(new ZbjgService());
		boolean rs = tranService.saveZbsb(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
	    return null;
	}
	
	
	/**
	 * 
	 * @描述: 查看周报上报
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 上午11:12:26
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
	public ActionForward ckZbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbjgForm myForm = (ZbjgForm)form;
		ZbjgForm model = service.getModel(myForm.getSbjgid());
		BeanUtils.copyProperties(myForm, model);
		ZqszForm zbrc = new ZqszService().getModel(model.getSbzbid());
		request.setAttribute("zbrc",zbrc);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zbrc.getXq()));
		request.setAttribute("bjxx", service.getTeaBjxx(model.getBjdm()));
		request.setAttribute("wtryInfo", service.getZbWtryInfo(myForm.getSbjgid()));
		return mapping.findForward("zbjgview");
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
	 * @描述: 获取周次List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 上午08:56:28
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
	public ActionForward getZcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZbjgForm model = (ZbjgForm) form;
		JSONArray json = JSONArray.fromObject(service.getZcList(model.getXn(), model.getXq())); 
		response.getWriter().print(json);
		
		return null;	
		
	}
	
	/**
	 * 
	 * @描述: 获取起止日期
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 上午08:56:03
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
	public ActionForward getQzrq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZbjgForm model = (ZbjgForm) form;
		JSONArray json = JSONArray.fromObject(service.getQzrq(model.getSbzbid())); 
		response.getWriter().print(json);
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
		
		ZbjgForm model = (ZbjgForm) form;

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
