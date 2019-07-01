package xgxt.dtjs.zjcm;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.dtjs.DtjsDAO;

public class ZjcmDtjsDAO extends DtjsDAO {
	
	/**
	 * 获得培训证书列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String,String>> getPxzsList(){
		DAO dao = DAO.getInstance();
		String sql = "select xh, count(xh) num from xspxxxb where zsyw = '有' group by xh";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {},
				new String[] { "xh", "num" });
		return list;
	}
	
	/**
	 * 提交人是否有人没有培训证书
	 * 
	 * @author luojw
	 */
	public boolean isPxzs(String[] pkValue){
		DAO dao = DAO.getInstance();
		boolean flag = true;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xspxxxb a where ");
		sql.append("exists(select 1 from fzdx b where a.xh = b.xh");
		for (int i = 0; i < pkValue.length; i++) {
			if(i==0){
				sql.append(" and (b.xn||b.xq||b.xh='" + pkValue[i] + "'");
			}else{
				sql.append(" or b.xn||b.xq||b.xh='" + pkValue[i] + "'");
			}
		}
		sql.append(")");
		sql.append(")");
		sql.append(" and zsyw = '有'");
		List<HashMap<String,String>> list = dao.getList(sql.toString(), new String[]{}, new String[]{"xh"}) ;
		if (list != null && list.size() == pkValue.length) {
			flag = false;
		}
		
		return flag;
	}
}
