package xgxt.pjpy.zjcm;

import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class ZjcmPjpyModel {

	/* ͨ�� */
	Pages pages = new Pages();

	FormFile uploadFile;// �ϴ��ļ�

	private String tableName;// ������
	
	private String fdyQx;// ����ԱȨ��

	private String bzrQx;// ������Ȩ��

	private String[] checkVal;// ������

	private String xysh;// ѧԺ���

	private String xxsh;// ѧУ���

	private String userName;// �û���

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

	private String xqdm;// У������

	private String lddm;// ¥������

	private String cs;// ����

	private String qsh;// ���Һ�
	
	/* �������� ����С�� */
	private String[] cpxy;// ����ѧԺ

	private String[] zwdm;// ְ�����

	private String zhfkg;// �ۺϷֿ���

	private String jxjkg;// ��ѧ�𿪹�

	/* �������� ������ */
	private String pycc;// �������

	private String[] xfjdxh;// ͬ����ѧ��

	private String[] xfjd;// ѧ�ּ���

	/* �������� �ۺϷ� */
	private String dyfbl;// �����ֱ���

	private String zyfbl;// �����ֱ���

	private String tyfbl;// �����ֱ���

	private String nlfbl;// �����ֱ���

	private String[] pjxh;// ����ѧ��

	private String[] dyf;// ������

	private String[] zyf;// ������

	private String[] tyf;// ������

	private String[] nlf;// ������

	/* �������� ��������� */
	private String[] wlkbjdm;// ����ư༶����

	private String bjlx;// �༶����

	/* �������� �������� */
	private String jxjdm;// ��ѧ�����

	private String jxjlbdm;// ��ѧ��������

	private String rychdm;// �����ƺŴ���

	private String tjzd;// �����ֶ�

	private String tjlx;// ��������

	private String tjz;// ����ֵ

	/* �������� ������� */
	private String[] jxjjd;// ��ѧ����

	private String[] rychjd;// �����ƺż��

	private String[] fjdlx;// �Ǽ������

	private String[] fjddm;// �Ǽ�ô���

	/* �������� У�⽱ѧ�� */
	private String xwjxjdm;// У�⽱ѧ�����

	/* �������� ����ͳ�� */
	private String yhdm;// ���д���
	
	private String yhlx;// ��������

	private String yhmc;// ��������
	
	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjlx() {
		return bjlx;
	}

	public void setBjlx(String bjlx) {
		this.bjlx = bjlx;
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

	public String getBzrQx() {
		return bzrQx;
	}

	public void setBzrQx(String bzrQx) {
		this.bzrQx = bzrQx;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String[] getCpxy() {
		return cpxy;
	}

	public void setCpxy(String[] cpxy) {
		this.cpxy = cpxy;
	}

	public String[] getDyf() {
		return dyf;
	}

	public void setDyf(String[] dyf) {
		this.dyf = dyf;
	}

	public String getDyfbl() {
		return dyfbl;
	}

	public void setDyfbl(String dyfbl) {
		this.dyfbl = dyfbl;
	}

	public String getFdyQx() {
		return fdyQx;
	}

	public void setFdyQx(String fdyQx) {
		this.fdyQx = fdyQx;
	}

	public String[] getFjddm() {
		return fjddm;
	}

	public void setFjddm(String[] fjddm) {
		this.fjddm = fjddm;
	}

	public String[] getFjdlx() {
		return fjdlx;
	}

	public void setFjdlx(String[] fjdlx) {
		this.fjdlx = fjdlx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJxjdm() {
		return jxjdm;
	}

	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}

	public String[] getJxjjd() {
		return jxjjd;
	}

	public void setJxjjd(String[] jxjjd) {
		this.jxjjd = jxjjd;
	}

	public String getJxjkg() {
		return jxjkg;
	}

	public void setJxjkg(String jxjkg) {
		this.jxjkg = jxjkg;
	}

	public String getJxjlbdm() {
		return jxjlbdm;
	}

	public void setJxjlbdm(String jxjlbdm) {
		this.jxjlbdm = jxjlbdm;
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

	public String[] getNlf() {
		return nlf;
	}

	public void setNlf(String[] nlf) {
		this.nlf = nlf;
	}

	public String getNlfbl() {
		return nlfbl;
	}

	public void setNlfbl(String nlfbl) {
		this.nlfbl = nlfbl;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getPjxh() {
		return pjxh;
	}

	public void setPjxh(String[] pjxh) {
		this.pjxh = pjxh;
	}

	public String getPycc() {
		return pycc;
	}

	public void setPycc(String pycc) {
		this.pycc = pycc;
	}

	public String getRychdm() {
		return rychdm;
	}

	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}

	public String[] getRychjd() {
		return rychjd;
	}

	public void setRychjd(String[] rychjd) {
		this.rychjd = rychjd;
	}

	public String getTjlx() {
		return tjlx;
	}

	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}

	public String getTjz() {
		return tjz;
	}

	public void setTjz(String tjz) {
		this.tjz = tjz;
	}

	public String getTjzd() {
		return tjzd;
	}

	public void setTjzd(String tjzd) {
		this.tjzd = tjzd;
	}

	public String[] getTyf() {
		return tyf;
	}

	public void setTyf(String[] tyf) {
		this.tyf = tyf;
	}

	public String getTyfbl() {
		return tyfbl;
	}

	public void setTyfbl(String tyfbl) {
		this.tyfbl = tyfbl;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String[] getWlkbjdm() {
		return wlkbjdm;
	}

	public void setWlkbjdm(String[] wlkbjdm) {
		this.wlkbjdm = wlkbjdm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String[] getXfjd() {
		return xfjd;
	}

	public void setXfjd(String[] xfjd) {
		this.xfjd = xfjd;
	}

	public String[] getXfjdxh() {
		return xfjdxh;
	}

	public void setXfjdxh(String[] xfjdxh) {
		this.xfjdxh = xfjdxh;
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

	public String getXwjxjdm() {
		return xwjxjdm;
	}

	public void setXwjxjdm(String xwjxjdm) {
		this.xwjxjdm = xwjxjdm;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
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

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getYhdm() {
		return yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	public String getYhlx() {
		return yhlx;
	}

	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}

	public String getZhfkg() {
		return zhfkg;
	}

	public void setZhfkg(String zhfkg) {
		this.zhfkg = zhfkg;
	}

	public String[] getZwdm() {
		return zwdm;
	}

	public void setZwdm(String[] zwdm) {
		this.zwdm = zwdm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getZyf() {
		return zyf;
	}

	public void setZyf(String[] zyf) {
		this.zyf = zyf;
	}

	public String getZyfbl() {
		return zyfbl;
	}

	public void setZyfbl(String zyfbl) {
		this.zyfbl = zyfbl;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getXqdm() {
		return xqdm;
	}

	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
