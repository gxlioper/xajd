/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-4-19 ����11:10:46 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-��Ŀ����-��������
 * @�๦������: ���з�װ��ҳ��չʾ
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-19 ����11:10:46 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjszViewForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private List<HashMap<String, String>> tjszList = null;// ������ֵ

	private List<HashMap<String, String>> tjList = null;// ��������
	private List<HashMap<String, String>> gxList = null;// ��ϵ����
	private List<HashMap<String, String>> tjgxList = null;// ������ϵ����

	private List<HashMap<String, String>> xnList = null;
	private List<HashMap<String, String>> xqList = null;
	private List<HashMap<String, String>> yyfwList = null;

	private List<HashMap<String, String>> sfqyList = null;
	private List<HashMap<String, String>> zhcpTjxmList = null;//�۲�������Ŀ

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
	 * @param tjszListҪ���õ� tjszList
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
	 * @param tjListҪ���õ� tjList
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
	 * @param gxListҪ���õ� gxList
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
	 * @param tjgxListҪ���õ� tjgxList
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
	 * @param xnListҪ���õ� xnList
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
	 * @param xqListҪ���õ� xqList
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
	 * @param yyfwListҪ���õ� yyfwList
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
	 * @param sfqyListҪ���õ� sfqyList
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
	 * @param zhcpTjxmListҪ���õ� zhcpTjxmList
	 */
	public void setZhcpTjxmList(List<HashMap<String, String>> zhcpTjxmList) {
		this.zhcpTjxmList = zhcpTjxmList;
	}
}
