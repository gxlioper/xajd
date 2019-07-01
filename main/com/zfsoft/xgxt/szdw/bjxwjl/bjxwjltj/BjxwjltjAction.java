/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-16 ����01:40:39 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjltj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-16 ����01:40:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjxwjltjAction extends SuperAction {

	/**
	 * service
	 */
	private static BjxwjltjService service = new BjxwjltjService();
	
	/**
	 * path ·��
	 */
	private static final String PATH = "szdw_bjxwjltj.do?method=bjxwjltjManage";
	
	private static final String url = "szdw_bjxwjltj.do?method=bjxwjltjManage";
	
	/**
	 * �༶��Ϣ��¼ά������
	 */
	@SystemAuth(url = url)
	public ActionForward bjxwjltjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", PATH);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjxwjltjManage");
	}
	
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-4-23 ����11:43:04
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
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjltjForm model = (BjxwjltjForm) form;
		User user = getUser(request);
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPageList(model,user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @���� : �鿴����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-19 ����04:29:09
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
	public ActionForward ck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BjxwjltjForm model = (BjxwjltjForm) form;
		HashMap<String , String> bjxx = service.getbjxx(model.getBjdm());
		List<HashMap<String , String>> gbxx = new DwwhService().getZwListByBjdm(model.getBjdm());
		request.setAttribute("fdyjlxx", service.getXwxx(model.getBjdm(), "1")); //'1' ������Ա��¼��Ϣ
		request.setAttribute("bzrjlxx", service.getXwxx(model.getBjdm(), "2"));	//'2' ��������μ�¼��Ϣ 
		request.setAttribute("bjxx", bjxx);
		request.setAttribute("gbxx", gbxx);
		return mapping.findForward("tjck");
	}
	
}
