/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-26 ����01:45:53 
 */  
package xsgzgl.xsxx.general.xszpgl;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xucy[����:754]
 * @ʱ�䣺 2013-9-26 ����01:45:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XszpglForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	FormFile file;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String xh;
	private String ksh;
	private String sfzh;
	private String xm;
	private String xb;
	private String xbmc;//�Ա�����
	private String nj;//
	private String xydm;//
	private String xymc;//
	private String zydm;//
	private String zymc;//
	private String bjdm;//
	private String bjmc;//
	private String sfdr;
	private String photoNameType;//ѡ������
	private String zpType;//��������Ƭ����
	
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXbmc() {
		return xbmc;
	}
	public void setXbmc(String xbmc) {
		this.xbmc = xbmc;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getSfdr() {
		return sfdr;
	}
	public void setSfdr(String sfdr) {
		this.sfdr = sfdr;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public String getKsh() {
		return ksh;
	}
	public void setKsh(String ksh) {
		this.ksh = ksh;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getPhotoNameType() {
		return photoNameType;
	}
	public void setPhotoNameType(String photoNameType) {
		this.photoNameType = photoNameType;
	}
	/**
	 * @return the zpType
	 */
	public String getZpType() {
		return zpType;
	}
	/**
	 * @param zpTypeҪ���õ� zpType
	 */
	public void setZpType(String zpType) {
		this.zpType = zpType;
	}
	
	
}
