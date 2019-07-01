
package xgxt.wjcf.hygxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������ѧԺΥ�ʹ���Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-23</p>
 */
public class WjcfHygxyService {

	WjcfHygxyDAO dao = null;//���ݲ���ͨ��DAO
	
	/**
	 * ͨ�������õ����ѯ��ͷ
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getQueryTitle(String tableName) throws Exception {
		dao = new WjcfHygxyDAO();
		List<HashMap<String, String>> titleList = null;
		String[] enList = null;//Ӣ���б�
		String[] cnList = null;//�����б�
		if (!StringUtils.isNull(tableName)) {
			if (StringUtils.isEqual(tableName, "wjcf_gzjyb")) {
				enList = new String[] { "pk", "rownum", "xh", "xm", "nj", "xn",
						"xq", "bjmc", "jyzt" };
				cnList = new String[] { "pk", "�к�", "ѧ��", "����", "�꼶", "ѧ��",
						"ѧ��", "�༶����", "��������" };
			}
		}
		titleList = dao.getQueryTitle(enList, cnList);
		return titleList;
	}
	
	/**
	 * ���ٽ�����ѯ���
	 * @param gzjyModel
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGzjyQueryResult(WjcfGzjyModel gzjyModel) throws Exception{
		dao = new WjcfHygxyDAO();
		return dao.getGzjyQueryResult(gzjyModel);
	}
	
	/**
	 * ��ȡѧ��Υ����Ϣ
	 * @param xh
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuWjxx(String xh, String pkValue) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.getStuWjxx(xh, pkValue);
	}

	/**
	 * ������ٽ�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveGzjy(WjcfGzjyModel model, HttpServletRequest request) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.saveGzjy(model, request);
	}
	
	/**
	 * ��ʾ�������ٽ�����Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewGzjy(String pkValue) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.viewGzjy(pkValue);
	}
	
	/**
	 * ����ɾ�����ٽ�����Ϣ
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteGzjy(String[] keys, HttpServletRequest request) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.deleteGzjy(keys, request);
	}
	
	/**
	 * �޸ı�����ٽ�����Ϣ
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateGzjy(WjcfGzjyModel model, String pkValue, HttpServletRequest request) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.updateGzjy(model, pkValue, request);
	}
	
	/**
	 * ����ת�ַ���
	 * @param list
	 * @return
	 */
	public String listToString(String[] list) {
		dao = new WjcfHygxyDAO();
		if (list != null && list.length > 0) {
			String str= "";
			for (String s : list) {
				str = str + s + "!"; 
			}
			return StringUtils.isNull(str) ? "" : str.substring(0, str.length() - 1);
		} 
		return "";
	}
	/**
	 * ��������
	 * @param pkVal
	 * @param cfwh
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean plfw(String pkVal, String cfwh, String cfsj) throws Exception{
		dao = new WjcfHygxyDAO();
		return dao.plfw(pkVal, cfwh, cfsj);
	}
	
	public List<String[]> wyhslQuery(WjcfGzjyModel model) throws Exception {
		dao = new WjcfHygxyDAO();
		return dao.wyhslQuery(model);
	}
	
	public List<HashMap<String, String>> wyhslTitle() {
		dao = new WjcfHygxyDAO();
		return dao.wyhslTitle();
	}
	
	public HashMap<String, String> wyhslone(String pkValue) {
		dao = new WjcfHygxyDAO();
		return dao.wyhslone(pkValue);
	}
	
	public boolean updateWhysl(WjcfGzjyModel model, String pkValue, HttpServletRequest request) throws Exception{
		dao = new WjcfHygxyDAO();
		return dao.updateWhysl(model, pkValue, request);
	}
	
	/**
	 * ��������
	 * @param pkVal
	 * @param cfwh
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean plsl(String pkVal, String wyhsl, String wyhsllr) throws Exception{
		dao = new WjcfHygxyDAO();
		return dao.plsl(pkVal, wyhsl, wyhsllr);
	}
}
