package xgxt.dtjs.zjlg.zbgl;

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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.dtjs.DtjsForm;
import xgxt.dtjs.DtjsTyService;
import xgxt.dtjs.zjlg.ZjlgDtjsForm;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

public class ZbglAction extends DispatchAction {

	/**
	 * ��֧������
	 * 
	 * @return ActionForward
	 */
	public ActionForward zbglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		ZbglService service = new ZbglService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		ZbglModel model = new ZbglModel();

		String tableName = "";
		String realTable = "zjlg_zbmc";
//		String title = "���Ž��� - ֧������ - ֧������";

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			request.setAttribute("xydm", userDep);
		}

		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String zbdm = myForm.getZbdm();
		xydm = Base.isNull(xydm) ? "%" : xydm;
		zbdm = Base.isNull(zbdm) ? "%" : zbdm;
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("save")) {
			result = service.saveDzb(model);
			request.setAttribute("result", result);
		}else if ((request.getParameter("doType") != null)
				&& request.getParameter("doType").equalsIgnoreCase("clear")) {

			result = service.clearDzb(model);
			request.setAttribute("result", result);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "dtjs_zbgl.do");
		FormModleCommon.setNjXyZyBjListForDzb(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs, topTr);
		request.setAttribute("zbList", service.getZbList(xydm));
		request.setAttribute("dzbList", Base.isNull(zbdm)?new HashMap<String, String>():service.getZbcyList(zbdm));
		request.setAttribute("bjList", service.getWfpbjList(xydm,zydm));
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);


		return mapping.findForward("zbglManage");
	}
	
	/**
	 * ֧����Ϣ
	 * 
	 * @return ActionForward
	 */
	public ActionForward zbxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");


		ZbglService service = new ZbglService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		ZbglModel model = new ZbglModel();

		String title = "���Ž��� - ֧������ - ֧����Ϣ";
		String tableName = "view_zjlg_zbxx";
		String realTable = "";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		
		String xydm = myForm.getXydm();
		xydm = Base.isNull(xydm) ? "%" : xydm;
		
		BeanUtils.copyProperties(model, myForm);

		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] colList = new String[] { "xymc", "zbmc", "jjfz", "ybdy",
					"zsdy", "bjmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getZbxxList(tableName, model, colList);
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setZbdm(DealString.toGBK(myForm.getZbdm()));
		}
		
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "zjlg_dtjs_zbxx.do");
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("zbList", service.getZbList(xydm));
		FormModleCommon.setNjXyZyBjListForDzb(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs, topTr);

		return mapping.findForward("zbxxManage");
	}
	
	/**
	 * ֧��ά������
	 * 
	 * @return ActionForward
	 */
	public ActionForward zbwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		ZbglService service = new ZbglService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		ZbglModel model = new ZbglModel();

		String tableName = "view_zjlg_zbmc";
		String realTable = "zjlg_zbmc";
		String doType = request.getParameter("doType");
		String[] checkVal = myForm.getCheckVal();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		String xydm = myForm.getXydm();
		xydm = Base.isNull(xydm) ? "%" : xydm;
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(model, myForm);

		boolean result = false;
		
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delZbmc(checkVal);
				request.setAttribute("result", result);
			}
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xymc", "zbmc", "fdyxm" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getZbxxList(tableName, model, colList);
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setZbdm(DealString.toGBK(myForm.getZbdm()));
		}
		
		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "zjlg_dtjs_zbwh.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);

		FormModleCommon.setNjXyZyBjListForDzb(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("zbwhManage");
	}
	
	/**
	 * ֧��ά��
	 * 
	 * @return ActionForward
	 */
	public ActionForward zbwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		HttpSession session = request.getSession();
		
		String userType = session.getAttribute("userType").toString();
		String userDep = (String) session.getAttribute("userDep");
		
		String title = "���Ž��� - ֧������ - ֧��ά��";
		String doType = request.getParameter("doType");
		String pk = request.getParameter("pk");
		String zgh = request.getParameter("zgh");//ְ����
		String xydm = request.getParameter("xydm");//ѧԺ����
		String zbmc = request.getParameter("zbmc");//֧������
		
		DtjsTyService tyService= new DtjsTyService();
		ZbglService service = new ZbglService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		ZbglModel model = new ZbglModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		if ("xy".equalsIgnoreCase(userType)) {
			rs.put("xydm", userDep);
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);

		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			rs = service.getZbmcInfo(pk);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveZbmc(model, pk, request);
			request.setAttribute("result", result);
		}

		if (!Base.isNull(zgh)) {
			String fdyxm = tyService
					.getOneValue("view_fdyxx", "xm", "zgh", zgh);
			rs.put("zgh", zgh);
			rs.put("fdyxm", fdyxm);
			rs.put("xydm", xydm);
			rs.put("zbmc", zbmc);
		}
		
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForDzb(request);

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);
		request.setAttribute("pk", pk);
		
		return mapping.findForward("zbwhUpdate");
	}

	/**
	 * ֧����Ա����
	 * 
	 * @return ActionForward
	 */
	public ActionForward zbdyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_zjlg_zbdy";
		String realTable = "";
		String path = "zjlg_dtjs_zbdy.do";

		ZbglService service = new ZbglService();
		ZjlgDtjsForm myForm = (ZjlgDtjsForm) form;
		DtjsForm dtForm = new DtjsForm();
		ZbglModel model = new ZbglModel();

		String[] checkVal = myForm.getCheckVal();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);

		boolean result = false;
		
		//ɾ��֧����Ա��������ʽ��Ա��Ԥ����Ա��
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			if (checkVal != null && checkVal.length > 0) {
				result = service.delZbdy(checkVal);
				request.setAttribute("result", result);

			}
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			
			//�Ƿ����ظ�����
			String isCf = request.getParameter("isCf");
			if ("on".equalsIgnoreCase(isCf)) {

			}			

			// �����û�����MAP
			HashMap<String, String> map = service.setUserTypeMap(request);

			String[] colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
					"xymc", "zymc", "bjmc", "zbmc", "lx" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);

			rs = service.getDtjsList(tableName, model, colList, service
					.getUserTypeQuery(map));
		}
		

		// ��ʼ��request��ֵ
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);

		//��ʼ��ҳ���б�
		BeanUtils.copyProperties(dtForm, myForm);
		service.setList(dtForm, request, "zbdy");
		
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("zbdyManage");
	}
	
}
