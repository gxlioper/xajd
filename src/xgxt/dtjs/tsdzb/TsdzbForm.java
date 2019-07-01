/**
 * @部门:学工产品事业部
 * @日期：2017-10-30 下午03:45:41 
 */  
package xgxt.dtjs.tsdzb;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 特色党支部form(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-10-30 下午03:45:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsdzbForm extends ActionForm {
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -3716993835629788977L;
	private String dzbid;
	private String dzbmc;
	private String fzr;
	private String lxfs;
	private String cjsj;
	private String cjr;
	private String bz;
	private String shzt;
	private String shyj;
	private String pf;
	private String cjrxm;
	private String bjxx;
	private String cjrzgh;
	private String cjrlx;
	
	private String[] bjdms;
	private String type;
	private String njdm;
	private String xydm;
	private String zydm;
	private String[] bjdmms;
	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the dzbid
	 */
	public String getDzbid() {
		return dzbid;
	}
	/**
	 * @param dzbid要设置的 dzbid
	 */
	public void setDzbid(String dzbid) {
		this.dzbid = dzbid;
	}
	/**
	 * @return the fzr
	 */
	public String getFzr() {
		return fzr;
	}
	/**
	 * @param fzr要设置的 fzr
	 */
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	/**
	 * @return the lxfs
	 */
	public String getLxfs() {
		return lxfs;
	}
	/**
	 * @param lxfs要设置的 lxfs
	 */
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}
	/**
	 * @return the cjsj
	 */
	public String getCjsj() {
		return cjsj;
	}
	/**
	 * @param cjsj要设置的 cjsj
	 */
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	/**
	 * @return the cjr
	 */
	public String getCjr() {
		return cjr;
	}
	/**
	 * @param cjr要设置的 cjr
	 */
	public void setCjr(String cjr) {
		this.cjr = cjr;
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
	 * @return the pf
	 */
	public String getPf() {
		return pf;
	}
	/**
	 * @param pf要设置的 pf
	 */
	public void setPf(String pf) {
		this.pf = pf;
	}
	/**
	 * @return the bjdms
	 */
	public String[] getBjdms() {
		return bjdms;
	}
	/**
	 * @param bjdms要设置的 bjdms
	 */
	public void setBjdms(String[] bjdms) {
		this.bjdms = bjdms;
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
	 * @return the cjrxm
	 */
	public String getCjrxm() {
		return cjrxm;
	}
	/**
	 * @param cjrxm要设置的 cjrxm
	 */
	public void setCjrxm(String cjrxm) {
		this.cjrxm = cjrxm;
	}
	/**
	 * @return the dzbmc
	 */
	public String getDzbmc() {
		return dzbmc;
	}
	/**
	 * @param dzbmc要设置的 dzbmc
	 */
	public void setDzbmc(String dzbmc) {
		this.dzbmc = dzbmc;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-1 上午09:10:02 
	 * @return		: the bjxx
	 */
	public String getBjxx() {
		return bjxx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-1 上午09:10:02 
	 * @param 		：bjxx the bjxx to set
	 */
	public void setBjxx(String bjxx) {
		this.bjxx = bjxx;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-1 上午09:49:20 
	 * @return		: the cjrzgh
	 */
	public String getCjrzgh() {
		return cjrzgh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-1 上午09:49:20 
	 * @param 		：cjrzgh the cjrzgh to set
	 */
	public void setCjrzgh(String cjrzgh) {
		this.cjrzgh = cjrzgh;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-1 上午09:54:48 
	 * @return		: the cjrlx
	 */
	public String getCjrlx() {
		return cjrlx;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-1 上午09:54:48 
	 * @param 		：cjrlx the cjrlx to set
	 */
	public void setCjrlx(String cjrlx) {
		this.cjrlx = cjrlx;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-2 上午09:13:06 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-2 上午09:13:06 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-30 上午11:29:41 
	 * @return		: the njdm
	 */
	public String getNjdm() {
		return njdm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-30 上午11:29:41 
	 * @param 		：njdm the njdm to set
	 */
	public void setNjdm(String njdm) {
		this.njdm = njdm;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-30 上午11:29:41 
	 * @return		: the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-30 上午11:29:41 
	 * @param 		：xydm the xydm to set
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-30 上午11:29:41 
	 * @return		: the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-30 上午11:29:41 
	 * @param 		：zydm the zydm to set
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-30 下午05:35:51 
	 * @return		: the bjdmms
	 */
	public String[] getBjdmms() {
		return bjdmms;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-30 下午05:35:51 
	 * @param 		：bjdmms the bjdmms to set
	 */
	public void setBjdmms(String[] bjdmms) {
		this.bjdmms = bjdmms;
	}
	
	
	
}
