/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-17 ����01:29:49 
 */  
package com.zfsoft.xgxt.zjly.zhf.cssz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �ۺϷֲ�������(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-17 ����01:29:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfCsszForm extends ActionForm{
	
	private static final long serialVersionUID = 17861735386863024L;
	private String id;
	private String sqkg;
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
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkgҪ���õ� sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	
	
}
