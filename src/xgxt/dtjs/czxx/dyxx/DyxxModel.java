package xgxt.dtjs.czxx.dyxx;

import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class DyxxModel {

	/* ͨ�� */
	Pages pages = new Pages();

	FormFile uploadFile;// �ϴ��ļ�

	SearchModel searchModel = new SearchModel();

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

	private String lx;// ����

	private String id;// ID

	private String zd;// ��ѯ�ֶ�

	private String zdValue;// ��ѯ�ֶ�ֵ

	private String zhdj;// ת���ȼ�

	private String zhsj;// ת��ʱ��

	/* �뵳���� */
	private String sqsj;// ����ʱ��

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

	/* ˼��㱨 */
	private String wjm;// '�ļ���';

	private String tjsj;// '�ύʱ��';

	private String scdz;// '�ϴ���ַ';

	private String tjr;// '�ύ��';

	/* �������� */
	private String[] pxmdxh;// ��ѵ����ѧ��

	private String pxsj;// ��ѵʱ��

	/* ��ѵ��Ϣ */

	private String pxmc;// ��ѵ����

	private String pxdd;// ��ѵ�ص�

	private String pxnr;// ��ѵ����

	private String[] dkcj;// ���γɼ�

	// ===========================�㶫����-������ѵ�ɼ� sjf==========================
	private String[] xxtd; // ѧϰ̬��

	private String[] xxjl; // ѧϰ����

	private String[] xxxg; // ѧϰЧ��

	private String xxtdbl; // ѧϰ̬�ȱ���

	private String xxjlbl; // ѧϰ���ɱ���

	private String xxxgbl; // ѧϰЧ������

	// ===========================�㶫����-������ѵ�ɼ� =============================
	private String lwjjfzsj;
	
	private String dnzw;
	
	private String db;
	
	private String zb;
	

	public String getZb() {
		return zb;
	}

	public void setZb(String zb) {
		this.zb = zb;
	}

	public String getDnzw() {
		return dnzw;
	}

	public void setDnzw(String dnzw) {
		this.dnzw = dnzw;
	}

	public String[] getDkcj() {
		return dkcj;
	}

	public void setDkcj(String[] dkcj) {
		this.dkcj = dkcj;
	}

	public String getPxmc() {
		return pxmc;
	}

	public void setPxmc(String pxmc) {
		this.pxmc = pxmc;
	}

	public String[] getPxmdxh() {
		return pxmdxh;
	}

	public void setPxmdxh(String[] pxmdxh) {
		this.pxmdxh = pxmdxh;
	}

	public String getTjr() {
		return tjr;
	}

	public void setTjr(String tjr) {
		this.tjr = tjr;
	}

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

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
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

	public String getZzzt() {
		return zzzt;
	}

	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
	}

	public String getScdz() {
		return scdz;
	}

	public void setScdz(String scdz) {
		this.scdz = scdz;
	}

	public String getTjsj() {
		return tjsj;
	}

	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}

	public String getPxsj() {
		return pxsj;
	}

	public void setPxsj(String pxsj) {
		this.pxsj = pxsj;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getZdValue() {
		return zdValue;
	}

	public void setZdValue(String zdValue) {
		this.zdValue = zdValue;
	}

	public String getPxdd() {
		return pxdd;
	}

	public void setPxdd(String pxdd) {
		this.pxdd = pxdd;
	}

	public String getPxnr() {
		return pxnr;
	}

	public void setPxnr(String pxnr) {
		this.pxnr = pxnr;
	}

	public String getZhdj() {
		return zhdj;
	}

	public void setZhdj(String zhdj) {
		this.zhdj = zhdj;
	}

	public String getZhsj() {
		return zhsj;
	}

	public void setZhsj(String zhsj) {
		this.zhsj = zhsj;
	}

	public String[] getXxjl() {
		return xxjl;
	}

	public void setXxjl(String[] xxjl) {
		this.xxjl = xxjl;
	}

	public String[] getXxtd() {
		return xxtd;
	}

	public void setXxtd(String[] xxtd) {
		this.xxtd = xxtd;
	}

	public String[] getXxxg() {
		return xxxg;
	}

	public void setXxxg(String[] xxxg) {
		this.xxxg = xxxg;
	}

	public String getXxjlbl() {
		return xxjlbl;
	}

	public void setXxjlbl(String xxjlbl) {
		this.xxjlbl = xxjlbl;
	}

	public String getXxtdbl() {
		return xxtdbl;
	}

	public void setXxtdbl(String xxtdbl) {
		this.xxtdbl = xxtdbl;
	}

	public String getXxxgbl() {
		return xxxgbl;
	}

	public void setXxxgbl(String xxxgbl) {
		this.xxxgbl = xxxgbl;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getLwjjfzsj() {
		return lwjjfzsj;
	}

	public void setLwjjfzsj(String lwjjfzsj) {
		this.lwjjfzsj = lwjjfzsj;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}


}
