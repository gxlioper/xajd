package xgxt.xszz.hndx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxService;
import xgxt.form.RequestForm;
import xgxt.jxgl.JxglTyForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.zjjt.PjpyZjjtModel;
import xgxt.pjpy.zjjt.PjpyZjjtService;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���ϴ�ѧѧ����������-action��
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

public class XszzHndxAction extends BasicAction {

	/**
	 * ѧ������_�����������϶�_����
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward csszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzTyForm myForm = (XszzTyForm) form;
		XszzHndxService service = new XszzHndxService();
		
		// ===================== ����ֵ ===================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep= (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_xsjbxx";
		// ����
		String realTable = "hndx_xszz_jjknsrd";
		// ����·��
		String path = "xszz_cssz.do";
		// ����
		String pk = request.getParameter("pk");
		// ����ֵ
		String pkValue = request.getParameter("pkValue");
		//��ǰѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		//����ɹ����
		boolean result = false;
		// =================end==================
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			ArrayList<String[]> rs = service.getXzXyRsList(myForm);
			List<HashMap<String, String>> topTr = service.getTopTr("cssz");
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		
		RequestForm rForm = new RequestForm();
		
		// �����ֶ�
		String[] qtzd = new String[] {};

		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "jjkns");
		// =================end ===================
		
		return mapping.findForward("csszManage");
	}
	
	/**
	 * ��������_��������_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward csszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzTyForm myForm = (XszzTyForm) form;
		XszzHndxService service = new XszzHndxService();
		
		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����
		String title = "�������� - "+Base.YXPZXY_KEY+"��������";
		// ��ǰѧ��
		String xn = Base.currXn;
		// ģ������
		String mklx = request.getParameter("mklx");
		// ��ͼ��
		String tableName = ("kg".equalsIgnoreCase(mklx)) ? "hndx_xszz_kgszb"
				: "hndx_xszz_xyrsb";
		// ����
		String realTable = ("kg".equalsIgnoreCase(mklx)) ? "hndx_xszz_kgszb"
				: "hndx_xszz_xyrsb";
		// ��ʾ��Ϣ
		String message = "";
		// ����
		String pkValue = request.getParameter("pk");
		// ��Ŀ����
		String xmmc =request.getParameter("xmmc");
		// ����ɹ����
		boolean result = false;
		// ���з����������Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================
		
		// ================= ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			// ��������
			if("all".equalsIgnoreCase(mklx)){
				result = service.saveJjknsSz(myForm, realTable);
				message = result ? "�����ɹ�" : "����ʧ��";		
			}
			// ��������
			else if("kg".equalsIgnoreCase(mklx)){
				result = service.saveKg(myForm, realTable, request);
				message = result ? "�����ɹ�" : "����ʧ��";
			}
			// ����
			else if("dt".equalsIgnoreCase(mklx)){
				result = service.editXszzXmRs(myForm, realTable);
				message = result ? "�����ɹ�" : "����ʧ��";
			}
			request.setAttribute("message", message);
		}
		// =================end==================
		
		// ================= ִ�в鿴���� ==================
		
		pkValue = Base.isNull(pkValue) ? xn + xmmc
				: pkValue;
		
		// ��������
		if ("kg".equalsIgnoreCase(mklx)) {
			selectPageDataByOne(request, realTable, tableName, pkValue);
			rs = (HashMap<String, String>) request.getAttribute("rs");
		}
		// ����
		else if ("all".equalsIgnoreCase(mklx)) {
			myForm.setXn(xn);
			myForm.setXmmc(xmmc);
			List<HashMap<String,String>> blList = service.getXyRsBlList(myForm);
			request.setAttribute("blList", blList);
		}
		// ����
		else if ("dt".equalsIgnoreCase(mklx)) {
			myForm.setXn(xn);
			myForm.setXmmc(xmmc);
			myForm.setXydm(pkValue);
			List<HashMap<String,String>> blList = service.getXySzRsList(myForm);
			request.setAttribute("blList", blList);
		}
		
		// ���õ�ǰѧ��ѧ��
		rs.put("xn", xn);
		// ѧԺ
		rs.put("xydm", pkValue);
		
		// =================end==================
		
		// ===============��ʼ��request��ֵ =====================
		RequestForm rForm = new RequestForm();
		// �����ֶ�
		String[] qtzd = new String[] {};

		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};
		
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setTitle(title);
		rForm.setRs(rs);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "jjkns");
		// =================end ===================
		
		return mapping.findForward("csszUpdate");
	}
	
	/**
	 * ѧ������_�����������϶�_����
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jjknssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== ����ֵ ===================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep= (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_xsjbxx";
		// ����
		String realTable = "hndx_xszz_jjknsrd";
		// ģ������
		String mklx = "sq";
		// ����
		String title = "ѧ������ - �����������϶� - ����";
		// ����·��
		String path = "xszz_xzcd_open.do?cdlb=sqcd&doType=open";
		// ����
		String pk = request.getParameter("pk");
		// ����ֵ
		String pkValue = request.getParameter("pkValue");
		
		XszzTyForm myForm = (XszzTyForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		rForm.setTitle(title);
		// =================end ===================
		
		return jjknsUpdate(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * ѧ������_�����������϶�_���
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jjknssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== ����ֵ ===================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep= (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_hddx_jjknssq";
		// ����
		String realTable = "hndx_xszz_jjknsrd";
		// ģ������
		String mklx = "sh";
		// ����
		String title = "ѧ������ - �����������϶� - ���";
		// ����·��
		String path = "xszz_xzcd_open.do?cdlb=shcd&doType=open";
		// ����
		String pk = request.getParameter("pk");
		// ����ֵ
		String pkValue = request.getParameter("pkValue");
		
		XszzTyForm myForm = (XszzTyForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		rForm.setTitle(title);
		// =================end ===================
		
		return jjknsManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * ѧ������_�����������϶�_���
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jjknsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== ����ֵ ===================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep= (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_hddx_jjknssq";
		// ����
		String realTable = "hndx_xszz_jjknsrd";
		// ģ������
		String mklx = "jg";
		// ����
		String title = "ѧ������ - �����������϶� - �����ѯ";
		// ����·��
		String path = "xszz_xzcd_open.do?cdlb=shcd&doType=open";
		// ����
		String pk = request.getParameter("pk");
		// ����ֵ
		String pkValue = request.getParameter("pkValue");
		
		XszzTyForm myForm = (XszzTyForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		rForm.setTitle(title);
		
		request.setAttribute("rs", null);
		// =================end ===================
		
		return jjknsManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * ѧ������_�����������϶�_�鿴
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jjknsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== ����ֵ ===================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep= (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_hddx_jjknssq";
		// ����
		String realTable = "hndx_xszz_jjknsrd";
		// ģ������
		String mklx = request.getParameter("mklx");
		// ����
		String title = "ѧ������ - �����������϶� - ά��";
		// ����·��
		String path = "";
		// ����ֵ
		String pkValue = request.getParameter("pk");
		
		XszzTyForm myForm = (XszzTyForm) form;

		RequestForm rForm = new RequestForm();
		
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		rForm.setPkValue(pkValue);
		rForm.setTitle(title);
		// =================end ===================
		
		return jjknsUpdate(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * ѧ������_�����������϶�_ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jjknsUpdate(ActionMapping mapping, XszzTyForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XszzHndxService service = new XszzHndxService();
		
		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = rForm.getUserType();
		// ��½�û���
		String userName = rForm.getUserName();
		// ��½�û�����
		String userDep = rForm.getUserDep();
		// ��½�û�����
		String bzrQx = rForm.getUserDep();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����
		String realTable = rForm.getRealTable();
		// ģ������
		String mklx = rForm.getMklx();
		// ��ǰѧ��
		String xn = Base.currXn;
		// ����
		String pk = "xh||xn";
		// ����ֵ
		String pkValue = rForm.getPkValue();
		// ѧ��
		String xh = request.getParameter("xh");
		// ѧ����Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// ��ʾ��Ϣ
		String message = "";
		// ���״̬
		String shzt = request.getParameter("shzt");
		//TODO Ŀǰд��
		//��Ŀ����
		String xmmc = "�����������϶�";
		// �༶���Ȩ��
		boolean isBjsh = service.isBjsh(userName, request);
		// ��Ŀ���뿪��
		String kg = service.getOneValue("hndx_xszz_kgszb", "kg", "xn||xmmc", xn
				+ xmmc);
		boolean canSq = (!Base.isNull(kg) && "true".equalsIgnoreCase(kg)) ? true
				: false;
		// ����ɹ����
		boolean result = false;
		// =================end==================

		// ==================����Ȩ�޿��� ==================
		if (("sq".equalsIgnoreCase(mklx) || // ���뿪�عرգ���������
			("jg".equalsIgnoreCase(mklx) && "add".equalsIgnoreCase(doType)))
				&& !canSq) {
			request.setAttribute("errMsg", "Ŀǰ���ɽ��и���Ŀ�����룬��ȷ�ϣ�");
			return new ActionForward("/errMsg.do", false);
		}

		if ("stu".equalsIgnoreCase(userType)) {// ѧ���û�
			xh = userName;
		}
		// =================end ===================
		
		// ==================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {

			result = service.saveJjknssq(myForm, realTable, request);
			message = result ? "�����ɹ�" : "����ʧ��";
			request.setAttribute("result", result);
			request.setAttribute("message", message);
		}
		// =================end ===================

		// ==================ִ���ļ�ɾ������ ==================
		if ("fileDel".equalsIgnoreCase(doType)) {

			result = service.fileDel(realTable, "scdz", pk, pkValue);
			message = result ? "�����ɹ�" : "����ʧ��";
			request.setAttribute("result", result);
			request.setAttribute("message", message);

		}
		// =================end ===================
		
		// ==================ִ����˲��� ==================
		if ("sh".equalsIgnoreCase(doType)) {
			
			result = service.shJjknssq(myForm, realTable, userType, isBjsh, shzt);
			message = result ? "�����ɹ�" : "����ʧ��";
			request.setAttribute("result", result);
			request.setAttribute("message", message);

		}
		// =================end ===================
		
		// ==================ִ�в鿴���� ==================
		if ("sq".equalsIgnoreCase(mklx) || "update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType) || result) {
			
			// ��������������
			if (!Base.isNull(xh) || !Base.isNull(pkValue)) {
				
				tableName = "view_hddx_jjknssq";

				pkValue = "sq".equalsIgnoreCase(mklx) ? xh + xn : pkValue;
					
				selectPageDataByOne(request, realTable, tableName, pkValue);

				rs = (HashMap<String, String>) request.getAttribute("rs");
				
				xh = Base.isNull(rs.get("xh")) ? xh : rs.get("xh");
				xn = Base.isNull(rs.get("xn")) ? xn : rs.get("xn");
			}
					
			// ѧ��������Ϣ
			String key = "xh";
			tableName = "view_xsxxb";	
			String[] colList = new String[] { "xh", "xm", "xb", "mzmc", "csrq",
					"sfzh", "zzmmmc", "yzbm", "sjhm", "hkxz", "hkshen",
					"hkshi", "hkxian", "nj", "xydm", "xymc", "zymc", "bjmc",
					"rxrq" };
			HashMap<String, String> stuInfo = service.getXszzInfo(tableName,
					key, xh, colList);
			rs.putAll(stuInfo);
			
			// ѧ��ס����Ϣ
			tableName = "view_xszsxx";
			colList = new String[] { "xqmc", "ldmc", "cs", "qsh", "qsdh" };
			HashMap<String, String> zsInfo = service.getXszzInfo(tableName, key,
					xh, colList);

			rs.putAll(zsInfo);
		
			rs.put("xn", xn);//����Ĭ�ϱ�ѧ��
		}
		// =================end ===================
		
		// ==================ִ�����Ӳ��� ==================
		if ("add".equalsIgnoreCase(doType)) {
			rs.put("xn", xn);//ֻ�����ӵ�ǰѧ�������
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		
		// �����ֶ�
		String[] qtzd = new String[] { "isBjsh" };

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(isBjsh) };
		
		rForm.setRs(rs);
		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "jjkns");
		// =================end ===================

		return mapping.findForward("jjknsUpdate");
	}
	
	/**
	 * ѧ������_�����������϶�_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jjknsManage(ActionMapping mapping, XszzTyForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XszzHndxService service = new XszzHndxService();
		
		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = rForm.getUserType();
		// ��½�û���
		String userName = rForm.getUserName();
		// ��½�û�����
		String userDep = rForm.getUserDep();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����
		String realTable = rForm.getRealTable();
		// ģ������
		String mklx = rForm.getMklx();
		// ��ǰѧ��
		String xn = Base.currXn;
		// ����
		String pk = "xh||xn";
		// ����ֵ
		String pkValue = rForm.getPkValue();
		// ѧ��
		String xh = request.getParameter("xh");
		// ��ʾ��Ϣ
		String message = "";
		// ����ֶ�
		String shzd = "";
		// ���״̬
		String shzt = request.getParameter("shzt");
//		TODO Ŀǰд��
		//��Ŀ����
		String xmmc = "�����������϶�";
		// �༶���Ȩ��
		boolean isBjsh = service.isBjsh(userName, request);
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		//����ɹ����
		boolean result = false;
		// =================end==================
		
		// ==================����Ȩ�޿��� ==================
		
		if (isBjsh) {// �༶���Ȩ��
			if ("stu".equalsIgnoreCase(userType)) {// ѧ���û�

				String key = "xh";
				xh = userName;
				String[] colList = new String[] { "xydm", "zydm", "bjdm", "nj" };
				HashMap<String, String> stuInfo = service.getXszzInfo(
						"view_xsxxb", key, xh, colList);
				myForm.setQueryequals_xydm(stuInfo.get("xydm"));
				myForm.setQueryequals_zydm(stuInfo.get("zydm"));
				myForm.setQueryequals_bjdm(stuInfo.get("bjdm"));
				myForm.setQueryequals_nj(stuInfo.get("nj"));

				shzd = "bjsh";
			} else {
				myForm.setQueryequals_xydm(userDep);
			}
		} else if ("xy".equalsIgnoreCase(userType)) {// ѧԺ�û�

			myForm.setQueryequals_xydm(userDep);

			if ("sh".equalsIgnoreCase(mklx)) {// ���
				myForm.setQueryequals_bjsh("ͨ��");
				shzd = "xysh";
			}

		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// ѧУ������Ա��

			if ("sh".equalsIgnoreCase(mklx)) {// ���
				myForm.setQueryequals_xysh("ͨ��");
				myForm.setQueryequals_bjsh("ͨ��");

				shzd = "xxsh";
			}
			
		} else {

			if ("sh".equalsIgnoreCase(mklx)) {// ���
				String msg = "��û�в���Ȩ�ޣ���ȷ��";
				request.setAttribute("msg", msg);
			}

		}
		// =================end==================
		
		// ==================ִ����˲��� ==================
		if ("sh".equalsIgnoreCase(doType)) {

			// ���ʱ��
			String nowTime = service.getNowTime("YYYYMMDD");
			// ���״̬
			shzt = "tg".equalsIgnoreCase(shzt) ? "ͨ��" : "��ͨ��";
			// ����������
			String knsjb = myForm.getKnsjb();

			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			
			//�ж��Ƿ񳬹��������
			
			if ("ͨ��".equalsIgnoreCase(shzt)) {
				myForm.setXmmc(xmmc);
				myForm.setShzd(shzd);
				myForm.setXn(xn);
				message = service.isCgrs(myForm);
			}
			
			//û������˳��ִ�����
			if (Base.isNull(message)) {
				HashMap<String, String> valueMap = new HashMap<String, String>();
				valueMap.put(shzd, shzt);
				valueMap.put(shzd + "sj", nowTime);
				valueMap.put("knsjb", knsjb);
				auditingBatchOperation(request, primaryMap, valueMap, realTable);
			}else{
				request.setAttribute("result", false);
			}
		}
		// =================end ===================
		
		// ==================ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nj",
					"xymc", "zymc", "bjmc","jbmc", "bjsh", "xysh", "xxsh" };
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
		
		// �����ֶ�
		String[] qtzd = new String[] { "isBjsh", "message" };

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(isBjsh), message };

		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "jjkns");
		// =================end ===================
		
		return mapping.findForward("jjknsManage");
	}
}
