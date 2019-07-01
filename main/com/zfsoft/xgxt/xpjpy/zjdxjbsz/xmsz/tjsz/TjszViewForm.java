/**
 * @部门:学工产品事业部
 * @日期：2017-4-19 上午11:10:46 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-项目设置-条件设置
 * @类功能描述: 进行封装，页面展示
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-4-19 上午11:10:46 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjszViewForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private List<HashMap<String, String>> tjszList = null;// 已设置值

	private List<HashMap<String, String>> tjList = null;// 条件代码
	private List<HashMap<String, String>> gxList = null;// 关系代码
	private List<HashMap<String, String>> tjgxList = null;// 条件关系代码

	private List<HashMap<String, String>> xnList = null;
	private List<HashMap<String, String>> xqList = null;
	private List<HashMap<String, String>> yyfwList = null;

	private List<HashMap<String, String>> sfqyList = null;
	private List<HashMap<String, String>> zhcpTjxmList = null;//综测条件项目

	public TjszViewForm() {
		super();
	}

	/**
	 * @return the tjszList
	 */
	public List<HashMap<String, String>> getTjszList() {
		return tjszList;
	}

	/**
	 * @param tjszList要设置的 tjszList
	 */
	public void setTjszList(List<HashMap<String, String>> tjszList) {
		this.tjszList = tjszList;
	}

	/**
	 * @return the tjList
	 */
	public List<HashMap<String, String>> getTjList() {
		return tjList;
	}

	/**
	 * @param tjList要设置的 tjList
	 */
	public void setTjList(List<HashMap<String, String>> tjList) {
		this.tjList = tjList;
	}

	/**
	 * @return the gxList
	 */
	public List<HashMap<String, String>> getGxList() {
		return gxList;
	}

	/**
	 * @param gxList要设置的 gxList
	 */
	public void setGxList(List<HashMap<String, String>> gxList) {
		this.gxList = gxList;
	}

	/**
	 * @return the tjgxList
	 */
	public List<HashMap<String, String>> getTjgxList() {
		return tjgxList;
	}

	/**
	 * @param tjgxList要设置的 tjgxList
	 */
	public void setTjgxList(List<HashMap<String, String>> tjgxList) {
		this.tjgxList = tjgxList;
	}

	/**
	 * @return the xnList
	 */
	public List<HashMap<String, String>> getXnList() {
		return xnList;
	}

	/**
	 * @param xnList要设置的 xnList
	 */
	public void setXnList(List<HashMap<String, String>> xnList) {
		this.xnList = xnList;
	}

	/**
	 * @return the xqList
	 */
	public List<HashMap<String, String>> getXqList() {
		return xqList;
	}

	/**
	 * @param xqList要设置的 xqList
	 */
	public void setXqList(List<HashMap<String, String>> xqList) {
		this.xqList = xqList;
	}

	/**
	 * @return the yyfwList
	 */
	public List<HashMap<String, String>> getYyfwList() {
		return yyfwList;
	}

	/**
	 * @param yyfwList要设置的 yyfwList
	 */
	public void setYyfwList(List<HashMap<String, String>> yyfwList) {
		this.yyfwList = yyfwList;
	}

	/**
	 * @return the sfqyList
	 */
	public List<HashMap<String, String>> getSfqyList() {
		return sfqyList;
	}

	/**
	 * @param sfqyList要设置的 sfqyList
	 */
	public void setSfqyList(List<HashMap<String, String>> sfqyList) {
		this.sfqyList = sfqyList;
	}

	/**
	 * @return the zhcpTjxmList
	 */
	public List<HashMap<String, String>> getZhcpTjxmList() {
		return zhcpTjxmList;
	}

	/**
	 * @param zhcpTjxmList要设置的 zhcpTjxmList
	 */
	public void setZhcpTjxmList(List<HashMap<String, String>> zhcpTjxmList) {
		this.zhcpTjxmList = zhcpTjxmList;
	}
}
