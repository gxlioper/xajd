package xsgzgl.pjpy.general.bjry;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

public class BjryglForm extends CommForm{
	private static final long serialVersionUID = -9205711105806100577L;
	private User user;
	private String guid;
	private String xn;//ѧ��
	private String xq;//ѧ��
	private String xqmc;//ѧ������
	private String bjdm;//�༶����
	private String rydm;//��������
	private String hdsj;//��ʱ��
	private String bz;//��ע
	private String nj;//�꼶
	private String xymc;//ѧԺ����
	private String zymc;//רҵ����
	private String bjmc;//�༶����
	private String mc;//��������
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getXn() {
		return xn;
	}
	public String setXn(String xn) {
		return this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getRydm() {
		return rydm;
	}
	public void setRydm(String rydm) {
		this.rydm = rydm;
	}
	public String getHdsj() {
		return hdsj;
	}
	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
}