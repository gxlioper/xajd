package xgxt.studentInfo.zjgszyjsxy;

import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭����ְҵ����ѧԺѧ����ϢDAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-11-06</p>
 */
public class XsxxZjgszyjsxyDAO extends DAO {
	
	/**
	 * ��ȡ�����б�
	 * @param tableName
	 * @param columns
	 * @return List
	 * */
	public List getDmList(String tableName, String[] columns){
		String sql = "select distinct ";
		for(int i=0; i<columns.length ; i++){
			if(i==columns.length-1){
				sql += columns[i];
			}else{
				sql += columns[i] + ",";
			}
		}
		sql += " from " + tableName;
		return getList(sql, new String[]{}, columns);
	}
}
