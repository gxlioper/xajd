
package xgxt.pjpy.commonutils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��������abstract service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-19</p>
 */
public abstract class CommonService {

	/**
	 * �����������LIST��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public abstract ArrayList<HashMap<String, String>> getTableQryTitle(String tableName);
	
	/**
	 * ��������ȡ��ѯ���
	 * @param object
	 * @return
	 */
	public abstract ArrayList<String[]> getTableQryResult(Object object);
}
