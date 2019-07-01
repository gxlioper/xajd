
package xgxt.wjcf.szzy;

import org.apache.struts.action.ActionForm;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 深圳职业信息技术学院学生考勤信息Parameter</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-12</p>
 */
public class WjcfSzzyActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String nj;//年级
	
	private String xn;//学年
	
	private String xq;//学期
	
	private String xydm;//学院代码
	
	private String zydm;//专业代码
	
	private String bjdm;//班级代码
	
	private String xqj;//星期几
	
	private String jsbh;//教室编号

	private String rq;//日期
	
	private String ydrs;//应到人数

	private String sdrs;//实到人数
	
	private String qjrs;//请假人数
	
	private String cql;//出勤率
	
	private String qq;//缺勤
	
	private String kqy;//考勤员
	
	private String bjsj;//病假事假
	
	private String gj;//公假
	
	private String xn_id;//虚拟主键
	
	private String kssj;//开始时间
	
	private String jssj;//结束时间
	
	private String bz;//备注
	
	private String sjd;//时间段
	
	private String kcdm;//课程代码
	
	private String nf;//年份
	
	private String yf;//月份
	
	public String getKcdm() {
		return kcdm;
	}

	public void setKcdm(String kcdm) {
		this.kcdm = kcdm;
	}

	public String getSjd() {
		return sjd;
	}

	public void setSjd(String sjd) {
		this.sjd = sjd;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getXn_id() {
		return xn_id;
	}

	public void setXn_id(String xn_id) {
		this.xn_id = xn_id;
	}

	public String getBjsj() {
		return bjsj;
	}

	public void setBjsj(String bjsj) {
		this.bjsj = bjsj;
	}

	public String getCql() {
		return cql;
	}

	public void setCql(String cql) {
		this.cql = cql;
	}

	public String getGj() {
		return gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getKqy() {
		return kqy;
	}

	public void setKqy(String kqy) {
		this.kqy = kqy;
	}

	public String getQjrs() {
		return qjrs;
	}

	public void setQjrs(String qjrs) {
		this.qjrs = qjrs;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getSdrs() {
		return sdrs;
	}

	public void setSdrs(String sdrs) {
		this.sdrs = sdrs;
	}

	public String getYdrs() {
		return ydrs;
	}

	public void setYdrs(String ydrs) {
		this.ydrs = ydrs;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
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

	public String getXqj() {
		return xqj;
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

	public void setXqj(String xqj) {
		this.xqj = xqj;
	}

	public String getNf() {
		return nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

}
