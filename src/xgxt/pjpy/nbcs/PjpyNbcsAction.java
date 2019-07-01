package xgxt.pjpy.nbcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zfsoft.basic.BasicAction;
import common.Globals;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ����������������-action��
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

public class PjpyNbcsAction extends BasicAction {
	
	/**
	 * ��������_�ʾ����_�ʾ���Ϣ_����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_wjdc_wjxx";
		// ����
		String realTable = "wjdc_wjxxb";
		// ����·��
		String path = "pjpy_wjManage.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {
			
			this.deleteOperation(request, realTable);
			
			// ɾ�������Ϣ
			boolean result = service.delZjxx(myForm, "id");

			if (result) {
				// ɾ���ش���Ϣ
				result = service.delHdxx(myForm, "wjbh");
			}
			
			request.setAttribute("result", result);
		}
		// =================end ===================
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
					"zjxx", "sfkq" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjManage");
	}
	
	/**
	 * ��������_�ʾ����_�ʾ���Ϣ_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ģ������
		String mklx = request.getParameter("mklx");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = "view_wjdc_wjxx";
		// ����
		String realTable = "wjdc_wjxxb";
		// ����·��
		String path = "pjpy_wjManage.do";
		// ����ֵ
		String pkValue = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ��ǰѧ��
		String xn = Base.currXn;
		// ��ǰѧ��
		String xq = Base.currXq;
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);
		// ��ǰ���
		String nd = Base.currNd;
		// ����ʱ��
		String jlsj = service.getNowTime("YYYYMMDD");
		String jlsjmc = service.getNowTime("YYYY��MM��DD��");
		// �ʾ���ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		//�������ݳɹ���־
		boolean result = false;
		// �Ƿ�ɼ�����
		boolean isSt = ("view".equalsIgnoreCase(doType)) ? true : false;
		// =================end==================
	
		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setId(id);
			if (Base.isNull(id)) {// ����
				result = service.saveWjInfo(myForm, realTable, request);
			} else {// �޸�
				result = service.updateWjInfo(myForm, realTable, request);
			}
			request.setAttribute("result", result);
		}
		// =================end ===================
		
		// ===================ִ�в鿴���� ======================
				
		// ����
		if ("add".equalsIgnoreCase(doType)) {
			// ��ʾ��
			rs.put("xn", xn);
			rs.put("xq", xq);
			rs.put("xqmc", xqmc);
			rs.put("nd", nd);
			rs.put("jlsj", jlsj);
			rs.put("jlsjmc", jlsjmc);
		}
		// �鿴���޸ģ�
		else if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType) 
				|| result) {
			
			String pk = Base.isNull(pkValue) ? "xn||xq||nd||wjmc||jlsj" : "id";
			
			pkValue = Base.isNull(pkValue) ? myForm.getXn() + myForm.getXq()
					+ myForm.getNd() + myForm.getWjmc() + myForm.getJlsj()
					: pkValue;
					
			String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc",
					"nd", "wjmc", "xn", "xq", "xqmc", "sfkq" };

			rs = service.getPjpyInfo(tableName, pk, pkValue, colList);
			
			//����ʾ������Ϣ
			if(!Base.isNull(pkValue) ){
				myForm.setId(pkValue);
				service.setWjZjInfo(myForm, request);			
			}
		}
		
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] {"id","isSt"};

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pkValue, String.valueOf(isSt) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjUpdate");
	}
	
	/**
	 * ��������_�ʾ����_������Ϣ_����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_wjdc_stxx";
		// ����
		String realTable = "wjdc_stxxb";
		// ����·��
		String path = "pjpy_stManage.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ================= ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {

			// ɾ������
			boolean result = service.delStInfo(myForm);

			if (result) {
				// ɾ����
				result = service.delDaInfo(myForm);
			}
			
			if (result) {
				// ɾ�������Ϣ
				result = service.delZjxx(myForm, "fpbh");
			}

			if (result) {
				// ɾ���ش���Ϣ
				result = service.delHdxx(myForm, "fpbh");
			}
			
			request.setAttribute("result", result);
			String message = result ? "ɾ���ɹ�" : "ɾ��ʧ��";
			request.setAttribute("result", result);
			request.setAttribute("message", message);
		}
		// =================end==================
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "stbh", "lxmc", "ssmc", "xsmc",
					"haveda" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("stManage");
	}
	
	/**
	 * ��������_�ʾ����_������Ϣ_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward stUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = "view_wjdc_stxx";
		// ����
		String realTable = "wjdc_stxxb";
		// ����·��
		String path = "pjpy_stManage.do";
		// ����ֵ
		String pkValue = request.getParameter("pk");
		// ������
		String stbh = service.getStbh();
		// ������ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		//�������ݳɹ���־
		boolean result = false;
		// =================end==================
		
		// ===================ִ�в鿴���� ======================
			
		//�Զ����ɣ�������
		rs.put("stbh", stbh);
		// �鿴���޸ģ�
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType) 
				|| result) {
			
			String pk =  "stbh";

			String[] colList = new String[] { "bz", "stbh", "stss", "stlx",
					"stmc" };

			rs = service.getPjpyInfo(tableName, pk, pkValue, colList);

		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] {};

		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("stUpdate");
	}
	
	/**
	 * ��������_�ʾ����_������Ϣ_����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward stSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����
		String realTable = "wjdc_stxxb";
		// �������ݳɹ���־
		boolean result = false;
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			// ��������
			result = service.saveStInfo(myForm, realTable, request);
			// ���������
			if (result) {
				result = service.saveDaInfo(myForm);
			}
		}
		// =================end ===================

		return stUpdate(mapping, form, request, response);
	}

	/**
	 * ��������_�ʾ����_������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "";
		// ����
		String realTable = "wjdc_zjb";
		// ����·��
		String path = "pjpy_zjManage.do";
		// =================end==================
		
		// ================= ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			
			boolean result =service.saveZjInfo(myForm,realTable);
			
			String message = result ? "�����ɹ�" : "����ʧ��";
			
			request.setAttribute("result", result);
			request.setAttribute("message", message);
		}
		// =================end==================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("zjManage");
	}

	/**
	 * ��������_�ʾ����_�ش��ʾ�_����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hdwjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_wjdc_wjxx";
		// ����
		String realTable = "wjdc_wjxxb";
		// ����·��
		String path = "pjpy_hdwjManage.do";
		//ѧ��
		String xn = Base.currXn;
		// ѧ��
		String xq = Base.currXq;
		// ���
		String nd = Base.currNd;
		// ѧУ����
		String xxdm = Base.xxdm;
		// ��������
		if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {
			myForm.setQueryequals_xn(xn);
			myForm.setQueryequals_xq(xq);
			myForm.setQueryequals_nd(nd);
		}
		//������Ϣ��ʾ
		String msg = "";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================Ȩ�޿��� ==================
		if (!"stu".equalsIgnoreCase(userType)) {
			msg = "�ʾ�ֻ����ѧ�����лش�";
		} 
		// =================end ===================
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {

			myForm.setXh(userName);

			String[] outputValue = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
					"zjxx", "hdnum" };
			List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
					tableName, outputValue, null);

			ArrayList<String[]> rs = service.getWjhdList(myForm);
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			if (rs != null && rs.size() > 0) {
				request.setAttribute("rsNum", rs.size());
			}
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		
		String[] qtzd = new String[] { "msg" };
		String[] qtzdz = new String[] { msg };
		
		RequestForm rForm = new RequestForm();

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
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdwjManage");
	}

	/**
	 * ��������_�ʾ����_�ش��ʾ�_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward hdwjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ģ������
		String mklx = request.getParameter("mklx");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = "view_wjdc_wjxx";
		// ����
		String realTable = "wjdc_wjxxb";
		// ����·��
		String path = "pjpy_hdwjManage.do";
		// ����ֵ
		String pkValue = request.getParameter("pk");
		// id
		String id = request.getParameter("id");
		pkValue = Base.isNull(pkValue) ? id : pkValue;
		// �ʾ���ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// �Ƿ�ɼ�����
		boolean isSt = true;
		// =================end==================

		// ===================ִ�в鿴���� ======================

		String pk = "id";

		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc" };

		rs = service.getPjpyInfo(tableName, pk, pkValue, colList);

		// ����ʾ������Ϣ

		// ��������
		myForm.setId(pkValue);
		myForm.setXhzgh(userName);
		myForm.setLx(userType);
		myForm.setWjbh(pkValue);

		// �������
		service.setWjZjInfo(myForm, request);


		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "id", "isSt" };

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pkValue, String.valueOf(isSt) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdwjUpdate");
	}
	
	/**
	 * ��������_�ʾ����_��_����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward daSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����
		String realTable = "wjdc_hdb";
		// �������ݳɹ���־
		boolean result = false;
		// =================end==================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setRealTable(realTable);

		// ===================end ====================
		
		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			// ��������
			result = service.saveHdzDa(myForm, rForm);
			request.setAttribute("result", result);
		}
		// =================end ===================

		return hdwjUpdate(mapping, form, request, response);
	}

	/**
	 * ��������_�ʾ����_�ش�ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hdtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_wjdc_wjxx";
		// ����
		String realTable = "wjdc_wjxxb";
		// ����·��
		String path = "pjpy_hdtjManage.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
					"zjxx","hdnum" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdtjManage");
	}
	
	/**
	 * ��������_�ʾ����_�ش��ʾ�_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward hdtjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = "view_wjdc_wjxx";
		// ����
		String realTable = "wjdc_wjxxb";
		// ����·��
		String path = "pjpy_wjManage.do";
		// ����ֵ
		String pkValue = request.getParameter("pk");
		// id
		String id = request.getParameter("id");
		pkValue = Base.isNull(pkValue) ? id : pkValue;
		// ͳ���꼶
		String nj = request.getParameter("nj");
		// ͳ��ѧԺ
		String xydm = request.getParameter("xy");
		// ͳ��רҵ
		String zydm = request.getParameter("zy");
		// ͳ�ư༶
		String bjdm = request.getParameter("bj");
		// ͳ���Ա�
		String xb = request.getParameter("xb");
		// ͳ��������ò
		String zzmm = request.getParameter("zzmm");
		// �ʾ���ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================ִ�в鿴���� ======================

		String pk = "id";
		
		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc" };

		rs = service.getPjpyInfo(tableName, pk, pkValue, colList);

		// ����ͳ����Ϣ
		myForm.setId(pkValue);
		myForm.setNj(nj);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		myForm.setXb(xb);
		myForm.setZzmm(zzmm);
		// ִ��ͳ��
		service.setWjZjInfo(myForm, request);
		
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdtjUpdate");
	}
	
	/**
	 * ��������_�ʾ����_�ش���_����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hdpjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_wjdc_wjjg";
		// ����
		String realTable = "wjdc_wjxxb";
		// ����·��
		String path = "pjpy_hdpjManage.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// ѧԺ�û�
			myForm.setQueryequals_xydm(userDep);
		}
		//	=================end==================
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nd",
					"xqmc", "nj", "xymc", "zymc", "bjmc", "wjmc", "isover" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdpjManage");
	}
	
	/**
	 * ��������_�ʾ����_�ش���_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward hdpjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = "view_wjdc_wjpj";
		// ����·��
		String path = "pjpy_hdpjManage.do";
		// ����ֵ
		String pkValue = request.getParameter("pk");
		// �ʾ���ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================ִ�в鿴���� ======================

		String pk = "pk";
		
		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc","xh","lx" };

		rs = service.getPjpyInfo(tableName, pk, pkValue, colList);

		//����ͳ����Ϣ
		myForm.setId(rs.get("id"));
		myForm.setWjbh(rs.get("id"));
		myForm.setXhzgh(rs.get("xh"));
		myForm.setLx(rs.get("lx"));
	
		service.setWjZjInfo(myForm, request);
		
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdpjUpdate");
	}
	
	/**
	 * ��������_�ʾ����_�ʾ����_����
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

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_wjdc_wjfp";
		// ����
		String realTable = "wjdc_wjfpb";
		// ����·��
		String path = "pjpy_wjfpManage.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// ѧԺ�û�
			myForm.setQueryequals_xydm(userDep);
		}
		//	=================end==================
		
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
			String[] outputColumn = { "pk", "xn", "nd", "xqmc", "nj", "xymc",
					"zymc", "bjmc", "wjmc" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjfpManage");
	}
	
	/**
	 * ��������_�ʾ����_�ʾ����_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wjfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "pjpy_wjfpManage.do";
		// ����ֵ
		String pkValue = request.getParameter("pk");
		//��ǰѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		//��ǰ���
		String nd = Base.currNd;
		myForm.setNd(nd);
		//��ǰѧ��
		String xq=Base.currXq;
		myForm.setXq(xq);
		//��ʾ��Ϣ
		String message = "";
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveWjfp(myForm);
			message = resault ? "����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[]{"message"};
		String[] qtzdz = new String[]{message};
		
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
	 * ��������_�ʾ����_����ͳ��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bbtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		// ��������
		String lx = request.getParameter("lx");
		// ����ֵ
		String pkValue = request.getParameter("pk");
		// �ʾ�ID
		String id = request.getParameter("id");
		id = Base.isNull(id) ? pkValue : id;
		myForm.setId(id);
		// ͳ���꼶
		String nj = request.getParameter("nj");
		myForm.setNj(nj);
		// ͳ��ѧԺ
		String xydm = request.getParameter("xy");
		myForm.setXydm(xydm);
		// ͳ��רҵ
		String zydm = request.getParameter("zy");
		myForm.setZydm(zydm);
		// ͳ�ư༶
		String bjdm = request.getParameter("bj");
		myForm.setBjdm(bjdm);
		// ͳ���Ա�
		String xb = request.getParameter("xb");
		myForm.setXb(xb);
		// ͳ��������ò
		String zzmm = request.getParameter("zzmm");
		myForm.setZzmm(zzmm);
		// =================end==================

		// =================ִ�е���Excel����==================

		response.reset();
		response.setContentType("application/vnd.ms-excel");

		if ("hsqk".equalsIgnoreCase(lx)) {// �������
			
			service.hsqkToExcel(myForm, response.getOutputStream());
			
		}else if("jgtj".equalsIgnoreCase(lx)){// ���ͳ��
			service.jgtjToExcel(myForm, response.getOutputStream(),request);
		}
		
		// =================end==================

		return null;

	}

}