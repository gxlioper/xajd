package xsgzgl.xsxx.grxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjForm;
import xsgzgl.xsxx.cssz.XsxxCsszForm;
import xsgzgl.xsxx.model.CsszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_������Ϣ_Init��
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

public class XsxxGrxxInit {

	/**
	 * ������Ϣ_�޸�����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initGrxxSq(RequestForm rForm, XsxxGrxxForm model, User user,
			HttpServletRequest request) {

		XsxxGrxxService service = new XsxxGrxxService();
		CsszModel csszModel = model.getCsszModel();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = user.getUserType();
		// ����·��
		String path = "xsxx_grxx_sq.do";
		// �Ƿ����
		String sfsh = csszModel.getSfsh();
		// ����ID
		String lcid = csszModel.getLcid();
		// ѧ��
		String xh = "";
		if ("stu".equalsIgnoreCase(userType)) {
			xh = user.getUserName();
		} else {
			xh = request.getParameter("xh");
		}
		model.setXh(xh);
		// ����ID
		String sqid = service.getSqid(model, user);
		model.setSqid(sqid);
		
		// ������
		String sqjg = service.getOneValue("xg_xsxx_grxx_xgsqb", "sqjg", "id", sqid);
		model.setSqjg(sqjg);
		
		// �����ֶ�
		String[] qtzd = new String[] { "sfsh", "lcid", "sqid" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { sfsh, lcid, sqid };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);

		if (!Base.isNull(xh)) {
			try {
				XsxxGrxxService.initZdxgb(xh);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ������Ϣ_�޸����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initGrxxSh(RequestForm rForm, XsxxGrxxForm model, User user,
			HttpServletRequest request) {

		CsszModel csszModel = model.getCsszModel();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = user.getUserType();
		// ����·��
		String path = "xsxx_grxx_sh.do";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ѧ��
		String xh = "";
		if ("stu".equalsIgnoreCase(userType)) {
			xh = user.getUserName();
		} else {
			xh = request.getParameter("xh");
		}
		model.setXh(xh);
		
		// �ж��û���λ
		int gwnum = 1;

		List<HashMap<String, String>> yhgwList = XtwhShlcService.getSpgwList(
				csszModel.getLcid(), user.getUserName());

		request.setAttribute("yhgwList", yhgwList);

		if (yhgwList != null && yhgwList.size() > 1) {
			gwnum = yhgwList.size();
		}
		

		// �����ֶ�
		String[] qtzd = new String[] { "gwnum" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(gwnum) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	
	/**
	 * ������Ϣ_�޸����_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initGrxxJg(RequestForm rForm, XsxxGrxxForm model, User user,
			HttpServletRequest request) {

		XsxxGrxxService service = new XsxxGrxxService();
		
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = user.getUserType();
		// ����·��
		String path = "xsxx_grxx_jg.do";
		// ��ͷ
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// ѧ��
		String xh = "";
		// ����
		String xm = "";
		if ("stu".equalsIgnoreCase(userType)) {
			xh = user.getUserName();
			xm = service.getOneValue("view_xsjbxx", "xm", "xh", xh);
		} else {
			xh = request.getParameter("xh");
		}
		model.setXh(xh);

		// �����ֶ�
		String[] qtzd = new String[] { "xh", "xm" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xh, xm };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
	}
	
	/**
	 * ������Ϣ_�޸����루��ϸ��_��ʼ������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void initGrxxShDetail(RequestForm rForm, XsxxGrxxForm model,
			User user, HttpServletRequest request) {

		XsxxGrxxService service = new XsxxGrxxService();
		CsszModel csszModel = model.getCsszModel();

		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// �û�����
		String userType = user.getUserType();
		// ����·��
		String path = "xsxx_grxx_jg.do";
		// ����ID
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		// ��˸�λ
		String shgw = request.getParameter("shgw");
		model.setShgw(shgw);
		// ѧ��
		String xh = service.getOneValue("xg_xsxx_grxx_xgsqb", "xh", "id", sqid);
		model.setXh(xh);

		// �����ֶ�
		String[] qtzd = new String[] { "sqid", "shgw", "xh" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { sqid, shgw, xh };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
	}
	
	private List<HashMap<String, String>> getDefaultValue(XsxxGrxxForm model,
			String path) {

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		if ("xsxx_grxx_sh.do".equalsIgnoreCase(path)) {// ������Ϣ�޸����

			String[] en = new String[] { "xh", "xm", "bjmc", "sqsj", "shzt" };
			String[] cn = new String[] { "ѧ��", "����", "�༶����", "����ʱ��", "���״̬" };

			topTr = dao.arrayToList(en, cn);
		} else if ("xsxx_grxx_jg.do".equalsIgnoreCase(path)) {// ������Ϣ�޸Ľ��

			String[] en = new String[] { "xh", "xm", "bjmc", "sqsj", "sqjg" };
			String[] cn = new String[] { "ѧ��", "����", "�༶����", "����ʱ��", "������" };

			topTr = dao.arrayToList(en, cn);
		}

		return topTr;
	}
}
