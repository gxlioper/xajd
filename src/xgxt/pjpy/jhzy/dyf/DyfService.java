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
	 * @describe ��ñ�ͷ
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
	 * @describe ��õ������б�
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
	 * @describe ���������
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
	 * @describe ����������б�
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
	 * @describe ����������
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
	 * @describe ��ü��ܷ��б�
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
	 * @describe ���漼�ܷ�
	 */
	public boolean saveJnf(DyfModel model) throws SQLException {
		return dao.saveJnf(model);
	}
	
	/**
	 * @author luo
	 * @describe ���ѧ���б�
	 */
	public List<HashMap<String, String>> getXqList() {
		return dao.getXqList();
	}
}
