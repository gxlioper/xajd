/**
 * @����:ѧ����Ʒ��ҵ��
 * @ʱ�䣺 2013-12-3 ����10:56:46 
 */  
package com.zfsoft.xgxt.xsxx.xjyd.xjydjg;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @ģ������: ѧ���춯
 * @�๦������:ѧ���춯���
 * @�๦������: ѧ���춯�����
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-12-3 ����10:56:46 
 * @�汾�� V1.0
 */
public class XjydjgForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	/**
	 * ѧ���춯���ID
	 */
	private String xjydjgid;
	/**
	 * ������Դ
	 */
	private String sjly;
	
	/**
	 * ��¼ʱ��
	 */
	private String jrsj;
	
	/**
	 * ѧ���춯�ĺ�
	 */
	private String xjydwh;
	/**
	 * ѧ���춯ʱ��
	 */
	private String xjydsj;
	/**
	 * ѧ���춯��ע
	 */
	private String xjydbz;

	/**
	 * ѧ���춯����id
	 */
	private String xjydsqid;
	/**
	 * ѧ��
	 */
	private String xh;

	/**
	 * ѧ��
	 */
	private String xn;
	/**
	 * ѧ��
	 */
	private String xq;
	/**
	 * ѧ��
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
	 * �Ƿ�����༶
	 */
	private String sftjbj;
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
	 * ����ѡ��ѧ����KEY
	 */
	private String xzxsKey;
	
	/**
	 * ��Ӧ����
	 */
	private String dybb;

	/**
	 * ���뿪ʼʱ��
	 */
	private String sqkssj;
	
	/**
	 * �������ʱ��
	 */
	private String sqjssj;
	
	//�������
	private FormFile formfile;
	private String filepath;
	
	
	private String ydyydm; // ԭ�����
	
	private String ydyymc; // ԭ������
	
	private String lyqxxxdm; // ѧУ����
	
	private String xxmc; // ѧУ����
	
	private String dqztdm; // ��ǰ״̬����
	
	private String dqztmc; // ��ǰ״̬����
	
	private String xz; // ѧ��
	
	private String sfsfs; // �Ƿ�ʦ����

	private String ydqsydm;//�춯ǰ��Ժ����

	private String ydqzybjdm;//�춯ǰרҵ�༶����

	public String getYdqsydm() {
		return ydqsydm;
	}

	public void setYdqsydm(String ydqsydm) {
		this.ydqsydm = ydqsydm;
	}

	public String getYdqzybjdm() {
		return ydqzybjdm;
	}

	public void setYdqzybjdm(String ydqzybjdm) {
		this.ydqzybjdm = ydqzybjdm;
	}

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
	public String getXzxsKey() {
		return xzxsKey;
	}
	public void setXzxsKey(String xzxsKey) {
		this.xzxsKey = xzxsKey;
	}
	public String getSftjbj() {
		return sftjbj;
	}
	public void setSftjbj(String sftjbj) {
		this.sftjbj = sftjbj;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;

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
	public String getYdlbmc() {
		return ydlbmc;
	}
	public void setYdlbmc(String ydlbmc) {
		this.ydlbmc = ydlbmc;
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
	public String getYdqxjlb() {
		return ydqxjlb;
	}
	public void setYdqxjlb(String ydqxjlb) {
		this.ydqxjlb = ydqxjlb;
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
	public String getYdhxjlb() {
		return ydhxjlb;
	}
	public void setYdhxjlb(String ydhxjlb) {
		this.ydhxjlb = ydhxjlb;
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
	public String getXjydjgid() {
		return xjydjgid;
	}
	public void setXjydjgid(String xjydjgid) {
		this.xjydjgid = xjydjgid;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getJrsj() {
		return jrsj;
	}
	public void setJrsj(String jrsj) {
		this.jrsj = jrsj;
	}
	public String getXjydwh() {
		return xjydwh;
	}
	public void setXjydwh(String xjydwh) {
		this.xjydwh = xjydwh;
	}
	public String getXjydsj() {
		return xjydsj;
	}
	public void setXjydsj(String xjydsj) {
		this.xjydsj = xjydsj;
	}
	public String getXjydbz() {
		return xjydbz;
	}
	public void setXjydbz(String xjydbz) {
		this.xjydbz = xjydbz;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getDybb() {
		return dybb;
	}

	public void setDybb(String dybb) {
		this.dybb = dybb;
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
