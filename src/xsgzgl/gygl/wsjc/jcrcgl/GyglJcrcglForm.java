package xsgzgl.gygl.wsjc.jcrcgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.CommForm;
import xgxt.form.User;

public class GyglJcrcglForm extends CommForm {

	private static final long serialVersionUID = 9219083873367155307L;

	private User user;

	private String guid;
	private String jclx;
	private String jcyf;
	private String mc;
	
	private ExportModel exportModel = new ExportModel();

	
	
	/**
	 * @return the jclx
	 */
	public String getJclx() {
		return jclx;
	}

	/**
	 * @param jclx要设置的 jclx
	 */
	public void setJclx(String jclx) {
		this.jclx = jclx;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getJcyf() {
		return jcyf;
	}

	public void setJcyf(String jcyf) {
		this.jcyf = jcyf;
	}

	/**
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}

	/**
	 * @param mc要设置的 mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}
	
}