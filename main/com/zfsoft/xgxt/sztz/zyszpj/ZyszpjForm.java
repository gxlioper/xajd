/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-6 ����04:59:16 
 */
package com.zfsoft.xgxt.sztz.zyszpj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ְҵ��������
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�· [���ţ�982]
 * @ʱ�䣺 2013-6-6 ����04:59:16
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZyszpjForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String zyszid;//ְҵ��������id
	private String xh;//ѧ��
	private String zpxx;//������Ϣ
	private String hpxx;//������Ϣ
	private String hpr;//������
	private String hprid;//����id
	private String spxx;//ʦ����Ϣ
	private String spr;//ʦ����
	private String sprid;//ʦ��id
	private String txsj;//��дʱ��
	private String xn;//ѧ��
	private String xq;//ѧ��
	private String xqmc;//ѧ������
	//-------------------------------------
	private String xmlbid;
	private String mc;
	private String xm;//����
	private String pjdj;//���۵ȼ�
	//---------------------------------
	private List<ZxmForm> zxm;//����Ŀ��Ϣ
	private List<Map<String, String>>  zxmMap;//����Ŀ��Ϣ��������ϸ��Ϣ�ϲ���
	private HashMap<String, String> xsxx;//ѧ����Ϣ
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String dqqx;//��ǰȨ��
	private String bjdm;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZpxx() {
		return zpxx;
	}

	public void setZpxx(String zpxx) {
		this.zpxx = zpxx;
	}

	public String getHpxx() {
		return hpxx;
	}

	public void setHpxx(String hpxx) {
		this.hpxx = hpxx;
	}

	public String getHpr() {
		return hpr;
	}

	public void setHpr(String hpr) {
		this.hpr = hpr;
	}

	public String getHprid() {
		return hprid;
	}

	public void setHprid(String hprId) {
		this.hprid = hprId;
	}

	public String getSpxx() {
		return spxx;
	}

	public void setSpxx(String spxx) {
		this.spxx = spxx;
	}

	public String getSpr() {
		return spr;
	}

	public void setSpr(String spr) {
		this.spr = spr;
	}
	public String getTxsj() {
		return txsj;
	}

	public void setTxsj(String txsj) {
		this.txsj = txsj;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}


	public List<ZxmForm> getZxm() {
		return zxm;
	}

	public void setZxm(List<ZxmForm> zxm) {
		this.zxm = zxm;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getZyszid() {
		return zyszid;
	}

	public void setZyszid(String zyszid) {
		this.zyszid = zyszid;
	}

	public String getSprid() {
		return sprid;
	}

	public void setSprid(String sprid) {
		this.sprid = sprid;
	}

	public String getXmlbid() {
		return xmlbid;
	}

	public void setXmlbid(String xmlbid) {
		this.xmlbid = xmlbid;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public HashMap<String, String> getXsxx() {
		return xsxx;
	}

	public void setXsxx(HashMap<String, String> xsxx) {
		this.xsxx = xsxx;
	}

	public String getPjdj() {
		return pjdj;
	}

	public void setPjdj(String pjdj) {
		this.pjdj = pjdj;
	}

	public List<Map<String, String>> getZxmMap() {
		return zxmMap;
	}

	public void setZxmMap(List<Map<String, String>> zxmMap) {
		this.zxmMap = zxmMap;
	}

	public String getDqqx() {
		return dqqx;
	}

	public void setDqqx(String dqqx) {
		this.dqqx = dqqx;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
}
