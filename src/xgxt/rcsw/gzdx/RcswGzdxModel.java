package xgxt.rcsw.gzdx;

import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class RcswGzdxModel { /* ͨ�� */
	Pages pages = new Pages();

	FormFile uploadFile;// �ϴ��ļ�

	private String[] checkVal;//������
	
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

	private String pkValue;// ����ֵ
	
	private String bmdm;//���Ŵ���
	
	private String zgh;// 'ְ����';
	
	private String zw;// 'ְλ';
	
	// ----------ѧ������-------------
	private String lymc;// '��������';

	private String lylx;// '��������';

	private String lysj;// '����ʱ��';

	private String lynr;// '��������';

	private String lyr;// '������';

	// ----------��ʦ�ظ�-------------

	private String hfr;// '�ظ���';

	private String hfsj;// '�ظ�ʱ��';

	private String hfnr;// '�ظ�����';

	private String hfpj;// '�ظ�����';

	private String bjlyxx;// '�༭������Ϣ';

	private String czsj;//�ö�����ʱ��;
	
	private String hfls;// '�ظ�¥��';
	
	private String lydx;//���Զ���
	
	public String getLydx() {
		return lydx;
	}

	public void setLydx(String lydx) {
		this.lydx = lydx;
	}

	public String getHfls() {
		return hfls;
	}

	public void setHfls(String hfls) {
		this.hfls = hfls;
	}

	public String getBjlyxx() {
		return bjlyxx;
	}

	public void setBjlyxx(String bjlyxx) {
		this.bjlyxx = bjlyxx;
	}

	public String getHfnr() {
		return hfnr;
	}

	public void setHfnr(String hfnr) {
		this.hfnr = hfnr;
	}

	public String getHfpj() {
		return hfpj;
	}

	public void setHfpj(String hfpj) {
		this.hfpj = hfpj;
	}

	public String getHfr() {
		return hfr;
	}

	public void setHfr(String hfr) {
		this.hfr = hfr;
	}

	public String getHfsj() {
		return hfsj;
	}

	public void setHfsj(String hfsj) {
		this.hfsj = hfsj;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getLylx() {
		return lylx;
	}

	public void setLylx(String lylx) {
		this.lylx = lylx;
	}

	public String getLymc() {
		return lymc;
	}

	public void setLymc(String lymc) {
		this.lymc = lymc;
	}

	public String getLynr() {
		return lynr;
	}

	public void setLynr(String lynr) {
		this.lynr = lynr;
	}

	public String getLyr() {
		return lyr;
	}

	public void setLyr(String lyr) {
		this.lyr = lyr;
	}

	public String getLysj() {
		return lysj;
	}

	public void setLysj(String lysj) {
		this.lysj = lysj;
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

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
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

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
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

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}
}
