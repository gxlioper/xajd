package xsgzgl.pjpy.general.zhcp;

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
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ۺϲ���_ͨ��_Init��
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

public class PjpyZhcpInit extends BasicInit {

	/**
	 * �۲����ά��_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initZhcpMaintain(RequestForm rForm, PjpyGeneralForm model,
			User user, HttpServletRequest request) {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// ����·��
		String path = "pjpy_general_zhcp_maintain.do";
		// ѧУ����
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);
		// ѧУƴ������
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);
		// ����ѧ��
		String pjxn = jbszModel.getPjxn();
		model.setPjxn(pjxn);
		// ����ѧ��
		String pjxq = jbszModel.getPjxq();
		model.setPjxq(pjxq);
		// �۲�����
		String zczq = jbszModel.getZczq();
		model.setZczq(zczq);
		// ��Ա��
		String ryk = jbszModel.getRyk();
		model.setRyk(ryk);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û���
		String userName = user.getUserName();
		// �û���
		String userType = user.getUserType();
		// �û�����
		String yhlx = model.getYhlx();
		if (Fdypd.isFdy(userName)) {// �Ƿ񸨵�Ա
			yhlx = "fdy";
		} else if (Fdypd.isBzr(userName, "")) {// �Ƿ������
			yhlx = "bzr";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ
			yhlx = "admin";
		} else {// ����
			yhlx = user.getUserType();
		}
		model.setYhlx(yhlx);

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
	 * �ۺϲ���_��ʼ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public void initZhcp(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);

		// ����·��
		String path = "pjpy_general_zhcp_maintain.do";
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
		// ҳ����ת
		String forward = request.getParameter("forward");
		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "forward" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, forward };

		// ====================��ʼ��ҳ���������=====================

		// ��ʼ����Ŀ�б�
		List<HashMap<String, String>> cshXmList = service.getCshXmList(user);

		if (cshXmList != null && cshXmList.size() == 1) {

			HashMap<String, String> cshXmMap = cshXmList.get(0);

			qtzd = new String[] { "pjxn", "pjxq", "forward", "xmdm", "xmmc",
					"lyb", "xmjb" };

			qtzdz = new String[] { pjxn, pjxq, forward, cshXmMap.get("xmdm"),
					cshXmMap.get("xmmc"), cshXmMap.get("lyb"),
					cshXmMap.get("xmjb") };
			cshXmList = new ArrayList<HashMap<String, String>>();
		}

		request.setAttribute("cshXmList", cshXmList);

		// �߼���ѯ
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { pjxn });
		searchModel.setSearch_tj_xq(new String[] { pjxq });

		request.setAttribute("searchTj", searchModel);

		// ==================�ܷ������� begin==================
		String message = "";
		String operation = myService.getOperation("112");
		if ("later".equalsIgnoreCase(operation)) {
			message = "����������Ѿ�������û��ύ���������ٽ�����ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		} else if ("early".equalsIgnoreCase(operation)) {
			message = "�����������ǰһ����δ������û��ύ�������ܶ��������ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		}
		// =================�ܷ������� end ===================

		// ====================��ʼ��ҳ��������� end=====================

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
	}

	/**
	 * �۲�������_��ʼ������
	 * 
	 * @param request
	 * @author qlj
	 * @throws Exception 
	 * 
	 */
	public void initZhcpResult(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		// ����·��
		String path = "pjpy_general_zhcp_result.do";
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
		// �������
		String pjnd = jbszModel.getPjnd();
		myForm.setPjnd(pjnd);
		String zczq = pjxn + "luojw" + pjxq + "luojw" + pjnd;
		// ��������
		String pjzq = jbszModel.getPjzq();
		// ҳ����ת
		String forward = request.getParameter("forward");
		// ��Ա��
		String ryk = jbszModel.getRyk();
		myForm.setRyk(ryk);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û���
		String userName = user.getUserName();
		// �û���
		String userType = user.getUserType();
		// �û�����
		String yhlx = myForm.getYhlx();
		if (Fdypd.isFdy(userName)) {// �Ƿ񸨵�Ա
			yhlx = "fdy";
		} else if (Fdypd.isBzr(userName, "")) {// �Ƿ������
			yhlx = "bzr";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ
			yhlx = "admin";
		} else {// ����
			yhlx = user.getUserType();
		}
		myForm.setYhlx(yhlx);

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "zczq",
				"forward" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, zczq, forward };
		
		// ==================�ܷ������� begin==================
		String message = "";
		String operation = myService.getOperation("116");// �ۺϷֽ��
		if ("later".equalsIgnoreCase(operation)) {
			message = "����������Ѿ�������û��ύ���������ٽ�����ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		}else if("early".equalsIgnoreCase(operation)){
			message = "�����������ǰһ����δ������û��ύ�������ܶ��������ز������������飬����ϵ����Ա��";
			request.setAttribute("operation", "no");
		}
		// =================�ܷ������� end ===================

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
	}

	/**
	 * �۲�������_��ʼ������
	 * 
	 * @param request
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initKindChoose(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		// ����·��
		String path = "pjpy_general_zhcp_result.do";
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
		// �۲�����
		String zczq = jbszModel.getZczq();
		myForm.setZczq(zczq);
		// ��Ա��
		String ryk = jbszModel.getRyk();
		myForm.setRyk(ryk);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û���
		String userName = user.getUserName();
		// �û���
		String userType = user.getUserType();
		// �û�����
		String yhlx = myForm.getYhlx();
		if (Fdypd.isFdy(userName)) {// �Ƿ񸨵�Ա
			yhlx = "fdy";
		} else if (Fdypd.isBzr(userName, "")) {// �Ƿ������
			yhlx = "bzr";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ
			yhlx = "admin";
		} else {// ����
			yhlx = user.getUserType();
		}
		myForm.setYhlx(yhlx);

		// �����ֶ�
		String[] qtzd = new String[] { "pjxn", "pjxq" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pjxn, pjxq };

		// ====================��ʼ��ҳ���������=====================
		request.setAttribute("checkKind", service.getCheckKind(myForm, user));

		request.setAttribute("topTr", service.getKindChoose(myForm, user));

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

}
