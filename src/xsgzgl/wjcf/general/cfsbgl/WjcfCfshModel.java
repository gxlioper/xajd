package xsgzgl.wjcf.general.cfsbgl;

import xsgzgl.comm.form.CommForm;

/**
 * 
* 
* �����ƣ�WjcfCfsbAction 
* ��������Υ�ʹ��ִ����ϱ�ActionForm
* �����ˣ�xucy 
* ����ʱ�䣺2012-6-20 ����01:18:00 
* �޸ı�ע��  
* @version 
*
 */
public class WjcfCfshModel extends CommForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cfId;
	
	private String cflbdm;
	
	private String spgw;

	public String getCflbdm() {
		return cflbdm;
	}

	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}

	public String getSpgw() {
		return spgw;
	}

	public void setSpgw(String spgw) {
		this.spgw = spgw;
	}

	public String getCfId() {
		return cfId;
	}

	public void setCfId(String cfId) {
		this.cfId = cfId;
	}
	
}
