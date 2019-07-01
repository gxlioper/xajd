
package xgxt.pjpy.ynys;

import xgxt.pjpy.PjpyActionForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 云南艺术评奖评优ActionForm
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-17</p>
 */
public class PjpyYnysActionForm extends PjpyActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8901022215624751603L;

	private String bjrs;//班级人数
	private String bzr;//班主任
	private String bjdbqk;//班级达标情况
	private String bzryj;//班主任意见
	private String fdyyj;//辅导员意见
	private String yxsh;//院系审核
	private String yxyj;//院系意见
	private String xxsh;//学校审核
	private String xxyj;//学校意见
	private String xyyj;//学院意见
	private String shxm;//审核项目
	private String jkzk;//健康状况
	private String yxsj;//优秀事迹
	private String bjyj;//班级意见
	private String jytyj;
	private String sxzzddszf;
	private String kxwhszf;
	private String sxlxszf;
	private String sjlxcxf;
	private String zhszcpzf;
	private String[] kcmc;
	private String[] cj;
	private String[] kclx;
	private String[] sfbxk;
	private String zykpjcj;
	private String whkpjcj;
	private String zhszcpcj;
	private String zhszcpcjpm;
	private String[] fbkw;
	private String[] lwhzpmc;
	private String[] hjjb;
	private String[] hjdj;
	private String zysj;
	private String ejyxyj;
	private String tzs;
	private String bhgqs;
	private String ywcf;
	private String bjry;
	private String yxdzbyj;
	private String xsrs;
	private String bzxm;
	private String bz;
	private String jtch;
	private String[] xid;
	private String delIds;
	
	public String getDelIds() {
		return delIds;
	}
	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}
	public String[] getXid() {
		return xid;
	}
	public void setXid(String[] xid) {
		this.xid = xid;
	}
	public String getJtch() {
		return jtch;
	}
	public void setJtch(String jtch) {
		this.jtch = jtch;
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
	public String getBhgqs() {
		return bhgqs;
	}
	public void setBhgqs(String bhgqs) {
		this.bhgqs = bhgqs;
	}
	public String getBjry() {
		return bjry;
	}
	public void setBjry(String bjry) {
		this.bjry = bjry;
	}
	public String getTzs() {
		return tzs;
	}
	public void setTzs(String tzs) {
		this.tzs = tzs;
	}
	public String getYwcf() {
		return ywcf;
	}
	public void setYwcf(String ywcf) {
		this.ywcf = ywcf;
	}
	public String getYxdzbyj() {
		return yxdzbyj;
	}
	public void setYxdzbyj(String yxdzbyj) {
		this.yxdzbyj = yxdzbyj;
	}
	public String getEjyxyj() {
		return ejyxyj;
	}
	public void setEjyxyj(String ejyxyj) {
		this.ejyxyj = ejyxyj;
	}
	public String getZysj() {
		return zysj;
	}
	public void setZysj(String zysj) {
		this.zysj = zysj;
	}
	public String getWhkpjcj() {
		return whkpjcj;
	}
	public void setWhkpjcj(String whkpjcj) {
		this.whkpjcj = whkpjcj;
	}
	public String getZhszcpcj() {
		return zhszcpcj;
	}
	public void setZhszcpcj(String zhszcpcj) {
		this.zhszcpcj = zhszcpcj;
	}
	public String getZhszcpcjpm() {
		return zhszcpcjpm;
	}
	public void setZhszcpcjpm(String zhszcpcjpm) {
		this.zhszcpcjpm = zhszcpcjpm;
	}
	public String getZykpjcj() {
		return zykpjcj;
	}
	public void setZykpjcj(String zykpjcj) {
		this.zykpjcj = zykpjcj;
	}
	public String getJytyj() {
		return jytyj;
	}
	public void setJytyj(String jytyj) {
		this.jytyj = jytyj;
	}
	public String getShxm() {
		return shxm;
	}
	public void setShxm(String shxm) {
		this.shxm = shxm;
	}
	public String getBjdbqk() {
		return bjdbqk;
	}
	public void setBjdbqk(String bjdbqk) {
		this.bjdbqk = bjdbqk;
	}
	public String getBjrs() {
		return bjrs;
	}
	public void setBjrs(String bjrs) {
		this.bjrs = bjrs;
	}
	public String getBzr() {
		return bzr;
	}
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	public String getBzryj() {
		return bzryj;
	}
	public void setBzryj(String bzryj) {
		this.bzryj = bzryj;
	}
	public String getFdyyj() {
		return fdyyj;
	}
	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxyj() {
		return xxyj;
	}
	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}
	public String getXyyj() {
		return xyyj;
	}
	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}
	public String getYxsh() {
		return yxsh;
	}
	public void setYxsh(String yxsh) {
		this.yxsh = yxsh;
	}
	public String getYxyj() {
		return yxyj;
	}
	public void setYxyj(String yxyj) {
		this.yxyj = yxyj;
	}
	public String getBjyj() {
		return bjyj;
	}
	public void setBjyj(String bjyj) {
		this.bjyj = bjyj;
	}
	public String getJkzk() {
		return jkzk;
	}
	public void setJkzk(String jkzk) {
		this.jkzk = jkzk;
	}
	public String getYxsj() {
		return yxsj;
	}
	public void setYxsj(String yxsj) {
		this.yxsj = yxsj;
	}
	public String getKxwhszf() {
		return kxwhszf;
	}
	public void setKxwhszf(String kxwhszf) {
		this.kxwhszf = kxwhszf;
	}
	public String getSjlxcxf() {
		return sjlxcxf;
	}
	public void setSjlxcxf(String sjlxcxf) {
		this.sjlxcxf = sjlxcxf;
	}
	public String getSxlxszf() {
		return sxlxszf;
	}
	public void setSxlxszf(String sxlxszf) {
		this.sxlxszf = sxlxszf;
	}
	public String getSxzzddszf() {
		return sxzzddszf;
	}
	public void setSxzzddszf(String sxzzddszf) {
		this.sxzzddszf = sxzzddszf;
	}
	public String getZhszcpzf() {
		return zhszcpzf;
	}
	public void setZhszcpzf(String zhszcpzf) {
		this.zhszcpzf = zhszcpzf;
	}
	public String[] getCj() {
		return cj;
	}
	public void setCj(String[] cj) {
		this.cj = cj;
	}
	public String[] getKclx() {
		return kclx;
	}
	public void setKclx(String[] kclx) {
		this.kclx = kclx;
	}
	public String[] getKcmc() {
		return kcmc;
	}
	public void setKcmc(String[] kcmc) {
		this.kcmc = kcmc;
	}
	public String[] getSfbxk() {
		return sfbxk;
	}
	public void setSfbxk(String[] sfbxk) {
		this.sfbxk = sfbxk;
	}
	public String[] getFbkw() {
		return fbkw;
	}
	public void setFbkw(String[] fbkw) {
		this.fbkw = fbkw;
	}
	public String[] getHjdj() {
		return hjdj;
	}
	public void setHjdj(String[] hjdj) {
		this.hjdj = hjdj;
	}
	public String[] getHjjb() {
		return hjjb;
	}
	public void setHjjb(String[] hjjb) {
		this.hjjb = hjjb;
	}
	public String[] getLwhzpmc() {
		return lwhzpmc;
	}
	public void setLwhzpmc(String[] lwhzpmc) {
		this.lwhzpmc = lwhzpmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
}
