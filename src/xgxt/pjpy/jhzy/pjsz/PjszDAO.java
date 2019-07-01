package xgxt.pjpy.jhzy.pjsz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.jhzy.JhzyPjpyDAO;
import xgxt.utils.String.StringUtils;

public class PjszDAO {
	DAO dao = DAO.getInstance();

	String sql;
	String inputSQL[];
	String outputSQL[];

	// 根据部门代码返回辅导员List
	public List getFdyList(String jsbmdm) {
		sql = "select zgh,xm,xb from view_fdyxx where bmdm = ? order by zgh";
		inputSQL = new String[] { jsbmdm };
		outputSQL = new String[] { "zgh", "xm", "xb" };
		return dao.getList(sql, inputSQL, outputSQL);
	}

	public List getZyList(String nj, String bmdm) {
		sql = "select distinct a.zydm,a.zymc from view_njxyzybj a where a.nj like ? and"
				+ " a.xydm = ?  and not exists (select 1 from "
				+ "dzzbsjhfb b where a.zydm = b.zydm)";
		inputSQL = new String[] { nj, bmdm };
		outputSQL = new String[] { "zydm", "zymc" };
		return dao.getList(sql, inputSQL, outputSQL);
	}

	public List getFdyZyList(String zgh) {
		sql = "select distinct a.zydm,a.zymc from view_njxyzybj a where exists (select 1 from dzzbsjhfb b where b.zgh=? and b.zydm=a.zydm)";
		inputSQL = new String[] { zgh };
		outputSQL = new String[] { "zydm", "zymc" };
		return dao.getList(sql, inputSQL, outputSQL);
	}

	public HashMap<String, String> getFdyInfo(String zgh, String jsbmdm) {
		sql = "select * from view_fdyxx where zgh=? and bmdm like ?";
		inputSQL = new String[] { zgh, jsbmdm };
		outputSQL = new String[] { "zgh", "xm", "xb", "lxdh", "zwmc", "zyzz",
				"bmmc" };
		return dao.getMap(sql, inputSQL, outputSQL);
	}

	/**
	 * @author
	 * @throws Exception
	 * @describe 删除条件
	 */
	public boolean delTj(String pk, HttpServletRequest request)
			throws Exception {
		boolean flg = StandardOperation.delete("jhzy_pjpy_tjsz",
				"xn||xq||szlx||jxjdm||tjzd", pk, request);
		return flg;

	}

	/**
	 * @author
	 * @describe 获得条件
	 */
	public List<HashMap<String, String>> getTjList(PjszModel model,
			String szlx, String[] colList) throws SQLException {
		StringBuffer query = new StringBuffer();
		String xn = DealString.toGBK(model.getXn());
		String xq = model.getXq();
		String rychdm = model.getRychdm();
		String jxjdm = model.getJxjdm();
		String cjlx = model.getCjlx();
//		String zdm = model.getZdm();
//		String val = model.getVal();
//		String tj = model.getTj();
		query.append(Base.isNull(cjlx) ? "" : " and szlx='" + cjlx + "' ");
		query.append(Base.isNull(xn) ? "" : " and xn='" + xn + "' ");
		if ("jxj".equals(cjlx)) {
			query.append(Base.isNull(jxjdm) ? "" : " and jxjdm='" + jxjdm
					+ "' ");
			query.append(Base.isNull(xq) ? "" : " and xq='" + xq + "' ");
		} else if ("rych".equals(cjlx)) {
			query.append(Base.isNull(rychdm) ? "" : " and jxjdm='" + rychdm
					+ "' ");
		}
		// query.append(Base.isNull(zdm)?"":" and tjzd='"+zdm+"' ");
		// query.append(Base.isNull(val)?"":" and tjz='"+val+"' ");
		// query.append(Base.isNull(tj)?"":" and tjlx='"+tj+"' ");

		String sql = "select xn||xq||szlx||jxjdm||tjzd pk,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,szlx,"
				+ "case when szlx='jxj' then (select jxjmc from jxjdmb b where a.jxjdm = b.jxjdm) "
				+ "when szlx='rych' then (select rychmc from rychdmb b where a.jxjdm = b.rychdm) "
				+ "end jxjdm,tjzd,tjmc,tjlx,tjz from jhzy_pjpy_tjsz a where 1=1"
				+ query;
		List<HashMap<String, String>> rsList = dao.getArrayList(sql,
				new String[] {}, colList);

		return rsList;
	}

