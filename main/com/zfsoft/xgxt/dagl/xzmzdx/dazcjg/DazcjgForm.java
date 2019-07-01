/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����02:54:21 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ����ת��-���
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����02:54:21 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazcjgForm extends ActionForm{
	
	private static final long serialVersionUID = -4562302399219564190L;
	private Pages pages = new Pages();//��ҳ
	private String type;//����
	private SearchModel searchModel = new SearchModel();//�߼���ѯ
	private ExportModel exportModel = new ExportModel();//�Զ��嵼��
	
	private String guid;//���ID(ϵͳĬ��)
	private String xh;//ѧ��
	private String zcfs;//ת����ʽ,1:�ʼġ�2:�Դ�
	private String yjdz;//�ʼĵ�ַ
	private String yzbm;//��������
	private String sjr;//�ռ���
	private String sjrdh;//�ռ��˵绰
	private String dwmc;//��λ����
	private String dwdz;//��λ��ַ
	private String kdfs;//��ݷ�ʽ
	private String kddh;//��ݵ���
	private String yjsj;//�ʼ�ʱ��(������)
	private String yjzt;//�ʼ�״̬,1:���ʼġ�2:δ�ʼ�
	private String zddacn;//�Դ�������ŵ
	private String yqtdrq;//Ԥ���ᵵ����(������)
	private String sjtdrq;//ʵ���ᵵ����(������)
	private String sjtdr;//ʵ���ᵵ��
	private String dazcxx;//����ת����Ϣ��1:�ѵǼǡ�2:��ת����3:δ�Ǽǡ�
	private String sjly;//������Դ��1:������ˡ�2:������ӡ�
	private String ywid;//ҵ��id
	private String sjlrr;//����¼����
	private String sjlrsj;//����¼��ʱ�䡾yyyy-mm-dd hh24:mi:ss��
	
	/**
	 * @return the dwdz
	 */
	public String getDwdz() {
		return dwdz;
	}
	/**
	 * @param dwdzҪ���õ� dwdz
	 */
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
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
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guidҪ���õ� guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
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
	 * @return the zcfs
	 */
	public String getZcfs() {
		return zcfs;
	}
	/**
	 * @param zcfsҪ���õ� zcfs
	 */
	public void setZcfs(String zcfs) {
		this.zcfs = zcfs;
	}
	/**
	 * @return the yjdz
	 */
	public String getYjdz() {
		return yjdz;
	}
	/**
	 * @param yjdzҪ���õ� yjdz
	 */
	public void setYjdz(String yjdz) {
		this.yjdz = yjdz;
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
	 * @return the sjr
	 */
	public String getSjr() {
		return sjr;
	}
	/**
	 * @param sjrҪ���õ� sjr
	 */
	public void setSjr(String sjr) {
		this.sjr = sjr;
	}
	/**
	 * @return the sjrdh
	 */
	public String getSjrdh() {
		return sjrdh;
	}
	/**
	 * @param sjrdhҪ���õ� sjrdh
	 */
	public void setSjrdh(String sjrdh) {
		this.sjrdh = sjrdh;
	}
	/**
	 * @return the dwmc
	 */
	public String getDwmc() {
		return dwmc;
	}
	/**
	 * @param dwmcҪ���õ� dwmc
	 */
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	/**
	 * @return the kdfs
	 */
	public String getKdfs() {
		return kdfs;
	}
	/**
	 * @param kdfsҪ���õ� kdfs
	 */
	public void setKdfs(String kdfs) {
		this.kdfs = kdfs;
	}
	/**
	 * @return the kddh
	 */
	public String getKddh() {
		return kddh;
	}
	/**
	 * @param kddhҪ���õ� kddh
	 */
	public void setKddh(String kddh) {
		this.kddh = kddh;
	}
	/**
	 * @return the yjsj
	 */
	public String getYjsj() {
		return yjsj;
	}
	/**
	 * @param yjsjҪ���õ� yjsj
	 */
	public void setYjsj(String yjsj) {
		this.yjsj = yjsj;
	}
	/**
	 * @return the yjzt
	 */
	public String getYjzt() {
		return yjzt;
	}
	/**
	 * @param yjztҪ���õ� yjzt
	 */
	public void setYjzt(String yjzt) {
		this.yjzt = yjzt;
	}
	/**
	 * @return the zddacn
	 */
	public String getZddacn() {
		return zddacn;
	}
	/**
	 * @param zddacnҪ���õ� zddacn
	 */
	public void setZddacn(String zddacn) {
		this.zddacn = zddacn;
	}
	/**
	 * @return the yqtdrq
	 */
	public String getYqtdrq() {
		return yqtdrq;
	}
	/**
	 * @param yqtdrqҪ���õ� yqtdrq
	 */
	public void setYqtdrq(String yqtdrq) {
		this.yqtdrq = yqtdrq;
	}
	/**
	 * @return the sjtdrq
	 */
	public String getSjtdrq() {
		return sjtdrq;
	}
	/**
	 * @param sjtdrqҪ���õ� sjtdrq
	 */
	public void setSjtdrq(String sjtdrq) {
		this.sjtdrq = sjtdrq;
	}
	/**
	 * @return the sjtdr
	 */
	public String getSjtdr() {
		return sjtdr;
	}
	/**
	 * @param sjtdrҪ���õ� sjtdr
	 */
	public void setSjtdr(String sjtdr) {
		this.sjtdr = sjtdr;
	}
	/**
	 * @return the dazcxx
	 */
	public String getDazcxx() {
		return dazcxx;
	}
	/**
	 * @param dazcxxҪ���õ� dazcxx
	 */
	public void setDazcxx(String dazcxx) {
		this.dazcxx = dazcxx;
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
	 * @return the sjlrr
	 */
	public String getSjlrr() {
		return sjlrr;
	}
	/**
	 * @param sjlrrҪ���õ� sjlrr
	 */
	public void setSjlrr(String sjlrr) {
		this.sjlrr = sjlrr;
	}
	/**
	 * @return the sjlrsj
	 */
	public String getSjlrsj() {
		return sjlrsj;
	}
	/**
	 * @param sjlrsjҪ���õ� sjlrsj
	 */
	public void setSjlrsj(String sjlrsj) {
		this.sjlrsj = sjlrsj;
	}
}
