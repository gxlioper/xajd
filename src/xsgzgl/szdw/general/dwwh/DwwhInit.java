package xsgzgl.szdw.general.dwwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwDwwhInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_˼������ά��_Init��
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

public class DwwhInit extends BasicInit {

	/**
	 * ˼������ά������ѯ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initDwwhSearch(RequestForm rForm, SzdwGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		
		String path = myForm.getPath();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
	}

	/**
	 * ˼����Ա��ࡾ���á�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initRybbSetting(RequestForm rForm, SzdwGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "szdw_general_dwwh.do";
		// ����
		String lx = request.getParameter("lx");
		// ��������
		String lxmc ;
		if (Base.xxdm.equals("12834")) {
			lxmc = "fdy".equalsIgnoreCase(lx) ? "��ӳ�" : "�жӳ�";
		}else {
			lxmc = "fdy".equalsIgnoreCase(lx) ? "����Ա" : "������";
		}
		// ְ����
		String zgh = request.getParameter("zgh");
		// �����ֶ�
		String[] qtzd = new String[] { "lx", "zgh", "lxmc" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { lx, zgh, lxmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		//================��ʼ������  begin=====================
		
		DwwhModel model = new DwwhModel();
		model.setLx(lx);
		model.setZgh(zgh);
		
		HashMap<String, String> map = service.getDwwh(model, user);
		request.setAttribute("rs", map);
		
		// �����б�
		List<HashMap<String, String>> bmList = service.getBmList();
		request.setAttribute("bmpyList", bmList);
	
		//================��ʼ������  end =====================
	}

	/**
	 * ��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initParameter(SzdwGeneralForm myForm) throws Exception {

		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwDwwhInterface service = myService.getSzdwDwwhService(myForm);
		service.initParameter();
	}
}
