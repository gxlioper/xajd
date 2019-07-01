package xgxt.wjdc;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.studentInfo.service.XsxxglService;

import common.GlobalsVariable;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ʾ����-action��
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

public class WjdcAction extends WjdcRealizAtion {

	// ======================���������ղ�======================================

	/**
	 * �ʾ���Ϣ_���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcWjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "xlpc_wjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ʾ���Ϣ_ά���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcWjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "xlpc_wjManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return wjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * ������Ϣ_���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcStManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "xlpc_stManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * ������Ϣ_ά���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcStUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "xlpc_stManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setMklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * ������������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcZjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "xlpc_zjManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return zjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ʾ���䣨�����ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcWjfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "xlpc_wjfpManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ʾ�������ã������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcWjfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		// ����
		String pk = request.getParameter("pk");
		// ����·��
		String path = "xlpc_wjfpManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش��ʾ�_���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdwjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "xlpc_hdwjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdwjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش��ʾ�_ά���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdwjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		// ����
		String pk = request.getParameter("pk");
		// ����·��
		String path = "xlpc_hdwjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;
		// ID
		String id = request.getParameter("id");
		// ����
		String lx = request.getParameter("lx");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setLx(lx);
		myForm.setId(id);
		// ================= end ==================

		return hdwjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش�ͳ��_���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String realTable = "";
		// ����·��
		String path = "xlpc_hdtjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdtjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش�ͳ��_ά���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdtjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String realTable = "";
		// ����·��
		String path = "xlpc_hdtjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
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

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		myForm.setNj(nj);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		myForm.setXb(xb);
		myForm.setZzmm(zzmm);
		// ================= end ==================

		return hdtjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش���_���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "xlpc_hdjgManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdjgManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش���_ά���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_wjdc_wjpj";
		// ����
		String realTable = "";
		// ����·��
		String path = "xlpc_hdjgManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return hdjgUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * �������_���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcFxjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_wjdc_fxjg";
		// ����
		String realTable = "wjdc_jgfxb";
		// ����·��
		String path = "xlpc_fxjgManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return fxjgManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �������_ά���������ղ飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcFxjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_wjdc_fxjg";
		// ����
		String realTable = "";
		// ����·��
		String path = "xlpc_fxjgManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_XLPC;
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return fxjgUpdate(mapping, myForm, rForm, request, response);
	}

	// ======================���������ղ� end==================================

	// ======================˼��״������======================================

	/**
	 * �ʾ���Ϣ_����˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkWjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxzk_wjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ʾ���Ϣ_ά����˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkWjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxzk_wjManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return wjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * ������Ϣ_����˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkStManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxzk_stManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * ������Ϣ_ά����˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkStUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxzk_stManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setMklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * �������˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkZjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxzk_zjManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return zjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ʾ���䣨˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkWjfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxzk_wjfpManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ʾ�������ã�˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkWjfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		// ����
		String pk = request.getParameter("pk");
		// ����·��
		String path = "sxzk_wjfpManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش��ʾ�_����˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdwjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxzk_hdwjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdwjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش��ʾ�_ά����˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdwjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		// ����
		String pk = request.getParameter("pk");
		// ����·��
		String path = "sxzk_hdwjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;
		// ID
		String id = request.getParameter("id");
		// ����
		String lx = request.getParameter("lx");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setLx(lx);
		myForm.setId(id);
		// ================= end ==================

		return hdwjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش�ͳ��_����˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String realTable = "";
		// ����·��
		String path = "sxzk_hdtjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdtjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش�ͳ��_ά����˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdtjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String realTable = "";
		// ����·��
		String path = "sxzk_hdtjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
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

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		myForm.setNj(nj);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		myForm.setXb(xb);
		myForm.setZzmm(zzmm);
		// ================= end ==================

		return hdtjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش���_����˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxzk_hdjgManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdjgManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ش���_ά����˼��״�����飩
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_wjdc_wjpj";
		// ����
		String realTable = "";
		// ����·��
		String path = "sxzk_hdjgManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXZK;
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return hdjgUpdate(mapping, myForm, rForm, request, response);
	}

	// ======================˼��״������ end =================================

	// ======================ͨ��======================================
	/**
	 * �ʾ����_������Ϣ_����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward stSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjdcService service = new WjdcService();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����
		String realTable = "wjdc_stxxb";
		// ����ʱ��
		String jlsj = myForm.getJlsj();
		// �Ƿ����

		if (Base.isNull(jlsj)) {
			jlsj = service.getNowTime("YYYYMMDD");
			myForm.setJlsj(jlsj);
		}
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
			rForm.setMessage(result ? "����ɹ�!" : "����ʧ��!");
		}
		// =================end ===================

		return stUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ʾ����_��_����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward daSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjdcService service = new WjdcService();
		WjdcForm myForm = (WjdcForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ
		String tableName = request.getParameter("tableName");
		// ����
		String realTable = "wjdc_hdb";
		// ·��
		String path = request.getParameter("path");
		// �������ݳɹ���־
		boolean result = false;
		// =================end==================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		// ===================end ====================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			// ��������
			result = service.saveHdzDa(myForm, rForm);
			String message = result ? "�����ɹ�" : "����ʧ��";
			rForm.setMessage(message);

		}
		// =================end ===================

		return hdwjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ʾ����_����ͳ��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bbtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjdcService service = new WjdcService();
		WjdcForm myForm = (WjdcForm) form;

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

		} else if ("jgtj".equalsIgnoreCase(lx)) {// ���ͳ��
			service.jgtjToExcel(myForm, response.getOutputStream(), request);
		}

		// =================end==================

		return null;

	}
	// ======================ͨ�� end======================================
	
	
	
	
	
	
	//====================ʵϰ��״������========================
	/**
	 * ʵϰ��״������-�ʾ�ά�� ����
	 */
	public ActionForward sxszkWjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxszk_wjManage.do";
		// ģ������(ʵϰ��״��)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjManage(mapping, myForm, rForm, request, response);
	}
	
	
	/**
	 * ʵϰ��״������-�ʾ�ά�� ά��
	 */
	public ActionForward sxszkWjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxszk_wjManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return wjUpdate(mapping, myForm, rForm, request, response);
	}

	
	/**
	 * ʵϰ��״������-��Ŀά�� ����
	 */
	public ActionForward sxszkStManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxszk_stManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stManage(mapping, myForm, rForm, request, response);
	}
	

	/**
	 * ʵϰ��״������-��Ŀά��
	 */
	public ActionForward sxszkStUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxszk_stManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setMklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stUpdate(mapping, myForm, rForm, request, response);
	}

	
	/**
	 * ʵϰ��״������-���ά��
	 */
	public ActionForward sxszkZjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxszk_zjManage.do";
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return zjManage(mapping, myForm, rForm, request, response);
	}

	
	/**
	 * ʵϰ��״������-�ʾ�������
	 */
	public ActionForward sxszkWjfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxszk_wjfpManage.do";
		// ģ������(ʵϰ��״��)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpManage(mapping, myForm, rForm, request, response);
	}
	
	
	/**
	 * ʵϰ��״������-�ʾ��������
	 */
	public ActionForward sxszkWjfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		// ����
		String pk = request.getParameter("pk");
		// ����·��
		String path = "sxszk_wjfpManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpUpdate(mapping, myForm, rForm, request, response);
	}


	/**
	 * ʵϰ��״������-�ش��ʾ����
	 */
	public ActionForward sxszkHdwjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxszk_hdwjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdwjManage(mapping, myForm, rForm, request, response);
	}


	/**
	 * ʵϰ��״������-�ش��ʾ�ά��
	 */
	public ActionForward sxszkHdwjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();
		// ================= ����ֵ ==================
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
		// ����
		String pk = request.getParameter("pk");
		// ����·��
		String path = "sxszk_hdwjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXSZK;
		// ID
		String id = request.getParameter("id");
		// ����
		String lx = request.getParameter("lx");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setLx(lx);
		myForm.setId(id);
		// ================= end ==================

		//ѧ��������Ϣ
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> stuInfo = xsxxglService.selectStuinfo(userName);
		request.setAttribute("stuInfo", stuInfo);
		
		return hdwj(mapping, myForm, rForm, request, response);
	}

	
	
	/**
	 * ʵϰ��״������-�ش��ʾ�
	 */
	private ActionForward hdwj(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = rForm.getUserType();
		// ��½�û���
		String userName = rForm.getUserName();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����ֵ
		String pkValue = rForm.getPk();
		// id
		String id = myForm.getId();
		pkValue = Base.isNull(pkValue) ? id : pkValue;
		// ����
		String lx = myForm.getLx();
		// �ʾ���ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// �Ƿ�ɼ�����
		boolean isSt = true;
		// =================end==================

		// ===================ִ�в鿴���� ======================

		String pk = "id";
		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc", "kyxg", "dawk","jwy" };

		rs = service.getWjdcInfo(tableName, pk, pkValue, colList);
		
		//ѧ���������
		rs.putAll(service.getWjdcInfo("xg_wjdc_xsjbqkb", "xh||wjid", 
				rForm.getUserName()+pkValue, new String[]{"sxdwmc","gzdd","gzgw","lxfs"}));
		
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

		// �����ֶ�
		String[] qtzd = new String[] { "id", "isSt", "bclx" };

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pkValue, String.valueOf(isSt), lx };

		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdwj");
	}

	
	
	/**
	 * ʵϰ��״������-�ش��ʾ���
	 */
	public ActionForward hdwjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjdcService service = new WjdcService();
		WjdcForm myForm = (WjdcForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ
		String tableName = request.getParameter("tableName");
		// ����
		String realTable = "wjdc_hdb";
		// ·��
		String path = request.getParameter("path");
		// �������ݳɹ���־
		boolean result = false;
		// =================end==================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		// ===================end ====================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			//����ʵϰ���������
			myForm.setXh(userName);
			myForm.setWjid(myForm.getId());
			result = service.saveSxsjbqk(myForm,request);
			// ��������
			result = result ? service.saveHdzDa(myForm, rForm) : result;
			String message = result ? "�����ɹ�" : "����ʧ��";
			rForm.setMessage(message);

		}
		// =================end ===================

		return hdwj(mapping, myForm, rForm, request, response);
	}
	
	
	
	/**
	 * ʵϰ��״������-�ش�ͳ��
	 */
	public ActionForward sxszkHdtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String realTable = "";
		// ����·��
		String path = "sxszk_hdtjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdtjManage(mapping, myForm, rForm, request, response);
	}

	
	/**
	 * ʵϰ��״������-�ش�ͳ��
	 */
	public ActionForward sxszkHdtjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String realTable = "";
		// ����·��
		String path = "sxszk_hdtjManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXSZK;
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
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

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		myForm.setNj(nj);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		myForm.setXb(xb);
		myForm.setZzmm(zzmm);
		// ================= end ==================

		return hdtjUpdate(mapping, myForm, rForm, request, response);
	}
	
	

	/**
	 * ʵϰ��״������-�ش���
	 */
	public ActionForward sxszkHdjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
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
		String path = "sxszk_hdjgManage.do";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdjgManage(mapping, myForm, rForm, request, response);
	}
	
	
	/**
	 * ʵϰ��״������-�ش���
	 */
	public ActionForward sxszkHdjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_wjdc_wjpj";
		// ����
		String realTable = "";
		// ģ������(�����ղ�)
		String mklx = GlobalsVariable.WJDC_SXSZK;
		// ����
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return hdjgUpdate(mapping, myForm, rForm, request, response);
	}
}
