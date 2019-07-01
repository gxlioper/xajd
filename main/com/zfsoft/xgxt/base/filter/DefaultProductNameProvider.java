/**
 * @部门:学工产品事业部
 * @日期：2016-8-16 下午08:05:52 
 */  
package com.zfsoft.xgxt.base.filter;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.zfsoft.license.WhoAmI;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 实现com.zfsoft.license.WhoAmI接口，提供系统名称用 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-8-16 下午08:05:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DefaultProductNameProvider implements WhoAmI {
	/**
	 * 系统名称
	 */
	private String productName;
	public static final String DEFAULT_PRODUCT_NAME = "学生工作管理系统";
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
