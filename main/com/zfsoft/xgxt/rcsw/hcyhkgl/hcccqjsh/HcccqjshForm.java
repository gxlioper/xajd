/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-18 ����08:50:25 
 */  
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjsh;
import com.zfsoft.xgxt.rcsw.hcyhkgl.comm.HcyhkForm;



/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �𳵳����������ģ��
 * @�๦������: TODO(�𳵳����������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-26 ����09:37:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class HcccqjshForm extends HcyhkForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
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
	private String thgw;//��λ�˻�
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
	 * @param ccqjtxidҪ���õ� ccqjtxid
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
	 * @param xhҪ���õ� xh
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
	 * @param txsjҪ���õ� txsj
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
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bzҪ���õ� bz
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
	 * @param typeҪ���õ� type
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
	 * @param splcҪ���õ� splc
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
	 * @param hcccqjmcҪ���õ� hcccqjmc
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
	 * @param hcyhklxmcҪ���õ� hcyhklxmc
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
	 * @param hcyhklxҪ���õ� hcyhklx
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
	 * @param cczdsfҪ���õ� cczdsf
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
	 * @param ccqdsfҪ���õ� ccqdsf
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
