/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-9 ����11:18:20 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.pjxmsq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import java.io.File;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2013-12-9 ����11:18:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxmsqModel extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = -2636417102028485376L;
	
	private String id;		// ID
	private String sqid;	//����ID
	private String xn;		//ѧ��
	private String xh; 		//ѧ��
	private String xq;		//ѧ��
	private String xqmc;   //ѧ������
	private String sqsj; 	//����ʱ��
	private String zzhdjx;	//���ջ�ý���
	private String shzt;	//���״̬
	private String ylzd1;	// Ԥ���ֶ�һ
	private String ylzd2;	// Ԥ���ֶζ�
	private String ylzd3;	// Ԥ���ֶ���
	private String ylzd4;	// Ԥ���ֶ���
	private String ylzd5;	// ����id
	private String sjly;	// ������Դ
	private String lylcywid;// ��Դҵ��id
	private String sqly;	//��������
	private String sqr;		//������
	private String splc;
	private String tzhxmdm; //��������Ŀ����
	private String dqxmdm;	//��ǰ��Ŀ����

	private String xmdm; 	//��Ŀ����
	private String xzdm; 	//�����ʴ���
	private String xmlx; 	//��Ŀ����
	private String xmmc;	//��Ŀ����
	private String xmje;	//���
	private String sqsjd;	//����ʱ���
	
	private String lxdm;
	
	private String lxdmmc;
	private FormFile file;
	
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private String type;
	private String queryType; //��ѯ����  wsq δ���� ��   ysq ������
	private String djjl;//����ҽҩ
	
	//�й�����ѧԺ���Ի��޸�
	private String[] ids;
	private String sfysq;
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String getSfysq() {
		return sfysq;
	}
	public void setSfysq(String sfysq) {
		this.sfysq = sfysq;
	}
	public String getDjjl() {
		return djjl;
	}
	public void setDjjl(String djjl) {
		this.djjl = djjl;
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
	 * @return the queryType
	 */
	public String getQueryType() {
		return queryType;
	}
	/**
	 * @param queryTypeҪ���õ� queryType
	 */
	public void setQueryType(String queryType) {
		this.queryType = queryType;
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
	 * @return the zzhdjx
	 */
	public String getZzhdjx() {
		return zzhdjx;
	}
	/**
	 * @param zzhdjxҪ���õ� zzhdjx
	 */
	public void setZzhdjx(String zzhdjx) {
		this.zzhdjx = zzhdjx;
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
	public String getXzdm() {
		return xzdm;
	}
	public void setXzdm(String xzdm) {
		this.xzdm = xzdm;
	}
	/**
	 * @return the xmlx
	 */
	public String getXmlx() {
		return xmlx;
	}
	/**
	 * @param xmlxҪ���õ� xmlx
	 */
	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
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
	 * @return the xmje
	 */
	public String getXmje() {
		return xmje;
	}
	/**
	 * @param xmjeҪ���õ� xmje
	 */
	public void setXmje(String xmje) {
		this.xmje = xmje;
	}
	/**
	 * @return the sqsjd
	 */
	public String getSqsjd() {
		return sqsjd;
	}
	/**
	 * @param sqsjdҪ���õ� sqsjd
	 */
	public void setSqsjd(String sqsjd) {
		this.sqsjd = sqsjd;
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
	public String getDqxmdm() {
		return dqxmdm;
	}
	public void setDqxmdm(String dqxmdm) {
		this.dqxmdm = dqxmdm;
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
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1Ҫ���õ� ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @param ylzd2Ҫ���õ� ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3Ҫ���õ� ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @return the ylzd4
	 */
	public String getYlzd4() {
		return ylzd4;
	}
	/**
	 * @param ylzd4Ҫ���õ� ylzd4
	 */
	public void setYlzd4(String ylzd4) {
		this.ylzd4 = ylzd4;
	}
	/**
	 * @return the ylzd5
	 */
	public String getYlzd5() {
		return ylzd5;
	}
	/**
	 * @param ylzd5Ҫ���õ� ylzd5
	 */
	public void setYlzd5(String ylzd5) {
		this.ylzd5 = ylzd5;
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
	 * @return the lylcywid
	 */
	public String getLylcywid() {
		return lylcywid;
	}
	/**
	 * @param lylcywidҪ���õ� lylcywid
	 */
	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
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
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}
	/**
	 * @param lxdmҪ���õ� lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}
	/**
	 * @return the lxdmmc
	 */
	public String getLxdmmc() {
		return lxdmmc;
	}
	/**
	 * @param lxdmmcҪ���õ� lxdmmc
	 */
	public void setLxdmmc(String lxdmmc) {
		this.lxdmmc = lxdmmc;
	}
	
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
}
