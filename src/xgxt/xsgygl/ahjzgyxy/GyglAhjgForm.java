package xgxt.xsgygl.ahjzgyxy;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 安徽建筑工业学院公寓管理ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-30</p>
 */
public class GyglAhjgForm extends ActionForm {

	private static final long serialVersionUID = -8604354673704600224L;
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
	String qslb;//寝室类别
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
