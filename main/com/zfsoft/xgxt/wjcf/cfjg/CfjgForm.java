/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-5 ����11:35:40 
 */  
package com.zfsoft.xgxt.wjcf.cfjg;


import org.apache.struts.upload.FormFile;

import xgxt.comm.CommForm;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.Pages;


import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-8-5 ����11:35:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfjgForm extends CommForm{
	private String cfid  ;
	private String cflbmc;
	private String cfyymc;
	private String cflbdm;
	private String cfyydm;
	private String cfsj  ;
	private String cfdqsj;	//���ֵ���ʱ��
	private String cfwh  ;
	private String wjsj  ;
	private String sbb   ;
	private String sbsj  ;
	private String wjssjg;
	private String bz    ;
	private String sfsc  ; 
	private String sssj  ;
	private String sswh  ;
	private String ssjg  ;
	private String cfggw ;
	private String ssyj  ;
	private String jcwh  ;
	private String jcsj  ;
	private String jcyj  ;
	private String fjmc  ;
	private String jcjg  ;
	private String zzwh  ;
	private String zzsj  ;
	private String zzyj  ;
	private String cfyj;
	private String cflsh;//������ˮ��
	private String sdlx;//�ʹ�����
	private String type;
	private String filepath;
	private String filepath2;
	private String filepath3;
	private String filepath4;
	private String nd;
	private User user;
	private FormFile fj;
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	public String getFilepath2() {
		return filepath2;
	}

	public void setFilepath2(String filepath2) {
		this.filepath2 = filepath2;
	}

	public String getFilepath3() {
		return filepath3;
	}

	public void setFilepath3(String filepath3) {
		this.filepath3 = filepath3;
	}

	public String getFilepath4() {
		return filepath4;
	}

	public void setFilepath4(String filepath4) {
		this.filepath4 = filepath4;
	}

	public String getSdlx() {
		return sdlx;
	}

	public void setSdlx(String sdlx) {
		this.sdlx = sdlx;
	}

	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCfggw() {
		return cfggw;
	}
	public void setCfggw(String cfggw) {
		this.cfggw = cfggw;
	}
	public String getCfid() {
		return cfid;
	}
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}
	public String getCflbmc() {
		return cflbmc;
	}
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	
	/**
	 * @return the cfdqsj
	 */
	public String getCfdqsj() {
		return cfdqsj;
	}
	/**
	 * @param cfdqsjҪ���õ� cfdqsj
	 */
	public void setCfdqsj(String cfdqsj) {
		this.cfdqsj = cfdqsj;
	}
	public String getCfwh() {
		return cfwh;
	}
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}
	public String getCfyymc() {
		return cfyymc;
	}
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	public String getJcjg() {
		return jcjg;
	}
	public void setJcjg(String jcjg) {
		this.jcjg = jcjg;
	}
	public String getJcsj() {
		return jcsj;
	}
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	public String getJcwh() {
		return jcwh;
	}
	public void setJcwh(String jcwh) {
		this.jcwh = jcwh;
	}
	public String getJcyj() {
		return jcyj;
	}
	public void setJcyj(String jcyj) {
		this.jcyj = jcyj;
	}
	public String getSbb() {
		return sbb;
	}
	public void setSbb(String sbb) {
		this.sbb = sbb;
	}
	public String getSbsj() {
		return sbsj;
	}
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	public String getSfsc() {
		return sfsc;
	}
	public void setSfsc(String sfsc) {
		this.sfsc = sfsc;
	}
	public String getSsjg() {
		return ssjg;
	}
	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
	}
	public String getSssj() {
		return sssj;
	}
	public void setSssj(String sssj) {
		this.sssj = sssj;
	}
	public String getSswh() {
		return sswh;
	}
	public void setSswh(String sswh) {
		this.sswh = sswh;
	}
	public String getSsyj() {
		return ssyj;
	}
	public void setSsyj(String ssyj) {
		this.ssyj = ssyj;
	}
	public String getWjsj() {
		return wjsj;
	}
	public void setWjsj(String wjsj) {
		this.wjsj = wjsj;
	}
	public String getWjssjg() {
		return wjssjg;
	}
	public void setWjssjg(String wjssjg) {
		this.wjssjg = wjssjg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCflbdm() {
		return cflbdm;
	}
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}
	public String getCfyydm() {
		return cfyydm;
	}
	public void setCfyydm(String cfyydm) {
		this.cfyydm = cfyydm;
	}
	public FormFile getFj() {
		return fj;
	}
	public void setFj(FormFile fj) {
		this.fj = fj;
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
	 * @return the zzwh
	 */
	public String getZzwh() {
		return zzwh;
	}
	/**
	 * @param zzwhҪ���õ� zzwh
	 */
	public void setZzwh(String zzwh) {
		this.zzwh = zzwh;
	}
	/**
	 * @return the zzsj
	 */
	public String getZzsj() {
		return zzsj;
	}
	/**
	 * @param zzsjҪ���õ� zzsj
	 */
	public void setZzsj(String zzsj) {
		this.zzsj = zzsj;
	}
	/**
	 * @return the zzyj
	 */
	public String getZzyj() {
		return zzyj;
	}
	/**
	 * @param zzyjҪ���õ� zzyj
	 */
	public void setZzyj(String zzyj) {
		this.zzyj = zzyj;
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
	 * @return the cfyj
	 */
	public String getCfyj() {
		return cfyj;
	}
	/**
	 * @param cfyjҪ���õ� cfyj
	 */
	public void setCfyj(String cfyj) {
		this.cfyj = cfyj;
	}
	/**
	 * @return the cflsh
	 */
	public String getCflsh() {
		return cflsh;
	}
	/**
	 * @param cflshҪ���õ� cflsh
	 */
	public void setCflsh(String cflsh) {
		this.cflsh = cflsh;
	}
	/**
	 * @return the nd
	 */
	public String getNd() {
		return nd;
	}
	/**
	 * @param ndҪ���õ� nd
	 */
	public void setNd(String nd) {
		this.nd = nd;
	}
	
	
	
	
	

}
