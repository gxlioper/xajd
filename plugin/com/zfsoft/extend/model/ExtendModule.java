/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-2 ����02:55:43 
 */  
package com.zfsoft.extend.model;

import java.io.Serializable;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ZFXG_EXTEND_MODULE
 * @���ߣ���С��[����:1036]
 * @ʱ�䣺 2015-6-2 ����02:55:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExtendModule  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	////////////////////////FIELDS///////////////////
	private String id;    //����
	
	private String mc;    //����
	
	private String sm;    //˵����Ϣ
	 
	private String sfqy;  //�Ƿ����ã�1���ã�0������
	
	private String sfsh;  //�Ƿ���Ҫ���
	
	private String splc;  //��������
	
	private String sjxz;  //�Ƿ���ʱ������
	
	private String kssj;  //��ʼʱ��
	
	private String jssj;  //����ʱ��
	
	private String bz;    //��ע
	
   ////////////////////////FIELDS///////////////////
	
   	
	
    /**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}

	/**
	 * @param mcҪ���õ� mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}

	/**
	 * @return the sm
	 */
	public String getSm() {
		return sm;
	}

	/**
	 * @param smҪ���õ� sm
	 */
	public void setSm(String sm) {
		this.sm = sm;
	}

	/**
	 * @return the sfqy
	 */
	public String getSfqy() {
		return sfqy;
	}

	/**
	 * @param sfqyҪ���õ� sfqy
	 */
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	/**
	 * @return the sfsh
	 */
	public String getSfsh() {
		return sfsh;
	}

	/**
	 * @param sfshҪ���õ� sfsh
	 */
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}

	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}

	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}

	/**
	 * @return the sjxz
	 */
	public String getSjxz() {
		return sjxz;
	}

	/**
	 * @param sjxzҪ���õ� sjxz
	 */
	public void setSjxz(String sjxz) {
		this.sjxz = sjxz;
	}

	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}

	/**
	 * @param kssjҪ���õ� kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}

	/**
	 * @param jssjҪ���õ� jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
}
