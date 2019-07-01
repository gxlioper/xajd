/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-13 ����10:13:18 
 */  
package com.zfsoft.xgxt.dagl.qdmb;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.utils.FormModleCommon;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.dagl.qdcl.DaqdclService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�  wanghj [���ţ�1004]
 * @ʱ�䣺 2014-2-13 ����10:13:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DaqdmbAction extends SuperAction {
	
	private static final String url = "daqdmb.do?method=daqdmbList";

	@SystemAuth(url = url)
	public ActionForward daqdmbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DaqdmbService service = new DaqdmbService();
		DaqdmbForm myForm = (DaqdmbForm) form;
		
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "daqdmb.do?method=daqdmbList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("daqdmbList");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��������-�����嵥ģ��ά��-����DAQDMB_MC:{daqdmb_mc},QYZT:{qyzt},DAQDCLIDS:{daqdclIds}")
	public ActionForward addDaqdmb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdmbService service = new DaqdmbService();
		DaqdmbForm myForm = (DaqdmbForm) form;
		if (SAVE.equalsIgnoreCase(myForm .getType())){
			String daqdclIds = request.getParameter("daqdclIds");
			String guid = UniqID.getInstance().getUniqIDHash();
			myForm.setDaqdmb_id(guid);
			boolean result = false;
			boolean saveDambcl = false;
			if(!StringUtil.isNull(daqdclIds)){
				//���浵���嵥ģ���
				 result = service.runInsert(myForm);
				//���浵���嵥ģ������м��
				String[] daqdcl = daqdclIds.split(",");
				saveDambcl = service.addMbclInfo(guid, daqdcl);
			}
			String messageKey = result&&saveDambcl ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		DaqdclService daqdclSv = new DaqdclService();
		List<HashMap<String, String>> daqdclList = daqdclSv.getDaqdclAllList();

		request.setAttribute("daqdclList", daqdclList);
		return mapping.findForward("addDaqdmb");
	}
	/**
	 * �޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��������-�����嵥ģ��ά��-�޸�DAQDMB_ID:{daqdmb_id},DAQDCLIDS:{daqdclIds},DAQDMB_MC:{daqdmb_mc},QYZT:{qyzt}")
	public ActionForward updateDaqdmb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdmbService service = new DaqdmbService();
		DaqdmbForm myForm = (DaqdmbForm) form;
		String daqdmb_id = myForm.getDaqdmb_id();
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			String daqdclIds = request.getParameter("daqdclIds");
			boolean result = false;
			boolean delDambcl = false;
			boolean saveDambcl = false;
			if(!StringUtil.isNull(daqdclIds)){
			
				result = service.runUpdate(myForm);
				//��ɾ���ģ������м��
				int delCount = service.delMbclInfo(daqdmb_id);
				if(delCount>0){
					delDambcl = true;
				}
				String[] daqdcl = daqdclIds.split(",");
				saveDambcl = service.addMbclInfo(daqdmb_id, daqdcl);
			}
			String messageKey = result&&delDambcl&&saveDambcl ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		DaqdmbForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		
		List<HashMap<String, String>> daqdclList = service.getDaqdclListByMbid(daqdmb_id);
		request.setAttribute("daqdclList", daqdclList);
		
		return mapping.findForward("updateDaqdmb");
	}
	
	
	/**
	 * ɾ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��������-�����嵥ģ��ά��-ɾ��DAQDMBIDS:{daqdmbIds}")
	public ActionForward delDaqdmb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdmbService service = new DaqdmbService();
		
		String daqdmbIds = request.getParameter("daqdmbIds");
		String[] daqdmb_id = daqdmbIds.split(",");
		if (!StringUtil.isNull(daqdmbIds)){
			//��ɾ���ģ������м��
			int delCount = service.delBatchMbcl(daqdmb_id);
			//ɾ��ģ���嵥��
			int num = service.runDelete(daqdmb_id);
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
	/**
	 * �鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewDaqdmb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdmbService service = new DaqdmbService();
		DaqdmbForm myForm = (DaqdmbForm) form;
		String daqdmb_id = myForm.getDaqdmb_id();
		HashMap<String, String> daqdmbInfo = service.getDaqdmbById(daqdmb_id);
		List<HashMap<String, String>> mbclList = service.getDaqdclListByMbid(daqdmb_id);
		request.setAttribute("rs", daqdmbInfo);
		request.setAttribute("clList", mbclList);
		return mapping.findForward("viewDaqdmb");
	}
	
	public ActionForward resumeFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdmbService service = new DaqdmbService();
		DaqdmbForm myForm = (DaqdmbForm) form;
		String flag = "0";
		
		HashMap<String, String> daqdmbInfo = service.getDaqdmbByName(myForm.getDaqdmb_mc());
		if(daqdmbInfo!=null && daqdmbInfo.size()>0){
			flag = "1";
		}
		
		response.getWriter().print(flag);
		return null;
	}
	
	
	/**
	 * �޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��������-�����嵥ģ��ά��-�޸�DAQDMBIDS:{daqdmbIds}")
	public ActionForward updateQdmbQyzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdmbService service = new DaqdmbService();
		DaqdmbForm myForm = (DaqdmbForm) form;
		String daqdmbIds = request.getParameter("daqdmbIds");
		if (!StringUtil.isNull(daqdmbIds)){
			String[] daqdmb_id = daqdmbIds.split(",");
			boolean result = service.updateQdmbQyzt(myForm.getQyzt(), daqdmb_id);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		return null;
	}
}
