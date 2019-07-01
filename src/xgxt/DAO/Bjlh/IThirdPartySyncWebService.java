/*
 *Create on 2007-08-10
 * 
 */
package xgxt.DAO.Bjlh;

/**
 * <p>同步第三方webservice数据的统一接口</p>
 * @author Administrator
 *
 */
public interface IThirdPartySyncWebService {

	/**
	 * 获得第三方在webservice数据同步过程中的表列定义元信息
	 * @param tableName 英文数据表名
	 * @return  	Column[]，如果失败，返回 	Column[0]
	 */
	public Column[] getTableColumnInfo(String tableName);
	
	/**
	 * 获得第三方在webservice数据同步过程中的表记录数量
	 * @param tableName
	 * @return 表记录数
	 */
	public int getTableRecordCount(String tableName);
	
	
	/**
	 * 获得第三方在webservice数据同步过程中的分页数据，返回的数据以二维字符串数据形式处理
	 * 字符串类型的二维数据，第一维代码每一行，第二维代表每列
	 * 同时要求第二维的数据顺序与<code>getTableColumnInfo</code>返回的结果顺序相同
	 * @param tableName  英文数据表名
	 * @param startLineNum  开始行数，从1开始，返回的数据包含该行
	 * @param pagesize  每次获取的行数
	 * @return  String[][]
	 * @see Column
	 * @see IThirdPartySyncWebService#getTableColumnInfo(String)
	 * 
	 */
	public String[][] getTableData(String tableName,int startLineNum,int pagesize);
	
	/**
	 * 厦门理工接口:增加xsjbxxlsb时调用接口改变数据
	 * @param part 考生号
	 * */
	public String ChangeStudentInfo(String part);
	
}
