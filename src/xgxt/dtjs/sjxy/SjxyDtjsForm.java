package xgxt.dtjs.sjxy;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class SjxyDtjsForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205711105806100577L;

	/* ͨ�� */
	Pages pages = new Pages();

	private String[] checkVal;// ������

	private String xh;// ѧ��

	private String xm;// ����

	private String xb;// �Ա�

	private String nj;// �꼶

	private String xn;// ѧ��

	private String xq;// ѧ��

	private String nd;// ���

	private String xydm;// ѧԺ����

	private String xymc;// ѧԺ����

	private String zydm;// רҵ����

	private String zymc;// רҵ����

	private String bjdm;// �༶����

	private String bjmc;// �༶����

	private String bz;// ��ע

	/* ����֧ */
	private String dzzmc;// ����֧����

	private String khqk;// �������

	/* ��֧�� */
	private String[] id;// 'ID';

	private String[] sszb;// ֧������

	private String[] sj;// '��֧�����';

	private String[] fsj;// '��֧�������';

	private String[] zzwy;// '��֯ίԱ';

	private String[] xcwy;// '����ίԱ';

	private String[] jjwy;// '�ͼ�ίԱ';

	/* �뵳���� */
	private String djsqsj;// �ݽ�����ʱ��

	private String zbmc;// ����֧��

	private String lx;// ����

	/* �뵳�������� */
	private String zzzt;// ��ְ״̬

	private String xsccdm;// ѧ�����

	/* Ԥ����Ա */

	private String kssj;// ��ʼʱ��

	private String jssj;// ����ʱ��

	private String zzlx;// 'ת������';

	/* ��ʽ��Ա */
	private String zzdw;// '��֯��λ';

	private String zzsj;// 'ת��ʱ��';

	private String rdsj;// '�뵳ʱ��';

	private String ybdykssj;// Ԥ����Ա��ʼʱ��

	private String ybdyjssj;// Ԥ����Ա����ʱ��

	/* ����ͳ�� */
	private String pycc;

	private String rdsjdks;

	private String rdsjdjs;

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getDjsqsj() {
		return djsqsj;
	}

	public void setDjsqsj(String djsqsj) {
		this.djsqsj = djsqsj;
	}

	public String getDzzmc() {
		return dzzmc;
	}

	public void setDzzmc(String dzzmc) {
		this.dzzmc = dzzmc;
	}

	public String[] getFsj() {
		return fsj;
	}

	public void setFsj(String[] fsj) {
		this.fsj = fsj;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getJjwy() {
		return jjwy;
	}

	public void setJjwy(String[] jjwy) {
		this.jjwy = jjwy;
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

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
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

	public String getRdsj() {
		return rdsj;
	}

	public void setRdsj(String rdsj) {
		this.rdsj = rdsj;
	}

	public String[] getSj() {
		return sj;
	}

	public void setSj(String[] sj) {
		this.sj = sj;
	}

	public String[] getSszb() {
		return sszb;
	}

	public void setSszb(String[] sszb) {
		this.sszb = sszb;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String[] getXcwy() {
		return xcwy;
	}

	public void setXcwy(String[] xcwy) {
		this.xcwy = xcwy;
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

	public String getXsccdm() {
		return xsccdm;
	}

	public void setXsccdm(String xsccdm) {
		this.xsccdm = xsccdm;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getYbdyjssj() {
		return ybdyjssj;
	}

	public void setYbdyjssj(String ybdyjssj) {
		this.ybdyjssj = ybdyjssj;
	}

	public String getYbdykssj() {
		return ybdykssj;
	}

	public void setYbdykssj(String ybdykssj) {
		this.ybdykssj = ybdykssj;
	}

	public String getZbmc() {
		return zbmc;
	}

	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getZzdw() {
		return zzdw;
	}

	public void setZzdw(String zzdw) {
		this.zzdw = zzdw;
	}

	public String getZzlx() {
		return zzlx;
	}

	public void setZzlx(String zzlx) {
		this.zzlx = zzlx;
	}

	public String getZzsj() {
		return zzsj;
	}

	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}

	public String[] getZzwy() {
		return zzwy;
	}

	public void setZzwy(String[] zzwy) {
		this.zzwy = zzwy;
	}

	public String getZzzt() {
		return zzzt;
	}

	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}

	public String getKhqk() {
		return khqk;
	}

	public void setKhqk(String khqk) {
		this.khqk = khqk;
	}

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getRdsjdjs() {
		return rdsjdjs;
	}

	public void setRdsjdjs(String rdsjdjs) {
		this.rdsjdjs = rdsjdjs;
	}

	public String getRdsjdks() {
		return rdsjdks;
	}

	public void setRdsjdks(String rdsjdks) {
		this.rdsjdks = rdsjdks;
	}

}
