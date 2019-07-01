package xgxt.comm.xtwh;

import org.apache.struts.action.ActionForm;

public class CommXtwhForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String yhm;// 用户名

	private String tsyj;// 提示语句

	private String[] pic;// 快捷方式（图片）

	public String getTsyj() {
		return tsyj;
	}

	public void setTsyj(String tsyj) {
		this.tsyj = tsyj;
	}

	public String[] getPic() {
		return pic;
	}

	public void setPic(String[] pic) {
		this.pic = pic;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
}
