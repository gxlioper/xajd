package xgxt.dtjs;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class DtjsForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* ͨ�� */
	Pages pages = new Pages();

	FormFile uploadFile;// �ϴ��ļ�

	private String fdyQx;// ����ԱȨ��

	private String bzrQx;// ������Ȩ��

	private String[] checkVal;// ������

	private String[] primarykey_checkVal;

	private String xysh;// ѧԺ���

	private String queryequals_xysh;

	private String xxsh;// ѧУ���

	private String queryequals_xxsh;

	private String userName;// �û���

	private String xh;// ѧ��

	private String querylike_xh;

	private String xm;// ����

	private String querylike_xm;

	private String xb;// �Ա�

	private String queryequals_xb;

	private String nj;// �꼶

	private String queryequals_nj;

	private String xn;// ѧ��

	private String queryequals_xn;

	private String save_xn;

	private String xq;// ѧ��

	private String queryequals_xq;

	private String save_xq;

	private String nd;// ���

	private String save_nd;;

	private String queryequals_nd;

	private String xydm;// ѧԺ����

	private String xymc;// ѧԺ����

	private String queryequals_xydm;

	private String zydm;// רҵ����

	private String queryequals_zydm;

	private String zymc;// רҵ����

	private String bjdm;// �༶����

	private String queryequals_bjdm;

	private String bjmc;// �༶����

	private String bz;// ��ע

	private String save_bz;

	private String lx;// ����

	private String queryequals_lx;

	private String id;// ID

	private String queryequals_id;

	private String zzmm;// ������ò

	private String queryequals_zzmm;

	private String mz;// ����

	private String queryequals_mz;

	private String mklx;// ģ������

	private String zzzt;// ��ְ״̬

	private String pycc;

	private String rdsjdks;

	private String rdsjdjs;

	/* ��ѵ��Ϣ */
	private String pxjssj;// ��ѵ��ʼʱ��

	private String pxkssj;// ��ѵ����ʱ��

	private String pxjg;// ��ѵ���

	private String pxxmdm;// ��ѵ��Ŀ����

	private String zsyw;// ֤������

	/* ��չ���� */
	private String kssj;// ��ʼʱ��

	private String jssj;// ����ʱ��

	// ===============�Ͼ���ʦ ��Ա��Ϣ====================

	private String rdsj;// '�뵳ʱ��';

	private String querygreaterequal_rdsj;

	private String querylessequal_rdsj;

	private String rtsj;// '����ʱ��';

	private String querygreaterequal_rtsj;

	private String querylessequal_rtsj;

	private String rtdd;// '���ŵص�';

	private String tyzbh;// '��Ա֤���';

	private String ywrtzys;// '��������־Ը��';

	private String xl;// 'ѧ��';

	// ===============�Ͼ���ʦ ��Ա��Ϣ end====================

	
	// ===============�㶫���� ���γɼ� end====================
	
	private String xxtd; //ѧϰ̬��
	private String xxjl; //ѧϰ����
	private String xxxg; //ѧϰЧ��
	
	public String getXxjl() {
		return xxjl;
	}

	public void setXxjl(String xxjl) {
		this.xxjl = xxjl;
	}

	public String getXxtd() {
		return xxtd;
	}

	public void setXxtd(String xxtd) {
		this.xxtd = xxtd;
	}

	public String getXxxg() {
		return xxxg;
	}

	public void setXxxg(String xxxg) {
		this.xxxg = xxxg;
	}

	public String getZsyw() {
		return zsyw;
	}

	public void setZsyw(String zsyw) {
		this.zsyw = zsyw;
	}

	public String getPxjg() {
		return pxjg;
	}

	public void setPxjg(String pxjg) {
		this.pxjg = pxjg;
	}

	public String getPxjssj() {
		return pxjssj;
	}

	public void setPxjssj(String pxjssj) {
		this.pxjssj = pxjssj;
	}

	public String getPxkssj() {
		return pxkssj;
	}

	public void setPxkssj(String pxkssj) {
		this.pxkssj = pxkssj;
	}

	public String getPxxmdm() {
		return pxxmdm;
	}

	public void setPxxmdm(String pxxmdm) {
		this.pxxmdm = pxxmdm;
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

	public String getZzzt() {
		return zzzt;
	}

	public void setZzzt(String zzzt) {
		this.zzzt = zzzt;
	}

	public String getRdsj() {
		return rdsj;
	}

	public void setRdsj(String rdsj) {
		this.rdsj = rdsj;
	}

	public String getRtdd() {
		return rtdd;
	}

	public void setRtdd(String rtdd) {
		this.rtdd = rtdd;
	}

	public String getRtsj() {
		return rtsj;
	}

	public void setRtsj(String rtsj) {
		this.rtsj = rtsj;
	}

	public String getTyzbh() {
		return tyzbh;
	}

	public void setTyzbh(String tyzbh) {
		this.tyzbh = tyzbh;
	}

	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	public String getYwrtzys() {
		return ywrtzys;
	}

	public void setYwrtzys(String ywrtzys) {
		this.ywrtzys = ywrtzys;
	}

	public String getBzrQx() {
		return bzrQx;
	}

	public void setBzrQx(String bzrQx) {
		this.bzrQx = bzrQx;
	}

	public String getFdyQx() {
		return fdyQx;
	}

	public void setFdyQx(String fdyQx) {
		this.fdyQx = fdyQx;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_id() {
		return queryequals_id;
	}

	public void setQueryequals_id(String queryequals_id) {
		this.queryequals_id = queryequals_id;
	}

	public String getQueryequals_lx() {
		return queryequals_lx;
	}

	public void setQueryequals_lx(String queryequals_lx) {
		this.queryequals_lx = queryequals_lx;
	}

	public String getQueryequals_nd() {
		return queryequals_nd;
	}

	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}

	public String getQueryequals_xb() {
		return queryequals_xb;
	}

	public void setQueryequals_xb(String queryequals_xb) {
		this.queryequals_xb = queryequals_xb;
	}

	public String getQueryequals_xn() {
		return queryequals_xn;
	}

	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}

	public String getQueryequals_xq() {
		return queryequals_xq;
	}

	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}

	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}

	public void setQueryequals_xxsh(String queryequals_xxsh) {
		this.queryequals_xxsh = queryequals_xxsh;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}

	public String getQueryequals_xysh() {
		return queryequals_xysh;
	}

	public void setQueryequals_xysh(String queryequals_xysh) {
		this.queryequals_xysh = queryequals_xysh;
	}

	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}

	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}

	public String getQuerylike_xh() {
		return querylike_xh;
	}

	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}

	public String getQuerylike_xm() {
		return querylike_xm;
	}

	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}

	public String getSave_bz() {
		return save_bz;
	}

	public void setSave_bz(String save_bz) {
		this.save_bz = save_bz;
	}

	public String getSave_nd() {
		return save_nd;
	}

	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
	}

	public String getSave_xn() {
		return save_xn;
	}

	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
	}

	public String getSave_xq() {
		return save_xq;
	}

	public void setSave_xq(String save_xq) {
		this.save_xq = save_xq;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getQueryequals_mz() {
		return queryequals_mz;
	}

	public void setQueryequals_mz(String queryequals_mz) {
		this.queryequals_mz = queryequals_mz;
	}

	public String getQueryequals_zzmm() {
		return queryequals_zzmm;
	}

	public void setQueryequals_zzmm(String queryequals_zzmm) {
		this.queryequals_zzmm = queryequals_zzmm;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getQuerygreaterequal_rdsj() {
		return querygreaterequal_rdsj;
	}

	public void setQuerygreaterequal_rdsj(String querygreaterequal_rdsj) {
		this.querygreaterequal_rdsj = querygreaterequal_rdsj;
	}

	public String getQuerygreaterequal_rtsj() {
		return querygreaterequal_rtsj;
	}

	public void setQuerygreaterequal_rtsj(String querygreaterequal_rtsj) {
		this.querygreaterequal_rtsj = querygreaterequal_rtsj;
	}

	public String getQuerylessequal_rdsj() {
		return querylessequal_rdsj;
	}

	public void setQuerylessequal_rdsj(String querylessequal_rdsj) {
		this.querylessequal_rdsj = querylessequal_rdsj;
	}

	public String getQuerylessequal_rtsj() {
		return querylessequal_rtsj;
	}

	public void setQuerylessequal_rtsj(String querylessequal_rtsj) {
		this.querylessequal_rtsj = querylessequal_rtsj;
	}

}
