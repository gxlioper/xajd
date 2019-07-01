package xgxt.szdw.xmlg;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;

public class XmlgSzdwDAO extends CommonQueryDAO {

	DAO dao = DAO.getInstance();

	/**
	 * @describe 获得文明班级申报自定义字段
	 * @author luo
	 */
	public List<HashMap<String, String>> getWmbjZdyZd(String xn, String xq) {
		String pk = xn + xq;
		String sql = "select zd, zdm, zdlx from xmlg_wmbj_hdbzd where xn||xq = ? order by zdlx,zd";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { pk }, new String[] { "zd", "zdm", "zdlx" });
		return list;
	}

	/**
	 * @describe 获得辅导员月评申报自定义字段
	 * @author luo
	 */
	public List<HashMap<String, String>> getFdyypZdyZd(String xn, String xq) {
		String pk = xn + xq;
		String sql = "select zd, zdm, zdlx from xmlg_fdyyp_hdbzd where xn||xq = ? order by zdlx,zd";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { pk }, new String[] { "zd", "zdm", "zdlx" });
		return list;
	}
	
	/**
	 * @describe 获得文明班级申报内容
	 * @author luo
	 */
	public List<HashMap<String, String>> getWmbjSbnr(String xn, String xq,
			String sbbj) {
		String pk = xn + xq + sbbj;
		StringBuffer sql = new StringBuffer();
		sql.append("select zd,zdz,zdm,zdlx from (select a.xn, a.xq, a.zd, a.zdz, a.zdm, a.zdlx from view_xmlg_wmbjsb a where xn||xq||sbbj = ? union ");
		sql.append("select b.xn, b.xq, b.zd, '' zdz, b.zdm, b.zdlx ");
		sql.append("from xmlg_wmbj_hdbzd b where xn=? and xq=? and not exists (select 1 ");
		sql.append("from view_xmlg_wmbjsb a where a.xn = b.xn and a.xq = b.xq and a.zd = b.zd)) order by xn, xq,zdlx,zd");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pk, xn, xq }, new String[] { "zd", "zdz", "zdm",
						"zdlx" });
		return list;
	}
	
	/**
	 * @describe 获得辅导员月评申报内容
	 * @author luo
	 */
	public List<HashMap<String, String>> getFdyypSbnr(String xn, String xq,
			String zgh, String yf) {
		String pk = xn + xq + zgh + yf;
		StringBuffer sql = new StringBuffer();
		sql.append("select zd,zdz,zdm,zdlx from (select a.xn, a.xq, a.zd, a.zdz, a.zdm, a.zdlx from view_xmlg_fdyypsb a where xn||xq||zgh||yf = ? union ");
		sql.append("select b.xn, b.xq, b.zd, '' zdz, b.zdm, b.zdlx ");
		sql.append("from xmlg_fdyyp_hdbzd b where xn=? and xq=? and not exists (select 1 ");
		sql.append("from view_xmlg_fdyypsb a where a.xn = b.xn and a.xq = b.xq and a.zd = b.zd)) order by xn, xq,zdlx,zd");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pk, xn, xq }, new String[] { "zd", "zdz", "zdm",
						"zdlx" });
		return list;
	}
	
	/**
	 * @describe 主键是否重复
	 * @author luo
	 */
	public String getZd(String tableName, String pkValue) {
		String pk = "";
		if("xmlg_wmbj_hdbzd".equalsIgnoreCase(tableName)){
			pk="xn||xq||zd";
		}else if ("xmlg_fdyyp_hdbzd".equalsIgnoreCase(tableName)){
			pk="xn||xq||zd";
		}
		
		String sql = "select count(*) num from " + tableName + " where " + pk + " = ?";
		String num = dao.getOneRs(sql, new String[] { pkValue }, "num");
		return num;
	}
	
	/**
	 * @describe 获得测评得分
	 * @author luo
	 */
	public HashMap<String, String> getCpdf(String xn, String xq, String zgh,
			String yf) {
		String pk = xn + xq + zgh + yf;
		String sql = "select xyyj,cpdf from xmlg_szdw_fdyypsh where xn||xq||zgh||yf = ?";
		return dao.getMap(sql, new String[] { pk }, new String[] { "xyyj",
				"cpdf" });
	}
	
	/**
	 * @describe 获得辅导员信息
	 * @author luo
	 */
	public HashMap<String, String> getFdyxx(String[] colList, String zgh) {
		return CommonQueryDAO.commonQueryOne("view_fdyxx", colList, "zgh", zgh);
	}
	
	/**
	 * @describe 是否系分管书记
	 * @author luo
	 */
	public boolean isXfgsj(String zgh) {

		boolean flg = false;
		String sql = "select count(*) num from yhb a where a.yhm = ? and exists (select 1"
				+ " from yhzb b where zmc = '系分管书记' and a.zdm = b.zdm)";
		String num = dao.getOneRs(sql, new String[] { zgh }, "num");

		if (!"0".equalsIgnoreCase(num)) {
			flg = true;
		}
		return flg;
	}
	
	
	/**
	 * @describe 获得提交名称
	 * @author luo
	 */
	public List<HashMap<String, String>> getMcList(String tableName,String lx) {
		lx = Base.isNull(lx) ? "%" : lx;
		StringBuffer sql = new StringBuffer();
		sql.append("select ''dm, '-----请选择-----'mc from dual union ");
		sql.append("select dm,mc from " + tableName + " where lx like ? order by dm nulls first");
		return dao.getList(sql.toString(), new String[] {lx}, new String[] { "dm", "mc" });
	}
}
