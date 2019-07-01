
package xgxt.pjpy.ynys;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����������������Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-17</p>
 */
public abstract class PjpyYnysService {

	public PjpyYnysDAO dao = null;
	
	/**
	 * ��ѧ������ʱ��
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjsqsj() throws Exception {
		dao = new PjpyYnysDAO();
		return dao.getJxjsqsj();
	}
	
	/**
	 * ������˽������
	 * @param tgRes
	 * @return
	 * @throws Exception
	 */
	public String getShjg(String tgRes) throws Exception {
		return StringUtils.isNull(tgRes) ? "δ���" : (StringUtils.isEqual("tg",
				tgRes) ? "ͨ��" : (StringUtils.isEqual("btg", tgRes) ? "��ͨ��"
				: "δ���"));
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new PjpyYnysDAO();
		return dao.getStuInfo(xh);
	}
	
	public List<String[]> getCjList(String xh) throws Exception {
		dao = new PjpyYnysDAO();
		return dao.getCjList(xh);
	}
	
	public HashMap<String, String> getCjpm(String xh) throws Exception {
		dao = new PjpyYnysDAO();
		return dao.getCjpm(xh);
	}
	
	public HashMap<String, String> getZhfpm(String xh) throws Exception {
		dao = new PjpyYnysDAO();
		return dao.getZhfpm(xh);
	}
}
