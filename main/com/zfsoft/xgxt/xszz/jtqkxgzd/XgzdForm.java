/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-4 ����10:33:33 
 */  
package com.zfsoft.xgxt.xszz.jtqkxgzd;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-4 ����10:33:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XgzdForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String[] zddms;
	private String[] guids;
	private String[] sfbts;
	private String guid;
	private String zddm;
	private String sfbt;
	private String type;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the zddms
	 */
	public String[] getZddms() {
		return zddms;
	}
	/**
	 * @param zddmsҪ���õ� zddms
	 */
	public void setZddms(String[] zddms) {
		this.zddms = zddms;
	}
	/**
	 * @return the guids
	 */
	public String[] getGuids() {
		return guids;
	}
	/**
	 * @param guidsҪ���õ� guids
	 */
	public void setGuids(String[] guids) {
		this.guids = guids;
	}
	/**
	 * @return the sfbts
	 */
	public String[] getSfbts() {
		return sfbts;
	}
	/**
	 * @param sfbtsҪ���õ� sfbts
	 */
	public void setSfbts(String[] sfbts) {
		this.sfbts = sfbts;
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
	 * @return the zddm
	 */
	public String getZddm() {
		return zddm;
	}
	/**
	 * @param zddmҪ���õ� zddm
	 */
	public void setZddm(String zddm) {
		this.zddm = zddm;
	}
	/**
	 * @return the sfbt
	 */
	public String getSfbt() {
		return sfbt;
	}
	/**
	 * @param sfbtҪ���õ� sfbt
	 */
	public void setSfbt(String sfbt) {
		this.sfbt = sfbt;
	}
	
}
