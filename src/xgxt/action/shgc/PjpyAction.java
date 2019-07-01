package xgxt.action.shgc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.sql.CLOB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.DAO.PjpyDao;
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.SearchUtils;

public class PjpyAction extends BaseAction {

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}

	/**
	 * @describe ��ѧ����Ŀ�Ĳ�ѯ
	 * @author zhoumi
	 * @return
	 */
	public ActionForward jxjxmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		DAO dao = DAO.getInstance();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "bbdm";
		String tips = "�������� - ��������ά�� - ��ѧ����Ŀά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "pjpy_shgc_jxjbbdmb";
		String[] colList = new String[] { "����", "bbdm", "bbmc" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��

		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 0);
		String bbmc = Base.chgNull(request.getParameter("bbmc"), "", 0);

		querry.append(SearchUtils.likeSql("bbdm", bbdm));
		querry.append(SearchUtils.likeSql("bbmc", bbmc));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bbdm", bbdm);
		map.put("bbmc", bbmc);

		String sql = "select bbdm ����,a.* from pjpy_shgc_jxjbbdmb a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("jxjxmList");
	}

	/**
	 * @describe ���������õ���ѧ����Ŀ��ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward jxjxmEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select bbdm,bbmc from pjpy_shgc_jxjbbdmb where bbdm=?";
		String[] outString = new String[] { "bbdm", "bbmc" };

		if ("save".equalsIgnoreCase(act)) {
			String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
			String bbmc = Base.chgNull(request.getParameter("bbmc"), "", 1);
			String bbwbgs = Base.chgNull(request.getParameter("bbwbgs"), "", 1);
			boolean b = false;

			b = StandardOperation.update("pjpy_shgc_jxjbbdmb", new String[] {
					"bbmc", "bbwbgs" }, new String[] { bbmc, bbwbgs }, "bbdm",
					bbdm, request);
			String num = dao.getOneRs(
					"select count(*) num from pjpy_shgc_jxjbbdmb where bbdm=?",
					new String[] { bbdm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation.insert("pjpy_shgc_jxjbbdmb",
						new String[] { "bbdm", "bbmc", "bbwbgs" },
						new String[] { bbdm, bbmc, bbwbgs }, request);
			}
			if (b) {
				pkVal = bbdm;
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		CLOB clob = dao.getOneClob(
				"select bbwbgs from pjpy_shgc_jxjbbdmb where bbdm=?",
				new String[] { pkVal }, "bbwbgs");
		if (null != clob) {
			map.put("bbwbgs", clob.getSubString(1L, (int) clob.length()));
		}

		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jxjxmEdit");
	}

	/**
	 * @describe ɾ����ѧ����Ŀ
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjxmDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);

		String[] pkT = pkDel.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length*9];
		int x = 0;
		for (int i = 0; i < pkT.length; i++) {
			sqlT[x] = "delete pjpy_shgc_jxjbbxssqb where bbdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete pjpy_shgc_jxjsjb where bbdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete pjpy_shgc_jxjjeb where bbdm='"+pkT[i]+"'";
			x++;
			String[] colT = dao.getArray(
					"select zddm col from pjpy_shgc_bbzdyzd where bbdm=?",
					new String[] { pkT[i] }, "col");
			for (int j = 0; j < colT.length; j++) {
				StandardOperation.update("pjpy_shgc_jxjbbxssqb",
						"update pjpy_shgc_jxjbbxssqb set zdy" + colT[j]
								+ "='' where 1=1", request);
				String[] outV = pjpyDao.getViewComm(
						"view_pjpy_shgc_jxjbbxssqb", "zdy" + colT[j]);
				if (dao
						.runUpdateTab("alter table pjpy_shgc_jxjbbxssqb drop column zdy"
								+ colT[j])) {
					String sqlTemp = "create or replace view view_pjpy_shgc_jxjbbxssqb as select a.*,(select z.xqmc from xqdzb z where z.xqdm=a.xq) xqmc,d.bbmc,b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh from pjpy_shgc_jxjbbxssqb a,view_stu_details b,pjpy_shgc_jxjbbdmb d where a.xh=b.xh and a.bbdm=d.bbdm";
					dao.creatView(sqlTemp, outV);
					dao
							.runUpdateTab("comment on table VIEW_PJPY_SHGC_JXJBBXSSQB is '��ѧ��������Ϣ'");
				}
			}
			sqlT[x] = "delete pjpy_shgc_bbzdyzd where bbdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete pjpy_shgc_jxjbbdmb where bbdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete pjpy_shgc_bjrsb where bbdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete pjpy_shgc_zyrsb where bbdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete pjpy_shgc_xyrsb where bbdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete pjpy_shgc_rswfzb where bbdm='"+pkT[i]+"'";
			x++;
		}
		dao.runBatch(sqlT);
		return new ActionForward("/shgc_pjpy.do?method=jxjxmList&go=go", false);
	}

	/**
	 * @describe ��ѧ����Ĳ�ѯ
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjjeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "bbdm||jxjje";
		String tips = "�������� - ��������ά�� - ��ѧ����ά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "view_pjpy_shgc_jxjjeb";
		String[] colList = new String[] { "����", "bbdm", "bbmc", "jxjje" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��

		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 0);
		String bbmc = Base.chgNull(request.getParameter("bbmc"), "", 0);

		querry.append(SearchUtils.equalSql("bbdm", bbdm));
		querry.append(SearchUtils.likeSql("bbmc", bbmc));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bbdm", bbdm);
		map.put("bbmc", bbmc);

		String sql = "select bbdm||jxjje ����,a.* from view_pjpy_shgc_jxjjeb a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("jxjjeList");
	}

	/**
	 * @describe ���������õ���ѧ������ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward jxjjeEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select bbdm,bbmc,jxjje from view_pjpy_shgc_jxjjeb where bbdm||jxjje=?";
		String[] outString = new String[] { "bbdm", "bbmc", "jxjje" };

		if ("save".equalsIgnoreCase(act)) {
			String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
			String jxjje = Base.chgNull(request.getParameter("jxjje"), "", 1);
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from pjpy_shgc_jxjjeb where bbdm||jxjje=?",
							new String[] { bbdm + jxjje }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation.insert("pjpy_shgc_jxjjeb", new String[] {
						"bbdm", "jxjje" }, new String[] { bbdm, jxjje },
						request);
				if (b) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
			} else {
				request.setAttribute("have", "have");
			}
			pkVal = bbdm + jxjje;
		}

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}

		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jxjjeEdit");
	}

	/**
	 * @describe ɾ����ѧ��������
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjjeDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);

		String[] pkT = pkDel.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete pjpy_shgc_jxjjeb where bbdm||jxjje='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
		return new ActionForward("/shgc_pjpy.do?method=jxjjeList&go=go", false);
	}

	/**
	 * @describe ��ѧ������ʱ���趨
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "bbdm||xydm";
		String tips = "�������� - ��������ά�� - ��ѧ������ʱ��ά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "view_pjpy_shgc_jxjsjb";
		String[] colList = new String[] { "����", "bbmc", "xymc", "xyrs",
				"knsrs", "kssj", "jssj" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��

		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 0);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 0);
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}

		querry.append(SearchUtils.equalSql("bbdm", bbdm));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bbdm", bbdm);
		map.put("xydm", xydm);

		String sql = "select bbdm||xydm ����,a.* from view_pjpy_shgc_jxjsjb a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("jxjsjList");
	}

	/**
	 * @describe ���������õ���ѧ��ʱ��������ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward jxjsjEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// дȨ��
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select bbdm,bbmc,xydm,xymc,xyrs,knsrs,kssj,jssj from view_pjpy_shgc_jxjsjb where bbdm||xydm=?";
		String[] outString = new String[] { "bbdm", "bbmc", "xydm", "xymc",
				"xyrs", "knsrs", "kssj", "jssj" };

		if ("save".equalsIgnoreCase(act)) {
			String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
			String xydm = Base.chgNull(request.getParameter("xydm"), "", 1);
			String knsrs = Base.chgNull(request.getParameter("knsrs"), "", 1);
			String kssj = Base.chgNull(request.getParameter("kssj"), "", 1);
			String jssj = Base.chgNull(request.getParameter("jssj"), "", 1);
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from view_pjpy_shgc_jxjsjb where bbdm||xydm=?",
							new String[] { bbdm + xydm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation
						.insert("pjpy_shgc_jxjsjb", new String[] { "bbdm",
								"xydm", "knsrs", "kssj", "jssj" },
								new String[] { bbdm, xydm, knsrs, kssj, jssj },
								request);
			} else {
				b = StandardOperation.update("pjpy_shgc_jxjsjb", new String[] {
						"knsrs", "kssj", "jssj" }, new String[] { knsrs, kssj,
						jssj }, "bbdm||xydm", bbdm + xydm, request);
			}
			if (b) {
				request.setAttribute("ok", "ok");
				pkVal = bbdm + xydm;
			} else {
				request.setAttribute("ok", "no");
			}
		}

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}

		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jxjsjEdit");
	}

	/**
	 * @describe �������ý�ѧ������ʱ��
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsjPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String knsrs = "��";
		String kssj = "1900-01-01";
		String jssj = "1900-01-01";
		HashMap<String, String> map = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			knsrs = Base.chgNull(request.getParameter("knsrs"), "", 1);
			kssj = Base.chgNull(request.getParameter("kssj"), "", 1);
			jssj = Base.chgNull(request.getParameter("jssj"), "", 1);
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update pjpy_shgc_jxjsjb set knsrs='" + knsrs
						+ "',kssj='" + kssj + "',jssj='" + jssj
						+ "' where bbdm||xydm='" + pkT[i] + "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("knsrs", knsrs);
		map.put("kssj", kssj);
		map.put("jssj", jssj);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("jxjsjPlsz");
	}

	/**
	 * @describe ��ʼ����ѧ������ʱ����������
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsjcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String sfbc = Base.chgNull(request.getParameter("sfbc"), "", 1);

		String[] xyList = dao.getArray(
				"select xydm from view_xsjbxx group by xydm", new String[] {},
				"xydm");
		String[] bbList = dao.getArray(
				"select bbdm from pjpy_shgc_jxjbbdmb group by bbdm",
				new String[] {}, "bbdm");
		if ("no".equalsIgnoreCase(sfbc)) {
			StandardOperation.delete("delete pjpy_shgc_jxjsjb where 1=1",
					"pjpy_shgc_jxjsjb", request);
			dao
					.runUpdate(
							"insert into pjpy_shgc_jxjsjb(bbdm,xydm) (select a.xmdm,b.xydm from pjpy_shgc_jxjbbdmb a,(select xydm from view_xsjbxx group by xydm) b)",
							new String[] {});
		} else {
			String[] sqlT = new String[(xyList.length*bbList.length)];
			int x = 0;
			for (int i = 0; i < xyList.length; i++) {
				String xydm = xyList[i];
				for (int j = 0; j < bbList.length; j++) {
					String bbdm = bbList[j];
					String num = dao
							.getOneRs(
									"select count(*) num from pjpy_shgc_jxjsjb where bbdm=? and xydm=?",
									new String[] { bbdm, xydm }, "num");
					if ("0".equalsIgnoreCase(num)) {
						sqlT[x] = "insert into pjpy_shgc_jxjsjb(bbdm,xydm) values('"+bbdm+"','"+xydm+"')";
						x++;
					}
				}
			}
			dao.runBatch(sqlT);
		}
		request.setAttribute("endCsh", "end");
		return new ActionForward("/shgc_pjpy.do?method=jxjsjList&go=go", false);
	}

	/**
	 * @describe ��ѧ�𱨱��Զ����ֶεĲ�ѯ
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjbbzdyzdList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "zddm";
		String tips = "�������� - ��������ά�� - ��ѧ�𱨱��Զ����ֶ�ά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "view_pjpy_shgc_bbzdyzd";
		String[] colList = new String[] { "����", "bbmc", "zddm", "zdmc", "zdlx",
				"zddx", "bxwsz" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��

		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 0);

		querry.append(SearchUtils.equalSql("bbdm", bbdm));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("bbdm", bbdm);

		String sql = "select zddm ����,a.* from view_pjpy_shgc_bbzdyzd a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("jxjbbzdyzdList");
	}

	/**
	 * @describe ���������õ���ѧ���Զ����ֶ���ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward jxjbbzdyzdEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String acDo = Base.chgNull(request.getParameter("acDo"), "", 0);
		String sql = "select bbdm,bbmc,zddm,zdmc,zdlx,zddx,bxwsz from view_pjpy_shgc_bbzdyzd where zddm=?";
		String[] outString = new String[] { "bbdm", "bbmc", "zddm", "zdmc",
				"zdlx", "zddx", "bxwsz" };

		if ("save".equalsIgnoreCase(acDo)) {
			String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
			String zddm = Base.chgNull(request.getParameter("zddm"), "", 1);
			String zdmc = Base.chgNull(request.getParameter("zdmc"), "", 1);
			String zdlx = Base.chgNull(request.getParameter("zdlx"), "", 1);
			String zddx = Base.chgNull(request.getParameter("zddx"), "", 1);
			String bxwsz = Base.chgNull(request.getParameter("bxwsz"), "", 1);
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from view_pjpy_shgc_bbzdyzd where zddm=?",
							new String[] { zddm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				String sqlTemp = "alter table pjpy_shgc_jxjbbxssqb add (zdy"
						+ zddm + " varchar(" + zddx + "))";
				dao.runUpdateTab(sqlTemp);
				String[] outV = dao.getViewComm("view_pjpy_shgc_jxjbbxssqb");
				sqlTemp = "create or replace view view_pjpy_shgc_jxjbbxssqb as select a.*,(select z.xqmc from xqdzb z where z.xqdm=a.xq) xqmc,d.bbmc,b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh from pjpy_shgc_jxjbbxssqb a,view_stu_details b,pjpy_shgc_jxjbbdmb d where a.xh=b.xh and a.bbdm=d.bbdm";
				dao.creatView(sqlTemp, outV);
				sqlTemp = "comment on column pjpy_shgc_jxjbbxssqb.zdy" + zddm
						+ " is '" + zdmc + "'";
				dao.runUpdateTab(sqlTemp);
				dao
						.runUpdateTab("comment on table VIEW_PJPY_SHGC_JXJBBXSSQB is '��ѧ��������Ϣ'");
				sqlTemp = "comment on column view_pjpy_shgc_jxjbbxssqb.zdy"
						+ zddm + " is '" + zdmc + "'";
				dao.runUpdateTab(sqlTemp);
				b = StandardOperation.insert("pjpy_shgc_bbzdyzd", new String[] {
						"bbdm", "zddm", "zdmc", "zdlx", "zddx", "bxwsz" },
						new String[] { bbdm, zddm, zdmc, zdlx, zddx, bxwsz },
						request);
			} else {
				if ("add".equalsIgnoreCase(act)) {
					request.setAttribute("have", "have");
				} else {
					String sqlTemp = "comment on column pjpy_shgc_jxjbbxssqb.zdy"
							+ zddm + " is '" + zdmc + "'";
					dao.runUpdateTab(sqlTemp);
					sqlTemp = "comment on column view_pjpy_shgc_jxjbbxssqb.zdy"
							+ zddm + " is '" + zdmc + "'";
					dao.runUpdateTab(sqlTemp);
					b = StandardOperation.update("pjpy_shgc_bbzdyzd",
							new String[] { "zdmc", "zdlx", "zddx", "bxwsz" },
							new String[] { zdmc, zdlx, zddx, bxwsz }, "zddm",
							zddm, request);
					// }
				}
			}
			pkVal = zddm;
			if (b) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}

		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmList());
		request.setAttribute("rs", map);
		request.setAttribute("acDo", acDo);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jxjbbzdyzdEdit");
	}

	/**
	 * @describe ɾ����ѧ���Զ����ֶ�����
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjbbzdyzdDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String[] pkT = pkDel.split("!!splitOne!!");
		for (int i = 0; i < pkT.length; i++) {
			if (!"".equalsIgnoreCase(pkT[i])) {
				StandardOperation.update("pjpy_shgc_jxjbbxssqb",
						"update pjpy_shgc_jxjbbxssqb set zdy" + pkT[i]
								+ "='' where 1=1", request);
				String[] outV = pjpyDao.getViewComm(
						"view_pjpy_shgc_jxjbbxssqb", "zdy" + pkT[i]);
				if (dao
						.runUpdateTab("alter table pjpy_shgc_jxjbbxssqb drop column zdy"
								+ pkT[i])) {
					String sqlTemp = "create or replace view view_pjpy_shgc_jxjbbxssqb as select a.*,(select z.xqmc from xqdzb z where z.xqdm=a.xq) xqmc,d.bbmc,b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh from pjpy_shgc_jxjbbxssqb a,view_stu_details b,pjpy_shgc_jxjbbdmb d where a.xh=b.xh and a.bbdm=d.bbdm";
					dao.creatView(sqlTemp, outV);
					dao
							.runUpdateTab("comment on table VIEW_PJPY_SHGC_JXJBBXSSQB is '��ѧ��������Ϣ'");
					StandardOperation.delete("pjpy_shgc_bbzdyzd", "zddm",
							pkT[i], request);
				}
			}
		}
		return new ActionForward("/shgc_pjpy.do?method=jxjbbzdyzdList&go=go",
				false);
	}

	/**
	 * @describe ��ѧ������ҳ��
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		String act = Base.chgNull(request.getParameter("act"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String nd = Base.currNd;
		String xq = Base.currXq;
		if (null == nd || "".equalsIgnoreCase(nd)){
			nd = dao.getOneRs("select to_char(sysdate,'yyyy') nd from dual", new String[]{}, "nd");
		}
		if ("02".equalsIgnoreCase(xq)){
			xq = "01";
		} else if ("01".equalsIgnoreCase(xq)){
			xq = "02";
			nd = String.valueOf(Integer.parseInt(nd)-1);
		}

		StringBuffer sb = new StringBuffer(" xh='");
		sb.append(xh);
		sb.append("' ");
		List<Vector<String>> zdyzdList = pjpyDao.getShgcZdyzdList(bbdm);
		String zdyzdXxxx = pjpyDao.getShgcZdyzdXxxxList(bbdm);
		if(zdyzdList.size() == 0){
			request.setAttribute("isNULL", "is");
		} else {
			request.setAttribute("isNULL", "no");
		}
		request.setAttribute("zdyzdList", zdyzdList);
		request.setAttribute("zdyzdXxxx", zdyzdXxxx);
		boolean sfkns = false;
		String sfksq = "-1";
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
		} else {
			pkVal = nd + xq + bbdm + xh;
		}
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;
			sfkns = dao.isKns(xh);

			sql1 = "select a.kssj,a.jssj,a.knsrs from view_pjpy_shgc_jxjsjb a,view_xsjbxx b where a.bbdm=? and b.xh=? and a.xydm=b.xydm";
			jxjksjssj = dao.getOneRs(sql1, new String[] { bbdm, xh },
					new String[] { "kssj", "jssj", "knsrs" });
			if (jxjksjssj == null && !"save".equalsIgnoreCase(act)){
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
			} else if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0
					&& (("��".equalsIgnoreCase(jxjksjssj[2]) || ("".equalsIgnoreCase(jxjksjssj[2]))) || ("��"
							.equalsIgnoreCase(jxjksjssj[2]) && sfkns))) {// /������ʱ�䷶Χ��
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {// /ѧ����д����
					String sqly = Base.chgNull(request.getParameter("sqly"),"", 1);
					String hjqk = Base.chgNull(request.getParameter("hjqk"),"",1);
					String xslwfb = Base.chgNull(request.getParameter("xslwfb"),"",1);
					String zljs = Base.chgNull(request.getParameter("zljs"),"",1);

					sql = "select count(*) num from pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert("pjpy_shgc_jxjbbxssqb",
								new String[] { "nd", "xq", "bbdm", "xh", "sqly",
										"hjqk", "xslwfb", "zljs" },
								new String[] { nd, xq, bbdm, xh, sqly, hjqk,
										xslwfb, zljs }, request);
						if (ok) {
							if (zdyzdList.size() != 0) {
								for (Iterator<Vector<String>> it = zdyzdList.iterator(); it
										.hasNext();) {
									Vector<String> tempSr = (Vector<String>) it.next();
									String srName = "zdy" + tempSr.get(0);
									String sr = Base.chgNull(request
											.getParameter(srName), "", 1);
									sb.append(",");
									sb.append(srName);
									sb.append("='");
									sb.append(sr);
									sb.append("'");
								}
								sql = "update pjpy_shgc_jxjbbxssqb set "
										+ sb.toString()
										+ " where nd||xq||bbdm||xh='" + pkVal + "'";
								StandardOperation.update("pjpy_shgc_jxjbbxssqb", sql, request);
							}
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						sql = "select count(*) num from pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh=? and xxsh='ͨ��'";
						num = dao.getOneRs(sql, new String[] { pkVal }, "num");
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update(
									"pjpy_shgc_jxjbbxssqb",
									new String[] { "sqly", "sqsj",
											"xysh", "xyshyj", "xyshsj",
											"xypzje", "xxsh", "xxshyj",
											"xxshsj", "xxpzje", "hjqk",
											"xslwfb", "zljs" }, new String[] {
											sqly, rightNow, "δ���", "", "",
											"0", "δ���", "", "", "0", hjqk,
											xslwfb, zljs },
									"nd||xq||bbdm||xh", pkVal, request);
							if (ok) {
								if (zdyzdList.size() != 0) {
									for (Iterator<Vector<String>> it = zdyzdList.iterator(); it
											.hasNext();) {
										Vector<String> tempSr = (Vector<String>) it.next();
										String srName = "zdy" + tempSr.get(0);
										String sr = Base.chgNull(request
												.getParameter(srName), "", 1);
										sb.append(",");
										sb.append(srName);
										sb.append("='");
										sb.append(sr);
										sb.append("'");
									}
									sql = "update pjpy_shgc_jxjbbxssqb set "
											+ sb.toString()
											+ " where nd||xq||bbdm||xh='" + pkVal
											+ "'";
									StandardOperation.update("pjpy_shgc_jxjbbxssqb", sql, request);
								}
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
						} else {
							request.setAttribute("have", "have");
						}
					}
				}
			} else {// ��������ʱ�䷶Χ��
				sfksq = "-1";
				request.setAttribute("sfksq", sfksq);// ��������
			}
		} else if (!(userType.equalsIgnoreCase("stu"))) {
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
			sfkns = dao.isKns(xh);

			sql1 = "select a.knsrs from view_pjpy_shgc_jxjsjb a,view_xsjbxx b where a.bbdm=? and b.xh=? and a.xydm=b.xydm";
			String knsT = dao
					.getOneRs(sql1, new String[] { bbdm, xh }, "knsrs");
			if (("��".equalsIgnoreCase(knsT) || ("".equalsIgnoreCase(knsT)))
					|| ("��".equalsIgnoreCase(knsT) && sfkns)) {// /������ʱ�䷶Χ��
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {
					String sqly = Base.chgNull(request.getParameter("sqly"),
							"", 1);
					String hjqk = Base.chgNull(request.getParameter("hjqk"),"",1);
					String xslwfb = Base.chgNull(request.getParameter("xslwfb"),"",1);
					String zljs = Base.chgNull(request.getParameter("zljs"),"",1);

					sql = "select count(*) num from pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert("pjpy_shgc_jxjbbxssqb",
								new String[] { "nd", "xq", "bbdm", "xh", "sqly",
										"hjqk", "xslwfb", "zljs" },
								new String[] { nd, xq, bbdm, xh, sqly, hjqk,
										xslwfb, zljs }, request);
						if (ok) {
							if (zdyzdList.size() != 0) {
								for (Iterator<Vector<String>> it = zdyzdList.iterator(); it
										.hasNext();) {
									Vector<String> tempSr = (Vector<String>) it.next();
									String srName = "zdy" + tempSr.get(0);
									String sr = Base.chgNull(request
											.getParameter(srName), "", 1);
									sb.append(",");
									sb.append(srName);
									sb.append("='");
									sb.append(sr);
									sb.append("'");
								}
								sql = "update pjpy_shgc_jxjbbxssqb set "
										+ sb.toString()
										+ " where nd||xq||bbdm||xh='" + pkVal + "'";
								StandardOperation.update("pjpy_shgc_jxjbbxssqb", sql, request);
							}
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						sql = "select count(*) num from pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh=? and xxsh='ͨ��'";
						num = dao.getOneRs(sql, new String[] { pkVal }, "num");
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update(
									"pjpy_shgc_jxjbbxssqb",
									new String[] { "sqly", "sqsj",
											"xysh", "xyshyj", "xyshsj",
											"xypzje", "xxsh", "xxshyj",
											"xxshsj", "xxpzje", "hjqk",
											"xslwfb", "zljs" }, new String[] {
											sqly, rightNow, "δ���", "", "",
											"0", "δ���", "", "", "0", hjqk,
											xslwfb, zljs }, "nd||xq||bbdm||xh",
									pkVal, request);
							if (ok) {
								if (zdyzdList.size() != 0) {
									for (Iterator<Vector<String>> it = zdyzdList.iterator(); it
											.hasNext();) {
										Vector<String> tempSr = (Vector<String>) it.next();
										String srName = "zdy" + tempSr.get(0);
										String sr = Base.chgNull(request
												.getParameter(srName), "", 1);
										sb.append(",");
										sb.append(srName);
										sb.append("='");
										sb.append(sr);
										sb.append("'");
									}
									sql = "update pjpy_shgc_jxjbbxssqb set "
											+ sb.toString()
											+ " where nd||xq||bbdm||xh='" + pkVal
											+ "'";
									StandardOperation.update("pjpy_shgc_jxjbbxssqb", sql, request);
								}
								request.setAttribute("ok", "ok");
							} else {
								request.setAttribute("ok", "no");
							}
						} else {
							request.setAttribute("have", "have");
						}
					}
				}
			} else {// ��������ʱ�䷶Χ��
				sfksq = "-1";
				request.setAttribute("sfksq", sfksq);// ��������
			}
		}

		sql = "select nd,xq,xqmc,bbdm,xh,hjqk,xslwfb,zljs,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,bbmc,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,lydq,csrq,rxny,mzmc,zzmmmc,kh from view_pjpy_shgc_jxjbbxssqb where 1=2";
		outString = dao.getColumnName(sql);// �����������
		sql = "select nd,xq,xqmc,bbdm,xh,hjqk,xslwfb,zljs,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,bbmc,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,lydq,csrq,rxny,mzmc,zzmmmc,kh from view_pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh=? ";
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if ((null != xh) && sfkns) {
				sql = "select aa.xh,aa.xm,aa.xb,aa.nj,aa.xymc,aa.zymc,aa.bjmc,aa.sfzh,aa.ssbh,aa.qsdh,bb.syd,(select z.rxny from bks_xsjbxx z where z.xh=aa.xh) rxny,aa.csrq,aa.mzmc,aa.zzmmmc,aa.kh,bb.nd,bb.lxdh,bb.rxqhk,bb.jtzz,bb.yzbm,bb.jtlxdh,bb.sfyycjcshzyhd,bb.sfyysqgjzxdkhqgzx,bb.sfjq,bb.sfge,bb.sfdq,bb.sfcj,bb.sfjls,bb.sfly,bb.sfzb,bb.jtcy1_xm,bb.jtcy1_nl,bb.jtcy1_gx,bb.jtcy1_gzdw,bb.jtcy1_zy,bb.jtcy1_nsr,bb.jtcy1_jkzk,bb.jtcy2_xm,bb.jtcy2_nl,bb.jtcy2_gx,bb.jtcy2_gzdw,bb.jtcy2_zy,bb.jtcy2_nsr,bb.jtcy2_jkzk,bb.jtcy3_xm,bb.jtcy3_nl,bb.jtcy3_gx,bb.jtcy3_gzdw,bb.jtcy3_zy,bb.jtcy3_nsr,bb.jtcy3_jkzk,bb.jtcy4_xm,bb.jtcy4_nl,bb.jtcy4_gx,bb.jtcy4_gzdw,bb.jtcy4_zy,bb.jtcy4_nsr,bb.jtcy4_jkzk,bb.jtcy5_xm,bb.jtcy5_nl,bb.jtcy5_gx,bb.jtcy5_gzdw,bb.jtcy5_zy,bb.jtcy5_nsr,bb.jtcy5_jkzk,bb.jtrjnsr,bb.xszbdszqk,bb.jtzszrzhqk,bb.jtzstfywsj,bb.qtqk,bb.mzbm_txdz,bb.mzbm_yzbm,bb.mzbm_lxdh from (select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.sfzh,a.ssbh,a.qsdh,a.lydq,a.csrq,a.mzmc,a.zzmmmc,a.kh from view_stu_details a where a.xh=?) aa , (select k.xh,k.nd,k.lxdh,k.rxqhk,k.jtzz,k.yzbm,k.jtlxdh,k.sfyycjcshzyhd,k.sfyysqgjzxdkhqgzx,k.sfjq,k.sfge,k.sfdq,k.sfcj,k.sfjls,k.sfly,k.sfzb,k.jtcy1_xm,k.jtcy1_nl,k.jtcy1_gx,k.jtcy1_gzdw,k.jtcy1_zy,k.jtcy1_nsr,k.jtcy1_jkzk,k.jtcy2_xm,k.jtcy2_nl,k.jtcy2_gx,k.jtcy2_gzdw,k.jtcy2_zy,k.jtcy2_nsr,k.jtcy2_jkzk,k.jtcy3_xm,k.jtcy3_nl,k.jtcy3_gx,k.jtcy3_gzdw,k.jtcy3_zy,k.jtcy3_nsr,k.jtcy3_jkzk,k.jtcy4_xm,k.jtcy4_nl,k.jtcy4_gx,k.jtcy4_gzdw,k.jtcy4_zy,k.jtcy4_nsr,k.jtcy4_jkzk,k.jtcy5_xm,k.jtcy5_nl,k.jtcy5_gx,k.jtcy5_gzdw,k.jtcy5_zy,k.jtcy5_nsr,k.jtcy5_jkzk,k.jtrjnsr,k.xszbdszqk,k.jtzszrzhqk,k.jtzstfywsj,k.qtqk,k.mzbm_txdz,k.mzbm_yzbm,k.mzbm_lxdh,k.syd from shgc_knsxx k where k.xxsh in ('����','�ر�����','����') and k.xh=? and k.nd=?) bb where aa.xh=bb.xh";
				String[] colName = new String[] { "xh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc", "sfzh", "ssbh", "qsdh", "syd",
						"rxny", "csrq", "mzmc", "zzmmmc", "kh", "nd", "lxdh",
						"rxqhk", "jtzz", "yzbm", "jtlxdh", "sfyycjcshzyhd",
						"sfyysqgjzxdkhqgzx", "sfjq", "sfge", "sfdq", "sfcj",
						"sfjls", "sfly", "sfzb", "jtcy1_xm", "jtcy1_nl",
						"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy", "jtcy1_nsr",
						"jtcy1_jkzk", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
						"jtcy2_gzdw", "jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk",
						"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdw",
						"jtcy3_zy", "jtcy3_nsr", "jtcy3_jkzk", "jtcy4_xm",
						"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
						"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl",
						"jtcy5_gx", "jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
						"jtcy5_jkzk", "jtrjnsr", "xszbdszqk", "jtzszrzhqk",
						"jtzstfywsj", "qtqk", "mzbm_txdz", "mzbm_yzbm",
						"mzbm_lxdh" };
				String[] outVal = dao.getOneRs(sql,
						new String[] { xh, xh, nd }, colName);
				if (outVal == null) {
				} else {
					colName = new String[] { "xh", "xm", "xb", "nj", "xymc",
							"zymc", "bjmc", "sfzh", "ssbh", "qsdh", "syd",
							"rxny", "csrq", "mzmc", "zzmmmc", "kh", "nd",
							"lxdh", "rxqhk", "jtzz", "yzbm", "jtlxdh",
							"sfyycjcshzyhd", "sfyysqgjzxdkhqgzx", "sfjq",
							"sfge", "sfdq", "sfcj", "sfjls", "sfly", "sfzb",
							"jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdw",
							"jtcy1_zy", "jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm",
							"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw", "jtcy2_zy",
							"jtcy2_nsr", "jtcy2_jkzk", "jtcy3_xm", "jtcy3_nl",
							"jtcy3_gx", "jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr",
							"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
							"jtcy4_gzdw", "jtcy4_zy", "jtcy4_nsr",
							"jtcy4_jkzk", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
							"jtcy5_gzdw", "jtcy5_zy", "jtcy5_nsr",
							"jtcy5_jkzk", "jtrjnsr", "xszbdszqk", "jtzszrzhqk",
							"jtzstfywsj", "qtqk", "mzbm_txdz", "mzbm_yzbm",
							"mzbm_lxdh" };
					for (int i = 0; i < colName.length; i++) {
						if (null != outVal[i]) {
							map.put(colName[i], outVal[i]);
						} else {
							map.put(colName[i], "");
						}
					}
				}
			} else if(null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.sfzh,a.ssbh,a.qsdh,a.lydq,a.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,a.mzmc,a.zzmmmc,a.kh,'"+nd+"' nd from view_stu_details a where a.xh=?";
				String[] colN = new String[] { "xh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc", "sfzh", "ssbh", "qsdh", "lydq",
						"csrq", "rxny", "mzmc", "zzmmmc", "kh", "nd" };
				String[] outV = dao.getOneRs(sql,
						new String[] { xh }, colN);
				if (outV != null) {
					colN = new String[] { "xh", "xm", "xb", "nj", "xymc",
							"zymc", "bjmc", "sfzh", "ssbh", "qsdh", "lydq",
							"csrq", "rxny", "mzmc", "zzmmmc", "kh", "nd" };
					for (int i = 0; i < colN.length; i++) {
						if (null != outV[i]) {
							map.put(colN[i], outV[i]);
						} else {
							map.put(colN[i], "");
						}
					}
				}
			}
		} else {
			int len1 = outString.length;
			int len2 = outValue.length;
			int max = 0;
			if (len1 >= len2) {
				max = len2;
			} else {
				max = len1;
			}
			for (int i = 0; i < max; i++) {
				outString[i] = outString[i].toLowerCase();
				if (outValue[i] != null) {
					map.put(outString[i], outValue[i]);
				} else {
					map.put(outString[i], "");
				}
			}
		}
		
		if (zdyzdList.size() != 0) {
			String st = " xh";
			for(Vector<String> str : zdyzdList){
				st += ",zdy" + str.get(0);
			}
			sql = "select "+st+" from view_pjpy_shgc_jxjbbxssqb where 1=2";
			outString = dao.getColumnName(sql);// �����������
			sql = "select "+st+" from view_pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh=? ";
			outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
			if (outValue != null) {
				int len1 = outString.length;
				int len2 = outValue.length;
				int max = 0;
				if (len1 >= len2) {
					max = len2;
				} else {
					max = len1;
				}
				for (int i = 0; i < max; i++) {
					outString[i] = outString[i].toLowerCase();
					if (outValue[i] != null) {
						map.put(outString[i], outValue[i]);
					} else {
						map.put(outString[i], "");
					}
				}
			}
		}
		
		if (null == map.get("bbdm") || "".equalsIgnoreCase(map.get("bbdm"))){
			map.put("bbdm", bbdm);
		}
		if(sfkns){
			request.setAttribute("isKns", "is");
		} else {
			request.setAttribute("isKns", "no");
		}
		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jxjsq");
	}
	
	/**
	 * @describe ��ѧ�����뱨���ӡ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String xq = Base.chgNull(request.getParameter("xqmc"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String sqly = Base.chgNull(request.getParameter("sqly"), "", 1);
		String sqsj = Base.chgNull(request.getParameter("sqsj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xyshsj = Base.chgNull(request.getParameter("xyshsj"), "", 1);
		String xypzje = Base.chgNull(request.getParameter("xypzje"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		String xxshsj = Base.chgNull(request.getParameter("xxshsj"), "", 1);
		String xxpzje = Base.chgNull(request.getParameter("xxpzje"), "", 1);
		String bbmc = Base.chgNull(request.getParameter("bbmc"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String xb = Base.chgNull(request.getParameter("xb"), "", 1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xymc = Base.chgNull(request.getParameter("xymc"), "", 1);
		String zymc = Base.chgNull(request.getParameter("zymc"), "", 1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"), "", 1);
		String ssbh = Base.chgNull(request.getParameter("ssbh"), "", 1);
		String qsdh = Base.chgNull(request.getParameter("qsdh"), "", 1);
		String csrq = Base.chgNull(request.getParameter("csrq"), "", 1);
		String mzmc = Base.chgNull(request.getParameter("mzmc"), "", 1);
		String zzmmmc = Base.chgNull(request.getParameter("zzmmmc"), "", 1);
		String kh = Base.chgNull(request.getParameter("kh"), "", 1);
		String rxny = Base.chgNull(request.getParameter("rxny"), "", 1);
		String hjqk = Base.chgNull(request.getParameter("hjqk"), "", 1);
		String xslwfb = Base.chgNull(request.getParameter("xslwfb"), "", 1);
		String zljs = Base.chgNull(request.getParameter("zljs"), "", 1);
		if("δ���".equalsIgnoreCase(xysh)){
			xysh=" ";
			xyshyj=" ";
			xypzje=" ";
		}
		if("δ���".equalsIgnoreCase(xxsh)){
			xxsh=" ";
			xxshyj=" ";
			xxpzje=" ";
		}
		String sqsj_year = "";
		String sqsj_mon = "";
		String sqsj_day = "";
		String xyshsj_year = "";
		String xyshsj_mon = "";
		String xyshsj_day = "";
		String xxshsj_year = "";
		String xxshsj_mon = "";
		String xxshsj_day = "";
		if(sqsj != null && !"".equalsIgnoreCase(sqsj)){
			sqsj_year = sqsj.substring(0,4);
			sqsj_mon = sqsj.substring(5, 7);
			sqsj_day = sqsj.substring(8);
		}
		if(xyshsj != null && !"".equalsIgnoreCase(xyshsj)){
			xyshsj_year = xyshsj.substring(0,4);
			xyshsj_mon = xyshsj.substring(5, 7);
			xyshsj_day = xyshsj.substring(8);
		}
		if(xxshsj != null && !"".equalsIgnoreCase(xxshsj)){
			xxshsj_year = xxshsj.substring(0,4);
			xxshsj_mon = xxshsj.substring(5, 7);
			xxshsj_day = xxshsj.substring(8);
		}

		CLOB clob = dao.getOneClob(
				"select bbwbgs from pjpy_shgc_jxjbbdmb where bbdm=?",
				new String[] { bbdm }, "bbwbgs");
		String htmlStr = "";
		if (null != clob) {
			htmlStr = clob.getSubString(1L, (int) clob.length());
		}
		String[] outValue = new String[] { xq, nd, bbdm, xh, sqly, sqsj, xysh,
				xyshyj, xyshsj, xypzje, xxsh, xxshyj, xxshsj, xxpzje, bbmc, xm,
				xb, sfzh, nj, xymc, zymc, bjmc, ssbh, qsdh, csrq, mzmc,
				zzmmmc, kh, rxny, hjqk, xslwfb, zljs, sqsj_year, sqsj_mon,
				sqsj_day, xyshsj_year, xyshsj_mon, xyshsj_day, xxshsj_year,
				xxshsj_mon, xxshsj_day };
		String[] outString = new String[] { "$xq$", "$nd$", "$bbdm$", "$xh$",
				"$sqly$", "$sqsj$", "$xysh$", "$xyshyj$", "$xyshsj$",
				"$xypzje$", "$xxsh$", "$xxshyj$", "$xxshsj$", "$xxpzje$",
				"$bbmc$", "$xm$", "$xb$", "$sfzh$", "$nj$", "$xymc$", "$zymc$",
				"$bjmc$", "$ssbh$", "$qsdh$", "$csrq$", "$mzmc$", "$zzmmmc$",
				"$kh$", "$rxny$", "$hjqk$", "$xslwfb$", "$zljs$",
				"$sqsj_year$", "$sqsj_mon$", "$sqsj_day$", "$xyshsj_year$",
				"$xyshsj_mon$", "$xyshsj_day$", "$xxshsj_year$",
				"$xxshsj_mon$", "$xxshsj_day$" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null && !(outValue[i].equals(""))) {
				htmlStr = htmlStr.replace(outString[i], outValue[i]);
			} else {
				htmlStr = htmlStr.replace(outString[i], " ");
			}
		}

		List<Vector<String>> zdyzdList = pjpyDao.getShgcZdyzdList(bbdm);
		if (zdyzdList.size() != 0) {
			for (Iterator<Vector<String>> it = zdyzdList.iterator(); it.hasNext();) {
				Vector<String> tempSr = (Vector<String>) it.next();
				String srName = "zdy" + tempSr.get(0) + "";
				String sr = Base.chgNull(request.getParameter(srName), "", 1);
				htmlStr = htmlStr.replace("$"+srName+"$", sr);
			}
		}
		
		request.setAttribute("htmlStr", htmlStr);
		request.setAttribute("bbmc", bbmc);
		return mapping.findForward("jxjsqb");
	}

	/**
	 * @describe ��ѧ������б�
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
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
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String tips = "";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String shzt = Base.chgNull(request.getParameter("shzt"), "", 1);
		realTable = "pjpy_shgc_jxjbbxssqb";
		pk = "nd||xq||bbdm||xh";
		tableName = "view_pjpy_shgc_jxjbbxssqb";
		
		String nd = "";
		String xq = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
			xq = Base.currXq;
			if (null == nd || "".equalsIgnoreCase(nd)){
				nd = dao.getOneRs("select to_char(sysdate,'yyyy') nd from dual", new String[]{}, "nd");
			}
			if ("02".equalsIgnoreCase(xq)){
				xq = "01";
			} else if ("01".equalsIgnoreCase(xq)){
				xq = "02";
				nd = String.valueOf(Integer.parseInt(nd)-1);
			}
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
			xq = Base.chgNull(request.getParameter("xq"), "", 1);
		}
		if (!isNull(shzt)) {
			if ("xy".equalsIgnoreCase(userType)) {
				querry.append(" and xysh='");
				querry.append(shzt);
				querry.append("' ");
			} else {
				querry.append(" and xxsh='");
				querry.append(shzt);
				querry.append("' ");
			}
		}
		if (!isNull(bbdm)) {
			querry.append(" and bbdm='");
			querry.append(bbdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xq)) {
			querry.append(" and xq='");
			querry.append(xq);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "��ǰ����λ�ã��������� - ��� - ��ѧ����Ŀ���";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "��ǰ����λ�ã��������� - ���� - ��������ѯ - ��ѧ����Ŀ";
			colList = new String[] { "bgcolor", "����", "pk2", "nd", "xq", "bbmc", "xh", "xm",
					"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "xysh", "xxsh", "sqsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.bbdm pk2,a.nd,a.xqmc xq,a.bbmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a";
				request.setAttribute("isXX", "is");
			} else {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.bbdm pk2,a.nd,a.xqmc xq,a.bbmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				request.setAttribute("isXX", "no");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "����", "pk2", "nd", "xq", "bbmc", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "sqsj", "xysh", "" };
			}else{
				colList = new String[] { "bgcolor", "����", "pk2", "nd", "xq", "bbmc", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "sqsj", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.bbdm pk2,a.nd,a.xqmc xq,a.bbmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='ͨ��' order by xxsh desc) a";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				sql = "select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.bbdm pk2,a.nd,a.xqmc xq,a.bbmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("xq", xq);
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("bbdm", bbdm);
		map.put("shzt", shzt);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// ���Ͷ�дȨ��
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * @describe ��ѧ����˵�����Ϣ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jxjshXxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		XszzDao xszzDao = new XszzDao();
		String actDo = Base.chgNull(request.getParameter("actDo"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String yesNo = Base.chgNull(request.getParameter("yesNo"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		String jxjje = Base.chgNull(request.getParameter("jxjje"), "", 1);
		String now = dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') now from dual",
				new String[] {}, "now");

		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh='"+pkT+"' and xxsh<>'ͨ��'";
				} else {
					sqlT[i] = "delete pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/shgc_pjpy.do?method=jxjsh&go=go", false);
			return newFwd;
		}
		
		if ("pass".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update pjpy_shgc_jxjbbxssqb set xysh='ͨ��',xyshsj='"
							+ now
							+ "',xypzje='0' where nd||xq||bbdm||xh='"
							+ pkT
							+ "'";
				} else {
					String xypzjeT = dao
							.getOneRs(
									"select xypzje from pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh=?",
									new String[] { pkT }, "xypzje");
					sqlT[i] = "update pjpy_shgc_jxjbbxssqb set xxsh='ͨ��',xxshsj='"
							+ now
							+ "',xxpzje='"
							+ xypzjeT
							+ "' where nd||xq||bbdm||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/shgc_pjpy.do?method=jxjsh&go=go", false);
			return newFwd;
		}
		
		if ("notPass".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					String xxshT = dao
							.getOneRs(
									"select xxsh from pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh=?",
									new String[] { pkT }, "xxsh");
					if (!"ͨ��".equalsIgnoreCase(xxshT)) {
						sqlT[i] = "update pjpy_shgc_jxjbbxssqb set xysh='��ͨ��',xyshsj='"
								+ now
								+ "',xypzje='0' where nd||xq||bbdm||xh='"
								+ pkT + "'";
					}
				} else {
					sqlT[i] = "update pjpy_shgc_jxjbbxssqb set xxsh='��ͨ��',xxshsj='"
							+ now
							+ "',xxpzje='0' where nd||xq||bbdm||xh='"
							+ pkT
							+ "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/shgc_pjpy.do?method=jxjsh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				String xxshT = dao.getOneRs("select xxsh from pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh=?", new String[]{pkVal}, "xxsh");
				if("ͨ��".equalsIgnoreCase(xxshT)){
					request.setAttribute("xxshjg", "pass");
				} else {
					StandardOperation
							.update(
									"pjpy_shgc_jxjbbxssqb",
									new String[] { "xysh", "xyshyj", "xyshsj",
											"xypzje" },
									new String[] { yesNo, xyshyj, now, jxjje },
									"nd||xq||bbdm||xh", pkVal, request);
				}
			} else {
				StandardOperation.update("pjpy_shgc_jxjbbxssqb", new String[] {
						"xxsh", "xxshyj", "xxshsj", "xxpzje" }, new String[] {
						yesNo, xxshyj, now, jxjje }, "nd||xq||bbdm||xh", pkVal,
						request);
			}
		}
		realTable = "pjpy_shgc_jxjbbxssqb";
		pk = "nd||xq||bbdm||xh";
		sql = "select rxny,mzmc,zzmmmc,kh,nd,xq,xqmc,bbdm,xh,hjqk,xslwfb,zljs,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,bbmc,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,lydq,csrq from view_pjpy_shgc_jxjbbxssqb where 1=2";
		String[] outString = dao.getColumnName(sql);// �����������
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,a.rxny,a.mzmc,a.zzmmmc,a.kh,a.nd,a.xq,a.xqmc,a.bbdm,a.xh,a.hjqk,a.xslwfb,a.zljs,a.sqly,a.sqsj,a.xysh,a.xyshyj,a.xyshsj,a.xypzje,a.xxsh,a.xxshyj,a.xxshsj,a.xxpzje,a.bbmc,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.ssbh,a.qsdh,a.lydq,a.csrq,a.xysh yesNo,a.xypzje jxjje "
				+ "from view_pjpy_shgc_jxjbbxssqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,a.rxny,a.mzmc,a.zzmmmc,a.kh,a.nd,a.xqmc,a.xq,a.bbdm,a.xh,a.hjqk,a.xslwfb,a.zljs,a.sqly,a.sqsj,a.xysh,a.xyshyj,a.xyshsj,a.xypzje,a.xxsh,a.xxshyj,a.xxshsj,a.xxpzje,a.bbmc,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.ssbh,a.qsdh,a.lydq,a.csrq,a.xxsh yesNo,a.xxpzje jxjje "
				+ "from view_pjpy_shgc_jxjbbxssqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[(outString.length+3)];
		colList[0] = "pk";
		int i = 0;
		for (i = 0; i < outString.length; i++) {
			colList[i+1] = outString[i].toLowerCase();
		}
		colList[i+1] = "yesNo";
		colList[i+2] = "jxjje";
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		String xh = "";
		List<Vector<String>> zdyzdList = new ArrayList<Vector<String>>();
		for (i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("xh")) {
				xh = rs[i];
			}
			if (colList[i].equalsIgnoreCase("bbdm")) {
				
				zdyzdList = pjpyDao.getShgcZdyzdList(rs[i]);
				if (zdyzdList.size() == 0) {
					request.setAttribute("isNULL", "is");
				} else {
					request.setAttribute("isNULL", "no");
				}
				request.setAttribute("zdyzdList", zdyzdList);
				List<HashMap<String, String>> jxjjeList = pjpyDao.getShgcJxjjeList(rs[i]);
				request.setAttribute("jxjjeList", jxjjeList);
			}
			hs.put(colList[i], rs[i]);
		}

		if (zdyzdList.size() != 0) {
			String st = " xh";
			for(Vector<String> str : zdyzdList){
				st += ",zdy" + str.get(0);
			}
			sql = "select "+st+" from view_pjpy_shgc_jxjbbxssqb where 1=2";
			outString = dao.getColumnName(sql);// �����������
			sql = "select "+st+" from view_pjpy_shgc_jxjbbxssqb where nd||xq||bbdm||xh=? ";
			String[] outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
			if (outValue != null) {
				int len1 = outString.length;
				int len2 = outValue.length;
				int max = 0;
				if (len1 >= len2) {
					max = len2;
				} else {
					max = len1;
				}
				for (int j = 0; j < max; j++) {
					outValue[j] = ((outValue[j] == null) || outValue[j].equalsIgnoreCase("")) ? " "
							: outValue[j];
					outString[j] = outString[j].toLowerCase();
					hs.put(outString[j].toLowerCase(), outValue[j]);
				}
			}
		}
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("rs", hs);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			request.setAttribute("xsQtJxjjlList", xszzDao.getShgcXsQtJxjjlList(xh));
			request.setAttribute("xsYxJxjjlList", xszzDao.getShgcXsYxJxjjlList(xh));
			request.setAttribute("xsZqJxjjlList", xszzDao.getShgcXsZqJxjjlList(xh));
		} else {
			request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		}
		request.setAttribute("xszzHdjeList", xszzDao.getShgcXszzHdjeList(xh));
		request.setAttribute("zjeList", xszzDao.getShgcXshdZjeList(xh,xxdm));
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_pjpy_shgc_jxjbbxssqb");
		request.setAttribute("act", "jxjsh");
		return mapping.findForward("jxjshXxxx");
	}
	
	/**
	 * @describe ��ѧ���б���
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jxjshExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String nd = "";
		String xq = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
			xq = Base.currXq;
			if (null == nd || "".equalsIgnoreCase(nd)){
				nd = dao.getOneRs("select to_char(sysdate,'yyyy') nd from dual", new String[]{}, "nd");
			}
			if ("02".equalsIgnoreCase(xq)){
				xq = "01";
			} else if ("01".equalsIgnoreCase(xq)){
				xq = "02";
				nd = String.valueOf(Integer.parseInt(nd)-1);
			}
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
			xq = Base.chgNull(request.getParameter("xq"), "", 1);
		}
		if (!isNull(bbdm)) {
			querry.append(" and bbdm='");
			querry.append(bbdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xq)) {
			querry.append(" and xq='");
			querry.append(xq);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		sql = "select * from view_pjpy_shgc_jxjbbxssqb " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_pjpy_shgc_jxjbbxssqb where 1=2");// �����������
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_pjpy_shgc_jxjbbxssqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("jxjshExp");
	}
	
	/**
	 * @describe ��ѧ��������б�
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jxjjehz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		PjpyDao pjpyDao = new PjpyDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String rsNum = "0";// ���صļ�¼��
		String writeAble = "yes";// дȨ��
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String tips = "";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String xq = Base.chgNull(request.getParameter("xq"), "", 1);
		if (!isNull(bbdm)) {
			querry.append(" and bbdm='");
			querry.append(bbdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xq)) {
			querry.append(" and xq='");
			querry.append(xq);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		if (!isNull(xysh)) {
			querry.append(" and xysh='");
			querry.append(xysh);
			querry.append("' ");
		}
		if (!isNull(xxsh)) {
			querry.append(" and xxsh='");
			querry.append(xxsh);
			querry.append("' ");
		}
		String je = "0";
		tips = "��ǰ����λ�ã��������� - ��� - ��ѧ�������";
		String sqlT = "";
		if (userType.equalsIgnoreCase("xx")) {
			colList = new String[] { "nd", "xq", "bbmc", "xh", "xm",
					"bjmc", "nj", "sqsj", "xysh", "xxsh", "xxpzje" };
		} else{
			colList = new String[] { "nd", "xq", "bbmc", "xh", "xm",
					"bjmc", "nj", "sqsj", "xysh", "xxsh", "xypzje" };
		}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select nvl(sum(ROUND(xxpzje)),0) num from view_pjpy_shgc_jxjbbxssqb "
					+ querry.toString();
				je = dao.getOneRs(sql, new String[]{}, "num");
				sqlT = "select ' ' nd,' ' xq,' ' bbmc,' ' xh,' ' xm,' ' bjmc, ' ' nj,' ' sqsj,'�ϼ�' xysh, ' ' xxsh, '"+je+"' xxpzje from dual";
				sql = "select nd,xqmc xq,bbmc,xh,xm,bjmc,nj,sqsj,xysh,xxsh,xxpzje from view_pjpy_shgc_jxjbbxssqb "
					+ querry.toString()
					+ " order by nd,xq desc";
			} else {
				sql = "select nvl(sum(ROUND(xypzje)),0) num from view_pjpy_shgc_jxjbbxssqb "
					+ querry.toString()
					+ " and xydm='"
					+ userDep;
				je = dao.getOneRs(sql, new String[]{}, "num");
				sqlT = "select ' ' nd,' ' xq,' ' bbmc,' ' xh,' ' xm,' ' bjmc, ' ' nj,' ' sqsj,'�ϼ�' xysh, ' ' xxsh, '"+je+"' xypzje from dual";
				sql = "select nd,xqmc xq,bbmc,xh,xm,bjmc,nj,sqsj,xysh,xxsh,xypzje from view_pjpy_shgc_jxjbbxssqb "
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by nd,xq desc";
			}
		colListCN = dao.getColumnNameCN(colList, "view_pjpy_shgc_jxjbbxssqb");
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rs.addAll(dao.rsToVator(sqlT, new String[] {}, colList));
		}

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("xq", xq);
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("bbdm", bbdm);
		map.put("xysh", xysh);
		map.put("xxsh", xxsh);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// ���Ͷ�дȨ��
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("jxjxmList", pjpyDao.getShgcJxjxmList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("xyshList", xszzDao.xyshList());
		request.setAttribute("xxshList", xszzDao.xxshList());
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("jxjjehz");
	}
	
	/**
	 * @describe ��ѧ������ܵ���
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jxjjehzExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String bbdm = Base.chgNull(request.getParameter("bbdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String nd = "";
		nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String xq = Base.chgNull(request.getParameter("xq"), "", 1);
		if (!isNull(bbdm)) {
			querry.append(" and bbdm='");
			querry.append(bbdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xq)) {
			querry.append(" and xq='");
			querry.append(xq);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		if (!isNull(xysh)) {
			querry.append(" and xysh='");
			querry.append(xysh);
			querry.append("' ");
		}
		if (!isNull(xxsh)) {
			querry.append(" and xxsh='");
			querry.append(xxsh);
			querry.append("' ");
		}
		
		sql = "select * from view_pjpy_shgc_jxjbbxssqb " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_pjpy_shgc_jxjbbxssqb where 1=2");// �����������
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		sql = "select nvl(sum(ROUND(xypzje)),0) xyzje,nvl(sum(ROUND(xxpzje)),0) xxzje from view_pjpy_shgc_jxjbbxssqb " + querry.toString();
		String[] je = dao.getOneRs(sql, new String[]{}, new String[]{"xyzje","xxzje"});
		
		StringBuffer sqlBf = new StringBuffer("select '�ϼ�' nd");
		for(int i = 1; i< colList.length; i++){
			if("xypzje".equalsIgnoreCase(colList[i])){
				sqlBf.append(",'"+je[0]+"' xypzje");
			} else if("xxpzje".equalsIgnoreCase(colList[i])){
				sqlBf.append(",'"+je[1]+"' xxpzje");
			} else {
				sqlBf.append(",' ' "+colList[i].toLowerCase());
			}
		}
		sqlBf.append(" from view_pjpy_shgc_jxjbbxssqb " + querry.toString());
		rs.addAll(dao.rsToVator(sqlBf.toString(), new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_pjpy_shgc_jxjbbxssqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("jxjjehzExp");
	}
	
	/**
	 * @describe ��ѧ����������ѯ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward jxjsqjgcx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String username = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> qtjxj = new Vector<Object>();
		Vector<Object> zqjxj = new Vector<Object>();
		Vector<Object> yxjxj = new Vector<Object>();
		String qtjxjrsNum = "";
		String yxjxjrsNum = "";
		String zqjxjrsNum = "";
		List qtjxjtopTr = new ArrayList<String[]>();
		List yxjxjtopTr = new ArrayList<String[]>();
		List zqjxjtopTr = new ArrayList<String[]>();
		ActionForward myActFwd = null;

		if (!userType.equalsIgnoreCase("stu")) {
			myActFwd = new ActionForward(
					"/shgc_pjpy.do?method=jxjsh&isQuery=is", false);
			return myActFwd;
		}
		sql = "select '/xgxt/shgc_pjpy.do?method=jxjsq'||'&'||'pkVal='||nd||xq||bbdm||xh url,rownum �к�,a.* from view_pjpy_shgc_jxjbbxssqb a where xh=?";
		colList = new String[] { "url", "�к�", "nd", "xqmc", "bbmc", "xh", "xm", "xysh",
				"xypzje", "xyshyj", "xxsh", "xxpzje", "xxshyj", "sqsj" };
		colListCN = dao.getColumnNameCN(colList, "view_pjpy_shgc_jxjbbxssqb");
		qtjxjtopTr = dao.arrayToList(colList, colListCN);
		qtjxj.addAll(dao.rsToVator(sql, new String[] { username }, colList));
		
		sql = "select rownum �к�,a.* from view_shgc_pjpy_yxjxj a where xh=?";
		colList = new String[] { "�к�", "xn", "xq", "xh", "xm", "jxjdj", "jxjje" };
		colListCN = dao.getColumnNameCN(colList, "view_shgc_pjpy_yxjxj");
		yxjxjtopTr = dao.arrayToList(colList, colListCN);
		yxjxj.addAll(dao.rsToVator(sql, new String[] { username }, colList));
		
		sql = "select rownum �к�,a.* from view_shgc_pjpy_zqjxj a where xh=?";
		colList = new String[] { "�к�", "nd", "xq", "xh", "xm", "jxjdj", "jxjje" };
		colListCN = dao.getColumnNameCN(colList, "view_shgc_pjpy_zqjxj");
		zqjxjtopTr = dao.arrayToList(colList, colListCN);
		zqjxj.addAll(dao.rsToVator(sql, new String[] { username }, colList));
		if (qtjxj == null) {
			qtjxjrsNum = "0";
		} else {
			qtjxjrsNum = String.valueOf(qtjxj.size());
		}
		if (yxjxj == null) {
			yxjxjrsNum = "0";
		} else {
			yxjxjrsNum = String.valueOf(yxjxj.size());
		}
		if (zqjxj == null) {
			zqjxjrsNum = "0";
		} else {
			zqjxjrsNum = String.valueOf(zqjxj.size());
		}

		request.setAttribute("qtjxj", qtjxj);
		request.setAttribute("yxjxj", yxjxj);
		request.setAttribute("zqjxj", zqjxj);
		request.setAttribute("qtjxjtopTr", qtjxjtopTr);
		request.setAttribute("yxjxjtopTr", yxjxjtopTr);
		request.setAttribute("zqjxjtopTr", zqjxjtopTr);
		request.setAttribute("qtjxjrsNum", qtjxjrsNum);// ���ͼ�¼��
		request.setAttribute("yxjxjrsNum", yxjxjrsNum);// ���ͼ�¼��
		request.setAttribute("zqjxjrsNum", zqjxjrsNum);// ���ͼ�¼��
		return mapping.findForward("jxjsqjgcx");
	}

	/**
	 * @describe �������ǿ��ѧ����ʱ���ʼ��
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward ptjxjdrsjcsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		StandardOperation.delete("shgc_qtjxj_drsjb", new String[]{"1"}, new String[]{"1"}, request);
		
		dao.runUpdate("insert into shgc_qtjxj_drsjb(xmmc,xydm) (select '����ѧ����ѧ��' xmmc,xydm from view_njxyzybj group by xydm)",new String[] {});
		dao.runUpdate("insert into shgc_qtjxj_drsjb(xmmc,xydm) (select '��ǿ��ѧ��' xmmc,xydm from view_njxyzybj group by xydm)",new String[] {});
		return new ActionForward("/shgc_pjpy.do?method=ptjxjdrsjList&go=go", false);
	}
	
	/**
	 * @describe �������ǿ��ѧ����ʱ���趨
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward ptjxjdrsjList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "bbdm||xydm";
		String tips = "�������� - ��������ά�� - �������ǿ��ѧ����ʱ��ά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "view_shgc_qtjxj_drsjb";
		String[] colList = new String[] { "����", "xmmc", "xymc", "xyrs", "kssj", "jssj" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��
		
		String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 0);
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}
		
		querry.append(SearchUtils.equalSql("xmmc", xmmc));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmmc", xmmc);
		map.put("xydm", xydm);
		
		String sql = "select xmmc||xydm ����,a.* from view_shgc_qtjxj_drsjb a" + querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		request.setAttribute("jxjxmList", pjpyDao.getShgcQtjxjxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("ptjxjdrsjList");
	}

	/**
	 * @describe ���������õ��������ǿ��ѧ����ʱ��������ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward ptjxjdrsjEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// дȨ��
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select xmmc,xydm,xymc,xyrs,kssj,jssj from view_shgc_qtjxj_drsjb where xmmc||xydm=?";
		String[] outString = new String[] { "xmmc", "xydm", "xymc",
				"xyrs", "kssj", "jssj" };

		if ("save".equalsIgnoreCase(act)) {
			String xmmc    = Base.chgNull(request.getParameter("xmmc"), "", 1);
			String xydm    = Base.chgNull(request.getParameter("xydm"), "", 1);
			String kssj    = Base.chgNull(request.getParameter("kssj"), "", 1);
			String jssj    = Base.chgNull(request.getParameter("jssj"), "", 1);
			boolean b = false;
			
			String num = dao.getOneRs(
					"select count(*) num from view_shgc_qtjxj_drsjb where xmmc||xydm=?",
					new String[] { xmmc + xydm }, "num");
			if("0".equalsIgnoreCase(num)){
				b = StandardOperation
						.insert("shgc_qtjxj_drsjb", new String[] { "xmmc",
								"xydm", "kssj", "jssj" },
								new String[] { xmmc, xydm, kssj, jssj },
								request);
			} else {
				b = StandardOperation.update("shgc_qtjxj_drsjb", new String[] {
						"kssj", "jssj" }, new String[] { kssj,
						jssj }, "xmmc||xydm", xmmc + xydm, request);
			}
			if(b){
				request.setAttribute("ok", "ok");
				pkVal = xmmc + xydm;
			}else{
				request.setAttribute("ok", "no");
			}
		}
		
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if(outValue != null){
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("jxjxmList", pjpyDao.getShgcQtjxjxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("ptjxjdrsjEdit");
	}
	
	/**
	 * @describe ���������������ǿ��ѧ��������ʱ��
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward ptjxjdrsjPlsz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String kssj = "1900-01-01";
		String jssj = "1900-01-01";
		HashMap<String, String> map = new HashMap<String, String>();
		if("save".equalsIgnoreCase(act)){
			kssj  = Base.chgNull(request.getParameter("kssj"), "", 1);
			jssj  = Base.chgNull(request.getParameter("jssj"), "", 1);
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update shgc_qtjxj_drsjb set kssj='" + kssj
						+ "',jssj='" + jssj + "' where xmmc||xydm='" + pkT[i]
						+ "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("kssj", kssj);
		map.put("jssj", jssj);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("ptjxjdrsjPlsz");
	}

	/**
	 * @describe ���㽱ѧ������ά��
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxjxjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "xh||xn||xq";
		String tips = "�������� - ��� - ���㽱ѧ��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "view_shgc_pjpy_yxjxj";
		String[] colList = new String[] { "����", "xn", "xq", "xh", "xm", "xymc",
				"zymc", "bjmc", "bzk", "cqjb", "xspjjd", "jxjdj", "jxjje", "kh" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��

		String xydm = Base.chgNull(request.getParameter("xydm"), "", 0);
		String zydm = Base.chgNull(request.getParameter("zydm"), "", 0);
		String bjdm = Base.chgNull(request.getParameter("bjdm"), "", 0);
		String nj = Base.chgNull(request.getParameter("nj"), "", 0);
		String xn = Base.chgNull(request.getParameter("xn"), "", 0);
		String xqmc = Base.chgNull(request.getParameter("xqmc"), "", 1);
		String bzk = Base.chgNull(request.getParameter("bzk"), "", 1);
		String cqjb = Base.chgNull(request.getParameter("cqjb"), "", 1);
		String jxjdj = Base.chgNull(request.getParameter("jxjdj"), "", 1);
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}

		querry.append(SearchUtils.equalSql("xydm", xydm));
		querry.append(SearchUtils.equalSql("zydm", zydm));
		querry.append(SearchUtils.equalSql("bjdm", bjdm));
		querry.append(SearchUtils.equalSql("xn", xn));
		querry.append(SearchUtils.equalSql("xq", xqmc));
		querry.append(SearchUtils.equalSql("nj", nj));
		querry.append(SearchUtils.equalSql("bzk", bzk));
		querry.append(SearchUtils.equalSql("cqjb", cqjb));
		querry.append(SearchUtils.rightLikeSql("jxjdj", jxjdj));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xydm", xydm);
		map.put("zydm", zydm);
		map.put("bjdm", bjdm);
		map.put("xn", xn);
		map.put("xqmc", xqmc);
		map.put("nj", nj);
		map.put("bzk", bzk);
		map.put("cqjb", cqjb);
		map.put("jxjdj", jxjdj);

		String sql = "select xh||xn||xq ����,a.* from view_shgc_pjpy_yxjxj a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		xydm = xydm==null ? "":xydm;
		zydm = zydm==null ? "":zydm;
		nj = nj==null ? "":nj;
		String bjKey = xydm+"!!"+zydm+"!!"+nj;
		
		boolean drqx = false;
		if (userType.equalsIgnoreCase("xy")) {
			String rightNow = dao.getOneRs(
					"select to_char(sysdate,'yyyy-mm-dd') rightNow from dual",
					new String[] {}, "rightNow");

			String[] drsj = dao
					.getOneRs(
							"select kssj,jssj from view_shgc_qtjxj_drsjb where xmmc='����ѧ����ѧ��' and xydm=?",
							new String[] { userDep }, new String[] { "kssj",
									"jssj" });

			if ((drsj != null) && drsj[0].compareToIgnoreCase(rightNow) <= 0
					&& drsj[1].compareToIgnoreCase(rightNow) >= 0) {
				drqx = true;
			}
		}
		
		if(drqx){
			request.setAttribute("drqx", "is");
		} else {
			request.setAttribute("drqx", "no");
		}
		request.setAttribute("yxJxjdjList", pjpyDao.getShgcYxJxjdjList());
		request.setAttribute("bzkList", pjpyDao.getShgcBzkList());
		request.setAttribute("cqjbList", pjpyDao.getShgcCqjbList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xnList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xydm));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("rs1", map);
		request.setAttribute("realTable", "shgc_pjpy_yxjxj");
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("yxjxjList");
	}
	
	/**
	 * @describe ���㽱ѧ������ɾ��
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward yxjxjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String[] pkDelT = pkDel.split("!!splitOne!!");
		String[] sqlT = new String[pkDelT.length];
		for (int i = 1; i < pkDelT.length; i++) {
			String pkT = pkDelT[i];
			sqlT[i] = "delete shgc_pjpy_yxjxj where xh||xn||xq='"+pkT+"'";
		}
		dao.runBatch(sqlT);
		ActionForward newFwd = new ActionForward(
				"/shgc_pjpy.do?method=yxjxjList&go=go", false);
		return newFwd;
	}

	/**
	 * @describe ���㽱ѧ���б���
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward yxjxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 0);
		String zydm = Base.chgNull(request.getParameter("zydm"), "", 0);
		String bjdm = Base.chgNull(request.getParameter("bjdm"), "", 0);
		String nj = Base.chgNull(request.getParameter("nj"), "", 0);
		String xn = Base.chgNull(request.getParameter("xn"), "", 0);
		String xqmc = Base.chgNull(request.getParameter("xqmc"), "", 1);
		String bzk = Base.chgNull(request.getParameter("bzk"), "", 1);
		String cqjb = Base.chgNull(request.getParameter("cqjb"), "", 1);
		String jxjdj = Base.chgNull(request.getParameter("jxjdj"), "", 1);
		if (userType.equalsIgnoreCase("xy")) {
			xydm = userDep;
		}
		querry.append(SearchUtils.equalSql("xydm", xydm));
		querry.append(SearchUtils.equalSql("zydm", zydm));
		querry.append(SearchUtils.equalSql("bjdm", bjdm));
		querry.append(SearchUtils.equalSql("xn", xn));
		querry.append(SearchUtils.equalSql("xq", xqmc));
		querry.append(SearchUtils.equalSql("nj", nj));
		querry.append(SearchUtils.equalSql("bzk", bzk));
		querry.append(SearchUtils.equalSql("cqjb", cqjb));
		querry.append(SearchUtils.rightLikeSql("jxjdj", jxjdj));
		
		sql = "select * from view_shgc_pjpy_yxjxj " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_shgc_pjpy_yxjxj where 1=2");// �����������
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_shgc_pjpy_yxjxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("yxjxjExp");
	}

	/**
	 * @describe �������㽱ѧ����
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward yxjxjJeff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String je = "0";
		HashMap<String, String> map = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			je = Base.chgNull(request.getParameter("je"), "", 1);
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				String num = dao
						.getOneRs(
								"select count(*) num from SHGC_PJPY_YXJXJJE where xh||xn||xq=?",
								new String[] { pkT[i] }, "num");
				if ("0".equalsIgnoreCase(num)) {
					String[] tempPk = dao
							.getOneRs(
									"select xh,xn,xq from SHGC_PJPY_YXJXJ where xh||xn||xq=?",
									new String[] { pkT[i] }, new String[] {
											"xh", "xn", "xq" });
					if (tempPk != null) {
						sqlT[i] = "insert into SHGC_PJPY_YXJXJJE(xh,xn,xq,je) values('"
								+ tempPk[0]
								+ "','"
								+ tempPk[1]
								+ "','"
								+ tempPk[2] + "','" + je + "')";
					}
				} else {
					sqlT[i] = "update SHGC_PJPY_YXJXJJE set je='" + je
							+ "' where xh||xn||xq='" + pkT[i] + "'";
				}
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("je", je);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("yxjxjJeff");
	}
	
	/**
	 * @describe ��ǿ��ѧ������ά��
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqjxjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		PjpyDao pjpyDao = new PjpyDao();
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "xh||nd||xq";
		String tips = "�������� - ��� - ��ǿ��ѧ��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "view_shgc_pjpy_zqjxj";
		String[] colList = new String[] { "����", "nd", "xq", "xh", "xm", "xymc",
				"zymc", "bjmc", "bzk", "cqjb", "xspjjd", "sfkns", "jxjdj", "jxjje", "kh" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��

		String xydm = Base.chgNull(request.getParameter("xydm"), "", 0);
		String zydm = Base.chgNull(request.getParameter("zydm"), "", 0);
		String bjdm = Base.chgNull(request.getParameter("bjdm"), "", 0);
		String nj = Base.chgNull(request.getParameter("nj"), "", 0);
		String nd = Base.chgNull(request.getParameter("nd"), "", 0);
		String sfkns = Base.chgNull(request.getParameter("sfkns"), "", 1);
		String xqmc = Base.chgNull(request.getParameter("xqmc"), "", 1);
		String bzk = Base.chgNull(request.getParameter("bzk"), "", 1);
		String cqjb = Base.chgNull(request.getParameter("cqjb"), "", 1);
		String jxjdj = Base.chgNull(request.getParameter("jxjdj"), "", 1);
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}

		querry.append(SearchUtils.equalSql("xydm", xydm));
		querry.append(SearchUtils.equalSql("zydm", zydm));
		querry.append(SearchUtils.equalSql("bjdm", bjdm));
		querry.append(SearchUtils.equalSql("nd", nd));
		querry.append(SearchUtils.equalSql("sfkns", sfkns));
		querry.append(SearchUtils.equalSql("xq", xqmc));
		querry.append(SearchUtils.equalSql("nj", nj));
		querry.append(SearchUtils.equalSql("bzk", bzk));
		querry.append(SearchUtils.equalSql("cqjb", cqjb));
		querry.append(SearchUtils.rightLikeSql("jxjdj", jxjdj));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xydm", xydm);
		map.put("zydm", zydm);
		map.put("bjdm", bjdm);
		map.put("nd", nd);
		map.put("sfkns", sfkns);
		map.put("xqmc", xqmc);
		map.put("nj", nj);
		map.put("bzk", bzk);
		map.put("cqjb", cqjb);
		map.put("jxjdj", jxjdj);

		String sql = "select xh||nd||xq ����,a.* from view_shgc_pjpy_zqjxj a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		xydm = xydm==null ? "":xydm;
		zydm = zydm==null ? "":zydm;
		nj = nj==null ? "":nj;
		String bjKey = xydm+"!!"+zydm+"!!"+nj;
		
		boolean drqx = false;
		if (userType.equalsIgnoreCase("xy")) {
			String rightNow = dao.getOneRs(
					"select to_char(sysdate,'yyyy-mm-dd') rightNow from dual",
					new String[] {}, "rightNow");

			String[] drsj = dao
					.getOneRs(
							"select kssj,jssj from view_shgc_qtjxj_drsjb where xmmc='��ǿ��ѧ��' and xydm=?",
							new String[] { userDep }, new String[] { "kssj",
									"jssj" });

			if ((drsj != null) && drsj[0].compareToIgnoreCase(rightNow) <= 0
					&& drsj[1].compareToIgnoreCase(rightNow) >= 0) {
				drqx = true;
			}
		}
		
		if(drqx){
			request.setAttribute("drqx", "is");
		} else {
			request.setAttribute("drqx", "no");
		}
		request.setAttribute("zqJxjdjList", pjpyDao.getShgcZqJxjdjList());
		request.setAttribute("bzkList", pjpyDao.getShgcBzkList());
		request.setAttribute("cqjbList", pjpyDao.getShgcCqjbList());
		request.setAttribute("sfknsList", pjpyDao.getShgcSfknsList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xydm));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("rs1", map);
		request.setAttribute("realTable", "shgc_pjpy_zqjxj");
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("zqjxjList");
	}
	
	/**
	 * @describe ��ǿ��ѧ������ɾ��
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zqjxjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String[] pkDelT = pkDel.split("!!splitOne!!");
		String[] sqlT = new String[pkDelT.length];
		for (int i = 1; i < pkDelT.length; i++) {
			String pkT = pkDelT[i];
			sqlT[i] = "delete shgc_pjpy_zqjxj where xh||nd||xq='"+pkT+"'";
		}
		dao.runBatch(sqlT);
		ActionForward newFwd = new ActionForward(
				"/shgc_pjpy.do?method=zqjxjList&go=go", false);
		return newFwd;
	}

	/**
	 * @describe ��ǿ��ѧ���б���
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zqjxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 0);
		String zydm = Base.chgNull(request.getParameter("zydm"), "", 0);
		String bjdm = Base.chgNull(request.getParameter("bjdm"), "", 0);
		String nj = Base.chgNull(request.getParameter("nj"), "", 0);
		String nd = Base.chgNull(request.getParameter("nd"), "", 0);
		String sfkns = Base.chgNull(request.getParameter("sfkns"), "", 1);
		String xqmc = Base.chgNull(request.getParameter("xqmc"), "", 1);
		String bzk = Base.chgNull(request.getParameter("bzk"), "", 1);
		String cqjb = Base.chgNull(request.getParameter("cqjb"), "", 1);
		String jxjdj = Base.chgNull(request.getParameter("jxjdj"), "", 1);
		if (userType.equalsIgnoreCase("xy")) {
			xydm = userDep;
		}
		querry.append(SearchUtils.equalSql("xydm", xydm));
		querry.append(SearchUtils.equalSql("zydm", zydm));
		querry.append(SearchUtils.equalSql("bjdm", bjdm));
		querry.append(SearchUtils.equalSql("nd", nd));
		querry.append(SearchUtils.equalSql("sfkns", sfkns));
		querry.append(SearchUtils.equalSql("xq", xqmc));
		querry.append(SearchUtils.equalSql("nj", nj));
		querry.append(SearchUtils.equalSql("bzk", bzk));
		querry.append(SearchUtils.equalSql("cqjb", cqjb));
		querry.append(SearchUtils.rightLikeSql("jxjdj", jxjdj));
		
		sql = "select * from view_shgc_pjpy_zqjxj " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_shgc_pjpy_zqjxj where 1=2");// �����������
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_shgc_pjpy_zqjxj");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("zqjxjExp");
	}

	/**
	 * @describe ������ǿ��ѧ����
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward zqjxjJeff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String je = "0";
		HashMap<String, String> map = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			je = Base.chgNull(request.getParameter("je"), "", 1);
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				String num = dao
						.getOneRs(
								"select count(*) num from SHGC_PJPY_ZQJXJJE where xh||nd||xq=?",
								new String[] { pkT[i] }, "num");
				if ("0".equalsIgnoreCase(num)) {
					String[] tempPk = dao
							.getOneRs(
									"select xh,xn,xq from SHGC_PJPY_ZQJXJ where xh||nd||xq=?",
									new String[] { pkT[i] }, new String[] {
											"xh", "nd", "xq" });
					if (tempPk != null) {
						sqlT[i] = "insert into SHGC_PJPY_ZQJXJJE(xh,nd,xq,je) values('"
								+ tempPk[0]
								+ "','"
								+ tempPk[1]
								+ "','"
								+ tempPk[2] + "','" + je + "')";
					}
				} else {
					sqlT[i] = "update SHGC_PJPY_ZQJXJJE set je='"+je+"' where xh||nd||xq='"+pkT[i]+"'";
				}
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("je", je);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("zqjxjJeff");
	}
}
