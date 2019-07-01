/*
 * �������� 2007-11-06 zhoumi
 *
 */
package xgxt.action.bjly;

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
			if (act.equalsIgnoreCase("auditing_bjly_kns")) {// ������ҵ��ѧ-���������
				myActFwd = auditing_bjly_kns(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("auditing_bjly_kns_one")) {// ������ҵ��ѧ-�������������
				myActFwd = auditing_bjly_kns_one(mapping, form, request,
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

	private ActionForward auditing_bjly_kns(ActionMapping mapping,
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
		sql = "select dqxn,dqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn",
		"dqnd" });
//		String xn = colList[0];

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
		realTable = "bjlydx_knssqb";
		pk = "xh||nd";
		tableName = "view_bjlydx_knssqb";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = colList[1];
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
		tips = "��ǰ����λ�ã�ѧ������ - ��� - ���������";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "��ǰ����λ�ã�ѧ������ - ���� - ��������ѯ - ������";
			colList = new String[] { "bgcolor", "����", "pk2", "nd", "xh", "xm",
					"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "xysh", "xxsh", "sqsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_sfzh||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_sfzh||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_sfzh||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_sfzh||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_sfzh||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.sqyy||'##OneSpile##'||a.bz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc";
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
					+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_sfzh||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_sfzh||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_sfzh||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_sfzh||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_sfzh||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.sqyy||'##OneSpile##'||a.bz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "����", "pk2", "nd", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "xysh", "sqsj", "" };
			}else{
				colList = new String[] { "bgcolor", "����", "pk2", "nd", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "sqsj", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh like '%��%' order by xxsh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_sfzh||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_sfzh||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_sfzh||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_sfzh||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_sfzh||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.sqyy||'##OneSpile##'||a.bz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh like '%��%' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'δ���') like '%��%' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.xh pk2,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				sqlExp = "select a.xh||'##OneSpile##'||a.nd||'##OneSpile##'||a.lxdh||'##OneSpile##'||a.jtcy1_xm||'##OneSpile##'||a.jtcy1_cw||'##OneSpile##'||a.jtcy1_nl||'##OneSpile##'||a.jtcy1_sfzh||'##OneSpile##'||a.jtcy1_gzdw||'##OneSpile##'||a.jtcy1_ysr||'##OneSpile##'||a.jtcy1_jkzk||'##OneSpile##'||a.jtcy2_xm||'##OneSpile##'||a.jtcy2_cw||'##OneSpile##'||a.jtcy2_nl||'##OneSpile##'||a.jtcy2_sfzh||'##OneSpile##'||a.jtcy2_gzdw||'##OneSpile##'||a.jtcy2_ysr||'##OneSpile##'||a.jtcy2_jkzk||'##OneSpile##'||a.jtcy3_xm||'##OneSpile##'||a.jtcy3_cw||'##OneSpile##'||a.jtcy3_nl||'##OneSpile##'||a.jtcy3_sfzh||'##OneSpile##'||a.jtcy3_gzdw||'##OneSpile##'||a.jtcy3_ysr||'##OneSpile##'||a.jtcy3_jkzk||'##OneSpile##'||a.jtcy4_xm||'##OneSpile##'||a.jtcy4_cw||'##OneSpile##'||a.jtcy4_nl||'##OneSpile##'||a.jtcy4_sfzh||'##OneSpile##'||a.jtcy4_gzdw||'##OneSpile##'||a.jtcy4_ysr||'##OneSpile##'||a.jtcy4_jkzk||'##OneSpile##'||a.jtcy5_xm||'##OneSpile##'||a.jtcy5_cw||'##OneSpile##'||a.jtcy5_nl||'##OneSpile##'||a.jtcy5_sfzh||'##OneSpile##'||a.jtcy5_gzdw||'##OneSpile##'||a.jtcy5_ysr||'##OneSpile##'||a.jtcy5_jkzk||'##OneSpile##'||a.sqyy||'##OneSpile##'||a.bz||'##OneSpile##'||a.sqsj||'##OneSpile##'||a.xysh||'##OneSpile##'||a.xxsh||'##OneSpile##'||a.xyshyj||'##OneSpile##'||a.xxshyj||'##OneSpile##'||a.xm||'##OneSpile##'||a.xb||'##OneSpile##'||a.sfzh||'##OneSpile##'||a.nj||'##OneSpile##'||a.xydm||'##OneSpile##'||a.xymc||'##OneSpile##'||a.zydm||'##OneSpile##'||a.zymc||'##OneSpile##'||a.bjdm||'##OneSpile##'||a.bjmc col from "
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
		String colListS = "xh##OneSpile##nd##OneSpile##lxdh##OneSpile##jtcy1_xm##OneSpile##jtcy1_cw##OneSpile##jtcy1_nl##OneSpile##jtcy1_sfzh##OneSpile##jtcy1_gzdw##OneSpile##jtcy1_ysr##OneSpile##jtcy1_jkzk##OneSpile##jtcy2_xm##OneSpile##jtcy2_cw##OneSpile##jtcy2_nl##OneSpile##jtcy2_sfzh##OneSpile##jtcy2_gzdw##OneSpile##jtcy2_ysr##OneSpile##jtcy2_jkzk##OneSpile##jtcy3_xm##OneSpile##jtcy3_cw##OneSpile##jtcy3_nl##OneSpile##jtcy3_sfzh##OneSpile##jtcy3_gzdw##OneSpile##jtcy3_ysr##OneSpile##jtcy3_jkzk##OneSpile##jtcy4_xm##OneSpile##jtcy4_cw##OneSpile##jtcy4_nl##OneSpile##jtcy4_sfzh##OneSpile##jtcy4_gzdw##OneSpile##jtcy4_ysr##OneSpile##jtcy4_jkzk##OneSpile##jtcy5_xm##OneSpile##jtcy5_cw##OneSpile##jtcy5_nl##OneSpile##jtcy5_sfzh##OneSpile##jtcy5_gzdw##OneSpile##jtcy5_ysr##OneSpile##jtcy5_jkzk##OneSpile##sqyy##OneSpile##bz##OneSpile##sqsj##OneSpile##xysh##OneSpile##xxsh##OneSpile##xyshyj##OneSpile##xxshyj##OneSpile##xm##OneSpile##xb##OneSpile##sfzh##OneSpile##nj##OneSpile##xydm##OneSpile##xymc##OneSpile##zydm##OneSpile##zymc##OneSpile##bjdm##OneSpile##bjmc";
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
		request.setAttribute("act", "xsjxjzxjsqb");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	private ActionForward auditing_bjly_kns_one(ActionMapping mapping,
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

		sql = "select count(*) con from bjlydx_knssrly";
		String con = dao.getOneRs(sql, new String[]{}, "con");
		boolean isNULL = false;
		if(con.equalsIgnoreCase("0")){
			isNULL = true;
			con = "1";
		}else{
			con = String.valueOf(Integer.parseInt(con)+1);
		}
		if(isNULL){
			request.setAttribute("isNULL", "is");
		}else{
			request.setAttribute("isNULL", "no");
		}
		request.setAttribute("con", con);
		List<String[]> srlyList = dao.getSrlyList();
		request.setAttribute("srlyList", srlyList);

		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");

			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					ok = StandardOperation.delete("bjlydx_knssqb", "xh||nd", pkT,
							"xxsh", new String[] { "ͨ��" }, request);
				} else {
					ok = StandardOperation.delete("bjlydx_knssqb", "xh||nd", pkT,
							request);
				}
				if (ok) {
					logMsg = "ɾ�� bjlydx_knssqb";
					Base.log(request, logMsg, sUName);
				}
			}
			ActionForward newFwd = new ActionForward(
					"/auditing_bjly_kns.do?go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("bjlydx_knssqb", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"xh||nd", pkVal, request);
			} else {
				ok = StandardOperation.update("bjlydx_knssqb", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"xh||nd", pkVal, request);
			}
			if (ok) {
				logMsg = "�޸�(���) bjlydx_knssqb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "bjlydx_knssqb";
		pk = "xh||nd";
		sql = "select * from view_bjlydx_knssqb where 1=2";
		String[] outString = dao.getColumnName(sql);// �����������
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,a.*,a.xysh yesNo "
				+ "from view_bjlydx_knssqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,a.*,a.xxsh yesNo "
				+ "from view_bjlydx_knssqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
			request.setAttribute("xx", 1);
		}
		colList = new String[(outString.length+2)];
		colList[0] = "pk";
		int i = 0;
		for (i = 0; i < outString.length; i++) {
			colList[i+1] = outString[i].toLowerCase();
		}
		colList[i+1] = "yesNo";
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}

		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		if (userType.equalsIgnoreCase("xy")) {
			request.setAttribute("chkList", dao.getChkList(5));
		} else {
			request.setAttribute("chkList", dao.getChkList(3));
		}
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "xnmz_knssq");
		request.setAttribute("act", "lstdsq");
		return mapping.findForward("success");
	}
}
