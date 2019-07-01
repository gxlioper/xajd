package xgxt.pjpy.czxx.tycj;

import xgxt.pjpy.tablesmodel.PjpyModel;
import xgxt.utils.Pages;

public class TycjModel extends PjpyModel {

	private String userName;
	private Pages pages = new Pages();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
