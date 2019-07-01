package xgxt.pjpy.jhzy;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class JhzyPjpyForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205711105806100577L;

	private String nj;

	private String nd;

	private String xn;

	private String zydm;

	private String xydm;

	private String bjdm;

	private String xq;

	private String xh;

	private String xm;

	private String xb;

	private String[] dyf;

	private String[] xhV;

	private String[] xnV;

	private String[] xqV;

	private String[] tyf;
	
	private String[] jnf;

	private String dyfsx;

	private String zyfsx;

	private String tyfsx;

	private String jnfsx;

	private String jcfsx;
	
	//党总支部书记划分
	private String zgh;

	private String[] jfxm;

	private String[] fz;

	private String[] jfly;
	
	private String bmdm;
	
	private String fdyxm;
	
	private String [] zydms;
		
	//荣誉称号
    private String gnmk;
    private String brjl;
    private String zysj;
    private String zxqjhjqk;
    private String bz;
    private String sqsj;
    private String fdysh;
    private String xysh;
    private String xxsh;
    
    //评奖评优
    private String sqly;
    private String jtjjqk;
    private String pk;
    private String jfqk;
    private String cjqk;
    private String sjhm;
    private String xxjl;
    private String dnshjxj;
    private String zysjjhjqk;
    private String szlx;
	
	private String jxjdm;
	
	private String tjzd;
	
	private String tjmc;
	
	private String tjlx;
	
	private String tjz;
	
	private String cjlx;
	
	private String rychdm;
	
	private String zdm;
	
	private String tj;
	
	private String val;
	
	private String zdmval;
	
	private String sfyxbj;
	
	private String xyshyj;
	private String szxyj;
	private String lxdh;
    
	// 通用分页
	Pages pages = new Pages();

	
	
	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String[] getDyf() {
		return dyf;
	}

	public void setDyf(String[] dyf) {
		this.dyf = dyf;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getTyf() {
		return tyf;
	}

	public void setTyf(String[] tyf) {
		this.tyf = tyf;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

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

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getXhV() {
		return xhV;
	}

	public void setXhV(String[] xhV) {
		this.xhV = xhV;
	}

	public String[] getXnV() {
		return xnV;
	}

	public void setXnV(String[] xnV) {
		this.xnV = xnV;
	}

	public String[] getXqV() {
		return xqV;
	}

	public void setXqV(String[] xqV) {
		this.xqV = xqV;
	}

	public String[] getFz() {
		return fz;
	}

	public void setFz(String[] fz) {
		this.fz = fz;
	}

	public String[] getJfxm() {
		return jfxm;
	}

	public void setJfxm(String[] jfxm) {
		this.jfxm = jfxm;
	}

	public String[] getJfly() {
		return jfly;
	}

	public void setJfly(String[] jfly) {
		this.jfly = jfly;
	}

	

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getFdyxm() {
		return fdyxm;
	}

	public void setFdyxm(String fdyxm) {
		this.fdyxm = fdyxm;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String[] getZydms() {
		return zydms;
	}

	public void setZydms(String[] zydms) {
		this.zydms = zydms;
	}

	public String getSzlx() {
		return szlx;
	}

	public void setSzlx(String szlx) {
		this.szlx = szlx;
	}

	public String getTjzd() {
		return tjzd;
	}

	public void setTjzd(String tjzd) {
		this.tjzd = tjzd;
	}

	public String getTjmc() {
		return tjmc;
	}

	public void setTjmc(String tjmc) {
		this.tjmc = tjmc;
	}

	public String getTjlx() {
		return tjlx;
	}

	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}

	public String getTjz() {
		return tjz;
	}

	public void setTjz(String tjz) {
		this.tjz = tjz;
	}

	public String getCjlx() {
		return cjlx;
	}

	public void setCjlx(String cjlx) {
		this.cjlx = cjlx;
	}

	public String getRychdm() {
		return rychdm;
	}

	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getZdmval() {
		return zdmval;
	}

	public void setZdmval(String zdmval) {
		this.zdmval = zdmval;
	}

	public String getSfyxbj() {
		return sfyxbj;
	}

	public void setSfyxbj(String sfyxbj) {
		this.sfyxbj = sfyxbj;
	}
		
	public String getDyfsx() {
		return dyfsx;
	}

	public void setDyfsx(String dyfsx) {
		this.dyfsx = dyfsx;
	}

	public String getJcfsx() {
		return jcfsx;
	}

	public void setJcfsx(String jcfsx) {
		this.jcfsx = jcfsx;
	}

	public String getJnfsx() {
		return jnfsx;
	}

	public void setJnfsx(String jnfsx) {
		this.jnfsx = jnfsx;
	}

	public String getTyfsx() {
		return tyfsx;
	}

	public void setTyfsx(String tyfsx) {
		this.tyfsx = tyfsx;
	}

	public String getZyfsx() {
		return zyfsx;
	}

	public void setZyfsx(String zyfsx) {
		this.zyfsx = zyfsx;
	}
	public String getGnmk() {
		return gnmk;
	}

	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}

	public String getBrjl() {
		return brjl;
	}

	public void setBrjl(String brjl) {
		this.brjl = brjl;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getFdysh() {
		return fdysh;
	}

	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getZxqjhjqk() {
		return zxqjhjqk;
	}

	public void setZxqjhjqk(String zxqjhjqk) {
		this.zxqjhjqk = zxqjhjqk;
	}

	public String getZysj() {
		return zysj;
	}

	public void setZysj(String zysj) {
		this.zysj = zysj;
	}

	public String getJxjdm() {
		return jxjdm;
	}

	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}

	public String[] getJnf() {
		return jnf;
	}

	public void setJnf(String[] jnf) {
		this.jnf = jnf;
	}

	public String getJtjjqk() {
		return jtjjqk;
	}

	public void setJtjjqk(String jtjjqk) {
		this.jtjjqk = jtjjqk;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getJfqk() {
		return jfqk;
	}

	public void setJfqk(String jfqk) {
		this.jfqk = jfqk;
	}

	public String getCjqk() {
		return cjqk;
	}

	public void setCjqk(String cjqk) {
		this.cjqk = cjqk;
	}

	public String getDnshjxj() {
		return dnshjxj;
	}

	public void setDnshjxj(String dnshjxj) {
		this.dnshjxj = dnshjxj;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getXxjl() {
		return xxjl;
	}

	public void setXxjl(String xxjl) {
		this.xxjl = xxjl;
	}
	
	
	public String getZysjjhjqk() {
		return zysjjhjqk;
	}

	public void setZysjjhjqk(String zysjjhjqk) {
		this.zysjjhjqk = zysjjhjqk;
	}

	public String getSzxyj() {
		return szxyj;
	}

	public void setSzxyj(String szxyj) {
		this.szxyj = szxyj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
}
