package xsgzgl.pjpy.general.wdpj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyWdpjInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjJgcxInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjLssbInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;
import xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbModel;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����_ͨ��_Init��
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

public class PjpyWdpjInit extends BasicInit {

	/**
	 * �ҵ�����_��ʼ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initWdpj(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInterface pjxmService = myService.getPjszPjxmService(myForm);
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);

		// ����·��
		String path = "pjpy_general_wdpj.do";
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq };

		// ==================�ܷ������� begin==================
		String message = "";
		String operation = myService.getOperation("113");// ���������������
		if ("later".equalsIgnoreCase(operation)) {
			message = "����������Ѿ�������û��ύ���������ٽ�����ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		} else if ("early".equalsIgnoreCase(operation)) {
			message = "�����������ǰһ����δ������û��ύ�������ܶ��������ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		}
		// =================�ܷ������� end ===================

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);

		List<HashMap<String, String>> lssbxmList = pjxmService.getLssbXmList();

		request.setAttribute("xmList", lssbxmList);

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
	}

	/**
	 * ѧ������_��ʼ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initXssq(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		PjpyWdpjService wdpjService = new PjpyWdpjService();
		WdpjXssqInterface xssqService = myService.getWdpjXssqService(myForm);
		
		// ����·��
		String path = "pjpy_general_wdpj.do";
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq };

		String message = "";
		String operation = myService.getOperation("113");// ���������������
		if ("later".equalsIgnoreCase(operation)) {
			message = "����������Ѿ�������û��ύ���������ٽ�����ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		} else if ("early".equalsIgnoreCase(operation)) {
			message = "�����������ǰһ����δ������û��ύ�������ܶ��������ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		}

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
		// ====================��ʼ��ҳ���������=====================
		// ��Ŀ�����б�
		List<HashMap<String, String>> xmxzList = wdpjService.getXmxzList();
		request.setAttribute("xmxzList", xmxzList);
	}

	/**
	 * ��ʦ�ϱ�_��ʼ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initLssb(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		PjszPjxmInterface pjxmService = myService.getPjszPjxmService(myForm);
		// ����·��
		String path = "pjpy_general_wdpj.do";
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
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		// �༶����
		String bjdm = request.getParameter("bjdm");

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm", "bjdm" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm, bjdm };

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);

		List<HashMap<String, String>> lssbxmList = pjxmService.getLssbXmList();

		request.setAttribute("xmList", lssbxmList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * ѧ�����_��ʼ������
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initXssqJgcx(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		WdpjXssqModel model = new WdpjXssqModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXssqInterface service = myService.getWdpjXssqService(myForm);
		WdpjJgcxInterface jgcxService = myService.getWdpjJgcxService(myForm);

		// ����·��
		String path = "pjpy_general_wdpj.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		// ѧ��
		// String xh = request.getParameter("xh");
		// model.setXh(xh);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �鿴��Ϣ
		String ckxx = request.getParameter("ckxx");
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "ckxx" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, ckxx };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================��ʼ��ҳ���������=====================
		String xh = user.getUserName();

		List<HashMap<String, String>> xssqByZqInfo = service.getXssqByZq(model,
				user);

		List<String[]> xszcInfo = service.getXszcInfo(model, user);

		List<HashMap<String, String>> xssqInfo = jgcxService.getLspjList(xh);

		ArrayList<Object> zcxxByZqInfo = (ArrayList<Object>) service
				.getZcxxByZq(model, user);

		request.setAttribute("xssqByZqInfo", xssqByZqInfo);
		request.setAttribute("xssqInfo", xssqInfo);
		request.setAttribute("zcxxByZqInfo", zcxxByZqInfo);
		request.setAttribute("xszcInfo", xszcInfo);
	}

	/**
	 * ������Ŀ���_��ʼ������
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initXmsh(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		WdpjXmshModel model = new WdpjXmshModel();
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);

		String otherValue = request.getParameter("otherValue");
		String[] otherValues = null;
		if (!Base.isNull(otherValue)) {

			otherValues = otherValue.split("!!@@!!");
		}
		String tableName = "xg_view_pjpy_pjshdcb";
		// ����·��
		String path = "pjpy_general_xmsh.do";
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
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��Ŀ����
		String xmdm = request.getParameter("xmdm");
		if (otherValues != null) {
			xmdm = otherValues[2];
		}
		model.setXmdm(xmdm);
		// ������λ
		String spgw = request.getParameter("spgw");
		if (otherValues != null) {
			spgw = otherValues[3];
		}
		model.setSpgw(spgw);
		// �����ֶ�
		// ture:����˼���Ϊ��һ�����
		boolean bool = !service.checkFirstSpgw(model, user);

		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm", "spgw", "first",
				"firstSpgw" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm, spgw, "first",
				String.valueOf(bool) };

		rForm.setTableName(tableName);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================��ʼ��ҳ���������=====================

		// ��ʼ����Ŀ�б�(�÷���������˿��غ�ʱ������ֶ�)
		List<HashMap<String, String>> cshXmList = service.getShxmList(model,
				user);
		request.setAttribute("cshXmList", cshXmList);

		// �߼���ѯ
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { pjxn });
		searchModel.setSearch_tj_xq(new String[] { pjxq });
		searchModel.setSearch_tj_shzt(new String[] { "δ���", "������" });

		myForm.setXmdm(xmdm);
		request.setAttribute("searchTj", searchModel);
	}

	/**
	 * ������Ŀ��ˡ���ϸ��_��ʼ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initXmshDetail(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		WdpjXmshModel model = new WdpjXmshModel();

		// ����·��
		String path = "pjpy_general_xmsh.do";
		// ѧУ����
//		String xxdm = (String) session.getAttribute("xxdm");
//		myForm.setXxdm(xxdm);
//		// ѧУƴ������
//		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
//		myForm.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// ������λ
		String spgw = request.getParameter("spgw");
		model.setSpgw(spgw);
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		String xh = request.getParameter("xh");
		model.setXh(new String[] { xh });
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		// ture:����˼���Ϊ��һ�����
		boolean bool = !service.checkFirstSpgw(model, user);

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm", "spgw", "first" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm, spgw,
				String.valueOf(bool) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================��ʼ��ҳ���������=====================
		// �������
		HashMap<String, Object> map = service.defaultWdpjXmsh(model, user);

		request.setAttribute("map", map);
	}

	/**
	 * ��������_��ʼ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initBcpj(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();

		// ����·��
		String path = "pjpy_general_bcpj.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);

		WdpjJgcxInterface service = myService.getWdpjJgcxService(myForm);

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * ������������ϸ��_��ʼ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initBcpjDetail(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXmshInterface xmshService = myService.getWdpjXmshService(myForm);
		WdpjJgcxInterface service = myService.getWdpjJgcxService(myForm);
		WdpjXmshModel model = new WdpjXmshModel();

		// ����·��
		String path = "pjpy_general_xmsh.do";
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
		// ������λ
		String spgw = request.getParameter("spgw");
		model.setSpgw(spgw);
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		String xh = request.getParameter("xh");
		model.setXh(new String[] { xh });
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");

		String pkValue = request.getParameter("pkValue");
		String[] pkArr = pkValue.split("!!@@!!");

		model.setXmdm(pkArr[0]);
		model.setXh(new String[] { pkArr[1] });
		// ture:����˼���Ϊ��һ�����
		boolean bool = !xmshService.checkFirstSpgw(model, user);

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm", "spgw", "first" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm, spgw,
				String.valueOf(bool) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================��ʼ��ҳ���������=====================
		// �������
		HashMap<String, Object> map = xmshService.defaultWdpjXmsh(model, user);

		request.setAttribute("map", map);
	}

	// ==================��ʷ���� begin==============================

	/**
	 * ��ʷ����_��ʼ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initLspj(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjJgcxInterface service = myService.getWdpjJgcxService(myForm);
		WdpjJgcxModel model = new WdpjJgcxModel();

		// ����·��
		String path = "pjpy_general_lspj.do";
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
		// ��ͼ
		String tableName = model.getSearch_table();
		// ��
		String realTable = model.getSave_table();
		// �û����
		String userStatus = user.getUserStatus();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ָ��
		String forward = request.getParameter("forward");
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "userStatus", "forward" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, userStatus, forward };

		if (Base.isNull(forward)) {
			path = "general_pjpy.do?method=wdpjLspj";
		}
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * ��ʷ������ά����_��ʼ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initLspjUpdate(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjJgcxInterface service = myService.getWdpjJgcxService(myForm);

		// ����·��
		String path = "pjpy_general_bcpj.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================��ʼ��ҳ���������=====================

		WdpjJgcxModel model = new WdpjJgcxModel();

		// �������
		HashMap<String, String> map = new HashMap<String, String>();
		if ("add".equalsIgnoreCase(doType)) {
			map.put("xq", "no");
			map.put("xmlx", "01");
		} else {
			// ����
			String pkValue = request.getParameter("pkValue");
			model.setPkValue(new String[] { pkValue });
			map = service.getLspjMap(model);
		}

		request.setAttribute("rs", map);
	}

	// ==================��ʷ���� end==============================

	/**
	 * ѧ��������ϸҳ��_��ʼ������
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initXssqDetail(RequestForm rForm, PjpyGeneralForm model,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ����·��
		String path = "pjpy_general_wdpj.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, model.getXxpymc());
		model.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		model.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		model.setPjxq(pjxq);
		// �������
		String pjnd = jbszModel.getPjnd();
		model.setPjnd(pjnd);
		// �۲�����
		String zczq = jbszModel.getZczq();
		model.setZczq(zczq);
		// ��Ա��
		String ryk = jbszModel.getRyk();
		model.setRyk(ryk);
		// ��������
		String pjzq = jbszModel.getPjzq();
		model.setPjzq(pjzq);
		// �۲�����
		String zcpm = jbszModel.getZcpm();
		model.setZcpm(zcpm);
		// ����ѧ������
		String pjxqmc = jbszModel.getPjxqmc();

		// ������
		String cpz = jbszModel.getCpz();
		model.setCpz(cpz);
		// ��������
		String opera = request.getParameter("opera");

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================��ʼ��ҳ���������=====================
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXssqInterface service = myService.getWdpjXssqService(model);

		// ��Ŀ�����б�
		HashMap<String, Object> wdpjXssqInfo = (HashMap<String, Object>) service
				.defaultWdpjXssq(model, user);
		request.setAttribute("opera", opera);
		request.setAttribute("wdpjXssqInfo", wdpjXssqInfo);
	}

	/**
	 * ѧ�����뱣����Ϣ_��ʼ������
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initSaveXssq(RequestForm rForm, PjpyGeneralForm model,
			BasicModel basicModel, User user, HttpServletRequest request)
			throws Exception {

		BasicService basicService = new BasicService();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String pjxn = jbszModel.getPjxn();

		String pjxq = jbszModel.getPjxq();

		String pjnd = jbszModel.getPjnd();

		String xh = user.getUserName();

		HttpSession session = request.getSession();
		// ��������
		String opera = request.getParameter("opera");

		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);

		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, model.getXxpymc());
		model.setXxpymc(xxpymc);

		HashMap<String, String> valueMap = new HashMap<String, String>();
		// �����ֶ�
		String[] save = null;

		String[] pkValue = null;
		if ("add".equalsIgnoreCase(opera)) {

			save = new String[] { "xmdm", "pjxn", "pjxq", "pjnd", "xh", "sqly",
					"sqsj", "sqjg" };

		} else if ("modi".equalsIgnoreCase(opera)) {

			save = new String[] { "sqly" };

			pkValue = new String[] { "xmdm", "pjxn", "pjxq", "pjnd", "xh" };

		}
		// --------------����------------
		basicModel.setTableName("xg_pjpy_pjxmsqb");
		// --------------��Ҫ�����ֵ--------------------

		valueMap.putAll(basicService.getValueMap(request, save));

		valueMap.put("xh", xh);

		valueMap.put("pjxn", pjxn);

		valueMap.put("pjxq", pjxq);

		valueMap.put("pjnd", pjnd);

		valueMap.put("sqsj", GetTime.getNowTime2());

		basicModel.setValueMap(valueMap);

		basicModel.setPk(pkValue);

	}

	/**
	 * ѧ�����뱣����Ϣ_��ʼ������
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initSaveLssb(RequestForm rForm, PjpyGeneralForm model,
			BasicModel basicModel, User user, HttpServletRequest request)
			throws Exception {

		BasicService basicService = new BasicService();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String pjxn = jbszModel.getPjxn();

		String pjxq = jbszModel.getPjxq();

		String pjnd = jbszModel.getPjnd();

		String xh = request.getParameter("xh");

		String userName = user.getUserName();

		String userType = user.getUserType();

		if ("stu".equalsIgnoreCase(userType)) {
			xh = userName;
		}

		HttpSession session = request.getSession();
		// ��������
		String opera = request.getParameter("opera");

		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);

		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, model.getXxpymc());
		model.setXxpymc(xxpymc);

		HashMap<String, String> valueMap = new HashMap<String, String>();
		// �����ֶ�
		String[] save = null;

		String[] pkValue = null;
		if ("add".equalsIgnoreCase(opera)) {

			save = new String[] { "xmdm", "pjxn", "pjxq", "pjnd", "xh", "sqly",
					"tjr", "sqsj" };

		} else if ("modi".equalsIgnoreCase(opera)) {

			save = new String[] { "sqly" };

			pkValue = new String[] { "xmdm", "pjxn", "pjxq", "pjnd", "xh" };

		}
		// --------------����------------
		basicModel.setTableName("xg_pjpy_pjxmsqb");
		// --------------��Ҫ�����ֵ--------------------

		valueMap.putAll(basicService.getValueMap(request, save));
		
		valueMap.putAll(basicService.getValueMap(request, "xg_pjpy_pjxmsqb"));

		valueMap.put("xh", xh);

		valueMap.put("pjxn", pjxn);

		valueMap.put("pjxq", pjxq);

		valueMap.put("pjnd", pjnd);

		valueMap.put("sqsj", GetTime.getNowTime2());

		valueMap.put("tjr", user.getUserName());

		basicModel.setValueMap(valueMap);

		basicModel.setPk(pkValue);

	}

	/**
	 * ѧ��������ϸҳ��_��ʼ������
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initXmshDetail(RequestForm rForm, WdpjXmshModel xmshModel,
			PjpyGeneralForm model, User user, HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = getXxmc(xxdm, model.getXxpymc());
		model.setXxpymc(xxpymc);

		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		model.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		model.setPjxq(pjxq);
		// �������
		String pjnd = jbszModel.getPjnd();
		model.setPjnd(pjnd);
		// �������
		String pjxqmc = jbszModel.getPjxqmc();
		model.setPjxqmc(pjxqmc);

		// �۲�����
		String zczq = jbszModel.getZczq();
		model.setZczq(zczq);
		// ��Ա��
		String ryk = jbszModel.getRyk();
		model.setRyk(ryk);
		// ��������
		String pjzq = jbszModel.getPjzq();
		model.setPjzq(pjzq);
		// �۲�����
		String zcpm = jbszModel.getZcpm();
		model.setZcpm(zcpm);
		// ������
		String cpz = jbszModel.getCpz();
		model.setCpz(cpz);
		// ��������
		String opera = request.getParameter("opera");

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		// ====================��ʼ��ҳ���������=====================
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXmshInterface service = myService.getWdpjXmshService(model);

		// ��Ŀ�����б�
		HashMap<String, Object> wdpjXssqInfo = (HashMap<String, Object>) service
				.defaultWdpjXmsh(xmshModel, user);
		request.setAttribute("opera", opera);
		request.setAttribute("wdpjXssqInfo", wdpjXssqInfo);
	}

	//
	// /**
	// * ������Ŀ���_��ʼ������
	// *
	// * @param request
	// * @author ΰ�����
	// * @throws Exception
	// *
	// */
	// public void initXmsb(RequestForm rForm, WdpjLssbModel model,
	// HttpServletRequest request) throws Exception {
	//
	// HttpSession session = request.getSession();
	//
	// PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
	//
	// // ����ģ��
	// String gnmk = "pjpy";
	// // ϵͳ�ֶ�����
	// String menu = "pjxmsb";
	// // �������ͣ����ӣ��޸ģ�ɾ���ȣ�
	// String doType = request.getParameter("doType");
	// // ����·��
	// String path = "pjpy_pjlc_xmsb.do";
	//
	// // ѧУ����
	// String xxdm = (String) session.getAttribute("xxdm");
	// jbszModel.setXxdm(xxdm);
	// // ѧУƴ������
	// String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
	// jbszModel.setXxpymc(xxpymc);
	//
	// // ����ѧ��
	// String pjxn = jbszModel.getPjxn();
	// // ����ѧ��
	// String pjxq = jbszModel.getPjxq();
	// // �������
	// String pjnd = jbszModel.getPjnd();
	// // ����ѧ������
	// String pjxqmc = jbszModel.getPjxqmc();
	// // ��Ŀ����
	// String xmdm = request.getParameter("xmdm");
	// xmdm = Base.isNull(xmdm) ? model.getXmdm() : xmdm;
	// // // ��Ŀ����
	// String bjdm = model.getBjdm();
	// if (Base.isNull(model.getBjdm())) {
	// bjdm = request.getParameter("bjdm");
	// }
	// // model.setBjdm(bjdm);
	// // ��Ŀ����model��ʼ��
	// String pk = pjxn + pjxq + pjnd + xmdm;
	//
	// PjpyGeneralService myService = new PjpyGeneralService();
	// WdpjLssbInterface service = myService.getWdpjLssbService(jbszModel);
	//
	// // ��ͷ
	// List<HashMap<String, String>> topTr = getDefaultValue(model);
	//
	// model.setPkValue(pk);
	// service.getXmszForXmdm(model);
	//
	// // ��������
	// String szrs = service.getXmszrs(model);
	// // ��Ŀ����
	// String xmmc = model.getXmmc();
	// // ���Ʒ�Χ
	// String kzfw = model.getKzfw();
	//
	// // �����ֶ�
	// String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc",
	// "xmdm", "bjdm", "szrs", "xmmc", "kzfw" };
	// // �����ֶ�ֵ
	// String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, xmdm, bjdm,
	// szrs, xmmc, kzfw };
	//
	// rForm.setQtzd(qtzd);
	// rForm.setQtzdz(qtzdz);
	//
	// rForm.setDoType(doType);
	// rForm.setGnmk(gnmk);
	// rForm.setMenu(menu);
	// rForm.setPath(path);
	// rForm.setTopTr(topTr);
	//
	// }

	private List<HashMap<String, String>> getDefaultValue(WdpjLssbModel model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("en", "xh");
		map.put("cn", "ѧ��");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xm");
		map.put("cn", "����");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xb");
		map.put("cn", "�Ա�");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "bjmc");
		map.put("cn", "�༶����");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "zhf");
		map.put("cn", "�۲��");
		topTr.add(map);

		// �۲�����
		String zcpm = "3";

		if ("3".equalsIgnoreCase(zcpm)) {
			map = new HashMap<String, String>();
			map.put("en", "zcfbjpm");
			map.put("cn", "�༶����");
			topTr.add(map);
		} else if ("2".equalsIgnoreCase(zcpm)) {
			map = new HashMap<String, String>();
			map.put("en", "zcfnjzypm");
			map.put("cn", "�꼶רҵ����");
			topTr.add(map);
		}

		map = new HashMap<String, String>();
		map.put("en", "cz");
		map.put("cn", "����");
		topTr.add(map);

		return topTr;
	}

}
