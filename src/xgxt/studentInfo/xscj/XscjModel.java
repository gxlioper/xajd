package xgxt.studentInfo.xscj;

import java.io.Serializable;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description: ����ѧԺѧ���ɼ�ά��Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ����
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2012-03-14
 * </p>
 */
public class XscjModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String xydm;// ѧԺ����

	private String zydm;// רҵ����

	private String bjdm;// �༶����

	private String nj;// �꼶

	private String xn;// ѧ��

	private String nd;// ���

	private String xh;// ѧ��

	private String xm;// ����

	private String xq;// ѧ��

	private String[] kcxz;// �γ�����
	
	private String cjlx;//�ɼ�����
	
	private String djksmc;
	
	private String kcmc;//�γ�����

	public String getKcmc() {
		return kcmc;
	}

	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
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

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String[] getKcxz() {
		return kcxz;
	}

	public void setKcxz(String[] kcxz) {
		this.kcxz = kcxz;
	}
	
	public String getCjlx() {
		return cjlx;
	}

	public void setCjlx(String cjlx) {
		this.cjlx = cjlx;
	}

	public String getDjksmc() {
		return djksmc;
	}

	public void setDjksmc(String djksmc) {
		this.djksmc = djksmc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
