/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2017-4-20 ����09:15:08 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.bbsz;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-��������-��Ŀ����-��������
 * @�๦������: �ǼǱ��ϱ�������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-20 ����09:16:35 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbszAction extends SuperAction {
	
	/**
	 * @����: ����Ԥ���б�
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-20 ����10:24:31
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
	public ActionForward bbylList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BbszForm model = (BbszForm)form;
		BbszService service = new BbszService();
		XmwhService xmwhService = new XmwhService();
		/*��Ŀ��Ϣ*/
		XmwhForm xmxx = xmwhService.getModel(model.getXmdm());
		request.setAttribute("xmxx", xmxx);
		/*��������*/
		String bblx = model.getBblx();
		request.setAttribute("bblx", bblx);
		/*����ͼƬ�б�*/
		List<HashMap<String,String>> bbxxList = service.getBbxxList(bblx);
		request.setAttribute("bbxxList", bbxxList);
		return mapping.findForward("bbylList");
	}
	
	/**
	 * @����: ����Ԥ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-20 ����03:36:59
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
	public ActionForward showYlbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BbszForm model = (BbszForm)form;
		BbszService service = new BbszService();
		XmwhService xmwhService = new XmwhService();
		/*��Ŀ��Ϣ*/
		XmwhForm xmxx = xmwhService.getModel(model.getXmdm());
		request.setAttribute("xmxx", xmxx);
		/*����Ԥ��*/
		String bbdm = model.getBbdm();
		List<HashMap<String,String>> bbxxList = service.getBbxxYlList(bbdm);
		request.setAttribute("bbxxList", bbxxList);
		request.setAttribute("bbwh", model);
		return mapping.findForward("showYlbb");
	}
	
	/**
	 * @����: ������Ŀ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-4-20 ����03:53:09
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
	public ActionForward updateXmbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BbszForm model = (BbszForm)form;
		XmwhForm xmwhModel = new XmwhForm();
		xmwhModel.setXmdm(model.getXmdm());
		if(StringUtils.isNotEmpty(model.getBblx())){
			if(model.getBblx().equals("1")){
				xmwhModel.setDjb(model.getBbdm());
			}else if(model.getBblx().equals("2")){
				xmwhModel.setSbb(model.getBbdm());
			}
		}
		XmwhService xmwhService = new XmwhService();
		boolean isSuccess = xmwhService.runUpdate(xmwhModel);
		response.getWriter().print(isSuccess);
		return null;
	}
}
