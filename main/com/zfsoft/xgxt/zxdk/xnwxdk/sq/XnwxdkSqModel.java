/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-18 ����02:34:16 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.sq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-18 ����02:34:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkSqModel extends ActionForm {
	 String  sqid;
	 String  xh;
	 String  xn;
	 String  xq;
	 String  sqsj;
	 String  shzt;
	 String  splc;
	 String  jttg;
	 String  zxj;
	 String  jxj;
	 String  qgzxsr;
	 String  xnwxjk;
	 String  qtsr;
	 String  zxdksqje;
	 String  ffje;
	 String  sqje;
	 String  sqly;
	 String  sqr;
	 String  zxdksqsj;
	 String  ffsj;
	 String  type;
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel();
	//����
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
	 
	//�������
	private FormFile formfile;
	private String filepath;
	 
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
	 * @return the jttg
	 */
	public String getJttg() {
		return jttg;
	}
	/**
	 * @param jttgҪ���õ� jttg
	 */
	public void setJttg(String jttg) {
		this.jttg = jttg;
	}
	/**
	 * @return the zxj
	 */
	public String getZxj() {
		return zxj;
	}
	/**
	 * @param zxjҪ���õ� zxj
	 */
	public void setZxj(String zxj) {
		this.zxj = zxj;
	}
	/**
	 * @return the jxj
	 */
	public String getJxj() {
		return jxj;
	}
	/**
	 * @param jxjҪ���õ� jxj
	 */
	public void setJxj(String jxj) {
		this.jxj = jxj;
	}
	/**
	 * @return the qgzxsr
	 */
	public String getQgzxsr() {
		return qgzxsr;
	}
	/**
	 * @param qgzxsrҪ���õ� qgzxsr
	 */
	public void setQgzxsr(String qgzxsr) {
		this.qgzxsr = qgzxsr;
	}
	/**
	 * @return the xnwxjk
	 */
	public String getXnwxjk() {
		return xnwxjk;
	}
	/**
	 * @param xnwxjkҪ���õ� xnwxjk
	 */
	public void setXnwxjk(String xnwxjk) {
		this.xnwxjk = xnwxjk;
	}
	/**
	 * @return the qtsr
	 */
	public String getQtsr() {
		return qtsr;
	}
	/**
	 * @param qtsrҪ���õ� qtsr
	 */
	public void setQtsr(String qtsr) {
		this.qtsr = qtsr;
	}
	/**
	 * @return the zxdksqje
	 */
	public String getZxdksqje() {
		return zxdksqje;
	}
	/**
	 * @param zxdksqjeҪ���õ� zxdksqje
	 */
	public void setZxdksqje(String zxdksqje) {
		this.zxdksqje = zxdksqje;
	}
	/**
	 * @return the ffje
	 */
	public String getFfje() {
		return ffje;
	}
	/**
	 * @param ffjeҪ���õ� ffje
	 */
	public void setFfje(String ffje) {
		this.ffje = ffje;
	}
	/**
	 * @return the sqje
	 */
	public String getSqje() {
		return sqje;
	}
	/**
	 * @param sqjeҪ���õ� sqje
	 */
	public void setSqje(String sqje) {
		this.sqje = sqje;
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
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * @param sqrҪ���õ� sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	/**
	 * @return the zxdksqsj
	 */
	public String getZxdksqsj() {
		return zxdksqsj;
	}
	/**
	 * @param zxdksqsjҪ���õ� zxdksqsj
	 */
	public void setZxdksqsj(String zxdksqsj) {
		this.zxdksqsj = zxdksqsj;
	}
	/**
	 * @return the ffsj
	 */
	public String getFfsj() {
		return ffsj;
	}
	/**
	 * @param ffsjҪ���õ� ffsj
	 */
	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
