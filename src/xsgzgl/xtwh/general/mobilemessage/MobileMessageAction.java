package xsgzgl.xtwh.general.mobilemessage;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.action.SuperAction;

public class MobileMessageAction extends SuperAction<MobileMessageForm, MobileMessageService> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	public ActionForward mobileMessageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MobileMessageService service = new MobileMessageService();
		MobileMessageForm model = (MobileMessageForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xtwh_mobileMessage.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("mobileMessageList");
	}
	/**
	 * 
	 * @描述:短信发送编辑
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-7-28 下午03:17:09
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
	public ActionForward mobileMsgAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("mobileMsgAdd");
	}
	
	public ActionForward mobileMsgView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MobileMessageService service = new MobileMessageService();
		MobileMessageForm myForm = (MobileMessageForm) form;
		MobileMessageForm model = service.getModel(myForm.getId());
		request.setAttribute("model", model);
		return mapping.findForward("mobileMsgView");
	}

	public ActionForward saveSendMsg(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
			MobileMessageService service = new MobileMessageService();
			MobileMessageForm model = (MobileMessageForm) form;
		    //获取发送人，即为当前系统登录人员
		    User user = getUser(request);
		    String username =user.getUserName();
		    String jsrxm = request.getParameter("jsrxm");
		  
		    String[] lxdh = request.getParameterValues("lxdh");
			model.setSxr(jsrxm);
			model.setFsr(username);
			//获取系统当前时间作为发送时间
			model.setFssj(GetTime.getTimeByFormat(DATE_FORMAT));
			boolean result = true;
			result=service.sendGroup(lxdh,model);
			String message = result ? "发送成功"
					: "发送失败";
			response.getWriter().print(getJsonMessage(message));
		    return null;
	}
	
}
