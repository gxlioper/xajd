/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-16 ����10:00:59 
 */  
package com.zfsoft.xgxt.xsxx.bbzc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ�ڱ���ע�� ��ʦ����
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-16 ����10:00:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqbdzcForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	
	
	private String id; //��ʶ��
	
	private String xh;	//ѧ��
	
	private String xn;	//ѧ��
	
	private String xq; //ѧ��
	
	private String xqmc; //ѧ������
	
	private String zczt; //ע��״̬
	
	private String zcsj;//ע��ʱ��
	
	private String zcr;//ע����
	
	private String wbdyy;//δ����ԭ��
	private String yjbdsj;//Ԥ�Ʊ���ʱ��
	private String wbdlbdm;//δ����������

	private String plIds;
	private String rownum_t;
	private String rownum_wx;
	private String rownum_w;
	private String rownum_x;
	private String rownum_y;
	private String searchXn;
	private String searchXq;
	
	/**
	 * @return the yjbdsj
	 */
	public String getYjbdsj() {
		return yjbdsj;
	}

	/**
	 * @param yjbdsjҪ���õ� yjbdsj
	 */
	public void setYjbdsj(String yjbdsj) {
		this.yjbdsj = yjbdsj;
	}

	/**
	 * @return the wbdlbdm
	 */
	public String getWbdlbdm() {
		return wbdlbdm;
	}

	/**
	 * @param wbdlbdmҪ���õ� wbdlbdm
	 */
	public void setWbdlbdm(String wbdlbdm) {
		this.wbdlbdm = wbdlbdm;
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
	 * @return the zczt
	 */
	public String getZczt() {
		return zczt;
	}

	/**
	 * @param zcztҪ���õ� zczt
	 */
	public void setZczt(String zczt) {
		this.zczt = zczt;
	}

	/**
	 * @return the zcsj
	 */
	public String getZcsj() {
		return zcsj;
	}

	/**
	 * @param zcsjҪ���õ� zcsj
	 */
	public void setZcsj(String zcsj) {
		this.zcsj = zcsj;
	}

	/**
	 * @return the zcr
	 */
	public String getZcr() {
		return zcr;
	}

	/**
	 * @param zcrҪ���õ� zcr
	 */
	public void setZcr(String zcr) {
		this.zcr = zcr;
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
	 * @return the searchXq
	 */
	public String getSearchXq() {
		return searchXq;
	}

	/**
	 * @param searchXqҪ���õ� searchXq
	 */
	public void setSearchXq(String searchXq) {
		this.searchXq = searchXq;
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
	 * @return the plIds
	 */
	public String getPlIds() {
		return plIds;
	}

	/**
	 * @param plIdsҪ���õ� plIds
	 */
	public void setPlIds(String plIds) {
		this.plIds = plIds;
	}

	

	

	/**
	 * @return the rownum_w
	 */
	public String getRownum_w() {
		return rownum_w;
	}

	/**
	 * @param rownumWҪ���õ� rownum_w
	 */
	public void setRownum_w(String rownumW) {
		rownum_w = rownumW;
	}

	/**
	 * @return the rownum_y
	 */
	public String getRownum_y() {
		return rownum_y;
	}

	/**
	 * @param rownumYҪ���õ� rownum_y
	 */
	public void setRownum_y(String rownumY) {
		rownum_y = rownumY;
	}

	/**
	 * @return the rownum_t
	 */
	public String getRownum_t() {
		return rownum_t;
	}

	/**
	 * @param rownumTҪ���õ� rownum_t
	 */
	public void setRownum_t(String rownumT) {
		rownum_t = rownumT;
	}

	public String getWbdyy() {
		return wbdyy;
	}

	public void setWbdyy(String wbdyy) {
		this.wbdyy = wbdyy;
	}

	/**
	 * @param rownumXҪ���õ� rownum_x
	 */
	public void setRownum_x(String rownumX) {
		rownum_x = rownumX;
	}

	/**
	 * @return the rownum_x
	 */
	public String getRownum_x() {
		return rownum_x;
	}

	/**
	 * @param rownumWXҪ���õ� rownum_wx
	 */
	public void setRownum_wx(String rownumWX) {
		rownum_wx = rownumWX;
	}

	/**
	 * @return the rownum_wt
	 */
	public String getRownum_wx() {
		return rownum_wx;
	}
	


}
