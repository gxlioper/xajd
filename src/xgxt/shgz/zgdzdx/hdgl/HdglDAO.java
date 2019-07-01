package xgxt.shgz.zgdzdx.hdgl;

import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsDAO;

public class HdglDAO extends ZgdzdxDtjsDAO {

	DAO dao = DAO.getInstance();

	/**
	 * @author luo
	 * @describe 是否在设定的时间范围内
	 */
	public String inSj(String hddm) throws Exception {

		String sql = "select count(*) count from zgdd_shgz_hdsjb where xn=? and xq=? and hddm=? and "
				+ "kssj <= to_char(sysdate, 'yyyymmdd') and jssj >= to_char(sysdate, 'yyyymmdd')";
		String count = dao.getOneRs(sql, new String[] { Base.currXn,
				Base.currXq, hddm }, "count");

		return count;
	}

	/**
	 * @author luo
	 * @describe 保存活动申请
	 */
	public boolean saveHdsq(HdglModel myModel, String[] zdyZd, String[] zdyZdz,
			HttpServletRequest request) throws Exception {

		String sql = "";

		StringBuffer inssb = new StringBuffer();
		StringBuffer delsb = new StringBuffer();

		String hddm = myModel.getHddm();
		String xh = myModel.getXh();
		String xn = myModel.getXn();
		String xq = myModel.getXq();

		boolean flg = false;

		if (zdyZd.length > 0) {
			for (int i = 0; i < zdyZd.length; i++) {
				sql = "delete zgdd_shgz_hdsqb where hddm||xn||xq||xh||zd = '"
						+ hddm + xn + xq + xh + zdyZd[i] + "'";
				delsb.append(sql);
				delsb.append("!!#!!");

				sql = "insert into zgdd_shgz_hdsqb(hddm,xn,xq,xh,zd,zdz) values('"
						+ hddm
						+ "','"
						+ xn
						+ "','"
						+ xq
						+ "','"
						+ xh
						+ "','"
						+ zdyZd[i] + "','" + zdyZdz[i] + "')";
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

		if (flg) {
			String primaryKey = "hddm||xn||xq||xh";
			String pk = hddm + xn + xq + xh;
			flg = StandardOperation.delete("zgdd_shgz_hdshb", primaryKey, pk,
					request);
			flg = StandardOperation.insert("zgdd_shgz_hdshb", new String[] {
					"hddm", "xn", "xq", "xh" },
					new String[] { hddm, xn, xq, xh }, request);
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 批量处理审核
	 */
	public boolean saveHdsh(String[] key, String shlx, String type)
			throws Exception {
		boolean flg = false;
		String sql = "";
		StringBuffer sb = new StringBuffer();
		if (!"del".equalsIgnoreCase(shlx)) {
			shlx = "tg".equalsIgnoreCase(shlx) ? "已通过" : "未通过";
		}

		if (key.length > 0) {
			for (int i = 0; i < key.length; i++) {

				if ("del".equalsIgnoreCase(shlx)) {
					sql = "delete from zgdd_shgz_hdshb where hddm||xn||xq||xh = '"
							+ key[i] + "'";
				} else if ("xy".equalsIgnoreCase(type)) {
					sql = "update zgdd_shgz_hdshb set xysh='" + shlx
							+ "'where hddm||xn||xq||xh = '" + key[i] + "'";
				} else {
					sql = "update zgdd_shgz_hdshb set xxsh='" + shlx
							+ "'where hddm||xn||xq||xh = '" + key[i] + "'";
				}
				sb.append(sql);
				sb.append("!!#!!");
			}

			String[] inssql = sb.toString().split("!!#!!");

			int[] res = dao.runBatch(inssql);
			for (int i = 0; i < res.length; i++) {
				flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flg)
					break;
			}
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 单条记录审核
	 */
	public boolean saveHdsh(String pk, String userType, String shlx,
			HdglModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "zgdd_shgz_hdshb";
		String primaryKey = "hddm||xn||xq||xh";

		shlx = "tg".equalsIgnoreCase(shlx) ? "已通过" : "未通过";
		String[] columns = null;
		String[] values = null;

		if ("xy".equalsIgnoreCase(userType)) {
			columns = new String[] { "xyyj", "xysh" };
			values = new String[] { DealString.toGBK(myModel.getXyyj()), shlx };
		} else {
			columns = new String[] { "xxyj", "xxsh" };
			values = new String[] { DealString.toGBK(myModel.getXxyj()), shlx };
		}

		boolean flg = StandardOperation.update(tableName, columns, values,
				primaryKey, pk, request);

		return flg;
	}

	/**
	 * @author luo
	 * @describe 获得自定义字段
	 */
	public List<HashMap<String, String>> getZdyZd(String hddm) {
		String pk = hddm + Base.currXn;
		String sql = "select zd,zdm,zdlx from zgdd_shgz_hdbzd where hddm||xn=? order by zdlx,zd";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { pk }, new String[] { "zd", "zdm", "zdlx" });
		return list;
	}

	/**
	 * @author luo
	 * @describe 是否已经提交申请
	 */
	public boolean hadSq(HdglModel myModel) {

		boolean flg = false;

		String hddm = myModel.getHddm();
		String xn = myModel.getXn();
		String xq = myModel.getXq();
		String xh = DealString.toGBK(myModel.getXh());
		String pk = hddm + xn + xq + xh;

		String sql = "select count(*) num from view_zgdd_hdshb where pk=?";
		String num = dao.getOneRs(sql, new String[] { pk }, "num");
		if (!"0".equalsIgnoreCase(num)) {
			flg = true;
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 获得自定义字段值
	 */
	public List<HashMap<String, String>> getZdyZdz(String hddm, String xh,String xn,String xq) {
//		String xn = Base.currXn;
//		String xq = Base.currXq;
		String sql = "select zd,zdz,zdm,zdlx from view_zgdd_hdzdyzd where hddm=? and xh=? and xn = ? and xq = ? order by zdlx,zd";
		List<HashMap<String, String>> list = dao
				.getList(sql, new String[] { hddm,xh,xn,xq }, new String[] {
						"zd", "zdz", "zdm", "zdlx" });
		return list;
	}

	/**
	 * @author luo
	 * @describe 是否需要学院审核
	 */
	public String needXy(String pk, String type) throws Exception {
		String sql = "";

		if ("view".equals(type)) {
			sql = "select a.isxysh from view_zgdd_hdfbb a, zgdd_shgz_hdshb b where"
					+ " a.hddm = b.hddm and a.xn = b.xn and a.xq = b.xq and a.pk||b.xh = ?";
		} else {
			sql = "select a.isxysh from view_zgdd_hdfbb a where a.pk=?";
		}
		return dao.getOneRs(sql, new String[] { pk }, "isxysh");
	}
}
