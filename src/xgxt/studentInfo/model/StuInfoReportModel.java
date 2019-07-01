package xgxt.studentInfo.model;

public class StuInfoReportModel {

	private boolean isMzmcNull;//民族是否有空的
	
	private boolean isHkxzNull;//户口性质是否有空的
	
	private boolean isXbNull;//性别是否有空的
	

	public boolean isHkxzNull() {
		return isHkxzNull;
	}

	public void setHkxzNull(boolean isHkxzNull) {
		this.isHkxzNull = isHkxzNull;
	}

	public boolean isXbNull() {
		return isXbNull;
	}

	public void setXbNull(boolean isXbNull) {
		this.isXbNull = isXbNull;
	}

	public boolean isMzmcNull() {
		return isMzmcNull;
	}

	public void setMzmcNull(boolean isMzmcNull) {
		this.isMzmcNull = isMzmcNull;
	}
	
}
