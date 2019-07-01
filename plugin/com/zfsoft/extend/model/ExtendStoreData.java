/**
 * @部门:学工产品事业部
 * @日期：2015-6-12 上午11:04:49 
 */  
package com.zfsoft.extend.model;

import java.io.Serializable;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-12 上午11:04:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendStoreData implements Serializable {
	
	private static final long serialVersionUID = 351210211797104143L;

	private String id;
	
	private String zjz;
	
	private String mid;
	
	private String meid;
	
	private String gid;
	
	private String zd;
	
	private String zdz;
	
	private String rn;

	
	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param id
	 * @param zjz
	 * @param mid
	 * @param meid
	 * @param gid
	 * @param zd
	 * @param zdz
	 * @param rn
	 */
	public ExtendStoreData(String id, String zjz, String mid, String meid,
			String gid, String zd, String zdz, String rn) {
		super();
		this.id = id;
		this.zjz = zjz;
		this.mid = mid;
		this.meid = meid;
		this.gid = gid;
		this.zd = zd;
		this.zdz = zdz;
		this.rn = rn;
	}

	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public ExtendStoreData() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the zjz
	 */
	public String getZjz() {
		return zjz;
	}

	/**
	 * @param zjz要设置的 zjz
	 */
	public void setZjz(String zjz) {
		this.zjz = zjz;
	}

	/**
	 * @return the mid
	 */
	public String getMid() {
		return mid;
	}

	/**
	 * @param mid要设置的 mid
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}

	/**
	 * @return the meid
	 */
	public String getMeid() {
		return meid;
	}

	/**
	 * @param meid要设置的 meid
	 */
	public void setMeid(String meid) {
		this.meid = meid;
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
	 * @return the zdz
	 */
	public String getZdz() {
		return zdz;
	}

	/**
	 * @param zdz要设置的 zdz
	 */
	public void setZdz(String zdz) {
		this.zdz = zdz;
	}

	/**
	 * @return the rn
	 */
	public String getRn() {
		return rn;
	}

	/**
	 * @param rn要设置的 rn
	 */
	public void setRn(String rn) {
		this.rn = rn;
	}
}
