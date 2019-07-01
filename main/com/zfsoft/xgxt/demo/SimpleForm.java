package com.zfsoft.xgxt.demo;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class SimpleForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String zd1 ;//��չ�ֶ�1 
	private String zd2 ;//��չ�ֶ�2 
	private String zd3 ;//��չ�ֶ�3 
	private String zd4 ;//��չ�ֶ�4 
	private String zd5 ;//��չ�ֶ�5 
	private String jgs ;//����ʡ 
	private String jgshi ;//������ 
	private String jgx ;//������ 
	private String ssbh ;//������ 
	private String rxnj ;//��ѧ�꼶 
	private String nfby ;//�ܷ��ҵ 
	private String sfzc ;//�Ƿ�ע�� 
	private String dasfyl ;//�����Ƿ����� 
	private String daylyy ;//��������ԭ�� 
	private String yxdm ;//ԺУ���� 
	private String sfzz ;//�Ƿ���ְ 
	private String sfsf ;//�Ƿ�ʦ�� 
	private String sfdl ;//�Ƿ���� 
	private String dxhwp ;//�����ί�� 
	private String bysj ;//��ҵʱ�� 
	private String zxwyyzdm ;//�����������ִ��� 
	private String wydj ;//����ȼ� 
	private String jsjdj ;//������ȼ� 
	private String lxdz ;//��ϵ��ַ 
	private String yzbm ;//�������� 
	private String shzw ;//���ְ�� 
	private String jypx ;//������ѵ 
	private String xmsj ;//��Ŀʵ�� 
	private String zgzs ;//�ʸ�֤�� 
	private String jljn ;//�������� 
	private String sybz1 ;//��Դ��ע1 
	private String sybz2 ;//��Դ��ע2 
	private String sybz3 ;//��Դ��ע3 
	private String xldm ;//ѧ������ 
	private String zkzh ;//׼��֤�� 
	private String grjl ;//���˼��� 
	private String sfcj ;//�Ƿ�м�/1��,0�� 
	private String ssch ;//���ᴲ�� 
	private String rzrq ;//ס������ 
	private String zsjzrq ;//ס�޽�ֹ���� 
	private String qsdh ;//���ҵ绰 
	private String ykth ;//һ��ͨ�� 
	private String yhkh ;//���п��� 
	private String xslb ;//ѧ�������� 
	private String xslx ;//ѧ�����ʹ��� 
	private String sfbys ;//�Ƿ��ҵ�� 
	private String yhdm ;//���д��� 
	private String hkshen ;//��������ʡ 
	private String hkshi ;//���������� 
	private String hkxian ;//���������� 
	private String zcsxhm ;//ע��˳����� 
	private String rxqwhcd ;//��ѧǰ�Ļ��̶� 
	private String xsqrxxbz ;//ѧ��ȷ����Ϣ��־ 
	private String dah ;//������ 
	private String ylbxh ;//ҽ�Ʊ��պ� 
	private String rxqdwdz ;//ԭ��ҵѧԺͨ�ŵ�ַ 
	private String rxqdwyb ;//ԭ��ҵѧԺ�ʱ� 
	private String rxqdwdh ;//��ѧǰ��λ�绰 
	private String gzbx ;//���б��� 
	private String sfgat ;//�Ƿ�۰�̨�� 
	private String sfssmz ;//�Ƿ�������� 
	private String sfzd ;//�Ƿ��߶� 
	private String syds ;//��Դ��ʡ 
	private String sydshi ;//��Դ���� 
	private String sydx ;//��Դ���� 
	private String byzh ;//��ҵ֤�� 
	private String xjh ;//ѧ���� 
	private String jrzzmmrq ;//����������ò���� 
	private String sfhq ;//�Ƿ��� 
	private String csds ;// 
	private String csdshi ;// 
	private String csdxian ;// 
	private String xh ;//ѧ�� 
	private String xy ;//ѧԺ���� 
	private String zymc ;//רҵ���� 
	private String bjmc ;//�༶���� 
	private String bjdm ;//�༶���� 
	private String zydm ;//רҵ���� 
	private String xydm ;//ѧԺ���� 
	private String bz ;//��ע 
	private String xm ;//���� 
	private String xmpy ;//����ƴ�� 
	private String nj ;//�꼶 
	private String syd ;//��Դ�� 
	private String csrq ;//�������� 
	private String sfzh ;//���֤���� 
	private String xb ;//�Ա� 
	private String mz ;//������� 
	private String zzmm ;//������ò���� 
	private String lxdh ;//��ϵ�绰 
	private String dzyx ;//�������� 
	private String cym ;//������ 
	private String sg ;//��� 
	private String tz ;//���� 
	private String tc ;//�س� 
	private String kslb ;//������� 
	private String rxfs ;//��ѧ��ʽ 
	private String pyfs ;//������ʽ 
	private String pycc ;//������� 
	private String xjlb ;//ѧ����� 
	private String xszp ;//ѧ����Ƭ 
	private String xjztm ;//ѧ��״̬ 
	private String xz ;//ѧ�� 
	private String whcd ;//�Ļ��̶� 
	private String rxqdw ;//ԭ��ҵѧԺ 
	private String jtdh ;//��ͥ�绰 
	private String jrgqtsj ;//���빲����ʱ�� 
	private String jrgcdsj ;//���빲����ʱ�� 
	private String jtcygc ;//��ͥ��Ա���� 
	private String jlcfjl ;//�������־��� 
	private String jkzk ;//���޲�ʷ 
	private String jtdz ;//��ͥ��ϸ��ַ 
	private String jtyb ;//��ͥ�ʱ� 
	private String jg ;//���� 
	private String xx ;//Ѫ�� 
	private String ah ;//���� 
	private String sfdk ;//�Ƿ���� 
	private String shgxxm1 ;//����ϵ����1 
	private String shgxgx1 ;//����ϵ��ϵ1 
	private String shgxgzdw1 ;//����ϵ������λ1 
	private String shgxzw1 ;//����ϵְ��1  
	private String shgxdwdh1 ;//����ϵ��λ�绰1 
	private String shgxsjhm1 ;//����ϵ�ֻ�����1 
	private String shgxxm2 ;//����ϵ����2 
	private String shgxgx2 ;//����ϵ��ϵ2 
	private String shgxgzdw2 ;//����ϵ������λ2 
	private String shgxzw2 ;//����ϵְ��2 
	private String shgxdwdh2 ;//����ϵ��λ�绰2 
	private String shgxsjhm2 ;//����ϵ�ֻ�����2 
	private String jtqkjj ;//��ͥ������ 
	private String jtjjqk ;//��ͥ������� 
	private String sjhm ;//�ֻ����� 
	private String byxx ;//��ҵ��Ϣ 
	private String kh ;//���� 
	private String rxrq ;//��ѧ���� 
	private String fdyxm ;//����Ա���� 
	private String gkcj ;//�߿��ɼ� 
	private String qqhm ;//QQ���� 
	private String hkxz ;//�������� 
	private String zyjb ;//רҵ���� 
	private String hkszd ;//�������ڵ� 
	private String ssyq ;//����Է�� 
	private String ssld ;//����¥�� 
	private String jtdzs ;//��ͥ��ַʡ 
	private String jtdzx ;//��ͥ��ַ���� 
	private String sfzsb ;//�Ƿ�ר���� 
	private String sfzfx ;//�Ƿ��ڷ�У 
	private String zjdm ;//�ڽ̴��� 
	private String sfyby ;//�Ƿ��ѱ�ҵ 
	private String byny ;//��ҵ���� 
	private String sfzx ;//�Ƿ���У 
	private String zw ;//ְ�� 
	private String thbs ;//�滻��ʶ 
	private String dybj ;//��ӡ��� 
	private String shbj ;//��˱�� 
	private String xwzsxlh ;//ѧλ֤�����к� 
	private String xwzsbh ;//ѧλ֤���� 
	private String xw ;//ѧλ 
	private String xzxm ;//У������ 
	private String zsxlh ;//֤�����к� 
	private String zsbh ;//֤���� 
	private String bjyjl ;//�Ͻ�ҵ���� 
	private String csd ;//�������� 
	private String zsjj ;//�������� 
	private String xxxs ;//ѧϰ��ʽ 
	private String bxlx ;//��ѧ���� 
	private String bxxs ;//��ѧ��ʽ 
	private String fxzyfx ;//����רҵ���� 
	private String fxzy ;//����רҵ 
	private String zylb ;//רҵ��� 
	private String dqszj ;//��ǰ���ڼ� 
	private String pyfx ;//�������� 
	private String zyfx ;//רҵ���� 
	private String xxszd ;//ѧУ���ڵ�  
	private String ksh ;//������ 
	private String xxfx ;//ѧϰ���� 
	private String zslb ;//������� 
	private String gj ;//���� 
	private String sfjh ;//�Ƿ��� 
	private String ccqj ;//�˳����� 
	private String byzffztdm ;//��ҵ֤����״̬���� 
	private String xwzsxxdz ;//У��ס����ϸ��ַ 

	
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getZd1() {
		return zd1;
	}
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	public String getZd2() {
		return zd2;
	}
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	public String getZd3() {
		return zd3;
	}
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	public String getZd4() {
		return zd4;
	}
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	public String getZd5() {
		return zd5;
	}
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}
	public String getJgs() {
		return jgs;
	}
	public void setJgs(String jgs) {
		this.jgs = jgs;
	}
	public String getJgshi() {
		return jgshi;
	}
	public void setJgshi(String jgshi) {
		this.jgshi = jgshi;
	}
	public String getJgx() {
		return jgx;
	}
	public void setJgx(String jgx) {
		this.jgx = jgx;
	}
	public String getSsbh() {
		return ssbh;
	}
	public void setSsbh(String ssbh) {
		this.ssbh = ssbh;
	}
	public String getRxnj() {
		return rxnj;
	}
	public void setRxnj(String rxnj) {
		this.rxnj = rxnj;
	}
	public String getNfby() {
		return nfby;
	}
	public void setNfby(String nfby) {
		this.nfby = nfby;
	}
	public String getSfzc() {
		return sfzc;
	}
	public void setSfzc(String sfzc) {
		this.sfzc = sfzc;
	}
	public String getDasfyl() {
		return dasfyl;
	}
	public void setDasfyl(String dasfyl) {
		this.dasfyl = dasfyl;
	}
	public String getDaylyy() {
		return daylyy;
	}
	public void setDaylyy(String daylyy) {
		this.daylyy = daylyy;
	}
	public String getYxdm() {
		return yxdm;
	}
	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
	}
	public String getSfzz() {
		return sfzz;
	}
	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}
	public String getSfsf() {
		return sfsf;
	}
	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
	}
	public String getSfdl() {
		return sfdl;
	}
	public void setSfdl(String sfdl) {
		this.sfdl = sfdl;
	}
	public String getDxhwp() {
		return dxhwp;
	}
	public void setDxhwp(String dxhwp) {
		this.dxhwp = dxhwp;
	}
	public String getBysj() {
		return bysj;
	}
	public void setBysj(String bysj) {
		this.bysj = bysj;
	}
	public String getZxwyyzdm() {
		return zxwyyzdm;
	}
	public void setZxwyyzdm(String zxwyyzdm) {
		this.zxwyyzdm = zxwyyzdm;
	}
	public String getWydj() {
		return wydj;
	}
	public void setWydj(String wydj) {
		this.wydj = wydj;
	}
	public String getJsjdj() {
		return jsjdj;
	}
	public void setJsjdj(String jsjdj) {
		this.jsjdj = jsjdj;
	}
	public String getLxdz() {
		return lxdz;
	}
	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}
	public String getYzbm() {
		return yzbm;
	}
	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}
	public String getShzw() {
		return shzw;
	}
	public void setShzw(String shzw) {
		this.shzw = shzw;
	}
	public String getJypx() {
		return jypx;
	}
	public void setJypx(String jypx) {
		this.jypx = jypx;
	}
	public String getXmsj() {
		return xmsj;
	}
	public void setXmsj(String xmsj) {
		this.xmsj = xmsj;
	}
	public String getZgzs() {
		return zgzs;
	}
	public void setZgzs(String zgzs) {
		this.zgzs = zgzs;
	}
	public String getJljn() {
		return jljn;
	}
	public void setJljn(String jljn) {
		this.jljn = jljn;
	}
	public String getSybz1() {
		return sybz1;
	}
	public void setSybz1(String sybz1) {
		this.sybz1 = sybz1;
	}
	public String getSybz2() {
		return sybz2;
	}
	public void setSybz2(String sybz2) {
		this.sybz2 = sybz2;
	}
	public String getSybz3() {
		return sybz3;
	}
	public void setSybz3(String sybz3) {
		this.sybz3 = sybz3;
	}
	public String getXldm() {
		return xldm;
	}
	public void setXldm(String xldm) {
		this.xldm = xldm;
	}
	public String getZkzh() {
		return zkzh;
	}
	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}
	public String getGrjl() {
		return grjl;
	}
	public void setGrjl(String grjl) {
		this.grjl = grjl;
	}
	public String getSfcj() {
		return sfcj;
	}
	public void setSfcj(String sfcj) {
		this.sfcj = sfcj;
	}
	public String getSsch() {
		return ssch;
	}
	public void setSsch(String ssch) {
		this.ssch = ssch;
	}
	public String getRzrq() {
		return rzrq;
	}
	public void setRzrq(String rzrq) {
		this.rzrq = rzrq;
	}
	public String getZsjzrq() {
		return zsjzrq;
	}
	public void setZsjzrq(String zsjzrq) {
		this.zsjzrq = zsjzrq;
	}
	public String getQsdh() {
		return qsdh;
	}
	public void setQsdh(String qsdh) {
		this.qsdh = qsdh;
	}
	public String getYkth() {
		return ykth;
	}
	public void setYkth(String ykth) {
		this.ykth = ykth;
	}
	public String getYhkh() {
		return yhkh;
	}
	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}
	public String getXslb() {
		return xslb;
	}
	public void setXslb(String xslb) {
		this.xslb = xslb;
	}
	public String getXslx() {
		return xslx;
	}
	public void setXslx(String xslx) {
		this.xslx = xslx;
	}
	public String getSfbys() {
		return sfbys;
	}
	public void setSfbys(String sfbys) {
		this.sfbys = sfbys;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getHkshen() {
		return hkshen;
	}
	public void setHkshen(String hkshen) {
		this.hkshen = hkshen;
	}
	public String getHkshi() {
		return hkshi;
	}
	public void setHkshi(String hkshi) {
		this.hkshi = hkshi;
	}
	public String getHkxian() {
		return hkxian;
	}
	public void setHkxian(String hkxian) {
		this.hkxian = hkxian;
	}
	public String getZcsxhm() {
		return zcsxhm;
	}
	public void setZcsxhm(String zcsxhm) {
		this.zcsxhm = zcsxhm;
	}
	public String getRxqwhcd() {
		return rxqwhcd;
	}
	public void setRxqwhcd(String rxqwhcd) {
		this.rxqwhcd = rxqwhcd;
	}
	public String getXsqrxxbz() {
		return xsqrxxbz;
	}
	public void setXsqrxxbz(String xsqrxxbz) {
		this.xsqrxxbz = xsqrxxbz;
	}
	public String getDah() {
		return dah;
	}
	public void setDah(String dah) {
		this.dah = dah;
	}
	public String getYlbxh() {
		return ylbxh;
	}
	public void setYlbxh(String ylbxh) {
		this.ylbxh = ylbxh;
	}
	public String getRxqdwdz() {
		return rxqdwdz;
	}
	public void setRxqdwdz(String rxqdwdz) {
		this.rxqdwdz = rxqdwdz;
	}
	public String getRxqdwyb() {
		return rxqdwyb;
	}
	public void setRxqdwyb(String rxqdwyb) {
		this.rxqdwyb = rxqdwyb;
	}
	public String getRxqdwdh() {
		return rxqdwdh;
	}
	public void setRxqdwdh(String rxqdwdh) {
		this.rxqdwdh = rxqdwdh;
	}
	public String getGzbx() {
		return gzbx;
	}
	public void setGzbx(String gzbx) {
		this.gzbx = gzbx;
	}
	public String getSfgat() {
		return sfgat;
	}
	public void setSfgat(String sfgat) {
		this.sfgat = sfgat;
	}
	public String getSfssmz() {
		return sfssmz;
	}
	public void setSfssmz(String sfssmz) {
		this.sfssmz = sfssmz;
	}
	public String getSfzd() {
		return sfzd;
	}
	public void setSfzd(String sfzd) {
		this.sfzd = sfzd;
	}
	public String getSyds() {
		return syds;
	}
	public void setSyds(String syds) {
		this.syds = syds;
	}
	public String getSydshi() {
		return sydshi;
	}
	public void setSydshi(String sydshi) {
		this.sydshi = sydshi;
	}
	public String getSydx() {
		return sydx;
	}
	public void setSydx(String sydx) {
		this.sydx = sydx;
	}
	public String getByzh() {
		return byzh;
	}
	public void setByzh(String byzh) {
		this.byzh = byzh;
	}
	public String getXjh() {
		return xjh;
	}
	public void setXjh(String xjh) {
		this.xjh = xjh;
	}
	public String getJrzzmmrq() {
		return jrzzmmrq;
	}
	public void setJrzzmmrq(String jrzzmmrq) {
		this.jrzzmmrq = jrzzmmrq;
	}
	public String getSfhq() {
		return sfhq;
	}
	public void setSfhq(String sfhq) {
		this.sfhq = sfhq;
	}
	public String getCsds() {
		return csds;
	}
	public void setCsds(String csds) {
		this.csds = csds;
	}
	public String getCsdshi() {
		return csdshi;
	}
	public void setCsdshi(String csdshi) {
		this.csdshi = csdshi;
	}
	public String getCsdxian() {
		return csdxian;
	}
	public void setCsdxian(String csdxian) {
		this.csdxian = csdxian;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getXydm() {
		return xydm;
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
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXmpy() {
		return xmpy;
	}
	public void setXmpy(String xmpy) {
		this.xmpy = xmpy;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getSyd() {
		return syd;
	}
	public void setSyd(String syd) {
		this.syd = syd;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getZzmm() {
		return zzmm;
	}
	public void setZzmm(String zzmm) {
		this.zzmm = zzmm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	public String getCym() {
		return cym;
	}
	public void setCym(String cym) {
		this.cym = cym;
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
	public String getTc() {
		return tc;
	}
	public void setTc(String tc) {
		this.tc = tc;
	}
	public String getKslb() {
		return kslb;
	}
	public void setKslb(String kslb) {
		this.kslb = kslb;
	}
	public String getRxfs() {
		return rxfs;
	}
	public void setRxfs(String rxfs) {
		this.rxfs = rxfs;
	}
	public String getPyfs() {
		return pyfs;
	}
	public void setPyfs(String pyfs) {
		this.pyfs = pyfs;
	}
	public String getPycc() {
		return pycc;
	}
	public void setPycc(String pycc) {
		this.pycc = pycc;
	}
	public String getXjlb() {
		return xjlb;
	}
	public void setXjlb(String xjlb) {
		this.xjlb = xjlb;
	}
	public String getXszp() {
		return xszp;
	}
	public void setXszp(String xszp) {
		this.xszp = xszp;
	}
	public String getXjztm() {
		return xjztm;
	}
	public void setXjztm(String xjztm) {
		this.xjztm = xjztm;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getWhcd() {
		return whcd;
	}
	public void setWhcd(String whcd) {
		this.whcd = whcd;
	}
	public String getRxqdw() {
		return rxqdw;
	}
	public void setRxqdw(String rxqdw) {
		this.rxqdw = rxqdw;
	}
	public String getJtdh() {
		return jtdh;
	}
	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}
	public String getJrgqtsj() {
		return jrgqtsj;
	}
	public void setJrgqtsj(String jrgqtsj) {
		this.jrgqtsj = jrgqtsj;
	}
	public String getJrgcdsj() {
		return jrgcdsj;
	}
	public void setJrgcdsj(String jrgcdsj) {
		this.jrgcdsj = jrgcdsj;
	}
	public String getJtcygc() {
		return jtcygc;
	}
	public void setJtcygc(String jtcygc) {
		this.jtcygc = jtcygc;
	}
	public String getJlcfjl() {
		return jlcfjl;
	}
	public void setJlcfjl(String jlcfjl) {
		this.jlcfjl = jlcfjl;
	}
	public String getJkzk() {
		return jkzk;
	}
	public void setJkzk(String jkzk) {
		this.jkzk = jkzk;
	}
	public String getJtdz() {
		return jtdz;
	}
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
	}
	public String getJtyb() {
		return jtyb;
	}
	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}
	public String getJg() {
		return jg;
	}
	public void setJg(String jg) {
		this.jg = jg;
	}
	public String getXx() {
		return xx;
	}
	public void setXx(String xx) {
		this.xx = xx;
	}
	public String getAh() {
		return ah;
	}
	public void setAh(String ah) {
		this.ah = ah;
	}
	public String getSfdk() {
		return sfdk;
	}
	public void setSfdk(String sfdk) {
		this.sfdk = sfdk;
	}
	public String getShgxxm1() {
		return shgxxm1;
	}
	public void setShgxxm1(String shgxxm1) {
		this.shgxxm1 = shgxxm1;
	}
	public String getShgxgx1() {
		return shgxgx1;
	}
	public void setShgxgx1(String shgxgx1) {
		this.shgxgx1 = shgxgx1;
	}
	public String getShgxgzdw1() {
		return shgxgzdw1;
	}
	public void setShgxgzdw1(String shgxgzdw1) {
		this.shgxgzdw1 = shgxgzdw1;
	}
	public String getShgxzw1() {
		return shgxzw1;
	}
	public void setShgxzw1(String shgxzw1) {
		this.shgxzw1 = shgxzw1;
	}
	public String getShgxdwdh1() {
		return shgxdwdh1;
	}
	public void setShgxdwdh1(String shgxdwdh1) {
		this.shgxdwdh1 = shgxdwdh1;
	}
	public String getShgxsjhm1() {
		return shgxsjhm1;
	}
	public void setShgxsjhm1(String shgxsjhm1) {
		this.shgxsjhm1 = shgxsjhm1;
	}
	public String getShgxxm2() {
		return shgxxm2;
	}
	public void setShgxxm2(String shgxxm2) {
		this.shgxxm2 = shgxxm2;
	}
	public String getShgxgx2() {
		return shgxgx2;
	}
	public void setShgxgx2(String shgxgx2) {
		this.shgxgx2 = shgxgx2;
	}
	public String getShgxgzdw2() {
		return shgxgzdw2;
	}
	public void setShgxgzdw2(String shgxgzdw2) {
		this.shgxgzdw2 = shgxgzdw2;
	}
	public String getShgxzw2() {
		return shgxzw2;
	}
	public void setShgxzw2(String shgxzw2) {
		this.shgxzw2 = shgxzw2;
	}
	public String getShgxdwdh2() {
		return shgxdwdh2;
	}
	public void setShgxdwdh2(String shgxdwdh2) {
		this.shgxdwdh2 = shgxdwdh2;
	}
	public String getShgxsjhm2() {
		return shgxsjhm2;
	}
	public void setShgxsjhm2(String shgxsjhm2) {
		this.shgxsjhm2 = shgxsjhm2;
	}
	public String getJtqkjj() {
		return jtqkjj;
	}
	public void setJtqkjj(String jtqkjj) {
		this.jtqkjj = jtqkjj;
	}
	public String getJtjjqk() {
		return jtjjqk;
	}
	public void setJtjjqk(String jtjjqk) {
		this.jtjjqk = jtjjqk;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getByxx() {
		return byxx;
	}
	public void setByxx(String byxx) {
		this.byxx = byxx;
	}
	public String getKh() {
		return kh;
	}
	public void setKh(String kh) {
		this.kh = kh;
	}
	public String getRxrq() {
		return rxrq;
	}
	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}
	public String getFdyxm() {
		return fdyxm;
	}
	public void setFdyxm(String fdyxm) {
		this.fdyxm = fdyxm;
	}
	public String getGkcj() {
		return gkcj;
	}
	public void setGkcj(String gkcj) {
		this.gkcj = gkcj;
	}
	public String getQqhm() {
		return qqhm;
	}
	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}
	public String getHkxz() {
		return hkxz;
	}
	public void setHkxz(String hkxz) {
		this.hkxz = hkxz;
	}
	public String getZyjb() {
		return zyjb;
	}
	public void setZyjb(String zyjb) {
		this.zyjb = zyjb;
	}
	public String getHkszd() {
		return hkszd;
	}
	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}
	public String getSsyq() {
		return ssyq;
	}
	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}
	public String getSsld() {
		return ssld;
	}
	public void setSsld(String ssld) {
		this.ssld = ssld;
	}
	public String getJtdzs() {
		return jtdzs;
	}
	public void setJtdzs(String jtdzs) {
		this.jtdzs = jtdzs;
	}
	public String getJtdzx() {
		return jtdzx;
	}
	public void setJtdzx(String jtdzx) {
		this.jtdzx = jtdzx;
	}
	public String getSfzsb() {
		return sfzsb;
	}
	public void setSfzsb(String sfzsb) {
		this.sfzsb = sfzsb;
	}
	public String getSfzfx() {
		return sfzfx;
	}
	public void setSfzfx(String sfzfx) {
		this.sfzfx = sfzfx;
	}
	public String getZjdm() {
		return zjdm;
	}
	public void setZjdm(String zjdm) {
		this.zjdm = zjdm;
	}
	public String getSfyby() {
		return sfyby;
	}
	public void setSfyby(String sfyby) {
		this.sfyby = sfyby;
	}
	public String getByny() {
		return byny;
	}
	public void setByny(String byny) {
		this.byny = byny;
	}
	public String getSfzx() {
		return sfzx;
	}
	public void setSfzx(String sfzx) {
		this.sfzx = sfzx;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public String getThbs() {
		return thbs;
	}
	public void setThbs(String thbs) {
		this.thbs = thbs;
	}
	public String getDybj() {
		return dybj;
	}
	public void setDybj(String dybj) {
		this.dybj = dybj;
	}
	public String getShbj() {
		return shbj;
	}
	public void setShbj(String shbj) {
		this.shbj = shbj;
	}
	public String getXwzsxlh() {
		return xwzsxlh;
	}
	public void setXwzsxlh(String xwzsxlh) {
		this.xwzsxlh = xwzsxlh;
	}
	public String getXwzsbh() {
		return xwzsbh;
	}
	public void setXwzsbh(String xwzsbh) {
		this.xwzsbh = xwzsbh;
	}
	public String getXw() {
		return xw;
	}
	public void setXw(String xw) {
		this.xw = xw;
	}
	public String getXzxm() {
		return xzxm;
	}
	public void setXzxm(String xzxm) {
		this.xzxm = xzxm;
	}
	public String getZsxlh() {
		return zsxlh;
	}
	public void setZsxlh(String zsxlh) {
		this.zsxlh = zsxlh;
	}
	public String getZsbh() {
		return zsbh;
	}
	public void setZsbh(String zsbh) {
		this.zsbh = zsbh;
	}
	public String getBjyjl() {
		return bjyjl;
	}
	public void setBjyjl(String bjyjl) {
		this.bjyjl = bjyjl;
	}
	public String getCsd() {
		return csd;
	}
	public void setCsd(String csd) {
		this.csd = csd;
	}
	public String getZsjj() {
		return zsjj;
	}
	public void setZsjj(String zsjj) {
		this.zsjj = zsjj;
	}
	public String getXxxs() {
		return xxxs;
	}
	public void setXxxs(String xxxs) {
		this.xxxs = xxxs;
	}
	public String getBxlx() {
		return bxlx;
	}
	public void setBxlx(String bxlx) {
		this.bxlx = bxlx;
	}
	public String getBxxs() {
		return bxxs;
	}
	public void setBxxs(String bxxs) {
		this.bxxs = bxxs;
	}
	public String getFxzyfx() {
		return fxzyfx;
	}
	public void setFxzyfx(String fxzyfx) {
		this.fxzyfx = fxzyfx;
	}
	public String getFxzy() {
		return fxzy;
	}
	public void setFxzy(String fxzy) {
		this.fxzy = fxzy;
	}
	public String getZylb() {
		return zylb;
	}
	public void setZylb(String zylb) {
		this.zylb = zylb;
	}
	public String getDqszj() {
		return dqszj;
	}
	public void setDqszj(String dqszj) {
		this.dqszj = dqszj;
	}
	public String getPyfx() {
		return pyfx;
	}
	public void setPyfx(String pyfx) {
		this.pyfx = pyfx;
	}
	public String getZyfx() {
		return zyfx;
	}
	public void setZyfx(String zyfx) {
		this.zyfx = zyfx;
	}
	public String getXxszd() {
		return xxszd;
	}
	public void setXxszd(String xxszd) {
		this.xxszd = xxszd;
	}
	public String getKsh() {
		return ksh;
	}
	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	public String getXxfx() {
		return xxfx;
	}
	public void setXxfx(String xxfx) {
		this.xxfx = xxfx;
	}
	public String getZslb() {
		return zslb;
	}
	public void setZslb(String zslb) {
		this.zslb = zslb;
	}
	public String getGj() {
		return gj;
	}
	public void setGj(String gj) {
		this.gj = gj;
	}
	public String getSfjh() {
		return sfjh;
	}
	public void setSfjh(String sfjh) {
		this.sfjh = sfjh;
	}
	public String getCcqj() {
		return ccqj;
	}
	public void setCcqj(String ccqj) {
		this.ccqj = ccqj;
	}
	public String getByzffztdm() {
		return byzffztdm;
	}
	public void setByzffztdm(String byzffztdm) {
		this.byzffztdm = byzffztdm;
	}
	public String getXwzsxxdz() {
		return xwzsxxdz;
	}
	public void setXwzsxxdz(String xwzsxxdz) {
		this.xwzsxxdz = xwzsxxdz;
	}
	
	
}
