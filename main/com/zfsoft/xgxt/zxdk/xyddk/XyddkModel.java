/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-25 ����03:21:11 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ���� - ������ѧ����
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-25 ����03:21:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyddkModel extends SuperAuditModel {

	private static final long serialVersionUID = -3569762156962449075L;

	private String id;
	private String xh;
	private String xn;
	private String xz;
	private String nj;
	private String dkje;
	private String xzf;//ѧ��
	private String shf;
	private String mnje;
	private String dkqx;
	private String sqly;
	private String sqsj;
	private String shzt;
	private String splcid;
	
	private String htbh;
	private String sjly;
	private String sfwy;
	
	private String zsf;//ס�޷�
	private String zsfdks;//ס�޷Ѵ�����
	private String xfdks;//ѧ�Ѵ����� 
	private String shfdks;//����Ѵ�����
	private String fkze;
	private String xdxn;
	private String xdje;
	private String xdly;
	
	private String yhmc; //��������
	private String lxdh; //��ϵ�绰
	
	//������ҽҩ��ѧ �����ֶ� 
	private String dkkssj;//���ʼʱ��
	private String dkjzsj;//�����ֹʱ��
	private String yjschksj;//Ԥ���״λ���ʱ��
	private String dkkh;//�����
	private String jhr1;//�����໤��1
	private String sfzh1;//�����໤��1���֤��
	private String zy1;//�����໤��1ְҵ
	private String gzdwmc1;//�����໤��1������λ����
	private String gzdwdz1;//�����໤��1������λ��ַ
	private String lxdh1;//�����໤��1��ϵ�绰
	private String jhr2;//�����໤��2
	private String sfzh2;//�����໤��2���֤��
	private String zy2;//�����໤��2ְҵ
	private String gzdwmc2;//�����໤��2������λ����
	private String gzdwdz2;//�����໤��2������λ��ַ
	private String xldh2;//�����໤��2��ϵ
	private String bldkrq;//����������ڣ������Ƽ���ѧ���Ի��ֶΣ�
	private String htdd;//��ͬǩ���ص㣨�����Ƽ���ѧ���Ի��ֶΣ�
	private String xyjbr;//ѧԺ������
	private String zd7;
	private String zd8;
	private String zd9;
	private String zd10;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	//�������
	private FormFile formfile;
	private String filepath;
	
	//�����޸������ֶ�
	private String splc;
	private String type;
    private String shid;
    private String shjg;
    private String shyj;
    private String gwid;
    private String thgw;
    private String shlc;
    private String[] splcs;
    private String[] ids;
    private String[] xhs;
    
    //�����������Ի�
    //  ��ͬ�������Ϣ
    private String gtjkryb;//��������
    private String gtjkrjtdz;//��ͥ��ϸ��ַ
    private String gtjkrjkzk;//����״��
    private String gtjkrsjhm;//�ֻ�����
    private String  gtjkrsfzh;//���֤����
    private String gtjkrgx;//��ѧ����ϵ
    private String gtjkrjtdh;//��ͥ�绰
    private String gtjkrxm;//����
   
    
    /**
	 * @return the gtjkryb
	 */
	public String getGtjkryb() {
		return gtjkryb;
	}
	/**
	 * @param gtjkrybҪ���õ� gtjkryb
	 */
	public void setGtjkryb(String gtjkryb) {
		this.gtjkryb = gtjkryb;
	}
	/**
	 * @return the gtjkrjtdz
	 */
	public String getGtjkrjtdz() {
		return gtjkrjtdz;
	}
	/**
	 * @param gtjkrjtdzҪ���õ� gtjkrjtdz
	 */
	public void setGtjkrjtdz(String gtjkrjtdz) {
		this.gtjkrjtdz = gtjkrjtdz;
	}
	/**
	 * @return the gtjkrjkzk
	 */
	public String getGtjkrjkzk() {
		return gtjkrjkzk;
	}
	/**
	 * @param gtjkrjkzkҪ���õ� gtjkrjkzk
	 */
	public void setGtjkrjkzk(String gtjkrjkzk) {
		this.gtjkrjkzk = gtjkrjkzk;
	}
	/**
	 * @return the gtjkrsjhm
	 */
	public String getGtjkrsjhm() {
		return gtjkrsjhm;
	}
	/**
	 * @param gtjkrsjhmҪ���õ� gtjkrsjhm
	 */
	public void setGtjkrsjhm(String gtjkrsjhm) {
		this.gtjkrsjhm = gtjkrsjhm;
	}
	/**
	 * @return the gtjkrsfzh
	 */
	public String getGtjkrsfzh() {
		return gtjkrsfzh;
	}
	/**
	 * @param gtjkrsfzhҪ���õ� gtjkrsfzh
	 */
	public void setGtjkrsfzh(String gtjkrsfzh) {
		this.gtjkrsfzh = gtjkrsfzh;
	}
	/**
	 * @return the gtjkrgx
	 */
	public String getGtjkrgx() {
		return gtjkrgx;
	}
	/**
	 * @param gtjkrgxҪ���õ� gtjkrgx
	 */
	public void setGtjkrgx(String gtjkrgx) {
		this.gtjkrgx = gtjkrgx;
	}
	/**
	 * @return the gtjkrjtdh
	 */
	public String getGtjkrjtdh() {
		return gtjkrjtdh;
	}
	/**
	 * @param gtjkrjtdhҪ���õ� gtjkrjtdh
	 */
	public void setGtjkrjtdh(String gtjkrjtdh) {
		this.gtjkrjtdh = gtjkrjtdh;
	}
	/**
	 * @return the gtjkrxm
	 */
	public String getGtjkrxm() {
		return gtjkrxm;
	}
	/**
	 * @param gtjkrxmҪ���õ� gtjkrxm
	 */
	public void setGtjkrxm(String gtjkrxm) {
		this.gtjkrxm = gtjkrxm;
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
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shidҪ���õ� shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjgҪ���õ� shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyjҪ���õ� shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwidҪ���õ� gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgwҪ���õ� thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the splcs
	 */
	public String[] getSplcs() {
		return splcs;
	}
	/**
	 * @param splcsҪ���õ� splcs
	 */
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	/**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * @param idsҪ���õ� ids
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwidsҪ���õ� gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	private String[] gwids;
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
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the dkkssj
	 */
	public String getDkkssj() {
		return dkkssj;
	}
	/**
	 * @param dkkssjҪ���õ� dkkssj
	 */
	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}
	/**
	 * @return the dkjzsj
	 */
	public String getDkjzsj() {
		return dkjzsj;
	}
	/**
	 * @param dkjzsjҪ���õ� dkjzsj
	 */
	public void setDkjzsj(String dkjzsj) {
		this.dkjzsj = dkjzsj;
	}
	/**
	 * @return the yjschksj
	 */
	public String getYjschksj() {
		return yjschksj;
	}
	/**
	 * @param yjschksjҪ���õ� yjschksj
	 */
	public void setYjschksj(String yjschksj) {
		this.yjschksj = yjschksj;
	}
	/**
	 * @return the dkkh
	 */
	public String getDkkh() {
		return dkkh;
	}
	/**
	 * @param dkkhҪ���õ� dkkh
	 */
	public void setDkkh(String dkkh) {
		this.dkkh = dkkh;
	}
	/**
	 * @return the jhr1
	 */
	public String getJhr1() {
		return jhr1;
	}
	/**
	 * @param jhr1Ҫ���õ� jhr1
	 */
	public void setJhr1(String jhr1) {
		this.jhr1 = jhr1;
	}
	/**
	 * @return the sfzh1
	 */
	public String getSfzh1() {
		return sfzh1;
	}
	/**
	 * @param sfzh1Ҫ���õ� sfzh1
	 */
	public void setSfzh1(String sfzh1) {
		this.sfzh1 = sfzh1;
	}
	/**
	 * @return the zy1
	 */
	public String getZy1() {
		return zy1;
	}
	/**
	 * @param zy1Ҫ���õ� zy1
	 */
	public void setZy1(String zy1) {
		this.zy1 = zy1;
	}
	/**
	 * @return the gzdwmc1
	 */
	public String getGzdwmc1() {
		return gzdwmc1;
	}
	/**
	 * @param gzdwmc1Ҫ���õ� gzdwmc1
	 */
	public void setGzdwmc1(String gzdwmc1) {
		this.gzdwmc1 = gzdwmc1;
	}
	/**
	 * @return the gzdwdz1
	 */
	public String getGzdwdz1() {
		return gzdwdz1;
	}
	/**
	 * @param gzdwdz1Ҫ���õ� gzdwdz1
	 */
	public void setGzdwdz1(String gzdwdz1) {
		this.gzdwdz1 = gzdwdz1;
	}
	/**
	 * @return the lxdh1
	 */
	public String getLxdh1() {
		return lxdh1;
	}
	/**
	 * @param lxdh1Ҫ���õ� lxdh1
	 */
	public void setLxdh1(String lxdh1) {
		this.lxdh1 = lxdh1;
	}
	/**
	 * @return the jhr2
	 */
	public String getJhr2() {
		return jhr2;
	}
	/**
	 * @param jhr2Ҫ���õ� jhr2
	 */
	public void setJhr2(String jhr2) {
		this.jhr2 = jhr2;
	}
	/**
	 * @return the sfzh2
	 */
	public String getSfzh2() {
		return sfzh2;
	}
	/**
	 * @param sfzh2Ҫ���õ� sfzh2
	 */
	public void setSfzh2(String sfzh2) {
		this.sfzh2 = sfzh2;
	}
	/**
	 * @return the zy2
	 */
	public String getZy2() {
		return zy2;
	}
	/**
	 * @param zy2Ҫ���õ� zy2
	 */
	public void setZy2(String zy2) {
		this.zy2 = zy2;
	}
	/**
	 * @return the gzdwmc2
	 */
	public String getGzdwmc2() {
		return gzdwmc2;
	}
	/**
	 * @param gzdwmc2Ҫ���õ� gzdwmc2
	 */
	public void setGzdwmc2(String gzdwmc2) {
		this.gzdwmc2 = gzdwmc2;
	}
	/**
	 * @return the gzdwdz2
	 */
	public String getGzdwdz2() {
		return gzdwdz2;
	}
	/**
	 * @param gzdwdz2Ҫ���õ� gzdwdz2
	 */
	public void setGzdwdz2(String gzdwdz2) {
		this.gzdwdz2 = gzdwdz2;
	}
	/**
	 * @return the xldh2
	 */
	public String getXldh2() {
		return xldh2;
	}
	/**
	 * @param xldh2Ҫ���õ� xldh2
	 */
	public void setXldh2(String xldh2) {
		this.xldh2 = xldh2;
	}
	
	
	
	/**
	 * @return the xdxn
	 */
	public String getXdxn() {
		return xdxn;
	}
	/**
	 * @param xdxnҪ���õ� xdxn
	 */
	public void setXdxn(String xdxn) {
		this.xdxn = xdxn;
	}
	/**
	 * @return the xdje
	 */
	public String getXdje() {
		return xdje;
	}
	/**
	 * @param xdjeҪ���õ� xdje
	 */
	public void setXdje(String xdje) {
		this.xdje = xdje;
	}
	/**
	 * @return the xdly
	 */
	public String getXdly() {
		return xdly;
	}
	/**
	 * @param xdlyҪ���õ� xdly
	 */
	public void setXdly(String xdly) {
		this.xdly = xdly;
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
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcidҪ���õ� splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}
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
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the dkje
	 */
	public String getDkje() {
		return dkje;
	}
	/**
	 * @param dkjeҪ���õ� dkje
	 */
	public void setDkje(String dkje) {
		this.dkje = dkje;
	}
	/**
	 * @return the xzf
	 */
	public String getXzf() {
		return xzf;
	}
	/**
	 * @param xzfҪ���õ� xzf
	 */
	public void setXzf(String xzf) {
		this.xzf = xzf;
	}
	/**
	 * @return the shf
	 */
	public String getShf() {
		return shf;
	}
	/**
	 * @param shfҪ���õ� shf
	 */
	public void setShf(String shf) {
		this.shf = shf;
	}
	/**
	 * @return the mnje
	 */
	public String getMnje() {
		return mnje;
	}
	/**
	 * @param mnjeҪ���õ� mnje
	 */
	public void setMnje(String mnje) {
		this.mnje = mnje;
	}
	/**
	 * @return the dkqx
	 */
	public String getDkqx() {
		return dkqx;
	}
	/**
	 * @param dkqxҪ���õ� dkqx
	 */
	public void setDkqx(String dkqx) {
		this.dkqx = dkqx;
	}
	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqlyҪ���õ� sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsjҪ���õ� sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
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
	 * @return the htbh
	 */
	public String getHtbh() {
		return htbh;
	}
	/**
	 * @param htbhҪ���õ� htbh
	 */
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @return the sfwy
	 */
	public String getSfwy() {
		return sfwy;
	}
	/**
	 * @param sfwyҪ���õ� sfwy
	 */
	public void setSfwy(String sfwy) {
		this.sfwy = sfwy;
	}
	/**
	 * @return the fkze
	 */
	public String getFkze() {
		return fkze;
	}
	/**
	 * @param fkzeҪ���õ� fkze
	 */
	public void setFkze(String fkze) {
		this.fkze = fkze;
	}

	/**
	 * @return the zsf
	 */
	public String getZsf() {
		return zsf;
	}
	/**
	 * @param zsfҪ���õ� zsf
	 */
	public void setZsf(String zsf) {
		this.zsf = zsf;
	}
	/**
	 * @return the zsfdks
	 */
	public String getZsfdks() {
		return zsfdks;
	}
	/**
	 * @param zsfdksҪ���õ� zsfdks
	 */
	public void setZsfdks(String zsfdks) {
		this.zsfdks = zsfdks;
	}
	/**
	 * @return the xfdks
	 */
	public String getXfdks() {
		return xfdks;
	}
	/**
	 * @param xfdksҪ���õ� xfdks
	 */
	public void setXfdks(String xfdks) {
		this.xfdks = xfdks;
	}
	/**
	 * @return the shfdks
	 */
	public String getShfdks() {
		return shfdks;
	}
	/**
	 * @param shfdksҪ���õ� shfdks
	 */
	public void setShfdks(String shfdks) {
		this.shfdks = shfdks;
	}
	/**
	 * @return the yhmc
	 */
	public String getYhmc() {
		return yhmc;
	}
	/**
	 * @param yhmcҪ���õ� yhmc
	 */
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
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
	public FormFile getFormfile() {
		return formfile;
	}
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the bldkrq
	 */
	public String getBldkrq() {
		return bldkrq;
	}
	/**
	 * @param bldkrqҪ���õ� bldkrq
	 */
	public void setBldkrq(String bldkrq) {
		this.bldkrq = bldkrq;
	}
	/**
	 * @return the htdd
	 */
	public String getHtdd() {
		return htdd;
	}
	/**
	 * @param htddҪ���õ� htdd
	 */
	public void setHtdd(String htdd) {
		this.htdd = htdd;
	}
	/**
	 * @return the xyjbr
	 */
	public String getXyjbr() {
		return xyjbr;
	}
	/**
	 * @param xyjbrҪ���õ� xyjbr
	 */
	public void setXyjbr(String xyjbr) {
		this.xyjbr = xyjbr;
	}
	/**
	 * @return the zd7
	 */
	public String getZd7() {
		return zd7;
	}
	/**
	 * @param zd7Ҫ���õ� zd7
	 */
	public void setZd7(String zd7) {
		this.zd7 = zd7;
	}
	/**
	 * @return the zd8
	 */
	public String getZd8() {
		return zd8;
	}
	/**
	 * @param zd8Ҫ���õ� zd8
	 */
	public void setZd8(String zd8) {
		this.zd8 = zd8;
	}
	/**
	 * @return the zd9
	 */
	public String getZd9() {
		return zd9;
	}
	/**
	 * @param zd9Ҫ���õ� zd9
	 */
	public void setZd9(String zd9) {
		this.zd9 = zd9;
	}
	/**
	 * @return the zd10
	 */
	public String getZd10() {
		return zd10;
	}
	/**
	 * @param zd10Ҫ���õ� zd10
	 */
	public void setZd10(String zd10) {
		this.zd10 = zd10;
	}
	
}
