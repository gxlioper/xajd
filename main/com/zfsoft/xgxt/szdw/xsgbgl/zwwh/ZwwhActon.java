/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����10:27:41 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwwh;

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
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwlx.ZwlxService;
import com.zfsoft.xgxt.szdw.xsgbgl.zwsq.ZwsqService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ��action
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-7 ����10:27:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwwhActon extends SuperAction {

	private ZwwhService service = new ZwwhService();
	
	private ZwlxService zwlxService = new ZwlxService();
	

	/**
	 * @����:ְ���б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-7 ����11:17:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	
	public ActionForward zwwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZwwhForm myForm = (ZwwhForm) form;
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("path", "szdw_xsgb_zwwh.do?method=zwList");
		return mapping.findForward("list");
	}
	/**
	 * @����:����ѧ���ɲ�����ְ��
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-7 ����11:23:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description = "����˼������-ѧ���ɲ�����-ѧ���ɲ�ְ�����-����ZWMC:{zwmc},LXDM:{lxdm},ZWZZ:{zwzz}")
	public ActionForward zwwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwwhForm myForm = (ZwwhForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//��ȡְ������
		ZwlxService zwlxService = new ZwlxService();
		request.setAttribute("zwlx", zwlxService.getList());
		request.setAttribute("model", StringUtils.formatData(myForm));
		return mapping.findForward("add");
	}
	/**
	 * @����:����ѧ���ɲ�����ְ��
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-7 ����11:24:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description = "����˼������-ѧ���ɲ�����-ѧ���ɲ�ְ�����-�޸�ZWID:{zwid},ZWMC:{zwmc},LXDM:{lxdm},ZWZZ:{zwzz}")
	public ActionForward zwwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwwhForm myForm = (ZwwhForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//��ȡְ������
		
		myForm=service.getModel(myForm);
		request.setAttribute("zwlx", zwlxService.getList());
		request.setAttribute("model", StringUtils.formatData(myForm));
		return mapping.findForward("update");
	}
	
	/**
	 * @����:ɾ��ѧ���ɲ�ְ��
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-7 ����11:24:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	@SystemLog(description = "����˼������-ѧ���ɲ�����-ѧ���ɲ�ְ�����-ɾ��ZWID:{values}")
	public ActionForward zwwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String message = null;
		if (!StringUtil.isNull(values)){
			String[] sqids = values.split(",");
			ZwsqService zwsq = new ZwsqService();
			//��֤��ְ���Ƿ�����
			int sqcount = zwsq.getSqCountByZwid(sqids);
			if(sqcount==0){
				int num = service.runDelete(sqids);
				boolean result =  num > 0;
				message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
						: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			}else{
				message = MessageUtil.getText("szdw_xsgbgl_zw");
			}
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
		
	}
	/**
	 * @����:ְ����Ϣ�鿴
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-13 ����9:27:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward ��������
	 */
	
	public ActionForward zwView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwwhForm myForm = (ZwwhForm) form;
		List<HashMap<String,String>> list = service.getZwList(myForm.getLxdm());
		//��ȡְ������
		request.setAttribute("zwlx", zwlxService.getList());
		request.setAttribute("zwList",list);
		if(!StringUtil.isNull(myForm.getZwid())){
			myForm = service.getModel(myForm);
		}
		request.setAttribute("model",StringUtils.formatData(myForm));
		return mapping.findForward("view");
	}
	
	/**
	 *
	 * @����:�鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-25 ����07:22:16
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
	public ActionForward zwck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZwwhForm myForm = (ZwwhForm) form;
		ZwwhForm model = service.getModel(myForm.getZwid());
		ZwlxForm zwlx = zwlxService.getModel(model.getLxdm());
		request.setAttribute("zwwh", model);
		request.setAttribute("zwlx", zwlx);
		return mapping.findForward("zwck");
	}
}
