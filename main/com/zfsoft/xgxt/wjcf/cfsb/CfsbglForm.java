/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-22 ����11:33:31 
 */  
package com.zfsoft.xgxt.wjcf.cfsb;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�ʹ��ֹ���ģ��
 * @�๦������: (Υ�ʹ����ϱ�����) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-22 ����11:33:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfsbglForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String xn;
	private String xq;
	private String xqmc;
	private String xh;
	private String cfyydm;
	private String cflbdm;
	private String cfyymc;
	private String cflbmc;
	private String wjsj;
	private FormFile fj;
	private String wjssjg;
	private String bz;
	private String cfyj; // ��������
	private String cfwh;
	private String splcid;            //��������id
	private String cfid;            //����Id
	private String sbb;            //�ϱ���
	private String sbbxm;            //�ϱ���
	private String sbsj;            //�ϱ�ʱ��
	private String sbjg;            //�ϱ����
	private String kzzd1;            //��չ�ֶ�1
	private String kzzd2;            //��չ�ֶ�2
	private String kzzd3;            //��չ�ֶ�3
	private String kzzd4;            //��չ�ֶ�4
	private String kzzd5;            //��չ�ֶ�5
	private String fjmc;            //��������	
	private String filepath;//����ѧ��������
	private String filepath2;//����Υ�ͼ�¼��
	private String filepath3;//�д�ֽ��
	private String filepath4;//�������¼

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

	/**
	 * @return the cfyymc
	 */
	public String getCfyymc() {
		return cfyymc;
	}
	/**
	 * @param cfyymcҪ���õ� cfyymc
	 */
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	/**
	 * @return the cflbmc
	 */
	public String getCflbmc() {
		return cflbmc;
	}
	/**
	 * @param cflbmcҪ���õ� cflbmc
	 */
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcidҪ���õ� splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the cfid
	 */
	public String getCfid() {
		return cfid;
	}
	/**
	 * @param cfidҪ���õ� cfid
	 */
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}
	/**
	 * @return the sbb
	 */
	public String getSbb() {
		return sbb;
	}
	/**
	 * @param sbbҪ���õ� sbb
	 */
	public void setSbb(String sbb) {
		this.sbb = sbb;
	}
	/**
	 * @return the sbbxm
	 */
	public String getSbbxm() {
		return sbbxm;
	}
	/**
	 * @param sbbxmҪ���õ� sbbxm
	 */
	public void setSbbxm(String sbbxm) {
		this.sbbxm = sbbxm;
	}
	/**
	 * @return the sbsj
	 */
	public String getSbsj() {
		return sbsj;
	}
	/**
	 * @param sbsjҪ���õ� sbsj
	 */
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	/**
	 * @return the sbjg
	 */
	public String getSbjg() {
		return sbjg;
	}
	/**
	 * @param sbjgҪ���õ� sbjg
	 */
	public void setSbjg(String sbjg) {
		this.sbjg = sbjg;
	}
	/**
	 * @return the kzzd1
	 */
	public String getKzzd1() {
		return kzzd1;
	}
	/**
	 * @param kzzd1Ҫ���õ� kzzd1
	 */
	public void setKzzd1(String kzzd1) {
		this.kzzd1 = kzzd1;
	}
	/**
	 * @return the kzzd2
	 */
	public String getKzzd2() {
		return kzzd2;
	}
	/**
	 * @param kzzd2Ҫ���õ� kzzd2
	 */
	public void setKzzd2(String kzzd2) {
		this.kzzd2 = kzzd2;
	}
	/**
	 * @return the kzzd3
	 */
	public String getKzzd3() {
		return kzzd3;
	}
	/**
	 * @param kzzd3Ҫ���õ� kzzd3
	 */
	public void setKzzd3(String kzzd3) {
		this.kzzd3 = kzzd3;
	}
	/**
	 * @return the kzzd4
	 */
	public String getKzzd4() {
		return kzzd4;
	}
	/**
	 * @param kzzd4Ҫ���õ� kzzd4
	 */
	public void setKzzd4(String kzzd4) {
		this.kzzd4 = kzzd4;
	}
	/**
	 * @return the kzzd5
	 */
	public String getKzzd5() {
		return kzzd5;
	}
	/**
	 * @param kzzd5Ҫ���õ� kzzd5
	 */
	public void setKzzd5(String kzzd5) {
		this.kzzd5 = kzzd5;
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
	 * @return the cfyydm
	 */
	public String getCfyydm() {
		return cfyydm;
	}
	/**
	 * @param cfyydmҪ���õ� cfyydm
	 */
	public void setCfyydm(String cfyydm) {
		this.cfyydm = cfyydm;
	}
	/**
	 * @return the cflbdm
	 */
	public String getCflbdm() {
		return cflbdm;
	}
	/**
	 * @param cflbdmҪ���õ� cflbdm
	 */
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}
	/**
	 * @return the wjsj
	 */
	public String getWjsj() {
		return wjsj;
	}
	/**
	 * @param wjsjҪ���õ� wjsj
	 */
	public void setWjsj(String wjsj) {
		this.wjsj = wjsj;
	}
	/**
	 * @return the fj
	 */
	public FormFile getFj() {
		return fj;
	}
	/**
	 * @param fjҪ���õ� fj
	 */
	public void setFj(FormFile fj) {
		this.fj = fj;
	}
	/**
	 * @return the wjssjg
	 */
	public String getWjssjg() {
		return wjssjg;
	}
	/**
	 * @param wjssjgҪ���õ� wjssjg
	 */
	public void setWjssjg(String wjssjg) {
		this.wjssjg = wjssjg;
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
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getXqmc() {
		return xqmc;
	}
	public String getCfwh() {
		return cfwh;
	}
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}
	
	
	
}
