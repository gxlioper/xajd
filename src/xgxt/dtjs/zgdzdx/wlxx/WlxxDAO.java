package xgxt.dtjs.zgdzdx.wlxx;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsDAO;
import xgxt.szdw.utils.ModelToStrings;

public class WlxxDAO extends ZgdzdxDtjsDAO {

	DAO dao = DAO.getInstance();

	/**
	 * @author luo
	 * @describe 保存网络信息
	 */
	public boolean saveWlxx(WlxxModel myModel, HttpServletRequest request)
			throws Exception {
		String tableName = "zgdd_wlxxb";
		String primaryKey = "zbid";
		String[] colList = new String[] { "zbid", "kssj","jssj", "zbqk", "wlbz" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean flg = true;
		flg = StandardOperation.delete(tableName, primaryKey, myModel.getZbid(),
				request);
		if (flg) {
			flg = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return flg;
	}

	/**
	 * @author luo
	 * @describe 删除网络信息
	 */
	public boolean delWlxx(String xh, HttpServletRequest request)
			throws Exception {
		String tableName = "zgdd_wlxxb";
		String primaryKey = "zbid";

		boolean flg = StandardOperation.delete(tableName, primaryKey, xh,
				request);

		return flg;
	}

}
