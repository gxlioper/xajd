/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-5 ����10:31:31 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpjjg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-5-5 ����10:31:31
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjJgForm extends ActionForm {

	/** 
	 * @���� serialVersionUID
	 */ 
	private static final long serialVersionUID = 1L;
	private String pdxn;// varchar2(20) 20 FALSE FALSE FALSE
	private String pdxq;// varchar2(20) 20 FALSE FALSE FALSE
	private String jgid;
	private String sjly;
	private String jxid;// varchar2(32) y 'sys_guid()' ����id
	private String sqxn;// varchar2(20) y ����ѧ��
	private String sqxq;// varchar2(20) y ����ѧ��
	private String jtpjdw;// varchar2(10) y ����������λ(ѧԺ��xy;�༶��bj;������qt)
	private String pjjtid;// varchar2(20) y ��������id
	private String pjjtmc;// varchar2(20) y ������������
	private String jtjj;// varchar2(1000) y ������
	private String rs;// varchar2(20) y ����
	private String sqrylx;// varchar2(2) y ������Ա����(stu��ѧ����tea����ʦ)
	private String sqr;// varchar2(20) y ������(ѧ��/ְ����)
	private String sqly;// varchar2(1000) y ��������
	private String shzt;// varchar2(2) y ���״̬
	private String splcid;// varchar2(32) y ��������id
	private String sqsj;// varchar2(20) y ����ʱ��
	private String sqid;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String jxmc;
	private String rdfs;
	private String filepath;
	
	private String lddm;
	private String ch;
	private String qsh;

	
	
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}

	/**
	 * @param lddmҪ���õ� lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	/**
	 * @return the ch
	 */
	public String getCh() {
		return ch;
	}

	/**
	 * @param chҪ���õ� ch
	 */
	public void setCh(String ch) {
		this.ch = ch;
	}

	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}

	/**
	 * @param qshҪ���õ� qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	/**
	 * @return the pdxn
	 */
	public String getPdxn() {
		return pdxn;
	}

	/**
	 * @param pdxnҪ���õ�
	 *            pdxn
	 */
	public void setPdxn(String pdxn) {
		this.pdxn = pdxn;
	}

	/**
	 * @return the pdxq
	 */
	public String getPdxq() {
		return pdxq;
	}

	/**
	 * @param pdxqҪ���õ�
	 *            pdxq
	 */
	public void setPdxq(String pdxq) {
		this.pdxq = pdxq;
	}

	/**
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}

	/**
	 * @param jgidҪ���õ�
	 *            jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}

	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}

	/**
	 * @param sjlyҪ���õ�
	 *            sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	/**
	 * @return the jxid
	 */
	public String getJxid() {
		return jxid;
	}

	/**
	 * @param jxidҪ���õ�
	 *            jxid
	 */
	public void setJxid(String jxid) {
		this.jxid = jxid;
	}

	/**
	 * @return the sqxn
	 */
	public String getSqxn() {
		return sqxn;
	}

	/**
	 * @param sqxnҪ���õ�
	 *            sqxn
	 */
	public void setSqxn(String sqxn) {
		this.sqxn = sqxn;
	}

	/**
	 * @return the sqxq
	 */
	public String getSqxq() {
		return sqxq;
	}

	/**
	 * @param sqxqҪ���õ�
	 *            sqxq
	 */
	public void setSqxq(String sqxq) {
		this.sqxq = sqxq;
	}

	/**
	 * @return the jtpjdw
	 */
	public String getJtpjdw() {
		return jtpjdw;
	}

	/**
	 * @param jtpjdwҪ���õ�
	 *            jtpjdw
	 */
	public void setJtpjdw(String jtpjdw) {
		this.jtpjdw = jtpjdw;
	}

	/**
	 * @return the pjjtid
	 */
	public String getPjjtid() {
		return pjjtid;
	}

	/**
	 * @param pjjtidҪ���õ�
	 *            pjjtid
	 */
	public void setPjjtid(String pjjtid) {
		this.pjjtid = pjjtid;
	}

	/**
	 * @return the pjjtmc
	 */
	public String getPjjtmc() {
		return pjjtmc;
	}

	/**
	 * @param pjjtmcҪ���õ�
	 *            pjjtmc
	 */
	public void setPjjtmc(String pjjtmc) {
		this.pjjtmc = pjjtmc;
	}

	/**
	 * @return the jtjj
	 */
	public String getJtjj() {
		return jtjj;
	}

	/**
	 * @param jtjjҪ���õ�
	 *            jtjj
	 */
	public void setJtjj(String jtjj) {
		this.jtjj = jtjj;
	}

	/**
	 * @return the rs
	 */
	public String getRs() {
		return rs;
	}

	/**
	 * @param rsҪ���õ�
	 *            rs
	 */
	public void setRs(String rs) {
		this.rs = rs;
	}

	/**
	 * @return the sqrylx
	 */
	public String getSqrylx() {
		return sqrylx;
	}

	/**
	 * @param sqrylxҪ���õ�
	 *            sqrylx
	 */
	public void setSqrylx(String sqrylx) {
		this.sqrylx = sqrylx;
	}

	/**
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}

	/**
	 * @param sqrҪ���õ�
	 *            sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}

	/**
	 * @param sqlyҪ���õ�
	 *            sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}

	/**
	 * @param shztҪ���õ�
	 *            shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}

	/**
	 * @param splcidҪ���õ�
	 *            splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}

	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}

	/**
	 * @param sqsjҪ���õ�
	 *            sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pagesҪ���õ�
	 *            pages
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
	 * @param searchModelҪ���õ�
	 *            searchModel
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
	 * @param typeҪ���õ�
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the jxmc
	 */
	public String getJxmc() {
		return jxmc;
	}

	/**
	 * @param jxmcҪ���õ�
	 *            jxmc
	 */
	public void setJxmc(String jxmc) {
		this.jxmc = jxmc;
	}

	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}

	/**
	 * @param sqidҪ���õ�
	 *            sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the rdfs
	 */
	public String getRdfs() {
		return rdfs;
	}

	/**
	 * @param rdfsҪ���õ� rdfs
	 */
	public void setRdfs(String rdfs) {
		this.rdfs = rdfs;
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
