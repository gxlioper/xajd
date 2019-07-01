/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.qgzx.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class QgzxForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	Pages pages = new Pages();
	private String pk;           //主键
	private String[] pkV;        //主键值
	private String xydm;         //学院代码
	private String zydm;         //专业代码
	private String bjdm;         //班级代码
	private String xymc;         //学院名称
	private String zymc;         //专业名称
	private String bjmc;         //班级名称
	private String xh;           //学号
	private String xb;           //性别
	private String xm;           //姓名
	private String nj;           //年级
	private String gwxz;         //岗位性质
	private String xn;           //学年
	private String xq;           //学期
	private String nd;           //年度
	private String xmdm;         //项目代码
	private String gwdm;         //岗位代码
	private String gwsbsj;       //岗位上报时间
	private String cxfs;         //
	private String yrdwdm;       //用人单位代码
	private String hblb;         //划拨类别
	private String yf;           //月份
	private String pkValue;      //主键值
	private String jsjsp;        //计算机水平
	private String yhtc;         //特长
	private String sffcfp="1";   //是否服从分派
	private String grqk;         //个人情况
	private String lxdh;         //联系电话
	private String zzmm;         //政治面貌
	private String mz;           //民族
	private String qsh;          //寝室号
	private String ldmc;         //楼栋名称
	private String mqqgzxqk;     //目前勤工助学情况
	private String kssj;         //开始时间
	private String jssj;         //结束时间
	private String bz;           //备注
	private String gzjl;         //工作经历
	private String xssq;         //申请理由
	private String rq;           //日期
	private String gzkssj;       //工作开始时间
	private String gzjssj;       //工作结束时间
	private String gznr;         //工作内容
	private String zgzsj;        //总工作时间
	private String ygzsj;        //月工作时间
	private String ffcjje;       //发放酬金金额
	private String ffsj;         //发放时间
	private String ffsjks;       //发放时间开始
	private String ffsjjs;       //发放时间结束
	private String userName;     //用户名
	private String userType;     //用户类型
	private String fzld;
	private String wzlxdm;
	private String fflx;         //发放类型
	//岗位调整
	private String tzyy;         //调整原因
	private String tzsj;         //调整时间
	private String oldXh;        //被替换的学生学号
	private String tzqgzxn;      //调整前工作学年
	private String tzqgznd;      //调整前工作年度
	private String tzqgzxq;      //调整前工作学期
	private String tzqkcjqgzxsj; //调整前可参加勤工助学时间
	private String tzhgzxn;      //调整后工作学年
	private String tzhgzxq;      //调整后工作学期
	private String tzhgznd;      //调整后工作年度
	private String tzhgw;        //调整后岗位名称
	private String tzhgwsbsj;    //调整后岗位上报时间
	private String tzhkcjqgzxsj; //调整可参加勤工助学时间
	private String tzqgw;        //调整前岗位
	private String tzqgwsbsj;    //调整前岗位上报时间
	private String shjg;         //审核结果
	//end岗位调整
	private String yhkh;
	private String yhmc;
	private String gzsj;
	private String cjje;
	private String bdsj;//报到时间 
	private String lzsj;//离职时间
	private String fjwjmc;//附件文件名称
	private FormFile uploadFile;
	private String xxyj;//学校审核
	private String xyyj;//学院审核
	private String fdyyj;//辅导员审核
	private String xxsh;//学校审核
	private String queryequals_xn;
	private String queryequals_nd;
	private String queryequals_xueqi;
	private String queryequals_gwdm;
	private String queryequals_gwxz;
	
	private String gzpj;                   //工作评价
	private String gzbx;                   //工作表现
	
	
	public String getGzpj() {
		return gzpj;
	}

	public void setGzpj(String gzpj) {
		this.gzpj = gzpj;
	}

	public String getGzbx() {
		return gzbx;
	}

	public void setGzbx(String gzbx) {
		this.gzbx = gzbx;
	}

	public String getQueryequals_gwxz() {
		return queryequals_gwxz;
	}

	public void setQueryequals_gwxz(String queryequals_gwxz) {
		this.queryequals_gwxz = queryequals_gwxz;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getMqqgzxqk() {
		return mqqgzxqk;
	}

	public void setMqqgzxqk(String mqqgzxqk) {
		this.mqqgzxqk = mqqgzxqk;
	}

	public String getLdmc() {
		return ldmc;
	}

	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getGrqk() {
		return grqk;
	}

	public void setGrqk(String grqk) {
		this.grqk = grqk;
	}

	public String getJsjsp() {
		return jsjsp;
	}

	public void setJsjsp(String jsjsp) {
		this.jsjsp = jsjsp;
	}

	public String getSffcfp() {
		return sffcfp;
	}

	public void setSffcfp(String sffcfp) {
		this.sffcfp = sffcfp;
	}

	public String getYhtc() {
		return yhtc;
	}

	public void setTc(String yhtc) {
		this.yhtc = yhtc;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getHblb() {
		return hblb;
	}

	public void setHblb(String hblb) {
		this.hblb = hblb;
	}

	public String getYrdwdm() {
		return yrdwdm;
	}

	public void setYrdwdm(String yrdwdm) {
		this.yrdwdm = yrdwdm;
	}

	public String getCxfs() {
		return cxfs;
	}

	public void setCxfs(String cxfs) {
		this.cxfs = cxfs;
	}

	public String getGwdm() {
		return gwdm;
	}

	public void setGwdm(String gwdm) {
		this.gwdm = gwdm;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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

	public String getGwxz() {
		return gwxz;
	}

	public void setGwxz(String gwxz) {
		this.gwxz = gwxz;
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

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public void setYhtc(String yhtc) {
		this.yhtc = yhtc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getGzjl() {
		return gzjl;
	}

	public void setGzjl(String gzjl) {
		this.gzjl = gzjl;
	}

	public String getXssq() {
		return xssq;
	}

	public void setXssq(String xssq) {
		this.xssq = xssq;
	}

	public String getRq() {
		return rq;
	}

	public void setRq(String rq) {
		this.rq = rq;
	}

	public String getGzkssj() {
		return gzkssj;
	}

	public void setGzkssj(String gzkssj) {
		this.gzkssj = gzkssj;
	}

	public String getGzjssj() {
		return gzjssj;
	}

	public void setGzjssj(String gzjssj) {
		this.gzjssj = gzjssj;
	}

	public String getGznr() {
		return gznr;
	}

	public void setGznr(String gznr) {
		this.gznr = gznr;
	}

	public String getZgzsj() {
		return zgzsj;
	}

	public void setZgzsj(String zgzsj) {
		this.zgzsj = zgzsj;
	}

	public String getYgzsj() {
		return ygzsj;
	}

	public void setYgzsj(String ygzsj) {
		this.ygzsj = ygzsj;
	}

	public String getFfcjje() {
		return ffcjje;
	}

	public void setFfcjje(String ffcjje) {
		this.ffcjje = ffcjje;
	}

	public String getGwsbsj() {
		return gwsbsj;
	}

	public void setGwsbsj(String gwsbsj) {
		this.gwsbsj = gwsbsj;
	}

	public String[] getPkV() {
		return pkV;
	}

	public void setPkV(String[] pkV) {
		this.pkV = pkV;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFfsj() {
		return ffsj;
	}

	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}

	public String getFfsjks() {
		return ffsjks;
	}

	public void setFfsjks(String ffsjks) {
		this.ffsjks = ffsjks;
	}

	public String getFfsjjs() {
		return ffsjjs;
	}

	public void setFfsjjs(String ffsjjs) {
		this.ffsjjs = ffsjjs;
	}

	public String getFzld() {
		return fzld;
	}

	public void setFzld(String fzld) {
		this.fzld = fzld;
	}

	public String getWzlxdm() {
		return wzlxdm;
	}

	public void setWzlxdm(String wzlxdm) {
		this.wzlxdm = wzlxdm;
	}

	public String getFflx() {
		return fflx;
	}

	public void setFflx(String fflx) {
		this.fflx = fflx;
	}

	public String getOldXh() {
		return oldXh;
	}

	public void setOldXh(String oldXh) {
		this.oldXh = oldXh;
	}

	public String getTzhgzxn() {
		return tzhgzxn;
	}

	public void setTzhgzxn(String tzhgzxn) {
		this.tzhgzxn = tzhgzxn;
	}

	public String getTzhgzxq() {
		return tzhgzxq;
	}

	public void setTzhgzxq(String tzhgzxq) {
		this.tzhgzxq = tzhgzxq;
	}

	public String getTzhgznd() {
		return tzhgznd;
	}

	public void setTzhgznd(String tzhgznd) {
		this.tzhgznd = tzhgznd;
	}
	

	public String getTzhgw() {
		return tzhgw;
	}

	public void setTzhgw(String tzhgw) {
		this.tzhgw = tzhgw;
	}

	public String getTzhgwsbsj() {
		return tzhgwsbsj;
	}

	public void setTzhgwsbsj(String tzhgwsbsj) {
		this.tzhgwsbsj = tzhgwsbsj;
	}

	public String getTzhkcjqgzxsj() {
		return tzhkcjqgzxsj;
	}

	public void setTzhkcjqgzxsj(String tzhkcjqgzxsj) {
		this.tzhkcjqgzxsj = tzhkcjqgzxsj;
	}

	public String getTzqgw() {
		return tzqgw;
	}

	public void setTzqgw(String tzqgw) {
		this.tzqgw = tzqgw;
	}

	public String getTzqgwsbsj() {
		return tzqgwsbsj;
	}

	public void setTzqgwsbsj(String tzqgwsbsj) {
		this.tzqgwsbsj = tzqgwsbsj;
	}

	public String getTzyy() {
		return tzyy;
	}

	public void setTzyy(String tzyy) {
		this.tzyy = tzyy;
	}

	public String getTzsj() {
		return tzsj;
	}

	public void setTzsj(String tzsj) {
		this.tzsj = tzsj;
	}

	public String getTzqgzxn() {
		return tzqgzxn;
	}

	public void setTzqgzxn(String tzqgzxn) {
		this.tzqgzxn = tzqgzxn;
	}

	public String getTzqgznd() {
		return tzqgznd;
	}

	public void setTzqgznd(String tzqgznd) {
		this.tzqgznd = tzqgznd;
	}

	public String getTzqgzxq() {
		return tzqgzxq;
	}

	public void setTzqgzxq(String tzqgzxq) {
		this.tzqgzxq = tzqgzxq;
	}

	public String getTzqkcjqgzxsj() {
		return tzqkcjqgzxsj;
	}

	public void setTzqkcjqgzxsj(String tzqkcjqgzxsj) {
		this.tzqkcjqgzxsj = tzqkcjqgzxsj;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getYhkh() {
		return yhkh;
	}

	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getGzsj() {
		return gzsj;
	}

	public void setGzsj(String gzsj) {
		this.gzsj = gzsj;
	}

	public String getCjje() {
		return cjje;
	}

	public void setCjje(String cjje) {
		this.cjje = cjje;
	}

	public String getBdsj() {
		return bdsj;
	}

	public void setBdsj(String bdsj) {
		this.bdsj = bdsj;
	}

	public String getLzsj() {
		return lzsj;
	}

	public void setLzsj(String lzsj) {
		this.lzsj = lzsj;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getFjwjmc() {
		return fjwjmc;
	}

	public void setFjwjmc(String fjwjmc) {
		this.fjwjmc = fjwjmc;
	}

	public String getXxyj() {
		return xxyj;
	}

	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}

	public String getXyyj() {
		return xyyj;
	}

	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}

	public String getFdyyj() {
		return fdyyj;
	}

	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getQueryequals_xn() {
		return queryequals_xn;
	}

	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}

	public String getQueryequals_nd() {
		return queryequals_nd;
	}

	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
	}

	public String getQueryequals_xueqi() {
		return queryequals_xueqi;
	}

	public void setQueryequals_xueqi(String queryequals_xueqi) {
		this.queryequals_xueqi = queryequals_xueqi;
	}

	public String getQueryequals_gwdm() {
		return queryequals_gwdm;
	}

	public void setQueryequals_gwdm(String queryequals_gwdm) {
		this.queryequals_gwdm = queryequals_gwdm;
	}	
	
}