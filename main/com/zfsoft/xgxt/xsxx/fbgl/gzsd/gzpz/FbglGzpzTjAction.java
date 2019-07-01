/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:09:28 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
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
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglGzdmService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:09:28
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglGzpzTjAction extends SuperAction {
	
	private static final String url = "fbglgzsd.do";
	
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		FbglGzpzTjForm myForm = (FbglGzpzTjForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================高级查询相关========================
			CommService cs = new CommService();
			myForm.setSearchModel(cs.getSearchModel(request));
			// ==================高级查询相关 end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		request.setAttribute("path", "fbglgzsd.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fbglgzpztjlb");
	}

	/**
	 * 
	 * @描述:批量删除
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
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
	@SystemLog(description="访问学生信息-分班管理-规则设定-删除VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			Map<String, Object> map = service.delete(values.split(","));
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @描述:显示具体信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-17 下午05:23:05
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		FbglGzpzTjForm myForm = (FbglGzpzTjForm) form;
		Map<String, Object> data = service.getGzpz(myForm.getPzgzid());
		request.setAttribute("data", data);
		return mapping.findForward("fbglgzpztjck");
	}

	/**
	 * 
	 * @描述: 添加
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-3 下午02:39:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-分班管理-规则设定-增加PZGZMC:{pzgzmc},GZDM:{gzdm},SYZT:{syzt}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		FbglGzpzTjForm myForm = (FbglGzpzTjForm) form;
		if (QUERY.equals(myForm.getType())) {
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		// 规则类型
		FbglGzdmService fgs = new FbglGzdmService();
		request.setAttribute("gzlist", fgs.getGzList());
		this.saveToken(request);
		return mapping.findForward("fbglgzpztjzj");
	}

	/**
	 * 
	 * @描述: 修改
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-3 下午02:39:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-分班管理-规则设定-修改PZGZID:{pzgzid},PZGZMC:{pzgzmc},GZDM:{gzdm},SYZT:{syzt}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		FbglGzpzTjForm myForm = (FbglGzpzTjForm) form;
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		FbglGzpzTjForm model = service.getModel(request.getParameter("pzgzid"));
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		FbglGzdmService fgs = new FbglGzdmService();
		Map<String, Object> data = service.getGzpzForUpdate(myForm.getPzgzid());
		request.setAttribute("data", data);
		// 规则类型
		request.setAttribute("gzlist", fgs.getGzList());
		return mapping.findForward("fbglgzpztjxg");
	}

	/**
	 * 
	 * @描述: 更新启用状态
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-3 下午02:40:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-分班管理-规则设定-更新启用状态VALUES:{values}")
	public ActionForward updateQyzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String[] ids = values.split(",");
		boolean result = false;
		for (String pzgzid : ids) {
			FbglGzpzTjForm myForm = (FbglGzpzTjForm) form;
			String qyzt = request.getParameter("qyzt");
			myForm.setQyzt(qyzt);
			myForm.setPzgzid(pzgzid);
			FbglGzpzTjServer service = new FbglGzpzTjServer();
			result = service.runUpdate(myForm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述: 复制规则
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-17 上午08:59:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-分班管理-规则设定-复制规则PZGZID:{pzgzid}")
	public ActionForward copy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pzgzid = request.getParameter("pzgzid");
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		boolean result = service.copy(pzgzid);
		String messageKey = result ? MessageKey.SYS_COPY_SUCCESS
				: MessageKey.SYS_COPY_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @描述: 此类型规则是否已有启用的
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-17 上午09:09:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward sfQy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		String gzdm = request.getParameter("gzdm");
		boolean sfqy = service.sfQy(gzdm);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", sfqy);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}

	/**
	 * 
	 * @描述: 获取规则类型
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-26 上午10:55:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward getGzlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pzgzid = request.getParameter("pzgzid");
		FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
		response.getWriter().print(
				JSONArray.fromObject(fgtxs.getGzpzTjxxLx(pzgzid)));
		return null;
	}

	/**
	 * 
	 * @描述: 获取预览
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-26 上午10:55:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward getPreview(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pzgzid = request.getParameter("pzgzid");
		String tjgzid = request.getParameter("tjgzid");
		FbglGzpzTjServer service = new FbglGzpzTjServer();
		response.getWriter().print(
				JSONArray.fromObject(service.getGzStr(pzgzid, tjgzid)));
		return null;
	}

	/**
	 * 
	 * @描述: 获取编班规则
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-2 下午02:09:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	public ActionForward getGzxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		String gzdm = request.getParameter("gzdm");
		response.getWriter().print(
				JSONArray.fromObject(fgts.getYqyTjnrList(gzdm)));
		return null;
	}
}
