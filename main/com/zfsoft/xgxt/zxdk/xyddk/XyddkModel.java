/**
 * @部门:学工产品事业部
 * @日期：2014-9-25 下午03:21:11 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 助学贷款 - 国家助学贷款
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-25 下午03:21:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyddkModel extends SuperAuditModel {

	private static final long serialVersionUID = -3569762156962449075L;

	private String id;
	private String xh;
	private String xn;
	private String xz;
	private String nj;
	private String dkje;
	private String xzf;//学费
	private String shf;
	private String mnje;
	private String dkqx;
	private String sqly;
	private String sqsj;
	private String shzt;
	private String splcid;
	
	private String htbh;
	private String sjly;
	private String sfwy;
	
	private String zsf;//住宿费
	private String zsfdks;//住宿费贷款数
	private String xfdks;//学费贷款数 
	private String shfdks;//生活费贷款数
	private String fkze;
	private String xdxn;
	private String xdje;
	private String xdly;
	
	private String yhmc; //银行名称
	private String lxdh; //联系电话
	
	//北京中医药大学 增加字段 
	private String dkkssj;//贷款开始时间
	private String dkjzsj;//贷款截止时间
	private String yjschksj;//预计首次还款时间
	private String dkkh;//贷款卡号
	private String jhr1;//法定监护人1
	private String sfzh1;//法定监护人1身份证号
	private String zy1;//法定监护人1职业
	private String gzdwmc1;//法定监护人1工作单位名称
	private String gzdwdz1;//法定监护人1工作单位地址
	private String lxdh1;//法定监护人1联系电话
	private String jhr2;//法定监护人2
	private String sfzh2;//法定监护人2身份证号
	private String zy2;//法定监护人2职业
	private String gzdwmc2;//法定监护人2工作单位名称
	private String gzdwdz2;//法定监护人2工作单位地址
	private String xldh2;//法定监护人2联系
	private String bldkrq;//办理贷款日期（西安科技大学个性化字段）
	private String htdd;//合同签订地点（西安科技大学个性化字段）
	private String xyjbr;//学院经办人
	private String zd7;
	private String zd8;
	private String zd9;
	private String zd10;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	//下载相关
	private FormFile formfile;
	private String filepath;
	
	//续贷修改新增字段
	private String splc;
	private String type;
    private String shid;
    private String shjg;
    private String shyj;
    private String gwid;
    private String thgw;
    private String shlc;
    private String[] splcs;
    private String[] ids;
    private String[] xhs;
    
    //苏州卫生个性化
    //  共同借款人信息
    private String gtjkryb;//邮政编码
    private String gtjkrjtdz;//家庭详细地址
    private String gtjkrjkzk;//健康状况
    private String gtjkrsjhm;//手机号码
    private String  gtjkrsfzh;//身份证号码
    private String gtjkrgx;//与学生关系
    private String gtjkrjtdh;//家庭电话
    private String gtjkrxm;//姓名
   
    
    /**
	 * @return the gtjkryb
	 */
	public String getGtjkryb() {
		return gtjkryb;
	}
	/**
	 * @param gtjkryb要设置的 gtjkryb
	 */
	public void setGtjkryb(String gtjkryb) {
		this.gtjkryb = gtjkryb;
	}
	/**
	 * @return the gtjkrjtdz
	 */
	public String getGtjkrjtdz() {
		return gtjkrjtdz;
	}
	/**
	 * @param gtjkrjtdz要设置的 gtjkrjtdz
	 */
	public void setGtjkrjtdz(String gtjkrjtdz) {
		this.gtjkrjtdz = gtjkrjtdz;
	}
	/**
	 * @return the gtjkrjkzk
	 */
	public String getGtjkrjkzk() {
		return gtjkrjkzk;
	}
	/**
	 * @param gtjkrjkzk要设置的 gtjkrjkzk
	 */
	public void setGtjkrjkzk(String gtjkrjkzk) {
		this.gtjkrjkzk = gtjkrjkzk;
	}
	/**
	 * @return the gtjkrsjhm
	 */
	public String getGtjkrsjhm() {
		return gtjkrsjhm;
	}
	/**
	 * @param gtjkrsjhm要设置的 gtjkrsjhm
	 */
	public void setGtjkrsjhm(String gtjkrsjhm) {
		this.gtjkrsjhm = gtjkrsjhm;
	}
	/**
	 * @return the gtjkrsfzh
	 */
	public String getGtjkrsfzh() {
		return gtjkrsfzh;
	}
	/**
	 * @param gtjkrsfzh要设置的 gtjkrsfzh
	 */
	public void setGtjkrsfzh(String gtjkrsfzh) {
		this.gtjkrsfzh = gtjkrsfzh;
	}
	/**
	 * @return the gtjkrgx
	 */
	public String getGtjkrgx() {
		return gtjkrgx;
	}
	/**
	 * @param gtjkrgx要设置的 gtjkrgx
	 */
	public void setGtjkrgx(String gtjkrgx) {
		this.gtjkrgx = gtjkrgx;
	}
	/**
	 * @return the gtjkrjtdh
	 */
	public String getGtjkrjtdh() {
		return gtjkrjtdh;
	}
	/**
	 * @param gtjkrjtdh要设置的 gtjkrjtdh
	 */
	public void setGtjkrjtdh(String gtjkrjtdh) {
		this.gtjkrjtdh = gtjkrjtdh;
	}
	/**
	 * @return the gtjkrxm
	 */
	public String getGtjkrxm() {
		return gtjkrxm;
	}
	/**
	 * @param gtjkrxm要设置的 gtjkrxm
	 */
	public void setGtjkrxm(String gtjkrxm) {
		this.gtjkrxm = gtjkrxm;
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
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shid要设置的 shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjg要设置的 shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyj要设置的 shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwid要设置的 gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgw要设置的 thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}
	/**
	 * @param splcs要设置的 splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	/**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * @param ids要设置的 ids
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwids要设置的 gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	private String[] gwids;
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
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the dkkssj
	 */
	public String getDkkssj() {
		return dkkssj;
	}
	/**
	 * @param dkkssj要设置的 dkkssj
	 */
	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}
	/**
	 * @return the dkjzsj
	 */
	public String getDkjzsj() {
		return dkjzsj;
	}
	/**
	 * @param dkjzsj要设置的 dkjzsj
	 */
	public void setDkjzsj(String dkjzsj) {
		this.dkjzsj = dkjzsj;
	}
	/**
	 * @return the yjschksj
	 */
	public String getYjschksj() {
		return yjschksj;
	}
	/**
	 * @param yjschksj要设置的 yjschksj
	 */
	public void setYjschksj(String yjschksj) {
		this.yjschksj = yjschksj;
	}
	/**
	 * @return the dkkh
	 */
	public String getDkkh() {
		return dkkh;
	}
	/**
	 * @param dkkh要设置的 dkkh
	 */
	public void setDkkh(String dkkh) {
		this.dkkh = dkkh;
	}
	/**
	 * @return the jhr1
	 */
	public String getJhr1() {
		return jhr1;
	}
	/**
	 * @param jhr1要设置的 jhr1
	 */
	public void setJhr1(String jhr1) {
		this.jhr1 = jhr1;
	}
	/**
	 * @return the sfzh1
	 */
	public String getSfzh1() {
		return sfzh1;
	}
	/**
	 * @param sfzh1要设置的 sfzh1
	 */
	public void setSfzh1(String sfzh1) {
		this.sfzh1 = sfzh1;
	}
	/**
	 * @return the zy1
	 */
	public String getZy1() {
		return zy1;
	}
	/**
	 * @param zy1要设置的 zy1
	 */
	public void setZy1(String zy1) {
		this.zy1 = zy1;
	}
	/**
	 * @return the gzdwmc1
	 */
	public String getGzdwmc1() {
		return gzdwmc1;
	}
	/**
	 * @param gzdwmc1要设置的 gzdwmc1
	 */
	public void setGzdwmc1(String gzdwmc1) {
		this.gzdwmc1 = gzdwmc1;
	}
	/**
	 * @return the gzdwdz1
	 */
	public String getGzdwdz1() {
		return gzdwdz1;
	}
	/**
	 * @param gzdwdz1要设置的 gzdwdz1
	 */
	public void setGzdwdz1(String gzdwdz1) {
		this.gzdwdz1 = gzdwdz1;
	}
	/**
	 * @return the lxdh1
	 */
	public String getLxdh1() {
		return lxdh1;
	}
	/**
	 * @param lxdh1要设置的 lxdh1
	 */
	public void setLxdh1(String lxdh1) {
		this.lxdh1 = lxdh1;
	}
	/**
	 * @return the jhr2
	 */
	public String getJhr2() {
		return jhr2;
	}
	/**
	 * @param jhr2要设置的 jhr2
	 */
	public void setJhr2(String jhr2) {
		this.jhr2 = jhr2;
	}
	/**
	 * @return the sfzh2
	 */
	public String getSfzh2() {
		return sfzh2;
	}
	/**
	 * @param sfzh2要设置的 sfzh2
	 */
	public void setSfzh2(String sfzh2) {
		this.sfzh2 = sfzh2;
	}
	/**
	 * @return the zy2
	 */
	public String getZy2() {
		return zy2;
	}
	/**
	 * @param zy2要设置的 zy2
	 */
	public void setZy2(String zy2) {
		this.zy2 = zy2;
	}
	/**
	 * @return the gzdwmc2
	 */
	public String getGzdwmc2() {
		return gzdwmc2;
	}
	/**
	 * @param gzdwmc2要设置的 gzdwmc2
	 */
	public void setGzdwmc2(String gzdwmc2) {
		this.gzdwmc2 = gzdwmc2;
	}
	/**
	 * @return the gzdwdz2
	 */
	public String getGzdwdz2() {
		return gzdwdz2;
	}
	/**
	 * @param gzdwdz2要设置的 gzdwdz2
	 */
	public void setGzdwdz2(String gzdwdz2) {
		this.gzdwdz2 = gzdwdz2;
	}
	/**
	 * @return the xldh2
	 */
	public String getXldh2() {
		return xldh2;
	}
	/**
	 * @param xldh2要设置的 xldh2
	 */
	public void setXldh2(String xldh2) {
		this.xldh2 = xldh2;
	}
	
	
	
	/**
	 * @return the xdxn
	 */
	public String getXdxn() {
		return xdxn;
	}
	/**
	 * @param xdxn要设置的 xdxn
	 */
	public void setXdxn(String xdxn) {
		this.xdxn = xdxn;
	}
	/**
	 * @return the xdje
	 */
	public String getXdje() {
		return xdje;
	}
	/**
	 * @param xdje要设置的 xdje
	 */
	public void setXdje(String xdje) {
		this.xdje = xdje;
	}
	/**
	 * @return the xdly
	 */
	public String getXdly() {
		return xdly;
	}
	/**
	 * @param xdly要设置的 xdly
	 */
	public void setXdly(String xdly) {
		this.xdly = xdly;
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
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the dkje
	 */
	public String getDkje() {
		return dkje;
	}
	/**
	 * @param dkje要设置的 dkje
	 */
	public void setDkje(String dkje) {
		this.dkje = dkje;
	}
	/**
	 * @return the xzf
	 */
	public String getXzf() {
		return xzf;
	}
	/**
	 * @param xzf要设置的 xzf
	 */
	public void setXzf(String xzf) {
		this.xzf = xzf;
	}
	/**
	 * @return the shf
	 */
	public String getShf() {
		return shf;
	}
	/**
	 * @param shf要设置的 shf
	 */
	public void setShf(String shf) {
		this.shf = shf;
	}
	/**
	 * @return the mnje
	 */
	public String getMnje() {
		return mnje;
	}
	/**
	 * @param mnje要设置的 mnje
	 */
	public void setMnje(String mnje) {
		this.mnje = mnje;
	}
	/**
	 * @return the dkqx
	 */
	public String getDkqx() {
		return dkqx;
	}
	/**
	 * @param dkqx要设置的 dkqx
	 */
	public void setDkqx(String dkqx) {
		this.dkqx = dkqx;
	}
	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
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
	 * @return the htbh
	 */
	public String getHtbh() {
		return htbh;
	}
	/**
	 * @param htbh要设置的 htbh
	 */
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @return the sfwy
	 */
	public String getSfwy() {
		return sfwy;
	}
	/**
	 * @param sfwy要设置的 sfwy
	 */
	public void setSfwy(String sfwy) {
		this.sfwy = sfwy;
	}
	/**
	 * @return the fkze
	 */
	public String getFkze() {
		return fkze;
	}
	/**
	 * @param fkze要设置的 fkze
	 */
	public void setFkze(String fkze) {
		this.fkze = fkze;
	}

	/**
	 * @return the zsf
	 */
	public String getZsf() {
		return zsf;
	}
	/**
	 * @param zsf要设置的 zsf
	 */
	public void setZsf(String zsf) {
		this.zsf = zsf;
	}
	/**
	 * @return the zsfdks
	 */
	public String getZsfdks() {
		return zsfdks;
	}
	/**
	 * @param zsfdks要设置的 zsfdks
	 */
	public void setZsfdks(String zsfdks) {
		this.zsfdks = zsfdks;
	}
	/**
	 * @return the xfdks
	 */
	public String getXfdks() {
		return xfdks;
	}
	/**
	 * @param xfdks要设置的 xfdks
	 */
	public void setXfdks(String xfdks) {
		this.xfdks = xfdks;
	}
	/**
	 * @return the shfdks
	 */
	public String getShfdks() {
		return shfdks;
	}
	/**
	 * @param shfdks要设置的 shfdks
	 */
	public void setShfdks(String shfdks) {
		this.shfdks = shfdks;
	}
	/**
	 * @return the yhmc
	 */
	public String getYhmc() {
		return yhmc;
	}
	/**
	 * @param yhmc要设置的 yhmc
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
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
	public FormFile getFormfile() {
		return formfile;
	}
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the bldkrq
	 */
	public String getBldkrq() {
		return bldkrq;
	}
	/**
	 * @param bldkrq要设置的 bldkrq
	 */
	public void setBldkrq(String bldkrq) {
		this.bldkrq = bldkrq;
	}
	/**
	 * @return the htdd
	 */
	public String getHtdd() {
		return htdd;
	}
	/**
	 * @param htdd要设置的 htdd
	 */
	public void setHtdd(String htdd) {
		this.htdd = htdd;
	}
	/**
	 * @return the xyjbr
	 */
	public String getXyjbr() {
		return xyjbr;
	}
	/**
	 * @param xyjbr要设置的 xyjbr
	 */
	public void setXyjbr(String xyjbr) {
		this.xyjbr = xyjbr;
	}
	/**
	 * @return the zd7
	 */
	public String getZd7() {
		return zd7;
	}
	/**
	 * @param zd7要设置的 zd7
	 */
	public void setZd7(String zd7) {
		this.zd7 = zd7;
	}
	/**
	 * @return the zd8
	 */
	public String getZd8() {
		return zd8;
	}
	/**
	 * @param zd8要设置的 zd8
	 */
	public void setZd8(String zd8) {
		this.zd8 = zd8;
	}
	/**
	 * @return the zd9
	 */
	public String getZd9() {
		return zd9;
	}
	/**
	 * @param zd9要设置的 zd9
	 */
	public void setZd9(String zd9) {
		this.zd9 = zd9;
	}
	/**
	 * @return the zd10
	 */
	public String getZd10() {
		return zd10;
	}
	/**
	 * @param zd10要设置的 zd10
	 */
	public void setZd10(String zd10) {
		this.zd10 = zd10;
	}
	
}
