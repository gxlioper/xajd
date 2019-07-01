/**
 * @部门:学工产品事业部
 * @日期：2014-4-28 上午10:53:23 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpzsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-4-28 上午10:53:23
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JtpjSzForm extends ActionForm {
	private String jxid;// varchar2(32) n 'sys_guid()' 奖项id
	private String jxmc;// varchar2(50) y 奖项名称
	private String jxsm;// varchar2(1000) y 奖项说明
	private String jtpjdw;// varchar2(10) y 集体评奖单位(学院：xy;班级：bj;其他：qt)
	private String sqxn;// varchar2(10) y 申请学年
	private String sqxq;// varchar2(10) y 申请学期
	private String pdxn;// varchar2(10) y 评定学年
	private String pdxq;// varchar2(10) y 评定学期
	private String sfkfsq;// varchar2(1) y 是否开放申请(0：否；1：是)
	private String sqkfkssj;// varchar2(20) y 申请开放开始时间
	private String sqkfjssj;// varchar2(20) y 申请开放结束时间
	private String splcid;// varchar2(32) y 审核流程id
	private String ksqxslx;// varchar2(200) y 可申请学生类型( - 分隔)
	private String dybbid;// varchar2(32) y 对应报表id

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String sqxqmc;
	private String pdxqmc;
	private String sqzq;
	/**
	 * @return the jxid
	 */
	public String getJxid() {
		return jxid;
	}

	/**
	 * @param jxid要设置的
	 *            jxid
	 */
	public void setJxid(String jxid) {
		this.jxid = jxid;
	}

	/**
	 * @return the jxmc
	 */
	public String getJxmc() {
		return jxmc;
	}

	/**
	 * @param jxmc要设置的
	 *            jxmc
	 */
	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}

	/**
	 * @return the jxsm
	 */
	public String getJxsm() {
		return jxsm;
	}

	/**
	 * @param jxsm要设置的
	 *            jxsm
	 */
	public void setJxsm(String jxsm) {
		this.jxsm = jxsm;
	}

	/**
	 * @return the jtpjdw
	 */
	public String getJtpjdw() {
		return jtpjdw;
	}

	/**
	 * @param jtpjdw要设置的
	 *            jtpjdw
	 */
	public void setJtpjdw(String jtpjdw) {
		this.jtpjdw = jtpjdw;
	}

	/**
	 * @return the sqxn
	 */
	public String getSqxn() {
		return sqxn;
	}

	/**
	 * @param sqxn要设置的
	 *            sqxn
	 */
	public void setSqxn(String sqxn) {
		this.sqxn = sqxn;
	}

	/**
	 * @return the sqxq
	 */
	public String getSqxq() {
		return sqxq;
	}

	/**
	 * @param sqxq要设置的
	 *            sqxq
	 */
	public void setSqxq(String sqxq) {
		this.sqxq = sqxq;
	}

	/**
	 * @return the sfkfsq
	 */
	public String getSfkfsq() {
		return sfkfsq;
	}

	/**
	 * @param sfkfsq要设置的
	 *            sfkfsq
	 */
	public void setSfkfsq(String sfkfsq) {
		this.sfkfsq = sfkfsq;
	}

	/**
	 * @return the sqkfkssj
	 */
	public String getSqkfkssj() {
		return sqkfkssj;
	}

	/**
	 * @param sqkfkssj要设置的
	 *            sqkfkssj
	 */
	public void setSqkfkssj(String sqkfkssj) {
		this.sqkfkssj = sqkfkssj;
	}

	/**
	 * @return the sqkfjssj
	 */
	public String getSqkfjssj() {
		return sqkfjssj;
	}

	/**
	 * @param sqkfjssj要设置的
	 *            sqkfjssj
	 */
	public void setSqkfjssj(String sqkfjssj) {
		this.sqkfjssj = sqkfjssj;
	}

	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}

	/**
	 * @param splcid要设置的
	 *            splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}

	/**
	 * @return the ksqxslx
	 */
	public String getKsqxslx() {
		return ksqxslx;
	}

	/**
	 * @param ksqxslx要设置的
	 *            ksqxslx
	 */
	public void setKsqxslx(String ksqxslx) {
		this.ksqxslx = ksqxslx;
	}

	/**
	 * @return the dybbid
	 */
	public String getDybbid() {
		return dybbid;
	}

	/**
	 * @param dybbid要设置的
	 *            dybbid
	 */
	public void setDybbid(String dybbid) {
		this.dybbid = dybbid;
	}

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pages要设置的
	 *            pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}

	/**
	 * @param searchModel要设置的
	 *            searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

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
	 * @return the pdxn
	 */
	public String getPdxn() {
		return pdxn;
	}

	/**
	 * @param pdxn要设置的 pdxn
	 */
	public void setPdxn(String pdxn) {
		this.pdxn = pdxn;
	}

	/**
	 * @return the pdxq
	 */
	public String getPdxq() {
		return pdxq;
	}

	/**
	 * @param pdxq要设置的 pdxq
	 */
	public void setPdxq(String pdxq) {
		this.pdxq = pdxq;
	}

	/**
	 * @return the sqxqmc
	 */
	public String getSqxqmc() {
		return sqxqmc;
	}

	/**
	 * @param sqxqmc要设置的 sqxqmc
	 */
	public void setSqxqmc(String sqxqmc) {
		this.sqxqmc = sqxqmc;
	}

	/**
	 * @return the pdxqmc
	 */
	public String getPdxqmc() {
		return pdxqmc;
	}

	/**
	 * @param pdxqmc要设置的 pdxqmc
	 */
	public void setPdxqmc(String pdxqmc) {
		this.pdxqmc = pdxqmc;
	}

	/**
	 * @return the sqzq
	 */
	public String getSqzq() {
		return sqzq;
	}

	/**
	 * @param sqzq要设置的 sqzq
	 */
	public void setSqzq(String sqzq) {
		this.sqzq = sqzq;
	}

}
