package xsgzgl.gygl.gyglry;

import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xsgzgl.gygl.comm.GyglNewForm;

public class GyglryForm extends GyglNewForm{
	
	private String pkValue;//主键 lddm||ch||qsh
	private String[] xhs;
	private String lxdh;//联系电话
	private String rzksrq;//任职开始日期
	private String rzjsrq;//任职结束日期
	private String bz;//备注
	
	private String lddm;//楼栋代码
	private String ch;//层号
	private String qsh;//寝室号
	private String fp_ch;//分配层号
	private String fp_qsh;//分配寝室号
	
	private String czfs;//操作方式   gx:更新 zj:追加
	private String old_xh;//需要替换的学号
	private FormFile file;//
	private ExportModel exportModel = new ExportModel();
	


	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getRzksrq() {
		return rzksrq;
	}
	public void setRzksrq(String rzksrq) {
		this.rzksrq = rzksrq;
	}
	public String getRzjsrq() {
		return rzjsrq;
	}
	public void setRzjsrq(String rzjsrq) {
		this.rzjsrq = rzjsrq;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getCzfs() {
		return czfs;
	}
	public void setCzfs(String czfs) {
		this.czfs = czfs;
	}
	public String getOld_xh() {
		return old_xh;
	}
	public void setOld_xh(String oldXh) {
		old_xh = oldXh;
	}
	public String getFp_ch() {
		return fp_ch;
	}
	public void setFp_ch(String fpCh) {
		fp_ch = fpCh;
	}
	public String getFp_qsh() {
		return fp_qsh;
	}
	public void setFp_qsh(String fpQsh) {
		fp_qsh = fpQsh;
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
