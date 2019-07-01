package xgxt.pjpy.tyb.zhszcp.action;

import xgxt.form.BaseForm;

public class PjpyZctjszActionForm extends BaseForm {

	private String dm;    
	private String mc;   
	private String bl;    
	private String xzf;   
	private String lb;    
	private String bm;    
	private String zd;    
	private String fdm;   
	private String tj;    
	private String sfplzj;
	private String shjb;
	private String mrf;
	private String zjz;
	private String zj;
	
	private String[] ejdm;    
	private String[] ejmc;    
	private String[] ejbl;    
	private String[] ejxzf;   
	private String[] ejlb;    
	private String[] ejbm;    
	private String[] ejzd;    
	private String[] ejfdm;   
	private String[] ejtj;    
	private String[] ejsfplzj;
	private String[] ejshjb;
	
	
	private String[] sjdm;    
	private String[] sjmc;    
	private String[] sjbl;    
	private String[] sjxzf;   
	private String[] sjlb;    
	private String[] sjbm;    
	private String[] sjzd;    
	private String[] sjfdm;   
	private String[] sjtj;    
	private String[] sjsfplzj;
	private String[] sjshjb;
	private String[] sjmrf;
	private String[] sjzjz;
	private String[] sjzj;
	private String[] sjsfwh;
	
	private String save_zdid;   
	private String save_zdmc;   
	private String save_tabname;
	private String save_mkmc;   
	private String save_zdlx;   
	private String save_zdcd;   
	private String save_bz;     
	private String save_cxxs;   
	private String save_cxxspx; 
	private String save_zdpx;  
	private String queryequals_tabname;
	
	private String tabname;
	
	private String zdcd;     //字段长度
	
	private String zdid;     //字段id
	
	private String cxxspx;     //查询显示排序
	
	private String zdpx;     //字段排序
	
	private String zdmc;     //字段名称
	
	private String zdlx;     //字段类型

	private String[] opid;     //下拉框选项代码
	
	private String[] opmc;     //下拉框选项名称
	
	private String cxxs;     //查询显示
	
	private String mkmc;     //模块名称
	
	private String bz;//备注
	
	private String sfnum;
	private String sfnull;

