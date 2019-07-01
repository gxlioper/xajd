/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 公寓管理房源库自动生成、房间单个修改MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-8-6 下午04:51:20</p>
 */
package xgxt.xsgygl.zgdzdx;

public class GyglZgdzdxModel {
	
	private String lddm;//楼栋代码
	private String cs;//层数
	private String cfjs;//每层房间数
	private String jcws;//每间床位数
	private String sfbz;//每间收费标准
	private String fpbz;//分配标志(房间开放对象类型)
	private String ssbh;//宿舍编号
	private String qsh;//寝室号(房间号)
	private String xiaoqu;
	private String sfbz1;
	private String sfbz2;
	private String qsdh;//寝室电话
	private String sffqfj;//是否夫妻房间
	private String bz;
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	public String getCfjs() {
		return cfjs;
	}
	public void setCfjs(String cfjs) {
		this.cfjs = cfjs;
	}
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = cs;
	}
	public String getFpbz() {
		return fpbz;
	}
	public void setFpbz(String fpbz) {
		this.fpbz = fpbz;
	}
	public String getJcws() {
		return jcws;
	}
	public void setJcws(String jcws) {
		this.jcws = jcws;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getSfbz() {
		return sfbz;
	}
	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}
	public String getSfbz1() {
		return sfbz1;
	}
	public void setSfbz1(String sfbz1) {
		this.sfbz1 = sfbz1;
	}
	public String getSfbz2() {
		return sfbz2;
	}
	public void setSfbz2(String sfbz2) {
		this.sfbz2 = sfbz2;
	}
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getQsdh() {
		return qsdh;
	}
	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}
	public String getSffqfj() {
		return sffqfj;
	}
	public void setSffqfj(String sffqfj) {
		this.sffqfj = sffqfj;
	}
}
