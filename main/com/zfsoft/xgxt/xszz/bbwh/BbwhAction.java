/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-4 ����03:06:05 
 */  
package com.zfsoft.xgxt.xszz.bbwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhForm;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-6-4 ����03:06:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbwhAction extends SuperAction {

	
	/**
	 * 
	 * @����: ����Ԥ���б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-5 ����02:12:47
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
	public ActionForward bgylList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BbwhForm model = (BbwhForm) form;
		BbwhService service = new BbwhService();
		XmwhService xmwhSerivce = new XmwhService();
		
		//��Ŀ��Ϣ
		XmwhForm xmxx = xmwhSerivce.getModel(model.getXmdm());
		//����ͼƬ�б�
		String bblx = model.getBblx();
		List<HashMap<String,String>> bbxxList = service.getBbxxLists(bblx);
		
		request.setAttribute("bblx", bblx);
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		return mapping.findForward("bgylList");
	}
	
	
	/**
	 * 
	 * @����: �ǼǱ�Ԥ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-5 ����03:52:49
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
	public ActionForward showYlbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BbwhForm model = (BbwhForm) form;
		BbwhService service = new BbwhService();
		XmwhService xmwhSerivce = new XmwhService();
		
		//��Ŀ��Ϣ
		XmwhForm xmxx = xmwhSerivce.getModel(model.getXmdm());
		List<HashMap<String,String>> bbxxList = service.getBbxxList(model.getBbdm());
		
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		request.setAttribute("bbwh", model);
		return mapping.findForward("showYlbg");
	}
	
	
	
	/**
	 * 
	 * @����: ������Ŀ����
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-6 ����08:47:31
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
	@SystemLog(description="����ѧ������-��������-������Ŀ����-�ǼǱ���XMDM:{xmdm}")
	public ActionForward updateXmbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BbwhForm model = (BbwhForm) form;
		XmwhForm xmwhModel = new XmwhForm();
		
		xmwhModel.setXmdm(model.getXmdm());
		if(StringUtils.isNotEmpty(model.getBblx())){
			if(model.getBblx().equals("1")){
				xmwhModel.setDybb(model.getBbdm());
			}else if(model.getBblx().equals("2")){
				xmwhModel.setDysbbb(model.getBbdm());
			}
		}
		XmwhService xmwhSerivce = new XmwhService();
		
		boolean isSuccess = xmwhSerivce.runUpdate(xmwhModel);
		response.getWriter().print(isSuccess);
		return null;
	}
}
