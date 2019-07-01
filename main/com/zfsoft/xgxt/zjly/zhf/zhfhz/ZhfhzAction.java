/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-23 ����10:01:13 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfhz;

import java.util.HashMap;
import java.util.List;

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
 * @ģ������: �ۺ����ʷֹ���
 * @�๦������: �ۺ���������ѧ�ֻ��ܱ� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-23 ����10:01:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfhzAction extends SuperAction<ZhfhzForm, ZhfhzService> {
	
	private static final String url = "xg_zjly_zhszfhz.do";
	
	/**
	 * 
	 * @����: �ۺ����ʷֻ���
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-23 ����10:35:43
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
	public ActionForward getZhfhzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
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
		
		ZhfhzService service = new ZhfhzService();	
		HashMap<String,String> map = service.getXsxx(userName);
		HashMap<String,String> zfMap = service.getZfs(userName);
		String zfs = zfMap.get("zfs");
		if(zfs != null&& zfs !="") {
			if(".".equalsIgnoreCase(zfs.substring(0, 1))) {
				zfs = "0"+zfs;
			}
		}
		if(zfs == null || zfs =="" || "0".equalsIgnoreCase(zfs)){		
			zfs = "";
		}	
		request.setAttribute("path", "xg_zjly_zhszfhz.do");	
		request.setAttribute("rs", map);
		request.setAttribute("zfrs", zfs);
		List<HashMap<String, String>> mkzflist = service.getmkzf(userName);
		String html = service.getHzxxHtml(userName,mkzflist);
		   //ȡ�±�������
		request.setAttribute("html", html);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getZhfhzList");		
	}
	
}
