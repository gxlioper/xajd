/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018��5��22�� ����10:23:23 
 */  
package xsgzgl.gyjc.jcsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;

import net.sf.json.JSONArray;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018��5��22�� ����10:23:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxxmAction extends SuperAction<CxxmForm, CxxmService> {
	private CxxmService service = new  CxxmService();
	private static final String url = "xg_gyjc_cxxmsz.do";
	
	/**
	 * 
	 * @����: ��ѯ���ֱ�׼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-11 ����11:55:56
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
	
	public  ActionForward  getCxxmList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		CxxmForm model = (CxxmForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//���û�����û�ҳ��
		request.setAttribute("userType", user.getUserStatus());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("search");
	}
	
	/**
	 * 
	 * @����:����ҳ����ת
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-12 ����08:59:19
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}
	

	//����
	public ActionForward saveCxxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		CxxmForm model = (CxxmForm) form;
		System.out.println(model.getDm());
		boolean rs = service.saveCxxm(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//ɾ����δ���� ���ж��Ƿ���ʹ�ã�
	public ActionForward delCxxm(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		CxxmForm model = (CxxmForm) form;
		CxxmService tanService = TransactionControlProxy.newProxy(new CxxmService());
		boolean rs = tanService.delRs(model.getDms());
		String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_SUCCESS;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
