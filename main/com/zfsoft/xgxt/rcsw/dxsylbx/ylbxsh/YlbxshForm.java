/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-18 ����08:50:25 
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsh;
import com.zfsoft.xgxt.rcsw.xszbb.comm.XszbbForm;



/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤�������ģ��
 * @�๦������: TODO(ѧ��֤����-�������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-18 ����08:50:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxshForm extends XszbbForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
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
	private String thgw;//��λ�˻�
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
	 * @param ylsqidҪ���õ� ylsqid
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
	 * @param xhҪ���õ� xh
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
	 * @param xnҪ���õ� xn
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
	 * @param xqҪ���õ� xq
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
	 * @param sqsjҪ���õ� sqsj
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
	 * @param czqebzdmҪ���õ� czqebzdm
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
	 * @param cbzkdmҪ���õ� cbzkdm
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
	 * @param zjsyrxmҪ���õ� zjsyrxm
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
	 * @param zjhҪ���õ� zjh
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
	 * @param sqlyҪ���õ� sqly
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
	 * @param shztҪ���õ� shzt
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
	 * @param splcҪ���õ� splc
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
	 * @param typeҪ���õ� type
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
	 * @param qtcbzkvalҪ���õ� qtcbzkval
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
	 * @param cbsjҪ���õ� cbsj
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
	 * @param xqmcҪ���õ� xqmc
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
	 * @param shlxҪ���õ� shlx
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
	 * @param ywidҪ���õ� ywid
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
	 * @param shsjҪ���õ� shsj
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
	 * @param shrҪ���õ� shr
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
	 * @param shyjҪ���õ� shyj
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
	 * @param shlcҪ���õ� shlc
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
	 * @param gwidҪ���õ� gwid
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
	 * @param shztmcҪ���õ� shztmc
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
	 * @param shidҪ���õ� shid
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
	 * @param thgwҪ���õ� thgw
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
	 * @param shjgҪ���õ� shjg
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