	public String getBl() {
		return bl;
	}
	public void setBl(String bl) {
		this.bl = bl;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getFdm() {
		return fdm;
	}
	public void setFdm(String fdm) {
		this.fdm = fdm;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getSfplzj() {
		return sfplzj;
	}
	public void setSfplzj(String sfplzj) {
		this.sfplzj = sfplzj;
	}
	public String getShjb() {
		return shjb;
	}
	public void setShjb(String shjb) {
		this.shjb = shjb;
	}
	public String getTj() {
		return tj;
	}
	public void setTj(String tj) {
		this.tj = tj;
	}
	public String getXzf() {
		return xzf;
	}
	public void setXzf(String xzf) {
		this.xzf = xzf;
	}
	public String getZd() {
		return zd;
	}
	public void setZd(String zd) {
		this.zd = zd;
	}
	public String[] getEjbl() {
		return ejbl;
	}
	public void setEjbl(String[] ejbl) {
		this.ejbl = ejbl;
	}
	public String[] getEjbm() {
		return ejbm;
	}
	public void setEjbm(String[] ejbm) {
		this.ejbm = ejbm;
	}
	public String[] getEjdm() {
		return ejdm;
	}
	public void setEjdm(String[] ejdm) {
		this.ejdm = ejdm;
	}
	public String[] getEjfdm() {
		return ejfdm;
	}
	public void setEjfdm(String[] ejfdm) {
		this.ejfdm = ejfdm;
	}
	public String[] getEjlb() {
		return ejlb;
	}
	public void setEjlb(String[] ejlb) {
		this.ejlb = ejlb;
	}
	public String[] getEjmc() {
		return ejmc;
	}
	public void setEjmc(String[] ejmc) {
		this.ejmc = ejmc;
	}
	public String[] getEjsfplzj() {
		return ejsfplzj;
	}
	public void setEjsfplzj(String[] ejsfplzj) {
		this.ejsfplzj = ejsfplzj;
	}
	public String[] getEjshjb() {
		return ejshjb;
	}
	public void setEjshjb(String[] ejshjb) {
		this.ejshjb = ejshjb;
	}
	public String[] getEjtj() {
		return ejtj;
	}
	public void setEjtj(String[] ejtj) {
		this.ejtj = ejtj;
	}
	public String[] getEjxzf() {
		return ejxzf;
	}
	public void setEjxzf(String[] ejxzf) {
		this.ejxzf = ejxzf;
	}
	public String[] getEjzd() {
		return ejzd;
	}
	public void setEjzd(String[] ejzd) {
		this.ejzd = ejzd;
	}
	public String[] getSjbl() {
		return sjbl;
	}
	public void setSjbl(String[] sjbl) {
		this.sjbl = sjbl;
	}
	public String[] getSjbm() {
		return sjbm;
	}
	public void setSjbm(String[] sjbm) {
		this.sjbm = sjbm;
	}
	public String[] getSjdm() {
		return sjdm;
	}
	public void setSjdm(String[] sjdm) {
		this.sjdm = sjdm;
	}
	public String[] getSjfdm() {
		return sjfdm;
	}
	public void setSjfdm(String[] sjfdm) {
		this.sjfdm = sjfdm;
	}
	public String[] getSjlb() {
		return sjlb;
	}
	public void setSjlb(String[] sjlb) {
		this.sjlb = sjlb;
	}
	public String[] getSjmc() {
		return sjmc;
	}
	public void setSjmc(String[] sjmc) {
		this.sjmc = sjmc;
	}
	public String[] getSjsfplzj() {
		return sjsfplzj;
	}
	public void setSjsfplzj(String[] sjsfplzj) {
		this.sjsfplzj = sjsfplzj;
	}
	public String[] getSjshjb() {
		return sjshjb;
	}
	public void setSjshjb(String[] sjshjb) {
		this.sjshjb = sjshjb;
	}
	public String[] getSjtj() {
		return sjtj;
	}
	public void setSjtj(String[] sjtj) {
		this.sjtj = sjtj;
	}
	public String[] getSjxzf() {
		return sjxzf;
	}
	public void setSjxzf(String[] sjxzf) {
		this.sjxzf = sjxzf;
	}
	public String[] getSjzd() {
		return sjzd;
	}
	public void setSjzd(String[] sjzd) {
		this.sjzd = sjzd;
	}
	public String getQueryequals_tabname() {
		return queryequals_tabname;
	}
	public void setQueryequals_tabname(String queryequals_tabname) {
		this.queryequals_tabname = queryequals_tabname;
	}
	public String getSave_bz() {
		return save_bz;
	}
	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}
	public String getSave_cxxs() {
		return save_cxxs;
	}
	public void setSave_cxxs(String save_cxxs) {
		this.save_cxxs = save_cxxs;
	}
	public String getSave_cxxspx() {
		return save_cxxspx;
	}
	public void setSave_cxxspx(String save_cxxspx) {
		this.save_cxxspx = save_cxxspx;
	}
	public String getSave_mkmc() {
		return save_mkmc;
	}
	public void setSave_mkmc(String save_mkmc) {
		this.save_mkmc = save_mkmc;
	}
	public String getSave_tabname() {
		return save_tabname;
	}
	public void setSave_tabname(String save_tabname) {
		this.save_tabname = save_tabname;
	}
	public String getSave_zdcd() {
		return save_zdcd;
	}
	public void setSave_zdcd(String save_zdcd) {
		this.save_zdcd = save_zdcd;
	}
	public String getSave_zdid() {
		return save_zdid;
	}
	public void setSave_zdid(String save_zdid) {
		this.save_zdid = save_zdid;
	}
	public String getSave_zdlx() {
		return save_zdlx;
	}
	public void setSave_zdlx(String save_zdlx) {
		this.save_zdlx = save_zdlx;
	}
	public String getSave_zdmc() {
		return save_zdmc;
	}
	public void setSave_zdmc(String save_zdmc) {
		this.save_zdmc = save_zdmc;
	}
	public String getSave_zdpx() {
		return save_zdpx;
	}
	public void setSave_zdpx(String save_zdpx) {
		this.save_zdpx = save_zdpx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCxxs() {
		return cxxs;
	}
	public void setCxxs(String cxxs) {
		this.cxxs = cxxs;
	}
	public String getCxxspx() {
		return cxxspx;
	}
	public void setCxxspx(String cxxspx) {
		this.cxxspx = cxxspx;
	}
	public String getMkmc() {
		return mkmc;
	}
	public void setMkmc(String mkmc) {
		this.mkmc = mkmc;
	}
	public String[] getOpid() {
		return opid;
	}
	public void setOpid(String[] opid) {
		this.opid = opid;
	}
	public String[] getOpmc() {
		return opmc;
	}
	public void setOpmc(String[] opmc) {
		this.opmc = opmc;
	}
	public String getTabname() {
		return tabname;
	}
	public void setTabname(String tabname) {
		this.tabname = tabname;
	}
	public String getZdcd() {
		return zdcd;
	}
	public void setZdcd(String zdcd) {
		this.zdcd = zdcd;
	}
	public String getZdid() {
		return zdid;
	}
	public void setZdid(String zdid) {
		this.zdid = zdid;
	}
	public String getZdlx() {
		return zdlx;
	}
	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}
	public String getZdmc() {
		return zdmc;
	}
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
	public String getZdpx() {
		return zdpx;
	}
	public void setZdpx(String zdpx) {
		this.zdpx = zdpx;
	}
	public String getSfnull() {
		return sfnull;
	}
	public void setSfnull(String sfnull) {
		this.sfnull = sfnull;
	}
	public String getSfnum() {
		return sfnum;
	}
	public void setSfnum(String sfnum) {
		this.sfnum = sfnum;
	}
	public String getMrf() {
		return mrf;
	}
	public void setMrf(String mrf) {
		this.mrf = mrf;
	}
	public String[] getSjmrf() {
		return sjmrf;
	}
	public void setSjmrf(String[] sjmrf) {
		this.sjmrf = sjmrf;
	}
	public String[] getSjzj() {
		return sjzj;
	}
	public void setSjzj(String[] sjzj) {
		this.sjzj = sjzj;
	}
	public String[] getSjzjz() {
		return sjzjz;
	}
	public void setSjzjz(String[] sjzjz) {
		this.sjzjz = sjzjz;
	}
	public String getZj() {
		return zj;
	}
	public void setZj(String zj) {
		this.zj = zj;
	}
	public String getZjz() {
		return zjz;
	}
	public void setZjz(String zjz) {
		this.zjz = zjz;
	}
	public String[] getSjsfwh() {
		return sjsfwh;
	}
	public void setSjsfwh(String[] sjsfwh) {
		this.sjsfwh = sjsfwh;
	} 
}
