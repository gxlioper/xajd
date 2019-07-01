/**
 * @部门:学工产品事业部
 * @日期：2013-12-18 上午08:50:25 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsh;
import com.zfsoft.xgxt.rcsw.xszbb.comm.XszbbForm;



/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办管理模块
 * @类功能描述: TODO(学生证补办-补办审核) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-18 上午08:50:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XszbbshForm extends XszbbForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1266861971209094482L;
	private String bbsqid;
	private String xh;
	private String sqsj;
	private String xszbblxdm;
	private String sfbbhcyhk;
	private String sqly;
	private String shzt;
	private String splc;
	private String type;
	private String xszbblxmc;
	private String shlx;
	
	private String ywid;
	private String shsj;
	private String shr;
	private String shyj;
	private String shlc;
	private String gwid;
	private String shztmc;
	private String shid;
	private String thgw;//岗位退回
	private String shjg;
	private String filepath;
	private String sj;//火車優惠卡补办时间
	private String dd;//火车优惠卡补办地点
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	
	private String ccqdz;
	private String cczdz;
	
	/**
	 * @return the bbsqid
	 */
	public String getBbsqid() {
		return bbsqid;
	}
	/**
	 * @param bbsqid要设置的 bbsqid
	 */
	public void setBbsqid(String bbsqid) {
		this.bbsqid = bbsqid;
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
	 * @return the xszbblxdm
	 */
	public String getXszbblxdm() {
		return xszbblxdm;
	}
	/**
	 * @param xszbblxdm要设置的 xszbblxdm
	 */
	public void setXszbblxdm(String xszbblxdm) {
		this.xszbblxdm = xszbblxdm;
	}
	/**
	 * @return the sfbbhcyhk
	 */
	public String getSfbbhcyhk() {
		return sfbbhcyhk;
	}
	/**
	 * @param sfbbhcyhk要设置的 sfbbhcyhk
	 */
	public void setSfbbhcyhk(String sfbbhcyhk) {
		this.sfbbhcyhk = sfbbhcyhk;
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
	/**
	 * @return the xszbblxmc
	 */
	public String getXszbblxmc() {
		return xszbblxmc;
	}
	/**
	 * @param xszbblxmc要设置的 xszbblxmc
	 */
	public void setXszbblxmc(String xszbblxmc) {
		this.xszbblxmc = xszbblxmc;
	}
	/**
	 * @return the shlx
	 */
	public String getShlx() {
		return shlx;
	}
	/**
	 * @param shlx要设置的 shlx
	 */
	public void setShlx(String shlx) {
		this.shlx = shlx;
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
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @param shsj要设置的 shsj
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
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
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String[] getGwids() {
		return gwids;
	}
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @return the sj
	 */
	public String getSj() {
		return sj;
	}
	/**
	 * @param sj要设置的 sj
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}
	/**
	 * @return the dd
	 */
	public String getDd() {
		return dd;
	}
	/**
	 * @param dd要设置的 dd
	 */
	public void setDd(String dd) {
		this.dd = dd;
	}
	/**
	 * @return the ccqdz
	 */
	public String getCcqdz() {
		return ccqdz;
	}
	/**
	 * @param ccqdz要设置的 ccqdz
	 */
	public void setCcqdz(String ccqdz) {
		this.ccqdz = ccqdz;
	}
	/**
	 * @return the cczdz
	 */
	public String getCczdz() {
		return cczdz;
	}
	/**
	 * @param cczdz要设置的 cczdz
	 */
	public void setCczdz(String cczdz) {
		this.cczdz = cczdz;
	}
	
}
