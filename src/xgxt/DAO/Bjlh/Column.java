/*
 *Create on 2007-08-10
 * 
 */
package xgxt.DAO.Bjlh;

import java.io.Serializable;

/**
 * <p>���ڶ���������ṩ��ͬ�����ݵ����ݱ������Ϣ</p>
 * @author Administrator
 *
 */

public class Column implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6315754945414755243L;

	/**Ӣ������**/
	private String colName;
	
	/**��ע��**/
	private String colComments;
	
	/**������**/
	private String colType;
	
	/**�ж��峤��**/
	private int colLength;

	/**
	 * @return colComments
	 */
	public String getColComments() {
		return colComments;
	}

	/**
	 * @param colComments Ҫ���õ� colComments
	 */
	public void setColComments(String colComments) {
		this.colComments = colComments;
	}

	/**
	 * @return colLength
	 */
	public int getColLength() {
		return colLength;
	}

	/**
	 * @param colLength Ҫ���õ� colLength
	 */
	public void setColLength(int colLength) {
		this.colLength = colLength;
	}

	/**
	 * @return colName
	 */
	public String getColName() {
		return colName;
	}

	/**
	 * @param colName Ҫ���õ� colName
	 */
	public void setColName(String colName) {
		this.colName = colName;
	}

	/**
	 * @return colType
	 */
	public String getColType() {
		return colType;
	}

	/**
	 * @param colType Ҫ���õ� colType
	 */
	public void setColType(String colType) {
		this.colType = colType;
	}
	
}
