/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����09:43:15 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����09:43:15
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglTjgzForm extends ActionForm {
	private String tjgzid;// varchar2(20) n ��������id
	private String tjgzmc;// varchar2(200) y ������������
	private String tjszzd;// varchar2(100) n ���������ֶ�
	private String tjszmc;// varchar2(200) y ������������
	private String sx;// number y ˳��[Ĭ��]
	private String xxlx;// number n ѡ������
	private String xxz;// varchar2(64) n ѡ��ֵ
	private String wsbl;// varchar2(1) y λ������
	private String sfkfx;// varchar2(1) y �Ƿ�ɸ�ѡ
	private String ylz;// varchar2(300) y Ԥ��ֵȡֵ
	private String sfkxg;// varchar2(1) y �Ƿ���޸�
	private String ppbmzd;// varchar2(300) y ƥ������ֶ�
	private String ylzqz; //Ԥ��ֵȡֵ��

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
	 * @return the tjgzmc
	 */
	public String getTjgzmc() {
		return tjgzmc;
	}

	/**
	 * @param tjgzmcҪ���õ�
	 *            tjgzmc
	 */
	public void setTjgzmc(String tjgzmc) {
		this.tjgzmc = tjgzmc;
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
	 * @return the tjszmc
	 */
	public String getTjszmc() {
		return tjszmc;
	}

	/**
	 * @param tjszmcҪ���õ�
	 *            tjszmc
	 */
	public void setTjszmc(String tjszmc) {
		this.tjszmc = tjszmc;
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
	 * @return the xxlx
	 */
	public String getXxlx() {
		return xxlx;
	}

	/**
	 * @param xxlxҪ���õ�
	 *            xxlx
	 */
	public void setXxlx(String xxlx) {
		this.xxlx = xxlx;
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
	 * @return the sfkfx
	 */
	public String getSfkfx() {
		return sfkfx;
	}

	/**
	 * @param sfkfxҪ���õ�
	 *            sfkfx
	 */
	public void setSfkfx(String sfkfx) {
		this.sfkfx = sfkfx;
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

	public String getYlzqz() {
		return ylzqz;
	}

	public void setYlzqz(String ylzqz) {
		this.ylzqz = ylzqz;
	}
	
}
