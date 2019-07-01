package xgxt.dtjs.zgdzdx.zbr;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsDAO;
import xgxt.szdw.utils.ModelToStrings;

public class ZbrDAO extends ZgdzdxDtjsDAO {

	DAO dao = DAO.getInstance();

	/**
	 * @author luo
	 * @describe 保存值班人信息
	 */
	public boolean saveZbr(ZbrModel myModel, HttpServletRequest request)
			throws Exception {
		String tableName = "zgdd_zbrb";
		String primaryKey = "xh";
		String[] colList = new String[] { "xh", "zbid", "gwmc", "yhzh", "dh" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean flg = true;
		flg = StandardOperation.delete(tableName, primaryKey, myModel.getXh(),
				request);
		if (flg) {
			flg = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 删除值班人信息
	 */
	public boolean delZbr(String xh, HttpServletRequest request)
			throws Exception {
		String tableName = "zgdd_zbrb";
		String primaryKey = "xh";

		boolean flg = StandardOperation.delete(tableName, primaryKey, xh,
				request);

		return flg;
	}

	/**
	 * @author luo
	 * @describe 判断值班号是否唯一
	 */
	public String getZbid(String zbid) {
		String sql = "select count(*) num from zgdd_zbrb where zbid=?";
		String num = dao.getOneRs(sql, new String[] { zbid }, "num");
		return num;
	}
}
