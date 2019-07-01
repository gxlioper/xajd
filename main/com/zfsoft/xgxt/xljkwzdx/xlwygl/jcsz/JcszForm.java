/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-22 ����05:26:04 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������� 
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-22 ����05:26:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 6948305626296913637L;
	
	public static final String XXLX_BJ = "0";
	
	public static final String XXLX_GY = "1";
	
	public static final String XXLX_PS = "2";
	
	
	//�༶�ܱ��ճ���������ID
	private String bjzbrcSplcid;
	
	//��Ԣ�ܱ��ճ���������ID
	private String gyzbrcSplcid;
	
	//ƽʱ��Ϣ�ϱ���������ID
	private String psxxsbSplcid;

	public String selectSplc(String xxlx){
		if(StringUtils.isBlank(xxlx))
			return null;
		
		if(StringUtils.equals(XXLX_BJ, xxlx)){
			return bjzbrcSplcid;
		}else if(StringUtils.equals(XXLX_GY, xxlx)){
			return gyzbrcSplcid;
		}else if(StringUtils.equals(XXLX_PS, xxlx)){
			return psxxsbSplcid;
		}
		return null;
	}
	
	/**
	 * @return the bjzbrcSplcid
	 */
	public String getBjzbrcSplcid() {
		return bjzbrcSplcid;
	}

	/**
	 * @param bjzbrcSplcidҪ���õ� bjzbrcSplcid
	 */
	public void setBjzbrcSplcid(String bjzbrcSplcid) {
		this.bjzbrcSplcid = bjzbrcSplcid;
	}

	/**
	 * @return the gyzbrcSplcid
	 */
	public String getGyzbrcSplcid() {
		return gyzbrcSplcid;
	}

	/**
	 * @param gyzbrcSplcidҪ���õ� gyzbrcSplcid
	 */
	public void setGyzbrcSplcid(String gyzbrcSplcid) {
		this.gyzbrcSplcid = gyzbrcSplcid;
	}

	/**
	 * @return the psxxsbSplcid
	 */
	public String getPsxxsbSplcid() {
		return psxxsbSplcid;
	}

	/**
	 * @param psxxsbSplcidҪ���õ� psxxsbSplcid
	 */
	public void setPsxxsbSplcid(String psxxsbSplcid) {
		this.psxxsbSplcid = psxxsbSplcid;
	}
	
	

}
