package xgxt.szdw.nbzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ����ְҵ��˼�����顪��ɫ�༶��Ŀ-action��
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
public class BjtsxmAction extends DispatchAction {

	/**
	 * @describe �༶��ɫ��Ŀ�����걨
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward bjtsxmSb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");
		String shzt = request.getParameter("shzt");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String title = "˼������ - �༶��ɫ��Ŀ - �걨";
		String tableName = "nbzy_tsbjxm_xmsb";

		BjtsxmService service = new BjtsxmService();
		BjtsxmForm myForm = (BjtsxmForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("stu".equalsIgnoreCase(userType)) {
			// �ж��Ƿ�೤
			if (service.isBz(userName)) {
				rs = service.getSzbjDetail(userName);
				List ldList = service.ldList();
				List csList = service.csList();
				List qsList = service.qsList();
				request.setAttribute("ldList", ldList);
				request.setAttribute("csList", csList);
				request.setAttribute("qsList", qsList);
			} else {
				request.setAttribute("title", title);
				request.setAttribute("rs", rs);
				request.setAttribute("userType", userType);
				request.setAttribute("pkValue", pkValue);
				request.setAttribute("doType", doType);

				return mapping.findForward("bjtsxmSb");
			}
		}
		// �ж��Ƿ������
		// TODO
		// userName = "123";
		List bjList = service.getBzrBj(userName);
		if (bjList != null && bjList.size() > 0) {
			userType = "teacher";
		}
		// ���ϵͳʱ��
		String nowTime = service.GetSysTime();
		if ("del".equalsIgnoreCase(doType)) {
			if (service.noSh(tableName, pkValue)) {
				boolean inserted = service.delxmSq(tableName, pkValue, request);
				if (inserted) {
					request.setAttribute("result", "yes");
				} else {
					request.setAttribute("result", "no");
				}
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/bjtsxm.do?method=bjtsxmSbjg&go=go",
					false);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean inserted = false;
			if ("stu".equalsIgnoreCase(userType)) {
				inserted = service.saveBjtsxmSb(myForm, pkValue,request);
			} else if ("teacher".equalsIgnoreCase(userType)) {
				shzt = "tg".equalsIgnoreCase(shzt) ? "��ͨ��" : "δͨ��";
				String[] columns = new String[] { "bzrsh", "bzryj", "bzrshsj" };
				String[] values = new String[] { shzt,
						DealString.toGBK(myForm.getBzryj()), nowTime };
				inserted = service.saveBjtsxmSh(tableName, pkValue, columns,
						values, request);
			} else if ("xy".equalsIgnoreCase(userType)) {
				shzt = "tg".equalsIgnoreCase(shzt) ? "��ͨ��" : "δͨ��";
				String[] columns = new String[] { "xysh", "xyyj", "xyshr",
						"xyshsj" };
				String[] values = new String[] { shzt,
						DealString.toGBK(myForm.getXyyj()),
						DealString.toGBK(userName), nowTime };
				inserted = service.saveBjtsxmSh(tableName, pkValue, columns,
						values, request);
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				shzt = "tg".equalsIgnoreCase(shzt) ? "��ͨ��" : "δͨ��";
				String[] columns = new String[] { "xxsh", "xxyj", "xxshr",
						"xxshsj" };
				String[] values = new String[] { shzt,
						DealString.toGBK(myForm.getXxyj()),
						DealString.toGBK(userName), nowTime };
				inserted = service.saveBjtsxmSh(tableName, pkValue, columns,
						values, request);
			}
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			doType = "edit";
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {

			rs = service.getTsxmsbDetail(pkValue);
			HashMap<String, String> rsBzr = service.getSzbjDetail(rs
					.get("bzxh"));
			rs.put("bzrxm", rsBzr.get("bzrxm"));
			List ldList = service.ldList();
			List csList = service.csList();
			List qsList = service.qsList();
			request.setAttribute("ldList", ldList);
			request.setAttribute("csList", csList);
			request.setAttribute("qsList", qsList);

			if (rs.get("lxrqs") != null && rs.get("lxrqs").length() > 0) {
				String[] lxrqs = rs.get("lxrqs").split("-");
				String lddm = lxrqs[0];
				String qsh = lxrqs[1];
				String cs = service.getCs(rs.get("lxrqs"));
				rs.put("lddm", lddm);
				rs.put("cs", cs);
				rs.put("qsh", qsh);
			}
		}

		if (service.noSh(tableName, pkValue)) {
			request.setAttribute("noSh", "yes");
		} else {
			request.setAttribute("noSh", "no");
		}

		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("bjtsxmSb");
	}

	/**
	 * @describe �༶��ɫ��Ŀ�����걨���
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward bjtsxmSbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_nbzy_bjtsxmsbjg";
		String realTable = "nbzy_tsbjxm_xmsb";
		String title = "˼������ - �༶��ɫ��Ŀ - �걨���";

		BjtsxmService service = new BjtsxmService();
		BjtsxmForm myForm = (BjtsxmForm) form;

		// ���������ڰ༶
		// TODO
		// userName = "123";
		List bjList = service.getBzrBj(userName);
		if (bjList != null && bjList.size() > 0) {
			userType = "teacher";
		}
		// ȡ�ò�ѯ����

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

		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> topTr = null;

		String[] colList = new String[] {};

		if ("student".equalsIgnoreCase(userOnLine)) {
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "bzrshimg", "xyshimg", "xxshimg" };
		} else if ("teacher".equalsIgnoreCase(userOnLine) && bjList != null
				&& bjList.size() > 0) {
			userType = "teacher";
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "bzrshimg" };
		} else if ("xy".equalsIgnoreCase(userType)) {
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "xyshimg" };
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "xxshimg" };
		}

		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			topTr = service.getTopTr(tableName, colList);
			rs = service.getTsxmsbjgList(myForm, colList, userName, userType);
			// ҳ�淵�ر�֤����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		request.setAttribute("rs", rs);
		if (rs != null && !"".equals(rs)) {
			request.setAttribute("rsNum", rs.size());
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xydm", xy);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		if ("stu".equalsIgnoreCase(userType)) {
			// �ж��Ƿ�೤
			if (!service.isBz(userName)) {
				request.setAttribute("isBz", "no");
			}
		}
		return mapping.findForward("bjtsxmSbjg");

	}

	/**
	 * �༶��ɫ��Ŀ�걨���ӡ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjtsxmsbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = request.getParameter("pk");
		BjtsxmService service = new BjtsxmService();
		HashMap<String, String> rs = service.getTsxmsbDetail(pk);
		HashMap<String, String> rsBzr = service.getSzbjDetail(rs.get("bzxh"));
		rs.put("bzrxm", rsBzr.get("bzrxm"));
		String xgbjfys = rs.get("xgbjfys");
		String zyjfys = rs.get("zyjfys");
		if (xgbjfys != null && xgbjfys.length() > 0) {
			String xgb[] = xgbjfys.split("!!@@!!");
			for (int i = 0; i < xgb.length; i++) {
				if (i != xgb.length - 1) {
					String[] jtnr = xgb[i].split("!!@!!");
					rs.put("zz_xm" + i, jtnr[0]);
					rs.put("zz_sbzz" + i, jtnr[1]);
					rs.put("zz_xbzz" + i, jtnr[2]);
					rs.put("zz_hj" + i, jtnr[3]);
				} else {
					String[] jtnr = xgb[i].split("!!@!!");
					rs.put("zz_sbhj", jtnr[0]);
					rs.put("zz_xbhj", jtnr[1]);
					rs.put("zz_zhj", jtnr[2]);
				}
			}
		}
		if (zyjfys != null && zyjfys.length() > 0) {
			String zy[] = zyjfys.split("!!@@!!");
			for (int i = 0; i < zy.length; i++) {
				if (i != zy.length - 1) {
					String[] jtnr = zy[i].split("!!@!!");
					rs.put("zy_xm" + i, jtnr[0]);
					rs.put("zy_sbzz" + i, jtnr[1]);
					rs.put("zy_xbzz" + i, jtnr[2]);
					rs.put("zy_hj" + i, jtnr[3]);
				} else {
					String[] jtnr = zy[i].split("!!@!!");
					rs.put("zy_sbhj", jtnr[0]);
					rs.put("zy_xbhj", jtnr[1]);
					rs.put("zy_zhj", jtnr[2]);
				}
			}
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("bjtsxmsbPrint");
	}

	/**
	 * @describe �༶��ɫ��Ŀ�������
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward bjtsxmJs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_nbzy_bjtsxmsbjg";
		String realTable = "nbzy_tsbjxm_xmsb";
		String title = "˼������ - �༶��ɫ��Ŀ - ��Ŀ����";

		BjtsxmService service = new BjtsxmService();
		BjtsxmForm myForm = (BjtsxmForm) form;

		// �ж��Ƿ������
		// TODO
		// userName = "123";
		List bjList = service.getBzrBj(userName);
		if (!"stu".equalsIgnoreCase(userType)) {
			if (bjList != null && bjList.size() > 0) {
				userType = "teacher";
				request.setAttribute("isBzr", "yes");
			} else {
				request.setAttribute("isBzr", "no");
			}
		}
		// ȡ�ò�ѯ����

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

		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> topTr = null;

		String[] colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
				"bjmc", "xmsqsj", "bzrsh" };

		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			topTr = service.getTopTr(tableName, colList);
			rs = service.getTsxmJdList(myForm, colList, userName, userType);
			// ҳ�淵�ر�֤����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		request.setAttribute("rs", rs);
		if (rs != null && !"".equals(rs)) {
			request.setAttribute("rsNum", rs.size());
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xydm", xy);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		if ("stu".equalsIgnoreCase(userType)) {
			// �ж��Ƿ�೤
			if (!service.isBz(userName)) {
				request.setAttribute("isBz", "no");
			} else {
				request.setAttribute("isBzr", "yes");
			}
		}

		return mapping.findForward("bjtsxmJs");

	}

	/**
	 * @describe �༶��ɫ��Ŀ�������
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward bjtsxmJsjd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");
		String shzt = request.getParameter("shzt");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String title = "˼������ - �༶��ɫ��Ŀ - �������";
		String tableName = "nbzy_tsbjxm_xmjs";

		BjtsxmService service = new BjtsxmService();
		BjtsxmForm myForm = (BjtsxmForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("stu".equalsIgnoreCase(userType)) {
			// �ж��Ƿ�೤
			if (service.isBz(userName)) {
				rs = service.getSzbjDetail(userName);
				List ldList = service.ldList();
				List csList = service.csList();
				List qsList = service.qsList();
				request.setAttribute("ldList", ldList);
				request.setAttribute("csList", csList);
				request.setAttribute("qsList", qsList);
			} else {
				request.setAttribute("title", title);
				request.setAttribute("rs", rs);
				request.setAttribute("userType", userType);
				request.setAttribute("pkValue", pkValue);
				request.setAttribute("doType", doType);

				return mapping.findForward("bjtsxmJsjd");
			}
		}
		// �ж��Ƿ������
		// TODO
		// userName = "123";
		List bjList = service.getBzrBj(userName);
		if (bjList != null && bjList.size() > 0) {
			userType = "teacher";
		}
		// ���ϵͳʱ��
		String nowTime = service.GetSysTime();
		if ("del".equalsIgnoreCase(doType)) {
			if (service.noSh(tableName, pkValue)) {
				boolean inserted = service.delxmSq(tableName, pkValue, request);
				if (inserted) {
					request.setAttribute("result", "yes");
				} else {
					request.setAttribute("result", "no");
				}
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/bjtsxm.do?method=bjtsxmJs&go=go", false);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean inserted = false;
			if ("stu".equalsIgnoreCase(userType)) {
				myForm.setXmdm(pkValue);
				inserted = service.saveBjtsxmJd(myForm, request);
			} else if ("teacher".equalsIgnoreCase(userType)) {
				shzt = "tg".equalsIgnoreCase(shzt) ? "��ͨ��" : "δͨ��";
				String[] columns = new String[] { "bzrsh", "bzrshsj" };
				String[] values = new String[] { shzt, nowTime };
				inserted = service.saveBjtsxmjdSh(pkValue, columns, values,
						request);
				// return new ActionForward("/bjtsxm.do?method=bjtsxmJs&go=go",
				// false);
			}
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			doType = "edit";
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getTsxmsbDetail(pkValue);
			HashMap<String, String> rsBzr = service.getSzbjDetail(rs
					.get("bzxh"));
			rs.put("bzrxm", rsBzr.get("bzrxm"));
			String xmsqsj = rs.get("xmsqsj");
			xmsqsj = xmsqsj.substring(0, 4) + "��" + xmsqsj.substring(4, 6)
					+ "��" + xmsqsj.substring(6, 8) + "��";
			rs.put("xmsqsj", xmsqsj);
			List<HashMap<String, String>> list = service
					.getTsxmjsjdDetail(pkValue);
			if (list != null && list.size() > 0) {
				rs.put("sbdy", list.get(0).get("sbdy"));
				for (int i = 0; i < list.size(); i++) {
					String[] sjjd = list.get(i).get("sjjd").split("-");
					rs.put("kssj" + i, sjjd[0]);
					rs.put("jssj" + i, sjjd[1]);
					rs.put("nr" + i, list.get(i).get("jbnr"));
				}
				request.setAttribute("jbnrnum", list.size());
			} else {
				if ("teacher".equalsIgnoreCase(userType)) {
					request.setAttribute("isBzr", "yes");
				}
				request.setAttribute("jbnrnum", "0");
			}

			if (service.noSh(tableName, pkValue)) {
				request.setAttribute("noSh", "yes");
			} else {
				request.setAttribute("noSh", "no");
			}
		}
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("bjtsxmJsjd");
	}

	/**
	 * �༶��ɫ��Ŀ������ȴ�ӡ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjtsxmjsjdPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = request.getParameter("pk");
		BjtsxmService service = new BjtsxmService();
		HashMap<String, String> rs = service.getTsxmsbDetail(pk);
		HashMap<String, String> rsBzr = service.getSzbjDetail(rs.get("bzxh"));
		rs.put("bzrxm", rsBzr.get("bzrxm"));
		String xmsqsj = rs.get("xmsqsj");
		xmsqsj = xmsqsj.substring(0, 4) + "��" + xmsqsj.substring(4, 6) + "��"
				+ xmsqsj.substring(6, 8) + "��";
		rs.put("xmsqsj", xmsqsj);
		List<HashMap<String, String>> list = service.getTsxmjsjdDetail(pk);
		if (list != null && list.size() > 0) {
			rs.put("sbdy", list.get(0).get("sbdy"));
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).get("sjjd") != null
						&& list.get(i).get("sjjd").length() > 0) {
					String[] sjjd = list.get(i).get("sjjd").split("-");
					String kssj = sjjd[0].substring(0, 4) + "��"
							+ sjjd[0].substring(4, 6) + "��"
							+ sjjd[0].substring(6, 8) + "��";
					String jssj = sjjd[1].substring(0, 4) + "��"
							+ sjjd[1].substring(4, 6) + "��"
							+ sjjd[1].substring(6, 8) + "��";
					rs.put("kssj" + i, kssj);
					rs.put("jssj" + i, jssj);
				}
				rs.put("nr" + i, list.get(i).get("jbnr"));
			}
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("bjtsxmjsjdPrint");
	}

	/**
	 * @describe �༶��ɫ��Ŀ��������
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward bjtsxmjsys(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_nbzy_bjtsxmsbjg";
		String realTable = "nbzy_tsbjxm_xmsb";
		String title = "˼������ - �༶��ɫ��Ŀ - ��������";

		BjtsxmService service = new BjtsxmService();
		BjtsxmForm myForm = (BjtsxmForm) form;

		// ���������ڰ༶
		// TODO
		// userName = "123";
		List bjList = service.getBzrBj(userName);
		if (bjList != null && bjList.size() > 0) {
			userType = "teacher";
		}
		// ȡ�ò�ѯ����

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

		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> topTr = null;

		String[] colList = new String[] {};

		if ("student".equalsIgnoreCase(userOnLine)) {
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "bzrshimg", "xyshimg", "xxshimg" };
		} else if ("teacher".equalsIgnoreCase(userOnLine) && bjList != null
				&& bjList.size() > 0) {
			userType = "teacher";
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "bzrshimg" };
		} else if ("xy".equalsIgnoreCase(userType)) {
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "xyshimg" };
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "xxshimg" };
		}

		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			topTr = service.getTopTr(tableName, colList);
			rs = service.getTsxmjsysList(myForm, colList, userName, userType);
			// ҳ�淵�ر�֤����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		request.setAttribute("rs", rs);
		if (rs != null && !"".equals(rs)) {
			request.setAttribute("rsNum", rs.size());
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xydm", xy);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		if ("stu".equalsIgnoreCase(userType)) {
			// �ж��Ƿ�೤
			if (!service.isBz(userName)) {
				request.setAttribute("isBz", "no");
				return mapping.findForward("bjtsxmjsys");
			}
		}

		return mapping.findForward("bjtsxmjsys");

	}

	/**
	 * @describe �༶��ɫ��Ŀ����������д
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward bjtsxmYs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");
		String shzt = request.getParameter("shzt");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String title = "˼������ - �༶��ɫ��Ŀ - ��Ŀ��������";
		String tableName = "nbzy_tsbjxm_xmys";

		BjtsxmService service = new BjtsxmService();
		BjtsxmForm myForm = (BjtsxmForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("stu".equalsIgnoreCase(userType)) {
			// �ж��Ƿ�೤
			if (service.isBz(userName)) {
				rs = service.getSzbjDetail(userName);
			}
		}
		// �ж��Ƿ������
		// TODO
		// userName = "123";
		List bjList = service.getBzrBj(userName);
		if (bjList != null && bjList.size() > 0) {
			userType = "teacher";
		}
		// ���ϵͳʱ��
		String nowTime = service.GetSysTime();
		if ("del".equalsIgnoreCase(doType)) {
			if (service.noSh(tableName, pkValue)) {
				boolean inserted = service.delxmSq(tableName, pkValue, request);
				if (inserted) {
					request.setAttribute("result", "yes");
				} else {
					request.setAttribute("result", "no");
				}
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/bjtsxm.do?method=bjtsxmjsys&go=go",
					false);
		}
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setXmdm(pkValue);
			boolean inserted = false;
			if ("stu".equalsIgnoreCase(userType)) {
				inserted = service.saveBjtsxmYs(myForm, request);
			} else if ("teacher".equalsIgnoreCase(userType)) {
				shzt = "tg".equalsIgnoreCase(shzt) ? "��ͨ��" : "δͨ��";
				String[] columns = new String[] { "bzrsh", "bzryj", "bzrshsj" };
				String[] values = new String[] { shzt,
						DealString.toGBK(myForm.getBzryj()), nowTime };
				inserted = service.saveBjtsxmSh(tableName, pkValue, columns,
						values, request);
				// return new
				// ActionForward("/bjtsxm.do?method=bjtsxmSbjg&go=go",
				// false);
			} else if ("xy".equalsIgnoreCase(userType)) {
				shzt = "tg".equalsIgnoreCase(shzt) ? "��ͨ��" : "δͨ��";
				String[] columns = new String[] { "xysh", "xyyj", "xyshr",
						"xyshsj" };
				String[] values = new String[] { shzt,
						DealString.toGBK(myForm.getXyyj()),
						DealString.toGBK(userName), nowTime };
				inserted = service.saveBjtsxmSh(tableName, pkValue, columns,
						values, request);
				// return new
				// ActionForward("/bjtsxm.do?method=bjtsxmSbjg&go=go",
				// false);
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				shzt = "tg".equalsIgnoreCase(shzt) ? "��ͨ��" : "δͨ��";
				String[] columns = new String[] { "xxsh", "xxyj", "xxshr",
						"xxshsj" };
				String[] values = new String[] { shzt,
						DealString.toGBK(myForm.getXxyj()),
						DealString.toGBK(userName), nowTime };
				inserted = service.saveBjtsxmSh(tableName, pkValue, columns,
						values, request);
				// return new
				// ActionForward("/bjtsxm.do?method=bjtsxmSbjg&go=go",
				// false);
			}
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			doType = "edit";
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getTsxmYsDetail(pkValue);
			List<HashMap<String, String>> list = service.getTsxmYsTxyj(pkValue);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					String pjzxh = list.get(i).get("pjzxh");
					String pjnr = list.get(i).get("pjnr");
					rs.put("ry" + i, pjzxh);
					rs.put("pjnr" + i, pjnr);
				}
				request.setAttribute("rynum", list.size());
			} else {
				request.setAttribute("rynum", "0");
			}
			request.setAttribute("ryList", service.getRyList(rs.get("bzxh")));

			if (service.noSh(tableName, pkValue)) {
				request.setAttribute("noSh", "yes");
			} else {
				request.setAttribute("noSh", "no");
			}
		}
		// setList(request);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("bjtsxmYs");
	}

	/**
	 * �༶��ɫ��Ŀ������ȴ�ӡ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjtsxmYsPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = request.getParameter("pk");
		BjtsxmService service = new BjtsxmService();
		HashMap<String, String> rs = service.getTsxmYsDetail(pk);
		String zcjf = rs.get("zcjf");
		if (zcjf != null && zcjf.length() > 0) {
			String[] arrZcjf = zcjf.split("!!@!!");
			if (arrZcjf[0] != null && arrZcjf[0].length() > 0) {
				String[] zcje = arrZcjf[0].split("!!@@!!");
				String[] zcxm = arrZcjf[1].split("!!@@!!");
				for (int i = 0; i < zcje.length; i++) {
					if (i == zcje.length - 1) {
						rs.put("hj", zcje[i]);
					} else {
						rs.put("zcje" + i, zcje[i]);
						rs.put("zcxm" + i, zcxm[i]);
					}
				}
			}
		}
		List<HashMap<String, String>> list = service.getTsxmYsTxyj(pk);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String pjzxh = list.get(i).get("pjzxh");
				String pjnr = list.get(i).get("pjnr");
				rs.put("ry" + i, service.getXsxm(pjzxh) + ":" + pjnr);
				// rs.put("pjnr" + i, pjnr);
			}
		}
		HashMap<String, String> rsBzr = service.getSzbjDetail(rs.get("bzxh"));
		rs.put("bzrxm", rsBzr.get("bzrxm"));
		request.setAttribute("rs", rs);
		return mapping.findForward("bjtsxmYsPrint");
	}

	/**
	 * @describe �༶��ɫ��Ŀ������������
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward bjtsxmYqys(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_nbzy_bjtsxmsbjg";
		String realTable = "nbzy_tsbjxm_xmsb";
		String title = "˼������ - �༶��ɫ��Ŀ - ��������";

		BjtsxmService service = new BjtsxmService();
		BjtsxmForm myForm = (BjtsxmForm) form;

		// ���������ڰ༶
		// TODO
		// userName = "123";
		List bjList = service.getBzrBj(userName);
		if (bjList != null && bjList.size() > 0) {
			userType = "teacher";
		}
		// ȡ�ò�ѯ����

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

		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> topTr = null;

		String[] colList = new String[] {};

		if ("student".equalsIgnoreCase(userOnLine)) {
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "bzrshimg", "xyshimg", "xxshimg" };
		} else if ("teacher".equalsIgnoreCase(userOnLine) && bjList != null
				&& bjList.size() > 0) {
			userType = "teacher";
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "bzrshimg" };
		} else if ("xy".equalsIgnoreCase(userType)) {
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "xyshimg" };
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			colList = new String[] { "xmdm", "xmmc", "bzxm", "nj", "xymc",
					"bjmc", "xmsqsj", "xxshimg" };
		}

		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			topTr = service.getTopTr(tableName, colList);
			rs = service.getTsxmYqys(myForm, colList, userName, userType);
			// ҳ�淵�ر�֤����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		request.setAttribute("rs", rs);
		if (rs != null && !"".equals(rs)) {
			request.setAttribute("rsNum", rs.size());
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xydm", xy);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		if ("stu".equalsIgnoreCase(userType)) {
			// �ж��Ƿ�೤
			if (!service.isBz(userName)) {
				request.setAttribute("isBz", "no");
			}
		}

		return mapping.findForward("bjtsxmYqys");

	}

	/**
	 * @describe �༶��ɫ��Ŀ��������������д
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward bjtsxmYqysSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");
		String shzt = request.getParameter("shzt");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String title = "˼������ - �༶��ɫ��Ŀ - ��Ŀ��������";
		String tableName = "nbzy_tsbjxm_yqys";

		BjtsxmService service = new BjtsxmService();
		BjtsxmForm myForm = (BjtsxmForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("stu".equalsIgnoreCase(userType)) {
			// �ж��Ƿ�೤
			if (service.isBz(userName)) {
				rs = service.getSzbjDetail(userName);
			}
		}
		// �ж��Ƿ������
		// TODO
		// userName = "123";
		List bjList = service.getBzrBj(userName);
		if (bjList != null && bjList.size() > 0) {
			userType = "teacher";
		}
		// ���ϵͳʱ��
		String nowTime = service.GetSysTime();
		if ("del".equalsIgnoreCase(doType)) {
			if (service.noSh(tableName, pkValue)) {
				boolean inserted = service.delxmSq(tableName, pkValue, request);
				if (inserted) {
					request.setAttribute("result", "yes");
				} else {
					request.setAttribute("result", "no");
				}
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/bjtsxm.do?method=bjtsxmYqys&go=go",
					false);
		}
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setXmdm(pkValue);
			boolean inserted = false;
			if ("stu".equalsIgnoreCase(userType)) {
				inserted = service.saveBjtsxmYqys(myForm, request);
			} else if ("teacher".equalsIgnoreCase(userType)) {
				shzt = "tg".equalsIgnoreCase(shzt) ? "��ͨ��" : "δͨ��";
				String[] columns = new String[] { "bzrsh", "bzryj", "bzrshsj" };
				String[] values = new String[] { shzt,
						DealString.toGBK(myForm.getBzryj()), nowTime };
				inserted = service.saveBjtsxmSh(tableName, pkValue, columns,
						values, request);
				// return new
				// ActionForward("/bjtsxm.do?method=bjtsxmSbjg&go=go",
				// false);
			} else if ("xy".equalsIgnoreCase(userType)) {
				shzt = "tg".equalsIgnoreCase(shzt) ? "��ͨ��" : "δͨ��";
				String[] columns = new String[] { "xysh", "xyyj", "xyshr",
						"xyshsj" };
				String[] values = new String[] { shzt,
						DealString.toGBK(myForm.getXyyj()),
						DealString.toGBK(userName), nowTime };
				inserted = service.saveBjtsxmSh(tableName, pkValue, columns,
						values, request);
				// return new
				// ActionForward("/bjtsxm.do?method=bjtsxmSbjg&go=go",
				// false);
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				shzt = "tg".equalsIgnoreCase(shzt) ? "��ͨ��" : "δͨ��";
				String[] columns = new String[] { "xxsh", "xxyj", "xxshr",
						"xxshsj" };
				String[] values = new String[] { shzt,
						DealString.toGBK(myForm.getXxyj()),
						DealString.toGBK(userName), nowTime };
				inserted = service.saveBjtsxmSh(tableName, pkValue, columns,
						values, request);
				// return new
				// ActionForward("/bjtsxm.do?method=bjtsxmSbjg&go=go",
				// false);
			}
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			doType = "edit";
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getTsxmYqysDetail(pkValue);
			if (service.noSh(tableName, pkValue)) {
				request.setAttribute("noSh", "yes");
			} else {
				request.setAttribute("noSh", "no");
			}
		}
		// setList(request);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("bjtsxmYqysSq");
	}

	/**
	 * �༶��ɫ��Ŀ������ȴ�ӡ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjtsxmYqsqPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = request.getParameter("pk");
		BjtsxmService service = new BjtsxmService();
		HashMap<String, String> rs = service.getTsxmYqysDetail(pk);
		HashMap<String, String> rsBzr = service.getSzbjDetail(rs.get("bzxh"));
		rs.put("bzrxm", rsBzr.get("bzrxm"));
		request.setAttribute("rs", rs);
		return mapping.findForward("bjtsxmYqysPrint");
	}
}
