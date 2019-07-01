/**
 * @部门:学工产品事业部
 * @日期：2015-8-15 上午11:36:27 
 */
package com.zfsoft.xgxt.khgl.khxmgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.zfsoft.xgxt.base.util.JsonUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.khgl.khbgl.KhbglService;
import com.zfsoft.xgxt.khgl.khdxgl.khdxgl.KhdxglService;
import com.zfsoft.xgxt.khgl.khdxgl.pfzgl.PfzglService;

/**
 * @系统名称: 工作管理系统
 * @模块名称: 考核项目管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-15 上午11:36:27
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KhxmglAction extends SuperAction<KhxmglForm, KhxmglService> {
	private KhdxglService khdxService = new KhdxglService();
	private KhxmglService service = new KhxmglService();
	
	private static final String url = "khgl_khxmgl_khxmgl.do";

	/**
	 * 
	 * @描述:查询考核项目列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午01:54:11
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
	public ActionForward getKhxmglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhxmglForm model = (KhxmglForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			List<HashMap<String, String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "khgl_khxmgl_khxmgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getKhxmglList");
	}

	/**
	 * 
	 * @描述:考核项目增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午05:27:51
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
	public ActionForward addKhxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhxmglForm model = (KhxmglForm) form;
		request.setAttribute("khdxList", khdxService.getKhdxList());
		return mapping.findForward("addKhxm");
	}

	/**
	 * 
	 * @描述:修改考核项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午01:55:20
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
	public ActionForward updateKhxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhxmglForm myForm = (KhxmglForm) form;
		KhxmglForm KhxmglForm = service.getModel(myForm);
		if (null != KhxmglForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(KhxmglForm));
			request.setAttribute("KhxmglForm", KhxmglForm);
		}
		request.setAttribute("khdxList", khdxService.getKhdxList());
		return mapping.findForward("updateKhxm");
	}

	/**
	 * 
	 * @描述:查看考核项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午01:55:20
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
	public ActionForward viewKhxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhxmglForm myForm = (KhxmglForm) form;
		HashMap<String, String> khxmMap = service.getKhxm(myForm.getXmid());
		List<HashMap<String,String>>  pfdxList = service.getpfdxXxList(myForm.getXmid());
		request.setAttribute("rs", StringUtils.formatData(khxmMap));
		request.setAttribute("pfdxList", pfdxList);
		return mapping.findForward("viewKhxm");
	}

	/**
	 * 
	 * @描述:考核项目保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午05:28:12
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
	@SystemLog(description = "访问考核管理-考核项目管理-考核项目管理-增加或修改保存XMMC:{xmmc},KHDXID:{khdxid},KSSJ:{kssj},JSSJ:{jssj},XMID:{xmid}")
	public ActionForward saveKhxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhxmglForm myForm = (KhxmglForm) form;
		if (service.isHave(myForm)) {
			String message = MessageUtil
					.getText(MessageKey.KHGL_KHXMGL_KHXM_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editKhxm(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:删除考核项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午04:08:06
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
	@SystemLog(description = "访问考核管理-考核项目管理-考核项目管理-删除VALUES:{values}")
	public ActionForward delKhxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String message = null;
		if (!StringUtil.isNull(values)) {
			//判断考核表是否被使用
			 message=service.isUsed(values);
			if (null!=message) {
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			String[] ids = values.split(",");
			boolean result = service.delKhxm(ids);
			 message = result ? MessageUtil.getText(
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
	 * @描述:考核项目选择列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午04:43:08
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
	public ActionForward showKhxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhxmglForm myForm = (KhxmglForm) form;
		String forWardUrl=null;
		
			forWardUrl="showStu";
		return mapping.findForward(forWardUrl);
	}
	/**
	 * 
	 * @描述:评分对象设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 上午11:50:20
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
	public ActionForward pfdxSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
			KhxmglForm model = (KhxmglForm) form;
			HashMap<String,String> khxmMap=service.getKhxm(model.getXmid());
			List<HashMap<String,String>>  pfdxList = service.getpfdxXxList(model.getXmid());
			List<HashMap<String,String>>  pfzList = new PfzglService().getPfzList(model.getKhdxid());
			for (int i = 0; i < pfdxList.size(); i++) {
				request.setAttribute("pffwList"+i, service.getPffwList(pfdxList.get(i).get("pfzid"),khxmMap.get("khlx")));
			}
			request.setAttribute("jsfsList", new OptionUtil().getOptions(OptionUtil.JSFS));
			request.setAttribute("pfzList",pfzList );
			request.setAttribute("khbList", new KhbglService().getKhbList(khxmMap.get("khdxid")));
			request.setAttribute("pffwList", service.getPffwList(pfzList.get(0).get("pfzid"),khxmMap.get("khlx")));
			
			request.setAttribute("pfdxList", pfdxList);
			request.setAttribute("rs", StringUtils.formatData(khxmMap));
			return mapping.findForward("pfdxSz");
	}
	/**
	 * 
	 * @描述:加载评分范围
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午04:51:09
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
	public ActionForward getPffw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			KhxmglForm model = (KhxmglForm) form;
			String khlx = request.getParameter("khlx");
			List<HashMap<String,String>> sbflList = service.getPffwList(model.getPfzid(),khlx);
			JSONArray dataList = JSONArray.fromObject(sbflList);
			response.getWriter().print(dataList);
			return null;
	}
	/**
	 * 
	 * @描述:评分对象设置保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午05:46:30
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
	public ActionForward savePfdxSz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KhxmglForm model = (KhxmglForm) form;
		String pfdxJson = request.getParameter("pfdxJson");
		
		List<KhxmglForm> pfdxList = JsonUtil.jsonArrToList(pfdxJson,KhxmglForm.class);
		boolean result = service.savePfdxSz(model,pfdxList);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
