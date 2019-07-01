package xgxt.pjpy.zjcm.xnjxj;

import java.lang.reflect.InvocationTargetException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;

public class XnjxjService {

	XnjxjDAO dao = new XnjxjDAO();

	XnjxjNewDAO myDAO = new XnjxjNewDAO();
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
	public ArrayList<String[]> getZhfList(String tableName, XnjxjModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getZhfList(tableName, model, colList);
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
		return XnjxjDAO.getStuInfo(xh);
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
	 * 获得申报者信息
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getSbzXx(String xh, String xn, String xq) {
		return dao.getSbzXx(xh, xn, xq);
	}

	/**
	 * 获得申报者旷课次数
	 * 
	 * @throws Exception
	 */
	public String getKkcs(String xh, String xn, String xq) {
		return dao.getKkcs(xh, xn, xq);
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
	 * 获得奖学金列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlb) {
		return dao.getJxjList(jxjlb);
	}

	/**
	 * 获得计算机等级列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJsjList() {
		return dao.getJsjList();
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
	 * @throws Exception
	 * @describe 保存奖学金申请
	 */
	public boolean saveJxjsq(XnjxjModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveJxjsq(model, request);
	}

	/**
	 * @author luo
	 * @describe 获得奖学金申请列表
	 */
	public ArrayList<String[]> getJxjSqList(String tableName, XnjxjModel model,
			String[] colList, String userType, String doType)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getJxjSqList(tableName, model, colList, userType, doType);
	}

	/**
	 * 获得学期名称
	 * 
	 * @throws Exception
	 */
	public String getXqmc(String xqdm) {
		return dao.getXqmc(xqdm);
	}

