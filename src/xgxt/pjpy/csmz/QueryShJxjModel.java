
package xgxt.pjpy.csmz;

import java.io.Serializable;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ɳ����ѧԺ����������άѧ���ѯMODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-06</p>
 */
public class QueryShJxjModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bjdm;//�༶����
	private String xydm;//ѧԺ����
	private String zydm;//רҵ����
	private String xh;//ѧ��
	private String xn;//ѧ��
	private String nj;//�꼶
	private String xq;//ѧ��
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
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
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
}
