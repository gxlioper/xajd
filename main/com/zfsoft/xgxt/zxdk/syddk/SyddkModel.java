/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-29 ����09:25:23 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ����-��Դ�ش��� 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-29 ����09:25:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SyddkModel extends ActionForm {

	
	private static final long serialVersionUID = 788630279462692940L;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String id;
	private String xh;
	private String xn;
	private String dkyh;
	private String dkje;
	private String dkqx;
	private String htbh;
	private String dkkssj;
	private String hzjym;
	private String sqly;
	private String lrsj;
	private String xfyss;//ѧ��Ӧ����
	private String zsfyss;//ס�޷�Ӧ����
	private String sjly;
	private String yhdm;
	private String[] xf;
	private String[] zsf;
	private String[] shf;
	private String[] dkxn;
	
	private String yhmc;
	
	//�������
	private FormFile formfile;
	private String filepath;
	
	//�ӱ����ĸ��Ի�
	private String dkcs;	//�������
	private String dqrq;	//��������
	
	
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
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
	 * @return the dkyh
	 */
	public String getDkyh() {
		return dkyh;
	}
	/**
	 * @param dkyhҪ���õ� dkyh
	 */
	public void setDkyh(String dkyh) {
		this.dkyh = dkyh;
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
	 * @return the hzjym
	 */
	public String getHzjym() {
		return hzjym;
	}
	/**
	 * @param hzjymҪ���õ� hzjym
	 */
	public void setHzjym(String hzjym) {
		this.hzjym = hzjym;
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
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsjҪ���õ� lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	/**
	 * @return the xf
	 */
	public String[] getXf() {
		return xf;
	}
	/**
	 * @param xfҪ���õ� xf
	 */
	public void setXf(String[] xf) {
		this.xf = xf;
	}
	/**
	 * @return the zsf
	 */
	public String[] getZsf() {
		return zsf;
	}
	/**
	 * @param zsfҪ���õ� zsf
	 */
	public void setZsf(String[] zsf) {
		this.zsf = zsf;
	}
	/**
	 * @return the shf
	 */
	public String[] getShf() {
		return shf;
	}
	/**
	 * @param shfҪ���õ� shf
	 */
	public void setShf(String[] shf) {
		this.shf = shf;
	}
	/**
	 * @return the dkxn
	 */
	public String[] getDkxn() {
		return dkxn;
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
	 * @param dkxnҪ���õ� dkxn
	 */
	public void setDkxn(String[] dkxn) {
		this.dkxn = dkxn;
	}
	/**
	 * @return the xfyss
	 */
	public String getXfyss() {
		return xfyss;
	}
	/**
	 * @param xfyssҪ���õ� xfyss
	 */
	public void setXfyss(String xfyss) {
		this.xfyss = xfyss;
	}
	/**
	 * @return the zsfyss
	 */
	public String getZsfyss() {
		return zsfyss;
	}
	/**
	 * @param zsfyssҪ���õ� zsfyss
	 */
	public void setZsfyss(String zsfyss) {
		this.zsfyss = zsfyss;
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
	 * @return the dkcs
	 */
	public String getDkcs() {
		return dkcs;
	}
	/**
	 * @param dkcsҪ���õ� dkcs
	 */
	public void setDkcs(String dkcs) {
		this.dkcs = dkcs;
	}
	/**
	 * @return the dqrq
	 */
	public String getDqrq() {
		return dqrq;
	}
	/**
	 * @param dqrqҪ���õ� dqrq
	 */
	public void setDqrq(String dqrq) {
		this.dqrq = dqrq;
	}
	
	
}
