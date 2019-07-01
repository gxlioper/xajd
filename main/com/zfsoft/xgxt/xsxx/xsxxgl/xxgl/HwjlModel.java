/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

import java.io.Serializable;

public class HwjlModel implements Serializable{
	private static final long serialVersionUID = 1L;

	private String jlid; //经历id
	
	private String xh; //学号
	
	private String qssj;//起始日期
	
	private String jzsj; // 截止时间
	
	private String mdd;//目的地
	
	private String sy;//事由

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @return		: the jlid
	 */
	public String getJlid() {
		return jlid;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @param 		：jlid the jlid to set
	 */
	public void setJlid(String jlid) {
		this.jlid = jlid;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @return		: the qssj
	 */
	public String getQssj() {
		return qssj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @param 		：qssj the qssj to set
	 */
	public void setQssj(String qssj) {
		this.qssj = qssj;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @return		: the jzsj
	 */
	public String getJzsj() {
		return jzsj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @param 		：jzsj the jzsj to set
	 */
	public void setJzsj(String jzsj) {
		this.jzsj = jzsj;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @return		: the mdd
	 */
	public String getMdd() {
		return mdd;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @param 		：mdd the mdd to set
	 */
	public void setMdd(String mdd) {
		this.mdd = mdd;
	}

	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @return		: the sy
	 */
	public String getSy() {
		return sy;
	}

	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-3-8 上午09:15:36 
	 * @param 		：sy the sy to set
	 */
	public void setSy(String sy) {
		this.sy = sy;
	}
	
	
}
