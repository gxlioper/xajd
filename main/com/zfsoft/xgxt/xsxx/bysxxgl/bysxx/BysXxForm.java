/**
 * @部门:学工产品事业部
 * @日期：2014-7-7 上午10:18:01 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.bysxx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业生信息管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：夏夏[工号:1104]
 * @时间： 2014-7-7 上午10:14:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BysXxForm extends ActionForm{
	private static final long serialVersionUID = 7187713079641588815L;
	private String xh;
	private String xm;
	private String nj;
	private String bynd;
	private String xydm;
	private String zydm;
	private String bjdm;
	private String pyccmc;
	private String jtcyfzqk;//家庭成员犯罪情况
	private String sxdwgznr;//毕业实习单位及工作内容
	private String bysjtm;//毕业设计题目
	
	private String qtyyqk;//其他语言情况
	private String brgzzy; //本人工作志愿
	private String zwjd;//自我鉴定
	private String type;
	
	private User user;
	private String jgs;// 籍贯省
	private String jgshi;// 籍贯市
	private String jgx;// 籍贯县
	private String ssbh;// 宿舍编号
	private String rxnj;// 入学年级
	private String nfby;// 能否毕业
	private String sfzc;// 是否注册
	private String dasfyl;// 档案是否遗留
	private String daylyy;// 档案遗留原因
	private String yxdm;// 院校代码
	private String sfzz;// 是否在职
	private String sfsf;// 是否师范
	private String sfdl;// 是否独立
	private String dxhwp;// 定向或委培
	private String bysj;// 毕业时间
	private String zxwyyzdm;// 主修外语语种代码
	private String wydj;// 外语等级
	private String jsjdj;// 计算机等级
	private String lxdz;// 联系地址
	private String yzbm;// 邮政编码
	private String shzw;// 社会职务
	private String jypx;// 教育培训
	private String xmsj;// 项目实践
	private String zgzs;// 资格证书
	private String jljn;// 奖励技能
	private String sybz1;// 生源备注1
	private String sybz2;// 生源备注2
	private String sybz3;// 生源备注3
	private String xldm;// 学历代码
	private String zkzh;// 准考证号
	private String grjl;// 个人简历
	private String sfcj;// 是否残疾/1是,0否
	private String ssch;// 宿舍床号
	private String rzrq;// 住宿日期
	private String zsjzrq;// 住宿截止日期
	private String qsdh;// 寝室电话
	private String ykth;// 一卡通号
	private String yhkh;// 银行卡号
	private String xslb;// 学生类别代码
	private String xslx;// 学生类型代码
	private String sfbys;// 是否毕业生
	private String yhdm;// 银行代码
	private String hkshen;// 户口所在省
	private String hkshi;// 户口所在市
	private String hkxian;// 户口所在县
	private String zcsxhm;// 注册顺序号码
	private String rxqwhcd;// 入学前文化程度
	private String xsqrxxbz;// 学生确认信息标志
	private String dah;// 档案号
	private String ylbxh;// 医疗保险号
	private String rxqdwdz;// 原毕业学院通信地址
	private String rxqdwyb;// 原毕业学院邮编
	private String rxqdwdh;// 入学前单位电话
	private String gzbx;// 高中表现
	private String sfgat;// 是否港澳台胞
	private String sfssmz;// 是否少数民簇
	private String sfzd;// 是否走读
	private String syds;// 生源地省
	private String sydshi;// 生源地市
	private String sydx;// 生源地县
	private String byzh;// 毕业证号
	private String xjh;// 学籍号
	private String jrzzmmrq;// 加入政治面貌日期
	private String sfhq;// 是否华侨
	private String csds;// 出生地省
	private String csdshi;// 出生地市
	private String csdxian;// 出生地县
	private String xy;// 学院名称
	private String xymc;// 学院名称
	private String zymc;// 专业名称
	private String bjmc;// 班级名称
	private String bz;// 备注
	private String xmpy;// 姓名拼音
	private String syd;// 生源地
	private String csrq;// 出生日期
	private String sfzh;// 身份证号码
	private String xb;// 性别
	private String mz;// 民族代码
	private String zzmm;// 政治面貌代码
	private String lxdh;// 联系电话
	private String dzyx;// 电子邮箱
	private String cym;// 曾用名
	private String sg;// 身高
	private String tz;// 体重
	private String tc;// 特长
	private String kslb;// 考生类别
	private String rxfs;// 入学方式
	private String pyfs;// 培养方式
	private String pycc;// 培养层次
	private String xjlb;// 学籍类别
	private String xszp;// 学生照片
	private String xsgkzp;//学生高考照片
	private String xjztm;// 学籍状态
	private String xz;// 学制
	private String whcd;// 文化程度
	private String rxqdw;// 原毕业学院
	private String jtdh;// 家庭电话
	private String jrgqtsj;// 加入共青团时间
	private String jrgcdsj;// 加入共产党时间
	private String jtcygc;// 家庭成员构成
	private String jlcfjl;// 奖励处分经历
	private String jkzk;// 有无病史
	private String jtdz;// 家庭详细地址
	private String jtyb;// 家庭邮编
	private String jg;// 籍贯
	private String xx;// 血型
	private String ah;// 爱好
	private String sfdk;// 是否贷款
	private String shgxxm1;// 社会关系姓名1
	private String shgxgx1;// 社会关系关系1
	private String shgxgzdw1;// 社会关系工作单位1
	private String shgxzw1;// 社会关系职务1
	private String shgxdwdh1;// 社会关系单位电话1
	private String shgxsjhm1;// 社会关系手机号码1
	private String shgxxm2;// 社会关系姓名2
	private String shgxgx2;// 社会关系关系2
	private String shgxgzdw2;// 社会关系工作单位2
	private String shgxzw2;// 社会关系职务2
	private String shgxdwdh2;// 社会关系单位电话2
	private String shgxsjhm2;// 社会关系手机号码2
	private String jtqkjj;// 家庭情况简介
	private String jtjjqk;// 家庭经济情况
	private String sjhm;// 手机号码
	private String byxx;// 毕业信息
	private String kh;// 卡号
	private String rxrq;// 入学日期
	private String fdyxm;// 辅导员姓名
	private String gkcj;// 高考成绩
	private String qqhm;// QQ号码
	private String hkxz;// 户口性质
	private String zyjb;// 专业级别
	private String hkszd;// 户口所在地
	private String ssyq;// 宿舍苑区
	private String ssld;// 宿舍楼栋
	private String jtdzs;// 家庭地址省
	private String jtdzx;// 家庭地址市县
	private String sfzsb;// 是否专升本
	private String sfzfx;// 是否在分校
	private String zjdm;// 宗教代码
	private String sfyby;// 是否已毕业
	
	private String sfzx;// 是否在校
	private String zw;// 职务
	private String thbs;// 替换标识
	private String dybj;// 打印标记
	private String shbj;// 审核标记
	private String xwzsxlh;// 学位证书序列号
	private String xwzsbh;// 学位证书编号
	private String xw;// 学位
	private String xzxm;// 校长姓名
	private String zsxlh;// 证书序列号
	private String zsbh;// 证书编号
	private String bjyjl;// 毕结业结论
	private String csd;// 出生地县
	private String zsjj;// 招生季节
	private String xxxs;// 学习形式
	private String bxlx;// 办学类型
	private String bxxs;// 办学形式
	private String fxzyfx;// 辅修专业方向
	private String fxzy;// 辅修专业
	private String zylb;// 专业类别
	private String dqszj;// 当前所在级
	private String pyfx;// 培养方向
	private String zyfx;// 专业方向
	private String xxszd;// 学校所在地
	private String ksh;// 考生号
	private String xxfx;// 学习方向
	private String zslb;// 招生类别
	private String gj;// 国籍
	private String sfjh;// 是否结婚
	private String ccqj;// 乘车区间
	private String byzffztdm;// 毕业证发放状态代码
	private String xwzsxxdz;// 校外住宿详细地址
	private String zd1;
	private String zd2; 
	private String zd3;
	private String zd4;
	private String zd5;

	private String shzt;// 审核状态
	private String sfxyqgzx;//是否需要勤工助学
	private String xgyx;// 相关意向
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the jtcyfzqk
	 */
	public String getJtcyfzqk() {
		return jtcyfzqk;
	}
	/**
	 * @param jtcyfzqk要设置的 jtcyfzqk
	 */
	public void setJtcyfzqk(String jtcyfzqk) {
		this.jtcyfzqk = jtcyfzqk;
	}
	/**
	 * @return the sxdwgznr
	 */
	public String getSxdwgznr() {
		return sxdwgznr;
	}
	/**
	 * @param sxdwgznr要设置的 sxdwgznr
	 */
	public void setSxdwgznr(String sxdwgznr) {
		this.sxdwgznr = sxdwgznr;
	}
	/**
	 * @return the bysjtm
	 */
	public String getBysjtm() {
		return bysjtm;
	}
	/**
	 * @param bysjtm要设置的 bysjtm
	 */
	public void setBysjtm(String bysjtm) {
		this.bysjtm = bysjtm;
	}
	/**
	 * @return the tc
	 */
	public String getTc() {
		return tc;
	}
	/**
	 * @param tc要设置的 tc
	 */
	public void setTc(String tc) {
		this.tc = tc;
	}
	/**
	 * @return the qtyyqk
	 */
	public String getQtyyqk() {
		return qtyyqk;
	}
	/**
	 * @param qtyyqk要设置的 qtyyqk
	 */
	public void setQtyyqk(String qtyyqk) {
		this.qtyyqk = qtyyqk;
	}
	/**
	 * @return the brgzzy
	 */
	public String getBrgzzy() {
		return brgzzy;
	}
	/**
	 * @param brgzzy要设置的 brgzzy
	 */
	public void setBrgzzy(String brgzzy) {
		this.brgzzy = brgzzy;
	}
	/**
	 * @return the zwjd
	 */
	public String getZwjd() {
		return zwjd;
	}
	/**
	 * @param zwjd要设置的 zwjd
	 */
	public void setZwjd(String zwjd) {
		this.zwjd = zwjd;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xb要设置的 xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param nj要设置的 nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the bynd
	 */
	public String getBynd() {
		return bynd;
	}
	/**
	 * @param bynd要设置的 bynd
	 */
	public void setBynd(String bynd) {
		this.bynd = bynd;
	}
	/**
	 * @return the xy
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xy要设置的 xy
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydm要设置的 xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @param zydm要设置的 zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymc要设置的 zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmc要设置的 bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the pyccmc
	 */
	public String getPyccmc() {
		return pyccmc;
	}
	/**
	 * @param pyccmc要设置的 pyccmc
	 */
	public void setPyccmc(String pyccmc) {
		this.pyccmc = pyccmc;
	}
	
	/**
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}
	/**
	 * @param sfzh要设置的 sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user要设置的 user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the jgs
	 */
	public String getJgs() {
		return jgs;
	}
	/**
	 * @param jgs要设置的 jgs
	 */
	public void setJgs(String jgs) {
		this.jgs = jgs;
	}
	/**
	 * @return the jgshi
	 */
	public String getJgshi() {
		return jgshi;
	}
	/**
	 * @param jgshi要设置的 jgshi
	 */
	public void setJgshi(String jgshi) {
		this.jgshi = jgshi;
	}
	/**
	 * @return the jgx
	 */
	public String getJgx() {
		return jgx;
	}
	/**
	 * @param jgx要设置的 jgx
	 */
	public void setJgx(String jgx) {
		this.jgx = jgx;
	}
	/**
	 * @return the ssbh
	 */
	public String getSsbh() {
		return ssbh;
	}
	/**
	 * @param ssbh要设置的 ssbh
	 */
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	/**
	 * @return the rxnj
	 */
	public String getRxnj() {
		return rxnj;
	}
	/**
	 * @param rxnj要设置的 rxnj
	 */
	public void setRxnj(String rxnj) {
		this.rxnj = rxnj;
	}
	/**
	 * @return the nfby
	 */
	public String getNfby() {
		return nfby;
	}
	/**
	 * @param nfby要设置的 nfby
	 */
	public void setNfby(String nfby) {
		this.nfby = nfby;
	}
	/**
	 * @return the sfzc
	 */
	public String getSfzc() {
		return sfzc;
	}
	/**
	 * @param sfzc要设置的 sfzc
	 */
	public void setSfzc(String sfzc) {
		this.sfzc = sfzc;
	}
	/**
	 * @return the dasfyl
	 */
	public String getDasfyl() {
		return dasfyl;
	}
	/**
	 * @param dasfyl要设置的 dasfyl
	 */
	public void setDasfyl(String dasfyl) {
		this.dasfyl = dasfyl;
	}
	/**
	 * @return the daylyy
	 */
	public String getDaylyy() {
		return daylyy;
	}
	/**
	 * @param daylyy要设置的 daylyy
	 */
	public void setDaylyy(String daylyy) {
		this.daylyy = daylyy;
	}
	/**
	 * @return the yxdm
	 */
	public String getYxdm() {
		return yxdm;
	}
	/**
	 * @param yxdm要设置的 yxdm
	 */
	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
	}
	/**
	 * @return the sfzz
	 */
	public String getSfzz() {
		return sfzz;
	}
	/**
	 * @param sfzz要设置的 sfzz
	 */
	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}
	/**
	 * @return the sfsf
	 */
	public String getSfsf() {
		return sfsf;
	}
	/**
	 * @param sfsf要设置的 sfsf
	 */
	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
	}
	/**
	 * @return the sfdl
	 */
	public String getSfdl() {
		return sfdl;
	}
	/**
	 * @param sfdl要设置的 sfdl
	 */
	public void setSfdl(String sfdl) {
		this.sfdl = sfdl;
	}
	/**
	 * @return the dxhwp
	 */
	public String getDxhwp() {
		return dxhwp;
	}
	/**
	 * @param dxhwp要设置的 dxhwp
	 */
	public void setDxhwp(String dxhwp) {
		this.dxhwp = dxhwp;
	}
	/**
	 * @return the bysj
	 */
	public String getBysj() {
		return bysj;
	}
	/**
	 * @param bysj要设置的 bysj
	 */
	public void setBysj(String bysj) {
		this.bysj = bysj;
	}
	/**
	 * @return the zxwyyzdm
	 */
	public String getZxwyyzdm() {
		return zxwyyzdm;
	}
	/**
	 * @param zxwyyzdm要设置的 zxwyyzdm
	 */
	public void setZxwyyzdm(String zxwyyzdm) {
		this.zxwyyzdm = zxwyyzdm;
	}
	/**
	 * @return the wydj
	 */
	public String getWydj() {
		return wydj;
	}
	/**
	 * @param wydj要设置的 wydj
	 */
	public void setWydj(String wydj) {
		this.wydj = wydj;
	}
	/**
	 * @return the jsjdj
	 */
	public String getJsjdj() {
		return jsjdj;
	}
	/**
	 * @param jsjdj要设置的 jsjdj
	 */
	public void setJsjdj(String jsjdj) {
		this.jsjdj = jsjdj;
	}
	/**
	 * @return the lxdz
	 */
	public String getLxdz() {
		return lxdz;
	}
	/**
	 * @param lxdz要设置的 lxdz
	 */
	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}
	/**
	 * @return the yzbm
	 */
	public String getYzbm() {
		return yzbm;
	}
	/**
	 * @param yzbm要设置的 yzbm
	 */
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	/**
	 * @return the shzw
	 */
	public String getShzw() {
		return shzw;
	}
	/**
	 * @param shzw要设置的 shzw
	 */
	public void setShzw(String shzw) {
		this.shzw = shzw;
	}
	/**
	 * @return the jypx
	 */
	public String getJypx() {
		return jypx;
	}
	/**
	 * @param jypx要设置的 jypx
	 */
	public void setJypx(String jypx) {
		this.jypx = jypx;
	}
	/**
	 * @return the xmsj
	 */
	public String getXmsj() {
		return xmsj;
	}
	/**
	 * @param xmsj要设置的 xmsj
	 */
	public void setXmsj(String xmsj) {
		this.xmsj = xmsj;
	}
	/**
	 * @return the zgzs
	 */
	public String getZgzs() {
		return zgzs;
	}
	/**
	 * @param zgzs要设置的 zgzs
	 */
	public void setZgzs(String zgzs) {
		this.zgzs = zgzs;
	}
	/**
	 * @return the jljn
	 */
	public String getJljn() {
		return jljn;
	}
	/**
	 * @param jljn要设置的 jljn
	 */
	public void setJljn(String jljn) {
		this.jljn = jljn;
	}
	/**
	 * @return the sybz1
	 */
	public String getSybz1() {
		return sybz1;
	}
	/**
	 * @param sybz1要设置的 sybz1
	 */
	public void setSybz1(String sybz1) {
		this.sybz1 = sybz1;
	}
	/**
	 * @return the sybz2
	 */
	public String getSybz2() {
		return sybz2;
	}
	/**
	 * @param sybz2要设置的 sybz2
	 */
	public void setSybz2(String sybz2) {
		this.sybz2 = sybz2;
	}
	/**
	 * @return the sybz3
	 */
	public String getSybz3() {
		return sybz3;
	}
	/**
	 * @param sybz3要设置的 sybz3
	 */
	public void setSybz3(String sybz3) {
		this.sybz3 = sybz3;
	}
	/**
	 * @return the xldm
	 */
	public String getXldm() {
		return xldm;
	}
	/**
	 * @param xldm要设置的 xldm
	 */
	public void setXldm(String xldm) {
		this.xldm = xldm;
	}
	/**
	 * @return the zkzh
	 */
	public String getZkzh() {
		return zkzh;
	}
	/**
	 * @param zkzh要设置的 zkzh
	 */
	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}
	/**
	 * @return the grjl
	 */
	public String getGrjl() {
		return grjl;
	}
	/**
	 * @param grjl要设置的 grjl
	 */
	public void setGrjl(String grjl) {
		this.grjl = grjl;
	}
	/**
	 * @return the sfcj
	 */
	public String getSfcj() {
		return sfcj;
	}
	/**
	 * @param sfcj要设置的 sfcj
	 */
	public void setSfcj(String sfcj) {
		this.sfcj = sfcj;
	}
	/**
	 * @return the ssch
	 */
	public String getSsch() {
		return ssch;
	}
	/**
	 * @param ssch要设置的 ssch
	 */
	public void setSsch(String ssch) {
		this.ssch = ssch;
	}
	/**
	 * @return the rzrq
	 */
	public String getRzrq() {
		return rzrq;
	}
	/**
	 * @param rzrq要设置的 rzrq
	 */
	public void setRzrq(String rzrq) {
		this.rzrq = rzrq;
	}
	/**
	 * @return the zsjzrq
	 */
	public String getZsjzrq() {
		return zsjzrq;
	}
	/**
	 * @param zsjzrq要设置的 zsjzrq
	 */
	public void setZsjzrq(String zsjzrq) {
		this.zsjzrq = zsjzrq;
	}
	/**
	 * @return the qsdh
	 */
	public String getQsdh() {
		return qsdh;
	}
	/**
	 * @param qsdh要设置的 qsdh
	 */
	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}
	/**
	 * @return the ykth
	 */
	public String getYkth() {
		return ykth;
	}
	/**
	 * @param ykth要设置的 ykth
	 */
	public void setYkth(String ykth) {
		this.ykth = ykth;
	}
	/**
	 * @return the yhkh
	 */
	public String getYhkh() {
		return yhkh;
	}
	/**
	 * @param yhkh要设置的 yhkh
	 */
	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}
	/**
	 * @return the xslb
	 */
	public String getXslb() {
		return xslb;
	}
	/**
	 * @param xslb要设置的 xslb
	 */
	public void setXslb(String xslb) {
		this.xslb = xslb;
	}
	/**
	 * @return the xslx
	 */
	public String getXslx() {
		return xslx;
	}
	/**
	 * @param xslx要设置的 xslx
	 */
	public void setXslx(String xslx) {
		this.xslx = xslx;
	}
	/**
	 * @return the sfbys
	 */
	public String getSfbys() {
		return sfbys;
	}
	/**
	 * @param sfbys要设置的 sfbys
	 */
	public void setSfbys(String sfbys) {
		this.sfbys = sfbys;
	}
	/**
	 * @return the yhdm
	 */
	public String getYhdm() {
		return yhdm;
	}
	/**
	 * @param yhdm要设置的 yhdm
	 */
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	/**
	 * @return the hkshen
	 */
	public String getHkshen() {
		return hkshen;
	}
	/**
	 * @param hkshen要设置的 hkshen
	 */
	public void setHkshen(String hkshen) {
		this.hkshen = hkshen;
	}
	/**
	 * @return the hkshi
	 */
	public String getHkshi() {
		return hkshi;
	}
	/**
	 * @param hkshi要设置的 hkshi
	 */
	public void setHkshi(String hkshi) {
		this.hkshi = hkshi;
	}
	/**
	 * @return the hkxian
	 */
	public String getHkxian() {
		return hkxian;
	}
	/**
	 * @param hkxian要设置的 hkxian
	 */
	public void setHkxian(String hkxian) {
		this.hkxian = hkxian;
	}
	/**
	 * @return the zcsxhm
	 */
	public String getZcsxhm() {
		return zcsxhm;
	}
	/**
	 * @param zcsxhm要设置的 zcsxhm
	 */
	public void setZcsxhm(String zcsxhm) {
		this.zcsxhm = zcsxhm;
	}
	/**
	 * @return the rxqwhcd
	 */
	public String getRxqwhcd() {
		return rxqwhcd;
	}
	/**
	 * @param rxqwhcd要设置的 rxqwhcd
	 */
	public void setRxqwhcd(String rxqwhcd) {
		this.rxqwhcd = rxqwhcd;
	}
	/**
	 * @return the xsqrxxbz
	 */
	public String getXsqrxxbz() {
		return xsqrxxbz;
	}
	/**
	 * @param xsqrxxbz要设置的 xsqrxxbz
	 */
	public void setXsqrxxbz(String xsqrxxbz) {
		this.xsqrxxbz = xsqrxxbz;
	}
	/**
	 * @return the dah
	 */
	public String getDah() {
		return dah;
	}
	/**
	 * @param dah要设置的 dah
	 */
	public void setDah(String dah) {
		this.dah = dah;
	}
	/**
	 * @return the ylbxh
	 */
	public String getYlbxh() {
		return ylbxh;
	}
	/**
	 * @param ylbxh要设置的 ylbxh
	 */
	public void setYlbxh(String ylbxh) {
		this.ylbxh = ylbxh;
	}
	/**
	 * @return the rxqdwdz
	 */
	public String getRxqdwdz() {
		return rxqdwdz;
	}
	/**
	 * @param rxqdwdz要设置的 rxqdwdz
	 */
	public void setRxqdwdz(String rxqdwdz) {
		this.rxqdwdz = rxqdwdz;
	}
	/**
	 * @return the rxqdwyb
	 */
	public String getRxqdwyb() {
		return rxqdwyb;
	}
	/**
	 * @param rxqdwyb要设置的 rxqdwyb
	 */
	public void setRxqdwyb(String rxqdwyb) {
		this.rxqdwyb = rxqdwyb;
	}
	/**
	 * @return the rxqdwdh
	 */
	public String getRxqdwdh() {
		return rxqdwdh;
	}
	/**
	 * @param rxqdwdh要设置的 rxqdwdh
	 */
	public void setRxqdwdh(String rxqdwdh) {
		this.rxqdwdh = rxqdwdh;
	}
	/**
	 * @return the gzbx
	 */
	public String getGzbx() {
		return gzbx;
	}
	/**
	 * @param gzbx要设置的 gzbx
	 */
	public void setGzbx(String gzbx) {
		this.gzbx = gzbx;
	}
	/**
	 * @return the sfgat
	 */
	public String getSfgat() {
		return sfgat;
	}
	/**
	 * @param sfgat要设置的 sfgat
	 */
	public void setSfgat(String sfgat) {
		this.sfgat = sfgat;
	}
	/**
	 * @return the sfssmz
	 */
	public String getSfssmz() {
		return sfssmz;
	}
	/**
	 * @param sfssmz要设置的 sfssmz
	 */
	public void setSfssmz(String sfssmz) {
		this.sfssmz = sfssmz;
	}
	/**
	 * @return the sfzd
	 */
	public String getSfzd() {
		return sfzd;
	}
	/**
	 * @param sfzd要设置的 sfzd
	 */
	public void setSfzd(String sfzd) {
		this.sfzd = sfzd;
	}
	/**
	 * @return the syds
	 */
	public String getSyds() {
		return syds;
	}
	/**
	 * @param syds要设置的 syds
	 */
	public void setSyds(String syds) {
		this.syds = syds;
	}
	/**
	 * @return the sydshi
	 */
	public String getSydshi() {
		return sydshi;
	}
	/**
	 * @param sydshi要设置的 sydshi
	 */
	public void setSydshi(String sydshi) {
		this.sydshi = sydshi;
	}
	/**
	 * @return the sydx
	 */
	public String getSydx() {
		return sydx;
	}
	/**
	 * @param sydx要设置的 sydx
	 */
	public void setSydx(String sydx) {
		this.sydx = sydx;
	}
	/**
	 * @return the byzh
	 */
	public String getByzh() {
		return byzh;
	}
	/**
	 * @param byzh要设置的 byzh
	 */
	public void setByzh(String byzh) {
		this.byzh = byzh;
	}
	/**
	 * @return the xjh
	 */
	public String getXjh() {
		return xjh;
	}
	/**
	 * @param xjh要设置的 xjh
	 */
	public void setXjh(String xjh) {
		this.xjh = xjh;
	}
	/**
	 * @return the jrzzmmrq
	 */
	public String getJrzzmmrq() {
		return jrzzmmrq;
	}
	/**
	 * @param jrzzmmrq要设置的 jrzzmmrq
	 */
	public void setJrzzmmrq(String jrzzmmrq) {
		this.jrzzmmrq = jrzzmmrq;
	}
	/**
	 * @return the sfhq
	 */
	public String getSfhq() {
		return sfhq;
	}
	/**
	 * @param sfhq要设置的 sfhq
	 */
	public void setSfhq(String sfhq) {
		this.sfhq = sfhq;
	}
	/**
	 * @return the csds
	 */
	public String getCsds() {
		return csds;
	}
	/**
	 * @param csds要设置的 csds
	 */
	public void setCsds(String csds) {
		this.csds = csds;
	}
	/**
	 * @return the csdshi
	 */
	public String getCsdshi() {
		return csdshi;
	}
	/**
	 * @param csdshi要设置的 csdshi
	 */
	public void setCsdshi(String csdshi) {
		this.csdshi = csdshi;
	}
	/**
	 * @return the csdxian
	 */
	public String getCsdxian() {
		return csdxian;
	}
	/**
	 * @param csdxian要设置的 csdxian
	 */
	public void setCsdxian(String csdxian) {
		this.csdxian = csdxian;
	}
	/**
	 * @return the xy
	 */
	public String getXy() {
		return xy;
	}
	/**
	 * @param xy要设置的 xy
	 */
	public void setXy(String xy) {
		this.xy = xy;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the xmpy
	 */
	public String getXmpy() {
		return xmpy;
	}
	/**
	 * @param xmpy要设置的 xmpy
	 */
	public void setXmpy(String xmpy) {
		this.xmpy = xmpy;
	}
	/**
	 * @return the syd
	 */
	public String getSyd() {
		return syd;
	}
	/**
	 * @param syd要设置的 syd
	 */
	public void setSyd(String syd) {
		this.syd = syd;
	}
	/**
	 * @return the csrq
	 */
	public String getCsrq() {
		return csrq;
	}
	/**
	 * @param csrq要设置的 csrq
	 */
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	/**
	 * @return the mz
	 */
	public String getMz() {
		return mz;
	}
	/**
	 * @param mz要设置的 mz
	 */
	public void setMz(String mz) {
		this.mz = mz;
	}
	/**
	 * @return the zzmm
	 */
	public String getZzmm() {
		return zzmm;
	}
	/**
	 * @param zzmm要设置的 zzmm
	 */
	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdh要设置的 lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the dzyx
	 */
	public String getDzyx() {
		return dzyx;
	}
	/**
	 * @param dzyx要设置的 dzyx
	 */
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	/**
	 * @return the cym
	 */
	public String getCym() {
		return cym;
	}
	/**
	 * @param cym要设置的 cym
	 */
	public void setCym(String cym) {
		this.cym = cym;
	}
	/**
	 * @return the sg
	 */
	public String getSg() {
		return sg;
	}
	/**
	 * @param sg要设置的 sg
	 */
	public void setSg(String sg) {
		this.sg = sg;
	}
	/**
	 * @return the tz
	 */
	public String getTz() {
		return tz;
	}
	/**
	 * @param tz要设置的 tz
	 */
	public void setTz(String tz) {
		this.tz = tz;
	}
	/**
	 * @return the kslb
	 */
	public String getKslb() {
		return kslb;
	}
	/**
	 * @param kslb要设置的 kslb
	 */
	public void setKslb(String kslb) {
		this.kslb = kslb;
	}
	/**
	 * @return the rxfs
	 */
	public String getRxfs() {
		return rxfs;
	}
	/**
	 * @param rxfs要设置的 rxfs
	 */
	public void setRxfs(String rxfs) {
		this.rxfs = rxfs;
	}
	/**
	 * @return the pyfs
	 */
	public String getPyfs() {
		return pyfs;
	}
	/**
	 * @param pyfs要设置的 pyfs
	 */
	public void setPyfs(String pyfs) {
		this.pyfs = pyfs;
	}
	/**
	 * @return the pycc
	 */
	public String getPycc() {
		return pycc;
	}
	/**
	 * @param pycc要设置的 pycc
	 */
	public void setPycc(String pycc) {
		this.pycc = pycc;
	}
	/**
	 * @return the xjlb
	 */
	public String getXjlb() {
		return xjlb;
	}
	/**
	 * @param xjlb要设置的 xjlb
	 */
	public void setXjlb(String xjlb) {
		this.xjlb = xjlb;
	}
	/**
	 * @return the xszp
	 */
	public String getXszp() {
		return xszp;
	}
	/**
	 * @param xszp要设置的 xszp
	 */
	public void setXszp(String xszp) {
		this.xszp = xszp;
	}
	/**
	 * @return the xsgkzp
	 */
	public String getXsgkzp() {
		return xsgkzp;
	}
	/**
	 * @param xsgkzp要设置的 xsgkzp
	 */
	public void setXsgkzp(String xsgkzp) {
		this.xsgkzp = xsgkzp;
	}
	/**
	 * @return the xjztm
	 */
	public String getXjztm() {
		return xjztm;
	}
	/**
	 * @param xjztm要设置的 xjztm
	 */
	public void setXjztm(String xjztm) {
		this.xjztm = xjztm;
	}
	/**
	 * @return the xz
	 */
	public String getXz() {
		return xz;
	}
	/**
	 * @param xz要设置的 xz
	 */
	public void setXz(String xz) {
		this.xz = xz;
	}
	/**
	 * @return the whcd
	 */
	public String getWhcd() {
		return whcd;
	}
	/**
	 * @param whcd要设置的 whcd
	 */
	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}
	/**
	 * @return the rxqdw
	 */
	public String getRxqdw() {
		return rxqdw;
	}
	/**
	 * @param rxqdw要设置的 rxqdw
	 */
	public void setRxqdw(String rxqdw) {
		this.rxqdw = rxqdw;
	}
	/**
	 * @return the jtdh
	 */
	public String getJtdh() {
		return jtdh;
	}
	/**
	 * @param jtdh要设置的 jtdh
	 */
	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}
	/**
	 * @return the jrgqtsj
	 */
	public String getJrgqtsj() {
		return jrgqtsj;
	}
	/**
	 * @param jrgqtsj要设置的 jrgqtsj
	 */
	public void setJrgqtsj(String jrgqtsj) {
		this.jrgqtsj = jrgqtsj;
	}
	/**
	 * @return the jrgcdsj
	 */
	public String getJrgcdsj() {
		return jrgcdsj;
	}
	/**
	 * @param jrgcdsj要设置的 jrgcdsj
	 */
	public void setJrgcdsj(String jrgcdsj) {
		this.jrgcdsj = jrgcdsj;
	}
	/**
	 * @return the jtcygc
	 */
	public String getJtcygc() {
		return jtcygc;
	}
	/**
	 * @param jtcygc要设置的 jtcygc
	 */
	public void setJtcygc(String jtcygc) {
		this.jtcygc = jtcygc;
	}
	/**
	 * @return the jlcfjl
	 */
	public String getJlcfjl() {
		return jlcfjl;
	}
	/**
	 * @param jlcfjl要设置的 jlcfjl
	 */
	public void setJlcfjl(String jlcfjl) {
		this.jlcfjl = jlcfjl;
	}
	/**
	 * @return the jkzk
	 */
	public String getJkzk() {
		return jkzk;
	}
	/**
	 * @param jkzk要设置的 jkzk
	 */
	public void setJkzk(String jkzk) {
		this.jkzk = jkzk;
	}
	/**
	 * @return the jtdz
	 */
	public String getJtdz() {
		return jtdz;
	}
	/**
	 * @param jtdz要设置的 jtdz
	 */
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	/**
	 * @return the jtyb
	 */
	public String getJtyb() {
		return jtyb;
	}
	/**
	 * @param jtyb要设置的 jtyb
	 */
	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}
	/**
	 * @return the jg
	 */
	public String getJg() {
		return jg;
	}
	/**
	 * @param jg要设置的 jg
	 */
	public void setJg(String jg) {
		this.jg = jg;
	}
	/**
	 * @return the xx
	 */
	public String getXx() {
		return xx;
	}
	/**
	 * @param xx要设置的 xx
	 */
	public void setXx(String xx) {
		this.xx = xx;
	}
	/**
	 * @return the ah
	 */
	public String getAh() {
		return ah;
	}
	/**
	 * @param ah要设置的 ah
	 */
	public void setAh(String ah) {
		this.ah = ah;
	}
	/**
	 * @return the sfdk
	 */
	public String getSfdk() {
		return sfdk;
	}
	/**
	 * @param sfdk要设置的 sfdk
	 */
	public void setSfdk(String sfdk) {
		this.sfdk = sfdk;
	}
	/**
	 * @return the shgxxm1
	 */
	public String getShgxxm1() {
		return shgxxm1;
	}
	/**
	 * @param shgxxm1要设置的 shgxxm1
	 */
	public void setShgxxm1(String shgxxm1) {
		this.shgxxm1 = shgxxm1;
	}
	/**
	 * @return the shgxgx1
	 */
	public String getShgxgx1() {
		return shgxgx1;
	}
	/**
	 * @param shgxgx1要设置的 shgxgx1
	 */
	public void setShgxgx1(String shgxgx1) {
		this.shgxgx1 = shgxgx1;
	}
	/**
	 * @return the shgxgzdw1
	 */
	public String getShgxgzdw1() {
		return shgxgzdw1;
	}
	/**
	 * @param shgxgzdw1要设置的 shgxgzdw1
	 */
	public void setShgxgzdw1(String shgxgzdw1) {
		this.shgxgzdw1 = shgxgzdw1;
	}
	/**
	 * @return the shgxzw1
	 */
	public String getShgxzw1() {
		return shgxzw1;
	}
	/**
	 * @param shgxzw1要设置的 shgxzw1
	 */
	public void setShgxzw1(String shgxzw1) {
		this.shgxzw1 = shgxzw1;
	}
	/**
	 * @return the shgxdwdh1
	 */
	public String getShgxdwdh1() {
		return shgxdwdh1;
	}
	/**
	 * @param shgxdwdh1要设置的 shgxdwdh1
	 */
	public void setShgxdwdh1(String shgxdwdh1) {
		this.shgxdwdh1 = shgxdwdh1;
	}
	/**
	 * @return the shgxsjhm1
	 */
	public String getShgxsjhm1() {
		return shgxsjhm1;
	}
	/**
	 * @param shgxsjhm1要设置的 shgxsjhm1
	 */
	public void setShgxsjhm1(String shgxsjhm1) {
		this.shgxsjhm1 = shgxsjhm1;
	}
	/**
	 * @return the shgxxm2
	 */
	public String getShgxxm2() {
		return shgxxm2;
	}
	/**
	 * @param shgxxm2要设置的 shgxxm2
	 */
	public void setShgxxm2(String shgxxm2) {
		this.shgxxm2 = shgxxm2;
	}
	/**
	 * @return the shgxgx2
	 */
	public String getShgxgx2() {
		return shgxgx2;
	}
	/**
	 * @param shgxgx2要设置的 shgxgx2
	 */
	public void setShgxgx2(String shgxgx2) {
		this.shgxgx2 = shgxgx2;
	}
	/**
	 * @return the shgxgzdw2
	 */
	public String getShgxgzdw2() {
		return shgxgzdw2;
	}
	/**
	 * @param shgxgzdw2要设置的 shgxgzdw2
	 */
	public void setShgxgzdw2(String shgxgzdw2) {
		this.shgxgzdw2 = shgxgzdw2;
	}
	/**
	 * @return the shgxzw2
	 */
	public String getShgxzw2() {
		return shgxzw2;
	}
	/**
	 * @param shgxzw2要设置的 shgxzw2
	 */
	public void setShgxzw2(String shgxzw2) {
		this.shgxzw2 = shgxzw2;
	}
	/**
	 * @return the shgxdwdh2
	 */
	public String getShgxdwdh2() {
		return shgxdwdh2;
	}
	/**
	 * @param shgxdwdh2要设置的 shgxdwdh2
	 */
	public void setShgxdwdh2(String shgxdwdh2) {
		this.shgxdwdh2 = shgxdwdh2;
	}
	/**
	 * @return the shgxsjhm2
	 */
	public String getShgxsjhm2() {
		return shgxsjhm2;
	}
	/**
	 * @param shgxsjhm2要设置的 shgxsjhm2
	 */
	public void setShgxsjhm2(String shgxsjhm2) {
		this.shgxsjhm2 = shgxsjhm2;
	}
	/**
	 * @return the jtqkjj
	 */
	public String getJtqkjj() {
		return jtqkjj;
	}
	/**
	 * @param jtqkjj要设置的 jtqkjj
	 */
	public void setJtqkjj(String jtqkjj) {
		this.jtqkjj = jtqkjj;
	}
	/**
	 * @return the jtjjqk
	 */
	public String getJtjjqk() {
		return jtjjqk;
	}
	/**
	 * @param jtjjqk要设置的 jtjjqk
	 */
	public void setJtjjqk(String jtjjqk) {
		this.jtjjqk = jtjjqk;
	}
	/**
	 * @return the sjhm
	 */
	public String getSjhm() {
		return sjhm;
	}
	/**
	 * @param sjhm要设置的 sjhm
	 */
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	/**
	 * @return the byxx
	 */
	public String getByxx() {
		return byxx;
	}
	/**
	 * @param byxx要设置的 byxx
	 */
	public void setByxx(String byxx) {
		this.byxx = byxx;
	}
	/**
	 * @return the kh
	 */
	public String getKh() {
		return kh;
	}
	/**
	 * @param kh要设置的 kh
	 */
	public void setKh(String kh) {
		this.kh = kh;
	}
	/**
	 * @return the rxrq
	 */
	public String getRxrq() {
		return rxrq;
	}
	/**
	 * @param rxrq要设置的 rxrq
	 */
	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}
	/**
	 * @return the fdyxm
	 */
	public String getFdyxm() {
		return fdyxm;
	}
	/**
	 * @param fdyxm要设置的 fdyxm
	 */
	public void setFdyxm(String fdyxm) {
		this.fdyxm = fdyxm;
	}
	/**
	 * @return the gkcj
	 */
	public String getGkcj() {
		return gkcj;
	}
	/**
	 * @param gkcj要设置的 gkcj
	 */
	public void setGkcj(String gkcj) {
		this.gkcj = gkcj;
	}
	/**
	 * @return the qqhm
	 */
	public String getQqhm() {
		return qqhm;
	}
	/**
	 * @param qqhm要设置的 qqhm
	 */
	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}
	/**
	 * @return the hkxz
	 */
	public String getHkxz() {
		return hkxz;
	}
	/**
	 * @param hkxz要设置的 hkxz
	 */
	public void setHkxz(String hkxz) {
		this.hkxz = hkxz;
	}
	/**
	 * @return the zyjb
	 */
	public String getZyjb() {
		return zyjb;
	}
	/**
	 * @param zyjb要设置的 zyjb
	 */
	public void setZyjb(String zyjb) {
		this.zyjb = zyjb;
	}
	/**
	 * @return the hkszd
	 */
	public String getHkszd() {
		return hkszd;
	}
	/**
	 * @param hkszd要设置的 hkszd
	 */
	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}
	/**
	 * @return the ssyq
	 */
	public String getSsyq() {
		return ssyq;
	}
	/**
	 * @param ssyq要设置的 ssyq
	 */
	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}
	/**
	 * @return the ssld
	 */
	public String getSsld() {
		return ssld;
	}
	/**
	 * @param ssld要设置的 ssld
	 */
	public void setSsld(String ssld) {
		this.ssld = ssld;
	}
	/**
	 * @return the jtdzs
	 */
	public String getJtdzs() {
		return jtdzs;
	}
	/**
	 * @param jtdzs要设置的 jtdzs
	 */
	public void setJtdzs(String jtdzs) {
		this.jtdzs = jtdzs;
	}
	/**
	 * @return the jtdzx
	 */
	public String getJtdzx() {
		return jtdzx;
	}
	/**
	 * @param jtdzx要设置的 jtdzx
	 */
	public void setJtdzx(String jtdzx) {
		this.jtdzx = jtdzx;
	}
	/**
	 * @return the sfzsb
	 */
	public String getSfzsb() {
		return sfzsb;
	}
	/**
	 * @param sfzsb要设置的 sfzsb
	 */
	public void setSfzsb(String sfzsb) {
		this.sfzsb = sfzsb;
	}
	/**
	 * @return the sfzfx
	 */
	public String getSfzfx() {
		return sfzfx;
	}
	/**
	 * @param sfzfx要设置的 sfzfx
	 */
	public void setSfzfx(String sfzfx) {
		this.sfzfx = sfzfx;
	}
	/**
	 * @return the zjdm
	 */
	public String getZjdm() {
		return zjdm;
	}
	/**
	 * @param zjdm要设置的 zjdm
	 */
	public void setZjdm(String zjdm) {
		this.zjdm = zjdm;
	}
	/**
	 * @return the sfyby
	 */
	public String getSfyby() {
		return sfyby;
	}
	/**
	 * @param sfyby要设置的 sfyby
	 */
	public void setSfyby(String sfyby) {
		this.sfyby = sfyby;
	}
	/**
	 * @return the sfzx
	 */
	public String getSfzx() {
		return sfzx;
	}
	/**
	 * @param sfzx要设置的 sfzx
	 */
	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}
	/**
	 * @return the zw
	 */
	public String getZw() {
		return zw;
	}
	/**
	 * @param zw要设置的 zw
	 */
	public void setZw(String zw) {
		this.zw = zw;
	}
	/**
	 * @return the thbs
	 */
	public String getThbs() {
		return thbs;
	}
	/**
	 * @param thbs要设置的 thbs
	 */
	public void setThbs(String thbs) {
		this.thbs = thbs;
	}
	/**
	 * @return the dybj
	 */
	public String getDybj() {
		return dybj;
	}
	/**
	 * @param dybj要设置的 dybj
	 */
	public void setDybj(String dybj) {
		this.dybj = dybj;
	}
	/**
	 * @return the shbj
	 */
	public String getShbj() {
		return shbj;
	}
	/**
	 * @param shbj要设置的 shbj
	 */
	public void setShbj(String shbj) {
		this.shbj = shbj;
	}
	/**
	 * @return the xwzsxlh
	 */
	public String getXwzsxlh() {
		return xwzsxlh;
	}
	/**
	 * @param xwzsxlh要设置的 xwzsxlh
	 */
	public void setXwzsxlh(String xwzsxlh) {
		this.xwzsxlh = xwzsxlh;
	}
	/**
	 * @return the xwzsbh
	 */
	public String getXwzsbh() {
		return xwzsbh;
	}
	/**
	 * @param xwzsbh要设置的 xwzsbh
	 */
	public void setXwzsbh(String xwzsbh) {
		this.xwzsbh = xwzsbh;
	}
	/**
	 * @return the xw
	 */
	public String getXw() {
		return xw;
	}
	/**
	 * @param xw要设置的 xw
	 */
	public void setXw(String xw) {
		this.xw = xw;
	}
	/**
	 * @return the xzxm
	 */
	public String getXzxm() {
		return xzxm;
	}
	/**
	 * @param xzxm要设置的 xzxm
	 */
	public void setXzxm(String xzxm) {
		this.xzxm = xzxm;
	}
	/**
	 * @return the zsxlh
	 */
	public String getZsxlh() {
		return zsxlh;
	}
	/**
	 * @param zsxlh要设置的 zsxlh
	 */
	public void setZsxlh(String zsxlh) {
		this.zsxlh = zsxlh;
	}
	/**
	 * @return the zsbh
	 */
	public String getZsbh() {
		return zsbh;
	}
	/**
	 * @param zsbh要设置的 zsbh
	 */
	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}
	/**
	 * @return the bjyjl
	 */
	public String getBjyjl() {
		return bjyjl;
	}
	/**
	 * @param bjyjl要设置的 bjyjl
	 */
	public void setBjyjl(String bjyjl) {
		this.bjyjl = bjyjl;
	}
	/**
	 * @return the csd
	 */
	public String getCsd() {
		return csd;
	}
	/**
	 * @param csd要设置的 csd
	 */
	public void setCsd(String csd) {
		this.csd = csd;
	}
	/**
	 * @return the zsjj
	 */
	public String getZsjj() {
		return zsjj;
	}
	/**
	 * @param zsjj要设置的 zsjj
	 */
	public void setZsjj(String zsjj) {
		this.zsjj = zsjj;
	}
	/**
	 * @return the xxxs
	 */
	public String getXxxs() {
		return xxxs;
	}
	/**
	 * @param xxxs要设置的 xxxs
	 */
	public void setXxxs(String xxxs) {
		this.xxxs = xxxs;
	}
	/**
	 * @return the bxlx
	 */
	public String getBxlx() {
		return bxlx;
	}
	/**
	 * @param bxlx要设置的 bxlx
	 */
	public void setBxlx(String bxlx) {
		this.bxlx = bxlx;
	}
	/**
	 * @return the bxxs
	 */
	public String getBxxs() {
		return bxxs;
	}
	/**
	 * @param bxxs要设置的 bxxs
	 */
	public void setBxxs(String bxxs) {
		this.bxxs = bxxs;
	}
	/**
	 * @return the fxzyfx
	 */
	public String getFxzyfx() {
		return fxzyfx;
	}
	/**
	 * @param fxzyfx要设置的 fxzyfx
	 */
	public void setFxzyfx(String fxzyfx) {
		this.fxzyfx = fxzyfx;
	}
	/**
	 * @return the fxzy
	 */
	public String getFxzy() {
		return fxzy;
	}
	/**
	 * @param fxzy要设置的 fxzy
	 */
	public void setFxzy(String fxzy) {
		this.fxzy = fxzy;
	}
	/**
	 * @return the zylb
	 */
	public String getZylb() {
		return zylb;
	}
	/**
	 * @param zylb要设置的 zylb
	 */
	public void setZylb(String zylb) {
		this.zylb = zylb;
	}
	/**
	 * @return the dqszj
	 */
	public String getDqszj() {
		return dqszj;
	}
	/**
	 * @param dqszj要设置的 dqszj
	 */
	public void setDqszj(String dqszj) {
		this.dqszj = dqszj;
	}
	/**
	 * @return the pyfx
	 */
	public String getPyfx() {
		return pyfx;
	}
	/**
	 * @param pyfx要设置的 pyfx
	 */
	public void setPyfx(String pyfx) {
		this.pyfx = pyfx;
	}
	/**
	 * @return the zyfx
	 */
	public String getZyfx() {
		return zyfx;
	}
	/**
	 * @param zyfx要设置的 zyfx
	 */
	public void setZyfx(String zyfx) {
		this.zyfx = zyfx;
	}
	/**
	 * @return the xxszd
	 */
	public String getXxszd() {
		return xxszd;
	}
	/**
	 * @param xxszd要设置的 xxszd
	 */
	public void setXxszd(String xxszd) {
		this.xxszd = xxszd;
	}
	/**
	 * @return the ksh
	 */
	public String getKsh() {
		return ksh;
	}
	/**
	 * @param ksh要设置的 ksh
	 */
	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	/**
	 * @return the xxfx
	 */
	public String getXxfx() {
		return xxfx;
	}
	/**
	 * @param xxfx要设置的 xxfx
	 */
	public void setXxfx(String xxfx) {
		this.xxfx = xxfx;
	}
	/**
	 * @return the zslb
	 */
	public String getZslb() {
		return zslb;
	}
	/**
	 * @param zslb要设置的 zslb
	 */
	public void setZslb(String zslb) {
		this.zslb = zslb;
	}
	/**
	 * @return the gj
	 */
	public String getGj() {
		return gj;
	}
	/**
	 * @param gj要设置的 gj
	 */
	public void setGj(String gj) {
		this.gj = gj;
	}
	/**
	 * @return the sfjh
	 */
	public String getSfjh() {
		return sfjh;
	}
	/**
	 * @param sfjh要设置的 sfjh
	 */
	public void setSfjh(String sfjh) {
		this.sfjh = sfjh;
	}
	/**
	 * @return the ccqj
	 */
	public String getCcqj() {
		return ccqj;
	}
	/**
	 * @param ccqj要设置的 ccqj
	 */
	public void setCcqj(String ccqj) {
		this.ccqj = ccqj;
	}
	/**
	 * @return the byzffztdm
	 */
	public String getByzffztdm() {
		return byzffztdm;
	}
	/**
	 * @param byzffztdm要设置的 byzffztdm
	 */
	public void setByzffztdm(String byzffztdm) {
		this.byzffztdm = byzffztdm;
	}
	/**
	 * @return the xwzsxxdz
	 */
	public String getXwzsxxdz() {
		return xwzsxxdz;
	}
	/**
	 * @param xwzsxxdz要设置的 xwzsxxdz
	 */
	public void setXwzsxxdz(String xwzsxxdz) {
		this.xwzsxxdz = xwzsxxdz;
	}
	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1要设置的 zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2要设置的 zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	/**
	 * @return the zd3
	 */
	public String getZd3() {
		return zd3;
	}
	/**
	 * @param zd3要设置的 zd3
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	/**
	 * @return the zd4
	 */
	public String getZd4() {
		return zd4;
	}
	/**
	 * @param zd4要设置的 zd4
	 */
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	/**
	 * @return the zd5
	 */
	public String getZd5() {
		return zd5;
	}
	/**
	 * @param zd5要设置的 zd5
	 */
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the sfxyqgzx
	 */
	public String getSfxyqgzx() {
		return sfxyqgzx;
	}
	/**
	 * @param sfxyqgzx要设置的 sfxyqgzx
	 */
	public void setSfxyqgzx(String sfxyqgzx) {
		this.sfxyqgzx = sfxyqgzx;
	}
	/**
	 * @return the xgyx
	 */
	public String getXgyx() {
		return xgyx;
	}
	/**
	 * @param xgyx要设置的 xgyx
	 */
	public void setXgyx(String xgyx) {
		this.xgyx = xgyx;
	}

	
	
	
	

}
