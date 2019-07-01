package xgxt.xsgygl.zjlgdx;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
import xgxt.utils.Pages;
/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 安徽建筑工业学院公寓管理ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-30</p>
 */
public class GyglZjlgForm extends ActionForm {

	private static final long serialVersionUID = -8604354673704600224L;
	private String xy;//学院
	private String zy;//专业
	private String bj;//班级
	private String xh;//学号
	private String nj;//年级
	private String zgh;//辅导员职工号
	private String xm;//姓名
	private String xn;//学年
	private String xq;//学期
	private String lddm;//楼栋
	private String qsh;//寝室号
	private String pysj;//时间
	private String zywt;//主要问题内容
	private String bz;//备注
	private String yesNo;//审核标志
	private String qslb;//寝室类别
	private String lc;
	private String sqly;
	private String lxdh;
	private String sqsj;
	private String shsj;
	private String xxsh;
	private String xxshyj;
	private String sfcx;
	private String cxsj;
	private String act;
	private String ssbh;
	private String sqrq1;
	private String sqrq2;
	private String zs;
	private String fs;
	private String jtgznr;
	private String ykzgzqk;
	private String xysh;
	private String xydm;
	private String zydm;
	private String bjdm;
	private Pages pages = new Pages();
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
	public String getCxsj() {
		return cxsj;
	}
	public void setCxsj(String cxsj) {
		this.cxsj = cxsj;
	}
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getSfcx() {
		return sfcx;
	}
	public void setSfcx(String sfcx) {
		this.sfcx = sfcx;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxshyj() {
		return xxshyj;
	}
	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	public String getFs() {
		return fs;
	}
	public void setFs(String fs) {
		this.fs = fs;
	}
	public String getSqrq1() {
		return sqrq1;
	}
	public void setSqrq1(String sqrq1) {
		this.sqrq1 = sqrq1;
	}
	public String getSqrq2() {
		return sqrq2;
	}
	public void setSqrq2(String sqrq2) {
		this.sqrq2 = sqrq2;
	}
	public String getZs() {
		return zs;
	}
	public void setZs(String zs) {
		this.zs = zs;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getJtgznr() {
		return jtgznr;
	}
	public void setJtgznr(String jtgznr) {
		this.jtgznr = jtgznr;
	}
	public String getYkzgzqk() {
		return ykzgzqk;
	}
	public void setYkzgzqk(String ykzgzqk) {
		this.ykzgzqk = ykzgzqk;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
