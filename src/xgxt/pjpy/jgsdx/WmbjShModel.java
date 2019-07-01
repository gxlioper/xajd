
package xgxt.pjpy.jgsdx;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学评奖评优文明班级单个审核MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-03</p>
 */
public class WmbjShModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String shxm;//审核
	private String shyj;//审核意见
	private String pkValue;//主键
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getShxm() {
		return shxm;
	}
	public void setShxm(String shxm) {
		this.shxm = shxm;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
}
