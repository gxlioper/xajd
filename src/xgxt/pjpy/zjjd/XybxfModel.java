
package xgxt.pjpy.zjjd;

import xgxt.base.DealString;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �㽭������������У԰ƽ����MODEL
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-05</p>
 */
public class XybxfModel {

	private String xn;//ѧ��
	private String xq;//ѧ��
	private String yf;//�·�
	private String xh;//ѧ��
	private String xm;//����
	private String xydm;//ѧԺ����
	private String zydm;//רҵ����
	private String bjdm;//�༶����
	private String jf;//�ӷ�
	private String kf;//�۷�
	private String sx;//����
	private String nj;
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getJf() {
		return jf;
	}
	public void setJf(String jf) {
		this.jf = jf;
	}
	public String getKf() {
		return kf;
	}
	public void setKf(String kf) {
		this.kf = kf;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	@Override
	public String toString() {
		// TODO �Զ����ɷ������
		return DealString.toGBK(xm);
	}
}
