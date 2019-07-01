package com.zfsoft.xgxt.dtjs.dxbmgl.shlcsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @������������У���������������action
 * @author������ ��1346��
 * @date��2017-11-1 ����09:28:08 
 */
public class DxbmshlcszAction extends SuperAction<DxbmshlcszForm, DxbmshlcszService> {
	private DxbmshlcszService service = new DxbmshlcszService();
	private XtwhShlcService shlcService = new XtwhShlcService();
	/** 
	 * @������������У�����������������ת
	 * @author������ ��1346��
	 * @date��2017-11-2 ����04:33:48 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxbmshlcsz(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		String rs=service.dxbmshlcszCx();
		DxbmshlcszForm model = (DxbmshlcszForm) form;
		model.setShl(rs);
		request.setAttribute("shl", rs);
		List<HashMap<String, String>> shlcList = shlcService.getShlcByDjlx("dtjs");//��ȡ���Ž������͵���� �����б�
		request.setAttribute("shlcList", shlcList);
		// ��ȡ�û����Ƿ��д��Ȩ�� ��title
		request.setAttribute("path", "dxbmgl_dxbmshlcsz?method=dxbmshlcsz");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dxbmshlcsz");
	}
	/** 
	 * @��������������������
	 * @author������ ��1346��
	 * @date��2017-11-3 ����09:53:50 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public ActionForward dxbmshlcszBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DxbmshlcszForm model = (DxbmshlcszForm) form;
		boolean result=service.dxbmshlcszBc(model);
		//����Ϊ����������
		request.setAttribute("result", result);
		dxbmshlcsz(mapping, form, request, response);
		return mapping.findForward("dxbmshlcsz");
	}
}
