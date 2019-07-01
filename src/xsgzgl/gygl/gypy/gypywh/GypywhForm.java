package xsgzgl.gygl.gypy.gypywh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;
/**
 * 公寓管理-公寓评优-公寓评优管理
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class GypywhForm extends CommForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ExportModel exportModel = new ExportModel();
	
	private User user;
	private String pkValue;
	private String xn;
	private String xqdm;
	private String pylbdm;
	private String pysj;
	private String pydx;
	private String xh;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXqdm() {
		return xqdm;
	}
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	public String getPysj() {
		return pysj;
	}
	public void setPysj(String pysj) {
		this.pysj = pysj;
	}
	public String getPylbdm() {
		return pylbdm;
	}
	public void setPylbdm(String pylbdm) {
		this.pylbdm = pylbdm;
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
	public String getPydx() {
		return pydx;
	}
	public void setPydx(String pydx) {
		this.pydx = pydx;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
}
