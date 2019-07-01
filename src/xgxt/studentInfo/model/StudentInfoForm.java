package xgxt.studentInfo.model;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

@SuppressWarnings("serial")
public class StudentInfoForm extends ActionForm implements Serializable {
	
	private String[] xszd;
	
	
	SearchModel searchModel = new SearchModel();
	
	private String xycxfw = "ydqbxy";//ѧ���춯ѧԺ�û���ѯ��Χ��Ĭ���춯ǰ��ѧԺ
	
	private String queryequals_ydqxydm;
	private String queryequals_ydqzydm;
	private String queryequals_ydqbjdm;
	private String queryequals_ydqnj;
	private String querylike_xh;
	private String querylike_xm;
	private String queryequals_ydlbdm;
	private String querygreaterequal_ydrq;
	private String querylessequal_ydrq;
	private String querygreaterequal_ydjzrq;
	private String querylessequal_ydjzrq;
	private String queryequals_fdysh;
	private String queryequals_xysh;
	private String queryequals_xxsh;
	private String[] xtgwidArr;
	private String[] shztArr;
	private String xsxgxxsfsh;//ѧ���޸���Ϣ�Ƿ����
	private String save_shlcid;
	private String shyj;//������
	private String xtgwid;//ϵͳ��λ���
	private String shjg;//��˽��
	private String[] primarykey_cbv;//����
	
	private String save_ydxh;
	private String isFdy; // ����Ա

	private String isBzr; // ������

	private String userName; // �û���

	private String sffx; // �Ƿ�ѧ

	private String ydrqks; // �춯���ڿ�ʼ

	private String ydrqjs; // �춯���ڽ���

	private String ydjzrqks; // �춯��ֹ���ڿ�ʼ

	private String ydjzrqjs; // �춯��ֹ���ڽ���

	private String ydjzrq; // �춯��ֹ����

	private String xsxgxxsh;// �Ƿ�����ѧ����Ϣ�޸���˹��ܱ�־

	private String dzxxqdm;// �Ƿ����õ�ַ��Ϣȡ���빦�ܱ�־

	private String havexsqx;

	private String cjbgdffsj;

	private String kxsj;

	private String bdsj;

	private String bkkssj;

	private String bkjssj;

	private String xzf;

	private String xydm;

	private String xymc;

	private String zydm;

	private String zymc;

	private String bjdm;

	private String bjmc;

	private String xh;

	private String xm;

	private String nj;

	private String ydlbmc;

	private String ydlbdm;

	private String lydq;

	private String lxdh;

	private String bz;

	private String xz;

	private String ydrq;

	private String xjzt;

	private String cc;

	private String ydxh;

	private String clwh;

	private String ydyy;

	private String ydsm;

	private String ydqxymc;

	private String ydqzymc;

	private String ydqbjmc;

	private String ydqxydm;

	private String ydqzydm;

	private String ydqbjdm;

	private String ydqxz;

	private String ydqxjztm;

	private String ydhxymc;

	private String ydhzymc;

	private String ydhbjmc;

	private String ydhxydm;

	private String ydhzydm;

	private String ydhbjdm;

	private String ydhxz;

	private String ydhxjztm;

	private String ydhnj;

	private String ydqnj;

	private String xmdm;

	private String kssj;

	private String kssjH;

	private String kssjM;

	private String kssjS;

	private String jssj;

	private String jssjH;

	private String jssjM;

	private String jssjS;

	private String nd;

	private String xn;

	private String xq;

	private String xb;

	private String zzmm;// ������ò

	private String jtzz;// ��ͥסַ

	private String gj;// ����

	private String cgyy;// ����ԭ��

	private String jjdbqk;// ���õ������

	private String sqrq;// ��������

	private String xxyj;// ѧУ���

	private String xyyj;// ѧԺ���

	private String xscyj;// ѧ�������

	private String jbbmyj;// ���첿�����

	private String sqly;// ��������

	private String sjhm;// �ֻ�����

	private String qtdh;// �����绰

