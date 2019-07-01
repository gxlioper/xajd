/*
 *Create on 2007-08-10
 * 
 */
package xgxt.DAO.Bjlh;

import java.io.Serializable;

/**
 * <p>用于定义第三方提供的同步数据的数据表的列信息</p>
 * @author Administrator
 *
 */

public class Column implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6315754945414755243L;

	/**英文列名**/
	private String colName;
	
	/**列注释**/
	private String colComments;
	
	/**列类型**/
	private String colType;
	
	/**列定义长度**/
	private int colLength;

	/**
	 * @return colComments
	 */
	public String getColComments() {
		return colComments;
	}

	/**
	 * @param colComments 要设置的 colComments
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
	 * @param colLength 要设置的 colLength
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
	 * @param colName 要设置的 colName
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
	 * @param colType 要设置的 colType
	 */
	public void setColType(String colType) {
		this.colType = colType;
	}
	
}
