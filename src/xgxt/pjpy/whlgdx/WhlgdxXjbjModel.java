package xgxt.pjpy.whlgdx;

import java.io.Serializable;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �人����ѧ���������Ƚ��༶Model
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-04</p>
 */
@SuppressWarnings("serial")
public class WhlgdxXjbjModel implements Serializable {
	private String xn;//ѧ��
	private String nd;//���
	private String xq;//ѧ��
	private String xydm;//ѧԺ����
	private String xymc;//ѧԺ����
	private String zydm;//רҵ����
	private String zymc;//רҵ����
	private String bjdm;//�༶����
	private String bjmc;//�༶����
	private String nj;//�꼶
	private String xjbjlbdm;//�Ƚ��༶������
	private String xjbjlbmc;//�Ƚ��༶�������
	private String bz;//��ע
	private String userName;//�û���
	private String userType;//�û�����
	private boolean isFdy;//�Ƿ�Ϊ����Ա
	
	public boolean isFdy() {
		return isFdy;
	}
	public void setFdy(boolean isFdy) {
		this.isFdy = isFdy;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXjbjlbdm() {
		return xjbjlbdm;
	}
	public void setXjbjlbdm(String xjbjlbdm) {
		this.xjbjlbdm = xjbjlbdm;
	}
	public String getXjbjlbmc() {
		return xjbjlbmc;
	}
	public void setXjbjlbmc(String xjbjlbmc) {
		this.xjbjlbmc = xjbjlbmc;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
}
