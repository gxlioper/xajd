package xgxt.xszz.nbcs;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class XszzNbcsDAO {
	
	DAO dao = DAO.getInstance();
	
	/**
	 * 下拉列表值 
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getChkList(int type) {
		
		return dao.getChkList(type);
	}
	
	
	/**
	 * 获取学生是否有放款记录
	 * @param xh
	 * @return
	 */
	public String getIsFfdk(String xh) {
		String sql = "select case when count>0 then 'true' else 'false' end sffk from ( select count(*) count from zxdk_nbcs_dkff where xh=?)";
		
		return dao.getOneRs(sql, new String[] {xh}, "sffk");
	}
}
