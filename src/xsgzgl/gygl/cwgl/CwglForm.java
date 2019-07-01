package xsgzgl.gygl.cwgl;


import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xsgzgl.gygl.comm.GyglNewForm;

public class CwglForm extends GyglNewForm{
	
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String bjdm;     //所属班级代码
	private String bz;     //备注
	private String xydm;     //所属学院代码
	private String cwh;     //床位号
	private String lddm;     //楼栋代码
	private String qsh;     //寝室号
	private String nj;     //所属年级
	private String zydm;    //专业代码
	private String xh;     //现住宿学生学号
	private String sfbl;   //是否保留
	private String ch;     //层号
	private String xb;		//性别
	
	private String[] pk_xh;
	private String tsyy;	//退宿原因
	private String tssj;	//退宿时间
	
	private String rzsj; 	//入住时间
	private String czr;		//操作人
	private ExportModel exportModel = new ExportModel();
	private String cshlx;	//初始化类型
	
	private String sfqccwss;//是否清除床位所属
	private String sfqcqsss;//是否清除寝室所属
	private String searchTjstr;//查询条件
	private String blbz;//保留备注
	private String yllb;//预留类别
	
	private String rzyy;//入住原因
	/**
	 * 学年
	 */
	private String xn;
	
	/**
	 * 学期
	 */
	private String xq;
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
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
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public String getSfbl() {
		return sfbl;
	}
	public void setSfbl(String sfbl) {
		this.sfbl = sfbl;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getCwh() {
		return cwh;
	}
	public void setCwh(String cwh) {
		this.cwh = cwh;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String[] getPk_xh() {
		return pk_xh;
	}
	public void setPk_xh(String[] pkXh) {
		pk_xh = pkXh;
	}
	public String getTsyy() {
		return tsyy;
	}
	public void setTsyy(String tsyy) {
		this.tsyy = tsyy;
	}
	public String getTssj() {
		return tssj;
	}
	public void setTssj(String tssj) {
		this.tssj = tssj;
	}
	public String getCshlx() {
		return cshlx;
	}
	public void setCshlx(String cshlx) {
		this.cshlx = cshlx;
	}
	public String getSfqccwss() {
		return sfqccwss;
	}
	public void setSfqccwss(String sfqccwss) {
		this.sfqccwss = sfqccwss;
	}
	public String getSfqcqsss() {
		return sfqcqsss;
	}
	public void setSfqcqsss(String sfqcqsss) {
		this.sfqcqsss = sfqcqsss;
	}
	public String getSearchTjstr() {
		return searchTjstr;
	}
	public void setSearchTjstr(String searchTjstr) {
		this.searchTjstr = searchTjstr;
	}
	public void setBlbz(String blbz) {
		this.blbz = blbz;
	}
	public String getBlbz() {
		return blbz;
	}
	public String getYllb() {
		return yllb;
	}
	public void setYllb(String yllb) {
		this.yllb = yllb;
	}
	public String getRzyy() {
		return rzyy;
	}
	public void setRzyy(String rzyy) {
		this.rzyy = rzyy;
	}
	
}
