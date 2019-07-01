package xgxt.pjpy.zjcm;

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
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.guizhdx.GuizhdxService;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZhcpjxjService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

import common.GlobalsVariable;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �㽭��ýѧԺ��������-action��
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

public class ZjcmPjpyAction extends DispatchAction {

	/**
	 * ���������
	 * 
	 * @return ActionForward
	 */
	public ActionForward cpxzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String tableName = "";
		String realTable = "zjcm_cpz";
		myForm.setTableName(realTable);

		// ѧԺ�û���½��ʼ��ѧԺ����
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			request.setAttribute("xydm", userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		// �������С��
		if ("save".equalsIgnoreCase(doType)) {

			String[] zwdm = myForm.getZwdm();

			if (zwdm != null && zwdm.length > 0) {// ����
				result = service.saveCpzInfo(myForm);
			} else {// ����
				result = service.cxCpzInfo(myForm);
			}
			request.setAttribute("result", result);
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "cpxz");

		request.setAttribute("path", "pjpy_cpxz.do");
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("cpxzManage");
	}

	/**
	 * ��������_��������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward tjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();
		

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_pjpy_tjsz";
		String realTable = "zjcm_pjpy_tjsz";
		String lx = request.getParameter("lx");
		lx = service.getPjpyTjszLx(lx);//Base.isNull(lx) ? "jxj" : lx;
		// ѧУ����
		String xxdm = Base.xxdm;
		// �����Ƿ���Ҫѧ��
		boolean needXq = service.getNeedXq(xxdm);
		// ��ѧУ������ѧ�ڣ������ֶ���Ϊ��no��
		if (!needXq) {
			myForm.setXq("no");
		}
		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		//---------2010.10.14 by lr------------
		//�ж��Ƿ����������
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(session.getAttribute("userType").toString()) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_TJSZ)
				&& service.checkKgflag()){
			String msg = "���ù�����ʱ�����Ų��������޲�ѯ��";
			request.setAttribute("yhInfo", msg);
		}
		//---------end2010.10.9 by lr------------

		// ����ɾ������
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||lx||jxjdm||tjzd||bjlx";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delPjpy(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// ������������
		if ("save".equalsIgnoreCase(doType)) {

			String bjlx = Base.isNull(myForm.getBjlx()) ? "nobj" : myForm
					.getBjlx();

			String[] onezd = new String[] { "xn", "xq", "lx", "jxjdm", "tjzd",
					"tjlx", "tjz", "bjlx" };

			String pk = "xn||xq||lx||jxjdm||tjzd||bjlx";
			String pkValue = myForm.getXn() + myForm.getXq() + myForm.getLx()
					+ myForm.getJxjdm() + myForm.getTjzd() + bjlx;

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			if ("��".equalsIgnoreCase(myForm.getTjlx())
					|| "��".equalsIgnoreCase(myForm.getTjlx())) {
				model.setTjz(model.getTjlx());
			}

			model.setBjlx(bjlx);

			result = service.savePjpy(saveForm, model, request);
			request.setAttribute("result", result);
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			// model.setLx(null);
			String[] colList = new String[] { "pk", "tj" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "tjsz");
		if("zhcpjxj".equalsIgnoreCase(lx)){
			//��ȡ�ۺϲ�����ѧ������б���Ϣ
			GuizhdxService gzdxService = new GuizhdxService();
			gzdxService.setList(request, GlobalsVariable.PJPY_ZHCPJXJ);
		}

		request.setAttribute("lx", lx);
		request.setAttribute("tjlx", myForm.getTjlx());
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("needXq", needXq);
		request.setAttribute("path", "pjpy_tjsz.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		request.setAttribute("pages", service.getPjpyTjszCard());
		return mapping.findForward("tjszManage");
	}

	/**
	 * ��������_��ѧ��������ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jdszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "�������� - �������� - �������";
		String doType = request.getParameter("doType");
		String realTable = "zjcm_jdsz";

		boolean result = false;

		doType = Base.isNull(doType) ? "add" : doType;

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		// ��ʼ������ѧ�꣬ѧ��, ���ͣ���ѧ�������ƺţ�
		String xn = Base.getJxjsqxn();// ����ѧ��
		String xq = Base.getJxjsqxq();// ����ѧ��
		String lx = request.getParameter("lx");// ����
		String jxjdm = request.getParameter("jxjdm");// ��ѧ�����
		String rychdm = request.getParameter("rychdm");// �����ƺŴ���

		myForm.setLx(lx);
		myForm.setJxjdm(jxjdm);
		myForm.setRychdm(rychdm);
		myForm.setXn(xn);
		myForm.setXq(xq);
		myForm.setTableName(realTable);

		rs.put("xn", xn);
		rs.put("xq", xq);
		rs.put("lx", lx);
		rs.put("jxjdm", jxjdm);
		rs.put("rychdm", rychdm);

		// ��ý�ѧ���Լ������ƺŵ�����
		List<HashMap<String, String>> rychList = service.getPjpyList("rychdmb",
				"rychdm", "rychmc", "", "", "");// �����ƺ��б�
		List<HashMap<String, String>> jxjList = service.getPjpyList("jxjdmb",
				"jxjdm", "jxjmc", "", "", "");// ��ѧ���б�
		if (rychList != null && rychList.size() > 4) {
			request.setAttribute("rychtd", 4 - rychList.size() % 4);// ���ո���
			request.setAttribute("rychnum", rychList.size() - 1);
		}
		if (jxjList != null && jxjList.size() > 0) {
			if (jxjList.size() % 4 != 0) {
				request.setAttribute("jxjtd", 4 - jxjList.size() % 4);// ���ո���
			}
			request.setAttribute("jxjnum", jxjList.size() - 1);
		}

		BeanUtils.copyProperties(model, myForm);

		// ����������
		if ("save".equalsIgnoreCase(doType)) {

			result = service.saveJdqk(model);

			request.setAttribute("result", result);

		}

		// ��ռ������
		if ("cancel".equalsIgnoreCase(doType)) {

			result = service.cxJdsz(model);

			request.setAttribute("result", result);

		}

		// �鿴�Ѿ�������ϵĽ�ѧ�������ƺţ�������
		if ("view".equalsIgnoreCase(doType) || result) {
			// ȷ�ϱ��Ƚ���ĿΪ��ѧ���������ƺ�
			xn = model.getXn();
			xq = model.getXq();
			lx = model.getLx();
			jxjdm = ("rych".equalsIgnoreCase(lx)) ? model.getRychdm() : model
					.getJxjdm();

			model.setJxjdm(jxjdm);

			List<HashMap<String, String>> rsList = service.getJxjjdList(model);
			if (rsList != null && rsList.size() > 0) {
				rs = rsList.get(0);
				if ("rych".equalsIgnoreCase(lx)) {
					rs.put("rychdm", rs.get("jxjdm"));
				}
				request.setAttribute("rsList", rsList);
				request.setAttribute("jdnum", rsList.size());
			}
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "jdsz");

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("lx", lx);

		return mapping.findForward("jdszManage");
	}

	/**
	 * ��������_�����ֹ���
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zyfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_zyf";
		String realTable = "zjcm_xfjdb";
		String xn = Base.getJxjsqxn();// ����ѧ��
		String xq = Base.getJxjsqxq();// ����ѧ��

		// ��ʼ���û�Ȩ��
		String fdyQx = session.getAttribute("fdyQx").toString();
		String bzrQx = session.getAttribute("bzrQx").toString();
		String userName = session.getAttribute("userName").toString();
		myForm.setFdyQx(fdyQx);
		myForm.setBzrQx(bzrQx);
		myForm.setUserName(userName);

		// ��ʼ������ѧ��ѧ��
		myForm.setXn(xn);
		myForm.setXq(xq);

		// ��ʼ��ѧԺ(������ΪѧԺ)
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// ��������ѧ��ѧ�ڵ�������
		if ("js".equalsIgnoreCase(doType)) {
			String message = service.jsXfJd(model);
			result = "����ɹ�".equalsIgnoreCase(message) ? true : false;
			request.setAttribute("message", message);
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "xn", "xqmc", "xh", "xm", "nj",
					"xymc", "zymc", "bjmc", "pycc", "xfjd", "zyf" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "zyf");

		request.setAttribute("path", "pjpy_zyf.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("zyfManage");
	}

	/**
	 * ��������_�ۺϷֹ���
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zhfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_zhf";
		String realTable = "zjcm_zhf";
		String xn = Base.getJxjsqxn();// ����ѧ��
		String xq = Base.getJxjsqxq();// ����ѧ��

		// ��ʼ���û�Ȩ��
		String fdyQx = session.getAttribute("fdyQx").toString();
		String bzrQx = session.getAttribute("bzrQx").toString();
		String userName = session.getAttribute("userName").toString();
		myForm.setFdyQx(fdyQx);
		myForm.setBzrQx(bzrQx);
		myForm.setUserName(userName);

		// ��ʼ������ѧ��ѧ��
		myForm.setXn(xn);
		myForm.setXq(xq);

		// ��ʼ��ѧԺ(������ΪѧԺ)
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		BeanUtils.copyProperties(model, myForm);

		// ======================��½��Ȩ�޿���======================================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		// ѧ���û�
		if ("stu".equalsIgnoreCase(userType)) {
			if (service.isCpz(model)) {// �ж��Ƿ����С��

				// ��ʼ�������������Ϣ
				String pk = "xh";
				String pkValue = userName;
				String[] colList = new String[] { "nj", "xydm", "zydm", "bjdm" };

				HashMap<String, String> map = service.getPjpyInfo(
						"view_xsjbxx", pk, pkValue, colList);

				myForm.setLx("jxj");
				myForm.setNj(map.get("nj"));
				myForm.setXydm(map.get("xydm"));
				myForm.setZydm(map.get("zydm"));
				myForm.setBjdm(map.get("bjdm"));

			} else {
				String yhInfo = "�ۺϷֲ���ֻ���ɲ���С����в�������ȷ�ϣ�";
				request.setAttribute("yhInfo", yhInfo);
				return new ActionForward("/yhInfo.do", false);
			}
		}
		// ======================end=====================================

		// ======================���ؿ���======================================
		if (!"xx".equalsIgnoreCase(userType)
				&& !"admin".equalsIgnoreCase(userType)) {
			// �ж��Ƿ��������ۺϷ�¼�뿪��
			String pk = "xn||xq||cpxy";
			String pkValue = xn + xq + userDep;
			String zhfkg = service
					.getOneValue("zjcm_cpz", "zhfkg", pk, pkValue);
			if (!"��".equalsIgnoreCase(zhfkg)) {
				String yhInfo = "�ۺϷ�¼�뿪�عرջ���δά������ȷ�ϣ�";
				request.setAttribute("yhInfo", yhInfo);
				return new ActionForward("/yhInfo.do", false);
			}
		}
		// ======================end=====================================

		// ��ʼ������ֵ����,�Լ���ʾ
		String[] bl = new String[] { "dyfbl", "zyfbl", "tyfbl", "nlfbl" };
		HashMap<String, String> map = service.getZhfBl(model, bl);

		StringBuffer ts = new StringBuffer();
		if (map != null && map.size() > 0) {
			ts.append("ע��");
			ts.append("�����ֱ�����" + map.get("dyfbl") + "%��");
			ts.append("�����ֱ�����" + map.get("zyfbl") + "%��");
			ts.append("�����ֱ�����" + map.get("tyfbl") + "%��");
			ts.append("�����ֱ�����" + map.get("nlfbl") + "%��");
		} else {
			ts.append("����ֵ������δ���á�");
			request.setAttribute("nobl", "yes");
		}

		request.setAttribute("dyfbl", map.get("dyfbl"));
		request.setAttribute("zyfbl", map.get("zyfbl"));
		request.setAttribute("tyfbl", map.get("tyfbl"));
		request.setAttribute("nlfbl", map.get("nlfbl"));
		request.setAttribute("ts", ts.toString());

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// ������������֣������֣�������
		if ("save".equalsIgnoreCase(doType)) {

			String[] arrzd = new String[] { "pjxh", "dyf", "tyf", "nlf" };
			String[] onezd = new String[] { "xn", "xq" };
			String pk = "xn||xq||pjxh";
			String[] pjxh = myForm.getPjxh();
			String[] pkValue = new String[pjxh.length];

			for (int i = 0; i < pjxh.length; i++) {
				pkValue[i] = xn + xq + pjxh[i];
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			result = service.savePjpy(saveForm, model);

			request.setAttribute("result", result);
		}

		// ��������ѧ��ѧ�ڵ��ۺϷ�
		if ("js".equalsIgnoreCase(doType)) {
			result = service.jsZhf(model);
			request.setAttribute("result", result);
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			topTr = service.getTopTr("zhf");
			rs = service.getZhfList(model);
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "zhf");

		request.setAttribute("path", "pjpy_zhf.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("zhfManage");
	}

	/**
	 * ��������_�ۺϷ�ά��(����)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zhfUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "�������� - �ۺϷ� - ��������";
		String doType = request.getParameter("doType");
		String xn = Base.getJxjsqxn();// ����ѧ��
		String xq = Base.getJxjsqxq();// ����ѧ��
		String pk = "xn||xq";
		String pkValue = xn + xq;
		String realTable = "zjcm_zhf_bl";
		boolean result = false;

		doType = Base.isNull(doType) ? "add" : doType;

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// �����������
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "dyfbl", "zyfbl",
					"tyfbl", "nlfbl" };
			pk = "xn||xq";
			pkValue = myForm.getXn() + myForm.getXq();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			result = service.savePjpy(saveForm, model, request);
			request.setAttribute("result", result);
		}

		if ("add".equalsIgnoreCase(doType) || result) {
			String[] colList = new String[] { "xn", "xq", "dyfbl", "zyfbl",
					"tyfbl", "nlfbl" };
			rs = service.getPjpyInfo(realTable, pk, pkValue, colList);
			rs.put("xn", xn);
			rs.put("xq", xq);
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "zhf");

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("zhfUpdate");
	}

	/**
	 * ��������_����ͳ��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bbtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String title = "�������� - ͳ�Ʒ��� - ����ͳ��";
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String lx = myForm.getLx();// ��������

		// ��ʼ�����ݣ���ѧ�꣬ѧ��
		String xn = Base.isNull(myForm.getXn()) ? Base.getJxjsqxn() : myForm
				.getXn();// ����ѧ��
		String xq = Base.isNull(myForm.getXq()) ? Base.getJxjsqxq() : myForm
				.getXq();// ����ѧ��
		myForm.setXn(xn);
		myForm.setXq(xq);

		// /ѧԺ�û�����
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "bbtj");

		BeanUtils.copyProperties(model, myForm);

		// ��ʼ������ֵ����,�Լ���ʾ
		String[] bl = new String[] { "dyfbl", "zyfbl", "tyfbl", "nlfbl" };
		HashMap<String, String> map = service.getZhfBl(model, bl);

		StringBuffer msg = new StringBuffer();
		if (map != null && map.size() > 0) {
			// ts.append("ע��");
			// ts.append("�����ֱ�����" + map.get("dyfbl") + "%��");
			// ts.append("�����ֱ�����" + map.get("zyfbl") + "%��");
			// ts.append("�����ֱ�����" + map.get("tyfbl") + "%��");
			// ts.append("�����ֱ�����" + map.get("nlfbl") + "%��");
		} else {
			msg.append("�ۺϷָ���ֵ������δ���á�");
			request.setAttribute("msg", msg);
		}

		if (!Base.isNull(doType) && "print".equalsIgnoreCase(doType)) {

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			if ("zhbnoyj".equalsIgnoreCase(lx) || "zhbyj".equalsIgnoreCase(lx)) {

				service.printZhszcpb(model, response.getOutputStream());

			} else if ("zhblxy".equalsIgnoreCase(lx)) {// ѧԺ�ۺϲ���������

				service.printZhszblb(model, response.getOutputStream());

			} else if ("jxjjetj".equalsIgnoreCase(lx)) {// ��ѧ����ͳ�Ʊ�

				service.printJxjjetjb(model, response.getOutputStream());

			} else if ("jxjhj".equalsIgnoreCase(lx)) {// ��ѧ���������ӡ

				if ("xy".equalsIgnoreCase(userType)) {
					model.setXydm(userDep);
				}
				service.printJxjhjmd(model, response.getOutputStream());

			} else if ("rychhj".equalsIgnoreCase(lx)) {// �����ƺŻ�������ӡ

				if ("xy".equalsIgnoreCase(userType)) {
					model.setXydm(userDep);
				}

				service.printRychhjmd(model, response.getOutputStream());

			} else if ("jxjjehz".equalsIgnoreCase(lx)) {// ��ѧ�������

				service.exportJxjjeHzData(model, response.getOutputStream());
			}
			return null;
		}

		request.setAttribute("title", title);
		request.setAttribute("yhlxList", service.queryYhlxList());
		request.setAttribute("userType", userType);

		return mapping.findForward("bbtjManage");
	}

	/**
	 * ��������_����ƹ���
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wlkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_wlk";
		String realTable = "zjcm_wlkb";

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "nj", "xymc", "zymc", "bjmc",
					"lxmc" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "wlk");

		request.setAttribute("path", "zjcm_pjpy_wlk.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("wlkManage");
	}

	/**
	 * ��������_�����ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wlkUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "";
		String realTable = "zjcm_wlkb";
		String fs = request.getParameter("fs");

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// ���������
		if ("save".equalsIgnoreCase(doType)) {

			if (!"bj".equalsIgnoreCase(fs)) {
				result = service.saveWlk(model);
			} else {
				String[] arrzd = new String[] { "wlkbjdm" };
				String[] onezd = new String[] { "lx" };
				String pk = "wlkbjdm";
				String[] pkValue = myForm.getWlkbjdm();

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);

				result = service.savePjpy(saveForm, model);

				request.setAttribute("result", result);

			}
			request.setAttribute("result", result);
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "wlk");

		request.setAttribute("path", "zjcm_pjpy_wlk.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("wlkUpdate");
	}

	/**
	 * ��������_��ѧ��(У��)�걨
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jxjsqXnManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "";
		String realTable = "zjcm_jxjsq";
		String userName = session.getAttribute("userName").toString();

		// ��ѧ�����
		String jxjdm = myForm.getJxjdm();

		// ��ʼ������ѧ��ѧ��
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		myForm.setXn(xn);
		myForm.setXq(xq);

		// �жϷ������Ƿ�������û�
		model.setUserName(userName);
		model.setXn(xn);
		model.setXq(xq);

		if (!service.isCpz(model)) {
			String msg = "��ģ��ֻ���ɲ���С����ʣ���ȷ�ϣ�";
			request.setAttribute("msg", msg);
		} else {

			// ��ʼ�������������Ϣ
			String pk = "xh";
			String pkValue = userName;
			String[] colList = new String[] { "nj", "xydm", "zydm", "bjdm" };

			HashMap<String, String> map = service.getPjpyInfo("view_xsjbxx",
					pk, pkValue, colList);

			myForm.setLx("jxj");
			myForm.setNj(map.get("nj"));
			myForm.setXydm(map.get("xydm"));
			myForm.setZydm(map.get("zydm"));
			myForm.setBjdm(map.get("bjdm"));

			// �ж��Ƿ��������ۺϷ�¼�뿪��
			pk = "xn||xq||cpxy";
			pkValue = xn + xq + myForm.getXydm();
			String jxjkg = service
					.getOneValue("zjcm_cpz", "jxjkg", pk, pkValue);
			if ("��".equalsIgnoreCase(jxjkg)) {
				String msg = "��ѧ���걨���عرգ���ȷ�ϣ�";
				request.setAttribute("msg", msg);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// �걨��ѧ��
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String[] arrzd = new String[] { "pjxh" };
			String[] onezd = new String[] { "xn", "xq", "jxjdm" };
			String pk = "xn||xq||pjxh||jxjdm";
			String[] pjxh = myForm.getCheckVal();
			String[] pkValue = new String[pjxh.length];

			for (int i = 0; i < pjxh.length; i++) {
				pkValue[i] = xn + xq + pjxh[i] + jxjdm;
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			model.setPjxh(pjxh);

			result = service.savePjpy(saveForm, model);

			request.setAttribute("result", result);
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			model.setPjxh(null);
			topTr = service.getTopTr("jxjxn");
			rs = service.getJxjSqXnList(model);
			// ���ʸ��걨��ѧ��
			String[] sbzxh = service.getJxjsbz(rs);
			if (sbzxh != null && sbzxh.length > 0) {
				request.setAttribute("sbzxh", sbzxh);
				request.setAttribute("sbznum", sbzxh.length);
			}
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "jxjxn");

		request.setAttribute("path", "pjpy_jxjsq_xn.do");
		request.setAttribute("searchJxjdm", jxjdm);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("jxjsqXnManage");
	}

	/**
	 * ��������_��ѧ��(У��)�걨(����)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jxjsqXnUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "�������� - У�ڽ�ѧ�� - �걨";
		String doType = request.getParameter("doType");
		String xn = Base.getJxjsqxn();// ����ѧ��
		String xq = Base.getJxjsqxq();// ����ѧ��
		String pkValue = request.getParameter("pk");// ����
		String jxjdm = request.getParameter("jxjdm");// ��ѧ�����
		String sel = request.getParameter("sel");// �Ƿ�ѡ
		String sbznum = request.getParameter("sbznum");// ���ʸ��걨�ý�ѧ�������
		String lx = request.getParameter("lx");// ��һ������һ����
		String nextXh = "";// ��ѧ��
		String swbj = "";// ��β���

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		// ��������������Ϣ
		String xh = Base.isNull(pkValue) ? myForm.getXh() : pkValue;
		jxjdm = Base.isNull(jxjdm) ? myForm.getJxjdm() : jxjdm;

		// ���ʸ��걨��
		String[] sbzxh = null;
		if (!Base.isNull(sbznum)) {
			sbzxh = new String[Integer.parseInt(sbznum)];
			for (int i = 0; i < Integer.parseInt(sbznum); i++) {
				sbzxh[i] = request.getParameter("sbzxh" + String.valueOf(i));
			}
			if (sbzxh != null && sbzxh.length > 0) {
				request.setAttribute("sbzxh", sbzxh);
				request.setAttribute("sbznum", sbzxh.length);
			}
		}

		// ѧ��˳��
		int xhnum = Base.isNull(request.getParameter("xhnum")) ? 0 : Integer
				.parseInt(request.getParameter("xhnum"));

		if (sbzxh != null && sbzxh.length > 0) {

			// ȷ�����򿪵�ѧ���������ʸ�ѧ���е�˳���Լ����Ƿ���β
			for (int i = 0; i < sbzxh.length; i++) {
				if (sbzxh[i].equalsIgnoreCase(xh)) {
					xhnum = i;
					if (i == 0) {
						swbj = "theFirst";
					} else if (i == sbzxh.length - 1) {
						swbj = "theLast";
					} else {
						swbj = "";
					}
					break;
				}
			}

			// �ж��ǰ�����һ��������һ��
			if ("before".equalsIgnoreCase(lx)) {
				xhnum = xhnum - 1;
				nextXh = sbzxh[xhnum];
			} else if ("next".equalsIgnoreCase(lx)) {
				xhnum = xhnum + 1;
				nextXh = sbzxh[xhnum];
			}

			// �ж���һ������һ�����������ʸ�ѧ���е�˳���Լ����Ƿ���β
			for (int i = 0; i < sbzxh.length; i++) {
				if (sbzxh[i].equalsIgnoreCase(nextXh)) {
					if (i == 0) {
						swbj = "theFirst";
					} else if (i == sbzxh.length - 1) {
						swbj = "theLast";
					} else {
						swbj = "";
					}
					break;
				}
			}
		}

		// ��ô�չ��ѧ��
		xh = Base.isNull(nextXh) ? xh : nextXh;

		model.setXh(xh);
		model.setXn(xn);
		model.setXq(xq);
		model.setJxjdm(jxjdm);

		// ��ô�չ���������Ϣ
		HashMap<String, String> rs = service.getJxjsqInfo(model);

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "jxjxn");

		request.setAttribute("xhnum", xhnum);
		request.setAttribute("swbj", swbj);
		request.setAttribute("sel", sel);
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("jxjsqXnUpdate");
	}

	/**
	 * ��������_��ѧ��(У��)�걨
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jxjsqXwManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_jxjsq";
		String realTable = "zjcm_jxjsq";
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		// ��ʼ������ѧ��ѧ��
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		myForm.setXn(xn);
		myForm.setXq(xq);

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// �걨��ѧ��
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String xwjxjdm = myForm.getXwjxjdm();// ���걨��ѧ�����
			String[] checkVal = myForm.getCheckVal();// ��ѡ��

			if (checkVal != null && checkVal.length > 0) {

				String[] arrzd = new String[] { "pjxh" };
				String[] onezd = new String[] { "xn", "xq", "jxjdm" };
				String pk = "pjxh||xn||xq||jxjdm";
				String[] pkValue_Temp = new String[checkVal.length];
				String[] pjxh_Temp = new String[checkVal.length];// �걨��ѧ��

				// ��������
				int n = 0;
				for (int i = 0; i < checkVal.length; i++) {
					boolean flag = true;
					for (int j = i + 1; j < checkVal.length; j++) {
						if (checkVal[i].equalsIgnoreCase(checkVal[j])) {
							if (flag) {
								pjxh_Temp[n] = checkVal[i];
								pkValue_Temp[n] = checkVal[i] + xn + xq
										+ xwjxjdm;
								n++;
								flag = false;
								checkVal[j] = "";
							}
						}
					}
					if (flag && !Base.isNull(checkVal[i])) {
						pjxh_Temp[n] = checkVal[i];
						pkValue_Temp[n] = checkVal[i] + xn + xq + xwjxjdm;
						n++;
					}
				}

				String[] pkValue = new String[n];
				String[] pjxh = new String[n];// �걨��ѧ��

				for (int i = 0; i < n; i++) {
					pjxh[i] = pjxh_Temp[i];
					pkValue[i] = pkValue_Temp[i];
				}

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);

				// ���ñ������ݵ�ֵ
				model.setPjxh(pjxh);
				model.setXn(xn);
				model.setXq(xq);
				model.setJxjdm(xwjxjdm);

				// ִ�б������
				result = service.savePjpy(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {

			model.setXxsh("ͨ��");

			String[] colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
					"nj", "xymc", "zymc", "bjmc", "pycc", "bjlx", "yycj",
					"jxjmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "jxjxn");

		request.setAttribute("path", "pjpy_jxjsq_xw.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("jxjsqXwManage");
	}

	/**
	 * ��������_��ѧ��(У��)�걨(����)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jxjsqXwUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "�������� - У�⽱ѧ�� - �걨";
		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_jxjsq";
		String realTable = "zjcm_jxjsq";
		String pk = "pjxh||xn||xq||jxjdm";// ����
		String pkValue = request.getParameter("pk");// ����ֵ

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		HashMap<String, String> rs = new HashMap<String, String>();
		HashMap<String, String> otherRs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// �����걨
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveJxjsb(model, request);
			request.setAttribute("result", result);
		}

		// �걨����ϸ��Ϣ
		if ("sb".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xn", "xq", "xh", "xm", "xb",
					"nj", "xymc", "zymc", "bjmc", "jxjmc", "fdyyj", "xyyj",
					"xxyj", "bz", "yycj" };
			rs = service.getPjpyInfo(tableName, pk, pkValue, colList);

			model.setXh(rs.get("xh"));
			model.setXn(rs.get("xn"));
			model.setXq(rs.get("xq"));
			// ��ô�չ���������Ϣ
			otherRs = service.getJxjsqInfo(model);
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "jxjxw");

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("otherRs", otherRs);

		return mapping.findForward("jxjsqXwUpdate");
	}

	/**
	 * ��������_���ҽ�ѧ���걨
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward gjjxjSbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_jxjsq";
		String realTable = "zjcm_jxjsq";
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		// ��ʼ������ѧ��ѧ��
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		myForm.setXn(xn);
		myForm.setXq(xq);

		// ��ù��ҽ�ѧ�����
		String jxjdm = service.getOneValue("jxjdmb", "jxjdm", "jxjmc", "���ҽ�ѧ��");

		// �жϹ��ҽ�ѧ������Ƿ�ά��
		if (Base.isNull(jxjdm)) {
			String msg = "���ҽ�ѧ�����δά������ȷ�ϣ�";
			request.setAttribute("msg", msg);
		}

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {

			model.setXxsh("ͨ��");

			String[] colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
					"nj", "xymc", "zymc", "bjmc", "jxjmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "jxjxn");

		request.setAttribute("path", "zjcm_pjpy_gjjxjsb.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("gjjxjSbManage");
	}

	/**
	 * ��������_���ҽ�ѧ���걨��������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward gjjxjSbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "�������� - ������ѧ�� - �걨";
		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_jxjsq";
		String realTable = "zjcm_jxjsq";
		String pk = "pjxh||xn||xq||jxjdm";// ����
		String pkValue = request.getParameter("pk");// ����ֵ
		String xh = request.getParameter("xh");// ѧ��
		String xn = Base.getJxjsqxn();// ����ѧ��
		String xq = Base.getJxjsqxq();// ����ѧ��

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		BeanUtils.copyProperties(model, myForm);

		// �����걨
		if ("save".equalsIgnoreCase(doType)) {
			// ��ù��ҽ�ѧ�����
			String jxjdm = service.getOneValue("jxjdmb", "jxjdm", "jxjmc",
					"���ҽ�ѧ��");
			model.setJxjdm(jxjdm);
			boolean result = service.saveJxjsb(model, request);

			request.setAttribute("result", result);
		}

		// ѧ��������Ϣ
		xh = Base.isNull(xh) ? service.getOneValue(realTable, "pjxh", pk,
				pkValue) : xh;

		HashMap<String, String> stuInfo = service.getStuInfo(xh);
		stuInfo.put("xn", xn);
		stuInfo.put("xq", xq);

		// ��ô�չ���������Ϣ
		model.setXh(xh);
		model.setXn(xn);
		model.setXq(xq);
		model.setNj(stuInfo.get("nj"));
		model.setXydm(stuInfo.get("xydm"));
		model.setZydm(stuInfo.get("zydm"));
		model.setBjdm(stuInfo.get("bjdm"));

		HashMap<String, String> otherInfo = service.getGjjxjNeedInfo(model);

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "");

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("stuInfo", stuInfo);
		request.setAttribute("otherInfo", otherInfo);

		return mapping.findForward("gjjxjSbUpdate");
	}

	/**
	 * ��������_���ҽ�ѧ���ӡ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String xh = myForm.getXh();
		String xn = Base.getJxjsqxn();// ����ѧ��
		String xq = Base.getJxjsqxq();// ����ѧ��

		HashMap<String, String> stuInfo = service.getStuInfo(xh);

		stuInfo.put("xxmc", StandardOperation.getXxmc());
		stuInfo.put("xn", xn);
		stuInfo.put("xq", xq);

		// ������֤��
		String sfzh = Base.chgNull(stuInfo.get("sfzh"), "", 1);
		String[] sF = new String[18];

		for (int i = 0; i < sfzh.length(); i++) {
			sF[i] = sfzh.substring(i, i + 1);
		}
		for (int i = 1; i < 19; i++) {
			stuInfo.put("sfzh" + i, sF[i - 1]);
		}

		// ��ô�չ���������Ϣ
		model.setXh(xh);
		model.setXn(xn);
		model.setXq(xq);
		model.setNj(stuInfo.get("nj"));
		model.setXydm(stuInfo.get("xydm"));
		model.setZydm(stuInfo.get("zydm"));
		model.setBjdm(stuInfo.get("bjdm"));

		HashMap<String, String> otherInfo = service.getGjjxjNeedInfo(model);

		request.setAttribute("rs", stuInfo);
		request.setAttribute("otherInfo", otherInfo);

		return mapping.findForward("gjjxjPrint");
	}

	/**
	 * ��������_�����ƺ��걨
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward rychsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "";
		String realTable = "zjcm_rychsqb";
		String userName = session.getAttribute("userName").toString();

		// ��ʼ������ѧ��ѧ��
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		myForm.setXn(xn);
		myForm.setXq(xq);

		// �жϷ������Ƿ�������û�
		model.setUserName(userName);
		model.setXn(xn);
		model.setXq(xq);

		if (!service.isCpz(model)) {
			String msg = "��ģ��ֻ���ɲ���С����ʣ���ȷ�ϣ�";
			request.setAttribute("msg", msg);
		} else {

			// ��ʼ�������������Ϣ
			String pk = "xh";
			String pkValue = userName;
			String[] colList = new String[] { "nj", "xydm", "zydm", "bjdm" };

			HashMap<String, String> map = service.getPjpyInfo("view_xsjbxx",
					pk, pkValue, colList);

			myForm.setLx("rych");
			myForm.setNj(map.get("nj"));
			myForm.setXydm(map.get("xydm"));
			myForm.setZydm(map.get("zydm"));
			myForm.setBjdm(map.get("bjdm"));
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// �걨�����ƺ�
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String[] arrzd = new String[] { "pjxh" };
			String[] onezd = new String[] { "xn", "xq", "rychdm" };
			String pk = "xn||xq||pjxh||rychdm";
			String[] pjxh = myForm.getCheckVal();
			String[] pkValue = new String[pjxh.length];
			String rychdm = myForm.getRychdm();

			for (int i = 0; i < pjxh.length; i++) {
				pkValue[i] = xn + xq + pjxh[i] + rychdm;
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			model.setPjxh(pjxh);

			result = service.savePjpy(saveForm, model);

			request.setAttribute("result", result);
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			model.setPjxh(null);
			topTr = service.getTopTr("jxjxn");
			rs = service.getJxjSqXnList(model);
			// ���ʸ��걨��ѧ��
			String[] sbzxh = service.getJxjsbz(rs);
			if (sbzxh != null && sbzxh.length > 0) {
				request.setAttribute("sbzxh", sbzxh);
				request.setAttribute("sbznum", sbzxh.length);
			}
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "rych");

		request.setAttribute("path", "pjpy_rychsq.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("rychsqManage");
	}

	/**
	 * ��������_�����ƺ��걨(����)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward rychsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "�������� - �����ƺ� - �걨";
		String doType = request.getParameter("doType");
		String xn = Base.getJxjsqxn();// ����ѧ��
		String xq = Base.getJxjsqxq();// ����ѧ��
		String pkValue = request.getParameter("pk");// ����
		String rychdm = request.getParameter("rychdm");// �����ƺŴ���
		String sel = request.getParameter("sel");// �Ƿ�ѡ
		String sbznum = request.getParameter("sbznum");// ���ʸ��걨�ý�ѧ�������
		String lx = request.getParameter("lx");// ��һ������һ����
		String nextXh = "";// ��ѧ��
		String swbj = "";// ��β���

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		// ��������������Ϣ
		String xh = Base.isNull(pkValue) ? myForm.getXh() : pkValue;
		rychdm = Base.isNull(rychdm) ? myForm.getRychdm() : rychdm;

		// ���ʸ��걨��
		String[] sbzxh = null;
		if (!Base.isNull(sbznum)) {
			sbzxh = new String[Integer.parseInt(sbznum)];
			for (int i = 0; i < Integer.parseInt(sbznum); i++) {
				sbzxh[i] = request.getParameter("sbzxh" + String.valueOf(i));
			}
			if (sbzxh != null && sbzxh.length > 0) {
				request.setAttribute("sbzxh", sbzxh);
				request.setAttribute("sbznum", sbzxh.length);
			}
		}

		// ѧ��˳��
		int xhnum = Base.isNull(request.getParameter("xhnum")) ? 0 : Integer
				.parseInt(request.getParameter("xhnum"));

		if (sbzxh != null && sbzxh.length > 0) {

			// ȷ�����򿪵�ѧ���������ʸ�ѧ���е�˳���Լ����Ƿ���β
			for (int i = 0; i < sbzxh.length; i++) {
				if (sbzxh[i].equalsIgnoreCase(xh)) {
					xhnum = i;
					if (i == 0) {
						swbj = "theFirst";
					} else if (i == sbzxh.length - 1) {
						swbj = "theLast";
					} else {
						swbj = "";
					}
					break;
				}
			}

			// �ж��ǰ�����һ��������һ��
			if ("before".equalsIgnoreCase(lx)) {
				xhnum = xhnum - 1;
				nextXh = sbzxh[xhnum];
			} else if ("next".equalsIgnoreCase(lx)) {
				xhnum = xhnum + 1;
				nextXh = sbzxh[xhnum];
			}

			// �ж���һ������һ�����������ʸ�ѧ���е�˳���Լ����Ƿ���β
			for (int i = 0; i < sbzxh.length; i++) {
				if (sbzxh[i].equalsIgnoreCase(nextXh)) {
					if (i == 0) {
						swbj = "theFirst";
					} else if (i == sbzxh.length - 1) {
						swbj = "theLast";
					} else {
						swbj = "";
					}
					break;
				}
			}
		}

		// ��ô�չ��ѧ��
		xh = Base.isNull(nextXh) ? xh : nextXh;

		model.setXh(xh);
		model.setXn(xn);
		model.setXq(xq);
		model.setRychdm(rychdm);

		// ��ô�չ���������Ϣ
		HashMap<String, String> rs = service.getJxjsqInfo(model);

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "rych");

		request.setAttribute("xhnum", xhnum);
		request.setAttribute("swbj", swbj);
		request.setAttribute("sel", sel);
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("rychsqUpdate");
	}

	/**
	 * ��������_��ѧ��������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jxjjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTyForm myForm = (PjpyTyForm) form;
		myForm.setLx("jxj");
		return sbjgManage(mapping, myForm, request, response);
	}

	/**
	 * ��������_�����ƺ�������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward rychjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTyForm myForm = (PjpyTyForm) form;
		myForm.setLx("rych");
		return sbjgManage(mapping, myForm, request, response);
	}

	/**
	 * ��������_�걨���
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sbjgManage(ActionMapping mapping, PjpyTyForm myForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		// �жϽ�ѧ���������ƺ�
		String lx = request.getParameter("lx");
		lx = Base.isNull(lx) ? myForm.getLx() : lx;
		String path = "jxj".equalsIgnoreCase(lx) ? "pjpy_jxjjg.do"
				: "pjpy_rychjg.do";
		String realTable = "jxj".equalsIgnoreCase(lx) ? "zjcm_jxjsq"
				: "zjcm_rychsqb";
		String tableName = "jxj".equalsIgnoreCase(lx) ? "view_zjcm_jxjsq"
				: "view_zjcm_rychsq";
		// ��ʼ���û���Ϣ
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String fdyQx = session.getAttribute("fdyQx").toString();
		String bzrQx = session.getAttribute("bzrQx").toString();

		if ("xy".equalsIgnoreCase(userType)) {// ѧԺ�û�����
			myForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {// ѧ���û�����
			String pk = "xh";
			String pkValue = userName;
			String[] colList = new String[] { "nj", "xydm", "zydm", "bjdm" };

			HashMap<String, String> map = service.getPjpyInfo("view_xsjbxx",
					pk, pkValue, colList);

			myForm.setNj(map.get("nj"));
			myForm.setXydm(map.get("xydm"));
			myForm.setZydm(map.get("zydm"));
			myForm.setBjdm(map.get("bjdm"));
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// ɾ����ѧ��(�����ƺ�)
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {

			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "jxj".equalsIgnoreCase(lx) ? "pjxh||xn||xq||jxjdm"
						: "pjxh||xn||xq||rychdm";

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
			model.setLx(null);
			String[] colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
					"xymc", "zymc", "bjmc", "jxjmc", "fdysh", "xysh", "xxsh" };
			if ("rych".equalsIgnoreCase(lx)) {
				colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
						"xymc", "zymc", "bjmc", "rychmc", "xysh", "xxsh" };
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "sbjg");

		request.setAttribute("path", path);
		request.setAttribute("lx", lx);
		request.setAttribute("userType", userType);
		request.setAttribute("fdyQx", fdyQx);
		request.setAttribute("bzrQx", bzrQx);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("sbjgManage");
	}

	/**
	 * ��������_��ѧ��(У��)�걨(����)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sbjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String pkValue = request.getParameter("pk");// ����
		String lx = request.getParameter("doType");// ��ѧ��,�����ƺ�
		String tableName = ("jxj".equalsIgnoreCase(lx)) ? "view_zjcm_jxjsq"
				: "view_zjcm_rychsq";
		String title = ("jxj".equalsIgnoreCase(lx)) ? "�������� - ��ѧ�� - �걨���"
				: "�������� - �����ƺ� - �걨���";

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String[] colList = ("jxj".equalsIgnoreCase(lx)) ? new String[] { "xh",
				"xn", "xq", "jxjdm", "fdyyj", "xyyj", "xxyj", "bz" }
				: new String[] { "xh", "xn", "xq", "rychdm", "fdyyj", "xyyj",
						"xxyj", "bz" };

		HashMap<String, String> stuInfo = service.getPjpyInfo(tableName, "pk",
				pkValue, colList);

		model.setXh(stuInfo.get("xh"));
		model.setXn(stuInfo.get("xn"));
		model.setXq(stuInfo.get("xq"));
		model.setJxjdm(stuInfo.get("jxjdm"));
		model.setRychdm(stuInfo.get("rychdm"));

		// ��ô�չ���������Ϣ
		HashMap<String, String> rs = service.getJxjsqInfo(model);

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "sbjg");

		request.setAttribute("lx", lx);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("stuInfo", stuInfo);

		return mapping.findForward("sbjgUpdate");
	}
}
