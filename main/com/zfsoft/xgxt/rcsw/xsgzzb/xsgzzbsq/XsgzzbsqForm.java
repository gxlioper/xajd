package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq;

import com.zfsoft.xgxt.rcsw.xsgzzb.comm.XsgzzbForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ܱ�����
 */
public class XsgzzbsqForm extends XsgzzbForm {

	private static final long serialVersionUID = 1L;
	private String sqid;
	private String xn;
	private String xq;
	private String bmdm;
	private String bmdmmc;
	private String zc;
	private String zcmc;
	private String zcksjsrq;
	private String yzzygz;
	private String xzzygz;
	private String yj;
	private String lrsj;
	private String lrr;
	private String lrrxm;
	private String shzt;
	private String shztmc;
	private String splc;
	private String type;
	private String xqmc;
	private String qtgz;
	
	private String gzzblx ;//�����ܱ����ͣ�ѧԺxy���༶bj��
	private String dbfdy;	//���ศ��Ա
	
	// =========== �༶�ܱ� < ============
	private String bjdm;
	private String ydrs;
	private String sdrs;
	private String qjrs;
	private String wdrs;
	private String zynr;
	private String zywt;
	private String jjdc;
	private String filepath;
	private String nj;
	private String xymc;
	private String zymc;
	private String bjmc;
	private String wjlxmc;
	// =========== �༶�ܱ� > ============
	//�Ĵ���Ϣְҵ����ѧԺ�����ϴ����ļ�
	private String filegid;//������id
	private String wjlxdm;//�ļ�����
	private String fjlj;//�ļ�·��
	private String fjmc;//�ļ�����
	private String[] wjlxdms;//�ļ���������
	private String scbz;
	/**
	 * @return the scbz
	 */
	public String getScbz() {
		return scbz;
	}
	/**
	 * @param scbzҪ���õ� scbz
	 */
	public void setScbz(String scbz) {
		this.scbz = scbz;
	}
	/**
	 * @return the wjlxdms
	 */
	public String[] getWjlxdms() {
		return wjlxdms;
	}
	/**
	 * @param wjlxdmsҪ���õ� wjlxdms
	 */
	public void setWjlxdms(String[] wjlxdms) {
		this.wjlxdms = wjlxdms;
	}
	
	/**
	 * @return the filegid
	 */
	public String getFilegid() {
		return filegid;
	}
	/**
	 * @param filegidҪ���õ� filegid
	 */
	public void setFilegid(String filegid) {
		this.filegid = filegid;
	}
	/**
	 * @return the fjlj
	 */
	public String getFjlj() {
		return fjlj;
	}
	/**
	 * @param fjljҪ���õ� fjlj
	 */
	public void setFjlj(String fjlj) {
		this.fjlj = fjlj;
	}
	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmcҪ���õ� fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the wjlxdm
	 */
	public String getWjlxdm() {
		return wjlxdm;
	}
	/**
	 * @param wjlxdmҪ���õ� wjlxdm
	 */
	public void setWjlxdm(String wjlxdm) {
		this.wjlxdm = wjlxdm;
	}
	/**
	 * @return the wjlxmc
	 */
	public String getWjlxmc() {
		return wjlxmc;
	}
	/**
	 * @param wjlxmcҪ���õ� wjlxmc
	 */
	public void setWjlxmc(String wjlxmc) {
		this.wjlxmc = wjlxmc;
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

	public String getDbfdy() {
		return dbfdy;
	}

	public void setDbfdy(String dbfdy) {
		this.dbfdy = dbfdy;
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
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public String getBmdmmc() {
		return bmdmmc;
	}
	public void setBmdmmc(String bmdmmc) {
		this.bmdmmc = bmdmmc;
	}
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public String getZcmc() {
		return zcmc;
	}
	public void setZcmc(String zcmc) {
		this.zcmc = zcmc;
	}
	public String getZcksjsrq() {
		return zcksjsrq;
	}
	public void setZcksjsrq(String zcksjsrq) {
		this.zcksjsrq = zcksjsrq;
	}
	public String getYzzygz() {
		return yzzygz;
	}
	public void setYzzygz(String yzzygz) {
		this.yzzygz = yzzygz;
	}
	public String getXzzygz() {
		return xzzygz;
	}
	public void setXzzygz(String xzzygz) {
		this.xzzygz = xzzygz;
	}
	public String getYj() {
		return yj;
	}
	public void setYj(String yj) {
		this.yj = yj;
	}
	public String getLrsj() {
		return lrsj;
	}
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public String getLrrxm() {
		return lrrxm;
	}
	public void setLrrxm(String lrrxm) {
		this.lrrxm = lrrxm;
	}
	public String getQtgz() {
		return qtgz;
	}
	public void setQtgz(String qtgz) {
		this.qtgz = qtgz;
	}
	public String getGzzblx() {
		return gzzblx;
	}
	public void setGzzblx(String gzzblx) {
		this.gzzblx = gzzblx;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getYdrs() {
		return ydrs;
	}
	public void setYdrs(String ydrs) {
		this.ydrs = ydrs;
	}
	public String getSdrs() {
		return sdrs;
	}
	public void setSdrs(String sdrs) {
		this.sdrs = sdrs;
	}
	public String getQjrs() {
		return qjrs;
	}
	public void setQjrs(String qjrs) {
		this.qjrs = qjrs;
	}
	public String getWdrs() {
		return wdrs;
	}
	public void setWdrs(String wdrs) {
		this.wdrs = wdrs;
	}
	public String getZynr() {
		return zynr;
	}
	public void setZynr(String zynr) {
		this.zynr = zynr;
	}
	public String getZywt() {
		return zywt;
	}
	public void setZywt(String zywt) {
		this.zywt = zywt;
	}
	public String getJjdc() {
		return jjdc;
	}
	public void setJjdc(String jjdc) {
		this.jjdc = jjdc;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	
}
