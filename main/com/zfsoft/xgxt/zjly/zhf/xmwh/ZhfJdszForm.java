/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-29 ����08:59:05 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ۺϷֹ���ģ��
 * @�๦������: �������(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-29 ����08:59:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfJdszForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String jfxmdm;
	private String jfxmmc;
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
	 * @return the jfxmdm
	 */
	public String getJfxmdm() {
		return jfxmdm;
	}
	/**
	 * @param jfxmdmҪ���õ� jfxmdm
	 */
	public void setJfxmdm(String jfxmdm) {
		this.jfxmdm = jfxmdm;
	}
	/**
	 * @return the jfxmmc
	 */
	public String getJfxmmc() {
		return jfxmmc;
	}
	/**
	 * @param jfxmmcҪ���õ� jfxmmc
	 */
	public void setJfxmmc(String jfxmmc) {
		this.jfxmmc = jfxmmc;
	}
	
	
}
