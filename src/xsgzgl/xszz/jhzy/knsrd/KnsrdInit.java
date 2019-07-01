package xsgzgl.xszz.jhzy.knsrd;

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
 * Description: ѧ������_�������϶�_��ְҵ_Init��
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

public class KnsrdInit {

	/**
	 * �������϶����롾��ѯ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initSqSearch(RequestForm rForm, KnsrdForm myForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_knsrd_sq.do";
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
	 * �������϶����롾���ӡ�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initSqInsert(RequestForm rForm, KnsrdForm myForm, User user,
			HttpServletRequest request) {

		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();
		KnsrdService service = new KnsrdService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		String tableName = "view_xsjbxx";
		// ����·��
		String path = "xszz_jhzy_knsrd_sq.do";
		// ѧ��
		String xh = request.getParameter("xh");
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			xh = user.getUserName();
		}

		// ����ѧ��
		String xn = csszModel.getXn();
		// �Ƿ��ͥ�������
		boolean isJtqkdc = csszModel.getIsJtqkdc(xh, xn);
		// ����������״̬
		String knssqzt = csszModel.getKnssqzt();

		HashMap<String, String> rs = new HashMap<String, String>();
		if (!Base.isNull(xh)) {
			rs = service.getKnsrdInfo(xh, xn);
		}

		// �����ֶ�
		String[] qtzd = new String[] { "xh", "isJtqkdc", "knssqzt" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xh, String.valueOf(isJtqkdc), knssqzt };

		rForm.setTableName(tableName);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);

	}

	/**
	 * �������϶����롾�޸ġ�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initSqUpdate(RequestForm rForm, KnsrdForm myForm, User user,
			HttpServletRequest request) {

		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();
		KnsrdService service = new KnsrdService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_knsrd_sq.do";
		// ����
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!Base.isNull(pkValue)) {
			String xn = pkValue.split("luojw")[0];
			String xh = pkValue.split("luojw")[1];
			rs = service.getKnsrdInfo(xh, xn);
		}

		// ����������״̬
		String knssqzt = csszModel.getKnssqzt();

		// �����ֶ�
		String[] qtzd = new String[] { "knssqzt" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { knssqzt };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);
	}

	/**
	 * �������϶���ˡ���ѯ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initShSearch(RequestForm rForm, KnsrdForm myForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = request.getParameter("userType");
		// ����·��
		String path = "xszz_jhzy_knsrd_sh.do";
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
	 * �������϶���ˡ���ѯ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initjgSearch(RequestForm rForm, KnsrdForm myForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = request.getParameter("userType");
		// ����·��
		String path = "xszz_jhzy_knsrd_jg.do";
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
	 * �������϶���ˡ��޸ġ�
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initShUpdate(RequestForm rForm, KnsrdForm myForm, User user,
			HttpServletRequest request) {

		KnsrdService service = new KnsrdService();

		String pkValue = request.getParameter("pkValue");
		String xn = pkValue.split("luojw")[0];
		String xh = pkValue.split("luojw")[1];

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_knsrd_sh.do";
		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HashMap<String, String> map = service.getKnsrdInfo(xh, xn);
		request.setAttribute("map", map);

		String sqlb = map.get("sqlb");
		if (!Base.isNull(sqlb) && sqlb.split("luojw").length > 0) {
			List<HashMap<String, String>> knslbList = service.getKnslbList(sqlb
					.split("luojw"));
			request.setAttribute("knslbList", knslbList);
		}

		String tjdc = "һ������";
		String beforeTjdc = "";
		
		if ("bzr".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("bzrtjdc")) ? tjdc : map.get("bzrtjdc");
			beforeTjdc = tjdc;
			request.setAttribute("shyj", map.get("bzrshyj"));
		} else if ("fdy".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("fdytjdc")) ? map.get("bzrtjdc") : map
					.get("fdytjdc");
			beforeTjdc = map.get("bzrtjdc");
			request.setAttribute("shyj", map.get("fdyshyj"));
		}
		if ("xy".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("xytjdc")) ? map.get("fdytjdc") : map
					.get("xytjdc");
			beforeTjdc = map.get("fdytjdc");
			request.setAttribute("shyj", map.get("xyshyj"));
		}
		if ("xx".equalsIgnoreCase(user.getUserStatus())) {
			tjdc = Base.isNull(map.get("xxtjdc")) ? map.get("xytjdc") : map
					.get("xxtjdc");
			beforeTjdc = map.get("xytjdc");
			request.setAttribute("shyj", map.get("xxshyj"));
		}

		request.setAttribute("beforeTjdc", beforeTjdc);
		request.setAttribute("tjdj", tjdc);
		request.setAttribute("map", map);
	}

	/**
	 * �������϶��������ѯ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initJgSearch(RequestForm rForm, KnsrdForm myForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_knsrd_jg.do";
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
	 * �������϶��������ϸ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initJgDetail(RequestForm rForm, KnsrdForm myForm, User user,
			HttpServletRequest request) {

		KnsrdService service = new KnsrdService();

		String pkValue = request.getParameter("pkValue");
		String xn = pkValue.split("luojw")[0];
		String xh = pkValue.split("luojw")[1];

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_knsrd_jg.do";
		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HashMap<String, String> map = service.getKnsrdInfo(xh, xn);
		request.setAttribute("map", map);

		String sqlb = map.get("sqlb");
		if (!Base.isNull(sqlb) && sqlb.split("luojw").length > 0) {
			List<HashMap<String, String>> knslbList = service.getKnslbList(sqlb
					.split("luojw"));
			request.setAttribute("knslbList", knslbList);
		}

		request.setAttribute("map", map);
	}
}
