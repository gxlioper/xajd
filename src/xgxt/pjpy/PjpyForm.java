package xgxt.pjpy;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class PjpyForm extends ActionForm{
    private static final long serialVersionUID = 0L;    
    private String xydm;
	private String zydm;
	private String bjdm;
	private String xh;
	private String xm;
	private String nj;
	private String ryjf;
	private String rzjf;
	private String bsjf;
	private String qsbsjf;
	private String cjhdjf;
	private String wjkf;
	private String jttbkf;
	private String qtjjfxx;
	private String hjjf;
	private String fkjf;
	private String kccjbjgkf;
	private String qtzykf;
	private String tybsjf;
	private String jnjf;	
	private String xn;
	private String xq;
	private String xb;
	private String csrq;
	private String rxrq;
	private String mzmc;
	private String xxmc;
	private String xymc;
	private String bjmc;
	private String zymc;
	private String xyshyj;
	private String xxshyj;
	//漳州师范
	private String zhkpzf1;
	private String zhkpzf2;
	private String zhkpzf3;
	private String zhkpzf4;
	private String zhkpbjpx1;
	private String zhkpbjpx2;
	private String zhkpbjpx3;
	private String zhkpbjpx4;
	private String zhkpzypx1;
	private String zhkpzypx2;
	private String zhkpzypx3;
	private String zhkpzypx4;
	
	private String bjcjpx1;
	private String bjcjpx2;
	private String bjcjpx3;
	private String bjcjpx4 ;
	private String zycjpx1;
	private String zycjpx2;
	private String zycjpx3;
	private String zycjpx4;
	
	private String csny;
	private String email;
	private String lxdh;
	private String zzmc;
	private String rxny;
	private String qzrq1;
	private String qzrq2;
	private String qzrq3;
	private String qzrq4;
	private String qzrq5;
	private String hdmc1;
	private String hdmc2;
	private String hdmc3;
	private String hdmc4;
	private String hdmc5;
	private String hjrq1;
	private String hjrq2;
	private String hjrq3;
	private String hjrq4;
	private String hjrq5;
	private String jxmc1;
	private String jxmc2;
	private String jxmc3;
	private String jxmc4;
	private String jxmc5;
	private String njrs;
	private String njpm;
	private String gnkw;
	private String gjkw;
	private String gnxshy;
	private String gjxshy;
	private String sci;
	private String ei;
	private String zl;
	private String kjjl;
	private String qt;
	private String zysj;
	private String baosteelshyj;
	private String zzmm;
	private String xscg;
	private String jthdbx;
	private String skxxbx;
	private String ldwsbx;
	private String xjxgbx;
	private String qtbx;
	private String zzxx;
	private String xxtd;
	private String ddxy;
	private String shhd;
	private String fzgn;
	private String gbrzjf;
	private String xfxfjf;
	private String jjsjjf;
	private String glbsjf;
	private String wjcljf;
	private String xmdm;
	private String zdm;
	private String cxcj;
	private String tycj; 
	private String pjcj;
	private String dyzf;
	private String tyjsdm;
	private String rwjsdm;
	private String zyjsdm;
	private String cxdf;
	private String zzmmmc;
		
	//以下这些字段主要用于	上海工程技术大学	上 海 地 铁 教 育 奖
	private String lwzzmc1;
	private String lwnf1;
	private String kwcbsmc1;
	private String pm1;
	private String ygsm1;
	
	private String lwzzmc2;
	private String lwnf2;
	private String kwcbsmc2;
	private String pm2;
	private String ygsm2;
	
	private String lwzzmc3;
	private String lwnf3;
	private String kwcbsmc3;
	private String pm3;
	private String ygsm3;
	
	private String lwzzmc4;
	private String lwnf4;
	private String kwcbsmc4;
	private String pm4;
	private String ygsm4;
	
	private String dsyj;
	private String xsgzbgsyj;
	private String shdtyj;
	
	//以下这些字段主要用于	上海工程技术大学	上海市高等学校优秀学生（索尼）奖学金评审表
	private String hjjl;
	private String pwyj;
	
	//以下这些字段主要用于 上海工程技术大学	交运奖学金
	private String sqly;
	private String beizhu;
	private String shjyyj;
	
	//以下这些字段主要用于 上海工程技术大学	上联奖学金
	private String hzjydw;
	
	private FormFile file;
	
	//安徽建工
	private String bjcjpx5;
	private String zycjpx5;
	private String zhkpzf5;
	private String zhkpbjpx5;
	private String zhkpzypx5;
	private String kh;
	private String bz;
	//浙江经贸职业技术学院  素质积分维护
	private String pjljf;
	private String cpcq;
	private String stsz;
	private String dlqk;
	private String hdqk;
	private String yydjjianf;
	private String jsjjianf;
	private String kyjf;
	private String zxksjf;
	private String yydjjf;
	private String jsjjf;
	private String qtjf;
	private String sxpdbx;
	private String pjcj1;
	private String pjcj2;
	private String pjcj3;
	private String pjcj4;
	private String sjhm;
	private String wysp;
	private String drzw;
	private String szmc1;
	private String szcj1;
	private String szmc2;
	private String szcj2;
	private String szmc3;
	private String szcj3;
	private String szmc4;
	private String szcj4;
	private String szmc5;
	private String szcj5;
	private String zhszcpcj;
	private String zhszcpcjpm;
	
	Pages pages = new Pages();
	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getSzcj1() {
		return szcj1;
	}

	public void setSzcj1(String szcj1) {
		this.szcj1 = szcj1;
	}

	public String getSzcj2() {
		return szcj2;
	}

	public void setSzcj2(String szcj2) {
		this.szcj2 = szcj2;
	}

	public String getSzcj3() {
		return szcj3;
	}

	public void setSzcj3(String szcj3) {
		this.szcj3 = szcj3;
	}

	public String getSzcj4() {
		return szcj4;
	}

	public void setSzcj4(String szcj4) {
		this.szcj4 = szcj4;
	}

	public String getSzcj5() {
		return szcj5;
	}

	public void setSzcj5(String szcj5) {
		this.szcj5 = szcj5;
	}

	public String getSzmc1() {
		return szmc1;
	}

	public void setSzmc1(String szmc1) {
		this.szmc1 = szmc1;
	}

	public String getSzmc2() {
		return szmc2;
	}

	public void setSzmc2(String szmc2) {
		this.szmc2 = szmc2;
	}

	public String getSzmc3() {
		return szmc3;
	}

	public void setSzmc3(String szmc3) {
		this.szmc3 = szmc3;
	}

	public String getSzmc4() {
		return szmc4;
	}

	public void setSzmc4(String szmc4) {
		this.szmc4 = szmc4;
	}

	public String getSzmc5() {
		return szmc5;
	}

	public void setSzmc5(String szmc5) {
		this.szmc5 = szmc5;
	}

	public String getZhszcpcj() {
		return zhszcpcj;
	}

	public void setZhszcpcj(String zhszcpcj) {
		this.zhszcpcj = zhszcpcj;
	}

	public String getZhszcpcjpm() {
		return zhszcpcjpm;
	}

	public void setZhszcpcjpm(String zhszcpcjpm) {
		this.zhszcpcjpm = zhszcpcjpm;
	}

	public String getDrzw() {
		return drzw;
	}

	public void setDrzw(String drzw) {
		this.drzw = drzw;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getWysp() {
		return wysp;
	}

	public void setWysp(String wysp) {
		this.wysp = wysp;
	}

	public String getBjcjpx5() {
		return bjcjpx5;
	}

	public void setBjcjpx5(String bjcjpx5) {
		this.bjcjpx5 = bjcjpx5;
	}

	public String getZhkpbjpx5() {
		return zhkpbjpx5;
	}

	public void setZhkpbjpx5(String zhkpbjpx5) {
		this.zhkpbjpx5 = zhkpbjpx5;
	}

	public String getZhkpzf5() {
		return zhkpzf5;
	}

	public void setZhkpzf5(String zhkpzf5) {
		this.zhkpzf5 = zhkpzf5;
	}

	public String getZhkpzypx5() {
		return zhkpzypx5;
	}

	public void setZhkpzypx5(String zhkpzypx5) {
		this.zhkpzypx5 = zhkpzypx5;
	}

	public String getZycjpx5() {
		return zycjpx5;
	}

	public void setZycjpx5(String zycjpx5) {
		this.zycjpx5 = zycjpx5;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getCxdf() {
		return cxdf;
	}

	public void setCxdf(String cxdf) {
		this.cxdf = cxdf;
	}

	public String getZyjsdm() {
		return zyjsdm;
	}

	public void setZyjsdm(String zyjsdm) {
		this.zyjsdm = zyjsdm;
	}

	public String getRwjsdm() {
		return rwjsdm;
	}

	public void setRwjsdm(String rwjsdm) {
		this.rwjsdm = rwjsdm;
	}

	public String getTyjsdm() {
		return tyjsdm;
	}

	public void setTyjsdm(String tyjsdm) {
		this.tyjsdm = tyjsdm;
	}

	public String getCxcj() {
		return cxcj;
	}

	public void setCxcj(String cxcj) {
		this.cxcj = cxcj;
	}

	public String getPjcj() {
		return pjcj;
	}

	public void setPjcj(String pjcj) {
		this.pjcj = pjcj;
	}

	public String getTycj() {
		return tycj;
	}

	public void setTycj(String tycj) {
		this.tycj = tycj;
	}

	public String getDyzf() {
		return dyzf;
	}

	public void setDyzf(String dyzf) {
		this.dyzf = dyzf;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXscg(){
		return this.xscg;
	}

	public void setXscg(String xscg){
		this.xscg = xscg;
	}
	
	public String getZzmm(){
		return this.zzmm;
	}

	public void setZzmm(String zzmm){
		this.zzmm = zzmm;
	}
	
	public String getCsny(){
		return this.csny;
	}

	public void setCsny(String csny){
		this.csny = csny;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getLxdh(){
		return this.lxdh;
	}

	public void setLxdh(String lxdh){
		this.lxdh = lxdh;
	}

	public String getZzmc(){
		return this.zzmc;
	}

	public void setZzmc(String zzmc){
		this.zzmc = zzmc;
	}


	public String getRxny(){
		return this.rxny;
	}

	public void setRxny(String rxny){
		this.rxny = rxny;
	}

	public String getQzrq1(){
		return this.qzrq1;
	}

	public void setQzrq1(String qzrq1){
		this.qzrq1 = qzrq1;
	}

	public String getQzrq2(){
		return this.qzrq2;
	}

	public void setQzrq2(String qzrq2){
		this.qzrq2 = qzrq2;
	}

	public String getQzrq3(){
		return this.qzrq3;
	}

	public void setQzrq3(String qzrq3){
		this.qzrq3 = qzrq3;
	}

	public String getQzrq4(){
		return this.qzrq4;
	}

	public void setQzrq4(String qzrq4){
		this.qzrq4 = qzrq4;
	}

	public String getQzrq5(){
		return this.qzrq5;
	}

	public void setQzrq5(String qzrq5){
		this.qzrq5 = qzrq5;
	}


	public String getHdmc1(){
		return this.hdmc1;
	}

	public void setHdmc1(String hdmc1){
		this.hdmc1 = hdmc1;
	}
	public String getHdmc2(){
		return this.hdmc2;
	}

	public void setHdmc2(String hdmc2){
		this.hdmc2 = hdmc2;
	}
	public String getHdmc3(){
		return this.hdmc3;
	}

	public void setHdmc3(String hdmc3){
		this.hdmc3 = hdmc3;
	}
	public String getHdmc4(){
		return this.hdmc4;
	}

	public void setHdmc4(String hdmc4){
		this.hdmc4 = hdmc4;
	}
	public String getHdmc5(){
		return this.hdmc5;
	}

	public void setHdmc5(String hdmc5){
		this.hdmc5 = hdmc5;
	}
	public String getHjrq1(){
		return this.hjrq1;
	}

	public void setHjrq1(String hjrq1){
		this.hjrq1 = hjrq1;
	}
	public String getHjrq2(){
		return this.hjrq2;
	}

	public void setHjrq2(String hjrq2){
		this.hjrq2 = hjrq2;
	}
	public String getHjrq3(){
		return this.hjrq3;
	}

	public void setHjrq3(String hjrq3){
		this.hjrq3 = hjrq3;
	}
	public String getHjrq4(){
		return this.hjrq4;
	}

	public void setHjrq4(String hjrq4){
		this.hjrq4 = hjrq4;
	}
	public String getHjrq5(){
		return this.hjrq5;
	}

	public void setHjrq5(String hjrq5){
		this.hjrq5 = hjrq5;
	}

	public String getJxmc1(){
		return this.jxmc1;
	}

	public void setJxmc1(String jxmc1){
		this.jxmc1 = jxmc1;
	}
	public String getJxmc2(){
		return this.jxmc2;
	}

	public void setJxmc2(String jxmc2){
		this.jxmc2 = jxmc2;
	}

	public String getJxmc3(){
		return this.jxmc3;
	}

	public void setJxmc3(String jxmc3){
		this.jxmc3 = jxmc3;
	}

	public String getJxmc4(){
		return this.jxmc4;
	}

	public void setJxmc4(String jxmc4){
		this.jxmc4 = jxmc4;
	}

	public String getJxmc5(){
		return this.jxmc5;
	}

	public void setJxmc5(String jxmc5){
		this.jxmc5 = jxmc5;
	}

	
	public String getNjrs(){
		return this.njrs;
	}
	
	public void setNjrs(String njrs){
		this.njrs = njrs;
	}
	
	public String getNjpm(){
		return this.njpm;
	}
	
	public void setNjpm(String njpm){
		this.njpm = njpm;
	}
	
	public String getGnkw(){
		return this.gnkw;
	}
	
	public void setGnkw(String gnkw){
		this.gnkw = gnkw;
	}
	
	public String getGjkw(){
		return this.gjkw;
	}
	
	public void setGjkw(String gjkw){
		this.gjkw = gjkw;
	}
	
	public String getGnxshy(){
		return this.gnxshy;
	}
	
	public void setGnxshy(String gnxshy){
		this.gnxshy = gnxshy;
	}
	
	public String getGjxshy(){
		return this.gjxshy;
	}
	
	public void setGjxshy(String gjxshy){
		this.gjxshy = gjxshy;
	}
	
	public String getSci(){
		return this.sci;
	}
	
	public void setSci(String sci){
		this.sci = sci;
	}
	
	public String getEi(){
		return this.ei;
	}
	
	public void setEi(String ei){
		this.ei = ei;
	}
	
	public String getZl(){
		return this.zl;
	}
	
	public void setZl(String zl){
		this.zl = zl;
	}
	
	
	public String getKjjl(){
		return this.kjjl;
	}
	
	public void setKjjl(String kjjl){
		this.kjjl = kjjl;
	}
	
	public String getQt(){
		return this.qt;
	}
	
	public void setQt(String qt){
		this.qt = qt;
	}
	
	public String getZysj(){
		return this.zysj;
	}
	
	public void setZysj(String zysj){
		this.zysj = zysj;
	}
	
	public String getBaosteelshyj(){
		return this.baosteelshyj;
	}
	
	public void setBaosteelshyj(String baosteelshyj){
		this.baosteelshyj = baosteelshyj;
	}
	
	public String getXxshyj(){
		return this.xxshyj;
	}
	
	public void setXxshyj(String xxshyj){
		this.xxshyj = xxshyj;
	}
	
	public String getXyshyj(){
		return this.xyshyj;
	}
	
	public void setXyshyj(String xyshyj){
		this.xyshyj = xyshyj;
	}
	
	public String getZymc(){
		return this.zymc;
	}
	
	public void setZymc(String zymc){
		this.zymc = zymc;
	}
	
	public String getBjmc(){
		return this.bjmc;
	}
	
	public void setBjmc(String bjmc){
		this.bjmc = bjmc;
	}
	
	public String getXymc(){
		return this.xymc;
	}
	
	public void setXymc(String xymc){
		this.xymc = xymc;
	}
	
	public String getXxmc(){
		return this.xxmc;
	}
	
	public void setXxmc(String xxmc){
		this.xxmc = xxmc;
	}
	
	public String getMzmc(){
		return this.mzmc;
	}
	
	public void setMzmc(String mzmc){
		this.mzmc = mzmc;
	}
	
	public String getRxrq(){
		return this.rxrq;
	}
	
	public void setRxrq(String rxrq){
		this.rxrq = rxrq;
	}
	
	public String getCsrq(){
		return this.csrq;
	}
	
	public void setCsrq(String csrq){
		this.csrq = csrq;
	}
	
	public String getXb(){
		return this.xb;
	}
	
	public void setXb(String xb){
		this.xb = xb;
	}
	
	public String getJnjf(){
		return this.jnjf;
	}
	
	public void setJnjf(String jnjf){
		this.jnjf = jnjf;
	}
	
	public String getTybsjf(){
		return this.tybsjf;
	}
	
	public void setTybsjf(String tybsjf){
		this.tybsjf = tybsjf;
	}
	
	public String getQtzykf(){
		return this.qtzykf;
	}
	
	public void setQtzykf(String qtzykf){
		this.qtzykf = qtzykf;
	}
	
	public String getKccjbjgkf(){
		return this.kccjbjgkf;
	}
	
	public void setKccjbjgkf(String kccjbjgkf){
		this.kccjbjgkf = kccjbjgkf;
	}
	
	public String getFkjf(){
		return this.fkjf;
	}
	
	public void setFkjf(String fkjf){
		this.fkjf = fkjf;
	}
	
	public String getHjjf(){
		return this.hjjf;
	}
	
	public void setHjjf(String hjjf){
		this.hjjf = hjjf;
	}
	
	public String getXq(){
		return this.xq;
	}
	
	public void setXq(String xq){
		this.xq = xq;
	}
	
	public String getXn(){
		return this.xn;
	}
	
	public void setXn(String xn){
		this.xn = xn;
	}
	
	public String getQsbsjf(){
		return this.qsbsjf;
	}
	
	public void setQsbsjf(String qsbsjf){
		this.qsbsjf = qsbsjf;
	}
	
	public String getCjhdjf(){
		return this.cjhdjf;
	}
	
	public void setCjhdjf(String cjhdjf){
		this.cjhdjf = cjhdjf;
	}
	
	public String getWjkf(){
		return this.wjkf;
	}
	
	public void setWjkf(String wjkf){
		this.wjkf = wjkf;
	}
	
	public String getJttbkf(){
		return this.jttbkf;
	}
	
	public void setJttbkf(String jttbkf){
		this.jttbkf = jttbkf;
	}
	
	public String getQtjjfxx(){
		return this.qtjjfxx;
	}
	
	public void setQtjjfxx(String qtjjfxx){
		this.qtjjfxx = qtjjfxx;
	}
	
	
	public String getRyjf(){
		return this.ryjf;
	}
	
	public void setRyjf(String ryjf){
		this.ryjf = ryjf;
	}
	
	public String getRzjf(){
		return this.rzjf;
	}
	
	public void setRzjf(String rzjf){
		this.rzjf = rzjf;
	}
	
	public String getBsjf(){
		return this.bsjf;
	}
	public void setBsjf(String bsjf){
		this.bsjf = bsjf;
	}
	
	public String getNj(){
		return this.nj;
	}
	public void setNj(String nj){
		this.nj = nj;
	}
	
	public String getXm(){
		return this.xm;
	}
	public void setXm(String xm){
		this.xm = xm;
	}
	
	public String getXh(){
		return this.xh;
	}
	public void setXh(String xh){
		this.xh = xh;
	}
	
	public String getBjdm(){
		return this.bjdm;
	}
	public void setBjdm(String bjdm){
		this.bjdm = bjdm;
	}
	
	public String getZydm(){
		return this.zydm;
	}
	public void setZydm(String zydm){
		this.zydm = zydm;
	}
	
	public String getXydm(){
		return this.xydm;
	}
	public void setXydm(String xydm){
		this.xydm = xydm;
	}

	public String getDdxy() {
		return ddxy;
	}

	public void setDdxy(String ddxy) {
		this.ddxy = ddxy;
	}

	public String getFzgn() {
		return fzgn;
	}

	public void setFzgn(String fzgn) {
		this.fzgn = fzgn;
	}

	public String getGbrzjf() {
		return gbrzjf;
	}

	public void setGbrzjf(String gbrzjf) {
		this.gbrzjf = gbrzjf;
	}

	public String getGlbsjf() {
		return glbsjf;
	}

	public void setGlbsjf(String glbsjf) {
		this.glbsjf = glbsjf;
	}

	public String getJjsjjf() {
		return jjsjjf;
	}

	public void setJjsjjf(String jjsjjf) {
		this.jjsjjf = jjsjjf;
	}

	public String getJthdbx() {
		return jthdbx;
	}

	public void setJthdbx(String jthdbx) {
		this.jthdbx = jthdbx;
	}

	public String getLdwsbx() {
		return ldwsbx;
	}

	public void setLdwsbx(String ldwsbx) {
		this.ldwsbx = ldwsbx;
	}

	public String getQtbx() {
		return qtbx;
	}

	public void setQtbx(String qtbx) {
		this.qtbx = qtbx;
	}

	public String getShhd() {
		return shhd;
	}

	public void setShhd(String shhd) {
		this.shhd = shhd;
	}

	public String getSkxxbx() {
		return skxxbx;
	}

	public void setSkxxbx(String skxxbx) {
		this.skxxbx = skxxbx;
	}

	public String getWjcljf() {
		return wjcljf;
	}

	public void setWjcljf(String wjcljf) {
		this.wjcljf = wjcljf;
	}

	public String getXfxfjf() {
		return xfxfjf;
	}

	public void setXfxfjf(String xfxfjf) {
		this.xfxfjf = xfxfjf;
	}

	public String getXjxgbx() {
		return xjxgbx;
	}

	public void setXjxgbx(String xjxgbx) {
		this.xjxgbx = xjxgbx;
	}

	public String getXxtd() {
		return xxtd;
	}

	public void setXxtd(String xxtd) {
		this.xxtd = xxtd;
	}

	public String getZzxx() {
		return zzxx;
	}

	public void setZzxx(String zzxx) {
		this.zzxx = zzxx;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDsyj() {
		return dsyj;
	}

	public void setDsyj(String dsyj) {
		this.dsyj = dsyj;
	}

	public String getKwcbsmc1() {
		return kwcbsmc1;
	}

	public void setKwcbsmc1(String kwcbsmc1) {
		this.kwcbsmc1 = kwcbsmc1;
	}

	public String getKwcbsmc2() {
		return kwcbsmc2;
	}

	public void setKwcbsmc2(String kwcbsmc2) {
		this.kwcbsmc2 = kwcbsmc2;
	}

	public String getKwcbsmc3() {
		return kwcbsmc3;
	}

	public void setKwcbsmc3(String kwcbsmc3) {
		this.kwcbsmc3 = kwcbsmc3;
	}

	public String getKwcbsmc4() {
		return kwcbsmc4;
	}

	public void setKwcbsmc4(String kwcbsmc4) {
		this.kwcbsmc4 = kwcbsmc4;
	}

	public String getLwnf1() {
		return lwnf1;
	}

	public void setLwnf1(String lwnf1) {
		this.lwnf1 = lwnf1;
	}

	public String getLwnf2() {
		return lwnf2;
	}

	public void setLwnf2(String lwnf2) {
		this.lwnf2 = lwnf2;
	}

	public String getLwnf3() {
		return lwnf3;
	}

	public void setLwnf3(String lwnf3) {
		this.lwnf3 = lwnf3;
	}

	public String getLwnf4() {
		return lwnf4;
	}

	public void setLwnf4(String lwnf4) {
		this.lwnf4 = lwnf4;
	}

	public String getLwzzmc1() {
		return lwzzmc1;
	}

	public void setLwzzmc1(String lwzzmc1) {
		this.lwzzmc1 = lwzzmc1;
	}

	public String getLwzzmc2() {
		return lwzzmc2;
	}

	public void setLwzzmc2(String lwzzmc2) {
		this.lwzzmc2 = lwzzmc2;
	}

	public String getLwzzmc3() {
		return lwzzmc3;
	}

	public void setLwzzmc3(String lwzzmc3) {
		this.lwzzmc3 = lwzzmc3;
	}

	public String getLwzzmc4() {
		return lwzzmc4;
	}

	public void setLwzzmc4(String lwzzmc4) {
		this.lwzzmc4 = lwzzmc4;
	}

	public String getPm1() {
		return pm1;
	}

	public void setPm1(String pm1) {
		this.pm1 = pm1;
	}

	public String getPm2() {
		return pm2;
	}

	public void setPm2(String pm2) {
		this.pm2 = pm2;
	}

	public String getPm3() {
		return pm3;
	}

	public void setPm3(String pm3) {
		this.pm3 = pm3;
	}

	public String getPm4() {
		return pm4;
	}

	public void setPm4(String pm4) {
		this.pm4 = pm4;
	}

	public String getShdtyj() {
		return shdtyj;
	}

	public void setShdtyj(String shdtyj) {
		this.shdtyj = shdtyj;
	}

	public String getXsgzbgsyj() {
		return xsgzbgsyj;
	}

	public void setXsgzbgsyj(String xsgzbgsyj) {
		this.xsgzbgsyj = xsgzbgsyj;
	}

	public String getYgsm1() {
		return ygsm1;
	}

	public void setYgsm1(String ygsm1) {
		this.ygsm1 = ygsm1;
	}

	public String getYgsm2() {
		return ygsm2;
	}

	public void setYgsm2(String ygsm2) {
		this.ygsm2 = ygsm2;
	}

	public String getYgsm3() {
		return ygsm3;
	}

	public void setYgsm3(String ygsm3) {
		this.ygsm3 = ygsm3;
	}

	public String getYgsm4() {
		return ygsm4;
	}

	public void setYgsm4(String ygsm4) {
		this.ygsm4 = ygsm4;
	}

	public String getHjjl() {
		return hjjl;
	}

	public void setHjjl(String hjjl) {
		this.hjjl = hjjl;
	}

	public String getPwyj() {
		return pwyj;
	}

	public void setPwyj(String pwyj) {
		this.pwyj = pwyj;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getShjyyj() {
		return shjyyj;
	}

	public void setShjyyj(String shjyyj) {
		this.shjyyj = shjyyj;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getHzjydw() {
		return hzjydw;
	}

	public void setHzjydw(String hzjydw) {
		this.hzjydw = hzjydw;
	}

	public String getZhkpbjpx1() {
		return zhkpbjpx1;
	}

	public void setZhkpbjpx1(String zhkpbjpx1) {
		this.zhkpbjpx1 = zhkpbjpx1;
	}

	public String getZhkpbjpx2() {
		return zhkpbjpx2;
	}

	public void setZhkpbjpx2(String zhkpbjpx2) {
		this.zhkpbjpx2 = zhkpbjpx2;
	}

	public String getZhkpbjpx3() {
		return zhkpbjpx3;
	}

	public void setZhkpbjpx3(String zhkpbjpx3) {
		this.zhkpbjpx3 = zhkpbjpx3;
	}

	public String getZhkpbjpx4() {
		return zhkpbjpx4;
	}

	public void setZhkpbjpx4(String zhkpbjpx4) {
		this.zhkpbjpx4 = zhkpbjpx4;
	}

	public String getZhkpzf1() {
		return zhkpzf1;
	}

	public void setZhkpzf1(String zhkpzf1) {
		this.zhkpzf1 = zhkpzf1;
	}

	public String getZhkpzf2() {
		return zhkpzf2;
	}

	public void setZhkpzf2(String zhkpzf2) {
		this.zhkpzf2 = zhkpzf2;
	}

	public String getZhkpzf3() {
		return zhkpzf3;
	}

	public void setZhkpzf3(String zhkpzf3) {
		this.zhkpzf3 = zhkpzf3;
	}

	public String getZhkpzf4() {
		return zhkpzf4;
	}

	public void setZhkpzf4(String zhkpzf4) {
		this.zhkpzf4 = zhkpzf4;
	}

	public String getZhkpzypx1() {
		return zhkpzypx1;
	}

	public void setZhkpzypx1(String zhkpzypx1) {
		this.zhkpzypx1 = zhkpzypx1;
	}

	public String getZhkpzypx2() {
		return zhkpzypx2;
	}

	public void setZhkpzypx2(String zhkpzypx2) {
		this.zhkpzypx2 = zhkpzypx2;
	}

	public String getZhkpzypx3() {
		return zhkpzypx3;
	}

	public void setZhkpzypx3(String zhkpzypx3) {
		this.zhkpzypx3 = zhkpzypx3;
	}

	public String getZhkpzypx4() {
		return zhkpzypx4;
	}

	public void setZhkpzypx4(String zhkpzypx4) {
		this.zhkpzypx4 = zhkpzypx4;
	}

	public String getBjcjpx1() {
		return bjcjpx1;
	}

	public void setBjcjpx1(String bjcjpx1) {
		this.bjcjpx1 = bjcjpx1;
	}

	public String getBjcjpx2() {
		return bjcjpx2;
	}

	public void setBjcjpx2(String bjcjpx2) {
		this.bjcjpx2 = bjcjpx2;
	}

	public String getBjcjpx3() {
		return bjcjpx3;
	}

	public void setBjcjpx3(String bjcjpx3) {
		this.bjcjpx3 = bjcjpx3;
	}

	public String getBjcjpx4() {
		return bjcjpx4;
	}

	public void setBjcjpx4(String bjcjpx4) {
		this.bjcjpx4 = bjcjpx4;
	}

	public String getZycjpx1() {
		return zycjpx1;
	}

	public void setZycjpx1(String zycjpx1) {
		this.zycjpx1 = zycjpx1;
	}

	public String getZycjpx2() {
		return zycjpx2;
	}

	public void setZycjpx2(String zycjpx2) {
		this.zycjpx2 = zycjpx2;
	}

	public String getZycjpx3() {
		return zycjpx3;
	}

	public void setZycjpx3(String zycjpx3) {
		this.zycjpx3 = zycjpx3;
	}

	public String getZycjpx4() {
		return zycjpx4;
	}

	public void setZycjpx4(String zycjpx4) {
		this.zycjpx4 = zycjpx4;
	}

	public String getZzmmmc() {
		return zzmmmc;
	}

	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}

	public String getCpcq() {
		return cpcq;
	}

	public void setCpcq(String cpcq) {
		this.cpcq = cpcq;
	}

	public String getDlqk() {
		return dlqk;
	}

	public void setDlqk(String dlqk) {
		this.dlqk = dlqk;
	}

	public String getHdqk() {
		return hdqk;
	}

	public void setHdqk(String hdqk) {
		this.hdqk = hdqk;
	}

	public String getJsjjf() {
		return jsjjf;
	}

	public void setJsjjf(String jsjjf) {
		this.jsjjf = jsjjf;
	}

	public String getJsjjianf() {
		return jsjjianf;
	}

	public void setJsjjianf(String jsjjianf) {
		this.jsjjianf = jsjjianf;
	}

	public String getKyjf() {
		return kyjf;
	}

	public void setKyjf(String kyjf) {
		this.kyjf = kyjf;
	}

	public String getPjljf() {
		return pjljf;
	}

	public void setPjljf(String pjljf) {
		this.pjljf = pjljf;
	}

	public String getQtjf() {
		return qtjf;
	}

	public void setQtjf(String qtjf) {
		this.qtjf = qtjf;
	}

	public String getStsz() {
		return stsz;
	}

	public void setStsz(String stsz) {
		this.stsz = stsz;
	}

	public String getSxpdbx() {
		return sxpdbx;
	}

	public void setSxpdbx(String sxpdbx) {
		this.sxpdbx = sxpdbx;
	}

	public String getYydjjf() {
		return yydjjf;
	}

	public void setYydjjf(String yydjjf) {
		this.yydjjf = yydjjf;
	}

	public String getYydjjianf() {
		return yydjjianf;
	}

	public void setYydjjianf(String yydjjianf) {
		this.yydjjianf = yydjjianf;
	}

	public String getZxksjf() {
		return zxksjf;
	}

	public void setZxksjf(String zxksjf) {
		this.zxksjf = zxksjf;
	}

	public String getPjcj1() {
		return pjcj1;
	}

	public void setPjcj1(String pjcj1) {
		this.pjcj1 = pjcj1;
	}

	public String getPjcj2() {
		return pjcj2;
	}

	public void setPjcj2(String pjcj2) {
		this.pjcj2 = pjcj2;
	}

	public String getPjcj3() {
		return pjcj3;
	}

	public void setPjcj3(String pjcj3) {
		this.pjcj3 = pjcj3;
	}

	public String getPjcj4() {
		return pjcj4;
	}

	public void setPjcj4(String pjcj4) {
		this.pjcj4 = pjcj4;
	}

	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}
