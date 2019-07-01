package xgxt.pjpy.zjcm.xfjs;

import java.lang.reflect.InvocationTargetException;
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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyXfjsAction extends CommonAction {
	PjpyXfjsService service = new PjpyXfjsService();

	/**
	 * ѧ��������ά����ҳ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		boolean xxOper = false;
		User user =  getUser(request);;
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		xxOper = "xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType) ? true : false;

		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		PjpyXfjsForm model = new PjpyXfjsForm();

		String tableName = "view_pjpy_xfjs_bjccqkb";
		String realTable = "pjpy_xfjs_bjccqkb";
		String title = "�������� - ѧ�罨�� - ѧ��������";

		if ("stu".equalsIgnoreCase(userType)) {
			String msg = "��ҳ��ѧ�����ܷ��ʣ�";
			request.setAttribute("msg", msg);
		}

		myForm
				.setFdy(session.getAttribute("fdyQx").equals(true) ? true
						: false);
		myForm.setUserName(userName);
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			if (!myForm.isFdy()) {// �Ǹ���Ա
			//myForm.setCcyhlx("xy");
			}
			xxOper = true;
		}

		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		// ==========2012.12.10 edit by luojw begin============
		if (Base.isNull(myForm.getXn())) {
			myForm.setXn(Base.currXn);
		}

		if (Base.isNull(myForm.getXq())) {
			myForm.setXq(Base.currXq);
		}
		// ==========2012.12.10 edit by luojw end============

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xn", "xqmc", "xymc",
					"bjmc", "jclxmc", "ydrs", "sdrs", "qqrs", "ccrq",
					"fdyclsj", "fdysjclsj", "xxsh", "ccyhlx" };// pk =
			// xn||xq||bjdm||ccrq||jclxdm
			String[] topList = new String[] { "xn", "xqmc", "xymc", "bjmc",
					"jclxmc", "ydrs", "sdrs", "qqrs", "ccrq", "fdyclsj",
					"fdysjclsj", "xxsh", "����û�" };

			topTr = service.getTopTr(tableName, topList);
			rs = service.queryXsccqkb(model, colList,user);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "pjpy_xfjs_xsccqk.do");
		request.setAttribute("title", title);
		setUserInfo(request);
		request.setAttribute("xxOper", xxOper);
		request.setAttribute("jclxList", service.queryJclxList());// �������
		setNjXyZyBjList(request, myForm, false);
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("xsccqk");
	}

	/**
	 * ��ʾ��������Ϣ����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String title = "�������� - ѧ�罨�� - ѧ�������� - �༶����������";

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			request.setAttribute("ccyhlx", "xy");
		} else {
			request.setAttribute("ccyhlx", "xx");
		}

		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("jclxList", service.queryJclxList());
		setNjXyZyBjList(request, myForm);
		request.setAttribute("title", title);
		return mapping.findForward("bjccqkzj");
	}

	/**
	 * �༶��������Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward bjccqkSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		model.setCcyhlx("xy".equalsIgnoreCase(userType) ? "xy" : "xx");
		model.setBjdm(request.getParameter("save_bjdm"));

		boolean result = service.bjccqkSave(model, request);

		xsccqkZj(mapping, form, request, response);
		request.setAttribute("result", result);
		model.setBjmc(DealString.toGBK(model.getBjmc()));
		return mapping.findForward("bjccqkzj");
	}

	/**
	 * ��ʾ�༶��������Ϣ�޸�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward modiBjccqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		// String pk = "xn||xq||bjdm||ccrq||jclxdm||ccyhlx";
		String pkValue = request.getParameter("pk");
		String type = request.getParameter("type");

		if (!Base.isNull(type) && "save".equalsIgnoreCase(type)) {
			request.setAttribute("result", service.bjccqkSave(model, request));// ����������
		}

		request.setAttribute("rs", service.queryBjccqkbByPk(pkValue));
		return mapping.findForward("modiBjccqk");
	}

	/**
	 * ��ʾѧ����������Ϣ�޸�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pkValue = DealString.toGBK(request.getParameter("pk"));
		String title = "�������� - ѧ�罨�� - ѧ�������� - �༶�������޸�";

		request.setAttribute("model", service.queryBjccqkbByPk(pkValue));// �༶�����Ϣ
		request.setAttribute("flag", service.checkOther(pkValue));// ���ѧԺ��ѧУ�Ƿ�¼����ͬһʱ��ļ�¼�Ҹ���Ա�Ѿ�������
		request.setAttribute("stuList", service.queryBjStuList(pkValue));// ��ѯ���༶��ȫ��ѧ��
		request.setAttribute("wjlxList", service.queryWjlxList());
		request.setAttribute("title", title);
		return mapping.findForward("xsccqkxg");
	}

	/**
	 * ��ʾ�༶��������ϸ��Ϣҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward bjccqkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pkValue = DealString.toGBK(request.getParameter("pk"));
		String title = "�������� - ѧ�罨�� - ѧ�������� - �༶��������ϸ��Ϣ";

		request.setAttribute("model", service.queryBjccqkbByPk(pkValue));// �༶�����Ϣ
		request.setAttribute("xsList", service.queryXsccqkbByBj(pkValue));// �༶ѧ�������Ϣ
		request.setAttribute("title", title);
		return mapping.findForward("bjccqkxx");
	}

	/**
	 * ����ѧ���������޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkXgSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		boolean result = service.saveXsccqk(model, request);

		xsccqkXg(mapping, form, request, response);
		request.setAttribute("result", result);
		return mapping.findForward("xsccqkxg");
	}

	/**
	 * ѧ����������Ϣɾ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		String delPk = DealString.toGBK(request.getParameter("delPk"));

		model.setCbv(delPk.split("!!"));
		boolean result = service.deleteXsccqkb(model, request);

		request.setAttribute("result", result);

		xsccqkWh(mapping, form, request, response);
		return mapping.findForward("xsccqk");
	}

	/**
	 * ѧ����������˲�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward ccqksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = (String) session.getAttribute("userDep");

		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		PjpyXfjsForm model = new PjpyXfjsForm();

		String tableName = "view_pjpy_xfjs_bjccqkb";
		String realTable = "pjpy_xfjs_bjccqkb";
		String title = "�������� - ѧ�罨�� - ѧ�����������";

		if ("stu".equalsIgnoreCase(userType)) {
			String msg = "��ҳ��ѧ�����ܷ��ʣ�";
			request.setAttribute("msg", msg);
		}

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		// ==========2012.12.10 edit by luojw begin============
		if (Base.isNull(myForm.getXn())) {
			myForm.setXn(Base.currXn);
		}

		if (Base.isNull(myForm.getXq())) {
			myForm.setXq(Base.currXq);
		}
		// ==========2012.12.10 edit by luojw end============

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xn", "xqmc", "xymc",
					"bjmc", "jclxmc", "ydrs", "sdrs", "qqrs", "ccrq",
					"fdyclsj", "fdysjclsj", "xxsh", "ccyhlx" };// pk =
			// xn||xq||bjdm||ccrq||jclxdm||ccyhlx
			String[] topList = new String[] { "xn", "xqmc", "xymc", "bjmc",
					"jclxmc", "ydrs", "sdrs", "qqrs", "ccrq", "fdyclsj",
					"fdysjclsj", "xxsh", "ccyhlx" };
			User user =  getUser(request);
			topTr = service.getTopTr(tableName, topList);
			rs = service.queryXsccqkb(model, colList,user);
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "pjpy_xfjs_ccqksh.do");
		request.setAttribute("title", title);
		setUserInfo(request);
		request.setAttribute("jclxList", service.queryJclxList());// �������
		setNjXyZyBjList(request, myForm, false);
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("ccqksh");
	}

	/**
	 * �༶�����Ϣ�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward bjccqkshxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = "�������� - ѧ�罨�� - ѧ�������� - �༶���������";
		String pkValue = request.getParameter("pk");
		String type = request.getParameter("type");

		if (!Base.isNull(type) && "save".equalsIgnoreCase(type)) {
			PjpyXfjsForm model = (PjpyXfjsForm) form;
			model.setCbv(new String[] { request.getParameter("pk") });
			request.setAttribute("result", service.ccqkAuditing(model));
		}

		request.setAttribute("model", service.queryBjccqkbByPk(pkValue));// �༶�����Ϣ
		request.setAttribute("xsList", service.queryXsccqkbByBj(pkValue));// �༶ѧ�������Ϣ
		request.setAttribute("title", title);
		return mapping.findForward("bjccqkshxx");
	}

	/**
	 * ѧ����������Ϣ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward ccqkAudi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		String delPk = DealString.toGBK(request.getParameter("delPk"));

		model.setCbv(delPk.split("!!"));
		model.setXxsh(request.getParameter("shjg"));
		boolean result = service.ccqkAuditing(model);

		request.setAttribute("result", result);
		return ccqksh(mapping, form, request, response);
	}

	/**
	 * ѧ�����ڲ�ѯ��ҳ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xskqcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String page = "xskqcx";

		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		PjpyXfjsForm model = new PjpyXfjsForm();

		// ==========2012.12.10 edit by luojw begin============
		if (Base.isNull(myForm.getXn())) {
			myForm.setXn(Base.currXn);
		}

		if (Base.isNull(myForm.getXq())) {
			myForm.setXq(Base.currXq);
		}
		// ==========2012.12.10 edit by luojw end============

		String tableName = "view_pjpy_xfjs_xsjljcb";
		String realTable = "pjpy_xfjs_xsjljcb";
		String title = "�������� - ѧ�罨�� - ѧ�����ڲ�ѯ";

		if ("stu".equalsIgnoreCase(userType)) {
			myForm.setXh(session.getAttribute("userName").toString());
			page = "xskqxxcx";
		}

		myForm
				.setFdy(session.getAttribute("fdyQx").equals(true) ? true
						: false);
		myForm.setUserName(userName);
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		BeanUtils.copyProperties(model, myForm);

		// �����ѯ��ť���в�ѯ
		// if (((request.getParameter("go") != null) &&
		// request.getParameter("go")
		// .equalsIgnoreCase("go"))) {
		String[] colList = new String[] { "pk", "xh", "xm", "xn", "xqmc",
				"xymc", "bjmc", "jclxmc", "wjlxmc", "qjlxmc", "ccrq", "ccyhlx" };
		// pk = xh||xn||xq||bjdm||ccrq||jclxdm||ccyhlx
		String[] topList = new String[] { "xh", "xm", "xn", "xqmc", "xymc",
				"bjmc", "jclxmc", "wjlxmc", "qjlxmc", "ccrq", "ccyhlx" };

		topTr = service.getTopTr(tableName, topList);
		rs = service.queryXskqjljcb(model, colList);

		myForm.setXh(DealString.toGBK(myForm.getXh()));
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		// }

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "pjpy_xfjs_xsccqk.do");
		request.setAttribute("title", title);
		setUserInfo(request);
		request.setAttribute("jclxList", service.queryJclxList());// �������
		request.setAttribute("wjlxList", service.queryWjlxList());// Υ������
		request.setAttribute("qjlxList", service.queryQjlxList());// �������
		setNjXyZyBjList(request, myForm, false);
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward(page);
	}

	/**
	 * ��ʾѧ����������ϸ��Ϣҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pkValue = DealString.toGBK(request.getParameter("pk"));
		String title = "�������� - ѧ�罨�� - ѧ�������� - ѧ����������ϸ��Ϣ";

		request.setAttribute("model", service.queryXsccqkbByPk(pkValue));// �༶ѧ�������Ϣ
		request.setAttribute("title", title);
		return mapping.findForward("xsccqkxx");
	}

	/**
	 * �����༶��������Ϣ
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward expXsccqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		PjpyXfjsService service = new PjpyXfjsService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setNj(request.getParameter("nj"));
		model.setCcrq(request.getParameter("ccrq"));
		model.setJclxdm(request.getParameter("jclxdm"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setSfyr(request.getParameter("sfyr"));
		model.setSfzgdsjcl(request.getParameter("sfzgdsjcl"));
		model.setSfcl(request.getParameter("sfcl"));

		model.setFdy(session.getAttribute("fdyQx").equals(true) ? true : false);
		model.setUserName(userName);

		String[] colList = { "xn", "xq", "xqmc", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "nj", "ccrq", "jclxdm", "jclxmc",
				"ydrs", "qqrs", "sdrs", "wjrs", "fdyclbz", "fdyclsj",
				"fdysjclsj", "xxsh", "ccyhlx", "bz" };
		String[] colListCN = service.getColumnNameCN(colList,
				"view_pjpy_xfjs_bjccqkb");
		List<String[]> rs = service.queryXsccqkForExport(model, colList);// ��ѯ�༶��������Ϣ

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, colList, colListCN, response
				.getOutputStream());
		return mapping.findForward("");
	}

	/**
	 * ����ѧ��������Ϣ
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward expXskqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		PjpyXfjsService service = new PjpyXfjsService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setNj(request.getParameter("nj"));
		model.setCcrq(request.getParameter("ccrq"));
		model.setCcrqks(request.getParameter("ccrqks"));
		model.setCcrqjs(request.getParameter("ccrqjs"));
		model.setJclxdm(request.getParameter("jclxdm"));
		model.setWjlxdm(request.getParameter("wjlxdm"));
		model.setQjlxdm(request.getParameter("qjlxdm"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setXh(request.getParameter("xh"));
		model.setXm(request.getParameter("xm"));

		model.setFdy(session.getAttribute("fdyQx").equals(true) ? true : false);
		model.setUserName(userName);

		String[] colList = { "xn", "xq", "xqmc", "xh", "xm", "xb", "nj",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "ccrq",
				"jclxdm", "jclxmc", "qjlxdm", "qjlxmc", "wjcs", "wjlxdm",
				"wjlxmc", "ccyhlx", "bz" };
		String[] colListCN = service.getColumnNameCN(colList,
				"view_pjpy_xfjs_xsjljcb");
		List<String[]> rs = service.queryXskqxxForExport(model, colList);// ��ѯѧ��������Ϣ

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, colList, colListCN, response
				.getOutputStream());
		return mapping.findForward("");
	}

	/**
	 * ��ʾͳ�Ʋ�ѯѡ��ҳ��
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * */
	public ActionForward showXfjsTjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("showxfjstjcx");
	}

	/**
	 * ������ͳ�Ʋ�ѯ
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward bjccqkSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		String tableName = "view_pjpy_xfjs_bjccqkb";
		String realTable = "pjpy_xfjs_bjccqkb";
		String title = "�������� - ѧ�罨�� - ͳ�Ʋ�ѯ - ������";

		List<HashMap<String, String>> topTr = null;
		List<String[]> rs = null;

		myForm
				.setFdy(session.getAttribute("fdyQx").equals(true) ? true
						: false);
		myForm.setUserName(userName);
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(session.getAttribute("userDep").toString());
		}
		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] colList = new String[] { "pkValue", "qualification",
					"xymc", "zymc", "bjmc", "nj", "wjcs", "cccs" };
			String[] topList = new String[] { "����ֵ", "����", "ѧԺ����", "רҵ����",
					"�༶����", "�꼶", "Υ�ʹ���", "������" };

			topTr = service.getTopTr(tableName, topList);
			rs = service.queryBjccqktjxx(myForm, colList);

			String pk = getBjccqkSearchPk(myForm);
			request.setAttribute("pk", !Base.isNull(pk) ? StringUtils.joinStr(
					pk, "||bjdm") : "bjdm");
		} else {
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
		}
		setNjXyZyBjList(request, myForm, false);
		request.setAttribute("title", title);
		request.setAttribute("jclxList", service.queryJclxList());// �������
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("bjccqkSearch");
	}

	/**
	 * ��ʾ������ͳ�Ʋ�ѯ��ϸ��Ϣ
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward bjccqktjDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = request.getParameter("pk");// �����ֶ�
		String pkValue = request.getParameter("pkValue");// ����ֵ

		request.setAttribute("rs", service.queryBjccqktjDetails(pk, pkValue));// ��ѯ��ϸ��Ϣ
		return mapping.findForward("bjccqktjDetails");
	}

	/**
	 * ��ȡ��ѯ������ֵ
	 * 
	 * @param PjpyXfjsForm
	 *            model
	 * @return String
	 * */
	private String getBjccqkSearchPk(PjpyXfjsForm model) {
		String pk = "";
		StringBuffer qualification = new StringBuffer();
		qualification.append(Base.isNull(model.getXn()) ? "" : "xn||");
		qualification.append(Base.isNull(model.getXq()) ? "" : "xq||");
		qualification.append(Base.isNull(model.getNj()) ? "" : "nj||");
		qualification.append(Base.isNull(model.getXydm()) ? "" : "xydm||");
		qualification.append(Base.isNull(model.getZydm()) ? "" : "zydm||");
		qualification.append(Base.isNull(model.getBjdm()) ? "" : "bjdm||");
		qualification.append(Base.isNull(model.getJclxdm()) ? "" : "jclxdm||");
		qualification.append(Base.isNull(model.getWjlxdm()) ? "" : "wjlxdm||");
		qualification.append(Base.isNull(model.getQjlxdm()) ? "" : "qjlxdm||");
		qualification.append(Base.isNull(model.getXh()) ? "" : "xh||");
		pk = qualification.toString();
		if (!Base.isNull(pk)) {
			pk = pk.substring(0, pk.length() - 2);
		}
		return pk;
	}

	/**
	 * ������ͳ�Ʋ�ѯ����
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward expBjccqktjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		PjpyXfjsService service = new PjpyXfjsService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setNj(request.getParameter("nj"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setXymc(request.getParameter("xymc"));
		model.setZymc(request.getParameter("zymc"));
		model.setBjmc(request.getParameter("bjmc"));
		model.setJclxdm(request.getParameter("jclxdm"));
		model.setJclxmc(request.getParameter("jclxmc"));

		model.setFdy(session.getAttribute("fdyQx").equals(true) ? true : false);
		model.setUserName(userName);

		String[] colList = new String[] { "qualification", "xymc", "zymc",
				"bjmc", "nj", "wjcs", "cccs" };
		String[] colListCN = new String[] { "����", "ѧԺ����", "רҵ����", "�༶����", "�꼶",
				"Υ�ʹ���", "������" };
		List<String[]> rs = service.queryBjccqktjxx(model, colList);// ��ѯ�༶��������Ϣ

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, colList, colListCN, response
				.getOutputStream());
		return mapping.findForward("");
	}

	/**
	 * ѧ������ͳ�Ʋ�ѯ
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward xskqqkSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		String tableName = "view_pjpy_xskqb";
		String realTable = "pjpy_xskqb";
		String title = "�������� - ѧ�罨�� - ͳ�Ʋ�ѯ - ѧ�����ڲ�ѯ";

		List<HashMap<String, String>> topTr = null;
		List<String[]> rs = null;

		myForm
				.setFdy(session.getAttribute("fdyQx").equals(true) ? true
						: false);
		myForm.setUserName(userName);

		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] colList = new String[] { "qualification", "xh", "xm",
					"xymc", "bjmc", "nj", "wjcs", "qjcs", "kkjs" };
			String[] topList = new String[] { "����", "xh", "xm", "xymc", "bjmc",
					"nj", "Υ�ʹ���", "��ٴ���", "���ν���" };

			topTr = service.getTopTr(tableName, topList);
			rs = service.queryXskqqktjxx(myForm, colList);

			String pk = getBjccqkSearchPk(myForm);
			request.setAttribute("pk", !Base.isNull(pk) ? StringUtils.joinStr(
					pk, "||xh") : "xh");
		} else {
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
		}

		setNjXyZyBjList(request, myForm, false);
		request.setAttribute("title", title);
		request.setAttribute("jclxList", service.queryJclxList());// �������
		request.setAttribute("wjlxList", service.queryWjlxList());// Υ������
		request.setAttribute("qjlxList", service.queryQjlxList());// �������
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("xskqqkSearch");
	}

	/**
	 * ��ʾѧ������ͳ�Ʋ�ѯ��ϸ��Ϣ
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward xskqtjDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = request.getParameter("pk");// �����ֶ�
		String pkValue = request.getParameter("pkValue");// ����ֵ

		request.setAttribute("rs", service.queryXskqtjDetails(pk, pkValue));// ��ѯ��ϸ��Ϣ
		return mapping.findForward("xskqtjDetails");
	}

	/**
	 * ѧ������ͳ�Ʋ�ѯ����
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward expXskqtjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		PjpyXfjsService service = new PjpyXfjsService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setNj(request.getParameter("nj"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setJclxdm(request.getParameter("jclxdm"));
		model.setJclxmc(request.getParameter("jclxmc"));
		model.setWjlxdm(request.getParameter("wjlxdm"));
		model.setWjlxmc(request.getParameter("wjlxmc"));
		model.setQjlxdm(request.getParameter("qjlxdm"));
		model.setQjlxmc(request.getParameter("qjlxmc"));
		model.setXh(request.getParameter("xh"));

		model.setFdy(session.getAttribute("fdyQx").equals(true) ? true : false);
		model.setUserName(userName);

		String[] colList = new String[] { "qualification", "xh", "xm", "xymc",
				"bjmc", "nj", "wjcs", "qjcs", "kkjs" };
		String[] colListCN = new String[] { "����", "ѧ��", "����", "ѧԺ", "�༶", "�꼶",
				"Υ�ʹ���", "��ٴ���", "���ν���" };
		List<String[]> rs = service.queryXskqqktjxx(model, colList);// ��ѯ�༶��������Ϣ
		
		//
		List<String[]> rss= new ArrayList<String[]>(); 
		String[] tmp =null;
		for (int i = 0; i < rs.size(); i++) {
			tmp =new String[(rs.get(i).length - 1)];
			for(int j = 0; j < tmp.length ; j++){
				tmp[j]=rs.get(i)[(j+1)];
			}
			rss.add(tmp);
		}
		

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rss, colList, colListCN, response
				.getOutputStream());
		return mapping.findForward("");
	}

	/**
	 * �����û���Ϣ
	 * 
	 * @param HttpServletRequest
	 *            request
	 * */
	public void setUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String userStatus = "";
		String userType = session.getAttribute("userType").toString();
		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			userStatus = "xx";// ѧУ�û�������Ա��
		}
		request.setAttribute("userStatus", userStatus);
		request.setAttribute("userType", session.getAttribute("userType"));
		request.setAttribute("userName", session.getAttribute("userName"));
		request.setAttribute("userDep", session.getAttribute("userDep"));
	}

	public void setNjXyZyBjList(HttpServletRequest request, PjpyXfjsForm myForm)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// ��request�����꼶ѧԺרҵ�༶List�ķ���
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		//
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
		}
	}

	public void setNjXyZyBjList(HttpServletRequest request,
			PjpyXfjsForm myForm, boolean bjFlag)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// ��request�����꼶ѧԺרҵ�༶List�ķ���
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		//
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")
				&& bjFlag) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
		}
	}

	public void commonRequestSet(HttpServletRequest request, String tableName,
			String realTable, List<String[]> rs,
			List<HashMap<String, String>> topTr) {
		// Request��ֵ��ͨ�÷��� ������title�����ݿ���ȡ
		String writeAble = request.getParameter("writeAble");
		if (Base.isNull(writeAble)) {
			String[] message = getWriteAbleAndTitle(request);
			writeAble = message[0];
		}

		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
}
