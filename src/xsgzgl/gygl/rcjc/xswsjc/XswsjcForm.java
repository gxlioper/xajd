package xsgzgl.gygl.rcjc.xswsjc;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @ϵͳ���ƣ�ѧ����������ϵͳ
 * @ģ�����ƣ��ڹ���ѧ-��������
 * @�๦��������
 * @���ߣ�׿��[����:1391]
 * @ʱ�䣺2017��5��11��
 * @�汾��V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XswsjcForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel(); //�߼���ѯ
	private Pages pages = new Pages(); // ��ҳ
	private ExportModel exportModel = new ExportModel(); //�Զ��嵼��
	
	private String jcrcid; //����ճ�ID
	private String xn;
	private String xq;
	private String xh;
	private String fs; //����
	private String djbz;
	
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
	 * @return the jcrcid
	 */
	public String getJcrcid() {
		return jcrcid;
	}
	/**
	 * @param jcrcidҪ���õ� jcrcid
	 */
	public void setJcrcid(String jcrcid) {
		this.jcrcid = jcrcid;
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
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fsҪ���õ� fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}
	/**
	 * @return the djbz
	 */
	public String getDjbz() {
		return djbz;
	}
	/**
	 * @param djbzҪ���õ� djbz
	 */
	public void setDjbz(String djbz) {
		this.djbz = djbz;
	}
	
	
	
}
