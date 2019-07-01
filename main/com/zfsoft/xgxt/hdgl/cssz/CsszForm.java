/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.cssz;

import java.util.List;

import org.apache.struts.action.ActionForm;

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
	
	private String[] bls;
	private String[] jss;
	private String[] dss;
	
	private List<String[]> paramList;
	
	private String bl;
	private String js;
	private String ds;
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
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:22:03 
	 * @return		: the bl
	 */
	public String getBl() {
		return bl;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:22:03 
	 * @param 		：bl the bl to set
	 */
	public void setBl(String bl) {
		this.bl = bl;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:22:03 
	 * @return		: the js
	 */
	public String getJs() {
		return js;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:22:03 
	 * @param 		：js the js to set
	 */
	public void setJs(String js) {
		this.js = js;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:22:03 
	 * @return		: the ds
	 */
	public String getDs() {
		return ds;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:22:03 
	 * @param 		：ds the ds to set
	 */
	public void setDs(String ds) {
		this.ds = ds;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:49:46 
	 * @return		: the bls
	 */
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:56:39 
	 * @return		: the bls
	 */
	public String[] getBls() {
		return bls;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:56:39 
	 * @param 		：bls the bls to set
	 */
	public void setBls(String[] bls) {
		this.bls = bls;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:56:39 
	 * @return		: the jss
	 */
	public String[] getJss() {
		return jss;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:56:39 
	 * @param 		：jss the jss to set
	 */
	public void setJss(String[] jss) {
		this.jss = jss;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:56:39 
	 * @return		: the dss
	 */
	public String[] getDss() {
		return dss;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-16 下午02:56:39 
	 * @param 		：dss the dss to set
	 */
	public void setDss(String[] dss) {
		this.dss = dss;
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
