package com.zfsoft.xgxt.xszz.bdpz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ѧ����������ģ��
 * @�๦������: ѧ������2013�涯̬��֧�� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-20 ����02:09:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BdpzAction extends SuperAction {

	/**
	 * 
	 * @����:����Դ��̬����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-20 ����02:08:58
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
	public ActionForward getSjyList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BdpzForm model = (BdpzForm) form;
		BdpzService service = new BdpzService();
		
		List<HashMap<String,String>> sjyList = service.getSjyList(model);
		
		JSONArray dataList = JSONArray.fromObject(sjyList);
		response.getWriter().print(dataList);
		return null;
	}
	
}
