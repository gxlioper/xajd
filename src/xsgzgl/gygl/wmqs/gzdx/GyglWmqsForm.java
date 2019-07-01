package xsgzgl.gygl.wmqs.gzdx;

import xsgzgl.gygl.comm.GyglNewForm;

public class GyglWmqsForm extends GyglNewForm{
	
	//文明寝室个数维护
	private String[] xydms;//学院代码数组
	private String[] wmqsgss;//文明寝室个数数组

	//文明寝室申请
	private String lddm;//楼栋代码
	private String qsh;//寝室号
	private String qsrs;//寝室人数
	private String sqsm;//申请说明
	private String sqr;//申请人
	
	//文明寝室审核
	private String[] primarykey_checkVal;//审核主键数组
	private String userType;//用户类型  辅导员，学校
	private String shzt;//审核状态
	private String shbz;//审核备注
	
	//文明寝室管理
	private String pkValue;//操作主键

	public String[] getXydms() {
		return xydms;
	}
	public void setXydms(String[] xydms) {
		this.xydms = xydms;
	}
	public String[] getWmqsgss() {
		return wmqsgss;
	}
	public void setWmqsgss(String[] wmqsgss) {
		this.wmqsgss = wmqsgss;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getQsrs() {
		return qsrs;
	}
	public void setQsrs(String qsrs) {
		this.qsrs = qsrs;
	}
	public String getSqsm() {
		return sqsm;
	}
	public void setSqsm(String sqsm) {
		this.sqsm = sqsm;
	}
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}
	public void setPrimarykey_checkVal(String[] primarykeyCheckVal) {
		primarykey_checkVal = primarykeyCheckVal;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getShbz() {
		return shbz;
	}
	public void setShbz(String shbz) {
		this.shbz = shbz;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

}
