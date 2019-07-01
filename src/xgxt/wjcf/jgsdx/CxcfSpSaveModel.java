
package xgxt.wjcf.jgsdx;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学违纪处分Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-24</p>
 */
public class CxcfSpSaveModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String spzt;//审批状态
	private String cxwh;//解除文号
	private String cxsj;//解除时间
	private String pkValue;//主键
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getCxsj() {
		return cxsj;
	}
	public void setCxsj(String cxsj) {
		this.cxsj = cxsj;
	}
	public String getCxwh() {
		return cxwh;
	}
	public void setCxwh(String cxwh) {
		this.cxwh = cxwh;
	}
	public String getSpzt() {
		return spzt;
	}
	public void setSpzt(String spzt) {
		this.spzt = spzt;
	}
}
