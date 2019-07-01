/**
 * @部门:学工产品事业部
 * @日期：2015-8-11 上午11:36:27 
 */
package com.zfsoft.xgxt.khgl.khbgl;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.khgl.khdxgl.khdxgl.KhdxglService;

/**
 * @系统名称: 工作管理系统
 * @模块名称: 考核表管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-11 上午11:36:27
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KhbglAction extends SuperAction<KhbglForm, KhbglService> {
	private KhdxglService khdxService = new KhdxglService();
	private KhbglService service = new KhbglService();
	
	private static final String url = "khgl_khbgl_khbgl.do";

	/**
	 * 
	 * @描述:查询考核表列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午01:54:11
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
	public ActionForward getKhbglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhbglForm model = (KhbglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_khbgl_khbgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getKhbglList");
	}

	/**
	 * 
	 * @描述:考核表增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午05:27:51
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
	public ActionForward addKhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("khdxList", khdxService.getKhdxList());
		return mapping.findForward("addKhb");
	}

	/**
	 * 
	 * @描述:修改考核表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午01:55:20
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
	public ActionForward updateKhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhbglForm myForm = (KhbglForm) form;
		KhbglForm KhbglForm = service.getModel(myForm);
		if (null != KhbglForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(KhbglForm));
			request.setAttribute("KhbglForm", KhbglForm);
		}
		request.setAttribute("khdxList", khdxService.getKhdxList());
		return mapping.findForward("updateKhb");
	}

	/**
	 * 
	 * @描述:查看考核表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午01:55:20
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
	public ActionForward viewKhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhbglForm myForm = (KhbglForm) form;
		HashMap<String, String> sbjgMap = service.getKhb(myForm.getKhbid());
		request.setAttribute("rs", StringUtils.formatData(sbjgMap));
		return mapping.findForward("viewKhb");
	}

	/**
	 * 
	 * @描述:考核表保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午05:28:12
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
	@SystemLog(description = "访问考核管理-考核表管理-考核表管理-增加或修改保存KHBMC:{khbmc},KHDXID:{khdxid},KHBID:{khbid}")
	public ActionForward saveKhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhbglForm myForm = (KhbglForm) form;
		if (service.isHave(myForm)) {
			String message = MessageUtil
					.getText(MessageKey.KHGL_KHBGL_KHB_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editKhb(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:删除考核表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午04:08:06
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
	@SystemLog(description = "访问考核管理-考核表管理-考核表管理-删除VALUES:{values}")
	public ActionForward delKhb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//判断考核表是否被使用
			String message=service.isUsed(values);
			if (null!=message) {
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			String[] ids = values.split(",");
			boolean result = service.delKhb(ids);
			 message = result? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	

	/**
	 * 
	 * @描述:考核表内容预览
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-8-14上午10:32:13
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
	public ActionForward khnryl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			KhbglForm model = (KhbglForm) form;
			request.setAttribute("khbid", model.getKhbid());
			request.setAttribute("khbmc", model.getKhbmc());
			return mapping.findForward("khnryl");
	}
	/**
	 * 
	 * @描述:考核表选择列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午04:43:08
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
	public ActionForward showKhb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhbglForm myForm = (KhbglForm) form;
		String forWardUrl=null;
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getKhbList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

			forWardUrl="showStu";
		return mapping.findForward(forWardUrl);
	}
	/**
	 * 
	 * @描述:考核表复制
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-14 上午08:55:42
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
	public ActionForward khbfz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhbglForm model = (KhbglForm) form;
		KhbglForm myForm=new KhbglForm();
		boolean result=true;
		if(SAVE.equals(model.getType())){
			myForm.setKhbid(null);
			myForm.setKhbmc(model.getKhbmc());
			if (service.isHave(myForm)) {
				String message = MessageUtil
						.getText(MessageKey.KHGL_KHBGL_KHB_REPEAT);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
		result=service.khbfz(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		}
		request.setAttribute("khbid", model.getKhbid());
		return mapping.findForward("khbfz");
	}
	/**
	 * 
	 * @描述:考核表启用状态设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-14 上午11:55:25
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
	public ActionForward qySz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String sfqy = request.getParameter("sfqy");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			boolean result = service.qySz(ids,sfqy);
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_SAVE_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_SAVE_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} 
		return null;
	}
	
}
