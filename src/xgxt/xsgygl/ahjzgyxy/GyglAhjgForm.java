package xgxt.xsgygl.ahjzgyxy;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ս�����ҵѧԺ��Ԣ����ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-30</p>
 */
public class GyglAhjgForm extends ActionForm {

	private static final long serialVersionUID = -8604354673704600224L;
	String xy;//ѧԺ
	String zy;//רҵ
	String bj;//�༶
	String xh;//ѧ��
	String nj;//�꼶
	String zgh;//����Աְ����
	String xm;//����
	String xn;//ѧ��
	String xq;//ѧ��
	String lddm;//¥��
	String qsh;//���Һ�
	String pysj;//ʱ��
	String zywt;//��Ҫ��������
	String bz;//��ע
	String yesNo;//��˱�־
	String qslb;//�������
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = DealString.toGBK(yesNo);
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = DealString.toGBK(bz);
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
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
		this.xm = DealString.toGBK(xm);
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
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getZywt() {
		return zywt;
	}
	public void setZywt(String zywt) {
		this.zywt = zywt;
	}
	public String getPysj() {
		return pysj;
	}
	public void setPysj(String pysj) {
		this.pysj = pysj;
	}
	public String getQslb() {
		return qslb;
	}
	public void setQslb(String qslb) {
		this.qslb = DealString.toGBK(qslb);
	}
}
