/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-19 ����08:54:43 
 */
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxdmwh;


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
import xgxt.utils.String.StringUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ������ڷ�У����ά������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-11 ����09:15:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
@SuppressWarnings("unchecked")
public class JqfxdmwhAction extends SuperAction {
	
	/**
	 * 
	 * @����:TODO(��У�������ά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-11 ����09:16:43
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
	public ActionForward jlkjfxglDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqfxdmwhForm model = (JqfxdmwhForm) form;
		JqfxdmwhService service = new JqfxdmwhService();

		if (QUERY.equals(model.getType())) {
			List<HashMap<String, String>> resultList = service
					.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		String path = "jlkjxyfxgldmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);

		return mapping.findForward("jlkjfxglDmwh");

	}
	
	/**
	 * 
	 * @����:TODO(���ӷ�У�������ά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-2-24 ����11:28:01
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
	public ActionForward addFxglDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqfxdmwhForm model = (JqfxdmwhForm) form;
		JqfxdmwhService service = new JqfxdmwhService();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.saveJqfxgldmwhInfo(model, "add");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}		
		return mapping.findForward("addFxglDmwh");
	}
	
	/**
	 * 
	 * @����:TODO(���ķ�У������ά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-2-24 ����12:44:58
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
	public ActionForward updateFxglDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxdmwhService service = new JqfxdmwhService();
		
		String fxdm = request.getParameter("fxdm");
		fxdm = java.net.URLDecoder.decode(fxdm, "utf-8");
		JqfxdmwhForm myForm = (JqfxdmwhForm) form;			
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){			
			boolean result = service.saveJqfxgldmwhInfo(myForm, "update");
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		myForm.setFxdm(fxdm);
		JqfxdmwhForm model = service.getModel(myForm);		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateFxglDmwh");
	}
	
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-16 ����10:58:51
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
	public ActionForward delFxglDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxdmwhService service = new JqfxdmwhService();	
		String values = request.getParameter("values");
	
		if (!StringUtil.isNull(values)) {
			String[] mess = service.deletFxlbdmwhInfo(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

}
