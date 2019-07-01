package xgxt.xsgygl.bjlh.sjwh;

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
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.SearchUtils;
import xgxt.xsgygl.bjlh.BjlhGyglForm;

public class SjwhAction extends DispatchAction {

	/**
	 * ��ίѧ������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward twsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_twxs";
		String realTable = "bjlh_twxsb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// �ж��û����޷���Ȩ��
		if (!("xx".equalsIgnoreCase(userType) || "admin"
				.equalsIgnoreCase(userType))) {
			if (!userDep.equalsIgnoreCase(service.getBmdm("��ί"))) {
				String msg = "��ģ��ֻ������ί�û�����,��ȷ�ϣ�";
				request.setAttribute("msg", msg);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		boolean reslut = false;

		// ����������ί��
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] twsxh = myForm.getTwsxh();
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {

				String pk = "twsxh";
				String[] arrzd = new String[] { "twsxh", "sftws" };
				String[] sftws = new String[checkVal.length];

				for (int i = 0; i < checkVal.length; i++) {
					sftws[i] = "��";
					if (twsxh != null && twsxh.length > 0) {
						for (int j = 0; j < twsxh.length; j++) {
							if (twsxh[j].equalsIgnoreCase(checkVal[i])) {
								sftws[i] = "��";
								break;
							}
						}
					}
				}

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);
				saveForm.setArrzd(arrzd);

				model.setTwsxh(checkVal);
				model.setSftws(sftws);

				reslut = service.saveGygl(saveForm, model);
				request.setAttribute("result", reslut);
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| reslut) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "sftws" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_tws.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// �����б�ֵ
		service.setList(myForm, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("twsManage");
	}

	/**
	 * ����ѧ������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward tysManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_tyxs";
		String realTable = "bjlh_tyxsb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// �ж��û����޷���Ȩ��
		if (!("xx".equalsIgnoreCase(userType) || "admin"
				.equalsIgnoreCase(userType))) {
			if (!userDep.equalsIgnoreCase(service.getBmdm("������ѧ��"))) {
				String msg = "��ģ��ֻ����������ѧ���û�����,��ȷ�ϣ�";
				request.setAttribute("msg", msg);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		boolean reslut = false;

		// ��������������
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String[] tysxh = myForm.getTysxh();
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {

				String pk = "tysxh";
				String[] arrzd = new String[] { "tysxh", "sftys" };
				String[] sftys = new String[checkVal.length];

				for (int i = 0; i < checkVal.length; i++) {
					sftys[i] = "��";
					if (tysxh != null && tysxh.length > 0) {
						for (int j = 0; j < tysxh.length; j++) {
							if (tysxh[j].equalsIgnoreCase(checkVal[i])) {
								sftys[i] = "��";
								break;
							}
						}
					}
				}

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);
				saveForm.setArrzd(arrzd);

				model.setTysxh(checkVal);
				model.setSftys(sftys);

				reslut = service.saveGygl(saveForm, model);
				request.setAttribute("result", reslut);
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| reslut) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
					"zymc", "bjmc", "sftys" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_tys.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// �����б�ֵ
		service.setList(myForm, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("tysManage");
	}

	/**
	 * �о�������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward yjsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_yjsxx";
		String realTable = "bjlh_fqrzxsb";
		String doType = request.getParameter("doType");
		String lx = "�о���";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// �ж��û����޷���Ȩ��
		if (!("xx".equalsIgnoreCase(userType) || "admin"
				.equalsIgnoreCase(userType))) {
			if (!userDep.equalsIgnoreCase(service.getBmdm("���д�"))) {
				String msg = "��ģ��ֻ���ɿ��д��û�����,��ȷ�ϣ�";
				request.setAttribute("msg", msg);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		// ����ɾ���о���
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "fqrzxh||lx";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delGygl(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "fqrzxh", "xm", "xb", "xymc",
					"zymc", "bjmc", "rxrq" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_yjs.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// �����б�ֵ
		myForm.setLx(lx);
		service.setList(myForm, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("yjsManage");
	}

	/**
	 * �о���ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward yjsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "��Ϣά��-�о���";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		// String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String isCz = request.getParameter("isCz");
		String tableName = "view_bjlh_yjsxx";
		String realTable = "bjlh_fqrzxsb";
		String lx = "�о���";
		String url = "/xgxt/bjlh_sjwh.do?method=yjsUpdate&doType=update&isCz=yes&pk=";
		doType = Base.isNull(doType) ? "add" : doType;

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// �鿴���޸���ѡ����
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			String pk = "fqrzxh||lx";
			String pkValue = key;
			String[] colList = new String[] { "fqrzxh", "lx", "xm", "xb", "xydm",
					"zydm", "bjdm", "mz", "zzmm", "csrq", "sfzh", "lxdh", "jg",
					"lydq", "sg", "tz", "rxrq", "xz", "bz", "nj" };
			rs = service.getGyglInfo(tableName, pk, pkValue, colList);
			
			//�ж�¼��ֵ�Ƿ��Ѵ��������ݿ�
			if ("yes".equalsIgnoreCase(isCz)) {
				doType = "add";
			}
		}

		// �����о������
		if ("save".equalsIgnoreCase(doType)) {

			String[] onezd = new String[] { "fqrzxh", "lx", "xm", "xb", "bjdm",
					"mz", "zzmm", "csrq", "sfzh", "lxdh", "jg", "lydq", "sg",
					"tz", "rxrq", "xz", "bz" };
			String pk = "fqrzxh||lx";
			String pkValue = myForm.getFqrzxh() + lx;

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			model.setLx(lx);

			boolean result = service.saveGygl(saveForm, model, request);
			request.setAttribute("result", result);
		}
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("url", url);
		request.setAttribute("rs", rs);
		// �����б�ֵ
		myForm.setLx(lx);
		service.setList(myForm, request);
		FormModleCommon.requestSetList(new String[] { "mz", "zzmm" }, request);

		return mapping.findForward("yjsUpdate");
	}

	/**
	 * �ɽ�������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cjsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_cjsxx";
		String realTable = "bjlh_fqrzxsb";
		String doType = request.getParameter("doType");
		String lx = "�ɽ���";

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// �ж��û����޷���Ȩ��
		if (!("xx".equalsIgnoreCase(userType) || "admin"
				.equalsIgnoreCase(userType))) {
			if (!userDep.equalsIgnoreCase(service.getBmdm("���˽�����"))) {
				String msg = "��ģ��ֻ���ɳ��˽������û�����,��ȷ�ϣ�";
				request.setAttribute("msg", msg);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		// ����ɾ���ɽ���
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "fqrzxh||lx";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delGygl(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "fqrzxh", "xm", "xb", "xymc",
					"zymc", "bjmc", "rxrq" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_cjs.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// �����б�ֵ
		myForm.setLx(lx);
		service.setList(myForm, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("cjsManage");
	}

	/**
	 * �ɽ���ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cjsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "��Ϣά��-�ɽ���";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		// String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String isCz = request.getParameter("isCz");
		String tableName = "view_bjlh_cjsxx";
		String realTable = "bjlh_fqrzxsb";
		String lx = "�ɽ���";
		String url = "/xgxt/bjlh_sjwh.do?method=cjsUpdate&doType=update&isCz=yes&pk=";
		doType = Base.isNull(doType) ? "add" : doType;

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// �鿴���޸���ѡ����
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			String pk = "fqrzxh||lx";
			String pkValue = key;
			String[] colList = new String[] { "fqrzxh", "lx", "xm", "xb", "xydm",
					"zydm", "bjdm", "mz", "zzmm", "csrq", "sfzh", "lxdh", "jg",
					"lydq", "sg", "tz", "rxrq", "xz", "bz", "nj" };
			rs = service.getGyglInfo(tableName, pk, pkValue, colList);
			
//			�ж�¼��ֵ�Ƿ��Ѵ��������ݿ�
			if ("yes".equalsIgnoreCase(isCz)) {
				doType = "add";
			}
		}

		// �����о������
		if ("save".equalsIgnoreCase(doType)) {

			String[] onezd = new String[] { "fqrzxh", "lx", "xm", "xb", "bjdm",
					"mz", "zzmm", "csrq", "sfzh", "lxdh", "jg", "lydq", "sg",
					"tz", "rxrq", "xz", "bz" };
			String pk = "fqrzxh||lx";
			String pkValue = myForm.getFqrzxh() + lx;

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			model.setLx("�ɽ���");

			boolean result = service.saveGygl(saveForm, model, request);
			request.setAttribute("result", result);
		}
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("url", url);
		request.setAttribute("rs", rs);
		// �����б�ֵ
		myForm.setLx(lx);
		service.setList(myForm, request);
		FormModleCommon.requestSetList(new String[] { "mz", "zzmm" }, request);

		return mapping.findForward("cjsUpdate");
	}

	/**
	 * ��Դ�����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fykManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_ssxx";
		String realTable = "bjlh_ssxxb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		// ����ɾ����Դ��
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "lddm||cs||qsh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delGygl(saveForm, model);
				
				if (result) {
					service.createCwxx();
				}
				
				request.setAttribute("result", result);
			}
		}

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xqmc", "ssbh", "zcs",
					"cs", "cws", "qsdh", "sfbz","fbbj" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_fyk.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// �����б�ֵ
		service.setList(myForm,request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("fykManage");
	}

	/**
	 * ��Դ��ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fykUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "��Ԣ���� - ��Ϣά�� - ��Դ��ά��";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		// String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String isCz = request.getParameter("isCz");
		String tableName = "view_bjlh_ssxx";
		String realTable = "bjlh_ssxxb";
		String url = "/xgxt/bjlh_sjwh.do?method=fykUpdate&doType=update&isCz=yes&pk=";
		doType = Base.isNull(doType) ? "add" : doType;

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// �鿴���޸���ѡ����
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			String pk = "lddm||cs||qsh";
			String pkValue = key;
			String[] colList = new String[] { "lddm", "ldmc", "cs", "qsh",
					"cws", "fbbj", "qsdh", "sfbz", "bz", "xqdm", "xqmc" };
			rs = service.getGyglInfo(tableName, pk, pkValue, colList);
			myForm.setLddm(rs.get("lddm"));
			
			//	�ж�¼��ֵ�Ƿ��Ѵ��������ݿ�
			if ("yes".equalsIgnoreCase(isCz)) {
				doType = "add";
			}
		}

		// ���淿Դ�����
		if ("save".equalsIgnoreCase(doType)) {

			String[] onezd = new String[] { "lddm", "cs", "qsh", "cws", "fbbj",
					"qsdh", "sfbz", "bz" };
			String pk = "lddm||cs||qsh";
			String pkValue = myForm.getLddm() + myForm.getCs() + myForm.getQsh();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			boolean result = service.saveGygl(saveForm, model, request);
			
			if (result) {
				service.createCwxx();
			}
			
			request.setAttribute("result", result);
		}
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("url", url);
		request.setAttribute("rs", rs);
		// �����б�ֵ
		service.setList(myForm, request);

		return mapping.findForward("fykUpdate");
	}

	/**
	 * ѧ��ס����Ϣ����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_xszsxx";
		String realTable = "bjlh_xszsxxb";
		String doType = request.getParameter("doType");
		@SuppressWarnings("unused")
		String lx = "";
		String isAdmin = "yes";
		String isXy = "no";
		String errlx = myForm.getErrlx();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
				
  		// �ж��û�Ȩ��
		if (!( "admin".equalsIgnoreCase(userType))) {
			lx = myForm.getLx();
			isAdmin = "no";
			if (userDep.equalsIgnoreCase(service.getBmdm("���д�"))) {
				lx = "�о���";	
			}else if (userDep.equalsIgnoreCase(service.getBmdm("���˽�����"))) {
				lx = "�ɽ���";	
			}else if (userDep.equalsIgnoreCase(service.getBmdm("��ί"))) {
				lx = "��ί��";	
			}else if (userDep.equalsIgnoreCase(service.getBmdm("������ѧ��"))) {
				lx = "������";	
			} else {
				if ("xx".equalsIgnoreCase(userType)) {	
					isAdmin = "yes";
				}else{
					lx = "ȫ����";
					isXy = "yes";
					myForm.setXydm(userDep);
				}
			}
			myForm.setLx(lx);
			
			if (!Base.isNull(lx) && !"ȫ����".equalsIgnoreCase(lx)) {
				isXy = "yes";
			}
		}
		
		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		// �����˷�����
		if (!Base.isNull(doType) && "tf".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "id";
				String[] onezd = new String[] { "zzbj","tfrq" }; 
				
				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);
				saveForm.setOnezd(onezd);
				
				model.setZzbj("no");
				model.setTfrq(GetTime.getSystemTime().replace("-",""));
				myForm.setZzbj("no");
				
				result = service.updateGygl(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		//	�����˷�
		if (!Base.isNull(doType) && "zttf".equalsIgnoreCase(doType)) {
			myForm.setZzbj("no");
			result = service.excuteZttf(model);
		}
		
		// ����ɾ����ѡ����
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "id";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delGygl(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xh", "xm", "xb",
					"ssbh","cwm", "xymc","zymc","bjmc","lx","rzrq","tfrq" };

			String sfyc = myForm.getSfyc();
			if (!Base.isNull(sfyc)) {
				colList = new String[] { "pk", "xh", "xm", "xb", "ssbh", "cwm",
						"xymc", "zymc", "bjmc", "lx", "err" };
				if ("lxbf".equalsIgnoreCase(errlx)) {
					tableName = "view_bjlh_gyerr_lxbf";
				}else if ("cwcf".equalsIgnoreCase(errlx)){
					tableName = "view_bjlh_gyerr_cwcf";
				}else if ("ssfp".equalsIgnoreCase(errlx)){
					tableName = "view_bjlh_gyerr_ssfp";
				}else if ("twty".equalsIgnoreCase(errlx)){
					tableName = "view_bjlh_gyerr_twty";
				}else if ("xbyc".equalsIgnoreCase(errlx)){
					tableName = "view_bjlh_gyerr_xbyc";
				}
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_zsxx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		request.setAttribute("isXy", isXy);
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("errlx", errlx);
		// �����б�ֵ
		service.setList(myForm,request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("zsxxManage");
	}

	/**
	 * ѧ��ס����Ϣά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zsxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		
		String title = "��Ԣ���� - ��Ϣά�� - ѧ��ס����Ϣά��";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		// String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_bjlh_xszsxx";
		String realTable = "bjlh_xszsxxb";
		String xh = request.getParameter("xh");
		String lx = request.getParameter("lx");
		doType = Base.isNull(doType) ? "add" : doType;

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();
		
		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// �鿴���޸���ѡ����
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {
			String pk = "pk";
			String pkValue = key;
			String[] colList = new String[] { "xh", "xm", "nj", "xb", "xqdm",
					"lddm", "cs", "qsh", "cwh", "xydm", "xymc", "zymc", "bjmc",
					"lx", "rzrq","tfrq", "zzbj", "bz" };
			if (!Base.isNull(xh) && !Base.isNull(lx)) {
				pkValue = service.getId(xh, lx);
				doType = "add";
			}
			rs = service.getGyglInfo(tableName, pk, pkValue, colList);
			if (Base.isNull(rs.get("xh"))) {
				tableName = "view_bjlh_xsxx";
				pk = "xh||lx";
				pkValue = xh + lx;
				colList = new String[] { "xh", "xm", "nj", "xb", "xydm",
						"xymc", "zymc", "bjmc", "lx" };
				rs = service.getGyglInfo(tableName, pk, pkValue, colList);
			}
			rs.put("zzbj", Base.isNull(rs.get("zzbj")) ? "yes" : rs.get("zzbj"));
			myForm.setXydm(rs.get("xydm"));
			myForm.setXqdm(rs.get("xqdm"));
			myForm.setLddm(rs.get("lddm"));
			myForm.setCs(rs.get("cs"));
			myForm.setQsh(rs.get("qsh"));
			myForm.setXh(rs.get("xh"));
			myForm.setFplx(Base.isNull(rs.get("zzbj")) ? "yes" : rs.get("zzbj"));
			String bmdm = service.getBmdm(rs.get("lx"));
			
			if (SjwhService.TWDM.equalsIgnoreCase(bmdm)) {// ������Ϊ��ί
				myForm.setXydm(SjwhService.TWDM);
			} else if (SjwhService.TYDM.equalsIgnoreCase(bmdm)) {// ������Ϊ������ѧ��
				myForm.setXydm(SjwhService.TYDM);
			} else if (SjwhService.KYDM.equalsIgnoreCase(bmdm)) {// ������Ϊ���д�
				myForm.setXydm(SjwhService.KYDM);
			} else if (SjwhService.CJDM.equalsIgnoreCase(bmdm)) {// ������Ϊ���˽�����
				myForm.setXydm(SjwhService.CJDM);
			} 
		}

		// ����ѧ��ס�����
		if ("save".equalsIgnoreCase(doType)) {

			service.updateTfxx(model);
			
			String[] onezd = new String[] { "xh", "lx", "lddm", "cs", "qsh",
					"cwh", "rzrq", "tfrq", "zzbj", "bz" };
			String pk = "xh||lx||rzrq";
			String pkValue = myForm.getXh() + myForm.getLx() + myForm.getRzrq();

			SaveForm saveForm = new SaveForm();
			
			saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			model.setZzbj(myForm.getZzbj());
			model.setTfrq("");
			boolean result = service.saveGygl(saveForm, model, request);
			request.setAttribute("result", result);
		}
		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("rs", rs);
		// �����б�ֵ
		service.setList(myForm, request);

		return mapping.findForward("zsxxUpdate");
	}
	
	/**
	 * ѧ��ס����Ϣ�鿴
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zsxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		HttpSession session = request.getSession();

//		String userType = session.getAttribute("userType").toString();
//		String userName = session.getAttribute("userName").toString();
//		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
//		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_xsxx";
		String title = "��Ԣ���� - ѧ����Ϣ�鿴";
		String xh = request.getParameter("xh");
		
		model.setXh(xh);
		
		String[] colList = new String[] { "xh", "xm", "xb", "lx", "xymc",
				"zymc", "bjmc" };

		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName, colList, null);
		ArrayList<String[]> rsList = service.getGyglList(tableName, model, colList);
		
		if (rsList != null && rsList.size() > 0) {
			request.setAttribute("topTr", topTr);
			request.setAttribute("rsList", rsList);
			request.setAttribute("rsNum", rsList.size());
		}
		
		request.setAttribute("title", title);
		
		return mapping.findForward("zsxxView");
	}
	
	/**
	 * ѧ��������Ϣ����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xsxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_xsxx";
		String realTable = "";
//		String doType = request.getParameter("doType");
		String lx = request.getParameter("lx");
		String xslx = "";
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		//�жϲ�ѯѧ������
		if ("�о���".equalsIgnoreCase(lx) || "�ɽ���".equalsIgnoreCase(lx)) {
			xslx = lx;
		} 
		myForm.setLx(xslx);
		
		BeanUtils.copyProperties(model, myForm);

		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			model.setLx(lx);
			String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_zsxx.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// �����б�ֵ
		request.setAttribute("lx", lx);
		request.setAttribute("xslx", xslx);
		service.setList(myForm,request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("xsxxManage");
	}
	
	/**
	 * ���λ����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlcwManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");

		SjwhService service = new SjwhService();
		BjlhGyglForm myForm = (BjlhGyglForm) form;
		SjwhModel model = new SjwhModel();

		String tableName = "view_bjlh_xlcw";
		String realTable = "bjlh_xlcwb";
		String doType = request.getParameter("doType");

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		// ����ɾ�����λ
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "lddm||cs||qsh||cwh";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delGygl(saveForm, model);
				request.setAttribute("result", result);
			}
		}
		
		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xqmc", "ssbh", "zcs",
					"cs", "cwm" };
			String sfyc = myForm.getSfyc();
			if (!Base.isNull(sfyc)) {
				tableName = "view_bjlh_xlcw_err";
				colList = new String[] { "pk", "xqmc", "ssbh", "cwm", "err" };
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getGyglList(tableName, model, colList);
		}

		// ��ŷ���·����ȡ��дȨ
		request.setAttribute("path", "bjlh_gygl_xlcw.do");
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("userDep", userDep);
		// �����б�ֵ
		service.setList(myForm, request);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("xlcwManage");
	}

}
