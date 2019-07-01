package xgxt.xtwh.kjfs;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.xtwh.XtwhInit;
import xgxt.xtwh.sysz.SyszService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_��ݷ�ʽ_action��
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

public class KjfsAction extends BasicAction {

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
	public ActionForward kjfsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KjfsForm myForm = (KjfsForm) form;
		KjfsService service = new KjfsService();
		XtwhInit init = new XtwhInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getXtwhKjfsInit(rForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		// =================== end ===================

		// ============= ִ�б������ ============
		if ("save".equalsIgnoreCase(doType)) {

			boolean flag = service.saveYhKjfs(myForm, user);

			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;

			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== ��ʼ��ҳ����ʾֵ ===========
		List<HashMap<String, String>> rsList = service
				.setKjfsList(myForm, user);
		if (rsList != null && rsList.size() > 0) {
			myForm.getPages().setPageSize(18);
		}
		rForm.setRsList(service.getResultList(rsList, myForm.getPages()));
		// �û�Ȩ��List
		List<HashMap<String, String>> yhqxList = service.getKjfsList(user);
		// ================= end =====================

		// ============= ����request��ֵ =============
		
		//�����б�
		request.setAttribute("fwlbList", service.getFwlb(myForm, user));
		service.setRequestValue(rForm, request);
		request.setAttribute("yhqxList", yhqxList);
		// =================== end ===================

		return mapping.findForward("kjfsManage");
	}
	
	/**
	 * ��ݷ�ʽ��ת��ѧ���û���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stuKjfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		KjfsService service = new KjfsService();
		User user = getUser(request);

		// ================= ����ֵ ==================
		// ��ݷ�ʽ·��
		String path = request.getParameter("path");
		path = path.replace("'", "");
		// ����·��
		String url = "/main1.jsp";
		// �Ƿ񱻷����·��
		boolean flag = service.hadQx(user, path);
		// =================end ===================

		// ==================�Ƿ��ܹ����ʼ�� ==================
		if (!flag) {
			String msg = "�Բ�����û�з��ʸ�ģ���Ȩ�ޣ�����ϵ����Ա��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else {
			String menu = service
					.getOneValue("gnmkdmb", "gnmkdm", "dyym", path);
			if (!Base.isNull(menu) && menu.length() == 7) {
				String menuTop = menu.substring(0, 3);
				String menuLeft = menu.substring(0, 5);

				url += "?menuTop=" + menuTop + "";
				url += "&menuLeft=" + menuLeft + "";
				url += "&gnmkpath=" + path + "";
			}
		}
		// =================end ===================

		return new ActionForward(url, false);
	}
}