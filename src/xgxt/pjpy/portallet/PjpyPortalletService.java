package xgxt.pjpy.portallet;

import java.util.HashMap;
import java.util.List;

public class PjpyPortalletService {

	private PjpyPortalletDAO dao = PjpyPortalletDAO.getInstance();
	
	public static PjpyPortalletService service = new PjpyPortalletService();
	
	public static PjpyPortalletService getInstance(){
		return service;
	}
	
	/**
	 * 输出表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> titList() throws Exception {
		return dao.titList();
	}
	
	/**
	 * 输出结果只取后5条
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> rsList(String xh) throws Exception {
		return dao.rsList(xh);
	}
}
