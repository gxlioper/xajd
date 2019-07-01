package xgxt.xginterface.gxt;

public class MOItem {
	private String mobile;// 发送方手机号码
	private String srvNo; // 上行特服号
	private String msg;   // 短信内容
	private String moTime;// 回复时间  
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMoTime() {
		return moTime;
	}
	public void setMoTime(String moTime) {
		this.moTime = moTime;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSrvNo() {
		return srvNo;
	}
	public void setSrvNo(String srvNo) {
		this.srvNo = srvNo;
	}

}
