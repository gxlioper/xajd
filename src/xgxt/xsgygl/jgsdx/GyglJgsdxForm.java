/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学公寓管理form</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-10 上午11:35:17</p>
 */
package xgxt.xsgygl.jgsdx;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;

public class GyglJgsdxForm extends ActionForm {
	
	private static final long serialVersionUID = -8152088433177887280L;
	String xy;//学院
	String zy;//专业
	String bj;//班级
	String xh;//学号
	String nj;//年级
	String zgh;//辅导员职工号
	String xm;//姓名
	String xn;//学年
	String xq;//学期
	String lddm;//楼栋
	String qsh;//寝室号
	String pysj;//时间
	String zywt;//主要问题内容
	String bz;//备注
	String yesNo;//审核标志
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
