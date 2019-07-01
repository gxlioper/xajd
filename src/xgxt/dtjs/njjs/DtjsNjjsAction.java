package xgxt.dtjs.njjs;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.DtjsForm;
import xgxt.form.RequestForm;
import xgxt.studentInfo.service.XsxxglService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �Ͼ���ʦ���Ž���-action��
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

public class DtjsNjjsAction extends BasicAction {

	/**
	 * ���Ž���_��Ա��Ϣ_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tyxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsNjjsService service = new DtjsNjjsService();
		DtjsForm myForm = (DtjsForm) form;

		// ================== ����ֵ ==================
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
		String tableName = "view_njjs_tyxx";
		// ����
		String realTable = "njjs_tyxxb";
		// ����·��
		String path = "dtjs_tyxx.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end ===================

		// ==================�ж�ģ��,Ȩ��======================

		// ��½�û�ΪѧԺ
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}

		// =================end ===================

		// ==================ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc", "mzmc", "zzmmmc", "rtsj", "rdsj", };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			expPageData(request, response, realTable, tableName, null);
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
		service.setList(myForm, request, "tyxx");
		// =================end ===================

		return mapping.findForward("tyxxManage");
	}

	/**
	 * ���Ž���_��Ա��Ϣ_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward tyxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsNjjsService service = new DtjsNjjsService();
		XsxxglService stuService = new XsxxglService();
		DtjsForm myForm = (DtjsForm) form;

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = "view_njjs_tyxx";
		// ����
		String realTable = "njjs_tyxxb";
		// ����·��
		String path = "dtjs_tyxx.do";
		// ѧ��
		String xh = request.getParameter("xh");
		// ����ֵ
		String pkValue = request.getParameter("pk");
		pkValue = Base.isNull(xh) ? pkValue : xh;
		// ��ʾ��Ϣ
		String message = "";
		// ��Ա��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// �������ݳɹ���־
		boolean result = false;
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			result = service.saveTyxx(myForm, realTable, request);
			message = result ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// ===================ִ�в鿴���� ======================

		// ����
		if ("add".equalsIgnoreCase(doType)) {

			if (!Base.isNull(xh)) {

				selectPageDataByOne(request, realTable, tableName, pkValue);

				rs = (HashMap<String, String>) request.getAttribute("rs");

				if (Base.isNull(rs.get("xh"))) {
					rs = stuService.selectStuinfo(xh);
				}
			}

			rs.put("xh", xh);
		}
		// �鿴���޸ģ�
		else if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType) || result) {

			HashMap<String, String> map = stuService.selectStuinfo(pkValue);

			selectPageDataByOne(request, realTable, tableName, pkValue);

			rs = (HashMap<String, String>) request.getAttribute("rs");
			rs.put("jg", map.get("jg"));

		}

		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "message" };
		String[] qtzdz = new String[] { message };

		rForm.setDoType(doType);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "tyxx");
		// =================end ===================

		return mapping.findForward("tyxxUpdate");
	}
}
