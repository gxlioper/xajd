
package xgxt.xszz.zgkydx;

import java.io.Serializable;

import xgxt.base.DealString;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �й���ҵ��ѧ��ѯMODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-13</p>
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
	private String knpd;//�����ж����
	private String xbshjg;//ϵ����˽��
	private String xxshjg;//ѧУ��˽��
	private String tsrqdm;//������Ⱥ����
	private String zzxmdm;//������Ŀ����
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
	public String getKnpd() {
		return knpd;
	}
	public void setKnpd(String knpd) {
		this.knpd = DealString.toGBK(knpd);
	}
	public String getTsrqdm() {
		return tsrqdm;
	}
	public void setTsrqdm(String tsrqdm) {
		this.tsrqdm = tsrqdm;
	}
	public String getXxshjg() {
		return xxshjg;
	}
	public void setXxshjg(String xxshjg) {
		this.xxshjg = DealString.toGBK(xxshjg);
	}
	public String getZzxmdm() {
		return zzxmdm;
	}
	public void setZzxmdm(String zzxmdm) {
		this.zzxmdm = zzxmdm;
	}
	public String getXbshjg() {
		return xbshjg;
	}
	public void setXbshjg(String xbshjg) {
		this.xbshjg = DealString.toGBK(xbshjg);
	}
}
