/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-18 ����02:35:46 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.sh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-18 ����02:35:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkShModel extends ActionForm {
	private String  sqid;
	private String  xh;
	private String  xn;
	private String  xq;
	private String  sqsj;
	private String  shzt;
	private String  splc;
	private String  jttg;
	private String  zxj;
	private String  jxj;
	private String  qgzxsr;
	private String  xnwxjk;
	private String  qtsr;
	private String  zxdksqje;
	private String  zxdksqsj;
	private String  ffje;
	private String  sqje;
	private String  sqly;
	private String  sqr;
	private String  ffsj;
	private String  type;
	private String  pzje;
	private String shid;
    private String shjg;
    private String shyj;
    private String gwid;
    private String thgw;
    private String shlc;
    private String zd1;//���ҵ���ֶ����ơ���׼��
    private String zd3;//���ҵ���ֶ����ݡ���׼��
    //���������
    private String[] id;
    private String[] gwids;
    private String[] xhs;
    private static final long serialVersionUID = 1L;
    private SearchModel searchModel = new SearchModel();
   //����
   private ExportModel exportModel = new ExportModel();
   private Pages pages = new Pages();
   
   //�������
   private FormFile formfile;
   private String filepath;
   
    /**
	 * @return the zd1
	 */
	public String getZd1() {
		return zd1;
	}
	/**
	 * @param zd1Ҫ���õ� zd1
	 */
	public void setZd1(String zd1) {
		this.zd1 = zd1;
	}
	/**
	 * @return the zd2
	 */
	public String getZd3() {
		return zd3;
	}
	/**
	 * @param zd2Ҫ���õ� zd2
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	
	 /**
	 * @return the pzje
	 */
	public String getPzje() {
		return pzje;
	}
	/**
	 * @param pzjeҪ���õ� pzje
	 */
	public void setPzje(String pzje) {
		this.pzje = pzje;
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
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}
	/**
	 * @return the jttg
	 */
	public String getJttg() {
		return jttg;
	}
	/**
	 * @param jttgҪ���õ� jttg
	 */
	public void setJttg(String jttg) {
		this.jttg = jttg;
	}
	/**
	 * @return the zxj
	 */
	public String getZxj() {
		return zxj;
	}
	/**
	 * @param zxjҪ���õ� zxj
	 */
	public void setZxj(String zxj) {
		this.zxj = zxj;
	}
	/**
	 * @return the jxj
	 */
	public String getJxj() {
		return jxj;
	}
	/**
	 * @param jxjҪ���õ� jxj
	 */
	public void setJxj(String jxj) {
		this.jxj = jxj;
	}
	/**
	 * @return the qgzxsr
	 */
	public String getQgzxsr() {
		return qgzxsr;
	}
	/**
	 * @param qgzxsrҪ���õ� qgzxsr
	 */
	public void setQgzxsr(String qgzxsr) {
		this.qgzxsr = qgzxsr;
	}
	/**
	 * @return the xnwxjk
	 */
	public String getXnwxjk() {
		return xnwxjk;
	}
	/**
	 * @param xnwxjkҪ���õ� xnwxjk
	 */
	public void setXnwxjk(String xnwxjk) {
		this.xnwxjk = xnwxjk;
	}
	/**
	 * @return the qtsr
	 */
	public String getQtsr() {
		return qtsr;
	}
	/**
	 * @param qtsrҪ���õ� qtsr
	 */
	public void setQtsr(String qtsr) {
		this.qtsr = qtsr;
	}
	/**
	 * @return the zxdksqje
	 */
	public String getZxdksqje() {
		return zxdksqje;
	}
	/**
	 * @param zxdksqjeҪ���õ� zxdksqje
	 */
	public void setZxdksqje(String zxdksqje) {
		this.zxdksqje = zxdksqje;
	}
	/**
	 * @return the ffje
	 */
	public String getFfje() {
		return ffje;
	}
	/**
	 * @param ffjeҪ���õ� ffje
	 */
	public void setFfje(String ffje) {
		this.ffje = ffje;
	}
	/**
	 * @return the sqje
	 */
	public String getSqje() {
		return sqje;
	}
	/**
	 * @param sqjeҪ���õ� sqje
	 */
	public void setSqje(String sqje) {
		this.sqje = sqje;
	}
	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqlyҪ���õ� sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * @param sqrҪ���õ� sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	/**
	 * @return the zxdksqsj
	 */
	public String getZxdksqsj() {
		return zxdksqsj;
	}
	/**
	 * @param zxdksqsjҪ���õ� zxdksqsj
	 */
	public void setZxdksqsj(String zxdksqsj) {
		this.zxdksqsj = zxdksqsj;
	}
	/**
	 * @return the ffsj
	 */
	public String getFfsj() {
		return ffsj;
	}
	/**
	 * @param ffsjҪ���õ� ffsj
	 */
	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FormFile getFormfile() {
		return formfile;
	}
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
