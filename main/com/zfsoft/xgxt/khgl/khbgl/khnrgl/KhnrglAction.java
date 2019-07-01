/**
 * @部门:学工产品事业部
 * @日期：2015-8-13 上午11:36:27 
 */
package com.zfsoft.xgxt.khgl.khbgl.khnrgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.khgl.khbgl.KhbglService;

/**
 * @系统名称: 工作管理系统
 * @模块名称: 考核内容管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-13 上午11:36:27
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KhnrglAction extends SuperAction<KhnrglForm, KhnrglService> {
	private KhbglService khbService = new KhbglService();
	private KhnrglService service = new KhnrglService();
	
	private static final String url = "khgl_khbgl_khbgl.do";

	/**
	 * 
	 * @描述:查询考核内容列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-13 下午01:54:11
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
	public ActionForward getKhnrglList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhnrglForm model = (KhnrglForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			List<HashMap<String, String>> resultList = service.getKhnrList(
					model.getKhbid());
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "khgl_khbgl_khbgl.do");
		FormModleCommon.commonRequestSet(request);
		//考核表信息
		HashMap<String,String> khbMap = khbService.getKhb(model.getKhbid());
		request.setAttribute("khbid", model.getKhbid());
		request.setAttribute("khbMap", StringUtils.formatData(khbMap));
		return mapping.findForward("getKhnrglList");
	}

	/**
	 * 
	 * @描述:考核内容增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-13 下午05:27:51
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
	public ActionForward addKhnr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhnrglForm model = (KhnrglForm) form;
		//默认分值类型：区间
		model.setFzlx("0");
		List<HashMap<String, String>> fzlxList = new OptionUtil().getOptions(OptionUtil.FZLX);// 分值类型
		request.setAttribute("fzlxList", fzlxList);
		request.setAttribute("khbid", model.getKhbid());
		return mapping.findForward("addKhnr");
	}

	/**
	 * 
	 * @描述:修改考核内容
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-13 下午01:55:20
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
	public ActionForward updateKhnr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhnrglForm myForm = (KhnrglForm) form;
		String sfypf=request.getParameter("sfypf");
		KhnrglForm KhnrglForm = service.getModel(myForm);
		if (null != KhnrglForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(KhnrglForm));
			request.setAttribute("KhnrglForm", KhnrglForm);
		}
		List<HashMap<String, String>> fzlxList = new OptionUtil().getOptions(OptionUtil.FZLX);// 分值类型
		
		HashMap<String, String> khnrMap = service.getKhnr(myForm);
		//是否已评分，已评分考核表不能增加、删除不能操作，修改（只能对一级指标、二级指标、考核内容修改）
		request.setAttribute("sfypf", sfypf);
		request.setAttribute("fzlxList", fzlxList);
		request.setAttribute("rs", StringUtils.formatData(khnrMap));
		return mapping.findForward("updateKhnr");
	}

	/**
	 * 
	 * @描述:查看考核内容
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-13 下午01:55:20
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
	public ActionForward viewKhnrgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhnrglForm myForm = (KhnrglForm) form;
		HashMap<String, String> sbjgMap = service.getKhnr(myForm);
		request.setAttribute("rs", StringUtils.formatData(sbjgMap));
		request.setAttribute("path", "xmsbKhnrgl.do?method=viewKhnrgl");
		return mapping.findForward("viewKhnrgl");
	}

	/**
	 * 
	 * @描述:考核内容保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-13 下午05:28:12
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
	public ActionForward saveKhnr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KhnrglForm myForm = (KhnrglForm) form;
		if (service.isHave(myForm)) {
			String message = MessageUtil
					.getText(MessageKey.KHGL_KHBGL_KHNR_USED);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editKhnr(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:删除考核内容
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-13 下午04:08:06
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
	public ActionForward delKhnr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
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
	 * @描述:考核内容维护
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-8-13上午10:32:13
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
	public ActionForward khnrwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			KhnrglForm model = (KhnrglForm) form;
			if (QUERY.equalsIgnoreCase(model.getType())) {
				List<HashMap<String, String>> resultList = service.getKhnrList(
						model.getKhbid());
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			String path = "khgl_Khnrgl_Khnrgl.do";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("khnrwh");
	}
	
	
}
