package xgxt.xtwh.xtwhCriterion.entities;

import xgxt.base.Encrypt;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_�û�����_�û�ʵ����
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class YhEntity extends Entitry{
	private String yhm; 	// �û���
	private String mm;		// ����
	private String szbm;	// ���ڲ���
	private String xm; 		// ����
	private String kqzt;	// ����״̬
	
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getSzbm() {
		return szbm;
	}
	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getKqzt() {
		return kqzt;
	}
	public void setKqzt(String kqzt) {
		this.kqzt = kqzt;
	}
	@Override
	public String getMappingTable() {
		return "xg_xtwh_yhb";
	}
	@Override
	public String getPk() {
		return "yhm";
	}
	@Override
	public String getPkValue() {
		return yhm;
	}
}
