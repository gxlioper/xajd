/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-29 ����04:18:40 
 */  
package com.zfsoft.xgxt.rcsw.gjgl.gjjg;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cp[����:1352]
 * @ʱ�䣺 2016-11-29 ����04:18:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GjjgForm extends ActionForm{
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private ExportModel exportModel = new ExportModel();
	private String path;
	private String gjxxid;
	private String xh;
	private String xm;
	private String qsh;
	private String zbffzr;
	private String sy;
	private String qjkssj;
	private String qjjssj;
	private String qjjc;
	private String sfgq;
	private String bgqsj;
    private String bz;
    private FormFile drmb;//file
    private User user;//user,�������ݷ�Χ����
	 private String filepath;//��Ŵ����ļ���·��
	 
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
	 * @return the gjxxid
	 */
	public String getGjxxid() {
		return gjxxid;
	}
	/**
	 * @param gjxxidҪ���õ� gjxxid
	 */
	public void setGjxxid(String gjxxid) {
		this.gjxxid = gjxxid;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param pathҪ���õ� path
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qshҪ���õ� qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @return the zbffzr
	 */
	public String getZbffzr() {
		return zbffzr;
	}
	/**
	 * @param zbffzrҪ���õ� zbffzr
	 */
	public void setZbffzr(String zbffzr) {
		this.zbffzr = zbffzr;
	}
	/**
	 * @return the sy
	 */
	public String getSy() {
		return sy;
	}
	/**
	 * @param syҪ���õ� sy
	 */
	public void setSy(String sy) {
		this.sy = sy;
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
	 * @return the qjjc
	 */
	public String getQjjc() {
		return qjjc;
	}
	/**
	 * @param qjjcҪ���õ� qjjc
	 */
	public void setQjjc(String qjjc) {
		this.qjjc = qjjc;
	}
	/**
	 * @return the sfgq
	 */
	public String getSfgq() {
		return sfgq;
	}
	/**
	 * @param sfgqҪ���õ� sfgq
	 */
	public void setSfgq(String sfgq) {
		this.sfgq = sfgq;
	}
	/**
	 * @return the bgqsj
	 */
	public String getBgqsj() {
		return bgqsj;
	}
	/**
	 * @param bgqsjҪ���õ� bgqsj
	 */
	public void setBgqsj(String bgqsj) {
		this.bgqsj = bgqsj;
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
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}
	/**
	 * @param drmbҪ���õ� drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param userҪ���õ� user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepathҪ���õ� filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
	
	
	
	
	
	

}
