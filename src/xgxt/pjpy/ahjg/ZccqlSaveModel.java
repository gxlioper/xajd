
package xgxt.pjpy.ahjg;

import java.io.Serializable;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ս�����ҵѧԺ����������ٳ����ʱ���MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */
public class ZccqlSaveModel implements Serializable {

	/**
	 * �Զ����ɰ汾ID
	 */
	private static final long serialVersionUID = 1L;

	private String bjdm;//�༶����
	private String xn;//ѧ��
	private String bzxm;//�೤����
	private String bzr;//������
	private String xsrs;//ѧ������
	private String zccql;//��ٳ�����
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBzr() {
		return bzr;
	}
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	public String getBzxm() {
		return bzxm;
	}
	public void setBzxm(String bzxm) {
		this.bzxm = bzxm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXsrs() {
		return xsrs;
	}
	public void setXsrs(String xsrs) {
		this.xsrs = xsrs;
	}
	public String getZccql() {
		return zccql;
	}
	public void setZccql(String zccql) {
		this.zccql = zccql;
	}
}
