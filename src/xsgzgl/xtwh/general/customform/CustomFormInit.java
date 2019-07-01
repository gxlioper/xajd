package xsgzgl.xtwh.general.customform;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.RequestForm;
import xgxt.form.User;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ϵ�y�S�o_�Զ��x���_Init��
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

public class CustomFormInit {

	/**
	 * ��ʼ�����ݡ��Զ��x��ι���
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initManage(RequestForm rForm, CustomFormForm myForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xtwh_general_customform.do";
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
	 * ��ʼ�����ݡ��Զ��x��β�����
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initParameter(RequestForm rForm, CustomFormForm myForm,
			User user, HttpServletRequest request) {

		DAO dao = DAO.getInstance();
		
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xtwh_general_customform.do";
		// ����ʱ��
		String cjsj = dao.getNowTime("yyyy-mm-dd hh24:mi:ss");
		// �����ֶ�
		String[] qtzd = new String[] { "cjsj" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { cjsj };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		// ����ģ���б�
		List<HashMap<String, String>> ssmkList = dao.getWhList(
				"xg_xtwh_splcmkdzb", "mkdm", "mkmc", "", "", "", true);
		request.setAttribute("ssmkList", ssmkList);
	}
	
	/**
	 * ��ʼ�����ݡ��Զ��x������á�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initSetting(RequestForm rForm, CustomFormForm myForm,
			User user, HttpServletRequest request) {

		CustomFormService service = new CustomFormService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xtwh_general_customform.do";
		// ��ID
		String form_id = request.getParameter("form_id");
		// ��ʾ˳��
		String xssx = service.getMaxXssx(form_id);
		// �����ֶ�
		String[] qtzd = new String[] { "xssx" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xssx };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ������
		HashMap<String, String> map = service.getCustomFormInfo(form_id);
		request.setAttribute("formInfo", map);
	}
	
	/**
	 * ��ʼ�����ݡ��Զ��x��β�ԃ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initSearch(RequestForm rForm, CustomFormForm myForm,
			User user, HttpServletRequest request) {

		CustomFormService service = new CustomFormService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xtwh_general_customform.do";
		// ��ID
		String form_id = request.getParameter("form_id");
		// ��ʾ˳��
		String xssx = service.getMaxXssx(form_id);
		// �����ֶ�
		String[] qtzd = new String[] { "xssx" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xssx };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ������
		HashMap<String, String> map = service.getCustomFormInfo(form_id);
		request.setAttribute("formInfo", map);
	}
}
