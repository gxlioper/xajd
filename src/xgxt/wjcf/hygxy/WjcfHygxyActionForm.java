
package xgxt.wjcf.hygxy;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 淮阴工学院违纪处分ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-23</p>
 */
public class WjcfHygxyActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xh;//学号
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String bjdm;//班级代码
	private String xn;//学年
	private String xq;//学期
	private String nd;//年度
	private String nj;//年级
	private String xm;//姓名
	private String jyr;//教育人
	private String jysj;//教育时间
	private String jyzt;//教育主题
	private String jyrbx;//教育人表现
	private String pkValue;//主键
	private String bz;
	private String cfxn;
	private String cfxq;
	private String cfsbsj;
	private String cfpk;
	private String cfsj;
	private String cfwh;
	private String cflb;
	private String cfyy;
	private String[] cbv;
	private String wyhsl;
	private String wyhsllr;
	
	public String getWyhsl() {
		return wyhsl;
	}
	public void setWyhsl(String wyhsl) {
		this.wyhsl = wyhsl;
	}
	public String getWyhsllr() {
		return wyhsllr;
	}
	public void setWyhsllr(String wyhsllr) {
		this.wyhsllr = wyhsllr;
	}
	public String getJyr() {
		return jyr;
	}
	public void setJyr(String jyr) {
		this.jyr = jyr;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
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
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getJyrbx() {
		return jyrbx;
	}
	public void setJyrbx(String jyrbx) {
		this.jyrbx = jyrbx;
	}
	public String getJysj() {
		return jysj;
	}
	public void setJysj(String jysj) {
		this.jysj = jysj;
	}
	public String getJyzt() {
		return jyzt;
	}
	public void setJyzt(String jyzt) {
		this.jyzt = jyzt;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCfsbsj() {
		return cfsbsj;
	}
	public void setCfsbsj(String cfsbsj) {
		this.cfsbsj = cfsbsj;
	}
	public String getCfxn() {
		return cfxn;
	}
	public void setCfxn(String cfxn) {
		this.cfxn = cfxn;
	}
	public String getCfxq() {
		return cfxq;
	}
	public void setCfxq(String cfxq) {
		this.cfxq = cfxq;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getCfpk() {
		return cfpk;
	}
	public void setCfpk(String cfpk) {
		this.cfpk = cfpk;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getCfwh() {
		return cfwh;
	}
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}
	public String getCflb() {
		return cflb;
	}
	public void setCflb(String cflb) {
		this.cflb = cflb;
	}
	public String getCfyy() {
		return cfyy;
	}
	public void setCfyy(String cfyy) {
		this.cfyy = cfyy;
	}
	
}
