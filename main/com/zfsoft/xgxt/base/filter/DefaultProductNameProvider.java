/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-16 ����08:05:52 
 */  
package com.zfsoft.xgxt.base.filter;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.zfsoft.license.WhoAmI;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ʵ��com.zfsoft.license.WhoAmI�ӿڣ��ṩϵͳ������ 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2016-8-16 ����08:05:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DefaultProductNameProvider implements WhoAmI {
	/**
	 * ϵͳ����
	 */
	private String productName;
	public static final String DEFAULT_PRODUCT_NAME = "ѧ����������ϵͳ";
	public String myNameIs() {
		String myName = DEFAULT_PRODUCT_NAME;
		if(productName != null){
			myName = productName;
		}
		try {
			return new Base64().encodeAsString(myName.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}


}
