/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-1 ����08:56:54 
 */  
package com.zfsoft.xgxt.xsztz.jdwhsz;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-8-1 ����08:56:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JdwhSzForm extends ActionForm {
	 private String  jdwhid;//�׶�ά��ID
	 private String  xmdm;//��Ŀ����
	 private String  jdid;//�׶�ID
	 private String  jdcy;//�׶γ�Ա,������Ŀ��������ѧ�ţ�������Ŀ���������Ŷ�ID
	 private String  jbf;//�����
	 private String  hdsc;//�ʱ��
	 private String  bz;//��ע
	 private String  jdsj;//�׶�ʱ��
	 private String  xhs;//�ַ���ƴ��ѧ��
	 private String  xh;
	 private FormFile file;
	 private String filepath;
	 private String[]  jdcys;//�׶γ�Ա����
	 private String  ylzd1;
	 private String  ylzd2;
	 private String  ylzd3;
	 private String  ylzd4;
	 private String  ylzd5;
	 private String  sjly1;
	 private String  lylcywid1;
	 private SearchModel searchModel = new SearchModel();
	 private static final long serialVersionUID = 1L;
	 private String type;
     private ExportModel exportModel = new ExportModel();
     private Pages pages = new Pages();
	 /**
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1Ҫ���õ� ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @param ylzd2Ҫ���õ� ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3Ҫ���õ� ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @param ylzd4Ҫ���õ� ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @param ylzd5Ҫ���õ� ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
	}
	/**
	 * @return the sjly1
	 */
	public String getSjly1() {
		return sjly1;
	}
	/**
	 * @param sjly1Ҫ���õ� sjly1
	 */
	public void setSjly1(String sjly1) {
		this.sjly1 = sjly1;
	}
	/**
	 * @return the lylcywid1
	 */
	public String getLylcywid1() {
		return lylcywid1;
	}
	/**
	 * @param lylcywid1Ҫ���õ� lylcywid1
	 */
	public void setLylcywid1(String lylcywid1) {
		this.lylcywid1 = lylcywid1;
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
	 * @return the file
	 */
	public FormFile getFile() {
		return file;
	}
	/**
	 * @param fileҪ���õ� file
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}
	
	 /**
	 * @return the jdcys
	 */
	public String[] getJdcys() {
		return jdcys;
	}
	/**
	 * @param jdcysҪ���õ� jdcys
	 */
	public void setJdcys(String[] jdcys) {
		this.jdcys = jdcys;
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
	 * @return the xhs
	 */
	public String getXhs() {
		return xhs;
	}
	/**
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String xhs) {
		this.xhs = xhs;
	}
  
     /**
	 * @return the jdwhid
	 */
	public String getJdwhid() {
		return jdwhid;
	}
	/**
	 * @param jdwhidҪ���õ� jdwhid
	 */
	public void setJdwhid(String jdwhid) {
		this.jdwhid = jdwhid;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdmҪ���õ� xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	/**
	 * @return the jdid
	 */
	public String getJdid() {
		return jdid;
	}
	/**
	 * @param jdidҪ���õ� jdid
	 */
	public void setJdid(String jdid) {
		this.jdid = jdid;
	}
	/**
	 * @return the jdcy
	 */
	public String getJdcy() {
		return jdcy;
	}
	/**
	 * @param jdcyҪ���õ� jdcy
	 */
	public void setJdcy(String jdcy) {
		this.jdcy = jdcy;
	}
	/**
	 * @return the jbf
	 */
	public String getJbf() {
		return jbf;
	}
	/**
	 * @param jbfҪ���õ� jbf
	 */
	public void setJbf(String jbf) {
		this.jbf = jbf;
	}
	/**
	 * @return the hdsc
	 */
	public String getHdsc() {
		return hdsc;
	}
	/**
	 * @param hdscҪ���õ� hdsc
	 */
	public void setHdsc(String hdsc) {
		this.hdsc = hdsc;
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
	 * @return the jdsj
	 */
	public String getJdsj() {
		return jdsj;
	}
	/**
	 * @param jdsjҪ���õ� jdsj
	 */
	public void setJdsj(String jdsj) {
		this.jdsj = jdsj;
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
	
}
