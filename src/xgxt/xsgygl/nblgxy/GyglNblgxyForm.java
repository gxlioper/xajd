/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-27 下午03:10:15</p>
 */
package xgxt.xsgygl.nblgxy;

import org.apache.struts.action.ActionForm;

public class GyglNblgxyForm extends ActionForm {
	private static final long serialVersionUID = -1086254391569068325L;
	String xy;//学院
	String zy;//专业
	String bj;//班级
	String xh;//学号
	String nj;//年级
	String zgh;//辅导员职工号
	String xm;//姓名
	String xn;//学年
	String xq;//学期
	String lddm;//楼栋
	String qsh;//寝室号
	String pysj;//时间
	String zywt;//主要问题内容
	String bz;//备注
	String yesNo;//审核标志
	String nf;//年份
	String yf;//月份
	String gzksrq;//开始日期
	String gzjsrq;//结束日期
	String rzqsh;//入住寝室
	String fzld;//负责楼栋	
	String gznr_jyfk;//简要反馈
	String jgznr_jyjl;//简要记录
	String gznr_jhynr;//会议内容
	String gznr_jxsdt;//学生动态
	String gznr_jqtnr;//其他内容
	String fkyj_xyfk;//学院反馈
	String fkyj_xgbfk;//学工部反馈
	String fkyj_fwzxfk;//服务中心反馈
	String sfzz;//是否在职
	String rq;//日期
	String lxfs;//联系方式
	String fzssbh;//负责宿舍编号
	String ssbhcn;//负责宿舍注释
	String dzyx;//邮箱
	String ssbh;//
	String lzrq;//离职日期
	String lxfs_ch;
	String lxfs_dh;
	String ahtc;
	String jtzk;
	String xfcj;
	String hjry;
	String wjqk;
	String xgtzxg;
	String rjjwshhd;
	String gzqk;
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	public String getSfzz() {
		return sfzz;
	}
	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}
	public String getFkyj_fwzxfk() {
		return fkyj_fwzxfk;
	}
	public void setFkyj_fwzxfk(String fkyj_fwzxfk) {
		this.fkyj_fwzxfk = fkyj_fwzxfk;
	}
	public String getFkyj_xgbfk() {
		return fkyj_xgbfk;
	}
	public void setFkyj_xgbfk(String fkyj_xgbfk) {
		this.fkyj_xgbfk = fkyj_xgbfk;
	}
	public String getFkyj_xyfk() {
		return fkyj_xyfk;
	}
	public void setFkyj_xyfk(String fkyj_xyfk) {
		this.fkyj_xyfk = fkyj_xyfk;
	}
	public String getFzld() {
		return fzld;
	}
	public void setFzld(String fzld) {
		this.fzld = fzld;
	}
	public String getGzjsrq() {
		return gzjsrq;
	}
	public void setGzjsrq(String gzjsrq) {
		this.gzjsrq = gzjsrq;
	}
	public String getGzksrq() {
		return gzksrq;
	}
	public void setGzksrq(String gzksrq) {
		this.gzksrq = gzksrq;
	}
	public String getGznr_jhynr() {
		return gznr_jhynr;
	}
	public void setGznr_jhynr(String gznr_jhynr) {
		this.gznr_jhynr = gznr_jhynr;
	}
	public String getGznr_jqtnr() {
		return gznr_jqtnr;
	}
	public void setGznr_jqtnr(String gznr_jqtnr) {
		this.gznr_jqtnr = gznr_jqtnr;
	}
	public String getGznr_jxsdt() {
		return gznr_jxsdt;
	}
	public void setGznr_jxsdt(String gznr_jxsdt) {
		this.gznr_jxsdt = gznr_jxsdt;
	}
	public String getGznr_jyfk() {
		return gznr_jyfk;
	}
	public void setGznr_jyfk(String gznr_jyfk) {
		this.gznr_jyfk = gznr_jyfk;
	}
	public String getJgznr_jyjl() {
		return jgznr_jyjl;
	}
	public void setJgznr_jyjl(String jgznr_jyjl) {
		this.jgznr_jyjl = jgznr_jyjl;
	}
	public String getRzqsh() {
		return rzqsh;
	}
	public void setRzqsh(String rzqsh) {
		this.rzqsh = rzqsh;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getNf() {
		return nf;
	}
	public void setNf(String nf) {
		this.nf = nf;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getPysj() {
		return pysj;
	}
	public void setPysj(String pysj) {
		this.pysj = pysj;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
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
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getZywt() {
		return zywt;
	}
	public void setZywt(String zywt) {
		this.zywt = zywt;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	public String getFzssbh() {
		return fzssbh;
	}
	public void setFzssbh(String fzssbh) {
		this.fzssbh = fzssbh;
	}
	public String getLxfs() {
		return lxfs;
	}
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getSsbhcn() {
		return ssbhcn;
	}
	public void setSsbhcn(String ssbhcn) {
		this.ssbhcn = ssbhcn;
	}
	public String getLzrq() {
		return lzrq;
	}
	public void setLzrq(String lzrq) {
		this.lzrq = lzrq;
	}
	public String getAhtc() {
		return ahtc;
	}
	public void setAhtc(String ahtc) {
		this.ahtc = ahtc;
	}
	public String getGzqk() {
		return gzqk;
	}
	public void setGzqk(String gzqk) {
		this.gzqk = gzqk;
	}
	public String getHjry() {
		return hjry;
	}
	public void setHjry(String hjry) {
		this.hjry = hjry;
	}
	public String getJtzk() {
		return jtzk;
	}
	public void setJtzk(String jtzk) {
		this.jtzk = jtzk;
	}
	public String getLxfs_ch() {
		return lxfs_ch;
	}
	public void setLxfs_ch(String lxfs_ch) {
		this.lxfs_ch = lxfs_ch;
	}
	public String getLxfs_dh() {
		return lxfs_dh;
	}
	public void setLxfs_dh(String lxfs_dh) {
		this.lxfs_dh = lxfs_dh;
	}
	public String getRjjwshhd() {
		return rjjwshhd;
	}
	public void setRjjwshhd(String rjjwshhd) {
		this.rjjwshhd = rjjwshhd;
	}
	public String getWjqk() {
		return wjqk;
	}
	public void setWjqk(String wjqk) {
		this.wjqk = wjqk;
	}
	public String getXfcj() {
		return xfcj;
	}
	public void setXfcj(String xfcj) {
		this.xfcj = xfcj;
	}
	public String getXgtzxg() {
		return xgtzxg;
	}
	public void setXgtzxg(String xgtzxg) {
		this.xgtzxg = xgtzxg;
	}
}
