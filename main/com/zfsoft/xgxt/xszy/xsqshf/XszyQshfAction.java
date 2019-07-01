/**
 * @部门:学工产品事业部
 * @日期：2015-2-6 下午04:35:38 
 */
package com.zfsoft.xgxt.xszy.xsqshf;

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
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 新生之友管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-2-6 下午04:35:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XszyQshfAction extends SuperAction<XszyQshfForm, XszyQshfService> {
	private XszyQshfService service = new XszyQshfService();

	private static final String url = "xszy_xsqshf.do";
	
	/**
	 * 
	 * @描述:寝室划分信息列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-6 下午05:21:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getQshfxxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm model = (XszyQshfForm) form;
		User user = getUser(request);
		if (!"xy".equalsIgnoreCase(user.getUserStatus())&&!"xx".equalsIgnoreCase(user.getUserStatus())) {
			String msg = "该模块仅允许院系或校级用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
	}
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			if (null == model.getNj()) {
				model.setNj(Base.currNd);
			}
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "xszy_xsqshf.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qshfxxList");
	}

	/**
	 * 
	 * @描述:分配操作（手动分配或调整）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-9 下午02:57:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward fpcz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm myForm = (XszyQshfForm) form;
		if (UPDATE.equals(myForm.getType())) {
			XszyQshfForm model = service.getModel(myForm.getId());
			if (null != model) {
				BeanUtils.copyProperties(myForm, model);
				request.setAttribute("model", model);
			}
		}
		List<HashMap<String, String>> xyList = service.getXyList();
		request.setAttribute("xyList", xyList);
		return mapping.findForward("fpcz");
	}

	/**
	 * 
	 * @描述:寝室分配保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-9 下午04:30:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editFpcz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm model = (XszyQshfForm) form;
		User user = getUser(request);
		if(null==model.getNj()){
			model.setNj(Base.currNd);
		}
		boolean result = service.editFpcz(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:批量分配
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-10 下午02:39:04
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
	public ActionForward plfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm myForm = (XszyQshfForm) form;
		HashMap<String,String> qssMap = service.getQss(myForm);
		request.setAttribute("qssMap", qssMap);
		List<HashMap<String, String>> xyList = service.getXyList();
		request.setAttribute("xyList", xyList);
		return mapping.findForward("plfp");
	}
	/**
	 * 
	 * @描述:批量分配保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-10 下午02:20:28
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
	public ActionForward savePlfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm model = (XszyQshfForm) form;
		User user = getUser(request);
		boolean result = service.savePlfp(model,user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述:寝室分配取消
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-10 上午11:04:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward qxFp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_CANCEL_SUCCESS_NUM, new Object[]{num,ids.length-num}) : MessageUtil
					.getText(MessageKey.SYS_CANCEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @描述:寝室信息查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-10 上午11:04:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward qsxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm myForm = (XszyQshfForm) form;
		if(null==myForm.getNj()||"null".equals(myForm.getNj())){
			myForm.setNj(Base.currNd);
		}
		HashMap<String, String> qsxxMap = service.getQsxx(myForm);
		/*考虑到新生之友信息管理点击【匹配寝室】出现undefind情况，这里直接在SQL里面查询出来*/
		/*qsxxMap.put("sfhhqs", myForm.getSfhhqs());*/
		request.setAttribute("qsxxMap", StringUtils.formatData(qsxxMap));
		List<HashMap<String, String>> rzxsList = service.getRzxsList(myForm);
	    request.setAttribute("rzxsList", rzxsList);
		return mapping.findForward("qsxxView");
	}
	/**
	 * 
	 * @描述:入住学生信息查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-11 上午08:48:35
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
	public ActionForward rzxsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszyQshfForm myForm = (XszyQshfForm) form;
		if(null==myForm.getNj()){
			myForm.setNj(Base.currNd);
		}
		HashMap<String, String> qsxxMap = service.getQsxx(myForm);
		request.setAttribute("qsxxMap", StringUtils.formatData(qsxxMap));
		List<HashMap<String, String>> rzxsList = service.getRzxsList(myForm);
	    request.setAttribute("rzxsList", rzxsList);
		return mapping.findForward("rzxsView");
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:分配详情查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-11 上午09:53:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward fpxqCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XszyQshfForm myForm = (XszyQshfForm) form;
		User user = getUser(request);
		if(null==myForm.getNj()){
			myForm.setNj(Base.currNd);
		}
		HashMap<String, String> qsxxAllMap = service.getQsxxAll(myForm);
		List<HashMap<String, String>> fpXqList = service.getFpxq(myForm,user);
		request.setAttribute("qsxxAllMap", StringUtils.formatData(qsxxAllMap));
		request.setAttribute("nj", Base.currNd);
		request.setAttribute("fpXqList", fpXqList);
		return mapping.findForward("fpxqCk");
		
	}
	/**
	 * 
	 * @描述:学园分配统计
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-22 下午01:50:31
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
	public ActionForward fptjCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XszyQshfForm myForm = (XszyQshfForm) form;
		User user = getUser(request);
		if(null==myForm.getNj()){
			myForm.setNj(Base.currNd);
		}
		HashMap<String, String> qsxxAllMap = service.getQsxxAll(myForm);
		List<HashMap<String, String>> fpTjList = service.getFptj(myForm,user);
		request.setAttribute("qsxxAllMap", StringUtils.formatData(qsxxAllMap));
		request.setAttribute("nj", Base.currNd);
		request.setAttribute("fpTjList", fpTjList);
		return mapping.findForward("fptjCk");
		
	}

}
