/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xgxt.gygl.sspy.pysq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @className	： SspysqForm
 * @description	： 宿舍评优申请Model
 * @author 		：CP（1352）
 * @date		： 2018-4-27 下午03:16:19
 * @version 	V1.0
 */
public class SspysqForm extends ActionForm{
	private static final long serialVersionUID = 1L;
// xn-xq-lddm-qsh-pyxmdm  唯一
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
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 上午09:39:20 
	 * @return		: the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-28 上午09:39:20 
	 * @param 		：bz the bz to set
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：sqid the sqid to set
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：xn the xn to set
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：sqsj the sqsj to set
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：sqly the sqly to set
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the pyxmdm
	 */
	public String getPyxmdm() {
		return pyxmdm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：pyxmdm the pyxmdm to set
	 */
	public void setPyxmdm(String pyxmdm) {
		this.pyxmdm = pyxmdm;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the pyxmmc
	 */
	public String getPyxmmc() {
		return pyxmmc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：pyxmmc the pyxmmc to set
	 */
	public void setPyxmmc(String pyxmmc) {
		this.pyxmmc = pyxmmc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：lddm the lddm to set
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：qsh the qsh to set
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：splc the splc to set
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：shztmc the shztmc to set
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:42:58 
	 * @return		: the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:42:58 
	 * @param 		：filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午03:29:34 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午04:19:09 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-4-27 下午04:19:09 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-3 下午05:20:26 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-5-3 下午05:20:26 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	
	
}