	/**
	 * 获得奖学金类别列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjLbList() {
		return dao.getJxjLbList();
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
	public boolean saveJxjsh(XnjxjModel model, String pk, String shzt,
			String userType, HttpServletRequest request) throws Exception {
		return dao.saveJxjsh(model, pk, shzt, userType, request);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存次级奖学金审核
	 */
	public boolean saveCjJxjsh(XnjxjModel model, String pk, String shzt,
			String userType) throws Exception {
		return dao.saveCjJxjsh(model, pk, shzt, userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存奖学金审核
	 */
	public String saveJxjsh(String[] key, String jxjdm, String shzt,
			String userType) throws Exception {
		return dao.saveJxjsh(key, jxjdm, shzt, userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 删除奖学金
	 */
	public boolean delJxjSq(String[] key,String userType) throws Exception {
		return dao.delJxjSq(key,userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 判断奖学金条件
	 */
	public String Jxjtj(XnjxjModel model, String zhpm) {
		return dao.Jxjtj(model, zhpm);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存奖学金审核
	 */
	public boolean saveXwjxjSb(String[] key, String jxjdm) throws Exception {
		return dao.saveXwjxjSb(key, jxjdm);
	}

	/**
	 * @author luo
	 * @throws Exception 
	 * @describe 判断奖学金兼得情况
	 */
	public boolean hadJxj(String pk) {
		return dao.hadJxj(pk);
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
	 * 获得奖学金人上限数
	 */
	public String getJxjRs(String jxjdm, String bjdm) {
		return dao.getJxjRs(jxjdm, bjdm);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe 获得奖学金人数
	 */
	public String getHdJxjRs(String jxjdm,String bjdm) {
		return dao.getHdJxjRs(jxjdm,bjdm);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存次级奖学金审核
	 */
	public String saveCjJxjsh(String[] key, String jxjdm, String shzt)
			throws Exception {
		return dao.saveCjJxjsh(key, jxjdm, shzt);
	}
	
	/**
	 * 判断次级人数
	 */
	public String getCjJxjRs(String jxjdm, String bjdm, String num) {
		return dao.getCjJxjRs(jxjdm, bjdm, num);
	}
	
	/**
	 * 校内奖学金列表
	 * @param jxjlb
	 * @return
	 */
	public List<HashMap<String, String>> getXnjxjList(String jxjlb) {
		return dao.getXnjxjList(jxjlb);
	}
	
	
	/******************************** 李涛加入 2010-4-12 ********************************/
	public List<String[]> queryJxhshResult(XnjxjModel model, String userType,
			String isFdy, String userName) throws Exception {
		
		return myDAO.queryJxhshResult(model, userType, isFdy, userName);
	}
	
	/**
	 * 查询奖学金表头
	 * 
	 * @param model
	 * @param userType
	 * @param isFdy
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjshTitle(XnjxjModel model,
			String userType, String isFdy) {
		String[] en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
				"jxjmc", "dypm", "zypm", "typm", "zhpm", "sh" };
		String[] cn = new String[] { "pk","color", "dis", "行号", "学号", "姓名", "班级", "学年", "学期",
				"奖学金", "德育排名", "智育排名", "体育排名", "综测排名", "学校审核" };
		if (UserTypePd.isXy(userType)) {
			if (UserTypePd.isFdy(isFdy)) {
				en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
						"jxjmc", "dypm", "zypm", "typm", "zhpm", "sh", "xysh",
						"xxsh" };
				cn = new String[] { "pk","color", "dis", "行号", "学号", "姓名", "班级", "学年", "学期",
						"奖学金", "德育排名", "智育排名", "体育排名", "综测排名", "辅导员审核", "学院审核",
						"学校审核" };
			} else {
				en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
						"jxjmc", "dypm", "zypm", "typm", "zhpm", "sh", "xxsh" };
				cn = new String[] { "pk","color", "dis", "行号", "学号", "姓名", "班级", "学年", "学期",
						"奖学金", "德育排名", "智育排名", "体育排名", "综测排名", "学院审核", "学校审核" };
			}
		}
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询班级实际获奖通过人数
	 * @param model
	 * @param isFdy
	 * @return
	 */
	public String queryBjhjrs(XnjxjModel model, String isFdy) {
		String appendSql = " and xysh='通过'";
		if (UserTypePd.isFdy(isFdy)) {
			appendSql = " and fdysh='通过'";
		}
		return myDAO.queryBjhjtgrs(model, appendSql);
	}
	
	/**
	 * 查询班级获奖名额
	 * @param model
	 * @return
	 */
	public String queryBjhjme(XnjxjModel model) {
		return myDAO.queryBjhjme(model);
	}
	
	/**
	 * 修改奖学金审核结果
	 * @param pkValue
	 * @param jg
	 * @param userType
	 * @param isFdy
	 * @return
	 * @throws SQLException
	 */
	public boolean updateJxjshResult(String[] pkValue, String jg,
			String userType, String isFdy) throws SQLException{
		if (pkValue != null && pkValue.length > 0) {
			return myDAO.updateJxjshResult(pkValue, jg, 
					UserTypePd.isXy(userType) ? 
							(UserTypePd.isFdy(isFdy) ? "fdysh" : "xysh") : "xxsh");
		} else {
			return false;
		}
	}
	
	/**
	 * 查询单个奖学金详细信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryXnjxjDetails(String pkValue,
			String userType, String fdyFlag) {
		return myDAO.queryXnjxjDetails(pkValue, userType, fdyFlag);
	}
	
	//审核列表
	public List<HashMap<String, String>> getShList() {
		DAO dao = DAO.getInstance();
		return dao.getChkList(3);
	}
	
	/**
	 * 修改奖学金单个审核结果
	 * @param shzd fdysh,xysh,xxsh
	 * @param shyj fdyyj,xyyj,xxyj
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateXnjxjshResult(String userType, String isFdy,
			XnjxjModel model, String pkValue) throws Exception {
		String  zd = UserTypePd.isXy(userType) ? 
				(UserTypePd.isFdy(isFdy) ? "fdy" : "xy") : "xx";
		return myDAO.updateXnjxjshResult(zd + "sh", zd + "yj", zd + "shsj",
				model, pkValue);
	}
}
