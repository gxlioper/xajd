package xsgzgl.gygl.wsjc.gzdx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.xml.XMLReader;
import xgxt.utils.Pages;
import xsgzgl.gygl.comm.GyglNewForm;

public class GyglTyForm extends GyglNewForm {

	private static final long serialVersionUID = -9205711105806100577L;

	/* ͨ�� */
	Pages pages = new Pages();

	FormFile uploadFile;// �ϴ��ļ�

	private String gdtj;// ��������

	private String pkValue;// ����ֵ

	private String[] checkVal;// ������

	private String[] primarykey_checkVal;

	private String tableName;// ������

	private String xysh;// ѧԺ���

	private String xxsh;// ѧУ���

	private String userName;// �û���

	private String shzt;// ���״̬

	private String queryequals_shzt;

	private String shsj;// ���ʱ��

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

	private String xq;// ѧ��

	private String queryequals_xq;

	private String nd;// ���

	private String queryequals_nd;

	private String xydm;// ѧԺ����

	private String queryequals_xydm;

	private String xymc;// ѧԺ����

	private String zydm;// רҵ����

	private String queryequals_zydm;

	private String zymc;// רҵ����

	private String bjdm;// �༶����

	private String queryequals_bjdm;

	private String bjmc;// �༶����

	private String bz;// ��ע

	private String lx;// ����

	private String id;// ID

	private String xqdm;// 'У������';

	private String queryequals_xqdm;

	private String lddm;// ¥������

	private String queryequals_lddm;

	private String cs;// ����

	private String queryequals_cs;

	private String qsh;// ���Һ�

	private String queryequals_qsh;

	private String bmdm;// ���Ŵ���

	private String fsxx;// '��������';

	private String fssx;// '��������';

	private String dj;// '�ȼ�';

	private String zzmm;// '������ò';

	// ===================�������===============================

	private static String edition;// ��Ԣ�汾

	private String[] fs;// ����

	private String jcsj;// ���ʱ��

	private String lrr;// ¼����

	private String lrrlx;// ¼��������

	private String lrsj;// ¼��ʱ��

	private String kssj;// ��ʼʱ��

	private String jssj;// ����ʱ��

	private String bblx;// ��������

	private String needNj;// �Ƿ���Ҫ�꼶

	private String jczq;// '�������';

	private String zgzc;// '�ܹ��ܴ�';

	private String qsrq;// '��ʼ����';

	private String lrxs;// '¼����ʽ';

	private String gldj;// '�����ȼ�';

	private String glfs;// '��������';

	private String jcf;// '������';

	private String[] wsfxx;// '����������';

	private String[] wsfsx;// '����������';

	private String[] wsfdj;// '�����ֵȼ�';

	private String[] djpx;// '�ȼ�����';

	private String zc;// '�ܴ�';

	private String sfdf;// '�Ƿ���';

	private String jcbm;// '��鲿��';

	private String jczc;// '����ܴ�';

	private String kszc;// '��ʼ�ܴ�';

	private String jszc;// '�����ܴ�';

	private String[] jcld;// '���¥��';

	private String[] jccs;// '������';

	private String[] jcqs;// '�������';

	private String[] wsffs;// '������';

	private String[] arr_jcbm;// '��鲿��';

	private String[] jcbz;// '��鱸ע';

	private String pmqk;// '�����ܴ�';

	private String[] xmdm; // ѧ���۷־�����Ŀ����

	// ===================������� end===========================

	// ===================��Ԣ����===============================

	private String bxlxdh;// '��ϵ�绰';

	private String tjfs;// 'ͳ�Ʒ�ʽ';

	private String tjfw;// 'ͳ�Ʒ�Χ';

	private String bxsj;// '����ʱ��';

	private String querygreaterequal_bxsj;

	private String querylessequal_bxsj;

	private String xwsj;// 'ϣ��ʱ��';

	private String xwkssj;// 'ϣ��ʱ��(��ʼ)';

	private String xwjssj;// 'ϣ��ʱ��(����)';

	private String bxnr;// '��������';

	private String sfsf;// '�Ƿ��շ�';

	private String queryequals_sfsf;

	private String wxry;// 'ά����Ա';

	private String wxfy;// 'ά�޷���';

	private String querygreaterequal_wxfy;

