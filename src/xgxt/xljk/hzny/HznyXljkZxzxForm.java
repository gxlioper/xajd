package xgxt.xljk.hzny;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: qlj
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-4 ����04:34:55
 * </p>
 */
public class HznyXljkZxzxForm extends ActionForm {

	User user = new User();

	SearchModel searchModel = new SearchModel();

	Pages pages = new Pages();

	private String zxsbh; // ��ѯʦ���

	private String bz; // ��ע

	private String zgh; // ְ����

	private String jj; // ���

	private String zxszg; // ��ѯʦ�ʸ�

	private String tbgxcs; //�ر���Ĵ�ʩ

	private String xsdqzk; //ѧ����ǰ���

	private String xyclgc; //ѧԺ�������

	private String xh; //ѧ��

	private String tbgxxslb; //�ر����ѧ�����

	private String xswjbx; //

	private String sfzy; //

	private String zxsj; //

	private String guid; //

	private String zxwt; //

	private String hfsj; //

	private String hfnr; //
	
	private String zxnrjyj;     //
	
	private String fksj;     //
	
	private String zxsfk;     //
	
	private String zxnr;     //
	
	private String zxshf;     //
	
	private String sbr;
	
	private String sbsj;
	
	private String sbrlxdh;

	public String getSbr() {
		return sbr;
	}

	public void setSbr(String sbr) {
		this.sbr = sbr;
	}

	public String getSbrlxdh() {
		return sbrlxdh;
	}

	public void setSbrlxdh(String sbrlxdh) {
		this.sbrlxdh = sbrlxdh;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getJj() {
		return jj;
	}

	public void setJj(String jj) {
		this.jj = jj;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getZxsbh() {
		return zxsbh;
	}

	public void setZxsbh(String zxsbh) {
		this.zxsbh = zxsbh;
	}

	public String getZxszg() {
		return zxszg;
	}

	public void setZxszg(String zxszg) {
		this.zxszg = zxszg;
	}

	public String getSfzy() {
		return sfzy;
	}

	public void setSfzy(String sfzy) {
		this.sfzy = sfzy;
	}

	public String getTbgxcs() {
		return tbgxcs;
	}

	public void setTbgxcs(String tbgxcs) {
		this.tbgxcs = tbgxcs;
	}

	public String getTbgxxslb() {
		return tbgxxslb;
	}

	public void setTbgxxslb(String tbgxxslb) {
		this.tbgxxslb = tbgxxslb;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXswjbx() {
		return xswjbx;
	}

	public void setXswjbx(String xswjbx) {
		this.xswjbx = xswjbx;
	}

	public String getXyclgc() {
		return xyclgc;
	}

	public void setXyclgc(String xyclgc) {
		this.xyclgc = xyclgc;
	}

	public String getXsdqzk() {
		return xsdqzk;
	}

	public void setXsdqzk(String xsdqzk) {
		this.xsdqzk = xsdqzk;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getHfnr() {
		return hfnr;
	}

	public void setHfnr(String hfnr) {
		this.hfnr = hfnr;
	}

	public String getHfsj() {
		return hfsj;
	}

	public void setHfsj(String hfsj) {
		this.hfsj = hfsj;
	}

	public String getZxsj() {
		return zxsj;
	}

	public void setZxsj(String zxsj) {
		this.zxsj = zxsj;
	}

	public String getZxwt() {
		return zxwt;
	}

	public void setZxwt(String zxwt) {
		this.zxwt = zxwt;
	}

	public String getFksj() {
		return fksj;
	}

	public void setFksj(String fksj) {
		this.fksj = fksj;
	}

	public String getZxnrjyj() {
		return zxnrjyj;
	}

	public void setZxnrjyj(String zxnrjyj) {
		this.zxnrjyj = zxnrjyj;
	}

	public String getZxsfk() {
		return zxsfk;
	}

	public void setZxsfk(String zxsfk) {
		this.zxsfk = zxsfk;
	}

	public String getZxnr() {
		return zxnr;
	}

	public void setZxnr(String zxnr) {
		this.zxnr = zxnr;
	}

	public String getZxshf() {
		return zxshf;
	}

	public void setZxshf(String zxshf) {
		this.zxshf = zxshf;
	}
}
