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
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// ��ѯ
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
	 * @����:���ŷ��ͱ༭
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-7-28 ����03:17:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
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
		    //��ȡ�����ˣ���Ϊ��ǰϵͳ��¼��Ա
		    User user = getUser(request);
		    String username =user.getUserName();
		    String jsrxm = request.getParameter("jsrxm");
		  
		    String[] lxdh = request.getParameterValues("lxdh");
			model.setSxr(jsrxm);
			model.setFsr(username);
			//��ȡϵͳ��ǰʱ����Ϊ����ʱ��
			model.setFssj(GetTime.getTimeByFormat(DATE_FORMAT));
			boolean result = true;
			result=service.sendGroup(lxdh,model);
			String message = result ? "���ͳɹ�"
					: "����ʧ��";
			response.getWriter().print(getJsonMessage(message));
		    return null;
	}
	
}
