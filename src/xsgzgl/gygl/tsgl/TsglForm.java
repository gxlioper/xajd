package xsgzgl.gygl.tsgl;


import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xsgzgl.gygl.comm.GyglNewForm;

public class TsglForm extends GyglNewForm {

	
	private static final long serialVersionUID = 1L;

	private String lddm;//¥������(�춯ǰ¥�������ҡ���λ)
	private String qsh;//���Һ�
	private String cwh;//��λ��
	private String xb;	//�Ա�
	private String xh;
	private String xm;
	private String xydm;
	private String nj;
	private String ch;
	private String tsyy;
	private String bz;
	private String xhs;
	private String[] pk_xh;
	
	private String[]primarykey_checkVal;//����
	private String tssj;//����ʱ��
	private String tsczr;//���޲�����
	
	private ExportModel exportModel = new ExportModel();

	/**
	 * ѧ��
	 */
	private String xn;
	
	/**
	 * ѧ��
	 */
	private String xq;
	/**
	 * �Ƿ�λ��ʼ��
	 */
	private String sfcwcsh;
	
	/**
	 * �����춯����
	 */
	private String ssydlx;
	
	/**
	 * �춯��¥�����Ҵ�λ
	 */
	private String ydhldqscw;
	
	public String getYdhldqscw() {
		return ydhldqscw;
	}
	public void setYdhldqscw(String ydhldqscw) {
		this.ydhldqscw = ydhldqscw;
	}
	public String getSsydlx() {
		return ssydlx;
	}
	public void setSsydlx(String ssydlx) {
		this.ssydlx = ssydlx;
	}
	public String getSfcwcsh() {
		return sfcwcsh;
	}
	public void setSfcwcsh(String sfcwcsh) {
		this.sfcwcsh = sfcwcsh;
	}
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
	public String getTsczr() {
		return tsczr;
	}
	public void setTsczr(String tsczr) {
		this.tsczr = tsczr;
	}
	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}
	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
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
	
}
