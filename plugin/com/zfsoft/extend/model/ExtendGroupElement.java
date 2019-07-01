/**
 * @部门:学工产品事业部
 * @日期：2015-6-3 上午09:22:52 
 */  
package com.zfsoft.extend.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.extend.service.ZDSource;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-3 上午09:22:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendGroupElement implements Serializable{

	private static final long serialVersionUID = 8277734477423712182L;
	
	private String zd;
	
	private String mc;
	
	private String lx;
	
	private String sj;
	
	private String gs;
	
	private String mrz;
	
	private String bt;
	
	private String cd;
	
	private String bz;
	
	private String xssx;
	
	private String gid;

	private String sfbj;
	
	private String elcd;
	/////////////////////////////////
	private List<HashMap<String,String>> zdData; //字段数据
	////////////////////////////////
	
	public ZDSource obtainZDSourceInstance(){
		if(StringUtils.isBlank(sj)){
			return null;
		}
		String method = StringUtils.substringBefore(sj, ":");
		String source = StringUtils.substringAfter(sj, ":");
		return new ZDSource(method, source);
	}
	
	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public ExtendGroupElement() {
		super();
	}

	

	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param zd
	 * @param mc
	 * @param lx
	 * @param sj
	 * @param gs
	 * @param mrz
	 * @param bt
	 * @param cd
	 * @param bz
	 * @param xssx
	 * @param gid
	 * @param sfbj
	 */
	public ExtendGroupElement(String zd, String mc, String lx, String sj,
			String gs, String mrz, String bt, String cd, String bz,
			String xssx, String gid, String sfbj,String elcd) {
		super();
		this.zd = zd;
		this.mc = mc;
		this.lx = lx;
		this.sj = sj;
		this.gs = gs;
		this.mrz = mrz;
		this.bt = bt;
		this.cd = cd;
		this.bz = bz;
		this.xssx = xssx;
		this.gid = gid;
		this.sfbj = sfbj;
		this.elcd = elcd;
	}

	/**
	 * @return the zd
	 */
	public String getZd() {
		return zd;
	}

	/**
	 * @param zd要设置的 zd
	 */
	public void setZd(String zd) {
		this.zd = zd;
	}

	/**
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}

	/**
	 * @param mc要设置的 mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}

	/**
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}

	/**
	 * @param lx要设置的 lx
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}

	/**
	 * @return the sj
	 */
	public String getSj() {
		return sj;
	}

	/**
	 * @param sj要设置的 sj
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}

	/**
	 * @return the gs
	 */
	public String getGs() {
		return gs;
	}

	/**
	 * @param gs要设置的 gs
	 */
	public void setGs(String gs) {
		this.gs = gs;
	}

	/**
	 * @return the mrz
	 */
	public String getMrz() {
		return mrz;
	}

	/**
	 * @param mrz要设置的 mrz
	 */
	public void setMrz(String mrz) {
		this.mrz = mrz;
	}

	/**
	 * @return the bt
	 */
	public String getBt() {
		return bt;
	}

	/**
	 * @param bt要设置的 bt
	 */
	public void setBt(String bt) {
		this.bt = bt;
	}

	/**
	 * @return the cd
	 */
	public String getCd() {
		return cd;
	}

	/**
	 * @param cd要设置的 cd
	 */
	public void setCd(String cd) {
		this.cd = cd;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the xssx
	 */
	public String getXssx() {
		return xssx;
	}

	/**
	 * @param xssx要设置的 xssx
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	/**
	 * @return the gid
	 */
	public String getGid() {
		return gid;
	}

	/**
	 * @param gid要设置的 gid
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}

	/**
	 * @return the zdData
	 */
	public List<HashMap<String, String>> getZdData() {
		return zdData;
	}

	/**
	 * @param zdData要设置的 zdData
	 */
	public void setZdData(List<HashMap<String, String>> zdData) {
		this.zdData = zdData;
	}

	/**
	 * @return the sfbj
	 */
	public String getSfbj() {
		return sfbj;
	}

	/**
	 * @param sfbj要设置的 sfbj
	 */
	public void setSfbj(String sfbj) {
		this.sfbj = sfbj;
	}

	/**
	 * @return the elcd
	 */
	public String getElcd() {
		return elcd;
	}

	/**
	 * @param elcd要设置的 elcd
	 */
	public void setElcd(String elcd) {
		this.elcd = elcd;
	}

}
