/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-24 ����09:13:10 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.Serializable;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������: ����Ա��Ϣ-������������
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2015-12-17 ����09:18:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FmczModel implements Serializable{

	private static final long serialVersionUID = -8692278407785586702L;
	
	private String fmczid;
	private String mc;
	private String sj;
	private String brpm;
	private String lx;
	private String bz;
	
	
	public String getFmczid() {
		return fmczid;
	}
	public void setFmczid(String fmczid) {
		this.fmczid = fmczid;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public String getBrpm() {
		return brpm;
	}
	public void setBrpm(String brpm) {
		this.brpm = brpm;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	

}
