package com.zfsoft.xgxt.jskp.sbsh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ʵ����
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2017-7-7 ����02:04:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class JskpXmsbshForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String sqid;
	private String xh;
	private String sbsj;
	private String shzt;
	private String splcid;
	private String bz;
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
	private String type;
	private String fs;
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String ffgz;	//���ֹ���
	private String fjid;
	private String lxzt;
	private String xmid;
	private String sbly;
	private String hjsj;
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
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqidҪ���õ� sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the ywid
	 */
	public String getYwid() {
		return ywid;
	}
	/**
	 * @param ywidҪ���õ� ywid
	 */
	public void setYwid(String ywid) {
		this.ywid = ywid;
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
	 * @return the shlc
	 */
	public String getShlc() {
		return shlc;
	}
	/**
	 * @param shlcҪ���õ� shlc
	 */
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	/**
	 * @return the gwid
	 */
	public String getGwid() {
		return gwid;
	}
	/**
	 * @param gwidҪ���õ� gwid
	 */
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	/**
	 * @return the shztmc
	 */
	public String getShztmc() {
		return shztmc;
	}
	/**
	 * @param shztmcҪ���õ� shztmc
	 */
	public void setShztmc(String shztmc) {
		this.shztmc = shztmc;
	}
	/**
	 * @return the shid
	 */
	public String getShid() {
		return shid;
	}
	/**
	 * @param shidҪ���õ� shid
	 */
	public void setShid(String shid) {
		this.shid = shid;
	}
	/**
	 * @return the thgw
	 */
	public String getThgw() {
		return thgw;
	}
	/**
	 * @param thgwҪ���õ� thgw
	 */
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	/**
	 * @return the shjg
	 */
	public String getShjg() {
		return shjg;
	}
	/**
	 * @param shjgҪ���õ� shjg
	 */
	public void setShjg(String shjg) {
		this.shjg = shjg;
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
	 * @return the id
	 */
	public String[] getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String[] id) {
		this.id = id;
	}
	/**
	 * @return the gwids
	 */
	public String[] getGwids() {
		return gwids;
	}
	/**
	 * @param gwidsҪ���õ� gwids
	 */
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	/**
	 * @return the xhs
	 */
	public String[] getXhs() {
		return xhs;
	}
	/**
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
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
	 * @return the ffgz
	 */
	public String getFfgz() {
		return ffgz;
	}
	/**
	 * @param ffgzҪ���õ� ffgz
	 */
	public void setFfgz(String ffgz) {
		this.ffgz = ffgz;
	}
	/**
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}
	/**
	 * @param fjidҪ���õ� fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}
	/**
	 * @return the lxzt
	 */
	public String getLxzt() {
		return lxzt;
	}
	/**
	 * @param lxztҪ���õ� lxzt
	 */
	public void setLxzt(String lxzt) {
		this.lxzt = lxzt;
	}
	/**
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmidҪ���õ� xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the sbly
	 */
	public String getSbly() {
		return sbly;
	}
	/**
	 * @param sblyҪ���õ� sbly
	 */
	public void setSbly(String sbly) {
		this.sbly = sbly;
	}
	/**
	 * @return the hjsj
	 */
	public String getHjsj() {
		return hjsj;
	}
	/**
	 * @param hjsjҪ���õ� hjsj
	 */
	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}
	
	
	
	
	
	
	
	

}
