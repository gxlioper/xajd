/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����05:02:37 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsb;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-9 ����05:02:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmsbForm extends ActionForm {
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
	private String xmms;//��Ŀ����
	private String dkfyj;//��/�۷�����
	private String cyyq;//����Ҫ��
	private String czr;//������
	private String splc;
	private String shzt;
	private String csms;//����ģʽ/��Ŀ���� 
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
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
	
	

}
