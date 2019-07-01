package xgxt.szdw.xmlg.wmbj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class WmbjService {

	WmbjDAO dao = new WmbjDAO();

	/**
	 * @describe 获得文明班级活动字段列表
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @author luo
	 */
	public ArrayList<String[]> getCsszList(String tableName, WmbjModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getCsszList(tableName, model, colList);
	}

	/**
	 * @describe 保存文明班级活动字段设置
	 * @author luo
	 */
	public boolean saveCssz(WmbjModel myModel, HttpServletRequest request)
			throws Exception {
		return dao.saveCssz(myModel, request);
	}

	/**
	 * @describe 获得文明班级活动字段
	 * @author luo
	 */
	public HashMap<String, String> getCssz(String tableName, String[] colList,
			String pk, String pkValue) {
		return dao.getCssz(tableName, colList, pk, pkValue);
	}

	/**
	 * @describe 删除文明班级活动字段
	 * @author luo
	 */
	public boolean delCssz(String pk, HttpServletRequest request)
			throws Exception {
		return dao.delCssz(pk, request);
	}

	/**
	 * @describe 判断是否班干部
	 * @author luo
	 */
	public boolean isBgb(String xh) {
		return dao.isBgb(xh);
	}

	/**
	 * @describe 获得文明班级申报自定义字段
	 * @author luo
	 */
	public List<HashMap<String, String>> getZdyZd(String xn, String xq) {
		return dao.getWmbjZdyZd(xn, xq);
	}

	/**
	 * @describe 保存文明班级申报内容
	 * @author luo
	 */
	public boolean saveWmbjsb(WmbjModel myModel, String[] zdyZd, String[] zdyZdz,
			HttpServletRequest request) throws Exception {
		return dao.saveWmbjsb(myModel, zdyZd, zdyZdz, request);
	}
	
	/**
	 * @describe 获得申报学生基本信息
	 * @author luo
	 */
	public HashMap<String, String> getSbrXx(String xh) {
		return dao.getSbrXx(xh);
	}
	
	/**
	 * @describe 获得文明班级申报列表
	 * @author luo
	 */
	public ArrayList<String[]> getWmbjList(String tableName, WmbjModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getWmbjList(tableName, model, colList);
	}
	
	/**
	 * @describe 删除文明班级信息
	 * @author luo
	 */
	public boolean delWmbj(String[] key) throws Exception {
		return dao.delWmbj(key);
	}
}
