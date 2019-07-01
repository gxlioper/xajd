/**
 * @部门:学工产品事业部
 * @日期：2015-5-26 下午02:01:13 
 */  
package xsgzgl.gygl.xyzsgl.sh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-5-26 下午02:01:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyzsShForm extends ActionForm {
	 private String sqbh;      
	 private String xh;
	 private String sqsjstart;
	 private String sqsjend;  
	 private String sqsj;        
	 private String shzt;  
	 private String splc;  
	 private String shlc;
	 private String xxdz;       
	 private String lxdh;       
	 private String parentslxdy; 
	 private String xl;        
	 private String zwjzyy;   
	 private String bz;
	 private String xn;
	 private String shid;
	 private String shjg;
	 private String shyj;
	 private String gwid;
	 private String thgw;
	 private String filepath;
	 //批量审核用
	 private String[] id;
	 private String[] gwids;
	 private String[] xhs;
	 private SearchModel searchModel = new SearchModel();
	 private static final long serialVersionUID = 1L;
	 private String type;
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
	 
	 
	 
	 /**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath要设置的 filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgw要设置的 thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	 
	 /**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlc要设置的 shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	
	 /**
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwids要设置的 gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	
	 /**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	
	 /**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	 /**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjg要设置的 shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	 /**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shid要设置的 shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	
	 /**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	
	/**
	 * @return the sqbh
	 */
	public String getSqbh() {
		return sqbh;
	}
	/**
	 * @param sqbh要设置的 sqbh
	 */
	public void setSqbh(String sqbh) {
		this.sqbh = sqbh;
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
	 * @return the sqsjstart
	 */
	public String getSqsjstart() {
		return sqsjstart;
	}
	/**
	 * @param sqsjstart要设置的 sqsjstart
	 */
	public void setSqsjstart(String sqsjstart) {
		this.sqsjstart = sqsjstart;
	}
	/**
	 * @return the sqsjend
	 */
	public String getSqsjend() {
		return sqsjend;
	}
	/**
	 * @param sqsjend要设置的 sqsjend
	 */
	public void setSqsjend(String sqsjend) {
		this.sqsjend = sqsjend;
	}
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
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
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the xxdz
	 */
	public String getXxdz() {
		return xxdz;
	}
	/**
	 * @param xxdz要设置的 xxdz
	 */
	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdh要设置的 lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the parentslxdy
	 */
	public String getParentslxdy() {
		return parentslxdy;
	}
	/**
	 * @param parentslxdy要设置的 parentslxdy
	 */
	public void setParentslxdy(String parentslxdy) {
		this.parentslxdy = parentslxdy;
	}
	/**
	 * @return the xl
	 */
	public String getXl() {
		return xl;
	}
	/**
	 * @param xl要设置的 xl
	 */
	public void setXl(String xl) {
		this.xl = xl;
	}
	/**
	 * @return the zwjzyy
	 */
	public String getZwjzyy() {
		return zwjzyy;
	}
	/**
	 * @param zwjzyy要设置的 zwjzyy
	 */
	public void setZwjzyy(String zwjzyy) {
		this.zwjzyy = zwjzyy;
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
}
