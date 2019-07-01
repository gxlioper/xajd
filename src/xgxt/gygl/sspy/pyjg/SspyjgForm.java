/**
 * @部门:学工产品(1)部
 * @日期：2018-4-28 上午09:25:05 
 */  
package xgxt.gygl.sspy.pyjg;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理管理模块
 * @类功能描述: 宿舍评优结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-28 上午09:24:38 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class SspyjgForm extends ActionForm{
	
	private static final long serialVersionUID = -4562302399219564190L;
	private Pages pages = new Pages();//分页
	private SearchModel searchModel = new SearchModel();//高级查询
	private ExportModel exportModel = new ExportModel();//自定义导出
	private String type;//类型
	private String guid;//结果ID
	private String xh;//学号
	private String sqsj;//申请时间
	private String xn;//学年
	private String xq;//学期
	private String lddm;//楼栋代码
	private String ldmc;//楼栋名称
	private String ch;//层号
	private String qsh;//寝室号
	private String pyxmdm;//评优项目代码
	private String sjly;//数据来源
	private String filepath;//附件id
	private String sqly;//申请理由
	private String bz;//备注
	private String ywid;//业务id
	private String sjlrsj;//数据录入时间
	private String sjlrr;//数据录入人
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
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guid要设置的 guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
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
	 * @return the pyxmdm
	 */
	public String getPyxmdm() {
		return pyxmdm;
	}
	/**
	 * @param pyxmdm要设置的 pyxmdm
	 */
	public void setPyxmdm(String pyxmdm) {
		this.pyxmdm = pyxmdm;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
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
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
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
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywid要设置的 ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @return the sjlrsj
	 */
	public String getSjlrsj() {
		return sjlrsj;
	}
	/**
	 * @param sjlrsj要设置的 sjlrsj
	 */
	public void setSjlrsj(String sjlrsj) {
		this.sjlrsj = sjlrsj;
	}
	/**
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmc要设置的 ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return the ch
	 */
	public String getCh() {
		return ch;
	}
	/**
	 * @param ch要设置的 ch
	 */
	public void setCh(String ch) {
		this.ch = ch;
	}
	/**
	 * @return the sjlrr
	 */
	public String getSjlrr() {
		return sjlrr;
	}
	/**
	 * @param sjlrr要设置的 sjlrr
	 */
	public void setSjlrr(String sjlrr) {
		this.sjlrr = sjlrr;
	}
}
