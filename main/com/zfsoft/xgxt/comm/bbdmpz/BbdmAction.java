/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-26 ����02:26:10 
 */  
package com.zfsoft.xgxt.comm.bbdmpz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bbdmpz.model.BbdmModel;
import com.zfsoft.xgxt.comm.bbdmpz.service.BbdmService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-26 ����02:26:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BbdmAction extends SuperAction {

	/**
	 * 
	 * @����:��������ģ�����ƻ�ȡ����б�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-26 ����02:27:40
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
	public ActionForward queryBbdmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BbdmModel myform = (BbdmModel)form;

		String mkdm = myform.getMkdm(); 
		
		String ywid = myform.getYwid();
		
		String thlj = myform.getThlj();

		if(null == mkdm){
			String msg = "��ģ��δ���ñ������ϵ����Ա��";
			request.setAttribute("yhInfo", msg);
			request.setAttribute("returnBackUrl", request.getParameter("returnBackUrl"));
			return mapping.findForward("yhInfo");
		}

		BbdmService service = new BbdmService();
		
		HashMap<String , String> bbdmCs = service.getBbdmCs(mkdm);

		String dybb = service.getDybb(mkdm, ywid);

		myform.setDybb(dybb);
		
		//ģ�����
		request.setAttribute("mkdm", mkdm);
		//��ǰ���õı�����
		request.setAttribute("dybb", dybb);
		//�˻�·��
		request.setAttribute("thlj", thlj);
		//ҵ������
		request.setAttribute("ywid", ywid);
		
		List<HashMap<String, String>> bbxxList = service.getBbdmList(mkdm);
	
		if(bbxxList == null || bbxxList.size() == 0){
			String msg = "��ģ��û����ر������ϵ����Ա��";
			request.setAttribute("yhInfo", msg);
			request.setAttribute("returnBackUrl", request.getParameter("returnBackUrl"));
			return mapping.findForward("yhInfo");
		}
		

		request.setAttribute("bbxxList", bbxxList);
		
		return mapping.findForward("viewBbdmList");
	}
	
	/**
	 * 
	 * @����:����GUID ѡ�񱨱�
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-26 ����03:54:12
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
	public ActionForward queryBbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BbdmService service = new BbdmService();
		BbdmModel myform = (BbdmModel)form;
		String guid = myform.getGuid();//request.getParameter("bbdmwh_guid");
		String ywid = myform.getYwid();
		String dybb = myform.getDybb();
		List<HashMap<String,String>> bbxxList = service.getBbdm(guid);
		
		BbdmModel model = service.getModelByGuid(guid);
		
		if(model != null ){
			BeanUtils.copyProperties(myform, model);
			
			myform.setYwid(ywid);
			myform.setDybb(dybb);
		}
		
		request.setAttribute("bbdmModel", myform);
		
		request.setAttribute("bbxxList", bbxxList);
		
		return mapping.findForward("viewBbdmInfo");
	}
	
	/**
	 * 
	 * @����:���ñ���
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-27 ����01:09:59
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
	public ActionForward setBbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		BbdmService service = new BbdmService();
		BbdmModel myform = (BbdmModel)form;
		
		String guid = myform.getGuid();
		String ywid = myform.getYwid();
		String mkdm = myform.getMkdm();
		
/*		if(StringUtil.isNull(ywid) || StringUtil.isNull(guid) || StringUtil.isNull(mkdm)){
			String messageKey = MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}*/
		
		boolean isSuccess = service.setupDybb(mkdm, ywid, guid);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
}
