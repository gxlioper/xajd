package xgxt.pjpy.zjcm.rych;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.zjcm.xnjxj.XnjxjModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.UserTypePd;

public class RychService {
	RychDAO myDAO= new RychDAO();
	RychNewDAO dao = new RychNewDAO();
	public HashMap<String, String> serv_getXsInfo(String xh) {
		// TODO 自动生成方法存根
		return CommonQueryDAO.commonQueryOne("view_stu_details", new String[]{"xh","xm","xb","xymc","zymc","bjmc","zzmmmc","mzmc","csrq"}, "xh", xh);
	}
	
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe 获得评奖学年学期学生排名和成绩
	 */
	public HashMap<String, String> getCjPm(String xh,String xn,String xq) {	
		HashMap<String, String> map= CommonQueryDAO.commonQueryOne("zjcm_zhf", new String[]{"dyf","zyf","zhf","zhpm"}, "xh||xn||xq", xh+xn+xq);
		map.put("dyfpm",myDAO.getPm(xh, xn, xq, "dyf"));
		map.put("zyfpm",myDAO.getPm(xh, xn, xq, "zyf"));
		return map;
	}
	
	/**
	 * 获取荣誉称号列表
	 * @return
	 */
	public List<HashMap<String,String>>serv_getRychList(){
		return myDAO.getRychList();
	}
	
	/**
	 * 判断该生所申请荣誉称号是否重复且是否通过审核
	 */
	public HashMap<String,String> serv_rychSqPd(RychModel model){
		String pk = "xh||xn||xqdm||rychdm";
		String querry = " where  "+pk+"='"+model.getXh()+Base.getJxjsqxn()+Base.getJxjsqxq()+model.getRychdm()+"' and xysh='通过' ";
		return CommonQueryDAO.dao_getInfo("zjcm_rychsqb",new String[]{"xh"},querry);
	}
	
	/**
	 * @author luo
	 * @throws Exception
	 * @describe 判断荣誉称号条件
	 */
	public String Rychtj(RychModel model, String zhpm) {
		return myDAO.Rychtj(model, zhpm);
	}

	public boolean serv_rychSave(RychModel model) throws Exception {
		// TODO 自动生成方法存根
		return myDAO.serv_rychSave(model);
	}

	public boolean saveRychsh(String[] checkVal, String shrych, String shzt, String userType) throws SQLException {
		// TODO 自动生成方法存根
		return myDAO.saveRychSh(checkVal,shrych,shzt,userType);
	}

	public boolean delRychSq(String[] checkVal) throws SQLException {
		// TODO 自动生成方法存根
		return myDAO.delRychSq(checkVal);
	}

