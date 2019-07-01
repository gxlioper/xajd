package xsgzgl.pjpy.general.tjfx;

import xgxt.comm.CommForm;
import xgxt.form.User;

public class HjxsmdForm extends CommForm {
	private static final long serialVersionUID = 1L;

	private User user;
	private String xn;
	private String xy;

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
