/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-27 ����02:23:49 
 */  
package com.zfsoft.xgxt.xtwh.cxpz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;


import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �Զ����ѯ�� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-5-27 ����02:23:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxpzAction extends SuperAction {

	
	/**
	 * 
	 * @����: ��ѯ���ó�ʼ��ҳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-27 ����03:42:51
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
	public ActionForward cxpzInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxpzForm model = (CxpzForm) form;
		CxpzService service = new CxpzService();
		
		List<HashMap<String,String>> cxgnList = service.getCxgnList(model.getGnmc());
		
		if (StringUtils.isNull(model.getGnbz()) && cxgnList.size() > 0){
			model.setGnbz(cxgnList.get(0).get("gnbz"));
		}
		
		
		request.setAttribute("cxgnList", cxgnList);
		return mapping.findForward("cxpzInit");
	}
	
	
	
	/**
	 * 
	 * @����: ��ȡ��ѯ���������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-27 ����05:20:33
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
	public ActionForward getColList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxpzForm model = (CxpzForm) form;
		CxpzService service = new CxpzService();
		
		List<HashMap<String,String>> colList = service.getColList(model.getGnbz());
		
		request.setAttribute("colList", colList);
		return mapping.findForward("colList");
	}
	
	
	/**
	 * 
	 * @����: ��������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-29 ����05:13:26
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
	public ActionForward updateColInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxpzForm model = (CxpzForm) form;
		CxpzService service = new CxpzService();
		
		service.updateColInfo(model);
		
		return null;
	}
	


	/**
	 * 
	 * @����: ��ȡ��ѯ������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-31 ����02:19:32
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
	public ActionForward getCxpzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CxpzForm model = (CxpzForm) form;
		CxpzService service = new CxpzService();
		
		String cxpz = request.getParameter("cxpz");
		
		JSONObject json = service.getCxbzForJson(model.getGnbz(),cxpz);
		response.getWriter().print(json);
		
		return null;
	}
	
	
	public ActionForward ylxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("ylxg");
	}

}
