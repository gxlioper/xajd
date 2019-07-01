package xsgzgl.gygl.hzsf;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;
/**
 * 公寓管理-湖州师范-楼栋考核管理
 * @author yeyipin
 * @since 2012.12.25 merry christmas
 */
public class LdkhglForm extends CommForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	private String pkValue;

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
	
}
