/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-24 ����10:59:52 
 */  
package com.zfsoft.xgxt.wjcf.cflbdmwh;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (����������ά��) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-24 ����10:52:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CflbdmwhAction extends SuperAction {
	
	private static final String url = "wjcf_cflbdmwh.do?method=cxCflbdmList";
	
	@SystemAuth(url = url)
	public ActionForward cxCflbdmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CflbdmwhForm myForm = (CflbdmwhForm) form;
		CflbdmwhService service = new CflbdmwhService();
		
		if (QUERY.equals(myForm.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "wjcf_cflbdmwh.do?method=cxCflbdmList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxCflbdmList");
	}
	/**
	 * 
	 * @����:(��������������)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-24 ����01:33:16
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����Υ�ʹ���-��������-����������ά��-����CFLBMC:{cflbmc},SPL:{spl},SFKSS:{sfkss},SFKSQJC:{sfksqjc}")
	public ActionForward cflbdmAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CflbdmwhForm myForm = (CflbdmwhForm) form;
		CflbdmwhService service = new CflbdmwhService();
		
		if("save".equalsIgnoreCase(myForm.getType())){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			boolean flag=service.checkIsExist(myForm);
			if(flag){
				String messageKey=MessageKey.WJCF_CFLBDM_CFLBCZ;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			myForm.setCjsj(sdf.format(new Date()));
			boolean result = service.runInsert(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("wjcf"));
		this.saveToken(request);
		return mapping.findForward("cflbdmAdd");
	}
	
	/**
	 * 
	 * @����:(�����������޸�)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-24 ����01:33:16
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����Υ�ʹ���-��������-����������ά��-�޸�CFLBDM:{cflbdm},CFLBMC:{cflbmc},SPL:{spl},SFKSS:{sfkss},SFKSQJC:{sfksqjc}")
	public ActionForward cflbdmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CflbdmwhForm myForm = (CflbdmwhForm) form;
		CflbdmwhForm model = new CflbdmwhForm();
		CflbdmwhService service = new CflbdmwhService();
		if("save".equalsIgnoreCase(myForm.getType())){
			boolean flag=service.checkIsExist(myForm);
			if(flag){//������������Ƿ����
				String messageKey=MessageKey.WJCF_CFLBDM_CFLBCZ;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			//���������������Ƿ�����������������������޸�
			//�ȿ���...
			boolean result=service.runUpdate(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
			
		}
		model=service.getModel(myForm);
		XtwhShlcService shlcService = new XtwhShlcService();
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("wjcf"));
		request.setAttribute("spjbList", shlcService.getSpjbListByGnmk(model.getSpl()));
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("cflbdmUpdate");
	}
	/**
	 * 
	 * @����:(����������ɾ��)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-24 ����01:33:16
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "����Υ�ʹ���-��������-����������ά��-ɾ��VALUES:{values}")
	public ActionForward cflbdmDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CflbdmwhService service = new CflbdmwhService();
		String values = request.getParameter("values");
		int num = service.runDelete(values.split(","));
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
				: MessageUtil.getText(MessageKey.WJCF_CFLBDM_CFLBYSY);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	public ActionForward getCfqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CflbdmwhForm myForm = (CflbdmwhForm) form;
		CflbdmwhService service = new CflbdmwhService();
		if(StringUtils.isNotNull(myForm.getCflbdm())){
			String cfqx = service.getCfqx(myForm.getCflbdm());
			response.getWriter().print(getJsonMessage(cfqx));
		}
		return null;
	}
	public ActionForward getCfqxBymc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CflbdmwhForm myForm = (CflbdmwhForm) form;
		CflbdmwhService service = new CflbdmwhService();
		if(StringUtils.isNotNull(myForm.getCflbmc())){
			String cfqx = service.getCfqxByMc(myForm.getCflbmc());
			response.getWriter().print(getJsonMessage(cfqx));
		}
		return null;
	}
	
	/**
	 * @����:��ȡ�������𣨸�λ���б��б���ÿ����¼Ӧ����������λ�͸�λ����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��2��27�� ����9:11:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws IOException 
	 */
	public ActionForward getSpjbListById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		String splcId = request.getParameter("splcId");
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> spjbList = shlcService.getSpjbListByGnmk(splcId);
		JSONArray dataList = JSONArray.fromObject(spjbList);
		response.getWriter().print(dataList);
		return null;
	}
}
