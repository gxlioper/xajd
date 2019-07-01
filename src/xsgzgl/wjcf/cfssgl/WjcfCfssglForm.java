package xsgzgl.wjcf.cfssgl;


import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xsgzgl.comm.form.CommForm;

/**
 * 
* 
* 类名称：WjcfCfssglForm 
* 类描述：违纪处分申诉管理from
* 创建人：yijd 
* 创建时间：2012-6-19 上午09:20:00 
* 修改备注：  
* @version 
*
 */
public class WjcfCfssglForm extends CommForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FormFile ssfj;//申述附件
	private FormFile shfj;//审核附件
	private String ssfjmc;//申诉附件名称
	private String cffjmc;//审核附件名称
	private String cfid;
	private String sqsj;
	private String sqly;
	private String ssjg;
	private String xn;
	private String xq;
	private String cflbdm;
	private String cflbmc;
	private String cfyydm;
	private String cfyymc;
	private String bjdm;
	private String xydm;
	private String zydm;
	private String nj;
	private String xh;
	private String xm;
	private String xtgwid;
	private String shzt;
	private String shsj;
	private String shyj;
	private String shr;
	private String sftj;
	private String sswh;
	private String sssj;
	
	private String cfggw ;
	private ExportModel exportModel = new ExportModel();
	
	//工具属性
	private String sjSpgw;//上级审批岗位
	private String xjSpgw;//下级审批岗位
	public FormFile getSsfj() {
		return ssfj;
	}
	public void setSsfj(FormFile ssfj) {
		this.ssfj = ssfj;
	}
	public FormFile getShfj() {
		return shfj;
	}
	public void setShfj(FormFile shfj) {
		this.shfj = shfj;
	}
	public String getCfid() {
		return cfid;
	}
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSsjg() {
		return ssjg;
	}
	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getCflbdm() {
		return cflbdm;
	}
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}
	public String getCflbmc() {
		return cflbmc;
	}
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	public String getCfyydm() {
		return cfyydm;
	}
	public void setCfyydm(String cfyydm) {
		this.cfyydm = cfyydm;
	}
	public String getCfyymc() {
		return cfyymc;
	}
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSsfjmc() {
		return ssfjmc;
	}
	public void setSsfjmc(String ssfjmc) {
		this.ssfjmc = ssfjmc;
	}
	public String getCffjmc() {
		return cffjmc;
	}
	public void setCffjmc(String cffjmc) {
		this.cffjmc = cffjmc;
	}
	public String getXtgwid() {
		return xtgwid;
	}
	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getSftj() {
		return sftj;
	}
	public void setSftj(String sftj) {
		this.sftj = sftj;
	}
	public String getSswh() {
		return sswh;
	}
	public void setSswh(String sswh) {
		this.sswh = sswh;
	}
	public String getSssj() {
		return sssj;
	}
	public void setSssj(String sssj) {
		this.sssj = sssj;
	}
	public String getSjSpgw() {
		return sjSpgw;
	}
	public void setSjSpgw(String sjSpgw) {
		this.sjSpgw = sjSpgw;
	}
	public String getXjSpgw() {
		return xjSpgw;
	}
	public void setXjSpgw(String xjSpgw) {
		this.xjSpgw = xjSpgw;
	}
	public String getCfggw() {
		return cfggw;
	}
	public void setCfggw(String cfggw) {
		this.cfggw = cfggw;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	
}
