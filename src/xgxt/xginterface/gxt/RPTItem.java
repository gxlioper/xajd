package xgxt.xginterface.gxt;

public class RPTItem {
	private String mobile;//接收人手机号码
	private String srvNo; // 发送特服号
	private long userdefid;//接口短信编号
	private int status;//状态值(具体值请查看高校通消息返回报告说明)
	private String rptTime;//回执时间
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRptTime() {
		return rptTime;
	}
	public void setRptTime(String rptTime) {
		this.rptTime = rptTime;
	}
	public String getSrvNo() {
		return srvNo;
	}
	public void setSrvNo(String srvNo) {
		this.srvNo = srvNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getUserdefid() {
		return userdefid;
	}
	public void setUserdefid(long userdefid) {
		this.userdefid = userdefid;
	}

}
