/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-19 ����10:03:16 
 */  
package com.zfsoft.xgxt.zjly.ylbx;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ҽ�Ʊ��չ���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-4-19 ����10:03:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YlbxForm extends ActionForm {
	  private String xn;
	  private String  id;
	  private String xm;
	  private String xb;
	  private String xymc;
	  private String xh;
	  private String sfzh;
	  private String csrq;
	  private String rxsj;
	  private String lxdh;
	  private String zlbh;
	  private String czxx;
	  private String czzjbh;
	  private String czkssj;
	  private String czzzsj;
	  private String knbs;
	  private String ganbs;
	  private String ycyy;
	  private String yyyybs;
	  private String cxblb;
	  private String shbz;
	  private String shyj;
	  private String type;
	  private FormFile drmb;//file
	  private User user;//user,�������ݷ�Χ����
	  private String filepath;//��Ŵ����ļ���·��
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
	private Pages pages = new Pages();
	  private SearchModel searchModel = new SearchModel();
		//����
	  private ExportModel exportModel = new ExportModel();
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
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xbҪ���õ� xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymcҪ���õ� xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
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
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}
	/**
	 * @param sfzhҪ���õ� sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	/**
	 * @return the csrq
	 */
	public String getCsrq() {
		return csrq;
	}
	/**
	 * @param csrqҪ���õ� csrq
	 */
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	/**
	 * @return the rxsj
	 */
	public String getRxsj() {
		return rxsj;
	}
	/**
	 * @param rxsjҪ���õ� rxsj
	 */
	public void setRxsj(String rxsj) {
		this.rxsj = rxsj;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdhҪ���õ� lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the zlbh
	 */
	public String getZlbh() {
		return zlbh;
	}
	/**
	 * @param zlbhҪ���õ� zlbh
	 */
	public void setZlbh(String zlbh) {
		this.zlbh = zlbh;
	}
	/**
	 * @return the czxx
	 */
	public String getCzxx() {
		return czxx;
	}
	/**
	 * @param czxxҪ���õ� czxx
	 */
	public void setCzxx(String czxx) {
		this.czxx = czxx;
	}
	/**
	 * @return the czzjbh
	 */
	public String getCzzjbh() {
		return czzjbh;
	}
	/**
	 * @param czzjbhҪ���õ� czzjbh
	 */
	public void setCzzjbh(String czzjbh) {
		this.czzjbh = czzjbh;
	}
	/**
	 * @return the czkssj
	 */
	public String getCzkssj() {
		return czkssj;
	}
	/**
	 * @param czkssjҪ���õ� czkssj
	 */
	public void setCzkssj(String czkssj) {
		this.czkssj = czkssj;
	}
	/**
	 * @return the czzzsj
	 */
	public String getCzzzsj() {
		return czzzsj;
	}
	/**
	 * @param czzzsjҪ���õ� czzzsj
	 */
	public void setCzzzsj(String czzzsj) {
		this.czzzsj = czzzsj;
	}
	/**
	 * @return the knbs
	 */
	public String getKnbs() {
		return knbs;
	}
	/**
	 * @param knbsҪ���õ� knbs
	 */
	public void setKnbs(String knbs) {
		this.knbs = knbs;
	}
	/**
	 * @return the ganbs
	 */
	public String getGanbs() {
		return ganbs;
	}
	/**
	 * @param ganbsҪ���õ� ganbs
	 */
	public void setGanbs(String ganbs) {
		this.ganbs = ganbs;
	}
	/**
	 * @return the ycyy
	 */
	public String getYcyy() {
		return ycyy;
	}
	/**
	 * @param ycyyҪ���õ� ycyy
	 */
	public void setYcyy(String ycyy) {
		this.ycyy = ycyy;
	}
	/**
	 * @return the yyyybs
	 */
	public String getYyyybs() {
		return yyyybs;
	}
	/**
	 * @param yyyybsҪ���õ� yyyybs
	 */
	public void setYyyybs(String yyyybs) {
		this.yyyybs = yyyybs;
	}
	/**
	 * @return the cxblb
	 */
	public String getCxblb() {
		return cxblb;
	}
	/**
	 * @param cxblbҪ���õ� cxblb
	 */
	public void setCxblb(String cxblb) {
		this.cxblb = cxblb;
	}
	/**
	 * @return the shbz
	 */
	public String getShbz() {
		return shbz;
	}
	/**
	 * @param shbzҪ���õ� shbz
	 */
	public void setShbz(String shbz) {
		this.shbz = shbz;
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
