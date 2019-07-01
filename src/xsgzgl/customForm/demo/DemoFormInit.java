package xsgzgl.customForm.demo;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.form.User;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ϵ�y�S�o_�Զ��x���_DEMO_Init��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ������
 * @version 1.0
 */

public class DemoFormInit {
		
	/**
	 * ��ʼ�����ݡ��Զ��x��ι���
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initDemoFormManage(RequestForm rForm, DemoFormForm myForm,
			User user, HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "customForm.do";

		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
	
	/**
	 * ��ʼ�����ݡ��@ʾ�ֶ��O�á�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initXszdSetup(RequestForm rForm, DemoFormForm myForm,
			User user, HttpServletRequest request) {

		DemoFormService service = new DemoFormService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "customForm.do";
		// FormID
		String form_id = request.getParameter("form_id");
		// ���ٱ�
		String ssb = request.getParameter("ssb");
		// �@ʾ���
		String xssx = "1";
		// �����ֶ�
		String[] qtzd = new String[] { "ssb", "form_id", "xssx" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { ssb, form_id, xssx };

		myForm.setForm_id(form_id);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// =================��ʼ��������Ϣ==============================
		// ���ٱ��ֶ��б�
		List<HashMap<String, String>> zdList = service.getZdList(ssb);
		request.setAttribute("zdList", zdList);
	}
	
	/**
	 * ��ʼ�����ݡ��Y����ԃ�O�á�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initJgcxSetup(RequestForm rForm, DemoFormForm myForm,
			User user, HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "customForm.do";

		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}
}
