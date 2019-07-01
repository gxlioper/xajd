package xgxt.pjpy.comm.pjpy.pjlc;

import xgxt.pjpy.comm.pjpy.PjpyCommForm;

public class PjpyPjlcForm extends PjpyCommForm {

	private static final long serialVersionUID = 1L;

	private String[] primarykey_checkVal;// CheckBox

	private String[] checkVal;// CheckBox

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}
}
