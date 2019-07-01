/**
 * @部门:学工产品事业部
 * @日期：2015-5-25 上午11:56:38 
 */  
package xsgzgl.gygl.xyzsgl.sq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-5-25 上午11:56:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyzsSqForm extends ActionForm {
	 private String sqbh;      
	 private String xh;
	 private String sqsjstart;
	 private String sqsjend;  
	 private String sqsj;        
	 private String shzt;  
	 private String splc;  
	 private String xxdz;       
	 private String lxdh;       
	 private String parentslxdy; 
	 private String xl;        
	 private String zwjzyy;   
	 private String bz;
	 private String xn;
	 private String filepath;
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

}
