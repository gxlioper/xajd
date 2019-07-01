/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2010-2-3 上午11:30:40</p>
 */
package xgxt.shgz.server.nbcszyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.shgz.dao.nbcszyjsxy.ShgzDAO;
import xgxt.shgz.model.nbcszysjxy.ShsjModel;

public class ShgzService {
	public ArrayList<String[]> serv_shsjXxSearch(ShsjModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		ShgzDAO dao = new ShgzDAO();
		return dao.shsjXxSearch(model);
	}
	public List<HashMap<String, String>> getShsjTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		colList = new String[]{"pk","xn","xqmc","xh","xm","xymc","bjmc","hdxm","hdnr"};
		colListCN =  new String[]{"pk","学年","学期","学号","姓名","院系","班级","活动项目","内容"}; 		
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		return topTr;
	}
	public boolean serv_shsjXxSave(ShsjModel model,String pkValue) throws Exception{
		ShgzDAO dao = new ShgzDAO();
		return dao.shsjXxSave(model, pkValue);
	}
	public HashMap<String,String> serv_ShsjXx(String pkValue){
		ShgzDAO dao = new ShgzDAO();
		return dao.getShsjXx(pkValue);
	}
	public boolean serv_shsjXxDel(String pkVStr) throws SQLException{
		ShgzDAO dao = new ShgzDAO();
		return dao.shsjXxDel(pkVStr);
	}
}
