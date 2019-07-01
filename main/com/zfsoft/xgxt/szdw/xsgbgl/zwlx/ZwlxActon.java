/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����10:27:41 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwlx;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhForm;
import com.zfsoft.xgxt.szdw.xsgbgl.zwwh.ZwwhService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ������action
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-7 ����10:27:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwlxActon extends SuperAction {
	
	private static final String url = "szdw_xsgb_zwlx.do?method=zwlxList";

	/**
	 * @����:ְ�������б�
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
	@SystemAuth(url = url)
	public ActionForward zwlxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZwlxForm myForm = (ZwlxForm) form;
		ZwlxService service = new ZwlxService();
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
		request.setAttribute("path", "szdw_xsgb_zwlx.do?method=zwlxList");
		return mapping.findForward("list");
	}
	/**
	 * @����:����ѧ���ɲ�����ְ������
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����˼������-ѧ���ɲ�����-�ɲ�ְ������ά��-����LXMC:{lxmc}")
	public ActionForward zwlxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwlxForm myForm = (ZwlxForm) form;
		ZwlxService service = new ZwlxService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean yzLxName = service.yzLxName(myForm.getLxmc());
			if(yzLxName){
				boolean result = service.runInsert(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonResult(messageKey,result));
			}else{
				response.getWriter().print(getJsonResult("szdw_xsgbgl_zwlxyz",yzLxName));
			}
			return null;
		}
		request.setAttribute("model", StringUtils.formatData(myForm));
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("szdw");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		return mapping.findForward("add");
	}
	/**
	 * @����:����ѧ���ɲ�����ְ������
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����˼������-ѧ���ɲ�����-�ɲ�ְ������ά��-�޸�LXDM:{lxdm},LXMC:{lxmc}")
	public ActionForward zwlxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwlxForm myForm = (ZwlxForm) form;
		ZwlxService service = new ZwlxService();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		myForm=service.getModel(myForm);
		request.setAttribute("model", StringUtils.formatData(myForm));
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("szdw");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		return mapping.findForward("update");
	}
	
	/**
	 * @����:ɾ��ѧ���ɲ�ְ������
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����˼������-ѧ���ɲ�����-�ɲ�ְ������ά��-ɾ��LXDM:{values}")
	public ActionForward zwlxDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZwlxService service = new ZwlxService();
		String values = request.getParameter("values");
		String message = null;
		if (!StringUtil.isNull(values)){
			ZwwhService zwwhService = new ZwwhService();
			int sqcount = zwwhService.yzZwCount(values.split(","));
			if(sqcount<=0){
				int num = service.runDelete(values.split(","));
				boolean result =  num > 0;
				message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
						: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			}else{
				message = MessageUtil.getText("szdw_xsgbgl_zwlx");
			}
			
			response.getWriter().print(getJsonMessage(message));
		}
		return null;
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ְ�����Ͳ鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-26 ����10:09:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward zwlxck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ZwlxForm myForm = (ZwlxForm) form;
		ZwlxForm zwlx =   new ZwlxService().getModel(myForm.getLxdm());
		HashMap<String, String> lcxx = new ZwlxService().getSplcMc(zwlx.getSplc());
		request.setAttribute("zwlx", zwlx);
		request.setAttribute("splc", lcxx.get("lcxx"));
		return mapping.findForward("zwlxck");
	}
	
	
}