	private String querylessequal_wxfy;

	private String sfwg;// '�Ƿ��깤';

	private String queryequals_sfwg;

	private String wgsj;// '�ύʱ��';

	private String wgsjh;// '�ύʱ��(ʱ)';

	private String wgsjm;// '�ύʱ��(��)';

	private String wgsjs;// '�ύʱ��(��)';

	private String cljg;// '������';

	private String fwtd;// '����̬��';

	private String fwzl;// '��������';

	private String sfjs;// '�Ƿ�ʱ';

	private String xcql;// '�ֳ�����';

	private String yhjy;// '�û�����';

	private String tjcllx;// '�������ͣ�ͳ�ƣ�';

	private String tjclmc;// '�������ƣ�ͳ�ƣ�';

	private String[] cllx;// '��������';

	private String[] clmc;// '��������';

	private String[] clsl;// '��������';

	private String[] cldj;// '���ϵ���';

	// ===================��Ԣ���� end===============================
	
	private String stjxn; // ͳ��ѧ��

	private String stjxq; // ͳ��ѧ��

	private String tjxn;

	private String tjxq;
	
	private String jcry;//�����Ա

	static {
		edition = XMLReader.getFlowControl("gygl", "edition");
	}
	
	public String getTjxn() {
		return tjxn;
	}

	public void setTjxn(String tjxn) {
		this.tjxn = tjxn;
	}

	public String getTjxq() {
		return tjxq;
	}

	public void setTjxq(String tjxq) {
		this.tjxq = tjxq;
	}

	public String getStjxn() {
		return stjxn;
	}

	public void setStjxn(String stjxn) {
		this.stjxn = stjxn;
	}

	public String getStjxq() {
		return stjxq;
	}

	public void setStjxq(String stjxq) {
		this.stjxq = stjxq;
	}

	public String getNeedNj() {
		return needNj;
	}

