/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:07:42
 */
package com.zfsoft.xgxt.xpjpy.xmsz.tjsz;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 项目维护-条件设置,进行封装，页面展示
 * @类功能描述:
 * @作者： ligl
 * @日期：2013-8-5 上午11:07:42
 * @版本： V1.0
 * @修改记录:
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

	public List<HashMap<String, String>> getTjszList() {
		return tjszList;
	}

	public void setTjszList(List<HashMap<String, String>> tjszList) {
		this.tjszList = tjszList;
	}

	public List<HashMap<String, String>> getTjList() {
		return tjList;
	}

	public void setTjList(List<HashMap<String, String>> tjList) {
		this.tjList = tjList;
	}

	public List<HashMap<String, String>> getGxList() {
		return gxList;
	}

	public void setGxList(List<HashMap<String, String>> gxList) {
		this.gxList = gxList;
	}

	public List<HashMap<String, String>> getTjgxList() {
		return tjgxList;
	}

	public void setTjgxList(List<HashMap<String, String>> tjgxList) {
		this.tjgxList = tjgxList;
	}

	public List<HashMap<String, String>> getXnList() {
		return xnList;
	}

	public void setXnList(List<HashMap<String, String>> xnList) {
		this.xnList = xnList;
	}

	public List<HashMap<String, String>> getXqList() {
		return xqList;
	}

	public void setXqList(List<HashMap<String, String>> xqList) {
		this.xqList = xqList;
	}

	public List<HashMap<String, String>> getYyfwList() {
		return yyfwList;
	}

	public void setYyfwList(List<HashMap<String, String>> yyfwList) {
		this.yyfwList = yyfwList;
	}

	public List<HashMap<String, String>> getSfqyList() {
		return sfqyList;
	}

	public void setSfqyList(List<HashMap<String, String>> sfqyList) {
		this.sfqyList = sfqyList;
	}

	public List<HashMap<String, String>> getZhcpTjxmList() {
		return zhcpTjxmList;
	}

	public void setZhcpTjxmList(List<HashMap<String, String>> zhcpTjxmList) {
		this.zhcpTjxmList = zhcpTjxmList;
	}

}
