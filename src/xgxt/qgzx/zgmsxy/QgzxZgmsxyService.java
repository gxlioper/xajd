package xgxt.qgzx.zgmsxy;

import java.util.HashMap;
import java.util.List;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧģ���й�����ѧԺService</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-11-04</p>
 */
public class QgzxZgmsxyService {
	/**
	 * ��ѯ��λ��Ϣ
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwInfo(String pk,String pkValue){
		QgzxZgmsxyDAO dao = new QgzxZgmsxyDAO();
		return dao.getGwInfo(pk,pkValue);
	}
	
	/**
	 * ��ȡ��λ��ѧ�������Ϣ
	 * */
	public List getStuPayInfo(String pk,String pkValue){
		QgzxZgmsxyDAO dao = new QgzxZgmsxyDAO();
		return dao.getStuPayInfo(pk,pkValue);
	}
}
