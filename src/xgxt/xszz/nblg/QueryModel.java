
package xgxt.xszz.nblg;

import java.io.Serializable;

import xgxt.action.Base;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��������ѯMODEL</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-01-13</p>
 */
public class QueryModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bjdm;//�༶����
	private String xydm;//ѧԺ����
	private String zydm;//רҵ����
	private String xh;//ѧ��
	private String xn;//ѧ��
	private String nd;//���
	private String nj;//�꼶
	private String mzpy;
	private String xysh;
	private String xxsh;
	private String go;//��ѯ��ʶ
	private String isQuery;//���ܱ�ʶ
	public String getGo() {
		return go;
	}
	public void setGo(String go) {
		this.go = go;
	}
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
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}
	public String getMzpy() {
		return mzpy;
	}
	public void setMzpy(String mzpy) {
		this.mzpy = Base.chgNull(mzpy,"",1);
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = Base.chgNull(xxsh,"",1);
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = Base.chgNull(xysh,"",1);
	}
}
