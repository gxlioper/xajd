package xgxt.xginterface.gxt;

import java.text.SimpleDateFormat;
import java.util.Date;

import xgxt.action.Base;

public class SendMess {
	String loginName;    //�û���
	String passWord;    //�ܳ�
    String schoolCode = Base.xxdm;    //ѧУ����
	String smsMsg;    //������Ϣ
	String sendTime = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(new Date());//����Ϣʱ��
	String mps;    //��������ѧ��
	long userDefId = 0;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getMps() {
		return mps;
	}
	public void setMps(String mps) {
		this.mps = mps;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getSmsMsg() {
		return smsMsg;
	}
	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}
	public long getUserDefId() {
		return userDefId;
	}
	public void setUserDefId(long userDefId) {
		this.userDefId = userDefId;
	}
}
