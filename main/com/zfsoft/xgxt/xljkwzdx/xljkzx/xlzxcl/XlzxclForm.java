/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����02:46:53 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ -������ѯ����
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-29 ����02:46:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlzxclForm extends ActionForm {
	
	private static final long serialVersionUID = -7936444598764937984L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();
	
	private ExportModel exportModel = new ExportModel();
	
	private String zxid;
	
	private String sqid;
	
	private String xh;
	
	private String zzaprq;
	
	private String zxsdkssj;
	
	private String zxsdjssj;
	
	private String zxs;
	
	private String zxslxdh;
	
	private String zxdz;
	
	private String bz;
	
	private String zxzt;
	
	private String zxrq;
	
	private String zxkssj;
	
	private String zxjssj;
	
	private String lfzzs;
	
	private String xlhd;
	
	private String zxzj;
	
	private String gswtlx;
	
	private String jscd;
	
	private String[] jscdarray;
	
	private String qtjscd;
	
	private String yzcdpg;
	
	private String[] yzcdpgarray;
	
	private String qtyzcdpg;
	
	private String sfxyzj;
	
	private String sfyyxczx;
	
	private String xczxsj;
	
	private String zxxgmydpf;
	
	private String xszxpj;
	
	private String[] wtlxarray;
	
	private String[] qtwtlxarray;
	
	private String qtwtlx;
	
	private String yyzxfs;
	

	public String getYyzxfs() {
		return yyzxfs;
	}

	public void setYyzxfs(String yyzxfs) {
		this.yyzxfs = yyzxfs;
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
	 * @return ��ѯID
	 */
	public String getZxid() {
		return zxid;
	}

	/**
	 * @param ��ѯID
	 */
	public void setZxid(String zxid) {
		this.zxid = zxid;
	}

	/**
	 * @return ����ID
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param ����ID
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	/**
	 * @return ѧ��
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param ѧ��
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return ��ѯ��������
	 */
	public String getZzaprq() {
		return zzaprq;
	}

	/**
	 * @param ��ѯ��������
	 */
	public void setZzaprq(String zzaprq) {
		this.zzaprq = zzaprq;
	}

	/**
	 * @return ��ѯʱ�ο�ʼʱ��
	 */
	public String getZxsdkssj() {
		return zxsdkssj;
	}

	/**
	 * @param ��ѯʱ�ο�ʼʱ��
	 */
	public void setZxsdkssj(String zxsdkssj) {
		this.zxsdkssj = zxsdkssj;
	}

	/**
	 * @return ��ѯʱ�ν���ʱ��
	 */
	public String getZxsdjssj() {
		return zxsdjssj;
	}

	/**
	 * @param ��ѯʱ�ν���ʱ��
	 */
	public void setZxsdjssj(String zxsdjssj) {
		this.zxsdjssj = zxsdjssj;
	}

	/**
	 * @return ������ѯʦ
	 */
	public String getZxs() {
		return zxs;
	}

	/**
	 * @param ������ѯʦ
	 */
	public void setZxs(String zxs) {
		this.zxs = zxs;
	}

	/**
	 * @return ��ѯʦ��ϵ�绰
	 */
	public String getZxslxdh() {
		return zxslxdh;
	}

	/**
	 * @param ��ѯʦ��ϵ�绰
	 */
	public void setZxslxdh(String zxslxdh) {
		this.zxslxdh = zxslxdh;
	}

	/**
	 * @return ��ѯ��ַ
	 */
	public String getZxdz() {
		return zxdz;
	}

	/**
	 * @param ��ѯ��ַ
	 */
	public void setZxdz(String zxdz) {
		this.zxdz = zxdz;
	}

	/**
	 * @return ��ע
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param ��ע
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return ��ѯ״̬(0��δ��ѯ;1:����ѯ)
	 */
	public String getZxzt() {
		return zxzt;
	}

	/**
	 * @param ��ѯ״̬(0��δ��ѯ;1:����ѯ)
	 */
	public void setZxzt(String zxzt) {
		this.zxzt = zxzt;
	}

	/**
	 * @return ��ѯ����
	 */
	public String getZxrq() {
		return zxrq;
	}

	/**
	 * @param ��ѯ����
	 */
	public void setZxrq(String zxrq) {
		this.zxrq = zxrq;
	}

	/**
	 * @return ��ѯ��ʼʱ��
	 */
	public String getZxkssj() {
		return zxkssj;
	}

	/**
	 * @param ��ѯ��ʼʱ��
	 */
	public void setZxkssj(String zxkssj) {
		this.zxkssj = zxkssj;
	}

	/**
	 * @return ��ѯ����ʱ��
	 */
	public String getZxjssj() {
		return zxjssj;
	}

	/**
	 * @param ��ѯ����ʱ��
	 */
	public void setZxjssj(String zxjssj) {
		this.zxjssj = zxjssj;
	}

	/**
	 * @return ����������
	 */
	public String getLfzzs() {
		return lfzzs;
	}

	/**
	 * @param ����������
	 */
	public void setLfzzs(String lfzzs) {
		this.lfzzs = lfzzs;
	}

	/**
	 * @return ��ѯ���̼���Ҫ��������
	 */
	public String getXlhd() {
		return xlhd;
	}

	/**
	 * @param ��ѯ���̼���Ҫ��������
	 */
	public void setXlhd(String xlhd) {
		this.xlhd = xlhd;
	}

	/**
	 * @return ��ѯ����ܽ�
	 */
	public String getZxzj() {
		return zxzj;
	}

	/**
	 * @param ��ѯ����ܽ�
	 */
	public void setZxzj(String zxzj) {
		this.zxzj = zxzj;
	}

	/**
	 * @return ������������
	 */
	public String getGswtlx() {
		return gswtlx;
	}

	/**
	 * @param ������������
	 */
	public void setGswtlx(String gswtlx) {
		this.gswtlx = gswtlx;
	}

	/**
	 * @return ��ѯʦ�����߶���ѯ�Ľ��̶ܳ�
	 */
	public String getJscd() {
		return jscd;
	}

	/**
	 * @param ��ѯʦ�����߶���ѯ�Ľ��̶ܳ�
	 */
	public void setJscd(String jscd) {
		this.jscd = jscd;
	}

	/**
	 * @return ��������������������س̶ȵ�����
	 */
	public String getYzcdpg() {
		return yzcdpg;
	}

	/**
	 * @param ��������������������س̶ȵ�����
	 */
	public void setYzcdpg(String yzcdpg) {
		this.yzcdpg = yzcdpg;
	}

	/**
	 * @return �Ƿ���Ҫת��
	 */
	public String getSfxyzj() {
		return sfxyzj;
	}

	/**
	 * @param �Ƿ���Ҫת��
	 */
	public void setSfxyzj(String sfxyzj) {
		this.sfxyzj = sfxyzj;
	}

	/**
	 * @return �Ƿ�ԤԼ�´���ѯ
	 */
	public String getSfyyxczx() {
		return sfyyxczx;
	}

	/**
	 * @param �Ƿ�ԤԼ�´���ѯ
	 */
	public void setSfyyxczx(String sfyyxczx) {
		this.sfyyxczx = sfyyxczx;
	}

	/**
	 * @return ԤԼ�´���ѯʱ��
	 */
	public String getXczxsj() {
		return xczxsj;
	}

	/**
	 * @param ԤԼ�´���ѯʱ��
	 */
	public void setXczxsj(String xczxsj) {
		this.xczxsj = xczxsj;
	}

	/**
	 * @return ѧ����ѯ����
	 */
	public String getXszxpj() {
		return xszxpj;
	}

	/**
	 * @param ѧ����ѯ����
	 */
	public void setXszxpj(String xszxpj) {
		this.xszxpj = xszxpj;
	}
	
	/**
	 * @return ��ѯЧ�����������
	 */
	public String getZxxgmydpf() {
		return zxxgmydpf;
	}

	/**
	 * @param ��ѯЧ�����������
	 */
	public void setZxxgmydpf(String zxxgmydpf) {
		this.zxxgmydpf = zxxgmydpf;
	}

	/**
	 * @return ��������
	 */
	public String[] getWtlxarray() {
		return wtlxarray;
	}

	/**
	 * @param ��������
	 */
	public void setWtlxarray(String[] wtlxarray) {
		this.wtlxarray = wtlxarray;
	}

	/**
	 * @return ������������
	 */
	public String[] getQtwtlxarray() {
		return qtwtlxarray;
	}

	/**
	 * @param ������������
	 */
	public void setQtwtlxarray(String[] qtwtlxarray) {
		this.qtwtlxarray = qtwtlxarray;
	}

	/**
	 * @return ����������������
	 */
	public String getQtwtlx() {
		return qtwtlx;
	}

	/**
	 * @param ����������������
	 */
	public void setQtwtlx(String qtwtlx) {
		this.qtwtlx = qtwtlx;
	}

	/**
	 * @return ���̶ܳ�����
	 */
	public String[] getJscdarray() {
		return jscdarray;
	}

	/**
	 * @param ���̶ܳ�����
	 */
	public void setJscdarray(String[] jscdarray) {
		this.jscdarray = jscdarray;
	}

	/**
	 * @return �������̶ܳ�����
	 */
	public String getQtjscd() {
		return qtjscd;
	}

	/**
	 * @param �������̶ܳ�����
	 */
	public void setQtjscd(String qtjscd) {
		this.qtjscd = qtjscd;
	}

	/**
	 * @return the yzcdpgarray
	 */
	public String[] getYzcdpgarray() {
		return yzcdpgarray;
	}

	/**
	 * @param yzcdpgarrayҪ���õ� yzcdpgarray
	 */
	public void setYzcdpgarray(String[] yzcdpgarray) {
		this.yzcdpgarray = yzcdpgarray;
	}

	/**
	 * @return the qtyzcdpg
	 */
	public String getQtyzcdpg() {
		return qtyzcdpg;
	}

	/**
	 * @param qtyzcdpgҪ���õ� qtyzcdpg
	 */
	public void setQtyzcdpg(String qtyzcdpg) {
		this.qtyzcdpg = qtyzcdpg;
	}

}
