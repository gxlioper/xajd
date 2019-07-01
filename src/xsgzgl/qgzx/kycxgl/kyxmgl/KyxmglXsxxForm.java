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

public class KyxmglXsxxForm extends ActionForm{
	
	private String xh;
	private String xmfg;//项目分工
	private String sfsfs;//是否师范生
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the xmfg
	 */
	public String getXmfg() {
		return xmfg;
	}
	/**
	 * @param xmfg要设置的 xmfg
	 */
	public void setXmfg(String xmfg) {
		this.xmfg = xmfg;
	}
	/**
	 * @return the sfsfs
	 */
	public String getSfsfs() {
		return sfsfs;
	}
	/**
	 * @param sfsfs要设置的 sfsfs
	 */
	public void setSfsfs(String sfsfs) {
		this.sfsfs = sfsfs;
	}
	
	
	
	
	
	
	

}
