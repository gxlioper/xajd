package xgxt.studentInfo.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxTjszService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ-ͳ������-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * 12 Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * 
 * @version 1.0
 */

public class XsxxTjszAction extends BasicAction{

	/**
	 * ͳ������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward tjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		StudentInfoForm myForm = (StudentInfoForm) form;
		XsxxTjszService service = new XsxxTjszService();
		User user = getUser(request);// �û�����

		// ================= ����ֵ ==================
		// ����·��
		String path = "xsxx_tjsz.do?method=tjszManage";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����ģ��
		String gnmk = "xsxx";
		// �˵�
		String menu = "";
		// ��ʾ��Ϣ
		String message = "";
		// =================end==================

		// =================ִ�б������==================;
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveTjsz(myForm, user);
			message = flag ? "�����ɹ�" : "����ʧ��";
		}
		// =================end==================

		// =================���DB��������==================
		List<HashMap<String, Object>> rsList = service.getDbPzList(myForm);
		// =================end==================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setMessage(message);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, user, request);
		request.setAttribute("rsList", rsList);
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("rsNum", rsList.size());
		}
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);

		service.setList(model, rForm, request);
		// =================end ===================

		return mapping.findForward("tjszManage");
	}
}
