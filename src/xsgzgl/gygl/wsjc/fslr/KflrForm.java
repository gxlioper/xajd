/**
 * @部门:学工产品事业部
 * @日期：2017-6-1 下午03:34:50 
 */  
package xsgzgl.gygl.wsjc.fslr;

import org.apache.struts.action.ActionForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-6-1 下午03:34:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KflrForm extends ActionForm{
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -8850283607512316137L;
	private String jcrcid;
	private String lddm;
	private String qsh;
	private String kfdh;
	private String kf;
	
	private String kfArr[];
	
	private String delArr[];
	/**
	 * @return the jcrcid
	 */
	public String getJcrcid() {
		return jcrcid;
	}
	/**
	 * @param jcrcid要设置的 jcrcid
	 */
	public void setJcrcid(String jcrcid) {
		this.jcrcid = jcrcid;
	}
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qsh要设置的 qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @return the kfdh
	 */
	public String getKfdh() {
		return kfdh;
	}
	/**
	 * @param kfdh要设置的 kfdh
	 */
	public void setKfdh(String kfdh) {
		this.kfdh = kfdh;
	}
	/**
	 * @return the kf
	 */
	public String getKf() {
		return kf;
	}
	/**
	 * @param kf要设置的 kf
	 */
	public void setKf(String kf) {
		this.kf = kf;
	}
	/**
	 * @return the kfArr
	 */
	public String[] getKfArr() {
		return kfArr;
	}
	/**
	 * @param kfArr要设置的 kfArr
	 */
	public void setKfArr(String[] kfArr) {
		this.kfArr = kfArr;
	}
	/**
	 * @return the delArr
	 */
	public String[] getDelArr() {
		return delArr;
	}
	/**
	 * @param delArr要设置的 delArr
	 */
	public void setDelArr(String[] delArr) {
		this.delArr = delArr;
	}
	
	
}
