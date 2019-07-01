/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stglsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class StglshForm extends ActionForm{
	private Pages pages = new Pages();
	// 高级查询
	SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();

	private String type;
	private String xh;
	private String zzsqsj;
	private String zzly;
	private String sqid;
	private String sqsj;
	private String zzywid;
	private String stlx;
	private String stqc;
	private String stjc;
	private String styx;
	private String ywzddw;
	private String stzdls;
	private String stjs;
	private String gzh;
	private String filepath;
	private String splc;
	private String shzt;
	private String zzshzt;
	
	private String shid;
	private String shlx;//0 新社团申请 1 转正申请
	private String ywid;
	private String shsj;
	private String shr;
	private String shyj;
	private String shlc;
	private String gwid;
	private String shztmc;
	private String thgw;//岗位退回
	private String shjg;
	
	private String[] id;//放申请ID
	private String[] zzid;//放转正业务ID
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	private String[] shlxs;

	private String strs;//组织人数
	private String[] jflyArray;//学生组织经费来源
	private String jfly;
	private String bgsdz;//办公室地址
	private String ndzzzt;//年度组织状态
	private String xn;//学年
	private String zzlb;//组织类别
	private String[] tzsxh;//团支书信息学号
	private String clsj;//成立时间

	public String getStrs() {
		return strs;
	}

	public void setStrs(String strs) {
		this.strs = strs;
	}

	public String[] getJflyArray() {
		return jflyArray;
	}

	public void setJflyArray(String[] jflyArray) {
		this.jflyArray = jflyArray;
	}

	public String getJfly() {
		return jfly;
	}

	public void setJfly(String jfly) {
		this.jfly = jfly;
	}

	public String getBgsdz() {
		return bgsdz;
	}

	public void setBgsdz(String bgsdz) {
		this.bgsdz = bgsdz;
	}

	public String getNdzzzt() {
		return ndzzzt;
	}

	public void setNdzzzt(String ndzzzt) {
		this.ndzzzt = ndzzzt;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getZzlb() {
		return zzlb;
	}

	public void setZzlb(String zzlb) {
		this.zzlb = zzlb;
	}

	public String[] getTzsxh() {
		return tzsxh;
	}

	public void setTzsxh(String[] tzsxh) {
		this.tzsxh = tzsxh;
	}

	public String getClsj() {
		return clsj;
	}

	public void setClsj(String clsj) {
		this.clsj = clsj;
	}

	/**

	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the stlx
	 */
	public String getStlx() {
		return stlx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：stlx the stlx to set
	 */
	public void setStlx(String stlx) {
		this.stlx = stlx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the stqc
	 */
	public String getStqc() {
		return stqc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：stqc the stqc to set
	 */
	public void setStqc(String stqc) {
		this.stqc = stqc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the stjc
	 */
	public String getStjc() {
		return stjc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：stjc the stjc to set
	 */
	public void setStjc(String stjc) {
		this.stjc = stjc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the styx
	 */
	public String getStyx() {
		return styx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：styx the styx to set
	 */
	public void setStyx(String styx) {
		this.styx = styx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the ywzddw
	 */
	public String getYwzddw() {
		return ywzddw;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：ywzddw the ywzddw to set
	 */
	public void setYwzddw(String ywzddw) {
		this.ywzddw = ywzddw;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the stzdls
	 */
	public String getStzdls() {
		return stzdls;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：stzdls the stzdls to set
	 */
	public void setStzdls(String stzdls) {
		this.stzdls = stzdls;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the stjs
	 */
	public String getStjs() {
		return stjs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：stjs the stjs to set
	 */
	public void setStjs(String stjs) {
		this.stjs = stjs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the gzh
	 */
	public String getGzh() {
		return gzh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：gzh the gzh to set
	 */
	public void setGzh(String gzh) {
		this.gzh = gzh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：shid the shid to set
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the shlx
	 */
	public String getShlx() {
		return shlx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：shlx the shlx to set
	 */
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：ywid the ywid to set
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：shsj the shsj to set
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：shr the shr to set
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：shyj the shyj to set
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：shlc the shlc to set
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：gwid the gwid to set
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：shztmc the shztmc to set
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：thgw the thgw to set
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：shjg the shjg to set
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：id the id to set
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：gwids the gwids to set
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：xhs the xhs to set
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @return		: the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-2 下午03:41:25 
	 * @param 		：splcs the splcs to set
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-8 下午03:13:05 
	 * @return		: the zzywid
	 */
	public String getZzywid() {
		return zzywid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-8 下午03:13:05 
	 * @param 		：zzywid the zzywid to set
	 */
	public void setZzywid(String zzywid) {
		this.zzywid = zzywid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-8 下午05:35:39 
	 * @return		: the zzshzt
	 */
	public String getZzshzt() {
		return zzshzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-8 下午05:35:39 
	 * @param 		：zzshzt the zzshzt to set
	 */
	public void setZzshzt(String zzshzt) {
		this.zzshzt = zzshzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-8 下午05:44:49 
	 * @return		: the zzsqsj
	 */
	public String getZzsqsj() {
		return zzsqsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-8 下午05:44:49 
	 * @param 		：zzsqsj the zzsqsj to set
	 */
	public void setZzsqsj(String zzsqsj) {
		this.zzsqsj = zzsqsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-8 下午05:44:49 
	 * @return		: the zzly
	 */
	public String getZzly() {
		return zzly;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-8 下午05:44:49 
	 * @param 		：zzly the zzly to set
	 */
	public void setZzly(String zzly) {
		this.zzly = zzly;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-8 下午05:45:26 
	 * @return		: the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-8 下午05:45:26 
	 * @param 		：sqsj the sqsj to set
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-9 上午10:21:01 
	 * @return		: the zzid
	 */
	public String[] getZzid() {
		return zzid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-9 上午10:21:01 
	 * @param 		：zzid the zzid to set
	 */
	public void setZzid(String[] zzid) {
		this.zzid = zzid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-9 上午10:23:18 
	 * @return		: the shlxs
	 */
	public String[] getShlxs() {
		return shlxs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-2-9 上午10:23:18 
	 * @param 		：shlxs the shlxs to set
	 */
	public void setShlxs(String[] shlxs) {
		this.shlxs = shlxs;
	}
	
	
}
