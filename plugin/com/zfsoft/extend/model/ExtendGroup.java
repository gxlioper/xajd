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

public class ExtendGroup implements Serializable {

	private static final long serialVersionUID = 1L;

    public static final String SFSH_Y = "1";
	
	public static final String SFSH_N = "0";
	
	public static final String LX_NORMAL = "normal";
	
	public static final String LX_LIST = "list";
	
	////////////////////////FIELDS///////////////////
	private String id;      //����
	
	private String mc;      //������
	
	private String lx;      //�����ͣ�NORMAL,TABLE
	
	private String xssx;    //��ʾ˳��
	
	private String meid;     //����ģ��ID
	
	private String tsxz;     //������б�����ʹ�ø��ֶ����������������������û������
   ////////////////////////FIELDS///////////////////

	private List<ExtendGroupElement> extendGroupElementList;
	
	
	
	
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
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
 * @���� ��TODO�����µ�ǰ���췽��
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
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}

	/**
	 * @param lxҪ���õ� lx
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
	 * @param meidҪ���õ� meid
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
	 * @param xssxҪ���õ� xssx
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
	 * @param extendGroupElementListҪ���õ� extendGroupElementList
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
	 * @param tsxzҪ���õ� tsxz
	 */
	public void setTsxz(String tsxz) {
		this.tsxz = tsxz;
	}

}
