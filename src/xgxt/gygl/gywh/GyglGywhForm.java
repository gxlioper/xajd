package xgxt.gygl.gywh;

import xgxt.form.User;
import xgxt.gygl.GyglCommForm;

public class GyglGywhForm extends GyglCommForm {

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private String tableName;//����
	
	private String realTable;//�����;
	
	private String query;//����
	
	private String []inputArr;//����ֵ
	
	private String []yqArr;//԰��;
	
	private String []xqArr;//У��
	
	private String pkValue;//����ֵ
	
	private String bhgz="blxs";//��Ź���(Ĭ��Ϊ����)
	
	private String lcsz="15";
	
	private String qsh;//���Һ�
	
	private String cws;//��λ��
	
	private String []cwhArr;//
	
	private String fpbj;//������
	
	private String qsdh;//���ҵ绰
	
	private String sfbz;//�ܷѱ�׼
	
	private String bls;//���Һ�λ��
	
	private String qss;//������
	
	private String []qsslArr;//��������
	
	private String []cwsArr;//��������
	
	private String []sfbzArr;//��������
	
	private String []fpbjArr;//��������
	
	private String []csArr;//��������
	
	private String []xbArr;//�Ա�����
	
	private String []kfhzArr;//�ɷ��ס
	
	private String xb;//�Ա�
	
	private String kfhz;//�ɷ��ס
	
	private String ssbh;//������
	
	private String lddm;//¥������
	
	private String sfdr;//�Ƿ���
	
	private String dm;//У������
	
	private String xqdm;//У������
	
	private String xqmc;//У������
	
	private String yqdm;//԰������
	
	private String yqmc;//԰������
	
	private String mklx;// ģ������;
	
	private String ldmc;//¥������;
	
	private String cs="7";//¥��;
	
	private String xbxd;//�Ա��޶�
	
	private String bz;// ��ע

	private String qsbz;// ���ұ�ע
	
	private String czxq;//����У��
	
	private String czyq;//����԰��
	
	private String []cwbjArr;//��λ����
	
	private String []checkVal;
	
	private String []colList;//����ֶ�;
	
	private String []topTr;//��ͷ
	
	private String cwgs;//��λ����
	
	private String wsxz;//λ��ѡ��
	
	private String scb;//ɾ����
	
	private String glb;//������
	
	private String path;//·��
	
	private String xh;//ѧ��
	
	private String []rzqk;//��ס���
	
	private String fpdx;//�������
	
	private String[]rzqkHidArr;
	
	private String[]xbxdHidArr;
	
	private String ldHid;//¥������������
	
	private String fpbm;//���䲿��
	
	private String cwsSelect;
	
	public String getCwsSelect() {
		return cwsSelect;
	}

