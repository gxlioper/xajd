/**
 * @部门:学工产品事业部
 * @日期：2016-12-6 下午03:18:51 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.jcjy;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-12-6 下午03:18:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcjyModel extends ActionForm {
	private static final long serialVersionUID = 1966791940187986544L;
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private String type;//类型
	private SearchModel searchModel = new SearchModel();
	
	private String juid;        //id
	private String xh;          //学号
	private String dclb;	    //代偿类别
	private String bysj;		//毕业时间
	private String jrlxfs;		//家人联系方式
	private String jydwmc;		//就业单位名称
	private String jydwdz;		//就业单位详细地址
	private String sfwxzfsfz;	//是否为县政府所在地
	private String hylb;		//行业类别
	private String jydwlxdh;	//就业单位人事部门联系电话
	private String qdfwnx;		//已签订服务年限
	private String yjnxf;		//应缴纳学费金额(元)
	private String sjjnxf;		//实际缴纳学费金额(元)
	private String dkje;		//贷款本金金额(元)
	private String yh;			//经办银行名称
	private String hth;			//贷款合同号
	private String dkkssj;		//贷款开始时间
	private String dkjssj;		//贷款结束时间
	private String sqbcje;		//申请补偿金额(元)
	private String dksfwqch;	//贷款是否已完全偿还
	private String dicdc;		//第一次贷款代偿本金/学费代偿金
	private String dicdcsj;		//第一次时间
	private String decdc;		//第二次贷款代偿本金/学费代偿金
	private String decdcsj;		//第二次时间
	private String dscdc;		//第三次贷款代偿本金/学费代偿金
	private String dscdcsj;		//第三次时间
	private String pzbcdcje;	//批准补偿代偿金额(元)
	private String bcsqbcdcje;	//本次申请补偿代偿金额
	private String sfzzzg;		//是否在职在岗
	private String lzsfzc;		//离职离岗是否为正常
	private String clsfqq;		//材料是否齐全
	private String bz;			//备注
	private String ylzd1;		//预留字段1
	private String ylzd2;		//预留字段2
	private String ylzd3;		//预留字段3
	private String ylzd4;		//预留字段4
	private String ylzd5;		//预留字段5
	
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the juid
	 */
	public String getJuid() {
		return juid;
	}
	/**
	 * @param juid要设置的 juid
	 */
	public void setJuid(String juid) {
		this.juid = juid;
	}
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
	 * @return the dclb
	 */
	public String getDclb() {
		return dclb;
	}
	/**
	 * @param dclb要设置的 dclb
	 */
	public void setDclb(String dclb) {
		this.dclb = dclb;
	}
	/**
	 * @return the bysj
	 */
	public String getBysj() {
		return bysj;
	}
	/**
	 * @param bysj要设置的 bysj
	 */
	public void setBysj(String bysj) {
		this.bysj = bysj;
	}
	/**
	 * @return the jrlxfs
	 */
	public String getJrlxfs() {
		return jrlxfs;
	}
	/**
	 * @param jrlxfs要设置的 jrlxfs
	 */
	public void setJrlxfs(String jrlxfs) {
		this.jrlxfs = jrlxfs;
	}
	/**
	 * @return the jydwmc
	 */
	public String getJydwmc() {
		return jydwmc;
	}
	/**
	 * @param jydwmc要设置的 jydwmc
	 */
	public void setJydwmc(String jydwmc) {
		this.jydwmc = jydwmc;
	}
	/**
	 * @return the jydwdz
	 */
	public String getJydwdz() {
		return jydwdz;
	}
	/**
	 * @param jydwdz要设置的 jydwdz
	 */
	public void setJydwdz(String jydwdz) {
		this.jydwdz = jydwdz;
	}
	/**
	 * @return the sfwxzfsfz
	 */
	public String getSfwxzfsfz() {
		return sfwxzfsfz;
	}
	/**
	 * @param sfwxzfsfz要设置的 sfwxzfsfz
	 */
	public void setSfwxzfsfz(String sfwxzfsfz) {
		this.sfwxzfsfz = sfwxzfsfz;
	}
	/**
	 * @return the hylb
	 */
	public String getHylb() {
		return hylb;
	}
	/**
	 * @param hylb要设置的 hylb
	 */
	public void setHylb(String hylb) {
		this.hylb = hylb;
	}
	/**
	 * @return the jydwlxdh
	 */
	public String getJydwlxdh() {
		return jydwlxdh;
	}
	/**
	 * @param jydwlxdh要设置的 jydwlxdh
	 */
	public void setJydwlxdh(String jydwlxdh) {
		this.jydwlxdh = jydwlxdh;
	}
	/**
	 * @return the qdfwnx
	 */
	public String getQdfwnx() {
		return qdfwnx;
	}
	/**
	 * @param qdfwnx要设置的 qdfwnx
	 */
	public void setQdfwnx(String qdfwnx) {
		this.qdfwnx = qdfwnx;
	}
	/**
	 * @return the yjnxf
	 */
	public String getYjnxf() {
		return yjnxf;
	}
	/**
	 * @param yjnxf要设置的 yjnxf
	 */
	public void setYjnxf(String yjnxf) {
		this.yjnxf = yjnxf;
	}
	/**
	 * @return the sjjnxf
	 */
	public String getSjjnxf() {
		return sjjnxf;
	}
	/**
	 * @param sjjnxf要设置的 sjjnxf
	 */
	public void setSjjnxf(String sjjnxf) {
		this.sjjnxf = sjjnxf;
	}
	/**
	 * @return the dkje
	 */
	public String getDkje() {
		return dkje;
	}
	/**
	 * @param dkje要设置的 dkje
	 */
	public void setDkje(String dkje) {
		this.dkje = dkje;
	}
	/**
	 * @return the yh
	 */
	public String getYh() {
		return yh;
	}
	/**
	 * @param yh要设置的 yh
	 */
	public void setYh(String yh) {
		this.yh = yh;
	}
	/**
	 * @return the hth
	 */
	public String getHth() {
		return hth;
	}
	/**
	 * @param hth要设置的 hth
	 */
	public void setHth(String hth) {
		this.hth = hth;
	}
	/**
	 * @return the dkkssj
	 */
	public String getDkkssj() {
		return dkkssj;
	}
	/**
	 * @param dkkssj要设置的 dkkssj
	 */
	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}
	/**
	 * @return the dkjssj
	 */
	public String getDkjssj() {
		return dkjssj;
	}
	/**
	 * @param dkjssj要设置的 dkjssj
	 */
	public void setDkjssj(String dkjssj) {
		this.dkjssj = dkjssj;
	}
	/**
	 * @return the sqbcje
	 */
	public String getSqbcje() {
		return sqbcje;
	}
	/**
	 * @param sqbcje要设置的 sqbcje
	 */
	public void setSqbcje(String sqbcje) {
		this.sqbcje = sqbcje;
	}
	/**
	 * @return the dksfwqch
	 */
	public String getDksfwqch() {
		return dksfwqch;
	}
	/**
	 * @param dksfwqch要设置的 dksfwqch
	 */
	public void setDksfwqch(String dksfwqch) {
		this.dksfwqch = dksfwqch;
	}
	/**
	 * @return the dicdc
	 */
	public String getDicdc() {
		return dicdc;
	}
	/**
	 * @param dicdc要设置的 dicdc
	 */
	public void setDicdc(String dicdc) {
		this.dicdc = dicdc;
	}
	/**
	 * @return the dicdcsj
	 */
	public String getDicdcsj() {
		return dicdcsj;
	}
	/**
	 * @param dicdcsj要设置的 dicdcsj
	 */
	public void setDicdcsj(String dicdcsj) {
		this.dicdcsj = dicdcsj;
	}
	/**
	 * @return the decdc
	 */
	public String getDecdc() {
		return decdc;
	}
	/**
	 * @param decdc要设置的 decdc
	 */
	public void setDecdc(String decdc) {
		this.decdc = decdc;
	}
	/**
	 * @return the decdcsj
	 */
	public String getDecdcsj() {
		return decdcsj;
	}
	/**
	 * @param decdcsj要设置的 decdcsj
	 */
	public void setDecdcsj(String decdcsj) {
		this.decdcsj = decdcsj;
	}
	/**
	 * @return the dscdc
	 */
	public String getDscdc() {
		return dscdc;
	}
	/**
	 * @param dscdc要设置的 dscdc
	 */
	public void setDscdc(String dscdc) {
		this.dscdc = dscdc;
	}
	/**
	 * @return the dscdcsj
	 */
	public String getDscdcsj() {
		return dscdcsj;
	}
	/**
	 * @param dscdcsj要设置的 dscdcsj
	 */
	public void setDscdcsj(String dscdcsj) {
		this.dscdcsj = dscdcsj;
	}
	/**
	 * @return the pzbcdcje
	 */
	public String getPzbcdcje() {
		return pzbcdcje;
	}
	/**
	 * @param pzbcdcje要设置的 pzbcdcje
	 */
	public void setPzbcdcje(String pzbcdcje) {
		this.pzbcdcje = pzbcdcje;
	}
	/**
	 * @return the bcsqbcdcje
	 */
	public String getBcsqbcdcje() {
		return bcsqbcdcje;
	}
	/**
	 * @param bcsqbcdcje要设置的 bcsqbcdcje
	 */
	public void setBcsqbcdcje(String bcsqbcdcje) {
		this.bcsqbcdcje = bcsqbcdcje;
	}
	/**
	 * @return the sfzzzg
	 */
	public String getSfzzzg() {
		return sfzzzg;
	}
	/**
	 * @param sfzzzg要设置的 sfzzzg
	 */
	public void setSfzzzg(String sfzzzg) {
		this.sfzzzg = sfzzzg;
	}
	/**
	 * @return the lzsfzc
	 */
	public String getLzsfzc() {
		return lzsfzc;
	}
	/**
	 * @param lzsfzc要设置的 lzsfzc
	 */
	public void setLzsfzc(String lzsfzc) {
		this.lzsfzc = lzsfzc;
	}
	/**
	 * @return the clsfqq
	 */
	public String getClsfqq() {
		return clsfqq;
	}
	/**
	 * @param clsfqq要设置的 clsfqq
	 */
	public void setClsfqq(String clsfqq) {
		this.clsfqq = clsfqq;
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
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1要设置的 ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @param ylzd2要设置的 ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3要设置的 ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @param ylzd4要设置的 ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @param ylzd5要设置的 ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
}
