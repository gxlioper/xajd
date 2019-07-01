/**
 * @部门:学工产品事业部
 * @日期：2014-12-3 下午01:53:07
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 获奖情况
 * @作者：江水才[工号：1150]
 * @日期：2014-12-3 下午01:53:07
 * @版本： V1.0
 * @修改记录:
 */
public class HjqkNewModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String hjid;// 主键id
	private String xh;// 学号
	private String hjsj;// 获奖时间
	private String fjdw;// 发奖单位
	private String hjmc;// 获奖名称
	private String hjje;// 获奖金额  浙江万里个性化字段
	private String bz;// 备注

	/**
	 * @描述 ：
	 */
	public HjqkNewModel() {
		super();
	}

	public String getHjid() {
		return hjid;
	}

	public void setHjid(String hjid) {
		this.hjid = hjid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getHjsj() {
		return hjsj;
	}

	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}

	public String getFjdw() {
		return fjdw;
	}

	public void setFjdw(String fjdw) {
		this.fjdw = fjdw;
	}

	public String getHjmc() {
		return hjmc;
	}

	public void setHjmc(String hjmc) {
		this.hjmc = hjmc;
	}

	public String getHjje() {
		return hjje;
	}

	public void setHjje(String hjje) {
		this.hjje = hjje;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
