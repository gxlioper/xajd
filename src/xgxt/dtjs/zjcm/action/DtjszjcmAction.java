package xgxt.dtjs.zjcm.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import xgxt.dtjs.zjcm.form.DtjszjcmForm;
import xgxt.dtjs.zjcm.model.DtjszjcmModel;
import xgxt.dtjs.zjcm.server.DtjszjcmService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �㽭��ýѧԺѧ����Ϣ�����Ž���-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class DtjszjcmAction extends DispatchAction {

	/**
	 * @describe ����������ѯĿǰ���е�������
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward swcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_swcl";
		String realTable = "swcl";
		String title = "���Ž���-����ά��-������";

		ArrayList<String[]> rs = null;
		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		// ȡ�ò�ѯ����
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// ȡ�ñ�ͷ
			topTr = service.getSwclTopTr();
			// ȡ�ò�ѯ���
			rs = service.getSwclList(myModel, myForm);
			// ҳ�淵�ر�֤����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("swcl");
	}

	/**
	 * @describe ����������Ϣ�����ݿ���ɾ��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward delSwcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		// �������
		String xh = DealString.toGBK(request.getParameter("pk"));

		// ɾ����չ����
		String[] keys = xh.split("!!SplitSignOne!!");
		// ɾ���������
		String delSwcl = service.delSwcl(keys);

		// boolean del = service.delSwcl(xh, request);
		// �ж�ɾ���Ƿ�ɹ�
		if (delSwcl == null || "".equals(delSwcl)) {
			request.setAttribute("del", "ok");
		} else {
			request.setAttribute("del", "no");
			request.setAttribute("delSwcl", delSwcl);
		}

		return mapping.findForward("delSwcl");
	}

	/**
	 * @describe ҳ����ת������ҳ��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward swclShq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		String title = "���Ž���-����ά��-����������";

		String xy = myForm.getXydm();
		// ��ò�������
		List<HashMap<String, String>> cl = service.getClList();

		HashMap<String, String> rs = new HashMap<String, String>();

		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());

		xy = (xy == null) ? "" : xy;

		request.setAttribute("xydm", xy);
		request.setAttribute("title", title);
		request.setAttribute("clList", cl);
		request.setAttribute("sssj", time);
		request.setAttribute("rs", rs);

		return mapping.findForward("swclShq");
	}

	/**
	 * @describe ��������Ϣ���浽���ݿ�
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveSwclshq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String title = "���Ž���-����ά��-����������";
		String xh = myForm.getXh();
		// ������������Ϣ���������ͳһ����Ϊδ���
		myForm.setShlx("wsh");
		BeanUtils.copyProperties(myModel, myForm);
		// ���������Ϣ
		boolean inserted = service.updataSwcl(myModel, xh, request);
		HashMap<String, String> rs = new HashMap<String, String>();
		// ҳ�淵�ر�֤��ע������
		myForm.setBz(DealString.toGBK(myForm.getBz()));
		// �ж�����Ƿ�ɹ�
		if (inserted) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		String xy = "";
		// ��ò�������
		List<HashMap<String, String>> cl = service.getClList();

		request.setAttribute("xydm", xy);
		request.setAttribute("clList", cl);
		request.setAttribute("sssj", "");
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("swclShq");
	}

	/**
	 * @describe ҳ����ת�����ҳ��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward swclShh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		String xh = DealString.toGBK(request.getParameter("pk"));
		String title = "���Ž���-����ά��-���������";
		// ������޸���Ϣ
		HashMap<String, String> rs = service.getSwclSh(xh);

		String xy = "";
		String bjKey = "!!" + "!!";
		// ��ò�������
		List<HashMap<String, String>> cl = service.getClList();
		String shlx = rs.get("shlx");
		String db = DealString.toGBK(request.getParameter("db"));
		// ������¼�Ƿ�ͨ��˫�������
		if (db != null && !"".equals(db)) {
			request.setAttribute("db", db);
		} else {
			// ������˼�¼�Ƿ��Ѿ�ͨ�����
			if ("tg".equals(shlx)) {
				request.setAttribute("tg", "tg");
			}
		}
		request.setAttribute("xydm", xy);
		request.setAttribute("clList", cl);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("swclShh");
	}

	/**
	 * @describe �������Ϣ���浽���ݿ���
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveSwclshh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String title = "���Ž���-����ά��-���������";
		String xh = myForm.getXh();
		// ��ò�������
		String cllx = DealString.toGBK(request.getParameter("cllxV"));
		myForm.setCllx(cllx);

		BeanUtils.copyProperties(myModel, myForm);
		// ���������Ϣ
		boolean inserted = service.updataSwcl(myModel, xh, request);
		// �жϱ����Ƿ�ɹ�
		if (inserted) {
			request.setAttribute("update", "ok");
		} else {
			request.setAttribute("update", "no");
		}
		String xy = "";
		String bjKey = "!!" + "!!";

		request.setAttribute("xydm", xy);
		request.setAttribute("title", title);
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("swclShh");
	}

	// /**
	// * @describe ʱ������
	// * @author luo
	// * @throws Exception
	// */
	// public ActionForward setSj(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	//
	// String pk = DealString.toGBK(request.getParameter("pk"));
	// String[] temp = pk.split("split");
	// // String temppk="";
	// String title = "ʱ������";
	// //
	// // for(int i = 0;i<temp.length;i++){
	// // temppk
	// // }
	// request.setAttribute("title", title);
	// request.setAttribute("temppk", pk);
	//
	// return mapping.findForward("setSj");
	// }

	/**
	 * @describe ҳ����ת����֯��ϵ��ת
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward zzgx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		String xh = DealString.toGBK(request.getParameter("pk"));
		// ����뵳ʱ��
		String rdsj = DealString.toGBK(request.getParameter("rdsj"));

		// ���δ��ת����Ϣ
		HashMap<String, String> rs = service.getZzgxxx(xh);
		// ��ý�ת����Ϣ
		HashMap<String, String> rs_zzgx = service.getZzgxjz(xh);

		xh = rs_zzgx.get("xh");
		// �ж��Ƿ��ת��
		if (xh != null && !"".equals(xh)) {
			request.setAttribute("rs_zzgx", rs_zzgx);
			rs_zzgx.put("rdsj", rdsj);
		} else {
			request.setAttribute("rs", rs);
			rs.put("rdsj", rdsj);
		}

		String xy = "";
		String bjKey = "!!" + "!!";

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("zzgx");
	}

	/**
	 * @descri ������֯��ϵ��ת
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveZzgx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String xh = myForm.getXh();
		String xy = "";
		String bjKey = "!!" + "!!";

		BeanUtils.copyProperties(myModel, myForm);
		// ������֯��ϵ��ת��Ϣ
		boolean inserted = service.updataZzgx(myModel, xh, request);

		// �ж��Ƿ񱣴�ʧ��
		if (inserted) {
			request.setAttribute("update", "ok");
		} else {
			request.setAttribute("update", "no");
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("zzgx");
	}

	/**
	 * @describe ����������ѯĿǰ���еķ�չ����
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward fzdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_fzdx";
		String realTable = "fzdx";
		String title = "���Ž���-����ά��-��չ����";

		ArrayList<String[]> rs = null;
		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		// ��ò�ѯ����
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// ��ѯ�Ƿ�����ѯ��ť
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// ��ñ�ͷ
			topTr = service.getFzdxTopTr();
			// ��ò�ѯ����б�
			rs = service.getFzdxList(myModel, myForm);
			// ��ֹ����ҳ����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("fzdx");
	}

	/**
	 * @describe ҳ����ת����չ����ҳ��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward fzdxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();
		HashMap<String, String> rs = new HashMap<String, String>();

		String pk = DealString.toGBK(request.getParameter("pk"));
		String xy = "";
		HashMap<String, String> rsZjcm = new HashMap<String, String>();
		String bjKey = "!!" + "!!";

		// �ж��Ƿ����Ӱ�ť
		if (pk != null && !"".equals(pk)) {
			// ��÷�չ������Ϣ
			rs = service.getFzdxOne(pk);
			String db = DealString.toGBK(request.getParameter("db"));
			// �ж��Ƿ����˫��
			if (db != null && !"".equals(db)) {
				request.setAttribute("db", db);
			}
			request.setAttribute("doType", "modi");
			request.setAttribute("type", "update");
		} else {
			request.setAttribute("doType", "add");
			rs.put("xh", "");
			request.setAttribute("rsZjcm", rsZjcm);
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("rs", rs);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xsccList", service.getXxccList());

		return mapping.findForward("fzdxOne");
	}

	/**
	 * @describe ����չ������Ϣ���浽���ݿ�
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveFzdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String nd = DealString.toGBK(request.getParameter("ndV"));
		String xm = DealString.toGBK(request.getParameter("xmV"));
		String xq = DealString.toGBK(request.getParameter("xqV"));
		String xn = DealString.toGBK(request.getParameter("xnV"));
		String kssj = DealString.toGBK(request.getParameter("kssjV"));
		String xh = myForm.getXh();

		// Ԥ����Ա��Ԥ����Ϊһ��
		int jssj = 0;
		if (myForm.getJssj() != null || !"".equals(myForm.getJssj())) {
			jssj = Integer.parseInt(myForm.getJssj().substring(0, 4)) + 1;
		}

		myForm.setNd(nd);
		myForm.setXm(xm);
		myForm.setXq(xq);
		myForm.setXn(xn);
		myForm.setKssj(kssj);

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(myModel, myForm);
		// ���淢չ������Ϣ
		boolean inserted = service.updataFzdx(myModel, xh, request);

		// �жϱ����Ƿ�ɹ�
		if (inserted) {
			request.setAttribute("inserted", "ok");
			rs.put("xh", "");

			// �������ʱ��ǿ�,����ǿս�Ԥ����Ա�Ľ���ʱ������Ϊһ���
			// ��:��չ�������ʱ��:20081010 ---> Ԥ����Ա��ʼʱ��:20081010 Ԥ����Ա����ʱ��:20091010
			if (jssj != 0) {
				myModel.setKssj(String.valueOf(myForm.getJssj()));
				myModel.setJssj(String.valueOf(jssj)
						+ myForm.getJssj().substring(4, 8));
			} else {
				myModel.setKssj(null);
				myModel.setJssj(null);
			}
			// ����Ԥ����Ա��Ϣ
			service.updataYbdy(myModel, xh, request);
		} else {
			request.setAttribute("inserted", "no");
			rs.put("xh", "");
		}
		String xy = "";

		request.setAttribute("xydm", xy);
		request.setAttribute("doType", "modi");
		request.setAttribute("rs", rs);
		request.setAttribute("rsZjcm", rs);

		return mapping.findForward("fzdxOne");
	}

	/**
	 * @describe ����չ������Ϣ�����ݿ���ɾ��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward delFzdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		String pk = DealString.toGBK(request.getParameter("pk"));

		// ɾ����չ����
		// boolean del = service.delFzdx(pk, request);
		String[] keys = pk.split("!!SplitSignOne!!");
		String delFzdx = service.delFzdx(keys);
		if (delFzdx == null || "".equals(delFzdx)) {
			request.setAttribute("del", "ok");
		} else {
			request.setAttribute("delFzdx", delFzdx);
			request.setAttribute("del", "no");
		}

		return mapping.findForward("delFzdx");
	}

	/**
	 * @describe ��ʾȫ����չ����
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward fzdxAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_rdjjfzxx";
		String realTable = "rdjjfzxxb";
		String title = "���Ž���-����ά��-�뵳��������-��չ��������";
		String fzdx = request.getParameter("fz");

		ArrayList<String[]> rs = null;
		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		fzdx = (fzdx == null) ? "" : fzdx;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// �ӷ�չ����ҳ�����
			if ("fz".equals(fzdx)) {
				topTr = service.getFzdxTopTr();
				rs = service.getFzdxList(myModel, myForm);
			}
			// ���뵳��������ҳ�����
			else {
				topTr = service.getRdjjfzTopTr();
				rs = service.getRdjjfzList(myModel, myForm);
			}
		}

		request.setAttribute("fz", fzdx);
		request.setAttribute("xydm", xy);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("fzdxAll");
	}

	/**
	 * @describe ����µ�Ա��Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward addDyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();

		String[] checkVal = myForm.getCheckVal();
		String zzsj = myForm.getJssj();

		// �ж��з�ѡ��
		if (checkVal != null) {
			// ����µ�Ա��Ϣ
			String addDy = service.addDy(checkVal, zzsj);
			// �ж��з����ʧ��
			if ((addDy != null && !"".equals(addDy))) {
				request.setAttribute("inserted", "no");
				request.setAttribute("addDy", addDy);
				return mapping.findForward("addDyxx");
			}
		}

		request.setAttribute("inserted", "ok");

		return mapping.findForward("addDyxx");
	}

	/**
	 * @describe ������ӷ�չ������Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward addFzdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();

		String tableName = "view_rdjjfzxx";
		String realTable = "rdjjfzxxb";
		String title = "���Ž���-����ά��-�뵳��������-��չ��������";
		String fzdx = request.getParameter("fz");

		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;

		String[] checkVal = myForm.getCheckVal();
		String kssj = myForm.getKssj();
		String jssj = myForm.getJssj();

		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xydm", xy);
		request.setAttribute("fz", fzdx);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());

		// �ж��з�ѡ��
		if (checkVal != null) {
			String[] keys = new String[checkVal.length];
			String[] isYb = new String[checkVal.length];
			String[] noYb = new String[checkVal.length];
			int j = 0;
			int k = 0;

			for (int i = 0; i < checkVal.length; i++) {
				String[] pk = checkVal[i].split("&");
				String xn = pk[1];
				String xq = pk[2];
				String xh = pk[3];

				keys[i] = xn + xq + xh;
				boolean isYbdy = service.isYbdy(keys[i]);
				// �ж��Ƿ�Ա
				if (isYbdy) {
					isYb[j] = keys[i];
					j++;
				} else {
					noYb[k] = checkVal[i];
					k++;
				}
			}
			// �ж��Ƿ�ӷ�չ����ҳ�����
			if ("fz".equals(fzdx)) {
				// ���·�չ����
				String numFzdx = service.updateFzdx(keys, jssj);
				String numIsYb = null;
				String numNoYb = null;

				if (numFzdx == null || "".equals(numFzdx)) {
					// ��Ԥ����Ա�Ļ�,�޸���Ԥ����Ա��Ϣ
					if (isYb[0] != null) {
						numIsYb = service.updateYbdy(isYb, jssj, j);
					}
					// ����Ԥ����Ա�Ļ�,�����Ԥ����Ա��Ϣ
					if (noYb[0] != null) {
						numNoYb = service.addYbdy(noYb, jssj, k);
					}
				}
				// �������ִ���
				if ((numFzdx != null && !"".equals(numFzdx))
						|| (numIsYb != null && !"".equals(numIsYb))
						|| (numNoYb != null && !"".equals(numNoYb))) {
					request.setAttribute("inserted", "no");
					request.setAttribute("numIsYb", numIsYb);
					request.setAttribute("numNoYb", numNoYb);
					request.setAttribute("numFzdx", numFzdx);
					return mapping.findForward("addFzdx");
				}

			} else {
				// ɾ����չ������Ϣ
				String numFzdx = service.delFzdx(keys);
				// ��ӷ�չ������Ϣ
				String addFzdx = service.addFzdx(checkVal, kssj);
				// �������ִ���
				if ((numFzdx != null && !"".equals(numFzdx))
						|| (addFzdx != null && !"".equals(addFzdx))) {
					request.setAttribute("inserted", "no");
					request.setAttribute("addFzdx", addFzdx);
					request.setAttribute("numFzdx", numFzdx);
					return mapping.findForward("addFzdx");
				}
			}
		}

		request.setAttribute("inserted", "ok");

		return mapping.findForward("addFzdx");
	}

	/**
	 * @describe ��ʾ����ת��������Ԥ����Ա
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward zzYb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_ybdyxx";
		String realTable = "ybdyxxb";
		String title = "���Ž���-����ά��-Ԥ����Ա-ת��һ��";

		ArrayList<String[]> rs = null;
		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// ȡ�õ�Ա��Ϣ��ͷ
			topTr = service.getZzybTopTr();
			// ȡ�÷���ת��������Ԥ����Ա��Ϣ
			rs = service.getZzybList(myModel, myForm);
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("zzYb");
	}

	private void commonRequestSet(HttpServletRequest request, String tableName,
			String realTable, ArrayList<String[]> rs, List topTr, String title) {
		// Request��ֵ��ͨ�÷���
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}

	// TODO
	/**
	 * @describe ����������ѯĿǰ���еĵ��ڽ�����Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dnjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_dnjc";
		String realTable = "dnjc";
		String title = "���Ž���-����ά��-���ڽ���";

		ArrayList<String[]> rs = null;
		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		// ȡ�ò�ѯ����
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// ȡ�ñ�ͷ
			topTr = service.getDnjcTopTr();
			// ȡ�ò�ѯ���
			rs = service.getDnjcList(myModel, myForm);
			// ҳ�淵�ر�֤����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("dnjc");
	}

	/**
	 * @describe �����ڽ�����Ϣ�����ݿ���ɾ��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward delDnjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		// �������
		String xh = DealString.toGBK(request.getParameter("pk"));

		// ɾ����չ����
		String[] keys = xh.split("!!SplitSignOne!!");
		// ɾ���������
		String delDnjc = service.delDnjc(keys);

		// boolean del = service.delSwcl(xh, request);
		// �ж�ɾ���Ƿ�ɹ�
		if (delDnjc == null || "".equals(delDnjc)) {
			request.setAttribute("del", "ok");
		} else {
			request.setAttribute("del", "no");
			request.setAttribute("delDnjc", delDnjc);
		}

		return mapping.findForward("delDnjc");
	}

	/**
	 * @describe ҳ����ת�����ڽ�������ҳ��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dnjcShq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		String title = "���Ž���-����ά��-���ڽ�������";
		String xy = myForm.getXydm();
		// ��ý�������
		List<HashMap<String, String>> lx = service.getJclxList();
		// ��ý�������
		List<HashMap<String, String>> ly = service.getJclyList();

		HashMap<String, String> rs = new HashMap<String, String>();

		xy = (xy == null) ? "" : xy;

		request.setAttribute("xydm", xy);
		request.setAttribute("title", title);
		request.setAttribute("lxList", lx);
		request.setAttribute("lyList", ly);
		request.setAttribute("rs", rs);

		return mapping.findForward("dnjcShq");
	}

	/**
	 * @describe �����ڽ���������Ϣ���浽���ݿ�
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveDnjcshq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String title = "���Ž���-����ά��-���ڽ�������";
		String xh = myForm.getXh();
		// ������������Ϣ���������ͳһ����Ϊδ���
		myForm.setShlx("wsh");
		// ��ý�������
		List<HashMap<String, String>> lx = service.getJclxList();
		// ��ý�������
		List<HashMap<String, String>> ly = service.getJclyList();

		BeanUtils.copyProperties(myModel, myForm);
		// ���������Ϣ
		boolean inserted = service.updataDnjc(myModel, xh, request);
		HashMap<String, String> rs = new HashMap<String, String>();
		// ҳ�淵�ر�֤����������
		myForm.setBz(DealString.toGBK(myForm.getBz()));
		// �ж�����Ƿ�ɹ�
		if (inserted) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		String xy = "";

		request.setAttribute("xydm", xy);
		request.setAttribute("lxList", lx);
		request.setAttribute("lyList", ly);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("dnjcShq");
	}

	/**
	 * @describe ҳ����ת�����ڽ������ҳ��
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dnjcShh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		String xh = DealString.toGBK(request.getParameter("pk"));
		// ������޸���Ϣ
		HashMap<String, String> rs = service.getDnjc(xh);
		String title = "���Ž���-����ά��-���ڽ������";
		String xy = "";
		String bjKey = "!!" + "!!";
		// ��ý�������
		List<HashMap<String, String>> lx = service.getJclxList();
		// ��ý�������
		List<HashMap<String, String>> ly = service.getJclyList();
		String shlx = rs.get("shlx");
		String db = DealString.toGBK(request.getParameter("db"));
		// ������¼�Ƿ�ͨ��˫�������
		if (db != null && !"".equals(db)) {
			request.setAttribute("db", db);
		} else {
			// ������˼�¼�Ƿ��Ѿ�ͨ�����
			if ("tg".equals(shlx)) {
				request.setAttribute("tg", "tg");
			}
		}
		request.setAttribute("xydm", xy);
		request.setAttribute("lxList", lx);
		request.setAttribute("lyList", ly);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("dnjcShh");
	}

	/**
	 * @describe �����ڽ��������Ϣ���浽���ݿ���
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveDnjcshh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String title = "���Ž���-����ά��-���ڽ������";
		String xh = myForm.getXh();
		// ��ò�������
		String shlx = DealString.toGBK(request.getParameter("shlxV"));
		myForm.setCllx(shlx);

		BeanUtils.copyProperties(myModel, myForm);
		// ���������Ϣ
		boolean inserted = service.updataDnjc(myModel, xh, request);
		// �жϱ����Ƿ�ɹ�
		if (inserted) {
			request.setAttribute("update", "ok");
		} else {
			request.setAttribute("update", "no");
		}
		String xy = "";
		String bjKey = "!!" + "!!";

		request.setAttribute("xydm", xy);
		request.setAttribute("title", title);
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("dnjcShh");
	}

	/**
	 * @describe ������˵��ڽ�����Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dnjcShhAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		// �������
		String xh = DealString.toGBK(request.getParameter("pk"));
		String shlx = DealString.toGBK(request.getParameter("zht"));
		// ɾ����չ����
		String[] keys = xh.split("!!SplitSignOne!!");
		// ɾ���������
		String updateShh = service.dnjcShhAll(keys,shlx);

		// boolean del = service.delSwcl(xh, request);
		// �ж�ɾ���Ƿ�ɹ�
		if (updateShh == null || "".equals(updateShh)) {
			request.setAttribute("update", "ok");
		} else {
			request.setAttribute("update", "no");
			request.setAttribute("updateShh", updateShh);
		}

		return mapping.findForward("delDnjc");
	}
	
	/**
	 * @describe ���������������Ϣ
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward swclShhAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		// �������
		String xh = DealString.toGBK(request.getParameter("pk"));
		String shlx = DealString.toGBK(request.getParameter("zht"));
		// ɾ����չ����
		String[] keys = xh.split("!!SplitSignOne!!");
		// ɾ���������
		String updateShh = service.swclShhAll(keys,shlx);

		// boolean del = service.delSwcl(xh, request);
		// �ж�ɾ���Ƿ�ɹ�
		if (updateShh == null || "".equals(updateShh)) {
			request.setAttribute("update", "ok");
		} else {
			request.setAttribute("update", "no");
			request.setAttribute("updateShh", updateShh);
		}

		return mapping.findForward("delSwcl");
	}
}
