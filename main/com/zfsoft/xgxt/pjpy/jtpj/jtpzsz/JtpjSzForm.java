/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-28 ����10:53:23 
 */
package com.zfsoft.xgxt.pjpy.jtpj.jtpzsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-4-28 ����10:53:23
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JtpjSzForm extends ActionForm {
	private String jxid;// varchar2(32) n 'sys_guid()' ����id
	private String jxmc;// varchar2(50) y ��������
	private String jxsm;// varchar2(1000) y ����˵��
	private String jtpjdw;// varchar2(10) y ����������λ(ѧԺ��xy;�༶��bj;������qt)
	private String sqxn;// varchar2(10) y ����ѧ��
	private String sqxq;// varchar2(10) y ����ѧ��
	private String pdxn;// varchar2(10) y ����ѧ��
	private String pdxq;// varchar2(10) y ����ѧ��
	private String sfkfsq;// varchar2(1) y �Ƿ񿪷�����(0����1����)
	private String sqkfkssj;// varchar2(20) y ���뿪�ſ�ʼʱ��
	private String sqkfjssj;// varchar2(20) y ���뿪�Ž���ʱ��
	private String splcid;// varchar2(32) y �������id
	private String ksqxslx;// varchar2(200) y ������ѧ������( - �ָ�)
	private String dybbid;// varchar2(32) y ��Ӧ����id

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String sqxqmc;
	private String pdxqmc;
	private String sqzq;
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
	 * @return the jxsm
	 */
	public String getJxsm() {
		return jxsm;
	}

	/**
	 * @param jxsmҪ���õ�
	 *            jxsm
	 */
	public void setJxsm(String jxsm) {
		this.jxsm = jxsm;
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
	 * @return the sfkfsq
	 */
	public String getSfkfsq() {
		return sfkfsq;
	}

	/**
	 * @param sfkfsqҪ���õ�
	 *            sfkfsq
	 */
	public void setSfkfsq(String sfkfsq) {
		this.sfkfsq = sfkfsq;
	}

	/**
	 * @return the sqkfkssj
	 */
	public String getSqkfkssj() {
		return sqkfkssj;
	}

	/**
	 * @param sqkfkssjҪ���õ�
	 *            sqkfkssj
	 */
	public void setSqkfkssj(String sqkfkssj) {
		this.sqkfkssj = sqkfkssj;
	}

	/**
	 * @return the sqkfjssj
	 */
	public String getSqkfjssj() {
		return sqkfjssj;
	}

	/**
	 * @param sqkfjssjҪ���õ�
	 *            sqkfjssj
	 */
	public void setSqkfjssj(String sqkfjssj) {
		this.sqkfjssj = sqkfjssj;
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
	 * @return the ksqxslx
	 */
	public String getKsqxslx() {
		return ksqxslx;
	}

	/**
	 * @param ksqxslxҪ���õ�
	 *            ksqxslx
	 */
	public void setKsqxslx(String ksqxslx) {
		this.ksqxslx = ksqxslx;
	}

	/**
	 * @return the dybbid
	 */
	public String getDybbid() {
		return dybbid;
	}

	/**
	 * @param dybbidҪ���õ�
	 *            dybbid
	 */
	public void setDybbid(String dybbid) {
		this.dybbid = dybbid;
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
	 * @return the pdxn
	 */
	public String getPdxn() {
		return pdxn;
	}

	/**
	 * @param pdxnҪ���õ� pdxn
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
	 * @param pdxqҪ���õ� pdxq
	 */
	public void setPdxq(String pdxq) {
		this.pdxq = pdxq;
	}

	/**
	 * @return the sqxqmc
	 */
	public String getSqxqmc() {
		return sqxqmc;
	}

	/**
	 * @param sqxqmcҪ���õ� sqxqmc
	 */
	public void setSqxqmc(String sqxqmc) {
		this.sqxqmc = sqxqmc;
	}

	/**
	 * @return the pdxqmc
	 */
	public String getPdxqmc() {
		return pdxqmc;
	}

	/**
	 * @param pdxqmcҪ���õ� pdxqmc
	 */
	public void setPdxqmc(String pdxqmc) {
		this.pdxqmc = pdxqmc;
	}

	/**
	 * @return the sqzq
	 */
	public String getSqzq() {
		return sqzq;
	}

	/**
	 * @param sqzqҪ���õ� sqzq
	 */
	public void setSqzq(String sqzq) {
		this.sqzq = sqzq;
	}

}
