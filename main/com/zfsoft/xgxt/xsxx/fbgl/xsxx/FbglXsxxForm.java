/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-26 ����09:31:44 
 */
package com.zfsoft.xgxt.xsxx.fbgl.xsxx;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����ѧ����Ϣ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-26 ����09:31:44
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglXsxxForm extends ActionForm {
	private static final long serialVersionUID = 4318040414346601054L;
	private String pk;// varchar2(100) n ����[�꼶+������]
	private String xh;// varchar2(20) y ѧ��
	private String xy;// varchar2(50) y ѧԺ����
	private String zymc;// varchar2(50) y רҵ����
	private String bjmc;// varchar2(50) y �༶����
	private String bjdm;// varchar2(20) y �༶����
	private String zydm;// varchar2(20) y רҵ����
	private String xydm;// varchar2(20) y ѧԺ����
	private String bz;// varchar2(1000) y ��ע
	private String xm;// varchar2(64) y ����
	private String xmpy;// varchar2(64) y ����ƴ��
	private String nj;// varchar2(10) y �꼶
	private String syd;// varchar2(255) y ��Դ��
	private String csrq;// varchar2(20) y ��������
	private String sfzh;// varchar2(64) y ���֤����
	private String xb;// varchar2(4) y �Ա�
	private String mz;// varchar2(64) y �������
	private String zzmm;// varchar2(20) y ������ò����
	private String lxdh;// varchar2(120) y ��ϵ�绰
	private String dzyx;// varchar2(64) y ��������
	private String cym;// varchar2(30) y ������
	private String sg;// varchar2(10) y ���
	private String tz;// varchar2(10) y ����
	private String tc;// varchar2(64) y �س�
	private String kslb;// varchar2(64) y �������
	private String rxfs;// varchar2(64) y ��ѧ��ʽ
	private String pyfs;// varchar2(64) y ������ʽ
	private String pycc;// varchar2(64) y �������
	private String xjlb;// varchar2(64) y ѧ�����
	private String xszp;// varchar2(64) y ѧ����Ƭ
	private String xjztm;// varchar2(30) y ѧ��״̬
	private String xz;// varchar2(4) y ѧ��
	private String whcd;// varchar2(20) y �Ļ��̶�
	private String rxqdw;// varchar2(255) y ԭ��ҵѧԺ
	private String jtdh;// varchar2(20) y ��ͥ�绰
	private String jrgqtsj;// varchar2(24) y ���빲����ʱ��
	private String jrgcdsj;// varchar2(24) y ���빲����ʱ��
	private String jtcygc;// varchar2(4) y ��ͥ��Ա����
	private String jlcfjl;// varchar2(300) y �������־���
	private String jkzk;// varchar2(255) y ���޲�ʷ
	private String jtdz;// varchar2(255) y ��ͥ��ϸ��ַ
	private String jtyb;// varchar2(20) y ��ͥ�ʱ�
	private String jg;// varchar2(255) y ����
	private String xx;// varchar2(20) y Ѫ��
	private String ah;// varchar2(255) y ����
	private String sfdk;// varchar2(4) y �Ƿ����
	private String shgxxm1;// varchar2(20) y ����ϵ����1
	private String shgxgx1;// varchar2(64) y ����ϵ��ϵ1
	private String shgxgzdw1;// varchar2(255) y ����ϵ������λ1
	private String shgxzw1;// varchar2(64) y ����ϵְ��1
	private String shgxdwdh1;// varchar2(20) y ����ϵ��λ�绰1
	private String shgxsjhm1;// varchar2(20) y ����ϵ�ֻ�����1
	private String shgxxm2;// varchar2(20) y ����ϵ����2
	private String shgxgx2;// varchar2(64) y ����ϵ��ϵ2
	private String shgxgzdw2;// varchar2(255) y ����ϵ������λ2
	private String shgxzw2;// varchar2(64) y ����ϵְ��2
	private String shgxdwdh2;// varchar2(20) y ����ϵ��λ�绰2
	private String shgxsjhm2;// varchar2(20) y ����ϵ�ֻ�����2
	private String jtqkjj;// varchar2(255) y ��ͥ������
	private String jtjjqk;// varchar2(255) y ��ͥ�������
	private String sjhm;// varchar2(20) y �ֻ�����
	private String byxx;// varchar2(1000) y ��ҵ��Ϣ
	private String kh;// varchar2(20) y ����
	private String rxrq;// varchar2(20) y ��ѧ����
	private String fdyxm;// varchar2(20) y ����Ա����
	private String gkcj;// varchar2(10) y �߿��ɼ�
	private String qqhm;// varchar2(20) y qq����
	private String hkxz;// varchar2(20) y ��������
	private String zyjb;// varchar2(20) y רҵ����
	private String hkszd;// varchar2(120) y �������ڵ�
	private String ssyq;// varchar2(64) y ����Է��
	private String ssld;// varchar2(64) y ����¥��
	private String jtdzs;// varchar2(64) y ��ͥ��ַʡ
	private String jtdzx;// varchar2(64) y ��ͥ��ַ����
	private String sfzsb;// varchar2(20) y �Ƿ�ר����
	private String sfzfx;// varchar2(10) y �Ƿ��ڷ�У
	private String zjdm;// varchar2(20) y �ڽ̴���
	private String sfyby;// varchar2(6) y '��' �Ƿ��ѱ�ҵ
	private String byny;// varchar2(10) y ��ҵ����
	private String sfzx;// varchar2(10) y �Ƿ���У
	private String zw;// varchar2(64) y ְ��
	private String thbs;// varchar2(10) y �滻��ʶ
	private String dybj;// varchar2(10) y ��ӡ���
	private String shbj;// varchar2(10) y ��˱��
	private String xwzsxlh;// varchar2(20) y ѧλ֤�����к�
	private String xwzsbh;// varchar2(20) y ѧλ֤����
	private String xw;// varchar2(32) y ѧλ
	private String xzxm;// varchar2(30) y У������
	private String zsxlh;// varchar2(20) y ֤�����к�
	private String zsbh;// varchar2(20) y ֤����
	private String bjyjl;// varchar2(20) y �Ͻ�ҵ����
	private String csd;// varchar2(64) y ��������
	private String zsjj;// varchar2(20) y ��������
	private String xxxs;// varchar2(32) y ѧϰ��ʽ
	private String bxlx;// varchar2(32) y ��ѧ����
	private String bxxs;// varchar2(32) y ��ѧ��ʽ
	private String fxzyfx;// varchar2(32) y ����רҵ����
	private String fxzy;// varchar2(32) y ����רҵ
	private String zylb;// varchar2(32) y רҵ���
	private String dqszj;// varchar2(4) y ��ǰ���ڼ�
	private String pyfx;// varchar2(32) y ��������
	private String zyfx;// varchar2(32) y רҵ����
	private String xxszd;// varchar2(64) y ѧУ���ڵ�
	private String ksh;// varchar2(32) y ������
	private String xxfx;// varchar2(64) y ѧϰ����
	private String zslb;// varchar2(64) y �������
	private String gj;// varchar2(100) y ����
	private String sfjh;// varchar2(4) y �Ƿ���
	private String ccqj;// varchar2(200) y �˳�����
	private String byzffztdm;// varchar2(20) y ��ҵ֤����״̬����
	private String xwzsxxdz;// varchar2(300) y У��ס����ϸ��ַ
	private String jgs;// varchar2(64) y ����ʡ
	private String jgshi;// varchar2(64) y ������
	private String jgx;// varchar2(64) y ������
	private String ssbh;// varchar2(20) y ������
	private String rxnj;// varchar2(4) y ��ѧ�꼶
	private String nfby;// varchar2(8) y �ܷ��ҵ
	private String sfzc;// varchar2(6) y �Ƿ�ע��
	private String dasfyl;// varchar2(6) y �����Ƿ�����
	private String daylyy;// varchar2(300) y ��������ԭ��
	private String yxdm;// varchar2(10) y ԺУ����
	private String sfzz;// varchar2(10) y �Ƿ���ְ
	private String sfsf;// varchar2(10) y �Ƿ�ʦ��
	private String sfdl;// varchar2(10) y �Ƿ����
	private String dxhwp;// varchar2(10) y �����ί��
	private String bysj;// varchar2(10) y ��ҵʱ��
	private String zxwyyzdm;// varchar2(10) y �����������ִ���
	private String wydj;// varchar2(32) y ����ȼ�
	private String jsjdj;// varchar2(32) y ������ȼ�
	private String lxdz;// varchar2(120) y ��ϵ��ַ
	private String yzbm;// varchar2(20) y ��������
	private String shzw;// varchar2(120) y ���ְ��
	private String jypx;// varchar2(300) y ������ѵ
	private String xmsj;// varchar2(300) y ��Ŀʵ��
	private String zgzs;// varchar2(300) y �ʸ�֤��
	private String jljn;// varchar2(300) y ��������
	private String sybz1;// varchar2(300) y ��Դ��ע1
	private String sybz2;// varchar2(300) y ��Դ��ע2
	private String sybz3;// varchar2(300) y ��Դ��ע3
	private String xldm;// varchar2(10) y ѧ������
	private String zkzh;// varchar2(20) y ׼��֤��
	private String grjl;// varchar2(2000) y ���˼���
	private String sfcj;// varchar2(4) y �Ƿ�м�/1��,0��
	private String ssch;// varchar2(10) y ���ᴲ��
	private String rzrq;// varchar2(10) y ס������
	private String zsjzrq;// varchar2(10) y ס�޽�ֹ����
	private String qsdh;// varchar2(20) y ���ҵ绰
	private String ykth;// varchar2(20) y һ��ͨ��
	private String yhkh;// varchar2(20) y ���п���
	private String xslb;// varchar2(30) y ѧ��������
	private String xslx;// varchar2(30) y ѧ�����ʹ���
	private String sfbys;// varchar2(2) y �Ƿ��ҵ��
	private String yhdm;// varchar2(3) y ���д���
	private String hkshen;// varchar2(30) y ��������ʡ
	private String hkshi;// varchar2(30) y ����������
	private String hkxian;// varchar2(30) y ����������
	private String zcsxhm;// varchar2(30) y ע��˳�����
	private String rxqwhcd;// varchar2(30) y ��ѧǰ�Ļ��̶�
	private String xsqrxxbz;// varchar2(4) y '��' ѧ��ȷ����Ϣ��־
	private String dah;// varchar2(60) y ������
	private String ylbxh;// varchar2(60) y ҽ�Ʊ��պ�
	private String rxqdwdz;// varchar2(200) y ԭ��ҵѧԺͨ�ŵ�ַ
	private String rxqdwyb;// varchar2(6) y ԭ��ҵѧԺ�ʱ�
	private String rxqdwdh;// varchar2(20) y ��ѧǰ��λ�绰
	private String gzbx;// varchar2(1000) y ���б���
	private String sfgat;// varchar2(4) y �Ƿ�۰�̨��
	private String sfssmz;// varchar2(4) y �Ƿ��������
	private String sfzd;// varchar2(6) y �Ƿ��߶�
	private String syds;// varchar2(65) y ��Դ��ʡ
	private String sydshi;// varchar2(64) y ��Դ����
	private String sydx;// varchar2(64) y ��Դ����
	private String byzh;// varchar2(30) y ��ҵ֤��
	private String xjh;// varchar2(40) y ѧ����
	private String jrzzmmrq;// varchar2(24) y ����������ò����
	private String sfhq;// varchar2(2) y �Ƿ���
	private String csds;// varchar2(64) y ������ʡ
	private String csdshi;// varchar2(64) y ��������
	private String csdxian;// varchar2(64) y ��������
	private String zd1;// varchar2(1000) y ��ѧǰ��λ���㶫����ѧԺ-ѧ���ţ�
	private String zd2;// varchar2(1000) y ��������㶫����ѧԺ-�ֻ��̺ţ�
	private String zd3;// varchar2(1000) y ΢�������㶫����ѧԺ-�������
	private String zd4;// varchar2(1000) y ΢����
	private String zd5;// varchar2(1000) y ��չ�ֶ�5
	private String xjlbdm;// varchar2(20) y ѧ��������
	private String bxhgz;// varchar2(20) y ��ѧ�Ź���
	private String fbgz;// varchar2(20) y �ְ����
	private String tdcj;//Ͷ���ɼ�
	private String lsh;
	private String zjmc;
	private String zzmmmc;
	private String sydmc;
	private String mzmc;
	private String jgmc;
	private String hkszdmc;
	//��չ�ֶ� ����ҳ����ʾ�����������
	private String pyccmc;
	
	
	private String xhqk;//ѧ�����
	private String fbqk;//�ְ����
	private String pzgzid;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//ҳ�����ӻ�ȡ��һ����Ӧ���� ƫ��������һ�� ƫ����Ϊ1��
	private String skewing="0";
	private Map<String, String> rowData = new HashMap<String, String>();
	
	private String tjzt;
	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}

	/**
	 * @param pkҪ���õ�
	 *            pk
	 */
	public void setPk(String pk) {
		this.pk = pk;
	}

	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param xhҪ���õ�
	 *            xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return the xy
	 */
	public String getXy() {
		return xy;
	}

	/**
	 * @param xyҪ���õ�
	 *            xy
	 */
	public void setXy(String xy) {
		this.xy = xy;
	}

	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}

	/**
	 * @param zymcҪ���õ�
	 *            zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}

	/**
	 * @param bjmcҪ���õ�
	 *            bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}

	/**
	 * @param bjdmҪ���õ�
	 *            bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}

	/**
	 * @param zydmҪ���õ�
	 *            zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}

	/**
	 * @param xydmҪ���õ�
	 *            xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bzҪ���õ�
	 *            bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}

	/**
	 * @param xmҪ���õ�
	 *            xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}

	/**
	 * @return the xmpy
	 */
	public String getXmpy() {
		return xmpy;
	}

	/**
	 * @param xmpyҪ���õ�
	 *            xmpy
	 */
	public void setXmpy(String xmpy) {
		this.xmpy = xmpy;
	}

	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}

	/**
	 * @param njҪ���õ�
	 *            nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}

	/**
	 * @return the syd
	 */
	public String getSyd() {
		return syd;
	}

	/**
	 * @param sydҪ���õ�
	 *            syd
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
	 * @param csrqҪ���õ�
	 *            csrq
	 */
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	/**
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}

	/**
	 * @param sfzhҪ���õ�
	 *            sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}

	/**
	 * @param xbҪ���õ�
	 *            xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}

	/**
	 * @return the mz
	 */
	public String getMz() {
		return mz;
	}

	/**
	 * @param mzҪ���õ�
	 *            mz
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
	 * @param zzmmҪ���õ�
	 *            zzmm
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
	 * @param lxdhҪ���õ�
	 *            lxdh
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
	 * @param dzyxҪ���õ�
	 *            dzyx
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
	 * @param cymҪ���õ�
	 *            cym
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
	 * @param sgҪ���õ�
	 *            sg
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
	 * @param tzҪ���õ�
	 *            tz
	 */
	public void setTz(String tz) {
		this.tz = tz;
	}

	/**
	 * @return the tc
	 */
	public String getTc() {
		return tc;
	}

	/**
	 * @param tcҪ���õ�
	 *            tc
	 */
	public void setTc(String tc) {
		this.tc = tc;
	}

	/**
	 * @return the kslb
	 */
	public String getKslb() {
		return kslb;
	}

	/**
	 * @param kslbҪ���õ�
	 *            kslb
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
	 * @param rxfsҪ���õ�
	 *            rxfs
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
	 * @param pyfsҪ���õ�
	 *            pyfs
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
	 * @param pyccҪ���õ�
	 *            pycc
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
	 * @param xjlbҪ���õ�
	 *            xjlb
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
	 * @param xszpҪ���õ�
	 *            xszp
	 */
	public void setXszp(String xszp) {
		this.xszp = xszp;
	}

	/**
	 * @return the xjztm
	 */
	public String getXjztm() {
		return xjztm;
	}

	/**
	 * @param xjztmҪ���õ�
	 *            xjztm
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
	 * @param xzҪ���õ�
	 *            xz
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
	 * @param whcdҪ���õ�
	 *            whcd
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
	 * @param rxqdwҪ���õ�
	 *            rxqdw
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
	 * @param jtdhҪ���õ�
	 *            jtdh
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
	 * @param jrgqtsjҪ���õ�
	 *            jrgqtsj
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
	 * @param jrgcdsjҪ���õ�
	 *            jrgcdsj
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
	 * @param jtcygcҪ���õ�
	 *            jtcygc
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
	 * @param jlcfjlҪ���õ�
	 *            jlcfjl
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
	 * @param jkzkҪ���õ�
	 *            jkzk
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
	 * @param jtdzҪ���õ�
	 *            jtdz
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
	 * @param jtybҪ���õ�
	 *            jtyb
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
	 * @param jgҪ���õ�
	 *            jg
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
	 * @param xxҪ���õ�
	 *            xx
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
	 * @param ahҪ���õ�
	 *            ah
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
	 * @param sfdkҪ���õ�
	 *            sfdk
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
	 * @param shgxxm1Ҫ���õ�
	 *            shgxxm1
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
	 * @param shgxgx1Ҫ���õ�
	 *            shgxgx1
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
	 * @param shgxgzdw1Ҫ���õ�
	 *            shgxgzdw1
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
	 * @param shgxzw1Ҫ���õ�
	 *            shgxzw1
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
	 * @param shgxdwdh1Ҫ���õ�
	 *            shgxdwdh1
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
	 * @param shgxsjhm1Ҫ���õ�
	 *            shgxsjhm1
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
	 * @param shgxxm2Ҫ���õ�
	 *            shgxxm2
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
	 * @param shgxgx2Ҫ���õ�
	 *            shgxgx2
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
	 * @param shgxgzdw2Ҫ���õ�
	 *            shgxgzdw2
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
	 * @param shgxzw2Ҫ���õ�
	 *            shgxzw2
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
	 * @param shgxdwdh2Ҫ���õ�
	 *            shgxdwdh2
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
	 * @param shgxsjhm2Ҫ���õ�
	 *            shgxsjhm2
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
	 * @param jtqkjjҪ���õ�
	 *            jtqkjj
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
	 * @param jtjjqkҪ���õ�
	 *            jtjjqk
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
	 * @param sjhmҪ���õ�
	 *            sjhm
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
	 * @param byxxҪ���õ�
	 *            byxx
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
	 * @param khҪ���õ�
	 *            kh
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
	 * @param rxrqҪ���õ�
	 *            rxrq
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
	 * @param fdyxmҪ���õ�
	 *            fdyxm
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
	 * @param gkcjҪ���õ�
	 *            gkcj
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
	 * @param qqhmҪ���õ�
	 *            qqhm
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
	 * @param hkxzҪ���õ�
	 *            hkxz
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
	 * @param zyjbҪ���õ�
	 *            zyjb
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
	 * @param hkszdҪ���õ�
	 *            hkszd
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
	 * @param ssyqҪ���õ�
	 *            ssyq
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
	 * @param ssldҪ���õ�
	 *            ssld
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
	 * @param jtdzsҪ���õ�
	 *            jtdzs
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
	 * @param jtdzxҪ���õ�
	 *            jtdzx
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
	 * @param sfzsbҪ���õ�
	 *            sfzsb
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
	 * @param sfzfxҪ���õ�
	 *            sfzfx
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
	 * @param zjdmҪ���õ�
	 *            zjdm
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
	 * @param sfybyҪ���õ�
	 *            sfyby
	 */
	public void setSfyby(String sfyby) {
		this.sfyby = sfyby;
	}

	/**
	 * @return the byny
	 */
	public String getByny() {
		return byny;
	}

	/**
	 * @param bynyҪ���õ�
	 *            byny
	 */
	public void setByny(String byny) {
		this.byny = byny;
	}

	/**
	 * @return the sfzx
	 */
	public String getSfzx() {
		return sfzx;
	}

	/**
	 * @param sfzxҪ���õ�
	 *            sfzx
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
	 * @param zwҪ���õ�
	 *            zw
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
	 * @param thbsҪ���õ�
	 *            thbs
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
	 * @param dybjҪ���õ�
	 *            dybj
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
	 * @param shbjҪ���õ�
	 *            shbj
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
	 * @param xwzsxlhҪ���õ�
	 *            xwzsxlh
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
	 * @param xwzsbhҪ���õ�
	 *            xwzsbh
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
	 * @param xwҪ���õ�
	 *            xw
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
	 * @param xzxmҪ���õ�
	 *            xzxm
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
	 * @param zsxlhҪ���õ�
	 *            zsxlh
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
	 * @param zsbhҪ���õ�
	 *            zsbh
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
	 * @param bjyjlҪ���õ�
	 *            bjyjl
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
	 * @param csdҪ���õ�
	 *            csd
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
	 * @param zsjjҪ���õ�
	 *            zsjj
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
	 * @param xxxsҪ���õ�
	 *            xxxs
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
	 * @param bxlxҪ���õ�
	 *            bxlx
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
	 * @param bxxsҪ���õ�
	 *            bxxs
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
	 * @param fxzyfxҪ���õ�
	 *            fxzyfx
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
	 * @param fxzyҪ���õ�
	 *            fxzy
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
	 * @param zylbҪ���õ�
	 *            zylb
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
	 * @param dqszjҪ���õ�
	 *            dqszj
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
	 * @param pyfxҪ���õ�
	 *            pyfx
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
	 * @param zyfxҪ���õ�
	 *            zyfx
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
	 * @param xxszdҪ���õ�
	 *            xxszd
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
	 * @param kshҪ���õ�
	 *            ksh
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
	 * @param xxfxҪ���õ�
	 *            xxfx
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
	 * @param zslbҪ���õ�
	 *            zslb
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
	 * @param gjҪ���õ�
	 *            gj
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
	 * @param sfjhҪ���õ�
	 *            sfjh
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
	 * @param ccqjҪ���õ�
	 *            ccqj
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
	 * @param byzffztdmҪ���õ�
	 *            byzffztdm
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
	 * @param xwzsxxdzҪ���õ�
	 *            xwzsxxdz
	 */
	public void setXwzsxxdz(String xwzsxxdz) {
		this.xwzsxxdz = xwzsxxdz;
	}

	/**
	 * @return the jgs
	 */
	public String getJgs() {
		return jgs;
	}

	/**
	 * @param jgsҪ���õ�
	 *            jgs
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
	 * @param jgshiҪ���õ�
	 *            jgshi
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
	 * @param jgxҪ���õ�
	 *            jgx
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
	 * @param ssbhҪ���õ�
	 *            ssbh
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
	 * @param rxnjҪ���õ�
	 *            rxnj
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
	 * @param nfbyҪ���õ�
	 *            nfby
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
	 * @param sfzcҪ���õ�
	 *            sfzc
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
	 * @param dasfylҪ���õ�
	 *            dasfyl
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
	 * @param daylyyҪ���õ�
	 *            daylyy
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
	 * @param yxdmҪ���õ�
	 *            yxdm
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
	 * @param sfzzҪ���õ�
	 *            sfzz
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
	 * @param sfsfҪ���õ�
	 *            sfsf
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
	 * @param sfdlҪ���õ�
	 *            sfdl
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
	 * @param dxhwpҪ���õ�
	 *            dxhwp
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
	 * @param bysjҪ���õ�
	 *            bysj
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
	 * @param zxwyyzdmҪ���õ�
	 *            zxwyyzdm
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
	 * @param wydjҪ���õ�
	 *            wydj
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
	 * @param jsjdjҪ���õ�
	 *            jsjdj
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
	 * @param lxdzҪ���õ�
	 *            lxdz
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
	 * @param yzbmҪ���õ�
	 *            yzbm
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
	 * @param shzwҪ���õ�
	 *            shzw
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
	 * @param jypxҪ���õ�
	 *            jypx
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
	 * @param xmsjҪ���õ�
	 *            xmsj
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
	 * @param zgzsҪ���õ�
	 *            zgzs
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
	 * @param jljnҪ���õ�
	 *            jljn
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
	 * @param sybz1Ҫ���õ�
	 *            sybz1
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
	 * @param sybz2Ҫ���õ�
	 *            sybz2
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
	 * @param sybz3Ҫ���õ�
	 *            sybz3
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
	 * @param xldmҪ���õ�
	 *            xldm
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
	 * @param zkzhҪ���õ�
	 *            zkzh
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
	 * @param grjlҪ���õ�
	 *            grjl
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
	 * @param sfcjҪ���õ�
	 *            sfcj
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
	 * @param sschҪ���õ�
	 *            ssch
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
	 * @param rzrqҪ���õ�
	 *            rzrq
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
	 * @param zsjzrqҪ���õ�
	 *            zsjzrq
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
	 * @param qsdhҪ���õ�
	 *            qsdh
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
	 * @param ykthҪ���õ�
	 *            ykth
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
	 * @param yhkhҪ���õ�
	 *            yhkh
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
	 * @param xslbҪ���õ�
	 *            xslb
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
	 * @param xslxҪ���õ�
	 *            xslx
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
	 * @param sfbysҪ���õ�
	 *            sfbys
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
	 * @param yhdmҪ���õ�
	 *            yhdm
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
	 * @param hkshenҪ���õ�
	 *            hkshen
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
	 * @param hkshiҪ���õ�
	 *            hkshi
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
	 * @param hkxianҪ���õ�
	 *            hkxian
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
	 * @param zcsxhmҪ���õ�
	 *            zcsxhm
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
	 * @param rxqwhcdҪ���õ�
	 *            rxqwhcd
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
	 * @param xsqrxxbzҪ���õ�
	 *            xsqrxxbz
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
	 * @param dahҪ���õ�
	 *            dah
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
	 * @param ylbxhҪ���õ�
	 *            ylbxh
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
	 * @param rxqdwdzҪ���õ�
	 *            rxqdwdz
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
	 * @param rxqdwybҪ���õ�
	 *            rxqdwyb
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
	 * @param rxqdwdhҪ���õ�
	 *            rxqdwdh
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
	 * @param gzbxҪ���õ�
	 *            gzbx
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
	 * @param sfgatҪ���õ�
	 *            sfgat
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
	 * @param sfssmzҪ���õ�
	 *            sfssmz
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
	 * @param sfzdҪ���õ�
	 *            sfzd
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
	 * @param sydsҪ���õ�
	 *            syds
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
	 * @param sydshiҪ���õ�
	 *            sydshi
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
	 * @param sydxҪ���õ�
	 *            sydx
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
	 * @param byzhҪ���õ�
	 *            byzh
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
	 * @param xjhҪ���õ�
	 *            xjh
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
	 * @param jrzzmmrqҪ���õ�
	 *            jrzzmmrq
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
	 * @param sfhqҪ���õ�
	 *            sfhq
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
	 * @param csdsҪ���õ�
	 *            csds
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
	 * @param csdshiҪ���õ�
	 *            csdshi
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
	 * @param csdxianҪ���õ�
	 *            csdxian
	 */
	public void setCsdxian(String csdxian) {
		this.csdxian = csdxian;
	}

	/**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}

	/**
	 * @param zd1Ҫ���õ�
	 *            zd1
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
	 * @param zd2Ҫ���õ�
	 *            zd2
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
	 * @param zd3Ҫ���õ�
	 *            zd3
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
	 * @param zd4Ҫ���õ�
	 *            zd4
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
	 * @param zd5Ҫ���õ�
	 *            zd5
	 */
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}

	/**
	 * @return the xjlbdm
	 */
	public String getXjlbdm() {
		return xjlbdm;
	}

	/**
	 * @param xjlbdmҪ���õ�
	 *            xjlbdm
	 */
	public void setXjlbdm(String xjlbdm) {
		this.xjlbdm = xjlbdm;
	}

	/**
	 * @return the bxhgz
	 */
	public String getBxhgz() {
		return bxhgz;
	}

	/**
	 * @param bxhgzҪ���õ�
	 *            bxhgz
	 */
	public void setBxhgz(String bxhgz) {
		this.bxhgz = bxhgz;
	}

	/**
	 * @return the fbgz
	 */
	public String getFbgz() {
		return fbgz;
	}

	/**
	 * @param fbgzҪ���õ�
	 *            fbgz
	 */
	public void setFbgz(String fbgz) {
		this.fbgz = fbgz;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param typeҪ���õ�
	 *            type
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
	 * @param pagesҪ���õ�
	 *            pages
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
	 * @param searchModelҪ���õ�
	 *            searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	/**
	 * @return the tdcj
	 */
	public String getTdcj() {
		return tdcj;
	}

	/**
	 * @param tdcjҪ���õ� tdcj
	 */
	public void setTdcj(String tdcj) {
		this.tdcj = tdcj;
	}

	/**
	 * @return the pzgzid
	 */
	public String getPzgzid() {
		return pzgzid;
	}

	/**
	 * @param pzgzidҪ���õ� pzgzid
	 */
	public void setPzgzid(String pzgzid) {
		this.pzgzid = pzgzid;
	}

	/**
	 * @return the skewing
	 */
	public String getSkewing() {
		return skewing;
	}

	/**
	 * @param skewingҪ���õ� skewing
	 */
	public void setSkewing(String skewing) {
		this.skewing = skewing;
	}

	/**
	 * @return the lsh
	 */
	public String getLsh() {
		return lsh;
	}

	/**
	 * @param lshҪ���õ� lsh
	 */
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	/**
	 * @return the rowData
	 */
	public Map<String, String> getRowData() {
		return rowData;
	}

	/**
	 * @param rowDataҪ���õ� rowData
	 */
	public void setRowData(Map<String, String> rowData) {
		this.rowData = rowData;
	}


	/**
	 * @return the tjzt
	 */
	public String getTjzt() {
		return tjzt;
	}

	/**
	 * @param tjztҪ���õ� tjzt
	 */
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}

	/**
	 * @return the xhqk
	 */
	public String getXhqk() {
		return xhqk;
	}

	/**
	 * @param xhqkҪ���õ� xhqk
	 */
	public void setXhqk(String xhqk) {
		this.xhqk = xhqk;
	}

	/**
	 * @return the fbqk
	 */
	public String getFbqk() {
		return fbqk;
	}

	/**
	 * @param fbqkҪ���õ� fbqk
	 */
	public void setFbqk(String fbqk) {
		this.fbqk = fbqk;
	}

	/**
	 * @return the zjmc
	 */
	public String getZjmc() {
		return zjmc;
	}

	/**
	 * @param zjmcҪ���õ� zjmc
	 */
	public void setZjmc(String zjmc) {
		this.zjmc = zjmc;
	}

	/**
	 * @return the zzmmmc
	 */
	public String getZzmmmc() {
		return zzmmmc;
	}

	/**
	 * @param zzmmmcҪ���õ� zzmmmc
	 */
	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}

	/**
	 * @return the sydmc
	 */
	public String getSydmc() {
		return sydmc;
	}

	/**
	 * @param sydmcҪ���õ� sydmc
	 */
	public void setSydmc(String sydmc) {
		this.sydmc = sydmc;
	}

	/**
	 * @return the mzmc
	 */
	public String getMzmc() {
		return mzmc;
	}

	/**
	 * @param mzmcҪ���õ� mzmc
	 */
	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}

	/**
	 * @return the jgmc
	 */
	public String getJgmc() {
		return jgmc;
	}

	/**
	 * @param jgmcҪ���õ� jgmc
	 */
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	/**
	 * @return the hkszdmc
	 */
	public String getHkszdmc() {
		return hkszdmc;
	}

	/**
	 * @param hkszdmcҪ���õ� hkszdmc
	 */
	public void setHkszdmc(String hkszdmc) {
		this.hkszdmc = hkszdmc;
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
	
}
