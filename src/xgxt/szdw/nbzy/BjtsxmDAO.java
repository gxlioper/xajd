package xgxt.szdw.nbzy;

//
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;

//
public class BjtsxmDAO {

	DAO dao = DAO.getInstance();

	// ��ȡ�೤���ڰ༶��ϸ��Ϣ
	public HashMap<String, String> getSzbjDetail(String xh) {
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select c.*, d.xh tzsxh, d.xm tzsxm from (select a.bzxh, a.bzxm, a.bzrzgh, a.bzrxm,"
				+ " a.xydm, a.xymc, a.zydm, a.zymc, a.bjdm, a.bjmc, b.bjrs from (select t.xh bzxh, t.xm bzxm,"
				+ " c.zgh bzrzgh, c.xm bzrxm, t.xydm, t.xymc, t.zydm, t.zymc, t.bjdm, t.bjmc from view_xsjbxx t,"
				+ " fdybjb a, fdyxxb c where t.xh = ? and t.bjdm = a.bjdm and a.zgh = c.zgh) a,"
				+ " (select count(t.xh) bjrs, t.bjdm from view_xsjbxx t where t.bjdm = (select t.bjdm from"
				+ " view_xsjbxx t where t.xh = ? ) group by t.bjdm) b where a.bjdm = b.bjdm) c left join"
				+ " view_bjgbxx d on c.bjdm = d.bjdm and d.bjgbmc = '��֧�����'";
		map = dao.getMap(sql, new String[] { xh, xh }, new String[] { "bzxh",
				"bzxm", "bzrzgh", "bzrxm", "tzsxh", "tzsxm", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc", "bjrs" });
		//System.out.println(sql);
		return map;
	}

	// ��ȡ¥���б�
	public List<HashMap<String, String>> ldList() {
		String sql = "select lddm,ldmc from sslddmb";
		return dao.getList(sql, new String[] {},
				new String[] { "lddm", "ldmc" });
	}

	// ��ȡ�����б�
	public List<HashMap<String, String>> csList() {
		String sql = "select cs,cs from ssxxb";
		return dao.getList(sql, new String[] {}, new String[] { "cs", "cs" });
	}

	// ��ȡ���Ҳ���
	public String getCs(String ssbh) {
		String sql = "select cs from ssxxb where ssbh = ?";
		return dao.getOneRs(sql, new String[] { ssbh }, "cs");
	}

	// ��ȡ�����б�
	public List<HashMap<String, String>> qsList() {
		String sql = "select distinct qsh,qsh from ssxxb";
		return dao.getList(sql, new String[] {}, new String[] { "qsh", "qsh" });
	}

	// ��ȡ���������¥���б�
	public List<HashMap<String, String>> getSsFpQsList(String cs, String lddm) {
		String sql = "";
		sql = "select 'qsh'dm , '--��ѡ��--'mc from dual union all select * from (select distinct qsh dm,qsh mc from ssxxb where cs=? and lddm = ? order by cs)";
		List<HashMap<String, String>> qsList = dao.getList(sql, new String[] {
				cs, lddm }, new String[] { "dm", "mc" });
		return qsList;
	}

	// �ж��Ƿ�೤
	public boolean isBz(String xh) {
		boolean flg = false;
		String sql = "select count(xh) count from view_bjgbxx where xh=? and bjgbmc = '�೤'";
		String count = dao.getOneRs(sql, new String[] { xh }, "count");
		if (!"0".equalsIgnoreCase(count)) {
			flg = true;
		}
		return flg;
	}

	// ��ð����������༶
	public List getBzrBj(String zgh) throws SQLException {

		String sql = "select bjdm from fdybjb where zgh = ?";
		List bjList = dao.getList(sql, new String[] { zgh }, "bjdm");
		return bjList;
	}

	// ��ð����������༶
	public String getXxdm(String yhm) throws SQLException {

		String sql = "select szbm from yhb where yhm = ?;";
		String xydm = dao.getOneRs(sql, new String[] { yhm }, "szbm");
		return xydm;
	}

	// ����༶��ɫ��Ŀ�걨
	public boolean saveBjtsxmSb(BjtsxmForm myForm, String pkValue,
			HttpServletRequest request) throws Exception {
		// ��Ŀ����
		String xmdm = getXmdm();
		// ��Ŀ����
		String xmmc = DealString.toGBK(myForm.getXmmc());
		// ��Ŀ��ɫ
		String xmts = DealString.toGBK(myForm.getXmts());
		// ��Ŀ�����������
		String xmjsfa = DealString.toGBK(myForm.getXmjsfa());
		// ��Ŀ����
		String xmjd = DealString.toGBK(myForm.getXmjd());
		// ��ĿԤ��Ч��
		String yqxg = DealString.toGBK(myForm.getYqxg());
		// ��Ŀ�����ص�
		String ysyd = DealString.toGBK(myForm.getYsyd());
		// �걨�༶����
		String bjdm = DealString.toGBK(myForm.getBjdm());
		// �걨��Ժ����
		String xydm = DealString.toGBK(myForm.getXydm());
		// ������ְ����
		String bzrzgh = DealString.toGBK(myForm.getBzrzgh());
		// �೤ѧ��
		String bzxh = DealString.toGBK(myForm.getBzxh());
		// ��֧��ѧ��
		String tzsxh = DealString.toGBK(myForm.getTzsxh());
		// �༶����
		String bjrs = DealString.toGBK(myForm.getBjrs());
		// ��ϵ������
		String lxrxm = DealString.toGBK(myForm.getLxrxm());
		// ��ϵ��ְ��
		String lxrzw = DealString.toGBK(myForm.getLxrzw());
		// ��ϵ���ֻ��̺�
		String lxrsjdh = DealString.toGBK(myForm.getLxrqsdh());
		// ��ϵ������
		String lxrqs = DealString.toGBK(myForm.getLxrqs());
		// ��ϵ�����ҵ绰
		String lxrqsdh = DealString.toGBK(myForm.getLxrqsdh());
		// ��ϵ���ֻ�
		String lxrsj = DealString.toGBK(myForm.getLxrsj());
		// ��ϵ�˵���
		String lxremail = DealString.toGBK(myForm.getLxremail());
		// �༶��óɼ����߱�����
		String bjcjtj = DealString.toGBK(myForm.getBjcjtj());
		// ȫ��ͬѧ�Է�����ͬ
		String qbrt = DealString.toGBK(myForm.getQbrt());
		// ѧ��������Ԥ��
		String xgbjfys = DealString.toGBK(myForm.getXgbjfys());
		// ���о���Ԥ��
		String zyjfys = DealString.toGBK(myForm.getZyjfys());

		boolean flg = false;
		String[] colList = new String[] { "xmdm", "xmmc", "xmts", "xmjsfa",
				"xmjd", "yqxg", "ysyd", "bjdm", "xydm", "bzrzgh", "bzxh",
				"tzsxh", "bjrs", "lxrxm", "lxrzw", "lxrsjdh", "lxrqs",
				"lxrqsdh", "lxrsj", "lxremail", "bjcjtj", "qbrt", "xgbjfys",
				"zyjfys" };
		String[] valList = new String[] {};
		if (!"".equalsIgnoreCase(pkValue)) {
			flg = StandardOperation.delete("nbzy_tsbjxm_xmsb", "xmdm", pkValue,
					request);
			if (flg) {
				valList = new String[] { pkValue, xmmc, xmts, xmjsfa, xmjd,
						yqxg, ysyd, bjdm, xydm, bzrzgh, bzxh, tzsxh, bjrs,
						lxrxm, lxrzw, lxrsjdh, lxrqs, lxrqsdh, lxrsj, lxremail,
						bjcjtj, qbrt, xgbjfys, zyjfys };

				flg = StandardOperation.insert("nbzy_tsbjxm_xmsb", colList,
						valList, request);
			}
		} else {
			valList = new String[] { xmdm, xmmc, xmts, xmjsfa, xmjd, yqxg,
					ysyd, bjdm, xydm, bzrzgh, bzxh, tzsxh, bjrs, lxrxm, lxrzw,
					lxrsjdh, lxrqs, lxrqsdh, lxrsj, lxremail, bjcjtj, qbrt,
					xgbjfys, zyjfys };

			flg = StandardOperation.insert("nbzy_tsbjxm_xmsb", colList,
					valList, request);
		}
		return flg;
	}

	// ����༶��ɫ��Ŀ������
	public boolean saveBjtsxmSh(String tableName, String pkValue,
			String[] columns, String[] values, HttpServletRequest request)
			throws Exception {
		boolean flg = StandardOperation.update(tableName, columns, values,
				"xmdm", pkValue, request);
		return flg;
	}

	// ����༶��ɫ��Ŀ����������
	public boolean saveBjtsxmjdSh(String pkValue, String[] columns,
			String[] values, HttpServletRequest request) throws Exception {
		boolean flg = StandardOperation.update("nbzy_tsbjxm_xmjs", columns,
				values, "xmdm", pkValue, request);
		return flg;
	}

	// ɾ��������Ϣ
	public boolean delxmSq(String tableName, String xmdm,
			HttpServletRequest request) throws Exception {
		boolean flg = false;
		flg = StandardOperation.delete(tableName, "xmdm", xmdm, request);
		return flg;
	}

	// �Ƿ�ͨ�����
	public boolean noSh(String tableName, String xmdm) {
		boolean flg = false;
		String sql = "select bzrsh from " + tableName + " where xmdm = ?";
		String bzrsh = dao.getOneRs(sql, new String[] { xmdm }, "bzrsh");
		if (!"��ͨ��".equalsIgnoreCase(bzrsh) && !"δͨ��".equalsIgnoreCase(bzrsh)) {
			flg = true;
		}
		return flg;
	}

	// ��ȡϵͳʱ��
	public String GetSysTime() {
		String sql = "select to_char(sysdate,'yyyy-mm-dd') nowTime from dual";
		return dao.getOneRs(sql, new String[] {}, "nowTime");
	}

	// ��ȡ��Ŀ����
	public String getXmdm() {
		DAO dao = DAO.getInstance();
		String sql = "select d.xmdm from (select rownum num, c.xmdm, c.tempxmdm"
				+ " from (select a.xmdm, (to_char(b.xmdm) - to_char(a.xmdm)) tempxmdm"
				+ " from (select rownum num, t.* from nbzy_tsbjxm_xmsb t) a, (select rownum - 1 num, "
				+ " t.* from nbzy_tsbjxm_xmsb t) b where a.num = b.num) c where c.tempxmdm > 1) d where d.num = 1";
		String xmdm1 = dao.getOneRs(sql, new String[] {}, "xmdm");
		int newDm = 0;
		if (xmdm1 != null && !"".equals(xmdm1)) {
			newDm = Integer.parseInt(xmdm1) + 1;
		}
		sql = " select MAX(t.xmdm)+1 xmdm from nbzy_tsbjxm_xmsb t";
		String xmdm2 = dao.getOneRs(sql, new String[] {}, "xmdm");
		if (xmdm2 == null || "".equals(xmdm2)) {
			newDm = 1;
		}
		if ((xmdm1 == null || "".equals(xmdm1))
				&& (xmdm2 != null && !"".equals(xmdm2))) {
			newDm = Integer.parseInt(xmdm2);
		}
		String str = String.valueOf(newDm);
		sql = "select min(t.xmdm) xmdm from nbzy_tsbjxm_xmsb t";
		String xmdm3 = dao.getOneRs(sql, new String[] {}, "xmdm");
		if (!"0001".equalsIgnoreCase(xmdm3)) {
			str = "1";
		}
		if (str.length() == 1) {
			str = "000" + str;
		} else if (str.length() == 2) {
			str = "00" + str;
		} else if (str.length() == 3) {
			str = "0" + str;
		}
		return str;
	}

	// ��ȡ��ɫ��Ŀ�걨�����ϢList
	public List<HashMap<String, String>> getTsxmsbjgList(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		DAO dao = DAO.getInstance();

		String nj = DealString.toGBK(myForm.getNj());
		String nd = DealString.toGBK(myForm.getNd());
		String xh = DealString.toGBK(myForm.getXh());
		String xm = DealString.toGBK(myForm.getXm());
		String xydm = DealString.toGBK(myForm.getXydm());
		String zydm = DealString.toGBK(myForm.getZydm());
		String bjdm = DealString.toGBK(myForm.getBjdm());

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and nj ='" + nj
				+ "'");
		if ("stu".equalsIgnoreCase(userType)) {
			query.append(" and bzxh ='" + userName + "'");
		} else {
			query.append("".equalsIgnoreCase(xh) ? " and 1=1" : " and bzxh ='"
					+ xh + "'");
		}
		query.append("".equalsIgnoreCase(xm) ? " and 1=1" : " and bzxm like '%"
				+ xm + "%'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and xydm ='"
				+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and zydm ='"
				+ zydm + "'");
		query.append("".equalsIgnoreCase(nd) ? " and 1=1" : " and xmsqsj like'"
				+ nd + "%'");
		if ("teacher".equalsIgnoreCase(userType)) {
			query.append(" and bjdm in ('',");
			List bjList = getBzrBj(userName);
			for (int i = 0; i < bjList.size(); i++) {
				query.append("'" + bjList.get(i) + "',");
			}
			query.append("'')");
		} else {
			query.append("".equalsIgnoreCase(bjdm) ? " and 1=1"
					: " and bjdm ='" + bjdm + "'");
		}

		if ("xy".equalsIgnoreCase(userType)) {
			query.append(" and bzrsh='��ͨ��'");
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			query.append(" and xysh='��ͨ��'");
		}
		String sql = "select * from view_nbzy_bjtsxmsbjg" + query.toString();

		System.out.println(sql);
		List<HashMap<String, String>> rsList = dao.getArrayList(sql,
				new String[] {}, colList);

		return rsList;
	}

	// ��ȡ���ͨ������ɫ��Ŀ��ϢList
	public List<HashMap<String, String>> getTsxmJdList(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		DAO dao = DAO.getInstance();

		String nj = DealString.toGBK(myForm.getNj());
		String nd = DealString.toGBK(myForm.getNd());
		String xm = DealString.toGBK(myForm.getXm());
		String xydm = DealString.toGBK(myForm.getXydm());
		String zydm = DealString.toGBK(myForm.getZydm());

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and nj ='" + nj
				+ "'");
		if ("stu".equalsIgnoreCase(userType)) {
			query.append(" and bzxh ='" + userName + "'");
		}
		query.append("".equalsIgnoreCase(xm) ? " and 1=1" : " and bzxm like '%"
				+ xm + "%'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and xydm ='"
				+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and zydm ='"
				+ zydm + "'");
		query.append("".equalsIgnoreCase(nd) ? " and 1=1" : " and xmsqsj like'"
				+ nd + "%'");
		query.append(" and xxsh='��ͨ��'");
		if ("teacher".equalsIgnoreCase(userType)) {
			query.append(" and bjdm in ('',");
			List bjList = getBzrBj(userName);
			for (int i = 0; i < bjList.size(); i++) {
				query.append("'" + bjList.get(i) + "',");
			}
			query.append("'')");
		}

		String sql = "select a.xmdm, a.xmmc, a.bzxm, a.nj, a.xymc,a.bjmc, a.xmsqsj, case when b.bzrsh = '��ͨ��' then"
				+ " '<img src=images/ytg.gif  ></img>' when b.bzrsh = 'δͨ��' then  '<img src=images/wtg.gif  ></img>'"
				+ " else '<img src=images/wsh.gif  ></img>' end bzrsh from (select * from view_nbzy_bjtsxmsbjg"
				+ query.toString()
				+ ")a left join (select distinct (xmdm),bzrsh from nbzy_tsbjxm_xmjs) b on a.xmdm = b.xmdm";

		System.out.println(sql);
		List<HashMap<String, String>> rsList = dao.getArrayList(sql,
				new String[] {}, colList);

		return rsList;
	}

	// ��ȡ��ɫ��Ŀ��ϸ
	public HashMap<String, String> getTsxmsbDetail(String xmdm) {

		HashMap<String, String> map = new HashMap<String, String>();

		String sql = "select * from view_nbzy_bjtsxmsbjg where xmdm=?";

		String[] colList = new String[] { "xmdm", "xmmc", "bzxm", "tzsxm",
				"nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "xmsqsj",
				"bzrsh", "xysh", "xxsh", "bzrshimg", "xyshimg", "xxshimg",
				"xmsqsj", "xmts", "xmjsfa", "xmjd", "yqxg", "ysyd", "bjdm",
				"xydm", "bzrzgh", "bzxh", "tzsxh", "bjrs", "lxrxm", "lxrzw",
				"lxrsjdh", "lxrqs", "lxrqsdh", "lxrsj", "lxremail", "bjcjtj",
				"qbrt", "xgbjfys", "zyjfys", "bzrsh", "bzryj", "bzrshsj",
				"xysh", "xyyj", "xyshr", "xyshsj", "xxsh", "xxyj", "xxshr",
				"xxshsj" };
		map = dao.getMap(sql, new String[] { xmdm }, colList);
		return map;
	}

	// ��ȡ��ɫ��Ŀ���������ϸ
	public List<HashMap<String, String>> getTsxmjsjdDetail(String xmdm) {
		String sql = "select * from nbzy_tsbjxm_xmjs where xmdm=?";
		String[] colList = new String[] { "sbdy", "sjjd", "jbnr" };
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { xmdm }, colList);
		return list;
	}

