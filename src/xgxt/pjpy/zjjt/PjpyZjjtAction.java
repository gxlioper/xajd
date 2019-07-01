package xgxt.pjpy.zjjt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.jxgl.JxglTyForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �㽭��ͨ��ʦ��������-action��
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

public class PjpyZjjtAction extends BasicAction {

	/**
	 * ��������_���з����
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTyForm myForm = (PjpyTyForm) form;
		myForm.setMklx("sh");
		return cxfManage(mapping, myForm, request, response);
	}

	/**
	 * ��������_���зֽ��
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTyForm myForm = (PjpyTyForm) form;
		myForm.setMklx("jg");
		return cxfManage(mapping, myForm, request, response);
	}
	
	/**
	 * ��������_���зֹ���
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfManage(ActionMapping mapping, PjpyTyForm myForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();

		String userName = (String) request.getSession().getAttribute("userName");
		String userType = (String) request.getSession().getAttribute("userType");
		String userDep = (String) request.getSession().getAttribute("userDep");
		String doType = request.getParameter("doType");
		String tableName = "view_zjjt_cxf";
		String realTable = "zjjt_cxfb";
		String path = "pjpy_cxfsh.do";
		String mklx = myForm.getMklx();
		String title = "sh".equalsIgnoreCase(mklx) ? "�������� - ���з� - ���"
				: "�������� - ���з� - ���";
		
		BeanUtils.copyProperties(model, myForm);
		
		if("stu".equalsIgnoreCase(userType)){
			//	��ʼ����½�������Ϣ
			String pk = "xh";
			String pkValue = userName;
			String[] colList = new String[] { "xh", "nj", "xydm", "zydm",
					"bjdm" };
			
			HashMap<String,String> map = service.getPjpyInfo("view_xsjbxx", pk, pkValue, colList);
			
			myForm.setNj(map.get("nj"));
			myForm.setXydm(map.get("xydm"));
			myForm.setZydm(map.get("zydm"));
			myForm.setBjdm(map.get("bjdm"));
			myForm.setXh(map.get("xh"));
		}else if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		boolean result = false;
		
		// ����ɾ�����з�
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||pjxh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delPjpy(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
					"xymc", "zymc", "bjmc", "addValue", "subValue" };
			if ("jg".equalsIgnoreCase(mklx)) {
				colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
						"xymc", "zymc", "bjmc", "addValue", "addV", "subValue",
						"subV", "cxf" };
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}
		
		// ��ʼ���б�ֵ
		service.setList(myForm, request, "cxf");
		
		// ����Request��ֵ
		RequestForm rForm = new RequestForm();
		rForm.setPath(path);
		rForm.setMklx(mklx);
		service.setRequestValue(rForm, request);
		
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		
		request.setAttribute("title", title);
		request.setAttribute("tableName", "view_zjjt_cxf_ex");

		return mapping.findForward("cxfManage");
	}
	
	/**
	 * ��������_���з�ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();

		String doType = request.getParameter("doType");
		String type = request.getParameter("type");
		doType = Base.isNull(doType) ? "add" : doType;
		String userName = (String) request.getSession().getAttribute("userName");
		String userType = (String) request.getSession().getAttribute("userType");
		String tableName = "view_zjjt_cxf";
		String realTable = "zjjt_cxfb";
		String title = "�������� - ���з� - ά��";
		String xn = Base.getJxjsqxn();//����ѧ��
		String xq = Base.getJxjsqxq();//����ѧ��
		String xh = request.getParameter("xh");
		String pk = "xn||xq||xh";
		String key = request.getParameter("pk");
		
		BeanUtils.copyProperties(model, myForm);
	
		HashMap<String, String> rs = new HashMap<String, String>();
		
		// �жϵ�½���Ƿ���ѧ��
		if ("stu".equalsIgnoreCase(userType)) {
			// ���ѧ��������Ϣ
			rs = service.getDetStuInfo(userName);
			rs.put("xn", xn);
			rs.put("xq", xq);
		} else {
			
			if ("add".equalsIgnoreCase(doType)&&!Base.isNull(xh)) {
				key = xn + xq + xh;
			}
			String[] colList = new String[] { "xn", "xq", "xh", "xm",
					"xymc", "zymc", "bjmc", "nj", "xb", "xm" };
			rs = service.getPjpyInfo(tableName, pk, key, colList);
			
			//��ѧ���ޱ�ѧ��ѧ�ڲ��з�
			if(Base.isNull(rs.get("xh"))){
				rs = service.getDetStuInfo(xh);
				rs.put("xn", xn);
				rs.put("xq", xq);
			}
		}

		//��ѧ���û��������
		if ("add".equalsIgnoreCase(doType) && !Base.isNull(xh)) {	
			key = service.getOneValue("zjjt_cxfb", "pjxh", "xn||xq||pjxh", key);
		}
		
		//������мӼ���
		if ("save".equalsIgnoreCase(doType)) {
	
			String[] arrzd = new String[] { "pjxh", "jjf", "fz", "rq", "sy",
					"shjg" };
			String[] onezd = new String[] { "xn", "xq" };
			pk = "pjxh";
			
			// ��������
			String[] jjf = myForm.getJjf();
			String[] pjxh = null;
			String[] pkValue = null;
			
			if (jjf != null && jjf.length > 0) {
				pjxh = new String[jjf.length];
				pkValue = new String[jjf.length];
				for (int i = 0; i < jjf.length; i++) {
					pjxh[i] = myForm.getXh();
					pkValue[i] = myForm.getXh();
				}
				model.setPjxh(pjxh);
			}
				
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			
			//ִ�в�����������
			boolean result = service.savePjpy(saveForm, model);
			
			request.setAttribute("type", type);
			request.setAttribute("result", result);
		
		}
		
		// ��ʼ���б�ֵ
		service.setList(myForm, request, "cxf");
		
		// ����Request��ֵ
		RequestForm rForm = new RequestForm();
		rForm.setTitle(title);
		rForm.setRs(rs);
		rForm.setDoType(doType);
		service.setRequestValue(rForm, request);
		
		return mapping.findForward("cxfUpdate");
	}

	/**
	 * ��������_���з�_¼��
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfwhLr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== ����ֵ ===================
		HttpSession session = request.getSession();
		
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// ģ������
		String mklx = "lr";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_zjjt_cxfsbxs";
		// ����
		String realTable = "zjjt_cxflrb";
		// ����·��
		String path = "pjpy_cxflr.do";
		
		PjpyTyForm myForm = (PjpyTyForm) form;

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
		
		return cxfwhManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * ��������_���з�_��ѯ
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfwhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== ����ֵ ===================
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// ��½�û�����
		String userDep = (String) request.getSession().getAttribute("userDep");
		// ģ������
		String mklx = "cx";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_zjjt_cxfjg";
		// ����
		String realTable = "zjjt_cxflrb";
		// ����·��
		String path = "pjpy_cxfcx.do";

		PjpyTyForm myForm = (PjpyTyForm) form;

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
		
		return cxfcxManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * ��������_���з�_����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfwhManage(ActionMapping mapping, PjpyTyForm form,RequestForm rForm, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();
		
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
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		
		BeanUtils.copyProperties(model, myForm);
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] colList = null;
			if ("lr".equalsIgnoreCase(mklx)) {//¼��
				colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh" };
			}else if("cx".equalsIgnoreCase(mklx)) {//��ѯ
				colList = new String[] { "pk", "xh", "xm", "xb","xn","xqm", "xymc",
						"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh", "mrz",
						"jiaf", "jianf", "cxf" };
			}
			List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
					tableName, colList, null);
			
			String otherQuery = CommonQueryDAO.getQuerySqlByUser(getUser(request), "a", "xydm", "bjdm");
			
			ArrayList<String[]> rs = service.getPjpyList(tableName,myForm,
					colList, otherQuery);
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);
		}
		// =================end ===================
		
		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		// =================end==================
		
		// ===============��ʼ��request��ֵ =====================
		// �����ֶ�
		// String[] qtzd = new String[] {"shzd"};

		// �����ֶ�ֵ
		// String[] qtzdz = new String[] {shzd};

		// rForm.setQtzd(qtzd);
		// rForm.setQtzdz(qtzdz);

		if ("cx".equalsIgnoreCase(mklx)) {// ���������ݵ���ͼ
			tableName = "view_zjjt_cxfexp";
			rForm.setTableName(tableName);
		}
		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "cxf");
		// =================end ===================
		
		return mapping.findForward("cxfwhManage");
	}
	
	/**
	 * ��������_���з�_����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfcxManage(ActionMapping mapping, PjpyTyForm form,RequestForm rForm, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();
		
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
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		
		BeanUtils.copyProperties(model, myForm);
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] colList = null;
			if ("lr".equalsIgnoreCase(mklx)) {//¼��
				colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh" };
			}else if("cx".equalsIgnoreCase(mklx)) {//��ѯ
				colList = new String[] { "pk", "xh", "xm", "xb","xn","xqm", "xymc",
						"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh", "mrz",
						"jiaf", "jianf", "cxf" };
			}
			List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
					tableName, colList, null);
			
			String otherQuery = CommonQueryDAO.getQuerySqlByUser(getUser(request), "a", "xydm", "bjdm");
			
			ArrayList<String[]> rs = service.getPjpyList(myForm,
					colList, otherQuery);
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);
		}
		// =================end ===================
		
		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		// =================end==================
		
		// ===============��ʼ��request��ֵ =====================
		// �����ֶ�
		// String[] qtzd = new String[] {"shzd"};

		// �����ֶ�ֵ
		// String[] qtzdz = new String[] {shzd};

		// rForm.setQtzd(qtzd);
		// rForm.setQtzdz(qtzdz);

		if ("cx".equalsIgnoreCase(mklx)) {// ���������ݵ���ͼ
			tableName = "view_zjjt_cxfexp";
			rForm.setTableName(tableName);
		}
		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "cxf");
		// =================end ===================
		
		return mapping.findForward("cxfwhManage");
	}
	
	/**
	 * ��������_���з�_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();
		
		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����
		String title = "���з� - ά��";
		// ��ǰѧ��
		String xn = Base.currXn;
		// ��ǰѧ��
		String xq = Base.currXq;
		// ģ������
		String mklx = request.getParameter("mklx");
		// ��ͼ��
		String tableName = ("lr".equalsIgnoreCase(mklx)) ? "view_zjjt_cxfsbxs"
				: "view_zjjt_cxfjg";
		// ����
		String realTable = "zjjt_cxflrb";
		// ����
		String pk = ("lr".equalsIgnoreCase(mklx))?"xh":"xh||xn||xq";
		// ����ֵ
		String pkValue = request.getParameter("pk");
		// ���з���������Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// ���з���������Ϣ
		List<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();
		//������
		boolean result = false;
		// =================end==================
		
		BeanUtils.copyProperties(model, myForm);
		
		// ================= ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			pkValue = model.getXh();
			result = service.saveCxf(model, realTable);
			request.setAttribute("result", result);
		}
		// =================end==================
		
		// ================= ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {
			result = service.delCxf(model, realTable);
			request.setAttribute("result", result);
		}
		// =================end==================
		
		// ================= ִ�в鿴���� ==================
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType) || result) {
			if("cx".equalsIgnoreCase(mklx)){
				String[] colList = new String[] { "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "ldmc", "xqmc", "cs", "qsh", "nj","sfzh" ,"xn","xq"};
				rs = service.getPjpyInfo(tableName, pk, pkValue, colList);
				
				myForm.setXh(rs.get("xh"));
				myForm.setXn(rs.get("xn"));
				myForm.setXq(rs.get("xq"));
				rsList = service.getCxfXxList(myForm);
			}else {
				String[] colList = new String[] { "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "ldmc", "xqmc", "cs", "qsh", "nj","sfzh" };
				rs = service.getPjpyInfo(tableName, pk, pkValue, colList);
				rs.put("xn", xn);
				rs.put("xq", xq);
				
				myForm.setXh(rs.get("xh"));
				myForm.setXn(rs.get("xn"));
				myForm.setXq(rs.get("xq"));
			}
		}
		// =================end==================
		
		// ===============��ʼ��request��ֵ =====================
		RequestForm rForm = new RequestForm();
		//	�����ֶ�
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
		rForm.setRsList(rsList);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "cxf");
		// =================end ===================
		
		return mapping.findForward("cxfwhUpdate");
	}
	
	/**
	 * ��������_���з�_����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward szManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();
		
		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����
		String title = "���з� - ����";
		// ��ǰѧ��
		String xn = Base.currXn;
		// ��ǰѧ��
		String xq = Base.currXq;
		// ģ������
		String mklx = request.getParameter("mklx");
		// ��ͼ��
		String tableName = "zjjt_cxf_sz";
		// ����
		String realTable = "zjjt_cxf_sz";
		// ����
		String pk = "xn||xq";
		// ����ֵ
		String pkValue = xn + xq;
		// ���з����������Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================
		
		BeanUtils.copyProperties(model, myForm);
		
		// ================= ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
			this.insertOperation(request, realTable);
		}
		// =================end==================
		
		// ================= ִ�в鿴���� ==================
		selectPageDataByOne(request, realTable, tableName, pkValue);
		rs = (HashMap<String, String>) request.getAttribute("rs");
		// ���õ�ǰѧ��ѧ��
		rs.put("xn", xn);
		rs.put("xq", xq);
		rs.put("save_xn", xn);
		rs.put("save_xq", xq);
		// =================end==================
		
		// ===============��ʼ��request��ֵ =====================
		RequestForm rForm = new RequestForm();
		//	�����ֶ�
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
		rForm.setPk(pkValue);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "cxf");
		// =================end ===================
		
		return mapping.findForward("szManage");
	}
	
	/**
	 * ��������_���з�_ͳ��
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfwhTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();
		
		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û����ڲ���
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ǰѧ��
		String xn = Base.currXn;
		// ��ǰѧ��
		String xq = Base.currXq;
		//·��
		String path = "pjpy_cxftj.do";
		// ģ������
		String mklx = request.getParameter("mklx");
		// ��ͼ��
		String tableName = "";
		// ����
		String realTable = "";
		// ����
		String pk = "";
		// ����ֵ
		String pkValue = "";
		String tjfs = myForm.getTjfs();
		if(Base.isNull(tjfs)){
			myForm.setTjfs("xq");
		}
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		BeanUtils.copyProperties(model, myForm);
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			List<HashMap<String, String>> topTr = null;
			
			if("xq".equalsIgnoreCase(model.getTjfs())){
				topTr = service.getTopTr("cxftj");
			}else{
				topTr = service.getTopTr("cxftjxn");
			}
			
			ArrayList<String[]> rs = (ArrayList<String[]>) service.getCxfTj(myForm,getUser(request));
			//ArrayList<String[]> rs = service.getCxfTjInfo(myForm);
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs, topTr);
		}
		// =================end ===================
		
		//================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		// =================end==================
		
		
		// ==================ִ�д�ӡ������� ==================
		if (!Base.isNull(doType) && "print".equalsIgnoreCase(doType)) {

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.printCxfTjbb(myForm, response.getOutputStream());

			return null;
		}
		// =================end ===================
		
		// ===============��ʼ��request��ֵ =====================
		RequestForm rForm = new RequestForm();
		
		//	�����ֶ�
		String[] qtzd = new String[] {};

		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};
		
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setPk(pkValue);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================��ʼ��request��ֵ ======================
		service.setList(myForm, request, "cxf");
		// =================end ===================
		
		return mapping.findForward("cxfwhTj");
	}
	
	
	/**
	 * ��ʼ�����з�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initCxf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		
		request.setAttribute("result", service.initCxf(myForm));
		myForm.setXn(null);
		myForm.setXq(null);
		return cxfwhLr(mapping, form, request, response);
	}
}
