/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:24:33 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����10:24:33
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglGzdmForm extends ActionForm{
	private String gzdm;// varchar2(20) n �������
	private String gzmc;// varchar2(200) n ��������
	private String tjgzid;// varchar2(20) n ������������id(���ŷָ�)

	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

	public String[] getTjgz(){
		if(StringUtils.isNull(tjgzid)){
			return null;
		}
		return tjgzid.split(",");
	}
	/**
	 * @return the gzdm
	 */
	public String getGzdm() {
		return gzdm;
	}

	/**
	 * @param gzdmҪ���õ�
	 *            gzdm
	 */
	public void setGzdm(String gzdm) {
		this.gzdm = gzdm;
	}

	/**
	 * @return the gzmc
	 */
	public String getGzmc() {
		return gzmc;
	}

	/**
	 * @param gzmcҪ���õ�
	 *            gzmc
	 */
	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
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
}