	/**
	 * @author luo
	 * @describe 获得条件字段列表
	 */
	public List<HashMap<String, String>> getZdList() throws SQLException {
		String sql = "select * from jxjtjzdb";
		return dao.getList(sql, new String[] {},
				new String[] { "zdmc", "zdsm" });

	}

	/**
	 * 获取荣誉称号列表
	 */
	public List<HashMap<String, String>> getRychList() {
		DAO dao = DAO.getInstance();
		String sql = "select rychdm dm, rychmc mc from rychdmb";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}

	/**
	 * 奖学金列表
	 * 
	 * @param jxjlbdm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlbdm)
			throws Exception {
		return dao.getList("select jxjdm,jxjmc from jxjdmb", new String[] {},
				new String[] { "jxjdm", "jxjmc" });
	}

	/**
	 * @author
	 * @throws Exception
	 * @describe 保存条件设置
	 */
	public boolean saveTjsz(PjszModel model, HttpServletRequest request)
			throws Exception {

		StringBuffer buf = new StringBuffer();

		String xn = model.getXn();
		String xq = model.getXq();
		String cjlx = model.getCjlx();// 设置类型
		String jxjdm = "";
		String pk = "xn||szlx||jxjdm||tjzd";
		if ("rych".equals(cjlx)) {
			jxjdm = model.getRychdm();//
			xq = "";
		} else {
			jxjdm = model.getJxjdm();
		}
		if (StringUtils.isNotNull(xq)) {
			pk = "xn||xq||szlx||jxjdm||tjzd";
		}
		String zdm = model.getZdm();//
		String zdmval = model.getZdmval();
		String tjlx = model.getTj();
		String val = model.getVal();

		buf.append(xn);
		if (StringUtils.isNotNull(xq)) {
			buf.append(xq);
		}
		buf.append(cjlx).append(jxjdm).append(zdm).toString();
		if ("bjgkm".equals(zdm) || "cf".equals(zdm)) {
			tjlx = "=";
			val = "是";
		}
		boolean flg = StandardOperation.delete("jhzy_pjpy_tjsz", pk, buf
				.toString(), request);
		if (flg) {
			flg = StandardOperation.insert("jhzy_pjpy_tjsz",
					new String[] { "xn", "xq", "szlx", "jxjdm", "tjzd", "tjmc",
							"tjlx", "tjz" }, new String[] { xn, xq, cjlx,
							jxjdm, zdm, zdmval, tjlx, val }, request);
		}
		// String a =getZybfb( "2007-2008", "3051011025","1011");
		return flg;
	}

