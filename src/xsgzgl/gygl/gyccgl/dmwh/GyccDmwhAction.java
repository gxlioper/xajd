package xsgzgl.gygl.gyccgl.dmwh;

import java.util.ArrayList;
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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

public class GyccDmwhAction extends SuperAction<GyccDmwhForm, GyccDmwhService> {
	private static final String url = "gygl_gyccgl_dmwh.do";
	private GyccDmwhService service = new GyccDmwhService();
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����11:58:05
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
	@SystemAuth(url = url)
	public ActionForward searchRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GyccDmwhForm myForm = (GyccDmwhForm)form;
		if(QUERY.equals(myForm.getType())){
			User user = getUser(request);
			List<HashMap<String, String>> resultList = new ArrayList<HashMap<String,String>>();
			if("wp".equals(myForm.getCxlx())){
				resultList = service.getPageList(myForm);
			}else{
				resultList = service.getPageList(myForm,user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
		
	}
	
	/**
	 * 
	 * @����:������Ʒ��תҳ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����04:18:52
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addWp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		return mapping.findForward("addwp");
	}
	
	/**
	 * 
	 * @����:�޸���Ʒ��תҳ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����04:18:52
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateWp(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)throws Exception{
		GyccDmwhForm myForm = (GyccDmwhForm)form;
		GyccDmwhForm model = service.getModel(myForm.getDm());
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("updatewp");
	}
	
	/**
	 * 
	 * @����: ������Ʒ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����04:21:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�Ԣ����-����Ʋ�����-����ά��-������Ʒ����������:{type}-DM:{dm},MC:{mc}")
	public ActionForward saveWpForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		GyccDmwhForm myForm = (GyccDmwhForm)form;
		String messageKey = service.saveWpForm(myForm) ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:������Ʒ��תҳ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����04:18:52
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addShcd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		return mapping.findForward("addshcd");
	}
	
	/**
	 * 
	 * @����:�޸���Ʒ��תҳ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����04:18:52
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateShcd(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)throws Exception{
		GyccDmwhForm myForm = (GyccDmwhForm)form;
		GyccDmwhForm model = service.getBscdForm(myForm.getShcddm());
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("updateshcd");
	}
	
	/**
	 * 
	 * @����: �����𺦳̶�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����05:13:36
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�Ԣ����-����Ʋ�����-����ά��-�����𺦳̶ȣ���������:{type}-SHCDDM:{shcddm},SHCDMC:{shcdmc},JE:{je}")
	public ActionForward saveShcdForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		GyccDmwhForm myForm = (GyccDmwhForm)form;
		String messageKey = service.saveShcdForm(myForm) ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ����Ʒ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����05:37:21
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�Ԣ����-����Ʋ�����-����ά��-ɾ����Ʒ�Ʋ�DMS:{values}")
	public ActionForward delWp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(!service.isWpdmNotUserd(ids)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_DMWH_USERD_NOTDEL));
				return null;
			}
			int num = service.runDelete(ids);
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
	
	/**
	 * 
	 * @����: ɾ���𺦳̶�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����05:39:50
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�Ԣ����-����Ʋ�����-����ά��-ɾ���𻵳̶�SHCDDMS:{values}")
	public ActionForward delShcd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(!service.isShcdNotUserd(ids)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_DMWH_USERD_NOTDEL));
				return null;
			}
			int num = service.DelShcd(ids);
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
