package xgxt.xsgygl.zgdzdx;

import org.apache.struts.action.ActionForm;

import xgxt.base.DealString;
/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �й����ʴ�ѧ���人����Ԣ����ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-06</p>
 */
public class GyglZgdzdxForm extends ActionForm {

	private static final long serialVersionUID = 9118963723091084951L;
	private String xiaoqu; //У��
	private String lddm;//¥������
	private String cs;//����	
	private String cfjs;//ÿ�㷿����
	private String jcws;//ÿ�䴲λ��
	private String sfbz;//ÿ���շѱ�׼
	private String xh;//ѧ��
	private String xm;
	private String xb;
	private String xymc;
	private String zymc;
	private String bjmc;
	private String fpbz;//�����־(���俪�Ŷ�������)
	private String chsfbl;//����Ƿ���
	private String fjhws;//�����λ��
	private String qshbpgz;//���Һű��Ź���
	private String ssbh;//������
	private String qsh;//���Һ�(�����)
	private String xbxd;//¥���Ա��޶�
	private String nj;//�꼶
	private String xydm;//ѧԺ����
	private String zydm;//רҵ����
	private String bjdm;//�༶����
	private String oracleItem;//
	private String sql;//
	private String sfbz1;//ÿ���շѱ�׼����ѯ�ã�
	private String sfbz2;//ÿ���շѱ�׼����ѯ�ã�
	private String oldCondiSqlValue;
	private String conditionSqlValue;
	private String xqdm;//У������
	private String tb;//ͼ��
	private String qsdh;//���ҵ绰
	private String sffqfj;
	private String bz;//��ע
	private String ksh;//�����ţ�ѧ�ţ�
	private String yqdm;//԰������
	private String sfzz;//�Ƿ���ְ
    private String lxdh;//��ϵ�绰
    private String rzrq;//��ְ����
    private String lzrq;//��ְ����
    private String dzyx;//��������
    private String lz;//¥��
    private String cz;//�㳤
    private String rq;//����
	private String hdnr;//�����
	private String hdmc;//�����
	private String zzdw;//��֯��λ
	private String sj;//ʱ��
	private String cjry;//�μ���Ա
	private String xn;//ѧ��
	private String xq;//ѧ��
	private String cwh;//��λ��
	private String zbdw;//���쵥λ
	private String fzrxm;//����������
	private String fzrlxfs;//��������ϵ��ʽ��
	private String jsrxm;//����������
	private String jsrlxfs;//��������ϵ��ʽ
	private String hddd;//��ص�
	private String hdksrq;//���ʼ���ڣ��գ�
	private String hdjsrq;//��������ڣ��գ�
	private String hdkssj;//���ʼʱ�䣨ʱ��
	private String hdjssj;//�����ʱ�䣨ʱ��
	private String cjrs;//�μ�����
	private String sqrdlm;//�����˵�½��
	private String sqrxm;//����������
	private String yesNo;//��˱�־
	private String nd;//���
	private String imgNj = "";
	private String imgXy = "";
	private String lc;//¥��
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	public String getImgNj() {
		return imgNj;
	}
	public void setImgNj(String imgNj) {
		this.imgNj = imgNj;
	}
	public String getImgXy() {
		return imgXy;
	}
	public void setImgXy(String imgXy) {
		this.imgXy = imgXy;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = DealString.toGBK(nd);
	}
	public String getCjrs() {
		return cjrs;
	}
	public void setCjrs(String cjrs) {
		this.cjrs = DealString.toGBK(cjrs);
	}
	public String getFzrlxfs() {
		return fzrlxfs;
	}
	public void setFzrlxfs(String fzrlxfs) {
		this.fzrlxfs = DealString.toGBK(fzrlxfs);
	}
	public String getFzrxm() {
		return fzrxm;
	}
	public void setFzrxm(String fzrxm) {
		this.fzrxm = DealString.toGBK(fzrxm);
	}
	public String getHddd() {
		return hddd;
	}
	public void setHddd(String hddd) {
		this.hddd = DealString.toGBK(hddd);
	}
	public String getHdjsrq() {
		return hdjsrq;
	}
	public void setHdjsrq(String hdjsrq) {
		this.hdjsrq = DealString.toGBK(hdjsrq);
	}
	public String getHdksrq() {
		return hdksrq;
	}
	public void setHdksrq(String hdksrq) {
		this.hdksrq = DealString.toGBK(hdksrq);
	}
	public String getJsrlxfs() {
		return jsrlxfs;
	}
	public void setJsrlxfs(String jsrlxfs) {
		this.jsrlxfs = DealString.toGBK(jsrlxfs);
	}
	public String getJsrxm() {
		return jsrxm;
	}
	public void setJsrxm(String jsrxm) {
		this.jsrxm = DealString.toGBK(jsrxm);
	}
	public String getSqrdlm() {
		return sqrdlm;
	}
	public void setSqrdlm(String sqrdlm) {
		this.sqrdlm = DealString.toGBK(sqrdlm);
	}
	public String getSqrxm() {
		return sqrxm;
	}
	public void setSqrxm(String sqrxm) {
		this.sqrxm = DealString.toGBK(sqrxm);
	}
	public String getYesNo() {
		return yesNo;
	}
	public void setYesNo(String yesNo) {
		this.yesNo = DealString.toGBK(yesNo);
	}
	public String getZbdw() {
		return zbdw;
	}
	public void setZbdw(String zbdw) {
		this.zbdw = DealString.toGBK(zbdw);
	}
	public String getCwh() {
		return cwh;
	}
	public void setCwh(String cwh) {
		this.cwh = DealString.toGBK(cwh);
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = DealString.toGBK(xn);
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = DealString.toGBK(xq);
	}
	public String getCjry() {
		return cjry;
	}
	public void setCjry(String cjry) {
		this.cjry = DealString.toGBK(cjry);
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = DealString.toGBK(sj);
	}
	public String getHdmc() {
		return hdmc;
	}
	public void setHdmc(String hdmc) {
		this.hdmc = DealString.toGBK(hdmc);
	}
	public String getHdnr() {
		return hdnr;
	}
	public void setHdnr(String hdnr) {
		this.hdnr = DealString.toGBK(hdnr);
	}
	public String getZzdw() {
		return zzdw;
	}
	public void setZzdw(String zzdw) {
		this.zzdw = DealString.toGBK(zzdw);
	}
	public String getRq() {
		return rq;
	}
	public void setRq(String rq) {
		this.rq = DealString.toGBK(rq);
	}
	public String getCz() {
		return cz;
	}
	public void setCz(String cz) {
		this.cz = DealString.toGBK(cz);
	}
	public String getLz() {
		return lz;
	}
	public void setLz(String lz) {
		this.lz = DealString.toGBK(lz);
	}
	public String getSfzz() {
		return sfzz;
	}
	public void setSfzz(String sfzz) {
		this.sfzz = DealString.toGBK(sfzz);
	}
	public String getYqdm() {
		return yqdm;
	}
	public void setYqdm(String yqdm) {
		this.yqdm = DealString.toGBK(yqdm);
	}
	public String getKsh() {
		return ksh;
	}
	public void setKsh(String ksh) {
		this.ksh = DealString.toGBK(ksh);
	}
	public String getOldCondiSqlValue() {
		return oldCondiSqlValue;
	}
	public void setOldCondiSqlValue(String oldCondiSqlValue) {
		this.oldCondiSqlValue = DealString.toGBK(oldCondiSqlValue);
	}
	public String getOracleItem() {
		return oracleItem;
	}
	public void setOracleItem(String oracleItem) {
		this.oracleItem = DealString.toGBK(oracleItem);
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = DealString.toGBK(sql);
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = DealString.toGBK(nj);
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = DealString.toGBK(xydm);
	}
	public String getXbxd() {
		return xbxd;
	}
	public void setXbxd(String xbxd) {
		this.xbxd = DealString.toGBK(xbxd);
	}
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = DealString.toGBK(ssbh);
	}
	public String getJcws() {
		return jcws;
	}
	public void setJcws(String jcws) {
		this.jcws = DealString.toGBK(jcws);
	}
	public String getCfjs() {
		return cfjs;
	}
	public void setCfjs(String cfjs) {
		this.cfjs = DealString.toGBK(cfjs);
	}
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = DealString.toGBK(cs);
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = DealString.toGBK(lddm);
	}
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = DealString.toGBK(xiaoqu);
	}
	public String getFpbz() {
		return fpbz;
	}
	public void setFpbz(String fpbz) {
		this.fpbz = DealString.toGBK(fpbz);
	}
	public String getSfbz() {
		return sfbz;
	}
	public void setSfbz(String sfbz) {
		this.sfbz = DealString.toGBK(sfbz);
	}
	public String getChsfbl() {
		return chsfbl;
	}
	public void setChsfbl(String chsfbl) {
		this.chsfbl = DealString.toGBK(chsfbl);
	}
	public String getFjhws() {
		return fjhws;
	}
	public void setFjhws(String fjhws) {
		this.fjhws = DealString.toGBK(fjhws);
	}
	public String getQshbpgz() {
		return qshbpgz;
	}
	public void setQshbpgz(String qshbpgz) {
		this.qshbpgz = qshbpgz;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = DealString.toGBK(qsh);
	}
    public String getSfbz1() {
		return sfbz1;
	}
	public void setSfbz1(String sfbz1) {
		this.sfbz1 = DealString.toGBK(sfbz1);
	}
	public String getSfbz2() {
		return sfbz2;
	}
	public void setSfbz2(String sfbz2) {
		this.sfbz2 = DealString.toGBK(sfbz2);
	}
	public String getConditionSqlValue() {
		return conditionSqlValue;
	}
	public void setConditionSqlValue(String conditionSqlValue) {
		this.conditionSqlValue = conditionSqlValue;
	}
	public String getXqdm() {
		return xqdm;
	}
	public void setXqdm(String xqdm) {
		this.xqdm = DealString.toGBK(xqdm);
	}
	public String getTb() {
		return tb;
	}
	public void setTb(String tb) {
		this.tb = DealString.toGBK(tb);
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = DealString.toGBK(bz);
	}
	public String getQsdh() {
		return qsdh;
	}
	public void setQsdh(String qsdh) {
		this.qsdh = DealString.toGBK(qsdh);
	}
	public String getSffqfj() {
		return sffqfj;
	}
	public void setSffqfj(String sffqfj) {
		this.sffqfj = DealString.toGBK(sffqfj);
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = DealString.toGBK(bjdm);
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = DealString.toGBK(zydm);
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = DealString.toGBK(xh);
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = DealString.toGBK(xm);
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = DealString.toGBK(dzyx);
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = DealString.toGBK(lxdh);
	}
	public String getLzrq() {
		return lzrq;
	}
	public void setLzrq(String lzrq) {
		this.lzrq = DealString.toGBK(lzrq);
	}
	public String getRzrq() {
		return rzrq;
	}
	public void setRzrq(String rzrq) {
		this.rzrq = DealString.toGBK(rzrq);
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = DealString.toGBK(bjmc);
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = DealString.toGBK(xb);
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = DealString.toGBK(xymc);
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = DealString.toGBK(zymc);
	}
	public String getHdjssj() {
		return hdjssj;
	}
	public void setHdjssj(String hdjssj) {
		this.hdjssj = DealString.toGBK(hdjssj);
	}
	public String getHdkssj() {
		return hdkssj;
	}
	public void setHdkssj(String hdkssj) {
		this.hdkssj = DealString.toGBK(hdkssj);
	}

}
