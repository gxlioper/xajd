/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-10 ����10:15:42 
 */
package com.zfsoft.xgxt.rcsw.fyff.ffjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ����񡪡����÷��š������Ž�� ����ģ��
 * @�๦������: ���Ž��ά��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-10 ����10:15:42
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FyffjgForm extends ActionForm {

	private static final long serialVersionUID = -2002064774952893703L;

	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();

	private String guid; // guid
	private String xh; // ѧ��
	private String ffxmdm; // ������Ŀ����
	private String ffsj; // ����ʱ��
	private String fftjdm; // ����;������
	private String sfje; // ʵ�����
	private String ffzh; // �����˺�
	private String bfyfs; // �����·���
	private String bfje; // �������
	private String bz; // ��ע
	private String fffs; // ���ŷ�ʽ

	private String ffxmdmkz;
	private String fftjdmkz;
	private String ffsjkz;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getFfxmdm() {
		return ffxmdm;
	}

	public void setFfxmdm(String ffxmdm) {
		this.ffxmdm = ffxmdm;
	}

	public String getFfsj() {
		return ffsj;
	}

	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}

	public String getFftjdm() {
		return fftjdm;
	}

	public void setFftjdm(String fftjdm) {
		this.fftjdm = fftjdm;
	}

	public String getSfje() {
		return sfje;
	}

	public void setSfje(String sfje) {
		this.sfje = sfje;
	}

	public String getFfzh() {
		return ffzh;
	}

	public void setFfzh(String ffzh) {
		this.ffzh = ffzh;
	}

	public String getBfyfs() {
		return bfyfs;
	}

	public void setBfyfs(String bfyfs) {
		this.bfyfs = bfyfs;
	}

	public String getBfje() {
		return bfje;
	}

	public void setBfje(String bfje) {
		this.bfje = bfje;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getFffs() {
		return fffs;
	}

	public void setFffs(String fffs) {
		this.fffs = fffs;
	}

	public String getFfxmdmkz() {
		if(StringUtils.isNull(ffxmdmkz)){
			setFfxmdmkz(ffxmdm);
		}
		return ffxmdmkz;
	}

	public void setFfxmdmkz(String ffxmdmkz) {
		this.ffxmdmkz = ffxmdmkz;
	}

	public String getFftjdmkz() {
		if(StringUtils.isNull(fftjdmkz)){
			setFftjdmkz(fftjdm);
		}
		return fftjdmkz;
	}

	public void setFftjdmkz(String fftjdmkz) {
		this.fftjdmkz = fftjdmkz;
	}

	public String getFfsjkz() {
		if(StringUtils.isNull(ffsjkz)){
			setFfsjkz(ffsj);
		}
		return ffsjkz;
	}

	public void setFfsjkz(String ffsjkz) {
		this.ffsjkz = ffsjkz;
	}

}
