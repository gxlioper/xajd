/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-28 ����10:54:09 
 */  
package com.zfsoft.xgxt.wjcf.cfsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.wjcf.cfsb.CfsbglForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (�����ϱ����) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-28 ����10:51:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfshForm extends CfsbglForm {


	private static final long serialVersionUID = 1L;
	
	private String shlx;
	private String guid ;//ID
	private String shid ;//ID
	private String ywid ;//ҵ��ID
	private String shr ;//�����
	private String shsj ;//���ʱ��
	private String shzt ;//���״̬
	private String shyj ;//������
	private String gwid ;//��˸�λ
	private String sbjg ;
	private String cfwh;
	private String cfsj;
	private String cfdqsj;//���ֵ���ʱ��
	private String cfid;
	private String sjly;
	private String cflsh;
	private String cflbdm;//����������
	private String kzzd1;//���һ�θı�ǰ�������
	private String thgw;//��λ�˻�
	
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcids;
	
	private int cffwqxPd; //���ַ���Ȩ���ж�
	private String kzzd2; //�����ĺ�
	private String kzzd3; //����ʱ��
	private String kzzd5; //���ֵ���ʱ��
	
	/**
	 * @return the cfid
	 */
	public String getCfid() {
		return cfid;
	}

	/**
	 * @param cfidҪ���õ� cfid
	 */
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}

	/**
	 * @return the cfwh
	 */
	public String getCfwh() {
		return cfwh;
	}

	/**
	 * @param cfwhҪ���õ� cfwh
	 */
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}

	/**
	 * @return the cfsj
	 */
	public String getCfsj() {
		return cfsj;
	}

	/**
	 * @param cfsjҪ���õ� cfsj
	 */
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}

	/**
	 * @return the sbjg
	 */
	public String getSbjg() {
		return sbjg;
	}

	/**
	 * @param sbjgҪ���õ� sbjg
	 */
	public void setSbjg(String sbjg) {
		this.sbjg = sbjg;
	}

	private ExportModel exportModel = new ExportModel();

	/**
	 * @return the shlx
	 */
	public String getShlx() {
		return shlx;
	}

	/**
	 * @param shlxҪ���õ� shlx
	 */
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}

	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * @param guidҪ���õ� guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}

	/**
	 * @param shidҪ���õ� shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}

	/**
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}

	/**
	 * @param ywidҪ���õ� ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}

	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}

	/**
	 * @param shrҪ���õ� shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}

	/**
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}

	/**
	 * @param shsjҪ���õ� shsj
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}

	/**
	 * @param shztҪ���õ� shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}

	/**
	 * @param shyjҪ���õ� shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}

	/**
	 * @param gwidҪ���õ� gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}

	/**
	 * @param exportModelҪ���õ� exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}

	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}

	/**
	 * @param thgwҪ���õ� thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getGwids() {
		return gwids;
	}

	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}

	public String[] getXhs() {
		return xhs;
	}

	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	public String[] getSplcids() {
		return splcids;
	}

	public void setSplcids(String[] splcids) {
		this.splcids = splcids;
	}

	/**
	 * @return the cflsh
	 */
	public String getCflsh() {
		return cflsh;
	}

	/**
	 * @param cflshҪ���õ� cflsh
	 */
	public void setCflsh(String cflsh) {
		this.cflsh = cflsh;
	}

	/**
	 * @return the cflbdm
	 */
	public String getCflbdm() {
		return cflbdm;
	}

	/**
	 * @param cflbdmҪ���õ� cflbdm
	 */
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}

	/**
	 * @return the kzzd1
	 */
	public String getKzzd1() {
		return kzzd1;
	}

	/**
	 * @param kzzd1Ҫ���õ� kzzd1
	 */
	public void setKzzd1(String kzzd1) {
		this.kzzd1 = kzzd1;
	}

	/**
	 * @return the cffwqxPd
	 */
	public int getCffwqxPd() {
		return cffwqxPd;
	}

	/**
	 * @param cffwqxPdҪ���õ� cffwqxPd
	 */
	public void setCffwqxPd(int cffwqxPd) {
		this.cffwqxPd = cffwqxPd;
	}

	/**
	 * @return the kzzd2
	 */
	public String getKzzd2() {
		return kzzd2;
	}

	/**
	 * @param kzzd2Ҫ���õ� kzzd2
	 */
	public void setKzzd2(String kzzd2) {
		this.kzzd2 = kzzd2;
	}

	/**
	 * @return the kzzd3
	 */
	public String getKzzd3() {
		return kzzd3;
	}

	/**
	 * @param kzzd3Ҫ���õ� kzzd3
	 */
	public void setKzzd3(String kzzd3) {
		this.kzzd3 = kzzd3;
	}

	/**
	 * @return the kzzd5
	 */
	public String getKzzd5() {
		return kzzd5;
	}

	/**
	 * @param kzzd4Ҫ���õ� kzzd5
	 */
	public void setKzzd5(String kzzd5) {
		this.kzzd5 = kzzd5;
	}

	/**
	 * @return the cfdqsj
	 */
	public String getCfdqsj() {
		return cfdqsj;
	}

	/**
	 * @param cfdqsjҪ���õ� cfdqsj
	 */
	public void setCfdqsj(String cfdqsj) {
		this.cfdqsj = cfdqsj;
	}
	
	
	
	

}
