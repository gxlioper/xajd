package xsgzgl.szdw.txgb;

import xgxt.comm.CommForm;
import xgxt.form.User;

public class SzdwTxgbForm extends CommForm{

	private static final long serialVersionUID = 9219083873367155307L;
	
	private User user;
	
	private String pkValue;

	
	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
