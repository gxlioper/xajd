/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-2 ����03:04:09 
 */  
package com.zfsoft.extend.model;

import java.io.Serializable;
import java.util.List;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-2 ����03:04:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExtendModuleElement  implements Serializable {

	private static final long serialVersionUID = 1L;

    public static final String SFSH_Y = "1";
	
	public static final String SFSH_N = "0";
	
	////////////////////////FIELDS///////////////////
	private String id;      //����
	
	private String mc;      //����
	
	private String sfsh;    //�Ƿ���Ҫ��ˣ�1����ˣ�0����Ҫ���
	 
	private String shlc;    //�������
	
	private String sm;		//˵��
	
	private String mid;     //����ģ��ID
	
	private String xssx;    //��ʾ˳��
   ////////////////////////FIELDS///////////////////

	private List<ExtendGroup> extendGroupList;
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
 * @���� ��TODO�����µ�ǰ���췽��
 * @param id
 * @param mc
 * @param sfsh
 * @param shlc
 * @param sm
 * @param mid
 * @param xssx
 */
public ExtendModuleElement(String id, String mc, String sfsh, String shlc,
		String sm, String mid, String xssx) {
	super();
	this.id = id;
	this.mc = mc;
	this.sfsh = sfsh;
	this.shlc = shlc;
	this.sm = sm;
	this.mid = mid;
	this.xssx = xssx;
}

	/**
 * @���� ��TODO�����µ�ǰ���췽��
 */
public ExtendModuleElement() {
	super();
}

	/**
	 * @param idҪ���õ� id
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
	 * @param mcҪ���õ� mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}

	/**
	 * @return the sfsh
	 */
	public String getSfsh() {
		return sfsh;
	}

	/**
	 * @param sfshҪ���õ� sfsh
	 */
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}

	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}

	/**
	 * @param shlcҪ���õ� shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}

	/**
	 * @return the sm
	 */
	public String getSm() {
		return sm;
	}

	/**
	 * @param smҪ���õ� sm
	 */
	public void setSm(String sm) {
		this.sm = sm;
	}

	/**
	 * @return the mid
	 */
	public String getMid() {
		return mid;
	}

	/**
	 * @param midҪ���õ� mid
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}

	/**
	 * @return the xssx
	 */
	public String getXssx() {
		return xssx;
	}

	/**
	 * @param xssxҪ���õ� xssx
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	/**
	 * @return the extendGroupList
	 */
	public List<ExtendGroup> getExtendGroupList() {
		return extendGroupList;
	}

	/**
	 * @param extendGroupListҪ���õ� extendGroupList
	 */
	public void setExtendGroupList(List<ExtendGroup> extendGroupList) {
		this.extendGroupList = extendGroupList;
	}
}
