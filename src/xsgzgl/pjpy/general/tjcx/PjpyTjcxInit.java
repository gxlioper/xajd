package xsgzgl.pjpy.general.tjcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;
import xsgzgl.pjpy.general.inter.PjpyTjcxInterface;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʒ���_ͨ��_Init��
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

public class PjpyTjcxInit extends BasicInit {

	/**
	 * �۲�༶����_��ʼ�����ݡ��еȼ����ԡ�
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initZcbjmdDjks(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// ����·��
		String path = "pjpy_tjcx_zcbjmd_djks.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// ��������
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// ��������
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// �۲�����
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ʽ·��
		String stylePath = request.getParameter("stylePath");
		// ������
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * �۲�༶����_��ʼ�����ݡ��޵ȼ����ԡ�
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initZcbjmdNoDjks(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// ����·��
		String path = "pjpy_tjcx_zcbjmd_nodjks.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// ��������
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// ��������
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// �۲�����
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ʽ·��
		String stylePath = request.getParameter("stylePath");
		// ������
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 *����������_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initHjmdhz(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// ����·��
		String path = "pjpy_tjcx_hjmdhz.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// ��������
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// ��������
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// �۲�����
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ʽ·��
		String stylePath = request.getParameter("stylePath");
		// ������
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = new String[] { pjxn };
		searchModel.setSearch_tj_xn(xn);

		request.setAttribute("searchTj", searchModel);
	}

	/**
	 *�񽱽�����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initHjjehz(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// ����·��
		String path = "pjpy_tjcx_hjjehz.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// ��������
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// ��������
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// �۲�����
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ʽ·��
		String stylePath = request.getParameter("stylePath");
		// ������
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = new String[] { pjxn };
		searchModel.setSearch_tj_xn(xn);

		request.setAttribute("searchTj", searchModel);
	}
	
	/**
	 * ѧԺ��ѧ���Ƽ���������
	 * @param request
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initTjmdhz(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// ����·��
		String path = "pjpy_tjcx_tjmdhz.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// ��������
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// ��������
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// �۲�����
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ʽ·��
		String stylePath = request.getParameter("stylePath");
		// ������
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = new String[] { pjxn };
		searchModel.setSearch_tj_xn(xn);

		request.setAttribute("searchTj", searchModel);
	}
	
	/**
	 * ��������ѧ���Ƽ���������
	 * @param request
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initKnsTjmdhz(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// ����·��
		String path = "pjpy_tjcx_knstjmdhz.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// ��������
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// ��������
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// �۲�����
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ʽ·��
		String stylePath = request.getParameter("stylePath");
		// ������
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = new String[] { pjxn };
		searchModel.setSearch_tj_xn(xn);

		request.setAttribute("searchTj", searchModel);
	}
	
	/**
	 *�񽱽�����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initCmhjmdhz(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyTjcxInterface service = myService.getPjpyTjcxService(myForm);

		// ����·��
		String path = "pjpy_tjcx_cmhjmdhz.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// ��������
		String pjzq = jbszModel.getPjzq();
		myForm.setPjzq(pjzq);
		// ��������
		String zypm = jbszModel.getZypm();
		myForm.setZypm(zypm);
		// �۲�����
		String zcpm = jbszModel.getZcpm();
		myForm.setZcpm(zcpm);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ʽ·��
		String stylePath = request.getParameter("stylePath");
		// ������
		String cpz = jbszModel.getCpz();
		myForm.setCpz(cpz);

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "cpz" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, cpz };

		rForm.setStylePath(stylePath);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = new String[] { pjxn };
		searchModel.setSearch_tj_xn(xn);

		request.setAttribute("searchTj", searchModel);
	}
}
