/**
 * @部门:学工产品事业部
 * @日期：2017年4月11日 下午2:34:58 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.sqsh;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import xgxt.action.Base;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优
 * @类功能描述: 学生成绩实体类，用于学生成绩汇总导出的数据承载
 * @作者： xuwen[工号:1426]
 * @时间： 2017年4月11日 下午2:34:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XscjModel {
	private String xh;
	private String xm;
	private String pjcj;	//平均成绩
	private String pjjd;	//平均绩点
	private String pjcjpm;	//按平均成绩的排名（后来改为按平均绩点排名，属性名还是这个没有改）
	//成绩map，包含第一学期成绩和第二学期成绩
	//新需求，包含绩点，[0]成绩，[1]绩点
	private Map<String,Map<String,String[]>> cjMap = new LinkedHashMap<String,Map<String,String[]>>();
	
	
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param xh
	 * @param xm
	 */
	public XscjModel(String xh, String xm,String pjcj,String pjjd,String pjcjpm) {
		super();
		this.xh = xh;
		this.xm = xm;
		this.pjcj = pjcj;
		this.pjjd = pjjd;
		this.pjcjpm = pjcjpm;
		//这里直接写死2个学期了，可以改为从数据库取学期代码来创建
//		this.cjMap.put("01", new LinkedHashMap<String,String>());
//		this.cjMap.put("02", new LinkedHashMap<String,String>());
		
		for(HashMap<String,String> map:Base.getXqList()){
//			this.cjMap.put(map.get("xqdm"), new LinkedHashMap<String,String[]>());
//			this.cjMap.put(map.get("xqdm"), new TreeMap<String,String[]>(com.ibm.icu.text.Collator.getInstance(com.ibm.icu.util.ULocale.SIMPLIFIED_CHINESE)));
			this.cjMap.put(map.get("xqdm"), new TreeMap<String,String[]>());
		}
		
	}
	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public XscjModel() {
		super();
	}
	/**
	 * @return the cjMap
	 */
	public Map<String, Map<String, String[]>> getCjMap() {
		return cjMap;
	}
	/**
	 * @param cjMap要设置的 cjMap
	 */
	public void setCjMap(Map<String, Map<String, String[]>> cjMap) {
		this.cjMap = cjMap;
	}
	/**
	 * @return the pjcj
	 */
	public String getPjcj() {
		return pjcj;
	}
	/**
	 * @param pjcj要设置的 pjcj
	 */
	public void setPjcj(String pjcj) {
		this.pjcj = pjcj;
	}
	/**
	 * @return the pjjd
	 */
	public String getPjjd() {
		return pjjd;
	}
	/**
	 * @param pjjd要设置的 pjjd
	 */
	public void setPjjd(String pjjd) {
		this.pjjd = pjjd;
	}
	/**
	 * @return the pjcjpm
	 */
	public String getPjcjpm() {
		return pjcjpm;
	}
	/**
	 * @param pjcjpm要设置的 pjcjpm
	 */
	public void setPjcjpm(String pjcjpm) {
		this.pjcjpm = pjcjpm;
	}
	
	
}
