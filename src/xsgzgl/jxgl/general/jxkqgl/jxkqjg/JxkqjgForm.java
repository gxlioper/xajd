/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-7 ����07:29:57 
 */  
package xsgzgl.jxgl.general.jxkqgl.jxkqjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-7 ����07:29:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JxkqjgForm extends ActionForm {
	private String kqid;
	private String xh;
	private String xn;
	private String xq;
	private String xqmc;
	private String jxid;
	private String jxmc;
	private String kqlb;
	private String kqlbmc;
	private String kqlxmc;
	private String kqlx;
	private String kqsj;
	private String sbsj;
	private String kqqk;
	private String sbr;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the kqid
	 */
	public String getKqid() {
		return kqid;
	}
	/**
	 * @param kqidҪ���õ� kqid
	 */
	public void setKqid(String kqid) {
		this.kqid = kqid;
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
	 * @return the jxid
	 */
	public String getJxid() {
		return jxid;
	}
	/**
	 * @param jxidҪ���õ� jxid
	 */
	public void setJxid(String jxid) {
		this.jxid = jxid;
	}
	/**
	 * @return the kqlb
	 */
	public String getKqlb() {
		return kqlb;
	}
	/**
	 * @param kqlbҪ���õ� kqlb
	 */
	public void setKqlb(String kqlb) {
		this.kqlb = kqlb;
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
	 * @return the kqsj
	 */
	public String getKqsj() {
		return kqsj;
	}
	/**
	 * @param kqsjҪ���õ� kqsj
	 */
	public void setKqsj(String kqsj) {
		this.kqsj = kqsj;
	}
	/**
	 * @return the sbsj
	 */
	public String getSbsj() {
		return sbsj;
	}
	/**
	 * @param sbsjҪ���õ� sbsj
	 */
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	/**
	 * @return the sbr
	 */
	public String getSbr() {
		return sbr;
	}
	/**
	 * @param sbrҪ���õ� sbr
	 */
	public void setSbr(String sbr) {
		this.sbr = sbr;
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
	 * @return the kqqk
	 */
	public String getKqqk() {
		return kqqk;
	}
	/**
	 * @param kqqkҪ���õ� kqqk
	 */
	public void setKqqk(String kqqk) {
		this.kqqk = kqqk;
	}
	/**
	 * @return the jxmc
	 */
	public String getJxmc() {
		return jxmc;
	}
	/**
	 * @param jxmcҪ���õ� jxmc
	 */
	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}
	/**
	 * @return the kqlbmc
	 */
	public String getKqlbmc() {
		return kqlbmc;
	}
	/**
	 * @param kqlbmcҪ���õ� kqlbmc
	 */
	public void setKqlbmc(String kqlbmc) {
		this.kqlbmc = kqlbmc;
	}
	/**
	 * @return the kqlxmc
	 */
	public String getKqlxmc() {
		return kqlxmc;
	}
	/**
	 * @param kqlxmcҪ���õ� kqlxmc
	 */
	public void setKqlxmc(String kqlxmc) {
		this.kqlxmc = kqlxmc;
	}
	

}
