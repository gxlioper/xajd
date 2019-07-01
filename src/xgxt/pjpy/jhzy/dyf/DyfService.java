package xgxt.pjpy.jhzy.dyf;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;


public class DyfService {

	DyfDAO dao = new DyfDAO();

	/**
	 * @author luo
	 * @describe 获得表头
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	/**
	 * @author luo
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @describe 获得德育分列表
	 */
	public ArrayList<String[]> getDyfList(String tableName, DyfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getDyfList(tableName, model, colList);
	}
	
	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 保存德育分
	 */
	public boolean saveDyf(DyfModel model) throws SQLException {
		return dao.saveDyf(model);
	}
	
	/**
	 * @author luo
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @describe 获得体育分列表
	 */
	public ArrayList<String[]> getTyfList(String tableName, DyfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getTyfList(tableName, model, colList);
	}
	
	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 保存体育分
	 */
	public boolean saveTyf(DyfModel model) throws SQLException {
		return dao.saveTyf(model);
	}
	
	/**
	 * @author luo
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @describe 获得技能分列表
	 */
	public ArrayList<String[]> getJnfList(String tableName, DyfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getJnfList(tableName, model, colList);
	}
	
	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 保存技能分
	 */
	public boolean saveJnf(DyfModel model) throws SQLException {
		return dao.saveJnf(model);
	}
	
	/**
	 * @author luo
	 * @describe 获得学期列表
	 */
	public List<HashMap<String, String>> getXqList() {
		return dao.getXqList();
	}
}
