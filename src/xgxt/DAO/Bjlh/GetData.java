/**
 * 
 */
package xgxt.DAO.Bjlh;


/**
 * @author Administrator
 * 
 */
public class GetData implements IThirdPartySyncWebService {

	/*
	 * ���� Javadoc��
	 * 
	 * @see com.zfsoft.IThirdPartySyncWebService#getTableColumnInfo(java.lang.String)
	 */
	public Column[] getTableColumnInfo(String tableName) {
		BjlhDAO dao = new BjlhDAO();
		return dao.getTableColumnInfo(tableName);
	}

	/*
	 * ���� Javadoc��
	 * 
	 * @see com.zfsoft.IThirdPartySyncWebService#getTableData(java.lang.String,
	 *      int, int)
	 */
	public String[][] getTableData(String tableName, int startLineNum,
			int pagesize) {
		BjlhDAO dao = new BjlhDAO();
		return dao.getTableData(tableName, startLineNum, pagesize);
	}

	/* ���� Javadoc��
	 * @see xgxt.DAO.Bjlh.IThirdPartySyncWebService#getTableRecordCount(java.lang.String)
	 */
	public int getTableRecordCount(String tableName) {
		BjlhDAO dao = new BjlhDAO();
		return dao.getTableRecordCount(tableName);
	}
	
	/**
	 * �������ӿ�:����xsjbxxlsbʱ���ýӿڸı�����
	 * @param part ������
	 * */
	public String ChangeStudentInfo(String part){
		return part;
	}
}
