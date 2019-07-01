package com.zfsoft.xgxt.rcsw.txhd.xmxxsq;

import com.zfsoft.xgxt.rcsw.txhd.comm.TxhdForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 项目申请
 */
public class XmxxsqForm extends TxhdForm {

	private static final long serialVersionUID = 1L;
	
	private String sqid ;//主键
	private String xmdm ;	//项目代码
	private String xmmc ;	//项目名称
	private String hdkssj ;	//活动开始时间
	private String hdjssj ;	//活动结束时间
	private String lbdm ;	//类别代码
	private String sqrssx ;	//申请人数上限
	private String shrssx ;	//审核人数上限
	private String shlc ;	//审核流程
	private String rskzjb ;	//人数控制级别
	private String hddd ;	//活动地点
	private String hdsm ;	//活动说明
	private String sqkg ;	//申请开关
	private String sqkssj ;	//申请开始时间
	private String sqjssj ;	//申请结束时间
	private String shkg ;	//审核开关
	private String shkssj ;	//审核开始时间
	private String shjssj ;	//审核结束时间
	private String fbsj ;	//发布时间
	private String xn;		//学年
	private String xq;		//学期
	private String xqmc;
	private String kgbz;	//开关备注
	private String syxf;	//授予学分
	
	private String cbdw;//承办单位
	private String fzrxm;//负责人姓名
	private String lxdh;//联系电话
	private String hdzt;//活动主题
	private String hdmdyy;//活动目的及意义
	private String hdfa;//活动方案
	
	private String sqr ;
	private String sqsj ;//申请时间
	private String shzt;
	private String shztmc;
	private String splc;
	private String type;
	private String hdggdm;//活动规格代码
	
	/**
	 * @return the hdggdm
	 */
	public String getHdggdm() {
		return hdggdm;
	}
	/**
	 * @param hdggdm要设置的 hdggdm
	 */
	public void setHdggdm(String hdggdm) {
		this.hdggdm = hdggdm;
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
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmc要设置的 xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the hdkssj
	 */
	public String getHdkssj() {
		return hdkssj;
	}
	/**
	 * @param hdkssj要设置的 hdkssj
	 */
	public void setHdkssj(String hdkssj) {
		this.hdkssj = hdkssj;
	}
	/**
	 * @return the hdjssj
	 */
	public String getHdjssj() {
		return hdjssj;
	}
	/**
	 * @param hdjssj要设置的 hdjssj
	 */
	public void setHdjssj(String hdjssj) {
		this.hdjssj = hdjssj;
	}
	/**
	 * @return the lbdm
	 */
	public String getLbdm() {
		return lbdm;
	}
	/**
	 * @param lbdm要设置的 lbdm
	 */
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	/**
	 * @return the sqrssx
	 */
	public String getSqrssx() {
		return sqrssx;
	}
	/**
	 * @param sqrssx要设置的 sqrssx
	 */
	public void setSqrssx(String sqrssx) {
		this.sqrssx = sqrssx;
	}
	/**
	 * @return the shrssx
	 */
	public String getShrssx() {
		return shrssx;
	}
	/**
	 * @param shrssx要设置的 shrssx
	 */
	public void setShrssx(String shrssx) {
		this.shrssx = shrssx;
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
	 * @return the rskzjb
	 */
	public String getRskzjb() {
		return rskzjb;
	}
	/**
	 * @param rskzjb要设置的 rskzjb
	 */
	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
	}
	/**
	 * @return the hddd
	 */
	public String getHddd() {
		return hddd;
	}
	/**
	 * @param hddd要设置的 hddd
	 */
	public void setHddd(String hddd) {
		this.hddd = hddd;
	}
	/**
	 * @return the hdsm
	 */
	public String getHdsm() {
		return hdsm;
	}
	/**
	 * @param hdsm要设置的 hdsm
	 */
	public void setHdsm(String hdsm) {
		this.hdsm = hdsm;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkg要设置的 sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssj要设置的 sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssj要设置的 sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the shkg
	 */
	public String getShkg() {
		return shkg;
	}
	/**
	 * @param shkg要设置的 shkg
	 */
	public void setShkg(String shkg) {
		this.shkg = shkg;
	}
	/**
	 * @return the shkssj
	 */
	public String getShkssj() {
		return shkssj;
	}
	/**
	 * @param shkssj要设置的 shkssj
	 */
	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}
	/**
	 * @return the shjssj
	 */
	public String getShjssj() {
		return shjssj;
	}
	/**
	 * @param shjssj要设置的 shjssj
	 */
	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}
	/**
	 * @return the fbsj
	 */
	public String getFbsj() {
		return fbsj;
	}
	/**
	 * @param fbsj要设置的 fbsj
	 */
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the kgbz
	 */
	public String getKgbz() {
		return kgbz;
	}
	/**
	 * @param kgbz要设置的 kgbz
	 */
	public void setKgbz(String kgbz) {
		this.kgbz = kgbz;
	}
	/**
	 * @return the syxf
	 */
	public String getSyxf() {
		return syxf;
	}
	/**
	 * @param syxf要设置的 syxf
	 */
	public void setSyxf(String syxf) {
		this.syxf = syxf;
	}
	/**
	 * @return the cbdw
	 */
	public String getCbdw() {
		return cbdw;
	}
	/**
	 * @param cbdw要设置的 cbdw
	 */
	public void setCbdw(String cbdw) {
		this.cbdw = cbdw;
	}
	/**
	 * @return the fzrxm
	 */
	public String getFzrxm() {
		return fzrxm;
	}
	/**
	 * @param fzrxm要设置的 fzrxm
	 */
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
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
	 * @return the hdzt
	 */
	public String getHdzt() {
		return hdzt;
	}
	/**
	 * @param hdzt要设置的 hdzt
	 */
	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}
	/**
	 * @return the hdmdyy
	 */
	public String getHdmdyy() {
		return hdmdyy;
	}
	/**
	 * @param hdmdyy要设置的 hdmdyy
	 */
	public void setHdmdyy(String hdmdyy) {
		this.hdmdyy = hdmdyy;
	}
	/**
	 * @return the hdfa
	 */
	public String getHdfa() {
		return hdfa;
	}
	/**
	 * @param hdfa要设置的 hdfa
	 */
	public void setHdfa(String hdfa) {
		this.hdfa = hdfa;
	}
	/**
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * @param sqr要设置的 sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
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
	 * @return the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @param shztmc要设置的 shztmc
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
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
	
}
