package com.zfsoft.xgxt.dtjs.dxbmgl.bmsh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.dtjs.dxbmgl.pxbm.DxpxbmForm;

/** 
 * @������������У�������form
 * @author������ ��1346��
 * @date��2017-11-1 ����03:34:05 
 */
public class DxbmshForm extends DxpxbmForm {
	private static final long serialVersionUID = 1L;
	private String shzt;//���״̬
	private String shztmc;//���״̬����
	private String type;
	//������
	private String shyj;
	private String gwid;
	private String shid;
	private String thgw;
	private String[] ids;
	private String[] gwids;
	private String[] xhs;
	private String[]splcs;
	private ExportModel exportModel=new ExportModel();
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getThgw() {
		return thgw;
	}
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String[] getGwids() {
		return gwids;
	}
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	public String[] getSplcs() {
		return splcs;
	}
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getShztmc() {
		return shztmc;
	}
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
}
