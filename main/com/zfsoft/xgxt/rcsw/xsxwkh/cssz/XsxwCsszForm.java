/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-05-07 ����04:28:03 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.cssz;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ����Ϊ���˲�������model
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-8-2 ����02:33:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XsxwCsszForm extends ActionForm {
	private static final long serialVersionUID = -1604650553048161540L;
	private String id;
	private String kgzt;
	private String kssj;
	private String jssj;
	private String xn;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getKgzt() {
		return kgzt;
	}
	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}
	
	
}
