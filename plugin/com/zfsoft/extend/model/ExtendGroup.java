/**
 * @部门:学工产品事业部
 * @日期：2015-6-2 下午03:04:09 
 */  
package com.zfsoft.extend.model;

import java.io.Serializable;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-2 下午03:04:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendGroup implements Serializable {

	private static final long serialVersionUID = 1L;

    public static final String SFSH_Y = "1";
	
	public static final String SFSH_N = "0";
	
	public static final String LX_NORMAL = "normal";
	
	public static final String LX_LIST = "list";
	
	////////////////////////FIELDS///////////////////
	private String id;      //主键
	
	private String mc;      //组名称
	
	private String lx;      //组类型，NORMAL,TABLE
	
	private String xssx;    //显示顺序
	
	private String meid;     //所属模块ID
	
	private String tsxz;     //如果是列表，可以使用该字段限制输入的条数，如果不填，没有限制
   ////////////////////////FIELDS///////////////////

	private List<ExtendGroupElement> extendGroupElementList;
	
	
	
	
	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param id
	 * @param mc
	 * @param lx
	 * @param xssx
	 * @param meid
	 */
	public ExtendGroup(String id, String mc, String lx, String xssx, String meid, String tsxz) {
		super();
		this.id = id;
		this.mc = mc;
		this.lx = lx;
		this.xssx = xssx;
		this.meid = meid;
		this.tsxz = tsxz;
	}

	/**
 * @描述 ：TODO描述下当前构造方法
 */
public ExtendGroup() {
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
	 * @return the extendGroupElementList
	 */
	public List<ExtendGroupElement> getExtendGroupElementList() {
		return extendGroupElementList;
	}

	/**
	 * @param extendGroupElementList要设置的 extendGroupElementList
	 */
	public void setExtendGroupElementList(
			List<ExtendGroupElement> extendGroupElementList) {
		this.extendGroupElementList = extendGroupElementList;
	}

	/**
	 * @return the tsxz
	 */
	public String getTsxz() {
		return tsxz;
	}

	/**
	 * @param tsxz要设置的 tsxz
	 */
	public void setTsxz(String tsxz) {
		this.tsxz = tsxz;
	}

}
