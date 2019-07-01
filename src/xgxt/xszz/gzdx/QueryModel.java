
package xgxt.xszz.gzdx;

import java.io.Serializable;

import xgxt.base.DealString;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ݴ�ѧMODEL</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-12-24</p>
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
	private String xq;//ѧ��
	private String xm;
	private String xb;
	private String sfzh;
	private String htbh;
	private String sfqbhqdk;
	private String btsj;
	private String go;//��ѯ��ʶ
	private String isQuery;//���ܱ�ʶ
	
	public String getBtsj() {
		return btsj;
	}
	public void setBtsj(String btsj) {
		this.btsj = btsj;
	}
	public String getSfqbhqdk() {
		return sfqbhqdk;
	}
	public void setSfqbhqdk(String sfqbhqdk) {
		this.sfqbhqdk = DealString.toGBK(sfqbhqdk);
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = DealString.toGBK(htbh);
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
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
	public String getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = DealString.toGBK(sfzh);
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
}
