/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-7 ����10:18:01 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.bysxx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ����Ϣ����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�����[����:1104]
 * @ʱ�䣺 2014-7-7 ����10:14:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BysXxForm extends ActionForm{
	private static final long serialVersionUID = 7187713079641588815L;
	private String xh;
	private String xm;
	private String nj;
	private String bynd;
	private String xydm;
	private String zydm;
	private String bjdm;
	private String pyccmc;
	private String jtcyfzqk;//��ͥ��Ա�������
	private String sxdwgznr;//��ҵʵϰ��λ����������
	private String bysjtm;//��ҵ�����Ŀ
	
	private String qtyyqk;//�����������
	private String brgzzy; //���˹���־Ը
	private String zwjd;//���Ҽ���
	private String type;
	
	private User user;
	private String jgs;// ����ʡ
	private String jgshi;// ������
	private String jgx;// ������
	private String ssbh;// ������
	private String rxnj;// ��ѧ�꼶
	private String nfby;// �ܷ��ҵ
	private String sfzc;// �Ƿ�ע��
	private String dasfyl;// �����Ƿ�����
	private String daylyy;// ��������ԭ��
	private String yxdm;// ԺУ����
	private String sfzz;// �Ƿ���ְ
	private String sfsf;// �Ƿ�ʦ��
	private String sfdl;// �Ƿ����
	private String dxhwp;// �����ί��
	private String bysj;// ��ҵʱ��
	private String zxwyyzdm;// �����������ִ���
	private String wydj;// ����ȼ�
	private String jsjdj;// ������ȼ�
	private String lxdz;// ��ϵ��ַ
	private String yzbm;// ��������
	private String shzw;// ���ְ��
	private String jypx;// ������ѵ
	private String xmsj;// ��Ŀʵ��
	private String zgzs;// �ʸ�֤��
	private String jljn;// ��������
	private String sybz1;// ��Դ��ע1
	private String sybz2;// ��Դ��ע2
	private String sybz3;// ��Դ��ע3
	private String xldm;// ѧ������
	private String zkzh;// ׼��֤��
	private String grjl;// ���˼���
	private String sfcj;// �Ƿ�м�/1��,0��
	private String ssch;// ���ᴲ��
	private String rzrq;// ס������
	private String zsjzrq;// ס�޽�ֹ����
	private String qsdh;// ���ҵ绰
	private String ykth;// һ��ͨ��
	private String yhkh;// ���п���
	private String xslb;// ѧ��������
	private String xslx;// ѧ�����ʹ���
	private String sfbys;// �Ƿ��ҵ��
	private String yhdm;// ���д���
	private String hkshen;// ��������ʡ
	private String hkshi;// ����������
	private String hkxian;// ����������
	private String zcsxhm;// ע��˳�����
	private String rxqwhcd;// ��ѧǰ�Ļ��̶�
	private String xsqrxxbz;// ѧ��ȷ����Ϣ��־
	private String dah;// ������
	private String ylbxh;// ҽ�Ʊ��պ�
	private String rxqdwdz;// ԭ��ҵѧԺͨ�ŵ�ַ
	private String rxqdwyb;// ԭ��ҵѧԺ�ʱ�
	private String rxqdwdh;// ��ѧǰ��λ�绰
	private String gzbx;// ���б���
	private String sfgat;// �Ƿ�۰�̨��
	private String sfssmz;// �Ƿ��������
	private String sfzd;// �Ƿ��߶�
	private String syds;// ��Դ��ʡ
	private String sydshi;// ��Դ����
	private String sydx;// ��Դ����
	private String byzh;// ��ҵ֤��
	private String xjh;// ѧ����
	private String jrzzmmrq;// ����������ò����
	private String sfhq;// �Ƿ���
	private String csds;// ������ʡ
	private String csdshi;// ��������
	private String csdxian;// ��������
	private String xy;// ѧԺ����
	private String xymc;// ѧԺ����
	private String zymc;// רҵ����
	private String bjmc;// �༶����
	private String bz;// ��ע
	private String xmpy;// ����ƴ��
	private String syd;// ��Դ��
	private String csrq;// ��������
	private String sfzh;// ���֤����
	private String xb;// �Ա�
	private String mz;// �������
	private String zzmm;// ������ò����
	private String lxdh;// ��ϵ�绰
	private String dzyx;// ��������
	private String cym;// ������
	private String sg;// ���
	private String tz;// ����
	private String tc;// �س�
	private String kslb;// �������
	private String rxfs;// ��ѧ��ʽ
	private String pyfs;// ������ʽ
	private String pycc;// �������
	private String xjlb;// ѧ�����
	private String xszp;// ѧ����Ƭ
	private String xsgkzp;//ѧ���߿���Ƭ
	private String xjztm;// ѧ��״̬
	private String xz;// ѧ��
	private String whcd;// �Ļ��̶�
	private String rxqdw;// ԭ��ҵѧԺ
	private String jtdh;// ��ͥ�绰
	private String jrgqtsj;// ���빲����ʱ��
	private String jrgcdsj;// ���빲����ʱ��
	private String jtcygc;// ��ͥ��Ա����
	private String jlcfjl;// �������־���
	private String jkzk;// ���޲�ʷ
	private String jtdz;// ��ͥ��ϸ��ַ
	private String jtyb;// ��ͥ�ʱ�
	private String jg;// ����
	private String xx;// Ѫ��
	private String ah;// ����
	private String sfdk;// �Ƿ����
	private String shgxxm1;// ����ϵ����1
	private String shgxgx1;// ����ϵ��ϵ1
	private String shgxgzdw1;// ����ϵ������λ1
	private String shgxzw1;// ����ϵְ��1
	private String shgxdwdh1;// ����ϵ��λ�绰1
	private String shgxsjhm1;// ����ϵ�ֻ�����1
	private String shgxxm2;// ����ϵ����2
	private String shgxgx2;// ����ϵ��ϵ2
	private String shgxgzdw2;// ����ϵ������λ2
	private String shgxzw2;// ����ϵְ��2
	private String shgxdwdh2;// ����ϵ��λ�绰2
	private String shgxsjhm2;// ����ϵ�ֻ�����2
	private String jtqkjj;// ��ͥ������
	private String jtjjqk;// ��ͥ�������
	private String sjhm;// �ֻ�����
	private String byxx;// ��ҵ��Ϣ
	private String kh;// ����
	private String rxrq;// ��ѧ����
	private String fdyxm;// ����Ա����
	private String gkcj;// �߿��ɼ�
	private String qqhm;// QQ����
	private String hkxz;// ��������
	private String zyjb;// רҵ����
	private String hkszd;// �������ڵ�
	private String ssyq;// ����Է��
	private String ssld;// ����¥��
	private String jtdzs;// ��ͥ��ַʡ
	private String jtdzx;// ��ͥ��ַ����
	private String sfzsb;// �Ƿ�ר����
	private String sfzfx;// �Ƿ��ڷ�У
	private String zjdm;// �ڽ̴���
	private String sfyby;// �Ƿ��ѱ�ҵ
	
	private String sfzx;// �Ƿ���У
	private String zw;// ְ��
	private String thbs;// �滻��ʶ
	private String dybj;// ��ӡ���
	private String shbj;// ��˱��
	private String xwzsxlh;// ѧλ֤�����к�
	private String xwzsbh;// ѧλ֤����
	private String xw;// ѧλ
	private String xzxm;// У������
	private String zsxlh;// ֤�����к�
	private String zsbh;// ֤����
	private String bjyjl;// �Ͻ�ҵ����
	private String csd;// ��������
	private String zsjj;// ��������
	private String xxxs;// ѧϰ��ʽ
	private String bxlx;// ��ѧ����
	private String bxxs;// ��ѧ��ʽ
	private String fxzyfx;// ����רҵ����
	private String fxzy;// ����רҵ
	private String zylb;// רҵ���
	private String dqszj;// ��ǰ���ڼ�
	private String pyfx;// ��������
	private String zyfx;// רҵ����
	private String xxszd;// ѧУ���ڵ�
	private String ksh;// ������
	private String xxfx;// ѧϰ����
	private String zslb;// �������
	private String gj;// ����
	private String sfjh;// �Ƿ���
	private String ccqj;// �˳�����
	private String byzffztdm;// ��ҵ֤����״̬����
	private String xwzsxxdz;// У��ס����ϸ��ַ
	private String zd1;
	private String zd2; 
	private String zd3;
	private String zd4;
	private String zd5;

	private String shzt;// ���״̬
	private String sfxyqgzx;//�Ƿ���Ҫ�ڹ���ѧ
	private String xgyx;// �������
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the jtcyfzqk
	 */
	public String getJtcyfzqk() {
		return jtcyfzqk;
	}
	/**
	 * @param jtcyfzqkҪ���õ� jtcyfzqk
	 */
	public void setJtcyfzqk(String jtcyfzqk) {
		this.jtcyfzqk = jtcyfzqk;
	}
	/**
	 * @return the sxdwgznr
	 */
	public String getSxdwgznr() {
		return sxdwgznr;
	}
	/**
	 * @param sxdwgznrҪ���õ� sxdwgznr
	 */
	public void setSxdwgznr(String sxdwgznr) {
		this.sxdwgznr = sxdwgznr;
	}
	/**
	 * @return the bysjtm
	 */
	public String getBysjtm() {
		return bysjtm;
	}
	/**
	 * @param bysjtmҪ���õ� bysjtm
	 */
	public void setBysjtm(String bysjtm) {
		this.bysjtm = bysjtm;
	}
	/**
	 * @return the tc
	 */
	public String getTc() {
		return tc;
	}
	/**
	 * @param tcҪ���õ� tc
	 */
	public void setTc(String tc) {
		this.tc = tc;
	}
	/**
	 * @return the qtyyqk
	 */
	public String getQtyyqk() {
		return qtyyqk;
	}
	/**
	 * @param qtyyqkҪ���õ� qtyyqk
	 */
	public void setQtyyqk(String qtyyqk) {
		this.qtyyqk = qtyyqk;
	}
	/**
	 * @return the brgzzy
	 */
	public String getBrgzzy() {
		return brgzzy;
	}
	/**
	 * @param brgzzyҪ���õ� brgzzy
	 */
	public void setBrgzzy(String brgzzy) {
		this.brgzzy = brgzzy;
	}
	/**
	 * @return the zwjd
	 */
	public String getZwjd() {
		return zwjd;
	}
	/**
	 * @param zwjdҪ���õ� zwjd
	 */
	public void setZwjd(String zwjd) {
		this.zwjd = zwjd;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModelҪ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModelҪ���õ� exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xbҪ���õ� xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param njҪ���õ� nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the bynd
	 */
	public String getBynd() {
		return bynd;
	}
	/**
	 * @param byndҪ���õ� bynd
	 */
	public void setBynd(String bynd) {
		this.bynd = bynd;
	}
	/**
	 * @return the xy
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xyҪ���õ� xy
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydmҪ���õ� xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @param zydmҪ���õ� zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymcҪ���õ� zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdmҪ���õ� bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmcҪ���õ� bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the pyccmc
	 */
	public String getPyccmc() {
		return pyccmc;
	}
	/**
	 * @param pyccmcҪ���õ� pyccmc
	 */
	public void setPyccmc(String pyccmc) {
		this.pyccmc = pyccmc;
	}
	
	/**
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}
	/**
	 * @param sfzhҪ���õ� sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param userҪ���õ� user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the jgs
	 */
	public String getJgs() {
		return jgs;
	}
	/**
	 * @param jgsҪ���õ� jgs
	 */
	public void setJgs(String jgs) {
		this.jgs = jgs;
	}
	/**
	 * @return the jgshi
	 */
	public String getJgshi() {
		return jgshi;
	}
	/**
	 * @param jgshiҪ���õ� jgshi
	 */
	public void setJgshi(String jgshi) {
		this.jgshi = jgshi;
	}
	/**
	 * @return the jgx
	 */
	public String getJgx() {
		return jgx;
	}
	/**
	 * @param jgxҪ���õ� jgx
	 */
	public void setJgx(String jgx) {
		this.jgx = jgx;
	}
	/**
	 * @return the ssbh
	 */
	public String getSsbh() {
		return ssbh;
	}
	/**
	 * @param ssbhҪ���õ� ssbh
	 */
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	/**
	 * @return the rxnj
	 */
	public String getRxnj() {
		return rxnj;
	}
	/**
	 * @param rxnjҪ���õ� rxnj
	 */
	public void setRxnj(String rxnj) {
		this.rxnj = rxnj;
	}
	/**
	 * @return the nfby
	 */
	public String getNfby() {
		return nfby;
	}
	/**
	 * @param nfbyҪ���õ� nfby
	 */
	public void setNfby(String nfby) {
		this.nfby = nfby;
	}
	/**
	 * @return the sfzc
	 */
	public String getSfzc() {
		return sfzc;
	}
	/**
	 * @param sfzcҪ���õ� sfzc
	 */
	public void setSfzc(String sfzc) {
		this.sfzc = sfzc;
	}
	/**
	 * @return the dasfyl
	 */
	public String getDasfyl() {
		return dasfyl;
	}
	/**
	 * @param dasfylҪ���õ� dasfyl
	 */
	public void setDasfyl(String dasfyl) {
		this.dasfyl = dasfyl;
	}
	/**
	 * @return the daylyy
	 */
	public String getDaylyy() {
		return daylyy;
	}
	/**
	 * @param daylyyҪ���õ� daylyy
	 */
	public void setDaylyy(String daylyy) {
		this.daylyy = daylyy;
	}
	/**
	 * @return the yxdm
	 */
	public String getYxdm() {
		return yxdm;
	}
	/**
	 * @param yxdmҪ���õ� yxdm
	 */
	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
	}
	/**
	 * @return the sfzz
	 */
	public String getSfzz() {
		return sfzz;
	}
	/**
	 * @param sfzzҪ���õ� sfzz
	 */
	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}
	/**
	 * @return the sfsf
	 */
	public String getSfsf() {
		return sfsf;
	}
	/**
	 * @param sfsfҪ���õ� sfsf
	 */
	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
	}
	/**
	 * @return the sfdl
	 */
	public String getSfdl() {
		return sfdl;
	}
	/**
	 * @param sfdlҪ���õ� sfdl
	 */
	public void setSfdl(String sfdl) {
		this.sfdl = sfdl;
	}
	/**
	 * @return the dxhwp
	 */
	public String getDxhwp() {
		return dxhwp;
	}
	/**
	 * @param dxhwpҪ���õ� dxhwp
	 */
	public void setDxhwp(String dxhwp) {
		this.dxhwp = dxhwp;
	}
	/**
	 * @return the bysj
	 */
	public String getBysj() {
		return bysj;
	}
	/**
	 * @param bysjҪ���õ� bysj
	 */
	public void setBysj(String bysj) {
		this.bysj = bysj;
	}
	/**
	 * @return the zxwyyzdm
	 */
	public String getZxwyyzdm() {
		return zxwyyzdm;
	}
	/**
	 * @param zxwyyzdmҪ���õ� zxwyyzdm
	 */
	public void setZxwyyzdm(String zxwyyzdm) {
		this.zxwyyzdm = zxwyyzdm;
	}
	/**
	 * @return the wydj
	 */
	public String getWydj() {
		return wydj;
	}
	/**
	 * @param wydjҪ���õ� wydj
	 */
	public void setWydj(String wydj) {
		this.wydj = wydj;
	}
	/**
	 * @return the jsjdj
	 */
	public String getJsjdj() {
		return jsjdj;
	}
	/**
	 * @param jsjdjҪ���õ� jsjdj
	 */
	public void setJsjdj(String jsjdj) {
		this.jsjdj = jsjdj;
	}
	/**
	 * @return the lxdz
	 */
	public String getLxdz() {
		return lxdz;
	}
	/**
	 * @param lxdzҪ���õ� lxdz
	 */
	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}
	/**
	 * @return the yzbm
	 */
	public String getYzbm() {
		return yzbm;
	}
	/**
	 * @param yzbmҪ���õ� yzbm
	 */
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	/**
	 * @return the shzw
	 */
	public String getShzw() {
		return shzw;
	}
	/**
	 * @param shzwҪ���õ� shzw
	 */
	public void setShzw(String shzw) {
		this.shzw = shzw;
	}
	/**
	 * @return the jypx
	 */
	public String getJypx() {
		return jypx;
	}
	/**
	 * @param jypxҪ���õ� jypx
	 */
	public void setJypx(String jypx) {
		this.jypx = jypx;
	}
	/**
	 * @return the xmsj
	 */
	public String getXmsj() {
		return xmsj;
	}
	/**
	 * @param xmsjҪ���õ� xmsj
	 */
	public void setXmsj(String xmsj) {
		this.xmsj = xmsj;
	}
	/**
	 * @return the zgzs
	 */
	public String getZgzs() {
		return zgzs;
	}
	/**
	 * @param zgzsҪ���õ� zgzs
	 */
	public void setZgzs(String zgzs) {
		this.zgzs = zgzs;
	}
	/**
	 * @return the jljn
	 */
	public String getJljn() {
		return jljn;
	}
	/**
	 * @param jljnҪ���õ� jljn
	 */
	public void setJljn(String jljn) {
		this.jljn = jljn;
	}
	/**
	 * @return the sybz1
	 */
	public String getSybz1() {
		return sybz1;
	}
	/**
	 * @param sybz1Ҫ���õ� sybz1
	 */
	public void setSybz1(String sybz1) {
		this.sybz1 = sybz1;
	}
	/**
	 * @return the sybz2
	 */
	public String getSybz2() {
		return sybz2;
	}
	/**
	 * @param sybz2Ҫ���õ� sybz2
	 */
	public void setSybz2(String sybz2) {
		this.sybz2 = sybz2;
	}
	/**
	 * @return the sybz3
	 */
	public String getSybz3() {
		return sybz3;
	}
	/**
	 * @param sybz3Ҫ���õ� sybz3
	 */
	public void setSybz3(String sybz3) {
		this.sybz3 = sybz3;
	}
	/**
	 * @return the xldm
	 */
	public String getXldm() {
		return xldm;
	}
	/**
	 * @param xldmҪ���õ� xldm
	 */
	public void setXldm(String xldm) {
		this.xldm = xldm;
	}
	/**
	 * @return the zkzh
	 */
	public String getZkzh() {
		return zkzh;
	}
	/**
	 * @param zkzhҪ���õ� zkzh
	 */
	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}
	/**
	 * @return the grjl
	 */
	public String getGrjl() {
		return grjl;
	}
	/**
	 * @param grjlҪ���õ� grjl
	 */
	public void setGrjl(String grjl) {
		this.grjl = grjl;
	}
	/**
	 * @return the sfcj
	 */
	public String getSfcj() {
		return sfcj;
	}
	/**
	 * @param sfcjҪ���õ� sfcj
	 */
	public void setSfcj(String sfcj) {
		this.sfcj = sfcj;
	}
	/**
	 * @return the ssch
	 */
	public String getSsch() {
		return ssch;
	}
	/**
	 * @param sschҪ���õ� ssch
	 */
	public void setSsch(String ssch) {
		this.ssch = ssch;
	}
	/**
	 * @return the rzrq
	 */
	public String getRzrq() {
		return rzrq;
	}
	/**
	 * @param rzrqҪ���õ� rzrq
	 */
	public void setRzrq(String rzrq) {
		this.rzrq = rzrq;
	}
	/**
	 * @return the zsjzrq
	 */
	public String getZsjzrq() {
		return zsjzrq;
	}
	/**
	 * @param zsjzrqҪ���õ� zsjzrq
	 */
	public void setZsjzrq(String zsjzrq) {
		this.zsjzrq = zsjzrq;
	}
	/**
	 * @return the qsdh
	 */
	public String getQsdh() {
		return qsdh;
	}
	/**
	 * @param qsdhҪ���õ� qsdh
	 */
	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}
	/**
	 * @return the ykth
	 */
	public String getYkth() {
		return ykth;
	}
	/**
	 * @param ykthҪ���õ� ykth
	 */
	public void setYkth(String ykth) {
		this.ykth = ykth;
	}
	/**
	 * @return the yhkh
	 */
	public String getYhkh() {
		return yhkh;
	}
	/**
	 * @param yhkhҪ���õ� yhkh
	 */
	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}
	/**
	 * @return the xslb
	 */
	public String getXslb() {
		return xslb;
	}
	/**
	 * @param xslbҪ���õ� xslb
	 */
	public void setXslb(String xslb) {
		this.xslb = xslb;
	}
	/**
	 * @return the xslx
	 */
	public String getXslx() {
		return xslx;
	}
	/**
	 * @param xslxҪ���õ� xslx
	 */
	public void setXslx(String xslx) {
		this.xslx = xslx;
	}
	/**
	 * @return the sfbys
	 */
	public String getSfbys() {
		return sfbys;
	}
	/**
	 * @param sfbysҪ���õ� sfbys
	 */
	public void setSfbys(String sfbys) {
		this.sfbys = sfbys;
	}
	/**
	 * @return the yhdm
	 */
	public String getYhdm() {
		return yhdm;
	}
	/**
	 * @param yhdmҪ���õ� yhdm
	 */
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	/**
	 * @return the hkshen
	 */
	public String getHkshen() {
		return hkshen;
	}
	/**
	 * @param hkshenҪ���õ� hkshen
	 */
	public void setHkshen(String hkshen) {
		this.hkshen = hkshen;
	}
	/**
	 * @return the hkshi
	 */
	public String getHkshi() {
		return hkshi;
	}
	/**
	 * @param hkshiҪ���õ� hkshi
	 */
	public void setHkshi(String hkshi) {
		this.hkshi = hkshi;
	}
	/**
	 * @return the hkxian
	 */
	public String getHkxian() {
		return hkxian;
	}
	/**
	 * @param hkxianҪ���õ� hkxian
	 */
	public void setHkxian(String hkxian) {
		this.hkxian = hkxian;
	}
	/**
	 * @return the zcsxhm
	 */
	public String getZcsxhm() {
		return zcsxhm;
	}
	/**
	 * @param zcsxhmҪ���õ� zcsxhm
	 */
	public void setZcsxhm(String zcsxhm) {
		this.zcsxhm = zcsxhm;
	}
	/**
	 * @return the rxqwhcd
	 */
	public String getRxqwhcd() {
		return rxqwhcd;
	}
	/**
	 * @param rxqwhcdҪ���õ� rxqwhcd
	 */
	public void setRxqwhcd(String rxqwhcd) {
		this.rxqwhcd = rxqwhcd;
	}
	/**
	 * @return the xsqrxxbz
	 */
	public String getXsqrxxbz() {
		return xsqrxxbz;
	}
	/**
	 * @param xsqrxxbzҪ���õ� xsqrxxbz
	 */
	public void setXsqrxxbz(String xsqrxxbz) {
		this.xsqrxxbz = xsqrxxbz;
	}
	/**
	 * @return the dah
	 */
	public String getDah() {
		return dah;
	}
	/**
	 * @param dahҪ���õ� dah
	 */
	public void setDah(String dah) {
		this.dah = dah;
	}
	/**
	 * @return the ylbxh
	 */
	public String getYlbxh() {
		return ylbxh;
	}
	/**
	 * @param ylbxhҪ���õ� ylbxh
	 */
	public void setYlbxh(String ylbxh) {
		this.ylbxh = ylbxh;
	}
	/**
	 * @return the rxqdwdz
	 */
	public String getRxqdwdz() {
		return rxqdwdz;
	}
	/**
	 * @param rxqdwdzҪ���õ� rxqdwdz
	 */
	public void setRxqdwdz(String rxqdwdz) {
		this.rxqdwdz = rxqdwdz;
	}
	/**
	 * @return the rxqdwyb
	 */
	public String getRxqdwyb() {
		return rxqdwyb;
	}
	/**
	 * @param rxqdwybҪ���õ� rxqdwyb
	 */
	public void setRxqdwyb(String rxqdwyb) {
		this.rxqdwyb = rxqdwyb;
	}
	/**
	 * @return the rxqdwdh
	 */
	public String getRxqdwdh() {
		return rxqdwdh;
	}
	/**
	 * @param rxqdwdhҪ���õ� rxqdwdh
	 */
	public void setRxqdwdh(String rxqdwdh) {
		this.rxqdwdh = rxqdwdh;
	}
	/**
	 * @return the gzbx
	 */
	public String getGzbx() {
		return gzbx;
	}
	/**
	 * @param gzbxҪ���õ� gzbx
	 */
	public void setGzbx(String gzbx) {
		this.gzbx = gzbx;
	}
	/**
	 * @return the sfgat
	 */
	public String getSfgat() {
		return sfgat;
	}
	/**
	 * @param sfgatҪ���õ� sfgat
	 */
	public void setSfgat(String sfgat) {
		this.sfgat = sfgat;
	}
	/**
	 * @return the sfssmz
	 */
	public String getSfssmz() {
		return sfssmz;
	}
	/**
	 * @param sfssmzҪ���õ� sfssmz
	 */
	public void setSfssmz(String sfssmz) {
		this.sfssmz = sfssmz;
	}
	/**
	 * @return the sfzd
	 */
	public String getSfzd() {
		return sfzd;
	}
	/**
	 * @param sfzdҪ���õ� sfzd
	 */
	public void setSfzd(String sfzd) {
		this.sfzd = sfzd;
	}
	/**
	 * @return the syds
	 */
	public String getSyds() {
		return syds;
	}
	/**
	 * @param sydsҪ���õ� syds
	 */
	public void setSyds(String syds) {
		this.syds = syds;
	}
	/**
	 * @return the sydshi
	 */
	public String getSydshi() {
		return sydshi;
	}
	/**
	 * @param sydshiҪ���õ� sydshi
	 */
	public void setSydshi(String sydshi) {
		this.sydshi = sydshi;
	}
	/**
	 * @return the sydx
	 */
	public String getSydx() {
		return sydx;
	}
	/**
	 * @param sydxҪ���õ� sydx
	 */
	public void setSydx(String sydx) {
		this.sydx = sydx;
	}
	/**
	 * @return the byzh
	 */
	public String getByzh() {
		return byzh;
	}
	/**
	 * @param byzhҪ���õ� byzh
	 */
	public void setByzh(String byzh) {
		this.byzh = byzh;
	}
	/**
	 * @return the xjh
	 */
	public String getXjh() {
		return xjh;
	}
	/**
	 * @param xjhҪ���õ� xjh
	 */
	public void setXjh(String xjh) {
		this.xjh = xjh;
	}
	/**
	 * @return the jrzzmmrq
	 */
	public String getJrzzmmrq() {
		return jrzzmmrq;
	}
	/**
	 * @param jrzzmmrqҪ���õ� jrzzmmrq
	 */
	public void setJrzzmmrq(String jrzzmmrq) {
		this.jrzzmmrq = jrzzmmrq;
	}
	/**
	 * @return the sfhq
	 */
	public String getSfhq() {
		return sfhq;
	}
	/**
	 * @param sfhqҪ���õ� sfhq
	 */
	public void setSfhq(String sfhq) {
		this.sfhq = sfhq;
	}
	/**
	 * @return the csds
	 */
	public String getCsds() {
		return csds;
	}
	/**
	 * @param csdsҪ���õ� csds
	 */
	public void setCsds(String csds) {
		this.csds = csds;
	}
	/**
	 * @return the csdshi
	 */
	public String getCsdshi() {
		return csdshi;
	}
	/**
	 * @param csdshiҪ���õ� csdshi
	 */
	public void setCsdshi(String csdshi) {
		this.csdshi = csdshi;
	}
	/**
	 * @return the csdxian
	 */
	public String getCsdxian() {
		return csdxian;
	}
	/**
	 * @param csdxianҪ���õ� csdxian
	 */
	public void setCsdxian(String csdxian) {
		this.csdxian = csdxian;
	}
	/**
	 * @return the xy
	 */
	public String getXy() {
		return xy;
	}
	/**
	 * @param xyҪ���õ� xy
	 */
	public void setXy(String xy) {
		this.xy = xy;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the xmpy
	 */
	public String getXmpy() {
		return xmpy;
	}
	/**
	 * @param xmpyҪ���õ� xmpy
	 */
	public void setXmpy(String xmpy) {
		this.xmpy = xmpy;
	}
	/**
	 * @return the syd
	 */
	public String getSyd() {
		return syd;
	}
	/**
	 * @param sydҪ���õ� syd
	 */
	public void setSyd(String syd) {
		this.syd = syd;
	}
	/**
	 * @return the csrq
	 */
	public String getCsrq() {
		return csrq;
	}
	/**
	 * @param csrqҪ���õ� csrq
	 */
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	/**
	 * @return the mz
	 */
	public String getMz() {
		return mz;
	}
	/**
	 * @param mzҪ���õ� mz
	 */
	public void setMz(String mz) {
		this.mz = mz;
	}
	/**
	 * @return the zzmm
	 */
	public String getZzmm() {
		return zzmm;
	}
	/**
	 * @param zzmmҪ���õ� zzmm
	 */
	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdhҪ���õ� lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the dzyx
	 */
	public String getDzyx() {
		return dzyx;
	}
	/**
	 * @param dzyxҪ���õ� dzyx
	 */
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	/**
	 * @return the cym
	 */
	public String getCym() {
		return cym;
	}
	/**
	 * @param cymҪ���õ� cym
	 */
	public void setCym(String cym) {
		this.cym = cym;
	}
	/**
	 * @return the sg
	 */
	public String getSg() {
		return sg;
	}
	/**
	 * @param sgҪ���õ� sg
	 */
	public void setSg(String sg) {
		this.sg = sg;
	}
	/**
	 * @return the tz
	 */
	public String getTz() {
		return tz;
	}
	/**
	 * @param tzҪ���õ� tz
	 */
	public void setTz(String tz) {
		this.tz = tz;
	}
	/**
	 * @return the kslb
	 */
	public String getKslb() {
		return kslb;
	}
	/**
	 * @param kslbҪ���õ� kslb
	 */
	public void setKslb(String kslb) {
		this.kslb = kslb;
	}
	/**
	 * @return the rxfs
	 */
	public String getRxfs() {
		return rxfs;
	}
	/**
	 * @param rxfsҪ���õ� rxfs
	 */
	public void setRxfs(String rxfs) {
		this.rxfs = rxfs;
	}
	/**
	 * @return the pyfs
	 */
	public String getPyfs() {
		return pyfs;
	}
	/**
	 * @param pyfsҪ���õ� pyfs
	 */
	public void setPyfs(String pyfs) {
		this.pyfs = pyfs;
	}
	/**
	 * @return the pycc
	 */
	public String getPycc() {
		return pycc;
	}
	/**
	 * @param pyccҪ���õ� pycc
	 */
	public void setPycc(String pycc) {
		this.pycc = pycc;
	}
	/**
	 * @return the xjlb
	 */
	public String getXjlb() {
		return xjlb;
	}
	/**
	 * @param xjlbҪ���õ� xjlb
	 */
	public void setXjlb(String xjlb) {
		this.xjlb = xjlb;
	}
	/**
	 * @return the xszp
	 */
	public String getXszp() {
		return xszp;
	}
	/**
	 * @param xszpҪ���õ� xszp
	 */
	public void setXszp(String xszp) {
		this.xszp = xszp;
	}
	/**
	 * @return the xsgkzp
	 */
	public String getXsgkzp() {
		return xsgkzp;
	}
	/**
	 * @param xsgkzpҪ���õ� xsgkzp
	 */
	public void setXsgkzp(String xsgkzp) {
		this.xsgkzp = xsgkzp;
	}
	/**
	 * @return the xjztm
	 */
	public String getXjztm() {
		return xjztm;
	}
	/**
	 * @param xjztmҪ���õ� xjztm
	 */
	public void setXjztm(String xjztm) {
		this.xjztm = xjztm;
	}
	/**
	 * @return the xz
	 */
	public String getXz() {
		return xz;
	}
	/**
	 * @param xzҪ���õ� xz
	 */
	public void setXz(String xz) {
		this.xz = xz;
	}
	/**
	 * @return the whcd
	 */
	public String getWhcd() {
		return whcd;
	}
	/**
	 * @param whcdҪ���õ� whcd
	 */
	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}
	/**
	 * @return the rxqdw
	 */
	public String getRxqdw() {
		return rxqdw;
	}
	/**
	 * @param rxqdwҪ���õ� rxqdw
	 */
	public void setRxqdw(String rxqdw) {
		this.rxqdw = rxqdw;
	}
	/**
	 * @return the jtdh
	 */
	public String getJtdh() {
		return jtdh;
	}
	/**
	 * @param jtdhҪ���õ� jtdh
	 */
	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}
	/**
	 * @return the jrgqtsj
	 */
	public String getJrgqtsj() {
		return jrgqtsj;
	}
	/**
	 * @param jrgqtsjҪ���õ� jrgqtsj
	 */
	public void setJrgqtsj(String jrgqtsj) {
		this.jrgqtsj = jrgqtsj;
	}
	/**
	 * @return the jrgcdsj
	 */
	public String getJrgcdsj() {
		return jrgcdsj;
	}
	/**
	 * @param jrgcdsjҪ���õ� jrgcdsj
	 */
	public void setJrgcdsj(String jrgcdsj) {
		this.jrgcdsj = jrgcdsj;
	}
	/**
	 * @return the jtcygc
	 */
	public String getJtcygc() {
		return jtcygc;
	}
	/**
	 * @param jtcygcҪ���õ� jtcygc
	 */
	public void setJtcygc(String jtcygc) {
		this.jtcygc = jtcygc;
	}
	/**
	 * @return the jlcfjl
	 */
	public String getJlcfjl() {
		return jlcfjl;
	}
	/**
	 * @param jlcfjlҪ���õ� jlcfjl
	 */
	public void setJlcfjl(String jlcfjl) {
		this.jlcfjl = jlcfjl;
	}
	/**
	 * @return the jkzk
	 */
	public String getJkzk() {
		return jkzk;
	}
	/**
	 * @param jkzkҪ���õ� jkzk
	 */
	public void setJkzk(String jkzk) {
		this.jkzk = jkzk;
	}
	/**
	 * @return the jtdz
	 */
	public String getJtdz() {
		return jtdz;
	}
	/**
	 * @param jtdzҪ���õ� jtdz
	 */
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	/**
	 * @return the jtyb
	 */
	public String getJtyb() {
		return jtyb;
	}
	/**
	 * @param jtybҪ���õ� jtyb
	 */
	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}
	/**
	 * @return the jg
	 */
	public String getJg() {
		return jg;
	}
	/**
	 * @param jgҪ���õ� jg
	 */
	public void setJg(String jg) {
		this.jg = jg;
	}
	/**
	 * @return the xx
	 */
	public String getXx() {
		return xx;
	}
	/**
	 * @param xxҪ���õ� xx
	 */
	public void setXx(String xx) {
		this.xx = xx;
	}
	/**
	 * @return the ah
	 */
	public String getAh() {
		return ah;
	}
	/**
	 * @param ahҪ���õ� ah
	 */
	public void setAh(String ah) {
		this.ah = ah;
	}
	/**
	 * @return the sfdk
	 */
	public String getSfdk() {
		return sfdk;
	}
	/**
	 * @param sfdkҪ���õ� sfdk
	 */
	public void setSfdk(String sfdk) {
		this.sfdk = sfdk;
	}
	/**
	 * @return the shgxxm1
	 */
	public String getShgxxm1() {
		return shgxxm1;
	}
	/**
	 * @param shgxxm1Ҫ���õ� shgxxm1
	 */
	public void setShgxxm1(String shgxxm1) {
		this.shgxxm1 = shgxxm1;
	}
	/**
	 * @return the shgxgx1
	 */
	public String getShgxgx1() {
		return shgxgx1;
	}
	/**
	 * @param shgxgx1Ҫ���õ� shgxgx1
	 */
	public void setShgxgx1(String shgxgx1) {
		this.shgxgx1 = shgxgx1;
	}
	/**
	 * @return the shgxgzdw1
	 */
	public String getShgxgzdw1() {
		return shgxgzdw1;
	}
	/**
	 * @param shgxgzdw1Ҫ���õ� shgxgzdw1
	 */
	public void setShgxgzdw1(String shgxgzdw1) {
		this.shgxgzdw1 = shgxgzdw1;
	}
	/**
	 * @return the shgxzw1
	 */
	public String getShgxzw1() {
		return shgxzw1;
	}
	/**
	 * @param shgxzw1Ҫ���õ� shgxzw1
	 */
	public void setShgxzw1(String shgxzw1) {
		this.shgxzw1 = shgxzw1;
	}
	/**
	 * @return the shgxdwdh1
	 */
	public String getShgxdwdh1() {
		return shgxdwdh1;
	}
	/**
	 * @param shgxdwdh1Ҫ���õ� shgxdwdh1
	 */
	public void setShgxdwdh1(String shgxdwdh1) {
		this.shgxdwdh1 = shgxdwdh1;
	}
	/**
	 * @return the shgxsjhm1
	 */
	public String getShgxsjhm1() {
		return shgxsjhm1;
	}
	/**
	 * @param shgxsjhm1Ҫ���õ� shgxsjhm1
	 */
	public void setShgxsjhm1(String shgxsjhm1) {
		this.shgxsjhm1 = shgxsjhm1;
	}
	/**
	 * @return the shgxxm2
	 */
	public String getShgxxm2() {
		return shgxxm2;
	}
	/**
	 * @param shgxxm2Ҫ���õ� shgxxm2
	 */
	public void setShgxxm2(String shgxxm2) {
		this.shgxxm2 = shgxxm2;
	}
	/**
	 * @return the shgxgx2
	 */
	public String getShgxgx2() {
		return shgxgx2;
	}
	/**
	 * @param shgxgx2Ҫ���õ� shgxgx2
	 */
	public void setShgxgx2(String shgxgx2) {
		this.shgxgx2 = shgxgx2;
	}
	/**
	 * @return the shgxgzdw2
	 */
	public String getShgxgzdw2() {
		return shgxgzdw2;
	}
	/**
	 * @param shgxgzdw2Ҫ���õ� shgxgzdw2
	 */
	public void setShgxgzdw2(String shgxgzdw2) {
		this.shgxgzdw2 = shgxgzdw2;
	}
	/**
	 * @return the shgxzw2
	 */
	public String getShgxzw2() {
		return shgxzw2;
	}
	/**
	 * @param shgxzw2Ҫ���õ� shgxzw2
	 */
	public void setShgxzw2(String shgxzw2) {
		this.shgxzw2 = shgxzw2;
	}
	/**
	 * @return the shgxdwdh2
	 */
	public String getShgxdwdh2() {
		return shgxdwdh2;
	}
	/**
	 * @param shgxdwdh2Ҫ���õ� shgxdwdh2
	 */
	public void setShgxdwdh2(String shgxdwdh2) {
		this.shgxdwdh2 = shgxdwdh2;
	}
	/**
	 * @return the shgxsjhm2
	 */
	public String getShgxsjhm2() {
		return shgxsjhm2;
	}
	/**
	 * @param shgxsjhm2Ҫ���õ� shgxsjhm2
	 */
	public void setShgxsjhm2(String shgxsjhm2) {
		this.shgxsjhm2 = shgxsjhm2;
	}
	/**
	 * @return the jtqkjj
	 */
	public String getJtqkjj() {
		return jtqkjj;
	}
	/**
	 * @param jtqkjjҪ���õ� jtqkjj
	 */
	public void setJtqkjj(String jtqkjj) {
		this.jtqkjj = jtqkjj;
	}
	/**
	 * @return the jtjjqk
	 */
	public String getJtjjqk() {
		return jtjjqk;
	}
	/**
	 * @param jtjjqkҪ���õ� jtjjqk
	 */
	public void setJtjjqk(String jtjjqk) {
		this.jtjjqk = jtjjqk;
	}
	/**
	 * @return the sjhm
	 */
	public String getSjhm() {
		return sjhm;
	}
	/**
	 * @param sjhmҪ���õ� sjhm
	 */
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	/**
	 * @return the byxx
	 */
	public String getByxx() {
		return byxx;
	}
	/**
	 * @param byxxҪ���õ� byxx
	 */
	public void setByxx(String byxx) {
		this.byxx = byxx;
	}
	/**
	 * @return the kh
	 */
	public String getKh() {
		return kh;
	}
	/**
	 * @param khҪ���õ� kh
	 */
	public void setKh(String kh) {
		this.kh = kh;
	}
	/**
	 * @return the rxrq
	 */
	public String getRxrq() {
		return rxrq;
	}
	/**
	 * @param rxrqҪ���õ� rxrq
	 */
	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}
	/**
	 * @return the fdyxm
	 */
	public String getFdyxm() {
		return fdyxm;
	}
	/**
	 * @param fdyxmҪ���õ� fdyxm
	 */
	public void setFdyxm(String fdyxm) {
		this.fdyxm = fdyxm;
	}
	/**
	 * @return the gkcj
	 */
	public String getGkcj() {
		return gkcj;
	}
	/**
	 * @param gkcjҪ���õ� gkcj
	 */
	public void setGkcj(String gkcj) {
		this.gkcj = gkcj;
	}
	/**
	 * @return the qqhm
	 */
	public String getQqhm() {
		return qqhm;
	}
	/**
	 * @param qqhmҪ���õ� qqhm
	 */
	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}
	/**
	 * @return the hkxz
	 */
	public String getHkxz() {
		return hkxz;
	}
	/**
	 * @param hkxzҪ���õ� hkxz
	 */
	public void setHkxz(String hkxz) {
		this.hkxz = hkxz;
	}
	/**
	 * @return the zyjb
	 */
	public String getZyjb() {
		return zyjb;
	}
	/**
	 * @param zyjbҪ���õ� zyjb
	 */
	public void setZyjb(String zyjb) {
		this.zyjb = zyjb;
	}
	/**
	 * @return the hkszd
	 */
	public String getHkszd() {
		return hkszd;
	}
	/**
	 * @param hkszdҪ���õ� hkszd
	 */
	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}
	/**
	 * @return the ssyq
	 */
	public String getSsyq() {
		return ssyq;
	}
	/**
	 * @param ssyqҪ���õ� ssyq
	 */
	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}
	/**
	 * @return the ssld
	 */
	public String getSsld() {
		return ssld;
	}
	/**
	 * @param ssldҪ���õ� ssld
	 */
	public void setSsld(String ssld) {
		this.ssld = ssld;
	}
	/**
	 * @return the jtdzs
	 */
	public String getJtdzs() {
		return jtdzs;
	}
	/**
	 * @param jtdzsҪ���õ� jtdzs
	 */
	public void setJtdzs(String jtdzs) {
		this.jtdzs = jtdzs;
	}
	/**
	 * @return the jtdzx
	 */
	public String getJtdzx() {
		return jtdzx;
	}
	/**
	 * @param jtdzxҪ���õ� jtdzx
	 */
	public void setJtdzx(String jtdzx) {
		this.jtdzx = jtdzx;
	}
	/**
	 * @return the sfzsb
	 */
	public String getSfzsb() {
		return sfzsb;
	}
	/**
	 * @param sfzsbҪ���õ� sfzsb
	 */
	public void setSfzsb(String sfzsb) {
		this.sfzsb = sfzsb;
	}
	/**
	 * @return the sfzfx
	 */
	public String getSfzfx() {
		return sfzfx;
	}
	/**
	 * @param sfzfxҪ���õ� sfzfx
	 */
	public void setSfzfx(String sfzfx) {
		this.sfzfx = sfzfx;
	}
	/**
	 * @return the zjdm
	 */
	public String getZjdm() {
		return zjdm;
	}
	/**
	 * @param zjdmҪ���õ� zjdm
	 */
	public void setZjdm(String zjdm) {
		this.zjdm = zjdm;
	}
	/**
	 * @return the sfyby
	 */
	public String getSfyby() {
		return sfyby;
	}
	/**
	 * @param sfybyҪ���õ� sfyby
	 */
	public void setSfyby(String sfyby) {
		this.sfyby = sfyby;
	}
	/**
	 * @return the sfzx
	 */
	public String getSfzx() {
		return sfzx;
	}
	/**
	 * @param sfzxҪ���õ� sfzx
	 */
	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}
	/**
	 * @return the zw
	 */
	public String getZw() {
		return zw;
	}
	/**
	 * @param zwҪ���õ� zw
	 */
	public void setZw(String zw) {
		this.zw = zw;
	}
	/**
	 * @return the thbs
	 */
	public String getThbs() {
		return thbs;
	}
	/**
	 * @param thbsҪ���õ� thbs
	 */
	public void setThbs(String thbs) {
		this.thbs = thbs;
	}
	/**
	 * @return the dybj
	 */
	public String getDybj() {
		return dybj;
	}
	/**
	 * @param dybjҪ���õ� dybj
	 */
	public void setDybj(String dybj) {
		this.dybj = dybj;
	}
	/**
	 * @return the shbj
	 */
	public String getShbj() {
		return shbj;
	}
	/**
	 * @param shbjҪ���õ� shbj
	 */
	public void setShbj(String shbj) {
		this.shbj = shbj;
	}
	/**
	 * @return the xwzsxlh
	 */
	public String getXwzsxlh() {
		return xwzsxlh;
	}
	/**
	 * @param xwzsxlhҪ���õ� xwzsxlh
	 */
	public void setXwzsxlh(String xwzsxlh) {
		this.xwzsxlh = xwzsxlh;
	}
	/**
	 * @return the xwzsbh
	 */
	public String getXwzsbh() {
		return xwzsbh;
	}
	/**
	 * @param xwzsbhҪ���õ� xwzsbh
	 */
	public void setXwzsbh(String xwzsbh) {
		this.xwzsbh = xwzsbh;
	}
	/**
	 * @return the xw
	 */
	public String getXw() {
		return xw;
	}
	/**
	 * @param xwҪ���õ� xw
	 */
	public void setXw(String xw) {
		this.xw = xw;
	}
	/**
	 * @return the xzxm
	 */
	public String getXzxm() {
		return xzxm;
	}
	/**
	 * @param xzxmҪ���õ� xzxm
	 */
	public void setXzxm(String xzxm) {
		this.xzxm = xzxm;
	}
	/**
	 * @return the zsxlh
	 */
	public String getZsxlh() {
		return zsxlh;
	}
	/**
	 * @param zsxlhҪ���õ� zsxlh
	 */
	public void setZsxlh(String zsxlh) {
		this.zsxlh = zsxlh;
	}
	/**
	 * @return the zsbh
	 */
	public String getZsbh() {
		return zsbh;
	}
	/**
	 * @param zsbhҪ���õ� zsbh
	 */
	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}
	/**
	 * @return the bjyjl
	 */
	public String getBjyjl() {
		return bjyjl;
	}
	/**
	 * @param bjyjlҪ���õ� bjyjl
	 */
	public void setBjyjl(String bjyjl) {
		this.bjyjl = bjyjl;
	}
	/**
	 * @return the csd
	 */
	public String getCsd() {
		return csd;
	}
	/**
	 * @param csdҪ���õ� csd
	 */
	public void setCsd(String csd) {
		this.csd = csd;
	}
	/**
	 * @return the zsjj
	 */
	public String getZsjj() {
		return zsjj;
	}
	/**
	 * @param zsjjҪ���õ� zsjj
	 */
	public void setZsjj(String zsjj) {
		this.zsjj = zsjj;
	}
	/**
	 * @return the xxxs
	 */
	public String getXxxs() {
		return xxxs;
	}
	/**
	 * @param xxxsҪ���õ� xxxs
	 */
	public void setXxxs(String xxxs) {
		this.xxxs = xxxs;
	}
	/**
	 * @return the bxlx
	 */
	public String getBxlx() {
		return bxlx;
	}
	/**
	 * @param bxlxҪ���õ� bxlx
	 */
	public void setBxlx(String bxlx) {
		this.bxlx = bxlx;
	}
	/**
	 * @return the bxxs
	 */
	public String getBxxs() {
		return bxxs;
	}
	/**
	 * @param bxxsҪ���õ� bxxs
	 */
	public void setBxxs(String bxxs) {
		this.bxxs = bxxs;
	}
	/**
	 * @return the fxzyfx
	 */
	public String getFxzyfx() {
		return fxzyfx;
	}
	/**
	 * @param fxzyfxҪ���õ� fxzyfx
	 */
	public void setFxzyfx(String fxzyfx) {
		this.fxzyfx = fxzyfx;
	}
	/**
	 * @return the fxzy
	 */
	public String getFxzy() {
		return fxzy;
	}
	/**
	 * @param fxzyҪ���õ� fxzy
	 */
	public void setFxzy(String fxzy) {
		this.fxzy = fxzy;
	}
	/**
	 * @return the zylb
	 */
	public String getZylb() {
		return zylb;
	}
	/**
	 * @param zylbҪ���õ� zylb
	 */
	public void setZylb(String zylb) {
		this.zylb = zylb;
	}
	/**
	 * @return the dqszj
	 */
	public String getDqszj() {
		return dqszj;
	}
	/**
	 * @param dqszjҪ���õ� dqszj
	 */
	public void setDqszj(String dqszj) {
		this.dqszj = dqszj;
	}
	/**
	 * @return the pyfx
	 */
	public String getPyfx() {
		return pyfx;
	}
	/**
	 * @param pyfxҪ���õ� pyfx
	 */
	public void setPyfx(String pyfx) {
		this.pyfx = pyfx;
	}
	/**
	 * @return the zyfx
	 */
	public String getZyfx() {
		return zyfx;
	}
	/**
	 * @param zyfxҪ���õ� zyfx
	 */
	public void setZyfx(String zyfx) {
		this.zyfx = zyfx;
	}
	/**
	 * @return the xxszd
	 */
	public String getXxszd() {
		return xxszd;
	}
	/**
	 * @param xxszdҪ���õ� xxszd
	 */
	public void setXxszd(String xxszd) {
		this.xxszd = xxszd;
	}
	/**
	 * @return the ksh
	 */
	public String getKsh() {
		return ksh;
	}
	/**
	 * @param kshҪ���õ� ksh
	 */
	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	/**
	 * @return the xxfx
	 */
	public String getXxfx() {
		return xxfx;
	}
	/**
	 * @param xxfxҪ���õ� xxfx
	 */
	public void setXxfx(String xxfx) {
		this.xxfx = xxfx;
	}
	/**
	 * @return the zslb
	 */
	public String getZslb() {
		return zslb;
	}
	/**
	 * @param zslbҪ���õ� zslb
	 */
	public void setZslb(String zslb) {
		this.zslb = zslb;
	}
	/**
	 * @return the gj
	 */
	public String getGj() {
		return gj;
	}
	/**
	 * @param gjҪ���õ� gj
	 */
	public void setGj(String gj) {
		this.gj = gj;
	}
	/**
	 * @return the sfjh
	 */
	public String getSfjh() {
		return sfjh;
	}
	/**
	 * @param sfjhҪ���õ� sfjh
	 */
	public void setSfjh(String sfjh) {
		this.sfjh = sfjh;
	}
	/**
	 * @return the ccqj
	 */
	public String getCcqj() {
		return ccqj;
	}
	/**
	 * @param ccqjҪ���õ� ccqj
	 */
	public void setCcqj(String ccqj) {
		this.ccqj = ccqj;
	}
	/**
	 * @return the byzffztdm
	 */
	public String getByzffztdm() {
		return byzffztdm;
	}
	/**
	 * @param byzffztdmҪ���õ� byzffztdm
	 */
	public void setByzffztdm(String byzffztdm) {
		this.byzffztdm = byzffztdm;
	}
	/**
	 * @return the xwzsxxdz
	 */
	public String getXwzsxxdz() {
		return xwzsxxdz;
	}
	/**
	 * @param xwzsxxdzҪ���õ� xwzsxxdz
	 */
	public void setXwzsxxdz(String xwzsxxdz) {
		this.xwzsxxdz = xwzsxxdz;
	}
	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1Ҫ���õ� zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2Ҫ���õ� zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	/**
	 * @return the zd3
	 */
	public String getZd3() {
		return zd3;
	}
	/**
	 * @param zd3Ҫ���õ� zd3
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	/**
	 * @return the zd4
	 */
	public String getZd4() {
		return zd4;
	}
	/**
	 * @param zd4Ҫ���õ� zd4
	 */
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	/**
	 * @return the zd5
	 */
	public String getZd5() {
		return zd5;
	}
	/**
	 * @param zd5Ҫ���õ� zd5
	 */
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shztҪ���õ� shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the sfxyqgzx
	 */
	public String getSfxyqgzx() {
		return sfxyqgzx;
	}
	/**
	 * @param sfxyqgzxҪ���õ� sfxyqgzx
	 */
	public void setSfxyqgzx(String sfxyqgzx) {
		this.sfxyqgzx = sfxyqgzx;
	}
	/**
	 * @return the xgyx
	 */
	public String getXgyx() {
		return xgyx;
	}
	/**
	 * @param xgyxҪ���õ� xgyx
	 */
	public void setXgyx(String xgyx) {
		this.xgyx = xgyx;
	}

	
	
	
	

}
