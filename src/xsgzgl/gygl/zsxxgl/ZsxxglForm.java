package xsgzgl.gygl.zsxxgl;


import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.utils.Pages;
import xsgzgl.gygl.comm.GyglNewForm;

public class ZsxxglForm extends GyglNewForm {

	
	private static final long serialVersionUID = 1L;
	private String type;
	private String lddm;//¥������
	private String qsh;//���Һ�
	private String cwh;//��λ��
	private String xb;	//�Ա�
	private String xh;
	private String xm;
	private String xydm;
	private String zydm;
	private String nj;
	private String ch;
	private String tsyy;
	private String bz;
	private String xhs;
	private String[] pk_xh;
	private String tssj;
	private String rzsj; //��סʱ��
	private String czr;//������
	private String bzr;
	private ExportModel exportModel = new ExportModel();
	private Pages page=new Pages();

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
	 * ѧ��
	 */
	private String xn;
	
	/**
	 * ѧ��
	 */
	private String xq;
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	
	public Pages getPage() {
		return page;
	}
	public void setPage(Pages page) {
		this.page = page;
	}
	private String rzyy;
	
	private String rzyydm;
	private String rzyymc;
	//������У �Ƿ��ʼ����λ
	private String sfqccwss;
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}	
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getCwh() {
		return cwh;
	}
	public void setCwh(String cwh) {
		this.cwh = cwh;
	}
	public String getTsyy() {
		return tsyy;
	}
	public void setTsyy(String tsyy) {
		this.tsyy = tsyy;
	}
	public String[] getPk_xh() {
		return pk_xh;
	}
	public void setPk_xh(String[] pkXh) {
		pk_xh = pkXh;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getXhs() {
		return xhs;
	}
	public void setXhs(String xhs) {
		this.xhs = xhs;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	
	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @param zydmҪ���õ� zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getTssj() {
		return tssj;
	}
	public void setTssj(String tssj) {
		this.tssj = tssj;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModelҪ���õ� exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getRzyy() {
		return rzyy;
	}
	public void setRzyy(String rzyy) {
		this.rzyy = rzyy;
	}
	public String getRzyydm() {
		return rzyydm;
	}
	public void setRzyydm(String rzyydm) {
		this.rzyydm = rzyydm;
	}
	public String getRzyymc() {
		return rzyymc;
	}
	public void setRzyymc(String rzyymc) {
		this.rzyymc = rzyymc;
	}
	/**
	 * @return the sfqccwss
	 */
	public String getSfqccwss() {
		return sfqccwss;
	}
	/**
	 * @param sfqccwssҪ���õ� sfqccwss
	 */
	public void setSfqccwss(String sfqccwss) {
		this.sfqccwss = sfqccwss;
	}
	/**
	 * @return the bzr
	 */
	public String getBzr() {
		return bzr;
	}
	/**
	 * @param bzrҪ���õ� bzr
	 */
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	
	
}
