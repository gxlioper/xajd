/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-30 下午01:58:06</p>
 */
package xgxt.xsgygl.zjgszyjsxy;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;

public class GyglZjgszyForm extends ActionForm {

	private static final long serialVersionUID = 6092179630349487581L;
	
    String xqdm;//校区代码
    String lddm;
    String qsh;
    String ssbh;
    String xh;
    String xm;
    String bm;
    String zw;
    String lxdh;
    String rzrq;
    String lzrq;
    String bz;
    String xn;
    String xq;
    String rq;
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
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
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = DealString.toGBK(bm);
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = DealString.toGBK(bz);
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = DealString.toGBK(lxdh);
	}
	public String getLzrq() {
		return lzrq;
	}
	public void setLzrq(String lzrq) {
		this.lzrq = lzrq;
	}
	public String getRzrq() {
		return rzrq;
	}
	public void setRzrq(String rzrq) {
		this.rzrq = rzrq;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = DealString.toGBK(zw);
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getXqdm() {
		return xqdm;
	}
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = DealString.toGBK(xh);
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = DealString.toGBK(xm);
	}
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
}
