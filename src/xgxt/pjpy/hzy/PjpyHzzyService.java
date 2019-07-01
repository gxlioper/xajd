
package xgxt.pjpy.hzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ְԺ��������Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-19</p>
 */
public class PjpyHzzyService {

	PjpyHzzyDAO dao = null;//���ݲ���DAO
	
	/**
	 * 
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTableQryTitle(String tableName) throws Exception {
		dao = new PjpyHzzyDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = null;
		String[] cnList = null;
		if (StringUtils.isEqual(tableName, "cjb")) {
			enList = new String[]{"rownum", "xh", "xm", "xn", "xq", "bjmc", "kcmc", "cj"};
			cnList = new String[]{"�к�", "ѧ��", "����", "ѧ��", "ѧ��", "�༶����", "�γ�����", "�ɼ�"};
		}
		if (StringUtils.isEqual(tableName, "zhszcp")) {
			enList = new String[]{"xh||xn||xq||nd", "rownum", "xh", "xm", "xn", "nd","xq", "bjmc", "xydykpf","zcj","tcj" ,"gzxxcx","zhszcpzf"};
			cnList = new String[]{"pk", "�к�", "ѧ��", "����", "ѧ��","���", "ѧ��", "�༶����", "ѧԺ������", "�ǳɼ�", "��ɼ�", "ѧϰ���·�", "�����ܷ�"};
		}
		topList = dao.getTableQryTitle(enList, cnList);
		return topList;
	}
	
	/**
	 * ��ȡ�ɼ����ѯ���
	 * @param cjbModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjbQryResult(CjbModel cjbModel) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.getCjbQryResult(cjbModel);
	}
	
	/**
	 * �ۺ����ʲ�����ѯ���
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhszcpQryResult(ZhszcpModel zhszcpModel) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.getZhszcpQryResult(zhszcpModel);
	}
	
	/**
	 * �����ڽ�ѧ������ѧ��ȵ��ۺ����ʷ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuXx(String xh) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.getStuXx(xh);
	}
	
	/**
	 * �ۺ����ʲ�������
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpSave(ZhszcpModel zhszcpModel, HttpServletRequest request) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.zhszcpSave(zhszcpModel, request);
	}
	
	/**
	 * �ۺ����ʲ�������ɾ��
	 * @param keys
	 * @param request
	 * @return NULLɾ���ɹ���NOT NULL ɾ��ʧ��
	 * @throws Exception
	 */
	public String zhszcpDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.zhszcpDel(keys, request);
	}
	
	/**
	 * ͨ��������ȡѧ���ۺ����ʲ����ɼ�
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpXx(String pkValue) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.getZhszcpXx(pkValue);
	}
	
	/**
	 * �ۺ����ʲ����ɼ��޸ı���
	 * @param zhszcpModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpModi(ZhszcpModel zhszcpModel, String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.zhszcpModi(zhszcpModel, pkValue, request);
	}
	
	/**
	 * �����ƺŴ�ӡ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> rychPrint(String pkValue) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.rychPrint(pkValue);
	}
	
	public String getPks(String[] keys) throws Exception {
		String pks = "";
		if (keys != null && keys.length > 0) {
			for (int i=0;i<keys.length;i++) {
				if (!StringUtils.isNull(keys[i])) {
					pks += (keys[i]+"-");
				}
			}
			return pks;
		} else {
			return "";
		}
	}
	
	public boolean updateQm(String qm, String keys, String userType, String isFdy, String tableName) throws Exception {
		dao = new PjpyHzzyDAO();
		return dao.updateQm(qm, keys, userType, isFdy, tableName);
	}
}
