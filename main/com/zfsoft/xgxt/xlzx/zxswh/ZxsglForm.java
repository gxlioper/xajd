package com.zfsoft.xgxt.xlzx.zxswh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: (咨询师信息查询) 
 * @作者： whj [工号：1004]
 * @时间： 2013-8-14 下午2:55:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class ZxsglForm extends ActionForm {


	private static final long serialVersionUID = 1L;


	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	
	private ExportModel exportModel = new ExportModel();
	  
	private String zgh; // 编号（职工号）
	
	private String lxdh; //联系电话
	
	private String address; //办公电话
	
	private String zxslv; // 级别
	
	private String zxszg; // 资格

	private String kjdrs; // 可接待人数/日

	private String status; //在岗状态(在岗、不在岗)

	private String createsj; //创建时间

	private String createbh; //创建人编号

	private String createmc; //创建人名称
	
	private String  zxsjj; //简介
	
	private String  datazt; //数据状态0失效1正常
	
	
	private String xm; // 姓名
	
	private String xb; // 性别
	
	private String age; // 年龄
	
	private String xq; // 校区
	
	private String sclydm; // 擅长领域
	

	/**
	 * @return the scly
	 */
	public String getSclydm() {
		return sclydm;
	}

	/**
	 * @param scly要设置的 scly
	 */
	public void setSclydm(String sclydm) {
		this.sclydm = sclydm;
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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}

	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address要设置的 address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the zxslv
	 */
	public String getZxslv() {
		return zxslv;
	}

	/**
	 * @param zxslv要设置的 zxslv
	 */
	public void setZxslv(String zxslv) {
		this.zxslv = zxslv;
	}

	/**
	 * @return the zxszg
	 */
	public String getZxszg() {
		return zxszg;
	}

	/**
	 * @param zxszg要设置的 zxszg
	 */
	public void setZxszg(String zxszg) {
		this.zxszg = zxszg;
	}

	/**
	 * @return the kjdrs
	 */
	public String getKjdrs() {
		return kjdrs;
	}

	/**
	 * @param kjdrs要设置的 kjdrs
	 */
	public void setKjdrs(String kjdrs) {
		this.kjdrs = kjdrs;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status要设置的 status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the createsj
	 */
	public String getCreatesj() {
		return createsj;
	}

	/**
	 * @param createsj要设置的 createsj
	 */
	public void setCreatesj(String createsj) {
		this.createsj = createsj;
	}

	/**
	 * @return the createbh
	 */
	public String getCreatebh() {
		return createbh;
	}

	/**
	 * @param createbh要设置的 createbh
	 */
	public void setCreatebh(String createbh) {
		this.createbh = createbh;
	}

	/**
	 * @return the createmc
	 */
	public String getCreatemc() {
		return createmc;
	}

	/**
	 * @param createmc要设置的 createmc
	 */
	public void setCreatemc(String createmc) {
		this.createmc = createmc;
	}

	/**
	 * @return the zxsjj
	 */
	public String getZxsjj() {
		return zxsjj;
	}

	/**
	 * @param zxsjj要设置的 zxsjj
	 */
	public void setZxsjj(String zxsjj) {
		this.zxsjj = zxsjj;
	}

	
	/**
	 * @return the datazt
	 */
	public String getDatazt() {
		return datazt;
	}

	/**
	 * @param datazt要设置的 datazt
	 */
	public void setDatazt(String datazt) {
		this.datazt = datazt;
	}

	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}

	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}

	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}

	/**
	 * @param xb要设置的 xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
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

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

}
