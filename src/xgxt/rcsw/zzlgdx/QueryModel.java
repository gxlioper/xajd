
package xgxt.rcsw.zzlgdx;

import java.io.Serializable;

import xgxt.base.DealString;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭����ѯModel</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-11-27</p>
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
	private String xm;
	private String xb;
	private String sfzh;
	private String xysh;
	private String xxsh;
	private String qjlx;
	private String shjg;
	private String go;//��ѯ��ʶ
	private String userType;
	
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = DealString.toGBK(shjg);
	}
	public String getQjlx() {
		return qjlx;
	}
	public void setQjlx(String qjlx) {
		this.qjlx = DealString.toGBK(qjlx);
	}
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
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = DealString.toGBK(xb);
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = DealString.toGBK(xm);
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = DealString.toGBK(xxsh);
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = DealString.toGBK(xysh);
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
