/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-9-10 ����10:16:38</p>
 */
package xgxt.studentInfo.ahjzgyxy;

public class StuTypeModel {
	// ѧ��
	private String xh;

	// ����
	private String xm;

	// �Ա�
	private String xb;

	// �꼶
	private String nj;

	// ѧԺ����
	private String xydm;

	// רҵ����
	private String zydm;

	// �༶����
	private String bjdm;
	
	//���ͣɣ�
	private String[] lxids;

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String[] getLxids() {
		return lxids;
	}

	public void setLxids(String[] lxids) {
		this.lxids = lxids;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

}
