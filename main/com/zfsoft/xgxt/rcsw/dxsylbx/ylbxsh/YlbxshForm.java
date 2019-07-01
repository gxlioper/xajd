/**
 * @部门:学工产品事业部
 * @日期：2013-12-18 上午08:50:25 
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsh;
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
public class YlbxshForm extends XszbbForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1266861971209094482L;
	private String ylsqid;
	private String xh;
	private String xn;
	private String xq;
	private String sqsj;
	private String czqebzdm;
	private String cbzkdm;
	private String zjsyrxm;
	private String zjh;
	private String sqly;
	private String shzt;
	private String splc;
	private String type;
	private String qtcbzkval;
	private String cbsj;
	private String xqmc;
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
	
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	
	
	/**
	 * @return the ylsqid
	 */
	public String getYlsqid() {
		return ylsqid;
	}
	/**
	 * @param ylsqid要设置的 ylsqid
	 */
	public void setYlsqid(String ylsqid) {
		this.ylsqid = ylsqid;
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
	 * @return the czqebzdm
	 */
	public String getCzqebzdm() {
		return czqebzdm;
	}
	/**
	 * @param czqebzdm要设置的 czqebzdm
	 */
	public void setCzqebzdm(String czqebzdm) {
		this.czqebzdm = czqebzdm;
	}
	/**
	 * @return the cbzkdm
	 */
	public String getCbzkdm() {
		return cbzkdm;
	}
	/**
	 * @param cbzkdm要设置的 cbzkdm
	 */
	public void setCbzkdm(String cbzkdm) {
		this.cbzkdm = cbzkdm;
	}
	/**
	 * @return the zjsyrxm
	 */
	public String getZjsyrxm() {
		return zjsyrxm;
	}
	/**
	 * @param zjsyrxm要设置的 zjsyrxm
	 */
	public void setZjsyrxm(String zjsyrxm) {
		this.zjsyrxm = zjsyrxm;
	}
	/**
	 * @return the zjh
	 */
	public String getZjh() {
		return zjh;
	}
	/**
	 * @param zjh要设置的 zjh
	 */
	public void setZjh(String zjh) {
		this.zjh = zjh;
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
	 * @return the qtcbzkval
	 */
	public String getQtcbzkval() {
		return qtcbzkval;
	}
	/**
	 * @param qtcbzkval要设置的 qtcbzkval
	 */
	public void setQtcbzkval(String qtcbzkval) {
		this.qtcbzkval = qtcbzkval;
	}
	/**
	 * @return the cbsj
	 */
	public String getCbsj() {
		return cbsj;
	}
	/**
	 * @param cbsj要设置的 cbsj
	 */
	public void setCbsj(String cbsj) {
		this.cbsj = cbsj;
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
	public String[] getSplcs() {
		return splcs;
	}
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	


}
