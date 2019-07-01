package xsgzgl.szdw.general.szbb;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwSzbbInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_˼��������_Init��
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

public class SzbbInit extends BasicInit {

	/**
	 * ˼�������ࡾ��ѯ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initSzbbSearch(RequestForm rForm, SzdwGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		SzdwGeneralService myService = new SzdwGeneralService();
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �꼶
		String nj=request.getParameter("nj");
		// ѧԺ
		String xydm=request.getParameter("xydm");
		// רҵ
		String zydm=request.getParameter("zydm");
		// �༶
		String bjdm=request.getParameter("bjdm");
		// ����·��
		String path = "szdw_general_szbb.do";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		
		// ------------------Ĭ�ϼ��� �꼶��ѧԺ��רҵ���༶ ��Ϣ begin--------------
		myForm.setNj(nj);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		// ------------------Ĭ�ϼ��� �꼶��ѧԺ��רҵ���༶ ��Ϣ end--------------
		
	}
}