	/**
	 * @author
	 * @throws Exception
	 * @describe 是否符合条件 @ xh(学号) @ xn(学年) @ xq(学期) @ szlx(荣誉称号：rych；奖学金：jxj；) @
	 *           dm(如果是奖学金，传奖学金代码，如果是荣誉称号，传荣誉称号代码) @
	 *           tjzd(综合分:zhf,德育分:dyf,智育分:zyf,体育分:tyf,不及格科目:bjgkm,处分:cf
	 *           zhpm,德育分:dypm,智育分:zypm,体育分:typm,)
	 * 
	 */
	public boolean isTjsz(String xh, String xn, String xq, String cjlx,
			String jxjdm, String tjzd) throws SQLException {
		boolean istj = false;
		JhzyPjpyDAO fsdao = new JhzyPjpyDAO();
		StringBuffer query = new StringBuffer();
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = "";
		String sql = "";
		String val = "0";

		query.append(xn).append(xq).append(cjlx).append(jxjdm).append(tjzd);
		pk = query.toString();

		List<HashMap<String, String>> fsmap = fsdao.getFzPmList(xh, xn, xq);
		if(fsmap == null || fsmap.size()<1){
			return false;
		}
		sql = "select tjz,tjlx from jhzy_pjpy_tjsz a where xn||xq||szlx||jxjdm||tjzd=?";
		map = dao.getMap(sql, new String[] { pk },new String[] { "tjlx", "tjz" });
		if ("bjgkm".equals(tjzd)) {
			StringBuffer sqlcj = new StringBuffer(
					"select cj from cjb where xh=? and xn=?");
			if ("jxj".equals(cjlx)) {
				sqlcj.append(" and xq=").append(xq);
			}
			map = dao.getMap(sqlcj.toString(), new String[] { xh,
					xn }, new String[] {});
			if (map.size() < 1) {
				istj = true;
			}
		}
		if ("cf".equals(tjzd)) {
			StringBuffer sqlcj = new StringBuffer("select * from wjcfb where xh=? and xn=?");
			if ("jxj".equals(cjlx)) {
				sqlcj.append(" and xq=").append(xq);
			}
			map = dao.getMap(sqlcj.toString(), new String[] { xh,xn }, new String[] {});
			if (map.size() < 1) {
				istj = true;
			}
		}
		boolean ifhasnext = true;
//		int i = 0;
		for (Iterator<HashMap<String, String>> it = fsmap.iterator(); it.hasNext();) {
			if (fsmap != null && fsmap.size() > 0) {
				// val = fsmap.get(tjzd);
				val = it.next().get(tjzd);
			}
			if (ifhasnext) {
				if ("zhf".equals(tjzd) || "dyf".equals(tjzd)
						|| "zyf".equals(tjzd) || "tyf".equals(tjzd)) {
					if (map != null && map.size() > 0) {
						String tjlx = map.get("tjlx");
						String tjz = map.get("tjz");
						float valint = StringUtils.isNull(val) ? 0 : Float.parseFloat(val);
						float tjzint = StringUtils.isNull(tjz) ? 0 : Float.parseFloat(tjz);
						if (">".equalsIgnoreCase(tjlx)) {
							if (valint > tjzint) {
								istj = true;
							}
						} else if (">=".equalsIgnoreCase(tjlx)) {
							if (valint >= tjzint) {
								istj = true;
							}
						} else if ("<".equalsIgnoreCase(tjlx)) {
							if (valint < tjzint) {
								istj = true;
							}
						} else if ("<=".equalsIgnoreCase(tjlx)) {
							if (valint <= tjzint) {
								istj = true;
							}
						} else if ("=".equalsIgnoreCase(tjlx)) {
							if (valint == tjzint) {
								istj = true;
							}
						}
					}
				} else if ("zhpm".equals(tjzd) || "dypm".equals(tjzd)
						|| "zypm".equals(tjzd) || "typm".equals(tjzd)) {
					if (map != null && map.size() > 0) {
						String tjlx = map.get("tjlx");
						String tjz = map.get("tjz");
						int bjrs = 0;
						if (fsmap != null && fsmap.size() > 0) {
							// val = fsmap.get(tjzd);
//							val = it.next().get(tjzd);
//							
//							HashMap<String, String> itmap = it.next();
							bjrs = StringUtils.isNull(fsmap.get(0).get("num"))?0:Integer.parseInt(fsmap.get(0).get("num"));
//							bjrs = StringUtils.isNull(itmap.get("num")) ? 0
//									: Integer.parseInt(itmap.get("num"));
						}
						float valint = StringUtils.isNull(val) ? 0 : Float
								.parseFloat(val);
						float tjzint = (StringUtils.isNull(tjz) ? 0 : Float
								.parseFloat(tjz))
								* bjrs / 100;
						if (">".equalsIgnoreCase(tjlx)) {
							if (valint > tjzint) {
								istj = true;
							}
						} else if (">=".equalsIgnoreCase(tjlx)) {
							if (valint >= tjzint) {
								istj = true;
							}
						} else if ("<".equalsIgnoreCase(tjlx)) {
							if (valint < tjzint) {
								istj = true;
							}
						} else if ("<=".equalsIgnoreCase(tjlx)) {
							if (valint <= tjzint) {
								istj = true;
							}
						} else if ("=".equalsIgnoreCase(tjlx)) {
							if (valint == tjzint) {
								istj = true;
							}
						}
					}
				}
				ifhasnext = istj;
			}
		}
		return istj;
	}

	/**
	 * @author
	 * @throws SQLException
	 * @describe 是否符合条件
	 */
	public String[] getSxtj(String xn, String xq, String cjlx, String jxjdm)
			throws SQLException {
		StringBuffer buf = new StringBuffer();
		String pk = "xn||szlx||jxjdm";
		buf.append(xn);
		if (StringUtils.isNotNull(xq)) {
			buf.append(xq);
			pk = "xn||xq||szlx||jxjdm";
		}
		buf.append(cjlx).append(jxjdm);
		String sql = "select tjzd from jhzy_pjpy_tjsz a where " + pk + "=?";
		return dao.getArray(sql, new String[] { buf.toString() }, "tjzd");
	}
}
