/**
 * @部门:学工产品事业部
 * @日期：2015-8-10 上午11:36:27 
 */
package com.zfsoft.xgxt.khgl.khdxgl.khdxgl;

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
import com.zfsoft.xgxt.base.util.OptionUtil;

/**
 * @系统名称: 工作管理系统
 * @模块名称: 考核对象管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-10 上午11:36:27
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KhdxglAction extends SuperAction<KhdxglForm, KhdxglService> {
	private KhdxglService service = new KhdxglService();
	
	private static final String url = "khgl_khdxgl_khdxgl.do";

	/**
	 * 
	 * @描述:查询考核对象列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午01:54:11
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
	public ActionForward getKhdxglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhdxglForm model = (KhdxglForm) form;
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
		String path = "khgl_khdxgl_khdxgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getKhdxglList");
	}
	/**
	 * 
	 * @描述:考核对象查看
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-17 上午08:48:09
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
	public ActionForward viewKhdxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhdxglForm model = (KhdxglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getKhdxList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path =null;
		
		if("1".equals(model.getKhlx())){
			path = "khglKhdxgl.do?method=viewKhdxOfStuList";//考核对象为学生
		}else{
			path = "khglKhdxgl.do?method=viewKhdxOfTeaList";//考核对象为教师
		}
		
		request.setAttribute("path", path);
		request.setAttribute("khlx", model.getKhlx());
		request.setAttribute("pflx", model.getPflx());
		request.setAttribute("sfnz", model.getSfnz());
		request.setAttribute("fpzt", model.getFpzt());
		request.setAttribute("khdxid", model.getKhdxid());
		request.setAttribute("pfzid", model.getPfzid());
		request.setAttribute("khdxmc", model.getKhdxmc());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewKhdxList");
	}

	/**
	 * 
	 * @描述:考核对象增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午05:27:51
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
	public ActionForward addKhdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("khlxList", new OptionUtil().getOptions("khlx"));
		return mapping.findForward("addKhdx");
	}

	/**
	 * 
	 * @描述:修改考核对象
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午01:55:20
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
	public ActionForward updateKhdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhdxglForm myForm = (KhdxglForm) form;
		KhdxglForm KhdxglForm = service.getModel(myForm);
		if (null != KhdxglForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(KhdxglForm));
			request.setAttribute("KhdxglForm", KhdxglForm);
		}
		request.setAttribute("sfqy", request.getParameter("sfqy"));
		request.setAttribute("khlxList", new OptionUtil().getOptions("khlx"));
		return mapping.findForward("updateKhdx");
	}

	
	/**
	 * 
	 * @描述:考核对象保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午05:28:12
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
	@SystemLog(description = "访问考核管理-考核对象管理-考核对象管理-增加或修改保存KHDXMC:{khdxmc},KHLX:{khlx},KHDXID:{khdxid}")
	public ActionForward saveKhdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhdxglForm myForm = (KhdxglForm) form;
		if (service.isHave(myForm)) {
			String message = MessageUtil
					.getText(MessageKey.KHGL_KHDXGL_KHDX_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editKhdx(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:删除考核对象
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午04:08:06
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
	@SystemLog(description = "访问考核管理-考核对象管理-考核对象管理-删除VALUES:{values}")
	public ActionForward delKhdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//判断考核对象是否被使用
			if (service.isUsed(values)) {
				String message = MessageUtil.getText(MessageKey.KHGL_KHDXGL_KHDX_USED);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			String message = num>0 ? MessageUtil.getText(
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
	 * @描述:考核对象选择列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午04:43:08
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
	public ActionForward showKhdx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhdxglForm myForm = (KhdxglForm) form;
		String forWardUrl=null;
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getKhdxFpList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		if("1".equals(myForm.getKhlx())){
			request.setAttribute("path", "khglKhdxgl.do?method=viewKhdxOfStuList");//设置路径，只是用于高级查询
			forWardUrl="showStu";
		}else{
			request.setAttribute("path", "khglKhdxgl.do?method=viewKhdxOfTeaList");//设置路径，只是用于高级查询
			forWardUrl="showTea";
		}
		
		request.setAttribute("khdxid", myForm.getKhdxid());
		return mapping.findForward(forWardUrl);
	}
	/**
	 * 
	 * @描述:考核对象分配保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 上午08:55:42
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
	public ActionForward saveKhdxFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhdxglForm model = (KhdxglForm) form;
		boolean result=true;
		String value = request.getParameter("values");
		result=service.saveKhdxFp(model,value);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	/**
	 * 
	 * @描述:考核对象分配保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 上午08:55:42
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
	public ActionForward saveKhdxQxFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhdxglForm model = (KhdxglForm) form;
		boolean result=true;
		String value = request.getParameter("values");
		result=service.saveKhdxQxFp(model,value);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
}