	// ����༶��ɫ��Ŀ�����걨
	public boolean saveBjtsxmJd(BjtsxmForm myForm, HttpServletRequest request)
			throws Exception {

		// ��Ŀ����
		String xmdm = DealString.toGBK(myForm.getXmdm());
		// �걨����
		String sbdy = DealString.toGBK(myForm.getSbdy());
		// ʱ��׶�
		String sjjd = DealString.toGBK(myForm.getSjjd());
		String[] arrSjjd = sjjd.split("!!@@!!");
		// ��������
		String jbnr = DealString.toGBK(myForm.getJbnr());
		String[] arrJbnr = jbnr.split("!!@@!!");

		// String[] colList = new String[] { "xmdm", "sbdy", "sjjd", "jbnr", };
		// String[] valList = new String[] { xmdm, sbdy, sjjd, jbnr };
		String sql = "delete nbzy_tsbjxm_xmjs a where��a.xmdm = ?";
		StringBuffer sb = new StringBuffer();

		boolean flg = dao.runUpdate(sql, new String[] { xmdm });
		for (int i = 0; i < arrSjjd.length; i++) {
			sql = "insert into nbzy_tsbjxm_xmjs(xmdm, sbdy, sjjd, jbnr) values('"
					+ xmdm
					+ "','"
					+ sbdy
					+ "','"
					+ arrSjjd[i]
					+ "','"
					+ arrJbnr[i] + "')";
			sb.append(sql);
			sb.append("!!#!!");
		}

		String[] pksql = sb.toString().split("!!#!!");
		dao.runBatch(pksql);

		return flg;
	}

