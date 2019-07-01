/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:07:12 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.xsxx.fbgl.Config;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:07:12
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglGzpzTjForm  extends ActionForm {
	private String pzgzid;// varchar2(100) n 配置规则id
	private String pzgzmc;// varchar2(100) n 配置规则名称
	private String qyzt="0";// varchar2(1) n 启用状态
	private String gzdm;// varchar2(20) n 规则代码
	private String gxsj;// varchar2(40) y 更新时间
	private String sfnz;//是否内置
	private String syzt;//使用状态
	//扩展自动
	private String qyztmc;//
	private String gzmc;//规则名称
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	/**
	 * @return the pzgzid
	 */
	public String getPzgzid() {
		return pzgzid;
	}

	/**
	 * @param pzgzid要设置的
	 *            pzgzid
	 */
	public void setPzgzid(String pzgzid) {
		this.pzgzid = pzgzid;
	}

	/**
	 * @return the pzgzmc
	 */
	public String getPzgzmc() {
		return pzgzmc;
	}

	/**
	 * @param pzgzmc要设置的
	 *            pzgzmc
	 */
	public void setPzgzmc(String pzgzmc) {
		this.pzgzmc = pzgzmc;
	}

	/**
	 * @return the qyzt
	 */
	public String getQyzt() {
		return qyzt;
	}

	/**
	 * @param qyzt要设置的
	 *            qyzt
	 */
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
	}

	/**
	 * @return the gzdm
	 */
	public String getGzdm() {
		return gzdm;
	}

	/**
	 * @param gzdm要设置的
	 *            gzdm
	 */
	public void setGzdm(String gzdm) {
		this.gzdm = gzdm;
	}

	/**
	 * @return the gxsj
	 */
	public String getGxsj() {
		return gxsj;
	}

	/**
	 * @param gxsj要设置的
	 *            gxsj
	 */
	public void setGxsj(String gxsj) {
		this.gxsj = gxsj;
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
	 * @return the gzmc
	 */
	public String getGzmc() {
		return gzmc;
	}

	/**
	 * @param gzmc要设置的 gzmc
	 */
	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
	}

	/**
	 * @return the qyztmc
	 */
	public String getQyztmc() {
		if(StringUtils.isNull(qyztmc)){
			if(Config._QYZT_BQY.equals(this.qyzt)){
				qyztmc="停用";
			}else{
				qyztmc="启用";
			}
		}
		return qyztmc;
	}

	/**
	 * @param qyztmc要设置的 qyztmc
	 */
	public void setQyztmc(String qyztmc) {
		this.qyztmc = qyztmc;
	}

	/**
	 * @return the sfnz
	 */
	public String getSfnz() {
		return sfnz;
	}

	/**
	 * @param sfnz要设置的 sfnz
	 */
	public void setSfnz(String sfnz) {
		this.sfnz = sfnz;
	}

	/**
	 * @return the syzt
	 */
	public String getSyzt() {
		return syzt;
	}

	/**
	 * @param syzt要设置的 syzt
	 */
	public void setSyzt(String syzt) {
		this.syzt = syzt;
	}
}
