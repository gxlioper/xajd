/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-20 ����11:38:01 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg;


import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ϰ���ڹ���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-20 ����11:38:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmsbjgForm extends ActionForm{
	
	private String jgid;
	private String xmdm;
	private String xn;
	private String xq;
	private String xmmc;
	private String xmjbdm;
	private String sbbmdm;
	private String sskmdm;//������Ŀ����
	private String kcyrs;//�ɲ�������
	private String xmkssj;
	private String xmsbsj;
	private String jcxf;
	private String sbr;
	private String lxdh;
	private String sfsljx;//�Ƿ��������0|��1|��
	private String sfrm;//�Ƿ�����
	private String xmms;//��Ŀ����
	private String dkfyj;//��/�۷�����
	private String cyyq;//����Ҫ��
	private String czr;//������
	private String sjly;//������Դ
	private String splc;
	private String sqkg;
	private String sqkssj;
	private String sqjssj;
	private String rskzjb;
	private String type;
	private String[] xhArr;
    private String[] ylzd1Arr;
    private String[] ylzd2Arr;
    private String[] ylzd3Arr;
    private String tjsj;
    private String xfrdjgzt;
    private String rdsqid;
    private String rdsplc;
    private String xfrdsqzt;
    private String csms;
    private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String xmcd;//��Ŀ����
	private String bkgs; //������
	
	
	/**
	 * @return the xmcd
	 */
	public String getXmcd() {
		return xmcd;
	}
	/**
	 * @param xmcdҪ���õ� xmcd
	 */
	public void setXmcd(String xmcd) {
		this.xmcd = xmcd;
	}
	/**
	 * @return the bkgs
	 */
	public String getBkgs() {
		return bkgs;
	}
	/**
	 * @param bkgsҪ���õ� bkgs
	 */
	public void setBkgs(String bkgs) {
		this.bkgs = bkgs;
	}
	/**
	 * @return the csms
	 */
	public String getCsms() {
		return csms;
	}
	/**
	 * @param csmsҪ���õ� csms
	 */
	public void setCsms(String csms) {
		this.csms = csms;
	}
	
	/**
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgidҪ���õ� jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
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
	 * @return the xmmc
	 */
	public String getXmmc() {
		return xmmc;
	}
	/**
	 * @param xmmcҪ���õ� xmmc
	 */
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	/**
	 * @return the xmjbdm
	 */
	public String getXmjbdm() {
		return xmjbdm;
	}
	/**
	 * @param xmjbdmҪ���õ� xmjbdm
	 */
	public void setXmjbdm(String xmjbdm) {
		this.xmjbdm = xmjbdm;
	}
	/**
	 * @return the sbbmdm
	 */
	public String getSbbmdm() {
		return sbbmdm;
	}
	/**
	 * @param sbbmdmҪ���õ� sbbmdm
	 */
	public void setSbbmdm(String sbbmdm) {
		this.sbbmdm = sbbmdm;
	}
	/**
	 * @return the sskmdm
	 */
	public String getSskmdm() {
		return sskmdm;
	}
	/**
	 * @param sskmdmҪ���õ� sskmdm
	 */
	public void setSskmdm(String sskmdm) {
		this.sskmdm = sskmdm;
	}
	/**
	 * @return the kcyrs
	 */
	public String getKcyrs() {
		return kcyrs;
	}
	/**
	 * @param kcyrsҪ���õ� kcyrs
	 */
	public void setKcyrs(String kcyrs) {
		this.kcyrs = kcyrs;
	}
	/**
	 * @return the xmkssj
	 */
	public String getXmkssj() {
		return xmkssj;
	}
	/**
	 * @param xmkssjҪ���õ� xmkssj
	 */
	public void setXmkssj(String xmkssj) {
		this.xmkssj = xmkssj;
	}
	/**
	 * @return the xmsbsj
	 */
	public String getXmsbsj() {
		return xmsbsj;
	}
	/**
	 * @param xmsbsjҪ���õ� xmsbsj
	 */
	public void setXmsbsj(String xmsbsj) {
		this.xmsbsj = xmsbsj;
	}
	/**
	 * @return the jcxf
	 */
	public String getJcxf() {
		return jcxf;
	}
	/**
	 * @param jcxfҪ���õ� jcxf
	 */
	public void setJcxf(String jcxf) {
		this.jcxf = jcxf;
	}
	/**
	 * @return the sbr
	 */
	public String getSbr() {
		return sbr;
	}
	/**
	 * @param sbrҪ���õ� sbr
	 */
	public void setSbr(String sbr) {
		this.sbr = sbr;
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
	 * @return the sfsljx
	 */
	public String getSfsljx() {
		return sfsljx;
	}
	/**
	 * @param sfsljxҪ���õ� sfsljx
	 */
	public void setSfsljx(String sfsljx) {
		this.sfsljx = sfsljx;
	}
	/**
	 * @return the xmms
	 */
	public String getXmms() {
		return xmms;
	}
	/**
	 * @param xmmsҪ���õ� xmms
	 */
	public void setXmms(String xmms) {
		this.xmms = xmms;
	}
	/**
	 * @return the dkfyj
	 */
	public String getDkfyj() {
		return dkfyj;
	}
	/**
	 * @param dkfyjҪ���õ� dkfyj
	 */
	public void setDkfyj(String dkfyj) {
		this.dkfyj = dkfyj;
	}
	/**
	 * @return the cyyq
	 */
	public String getCyyq() {
		return cyyq;
	}
	/**
	 * @param cyyqҪ���õ� cyyq
	 */
	public void setCyyq(String cyyq) {
		this.cyyq = cyyq;
	}
	/**
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}
	/**
	 * @param czrҪ���õ� czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkgҪ���õ� sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssjҪ���õ� sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssjҪ���õ� sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	/**
	 * @return the rskzjb
	 */
	public String getRskzjb() {
		return rskzjb;
	}
	/**
	 * @param rskzjbҪ���õ� rskzjb
	 */
	public void setRskzjb(String rskzjb) {
		this.rskzjb = rskzjb;
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
	 * @return the sfrm
	 */
	public String getSfrm() {
		return sfrm;
	}
	/**
	 * @param sfrmҪ���õ� sfrm
	 */
	public void setSfrm(String sfrm) {
		this.sfrm = sfrm;
	}
	/**
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArrҪ���õ� xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	/**
	 * @return the ylzd1Arr
	 */
	public String[] getYlzd1Arr() {
		return ylzd1Arr;
	}
	/**
	 * @param ylzd1ArrҪ���õ� ylzd1Arr
	 */
	public void setYlzd1Arr(String[] ylzd1Arr) {
		this.ylzd1Arr = ylzd1Arr;
	}
	/**
	 * @return the ylzd2Arr
	 */
	public String[] getYlzd2Arr() {
		return ylzd2Arr;
	}
	/**
	 * @param ylzd2ArrҪ���õ� ylzd2Arr
	 */
	public void setYlzd2Arr(String[] ylzd2Arr) {
		this.ylzd2Arr = ylzd2Arr;
	}
	/**
	 * @return the ylzd3Arr
	 */
	public String[] getYlzd3Arr() {
		return ylzd3Arr;
	}
	/**
	 * @param ylzd3ArrҪ���õ� ylzd3Arr
	 */
	public void setYlzd3Arr(String[] ylzd3Arr) {
		this.ylzd3Arr = ylzd3Arr;
	}
	/**
	 * @return the tjsj
	 */
	public String getTjsj() {
		return tjsj;
	}
	/**
	 * @param tjsjҪ���õ� tjsj
	 */
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	/**
	 * @return the xfrdjgzt
	 */
	public String getXfrdjgzt() {
		return xfrdjgzt;
	}
	/**
	 * @param xfrdjgztҪ���õ� xfrdjgzt
	 */
	public void setXfrdjgzt(String xfrdjgzt) {
		this.xfrdjgzt = xfrdjgzt;
	}
	/**
	 * @return the rdsqid
	 */
	public String getRdsqid() {
		return rdsqid;
	}
	/**
	 * @param rdsqidҪ���õ� rdsqid
	 */
	public void setRdsqid(String rdsqid) {
		this.rdsqid = rdsqid;
	}
	/**
	 * @return the rdsplc
	 */
	public String getRdsplc() {
		return rdsplc;
	}
	/**
	 * @param rdsplcҪ���õ� rdsplc
	 */
	public void setRdsplc(String rdsplc) {
		this.rdsplc = rdsplc;
	}
	/**
	 * @return the xfrdsqzt
	 */
	public String getXfrdsqzt() {
		return xfrdsqzt;
	}
	/**
	 * @param xfrdsqztҪ���õ� xfrdsqzt
	 */
	public void setXfrdsqzt(String xfrdsqzt) {
		this.xfrdsqzt = xfrdsqzt;
	}
	

}
