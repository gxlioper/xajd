package xgxt.xginterface.gxt;

public class RPTItem {
	private String mobile;//�������ֻ�����
	private String srvNo; // �����ط���
	private long userdefid;//�ӿڶ��ű��
	private int status;//״ֵ̬(����ֵ��鿴��Уͨ��Ϣ���ر���˵��)
	private String rptTime;//��ִʱ��
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
