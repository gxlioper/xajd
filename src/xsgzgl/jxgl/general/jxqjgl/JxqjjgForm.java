/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-6 ����09:52:35 
 */  
package xsgzgl.jxgl.general.jxqjgl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-6 ����09:52:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JxqjjgForm extends ActionForm {
	
	private String qjid;
	private String qjlx;
	private String qjts;
	private String xh;
	private String xn;
	private String xq;
	private String xqmc;
	private String qjsy;
	private String sqsj;
	private String qjkssj;
	private String qjjssj;
	private String jbr;
	private String bz;
	private String sjly;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	//�������
	private FormFile formfile;
	private String fjxx;
	/**
	 * @return the qjid
	 */
	public String getQjid() {
		return qjid;
	}
	/**
	 * @param qjidҪ���õ� qjid
	 */
	public void setQjid(String qjid) {
		this.qjid = qjid;
	}
	/**
	 * @return the qjlx
	 */
	public String getQjlx() {
		return qjlx;
	}
	/**
	 * @param qjlxҪ���õ� qjlx
	 */
	public void setQjlx(String qjlx) {
		this.qjlx = qjlx;
	}
	/**
	 * @return the qjts
	 */
	public String getQjts() {
		return qjts;
	}
	/**
	 * @param qjtsҪ���õ� qjts
	 */
	public void setQjts(String qjts) {
		this.qjts = qjts;
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
	 * @return the qjsy
	 */
	public String getQjsy() {
		return qjsy;
	}
	/**
	 * @param qjsyҪ���õ� qjsy
	 */
	public void setQjsy(String qjsy) {
		this.qjsy = qjsy;
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
	 * @return the qjkssj
	 */
	public String getQjkssj() {
		return qjkssj;
	}
	/**
	 * @param qjkssjҪ���õ� qjkssj
	 */
	public void setQjkssj(String qjkssj) {
		this.qjkssj = qjkssj;
	}
	/**
	 * @return the qjjssj
	 */
	public String getQjjssj() {
		return qjjssj;
	}
	/**
	 * @param qjjssjҪ���õ� qjjssj
	 */
	public void setQjjssj(String qjjssj) {
		this.qjjssj = qjjssj;
	}
	/**
	 * @return the jbr
	 */
	public String getJbr() {
		return jbr;
	}
	/**
	 * @param jbrҪ���õ� jbr
	 */
	public void setJbr(String jbr) {
		this.jbr = jbr;
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
	 * @return the formfile
	 */
	public FormFile getFormfile() {
		return formfile;
	}
	/**
	 * @param formfileҪ���õ� formfile
	 */
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}
	/**
	 * @return the fjxx
	 */
	public String getFjxx() {
		return fjxx;
	}
	/**
	 * @param fjxxҪ���õ� fjxx
	 */
	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
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
	

}
