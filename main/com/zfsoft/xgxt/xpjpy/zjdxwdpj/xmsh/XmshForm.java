/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-5-22 ����06:02:14 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������
 * @�๦������: ��������-�ҵ�����-�������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-5-22 ����05:42:24 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmshForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();/*��ҳ*/
	private SearchModel searchModel = new SearchModel();/*�߼���ѯ*/
	private ExportModel exportModel = new ExportModel();/*����*/
	private String type;/*����*/
	private String shzt;/*���״̬*/
	private String id;/*����ID*/
	private String shyj;/*������*/
	private String xyXmdm;/*ѧԺ������Ŀ����*/
	private String xmdm;/*��Ŀ����*/
	private String dqxmdm; /*��ǰ��Ŀ����*/
	private String pdjx;// ��������
	private String shid;// ��˸�λID
	private String shsj;// ���ʱ��
	private String shr;// �����
	private String tjdw;// ͳ�Ƶ�λ
	private String bmdm;// ���Ŵ���
	private String thgw;// �˻ظ�λ
	private String shjg;// ��˽��
	private String gwid;// ��λid
	private String tzhxmdm;// ��������Ŀ����
	private String bjdms;// �༶����
	private String xmdms;// ��Ŀ���뼯
	private String splc;// ��������
	private String xn;// ѧ��
	private String xh;// ѧ��
	private String fjxx;/*����id*/
	private String sqsj;/*����ʱ��*/
	private String sqly;/*��������*/
	private String sqr;// ������
	private String crsj;/*����ʱ��*/
	
	private String wysp;/*����ˮƽ*/
	private String ssdh;/*����绰*/
	private String gzzw;/*������Ṥ��ְ��*/
	private String cjkyqk;/*�μӿ������*/
	private String dwrs;/*���轱��λ����ʶ*/
	private String grxxjl;	//����ѧϰ����
	
	/**
	 * @return the crsj
	 */
	public String getCrsj() {
		return crsj;
	}
	/**
	 * @param crsjҪ���õ� crsj
	 */
	public void setCrsj(String crsj) {
		this.crsj = crsj;
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
	 * @return the fjxx
	 */
	public String getFjxx() {
		return fjxx;
	}
	/**
	 * @param fjxxҪ���õ� fjxx
	 */
	public void setFjxx(String fjxx) {
		this.fjxx = fjxx;
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
	 * @return the tjdw
	 */
	public String getTjdw() {
		return tjdw;
	}
	/**
	 * @param tjdwҪ���õ� tjdw
	 */
	public void setTjdw(String tjdw) {
		this.tjdw = tjdw;
	}
	/**
	 * @return the bmdm
	 */
	public String getBmdm() {
		return bmdm;
	}
	/**
	 * @param bmdmҪ���õ� bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
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
	 * @return the tzhxmdm
	 */
	public String getTzhxmdm() {
		return tzhxmdm;
	}
	/**
	 * @param tzhxmdmҪ���õ� tzhxmdm
	 */
	public void setTzhxmdm(String tzhxmdm) {
		this.tzhxmdm = tzhxmdm;
	}
	/**
	 * @return the bjdms
	 */
	public String getBjdms() {
		return bjdms;
	}
	/**
	 * @param bjdmsҪ���õ� bjdms
	 */
	public void setBjdms(String bjdms) {
		this.bjdms = bjdms;
	}
	/**
	 * @return the xmdms
	 */
	public String getXmdms() {
		return xmdms;
	}
	/**
	 * @param xmdmsҪ���õ� xmdms
	 */
	public void setXmdms(String xmdms) {
		this.xmdms = xmdms;
	}
	/**
	 * @return the pdjx
	 */
	public String getPdjx() {
		return pdjx;
	}
	/**
	 * @param pdjxҪ���õ� pdjx
	 */
	public void setPdjx(String pdjx) {
		this.pdjx = pdjx;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdmҪ���õ� xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	/**
	 * @return the dqxmdm
	 */
	public String getDqxmdm() {
		return dqxmdm;
	}
	/**
	 * @param dqxmdmҪ���õ� dqxmdm
	 */
	public void setDqxmdm(String dqxmdm) {
		this.dqxmdm = dqxmdm;
	}
	/**
	 * @return the xyXmdm
	 */
	public String getXyXmdm() {
		return xyXmdm;
	}
	/**
	 * @param xyXmdmҪ���õ� xyXmdm
	 */
	public void setXyXmdm(String xyXmdm) {
		this.xyXmdm = xyXmdm;
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
	 * @return the wysp
	 */
	public String getWysp() {
		return wysp;
	}
	/**
	 * @param wyspҪ���õ� wysp
	 */
	public void setWysp(String wysp) {
		this.wysp = wysp;
	}
	/**
	 * @return the ssdh
	 */
	public String getSsdh() {
		return ssdh;
	}
	/**
	 * @param ssdhҪ���õ� ssdh
	 */
	public void setSsdh(String ssdh) {
		this.ssdh = ssdh;
	}
	/**
	 * @return the gzzw
	 */
	public String getGzzw() {
		return gzzw;
	}
	/**
	 * @param gzzwҪ���õ� gzzw
	 */
	public void setGzzw(String gzzw) {
		this.gzzw = gzzw;
	}
	/**
	 * @return the cjkyqk
	 */
	public String getCjkyqk() {
		return cjkyqk;
	}
	/**
	 * @param cjkyqkҪ���õ� cjkyqk
	 */
	public void setCjkyqk(String cjkyqk) {
		this.cjkyqk = cjkyqk;
	}
	/**
	 * @return the dwrs
	 */
	public String getDwrs() {
		return dwrs;
	}
	/**
	 * @param dwrsҪ���õ� dwrs
	 */
	public void setDwrs(String dwrs) {
		this.dwrs = dwrs;
	}

	public String getGrxxjl() {
		return grxxjl;
	}

	public void setGrxxjl(String grxxjl) {
		this.grxxjl = grxxjl;
	}
}
