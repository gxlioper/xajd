package xgxt.jxgl.gzdx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.jxgl.JxglTyForm;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���ݴ�ѧѧ����Ϣ�����ѵ����-action��
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

public class JxglGzdxAction extends BasicAction {

	/**
	 * ��ѵ����_�⻺ѵ_����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward mhxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglTyForm myForm = (JxglTyForm) form;
		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_gzdx_jxgl_mhxsq";
		// ����
		String realTable = "jxgl_mhxsqb";
		// ����·��
		String path = "jxgl_mhxsq.do";
		// ��ǰѧ��
		String xn = Base.currXn;
		// ѧ��
		String xh = request.getParameter("xh");
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// =================ִ�б������=================
		if ("save".equalsIgnoreCase(doType)) {
			this.insertOperation(request, realTable);
			xh = myForm.getSave_xh();
		}
		// ===================end====================
			
		
		// =================��ʼ��ѧ����Ϣ=================
		HashMap<String, String> rs = new HashMap<String, String>();
		
		// ��½�û�Ϊѧ��
		if ("stu".equalsIgnoreCase(userType)) {
			xh = userName;
		}
		
		// ���ò���
		myForm.setXh(xh);
		myForm.setXn(xn);
		String pkValue = xh + xn;
		
		// ��ʾ��ѯ��������
		selectPageDataByOne(request, realTable, tableName, pkValue);
		
		rs = (HashMap<String, String>) request.getAttribute("rs");
		
		if (Base.isNull(rs.get("xh"))) {
			rs = service.getMhxSqInfo(myForm);
		}
		
		//����
		String jg = rs.get("jg");
		if (!Base.isNull(jg)) {
			String[] arrDq = jg.split("/");
			rs.put("jgshen", (arrDq.length >= 1) ? arrDq[0] : "");
			rs.put("jgshi", (arrDq.length >= 2) ? arrDq[1] : "");
			rs.put("jgxian", (arrDq.length >= 3) ? arrDq[2] : "");
		}

		
		// ===================end====================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName("view_jxmdxx");
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================
		
		// ==================��ʼ��request��ֵ====================
		service.setList(myForm, request, "mhx");
		//================== end ====================


		return mapping.findForward("mhxsq");
	}
	
	/**
	 * ��ѵ����_�⻺ѵ_���
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward mhxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ===================== ����ֵ ===================
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		String userDep = (String) request.getSession().getAttribute("userDep");
		String doType = request.getParameter("doType");
		String tableName = "view_gzdx_jxgl_mhxsq";
		String realTable = "jxgl_mhxsqb";
		String path = "jxgl_mhxsh.do";
		String mklx = "sh";

		JxglTyForm myForm = (JxglTyForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		// =================end ===================
		return mhxManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * ��ѵ����_�⻺ѵ_���
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward mhxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//================== ����ֵ===================
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		String userDep = (String) request.getSession().getAttribute("userDep");
		String doType = request.getParameter("doType");
		String tableName = "view_gzdx_jxgl_mhxsq";
		String realTable = "jxgl_mhxsqb";
		String path = "jxgl_mhxjg.do";
		String mklx = "jg";

		JxglTyForm myForm = (JxglTyForm) form;

		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		// =================end ===================

		return mhxManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * ��ѵ����_�⻺ѵ_����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mhxManage(ActionMapping mapping, JxglTyForm myForm,
			RequestForm rForm, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();

		// ================== ����ֵ ==================
		// ��½�û�����
		String userType = rForm.getUserType();
		// ��½�û���
		String userName = rForm.getUserName();
		// ��½�߲���
		String userDep = rForm.getUserDep();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����
		String realTable = rForm.getRealTable();
		// ����·��
		String path = rForm.getDoType();
		// ģ������
		String mklx = rForm.getMklx();
		//����ֶ�
		String shzd = "";
		//��˽��
		String shjg = request.getParameter("shjg");
		// ����;
		String pk = request.getParameter("pk");
		//��ǰѧ��
		String xn = Base.currXn;
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end ===================
		
		//==================�ж�ģ��,Ȩ��======================
		
		// ���
		if ("sh".equalsIgnoreCase(mklx)) {
			myForm.setQueryequals_xn(xn);
			// ��½�û�ΪѧУ
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				myForm.setQueryequals_xysh("ͨ��");
				shzd = "xxsh";
			}
			// ��½�û�ΪѧԺ
			else if ("xy".equalsIgnoreCase(userType)) {
				myForm.setQueryequals_xydm(userDep);
				shzd = "xysh";
			}

		}else{
			// ��½�û�ΪѧԺ
			 if ("xy".equalsIgnoreCase(userType)) {
				myForm.setQueryequals_xydm(userDep);
			}
				// ��½�û�Ϊѧ��
				else if ("stu".equalsIgnoreCase(userType)) {
					myForm.setQuerylike_xh(userName);
				}
		}
		
		// =================end ===================
		
		// ==================ִ����˲��� ==================
		if ("sh".equalsIgnoreCase(doType)) {		
			request.setAttribute("shzd", shzd);
			request.setAttribute("shjg", shjg);
			auditingBatchOperation(request, realTable);
		}
		// =================end ===================
		
		// ==================ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================
		
		//==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xn", "xh", "xm", "xb", "nj",
					"xymc", "zymc", "bjmc", "lxmc", "xysh", "xxsh" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = { "bjdm", "bjmc", "bz", "jg", "jsxm",
					"lddm", "ldmc", "lxmc", "nj", "pk", "sfzh", "sqlx", "sqly",
					"sqsj", "xb", "xh", "xm", "xn", "xxsh", "xxshsj", "xxshyj",
					"xydm", "xymc", "xysh", "xyshsj", "xyshyj", "zydm", "zymc" };
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================
		
		// ===============��ʼ��request��ֵ =====================	
		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "mhx");
		// =================end ===================

		return mapping.findForward("mhxManage");
	}

	/**
	 * ��ѵ����_�⻺ѵ_ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mhxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglTyForm myForm = (JxglTyForm) form;
		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_gzdx_jxgl_mhxsq";
		// ����
		String realTable = "jxgl_mhxsqb";
		// ����·��
		String path = "jxgl_mhxsq.do";
		// ����
		String title = "��ѵ���� - �⻺ѵά��";
		// ��ǰѧ��
		String xn = Base.currXn;
		// ģ������
		String mklx = request.getParameter("mklx");
		// ����ֵ
		String pkValue = request.getParameter("pk");
		// ѧ��
		String xh = request.getParameter("xh");
		// ����ֶ�
		String shzd = "";
		// �⻺ѵ��������Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================
		
		// =================Ȩ�޿���=================
		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			shzd = "xxsh";
		}else{
			shzd = "xysh";
		}
		
		// ==================ִ����˲��� ==================
		if ("sh".equalsIgnoreCase(doType)) {
			doType = "update";
			this.updateOperation(request, realTable);
		}
		// =================end==================
		
		// ==================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			doType = "add";
			this.insertOperation(request, realTable);
			xh = myForm.getSave_xh();
		}
		// =================end==================
		
		// ==================ִ�����Ӳ��� ==================
		if ("add".equalsIgnoreCase(doType)) {
			
			pkValue = xh + xn;
			myForm.setXh(xh);
			myForm.setXn(xn);
			
			if(!Base.isNull(xh)){

				selectPageDataByOne(request, realTable, tableName, pkValue);

				rs = (HashMap<String, String>) request.getAttribute("rs");

				if (Base.isNull(rs.get("xh"))) {
					rs = service.getMhxSqInfo(myForm);
					rs.put("save_xysh", "δ���");
					rs.put("save_xxsh", "δ���");
				}
			}
			rs.put("xn", xn);
			request.setAttribute("rs", rs);
		}
		// =================end==================
		
		//==================ִ�в鿴���� ==================
		if ("view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {
			// ��ʾ��ѯ��������
			selectPageDataByOne(request, realTable, tableName, pkValue);
			
			rs = (HashMap<String, String>) request.getAttribute("rs");
		}
		// =================end ===================

		// ==================���ü��� ==================
		// ����
		String jg = rs.get("jg");
		if (!Base.isNull(jg)) {
			String[] arrDq = jg.split("/");
			rs.put("jgshen", (arrDq.length >= 1) ? arrDq[0] : "");
			rs.put("jgshi", (arrDq.length >= 2) ? arrDq[1] : "");
			rs.put("jgxian", (arrDq.length >= 3) ? arrDq[2] : "");
		}
		// =================end ===================
		
		// ===============��ʼ��request��ֵ =====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] {"shzd"};

		// �����ֶ�ֵ
		String[] qtzdz = new String[] {shzd};
		
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName("view_jxmdxx");
		rForm.setRealTable(realTable);
		rForm.setTitle(title);
		rForm.setRs(rs);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "mhx");
		// =================end ===================

		return mapping.findForward("mhxUpdate");
	}
	
	/**
	 * ��ѵ����_�⻺ѵ_��ӡ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mhxPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {


		JxglTyForm myForm = (JxglTyForm) form;
		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_gzdx_jxgl_mhxsq";
		// ����
		String realTable = "jxgl_mhxsqb";
		// ����·��
		String path = "jxgl_mhxsq.do";
		// ����
		String pkValue = request.getParameter("pkValue");
		// =================end==================
		
		// ================= ����ֵ ==================
		// ��ʾ��ѯ��������
		selectPageDataByOne(request, realTable, tableName, pkValue);

		HashMap<String, String> rs = (HashMap<String, String>) request
				.getAttribute("rs");
		
		// ����
		String jg = rs.get("jg");
		if (!Base.isNull(jg)) {
			rs.put("jg", service.getJgInfo(jg));
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
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================
		
		return mapping.findForward("mhxPrint");
	}

	/**
	 * ��ѵ����_��Ϣά��_���ܳɼ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jncjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();
		JxglTyForm myForm = (JxglTyForm) form;
		
		//	================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// ��½�û�����
		String userDep= (String) request.getSession().getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_gzdx_jxgl_jncj";
		// ����
		String realTable = "gzdx_jxgl_jncjb";
		// ����·��
		String path = "jxgl_jxjncj.do";
		// ��ǰѧ��
		String xn = Base.currXn;
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		//ѧУ����
		String xxdm = Base.xxdm;
		// =================end==================
		
		// ==================�ж�ģ��,Ȩ��======================
		// ���õ�ǰѧ��
		if (Base.isNull(myForm.getQueryequals_xn())) {
			myForm.setQueryequals_xn(xn);
		}
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setQueryequals_xydm(userDep);
		}
		// =================end ===================
		

		// ==================ִ�б������======================
		if ("save".equalsIgnoreCase(doType)) {
			HashMap<String, String[]> primaryArrayMap = this.getValueArrayMap(request, "primarykey_");
			HashMap<String, String> primaryMap = new HashMap<String, String>();
			primaryMap.put("xn", myForm.getQueryequals_xn());
			HashMap<String, String> tableMap = new HashMap<String, String>();
			tableMap.put("tableName", realTable);
			tableMap.put("viewName", tableName);
			this.savePageDataBatch(request, primaryArrayMap, primaryMap, tableMap);
		}
		// =================end ===================
		
		//==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xn", "xh", "xm", "xb", "nj",
					"xymc", "zymc", "bjmc","ldmc","cj" };
			
			//����ְҵ����ѧԺ
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)){
				outputColumn =new String[]{ "pk", "xn", "xh","sfzh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc","cj" };
			}
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

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "jxcj");
		// =================end ===================

		return mapping.findForward("jncjManage");
	}
	
	/**
	 * ��ѵ����_��Ϣά��_�ɼ��鿴
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjckManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();
		JxglTyForm myForm = (JxglTyForm) form;
		
		//	================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// ��½�û�����
		String userDep= (String) request.getSession().getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_gzdx_jxgl_cjck";
		// ����
		String realTable = "gzdx_jxgl_jncjb";
		// ����·��
		String path = "jxgl_jxcjck.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		//ѧУ����
		String xxdm=Base.xxdm;
		// =================end==================
		
		// ==================�ж�ģ��,Ȩ��======================
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setQueryequals_xydm(userDep);
		}
		// =================end ===================
		
		//==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xn", "xh", "xm", "xb", "nj",
					"xymc", "zymc", "bjmc", "ldmc", "llcj", "jncj" };
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)){
				outputColumn =new String[] { "pk", "xn", "xh", "xm","sfzh", "xb", "nj",
						"xymc", "zymc", "bjmc", "ldmc", "llcj", "jncj" };
			}
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = { "pk", "xn", "xh", "xm", "xb", "nj",
					"xymc", "zymc", "bjmc", "ldmc", "llcj", "jncj" };
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

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "jxcj");
		// =================end ===================

		return mapping.findForward("cjckManage");
	}
}
