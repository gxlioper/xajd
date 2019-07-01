/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-26 ����04:09:03 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����_���ڹ���_����ģ��
 * @�๦������: ����ά��form
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-10-26 ����04:09:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqwhForm extends ActionForm {

	private static final long serialVersionUID = 2128327451308674726L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String bjmc;
	private String bjrs;
	private String sjrs;
	private String kkrs;
	private String cql;
	private String kkxq;
	private String toyf;
	private String tozc;
    private String xqmc;
    private String[] xhArr;
    private String xh;
    private String bjcs;
    private String sjcs;
    private String kkjs;
    private String zydm;
    
    private String xydm;
    
    private String id; 		//
    private String xn; 		//ѧ��
    private String xq; 		//ѧ��
    private String yf; 		//�·�
    private String zc; 		//�ܴ�
    private String bjdm; 	//�༶����
    private String cqrs; 	//��������
    private String bz; 		//��ע
    private String shzt; 	//���״̬
    private String splc; 	//��������
    private String jlr; 	//��¼��
    private String jlsj;	//��¼ʱ��
    
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
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmcҪ���õ� bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the bjrs
	 */
	public String getBjrs() {
		return bjrs;
	}
	/**
	 * @param bjrsҪ���õ� bjrs
	 */
	public void setBjrs(String bjrs) {
		this.bjrs = bjrs;
	}
	/**
	 * @return the sjrs
	 */
	public String getSjrs() {
		return sjrs;
	}
	/**
	 * @param sjrsҪ���õ� sjrs
	 */
	public void setSjrs(String sjrs) {
		this.sjrs = sjrs;
	}
	/**
	 * @return the kkrs
	 */
	public String getKkrs() {
		return kkrs;
	}
	/**
	 * @param kkrsҪ���õ� kkrs
	 */
	public void setKkrs(String kkrs) {
		this.kkrs = kkrs;
	}
	/**
	 * @return the cql
	 */
	public String getCql() {
		return cql;
	}
	/**
	 * @param cqlҪ���õ� cql
	 */
	public void setCql(String cql) {
		this.cql = cql;
	}
	/**
	 * @return the kkxq
	 */
	public String getKkxq() {
		return kkxq;
	}
	/**
	 * @param kkxqҪ���õ� kkxq
	 */
	public void setKkxq(String kkxq) {
		this.kkxq = kkxq;
	}
	/**
	 * @return the toyf
	 */
	public String getToyf() {
		return toyf;
	}
	/**
	 * @param toyfҪ���õ� toyf
	 */
	public void setToyf(String toyf) {
		this.toyf = toyf;
	}
	/**
	 * @return the tozc
	 */
	public String getTozc() {
		return tozc;
	}
	/**
	 * @param tozcҪ���õ� tozc
	 */
	public void setTozc(String tozc) {
		this.tozc = tozc;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArrҪ���õ� xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
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
	 * @return the bjcs
	 */
	public String getBjcs() {
		return bjcs;
	}
	/**
	 * @param bjcsҪ���õ� bjcs
	 */
	public void setBjcs(String bjcs) {
		this.bjcs = bjcs;
	}
	/**
	 * @return the sjcs
	 */
	public String getSjcs() {
		return sjcs;
	}
	/**
	 * @param sjcsҪ���õ� sjcs
	 */
	public void setSjcs(String sjcs) {
		this.sjcs = sjcs;
	}
	/**
	 * @return the kkjs
	 */
	public String getKkjs() {
		return kkjs;
	}
	/**
	 * @param kkjsҪ���õ� kkjs
	 */
	public void setKkjs(String kkjs) {
		this.kkjs = kkjs;
	}
	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @param zydmҪ���õ� zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydmҪ���õ� xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
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
	 * @return the yf
	 */
	public String getYf() {
		return yf;
	}
	/**
	 * @param yfҪ���õ� yf
	 */
	public void setYf(String yf) {
		this.yf = yf;
	}
	/**
	 * @return the zc
	 */
	public String getZc() {
		return zc;
	}
	/**
	 * @param zcҪ���õ� zc
	 */
	public void setZc(String zc) {
		this.zc = zc;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdmҪ���õ� bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the cqrs
	 */
	public String getCqrs() {
		return cqrs;
	}
	/**
	 * @param cqrsҪ���õ� cqrs
	 */
	public void setCqrs(String cqrs) {
		this.cqrs = cqrs;
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
	 * @return the jlr
	 */
	public String getJlr() {
		return jlr;
	}
	/**
	 * @param jlrҪ���õ� jlr
	 */
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	/**
	 * @return the jlsj
	 */
	public String getJlsj() {
		return jlsj;
	}
	/**
	 * @param jlsjҪ���õ� jlsj
	 */
	public void setJlsj(String jlsj) {
		this.jlsj = jlsj;
	}
	
}
