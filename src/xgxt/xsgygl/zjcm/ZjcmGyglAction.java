package xgxt.xsgygl.zjcm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.pjpy.cqdzgc.PjpyCqdzgcForm;
import xgxt.pjpy.cqdzgc.PjpyCqdzgcService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xsgygl.GyglTyForm;

import com.zfsoft.basic.BasicAction;

public class ZjcmGyglAction extends BasicAction {

	/**
	 * ����������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wsjcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		ZjcmGyglModel model = new ZjcmGyglModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_wsjc";
		String realTable = "zjcm_wsjcb";
		String xn = Base.currXn;
		String xq = Base.currXq;
		String csh = request.getParameter("csh");
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		if ("xy".equals(userType)) {
			myForm.setXydm(userDep);
		}

		// �ж����ݿ����������ȼ��Ƿ�ά��
		if (!service.isWsjcdj()) {
			String msg = "�������ȼ���δά������ȷ��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		// ��ʼ�����ݣ���ѧ�꣬ѧ�ڣ��ܴ�
		if (Base.isNull(csh)) {
			myForm.setXn(xn);
			myForm.setXq(xq);
			csh = "no";
		}

		BeanUtils.copyProperties(model, myForm);

		// ����ɾ����������
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||jcqs||jcsj";

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
			String[] colList = new String[] { "pk", "xn", "xqmcc", "xqmc",
					"ldmc", "cs", "qsh", "jcsj", "fs" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getWsjcQueryList(tableName, model, colList);

		}
		// ��ʼ���б�ֵ
		service.setList(myForm, request, "wsjc");
		request.setAttribute("path", "wsjc.do");
		request.setAttribute("csh", csh);
		
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("wsjcManage");
	}

	/**
	 * �������ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wsjcUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		ZjcmGyglModel model = new ZjcmGyglModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_ssxx";
		String realTable = "zjcm_wsjcb";
		String userName = session.getAttribute("userName").toString();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String xn = Base.currXn;
		String xq = Base.currXq;
		String bz = service.getWsdjMsg();
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		if ("xy".equals(userType)) {
			myForm.setXydm(userDep);
		}	
		
		
		BeanUtils.copyProperties(model, myForm);

		// ������������
		if ("save".equalsIgnoreCase(doType)) {

			int n = 0;
			for (int i = 0; i < model.getFs().length; i++) {
				if (!Base.isNull(model.getFs()[i])) {
					n++;
				}
			}

			String[] arrzd = new String[] { "jcqs", "fs" };
			String[] onezd = new String[] { "xn", "xq", "jcsj", "lrr" };
			String pk = "xn||xq||jcqs||jcsj";
			String[] pkValue = new String[n];

			String jcsj = request.getParameter("jcsj");
			String[] jcqs = new String[n];
			String[] fs = new String[n];

			n = 0;

			for (int i = 0; i < model.getJcqs().length; i++) {
				if (!Base.isNull(model.getFs()[i])) {
					pkValue[n] = xn + xq + model.getJcqs()[i] + jcsj;
					jcqs[n] = model.getJcqs()[i];
					fs[n] = model.getFs()[i];
					n++;
				}
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			model.setXn(xn);
			model.setXq(xq);
			model.setJcsj(jcsj);
			model.setLrr(userName);
			model.setJcqs(jcqs);
			model.setFs(fs);

			result = service.saveGygl(saveForm, model);

			request.setAttribute("result", result);
		}

		BeanUtils.copyProperties(model, myForm);

		// �����ѯ��ť���в�ѯ
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xqmc", "ldmc", "cs",
					"qsh", "cws", "xbxd", "sfbz", "qsdh" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
//			rs = service.getGyglList(tableName, model, colList, "");
			rs = service.getWsjcQueryList(tableName, model, colList);
		}
		// ��ʼ���б�ֵ
		service.setList(myForm, request, "wsjc");
		request.setAttribute("path", "wsjc.do");
		request.setAttribute("bz", bz);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("wsjcUpdate");
	}

	/**
	 * ���������Ϣ�鿴
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wsjcView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "��Ԣ���� - ���������Ϣ - �鿴";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_xszsxx";
		String realTable = "zjcm_wsjcb";
		String pk = "";
		String pkValue = request.getParameter("pkValue");

		doType = Base.isNull(doType) ? "add" : doType;

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		ZjcmGyglModel model = new ZjcmGyglModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// �鿴��Դ����Ϣ
		if ("view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {
			pk = "pk";
			pkValue = key;

			String[] colList = new String[] { "xqmc", "lddm", "ldmc", "cs",
					"qsh", "xn", "xq", "jcsj", "fs" };
			rs = service.getGyglInfo("view_zjcm_wsjc", pk, pkValue, colList);

			colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "cwh" };

			model.setLddm(rs.get("lddm"));
			model.setCs(rs.get("cs"));
			model.setQsh(rs.get("qsh"));

			ArrayList<String[]> rsList = service.getGyglList(tableName, model,
					colList, "");
			if (rsList != null && rsList.size() > 0) {
				List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
						"view_xszsxx", colList, null);
				request.setAttribute("topTr", topTr);
				request.setAttribute("rsList", rsList);
				request.setAttribute("rsNum", rsList.size());
			}
		}

		// �������������޸�
		if ("save".equalsIgnoreCase(doType)) {
			String fs = myForm.getFs()[0];
			pkValue = request.getParameter("pkValue");
			;
			boolean result = service.updateWsjcf(fs, pkValue, request);
			request.setAttribute("result", result);
		}
		// ��ʼ���б�ֵ
		service.setList(myForm, request, "wsjc");

		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		
		return mapping.findForward("wsjcView");
	}

	/**
	 * ѧ��ס����Ϣ�鿴
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fyxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "��Ԣ���� - ��Դ��Ϣ - �鿴";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_xszsxx";
		String realTable = "xszsxxb";

		doType = Base.isNull(doType) ? "add" : doType;

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		ZjcmGyglModel model = new ZjcmGyglModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// �鿴��Դ����Ϣ
		if ("view".equalsIgnoreCase(doType)) {
			String pk = "pk";
			String pkValue = key;

			String[] colList = new String[] { "lddm", "ldmc", "cs", "qsh",
					"cws", "fpbj", "qsdh", "sfbz", "bz", "xqdm", "xqmc" };
			rs = service.getGyglInfo("view_zjcm_ssxx", pk, pkValue, colList);

			colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "cwh" };

			model.setLddm(rs.get("lddm"));
			model.setCs(rs.get("cs"));
			model.setQsh(rs.get("qsh"));

			ArrayList<String[]> rsList = service.getGyglList(tableName, model,
					colList, "");
			if (rsList != null && rsList.size() > 0) {
				List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
						"view_xszsxx", colList, null);
				request.setAttribute("topTr", topTr);
				request.setAttribute("rsList", rsList);
				request.setAttribute("rsNum", rsList.size());
			}
		}

		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("rs", rs);

		return mapping.findForward("fyxxView");
	}

	/**
	 * �������ͳ��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wsjcTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "��Ԣ���� - ͳ�Ʒ��� - �������ͳ��";
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String tableName = "";
		String realTable = "";
		String xn = Base.currXn;
		String xq = Base.currXq;

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		ZjcmGyglModel model = new ZjcmGyglModel();

		// ��ʼ�����ݣ���ѧ�꣬ѧ��
		myForm.setXn(xn);
		myForm.setXq(xq);

		// ��ʼ���б�ֵ
		service.setList(myForm, request, "wsjc");

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "print".equalsIgnoreCase(doType)) {

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.printWsjcExcel(model, response.getOutputStream());

			return null;
		}

		request.setAttribute("title", title);

		return mapping.findForward("wsjcTj");
	}

	/**
	 * ��Ԣ����(����)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward gybxSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		XsxxglService stuService = new XsxxglService();

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �û���
		String userName = (String) session.getAttribute("userName");
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ģ������
		String mklx =  "sq";
		// �Ƿ�����ѯ
		String isjg = request.getParameter("isjg");
		// ѧ��
		String xh = request.getParameter("xh");
		xh = "stu".equalsIgnoreCase(userType) ? userName : xh;
		// ����·��
		String path = "gygl_bxsqManage.do";
		// ����ʱ��
		String bxsj = service.getNowTime("YYYYMMDD");
		String bxsjmc = service.getNowTime("YYYY��MM��DD��");
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ�����ɹ�
		boolean result = false;
		// �����Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("xh", xh);
		// ѧ��������Ϣ
		HashMap<String, String> stuInfo = new HashMap<String, String>();
		// =================end==================

		// ================= �����Ϣȡ�� ==================
		if (!Base.isNull(xh)) {
			// ѧ��������Ϣ
			stuInfo = stuService.selectStuinfo(xh);
			rs.putAll(stuInfo);
			rs.put("bxsj", bxsj);
			rs.put("bxsjmc", bxsjmc);
		}
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			result = service.saveBxSq(myForm, request);
			message = result ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// ===================����ɹ��� ======================
		// ��ʾ������Ϣ
		if (result) {
			rs = service.getBxInfo(myForm);

			// ����ʱ��
			bxsj = rs.get("bxsj");

			if (!Base.isNull(bxsj) && bxsj.length() >= 8) {
				bxsj = bxsj.substring(0, 8);
				bxsjmc = bxsj.substring(0, 4) + "��" + bxsj.substring(4, 6)
						+ "��" + bxsj.substring(6, 8) + "��";
			}

			rs.put("bxsj", bxsj);
			rs.put("bxsjmc", bxsjmc);
			rs.putAll(stuInfo);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "isjg" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { isjg };

		rForm.setUserType(userType);
		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("gybxSq");
	}

	/**
	 * ��Ԣ����(��˹���)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �û���
		String userName = (String) session.getAttribute("userName");
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �û�����
		String userDep = (String) session.getAttribute("userDep");
		// ����ԱȨ��
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ģ������
		String mklx = "sh";
		// ��ͼ
		String tableName = "view_gygl_zjcm_gybx";
		// ����·��
		String path = "gygl_bxshManage.do";
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// �Ƿ�ѧԺ
		boolean isxy = false;
		// ��Ŀ�б�
		List<HashMap<String, String>> rsList = null;
		// =================end==================

		// ==================��½�û���� ==================

		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// ѧԺ�û�
			myForm.setQueryequals_xydm(userDep);
			myForm.setXydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "nj", "xymc", "zymc",
					"bjmc", "xqmc", "ldmc", "cs", "qsh", "bxsj", "shzt" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "isxy" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("bxshManage");
	}

	/**
	 * ��Ԣ����(���)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward gybxSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ģ������
		String mklx = "sh";
		// ����
		String pk = request.getParameter("pk");
		myForm.setPkValue(pk);
		// ����·��
		String path = "gygl_bxshManage.do";
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ�����ɹ�
		boolean result = false;
		// �����Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("pk", pk);
		// =================end==================

		// ================= �����Ϣȡ�� ==================
		if (!Base.isNull(pk)) {
			// ѧ��������Ϣ
			rs = service.getBxshInfo(myForm);
		}
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {

			// ���������Ϣ
			result = service.saveBxSh(myForm, request);

			if (result) {
				// ���������Ϣ
				result = service.saveBxcl(myForm);
			}
			message = result ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setUserType(userType);
		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("gybxSh");
	}

	/**
	 * ��Ԣ����(�������)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bxjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �û���
		String userName = (String) session.getAttribute("userName");
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �û�����
		String userDep = (String) session.getAttribute("userDep");
		// ����ԱȨ��
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ģ������
		String mklx = "sh";
		// ��ͼ
		String tableName = "view_gygl_zjcm_gybx";
		// ��
		String realTable = "gygl_gybxb";
		// ����·��
		String path = "gygl_bxjgManage.do";
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// �Ƿ�ѧԺ
		boolean isxy = false;
		// =================end==================

		// ==================��½�û���� ==================
		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// ѧԺ�û�
			myForm.setQueryequals_xydm(userDep);
			myForm.setXydm(userDep);
			isxy = true;
		} else if ("stu".equalsIgnoreCase(userType)) {
			// ѧ���û�
			myForm.setQuerylike_xh(userName);
		}
		// =================end==================

		// ==================ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================

		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "nj", "xymc", "zymc",
					"bjmc", "xqmc", "ldmc", "cs", "qsh", "bxsj", "sfsf",
					"sfwg", "shzt" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "isxy" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("bxjgManage");
	}

	/**
	 * ��Ԣ����(����)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward gybxPj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ģ������
		String mklx = "pj";
		// ����
		String pk = request.getParameter("pk");
		myForm.setPkValue(pk);
		// ����·��
		String path = "gygl_bxjgManage.do";
		// ��ʾ��Ϣ
		String message = "";
		// �Ƿ�����ɹ�
		boolean result = false;
		// �����Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("pk", pk);
		// =================end==================

		// ================= �����Ϣȡ�� ==================
		if (!Base.isNull(pk)) {
			rs = service.getBxshInfo(myForm);
		}
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {

			// ����������Ϣ
			result = service.saveBxPj(myForm, request);
			message = result ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] {};
		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setUserType(userType);
		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("gybxPj");
	}

	/**
	 * ��Ԣ����(ͳ�ƽ��)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bxtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �û���
		String userName = (String) session.getAttribute("userName");
		// �û�����
		String userType = (String) session.getAttribute("userType");
		// �û�����
		String userDep = (String) session.getAttribute("userDep");
		// ����ԱȨ��
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// ������Ȩ��
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ģ������
		String mklx = "tj";
		// ����·��
		String path = "gygl_bxtjManage.do";
		// ��ʾ��Ϣ
		String message = "";
		// ͳ�Ʒ�Χ
		String fw =myForm.getTjfw();
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// �Ƿ�ѧԺ
		boolean isxy = false;
		// ��ͷ
		List<HashMap<String, String>> topTr = null;
		// ����б�
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// =================end==================

		// ==================��½�û���� ==================
		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// ѧԺ�û�
			myForm.setXydm(userDep);
			isxy = true;
		} else if ("stu".equalsIgnoreCase(userType)) {
			// ѧ���û�
			myForm.setXh(userName);
		}
		// =================end==================

		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expGybxInfo(myForm, response.getOutputStream());

			return mapping.findForward("");
		}
		// =================end ===================
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			
			topTr = service.getBxTjTop(myForm);
			rsArrList = service.getBxTjList(myForm);
			//������
			if (topTr != null && topTr.size() > 0) {
				String tjfs = myForm.getTjfs();
				int num = "bxtjfw_ss".equalsIgnoreCase(tjfs) ? 5 : 2;
				request.setAttribute("topNum", topTr.size() - num);
			}
			//��¼��
			if (rsArrList != null && rsArrList.size() > 0) {
				request.setAttribute("rsNum", rsArrList.size()-1);
			}
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "isxy", "fw" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { String.valueOf(isxy), fw };

		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("bxtjManage");
	}
	
	/**
	 * ��Ԣ������Ϣͳ��
	 * method wstjManage
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wstjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		myForm.setTjxn(request.getParameter("xn"));
		myForm.setTjxq(request.getParameter("xq"));
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/gygl/gygl_jssp.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.wstjManage(myForm, request, wwb);
		return null;
	}
	
	
}
