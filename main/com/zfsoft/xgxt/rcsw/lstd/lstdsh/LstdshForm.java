/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-25 ����09:38:24 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdsh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ɫͨ�����
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-25 ����09:38:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdshForm extends ActionForm {
	
	
	private static final long serialVersionUID = 2048640730113571410L;

	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();

	FormFile uploadFile;// �ϴ��ļ�

	private String yclx;//�쳣��������
	
	private String[] primarykey_checkVal;// CheckBox
	
	
	private String sqid;
	private String xh;
	private String sqsj;
	private String lxdm;
	private String sqly;
	private String shzt;
	private String splc;
	private String type;
	private String lxmc;
	private String shlx;
	
	private String ywid;
	private String shsj;
	private String shr;
	private String shyj;
	private String shlc;
	private String gwid;
	private String shztmc;
	private String shid;
	private String thgw;//��λ�˻�
	private String shjg;
	private String xn;
	private String xq;
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	
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
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public FormFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getYclx() {
		return yclx;
	}
	public void setYclx(String yclx) {
		this.yclx = yclx;
	}
	public String[] getPrimarykey_checkVal() {
		return primarykey_checkVal;
	}
	public void setPrimarykey_checkVal(String[] primarykeyCheckVal) {
		primarykey_checkVal = primarykeyCheckVal;
	}
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getLxdm() {
		return lxdm;
	}
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLxmc() {
		return lxmc;
	}
	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}
	public String getShlx() {
		return shlx;
	}
	public void setShlx(String shlx) {
		this.shlx = shlx;
	}
	public String getYwid() {
		return ywid;
	}
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	public String getShsj() {
		return shsj;
	}
	public void setShsj(String shsj) {
		this.shsj = shsj;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getShlc() {
		return shlc;
	}
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public String getShztmc() {
		return shztmc;
	}
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getThgw() {
		return thgw;
	}
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String[] getGwids() {
		return gwids;
	}
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}

}
