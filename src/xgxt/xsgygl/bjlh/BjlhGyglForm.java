package xgxt.xsgygl.bjlh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class BjlhGyglForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205711105806100577L;

	/* ͨ�� */
	Pages pages = new Pages();

	FormFile uploadFile;// �ϴ��ļ�

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

	private String lddm;// ¥������

	/* ����ά�� */

	private String fqrzxh;// ��ȫ����ѧ��
	
	private String[] sftws;// �Ƿ���ί��

	private String[] sftys;// �Ƿ�������

	private String sfbj;// �Ƿ���

	private String[] twsxh;// ��ί��

	private String[] tysxh;// ������

	private String lx;// '����';

	private String mz;// '����';

	private String zzmm;// '������ò';

	private String csrq;// '��������';

	private String sg;// '���';

	private String tz;// '����';

	private String sfzh;// '���֤��';

	private String lxdh;// '��ϵ�绰';

	private String jg;// '����';

	private String xz;// 'ѧ��';

	private String lydq;// '��Դ����';

	private String rxrq;// '��ѧ����';

	private String cs;// '����';

	private String qsh;// '���Һ�';

	private String cws;// '��λ��';

	private String fbbj;// '�������';

	private String qsdh;// '���ҵ绰';

	private String sfbz;// '�շѱ�׼';

	private String xqdm;// 'У������';

	private String cwh;// '��λ��';

	private String rzrq;// '��ס����';

	private String tfrq;// '�˷�����';

	private String zzbj;// 'ס�ޱ��';

	private String fplx;// '��������';

	private String sfyc;// '�Ƿ��쳣';
	
	private String errlx;// '�����쳣����';
	
	/* ͳ�Ʊ��� */
	private String bblx;// ��������

	private String byny;// ��ҵ����
	
	private String conditionSqlText;

	private String conditionSqlValue;

	private String oldCondiSqlValue;

	private String oracleItem;

	private String sql;
	
	private String fpbj;
	
	private String bysj;
	
	private String tjlx;
	
	private String sclx;
	
	private String zt;
	
	private String[] cbv;
	
	private String conditionXlValue;

	public String getConditionXlValue() {
		return conditionXlValue;
	}

	public void setConditionXlValue(String conditionXlValue) {
		this.conditionXlValue = conditionXlValue;
	}

	public String getFpbj() {
		return fpbj;
	}

	public void setFpbj(String fpbj) {
		this.fpbj = fpbj;
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

	public String getByny() {
		return byny;
	}

	public void setByny(String byny) {
		this.byny = byny;
	}

	public String getBblx() {
		return bblx;
	}

	public void setBblx(String bblx) {
		this.bblx = bblx;
	}

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

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String getSfbj() {
		return sfbj;
	}

	public void setSfbj(String sfbj) {
		this.sfbj = sfbj;
	}

	public String[] getSftws() {
		return sftws;
	}

	public void setSftws(String[] sftws) {
		this.sftws = sftws;
	}

	public String[] getTwsxh() {
		return twsxh;
	}

	public void setTwsxh(String[] twsxh) {
		this.twsxh = twsxh;
	}

	public String[] getSftys() {
		return sftys;
	}

	public void setSftys(String[] sftys) {
		this.sftys = sftys;
	}

	public String[] getTysxh() {
		return tysxh;
	}

	public void setTysxh(String[] tysxh) {
		this.tysxh = tysxh;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getLydq() {
		return lydq;
	}

	public void setLydq(String lydq) {
		this.lydq = lydq;
	}

	public String getRxrq() {
		return rxrq;
	}

	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getCws() {
		return cws;
	}

	public void setCws(String cws) {
		this.cws = cws;
	}

	public String getFbbj() {
		return fbbj;
	}

	public void setFbbj(String fbbj) {
		this.fbbj = fbbj;
	}

	public String getQsdh() {
		return qsdh;
	}

	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	public String getSfbz() {
		return sfbz;
	}

	public void setSfbz(String sfbz) {
		this.sfbz = sfbz;
	}

	public String getXqdm() {
		return xqdm;
	}

	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}

	public String getCwh() {
		return cwh;
	}

	public void setCwh(String cwh) {
		this.cwh = cwh;
	}

	public String getRzrq() {
		return rzrq;
	}

	public void setRzrq(String rzrq) {
		this.rzrq = rzrq;
	}

	public String getTfrq() {
		return tfrq;
	}

	public void setTfrq(String tfrq) {
		this.tfrq = tfrq;
	}

	public String getZzbj() {
		return zzbj;
	}

	public void setZzbj(String zzbj) {
		this.zzbj = zzbj;
	}

	public String getFplx() {
		return fplx;
	}

	public void setFplx(String fplx) {
		this.fplx = fplx;
	}

	public String getConditionSqlText() {
		return conditionSqlText;
	}

	public void setConditionSqlText(String conditionSqlText) {
		this.conditionSqlText = conditionSqlText;
	}

	public String getConditionSqlValue() {
		return conditionSqlValue;
	}

	public void setConditionSqlValue(String conditionSqlValue) {
		this.conditionSqlValue = conditionSqlValue;
	}

	public String getOldCondiSqlValue() {
		return oldCondiSqlValue;
	}

	public void setOldCondiSqlValue(String oldCondiSqlValue) {
		this.oldCondiSqlValue = oldCondiSqlValue;
	}

	public String getOracleItem() {
		return oracleItem;
	}

	public void setOracleItem(String oracleItem) {
		this.oracleItem = oracleItem;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSfyc() {
		return sfyc;
	}

	public void setSfyc(String sfyc) {
		this.sfyc = sfyc;
	}

	public String getBysj() {
		return bysj;
	}

	public void setBysj(String bysj) {
		this.bysj = bysj;
	}

	public String[] getCbv() {
		return cbv;
	}

	public void setCbv(String[] cbv) {
		this.cbv = cbv;
	}

	public String getSclx() {
		return sclx;
	}

	public void setSclx(String sclx) {
		this.sclx = sclx;
	}

	public String getTjlx() {
		return tjlx;
	}

	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getFqrzxh() {
		return fqrzxh;
	}

	public void setFqrzxh(String fqrzxh) {
		this.fqrzxh = fqrzxh;
	}

	public String getErrlx() {
		return errlx;
	}

	public void setErrlx(String errlx) {
		this.errlx = errlx;
	}

}
