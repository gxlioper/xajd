/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-18 ����08:50:25 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsh;
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
public class XszbbshForm extends XszbbForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
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
	private String thgw;//��λ�˻�
	private String shjg;
	private String filepath;
	private String sj;//��܇���ݿ�����ʱ��
	private String dd;//���Żݿ�����ص�
	
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
	 * @param bbsqidҪ���õ� bbsqid
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
	 * @param xhҪ���õ� xh
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
	 * @param sqsjҪ���õ� sqsj
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
	 * @param xszbblxdmҪ���õ� xszbblxdm
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
	 * @param sfbbhcyhkҪ���õ� sfbbhcyhk
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
	 * @return the xszbblxmc
	 */
	public String getXszbblxmc() {
		return xszbblxmc;
	}
	/**
	 * @param xszbblxmcҪ���õ� xszbblxmc
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
	 * @param sjҪ���õ� sj
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
	 * @param ddҪ���õ� dd
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
	 * @param ccqdzҪ���õ� ccqdz
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
	 * @param cczdzҪ���õ� cczdz
	 */
	public void setCczdz(String cczdz) {
		this.cczdz = cczdz;
	}
	
}
