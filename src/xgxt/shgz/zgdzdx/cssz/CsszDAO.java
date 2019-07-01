package xgxt.shgz.zgdzdx.cssz;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsDAO;
import xgxt.szdw.utils.ModelToStrings;

public class CsszDAO extends ZgdzdxDtjsDAO {

	DAO dao = DAO.getInstance();

	/**
	 * @author luo
	 * @describe 保存参数设置
	 */
	public boolean saveCssz(CsszModel myModel, HttpServletRequest request)
			throws Exception {
		String tableName = "zgdd_shgz_hdbzd";
		String primaryKey = "hddm||xn||zd";
		String primaryValue = myModel.getHddm() + myModel.getXn()
				+ DealString.toGBK(myModel.getZd());

		String[] colList = new String[] { "hddm", "xn", "zd", "zdm", "zdlx" };
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
	public boolean delCssz(String pk, HttpServletRequest request)
			throws Exception {
		String tableName = "zgdd_shgz_hdbzd";
		String primaryKey = "hddm||xn||zd";

		boolean flg = StandardOperation.delete(tableName, primaryKey, pk,
				request);

		return flg;
	}

	/**
	 * @author luo
	 * @describe 主键是否重复
	 */
	public String getZd(String pk) {
		String sql = "select count(*) num from zgdd_shgz_hdbzd where hddm||xn||zd = ?";
		String num = dao.getOneRs(sql, new String[] { pk }, "num");
		return num;
	}
}
