/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:10:21 
 */  
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����10:10:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FbglGzpzTjXxAction extends SuperAction {
	/**
	 * 
	 * @����: 
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-19 ����11:03:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward getGzpzTjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjXxServer fgts=new FbglGzpzTjXxServer();
		String tjgzid=request.getParameter("tjgzid");
		String pzgzid=request.getParameter("pzgzid");
		List<HashMap<String, String>> list=fgts.getGzpzTjxxForLx(tjgzid, pzgzid, false);
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}
	/**
	 * 
	 * @����: ��ȡ���ù���ѡ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-19 ����02:54:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward getGzpzTjSelect(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglGzpzTjXxServer fgts=new FbglGzpzTjXxServer();
		String pzgzid=request.getParameter("pzgzid");
		String tjgzid=request.getParameter("tjgzid");
		String sx=request.getParameter("sx");
		String tjszzd=request.getParameter("tjszzd");
		HashMap<String, String> list=fgts.getGzpzTjgz(pzgzid, tjgzid,tjszzd,sx);
		response.getWriter().print(JSONObject.fromObject(list));
		return null;
	}
}