	public void setNeedNj(String needNj) {
		this.needNj = needNj;
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

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String[] getFs() {
		return fs;
	}

	public void setFs(String[] fs) {
		this.fs = fs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getJcqs() {
		return jcqs;
	}

	public void setJcqs(String[] jcqs) {
		this.jcqs = jcqs;
	}

	public String getJcsj() {
		return jcsj;
	}

	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
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

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
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

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
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

	public String getXqdm() {
		return xqdm;
	}

	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
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

	public String getQueryequals_cs() {
		return queryequals_cs;
	}

	public void setQueryequals_cs(String queryequals_cs) {
		this.queryequals_cs = queryequals_cs;
	}

	public String getQueryequals_lddm() {
		return queryequals_lddm;
	}

	public void setQueryequals_lddm(String queryequals_lddm) {
		this.queryequals_lddm = queryequals_lddm;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}

	public String getQueryequals_qsh() {
		return queryequals_qsh;
	}

	public void setQueryequals_qsh(String queryequals_qsh) {
		this.queryequals_qsh = queryequals_qsh;
	}

	public String getQueryequals_xqdm() {
		return queryequals_xqdm;
	}

	public void setQueryequals_xqdm(String queryequals_xqdm) {
		this.queryequals_xqdm = queryequals_xqdm;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}

	public String getBxnr() {
		return bxnr;
	}

	public void setBxnr(String bxnr) {
		this.bxnr = bxnr;
	}

	public String getBxsj() {
		return bxsj;
	}

	public void setBxsj(String bxsj) {
		this.bxsj = bxsj;
	}

	public String getCljg() {
		return cljg;
	}

	public void setCljg(String cljg) {
		this.cljg = cljg;
	}

	public String getFwtd() {
		return fwtd;
	}

	public void setFwtd(String fwtd) {
		this.fwtd = fwtd;
	}

	public String getFwzl() {
		return fwzl;
	}

	public void setFwzl(String fwzl) {
		this.fwzl = fwzl;
	}

	public String getSfjs() {
		return sfjs;
	}

	public void setSfjs(String sfjs) {
		this.sfjs = sfjs;
	}

	public String getSfsf() {
		return sfsf;
	}

	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
	}

	public String getSfwg() {
		return sfwg;
	}

	public void setSfwg(String sfwg) {
		this.sfwg = sfwg;
	}

	public String getWgsj() {
		return wgsj;
	}

	public void setWgsj(String wgsj) {
		this.wgsj = wgsj;
	}

	public String getWxfy() {
		return wxfy;
	}

	public void setWxfy(String wxfy) {
		this.wxfy = wxfy;
	}

	public String getWxry() {
		return wxry;
	}

	public void setWxry(String wxry) {
		this.wxry = wxry;
	}

	public String getXcql() {
		return xcql;
	}

	public void setXcql(String xcql) {
		this.xcql = xcql;
	}

	public String getXwsj() {
		return xwsj;
	}

	public void setXwsj(String xwsj) {
		this.xwsj = xwsj;
	}

	public String getYhjy() {
		return yhjy;
	}

	public void setYhjy(String yhjy) {
		this.yhjy = yhjy;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_nd() {
		return queryequals_nd;
	}

	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
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

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getCldj() {
		return cldj;
	}

	public void setCldj(String[] cldj) {
		this.cldj = cldj;
	}

	public String[] getCllx() {
		return cllx;
	}

	public void setCllx(String[] cllx) {
		this.cllx = cllx;
	}

	public String[] getClmc() {
		return clmc;
	}

	public void setClmc(String[] clmc) {
		this.clmc = clmc;
	}

	public String[] getClsl() {
		return clsl;
	}

	public void setClsl(String[] clsl) {
		this.clsl = clsl;
	}

	public String getQueryequals_sfsf() {
		return queryequals_sfsf;
	}

	public void setQueryequals_sfsf(String queryequals_sfsf) {
		this.queryequals_sfsf = queryequals_sfsf;
	}

	public String getQueryequals_sfwg() {
		return queryequals_sfwg;
	}

	public void setQueryequals_sfwg(String queryequals_sfwg) {
		this.queryequals_sfwg = queryequals_sfwg;
	}

	public String getQuerygreaterequal_bxsj() {
		return querygreaterequal_bxsj;
	}

	public void setQuerygreaterequal_bxsj(String querygreaterequal_bxsj) {
		this.querygreaterequal_bxsj = querygreaterequal_bxsj;
	}

	public String getQuerylessequal_bxsj() {
		return querylessequal_bxsj;
	}

	public void setQuerylessequal_bxsj(String querylessequal_bxsj) {
		this.querylessequal_bxsj = querylessequal_bxsj;
	}

	public String getTjfs() {
		return tjfs;
	}

	public void setTjfs(String tjfs) {
		this.tjfs = tjfs;
	}

	public String getTjfw() {
		return tjfw;
	}

	public void setTjfw(String tjfw) {
		this.tjfw = tjfw;
	}

	public String getTjcllx() {
		return tjcllx;
	}

	public void setTjcllx(String tjcllx) {
		this.tjcllx = tjcllx;
	}

	public String getTjclmc() {
		return tjclmc;
	}

	public void setTjclmc(String tjclmc) {
		this.tjclmc = tjclmc;
	}

	public String getQueryequals_shzt() {
		return queryequals_shzt;
	}

	public void setQueryequals_shzt(String queryequals_shzt) {
		this.queryequals_shzt = queryequals_shzt;
	}

	public String getXwjssj() {
		return xwjssj;
	}

	public void setXwjssj(String xwjssj) {
		this.xwjssj = xwjssj;
	}

	public String getXwkssj() {
		return xwkssj;
	}

	public void setXwkssj(String xwkssj) {
		this.xwkssj = xwkssj;
	}

	public String getBxlxdh() {
		return bxlxdh;
	}

	public void setBxlxdh(String bxlxdh) {
		this.bxlxdh = bxlxdh;
	}

	public String getWgsjm() {
		return wgsjm;
	}

	public void setWgsjm(String wgsjm) {
		this.wgsjm = wgsjm;
	}

	public String getWgsjs() {
		return wgsjs;
	}

	public void setWgsjs(String wgsjs) {
		this.wgsjs = wgsjs;
	}

	public String getWgsjh() {
		return wgsjh;
	}

	public void setWgsjh(String wgsjh) {
		this.wgsjh = wgsjh;
	}

	public String getQuerygreaterequal_wxfy() {
		return querygreaterequal_wxfy;
	}

	public void setQuerygreaterequal_wxfy(String querygreaterequal_wxfy) {
		this.querygreaterequal_wxfy = querygreaterequal_wxfy;
	}

	public String getQuerylessequal_wxfy() {
		return querylessequal_wxfy;
	}

	public void setQuerylessequal_wxfy(String querylessequal_wxfy) {
		this.querylessequal_wxfy = querylessequal_wxfy;
	}

	public String getGldj() {
		return gldj;
	}

	public void setGldj(String gldj) {
		this.gldj = gldj;
	}

	public String getGlfs() {
		return glfs;
	}

	public void setGlfs(String glfs) {
		this.glfs = glfs;
	}

	public String getJcf() {
		return jcf;
	}

	public void setJcf(String jcf) {
		this.jcf = jcf;
	}

	public String getJczq() {
		return jczq;
	}

	public void setJczq(String jczq) {
		this.jczq = jczq;
	}

	public String getLrxs() {
		return lrxs;
	}

	public void setLrxs(String lrxs) {
		this.lrxs = lrxs;
	}

	public String getQsrq() {
		return qsrq;
	}

	public void setQsrq(String qsrq) {
		this.qsrq = qsrq;
	}

	public String getZgzc() {
		return zgzc;
	}

	public void setZgzc(String zgzc) {
		this.zgzc = zgzc;
	}

	public String[] getWsfdj() {
		return wsfdj;
	}

	public void setWsfdj(String[] wsfdj) {
		this.wsfdj = wsfdj;
	}

	public String[] getWsfsx() {
		return wsfsx;
	}

	public void setWsfsx(String[] wsfsx) {
		this.wsfsx = wsfsx;
	}

	public String[] getWsfxx() {
		return wsfxx;
	}

	public void setWsfxx(String[] wsfxx) {
		this.wsfxx = wsfxx;
	}

	public String[] getDjpx() {
		return djpx;
	}

	public void setDjpx(String[] djpx) {
		this.djpx = djpx;
	}

	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	public String[] getJccs() {
		return jccs;
	}

	public void setJccs(String[] jccs) {
		this.jccs = jccs;
	}

	public String[] getJcld() {
		return jcld;
	}

	public void setJcld(String[] jcld) {
		this.jcld = jcld;
	}

	public String getJczc() {
		return jczc;
	}

	public void setJczc(String jczc) {
		this.jczc = jczc;
	}

	public String[] getWsffs() {
		return wsffs;
	}

	public void setWsffs(String[] wsffs) {
		this.wsffs = wsffs;
	}

	public String getJcbm() {
		return jcbm;
	}

	public void setJcbm(String jcbm) {
		this.jcbm = jcbm;
	}

	public String getSfdf() {
		return sfdf;
	}

	public void setSfdf(String sfdf) {
		this.sfdf = sfdf;
	}

	public String getLrrlx() {
		return lrrlx;
	}

	public void setLrrlx(String lrrlx) {
		this.lrrlx = lrrlx;
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getJszc() {
		return jszc;
	}

	public void setJszc(String jszc) {
		this.jszc = jszc;
	}

	public String getKszc() {
		return kszc;
	}

	public void setKszc(String kszc) {
		this.kszc = kszc;
	}

	public String getFssx() {
		return fssx;
	}

	public void setFssx(String fssx) {
		this.fssx = fssx;
	}

	public String getFsxx() {
		return fsxx;
	}

	public void setFsxx(String fsxx) {
		this.fsxx = fsxx;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String[] getArr_jcbm() {
		return arr_jcbm;
	}

	public void setArr_jcbm(String[] arr_jcbm) {
		this.arr_jcbm = arr_jcbm;
	}

	public String[] getJcbz() {
		return jcbz;
	}

	public void setJcbz(String[] jcbz) {
		this.jcbz = jcbz;
	}

	public String getPmqk() {
		return pmqk;
	}

	public void setPmqk(String pmqk) {
		this.pmqk = pmqk;
	}

	public String getGdtj() {
		return gdtj;
	}

	public void setGdtj(String gdtj) {
		this.gdtj = gdtj;
	}

	public String[] getXmdm() {
		return xmdm;
	}

	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getJcry() {
		return jcry;
	}

	public void setJcry(String jcry) {
		this.jcry = jcry;
	}
	
	
}
