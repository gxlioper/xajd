
package xgxt.pjpy.hbsf;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 湖北师范学院评奖评优ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class PjpyHbsfActionForm extends ActionForm {

	/**
	 * 自动生成版本ID
	 */
	private static final long serialVersionUID = 1L;

	private String xh;//学号
	private String xn;//学年
	private String nd;//年度
	private String nj;//年级
	private String xq;//学期
	private String xydm;//学院代码
	private String bjdm;//班级代码
	private String zydm;//专业代码
	private String jxjdm;//奖学金代码
	private String drzw;//担任职务
	private String wysp;//外语水平
	private String sjhm;//手机号码
	private String sqly;//申请理由
	private String kyxm;//科研项目
	private String xxjl;//学习简历
	private String jxj1;//奖学金1
	private String shyg1;//三好优干分1
	private String sxddf;//思想道德素质分
	private String kxwhf;//科学文化分
	private String sxjkf;//身心健康分
	private String kcjqf;//课程加权分
	private String pm;//排名
	private String zhkpzf1;//综合测评总分
	private String zhkppm;//综合测评排名
	private String bjghkms;//不及格缓考门数
	private String cljz;//处理记载
	private String tyhgbz1;//体育合格标准
	private String bz;//备注
	private String zpf;//自评分
	private String sxddbx;//思想道德表现
	private String zzllxx;//政治理论学习
	private String ssjsqk;//宿舍建设情况
	private String shsjhd;//社会实践活动
	private String gbrzbx;//干部任职表现
	private String qttcsj;//其它突出事迹
	private String kcjqpfj;//课程加权平均分
	private String kcjqpfjpm;//课程加权平均分排名
	private String zytz;//专业拓展
	private String yyjn;//语言技能
	private String jsjjn;//计算机技能
	private String kxjs;//科学竞赛
	private String zyjn;//职业技能
	private String cxnl;//创新能力
	private String tydb;//体育达标
	private String tyhd;//体育活动
	private String tsqk;//特殊情况
	private String stszzf;//身体素质总分
	private String xljkhd;//心理健康活动
	private String xlszzk;//心理素质状况
	private String xlszzf;//心理素质总分
	private String zhcppm;//综合测评排名
	private String bjkhkms;//不及格缓考门数
	private String cfjz;//处分记载
	private String cpjg;//测评结果
	private String zhszcpzf;//综合素质测评总分
	private String dcj;//德成绩
	private String zcj;//智成绩
	private String tcj;//体成绩
	private String[] cbv;
	
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getDrzw() {
		return drzw;
	}
	public void setDrzw(String drzw) {
		this.drzw = drzw;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getKyxm() {
		return kyxm;
	}
	public void setKyxm(String kyxm) {
		this.kyxm = kyxm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getWysp() {
		return wysp;
	}
	public void setWysp(String wysp) {
		this.wysp = wysp;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXxjl() {
		return xxjl;
	}
	public void setXxjl(String xxjl) {
		this.xxjl = xxjl;
	}
	public String getBjghkms() {
		return bjghkms;
	}
	public void setBjghkms(String bjghkms) {
		this.bjghkms = bjghkms;
	}
	public String getCljz() {
		return cljz;
	}
	public void setCljz(String cljz) {
		this.cljz = cljz;
	}
	public String getJxj1() {
		return jxj1;
	}
	public void setJxj1(String jxj1) {
		this.jxj1 = jxj1;
	}
	public String getKcjqf() {
		return kcjqf;
	}
	public void setKcjqf(String kcjqf) {
		this.kcjqf = kcjqf;
	}
	public String getKxwhf() {
		return kxwhf;
	}
	public void setKxwhf(String kxwhf) {
		this.kxwhf = kxwhf;
	}
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	public String getShyg1() {
		return shyg1;
	}
	public void setShyg1(String shyg1) {
		this.shyg1 = shyg1;
	}
	public String getSxddf() {
		return sxddf;
	}
	public void setSxddf(String sxddf) {
		this.sxddf = sxddf;
	}
	public String getSxjkf() {
		return sxjkf;
	}
	public void setSxjkf(String sxjkf) {
		this.sxjkf = sxjkf;
	}
	public String getTyhgbz1() {
		return tyhgbz1;
	}
	public void setTyhgbz1(String tyhgbz1) {
		this.tyhgbz1 = tyhgbz1;
	}
	public String getZhkppm() {
		return zhkppm;
	}
	public void setZhkppm(String zhkppm) {
		this.zhkppm = zhkppm;
	}
	public String getZhkpzf1() {
		return zhkpzf1;
	}
	public void setZhkpzf1(String zhkpzf1) {
		this.zhkpzf1 = zhkpzf1;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
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
	public String getBjkhkms() {
		return bjkhkms;
	}
	public void setBjkhkms(String bjkhkms) {
		this.bjkhkms = bjkhkms;
	}
	public String getCfjz() {
		return cfjz;
	}
	public void setCfjz(String cfjz) {
		this.cfjz = cfjz;
	}
	public String getCpjg() {
		return cpjg;
	}
	public void setCpjg(String cpjg) {
		this.cpjg = cpjg;
	}
	public String getCxnl() {
		return cxnl;
	}
	public void setCxnl(String cxnl) {
		this.cxnl = cxnl;
	}
	public String getGbrzbx() {
		return gbrzbx;
	}
	public void setGbrzbx(String gbrzbx) {
		this.gbrzbx = gbrzbx;
	}
	public String getJsjjn() {
		return jsjjn;
	}
	public void setJsjjn(String jsjjn) {
		this.jsjjn = jsjjn;
	}
	public String getKcjqpfj() {
		return kcjqpfj;
	}
	public void setKcjqpfj(String kcjqpfj) {
		this.kcjqpfj = kcjqpfj;
	}
	public String getKcjqpfjpm() {
		return kcjqpfjpm;
	}
	public void setKcjqpfjpm(String kcjqpfjpm) {
		this.kcjqpfjpm = kcjqpfjpm;
	}
	public String getKxjs() {
		return kxjs;
	}
	public void setKxjs(String kxjs) {
		this.kxjs = kxjs;
	}
	public String getQttcsj() {
		return qttcsj;
	}
	public void setQttcsj(String qttcsj) {
		this.qttcsj = qttcsj;
	}
	public String getShsjhd() {
		return shsjhd;
	}
	public void setShsjhd(String shsjhd) {
		this.shsjhd = shsjhd;
	}
	public String getSsjsqk() {
		return ssjsqk;
	}
	public void setSsjsqk(String ssjsqk) {
		this.ssjsqk = ssjsqk;
	}
	public String getStszzf() {
		return stszzf;
	}
	public void setStszzf(String stszzf) {
		this.stszzf = stszzf;
	}
	public String getSxddbx() {
		return sxddbx;
	}
	public void setSxddbx(String sxddbx) {
		this.sxddbx = sxddbx;
	}
	public String getTsqk() {
		return tsqk;
	}
	public void setTsqk(String tsqk) {
		this.tsqk = tsqk;
	}
	public String getTydb() {
		return tydb;
	}
	public void setTydb(String tydb) {
		this.tydb = tydb;
	}
	public String getTyhd() {
		return tyhd;
	}
	public void setTyhd(String tyhd) {
		this.tyhd = tyhd;
	}
	public String getXljkhd() {
		return xljkhd;
	}
	public void setXljkhd(String xljkhd) {
		this.xljkhd = xljkhd;
	}
	public String getXlszzf() {
		return xlszzf;
	}
	public void setXlszzf(String xlszzf) {
		this.xlszzf = xlszzf;
	}
	public String getXlszzk() {
		return xlszzk;
	}
	public void setXlszzk(String xlszzk) {
		this.xlszzk = xlszzk;
	}
	public String getYyjn() {
		return yyjn;
	}
	public void setYyjn(String yyjn) {
		this.yyjn = yyjn;
	}
	public String getZhcppm() {
		return zhcppm;
	}
	public void setZhcppm(String zhcppm) {
		this.zhcppm = zhcppm;
	}
	public String getZpf() {
		return zpf;
	}
	public void setZpf(String zpf) {
		this.zpf = zpf;
	}
	public String getZyjn() {
		return zyjn;
	}
	public void setZyjn(String zyjn) {
		this.zyjn = zyjn;
	}
	public String getZytz() {
		return zytz;
	}
	public void setZytz(String zytz) {
		this.zytz = zytz;
	}
	public String getZzllxx() {
		return zzllxx;
	}
	public void setZzllxx(String zzllxx) {
		this.zzllxx = zzllxx;
	}
	public String getDcj() {
		return dcj;
	}
	public void setDcj(String dcj) {
		this.dcj = dcj;
	}
	public String getTcj() {
		return tcj;
	}
	public void setTcj(String tcj) {
		this.tcj = tcj;
	}
	public String getZcj() {
		return zcj;
	}
	public void setZcj(String zcj) {
		this.zcj = zcj;
	}
	public String getZhszcpzf() {
		return zhszcpzf;
	}
	public void setZhszcpzf(String zhszcpzf) {
		this.zhszcpzf = zhszcpzf;
	}
}
