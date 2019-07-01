/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-26 ����05:27:00 
 */  
package com.zfsoft.xgxt.xpjpy.bbwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhModel;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������ţ��£�����ģ��
 * @�๦������: TODO(�ǼǱ���ά��) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-11-26 ����05:27:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbwhAction extends SuperAction {

	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(����б�Ԥ��)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-11-27 ����09:07:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward bgylList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		BbwhForm model = (BbwhForm)form;
		BbwhService service = new BbwhService();
		XmwhService xmwhService = new XmwhService();
		//��Ŀ��Ϣ
		XmwhModel xmxx = xmwhService.getModel(model.getXmdm());
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(xmxx.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
		}
		String bblx=model.getBblx();
		//����ͼƬ�б�
		List<HashMap<String,String>> bbxxList = service.getBbxxList(bblx);
		
		request.setAttribute("bblx", bblx);
		request.setAttribute("xqmc", xqmc);
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		return mapping.findForward("viewBgylList");
	}
	/**
	 * 
	 * @����:TODO(�ǼǱ�Ԥ��)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-11-27 ����09:08:55
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
		
		BbwhForm model = (BbwhForm)form;
		BbwhService service = new BbwhService();
		XmwhService xmwhService = new XmwhService();
		//��Ŀ��Ϣ
		XmwhModel xmxx = xmwhService.getModel(model.getXmdm());
		
		//����ͼƬ�б�
		List<HashMap<String,String>> bbxxList = service.getBbxx(model.getBbdm());
		
		request.setAttribute("xmxx", xmxx);
		request.setAttribute("bbxxList", bbxxList);
		request.setAttribute("bbwh", model);
		return mapping.findForward("viewYlbb");

	}
	
	/**
	 * 
	 * @����:TODO(������Ŀ�ǼǱ�)
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-11-27 ����09:09:32
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
	@SystemLog(description="������������-��������-��Ŀ����-�ǼǱ�����-����XMDM��{xmdm}")
	public ActionForward updateXmbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BbwhForm model = (BbwhForm) form;
		XmwhService xmwhService = new XmwhService();
		
		XmwhModel xmxx = new XmwhModel();
		if(model.getBblx().equals("2")){
			xmxx.setDysbbb(model.getBbdm());
		}else{
			xmxx.setDybb(model.getBbdm());
		}
		xmxx.setXmdm(model.getXmdm());
		boolean isSuccess = xmwhService.runUpdate(xmxx);
		response.getWriter().print(isSuccess);
		return null;
	}
}
