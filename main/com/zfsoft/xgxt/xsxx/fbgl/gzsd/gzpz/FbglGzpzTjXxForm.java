/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:10:29 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����10:10:29
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglGzpzTjXxForm  extends ActionForm {
	private String pk;
	private String pzgzid;// varchar2(100) n ���ù���id
	private String tjgzid;// varchar2(20) n ������������id
	private String tjszzd;// varchar2(100) n ���������ֶ�
	private String sx;// number y ˳��
	private String xxz;// varchar2(300) y ѡ��ֵ
	private String wsbl;// varchar2(1) y λ������
	private String ylz;// varchar2(300) y Ԥ��ֵȡֵ
	private String qsz;//��ʼֵ
	private String sfkxg;// varchar2(1) y �Ƿ���޸�
	private String ppbmzd;// varchar2(300) y ƥ������ֶ�

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

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
	 * @return the pzgzid
	 */
	public String getPzgzid() {
		return pzgzid;
	}

	/**
	 * @param pzgzidҪ���õ�
	 *            pzgzid
	 */
	public void setPzgzid(String pzgzid) {
		this.pzgzid = pzgzid;
	}

	/**
	 * @return the tjgzid
	 */
	public String getTjgzid() {
		return tjgzid;
	}

	/**
	 * @param tjgzidҪ���õ�
	 *            tjgzid
	 */
	public void setTjgzid(String tjgzid) {
		this.tjgzid = tjgzid;
	}

	/**
	 * @return the tjszzd
	 */
	public String getTjszzd() {
		return tjszzd;
	}

	/**
	 * @param tjszzdҪ���õ�
	 *            tjszzd
	 */
	public void setTjszzd(String tjszzd) {
		this.tjszzd = tjszzd;
	}

	/**
	 * @return the sx
	 */
	public String getSx() {
		return sx;
	}

	/**
	 * @param sxҪ���õ�
	 *            sx
	 */
	public void setSx(String sx) {
		this.sx = sx;
	}

	/**
	 * @return the xxz
	 */
	public String getXxz() {
		return xxz;
	}

	/**
	 * @param xxzҪ���õ�
	 *            xxz
	 */
	public void setXxz(String xxz) {
		this.xxz = xxz;
	}

	/**
	 * @return the wsbl
	 */
	public String getWsbl() {
		return wsbl;
	}

	/**
	 * @param wsblҪ���õ�
	 *            wsbl
	 */
	public void setWsbl(String wsbl) {
		this.wsbl = wsbl;
	}

	/**
	 * @return the ylz
	 */
	public String getYlz() {
		return ylz;
	}

	/**
	 * @param ylzҪ���õ�
	 *            ylz
	 */
	public void setYlz(String ylz) {
		this.ylz = ylz;
	}

	/**
	 * @return the sfkxg
	 */
	public String getSfkxg() {
		return sfkxg;
	}

	/**
	 * @param sfkxgҪ���õ�
	 *            sfkxg
	 */
	public void setSfkxg(String sfkxg) {
		this.sfkxg = sfkxg;
	}

	/**
	 * @return the ppbmzd
	 */
	public String getPpbmzd() {
		return ppbmzd;
	}

	/**
	 * @param ppbmzdҪ���õ�
	 *            ppbmzd
	 */
	public void setPpbmzd(String ppbmzd) {
		this.ppbmzd = ppbmzd;
	}

	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}

	/**
	 * @param pkҪ���õ� pk
	 */
	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getQsz() {
		return qsz;
	}

	public void setQsz(String qsz) {
		this.qsz = qsz;
	}
	
}
