package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.xssq.XssqForm;
import common.newp.StringUtil;

public class ZqszAction extends SuperAction<ZqszForm, ZqszService> {
	private ZqszService service = new ZqszService();
	private final String url = "xg_xlzxnew_zqrcgl.do";
	/**
	 * 
	 * @描述: 获取周日程List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-14 下午03:37:10
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
	public ActionForward getZrcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zbrc");
	}
	
	/**
	 * 
	 * @描述: 查询周日程
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-14 下午03:42:02
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
	public ActionForward searchZrcCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 获取月日程
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-14 下午03:48:31
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
	public ActionForward getYrcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "xg_xlzxnew_zqrcgl.do");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "xlzxnew_zqrcgl.do?method=getYrcList");
		return mapping.findForward("ybrc");
	}
	
	/**
	 * 
	 * @描述: 查询月日程
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-14 下午03:50:03
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
	public ActionForward searchYrcCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getYbzqList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午02:26:31
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
	public ActionForward addZq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述:修改
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午02:39:23
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
	public ActionForward editZq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		ZqszForm model = service.getModel(myForm.getZbid());
		BeanUtils.copyProperties(myForm, model);
		request.setAttribute("rs",model);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		return mapping.findForward("edit");
	}
	
	/**
	 * 
	 * @描述: 查看周期设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午02:40:55
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
	public ActionForward ckZqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		ZqszForm model = service.getModel(myForm.getZbid());
		request.setAttribute("rs",model);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(model.getXq()));
		return mapping.findForward("ckzqsz");
	}
	
	/**
	 * 
	 * @描述: 删除
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午03:14:01
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
	public ActionForward  delzqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String values = request.getParameter("zbids");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(!service.checkIsZqNotUserd(ids)){
				String message = "有周次正在被是使用，无法删除！";
				response.getWriter().print(getJsonMessage(message));
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
	 * @描述: 保存周期设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午04:27:36
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
	public ActionForward saveZqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		if(!service.checkIsZqMcNotUserd(myForm.getZbid(),myForm.getZbzc(),myForm.getXn(),myForm.getXq())){
			String messageKey = "该学年学期周次已存在！";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		//验证日期是否重叠
		if(!service.checkIsTimeNotRepeat(myForm.getZbksrq(),myForm.getZbjsrq(),myForm.getZbid())){
			String messageKey = "日期与其他周次重叠，请确认！";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		boolean rs = true;
		if(StringUtils.isNotNull(myForm.getZbid())){
			rs = service.runUpdate(myForm);
		}else{
			rs = service.runInsert(myForm);
		}
		String messsageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messsageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 保存月周期设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午04:27:36
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
	public ActionForward saveYzqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		if(!service.checkIsYzqMcNotUsed(myForm.getXn(),myForm.getYf(),myForm.getYbid())){
			String messageKey = "该学年月份已存在！";
			response.getWriter().print(getJsonMessage(messageKey));
			return null;
		}
		boolean rs = true;
		if(StringUtils.isNull(myForm.getYbid())){
			rs = service.saveYzqsz(myForm);
		}else{
			rs = service.updateYzqsz(myForm);
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS :MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 增加月周期设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午04:31:15
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
	public ActionForward addYzqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		myForm.setXn(Base.currXn);
		request.setAttribute("xnList",Base.getXnndList());
		return mapping.findForward("addyzqsz");
	}
	
	/**
	 * 
	 * @描述: 修改月周期设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午04:32:37
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
	public ActionForward editYzqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		ZqszForm model = service.getYzqModel(myForm.getYbid());
		BeanUtils.copyProperties(myForm, model);
		request.setAttribute("xnList",Base.getXnndList());
		return mapping.findForward("edityzqsz");
	}
	
	/**
	 * 
	 * @描述: 查看月周期设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午04:34:34
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
	public ActionForward ckYzqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		ZqszForm model = service.getYzqModel(myForm.getYbid());
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("ckyzqsz");
	}
	
	/**
	 * 
	 * @描述: 查看周日程详细信息(已上报和未上报)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-16 下午04:41:18
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
	public ActionForward ckZqDetailxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		request.setAttribute("sbbjlx", request.getParameter("sbbjlx"));
		request.setAttribute("zbid", request.getParameter("zbid"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ckzqdetailxx");
	}
	
	/**
	 * 
	 * @描述: 查询周期
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-23 上午08:53:55
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
	public ActionForward searchSbxxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZqszForm myForm = (ZqszForm)form;
		String sbbjlx = request.getParameter("sbbjlx");
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getZqsbxxqkCx(myForm, user, sbbjlx);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除月周期设置
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-22 下午02:55:28
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
	public ActionForward delYzqSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			//验证周次是否被使用
			if(service.checkIsYzqNotUserd(ids)){
				String message = "有周次正在被是使用，无法删除！";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			boolean result = service.delYzqSz(ids);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
}