	private String dwdh;// ��λ�绰

	private String jtdh;// ��ͥ�绰

	private String email;

	private String blfs;// �������

	private String gsyq;// ��ʽҪ��

	private String cgrq;// ��������

	private String ggrq;// �������

	private String lxfs;// ��ϵ��ʽ

	private String bjrs;// �༶����

	private String zdmc;// �����

	private String zdsjcd;// �ֶ�ʵ�ʳ���

	private String qsw;// ��ʼλ

	private String ws;// λ��

	private String sfbl;// �Ƿ���

	private String fbzd = "zydm";// �ְ��ֶ�

	private String isSz;// �Ƿ���Ҫʱ������
	
	private String jkzk;//����״��
	
	private String jg;//����
	
	private String syd;//��Դ��;
	
	private String hkszd;//�������ڵ�;

	/** �ϲ��Ƽ�ѧԺ */
	private String bzrkssj;// �����ο�ʼʱ��

	private String bzrjssj;// �����ν���ʱ��

	private String bzrkssjH;// �����ο�ʼʱ��H

	private String bzrkssjM;// �����ο�ʼʱ��M

	private String bzrkssjS;// �����ο�ʼʱ��S

	private String bzrjssjH;// �����ν���ʱ��H

	private String bzrjssjM;// �����ν���ʱ��M

	private String bzrjssjS;// �����ν���ʱ��S

	/** �㶫����ѧԺ */

	private String csrq;

	private String jtdz;

	private String ydxx;

	private String ydzy;

	private String xdzy;

	private String sxjl;

	private String jnzs;

	/** �Ϻ����� */

	private String wjh;// �ļ���

	private String lrrq;// ¼������

	private String wjlx;// �ļ�����

	private String mb;// ģ��

	private String gdzldm;// �鵵���ϴ���

	private String hkssqx;// ������������

	private String hkssjd;// ���������ֵ�

	private String hkxxdz;// ������ϸ��ַ

	private String rxsj;// ��Уʱ��

	private String bysj;// ��ҵʱ��

	private String dyym;// ��Ӧҳ��

	/** ******��������Ժ************** */
	private String ddqkdm;// �����������

	private String ssda;// ��������

	/** ****���ݴ�ѧ***** */

	private String sfzx;// �Ƿ���У

	/** *****�Ϻ�����********* */
	private String zmlx;// ֤������

	/** *****�й��ش�********* */
	private String xszt;// ѧ��״̬

	private String sfqq;// �Ƿ���ȫ

	private String dm;
	
	private String tjbcyfs;
	
	private String xjlb;//ѧ�����

	/** *****ͳ������********* */
	private String[] tjzd;// ͳ���ֶ�

	private String[] tjzdz;// ͳ���ֶ�ֵ

	private String[] xsmc;// ��ʾ����
	
	private String cwbh; // ������

	public String[] getXsmc() {
		return xsmc;
	}

	public void setXsmc(String[] xsmc) {
		this.xsmc = xsmc;
	}

	public String[] getTjzd() {
		return tjzd;
	}

	public void setTjzd(String[] tjzd) {
		this.tjzd = tjzd;
	}

	public String[] getTjzdz() {
		return tjzdz;
	}

	public void setTjzdz(String[] tjzdz) {
		this.tjzdz = tjzdz;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSffx() {
		return sffx;
	}

	public void setSffx(String sffx) {
		this.sffx = sffx;
	}

	public String getXszt() {
		return xszt;
	}

	public void setXszt(String xszt) {
		this.xszt = xszt;
	}

	public String getSfzx() {
		return sfzx;
	}

	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}

	public String getSsda() {
		return ssda;
	}

	public void setSsda(String ssda) {
		this.ssda = ssda;
	}

	public String getBysj() {
		return bysj;
	}

	public void setBysj(String bysj) {
		this.bysj = bysj;
	}

	public String getHkssjd() {
		return hkssjd;
	}

	public void setHkssjd(String hkssjd) {
		this.hkssjd = hkssjd;
	}

