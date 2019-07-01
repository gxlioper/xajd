package xsgzgl.dtjs.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.dtjs.general.tyjf.DtjsTyjfInit;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���Ž���_ͨ��_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class DtjsGeneralAction extends BasicAction {
	
	/**
	 * ���Ž���_��Ա�ɷѹ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward tyjfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsGeneralForm myForm = (DtjsGeneralForm) form;
		DtjsGeneralService service = new DtjsGeneralService();
		DtjsTyjfInit init = new DtjsTyjfInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initTyjf(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/dtjs/" + xxpymc + "/tyjf/tyjfManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
}
