package xgxt.szdw.bjlh.fdykh;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.CommForm;

public class BjlhFdykhForm extends CommForm{
	private static final long serialVersionUID = -9205711105806100577L;
	private String[] checkVal;
	
	private ExportModel exportModel =  new ExportModel();
	private String type;
	private String tjlx;
	private String zzlbmc; //职责类别
	private String[] khzgh;//考核职工号
	private String xm;//姓名
	private String xy;//学院
	private String bmdm;
		//辅导员考核表   xg_szdw_bjlh_fdykhb
	private String khbid;//考核表id
	private String xn;//学年
	private String khbmc;//考核表名称
	private String pfdx;//评分对象
	private String rq;//日期
	private String sfqy;//是否启用
	
	//辅导员考核项目表  xg_szdw_bjlh_fdykhxmb
	private String xmid;//项目id
//	private String khbid;//考核表id
	private String yjzb;//一级指标
	private String ejzb;//二级指标
	private String khnr;//考核内容
	private String fzqj;//分值区间
	private String pflx;//评分类型
	private String xssx;//显示顺序
	
	//辅导员考核评分表xg_szdw_bjlh_fdykhpfb
//	private String xmid;//项目id
//	private String fz;//分值
	private String yhm;//用户名
	private String fdyid;//辅导员id
	
	private String zgh;//职工号
	private String bmmc;//部门名称
	private String khdj;//考核等级
	private String jtdj;//津贴等级
	private String khdjdm;//考核等级代码
	private String jtdjdm;//津贴等级代码
	
	public String[] getCheckVal() {
		return checkVal;
	}
	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}
	public String[] getKhzgh() {
		return khzgh;
	}
	public void setKhzgh(String[] khzgh) {
		this.khzgh = khzgh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getKhbid() {
		return khbid;
	}
	public void setKhbid(String khbid) {
		this.khbid = khbid;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getKhbmc() {
		return khbmc;
	}
	public void setKhbmc(String khbmc) {
		this.khbmc = khbmc;
	}
	public String getPfdx() {
		return pfdx;
	}
	public void setPfdx(String pfdx) {
		this.pfdx = pfdx;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	public String getYjzb() {
		return yjzb;
	}
	public void setYjzb(String yjzb) {
		this.yjzb = yjzb;
	}
	public String getEjzb() {
		return ejzb;
	}
	public void setEjzb(String ejzb) {
		this.ejzb = ejzb;
	}
	public String getKhnr() {
		return khnr;
	}
	public void setKhnr(String khnr) {
		this.khnr = khnr;
	}
	public String getFzqj() {
		return fzqj;
	}
	public void setFzqj(String fzqj) {
		this.fzqj = fzqj;
	}
	public String getPflx() {
		return pflx;
	}
	public void setPflx(String pflx) {
		this.pflx = pflx;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getFdyid() {
		return fdyid;
	}
	public void setFdyid(String fdyid) {
		this.fdyid = fdyid;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getKhdj() {
		return khdj;
	}
	public void setKhdj(String khdj) {
		this.khdj = khdj;
	}
	public String getJtdj() {
		return jtdj;
	}
	public void setJtdj(String jtdj) {
		this.jtdj = jtdj;
	}
	public String getKhdjdm() {
		return khdjdm;
	}
	public void setKhdjdm(String khdjdm) {
		this.khdjdm = khdjdm;
	}
	public String getJtdjdm() {
		return jtdjdm;
	}
	public void setJtdjdm(String jtdjdm) {
		this.jtdjdm = jtdjdm;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @return the tjlx
	 */
	public String getTjlx() {
		return tjlx;
	}
	/**
	 * @param tjlx要设置的 tjlx
	 */
	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}
	/**
	 * @return the zzlbmc
	 */
	public String getZzlbmc() {
		return zzlbmc;
	}
	/**
	 * @param zzlbmc要设置的 zzlbmc
	 */
	public void setZzlbmc(String zzlbmc) {
		this.zzlbmc = zzlbmc;
	}
	
}
