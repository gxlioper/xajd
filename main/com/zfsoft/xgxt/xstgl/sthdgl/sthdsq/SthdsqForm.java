/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����05:02:37 
 */  
package com.zfsoft.xgxt.xstgl.sthdgl.sthdsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-9 ����05:02:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SthdsqForm extends ActionForm {
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;

	private String hdid;
/*	private String xn;
	private String xq;
	private String xqmc;*/
	private String xh;
	private String hdmc;//�����
	private String fwsj;//����ʱ��
	private String fwsc;//����ʱ��
	private String fwdd;//����ص�
	private String zbdw;//���쵥λ
	private String fjid;
	private String splc;
	private String shzt;
	private String fwddssx;//����ص�ʡ����
	private String lrr;
	private String lrsj;

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public String getHdid() {
		return hdid;
	}

	public void setHdid(String hdid) {
		this.hdid = hdid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getHdmc() {
		return hdmc;
	}

	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}

	public String getFwsj() {
		return fwsj;
	}

	public void setFwsj(String fwsj) {
		this.fwsj = fwsj;
	}

	public String getFwsc() {
		return fwsc;
	}

	public void setFwsc(String fwsc) {
		this.fwsc = fwsc;
	}

	public String getFwdd() {
		return fwdd;
	}

	public void setFwdd(String fwdd) {
		this.fwdd = fwdd;
	}

	public String getZbdw() {
		return zbdw;
	}

	public void setZbdw(String zbdw) {
		this.zbdw = zbdw;
	}

	public String getFjid() {
		return fjid;
	}

	public void setFjid(String fjid) {
		this.fjid = fjid;
	}

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getFwddssx() {
		return fwddssx;
	}

	public void setFwddssx(String fwddssx) {
		this.fwddssx = fwddssx;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
