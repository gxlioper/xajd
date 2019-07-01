/*
 * �������� 2007-11-06 zhoumi
 *
 */
package xgxt.action.szxxjsxy;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.XszzForm;
import xgxt.action.Base;
import xgxt.base.*;

/** ѧ������ */
public class XszzAction extends Action {
	StandardOperation so = new StandardOperation();

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// String userType;

		// String userDep;

		// String sUName;

		// String logMsg;
		HttpSession session = request.getSession();
		try {
			/** ���߼�� */
			int i = Base.chkTimeOut(session);
			if (i <= 2) {
				request.setAttribute("errMsg", "��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}

			// boolean isStu = true;
			// userType = session.getAttribute("userType").toString();
			// isStu = (userType.equalsIgnoreCase("stu"));
			// sUName = session.getAttribute("userName").toString();
			// userDep = session.getAttribute("userDep").toString();
			String writeAble;
			int p = -1;

			ActionForward myActFwd = null;
			String act = mapping.getParameter();
			// String power = "";
			if (act.equalsIgnoreCase("auditing_szxx_gjjxjsq")) {// ������Ϣ����ѧԺ-���ҽ�ѧ�����
				myActFwd = auditing_szxx_gjjxjsq(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_szxx_gjjxjsq_one")) {// ������Ϣ����ѧԺ-���ҽ�ѧ�𵥸����
				myActFwd = auditing_szxx_gjjxjsq_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_szxx_gjzxjsq")) {// ������Ϣ����ѧԺ-������ѧ�����
				myActFwd = auditing_szxx_gjzxjsq(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_szxx_gjzxjsq_one")) {// ������Ϣ����ѧԺ-������ѧ�𵥸����
				myActFwd = auditing_szxx_gjzxjsq_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_szxx_wszxjsq")) {// ������Ϣ����ѧԺ-������ѧ�����
				myActFwd = auditing_szxx_wszxjsq(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_szxx_wszxjsq_one")) {// ������Ϣ����ѧԺ-������ѧ�𵥸����
				myActFwd = auditing_szxx_wszxjsq_one(mapping, form, request,
						response);
			} else if (act.equalsIgnoreCase("auditing_szxx_xnzxjsq")) {// ������Ϣ����ѧԺ-У����ѧ�����
				myActFwd = auditing_szxx_xnzxjsq(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_szxx_xnzxjsq_one")) {// ������Ϣ����ѧԺ-У����ѧ�𵥸����
				myActFwd = auditing_szxx_xnzxjsq_one(mapping, form, request,
						response);
			}
			writeAble = (p == 1) ? "yes" : "no";
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "���������Թ��ϣ�" + e.toString());
			return new ActionForward("/errMsg.do", false);
		}
	}

	private ActionForward auditing_szxx_gjjxjsq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql���
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String tips = "";

		String xh = DealString.toGBK(checkForm.getXh());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "szxx_gjjxjsqb";
		pk = "xh||nd";
		tableName = "view_szxx_gjjxjsqb";
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = request.getParameter("nd");
		}
		if (isNull(nd)) {
		} else {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (isNull(xy)) {
		} else {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (isNull(zy)) {
		} else {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (isNull(bj)) {
		} else {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (isNull(xh)) {
		} else {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "��ǰ����λ�ã�ѧ������ - ��� - ���ҽ�ѧ�����";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "��ǰ����λ�ã�ѧ������ - ���� - ��������ѯ - ���ҽ�ѧ��";
			colList = new String[] { "bgcolor", "����", "pk2", "nd", "xh",
					"xm", "xb", "xymc", "zymc", "bjmc", "jxjdjmc", "jjje", "sqsj", "xysh", "xxsh"  };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.* from "
						+ tableName + " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xb||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.jxjdjdm||'##OneSpile##'||a.jxjdjmc||'##OneSpile##'||a.jjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.kh col from "
					+ tableName + " a" + querry.toString() + " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xb||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.jxjdjdm||'##OneSpile##'||a.jxjdjmc||'##OneSpile##'||a.jjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.kh col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "����", "pk2", "nd", "xh",
					"xm", "xb", "xymc", "zymc", "bjmc", "jxjdjmc", "jjje", "sqsj", ""  };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.* from "
						+ tableName + " a" + querry.toString() + " and xysh='ͨ��' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xb||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.jxjdjdm||'##OneSpile##'||a.jxjdjmc||'##OneSpile##'||a.jjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.kh col from "
					+ tableName + " a" + querry.toString() + " and xysh='ͨ��' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xb||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.jxjdjdm||'##OneSpile##'||a.jxjdjmc||'##OneSpile##'||a.jjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.kh col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##csny##OneSpile##rxny##OneSpile##mzmc##OneSpile##xb##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##nj##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_gx##OneSpile##jtcy1_gzdz##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_gx##OneSpile##jtcy2_gzdz##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_gx##OneSpile##jtcy3_gzdz##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_gx##OneSpile##jtcy4_gzdz##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_gx##OneSpile##jtcy5_gzdz##OneSpile##sqsj##OneSpile##jlxx##OneSpile##hkrs##OneSpile##jtyzsr##OneSpile##jtrjsr##OneSpile##jtsrly##OneSpile##jtzz##OneSpile##sqly##OneSpile##xyshyj##OneSpile##xxshyj##OneSpile##yzbm##OneSpile##radjthk##OneSpile##jxjdjdm##OneSpile##jxjdjmc##OneSpile##jjje##OneSpile##xysh##OneSpile##xxsh##OneSpile##kh";
		StringBuffer rsExpString = new StringBuffer("");
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rsExp.addAll(dao.rsToVator(sqlExp, new String[] {}, new String[]{"col"}));
			for(Iterator<Object> it = rsExp.iterator(); it.hasNext();){
				String[] isT = (String[])it.next();
				rsExpString.append(isT[0]);
				rsExpString.append("##TwoSpile##");
			}
		}
		request.setAttribute("colListS", colListS);
		request.setAttribute("rsExpString", rsExpString.toString());

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// ���Ͷ�дȨ��
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("act", "szxx_gjjxjsqb");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	private ActionForward auditing_szxx_gjjxjsq_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));

		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete szxx_gjjxjsqb where xh||nd='"+pkT+"' and xxsh<>'ͨ��'";
				} else {
					sqlT[i] = "delete szxx_gjjxjsqb where xh||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_szxx_gjjxjsq.do?go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("szxx_gjjxjsqb", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("szxx_gjjxjsqb", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
			}
			if (ok) {
				logMsg = "�޸�(���) szxx_gjjxjsqb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "szxx_gjjxjsqb";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,xh,nd,xm,csny,rxny,mzmc,xb,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdz,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdz,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdz,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdz,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdz,sqsj,jlxx,hkrs,jtyzsr,jtrjsr,jtsrly,jtzz,sqly,xyshyj,xxshyj,yzbm,radjthk,jxjdjdm,jxjdjmc,jjje,xysh,xxsh,kh,XYSH yesNo from view_szxx_gjjxjsqb where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,xh,nd,xm,csny,rxny,mzmc,xb,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdz,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdz,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdz,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdz,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdz,sqsj,jlxx,hkrs,jtyzsr,jtrjsr,jtsrly,jtzz,sqly,xyshyj,xxshyj,yzbm,radjthk,jxjdjdm,jxjdjmc,jjje,xysh,xxsh,kh,XXSH yesNo from view_szxx_gjjxjsqb where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "csny", "rxny", "mzmc", "xb", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "nj", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdz", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdz", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdz", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdz", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdz", "sqsj", "jlxx", "hkrs", "jtyzsr", "jtrjsr", "jtsrly", "jtzz", "sqly", "xyshyj", "xxshyj", "yzbm", "radjthk", "jxjdjdm", "jxjdjmc", "jjje", "xysh", "xxsh", "kh", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "szxx_gjjxjsqb");
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_szxx_gjzxjsq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql���
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String tips = "";

		String xh = DealString.toGBK(checkForm.getXh());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		realTable = "szxx_gjzxjsqb";
		pk = "xh||nd";
		tableName = "view_szxx_gjzxjsqb";
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = request.getParameter("nd");
		}
		if (isNull(nd)) {
		} else {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (isNull(xy)) {
		} else {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (isNull(zy)) {
		} else {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (isNull(bj)) {
		} else {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (isNull(xh)) {
		} else {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "��ǰ����λ�ã�ѧ������ - ��� - ������ѧ�����";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "��ǰ����λ�ã�ѧ������ - ���� - ��������ѯ - ������ѧ��";
			colList = new String[] { "bgcolor", "����", "pk2", "nd", "xh",
					"xm", "xb", "xymc", "zymc", "bjmc", "zmclmc", "zxjje", "sqsj", "xysh", "xxsh"  };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.* from "
						+ tableName + " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xb||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.zxjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.kh col from "
					+ tableName + " a" + querry.toString() + " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xb||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.zxjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.kh col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "����", "pk2", "nd", "xh",
					"xm", "xb", "xymc", "zymc", "bjmc", "zmclmc", "zxjje", "sqsj", ""  };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.* from "
						+ tableName + " a" + querry.toString() + " and xysh='ͨ��' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xb||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.zxjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.kh col from "
					+ tableName + " a" + querry.toString() + " and xysh='ͨ��' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.csny||'##OneSpile##'||a.rxny||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.xb||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.nj||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdz||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdz||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdz||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdz||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.jlxx||'##OneSpile##'||a.hkrs||'##OneSpile##'||a.jtyzsr||'##OneSpile##'||a.jtrjsr||'##OneSpile##'||a.jtsrly||'##OneSpile##'||a.jtzz||'##OneSpile##'||a.sqly||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.radjthk||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.zxjje||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.kh col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##nd##OneSpile##xm##OneSpile##csny##OneSpile##rxny##OneSpile##mzmc##OneSpile##xb##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##nj##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_gx##OneSpile##jtcy1_gzdz##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_gx##OneSpile##jtcy2_gzdz##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_gx##OneSpile##jtcy3_gzdz##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_gx##OneSpile##jtcy4_gzdz##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_gx##OneSpile##jtcy5_gzdz##OneSpile##sqsj##OneSpile##jlxx##OneSpile##hkrs##OneSpile##jtyzsr##OneSpile##jtrjsr##OneSpile##jtsrly##OneSpile##jtzz##OneSpile##sqly##OneSpile##xyshyj##OneSpile##xxshyj##OneSpile##yzbm##OneSpile##radjthk##OneSpile##zmcldm##OneSpile##zmclmc##OneSpile##zxjje##OneSpile##xysh##OneSpile##xxsh##OneSpile##kh";
		StringBuffer rsExpString = new StringBuffer("");
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rsExp.addAll(dao.rsToVator(sqlExp, new String[] {}, new String[]{"col"}));
			for(Iterator<Object> it = rsExp.iterator(); it.hasNext();){
				String[] isT = (String[])it.next();
				rsExpString.append(isT[0]);
				rsExpString.append("##TwoSpile##");
			}
		}
		request.setAttribute("colListS", colListS);
		request.setAttribute("rsExpString", rsExpString.toString());

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// ���Ͷ�дȨ��
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("act", "szxx_gjzxjsqb");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	private ActionForward auditing_szxx_gjzxjsq_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));

		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete szxx_gjzxjsqb where xh||nd='"+pkT+"' and xxsh<>'ͨ��'";
				} else {
					sqlT[i] = "delete szxx_gjzxjsqb where xh||nd='"+pkT+"'";
				}
				if (ok) {
					logMsg = "ɾ�� szxx_gjzxjsqb";
					Base.log(request, logMsg, sUName);
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_szxx_gjzxjsq.do?go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("szxx_gjzxjsqb", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("szxx_gjzxjsqb", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
			}
			if (ok) {
				logMsg = "�޸�(���) szxx_gjzxjsqb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "szxx_gjzxjsqb";
		pk = "xh||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,xh,nd,xm,csny,rxny,mzmc,xb,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdz,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdz,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdz,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdz,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdz,sqsj,jlxx,hkrs,jtyzsr,jtrjsr,jtsrly,jtzz,sqly,xyshyj,xxshyj,yzbm,radjthk,zmcldm,zmclmc,zxjje,xysh,xxsh,kh,XYSH yesNo from view_szxx_gjzxjsqb where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,xh,nd,xm,csny,rxny,mzmc,xb,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdz,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdz,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdz,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdz,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdz,sqsj,jlxx,hkrs,jtyzsr,jtrjsr,jtsrly,jtzz,sqly,xyshyj,xxshyj,yzbm,radjthk,zmcldm,zmclmc,zxjje,xysh,xxsh,kh,XXSH yesNo from view_szxx_gjzxjsqb where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "xh", "nd", "xm", "csny", "rxny", "mzmc", "xb", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "nj", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdz", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdz", "jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdz", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx", "jtcy4_gzdz", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx", "jtcy5_gzdz", "sqsj", "jlxx", "hkrs", "jtyzsr", "jtrjsr", "jtsrly", "jtzz", "sqly", "xyshyj", "xxshyj", "yzbm", "radjthk", "zmcldm", "zmclmc", "zxjje", "xysh", "xxsh", "kh", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "szxx_gjzxjsqb");
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_szxx_wszxjsq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		DAO dao = DAO.getInstance();
		XszzForm checkForm = (XszzForm) form;
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql���
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String tips = "";

		String xh = DealString.toGBK(checkForm.getXh());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		String bzdm = DealString.toGBK(request.getParameter("shxm"));
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		String [] cols = {"shxmdm","shxmmc"};
		shxmList = dao.getList("select zxjdm shxmdm,zxjmc shxmmc from wszxjdmb", new String[]{}, cols);
		if((bzdm == null) || ("".equalsIgnoreCase(bzdm))){
		} else {
			querry1.append(" and bzdm='");
			querry1.append(bzdm);
			querry1.append("' ");
		}
		request.setAttribute("shxmList", shxmList);
		realTable = "szxx_WSZXJSQB";
		pk = "xh||bzdm||nd";
		tableName = "view_szxx_WSZXJSQB";
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = request.getParameter("nd");
		}
		if (isNull(nd)) {
		} else {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (isNull(xy)) {
		} else {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (isNull(zy)) {
		} else {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (isNull(bj)) {
		} else {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (isNull(xh)) {
		} else {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "��ǰ����λ�ã�ѧ������ - ��� - ������ѧ�����";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "��ǰ����λ�ã�ѧ������ - ���� - ��������ѯ - ������ѧ��";
			colList = new String[] { "bgcolor", "����", "pk2", "pk3", "nd", "xn",
					"xh", "xm", "nj", "xymc", "zymc", "bjmc", "bzmc", "zmclmc",
					"wszxjje", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.bzdm pk3,a.* from "
						+ tableName + " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.qsh||'##OneSpile##'||a.qsdh||'##OneSpile##'||a.lxdzxx||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sjhm||'##OneSpile##'||a.bz||'##OneSpile##'||a.bzdm||'##OneSpile##'||a.bzmc||'##OneSpile##'||a.wszxjje||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.xn||'##OneSpile##'||a.nd||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.bysj||'##OneSpile##'||a.zynj||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jttgje||'##OneSpile##'||a.zxje||'##OneSpile##'||a.jxje||'##OneSpile##'||a.qgzxje||'##OneSpile##'||a.xnwxdkje||'##OneSpile##'||a.qtsrje||'##OneSpile##'||a.zxdkje||'##OneSpile##'||a.zxdksj||'##OneSpile##'||a.yffzxdkje||'##OneSpile##'||a.yffzxdksj||'##OneSpile##'||a.sqzzly||'##OneSpile##'||a.zzff1||'##OneSpile##'||a.zzff1qsje||'##OneSpile##'||a.xz||'##OneSpile##'||a.brcn||'##OneSpile##'||a.drshgzqk||'##OneSpile##'||a.zzff1jsje||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.jg||'##OneSpile##'||a.csny||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.kh col from "
					+ tableName + " a" + querry.toString() + " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.bzdm pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.qsh||'##OneSpile##'||a.qsdh||'##OneSpile##'||a.lxdzxx||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sjhm||'##OneSpile##'||a.bz||'##OneSpile##'||a.bzdm||'##OneSpile##'||a.bzmc||'##OneSpile##'||a.wszxjje||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.xn||'##OneSpile##'||a.nd||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.bysj||'##OneSpile##'||a.zynj||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jttgje||'##OneSpile##'||a.zxje||'##OneSpile##'||a.jxje||'##OneSpile##'||a.qgzxje||'##OneSpile##'||a.xnwxdkje||'##OneSpile##'||a.qtsrje||'##OneSpile##'||a.zxdkje||'##OneSpile##'||a.zxdksj||'##OneSpile##'||a.yffzxdkje||'##OneSpile##'||a.yffzxdksj||'##OneSpile##'||a.sqzzly||'##OneSpile##'||a.zzff1||'##OneSpile##'||a.zzff1qsje||'##OneSpile##'||a.xz||'##OneSpile##'||a.brcn||'##OneSpile##'||a.drshgzqk||'##OneSpile##'||a.zzff1jsje||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.jg||'##OneSpile##'||a.csny||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.kh col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "����", "pk2", "pk3", "nd", "xn",
					"xh", "xm", "nj", "xymc", "zymc", "bjmc", "bzmc", "zmclmc",
					"wszxjje", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.bzdm pk3,a.* from "
						+ tableName + " a" + querry.toString() + " and xysh='ͨ��' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.qsh||'##OneSpile##'||a.qsdh||'##OneSpile##'||a.lxdzxx||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sjhm||'##OneSpile##'||a.bz||'##OneSpile##'||a.bzdm||'##OneSpile##'||a.bzmc||'##OneSpile##'||a.wszxjje||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.xn||'##OneSpile##'||a.nd||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.bysj||'##OneSpile##'||a.zynj||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jttgje||'##OneSpile##'||a.zxje||'##OneSpile##'||a.jxje||'##OneSpile##'||a.qgzxje||'##OneSpile##'||a.xnwxdkje||'##OneSpile##'||a.qtsrje||'##OneSpile##'||a.zxdkje||'##OneSpile##'||a.zxdksj||'##OneSpile##'||a.yffzxdkje||'##OneSpile##'||a.yffzxdksj||'##OneSpile##'||a.sqzzly||'##OneSpile##'||a.zzff1||'##OneSpile##'||a.zzff1qsje||'##OneSpile##'||a.xz||'##OneSpile##'||a.brcn||'##OneSpile##'||a.drshgzqk||'##OneSpile##'||a.zzff1jsje||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.jg||'##OneSpile##'||a.csny||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.kh col from "
					+ tableName + " a" + querry.toString() + " and xysh='ͨ��' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.bzdm pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.qsh||'##OneSpile##'||a.qsdh||'##OneSpile##'||a.lxdzxx||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.sjhm||'##OneSpile##'||a.bz||'##OneSpile##'||a.bzdm||'##OneSpile##'||a.bzmc||'##OneSpile##'||a.wszxjje||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.xn||'##OneSpile##'||a.nd||'##OneSpile##'||a.mzmc||'##OneSpile##'||a.bysj||'##OneSpile##'||a.zynj||'##OneSpile##'||a.jtdz||'##OneSpile##'||a.yzbm||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_gx||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_gx||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_gx||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_gx||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_gx||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jttgje||'##OneSpile##'||a.zxje||'##OneSpile##'||a.jxje||'##OneSpile##'||a.qgzxje||'##OneSpile##'||a.xnwxdkje||'##OneSpile##'||a.qtsrje||'##OneSpile##'||a.zxdkje||'##OneSpile##'||a.zxdksj||'##OneSpile##'||a.yffzxdkje||'##OneSpile##'||a.yffzxdksj||'##OneSpile##'||a.sqzzly||'##OneSpile##'||a.zzff1||'##OneSpile##'||a.zzff1qsje||'##OneSpile##'||a.xz||'##OneSpile##'||a.brcn||'##OneSpile##'||a.drshgzqk||'##OneSpile##'||a.zzff1jsje||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.zzmm||'##OneSpile##'||a.jcqk||'##OneSpile##'||a.jg||'##OneSpile##'||a.csny||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.xydm||'##OneSpile##'||a.zydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.kh col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##xm##OneSpile##xb##OneSpile##nj##OneSpile##qsh##OneSpile##qsdh##OneSpile##lxdzxx##OneSpile##lxdh##OneSpile##sjhm##OneSpile##bz##OneSpile##bzdm##OneSpile##bzmc##OneSpile##wszxjje##OneSpile##zmcldm##OneSpile##zmclmc##OneSpile##xn##OneSpile##nd##OneSpile##mzmc##OneSpile##bysj##OneSpile##zynj##OneSpile##jtdz##OneSpile##yzbm##OneSpile##sfzh##OneSpile##jtcy1_xm##OneSpile##jtcy1_gx##OneSpile##jtcy1_gzdw##OneSpile##jtcy1_ysr##OneSpile##jtcy2_xm##OneSpile##jtcy2_gx##OneSpile##jtcy2_gzdw##OneSpile##jtcy2_ysr##OneSpile##jtcy3_xm##OneSpile##jtcy3_gx##OneSpile##jtcy3_gzdw##OneSpile##jtcy3_ysr##OneSpile##jtcy4_xm##OneSpile##jtcy4_gx##OneSpile##jtcy4_gzdw##OneSpile##jtcy4_ysr##OneSpile##jtcy5_xm##OneSpile##jtcy5_gx##OneSpile##jtcy5_gzdw##OneSpile##jtcy5_ysr##OneSpile##jttgje##OneSpile##zxje##OneSpile##jxje##OneSpile##qgzxje##OneSpile##xnwxdkje##OneSpile##qtsrje##OneSpile##zxdkje##OneSpile##zxdksj##OneSpile##yffzxdkje##OneSpile##yffzxdksj##OneSpile##sqzzly##OneSpile##zzff1##OneSpile##zzff1qsje##OneSpile##xz##OneSpile##brcn##OneSpile##drshgzqk##OneSpile##zzff1jsje##OneSpile##xxsh##OneSpile##xysh##OneSpile##xyshyj##OneSpile##xxshyj##OneSpile##sqsj##OneSpile##zzmm##OneSpile##jcqk##OneSpile##jg##OneSpile##csny##OneSpile##jtcy5_nl##OneSpile##jtcy4_nl##OneSpile##jtcy3_nl##OneSpile##jtcy2_nl##OneSpile##jtcy1_nl##OneSpile##bjdm##OneSpile##xydm##OneSpile##zydm##OneSpile##xymc##OneSpile##zymc##OneSpile##bjmc##OneSpile##kh";
		StringBuffer rsExpString = new StringBuffer("");
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rsExp.addAll(dao.rsToVator(sqlExp, new String[] {}, new String[]{"col"}));
			for(Iterator<Object> it = rsExp.iterator(); it.hasNext();){
				String[] isT = (String[])it.next();
				rsExpString.append(isT[0]);
				rsExpString.append("##TwoSpile##");
			}
		}
		request.setAttribute("colListS", colListS);
		request.setAttribute("rsExpString", rsExpString.toString());

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("shxm", bzdm);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// ���Ͷ�дȨ��
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("act", "szxx_WSZXJSQB");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	private ActionForward auditing_szxx_wszxjsq_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));

		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete szxx_WSZXJSQB where xh||bzdm||nd='"+pkT+"' and xxsh<>'ͨ��'";
				} else {
					sqlT[i] = "delete szxx_WSZXJSQB where xh||bzdm||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_szxx_wszxjsq.do?go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("szxx_WSZXJSQB", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||bzdm||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("szxx_WSZXJSQB", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||bzdm||nd", pkVal, request);
			}
			if (ok) {
				logMsg = "�޸�(���) szxx_WSZXJSQB";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "szxx_WSZXJSQB";
		pk = "xh||bzdm||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,xh,xm,xb,nj,qsh,qsdh,lxdzxx,lxdh,sjhm,bz,bzdm,bzmc,wszxjje,zmcldm,zmclmc,xn,nd,mzmc,bysj,zynj,jtdz,yzbm,sfzh,jtcy1_xm,jtcy1_gx,jtcy1_gzdw,jtcy1_ysr,jtcy2_xm,jtcy2_gx,jtcy2_gzdw,jtcy2_ysr,jtcy3_xm,jtcy3_gx,jtcy3_gzdw,jtcy3_ysr,jtcy4_xm,jtcy4_gx,jtcy4_gzdw,jtcy4_ysr,jtcy5_xm,jtcy5_gx,jtcy5_gzdw,jtcy5_ysr,jttgje,zxje,jxje,qgzxje,xnwxdkje,qtsrje,zxdkje,zxdksj,yffzxdkje,yffzxdksj,sqzzly,zzff1,zzff1qsje,xz,brcn,drshgzqk,zzff1jsje,xxsh,xysh,xyshyj,xxshyj,sqsj,zzmm,jcqk,jg,csny,jtcy5_nl,jtcy4_nl,jtcy3_nl,jtcy2_nl,jtcy1_nl,bjdm,xydm,zydm,xymc,zymc,bjmc,kh,XYSH yesNo from view_szxx_WSZXJSQB where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,xh,xm,xb,nj,qsh,qsdh,lxdzxx,lxdh,sjhm,bz,bzdm,bzmc,wszxjje,zmcldm,zmclmc,xn,nd,mzmc,bysj,zynj,jtdz,yzbm,sfzh,jtcy1_xm,jtcy1_gx,jtcy1_gzdw,jtcy1_ysr,jtcy2_xm,jtcy2_gx,jtcy2_gzdw,jtcy2_ysr,jtcy3_xm,jtcy3_gx,jtcy3_gzdw,jtcy3_ysr,jtcy4_xm,jtcy4_gx,jtcy4_gzdw,jtcy4_ysr,jtcy5_xm,jtcy5_gx,jtcy5_gzdw,jtcy5_ysr,jttgje,zxje,jxje,qgzxje,xnwxdkje,qtsrje,zxdkje,zxdksj,yffzxdkje,yffzxdksj,sqzzly,zzff1,zzff1qsje,xz,brcn,drshgzqk,zzff1jsje,xxsh,xysh,xyshyj,xxshyj,sqsj,zzmm,jcqk,jg,csny,jtcy5_nl,jtcy4_nl,jtcy3_nl,jtcy2_nl,jtcy1_nl,bjdm,xydm,zydm,xymc,zymc,bjmc,kh,XYSH yesNo from view_szxx_WSZXJSQB where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk","xh", "xm", "xb", "nj", "qsh", "qsdh", "lxdzxx", "lxdh", "sjhm", "bz", "bzdm", "bzmc", "wszxjje", "zmcldm", "zmclmc", "xn", "nd", "mzmc", "bysj", "zynj", "jtdz", "yzbm", "sfzh", "jtcy1_xm", "jtcy1_gx", "jtcy1_gzdw", "jtcy1_ysr", "jtcy2_xm", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_ysr", "jtcy3_xm", "jtcy3_gx", "jtcy3_gzdw", "jtcy3_ysr", "jtcy4_xm", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_ysr", "jtcy5_xm", "jtcy5_gx", "jtcy5_gzdw", "jtcy5_ysr", "jttgje", "zxje", "jxje", "qgzxje", "xnwxdkje", "qtsrje", "zxdkje", "zxdksj", "yffzxdkje", "yffzxdksj", "sqzzly", "zzff1", "zzff1qsje", "xz", "brcn", "drshgzqk", "zzff1jsje", "xxsh", "xysh", "xyshyj", "xxshyj", "sqsj", "zzmm", "jcqk", "jg", "csny", "jtcy5_nl", "jtcy4_nl", "jtcy3_nl", "jtcy2_nl", "jtcy1_nl", "bjdm", "xydm", "zydm", "xymc", "zymc", "bjmc", "kh","yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "szxx_gjzxjsqb");
		return mapping.findForward("success");
	}
	
	private ActionForward auditing_szxx_xnzxjsq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		XszzForm checkForm = (XszzForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql���
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String tips = "";

		String xh = DealString.toGBK(checkForm.getXh());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		String bzdm = DealString.toGBK(request.getParameter("shxm"));
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		String [] cols = {"shxmdm","shxmmc"};
		shxmList = dao.getList("select dm shxmdm,mc shxmmc from xnzxjdmb", new String[]{}, cols);
		if ((bzdm == null) || ("".equalsIgnoreCase(bzdm))) {
		} else {
			querry1.append(" and xmdm='");
			querry1.append(bzdm);
			querry1.append("' ");
		}
		request.setAttribute("shxmList", shxmList);
		realTable = "szxx_xnzxjsqb";
		pk = "xh||xmdm||nd";
		tableName = "view_szxx_xnzxjsqb";
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = request.getParameter("nd");
		}
		if (isNull(nd)) {
		} else {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (isNull(xy)) {
		} else {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (isNull(zy)) {
		} else {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (isNull(bj)) {
		} else {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (isNull(xh)) {
		} else {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "��ǰ����λ�ã�ѧ������ - ��� - У����ѧ�����";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "��ǰ����λ�ã�ѧ������ - ���� - ��������ѯ - У����ѧ��";
			colList = new String[] { "bgcolor", "����", "pk2", "pk3", "nd", "nd", "xh", "xmmc", "xm", 
					"nj", "xymc", "zymc", "bjmc", "zxjje", "sqsj", "xysh", "xxsh" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.xmdm pk3,a.* from "
						+ tableName + " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select a.kh||'##OneSpile##'||a.xh||'##OneSpile##'||a.xmdm||'##OneSpile##'||a.xmmc||'##OneSpile##'||a.zxjje||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.bz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj col from "
					+ tableName + " a" + querry.toString() + " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.xmdm pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xxsh desc) a";
				sqlExp = "select a.kh||'##OneSpile##'||a.xh||'##OneSpile##'||a.xmdm||'##OneSpile##'||a.xmmc||'##OneSpile##'||a.zxjje||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.bz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			colList = new String[] { "bgcolor", "����", "pk2", "pk3", "nd", "nd", "xh", "xmmc", "xm", 
					"nj", "xymc", "zymc", "bjmc", "zxjje", "sqsj", "" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.xmdm pk3,a.* from "
						+ tableName + " a" + querry.toString() + " and xysh='ͨ��' order by xxsh desc) a";
				sqlExp = "select a.kh||'##OneSpile##'||a.xh||'##OneSpile##'||a.xmdm||'##OneSpile##'||a.xmmc||'##OneSpile##'||a.zxjje||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.bz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj col from "
					+ tableName + " a" + querry.toString() + " and xysh='ͨ��' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " ����,a.xh pk2,a.xmdm pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select a.kh||'##OneSpile##'||a.xh||'##OneSpile##'||a.xmdm||'##OneSpile##'||a.xmmc||'##OneSpile##'||a.zxjje||'##OneSpile##'||a.nd||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc||'##OneSpile##'||a.zmcldm||'##OneSpile##'||a.zmclmc||'##OneSpile##'||a.bz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "kh##OneSpile##xh##OneSpile##xmdm##OneSpile##xmmc##OneSpile##zxjje##OneSpile##nd##OneSpile##xm##OneSpile##xb##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc##OneSpile##zmcldm##OneSpile##zmclmc##OneSpile##bz##OneSpile##sqsj##OneSpile##xysh##OneSpile##xxsh##OneSpile##xyshyj##OneSpile##xxshyj";
		StringBuffer rsExpString = new StringBuffer("");
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rsExp.addAll(dao.rsToVator(sqlExp, new String[] {}, new String[]{"col"}));
			for(Iterator<Object> it = rsExp.iterator(); it.hasNext();){
				String[] isT = (String[])it.next();
				rsExpString.append(isT[0]);
				rsExpString.append("##TwoSpile##");
			}
		}
		request.setAttribute("colListS", colListS);
		request.setAttribute("rsExpString", rsExpString.toString());

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("shxm", bzdm);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// ���Ͷ�дȨ��
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("act", "szxx_xnzxjsqb");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	private ActionForward auditing_szxx_xnzxjsq_one(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));

		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete szxx_xnzxjsqb where xh||xmdm||nd='"+pkT+"' and xxsh<>'ͨ��'";
				} else {
					sqlT[i] = "delete szxx_xnzxjsqb where xh||xmdm||nd='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/auditing_szxx_xnzxjsq.do?go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("szxx_xnzxjsqb", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||xmdm||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("szxx_xnzxjsqb", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||xmdm||nd", pkVal, request);
			}
			if (ok) {
				logMsg = "�޸�(���) szxx_xnzxjsqb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "szxx_xnzxjsqb";
		pk = "xh||xmdm||nd";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,xh,xmdm,xmmc,zxjje,nd,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,zmcldm,zmclmc,bz,sqsj,xysh,xxsh,xyshyj,xxshyj,kh,XYSH yesNo from view_szxx_xnzxjsqb where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,xh,xmdm,xmmc,zxjje,nd,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,zmcldm,zmclmc,bz,sqsj,xysh,xxsh,xyshyj,xxshyj,kh,XYSH yesNo from view_szxx_xnzxjsqb where "
				+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk","xh", "xmdm", "xmmc", "zxjje", "nd", "xm", "xb", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "zmcldm", "zmclmc", "bz", "sqsj", "xysh", "xxsh", "xyshyj", "xxshyj", "kh","yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "szxx_xnzxjsqb");
		return mapping.findForward("success");
	}
}
