/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午02:44:58 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.Serializable;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @类功能描述: 思政队伍--辅导员信息
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-7-9 下午02:44:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdyxxModel extends SuperAuditModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String gwlbdm ;//岗位类别代码 
	private String khyh ;//开户银行 
	private String hyzk ;//婚姻状况，已婚，未婚 
	private String fdyzbs ;//辅导员值班室 
	private String fdyrzrq ;//辅导员入职日期（何时担任辅导员） 
	private String zyjnzs ;//职业技能证书，选项有无、心理咨询师一级、心理咨询师二级等，可添加项目 
	private String zyjnzs2 ;//职业技能证书，选项有无、心理咨询师一级、心理咨询师二级等，可添加项目 
	private String zyjnzs3 ;//职业技能证书，选项有无、心理咨询师一级、心理咨询师二级等，可添加项目 
	private String age ;//辅导员年龄 
	private String yhzh ;//银行账号 
	private String zzshen ;//省 
	private String zzshi ;//市 
	private String zzxian ;//县 
	private String zgh ;//职工号 
	private String xm ;//姓名 
	private String xb ;//性别代码 
	private String lxdh ;//联系电话 
	private String bmdm ;//所在部门代码 
	private String zw ;//职务 
	private String zyzz ;//主要职责 
	private String zzmm ;//政治面貌代码 
	private String xl ;//学历 
	private String csrq ;//出生日期 
	private String grhjqk ;//个人获奖情况 
	private String gzjl ;//工作经历 
	private String bz ;//备注 
	private String mz ;//民族代码 
	private String jg ;//籍贯代码 
	private String zc ;//职称 
	private String lxgzsj ;//入校工作时间 
	private String xsgzyjfx ;//学生工作研究方向 
	private String jtzz ;//家庭住址 
	private String yddh ;//移动电话 
	private String dzyx ;//电子邮箱 
	private String fblw ;//发表论文 
	private String kyjl ;//科研经历 
	private String byyx ;//毕业院校 
	private String sxzy ;//所学专业 
	private String yzbm ;// 
	private String xw ;//学位 
	private String rwsj ;//入伍时间 
	private String gzfg ;//工作分工 
	private String csfdysj ;//专(兼)职 
	private String zjz ;//专(兼)职 
	private String zwrzsj ;//任职时间 
	private String jsrzsj ;//技术任职时间 
	private String sjdw ;//上级单位 
	private String txdz ;//通讯地址 
	private String bgdh ;//办公电话 
	private String cz ;//传真 
	private String jrgz ;//兼任工作 
	private String jgxs ;//籍贯县市 
	private String sg ;//身高 
	private String tz ;//体重 
	private String tc ;//特长 
	private String jb ;//级别 
	private String djsj ;//定级时间 
	private String szgzsj ;//思政工作时间 
	private String bkbyyx ;//本科生毕业院校 
	private String bksxzy ;//本科生所学专业 
	private String ssbyyx ;//硕士毕业院校 
	private String ssbyzy ;//硕士所学专业 
	private String bsbyyx ;//博士毕业院校 
	private String bsbyzy ;//博士所学专业 
	private String sfzh ;//身份证号 
	private String cjgzrq ;//参加工作日期 
	private String sfbl ;//是否B类 1表示-是 0表示否 
	private String zhxwssxk ;//最后学位所属学科 
	private String pxqk ;//培训情况 
	private String csgz ;//从事工作 
	private String bgdd ;//办公地点 
	private String zdxl ;//在读学历 
	private String zdzy ;//在读专业 
	private String gdxlzl ;//高中以上学历经历 
	private String zyyjjg ;//主要研究成果 
	private String fgnj ;//分管年级 
	private String qtfgnj ;//其他分管年级 
	private String fdyz ;//辅导员组 
	private String fglxdm ;// 
	private String dwlbdm ;//单位类别代码 
	private String kzzd1 ;//微信名 
	private String kzzd2 ;//微博名 
	private String kzzd3 ;//QQ 
	private String kzzd4 ;//评奖评优 
	private String kzzd5 ;//扩展字段5 
	private String sfjryx ;//是否兼任院系老师 
	private String kzzd6 ;//扩展字段6 
	private String kzzd7 ;//扩展字段7 
	private String kzzd8 ;//扩展字段8 
	private String kzzd9 ;//扩展字段9 
	private String kzzd10 ;//扩展字段10 
	private String kzzd11 ;//扩展字段11 
	private String kzzd12 ;//扩展字段12 
	private String kzzd13 ;//扩展字段13 
	private String kzzd14 ;//扩展字段14 
	private String kzzd15 ;//扩展字段15 
	private String kzzd16 ;//扩展字段16 
	private String kzzd17 ;//扩展字段17 
	private String kzzd18 ;//扩展字段18 
	private String kzzd19 ;//扩展字段19 
	private String kzzd20 ;//扩展字段20 
	
	private String xgzdJson;
	private String sqid;
	private String sqjg;
	private String xgsj;
	
	private String shr;
	private String ywid;
	private String sqrid;
	private String shlc;
	private String tzlj;
	private String tzljsq;
	
	private String jssf;//教师身份 
	
	private String sfdw;
	
	private String bzxm;
	
	private String bzlxdh;
	
	private String sydm; //书院代码  西交大个性化
	
	private String jtdzx;//华东交通家庭地址
	private String xwpx;
	private String rdsj;
	private String sjhm;
	private String qqhm;
	private String wxh;
	private String ssh;
	private String xqah;
	private String xl_qrz;
	private String xw_qrz;
	private String byyx_qrz;
	private String zymc_qrz;
	private String qsrq_qrz;
	private String zzrq_qrz;
	private String xl_zz;
	private String xw_zz;
	private String byyx_zz;
	private String zymc_zz;
	private String qsrq_zz;
	private String zzrq_zz;
	private String rxrq;
	private String zgqk;
	private String zhuanzhi;
	private String zzssbm;
	private String jzssbm;
	private String gznx;
	private String sfdb;
	private String xdbj;
	private String dbrs;
	private String zyjszw;
	private String jb_13431;
	private String tsgz;
	private String rdrq;//入党日期
	private String zydm;//专业
	private String zw_13431;//职务
	private String xdbqk;
	private String bjs;
	private String sfyjszgz;
	private String skqk;
	private String slfx1;
	private String slfx2;
	private String shjg;
	private String jslbdm;
	private String zgzt;
	private String lxbz;
	private String sfzg;
	private String sdzb;
	private String bzbbz;
	private String yjfx;
	private String gzzz;
	private String rxgzsj;
	private String rxszbm;
	private String rxszgw;
	private String sfdnsjzfdy;
	private String drsj;
	private String lxbm;
	private String xlwb;
	private String jjlxr;
	private String jjlxrdh;
	private String sfzb;
	private String sfdrsjzfdy;

	public String getSfzb() {
		return sfzb;
	}

	public void setSfzb(String sfzb) {
		this.sfzb = sfzb;
	}

	public String getSfdrsjzfdy() {
		return sfdrsjzfdy;
	}

	public void setSfdrsjzfdy(String sfdrsjzfdy) {
		this.sfdrsjzfdy = sfdrsjzfdy;
	}

	public String getZgzt() {
		return zgzt;
	}

	public void setZgzt(String zgzt) {
		this.zgzt = zgzt;
	}

	public String getLxbz() {
		return lxbz;
	}

	public void setLxbz(String lxbz) {
		this.lxbz = lxbz;
	}

	public String getSfzg() {
		return sfzg;
	}

	public void setSfzg(String sfzg) {
		this.sfzg = sfzg;
	}

	public String getSdzb() {
		return sdzb;
	}

	public void setSdzb(String sdzb) {
		this.sdzb = sdzb;
	}

	public String getBzbbz() {
		return bzbbz;
	}

	public void setBzbbz(String bzbbz) {
		this.bzbbz = bzbbz;
	}

	public String getYjfx() {
		return yjfx;
	}

	public void setYjfx(String yjfx) {
		this.yjfx = yjfx;
	}

	public String getGzzz() {
		return gzzz;
	}

	public void setGzzz(String gzzz) {
		this.gzzz = gzzz;
	}

	public String getRxgzsj() {
		return rxgzsj;
	}

	public void setRxgzsj(String rxgzsj) {
		this.rxgzsj = rxgzsj;
	}

	public String getRxszbm() {
		return rxszbm;
	}

	public void setRxszbm(String rxszbm) {
		this.rxszbm = rxszbm;
	}

	public String getRxszgw() {
		return rxszgw;
	}

	public void setRxszgw(String rxszgw) {
		this.rxszgw = rxszgw;
	}

	public String getSfdnsjzfdy() {
		return sfdnsjzfdy;
	}

	public void setSfdnsjzfdy(String sfdnsjzfdy) {
		this.sfdnsjzfdy = sfdnsjzfdy;
	}

	public String getDrsj() {
		return drsj;
	}

	public void setDrsj(String drsj) {
		this.drsj = drsj;
	}

	public String getLxbm() {
		return lxbm;
	}

	public void setLxbm(String lxbm) {
		this.lxbm = lxbm;
	}

	public String getXlwb() {
		return xlwb;
	}

	public void setXlwb(String xlwb) {
		this.xlwb = xlwb;
	}

	public String getJjlxr() {
		return jjlxr;
	}

	public void setJjlxr(String jjlxr) {
		this.jjlxr = jjlxr;
	}

	public String getJjlxrdh() {
		return jjlxrdh;
	}

	public void setJjlxrdh(String jjlxrdh) {
		this.jjlxrdh = jjlxrdh;
	}

	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @return the jslbdm
	 */
	public String getJslbdm() {
		return jslbdm;
	}
	/**
	 * @param jslbdm要设置的 jslbdm
	 */
	public void setJslbdm(String jslbdm) {
		this.jslbdm = jslbdm;
	}
	/**
	 * @param shjg要设置的 shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the xdbqk
	 */
	public String getXdbqk() {
		return xdbqk;
	}
	/**
	 * @param xdbqk要设置的 xdbqk
	 */
	public void setXdbqk(String xdbqk) {
		this.xdbqk = xdbqk;
	}
	/**
	 * @return the bjs
	 */
	public String getBjs() {
		return bjs;
	}
	/**
	 * @param bjs要设置的 bjs
	 */
	public void setBjs(String bjs) {
		this.bjs = bjs;
	}
	/**
	 * @return the sfyjszgz
	 */
	public String getSfyjszgz() {
		return sfyjszgz;
	}
	/**
	 * @param sfyjszgz要设置的 sfyjszgz
	 */
	public void setSfyjszgz(String sfyjszgz) {
		this.sfyjszgz = sfyjszgz;
	}
	/**
	 * @return the skqk
	 */
	public String getSkqk() {
		return skqk;
	}
	/**
	 * @param skqk要设置的 skqk
	 */
	public void setSkqk(String skqk) {
		this.skqk = skqk;
	}
	/**
	 * @return the slfx1
	 */
	public String getSlfx1() {
		return slfx1;
	}
	/**
	 * @param slfx1要设置的 slfx1
	 */
	public void setSlfx1(String slfx1) {
		this.slfx1 = slfx1;
	}
	/**
	 * @return the slfx2
	 */
	public String getSlfx2() {
		return slfx2;
	}
	/**
	 * @param slfx2要设置的 slfx2
	 */
	public void setSlfx2(String slfx2) {
		this.slfx2 = slfx2;
	}
	/**
	 * @return the xwpx
	 */
	public String getXwpx() {
		return xwpx;
	}
	/**
	 * @param xwpx要设置的 xwpx
	 */
	public void setXwpx(String xwpx) {
		this.xwpx = xwpx;
	}
	/**
	 * @return the rdsj
	 */
	public String getRdsj() {
		return rdsj;
	}
	/**
	 * @param rdsj要设置的 rdsj
	 */
	public void setRdsj(String rdsj) {
		this.rdsj = rdsj;
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
	 * @return the wxh
	 */
	public String getWxh() {
		return wxh;
	}
	/**
	 * @param wxh要设置的 wxh
	 */
	public void setWxh(String wxh) {
		this.wxh = wxh;
	}
	/**
	 * @return the ssh
	 */
	public String getSsh() {
		return ssh;
	}
	/**
	 * @param ssh要设置的 ssh
	 */
	public void setSsh(String ssh) {
		this.ssh = ssh;
	}
	/**
	 * @return the xqah
	 */
	public String getXqah() {
		return xqah;
	}
	/**
	 * @param xqah要设置的 xqah
	 */
	public void setXqah(String xqah) {
		this.xqah = xqah;
	}
	/**
	 * @return the xl_qrz
	 */
	public String getXl_qrz() {
		return xl_qrz;
	}
	/**
	 * @param xlQrz要设置的 xl_qrz
	 */
	public void setXl_qrz(String xlQrz) {
		xl_qrz = xlQrz;
	}
	/**
	 * @return the xw_qrz
	 */
	public String getXw_qrz() {
		return xw_qrz;
	}
	/**
	 * @param xwQrz要设置的 xw_qrz
	 */
	public void setXw_qrz(String xwQrz) {
		xw_qrz = xwQrz;
	}
	/**
	 * @return the byyx_qrz
	 */
	public String getByyx_qrz() {
		return byyx_qrz;
	}
	/**
	 * @param byyxQrz要设置的 byyx_qrz
	 */
	public void setByyx_qrz(String byyxQrz) {
		byyx_qrz = byyxQrz;
	}
	/**
	 * @return the zymc_qrz
	 */
	public String getZymc_qrz() {
		return zymc_qrz;
	}
	/**
	 * @param zymcQrz要设置的 zymc_qrz
	 */
	public void setZymc_qrz(String zymcQrz) {
		zymc_qrz = zymcQrz;
	}
	/**
	 * @return the qsrq_qrz
	 */
	public String getQsrq_qrz() {
		return qsrq_qrz;
	}
	/**
	 * @param qsrqQrz要设置的 qsrq_qrz
	 */
	public void setQsrq_qrz(String qsrqQrz) {
		qsrq_qrz = qsrqQrz;
	}
	/**
	 * @return the zzrq_qrz
	 */
	public String getZzrq_qrz() {
		return zzrq_qrz;
	}
	/**
	 * @param zzrqQrz要设置的 zzrq_qrz
	 */
	public void setZzrq_qrz(String zzrqQrz) {
		zzrq_qrz = zzrqQrz;
	}
	/**
	 * @return the xl_zz
	 */
	public String getXl_zz() {
		return xl_zz;
	}
	/**
	 * @param xlZz要设置的 xl_zz
	 */
	public void setXl_zz(String xlZz) {
		xl_zz = xlZz;
	}
	/**
	 * @return the xw_zz
	 */
	public String getXw_zz() {
		return xw_zz;
	}
	/**
	 * @param xwZz要设置的 xw_zz
	 */
	public void setXw_zz(String xwZz) {
		xw_zz = xwZz;
	}
	/**
	 * @return the byyx_zz
	 */
	public String getByyx_zz() {
		return byyx_zz;
	}
	/**
	 * @param byyxZz要设置的 byyx_zz
	 */
	public void setByyx_zz(String byyxZz) {
		byyx_zz = byyxZz;
	}
	/**
	 * @return the zymc_zz
	 */
	public String getZymc_zz() {
		return zymc_zz;
	}
	/**
	 * @param zymcZz要设置的 zymc_zz
	 */
	public void setZymc_zz(String zymcZz) {
		zymc_zz = zymcZz;
	}
	/**
	 * @return the qsrq_zz
	 */
	public String getQsrq_zz() {
		return qsrq_zz;
	}
	/**
	 * @param qsrqZz要设置的 qsrq_zz
	 */
	public void setQsrq_zz(String qsrqZz) {
		qsrq_zz = qsrqZz;
	}
	/**
	 * @return the zzrq_zz
	 */
	public String getZzrq_zz() {
		return zzrq_zz;
	}
	/**
	 * @param zzrqZz要设置的 zzrq_zz
	 */
	public void setZzrq_zz(String zzrqZz) {
		zzrq_zz = zzrqZz;
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
	 * @return the zgqk
	 */
	public String getZgqk() {
		return zgqk;
	}
	/**
	 * @param zgqk要设置的 zgqk
	 */
	public void setZgqk(String zgqk) {
		this.zgqk = zgqk;
	}
	/**
	 * @return the zhuanzhi
	 */
	public String getZhuanzhi() {
		return zhuanzhi;
	}
	/**
	 * @param zhuanzhi要设置的 zhuanzhi
	 */
	public void setZhuanzhi(String zhuanzhi) {
		this.zhuanzhi = zhuanzhi;
	}
	/**
	 * @return the zzssbm
	 */
	public String getZzssbm() {
		return zzssbm;
	}
	/**
	 * @param zzssbm要设置的 zzssbm
	 */
	public void setZzssbm(String zzssbm) {
		this.zzssbm = zzssbm;
	}
	/**
	 * @return the jzssbm
	 */
	public String getJzssbm() {
		return jzssbm;
	}
	/**
	 * @param jzssbm要设置的 jzssbm
	 */
	public void setJzssbm(String jzssbm) {
		this.jzssbm = jzssbm;
	}
	/**
	 * @return the gznx
	 */
	public String getGznx() {
		return gznx;
	}
	/**
	 * @param gznx要设置的 gznx
	 */
	public void setGznx(String gznx) {
		this.gznx = gznx;
	}
	/**
	 * @return the sfdb
	 */
	public String getSfdb() {
		return sfdb;
	}
	/**
	 * @param sfdb要设置的 sfdb
	 */
	public void setSfdb(String sfdb) {
		this.sfdb = sfdb;
	}
	/**
	 * @return the xdbj
	 */
	public String getXdbj() {
		return xdbj;
	}
	/**
	 * @param xdbj要设置的 xdbj
	 */
	public void setXdbj(String xdbj) {
		this.xdbj = xdbj;
	}
	/**
	 * @return the dbrs
	 */
	public String getDbrs() {
		return dbrs;
	}
	/**
	 * @param dbrs要设置的 dbrs
	 */
	public void setDbrs(String dbrs) {
		this.dbrs = dbrs;
	}
	/**
	 * @return the zyjszw
	 */
	public String getZyjszw() {
		return zyjszw;
	}
	/**
	 * @param zyjszw要设置的 zyjszw
	 */
	public void setZyjszw(String zyjszw) {
		this.zyjszw = zyjszw;
	}
	/**
	 * @return the jb_13431
	 */
	public String getJb_13431() {
		return jb_13431;
	}
	/**
	 * @param jb_13431要设置的 jb_13431
	 */
	public void setJb_13431(String jb_13431) {
		this.jb_13431 = jb_13431;
	}
	/**
	 * @return the tsgz
	 */
	public String getTsgz() {
		return tsgz;
	}
	/**
	 * @param tsgz要设置的 tsgz
	 */
	public void setTsgz(String tsgz) {
		this.tsgz = tsgz;
	}
	/**
	 * @return the zw_13431
	 */
	public String getZw_13431() {
		return zw_13431;
	}
	/**
	 * @param zw_13431要设置的 zw_13431
	 */
	public void setZw_13431(String zw_13431) {
		this.zw_13431 = zw_13431;
	}
	/**
	 * @return the rdrq
	 */
	public String getRdrq() {
		return rdrq;
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
	 * @param rdrq要设置的 rdrq
	 */
	public void setRdrq(String rdrq) {
		this.rdrq = rdrq;
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
	 * @return the jssf
	 */
	public String getJssf() {
		return jssf;
	}
	/**
	 * @param jssf要设置的 jssf
	 */
	public void setJssf(String jssf) {
		this.jssf = jssf;
	}
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlc要设置的 shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the tzlj
	 */
	public String getTzlj() {
		return tzlj;
	}
	/**
	 * @param tzlj要设置的 tzlj
	 */
	public void setTzlj(String tzlj) {
		this.tzlj = tzlj;
	}
	/**
	 * @return the tzljsq
	 */
	public String getTzljsq() {
		return tzljsq;
	}
	/**
	 * @param tzljsq要设置的 tzljsq
	 */
	public void setTzljsq(String tzljsq) {
		this.tzljsq = tzljsq;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shr要设置的 shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywid要设置的 ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @return the sqrid
	 */
	public String getSqrid() {
		return sqrid;
	}
	/**
	 * @param sqrid要设置的 sqrid
	 */
	public void setSqrid(String sqrid) {
		this.sqrid = sqrid;
	}
	/**
	 * @return the xgsj
	 */
	public String getXgsj() {
		return xgsj;
	}
	/**
	 * @param xgsj要设置的 xgsj
	 */
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the sqjg
	 */
	public String getSqjg() {
		return sqjg;
	}
	/**
	 * @param sqjg要设置的 sqjg
	 */
	public void setSqjg(String sqjg) {
		this.sqjg = sqjg;
	}
	/**
	 * @return the xgzdJson
	 */
	public String getXgzdJson() {
		return xgzdJson;
	}
	/**
	 * @param xgzdJson要设置的 xgzdJson
	 */
	public void setXgzdJson(String xgzdJson) {
		this.xgzdJson = xgzdJson;
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
	 * @return the gwlbdm
	 */
	public String getGwlbdm() {
		return gwlbdm;
	}
	/**
	 * @param gwlbdm要设置的 gwlbdm
	 */
	public void setGwlbdm(String gwlbdm) {
		this.gwlbdm = gwlbdm;
	}
	/**
	 * @return the khyh
	 */
	public String getKhyh() {
		return khyh;
	}
	/**
	 * @param khyh要设置的 khyh
	 */
	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}
	/**
	 * @return the hyzk
	 */
	public String getHyzk() {
		return hyzk;
	}
	/**
	 * @param hyzk要设置的 hyzk
	 */
	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}
	/**
	 * @return the fdyzbs
	 */
	public String getFdyzbs() {
		return fdyzbs;
	}
	/**
	 * @param fdyzbs要设置的 fdyzbs
	 */
	public void setFdyzbs(String fdyzbs) {
		this.fdyzbs = fdyzbs;
	}
	/**
	 * @return the fdyrzrq
	 */
	public String getFdyrzrq() {
		return fdyrzrq;
	}
	/**
	 * @param fdyrzrq要设置的 fdyrzrq
	 */
	public void setFdyrzrq(String fdyrzrq) {
		this.fdyrzrq = fdyrzrq;
	}
	/**
	 * @return the zyjnzs
	 */
	public String getZyjnzs() {
		return zyjnzs;
	}
	/**
	 * @param zyjnzs要设置的 zyjnzs
	 */
	public void setZyjnzs(String zyjnzs) {
		this.zyjnzs = zyjnzs;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age要设置的 age
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the yhzh
	 */
	public String getYhzh() {
		return yhzh;
	}
	/**
	 * @param yhzh要设置的 yhzh
	 */
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	/**
	 * @return the zzshen
	 */
	public String getZzshen() {
		return zzshen;
	}
	/**
	 * @param zzshen要设置的 zzshen
	 */
	public void setZzshen(String zzshen) {
		this.zzshen = zzshen;
	}
	/**
	 * @return the zzshi
	 */
	public String getZzshi() {
		return zzshi;
	}
	/**
	 * @param zzshi要设置的 zzshi
	 */
	public void setZzshi(String zzshi) {
		this.zzshi = zzshi;
	}
	/**
	 * @return the zzxian
	 */
	public String getZzxian() {
		return zzxian;
	}
	/**
	 * @param zzxian要设置的 zzxian
	 */
	public void setZzxian(String zzxian) {
		this.zzxian = zzxian;
	}
	/**
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
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
	 * @return the bmdm
	 */
	public String getBmdm() {
		return bmdm;
	}
	/**
	 * @param bmdm要设置的 bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
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
	 * @return the zyzz
	 */
	public String getZyzz() {
		return zyzz;
	}
	/**
	 * @param zyzz要设置的 zyzz
	 */
	public void setZyzz(String zyzz) {
		this.zyzz = zyzz;
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
	 * @return the xl
	 */
	public String getXl() {
		return xl;
	}
	/**
	 * @param xl要设置的 xl
	 */
	public void setXl(String xl) {
		this.xl = xl;
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
	 * @return the grhjqk
	 */
	public String getGrhjqk() {
		return grhjqk;
	}
	/**
	 * @param grhjqk要设置的 grhjqk
	 */
	public void setGrhjqk(String grhjqk) {
		this.grhjqk = grhjqk;
	}
	/**
	 * @return the gzjl
	 */
	public String getGzjl() {
		return gzjl;
	}
	/**
	 * @param gzjl要设置的 gzjl
	 */
	public void setGzjl(String gzjl) {
		this.gzjl = gzjl;
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
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}
	/**
	 * @param zc要设置的 zc
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}
	/**
	 * @return the lxgzsj
	 */
	public String getLxgzsj() {
		return lxgzsj;
	}
	/**
	 * @param lxgzsj要设置的 lxgzsj
	 */
	public void setLxgzsj(String lxgzsj) {
		this.lxgzsj = lxgzsj;
	}
	/**
	 * @return the xsgzyjfx
	 */
	public String getXsgzyjfx() {
		return xsgzyjfx;
	}
	/**
	 * @param xsgzyjfx要设置的 xsgzyjfx
	 */
	public void setXsgzyjfx(String xsgzyjfx) {
		this.xsgzyjfx = xsgzyjfx;
	}
	/**
	 * @return the jtzz
	 */
	public String getJtzz() {
		return jtzz;
	}
	/**
	 * @param jtzz要设置的 jtzz
	 */
	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	/**
	 * @return the yddh
	 */
	public String getYddh() {
		return yddh;
	}
	/**
	 * @param yddh要设置的 yddh
	 */
	public void setYddh(String yddh) {
		this.yddh = yddh;
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
	 * @return the fblw
	 */
	public String getFblw() {
		return fblw;
	}
	/**
	 * @param fblw要设置的 fblw
	 */
	public void setFblw(String fblw) {
		this.fblw = fblw;
	}
	/**
	 * @return the kyjl
	 */
	public String getKyjl() {
		return kyjl;
	}
	/**
	 * @param kyjl要设置的 kyjl
	 */
	public void setKyjl(String kyjl) {
		this.kyjl = kyjl;
	}
	/**
	 * @return the byyx
	 */
	public String getByyx() {
		return byyx;
	}
	/**
	 * @param byyx要设置的 byyx
	 */
	public void setByyx(String byyx) {
		this.byyx = byyx;
	}
	/**
	 * @return the sxzy
	 */
	public String getSxzy() {
		return sxzy;
	}
	/**
	 * @param sxzy要设置的 sxzy
	 */
	public void setSxzy(String sxzy) {
		this.sxzy = sxzy;
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
	 * @return the rwsj
	 */
	public String getRwsj() {
		return rwsj;
	}
	/**
	 * @param rwsj要设置的 rwsj
	 */
	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}
	/**
	 * @return the gzfg
	 */
	public String getGzfg() {
		return gzfg;
	}
	/**
	 * @param gzfg要设置的 gzfg
	 */
	public void setGzfg(String gzfg) {
		this.gzfg = gzfg;
	}
	/**
	 * @return the csfdysj
	 */
	public String getCsfdysj() {
		return csfdysj;
	}
	/**
	 * @param csfdysj要设置的 csfdysj
	 */
	public void setCsfdysj(String csfdysj) {
		this.csfdysj = csfdysj;
	}
	/**
	 * @return the zjz
	 */
	public String getZjz() {
		return zjz;
	}
	/**
	 * @param zjz要设置的 zjz
	 */
	public void setZjz(String zjz) {
		this.zjz = zjz;
	}
	/**
	 * @return the zwrzsj
	 */
	public String getZwrzsj() {
		return zwrzsj;
	}
	/**
	 * @param zwrzsj要设置的 zwrzsj
	 */
	public void setZwrzsj(String zwrzsj) {
		this.zwrzsj = zwrzsj;
	}
	/**
	 * @return the jsrzsj
	 */
	public String getJsrzsj() {
		return jsrzsj;
	}
	/**
	 * @param jsrzsj要设置的 jsrzsj
	 */
	public void setJsrzsj(String jsrzsj) {
		this.jsrzsj = jsrzsj;
	}
	/**
	 * @return the sjdw
	 */
	public String getSjdw() {
		return sjdw;
	}
	/**
	 * @param sjdw要设置的 sjdw
	 */
	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}
	/**
	 * @return the txdz
	 */
	public String getTxdz() {
		return txdz;
	}
	/**
	 * @param txdz要设置的 txdz
	 */
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	/**
	 * @return the bgdh
	 */
	public String getBgdh() {
		return bgdh;
	}
	/**
	 * @param bgdh要设置的 bgdh
	 */
	public void setBgdh(String bgdh) {
		this.bgdh = bgdh;
	}
	/**
	 * @return the cz
	 */
	public String getCz() {
		return cz;
	}
	/**
	 * @param cz要设置的 cz
	 */
	public void setCz(String cz) {
		this.cz = cz;
	}
	/**
	 * @return the jrgz
	 */
	public String getJrgz() {
		return jrgz;
	}
	/**
	 * @param jrgz要设置的 jrgz
	 */
	public void setJrgz(String jrgz) {
		this.jrgz = jrgz;
	}
	/**
	 * @return the jgxs
	 */
	public String getJgxs() {
		return jgxs;
	}
	/**
	 * @param jgxs要设置的 jgxs
	 */
	public void setJgxs(String jgxs) {
		this.jgxs = jgxs;
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
	 * @return the jb
	 */
	public String getJb() {
		return jb;
	}
	/**
	 * @param jb要设置的 jb
	 */
	public void setJb(String jb) {
		this.jb = jb;
	}
	/**
	 * @return the djsj
	 */
	public String getDjsj() {
		return djsj;
	}
	/**
	 * @param djsj要设置的 djsj
	 */
	public void setDjsj(String djsj) {
		this.djsj = djsj;
	}
	/**
	 * @return the szgzsj
	 */
	public String getSzgzsj() {
		return szgzsj;
	}
	/**
	 * @param szgzsj要设置的 szgzsj
	 */
	public void setSzgzsj(String szgzsj) {
		this.szgzsj = szgzsj;
	}
	/**
	 * @return the bkbyyx
	 */
	public String getBkbyyx() {
		return bkbyyx;
	}
	/**
	 * @param bkbyyx要设置的 bkbyyx
	 */
	public void setBkbyyx(String bkbyyx) {
		this.bkbyyx = bkbyyx;
	}
	/**
	 * @return the bksxzy
	 */
	public String getBksxzy() {
		return bksxzy;
	}
	/**
	 * @param bksxzy要设置的 bksxzy
	 */
	public void setBksxzy(String bksxzy) {
		this.bksxzy = bksxzy;
	}
	/**
	 * @return the ssbyyx
	 */
	public String getSsbyyx() {
		return ssbyyx;
	}
	/**
	 * @param ssbyyx要设置的 ssbyyx
	 */
	public void setSsbyyx(String ssbyyx) {
		this.ssbyyx = ssbyyx;
	}
	/**
	 * @return the ssbyzy
	 */
	public String getSsbyzy() {
		return ssbyzy;
	}
	/**
	 * @param ssbyzy要设置的 ssbyzy
	 */
	public void setSsbyzy(String ssbyzy) {
		this.ssbyzy = ssbyzy;
	}
	/**
	 * @return the bsbyyx
	 */
	public String getBsbyyx() {
		return bsbyyx;
	}
	/**
	 * @param bsbyyx要设置的 bsbyyx
	 */
	public void setBsbyyx(String bsbyyx) {
		this.bsbyyx = bsbyyx;
	}
	/**
	 * @return the bsbyzy
	 */
	public String getBsbyzy() {
		return bsbyzy;
	}
	/**
	 * @param bsbyzy要设置的 bsbyzy
	 */
	public void setBsbyzy(String bsbyzy) {
		this.bsbyzy = bsbyzy;
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
	 * @return the cjgzrq
	 */
	public String getCjgzrq() {
		return cjgzrq;
	}
	/**
	 * @param cjgzrq要设置的 cjgzrq
	 */
	public void setCjgzrq(String cjgzrq) {
		this.cjgzrq = cjgzrq;
	}
	/**
	 * @return the sfbl
	 */
	public String getSfbl() {
		return sfbl;
	}
	/**
	 * @param sfbl要设置的 sfbl
	 */
	public void setSfbl(String sfbl) {
		this.sfbl = sfbl;
	}
	/**
	 * @return the zhxwssxk
	 */
	public String getZhxwssxk() {
		return zhxwssxk;
	}
	/**
	 * @param zhxwssxk要设置的 zhxwssxk
	 */
	public void setZhxwssxk(String zhxwssxk) {
		this.zhxwssxk = zhxwssxk;
	}
	/**
	 * @return the pxqk
	 */
	public String getPxqk() {
		return pxqk;
	}
	/**
	 * @param pxqk要设置的 pxqk
	 */
	public void setPxqk(String pxqk) {
		this.pxqk = pxqk;
	}
	/**
	 * @return the csgz
	 */
	public String getCsgz() {
		return csgz;
	}
	/**
	 * @param csgz要设置的 csgz
	 */
	public void setCsgz(String csgz) {
		this.csgz = csgz;
	}
	/**
	 * @return the bgdd
	 */
	public String getBgdd() {
		return bgdd;
	}
	/**
	 * @param bgdd要设置的 bgdd
	 */
	public void setBgdd(String bgdd) {
		this.bgdd = bgdd;
	}
	/**
	 * @return the zdxl
	 */
	public String getZdxl() {
		return zdxl;
	}
	/**
	 * @param zdxl要设置的 zdxl
	 */
	public void setZdxl(String zdxl) {
		this.zdxl = zdxl;
	}
	/**
	 * @return the zdzy
	 */
	public String getZdzy() {
		return zdzy;
	}
	/**
	 * @param zdzy要设置的 zdzy
	 */
	public void setZdzy(String zdzy) {
		this.zdzy = zdzy;
	}
	/**
	 * @return the gdxlzl
	 */
	public String getGdxlzl() {
		return gdxlzl;
	}
	/**
	 * @param gdxlzl要设置的 gdxlzl
	 */
	public void setGdxlzl(String gdxlzl) {
		this.gdxlzl = gdxlzl;
	}
	/**
	 * @return the zyyjjg
	 */
	public String getZyyjjg() {
		return zyyjjg;
	}
	/**
	 * @param zyyjjg要设置的 zyyjjg
	 */
	public void setZyyjjg(String zyyjjg) {
		this.zyyjjg = zyyjjg;
	}
	/**
	 * @return the fgnj
	 */
	public String getFgnj() {
		return fgnj;
	}
	/**
	 * @param fgnj要设置的 fgnj
	 */
	public void setFgnj(String fgnj) {
		this.fgnj = fgnj;
	}
	/**
	 * @return the qtfgnj
	 */
	public String getQtfgnj() {
		return qtfgnj;
	}
	/**
	 * @param qtfgnj要设置的 qtfgnj
	 */
	public void setQtfgnj(String qtfgnj) {
		this.qtfgnj = qtfgnj;
	}
	/**
	 * @return the fdyz
	 */
	public String getFdyz() {
		return fdyz;
	}
	/**
	 * @param fdyz要设置的 fdyz
	 */
	public void setFdyz(String fdyz) {
		this.fdyz = fdyz;
	}
	/**
	 * @return the fglxdm
	 */
	public String getFglxdm() {
		return fglxdm;
	}
	/**
	 * @param fglxdm要设置的 fglxdm
	 */
	public void setFglxdm(String fglxdm) {
		this.fglxdm = fglxdm;
	}
	/**
	 * @return the dwlbdm
	 */
	public String getDwlbdm() {
		return dwlbdm;
	}
	/**
	 * @param dwlbdm要设置的 dwlbdm
	 */
	public void setDwlbdm(String dwlbdm) {
		this.dwlbdm = dwlbdm;
	}
	/**
	 * @return the kzzd1
	 */
	public String getKzzd1() {
		return kzzd1;
	}
	/**
	 * @param kzzd1要设置的 kzzd1
	 */
	public void setKzzd1(String kzzd1) {
		this.kzzd1 = kzzd1;
	}
	/**
	 * @return the kzzd2
	 */
	public String getKzzd2() {
		return kzzd2;
	}
	/**
	 * @param kzzd2要设置的 kzzd2
	 */
	public void setKzzd2(String kzzd2) {
		this.kzzd2 = kzzd2;
	}
	/**
	 * @return the kzzd3
	 */
	public String getKzzd3() {
		return kzzd3;
	}
	/**
	 * @param kzzd3要设置的 kzzd3
	 */
	public void setKzzd3(String kzzd3) {
		this.kzzd3 = kzzd3;
	}
	/**
	 * @return the kzzd4
	 */
	public String getKzzd4() {
		return kzzd4;
	}
	/**
	 * @param kzzd4要设置的 kzzd4
	 */
	public void setKzzd4(String kzzd4) {
		this.kzzd4 = kzzd4;
	}
	/**
	 * @return the kzzd5
	 */
	public String getKzzd5() {
		return kzzd5;
	}
	/**
	 * @param kzzd5要设置的 kzzd5
	 */
	public void setKzzd5(String kzzd5) {
		this.kzzd5 = kzzd5;
	}
	/**
	 * @return the sfjryx
	 */
	public String getSfjryx() {
		return sfjryx;
	}
	/**
	 * @param sfjryx要设置的 sfjryx
	 */
	public void setSfjryx(String sfjryx) {
		this.sfjryx = sfjryx;
	}
	/**
	 * @return the kzzd6
	 */
	public String getKzzd6() {
		return kzzd6;
	}
	/**
	 * @param kzzd6要设置的 kzzd6
	 */
	public void setKzzd6(String kzzd6) {
		this.kzzd6 = kzzd6;
	}
	/**
	 * @return the kzzd7
	 */
	public String getKzzd7() {
		return kzzd7;
	}
	/**
	 * @param kzzd7要设置的 kzzd7
	 */
	public void setKzzd7(String kzzd7) {
		this.kzzd7 = kzzd7;
	}
	/**
	 * @return the kzzd8
	 */
	public String getKzzd8() {
		return kzzd8;
	}
	/**
	 * @param kzzd8要设置的 kzzd8
	 */
	public void setKzzd8(String kzzd8) {
		this.kzzd8 = kzzd8;
	}
	/**
	 * @return the kzzd9
	 */
	public String getKzzd9() {
		return kzzd9;
	}
	/**
	 * @param kzzd9要设置的 kzzd9
	 */
	public void setKzzd9(String kzzd9) {
		this.kzzd9 = kzzd9;
	}
	/**
	 * @return the kzzd10
	 */
	public String getKzzd10() {
		return kzzd10;
	}
	/**
	 * @param kzzd10要设置的 kzzd10
	 */
	public void setKzzd10(String kzzd10) {
		this.kzzd10 = kzzd10;
	}
	/**
	 * @return the kzzd11
	 */
	public String getKzzd11() {
		return kzzd11;
	}
	/**
	 * @param kzzd11要设置的 kzzd11
	 */
	public void setKzzd11(String kzzd11) {
		this.kzzd11 = kzzd11;
	}
	/**
	 * @return the kzzd12
	 */
	public String getKzzd12() {
		return kzzd12;
	}
	/**
	 * @param kzzd12要设置的 kzzd12
	 */
	public void setKzzd12(String kzzd12) {
		this.kzzd12 = kzzd12;
	}
	/**
	 * @return the kzzd13
	 */
	public String getKzzd13() {
		return kzzd13;
	}
	/**
	 * @param kzzd13要设置的 kzzd13
	 */
	public void setKzzd13(String kzzd13) {
		this.kzzd13 = kzzd13;
	}
	/**
	 * @return the kzzd14
	 */
	public String getKzzd14() {
		return kzzd14;
	}
	/**
	 * @param kzzd14要设置的 kzzd14
	 */
	public void setKzzd14(String kzzd14) {
		this.kzzd14 = kzzd14;
	}
	/**
	 * @return the kzzd15
	 */
	public String getKzzd15() {
		return kzzd15;
	}
	/**
	 * @param kzzd15要设置的 kzzd15
	 */
	public void setKzzd15(String kzzd15) {
		this.kzzd15 = kzzd15;
	}
	/**
	 * @return the kzzd16
	 */
	public String getKzzd16() {
		return kzzd16;
	}
	/**
	 * @param kzzd16要设置的 kzzd16
	 */
	public void setKzzd16(String kzzd16) {
		this.kzzd16 = kzzd16;
	}
	/**
	 * @return the kzzd17
	 */
	public String getKzzd17() {
		return kzzd17;
	}
	/**
	 * @param kzzd17要设置的 kzzd17
	 */
	public void setKzzd17(String kzzd17) {
		this.kzzd17 = kzzd17;
	}
	/**
	 * @return the kzzd18
	 */
	public String getKzzd18() {
		return kzzd18;
	}
	/**
	 * @param kzzd18要设置的 kzzd18
	 */
	public void setKzzd18(String kzzd18) {
		this.kzzd18 = kzzd18;
	}
	/**
	 * @return the kzzd19
	 */
	public String getKzzd19() {
		return kzzd19;
	}
	/**
	 * @param kzzd19要设置的 kzzd19
	 */
	public void setKzzd19(String kzzd19) {
		this.kzzd19 = kzzd19;
	}
	/**
	 * @return the kzzd20
	 */
	public String getKzzd20() {
		return kzzd20;
	}
	/**
	 * @param kzzd20要设置的 kzzd20
	 */
	public void setKzzd20(String kzzd20) {
		this.kzzd20 = kzzd20;
	}
	/**
	 * @return the sfdw
	 */
	public String getSfdw() {
		return sfdw;
	}
	/**
	 * @param sfdw要设置的 sfdw
	 */
	public void setSfdw(String sfdw) {
		this.sfdw = sfdw;
	}
	/**
	 * @return the bzxm
	 */
	public String getBzxm() {
		return bzxm;
	}
	/**
	 * @param bzxm要设置的 bzxm
	 */
	public void setBzxm(String bzxm) {
		this.bzxm = bzxm;
	}
	/**
	 * @return the bzlxdh
	 */
	public String getBzlxdh() {
		return bzlxdh;
	}
	/**
	 * @param bzlxdh要设置的 bzlxdh
	 */
	public void setBzlxdh(String bzlxdh) {
		this.bzlxdh = bzlxdh;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-26 上午10:27:23 
	 * @return		: the sydm
	 */
	public String getSydm() {
		return sydm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-26 上午10:27:23 
	 * @param 		：sydm the sydm to set
	 */
	public void setSydm(String sydm) {
		this.sydm = sydm;
	}
	/**
	 * @return the zyjnzs2
	 */
	public String getZyjnzs2() {
		return zyjnzs2;
	}
	/**
	 * @param zyjnzs2要设置的 zyjnzs2
	 */
	public void setZyjnzs2(String zyjnzs2) {
		this.zyjnzs2 = zyjnzs2;
	}
	/**
	 * @return the zyjnzs3
	 */
	public String getZyjnzs3() {
		return zyjnzs3;
	}
	/**
	 * @param zyjnzs3要设置的 zyjnzs3
	 */
	public void setZyjnzs3(String zyjnzs3) {
		this.zyjnzs3 = zyjnzs3;
	}
	

	
}
