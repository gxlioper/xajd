
package xgxt.pjpy.ahjg;

import java.io.Serializable;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ս�����ҵѧԺ�������Ű༶������MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-12</p>
 */
public class BjbklSaveModel implements Serializable {

	/**
	 * �Զ����ɰ汾ID
	 */
	private static final long serialVersionUID = 1L;

	private String xn;//ѧ��
	private String xydm;//ѧԺ����
	private String zydm;//רҵ����
	private String bjdm;//�༶����
	private String bzxm;//�೤����
	private String xsrs;//ѧ������
	private String bzr;//������
	private String bjbkl;//�༶������
	
	public String getBjbkl() {
		return bjbkl;
	}
	public void setBjbkl(String bjbkl) {
		this.bjbkl = bjbkl;
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
	public String getXsrs() {
		return xsrs;
	}
	public void setXsrs(String xsrs) {
		this.xsrs = xsrs;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
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
