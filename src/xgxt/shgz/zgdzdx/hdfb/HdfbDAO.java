package xgxt.shgz.zgdzdx.hdfb;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsDAO;
import xgxt.szdw.utils.ModelToStrings;

public class HdfbDAO extends ZgdzdxDtjsDAO {

	DAO dao = DAO.getInstance();

	/**
	 * @author luo
	 * @describe 保存活动发布
	 */
	public boolean saveHdfb(HdfbModel myModel, HttpServletRequest request)
			throws Exception {
		String tableName = "zgdd_shgz_hdsjb";
		String primaryKey = "hddm||xn||xq";
		String primaryValue = myModel.getHddm() + myModel.getXn()
				+ myModel.getXq();
		String[] colList = new String[] { "hddm", "xn", "xq", "kssj", "jssj",
				"isxysh" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean flg = true;
		flg = StandardOperation.delete(tableName, primaryKey, primaryValue,
				request);
		if (flg) {
			flg = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 删除参数
	 */
	public boolean delHdfb(String pk, HttpServletRequest request)
			throws Exception {
		String tableName = "zgdd_shgz_hdsjb";
		String primaryKey = "hddm||xn||xq";

		boolean flg = StandardOperation.delete(tableName, primaryKey, pk,
				request);

		return flg;
	}

	/**
	 * @author luo
	 * @describe 主键是否重复
	 */
	public String getZd(String hddm, String zd) {
		String sql = "select count(*) num from zgdd_shgz_hdbzd where hddm=? and zd = ?";
		String num = dao.getOneRs(sql, new String[] { hddm, zd }, "num");
		return num;
	}
}
