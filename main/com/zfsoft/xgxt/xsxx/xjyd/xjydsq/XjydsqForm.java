package com.zfsoft.xgxt.xsxx.xjyd.xjydsq;

import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.xsxx.xjyd.XjydForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ���춯����
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-11-28 ����09:40:48 
 * @�汾�� V5.12.20
 */
public class XjydsqForm extends XjydForm {

	private static final long serialVersionUID = 1L;

	private String shlcidnew; //���������ID
	
	
	private String type;
	/**
	 * ��ҳ��
	 */
	private Pages pages = new Pages();
	/**
	 * �߼���ѯ��
	 */
	private SearchModel searchModel = new SearchModel();
	/**
	 * ������
	 */
	private ExportModel exportModel = new ExportModel();
	/**
	 * ѧ���춯����id
	 */
	private String xjydsqid;
	/**
	 * ѧ��
	 */
	private String xh;
	/**
	 * ����ʱ��
	 */
	private String sqsj;
	/**
	 * ��������id
	 */
	private String splcid;
	/**
	 * ���״̬
	 */
	private String shzt;
	/**
	 * ѧ��
	 */
	private String xn;
	/**
	 * ѧ��
	 */
	private String xq;
	/**
	 * ѧ������
	 */
	private String xqmc;
	/**
	 * �춯������
	 */
	private String ydlbdm;
	/**
	 * �춯�������
	 */
	private String ydlbmc;
	/**
	 * ������/������
	 */
	private String sqr;
	/**
	 * ��������
	 */
	private String sqly;
	/**
	 * �춯ǰ�꼶
	 */
	private String ydqnj;
	/**
	 * �춯ǰѧԺ����
	 */
	private String ydqxydm;
	/**
	 * �춯ǰרҵ����
	 */
	private String ydqzydm;
	/**
	 * �춯ǰ�༶����
	 */
	private String ydqbjdm;
	/**
	 * �춯ǰѧԺ����
	 */
	private String ydqxymc;
	/**
	 * �춯ǰרҵ����
	 */
	private String ydqzymc;
	/**
	 * �춯ǰ�༶����
	 */
	private String ydqbjmc;
	/**
	 * �춯ǰѧ��������
	 */
	private String ydqxjlb;
	/**
	 * �춯ǰѧ���������
	 */
	private String ydqxjlbmc;
	/**
	 * �춯ǰ�Ƿ���ѧ������
	 */
	private String ydqsfyxjmc;
	/**
	 * �춯ǰ�Ƿ���У����
	 */
	private String ydqsfzxmc;
	/**
	 * �춯���꼶
	 */
	private String ydhnj;
	/**
	 * �춯��ѧԺ����
	 */
	private String ydhxydm;
	/**
	 * �춯��רҵ����
	 */
	private String ydhzydm;
	/**
	 * �춯��༶����
	 */
	private String ydhbjdm;
	/**
	 * �춯��ѧԺ����
	 */
	private String ydhxymc;
	/**
	 * �춯��רҵ����
	 */
	private String ydhzymc;
	/**
	 * �춯��༶����
	 */
	private String ydhbjmc;
	/**
	 * �춯��ѧ��������
	 */
	private String ydhxjlb;

	/**
	 * �춯��ѧ���������
	 */
	private String ydhxjlbmc;
	/**
	 * �춯���Ƿ���ѧ������
	 */
	private String ydhsfyxjmc;
	/**
	 * �춯���Ƿ���У����
	 */
	private String ydhsfzxmc;
	
	/**
	 * ���뿪ʼʱ��
	 */
	private String sqkssj;
	
	/**
	 * �������ʱ��
	 */
	private String sqjssj;
	
	
	private String ydyydm; // ԭ�����
	
	private String ydyymc; // ԭ������
	
	private String lyqxxxdm; // ѧУ����
	
	private String xxmc; // ѧУ����
	
	private String dqztdm; // ��ǰ״̬����
	
	private String dqztmc; // ��ǰ״̬����
	
	private String xz; // ѧ��
	
	private String sfsfs; // �Ƿ�ʦ����	
	
