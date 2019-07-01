package xsgzgl.qgzx.yftssz;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;
/**
 * 勤工助学-基础设置-基础代码维护
 * @author yeyipin
 * @since 2018.8.13
 */
public class QgzxYftsszForm extends CommForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String xn;//年度
	private String yf;//月份
	private String[] months;//月份数组
	private String ts;//天数
	private String[] days;//天数
	private ExportModel exportModel = new ExportModel();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	
	public String[] getMonths() {
		return months;
	}
	public void setMonths(String[] months) {
		this.months = months;
	}
	public String[] getDays() {
		return days;
	}
	public void setDays(String[] days) {
		this.days = days;
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
