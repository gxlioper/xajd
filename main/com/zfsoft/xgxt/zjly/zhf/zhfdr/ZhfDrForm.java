/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-20 ����10:04:28 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfdr;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-6-20 ����10:04:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfDrForm extends ActionForm {
	 private String id;//ID
	 private String  xh;//ѧ��
	 private String  jfxmdm;//�Ʒ���Ŀ����
	 private String  xmmkdm;//��Ŀģ�����
	 private String  sxsm;//����˵��
	 private String  shsj;//���ʱ��
	 private String  shr;//�����
	 private String  cysj;//����/���ʱ��
	 private String  shzt;//���״̬
	 private String  lrsj;//¼��ʱ��
	 private String  lrr;//¼����
	 private String  fj;//����
	 private String  thyj;//�˻����
	 private FormFile drmb;//file
	 private static final long serialVersionUID = 1L;
	 private SearchModel searchModel = new SearchModel(); //�߼���ѯ����
	 private Pages pages = new Pages();//��ҳ��
	 private String type;//���ͣ�ǰ̨��ת�жϱ�־
	 private User user;//user,�������ݷ�Χ����
	 private String fjmc;//��������
	 private String filepath;//��Ŵ����ļ���·��
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
	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmcҪ���õ� fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
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
	 * @return the thyj
	 */
	public String getThyj() {
		return thyj;
	}
	/**
	 * @param thyjҪ���õ� thyj
	 */
	public void setThyj(String thyj) {
		this.thyj = thyj;
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
	 * @return the jfxmdm
	 */
	public String getJfxmdm() {
		return jfxmdm;
	}
	/**
	 * @param jfxmdmҪ���õ� jfxmdm
	 */
	public void setJfxmdm(String jfxmdm) {
		this.jfxmdm = jfxmdm;
	}
	/**
	 * @return the xmmkdm
	 */
	public String getXmmkdm() {
		return xmmkdm;
	}
	/**
	 * @param xmmkdmҪ���õ� xmmkdm
	 */
	public void setXmmkdm(String xmmkdm) {
		this.xmmkdm = xmmkdm;
	}
	/**
	 * @return the sxsm
	 */
	public String getSxsm() {
		return sxsm;
	}
	/**
	 * @param sxsmҪ���õ� sxsm
	 */
	public void setSxsm(String sxsm) {
		this.sxsm = sxsm;
	}
	/**
	 * @return the shsj
	 */
	public String getShsj() {
		return shsj;
	}
	/**
	 * @param shsjҪ���õ� shsj
	 */
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	/**
	 * @return the shr
	 */
	public String getShr() {
		return shr;
	}
	/**
	 * @param shrҪ���õ� shr
	 */
	public void setShr(String shr) {
		this.shr = shr;
	}
	/**
	 * @return the cysj
	 */
	public String getCysj() {
		return cysj;
	}
	/**
	 * @param cysjҪ���õ� cysj
	 */
	public void setCysj(String cysj) {
		this.cysj = cysj;
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
	 * @return the fj
	 */
	public String getFj() {
		return fj;
	}
	/**
	 * @param fjҪ���õ� fj
	 */
	public void setFj(String fj) {
		this.fj = fj;
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

}
