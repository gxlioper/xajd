
package xgxt.pjpy.hbsf;

import java.io.Serializable;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 湖北师范学院评奖评优综合素质测评保存MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-14</p>
 */
public class ZhszcpSaveModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xh;//学号
	private String xn;//学年
	private String nd;//年度
	private String xq;//学期
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
	private String bjghkms;//不及格缓考门数
	private String cfjz;//处分记载
	private String cpjg;//测评结果
	private String zhszcpzf;//综合素质测评总分
	private String dcj;//德成绩
	private String zcj;//智成绩
	private String tcj;//体成绩
	public String getBjghkms() {
		return bjghkms;
	}
	public void setBjghkms(String bjghkms) {
		this.bjghkms = bjghkms;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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
	public String getDcj() {
		return dcj;
	}
	public void setDcj(String dcj) {
		this.dcj = dcj;
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
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
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
	public String getTcj() {
		return tcj;
	}
	public void setTcj(String tcj) {
		this.tcj = tcj;
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
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
	public String getYyjn() {
		return yyjn;
	}
	public void setYyjn(String yyjn) {
		this.yyjn = yyjn;
	}
	public String getZcj() {
		return zcj;
	}
	public void setZcj(String zcj) {
		this.zcj = zcj;
	}
	public String getZhcppm() {
		return zhcppm;
	}
	public void setZhcppm(String zhcppm) {
		this.zhcppm = zhcppm;
	}
	public String getZhszcpzf() {
		return zhszcpzf;
	}
	public void setZhszcpzf(String zhszcpzf) {
		this.zhszcpzf = zhszcpzf;
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
}
