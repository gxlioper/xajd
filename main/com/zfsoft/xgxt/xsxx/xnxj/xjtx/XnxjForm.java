/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-19 ����01:43:58 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjtx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-19 ����01:43:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnxjForm extends ActionForm {

	
	
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	
	private String type;
	private String shQryType;
	private String kgzt;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String searchXn ; //����ѧ��
	
	private String id; //ҵ������
	
	private String xh;	//ѧ��
	
	private String xn;	//ѧ��
	
	private String xjnr; //С������
	
	private String txsj;	//��дʱ��
	
	private String shjg;  //��˽��
	
	private String splid;  //�����ID

	private String xtgwid; //��λid
	
	private String shid; //���id
	
	private String shyj;//������
	
	private String thgw; //�˻ظ�λid
	
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
	 * @return the xjnr
	 */
	public String getXjnr() {
		return xjnr;
	}

	/**
	 * @param xjnrҪ���õ� xjnr
	 */
	public void setXjnr(String xjnr) {
		this.xjnr = xjnr;
	}

	/**
	 * @return the txsj
	 */
	public String getTxsj() {
		return txsj;
	}

	/**
	 * @param txsjҪ���õ� txsj
	 */
	public void setTxsj(String txsj) {
		this.txsj = txsj;
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
	 * @return the splid
	 */
	public String getSplid() {
		return splid;
	}

	/**
	 * @param splidҪ���õ� splid
	 */
	public void setSplid(String splid) {
		this.splid = splid;
	}

	/**
	 * @return the searchXn
	 */
	public String getSearchXn() {
		return searchXn;
	}

	/**
	 * @param searchXnҪ���õ� searchXn
	 */
	public void setSearchXn(String searchXn) {
		this.searchXn = searchXn;
	}

	/**
	 * @return the kgzt
	 */
	public String getKgzt() {
		return kgzt;
	}

	/**
	 * @param kgztҪ���õ� kgzt
	 */
	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}

	/**
	 * @return the shQryType
	 */
	public String getShQryType() {
		return shQryType;
	}

	/**
	 * @param shQryTypeҪ���õ� shQryType
	 */
	public void setShQryType(String shQryType) {
		this.shQryType = shQryType;
	}

	/**
	 * @return the xtgwid
	 */
	public String getXtgwid() {
		return xtgwid;
	}

	/**
	 * @param xtgwidҪ���õ� xtgwid
	 */
	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
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


	
	
}
