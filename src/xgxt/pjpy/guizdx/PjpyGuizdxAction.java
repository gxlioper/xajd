package xgxt.pjpy.guizdx;

import java.util.ArrayList;
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
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���ݴ�ѧ��������-action��
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

public class PjpyGuizdxAction extends BasicAction {
	
	/**
	 * ��������_��������_��������_����ѧУ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszXxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGuizdxService service = new PjpyGuizdxService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// ģ������(jxj:��ѧ��rych�������ƺ�)
		String mklx = request.getParameter("mklx");
		mklx = Base.isNull(mklx) ? "jxj" : mklx;
		myForm.setQueryequals_szlx(mklx);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_gzdx_pjrs_xx";
		// ����
		String realTable = "guizdx_pjpy_rssz";
		// ����·��
		String path = "pjpy_rssz.do";
		// ����ѧ��
		String xn = Base.getJxjsqxn();
		myForm.setQueryequals_xn(xn);
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "xn", "lxmc", "jxjmc", "nj", "xymc",
					"szbl", "szrs" };
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
		rForm.setMklx(mklx);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "rssz");
		// =================end ===================

		return mapping.findForward("rsszXxManage");
	}
	
	/**
	 * ��������_��������_��������_ά����ѧУ��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward rsszXxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGuizdxService service = new PjpyGuizdxService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// ģ������(jxj:��ѧ��rych�������ƺ�)
		String mklx = request.getParameter("mklx");
		mklx = Base.isNull(mklx) ? "jxj" : mklx;
		myForm.setSzlx(mklx);
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_njxyzybj";
		// ����
		String realTable = "guizdx_pjpy_rssz";
		// ����·��
		String path = "pjpy_rssz.do";
		// ����ѧ��
		String xn = Base.getJxjsqxn();
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {

			boolean result = service.savePjpyRssz(myForm, realTable);

			request.setAttribute("result", result);

		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "xn" };

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn };
		
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "rssz");
		// =================end ===================
		

		return mapping.findForward("rsszXxUpdate");
	}

	/**
	 * ��������_��������_��������_����ѧԺ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszXyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGuizdxService service = new PjpyGuizdxService();
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
		String realTable = "guizdx_pjpy_rssz";
		// ����·��
		String path = "pjpy_rssz.do";
		// ����ѧ��
		String xn = Base.getJxjsqxn();
		// �꼶
		String nj = myForm.getNj();
		// ��ѧ�����
		String jxjdm = myForm.getJxjdm();
		// ��ѧ������
		String jxjmc = service.getOneValue("jxjdmb", "jxjmc", "jxjdm", myForm
				.getJxjdm());
		// ѧԺ����
		String xymc = service.getOneValue("view_njxyzybj", "xymc", "xydm",
				userDep);
		// ѧԺ��������
		String xyrs = service.getOneValue("guizdx_pjpy_rssz", "szrs",
				"xn||szlx||bmlx||szbm||jxjdm||sznj", xn + "jxj" + "xy"
						+ userDep + jxjdm + nj);
		xyrs = Base.isNull(xyrs) ? "0" : xyrs;
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// =================Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// ѧԺ
			myForm.setXydm(userDep);
			myForm.setXn(xn);
		}
		// =================end==================
		
		// =================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {
			
			boolean result = service.savePjpyRssz_Zy(myForm, realTable);

			request.setAttribute("result", result);
		}
		// =================end==================
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			ArrayList<String[]> rs = service.getZyRsList(myForm);
			List<HashMap<String, String>> topTr = service.getTopTr("rssz_zy");
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);
		}
		// =================end ===================
		
		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expRssz_Zy(myForm, response.getOutputStream());

			return null;
		}
		// =================end ===================
		
		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "jxjmc", "xymc", "nj", "xyrs" };

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { jxjmc, xymc, nj, xyrs };
		
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
		service.setList(myForm, request, "rssz");
		// =================end ===================

		return mapping.findForward("rsszXyManage");
	}
}

