/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-24 ����11:04:44 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.zxsgl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xljkwzdx.common.StringTools;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ-��ѯʦ����
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-24 ����11:04:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxsxxAction extends SuperAction{
	
	private static final String url = "xljk_xljkzx_zxsgl.do";

	/**
	 * 
	 * @����:��ѯ��ѯʦ����ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-24 ����11:24:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//��ȡ����Ȩ��
		request.setAttribute("path", "xljk_xljkzx_zxsgl.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("queryZxsxx");
	}
	
	/**
	 * 
	 * @����:��ѯ��ѯʦ
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-24 ����11:50:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryZxsxxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxsxxForm model  = (ZxsxxForm) form;
		ZxsxxService service = new ZxsxxService();
		
		//���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:�����ѯʦ����ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-24����01:32:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zgh = request.getParameter("zgh");
		HashMap<String, String> zxsInfo=new HashMap<String,String>();
		ZxsxxService service = new ZxsxxService();
		if(!StringUtil.isNull(zgh)){                 
			if(!service.zxsxxIsExist(zgh)){              //�жϸý�ʦ�Ѿ���������ѯʦ
				zxsInfo = service.getFdyInfo(zgh);		 //������     ��ѯ���ý�ʦ��Ϣ��ǰ̨��ʾ
			}else{
				request.setAttribute("zxsxxIsExisttip", "true");//����     �ſ�ǰ̨��ʾ����
			}
	 	}
		request.setAttribute("zxsInfo", zxsInfo);
		
		return mapping.findForward("addZxsxx");
	}
	
	/**
	 * 
	 * @����:�����ѯʦ
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-24����02:50:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addZxsxxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxForm model  = (ZxsxxForm) form;
		ZxsxxService service = new ZxsxxService();
		
		boolean isSuccess = service.runInsert(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:�޸���ѯʦ����ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-24����10:20:39 
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
	public ActionForward updateZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxForm model  = (ZxsxxForm) form;
		ZxsxxService service = new ZxsxxService();
		
		ZxsxxForm dataModel = service.getModel(model.getZgh());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		
		HashMap<String, String> zxsInfo=new HashMap<String,String>();
		zxsInfo = service.getFdyInfo(model.getZgh());
		request.setAttribute("zxsInfo", StringUtils.formatData(zxsInfo));
		
		return mapping.findForward("updateZxsxx");
	}
	
	/**
	 * 
	 * @����:�޸���ѯʦ
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-24����03:32:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateZxsxxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxForm model  = (ZxsxxForm) form;
		ZxsxxService service = new ZxsxxService();
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ����ѯʦ
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-23 ����03:23:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxService service = new ZxsxxService();
		
		String zghs = request.getParameter("zghs"); //��ɾ����ְ����
		
		int isSuccess = service.runDelete(zghs.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
	/**
	 * 
	 * @����:�鿴��ѯʦ
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-25 ����10:22:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewZxsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxForm model  = (ZxsxxForm) form;
		HashMap<String, String> zxsInfo=new HashMap<String,String>();
		ZxsxxService service = new ZxsxxService();
		zxsInfo = service.getFdyInfo(model.getZgh());		 //��ѯ��ʦ��Ϣ��ǰ̨��ʾ
		request.setAttribute("zxsInfo", StringUtils.formatData(zxsInfo));
		
		ZxsxxForm dataModel = service.getModel(model.getZgh()); //��ѯ�ý�ʦ��Ϊ��ѯʦ����Ϣ��ǰ̨��ʾ
		BeanUtils.copyProperties(model, dataModel);
		model.setZxsjj(StringTools.strOutNull(model.getZxsjj()).replaceAll(" ", "&nbsp"));
		if(model.getStatus().equals("0")){
			model.setStatus("���ڸ�");
		}else if(model.getStatus().equals("1")){
			model.setStatus("�ڸ�");
		}
		if(model.getKjdrs()!=null&&!model.getKjdrs().trim().equals("")){
		}else{
			model.setKjdrs("������");
		}
		return mapping.findForward("viewZxsxx");
	}
	
	/**
	 * 
	 * @����:�����ڸ�״̬����ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-25 ����08:52:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZxsxxStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zghs = request.getParameter("zghs");
		request.setAttribute("zghs", zghs);
		return mapping.findForward("setZxsxxStatus");
	}
	
	/**
	 * 
	 * @����:�����ڸ�״̬
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-25 ����08:52:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZxsxxStatusAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String zghs = request.getParameter("zghs");
		String status = request.getParameter("status");
		ZxsxxService service = new ZxsxxService();
		zghs = zghs.replace(",", "','");
		zghs = "'"+zghs+"'";
		boolean isSuccess = service.setZxsxxStatus(zghs, status);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @����:��ѯԤԼ˵������ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-25 ����09:30:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZxyysm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZxsxxService service = new ZxsxxService();
		String zxyysm = service.getZxyysm();
		request.setAttribute("zxyysm", zxyysm);
		return mapping.findForward("setZxyysm");
	}
	
	/**
	 * 
	 * @����:��ѯԤԼ˵��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-25 ����09:30:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward setZxyysmAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String zxyysm = request.getParameter("zxyysm");
		
		ZxsxxService service = new ZxsxxService();
		
		boolean isSuccess = service.setZxyysm(zxyysm);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;	
	}
}