	public ArrayList<String[]> getRychSqList(String tableName, RychModel model, String[] colList, String userType, String string) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		return myDAO.getRychSqList(tableName, model, colList,userType, string);
	}

	public boolean saveRychsh(RychModel model, String pk, String shzt, String userType, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return myDAO.saveRychsh(model,pk, shzt, userType, request);
	}

	public HashMap<String, String> getXsRychXx(String pk) {
//		 TODO 自动生成方法存根
		return myDAO.getXsRychXx(pk);
	}
	
	/**
	 * 获得违纪处分列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWjcfList(String xh) {
		return myDAO.getWjcfList(xh);
	}
	
	/**
	 * 获得违纪处分列表
	 * @throws SQLException 
	 * 
	 * @throws Exception
	 */
	public boolean delRych(String[] checkVal) throws SQLException {
		return myDAO.delRychSq(checkVal);
	}

	public HashMap<String, String> getPrintXx(HashMap<String, String> rs) {
		return myDAO.getPrintXx(rs);
	}
	
	//根据荣誉称号代码获得名称
	public String getRychmc(String rychdm) {
		return myDAO.getRychmc(rychdm);
	}
	
	
	/************************李涛加入 2010-4-20 ***********************/
	
	/**
	 * 查询单个学院单个荣誉称号的参评范围
	 * @param model
	 * @return 如果返回多条数据,则作特殊标志 2为返回多条数据
	 * @throws SQLException
	 */
	public String queryRychCpfwByXydm(RychModel model) throws SQLException{
		return dao.queryRychCpfwByXydm(model);
	}
	
	/**
	 * 查询荣誉称号表头
	 * 
	 * @param model
	 * @param userType
	 * @param isFdy
	 * @return
	 */
	public List<HashMap<String, String>> queryRychshTitle(
			String userType, String isFdy) {
		String[] en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
				"rychmc", "dypm", "zypm", "typm", "zhpm", "sh" };
		String[] cn = new String[] { "pk","color", "dis", "行号", "学号", "姓名", "班级", "学年", "学期",
				"荣誉称号", "德育排名", "智育排名", "体育排名", "综测排名", "学校审核" };
		if (UserTypePd.isXy(userType)) {
			if (UserTypePd.isFdy(isFdy)) {
				en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
						"rychmc", "dypm", "zypm", "typm", "zhpm", "sh", "xysh",
						"xxsh" };
				cn = new String[] { "pk","color", "dis", "行号", "学号", "姓名", "班级", "学年", "学期",
						"荣誉称号", "德育排名", "智育排名", "体育排名", "综测排名", "辅导员审核", "学院审核",
						"学校审核" };
			} else {
				en = new String[] { "pk","color", "dis", "r", "xh", "xm", "bjmc", "xn", "xq",
						"rychmc", "dypm", "zypm", "typm", "zhpm", "sh", "xxsh" };
				cn = new String[] { "pk","color", "dis", "行号", "学号", "姓名", "班级", "学年", "学期",
						"荣誉称号", "德育排名", "智育排名", "体育排名", "综测排名", "学院审核", "学校审核" };
			}
		}
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	public List<String[]> queryRychshResult(RychModel model, String userType,
			String fdyFlag, String userName) throws Exception {
		return dao.queryRychshResult(model, userType, fdyFlag, userName);
	}
	
	/**
	 * 查询荣誉称号获奖人数
	 * @param model
	 * @param bmdm
	 * @param bmlb
	 * @return
	 */
	public String queryRychHjrs(RychModel model,  String bmlb) {
		String bmdm = getBmdm(model, bmlb);
		return dao.queryRychHjrs(model, bmdm, bmlb);
	}

	private String getBmdm(RychModel model, String bmlb) {
		String bmdm = "xydm".equalsIgnoreCase(bmlb) ? model.getXydm() : ("zydm"
				.equalsIgnoreCase(bmlb) ? model.getZydm() : ("bjdm"
				.equalsIgnoreCase(bmlb) ? model.getBjdm() : ""));
		return bmdm;
	}
	
	/**
	 * 查询部门荣誉称号通过人数
	 * @param model
	 * @param appendSql
	 * @return
	 */
	public String queryRychTgrs(RychModel model, String bmlb, String isFdy) {
		String bmdm = getBmdm(model, bmlb);
		StringBuilder appendSql = new StringBuilder(" and xysh='通过'");
		if (UserTypePd.isFdy(isFdy)) {
			appendSql = new StringBuilder(" and fdysh='通过'");
		}
		appendSql.append("  and exists (select 1 from view_xsjbxx b where a.pjxh=b.xh and ")
		.append(bmlb)
		.append(" = ?)");
		return dao.queryRychTgrs(model, appendSql.toString(), bmdm);
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
	public boolean updateRychResult(String[] pkValue, String jg,
			String userType, String isFdy) throws SQLException{
		if (pkValue != null && pkValue.length > 0) {
			return dao.updateRychResult(pkValue, jg, 
					UserTypePd.isXy(userType) ? 
							(UserTypePd.isFdy(isFdy) ? "fdysh" : "xysh") : "xxsh");
		} else {
			return false;
		}
	}
	
	/**
	 * 查询单个荣誉称号详细信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryRychDetails(String pkValue,
			String userType, String fdyFlag) {
		return dao.queryRychDetails(pkValue, userType, fdyFlag);
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
	public boolean updateRychshResult(String userType, String isFdy,
			RychModel model, String pkValue) throws Exception {
		String  zd = UserTypePd.isXy(userType) ? 
				(UserTypePd.isFdy(isFdy) ? "fdy" : "xy") : "xx";
		return dao.updateRychshResult(zd + "sh", zd + "yj", zd + "shsj",
				model, pkValue);
	}
	
//	审核列表
	public List<HashMap<String, String>> getShList() {
		DAO dao = DAO.getInstance();
		return dao.getChkList(3);
	}
}
