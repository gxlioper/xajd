/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-1 ����04:09:29 
 */
package com.zfsoft.xgxt.zxdk.syddk;

import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-7-1 ����04:09:29
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class SydkSqshModel extends SuperAuditModel {

	private static final long serialVersionUID = 1L;

	private String id;
	private String xn;
	private String xh;
	private String yhdm;//���д���
	private String yhmc;
	private String dkje;// ������
	private String dkqx;// ��������
	private String zsysf;// ס��Ӧ�շ�
	private String xfysf;// ѧ��Ӧ�շ�
	private String sqsj;
	private String sqly;
	private String shzt;
	private String splcid;
	
	private String htbh;
	private String dkkssj;
	private String hzjym;
    
	private String[] xf;
	private String[] zsf;
	private String[] shf;
	private String[] dkxn;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	//�������
	private FormFile formfile;
	private String filepath;
    
	//�������������
	private String[] ids;
	private String[] gwids;
	private String[] xhs;
	
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

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String[] getXf() {
		return xf;
	}

	public void setXf(String[] xf) {
		this.xf = xf;
	}

	public String[] getZsf() {
		return zsf;
	}

	public void setZsf(String[] zsf) {
		this.zsf = zsf;
	}

	public String[] getShf() {
		return shf;
	}

	public void setShf(String[] shf) {
		this.shf = shf;
	}

	public String[] getDkxn() {
		return dkxn;
	}

	public void setDkxn(String[] dkxn) {
		this.dkxn = dkxn;
	}

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getDkkssj() {
		return dkkssj;
	}

	public void setDkkssj(String dkkssj) {
		this.dkkssj = dkkssj;
	}

	public String getHzjym() {
		return hzjym;
	}

	public void setHzjym(String hzjym) {
		this.hzjym = hzjym;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getYhdm() {
		return yhdm;
	}

	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}

	public String getDkje() {
		return dkje;
	}

	public void setDkje(String dkje) {
		this.dkje = dkje;
	}

	public String getDkqx() {
		return dkqx;
	}

	public void setDkqx(String dkqx) {
		this.dkqx = dkqx;
	}

	public String getZsysf() {
		return zsysf;
	}

	public void setZsysf(String zsysf) {
		this.zsysf = zsysf;
	}

	public String getXfysf() {
		return xfysf;
	}

	public void setXfysf(String xfysf) {
		this.xfysf = xfysf;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getSplcid() {
		return splcid;
	}

	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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
}
