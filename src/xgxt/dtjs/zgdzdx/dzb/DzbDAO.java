package xgxt.dtjs.zgdzdx.dzb;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zgdzdx.ZgdzdxDtjsDAO;
import xgxt.szdw.utils.ModelToStrings;

public class DzbDAO extends ZgdzdxDtjsDAO {

	DAO dao = DAO.getInstance();

	/**
	 * @author luo
	 * @describe 保存党支部信息
	 */
	public boolean saveDzb(DzbModel myModel, HttpServletRequest request)
			throws Exception {
		String tableName = "zgdd_dzbjsb";
		String primaryKey = "xydm||dzbmc";
		String xydm = myModel.getXydm();
		String dzbmc = DealString.toGBK(myModel.getDzbmc());
		String[] colList = new String[] { "xydm", "dzbmc", "zrq", "dzbcys",
				"dzbjycs", "dzbbz" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean flg = true;
		flg = StandardOperation.delete(tableName, primaryKey, xydm + dzbmc,
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
	public boolean delDzb(String xydm, HttpServletRequest request)
			throws Exception {
		String tableName = "zgdd_dzbjsb";
		String primaryKey = "xydm||dzbmc";

		boolean flg = StandardOperation.delete(tableName, primaryKey, xydm,
				request);

		return flg;
	}

}
