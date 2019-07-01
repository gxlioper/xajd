
package xgxt.pjpy.ynys;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class YxbysService extends PjpyYnysService {

	YxbysDAO dao = null;
	
	/**
	 * ѧ��������Ϣ
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> stuDetailsInfo(String xh) throws Exception {
		dao = new YxbysDAO();
		return dao.stuDetailsInfo(xh);
	}
	
	/**
	 * �����ҵ�����뱣��
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveYxbysxx(YxbysModel model, HttpServletRequest request) throws Exception {
		dao = new YxbysDAO();
		dao.saveYxbysfzxx(model, request);
		return dao.saveYxbysxx(model, request);
	}
	
	/**
	 * �����ҵ����ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYxbysQryTitle() throws Exception {
		dao = new YxbysDAO();
		String[] enList = new String[] { "pk", "rownum", "xn", "nj","xh", "xm", "xymc",
				"zymc", "bjmc", "grry", "yxsh", "xxsh" };
		String[] cnList = new String[] { "pk", "�к�", "ѧ��", "�꼶", "ѧ��", "����", "ѧԺ����",
				"רҵ����", "�༶����", "��������", "Ժϵ���", "ѧУ���" };
		return dao.getQryTitle(enList, cnList);
	}
	
	/**
	 * �����ҵ����ѯ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> yxbysQryRes(YxbysModel model) throws Exception {
		dao = new YxbysDAO();
		return dao.yxbysQryRes(model);
	}
	
	/**
	 * ��ʾ�����ҵ����Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewYxbysxx(String pkValue) {
		dao = new YxbysDAO();
		return dao.viewYxbysxx(pkValue);
	}
	
	/**
	 * �޸ı�����
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateYxbysxx(String pkValue, YxbysModel model, HttpServletRequest request) throws Exception {
		dao = new YxbysDAO();
		dao.saveYxbysfzxx(model, request);
		return dao.modiYxbysxx(pkValue, model, request);
	}
	
	/**
	 * ����ɾ��
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delYxbysxx(String[] keys, HttpServletRequest request) throws Exception {
		dao = new YxbysDAO();
		return dao.delYxbysxx(keys, request);
	}
	
	/**
	 * �����ҵ�������ӡ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> yxbysPrint(String pkValue) throws Exception {
		dao = new YxbysDAO();
		return dao.yxbysPrint(pkValue);
	}
}
