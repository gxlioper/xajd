/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-12 ����10:05:50 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.gbdw;

import com.zfsoft.xgxt.szdw.xsgbgl.zwsh.ZwshForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ����� ����ά��
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-12 ����10:05:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DwwhForm extends ZwshForm{

	/** 
	 * @���� serialVersionUID : TODO
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String dwid ;//����ID
	private String zwid ;//ְ��id
	private String xh ;//ѧ��
	private String zzzt ;//��ְ״̬
	private String rzsj ;//��ְʱ��
	private String lzsj ;//��ְʱ��
	private String lrsj ;//����ʱ��
	private String sjly ;//������Դ
	private String zwsqid ;//����id
	private String bjdm;

	private String[] zwids;
	private String[] xhs;

	@Override
	public String[] getZwids() {
		return zwids;
	}

	@Override
	public void setZwids(String[] zwids) {
		this.zwids = zwids;
	}

	@Override
	public String[] getXhs() {
		return xhs;
	}

	@Override
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getDwid() {
		return dwid;
	}
	public void setDwid(String dwid) {
		this.dwid = dwid;
	}
	public String getZwid() {
		return zwid;
	}
	public void setZwid(String zwid) {
		this.zwid = zwid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getZzzt() {
		return zzzt;
	}
	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getLzsj() {
		return lzsj;
	}
	public void setLzsj(String lzsj) {
		this.lzsj = lzsj;
	}
	public String getLrsj() {
		return lrsj;
	}
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @return the zwsqid
	 */
	public String getZwsqid() {
		return zwsqid;
	}
	/**
	 * @param zwsqidҪ���õ� zwsqid
	 */
	public void setZwsqid(String zwsqid) {
		this.zwsqid = zwsqid;
	}

	

}
