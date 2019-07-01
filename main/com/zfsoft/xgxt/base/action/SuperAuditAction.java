/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014��6��9�� ����9:27:09 
 */  
package com.zfsoft.xgxt.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import common.newp.StringUtil;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014��6��9�� ����9:27:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public abstract class SuperAuditAction<T extends SuperAuditModel,S extends SuperAuditService<T,?>> extends SuperAction<T,S>{

	private String gnid;
	private String squrl;
	private String shurl;
	
	protected SuperAuditAction(String gnid ,String squrl,String shurl){
		this.gnid = gnid;
		this.squrl = squrl;
		this.shurl = shurl;
	}
	
	
	/**
	 * 
	 * @����: �ύ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��9�� ����4:10:14
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
	@SuppressWarnings("unchecked")
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T model = (T) form;
		
		JSONObject message = submit(gnid , model.getId(), squrl, shurl);
		response.getWriter().print(message);
		
		return null;
	}
	
	
	/**
	 * 
	 * @����: �������ύ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��9�� ����4:53:42
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
	@SuppressWarnings("unchecked")
	public ActionForward saveAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T model = (T) form;
		S service = getService();
		boolean isSuccess = false;
		//���������¼��Ӧ�����״̬������С�
		model.setShzt(Constants.YW_SHZ);
		
		//��֤����ID�����ID��һ���ԣ���ϵͳ����ΨһID
		if (StringUtil.isNull(model.getId())){
			String uuid = UniqID.getInstance().getUniqIDHash().toUpperCase();
			model.setId(uuid);
			
			//���������¼
			isSuccess = service.runInsert(model);
		} else {
			isSuccess = service.runUpdate(model);
		}
		
		JSONObject message = null;
		
		if (isSuccess){
			//�ύ���뵽�������
			message = submit(gnid , model.getId(), squrl, shurl);
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	

	/**
	 * 
	 * @����: �޸ĺ��ύ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��9�� ����5:09:21
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
	@SuppressWarnings("unchecked")
	public ActionForward updateAndSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T model = (T) form;
		S service = getService();
		
		//��ִ���޸Ĳ���
		boolean isSuccess = service.runUpdate(model);
		JSONObject message = null;
		
		if (isSuccess){
			//�ύ���뵽�������
			message = submit(gnid , model.getId(), squrl, shurl);
		} else {
			message = getJsonMessageByKey(MessageKey.SYS_SUBMIT_FAIL);
		}
		
		response.getWriter().print(message);
		return null;
	}
	
	
	
	/*
	 * @����: �ύ����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��9�� ����4:04:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnid ��Ӧ���������е�ID
	 * @param id �����¼ID
	 * @param squrl ���������ת·��
	 * @param shurl ��˴�����ת·�� 
	 * @return
	 * @throws Exception
	 * JSONObject �������� 
	 * @throws
	 */
	private JSONObject submit(String gnid,String id,String squrl,String shurl)
			throws Exception {
		
		ShlcInterface shlc = new CommShlcImpl();
		
		S service = getService();
		//��ѯ�����¼
		T model = service.getModel(id);
		String splcid = model.getSplcid();
		//�ύ��������
		boolean isSuccess = shlc.runSubmit(id, splcid, model.getXh(), shurl, squrl);
		
		if(isSuccess){
			//���������¼״̬
			model.setShzt(Constants.YW_SHZ);
			//model.setSplcid(splcid);
			isSuccess = service.runUpdate(model);
		}
		
		String message = isSuccess ? 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_SUCCESS) : 
						 MessageUtil.getText(MessageKey.SYS_SUBMIT_FAIL);

		return getJsonMessage(message);
	}
	
	
	/**
	 * 
	 * @����: ȡ���ύ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��10�� ����8:52:28
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
	@SuppressWarnings("unchecked")
	public ActionForward cancelSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T t = (T) form;
		S service = getService();
		ShlcInterface shlc = new CommShlcImpl();
		
		T model = service.getModel(t.getId());
		
		boolean isSuccess = shlc.firstStepCancle(model.getId(), model.getSplcid());
		
		if(isSuccess){
			//����ҵ��״̬Ϊ'δ�ύ'
			model.setShzt(Constants.YW_WTJ);
			service.runUpdate(model);
		}
		String messageKey = isSuccess ? 
				MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����: ��˲���
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��10�� ����9:08:55
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
	@SuppressWarnings("unchecked")
	public ActionForward submitAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T t  = (T) form;
		S service = getService();
		ShlcInterface shlc = new CommShlcImpl();
		User user = getUser(request);
		
		ShxxModel shxxModel = new ShxxModel();
		BeanUtils.copyProperties(shxxModel, t);
		
		T model = service.getModel(t.getId());
				
		shxxModel.setYwid(t.getId());
		shxxModel.setShlc(model.getSplcid());
		shxxModel.setShr(user.getUserName());
		shxxModel.setShzt(t.getShjg());
		shxxModel.setSqrid(model.getXh());

//		shxxModel.setShyj(t.getShyj());
//		shxxModel.setThgw(t.getThgw());
//		shxxModel.setGwid(t.getGwid());
		shxxModel.setTzlj(shurl);
		shxxModel.setTzljsq(squrl);
		
		try {
			//��˲���{����һ�����ݵ���˱���}
			String zhzt  = shlc.runAuditing(shxxModel); 
			model.setShzt(zhzt);//��ȡ���״̬��־��������Model
			
			boolean result = service.runUpdate(model);//���������{�������������״̬��Ϣ}
			
			//������ͨ�� ����һ�����ݵ������
			if(result && Constants.SH_TG.equals(zhzt)){ 
				BeanUtils.copyProperties(model, shxxModel);
				result = service.afterLastAudit(model);
			}
			
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * @����: ȡ�����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��10�� ����2:19:20
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
	@SuppressWarnings("unchecked")
	public ActionForward cancelAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		T t  = (T) form;
		S service = getService();
		User user = getUser(request);
		
		//���� �����¼��Ӧ���״̬Ϊ������С�
		t.setShzt(Constants.YW_SHZ);
		T model = service.getModel(t);
		
		boolean isSuccess = new CommShlcImpl().runCancel(user.getUserName(), t.getId(), model.getSplcid(), t.getGwid());
		
		if(isSuccess){
			isSuccess = service.runUpdate(t); 
			service.deleteResult(t); 
		}
		
		String messageKey = isSuccess ? MessageUtil.getText(MessageKey.SYS_CANCEL_SUCCESS) :  MessageUtil.getText(MessageKey.SYS_CANCEL_FAIL);
		response.getWriter().print(getJsonMessage(messageKey));
		
		return null;
	}
	
	
	
}
