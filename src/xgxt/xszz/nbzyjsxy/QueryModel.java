
package xgxt.xszz.nbzyjsxy;

import java.io.Serializable;

import xgxt.action.Base;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ְҵ����ѧԺ��ѯMODEL</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-07-10</p>
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
	private String xm;//����
	private String dw;//��λ
	private String bm;//����
	private String sfzh;//���֤��
	private String sj;//�ֻ�
	private String jsdm;//��ɫ����
	private String xhyhm;//ѧ�Ż��û���
	private String dwlxdh;//��λ��ϵ�绰
	private String bjrsh;//���������
	private String xysh;//ѧԺ���
	private String xxsh;//ѧУ���
	private String sfjk;//�Ƿ���
	private String go;//��ѯ��ʶ
	private String tjlx;//ͳ������
	private String isQuery;//���ܱ�ʶ
	
	public String getTjlx() {
		return tjlx;
	}
	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
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
	public String getDwlxdh() {
		return dwlxdh;
	}
	public void setDwlxdh(String dwlxdh) {
		this.dwlxdh = dwlxdh;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public String getBjrsh() {
		return bjrsh;
	}
	public void setBjrsh(String bjrsh) {
		this.bjrsh = Base.chgNull(bjrsh,"",1);
	}
	public String getJsdm() {
		return jsdm;
	}
	public void setJsdm(String jsdm) {
		this.jsdm = jsdm;
	}
	public String getXhyhm() {
		return xhyhm;
	}
	public void setXhyhm(String xhyhm) {
		this.xhyhm = xhyhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public String getSfjk() {
		return sfjk;
	}
	public void setSfjk(String sfjk) {
		this.sfjk = sfjk;
	}
}
