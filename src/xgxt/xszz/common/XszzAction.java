package xgxt.xszz.common;

import java.io.OutputStream;
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
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.SearchUtils;

public class XszzAction extends BaseAction {

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}
	
	private void generateList(ArrayList<HashMap<String, String>> list,String[] col,String[] colCn){
		for(int i=0;i<col.length;i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en",col[i]);
			map.put("cn", colCn[i]);
			list.add(map);
		}
	}

	/**
	 * @describe ������Ŀ�Ĳ�ѯ
	 * @author zhoumi
	 * @return
	 */
	public ActionForward data_zzxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		DAO dao = DAO.getInstance();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "xmdm";
		String tips = "ѧ������ - ��������ά�� - ������Ŀά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "xszz_common_new_zzxmdmb";
		String[] colList = new String[] { "����", "xmdm", "xmmc" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��

		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);

		querry.append(SearchUtils.likeSql("xmdm", xmdm));
		querry.append(SearchUtils.likeSql("xmmc", xmmc));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);
		map.put("xmmc", xmmc);

		String sql = "select xmdm ����,a.* from xszz_common_new_zzxmdmb a"
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
		return mapping.findForward("data_zzxm");
	}

	/**
	 * @describe ���������õ�������Ŀ��ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward zzxmEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String doit = Base.chgNull(request.getParameter("doit"), "", 0);
		String sql = "select xmdm,xmmc from xszz_common_new_zzxmdmb where xmdm=?";
		String[] outString = new String[] { "xmdm", "xmmc" };

		if ("save".equalsIgnoreCase(act)) {
			String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
			String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);
			boolean b = false;

			if ("add".equalsIgnoreCase(doit)) {
				String num = dao
						.getOneRs(
								"select count(*) num from xszz_common_new_zzxmdmb where xmdm=?",
								new String[] { xmdm }, "num");
				if ("0".equalsIgnoreCase(num)) {
					b = StandardOperation.insert("xszz_common_new_zzxmdmb",
							new String[] { "xmdm", "xmmc" }, new String[] {
									xmdm, xmmc }, request);
				} else {
					request.setAttribute("have", "have");

				}
			} else if ("edit".equalsIgnoreCase(doit)) {
				b = StandardOperation.update("xszz_common_new_zzxmdmb",
						new String[] { "xmmc" }, new String[] { xmmc }, "xmdm",
						xmdm, request);
			}
			if (b) {
				pkVal = xmdm;
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

		request.setAttribute("doit", doit);
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zzxmEdit");
	}

	/**
	 * @describe ɾ��������Ŀ
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzxmDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzComNewDao comNewDao = new XszzComNewDao();
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);

		String[] pkT = pkDel.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length*6];
		int x = 0;
		for (int i = 0; i < pkT.length; i++) {
			sqlT[x] = "delete xszz_common_new_bbgsb where xmdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete xszz_common_new_zzsjb where xmdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete xszz_common_new_zzjeb where xmdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete xszz_zgktdx_tsrqxmwh where zzxmdm='"+pkT[i]+"'";
			x++;
			String[] colT = dao.getArray(
					"select zddm col from xszz_common_new_bbzdyzd where xmdm=?",
					new String[] { pkT[i] }, "col");
			for (int j = 0; j < colT.length; j++) {
				StandardOperation.update("xszz_common_new_zzbbxssqb",
						"update xszz_common_new_zzbbxssqb set zdy" + colT[j]
								+ "='' where 1=1", request);
				String[] outV = comNewDao.getViewComm(
						"view_xszz_common_new_zzbbxssqb", "zdy" + colT[j]);
				if (dao
						.runUpdateTab("alter table xszz_common_new_zzbbxssqb drop column zdy"
								+ colT[j])) {
					String sqlTemp = "create or replace view view_xszz_common_new_zzbbxssqb as select a.*,b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh from xszz_common_new_zzbbxssqb a,view_stu_details b where a.xh=b.xh";
					dao.creatView(sqlTemp, outV);
					dao
							.runUpdateTab("comment on table VIEW_XSZZ_COMMON_NEW_ZZBBXSSQB is '����������Ϣ'");
				}
			}
			sqlT[x] = "delete xszz_common_new_bbzdyzd where xmdm='"+pkT[i]+"'";
			x++;
			sqlT[x] = "delete xszz_common_new_zzxmdmb where xmdm='"+pkT[i]+"'";
			x++;
		}
		dao.runBatch(sqlT);
		return new ActionForward("/new_common_xszz.do?method=data_zzxm&go=go", false);
	}

	/**
	 * @describe �������Ĳ�ѯ
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward data_zzje(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		DAO dao = DAO.getInstance();
		XszzComNewDao comNewDao = new XszzComNewDao();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "xmdm||zzje";
		String tips = "ѧ������ - ��������ά�� - �������ά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "view_xszz_common_new_zzjeb";
		String[] colList = new String[] { "����", "xmdm", "xmmc", "zzje" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��

		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);

		querry.append(SearchUtils.equalSql("xmdm", xmdm));
		querry.append(SearchUtils.likeSql("xmmc", xmmc));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);
		map.put("xmmc", xmmc);

		String sql = "select xmdm||zzje ����,a.* from view_xszz_common_new_zzjeb a"
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

		request.setAttribute("zzxmList", comNewDao.getZzxmList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("data_zzje");
	}

	/**
	 * @describe ���������õ����������ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward zzjeEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzComNewDao comNewDao = new XszzComNewDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select xmdm,xmmc,zzje from view_xszz_common_new_zzjeb where xmdm||zzje=?";
		String[] outString = new String[] { "xmdm", "xmmc", "zzje" };

		if ("save".equalsIgnoreCase(act)) {
			String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
			String zzje = Base.chgNull(request.getParameter("zzje"), "", 1);
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from xszz_common_new_zzjeb where xmdm||zzje=?",
							new String[] { xmdm + zzje }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation.insert("xszz_common_new_zzjeb", new String[] {
						"xmdm", "zzje" }, new String[] { xmdm, zzje },
						request);
				if (b) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
			} else {
				request.setAttribute("have", "have");
			}
			pkVal = xmdm + zzje;
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

		request.setAttribute("zzxmList", comNewDao.getZzxmList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zzjeEdit");
	}

	/**
	 * @describe ɾ�������������
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzjeDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);

		String[] pkT = pkDel.split("!!splitOne!!");
		String[] sqlT = new String[pkT.length];
		for (int i = 0; i < pkT.length; i++) {
			sqlT[i] = "delete xszz_common_new_zzjeb where xmdm||zzje='"+pkT[i]+"'";
		}
		dao.runBatch(sqlT);
		return new ActionForward("/new_common_xszz.do?method=data_zzje&go=go", false);
	}

	/**
	 * @describe ��������ʱ���趨
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward data_zzsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzComNewDao comNewDao = new XszzComNewDao();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "xmdm||xydm";
		String tips = "ѧ������ - ��������ά�� - ��������ʱ��ά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "view_xszz_common_new_zzsjb";
		String[] colList = new String[] { "����", "xmmc", "xymc", "xyrs",
				"sfkns", "kssj", "jssj" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��

		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 1);
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}

		querry.append(SearchUtils.equalSql("xmdm", xmdm));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);
		map.put("xydm", xydm);

		String sql = "select xmdm||xydm ����,a.* from view_xszz_common_new_zzsjb a"
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

		request.setAttribute("zzxmList", comNewDao.getZzxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("data_zzsj");
	}

	/**
	 * @describe ���������õ�����ʱ��������ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward zzsjEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzComNewDao comNewDao = new XszzComNewDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// дȨ��
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select xmdm,xmmc,xydm,xymc,xyrs,sfkns,kssj,jssj from view_xszz_common_new_zzsjb where xmdm||xydm=?";
		String[] outString = new String[] { "xmdm", "xmmc", "xydm", "xymc",
				"xyrs", "sfkns", "kssj", "jssj" };

		if ("save".equalsIgnoreCase(act)) {
			String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
			String xydm = Base.chgNull(request.getParameter("xydm"), "", 1);
			String sfkns = Base.chgNull(request.getParameter("sfkns"), "", 1);
			String kssj = Base.chgNull(request.getParameter("kssj"), "", 1);
			String jssj = Base.chgNull(request.getParameter("jssj"), "", 1);
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from view_xszz_common_new_zzsjb where xmdm||xydm=?",
							new String[] { xmdm + xydm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation
						.insert("xszz_common_new_zzsjb", new String[] { "xmdm",
								"xydm", "sfkns", "kssj", "jssj" },
								new String[] { xmdm, xydm, sfkns, kssj, jssj },
								request);
			} else {
				b = StandardOperation.update("xszz_common_new_zzsjb", new String[] {
						"sfkns", "kssj", "jssj" }, new String[] { sfkns, kssj,
						jssj }, "xmdm||xydm", xmdm + xydm, request);
			}
			if (b) {
				request.setAttribute("ok", "ok");
				pkVal = xmdm + xydm;
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
		request.setAttribute("zzxmList", comNewDao.getZzxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zzsjEdit");
	}

	/**
	 * @describe ����������������ʱ��
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzsjPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String sfkns = "��";
		String kssj = "1900-01-01";
		String jssj = "1900-01-01";
		HashMap<String, String> map = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			sfkns = Base.chgNull(request.getParameter("sfkns"), "", 1);
			kssj = Base.chgNull(request.getParameter("kssj"), "", 1);
			jssj = Base.chgNull(request.getParameter("jssj"), "", 1);
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update xszz_common_new_zzsjb set sfkns='" + sfkns
						+ "',kssj='" + kssj + "',jssj='" + jssj
						+ "' where xmdm||xydm='" + pkT[i] + "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("sfkns", sfkns);
		map.put("kssj", kssj);
		map.put("jssj", jssj);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("zzsjPlsz");
	}

	/**
	 * @describe ��ʼ����������ʱ����������
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzsjcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String sfbc = Base.chgNull(request.getParameter("sfbc"), "", 1);

		String[] xyList = dao.getArray(
				"select xydm from view_xsjbxx group by xydm", new String[] {},
				"xydm");
		String[] bbList = dao.getArray(
				"select xmdm from XSZZ_COMMON_NEW_ZZXMDMB group by xmdm",
				new String[] {}, "xmdm");
		if ("no".equalsIgnoreCase(sfbc)) {
			StandardOperation.delete("delete xszz_common_new_zzsjb where 1=1", "xszz_common_new_zzsjb", request);
			dao
				.runUpdate(
					"insert into xszz_common_new_zzsjb(xmdm,xydm) (select a.xmdm,b.xydm from XSZZ_COMMON_NEW_ZZXMDMB a,(select xydm from view_xsjbxx group by xydm) b)",
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
									"select count(*) num from xszz_common_new_zzsjb where xmdm=? and xydm=?",
									new String[] { bbdm, xydm }, "num");
					if ("0".equalsIgnoreCase(num)) {
						sqlT[x] = "insert into xszz_common_new_zzsjb(xmdm,xydm) values('"+bbdm+"','"+xydm+"')";
						x++;
					}
				}
			}
			dao.runBatch(sqlT);
		}
		request.setAttribute("endCsh", "end");
		return new ActionForward("/new_common_xszz.do?method=data_zzsj&go=go", false);
	}

	/**
	 * @describe ���������Զ����ֶεĲ�ѯ
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward data_zzbbzdyzd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		DAO dao = DAO.getInstance();
		XszzComNewDao comNewDao = new XszzComNewDao();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "zddm";
		String tips = "ѧ������ - ��������ά�� - ���������Զ����ֶ�ά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "view_xszz_common_new_bbzdyzd";
		String[] colList = new String[] { "����", "xmmc", "zddm", "zdmc", "zdlx",
				"zddx", "bxwsz" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��

		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);

		querry.append(SearchUtils.equalSql("xmdm", xmdm));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);

		String sql = "select zddm ����,a.* from view_xszz_common_new_bbzdyzd a"
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

		request.setAttribute("zzxmList", comNewDao.getZzxmList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("data_zzbbzdyzd");
	}

	/**
	 * @describe ���������õ������Զ����ֶ���ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward zzbbzdyzdEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		XszzComNewDao comNewDao = new XszzComNewDao();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String acDo = Base.chgNull(request.getParameter("acDo"), "", 0);
		String sql = "select xmdm,xmmc,zddm,zdmc,zdlx,zddx,bxwsz from view_xszz_common_new_bbzdyzd where zddm=?";
		String[] outString = new String[] { "xmdm", "xmmc", "zddm", "zdmc",
				"zdlx", "zddx", "bxwsz" };

		if ("save".equalsIgnoreCase(acDo)) {
			String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
			String zddm = Base.chgNull(request.getParameter("zddm"), "", 1);
			String zdmc = Base.chgNull(request.getParameter("zdmc"), "", 1);
			String zdlx = Base.chgNull(request.getParameter("zdlx"), "", 1);
			String zddx = Base.chgNull(request.getParameter("zddx"), "", 1);
			String bxwsz = Base.chgNull(request.getParameter("bxwsz"), "", 1);
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from view_xszz_common_new_bbzdyzd where zddm=?",
							new String[] { zddm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				String sqlTemp = "alter table xszz_common_new_zzbbxssqb add (zdy"
						+ zddm + " varchar(" + zddx + "))";
				dao.runUpdateTab(sqlTemp);
				String[] outV = dao.getViewComm("view_xszz_common_new_zzbbxssqb");
				sqlTemp = "create or replace view view_xszz_common_new_zzbbxssqb as select a.*,b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh from xszz_common_new_zzbbxssqb a,view_stu_details b where a.xh=b.xh";
				dao.creatView(sqlTemp, outV);
				sqlTemp = "comment on column xszz_common_new_zzbbxssqb.zdy" + zddm
						+ " is '" + zdmc + "'";
				dao.runUpdateTab(sqlTemp);
				dao.runUpdateTab("comment on table VIEW_XSZZ_COMMON_NEW_ZZBBXSSQB is '����������Ϣ'");
				sqlTemp = "comment on column view_xszz_common_new_zzbbxssqb.zdy"
						+ zddm + " is '" + zdmc + "'";
				dao.runUpdateTab(sqlTemp);
				b = StandardOperation.insert("xszz_common_new_bbzdyzd", new String[] {
						"xmdm", "zddm", "zdmc", "zdlx", "zddx", "bxwsz" },
						new String[] { xmdm, zddm, zdmc, zdlx, zddx, bxwsz },
						request);
			} else {
				if ("add".equalsIgnoreCase(act)) {
					request.setAttribute("have", "have");
				} else {
					String sqlTemp = "comment on column xszz_common_new_zzbbxssqb.zdy"
							+ zddm + " is '" + zdmc + "'";
					dao.runUpdateTab(sqlTemp);
					sqlTemp = "comment on column view_xszz_common_new_zzbbxssqb.zdy"
							+ zddm + " is '" + zdmc + "'";
					dao.runUpdateTab(sqlTemp);
					b = StandardOperation.update("xszz_common_new_bbzdyzd",
							new String[] { "zdmc", "zdlx", "zddx", "bxwsz" },
							new String[] { zdmc, zdlx, zddx, bxwsz }, "zddm",
							zddm, request);
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

		request.setAttribute("zzxmList", comNewDao.getZzxmList());
		request.setAttribute("rs", map);
		request.setAttribute("acDo", acDo);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zzbbzdyzdEdit");
	}

	/**
	 * @describe ɾ�������Զ����ֶ�����
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward zzbbzdyzdDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzComNewDao comNewDao = new XszzComNewDao();
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String[] pkT = pkDel.split("!!splitOne!!");
		for (int i = 0; i < pkT.length; i++) {
			if (!"".equalsIgnoreCase(pkT[i]) && !"zddm".equalsIgnoreCase(pkT[i])) {
				StandardOperation.update("xszz_common_new_zzbbxssqb",
						"update xszz_common_new_zzbbxssqb set zdy" + pkT[i]
								+ "='' where 1=1", request);
				String[] outV = comNewDao.getViewComm(
						"view_xszz_common_new_zzbbxssqb", "zdy" + pkT[i]);
				if (dao
						.runUpdateTab("alter table xszz_common_new_zzbbxssqb drop column zdy"
								+ pkT[i])) {
					String sqlTemp = "create or replace view view_xszz_common_new_zzbbxssqb as select a.*,b.xm,b.xb,b.sfzh,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.ssbh,b.qsdh,b.lydq,b.csrq,(select z.rxny from bks_xsjbxx z where z.xh=a.xh) rxny,b.mzmc,b.zzmmmc,b.kh from xszz_common_new_zzbbxssqb a,view_stu_details b where a.xh=b.xh";
					dao.creatView(sqlTemp, outV);
					dao.runUpdateTab("comment on table VIEW_XSZZ_COMMON_NEW_ZZBBXSSQB is '����������Ϣ'");
					StandardOperation.delete("xszz_common_new_bbzdyzd", "zddm",
							pkT[i], request);
				}
			}
		}
		return new ActionForward("/new_common_xszz.do?method=data_zzbbzdyzd&go=go",
				false);
	}

	/**
	 * @describe ���������ʽ�Ĳ�ѯ
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward data_zzbbgs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		DAO dao = DAO.getInstance();
		XszzComNewDao comNewDao = new XszzComNewDao();
		String userType = session.getAttribute("userOnLine").toString();
		String pk = "xmdm";
		String tips = "ѧ������ - ��������ά�� - ������Ŀ�����ʽά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "xszz_common_new_zzxmdmb";
		String[] colList = new String[] { "����", "xmdm", "xmmc" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��

		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);

		querry.append(SearchUtils.equalSql("xmdm", xmdm));
		querry.append(SearchUtils.likeSql("xmmc", xmmc));

		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmdm", xmdm);
		map.put("xmmc", xmmc);

		String sql = "select xmdm ����,a.* from xszz_common_new_zzxmdmb a"
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

		request.setAttribute("zzxmList", comNewDao.getZzxmList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("data_zzbbgs");
	}

	/**
	 * @describe ���������õ����������ʽ��ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward zzbbgsEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 0);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select xmdm,xmmc from XSZZ_COMMON_NEW_ZZXMDMB where xmdm=?";
		String[] outString = new String[] { "xmdm", "xmmc" };

		if ("save".equalsIgnoreCase(act)) {
			String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
			String bbgs = Base.chgNull(request.getParameter("content1"), "", 1);
			boolean b = false;

			b = StandardOperation.update("xszz_common_new_bbgsb", new String[] {
					"bbgs" }, new String[] { bbgs }, "xmdm",
					xmdm, request);
			String num = dao.getOneRs(
					"select count(*) num from xszz_common_new_bbgsb where xmdm=?",
					new String[] { xmdm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation.insert("xszz_common_new_bbgsb",
						new String[] { "xmdm", "bbgs" },
						new String[] { xmdm, bbgs }, request);
			}
			if (b) {
				pkVal = xmdm;
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
				"select bbgs from xszz_common_new_bbgsb where xmdm=?",
				new String[] { pkVal }, "bbgs");
		if (null != clob) {
			map.put("bbgs", clob.getSubString(1L, (int) clob.length()));
		}

		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zzbbgsEdit");
	}

	/**
	 * @describe ��ѧ��������ʱ���趨
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward data_zxdksj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "xmmc||xydm";
		String tips = "ѧ������ - ��������ά�� - ������Ŀʱ��ά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "view_common_new_gjzxdk_sjb";
		String[] colList = new String[] { "����", "xmmc", "xymc", "xyrs", "kssj", "jssj" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String writeAble = "yes";// дȨ��
		
		String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 1);
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
		
		String sql = "select xmmc||xydm ����,a.* from view_common_new_gjzxdk_sjb a" + querry.toString();
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
		
		request.setAttribute("zxdkxmList", xszzDao.getXszzZxdkxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("data_zxdksj");
	}

	/**
	 * @describe ���������õ���ѧ����ʱ��������ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zxdksjEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// дȨ��
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select xmmc,xydm,xymc,xyrs,kssj,jssj from view_common_new_gjzxdk_sjb where xmmc||xydm=?";
		String[] outString = new String[] { "xmmc", "xydm", "xymc",
				"xyrs", "kssj", "jssj" };

		if ("save".equalsIgnoreCase(act)) {
			String xmmc    = Base.chgNull(request.getParameter("xmmc"), "", 1);
			String xydm    = Base.chgNull(request.getParameter("xydm"), "", 1);
			String kssj    = Base.chgNull(request.getParameter("kssj"), "", 1);
			String jssj    = Base.chgNull(request.getParameter("jssj"), "", 1);
			boolean b = false;
			
			String num = dao.getOneRs(
					"select count(*) num from view_common_new_gjzxdk_sjb where xmmc||xydm=?",
					new String[] { xmmc + xydm }, "num");
			if("0".equalsIgnoreCase(num)){
				b = StandardOperation
						.insert("common_new_gjzxdk_sjb", new String[] { "xmmc",
								"xydm", "kssj", "jssj" },
								new String[] { xmmc, xydm, kssj, jssj },
								request);
			} else {
				b = StandardOperation.update("common_new_gjzxdk_sjb", new String[] {
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
		request.setAttribute("zxdkxmList", xszzDao.getXszzZxdkxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxdksjEdit");
	}
	
	/**
	 * @describe ����������ѧ��������ʱ��
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zxdksjPlsz(ActionMapping mapping,
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
				sqlT[i] = "update common_new_gjzxdk_sjb set kssj='" + kssj
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
		return mapping.findForward("zxdksjPlsz");
	}
	
	/**
	 * @describe ��ѧ����ʱ���ʼ��
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zxdksjcsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = Base.xxdm;
		StandardOperation.delete("common_new_gjzxdk_sjb", new String[]{"1"}, new String[]{"1"}, request);
		
		dao.runUpdateTab("insert into common_new_gjzxdk_sjb(xmmc,xydm) select '������ѧ����' xmmc,xydm from view_njxyzybj group by xydm");
		dao.runUpdateTab("insert into common_new_gjzxdk_sjb(xmmc,xydm) select '������' xmmc,xydm from view_njxyzybj group by xydm");
		
		if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
			dao.runUpdateTab("insert into common_new_gjzxdk_sjb(xmmc,xydm) select '������־��ѧ��' xmmc,xydm from view_njxyzybj group by xydm");
			dao.runUpdateTab("insert into common_new_gjzxdk_sjb(xmmc,xydm) select '���ҽ�ѧ��' xmmc,xydm from view_njxyzybj group by xydm");
		}
		
		return new ActionForward("/new_common_xszz.do?method=data_zxdksj&go=go", false);
	}
	
	/**
	 * @describe ����Э������
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward hkxysz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String doType = request.getParameter("doType");// ��������

		String sUName;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		sUName = session.getAttribute("userName").toString();
		String sql = "";
		String[] outString = new String[] {};
		sql = "select yhmc,hkqx,sfgdhkfs,zhmc,gdhkfsdm from COMMON_ZXDK_HKXYSZB1 where 1=2";
		outString = dao.getColumnName(sql);
		sql = "select gdhkfsdm from COMMON_ZXDK_HKXYSZB1 where rownum=1";
		String gdhkfsdm = "";
		boolean ok = false;
		String[] gdhkfsdmTemp = dao.getOneRs(sql, new String[] {},
				new String[] { "gdhkfsdm" });
		if (gdhkfsdmTemp != null) {
			gdhkfsdm = gdhkfsdmTemp[0];
		} else {
			gdhkfsdm = Base.chgNull(request.getParameter("hkfsdm"), "", 1);
		}

		String logMsg;
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String sfgdhkfs = Base.chgNull(request.getParameter("sfgdhkfs"), "", 1);
		String yhmc = Base.chgNull(request.getParameter("yhmc"), "", 1);
		String hkqx = Base.chgNull(request.getParameter("hkqx"), "", 1);
		String zhmc = Base.chgNull(request.getParameter("zhmc"), "", 1);
		String hkfsmc1 = Base.chgNull(request.getParameter("hkfsmc1"), "", 1);
		String hkfsmc2 = Base.chgNull(request.getParameter("hkfsmc2"), "", 1);
		String hkfsmc3 = Base.chgNull(request.getParameter("hkfsmc3"), "", 1);
		String hkfsmc4 = Base.chgNull(request.getParameter("hkfsmc4"), "", 1);
		String hkfsmc5 = Base.chgNull(request.getParameter("hkfsmc5"), "", 1);
		String hkfsmc6 = Base.chgNull(request.getParameter("hkfsmc6"), "", 1);
		String hkfsmc7 = Base.chgNull(request.getParameter("hkfsmc7"), "", 1);
		String hkfsmc8 = Base.chgNull(request.getParameter("hkfsmc8"), "", 1);
		String hkfsmc9 = Base.chgNull(request.getParameter("hkfsmc9"), "", 1);
		String hkfsmc10 = Base.chgNull(request.getParameter("hkfsmc10"), "", 1);
		String[] temp = new String[] { hkfsmc1, hkfsmc2, hkfsmc3, hkfsmc4,
				hkfsmc5, hkfsmc6, hkfsmc7, hkfsmc8, hkfsmc9, hkfsmc10 };

		if (doType != null && doType.equalsIgnoreCase("add")) {
			gdhkfsdm = Base.chgNull(request.getParameter("hkfsdm"), "", 1);

			int it;
			for (int i = 0; i < temp.length; i++) {
				it = i + 1;
				ok = StandardOperation.update("COMMON_ZXDK_HKXYSZB2",
						new String[] { "HKFS" }, new String[] { temp[i] },
						"HKFSDM", String.valueOf(it), request);
				if(ok){
					logMsg = "�޸� COMMON_ZXDK_HKXYSZB2";
					Base.log(request, logMsg, sUName);
				}
			}

			sql = "select yhmc,hkqx,sfgdhkfs,zhmc,gdhkfsdm from COMMON_ZXDK_HKXYSZB1 where rownum=1";
			outValue = dao.getOneRs(sql, new String[] {}, outString);

			String[] valueForOut = new String[] { yhmc, hkqx, sfgdhkfs, zhmc,
					gdhkfsdm };
			if (outValue == null) {
				ok = StandardOperation.insert("COMMON_ZXDK_HKXYSZB1", new String[] {
						"yhmc", "hkqx", "sfgdhkfs", "zhmc", "gdhkfsdm" },
						valueForOut, request);
			} else {
				sql = "update COMMON_ZXDK_HKXYSZB1 set yhmc='" + yhmc
						+ "',hkqx='" + hkqx + "',sfgdhkfs='" + sfgdhkfs
						+ "',zhmc='" + zhmc + "',gdhkfsdm='" + gdhkfsdm + "'";
				ok = StandardOperation.update("COMMON_ZXDK_HKXYSZB1", sql,
						request);
			}

			if (ok) {
				logMsg = "�޸Ļ���� COMMON_ZXDK_HKXYSZB1";
				Base.log(request, logMsg, sUName);
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		if ("read".equalsIgnoreCase(doType)) {
			sfgdhkfs = request.getParameter("sfgdhkfs");

			String[] out = new String[] { sfgdhkfs, yhmc, hkqx, zhmc, gdhkfsdm,
					hkfsmc1, hkfsmc2, hkfsmc3, hkfsmc4, hkfsmc5, hkfsmc6,
					hkfsmc7, hkfsmc8, hkfsmc9, hkfsmc10 };
			String[] pout = new String[] { "sfgdhkfs", "yhmc", "hkqx", "zhmc",
					"gdhkfsdm", "hkfsmc1", "hkfsmc2", "hkfsmc3", "hkfsmc4",
					"hkfsmc5", "hkfsmc6", "hkfsmc7", "hkfsmc8", "hkfsmc9",
			"hkfsmc10" };
			for (int i = 0; i < pout.length; i++) {
				if (out[i] != null) {
					map.put(pout[i], out[i]);
				} else {
					map.put(pout[i], "");
				}
			}
			if ("1".equalsIgnoreCase(sfgdhkfs)) {
				request.setAttribute("isGD", "is");
			} else {
				request.setAttribute("isGD", "no");
			}
		} else {
			sql = "select yhmc,hkqx,sfgdhkfs,zhmc,gdhkfsdm from COMMON_ZXDK_HKXYSZB1 where rownum=1";
			outValue = dao.getOneRs(sql, new String[] {}, outString);
			outString = new String[] { "yhmc", "hkqx", "sfgdhkfs", "zhmc",
			"gdhkfsdm" };
			if (outValue != null) {
				for (int i = 0; i < outString.length; i++) {
					if (outValue[i] != null) {
						map.put(outString[i], outValue[i]);
					} else {
						map.put(outString[i], "");
					}
				}
			}

			if ("1".equalsIgnoreCase(sfgdhkfs)) {
				request.setAttribute("isGD", "is");
			} else if (("".equalsIgnoreCase(sfgdhkfs) || (sfgdhkfs == null))
					&& (outValue != null)
					&& ("1".equalsIgnoreCase(outValue[2]))) {
				request.setAttribute("isGD", "is");
			} else {
				request.setAttribute("isGD", "no");
			}

			String[] hkfstemp;
			String[] hkfsm = new String[] { "hkfsmc1", "hkfsmc2", "hkfsmc3",
					"hkfsmc4", "hkfsmc5", "hkfsmc6", "hkfsmc7", "hkfsmc8",
					"hkfsmc9", "hkfsmc10" };
			for (int i = 1; i <= 10; i++) {
				sql = "select hkfs from COMMON_ZXDK_HKXYSZB2 where hkfsdm=?";
				hkfstemp = dao.getOneRs(sql,
						new String[] { String.valueOf(i) },
						new String[] { "hkfs" });
				if (hkfstemp != null) {
					map.put(hkfsm[i - 1], hkfstemp[0]);
				}
			}
		}

		sql = "select hkfs from COMMON_ZXDK_HKXYSZB2 where hkfsdm=?";
		String[] hkfsTemp = dao.getOneRs(sql, new String[] { gdhkfsdm },
				new String[] { "hkfs" });
		if (hkfsTemp != null) {
			map.put("hkfs", hkfsTemp[0]);
			map.put("hkfsdm", gdhkfsdm);
		} else {
			map.put("hkfs", "");
			map.put("hkfsdm", "");
		}

		sql = "select hkfsdm,hkfs from COMMON_ZXDK_HKXYSZB2";
		List hkList = dao.getList(sql, new String[] {}, new String[] {
				"hkfsdm", "hkfs" });
		request.setAttribute("hkList", hkList);
		request.setAttribute("rs", map);
		return mapping.findForward("hkxysz");
	}
	
	/**
	 * @describe ѡ��˵�
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward xszzNewCd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String cdlb = request.getParameter("cdlb");
		String doType = request.getParameter("doType");
		List tempList = null;
		ActionForward myAct;
		String querry = " and 1=1";
		HashMap<String, String> map = new HashMap<String, String>();
		querry += Globals.NewNotJudgeWhichSchool(xxdm);
		request.setAttribute("cdlb", cdlb);

		if ("sq".equalsIgnoreCase(cdlb)) {
			if ("open".equalsIgnoreCase(doType)) {
				if (Globals.NewJudgeWhichSchool(xxdm)) {
					sql = "select a.gnmklj,a.gnmkmc from "
						+ "((select gnmklj,gnmkmc,xssx from new_xszz_gnmkb where xxdm=? and gnmklx='����') "
						+ "union (select gnmklj,gnmkmc,xssx from new_xszz_gnmkb where "
						+ "xxmc is null and gnmklx='����' " + querry + ")) a order by xssx";
					tempList = dao.getList(sql, new String[] { xxdm },
							new String[] { "gnmklj", "gnmkmc" });
				} else {
					sql = "select gnmklj,gnmkmc from new_xszz_gnmkb where xxdm is null and gnmklx='����' order by xssx";
					tempList = dao.getList(sql, new String[] {}, new String[] {
							"gnmklj", "gnmkmc" });
				}
				request.setAttribute("list", tempList);
				request.setAttribute("rs", map);

			} else if ("query".equalsIgnoreCase(doType)) {
				String url = request.getParameter("gnmk");
				if ((url != null) && (!"".equalsIgnoreCase(url))) {
					url = DealString.toGBK(url);
					url = "/" + url;
					myAct = new ActionForward(url, false);
				} else {
					myAct = new ActionForward("/xgxt/new_common_xszz.do?method=xszzNewCd&cdlb=sq&doType=open",false);
				}
				return myAct;
			}
		} else if ("sh".equalsIgnoreCase(cdlb)) {
			if ("open".equalsIgnoreCase(doType)) {
				if (Globals.NewJudgeWhichSchool(xxdm)) {
					sql = "select a.gnmklj,a.gnmkmc from "
						+ "((select gnmklj,gnmkmc,xssx from new_xszz_gnmkb where xxdm=? and gnmklx='���') "
						+ "union (select gnmklj,gnmkmc,xssx from new_xszz_gnmkb where "
						+ "xxdm is null and gnmklx='���' " + querry + ")) a order by xssx";
					tempList = dao.getList(sql, new String[] { xxdm },
							new String[] { "gnmklj", "gnmkmc" });
				} else {
					sql = "select gnmklj,gnmkmc from new_xszz_gnmkb where xxdm is null and gnmklx='���' order by xssx";
					tempList = dao.getList(sql, new String[] {}, new String[] {
							"gnmklj", "gnmkmc" });
				}
				request.setAttribute("list", tempList);
				request.setAttribute("rs", map);

			} else if ("query".equalsIgnoreCase(doType)) {
				String url = request.getParameter("gnmk");
				if ((url != null) && (!"".equalsIgnoreCase(url))) {
					url = DealString.toGBK(url);
					url = "/" + url;
					myAct = new ActionForward(url, false);
				} else {
					myAct = new ActionForward(
							"/xgxt/new_common_xszz.do?method=xszzNewCd&cdlb=sh&doType=open",
							false);
				}
				return myAct;
			}
		}
		return mapping.findForward("xszzNewCd");
	}
	
	/**
	 * @describe ��������ѯ
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward stuResultCd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String username = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		String rsNum = "";
		List topTr = new ArrayList<String[]>();
		HashMap<String, String> map = new HashMap<String, String>();
		String gnmkmc = DealString.toGBK(request.getParameter("gnmkmc"));
		String doType = request.getParameter("doType");
		ActionForward myActFwd = null;
		List tempList = null;
		String querry = " and 1=1";

		if ("query".equals(doType)) {
			map.put("gnmkmc", gnmkmc);
		}

		querry += Globals.NewNotJudgeWhichSchool(xxdm);

		if (Globals.NewJudgeWhichSchool(xxdm)) {
			sql = "select a.gnmklj,a.gnmkmc from "
					+ "((select gnmklj,gnmkmc,xssx from new_xszz_gnmkb where xxdm=? and gnmklx='����') "
					+ "union (select gnmklj,gnmkmc,xssx from new_xszz_gnmkb where "
					+ "xxmc is null and gnmklx='����' " + querry
					+ ")) a order by a.xssx";
			tempList = dao.getList(sql, new String[] { xxdm }, new String[] {
					"gnmklj", "gnmkmc" });
		} else {
			sql = "select gnmklj,gnmkmc from new_xszz_gnmkb where xxmc is null and gnmklx='����' order by xssx";
			tempList = dao.getList(sql, new String[] {}, new String[] {
					"gnmklj", "gnmkmc" });
		}
		request.setAttribute("list", tempList);
		request.setAttribute("rs", map);

		if ("query".equals(doType)) {
			if ("������".equals(gnmkmc)) {
				String tableName = "view_xszz_new_knsxx"; // �����������
				colList = new String[] { "url", "�к�", "nd", "xh", "xm", "sqsj", "xysh", "xyshyj",
						"xxsh", "xxshyj" };
				sql = "select '/xgxt/new_common_xszz.do?method=knssq'||'&'||'pkVal='||nd||xh url,";
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)){//�й���ҵ��ѧ
					tableName = "view_zgkydx_knsxx";
					colList = new String[] { "url", "�к�", "nd", "xh", "xm", "sqsj",
							"bjpyjg", "xbshjg", "xxshjg" };
					if (!userType.equalsIgnoreCase("stu")) {
						myActFwd = new ActionForward("/zgkydx_xszz.do?method=knssh&isQuery=is",
								false);
						return myActFwd;
					}
					sql = "select '/xgxt/zgkydx_xszz.do?method=knssq'||'&'||'pkVal='||nd||xh url,";
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)){//�й�����ѧԺ
					tableName = "view_zgmsxy_knsxx";
					colList = new String[] { "url", "�к�", "xn", "xh", "xm", "sqsj", "mzpyjg", "csly", "xysh", "xxsh" };
					if (!userType.equalsIgnoreCase("stu")) {
						myActFwd = new ActionForward("/zgmsxy_xszz.do?method=knsrdsh&isQuery=is",
								false);
						return myActFwd;
					}
					sql = "select '/xgxt/zgmsxy_xszz.do?method=knsrdsq'||'&'||'pkVal='||xn||xh url,";
				}
				if (!userType.equalsIgnoreCase("stu")) {
					myActFwd = new ActionForward("/new_common_xszz.do?method=knssh&isQuery=is",
							false);
					return myActFwd;
				}
				sql += "rownum �к�,a.* from " + tableName
						+ " a where xh=?";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				if ((request.getParameter("act") != null)
						&& request.getParameter("act").equalsIgnoreCase("go")) {
					rs.addAll(dao.rsToVator(sql, new String[] { username },
							colList));
					if (rs == null) {
						rsNum = "0";
					} else {
						rsNum = String.valueOf(rs.size());
					}
				}
			}
			if ("������Ŀ".equals(gnmkmc)) {
				String tableName = "view_xszz_common_new_zzbbxssqb"; 
				colList = new String[] { "url", "�к�", "nd", "xmmc", "xh", "xm", "sqsj", "xysh", "xypzje", "xyshyj",
						"xxsh", "xxpzje", "xxshyj" };
				if (!userType.equalsIgnoreCase("stu")) {
					myActFwd = new ActionForward("/new_common_xszz.do?method=xszzsh&isQuery=is",
							false);
					return myActFwd;
				}
				sql = "select '/xgxt/new_common_xszz.do?method=xszzsq'||'&'||'pkVal='||nd||xmdm||xh url,rownum �к�,a.* from " + tableName
						+ " a where xh=?";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				if ((request.getParameter("act") != null)
						&& request.getParameter("act").equalsIgnoreCase("go")) {
					rs.addAll(dao.rsToVator(sql, new String[] { username },
							colList));
					if (rs == null) {
						rsNum = "0";
					} else {
						rsNum = String.valueOf(rs.size());
					}
				}
			}
			if ("������ѧ����".equals(gnmkmc)) {
				String tableName = "view_xszz_common_gjzxdk"; 
				colList = new String[] { "url", "�к�", "nd", "xh", "xm", "hj", "sqsj", "xysh", "xyshyj", "xxsh", "xxshyj" };
				if (!userType.equalsIgnoreCase("stu")) {
					myActFwd = new ActionForward("/new_common_xszz.do?method=gjzxdksh&isQuery=is",
							false);
					return myActFwd;
				}
				sql = "select '/xgxt/new_common_xszz.do?method=gjzxdksq'||'&'||'pkVal='||nd||xh url,rownum �к�,a.* from " + tableName
						+ " a where xh=?";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				if ((request.getParameter("act") != null)
						&& request.getParameter("act").equalsIgnoreCase("go")) {
					rs.addAll(dao.rsToVator(sql, new String[] { username },
							colList));
					if (rs == null) {
						rsNum = "0";
					} else {
						rsNum = String.valueOf(rs.size());
					}
				}
			}
			if ("��ͥ�������".equals(gnmkmc)) {
				String tableName = "view_zgdzdx_kns_jtqkdc"; 
				colList = new String[] { "url", "�к�", "xn", "xh", "xm", "sqsj", "sh" };
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
					tableName = "view_zgdzdx_kns_jtqkdc";
					colList = new String[] { "url", "�к�", "xn", "xh", "xm", "sqsj", "sh" };
					if (!userType.equalsIgnoreCase("stu")) {
						myActFwd = new ActionForward("/zgdzdx_xszz.do?method=jtqkdcsh&isQuery=is",
								false);
						return myActFwd;
					}
				}
				if (!userType.equalsIgnoreCase("stu")) {
					myActFwd = new ActionForward("/zgdzdx_xszz.do?method=jtqkdcsh&isQuery=is",
							false);
					return myActFwd;
				}
				sql = "select '/xgxt/zgdzdx_xszz.do?method=jtqkdcsq'||'&'||'pkVal='||xn||xh url,rownum �к�,a.* from " + tableName
						+ " a where xh=?";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				if ((request.getParameter("act") != null)
						&& request.getParameter("act").equalsIgnoreCase("go")) {
					rs.addAll(dao.rsToVator(sql, new String[] { username },
							colList));
					if (rs == null) {
						rsNum = "0";
					} else {
						rsNum = String.valueOf(rs.size());
					}
				}
			}
			if ("�������϶�".equals(gnmkmc)) {
				String tableName = "view_zgdzdx_kns_pksrd"; 
				colList = new String[] { "url", "�к�", "xn", "xh", "xm", "sqsj", "tjdc", "xysh", "xxsh" };
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
					tableName = "view_zgdzdx_kns_pksrd";
					colList = new String[] { "url", "�к�", "xn", "xh", "xm", "sqsj", "tjdc", "xysh", "xxsh" };
					if (!userType.equalsIgnoreCase("stu")) {
						myActFwd = new ActionForward("/zgdzdx_xszz.do?method=knsrdsh&isQuery=is",
								false);
						return myActFwd;
					}
				}
				if (!userType.equalsIgnoreCase("stu")) {
					myActFwd = new ActionForward("/zgdzdx_xszz.do?method=knsrdsh&isQuery=is",
							false);
					return myActFwd;
				}
				sql = "select '/xgxt/zgdzdx_xszz.do?method=knsrdsq'||'&'||'pkVal='||xn||xh url,rownum �к�,a.* from " + tableName
						+ " a where xh=?";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				if ((request.getParameter("act") != null)
						&& request.getParameter("act").equalsIgnoreCase("go")) {
					rs.addAll(dao.rsToVator(sql, new String[] { username },
							colList));
					if (rs == null) {
						rsNum = "0";
					} else {
						rsNum = String.valueOf(rs.size());
					}
				}
			}
			if ("˼Դ����".equals(gnmkmc)) {
				String tableName = "view_nbzyjsxy_syjj"; 
				colList = new String[] { "url", "�к�", "xh", "xm", "jkcs", "jkzje", "sqsj",
						"sqje", "spje", "bjrsh", "bjrshyj", "xysh", "xyshyj",
						"xxsh", "xxshyj" };
				if (xxdm.equalsIgnoreCase(Globals.XXDM_NBZYJSXY)){
					tableName = "view_nbzyjsxy_syjj";
					colList = new String[] { "url", "�к�", "xh", "xm", "jkcs", "jkzje", "sqsj",
							"sqje", "spje", "bjrsh", "bjrshyj", "xysh",
							"xyshyj", "xxsh", "xxshyj" };
					if (!userType.equalsIgnoreCase("stu")) {
						myActFwd = new ActionForward("/nbzyjsxy_xszz.do?method=syjjsh&isQuery=is",
								false);
						return myActFwd;
					}
				}
				if (!userType.equalsIgnoreCase("stu")) {
					myActFwd = new ActionForward("/nbzyjsxy_xszz.do?method=syjjsh&isQuery=is",
							false);
					return myActFwd;
				}
				sql = "select '/xgxt/nbzyjsxy_xszz.do?method=syjjsq'||'&'||'pkVal='||guid url,rownum �к�,a.* from " + tableName
						+ " a where xh=?";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				if ((request.getParameter("act") != null)
						&& request.getParameter("act").equalsIgnoreCase("go")) {
					rs.addAll(dao.rsToVator(sql, new String[] { username },
							colList));
					if (rs == null) {
						rsNum = "0";
					} else {
						rsNum = String.valueOf(rs.size());
					}
				}
			}
			if ("������־��ѧ��".equals(gnmkmc)) {
				String tableName = "view_xszz_xmlg_gjlzjxj"; 
				colList = new String[] { "url", "�к�", "xn", "xh", "xm", "sqsj", "xysh", "xyshyj",
						"xxsh", "xxshyj" };
				if (xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY)){
					tableName = "view_xszz_xmlg_gjlzjxj";
					colList = new String[] { "url", "�к�", "xn", "xh", "xm", "sqsj", "xysh", "xyshyj",
							"xxsh", "xxshyj" };
					if (!userType.equalsIgnoreCase("stu")) {
						myActFwd = new ActionForward("/xmlgxy_xszz.do?method=gjlzjxjsh&isQuery=is",
								false);
						return myActFwd;
					}
				}
				if (!userType.equalsIgnoreCase("stu")) {
					myActFwd = new ActionForward("/xmlgxy_xszz.do?method=gjlzjxjsh&isQuery=is",
							false);
					return myActFwd;
				}
				sql = "select '/xgxt/xmlgxy_xszz.do?method=gjlzjxjsq'||'&'||'pkVal='||xn||xh url,rownum �к�,a.* from " + tableName
						+ " a where xh=?";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				if ((request.getParameter("act") != null)
						&& request.getParameter("act").equalsIgnoreCase("go")) {
					rs.addAll(dao.rsToVator(sql, new String[] { username },
							colList));
					if (rs == null) {
						rsNum = "0";
					} else {
						rsNum = String.valueOf(rs.size());
					}
				}
			}
			if ("���ҽ�ѧ��".equals(gnmkmc)) {
				String tableName = "view_xszz_xmlg_gjjxj"; 
				colList = new String[] { "url", "�к�", "xn", "xh", "xm", "sqsj", "xysh", "xyshyj",
						"xxsh", "xxshyj" };
				if (xxdm.equalsIgnoreCase(Globals.XXDM_XMLGXY)){
					tableName = "view_xszz_xmlg_gjjxj";
					colList = new String[] { "url", "�к�", "xn", "xh", "xm", "sqsj", "xysh", "xyshyj",
							"xxsh", "xxshyj" };
					if (!userType.equalsIgnoreCase("stu")) {
						myActFwd = new ActionForward("/xmlgxy_xszz.do?method=gjjxjsh&isQuery=is",
								false);
						return myActFwd;
					}
				}
				if (!userType.equalsIgnoreCase("stu")) {
					myActFwd = new ActionForward("/xmlgxy_xszz.do?method=gjjxjsh&isQuery=is",
							false);
					return myActFwd;
				}
				sql = "select '/xgxt/xmlgxy_xszz.do?method=gjjxjsq'||'&'||'pkVal='||xn||xh url,rownum �к�,a.* from " + tableName
						+ " a where xh=?";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				if ((request.getParameter("act") != null)
						&& request.getParameter("act").equalsIgnoreCase("go")) {
					rs.addAll(dao.rsToVator(sql, new String[] { username },
							colList));
					if (rs == null) {
						rsNum = "0";
					} else {
						rsNum = String.valueOf(rs.size());
					}
				}
			}
		}

		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("rs1", map);// �Ѳ�ѯ��������ȥ
		return mapping.findForward("stuResultCd");
	}
	
	/**
	 * @describe ��������
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward xszzNewhz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		HashMap<String, String> hs = new HashMap<String, String>();
		String tips = "ƶ������ �� ���ݵ���";
		String userType = session.getAttribute("userType").toString();
		String selTab = request.getParameter("hzxmb");
		String xxdm = StandardOperation.getXxdm();
		if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("disable", "yes");
		}
		if (selTab == null || "".equals(selTab))
			selTab = "view_xszz_new_knsxx";
		String sql = "select * from " + selTab + " where 1=2";
		String[] colList = dao.getColumnName(sql);
		String[] colListCn = dao.getColumnNameCN(colList, selTab);
		ArrayList<HashMap<String, String>> srcTabColsList = new ArrayList<HashMap<String, String>>();
		generateList(srcTabColsList, colList, colListCn);
		String nj = request.getParameter("nj");
		String nd = request.getParameter("nd");
		String xn = request.getParameter("xn");
		String xh = request.getParameter("xh");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)||selTab.equalsIgnoreCase("VIEW_KNSXX")) {
			request.setAttribute("xnnd", "xn");
		} else {
			request.setAttribute("xnnd", "nd");
		}
		
		hs.put("hzxmb", selTab);
		hs.put("en", "");
		xydm = xydm==null ? "":xydm;
		zydm = zydm==null ? "":zydm;
		nj = nj==null ? "":nj;
		String bjKey = xydm+"!!"+zydm+"!!"+nj;
		hs.put("nj", nj);
		hs.put("nd", nd);
		hs.put("xn", xn);
		hs.put("xh", xh);
		hs.put("xydm", xydm);
		hs.put("zydm", zydm);
		hs.put("bjdm", bjdm);
		request.setAttribute("rs", hs);
		request.setAttribute("tips", tips);
		request.setAttribute("userType", userType);
		request.setAttribute("srcTabColsList", srcTabColsList);
		request.setAttribute("tableName", selTab);
		request.setAttribute("zzxmList", xszzDao.getNewZzxmList(xxdm));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(xydm));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		return mapping.findForward("xszzNewhz");
	}
	
	/**
	 * @describe �������ܵ���
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward xszzhzExp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String nj = request.getParameter("nj");
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String xh = request.getParameter("xh");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String tableName = request.getParameter("tableName");
		StringBuffer condi = new StringBuffer(" where 1=1 ");
		// String[] queryCondiArr = {nj,xn,xh,xydm,zydm,bjdm};
		String queryStr = request.getParameter("queryStr");
		String[] queryColsArr = queryStr.split("!!");
		String[] queryColsArrCn = dao.getColumnNameCN(queryColsArr,
				tableName);
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.equals(""))) {
			xydm = userDep;
		}
		StringBuffer sql = new StringBuffer("select ");
		for (int i = 0; i < queryColsArr.length; i++) {
			sql.append(queryColsArr[i]);
			sql.append(i != queryColsArr.length - 1 ? "," : "");
		}
		sql.append(" from ");
		sql.append(tableName);
		// ������������
		if (nj != null && !(nj.equalsIgnoreCase(""))) {
			condi.append(" and nj='");
			condi.append(nj);
			condi.append("' ");
		}
		if (nd != null && !(nd.equalsIgnoreCase(""))) {
			condi.append(" and nd='");
			condi.append(nd);
			condi.append("' ");
		}
		if (xn != null && !(xn.equalsIgnoreCase(""))) {
			condi.append(" and xn='");
			condi.append(xn);
			condi.append("' ");
		}
		if (xh != null && !(xh.trim().equalsIgnoreCase(""))) {
			condi.append(" and xh='");
			condi.append(xh);
			condi.append("' ");
		}
		if (xydm != null && !(xydm.trim().equalsIgnoreCase(""))) {
			condi.append(" and xydm='");
			condi.append(xydm);
			condi.append("' ");
		}
		if (zydm != null && !(zydm.trim().equalsIgnoreCase(""))) {
			condi.append(" and zydm='");
			condi.append(zydm);
			condi.append("' ");
		}
		if (bjdm != null && !(bjdm.trim().equalsIgnoreCase(""))) {
			condi.append(" and bjdm='");
			condi.append(bjdm);
			condi.append("' ");
		}
		sql.append(condi.toString());
		Vector<Object> rs = new Vector<Object>();
		rs.addAll(dao.rsToVator(sql.toString(), new String[] {},
				queryColsArr));
		try {
			response.reset();
			OutputStream output = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportDataFor(rs, queryColsArrCn, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("xszzhzExp");
	}

	/**
	 * @describe ��������ҳ��
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		String xxdm = StandardOperation.getXxdm();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		String act = Base.chgNull(request.getParameter("act"), "", 1);
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String xh = "";
		if (!userType.equalsIgnoreCase("stu")) {
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
		}else{
			xh = sUName;
		}
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String nd = Base.currNd;

		StringBuffer sb = new StringBuffer(" xh='");
		sb.append(xh);
		sb.append("' ");
		map.put("xmmc", dao.getOneRs("select xmmc from XSZZ_COMMON_NEW_ZZXMDMB where xmdm=?", new String[]{xmdm}, "xmmc"));
		List<String[]> zdyzdList = xszzDao.getXszzZdyzdList(xmdm);
		String zdyzdXxxx = xszzDao.getXszzZdyzdXxxxList(xmdm);
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
			pkVal = nd + xmdm + xh;
		}
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;
			sfkns = dao.isKns(xh);
			
			boolean b = true;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {//�й���ҵ��ѧ������Ⱥ�ж�
				b = false;
				List<HashMap<String, String>> zzxmL = dao
				.getList(
						"select tsrqdm from xszz_zgktdx_tsrqxmwh where zzxmdm=?",
						new String[] { xmdm },
						new String[] { "tsrqdm" });
				if (zzxmL.size() != 0 && !"".equalsIgnoreCase(xh)) {
					for (Iterator<HashMap<String, String>> it = zzxmL.iterator(); it.hasNext() && !b;) {
						HashMap<String, String> hTsrq = it.next();
						List<HashMap<String, String>> xhL = dao
						.getList(
								"select xh from XSZZ_ZGKD_TSRQ where nd=? and tsrqdm=?",
								new String[] { nd, hTsrq.get("tsrqdm") },
								new String[] { "xh" });
						for (Iterator<HashMap<String, String>> its = xhL.iterator(); its.hasNext() && !b;) {
							HashMap<String, String> hXh = its.next();
							if (xh.equals(hXh.get("xh"))){
								b = true;
							}
						}
					}
				} else {
					b = true;
				}
			}

			sql1 = "select a.kssj,a.jssj,a.sfkns from view_xszz_common_new_zzsjb a,view_xsjbxx b where a.xmdm=? and b.xh=? and a.xydm=b.xydm";
			jxjksjssj = dao.getOneRs(sql1, new String[] { xmdm, xh },
					new String[] { "kssj", "jssj", "sfkns" });
			if (jxjksjssj == null && b){
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
			} else if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0
					&& (("��".equalsIgnoreCase(jxjksjssj[2]) || ("".equalsIgnoreCase(jxjksjssj[2]))) || ("��"
							.equalsIgnoreCase(jxjksjssj[2]) && sfkns)) && b) {// /������ʱ�䷶Χ��
//			if (jxjksjssj != null
//					&& jxjksjssj[0].compareToIgnoreCase(rightNow) < 0
//					&& jxjksjssj[1].compareToIgnoreCase(rightNow) > 0
//					&& (("��".equalsIgnoreCase(jxjksjssj[2]) || ("".equalsIgnoreCase(jxjksjssj[2]))) || ("��"
//							.equalsIgnoreCase(jxjksjssj[2]) && sfkns)) && b) {// /������ʱ�䷶Χ��
				
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {// /ѧ����д����
					String xmmc = Base.chgNull(request.getParameter("xmmc"),"", 1);
					String sqly = Base.chgNull(request.getParameter("sqly"),"", 1);
					String lxdh = Base.chgNull(request.getParameter("lxdh"),"",1);
					String rxqhk = Base.chgNull(request.getParameter("rxqhk"),"",1);
					String jtzz = Base.chgNull(request.getParameter("jtzz"),"",1);
					String yzbm = Base.chgNull(request.getParameter("yzbm"),"",1);
					String jtlxdh = Base.chgNull(request.getParameter("jtlxdh"),"",1);
					String sfyycjcshzyhd = Base.chgNull(request.getParameter("sfyycjcshzyhd"),"",1);
					String sfyysqgjzxdkhqgzx = Base.chgNull(request.getParameter("sfyysqgjzxdkhqgzx"),"",1);
					String sfjq = Base.chgNull(request.getParameter("sfjq"),"",1);
					String sfge = Base.chgNull(request.getParameter("sfge"),"",1);
					String sfdq = Base.chgNull(request.getParameter("sfdq"),"",1);
					String sfcj = Base.chgNull(request.getParameter("sfcj"),"",1);
					String sfjls = Base.chgNull(request.getParameter("sfjls"),"",1);
					String sfly = Base.chgNull(request.getParameter("sfly"),"",1);
					String sfzb = Base.chgNull(request.getParameter("sfzb"),"",1);
					String jtcy1_xm = Base.chgNull(request.getParameter("jtcy1_xm"),"",1);
					String jtcy1_nl = Base.chgNull(request.getParameter("jtcy1_nl"),"",1);
					String jtcy1_gx = Base.chgNull(request.getParameter("jtcy1_gx"),"",1);
					String jtcy1_gzdw = Base.chgNull(request.getParameter("jtcy1_gzdw"),"",1);
					String jtcy1_zy = Base.chgNull(request.getParameter("jtcy1_zy"),"",1);
					String jtcy1_nsr = Base.chgNull(request.getParameter("jtcy1_nsr"),"",1);
					String jtcy1_jkzk = Base.chgNull(request.getParameter("jtcy1_jkzk"),"",1);
					String jtcy2_xm = Base.chgNull(request.getParameter("jtcy2_xm"),"",1);
					String jtcy2_nl = Base.chgNull(request.getParameter("jtcy2_nl"),"",1);
					String jtcy2_gx = Base.chgNull(request.getParameter("jtcy2_gx"),"",1);
					String jtcy2_gzdw = Base.chgNull(request.getParameter("jtcy2_gzdw"),"",1);
					String jtcy2_zy = Base.chgNull(request.getParameter("jtcy2_zy"),"",1);
					String jtcy2_nsr = Base.chgNull(request.getParameter("jtcy2_nsr"),"",1);
					String jtcy2_jkzk = Base.chgNull(request.getParameter("jtcy2_jkzk"),"",1);
					String jtcy3_xm = Base.chgNull(request.getParameter("jtcy3_xm"),"",1);
					String jtcy3_nl = Base.chgNull(request.getParameter("jtcy3_nl"),"",1);
					String jtcy3_gx = Base.chgNull(request.getParameter("jtcy3_gx"),"",1);
					String jtcy3_gzdw = Base.chgNull(request.getParameter("jtcy3_gzdw"),"",1);
					String jtcy3_zy = Base.chgNull(request.getParameter("jtcy3_zy"),"",1);
					String jtcy3_nsr = Base.chgNull(request.getParameter("jtcy3_nsr"),"",1);
					String jtcy3_jkzk = Base.chgNull(request.getParameter("jtcy3_jkzk"),"",1);
					String jtcy4_xm = Base.chgNull(request.getParameter("jtcy4_xm"),"",1);
					String jtcy4_nl = Base.chgNull(request.getParameter("jtcy4_nl"),"",1);
					String jtcy4_gx = Base.chgNull(request.getParameter("jtcy4_gx"),"",1);
					String jtcy4_gzdw = Base.chgNull(request.getParameter("jtcy4_gzdw"),"",1);
					String jtcy4_zy = Base.chgNull(request.getParameter("jtcy4_zy"),"",1);
					String jtcy4_nsr = Base.chgNull(request.getParameter("jtcy4_nsr"),"",1);
					String jtcy4_jkzk = Base.chgNull(request.getParameter("jtcy4_jkzk"),"",1);
					String jtcy5_xm = Base.chgNull(request.getParameter("jtcy5_xm"),"",1);
					String jtcy5_nl = Base.chgNull(request.getParameter("jtcy5_nl"),"",1);
					String jtcy5_gx = Base.chgNull(request.getParameter("jtcy5_gx"),"",1);
					String jtcy5_gzdw = Base.chgNull(request.getParameter("jtcy5_gzdw"),"",1);
					String jtcy5_zy = Base.chgNull(request.getParameter("jtcy5_zy"),"",1);
					String jtcy5_nsr = Base.chgNull(request.getParameter("jtcy5_nsr"),"",1);
					String jtcy5_jkzk = Base.chgNull(request.getParameter("jtcy5_jkzk"),"",1);
					String jtrjnsr = Base.chgNull(request.getParameter("jtrjnsr"),"",1);
					String xszbdszqk = Base.chgNull(request.getParameter("xszbdszqk"),"",1);
					String jtzszrzhqk = Base.chgNull(request.getParameter("jtzszrzhqk"),"",1);
					String jtzstfywsj = Base.chgNull(request.getParameter("jtzstfywsj"),"",1);
					String qtqk = Base.chgNull(request.getParameter("qtqk"),"",1);
					String mzbm_txdz = Base.chgNull(request.getParameter("mzbm_txdz"),"",1);
					String mzbm_yzbm = Base.chgNull(request.getParameter("mzbm_yzbm"),"",1);
					String mzbm_lxdh = Base.chgNull(request.getParameter("mzbm_lxdh"),"",1);
					String syd = Base.chgNull(request.getParameter("syd"),"",1);
					String bjgmc = Base.chgNull(request.getParameter("bjgmc"),"",1);
					String zycjpm = Base.chgNull(request.getParameter("zycjpm"),"",1);
					String zyrs = Base.chgNull(request.getParameter("zyrs"),"",1);
					String kns = "";
					if(sfkns){
						kns = "��";
					}else{
						kns = "��";
					}

					sql = "select count(*) num from xszz_common_new_zzbbxssqb where nd||xmdm||xh=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert(
								"xszz_common_new_zzbbxssqb", new String[] {
										"nd", "xmdm", "xmmc", "xh", "sqly",
										"lxdh", "rxqhk", "jtzz", "yzbm",
										"jtlxdh", "sfyycjcshzyhd",
										"sfyysqgjzxdkhqgzx", "sfjq", "sfge",
										"sfdq", "sfcj", "sfjls", "sfly",
										"sfzb", "jtcy1_xm", "jtcy1_nl",
										"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy",
										"jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm",
										"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
										"jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk",
										"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
										"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr",
										"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl",
										"jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
										"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm",
										"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
										"jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk",
										"jtrjnsr", "xszbdszqk", "jtzszrzhqk",
										"jtzstfywsj", "qtqk", "mzbm_txdz",
										"mzbm_yzbm", "mzbm_lxdh", "sfkns",
										"syd", "bjgmc", "zycjpm", "zyrs" },
								new String[] { nd, xmdm, xmmc, xh, sqly, lxdh,
										rxqhk, jtzz, yzbm, jtlxdh,
										sfyycjcshzyhd, sfyysqgjzxdkhqgzx, sfjq,
										sfge, sfdq, sfcj, sfjls, sfly, sfzb,
										jtcy1_xm, jtcy1_nl, jtcy1_gx,
										jtcy1_gzdw, jtcy1_zy, jtcy1_nsr,
										jtcy1_jkzk, jtcy2_xm, jtcy2_nl,
										jtcy2_gx, jtcy2_gzdw, jtcy2_zy,
										jtcy2_nsr, jtcy2_jkzk, jtcy3_xm,
										jtcy3_nl, jtcy3_gx, jtcy3_gzdw,
										jtcy3_zy, jtcy3_nsr, jtcy3_jkzk,
										jtcy4_xm, jtcy4_nl, jtcy4_gx,
										jtcy4_gzdw, jtcy4_zy, jtcy4_nsr,
										jtcy4_jkzk, jtcy5_xm, jtcy5_nl,
										jtcy5_gx, jtcy5_gzdw, jtcy5_zy,
										jtcy5_nsr, jtcy5_jkzk, jtrjnsr,
										xszbdszqk, jtzszrzhqk, jtzstfywsj,
										qtqk, mzbm_txdz, mzbm_yzbm, mzbm_lxdh,
										kns, syd, bjgmc, zycjpm, zyrs },
								request);
						if (ok) {
							if (zdyzdList.size() != 0) {
								for (Iterator it = zdyzdList.iterator(); it
										.hasNext();) {
									String[] tempSr = (String[]) it.next();
									String srName = "zdy" + tempSr[0];
									String sr = Base.chgNull(request
											.getParameter(srName), "", 1);
									sb.append(",");
									sb.append(srName);
									sb.append("='");
									sb.append(sr);
									sb.append("'");
								}
								sql = "update xszz_common_new_zzbbxssqb set "
										+ sb.toString()
										+ " where nd||xmdm||xh='" + pkVal + "'";
								StandardOperation.update("xszz_common_new_zzbbxssqb", sql, request);
							}
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						sql = "select count(*) num from xszz_common_new_zzbbxssqb where nd||xmdm||xh=? and xxsh='ͨ��'";
						num = dao.getOneRs(sql, new String[] { pkVal }, "num");
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update(
									"xszz_common_new_zzbbxssqb", new String[] {
											"xmmc", "sqly", "sqsj", "xysh",
											"xyshyj", "xyshsj", "xypzje",
											"xxsh", "xxshyj", "xxshsj",
											"xxpzje", "lxdh", "rxqhk", "jtzz",
											"yzbm", "jtlxdh", "sfyycjcshzyhd",
											"sfyysqgjzxdkhqgzx", "sfjq",
											"sfge", "sfdq", "sfcj", "sfjls",
											"sfly", "sfzb", "jtcy1_xm",
											"jtcy1_nl", "jtcy1_gx",
											"jtcy1_gzdw", "jtcy1_zy",
											"jtcy1_nsr", "jtcy1_jkzk",
											"jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
											"jtcy2_gzdw", "jtcy2_zy",
											"jtcy2_nsr", "jtcy2_jkzk",
											"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
											"jtcy3_gzdw", "jtcy3_zy",
											"jtcy3_nsr", "jtcy3_jkzk",
											"jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
											"jtcy4_gzdw", "jtcy4_zy",
											"jtcy4_nsr", "jtcy4_jkzk",
											"jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
											"jtcy5_gzdw", "jtcy5_zy",
											"jtcy5_nsr", "jtcy5_jkzk",
											"jtrjnsr", "xszbdszqk",
											"jtzszrzhqk", "jtzstfywsj", "qtqk",
											"mzbm_txdz", "mzbm_yzbm",
											"mzbm_lxdh", "sfkns", "syd",
											"bjgmc", "zycjpm", "zyrs" },
									new String[] { xmmc, sqly, rightNow, "δ���",
											"", "", "0", "δ���", "", "", "0",
											lxdh, rxqhk, jtzz, yzbm, jtlxdh,
											sfyycjcshzyhd, sfyysqgjzxdkhqgzx,
											sfjq, sfge, sfdq, sfcj, sfjls,
											sfly, sfzb, jtcy1_xm, jtcy1_nl,
											jtcy1_gx, jtcy1_gzdw, jtcy1_zy,
											jtcy1_nsr, jtcy1_jkzk, jtcy2_xm,
											jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
											jtcy2_zy, jtcy2_nsr, jtcy2_jkzk,
											jtcy3_xm, jtcy3_nl, jtcy3_gx,
											jtcy3_gzdw, jtcy3_zy, jtcy3_nsr,
											jtcy3_jkzk, jtcy4_xm, jtcy4_nl,
											jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
											jtcy4_nsr, jtcy4_jkzk, jtcy5_xm,
											jtcy5_nl, jtcy5_gx, jtcy5_gzdw,
											jtcy5_zy, jtcy5_nsr, jtcy5_jkzk,
											jtrjnsr, xszbdszqk, jtzszrzhqk,
											jtzstfywsj, qtqk, mzbm_txdz,
											mzbm_yzbm, mzbm_lxdh, kns, syd,
											bjgmc, zycjpm, zyrs },
									"nd||xmdm||xh", pkVal, request);
							if (ok) {
								if (zdyzdList.size() != 0) {
									for (Iterator it = zdyzdList.iterator(); it
											.hasNext();) {
										String[] tempSr = (String[]) it.next();
										String srName = "zdy" + tempSr[0];
										String sr = Base.chgNull(request
												.getParameter(srName), "", 1);
										sb.append(",");
										sb.append(srName);
										sb.append("='");
										sb.append(sr);
										sb.append("'");
									}
									sql = "update xszz_common_new_zzbbxssqb set "
											+ sb.toString()
											+ " where nd||xmdm||xh='" + pkVal
											+ "'";
									StandardOperation.update("xszz_common_new_zzbbxssqb", sql, request);
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

			sql1 = "select a.sfkns from view_xszz_common_new_zzsjb a,view_xsjbxx b where a.xmdm=? and b.xh=? and a.xydm=b.xydm";
			String knsT = dao
					.getOneRs(sql1, new String[] { xmdm, xh }, "sfkns");
			
			boolean b = true;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {//�й���ҵ��ѧ������Ⱥ�ж�
				b = false;
				List<HashMap<String, String>> zzxmL = dao
				.getList(
						"select tsrqdm from xszz_zgktdx_tsrqxmwh where zzxmdm=?",
						new String[] { xmdm },
						new String[] { "tsrqdm" });
				if (zzxmL.size() != 0 && !"".equalsIgnoreCase(xh)) {
					for (Iterator<HashMap<String, String>> it = zzxmL.iterator(); it.hasNext() && !b;) {
						HashMap<String, String> hTsrq = it.next();
						List<HashMap<String, String>> xhL = dao
						.getList(
								"select xh from XSZZ_ZGKD_TSRQ where nd=? and tsrqdm=?",
								new String[] { nd, hTsrq.get("tsrqdm") },
								new String[] { "xh" });
						for (Iterator<HashMap<String, String>> its = xhL.iterator(); its.hasNext() && !b;) {
							HashMap<String, String> hXh = its.next();
							if (xh.equals(hXh.get("xh"))){
								b = true;
							}
						}
					}
				} else {
					b = true;
				}
			}
			
			if ((("��".equalsIgnoreCase(knsT) || ("".equalsIgnoreCase(knsT)))
					|| ("��".equalsIgnoreCase(knsT) && sfkns)) && b) {// /������ʱ�䷶Χ��
				
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {
					String xmmc = Base.chgNull(request.getParameter("xmmc"),
							"", 1);
					String sqly = Base.chgNull(request.getParameter("sqly"),
							"", 1);
					String lxdh = Base.chgNull(request.getParameter("lxdh"),"",1);
					String rxqhk = Base.chgNull(request.getParameter("rxqhk"),"",1);
					String jtzz = Base.chgNull(request.getParameter("jtzz"),"",1);
					String yzbm = Base.chgNull(request.getParameter("yzbm"),"",1);
					String jtlxdh = Base.chgNull(request.getParameter("jtlxdh"),"",1);
					String sfyycjcshzyhd = Base.chgNull(request.getParameter("sfyycjcshzyhd"),"",1);
					String sfyysqgjzxdkhqgzx = Base.chgNull(request.getParameter("sfyysqgjzxdkhqgzx"),"",1);
					String sfjq = Base.chgNull(request.getParameter("sfjq"),"",1);
					String sfge = Base.chgNull(request.getParameter("sfge"),"",1);
					String sfdq = Base.chgNull(request.getParameter("sfdq"),"",1);
					String sfcj = Base.chgNull(request.getParameter("sfcj"),"",1);
					String sfjls = Base.chgNull(request.getParameter("sfjls"),"",1);
					String sfly = Base.chgNull(request.getParameter("sfly"),"",1);
					String sfzb = Base.chgNull(request.getParameter("sfzb"),"",1);
					String jtcy1_xm = Base.chgNull(request.getParameter("jtcy1_xm"),"",1);
					String jtcy1_nl = Base.chgNull(request.getParameter("jtcy1_nl"),"",1);
					String jtcy1_gx = Base.chgNull(request.getParameter("jtcy1_gx"),"",1);
					String jtcy1_gzdw = Base.chgNull(request.getParameter("jtcy1_gzdw"),"",1);
					String jtcy1_zy = Base.chgNull(request.getParameter("jtcy1_zy"),"",1);
					String jtcy1_nsr = Base.chgNull(request.getParameter("jtcy1_nsr"),"",1);
					String jtcy1_jkzk = Base.chgNull(request.getParameter("jtcy1_jkzk"),"",1);
					String jtcy2_xm = Base.chgNull(request.getParameter("jtcy2_xm"),"",1);
					String jtcy2_nl = Base.chgNull(request.getParameter("jtcy2_nl"),"",1);
					String jtcy2_gx = Base.chgNull(request.getParameter("jtcy2_gx"),"",1);
					String jtcy2_gzdw = Base.chgNull(request.getParameter("jtcy2_gzdw"),"",1);
					String jtcy2_zy = Base.chgNull(request.getParameter("jtcy2_zy"),"",1);
					String jtcy2_nsr = Base.chgNull(request.getParameter("jtcy2_nsr"),"",1);
					String jtcy2_jkzk = Base.chgNull(request.getParameter("jtcy2_jkzk"),"",1);
					String jtcy3_xm = Base.chgNull(request.getParameter("jtcy3_xm"),"",1);
					String jtcy3_nl = Base.chgNull(request.getParameter("jtcy3_nl"),"",1);
					String jtcy3_gx = Base.chgNull(request.getParameter("jtcy3_gx"),"",1);
					String jtcy3_gzdw = Base.chgNull(request.getParameter("jtcy3_gzdw"),"",1);
					String jtcy3_zy = Base.chgNull(request.getParameter("jtcy3_zy"),"",1);
					String jtcy3_nsr = Base.chgNull(request.getParameter("jtcy3_nsr"),"",1);
					String jtcy3_jkzk = Base.chgNull(request.getParameter("jtcy3_jkzk"),"",1);
					String jtcy4_xm = Base.chgNull(request.getParameter("jtcy4_xm"),"",1);
					String jtcy4_nl = Base.chgNull(request.getParameter("jtcy4_nl"),"",1);
					String jtcy4_gx = Base.chgNull(request.getParameter("jtcy4_gx"),"",1);
					String jtcy4_gzdw = Base.chgNull(request.getParameter("jtcy4_gzdw"),"",1);
					String jtcy4_zy = Base.chgNull(request.getParameter("jtcy4_zy"),"",1);
					String jtcy4_nsr = Base.chgNull(request.getParameter("jtcy4_nsr"),"",1);
					String jtcy4_jkzk = Base.chgNull(request.getParameter("jtcy4_jkzk"),"",1);
					String jtcy5_xm = Base.chgNull(request.getParameter("jtcy5_xm"),"",1);
					String jtcy5_nl = Base.chgNull(request.getParameter("jtcy5_nl"),"",1);
					String jtcy5_gx = Base.chgNull(request.getParameter("jtcy5_gx"),"",1);
					String jtcy5_gzdw = Base.chgNull(request.getParameter("jtcy5_gzdw"),"",1);
					String jtcy5_zy = Base.chgNull(request.getParameter("jtcy5_zy"),"",1);
					String jtcy5_nsr = Base.chgNull(request.getParameter("jtcy5_nsr"),"",1);
					String jtcy5_jkzk = Base.chgNull(request.getParameter("jtcy5_jkzk"),"",1);
					String jtrjnsr = Base.chgNull(request.getParameter("jtrjnsr"),"",1);
					String xszbdszqk = Base.chgNull(request.getParameter("xszbdszqk"),"",1);
					String jtzszrzhqk = Base.chgNull(request.getParameter("jtzszrzhqk"),"",1);
					String jtzstfywsj = Base.chgNull(request.getParameter("jtzstfywsj"),"",1);
					String qtqk = Base.chgNull(request.getParameter("qtqk"),"",1);
					String mzbm_txdz = Base.chgNull(request.getParameter("mzbm_txdz"),"",1);
					String mzbm_yzbm = Base.chgNull(request.getParameter("mzbm_yzbm"),"",1);
					String mzbm_lxdh = Base.chgNull(request.getParameter("mzbm_lxdh"),"",1);
					String syd = Base.chgNull(request.getParameter("syd"),"",1);
					String bjgmc = Base.chgNull(request.getParameter("bjgmc"),"",1);
					String zycjpm = Base.chgNull(request.getParameter("zycjpm"),"",1);
					String zyrs = Base.chgNull(request.getParameter("zyrs"),"",1);
					String kns = "";
					if(sfkns){
						kns = "��";
					}else{
						kns = "��";
					}

					sql = "select count(*) num from xszz_common_new_zzbbxssqb where nd||xmdm||xh=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert(
								"xszz_common_new_zzbbxssqb", new String[] {
										"nd", "xmdm", "xmmc", "xh", "sqly",
										"lxdh", "rxqhk", "jtzz", "yzbm",
										"jtlxdh", "sfyycjcshzyhd",
										"sfyysqgjzxdkhqgzx", "sfjq", "sfge",
										"sfdq", "sfcj", "sfjls", "sfly",
										"sfzb", "jtcy1_xm", "jtcy1_nl",
										"jtcy1_gx", "jtcy1_gzdw", "jtcy1_zy",
										"jtcy1_nsr", "jtcy1_jkzk", "jtcy2_xm",
										"jtcy2_nl", "jtcy2_gx", "jtcy2_gzdw",
										"jtcy2_zy", "jtcy2_nsr", "jtcy2_jkzk",
										"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
										"jtcy3_gzdw", "jtcy3_zy", "jtcy3_nsr",
										"jtcy3_jkzk", "jtcy4_xm", "jtcy4_nl",
										"jtcy4_gx", "jtcy4_gzdw", "jtcy4_zy",
										"jtcy4_nsr", "jtcy4_jkzk", "jtcy5_xm",
										"jtcy5_nl", "jtcy5_gx", "jtcy5_gzdw",
										"jtcy5_zy", "jtcy5_nsr", "jtcy5_jkzk",
										"jtrjnsr", "xszbdszqk", "jtzszrzhqk",
										"jtzstfywsj", "qtqk", "mzbm_txdz",
										"mzbm_yzbm", "mzbm_lxdh", "sfkns",
										"syd", "bjgmc", "zycjpm", "zyrs" },
								new String[] { nd, xmdm, xmmc, xh, sqly, lxdh,
										rxqhk, jtzz, yzbm, jtlxdh,
										sfyycjcshzyhd, sfyysqgjzxdkhqgzx, sfjq,
										sfge, sfdq, sfcj, sfjls, sfly, sfzb,
										jtcy1_xm, jtcy1_nl, jtcy1_gx,
										jtcy1_gzdw, jtcy1_zy, jtcy1_nsr,
										jtcy1_jkzk, jtcy2_xm, jtcy2_nl,
										jtcy2_gx, jtcy2_gzdw, jtcy2_zy,
										jtcy2_nsr, jtcy2_jkzk, jtcy3_xm,
										jtcy3_nl, jtcy3_gx, jtcy3_gzdw,
										jtcy3_zy, jtcy3_nsr, jtcy3_jkzk,
										jtcy4_xm, jtcy4_nl, jtcy4_gx,
										jtcy4_gzdw, jtcy4_zy, jtcy4_nsr,
										jtcy4_jkzk, jtcy5_xm, jtcy5_nl,
										jtcy5_gx, jtcy5_gzdw, jtcy5_zy,
										jtcy5_nsr, jtcy5_jkzk, jtrjnsr,
										xszbdszqk, jtzszrzhqk, jtzstfywsj,
										qtqk, mzbm_txdz, mzbm_yzbm, mzbm_lxdh,
										kns, syd, bjgmc, zycjpm, zyrs },
								request);
						if (ok) {
							if (zdyzdList.size() != 0) {
								for (Iterator it = zdyzdList.iterator(); it
										.hasNext();) {
									String[] tempSr = (String[]) it.next();
									String srName = "zdy" + tempSr[0];
									String sr = Base.chgNull(request
											.getParameter(srName), "", 1);
									sb.append(",");
									sb.append(srName);
									sb.append("='");
									sb.append(sr);
									sb.append("'");
								}
								sql = "update xszz_common_new_zzbbxssqb set "
										+ sb.toString()
										+ " where nd||xmdm||xh='" + pkVal + "'";
								StandardOperation.update("xszz_common_new_zzbbxssqb", sql, request);
							}
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						sql = "select count(*) num from xszz_common_new_zzbbxssqb where nd||xmdm||xh=? and xxsh='ͨ��'";
						num = dao.getOneRs(sql, new String[] { pkVal }, "num");
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update(
									"xszz_common_new_zzbbxssqb", new String[] {
											"xmmc", "sqly", "sqsj", "xysh",
											"xyshyj", "xyshsj", "xypzje",
											"xxsh", "xxshyj", "xxshsj",
											"xxpzje", "lxdh", "rxqhk", "jtzz",
											"yzbm", "jtlxdh", "sfyycjcshzyhd",
											"sfyysqgjzxdkhqgzx", "sfjq",
											"sfge", "sfdq", "sfcj", "sfjls",
											"sfly", "sfzb", "jtcy1_xm",
											"jtcy1_nl", "jtcy1_gx",
											"jtcy1_gzdw", "jtcy1_zy",
											"jtcy1_nsr", "jtcy1_jkzk",
											"jtcy2_xm", "jtcy2_nl", "jtcy2_gx",
											"jtcy2_gzdw", "jtcy2_zy",
											"jtcy2_nsr", "jtcy2_jkzk",
											"jtcy3_xm", "jtcy3_nl", "jtcy3_gx",
											"jtcy3_gzdw", "jtcy3_zy",
											"jtcy3_nsr", "jtcy3_jkzk",
											"jtcy4_xm", "jtcy4_nl", "jtcy4_gx",
											"jtcy4_gzdw", "jtcy4_zy",
											"jtcy4_nsr", "jtcy4_jkzk",
											"jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
											"jtcy5_gzdw", "jtcy5_zy",
											"jtcy5_nsr", "jtcy5_jkzk",
											"jtrjnsr", "xszbdszqk",
											"jtzszrzhqk", "jtzstfywsj", "qtqk",
											"mzbm_txdz", "mzbm_yzbm",
											"mzbm_lxdh", "sfkns", "syd",
											"bjgmc", "zycjpm", "zyrs" },
									new String[] { xmmc, sqly, rightNow, "δ���",
											"", "", "0", "δ���", "", "", "0",
											lxdh, rxqhk, jtzz, yzbm, jtlxdh,
											sfyycjcshzyhd, sfyysqgjzxdkhqgzx,
											sfjq, sfge, sfdq, sfcj, sfjls,
											sfly, sfzb, jtcy1_xm, jtcy1_nl,
											jtcy1_gx, jtcy1_gzdw, jtcy1_zy,
											jtcy1_nsr, jtcy1_jkzk, jtcy2_xm,
											jtcy2_nl, jtcy2_gx, jtcy2_gzdw,
											jtcy2_zy, jtcy2_nsr, jtcy2_jkzk,
											jtcy3_xm, jtcy3_nl, jtcy3_gx,
											jtcy3_gzdw, jtcy3_zy, jtcy3_nsr,
											jtcy3_jkzk, jtcy4_xm, jtcy4_nl,
											jtcy4_gx, jtcy4_gzdw, jtcy4_zy,
											jtcy4_nsr, jtcy4_jkzk, jtcy5_xm,
											jtcy5_nl, jtcy5_gx, jtcy5_gzdw,
											jtcy5_zy, jtcy5_nsr, jtcy5_jkzk,
											jtrjnsr, xszbdszqk, jtzszrzhqk,
											jtzstfywsj, qtqk, mzbm_txdz,
											mzbm_yzbm, mzbm_lxdh, kns, syd,
											bjgmc, zycjpm, zyrs },
									"nd||xmdm||xh", pkVal, request);
							if (ok) {
								if (zdyzdList.size() != 0) {
									for (Iterator it = zdyzdList.iterator(); it
											.hasNext();) {
										String[] tempSr = (String[]) it.next();
										String srName = "zdy" + tempSr[0];
										String sr = Base.chgNull(request
												.getParameter(srName), "", 1);
										sb.append(",");
										sb.append(srName);
										sb.append("='");
										sb.append(sr);
										sb.append("'");
									}
									sql = "update xszz_common_new_zzbbxssqb set "
											+ sb.toString()
											+ " where nd||xmdm||xh='" + pkVal
											+ "'";
									StandardOperation.update("xszz_common_new_zzbbxssqb", sql, request);
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

		sql = "select nd,xmdm,xmmc,xh,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,syd,csrq,mzmc,zzmmmc,kh,rxny,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sfkns,bjgmc,zycjpm,zyrs from view_xszz_common_new_zzbbxssqb where 1=2";
		outString = dao.getColumnName(sql);// �����������
		sql = "select nd,xmdm,xmmc,xh,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,syd,csrq,mzmc,zzmmmc,kh,rxny,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sfkns,bjgmc,zycjpm,zyrs from view_xszz_common_new_zzbbxssqb where nd||xmdm||xh=? ";
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if(null != xh) {
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
				sql = "SELECT COUNT(*) num FROM view_xsjbxx WHERE zydm=(SELECT zydm FROM view_xsjbxx WHERE xh=?) AND nj=(SELECT nj FROM view_xsjbxx WHERE xh=?)";
				String num = dao.getOneRs(sql, new String[] { xh, xh }, "num");
				map.put("zyrs", num);
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
			for(String[] str : zdyzdList){
				st += ",zdy" + str[0];
			}
			sql = "select "+st+" from view_xszz_common_new_zzbbxssqb where 1=2";
			outString = dao.getColumnName(sql);// �����������
			sql = "select "+st+" from view_xszz_common_new_zzbbxssqb where nd||xmdm||xh=? ";
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
		
		if (null == map.get("xmdm") || "".equalsIgnoreCase(map.get("xmdm"))){
			map.put("xmdm", xmdm);
		}
		if(sfkns){
			request.setAttribute("isKns", "is");
		} else {
			request.setAttribute("isKns", "no");
		}
		request.setAttribute("zzxmList", xszzDao.getXszzZzxmList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xszzsq");
	}
	
	/**
	 * @describe �������뱨���ӡ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String bjgmc = Base.chgNull(request.getParameter("bjgmc"), "", 1);
		String zycjpm = Base.chgNull(request.getParameter("zycjpm"), "", 1);
		String zyrs = Base.chgNull(request.getParameter("zyrs"), "", 1);
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
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String xb = Base.chgNull(request.getParameter("xb"), "", 1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xymc = Base.chgNull(request.getParameter("xymc"), "", 1);
		String zymc = Base.chgNull(request.getParameter("zymc"), "", 1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"), "", 1);
		String ssbh = Base.chgNull(request.getParameter("ssbh"), "", 1);
		String qsdh = Base.chgNull(request.getParameter("qsdh"), "", 1);
		String syd = Base.chgNull(request.getParameter("syd"), "", 1);
		String csrq = Base.chgNull(request.getParameter("csrq"), "", 1);
		String mzmc = Base.chgNull(request.getParameter("mzmc"), "", 1);
		String zzmmmc = Base.chgNull(request.getParameter("zzmmmc"), "", 1);
		String kh = Base.chgNull(request.getParameter("kh"), "", 1);
		String rxny = Base.chgNull(request.getParameter("rxny"), "", 1);
		String lxdh = Base.chgNull(request.getParameter("lxdh"), "", 1);
		String rxqhk = Base.chgNull(request.getParameter("rxqhk"), "", 1);
		String jtzz = Base.chgNull(request.getParameter("jtzz"), "", 1);
		String yzbm = Base.chgNull(request.getParameter("yzbm"), "", 1);
		String jtlxdh = Base.chgNull(request.getParameter("jtlxdh"), "", 1);
		String sfyycjcshzyhd = Base.chgNull(request
				.getParameter("sfyycjcshzyhd"), "", 1);
		String sfyysqgjzxdkhqgzx = Base.chgNull(request
				.getParameter("sfyysqgjzxdkhqgzx"), "", 1);
		String sfjq = Base.chgNull(request.getParameter("sfjq"), "", 1);
		String sfge = Base.chgNull(request.getParameter("sfge"), "", 1);
		String sfdq = Base.chgNull(request.getParameter("sfdq"), "", 1);
		String sfcj = Base.chgNull(request.getParameter("sfcj"), "", 1);
		String sfjls = Base.chgNull(request.getParameter("sfjls"), "", 1);
		String sfly = Base.chgNull(request.getParameter("sfly"), "", 1);
		String sfzb = Base.chgNull(request.getParameter("sfzb"), "", 1);
		String jtcy1_xm = Base.chgNull(request.getParameter("jtcy1_xm"), "", 1);
		String jtcy1_nl = Base.chgNull(request.getParameter("jtcy1_nl"), "", 1);
		String jtcy1_gx = Base.chgNull(request.getParameter("jtcy1_gx"), "", 1);
		String jtcy1_gzdw = Base.chgNull(request.getParameter("jtcy1_gzdw"),
				"", 1);
		String jtcy1_zy = Base.chgNull(request.getParameter("jtcy1_zy"), "", 1);
		String jtcy1_nsr = Base.chgNull(request.getParameter("jtcy1_nsr"), "",
				1);
		String jtcy1_jkzk = Base.chgNull(request.getParameter("jtcy1_jkzk"),
				"", 1);
		String jtcy2_xm = Base.chgNull(request.getParameter("jtcy2_xm"), "", 1);
		String jtcy2_nl = Base.chgNull(request.getParameter("jtcy2_nl"), "", 1);
		String jtcy2_gx = Base.chgNull(request.getParameter("jtcy2_gx"), "", 1);
		String jtcy2_gzdw = Base.chgNull(request.getParameter("jtcy2_gzdw"),
				"", 1);
		String jtcy2_zy = Base.chgNull(request.getParameter("jtcy2_zy"), "", 1);
		String jtcy2_nsr = Base.chgNull(request.getParameter("jtcy2_nsr"), "",
				1);
		String jtcy2_jkzk = Base.chgNull(request.getParameter("jtcy2_jkzk"),
				"", 1);
		String jtcy3_xm = Base.chgNull(request.getParameter("jtcy3_xm"), "", 1);
		String jtcy3_nl = Base.chgNull(request.getParameter("jtcy3_nl"), "", 1);
		String jtcy3_gx = Base.chgNull(request.getParameter("jtcy3_gx"), "", 1);
		String jtcy3_gzdw = Base.chgNull(request.getParameter("jtcy3_gzdw"),
				"", 1);
		String jtcy3_zy = Base.chgNull(request.getParameter("jtcy3_zy"), "", 1);
		String jtcy3_nsr = Base.chgNull(request.getParameter("jtcy3_nsr"), "",
				1);
		String jtcy3_jkzk = Base.chgNull(request.getParameter("jtcy3_jkzk"),
				"", 1);
		String jtcy4_xm = Base.chgNull(request.getParameter("jtcy4_xm"), "", 1);
		String jtcy4_nl = Base.chgNull(request.getParameter("jtcy4_nl"), "", 1);
		String jtcy4_gx = Base.chgNull(request.getParameter("jtcy4_gx"), "", 1);
		String jtcy4_gzdw = Base.chgNull(request.getParameter("jtcy4_gzdw"),
				"", 1);
		String jtcy4_zy = Base.chgNull(request.getParameter("jtcy4_zy"), "", 1);
		String jtcy4_nsr = Base.chgNull(request.getParameter("jtcy4_nsr"), "",
				1);
		String jtcy4_jkzk = Base.chgNull(request.getParameter("jtcy4_jkzk"),
				"", 1);
		String jtcy5_xm = Base.chgNull(request.getParameter("jtcy5_xm"), "", 1);
		String jtcy5_nl = Base.chgNull(request.getParameter("jtcy5_nl"), "", 1);
		String jtcy5_gx = Base.chgNull(request.getParameter("jtcy5_gx"), "", 1);
		String jtcy5_gzdw = Base.chgNull(request.getParameter("jtcy5_gzdw"),
				"", 1);
		String jtcy5_zy = Base.chgNull(request.getParameter("jtcy5_zy"), "", 1);
		String jtcy5_nsr = Base.chgNull(request.getParameter("jtcy5_nsr"), "",
				1);
		String jtcy5_jkzk = Base.chgNull(request.getParameter("jtcy5_jkzk"),
				"", 1);
		String jtrjnsr = Base.chgNull(request.getParameter("jtrjnsr"), "", 1);
		String xszbdszqk = Base.chgNull(request.getParameter("xszbdszqk"), "",
				1);
		String jtzszrzhqk = Base.chgNull(request.getParameter("jtzszrzhqk"),
				"", 1);
		String jtzstfywsj = Base.chgNull(request.getParameter("jtzstfywsj"),
				"", 1);
		String qtqk = Base.chgNull(request.getParameter("qtqk"), "", 1);
		String mzbm_txdz = Base.chgNull(request.getParameter("mzbm_txdz"), "",
				1);
		String mzbm_yzbm = Base.chgNull(request.getParameter("mzbm_yzbm"), "",
				1);
		String mzbm_lxdh = Base.chgNull(request.getParameter("mzbm_lxdh"), "",
				1);
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
			sqsj = sqsj_year + "��" + sqsj_mon + "��" + sqsj_day + "��";
		}
		if(xyshsj != null && !"".equalsIgnoreCase(xyshsj)){
			xyshsj_year = xyshsj.substring(0,4);
			xyshsj_mon = xyshsj.substring(5, 7);
			xyshsj_day = xyshsj.substring(8);
			xyshsj = xyshsj_year + "��" + xyshsj_mon + "��" + xyshsj_day + "��";
		}
		if(xxshsj != null && !"".equalsIgnoreCase(xxshsj)){
			xxshsj_year = xxshsj.substring(0,4);
			xxshsj_mon = xxshsj.substring(5, 7);
			xxshsj_day = xxshsj.substring(8);
			xxshsj = xxshsj_year + "��" + xxshsj_mon + "��" + xxshsj_day + "��";
		}

		CLOB clob = dao.getOneClob(
				"select bbgs from xszz_common_new_bbgsb where xmdm=?",
				new String[] { xmdm }, "bbgs");
		String htmlStr = "";
		if (null != clob) {
			htmlStr = clob.getSubString(1L, (int) clob.length());
		}
		String[] outValue = new String[] { nd, xmdm, xmmc, xh, xm, xb, sfzh,
				nj, xymc, zymc, bjmc, ssbh, qsdh, csrq, rxny, mzmc, zzmmmc, kh,
				lxdh, rxqhk, jtzz, yzbm, jtlxdh, sfyycjcshzyhd,
				sfyysqgjzxdkhqgzx, sfjq, sfge, sfdq, sfcj, sfjls, sfly, sfzb,
				jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdw, jtcy1_zy, jtcy1_nsr,
				jtcy1_jkzk, jtcy2_xm, jtcy2_nl, jtcy2_gx, jtcy2_gzdw, jtcy2_zy,
				jtcy2_nsr, jtcy2_jkzk, jtcy3_xm, jtcy3_nl, jtcy3_gx,
				jtcy3_gzdw, jtcy3_zy, jtcy3_nsr, jtcy3_jkzk, jtcy4_xm,
				jtcy4_nl, jtcy4_gx, jtcy4_gzdw, jtcy4_zy, jtcy4_nsr,
				jtcy4_jkzk, jtcy5_xm, jtcy5_nl, jtcy5_gx, jtcy5_gzdw, jtcy5_zy,
				jtcy5_nsr, jtcy5_jkzk, jtrjnsr, xszbdszqk, jtzszrzhqk,
				jtzstfywsj, qtqk, mzbm_txdz, mzbm_yzbm, mzbm_lxdh, sqly, sqsj,
				sqsj_year, sqsj_mon, sqsj_day, xysh, xyshyj, xyshsj,
				xyshsj_year, xyshsj_mon, xyshsj_day, xypzje, xxsh, xxshyj,
				xxshsj, xxshsj_year, xxshsj_mon, xxshsj_day, xxpzje, syd,
				bjgmc, zycjpm, zyrs };
		String[] outString = new String[] { "$���$", "$��������$", "$��������$", "$ѧ��$",
				"$����$", "$�Ա�$", "$���֤��$", "$�꼶$", "$ѧԺ����$", "$רҵ����$", "$�༶����$",
				"$������$", "$���ҵ绰$", "$��������$", "$��ѧ����$", "$��������$", "$������ò$",
				"$����$", "$��ϵ�绰$", "$��ѧǰ����$", "$��ͥסַ$", "$��������$", "$��ͥ��ϵ�绰$",
				"$�Ƿ�Ը��μӴ��ƻ�־Ը�$", "$�Ƿ�Ը�����������ѧ������ڹ���ѧ$", "$�Ƿ�ȫ$", "$�Ƿ�¶�$",
				"$�Ƿ���$", "$�Ƿ�м�$", "$�Ƿ������$", "$�Ƿ�����$", "$�Ƿ��ز�$",
				"$��ͥ��Ա1_����$", "$��ͥ��Ա1_����$", "$��ͥ��Ա1_��ѧ����ϵ$",
				"$��ͥ��Ա1_����(ѧϰ)��λ$", "$��ͥ��Ա1_ְҵ$", "$��ͥ��Ա1_������(Ԫ)$",
				"$��ͥ��Ա1_����״��$", "$��ͥ��Ա2_����$", "$��ͥ��Ա2_����$", "$��ͥ��Ա2_��ѧ����ϵ$",
				"$��ͥ��Ա2_����(ѧϰ)��λ$", "$��ͥ��Ա2_ְҵ$", "$��ͥ��Ա2_������(Ԫ)$",
				"$��ͥ��Ա2_����״��$", "$��ͥ��Ա3_����$", "$��ͥ��Ա3_����$", "$��ͥ��Ա3_��ѧ����ϵ$",
				"$��ͥ��Ա3_����(ѧϰ)��λ$", "$��ͥ��Ա3_ְҵ$", "$��ͥ��Ա3_������(Ԫ)$",
				"$��ͥ��Ա3_����״��$", "$��ͥ��Ա4_����$", "$��ͥ��Ա4_����$", "$��ͥ��Ա4_��ѧ����ϵ$",
				"$��ͥ��Ա4_����(ѧϰ)��λ$", "$��ͥ��Ա4_ְҵ$", "$��ͥ��Ա4_������(Ԫ)$",
				"$��ͥ��Ա4_����״��$", "$��ͥ��Ա5_����$", "$��ͥ��Ա5_����$", "$��ͥ��Ա5_��ѧ����ϵ$",
				"$��ͥ��Ա5_����(ѧϰ)��λ$", "$��ͥ��Ա5_ְҵ$", "$��ͥ��Ա5_������(Ԫ)$",
				"$��ͥ��Ա5_����״��$", "$��ͥ�˾�������$", "$ѧ���ڱ����������$", "$��ͥ������Ȼ�ֺ����$",
				"$��ͥ����ͻ�������¼�$", "$�������$", "$��������ͨѶ��ַ$", "$����������������$",
				"$����������ϵ�绰$", "$��������$", "$����ʱ��$", "$����ʱ��_��$", "$����ʱ��_��$",
				"$����ʱ��_��$", "$ѧԺ���$", "$ѧԺ������$", "$ѧԺ���ʱ��$", "$ѧԺ���ʱ��_��$",
				"$ѧԺ���ʱ��_��$", "$ѧԺ���ʱ��_��$", "$ѧԺ��׼���$", "$ѧУ���$", "$ѧУ������$",
				"$ѧУ���ʱ��$", "$ѧУ���ʱ��_��$", "$ѧУ���ʱ��_��$", "$ѧУ���ʱ��_��$",
				"$ѧУ��׼���$", "$��Դ��$", "$�������Ŀ��$", "$רҵ�ɼ�����$", "$רҵ����$" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null && !(outValue[i].equals(""))) {
				htmlStr = htmlStr.replace(outString[i], outValue[i]);
			} else {
				htmlStr = htmlStr.replace(outString[i], " ");
			}
		}

		List<String[]> zdyzdList = xszzDao.getXszzZdyzdList(xmdm);
		if (zdyzdList.size() != 0) {
			for (Iterator it = zdyzdList.iterator(); it.hasNext();) {
				String[] tempSr = (String[]) it.next();
				String srName = "zdy" + tempSr[0] + "";
				String sr = Base.chgNull(request.getParameter(srName), "", 1);
				htmlStr = htmlStr.replace("$"+srName+"$", sr);
			}
		}
		
		request.setAttribute("htmlStr", htmlStr);
		request.setAttribute("xmmc", xmmc);
		return mapping.findForward("xszzsqb");
	}

	/**
	 * @describe ��������б�
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		CommanForm actionForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
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
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		realTable = "xszz_common_new_zzbbxssqb";
		pk = "nd||xmdm||xh";
		tableName = "view_xszz_common_new_zzbbxssqb";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
		}
		if (!isNull(xmdm)) {
			querry.append(" and xmdm='");
			querry.append(xmdm);
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
		String sqlt = "";
		querry.append(querry1.toString());
		tips = "��ǰ����λ�ã�ѧ������ - ��� - ������Ŀ���";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "��ǰ����λ�ã�ѧ������ - ���� - ��������ѯ - ������Ŀ";
			colList = new String[] { "bgcolor", "����", "pk2", "r", "nd", "xmmc", "xh", "xm",
					"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "xysh", "xxsh", "sqsj", "sfkns" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select * from (select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString(); 
				request.setAttribute("isXX", "is");
			} else {
				sql = "select * from (select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString()+" and xydm='"
					+ userDep
					+ "'"; 
				request.setAttribute("isXX", "no");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "����", "pk2", "r", "nd", "xmmc", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "sqsj", "sfkns", "xysh", "" };
			}else{
				colList = new String[] { "bgcolor", "����", "pk2", "r", "nd", "xmmc", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "sqsj", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select * from (select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='ͨ��' order by xxsh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString()+" and xysh='ͨ��'"; 
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				sql = "select * from (select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString()+" and xydm='"
					+ userDep
					+ "'"; 
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
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("xmdm", xmdm);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		
		String xxdm = StandardOperation.getXxdm();
		
		if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm)){
			request.setAttribute("xxmc", "zgkydx");
		}
		actionForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(sqlt, new String[]{}, "num")));
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// ���Ͷ�дȨ��
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("zzxmList", xszzDao.getXszzZzxmList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("xszzsh");
	}
	
	/**
	 * @describe ������˵�����Ϣ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzshXxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
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
		String zzje = Base.chgNull(request.getParameter("zzje"), "", 1);
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
					sqlT[i] = "delete xszz_common_new_zzbbxssqb where nd||xmdm||xh='"+pkT+"' and xxsh<>'ͨ��'";
				} else {
					sqlT[i] = "delete xszz_common_new_zzbbxssqb where nd||xmdm||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=xszzsh&go=go", false);
			return newFwd;
		}
		
		if ("pass".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update xszz_common_new_zzbbxssqb set xysh='ͨ��',xyshsj='"
							+ now
							+ "',xypzje='0' where nd||xmdm||xh='"
							+ pkT
							+ "'";
				} else {
					String xypzjeT = dao
							.getOneRs(
									"select xypzje from xszz_common_new_zzbbxssqb where nd||xmdm||xh=?",
									new String[] { pkT }, "xypzje");
					sqlT[i] = "update xszz_common_new_zzbbxssqb set xxsh='ͨ��',xxshsj='"
							+ now
							+ "',xxpzje='"
							+ xypzjeT
							+ "' where nd||xmdm||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=xszzsh&go=go", false);
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
									"select xxsh from xszz_common_new_zzbbxssqb where nd||xmdm||xh=?",
									new String[] { pkT }, "xxsh");
					if (!"ͨ��".equalsIgnoreCase(xxshT)) {
						sqlT[i] = "update xszz_common_new_zzbbxssqb set xysh='��ͨ��',xyshsj='"
								+ now
								+ "',xypzje='0' where nd||xmdm||xh='"
								+ pkT + "'";
					}
				} else {
					sqlT[i] = "update xszz_common_new_zzbbxssqb set xxsh='��ͨ��',xxshsj='"
							+ now
							+ "',xxpzje='0' where nd||xmdm||xh='"
							+ pkT
							+ "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=xszzsh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			boolean b = false;
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				String xxshT = dao.getOneRs("select xxsh from xszz_common_new_zzbbxssqb where nd||xmdm||xh=?", new String[]{pkVal}, "xxsh");
				if("ͨ��".equalsIgnoreCase(xxshT)){
					request.setAttribute("xxshjg", "pass");
				} else {
					b = StandardOperation
							.update(
									"xszz_common_new_zzbbxssqb",
									new String[] { "xysh", "xyshyj", "xyshsj",
											"xypzje" },
									new String[] { yesNo, xyshyj, now, zzje },
									"nd||xmdm||xh", pkVal, request);
				}
			} else {
				b = StandardOperation.update("xszz_common_new_zzbbxssqb", new String[] {
						"xxsh", "xxshyj", "xxshsj", "xxpzje" }, new String[] {
						yesNo, xxshyj, now, zzje }, "nd||xmdm||xh", pkVal,
						request);
			}
			if(b){
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		realTable = "xszz_common_new_zzbbxssqb";
		pk = "nd||xmdm||xh";
		sql = "select nd,xmdm,xh,sqly,sqsj,xysh,xyshyj,xyshsj,xypzje,xxsh,xxshyj,xxshsj,xxpzje,xmmc,xm,xb,sfzh,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,ssbh,qsdh,syd,csrq,mzmc,zzmmmc,kh,rxny,lxdh,rxqhk,jtzz,yzbm,jtlxdh,sfyycjcshzyhd,sfyysqgjzxdkhqgzx,sfjq,sfge,sfdq,sfcj,sfjls,sfly,sfzb,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdw,jtcy1_zy,jtcy1_nsr,jtcy1_jkzk,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdw,jtcy2_zy,jtcy2_nsr,jtcy2_jkzk,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdw,jtcy3_zy,jtcy3_nsr,jtcy3_jkzk,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdw,jtcy4_zy,jtcy4_nsr,jtcy4_jkzk,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdw,jtcy5_zy,jtcy5_nsr,jtcy5_jkzk,jtrjnsr,xszbdszqk,jtzszrzhqk,jtzstfywsj,qtqk,mzbm_txdz,mzbm_yzbm,mzbm_lxdh,sfkns,bjgmc,zycjpm,zyrs from view_xszz_common_new_zzbbxssqb where 1=2";
		String[] outString = dao.getColumnName(sql);// �����������
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,a.nd,a.xmdm,a.xh,a.sqly,a.sqsj,a.xysh,a.xyshyj,a.xyshsj,a.xypzje,a.xxsh,a.xxshyj,a.xxshsj,a.xxpzje,a.xmmc,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.ssbh,a.qsdh,a.syd,a.csrq,a.mzmc,a.zzmmmc,a.kh,a.rxny,a.lxdh,a.rxqhk,a.jtzz,a.yzbm,a.jtlxdh,a.sfyycjcshzyhd,a.sfyysqgjzxdkhqgzx,a.sfjq,a.sfge,a.sfdq,a.sfcj,a.sfjls,a.sfly,a.sfzb,a.jtcy1_xm,a.jtcy1_nl,a.jtcy1_gx,a.jtcy1_gzdw,a.jtcy1_zy,a.jtcy1_nsr,a.jtcy1_jkzk,a.jtcy2_xm,a.jtcy2_nl,a.jtcy2_gx,a.jtcy2_gzdw,a.jtcy2_zy,a.jtcy2_nsr,a.jtcy2_jkzk,a.jtcy3_xm,a.jtcy3_nl,a.jtcy3_gx,a.jtcy3_gzdw,a.jtcy3_zy,a.jtcy3_nsr,a.jtcy3_jkzk,a.jtcy4_xm,a.jtcy4_nl,a.jtcy4_gx,a.jtcy4_gzdw,a.jtcy4_zy,a.jtcy4_nsr,a.jtcy4_jkzk,a.jtcy5_xm,a.jtcy5_nl,a.jtcy5_gx,a.jtcy5_gzdw,a.jtcy5_zy,a.jtcy5_nsr,a.jtcy5_jkzk,a.jtrjnsr,a.xszbdszqk,a.jtzszrzhqk,a.jtzstfywsj,a.qtqk,a.mzbm_txdz,a.mzbm_yzbm,a.mzbm_lxdh,a.sfkns,a.bjgmc,a.zycjpm,a.zyrs,a.xysh yesNo,a.xypzje zzje "
				+ "from view_xszz_common_new_zzbbxssqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,a.nd,a.xmdm,a.xh,a.sqly,a.sqsj,a.xysh,a.xyshyj,a.xyshsj,a.xypzje,a.xxsh,a.xxshyj,a.xxshsj,a.xxpzje,a.xmmc,a.xm,a.xb,a.sfzh,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.ssbh,a.qsdh,a.syd,a.csrq,a.mzmc,a.zzmmmc,a.kh,a.rxny,a.lxdh,a.rxqhk,a.jtzz,a.yzbm,a.jtlxdh,a.sfyycjcshzyhd,a.sfyysqgjzxdkhqgzx,a.sfjq,a.sfge,a.sfdq,a.sfcj,a.sfjls,a.sfly,a.sfzb,a.jtcy1_xm,a.jtcy1_nl,a.jtcy1_gx,a.jtcy1_gzdw,a.jtcy1_zy,a.jtcy1_nsr,a.jtcy1_jkzk,a.jtcy2_xm,a.jtcy2_nl,a.jtcy2_gx,a.jtcy2_gzdw,a.jtcy2_zy,a.jtcy2_nsr,a.jtcy2_jkzk,a.jtcy3_xm,a.jtcy3_nl,a.jtcy3_gx,a.jtcy3_gzdw,a.jtcy3_zy,a.jtcy3_nsr,a.jtcy3_jkzk,a.jtcy4_xm,a.jtcy4_nl,a.jtcy4_gx,a.jtcy4_gzdw,a.jtcy4_zy,a.jtcy4_nsr,a.jtcy4_jkzk,a.jtcy5_xm,a.jtcy5_nl,a.jtcy5_gx,a.jtcy5_gzdw,a.jtcy5_zy,a.jtcy5_nsr,a.jtcy5_jkzk,a.jtrjnsr,a.xszbdszqk,a.jtzszrzhqk,a.jtzstfywsj,a.qtqk,a.mzbm_txdz,a.mzbm_yzbm,a.mzbm_lxdh,a.sfkns,a.bjgmc,a.zycjpm,a.zyrs,a.xxsh yesNo,a.xxpzje zzje "
				+ "from view_xszz_common_new_zzbbxssqb a where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[(outString.length+3)];
		colList[0] = "pk";
		int i = 0;
		for (i = 0; i < outString.length; i++) {
			colList[i+1] = outString[i].toLowerCase();
		}
		colList[i+1] = "yesNo";
		colList[i+2] = "zzje";
		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		String xh = "";
		List<String[]> zdyzdList = new ArrayList<String[]>();
		for (i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			if (colList[i].equalsIgnoreCase("xh")) {
				xh = rs[i];
			}
			if (colList[i].equalsIgnoreCase("xmdm")) {
				
				zdyzdList = xszzDao.getXszzZdyzdList(rs[i]);
				if (zdyzdList.size() == 0) {
					request.setAttribute("isNULL", "is");
				} else {
					request.setAttribute("isNULL", "no");
				}
				request.setAttribute("zdyzdList", zdyzdList);
				List<HashMap<String, String>> zzjeList = xszzDao.getXszzZzjeList(rs[i]);
				request.setAttribute("zzjeList", zzjeList);
			}
			hs.put(colList[i], rs[i]);
		}

		if (zdyzdList.size() != 0) {
			String st = " xh";
			for(String[] str : zdyzdList){
				st += ",zdy" + str[0];
			}
			sql = "select "+st+" from view_xszz_common_new_zzbbxssqb where 1=2";
			outString = dao.getColumnName(sql);// �����������
			sql = "select "+st+" from view_xszz_common_new_zzbbxssqb where nd||xmdm||xh=? ";
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
		request.setAttribute("rs", hs);
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("xszzHdjeList", xszzDao.getXszzHdjeList(xh));
		request.setAttribute("zjeList", xszzDao.getXshdZjeList(xh));
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_xszz_common_new_zzbbxssqb");
		request.setAttribute("act", "zzsh");
		return mapping.findForward("xszzshXxxx");
	}
	
	/**
	 * @describe �����б���
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzshExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
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
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
		}
		if (!isNull(xmdm)) {
			querry.append(" and xmdm='");
			querry.append(xmdm);
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
		sql = "select * from view_xszz_common_new_zzbbxssqb " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_xszz_common_new_zzbbxssqb where 1=2");// �����������
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_xszz_common_new_zzbbxssqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("xszzshExp");
	}

	/**
	 * @describe �����������б�
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzJehz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
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
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);

		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		if (!isNull(xmdm)) {
			querry.append(" and xmdm='");
			querry.append(xmdm);
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
		tips = "��ǰ����λ�ã�ѧ������ - ��� - ��������";
		String sqlT = "";
		if (userType.equalsIgnoreCase("xx")) {
			colList = new String[] { "nd", "xmmc", "xh", "xm", "bjmc", "nj",
					"sqsj", "xysh", "xxsh", "xxpzje" };
		} else {
			colList = new String[] { "nd", "xmmc", "xh", "xm", "bjmc", "nj",
					"sqsj", "xysh", "xxsh", "xypzje" };
		}
		if (userType.equalsIgnoreCase("xx")) {
			sql = "select nvl(sum(ROUND(xxpzje)),0) num from view_xszz_common_new_zzbbxssqb "
					+ querry.toString();
			je = dao.getOneRs(sql, new String[] {}, "num");
			sqlT = "select ' ' nd,' ' xmmc,' ' xh,' ' xm,' ' bjmc, ' ' nj,' ' sqsj,'�ϼ�' xysh, ' ' xxsh, '"
					+ je + "' xxpzje from dual";
			sql = "select nd,xmmc,xh,xm,bjmc,nj,sqsj,xysh,xxsh,xxpzje from view_xszz_common_new_zzbbxssqb "
					+ querry.toString() + " order by nd desc";
		} else {
			sql = "select nvl(sum(ROUND(xypzje)),0) num from view_xszz_common_new_zzbbxssqb "
					+ querry.toString() + " and xydm='" + userDep;
			je = dao.getOneRs(sql, new String[] {}, "num");
			sqlT = "select ' ' nd,' ' xmmc,' ' xh,' ' xm,' ' bjmc, ' ' nj,' ' sqsj,'�ϼ�' xysh, ' ' xxsh, '"
					+ je + "' xypzje from dual";
			sql = "select nd,xmmc,xh,xm,bjmc,nj,sqsj,xysh,xxsh,xypzje from view_xszz_common_new_zzbbxssqb "
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by nd desc";
		}
		colListCN = dao.getColumnNameCN(colList,
				"view_xszz_common_new_zzbbxssqb");
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
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("xmdm", xmdm);
		map.put("xysh", xysh);
		map.put("xxsh", xxsh);
		xy = xy == null ? "" : xy;
		zy = zy == null ? "" : zy;
		nj = nj == null ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// ���Ͷ�дȨ��
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("zzxmList", xszzDao.getXszzZzxmList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
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
		return mapping.findForward("xszzJehz");
	}
	
	/**
	 * @describe ���������ܵ���
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzJehzExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
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
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String nd = "";
		nd = Base.chgNull(request.getParameter("nd"), "", 1);
		if (!isNull(xmdm)) {
			querry.append(" and xmdm='");
			querry.append(xmdm);
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
		
		sql = "select * from view_xszz_common_new_zzbbxssqb " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_xszz_common_new_zzbbxssqb where 1=2");// �����������
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		sql = "select nvl(sum(ROUND(xypzje)),0) xyzje,nvl(sum(ROUND(xxpzje)),0) xxzje from view_xszz_common_new_zzbbxssqb " + querry.toString();
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
		sqlBf.append(" from dual ");
		rs.addAll(dao.rsToVator(sqlBf.toString(), new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_xszz_common_new_zzbbxssqb");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("xszzJehzExp");
	}

	/**
	 * @describe ������ѧ��������ҳ��
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjzxdksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		String act = Base.chgNull(request.getParameter("act"), "", 1);
		String xh = "";
		if (!userType.equalsIgnoreCase("stu")) {
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
		}else{
			xh = sUName;
		}
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String nd = Base.currNd;

		String sfksq = "-1";
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
		} else {
			pkVal = nd + xh;
		}
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from COMMON_NEW_GJZXDK_SJB a,view_xsjbxx b where a.xmmc='������ѧ����' and b.xh=? and a.xydm=b.xydm";
			jxjksjssj = dao.getOneRs(sql1, new String[] { xh },
					new String[] { "kssj", "jssj" });
			if (jxjksjssj == null){
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
			} else if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /������ʱ�䷶Χ��
//			if (jxjksjssj != null
//					&& jxjksjssj[0].compareToIgnoreCase(rightNow) < 0
//					&& jxjksjssj[1].compareToIgnoreCase(rightNow) > 0) {// /������ʱ�䷶Χ��
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {// /ѧ����д����
					String xsxz = Base.chgNull(request.getParameter("xsxz"),
							"", 1);
					String yxjzdz = Base.chgNull(
							request.getParameter("yxjzdz"), "", 1);
					String lxdh = Base.chgNull(request.getParameter("lxdh"),
							"", 1);
					String yzbm = Base.chgNull(request.getParameter("yzbm"),
							"", 1);
					String jtjjqk = Base.chgNull(
							request.getParameter("jtjjqk"), "", 1);
					String xfdk = Base.chgNull(request.getParameter("xfdk"),
							"", 1);
					String yjxf = Base.chgNull(request.getParameter("yjxf"),
							"", 1);
					String shfdk = Base.chgNull(request.getParameter("shfdk"),
							"", 1);
					String hj = Base.chgNull(request.getParameter("hj"), "", 1);
					String sxncj1_mc = Base.chgNull(request
							.getParameter("sxncj1_mc"), "", 1);
					String sxncj1_cj = Base.chgNull(request
							.getParameter("sxncj1_cj"), "", 1);
					String sxncj2_mc = Base.chgNull(request
							.getParameter("sxncj2_mc"), "", 1);
					String sxncj2_cj = Base.chgNull(request
							.getParameter("sxncj2_cj"), "", 1);
					String sxncj3_mc = Base.chgNull(request
							.getParameter("sxncj3_mc"), "", 1);
					String sxncj3_cj = Base.chgNull(request
							.getParameter("sxncj3_cj"), "", 1);
					String sxncj4_mc = Base.chgNull(request
							.getParameter("sxncj4_mc"), "", 1);
					String sxncj4_cj = Base.chgNull(request
							.getParameter("sxncj4_cj"), "", 1);
					String sxncj5_mc = Base.chgNull(request
							.getParameter("sxncj5_mc"), "", 1);
					String sxncj5_cj = Base.chgNull(request
							.getParameter("sxncj5_cj"), "", 1);
					String sxncj6_mc = Base.chgNull(request
							.getParameter("sxncj6_mc"), "", 1);
					String sxncj6_cj = Base.chgNull(request
							.getParameter("sxncj6_cj"), "", 1);
					String sxncj7_mc = Base.chgNull(request
							.getParameter("sxncj7_mc"), "", 1);
					String sxncj7_cj = Base.chgNull(request
							.getParameter("sxncj7_cj"), "", 1);
					String sxncj8_mc = Base.chgNull(request
							.getParameter("sxncj8_mc"), "", 1);
					String sxncj8_cj = Base.chgNull(request
							.getParameter("sxncj8_cj"), "", 1);
					String sxncj9_mc = Base.chgNull(request
							.getParameter("sxncj9_mc"), "", 1);
					String sxncj9_cj = Base.chgNull(request
							.getParameter("sxncj9_cj"), "", 1);
					String sxncj10_mc = Base.chgNull(request
							.getParameter("sxncj10_mc"), "", 1);
					String sxncj10_cj = Base.chgNull(request
							.getParameter("sxncj10_cj"), "", 1);
					String rxlbjgkm = Base.chgNull(request
							.getParameter("rxlbjgkm"), "", 1);
					String rxlbktgkm = Base.chgNull(request
							.getParameter("rxlbktgkm"), "", 1);

					sql = "select count(*) num from xszz_common_gjzxdk where nd||xh=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert("xszz_common_gjzxdk",
								new String[] { "nd", "xh", "xsxz",
												"yxjzdz", "lxdh", "yzbm",
												"jtjjqk", "xfdk", "yjxf",
												"shfdk", "hj", "sxncj1_mc",
												"sxncj1_cj", "sxncj2_mc",
												"sxncj2_cj", "sxncj3_mc",
												"sxncj3_cj", "sxncj4_mc",
												"sxncj4_cj", "sxncj5_mc",
												"sxncj5_cj", "sxncj6_mc",
												"sxncj6_cj", "sxncj7_mc",
												"sxncj7_cj", "sxncj8_mc",
												"sxncj8_cj", "sxncj9_mc",
												"sxncj9_cj", "sxncj10_mc",
												"sxncj10_cj", "rxlbjgkm",
												"rxlbktgkm" },
										new String[] { nd, xh, xsxz, yxjzdz,
												lxdh, yzbm, jtjjqk, xfdk, yjxf,
												shfdk, hj, sxncj1_mc,
												sxncj1_cj, sxncj2_mc,
												sxncj2_cj, sxncj3_mc,
												sxncj3_cj, sxncj4_mc,
												sxncj4_cj, sxncj5_mc,
												sxncj5_cj, sxncj6_mc,
												sxncj6_cj, sxncj7_mc,
												sxncj7_cj, sxncj8_mc,
												sxncj8_cj, sxncj9_mc,
												sxncj9_cj, sxncj10_mc,
												sxncj10_cj, rxlbjgkm, rxlbktgkm },
								request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						sql = "select count(*) num from xszz_common_gjzxdk where nd||xh=? and xxsh='ͨ��'";
						num = dao.getOneRs(sql, new String[] { pkVal }, "num");
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update("xszz_common_gjzxdk",
									new String[] { "xsxz", "yxjzdz", "lxdh",
											"yzbm", "jtjjqk", "xfdk", "yjxf",
											"shfdk", "hj", "sxncj1_mc",
											"sxncj1_cj", "sxncj2_mc",
											"sxncj2_cj", "sxncj3_mc",
											"sxncj3_cj", "sxncj4_mc",
											"sxncj4_cj", "sxncj5_mc",
											"sxncj5_cj", "sxncj6_mc",
											"sxncj6_cj", "sxncj7_mc",
											"sxncj7_cj", "sxncj8_mc",
											"sxncj8_cj", "sxncj9_mc",
											"sxncj9_cj", "sxncj10_mc",
											"sxncj10_cj", "rxlbjgkm",
											"rxlbktgkm", "sqsj", "xysh",
											"xyshsj", "xyshyj", "xxsh",
											"xxshsj", "xxshyj" }, new String[] {
											xsxz, yxjzdz, lxdh, yzbm, jtjjqk,
											xfdk, yjxf, shfdk, hj, sxncj1_mc,
											sxncj1_cj, sxncj2_mc, sxncj2_cj,
											sxncj3_mc, sxncj3_cj, sxncj4_mc,
											sxncj4_cj, sxncj5_mc, sxncj5_cj,
											sxncj6_mc, sxncj6_cj, sxncj7_mc,
											sxncj7_cj, sxncj8_mc, sxncj8_cj,
											sxncj9_mc, sxncj9_cj, sxncj10_mc,
											sxncj10_cj, rxlbjgkm, rxlbktgkm,
											rightNow, "δ���", "", "", "δ���", "",
											"" },
									"nd||xh", pkVal, request);
							if (ok) {
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
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {
					String xsxz = Base.chgNull(request.getParameter("xsxz"),
							"", 1);
					String yxjzdz = Base.chgNull(
							request.getParameter("yxjzdz"), "", 1);
					String lxdh = Base.chgNull(request.getParameter("lxdh"),
							"", 1);
					String yzbm = Base.chgNull(request.getParameter("yzbm"),
							"", 1);
					String jtjjqk = Base.chgNull(
							request.getParameter("jtjjqk"), "", 1);
					String xfdk = Base.chgNull(request.getParameter("xfdk"),
							"", 1);
					String yjxf = Base.chgNull(request.getParameter("yjxf"),
							"", 1);
					String shfdk = Base.chgNull(request.getParameter("shfdk"),
							"", 1);
					String hj = Base.chgNull(request.getParameter("hj"), "", 1);
					String sxncj1_mc = Base.chgNull(request
							.getParameter("sxncj1_mc"), "", 1);
					String sxncj1_cj = Base.chgNull(request
							.getParameter("sxncj1_cj"), "", 1);
					String sxncj2_mc = Base.chgNull(request
							.getParameter("sxncj2_mc"), "", 1);
					String sxncj2_cj = Base.chgNull(request
							.getParameter("sxncj2_cj"), "", 1);
					String sxncj3_mc = Base.chgNull(request
							.getParameter("sxncj3_mc"), "", 1);
					String sxncj3_cj = Base.chgNull(request
							.getParameter("sxncj3_cj"), "", 1);
					String sxncj4_mc = Base.chgNull(request
							.getParameter("sxncj4_mc"), "", 1);
					String sxncj4_cj = Base.chgNull(request
							.getParameter("sxncj4_cj"), "", 1);
					String sxncj5_mc = Base.chgNull(request
							.getParameter("sxncj5_mc"), "", 1);
					String sxncj5_cj = Base.chgNull(request
							.getParameter("sxncj5_cj"), "", 1);
					String sxncj6_mc = Base.chgNull(request
							.getParameter("sxncj6_mc"), "", 1);
					String sxncj6_cj = Base.chgNull(request
							.getParameter("sxncj6_cj"), "", 1);
					String sxncj7_mc = Base.chgNull(request
							.getParameter("sxncj7_mc"), "", 1);
					String sxncj7_cj = Base.chgNull(request
							.getParameter("sxncj7_cj"), "", 1);
					String sxncj8_mc = Base.chgNull(request
							.getParameter("sxncj8_mc"), "", 1);
					String sxncj8_cj = Base.chgNull(request
							.getParameter("sxncj8_cj"), "", 1);
					String sxncj9_mc = Base.chgNull(request
							.getParameter("sxncj9_mc"), "", 1);
					String sxncj9_cj = Base.chgNull(request
							.getParameter("sxncj9_cj"), "", 1);
					String sxncj10_mc = Base.chgNull(request
							.getParameter("sxncj10_mc"), "", 1);
					String sxncj10_cj = Base.chgNull(request
							.getParameter("sxncj10_cj"), "", 1);
					String rxlbjgkm = Base.chgNull(request
							.getParameter("rxlbjgkm"), "", 1);
					String rxlbktgkm = Base.chgNull(request
							.getParameter("rxlbktgkm"), "", 1);

					sql = "select count(*) num from xszz_common_gjzxdk where nd||xh=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation.insert("xszz_common_gjzxdk",
								new String[] { "nd", "xh", "xsxz",
												"yxjzdz", "lxdh", "yzbm",
												"jtjjqk", "xfdk", "yjxf",
												"shfdk", "hj", "sxncj1_mc",
												"sxncj1_cj", "sxncj2_mc",
												"sxncj2_cj", "sxncj3_mc",
												"sxncj3_cj", "sxncj4_mc",
												"sxncj4_cj", "sxncj5_mc",
												"sxncj5_cj", "sxncj6_mc",
												"sxncj6_cj", "sxncj7_mc",
												"sxncj7_cj", "sxncj8_mc",
												"sxncj8_cj", "sxncj9_mc",
												"sxncj9_cj", "sxncj10_mc",
												"sxncj10_cj", "rxlbjgkm",
												"rxlbktgkm" },
										new String[] { nd, xh, xsxz, yxjzdz,
												lxdh, yzbm, jtjjqk, xfdk, yjxf,
												shfdk, hj, sxncj1_mc,
												sxncj1_cj, sxncj2_mc,
												sxncj2_cj, sxncj3_mc,
												sxncj3_cj, sxncj4_mc,
												sxncj4_cj, sxncj5_mc,
												sxncj5_cj, sxncj6_mc,
												sxncj6_cj, sxncj7_mc,
												sxncj7_cj, sxncj8_mc,
												sxncj8_cj, sxncj9_mc,
												sxncj9_cj, sxncj10_mc,
												sxncj10_cj, rxlbjgkm, rxlbktgkm },
								request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						sql = "select count(*) num from xszz_common_gjzxdk where nd||xh=? and xxsh='ͨ��'";
						num = dao.getOneRs(sql, new String[] { pkVal }, "num");
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update("xszz_common_gjzxdk",
									new String[] { "xsxz", "yxjzdz", "lxdh",
											"yzbm", "jtjjqk", "xfdk", "yjxf",
											"shfdk", "hj", "sxncj1_mc",
											"sxncj1_cj", "sxncj2_mc",
											"sxncj2_cj", "sxncj3_mc",
											"sxncj3_cj", "sxncj4_mc",
											"sxncj4_cj", "sxncj5_mc",
											"sxncj5_cj", "sxncj6_mc",
											"sxncj6_cj", "sxncj7_mc",
											"sxncj7_cj", "sxncj8_mc",
											"sxncj8_cj", "sxncj9_mc",
											"sxncj9_cj", "sxncj10_mc",
											"sxncj10_cj", "rxlbjgkm",
											"rxlbktgkm", "sqsj", "xysh",
											"xyshsj", "xyshyj", "xxsh",
											"xxshsj", "xxshyj" }, new String[] {
											xsxz, yxjzdz, lxdh, yzbm, jtjjqk,
											xfdk, yjxf, shfdk, hj, sxncj1_mc,
											sxncj1_cj, sxncj2_mc, sxncj2_cj,
											sxncj3_mc, sxncj3_cj, sxncj4_mc,
											sxncj4_cj, sxncj5_mc, sxncj5_cj,
											sxncj6_mc, sxncj6_cj, sxncj7_mc,
											sxncj7_cj, sxncj8_mc, sxncj8_cj,
											sxncj9_mc, sxncj9_cj, sxncj10_mc,
											sxncj10_cj, rxlbjgkm, rxlbktgkm,
											rightNow, "δ���", "", "", "δ���", "",
											"" },
									"nd||xh", pkVal, request);
							if (ok) {
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

		sql = "select nd,xh,xm,xb,nj,xz,sfzh,rxny,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,xsxz,yxjzdz,lxdh,yzbm,jtjjqk,xfdk,yjxf,shfdk,hj,sxncj1_mc,sxncj1_cj,sxncj2_mc,sxncj2_cj,sxncj3_mc,sxncj3_cj,sxncj4_mc,sxncj4_cj,sxncj5_mc,sxncj5_cj,sxncj6_mc,sxncj6_cj,sxncj7_mc,sxncj7_cj,sxncj8_mc,sxncj8_cj,sxncj9_mc,sxncj9_cj,sxncj10_mc,sxncj10_cj,rxlbjgkm,rxlbktgkm,sqsj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_common_gjzxdk where 1=2";
		outString = dao.getColumnName(sql);// �����������
		sql = "select nd,xh,xm,xb,nj,xz,sfzh,rxny,csrq,xydm,xymc,zydm,zymc,bjdm,bjmc,xsxz,yxjzdz,lxdh,yzbm,jtjjqk,xfdk,yjxf,shfdk,hj,sxncj1_mc,sxncj1_cj,sxncj2_mc,sxncj2_cj,sxncj3_mc,sxncj3_cj,sxncj4_mc,sxncj4_cj,sxncj5_mc,sxncj5_cj,sxncj6_mc,sxncj6_cj,sxncj7_mc,sxncj7_cj,sxncj8_mc,sxncj8_cj,sxncj9_mc,sxncj9_cj,sxncj10_mc,sxncj10_cj,rxlbjgkm,rxlbktgkm,sqsj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_common_gjzxdk where nd||xh=? ";
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if(null != xh) {
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
		
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdksq");
	}
	
	/**
	 * @describe ������ѧ�������뱨���ӡ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjzxdksqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String xb = Base.chgNull(request.getParameter("xb"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xz = Base.chgNull(request.getParameter("xz"), "", 1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
		String rxny = Base.chgNull(request.getParameter("rxny"), "", 1);
		String csrq = Base.chgNull(request.getParameter("csrq"), "", 1);
		String xymc = Base.chgNull(request.getParameter("xymc"), "", 1);
		String zymc = Base.chgNull(request.getParameter("zymc"), "", 1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"), "", 1);
		String xsxz = Base.chgNull(request.getParameter("xsxz"), "", 1);
		String yxjzdz = Base.chgNull(request.getParameter("yxjzdz"), "", 1);
		String lxdh = Base.chgNull(request.getParameter("lxdh"), "", 1);
		String yzbm = Base.chgNull(request.getParameter("yzbm"), "", 1);
		String jtjjqk = Base.chgNull(request.getParameter("jtjjqk"), "", 1);
		String xfdk = Base.chgNull(request.getParameter("xfdk"), "", 1);
		String yjxf = Base.chgNull(request.getParameter("yjxf"), "", 1);
		String shfdk = Base.chgNull(request.getParameter("shfdk"), "", 1);
		String hj = Base.chgNull(request.getParameter("hj"), "", 1);
		String sxncj1_mc = Base.chgNull(request.getParameter("sxncj1_mc"), "",
				1);
		String sxncj1_cj = Base.chgNull(request.getParameter("sxncj1_cj"), "",
				1);
		String sxncj2_mc = Base.chgNull(request.getParameter("sxncj2_mc"), "",
				1);
		String sxncj2_cj = Base.chgNull(request.getParameter("sxncj2_cj"), "",
				1);
		String sxncj3_mc = Base.chgNull(request.getParameter("sxncj3_mc"), "",
				1);
		String sxncj3_cj = Base.chgNull(request.getParameter("sxncj3_cj"), "",
				1);
		String sxncj4_mc = Base.chgNull(request.getParameter("sxncj4_mc"), "",
				1);
		String sxncj4_cj = Base.chgNull(request.getParameter("sxncj4_cj"), "",
				1);
		String sxncj5_mc = Base.chgNull(request.getParameter("sxncj5_mc"), "",
				1);
		String sxncj5_cj = Base.chgNull(request.getParameter("sxncj5_cj"), "",
				1);
		String sxncj6_mc = Base.chgNull(request.getParameter("sxncj6_mc"), "",
				1);
		String sxncj6_cj = Base.chgNull(request.getParameter("sxncj6_cj"), "",
				1);
		String sxncj7_mc = Base.chgNull(request.getParameter("sxncj7_mc"), "",
				1);
		String sxncj7_cj = Base.chgNull(request.getParameter("sxncj7_cj"), "",
				1);
		String sxncj8_mc = Base.chgNull(request.getParameter("sxncj8_mc"), "",
				1);
		String sxncj8_cj = Base.chgNull(request.getParameter("sxncj8_cj"), "",
				1);
		String sxncj9_mc = Base.chgNull(request.getParameter("sxncj9_mc"), "",
				1);
		String sxncj9_cj = Base.chgNull(request.getParameter("sxncj9_cj"), "",
				1);
		String sxncj10_mc = Base.chgNull(request.getParameter("sxncj10_mc"),
				"", 1);
		String sxncj10_cj = Base.chgNull(request.getParameter("sxncj10_cj"),
				"", 1);
		String rxlbjgkm = Base.chgNull(request.getParameter("rxlbjgkm"), "", 1);
		String rxlbktgkm = Base.chgNull(request.getParameter("rxlbktgkm"), "",
				1);
		String sqsj = Base.chgNull(request.getParameter("sqsj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xyshsj = Base.chgNull(request.getParameter("xyshsj"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String xxshsj = Base.chgNull(request.getParameter("xxshsj"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		if("δ���".equalsIgnoreCase(xysh)){
			xysh=" ";
			xyshyj=" ";
		}
		if("δ���".equalsIgnoreCase(xxsh)){
			xxsh=" ";
			xxshyj=" ";
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
			sqsj = sqsj_year + "��" + sqsj_mon + "��" + sqsj_day + "��";
		}
		if(xyshsj != null && !"".equalsIgnoreCase(xyshsj)){
			xyshsj_year = xyshsj.substring(0,4);
			xyshsj_mon = xyshsj.substring(5, 7);
			xyshsj_day = xyshsj.substring(8);
			xyshsj = xyshsj_year + "��" + xyshsj_mon + "��" + xyshsj_day + "��";
		}
		if(xxshsj != null && !"".equalsIgnoreCase(xxshsj)){
			xxshsj_year = xxshsj.substring(0,4);
			xxshsj_mon = xxshsj.substring(5, 7);
			xxshsj_day = xxshsj.substring(8);
			xxshsj = xxshsj_year + "��" + xxshsj_mon + "��" + xxshsj_day + "��";
		}

		String[] outValue = new String[] { nd, xh, xm, xb, nj, xz, sfzh, rxny,
				csrq, xymc, zymc, bjmc, xsxz, yxjzdz, lxdh, yzbm, jtjjqk, xfdk,
				yjxf, shfdk, hj, sxncj1_mc, sxncj1_cj, sxncj2_mc, sxncj2_cj,
				sxncj3_mc, sxncj3_cj, sxncj4_mc, sxncj4_cj, sxncj5_mc,
				sxncj5_cj, sxncj6_mc, sxncj6_cj, sxncj7_mc, sxncj7_cj,
				sxncj8_mc, sxncj8_cj, sxncj9_mc, sxncj9_cj, sxncj10_mc,
				sxncj10_cj, rxlbjgkm, rxlbktgkm, sqsj, xysh, xyshsj, xyshyj,
				xxsh, xxshsj, xxshyj };
		String[] outString = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"sfzh", "rxny", "csrq", "xymc", "zymc", "bjmc", "xsxz",
				"yxjzdz", "lxdh", "yzbm", "jtjjqk", "xfdk", "yjxf", "shfdk",
				"hj", "sxncj1_mc", "sxncj1_cj", "sxncj2_mc", "sxncj2_cj",
				"sxncj3_mc", "sxncj3_cj", "sxncj4_mc", "sxncj4_cj",
				"sxncj5_mc", "sxncj5_cj", "sxncj6_mc", "sxncj6_cj",
				"sxncj7_mc", "sxncj7_cj", "sxncj8_mc", "sxncj8_cj",
				"sxncj9_mc", "sxncj9_cj", "sxncj10_mc", "sxncj10_cj",
				"rxlbjgkm", "rxlbktgkm", "sqsj", "xysh", "xyshsj", "xyshyj",
				"xxsh", "xxshsj", "xxshyj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("gjzxdksqb");
	}

	/**
	 * @describe ������ѧ��������б�
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjzxdksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		CommanForm actionForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
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
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		realTable = "xszz_common_gjzxdk";
		pk = "nd||xh";
		tableName = "view_xszz_common_gjzxdk";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
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
		String sqlt = "";
		tips = "��ǰ����λ�ã�ѧ������ - ��� - ������ѧ������Ŀ���";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "��ǰ����λ�ã�ѧ������ - ���� - ��������ѯ - ������ѧ����";
			colList = new String[] { "bgcolor", "����", "r", "nd", "xh", "xm",
					"sfzh", "xymc", "bjmc", "hj", "xysh", "xxsh", "sqsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select * from (select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,rownum r,a.nd,a.xh,a.xm,a.sfzh,a.xymc,a.bjmc,a.hj,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from "
					+ tableName
					+ " a"
					+ querry.toString();
				request.setAttribute("isXX", "is");
			} else {
				sql = "select * from (select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,rownum r,a.nd,a.xh,a.xm,a.sfzh,a.xymc,a.bjmc,a.hj,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "'";
				request.setAttribute("isXX", "no");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "����", "r", "nd", "xh", "xm",
						"sfzh", "xymc", "bjmc", "hj", "sqsj", "xysh", "" };
			}else{
				colList = new String[] { "bgcolor", "����", "r", "nd", "xh", "xm",
						"sfzh", "xymc", "bjmc", "hj", "sqsj", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select * from (select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,rownum r,a.nd,a.xh,a.xm,a.sfzh,a.xymc,a.bjmc,a.hj,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='ͨ��' order by xxsh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='ͨ��'";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				sql = "select * from (select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,rownum r,a.nd,a.xh,a.xm,a.sfzh,a.xymc,a.bjmc,a.hj,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "'";
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
		map.put("nj", nj);
		map.put("xh", xh);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		String xxdm = StandardOperation.getXxdm();
		
		if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm)){
			request.setAttribute("xxmc", "zgkydx");
		}
		actionForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(sqlt, new String[]{}, "num")));
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
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("gjzxdksh");
	}
	
	/**
	 * @describe ������ѧ������˵�����Ϣ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjzxdkshXxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
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
					sqlT[i] = "delete xszz_common_gjzxdk where nd||xh='"+pkT+"' and xxsh<>'ͨ��'";
				} else {
					sqlT[i] = "delete xszz_common_gjzxdk where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=gjzxdksh&go=go", false);
			return newFwd;
		}
		
		if ("pass".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update xszz_common_gjzxdk set xysh='ͨ��',xyshsj='"
							+ now + "' where nd||xh='" + pkT + "'";
				} else {
					sqlT[i] = "update xszz_common_gjzxdk set xxsh='ͨ��',xxshsj='"
							+ now + "' where nd||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=gjzxdksh&go=go", false);
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
									"select xxsh from xszz_common_gjzxdk where nd||xh=?",
									new String[] { pkT }, "xxsh");
					if (!"ͨ��".equalsIgnoreCase(xxshT)) {
						sqlT[i] = "update xszz_common_gjzxdk set xysh='��ͨ��',xyshsj='"
								+ now + "' where nd||xh='" + pkT + "'";
					}
				} else {
					sqlT[i] = "update xszz_common_gjzxdk set xxsh='��ͨ��',xxshsj='"
							+ now + "' where nd||xh='" + pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=gjzxdksh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			boolean b = false;
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				String xxshT = dao.getOneRs("select xxsh from xszz_common_gjzxdk where nd||xh=?", new String[]{pkVal}, "xxsh");
				if("ͨ��".equalsIgnoreCase(xxshT)){
					request.setAttribute("xxshjg", "pass");
				} else {
					b = StandardOperation
							.update(
									"xszz_common_gjzxdk",
									new String[] { "xysh", "xyshyj", "xyshsj" },
									new String[] { yesNo, xyshyj, now },
									"nd||xh", pkVal, request);
				}
			} else {
				b = StandardOperation.update("xszz_common_gjzxdk", new String[] {
						"xxsh", "xxshyj", "xxshsj" }, new String[] {
						yesNo, xxshyj, now }, "nd||xh", pkVal,
						request);
			}
			if(b){
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		realTable = "xszz_common_gjzxdk";
		pk = "nd||xh";
		sql = "select nd,xh,xm,xb,nj,xz,sfzh,rxny,csrq,xymc,zymc,bjmc,xsxz,yxjzdz,lxdh,yzbm,jtjjqk,xfdk,yjxf,shfdk,hj,sxncj1_mc,sxncj1_cj,sxncj2_mc,sxncj2_cj,sxncj3_mc,sxncj3_cj,sxncj4_mc,sxncj4_cj,sxncj5_mc,sxncj5_cj,sxncj6_mc,sxncj6_cj,sxncj7_mc,sxncj7_cj,sxncj8_mc,sxncj8_cj,sxncj9_mc,sxncj9_cj,sxncj10_mc,sxncj10_cj,rxlbjgkm,rxlbktgkm,sqsj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj from view_xszz_common_gjzxdk where 1=2";
		String[] outString = dao.getColumnName(sql);// �����������
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,nd,xh,xm,xb,nj,xz,sfzh,rxny,csrq,xymc,zymc,bjmc,xsxz,yxjzdz,lxdh,yzbm,jtjjqk,xfdk,yjxf,shfdk,hj,sxncj1_mc,sxncj1_cj,sxncj2_mc,sxncj2_cj,sxncj3_mc,sxncj3_cj,sxncj4_mc,sxncj4_cj,sxncj5_mc,sxncj5_cj,sxncj6_mc,sxncj6_cj,sxncj7_mc,sxncj7_cj,sxncj8_mc,sxncj8_cj,sxncj9_mc,sxncj9_cj,sxncj10_mc,sxncj10_cj,rxlbjgkm,rxlbktgkm,sqsj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj,xysh yesNo "
				+ "from view_xszz_common_gjzxdk where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,nd,xh,xm,xb,nj,xz,sfzh,rxny,csrq,xymc,zymc,bjmc,xsxz,yxjzdz,lxdh,yzbm,jtjjqk,xfdk,yjxf,shfdk,hj,sxncj1_mc,sxncj1_cj,sxncj2_mc,sxncj2_cj,sxncj3_mc,sxncj3_cj,sxncj4_mc,sxncj4_cj,sxncj5_mc,sxncj5_cj,sxncj6_mc,sxncj6_cj,sxncj7_mc,sxncj7_cj,sxncj8_mc,sxncj8_cj,sxncj9_mc,sxncj9_cj,sxncj10_mc,sxncj10_cj,rxlbjgkm,rxlbktgkm,sqsj,xysh,xyshsj,xyshyj,xxsh,xxshsj,xxshyj,xxsh yesNo "
				+ "from view_xszz_common_gjzxdk where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
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

		hs.put("shsj", now);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_xszz_common_gjzxdk");
		request.setAttribute("act", "gjzxdksh");
		return mapping.findForward("gjzxdkshXxxx");
	}
	
	/**
	 * @describe ������ѧ�����б���
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward gjzxdkshExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
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
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
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
		sql = "select * from view_xszz_common_gjzxdk " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_xszz_common_gjzxdk where 1=2");// �����������
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_xszz_common_gjzxdk");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("gjzxdkshExp");
	}
	
	/**
	 * @describe ����������ҳ��
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward knssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		// String userNameReal;

		String sUName;

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		// userNameReal = session.getAttribute("userNameReal").toString();
		// userDep = session.getAttribute("userDep").toString();

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, "sdate"));

		String act = Base.chgNull(request.getParameter("act"), "", 1);
		String xh = "";
		if (!userType.equalsIgnoreCase("stu")) {
			xh = Base.chgNull(request.getParameter("xh"), "", 1);
		}else{
			xh = sUName;
		}
		String sql = "";
		String sql1 = "";
		String[] outString = new String[] {};
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String nd = Base.currNd;

		String sfksq = "-1";
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
		} else {
			pkVal = nd + xh;
		}
		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;

			sql1 = "select a.kssj,a.jssj from COMMON_NEW_GJZXDK_SJB a,view_xsjbxx b where a.xmmc='������' and b.xh=? and a.xydm=b.xydm";
			jxjksjssj = dao.getOneRs(sql1, new String[] { xh },
					new String[] { "kssj", "jssj" });
			if (jxjksjssj == null){
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
			} else if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /������ʱ�䷶Χ��
//			if (jxjksjssj != null
//					&& jxjksjssj[0].compareToIgnoreCase(rightNow) < 0
//					&& jxjksjssj[1].compareToIgnoreCase(rightNow) > 0) {// /������ʱ�䷶Χ��
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {// /ѧ����д����
					String lxdh = Base.chgNull(request.getParameter("lxdh"),
							"", 1);
					String sqly = Base.chgNull(request.getParameter("sqly"),
							"", 1);
					String bz = Base.chgNull(request.getParameter("bz"), "", 1);

					sql = "select count(*) num from xszz_new_knsxx where nd||xh=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation
								.insert(
										"xszz_new_knsxx",
										new String[] { "nd", "xh", "lxdh",
												"sqly", "bz" },
										new String[] { nd, xh, lxdh, sqly, bz },
										request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						sql = "select count(*) num from xszz_new_knsxx where nd||xh=? and xxsh='ͨ��'";
						num = dao.getOneRs(sql, new String[] { pkVal }, "num");
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update("xszz_new_knsxx",
									new String[] { "lxdh", "sqly", "bz",
											"sqsj", "xysh", "xyshyj", "xxsh",
											"xxshyj" }, new String[] { lxdh,
											sqly, bz, rightNow, "δ���", "",
											"δ���", "" },
									"nd||xh", pkVal, request);
							if (ok) {
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
				sfksq = "1";// /���Խ�������
				request.setAttribute("sfksq", sfksq);
				if (act != null && act.equalsIgnoreCase("save")) {
					String lxdh = Base.chgNull(request.getParameter("lxdh"),
							"", 1);
					String sqly = Base.chgNull(request.getParameter("sqly"),
							"", 1);
					String bz = Base.chgNull(request.getParameter("bz"), "", 1);

					sql = "select count(*) num from xszz_new_knsxx where nd||xh=?";
					String num = dao.getOneRs(sql, new String[] { pkVal },
							"num");
					if ("0".equalsIgnoreCase(num)) {
						boolean ok = false;
						ok = StandardOperation
								.insert(
										"xszz_new_knsxx",
										new String[] { "nd", "xh", "lxdh",
												"sqly", "bz" },
										new String[] { nd, xh, lxdh, sqly, bz },
										request);
						if (ok) {
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					} else {
						sql = "select count(*) num from xszz_new_knsxx where nd||xh=? and xxsh='ͨ��'";
						num = dao.getOneRs(sql, new String[] { pkVal }, "num");
						if ("0".equalsIgnoreCase(num)) {
							boolean ok = false;
							ok = StandardOperation.update("xszz_new_knsxx",
									new String[] { "lxdh", "sqly", "bz",
											"sqsj", "xysh", "xyshyj", "xxsh",
											"xxshyj" }, new String[] { lxdh,
											sqly, bz, rightNow, "δ���", "",
											"δ���", "" },
									"nd||xh", pkVal, request);
							if (ok) {
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

		sql = "select nd,xh,xm,xb,nj,xz,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,sqly,bz,sqsj,xysh,xyshyj,xxsh,xxshyj from view_xszz_new_knsxx where 1=2";
		outString = dao.getColumnName(sql);// �����������
		sql = "select nd,xh,xm,xb,nj,xz,sfzh,xydm,xymc,zydm,zymc,bjdm,bjmc,lxdh,sqly,bz,sqsj,xysh,xyshyj,xxsh,xxshyj from view_xszz_new_knsxx where nd||xh=? ";
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		if (outValue == null) {
			if(null != xh) {
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.sfzh,a.xz,'"+nd+"' nd from view_stu_details a where a.xh=?";
				String[] colN = new String[] { "xh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc", "sfzh", "xz", "nd" };
				String[] outV = dao.getOneRs(sql,
						new String[] { xh }, colN);
				if (outV != null) {
					colN = new String[] { "xh", "xm", "xb", "nj", "xymc",
							"zymc", "bjmc", "sfzh", "xz", "nd" };
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
		
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssq");
	}
	
	/**
	 * @describe ���������뱨���ӡ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward knssqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String xb = Base.chgNull(request.getParameter("xb"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xz = Base.chgNull(request.getParameter("xz"), "", 1);
		String sfzh = Base.chgNull(request.getParameter("sfzh"), "", 1);
		String xymc = Base.chgNull(request.getParameter("xymc"), "", 1);
		String zymc = Base.chgNull(request.getParameter("zymc"), "", 1);
		String bjmc = Base.chgNull(request.getParameter("bjmc"), "", 1);
		String lxdh = Base.chgNull(request.getParameter("lxdh"), "", 1);
		String sqly = Base.chgNull(request.getParameter("sqly"), "", 1);
		String bz = Base.chgNull(request.getParameter("bz"), "", 1);
		String sqsj = Base.chgNull(request.getParameter("sqsj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xyshyj = Base.chgNull(request.getParameter("xyshyj"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String xxshyj = Base.chgNull(request.getParameter("xxshyj"), "", 1);
		if("δ���".equalsIgnoreCase(xysh)){
			xysh=" ";
			xyshyj=" ";
		}
		if("δ���".equalsIgnoreCase(xxsh)){
			xxsh=" ";
			xxshyj=" ";
		}

		String[] outValue = new String[] { nd, xh, xm, xb, nj, xz, sfzh, xymc,
				zymc, bjmc, lxdh, sqly, bz, sqsj, xysh, xyshyj, xxsh, xxshyj };
		String[] outString = new String[] { "nd", "xh", "xm", "xb", "nj", "xz",
				"sfzh", "xymc", "zymc", "bjmc", "lxdh", "sqly", "bz", "sqsj",
				"xysh", "xyshyj", "xxsh", "xxshyj" };
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null) {
				map.put(outString[i], outValue[i]);
			} else
				map.put(outString[i], "");
		}
		request.setAttribute("rs", map);
		return mapping.findForward("knssqb");
	}

	/**
	 * @describe ����������б�
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward knssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		CommanForm actionForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
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
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		realTable = "xszz_new_knsxx";
		pk = "nd||xh";
		tableName = "view_xszz_new_knsxx";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
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
		String sqlt = "";
		tips = "��ǰ����λ�ã�ѧ������ - ��� - ��������Ŀ���";
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "��ǰ����λ�ã�ѧ������ - ���� - ��������ѯ - ������";
			colList = new String[] { "bgcolor", "����", "r", "nd", "xh", "xm",
					"sfzh", "xymc", "bjmc", "xysh", "xxsh", "sqsj" };
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select * from (select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,rownum r,a.nd,a.xh,a.xm,a.sfzh,a.xymc,a.bjmc,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " order by xxsh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from "
					+ tableName
					+ " a"
					+ querry.toString();
				request.setAttribute("isXX", "is");
			} else {
				sql = "select * from (select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,rownum r,a.nd,a.xh,a.xm,a.sfzh,a.xymc,a.bjmc,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "'";
				request.setAttribute("isXX", "no");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "����", "r", "nd", "xh", "xm",
						"sfzh", "xymc", "bjmc", "sqsj", "xysh", "" };
			}else{
				colList = new String[] { "bgcolor", "����", "r", "nd", "xh", "xm",
						"sfzh", "xymc", "bjmc", "sqsj", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select * from (select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,rownum r,a.nd,a.xh,a.xm,a.sfzh,a.xymc,a.bjmc,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh in ('����','һ������','��������') order by xxsh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from  "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh in ('����','һ������','��������')";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				sql = "select * from (select (case when nvl(a.xysh,'δ���') in ('����','һ������','��������') then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,rownum r,a.nd,a.xh,a.xm,a.sfzh,a.xymc,a.bjmc,a.xysh,a.xxsh,a.sqsj from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a) where r<="
					+ actionForm.getPages().getStart() + actionForm.getPages().getPageSize()
					+ " and r>"
					+ actionForm.getPages().getStart();
				sqlt = "select count(*) num from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xydm='"
					+ userDep
					+ "'";
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
		map.put("nj", nj);
		map.put("xh", xh);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		actionForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(sqlt, new String[]{}, "num")));
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			userType = "xx";
		}
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("knssh");
	}
	
	/**
	 * @describe ��������˵�����Ϣ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward knsshXxxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
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
					sqlT[i] = "delete xszz_new_knsxx where nd||xh='"+pkT+"' and xxsh<>'ͨ��'";
				} else {
					sqlT[i] = "delete xszz_new_knsxx where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}
		
		if ("pass".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "update xszz_new_knsxx set xysh='ͨ��' where nd||xh='"+pkT+"'";
				} else {
					sqlT[i] = "update xszz_new_knsxx set xxsh='ͨ��' where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=knssh&go=go", false);
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
									"select xxsh from xszz_new_knsxx where nd||xh=?",
									new String[] { pkT }, "xxsh");
					if (!"ͨ��".equalsIgnoreCase(xxshT)) {
						sqlT[i] = "update xszz_new_knsxx set xysh='������' where nd||xh='"+pkT+"'";
					}
				} else {
					sqlT[i] = "update xszz_new_knsxx set xxsh='������' where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}
		
		if ("kn".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				String xxshT = dao.getOneRs(
						"select xxsh from xszz_new_knsxx where nd||xh=?",
						new String[] { pkT }, "xxsh");
				if (!"ͨ��".equalsIgnoreCase(xxshT)) {
					sqlT[i] = "update xszz_new_knsxx set xysh='����' where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}
		
		if ("ybkn".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				String xxshT = dao.getOneRs(
						"select xxsh from xszz_new_knsxx where nd||xh=?",
						new String[] { pkT }, "xxsh");
				if (!"ͨ��".equalsIgnoreCase(xxshT)) {
					sqlT[i] = "update xszz_new_knsxx set xysh='һ������' where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}
		
		if ("tskn".equalsIgnoreCase(actDo)) {
			String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				String xxshT = dao.getOneRs(
						"select xxsh from xszz_new_knsxx where nd||xh=?",
						new String[] { pkT }, "xxsh");
				if (!"ͨ��".equalsIgnoreCase(xxshT)) {
					sqlT[i] = "update xszz_new_knsxx set xysh='��������' where nd||xh='"+pkT+"'";
				}
			}
			dao.runBatch(sqlT);
			ActionForward newFwd = new ActionForward(
					"/new_common_xszz.do?method=knssh&go=go", false);
			return newFwd;
		}

		if (actDo.equalsIgnoreCase("save")) {
			boolean b = false;
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				String xxshT = dao.getOneRs(
						"select xxsh from xszz_new_knsxx where nd||xh=?",
						new String[] { pkVal }, "xxsh");
				if("ͨ��".equalsIgnoreCase(xxshT)){
					request.setAttribute("xxshjg", "pass");
				} else {
					b = StandardOperation
							.update(
									"xszz_new_knsxx",
									new String[] { "xysh", "xyshyj" },
									new String[] { yesNo, xyshyj },
									"nd||xh", pkVal, request);
				}
			} else {
				b = StandardOperation.update("xszz_new_knsxx", new String[] {
						"xxsh", "xxshyj" }, new String[] {
						yesNo, xxshyj }, "nd||xh", pkVal,
						request);
			}
			if(b){
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		realTable = "xszz_new_knsxx";
		pk = "nd||xh";
		sql = "select nd,xh,xm,xb,nj,xz,sfzh,xymc,zymc,bjmc,lxdh,sqly,bz,sqsj,xysh,xyshyj,xxsh,xxshyj from view_xszz_new_knsxx where 1=2";
		String[] outString = dao.getColumnName(sql);// �����������
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
				+ pk
				+ " pk,nd,xh,xm,xb,nj,xz,sfzh,xymc,zymc,bjmc,lxdh,sqly,bz,sqsj,xysh,xyshyj,xxsh,xxshyj,xysh yesNo "
				+ "from view_xszz_new_knsxx where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
				+ pk
				+ " pk,nd,xh,xm,xb,nj,xz,sfzh,xymc,zymc,bjmc,lxdh,sqly,bz,sqsj,xysh,xyshyj,xxsh,xxshyj,xxsh yesNo "
				+ "from view_xszz_new_knsxx where " + pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
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

		hs.put("shsj", now);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			request.setAttribute("chkList", dao.getChkList(3));
		} else {
			request.setAttribute("chkList", dao.getChkList(12));
		}
		request.setAttribute("pk", pkVal);
		request.setAttribute("tName", realTable);
		request.setAttribute("titName", "view_xszz_new_knsxx");
		request.setAttribute("act", "knssh");
		return mapping.findForward("knsshXxxx");
	}
	
	/**
	 * @describe �������б���
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward knsshExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
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
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
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
		sql = "select * from view_xszz_new_knsxx " + querry.toString();
		
		String[] colList = dao.getColumnName("select * from view_xszz_new_knsxx where 1=2");// �����������
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, "view_xszz_new_knsxx");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("knsshExp");
	}
}
