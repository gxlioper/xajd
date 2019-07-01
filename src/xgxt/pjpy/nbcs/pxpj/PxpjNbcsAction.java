package xgxt.pjpy.nbcs.pxpj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.SearchUtils;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������ѧ��Ʒ������-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class PxpjNbcsAction extends BasicAction {

	/**
	 * ѧ��Ʒ������_�����_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward djrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �Ƿ񸨵�Ա
		String isFdy = session.getAttribute("isFdy").toString();
		// �Ƿ������
		String isBzr = session.getAttribute("isBzr").toString();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_pxpj_djr";
		// ����
		String realTable = "pxpj_djrb";
		// ����·��
		String path = "pjpy_pxpj_djrManage.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// �Ƿ�ѧԺ
		boolean isxy = false;
		// =================end==================

		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType) && !(Boolean.parseBoolean(isFdy))
				&& !(Boolean.parseBoolean(isBzr))) {
			// ѧԺ�û�
			myForm.setQueryequals_xydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ==================ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================

		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nj",
					"xymc", "zymc", "bjmc" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "isxy" };
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("djrManage");
	}

	/**
	 * ѧ��Ʒ������_�����_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward djrUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �Ƿ񸨵�Ա
		String isFdy = session.getAttribute("isFdy").toString();
		// �Ƿ������
		String isBzr = session.getAttribute("isBzr").toString();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "pjpy_pxpj_djrManage.do";
		// ����
		String realTable = "pxpj_djrb";
		// ��ǰѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ�ѧԺ
		boolean isxy = false;
		// =================end==================

		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType) && !(Boolean.parseBoolean(isFdy))
				&& !(Boolean.parseBoolean(isBzr))) {
			// ѧԺ�û�
			myForm.setXydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveDjr(myForm, realTable);
			message = resault ? "����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "message", "xn", "isxy" };
		String[] qtzdz = new String[] { message, xn, String.valueOf(isxy) };

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("djrUpdate");
	}
	
	/**
	 * ѧ��Ʒ������_�����_��������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward djrBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "pjpy_pxpj_djrManage.do";
		// ����
		String realTable = "pxpj_djrblb";
		// ��ǰѧ��
		String xn = Base.currXn;
		// ��ʾ��Ϣ
		String message = "";
		// ������Ϣ
		String[] colList = new String[] { "bl", "bz" };
		HashMap<String, String> rs = service.getPjpyInfo(realTable, "xn", xn,
				colList);
		rs.put("xn", xn);
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveDjrbl(myForm, realTable, request);
			message = resault ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "message", "xn" };
		String[] qtzdz = new String[] { message, xn };
		
		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("djrBlsz");
	}
	
	/**
	 * ѧ��Ʒ������_�ʾ����_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �Ƿ񸨵�Ա
		String isFdy = session.getAttribute("isFdy").toString();
		// �Ƿ������
		String isBzr = session.getAttribute("isBzr").toString();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_pxpj_fpjg";
		// ����
		String realTable = "pxpj_wjfpb";
		// ����·��
		String path = "pjpy_pxpj_wjfp.do";
		//��ǰѧ��
		String xn = Base.currXn;
		myForm.setQueryequals_xn(xn);
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// �Ƿ�ѧԺ
		boolean isxy = false;
		// =================end==================

		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType) && !(Boolean.parseBoolean(isFdy))
				&& !(Boolean.parseBoolean(isBzr))) {
			// ѧԺ�û�
			myForm.setQueryequals_xydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ==================ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================
		
		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, "", tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nj",
					"xymc", "zymc", "bjmc", "wjmc", "fpzt" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "isxy" };
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("wjfpManage");
	}

	/**
	 * ѧ��Ʒ������_�ʾ����_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wjfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �Ƿ񸨵�Ա
		String isFdy = session.getAttribute("isFdy").toString();
		// �Ƿ������
		String isBzr = session.getAttribute("isBzr").toString();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "pjpy_pxpj_djrManage.do";
		// ��ǰѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ�ѧԺ
		boolean isxy = false;
		// =================end==================

		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType) && !(Boolean.parseBoolean(isFdy))
				&& !(Boolean.parseBoolean(isBzr))) {
			// ѧԺ�û�
			myForm.setXydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveWjfp(myForm);
			message = resault ? "����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "message", "xn", "isxy" };
		String[] qtzdz = new String[] { message, xn, String.valueOf(isxy) };

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjfpUpdate");
	}
	
	/**
	 * ѧ��Ʒ������_Ʒ������_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pxpjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxglService stuService = new XsxxglService();
		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_pxpj_wjfp";
		// ����
		String realTable = "pxpj_wjfpb";
		// ����·��
		String path = "pjpy_pxpj_pxpj.do";
		// ��ǰѧ��
		String xn = Base.currXn;
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ������Ϣ��ʾ
		String msg = "";
		// �Ƿ�����
		boolean isDjr = false;
		// =================end==================

		// ================= Ȩ�޿��� ==================
		if (!"stu".equalsIgnoreCase(userType)) {
			msg = "�ʾ�ֻ����ѧ�����лش�";
		}else {
			// ѧ��������Ϣ
			HashMap<String, String> stuInfo = stuService
					.selectStuinfo(userName);
			stuInfo.put("xn", xn);
			request.setAttribute("stuInfo", stuInfo);
			
			myForm.setXh(userName);
			myForm.setXn(xn);
			isDjr = service.isDjr(myForm);
		}
		// =================end==================

		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, "", tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nj",
					"xymc", "zymc", "bjmc", "wjmc" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "msg", "isDjr" };
		String[] qtzdz = new String[] { msg, String.valueOf(isDjr) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("pxpjManage");
	}
	
	/**
	 * ѧ��Ʒ������_Ʒ������_�ش�
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward pxpjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		myForm.setLx(userType);
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		myForm.setXhzgh(userName);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = "view_wjdc_wjxx";
		// ����
		String realTable = "pxpj_hdb";
		// ����·��
		String path = "pjpy_pxpj_pxpj.do";
		// ����ֵ
		String pkValue = request.getParameter("pk");
		
		// �ʾ����뱻������
		HashMap<String, String> map = service.getPjpyInfo("pxpj_wjfpb",
				"fpxh||id", pkValue, new String[] { "id", "fpxh" });

		String id = map.get("id");
		id = Base.isNull(id) ? request.getParameter("id") : id;
		
		String bpjr = map.get("fpxh");
		bpjr = Base.isNull(bpjr) ? request.getParameter("bpjr") : bpjr;
		
		myForm.setBpjr(bpjr);
		myForm.setWjbh(id);
		myForm.setId(id);
		
		// �ʾ���ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================ִ�в鿴���� ======================

		String pk = "id";

		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc" };

		rs = service.getPjpyInfo(tableName, pk, id, colList);

		// �������
		service.setWjZjInfo(myForm, request);

		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "id", "bpjr" };

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { id, bpjr };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			
			// ��������
			boolean result = service.savePxpj(myForm, rForm);
			request.setAttribute("result", result);
		}
		// =================end ===================
		
		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("pxpjUpdate");
	}

	/**
	 * ѧ��Ʒ������_���۽��_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �Ƿ񸨵�Ա
		String isFdy = session.getAttribute("isFdy").toString();
		// �Ƿ������
		String isBzr = session.getAttribute("isBzr").toString();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_pxpj_fpjg";
		// ����
		String realTable = "pxpj_wjfpb";
		// ����·��
		String path = "pjpy_pxpj_pjjg.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// �Ƿ�ѧԺ
		boolean isxy = false;
		// =================end==================

		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType) && !(Boolean.parseBoolean(isFdy))
				&& !(Boolean.parseBoolean(isBzr))) {
			// ѧԺ�û�
			myForm.setQueryequals_xydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, "", tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputValue = { "pk", "nj", "xymc", "zymc", "bjmc","xn",
					"wjmc" };
			List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
					tableName, outputValue, null);

			List<HashMap<String, String>> rs = service.getTjjgList(myForm);
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			if (rs != null && rs.size() > 0) {
				request.setAttribute("rsNum", rs.size());
			}
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "isxy" };
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("pjjgManage");
	}

	/**
	 * ѧ��Ʒ������_���۽��_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward pjjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����
		String pkValue = request.getParameter("pk");
		// ������
		String realTable = "pxpj_wjpjfb";
		// ����·��
		String path = "pjpy_pxpj_pjjg.do";	
		//��ʾ��Ϣ
		String message = "";
		// =================end==================

		// =================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.savePjf(myForm, realTable);
			message = resault ? "�����ɹ�" : "����ʧ��";
		}
		// =================end==================
		

		// =================ִ�в鿴���� ==================
		// �ʾ������Ϣ
		HashMap<String, String> map = service.getPjpyInfo("view_pxpj_bjqk",
				"pk", pkValue, new String[] { "bjdm", "bjmc", "id", "wjmc",
						"xn" });
		// ѧ��
		String xn = map.get("xn");
		// �ʾ���
		String wjbh = map.get("id");
		wjbh = Base.isNull(wjbh) ? myForm.getWjbh() : wjbh;
		// �༶����
		String bjdm = map.get("bjdm");
		// �༶����
		String bjmc = map.get("bjmc");
		// /�ʾ�����
		String wjmc = map.get("wjmc");
		
		// ѧ�������б�
		myForm.setBjdm(bjdm);
		myForm.setWjbh(wjbh);
		myForm.setXn(xn);
		List<HashMap<String, String>> rsList = service.getXsxxList(myForm);
		List<HashMap<String, String>> cfList = service.getXscfList(myForm);
		// =================end==================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "wjbh", "bjmc", "wjmc", "xn", "message" };
		String[] qtzdz = new String[] { wjbh, bjmc, wjmc, xn, message };

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setRsList(rsList);

		request.setAttribute("cfList", cfList);
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("pjjgUpdate");
	}
}