	// ��ȡ��ɫ��Ŀ����������ϢList
	public List<HashMap<String, String>> getTsxmjsysList(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		DAO dao = DAO.getInstance();

		String nj = DealString.toGBK(myForm.getNj());
		String nd = DealString.toGBK(myForm.getNd());
		String xh = DealString.toGBK(myForm.getXh());
		String xm = DealString.toGBK(myForm.getXm());
		String xydm = DealString.toGBK(myForm.getXydm());
		String zydm = DealString.toGBK(myForm.getZydm());
		String bjdm = DealString.toGBK(myForm.getBjdm());
		String sql = "";

		StringBuffer query = new StringBuffer();

		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and nj ='" + nj
				+ "'");
		if ("stu".equalsIgnoreCase(userType)) {
			query.append(" and bzxh ='" + userName + "'");
		} else {
			query.append("".equalsIgnoreCase(xh) ? " and 1=1" : " and bzxh ='"
					+ xh + "'");
		}
		query.append("".equalsIgnoreCase(xm) ? " and 1=1" : " and bzxm like '%"
				+ xm + "%'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and xydm ='"
				+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and zydm ='"
				+ zydm + "'");
		query.append("".equalsIgnoreCase(nd) ? " and 1=1" : " and xmsqsj like'"
				+ nd + "%'");
		if ("teacher".equalsIgnoreCase(userType)) {
			query.append(" and bjdm in ('',");
			List bjList = getBzrBj(userName);
			for (int i = 0; i < bjList.size(); i++) {
				query.append("'" + bjList.get(i) + "',");
			}
			query.append("'')");
		} else {
			query.append("".equalsIgnoreCase(bjdm) ? " and 1=1"
					: " and bjdm ='" + bjdm + "'");
		}

