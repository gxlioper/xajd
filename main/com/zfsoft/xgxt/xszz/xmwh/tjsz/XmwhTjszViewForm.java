/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.tjsz;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ŀά��-��������,���з�װ��ҳ��չʾ
 * @�๦������:
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����06:33:00O
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhTjszViewForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private List<HashMap<String, String>> tjszList = null;// ������ֵ

	private List<HashMap<String, String>> tjList = null;// ��������
	private List<HashMap<String, String>> gxList = null;// ��ϵ����
	private List<HashMap<String, String>> tjgxList = null;// ������ϵ����

	private List<HashMap<String, String>> xnList = null;
	private List<HashMap<String, String>> xqList = null;
	private List<HashMap<String, String>> bjdlList = null;

	private List<HashMap<String, String>> sfqyList = null;
	private List<HashMap<String, String>> zhcpTjxmList = null;//�۲�������Ŀ

	public XmwhTjszViewForm() {
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

	public List<HashMap<String, String>> getBjdlList() {
		return bjdlList;
	}

	public void setBjdlList(List<HashMap<String, String>> bjdlList) {
		this.bjdlList = bjdlList;
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
