/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-11 ����09:09:59 
 */  
package com.zfsoft.xgxt.jskp.zzsq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧϰ���۹���ģ��
 * @�๦������: ��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-11 ����09:09:20 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XspjsqForm extends ActionForm{
	private static final long serialVersionUID = -4562302399219564190L;
	private Pages pages = new Pages();//��ҳ
	private SearchModel searchModel = new SearchModel();//�߼���ѯ
	private ExportModel exportModel = new ExportModel();//�Զ��嵼��
	private String sqid;//����ID
	private String xh;//ѧ��
	private String xn;//ѧ��
	private String fz;//��ֵ
	private String dxqdm;//��ѧ�ڴ���
	private String xmmc;//��Ŀ����
	private String zdbmdm;//ָ�����Ŵ���
	private String xmlbdm;//��Ŀ������
	private String cysj;//����ʱ��
	private String fzrxm;//����������
	private String fzrlxfs;//��������ϵ��ʽ
	private String zdlsxm;//ָ����ʦ����
	private String zdlslxfs;//ָ����ʦ��ϵ��ʽ
	private String fjid;//����id
	private String sqly;//��������
	private String sjlrr;//����¼����
	private String sjlrsj;//����¼��ʱ��
	private String shzt;//���״̬
	private String splcid;//��������ID
	private String type;//����
	private String saveFlag;//��������(����ʱ�Ĳ���)
	private FormFile drmb;/*����ģ��*/
	private String exclePath;/*����ģ��·��*/
	private String filepath;/*�ļ� ·��*/
	
	/**
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}
	/**
	 * @param drmbҪ���õ� drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
	}
	/**
	 * @return the exclePath
	 */
	public String getExclePath() {
		return exclePath;
	}
	/**
	 * @param exclePathҪ���õ� exclePath
	 */
	public void setExclePath(String exclePath) {
		this.exclePath = exclePath;
	}
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepathҪ���õ� filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
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
	 * @return the fz
	 */
	public String getFz() {
		return fz;
	}
	/**
	 * @param fzҪ���õ� fz
	 */
	public void setFz(String fz) {
		this.fz = fz;
	}
	/**
	 * @return the dxqdm
	 */
	public String getDxqdm() {
		return dxqdm;
	}
	/**
	 * @param dxqdmҪ���õ� dxqdm
	 */
	public void setDxqdm(String dxqdm) {
		this.dxqdm = dxqdm;
	}
	/**
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmcҪ���õ� xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the zdbmdm
	 */
	public String getZdbmdm() {
		return zdbmdm;
	}
	/**
	 * @param zdbmdmҪ���õ� zdbmdm
	 */
	public void setZdbmdm(String zdbmdm) {
		this.zdbmdm = zdbmdm;
	}
	/**
	 * @return the xmlbdm
	 */
	public String getXmlbdm() {
		return xmlbdm;
	}
	/**
	 * @param xmlbdmҪ���õ� xmlbdm
	 */
	public void setXmlbdm(String xmlbdm) {
		this.xmlbdm = xmlbdm;
	}
	/**
	 * @return the cysj
	 */
	public String getCysj() {
		return cysj;
	}
	/**
	 * @param cysjҪ���õ� cysj
	 */
	public void setCysj(String cysj) {
		this.cysj = cysj;
	}
	/**
	 * @return the fzrxm
	 */
	public String getFzrxm() {
		return fzrxm;
	}
	/**
	 * @param fzrxmҪ���õ� fzrxm
	 */
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
	}
	/**
	 * @return the fzrlxfs
	 */
	public String getFzrlxfs() {
		return fzrlxfs;
	}
	/**
	 * @param fzrlxfsҪ���õ� fzrlxfs
	 */
	public void setFzrlxfs(String fzrlxfs) {
		this.fzrlxfs = fzrlxfs;
	}
	/**
	 * @return the zdlsxm
	 */
	public String getZdlsxm() {
		return zdlsxm;
	}
	/**
	 * @param zdlsxmҪ���õ� zdlsxm
	 */
	public void setZdlsxm(String zdlsxm) {
		this.zdlsxm = zdlsxm;
	}
	/**
	 * @return the zdlslxfs
	 */
	public String getZdlslxfs() {
		return zdlslxfs;
	}
	/**
	 * @param zdlslxfsҪ���õ� zdlslxfs
	 */
	public void setZdlslxfs(String zdlslxfs) {
		this.zdlslxfs = zdlslxfs;
	}
	/**
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}
	/**
	 * @param fjidҪ���õ� fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
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