	//�������
	private FormFile formfile;
	private String filepath;
	
	
	public String getDqztdm() {
		return dqztdm;
	}
	public void setDqztdm(String dqztdm) {
		this.dqztdm = dqztdm;
	}
	public String getDqztmc() {
		return dqztmc;
	}
	public void setDqztmc(String dqztmc) {
		this.dqztmc = dqztmc;
	}
	public String getYdyymc() {
		return ydyymc;
	}
	public void setYdyymc(String ydyymc) {
		this.ydyymc = ydyymc;
	}
	public String getXxmc() {
		return xxmc;
	}
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	public String getYdyydm() {
		return ydyydm;
	}
	public void setYdyydm(String ydyydm) {
		this.ydyydm = ydyydm;
	}
	public String getLyqxxxdm() {
		return lyqxxxdm;
	}
	public void setLyqxxxdm(String lyqxxxdm) {
		this.lyqxxxdm = lyqxxxdm;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getSfsfs() {
		return sfsfs;
	}
	public void setSfsfs(String sfsfs) {
		this.sfsfs = sfsfs;
	}
	public String getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	public String getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getYdlbmc() {
		return ydlbmc;
	}
	public void setYdlbmc(String ydlbmc) {
		this.ydlbmc = ydlbmc;
	}
	public String getYdqxymc() {
		return ydqxymc;
	}
	public void setYdqxymc(String ydqxymc) {
		this.ydqxymc = ydqxymc;
	}
	public String getYdqzymc() {
		return ydqzymc;
	}
	public void setYdqzymc(String ydqzymc) {
		this.ydqzymc = ydqzymc;
	}
	public String getYdqbjmc() {
		return ydqbjmc;
	}
	public void setYdqbjmc(String ydqbjmc) {
		this.ydqbjmc = ydqbjmc;
	}
	public String getYdqxjlbmc() {
		return ydqxjlbmc;
	}
	public void setYdqxjlbmc(String ydqxjlbmc) {
		this.ydqxjlbmc = ydqxjlbmc;
	}
	public String getYdqsfyxjmc() {
		return ydqsfyxjmc;
	}
	public void setYdqsfyxjmc(String ydqsfyxjmc) {
		this.ydqsfyxjmc = ydqsfyxjmc;
	}
	public String getYdqsfzxmc() {
		return ydqsfzxmc;
	}
	public void setYdqsfzxmc(String ydqsfzxmc) {
		this.ydqsfzxmc = ydqsfzxmc;
	}
	public String getYdhxymc() {
		return ydhxymc;
	}
	public void setYdhxymc(String ydhxymc) {
		this.ydhxymc = ydhxymc;
	}
	public String getYdhzymc() {
		return ydhzymc;
	}
	public void setYdhzymc(String ydhzymc) {
		this.ydhzymc = ydhzymc;
	}
	public String getYdhbjmc() {
		return ydhbjmc;
	}
	public void setYdhbjmc(String ydhbjmc) {
		this.ydhbjmc = ydhbjmc;
	}
	public String getYdhxjlbmc() {
		return ydhxjlbmc;
	}
	public void setYdhxjlbmc(String ydhxjlbmc) {
		this.ydhxjlbmc = ydhxjlbmc;
	}
	public String getYdhsfyxjmc() {
		return ydhsfyxjmc;
	}
	public void setYdhsfyxjmc(String ydhsfyxjmc) {
		this.ydhsfyxjmc = ydhsfyxjmc;
	}
	public String getYdhsfzxmc() {
		return ydhsfzxmc;
	}
	public void setYdhsfzxmc(String ydhsfzxmc) {
		this.ydhsfzxmc = ydhsfzxmc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getXjydsqid() {
		return xjydsqid;
	}
	public void setXjydsqid(String xjydsqid) {
		this.xjydsqid = xjydsqid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getSplcid() {
		return splcid;
	}
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
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
	public String getYdlbdm() {
		return ydlbdm;
	}
	public void setYdlbdm(String ydlbdm) {
		this.ydlbdm = ydlbdm;
	}
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	public String getSqly() {
		return sqly;
	}
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	public String getYdqnj() {
		return ydqnj;
	}
	public void setYdqnj(String ydqnj) {
		this.ydqnj = ydqnj;
	}
	public String getYdqxydm() {
		return ydqxydm;
	}
	public void setYdqxydm(String ydqxydm) {
		this.ydqxydm = ydqxydm;
	}
	public String getYdqzydm() {
		return ydqzydm;
	}
	public void setYdqzydm(String ydqzydm) {
		this.ydqzydm = ydqzydm;
	}
	public String getYdqbjdm() {
		return ydqbjdm;
	}
	public void setYdqbjdm(String ydqbjdm) {
		this.ydqbjdm = ydqbjdm;
	}
	public String getYdqxjlb() {
		return ydqxjlb;
	}
	public void setYdqxjlb(String ydqxjlb) {
		this.ydqxjlb = ydqxjlb;
	}
	public String getYdhnj() {
		return ydhnj;
	}
	public void setYdhnj(String ydhnj) {
		this.ydhnj = ydhnj;
	}
	public String getYdhxydm() {
		return ydhxydm;
	}
	public void setYdhxydm(String ydhxydm) {
		this.ydhxydm = ydhxydm;
	}
	public String getYdhzydm() {
		return ydhzydm;
	}
	public void setYdhzydm(String ydhzydm) {
		this.ydhzydm = ydhzydm;
	}
	public String getYdhbjdm() {
		return ydhbjdm;
	}
	public void setYdhbjdm(String ydhbjdm) {
		this.ydhbjdm = ydhbjdm;
	}
	public String getYdhxjlb() {
		return ydhxjlb;
	}
	public void setYdhxjlb(String ydhxjlb) {
		this.ydhxjlb = ydhxjlb;
	}
	public String getShlcidnew() {
		return shlcidnew;
	}
	public void setShlcidnew(String shlcidnew) {
		this.shlcidnew = shlcidnew;
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
}
