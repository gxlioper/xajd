/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.gygl.sspy.pysh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class SspyshForm extends ActionForm{
	private String sqid;
	private String xh;//申请人
	private String xn;
	private String xq;
	private String sqsj;
	private String sqly;
	private String pyxmdm;
	private String pyxmmc;
	private String lddm;
	private String qsh;
	private String splc;
	private String shzt;
	private String shztmc;
	private String filepath;
	private String bz;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String shid;
	private String shlx;
	private String ywid;
	private String shsj;
	private String shr;
	private String shyj;
	private String shlc;
	private String gwid;
	private String thgw;//岗位退回
	private String shjg;
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：xn the xn to set
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：sqsj the sqsj to set
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：sqly the sqly to set
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the pyxmdm
	 */
	public String getPyxmdm() {
		return pyxmdm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：pyxmdm the pyxmdm to set
	 */
	public void setPyxmdm(String pyxmdm) {
		this.pyxmdm = pyxmdm;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the pyxmmc
	 */
	public String getPyxmmc() {
		return pyxmmc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：pyxmmc the pyxmmc to set
	 */
	public void setPyxmmc(String pyxmmc) {
		this.pyxmmc = pyxmmc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：lddm the lddm to set
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：qsh the qsh to set
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：shztmc the shztmc to set
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：bz the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：shid the shid to set
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the shlx
	 */
	public String getShlx() {
		return shlx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：shlx the shlx to set
	 */
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：ywid the ywid to set
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：shsj the shsj to set
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：shr the shr to set
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：shyj the shyj to set
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：shlc the shlc to set
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：gwid the gwid to set
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：thgw the thgw to set
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：shjg the shjg to set
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：id the id to set
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：gwids the gwids to set
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：xhs the xhs to set
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @return		: the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:13:39 
	 * @param 		：splcs the splcs to set
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:26:02 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午12:26:02 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午02:28:32 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 下午02:28:32 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	
}
