package xgxt.xsgygl.csmzzyjsxy;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ɳ����ѧԺ��Ԣ����ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-11</p>
 */ 
public class GyglCsmzActionForm extends ActionForm {
	private static final long serialVersionUID = -8159163590410254032L;
	
	/* ͨ�� */
	Pages pages = new Pages();
	private String xy;//ѧԺ
	private String zy;//רҵ
	private String bj;//�༶
	private String xh;//ѧ��
	private String nj;//�꼶
	private String zgh;//����Աְ����
	private String xm;//����
	private String xn;//ѧ��
	private String xq;//ѧ��
	private String lddm;//¥��
	private String qsh;//���Һ�
	private String sj;//����ʱ��
	private String zywt;//��Ҫ��������
	private String bz;//��ע
	private String xsfk;//ѧ��������Ϣ
	private String xqdm;//У������
	private String xb;//�Ա�
	private String xydm;//ѧԺ����
	private String zydm;//רҵ����
	private String bjdm;//�༶����
	private String oracleItem;
	private String sql;
	private String conditionSqlText;
	private String conditionSqlValue;
	private String count;
	private String boy;
	private String girl;
	private String oldCondiSqlValue;
	private String cwfps;
	private String fpfs;
	private String xb2;
	private String cwh;
	private String userType;//
	private String zsrq;
	private String ksh;
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBoy() {
		return boy;
	}
	public void setBoy(String boy) {
		this.boy = boy;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getCwfps() {
		return cwfps;
	}
	public void setCwfps(String cwfps) {
		this.cwfps = cwfps;
	}
	public String getCwh() {
		return cwh;
	}
	public void setCwh(String cwh) {
		this.cwh = cwh;
	}
	public String getFpfs() {
		return fpfs;
	}
	public void setFpfs(String fpfs) {
		this.fpfs = fpfs;
	}
	public String getGirl() {
		return girl;
	}
	public void setGirl(String girl) {
		this.girl = girl;
	}
	public String getKsh() {
		return ksh;
	}
	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
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
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXb2() {
		return xb2;
	}
	public void setXb2(String xb2) {
		this.xb2 = xb2;
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
	public String getXqdm() {
		return xqdm;
	}
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}
	public String getXsfk() {
		return xsfk;
	}
	public void setXsfk(String xsfk) {
		this.xsfk = xsfk;
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getZsrq() {
		return zsrq;
	}
	public void setZsrq(String zsrq) {
		this.zsrq = zsrq;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZywt() {
		return zywt;
	}
	public void setZywt(String zywt) {
		this.zywt = zywt;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
}
