/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-22 ����08:53:35 
 */  
package com.zfsoft.xgxt.xlzx.yysq;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlxxModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ԤԼ����
 * @���ߣ�wanghj [���ţ�1004]
 * @ʱ�䣺 2013-8-22 ����08:53:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YysqForm  extends ActionForm {


	private static final long serialVersionUID = 1L;

	User user = new User();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();
	
	private String id; //����
	private String xh;//ѧ��
	private String xstell;//ѧ���绰
	private String zgh;//ְ����
	private String yyzxrq;//ԤԼ��ѯʱ��
	private String yyzxzt;//ԤԼ��ѯ����
	private String yyzxxq;//ԤԼ��ѯ����
	private String status;//ԤԼ״̬-1ԤԼ��2ԤԼ�ɹ�3ԤԼ��(ѧ��ȡ��)4ԤԼ�ɹ�(ѧ��ȡ��)5ԤԼʧ��6�ѹ���
	private String yysbyy;//ԤԼʧ��ԭ��
	private String createsj;//����ʱ��
	private String bz;//��ע
	private String datazt;//����״̬-0ʧЧ1����
	private String qssj;//ԤԼ��ѯ��ʼʱ��
	private String jssj;//ԤԼ��ѯ����ʱ��
	private String xn;//ѧ��
	private String xq;//ѧ��
	private String xxxq;//У��
	private String sjddm;//ʱ��δ���
	
	private String sfdszn;
	private String jtszd;
	private String jtjjzk;
	private String fqwhcd;
	private String mqwhcd;
	private String fmhyzk;
	private String jtjsbs;
	private String jtxhcd;
	private String sfzl;
	private String djrq;
	private String[] yzxwts;
	private String yzxwt;
	private String wtbc;
	private String zxqw;
	private List<ZxzxjlxxModel> xxList;
	private String type;
	private String txr;
	
	private String zwpgid;//���ϳ���ѧԺ��������id

	private String qxztzt;//һ������״������:hh �ܺ�,jh �Ϻ�,yb һ��,jc �ϲ�,hc �ܲ�
	private String qxztjl;//һ������״������:yz ����,jz ����,y ��,qw ��΢,w ��
	private String qxztyy;//һ������״������:yz ����,jz ����,y ��,qw ��΢,w ��
	private String sczxhgb;//�ϴ���ѯ��ĸı�:hmx ������,jmx ������,yb һ��,jbmx �ϲ�����,bmx ������
	private String zjzt;//���Լ������״̬:hmy ������,jmy ������,yb һ��,jbmc �ϲ�����,bmy ������
	private String bczxwt;//������ѯ������
	private String zxhzt;//�ϴ���ѯ�����������״̬����Ṧ��

	private String yyfs;//ԤԼ��ʽ:ws ����ԤԼ,dh �绰ԤԼ,xc �ֳ�ԤԼ,jz �ҳ�ԤԼ

	public String getYyfs() {
		return yyfs;
	}

	public void setYyfs(String yyfs) {
		this.yyfs = yyfs;
	}

	public String getQxztzt() {
		return qxztzt;
	}

	public void setQxztzt(String qxztzt) {
		this.qxztzt = qxztzt;
	}

	public String getQxztjl() {
		return qxztjl;
	}

	public void setQxztjl(String qxztjl) {
		this.qxztjl = qxztjl;
	}

	public String getQxztyy() {
		return qxztyy;
	}

	public void setQxztyy(String qxztyy) {
		this.qxztyy = qxztyy;
	}

	public String getSczxhgb() {
		return sczxhgb;
	}

	public void setSczxhgb(String sczxhgb) {
		this.sczxhgb = sczxhgb;
	}

	public String getZjzt() {
		return zjzt;
	}

	public void setZjzt(String zjzt) {
		this.zjzt = zjzt;
	}

	public String getBczxwt() {
		return bczxwt;
	}

	public void setBczxwt(String bczxwt) {
		this.bczxwt = bczxwt;
	}

	public String getZxhzt() {
		return zxhzt;
	}

	public void setZxhzt(String zxhzt) {
		this.zxhzt = zxhzt;
	}

	/**
	 * @return the zwpgid
	 */
	public String getZwpgid() {
		return zwpgid;
	}
	/**
	 * @param zwpgidҪ���õ� zwpgid
	 */
	public void setZwpgid(String zwpgid) {
		this.zwpgid = zwpgid;
	}
	/**
	 * ��������
	 */
	private ExportModel exportModel = new ExportModel();
	
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	
	public String getSjddm() {
		return sjddm;
	}
	public void setSjddm(String sjddm) {
		this.sjddm = sjddm;
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
	 * @return the xstell
	 */
	public String getXstell() {
		return xstell;
	}
	/**
	 * @param xstellҪ���õ� xstell
	 */
	public void setXstell(String xstell) {
		this.xstell = xstell;
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
	 * @return the yyzxrq
	 */
	public String getYyzxrq() {
		return yyzxrq;
	}
	/**
	 * @param yyzxrqҪ���õ� yyzxrq
	 */
	public void setYyzxrq(String yyzxrq) {
		this.yyzxrq = yyzxrq;
	}
	/**
	 * @return the yyzxzt
	 */
	public String getYyzxzt() {
		return yyzxzt;
	}
	/**
	 * @param yyzxztҪ���õ� yyzxzt
	 */
	public void setYyzxzt(String yyzxzt) {
		this.yyzxzt = yyzxzt;
	}
	/**
	 * @return the yyzxxq
	 */
	public String getYyzxxq() {
		return yyzxxq;
	}
	/**
	 * @param yyzxxqҪ���õ� yyzxxq
	 */
	public void setYyzxxq(String yyzxxq) {
		this.yyzxxq = yyzxxq;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param statusҪ���õ� status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the yysbyy
	 */
	public String getYysbyy() {
		return yysbyy;
	}
	/**
	 * @param yysbyyҪ���õ� yysbyy
	 */
	public void setYysbyy(String yysbyy) {
		this.yysbyy = yysbyy;
	}
	/**
	 * @return the createsj
	 */
	public String getCreatesj() {
		return createsj;
	}
	/**
	 * @param createsjҪ���õ� createsj
	 */
	public void setCreatesj(String createsj) {
		this.createsj = createsj;
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
	 * @return the datazt
	 */
	public String getDatazt() {
		return datazt;
	}
	
	/**
	 * @param dataztҪ���õ� datazt
	 */
	public void setDatazt(String datazt) {
		this.datazt = datazt;
	}
	/**
	 * @return the qssj
	 */
	public String getQssj() {
		return qssj;
	}
	/**
	 * @param qssjҪ���õ� qssj
	 */
	public void setQssj(String qssj) {
		this.qssj = qssj;
	}
	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}
	/**
	 * @param jssjҪ���õ� jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXxxq() {
		return xxxq;
	}
	public void setXxxq(String xxxq) {
		this.xxxq = xxxq;
	}
	/**
	 * @return the sfdszn
	 */
	public String getSfdszn() {
		return sfdszn;
	}
	/**
	 * @param sfdsznҪ���õ� sfdszn
	 */
	public void setSfdszn(String sfdszn) {
		this.sfdszn = sfdszn;
	}
	/**
	 * @return the jtszd
	 */
	public String getJtszd() {
		return jtszd;
	}
	/**
	 * @param jtszdҪ���õ� jtszd
	 */
	public void setJtszd(String jtszd) {
		this.jtszd = jtszd;
	}
	/**
	 * @return the jtjjzk
	 */
	public String getJtjjzk() {
		return jtjjzk;
	}
	/**
	 * @param jtjjzkҪ���õ� jtjjzk
	 */
	public void setJtjjzk(String jtjjzk) {
		this.jtjjzk = jtjjzk;
	}
	/**
	 * @return the fqwhcd
	 */
	public String getFqwhcd() {
		return fqwhcd;
	}
	/**
	 * @param fqwhcdҪ���õ� fqwhcd
	 */
	public void setFqwhcd(String fqwhcd) {
		this.fqwhcd = fqwhcd;
	}
	/**
	 * @return the mqwhcd
	 */
	public String getMqwhcd() {
		return mqwhcd;
	}
	/**
	 * @param mqwhcdҪ���õ� mqwhcd
	 */
	public void setMqwhcd(String mqwhcd) {
		this.mqwhcd = mqwhcd;
	}
	/**
	 * @return the fmhyzk
	 */
	public String getFmhyzk() {
		return fmhyzk;
	}
	/**
	 * @param fmhyzkҪ���õ� fmhyzk
	 */
	public void setFmhyzk(String fmhyzk) {
		this.fmhyzk = fmhyzk;
	}
	/**
	 * @return the jtjsbs
	 */
	public String getJtjsbs() {
		return jtjsbs;
	}
	/**
	 * @param jtjsbsҪ���õ� jtjsbs
	 */
	public void setJtjsbs(String jtjsbs) {
		this.jtjsbs = jtjsbs;
	}
	/**
	 * @return the jtxhcd
	 */
	public String getJtxhcd() {
		return jtxhcd;
	}
	/**
	 * @param jtxhcdҪ���õ� jtxhcd
	 */
	public void setJtxhcd(String jtxhcd) {
		this.jtxhcd = jtxhcd;
	}
	/**
	 * @return the sfzl
	 */
	public String getSfzl() {
		return sfzl;
	}
	/**
	 * @param sfzlҪ���õ� sfzl
	 */
	public void setSfzl(String sfzl) {
		this.sfzl = sfzl;
	}
	/**
	 * @return the djrq
	 */
	public String getDjrq() {
		return djrq;
	}
	/**
	 * @param djrqҪ���õ� djrq
	 */
	public void setDjrq(String djrq) {
		this.djrq = djrq;
	}
	/**
	 * @return the yzxwts
	 */
	public String[] getYzxwts() {
		return yzxwts;
	}
	/**
	 * @param yzxwtsҪ���õ� yzxwts
	 */
	public void setYzxwts(String[] yzxwts) {
		this.yzxwts = yzxwts;
	}
	/**
	 * @return the yzxwt
	 */
	public String getYzxwt() {
		return yzxwt;
	}
	/**
	 * @param yzxwtҪ���õ� yzxwt
	 */
	public void setYzxwt(String yzxwt) {
		this.yzxwt = yzxwt;
	}
	/**
	 * @return the wtbc
	 */
	public String getWtbc() {
		return wtbc;
	}
	/**
	 * @param wtbcҪ���õ� wtbc
	 */
	public void setWtbc(String wtbc) {
		this.wtbc = wtbc;
	}
	/**
	 * @return the zxqw
	 */
	public String getZxqw() {
		return zxqw;
	}
	/**
	 * @param zxqwҪ���õ� zxqw
	 */
	public void setZxqw(String zxqw) {
		this.zxqw = zxqw;
	}
	/**
	 * @return the xxList
	 */
	public List<ZxzxjlxxModel> getXxList() {
		return xxList;
	}
	/**
	 * @param xxListҪ���õ� xxList
	 */
	public void setXxList(List<ZxzxjlxxModel> xxList) {
		this.xxList = xxList;
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
	 * @return the txr
	 */
	public String getTxr() {
		return txr;
	}
	/**
	 * @param txrҪ���õ� txr
	 */
	public void setTxr(String txr) {
		this.txr = txr;
	}
	
	
}
