package xsgzgl.xszz.jhzy.gjlzjxj;

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
 * Description: ѧ������_������־��ѧ��_��ְҵ_Init��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */

public class GjlzjxjInit {

	/**
	 * ������־��ѧ�����롾��ѯ��
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initSqSearch(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_gjlzjxjSq.do";
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
	 * ������־��ѧ�����롾���ӡ�
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initSqInsert(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		GjlzjxjService service = new GjlzjxjService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		String tableName = "view_xsjbxx";
		// ����·��
		String path = "xszz_jhzy_gjlzjxjSq.do";
		// ѧ��
		String xh = request.getParameter("xh");
		if ("stu".equalsIgnoreCase(user.getUserType())) {
			xh = user.getUserName();
		}

		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("flag", "true");
		if (!Base.isNull(xh)) {
			XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();
			// ѧ��
			String xn = csszModel.getXn();
			rs = service.getGjlzjxjInfo(xh, xn);
		} 

		// �����ֶ�
		String[] qtzd = new String[] { "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xh };

		rForm.setTableName(tableName);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);
	}

	/**
	 * ������־��ѧ�����롾�޸ġ�
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initSqUpdate(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		GjlzjxjService service = new GjlzjxjService();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_knsrd_sq.do";
		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};
		// ����
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!Base.isNull(pkValue)) {
			String xn = pkValue.split("!!luojw!!")[0];
			String xh = pkValue.split("!!luojw!!")[1];
			rs = service.getGjlzjxjInfo(xh, xn);
		}

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);
	}

	/**
	 * ������־��ѧ����ˡ���ѯ��
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initShSearch(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = request.getParameter("userType");
		// ����·��
		String path = "xszz_jhzy_gjlzjxjSh.do";
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
	 * ���ҽ�ѧ���϶��������ѯ��
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initjgSearch(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = request.getParameter("userType");
		// ����·��
		String path = "xszz_jhzy_gjlzjxjCx.do";
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
	 * ������־��ѧ����ˡ��޸ġ�
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initShUpdate(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		GjlzjxjService service = new GjlzjxjService();

		String pkValue = request.getParameter("pkValue");
		String xn = pkValue.split("!!luojw!!")[0];
		String xh = pkValue.split("!!luojw!!")[1];

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_Gjlzjxj_sh.do";
		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HashMap<String, String> map = service.getGjlzjxjInfo(xh, xn);
		request.setAttribute("map", map);


		if ("bzr".equalsIgnoreCase(user.getUserStatus())) {
			request.setAttribute("shyj", map.get("bzrshyj"));
		} else if ("fdy".equalsIgnoreCase(user.getUserStatus())) {
			request.setAttribute("shyj", map.get("fdyshyj"));
		}
		if ("xy".equalsIgnoreCase(user.getUserStatus())) {
			request.setAttribute("shyj", map.get("xyshyj"));
		}
		if ("xx".equalsIgnoreCase(user.getUserStatus())) {
			request.setAttribute("shyj", map.get("xxshyj"));
		}
		request.setAttribute("xh", xh);
		request.setAttribute("xn", xn);
		request.setAttribute("map", map);
	}

	/**
	 * ������־��ѧ��������ѯ��
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initJgSearch(RequestForm rForm, GjlzjxjForm myForm, User user,
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
	 *������־��ѧ��������ϸ��
	 * 
	 * @param request
	 * @author lt
	 * 
	 */
	public void initJgDetail(RequestForm rForm, GjlzjxjForm myForm, User user,
			HttpServletRequest request) {

		GjlzjxjService service = new GjlzjxjService();

		String pkValue = request.getParameter("pkValue");
		String xn = pkValue.split("luojw")[0];
		String xh = pkValue.split("luojw")[1];

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "xszz_jhzy_Gjlzjxj_jg.do";
		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		HashMap<String, String> map = service.getGjlzjxjInfo(xh, xn);
		request.setAttribute("map", map);

	
		request.setAttribute("xn", xn);
		request.setAttribute("xh", xh);
		request.setAttribute("map", map);
	}
}
