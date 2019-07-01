
package xgxt.xszz.zgmsxy;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国美术学院国家助学贷款MODEL</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-06-01</p>
 */
public class GjzxdkModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nd;
	private String xh;
	private String xm;
	private String xb;
	private String nj;
	private String xydm;
	private String xymc;
	private String zydm;
	private String zymc;
	private String bjdm;
	private String bjmc;
	private String mzmc;
	private String zzmmmc;
	private String sfzh;
	private String lxdh;
	private String jtzz;
	private String yzbm;
	private String fqxm;
	private String fqgzdw;
	private String fqysr ;
	private String fqdh;
	private String mqxm;
	private String mqgzdw;
	private String mqysr ;
	private String mqdh;
	private String brjyqxhdw ;
	private String jtgddh;
	private String brdzyxjdzlxfs ;
	private String dqgzdwjdz ;
	private String dqgzdwyb;
	private String dqgzdwdh;
	private String lxfsbgqk;
	private String sqsj;
	private String sqje;
	private String xysh;
	private String xyshyj;
	private String xxsh;
	private String xxshyj;
	private String hth ;
	private String dkqx;
	private String htffje;
	private String sjffrq;
	private String dkzl;
	private String htye;
	private String grzhdkzh;
	private String hzdwmc;
	private String jqbj;
	private String jqlx;
	private String zhkzh ;
	private String dkxt;
	private String wyys;
	private String schkr ;
	
	private String dkyh;//贷款银行
	
	private String dknx;//贷款年限
	
	private String dkyhdd;//贷款银行地点
	//---  学生申请-学生申请
	private String fqsfzh;//父亲身份证号
	private String mqsfzh;//母亲身份证号
	private String lxdh2;//联系电话2
	private String lxdh3;//联系电话3
	private String sfzyxzzrq;//身份证有效截止日期（格式：20120606）
	private String dkhj;//贷款金额
	private String xq1;//第一学期
	private String xq2;//第二学期
	private String xq3;//第三学期
	private String xq4;//第四学期
	private String xq5;//第五学期
	private String xfje;//学费金额
	private String zsfje;//住宿费金额
	private String qq;//QQ
	private String email;//Email
	private String yxsh;//院系审核
	private String yxshyj;//院系审核意见
	
	//江西理工大学个性化begin
	private String dkxn;//贷款学年
	private String xf;//学费
	private String zsf;//住宿费
	//江西理工大学个性化end
	
	private String jzrxm;     //见证人姓名
	private String jzrlxdh;     //见证人联系电话
	private String yjzrgx;     //与见证人关系
	private String jzrzz;     //见证人住址
	private String dyxnzsf;     //第一学年住宿费
	private String dexnshf;     //第二学年生活费
	private String dsixnzsf;     //第四学年住宿费
	private String dsixnshf;     //第四学年生活费
	private String dexnzsf;     //第二学年住宿费
	private String dyxnshf;     //第一学年生活费
	private String dsanxnshf;     //第三学年生活费
	private String dsanxnzsf;     //第三学年住宿费
	private String dwuxnshf;     //第五学年生活费
	private String dwuxnzsf;     //第五学年住宿费
	
	private String dyxnxf;    // 第一学年学费
	private String dexnxf;    // 第二学年学费
	private String dsanxnxf;  // 第三学年学费
	private String dsixnxf;   // 第四学年学费
	private String dwuxnxf;   // 第五学年学费
	
	
	public String getDexnxf() {
		return dexnxf;
	}
	public void setDexnxf(String dexnxf) {
		this.dexnxf = dexnxf;
	}
	public String getDsanxnxf() {
		return dsanxnxf;
	}
	public void setDsanxnxf(String dsanxnxf) {
		this.dsanxnxf = dsanxnxf;
	}
	public String getDsixnxf() {
		return dsixnxf;
	}
	public void setDsixnxf(String dsixnxf) {
		this.dsixnxf = dsixnxf;
	}
	public String getDwuxnxf() {
		return dwuxnxf;
	}
	public void setDwuxnxf(String dwuxnxf) {
		this.dwuxnxf = dwuxnxf;
	}
	public String getDyxnxf() {
		return dyxnxf;
	}
	public void setDyxnxf(String dyxnxf) {
		this.dyxnxf = dyxnxf;
	}
	public String getDexnshf() {
		return dexnshf;
	}
	public void setDexnshf(String dexnshf) {
		this.dexnshf = dexnshf;
	}
	public String getDexnzsf() {
		return dexnzsf;
	}
	public void setDexnzsf(String dexnzsf) {
		this.dexnzsf = dexnzsf;
	}
	public String getDsanxnshf() {
		return dsanxnshf;
	}
	public void setDsanxnshf(String dsanxnshf) {
		this.dsanxnshf = dsanxnshf;
	}
	public String getDsixnshf() {
		return dsixnshf;
	}
	public void setDsixnshf(String dsixnshf) {
		this.dsixnshf = dsixnshf;
	}
	public String getDsixnzsf() {
		return dsixnzsf;
	}
	public void setDsixnzsf(String dsixnzsf) {
		this.dsixnzsf = dsixnzsf;
	}
	public String getDyxnshf() {
		return dyxnshf;
	}
	public void setDyxnshf(String dyxnshf) {
		this.dyxnshf = dyxnshf;
	}
	public String getDyxnzsf() {
		return dyxnzsf;
	}
	public void setDyxnzsf(String dyxnzsf) {
		this.dyxnzsf = dyxnzsf;
	}
	public String getJzrlxdh() {
		return jzrlxdh;
	}
	public void setJzrlxdh(String jzrlxdh) {
		this.jzrlxdh = jzrlxdh;
	}
	public String getJzrxm() {
		return jzrxm;
	}
	public void setJzrxm(String jzrxm) {
		this.jzrxm = jzrxm;
	}
	public String getJzrzz() {
		return jzrzz;
	}
	public void setJzrzz(String jzrzz) {
		this.jzrzz = jzrzz;
	}
	public String getYjzrgx() {
		return yjzrgx;
	}
	public void setYjzrgx(String yjzrgx) {
		this.yjzrgx = yjzrgx;
	}
	public String getDknx() {
		return dknx;
	}
	public void setDknx(String dknx) {
		this.dknx = dknx;
	}
	public String getDkyh() {
		return dkyh;
	}
	public void setDkyh(String dkyh) {
		this.dkyh = dkyh;
	}
	public String getDkyhdd() {
		return dkyhdd;
	}
	public void setDkyhdd(String dkyhdd) {
		this.dkyhdd = dkyhdd;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getBrdzyxjdzlxfs() {
		return brdzyxjdzlxfs;
	}
	public void setBrdzyxjdzlxfs(String brdzyxjdzlxfs) {
		this.brdzyxjdzlxfs = brdzyxjdzlxfs;
	}
	public String getBrjyqxhdw() {
		return brjyqxhdw;
	}
	public void setBrjyqxhdw(String brjyqxhdw) {
		this.brjyqxhdw = brjyqxhdw;
	}
	public String getDkqx() {
		return dkqx;
	}
	public void setDkqx(String dkqx) {
		this.dkqx = dkqx;
	}
	public String getDkxt() {
		return dkxt;
	}
	public void setDkxt(String dkxt) {
		this.dkxt = dkxt;
	}
	public String getDkzl() {
		return dkzl;
	}
	public void setDkzl(String dkzl) {
		this.dkzl = dkzl;
	}
	public String getDqgzdwdh() {
		return dqgzdwdh;
	}
	public void setDqgzdwdh(String dqgzdwdh) {
		this.dqgzdwdh = dqgzdwdh;
	}
	public String getDqgzdwjdz() {
		return dqgzdwjdz;
	}
	public void setDqgzdwjdz(String dqgzdwjdz) {
		this.dqgzdwjdz = dqgzdwjdz;
	}
	public String getDqgzdwyb() {
		return dqgzdwyb;
	}
	public void setDqgzdwyb(String dqgzdwyb) {
		this.dqgzdwyb = dqgzdwyb;
	}
	public String getFqdh() {
		return fqdh;
	}
	public void setFqdh(String fqdh) {
		this.fqdh = fqdh;
	}
	public String getFqgzdw() {
		return fqgzdw;
	}
	public void setFqgzdw(String fqgzdw) {
		this.fqgzdw = fqgzdw;
	}
	public String getFqxm() {
		return fqxm;
	}
	public void setFqxm(String fqxm) {
		this.fqxm = fqxm;
	}
	public String getFqysr() {
		return fqysr;
	}
	public void setFqysr(String fqysr) {
		this.fqysr = fqysr;
	}
	public String getGrzhdkzh() {
		return grzhdkzh;
	}
	public void setGrzhdkzh(String grzhdkzh) {
		this.grzhdkzh = grzhdkzh;
	}
	public String getHtffje() {
		return htffje;
	}
	public void setHtffje(String htffje) {
		this.htffje = htffje;
	}
	public String getHth() {
		return hth;
	}
	public void setHth(String hth) {
		this.hth = hth;
	}
	public String getHtye() {
		return htye;
	}
	public void setHtye(String htye) {
		this.htye = htye;
	}
	public String getHzdwmc() {
		return hzdwmc;
	}
	public void setHzdwmc(String hzdwmc) {
		this.hzdwmc = hzdwmc;
	}
	public String getJqbj() {
		return jqbj;
	}
	public void setJqbj(String jqbj) {
		this.jqbj = jqbj;
	}
	public String getJqlx() {
		return jqlx;
	}
	public void setJqlx(String jqlx) {
		this.jqlx = jqlx;
	}
	public String getJtgddh() {
		return jtgddh;
	}
	public void setJtgddh(String jtgddh) {
		this.jtgddh = jtgddh;
	}
	public String getJtzz() {
		return jtzz;
	}
	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getLxfsbgqk() {
		return lxfsbgqk;
	}
	public void setLxfsbgqk(String lxfsbgqk) {
		this.lxfsbgqk = lxfsbgqk;
	}
	public String getMqdh() {
		return mqdh;
	}
	public void setMqdh(String mqdh) {
		this.mqdh = mqdh;
	}
	public String getMqgzdw() {
		return mqgzdw;
	}
	public void setMqgzdw(String mqgzdw) {
		this.mqgzdw = mqgzdw;
	}
	public String getMqxm() {
		return mqxm;
	}
	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}
	public String getMqysr() {
		return mqysr;
	}
	public void setMqysr(String mqysr) {
		this.mqysr = mqysr;
	}
	public String getMzmc() {
		return mzmc;
	}
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
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
	public String getSchkr() {
		return schkr;
	}
	public void setSchkr(String schkr) {
		this.schkr = schkr;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getSjffrq() {
		return sjffrq;
	}
	public void setSjffrq(String sjffrq) {
		this.sjffrq = sjffrq;
	}
	public String getSqje() {
		return sqje;
	}
	public void setSqje(String sqje) {
		this.sqje = sqje;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getWyys() {
		return wyys;
	}
	public void setWyys(String wyys) {
		this.wyys = wyys;
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
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxshyj() {
		return xxshyj;
	}
	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getXyshyj() {
		return xyshyj;
	}
	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getZhkzh() {
		return zhkzh;
	}
	public void setZhkzh(String zhkzh) {
		this.zhkzh = zhkzh;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getZzmmmc() {
		return zzmmmc;
	}
	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}
	public String getFqsfzh() {
		return fqsfzh;
	}
	public void setFqsfzh(String fqsfzh) {
		this.fqsfzh = fqsfzh;
	}
	public String getMqsfzh() {
		return mqsfzh;
	}
	public void setMqsfzh(String mqsfzh) {
		this.mqsfzh = mqsfzh;
	}
	public String getLxdh2() {
		return lxdh2;
	}
	public void setLxdh2(String lxdh2) {
		this.lxdh2 = lxdh2;
	}
	public String getLxdh3() {
		return lxdh3;
	}
	public void setLxdh3(String lxdh3) {
		this.lxdh3 = lxdh3;
	}
	public String getSfzyxzzrq() {
		return sfzyxzzrq;
	}
	public void setSfzyxzzrq(String sfzyxzzrq) {
		this.sfzyxzzrq = sfzyxzzrq;
	}
	public String getDkhj() {
		return dkhj;
	}
	public void setDkhj(String dkhj) {
		this.dkhj = dkhj;
	}
	public String getXq1() {
		return xq1;
	}
	public void setXq1(String xq1) {
		this.xq1 = xq1;
	}
	public String getXq2() {
		return xq2;
	}
	public void setXq2(String xq2) {
		this.xq2 = xq2;
	}
	public String getXq3() {
		return xq3;
	}
	public void setXq3(String xq3) {
		this.xq3 = xq3;
	}
	public String getXq4() {
		return xq4;
	}
	public void setXq4(String xq4) {
		this.xq4 = xq4;
	}
	public String getXq5() {
		return xq5;
	}
	public void setXq5(String xq5) {
		this.xq5 = xq5;
	}
	public String getXfje() {
		return xfje;
	}
	public void setXfje(String xfje) {
		this.xfje = xfje;
	}
	public String getZsfje() {
		return zsfje;
	}
	public void setZsfje(String zsfje) {
		this.zsfje = zsfje;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getYxsh() {
		return yxsh;
	}
	public void setYxsh(String yxsh) {
		this.yxsh = yxsh;
	}
	public String getYxshyj() {
		return yxshyj;
	}
	public void setYxshyj(String yxshyj) {
		this.yxshyj = yxshyj;
	}
	public String getDkxn() {
		return dkxn;
	}
	public void setDkxn(String dkxn) {
		this.dkxn = dkxn;
	}
	public String getXf() {
		return xf;
	}
	public void setXf(String xf) {
		this.xf = xf;
	}
	public String getZsf() {
		return zsf;
	}
	public void setZsf(String zsf) {
		this.zsf = zsf;
	}
	public String getDsanxnzsf() {
		return dsanxnzsf;
	}
	public void setDsanxnzsf(String dsanxnzsf) {
		this.dsanxnzsf = dsanxnzsf;
	}
	
	public String getDwuxnshf() {
		return dwuxnshf;
	}
	public void setDwuxnshf(String dwuxnshf) {
		this.dwuxnshf = dwuxnshf;
	}
	public String getDwuxnzsf() {
		return dwuxnzsf;
	}
	public void setDwuxnzsf(String dwuxnzsf) {
		this.dwuxnzsf = dwuxnzsf;
	}
	
	
}
