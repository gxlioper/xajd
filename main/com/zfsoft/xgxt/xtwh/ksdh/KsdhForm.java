/**
 * @部门:学工产品事业部
 * @日期：2015-10-8 上午09:28:12 
 */
package com.zfsoft.xgxt.xtwh.ksdh;

import org.apache.struts.action.ActionForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： yxy[工号:1206]
 * @时间： 2015-10-8 上午09:28:12
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KsdhForm extends ActionForm {
	private String id;
	private String yhm;// 用户名（XG_XTWH_KSDHB）
	private String cdid;// 功能模块代码表，关联表 XG_XTWH_DHTP（XG_XTWH_KSDHB）
	private String czsj;// 操作时间（XG_XTWH_KSDHB）
	private String yhlx;// 用户类型（XG_XTWH_KSDHB）
	private String gnmktblj;// 功能模块对应图片路径（xg_xtwh_dhtp）
	private String mkfldm;// 模块分类代码（XG_DHFLDMB代码表，XG_XTWH_DHTP）
	private String cshdhmk;// 初始化导航模块（XG_XTWH_DHTP，当快速导航表中该用户无数据时取cshdhmk为1的默认值）
	private String xssx;//显示顺序
	private String xxqx;//学校权限控制，默认为public,非空字段（很重要，为了 防止止有个性化需求）
	private String[] gnmkms;//gnmkdm数组
    private String[] xssxs;//xssx数组
    private String gnmkmc;//功能模块名称
	/**
	 * @return the cdid
	 */
	public String getCdid() {
		return cdid;
	}

	/**
	 * @param cdid要设置的 cdid
	 */
	public void setCdid(String cdid) {
		this.cdid = cdid;
	}

	/**
	 * @return the gnmktblj
	 */
	public String getGnmktblj() {
		return gnmktblj;
	}

	/**
	 * @param gnmktblj要设置的 gnmktblj
	 */
	public void setGnmktblj(String gnmktblj) {
		this.gnmktblj = gnmktblj;
	}

	/**
	 * @return the mkfldm
	 */
	public String getMkfldm() {
		return mkfldm;
	}

	/**
	 * @param mkfldm要设置的 mkfldm
	 */
	public void setMkfldm(String mkfldm) {
		this.mkfldm = mkfldm;
	}

	/**
	 * @return the cshdhmk
	 */
	public String getCshdhmk() {
		return cshdhmk;
	}

	/**
	 * @param cshdhmk要设置的 cshdhmk
	 */
	public void setCshdhmk(String cshdhmk) {
		this.cshdhmk = cshdhmk;
	}

	/**
	 * @return the xxqx
	 */
	public String getXxqx() {
		return xxqx;
	}

	/**
	 * @param xxqx要设置的 xxqx
	 */
	public void setXxqx(String xxqx) {
		this.xxqx = xxqx;
	}

	/**
	 * @return the gnmkmc
	 */
	public String getGnmkmc() {
		return gnmkmc;
	}

	/**
	 * @param gnmkmc要设置的 gnmkmc
	 */
	public void setGnmkmc(String gnmkmc) {
		this.gnmkmc = gnmkmc;
	}

	/**
	 * @return the gnmkmbs
	 */
	public String[] getGnmkms() {
		return gnmkms;
	}

	/**
	 * @param gnmkmbs要设置的 gnmkmbs
	 */
	public void setGnmkms(String[] gnmkms) {
		this.gnmkms = gnmkms;
	}

	/**
	 * @return the xssxs
	 */
	public String[] getXssxs() {
		return xssxs;
	}

	/**
	 * @param xssxs要设置的 xssxs
	 */
	public void setXssxs(String[] xssxs) {
		this.xssxs = xssxs;
	}

	/**
	 * @return the xssx
	 */
	public String getXssx() {
		return xssx;
	}

	/**
	 * @param xssx要设置的 xssx
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	private String type;// 后台判断标志


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type要设置的
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id要设置的
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the yhm
	 */
	public String getYhm() {
		return yhm;
	}

	/**
	 * @param yhm要设置的
	 *            yhm
	 */
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}



	/**
	 * @return the czsj
	 */
	public String getCzsj() {
		return czsj;
	}

	/**
	 * @param czsj要设置的
	 *            czsj
	 */
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}

	/**
	 * @return the yhlx
	 */
	public String getYhlx() {
		return yhlx;
	}

	/**
	 * @param yhlx要设置的
	 *            yhlx
	 */
	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}
}
