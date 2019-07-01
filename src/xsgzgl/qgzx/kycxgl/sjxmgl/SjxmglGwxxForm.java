/**
 * @部门:学工产品事业部
 * @日期：2015-11-30 上午11:38:01 
 */  
package  xsgzgl.qgzx.kycxgl.sjxmgl;


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

public class SjxmglGwxxForm extends ActionForm{
	
	private String xmid;
	private String gwlb;//
	private String gwgzzy;//工作摘要
	private String zcyrs;//总参与人数
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
	 * @return the gwlb
	 */
	public String getGwlb() {
		return gwlb;
	}
	/**
	 * @param gwlb要设置的 gwlb
	 */
	public void setGwlb(String gwlb) {
		this.gwlb = gwlb;
	}
	/**
	 * @return the gwgzzy
	 */
	public String getGwgzzy() {
		return gwgzzy;
	}
	/**
	 * @param gwgzzy要设置的 gwgzzy
	 */
	public void setGwgzzy(String gwgzzy) {
		this.gwgzzy = gwgzzy;
	}
	/**
	 * @return the zcyrs
	 */
	public String getZcyrs() {
		return zcyrs;
	}
	/**
	 * @param zcyrs要设置的 zcyrs
	 */
	public void setZcyrs(String zcyrs) {
		this.zcyrs = zcyrs;
	}
	
	
	

}
