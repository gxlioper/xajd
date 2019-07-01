package xgxt.dtjs.gdby.dyxx;

import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.dtjs.czxx.dyxx.DyxxModel;

public class DtjsGdbyService {
	/**
	 * 获得成绩比率
	 * 
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getCjbl(String xn){
		
		DAO dao = DAO.getInstance();
		String sql = "select xn,NVL(xxtdbl,0) xxtdbl,NVL(xxjlbl,0) xxjlbl,NVL(xxxgbl,0) xxxgbl from dtjs_csszb" +
				" where xn=?";
		
		return dao.getMapNotOut(sql, new String[]{xn});
	}

	/**
	 * 存储成绩比率
	 * 
	 * @param model
	 * @return
	 */
	public boolean saveCjbl(DyxxModel model) {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		try {
			String delSql = "delete from dtjs_csszb where xn=?";
			dao.runUpdate2(delSql, new String[] { model.getXn() });

			String insSql = "insert into dtjs_csszb values(?,?,?,?)";
			String[] input = new String[] { model.getXn(), model.getXxtdbl(),
					model.getXxjlbl(), model.getXxxgbl() };
			dao.runUpdate2(insSql, input);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
