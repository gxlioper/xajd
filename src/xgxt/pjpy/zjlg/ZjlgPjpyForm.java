package xgxt.pjpy.zjlg;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class ZjlgPjpyForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1948684890106534275L;

	// ѧ��
	private String xh;

	// ����
	private String xm;

	// �Ա�
	private String xb;

	// �꼶
	private String nj;

	// ѧԺ����
	private String xydm;

	// רҵ����
	private String zydm;

	// �༶����
	private String bjdm;
	
	//�༶���루����ֵ��
	private String bj;

	// ѧ��
	private String xn;
	
	private String jxjxn;

	// ���ڷ�
	private String kqf;

	// ������
	private String cpf;

	// ���
	private String nd;

	// ѧ��
	private String xq;

	// �Ƿ���Ҫ��������
	private String isBl;

	// �����ֱ���
	private String zpfbl;

	// �༶�ֱ���
	private String bjfbl;

	// ���������
	private String zwpyf;

	// �༶�����
	private String bjpyf;

	// ѧԺ���ӷ�
	private String xyfjf;

	// ѧԺ���
	private String xysh;

	// ƽʱ�ֱ���
	private String psfbl;

	// �����ֱ���
	private String wsfbl;

	// ���ڷֱ���
	private String kqfbl;
	
	// ��������
	private String dybl;
	
	//��ѧ�����
	private String jxjbl;
	
	//��ѧ������
	private String jxjrs;
	
	//���������
	private String cpzdm;
	
	//���Ŵ���
	private String bmdm;
	
	//��������
	private String cprs;
	
	//�������
	private String bmlb;
	
	// ��������
	private String zybl;

	// �ɼ�����
	private String cjlx;

	// �����ƺŴ���
	private String rychdm;

	// ��ע��ѧ�������Ϣ
	private String bz;

	// �Ƿ��߶���
	private String isZds;
	
	// �Ƿ�����༶
	private String sfyxbj;
	
	//�ֶ���
	private String zdm;
	
	//����
	private String tj;

	//����ֵ
	private String val;
	
	// ͨ��
	Pages pages = new Pages();
	// ���״̬
	private String yesNo;
	//�������Ҹ���
	private String wmqsgs;
	//A�����Ҹ���
	private String ajqsgs;
	//�༶���
	private String bjqk;
	//��ѧ�����
	private String jxjdm;

	private String csrq;
	//��������
	private String drzw;
	//ѧϰ����
	private String xxjl;
	//����Ա���
	private String fdyyj;
	//ѧԺ���
	private String xyshyj;
	//ѧУ���
	private String xxshyj;
	//�������
	private String jfqk;
	//��Ҫ�¼�
	private String zysj;
	//�������ѽ�
	private String jytyj;
	//��ҵȥ��
	private String byqx;
	//������Ṥ�����
	private String kycg;
	//��ѧ�����
	private String jxjlb;
	//���»��������
	private String lshshyj ;
	//��������
	private String sqly;
	
	//δ����༶����
	private String wfpbjdm;
	
	//�ѷ���༶����
	private String yfpbjdm;
	
	//���ñ���
	private String szbl;
	
	//����
	private String me;
	
	//������
	private String shyj;
	
	//�����ƺ�����
	private String rychrs;
	//���п���
	private String yhkh;
	//��������
	private String yhlx;
	private String thly;
		private String zhszcpcjpm;
	private String dycj;
	private String zycj;
	private String tycj;
	private String sbdj;
	private String djchdjxj;
	private String nccz;
	
	//������ʽ
	private String [] njz;
	private String [] pjfsz;
	private String act;
	
	private String queryequals_xn;
	private String queryequals_nj;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_bjdm;
	private String querylike_xh;
	private String querylike_xm;
	private String queryequals_cpzdm;
    private String querylike_xn;
	
	public String getQuerylike_xn() {
		return querylike_xn;
	}

	public void setQuerylike_xn(String querylike_xn) {
		this.querylike_xn = querylike_xn;
	}

	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}

	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}

	public String getQueryequals_nj() {
		return queryequals_nj;
	}

	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}

	public String getQueryequals_xn() {
		return queryequals_xn;
	}

	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}

	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}

	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
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

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getNccz() {
		return nccz;
	}

	public void setNccz(String nccz) {
		this.nccz = nccz;
	}

	public String getSbdj() {
		return sbdj;
	}

	public void setSbdj(String sbdj) {
		this.sbdj = sbdj;
	}

	public String getDjchdjxj() {
		return djchdjxj;
	}

	public void setDjchdjxj(String djchdjxj) {
		this.djchdjxj = djchdjxj;
	}

	public String getZhszcpcjpm() {
		return zhszcpcjpm;
	}

	public void setZhszcpcjpm(String zhszcpcjpm) {
		this.zhszcpcjpm = zhszcpcjpm;
	}

	public String getDycj() {
		return dycj;
	}

	public void setDycj(String dycj) {
		this.dycj = dycj;
	}

	public String getZycj() {
		return zycj;
	}

	public void setZycj(String zycj) {
		this.zycj = zycj;
	}

	public String getTycj() {
		return tycj;
	}

	public void setTycj(String tycj) {
		this.tycj = tycj;
	}

	public String getYhkh() {
		return yhkh;
	}

	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

	public String getYhlx() {
		return yhlx;
	}

	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}

	
	//��˷�ʽ
	private String shfs;
	
	public String getShfs() {
		return shfs;
	}

	public void setShfs(String shfs) {
		this.shfs = shfs;
	}

	public String getBjdm() {
		return bjdm;
	}

	public String getAjqsgs() {
		return ajqsgs;
	}

	public void setAjqsgs(String ajqsgs) {
		this.ajqsgs = ajqsgs;
	}

	public String getBj() {
		return bj;
	}

	public void setBj(String bj) {
		this.bj = bj;
	}
	
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getBjfbl() {
		return bjfbl;
	}

	public void setBjfbl(String bjfbl) {
		this.bjfbl = bjfbl;
	}

	public String getBjpyf() {
		return bjpyf;
	}

	public void setBjpyf(String bjpyf) {
		this.bjpyf = bjpyf;
	}

	public String getBjqk() {
		return bjqk;
	}

	public void setBjqk(String bjqk) {
		this.bjqk = bjqk;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getBmlb() {
		return bmlb;
	}

	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
	}

	public String getByqx() {
		return byqx;
	}

	public void setByqx(String byqx) {
		this.byqx = byqx;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCjlx() {
		return cjlx;
	}

	public void setCjlx(String cjlx) {
		this.cjlx = cjlx;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCprs() {
		return cprs;
	}

	public void setCprs(String cprs) {
		this.cprs = cprs;
	}

	public String getCpzdm() {
		return cpzdm;
	}

	public void setCpzdm(String cpzdm) {
		this.cpzdm = cpzdm;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getDrzw() {
		return drzw;
	}

	public void setDrzw(String drzw) {
		this.drzw = drzw;
	}

	public String getDybl() {
		return dybl;
	}

	public void setDybl(String dybl) {
		this.dybl = dybl;
	}

	public String getFdyyj() {
		return fdyyj;
	}

	public void setFdyyj(String fdyyj) {
		this.fdyyj = fdyyj;
	}

	public String getIsBl() {
		return isBl;
	}

	public void setIsBl(String isBl) {
		this.isBl = isBl;
	}

	public String getIsZds() {
		return isZds;
	}

	public void setIsZds(String isZds) {
		this.isZds = isZds;
	}

	public String getJfqk() {
		return jfqk;
	}

	public void setJfqk(String jfqk) {
		this.jfqk = jfqk;
	}

	public String getJxjbl() {
		return jxjbl;
	}

	public void setJxjbl(String jxjbl) {
		this.jxjbl = jxjbl;
	}

	public String getJxjdm() {
		return jxjdm;
	}

	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}

	public String getJxjlb() {
		return jxjlb;
	}

	public void setJxjlb(String jxjlb) {
		this.jxjlb = jxjlb;
	}

	public String getJxjrs() {
		return jxjrs;
	}

	public void setJxjrs(String jxjrs) {
		this.jxjrs = jxjrs;
	}

	public String getJytyj() {
		return jytyj;
	}

	public void setJytyj(String jytyj) {
		this.jytyj = jytyj;
	}

	public String getKqf() {
		return kqf;
	}

	public void setKqf(String kqf) {
		this.kqf = kqf;
	}

	public String getKqfbl() {
		return kqfbl;
	}

	public void setKqfbl(String kqfbl) {
		this.kqfbl = kqfbl;
	}

	public String getKycg() {
		return kycg;
	}

	public void setKycg(String kycg) {
		this.kycg = kycg;
	}

	public String getLshshyj() {
		return lshshyj;
	}

	public void setLshshyj(String lshshyj) {
		this.lshshyj = lshshyj;
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

	public String getPsfbl() {
		return psfbl;
	}

	public void setPsfbl(String psfbl) {
		this.psfbl = psfbl;
	}

	public String getRychdm() {
		return rychdm;
	}

	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSzbl() {
		return szbl;
	}

	public void setSzbl(String szbl) {
		this.szbl = szbl;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getWfpbjdm() {
		return wfpbjdm;
	}

	public void setWfpbjdm(String wfpbjdm) {
		this.wfpbjdm = wfpbjdm;
	}

	public String getWmqsgs() {
		return wmqsgs;
	}

	public void setWmqsgs(String wmqsgs) {
		this.wmqsgs = wmqsgs;
	}

	public String getWsfbl() {
		return wsfbl;
	}

	public void setWsfbl(String wsfbl) {
		this.wsfbl = wsfbl;
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

	public String getXxjl() {
		return xxjl;
	}

	public void setXxjl(String xxjl) {
		this.xxjl = xxjl;
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

	public String getXyfjf() {
		return xyfjf;
	}

	public void setXyfjf(String xyfjf) {
		this.xyfjf = xyfjf;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getYesNo() {
		return yesNo;
	}

	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}

	public String getYfpbjdm() {
		return yfpbjdm;
	}

	public void setYfpbjdm(String yfpbjdm) {
		this.yfpbjdm = yfpbjdm;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getZpfbl() {
		return zpfbl;
	}

	public void setZpfbl(String zpfbl) {
		this.zpfbl = zpfbl;
	}

	public String getZwpyf() {
		return zwpyf;
	}

	public void setZwpyf(String zwpyf) {
		this.zwpyf = zwpyf;
	}

	public String getZybl() {
		return zybl;
	}

	public void setZybl(String zybl) {
		this.zybl = zybl;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getZysj() {
		return zysj;
	}

	public void setZysj(String zysj) {
		this.zysj = zysj;
	}
	
  

	public String getMe() {
		return me;
	}

	public void setMe(String me) {
		this.me = me;
	}

		public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	
	public String getRychrs() {
		return rychrs;
	}

	public void setRychrs(String rychrs) {
		this.rychrs = rychrs;
	}

	public String getSfyxbj() {
		return sfyxbj;
	}

	public void setSfyxbj(String sfyxbj) {
		this.sfyxbj = sfyxbj;
	}

	public String[] getPjfsz() {
		return pjfsz;
	}

	public void setPjfsz(String[] pjfsz) {
		this.pjfsz = pjfsz;
	}

	public String[] getNjz() {
		return njz;
	}

	public void setNjz(String[] njz) {
		this.njz = njz;
	}

	public String getQueryequals_cpzdm() {
		return queryequals_cpzdm;
	}

	public void setQueryequals_cpzdm(String queryequals_cpzdm) {
		this.queryequals_cpzdm = queryequals_cpzdm;
	}

	public String getJxjxn() {
		return jxjxn;
	}

	public void setJxjxn(String jxjxn) {
		this.jxjxn = jxjxn;
	}

	public String getThly() {
		return thly;
	}

	public void setThly(String thly) {
		this.thly = thly;
	}


}