package xsgzgl.jxgl.hzsf.tdry;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

public class JxglTdryForm extends CommForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String pkValue;
	private String query;
	private String doType;//����(add,update)
	private String xn;//ѧ��
	private String bzbh;//���Ʊ��
	private String bzjbdm;//���Ƽ������(tj,yj,lj)
	private String bzdj;//���Ƶȼ���1,2,3,4��
	private String bzdm;//���ƴ���
	private String sjdm;//�ϼ�����
	private String tuandm;//�Ŵ���
	private String yingdm;//Ӫ����
	private String liandm;//������
	private String tdrydm;//�Ŷ���������
	private String bz;//��ע
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getBzbh() {
		return bzbh;
	}
	public void setBzbh(String bzbh) {
		this.bzbh = bzbh;
	}
	public String getBzjbdm() {
		return bzjbdm;
	}
	public void setBzjbdm(String bzjbdm) {
		this.bzjbdm = bzjbdm;
	}
	public String getBzdj() {
		return bzdj;
	}
	public void setBzdj(String bzdj) {
		this.bzdj = bzdj;
	}
	public String getBzdm() {
		return bzdm;
	}
	public void setBzdm(String bzdm) {
		this.bzdm = bzdm;
	}
	public String getSjdm() {
		return sjdm;
	}
	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}
	public String getTuandm() {
		return tuandm;
	}
	public void setTuandm(String tuandm) {
		this.tuandm = tuandm;
	}
	public String getYingdm() {
		return yingdm;
	}
	public void setYingdm(String yingdm) {
		this.yingdm = yingdm;
	}
	public String getLiandm() {
		return liandm;
	}
	public void setLiandm(String liandm) {
		this.liandm = liandm;
	}
	public String getTdrydm() {
		return tdrydm;
	}
	public void setTdrydm(String tdrydm) {
		this.tdrydm = tdrydm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
