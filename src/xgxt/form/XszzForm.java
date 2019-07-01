/*
 * 创建日期 2007-1-12 bat_zzj
 *
 */
package xgxt.form;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**学生资助*/
public class XszzForm extends ActionForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xn;

    private String nd;

    private String xq;

    private String xydm;

    private String zydm;

    private String bjdm;

    private String xh;

    private String shzt;
    
    private String shxmdm; //审核项目
    
    private String nj;
    
    private String csny;
    
    private String xb;
    
    private String rxny;
    
    private String mzmc;
    
    private String xxmc;
    
    private String xy;
    
    private String xmc;
    
    private String bjmc;
    
    private String jlxx;///奖励信息
    
    private String radJthk;//户口(城镇、农村)
    
    private String hkrs;//户口人数
    
    private String hth;//合同号
    
    private String yzbm;
    
    private String jtzz;//家庭住址
    
    private String xm;
    
    private String jtyzsr;//家庭月总收入
    
    private String jtrjsr;//家庭人均收入
    
    private String jtsrly;//家庭收入来源
    
    private String lsknbzdm;//临时困难补助代码
    
    private String zxbzdm;//专项补助代码
    
    private String wszxjdm;//外设助学金代码
    
    private String sfgdhkfs;//是否固定还款方式
    
    private String yhmc;//银行名称
    
    private String hkqx;//银行期限
    
    private String zhmc;//支行名称
    
    private String gdhkfsdm;//固定还款方式代码
    
    private String hkfsmc1;//还款方式名称1
    
    private String hkfsmc2;//还款方式名称2
    
    private String hkfsmc3;//还款方式名称3
    
    private String hkfsmc4;//还款方式名称4
    
    private String hkfsmc5;//还款方式名称5
    
    private String hkfsmc6;//还款方式名称6
    
    private String hkfsmc7;//还款方式名称7
    
    private String hkfsmc8;//还款方式名称8
    
    private String hkfsmc9;//还款方式名称9
    
    private String hkfsmc10;//还款方式名称10
    
    private String hkfsdm;//还款方式代码
    
    private String sfzh;//身份证号
    
    private String csrq;//出身日期
    
    private String xl;//学历
    
    private String xymc;//学院名称
    
    private String lxdh;//联系电话
    
    private String hyzk;//婚姻状况
    
    private String poxm;//配偶姓名
    
    private String gzdw;//工作单位
    
    private String dwdh;//单位电话
    
    private String dwdz;//单位地址
    
    private String dwyzbm;//单位邮政编码
    
    private String yddh;//移动电话
    
    private String email;//E-MAIL
    
    private String lxrxm;//联系人姓名
    
    private String lxrxb;//联系人性别
    
    private String lxrcsrq;//联系人出身日期
    
    private String lxrgx;//联系人与贷款人关系
    
    private String lxrdh;//联系人电话
    
    private String lxrdwdz;//联系人工作单位
    
    private String jtxxzz;//家庭详细住址
    
    private String jtyzbm;//家庭邮政编码
    
    private String jtlxdh;//家庭联系电话
    
    private String fqxm;//父亲姓名
    
    private String mqxm;//母亲姓名
    
    private String fqzy;//父亲职业
    
    private String mqzy;//母亲职业
    
    private String fqsfzh;//父亲身份证号
    
    private String mqsfzh;//母亲身份证号
    
    private String hkfs1;//还款方式1
    
    private String lxsj;//离校时间
    
    private String lxyy;//离校原因
    
    private String hkkssj;//还款开始时间
    
    private String hkjssj;//还款结束时间
    
    private String hkcs;//还款次数
    
    private String zffm;//帐户户名
    
    private String zfh;//帐户号
    
    private String bz;//备注
    
    private String zhfkrq;//最后放款日期
    
    private String fkzje;//放款总金额
    
    private String xmdm;//项目代码
    
    private String gnmk;
    
    private String sbsj;
    
    private String yesNo;
    
    private String zzdjdm;
    
    private String jxjdm1;
    
    private String selectTab;
    
    private String disable;
    
    private String xysh;
    
    private String xxsh;
    
    private String jb;//级别
    
    private String shlb;//审核类别
    
    private String kncddm;//困难程度代码
    

	public String getShxmdm() {
		return shxmdm;
	}

	public void setShxmdm(String shxmdm) {
		this.shxmdm = shxmdm;
	}

	public String getKncddm() {
		return kncddm;
	}

	public void setKncddm(String kncddm) {
		this.kncddm = kncddm;
	}

	public String getShlb() {
		return shlb;
	}

	public void setShlb(String shlb) {
		this.shlb = shlb;
	}

	public String getJb() {
		return jb;
	}

	public void setJb(String jb) {
		this.jb = jb;
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

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String getSelectTab() {
		return selectTab;
	}

	public void setSelectTab(String selectTab) {
		this.selectTab = selectTab;
	}

	public String getJxjdm1() {
		return jxjdm1;
	}

	public void setJxjdm1(String jxjdm1) {
		this.jxjdm1 = jxjdm1;
	}

	public String getZzdjdm() {
		return zzdjdm;
	}

	public void setZzdjdm(String zzdjdm) {
		this.zzdjdm = zzdjdm;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getFkzje() {
		return fkzje;
	}

	public void setFkzje(String fkzje) {
		this.fkzje = fkzje;
	}

	public String getZhfkrq() {
		return zhfkrq;
	}

	public void setZhfkrq(String zhfkrq) {
		this.zhfkrq = zhfkrq;
	}

	public String getHkfsdm() {
		return hkfsdm;
	}

	public void setHkfsdm(String hkfsdm) {
		this.hkfsdm = hkfsdm;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getSfgdhkfs() {
		return sfgdhkfs;
	}

	public void setSfgdhkfs(String sfgdhkfs) {
		this.sfgdhkfs = sfgdhkfs;
	}

	public String getWszxjdm(){
    	return wszxjdm;
    }
    
    public void setWszxjdm(String wszxjdm){
    	this.wszxjdm = wszxjdm;
    }
    
    public String getZxbzdm(){
    	return zxbzdm;
    }
    
    public void setZxbzdm(String zxbzdm){
    	this.zxbzdm = zxbzdm;
    }
    
    public String getLsknbzdm(){
    	return lsknbzdm;
    }
    
    public void setLsknbzdm(String lsknbzdm){
    	this.lsknbzdm = lsknbzdm;
    }
    
    public String getJtsrly(){
    	return jtsrly;
    }
    
    public void setJtsrly(String jtsrly){
    	this.jtsrly = jtsrly;
    } 
    
    public String getJtrjsr(){
    	return jtrjsr;
    }
    
    public void setJtrjsr(String jtrjsr){
    	this.jtrjsr = jtrjsr;
    }     
    
    public String getJtyzsr(){
    	return jtyzsr;
    }
    
    public void setJtyzsr(String jtyzsr){
    	this.jtyzsr = jtyzsr;
    } 
    
    public String getXy(){
    	return xy;
    }
    
    public void setXy(String xy){
    	this.xy = xy;
    }    
    
    public String getXm(){
    	return xm;
    }
    
    public void setXm(String xm){
    	this.xm = xm;
    }
    
    public String getJtzz(){
    	return jtzz;
    }
    
    public void setJtzz(String jtzz){
    	this.jtzz = jtzz;
    }
    
    public String getYzbm(){
    	return yzbm;
    }
    
    public void setYzbm(String yzbm){
    	this.yzbm = yzbm;
    }
    
    public String getHkrs(){
    	return hkrs;
    }
    
    public void setHkrs(String hkrs){
    	this.hkrs = hkrs;
    }
    
    public String getRadJthk(){
    	return radJthk;
    }
    
    public void setRadJthk(String radJthk){
    	this.radJthk = radJthk;
    }
    
    public String getJlxx(){
    	return jlxx;
    }
    
    public void setJlxx(String jlxx){
    	this.jlxx = jlxx;
    }
    
    public String getBjmc(){
    	return bjmc;
    }
    
    public void setBjmc(String bjmc){
    	this.bjmc = bjmc;
    }
    
    public String getXmc(){
    	return xmc;
    }
    
    public void setXmc(String xmc){
    	this.xmc = xmc;
    }
    
    public String getXxmc(){
    	return xxmc;
    }
    
    public void setXxmc(String xxmc){
    	this.xxmc = xxmc;
    }
    
    public String getMz(){
    	return mzmc;
    }
    
    public void setMz(String mzmc){
    	this.mzmc = mzmc;
    }
    
    public String getRxny(){
    	return rxny;
    }
    
    public void setRxny(String rxny){
    	this.rxny = rxny;
    }
    
    public String getXb(){
    	return xb;
    }
    
    public void setXb(String xb){
    	this.xb = xb;
    }
    
    public String getCsny(){
    	return csny;
    }
    
    public void setCsny(String csny){
    	this.csny = csny;
    }
    
    public String getNd() {
	return nd;
    }

    public void setNd(String nd) {
	this.nd = nd;
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

    public String getBjdm() {
	return bjdm;
    }

    public void setBjdm(String bjdm) {
	this.bjdm = bjdm;
    }

    public String getXh() {
	return xh;
    }

    public void setXh(String xh) {
	this.xh = xh;
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

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getNj() {
        return nj;
    }

    public void setNj(String nj) {
        this.nj = nj;
    }

	public String getHth() {
		return hth;
	}

	public void setHth(String hth) {
		this.hth = hth;
	}

	public String getMzmc() {
		return mzmc;
	}

	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}

	public String getGdhkfsdm() {
		return gdhkfsdm;
	}

	public void setGdhkfsdm(String gdhkfsdm) {
		this.gdhkfsdm = gdhkfsdm;
	}

	public String getHkfsmc10() {
		return hkfsmc10;
	}

	public void setHkfsmc10(String hkfsmc10) {
		this.hkfsmc10 = hkfsmc10;
	}

	public String getHkfsmc1() {
		return hkfsmc1;
	}

	public void setHkfsmc1(String hkfsmc1) {
		this.hkfsmc1 = hkfsmc1;
	}

	public String getHkfsmc2() {
		return hkfsmc2;
	}

	public void setHkfsmc2(String hkfsmc2) {
		this.hkfsmc2 = hkfsmc2;
	}

	public String getHkfsmc3() {
		return hkfsmc3;
	}

	public void setHkfsmc3(String hkfsmc3) {
		this.hkfsmc3 = hkfsmc3;
	}

	public String getHkfsmc4() {
		return hkfsmc4;
	}

	public void setHkfsmc4(String hkfsmc4) {
		this.hkfsmc4 = hkfsmc4;
	}

	public String getHkfsmc5() {
		return hkfsmc5;
	}

	public void setHkfsmc5(String hkfsmc5) {
		this.hkfsmc5 = hkfsmc5;
	}

	public String getHkfsmc6() {
		return hkfsmc6;
	}

	public void setHkfsmc6(String hkfsmc6) {
		this.hkfsmc6 = hkfsmc6;
	}

	public String getHkfsmc7() {
		return hkfsmc7;
	}

	public void setHkfsmc7(String hkfsmc7) {
		this.hkfsmc7 = hkfsmc7;
	}

	public String getHkfsmc8() {
		return hkfsmc8;
	}

	public void setHkfsmc8(String hkfsmc8) {
		this.hkfsmc8 = hkfsmc8;
	}

	public String getHkfsmc9() {
		return hkfsmc9;
	}

	public void setHkfsmc9(String hkfsmc9) {
		this.hkfsmc9 = hkfsmc9;
	}

	public String getHkqx() {
		return hkqx;
	}

	public void setHkqx(String hkqx) {
		this.hkqx = hkqx;
	}

	public String getZhmc() {
		return zhmc;
	}

	public void setZhmc(String zhmc) {
		this.zhmc = zhmc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getDwdh() {
		return dwdh;
	}

	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}

	public String getDwdz() {
		return dwdz;
	}

	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}

	public String getDwyzbm() {
		return dwyzbm;
	}

	public void setDwyzbm(String dwyzbm) {
		this.dwyzbm = dwyzbm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFqsfzh() {
		return fqsfzh;
	}

	public void setFqsfzh(String fqsfzh) {
		this.fqsfzh = fqsfzh;
	}

	public String getFqxm() {
		return fqxm;
	}

	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}

	public String getFqzy() {
		return fqzy;
	}

	public void setFqzy(String fqzy) {
		this.fqzy = fqzy;
	}

	public String getGzdw() {
		return gzdw;
	}

	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}

	public String getHkcs() {
		return hkcs;
	}

	public void setHkcs(String hkcs) {
		this.hkcs = hkcs;
	}

	public String getHkfs1() {
		return hkfs1;
	}

	public void setHkfs1(String hkfs1) {
		this.hkfs1 = hkfs1;
	}

	public String getHkjssj() {
		return hkjssj;
	}

	public void setHkjssj(String hkjssj) {
		this.hkjssj = hkjssj;
	}

	public String getHkkssj() {
		return hkkssj;
	}

	public void setHkkssj(String hkkssj) {
		this.hkkssj = hkkssj;
	}

	public String getHyzk() {
		return hyzk;
	}

	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}

	public String getJtlxdh() {
		return jtlxdh;
	}

	public void setJtlxdh(String jtlxdh) {
		this.jtlxdh = jtlxdh;
	}

	public String getJtxxzz() {
		return jtxxzz;
	}

	public void setJtxxzz(String jtxxzz) {
		this.jtxxzz = jtxxzz;
	}

	public String getJtyzbm() {
		return jtyzbm;
	}

	public void setJtyzbm(String jtyzbm) {
		this.jtyzbm = jtyzbm;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getLxrcsrq() {
		return lxrcsrq;
	}

	public void setLxrcsrq(String lxrcsrq) {
		this.lxrcsrq = lxrcsrq;
	}

	public String getLxrdh() {
		return lxrdh;
	}

	public void setLxrdh(String lxrdh) {
		this.lxrdh = lxrdh;
	}

	public String getLxrdwdz() {
		return lxrdwdz;
	}

	public void setLxrdwdz(String lxrdwdz) {
		this.lxrdwdz = lxrdwdz;
	}

	public String getLxrgx() {
		return lxrgx;
	}

	public void setLxrgx(String lxrgx) {
		this.lxrgx = lxrgx;
	}

	public String getLxrxb() {
		return lxrxb;
	}

	public void setLxrxb(String lxrxb) {
		this.lxrxb = lxrxb;
	}

	public String getLxrxm() {
		return lxrxm;
	}

	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}

	public String getLxsj() {
		return lxsj;
	}

	public void setLxsj(String lxsj) {
		this.lxsj = lxsj;
	}

	public String getLxyy() {
		return lxyy;
	}

	public void setLxyy(String lxyy) {
		this.lxyy = lxyy;
	}

	public String getMqsfzh() {
		return mqsfzh;
	}

	public void setMqsfzh(String mqsfzh) {
		this.mqsfzh = mqsfzh;
	}

	public String getMqxm() {
		return mqxm;
	}

	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}

	public String getMqzy() {
		return mqzy;
	}

	public void setMqzy(String mqzy) {
		this.mqzy = mqzy;
	}

	public String getPoxm() {
		return poxm;
	}

	public void setPoxm(String poxm) {
		this.poxm = poxm;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getYddh() {
		return yddh;
	}

	public void setYddh(String yddh) {
		this.yddh = yddh;
	}

	public String getZffm() {
		return zffm;
	}

	public void setZffm(String zffm) {
		this.zffm = zffm;
	}

	public String getZfh() {
		return zfh;
	}

	public void setZfh(String zfh) {
		this.zfh = zfh;
	}

	public String getGnmk() {
		return gnmk;
	}

	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}

	public String getYesNo() {
		return yesNo;
	}

	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}

	private Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
