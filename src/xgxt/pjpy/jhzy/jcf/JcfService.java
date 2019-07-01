package xgxt.pjpy.jhzy.jcf;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;

public class JcfService {

	JcfDAO dao = new JcfDAO();

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
	 * @describe ��ý��ͷ��б�
	 */
	public ArrayList<String[]> getJcList(String tableName, JcfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getJcList(tableName, model, colList);
	}
	
	/**
	 * ���潱�ͷ�
	 * 
	 * @throws SQLException
	 */
	public boolean saveJcf(JcfModel model, HttpServletRequest request)
			throws SQLException {
		return dao.saveJcf(model, request);
	}
	
	public HashMap<String, String> getStuInfo(String xh) {
		return JcfDAO.getStuInfo(xh);
	}
	/**
	 * @author luo
	 * @describe ���ѧ���б�
	 */
	public List<HashMap<String, String>> getXqList() {
		return dao.getXqList();
	}
	
	/**
	 * ɾ�����ͷ�
	 * @throws Exception 
	 */
	public boolean delJcf(String pk, HttpServletRequest request)
			throws Exception {
		return dao.delJcf(pk, request);
	}
	
	/**
	 * ���ѧ�ڴ���
	 * @throws Exception 
	 */
	public String getXqdm(String xqmc) {
		return dao.getXqdm(xqmc);
	}
}
