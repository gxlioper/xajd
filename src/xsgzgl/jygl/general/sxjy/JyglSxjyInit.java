package xsgzgl.jygl.general.sxjy;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.jygl.general.JyglGeneralForm;
import xsgzgl.jygl.general.JyglGeneralService;
import xsgzgl.jygl.general.inter.JyglSxjyInterface;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjJgcxInterface;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ҵ����_ʵϰ��ҵ_ͨ��_Init��
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

public class JyglSxjyInit extends BasicInit {

	/**
	 * ��ʵϰ��ҵ - ����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initSxjyManage(RequestForm rForm, JyglGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		JyglGeneralService myService = new JyglGeneralService();
		JyglSxjyInterface service = myService.getJyglSxjyService(myForm);
		JyglSxjyModel model = new JyglSxjyModel();

		// ����·��
		String path = "jygl_general_sxjy.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// ��ͼ
		String tableName = model.getSearch_table();
		// ��
		String realTable = model.getSave_table();
		// �û����
		String userStatus = user.getUserStatus();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �����ֶ�
		String[] qtzd = new String[] { "userStatus" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { userStatus };

		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * ��ʵϰ��ҵ - ά����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initSxjyUpdate(RequestForm rForm, JyglGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		JyglGeneralService myService = new JyglGeneralService();
		JyglSxjyInterface service = myService.getJyglSxjyService(myForm);
		JyglSxjyModel model = new JyglSxjyModel();
		
		// ����·��
		String path = "jygl_general_sxjy.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �����ֶ�
		String[] qtzd = new String[] { "ryfw" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { "view_xsjbxx" };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================��ʼ��ҳ���������=====================

		// ��ϸ��Ϣ
		HashMap<String, String> map = new HashMap<String, String>();
		
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			// ����
			String pkValue = request.getParameter("pkValue");
			model.setPkValue(new String[] { pkValue });
			map = service.getSxjyMap(model);
		}

		request.setAttribute("rs", map);
	}
}
