/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-3 ����06:17:35 
 */  
package com.zfsoft.xgxt.axcs.wpsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-3 ����06:17:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class AxcsWpszAjax extends BasicAction{
	/**
	 * 
	 * @����:��ƷͼƬ�ϴ�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2014-12-3 ����06:18:10
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
	public ActionForward uploadWpPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		User user = getUser(request);// �û�����
		String flag = service.saveWpPic(model, user);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag);
	
		return null;
	}
	
	public ActionForward getWpTjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		List<HashMap<String, String>> resultList = service.getWpTjList(model.getXmdm());
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	public ActionForward getWpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WpszForm model = (WpszForm) form;
		WpszService service = new WpszService();
		HashMap<String, String> wpxx = service.getWpxxByXmdm(model.getXmdm());
		JSONObject data = JSONObject.fromMap(wpxx);
		response.getWriter().print(data);
	
		return null;
	}
	
}
