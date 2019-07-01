/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����02:44:58 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.Serializable;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @�๦������: ˼������--����Ա��Ϣ
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-7-9 ����02:44:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdyxxModel extends SuperAuditModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String gwlbdm ;//��λ������ 
	private String khyh ;//�������� 
	private String hyzk ;//����״�����ѻ飬δ�� 
	private String fdyzbs ;//����Աֵ���� 
	private String fdyrzrq ;//����Ա��ְ���ڣ���ʱ���θ���Ա�� 
	private String zyjnzs ;//ְҵ����֤�飬ѡ�����ޡ�������ѯʦһ����������ѯʦ�����ȣ��������Ŀ 
	private String zyjnzs2 ;//ְҵ����֤�飬ѡ�����ޡ�������ѯʦһ����������ѯʦ�����ȣ��������Ŀ 
	private String zyjnzs3 ;//ְҵ����֤�飬ѡ�����ޡ�������ѯʦһ����������ѯʦ�����ȣ��������Ŀ 
	private String age ;//����Ա���� 
	private String yhzh ;//�����˺� 
	private String zzshen ;//ʡ 
	private String zzshi ;//�� 
	private String zzxian ;//�� 
	private String zgh ;//ְ���� 
	private String xm ;//���� 
	private String xb ;//�Ա���� 
	private String lxdh ;//��ϵ�绰 
	private String bmdm ;//���ڲ��Ŵ��� 
	private String zw ;//ְ�� 
	private String zyzz ;//��Ҫְ�� 
	private String zzmm ;//������ò���� 
	private String xl ;//ѧ�� 
	private String csrq ;//�������� 
	private String grhjqk ;//���˻���� 
	private String gzjl ;//�������� 
	private String bz ;//��ע 
	private String mz ;//������� 
	private String jg ;//������� 
	private String zc ;//ְ�� 
	private String lxgzsj ;//��У����ʱ�� 
	private String xsgzyjfx ;//ѧ�������о����� 
	private String jtzz ;//��ͥסַ 
	private String yddh ;//�ƶ��绰 
	private String dzyx ;//�������� 
	private String fblw ;//�������� 
	private String kyjl ;//���о��� 
	private String byyx ;//��ҵԺУ 
	private String sxzy ;//��ѧרҵ 
	private String yzbm ;// 
	private String xw ;//ѧλ 
	private String rwsj ;//����ʱ�� 
	private String gzfg ;//�����ֹ� 
	private String csfdysj ;//ר(��)ְ 
	private String zjz ;//ר(��)ְ 
	private String zwrzsj ;//��ְʱ�� 
	private String jsrzsj ;//������ְʱ�� 
	private String sjdw ;//�ϼ���λ 
	private String txdz ;//ͨѶ��ַ 
	private String bgdh ;//�칫�绰 
	private String cz ;//���� 
	private String jrgz ;//���ι��� 
	private String jgxs ;//�������� 
	private String sg ;//��� 
	private String tz ;//���� 
	private String tc ;//�س� 
	private String jb ;//���� 
	private String djsj ;//����ʱ�� 
	private String szgzsj ;//˼������ʱ�� 
	private String bkbyyx ;//��������ҵԺУ 
	private String bksxzy ;//��������ѧרҵ 
	private String ssbyyx ;//˶ʿ��ҵԺУ 
	private String ssbyzy ;//˶ʿ��ѧרҵ 
	private String bsbyyx ;//��ʿ��ҵԺУ 
	private String bsbyzy ;//��ʿ��ѧרҵ 
	private String sfzh ;//���֤�� 
	private String cjgzrq ;//�μӹ������� 
	private String sfbl ;//�Ƿ�B�� 1��ʾ-�� 0��ʾ�� 
	private String zhxwssxk ;//���ѧλ����ѧ�� 
	private String pxqk ;//��ѵ��� 
	private String csgz ;//���¹��� 
	private String bgdd ;//�칫�ص� 
	private String zdxl ;//�ڶ�ѧ�� 
	private String zdzy ;//�ڶ�רҵ 
	private String gdxlzl ;//��������ѧ������ 
	private String zyyjjg ;//��Ҫ�о��ɹ� 
	private String fgnj ;//�ֹ��꼶 
	private String qtfgnj ;//�����ֹ��꼶 
	private String fdyz ;//����Ա�� 
	private String fglxdm ;// 
	private String dwlbdm ;//��λ������ 
	private String kzzd1 ;//΢���� 
	private String kzzd2 ;//΢���� 
	private String kzzd3 ;//QQ 
	private String kzzd4 ;//�������� 
	private String kzzd5 ;//��չ�ֶ�5 
	private String sfjryx ;//�Ƿ����Ժϵ��ʦ 
	private String kzzd6 ;//��չ�ֶ�6 
	private String kzzd7 ;//��չ�ֶ�7 
	private String kzzd8 ;//��չ�ֶ�8 
	private String kzzd9 ;//��չ�ֶ�9 
	private String kzzd10 ;//��չ�ֶ�10 
	private String kzzd11 ;//��չ�ֶ�11 
	private String kzzd12 ;//��չ�ֶ�12 
	private String kzzd13 ;//��չ�ֶ�13 
	private String kzzd14 ;//��չ�ֶ�14 
	private String kzzd15 ;//��չ�ֶ�15 
	private String kzzd16 ;//��չ�ֶ�16 
	private String kzzd17 ;//��չ�ֶ�17 
	private String kzzd18 ;//��չ�ֶ�18 
	private String kzzd19 ;//��չ�ֶ�19 
	private String kzzd20 ;//��չ�ֶ�20 
	
	private String xgzdJson;
	private String sqid;
	private String sqjg;
	private String xgsj;
	
	private String shr;
	private String ywid;
	private String sqrid;
	private String shlc;
	private String tzlj;
	private String tzljsq;
	
	private String jssf;//��ʦ��� 
	
	private String sfdw;
	
	private String bzxm;
	
	private String bzlxdh;
	
	private String sydm; //��Ժ����  ��������Ի�
	
	private String jtdzx;//������ͨ��ͥ��ַ
	private String xwpx;
	private String rdsj;
	private String sjhm;
	private String qqhm;
	private String wxh;
	private String ssh;
	private String xqah;
	private String xl_qrz;
	private String xw_qrz;
	private String byyx_qrz;
	private String zymc_qrz;
	private String qsrq_qrz;
	private String zzrq_qrz;
	private String xl_zz;
	private String xw_zz;
	private String byyx_zz;
	private String zymc_zz;
	private String qsrq_zz;
	private String zzrq_zz;
	private String rxrq;
	private String zgqk;
	private String zhuanzhi;
	private String zzssbm;
	private String jzssbm;
	private String gznx;
	private String sfdb;
	private String xdbj;
	private String dbrs;
	private String zyjszw;
	private String jb_13431;
	private String tsgz;
	private String rdrq;//�뵳����
	private String zydm;//רҵ
	private String zw_13431;//ְ��
	private String xdbqk;
	private String bjs;
	private String sfyjszgz;
	private String skqk;
	private String slfx1;
	private String slfx2;
	private String shjg;
	private String jslbdm;
	private String zgzt;
	private String lxbz;
	private String sfzg;
	private String sdzb;
	private String bzbbz;
	private String yjfx;
	private String gzzz;
	private String rxgzsj;
	private String rxszbm;
	private String rxszgw;
	private String sfdnsjzfdy;
	private String drsj;
	private String lxbm;
	private String xlwb;
	private String jjlxr;
	private String jjlxrdh;
	private String sfzb;
	private String sfdrsjzfdy;

	public String getSfzb() {
		return sfzb;
	}

	public void setSfzb(String sfzb) {
		this.sfzb = sfzb;
	}

	public String getSfdrsjzfdy() {
		return sfdrsjzfdy;
	}

	public void setSfdrsjzfdy(String sfdrsjzfdy) {
		this.sfdrsjzfdy = sfdrsjzfdy;
	}

	public String getZgzt() {
		return zgzt;
	}

	public void setZgzt(String zgzt) {
		this.zgzt = zgzt;
	}

	public String getLxbz() {
		return lxbz;
	}

	public void setLxbz(String lxbz) {
		this.lxbz = lxbz;
	}

	public String getSfzg() {
		return sfzg;
	}

	public void setSfzg(String sfzg) {
		this.sfzg = sfzg;
	}

	public String getSdzb() {
		return sdzb;
	}

	public void setSdzb(String sdzb) {
		this.sdzb = sdzb;
	}

	public String getBzbbz() {
		return bzbbz;
	}

	public void setBzbbz(String bzbbz) {
		this.bzbbz = bzbbz;
	}

	public String getYjfx() {
		return yjfx;
	}

	public void setYjfx(String yjfx) {
		this.yjfx = yjfx;
	}

	public String getGzzz() {
		return gzzz;
	}

	public void setGzzz(String gzzz) {
		this.gzzz = gzzz;
	}

	public String getRxgzsj() {
		return rxgzsj;
	}

	public void setRxgzsj(String rxgzsj) {
		this.rxgzsj = rxgzsj;
	}

	public String getRxszbm() {
		return rxszbm;
	}

	public void setRxszbm(String rxszbm) {
		this.rxszbm = rxszbm;
	}

	public String getRxszgw() {
		return rxszgw;
	}

	public void setRxszgw(String rxszgw) {
		this.rxszgw = rxszgw;
	}

	public String getSfdnsjzfdy() {
		return sfdnsjzfdy;
	}

	public void setSfdnsjzfdy(String sfdnsjzfdy) {
		this.sfdnsjzfdy = sfdnsjzfdy;
	}

	public String getDrsj() {
		return drsj;
	}

	public void setDrsj(String drsj) {
		this.drsj = drsj;
	}

	public String getLxbm() {
		return lxbm;
	}

	public void setLxbm(String lxbm) {
		this.lxbm = lxbm;
	}

	public String getXlwb() {
		return xlwb;
	}

	public void setXlwb(String xlwb) {
		this.xlwb = xlwb;
	}

	public String getJjlxr() {
		return jjlxr;
	}

	public void setJjlxr(String jjlxr) {
		this.jjlxr = jjlxr;
	}

	public String getJjlxrdh() {
		return jjlxrdh;
	}

	public void setJjlxrdh(String jjlxrdh) {
		this.jjlxrdh = jjlxrdh;
	}

	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @return the jslbdm
	 */
	public String getJslbdm() {
		return jslbdm;
	}
	/**
	 * @param jslbdmҪ���õ� jslbdm
	 */
	public void setJslbdm(String jslbdm) {
		this.jslbdm = jslbdm;
	}
	/**
	 * @param shjgҪ���õ� shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the xdbqk
	 */
	public String getXdbqk() {
		return xdbqk;
	}
	/**
	 * @param xdbqkҪ���õ� xdbqk
	 */
	public void setXdbqk(String xdbqk) {
		this.xdbqk = xdbqk;
	}
	/**
	 * @return the bjs
	 */
	public String getBjs() {
		return bjs;
	}
	/**
	 * @param bjsҪ���õ� bjs
	 */
	public void setBjs(String bjs) {
		this.bjs = bjs;
	}
	/**
	 * @return the sfyjszgz
	 */
	public String getSfyjszgz() {
		return sfyjszgz;
	}
	/**
	 * @param sfyjszgzҪ���õ� sfyjszgz
	 */
	public void setSfyjszgz(String sfyjszgz) {
		this.sfyjszgz = sfyjszgz;
	}
	/**
	 * @return the skqk
	 */
	public String getSkqk() {
		return skqk;
	}
	/**
	 * @param skqkҪ���õ� skqk
	 */
	public void setSkqk(String skqk) {
		this.skqk = skqk;
	}
	/**
	 * @return the slfx1
	 */
	public String getSlfx1() {
		return slfx1;
	}
	/**
	 * @param slfx1Ҫ���õ� slfx1
	 */
	public void setSlfx1(String slfx1) {
		this.slfx1 = slfx1;
	}
	/**
	 * @return the slfx2
	 */
	public String getSlfx2() {
		return slfx2;
	}
	/**
	 * @param slfx2Ҫ���õ� slfx2
	 */
	public void setSlfx2(String slfx2) {
		this.slfx2 = slfx2;
	}
	/**
	 * @return the xwpx
	 */
	public String getXwpx() {
		return xwpx;
	}
	/**
	 * @param xwpxҪ���õ� xwpx
	 */
	public void setXwpx(String xwpx) {
		this.xwpx = xwpx;
	}
	/**
	 * @return the rdsj
	 */
	public String getRdsj() {
		return rdsj;
	}
	/**
	 * @param rdsjҪ���õ� rdsj
	 */
	public void setRdsj(String rdsj) {
		this.rdsj = rdsj;
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
	 * @return the wxh
	 */
	public String getWxh() {
		return wxh;
	}
	/**
	 * @param wxhҪ���õ� wxh
	 */
	public void setWxh(String wxh) {
		this.wxh = wxh;
	}
	/**
	 * @return the ssh
	 */
	public String getSsh() {
		return ssh;
	}
	/**
	 * @param sshҪ���õ� ssh
	 */
	public void setSsh(String ssh) {
		this.ssh = ssh;
	}
	/**
	 * @return the xqah
	 */
	public String getXqah() {
		return xqah;
	}
	/**
	 * @param xqahҪ���õ� xqah
	 */
	public void setXqah(String xqah) {
		this.xqah = xqah;
	}
	/**
	 * @return the xl_qrz
	 */
	public String getXl_qrz() {
		return xl_qrz;
	}
	/**
	 * @param xlQrzҪ���õ� xl_qrz
	 */
	public void setXl_qrz(String xlQrz) {
		xl_qrz = xlQrz;
	}
	/**
	 * @return the xw_qrz
	 */
	public String getXw_qrz() {
		return xw_qrz;
	}
	/**
	 * @param xwQrzҪ���õ� xw_qrz
	 */
	public void setXw_qrz(String xwQrz) {
		xw_qrz = xwQrz;
	}
	/**
	 * @return the byyx_qrz
	 */
	public String getByyx_qrz() {
		return byyx_qrz;
	}
	/**
	 * @param byyxQrzҪ���õ� byyx_qrz
	 */
	public void setByyx_qrz(String byyxQrz) {
		byyx_qrz = byyxQrz;
	}
	/**
	 * @return the zymc_qrz
	 */
	public String getZymc_qrz() {
		return zymc_qrz;
	}
	/**
	 * @param zymcQrzҪ���õ� zymc_qrz
	 */
	public void setZymc_qrz(String zymcQrz) {
		zymc_qrz = zymcQrz;
	}
	/**
	 * @return the qsrq_qrz
	 */
	public String getQsrq_qrz() {
		return qsrq_qrz;
	}
	/**
	 * @param qsrqQrzҪ���õ� qsrq_qrz
	 */
	public void setQsrq_qrz(String qsrqQrz) {
		qsrq_qrz = qsrqQrz;
	}
	/**
	 * @return the zzrq_qrz
	 */
	public String getZzrq_qrz() {
		return zzrq_qrz;
	}
	/**
	 * @param zzrqQrzҪ���õ� zzrq_qrz
	 */
	public void setZzrq_qrz(String zzrqQrz) {
		zzrq_qrz = zzrqQrz;
	}
	/**
	 * @return the xl_zz
	 */
	public String getXl_zz() {
		return xl_zz;
	}
	/**
	 * @param xlZzҪ���õ� xl_zz
	 */
	public void setXl_zz(String xlZz) {
		xl_zz = xlZz;
	}
	/**
	 * @return the xw_zz
	 */
	public String getXw_zz() {
		return xw_zz;
	}
	/**
	 * @param xwZzҪ���õ� xw_zz
	 */
	public void setXw_zz(String xwZz) {
		xw_zz = xwZz;
	}
	/**
	 * @return the byyx_zz
	 */
	public String getByyx_zz() {
		return byyx_zz;
	}
	/**
	 * @param byyxZzҪ���õ� byyx_zz
	 */
	public void setByyx_zz(String byyxZz) {
		byyx_zz = byyxZz;
	}
	/**
	 * @return the zymc_zz
	 */
	public String getZymc_zz() {
		return zymc_zz;
	}
	/**
	 * @param zymcZzҪ���õ� zymc_zz
	 */
	public void setZymc_zz(String zymcZz) {
		zymc_zz = zymcZz;
	}
	/**
	 * @return the qsrq_zz
	 */
	public String getQsrq_zz() {
		return qsrq_zz;
	}
	/**
	 * @param qsrqZzҪ���õ� qsrq_zz
	 */
	public void setQsrq_zz(String qsrqZz) {
		qsrq_zz = qsrqZz;
	}
	/**
	 * @return the zzrq_zz
	 */
	public String getZzrq_zz() {
		return zzrq_zz;
	}
	/**
	 * @param zzrqZzҪ���õ� zzrq_zz
	 */
	public void setZzrq_zz(String zzrqZz) {
		zzrq_zz = zzrqZz;
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
	 * @return the zgqk
	 */
	public String getZgqk() {
		return zgqk;
	}
	/**
	 * @param zgqkҪ���õ� zgqk
	 */
	public void setZgqk(String zgqk) {
		this.zgqk = zgqk;
	}
	/**
	 * @return the zhuanzhi
	 */
	public String getZhuanzhi() {
		return zhuanzhi;
	}
	/**
	 * @param zhuanzhiҪ���õ� zhuanzhi
	 */
	public void setZhuanzhi(String zhuanzhi) {
		this.zhuanzhi = zhuanzhi;
	}
	/**
	 * @return the zzssbm
	 */
	public String getZzssbm() {
		return zzssbm;
	}
	/**
	 * @param zzssbmҪ���õ� zzssbm
	 */
	public void setZzssbm(String zzssbm) {
		this.zzssbm = zzssbm;
	}
	/**
	 * @return the jzssbm
	 */
	public String getJzssbm() {
		return jzssbm;
	}
	/**
	 * @param jzssbmҪ���õ� jzssbm
	 */
	public void setJzssbm(String jzssbm) {
		this.jzssbm = jzssbm;
	}
	/**
	 * @return the gznx
	 */
	public String getGznx() {
		return gznx;
	}
	/**
	 * @param gznxҪ���õ� gznx
	 */
	public void setGznx(String gznx) {
		this.gznx = gznx;
	}
	/**
	 * @return the sfdb
	 */
	public String getSfdb() {
		return sfdb;
	}
	/**
	 * @param sfdbҪ���õ� sfdb
	 */
	public void setSfdb(String sfdb) {
		this.sfdb = sfdb;
	}
	/**
	 * @return the xdbj
	 */
	public String getXdbj() {
		return xdbj;
	}
	/**
	 * @param xdbjҪ���õ� xdbj
	 */
	public void setXdbj(String xdbj) {
		this.xdbj = xdbj;
	}
	/**
	 * @return the dbrs
	 */
	public String getDbrs() {
		return dbrs;
	}
	/**
	 * @param dbrsҪ���õ� dbrs
	 */
	public void setDbrs(String dbrs) {
		this.dbrs = dbrs;
	}
	/**
	 * @return the zyjszw
	 */
	public String getZyjszw() {
		return zyjszw;
	}
	/**
	 * @param zyjszwҪ���õ� zyjszw
	 */
	public void setZyjszw(String zyjszw) {
		this.zyjszw = zyjszw;
	}
	/**
	 * @return the jb_13431
	 */
	public String getJb_13431() {
		return jb_13431;
	}
	/**
	 * @param jb_13431Ҫ���õ� jb_13431
	 */
	public void setJb_13431(String jb_13431) {
		this.jb_13431 = jb_13431;
	}
	/**
	 * @return the tsgz
	 */
	public String getTsgz() {
		return tsgz;
	}
	/**
	 * @param tsgzҪ���õ� tsgz
	 */
	public void setTsgz(String tsgz) {
		this.tsgz = tsgz;
	}
	/**
	 * @return the zw_13431
	 */
	public String getZw_13431() {
		return zw_13431;
	}
	/**
	 * @param zw_13431Ҫ���õ� zw_13431
	 */
	public void setZw_13431(String zw_13431) {
		this.zw_13431 = zw_13431;
	}
	/**
	 * @return the rdrq
	 */
	public String getRdrq() {
		return rdrq;
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
	 * @param rdrqҪ���õ� rdrq
	 */
	public void setRdrq(String rdrq) {
		this.rdrq = rdrq;
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
	 * @return the jssf
	 */
	public String getJssf() {
		return jssf;
	}
	/**
	 * @param jssfҪ���õ� jssf
	 */
	public void setJssf(String jssf) {
		this.jssf = jssf;
	}
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlcҪ���õ� shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the tzlj
	 */
	public String getTzlj() {
		return tzlj;
	}
	/**
	 * @param tzljҪ���õ� tzlj
	 */
	public void setTzlj(String tzlj) {
		this.tzlj = tzlj;
	}
	/**
	 * @return the tzljsq
	 */
	public String getTzljsq() {
		return tzljsq;
	}
	/**
	 * @param tzljsqҪ���õ� tzljsq
	 */
	public void setTzljsq(String tzljsq) {
		this.tzljsq = tzljsq;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shrҪ���õ� shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywidҪ���õ� ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	/**
	 * @return the sqrid
	 */
	public String getSqrid() {
		return sqrid;
	}
	/**
	 * @param sqridҪ���õ� sqrid
	 */
	public void setSqrid(String sqrid) {
		this.sqrid = sqrid;
	}
	/**
	 * @return the xgsj
	 */
	public String getXgsj() {
		return xgsj;
	}
	/**
	 * @param xgsjҪ���õ� xgsj
	 */
	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqidҪ���õ� sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the sqjg
	 */
	public String getSqjg() {
		return sqjg;
	}
	/**
	 * @param sqjgҪ���õ� sqjg
	 */
	public void setSqjg(String sqjg) {
		this.sqjg = sqjg;
	}
	/**
	 * @return the xgzdJson
	 */
	public String getXgzdJson() {
		return xgzdJson;
	}
	/**
	 * @param xgzdJsonҪ���õ� xgzdJson
	 */
	public void setXgzdJson(String xgzdJson) {
		this.xgzdJson = xgzdJson;
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
	 * @return the gwlbdm
	 */
	public String getGwlbdm() {
		return gwlbdm;
	}
	/**
	 * @param gwlbdmҪ���õ� gwlbdm
	 */
	public void setGwlbdm(String gwlbdm) {
		this.gwlbdm = gwlbdm;
	}
	/**
	 * @return the khyh
	 */
	public String getKhyh() {
		return khyh;
	}
	/**
	 * @param khyhҪ���õ� khyh
	 */
	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}
	/**
	 * @return the hyzk
	 */
	public String getHyzk() {
		return hyzk;
	}
	/**
	 * @param hyzkҪ���õ� hyzk
	 */
	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}
	/**
	 * @return the fdyzbs
	 */
	public String getFdyzbs() {
		return fdyzbs;
	}
	/**
	 * @param fdyzbsҪ���õ� fdyzbs
	 */
	public void setFdyzbs(String fdyzbs) {
		this.fdyzbs = fdyzbs;
	}
	/**
	 * @return the fdyrzrq
	 */
	public String getFdyrzrq() {
		return fdyrzrq;
	}
	/**
	 * @param fdyrzrqҪ���õ� fdyrzrq
	 */
	public void setFdyrzrq(String fdyrzrq) {
		this.fdyrzrq = fdyrzrq;
	}
	/**
	 * @return the zyjnzs
	 */
	public String getZyjnzs() {
		return zyjnzs;
	}
	/**
	 * @param zyjnzsҪ���õ� zyjnzs
	 */
	public void setZyjnzs(String zyjnzs) {
		this.zyjnzs = zyjnzs;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param ageҪ���õ� age
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the yhzh
	 */
	public String getYhzh() {
		return yhzh;
	}
	/**
	 * @param yhzhҪ���õ� yhzh
	 */
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	/**
	 * @return the zzshen
	 */
	public String getZzshen() {
		return zzshen;
	}
	/**
	 * @param zzshenҪ���õ� zzshen
	 */
	public void setZzshen(String zzshen) {
		this.zzshen = zzshen;
	}
	/**
	 * @return the zzshi
	 */
	public String getZzshi() {
		return zzshi;
	}
	/**
	 * @param zzshiҪ���õ� zzshi
	 */
	public void setZzshi(String zzshi) {
		this.zzshi = zzshi;
	}
	/**
	 * @return the zzxian
	 */
	public String getZzxian() {
		return zzxian;
	}
	/**
	 * @param zzxianҪ���õ� zzxian
	 */
	public void setZzxian(String zzxian) {
		this.zzxian = zzxian;
	}
	/**
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zghҪ���õ� zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
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
	 * @return the bmdm
	 */
	public String getBmdm() {
		return bmdm;
	}
	/**
	 * @param bmdmҪ���õ� bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
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
	 * @return the zyzz
	 */
	public String getZyzz() {
		return zyzz;
	}
	/**
	 * @param zyzzҪ���õ� zyzz
	 */
	public void setZyzz(String zyzz) {
		this.zyzz = zyzz;
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
	 * @return the xl
	 */
	public String getXl() {
		return xl;
	}
	/**
	 * @param xlҪ���õ� xl
	 */
	public void setXl(String xl) {
		this.xl = xl;
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
	 * @return the grhjqk
	 */
	public String getGrhjqk() {
		return grhjqk;
	}
	/**
	 * @param grhjqkҪ���õ� grhjqk
	 */
	public void setGrhjqk(String grhjqk) {
		this.grhjqk = grhjqk;
	}
	/**
	 * @return the gzjl
	 */
	public String getGzjl() {
		return gzjl;
	}
	/**
	 * @param gzjlҪ���õ� gzjl
	 */
	public void setGzjl(String gzjl) {
		this.gzjl = gzjl;
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
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}
	/**
	 * @param zcҪ���õ� zc
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}
	/**
	 * @return the lxgzsj
	 */
	public String getLxgzsj() {
		return lxgzsj;
	}
	/**
	 * @param lxgzsjҪ���õ� lxgzsj
	 */
	public void setLxgzsj(String lxgzsj) {
		this.lxgzsj = lxgzsj;
	}
	/**
	 * @return the xsgzyjfx
	 */
	public String getXsgzyjfx() {
		return xsgzyjfx;
	}
	/**
	 * @param xsgzyjfxҪ���õ� xsgzyjfx
	 */
	public void setXsgzyjfx(String xsgzyjfx) {
		this.xsgzyjfx = xsgzyjfx;
	}
	/**
	 * @return the jtzz
	 */
	public String getJtzz() {
		return jtzz;
	}
	/**
	 * @param jtzzҪ���õ� jtzz
	 */
	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}
	/**
	 * @return the yddh
	 */
	public String getYddh() {
		return yddh;
	}
	/**
	 * @param yddhҪ���õ� yddh
	 */
	public void setYddh(String yddh) {
		this.yddh = yddh;
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
	 * @return the fblw
	 */
	public String getFblw() {
		return fblw;
	}
	/**
	 * @param fblwҪ���õ� fblw
	 */
	public void setFblw(String fblw) {
		this.fblw = fblw;
	}
	/**
	 * @return the kyjl
	 */
	public String getKyjl() {
		return kyjl;
	}
	/**
	 * @param kyjlҪ���õ� kyjl
	 */
	public void setKyjl(String kyjl) {
		this.kyjl = kyjl;
	}
	/**
	 * @return the byyx
	 */
	public String getByyx() {
		return byyx;
	}
	/**
	 * @param byyxҪ���õ� byyx
	 */
	public void setByyx(String byyx) {
		this.byyx = byyx;
	}
	/**
	 * @return the sxzy
	 */
	public String getSxzy() {
		return sxzy;
	}
	/**
	 * @param sxzyҪ���õ� sxzy
	 */
	public void setSxzy(String sxzy) {
		this.sxzy = sxzy;
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
	 * @return the rwsj
	 */
	public String getRwsj() {
		return rwsj;
	}
	/**
	 * @param rwsjҪ���õ� rwsj
	 */
	public void setRwsj(String rwsj) {
		this.rwsj = rwsj;
	}
	/**
	 * @return the gzfg
	 */
	public String getGzfg() {
		return gzfg;
	}
	/**
	 * @param gzfgҪ���õ� gzfg
	 */
	public void setGzfg(String gzfg) {
		this.gzfg = gzfg;
	}
	/**
	 * @return the csfdysj
	 */
	public String getCsfdysj() {
		return csfdysj;
	}
	/**
	 * @param csfdysjҪ���õ� csfdysj
	 */
	public void setCsfdysj(String csfdysj) {
		this.csfdysj = csfdysj;
	}
	/**
	 * @return the zjz
	 */
	public String getZjz() {
		return zjz;
	}
	/**
	 * @param zjzҪ���õ� zjz
	 */
	public void setZjz(String zjz) {
		this.zjz = zjz;
	}
	/**
	 * @return the zwrzsj
	 */
	public String getZwrzsj() {
		return zwrzsj;
	}
	/**
	 * @param zwrzsjҪ���õ� zwrzsj
	 */
	public void setZwrzsj(String zwrzsj) {
		this.zwrzsj = zwrzsj;
	}
	/**
	 * @return the jsrzsj
	 */
	public String getJsrzsj() {
		return jsrzsj;
	}
	/**
	 * @param jsrzsjҪ���õ� jsrzsj
	 */
	public void setJsrzsj(String jsrzsj) {
		this.jsrzsj = jsrzsj;
	}
	/**
	 * @return the sjdw
	 */
	public String getSjdw() {
		return sjdw;
	}
	/**
	 * @param sjdwҪ���õ� sjdw
	 */
	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}
	/**
	 * @return the txdz
	 */
	public String getTxdz() {
		return txdz;
	}
	/**
	 * @param txdzҪ���õ� txdz
	 */
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	/**
	 * @return the bgdh
	 */
	public String getBgdh() {
		return bgdh;
	}
	/**
	 * @param bgdhҪ���õ� bgdh
	 */
	public void setBgdh(String bgdh) {
		this.bgdh = bgdh;
	}
	/**
	 * @return the cz
	 */
	public String getCz() {
		return cz;
	}
	/**
	 * @param czҪ���õ� cz
	 */
	public void setCz(String cz) {
		this.cz = cz;
	}
	/**
	 * @return the jrgz
	 */
	public String getJrgz() {
		return jrgz;
	}
	/**
	 * @param jrgzҪ���õ� jrgz
	 */
	public void setJrgz(String jrgz) {
		this.jrgz = jrgz;
	}
	/**
	 * @return the jgxs
	 */
	public String getJgxs() {
		return jgxs;
	}
	/**
	 * @param jgxsҪ���õ� jgxs
	 */
	public void setJgxs(String jgxs) {
		this.jgxs = jgxs;
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
	 * @return the jb
	 */
	public String getJb() {
		return jb;
	}
	/**
	 * @param jbҪ���õ� jb
	 */
	public void setJb(String jb) {
		this.jb = jb;
	}
	/**
	 * @return the djsj
	 */
	public String getDjsj() {
		return djsj;
	}
	/**
	 * @param djsjҪ���õ� djsj
	 */
	public void setDjsj(String djsj) {
		this.djsj = djsj;
	}
	/**
	 * @return the szgzsj
	 */
	public String getSzgzsj() {
		return szgzsj;
	}
	/**
	 * @param szgzsjҪ���õ� szgzsj
	 */
	public void setSzgzsj(String szgzsj) {
		this.szgzsj = szgzsj;
	}
	/**
	 * @return the bkbyyx
	 */
	public String getBkbyyx() {
		return bkbyyx;
	}
	/**
	 * @param bkbyyxҪ���õ� bkbyyx
	 */
	public void setBkbyyx(String bkbyyx) {
		this.bkbyyx = bkbyyx;
	}
	/**
	 * @return the bksxzy
	 */
	public String getBksxzy() {
		return bksxzy;
	}
	/**
	 * @param bksxzyҪ���õ� bksxzy
	 */
	public void setBksxzy(String bksxzy) {
		this.bksxzy = bksxzy;
	}
	/**
	 * @return the ssbyyx
	 */
	public String getSsbyyx() {
		return ssbyyx;
	}
	/**
	 * @param ssbyyxҪ���õ� ssbyyx
	 */
	public void setSsbyyx(String ssbyyx) {
		this.ssbyyx = ssbyyx;
	}
	/**
	 * @return the ssbyzy
	 */
	public String getSsbyzy() {
		return ssbyzy;
	}
	/**
	 * @param ssbyzyҪ���õ� ssbyzy
	 */
	public void setSsbyzy(String ssbyzy) {
		this.ssbyzy = ssbyzy;
	}
	/**
	 * @return the bsbyyx
	 */
	public String getBsbyyx() {
		return bsbyyx;
	}
	/**
	 * @param bsbyyxҪ���õ� bsbyyx
	 */
	public void setBsbyyx(String bsbyyx) {
		this.bsbyyx = bsbyyx;
	}
	/**
	 * @return the bsbyzy
	 */
	public String getBsbyzy() {
		return bsbyzy;
	}
	/**
	 * @param bsbyzyҪ���õ� bsbyzy
	 */
	public void setBsbyzy(String bsbyzy) {
		this.bsbyzy = bsbyzy;
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
	 * @return the cjgzrq
	 */
	public String getCjgzrq() {
		return cjgzrq;
	}
	/**
	 * @param cjgzrqҪ���õ� cjgzrq
	 */
	public void setCjgzrq(String cjgzrq) {
		this.cjgzrq = cjgzrq;
	}
	/**
	 * @return the sfbl
	 */
	public String getSfbl() {
		return sfbl;
	}
	/**
	 * @param sfblҪ���õ� sfbl
	 */
	public void setSfbl(String sfbl) {
		this.sfbl = sfbl;
	}
	/**
	 * @return the zhxwssxk
	 */
	public String getZhxwssxk() {
		return zhxwssxk;
	}
	/**
	 * @param zhxwssxkҪ���õ� zhxwssxk
	 */
	public void setZhxwssxk(String zhxwssxk) {
		this.zhxwssxk = zhxwssxk;
	}
	/**
	 * @return the pxqk
	 */
	public String getPxqk() {
		return pxqk;
	}
	/**
	 * @param pxqkҪ���õ� pxqk
	 */
	public void setPxqk(String pxqk) {
		this.pxqk = pxqk;
	}
	/**
	 * @return the csgz
	 */
	public String getCsgz() {
		return csgz;
	}
	/**
	 * @param csgzҪ���õ� csgz
	 */
	public void setCsgz(String csgz) {
		this.csgz = csgz;
	}
	/**
	 * @return the bgdd
	 */
	public String getBgdd() {
		return bgdd;
	}
	/**
	 * @param bgddҪ���õ� bgdd
	 */
	public void setBgdd(String bgdd) {
		this.bgdd = bgdd;
	}
	/**
	 * @return the zdxl
	 */
	public String getZdxl() {
		return zdxl;
	}
	/**
	 * @param zdxlҪ���õ� zdxl
	 */
	public void setZdxl(String zdxl) {
		this.zdxl = zdxl;
	}
	/**
	 * @return the zdzy
	 */
	public String getZdzy() {
		return zdzy;
	}
	/**
	 * @param zdzyҪ���õ� zdzy
	 */
	public void setZdzy(String zdzy) {
		this.zdzy = zdzy;
	}
	/**
	 * @return the gdxlzl
	 */
	public String getGdxlzl() {
		return gdxlzl;
	}
	/**
	 * @param gdxlzlҪ���õ� gdxlzl
	 */
	public void setGdxlzl(String gdxlzl) {
		this.gdxlzl = gdxlzl;
	}
	/**
	 * @return the zyyjjg
	 */
	public String getZyyjjg() {
		return zyyjjg;
	}
	/**
	 * @param zyyjjgҪ���õ� zyyjjg
	 */
	public void setZyyjjg(String zyyjjg) {
		this.zyyjjg = zyyjjg;
	}
	/**
	 * @return the fgnj
	 */
	public String getFgnj() {
		return fgnj;
	}
	/**
	 * @param fgnjҪ���õ� fgnj
	 */
	public void setFgnj(String fgnj) {
		this.fgnj = fgnj;
	}
	/**
	 * @return the qtfgnj
	 */
	public String getQtfgnj() {
		return qtfgnj;
	}
	/**
	 * @param qtfgnjҪ���õ� qtfgnj
	 */
	public void setQtfgnj(String qtfgnj) {
		this.qtfgnj = qtfgnj;
	}
	/**
	 * @return the fdyz
	 */
	public String getFdyz() {
		return fdyz;
	}
	/**
	 * @param fdyzҪ���õ� fdyz
	 */
	public void setFdyz(String fdyz) {
		this.fdyz = fdyz;
	}
	/**
	 * @return the fglxdm
	 */
	public String getFglxdm() {
		return fglxdm;
	}
	/**
	 * @param fglxdmҪ���õ� fglxdm
	 */
	public void setFglxdm(String fglxdm) {
		this.fglxdm = fglxdm;
	}
	/**
	 * @return the dwlbdm
	 */
	public String getDwlbdm() {
		return dwlbdm;
	}
	/**
	 * @param dwlbdmҪ���õ� dwlbdm
	 */
	public void setDwlbdm(String dwlbdm) {
		this.dwlbdm = dwlbdm;
	}
	/**
	 * @return the kzzd1
	 */
	public String getKzzd1() {
		return kzzd1;
	}
	/**
	 * @param kzzd1Ҫ���õ� kzzd1
	 */
	public void setKzzd1(String kzzd1) {
		this.kzzd1 = kzzd1;
	}
	/**
	 * @return the kzzd2
	 */
	public String getKzzd2() {
		return kzzd2;
	}
	/**
	 * @param kzzd2Ҫ���õ� kzzd2
	 */
	public void setKzzd2(String kzzd2) {
		this.kzzd2 = kzzd2;
	}
	/**
	 * @return the kzzd3
	 */
	public String getKzzd3() {
		return kzzd3;
	}
	/**
	 * @param kzzd3Ҫ���õ� kzzd3
	 */
	public void setKzzd3(String kzzd3) {
		this.kzzd3 = kzzd3;
	}
	/**
	 * @return the kzzd4
	 */
	public String getKzzd4() {
		return kzzd4;
	}
	/**
	 * @param kzzd4Ҫ���õ� kzzd4
	 */
	public void setKzzd4(String kzzd4) {
		this.kzzd4 = kzzd4;
	}
	/**
	 * @return the kzzd5
	 */
	public String getKzzd5() {
		return kzzd5;
	}
	/**
	 * @param kzzd5Ҫ���õ� kzzd5
	 */
	public void setKzzd5(String kzzd5) {
		this.kzzd5 = kzzd5;
	}
	/**
	 * @return the sfjryx
	 */
	public String getSfjryx() {
		return sfjryx;
	}
	/**
	 * @param sfjryxҪ���õ� sfjryx
	 */
	public void setSfjryx(String sfjryx) {
		this.sfjryx = sfjryx;
	}
	/**
	 * @return the kzzd6
	 */
	public String getKzzd6() {
		return kzzd6;
	}
	/**
	 * @param kzzd6Ҫ���õ� kzzd6
	 */
	public void setKzzd6(String kzzd6) {
		this.kzzd6 = kzzd6;
	}
	/**
	 * @return the kzzd7
	 */
	public String getKzzd7() {
		return kzzd7;
	}
	/**
	 * @param kzzd7Ҫ���õ� kzzd7
	 */
	public void setKzzd7(String kzzd7) {
		this.kzzd7 = kzzd7;
	}
	/**
	 * @return the kzzd8
	 */
	public String getKzzd8() {
		return kzzd8;
	}
	/**
	 * @param kzzd8Ҫ���õ� kzzd8
	 */
	public void setKzzd8(String kzzd8) {
		this.kzzd8 = kzzd8;
	}
	/**
	 * @return the kzzd9
	 */
	public String getKzzd9() {
		return kzzd9;
	}
	/**
	 * @param kzzd9Ҫ���õ� kzzd9
	 */
	public void setKzzd9(String kzzd9) {
		this.kzzd9 = kzzd9;
	}
	/**
	 * @return the kzzd10
	 */
	public String getKzzd10() {
		return kzzd10;
	}
	/**
	 * @param kzzd10Ҫ���õ� kzzd10
	 */
	public void setKzzd10(String kzzd10) {
		this.kzzd10 = kzzd10;
	}
	/**
	 * @return the kzzd11
	 */
	public String getKzzd11() {
		return kzzd11;
	}
	/**
	 * @param kzzd11Ҫ���õ� kzzd11
	 */
	public void setKzzd11(String kzzd11) {
		this.kzzd11 = kzzd11;
	}
	/**
	 * @return the kzzd12
	 */
	public String getKzzd12() {
		return kzzd12;
	}
	/**
	 * @param kzzd12Ҫ���õ� kzzd12
	 */
	public void setKzzd12(String kzzd12) {
		this.kzzd12 = kzzd12;
	}
	/**
	 * @return the kzzd13
	 */
	public String getKzzd13() {
		return kzzd13;
	}
	/**
	 * @param kzzd13Ҫ���õ� kzzd13
	 */
	public void setKzzd13(String kzzd13) {
		this.kzzd13 = kzzd13;
	}
	/**
	 * @return the kzzd14
	 */
	public String getKzzd14() {
		return kzzd14;
	}
	/**
	 * @param kzzd14Ҫ���õ� kzzd14
	 */
	public void setKzzd14(String kzzd14) {
		this.kzzd14 = kzzd14;
	}
	/**
	 * @return the kzzd15
	 */
	public String getKzzd15() {
		return kzzd15;
	}
	/**
	 * @param kzzd15Ҫ���õ� kzzd15
	 */
	public void setKzzd15(String kzzd15) {
		this.kzzd15 = kzzd15;
	}
	/**
	 * @return the kzzd16
	 */
	public String getKzzd16() {
		return kzzd16;
	}
	/**
	 * @param kzzd16Ҫ���õ� kzzd16
	 */
	public void setKzzd16(String kzzd16) {
		this.kzzd16 = kzzd16;
	}
	/**
	 * @return the kzzd17
	 */
	public String getKzzd17() {
		return kzzd17;
	}
	/**
	 * @param kzzd17Ҫ���õ� kzzd17
	 */
	public void setKzzd17(String kzzd17) {
		this.kzzd17 = kzzd17;
	}
	/**
	 * @return the kzzd18
	 */
	public String getKzzd18() {
		return kzzd18;
	}
	/**
	 * @param kzzd18Ҫ���õ� kzzd18
	 */
	public void setKzzd18(String kzzd18) {
		this.kzzd18 = kzzd18;
	}
	/**
	 * @return the kzzd19
	 */
	public String getKzzd19() {
		return kzzd19;
	}
	/**
	 * @param kzzd19Ҫ���õ� kzzd19
	 */
	public void setKzzd19(String kzzd19) {
		this.kzzd19 = kzzd19;
	}
	/**
	 * @return the kzzd20
	 */
	public String getKzzd20() {
		return kzzd20;
	}
	/**
	 * @param kzzd20Ҫ���õ� kzzd20
	 */
	public void setKzzd20(String kzzd20) {
		this.kzzd20 = kzzd20;
	}
	/**
	 * @return the sfdw
	 */
	public String getSfdw() {
		return sfdw;
	}
	/**
	 * @param sfdwҪ���õ� sfdw
	 */
	public void setSfdw(String sfdw) {
		this.sfdw = sfdw;
	}
	/**
	 * @return the bzxm
	 */
	public String getBzxm() {
		return bzxm;
	}
	/**
	 * @param bzxmҪ���õ� bzxm
	 */
	public void setBzxm(String bzxm) {
		this.bzxm = bzxm;
	}
	/**
	 * @return the bzlxdh
	 */
	public String getBzlxdh() {
		return bzlxdh;
	}
	/**
	 * @param bzlxdhҪ���õ� bzlxdh
	 */
	public void setBzlxdh(String bzlxdh) {
		this.bzlxdh = bzlxdh;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-26 ����10:27:23 
	 * @return		: the sydm
	 */
	public String getSydm() {
		return sydm;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� CP��1352��
	 * @date		�� 2018-1-26 ����10:27:23 
	 * @param 		��sydm the sydm to set
	 */
	public void setSydm(String sydm) {
		this.sydm = sydm;
	}
	/**
	 * @return the zyjnzs2
	 */
	public String getZyjnzs2() {
		return zyjnzs2;
	}
	/**
	 * @param zyjnzs2Ҫ���õ� zyjnzs2
	 */
	public void setZyjnzs2(String zyjnzs2) {
		this.zyjnzs2 = zyjnzs2;
	}
	/**
	 * @return the zyjnzs3
	 */
	public String getZyjnzs3() {
		return zyjnzs3;
	}
	/**
	 * @param zyjnzs3Ҫ���õ� zyjnzs3
	 */
	public void setZyjnzs3(String zyjnzs3) {
		this.zyjnzs3 = zyjnzs3;
	}
	

	
}