		if ("xy".equalsIgnoreCase(userType)) {
			sql += "select * from (";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			sql += "select * from (";
		}
		sql += "select t.xmdm, t.xmmc, t.bzxm,t.nj, t.xymc, t.bjmc, t.xmsqsj, c.bzrsh, case"
				+ " when c.bzrsh = '��ͨ��' then '<img src=images/ytg.gif  ></img>' when c.bzrsh = 'δͨ��' then"
				+ "'<img src=images/wtg.gif  ></img>' else '<img src=images/wsh.gif  ></img>' end bzrshimg,"
				+ " c.xysh, case when c.xysh = '��ͨ��' then  '<img src=images/ytg.gif  ></img>'  when c.xysh = 'δͨ��' then"
				+ " '<img src=images/wtg.gif  ></img>' else  '<img src=images/wsh.gif  ></img>' end xyshimg, c.xxsh,"
				+ " case when c.xxsh = '��ͨ��' then '<img src=images/ytg.gif  ></img>' when c.xxsh = 'δͨ��' then"
				+ " '<img src=images/wtg.gif  ></img>' else '<img src=images/wsh.gif  ></img>' end xxshimg"
				+ " from (select a.* from view_nbzy_bjtsxmsbjg a, (select distinct (xmdm), bzrsh from nbzy_tsbjxm_xmjs) b"
				+ " where a.xmdm = b.xmdm and b.bzrsh = '��ͨ��' "
				+ query.toString()
				+ ") t left join nbzy_tsbjxm_xmys c on t.xmdm = c.xmdm";

