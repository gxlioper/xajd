/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-5-10 ����03:21:53 
 */  
package xsgzgl.gygl.rcjc.wmqsxsmd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-5-10 ����03:21:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WmqsxsmdForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	
	
	private String id;
	private String xh;
	private String xn;//ѧ��
	private String xq;//ѧ��

	private String sjly;//������Դ 1:ά������  2:
	private String yjfs;//Ժ������
	private String xjfs;//Ժ������
	private String bz;//��ע
	private String lrr;//¼����
	private String lrsj;//¼��ʱ��
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
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @return the yjfs
	 */
	public String getYjfs() {
		return yjfs;
	}
	/**
	 * @param yjfsҪ���õ� yjfs
	 */
	public void setYjfs(String yjfs) {
		this.yjfs = yjfs;
	}
	/**
	 * @return the xjfs
	 */
	public String getXjfs() {
		return xjfs;
	}
	/**
	 * @param xjfsҪ���õ� xjfs
	 */
	public void setXjfs(String xjfs) {
		this.xjfs = xjfs;
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
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrrҪ���õ� lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsjҪ���õ� lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	
}
