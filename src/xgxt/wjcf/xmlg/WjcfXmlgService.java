package xgxt.wjcf.xmlg;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class WjcfXmlgService {

	private WjcfXmlgDAO dao = null;
	
	private static final String FDY = "fdy";
	private static final String XY = "xy";
	
//	查询处分打印报表信息
	public HashMap<String, String> getCfbprint(WjcfXmlgModel model) {
		dao = new WjcfXmlgDAO();
		return dao.getCfbprint(model);
	}
	
//	查询处分打印报表信息
	public HashMap<String, String> getCfbprintDetails(WjcfXmlgModel model) {
		dao = new WjcfXmlgDAO();
		return dao.getCfbprintDetails(model);
	}
	
	/**
	 * 处分申报查询表头字段信息
	 * @return
	 */
	public List<HashMap<String, String>> queryCfsbxxTitle() {
		dao = new WjcfXmlgDAO();
		return dao.queryCfsbxxTitle();
	}
	
	/**
	 * 处分申报查询数据结果信息（带分页）
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryCfsbxxResult(WjcfXmlgModel model, String userType, String[] colList)
			throws Exception {
		dao = new WjcfXmlgDAO();
		if (FDY.equalsIgnoreCase(userType) || XY.equalsIgnoreCase(userType)) {
			return dao.queryCfsbxxResultByxy(model, colList);
		} else {
			return dao.queryCfsbxxResultByxx(model, colList);
		}
	}
	
	/**
	 * 处分申报信息修改
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewCfsbxx(WjcfXmlgModel model) {
		dao = new WjcfXmlgDAO();
		return dao.viewCfsbxx(model);
	}
	
	/**
	 * 修改处分申报信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiCfsbxx(WjcfXmlgModel model){
		dao = new WjcfXmlgDAO();
		return dao.modiCfsbxx(model);
	}
	
	/**
	 * 删除处分申报信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delCfsbxx(WjcfXmlgModel model){
		dao = new WjcfXmlgDAO();
		return dao.delCfsbxx(model);
	}
	
	/**
	 * 处分审核查询结果
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryCfsbshResult(WjcfXmlgModel model, String userType)
			throws Exception {
		dao = new WjcfXmlgDAO();
		String[] colList = {};
		if ("xy".equalsIgnoreCase(userType)) {
			colList = new String[] { "pk", "dis", "r", "xn", "nd", "xh", "xm",
					 "bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh", "xysh",
					"xxsh" };
			return dao.queryCfsbxxResultByxy(model, colList);
		} else {
			colList = new String[] { "pk", "dis", "r", "xn", "nd", "xh", "xm",
					 "bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh", "xxsh",
					"xndsh" };
			return dao.queryCfsbxxResultByxx(model, colList);
		}
	}
	
	public List<String[]> queryCfsbshResult(WjcfXmlgModel model) throws Exception {
		dao = new WjcfXmlgDAO();
		String[] colList = { "pk", "dis", "r", "xn", "nd", "xh", "xm",
				 "bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh", 
		"xndsh" };
		return dao.queryCfsbxxResultByxx(model, colList);
	}
	
	/**
	 * 校办处分申报查询数据结果信息（带分页）
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryCfsbxxResultByxb(WjcfXmlgModel model)
			throws Exception {
		dao = new WjcfXmlgDAO();
		String[] colList = { "pk", "dis", "r", "xn", "nd", "xh", "xm",
				 "bjmc", "cflbmc", "cfyymc", "cfsj", "cfwh", 
		"xndsh" };
		return dao.queryCfsbxxResultByxb(model, colList);
	}
	
	/**
	 * 处分审核查询表头
	 * @param userType
	 * @return
	 */
	public List<HashMap<String, String>> queryCfsbshTitle(String userType) {
		dao = new WjcfXmlgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.queryCfsbshxxByxyTitle();
		} else {
			return dao.queryCfsbshxxByxxTitle();
		}
	}
	
	public List<HashMap<String, String>> queryCfsbshxxByxbTitle() {
		dao = new WjcfXmlgDAO();
		return dao.queryCfsbshxxByxbTitle();
	}
	
	/**
	 * 处分申报审核信息显示
	 * @param model
	 * @param userType
	 * @return
	 */
	public HashMap<String, String> viewCfsbshxx(WjcfXmlgModel model, String userType) {
		dao = new WjcfXmlgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.viewCfsbxxByxy(model);
		} else {
			return dao.viewCfsbxxByxx(model);
		}
	}
	
	/**
	 * 处分申报信息修改(校办)
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewCfsbxxByxb(WjcfXmlgModel model) {
		dao = new WjcfXmlgDAO();
		return dao.viewCfsbxxByxb(model);
	}
	
	/**
	 * 保存处分申报审核信息
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbshxx(WjcfXmlgModel model, String userType) throws Exception{
		dao = new WjcfXmlgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.saveCfsbshxxByxy(model);
		} else {
			return dao.saveCfsbshxxByxx(model);
		}
	}
	
	/**
	 * 学校用户操作保存处分审核信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbshxxByxb(WjcfXmlgModel model) throws Exception {
		dao = new WjcfXmlgDAO();
		return dao.saveCfsbshxxByxb(model);
	}
	
	/**
	 * 批量审核处分申报信息
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbplxx(WjcfXmlgModel model, String userType) throws Exception{
		dao = new WjcfXmlgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.saveCfsbplxxByxy(model);
		} else {
			return dao.saveCfsbplxxByxx(model);
		}
	}
	
	/**
	 * 学校用户操作，批量审核处分信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfsbplxxByxb(WjcfXmlgModel model) throws Exception{
		dao = new WjcfXmlgDAO();
		return dao.saveCfsbplxxByxb(model);
	}
	
	public List<HashMap<String, String>> loadshList() {
		dao = new WjcfXmlgDAO();
		return dao.loadShlist();
	}
	
	/**
	 * 学院查询留校察看数据(带分页)
	 * 
	 * @param model
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryLxckResult(WjcfXmlgModel model, String userType) throws Exception {
		dao = new WjcfXmlgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.queryLxckResultByxy(model);
		} else {
			return dao.queryLxckResultByxx(model);
		}
	}
	
	/**
	 * 学校查询留校察看数据(带分页)
	 * 
	 * @param model
	 * @param colList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryLxckResultByxnd(WjcfXmlgModel model) throws Exception {
		dao = new WjcfXmlgDAO();
		return dao.queryLxckResultByxnd(model);
	}
	
	/**
	 * 查询留校察看表头数据
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckTitle() {
		dao = new WjcfXmlgDAO();
		return dao.queryLxckTitle();
	}
	
	/**
	 * 查询留校察看表头数据
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckTitleByxb() {
		dao = new WjcfXmlgDAO();
		return dao.queryLxckTitleByxb();
	}
	
	/**
	 * 查询学生留校察看信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryStuLxckxx(String pkValue) {
		dao = new WjcfXmlgDAO();
		return dao.queryStuLxckxx(pkValue);
	}
	
	/**
	 * 仅查询处分为留校察看的处分信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryWjcfxxByLxck(WjcfXmlgModel model) throws Exception{
		dao = new WjcfXmlgDAO();
		return dao.queryWjcfxxByLxck(model);
	}
	
	public List<HashMap<String, String>> queryWjcfxxByLxck() {
		dao = new WjcfXmlgDAO();
		return dao.queryWjcfxxByLxck();
	}
	
	/**
	 * 保存留校察看申请信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxcksqxx(WjcfXmlgModel model) throws Exception{
		dao = new WjcfXmlgDAO();
		return dao.saveLxcksqxx(model);
	}
	
	/**
	 * 留校察看打印
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> lxckPrint(String pkValue) {
		dao = new WjcfXmlgDAO();
		return dao.lxckPrint(pkValue);
	}
	
	public boolean comTime(String cfsj) {
		Calendar c = Calendar.getInstance();
		if (cfsj.length()==8) {
			c.set(Integer.parseInt(cfsj.substring(0, 4)), Integer.parseInt(cfsj
					.substring(4, 6)), Integer.parseInt(cfsj.substring(6, 8)));
			c.add(Calendar.MONTH, 5);//因Calendar.getInstance()里面的是从0开始的所以要减一个月
//			System.out.println(c.get(Calendar.YEAR));
//			System.out.println(c.get(Calendar.MONTH));
//			System.out.println(c.get(Calendar.DAY_OF_MONTH));
			int num = c.compareTo(Calendar.getInstance());
//			System.out.println(Calendar.getInstance().get(Calendar.YEAR));
//			System.out.println(Calendar.getInstance().get(Calendar.MONTH));
//			System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//			System.out.println(num);
			return num <= 0 ? true : false;
		} else {
			return false;
		}
	}
	
	/**
	 * 删除留校察看信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delLxckxx(WjcfXmlgModel model) throws Exception {
		dao = new WjcfXmlgDAO();
		return dao.delLxckxx(model);
	}
	
	/**
	 * 查询单条留校察看信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewLxck(String pkValue) {
		dao = new WjcfXmlgDAO();
		return dao.viewLxck(pkValue);
	}
	
	/**
	 * 修改留校察看申请信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiLxcksqxx(WjcfXmlgModel model) throws Exception {
		dao = new WjcfXmlgDAO();
		return dao.modiLxcksqxx(model);
	}
	
	/**
	 * 查询留校察看单个审核信息
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryLxckshxx(WjcfXmlgModel model,
			String userType) {
		dao = new WjcfXmlgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.queryLxckshxxByxy(model);
		} else {
			return dao.queryLxckshxxByxx(model);
		}
	}
	
	/**
	 * 学校用户查询留校察看单个审核信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryLxckshxxByxb(WjcfXmlgModel model) {
		dao = new WjcfXmlgDAO();
		return dao.queryLxckshxxByxb(model);
	}
	
	/**
	 * 保存留校察看审核信息
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxckDgshxx(WjcfXmlgModel model, String userType) throws Exception {
		dao = new WjcfXmlgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.saveLxckshxxByxy(model);
		} else {
			return dao.saveLxckshxxByxx(model);
		}
	}
	
	/**
	 * 批量保存留校察看信息
	 * @param userType
	 * @param pkValues
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePlshLxckxx(String userType, String[] pkValues,
			WjcfXmlgModel model) throws Exception {
		dao = new WjcfXmlgDAO();
		return dao.savePlshLxckxx(userType, pkValues, model);
	}
	
	/**
	 * 校办保存留校察看审核信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxckshxxByxb(WjcfXmlgModel model) throws Exception {
		dao = new WjcfXmlgDAO();
		return dao.saveLxckshxxByxb(model);
	}
	
//	解除处分列表
	public List<HashMap<String, String>> loadJcList() {
		dao = new WjcfXmlgDAO();
		return dao.loadJcList();
	}
	
	/**
	 * 批量保存留校察看信息
	 * @param userType
	 * @param pkValues
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePlshLxckxxByxb(String[] pkValues,
			WjcfXmlgModel model) throws Exception {
		dao = new WjcfXmlgDAO();
		return dao.savePlshLxckxxByxb(pkValues, model);
	}
	
	public boolean savePlshCfxx(WjcfXmlgModel model, String userType) throws Exception{
		dao = new WjcfXmlgDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.saveCfsbplxxByxy_zjcm(model);
		} else {
			return dao.saveCfsbplxxByxx_zjcm(model);
		}
	}
	
	public static void main(String...strings) {
		WjcfXmlgService s = new WjcfXmlgService();
		System.out.println(s.comTime("20090407"));
		
	}
}
