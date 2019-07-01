/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-24 ����09:40:03 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmjg;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ���������ģ��
 * @�๦������: 
 * @���ߣ�����[����:1104]
 * @ʱ�䣺 2014-6-24 ����09:40:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TxhdXmjgForm extends ActionForm{
	
	private static final long serialVersionUID = 7187713079641588815L;
	
	private String sqid;
	private String guid;
	private String xh;
	private String xn;
	private String xq;
	private String xqmc;
	private String xmdm;
	private String xmmc;
	private String lbdm;//����
	private String hdsj;//�ʱ��
	private String hddd;//��ص�
	private String sqly;//��������
	private String sqsj;//����ʱ��
	private String sqr; //������
	private String sjly;//������Դ
	private String cbdw;//�а쵥λ
	private String fzrxm;//����������
	private String lxdh;//��ϵ�绰
	private String hdzt;//�����
	private String hdmdyy;//�Ŀ�ļ�����
	private String hdfa;//�����
	private String sqwz;//��������
	private String wzsysj;//����ʹ��ʱ��
	private String wzghsj;//���ʹ黹ʱ��
	private String xcfs;//������ʽ
	private String xgdd;//���ҵص�
	private String xckssj;//������ʼʱ��
	private String xcjssj;//��������ʱ��
	private String hbsl;//��������
	private String xcnr;//��������
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	//�������
	private FormFile formfile;
	private String filepath;
	
	private String hdkssj;	//���ʼʱ��
	private String hdjssj;	//�����ʱ��
	private String hdsm;	//�˵��
	private String syxf;	//����ѧ��
	
	
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @param guidҪ���õ� guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
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
	 * @return the hdsj
	 */
	public String getHdsj() {
		return hdsj;
	}
	/**
	 * @param hdsjҪ���õ� hdsj
	 */
	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}
	/**
	 * @return the hddd
	 */
	public String getHddd() {
		return hddd;
	}
	/**
	 * @param hdddҪ���õ� hddd
	 */
	public void setHddd(String hddd) {
		this.hddd = hddd;
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
	 * @return the formfile
	 */
	public FormFile getFormfile() {
		return formfile;
	}
	/**
	 * @param formfileҪ���õ� formfile
	 */
	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
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
	public String getHdkssj() {
		return hdkssj;
	}
	public void setHdkssj(String hdkssj) {
		this.hdkssj = hdkssj;
	}
	public String getHdjssj() {
		return hdjssj;
	}
	public void setHdjssj(String hdjssj) {
		this.hdjssj = hdjssj;
	}
	public String getLbdm() {
		return lbdm;
	}
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
	}
	public String getHdsm() {
		return hdsm;
	}
	public void setHdsm(String hdsm) {
		this.hdsm = hdsm;
	}
	public String getSyxf() {
		return syxf;
	}
	public void setSyxf(String syxf) {
		this.syxf = syxf;
	}
	/**
	 * @return the cbdw
	 */
	public String getCbdw() {
		return cbdw;
	}
	/**
	 * @param cbdwҪ���õ� cbdw
	 */
	public void setCbdw(String cbdw) {
		this.cbdw = cbdw;
	}
	/**
	 * @return the fzrxm
	 */
	public String getFzrxm() {
		return fzrxm;
	}
	/**
	 * @param fzrxmҪ���õ� fzrxm
	 */
	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
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
	 * @return the hdzt
	 */
	public String getHdzt() {
		return hdzt;
	}
	/**
	 * @param hdztҪ���õ� hdzt
	 */
	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}
	/**
	 * @return the hdmdyy
	 */
	public String getHdmdyy() {
		return hdmdyy;
	}
	/**
	 * @param hdmdyyҪ���õ� hdmdyy
	 */
	public void setHdmdyy(String hdmdyy) {
		this.hdmdyy = hdmdyy;
	}
	/**
	 * @return the hdfa
	 */
	public String getHdfa() {
		return hdfa;
	}
	/**
	 * @param hdfaҪ���õ� hdfa
	 */
	public void setHdfa(String hdfa) {
		this.hdfa = hdfa;
	}
	/**
	 * @return the sqwz
	 */
	public String getSqwz() {
		return sqwz;
	}
	/**
	 * @param sqwzҪ���õ� sqwz
	 */
	public void setSqwz(String sqwz) {
		this.sqwz = sqwz;
	}
	/**
	 * @return the wzsysj
	 */
	public String getWzsysj() {
		return wzsysj;
	}
	/**
	 * @param wzsysjҪ���õ� wzsysj
	 */
	public void setWzsysj(String wzsysj) {
		this.wzsysj = wzsysj;
	}
	/**
	 * @return the wzghsj
	 */
	public String getWzghsj() {
		return wzghsj;
	}
	/**
	 * @param wzghsjҪ���õ� wzghsj
	 */
	public void setWzghsj(String wzghsj) {
		this.wzghsj = wzghsj;
	}
	/**
	 * @return the xcfs
	 */
	public String getXcfs() {
		return xcfs;
	}
	/**
	 * @param xcfsҪ���õ� xcfs
	 */
	public void setXcfs(String xcfs) {
		this.xcfs = xcfs;
	}
	/**
	 * @return the xgdd
	 */
	public String getXgdd() {
		return xgdd;
	}
	/**
	 * @param xgddҪ���õ� xgdd
	 */
	public void setXgdd(String xgdd) {
		this.xgdd = xgdd;
	}
	/**
	 * @return the xckssj
	 */
	public String getXckssj() {
		return xckssj;
	}
	/**
	 * @param xckssjҪ���õ� xckssj
	 */
	public void setXckssj(String xckssj) {
		this.xckssj = xckssj;
	}
	/**
	 * @return the xcjssj
	 */
	public String getXcjssj() {
		return xcjssj;
	}
	/**
	 * @param xcjssjҪ���õ� xcjssj
	 */
	public void setXcjssj(String xcjssj) {
		this.xcjssj = xcjssj;
	}
	/**
	 * @return the hbsl
	 */
	public String getHbsl() {
		return hbsl;
	}
	/**
	 * @param hbslҪ���õ� hbsl
	 */
	public void setHbsl(String hbsl) {
		this.hbsl = hbsl;
	}
	/**
	 * @return the xcnr
	 */
	public String getXcnr() {
		return xcnr;
	}
	/**
	 * @param xcnrҪ���õ� xcnr
	 */
	public void setXcnr(String xcnr) {
		this.xcnr = xcnr;
	}
	
	

}
