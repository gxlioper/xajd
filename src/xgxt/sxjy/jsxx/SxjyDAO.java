package xgxt.sxjy.jsxx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SxjyForm;

public class SxjyDAO {

	// 保存新生调查表
	public boolean saveSjxx(SxjyForm myForm, HttpServletRequest request)
			throws Exception {

		// 问卷流水号
		String sjlsh = DealString.toGBK(myForm.getSjlsh());
		// 问卷名
		String sjm = DealString.toGBK(myForm.getSjm());
		// 问卷说明
		String sjsm = DealString.toGBK(myForm.getSjsm());
		// 问卷显示标记
		String sjxsbj = DealString.toGBK(myForm.getSjxsbj());
		// 加入时间
		// String jrsj = DealString.toGBK(myForm.getJrsj());

		boolean flg = StandardOperation.delete("jsxx_xsdcsjb", "sjlsh", sjlsh,
				request);
		if (flg) {
			flg = StandardOperation.insert("jsxx_xsdcsjb", new String[] {
					"sjlsh", "sjm", "sjsm", "sjxsbj" }, new String[] { sjlsh,
					sjm, sjsm, sjxsbj }, request);
		}

		return flg;
	}

	// 保存新生调查表
	public boolean saveNrxx(SxjyForm myForm, HttpServletRequest request)
			throws Exception {

		// 试题编号
		String stbh = DealString.toGBK(myForm.getStbh());
		// 试题类型
		String stlx = DealString.toGBK(myForm.getStlx());
		// 试题内容
		String stnr = DealString.toGBK(myForm.getStnr());
		// 试题答案
		String stda = DealString.toGBK(myForm.getStda());
		// 试题答案子选项
		String stzda = DealString.toGBK(myForm.getStzda());
		// 试题显示标记
		String stxsbj = DealString.toGBK(myForm.getStxsbj());
		// 加入时间
		// String jrsj = DealString.toGBK(myForm.getJrsj());

		boolean flg = StandardOperation.delete("jsxx_xsdcnrb", "stbh", stbh,
				request);
		if (flg) {
			flg = StandardOperation.insert("jsxx_xsdcnrb", new String[] {
					"stbh", "stlx", "stnr", "stda", "stzda", "stxsbj" },
					new String[] { stbh, stlx, stnr, stda, stzda, stxsbj },
					request);
		}

		return flg;
	}

	// 保存组卷信息
	public boolean saveZj(SxjyForm myForm, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();
		// 试题编号
		String[] stbhList = myForm.getStbhList();
		// 试卷流水号
		String sjlsh = DealString.toGBK(myForm.getSjlsh());

		String stbh = "";
		String sql = "delete from jsxx_xszjxxb where sjlsh=?";
		boolean flg = dao.runUpdate(sql, new String[] { sjlsh });

		if (flg) {
			if (stbhList != null && stbhList.length > 0) {
				for (int i = 0; i < stbhList.length; i++) {
					stbh = DealString.toGBK(stbhList[i]);
					sql = "insert into jsxx_xszjxxb(sjlsh,stbh) values('"
							+ sjlsh + "','" + stbh + "')";
					dao.runUpdate(sql, new String[] {});
				}
			}
		}

		return flg;
	}

	// 保存试题回答信息
	public boolean saveTx(SxjyForm myForm, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();
		// 回答人
		String hdr = myForm.getXh();
		// 试题编号
		String stbh = myForm.getStbh();
		// 试卷流水号
		String sjlsh = myForm.getSjlsh();
		// 试题答案
		String stda = myForm.getStda();
		// 试题答案子选项
		String stzda = DealString.toGBK(myForm.getStzda());

		String sql = "delete from jsxx_xssttxb where hdr=? and sjlsh=? and stbh=?";
		boolean flg = dao.runUpdate(sql, new String[] { hdr, sjlsh, stbh });

		if (flg) {
			sql = "insert into jsxx_xssttxb(hdr,sjlsh,stbh,stda,stzda) values('"
					+ hdr
					+ "','"
					+ sjlsh
					+ "','"
					+ stbh
					+ "','"
					+ stda
					+ "','"
					+ stzda + "')";
			dao.runUpdate(sql, new String[] {});
		}

		return flg;
	}

	// 删除新生调查表
	public boolean delSjxx(String pkValue, HttpServletRequest request)
			throws Exception {

		boolean flg = StandardOperation.delete("jsxx_xsdcsjb", "sjlsh",
				pkValue, request);
		return flg;
	}

