/**
 * @部门:学工产品事业部
 * @日期：2017年5月17日 下午4:48:06 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.sqsh;

import java.util.Set;
import java.util.TreeSet;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优模块
 * @类功能描述: 学生成绩汇总：学生课程Model
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月17日 下午4:48:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XskcModel {
	
	private String xqmc;
//	private Set<String> kcSet = new LinkedHashSet<String>();
	private Set<String> kcSet = new TreeSet<String>();
//	private Set<String> kcSet = new TreeSet<String>(com.ibm.icu.text.Collator.getInstance(com.ibm.icu.util.ULocale.SIMPLIFIED_CHINESE));
	
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the kcSet
	 */
	public Set<String> getKcSet() {
		return kcSet;
	}
	/**
	 * @param kcSet要设置的 kcSet
	 */
	public void setKcSet(Set<String> kcSet) {
		this.kcSet = kcSet;
	}
	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param xqmc
	 */
	public XskcModel(String xqmc) {
		super();
		this.xqmc = xqmc;
	}
	
	
}
