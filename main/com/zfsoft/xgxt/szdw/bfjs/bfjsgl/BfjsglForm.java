/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-4-19 ����11:03:19 
 */  
package com.zfsoft.xgxt.szdw.bfjs.bfjsgl;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��罨�����model(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-4-19 ����11:03:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BfjsglForm extends ActionForm{
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = -8298860854783929190L;
	private String jcid;
	private String bjdm;
	private String bjmc;
	private String jcrq;
	private String id;
	private String xh;
	private String jclx;
	private String kqlx;
	private String xm;
	private String[] dqs;
	private String[] qjs;
	private String[] qqs;
	private String[] cds;
	private String[] zts;
	private String type;
	private String[] xhs;
	private String doType;
	private String[] dels;
	private List<HashMap<String,String>> zcqqList;
	private List<HashMap<String,String>> zdqqList;
	private List<HashMap<String,String>> skqqList;
	private List<HashMap<String,String>> wzxqqList;
	private List<HashMap<String,String>> zccqList;
	private List<HashMap<String,String>> zdcqList;
	private List<HashMap<String,String>> skcqList;
	private List<HashMap<String,String>> wzxcqList;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	/**
	 * @return the jcid
	 */
	public String getJcid() {
		return jcid;
	}
	/**
	 * @param jcidҪ���õ� jcid
	 */
	public void setJcid(String jcid) {
		this.jcid = jcid;
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
	 * @return the jcrq
	 */
	public String getJcrq() {
		return jcrq;
	}
	/**
	 * @param jcrqҪ���õ� jcrq
	 */
	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
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
	 * @return the jclx
	 */
	public String getJclx() {
		return jclx;
	}
	/**
	 * @param jclxҪ���õ� jclx
	 */
	public void setJclx(String jclx) {
		this.jclx = jclx;
	}
	/**
	 * @return the kqlx
	 */
	public String getKqlx() {
		return kqlx;
	}
	/**
	 * @param kqlxҪ���õ� kqlx
	 */
	public void setKqlx(String kqlx) {
		this.kqlx = kqlx;
	}
	/**
	 * @return the dqs
	 */
	public String[] getDqs() {
		return dqs;
	}
	/**
	 * @param dqsҪ���õ� dqs
	 */
	public void setDqs(String[] dqs) {
		this.dqs = dqs;
	}
	/**
	 * @return the qjs
	 */
	public String[] getQjs() {
		return qjs;
	}
	/**
	 * @param qjsҪ���õ� qjs
	 */
	public void setQjs(String[] qjs) {
		this.qjs = qjs;
	}
	/**
	 * @return the qqs
	 */
	public String[] getQqs() {
		return qqs;
	}
	/**
	 * @param qqsҪ���õ� qqs
	 */
	public void setQqs(String[] qqs) {
		this.qqs = qqs;
	}
	/**
	 * @return the cds
	 */
	public String[] getCds() {
		return cds;
	}
	/**
	 * @param cdsҪ���õ� cds
	 */
	public void setCds(String[] cds) {
		this.cds = cds;
	}
	/**
	 * @return the zts
	 */
	public String[] getZts() {
		return zts;
	}
	/**
	 * @param ztsҪ���õ� zts
	 */
	public void setZts(String[] zts) {
		this.zts = zts;
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
	/**
	 * @return the zcqqList
	 */
	public List<HashMap<String, String>> getZcqqList() {
		return zcqqList;
	}
	/**
	 * @param zcqqListҪ���õ� zcqqList
	 */
	public void setZcqqList(List<HashMap<String, String>> zcqqList) {
		this.zcqqList = zcqqList;
	}
	/**
	 * @return the zdqqList
	 */
	public List<HashMap<String, String>> getZdqqList() {
		return zdqqList;
	}
	/**
	 * @param zdqqListҪ���õ� zdqqList
	 */
	public void setZdqqList(List<HashMap<String, String>> zdqqList) {
		this.zdqqList = zdqqList;
	}
	/**
	 * @return the skqqList
	 */
	public List<HashMap<String, String>> getSkqqList() {
		return skqqList;
	}
	/**
	 * @param skqqListҪ���õ� skqqList
	 */
	public void setSkqqList(List<HashMap<String, String>> skqqList) {
		this.skqqList = skqqList;
	}
	/**
	 * @return the wzxqqList
	 */
	public List<HashMap<String, String>> getWzxqqList() {
		return wzxqqList;
	}
	/**
	 * @param wzxqqListҪ���õ� wzxqqList
	 */
	public void setWzxqqList(List<HashMap<String, String>> wzxqqList) {
		this.wzxqqList = wzxqqList;
	}
	/**
	 * @return the zccqList
	 */
	public List<HashMap<String, String>> getZccqList() {
		return zccqList;
	}
	/**
	 * @param zccqListҪ���õ� zccqList
	 */
	public void setZccqList(List<HashMap<String, String>> zccqList) {
		this.zccqList = zccqList;
	}
	/**
	 * @return the zdcqList
	 */
	public List<HashMap<String, String>> getZdcqList() {
		return zdcqList;
	}
	/**
	 * @param zdcqListҪ���õ� zdcqList
	 */
	public void setZdcqList(List<HashMap<String, String>> zdcqList) {
		this.zdcqList = zdcqList;
	}
	/**
	 * @return the skcqList
	 */
	public List<HashMap<String, String>> getSkcqList() {
		return skcqList;
	}
	/**
	 * @param skcqListҪ���õ� skcqList
	 */
	public void setSkcqList(List<HashMap<String, String>> skcqList) {
		this.skcqList = skcqList;
	}
	/**
	 * @return the wzxcqList
	 */
	public List<HashMap<String, String>> getWzxcqList() {
		return wzxcqList;
	}
	/**
	 * @param wzxcqListҪ���õ� wzxcqList
	 */
	public void setWzxcqList(List<HashMap<String, String>> wzxcqList) {
		this.wzxcqList = wzxcqList;
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
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the doType
	 */
	public String getDoType() {
		return doType;
	}
	/**
	 * @param doTypeҪ���õ� doType
	 */
	public void setDoType(String doType) {
		this.doType = doType;
	}
	/**
	 * @return the dels
	 */
	public String[] getDels() {
		return dels;
	}
	/**
	 * @param delsҪ���õ� dels
	 */
	public void setDels(String[] dels) {
		this.dels = dels;
	}

	
	
	
}
