/**
 * @部门:学工产品事业部
 * @日期：2016-01-07 下午02:24:28 
 */
package com.zfsoft.xgxt.ystgl.dmwh;

import java.net.URLDecoder;
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

import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.xmlbgl.XmlbglService;
/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2016-01-07 下午02:24:28
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YstglDmwhAction extends SuperAction {
	private YstglDmwhService service = new YstglDmwhService();
	private static final String url = "ystgl_jcsz_dmwh.do";
	
	/**
	 * 
	 * @描述:艺术团类别列表
	 * @作者：xiaixa [工号：1104]
	 * @日期：2016-01-07 下午02:38:34
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
	public ActionForward getYstlbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (QUERY.equals(model.getType())) {
			String ystlbmc = request.getParameter("ystlbmc"); 
			if(null!=ystlbmc){
			ystlbmc = URLDecoder.decode(URLDecoder.decode(ystlbmc,"UTF-8"),"UTF-8");
			model.setYstlbmc(ystlbmc);
			}
			List<HashMap<String, String>> resultList = service.getYstlbList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getYstlbList");
	}
	/**
	 * 
	 * @描述:挂靠单位列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-1-14 下午04:09:27
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
	public ActionForward getGkdwList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (QUERY.equals(model.getType())) {
			String ystlbmc = request.getParameter("gkdwmc"); 
			if(null!=ystlbmc){
			ystlbmc = URLDecoder.decode(URLDecoder.decode(ystlbmc,"UTF-8"),"UTF-8");
			model.setYstlbmc(ystlbmc);
			}
			List<HashMap<String, String>> resultList = service.getGkdwList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getGkdwList");

	}

	/**
	 * 
	 * @描述:增加艺术团类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-01-07 下午03:44:04
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
	public ActionForward addYstlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (SAVE.equals(model.getType())) {
			boolean result = service.addYstlb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("addYstlb");
	}

	/**
	 * 
	 * @描述:修改艺术团类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-01-07 下午03:50:04
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
	@SystemLog(description="艺术团管理-代码维护-艺术团类别维护-修改ystlb:{ystlbdm}")
	public ActionForward updateYstlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm myForm = (YstglDmwhForm) form;
		YstglDmwhForm model = service.getYstlb(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.updateYstlb(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateYstlb");
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-01-07 下午04:03:00
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
	@SystemLog(description="艺术团管理-代码维护-艺术团类别维护-删除values:{values}")
	public ActionForward delYstlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//判断艺术团类别是否被占用
			if (service.isUsed(values)) {
				String messageKey = MessageKey.YSTGL_JCSZ_YSTLB_USED;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = service.deleteYstlb(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}
	
	/**
	 * 
	 * @描述:项目类别列表
	 * @作者：xiaixa [工号：1104]
	 * @日期：2016-01-07 下午02:38:34
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
	public ActionForward getXmlbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (QUERY.equals(model.getType())) {
			String Xmlbmc = request.getParameter("Xmlbmc"); 
			if(null!=Xmlbmc){
			Xmlbmc = URLDecoder.decode(URLDecoder.decode(Xmlbmc,"UTF-8"),"UTF-8");
			model.setXmlbmc(Xmlbmc);
			}
			List<HashMap<String, String>> resultList = service.getXmlbList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXmlbList");

	}

	/**
	 * 
	 * @描述:增加项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-01-07 下午03:44:04
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
	public ActionForward addXmlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (SAVE.equals(model.getType())) {
			boolean result = service.addXmlb(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("ystlbList", service.getYstlbListAll());
		return mapping.findForward("addXmlb");
	}

	/**
	 * 
	 * @描述:修改项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-01-07 下午03:50:04
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
	@SystemLog(description="艺术团管理-代码维护-项目类别维护-修改Xmlb:{Xmlbdm}")
	public ActionForward updateXmlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm myForm = (YstglDmwhForm) form;
		YstglDmwhForm model = service.getXmlb(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.updateXmlb(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		request.setAttribute("ystlbList", service.getYstlbListAll());
		return mapping.findForward("updateXmlb");
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-01-07 下午04:03:00
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
	@SystemLog(description="艺术团管理-代码维护-项目类别维护-删除values:{values}")
	public ActionForward delXmlb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//判断项目类别是否被占用
			if (service.isExistsXmlbData(values)) {
				String messageKey = MessageKey.YSTGL_JCSZ_XMLB_USED;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = service.deleteXmlb(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;

	}
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addGkdw(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm model = (YstglDmwhForm) form;
		if (SAVE.equals(model.getType())) {
			boolean result = service.addGkdw(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("addGkdw");
	}

	/**
	 * 
	 * @描述:修改项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-01-14 下午03:50:04
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
	@SystemLog(description="艺术团管理-代码维护-挂靠单位维护-修改Gkdw:{Gkdwdm}")
	public ActionForward updateGkdw(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		YstglDmwhForm myForm = (YstglDmwhForm) form;
		YstglDmwhForm model = service.getGkdw(myForm);
		if (UPDATE.equals(myForm.getType())) {
			boolean result = service.updateGkdw(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateGkdw");
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-01-14 下午04:03:00
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
	@SystemLog(description="艺术团管理-代码维护-挂靠单位维护-删除values:{values}")
	public ActionForward delGkdw(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//判断挂靠单位是否被占用
			if (service.isExistsGkdwData(values)) {
				String messageKey = MessageKey.YSTGL_JCSZ_GKDW_USED;
				response.getWriter().print(getJsonResult(messageKey, false));
				return null;
			}
			int num = service.deleteGkdw(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
}
