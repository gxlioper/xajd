/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018��5��22�� ����5:53:04 
 */  
package xsgzgl.gyjc.jcsd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018��5��22�� ����5:53:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CcrcForm  extends ActionForm{
	private String ccid;
	private String jcrq;
	private String xn;
	private String xqdm;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String lddm;
	private String qshs;
	private String lddms;
	private String xb;
	private String ldmc;
	private String bmmc;
	private String ch;
	private String qsh;
	private String zgh;
	private String xm;
	private String sffp;
	private String xh;
	private String qsnum;
	private String xgzgh;
	private String[] lds;
	private String[] qss;
	/**
	 * @return the jcxy
	 */
	public String getJcxy() {
		return jcxy;
	}
	/**
	 * @param jcxyҪ���õ� jcxy
	 */
	public void setJcxy(String jcxy) {
		this.jcxy = jcxy;
	}
	/**
	 * @return the wtjs
	 */
	public String getWtjs() {
		return wtjs;
	}
	/**
	 * @param wtjsҪ���õ� wtjs
	 */
	public void setWtjs(String wtjs) {
		this.wtjs = wtjs;
	}
	/**
	 * @return the ytjs
	 */
	public String getYtjs() {
		return ytjs;
	}
	/**
	 * @param ytjsҪ���õ� ytjs
	 */
	public void setYtjs(String ytjs) {
		this.ytjs = ytjs;
	}
	private String[] dels;
	private String jcxy;
	private String wtjs;
	private String ytjs;
	
	/**
	 * @return the dels
	 */
	public String[] getDels() {
		return dels;
	}
	/**
	 * @param delsҪ���õ� dels
	 */
	public void setDels(String[] dels) {
		this.dels = dels;
	}
	/**
	 * @return the lds
	 */
	public String[] getLds() {
		return lds;
	}
	/**
	 * @param ldsҪ���õ� lds
	 */
	public void setLds(String[] lds) {
		this.lds = lds;
	}
	/**
	 * @return the qss
	 */
	public String[] getQss() {
		return qss;
	}
	/**
	 * @param qssҪ���õ� qss
	 */
	public void setQss(String[] qss) {
		this.qss = qss;
	}
	/**
	 * @return the xgzgh
	 */
	public String getXgzgh() {
		return xgzgh;
	}
	/**
	 * @param xgzghҪ���õ� xgzgh
	 */
	public void setXgzgh(String xgzgh) {
		this.xgzgh = xgzgh;
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
	 * @return the qsnum
	 */
	public String getQsnum() {
		return qsnum;
	}
	/**
	 * @param qsnumҪ���õ� qsnum
	 */
	public void setQsnum(String qsnum) {
		this.qsnum = qsnum;
	}

	/**
	 * @return the sffp
	 */
	public String getSffp() {
		return sffp;
	}
	/**
	 * @param sffpҪ���õ� sffp
	 */
	public void setSffp(String sffp) {
		this.sffp = sffp;
	}
	/**
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zghҪ���õ� zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xbҪ���õ� xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}

	/**
	 * @return the lddms
	 */
	public String getLddms() {
		return lddms;
	}
	/**
	 * @param lddmsҪ���õ� lddms
	 */
	public void setLddms(String lddms) {
		this.lddms = lddms;
	}
	/**
	 * @return the qshs
	 */
	public String getQshs() {
		return qshs;
	}
	/**
	 * @param qshsҪ���õ� qshs
	 */
	public void setQshs(String qshs) {
		this.qshs = qshs;
	}
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
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmcҪ���õ� ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return the bmmc
	 */
	public String getBmmc() {
		return bmmc;
	}
	/**
	 * @param bmmcҪ���õ� bmmc
	 */
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
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
	 * @return the ccid
	 */
	public String getCcid() {
		return ccid;
	}
	/**
	 * @param ccidҪ���õ� ccid
	 */
	public void setCcid(String ccid) {
		this.ccid = ccid;
	}
	/**
	 * @return the jcrq
	 */
	public String getJcrq() {
		return jcrq;
	}
	/**
	 * @param jcrqҪ���õ� jcrq
	 */
	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
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
	 * @return the xqdm
	 */
	public String getXqdm() {
		return xqdm;
	}
	/**
	 * @param xqdmҪ���õ� xqdm
	 */
	public void setXqdm(String xqdm) {
		this.xqdm = xqdm;
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

}
