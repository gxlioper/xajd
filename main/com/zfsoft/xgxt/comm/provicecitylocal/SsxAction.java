/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-12 ����03:39:31 
 */  
package com.zfsoft.xgxt.comm.provicecitylocal;

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
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-12-12 ����03:39:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SsxAction extends SuperAction<SsxModel, SsxService> {
	SsxService ssx = new SsxService();
		
	/**
	 * 
	 * @����:��ȡ����map
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-12 ����04:18:29
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
	public ActionForward getCtiyMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SsxModel model = (SsxModel) form;
		List<HashMap<String, String>> citylist = ssx.getCtiyMap(model.getProvicedm());
		JSONArray jsoncitylist = JSONArray.fromObject(citylist);
		response.getWriter().print(jsoncitylist);
		return null;
	}
	
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-12 ����05:13:55
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
	public ActionForward getLocalMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SsxModel model = (SsxModel)form;
		List<HashMap<String, String>> locallist = ssx.getLocalMap(model.getProvicedm(), model.getCitydm());
		JSONArray jsonlocallist = JSONArray.fromObject(locallist);
		response.getWriter().print(jsonlocallist);
		return null;
	}
	
	/**
	 * 
	 * @����:��ȡʡ��Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-12 ����05:14:14
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
	public ActionForward getProviceMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SsxModel model = (SsxModel)form;
		List<HashMap<String, String>> provicelist = ssx.getProviceMap();		
		JSONArray jsonprovicelist = JSONArray.fromObject(provicelist);
		response.getWriter().print(jsonprovicelist);
		return null;
	}
	
	/**
	 * 
	 * @����:��ȡ����Ϣ���ֶ�����������£��޸�ģʽ���ã�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-12 ����05:14:14
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
	public ActionForward getTotalMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SsxModel model = (SsxModel)form;
		//ʡ��Ϣ
		List<HashMap<String, String>> provicelist = ssx.getProviceMap();		
		JSONArray jsonprovicelist = JSONArray.fromObject(provicelist);
		//������Ϣ
		List<HashMap<String, String>> citylist = ssx.getCtiyMap(model.getProvicedm());
		JSONArray jsoncitylist = JSONArray.fromObject(citylist);
		//������Ϣ
		List<HashMap<String, String>> locallist = ssx.getLocalMap(model.getProvicedm(), model.getCitydm());
		JSONArray jsonlocallist = JSONArray.fromObject(locallist);
		JSONObject json = new JSONObject();
		json.put("jsonprovicelist", jsonprovicelist);
		json.put("jsonprovicelist", jsoncitylist);
		json.put("jsonlocallist", jsonlocallist);
		response.getWriter().print(json);
		return null;
	}
	
	public ActionForward getSsxQcName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SsxModel model = (SsxModel)form;
		//ʡ��Ϣ
		String qcname = ssx.getSsxQcName(model.getProvicedm());
		HashMap<String, String> datamap = new HashMap<String, String>();
		datamap.put("qcname", qcname);
		JSONObject json = JSONObject.fromObject(datamap);
		response.getWriter().print(json);
		return null;
	}

}