		if ("xy".equalsIgnoreCase(userType)) {
			sql += ") where bzrsh = '��ͨ��'";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			sql += ") where xysh = '��ͨ��'";
		}
		System.out.println(sql);
		List<HashMap<String, String>> rsList = dao.getArrayList(sql,
				new String[] {}, colList);

		return rsList;
	}

	// �೤���ڰ༶ȫ��ͬѧ�б�
	public List<HashMap<String, String>> getRyList(String bzxh) {
		String sql = "select xh, xm from view_xsjbxx where bjdm = (select bjdm from view_xsjbxx where xh = ?)";
		List<HashMap<String, String>> ryList = dao.getList(sql,
				new String[] { bzxh }, new String[] { "xh", "xm" });
		return ryList;
	}

	// ����༶��ɫ��Ŀ����
	public boolean saveBjtsxmYs(BjtsxmForm myForm, HttpServletRequest request)
			throws Exception {

		// ��Ŀ����
		String xmdm = DealString.toGBK(myForm.getXmdm());
		// ��Ŀ�������
		String jsgc = DealString.toGBK(myForm.getJsgc());
		// ��Ŀʵʩǰ״��
		String ssqzk = DealString.toGBK(myForm.getSsqzk());
		// ��Ŀʵʩ��״��
		String sshzk = DealString.toGBK(myForm.getSshzk());
		// ��Ŀ����ɹ�
		String jscg = DealString.toGBK(myForm.getJscg());
		// ��Ŀ�����ʧ
		String jsds = DealString.toGBK(myForm.getJsds());
		// ��Ŀ֧������
		String zcjf = DealString.toGBK(myForm.getZcjf())
				+ DealString.toGBK(myForm.getZcxm());

		String[] colList = new String[] { "xmdm", "jsgc", "ssqzk", "sshzk",
				"jscg", "jsds", "zcjf" };
		String[] valList = new String[] { xmdm, jsgc, ssqzk, sshzk, jscg, jsds,
				zcjf };
		boolean flg = StandardOperation.delete("nbzy_tsbjxm_xmys", "xmdm",
				xmdm, request);
		if (flg) {
			flg = StandardOperation.insert("nbzy_tsbjxm_xmys", colList,
					valList, request);
		}

		if (flg) {

			flg = StandardOperation.delete("nbzy_tsbjxm_xmystxyj", "xmdm",
					xmdm, request);

			// ������ѧ��
			String pjzxh = DealString.toGBK(myForm.getPjzxh());
			String[] arrXh = pjzxh.split("!!@@!!");
			// ��������
			String pjnr = DealString.toGBK(myForm.getPjnr());
			String[] arrNr = pjnr.split("!!@@!!");

			StringBuffer sb = new StringBuffer();
			String sql = "";
			if (arrXh != null && arrXh.length > 0) {
				for (int i = 0; i < arrXh.length; i++) {
					sql = "insert into nbzy_tsbjxm_xmystxyj(xmdm,pjzxh,pjnr) values('"
							+ xmdm + "','" + arrXh[i] + "','" + arrNr[i] + "')";
					sb.append(sql);
					sb.append("!!#!!");
				}
			}
			String[] pksql = sb.toString().split("!!#!!");
			dao.runBatch(pksql);
		}
		return flg;
	}

	// ����༶��ɫ��Ŀ��������
	public boolean saveBjtsxmYqys(BjtsxmForm myForm, HttpServletRequest request)
			throws Exception {

		// ��Ŀ����
		String xmdm = DealString.toGBK(myForm.getXmdm());
		// ��Ŀ������
		String xmfzr = DealString.toGBK(myForm.getXmfzr());
		// ��ϵ�绰
		String lxdh = DealString.toGBK(myForm.getLxdh());
		// ��������
		String sqly = DealString.toGBK(myForm.getSqly());
		// ԭ����ʱ��
		String yyssj = DealString.toGBK(myForm.getYyssj());
		// ��������ʱ��
		String yqyssj = DealString.toGBK(myForm.getYqyssj());

		String[] colList = new String[] { "xmdm", "xmfzr", "lxdh", "sqly",
				"yyssj", "yqyssj" };
		String[] valList = new String[] { xmdm, xmfzr, lxdh, sqly, yyssj,
				yqyssj };
		boolean flg = StandardOperation.delete("nbzy_tsbjxm_yqys", "xmdm",
				xmdm, request);
		if (flg) {
			flg = StandardOperation.insert("nbzy_tsbjxm_yqys", colList,
					valList, request);
		}
		return flg;
	}

	// ��ȡ��ɫ��Ŀ������ϸ
	public HashMap<String, String> getTsxmYsDetail(String xmdm) {

		HashMap<String, String> map = new HashMap<String, String>();

		String sql = "select * from (select b.bzryj,b.xyyj,b.xxyj,a.bzxh,a.xmdm, a.xmmc, a.bzxm, a.xmsqsj,b.jsgc,"
				+ " b.ssqzk, b.sshzk,b.jscg, b.jsds, b.zcjf from view_nbzy_bjtsxmsbjg a"
				+ " left join nbzy_tsbjxm_xmys b on a.xmdm = b.xmdm) where xmdm = ?";

		String[] colList = new String[] { "bzryj", "xyyj", "xxyj", "bzxh",
				"xmdm", "xmmc", "bzxm", "xmsqsj", "jsgc", "ssqzk", "sshzk",
				"jscg", "jsds", "zcjf" };
		map = dao.getMap(sql, new String[] { xmdm }, colList);
		return map;
	}

	// ��ȡ��ɫ��Ŀ����������ϸ
	public HashMap<String, String> getTsxmYqysDetail(String xmdm) {

		HashMap<String, String> map = new HashMap<String, String>();

		String sql = "select * from (select a.bjmc,a.xymc,b.bzryj,b.xyyj,b.xxyj,a.bzxh,a.xmdm, a.xmmc, a.bzxm, a.xmsqsj,b.xmfzr,"
				+ " b.lxdh, b.sqly,b.yyssj, b.yqyssj from view_nbzy_bjtsxmsbjg a"
				+ " left join nbzy_tsbjxm_yqys b on a.xmdm = b.xmdm) where xmdm = ?";

		String[] colList = new String[] { "bjmc", "xymc", "bzryj", "xyyj",
				"xxyj", "bzxh", "xmdm", "xmmc", "bzxm", "xmsqsj", "xmfzr",
				"lxdh", "sqly", "yyssj", "yqyssj" };
		map = dao.getMap(sql, new String[] { xmdm }, colList);
		return map;
	}

	// ��ȡ��ɫ��Ŀ���հ༶ͬѧ���
	public List<HashMap<String, String>> getTsxmYsTxyj(String xmdm) {
		String sql = "select pjzxh,pjnr from nbzy_tsbjxm_xmystxyj where xmdm = ?";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { xmdm }, new String[] { "pjzxh", "pjnr" });
		return list;

	}

	// ��ð༶ͬѧ����
	public String getXsxm(String xh) {
		String sql = "select xm from view_xsjbxx where xh = ?";
		String xm = dao.getOneRs(sql, new String[] { xh }, "xm");
		return xm;
	}

	// ��ȡ��ɫ��Ŀ����������ϢList
	public List<HashMap<String, String>> getTsxmYqys(BjtsxmForm myForm,
			String[] colList, String userName, String userType)
			throws SQLException {
		DAO dao = DAO.getInstance();

		String nj = DealString.toGBK(myForm.getNj());
		String nd = DealString.toGBK(myForm.getNd());
		String xh = DealString.toGBK(myForm.getXh());
		String xm = DealString.toGBK(myForm.getXm());
		String xydm = DealString.toGBK(myForm.getXydm());
		String zydm = DealString.toGBK(myForm.getZydm());
		String bjdm = DealString.toGBK(myForm.getBjdm());
		String sql = "";

		StringBuffer query = new StringBuffer();

		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and nj ='" + nj
				+ "'");
		if ("stu".equalsIgnoreCase(userType)) {
			query.append(" and bzxh ='" + userName + "'");
		} else {
			query.append("".equalsIgnoreCase(xh) ? " and 1=1" : " and bzxh ='"
					+ xh + "'");
		}
		query.append("".equalsIgnoreCase(xm) ? " and 1=1" : " and bzxm like '%"
				+ xm + "%'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and xydm ='"
				+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and zydm ='"
				+ zydm + "'");
		query.append("".equalsIgnoreCase(nd) ? " and 1=1" : " and xmsqsj like'"
				+ nd + "%'");
		if ("teacher".equalsIgnoreCase(userType)) {
			query.append(" and bjdm in ('',");
			List bjList = getBzrBj(userName);
			for (int i = 0; i < bjList.size(); i++) {
				query.append("'" + bjList.get(i) + "',");
			}
			query.append("'')");
		} else {
			query.append("".equalsIgnoreCase(bjdm) ? " and 1=1"
					: " and bjdm ='" + bjdm + "'");
		}

		if ("xy".equalsIgnoreCase(userType)) {
			sql += "select * from (";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			sql += "select * from (";
		}
		sql += "select t.xmdm, t.xmmc, t.bzxm,t.nj, t.xymc, t.bjmc, t.xmsqsj, c.bzrsh, case"
				+ " when c.bzrsh = '��ͨ��' then '<img src=images/ytg.gif  ></img>' when c.bzrsh = 'δͨ��' then"
				+ "'<img src=images/wtg.gif  ></img>' else '<img src=images/wsh.gif  ></img>' end bzrshimg,"
				+ " c.xysh, case when c.xysh = '��ͨ��' then  '<img src=images/ytg.gif  ></img>'  when c.xysh = 'δͨ��' then"
				+ " '<img src=images/wtg.gif  ></img>' else  '<img src=images/wsh.gif  ></img>' end xyshimg, c.xxsh,"
				+ " case when c.xxsh = '��ͨ��' then '<img src=images/ytg.gif  ></img>' when c.xxsh = 'δͨ��' then"
				+ " '<img src=images/wtg.gif  ></img>' else '<img src=images/wsh.gif  ></img>' end xxshimg"
				+ " from (select a.* from view_nbzy_bjtsxmsbjg a, (select distinct (xmdm), xxsh from nbzy_tsbjxm_xmys) b"
				+ " where a.xmdm = b.xmdm and b.xxsh = '��ͨ��' "
				+ query.toString()
				+ ") t left join nbzy_tsbjxm_yqys c on t.xmdm = c.xmdm";

		if ("xy".equalsIgnoreCase(userType)) {
			sql += ") where bzrsh = '��ͨ��'";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			sql += ") where xysh = '��ͨ��'";
		}
		System.out.println(sql);
		List<HashMap<String, String>> rsList = dao.getArrayList(sql,
				new String[] {}, colList);

		return rsList;
	}

}
