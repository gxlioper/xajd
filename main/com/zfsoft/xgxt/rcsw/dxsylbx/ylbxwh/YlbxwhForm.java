/**
 * 学工产品事业部  
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxwh;

import com.zfsoft.xgxt.rcsw.dxsylbx.comm.DxsylbxForm;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 医疗保险维护管理模块
 * @类功能描述: TODO(医疗保险维护) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-6 上午10:44:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxwhForm extends DxsylbxForm{
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = -7261749964978412524L;
	
	private String type;
	private String czqebzdm;//财政全额补助代码
	private String czqebzmc;//财政全额补助名称
	private String cbzkdm;//参保状况代码
	private String cbzkmc;//参保状况名称		
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the czqebzdm
	 */
	public String getCzqebzdm() {
		return czqebzdm;
	}
	/**
	 * @param czqebzdm要设置的 czqebzdm
	 */
	public void setCzqebzdm(String czqebzdm) {
		this.czqebzdm = czqebzdm;
	}
	/**
	 * @return the czqebzmc
	 */
	public String getCzqebzmc() {
		return czqebzmc;
	}
	/**
	 * @param czqebzmc要设置的 czqebzmc
	 */
	public void setCzqebzmc(String czqebzmc) {
		this.czqebzmc = czqebzmc;
	}
	/**
	 * @return the cbzkdm
	 */
	public String getCbzkdm() {
		return cbzkdm;
	}
	/**
	 * @param cbzkdm要设置的 cbzkdm
	 */
	public void setCbzkdm(String cbzkdm) {
		this.cbzkdm = cbzkdm;
	}
	/**
	 * @return the cbzkmc
	 */
	public String getCbzkmc() {
		return cbzkmc;
	}
	/**
	 * @param cbzkmc要设置的 cbzkmc
	 */
	public void setCbzkmc(String cbzkmc) {
		this.cbzkmc = cbzkmc;
	}

	
}
