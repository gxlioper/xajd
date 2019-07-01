
package xgxt.wjcf.shgc;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 上海工程技术大学违纪处分Parameter</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-19</p>
 */
public class WjcfShgcActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String errMsg;
	private String[] errMsgs;//错误码
	private String sbsj;
	private String xn;//学年
	private String xq;//学期
	private String nd;//年度
	private String nj;//年级
	private String cflb;//处分类别
	private String xh;//学号
	private String xm;//姓名
	private String xb;//性别
	private String xydm;//学院代码
	private String xymc;//学院名称
	private String zydm;//专业代码
	private String zymc;//专业名称
	private String bjdm;//班级代码
	private String bjmc;//班级名称
	private String shjg;//审核结果	
	private String xysh;//学院审核
	private String xxsh;//学校审核
	private String csrq;//出生日期
	private String mz;//民族
	private String lxdh;//联系电话
	private String bz;//备注
	private String shzt;//审核主题
	private String zzmm;//政治面貌
	private String wjsj; //违纪时间
	private String shsj;//审核时间
	private String zc; //主持人
	private String cxryqd; //出席人员签到
	private String cxrs; //出席人数
	private String hyjl; //会议记录
	private String jlr; //记录人
	private String rq; //日期
	private String cfyy;//处分原因代码
	private String cflbmc;//处分类别名称
	private String cfyymc;//处分原因名称
	private String cfhbx;//处分后表现
	private String jcjl;//奖惩记录
	private String bzryj;//班主任意见
	private String xyyj;//学院意见
	private String kbbmyj;//会办部门意见
	private String xscyj;//学生处意见
	private String xxyj; //学校意见
	private String cclb;//处分类别
	private String jtwjsy;//具体违纪事由
	private String zacfqk;//治安处分情况
	private String qtcfqk;//其它处分情况
	private String[] cbv;//审核查询页面的checkbox
	private String xxclyj;//学校处理意见
	private String yesNo;
	private String cfsj;//处分时间
	private String cfwh;//处分文号
	private String sssj;//申诉时间
	private String slqk;//申诉情况
	private String slrq;//申诉日期
	private String sltzs;//申诉通知书
	private String yq;//申斥要求
	private String fcrq;//复查日期
	private String fcqk;//复查情况
	private String csqk;//初审情况
	private String fctzs;//复查通知书
	private String len;
	private String wjsclj;//文件上传路径
	private String mqzt;//日前状态
	private String jdsj;//解除时间
	private String jdwh;//解除文号
	private String sfzh;
	private String xyclyj;
	private String xsbx1;
	private String xsbx3;
	private String xsbx2;
	private String xsbx4;
	private String cfjb;
	private String xgcyj; //学工处意见
	

	
	public String getXgcyj() {
		return xgcyj;
	}
	public void setXgcyj(String xgcyj) {
		this.xgcyj = xgcyj;
	}
	public String getCfjb() {
		return cfjb;
	}
	public void setCfjb(String cfjb) {
		this.cfjb = cfjb;
	}
	public String getXyclyj() {
		return xyclyj;
	}
	public void setXyclyj(String xyclyj) {
		this.xyclyj = xyclyj;
	}
	public String getJdsj() {
		return jdsj;
	}
	public void setJdsj(String jdsj) {
		this.jdsj = jdsj;
	}
	public String getJdwh() {
		return jdwh;
	}
	public void setJdwh(String jdwh) {
		this.jdwh = jdwh;
	}
	public String getMqzt() {
		return mqzt;
	}
	public void setMqzt(String mqzt) {
		this.mqzt = mqzt;
	}
	public String getCsqk() {
		return csqk;
	}
	public void setCsqk(String csqk) {
		this.csqk = csqk;
	}
	public String getFcqk() {
		return fcqk;
	}
	public void setFcqk(String fcqk) {
		this.fcqk = fcqk;
	}
	public String getFcrq() {
		return fcrq;
	}
	public void setFcrq(String fcrq) {
		this.fcrq = fcrq;
	}
	public String getSlqk() {
		return slqk;
	}
	public void setSlqk(String slqk) {
		this.slqk = slqk;
	}
	public String getSlrq() {
		return slrq;
	}
	public void setSlrq(String slrq) {
		this.slrq = slrq;
	}
	public String getSltzs() {
		return sltzs;
	}
	public void setSltzs(String sltzs) {
		this.sltzs = sltzs;
	}
	public String getSssj() {
		return sssj;
	}
	public void setSssj(String sssj) {
		this.sssj = sssj;
	}
	public String getYq() {
		return yq;
	}
	public void setYq(String yq) {
		this.yq = yq;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getCfwh() {
		return cfwh;
	}
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}
	public String[] getCbv() {
		return cbv;
	}
	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}
	public String getJtwjsy() {
		return jtwjsy;
	}
	public void setJtwjsy(String jtwjsy) {
		this.jtwjsy = jtwjsy;
	}
	public String getQtcfqk() {
		return qtcfqk;
	}
	public void setQtcfqk(String qtcfqk) {
		this.qtcfqk = qtcfqk;
	}
	public String getZacfqk() {
		return zacfqk;
	}
	public void setZacfqk(String zacfqk) {
		this.zacfqk = zacfqk;
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getBzryj() {
		return bzryj;
	}
	public void setBzryj(String bzryj) {
		this.bzryj = bzryj;
	}
	public String getCclb() {
		return cclb;
	}
	public void setCclb(String cclb) {
		this.cclb = cclb;
	}
	public String getCfhbx() {
		return cfhbx;
	}
	public void setCfhbx(String cfhbx) {
		this.cfhbx = cfhbx;
	}
	public String getCflb() {
		return cflb;
	}
	public void setCflb(String cflb) {
		this.cflb = cflb;
	}
	public String getCflbmc() {
		return cflbmc;
	}
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	public String getCfyy() {
		return cfyy;
	}
	public void setCfyy(String cfyy) {
		this.cfyy = cfyy;
	}
	public String getCfyymc() {
		return cfyymc;
	}
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getCxrs() {
		return cxrs;
	}
	public void setCxrs(String cxrs) {
		this.cxrs = cxrs;
	}
	public String getCxryqd() {
		return cxryqd;
	}
	public void setCxryqd(String cxryqd) {
		this.cxryqd = cxryqd;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String[] getErrMsgs() {
		return errMsgs;
	}
	public void setErrMsgs(String[] errMsgs) {
		this.errMsgs = errMsgs;
	}
	public String getHyjl() {
		return hyjl;
	}
	public void setHyjl(String hyjl) {
		this.hyjl = hyjl;
	}
	public String getJcjl() {
		return jcjl;
	}
	public void setJcjl(String jcjl) {
		this.jcjl = jcjl;
	}
	public String getJlr() {
		return jlr;
	}
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	public String getKbbmyj() {
		return kbbmyj;
	}
	public void setKbbmyj(String kbbmyj) {
		this.kbbmyj = kbbmyj;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
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
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = rq;
	}
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getWjsj() {
		return wjsj;
	}
	public void setWjsj(String wjsj) {
		this.wjsj = wjsj;
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
	public String getXscyj() {
		return xscyj;
	}
	public void setXscyj(String xscyj) {
		this.xscyj = xscyj;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXxyj() {
		return xxyj;
	}
	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
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
	public String getXyyj() {
		return xyyj;
	}
	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
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
	public String getZzmm() {
		return zzmm;
	}
	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	public String getXxclyj() {
		return xxclyj;
	}
	public void setXxclyj(String xxclyj) {
		this.xxclyj = xxclyj;
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}
	public String getFctzs() {
		return fctzs;
	}
	public void setFctzs(String fctzs) {
		this.fctzs = fctzs;
	}
	public String getLen() {
		return len;
	}
	public void setLen(String len) {
		this.len = len;
	}
	public String getWjsclj() {
		return wjsclj;
	}
	public void setWjsclj(String wjsclj) {
		this.wjsclj = wjsclj;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getXsbx1() {
		return xsbx1;
	}
	public void setXsbx1(String xsbx1) {
		this.xsbx1 = xsbx1;
	}
	public String getXsbx2() {
		return xsbx2;
	}
	public void setXsbx2(String xsbx2) {
		this.xsbx2 = xsbx2;
	}
	public String getXsbx3() {
		return xsbx3;
	}
	public void setXsbx3(String xsbx3) {
		this.xsbx3 = xsbx3;
	}
	public String getXsbx4() {
		return xsbx4;
	}
	public void setXsbx4(String xsbx4) {
		this.xsbx4 = xsbx4;
	}
	public String getSbsj() {
		return sbsj;
	}
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
}
