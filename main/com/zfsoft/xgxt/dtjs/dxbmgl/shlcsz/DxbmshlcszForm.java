package com.zfsoft.xgxt.dtjs.dxbmgl.shlcsz;

import org.apache.struts.action.ActionForm;
/** 
 * @������������У������������form
 * @author������ ��1346��
 * @date��2017-11-1 ����08:52:45 
 */
public class DxbmshlcszForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String id;//����
	private String shl;//�������
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