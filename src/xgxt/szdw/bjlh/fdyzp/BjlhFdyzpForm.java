package xgxt.szdw.bjlh.fdyzp;

import org.apache.struts.action.ActionForm;

import xgxt.comm.CommForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class BjlhFdyzpForm extends CommForm{
	private static final long serialVersionUID = -9205711105806100577L;
		
	private String zpbid;//自评表ID
	private String xn;//学年
	private String mc;//名称
	private String rq;//日期
	private String sfqy;//是否启用
	
	private String[] xmid;//项目ID
	private String[] khxm;//考核项目
	private String[] xmlx;//项目类型
	private String[] xmbz;//备注
	
	private String yhm;//用户名
	private String xm;//姓名
	private String xy;//学院
	private String[] wcqk;//完成情况
	private String[] sftj;//是否提交
	public String getZpbid() {
		return zpbid;
	}
	public void setZpbid(String zpbid) {
		this.zpbid = zpbid;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	public String[] getXmid() {
		return xmid;
	}
	public void setXmid(String[] xmid) {
		this.xmid = xmid;
	}
	public String[] getKhxm() {
		return khxm;
	}
	public void setKhxm(String[] khxm) {
		this.khxm = khxm;
	}
	public String[] getXmlx() {
		return xmlx;
	}
	public void setXmlx(String[] xmlx) {
		this.xmlx = xmlx;
	}
	public String[] getXmbz() {
		return xmbz;
	}
	public void setXmbz(String[] xmbz) {
		this.xmbz = xmbz;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String[] getWcqk() {
		return wcqk;
	}
	public void setWcqk(String[] wcqk) {
		this.wcqk = wcqk;
	}
	public String[] getSftj() {
		return sftj;
	}
	public void setSftj(String[] sftj) {
		this.sftj = sftj;
	}
	
	
}
