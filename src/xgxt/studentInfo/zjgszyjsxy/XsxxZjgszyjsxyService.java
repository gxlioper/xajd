package xgxt.studentInfo.zjgszyjsxy;

import java.util.List;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭����ְҵ����ѧԺѧ����ϢService</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-11-06</p>
 */
public class XsxxZjgszyjsxyService {
	XsxxZjgszyjsxyDAO dao = new XsxxZjgszyjsxyDAO();
	
	/**
	 * ��ȡ�����б�
	 * @param tableName
	 * @param columns
	 * @return List
	 * */
	public List getDmList(String tableName,String[] columns){
		return dao.getDmList(tableName,columns);
	}
}
