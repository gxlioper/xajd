/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:00:24 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������:form
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:00:24
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjsqForm extends ActionForm {
	private String qjsqid;
	private String qjlxid;
	private String qjts;
	private String kssj;
	private String jssj;
	private String qjzt;
	private String xh;
	private String xn;
	private String xq;
	private String xqmc;
	private String qjsy;
	private String splcid;
	private String shzt;
	private String sqsj;
	private String kcbhs;
	private String qjbh;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String lcxx;
	private String addtype;//��־�Ƿ�����ʦ���� 1Ϊ��ʦ����
	//�������
	private FormFile formfile;
	private String filepath;

	private String ssxydm;//����ѧԺ
	
	private String qjlxmc;
	private String qjjs;
	private String sflx;
	private String sflxmc;
	private String jzdh;
	private String bz;
	private String jhrxm;//�໤������
	private String jhrlxfs;//�໤����ϵ��ʽ
	private String jtgj;//��ͨ����
	private String mdd;//Ŀ�ĵ�
	private String jtgjmc;
	//����ҽҩ�ߵ�ר��ѧУ
	private String xnxw;
	
	public String getXnxw() {
		return xnxw;
	}

	public void setXnxw(String xnxw) {
		this.xnxw = xnxw;
	}

	public String getSsxydm() {
		return ssxydm;
	}

	public void setSsxydm(String ssxydm) {
		this.ssxydm = ssxydm;
	}

	/**
	 * @return the qjlxmc
	 */
	public String getQjlxmc() {
		return qjlxmc;
	}

	/**
	 * @param qjlxmc the qjlxmc to set
	 */
	public void setQjlxmc(String qjlxmc) {
		this.qjlxmc = qjlxmc;
	}

	/**
	 * @return the qjsqid
	 */
	public String getQjsqid() {
		return qjsqid;
	}

	/**
	 * @param qjsqidҪ���õ�
	 *            qjsqid
	 */
	public void setQjsqid(String qjsqid) {
		this.qjsqid = qjsqid;
	}

	/**
	 * @return the qjlxid
	 */
	public String getQjlxid() {
		return qjlxid;
	}

	/**
	 * @param qjlxidҪ���õ�
	 *            qjlxid
	 */
	public void setQjlxid(String qjlxid) {
		this.qjlxid = qjlxid;
	}

	/**
	 * @return the qjts
	 */
	public String getQjts() {
		return qjts;
	}

	/**
	 * @param qjtsҪ���õ�
	 *            qjts
	 */
	public void setQjts(String qjts) {
		this.qjts = qjts;
	}

	/**
	 * @return the kssj
	 */
	public String getKssj() {
		return kssj;
	}

	/**
	 * @param kssjҪ���õ�
	 *            kssj
	 */
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	/**
	 * @return the jssj
	 */
	public String getJssj() {
		return jssj;
	}

	/**
	 * @param jssjҪ���õ�
	 *            jssj
	 */
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	/**
	 * @return the qjzt
	 */
	public String getQjzt() {
		return qjzt;
	}

	/**
	 * @param qjztҪ���õ�
	 *            qjzt
	 */
	public void setQjzt(String qjzt) {
		this.qjzt = qjzt;
	}

	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param xhҪ���õ�
	 *            xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}

	/**
	 * @param xnҪ���õ�
	 *            xn
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
	 * @param xqҪ���õ�
	 *            xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}

	/**
	 * @return the qjsy
	 */
	public String getQjsy() {
		return qjsy;
	}

	/**
	 * @param qjsyҪ���õ�
	 *            qjsy
	 */
	public void setQjsy(String qjsy) {
		this.qjsy = qjsy;
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
	 * @return the addtype
	 */
	public String getAddtype() {
		return addtype;
	}

	/**
	 * @param addtypeҪ���õ� addtype
	 */
	public void setAddtype(String addtype) {
		this.addtype = addtype;
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
	 * @return the kcbhs
	 */
	public String getKcbhs() {
		return kcbhs;
	}

	/**
	 * @param kcbhsҪ���õ� kcbhs
	 */
	public void setKcbhs(String kcbhs) {
		this.kcbhs = kcbhs;
	}

	public String getQjjs() {
		return qjjs;
	}

	public void setQjjs(String qjjs) {
		this.qjjs = qjjs;
	}

	public String getSflx() {
		return sflx;
	}

	public void setSflx(String sflx) {
		this.sflx = sflx;
	}

	public String getSflxmc() {
		return sflxmc;
	}

	public void setSflxmc(String sflxmc) {
		this.sflxmc = sflxmc;
	}

	/**
	 * @return the qjbh
	 */
	public String getQjbh() {
		return qjbh;
	}

	/**
	 * @param qjbhҪ���õ� qjbh
	 */
	public void setQjbh(String qjbh) {
		this.qjbh = qjbh;
	}

	/**
	 * @return the jzdh
	 */
	public String getJzdh() {
		return jzdh;
	}

	/**
	 * @param jzdhҪ���õ� jzdh
	 */
	public void setJzdh(String jzdh) {
		this.jzdh = jzdh;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the jhrxm
	 */
	public String getJhrxm() {
		return jhrxm;
	}

	/**
	 * @param jhrxmҪ���õ� jhrxm
	 */
	public void setJhrxm(String jhrxm) {
		this.jhrxm = jhrxm;
	}

	/**
	 * @return the jhrlxfs
	 */
	public String getJhrlxfs() {
		return jhrlxfs;
	}

	/**
	 * @param jhrlxfsҪ���õ� jhrlxfs
	 */
	public void setJhrlxfs(String jhrlxfs) {
		this.jhrlxfs = jhrlxfs;
	}

	/**
	 * @return the jtgj
	 */
	public String getJtgj() {
		return jtgj;
	}

	/**
	 * @param jtgjҪ���õ� jtgj
	 */
	public void setJtgj(String jtgj) {
		this.jtgj = jtgj;
	}

	/**
	 * @return the mdd
	 */
	public String getMdd() {
		return mdd;
	}

	/**
	 * @param mddҪ���õ� mdd
	 */
	public void setMdd(String mdd) {
		this.mdd = mdd;
	}

	/**
	 * @return the jtgjmc
	 */
	public String getJtgjmc() {
		return jtgjmc;
	}

	/**
	 * @param jtgjmcҪ���õ� jtgjmc
	 */
	public void setJtgjmc(String jtgjmc) {
		this.jtgjmc = jtgjmc;
	}
	
	
	
}
