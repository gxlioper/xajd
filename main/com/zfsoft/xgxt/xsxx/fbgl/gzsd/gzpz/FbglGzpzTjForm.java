/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:07:12 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.xsxx.fbgl.Config;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����10:07:12
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglGzpzTjForm  extends ActionForm {
	private String pzgzid;// varchar2(100) n ���ù���id
	private String pzgzmc;// varchar2(100) n ���ù�������
	private String qyzt="0";// varchar2(1) n ����״̬
	private String gzdm;// varchar2(20) n �������
	private String gxsj;// varchar2(40) y ����ʱ��
	private String sfnz;//�Ƿ�����
	private String syzt;//ʹ��״̬
	//��չ�Զ�
	private String qyztmc;//
	private String gzmc;//��������
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();

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
	 * @return the pzgzmc
	 */
	public String getPzgzmc() {
		return pzgzmc;
	}

	/**
	 * @param pzgzmcҪ���õ�
	 *            pzgzmc
	 */
	public void setPzgzmc(String pzgzmc) {
		this.pzgzmc = pzgzmc;
	}

	/**
	 * @return the qyzt
	 */
	public String getQyzt() {
		return qyzt;
	}

	/**
	 * @param qyztҪ���õ�
	 *            qyzt
	 */
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
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
	 * @return the gxsj
	 */
	public String getGxsj() {
		return gxsj;
	}

	/**
	 * @param gxsjҪ���õ�
	 *            gxsj
	 */
	public void setGxsj(String gxsj) {
		this.gxsj = gxsj;
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

	/**
	 * @return the gzmc
	 */
	public String getGzmc() {
		return gzmc;
	}

	/**
	 * @param gzmcҪ���õ� gzmc
	 */
	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
	}

	/**
	 * @return the qyztmc
	 */
	public String getQyztmc() {
		if(StringUtils.isNull(qyztmc)){
			if(Config._QYZT_BQY.equals(this.qyzt)){
				qyztmc="ͣ��";
			}else{
				qyztmc="����";
			}
		}
		return qyztmc;
	}

	/**
	 * @param qyztmcҪ���õ� qyztmc
	 */
	public void setQyztmc(String qyztmc) {
		this.qyztmc = qyztmc;
	}

	/**
	 * @return the sfnz
	 */
	public String getSfnz() {
		return sfnz;
	}

	/**
	 * @param sfnzҪ���õ� sfnz
	 */
	public void setSfnz(String sfnz) {
		this.sfnz = sfnz;
	}

	/**
	 * @return the syzt
	 */
	public String getSyzt() {
		return syzt;
	}

	/**
	 * @param syztҪ���õ� syzt
	 */
	public void setSyzt(String syzt) {
		this.syzt = syzt;
	}
}
