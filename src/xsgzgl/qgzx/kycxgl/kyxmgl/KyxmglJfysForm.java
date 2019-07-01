/**
 * @部门:学工产品事业部
 * @日期：2015-11-30 上午11:38:01 
 */  
package  xsgzgl.qgzx.kycxgl.kyxmgl;


import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-11-30 上午11:38:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KyxmglJfysForm extends ActionForm{
	
	private String xmid;
	private String zclb;//项目编号
	private String ysje;//预算金额
	private String zyyt;//主要用途
	/**
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmid要设置的 xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the zclb
	 */
	public String getZclb() {
		return zclb;
	}
	/**
	 * @param zclb要设置的 zclb
	 */
	public void setZclb(String zclb) {
		this.zclb = zclb;
	}
	/**
	 * @return the ysje
	 */
	public String getYsje() {
		return ysje;
	}
	/**
	 * @param ysje要设置的 ysje
	 */
	public void setYsje(String ysje) {
		this.ysje = ysje;
	}
	/**
	 * @return the zyyt
	 */
	public String getZyyt() {
		return zyyt;
	}
	/**
	 * @param zyyt要设置的 zyyt
	 */
	public void setZyyt(String zyyt) {
		this.zyyt = zyyt;
	}
	
	
	
	
	
	

}
