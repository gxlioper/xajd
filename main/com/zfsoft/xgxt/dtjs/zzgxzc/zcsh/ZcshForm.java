/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-10 ����04:54:54 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-2-10 ����04:54:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcshForm extends ActionForm {
	 private String sqid;//����id
	 private String xh;//ѧ��
	 private String szdzb;//���ڵ�֧��
	 private String sfsn;//�Ƿ�ʡ��
	 private String jsdzz;//���յ���֯
	 private String sqdw;//��ȥ��λ
	 private String dfjzrq;//���ѽ�������
	 private String sfkjhyzm;//�Ƿ񿪾߻���֤��
	 private String jsxbh;//�����ű��
	 private String shzt;//���״̬
	 private String splcid;//��������id
	 private String sqr;//������
	 private String sqsj;//����ʱ��
	 
	 private String type;
	 private SearchModel searchModel = new SearchModel();
	 private Pages pages = new Pages();
	 private ExportModel exportModel = new ExportModel();
	 
	//����ֶ�
	 private String shid;
	 private String shjg;
	 private String shyj;
	 private String gwid;
	 private String thgw;
	 private String shlc;
	 
	//���������
	 private String[] id;
	 private String[] gwids;
     private String[] xhs;
     private static final long serialVersionUID = 1L;
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
	 * @return the szdzb
	 */
	public String getSzdzb() {
		return szdzb;
	}
	/**
	 * @param szdzbҪ���õ� szdzb
	 */
	public void setSzdzb(String szdzb) {
		this.szdzb = szdzb;
	}
	/**
	 * @return the sfsn
	 */
	public String getSfsn() {
		return sfsn;
	}
	/**
	 * @param sfsnҪ���õ� sfsn
	 */
	public void setSfsn(String sfsn) {
		this.sfsn = sfsn;
	}
	/**
	 * @return the jsdzz
	 */
	public String getJsdzz() {
		return jsdzz;
	}
	/**
	 * @param jsdzzҪ���õ� jsdzz
	 */
	public void setJsdzz(String jsdzz) {
		this.jsdzz = jsdzz;
	}
	/**
	 * @return the sqdw
	 */
	public String getSqdw() {
		return sqdw;
	}
	/**
	 * @param sqdwҪ���õ� sqdw
	 */
	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}
	/**
	 * @return the dfjzrq
	 */
	public String getDfjzrq() {
		return dfjzrq;
	}
	/**
	 * @param dfjzrqҪ���õ� dfjzrq
	 */
	public void setDfjzrq(String dfjzrq) {
		this.dfjzrq = dfjzrq;
	}
	/**
	 * @return the sfkjhyzm
	 */
	public String getSfkjhyzm() {
		return sfkjhyzm;
	}
	/**
	 * @param sfkjhyzmҪ���õ� sfkjhyzm
	 */
	public void setSfkjhyzm(String sfkjhyzm) {
		this.sfkjhyzm = sfkjhyzm;
	}
	/**
	 * @return the jsxbh
	 */
	public String getJsxbh() {
		return jsxbh;
	}
	/**
	 * @param jsxbhҪ���õ� jsxbh
	 */
	public void setJsxbh(String jsxbh) {
		this.jsxbh = jsxbh;
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
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shidҪ���õ� shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjgҪ���õ� shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	/**
	 * @return the shyj
	 */
	public String getShyj() {
		return shyj;
	}
	/**
	 * @param shyjҪ���õ� shyj
	 */
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwidҪ���õ� gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgwҪ���õ� thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlcҪ���õ� shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String[] id) {
		this.id = id;
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
	
}
