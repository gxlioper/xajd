/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ɽ��ѧ��Ԣ����form</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-10 ����11:35:17</p>
 */
package xgxt.xsgygl.jgsdx;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;

public class GyglJgsdxForm extends ActionForm {
	
	private static final long serialVersionUID = -8152088433177887280L;
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
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = DealString.toGBK(bj);
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
		this.lddm =  DealString.toGBK(lddm);
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj =  DealString.toGBK(nj);
	}
	public String getPysj() {
		return pysj;
	}
	public void setPysj(String pysj) {
		this.pysj =  DealString.toGBK(pysj);
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh =  DealString.toGBK(qsh);
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh =  DealString.toGBK(xh);
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm =  DealString.toGBK(xm);
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn =  DealString.toGBK(xn);
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq =  DealString.toGBK(xq);
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy =  DealString.toGBK(xy);
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo =  DealString.toGBK(yesNo);
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh =  DealString.toGBK(zgh);
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy =  DealString.toGBK(zy);
	}
	public String getZywt() {
		return zywt;
	}
	public void setZywt(String zywt) {
		this.zywt =  DealString.toGBK(zywt);
	}

}
