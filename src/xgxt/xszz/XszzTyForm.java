package xgxt.xszz;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.utils.Pages;

public class XszzTyForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* ͨ�� */
	Pages pages = new Pages();

	FormFile uploadFile;// �ϴ��ļ�

	private String save_bzrshsj;
	
	private String save_fdyshsj;
	
	private String iskns;//�Ƿ��������϶���ƶ�����϶���Ŀ���������
	
	private boolean flg;//�ֶ����ú�Ҫ�����ڴ��е���Ŀ�ֶΣ�by �����
	
	private String pjcj;

	private String pjcjtj;
	
	private String jqpjf;
	
	private String jqpjftj;
	
	private String jqpjjd;
	
	private String jqpjjdtj;
	
	private String[] bkjdxm;

	private String queryequals_isxb;

	private String save_xq;

	private String save_nd;

	private String isxb;// �Ƿ��°�

	private String save_bjgkms;// �������Ŀ��

	private String mklx;// ģ������

	private String[] checkVal;// ������

	private String[] primarykey_checkVal;

	private String pkValue;// ����ֵ

	private String xysh;// ѧԺ���

	private String queryequals_xysh;

	private String xxsh;// ѧУ���

	private String szbm;// ���ڲ���

	private String queryequals_xxsh;

	private String userName;// �û���

	private String userDep;// ���ڲ���

	private String zgh;// ְ����

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

	private String queryequals_lx;
	
	private String shsj1;
	
	private String shsj2;
	
	private String shsj3;

	private String id;// ID

	private String sqly;// '��������';

	private String xyshsj;// 'ѧԺ���ʱ��';

	private String xyshyj;// 'ѧԺ������';

	private String xxshsj;// 'ѧУ���ʱ��';

	private String xxshyj;// 'ѧУ������';

	private String bjsh;// '�༶���';

	private String queryequals_bjsh;

	private String bjshyj;// '�༶������';

	private String bjshsj;// '�༶���ʱ��';

	private String save_xh;// 'ѧ��';

	private String save_xn;// 'ѧ��';

	private String save_xysh;// 'ѧԺ���';

	private String save_xyshsj;// 'ѧԺ���ʱ��';

	private String save_xyshyj;// 'ѧԺ������';

	private String save_xxsh;// 'ѧУ���';

	private String save_xxshsj;// 'ѧУ���ʱ��';

	private String save_xxshyj;// 'ѧУ������';

	private String save_bjsh;// '�༶���';

	private String save_bjshyj;// '�༶������';

	private String save_bjshsj;// '�༶���ʱ��';

	private String save_bz;// '��ע';

	private String shzd;// ����ֶ�

	private String bl;// ����

	private String kssj;// ��ʼʱ��

	private String jssj;// ����ʱ��

	// ======================���ݴ�ѧ========================================
	/* ��ʳ���� */
	private String[] zyxmList;// רҵ��Ŀ

	private String[] bzzy;// ����רҵ

	private String[] all_xh;// ȫ��ѧ��

	private String xmdm;// '��Ŀ����';

	private String ffje;// '�������';

	private String bzlx;// '��������';

	private String queryequals_bzlx;

	private String yf;// '�·�';

	private String queryequals_yf;

	private String querygreaterequal_yf;

	private String querylessequal_yf;

	// ======================���ϴ�ѧ========================================
	/* �������� */
	private String[] szxy;// 'ѧԺ����';

	private String kg;// '����';

	private String xmmc;// '��Ŀ����';

	private String[] xmjb;// '��Ŀ����';

	private String[] xmbl;// '��Ŀ����';

	private String[] xmrs;// '��Ŀ����';

	/* �����������϶� */
	private String knsjb;// '����������';

	private String queryequals_knsjb;

	private String jtrs;// '��ͥ����';

	private String jtnzsr;// '��ͥ��������';

	private String jtsrly;// '��ͥ������Ҫ��Դ';

	private String jtyzsr;// '��ͥ��������';

	private String jtdh;// '��ͥ�绰';

	private String jtyb;// '��ͥ�ʱ�';

	private String jtrjsr;// '��ͥ�˾�������';

	private String jtfzqk;// '��ͥ��ծ���';

	private String jtdd;// '��ͥסַ';

	private String jtdz;// '��ͥ��ַ';

	private String sfge;// '�Ƿ�¶�';

	private String sfcj;// '�Ƿ�м�';

	private String sfdb;// '�Ƿ�ͱ���';

	private String sflszn;// '�Ƿ���ʿ��Ů';

	private String sfzrch;// '�Ƿ�������Ȼ�ֺ�';

	private String sfjthb;// '��ͥ��Ա�Ƿ��ڻ��ز�';

	private String sfpkzm;// '�Ƿ���ƶ��֤��';

	private String jtdxrs;// '���ж���ѧ����';

	private String dydj;// '���������ȼ�';

	private String xsjtjjqk;// '��ͥ�������';

	private String brjdqzmbqk;// '���˻����';

	private String qtqk;// '�������';

	private String sy;// '��Դ';

	private String scdz;// 'ƶ��֤���ϴ���ַ';

	private String sfncpkdq;// '�Ƿ�ũ��ƶ������';

	private String sfczxgjt;// '�Ƿ�����¸ڼ�ͥ';

	private String sffmxcj;// '�Ƿ�ĸ�²м�';

	private String sfhzdjb;// '�Ƿ��ش󼲲�';

	private String sfdqjt;// '�Ƿ��׼�ͥ';

	private String sfgr;// '�Ƿ�¶�';

	private String sfzrzh;// '�Ƿ���Ȼ�ֺ�';

	private String sfjtrkd;// '�Ƿ��ͥ�˿ڶ�';

	private String sfqt;// '�Ƿ�����';

	private String qtnr;// '��������';

	private String pkyyxxsm;// 'ƶ��ԭ����ϸ˵��';
	
	private String mzbm_xxtxdz;//'����������ϸͨѶ��ַ';

	private String mzbm_yzbm;//'����������������';

	private String mzbm_lxdh;//'����������ϵ�绰';
	
	private String save_jtrs;// '��ͥ����';

	private String save_jtyb;// '��ͥ�ʱ�';

	private String save_jtnzsr;// '��ͥ��������';

	private String save_jtsrly;// '��ͥ������Ҫ��Դ';

	private String save_jtyzsr;// '��ͥ��������';

	private String save_jtdh;// '��ͥ�绰';

	private String save_jtrjsr;// '��ͥ�˾�������';

	private String save_jtfzqk;// '��ͥ��ծ���';

	private String save_jtdd;// '��ͥסַ';

	private String save_jtdz;// '��ͥ��ַ';

	private String save_sfge;// '�Ƿ�¶�';

	private String save_sfcj;// '�Ƿ�м�';

	private String save_sfdb;// '�Ƿ�ͱ���';

	private String save_sflszn;// '�Ƿ���ʿ��Ů';

	private String save_sfzrch;// '�Ƿ�������Ȼ�ֺ�';

	private String save_sfjthb;// '��ͥ��Ա�Ƿ��ڻ��ز�';

	private String save_sfpkzm;// '�Ƿ���ƶ��֤��';

	private String save_jtdxrs;// '���ж���ѧ����';

	private String save_dydj;// '���������ȼ�';

	private String save_xsjtjjqk;// '��ͥ�������';

	private String save_brjdqzmbqk;// '���˻����';

	private String save_qtqk;// '�������';

	private String save_scdz;// 'ƶ��֤���ϴ���ַ';

	private String save_sy;// '��Դ';

	private String save_sfncpkdq;// '�Ƿ�ũ��ƶ������';

	private String save_sfczxgjt;// '�Ƿ�����¸ڼ�ͥ';

	private String save_sffmxcj;// '�Ƿ�ĸ�²м�';

	private String save_sfhzdjb;// '�Ƿ��ش󼲲�';

	private String save_sfdqjt;// '�Ƿ��׼�ͥ';

	private String save_sfgr;// '�Ƿ�¶�';

	private String save_sfzrzh;// '�Ƿ���Ȼ�ֺ�';

	private String save_sfjtrkd;// '�Ƿ��ͥ�˿ڶ�';

	private String save_sfqt;// '�Ƿ�����';

	private String save_qtnr;// '��������';

	private String save_pkyyxxsm;// 'ƶ��ԭ����ϸ˵��';
	
	private String save_xxtxdz;//'����������ϸͨѶ��ַ';

	private String save_yzbm;//'����������������';

	private String save_lxdh;//'����������ϵ�绰';
	
	// =====================�Ͼ���ʦ=================================
	private String xfsfxm;// '�շ���Ŀ';

	private String querylike_xfsfxm;

	private String xfyjje;// 'Ӧ�ɽ��';

	private String xfsjje;// 'ʵ�ɽ��';

	private String xfsfqf;// '�Ƿ�Ƿ��';

	private String queryequals_xfsfqf;

	// =====================���ݴ�ѧ ��������=================================

	private String tjzd;// �����ֶ�

	private String tjlx;// ��������

	private String tjz;// ����ֵ

	// ======================���ǹ��õ�~========================================

	/* ======================��Ŀ���=================================== */

	private String xssx;// '��ʾ˳��';

	private String querylike_xmmc;// '��Ŀ����';

	private String xmlb;// '��Ŀ���';

	private String queryequals_xmlb;

	private String pdsj;// '����ʱ��';

	private String queryequals_pdsj;

	private String xmb;// '��Ŀ��';

	private String mrxm;// 'Ĭ����Ŀ';

	private String xxdm;// 'ѧУ����';

	private String xmsm;// '��Ŀ˵��';

	private String sfje;// '�Ƿ��漰���';

	private String sffj;// '�Ƿ�ּ�';

	private String kgzt;// '����״̬';

	private String shjb;// '��˼���';

	private String bzrsh;// '���������';

	private String fdysh;// '����Ա���';

	// private String xysh;//'ѧԺ���';
	// private String xxsh;//'ѧУ���';

	private String rskz;// '��������';

	private String bzrkz;// '�����ο���';

	private String fdykz;// '����Ա����';

	private String xykz;// 'ѧԺ����';

	private String xxkz;// 'ѧУ����';

	private String kzjb;// '���Ƽ���';

	private String sqzq;// '��������';

	private String rssx;// '��������';

	private String[] kzr;// '������';

	private String[] shr;// '�����';

	private String lssh;// '��ʦ���';

	private String jelx;// '�������';

	private String[] fjxmdm;// '��Ŀ����';

	private String[] fjmc;// '��������';

	private String[] nojemc;// '��������Ŀ';

	private String[] qdjemc;// 'ȷ�������Ŀ';

	private String[] qjjemc;// '��������Ŀ';

	private String[] fjxxje;// '���޽��';

	private String[] fjsxje;// '���޽��';

	private String[] fjqdje;// 'ȷ�����';

	private String nofjje;// '�޷ּ�ȷ�����';

	private String nofjsx;// '�޷ּ����޽��';

	private String nofjxx;// '�޷ּ����޽��';

	private String[] xmtjb;// '��Ŀ������';

	private String[] xmtj;// '��Ŀ����';

	private String[] xmtjz;// '��Ŀ����ֵ';

	private String queryequals_xmdm;

	private String queryequals_mrxm;

	private String queryequals_sqzq;

	private String queryequals_sfje;

	private String queryequals_sffj;

	private String queryequals_jelx;

	private String queryequals_shjb;

	private String queryequals_rskz;

	private String[] zzxmdm;// ������Ŀ����

	private String[] zzxmkg;// ������Ŀ����

	private String bmjb;// '���ż���';

	private String[] bmdm;// '���Ŵ���';

	private String[] szrs;// '��������';

	private String fpfs;// ���䷽ʽ

	private String[] iscz;// '�Ƿ����';

	private String[] sznj;// '�Ƿ����';

	private String fprs;// '��������';

	/* ======================ѧ������=================================== */

	private String sqsj;// '����ʱ��';

	private String sqsjCn;// '����ʱ��';

	/* ======================��ͥ�������=================================== */

	private String zd;// '�ֶ�';

	private String path;// '·��';

	// private String sqsj;//'����ʱ��';
	// private String jtrjsr;//'��ͥ�˾�����';
	private String sfgc;// '�Ƿ�²�';

	private String sfdq;// '�Ƿ���';

	private String lszn;// '��ʿ��Ů';

	private String yhzzqk;// '�ѻ��������';

	private String jtszqk;// '��ͥ�������';

	private String tfsjqk;// 'ͻ���¼����';

	private String cjnmqk;// '�м��������';

	private String jtsyqk;// '��ͥʧҵ���';

	private String jtqzqk;// '��ͥǷծ���';

	private String jtqtqk;// '��ͥ�������';

	private String jthk;// ��ͥ����

	private String srly;// ������Դ

	// private String bzrsh;//'���������';
	private String bzrshsj;// '���������ʱ��';

	private String bzrshyj;// '������������';

	// private String fdysh;//'����Ա���';
	private String fdyshsj;// '����Ա���ʱ��';

	private String fdyshyj;// '����Ա������';

	private String save_hdbh;// ��ͬ���

	private String querylike_hdbh;

	// private String xysh;//'ѧԺ���';
	// private String xyshsj;//'ѧԺ���ʱ��';
	// private String xyshyj;//'ѧԺ������';
	// private String xxsh;//'ѧУ���';
	// private String xxshsj;//'ѧУ���ʱ��';
	// private String xxshyj;//'ѧУ������';
	// private String jtrs ;//'��ͥ����';
	// private String jtnzsr ;//'��ͥ��������';
	// private String jtyzsr ;//'��ͥ��������';

	private String jtrjysr;// '��ͥ�˾�������';

	// private String jtsrly ;//'��ͥ������Դ';
	private String jtddxrs;// '��ͥ����ѧ����';

	private String jtjbjjqk;// '��ͥ�����������';

	private String dypddj;// '���������ȼ�';

	private String brhjqk;// '���˻����';

	private String tpsc;// 'ͼƬ�ϴ�';

	private String[] jb;// '����';

	private String[] jbmc;// '��������';

	private String xmzzje;// ��Ŀ�������

	private String xmzzjb;// ��Ŀ��������

	private String save_xmzzje;

	private String save_xmzzjb;

	private String shpk;// '�������';

	private String shzt1;// '���״̬1';

	private String shzt2;// '���״̬2';

	private String shzt3;// '���״̬3';

	private String shzt1yj;// '���״̬1���';

	private String shzt2yj;// '���״̬2���';

	private String shzt3yj;// '���״̬3���';

	private String gx;// '��ϵ';

	private String[] cyxm;// '��Ա����';

	private String[] cynl;// '��Ա����';

	private String[] cygx;// '��Ա��ϵ';

	private String[] cygzdw;// '������λ';

	private String[] cyzy;// 'ְҵ';

	private String[] cydh;// '��ϵ�绰';

	private String[] cynsr;// '������';

	private String[] cynzc;// '��֧��';

	private String[] cyyb;// '�ʱ�';

	private String[] cyysr;// '������';

	private String[] cyjkzk;// '����״��';

	private String save_xmdm;

	private String save_sqsj;

	private String sqsm;// ����˵��

	private String zje;// '�ܽ��';

	private String xnje;// 'ѧ����';

	private String tjbbdm;// ͳ�Ʊ������

	private String bjpm;// '�༶����';

	private String sbxqxfjd;// '�ϰ�ѧ��ѧ�ּ���';

	private String xbxqxfjd;// '�°�ѧ��ѧ�ּ���';

	private String bxkms;// '���޿�����';

	private String yxms;// '��������';

	private String lhms;// '��������';

	private String zycjpm;// 'רҵ�ɼ�����';

	private String zhcpcj;// '�ۺϲ����ɼ�';

	private String zcbjpm;// '�۲�༶����';

	private String wysp;// '����ˮƽ';

	private String jsjsp;// '�����ˮƽ';

	private String hjrq1;// '������1';

	private String hjmc1;// '������1';

	private String bjdw1;// '�佱��λ1';

	private String hjrq2;// '������2';

	private String hjmc2;// '������2';

	private String bjdw2;// '�佱��λ2';

	private String hjrq3;// '������3';

	private String hjmc3;// '������3';

	private String bjdw3;// '�佱��λ3';

	private String hjrq4;// '������4';

	private String hjmc4;// '������4';

	private String bjdw4;// '�佱��λ4';

	private String xf;// 'ѧ��';

	private String kyqk;// '�������';

	// ======================���ǹ��õ�~========================================
	
	private String widthType;
	
	public String getWidthType() {
		return widthType;
	}

	public void setWidthType(String widthType) {
		this.widthType = widthType;
	}

	public String getSqsm() {
		return sqsm;
	}

	public String getTjbbdm() {
		return tjbbdm;
	}

	public void setTjbbdm(String tjbbdm) {
		this.tjbbdm = tjbbdm;
	}

	public void setSqsm(String sqsm) {
		this.sqsm = sqsm;
	}

	public String getSave_sqsj() {
		return save_sqsj;
	}

	public void setSave_sqsj(String save_sqsj) {
		this.save_sqsj = save_sqsj;
	}

	public String getSave_xmdm() {
		return save_xmdm;
	}

	public void setSave_xmdm(String save_xmdm) {
		this.save_xmdm = save_xmdm;
	}

	public String[] getZzxmdm() {
		return zzxmdm;
	}

	public void setZzxmdm(String[] zzxmdm) {
		this.zzxmdm = zzxmdm;
	}

	public String[] getZzxmkg() {
		return zzxmkg;
	}

	public void setZzxmkg(String[] zzxmkg) {
		this.zzxmkg = zzxmkg;
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

	public String getSave_xh() {
		return save_xh;
	}

	public void setSave_xh(String save_xh) {
		this.save_xh = save_xh;
	}

	public String getSave_xn() {
		return save_xn;
	}

	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
	}

	public String getSave_xxsh() {
		return save_xxsh;
	}

	public void setSave_xxsh(String save_xxsh) {
		this.save_xxsh = save_xxsh;
	}

	public String getSave_xxshsj() {
		return save_xxshsj;
	}

	public void setSave_xxshsj(String save_xxshsj) {
		this.save_xxshsj = save_xxshsj;
	}

	public String getSave_xxshyj() {
		return save_xxshyj;
	}

	public void setSave_xxshyj(String save_xxshyj) {
		this.save_xxshyj = save_xxshyj;
	}

	public String getSave_xysh() {
		return save_xysh;
	}

	public void setSave_xysh(String save_xysh) {
		this.save_xysh = save_xysh;
	}

	public String getSave_xyshsj() {
		return save_xyshsj;
	}

	public void setSave_xyshsj(String save_xyshsj) {
		this.save_xyshsj = save_xyshsj;
	}

	public String getSave_xyshyj() {
		return save_xyshyj;
	}

	public void setSave_xyshyj(String save_xyshyj) {
		this.save_xyshyj = save_xyshyj;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
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

	public String getXxshsj() {
		return xxshsj;
	}

	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}

	public String getXxshyj() {
		return xxshyj;
	}

	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
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

	public String getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
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

	public String getFfje() {
		return ffje;
	}

	public void setFfje(String ffje) {
		this.ffje = ffje;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String[] getZyxmList() {
		return zyxmList;
	}

	public void setZyxmList(String[] zyxmList) {
		this.zyxmList = zyxmList;
	}

	public String getBzlx() {
		return bzlx;
	}

	public void setBzlx(String bzlx) {
		this.bzlx = bzlx;
	}

	public String[] getBzzy() {
		return bzzy;
	}

	public void setBzzy(String[] bzzy) {
		this.bzzy = bzzy;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getQueryequals_bzlx() {
		return queryequals_bzlx;
	}

	public void setQueryequals_bzlx(String queryequals_bzlx) {
		this.queryequals_bzlx = queryequals_bzlx;
	}

	public String getQueryequals_yf() {
		return queryequals_yf;
	}

	public void setQueryequals_yf(String queryequals_yf) {
		this.queryequals_yf = queryequals_yf;
	}

	public String[] getAll_xh() {
		return all_xh;
	}

	public void setAll_xh(String[] all_xh) {
		this.all_xh = all_xh;
	}

	public String getQuerygreaterequal_yf() {
		return querygreaterequal_yf;
	}

	public void setQuerygreaterequal_yf(String querygreaterequal_yf) {
		this.querygreaterequal_yf = querygreaterequal_yf;
	}

	public String getQuerylessequal_yf() {
		return querylessequal_yf;
	}

	public void setQuerylessequal_yf(String querylessequal_yf) {
		this.querylessequal_yf = querylessequal_yf;
	}

	public String getBrjdqzmbqk() {
		return brjdqzmbqk;
	}

	public void setBrjdqzmbqk(String brjdqzmbqk) {
		this.brjdqzmbqk = brjdqzmbqk;
	}

	public String getDydj() {
		return dydj;
	}

	public void setDydj(String dydj) {
		this.dydj = dydj;
	}

	public String getJtdd() {
		return jtdd;
	}

	public void setJtdd(String jtdd) {
		this.jtdd = jtdd;
	}

	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getJtdxrs() {
		return jtdxrs;
	}

	public void setJtdxrs(String jtdxrs) {
		this.jtdxrs = jtdxrs;
	}

	public String getJtfzqk() {
		return jtfzqk;
	}

	public void setJtfzqk(String jtfzqk) {
		this.jtfzqk = jtfzqk;
	}

	public String getJtnzsr() {
		return jtnzsr;
	}

	public void setJtnzsr(String jtnzsr) {
		this.jtnzsr = jtnzsr;
	}

	public String getJtrjsr() {
		return jtrjsr;
	}

	public void setJtrjsr(String jtrjsr) {
		this.jtrjsr = jtrjsr;
	}

	public String getJtrs() {
		return jtrs;
	}

	public void setJtrs(String jtrs) {
		this.jtrs = jtrs;
	}

	public String getJtsrly() {
		return jtsrly;
	}

	public void setJtsrly(String jtsrly) {
		this.jtsrly = jtsrly;
	}

	public String getJtyzsr() {
		return jtyzsr;
	}

	public void setJtyzsr(String jtyzsr) {
		this.jtyzsr = jtyzsr;
	}

	public String getQtqk() {
		return qtqk;
	}

	public void setQtqk(String qtqk) {
		this.qtqk = qtqk;
	}

	public String getScdz() {
		return scdz;
	}

	public void setScdz(String scdz) {
		this.scdz = scdz;
	}

	public String getSfcj() {
		return sfcj;
	}

	public void setSfcj(String sfcj) {
		this.sfcj = sfcj;
	}

	public String getSfdb() {
		return sfdb;
	}

	public void setSfdb(String sfdb) {
		this.sfdb = sfdb;
	}

	public String getSfge() {
		return sfge;
	}

	public void setSfge(String sfge) {
		this.sfge = sfge;
	}

	public String getSfjthb() {
		return sfjthb;
	}

	public void setSfjthb(String sfjthb) {
		this.sfjthb = sfjthb;
	}

	public String getSflszn() {
		return sflszn;
	}

	public void setSflszn(String sflszn) {
		this.sflszn = sflszn;
	}

	public String getSfpkzm() {
		return sfpkzm;
	}

	public void setSfpkzm(String sfpkzm) {
		this.sfpkzm = sfpkzm;
	}

	public String getSfzrch() {
		return sfzrch;
	}

	public void setSfzrch(String sfzrch) {
		this.sfzrch = sfzrch;
	}

	public String getXsjtjjqk() {
		return xsjtjjqk;
	}

	public void setXsjtjjqk(String xsjtjjqk) {
		this.xsjtjjqk = xsjtjjqk;
	}

	public String getSave_brjdqzmbqk() {
		return save_brjdqzmbqk;
	}

	public void setSave_brjdqzmbqk(String save_brjdqzmbqk) {
		this.save_brjdqzmbqk = save_brjdqzmbqk;
	}

	public String getSave_dydj() {
		return save_dydj;
	}

	public void setSave_dydj(String save_dydj) {
		this.save_dydj = save_dydj;
	}

	public String getSave_jtdd() {
		return save_jtdd;
	}

	public void setSave_jtdd(String save_jtdd) {
		this.save_jtdd = save_jtdd;
	}

	public String getSave_jtdh() {
		return save_jtdh;
	}

	public void setSave_jtdh(String save_jtdh) {
		this.save_jtdh = save_jtdh;
	}

	public String getSave_jtdxrs() {
		return save_jtdxrs;
	}

	public void setSave_jtdxrs(String save_jtdxrs) {
		this.save_jtdxrs = save_jtdxrs;
	}

	public String getSave_jtfzqk() {
		return save_jtfzqk;
	}

	public void setSave_jtfzqk(String save_jtfzqk) {
		this.save_jtfzqk = save_jtfzqk;
	}

	public String getSave_jtnzsr() {
		return save_jtnzsr;
	}

	public void setSave_jtnzsr(String save_jtnzsr) {
		this.save_jtnzsr = save_jtnzsr;
	}

	public String getSave_jtrjsr() {
		return save_jtrjsr;
	}

	public void setSave_jtrjsr(String save_jtrjsr) {
		this.save_jtrjsr = save_jtrjsr;
	}

	public String getSave_jtrs() {
		return save_jtrs;
	}

	public void setSave_jtrs(String save_jtrs) {
		this.save_jtrs = save_jtrs;
	}

	public String getSave_jtsrly() {
		return save_jtsrly;
	}

	public void setSave_jtsrly(String save_jtsrly) {
		this.save_jtsrly = save_jtsrly;
	}

	public String getSave_jtyzsr() {
		return save_jtyzsr;
	}

	public void setSave_jtyzsr(String save_jtyzsr) {
		this.save_jtyzsr = save_jtyzsr;
	}

	public String getSave_qtqk() {
		return save_qtqk;
	}

	public void setSave_qtqk(String save_qtqk) {
		this.save_qtqk = save_qtqk;
	}

	public String getSave_scdz() {
		return save_scdz;
	}

	public void setSave_scdz(String save_scdz) {
		this.save_scdz = save_scdz;
	}

	public String getSave_sfcj() {
		return save_sfcj;
	}

	public void setSave_sfcj(String save_sfcj) {
		this.save_sfcj = save_sfcj;
	}

	public String getSave_sfdb() {
		return save_sfdb;
	}

	public void setSave_sfdb(String save_sfdb) {
		this.save_sfdb = save_sfdb;
	}

	public String getSave_sfge() {
		return save_sfge;
	}

	public void setSave_sfge(String save_sfge) {
		this.save_sfge = save_sfge;
	}

	public String getSave_sfjthb() {
		return save_sfjthb;
	}

	public void setSave_sfjthb(String save_sfjthb) {
		this.save_sfjthb = save_sfjthb;
	}

	public String getSave_sflszn() {
		return save_sflszn;
	}

	public void setSave_sflszn(String save_sflszn) {
		this.save_sflszn = save_sflszn;
	}

	public String getSave_sfpkzm() {
		return save_sfpkzm;
	}

	public void setSave_sfpkzm(String save_sfpkzm) {
		this.save_sfpkzm = save_sfpkzm;
	}

	public String getSave_sfzrch() {
		return save_sfzrch;
	}

	public void setSave_sfzrch(String save_sfzrch) {
		this.save_sfzrch = save_sfzrch;
	}

	public String getSave_xsjtjjqk() {
		return save_xsjtjjqk;
	}

	public void setSave_xsjtjjqk(String save_xsjtjjqk) {
		this.save_xsjtjjqk = save_xsjtjjqk;
	}

	public String getBjsh() {
		return bjsh;
	}

	public void setBjsh(String bjsh) {
		this.bjsh = bjsh;
	}

	public String getBjshsj() {
		return bjshsj;
	}

	public void setBjshsj(String bjshsj) {
		this.bjshsj = bjshsj;
	}

	public String getBjshyj() {
		return bjshyj;
	}

	public void setBjshyj(String bjshyj) {
		this.bjshyj = bjshyj;
	}

	public String getQueryequals_bjsh() {
		return queryequals_bjsh;
	}

	public void setQueryequals_bjsh(String queryequals_bjsh) {
		this.queryequals_bjsh = queryequals_bjsh;
	}

	public String getSave_bjsh() {
		return save_bjsh;
	}

	public void setSave_bjsh(String save_bjsh) {
		this.save_bjsh = save_bjsh;
	}

	public String getSave_bjshsj() {
		return save_bjshsj;
	}

	public void setSave_bjshsj(String save_bjshsj) {
		this.save_bjshsj = save_bjshsj;
	}

	public String getSave_bjshyj() {
		return save_bjshyj;
	}

	public void setSave_bjshyj(String save_bjshyj) {
		this.save_bjshyj = save_bjshyj;
	}

	public String getKg() {
		return kg;
	}

	public void setKg(String kg) {
		this.kg = kg;
	}

	public String[] getSzxy() {
		return szxy;
	}

	public void setSzxy(String[] szxy) {
		this.szxy = szxy;
	}

	public String[] getXmbl() {
		return xmbl;
	}

	public void setXmbl(String[] xmbl) {
		this.xmbl = xmbl;
	}

	public String[] getXmjb() {
		return xmjb;
	}

	public void setXmjb(String[] xmjb) {
		this.xmjb = xmjb;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String[] getXmrs() {
		return xmrs;
	}

	public void setXmrs(String[] xmrs) {
		this.xmrs = xmrs;
	}

	public String getKnsjb() {
		return knsjb;
	}

	public void setKnsjb(String knsjb) {
		this.knsjb = knsjb;
	}

	public String getQueryequals_lx() {
		return queryequals_lx;
	}

	public void setQueryequals_lx(String queryequals_lx) {
		this.queryequals_lx = queryequals_lx;
	}

	public String getQueryequals_knsjb() {
		return queryequals_knsjb;
	}

	public void setQueryequals_knsjb(String queryequals_knsjb) {
		this.queryequals_knsjb = queryequals_knsjb;
	}

	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}

	public void setPrimarykey_checkVal(String[] primarykey_checkVal) {
		this.primarykey_checkVal = primarykey_checkVal;
	}

	public String getShzd() {
		return shzd;
	}

	public void setShzd(String shzd) {
		this.shzd = shzd;
	}

	public String getQueryequals_xfsfqf() {
		return queryequals_xfsfqf;
	}

	public void setQueryequals_xfsfqf(String queryequals_xfsfqf) {
		this.queryequals_xfsfqf = queryequals_xfsfqf;
	}

	public String getXfsfqf() {
		return xfsfqf;
	}

	public void setXfsfqf(String xfsfqf) {
		this.xfsfqf = xfsfqf;
	}

	public String getXfsfxm() {
		return xfsfxm;
	}

	public void setXfsfxm(String xfsfxm) {
		this.xfsfxm = xfsfxm;
	}

	public String getXfsjje() {
		return xfsjje;
	}

	public void setXfsjje(String xfsjje) {
		this.xfsjje = xfsjje;
	}

	public String getXfyjje() {
		return xfyjje;
	}

	public void setXfyjje(String xfyjje) {
		this.xfyjje = xfyjje;
	}

	public String getQuerylike_xfsfxm() {
		return querylike_xfsfxm;
	}

	public void setQuerylike_xfsfxm(String querylike_xfsfxm) {
		this.querylike_xfsfxm = querylike_xfsfxm;
	}

	public String getBzrsh() {
		return bzrsh;
	}

	public void setBzrsh(String bzrsh) {
		this.bzrsh = bzrsh;
	}

	public String getFdysh() {
		return fdysh;
	}

	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}

	public String getMrxm() {
		return mrxm;
	}

	public void setMrxm(String mrxm) {
		this.mrxm = mrxm;
	}

	public String getRskz() {
		return rskz;
	}

	public void setRskz(String rskz) {
		this.rskz = rskz;
	}

	public String getRssx() {
		return rssx;
	}

	public void setRssx(String rssx) {
		this.rssx = rssx;
	}

	public String getSffj() {
		return sffj;
	}

	public void setSffj(String sffj) {
		this.sffj = sffj;
	}

	public String getSqzq() {
		return sqzq;
	}

	public void setSqzq(String sqzq) {
		this.sqzq = sqzq;
	}

	public String getXxdm() {
		return xxdm;
	}

	public void setXxdm(String xxdm) {
		this.xxdm = xxdm;
	}

	public String getSfje() {
		return sfje;
	}

	public void setSfje(String sfje) {
		this.sfje = sfje;
	}

	public String getBzrkz() {
		return bzrkz;
	}

	public void setBzrkz(String bzrkz) {
		this.bzrkz = bzrkz;
	}

	public String getFdykz() {
		return fdykz;
	}

	public void setFdykz(String fdykz) {
		this.fdykz = fdykz;
	}

	public String getKgzt() {
		return kgzt;
	}

	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}

	public String getKzjb() {
		return kzjb;
	}

	public void setKzjb(String kzjb) {
		this.kzjb = kzjb;
	}

	public String getXmb() {
		return xmb;
	}

	public void setXmb(String xmb) {
		this.xmb = xmb;
	}

	public String getXmsm() {
		return xmsm;
	}

	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
	}

	public String getXxkz() {
		return xxkz;
	}

	public void setXxkz(String xxkz) {
		this.xxkz = xxkz;
	}

	public String getXykz() {
		return xykz;
	}

	public void setXykz(String xykz) {
		this.xykz = xykz;
	}

	public String[] getKzr() {
		return kzr;
	}

	public void setKzr(String[] kzr) {
		this.kzr = kzr;
	}

	public String[] getShr() {
		return shr;
	}

	public void setShr(String[] shr) {
		this.shr = shr;
	}

	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getShjb() {
		return shjb;
	}

	public void setShjb(String shjb) {
		this.shjb = shjb;
	}

	public String getLssh() {
		return lssh;
	}

	public void setLssh(String lssh) {
		this.lssh = lssh;
	}

	public String getJelx() {
		return jelx;
	}

	public void setJelx(String jelx) {
		this.jelx = jelx;
	}

	public String[] getFjmc() {
		return fjmc;
	}

	public void setFjmc(String[] fjmc) {
		this.fjmc = fjmc;
	}

	public String[] getFjqdje() {
		return fjqdje;
	}

	public void setFjqdje(String[] fjqdje) {
		this.fjqdje = fjqdje;
	}

	public String[] getFjsxje() {
		return fjsxje;
	}

	public void setFjsxje(String[] fjsxje) {
		this.fjsxje = fjsxje;
	}

	public String[] getFjxmdm() {
		return fjxmdm;
	}

	public void setFjxmdm(String[] fjxmdm) {
		this.fjxmdm = fjxmdm;
	}

	public String[] getFjxxje() {
		return fjxxje;
	}

	public void setFjxxje(String[] fjxxje) {
		this.fjxxje = fjxxje;
	}

	public String[] getNojemc() {
		return nojemc;
	}

	public void setNojemc(String[] nojemc) {
		this.nojemc = nojemc;
	}

	public String[] getQdjemc() {
		return qdjemc;
	}

	public void setQdjemc(String[] qdjemc) {
		this.qdjemc = qdjemc;
	}

	public String[] getQjjemc() {
		return qjjemc;
	}

	public void setQjjemc(String[] qjjemc) {
		this.qjjemc = qjjemc;
	}

	public String getNofjje() {
		return nofjje;
	}

	public void setNofjje(String nofjje) {
		this.nofjje = nofjje;
	}

	public String getNofjsx() {
		return nofjsx;
	}

	public void setNofjsx(String nofjsx) {
		this.nofjsx = nofjsx;
	}

	public String getNofjxx() {
		return nofjxx;
	}

	public void setNofjxx(String nofjxx) {
		this.nofjxx = nofjxx;
	}

	public String[] getXmtj() {
		return xmtj;
	}

	public void setXmtj(String[] xmtj) {
		this.xmtj = xmtj;
	}

	public String[] getXmtjz() {
		return xmtjz;
	}

	public void setXmtjz(String[] xmtjz) {
		this.xmtjz = xmtjz;
	}

	public String getQueryequals_jelx() {
		return queryequals_jelx;
	}

	public void setQueryequals_jelx(String queryequals_jelx) {
		this.queryequals_jelx = queryequals_jelx;
	}

	public String getQueryequals_mrxm() {
		return queryequals_mrxm;
	}

	public void setQueryequals_mrxm(String queryequals_mrxm) {
		this.queryequals_mrxm = queryequals_mrxm;
	}

	public String getQueryequals_rskz() {
		return queryequals_rskz;
	}

	public void setQueryequals_rskz(String queryequals_rskz) {
		this.queryequals_rskz = queryequals_rskz;
	}

	public String getQueryequals_sffj() {
		return queryequals_sffj;
	}

	public void setQueryequals_sffj(String queryequals_sffj) {
		this.queryequals_sffj = queryequals_sffj;
	}

	public String getQueryequals_sfje() {
		return queryequals_sfje;
	}

	public void setQueryequals_sfje(String queryequals_sfje) {
		this.queryequals_sfje = queryequals_sfje;
	}

	public String getQueryequals_shjb() {
		return queryequals_shjb;
	}

	public void setQueryequals_shjb(String queryequals_shjb) {
		this.queryequals_shjb = queryequals_shjb;
	}

	public String getQueryequals_sqzq() {
		return queryequals_sqzq;
	}

	public void setQueryequals_sqzq(String queryequals_sqzq) {
		this.queryequals_sqzq = queryequals_sqzq;
	}

	public String getQueryequals_xmdm() {
		return queryequals_xmdm;
	}

	public void setQueryequals_xmdm(String queryequals_xmdm) {
		this.queryequals_xmdm = queryequals_xmdm;
	}

	public String[] getBmdm() {
		return bmdm;
	}

	public void setBmdm(String[] bmdm) {
		this.bmdm = bmdm;
	}

	public String getBmjb() {
		return bmjb;
	}

	public void setBmjb(String bmjb) {
		this.bmjb = bmjb;
	}

	public String[] getSzrs() {
		return szrs;
	}

	public void setSzrs(String[] szrs) {
		this.szrs = szrs;
	}

	public String getFpfs() {
		return fpfs;
	}

	public void setFpfs(String fpfs) {
		this.fpfs = fpfs;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String[] getIscz() {
		return iscz;
	}

	public void setIscz(String[] iscz) {
		this.iscz = iscz;
	}

	public String[] getSznj() {
		return sznj;
	}

	public void setSznj(String[] sznj) {
		this.sznj = sznj;
	}

	public String getFprs() {
		return fprs;
	}

	public void setFprs(String fprs) {
		this.fprs = fprs;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String[] getXmtjb() {
		return xmtjb;
	}

	public void setXmtjb(String[] xmtjb) {
		this.xmtjb = xmtjb;
	}

	public String getBrhjqk() {
		return brhjqk;
	}

	public void setBrhjqk(String brhjqk) {
		this.brhjqk = brhjqk;
	}

	public String getBzrshsj() {
		return bzrshsj;
	}

	public void setBzrshsj(String bzrshsj) {
		this.bzrshsj = bzrshsj;
	}

	public String getBzrshyj() {
		return bzrshyj;
	}

	public void setBzrshyj(String bzrshyj) {
		this.bzrshyj = bzrshyj;
	}

	public String getCjnmqk() {
		return cjnmqk;
	}

	public void setCjnmqk(String cjnmqk) {
		this.cjnmqk = cjnmqk;
	}

	public String getDypddj() {
		return dypddj;
	}

	public void setDypddj(String dypddj) {
		this.dypddj = dypddj;
	}

	public String getFdyshsj() {
		return fdyshsj;
	}

	public void setFdyshsj(String fdyshsj) {
		this.fdyshsj = fdyshsj;
	}

	public String getFdyshyj() {
		return fdyshyj;
	}

	public void setFdyshyj(String fdyshyj) {
		this.fdyshyj = fdyshyj;
	}

	public String getJtddxrs() {
		return jtddxrs;
	}

	public void setJtddxrs(String jtddxrs) {
		this.jtddxrs = jtddxrs;
	}

	public String getJtjbjjqk() {
		return jtjbjjqk;
	}

	public void setJtjbjjqk(String jtjbjjqk) {
		this.jtjbjjqk = jtjbjjqk;
	}

	public String getJtqtqk() {
		return jtqtqk;
	}

	public void setJtqtqk(String jtqtqk) {
		this.jtqtqk = jtqtqk;
	}

	public String getJtqzqk() {
		return jtqzqk;
	}

	public void setJtqzqk(String jtqzqk) {
		this.jtqzqk = jtqzqk;
	}

	public String getJtrjysr() {
		return jtrjysr;
	}

	public void setJtrjysr(String jtrjysr) {
		this.jtrjysr = jtrjysr;
	}

	public String getJtsyqk() {
		return jtsyqk;
	}

	public void setJtsyqk(String jtsyqk) {
		this.jtsyqk = jtsyqk;
	}

	public String getJtszqk() {
		return jtszqk;
	}

	public void setJtszqk(String jtszqk) {
		this.jtszqk = jtszqk;
	}

	public String getLszn() {
		return lszn;
	}

	public void setLszn(String lszn) {
		this.lszn = lszn;
	}

	public String getSfdq() {
		return sfdq;
	}

	public void setSfdq(String sfdq) {
		this.sfdq = sfdq;
	}

	public String getSfgc() {
		return sfgc;
	}

	public void setSfgc(String sfgc) {
		this.sfgc = sfgc;
	}

	public String getTfsjqk() {
		return tfsjqk;
	}

	public void setTfsjqk(String tfsjqk) {
		this.tfsjqk = tfsjqk;
	}

	public String getTpsc() {
		return tpsc;
	}

	public void setTpsc(String tpsc) {
		this.tpsc = tpsc;
	}

	public String getYhzzqk() {
		return yhzzqk;
	}

	public void setYhzzqk(String yhzzqk) {
		this.yhzzqk = yhzzqk;
	}

	public String getSqsjCn() {
		return sqsjCn;
	}

	public void setSqsjCn(String sqsjCn) {
		this.sqsjCn = sqsjCn;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getUserDep() {
		return userDep;
	}

	public void setUserDep(String userDep) {
		this.userDep = userDep;
	}

	public String[] getJb() {
		return jb;
	}

	public void setJb(String[] jb) {
		this.jb = jb;
	}

	public String[] getJbmc() {
		return jbmc;
	}

	public void setJbmc(String[] jbmc) {
		this.jbmc = jbmc;
	}

	public String getSave_xmzzje() {
		return save_xmzzje;
	}

	public void setSave_xmzzje(String save_xmzzje) {
		this.save_xmzzje = save_xmzzje;
	}

	public String getXmzzje() {
		return xmzzje;
	}

	public void setXmzzje(String xmzzje) {
		this.xmzzje = xmzzje;
	}

	public String getSave_xmzzjb() {
		return save_xmzzjb;
	}

	public void setSave_xmzzjb(String save_xmzzjb) {
		this.save_xmzzjb = save_xmzzjb;
	}

	public String getXmzzjb() {
		return xmzzjb;
	}

	public void setXmzzjb(String xmzzjb) {
		this.xmzzjb = xmzzjb;
	}

	public String getShpk() {
		return shpk;
	}

	public void setShpk(String shpk) {
		this.shpk = shpk;
	}

	public String getShzt1() {
		return shzt1;
	}

	public void setShzt1(String shzt1) {
		this.shzt1 = shzt1;
	}

	public String getShzt1yj() {
		return shzt1yj;
	}

	public void setShzt1yj(String shzt1yj) {
		this.shzt1yj = shzt1yj;
	}

	public String getShzt2() {
		return shzt2;
	}

	public void setShzt2(String shzt2) {
		this.shzt2 = shzt2;
	}

	public String getShzt2yj() {
		return shzt2yj;
	}

	public void setShzt2yj(String shzt2yj) {
		this.shzt2yj = shzt2yj;
	}

	public String getShzt3() {
		return shzt3;
	}

	public void setShzt3(String shzt3) {
		this.shzt3 = shzt3;
	}

	public String getShzt3yj() {
		return shzt3yj;
	}

	public void setShzt3yj(String shzt3yj) {
		this.shzt3yj = shzt3yj;
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

	public String getMklx() {
		return mklx;
	}

	public void setMklx(String mklx) {
		this.mklx = mklx;
	}

	public String[] getCydh() {
		return cydh;
	}

	public void setCydh(String[] cydh) {
		this.cydh = cydh;
	}

	public String[] getCygx() {
		return cygx;
	}

	public void setCygx(String[] cygx) {
		this.cygx = cygx;
	}

	public String[] getCygzdw() {
		return cygzdw;
	}

	public void setCygzdw(String[] cygzdw) {
		this.cygzdw = cygzdw;
	}

	public String[] getCyjkzk() {
		return cyjkzk;
	}

	public void setCyjkzk(String[] cyjkzk) {
		this.cyjkzk = cyjkzk;
	}

	public String[] getCynl() {
		return cynl;
	}

	public void setCynl(String[] cynl) {
		this.cynl = cynl;
	}

	public String[] getCynsr() {
		return cynsr;
	}

	public void setCynsr(String[] cynsr) {
		this.cynsr = cynsr;
	}

	public String[] getCyxm() {
		return cyxm;
	}

	public void setCyxm(String[] cyxm) {
		this.cyxm = cyxm;
	}

	public String[] getCyzy() {
		return cyzy;
	}

	public void setCyzy(String[] cyzy) {
		this.cyzy = cyzy;
	}

	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	public String[] getCynzc() {
		return cynzc;
	}

	public void setCynzc(String[] cynzc) {
		this.cynzc = cynzc;
	}

	public String getJthk() {
		return jthk;
	}

	public void setJthk(String jthk) {
		this.jthk = jthk;
	}

	public String getSrly() {
		return srly;
	}

	public void setSrly(String srly) {
		this.srly = srly;
	}

	public String getXnje() {
		return xnje;
	}

	public void setXnje(String xnje) {
		this.xnje = xnje;
	}

	public String getZje() {
		return zje;
	}

	public void setZje(String zje) {
		this.zje = zje;
	}

	public String getSzbm() {
		return szbm;
	}

	public void setSzbm(String szbm) {
		this.szbm = szbm;
	}

	public String getQuerylike_hdbh() {
		return querylike_hdbh;
	}

	public void setQuerylike_hdbh(String querylike_hdbh) {
		this.querylike_hdbh = querylike_hdbh;
	}

	public String getSave_hdbh() {
		return save_hdbh;
	}

	public void setSave_hdbh(String save_hdbh) {
		this.save_hdbh = save_hdbh;
	}

	public String[] getCyyb() {
		return cyyb;
	}

	public void setCyyb(String[] cyyb) {
		this.cyyb = cyyb;
	}

	public String[] getCyysr() {
		return cyysr;
	}

	public void setCyysr(String[] cyysr) {
		this.cyysr = cyysr;
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

	public String getXmlb() {
		return xmlb;
	}

	public void setXmlb(String xmlb) {
		this.xmlb = xmlb;
	}

	public String getBjdw1() {
		return bjdw1;
	}

	public void setBjdw1(String bjdw1) {
		this.bjdw1 = bjdw1;
	}

	public String getBjdw2() {
		return bjdw2;
	}

	public void setBjdw2(String bjdw2) {
		this.bjdw2 = bjdw2;
	}

	public String getBjdw3() {
		return bjdw3;
	}

	public void setBjdw3(String bjdw3) {
		this.bjdw3 = bjdw3;
	}

	public String getBjdw4() {
		return bjdw4;
	}

	public void setBjdw4(String bjdw4) {
		this.bjdw4 = bjdw4;
	}

	public String getBjpm() {
		return bjpm;
	}

	public void setBjpm(String bjpm) {
		this.bjpm = bjpm;
	}

	public String getBxkms() {
		return bxkms;
	}

	public void setBxkms(String bxkms) {
		this.bxkms = bxkms;
	}

	public String getHjmc1() {
		return hjmc1;
	}

	public void setHjmc1(String hjmc1) {
		this.hjmc1 = hjmc1;
	}

	public String getHjmc2() {
		return hjmc2;
	}

	public void setHjmc2(String hjmc2) {
		this.hjmc2 = hjmc2;
	}

	public String getHjmc3() {
		return hjmc3;
	}

	public void setHjmc3(String hjmc3) {
		this.hjmc3 = hjmc3;
	}

	public String getHjmc4() {
		return hjmc4;
	}

	public void setHjmc4(String hjmc4) {
		this.hjmc4 = hjmc4;
	}

	public String getHjrq1() {
		return hjrq1;
	}

	public void setHjrq1(String hjrq1) {
		this.hjrq1 = hjrq1;
	}

	public String getHjrq2() {
		return hjrq2;
	}

	public void setHjrq2(String hjrq2) {
		this.hjrq2 = hjrq2;
	}

	public String getHjrq3() {
		return hjrq3;
	}

	public void setHjrq3(String hjrq3) {
		this.hjrq3 = hjrq3;
	}

	public String getHjrq4() {
		return hjrq4;
	}

	public void setHjrq4(String hjrq4) {
		this.hjrq4 = hjrq4;
	}

	public String getJsjsp() {
		return jsjsp;
	}

	public void setJsjsp(String jsjsp) {
		this.jsjsp = jsjsp;
	}

	public String getKyqk() {
		return kyqk;
	}

	public void setKyqk(String kyqk) {
		this.kyqk = kyqk;
	}

	public String getLhms() {
		return lhms;
	}

	public void setLhms(String lhms) {
		this.lhms = lhms;
	}

	public String getSbxqxfjd() {
		return sbxqxfjd;
	}

	public void setSbxqxfjd(String sbxqxfjd) {
		this.sbxqxfjd = sbxqxfjd;
	}

	public String getWysp() {
		return wysp;
	}

	public void setWysp(String wysp) {
		this.wysp = wysp;
	}

	public String getXbxqxfjd() {
		return xbxqxfjd;
	}

	public void setXbxqxfjd(String xbxqxfjd) {
		this.xbxqxfjd = xbxqxfjd;
	}

	public String getYxms() {
		return yxms;
	}

	public void setYxms(String yxms) {
		this.yxms = yxms;
	}

	public String getZcbjpm() {
		return zcbjpm;
	}

	public void setZcbjpm(String zcbjpm) {
		this.zcbjpm = zcbjpm;
	}

	public String getZhcpcj() {
		return zhcpcj;
	}

	public void setZhcpcj(String zhcpcj) {
		this.zhcpcj = zhcpcj;
	}

	public String getZycjpm() {
		return zycjpm;
	}

	public void setZycjpm(String zycjpm) {
		this.zycjpm = zycjpm;
	}

	public String getXf() {
		return xf;
	}

	public void setXf(String xf) {
		this.xf = xf;
	}

	public String getQueryequals_xmlb() {
		return queryequals_xmlb;
	}

	public void setQueryequals_xmlb(String queryequals_xmlb) {
		this.queryequals_xmlb = queryequals_xmlb;
	}

	public String getQuerylike_xmmc() {
		return querylike_xmmc;
	}

	public void setQuerylike_xmmc(String querylike_xmmc) {
		this.querylike_xmmc = querylike_xmmc;
	}

	public String getPdsj() {
		return pdsj;
	}

	public void setPdsj(String pdsj) {
		this.pdsj = pdsj;
	}

	public String getQueryequals_pdsj() {
		return queryequals_pdsj;
	}

	public void setQueryequals_pdsj(String queryequals_pdsj) {
		this.queryequals_pdsj = queryequals_pdsj;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	public String getSave_bjgkms() {
		return save_bjgkms;
	}

	public void setSave_bjgkms(String save_bjgkms) {
		this.save_bjgkms = save_bjgkms;
	}

	public String getIsxb() {
		return isxb;
	}

	public void setIsxb(String isxb) {
		this.isxb = isxb;
	}

	public String getSave_nd() {
		return save_nd;
	}

	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
	}

	public String getSave_xq() {
		return save_xq;
	}

	public void setSave_xq(String save_xq) {
		this.save_xq = save_xq;
	}

	public String getQueryequals_isxb() {
		return queryequals_isxb;
	}

	public void setQueryequals_isxb(String queryequals_isxb) {
		this.queryequals_isxb = queryequals_isxb;
	}

	public String getPjcj() {
		return pjcj;
	}

	public void setPjcj(String pjcj) {
		this.pjcj = pjcj;
	}

	public String getPjcjtj() {
		return pjcjtj;
	}

	public void setPjcjtj(String pjcjtj) {
		this.pjcjtj = pjcjtj;
	}

	public String getSave_sy() {
		return save_sy;
	}

	public void setSave_sy(String save_sy) {
		this.save_sy = save_sy;
	}

	public String getSy() {
		return sy;
	}

	public void setSy(String sy) {
		this.sy = sy;
	}

	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}

	public String getSave_jtdz() {
		return save_jtdz;
	}

	public void setSave_jtdz(String save_jtdz) {
		this.save_jtdz = save_jtdz;
	}

	public String getJtyb() {
		return jtyb;
	}

	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}

	public String getSave_jtyb() {
		return save_jtyb;
	}

	public void setSave_jtyb(String save_jtyb) {
		this.save_jtyb = save_jtyb;
	}

	public String getQtnr() {
		return qtnr;
	}

	public void setQtnr(String qtnr) {
		this.qtnr = qtnr;
	}

	public String getSfczxgjt() {
		return sfczxgjt;
	}

	public void setSfczxgjt(String sfczxgjt) {
		this.sfczxgjt = sfczxgjt;
	}

	public String getSfdqjt() {
		return sfdqjt;
	}

	public void setSfdqjt(String sfdqjt) {
		this.sfdqjt = sfdqjt;
	}

	public String getSffmxcj() {
		return sffmxcj;
	}

	public void setSffmxcj(String sffmxcj) {
		this.sffmxcj = sffmxcj;
	}

	public String getSfgr() {
		return sfgr;
	}

	public void setSfgr(String sfgr) {
		this.sfgr = sfgr;
	}

	public String getSfhzdjb() {
		return sfhzdjb;
	}

	public void setSfhzdjb(String sfhzdjb) {
		this.sfhzdjb = sfhzdjb;
	}

	public String getSfjtrkd() {
		return sfjtrkd;
	}

	public void setSfjtrkd(String sfjtrkd) {
		this.sfjtrkd = sfjtrkd;
	}

	public String getSfncpkdq() {
		return sfncpkdq;
	}

	public void setSfncpkdq(String sfncpkdq) {
		this.sfncpkdq = sfncpkdq;
	}

	public String getSfqt() {
		return sfqt;
	}

	public void setSfqt(String sfqt) {
		this.sfqt = sfqt;
	}

	public String getSfzrzh() {
		return sfzrzh;
	}

	public void setSfzrzh(String sfzrzh) {
		this.sfzrzh = sfzrzh;
	}

	public String getSave_qtnr() {
		return save_qtnr;
	}

	public void setSave_qtnr(String save_qtnr) {
		this.save_qtnr = save_qtnr;
	}

	public String getSave_sfczxgjt() {
		return save_sfczxgjt;
	}

	public void setSave_sfczxgjt(String save_sfczxgjt) {
		this.save_sfczxgjt = save_sfczxgjt;
	}

	public String getSave_sfdqjt() {
		return save_sfdqjt;
	}

	public void setSave_sfdqjt(String save_sfdqjt) {
		this.save_sfdqjt = save_sfdqjt;
	}

	public String getSave_sffmxcj() {
		return save_sffmxcj;
	}

	public void setSave_sffmxcj(String save_sffmxcj) {
		this.save_sffmxcj = save_sffmxcj;
	}

	public String getSave_sfgr() {
		return save_sfgr;
	}

	public void setSave_sfgr(String save_sfgr) {
		this.save_sfgr = save_sfgr;
	}

	public String getSave_sfhzdjb() {
		return save_sfhzdjb;
	}

	public void setSave_sfhzdjb(String save_sfhzdjb) {
		this.save_sfhzdjb = save_sfhzdjb;
	}

	public String getSave_sfjtrkd() {
		return save_sfjtrkd;
	}

	public void setSave_sfjtrkd(String save_sfjtrkd) {
		this.save_sfjtrkd = save_sfjtrkd;
	}

	public String getSave_sfncpkdq() {
		return save_sfncpkdq;
	}

	public void setSave_sfncpkdq(String save_sfncpkdq) {
		this.save_sfncpkdq = save_sfncpkdq;
	}

	public String getSave_sfqt() {
		return save_sfqt;
	}

	public void setSave_sfqt(String save_sfqt) {
		this.save_sfqt = save_sfqt;
	}

	public String getSave_sfzrzh() {
		return save_sfzrzh;
	}

	public void setSave_sfzrzh(String save_sfzrzh) {
		this.save_sfzrzh = save_sfzrzh;
	}

	public String getPkyyxxsm() {
		return pkyyxxsm;
	}

	public void setPkyyxxsm(String pkyyxxsm) {
		this.pkyyxxsm = pkyyxxsm;
	}

	public String getSave_pkyyxxsm() {
		return save_pkyyxxsm;
	}

	public void setSave_pkyyxxsm(String save_pkyyxxsm) {
		this.save_pkyyxxsm = save_pkyyxxsm;
	}

	public String getMzbm_lxdh() {
		return mzbm_lxdh;
	}

	public void setMzbm_lxdh(String mzbm_lxdh) {
		this.mzbm_lxdh = mzbm_lxdh;
	}

	public String getMzbm_xxtxdz() {
		return mzbm_xxtxdz;
	}

	public void setMzbm_xxtxdz(String mzbm_xxtxdz) {
		this.mzbm_xxtxdz = mzbm_xxtxdz;
	}

	public String getMzbm_yzbm() {
		return mzbm_yzbm;
	}

	public void setMzbm_yzbm(String mzbm_yzbm) {
		this.mzbm_yzbm = mzbm_yzbm;
	}

	public String getSave_lxdh() {
		return save_lxdh;
	}

	public void setSave_lxdh(String save_lxdh) {
		this.save_lxdh = save_lxdh;
	}

	public String getSave_xxtxdz() {
		return save_xxtxdz;
	}

	public void setSave_xxtxdz(String save_xxtxdz) {
		this.save_xxtxdz = save_xxtxdz;
	}

	public String getSave_yzbm() {
		return save_yzbm;
	}

	public void setSave_yzbm(String save_yzbm) {
		this.save_yzbm = save_yzbm;
	}

	public boolean isFlg() {
		return flg;
	}

	public void setFlg(boolean flg) {
		this.flg = flg;
	}

	public String getIskns() {
		return iskns;
	}

	public void setIskns(String iskns) {
		this.iskns = iskns;
	}

	public String getSave_bzrshsj() {
		return save_bzrshsj;
	}

	public void setSave_bzrshsj(String save_bzrshsj) {
		this.save_bzrshsj = save_bzrshsj;
	}

	public String getSave_fdyshsj() {
		return save_fdyshsj;
	}

	public void setSave_fdyshsj(String save_fdyshsj) {
		this.save_fdyshsj = save_fdyshsj;
	}

	public String getShsj1() {
		return shsj1;
	}

	public void setShsj1(String shsj1) {
		this.shsj1 = shsj1;
	}

	public String getShsj2() {
		return shsj2;
	}

	public void setShsj2(String shsj2) {
		this.shsj2 = shsj2;
	}

	public String getShsj3() {
		return shsj3;
	}

	public void setShsj3(String shsj3) {
		this.shsj3 = shsj3;
	}

	public String getJqpjf() {
		return jqpjf;
	}

	public void setJqpjf(String jqpjf) {
		this.jqpjf = jqpjf;
	}

	public String getJqpjftj() {
		return jqpjftj;
	}

	public void setJqpjftj(String jqpjftj) {
		this.jqpjftj = jqpjftj;
	}

	public String getJqpjjd() {
		return jqpjjd;
	}

	public void setJqpjjd(String jqpjjd) {
		this.jqpjjd = jqpjjd;
	}

	public String getJqpjjdtj() {
		return jqpjjdtj;
	}

	public void setJqpjjdtj(String jqpjjdtj) {
		this.jqpjjdtj = jqpjjdtj;
	}

	public String[] getBkjdxm() {
		return bkjdxm;
	}

	public void setBkjdxm(String[] bkjdxm) {
		this.bkjdxm = bkjdxm;
	}

}
