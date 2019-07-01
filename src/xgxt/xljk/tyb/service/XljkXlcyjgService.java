/*
 * @className: XljkXlcyjgService.java
 * 
 * @time: 2010-4-30
 * 
 * @copyRight hz-zf
 * 
 */

package xgxt.xljk.tyb.service;

import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.pjpy.PjpyTyService;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.tyb.dao.XljkXlcyjgDAO;
import xgxt.xljk.tyb.model.XljkXlcyjgModel;

/**
 * 心理健康 - 心理测验结果维护所用的service,
 * 包括对大学生人格因素心理测试结果和卡特尔人格因素测试结果的维护
 * 
 * @author  lt
 * @version 1.0 2010-4-30
 * @see     xgxt.xljk.tyb.dao.XljkXlcyjgDAO
 */
public class XljkXlcyjgService {

	XljkXlcyjgDAO dao = new XljkXlcyjgDAO();
	
	/**
	 * 大学生人格因素测验结果表头
	 * @return
	 */
	public List<HashMap<String, String>> queryDxsrgysclJgTitle() {
		String[] enList = { "pk", "r", "xh", "xm", "xb", "bjmc",
				"cssj", "qtzz", "jsfl", "yyz", "sjz", "zf" };
		String[] cnList = { "pk", "行号", "学号", "姓名", "性别", "班级",
				"测试时间", "躯体症状测试分值", "精神分裂测试分值", "抑郁症测试分值", "神经症测试分值", "总分" };
		return getQueryTitle(enList, cnList);
	}
	
	/**
	 * 查询大学生人格因素测验结果
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDxsrgysjgResult(XljkXlcyjgModel model, String userName) throws Exception{
		return dao.queryDxsrgysjgResult(model, userName);
	}
	
	/**
	 * 增加大学生人格因素测验信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addDxsrgyscyXx(XljkXlcyjgModel model) throws Exception{
		return dao.addDxsrgyscyXx(model);
	}
	
	/**
	 * 修改大学生人格因素测验信息
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateDxsrgyscyXx(XljkXlcyjgModel model, String pkValue)
			throws Exception {
		return dao.updateDxsrgyscyXx(model, pkValue);
	}
	
	/**
	 * 显示大学生人格因素测验信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewDxsrgyscyXx(String pkValue) {
		return dao.viewDxsrgyscyXx(pkValue);
	}
	
	/**
	 * 删除大学生人格因素测验信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean deletDxsrgyscyXx(String[] pkValue, String tableName) throws Exception{
		if (pkValue != null && pkValue.length > 0) {
			String[] sqlArr = new String[pkValue.length]; 
			for (int i = 0; i < pkValue.length; i++) {
				StringBuilder sb = new StringBuilder("delete from ");
				sb.append(tableName);
				sb.append(" where xh||cssj='");
				sb.append(pkValue[i]);
				sb.append("'");
				sqlArr[i] = sb.toString();
			}
			return dao.deleteDxsrgyscyXx(sqlArr);
		} 
		return false;
	}
	

	/**
	 * 查询卡特尔人格因素信息结果
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryKtergysResult(XljkXlcyjgModel model,
			String userName) throws Exception {
		return dao.queryKtergysResult(model, userName);
	}
	
	/**
	 * 查询卡特尔人格因素查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryKtergysTitle() {
		String[] enList = { "pk", "r", "xh", "xm", "xb", "bjmc",
				"cssj", "ayz", "byz", "cyz", "dyz", "eyz" };
		String[] cnList = { "pk", "行号", "学号", "姓名", "性别", "班级",
				"测试时间", "A因子分值", "B因子分值", "C因子分值", "D因子分值", "E因子分值" };
		return getQueryTitle(enList, cnList);
	}
	
	/**
	 * 增加卡特尔人格因素信息结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addKtergysxx(XljkXlcyjgModel model) throws Exception{
		return dao.addKtergysxx(model);
	}

	/**
	 * 修改卡特尔人格因素信息结果
	 * @param model
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateKtergysxx(XljkXlcyjgModel model, String pkValue)
			throws Exception {
		return dao.updateKtergysxx(model, pkValue);
	}
	
	/**
	 * 显示卡特尔人格因素信息结果
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewKtergysxx(String pkValue) {
		return dao.viewKtergysxx(pkValue);
	}
	
	/**
	 * 查询大学生评语信息
	 * @param rs
	 * @return
	 */
	public List<String[]> queryDxsrgpyResult(HashMap<String, String> rs) {
		return dao.queryDxsrgpyResult(rs);
	}
	
	/**
	 * 查询不在基础代码表中的大学生人格因素因子
	 * @return
	 * @throws Exception
	 */
	public String queryDxsyzNotExistsDB() throws Exception{
		String result = "";
		PjpyTyService service = new PjpyTyService();
		
		//数据库中的因子信息
		String[] second = dao.queryDxsyzNotExistsDB();
		//默认的因子信息
		String[] first = {GlobalsVariable.XLJK_DXSRGYS_QTZZYZ,
				GlobalsVariable.XLJK_DXSRGYS_JSFLYZ, 
				GlobalsVariable.XLJK_DXSRGYS_YYZYZ,
				GlobalsVariable.XLJK_DXSRGYS_SJZYZ};
		
		//将二个数组中不重复的数据取出来
		String[] rs = service.checkCfsj(first, second);
		if (rs != null && rs.length > 0) {
			for (String s : rs) {
				result += s;
				result += ",";
			}
		}
		return StringUtils.isNotNull(result) ? result.substring(0, result.length() - 1) : result;
	}
	
	/**
	 * 查询卡特尔人格因素评语信息
	 * @param rs
	 * @return
	 */
	public List<String[]> queryKteyzpyResult(HashMap<String, String> rs) {
		return dao.queryKteyzpyResult(rs);
	}
	
	/**
	 * 查询不在基础代码表中的卡特尔人格因素因子
	 * @return
	 * @throws Exception
	 */
	public String queryKteyzNotExistsDB() throws Exception{
		String result = "";
		PjpyTyService service = new PjpyTyService();
		
		//默认的因子信息
		String[] yzArr = dao.getKteYzArr();
		//数据库中的因子信息
		String[] secondArr = dao.queryKteyzNotExistsDB();
		yzArr = getYzArr(yzArr);
		
		//取出二个数组中不重复数据
		String[] rs = service.checkCfsj(yzArr, secondArr);
		if (rs != null && rs.length > 0) {
			for (String s : rs) {
				result += s;
				result += ",";
			}
		}
		return StringUtils.isNotNull(result) ? result.substring(0, result.length() - 1) : result;
	}
	
	public String[] getYzArr(String[] yzArr) {
		if (yzArr != null && yzArr.length > 0) {
			String[] rs = new String[yzArr.length];
			for (int i=0;i<yzArr.length;i++) {
				rs[i] = yzArr[i].replaceAll("yz", "").toUpperCase();
			}
			return rs;
		} else {
			return null;
		}
	}
	
	private List<HashMap<String, String>> getQueryTitle(String[] en, String[] cn) {
		DAO mydao = DAO.getInstance();
		return mydao.arrayToList(en, cn); 
	}
}
