package xgxt.wjcf.nbcs;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.date.DateUtils;

public class WjcfNbcsService {

	/**
	 * 修改处分下发通知状态
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public boolean updateXfcftz(String[] keys) throws Exception{
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		return dao.updateXfcftz(keys);
	}
	
	/**
	 * 查询单个学生的待拟处分信息
	 * @param xh
	 * @return
	 */
	public List<String[]> queryNcftzBystu(String xh) {
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		return dao.queryNcftzBystu(xh);
	}
	
	/**
	 * 下发拟处分通知表头
	 * @return
	 */
	public List<HashMap<String, String>> getNcfxxTitle() {
		String[] en = { "pk", "xn", "nd", "cflbmc", "cfyymc", "wjsj", "sfsb", "xftz", "xxsh", "cfsj", "cfwh" };
		String[] cn = { "pk", "学年", "年度", "处分类别", "处分原因", "违纪时间", "当事人是否申辩",  "是否已下发拟处分通知", "学校审核", "决定时间", "决定文号" };
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		return dao.getTitle(en, cn);
	}
	
	/**
	 * 保存学生填写的通知书信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveNcftzxx(WjcfNbcsModel model) throws Exception{
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		return dao.saveNcftzxx(model);
	}
	
	/**
	 * 显示学生填写的通知书信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewNcftzxx(String pkValue) throws Exception{
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		HashMap<String, String> rs = dao.viewNcftzxx(pkValue);
		return rs;
	}
	
	/**
	 * 通知书打印
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> tzsPrint(String pkValue) {
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = dao.tzsPrint(pkValue);
		rs.put("year", DateUtils.getYear());
		rs.put("mon", DateUtils.getMonth());
		rs.put("date", DateUtils.getDayOfMonth());
		return rs;
	}
	
	/**
	 * 呈报表打印
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> cbbPrint(String pkValue, String cfpk) {
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		return dao.cbbPrint(pkValue, cfpk);
	}
}