	public String getHkssqx() {
		return hkssqx;
	}

	public void setHkssqx(String hkssqx) {
		this.hkssqx = hkssqx;
	}

	public String getHkxxdz() {
		return hkxxdz;
	}

	public void setHkxxdz(String hkxxdz) {
		this.hkxxdz = hkxxdz;
	}

	public String getRxsj() {
		return rxsj;
	}

	public void setRxsj(String rxsj) {
		this.rxsj = rxsj;
	}

	public String getGdzldm() {
		return gdzldm;
	}

	public void setGdzldm(String gdzldm) {
		this.gdzldm = gdzldm;
	}

	public String getMb() {
		return mb;
	}

	public void setMb(String mb) {
		this.mb = mb;
	}

	public String getWjlx() {
		return wjlx;
	}

	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String lrrq) {
		this.lrrq = lrrq;
	}

	public String getWjh() {
		return wjh;
	}

	public void setWjh(String wjh) {
		this.wjh = wjh;
	}

	public String getBzrjssj() {
		return bzrjssj;
	}

	public void setBzrjssj(String bzrjssj) {
		this.bzrjssj = bzrjssj;
	}

	public String getBzrjssjH() {
		return bzrjssjH;
	}

	public void setBzrjssjH(String bzrjssjH) {
		this.bzrjssjH = bzrjssjH;
	}

	public String getBzrjssjM() {
		return bzrjssjM;
	}

	public void setBzrjssjM(String bzrjssjM) {
		this.bzrjssjM = bzrjssjM;
	}

	public String getBzrjssjS() {
		return bzrjssjS;
	}

	public void setBzrjssjS(String bzrjssjS) {
		this.bzrjssjS = bzrjssjS;
	}

	public String getBzrkssj() {
		return bzrkssj;
	}

	public void setBzrkssj(String bzrkssj) {
		this.bzrkssj = bzrkssj;
	}

	public String getBzrkssjH() {
		return bzrkssjH;
	}

	public void setBzrkssjH(String bzrkssjH) {
		this.bzrkssjH = bzrkssjH;
	}

	public String getBzrkssjM() {
		return bzrkssjM;
	}

	public void setBzrkssjM(String bzrkssjM) {
		this.bzrkssjM = bzrkssjM;
	}

	public String getBzrkssjS() {
		return bzrkssjS;
	}

	public void setBzrkssjS(String bzrkssjS) {
		this.bzrkssjS = bzrkssjS;
	}

	public String getFbzd() {
		return fbzd;
	}

	public void setFbzd(String fbzd) {
		this.fbzd = fbzd;
	}

	public String getSfbl() {
		return sfbl;
	}

	public void setSfbl(String sfbl) {
		this.sfbl = sfbl;
	}

	public String getQsw() {
		return qsw;
	}

	public void setQsw(String qsw) {
		this.qsw = qsw;
	}

	public String getWs() {
		return ws;
	}

	public void setWs(String ws) {
		this.ws = ws;
	}

	public String getZdmc() {
		return zdmc;
	}

	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}

	public String getZdsjcd() {
		return zdsjcd;
	}

	public void setZdsjcd(String zdsjcd) {
		this.zdsjcd = zdsjcd;
	}

	public String getBjrs() {
		return bjrs;
	}

	public void setBjrs(String bjrs) {
		this.bjrs = bjrs;
	}

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getJssjH() {
		return jssjH;
	}

	public void setJssjH(String jssjH) {
		this.jssjH = jssjH;
	}

	public String getJssjM() {
		return jssjM;
	}

	public void setJssjM(String jssjM) {
		this.jssjM = jssjM;
	}

	public String getJssjS() {
		return jssjS;
	}

	public void setJssjS(String jssjS) {
		this.jssjS = jssjS;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getKssjH() {
		return kssjH;
	}

	public void setKssjH(String kssjH) {
		this.kssjH = kssjH;
	}

	public String getKssjM() {
		return kssjM;
	}

	public void setKssjM(String kssjM) {
		this.kssjM = kssjM;
	}

	public String getKssjS() {
		return kssjS;
	}

	public void setKssjS(String kssjS) {
		this.kssjS = kssjS;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getNj() {
		return this.nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getBjdm() {
		return this.bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getZydm() {
		return this.zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getXydm() {
		return this.xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getLydq() {
		return lydq;
	}

	public void setLydq(String lydq) {
		this.lydq = lydq;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
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

	public String getClwh() {
		return clwh;
	}

	public void setClwh(String clwh) {
		this.clwh = clwh;
	}

	public String getYdhbjmc() {
		return ydhbjmc;
	}

	public void setYdhbjmc(String ydhbjmc) {
		this.ydhbjmc = ydhbjmc;
	}

	public String getYdhxjztm() {
		return ydhxjztm;
	}

	public void setYdhxjztm(String ydhxjztm) {
		this.ydhxjztm = ydhxjztm;
	}

	public String getYdhxymc() {
		return ydhxymc;
	}

	public void setYdhxymc(String ydhxymc) {
		this.ydhxymc = ydhxymc;
	}

	public String getYdhxz() {
		return ydhxz;
	}

	public void setYdhxz(String ydhxz) {
		this.ydhxz = ydhxz;
	}

	public String getYdhzymc() {
		return ydhzymc;
	}

	public void setYdhzymc(String ydhzymc) {
		this.ydhzymc = ydhzymc;
	}

	public String getYdqbjmc() {
		return ydqbjmc;
	}

	public void setYdqbjmc(String ydqbjmc) {
		this.ydqbjmc = ydqbjmc;
	}

	public String getYdqxjztm() {
		return ydqxjztm;
	}

	public void setYdqxjztm(String ydqxjztm) {
		this.ydqxjztm = ydqxjztm;
	}

	public String getYdqxymc() {
		return ydqxymc;
	}

	public void setYdqxymc(String ydqxymc) {
		this.ydqxymc = ydqxymc;
	}

	public String getYdqxz() {
		return ydqxz;
	}

	public void setYdqxz(String ydqxz) {
		this.ydqxz = ydqxz;
	}

	public String getYdqzymc() {
		return ydqzymc;
	}

	public void setYdqzymc(String ydqzymc) {
		this.ydqzymc = ydqzymc;
	}

	public String getYdsm() {
		return ydsm;
	}

	public void setYdsm(String ydsm) {
		this.ydsm = ydsm;
	}

	public String getYdyy() {
		return ydyy;
	}

	public void setYdyy(String ydyy) {
		this.ydyy = ydyy;
	}

	public String getYdhnj() {
		return ydhnj;
	}

	public void setYdhnj(String ydhnj) {
		this.ydhnj = ydhnj;
	}

	public String getYdqnj() {
		return ydqnj;
	}

	public void setYdqnj(String ydqnj) {
		this.ydqnj = ydqnj;
	}

	public String getYdhbjdm() {
		return ydhbjdm;
	}

	public void setYdhbjdm(String ydhbjdm) {
		this.ydhbjdm = ydhbjdm;
	}

	public String getYdhxydm() {
		return ydhxydm;
	}

	public void setYdhxydm(String ydhxydm) {
		this.ydhxydm = ydhxydm;
	}

	public String getYdhzydm() {
		return ydhzydm;
	}

	public void setYdhzydm(String ydhzydm) {
		this.ydhzydm = ydhzydm;
	}

	public String getYdqbjdm() {
		return ydqbjdm;
	}

	public void setYdqbjdm(String ydqbjdm) {
		this.ydqbjdm = ydqbjdm;
	}

	public String getYdqxydm() {
		return ydqxydm;
	}

	public void setYdqxydm(String ydqxydm) {
		this.ydqxydm = ydqxydm;
	}

	public String getYdqzydm() {
		return ydqzydm;
	}

	public void setYdqzydm(String ydqzydm) {
		this.ydqzydm = ydqzydm;
	}

	public String getYdlbdm() {
		return ydlbdm;
	}

	public void setYdlbdm(String ydlbdm) {
		this.ydlbdm = ydlbdm;
	}

	public String getYdlbmc() {
		return ydlbmc;
	}

	public void setYdlbmc(String ydlbmc) {
		this.ydlbmc = ydlbmc;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getYdxh() {
		return ydxh;
	}

	public void setYdxh(String ydxh) {
		this.ydxh = ydxh;
	}

	public String getYdrq() {
		return ydrq;
	}

	public void setYdrq(String ydrq) {
		this.ydrq = ydrq;
	}

	public String getXjzt() {
		return xjzt;
	}

	public void setXjzt(String xjzt) {
		this.xjzt = xjzt;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
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

	public String getCgyy() {
		return cgyy;
	}

	public void setCgyy(String cgyy) {
		this.cgyy = cgyy;
	}

	public String getGj() {
		return gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getJjdbqk() {
		return jjdbqk;
	}

	public void setJjdbqk(String jjdbqk) {
		this.jjdbqk = jjdbqk;
	}

	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	public String getSqrq() {
		return sqrq;
	}

	public void setSqrq(String sqrq) {
		this.sqrq = sqrq;
	}

	public String getZzmm() {
		return zzmm;
	}

	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}

	public String getQtdh() {
		return qtdh;
	}

	public void setQtdh(String qtdh) {
		this.qtdh = qtdh;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getDwdh() {
		return dwdh;
	}

	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJtdh() {
		return jtdh;
	}

	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	public String getXscyj() {
		return xscyj;
	}

	public void setXscyj(String xscyj) {
		this.xscyj = xscyj;
	}

	public String getXxyj() {
		return xxyj;
	}

	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}

	public String getXyyj() {
		return xyyj;
	}

	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getJbbmyj() {
		return jbbmyj;
	}

	public void setJbbmyj(String jbbmyj) {
		this.jbbmyj = jbbmyj;
	}

	public String getBlfs() {
		return blfs;
	}

	public void setBlfs(String blfs) {
		this.blfs = blfs;
	}

	public String getCgrq() {
		return cgrq;
	}

	public void setCgrq(String cgrq) {
		this.cgrq = cgrq;
	}

	public String getGgrq() {
		return ggrq;
	}

	public void setGgrq(String ggrq) {
		this.ggrq = ggrq;
	}

	public String getGsyq() {
		return gsyq;
	}

	public void setGsyq(String gsyq) {
		this.gsyq = gsyq;
	}

	// /
	//
	Pages pages = new Pages();

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getJnzs() {
		return jnzs;
	}

	public void setJnzs(String jnzs) {
		this.jnzs = jnzs;
	}

	public String getJtdz() {
		return jtdz;
	}

	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}

	public String getSxjl() {
		return sxjl;
	}

	public void setSxjl(String sxjl) {
		this.sxjl = sxjl;
	}

	public String getXdzy() {
		return xdzy;
	}

	public void setXdzy(String xdzy) {
		this.xdzy = xdzy;
	}

	public String getYdxx() {
		return ydxx;
	}

	public void setYdxx(String ydxx) {
		this.ydxx = ydxx;
	}

	public String getYdzy() {
		return ydzy;
	}

	public void setYdzy(String ydzy) {
		this.ydzy = ydzy;
	}

	public String getDdqkdm() {
		return ddqkdm;
	}

	public void setDdqkdm(String ddqkdm) {
		this.ddqkdm = ddqkdm;
	}

	public String getDyym() {
		return dyym;
	}

	public void setDyym(String dyym) {
		this.dyym = dyym;
	}

	public String getZmlx() {
		return zmlx;
	}

	public void setZmlx(String zmlx) {
		this.zmlx = zmlx;
	}

	public String getIsSz() {
		return isSz;
	}

	public void setIsSz(String isSz) {
		this.isSz = isSz;
	}

	public String getCjbgdffsj() {
		return cjbgdffsj;
	}

	public void setCjbgdffsj(String cjbgdffsj) {
		this.cjbgdffsj = cjbgdffsj;
	}

	public String getKxsj() {
		return kxsj;
	}

	public void setKxsj(String kxsj) {
		this.kxsj = kxsj;
	}

	public String getBdsj() {
		return bdsj;
	}

	public void setBdsj(String bdsj) {
		this.bdsj = bdsj;
	}

	public String getXzf() {
		return xzf;
	}

	public void setXzf(String xzf) {
		this.xzf = xzf;
	}

	public String getBkkssj() {
		return bkkssj;
	}

	public void setBkkssj(String bkkssj) {
		this.bkkssj = bkkssj;
	}

	public String getBkjssj() {
		return bkjssj;
	}

	public void setBkjssj(String bkjssj) {
		this.bkjssj = bkjssj;
	}

	public String getSfqq() {
		return sfqq;
	}

	public void setSfqq(String sfqq) {
		this.sfqq = sfqq;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getHavexsqx() {
		return havexsqx;
	}

	public void setHavexsqx(String havexsqx) {
		this.havexsqx = havexsqx;
	}

	public String getXsxgxxsh() {
		return xsxgxxsh;
	}

	public void setXsxgxxsh(String xsxgxxsh) {
		this.xsxgxxsh = xsxgxxsh;
	}

	public String getDzxxqdm() {
		return dzxxqdm;
	}

	public void setDzxxqdm(String dzxxqdm) {
		this.dzxxqdm = dzxxqdm;
	}

	public String getYdjzrq() {
		return ydjzrq;
	}

	public void setYdjzrq(String ydjzrq) {
		this.ydjzrq = ydjzrq;
	}

	public String getYdrqks() {
		return ydrqks;
	}

	public void setYdrqks(String ydrqks) {
		this.ydrqks = ydrqks;
	}

	public String getYdrqjs() {
		return ydrqjs;
	}

	public void setYdrqjs(String ydrqjs) {
		this.ydrqjs = ydrqjs;
	}

	public String getYdjzrqks() {
		return ydjzrqks;
	}

	public void setYdjzrqks(String ydjzrqks) {
		this.ydjzrqks = ydjzrqks;
	}

	public String getYdjzrqjs() {
		return ydjzrqjs;
	}

	public void setYdjzrqjs(String ydjzrqjs) {
		this.ydjzrqjs = ydjzrqjs;
	}

	public String getIsFdy() {
		return isFdy;
	}

	public void setIsFdy(String isFdy) {
		this.isFdy = isFdy;
	}

	public String getIsBzr() {
		return isBzr;
	}

	public void setIsBzr(String isBzr) {
		this.isBzr = isBzr;
	}

	public String getSave_ydxh() {
		return save_ydxh;
	}

	public void setSave_ydxh(String save_ydxh) {
		this.save_ydxh = save_ydxh;
	}

	public String getQueryequals_ydqxydm() {
		return queryequals_ydqxydm;
	}

	public void setQueryequals_ydqxydm(String queryequals_ydqxydm) {
		this.queryequals_ydqxydm = queryequals_ydqxydm;
	}

	public String getQueryequals_ydqzydm() {
		return queryequals_ydqzydm;
	}

	public void setQueryequals_ydqzydm(String queryequals_ydqzydm) {
		this.queryequals_ydqzydm = queryequals_ydqzydm;
	}

	public String getQueryequals_ydqbjdm() {
		return queryequals_ydqbjdm;
	}

	public void setQueryequals_ydqbjdm(String queryequals_ydqbjdm) {
		this.queryequals_ydqbjdm = queryequals_ydqbjdm;
	}

	public String getQueryequals_ydqnj() {
		return queryequals_ydqnj;
	}

	public void setQueryequals_ydqnj(String queryequals_ydqnj) {
		this.queryequals_ydqnj = queryequals_ydqnj;
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

	public String getQueryequals_ydlbdm() {
		return queryequals_ydlbdm;
	}

	public void setQueryequals_ydlbdm(String queryequals_ydlbdm) {
		this.queryequals_ydlbdm = queryequals_ydlbdm;
	}

	public String getQuerygreaterequal_ydrq() {
		return querygreaterequal_ydrq;
	}

	public void setQuerygreaterequal_ydrq(String querygreaterequal_ydrq) {
		this.querygreaterequal_ydrq = querygreaterequal_ydrq;
	}

	public String getQuerylessequal_ydrq() {
		return querylessequal_ydrq;
	}

	public void setQuerylessequal_ydrq(String querylessequal_ydrq) {
		this.querylessequal_ydrq = querylessequal_ydrq;
	}

	public String getQuerygreaterequal_ydjzrq() {
		return querygreaterequal_ydjzrq;
	}

	public void setQuerygreaterequal_ydjzrq(String querygreaterequal_ydjzrq) {
		this.querygreaterequal_ydjzrq = querygreaterequal_ydjzrq;
	}

	public String getQuerylessequal_ydjzrq() {
		return querylessequal_ydjzrq;
	}

	public void setQuerylessequal_ydjzrq(String querylessequal_ydjzrq) {
		this.querylessequal_ydjzrq = querylessequal_ydjzrq;
	}

	public String getQueryequals_fdysh() {
		return queryequals_fdysh;
	}

	public void setQueryequals_fdysh(String queryequals_fdysh) {
		this.queryequals_fdysh = queryequals_fdysh;
	}

	public String getQueryequals_xysh() {
		return queryequals_xysh;
	}

	public void setQueryequals_xysh(String queryequals_xysh) {
		this.queryequals_xysh = queryequals_xysh;
	}

	public String getQueryequals_xxsh() {
		return queryequals_xxsh;
	}

	public void setQueryequals_xxsh(String queryequals_xxsh) {
		this.queryequals_xxsh = queryequals_xxsh;
	}

	public String[] getXtgwidArr() {
		return xtgwidArr;
	}

	public void setXtgwidArr(String[] xtgwidArr) {
		this.xtgwidArr = xtgwidArr;
	}

	public String[] getShztArr() {
		return shztArr;
	}

	public void setShztArr(String[] shztArr) {
		this.shztArr = shztArr;
	}

	public String getTjbcyfs() {
		return tjbcyfs;
	}

	public void setTjbcyfs(String tjbcyfs) {
		this.tjbcyfs = tjbcyfs;
	}

	public String getXsxgxxsfsh() {
		return xsxgxxsfsh;
	}

	public void setXsxgxxsfsh(String xsxgxxsfsh) {
		this.xsxgxxsfsh = xsxgxxsfsh;
	}

	public String getSave_shlcid() {
		return save_shlcid;
	}

	public void setSave_shlcid(String save_shlcid) {
		this.save_shlcid = save_shlcid;
	}

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getXtgwid() {
		return xtgwid;
	}

	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String[] getPrimarykey_cbv() {
		return primarykey_cbv;
	}

	public void setPrimarykey_cbv(String[] primarykey_cbv) {
		this.primarykey_cbv = primarykey_cbv;
	}

	public String[] getXszd() {
		return xszd;
	}

	public void setXszd(String[] xszd) {
		this.xszd = xszd;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public String getXycxfw() {
		return xycxfw;
	}

	public void setXycxfw(String xycxfw) {
		this.xycxfw = xycxfw;
	}

	public String getXjlb() {
		return xjlb;
	}

	public void setXjlb(String xjlb) {
		this.xjlb = xjlb;
	}

	public String getJkzk() {
		return jkzk;
	}

	public void setJkzk(String jkzk) {
		this.jkzk = jkzk;
	}

	public String getHkszd() {
		return hkszd;
	}

	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getSyd() {
		return syd;
	}

	public void setSyd(String syd) {
		this.syd = syd;
	}

	public String getCwbh() {
		return cwbh;
	}

	public void setCwbh(String cwbh) {
		this.cwbh = cwbh;
	}	
	
}
