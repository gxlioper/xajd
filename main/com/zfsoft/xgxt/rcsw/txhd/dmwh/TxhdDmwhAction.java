/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-20 ����11:20:20 
 */  
package com.zfsoft.xgxt.rcsw.txhd.dmwh;

import java.net.URLDecoder;
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

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-��ѧ�
 * @�๦������: ��ѧ�Action
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-6-20 ����11:20:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TxhdDmwhAction extends SuperAction {
	
	private static final String url = "rcsw_txhd_dmwh.do";
	
	/**
	 * 
	 * @����:������鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����04:32:11
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
	@SystemAuth(url = url)
	public ActionForward lbdmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		
		if (QUERY.equals(model.getType())){
			String lbmc = request.getParameter("lbmc"); 
			lbmc = URLDecoder.decode(URLDecoder.decode(lbmc,"UTF-8"),"UTF-8");
			model.setLbmc(lbmc);
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_txhd_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("lbdmList");
	}
	
	
	/**
	 * 
	 * @����:����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����04:32:30
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
	@SystemLog(description="�����ճ�����-��ѧ�-����ά��-����")
	public ActionForward addLbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�ж����ʹ���������Ƿ����
			boolean isExist=service.isExistByLbmc(model);
			if(!isExist){
				int nextLbdm=service.getNextLbdm();
				model.setLbdm(nextLbdm+"");
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_LBDMEXIST));
				return null;
				
			}
		}
		
		return mapping.findForward("addLbdm");
	}
	
	
	/**
	 * 
	 * @����:�޸�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����04:45:06
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
	@SystemLog(description="�����ճ�����-��ѧ�-����ά��-�޸�LBDM:{lbdm}")
	public ActionForward updateLbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		TxhdDmwhForm myModel = service.getModel(model);
		
		if (UPDATE.equalsIgnoreCase(model.getType())){
			//�ж���û�޸���Ŀ�������
			if(!model.getLbmc().trim().equals(myModel.getLbmc().trim())){
				//�жϽ�����Ƿ���ʹ��
				String checkLbdmForJg=service.checkLbForJg(model.getLbdm());
				//�ж���Ŀ�����Ƿ���ʹ��
				String checkLbdmForXmwh=service.checkLbForXmwh(model.getLbdm());
				
				if(!checkLbdmForJg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.RCSW_TXHD_LBDMEXIST_JG_UPDATE,checkLbdmForJg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				
				if(!checkLbdmForXmwh.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.RCSW_TXHD_LBDMEXIST_XMWH_UPDATE,checkLbdmForXmwh);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//û���޸�����ֱ�ӱ���
				model.setLbmc(myModel.getLbmc().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			boolean isExist=service.isExistByLbmc(model);
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_LBDMEXIST));
				return null;
			  
			}
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myModel));
		
		return mapping.findForward("updateLbdm");
		
	}
	
	
	/**
	 * 
	 * @����:ɾ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-20 ����05:15:51
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
	@SystemLog(description="�����ճ�����-��ѧ�-����ά��-ɾ��VALUES:{values}")
	public ActionForward delLbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhService service = new TxhdDmwhService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//�жϽ�����Ƿ���ʹ��
			String checkLbdmForJg=service.checkLbForJg(values);
			//�ж���Ŀ�����Ƿ���ʹ��
			String checkLbdmForXmwh=service.checkLbForXmwh(values);
			
			if(!checkLbdmForJg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_TXHD_LBDMEXIST_JG_DEL,checkLbdmForJg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkLbdmForXmwh.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_TXHD_LBDMEXIST_XMWH_DEL,checkLbdmForXmwh);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-9-17 ����03:24:20
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
	@SystemAuth(url = url)
	public ActionForward getHdgglist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		
		if (QUERY.equals(model.getType())){
			String hdggmc = request.getParameter("hdggmc"); 
			hdggmc = URLDecoder.decode(URLDecoder.decode(hdggmc,"UTF-8"),"UTF-8");
			model.setHdggmc(hdggmc);
			List<HashMap<String,String>> resultList = service.getHdggList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_txhd_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("hdgglist");
	}
	
	//���ӻ���
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addHdgg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getHdggList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "rcsw_txhd_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("addHdgg");
	}
	
	//�������ӻ���
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveAddNewHdgg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		String message = null;
		//�����ظ���֤
		if(service.checkIsExits(model)){
			message = MessageUtil.getText(MessageKey.RCSW_TXHD_DMWH_HDGG_REPEAT,model.getHdggmc());;
			response.getWriter().print(getJsonMessage(message));
		}else{
			boolean result = service.saveHdgg(model);
			message = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
		}
		return null;
	}
	
	//�޸Ļ���
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateHdgg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdDmwhForm model = (TxhdDmwhForm) form;
		request.setAttribute("hdggmc", model.getHdggmc());
		request.setAttribute("hdggdm", model.getHdggdm());
		return mapping.findForward("updateHdgg");
	}
	
	//�����޸Ļ���
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveUpdateNewHdgg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		

		TxhdDmwhForm model = (TxhdDmwhForm) form;
		TxhdDmwhService service = new TxhdDmwhService();
		String message = null;
		//�����ظ���֤
		if(service.checkIsExits(model)){
			message = MessageUtil.getText(MessageKey.RCSW_TXHD_DMWH_HDGG_REPEAT,model.getHdggmc());;
			response.getWriter().print(getJsonMessage(message));
		}else{
			boolean result = service.saveUpdateHdgg(model);
			message = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(message));
		}
		return null;
	}
	
	//ɾ������
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delhdgg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		TxhdDmwhService service = new TxhdDmwhService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			int num = service.delHdgg(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

}
