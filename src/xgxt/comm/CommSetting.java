package xgxt.comm;

public class CommSetting {

	// 结果集名称
	private String rsName;

	// 是否需要checkbox
	private String isCheckBox;

	// 显示的起始列
	private String startNum;

	// 显示的数量
	private String showNum;

	// 存在结果
	private String hadRs;

	public String getIsCheckBox() {
		return isCheckBox;
	}

	public void setIsCheckBox(String isCheckBox) {
		this.isCheckBox = isCheckBox;
	}

	public String getRsName() {
		return rsName;
	}

	public void setRsName(String rsName) {
		this.rsName = rsName;
	}

	public String getStartNum() {
		return startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}

	public String getShowNum() {
		return showNum;
	}

	public void setShowNum(String showNum) {
		this.showNum = showNum;
	}

	public String getHadRs() {
		return hadRs;
	}

	public void setHadRs(String hadRs) {
		this.hadRs = hadRs;
	}
}
