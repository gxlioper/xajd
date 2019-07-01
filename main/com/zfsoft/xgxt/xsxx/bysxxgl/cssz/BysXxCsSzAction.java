/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-8 ����07:29:47 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.log.SystemLog;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ����Ϣ�����������
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-8 ����07:29:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BysXxCsSzAction extends BasicAction {
	
	private XtwhShlcService shlcService = new XtwhShlcService();
	/**
	 * 
	 * @����:TODO��ҵ����Ϣ��������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-8 ����07:38:52
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
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��������-����BYND:{bynd}")
	public ActionForward bysCsSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = Base.chgNull(request.getParameter("doType"), "", 0);
		BysXxCsSzForm model = (BysXxCsSzForm) form;
		BysXxCsSzService service = new BysXxCsSzService();
		DAO dao = DAO.getInstance();
		if ("save".equals(doType)) {
			boolean result=service.csSz(model);
			request.setAttribute("result", result);//�������ӽ��
		}
		HashMap<String, String> map = service.getCssz();
		if(null!=(map.get("kgzt"))){
			model.setBynd(map.get("bynd"));
			model.setKgzt(map.get("kgzt"));
			model.setShlid(map.get("shlid"));
		}else{
			model.setKgzt("y");
		}
		HashMap<String, String> rs=service.splCx();
		if(rs!=null && !rs.isEmpty()){
			request.setAttribute("rs", rs);
		}
		// ����Ϊ����������
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("xsxx"));
		request.setAttribute("doType", doType);
		List<HashMap<String, String>> xnList = dao.getXnndList();
		xnList.remove(0);
		request.setAttribute("xnList", xnList);
		// ��ȡ�û����Ƿ��д��Ȩ��  �� title
		request.setAttribute("path", "xsxx_new_bysxx_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bysCsSz");
	}

}