	// 删除试题
	public boolean delSt(String pkValue) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "delete from jsxx_xsdcnrb where stbh = ?";
		boolean flg = dao.runUpdate(sql, new String[] { pkValue });
		sql = "delete from jsxx_xszjxxb where stbh = ?";
		if (flg) {
			flg = dao.runUpdate(sql, new String[] { pkValue });
		}
		return flg;
	}

	// 获得系统时间
	public String getNowDay() {
		DAO dao = DAO.getInstance();
		return dao.getOneRs(
				"select to_char(sysdate, 'YYYYMMDD') nowDay from dual",
				new String[] {}, "nowDay");
	}

	// 获得该问卷的试题数目
	public String getStNum(String sjlsh) {
		DAO dao = DAO.getInstance();
		String sql = "select count(c.stbh) num from jsxx_xsdcsjb a, jsxx_xszjxxb b, jsxx_xsdcnrb c"
				+ " where a.sjlsh = b.sjlsh and b.stbh = c.stbh and a.sjlsh = ? and c.stxsbj = '1' order by c.stbh";
		return dao.getOneRs(sql, new String[] { sjlsh }, "num");
	}

	// 获得填写的问卷调查名称
	public HashMap<String, String> getWjmc(String sjlsh) {
		DAO dao = DAO.getInstance();
		return dao.getMap("select sjlsh,sjm from jsxx_xsdcsjb where sjlsh = ?",
				new String[] { sjlsh }, new String[] { "sjlsh", "sjm" });
	}

	// 获得该问卷的试题内容
	public HashMap<String, String> getSt(String sjlsh, String num) {
		DAO dao = DAO.getInstance();
		String sql = "select * from (select d.*, rownum num from (select b.sjlsh,c.* from jsxx_xsdcsjb"
				+ " a, jsxx_xszjxxb b, jsxx_xsdcnrb c where a.sjlsh = b.sjlsh and b.stbh = c.stbh"
				+ " and a.sjlsh = ? and c.stxsbj = '1' order by c.stbh) d) where num = ?";
		return dao.getMap(sql, new String[] { sjlsh, num }, new String[] {
				"sjlsh", "stbh", "stlx", "stnr", "stda", "stzda" });
	}

	// 获得学生问卷调查情况
	public HashMap<String, String> getWjdcQk(String xh, String sjlsh) {
		DAO dao = DAO.getInstance();
		String sql = "select distinct(xh), xb, xm, xymc,zymc,bjmc,sjm from view_jsxx_xswjdcqk where xh = ? and sjlsh = ? ";
		return dao.getMap(sql, new String[] { xh, sjlsh }, new String[] { "xh",
				"xb", "xm", "xymc", "zymc", "bjmc", "sjm" });
	}

	// 获得学生问卷调答案
	public List<HashMap<String, String>> getWjdcXsList(String xh, String sjlsh) {
		DAO dao = DAO.getInstance();
		String sql = "select stbh, stnr, stda,stzda, xsstda, xsstzda from view_jsxx_xswjdcqk where xh = ? and sjlsh = ? order by stbh";
		return dao.getList(sql, new String[] { xh, sjlsh }, new String[] {
				"stbh", "stnr", "stda", "stzda", "xsstda", "xsstzda" });
	}

	// 获得试题已选择答案
	public HashMap<String, String> getStDa(String sjlsh, String num, String hdr) {
		DAO dao = DAO.getInstance();
		String sql = "select f.stda, f.stzda from (select d.*, rownum num from (select"
				+ " b.sjlsh, c.* from jsxx_xsdcsjb a, jsxx_xszjxxb b, jsxx_xsdcnrb c"
				+ " where a.sjlsh = b.sjlsh and b.stbh = c.stbh and a.sjlsh = ? and c.stxsbj = '1' order by c.stbh) d) e,"
				+ " jsxx_xssttxb f where e.num = ? and f.hdr = ? and f.sjlsh = e.sjlsh and f.stbh = e.stbh";
		return dao.getMap(sql, new String[] { sjlsh, num, hdr }, new String[] {
				"stda", "stzda" });
	}

	// 获取编制代码
	public String getSjLsh() {
		DAO dao = DAO.getInstance();
		String sql = "select d.sjlsh from (select rownum num, c.sjlsh, c.templsh from (select "
				+ " a.sjlsh, (to_char(b.sjlsh) - to_char(a.sjlsh)) templsh from (select rownum num,"
				+ " t.* from jsxx_xsdcsjb t) a,(select rownum - 1 num, t.* from jsxx_xsdcsjb t) b"
				+ " where a.num = b.num) c where c.templsh > 1) d where d.num = 1";
		String lsh1 = dao.getOneRs(sql, new String[] {}, "sjlsh");
		int newDm = 0;
		if (lsh1 != null && !"".equals(lsh1)) {
			newDm = Integer.parseInt(lsh1) + 1;
		}
		sql = " select MAX(t.sjlsh)+1 sjlsh from jsxx_xsdcsjb t";
		String lsh2 = dao.getOneRs(sql, new String[] {}, "sjlsh");
		if (lsh2 == null || "".equals(lsh2)) {
			newDm = 1;
		}
		if ((lsh1 == null || "".equals(lsh1))
				&& (lsh2 != null && !"".equals(lsh2))) {
			newDm = Integer.parseInt(lsh2);
		}
		String str = String.valueOf(newDm);
		if (str.length() == 1) {
			str = "000" + str;
		} else if (str.length() == 2) {
			str = "00" + str;
		} else if (str.length() == 3) {
			str = "0" + str;
		}
		return str;
	}

	// 获取编制代码
	public String getNrLsh() {
		DAO dao = DAO.getInstance();
		String sql = "select d.stbh from (select rownum num, c.stbh, c.templsh from (select "
				+ " a.stbh, (to_char(b.stbh) - to_char(a.stbh)) templsh from (select rownum num,"
				+ " t.* from jsxx_xsdcnrb t) a,(select rownum - 1 num, t.* from jsxx_xsdcnrb t) b"
				+ " where a.num = b.num) c where c.templsh > 1) d where d.num = 1";
		String lsh1 = dao.getOneRs(sql, new String[] {}, "stbh");
		int newDm = 0;
		if (lsh1 != null && !"".equals(lsh1)) {
			newDm = Integer.parseInt(lsh1) + 1;
		}
		sql = " select MAX(t.stbh)+1 stbh from jsxx_xsdcnrb t";
		String lsh2 = dao.getOneRs(sql, new String[] {}, "stbh");
		if (lsh2 == null || "".equals(lsh2)) {
			newDm = 1;
		}
		if ((lsh1 == null || "".equals(lsh1))
				&& (lsh2 != null && !"".equals(lsh2))) {
			newDm = Integer.parseInt(lsh2);
		}
		String str = String.valueOf(newDm);
		if (str.length() == 1) {
			str = "000" + str;
		} else if (str.length() == 2) {
			str = "00" + str;
		} else if (str.length() == 3) {
			str = "0" + str;
		}
		return str;
	}

	// 获取试题详细信息
	public HashMap<String, String> getStDetail(String stbh) {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select a.* from jsxx_xsdcnrb a where a.stbh = ?";

		map = dao.getMap(sql, new String[] { stbh }, new String[] { "stbh",
				"stlx", "stnr", "stda", "stzda", "stxsbj", "jrsj" });
		return map;
	}

	// 获取新生问卷表详细信息
	public HashMap<String, String> getXswjbDetail(String pkValue) {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from jsxx_xsdcsjb where sjlsh =?";

		map = dao.getMap(sql, new String[] { pkValue }, new String[] { "sjlsh",
				"sjm", "sjsm", "sjxsbj", "jrsj" });
		return map;
	}

	// 获取学生基本信息
	public HashMap<String, String> getXsjbxx(String xh) {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_xsjbxx where xh =?";

		map = dao.getMap(sql, new String[] { xh }, new String[] { "xh", "xm",
				"nj", "xydm", "zydm", "bjdm" });
		return map;
	}

	// 获取新生问卷表信息List
	public Vector<Object> getXswjdcList(SxjyForm myForm, String[] colList,
			String userType) {
		DAO dao = DAO.getInstance();

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(myForm.getNd()) ? " and 1=1"
				: " and jrsj like'" + myForm.getNd() + "%'");

		Vector<Object> vector = new Vector<Object>();

		if ("stu".equalsIgnoreCase(userType)) {
			query.append(" and sjxsbj = '1'");
		}
		String sql = "select rownum 行号, a.sjlsh 主键, a.sjlsh,a.sjm, case when a.sjxsbj = '1'"
				+ " then '是' else  '否' end sjxsbj,a.jrsj from jsxx_xsdcsjb a "
				+ query.toString() + " order by a.sjlsh";

		ArrayList<String[]> rslist = dao.rsToVator(sql, new String[] {},
				colList);
		vector.addAll(rslist);
		return vector;
	}

	// 获取问卷表信息List
	public Vector<Object> getXswjdcQkList(SxjyForm myForm, String[] colList) {
		DAO dao = DAO.getInstance();

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(myForm.getNj())
				|| myForm.getNj() == null ? " and 1=1" : " and nj ='"
				+ myForm.getNj() + "'");
		query.append("".equalsIgnoreCase(myForm.getXydm())
				|| myForm.getXydm() == null ? " and 1=1" : " and xydm ='"
				+ myForm.getXydm() + "'");
		query.append("".equalsIgnoreCase(myForm.getZydm())
				|| myForm.getZydm() == null ? " and 1=1" : " and zydm ='"
				+ myForm.getZydm() + "'");
		query.append("".equalsIgnoreCase(myForm.getBjdm())
				|| myForm.getBjdm() == null ? " and 1=1" : " and bjdm ='"
				+ myForm.getBjdm() + "'");
		query.append("".equalsIgnoreCase(myForm.getXh()) ? " and 1=1"
				: " and xh ='" + myForm.getXh() + "'");
		query.append("".equalsIgnoreCase(myForm.getXm())
				|| myForm.getXm() == null ? " and 1=1" : " and xm like '%"
				+ myForm.getXm() + "%'");
		query.append("".equalsIgnoreCase(myForm.getSjlsh()) ? " and 1=1"
				: " and sjlsh = '" + myForm.getSjlsh() + "'");

		Vector<Object> vector = new Vector<Object>();

		String sql = "select * from (select t.*,rownum r from (select distinct(xh) 主键,xh,"
				+ " xb,xm,xymc,zymc,bjmc from view_jsxx_xswjdcqk "
				+ query.toString()
				+ ") t)where r<="
				+ (myForm.getPages().getStart() + myForm.getPages()
						.getPageSize())
				+ "and r>"
				+ myForm.getPages().getStart();

		ArrayList<String[]> rslist = dao.rsToVator(sql, new String[] {},
				colList);
		vector.addAll(rslist);

		// 分页
		sql = "select count(distinct(xh)) count from view_jsxx_xswjdcqk"
				+ query;
		myForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		return vector;
	}

	// 获得子选项下拉列表
	public List<HashMap<String, String>> getXlsxmc(String show_length,
			String[] xs) throws SQLException {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < Integer.parseInt(show_length); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", xs[i]);
			map.put("mc", xs[i]);
			list.add(map);
		}
		return list;
	}

	// 获得问卷调查表名下拉列表
	public List<HashMap<String, String>> getWjdcb() {
		DAO dao = DAO.getInstance();
		String sql = "select sjlsh,sjm from jsxx_xsdcsjb where sjxsbj = '1'";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {},
				new String[] { "sjlsh", "sjm" });
		return list;
	}

	// 获得已存在全部试题
	public List<HashMap<String, String>> getAllStList() {
		DAO dao = DAO.getInstance();
		String sql = "select a.stbh, a.stbh || '--' || a.stnr stnr from jsxx_xsdcnrb a order by a.stbh";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {},
				new String[] { "stbh", "stnr" });
		return list;
	}

	// 获得该试卷不存在的试题
	public List<HashMap<String, String>> getXsSjStList(String sjlsh) {
		DAO dao = DAO.getInstance();
		String query = "".equals(sjlsh) ? "and 1=1" : "and a.stxsbj = '1'";
		String sql = "select a.stbh, a.stbh || '--' || a.stnr stnr from jsxx_xsdcnrb a where not exists"
				+ " (select 1 from jsxx_xszjxxb b where a.stbh = b.stbh and b.sjlsh = ?) "
				+ query + "order by a.stbh";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { sjlsh }, new String[] { "stbh", "stnr" });
		return list;
	}

	// 获得该试卷的全部试题
	public List<HashMap<String, String>> getXsStList(String sjlsh) {
		DAO dao = DAO.getInstance();
		String sql = "select a.stbh, a.stbh || '--' || a.stnr stnr from jsxx_xsdcnrb a,"
				+ " jsxx_xszjxxb b where b.stbh = a.stbh and b.sjlsh = ? and a.stxsbj = '1' order by a.stbh";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { sjlsh }, new String[] { "stbh", "stnr" });
		return list;
	}
}