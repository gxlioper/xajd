package xgxt.wjcf.nbcs;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.date.DateUtils;

public class WjcfNbcsService {

	/**
	 * �޸Ĵ����·�֪ͨ״̬
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public boolean updateXfcftz(String[] keys) throws Exception{
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		return dao.updateXfcftz(keys);
	}
	
	/**
	 * ��ѯ����ѧ���Ĵ��⴦����Ϣ
	 * @param xh
	 * @return
	 */
	public List<String[]> queryNcftzBystu(String xh) {
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		return dao.queryNcftzBystu(xh);
	}
	
	/**
	 * �·��⴦��֪ͨ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getNcfxxTitle() {
		String[] en = { "pk", "xn", "nd", "cflbmc", "cfyymc", "wjsj", "sfsb", "xftz", "xxsh", "cfsj", "cfwh" };
		String[] cn = { "pk", "ѧ��", "���", "�������", "����ԭ��", "Υ��ʱ��", "�������Ƿ����",  "�Ƿ����·��⴦��֪ͨ", "ѧУ���", "����ʱ��", "�����ĺ�" };
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		return dao.getTitle(en, cn);
	}
	
	/**
	 * ����ѧ����д��֪ͨ����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveNcftzxx(WjcfNbcsModel model) throws Exception{
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		return dao.saveNcftzxx(model);
	}
	
	/**
	 * ��ʾѧ����д��֪ͨ����Ϣ
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
	 * ֪ͨ���ӡ
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
	 * �ʱ����ӡ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> cbbPrint(String pkValue, String cfpk) {
		WjcfNbcsDAO dao = new WjcfNbcsDAO();
		return dao.cbbPrint(pkValue, cfpk);
	}
}
