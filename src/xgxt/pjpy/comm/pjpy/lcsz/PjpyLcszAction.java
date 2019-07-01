package xgxt.pjpy.comm.pjpy.lcsz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyInit;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��������_action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyLcszAction extends BasicAction {

	/**
	 * ��������_��������_������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjlcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyLcszForm myForm = (PjpyLcszForm) form;
		PjpyLcszService service = new PjpyLcszService();
		PjpyInit init = new PjpyInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getPjlcszRForm(rForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		// =================== end ===================
		
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			this.deleteOperation(request, "XG_PJPY_LCSZB");
		}
		
		// =================== ��ʼ���б�ֵ ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		request.setAttribute("rs", service.getLcsz());
		request.setAttribute("topTr", new BasicService().getTopTr("XG_PJPY_LCSZB", 
									new String[]{"lcdj", "lcmc", "kssj", "jssj"}));
		// =================== end ===================

		return mapping.findForward("pjlcsz");
	}

	/**
	 * ��������_��������_������������(ά��)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjlcszOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyLcszForm myForm = (PjpyLcszForm) form;
		PjpyLcszService service = new PjpyLcszService();
		PjpyInit init = new PjpyInit();
		User user = getUser(request);// �û�����
		
		String pkValue = request.getParameter("pkValue");

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getPjlcszRForm(rForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		// =================== end ===================

		// ============= ִ�б������ ============
		if ("save".equalsIgnoreCase(doType)) {

			boolean flag = service.savePjpyLcsz(myForm, rForm, user, request);
			String message = null;

			if(flag){
				pkValue = myForm.getLcdj();
				message = MessageInfo.MESSAGE_SAVE_SUCCESS;
			}else {
				message = MessageInfo.MESSAGE_SAVE_FALSE;
			}
			
			rForm.setMessage(message);
		}
		// =================== end ==============

		// ============= ������̵ȼ� =================
		List<Integer> lcdjList = service.getLcdj();
		
		// ================= end ========================

		// ================== ����ָ������ ================
		if(StringUtils.isNotNull(pkValue)){
			Map<String, String> rs = service.getLcForPk(pkValue);
			request.setAttribute("rs", rs);
			if(StringUtils.isNotNull(rs.get("lcdj"))){
				lcdjList = PjpyLcszService.sort(lcdjList, Integer.parseInt(rs.get("lcdj")));
			}
		}
		// ================== end ========================
	
		// =================== ��ʼ���б�ֵ ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		
		request.setAttribute("lcdjList", lcdjList);
		// =================== end ===================
		
		return mapping.findForward("pjlcszOne");
	}
}