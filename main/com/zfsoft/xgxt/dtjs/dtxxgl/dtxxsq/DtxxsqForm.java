/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:00:24 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ϣ����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-10-23 ����11:38:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class DtxxsqForm extends ActionForm {
	private static final long serialVersionUID = -7844335000460670544L;
	private String dtxxsqid;	
	private String xh;//ѧ��
	private String jddm;//�׶δ���
	private String sqsj;	//����ʱ��
	private String shzt;//���״̬
	private String splc;//��������
	private String zd1;//��չ�ֶ�
	private String zd2;//��չ�ֶ�
	private String zd3;//��չ�ֶ�
	private String zd4;//��չ�ֶ�
	private String zd5;//��չ�ֶ�
	private String jdmc;//�׶����� 
	private String grxj;//����С��
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel=new ExportModel();
	private String type;
	private String lcxx;
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	 * @return the jddm
	 */
	public String getJddm() {
		return jddm;
	}
	/**
	 * @param jddmҪ���õ� jddm
	 */
	public void setJddm(String jddm) {
		this.jddm = jddm;
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
	public String getZd2() {
		return zd2;
	}
	/**
	 * @param zd2Ҫ���õ� zd2
	 */
	public void setZd2(String zd2) {
		this.zd2 = zd2;
	}
	/**
	 * @return the zd3
	 */
	public String getZd3() {
		return zd3;
	}
	/**
	 * @param zd3Ҫ���õ� zd3
	 */
	public void setZd3(String zd3) {
		this.zd3 = zd3;
	}
	/**
	 * @return the zd4
	 */
	public String getZd4() {
		return zd4;
	}
	/**
	 * @param zd4Ҫ���õ� zd4
	 */
	public void setZd4(String zd4) {
		this.zd4 = zd4;
	}
	/**
	 * @return the zd5
	 */
	public String getZd5() {
		return zd5;
	}
	/**
	 * @param zd5Ҫ���õ� zd5
	 */
	public void setZd5(String zd5) {
		this.zd5 = zd5;
	}
	/**
	 * @return the jdmc
	 */
	public String getJdmc() {
		return jdmc;
	}
	/**
	 * @param jdmcҪ���õ� jdmc
	 */
	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
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
	 * @return the lcxx
	 */
	public String getLcxx() {
		return lcxx;
	}
	/**
	 * @param lcxxҪ���õ� lcxx
	 */
	public void setLcxx(String lcxx) {
		this.lcxx = lcxx;
	}
	/**
	 * @return the grxj
	 */
	public String getGrxj() {
		return grxj;
	}
	/**
	 * @param grxjҪ���õ� grxj
	 */
	public void setGrxj(String grxj) {
		this.grxj = grxj;
	}
	/**
	 * @return the dtxxsqid
	 */
	public String getDtxxsqid() {
		return dtxxsqid;
	}
	/**
	 * @param dtxxsqidҪ���õ� dtxxsqid
	 */
	public void setDtxxsqid(String dtxxsqid) {
		this.dtxxsqid = dtxxsqid;
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
}
