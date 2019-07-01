/**
 * @部门:学工产品事业部
 * @日期：2013-12-18 上午08:50:25 
 */  
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjsh;
import com.zfsoft.xgxt.rcsw.hcyhkgl.comm.HcyhkForm;



/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 火车车程区间管理模块
 * @类功能描述: TODO(火车车程区间审核) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-26 上午09:37:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class HcccqjshForm extends HcyhkForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = -4156508545113063888L;
	private String ccqjtxid;
	private String xh;
	private String txsj;
	private String xn;
	private String xq;
	private String ccqdz;
	private String cczdz;
	private String shzt;
	private String bz;
	private String type;
	private String splc;
	private String hcccqjmc;
	private String hcyhklxmc;
	private String hcyhklx;
	private String cczdsf;
	private String ccqdsf;
	
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
	 * @return the ccqjtxid
	 */
	public String getCcqjtxid() {
		return ccqjtxid;
	}
	/**
	 * @param ccqjtxid要设置的 ccqjtxid
	 */
	public void setCcqjtxid(String ccqjtxid) {
		this.ccqjtxid = ccqjtxid;
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
	 * @return the txsj
	 */
	public String getTxsj() {
		return txsj;
	}
	/**
	 * @param txsj要设置的 txsj
	 */
	public void setTxsj(String txsj) {
		this.txsj = txsj;
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
	 * @return the hcccqjmc
	 */
	public String getHcccqjmc() {
		return hcccqjmc;
	}
	/**
	 * @param hcccqjmc要设置的 hcccqjmc
	 */
	public void setHcccqjmc(String hcccqjmc) {
		this.hcccqjmc = hcccqjmc;
	}
	/**
	 * @return the hcyhklxmc
	 */
	public String getHcyhklxmc() {
		return hcyhklxmc;
	}
	/**
	 * @param hcyhklxmc要设置的 hcyhklxmc
	 */
	public void setHcyhklxmc(String hcyhklxmc) {
		this.hcyhklxmc = hcyhklxmc;
	}
	/**
	 * @return the hcyhklx
	 */
	public String getHcyhklx() {
		return hcyhklx;
	}
	/**
	 * @param hcyhklx要设置的 hcyhklx
	 */
	public void setHcyhklx(String hcyhklx) {
		this.hcyhklx = hcyhklx;
	}
	/**
	 * @return the cczdsf
	 */
	public String getCczdsf() {
		return cczdsf;
	}
	/**
	 * @param cczdsf要设置的 cczdsf
	 */
	public void setCczdsf(String cczdsf) {
		this.cczdsf = cczdsf;
	}
	/**
	 * @return the ccqdsf
	 */
	public String getCcqdsf() {
		return ccqdsf;
	}
	/**
	 * @param ccqdsf要设置的 ccqdsf
	 */
	public void setCcqdsf(String ccqdsf) {
		this.ccqdsf = ccqdsf;
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
