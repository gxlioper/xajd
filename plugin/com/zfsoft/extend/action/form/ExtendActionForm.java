/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-4 ����09:07:37 
 */  
package com.zfsoft.extend.action.form;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-4 ����09:07:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExtendActionForm extends ActionForm {


	private static final long serialVersionUID = 5245899687315237346L;
	
	
	/*����ģ�����*/
	private String extendModuleID;

	/*����ID*/
	private String dataId;

	/*�������ͣ�1����ʽ�����ݣ�2��ʱ������*/
	private String dataType;
	
	//ѧ��
	private String xh;
	
	//ְ����
	private String zgh;
	
	/*ѧ��������id*/
	private String bdpzid;
	
	/*�û��ύ������*/
	private String data;
	
	/*������������*/
	private String lx;
	
	/**
	 * @return the extendModuleID
	 */
	public String getExtendModuleID() {
		return extendModuleID;
	}


	/**
	 * @param extendModuleIDҪ���õ� extendModuleID
	 */
	public void setExtendModuleID(String extendModuleID) {
		this.extendModuleID = extendModuleID;
	}



	/**
	 * @return the dataId
	 */
	public String getDataId() {
		return dataId;
	}


	/**
	 * @param dataIdҪ���õ� dataId
	 */
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}


	/**
	 * @return the bdpzid
	 */
	public String getBdpzid() {
		return bdpzid;
	}


	/**
	 * @param bdpzidҪ���õ� bdpzid
	 */
	public void setBdpzid(String bdpzid) {
		this.bdpzid = bdpzid;
	}


	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}


	/**
	 * @param dataҪ���õ� data
	 */
	public void setData(String data) {
		this.data = data;
	}


	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}


	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}


	/**
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}


	/**
	 * @param zghҪ���õ� zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}


	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}


	/**
	 * @param dataTypeҪ���õ� dataType
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
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
	
	
}
