package xsgzgl.qgzx.tjcx;

import xgxt.comm.CommForm;
import xgxt.form.User;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-23 ����14:19:22
 * </p>
 */

public class QgzxTjcxForm extends CommForm{

	private static final long serialVersionUID = 9219083873367155307L;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}