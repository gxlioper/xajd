
package xgxt.pjpy.jgsdx;

import java.io.Serializable;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ɽ��ѧ�������������ƺ�������˲�ѯMODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-09</p>
 */
public class RychSjQryModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nj;//�꼶
	private String xn;//ѧ��
	private String xq;//ѧ��
	private String xh;//ѧ��
	private String xydm;//ѧԺ����
	private String zydm;//רҵ����
	private String bjdm;//�༶����
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
