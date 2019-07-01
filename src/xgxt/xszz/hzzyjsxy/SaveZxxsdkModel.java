
package xgxt.xszz.hzzyjsxy;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 杭州职业技术学院在校学生贷款信息保存MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-04</p>
 */
public class SaveZxxsdkModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xn;//学年
	private String xh;//学号
	private String yxlxdh;//有效联系电话
	private String jtdh;//家庭电话
	private String jtdz;//家庭地址
	private String dkje;//贷款金额
	private String fdsj;//放贷时间
	private String dknx;//贷款年限
	
	public String getDkje() {
		return dkje;
	}
	public void setDkje(String dkje) {
		this.dkje = dkje;
	}
	public String getDknx() {
		return dknx;
	}
	public void setDknx(String dknx) {
		this.dknx = dknx;
	}
	public String getFdsj() {
		return fdsj;
	}
	public void setFdsj(String fdsj) {
		this.fdsj = fdsj;
	}
	public String getJtdh() {
		return jtdh;
	}
	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}
	public String getJtdz() {
		return jtdz;
	}
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getYxlxdh() {
		return yxlxdh;
	}
	public void setYxlxdh(String yxlxdh) {
		this.yxlxdh = yxlxdh;
	}
	
}
