package xsgzgl.qgzx.glygl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

public class XqglyAction extends SuperAction<XqglyForm, XqglyService> {
	private String url = "qgzx_glygl_glywh.do";
	private XqglyService service = new XqglyService();
	/**
	 * 
	 * @描述: 查询跳转
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-6 下午03:15:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getXqglyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xqglycx");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-6 下午03:16:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward searchForXqgly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XqglyForm myForm = (XqglyForm)form;
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-6 下午03:18:44
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
	public ActionForward add(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("xqList", service.getXqList());
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @描述: 保存校区
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-6 下午03:19:55
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
	public ActionForward saveXq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XqglyForm myForm = (XqglyForm)form;
		if(!service.checkIsRepeat(myForm.getZgh(), myForm.getXq())){
			String message = "请勿重复填写校区管理员信息！";
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean rs = service.runInsert(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			boolean result = service.runDeletes(ids);
			String message = result ? MessageKey.SYS_DEL_SUCCESS:MessageKey.SYS_DEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
}
