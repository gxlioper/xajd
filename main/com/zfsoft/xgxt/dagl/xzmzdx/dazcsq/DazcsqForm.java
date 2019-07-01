/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����03:13:09 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ����ת��-����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����03:13:09 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazcsqForm extends ActionForm{
	
	private static final long serialVersionUID = -4562302399219564190L;
	private Pages pages = new Pages();//��ҳ
	private String type;//����
	private SearchModel searchModel = new SearchModel();//�߼���ѯ
	private ExportModel exportModel = new ExportModel();//�Զ��嵼��
	private String sqid;//����ID(ϵͳĬ��)
	private String xh;//ѧ��
	private String zcfs;//ת����ʽ
	private String yjdz;//�ʼĵ�ַ
	private String yzbm;//��������
	private String sjr;//�ռ���
	private String sjrdh;//�ռ��˵绰
	private String dwmc;//��λ����
	private String dwdz;//��λ��ַ
	private String zddacn;//�Դ�������ŵ
	private String yqtdrq;//Ԥ���ᵵ����(������)
	private String sjlrr;//����¼����
	private String sjlrsj;//����¼��ʱ��
	private String shzt;//���״̬
	private String splcid;//��������ID
	private String saveFlag;//��������(����ʱ�Ĳ���)
	
	/**
	 * @return the saveFlag
	 */
	public String getSaveFlag() {
		return saveFlag;
	}
	/**
	 * @param saveFlagҪ���õ� saveFlag
	 */
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
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
}
