package xsgzgl.wjcf.general.cfsbgl;

import xsgzgl.comm.form.CommForm;

/**
 * 
* 
* 类名称：WjcfCfsbAction 
* 类描述：违纪处分处分上报ActionForm
* 创建人：xucy 
* 创建时间：2012-6-20 下午01:18:00 
* 修改备注：  
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