	public void setCwsSelect(String cwsSelect) {
		this.cwsSelect = cwsSelect;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getGlb() {
		return glb;
	}

	public void setGlb(String glb) {
		this.glb = glb;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getScb() {
		return scb;
	}

	public void setScb(String scb) {
		this.scb = scb;
	}

	public String getWsxz() {
		return wsxz;
	}

	public void setWsxz(String wsxz) {
		this.wsxz = wsxz;
	}

	public String getCwgs() {
		return cwgs;
	}

	public void setCwgs(String cwgs) {
		this.cwgs = cwgs;
	}

	public String[] getColList() {
		return colList;
	}

	public void setColList(String[] colList) {
		this.colList = colList;
	}

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}

	public String[] getTopTr() {
		return topTr;
	}

	public void setTopTr(String[] topTr) {
		this.topTr = topTr;
	}

	public String getXqdm() {
		return xqdm;
	}

	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getYqdm() {
		return yqdm;
	}

	public void setYqdm(String yqdm) {
		this.yqdm = yqdm;
	}

	public String getYqmc() {
		return yqmc;
	}

	public void setYqmc(String yqmc) {
		this.yqmc = yqmc;
	}

	public String getSfdr() {
		return sfdr;
	}

	public void setSfdr(String sfdr) {
		this.sfdr = sfdr;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String[] getInputArr() {
		return inputArr;
	}

	public void setInputArr(String[] inputArr) {
		this.inputArr = inputArr;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String getSsbh() {
		return ssbh;
	}

	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public String getRealTable() {
		return realTable;
	}

	public void setRealTable(String realTable) {
		this.realTable = realTable;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getLdmc() {
		return ldmc;
	}

	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}

	public String getXbxd() {
		return xbxd;
	}

	public void setXbxd(String xbxd) {
		this.xbxd = xbxd;
	}

	public String getLcsz() {
		return lcsz;
	}

	public void setLcsz(String lcsz) {
		this.lcsz = lcsz;
	}

	public String[] getXqArr() {
		return xqArr;
	}

	public void setXqArr(String[] xqArr) {
		this.xqArr = xqArr;
	}

	public String[] getYqArr() {
		return yqArr;
	}

	public void setYqArr(String[] yqArr) {
		this.yqArr = yqArr;
	}

	public String getCws() {
		return cws;
	}

	public void setCws(String cws) {
		this.cws = cws;
	}

	public String getFpbj() {
		return fpbj;
	}

	public void setFpbj(String fpbj) {
		this.fpbj = fpbj;
	}

	public String getKfhz() {
		return kfhz;
	}

	public void setKfhz(String kfhz) {
		this.kfhz = kfhz;
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

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getBhgz() {
		return bhgz;
	}

	public void setBhgz(String bhgz) {
		this.bhgz = bhgz;
	}

	public String getBls() {
		return bls;
	}

	public void setBls(String bls) {
		this.bls = bls;
	}

	public String[] getCwsArr() {
		return cwsArr;
	}

	public void setCwsArr(String[] cwsArr) {
		this.cwsArr = cwsArr;
	}

	public String[] getFpbjArr() {
		return fpbjArr;
	}

	public void setFpbjArr(String[] fpbjArr) {
		this.fpbjArr = fpbjArr;
	}

	public String[] getQsslArr() {
		return qsslArr;
	}

	public void setQsslArr(String[] qsslArr) {
		this.qsslArr = qsslArr;
	}

	public String[] getSfbzArr() {
		return sfbzArr;
	}

	public void setSfbzArr(String[] sfbzArr) {
		this.sfbzArr = sfbzArr;
	}

	public String[] getCsArr() {
		return csArr;
	}

	public void setCsArr(String[] csArr) {
		this.csArr = csArr;
	}

	public String[] getKfhzArr() {
		return kfhzArr;
	}

	public void setKfhzArr(String[] kfhzArr) {
		this.kfhzArr = kfhzArr;
	}

	public String[] getXbArr() {
		return xbArr;
	}

	public void setXbArr(String[] xbArr) {
		this.xbArr = xbArr;
	}
	
	public String getQss() {
		return qss;
	}

	public void setQss(String qss) {
		this.qss = qss;
	}

	public String getQsbz() {
		return qsbz;
	}

	public void setQsbz(String qsbz) {
		this.qsbz = qsbz;
	}

	public String getCzxq() {
		return czxq;
	}

	public void setCzxq(String czxq) {
		this.czxq = czxq;
	}

	public String getCzyq() {
		return czyq;
	}

	public void setCzyq(String czyq) {
		this.czyq = czyq;
	}

	public String[] getCwhArr() {
		return cwhArr;
	}

	public void setCwhArr(String[] cwhArr) {
		this.cwhArr = cwhArr;
	}

	public String[] getCwbjArr() {
		return cwbjArr;
	}

	public void setCwbjArr(String[] cwbjArr) {
		this.cwbjArr = cwbjArr;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String[] getRzqk() {
		return rzqk;
	}

	public void setRzqk(String[] rzqk) {
		this.rzqk = rzqk;
	}

	public String getFpdx() {
		return fpdx;
	}

	public void setFpdx(String fpdx) {
		this.fpdx = fpdx;
	}

	public String[] getRzqkHidArr() {
		return rzqkHidArr;
	}

	public void setRzqkHidArr(String[] rzqkHidArr) {
		this.rzqkHidArr = rzqkHidArr;
	}

	public String[] getXbxdHidArr() {
		return xbxdHidArr;
	}

	public void setXbxdHidArr(String[] xbxdHidArr) {
		this.xbxdHidArr = xbxdHidArr;
	}

	public String getLdHid() {
		return ldHid;
	}

	public void setLdHid(String ldHid) {
		this.ldHid = ldHid;
	}

	public String getFpbm() {
		return fpbm;
	}

	public void setFpbm(String fpbm) {
		this.fpbm = fpbm;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
