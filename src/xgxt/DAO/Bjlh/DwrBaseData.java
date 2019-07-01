
package xgxt.DAO.Bjlh;

import java.util.ArrayList;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.MD5;

public class DwrBaseData {
//	用于基础数据初始化中的dwr获取本地表的字段和comments
	public String[][] getLocalColumns(String tableName){
		DAO dao = DAO.getInstance();
		String sql = "select * from "+tableName;
		String[] enCols = dao.getColumnName(sql);
		String[] cnCols = dao.getColumnNameCN(enCols, tableName);
		String[][] result = new String[enCols.length][2];
		for(int i=0;i<result.length;i++){
			result[i][0] = enCols[i];
			result[i][1] = cnCols[i];
		}
		return result;
	}
//	用于基础数据初始化中的dwr获取webservice异地表的字段和comments
	public String[][] getRemoteColumns(String tableName){
		BjlhWebServiceDao dao = new BjlhWebServiceDao(Base.getBjlhBaseDataEndPointUri(),"getTableColumnInfo",new String[]{"tableName"});
		Column[] remoteColumn = dao.getTableColumnInfo(tableName);
		String[][] result = new String[remoteColumn.length][2];
		for(int i=0;i<remoteColumn.length;i++){
			result[i][0] = remoteColumn[i].getColName();
			result[i][1] = remoteColumn[i].getColComments();
		}
		return result;
	}
	
	/**
	 * @param key 从页面获得本地表和远程表的组合键
	 * @return
	 */
	public String[] getTableProperties(String key){
		//获得选中的表的配置参数
		String[] result = new String[2];
		DAO dao = DAO.getInstance();
		String sql = "select  kssj,jgsj from glsjb where LOCALTABLENAME||REMOTETABLENAME=?";
		result = dao.getOneRs(sql, new String[]{key.toLowerCase().replace("!@", "")}, new String[]{"kssj","jgsj"});
		return result;
	}
	
	/**
	 * @param targetTable
	 * @param columns
	 * @return
	 */
	public String[] saveTableStructure(String localTable,String remoteTable, String[] localTablecolumns){
		String[] result;
		String[][] tarColumns = getLocalColumns(localTable);
		String[] notExistColumns = new String[localTablecolumns.length];
		int j=0;
		for(int i=0;i<localTablecolumns.length;i++){
			for(int k=0;k<tarColumns.length;k++){
				if(!localTablecolumns[i].equalsIgnoreCase(tarColumns[k][0]))
					notExistColumns[j++] = localTablecolumns[i];
			}
		}
		//通过webserviece获得远程表的表结构，不是从页面获得
		DAO dbDao = DAO.getInstance();
		BjlhWebServiceDao dao = new BjlhWebServiceDao(Base.getBjlhBaseDataEndPointUri(),"getTableColumnInfo",new String[]{"tableName"});
		Column[] remoteColumns = dao.getTableColumnInfo(remoteTable);
		ArrayList<String> wrongColumns = new ArrayList<String>();//用于存储没有增加到表中的列
		for(int i=0;i<notExistColumns.length && notExistColumns[i]!=null ;i++){
			for(Column column : remoteColumns){
				if(column.getColName().equalsIgnoreCase(notExistColumns[i])){
					String type = column.getColType();
					String collength= Integer.toString(column.getColLength());
					StringBuffer create_sql = new StringBuffer("alter table ");
					create_sql.append(localTable);
					create_sql.append(" add(");
					create_sql.append(notExistColumns[i]);
					create_sql.append(" ");
					create_sql.append(type);
					create_sql.append("(");
					create_sql.append(collength);
					create_sql.append("))");
					String sql = "{call zfPackage.synchronizeTableStructure(?)}";
					try{
						dbDao.runProcuder(sql, new String[]{create_sql.toString()});
					}catch(Exception e){
						System.out.println(column.getColComments());
						e.printStackTrace();
						wrongColumns.add(column.getColComments());
					}
				}	
			}
		}
		result = wrongColumns.toArray(new String[0]);
		return result;
	}
	
	//执行选中表的同步dwr调用
	public boolean synchronizeTableData(String localTableName,String remoteTableName) throws Exception{
		BjlhWebServiceDao dao = new BjlhWebServiceDao(Base.getBjlhBaseDataEndPointUri(),"getDate",new String[]{"tableName"},Base.getWebserviceUser(),Base.getWebserviceUser());
		return dao.synchronizeTableData(localTableName, remoteTableName);
	}
	
	
    //和教务同步数据执行的方法
	public boolean synchronizeTableDataForZFweb(String tableName) throws Exception{
		WebServiceUnitDAO dao = new WebServiceUnitDAO();
		boolean update=false;
		if(tableName.equalsIgnoreCase("xyxx")){
			update= dao.synchronizeXyData(tableName);
		}else if(tableName.equalsIgnoreCase("zyxx")){
			update= dao.synchronizeZyData(tableName);
		}else if(tableName.equalsIgnoreCase("bjxx")){
			update= dao.synchronizeBjData(tableName);
		}else if(tableName.equalsIgnoreCase("xsxx")){
			update= dao.synchronizeXsxxData();
		}else if(tableName.equalsIgnoreCase("xsqtxx")){
			update= dao.synchronizeXsqtxxData();
		}else if(tableName.equalsIgnoreCase("cjxx")){
			update= dao.synchronizeXscjxxData();
		}else if(tableName.equalsIgnoreCase("xjyd")){
			update= dao.synchronizeXjydxxData();
		}
		return update;
	}
	
	//单点登录到教务 start
	/**
	 * @return 教务的web站点地址
	 */
	public String[] getJwSiteFromJwWebservice(String userName,String jsName){
		DAO dao  = DAO.getInstance();
		String temp = Base.getEndPointUri();
		String jwUrl = temp.substring(0, temp.indexOf("service"))+"default2.aspx";
		String strTime = dao.getOneRs("select to_char(sysdate,'YYYY-MM-DDHH24:MI:SS') systime from dual", new String[]{}, "systime");
		String verify = getVerify(dao,userName,jsName,strTime);
		if(verify.length()>0){
			return (new String[]{jwUrl,strTime,verify});
		} else 
			return null;
	}
	
	
	
	public String getVerify(DAO dao,String userName,String userType,String strTime){
		MD5 md5 = new MD5();
		String sql = "select zfssokey from view_zf_key where rownum=1";
		String[] tmp = dao.getOneRs(sql, new String[] {},
				new String[] { "zfssokey" });
		if(tmp == null){
			return "";
		}else{
			String strKey = (tmp != null)&&tmp.length>0 ?tmp[0]:"";
			String res = md5.getMD5ofStr(userName + strKey + strTime + userType);
			return res;
		}
		
	}
//	单点登录到教务 end
	public static void main(String[] args){
		String temp = "http://10.11.5.99/buudp/services/transferService?wsdl";
		System.out.println(temp.substring(0, temp.indexOf("service")));
	}
}
