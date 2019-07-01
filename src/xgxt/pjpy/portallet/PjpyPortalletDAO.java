package xgxt.pjpy.portallet;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class PjpyPortalletDAO {

	private DAO dao = DAO.getInstance();
	
	public static PjpyPortalletDAO mydao = new PjpyPortalletDAO();
	
	public static PjpyPortalletDAO getInstance() {
		return mydao;
	}
	
	/**
	 * 输出表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> titList() throws Exception {
		String[] enList = new String[]{"xn", "jxjmc", "jlje"};
		String[] cnList = new String[]{"学年", "奖学金名称", "金额"};
		return dao.arrayToList(enList, cnList);
	} 
	
	/**
	 * 输出结果只取后5条
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> rsList(String xh) throws Exception {
		return dao
				.rsToVator(
						"select xn,jxjmc,jlje from (select xn,jxjdm,(select b.jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) jxjmc,(select b.jlje from jxjdmb b where a.jxjdm=b.jxjdm) jlje from xsjxjb a where xh=? order by xn desc) where rownum<=5",
						new String[] { xh }, new String[] { "xn", "jxjmc",
								"jlje" });
	}
}
