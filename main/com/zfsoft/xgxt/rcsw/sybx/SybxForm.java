/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-9 ����08:45:18 
 */  
package com.zfsoft.xgxt.rcsw.sybx;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: TODO ��ҵ���չ���
 * @���ߣ� honglin 
 * @ʱ�䣺 2013-5-8 ����05:22:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SybxForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private String type;//����
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String guid ;//ID
	private String xh ;//ѧ��
	private String xn ;//ѧ��
	private String jhrxm ;//�໤������
	private String jhrsfzh ;//�໤��
	private String txdz ;//ͨѶ��ַ
	private String bxje ;//�����ܽ��
	private String bz ;//��ע
	private String czjmylbxje ;//�������ҽ�Ʊ��ս��
	private String sybxje ;//��ҵ���ս��
	private String zjyy ;//����ԭ��
	private String czjmylbxcbqsrq ;//�������ҽ�Ʊ��ղα���ʼ����
	private String czjmylbxcbjsrq ;//�������ҽ�Ʊ��ղα���������
	private String sybxcbqsrq ;//��ҵ���ղα���ʼ����
	private String sybxcbjsrq ;//��ҵ���ղα���������
	private String cbrylb ;//�α���Ա���
	private String jfrylb ;//�ɷ���Ա���
	private String sfzqfjg ;//���֤ǩ������
	private String sfzyxqxqsrq ;//���֤��Ч������ʼ����
	private String sfzyxqxjzrq ;//���֤��Ч���޽�ֹ����

	private ExportModel exportModel = new ExportModel();
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getJhrxm() {
		return jhrxm;
	}
	public void setJhrxm(String jhrxm) {
		this.jhrxm = jhrxm;
	}
	public String getJhrsfzh() {
		return jhrsfzh;
	}
	public void setJhrsfzh(String jhrsfzh) {
		this.jhrsfzh = jhrsfzh;
	}
	public String getTxdz() {
		return txdz;
	}
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getBxje() {
		return bxje;
	}
	public void setBxje(String bxje) {
		this.bxje = bxje;
	}
	public String getCzjmylbxje() {
		return czjmylbxje;
	}
	public void setCzjmylbxje(String czjmylbxje) {
		this.czjmylbxje = czjmylbxje;
	}
	public String getSybxje() {
		return sybxje;
	}
	public void setSybxje(String sybxje) {
		this.sybxje = sybxje;
	}
	public String getZjyy() {
		return zjyy;
	}
	public void setZjyy(String zjyy) {
		this.zjyy = zjyy;
	}
	public String getCzjmylbxcbqsrq() {
		return czjmylbxcbqsrq;
	}
	public void setCzjmylbxcbqsrq(String czjmylbxcbqsrq) {
		this.czjmylbxcbqsrq = czjmylbxcbqsrq;
	}
	public String getCzjmylbxcbjsrq() {
		return czjmylbxcbjsrq;
	}
	public void setCzjmylbxcbjsrq(String czjmylbxcbjsrq) {
		this.czjmylbxcbjsrq = czjmylbxcbjsrq;
	}
	public String getSybxcbqsrq() {
		return sybxcbqsrq;
	}
	public void setSybxcbqsrq(String sybxcbqsrq) {
		this.sybxcbqsrq = sybxcbqsrq;
	}
	public String getSybxcbjsrq() {
		return sybxcbjsrq;
	}
	public void setSybxcbjsrq(String sybxcbjsrq) {
		this.sybxcbjsrq = sybxcbjsrq;
	}
	public String getCbrylb() {
		return cbrylb;
	}
	public void setCbrylb(String cbrylb) {
		this.cbrylb = cbrylb;
	}
	public String getJfrylb() {
		return jfrylb;
	}
	public void setJfrylb(String jfrylb) {
		this.jfrylb = jfrylb;
	}
	public String getSfzqfjg() {
		return sfzqfjg;
	}
	public void setSfzqfjg(String sfzqfjg) {
		this.sfzqfjg = sfzqfjg;
	}
	public String getSfzyxqxqsrq() {
		return sfzyxqxqsrq;
	}
	public void setSfzyxqxqsrq(String sfzyxqxqsrq) {
		this.sfzyxqxqsrq = sfzyxqxqsrq;
	}
	public String getSfzyxqxjzrq() {
		return sfzyxqxjzrq;
	}
	public void setSfzyxqxjzrq(String sfzyxqxjzrq) {
		this.sfzyxqxjzrq = sfzyxqxjzrq;
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
	
}
