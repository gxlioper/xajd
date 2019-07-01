package xgxt.pjpy.zjcm.zhf;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.pjpy.zjcm.zhf.ZhfModel;


public class ZhfService {

	ZhfDAO dao = new ZhfDAO();

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
	 * @describe ����ۺϷ��б�
	 */
	public ArrayList<String[]> getZhfList(String tableName, ZhfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getZhfList(tableName, model, colList);
	}
	
	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe �����ۺϷ�
	 */
	public boolean saveZhf(ZhfModel model) throws SQLException {
		return dao.saveZhf(model);
	}
	
	/**
	 * @author luo
	 * @describe �ж��Ƿ����С���Ա
	 */
	public boolean isCpzCy(String xh, String xydm) {
		return dao.isCpzCy(xh, xydm);
	}
	
	/**
	 * @author luo
	 * @describe ���ѧ��������Ϣ
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return ZhfDAO.getStuInfo(xh);
	}
	
	/**
	 * @author luo
	 * @describe �жϵ�½ʱ���Ƿ��������õ�ʱ�䷶Χ��
	 */
	public boolean inTime(String xydm) {
		return dao.inTime(xydm);
	}
	
	/**
	 * @author luo
	 * @describe ���ѧ���б�
	 */
	public List<HashMap<String, String>> getXqList() {
		return dao.getXqList();
	}
	
	/**
	 * �����ۺ����ʷ�����
	 * 
	 * @throws SQLException
	 */
	public boolean saveZhfBl(ZhfModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveZhfBl(model, request);
	}

	/**
	 * ����ۺ����ʷ�����
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getZhfBl() {
		return dao.getZhfBl();
	}
	
	/**
	 * �����ۺϷ�
	 * 
	 * @throws Exception
	 */
	public boolean jsZhf(String xn, String xq) throws Exception {
		return dao.jsZhf(xn, xq);
	}
}
