/**
 * @部门:学工产品事业部
 * @日期：2013-12-30 上午10:26:16 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务-假期留校
 * @类功能描述: 假期留校实体
 * @作者： 945
 * @时间： 2013-12-30 上午10:26:16
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JqlxModel extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	private String sqid;// 申请id
	private String xh;// 申请人
	private String sqsj;// 申请时间
	private String rzdz;// 入住地址
	private String sqly;// 申请理由
	private String sqzt;// 申请状态
	private String lcid;// 流程id
	private String lxkssj;// 留校开始时间
	private String lxjzsj;// 留校截止时间
	private String xn;// 学年
	private String xq;// 学期
	private String sjlx;// 数据类型0：直接录入，1：工作流

	// for query
	private String xqmc;// 学期名称
	private String sjlymc;// 数据来源名称

	// for check
	private String shzt;// 审核过程的状态
	private String shid;// ID
	private String ywid;// 业务ID
	private String shr;// 审核人
	private String shsj;// 审核时间
	private String shyj;// 审核意见
	private String gwid;// 审核岗位
	private String shjg;// 审核结果
	private String thgw;// 退回岗位
	private String fjxx;// 附件信息
	private String sfcnyf;// 是否吃年夜饭
	private String jzxm;// 家长姓名
	private String jzlxdh;// 家长联系电话
	private String sflxgn;// 是否留校过年
	private String bz;// 备注
	private String sfsyqzsw;//是否食用清真食物
	private String yzqs;//原住寝室
	private String cwxx;//床位信息
	private String lddm;//
	private String ldmc;
	private String qsh;//
	private String cwh;//
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] lcids;

	private FormFile impFilePath;//导入文件
	
	//衢州学院个性化字段
	private String lxkssj2;//留校开始时间2
	private String lxjzsj2;//留校截止时间2
	private String lxyy;//留校原因
	private String lxyymc;//留校原因名称
	private String dwlxr;//联系人
	private String dwlxdh;//联系电话
	private String lxdw;//联系单位
	private String lsxq;//留宿校区--浙江传媒个性化
	private String xxxqmc;//留宿校区校区名称--浙江传媒个性化
	
	// 温大个性化字段
	private String lxsqlxdm; //留校申请类型代码
	private String lxsqlxmc; //留校申请类型名称
	
	//浙江中医药大学个性化
	private String yqmc;
	private String lxxqmc;
	private String lxldmc;
	private String lxqs;
	private String lxxq;
	private String lxld;
	private String sfgcj;
	private String sqlxtj;
	
	
	/**
	 * @return the xxxqmc
	 */
	public String getXxxqmc() {
		return xxxqmc;
	}

	/**
	 * @param xxxqmc要设置的 xxxqmc
	 */
	public void setXxxqmc(String xxxqmc) {
		this.xxxqmc = xxxqmc;
	}

	/**
	 * @return the lsxq
	 */
	public String getLsxq() {
		return lsxq;
	}

	/**
	 * @param lsxq要设置的 lsxq
	 */
	public void setLsxq(String lsxq) {
		this.lsxq = lsxq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getRzdz() {
		return rzdz;
	}

	public void setRzdz(String rzdz) {
		this.rzdz = rzdz;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSqzt() {
		return sqzt;
	}

	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}

	public String getLcid() {
		return lcid;
	}

	public void setLcid(String lcid) {
		this.lcid = lcid;
	}

	public String getLxkssj() {
		return lxkssj;
	}

	public void setLxkssj(String lxkssj) {
		this.lxkssj = lxkssj;
	}

	public String getLxjzsj() {
		return lxjzsj;
	}

	public void setLxjzsj(String lxjzsj) {
		this.lxjzsj = lxjzsj;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getSjlx() {
		return sjlx;
	}

	public void setSjlx(String sjlx) {
		this.sjlx = sjlx;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShid() {
		return shid;
	}

	public void setShid(String shid) {
		this.shid = shid;
	}

	public String getYwid() {
		return ywid;
	}

	public void setYwid(String ywid) {
		this.ywid = ywid;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
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

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getThgw() {
		return thgw;
	}

	public void setThgw(String thgw) {
		this.thgw = thgw;
	}

	public String getSjlymc() {
		return sjlymc;
	}

	public void setSjlymc(String sjlymc) {
		this.sjlymc = sjlymc;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getGwids() {
		return gwids;
	}

	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}

	public String[] getXhs() {
		return xhs;
	}

	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	public String[] getLcids() {
		return lcids;
	}

	public void setLcids(String[] lcids) {
		this.lcids = lcids;
	}

	public String getFjxx() {
		return fjxx;
	}

	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
	}


	public String getSfcnyf() {
		return sfcnyf;
	}

	public void setSfcnyf(String sfcnyf) {
		this.sfcnyf = sfcnyf;
	}

	public String getJzxm() {
		return jzxm;
	}

	public void setJzxm(String jzxm) {
		this.jzxm = jzxm;
	}

	public String getJzlxdh() {
		return jzlxdh;
	}

	public void setJzlxdh(String jzlxdh) {
		this.jzlxdh = jzlxdh;
	}

	public String getSflxgn() {
		return sflxgn;
	}

	public void setSflxgn(String sflxgn) {
		this.sflxgn = sflxgn;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the sfsyqzsw
	 */
	public String getSfsyqzsw() {
		return sfsyqzsw;
	}

	/**
	 * @param sfsyqzsw要设置的 sfsyqzsw
	 */
	public void setSfsyqzsw(String sfsyqzsw) {
		this.sfsyqzsw = sfsyqzsw;
	}

	/**
	 * @return the yzqs
	 */
	public String getYzqs() {
		return yzqs;
	}

	/**
	 * @param yzqs要设置的 yzqs
	 */
	public void setYzqs(String yzqs) {
		this.yzqs = yzqs;
	}

	/**
	 * @return the cwxx
	 */
	public String getCwxx() {
		return cwxx;
	}

	/**
	 * @param cwxx要设置的 cwxx
	 */
	public void setCwxx(String cwxx) {
		this.cwxx = cwxx;
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

	public String getLdmc() {
		return ldmc;
	}

	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
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
	 * @return the cwh
	 */
	public String getCwh() {
		return cwh;
	}

	/**
	 * @param cwh要设置的 cwh
	 */
	public void setCwh(String cwh) {
		this.cwh = cwh;
	}

	/**
	 * @return the impFilePath
	 */
	public FormFile getImpFilePath() {
		return impFilePath;
	}

	/**
	 * @param impFilePath要设置的 impFilePath
	 */
	public void setImpFilePath(FormFile impFilePath) {
		this.impFilePath = impFilePath;
	}

	/**
	 * @return the lxkssj2
	 */
	public String getLxkssj2() {
		return lxkssj2;
	}

	/**
	 * @param lxkssj2要设置的 lxkssj2
	 */
	public void setLxkssj2(String lxkssj2) {
		this.lxkssj2 = lxkssj2;
	}

	/**
	 * @return the lxjzsj2
	 */
	public String getLxjzsj2() {
		return lxjzsj2;
	}

	/**
	 * @param lxjzsj2要设置的 lxjzsj2
	 */
	public void setLxjzsj2(String lxjzsj2) {
		this.lxjzsj2 = lxjzsj2;
	}

	/**
	 * @return the lxyy
	 */
	public String getLxyy() {
		return lxyy;
	}

	/**
	 * @param lxyy要设置的 lxyy
	 */
	public void setLxyy(String lxyy) {
		this.lxyy = lxyy;
	}

	/**
	 * @return the dwlxr
	 */
	public String getDwlxr() {
		return dwlxr;
	}

	/**
	 * @param dwlxr要设置的 dwlxr
	 */
	public void setDwlxr(String dwlxr) {
		this.dwlxr = dwlxr;
	}

	/**
	 * @return the dwlxdh
	 */
	public String getDwlxdh() {
		return dwlxdh;
	}

	/**
	 * @param dwlxdh要设置的 dwlxdh
	 */
	public void setDwlxdh(String dwlxdh) {
		this.dwlxdh = dwlxdh;
	}

	/**
	 * @return the lxdw
	 */
	public String getLxdw() {
		return lxdw;
	}

	/**
	 * @param lxdw要设置的 lxdw
	 */
	public void setLxdw(String lxdw) {
		this.lxdw = lxdw;
	}

	/**
	 * @return the lxyymc
	 */
	public String getLxyymc() {
		return lxyymc;
	}

	/**
	 * @param lxyymc要设置的 lxyymc
	 */
	public void setLxyymc(String lxyymc) {
		this.lxyymc = lxyymc;
	}

	public String getLxsqlxdm() {
		return lxsqlxdm;
	}

	public void setLxsqlxdm(String lxsqlxdm) {
		this.lxsqlxdm = lxsqlxdm;
	}

	public String getLxsqlxmc() {
		return lxsqlxmc;
	}

	public void setLxsqlxmc(String lxsqlxmc) {
		this.lxsqlxmc = lxsqlxmc;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:01:46 
	 * @return		: the yqmc
	 */
	public String getYqmc() {
		return yqmc;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:01:46 
	 * @param 		：yqmc the yqmc to set
	 */
	public void setYqmc(String yqmc) {
		this.yqmc = yqmc;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:01:46 
	 * @return		: the lxxqmc
	 */
	public String getLxxqmc() {
		return lxxqmc;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:01:46 
	 * @param 		：lxxqmc the lxxqmc to set
	 */
	public void setLxxqmc(String lxxqmc) {
		this.lxxqmc = lxxqmc;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:01:46 
	 * @return		: the lxldmc
	 */
	public String getLxldmc() {
		return lxldmc;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:01:46 
	 * @param 		：lxldmc the lxldmc to set
	 */
	public void setLxldmc(String lxldmc) {
		this.lxldmc = lxldmc;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:01:46 
	 * @return		: the lxqs
	 */
	public String getLxqs() {
		return lxqs;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:01:46 
	 * @param 		：lxqs the lxqs to set
	 */
	public void setLxqs(String lxqs) {
		this.lxqs = lxqs;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:24:11 
	 * @return		: the lxxq
	 */
	public String getLxxq() {
		return lxxq;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:24:11 
	 * @param 		：lxxq the lxxq to set
	 */
	public void setLxxq(String lxxq) {
		this.lxxq = lxxq;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:24:11 
	 * @return		: the lxld
	 */
	public String getLxld() {
		return lxld;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-12-23 下午02:24:11 
	 * @param 		：lxld the lxld to set
	 */
	public void setLxld(String lxld) {
		this.lxld = lxld;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-2 下午03:09:49 
	 * @return		: the sfgcj
	 */
	public String getSfgcj() {
		return sfgcj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-2 下午03:09:49 
	 * @param 		：sfgcj the sfgcj to set
	 */
	public void setSfgcj(String sfgcj) {
		this.sfgcj = sfgcj;
	}

	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-2 下午03:57:58 
	 * @return		: the sqlxtj
	 */
	public String getSqlxtj() {
		return sqlxtj;
	}

	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2018-1-2 下午03:57:58 
	 * @param 		：sqlxtj the sqlxtj to set
	 */
	public void setSqlxtj(String sqlxtj) {
		this.sqlxtj = sqlxtj;
	}
	
	
}
