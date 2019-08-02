/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xyfd.cssz;

import org.apache.struts.action.ActionForm;

import java.util.List;

/**
 * @className	： CsszForm
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-15 下午05:49:12
 * @version 	V1.0 
 */

public class CsszForm extends ActionForm{
	
	private static final long serialVersionUID = -7412574192963714092L;
	private String id;
	private String lx;
	private String splc;
	
	private String[] pbs;
	
	private List<String[]> paramList;
	
	private String pb;//朋辈志愿者
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-15 下午05:59:38 
	 * @return		: the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-15 下午05:59:38 
	 * @param 		：id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-15 下午05:59:38 
	 * @return		: the lx
	 */
	public String getLx() {
		return lx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-15 下午05:59:38 
	 * @param 		：lx the lx to set
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-15 下午05:59:38 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-15 下午05:59:38 
	 * @param 		：splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String[] getPbs() {
		return pbs;
	}

	public void setPbs(String[] pbs) {
		this.pbs = pbs;
	}

	public String getPb() {
		return pb;
	}

	public void setPb(String pb) {
		this.pb = pb;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午03:12:12 
	 * @return		: the paramList
	 */
	public List<String[]> getParamList() {
		return paramList;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午03:12:12 
	 * @param 		：paramList the paramList to set
	 */
	public void setParamList(List<String[]> paramList) {
		this.paramList = paramList;
	}
	
	
	
}
