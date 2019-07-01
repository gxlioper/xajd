package com.zfsoft.xgxt.dtjs.dxbmgl.shlcsz;

import org.apache.struts.action.ActionForm;
/** 
 * @功能描述：党校报名流程设置form
 * @author：杨珩 【1346】
 * @date：2017-11-1 上午08:52:45 
 */
public class DxbmshlcszForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String id;//主键
	private String shl;//审核流程
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShl() {
		return shl;
	}
	public void setShl(String shl) {
		this.shl = shl;
	}
}