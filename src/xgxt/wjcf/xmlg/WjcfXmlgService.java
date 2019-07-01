package xgxt.wjcf.xmlg;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class WjcfXmlgService {

	private WjcfXmlgDAO dao = null;
	
	private static final String FDY = "fdy";
	private static final String XY = "xy";
	
//	��ѯ���ִ�ӡ������Ϣ
	public HashMap<String, String> getCfbprint(WjcfXmlgModel model) {
		dao = new WjcfXmlgDAO();
		return dao.getCfbprint(model);
	}
	
//	��ѯ���ִ�ӡ������Ϣ
	public HashMap<String, String> getCfbprintDetails(WjcfXmlgModel model) {
		dao = new WjcfXmlgDAO();
		return dao.getCfbprintDetails(model);
	}
	
	/**
	 * �����걨��ѯ��ͷ�ֶ���Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> queryCfsbxxTitle() {
		dao = new WjcfXmlgDAO();
		return dao.queryCfsbxxTitle();
	}
	
	/**
	 * �����걨��ѯ���ݽ����Ϣ������ҳ��
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
	 * �����걨��Ϣ�޸�
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewCfsbxx(WjcfXmlgModel model) {
		dao = new WjcfXmlgDAO();
		return dao.viewCfsbxx(model);
	}
	
	/**
	 * �޸Ĵ����걨��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiCfsbxx(WjcfXmlgModel model){
		dao = new WjcfXmlgDAO();
		return dao.modiCfsbxx(model);
	}
	
	/**
	 * ɾ�������걨��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delCfsbxx(WjcfXmlgModel model){
		dao = new WjcfXmlgDAO();
		return dao.delCfsbxx(model);
	}
	
	/**
	 * ������˲�ѯ���
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
	 * У�촦���걨��ѯ���ݽ����Ϣ������ҳ��
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
	 * ������˲�ѯ��ͷ
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
	 * �����걨�����Ϣ��ʾ
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
	 * �����걨��Ϣ�޸�(У��)
	 * 
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewCfsbxxByxb(WjcfXmlgModel model) {
		dao = new WjcfXmlgDAO();
		return dao.viewCfsbxxByxb(model);
	}
	
	/**
	 * ���洦���걨�����Ϣ
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
	 * ѧУ�û��������洦�������Ϣ
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
	 * ������˴����걨��Ϣ
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
	 * ѧУ�û�������������˴�����Ϣ
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
	 * ѧԺ��ѯ��У�쿴����(����ҳ)
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
	 * ѧУ��ѯ��У�쿴����(����ҳ)
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
	 * ��ѯ��У�쿴��ͷ����
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckTitle() {
		dao = new WjcfXmlgDAO();
		return dao.queryLxckTitle();
	}
	
	/**
	 * ��ѯ��У�쿴��ͷ����
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckTitleByxb() {
		dao = new WjcfXmlgDAO();
		return dao.queryLxckTitleByxb();
	}
	
	/**
	 * ��ѯѧ����У�쿴��Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryStuLxckxx(String pkValue) {
		dao = new WjcfXmlgDAO();
		return dao.queryStuLxckxx(pkValue);
	}
	
	/**
	 * ����ѯ����Ϊ��У�쿴�Ĵ�����Ϣ
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
	 * ������У�쿴������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxcksqxx(WjcfXmlgModel model) throws Exception{
		dao = new WjcfXmlgDAO();
		return dao.saveLxcksqxx(model);
	}
	
	/**
	 * ��У�쿴��ӡ
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
			c.add(Calendar.MONTH, 5);//��Calendar.getInstance()������Ǵ�0��ʼ������Ҫ��һ����
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
	 * ɾ����У�쿴��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delLxckxx(WjcfXmlgModel model) throws Exception {
		dao = new WjcfXmlgDAO();
		return dao.delLxckxx(model);
	}
	
	/**
	 * ��ѯ������У�쿴��Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewLxck(String pkValue) {
		dao = new WjcfXmlgDAO();
		return dao.viewLxck(pkValue);
	}
	
	/**
	 * �޸���У�쿴������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiLxcksqxx(WjcfXmlgModel model) throws Exception {
		dao = new WjcfXmlgDAO();
		return dao.modiLxcksqxx(model);
	}
	
	/**
	 * ��ѯ��У�쿴���������Ϣ
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
	 * ѧУ�û���ѯ��У�쿴���������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryLxckshxxByxb(WjcfXmlgModel model) {
		dao = new WjcfXmlgDAO();
		return dao.queryLxckshxxByxb(model);
	}
	
	/**
	 * ������У�쿴�����Ϣ
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
	 * ����������У�쿴��Ϣ
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
	 * У�챣����У�쿴�����Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxckshxxByxb(WjcfXmlgModel model) throws Exception {
		dao = new WjcfXmlgDAO();
		return dao.saveLxckshxxByxb(model);
	}
	
//	��������б�
	public List<HashMap<String, String>> loadJcList() {
		dao = new WjcfXmlgDAO();
		return dao.loadJcList();
	}
	
	/**
	 * ����������У�쿴��Ϣ
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
