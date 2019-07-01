package xsgzgl.gygl.gyjlxxglnew;


import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

public class GyjlxxglNewForm extends CommForm{

	private static final long serialVersionUID = 1L;
	
	public User user;
	private String pkValue;
	private String sfcl;
	private String cljg;
	private String dcqk;

	private String shzt;
	private String shr;
	private String shyj;
	private String shsj;
	private String ylzd1;
	private String ylzd2;
	private String ylzd3;
	private ExportModel exportModel = new ExportModel();
	
	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getSfcl() {
		return sfcl;
	}

	public void setSfcl(String sfcl) {
		this.sfcl = sfcl;
	}

	public String getCljg() {
		return cljg;
	}

	public void setCljg(String cljg) {
		this.cljg = cljg;
	}

	public String getDcqk() {
		return dcqk;
	}

	public void setDcqk(String dcqk) {
		this.dcqk = dcqk;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
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

	/**
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}

	/**
	 * @param ylzd1要设置的 ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}

	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}

	/**
	 * @param ylzd2要设置的 ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}

	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}

	/**
	 * @param ylzd3要设置的 ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	
}
