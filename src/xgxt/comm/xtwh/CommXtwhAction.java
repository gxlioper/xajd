package xgxt.comm.xtwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class CommXtwhAction extends BasicAction {

	/**
	 * ��ҳ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommXtwhService service = new CommXtwhService();
		CommXtwhForm myForm = (CommXtwhForm) form;
		User user = getUser(request);

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xtwh_sywh.do";
		// ����ģ��
		// String gnmk = "xtwh";
		// �˵�
		// String menu = "sywh";
		// ��ʾ��Ϣ
		String message = "";
		// =================end==================

		// ==================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveSysz(myForm, request);
			message = result ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// ================= ��ҳ������Ϣ==================
		HashMap<String, String> rs = service.getSyInfo(myForm);
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setRs(rs);
		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		// ����
		// =================end ===================

		return mapping.findForward("syManage");
	}
	
	/**
	 * ��ݷ�ʽά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kjfsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommXtwhService service = new CommXtwhService();
		CommXtwhForm myForm = (CommXtwhForm) form;
		User user = getUser(request);

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xtwh_kjfs.do";
		// ģ�����
		String title = "�û���ݷ�ʽ����";
		// ����ģ��
		String gnmk = "xtwh";
		// �˵�
		String menu = "kjfs";
		// ��ʾ��Ϣ
		String message = "";
		// =================end==================

		// ==================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveKjfssz(myForm, user);
			message = result ? "�����ɹ�\n����ˢ��ҳ���ſɿ���Ч����" : "����ʧ��";
		}
		// =================end ===================

		// ================= ��ҳ������Ϣ==================
		List<HashMap<String, String>> rsList = service
				.setKjfsList(myForm, user);
		int size = 0;
		if (rsList != null && rsList.size() > 0) {
			size = rsList.size();
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "size" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(size) };

		rForm.setTitle(title);
		rForm.setRsList(rsList);
		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, user, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("kjfsUpdate");
	}
	
	/**
	 * ��֤�û�Ȩ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward yzyhqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommXtwhService service = new CommXtwhService();
		User user = getUser(request);

		// ================= ����ֵ ==================
		// ��ݷ�ʽ·��
		String path = request.getParameter("path");
		path=path.replace("!!@!!", "&");
		path = path.replace("'", "");
		// �Ƿ񱻷����·��
		boolean flag = service.hadQx(user, path);
		HttpSession session = request.getSession();
		session.setAttribute("clickPath", path);
		// =================end ===================

		// ==================�Ƿ��ܹ����ʼ�� ==================
		if (!flag) {
			String msg = "�Բ�����û�з��ʸ�ģ���Ȩ�ޣ�����ϵ����Ա��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================
		request.setAttribute("widthType","kjfs");
		return new ActionForward("/" + path, false);
	}
}