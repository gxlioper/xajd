/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-18 ����04:16:08 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.jcsz;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-18 ����04:16:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String kg; //����
	
	private String spl; //������ID
	
	private String xjxn;
	
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
	 * @return the kg
	 */
	public String getKg() {
		return kg;
	}

	/**
	 * @param kgҪ���õ� kg
	 */
	public void setKg(String kg) {
		this.kg = kg;
	}

	/**
	 * @return the spl
	 */
	public String getSpl() {
		return spl;
	}

	/**
	 * @param splҪ���õ� spl
	 */
	public void setSpl(String spl) {
		this.spl = spl;
	}

	/**
	 * @return the xjxn
	 */
	public String getXjxn() {
		return xjxn;
	}

	/**
	 * @param xjxnҪ���õ� xjxn
	 */
	public void setXjxn(String xjxn) {
		this.xjxn = xjxn;
	}


	
	
	
	
}
