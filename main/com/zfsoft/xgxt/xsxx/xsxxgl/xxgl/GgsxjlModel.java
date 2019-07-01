/**
 * @部门:学工产品事业部
 * @日期：2014-12-3 下午01:53:07
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 跟岗实习信息
 * @作者：沈晓波[工号:1123]
 * @时间： 2016-3-17 上午11:00:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GgsxjlModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ggid; // 主键ID
	private String xh;   // 学号
	private String ggsxdw; // 跟岗实习单位
	private String rzsj; // 入职时间
	private String fxsj; // 返校时间
	private String gw;   // 岗位
	
	
	public String getGgid() {
		return ggid;
	}
	public void setGgid(String ggid) {
		this.ggid = ggid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getGgsxdw() {
		return ggsxdw;
	}
	public void setGgsxdw(String ggsxdw) {
		this.ggsxdw = ggsxdw;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getFxsj() {
		return fxsj;
	}
	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}
	public String getGw() {
		return gw;
	}
	public void setGw(String gw) {
		this.gw = gw;
	}
	
	

}
