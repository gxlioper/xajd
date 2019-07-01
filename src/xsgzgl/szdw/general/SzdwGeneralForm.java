package xsgzgl.szdw.general;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_通用_Form类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SzdwGeneralForm extends CommForm {

	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	// ------------------思政队伍编办 begin  2013.1.14 by qlj-----------------
	private String xb; // 性别
	
	private String bmdm; // 部门代码 
	
	private String sfdb; // 是否带班
	
	private String zgh; // 职工号

	private String zghs;
	// ------------------思政队伍编办 end  2013.1.14 by qlj-----------------
	
	private String bzrdb;//班主任带班
	
	private String fdydb;//辅导员带班
	
	private String jryx;//兼任院系
	private String khyh;//开户银行
	private String yhzh;//银行账号
	private String hyzk;//婚姻状况
	private String fdyzbs;//辅导员值班室
	private String fdyrzrq;//辅导员入职日期
	private String zyjnzs;//职业技能证书
	private String age;//年龄
	private String bmlb;//部门类别
	private String zzshen;//住址（省）
	private String zzshi;//住址（市）
	private String zzxian;//住址（县）
	private String qqqh;//QQ群号
	private String bjdm ;//班级代码
	private String bjmc ; //班级名称
	private String type;//保存
	private String bbType;//编班类型
	private String sydm;//书院

	public String getSydm() {
		return sydm;
	}

	public void setSydm(String sydm) {
		this.sydm = sydm;
	}

	public String getBbType() {
		return bbType;
	}

	public void setBbType(String bbType) {
		this.bbType = bbType;
	}

	public String getZghs() {
		return zghs;
	}

	public void setZghs(String zghs) {
		this.zghs = zghs;
	}

	@Override
	public Pages getPages() {
		return pages;
	}

	@Override
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	@Override
	public SearchModel getSearchModel() {
		return searchModel;
	}

	@Override
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	/**
	 * @return the qqqh
	 */
	public String getQqqh() {
		return qqqh;
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
	 * @param qqqh要设置的 qqqh
	 */
	public void setQqqh(String qqqh) {
		this.qqqh = qqqh;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmc要设置的 bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the zzshen
	 */
	public String getZzshen() {
		return zzshen;
	}

	/**
	 * @param zzshen要设置的 zzshen
	 */
	public void setZzshen(String zzshen) {
		this.zzshen = zzshen;
	}

	/**
	 * @return the zzshi
	 */
	public String getZzshi() {
		return zzshi;
	}

	/**
	 * @param zzshi要设置的 zzshi
	 */
	public void setZzshi(String zzshi) {
		this.zzshi = zzshi;
	}

	/**
	 * @return the zzxian
	 */
	public String getZzxian() {
		return zzxian;
	}

	/**
	 * @param zzxian要设置的 zzxian
	 */
	public void setZzxian(String zzxian) {
		this.zzxian = zzxian;
	}

	/**
	 * @return the yhzh
	 */
	public String getYhzh() {
		return yhzh;
	}

	/**
	 * @param yhzh要设置的 yhzh
	 */
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	
	/**
	 * @return the khyh
	 */
	public String getKhyh() {
		return khyh;
	}

	/**
	 * @param khyh要设置的 khyh
	 */
	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}

	/**
	 * @return the hyzk
	 */
	public String getHyzk() {
		return hyzk;
	}

	/**
	 * @param hyzk要设置的 hyzk
	 */
	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	/**
	 * @return the fdyzbs
	 */
	public String getFdyzbs() {
		return fdyzbs;
	}

	/**
	 * @param fdyzbs要设置的 fdyzbs
	 */
	public void setFdyzbs(String fdyzbs) {
		this.fdyzbs = fdyzbs;
	}

	/**
	 * @return the fdyrzrq
	 */
	public String getFdyrzrq() {
		return fdyrzrq;
	}

	/**
	 * @param fdyrzrq要设置的 fdyrzrq
	 */
	public void setFdyrzrq(String fdyrzrq) {
		this.fdyrzrq = fdyrzrq;
	}

	/**
	 * @return the zyjnzs
	 */
	public String getZyjnzs() {
		return zyjnzs;
	}

	/**
	 * @param zyjnzs要设置的 zyjnzs
	 */
	public void setZyjnzs(String zyjnzs) {
		this.zyjnzs = zyjnzs;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age要设置的 age
	 */
	public void setAge(String age) {
		this.age = age;
	}

	
	

	
	private ExportModel exportModel = new ExportModel();
	private String path;
	

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getSfdb() {
		return sfdb;
	}

	public void setSfdb(String sfdb) {
		this.sfdb = sfdb;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getBzrdb() {
		return bzrdb;
	}

	public void setBzrdb(String bzrdb) {
		this.bzrdb = bzrdb;
	}

	public String getFdydb() {
		return fdydb;
	}

	public void setFdydb(String fdydb) {
		this.fdydb = fdydb;
	}

	public String getJryx() {
		return jryx;
	}

	public void setJryx(String jryx) {
		this.jryx = jryx;
	}

	public String getBmlb() {
		return bmlb;
	}

	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path要设置的 path
	 */
	public void setPath(String path) {
		this.path = path;
	}


}
