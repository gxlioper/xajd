/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-7 ����10:15:35 
 */  
package com.zfsoft.xgxt.rcsw.cdgl.sh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������:�����������form
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-3-7 ����10:15:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CdshForm extends ActionForm {

	
	
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = -3970845499392576864L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	/**
	 *  @���� ��type ��ѯ���� 
	 */
	private String type;
	
	/**
	 * @���� ��sqid ����id
	 */
	private String sqid;
	
	/**
	 * @���� ��cdid ����id
	 */
	private String cdid;
	
	/**
	 * @���� ��xh ѧ��
	 */
	private String xh;
	
	/**
	 * @���� ��bmlbdm ����������
	 */
	private String bmlbdm;
	
	/**
	 * @���� ��sqsj ����ʱ��
	 */
	private String sqsj;
	
	/**
	 * @���� ��sqsjdkssj ����ʱ��ο�ʼʱ��
	 */
	private String sqsjdkssj;
	
	/**
	 * @���� ��sqsjdjssj ����ʱ��ν���ʱ��
	 */
	private String sqsjdjssj;
	
	/**
	 * @���� ��sqly ��������
	 */
	private String sqly;
	
	private String cyrs;
	private String fzrxm;
	private String fzrlxfs;
	
	private String xfgfsyxy;
	
	/**
	 * @���� ��splcid ������id
	 */
	private String splcid;
	
	/**
	 * @���� ��shzt ����״̬
	 */
	private String shzt;
	
	/**
	 * @���� ��xtgwid ϵͳ��λid
	 */
	private String xtgwid;
	
	/**
	 * @���� ��shid ���id
	 */
	private String shid;
	
	/**
	 * @���� ��shyj ������
	 */
	private String shyj;
	
	/**
	 * @���� ��shjg ��˽��
	 */
	private String shjg;
	
	/**
	 * @���� ��thgw �˻ظ�λ
	 */
	private String thgw;
	
	private String[] id;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;


	/**
	 * @���� ���޲��������� 
	 */
	public CdshForm() {
		super();
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
	 * @return the xtgwid
	 */
	public String getXtgwid() {
		return xtgwid;
	}

	/**
	 * @param xtgwidҪ���õ� xtgwid
	 */
	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
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
	 * @return the cdid
	 */
	public String getCdid() {
		return cdid;
	}

	/**
	 * @param cdidҪ���õ� cdid
	 */
	public void setCdid(String cdid) {
		this.cdid = cdid;
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
	 * @return the bmlbdm
	 */
	public String getBmlbdm() {
		return bmlbdm;
	}

	/**
	 * @param bmlbdmҪ���õ� bmlbdm
	 */
	public void setBmlbdm(String bmlbdm) {
		this.bmlbdm = bmlbdm;
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
	 * @return the sqsjdkssj
	 */
	public String getSqsjdkssj() {
		return sqsjdkssj;
	}

	/**
	 * @param sqsjdkssjҪ���õ� sqsjdkssj
	 */
	public void setSqsjdkssj(String sqsjdkssj) {
		this.sqsjdkssj = sqsjdkssj;
	}

	/**
	 * @return the sqsjdjssj
	 */
	public String getSqsjdjssj() {
		return sqsjdjssj;
	}

	/**
	 * @param sqsjdjssjҪ���õ� sqsjdjssj
	 */
	public void setSqsjdjssj(String sqsjdjssj) {
		this.sqsjdjssj = sqsjdjssj;
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
	 * @return the cyrs
	 */
	public String getCyrs() {
		return cyrs;
	}

	/**
	 * @param cyrsҪ���õ� cyrs
	 */
	public void setCyrs(String cyrs) {
		this.cyrs = cyrs;
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
	 * @return the fzrlxfs
	 */
	public String getFzrlxfs() {
		return fzrlxfs;
	}

	/**
	 * @param fzrlxfsҪ���õ� fzrlxfs
	 */
	public void setFzrlxfs(String fzrlxfs) {
		this.fzrlxfs = fzrlxfs;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getGwids() {
		return gwids;
	}
	
	public String getXfgfsyxy() {
		return xfgfsyxy;
	}

	public void setXfgfsyxy(String xfgfsyxy) {
		this.xfgfsyxy = xfgfsyxy;
	}

	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}

	public String[] getXhs() {
		return xhs;
	}

	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}

	public String[] getSplcs() {
		return splcs;
	}

	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}

}
