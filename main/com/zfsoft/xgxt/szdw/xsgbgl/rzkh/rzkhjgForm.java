/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-10 ����05:16:50 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.rzkh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�����Դ[����:1206]
 * @ʱ�䣺 2015-6-10 ����05:16:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class rzkhjgForm extends ActionForm {
	 String khjgid;//���˽��id
	 String xh;//ѧ��
	 String xn;//ѧ��
	 String xq;//ѧ��
	 String zwmc;//ְλ����
	 String rzsj;//��ְʱ��
	 String grsz;//��������
	 String grzp;//��������
	 String zgdwyj;//���ܵ�λ���
	 String qdyj;//�������
	 String szdwyj;//���ڵ�λ���
	 String ddyj;//������
	 String xsgzcyj;//ѧ�����������
	 String bz;//��ע
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel();
	 private String type;
	 private ExportModel exportModel = new ExportModel();
	 private Pages pages = new Pages();
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
	 * @return the khjgid
	 */
	public String getKhjgid() {
		return khjgid;
	}
	/**
	 * @param khjgidҪ���õ� khjgid
	 */
	public void setKhjgid(String khjgid) {
		this.khjgid = khjgid;
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
	 * @return the zwmc
	 */
	public String getZwmc() {
		return zwmc;
	}
	/**
	 * @param zwmcҪ���õ� zwmc
	 */
	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}
	/**
	 * @return the rzsj
	 */
	public String getRzsj() {
		return rzsj;
	}
	/**
	 * @param rzsjҪ���õ� rzsj
	 */
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	/**
	 * @return the grsz
	 */
	public String getGrsz() {
		return grsz;
	}
	/**
	 * @param grszҪ���õ� grsz
	 */
	public void setGrsz(String grsz) {
		this.grsz = grsz;
	}
	/**
	 * @return the grzp
	 */
	public String getGrzp() {
		return grzp;
	}
	/**
	 * @param grzpҪ���õ� grzp
	 */
	public void setGrzp(String grzp) {
		this.grzp = grzp;
	}
	/**
	 * @return the zgdwyj
	 */
	public String getZgdwyj() {
		return zgdwyj;
	}
	/**
	 * @param zgdwyjҪ���õ� zgdwyj
	 */
	public void setZgdwyj(String zgdwyj) {
		this.zgdwyj = zgdwyj;
	}
	/**
	 * @return the qdyj
	 */
	public String getQdyj() {
		return qdyj;
	}
	/**
	 * @param qdyjҪ���õ� qdyj
	 */
	public void setQdyj(String qdyj) {
		this.qdyj = qdyj;
	}
	/**
	 * @return the szdwyj
	 */
	public String getSzdwyj() {
		return szdwyj;
	}
	/**
	 * @param szdwyjҪ���õ� szdwyj
	 */
	public void setSzdwyj(String szdwyj) {
		this.szdwyj = szdwyj;
	}
	/**
	 * @return the ddyj
	 */
	public String getDdyj() {
		return ddyj;
	}
	/**
	 * @param ddyjҪ���õ� ddyj
	 */
	public void setDdyj(String ddyj) {
		this.ddyj = ddyj;
	}

	/**
	 * @return the xsgzcyj
	 */
	public String getXsgzcyj() {
		return xsgzcyj;
	}
	/**
	 * @param xsgzcyjҪ���õ� xsgzcyj
	 */
	public void setXsgzcyj(String xsgzcyj) {
		this.xsgzcyj = xsgzcyj;
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
}
