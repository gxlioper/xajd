package xgxt.szdw.xmlg;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class XmlgSzdwForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205711105806100577L;

	// ѧ��
	private String xh;

	// ����
	private String xm;

	// �·�
	private String yf;

	// ����Աְ����
	private String zgh;

	// ��ʦ����
	private String jsdm;

	// �걨��ѧ��
	private String sbr;

	// �걨������
	private String sbrxm;

	// �걨ʱ��
	private String sbsj;

	// �Ա�
	private String xb;

	// �꼶
	private String nj;

	// ���Ŵ���
	private String bmdm;

	// ѧԺ����
	private String xydm;

	// רҵ����
	private String zydm;

	// �༶����
	private String bjdm;

	// ѧ��
	private String xn;

	// ���
	private String nd;

	// ѧ��
	private String xq;

	// ����ѧ��
	private String hzxn;

	// ����ѧ��
	private String hzxq;

	// ���ܿ�ʼʱ��
	private String kssj;

	// ���ܽ���ʱ��
	private String jssj;

	// �ֶ�
	private String zd;

	// �ֶ���
	private String zdm;

	// �ֶ�
	private String[] arrZd;

	// �ֶ���
	private String[] arrZdz;

	// �ֶ�����
	private String zdlx;

	// ѧԺ������
	private String xyyj;

	// ѧУ������
	private String xxyj;

	// �����÷�
	private String cpdf;

	private String lx;// '����';
	
	private String gsnr;// '��ʧ����';

	private String gssj;// '��ʧʱ��';

	private String bz;// '��ע';

	private String lrr;// '¼����';

	private String lrsj;// '¼��ʱ��';

	private String bgid;// '����ID';

	private String tjr;// '�ύ��';

	private String bgdm;// '�������';

	private String xmmc;// '��Ŀ����';
	
	private String bgnr;// '��������';

	private String ydbf;// 'Ӧ�԰취';
	
	private String tjsj;// '�ύʱ��';

	// ͨ�÷�ҳ
	Pages pages = new Pages();

	// ��ѡ��
	private String[] checkVal;

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
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

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getZdlx() {
		return zdlx;
	}

	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getXxyj() {
		return xxyj;
	}

	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}

	public String getXyyj() {
		return xyyj;
	}

	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}

	public String getCpdf() {
		return cpdf;
	}

	public void setCpdf(String cpdf) {
		this.cpdf = cpdf;
	}

	public String[] getArrZd() {
		return arrZd;
	}

	public void setArrZd(String[] arrZd) {
		this.arrZd = arrZd;
	}

	public String[] getArrZdz() {
		return arrZdz;
	}

	public void setArrZdz(String[] arrZdz) {
		this.arrZdz = arrZdz;
	}

	public String getSbr() {
		return sbr;
	}

	public void setSbr(String sbr) {
		this.sbr = sbr;
	}

	public String getSbrxm() {
		return sbrxm;
	}

	public void setSbrxm(String sbrxm) {
		this.sbrxm = sbrxm;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getJsdm() {
		return jsdm;
	}

	public void setJsdm(String jsdm) {
		this.jsdm = jsdm;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getHzxn() {
		return hzxn;
	}

	public void setHzxn(String hzxn) {
		this.hzxn = hzxn;
	}

	public String getHzxq() {
		return hzxq;
	}

	public void setHzxq(String hzxq) {
		this.hzxq = hzxq;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getGsnr() {
		return gsnr;
	}

	public void setGsnr(String gsnr) {
		this.gsnr = gsnr;
	}

	public String getGssj() {
		return gssj;
	}

	public void setGssj(String gssj) {
		this.gssj = gssj;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public String getBgdm() {
		return bgdm;
	}

	public void setBgdm(String bgdm) {
		this.bgdm = bgdm;
	}

	public String getBgid() {
		return bgid;
	}

	public void setBgid(String bgid) {
		this.bgid = bgid;
	}

	public String getBgnr() {
		return bgnr;
	}

	public void setBgnr(String bgnr) {
		this.bgnr = bgnr;
	}

	public String getTjr() {
		return tjr;
	}

	public void setTjr(String tjr) {
		this.tjr = tjr;
	}

	public String getTjsj() {
		return tjsj;
	}

	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getYdbf() {
		return ydbf;
	}

	public void setYdbf(String ydbf) {
		this.ydbf = ydbf;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

}
