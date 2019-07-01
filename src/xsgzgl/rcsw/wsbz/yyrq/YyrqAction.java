/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 上午10:56:35 
 */
package xsgzgl.rcsw.wsbz.yyrq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 洁净校园管理模块
 * @类功能描述: 预约日期维护
 * @作者： CP[工号:1352]
 * @时间： 2017-10-16 下午04:12:09
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YyrqAction extends SuperAction {
	private static final String url = "rcsw_wsbz_yyrq.do";

	/**
	 * @描述:查询
	 * @作者：1352[工号：1352]
	 * @日期：2017-10-16 下午04:12:15
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
	public ActionForward getYyrqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YyrqForm model = (YyrqForm) form;
		YyrqService service = new YyrqService();
		if (QUERY.equals(model.getType())) {
			// 查询记录
			List<HashMap<String, String>> resultList = service
					.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "rcsw_wsbz_yyrq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}

	/**
	 * @描述:增加
	 * @作者：CP[工号：1352]
	 * @日期：2017-10-16 下午05:57:14
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
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YyrqForm model = (YyrqForm) form;
		YyrqService service = new YyrqService();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean isExist = service.isExist(model);
			if (!isExist) {
				boolean result = service.saveLxInfo(model, "add");
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
	    	}else {
	    		response.getWriter().print(getJsonMessageByKey(MessageKey.XG_WSBZ_YYRQ_EXIST));
	    	}
		
			return null;
		}
		request.setAttribute("path", url);
		return mapping.findForward("add");
	}

	/**
	 * @描述:修改
	 * @作者：CP[工号：1352]
	 * @日期：2017-10-17 上午09:12:19
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
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YyrqForm myForm = (YyrqForm) form;
		YyrqService service = new YyrqService();
		YyrqForm model = service.getModel(myForm);
		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			boolean isExist = service.isxgExist(myForm);
			if (!isExist) {
				boolean result = service.saveLxInfo(myForm, "update");
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
						: MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
	    	}else {
	    		response.getWriter().print(getJsonMessageByKey(MessageKey.XG_WSBZ_YYRQ_EXIST));
	    	}
			
			return null;
		}
		request.setAttribute("model", model);
		BeanUtils.copyProperties(model, myForm);
		return mapping.findForward("xg");
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：CP[工号：1352]
	 * @日期：2017-10-16 下午07:57:45
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YyrqService service = new YyrqService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			int num = service.runDelete(values.split(","));
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

}
