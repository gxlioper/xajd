package xgxt.pjpy.zjjj.service;

import java.util.HashMap;
import java.util.List;

import xgxt.pjpy.zjjj.dao.ZjjjZhszcpDAO;

public class ZjjjZhszcpService {

	private ZjjjZhszcpDAO dao = ZjjjZhszcpDAO.getInstance();
	
	public static ZjjjZhszcpService service = new ZjjjZhszcpService();
	
	//SERVICE 单例模式
	public static ZjjjZhszcpService getInstance() {
		return service;
	}
	
	/**
	 * 德育等级列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> dycjList() throws Exception {
		return dao.dycjList();
	}
}
