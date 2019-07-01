
package xgxt.DAO.Bjlh;

import java.util.Calendar;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import xgxt.DAO.DAO;
import xgxt.base.BaseProperties;
import xgxt.pjpy.WSForCjJd.WebserviceClient;
import xgxt.utils.Arrays2;
import xgxt.utils.PropertesFileParser;

public class BjlhWebServiceDao extends WebserviceClient{
	private String userName="xgsj";
	private String password="00000000";
	private String endPointUri = "";
	public BjlhWebServiceDao(){}
	/**
	 * @param endPointUri
	 * @param userName ���û���ṩ�û������룬����Ĭ�ϵı������ϵ��û���������
	 * @param password
	 */
	public BjlhWebServiceDao(String endPointUri,String methodName, String[] paramArr2,String userName,String password){
		super(endPointUri,methodName,paramArr2);
		this.endPointUri = endPointUri;
		if(userName != null && password != null){
			this.userName = userName;
			this.password = password;
		}		
	}
	public BjlhWebServiceDao(String endPointUri,  String methodName, String[] paramArr2){
		super( endPointUri,  methodName,  paramArr2);
	}

	/**
	 * ��ñ�����Ϣ
	 * @param tableName
	 * @return
	 */
	public Column[] getTableColumnInfo(String tableName){
		Column[] result = null;
		Service service = new Service();
//		String user = "";
//		String password = "";
		QName qn = new QName("urn:BeanService","MetadataColumn");
		try{
			Call call = (Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(getEndPointUri()));
			call.registerTypeMapping(Column.class, qn, new BeanSerializerFactory(Column.class,qn), new BeanDeserializerFactory(Column.class,qn));
			call.setOperationName(getMethodName());
			addParameter(call,getParamArr());
			result = (Column[])call.invoke(new Object[]{tableName});
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int getDataCount(String tableName){
 		int result = 0;
		Service service = new Service();
		DAO utilDao = DAO.getInstance();

		try{
			Call call = (Call)service.createCall();
			System.out.print(getEndPointUri());
			call.setTargetEndpointAddress(new java.net.URL(getEndPointUri()));
			//call.registerTypeMapping(Column.class, qn, new BeanSerializerFactory(Column.class,qn), new BeanDeserializerFactory(Column.class,qn));
			call.setOperationName("getDataCount");
//			String [] temp = getParamArr();
			Param[] params = new Param[0]; 
			call.addParameter("username", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("password", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("dataServerId", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("params", XMLType.XSD_ANY, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_INT);   
			Object[] b =  new Object[]{userName,utilDao.getProVal("{call getmd5(?,?)}", new String[]{password}, new int[] {2})[0],tableName,params};
			result = (Integer)call.invoke(b);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * ��Զ�̱��е�����ͬ������ǰ����
	 * @param localTableName
	 * @param remoteTableName
	 * @return
	 * @throws Exception 
	 */
	public boolean synchronizeTableData(String localTableName,String remoteTableName) throws Exception{
		boolean result = true;
		DAO utilDao = DAO.getInstance();
		PropertesFileParser parse = new PropertesFileParser(BaseProperties.getTomcatPath()+"/servicesProperties/remoteTableColumns");

		try{
			Service service = new Service();
			Call call = (Call)service.createCall();
			QName qn = new QName("urn:BeanService","MetadataColumn");
			call.registerTypeMapping(Column.class, qn, new BeanSerializerFactory(Column.class,qn), new BeanDeserializerFactory(Column.class,qn));
			call.setTargetEndpointAddress(getEndPointUri());
			call.setOperationName("getData");
			int sum = getDataCount(remoteTableName);
			//���Զ�̱������
			if("bks_xsjbxx".equalsIgnoreCase(localTableName)||"bks_xsqtxx".equalsIgnoreCase(localTableName)){
				Calendar date = Calendar.getInstance();
				String bakeupFlag = date.get(Calendar.YEAR)+"0"+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DAY_OF_MONTH)+""+date.get(Calendar.HOUR_OF_DAY)+""+date.get(Calendar.MINUTE)+""+date.get(Calendar.SECOND);
				//�������ݱ�
				String backUpSql = "create table "+localTableName+ "_"+bakeupFlag+" as select * from "+localTableName;
				//���ԭ��
				String truncateTable = "truncate table "+localTableName;
				//��������ļ��еı��ر���webservice�ṩ��Ķ�Ӧ��
				String[] localAndRemoteColumns =  parse.getLocalAndRemoteTableValue(localTableName);
				String[] localTableColumnsName = localAndRemoteColumns[1].split(",");//�����remoteTableColumns�����ļ��б��ر��޶����ֶ�
				//�ϳɲ����������� start
				String sql = "insert into ";
				sql += localTableName+"(";
				String valuesPart = ") values(";
				for(String temp : localTableColumnsName){
					sql += temp+",";
					valuesPart += "?,";
				}
				sql = sql.substring(0, sql.length()-1);
				valuesPart = valuesPart.substring(0, valuesPart.length()-1);
				sql += valuesPart + ")";
				boolean firstStep = utilDao.runUpdate(backUpSql, new String[]{});
				boolean secondStep = false;
				String[] insertArrayValues = new String[localTableColumnsName.length];
				if(firstStep){//�ڱ��ݳɹ��������²���ձ�
					secondStep = utilDao.runUpdate(truncateTable,new String[]{});
				}
				String[][] rs = null;
				for(int i = 0;i<sum;i+=1000){
					rs = (String[][])call.invoke(new Object[]{userName,utilDao.getProVal("{call getmd5(?,?)}", new String[]{password}, new int[] {2})[0],remoteTableName,new Param[]{},i,1000});
					if(secondStep){//������˱��ǰ���°�webservice���ʻ�õ�����ͬ������
						for(String[] temp : rs){
							Arrays2.copy(temp, insertArrayValues, localTableColumnsName.length, 0, 0);
							utilDao.runUpdate(sql,insertArrayValues);
							//for(int w=0;w<insertArrayValues.length;w++){
								//System.out.print(insertArrayValues[w]+",");
							//}
						}
					}
					call.clearOperation();
				}
				
			}else{
			String[][] rs = (String[][])call.invoke(new Object[]{userName,utilDao.getProVal("{call getmd5(?,?)}", new String[]{password}, new int[] {2})[0],remoteTableName,new Param[]{},0,sum});

			Calendar date = Calendar.getInstance();
			String bakeupFlag = date.get(Calendar.YEAR)+"0"+(date.get(Calendar.MONTH)+1)+""+date.get(Calendar.DAY_OF_MONTH)+""+date.get(Calendar.HOUR_OF_DAY)+""+date.get(Calendar.MINUTE)+""+date.get(Calendar.SECOND);
			//�������ݱ�
			String backUpSql = "create table "+localTableName+ "_"+bakeupFlag+" as select * from "+localTableName;
			//���ԭ��
			String truncateTable = "truncate table "+localTableName;
			//��������ļ��еı��ر���webservice�ṩ��Ķ�Ӧ��
			String[] localAndRemoteColumns =  parse.getLocalAndRemoteTableValue(localTableName);
			String[] localTableColumnsName = localAndRemoteColumns[1].split(",");//�����remoteTableColumns�����ļ��б��ر��޶����ֶ�
			//�ϳɲ����������� start
			String sql = "insert into ";
			sql += localTableName+"(";
			String valuesPart = ") values(";
			for(String temp : localTableColumnsName){
				sql += temp+",";
				valuesPart += "?,";
			}
			sql = sql.substring(0, sql.length()-1);
			valuesPart = valuesPart.substring(0, valuesPart.length()-1);
			sql += valuesPart + ")";
			//end 


			//�ȱ��ݱ��ر�����
			boolean firstStep = utilDao.runUpdate(backUpSql, new String[]{});
			boolean secondStep = false;
			if(firstStep){//�ڱ��ݳɹ��������²���ձ�
				secondStep = utilDao.runUpdate(truncateTable,new String[]{});
			}
			if("zxbz_xxbmdm".equalsIgnoreCase(localTableName)){
//				�������ݱ�
				backUpSql = "create table bks_dwdmb_"+bakeupFlag+" as select * from bks_dwdmb";
				//���ԭ��
				truncateTable = "truncate table bks_dwdmb";
				firstStep = utilDao.runUpdate(backUpSql, new String[]{});
				if(firstStep){//�ڱ��ݳɹ��������²���ձ�
					secondStep = utilDao.runUpdate(truncateTable,new String[]{});
				}
			}
			if(secondStep){//������˱��ǰ���°�webservice���ʻ�õ�����ͬ������
				String[] insertArrayValues = new String[localTableColumnsName.length];
				for(String[] temp : rs){
					Arrays2.copy(temp, insertArrayValues, localTableColumnsName.length, 0, 0);
					if("zxbz_xxbmdm".equalsIgnoreCase(localTableName)){
						if (insertArrayValues[2].length() <= 4) {
							if (insertArrayValues[0].length() == 2) {
								String tsql = "insert into bks_dwdmb(dwdm,dwmc) values(?,?)";
								String[] tempS = { "", "" };
								tempS[0] = insertArrayValues[0];
								tempS[1] = insertArrayValues[1];
								utilDao.runUpdate(tsql, tempS);
							} else {
								insertArrayValues[2] = "1";
								insertArrayValues[3] = "5";
								utilDao.runUpdate(sql, insertArrayValues);
							}
						}
					} else {
						utilDao.runUpdate(sql,insertArrayValues);
					}
				}
			}
			}
		}catch(Exception e){
			e.printStackTrace();	
			result = false;
		}
		if("bks_xsqtxx".equalsIgnoreCase(localTableName)){
			String sqltmp = "update bks_xsqtxx a set ZZMMM = (select zzmmdm from zzmmdmb b where a.zzmmm = b.zzmmmc)";
			boolean updatetemp = utilDao.runUpdate(sqltmp, new String []{});
			if(updatetemp){
				sqltmp ="update bks_xsqtxx a set mzdm = (select mzdm from mzdmb b where a.mzdm = b.mzmc)";
				utilDao.runUpdate(sqltmp, new String []{});
			}
		}
		if("bks_xsjbxx".equalsIgnoreCase(localTableName)){
			String sqltmp = "update bks_xsjbxx a set xjztm = (select dybdmc from xjztdzb b where a.xjztm = b.dysjmc)";
			utilDao.runUpdate(sqltmp, new String []{});
//			boolean updatetemp = utilDao.runUpdate(sqltmp, new String []{});
		}
		return result;
	}
	public String getEndPointUri() {
		return endPointUri;
	}
	public void setEndPointUri(String endPointUri) {
		this.endPointUri = endPointUri;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}

