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
	 * （非 Javadoc）
	 * 
	 * @see com.zfsoft.IThirdPartySyncWebService#getTableColumnInfo(java.lang.String)
	 */
	public Column[] getTableColumnInfo(String tableName) {
		BjlhDAO dao = new BjlhDAO();
		return dao.getTableColumnInfo(tableName);
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.zfsoft.IThirdPartySyncWebService#getTableData(java.lang.String,
	 *      int, int)
	 */
	public String[][] getTableData(String tableName, int startLineNum,
			int pagesize) {
		BjlhDAO dao = new BjlhDAO();
		return dao.getTableData(tableName, startLineNum, pagesize);
	}

	/* （非 Javadoc）
	 * @see xgxt.DAO.Bjlh.IThirdPartySyncWebService#getTableRecordCount(java.lang.String)
	 */
	public int getTableRecordCount(String tableName) {
		BjlhDAO dao = new BjlhDAO();
		return dao.getTableRecordCount(tableName);
	}
	
	/**
	 * 厦门理工接口:增加xsjbxxlsb时调用接口改变数据
	 * @param part 考生号
	 * */
	public String ChangeStudentInfo(String part){
		return part;
	}
}
