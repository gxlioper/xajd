
package xgxt.wjcf.jgsdx;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学违纪处分ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-24</p>
 */
public class WjcfJgsdxActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xn;//学年
	private String xq;//学期
	private String xh;//学号
	private String xm;//姓名
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String bjdm;//班级代码
	private String nj;//年级
	private String cfwh;//处分文号
	private String bz;//备注
	private String xymc;//学院名称
	private String zymc;//专业名称
	private String bjmc;//班级名称
	private String cfsj;//处分时间
	private String xb;//性别
	private String cxsj;//撤消时间
	private String cfyymc;//处分原因名称
	private String cflbmc;//处分类别名称
	private String cflbdm;//处分类别代码
	private String cfyydm;//处分原因代码
	private String[] cbv;//多选列表
	private String spzt;//审批状态
	private String cxwh;//解除文号
	private String pkValue;//主键
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getCxwh() {
		return cxwh;
	}
	public void setCxwh(String cxwh) {
		this.cxwh = cxwh;
	}
	public String getSpzt() {
		return spzt;
	}
	public void setSpzt(String spzt) {
		this.spzt = spzt;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getCflbdm() {
		return cflbdm;
	}
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}
	public String getCflbmc() {
		return cflbmc;
	}
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
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
	public String getCfyydm() {
		return cfyydm;
	}
	public void setCfyydm(String cfyydm) {
		this.cfyydm = cfyydm;
	}
	public String getCfyymc() {
		return cfyymc;
	}
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	public String getCxsj() {
		return cxsj;
	}
	public void setCxsj(String cxsj) {
		this.cxsj = cxsj;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
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
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
}
