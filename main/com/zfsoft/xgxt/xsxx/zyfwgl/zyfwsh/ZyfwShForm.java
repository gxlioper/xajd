/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��10�� ����8:43:24 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը�������Form
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��10�� ����8:43:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwShForm extends ActionForm{
	
	private static final long serialVersionUID = -2717741659123386697L;
	// ��ҳ
	Pages pages = new Pages();
	// �߼���ѯ
	SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	
	private String fwid;	//����id
	private String xn;	//ѧ��
	private String xq;	//ѧ��
	private String xqmc;	//ѧ������
	private String xh;	//ѧ��
	private String fwdd;	//����ص�
	private String fwddssx;	//����ص�ʡ����
	private String fwkssj;	//����ʼʱ��
	private String fwjssj;	//�������ʱ��
	private String fwnr;	//��������
	private String fwxss;	//����Сʱ��
	private String jzr;	//��֤��
	private String splc;	//��������
	private String shzt;	//���״̬
	private String type;
	
	private String ywid;	//ҵ��id
	private String shsj;	//���ʱ��
	private String shr;	//�����
	private String shyj;	//������
	private String gwid;	//��λid
	private String shztmc;	//���״̬����
	private String shid;	//���id
	private String thgw;	//��λ�˻�
	private String shjg;	//��˽��
	private String[] fwids;	//����id���飬�����������
	private String[] gwids;	//��λid���飬�����������
	private String[] xhs;	//ѧ�����飬�����������
	
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
	 * @return the fwid
	 */
	public String getFwid() {
		return fwid;
	}
	/**
	 * @param fwidҪ���õ� fwid
	 */
	public void setFwid(String fwid) {
		this.fwid = fwid;
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
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
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
	 * @return the fwdd
	 */
	public String getFwdd() {
		return fwdd;
	}
	/**
	 * @param fwddҪ���õ� fwdd
	 */
	public void setFwdd(String fwdd) {
		this.fwdd = fwdd;
	}
	/**
	 * @return the fwddssx
	 */
	public String getFwddssx() {
		return fwddssx;
	}
	/**
	 * @param fwddssxҪ���õ� fwddssx
	 */
	public void setFwddssx(String fwddssx) {
		this.fwddssx = fwddssx;
	}
	/**
	 * @return the fwkssj
	 */
	public String getFwkssj() {
		return fwkssj;
	}
	/**
	 * @param fwkssjҪ���õ� fwkssj
	 */
	public void setFwkssj(String fwkssj) {
		this.fwkssj = fwkssj;
	}
	/**
	 * @return the fwjssj
	 */
	public String getFwjssj() {
		return fwjssj;
	}
	/**
	 * @param fwjssjҪ���õ� fwjssj
	 */
	public void setFwjssj(String fwjssj) {
		this.fwjssj = fwjssj;
	}
	/**
	 * @return the fwnr
	 */
	public String getFwnr() {
		return fwnr;
	}
	/**
	 * @param fwnrҪ���õ� fwnr
	 */
	public void setFwnr(String fwnr) {
		this.fwnr = fwnr;
	}
	/**
	 * @return the fwxss
	 */
	public String getFwxss() {
		return fwxss;
	}
	/**
	 * @param fwxssҪ���õ� fwxss
	 */
	public void setFwxss(String fwxss) {
		this.fwxss = fwxss;
	}
	/**
	 * @return the jzr
	 */
	public String getJzr() {
		return jzr;
	}
	/**
	 * @param jzrҪ���õ� jzr
	 */
	public void setJzr(String jzr) {
		this.jzr = jzr;
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
	 * @return the fwids
	 */
	public String[] getFwids() {
		return fwids;
	}
	/**
	 * @param fwidsҪ���õ� fwids
	 */
	public void setFwids(String[] fwids) {
		this.fwids = fwids;
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
	
	
}
