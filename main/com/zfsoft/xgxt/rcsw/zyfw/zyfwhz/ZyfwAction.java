/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-28 ����11:53:34 
 */  
package com.zfsoft.xgxt.rcsw.zyfw.zyfwhz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cp[���ţ�1352]
 * @ʱ�䣺 2016-12-28 ����11:53:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwAction extends SuperAction<ZyfwForm,ZyfwService>{
	private static final String url = "bpmxzyfwhz.do";//���ݿ����
	ZyfwService service = new ZyfwService();
	@SystemAuth(url = url)
		public ActionForward gjjgList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZyfwForm model = (ZyfwForm) form;
		HttpSession session = request.getSession();	
		// ��½�û�
		String userName = (String)session.getAttribute("userName");		
		User user = getUser(request); // ��ǰ��¼ѧ��
		String userType = user.getUserType();// ��ģ��ֻ����ѧ������
		if (!"stu".equalsIgnoreCase(userType)) {
			String msg = "��ģ�������ѧ���û����ʣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		HashMap<String,String> map = service.getXsxx(userName);
		HashMap<String,String> zscMap = ((ZyfwService) service).getZsc(userName,model);
		request.setAttribute("path", "bpmxzyfwhz.do");	
		request.setAttribute("rs", map);
		request.setAttribute("zsc", zscMap);
		request.setAttribute("zyfwList", service.getZyfwList(userName,model));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getzyfwList");		
	}
}
