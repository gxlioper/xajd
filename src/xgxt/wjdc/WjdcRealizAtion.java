package xgxt.wjdc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.utils.SearchUtils;

import com.zfsoft.basic.BasicAction;

public class WjdcRealizAtion extends BasicAction {

	/**
	 * �ʾ����_�ʾ���Ϣ_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward wjManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================

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

		// ==================ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {

			this.deleteOperation(request, realTable);

			// ɾ�������Ϣ
			boolean result = service.delZjxx(myForm, "id");

			if (result) {
				// ɾ���ش���Ϣ
				result = service.delHdxx(myForm, "wjbh");
			}

			request.setAttribute("result", result);
		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
					"zjxx", "sfkq", "kyxg", "dawk" };
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

		// =================��ʼ��request��ֵ ===============
		// �����ֶ�
		String[] qtzd = new String[] { "mklx" };
		// �����ֶ�ֵ
		String[] qtzdz = new String[] { mklx };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ==================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjManage");
	}

	/**
	 * �ʾ����_�ʾ���Ϣ_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward wjUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����
		String realTable = rForm.getRealTable();
		// ����ֵ
		String pkValue = rForm.getPk();
		// ID
		String id = myForm.getId();
		// ��ǰѧ��
		String xn = Base.currXn;
		// ��ǰѧ��
		String xq = Base.currXq;
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);
		// ��ǰ���
		String nd = Base.currNd;
		// ģ������
		String mklx = rForm.getMklx();
		// ����ʱ��
		String jlsj = service.getNowTime("YYYYMMDD");
		String jlsjmc = service.getNowTime("YYYY��MM��DD��");
		// �ʾ���ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// �������ݳɹ���־
		boolean result = false;
		// �Ƿ�ɼ�����
		boolean isSt = ("view".equalsIgnoreCase(doType)) ? true : false;
		// ��ʾ��Ϣ
		String message = "";
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setId(id);
			if (Base.isNull(id)) {// ����
				result = service.saveWjInfo(myForm, realTable, request);
			} else {// �޸�
				result = service.updateWjInfo(myForm, realTable, request);
			}
			message = result ? "�����ɹ�" : "����ʧ��";
		}
		// =================end ===================

		// ===================ִ�в鿴���� ======================

		// ����
		if ("add".equalsIgnoreCase(doType)) {
			// ��ʾ��
			rs.put("xn", xn);
			rs.put("xq", xq);
			rs.put("xqmc", xqmc);
			rs.put("nd", nd);
			rs.put("jlsj", jlsj);
			rs.put("jlsjmc", jlsjmc);
		}
		// �鿴���޸ģ�
		else if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType) || result) {

			String pk = Base.isNull(pkValue) ? "xn||xq||nd||wjmc||jlsj" : "id";

			pkValue = Base.isNull(pkValue) ? myForm.getXn() + myForm.getXq()
					+ myForm.getNd() + myForm.getWjmc() + myForm.getJlsj()
					: pkValue;

			String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc",
					"nd", "wjmc", "xn", "xq", "xqmc", "sfkq", "kyxg", "dawk","jwy" };

			rs = service.getWjdcInfo(tableName, pk, pkValue, colList);

			// ����ʾ������Ϣ
			if (!Base.isNull(pkValue)) {
				myForm.setId(pkValue);
				service.setWjZjInfo(myForm, request);
			}
		}

		// =================end ===================

		// =================��ʼ��request��ֵ ====================

		// �����ֶ�
		String[] qtzd = new String[] { "id", "isSt", "mklx", "message" };

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pkValue, String.valueOf(isSt), mklx,
				message };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjUpdate");
	}

	/**
	 * �ʾ����_������Ϣ_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward stManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����
		String realTable = rForm.getRealTable();
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ģ������
		String mklx = rForm.getMklx();
		// ��ʾ��Ϣ
		String message = "";
		// =================end==================

		// ================= ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {

			// ɾ������
			boolean result = service.delStInfo(myForm);

			if (result) {
				// ɾ����
				result = service.delDaInfo(myForm);
			}

			if (result) {
				// ɾ�������Ϣ
				result = service.delZjxx(myForm, "fpbh");
			}

			if (result) {
				// ɾ���ش���Ϣ
				result = service.delHdxx(myForm, "fpbh");
			}

			message = result ? "ɾ���ɹ�" : "ɾ��ʧ��";

		}
		// =================end==================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "stbh", "lxmc", "ssmc", "xsmc",
					"jlsj", "haveda" };
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
		String[] qtzd = new String[] { "mklx", "message" };

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { mklx, message };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("stManage");
	}

	/**
	 * �ʾ����_������Ϣ_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward stUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ģ������
		String mklx = rForm.getMklx();
		mklx = Base.isNull(mklx) ? myForm.getMklx() : mklx;
		// ����·��
		String path = rForm.getPath();
		path = Base.isNull(path) ? request.getParameter("path") : path;
		// ����ֵ
		String pkValue = rForm.getPk() + mklx;
		// ������
		String stbh = service.getStbh();
		// ������ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================ִ�в鿴���� ======================

		// �Զ����ɣ�������
		rs.put("stbh", stbh);
		rs.put("mklx", mklx);
		// �鿴���޸ģ�
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {

			String pk = "stbh||mklx";

			String[] colList = new String[] { "bz", "stbh", "stss", "stlx",
					"stmc", "jlsj", "mklx" };

			rs = service.getWjdcInfo(tableName, pk, pkValue, colList);

		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================

		// �����ֶ�
		String[] qtzd = new String[] {};

		// �����ֶ�ֵ
		String[] qtzdz = new String[] {};

		rForm.setPath(path);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("stUpdate");
	}

	/**
	 * �ʾ����_������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward zjManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		// ����
		String realTable = rForm.getRealTable();
		// =================end==================

		// ================= ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {

			boolean result = service.saveZjInfo(myForm, realTable);

			String message = result ? "�����ɹ�" : "����ʧ��";

			rForm.setMessage(message);
		}
		// =================end==================

		// =================��ʼ��request��ֵ ====================

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("zjManage");
	}

	/**
	 * �ʾ����_�ʾ����_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward wjfpManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = rForm.getUserType();
		// ��½�û�����
		String userDep = rForm.getUserDep();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����
		String realTable = rForm.getRealTable();
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// ѧԺ�û�
			myForm.setQueryequals_xydm(userDep);
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
			String[] outputColumn = { "pk", "xn", "nd", "xqmc", "nj", "xymc",
					"zymc", "bjmc", "wjmc" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjfpManage");
	}

	/**
	 * �ʾ����_�ʾ����_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward wjfpUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		// ��ǰѧ��
		String xn = Base.currXn;
		myForm.setXn(xn);
		// ��ǰ���
		String nd = Base.currNd;
		myForm.setNd(nd);
		// ��ǰѧ��
		String xq = Base.currXq;
		myForm.setXq(xq);
		// =================end==================

		// ===================ִ�б������ ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveWjfp(myForm);
			String message = resault ? "����ɹ�" : "����ʧ��";
			rForm.setMessage(message);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjfpUpdate");
	}

	/**
	 * �ʾ����_�ش��ʾ�_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward hdwjManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = rForm.getUserType();
		// ��½�û���
		String userName = rForm.getUserName();
		// ��ͼ��
		String tableName = rForm.getTableName();
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ==================Ȩ�޿��� ==================
		if (!"stu".equalsIgnoreCase(userType)) {
			String msg = "�ʾ�ֻ����ѧ�����лش�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {

			myForm.setXh(userName);

			String[] outputValue = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
					"zjxx", "hdnum" };
			List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
					tableName, outputValue, null);

			ArrayList<String[]> rs = service.getWjhdList(myForm);
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			if (rs != null && rs.size() > 0) {
				request.setAttribute("rsNum", rs.size());
			}
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdwjManage");
	}

	/**
	 * �ʾ����_�ش��ʾ�_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward hdwjUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = rForm.getUserType();
		// ��½�û���
		String userName = rForm.getUserName();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����ֵ
		String pkValue = rForm.getPk();
		// id
		String id = myForm.getId();
		pkValue = Base.isNull(pkValue) ? id : pkValue;
		// ����
		String lx = myForm.getLx();
		// �ʾ���ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// �Ƿ�ɼ�����
		boolean isSt = true;
		// =================end==================

		// ===================ִ�в鿴���� ======================

		String pk = "id";

		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc", "kyxg", "dawk","jwy" };

		rs = service.getWjdcInfo(tableName, pk, pkValue, colList);

		// ����ʾ������Ϣ

		// ��������
		myForm.setId(pkValue);
		myForm.setXhzgh(userName);
		myForm.setLx(userType);
		myForm.setWjbh(pkValue);

		// �������
		service.setWjZjInfo(myForm, request);
		// =================end ===================

		// =================��ʼ��request��ֵ ====================

		// �����ֶ�
		String[] qtzd = new String[] { "id", "isSt", "bclx" };

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { pkValue, String.valueOf(isSt), lx };

		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdwjUpdate");
	}

	/**
	 * �ʾ����_�ش�ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward hdtjManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// ��ͼ��
		String tableName = rForm.getTableName();
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
					"zjxx", "hdnum" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdtjManage");
	}

	/**
	 * �ʾ����_�ش��ʾ�_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward hdtjUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����ֵ
		String pkValue = rForm.getPk();
		// id
		String id = myForm.getId();
		pkValue = Base.isNull(pkValue) ? id : pkValue;
		// ͳ���꼶
		String nj = myForm.getNj();
		// ͳ��ѧԺ
		String xydm = myForm.getXydm();
		// ͳ��רҵ
		String zydm = myForm.getZydm();
		// ͳ�ư༶
		String bjdm = myForm.getBjdm();
		// ͳ���Ա�
		String xb = myForm.getXb();
		// ͳ��������ò
		String zzmm = myForm.getZzmm();
		// �ʾ���ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================ִ�в鿴���� ======================

		String pk = "id";

		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc" };

		rs = service.getWjdcInfo(tableName, pk, pkValue, colList);

		// ����ͳ����Ϣ
		myForm.setId(pkValue);
		myForm.setNj(nj);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		myForm.setXb(xb);
		myForm.setZzmm(zzmm);
		// ִ��ͳ��
		service.setWjZjInfo(myForm, request);

		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdtjUpdate");
	}

	/**
	 * �ʾ����_�ش���_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward hdjgManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		String doType = request.getParameter("doType");
		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = rForm.getUserType();
		// ��½�û�����
		String userDep = rForm.getUserDep();
		// ��ͼ��
		String tableName = rForm.getTableName();
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// ѧԺ�û�
			myForm.setQueryequals_xydm(userDep);
		}
		// =================end==================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nd",
					"xqmc", "nj", "xymc", "zymc", "bjmc", "wjmc", "isover" };
			selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		//------------����--------------------
		if(EXP.equals(doType)){
			String[] outputColumn = {"xh", "xm", "xb", "xn", "nd",
					"xqmc", "nj", "xymc", "zymc", "bjmc", "wjmc", "isover" };
			expPageData(request, response, tableName, tableName, outputColumn);
			return null;
		}
		
		
		
		// =================��ʼ��request��ֵ ====================
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdjgManage");
	}

	/**
	 * �ʾ����_�ش���_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward hdjgUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����ֵ
		String pkValue = rForm.getPk();
		// �ʾ���ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================ִ�в鿴���� ======================

		String pk = "pk";

		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc", "xh", "lx" };

		rs = service.getWjdcInfo(tableName, pk, pkValue, colList);

		// ����ͳ����Ϣ
		myForm.setId(rs.get("id"));
		myForm.setWjbh(rs.get("id"));
		myForm.setXhzgh(rs.get("xh"));
		myForm.setLx(rs.get("lx"));

		service.setWjZjInfo(myForm, request);

		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdjgUpdate");
	}

	/**
	 * �ʾ����_�������_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward fxjgManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// ��½�û�����
		String userType = rForm.getUserType();
		// ��½�û�����
		String userDep = rForm.getUserDep();
		// ��ͼ��
		String tableName = rForm.getTableName();
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= Ȩ�޿��� ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// ѧԺ�û�
			myForm.setQueryequals_xydm(userDep);
		}
		// =================end==================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nd",
					"xqmc", "nj", "xymc", "zymc", "bjmc", "wjmc" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("fxjgManage");
	}

	/**
	 * �ʾ����_�������_ά��
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward fxjgUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����ֵ
		String pkValue = rForm.getPk();
		// �ʾ���ϸ��Ϣ
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================ִ�в鿴���� ======================

		String pk = "pk";

		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc", "xh", "lx", "wzjg", "tbjg", "hadTb" };

		rs = service.getWjdcInfo(tableName, pk, pkValue, colList);

		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("fxjgUpdate");
	}
}
