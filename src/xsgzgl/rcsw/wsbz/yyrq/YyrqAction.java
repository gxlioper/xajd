/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����10:56:35 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ྻУ԰����ģ��
 * @�๦������: ԤԼ����ά��
 * @���ߣ� CP[����:1352]
 * @ʱ�䣺 2017-10-16 ����04:12:09
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YyrqAction extends SuperAction {
	private static final String url = "rcsw_wsbz_yyrq.do";

	/**
	 * @����:��ѯ
	 * @���ߣ�1352[���ţ�1352]
	 * @���ڣ�2017-10-16 ����04:12:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward getYyrqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YyrqForm model = (YyrqForm) form;
		YyrqService service = new YyrqService();
		if (QUERY.equals(model.getType())) {
			// ��ѯ��¼
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
	 * @����:����
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-10-16 ����05:57:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
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
	 * @����:�޸�
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-10-17 ����09:12:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
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
	 * @����:ɾ��
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-10-16 ����07:57:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
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
