/*
 *Create on 2007-08-10
 * 
 */
package xgxt.DAO.Bjlh;

/**
 * <p>ͬ��������webservice���ݵ�ͳһ�ӿ�</p>
 * @author Administrator
 *
 */
public interface IThirdPartySyncWebService {

	/**
	 * ��õ�������webservice����ͬ�������еı��ж���Ԫ��Ϣ
	 * @param tableName Ӣ�����ݱ���
	 * @return  	Column[]�����ʧ�ܣ����� 	Column[0]
	 */
	public Column[] getTableColumnInfo(String tableName);
	
	/**
	 * ��õ�������webservice����ͬ�������еı��¼����
	 * @param tableName
	 * @return ���¼��
	 */
	public int getTableRecordCount(String tableName);
	
	
	/**
	 * ��õ�������webservice����ͬ�������еķ�ҳ���ݣ����ص������Զ�ά�ַ���������ʽ����
	 * �ַ������͵Ķ�ά���ݣ���һά����ÿһ�У��ڶ�ά����ÿ��
	 * ͬʱҪ��ڶ�ά������˳����<code>getTableColumnInfo</code>���صĽ��˳����ͬ
	 * @param tableName  Ӣ�����ݱ���
	 * @param startLineNum  ��ʼ��������1��ʼ�����ص����ݰ�������
	 * @param pagesize  ÿ�λ�ȡ������
	 * @return  String[][]
	 * @see Column
	 * @see IThirdPartySyncWebService#getTableColumnInfo(String)
	 * 
	 */
	public String[][] getTableData(String tableName,int startLineNum,int pagesize);
	
	/**
	 * �������ӿ�:����xsjbxxlsbʱ���ýӿڸı�����
	 * @param part ������
	 * */
	public String ChangeStudentInfo(String part);
	
}
