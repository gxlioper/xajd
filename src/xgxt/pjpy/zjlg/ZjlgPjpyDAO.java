package xgxt.pjpy.zjlg;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.SearchUtils;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;

public class ZjlgPjpyDAO {
	DAO dao = DAO.getInstance();// 数据操作通用工具类

	/**
	 * @author luo
	 * @describe 取得数据库系统时间
	 */
	public String getSysTime() throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select substr(systime, 0, 4) || '年' || substr(systime, 5, 2) || '月' ||"
			+ " substr(systime, 7, 2) || '日' systime from (select to_char(sysdate, 'yyyymmdd') systime from dual)";
		String systime = dao.getOneRs(sql, new String[] {}, "systime");
		return systime;
	}

	/**
	 * @author luo
	 * @describe 是否班主任
	 */
	public boolean isBzrBj(String zgh) throws SQLException {

		boolean flg = false;
		String sql = "select bjdm from fdybjb where zgh = ?";
		List bjList = dao.getList(sql, new String[] { zgh }, "bjdm");

		if (bjList != null && bjList.size() > 0) {
			flg = true;
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 获得条件字段列表
	 */
	public List<HashMap<String, String>> getZdList(String szlx) throws SQLException {
		String sql = "select * from jxjtjzdb where szlx =?";
		return dao.getList(sql, new String[]{szlx}, new String[]{"zdmc","zdsm"});

	}

	/**
	 * @author luo
	 * @describe 获得奖学金类别列表
	 */
	public List<HashMap<String, String>> getJxjlbList() throws SQLException {
		String sql = "select * from jxjlbdmb";
		return dao.getList(sql, new String[]{}, new String[]{"jxjlbdm","jxjlbmc"});

	}

	/**
	 * @author luo
	 * @describe 德育分是否设置比例
	 */
	public boolean isDySz(String xn) {

		boolean flg = false;
		String sql = "select count(*) count from zjlg_dycpf_sz where xn = ?";
		String count = dao.getOneRs(sql, new String[] { xn }, "count");

		if (Integer.parseInt(count) > 0) {
			flg = true;
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 本学年是否已经进行过德育测评分的设置
	 */
	public boolean isDySz(String zgh, String xn) throws SQLException {

		boolean flg = false;
		String sql = "select count(a.xh) num from zjlg_dypsf a, view_xsjbxx c where a.xh = c.xh"
			+ " and a.xn = ? and exists (select 1 from fdybjb b where zgh = ? and c.bjdm = b.bjdm)";
		String num = dao.getOneRs(sql, new String[] { xn, zgh }, "num");

		sql = "select count(*) blnum from zjlg_dypsf_sz where bzrzgh = ? and xn = ?";
		String blnum = dao.getOneRs(sql, new String[] { zgh, xn }, "blnum");
		if (Integer.parseInt(num) == 0 && Integer.parseInt(blnum) == 0) {
			flg = true;
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 获得德育分组成各项最高分条件
	 */
	public HashMap<String, String> getDyZgf(String xn) throws SQLException {
		String sql = "select * from zjlg_dycpf_sz where xn=?";
		HashMap<String, String> map = dao.getMap(sql, new String[]{xn},new String[]{"psfbl","wsfbl","kqfbl"});
		return map;		
	}
	/**
	 * @author luo
	 * @describe 获得条件
	 */
	public List<HashMap<String, String>> getTjList(ZjlgPjpyModel model,
			String szlx, String[] colList) throws SQLException {

		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 奖学金代码
		String jxjdm = DealString.toGBK(model.getJxjdm());
		jxjdm = !"xjbj".equalsIgnoreCase(szlx) && !"ylxfb".equalsIgnoreCase(szlx) ? jxjdm : "-";
		String sql = "select * from (select a.szlx || a.xn || a.jxjdm || a.tjzd pk,a.szlx, a.xn, a.jxjdm, a.tjzd,"
			+ "  case when a.szlx = 'rych' then '荣誉称号' when a.szlx = 'jxj' then '奖学金' when a.szlx='ylxfb' then '优良学风班级' else '先进班级' end szmc,"
			+ " b.jxjmc, a.tjmc, a.tjlx, a.tjz || case when a.tjmc like '%排名%' then"
			+ " '%' when a.tjmc like '%率%' then '%' when a.tjmc like '%递增%' then '%' when a.tjmc like '%比例%' then '%' else ''  end tjz from zjlg_pjpy_tjsz a"
			+ " left join jxjdmb b on a.jxjdm = b.jxjdm ) where xn = ? and szlx = ? and jxjdm = ?";
		List<HashMap<String, String>> rsList = dao.getArrayList(sql,
				new String[] { xn, szlx, jxjdm }, colList);

		return rsList;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 删除条件
	 */
	public boolean delTj(String pk, HttpServletRequest request)
	throws Exception {
		boolean flg = StandardOperation.delete("zjlg_pjpy_tjsz",
				"szlx || xn || jxjdm || tjzd", pk, request);
		return flg;

	}

	/**
	 * @author luo
	 * @describe 德育分条件
	 */
	public boolean dyfTj(String xn, String szlx,String jxjdm, String dyf) {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'dycpf'";
		if(!Base.isNull(jxjdm)){
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao.getMap(sql,
				new String[] { xn, szlx }, new String[] { "tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tjlx = map.get("tjlx");
		String tjz = map.get("tjz");
		float tj = Float.parseFloat(tjz);
		float dycpf =  Float.parseFloat(dyf);

		if (">".equalsIgnoreCase(tjlx)) {
			if (dycpf > tj) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (dycpf >= tj) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (dycpf < tj) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (dycpf <= tj) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (dycpf == tj) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 智育分条件
	 */
	public boolean zyfTj(String xn, String szlx,String jxjdm, String zyf) {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'zycpf'";
		if(!Base.isNull(jxjdm)){
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao.getMap(sql,
				new String[] { xn, szlx }, new String[] { "tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tjlx = map.get("tjlx");
		String tjz = map.get("tjz");
		float tj = Float.parseFloat(tjz);
		float zycpf =  Float.parseFloat(zyf);

		if (">".equalsIgnoreCase(tjlx)) {
			if (zycpf > tj) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (zycpf >= tj) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (zycpf < tj) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (zycpf <= tj) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (zycpf == tj) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 综合素质分条件
	 */
	public boolean zhszfTj(String xn, String szlx,String jxjdm, String zhszf) {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'zhcpf'";
		if(!Base.isNull(jxjdm)){
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao.getMap(sql,
				new String[] { xn, szlx }, new String[] { "tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tjlx = map.get("tjlx");
		String tjz = map.get("tjz");
		float tj = Float.parseFloat(tjz);
		float zhcpf =  Float.parseFloat(zhszf);

		if (">".equalsIgnoreCase(tjlx)) {
			if (zhcpf > tj) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (zhcpf >= tj) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (zhcpf < tj) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (zhcpf <= tj) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (zhcpf == tj) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 德育成绩排名条件
	 */
	public boolean dyfpmTj(String xh, String xn, String szlx, String jxjdm,
			String cpzdm) throws SQLException {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'dypm'";
		if (!Base.isNull(jxjdm)) {
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao.getMap(sql, new String[] { xn, szlx }, new String[] { "tj",
				"tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tj = map.get("tj");
		String tjlx = map.get("tjlx");
		float tjz = Float.parseFloat(map.get("tjz"));

		cpzdm = Base.isNull(cpzdm) ? "%" : cpzdm;

		sql = "select dypm from " + tj + " where xh = ?";
//		int dypm = Integer.parseInt(dao.getOneRs(sql, new String[] { xn, zydm,
//		bjdm, xh }, "dypm"));
		String bfb = getDybfb( xn, xh, cpzdm);
		float dypm = Base.isNull(bfb) ? 0 : Float.parseFloat(bfb);


		if (">".equalsIgnoreCase(tjlx)) {
			if (dypm < tjz) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {

			if (dypm <= tjz) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (dypm > tjz) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (dypm >= tjz) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (dypm == tjz) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 智育成绩排名条件
	 */
	public boolean zyfpmTj(String xh, String xn, String szlx, String jxjdm,
			String cpzdm) throws SQLException {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'zypm'";
		if (!Base.isNull(jxjdm)) {
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao.getMap(sql, new String[] { xn, szlx }, new String[] { "tj",
				"tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tj = map.get("tj");
		String tjlx = map.get("tjlx");
		float tjz = Float.parseFloat(map.get("tjz"));

		cpzdm = Base.isNull(cpzdm) ? "%" : cpzdm;

		sql = "select zypm from " + tj + " where xh = ?";
//		int zypm = Integer.parseInt(dao.getOneRs(sql, new String[] { xn, zydm,
//		bjdm, xh }, "zypm"));
		String bfb = getZybfb( xn, xh, cpzdm);
		float zypm = Base.isNull(bfb) ? 0 : Float.parseFloat(bfb);

		if (">".equalsIgnoreCase(tjlx)) {
			if (zypm < tjz) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (zypm <= tjz) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (zypm > tjz) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (zypm >= tjz) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (zypm == tjz) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 综合素质测评成绩排名条件
	 */
	public boolean zhpmTj(String xh, String xn, String szlx, String jxjdm,
			String cpzdm) throws SQLException {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'pm'";
		if (!Base.isNull(jxjdm)) {
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao
		.getMap(sql, new String[] { xn, szlx }, new String[] { "tj",
				"tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tj = map.get("tj");
		String tjlx = map.get("tjlx");
		float tjz = Float.parseFloat(map.get("tjz"));

		cpzdm = Base.isNull(cpzdm) ? "%" : cpzdm;

		sql = "select pm from " + tj + " where xh = ?";
//		int pm = Integer.parseInt(dao.getOneRs(sql, new String[] { xn, zydm,
//		bjdm, xh }, "pm"));
		String bfb = getZhbfb( xn, xh, cpzdm);
		float pm = Base.isNull(bfb) ? 0 : Float.parseFloat(bfb);

		if (">".equalsIgnoreCase(tjlx)) {
			if (pm < tjz) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (pm <= tjz) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (pm >tjz) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (pm >= tjz) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (pm == tjz) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 班级平均分条件
	 */
	public boolean bjpjfTj(String xn, String szlx, String jxjdm,
			String bjdm) {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'avgcj'";
		if (!Base.isNull(jxjdm)) {
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao
		.getMap(sql, new String[] { xn, szlx }, new String[] { "tj",
				"tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tj = map.get("tj");
		String tjlx = map.get("tjlx");
		float tjz = Float.parseFloat(map.get("tjz"));

		sql = "select avgcj from " + tj;
		String cj =dao.getOneRs(sql, new String[] { xn,
				bjdm }, "avgcj");
		float avgcj = Base.isNull(cj) ? 0 : Float.parseFloat(cj);

		if (">".equalsIgnoreCase(tjlx)) {
			if (avgcj > tjz) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (avgcj >= tjz) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (avgcj < tjz) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (avgcj <= tjz) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (avgcj == tjz) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 不及格率条件
	 */
	public boolean bjglTj(String xn, String szlx, String jxjdm,
			String bjdm) {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'bjgl'";
		if (!Base.isNull(jxjdm)) {
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao
		.getMap(sql, new String[] { xn, szlx }, new String[] { "tj",
				"tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tj = map.get("tj");
		String tjlx = map.get("tjlx");
		float tjz = Float.parseFloat(map.get("tjz"));

		sql = "select bjgl from " + tj;
//		String bfb =dao.getOneRs(sql, new String[] { xn,
//				bjdm }, "bjgl");
//		float bjgl = Base.isNull(bfb) ? 0 : Float.parseFloat(bfb);
		float bjgl = 0;
		
		//班级所有学生不及格门数
		String bjbgs = dao
				.getOneRs(
						"select count(a.xh) ms from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and b.bjdm=? and a.kcxz not like '%辅修二专业%' and a.cj<60",
						new String[] { xn, bjdm }, "ms");
		//班级课程总数
		String bjmczs = dao.getOneRs("select count(a.xh) ms from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and b.bjdm=? and a.kcxz not like '%辅修二专业%'", new String[]{xn,bjdm}, "ms");
		
		float bjgms = StringUtils.isNull(bjbgs) || "0".equalsIgnoreCase(bjbgs) ? 0 : Float.parseFloat(bjbgs); 
		float zms = StringUtils.isNull(bjmczs) || "0".equalsIgnoreCase(bjmczs) ? 0 : Float.parseFloat(bjmczs);
		bjgl = zms > 0 ? bjgms / zms : 0;
		
		if (">".equalsIgnoreCase(tjlx)) {
			if (bjgl > tjz) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (bjgl >= tjz) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (bjgl < tjz) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (bjgl <= tjz) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (bjgl == tjz) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 文明寝室个数条件
	 */
	public boolean wmqsTj(String xn, String szlx,String jxjdm, String wmqsgs) {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'wmqsgs'";
		if(!Base.isNull(jxjdm)){
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao.getMap(sql,
				new String[] { xn, szlx }, new String[] { "tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tjlx = map.get("tjlx");
		String tjz = map.get("tjz");

		int tj = Integer.parseInt(tjz);
		int gs =  Integer.parseInt(wmqsgs);

		if (">".equalsIgnoreCase(tjlx)) {
			if (gs > tj) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (gs >= tj) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (gs < tj) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (gs <= tj) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (gs == tj) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe A级文明寝室个数条件
	 */
	public boolean ajqsgsTj(String xn, String szlx,String jxjdm, String ajqsgs) {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'ajqsgs'";
		if(!Base.isNull(jxjdm)){
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao.getMap(sql,
				new String[] { xn, szlx }, new String[] { "tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tjlx = map.get("tjlx");
		String tjz = map.get("tjz");

		int tj = Integer.parseInt(tjz);
		int gs =  Integer.parseInt(ajqsgs);

		if (">".equalsIgnoreCase(tjlx)) {
			if (gs > tj) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (gs >= tj) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (gs < tj) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (gs <= tj) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (gs == tj) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 智育成绩递增条件
	 */
	public boolean zypmdzTj(String xn, String szlx,String jxjdm, String bfb,String qbfb) {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'dz'";
		if(!Base.isNull(jxjdm)){
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao.getMap(sql,
				new String[] { xn, szlx }, new String[] { "tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tjlx = map.get("tjlx");
		String tjz = map.get("tjz");

		Float tj = Float.parseFloat(tjz);
		bfb = StringUtils.isNull(bfb)?"0":bfb;
		qbfb = StringUtils.isNull(qbfb)?"0":qbfb;
		Float gs =  Float.parseFloat(qbfb)-Float.parseFloat(bfb);

		if (gs < 0) {
			return false;
		}
		if (">".equalsIgnoreCase(tjlx)) {
			if (gs > tj) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (gs >= tj) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (gs < tj) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (gs <= tj) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (gs == tj) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 外语成绩条件
	 */
	public boolean wycjTj(String xn, String szlx,String jxjdm, String wycj) {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'wycj'";
		if(!Base.isNull(jxjdm)){
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao.getMap(sql,
				new String[] { xn, szlx }, new String[] { "tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tjlx = map.get("tjlx");
		String tjz = map.get("tjz");

		Float tj = Float.parseFloat(tjz);
		wycj = StringUtils.isNull(wycj)?"0.0":wycj;
		Float cj =  Float.parseFloat(wycj);

		if (">".equalsIgnoreCase(tjlx)) {
			if (cj > tj) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (cj >= tj) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (cj < tj) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (cj <= tj) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (cj == tj) {
				return true;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 是否优秀班级条件
	 */
	public boolean sfyxbjTj(String xn, String szlx,String jxjdm, String isorno) {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'sfyxbj'";
		if(!Base.isNull(jxjdm)){
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao.getMap(sql,
				new String[] { xn, szlx }, new String[] { "tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tjz = map.get("tjz");

		if(isorno.equalsIgnoreCase(tjz)){
			return true;
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 获得该年度德育排名百分比
	 */
	public String getDybfb(String xn, String xh, String cpzdm)
			throws SQLException {

		cpzdm = Base.isNull(cpzdm) ? "%" : cpzdm;

		String sql = "select dypm from view_zjlg_zhszcpf"
				+ " where xn = ? and cpzdm like ? and xh = ?";
		String dypm = dao.getOneRs(sql, new String[] { xn, cpzdm, xh }, "dypm");

		sql = "select count(*) zyrs from view_zjlg_zhszcpf a where xn=? and cpzdm=?";
		String zyrs = dao.getOneRs(sql, new String[] { xn, cpzdm }, "zyrs");
		String bfb = "";

		if (!Base.isNull(dypm) && !Base.isNull(zyrs)) {
			bfb = String.valueOf(Float.parseFloat(dypm)
					/ Float.parseFloat(zyrs) * 100);
		}

		return bfb;
	}

	/**
	 * @author luo
	 * @describe 获得该年度智育排名百分比
	 */
	public String getZybfb(String xn, String xh, String cpzdm)
			throws SQLException {

		cpzdm = Base.isNull(cpzdm) ? "%" : cpzdm;

		String sql = "select * from (select t.*,(dense_rank() over(partition by xn, cpzdm order by zycpf desc nulls last)) zypm"
				+ " from (select xh, trim(zycpf) zycpf,cpzdm,xn from view_zjlg_zhszcpf where xn = ? and cpzdm like ?"
				+ " order by zycpf desc) t) where xh = ?";
		String zypm = dao.getOneRs(sql, new String[] { xn, cpzdm, xh }, "zypm");

		sql = "select count(*) zyrs from (select t.*, rownum zypm from (select xh, zycpf from view_zjlg_zhszcpf"
				+ " where xn = ? and cpzdm like ? order by zycpf desc) t) ";
		String zyrs = dao.getOneRs(sql, new String[] { xn, cpzdm }, "zyrs");
		String bfb = "0";
		if (StringUtils.isNotNull(zypm) && StringUtils.isNotNull(zyrs)) {
			bfb = String.valueOf(Float.parseFloat(zypm)/ Float.parseFloat(zyrs) * 100);			
		}
		if(bfb.length()>5){
			bfb = bfb.substring(0,5);
		}
		return bfb;
	}
	/**
	 * @author luo
	 * @describe 是否存在智育递增条件
	 */
	public boolean iszydztj(String xn, String jxjdm)
			throws SQLException {
		boolean bool = true;
		String sql = "select * from ZJLG_PJPY_TJSZ where szlx='jxj' and tjzd ='dz' and xn=? and jxjdm=?";
		HashMap<String, String> map = dao.getMap(sql, new String[]{xn,jxjdm}, new String[]{"tjzd"});
		if(map==null || map.size()<1){
			bool = false;
		}
		return bool;
	}
	/**
	 * @author luo
	 * @describe 获得该年度综合排名百分比
	 */
	public String getZhbfb(String xn, String xh, String cpzdm)
			throws SQLException {

		cpzdm = Base.isNull(cpzdm) ? "%" : cpzdm;

		String sql = "select * from (select xh, zhcpf, (dense_rank() over(partition by"
				+ " xn,cpzdm order by zhcpf desc nulls last)) pm from view_zjlg_zhszcpf"
				+ " where xn = ? and cpzdm like ? order by zhcpf desc) where xh = ?";
		String pm = dao
				.getOneRs(sql, new String[] { xn, cpzdm, xh }, "pm");

		sql = "select count(*) zyrs from (select t.*, rownum pm from (select xh, zhcpf from view_zjlg_zhszcpf"
				+ " where xn = ? and cpzdm like ? order by zhcpf desc) t) ";
		String zyrs = dao
				.getOneRs(sql, new String[] { xn, cpzdm }, "zyrs");
		String bfb = "";
		if (!Base.isNull(pm) && !Base.isNull(zyrs)) {
			bfb = String.valueOf(Float.parseFloat(pm) / Float.parseFloat(zyrs)
					* 100);
		}

		return bfb;
	}
	
	/**
	 * @author luo
	 * @describe 获得班级考勤分
	 */
	public List<HashMap<String, String>> getKqfList(ZjlgPjpyModel model,
			ZjlgPjpyForm myForm, String zgh, String[] colList, String userType)
			throws SQLException {

		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 班主任
		String bzr = "teacher".equalsIgnoreCase(userType) ? " and exists (select 1 from fdybjb b where zgh like '"
				+ zgh + "' and a.bjdm = b.bjdm)"
				: "";
		
		StringBuffer query = new StringBuffer();
		
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and a.nj='" + nj
				+ "'");
		query.append("".equalsIgnoreCase(xn) ? " and 1=1" : " and a.xn='" + xn
				+ "'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and a.xydm='"
				+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and a.zydm='"
				+ zydm + "'");
		query.append("".equalsIgnoreCase(bjdm) ? " and 1=1" : " and a.bjdm='"
				+ bjdm + "'");
		query.append(" and a.xh like ?");
		query.append(" and a.xm like ?");
		
		StringBuffer sql = new StringBuffer();

		sql.append("select * from (select a.*,rownum r from (select distinct (xh),xm,xb,nj,xydm,");
		sql.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,kqf from (select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql.append(" zymc,bjdm,bjmc,xn,dqxn,kqf from view_zjlg_dykqf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql.append(" zymc,bjdm,bjmc,'' xn,'"+Base.currXn+"' dqxn,");
		sql.append(" ''kqf from view_xsjbxx b where not exists(select 1 from zjlg_dykqf a where ");
		sql.append(" a.xh||a.xn = b.xh||'"+Base.currXn+"')) order by bjdm,xh,xn) a "+query+" "+bzr+" order by a.bjdm)");
		sql.append(" where r > "+myForm.getPages().getStart()+ " and r <= ");
		sql.append((myForm.getPages().getStart() + myForm.getPages().getPageSize()));

		List<HashMap<String, String>> rsList = dao.getArrayList(sql.toString(),
				new String[] { xh, xm }, colList);
		//System.out.println(sql);
		StringBuffer sql2 = new StringBuffer();
		
		sql2.append("select count(*) count from (select distinct (xh),xm,xb,nj,xydm,");
		sql2.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,kqf from (select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql2.append(" zymc,bjdm,bjmc,xn,dqxn,kqf from view_zjlg_dykqf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql2.append(" zymc,bjdm,bjmc,'' xn,'"+Base.currXn+"' dqxn,");
		sql2.append(" ''kqf from view_xsjbxx b where not exists(select 1 from zjlg_dykqf a where ");
		sql2.append(" a.xh||a.xn = b.xh||'"+Base.currXn+"')) order by bjdm,xh,xn) a "+query+" "+bzr+" order by a.bjdm");
		
		

		myForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql2.toString(), new String[] { xh, xm },
						"count")));

		return rsList;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存考勤分
	 */
	public boolean saveKqf(ZjlgPjpyModel model) throws Exception {

		String sql = "";
		StringBuffer inssb = new StringBuffer();
		StringBuffer delsb = new StringBuffer();
		boolean flg = false;
		// 测评分
		String cpf = DealString.toGBK(model.getCpf());
		if (cpf != null && cpf.length() > 0) {
			// 单个学生信息
			String[] xs = cpf.split("!!@@!!");
			for (int i = 0; i < xs.length; i++) {
				String[] arrXx = xs[i].split("!@!");
				String xh = arrXx[0];
				String xn = arrXx[1];
				String kqf = arrXx[2];
				sql = "delete zjlg_dykqf a where a.xn = '" + xn
				+ "' and xh = '" + xh + "'";
				delsb.append(sql);
				delsb.append("!!#!!");

				sql = "insert into zjlg_dykqf(xh, xn, kqf) values('" + xh
				+ "','" + xn + "','" + kqf + "')";
				inssb.append(sql);
				inssb.append("!!#!!");

			}
		}

		String[] delsql = delsb.toString().split("!!#!!");
		dao.runBatch(delsql);

		String[] inssql = inssb.toString().split("!!#!!");

		int[] res = dao.runBatch(inssql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存平时分比例设置
	 */
	public boolean saveBlSz(String xn, String zgh, ZjlgPjpyModel model,
			HttpServletRequest request) throws Exception {

		String isBl = model.getIsBl();
		String zpfbl = model.getZpfbl();
		String bjfbl = model.getBjfbl();

		boolean flg = false;
		if (isBl != null) {
			String sql = "delete zjlg_dypsf_sz a where a.xn = ? and bzrzgh=?";
			flg = dao.runUpdate(sql, new String[] { xn, zgh });
			if (flg) {
				flg = StandardOperation.insert("zjlg_dypsf_sz", new String[] {
						"xn", "bzrzgh", "isBl", "zpfbl", "bjfbl", },
						new String[] { xn, zgh, isBl, zpfbl, bjfbl }, request);
			}
		} else {
			flg = StandardOperation.update("zjlg_dypsf_sz", new String[] {
					"zpfbl", "bjfbl" }, new String[] { zpfbl, bjfbl },
					"xn||bzrzgh", xn + zgh, request);
		}
		return flg;
	}


	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存德育比例设置
	 */
	public boolean saveDyBlSz(String xn, ZjlgPjpyModel model,
			HttpServletRequest request) throws Exception {

		String psfbl = model.getPsfbl();
		String wsfbl = model.getWsfbl();
		String kqfbl = model.getKqfbl();
		boolean flg = false;

		String sql = "delete zjlg_dycpf_sz a where a.xn = ? ";
		flg = dao.runUpdate(sql, new String[] { xn });
		if (flg) {
			flg = StandardOperation.insert("zjlg_dycpf_sz", new String[] {
					"xn", "psfbl", "wsfbl", "kqfbl" }, new String[] { xn,
					psfbl, wsfbl, kqfbl }, request);
		}

		return flg;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 获取平时分比例设置
	 */
	public HashMap<String, String> getBlSz(String xn, String zgh)
	throws Exception {
		HashMap<String, String> map = dao.getMap(
				"select * from zjlg_dypsf_sz where xn = ? and bzrzgh = ?",
				new String[] { xn, zgh }, new String[] { "isBl", "zpfbl",
				"bjfbl" });
		return map;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 获取德育分比例设置
	 */
	public HashMap<String, String> getDyBlSz(String xn) throws Exception {
		HashMap<String, String> map = dao
		.getMap("select * from zjlg_dycpf_sz where xn = ? ",
				new String[] { xn }, new String[] { "psfbl", "wsfbl",
		"kqfbl" });
		return map;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 获取综合素质比例设置
	 */
	public HashMap<String, String> getZhBlSz(String xn) throws Exception {
		HashMap<String, String> map = dao.getMap(
				"select * from zjlg_zhszcp_sz where xn = ? ",
				new String[] { xn }, new String[] { "dybl", "zybl", });
		return map;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存综合素质比例设置
	 */
	public boolean saveZhBlSz(String xn, ZjlgPjpyModel model,
			HttpServletRequest request) throws Exception {

		String dybl = model.getDybl();
		String zybl = model.getZybl();

		boolean flg = false;

		String sql = "delete zjlg_zhszcp_sz a where a.xn = ? ";
		flg = dao.runUpdate(sql, new String[] { xn });
		if (flg) {
			flg = StandardOperation.insert("zjlg_zhszcp_sz", new String[] {
					"xn", "dybl", "zybl", }, new String[] { xn, dybl, zybl },
					request);
		}


		return flg;
	}
	/**
	 * @author luo
	 * @describe 获得班级平时分
	 */
	public List<HashMap<String, String>> getPsfList(ZjlgPjpyModel model,
			ZjlgPjpyForm myForm, String zgh, String[] colList,String userType)
			throws SQLException {

		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 班主任
		String bzr = "teacher".equalsIgnoreCase(userType) ? " and exists (select 1 from fdybjb b where zgh like '"
				+ zgh + "' and a.bjdm = b.bjdm)"
				: "";
		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and a.nj='" + nj
				+ "'");
		query.append("".equalsIgnoreCase(xn) ? " and 1=1" : " and a.xn='" + xn
				+ "'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and a.xydm='"
				+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and a.zydm='"
				+ zydm + "'");
		query.append("".equalsIgnoreCase(bjdm) ? " and 1=1" : " and a.bjdm='"
				+ bjdm + "'");
		query.append(" and a.xh like ?");
		query.append(" and a.xm like ?");
		
		StringBuffer sql = new StringBuffer();

		sql.append("select * from (select a.*,rownum r from (select distinct (xh),xm,xb,nj,xydm,");
		sql.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,zwpyf,bjpyf,xyfjf,xysh  from (select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql.append(" zymc,bjdm,bjmc,xn,dqxn,zwpyf,bjpyf,xyfjf,xysh from view_zjlg_dypsf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql.append(" zymc,bjdm,bjmc,'' xn,'"+Base.currXn+"' dqxn,");
		sql.append(" ''zwpyf,''bjpyf,''xyfjf,''xysh from view_xsjbxx b where not exists(select 1 from zjlg_dypsf a where ");
		sql.append(" a.xh||a.xn = b.xh||'"+Base.currXn+"')) order by bjdm,xh,xn) a "+query+" "+bzr+" order by a.bjdm)");
		sql.append(" where r > "+myForm.getPages().getStart()+ " and r <= ");
		sql.append((myForm.getPages().getStart() + myForm.getPages().getPageSize()));

		List<HashMap<String, String>> rsList = dao.getArrayList(sql.toString(),
				new String[] { xh, xm }, colList);
		//System.out.println(sql);
		StringBuffer sql2 = new StringBuffer();
		
		sql2.append("select count(*) count from (select distinct (xh),xm,xb,nj,xydm,");
		sql2.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,zwpyf,bjpyf,xyfjf,xysh  from (select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql2.append(" zymc,bjdm,bjmc,xn,dqxn,zwpyf,bjpyf,xyfjf,xysh from view_zjlg_dypsf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql2.append(" zymc,bjdm,bjmc,'' xn,'"+Base.currXn+"' dqxn,");
		sql2.append(" ''zwpyf,''bjpyf,''xyfjf,''xysh from view_xsjbxx b where not exists(select 1 from zjlg_dypsf a where ");
		sql2.append(" a.xh||a.xn = b.xh||'"+Base.currXn+"')) order by bjdm,xh,xn) a "+query+" "+bzr+" order by a.bjdm");

		myForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql2.toString(), new String[] { xh, xm },
						"count")));

		return rsList;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存平时分
	 */
	public boolean savePsf(ZjlgPjpyModel model,String userType) throws Exception {

		String sql = "";
		StringBuffer inssb = new StringBuffer();
		StringBuffer delsb = new StringBuffer();
		boolean flg = false;
		// 测评分
		String cpf = DealString.toGBK(model.getCpf());
		if (cpf != null && cpf.length() > 0) {
			// 单个学生信息
			String[] xs = cpf.split("!!@@!!");
			for (int i = 0; i < xs.length; i++) {
				String[] arrXx = xs[i].split("!@!");
				String xh = arrXx[0];
				String xn = arrXx[1];
				String zwpyf = arrXx[2];
				String bjpyf = arrXx[3];
				String xyfjf = arrXx[4];
				xyfjf = "noPoint".equalsIgnoreCase(xyfjf) ? "" : xyfjf;
				String xysh = "teacher".equalsIgnoreCase(userType) ? "未审核"
						: arrXx[5];
				if (!Base.isNull(xysh)) {
					xysh = "1".equalsIgnoreCase(xysh) ? "已通过" : "未通过";
				}

				sql = "delete zjlg_dypsf a where a.xn = '" + xn
						+ "' and xh = '" + xh + "'";
				delsb.append(sql);
				delsb.append("!!#!!");

				sql = "insert into zjlg_dypsf(xh, xn, zwpyf, bjpyf, xyfjf,xysh) values('"
						+ xh
						+ "','"
						+ xn
						+ "','"
						+ zwpyf
						+ "','"
						+ bjpyf
						+ "','" + xyfjf + "','" + xysh + "')";
				inssb.append(sql);
				inssb.append("!!#!!");
			}
		}

		String[] delsql = delsb.toString().split("!!#!!");
		dao.runBatch(delsql);

		String[] inssql = inssb.toString().split("!!#!!");

		int[] res = dao.runBatch(inssql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存平时分(学院附加分)
	 */
	public boolean savePsfXyfjf(ZjlgPjpyModel model) throws Exception {
		StringBuffer sb = new StringBuffer();
		String sql = "";
		// 测评分
		String cpf = DealString.toGBK(model.getCpf());
		boolean flg = false;
		if (cpf != null && cpf.length() > 0) {
			// 单个学生信息
			String[] xs = cpf.split("!!@@!!");
			for (int i = 0; i < xs.length; i++) {
				String[] arrXx = xs[i].split("!@!");
				String xh = arrXx[0];
				String xn = arrXx[1];
				String xyfjf = arrXx[2];
				xyfjf = "noPoint".equalsIgnoreCase(xyfjf) ? "" : xyfjf;
				String xysh = arrXx[3];
				xysh = "1".equalsIgnoreCase(xysh) ? "已通过" : "未通过";

				sql = "update zjlg_dypsf set xyfjf = '" + xyfjf + "',xysh = '"
				+ xysh + "' where xh='" + xh + "' and xn = '" + xn
				+ "'";
				sb.append(sql);
				sb.append("!!#!!");

			}
		}
		String[] pksql = sb.toString().split("!!#!!");
		int[] res = dao.runBatch(pksql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}

	/**
	 * 奖学金列表
	 * @param jxjlbdm 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlbdm) throws Exception {
		String query = "";
		if(jxjlbdm!=null&&!jxjlbdm.equalsIgnoreCase("")){
			query = " where jxjlb = '"+jxjlbdm+"'";
		}
		return dao.getList("select jxjdm,jxjmc from jxjdmb"+query, new String[]{}, new String[]{"jxjdm", "jxjmc"});
	}
	/**
	 * 获得体育成绩 
	 * @param jxjlbdm 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> gettycj(String xh,String xn) throws Exception {
		return dao.getMap("select (round(avg(cj), 2)) tycj from cjb a where xh=? and xn=? and exists (select 1 from tykjxrwb b where a.xkkh=b.xkkh and rownum<2)", 
				new String[]{xh,xn}, new String[]{"tycj"});
	}
	/**
	 * @author luning
	 * @param myForm 
	 * @throws Exception
	 * @describe 奖学金评奖名额列表
	 */
	public ArrayList<String[]> getJxjRsList(ZjlgPjpyModel myModel, ZjlgPjpyForm myForm) throws Exception {
		String bmlb = DealString.toGBK(myModel.getBmlb());
		String[] colList = null; 
		StringBuffer query = ZjlgPjpyUnit.makeQuery(new String[]{"bmlb","jxjdm"}, myModel);
		if(myModel.getXn() != null && !("".equalsIgnoreCase(myModel.getXn().trim()))){
			query.append(" and a.xn = '"); 
			query.append(myModel.getXn()); 
			query.append("'"); 
		}
		String xydm = myModel.getXydm();
		String cpzdm = myModel.getCpzdm();
		String bjdm = myModel.getBjdm();
		String sql ="";
		if(bmlb.equalsIgnoreCase("xydm")){
			if(myModel.getXydm() != null && !("".equalsIgnoreCase(myModel.getXydm().trim()))){
				query.append(" and bmdm = '"); 
				query.append(myModel.getXydm()); 
				query.append("'"); 
			}
			myForm.getPages().setMaxRecord(
					Integer.parseInt(dao.getOneRs("select count(*) count from zjlg_xyjxjrs a"+query.toString(), new String[] {}, "count")));
			query.append(" ) where r > "); 
			query.append(myForm.getPages().getStart());
			query.append(" and r <= ");
			query.append((myForm.getPages().getStart() + myForm.getPages().getPageSize()));
			colList = new String[]{"pk","xymc","cprs","jxjmc","jxjbl","jxjrs"}; 
			sql = "select * from (select bmdm||bmlb||jxjdm||a.xn pk,(select bmmc from zxbz_xxbmdm where a.bmdm = bmdm) xymc,cprs,(select jxjmc from jxjdmb where jxjdm = a.jxjdm) jxjmc,jxjbl,jxjrs,rownum r from zjlg_xyjxjrs a "; 
		}else if ((bmlb.equalsIgnoreCase("bjdm"))){
			if (isNull(bjdm) && isNull(cpzdm) && !isNull(xydm)) {
				query.append(" and bmlb='bjdm' and exists (select 1  from view_njxyzybj where bjdm = a.bmdm and xydm='");
				query.append(xydm);
				query.append("')"); 
			} else if (isNull(bjdm) && !isNull(cpzdm)) {
				query.append(" and bmlb='bjdm' and exists (select 1 from zjlg_cpzfpb where bjdm = a.bmdm and cpzdm='");
				query.append(cpzdm);
				query.append("' and xn = '"); 
				query.append(myModel.getXn());
				query.append("')"); 
			} else if (!isNull(bjdm)) {
				query.append(" and bmdm='"); 
				query.append(bjdm);
				query.append("'");
			}
			myForm.getPages().setMaxRecord(
					Integer.parseInt(dao.getOneRs("select count(*) count from zjlg_xyjxjrs a"+query.toString(), new String[] {}, "count")));
			query.append(" ) where r > "); 
			query.append(myForm.getPages().getStart());
			query.append(" and r <= ");
			query.append((myForm.getPages().getStart() + myForm.getPages().getPageSize()));
			colList = new String[]{"pk","bjmc","cprs","jxjmc","jxjbl","jxjrs","cpzmc","cpzjxjrs"}; 
			sql = "select * from (select bmdm||bmlb||jxjdm||a.xn pk,(select bjmc from bks_bjdm where a.bmdm = bjdm) bjmc,cprs,(select jxjmc from jxjdmb where jxjdm = a.jxjdm) jxjmc,jxjbl,jxjrs,(select cpzmc from zjlg_cpzszb where b.cpzdm = cpzdm) cpzmc,(select jxjrs from zjlg_xyjxjrs where bmlb = 'cpzdm' and bmdm = b.cpzdm and xn = a.xn and a.jxjdm = jxjdm) cpzjxjrs,rownum r from zjlg_xyjxjrs a left join zjlg_cpzfpb b on a.bmdm = b.bjdm and a.xn = b.xn"; 
		}else if ((bmlb.equalsIgnoreCase("cpzdm"))){
			if(myModel.getCpzdm() != null && !("".equalsIgnoreCase(myModel.getCpzdm().trim()))){
				query.append(" and bmdm = '"); 
				query.append(myModel.getCpzdm()); 
				query.append("'"); 
			}
			if(myModel.getXydm() != null && !("".equalsIgnoreCase(myModel.getXydm().trim()))){
				query.append(" and xydm = '"); 
				query.append(myModel.getXydm()); 
				query.append("'"); 
			}
			myForm.getPages().setMaxRecord(
					Integer.parseInt(dao.getOneRs("select count(*) count from zjlg_cpzszb b left join zjlg_xyjxjrs a on a.bmdm = b.cpzdm and a.xn = b.xn"+query.toString(), new String[] {}, "count")));
			query.append(" ) where r > "); 
			query.append(myForm.getPages().getStart());
			query.append(" and r <= ");
			query.append((myForm.getPages().getStart() + myForm.getPages().getPageSize()));
			colList = new String[]{"pk","cpzmc","xymc","jxjmc","cprs","jxjbl","jxjrs"}; 
			sql = "select * from (select bmdm||bmlb||jxjdm||a.xn pk,b.cpzmc,(select bmmc from zxbz_xxbmdm where b.xydm = bmdm) xymc,(select jxjmc from jxjdmb where jxjdm = a.jxjdm) jxjmc,cprs,jxjbl,jxjrs,rownum r from zjlg_cpzszb b left join zjlg_xyjxjrs a on a.bmdm = b.cpzdm and a.xn = b.xn";
		}
		ArrayList<String[]> rs = dao.rsToVator(sql+query.toString(), new String []{}, colList);
		return rs;
	}

	/**
	 * @author luning
	 * @param myForm 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @describe 荣誉称号名额列表
	 */
	public ArrayList<String[]> getRychRsList(ZjlgPjpyModel myModel, ZjlgPjpyForm myForm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String bmlb = DealString.toGBK(myModel.getBmlb());
		String[] colList = new String[]{"pk","bmmc","rychmc","cprs","rychbl","rychrs"}; 
		StringBuffer query = ZjlgPjpyUnit.makeQuery(new String[]{"bmlb","rychdm","xn"}, myModel);
		String xydm = myModel.getXydm();
		String zydm = myModel.getZydm();
		String bjdm = myModel.getBjdm();
		String sql ="";
		if(bmlb.equalsIgnoreCase("xydm")){
			if(myModel.getXydm() != null && !("".equalsIgnoreCase(myModel.getXydm().trim()))){
				query.append(" and bmdm = '"); 
				query.append(xydm); 
				query.append("'"); 
			}
			sql ="select * from (select rychdm||bmdm||bmlb||xn pk,(select bmmc from zxbz_xxbmdm where a.bmdm=bmdm) bmmc,cprs,(select rychmc from rychdmb where a.rychdm = rychdm) rychmc,rychbl,rychrs,rownum r from  zjlg_xyrychrs a";
		}else if(bmlb.equalsIgnoreCase("zydm")){
			if (isNull(zydm) && !isNull(xydm)) {
				query.append(" and exists (select 1  from view_njxyzybj where zydm = a.bmdm and xydm='");
				query.append(xydm);
				query.append("')"); 
			} else if(!isNull(zydm)){
				query.append(" and bmdm = '"); 
				query.append(zydm); 
				query.append("'"); 
			}
			sql ="select * from (select rychdm||bmdm||bmlb||xn pk,(select zymc from bks_zydm where a.bmdm=zydm) bmmc,cprs,(select rychmc from rychdmb where a.rychdm = rychdm) rychmc,rychbl,rychrs,rownum r from  zjlg_xyrychrs a";
		}else if ((bmlb.equalsIgnoreCase("bjdm"))){
			if (isNull(bjdm) && isNull(zydm) && !isNull(xydm)) {
				query.append(" and exists (select 1  from view_njxyzybj where bjdm = a.bmdm and xydm='");
				query.append(xydm);
				query.append("')"); 
			} else if (isNull(bjdm) && !isNull(zydm)) {
				query.append(" and exists (select 1  from view_njxyzybj where bjdm = a.bmdm and zydm='");
				query.append(zydm);
				query.append("')"); 
			} else if (!isNull(bjdm)) {
				query.append(" and bmdm='"); 
				query.append(bjdm);
				query.append("'");
			}
			sql ="select * from (select rychdm||bmdm||bmlb||xn pk,(select bjmc from bks_bjdm where a.bmdm=bjdm) bmmc,cprs,(select rychmc from rychdmb where a.rychdm = rychdm) rychmc,rychbl,rychrs,rownum r from  zjlg_xyrychrs a";
		}
		myForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs("select count(*) count from zjlg_xyrychrs a "+query.toString(), new String[] {}, "count")));

		query.append(" ) where r > "); 
		query.append(myForm.getPages().getStart());
		query.append(" and r <= ");
		query.append((myForm.getPages().getStart() + myForm.getPages().getPageSize()));
		ArrayList<String[]> rs = dao.rsToVator(sql+query.toString(), new String []{}, colList);
		return rs;
	}

	/**
	 * @author luning
	 * @param myForm 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @describe 先进班级名额列表
	 */
	public ArrayList<String[]> getXjbjRsList(ZjlgPjpyModel myModel, ZjlgPjpyForm myForm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		StringBuffer query = ZjlgPjpyUnit.makeQuery(new String[]{"xn","xydm"}, myModel);
		myForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs("select count(*) count from ZJLG_XJBJMEB a "+query.toString(), new String[] {}, "count")));
		query.append(" ) where r > "); 
		query.append(myForm.getPages().getStart());
		query.append(" and r <= ");
		query.append((myForm.getPages().getStart() + myForm.getPages().getPageSize()));
		String[] colList = new String[]{"pk","xn","xymc","bjgs","szbl","me"};
		String sql = "select * from (select xydm||xn pk,xn,(select bmmc from zxbz_xxbmdm where a.xydm = bmdm) xymc,bjgs,szbl,me,rownum r  from ZJLG_XJBJMEB a";
		return dao.rsToVator(sql+query.toString(), new String[]{}, colList);
	}

	/**
	 * @author luning
	 * @describe 参评组列表
	 */
	public List<HashMap<String, String>> getCpzList(String xn, String xydm) {
		String query = "";
		if(xydm!=null&&!xydm.equalsIgnoreCase("")){
			query = " and xydm = '"+xydm +"'";
		}else{
			query = " and 1=0";
		}
		return dao.getList("select cpzdm,cpzmc cpzmc,cpzdm||'/'||cpzmc xscpz from zjlg_cpzszb where xn = ? "+query, new String[]{xn}, new String[]{"cpzdm","cpzmc","xscpz"});
	}

	/**
	 * @author luning
	 * @describe 根据参评组班级列表
	 */
	public List<HashMap<String, String>> getCpzBjList(String xn, String xydm, String cpzdm) {
		return dao.getList("select bjdm,(select bjmc from bks_bjdm where a.bjdm =bjdm) bjmc from zjlg_cpzfpb a where xn = ? and xydm = ? and cpzdm = ?", new String[]{xn,xydm,cpzdm}, new String[]{"bjdm","bjmc"});
	}

	public List<HashMap<String, String>> getWfpBjList(String xydm, String zydm, String nj, String xn) {
		// TODO 自动生成方法存根
		String query = SearchUtils.makeQueryCondition(xydm, zydm, "", "", "", "", nj, "", "", "");
		String myQuery = " and  not exists (select 1 from zjlg_cpzfpb where xn = ? and bjdm = a.bjdm)";
		String sql = "select bjdm,bjmc from view_njxyzybj a";
		return dao.getList(sql+query+myQuery, new String[] {xn}, new String[] { "bjdm", "bjmc" });
	}
	/**
	 * @author luning
	 * @describe 根据参评组班级列表
	 */
	public boolean saveCpzFp(String xn, String cpzdm, String bjdms,String xydm, HttpServletRequest request) throws Exception {
		String[] bjdmZ = bjdms.split("!!");
		boolean del = StandardOperation.delete("zjlg_cpzfpb", "cpzdm||xn||xydm", cpzdm+xn+xydm, request);
		if(del){
			String[] sqlArr = new String [bjdmZ.length];  
			for(int i = 0;i<sqlArr.length;i++){
				sqlArr[i] ="insert into zjlg_cpzfpb (cpzdm,xn,bjdm,xydm) values ('"+cpzdm+"','"+xn+"','"+bjdmZ[i]+"','"+xydm+"')" ;
			}
			int[] res = dao.runBatch(sqlArr);
			boolean inserted = true;
			for(int i=0;i<res.length;i++){
				inserted = (res[i]==Statement.EXECUTE_FAILED)?false:true;
				if(!inserted) break;
			}
			if(inserted){
				inserted = StandardOperation.delete("ZJLG_XYJXJRS", "bmlb||bmdm||xn", "cpzdm"+cpzdm+xn, request);
				String sql = "insert into ZJLG_XYJXJRS(xn,bmdm,jxjdm,cprs,bmlb) select '"+xn+"',b.cpzdm,a.jxjdm,count(*) num,'cpzdm' from (select b.*,cpzdm from view_xsjbxx b,zjlg_cpzfpb a where a.xn ='"+xn+"' and a.bjdm = b.bjdm and a.cpzdm = '"+cpzdm+"') b,jxjdmb a group by b.cpzdm,a.jxjdm";
				if(inserted){
					inserted = dao.runUpdate(sql,new String[]{} );
				}
			}
			return inserted;
		}else{
			return false;
		}
	}

	public boolean jxjcsh() throws Exception {
		String sql = "{call initBaseZjlgData()}";
		return dao.runProcuder(sql,new String[]{});
	}

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str.equalsIgnoreCase("all"));
	}

	public boolean plszSave(ZjlgPjpyModel myModel, String userType) throws Exception {
		//批量设置保存
		String xydm = myModel.getXydm();
		String cpzdm = myModel.getCpzdm();
		String bjdm = myModel.getBjdm();
		String jxjdm = myModel.getJxjdm();
		String szblS  = myModel.getSzbl();
		double szbl = 0.00;
		if(cpzdm!=null&&cpzdm.equalsIgnoreCase("")){
			cpzdm=null;
		}
		szbl = ((int) (Float.parseFloat(szblS) * 1000)) / 100000.00;
		String querry = "";
		if (!isNull(bjdm)) {
			querry = " and bmdm='" + bjdm + "' and bmlb='bjdm' ";
		}
		else if (!isNull(cpzdm)) {
			querry = " and bmdm='" + cpzdm + "' and bmlb='cpzdm' ";
		}else if(!isNull(xydm)){
			querry = " and bmdm='" + xydm + "' and bmlb='xydm' ";
		}else {
			querry = " and 1=1 ";
		}
		if (isNull(jxjdm)) {
			querry += " and 1=1 ";
		} else {
			querry += " and jxjdm='" + jxjdm + "' ";
		}
		String sql = "update zjlg_xyjxjrs set jxjbl='" + szbl
		+ "',jxjrs=(case when cprs*" + szbl
		+ "<1 then '0' else (case when instr(cprs*" + szbl
		+ ",'.')>0 then substr(cprs*" + szbl + ",1,instr(cprs*" + szbl
		+ ",'.')-1) else to_char(cprs*" + szbl
		+ ") end) end) where xn=?" + querry;
		boolean update = true;
		if(userType.equalsIgnoreCase("xy")&&isNull(bjdm)&&isNull(cpzdm)){
			
		}else{
			update = dao.runUpdate(sql, new String[]{Base.getJxjsqxn()});
		}
		if(update){
			String squerry = " and bmlb='cpzdm' and bmdm in (select cpzdm from zjlg_cpzfpb where xn = ? and xydm='"+xydm+"' and '"+cpzdm+"' = 'null')";
			if (isNull(jxjdm)) {
				squerry += " and 1=1 ";
			} else {
				squerry += " and jxjdm='" + jxjdm + "' ";
			}
			String ssql = "update zjlg_xyjxjrs set jxjbl='" + szbl
			+ "',jxjrs=(case when to_number(cprs)*" + szbl
			+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+szbl+")) end) where xn=? " + squerry;
			update = dao.runUpdate(ssql, new String[]{Base.getJxjsqxn(),Base.getJxjsqxn()});
		}
		if(update){
			String squerry = " and bmlb='bjdm' and bmdm in (select distinct bjdm from view_njxyzybj where xydm='"+xydm+"')";
			if (isNull(jxjdm)) {
				squerry += " and 1=1 ";
			} else {
				squerry += " and jxjdm='" + jxjdm + "' ";
			}
			String ssql = "update zjlg_xyjxjrs set jxjbl='" + szbl
			+ "',jxjrs=(case when to_number(cprs)*" + szbl
			+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+szbl+")) end) where xn=? " + squerry;
			update = dao.runUpdate(ssql, new String[]{Base.getJxjsqxn()});
		}
		return update;
	}

	public boolean saveJxjRs(String pk, ZjlgPjpyModel myModel, HttpServletRequest request) throws Exception {
		// 修改奖学金人数
		String jxjrs = myModel.getJxjrs();
		boolean update = StandardOperation.update("zjlg_xyjxjrs", new String[]{"jxjrs"}, new String[]{jxjrs}, "bmdm||bmlb||jxjdm||xn", pk, request);
		return update;
	}

	public boolean saveJxjXn(String jxjxn, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		boolean update = StandardOperation.update("xtszb", new String[]{"jxjsqxn"}, new String[]{jxjxn}, "1", "1", request);
		return update;
	}


	/**
	 * @author luo
	 * @describe 获得班级卫生分
	 */
	public List<HashMap<String, String>> getWsfList(ZjlgPjpyModel model,
			ZjlgPjpyForm myForm, String zgh, String[] colList, String userType)
			throws SQLException {

		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 班主任
		String bzr = "teacher".equalsIgnoreCase(userType) ? " and exists (select 1 from fdybjb b where zgh like '"
				+ zgh + "' and a.bjdm = b.bjdm)"
				: "";
		
		StringBuffer query = new StringBuffer();
		
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and a.nj='" + nj
				+ "'");
		query.append("".equalsIgnoreCase(xn) ? " and 1=1" : " and a.xn='" + xn
				+ "'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and a.xydm='"
				+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and a.zydm='"
				+ zydm + "'");
		query.append("".equalsIgnoreCase(bjdm) ? " and 1=1" : " and a.bjdm='"
				+ bjdm + "'");
		query.append(" and a.xh like ?");
		query.append(" and a.xm like ?");
		
		StringBuffer sql = new StringBuffer();

		sql.append("select * from (select a.*,rownum r from (select distinct (xh),xm,xb,nj,xydm,");
		sql.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,qsf,xyfjf,xysh,lrsj,isZds from (select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql.append(" zymc,bjdm,bjmc,xn,dqxn,qsf,xyfjf,xysh,lrsj,isZds from view_zjlg_dywsf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql.append(" zymc,bjdm,bjmc,'' xn,'"+Base.currXn+"' dqxn,");
		sql.append(" ''qsf,''xyfjf,''xysh,''lrsj,''isZds from view_xsjbxx b where not exists(select 1 from zjlg_dywsf a where ");
		sql.append(" a.xh||a.xn = b.xh||'"+Base.currXn+"')) order by bjdm,xh,xn) a "+query+" "+bzr+" order by a.bjdm)");
		sql.append(" where r > "+myForm.getPages().getStart()+ " and r <= ");
		sql.append((myForm.getPages().getStart() + myForm.getPages().getPageSize()));

		List<HashMap<String, String>> rsList = dao.getArrayList(sql.toString(),
				new String[] { xh, xm }, colList);
		//System.out.println(sql);
		StringBuffer sql2 = new StringBuffer();
		
		sql2.append("select count(*) count from (select distinct (xh),xm,xb,nj,xydm,");
		sql2.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,qsf,xyfjf,xysh,lrsj,isZds from (select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql2.append(" zymc,bjdm,bjmc,xn,dqxn,qsf,xyfjf,xysh,lrsj,isZds from view_zjlg_dywsf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql2.append(" zymc,bjdm,bjmc,'' xn,'"+Base.currXn+"' dqxn,");
		sql2.append(" ''qsf,''xyfjf,''xysh,''lrsj,''isZds from view_xsjbxx b where not exists(select 1 from zjlg_dywsf a where ");
		sql2.append(" a.xh||a.xn = b.xh||'"+Base.currXn+"')) order by bjdm,xh,xn) a "+query+" "+bzr+" order by a.bjdm");

		myForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql2.toString(), new String[] { xh, xm },
						"count")));

		return rsList;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存卫生分
	 */
	public boolean saveWsf(ZjlgPjpyModel model,String userType) throws Exception {

		String sql = "";
		StringBuffer inssb = new StringBuffer();
		StringBuffer delsb = new StringBuffer();
		boolean flg = false;
		// 测评分
		String cpf = DealString.toGBK(model.getCpf());
		if (cpf != null && cpf.length() > 0) {
			// 单个学生信息
			String[] xs = cpf.split("!!@@!!");
			for (int i = 0; i < xs.length; i++) {
				String[] arrXx = xs[i].split("!@!");
				String xh = arrXx[0];
				String xn = arrXx[1];
				String qsf = arrXx[2];
				String isZds = arrXx[3];
				String xyfjf = arrXx[4];
				xyfjf = "noPoint".equalsIgnoreCase(xyfjf) ? "" : xyfjf;
				String xysh = "teacher".equalsIgnoreCase(userType) ? "未审核"
						: arrXx[5];
				if (!Base.isNull(xysh)) {
					xysh = "1".equalsIgnoreCase(xysh) ? "已通过" : "未通过";
				}
				
				sql = "delete zjlg_dywsf a where a.xn = '" + xn
				+ "' and xh = '" + xh + "'";
				delsb.append(sql);
				delsb.append("!!#!!");

				sql = "insert into zjlg_dywsf(xh, xn, qsf, isZds, xyfjf,xysh) values('"
						+ xh
						+ "','"
						+ xn
						+ "','"
						+ qsf
						+ "','"
						+ isZds
						+ "','"
						+ xyfjf + "','" + xysh + "')";
				inssb.append(sql);
				inssb.append("!!#!!");
			}
		}

		String[] delsql = delsb.toString().split("!!#!!");
		dao.runBatch(delsql);

		String[] inssql = inssb.toString().split("!!#!!");

		int[] res = dao.runBatch(inssql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存卫生分(学院附加分)
	 */
	public boolean saveWsfXyfjf(ZjlgPjpyModel model) throws Exception {
		StringBuffer sb = new StringBuffer();
		String sql = "";
		// 测评分
		String cpf = DealString.toGBK(model.getCpf());
		boolean flg = false;
		if (cpf != null && cpf.length() > 0) {
			// 单个学生信息
			String[] xs = cpf.split("!!@@!!");
			for (int i = 0; i < xs.length; i++) {
				String[] arrXx = xs[i].split("!@!");
				String xh = arrXx[0];
				String xn = arrXx[1];
				String xyfjf = arrXx[2];
				xyfjf = "noPoint".equalsIgnoreCase(xyfjf) ? "" : xyfjf;
				String xysh = arrXx[3];
				xysh = "1".equalsIgnoreCase(xysh) ? "已通过" : "未通过";

				sql = "update zjlg_dywsf set xyfjf = '" + xyfjf + "',xysh = '"
				+ xysh + "' where xh='" + xh + "' and xn = '" + xn
				+ "'";
				sb.append(sql);
				sb.append("!!#!!");

			}
		}
		String[] pksql = sb.toString().split("!!#!!");
		int[] res = dao.runBatch(pksql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}

	/**
	 * @author luo
	 * @describe 获得德育测评总分
	 */
	public List<HashMap<String, String>> getZfList(ZjlgPjpyModel model,
			ZjlgPjpyForm myForm, String zgh, String[] colList, String userType,
			String userDep) throws SQLException {

		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		xn = Base.isNull(xn) ? "%" : xn;
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 班主任
		zgh = "teacher".equalsIgnoreCase(userType) ? zgh : "%";
		String bzr = "teacher".equalsIgnoreCase(userType) ? " where exists (select 1 from fdybjb b where zgh like '"
				+ zgh + "' and t.bjdm = b.bjdm)"
				: " where 1=1";

		StringBuffer query = new StringBuffer();

		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and t.nj='" + nj
				+ "'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and t.xydm='"
			+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and t.zydm='"
			+ zydm + "'");
		query.append("".equalsIgnoreCase(bjdm) ? " and 1=1" : " and t.bjdm='"
			+ bjdm + "'");

		String sql = "select * from (select t.*, to_number((psf + wsf + kqf - kf) / bl) dycpf,rownum r from ("
				+ "select a.*,case when b.pjfs='5' then '20' else '1' end bl from (select t.*, "
				+ " case when to_number(t.psf) > to_number(b.psfbl) then 'red' else 'black' end psfcl,"
				+ " case when to_number(t.wsf) > to_number(b.wsfbl) then 'red' else 'black' end wsfcl,"
				+ " case when to_number(t.kqf) > to_number(b.kqfbl) then 'red' else 'black' end kqfcl"
				+ " from (select t.xh, t.xm, t.xb, t.nj, t.xydm, t.xymc, t.zydm, t.zymc,t.xn,"
				+ " t.bjdm, t.bjmc, case when pssh = '已通过' then case when isbl = 'yes' then"
				+ " (zwpyf * zpfbl + bjpyf * bjfbl) / 100 + psfjf else zwpyf + bjpyf + psfjf"
				+ " end else 0 end psf, case when wssh = '已通过' then qsf + wsfjf else 0"
				+ " end wsf, kqf,kf from (select t.* from (select a.xh, a.xm, a.xb,"
				+ " a.nj, a.xydm, a.xymc, a.zydm, a.zymc, a.bjdm, a.bjmc, nvl(b.zwpyf, 0) zwpyf,"
				+ " nvl(b.bjpyf, 0) bjpyf, nvl(b.xyfjf, 0) psfjf,b.xn,b.xysh pssh, b.isbl, b.zpfbl,"
				+ " b.bjfbl, nvl(c.qsf, 0) qsf, nvl(c.xyfjf, 0) wsfjf, c.xysh wssh, nvl(d.kqf, 0) kqf,"
				+ " nvl(f.kf,0) kf from view_xsjbxx a left join (select t.xh, e.xn,t.DQXN, t.XYSH, t.zwpyf, t.bjpyf,"
				+ " t.xyfjf, e.isbl, e.zpfbl, e.bjfbl from view_zjlg_dypsf t left join zjlg_dypsf_sz e "
				+ " on t.dqxn = e.xn and e.bzrzgh like ? and exists (select 1 from fdybjb f where f.zgh = e.bzrzgh and f.bjdm=t.bjdm"
				+ " )) b on a.xh = b.xh and b.xn like ? left join view_zjlg_dywsf c "
				+ " on a.xh = c.xh and c.xn = b.xn and c.xn like ? left join view_zjlg_dykqf d on a.xh = d.xh and d.xn = c.xn and d.xn like ?"
				+ " left join (select t.xh, t.xn,sum(b.cfkf) kf from wjcfb t, view_xsjbxx a,zjlg_wjkfb b where t.xh = a.xh and t.xxsh = '通过' and t.cflb=b.cfdm"
				+ " and t.xn = b.xn group by t.xn,t.xh) f on a.xh = f.xh and b.xn = f.xn and f.xn like ?) t "
				+ bzr
				+ " order by t.bjdm, t.xh) t where 1=1 "
				+ query.toString()
				+ " and t.xh like ? and t.xm like ?) t ,zjlg_dycpf_sz b where t.xn = b.xn) a left join ZJLG_pjfsSdb b on b.xn=a.xn and a.nj=b.nj) t ) where r > "
				+ myForm.getPages().getStart()
				+ " and r <= "
				+ (myForm.getPages().getStart() + myForm.getPages()
						.getPageSize());
		//System.out.println(sql);
		List<HashMap<String, String>> rsList = dao.getList(sql, new String[] {
				zgh, xn, xn, xn, xn, xh, xm }, colList);

		// 分页
		sql = "select count(t.xh) count from (select a.xh, a.xm, a.xb,"
				+ " a.nj, a.xydm, a.xymc, a.zydm, a.zymc, a.bjdm, a.bjmc, nvl(b.zwpyf, 0) zwpyf,"
				+ " nvl(b.bjpyf, 0) bjpyf, nvl(b.xyfjf, 0) psfjf, b.xysh pssh, b.isbl, b.zpfbl,"
				+ " b.bjfbl, nvl(c.qsf, 0) qsf, nvl(c.xyfjf, 0) wsfjf, c.xysh wssh, nvl(d.kqf, 0) kqf,"
				+ " nvl(f.kf,0) kf from view_xsjbxx a left join (select t.xh, e.xn,t.DQXN, t.XYSH, t.zwpyf, t.bjpyf,"
				+ " t.xyfjf, e.isbl, e.zpfbl, e.bjfbl from view_zjlg_dypsf t left join zjlg_dypsf_sz e "
				+ " on t.dqxn = e.xn and e.bzrzgh like ? and exists (select 1 from fdybjb f where f.zgh = e.bzrzgh "
				+ " )) b on a.xh = b.xh and b.xn like ? left join view_zjlg_dywsf c "
				+ " on a.xh = c.xh and c.xn = b.xn and c.xn like ? left join view_zjlg_dykqf d on a.xh = d.xh and d.xn = c.xn and d.xn like ?"
				+ " left join (select t.xh, t.xn,sum(t.kf) kf from wjcfb t, view_xsjbxx a where t.xh = a.xh and t.xxsh = '通过'"
				+ " group by t.xn,t.xh) f on a.xh = f.xh and d.xn = f.xn and f.xn like ?) t "
			+ bzr + query.toString() + "and t.xh like ? and t.xm like ?";
		myForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] { zgh, xn, xn,
						xn, xn, xh, xm }, "count")));

		return rsList;
	}

	/**
	 * @author luo
	 * @describe 获得综合素质测评分
	 */
	public List<HashMap<String, String>>  getZhszcpfList(ZjlgPjpyModel model,ZjlgPjpyForm myForm,
			String zgh, String[] colList,String userType, String isBzr) throws SQLException {

		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 班主任职工号
		zgh = "teacher".equalsIgnoreCase(userType) ? zgh : "%";
		//参评组代码
		String cpzdm = DealString.toGBK(model.getCpzdm());

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and a.nj='" + nj
				+ "'");
		query.append("".equalsIgnoreCase(xn) ? " and 1=1" : " and a.xn='" + xn
				+ "'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and a.xydm='"
			+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and a.zydm='"
			+ zydm + "'");
		query.append("".equalsIgnoreCase(bjdm) ? " and 1=1" : " and a.bjdm='"
			+ bjdm + "'");
		query.append("".equalsIgnoreCase(cpzdm) ? " and 1=1" : " and a.cpzdm='"
			+ cpzdm + "'");

		String sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,"
			+ " dycpf,zycpf,zhcpf,tycj,(case when zhcpf is not null then (dense_rank() over partiton by xn,cpzdm order by to_number(zhcpf) desc nulls last) else to_number('') end) pm from view_zjlg_zhszcpf a"
			+ query.toString()
			+ " and xh like ? and xm like ?";
			
		
		if (UserTypePd.isFdy(isBzr)) {
			sql += " and exists (select 1 from fdybjb b where zgh like '"+zgh+"' and a.bjdm = b.bjdm) order by bjdm,xh";
		}
		

		List<HashMap<String, String>>  rsList =dao.getList(sql, new String[] {xh, xm}, colList);

//		System.out.println(sql);
//		分页
//		sql = "select count(*) count from view_zjlg_zhszcpf a"
//			+ query.toString()
//			+ " and xh like ? and xm like ?"
//			+ " and exists (select 1 from fdybjb b where zgh like ? and a.bjdm = b.bjdm)"
//			+ " order by bjdm,xh";
//		myForm.getPages().setMaxRecord(
//				Integer.parseInt(dao.getOneRs(sql, new String[] {xh, xm, zgh}, "count")));

		return rsList;
	}


	/**
	 * @author luo
	 * @throws Exception
	 * @describe 计算综合素质测评分
	 */
	public boolean saveZhszcpf(ZjlgPjpyModel model) throws Exception {

		boolean flg = false;
		
		// 学年
		String xn = DealString.toGBK(model.getXn());
		xn = Base.isNull(xn) ? Base.currXn : xn;
		
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select a.xh,(a.psf + a.wsf + a.kqf - a.kf) / bl dycpf from (select a.*,");
//		sql.append(" case when b.pjfs = '5' then 20 else 1 end bl from (select t.xh,");
//		sql.append(" t.xm,t.xb,t.nj,t.xn,t.xydm,t.xymc,t.zydm,t.zymc,t.dqxn,t.bjdm,");
//		sql.append(" t.bjmc,case when t.psf > b.psfbl then b.psfbl else to_char(t.psf)");
//		sql.append(" end psf,case when t.wsf > b.wsfbl then b.wsfbl else to_char(t.wsf)");
//		sql.append(" end wsf,case when t.kqf > b.kqfbl then b.kqfbl else to_char(t.kqf)");
//		sql.append(" end kqf,t.kf from (select t.xh,t.xm,t.xb,t.xn,t.nj,t.xydm,t.xymc,");
//		sql.append(" t.zydm,t.zymc,'"+xn+"' dqxn,t.bjdm,t.bjmc,case when pssh = '已通过' ");
//		sql.append(" then case when isbl = 'yes' then (zwpyf * zpfbl + bjpyf * bjfbl) / 100 +");
//		sql.append(" psfjf else zwpyf + bjpyf + psfjf end else 0 end psf,case");
//		sql.append(" when wssh = '已通过' then qsf + wsfjf else 0 end wsf,kqf,kf,rownum r");
//		sql.append(" from (select t.* from (select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,");
//		sql.append(" a.zydm,a.zymc,a.bjdm,a.bjmc,nvl(b.zwpyf, 0) zwpyf,nvl(b.bjpyf, 0) bjpyf,");
//		sql.append(" nvl(b.xyfjf, 0) psfjf,b.xysh pssh,b.xn,b.isbl,b.zpfbl,b.bjfbl,");
//		sql.append(" nvl(c.qsf, 0) qsf,nvl(c.xyfjf, 0) wsfjf,c.xysh wssh,nvl(d.kqf, 0) kqf,");
//		sql.append(" nvl(f.kf, 0) kf from view_xsjbxx a left join (select t.xh,e.xn,t.DQXN,");
//		sql.append(" t.XYSH,t.zwpyf,t.bjpyf,t.xyfjf,e.isbl,e.zpfbl,e.bjfbl from view_zjlg_dypsf t");
//		sql.append(" left join zjlg_dypsf_sz e on t.dqxn = e.xn and exists (select 1 from fdybjb f");
//		sql.append(" where f.zgh = e.bzrzgh and f.bjdm=t.bjdm)) b on a.xh = b.xh and b.xn like '"+xn+"' left join view_zjlg_dywsf c on a.xh = c.xh");
//		sql.append(" and c.xn like '"+xn+"' left join view_zjlg_dykqf d on a.xh = d.xh");
//		sql.append(" and d.xn like '"+xn+"' left join (select t.xh, t.xn, sum(b.cfkf) kf from wjcfb t,");
//		sql.append(" view_xsjbxx a, zjlg_wjkfb b where t.xh = a.xh and t.xxsh = '通过' and t.cflb = b.cfdm and t.xn = b.xn");
//		sql.append(" group by t.xn, t.xh) f on a.xh = f.xh and f.xn like '"+xn+"') t");
//		sql.append(" order by t.bjdm, t.xh) t) t,zjlg_dycpf_sz b where t.dqxn = b.xn) a");
//		sql.append(" left join ZJLG_pjfsSdb b on b.xn = a.xn and a.nj = b.nj)");
//		sql.append(" a where exists(select 1 from zjlg_zhszcp b where a.xh=b.xh and a.xn=b.xn)");
//		List<HashMap<String,String>> list = dao.getList(sql.toString(), new String[]{}, new String[]{"xh","dycpf"});
//		System.out.println(sql.toString());
//		if (list != null && list.size() > 0) {
//
//			StringBuffer sb = new StringBuffer();
//			String sbsql = "";
//			for (int i = 0; i < list.size(); i++) {
//				String xh = list.get(i).get("xh");
//				String dycpf = list.get(i).get("dycpf");
//				sbsql = "update zjlg_zhszcp set dycpf = '" + dycpf
//						+ "' where xh||xn='" + xh + xn + "'";
//				sb.append(sbsql);
//				sb.append("!!#!!");
//			}
//
//			String[] inssql = sb.toString().split("!!#!!");
//
//			int[] res = dao.runBatch(inssql);
//			for (int i = 0; i < res.length; i++) {
//				flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
//				if (!flg)
//					break;
//			}
//		}
		// String sql = "select avg(hdxf) hdxf from xshdxfxxb@dblink_jw";
		// String str_pjhdxf = dao.getOneRs(sql, new String[] {}, "hdxf");
		// str_pjhdxf = Base.isNull(str_pjhdxf) ? "0" : str_pjhdxf;

		flg = dao.runProcuder("{call zjlg_pjpy_zhszcpf(?, ?)}", new String[]{xn,Base.currXn});
		
		return flg;
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 判断是否设置过德育测评分最高分
	 */
	public boolean isMaxDy(String xn) {
		boolean flg = false;
		String sql ="select count(*) count from zjlg_dycpf_sz where xn = ?";
		String count = dao.getOneRs(sql, new String[]{xn}, "count");
		if(!"0".equalsIgnoreCase(count)){
			flg = true;
		}
		return flg;
	}

		
	/**
	 * @author luo
	 * @describe 获得综合素质测评打印列表
	 * 
	 * model.bjdm非空，得到学生综合素质的班级排名
	 * model.zydm非空，bjdm为空，得到学生综合素质的专业排名排名
	 * return List中包含输出值：pm 排名,xh 学号,bjmc 班级名称,xm 姓名,dycpf 德育测评分,dypm 德育排名,zycpf 智育测评分,zypm 智育排名,bjg 不及格课程数,zhcpf 综合素质测评分
	 */
	public List<HashMap<String, String>> getZhczPrintList(ZjlgPjpyModel model)
	throws SQLException {

		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 学号
		String xh = DealString.toGBK(model.getXh());
		// 参评组代码
		String cpzdm = DealString.toGBK(model.getCpzdm());
		
		StringBuffer query1 = new StringBuffer();
		StringBuffer query2 = new StringBuffer();
		if (!Base.isNull(xh)) {
//			bjdm = dao.getOneRs("select bjdm from view_xsjbxx where xh = ?",
//					new String[] { xh }, "bjdm");
			query1.append("select * from (");
			query2.append(") where xh = '" + xh + "'");
		}
		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and nj='" + nj
				+ "'");
		query.append("".equalsIgnoreCase(xn) ? " and 1=1" : " and xn='" + xn
				+ "'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and xydm='"
			+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and zydm='"
			+ zydm + "'");
		query.append("".equalsIgnoreCase(bjdm) ? " and 1=1" : " and bjdm='"
			+ bjdm + "'");
		query.append("".equalsIgnoreCase(cpzdm) ? " and 1=1" : " and cpzdm='"
			+ cpzdm + "'");

		String sql = query1
				+ "select zhpm pm, t.xh, t.bjmc, t.xm, trim(to_char(t.dycpf,'999.99'))dycpf, t.dypm, "
				+ " trim(to_char(t.zycpf,'999.99'))zycpf, t.zypm, nvl(c.bjg, 0) bjg, trim(to_char(t.zhcpf,'999.99'))zhcpf from (select t.* from "
				+ " (select a.xh,a.cpzdm,a.xm,a.xn,a.bjmc,(case when a.dycpf<1 then '0'||a.dycpf else a.dycpf end)dycpf,a.zhpm || '/'||d.cpznum zhpm,b.dypm," 
				+"(case when a.zycpf<1 then '0'||a.zycpf else a.zycpf end)zycpf,c.zypm,(case when a.zhcpf<1 then '0'||a.zhcpf else to_char(a.zhcpf) end)zhcpf"
				+ " from (select xh, xm, xn, bjmc, cpzdm,trim(dycpf)dycpf, trim(zycpf)zycpf, trim(zhcpf)zhcpf, (dense_rank() "
				+ " over(partition by xn,cpzdm order by zhcpf desc nulls last)) zhpm from view_zjlg_zhszcpf "
				+ query
				+ ") a, (select xh,dypm from view_zjlg_zhszcpf "
				+ query
				+ ") b, (select xh, zypm"
				+ " from view_zjlg_zhszcpf "
				+ query
				+ ") c,(select count(xh) cpznum,cpzdm,xn from view_zjlg_zhszcpf group by cpzdm,xn) d"
				+ " where a.xh = b.xh and b.xh = c.xh and a.cpzdm = d.cpzdm and a.xn = d.xn order by zhcpf desc) t order by cpzdm,zhpm ) t"
				+ " left join (select xh, xn, count(cj) bjg from cjb where kcxz not like '%辅修二专业%' and cj < '60' or cj='不及格' or cj='旷考' "
				+ " group by xn, xh) c on t.xh = c.xh and t.xn = c.xn" + query2;
//		System.out.println(sql);
		List<HashMap<String, String>> rsList = dao.getArrayList(sql,
				new String[] {}, new String[] { "pm", "xh", "bjmc", "xm",
				"dycpf", "dypm", "zycpf", "zypm", "bjg", "zhcpf" });
		return rsList;
	}

	/**
	 * @author luo
	 * @describe 获得报表打印列表
	 */
	public List<HashMap<String, String>> getBbdyList(String xnIn, String xyIn,
			String zyIn, String bjIn,String cpzIn,String xh,String xm,String jxjdm) throws SQLException {
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(xm)?"":" and xm='"+xm+"' ");
		
		
		String sql = "select count(*) count from view_zjlg_zhszcpf where xydm like ? and zydm like ? and bjdm like ?";

		sql = "select zhpm pm,t.xh,t.bjmc,t.xm,t.dycpf,t.dypm,t.zycpf,"
			+ " t.zypm,nvl(c.bjg, 0) bjg,t.zhcpf,(select zjlg_jxjmc(t.xn, t.xh) from dual) jxjmc,(select zjlg_jxjffhz(t.xn, t.xh) from dual) jlje,"
			+ " (select jlje12 from (select xh, xn, sum(jlje) jlje12 from (select xh,xn,(select zjlg_jxjffhz(xn, xh) from dual) jlje" 
			+" from (select xh,"
			+ " xn,  jxjze,  (case when jxjze = '1' then  sum(jlje) else  max(jlje)"
			+ " end) jlje from xsjxjb a, (select jlje, jxjdm, jxjmc, jxjlbdm, jxjlbmc,"
			+ " (case when jxjmc like '%纺织之光%' then '1' when jxjmc like '%国家励志奖%' " 
			+ "then '1' when jxjmc like '%桑麻奖学金%' then '1' else '2' end) jxjze"
			+ " from jxjdmb a, jxjlbdmb b where a.jxjlb = b.jxjlbdm) b"
			+ " where a.jxjdm = b.jxjdm  group by xh, xn, jlje, jxjze)"
			+ " group by xh, xn, jxjze) group by xh, xn) j where j.xh = t.xh and j.xn = t.xn) jlje,"
			+ " (select tycpf from view_zjlg_zhszcpf c where "
			+ "  c.xh = t.xh and c.xn = t.xn) tycj,(select zjlg_yycj(t.xn, t.xh) from dual) yycj,"
			+ " (select zjlg_jsjcj(t.xn, t.xh) from dual) jsjcj,(select zjlg_rych(t.xn, t.xh) from dual) rych,"
			+ " (select zjlg_wjcf(t.xn, t.xh) from dual) wjcf from (select t.*"
			+ " from (select a.xh,a.xm,a.xn,a.bjmc,a.dycpf,a.cpzdm,a.zhpm || '/'||d.cpznum zhpm,b.dypm,a.zycpf,c.zypm,"
			+ " a.zhcpf from (select xh,xm,cpzdm,xn,bjmc,dycpf,zycpf,zhcpf,(dense_rank()"
			+ " over(partition by xn, cpzdm order by zhcpf desc nulls last)) zhpm "
			+ " from view_zjlg_zhszcpf where xydm like '"
			+ xyIn
			+ "' and zydm like '"
			+ zyIn
			+ "' and bjdm like '"
			+ bjIn
			+ "' and cpzdm like '"
			+ cpzIn
			+ "' and xn = '"
			+ xnIn
			+ "') a,(select xh,dycpf,(dense_rank()"
			+ " over(partition by xn, cpzdm order by trim(to_number(dycpf,999.9999)) desc nulls last)) dypm"
			+ " from view_zjlg_zhszcpf where xydm like '"
			+ xyIn
			+ "' and zydm like '"
			+ zyIn
			+ "' and bjdm like '"
			+ bjIn
			+ "' and cpzdm like '"
			+ cpzIn
			+ "' and xn = '"
			+ xnIn
			+ "') b,(select xh,zycpf,(dense_rank()"
			+ " over(partition by xn, cpzdm order by trim(zycpf) desc nulls last)) zypm"
			+ " from view_zjlg_zhszcpf where xydm like '"
			+ xyIn
			+ "' and zydm like '"
			+ zyIn
			+ "' and bjdm like '"
			+ bjIn
			+ "' and cpzdm like '"
			+ cpzIn
			+ "' and xn = '"
			+ xnIn
			+ "' order by zycpf desc) c ,(select count(xh) cpznum,cpzdm from view_zjlg_zhszcpf group by cpzdm) d"
			+ " where a.xh = b.xh and b.xh = c.xh and a.cpzdm = d.cpzdm order by zhcpf desc) t) t"
			+ " left join (select xh, xn, count(cj) bjg from cjb where kcxz not like '%辅修二专业%' and cj < 60"
			+ " group by xn, xh) c on t.xh = c.xh and t.xn = c.xn order by cpzdm,zhpm";
	System.out.println(sql);
	List<HashMap<String, String>> rsList = dao.getArrayList(sql,
			new String[] {}, new String[] { "pm", "xh", "bjmc", "xm",
					"dycpf", "dypm", "zycpf", "zypm", "bjg", "zhcpf",
					"jxjmc", "jlje", "tycj", "yycj", "jsjcj", "rych",
					"wjcf" });
	return rsList;

	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存条件设置
	 */
	public boolean saveTjsz(String xn,String szlx,String jxjdm,String zdm,String tj,String tjlx,String tjz,
			HttpServletRequest request)
	throws Exception {

		HashMap<String, String> map = dao.getMap(
				"select * from jxjtjzdb where zdmc= ? and szlx = ?",
				new String[] { zdm, szlx }, new String[] { "zdsm", "lyb",
				"lybm" });

		String lyb = map.get("lyb");
		//String lybm = map.get("lybm");
		String tjmc = map.get("zdsm");

		if (!Base.isNull(lyb)) {
			tj = lyb;
		}

		String value = xn + szlx + jxjdm + zdm;

		boolean flg = StandardOperation.delete("zjlg_pjpy_tjsz",
				"xn||szlx||jxjdm||tjzd", value, request);
		if (flg) {
			flg = StandardOperation.insert("zjlg_pjpy_tjsz",
					new String[] { "xn", "szlx", "jxjdm", "tj", "tjzd", "tjmc",
					"tjlx", "tjz" }, new String[] { xn, szlx, jxjdm,
					tj, zdm, tjmc, tjlx, tjz }, request);
		}
		//String a =getZybfb( "2007-2008", "3051011025","1011");
		return flg;
	}
	/**
	 * 获取荣誉称号列表
	 */
	public List<HashMap<String,String>> getRychList(){
		DAO dao = DAO.getInstance();
		String sql = "select rychdm dm, rychmc mc from rychdmb";
		return dao.getList(sql,new String[]{},new String[]{"dm","mc"});
	}
	/**
	 * 获取相关信息
	 * @param table 表名
	 * @param querry 条件
	 * @return
	 */
	public HashMap<String,String> dao_getInfo(String table,String querry){
		DAO dao = DAO.getInstance();
		String[] colList =  dao.getColumnName("select * from "+table);
		for(int i=0;i<colList.length;i++){
			colList[i]=colList[i].toLowerCase();
		}
		String sql = "select * from "+table;
		return dao.getMap(sql+querry.toString(),new String[]{},colList);
	}
	/**
	 * 荣誉称号申请信息保存
	 */
	public boolean dao_rychSave(RychModel  model) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String xh     = model.getXh();
		String rychdm = model.getRychdm();
		String bz     = model.getBz();
		String xn     = Base.getJxjsqxn();
//		String xq     = Base.getJxjsqxq();
		String  pk    = "xh||xn||rychdm";
		String sql    = "";		
		String tag = dao.getOneRs("select count(xh)cout from zjlg_xsrychb where "+pk+"=?",new String[]{xh+xn+rychdm},"cout");
		if("0".equalsIgnoreCase(tag)){
			sql = " insert into zjlg_xsrychb(xh,xn,rychdm,bz) values(?,?,?,?) ";
		}else{
			sql = " update zjlg_xsrychb set xh=?,xn=?,rychdm=?,bz=? where "+pk+"='"+xh+xn+rychdm+"'";
		}
		done = dao.runUpdate(sql, new String[]{xh,xn,rychdm,bz});
		return done;
	}	
	/**
	 * 荣誉称号申请信息查询
	 */
	public ArrayList<String[]> dao_rychDefault(RychModel model){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String rychdm = model.getRychdm();
		String xn   = model.getXn();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(rychdm)?"":" and rychdm='"+rychdm+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");	
		String[] colList = new String[]{"pk","xn","xh","xm","rychmc","sqsj","xysh","xxsh"};
		String  sql = " select xh||xn||rychdm pk,xn,xh,xm,rychmc,sqsj,xysh,xxsh from view_zjlg_xsrychxx  " +querry;
		result = dao.rsToVator(sql, new String[]{},colList);
		return result;
	}
	/**
	 * 获取查询表头
	 * @param colListCN
	 * @return
	 */
	public ArrayList<HashMap<String, String>> dao_getSearchTitle(String[] colListCN ) {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < colListCN.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	/**
	 * 学生荣誉称号信息增加保存
	 */
	public boolean dao_rychAddSave(RychModel  model) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String xh     = model.getXh();
		String rychdm = model.getRychdm();
		String bz     = model.getBz();
		String xn     = Base.getJxjsqxn(); 
//		String xq     = Base.getJxjsqxq();
		String  pk    = "xh||xn||rychdm";
		String sql    = "";		
		String tag = dao.getOneRs("select count(xh)cout from zjlg_xsrychb where "+pk+"=?",new String[]{xh+xn+rychdm},"cout");
		if("0".equalsIgnoreCase(tag)){
			sql = " insert into zjlg_xsrychb(xh,xn,rychdm,bz,xysh,xxsh) values(?,?,?,?,'通过','通过') ";
		}else{
			sql = " update zjlg_xsrychb set xh=?,xn=?,rychdm=?,bz=?,xysh='通过',xxsh='通过' where "+pk+"='"+xh+xn+rychdm+"'";
		}
		done = dao.runUpdate(sql, new String[]{xh,xn,rychdm,bz});
		return done;
	}
	/**
	 * 学生荣誉称号信息修改
	 */
	public boolean dao_rychAddModi(RychModel  model,String pkValue) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String rychdm = model.getRychdm();
		String bz     = model.getBz();		
		String  pk    = "xh||xn||rychdm";
		String sql    = "";		
		sql = " update zjlg_xsrychb set rychdm=?,bz=?,xysh='未审核',xxsh='未审核' where "+pk+"='"+pkValue+"'";
		done = dao.runUpdate(sql, new String[]{rychdm,bz});
		return done;
	}
	/**
	 * 学生荣誉称号信息删除
	 */
	public boolean dao_rychDelete(String delPk) throws Exception{
		DAO dao = DAO.getInstance();	
		String pk = "xh||xn||rychdm";
		String[] pkValueA = delPk.split("!!");		
		String[] delPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete zjlg_xsrychb  where "+pk+"= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(delPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * 获取审核列表
	 */
	public List<HashMap<String, String>> getChkList() {
		DAO dao = DAO.getInstance();	
		return dao.getChkList(3);
	}
	/**
	 * 荣誉称号审核信息查询
	 */
	public ArrayList<String[]> dao_rychCkDefault(RychModel model,String userType){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String yesNo = model.getYesNo();
		String rychdm = model.getRychdm();
		String xn = model.getXn();
		StringBuffer sql = new StringBuffer(); 

		StringBuffer querry = new StringBuffer(" where 1=1 ");
		if("xy".equalsIgnoreCase(userType)){
			querry.append(Base.isNull(yesNo)?"":" and xysh='"+yesNo+"' ");
		}else{
			querry.append(Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"'");
			querry.append(" and xysh='通过' ");
		}		
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(rychdm)?"":" and rychdm='"+rychdm+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
//		String[] colList = new String[]{"pk","xn","xh","xm","rychmc","sqsj","xysh","xxsh","zycpf","dycpf","zhcpf","xypm","zypm","bjpm"};
//		sql.append("select xh||xn||rychdm pk,xn,xh,xm,rychmc,sqsj,xysh,xxsh, zycpf,dycpf,zhcpf,xypm,zypm,bjpm ");
//		sql.append("from (select a.*,b.zycpf,b.dycpf,b.zhcpf,b.xypm,b.zypm,b.bjpm from view_zjlg_xsrychxx a left join ( ");
//		sql.append("select t.xh,t.xn,t.zycpf,t.dycpf,t.zhcpf,t.xypm,t.zypm,t.bjpm from (select a.xh, a.xn, a.zycpf,a.dycpf,a.zhcpf, ");
//		sql.append("a.xypm,b.zypm,c.bjpm  from (select xh,  xn, zycpf,dycpf,zhcpf,(dense_rank() over (partition by xn,xydm order by zhcpf desc nulls last)) xypm ");
//		sql.append("from view_zjlg_zhszcpf   ) a,(select xh, xn,dycpf,zhcpf,(dense_rank() over (partition by xn,zydm order by zhcpf desc nulls last)) zypm ");
//		sql.append("from view_zjlg_zhszcpf ) b,( select xh, xn, dycpf,zhcpf,(dense_rank() over (partition by xn,bjdm order by zhcpf desc nulls last)) bjpm ");
//		sql.append("from view_zjlg_zhszcpf ) c where a.xh=b.xh and b.xh=c.xh and a.xn=b.xn and b.xn=c.xn ) t )b on a.xh=b.xh and a.xn=b.xn ) ");

//		String  sql = " select xh||xn||rychdm pk,xn,xh,xm,rychmc,sqsj,xysh,xxsh from view_zjlg_xsrychxx  " +querry;
		String[] colList = new String[]{"pk","xn","xh","xm","rychmc","sqsj","xysh","xxsh","zycpf","dycpf","zhcpf","pm"};
		sql.append("select xh||xn||rychdm pk,xn,xh,xm,nj,xydm,zydm,bjdm,rychdm,rychmc,sqsj,xysh,xxsh, zycpf,dycpf,zhcpf,pm  ");
		sql.append("from (select a.*,b.zycpf,b.dycpf,b.zhcpf,b.pm from view_zjlg_xsrychxx a left join view_zjlg_zhszcpf b on a.xh=b.xh and a.xn=b.xn )  ");
		result = dao.rsToVator(sql+querry.toString(), new String[]{},colList);
		return result;
	}
	/**
	 * 荣誉称号审核
	 */
	public boolean dao_rychCk(String pkVStr,String userType,String check) throws Exception{
		DAO dao = DAO.getInstance();	
		String pk = "xh||xn||rychdm";
		String shType = "";
		shType = ("xy".equalsIgnoreCase(userType))?"xysh":"xxsh";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"update zjlg_xsrychb set "+shType+"='"+check+"'  where "+pk+"= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}

	/**
	 * 学生奖学金增加保存
	 */
	public boolean dao_jxjAddSave(ZjlgPjpyModel  model) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String xh     = model.getXh();
		String xn     = Base.getJxjsqxn();
		String xq     = Base.getJxjsqxq();
		String jxjdm  = model.getJxjdm();
		//本人申请
		String xxjl   = model.getXxjl();
		//班级意见
		String fdyyj  = model.getFdyyj();
		//学院意见
		String xyshyj = model.getXyshyj();
		//学校意见
		String xxshyj = model.getXxshyj();
		//主要事迹
		String zysj = model.getZysj();
		//教育厅意见
		String jytyj = model.getJytyj();
		//毕业去向
		String byqx = model.getByqx();
		//担任社会工作情况
		String kycg = model.getKycg();
		//理事会审批意见
		String lshshyj = model.getLshshyj();
		String bz = model.getBz();
		//获奖情况
		String jfqk = model.getJfqk();
		//申请理由
		String sqly = model.getSqly();
		String yhkh = model.getYhkh();
		String yhlx = model.getYhlx();
		String nccz = model.getNccz();
		String sbdj = model.getSbdj();
		String djchdjxj = model.getDjchdjxj();
		
		
		String  pk    = "xh||xn||jxjdm";
		String sql    = "";	
		
		
		
		String num = dao.getOneRs("select count(xh) cout from xsjxjb where "+pk+"=?",new String[]{xh+xn+jxjdm},"cout");
		if("0".equalsIgnoreCase(num)){
			sql = " insert into xsjxjb(xh,xn,xq,jxjdm,xxjl,fdyyj,xyshyj,xxshyj,jfqk,bz,zysj,jytyj,byqx,kycg,lshshyj,sqly,yhkh,yhlx,nccz,sbdj,djchdjxj,sqsj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyymmddhh24miss')) ";
			done = dao.runUpdate(sql, new String[]{xh,xn,xq,jxjdm,xxjl,fdyyj,xyshyj,xxshyj,jfqk,bz,zysj,jytyj,byqx,kycg,lshshyj,sqly,yhkh,yhlx,nccz,sbdj,djchdjxj});
		}else{
			sql = " update xsjxjb set xh=?,xn=?,xq=?,jxjdm=?,xxjl=?,fdyyj=?,xyshyj=?,xxshyj=?,jfqk=?,bz=?,zysj=?,jytyj=?,byqx=?,kycg=?,lshshyj=?,sqly=?,yhkh=?,yhlx=?,nccz=?,sbdj=?,djchdjxj=? where xh||xn||jxjdm=?";
			done = dao.runUpdate(sql, new String[]{xh,xn,xq,jxjdm,xxjl,fdyyj,xyshyj,xxshyj,jfqk,bz,zysj,jytyj,byqx,kycg,lshshyj,sqly,yhkh,yhlx,nccz,sbdj,djchdjxj,xh+xn+jxjdm});
		}
		return done;
	}
	/**
	 * 荣誉称号审核
	 */
	@SuppressWarnings("unchecked")
	public String dao_getFdyForBjdm(String bjdm) throws Exception{
		DAO dao = DAO.getInstance();	
		String fdy = "";
		String sql = "select distinct zgh,xm from view_fdybjdz where bjdm=? ";
		List fdyXx = dao.getList(sql,new String[]{bjdm},new String[]{"xm"});
		if(fdyXx.size()>0){
			for(int i=0;i<fdyXx.size();i++){
				HashMap<String,String> map = (HashMap)fdyXx.get(i);
				fdy+=map.get("xm")+",";
			}
		}
		return fdy;
	}
	/**
	 * 统计班级人数
	 */
	public String dao_getXhNum(String bjdm){
		DAO dao  = DAO.getInstance();
		String sql = "select count(xh)cout from view_xsjbxx where bjdm=?";
		return dao.getOneRs(sql,new String[]{bjdm},"cout");
	}
	/**
	 * 先进班级申请保存
	 */
	public boolean dao_xjbjsqSave(XjbjModel model) throws Exception{
		DAO dao = DAO.getInstance();
		boolean done = false;
		String bjdm   = model.getBjdm();
		String wmqsgs = model.getWmqsgs();
		String ajqsgs = model.getAjqsgs();
		String xn     = Base.getJxjsqxn();
		String bjqk   = model.getBjqk();
		String sfyxbj = model.getSfyxbj();
		String  pk    = "bjdm||xn";
		String sql    = "";		
		String tag = dao.getOneRs("select count(bjdm)cout from zjlg_xjbjb where "+pk+"=?",new String[]{bjdm+xn},"cout");
		if("0".equalsIgnoreCase(tag)){
			sql = " insert into zjlg_xjbjb(bjdm,xn,wmqsgs,ajqsgs,bjqk,sfyxbj) values(?,?,?,?,?,?) ";
		}else{
			sql = " update zjlg_xjbjb set bjdm=?,xn=?,wmqsgs=?,ajqsgs=?,bjqk=?,sfyxbj=? where "+pk+"='"+bjdm+xn+"' ";
		}
		done = dao.runUpdate(sql, new String[]{bjdm,xn,wmqsgs,ajqsgs,bjqk,sfyxbj});
		return done;
	}
	/**
	 * 奖学金查询
	 */
	public ArrayList<String[]> jxj_query(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String xn = model.getXn();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String jxjdm = model.getJxjdm();
		String cpzdm = model.getCpzdm();
		
		StringBuffer querry = new StringBuffer(" and 1=1 ");
		StringBuffer querry1 = new StringBuffer(" and 1=1 ");
		querry.append(Base.isNull(xh)?"":" and b.xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry1.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(cpzdm)?"":" and cpzdm='"+cpzdm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and a.bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(jxjdm)?"":" and b.jxjdm='"+jxjdm+"' ");
		String[] colList = new String[]{"pk","jxjdm","xn","xh","xm","jxjmc","jxjlbmc","sqsj","xysh","xxsh"};
		String  sql = "select * from (select a.*,rownum r from (select distinct a.xh||xn||b.jxjdm pk,b.jxjdm,xn,a.xh,xm,xb,jxjmc,jxjlbmc,sqsj,xysh,xxsh,d.cpzdm" +
		" from view_xsxxb a left outer join (select bjdm,cpzdm from zjlg_cpzfpb where 1=1 "+querry1+") d on a.bjdm=d.bjdm,xsjxjb b,(select distinct jxjdm,jxjmc,jxjlbdm,jxjlbmc from jxjdmb a,jxjlbdmb b where a.jxjlb=b.jxjlbdm) c " +
		"where a.xh=b.xh and b.jxjdm=c.jxjdm "+querry+") a) a where r<="
		+ (myForm.getPages().getStart() + myForm.getPages().getPageSize())
		+ " and r> "
		+ myForm.getPages().getStart();
		result = dao.rsToVator(sql, new String[]{},colList);
		return result;
	}
	/**
	 * 奖学金记录查询
	 */
	public String jxj_queryrsunm(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		DAO dao      = DAO.getInstance();
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String jxjdm = model.getJxjdm();
		String cpzdm = model.getCpzdm();
		String xn = model.getXn();
		
		StringBuffer querry = new StringBuffer(" and 1=1 ");
		StringBuffer querry1 = new StringBuffer(" and 1=1 ");
		querry.append(Base.isNull(xh)?"":" and b.xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(cpzdm)?"":" and cpzdm='"+cpzdm+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry1.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and a.bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(jxjdm)?"":" and b.jxjdm='"+jxjdm+"' ");
//		String[] colList = new String[]{"pk","jxjdm","xn","xh","xm","jxjmc","jxjlbmc","sqsj","xysh","xxsh"};
		String sql="select count(*) count from (select a.*,rownum r from (select distinct a.xh||xn||b.jxjdm pk,b.jxjdm,xn,a.xh,xm,xb,jxjmc,jxjlbmc,sqsj,xysh,xxsh,d.cpzdm" +
		" from view_xsxxb a left outer join (select bjdm,cpzdm from zjlg_cpzfpb where 1=1 "+querry1+") d on a.bjdm=d.bjdm,xsjxjb b,(select distinct jxjdm,jxjmc,jxjlbdm,jxjlbmc from jxjdmb a,jxjlbdmb b where a.jxjlb=b.jxjlbdm) c " +
		"where a.xh=b.xh and b.jxjdm=c.jxjdm "+querry+") a)";
//		System.out.println(sql);
		String count = dao.getOneRs(sql, new String[]{}, "count");
		count = StringUtils.isNull(count)?"0":count;
		myForm.getPages().setMaxRecord(Integer.parseInt(count));
		return count;
	}
	/**
	 * 奖学金审核查询
	 */
	public ArrayList<String[]> jxj_shquery(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String xn = model.getXn();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String jxjdm = model.getJxjdm();
		String yesNo = myForm.getYesNo();
		String cpzdm = myForm.getCpzdm();
		StringBuffer querry = new StringBuffer(" and 1=1 ");
		StringBuffer query1 = new StringBuffer();
        query1.append(" where 1=1");
	
		String whosh = "";
		if("xy".equals(userType)){
			whosh = "xysh";
		}else{
			whosh = "xxsh";
			querry.append(" and xysh='通过' ");
		}
	
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		query1.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(jxjdm)?"":" and jxjdm='"+jxjdm+"' ");
		querry.append(Base.isNull(yesNo)?"":" and "+whosh+"='"+yesNo+"' ");
		querry.append(Base.isNull(cpzdm)?"":" and cpzdm='"+cpzdm+"' ");
			
		String isZds = myForm.getIsZds();
		String bmlb = "";
		@SuppressWarnings("unused")
		String bmdm = "";
		
		query1.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		
		if("xysh".equals(isZds)){
			bmlb = "xydm";
			bmdm = xydm;
			query1.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		}else if("cpzsh".equals(isZds)){
			bmlb = "cpzdm";
			bmdm = cpzdm;
			query1.append(Base.isNull(cpzdm)?"":" and cpzdm='"+cpzdm+"' ");
		}else if("bjsh".equals(isZds)){
			bmlb = "bjdm";
			bmdm = bjdm;
			query1.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		}
		
		String[] colList;
		if("xy".equals(userType)){
			colList = new String[]{"pk","jxjdm","xh","xm","jxjlbmc","sqsj","bjmc","xysh","dycj","tycj","zycj","pm"};
		}else{
			colList = new String[]{"pk","jxjdm","xh","xm","jxjlbmc","sqsj","bjmc","xysh","xxsh","dycj","tycj","zycj","pm"};
		}
//		String  sql = "select a.*,b.dycpf dycj,zycpf zycj,(select round(avg(cj), 1) " +
//				"from cjb a where a.kcmc like '%体育%' and a.xh = b.xh) tycj,zhcpf zhszcpcjpm " +
//				"from (select distinct a.xh || xn || b.jxjdm pk,b.jxjdm,xn,a.xh,xm,xb,jxjmc,jxjlbmc," +
//				"sqsj,xysh,xxsh from view_xsxxb a,xsjxjb b,(select distinct jxjdm, jxjmc, jxjlbdm, " +
//				"jxjlbmc from jxjdmb a, jxjlbdmb b where a.jxjlb = b.jxjlbdm) c where a.xh = b.xh " +
//				"and b.jxjdm = c.jxjdm) a,view_zjlg_zhszcpf b where a.xh = b.xh";
		
		//综测排名统一按参评组进行排名
		String sql = "select * from (select a.*,b.cpzdm,(case when b.dycpf='0' then '0' when b.dycpf<'1' then '0'||b.dycpf else b.dycpf end)dycj,"
				+"(case when trim(b.zycpf)='0' then '0' when trim(b.zycpf)<'1' then '0'||trim(b.zycpf) else trim(b.zycpf) end ) zycj," 
				+"(case when trim(b.tycpf)='0' then '0' when trim(b.tycpf)<'1' then '0'||trim(b.tycpf) else trim(b.tycpf) end ) tycj,b.pm from (select distinct a.xh || xn || b.jxjdm pk,b.jxjdm,xn," +
				"a.xh,a.xydm,a.xymc,a.zydm,a.zymc,a.nj,a.bjdm,a.bjmc,xm,xb,jxjmc,jxjlbmc,sqsj," +
				"xysh,xxsh from view_xsxxb a,xsjxjb b,(select distinct jxjdm, jxjmc, " +
				"jxjlbdm, jxjlbmc from jxjdmb a, jxjlbdmb b where a.jxjlb = b.jxjlbdm) c " +
				"where a.xh = b.xh and b.jxjdm = c.jxjdm) a left join view_zjlg_zhszcpf b on " +
				"a.xh = b.xh and a.xn=b.xn) where 1=1"+querry;
//		System.out.println(sql);
		result = dao.rsToVator(sql, new String[]{},colList);
		return result;
	}
	/**
	 * 奖学金发放查询
	 */
	public ArrayList<String[]> jxj_ffquery(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String xn = model.getXn();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String jxjdm = model.getJxjdm();
		String cpzdm = model.getCpzdm();
		String yesNo = myForm.getYesNo();
		String sql = "";
//		String whosh = "";
//		if("xy".equals(userType)){
//			whosh = "xysh";
//		}else{
//			whosh = "xxsh";
//		}
		StringBuffer querry = new StringBuffer();
		StringBuffer querry1 = new StringBuffer(" and 1=1 ");
		querry1.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		
		querry.append(Base.isNull(xh)?"":" and a.xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(jxjdm)?"":" and jxjdm='"+jxjdm+"' ");
		querry.append(Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"' ");
		querry.append(Base.isNull(cpzdm)?"":" and cpzdm='"+cpzdm+"' ");
		String[] colList;
		if("xy".equals(userType)){
			colList = new String[]{"xh","xm","jxjmc","jlje","xymc","bjmc"};
		}else{
			colList = new String[]{"xh","xm","jxjmc","jlje","xymc","bjmc"};
		}
		if(StringUtils.isNotNull(jxjdm)){
			sql = "select a.xm,a.nj,a.xydm,(select xymc from view_njxyzybj b where a.bjdm=b.bjdm) xymc," +
			"(select zymc from view_njxyzybj b where a.bjdm=b.bjdm) zydm,a.zymc," +
			"(select bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjdm,a.bjmc,b.xh,b.jxjdm,b.xn,b.sqsj,b.xysh,b.xxsh," +
			"b.jxjmc,b.jxjlb,b.jlje,b.szjdbz,b.jxjlbdm,b.jxjlbmc from view_xsxxb a left outer join (select bjdm,cpzdm from zjlg_cpzfpb where 1=1 "+querry1+") d on a.bjdm=d.bjdm,(select a.xh,a.jxjdm,a.xn,a.sqsj," +
			"a.xysh,a.xxsh,b.jxjmc,b.jxjlb,b.jlje,b.szjdbz,b.jxjlbdm,b.jxjlbmc from xsjxjb a,(select * from jxjdmb " +
			"a,jxjlbdmb b where a.jxjlb=b.jxjlbdm) b where a.jxjdm=b.jxjdm) b where a.xh=b.xh"+querry;
	}else{
//	String sql = "select a.*,b.xymc,b.zymc,b.bjmc from (select a.xm,a.nj,a.xydm,a.zydm,a.bjdm,b.xh,b.jxjdm,b.xn," +
//			"b.sqsj,b.xysh,b.xxsh,b.jxjmc,b.jxjlb,b.jlje,b.szjdbz,b.jxjlbdm,b.jxjlbmc from view_xsxxb a," +
//			"(select a.xh,a.jxjdm,a.xn,a.sqsj,a.xysh,a.xxsh,b.jxjmc,b.jxjlb,b.jlje,b.szjdbz,b.jxjlbdm,b.jxjlbmc from " +
//			"xsjxjb a,(select * from jxjdmb a,jxjlbdmb b where a.jxjlb=b.jxjlbdm) b where a.jxjdm=b.jxjdm) b where " +
//			"a.xh=b.xh "+querry+") a right outer join (select distinct a.xm,a.nj,a.xydm,b.xymc," +
//			"a.zydm,b.zymc,a.bjdm,b.bjmc from view_xsxxb a,view_njxyzybj b where a.xydm=b.xydm) b on b.xydm=a.xydm";
	sql = "select distinct b.xh,b.jxjmc,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,(select zjlg_jxjffhz(a.xn, a.xh) from dual) jlje,a.xxsh " 
		+"from (select a.xm,a.nj,a.xydm,(select xymc from view_njxyzybj b where a.bjdm = b.bjdm) xymc," 
		+"(select zymc from view_njxyzybj b where a.bjdm = b.bjdm) zymc,a.zydm,(select bjmc " 
		+"from view_njxyzybj b where a.bjdm = b.bjdm) bjmc,a.bjdm,b.xh,b.jxjdm,b.xn,b.sqsj," 
		+"b.xysh,b.xxsh,b.jxjmc,b.jlje,b.jxjlbdm,b.jxjlbmc,b.jxjze from view_xsxxb a left outer join (select bjdm,cpzdm from zjlg_cpzfpb where 1=1 "+querry1+") d on a.bjdm=d.bjdm," 
		+"(select a.xh,a.xn,a.xxsh,a.xysh,a.jxjdm,a.sqsj,b.jlje,b.jxjmc,b.jxjlbdm,b.jxjlbmc,b.jxjze " 
		+"from xsjxjb a,(select jlje,jxjdm,jxjmc,jxjlbdm,jxjlbmc,(case when jxjmc like '%纺织之光%' " 
		+"then '1' when jxjmc like '%国家励志奖%' then '1' when jxjmc like '%桑麻奖学金%' then '1' " 
		+"else '2' end) jxjze from jxjdmb a, jxjlbdmb b where a.jxjlb = b.jxjlbdm) b " 
		+"where a.jxjdm=b.jxjdm and a.xxsh='通过')b where a.xh = b.xh "+querry+" ) a " 
		+"right outer join (select distinct xh," 
		+"lower(max(ltrim(sys_connect_by_path('\"'||jxjmc||'\"',','),','))) jxjmc," 
		+"(case max(jxjze) when '1' then sum(jlje) else max(jlje) end) jlje from (select row_number() " 
		+"over(partition by xh order by xh) pno,row_number() " 
		+"over(partition by xh order by xh)-1 sno,xh,xh xsxh,jxjmc,jxjze,jlje from " 
		+"(select a.xm,a.nj,a.xydm,(select xymc from view_njxyzybj b where a.bjdm = b.bjdm) xymc," 
		+"(select zymc from view_njxyzybj b where a.bjdm = b.bjdm) zymc,a.zydm," 
		+"(select bjmc from view_njxyzybj b where a.bjdm = b.bjdm) bjmc,a.bjdm,b.xh,b.jxjdm,b.xn," 
		+"b.sqsj,b.xysh,b.xxsh,b.jxjmc,b.jlje,b.jxjlbdm,b.jxjlbmc,b.jxjze from view_xsxxb a  left outer join (select bjdm,cpzdm from zjlg_cpzfpb where 1=1 "+querry1+") d on a.bjdm=d.bjdm," 
		+"(select a.xh,a.xn,a.xxsh,a.xysh,a.jxjdm,a.sqsj,b.jlje,b.jxjmc,b.jxjlbdm,b.jxjlbmc,b.jxjze " 
		+"from xsjxjb a,(select jlje,jxjdm,jxjmc,jxjlbdm,jxjlbmc,(case when jxjmc like '%纺织之光%' " 
		+"then '1' when jxjmc like '%国家励志奖%' then '1' when jxjmc like '%桑麻奖学金%' then '1' " 
		+"else '2' end) jxjze from jxjdmb a, jxjlbdmb b where a.jxjlb = b.jxjlbdm) b " 
		+"where a.jxjdm=b.jxjdm and a.xxsh='通过')b where a.xh = b.xh "+querry+") " 
		+"group by xh,jxjmc,jlje) start with pno = 1 connect by prior pno = sno and prior xh=xsxh group by xh) " 
		+"b on a.xh=b.xh" ;
	}
//	System.out.println(sql);
	result = dao.rsToVator(sql, new String[]{},colList);
	return result;
	}
	/**
	 * 奖学金数据导出
	 * @throws IOException 
	 * @throws WriteException 
	 */
	public void jxj_DataExport(ZjlgPjpyForm myForm,ZjlgPjpyModel model,
			String jxjcxzj,HttpServletResponse response) throws IOException, WriteException{
		DAO dao      = DAO.getInstance();
//		ArrayList<String[]> result = new ArrayList<String[]>();
		StringBuffer querry = new StringBuffer(" and 1=1 ");
		String xh = DealString.toGBK(model.getXh());
		String nj = model.getNj();
		String xn = model.getXn();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = DealString.toGBK(model.getXm());
		String jxjdm = model.getJxjdm();
		myForm.setXh(xh);
		myForm.setXm(xm);
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(jxjdm)?"":" and jxjdm='"+jxjdm+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
//		String[] colList = new String[]{"pk","jxjdm","xn","xh","xm","jxjmc","jxjlbmc","sqsj","xysh","xxsh"};
		String  sql = "select * from view_jxjzjlg where 1=1"+querry;

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Vector<Object> vec = new Vector<Object>();
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("数据导出", 0);
//		String tabName = "view_jxjzjlg";

		String[] ColumnName;
		String[] ColumnNameCN;

		if("00021".equals(jxjcxzj) || "00022".equals(jxjcxzj) || "00023".equals(jxjcxzj)){
			ColumnName = new String[]{"jxjmc","xm","xb","csrq","mz","syd","zzmm","zw","lxdz","lxdh","xxjl","jfqk","xyshyj","xxshyj","bz"};
			ColumnNameCN = new String[]{"奖学金名称","姓名","性别","出生年月","民族","生源地","政治面貌","职务","家庭地址","联系电话","本人简历","获奖情况","学院意见","学校意见","备注"};
		}else if("00061".equals(jxjcxzj) || "00062".equals(jxjcxzj)){
			ColumnName = new String[]{"jxjmc","xm","xb","csrq","mz","syd","zzmm","zw","lxdz","lxdh","xxjl","zysj","jfqk","xyshyj","xxshyj","jytyj","byqx","bz",};
			ColumnNameCN = new String[]{"奖学金名称","姓名","性别","出生年月","民族","生源地","政治面貌","职务","家庭地址","联系电话","本人简 历","主要事迹","在校期间获奖情况",
					"院系意见","学校意见","省教育厅意见","毕业就业去向","备注"};
		}else if("00071".equals(jxjcxzj)){
			ColumnName = new String[]{"jxjmc","xm","xb","csrq","mz","zzmm","rxrq",		
					"xymc","zymc","kycg","jfqk","zysj","xxshyj","lshshyj","bz"};
			ColumnNameCN = new String[]{"奖学金名称","姓名","性别","出生年月","民族","政治面貌","入学年月",		
					"院系","专业","担任社会工作情况","何时何地受何表彰","主要事迹","学校评审意见","理事会审批意见","备注"};
		}else if("00072".equals(jxjcxzj)){
			ColumnName = new String[]{"jxjmc","xm","xb","csrq","xh","mz","rxrq",
					"sfzh","zzmm","lxdh","xxjl","jfqk","sqly","fdyyj","xyshyj","xxshyj"};
			ColumnNameCN = new String[]{"奖学金名称","姓名","性别","出生年月","学号","民族","入学时间",
					"身份证号","政治面貌","联系电话","学习等情况","获奖情况","申请理由","年级推荐意见","院系意见","学校意见"};
		}else if("00073".equals(jxjcxzj)){
			ColumnName = new String[]{"jxjmc","xm","xb","csrq","mz","zzmm","rxrq","xymc","zymc","kycg","jfqk",
					"zysj","xyshyj","lshshyj","bz"	};
			ColumnNameCN = new String[]{"奖学金名称","姓名","性别","出生年月","民族政治","面貌","入学年月","院系","专业","担任社会工作情况","何时何地受何表彰",
					"主要事迹","学院意见","评审委员会意见","备注"	};
		}else if("00074".equals(jxjcxzj)){
			ColumnName = new String[]{"jxjmc","xm","xb","mz","csrq","zzmm","zw","syd","zhpfmc","cjmc",
					"ah","jgs","jgs","jfqk","sqly","fdyyj","xyshyj","lshshyj","bz"};
			ColumnNameCN = new String[]{"奖学金名称","姓名","性别","民族","出生年月","政治面貌","担任职务","生源省市","综合测评名次","专业名次",
					"特长","申报等级","第几次获得桑麻奖学金","大学期间获奖情况","本人申请","班级辅导员意见","院系审核意见","桑麻奖学金评审委员会评审意见","备注"};
		}else{
			ColumnName = new String[]{"jxjmc","xm","xb","csrq","zzmm","xh","xymc","bjmc","zw",
					"xxjl","jxjmc","fdyyj","xyshyj","xxshyj","bz"};
			ColumnNameCN = new String[]{"奖学金名称","姓名","性别","出生年月","政治面貌","学号","学院","班级","担任职务",
					"本人申请","申请奖学金等级","班级推荐意见","学院意见","学校意见","备注"};
		}

		try {

			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	/**
	 * 奖学金批量删除
	 * 
	 * @param
	 * @param
	 * @return
	 */
	public String getAllDelList(String pks)
	throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from xsjxjb where xh||xn||jxjdm = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}
	/**
	 * 奖学金view
	 */
	public HashMap<String, String> jxj_view(String pk,String jxjcxzj){
		HashMap<String, String>  map = new HashMap<String, String> ();
		DAO dao      = DAO.getInstance();
//		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] colList = dao.getColumnName("select * from view_jxjzjlg where 1=2 ");
		String  sql = "select * from view_jxjzjlg where xh||xn||jxjdm = '" + pk + "'";
		HashMap<String, String>  map1 = dao.getMap(sql, new String[]{}, colList);

		if(map1 != null){
			for(int i=0;i<map1.size();i++){
				map.put(colList[i].toLowerCase(), map1.get(colList[i]));
			}
		}
		return map;
	}

	public boolean xjbjPlszSave(ZjlgPjpyModel myModel) throws Exception {
		// TODO 自动生成方法存根
		String xn = Base.getJxjsqxn();
		String szblS  = myModel.getSzbl();
		double szbl = 0.00;
		szbl = ((int) (Float.parseFloat(szblS) * 1000)) / 100000.00;
		String sql = "delete from zjlg_xjbjmeb where xn = ?";
		boolean update = dao.runUpdate(sql, new String[] {xn});
		if(update){
			sql = "insert into zjlg_xjbjmeb(xn,xydm,bjgs,szbl,me) select '"+xn+"' xn ,xydm,count(*),'"+szbl+"',floor(count(*)*"+szbl+") from view_njxyzybj a group by xydm";
			update = dao.runUpdate(sql, new String[] {});
		}
		return update;
	}

	public boolean saveXjbjMe(String pk, String me, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		boolean update = StandardOperation.update("zjlg_xjbjmeb", new String[]{"me"}, new String[]{me}, "xydm||xn", pk, request);
		return update;
	}

	public String getXjbjMe(String xn,String xydm){
		//所属学院先进班级名额
		String sql = "select nvl(me,'0')me from zjlg_xjbjmeb where xn = ? and xydm = ?";
		return dao.getOneRs(sql, new String[]{xn,xydm}, "me");
	}


	/**
	 * 荣誉称号审核信息查询
	 */
	public ArrayList<String[]> dao_xjbjDefault(XjbjModel model){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xn   = model.getXn();
		StringBuffer querry = new StringBuffer(" where 1=1 ");

		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
//		String[] colListCN = new String[]{"主键","学年","班级","年级","院系","申请时间","院系审核","学校审核"};
		String[] colList = new String[]{"pk","xn","bjmc","nj","xymc","sqsj","yxsh","xxsh"};
		String  sql = " select bjdm||xn pk,xn,bjmc,nj,xymc,sqsj,yxsh,xxsh from view_zjlg_xjbjxx  " +querry;
		result = dao.rsToVator(sql, new String[]{},colList);
		return result;
	}
	/**
	 * 先进班级信息增加保存
	 */
	public boolean dao_xjbjAddSave(XjbjModel  model) throws Exception{
		DAO dao = DAO.getInstance();
		boolean done = false;
		String bjdm   = model.getBjdm();
		String wmqsgs = model.getWmqsgs();
		String ajqsgs = model.getAjqsgs();
		String xn     = Base.getJxjsqxn();
		String bjqk   = model.getBjqk();
		String  pk    = "bjdm||xn";
		String sql    = "";	
		String sfyxbj = model.getSfyxbj();
		String tag = dao.getOneRs("select count(bjdm)cout from zjlg_xjbjb where "+pk+"=?",new String[]{bjdm+xn},"cout");
		if("0".equalsIgnoreCase(tag)){
			sql = " insert into zjlg_xjbjb(bjdm,xn,wmqsgs,ajqsgs,bjqk,sfyxbj,yxsh,xxsh) values(?,?,?,?,?,?,'通过','通过') ";
		}else{
			sql = " update zjlg_xjbjb set bjdm=?,xn=?,wmqsgs=?,ajqsgs=?,bjqk=?,sfyxbj=?,yxsh='通过',xxsh='通过' where "+pk+"='"+bjdm+xn+"' ";			  
		}
		done = dao.runUpdate(sql, new String[]{bjdm,xn,wmqsgs,ajqsgs,bjqk,sfyxbj});
		return done;
	}
	/**
	 * 先进班级信息修改结果保存
	 */
	public boolean dao_xjbjModiSave(XjbjModel  model,String pkValue) throws Exception{
		DAO dao = DAO.getInstance();
		boolean done = false;       
		String wmqsgs = model.getWmqsgs();
		String ajqsgs = model.getAjqsgs();
		String bjqk   = model.getBjqk();
		String sfyxbj = model.getSfyxbj();
		String  pk    = "bjdm||xn";
		String sql    = "";				
		sql = " update zjlg_xjbjb set wmqsgs=?,ajqsgs=?,bjqk=?,sfyxbj=? where "+pk+"='"+pkValue+"' ";			  		
		done = dao.runUpdate(sql, new String[]{wmqsgs,ajqsgs,bjqk,sfyxbj});
		return done;
	}
	/**
	 * 先进班级信息删除
	 */
	public boolean dao_xjbjDelete(String delPk) throws Exception{
		DAO dao = DAO.getInstance();	
		String pk = "bjdm||xn";
		String[] pkValueA = delPk.split("!!");		
		String[] delPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete zjlg_xjbjb  where "+pk+"= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(delPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * 先进班级审核信息查询
	 */
	public ArrayList<String[]> dao_xjbjCkDefault(XjbjModel model,String userType){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();

		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xn   = model.getXn();
		String yesNo = model.getYesNo();

		StringBuffer querry = new StringBuffer(" where 1=1 ");
		if("xy".equalsIgnoreCase(userType)){
			querry.append(Base.isNull(yesNo)?"":" and yxsh='"+yesNo+"' ");
		}else{
			querry.append(Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"'");
			querry.append(" and yxsh='通过' ");
		}		
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");		
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
//		String[] colListCN = new String[]{"主键","学年","班级","年级","院系","申请时间","院系审核","学校审核"};
		String[] colList = new String[]{"pk","xn","bjmc","nj","xymc","sqsj","yxsh","xxsh"};
		String  sql = " select bjdm||xn pk,xn,bjmc,nj,xymc,sqsj,yxsh,xxsh from view_zjlg_xjbjxx  " +querry;
		result = dao.rsToVator(sql, new String[]{},colList);
		return result;
	}
	/**
	 * 先进班级审核
	 */
	public boolean dao_xjbjCk(String pkVStr,String userType,String check) throws Exception{
		DAO dao = DAO.getInstance();	
		String pk = "bjdm||xn";
		String shType = "";
		shType = ("xy".equalsIgnoreCase(userType))?"yxsh":"xxsh";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"update zjlg_xjbjb set "+shType+"='"+check+"'  where "+pk+"= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}

	/**
	 * 先进班级审核班级数限定
	 * @param xn
	 * @param xydm
	 * @return
	 */
	public boolean xjbjBjsXd(String xn,String xydm,String pkVStr){
		String str = pkVStr.replaceAll("!!","','");
		str        = str.substring(2, str.length()-2);//将字符串中"!!"全部替换为"','"，却把第一个"',"和最后一个"',"去除。	
		String sql = "select count(a.bjdm)num from zjlg_xjbjb a,view_njxyzybj b where a.bjdm=b.bjdm and yxsh='通过' and xn=? and b.xydm=? ";
		String bjsPss = dao.getOneRs(sql,new String[]{xn,xydm},"num");//该学院已经通过的班级数
		String bjsXd  = getXjbjMe(xn,xydm);//该学院班级申请限定数
		String pk = "bjdm||xn";
		//选定审核的记录中未通过审核的记录数
		String chkBjsPss = dao.getOneRs("select count(bjdm)num from zjlg_xjbjb where "+pk+" in("+str+") and yxsh<>'通过' ",new String[]{},"num");//

		int bjsPssNum = Integer.parseInt(Base.isNull(bjsPss)?"0":bjsPss);
		int bjsXdNum  = Integer.parseInt(Base.isNull(bjsXd)?"0":bjsXd);
		int chkBjsPssNum = Integer.parseInt(Base.isNull(chkBjsPss)?"0":chkBjsPss);
		if((chkBjsPssNum+bjsPssNum)>bjsXdNum){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 奖学金申请审核
	 */
	public boolean dao_jxjSqCk(String pkVStr,String userType,String check,String shyj,String workFlowName,String checkName) throws Exception{
		DAO dao = DAO.getInstance();	
		String pk = "xh||xn||jxjdm";
		String shType = "";
		String shYjType = "";//审核意见类型
		if("xy".equalsIgnoreCase(userType)){
			shType = "xysh";
			shYjType = "xyshyj";
		}else{
			shType = "xxsh";
			shYjType = "xxshyj";
		}
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];	
		String[] istPkSql = new String[pkValueA.length];
		for (int i = 0; i < pkValueA.length; i++) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date = simpleDateFormat.format(new Date());
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"update xsjxjb set "+shType+"='"+check+"',"+shYjType+"='"+shyj+"'  where "+pk+"= '"+pkValueA[i]+"'";							
			istPkSql[i] = Base.isNull(pkValueA[i])?"":"insert into workFlowRecord(workFlowId,workFlowName,tableName,checkName,checkTime,status,yj) values('"+pkValueA[i]+"','"+workFlowName+"','xsjxjb','"+checkName+"','"+date+"','"+check+"','"+shyj+"')";							
			
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		int[] arrayInsert = dao.runBatch(istPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * 奖学金申请审核
	 */
	public boolean dao_audit(String pkVStr,String userType,String check,String shyj,String workFlowName,String checkName,String tableName) throws Exception{
		DAO dao = DAO.getInstance();	
		String pk = "xh||xn||jxjdm";
		String shType = "";
		String shYjType = "";//审核意见类型
		if("xy".equalsIgnoreCase(userType)){
			shType = "xysh";
			shYjType = "xyshyj";
		}else{
			shType = "xxsh";
			shYjType = "xxshyj";
		}
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];	
		String[] istPkSql = new String[pkValueA.length];
		for (int i = 0; i < pkValueA.length; i++) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date = simpleDateFormat.format(new Date());
			//updPkSql[i] = Base.isNull(pkValueA[i])?"":"update xsjxjb set "+shType+"='"+check+"',"+shYjType+"='"+shyj+"'  where "+pk+"= '"+pkValueA[i]+"'";							
			istPkSql[i] = Base.isNull(pkValueA[i])?"":"insert into workFlowRecord(workFlowId,workFlowName,tableName,checkName,checkTime,status,yj) values('"+pkValueA[i]+"','"+workFlowName+"','"+tableName+"','"+checkName+"','"+date+"','"+check+"','"+shyj+"')";							
			
		}              
		boolean doFlag = false;
		//int[] array = dao.runBatch(updPkSql);
		int[] arrayInsert = dao.runBatch(istPkSql);
		doFlag = dao.checkBatch(arrayInsert);   
		return doFlag;
	}
	/**
	 * 奖学金申请审核--退回
	 * author lyl
	 */
	public boolean dao_jxjBack(String pkVStr,String userType,String check,String thly,String workFlowName,String checkName,String tableName) throws Exception{

		DAO dao = DAO.getInstance();
		 String pk="";
		if(tableName.equals("xsjxjb")){
		    pk = "xh||xn||jxjdm";
		}
		else if(tableName.equals("zjlg_xsrychb")){
			pk="xh||xn||rychdm";
		}
		String shType = "";
		String shYjType = "";//审核意见类型
		if("xy".equalsIgnoreCase(userType)){
			shType = "xysh";
			shYjType = "xythly";
		}else{
			shType = "xxsh";
			shYjType = "xxthly";
		}
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];
		String[] istPkSql = new String[pkValueA.length];
		for (int i = 0; i < pkValueA.length; i++) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date = simpleDateFormat.format(new Date());
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"update "+tableName+" set "+shType+"='未审核',"+shYjType+"='"+thly+"',xysh='未审核'  where "+pk+"= '"+pkValueA[i]+"'";							
			istPkSql[i] = Base.isNull(pkValueA[i])?"":"insert into workFlowRecord(workFlowId,workFlowName,tableName,checkName,checkTime,status,yj) values('"+pkValueA[i]+"','"+workFlowName+"','"+tableName+"','"+checkName+"','"+date+"','"+check+"','"+thly+"')";							
				  
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		int[] arrayInsert = dao.runBatch(istPkSql);
		doFlag = dao.checkBatch(array);   
		
		return doFlag;
	}
	/**
	 * 流程追踪
	 * author lyl
	 */
	public ArrayList<String[]> dao_workFlowQuery(String pkVStr,String tableName) throws Exception{
		DAO dao = DAO.getInstance();	
		ArrayList<String[]> result = new ArrayList<String[]>();
		String sql="select workFlowName,checkName,checkTime,status,yj from workFlowRecord where workFlowId =?  and tableName=?";
		result = dao.rsToVator(sql, new String[]{pkVStr,tableName},new String[]{"workFlowName","checkName","checkTime","status","yj"});
		return result;
	}
	
	/**
	 * 根据奖学金主键获取xh，xn,jxjdm
	 */
	public HashMap<String, String> jxj_getxhxnjxjdm(String pkVStr) throws Exception{
		DAO dao = DAO.getInstance();	
		String sql = "select b.xh,b.xn,b.jxjdm,a.dycpf dycj,zycpf zycj,zhcpf zhszcpcjpm from view_zjlg_zhszcpf a,xsjxjb b " +
		"where a.xh=b.xh and b.xh||b.xn||b.jxjdm=?";
		HashMap<String, String> map = dao.getMap(sql, new String[]{pkVStr}, new String[]{"xh","xn","jxjdm"});
		return map;
	}

	public boolean rychcsh() throws Exception {
		String sql = "{call initBaseZjlgRychData()}";
		return dao.runProcuder(sql,new String[]{});
	}

	public boolean rychPlszSave(ZjlgPjpyModel myModel) throws Exception {
		// 荣誉称号批量设置
//		批量设置保存
		String xydm = myModel.getXydm();
		String zydm = myModel.getZydm();
		String bjdm = myModel.getBjdm();
		String rychdm = myModel.getRychdm();
		String szblS  = myModel.getSzbl();
		double szbl = 0.00;
		szbl = ((int) (Float.parseFloat(szblS) * 1000)) / 100000.00;
		String querry = "";
		if (!isNull(bjdm)) {
			querry = " and bmdm='" + bjdm + "' and bmlb='bjdm' ";
		}
		else if (!isNull(zydm)) {
			querry = " and bmdm='" + zydm + "' and bmlb='zydm' ";
		}else if(!isNull(xydm)){
			querry = " and bmdm='" + xydm + "' and bmlb='xydm' ";
		}else {
			querry = " and 1=1 ";
		}
		if (isNull(rychdm)) {
			querry += " and 1=1 ";
		} else {
			querry += " and rychdm='" + rychdm+ "' ";
		}
		String sql = "update zjlg_xyrychrs set rychbl='" + szbl
		+ "',rychrs=(case when cprs*" + szbl
		+ "<1 then '0' else (case when instr(cprs*" + szbl
		+ ",'.')>0 then substr(cprs*" + szbl + ",1,instr(cprs*" + szbl
		+ ",'.')-1) else to_char(cprs*" + szbl
		+ ") end) end) where xn=?" + querry;
		boolean update = dao.runUpdate(sql, new String[]{Base.getJxjsqxn()});
		if(update){
			String squerry = " and bmlb='zydm' and bmdm in (select distinct zydm from view_njxyzybj where xydm='"+xydm+"' and '"+zydm+"' = 'null')";
			String ssql = "update zjlg_xyrychrs set rychbl='" + szbl
			+ "',rychrs=(case when to_number(cprs)*" + szbl
			+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+szbl+")) end) where xn=? " + squerry;
			update = dao.runUpdate(ssql, new String[]{Base.getJxjsqxn()});
		}
		if(update){
			String squerry = " and bmlb='bjdm' and bmdm in (select distinct bjdm from view_njxyzybj where (('null'='"+zydm+"'and xydm='"+xydm+"') or zydm = '"+zydm+"'))";
			String ssql = "update zjlg_xyrychrs set rychbl='" + szbl
			+ "',rychrs=(case when to_number(cprs)*" + szbl
			+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+szbl+")) end) where xn=? " + squerry;
			update = dao.runUpdate(ssql, new String[]{Base.getJxjsqxn()});
		}
		return update;
	}

	public boolean saveRychRS(String pk, ZjlgPjpyModel myModel, HttpServletRequest request) throws Exception {
		// 修改荣誉称号人数
		String rychrs = myModel.getRychrs();
		boolean update = StandardOperation.update("zjlg_xyrychrs", new String[]{"rychrs"}, new String[]{rychrs}, "rychdm||bmdm||bmlb||xn", pk, request);
		return update;
	}

	public String getRychMe (String bmlb,String bmdm,String rychdm,String xn) throws Exception {
		// 获荣誉称号名额
		String sql = "select rychrs from zjlg_xyrychrs where bmlb = ? and bmdm = ? and rychdm = ? and xn = ?";
		String rychrs = dao.getOneRs(sql, new String[]{bmlb,bmdm,rychdm,xn}, "rychrs");
		return rychrs;
	}

	public boolean getJxjMe (String bmlb,String bmdm,String jxjdm,String xn) throws Exception {
		// 获奖学金名额
		boolean bool = false;
		StringBuffer query = new StringBuffer();
		query.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		query.append(Base.isNull(bmlb)?"":" and "+bmlb+"='"+bmdm+"' ");
		String sql = "select jxjrs from zjlg_xyjxjrs where bmlb = ? and bmdm = ? and jxjdm = ? and xn = ?";
		String rychrs = dao.getOneRs(sql, new String[]{bmlb,bmdm,jxjdm,xn}, "jxjrs");
		String sql1 = "select count(bjdm||xn) from zjlg_cpzfpb a where exists(select 1 from " +
		"(select distinct bjdm,a.xn from xsjxjb a,view_xsxxb b where a.xh=b.xh) b " +
		"where a.bjdm=b.bjdm "+query+")";
		String jjrs = dao.getOneRs(sql1, new String[]{jxjdm}, "rs");
//		System.out.println(Integer.parseInt(jjrs));
		if((Integer.parseInt(rychrs))>(Integer.parseInt(jjrs))){
			bool = true;
		}
		return bool;
	}
	
	public int getJxjrsxz (String bmlb,String bmdm,String jxjdm,String xn,String userType) throws Exception {
		// 获奖学金名额
//		boolean bool = false;
		StringBuffer query = new StringBuffer();
		query.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		query.append(Base.isNull(bmlb)?"":" and "+bmlb+"='"+bmdm+"' ");
		String sql = "select jxjrs jxjrs from zjlg_xyjxjrs where bmlb = ? and bmdm = ? and jxjdm = ? and xn = ?";
		String jxjrs = dao.getOneRs(sql, new String[]{bmlb,bmdm,jxjdm,xn}, "jxjrs");
		int jxjjme = 0;
		if(StringUtils.isNotNull(jxjrs)){
			jxjjme = Integer.parseInt(jxjrs);
		}
		return jxjjme;
	}
	public int getJxjrsxz1 (String bmlb,String bmdm,String jxjdm,String xn,String userType) throws Exception {
		// 获奖学金名额
//		boolean bool = false;
		StringBuffer query = new StringBuffer();
		//query.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		//query.append(Base.isNull(bmlb)?"":" and "+bmlb+"='"+bmdm+"' ");
		if("xy".equals(userType)){
			query.append(" and xysh='通过' ");
		}else if("xx".equals(userType) || "admin".equals(userType)){
			query.append(" and xxsh='通过' ");
		}else if("admin".equals(userType)){
			query.append(" and xxsh='通过' ");
		}
		String sql = "select count(xh) jxjrs from xsjxjb a,zjlg_xyjxjrs b where a.jxjdm=b.jxjdm and b.bmlb='"+bmlb+"' and b.bmdm='"+bmdm+"' and b.jxjdm='"+jxjdm+"' and b.xn='"+xn+"'"+query;
//		System.out.println(sql);
		String jxjrs = dao.getOneRs(sql, new String[]{}, "jxjrs");
		
		return Integer.parseInt(jxjrs);
	}
	public String getYycj (String xh,String xn) throws Exception {
		// 获得英语成绩
		StringBuffer query = new StringBuffer();
		query.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		query.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		String sql = "select max(cj) cj from xsdjksb a where a.xh=? and a.djksmc like '%CET4%' or a.djksmc like '%CET6%'";
		String yycj = dao.getOneRs(sql, new String[]{xh}, "cj");
		return yycj;
	}
	public String getIsbys (String xh) throws Exception {
		// 是否是毕业生
		StringBuffer query = new StringBuffer();
		query.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		String sql = "select name xm from jygl_xsjbxxb where xsxh=?";
		String xm = dao.getOneRs(sql, new String[]{xh}, "xm");
		return xm;
	}
	public boolean getIsjxjjd (String xh,String xn,String jxjdm,String usertype) throws Exception {
		// 是否奖学金间的
		StringBuffer query = new StringBuffer();
//		query.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		if("xy".equals(usertype)){
			query.append(" and xysh='通过' ");
		}else if("xx".equals(usertype)){
			query.append(" and xxsh='通过'");
		}else if("admin".equals(usertype)){
			query.append(" and xxsh='通过'");
		}
		
		String sql ="";
		if("00054".equals(jxjdm)){
			sql = "select * from jxjdmb a,xsjxjb b where a.jxjdm=b.jxjdm and "
				+"xh='"+xh+"' and xn='"+xn+"' and b.jxjdm='"+jxjdm+"' "+query;
		}else{
			sql = "select * from jxjdmb a,xsjxjb b where a.jxjdm=b.jxjdm and "
				+"xh='"+xh+"' and xn='"+xn+"' and jxjmc like '%"+jxjdm+"%'"+query;
		}
		String xm = dao.getOneRs(sql, new String[]{}, "jxjdm");
		
		return StringUtils.isNull(xm);
	}
	 /**
     * jxj代码获取路径
     * @param 
     * @param 
     * @return
     */
	public HashMap<String,String> dao_getJxjlj(String jxjdm){
		DAO dao = DAO.getInstance();
		if(StringUtils.isNull(jxjdm)){
			jxjdm = "00011";
		}
		String sql = "select bblj,sqbdlj,shbdlj,sqjgbdlj from pjpy_bbdyljb a,jxjdmb b where a.xmdm=b.bdbblxdm and jxjdm=?";
		String[] colList = {"bblj","sqbdlj","shbdlj","sqjgbdlj"};
		return dao.getMap(sql,new String[]{jxjdm},colList);
	}
	public ArrayList<HashMap<String, String>> getCfqk (String xh,String xn,String xq) throws Exception {
		// 处罚情况
		//String sql = "select sj,cfnr from xscfb where xh=? and xn=? and xq=?";
		String sql = "select xh,xn,(select cflbmc from cflbdmb b where a.cflb=b.cflbdm) cflb,(select cfyymc from cfyydmb b where a.cfyy=b.cfyydm) cfyy from wjcfb a where xh=? and xn=?";
		//String cfnr = dao.getOneRs(sql, new String[]{xh,xn,xq}, "cfnr");
		ArrayList<HashMap<String, String>>  rs = dao.getArrayList(sql,new String[]{xh,xn}, new String[]{"xh","xn","cflb","cfyy"});
		return rs;
	}
	/**获取班级平均分
	 * @author luo
	 */
	public String getbjpjf(String xn,String bjdm ) {
		String lyb = dao.getOneRs("select lyb from jxjtjzdb where zdmc='avgcj' and szlx='xjbj'",new String[]{}, "lyb");
		String sql = "";
		String avgcj = "0";
		if(!Base.isNull(lyb)){
			sql = "select trunc(avgcj,2)avgcj from "+lyb;
			avgcj = dao.getOneRs(sql,new String[]{xn,bjdm},"avgcj");	
		}		
		return Base.isNull(avgcj)?"0":avgcj;
	}
	/**获取班级不及格率
	 * @author luo
	 */
	public String getbjbjdl(String xn,String bjdm ){
		String lyb = dao.getOneRs("select lyb from jxjtjzdb where zdmc='bjgl' and szlx='xjbj'",new String[]{}, "lyb");		
		String bjgl="0";
		if(!Base.isNull(lyb)){
			String sql = "select trunc(bjgl,2)bjgl from "+lyb;
			bjgl = dao.getOneRs(sql,new String[]{xn,bjdm},"bjgl");
		}			
		return Base.isNull(bjgl)?"0":bjgl+"%";
	}
	/**
	 * 荣誉称号审核班级数限定
	 * @param rychdm 荣誉称号代码
	 * @param xn 学年
	 * @param xydm 学院代码
	 * @param shfs 审核方式
	 * @param zydm 专业代码
	 * @param bjdm 班级代码
	 * @param xh 学号
	 * @param pkVStr 审核记录主键串
	 * @return
	 * @throws Exception
	 */
	public boolean rychCkRsXd(String rychdm,String xn,String xydm,String shfs,String zydm,String bjdm,String pkVStr) throws Exception{
		String str = pkVStr.replaceAll("!!","','");
		str        = str.substring(2, str.length()-2);//将字符串中"!!"全部替换为"','"，却把第一个"',"和最后一个"',"去除。	
		String sql = "";
		String ygtRs= "";//已通过申请人数
		String rsXd = "";//限制人数
		if("bjfs".equalsIgnoreCase(shfs)){//班级方式
			sql = "select count(xh)num from view_zjlg_xsrychxx  where  xysh='通过' and xn=? and bjdm=? and rychdm=?  ";
			ygtRs = dao.getOneRs(sql,new String[]{xn,bjdm,rychdm},"num");//该学院、专业已经通过的班级数	
			rsXd = getRychMe("bjdm", bjdm, rychdm, xn);//该班级荣誉称号申请限定数			
		}if("zyfs".equalsIgnoreCase(shfs)){//专业方式
			sql = "select count(xh)num from view_zjlg_xsrychxx  where  xysh='通过' and xn=? and zydm=? and rychdm=?  ";
			ygtRs = dao.getOneRs(sql,new String[]{xn,zydm,rychdm},"num");//该学院、专业已经通过的班级数		
			rsXd = getRychMe("zydm", zydm, rychdm, xn);//该学院、专业荣誉称号申请限定数
		}else{
			sql = "select count(xh)num from view_zjlg_xsrychxx  where  xysh='通过' and xn=? and xydm=? and rychdm=? ";
			ygtRs = dao.getOneRs(sql,new String[]{xn,xydm,rychdm},"num");//该学院已经通过的班级数	
			rsXd = getRychMe("xydm", xydm, rychdm, xn);//该学院荣誉称号申请限定数
		}
//		if("xyfs".equalsIgnoreCase(shfs)){//院系方式
//			sql = "select count(xh)num from view_zjlg_xsrychxx  where  xysh='通过' and xn=? and xydm=? and rychdm=? ";
//			ygtRs = dao.getOneRs(sql,new String[]{xn,xydm,rychdm},"num");//该学院已经通过的班级数	
//			rsXd = getRychMe("xydm", xydm, rychdm, xn);//该学院荣誉称号申请限定数
//		}else if("zyfs".equalsIgnoreCase(shfs)){//专业方式
//			sql = "select count(xh)num from view_zjlg_xsrychxx  where  xysh='通过' and xn=? and zydm=? and rychdm=?  ";
//			ygtRs = dao.getOneRs(sql,new String[]{xn,zydm,rychdm},"num");//该学院、专业已经通过的班级数		
//			rsXd = getRychMe("zydm", zydm, rychdm, xn);//该学院、专业荣誉称号申请限定数
//		}else{//班级方式
//			sql = "select count(xh)num from view_zjlg_xsrychxx  where  xysh='通过' and xn=? and bjdm=? and rychdm=?  ";
//			ygtRs = dao.getOneRs(sql,new String[]{xn,bjdm,rychdm},"num");//该学院、专业已经通过的班级数	
//			rsXd = getRychMe("bjdm", bjdm, rychdm, xn);//该班级荣誉称号申请限定数
//		}		 		
		String pk = "xh||xn||rychdm";

		//选定审核的记录中未通过审核的记录数
		String chkRycPssRs = dao.getOneRs("select count(xh)num from view_zjlg_xsrychxx where "+pk+" in("+str+") and xysh<>'通过' ",new String[]{},"num");//

		int ygtRsNum = Integer.parseInt(Base.isNull(ygtRs)?"0":ygtRs);
		int rsXdNum  = Integer.parseInt(Base.isNull(rsXd)?"0":rsXd);
		int chkRycPssRsNum = Integer.parseInt(Base.isNull(chkRycPssRs)?"0":chkRycPssRs);
		if((chkRycPssRsNum+ygtRsNum)>rsXdNum){
			return false;
		}else{
			return true;
		}
	}
	public boolean delCpz(HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		String xn  = Base.getJxjsqxn();
		String cpzdm = DealString.toGBK(request.getParameter("cpzdm"));
		boolean update = StandardOperation.delete("zjlg_cpzszb", "xn||cpzdm", xn+cpzdm, request);
		if(update){
			update = StandardOperation.delete("zjlg_cpzfpb", "xn||cpzdm", xn+cpzdm, request);
		}
		return update;
	} 
	 /**
     * 获取报表路径
     * @param rychdm 荣誉称号代码
     * @return
     */
	public HashMap<String,String> dao_getRychBb(String rychdm){
		DAO dao = DAO.getInstance();		
		String sql = "select bblj,sqbdlj,shbdlj,sqjgbdlj from pjpy_bbdyljb a,rychdmb b where a.xmdm=b.bdbblxdm and xmlb='rych' and b.rychdm=?  ";
		String[] colList = {"bblj","sqbdlj","shbdlj","sqjgbdlj"};
		return dao.getMap(sql,new String[]{rychdm},colList);
	}
	/**
	 * 学生奖学金增加保存
	 */
	public boolean dao_jxjiscftj(ZjlgPjpyModel  model) throws Exception{
		DAO dao       = DAO.getInstance();
		boolean done = false;
		String xh     = model.getXh();
		String xn     = Base.getJxjsqxn();
		String jxjdm  = model.getJxjdm();
		ZjlgPjpyUnit.formToGBK(model);
		String  pk    = "xh||xn||jxjdm";
		
		String num = dao.getOneRs("select count(xh) cout from xsjxjb where "+pk+"=?",new String[]{xh+xn+jxjdm},"cout");
		if("0".equalsIgnoreCase(num)){
			done = true;
		}else{
			done = false;
		}
		return done;
	}
	public boolean createZXFdytjb(String xn,String xy,String zy,String bj) throws Exception{
		DAO dao = DAO.getInstance();
		boolean run = false;
		String sqlT = "{call zjlg_bbdy(?,?,?,?,?)}";
		run = dao.runProcuder(sqlT, new String[] {"",xn,xy,zy,bj});
		return run;
	}
	 /**
     * 是否有综合测评分
     * @param 
     * @return
     */
	public boolean dao_getiszhcpf(String xh){
		DAO dao = DAO.getInstance();		
		String sql = " select xm from view_zjlg_zhszcpf where xh=?";
		String[] colList = {"xm"};
		//dao.getMap(sql,new String[]{xh},colList);
		String[] isxm = dao.getOneRs(sql,new String[]{xh},colList);
		
		return StringUtils.isArrayNotNull(isxm);
	}
	 /**
     * 通过学号取得参评组代码
     * @param 
     * @return
     */
	public String dao_getCpzdmForXh(String xh,String xn){
		DAO dao = DAO.getInstance();		
		String sql = "select cpzdm from view_xsxxb a,zjlg_cpzfpb b where a.bjdm=b.bjdm and xh=? and xn=?";
		String[] colList = {"cpzdm"};
		//dao.getMap(sql,new String[]{xh},colList);
		String cpzdm = "";
		String[] cpzdmz = dao.getOneRs(sql,new String[]{xh,xn},colList);
		cpzdm = (StringUtils.isArrayNotNull(cpzdmz))?cpzdmz[0]:"";
		return cpzdm;
	}
	/**
	 * 获取某生所若奖学金列表
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getRhJxjList(String xh){
        DAO dao = DAO.getInstance();
        return dao.getList("select xn,jxjmc from view_jxjzjlg where xh=? and xxsh='通过'order by xn ",new String[]{xh},new String[]{"xn","jxjmc"});
	}
	/**
	 * 获取荣誉称号已通过人数
	 */
	public String getRychPssRs(String rychdm,String xn,String xydm,String zydm,String bjdm){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		String num = "";
		sql.append(" select count(xh)num from view_zjlg_xsrychxx  where  rychdm=? and xn=? and xysh='通过' and xxsh='通过' ");
		num = dao.getOneRs(sql.toString(), new String[]{rychdm,xn},"num");//全校通过人数
		if(!Base.isNull(xydm)){//学院通过人数
			sql.append(" and xydm=?");
			num = dao.getOneRs(sql.toString(), new String[]{rychdm,xn,xydm},"num");
		}
		if(!Base.isNull(zydm)){//专业通过人数
			sql.append(" and zydm=?");
			num = dao.getOneRs(sql.toString(), new String[]{rychdm,xn,zydm},"num");
		}
		if(!Base.isNull(bjdm)){//班级班级人数
			sql.append(" and bjdm=?");
			num = dao.getOneRs(sql.toString(), new String[]{rychdm,xn,bjdm},"num");
		}		
		return num;
	}
	
	/**
	 * 评奖人数超额验证
	 */
	public String getJxjRstxForXydm(String xydm,String xn,String jxjdm,String yzlx){
		DAO dao = DAO.getInstance();
		if(jxjdm!=null&&!jxjdm.equalsIgnoreCase("")){
		String sql = "select jxjrs from zjlg_xyjxjrs where bmlb = ? and bmdm = ? and xn =? and jxjdm=?";
		String xyrs  = dao.getOneRs(sql, new String[]{"xydm",xydm,xn,jxjdm}, "jxjrs");
		xyrs = StringUtils.isNotNull(xyrs) ? xyrs : "0";
		if(yzlx.equalsIgnoreCase("cpzdm")){
			sql = "select sum(jxjrs) jxjrs from zjlg_xyjxjrs where bmlb = 'cpzdm' and xn =? and jxjdm=? and exists (select 1 from zjlg_cpzszb a where a.xydm = ? and a.xn = ? and a.cpzdm = bmdm)";
			String xyjsrs  = dao.getOneRs(sql, new String[]{xn,jxjdm,xydm,xn}, "jxjrs");
			if(Integer.parseInt(xyjsrs)>Integer.parseInt(xyrs)){
				String cers  = ((Integer)(Integer.parseInt(xyjsrs)-Integer.parseInt(xyrs))).toString();
				String message = "学校设置学院为"+xyrs+"人,学院设置参评组总人数为"+xyjsrs+"人,人数超额"+cers+"人!";
				return message;
			}else{
				return null;
			}
		}else{
			sql = "select sum(jxjrs) jxjrs from zjlg_xyjxjrs where bmlb = 'bjdm' and xn =? and jxjdm=? and exists (select 1 from view_njxyzybj a where a.xydm = ? and a.bjdm = bmdm)";
			String xyjsrs  = dao.getOneRs(sql, new String[]{xn,jxjdm,xydm}, "jxjrs");
			if(Integer.parseInt(xyjsrs)>Integer.parseInt(xyrs)){
				String cers  = ((Integer)(Integer.parseInt(xyjsrs)-Integer.parseInt(xyrs))).toString();
				String message = "学校设置学院为"+xyrs+"人,学院设置班级总人数为"+xyjsrs+"人,人数超额"+cers+"人!";
				return message;
			}else{
				return null;
			}
		}
		}else{
			return null;
		}
	}

	public List<HashMap<String, String>> getPjfsList() {
		// TODO 自动生成方法存根
		String xn = Base.getJxjsqxn();
		String sql = "select a.nj,b.pjfs pjfsz from (select distinct nj from bks_bjdm) a left join (select nj,pjfs from zjlg_pjfssdb b where xn = ?) b on a.nj=b.nj order by nj";
		return dao.getList(sql, new String[]{xn}, new String[]{"nj","pjfsz"});
	}

	public boolean updatePjfs(ZjlgPjpyModel myModel) throws Exception {
		// TODO 自动生成方法存根
		String xn = Base.getJxjsqxn();
		String [] njz = myModel.getNjz();
		String [] pjfsz = myModel.getPjfsz();
		String [] sqlArr = new String[njz.length];
		String sql = "delete from zjlg_pjfssdb where xn = ?";
		boolean updated = dao.runUpdate(sql, new String []{xn});
		if(updated){
			for(int i=0;i<njz.length;i++ ){
				if(pjfsz[i]!=null&&!pjfsz[i].equalsIgnoreCase("")){
				sqlArr[i]="insert into zjlg_pjfssdb values ('"+xn+"','"+njz[i]+"','"+pjfsz[i]+"')";
				}
			}
		}
		dao.runBatch(sqlArr);
		return updated;
	}
	
	
	
	
	
	
	
	
	
	
	 /**
     * 通过学号、学年取相关体育成绩
     * @param 
     * @return
     */
	public String dao_getTyCj(String xh,String xn){
		DAO dao = DAO.getInstance();		
		String sql = "select tycpf  from view_zjlg_zhszcpf where xh=? and xn=?";				
		String tycj = dao.getOneRs(sql,new String[]{xh,xn},"tycpf");
		tycj = (Base.isNull(tycj))?"0.00":tycj;
		return tycj;
	}
	public List<HashMap<String, String>> getJsjYyDjList(String xn,String xh)
	throws SQLException {
		String sql = "select xn ,xq,xh,xm,djksmc,ksrq,cj from xsdjksb where xh=? and xn=?";	
		List<HashMap<String, String>> rsList = dao.getArrayList(sql,
				new String[] {xh,xn}, new String[] { "xn", "xq", "xh", "xm",
				"djksmc", "ksrq", "cj" });
		return rsList;
	}
	 /**
     * 通过学号、学年取相关体育成绩
     * @param 
     * @return
     */
	public String dao_getZjlgDyCj(String xh,String xn){
		DAO dao = DAO.getInstance();		
		String sql = "select zpf from zjlg_zpf where xh=? and xn=?";				
		String dycj = dao.getOneRs(sql,new String[]{xh,xn},"zpf");
		dycj = (Base.isNull(dycj))?"0.00":dycj;
		return dycj;
	}
	/**
	 * @author luo
	 * @describe 体育分条件
	 */
	public boolean tyfTj(String xn, String szlx,String jxjdm, String tyf) {

		boolean flg = false;
		String sql = "select * from zjlg_pjpy_tjsz where xn = ? and szlx = ? and tjzd = 'tycpf'";
		if(!Base.isNull(jxjdm)){
			sql += " and jxjdm = '" + jxjdm + "'";
		}
		HashMap<String, String> map = dao.getMap(sql,
				new String[] { xn, szlx }, new String[] { "tjlx", "tjz" });

		if (map.size() == 0) {
			return true;
		}

		String tjlx = map.get("tjlx");
		String tjz = map.get("tjz");
		float tj = Float.parseFloat(tjz);
		float tycpf =  Float.parseFloat(tyf);

		if (">".equalsIgnoreCase(tjlx)) {
			if (tycpf > tj) {
				return true;
			}
		} else if (">=".equalsIgnoreCase(tjlx)) {
			if (tycpf >= tj) {
				return true;
			}
		} else if ("<".equalsIgnoreCase(tjlx)) {
			if (tycpf < tj) {
				return true;
			}
		} else if ("<=".equalsIgnoreCase(tjlx)) {
			if (tycpf <= tj) {
				return true;
			}
		} else if ("=".equalsIgnoreCase(tjlx)) {
			if (tycpf == tj) {
				return true;
			}
		}
		return flg;
	}
	
	 /**
     * 获取部门申请奖学金情况
     * @param 
     * @return
     */
	public List<HashMap<String,String>> getJxjSqQk(String bmlb,String xydm,String bmdm,String jxjdm,String xn){
		DAO dao = DAO.getInstance();		

		StringBuilder sql=new StringBuilder();
		
		sql.append(" select bmdm,jxjrs,sqrs,(jxjrs-sqrs)ksqrs from( ");
		sql.append(" select a.bmdm,nvl(a.jxjrs,0)jxjrs,nvl(b.sqrs,0)sqrs from  ");
		sql.append(" (select bmdm,jxjrs jxjrs ");
		sql.append("  from zjlg_xyjxjrs ");
		sql.append(" where (bmlb='xydm' and bmdm = '"+xydm+"' ");
		
		if("cpzdm".equalsIgnoreCase(bmlb)){
			sql.append(" or  bmlb ='cpzdm' and bmdm = '"+bmdm+"' ");
		}		
				
		sql.append("  ) and jxjdm = '"+jxjdm+"' ");
		sql.append("   and xn = '"+xn+"')a ");
		sql.append(" left join  ");
		sql.append(" (select count(xh) sqrs,bmdm ");
		sql.append("   from view_xsjxjb a, zjlg_xyjxjrs b ");
		sql.append("  where a.jxjdm = b.jxjdm ");
		sql.append("   and (b.bmlb = 'xydm' and b.bmdm ='"+xydm+"' ");
		
		if("cpzdm".equalsIgnoreCase(bmlb)){
			sql.append(" or exists (select 1 ");
			sql.append(" from zjlg_cpzfpb c ");
			sql.append(" where cpzdm = '"+bmdm+"' ");
			sql.append(" and xn = '"+xn+"' ");
			sql.append(" and a.bjdm = c.bjdm) ");
	                           
		}	
		
		sql.append("  ) and b.jxjdm ='"+jxjdm+"' ");
		sql.append("  and b.xn =  '"+xn+"' ");
		sql.append("  and xysh = '通过' ");
		sql.append("  group by bmdm)b on a.bmdm=b.bmdm)");
		
		System.out.println(sql);
		return dao.getList(sql.toString(), new String[]{}, new String[]{"jxjrs","sqrs","ksqrs"});
	}
	
}
