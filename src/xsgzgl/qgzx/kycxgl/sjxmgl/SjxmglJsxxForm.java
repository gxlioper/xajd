/**
 * @部门:学工产品事业部
 * @日期：2015-11-30 上午11:38:01 
 */  
package  xsgzgl.qgzx.kycxgl.sjxmgl;


import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 实践管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-11-30 上午11:38:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SjxmglJsxxForm extends ActionForm{
	
	private String zgh;
	private String zc;//职称
	private String yjfx;//研究方向
	/**
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	/**
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}
	/**
	 * @param zc要设置的 zc
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}
	/**
	 * @return the yjfx
	 */
	public String getYjfx() {
		return yjfx;
	}
	/**
	 * @param yjfx要设置的 yjfx
	 */
	public void setYjfx(String yjfx) {
		this.yjfx = yjfx;
	}
	
}
