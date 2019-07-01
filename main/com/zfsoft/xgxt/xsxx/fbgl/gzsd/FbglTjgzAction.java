/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����09:43:04 
 */  
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����09:43:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FbglTjgzAction extends SuperAction {
	/**
	 * 
	 * @����: ����������ϸ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-27 ����05:06:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjgzid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public ActionForward getTjpzXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglTjgzService fs=new FbglTjgzService();
		String tjgzid=request.getParameter("tjgzid");
		List<HashMap<String, String>> list=fs.getTjpzXx(tjgzid);
		response.getWriter().print(JSONArray.fromObject(list));
		return null;
	}
	/**
	 * 
	 * @����:����������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-10 ����02:01:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward getTjNrpzXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglTjgzService fs=new FbglTjgzService();
		String tjgzid=request.getParameter("tjgzid");
		String tjszzd=request.getParameter("tjszzd");
		HashMap<String, String> tjnrMap=fs.getTjNrpzXx(tjgzid,tjszzd);
		if(!StringUtils.isNull(tjnrMap.get("ylz"))){
			tjnrMap.put("mrylz", fs.getMrylz(tjnrMap.get("ylz")));//��ȡĬ�ϵ�Ԥ��ֵ
		}
		response.getWriter().print(JSONObject.fromObject(tjnrMap));
		return null;
	}
	/**
	 * 
	 * @����: �����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-19 ����08:54:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward saveGzpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglTjgzService fs=new FbglTjgzService();
		String json=request.getParameter("json");
		String jsonStr=URLDecoder.decode(json,"utf-8");
		JSONObject jo=new JSONObject(jsonStr);
		if(fs.isHave(null, jo.get("pzgzmc").toString())){
			response.getWriter().print(getJsonMessageByKey(MessageKey.FBGL_FBGZMC_USED));
			return null;
		}
		boolean isok=fs.saveGzpz(jo,null);
		String messageKey = isok ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
	/**
	 * 
	 * @����: �޸Ĺ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-19 ����08:54:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	public ActionForward updateGzpz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglTjgzService fs=new FbglTjgzService();
		String json=request.getParameter("json");
		String jsonStr=URLDecoder.decode(json,"utf-8");
		JSONObject jo=new JSONObject(jsonStr);
		String type=request.getParameter("type");
		if(fs.isHave(jo.get("gzpzid").toString(), jo.get("pzgzmc").toString())){
			response.getWriter().print(getJsonMessageByKey(MessageKey.FBGL_FBGZMC_USED));
			return null;
		}
		boolean isok=fs.updataGzpz(jo);
		String messageKey = isok ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	} 
}
