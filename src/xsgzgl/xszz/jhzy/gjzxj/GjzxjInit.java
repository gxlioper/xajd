package xsgzgl.xszz.jhzy.gjzxj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ѧ������_������ѧ��_��ְҵ_Init��
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

public class GjzxjInit {

	/**
	 * ������ѧ�����롾��ѯ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initSqSearch(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_gjzxj_sq.do";
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
	 * ������ѧ�����롾���ӡ�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initSqInsert(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();
		GjzxjService service = new GjzxjService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		String tableName = "view_xsjbxx";
		// ����·��
		String path = "xszz_jhzy_gjzxj_sq.do";
		// ѧ��
		String xh = request.getParameter("xh");
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			xh = user.getUserName();
		}
		// ����ѧ��
		String xn = csszModel.getXn();
		// �Ƿ�������
		boolean isKns = csszModel.getIsKns(xh, xn);
		// ������ѧ������״̬
		String gjzxjsqzt = csszModel.getGjzxjsqzt();

		HashMap<String, String> rs = new HashMap<String, String>();
		if (!Base.isNull(xh)) {
			rs = service.getGjzxjInfo(xh, xn);
		}

		// �����ֶ�
		String[] qtzd = new String[] { "xh", "isKns", "gjzxjsqzt" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xh, String.valueOf(isKns), gjzxjsqzt };

		rForm.setTableName(tableName);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);
	}

	/**
	 * ������ѧ�����롾�޸ġ�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initSqUpdate(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();
		GjzxjService service = new GjzxjService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_gjzxj_sq.do";
		// ������ѧ������״̬
		String gjzxjsqzt = csszModel.getGjzxjsqzt();
		// �����ֶ�
		String[] qtzd = new String[] { "gjzxjsqzt" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { gjzxjsqzt };
		// ����
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!Base.isNull(pkValue)) {
			String xn = pkValue.split("luojw")[0];
			String xh = pkValue.split("luojw")[1];
			rs = service.getGjzxjInfo(xh, xn);
		}

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);
	}

	/**
	 * ������ѧ����ˡ���ѯ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initShSearch(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = request.getParameter("userType");
		// ����·��
		String path = "xszz_jhzy_gjzxj_sh.do";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HttpSession session = request.getSession();

		if ("fdy".equalsIgnoreCase(userType)) {
			session.setAttribute("fdyQx", "true");
			session.setAttribute("bzrQx", "false");
		} else if ("bzr".equalsIgnoreCase(userType)) {
			session.setAttribute("fdyQx", "false");
			session.setAttribute("bzrQx", "true");
		}
	}

	/**
	 * ������ѧ����ˡ��޸ġ�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initShUpdate(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		GjzxjService service = new GjzxjService();

		String pkValue = request.getParameter("pkValue");
		String xn = pkValue.split("luojw")[0];
		String xh = pkValue.split("luojw")[1];

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_gjzxj_sh.do";
		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HashMap<String, String> map = service.getGjzxjInfo(xh, xn);
		request.setAttribute("map", map);

		String tjdc = "һ��";

		if ("bzr".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("bzrtjdc")) ? tjdc : map.get("bzrtjdc");
			request.setAttribute("shyj", map.get("bzrshyj"));
		} else if ("fdy".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("fdytjdc")) ? map.get("bzrtjdc") : map
					.get("fdytjdc");
			request.setAttribute("shyj", map.get("fdyshyj"));
		}
		if ("xy".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("xytjdc")) ? map.get("fdytjdc") : map
					.get("xytjdc");
			request.setAttribute("shyj", map.get("xyshyj"));
		}
		if ("xx".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("xxtjdc")) ? map.get("xytjdc") : map
					.get("xxtjdc");
			request.setAttribute("shyj", map.get("xxshyj"));
		}

		request.setAttribute("tjdj", tjdc);
		request.setAttribute("map", map);
	}

	/**
	 * ������ѧ��������ѯ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initJgSearch(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_gjzxj_jg.do";
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
	 * ������ѧ��������ϸ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initJgDetail(RequestForm rForm, GjzxjForm myForm, User user,
			HttpServletRequest request) {

		GjzxjService service = new GjzxjService();

		String pkValue = request.getParameter("pkValue");
		String xn = pkValue.split("luojw")[0];
		String xh = pkValue.split("luojw")[1];

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_gjzxj_jg.do";
		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HashMap<String, String> map = service.getGjzxjInfo(xh, xn);
		request.setAttribute("map", map);
	}
}
