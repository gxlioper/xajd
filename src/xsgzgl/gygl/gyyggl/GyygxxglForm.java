/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:41:33 
 */  
package xsgzgl.gygl.gyyggl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.gygl.comm.GyglNewForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xucy [工号：754]
 * @时间： 2013-7-30 下午04:41:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyygxxglForm extends GyglNewForm{
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private static final long serialVersionUID = 1L;
	
	private String ygbh;//员工编号
	
	private String xm;//员工姓名
	
	private String xb;//性别
	
	private String xbmc;//性别名称
	
	private String nl;//年龄
	
	private String pqzyzk;//聘前职业状况
	
	private String zwdm;//职位代码
	
	private String zwmc;//职位名称
	
	private String pyrq;//聘用日期
	
	private String sfzh;//身份证号
	
	private String lxdh;//联系电话
	
	private String gzbz;//工资标准
	
	private String ssld;//所属楼栋
	
	private String zgzt;//是否在岗
	
	private ExportModel exportModel = new ExportModel();

	public String getYgbh() {
		return ygbh;
	}

	public void setYgbh(String ygbh) {
		this.ygbh = ygbh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getNl() {
		return nl;
	}

	public void setNl(String nl) {
		this.nl = nl;
	}

	public String getPqzyzk() {
		return pqzyzk;
	}

	public void setPqzyzk(String pqzyzk) {
		this.pqzyzk = pqzyzk;
	}

	public String getZwdm() {
		return zwdm;
	}

	public void setZwdm(String zwdm) {
		this.zwdm = zwdm;
	}

	public String getPyrq() {
		return pyrq;
	}

	public void setPyrq(String pyrq) {
		this.pyrq = pyrq;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getGzbz() {
		return gzbz;
	}

	public void setGzbz(String gzbz) {
		this.gzbz = gzbz;
	}

	public String getSsld() {
		return ssld;
	}

	public void setSsld(String ssld) {
		this.ssld = ssld;
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

	public String getXbmc() {
		return xbmc;
	}

	public void setXbmc(String xbmc) {
		this.xbmc = xbmc;
	}

	public String getZwmc() {
		return zwmc;
	}

	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	/**
	 * @return the zgzt
	 */
	public String getZgzt() {
		return zgzt;
	}

	/**
	 * @param zgzt要设置的 zgzt
	 */
	public void setZgzt(String zgzt) {
		this.zgzt = zgzt;
	}

}
