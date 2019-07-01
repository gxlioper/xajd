package xgxt.pjpy.zjcm.xwjxj;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import xgxt.DAO.DAO;

public class XwjxjService {

	XwjxjDAO dao = new XwjxjDAO();
	XwjxjNewDAO mydao = new XwjxjNewDAO();

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
	 * @describe 获得学生基本信息
	 */
	public HashMap<String, String> getStuInfo(String xh) {
		return XwjxjDAO.getStuInfo(xh);
	}

	/**
	 * @author luo
	 * @describe 获得奖学金申请列表
	 */
	public ArrayList<String[]> getJxjSqList(String tableName, XwjxjModel model,
			String[] colList, String userType) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getJxjSqList(tableName, model, colList, userType);
	}

	/**
	 * 获得申报奖学金信息
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getSbJxjXx(String pk) {
		return dao.getSbJxjXx(pk);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存奖学金审核
	 */
	public boolean saveJxjsh(XwjxjModel model, String pk, String shzt,
			String userType, HttpServletRequest request) throws Exception {
		return dao.saveJxjsh(model, pk, shzt, userType, request);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存奖学金审核
	 */
	public boolean saveJxjsh(String[] key, String jxjdm, String shzt,
			String userType) throws Exception {
		return dao.saveJxjsh(key, jxjdm, shzt, userType);
	}

	/**
	 * 获得奖学金列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlb) {
		return dao.getJxjList(jxjlb);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 获得奖学金类别
	 */
	public String getJxjlb(String jxjdm) {
		return dao.getJxjlb(jxjdm);
	}

	/**
	 * @author luo
	 * @throws Exception 
	 * @describe 获得奖学金人数上限
	 */
	public String getJxjRs(String jxjdm) {
		return dao.getJxjRs(jxjdm);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe 获得奖学金人数
	 */
	public String getHdJxjRs(String jxjdm) {
		return dao.getHdJxjRs(jxjdm);
	}

	/**
	 * 获得违纪处分列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfList(String xh) {
		return dao.getWjcfList(xh);
	}
	
	/**
	 * @author luo
	 * @describe 获得学期列表
	 */
	public List<HashMap<String, String>> getXqList() {
		return dao.getXqList();
	}
	
	/**
	 * 获得英语等级考试列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYyList() {
		return dao.getYyList();
	}
	
	/**
	 * 获得计算机等级列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJsjList() {
		return dao.getJsjList();
	}
	
	/*****************李涛 加入  2010-4-22************/
	public List<HashMap<String, String>> queryXwjxjshTitle() {
		String[] en = new String[] { "pk","color", "r", "xh", "xm", "bjmc", "xn", "xq",
				"jxjmc", "dypm", "zypm", "typm", "zhpm", "xxsh" };
		String[] cn = new String[] { "pk","color", "行号", "学号", "姓名", "班级", "学年", "学期",
				"奖学金", "德育排名", "智育排名", "体育排名", "综测排名", "学校审核" };
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询校外奖学金审核结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXwjxjshResult(XwjxjModel model) throws Exception {
		return mydao.queryXwjxjshResult(model);
	}
	
	/**
	 * 修改校外奖学金审核结果
	 * @param pk
	 * @param jg
	 * @return
	 * @throws Exception
	 */
	public boolean updateXwjxjshJg(String[] pk, String jg) throws Exception {
		return mydao.updateXwjxjshJg(pk, jg);
	}
	
	/**
	 * 查询单个校外奖这金信息
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryXwjxjDgxx(String pkValue) {
		return mydao.queryXwjxjDgxx(pkValue);
	}
	
	/**
	 * 修改单个校外奖学金审核信息
	 * 
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateXwjxjDgxx(XwjxjModel model, String pkValue)
			throws Exception {
		return mydao.updateXwjxjDgxx(model, pkValue);
	}
}
