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
	 * @describe 获得综合分列表
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
	 * @describe 保存综合分
	 */
	public boolean saveZhf(ZhfModel model) throws SQLException {
		return dao.saveZhf(model);
	}
	
	/**
	 * @author luo
	 * @describe 判断是否测评小组成员
	 */
	public boolean isCpzCy(String xh, String xydm) {
		return dao.isCpzCy(xh, xydm);
	}
	
	/**
	 * @author luo
	 * @describe 获得学生基本信息
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return ZhfDAO.getStuInfo(xh);
	}
	
	/**
	 * @author luo
	 * @describe 判断登陆时间是否在所设置的时间范围内
	 */
	public boolean inTime(String xydm) {
		return dao.inTime(xydm);
	}
	
	/**
	 * @author luo
	 * @describe 获得学期列表
	 */
	public List<HashMap<String, String>> getXqList() {
		return dao.getXqList();
	}
	
	/**
	 * 保存综合素质分上限
	 * 
	 * @throws SQLException
	 */
	public boolean saveZhfBl(ZhfModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveZhfBl(model, request);
	}

	/**
	 * 获得综合素质分上限
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getZhfBl() {
		return dao.getZhfBl();
	}
	
	/**
	 * 计算综合分
	 * 
	 * @throws Exception
	 */
	public boolean jsZhf(String xn, String xq) throws Exception {
		return dao.jsZhf(xn, xq);
	}
}
