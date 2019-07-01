/**
 * @部门:学工产品事业部
 * @日期：2014-8-19 下午02:20:33 
 */  
package com.zfsoft.xgxt.jjgl.jjxq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @类功能描述:家教需求
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-19 下午02:20:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjxqForm extends ActionForm {

	private static final long serialVersionUID = -3682166349200841439L;
    // 高级查询
    SearchModel searchModel = new SearchModel();

	private String xqid;
	private String sqr;
	private String znid;
	private String jjxkdm;
	private String jjsj;
	private String jjdd;
	private String kcbbys;
	private String jjlsyq;
	private String bz;
	private String shzt;
	private String jjzt;
	private String ztbz;
	private Pages pages = new Pages();
	
	private String znxm;
	private String znxb;
	private String znnj;
	private String jjnj;
	private String jjxk;
	
	
	private String sqid;
	private String zsc;
	private String zfy;
	private String jjxd;
	private String jzpj;

	private String splc;
	private String xh;

	private String ywid;	//业务id
	private String shsj;	//审核时间
	private String shr;	//审核人
	private String shyj;	//审核意见
	private String gwid;	//岗位id
	private String shztmc;	//审核状态名称
	private String shid;	//审核id
	private String thgw;	//岗位退回
	private String shjg;	//审核结果

	private String jjcz;	//家教操作
	private String jjczmc; //家教操作名称
	private String sqly;	//申请理由
	private String pcsj;	//派出时间
	private String pjjsqid;	//派家教申请id
	private String gbsj;	//关闭时间

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    /**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the zsc
	 */
	public String getZsc() {
		return zsc;
	}
	/**
	 * @param zsc要设置的 zsc
	 */
	public void setZsc(String zsc) {
		this.zsc = zsc;
	}
	/**
	 * @return the zfy
	 */
	public String getZfy() {
		return zfy;
	}
	/**
	 * @param zfy要设置的 zfy
	 */
	public void setZfy(String zfy) {
		this.zfy = zfy;
	}
	/**
	 * @return the jjxd
	 */
	public String getJjxd() {
		return jjxd;
	}
	/**
	 * @param jjxd要设置的 jjxd
	 */
	public void setJjxd(String jjxd) {
		this.jjxd = jjxd;
	}
	/**
	 * @return the jzpj
	 */
	public String getJzpj() {
		return jzpj;
	}
	/**
	 * @param jzpj要设置的 jzpj
	 */
	public void setJzpj(String jzpj) {
		this.jzpj = jzpj;
	}
	/**
	 * @return the znxm
	 */
	public String getZnxm() {
		return znxm;
	}
	/**
	 * @param znxm要设置的 znxm
	 */
	public void setZnxm(String znxm) {
		this.znxm = znxm;
	}
	/**
	 * @return the znxb
	 */
	public String getZnxb() {
		return znxb;
	}
	/**
	 * @param znxb要设置的 znxb
	 */
	public void setZnxb(String znxb) {
		this.znxb = znxb;
	}
	/**
	 * @return the znnj
	 */
	public String getZnnj() {
		return znnj;
	}
	/**
	 * @param znnj要设置的 znnj
	 */
	public void setZnnj(String znnj) {
		this.znnj = znnj;
	}
	/**
	 * @return the jjnjmc
	 */
	public String getJjnj() {
		return jjnj;
	}

	public void setJjnj(String jjnj) {
		this.jjnj = jjnj;
	}

	public String getJjxk() {
		return jjxk;
	}

	public void setJjxk(String jjxk) {
		this.jjxk = jjxk;
	}

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the xqid
	 */
	public String getXqid() {
		return xqid;
	}
	/**
	 * @param xqid要设置的 xqid
	 */
	public void setXqid(String xqid) {
		this.xqid = xqid;
	}
	/**
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * @param sqr要设置的 sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	/**
	 * @return the znid
	 */
	public String getZnid() {
		return znid;
	}
	/**
	 * @param znid要设置的 znid
	 */
	public void setZnid(String znid) {
		this.znid = znid;
	}
	/**
	 * @return the jjxkdm
	 */
	public String getJjxkdm() {
		return jjxkdm;
	}
	/**
	 * @param jjxkdm要设置的 jjxkdm
	 */
	public void setJjxkdm(String jjxkdm) {
		this.jjxkdm = jjxkdm;
	}
	/**
	 * @return the jjsj
	 */
	public String getJjsj() {
		return jjsj;
	}
	/**
	 * @param jjsj要设置的 jjsj
	 */
	public void setJjsj(String jjsj) {
		this.jjsj = jjsj;
	}
	/**
	 * @return the jjdd
	 */
	public String getJjdd() {
		return jjdd;
	}
	/**
	 * @param jjdd要设置的 jjdd
	 */
	public void setJjdd(String jjdd) {
		this.jjdd = jjdd;
	}
	/**
	 * @return the kcbbys
	 */
	public String getKcbbys() {
		return kcbbys;
	}
	/**
	 * @param kcbbys要设置的 kcbbys
	 */
	public void setKcbbys(String kcbbys) {
		this.kcbbys = kcbbys;
	}
	/**
	 * @return the jjlsyq
	 */
	public String getJjlsyq() {
		return jjlsyq;
	}
	/**
	 * @param jjlsyq要设置的 jjlsyq
	 */
	public void setJjlsyq(String jjlsyq) {
		this.jjlsyq = jjlsyq;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the jjzt
	 */
	public String getJjzt() {
		return jjzt;
	}
	/**
	 * @param jjzt要设置的 jjzt
	 */
	public void setJjzt(String jjzt) {
		this.jjzt = jjzt;
	}
	/**
	 * @return the ztbz
	 */
	public String getZtbz() {
		return ztbz;
	}
	/**
	 * @param ztbz要设置的 ztbz
	 */
	public void setZtbz(String ztbz) {
		this.ztbz = ztbz;
	}

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYwid() {
		return ywid;
	}

	public void setYwid(String ywid) {
		this.ywid = ywid;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getGwid() {
		return gwid;
	}

	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	public String getShztmc() {
		return shztmc;
	}

	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}

	public String getShid() {
		return shid;
	}

	public void setShid(String shid) {
		this.shid = shid;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getJjcz() {
		return jjcz;
	}

	public void setJjcz(String jjcz) {
		this.jjcz = jjcz;
	}

	public String getJjczmc() {
		return jjczmc;
	}

	public void setJjczmc(String jjczmc) {
		this.jjczmc = jjczmc;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getPcsj() {
		return pcsj;
	}

	public void setPcsj(String pcsj) {
		this.pcsj = pcsj;
	}

	public String getPjjsqid() {
		return pjjsqid;
	}

	public void setPjjsqid(String pjjsqid) {
		this.pjjsqid = pjjsqid;
	}

	public String getGbsj() {
		return gbsj;
	}

	public void setGbsj(String gbsj) {
		this.gbsj = gbsj;
	}
}
